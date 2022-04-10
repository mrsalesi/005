/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behPardakhtMellat;

import cms.access.Access_User;
import cms.cms.Factor;
import cms.tools.Payment;
import cms.tools.PaymentSetting;
import cms.tools.Server;
import cms.tools.ServerLog;
import cms.tools.jjTools;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjCalendar_EN;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;

/**
 *
 * @author Rashidi
 */
public class bpmPayment {

    public static long terminalId;//شماره پايانه پذيرنده
    public static String userName;//نام كاربري پذيرنده
    public static String userPassword;//كلمه عبور پذيرنده
    public static long orderId = 0;
    public static long amount = 0;
    public static String additionalData = "";
    public static String callBackUrl = "mellatPaymentResult.jsp";//request.getServletContext().getRealPath("/") +
    public static long payerId = 0;

    ////// ------------- bpmPayRequest() -------------    >
    public static String bpmPayRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            amount = Long.parseLong(jjTools.getParameter(request, Payment._amount));
            String factorId = jjTools.getSessionAttribute(request, "#FACTOR_ID").toLowerCase();//هنگام ذخیره شدن فاکتور آی دی آن در سشن ذخیره می شود
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            jjCalendar_IR dateIR = new jjCalendar_IR();
            int date = dateIR.getDBFormat_8length();
            orderId = Long.parseLong(userId + factorId + date);

            String webService = jjTools.getParameter(request, PaymentSetting._webService);
            String where = PaymentSetting._webService + "='" + webService + "'";
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName, where));//خواندن اطلاعات درگاه پرداخت از دیتابیس
            terminalId = Long.parseLong(row.get(0).get(PaymentSetting._terminalId).toString());
            userName = row.get(0).get(PaymentSetting._userName).toString();
            userPassword = row.get(0).get(PaymentSetting._pass).toString();

            jjCalendar_EN dateEN = new jjCalendar_EN();
            String urlParameters = "terminalId=" + terminalId + "&userName=" + userName + "&userPassword=" + userPassword + "&orderId=" + orderId + "&amount=" + amount + "&additionalData=" + additionalData + "&callBackUrl="+"modern-pc.ir/002/mellatPaymentResult.jsp&payerId=" + payerId + "&localDate=" + Integer.toString(dateEN.getDBFormat_8length()) + "&localTime=" + dateEN.getTimeFormat_7length().replaceAll(":", "");
//            String urlParameters = "terminalId=" + terminalId + "&userName=" + userName + "&userPassword=" + userPassword + "&orderId=" + getOrderId + "&amount=" + getAmount + "&additionalData=" + additionalData + "&callBackUrl=" + Server.siteName + "/002/" + callBackUrl + "&payerId=" + payerId + "&localDate=" + Integer.toString(dateEN.getDBFormat_8length()) + "&localTime=" + dateEN.getTimeFormat_7length().replaceAll(":", "");
            ServerLog.Print(urlParameters);
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String req = "http://sepanoshop.ir/?method=pay";
//            String req = "http://dfbyjh.tk/?method=pay";///////?????????
            URL url = new URL(req);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            conn.getOutputStream().write(postData);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }
            ServerLog.Print(sb.toString());
            return sb.toString() + "در حال انتقال به صفحه بانک...لطفا شکیبا باشید";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <------------- bpmPayRequest() -------------    
    ////// ------------- insert() ------------->
    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        return Payment.insert(request, db, isPost);
    }

    ////// <------------- insert() -------------    
    ////// ------------- refreshFactorStatus() ------------->
    public static String refreshFactorStatus(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        return Factor.refreshFactorStatus(request, db, isPost);
//       return Payment.refreshFactorStatus(request, db, isPost);
    }

    ////// <------------- refreshFactorStatus() -------------    
    ////// ------------- checkStatus() ------------->
    public static String checkStatus(int resCode) {
        try {
            String result = "";
            switch (resCode) {
                case 0:
                    result = "تراكنش با موفقيت انجام شد";
                    break;

                case 11:
                    result = "شماره كارت نامعتبر است";
                    break;
                case 12:
                    result = "موجودي كافي نيست";
                    break;

                case 13:
                    result = "رمز نادرست است";
                    break;
                case 14:
                    result = "تعداد دفعات وارد كردن رمز بيش از حد مجاز است";
                    break;
                case 15:
                    result = "كارت نامعتبر است";
                    break;
                case 16:
                    result = "دفعات برداشت وجه بيش از حد مجاز است";
                    break;
                case 17:
                    result = "كاربر از انجام تراكنش منصرف شده است";
                    break;
                case 18:
                    result = "تاريخ انقضاي كارت گذشته است";
                    break;
                case 19:
                    result = "مبلغ برداشت وجه بيش از حد مجاز است";
                    break;
                case 111:
                    result = "صادر كننده كارت نامعتبر است";
                    break;
                case 112:
                    result = "خطاي سوييچ صادر كننده كارت";
                    break;
                case 113:
                    result = "پاسخي از صادر كننده كارت دريافت نشد";
                    break;
                case 114:
                    result = "دارنده كارت مجاز به انجام اين تراكنش نيست";
                    break;
                case 21:
                    result = "پذيرنده نامعتبر است";
                    break;
                case 23:
                    result = "خطاي امنيتي رخ داده است";
                    break;
                case 24:
                    result = "اطلاعات كاربري پذيرنده نامعتبر است";
                    break;
                case 25:
                    result = "مبلغ نامعتبر است";
                    break;
                case 31:
                    result = "پاسخ نامعتبر است";
                    break;
                case 32:
                    result = "فرمت اطلاعات وارد شده صحيح نمي باشد";
                    break;
                case 33:
                    result = "حساب نامعتبر است";
                    break;
                case 35:
                    result = "خطاي سيستمي";
                    break;
                case 34:
                    result = "تاريخ نامعتبر است";
                    break;
                case 41:
                    result = "شماره درخواست تكراري است";
                    break;
                case 42:
                    result = "تراکنش sale یافت نشد";
                    break;
                case 43:
                    result = "قبلا درخواست داده شده است";
                    break;
                case 44:
                    result = "درخواست verify یافت نشد";
                    break;
                case 45:
                    result = "تراکنش  با موفقیت انجام شد و مبلغ واریز شد";
                    break;
                case 46:
                    result = "تراکنش settle نشده است ";
                    break;
                case 47:
                    result = "تراکنش settle یافت نشد ";
                    break;
                case 48:
                    result = "تراکنش reverseشده است ";
                    break;
                case 49:
                    result = "تراکنش refund یافت نشد ";
                    break;
                case 412:
                    result = "شناسه قبض نادرست است";
                    break;
                case 413:
                    result = "شناسه پرداخت نادرست است";
                    break;
                case 414:
                    result = "سازمان صادر كننده قبض نامعتبر است";
                    break;

                case 415:
                    result = "زمان جلسه كاري به پايان رسيده است";
                    break;
                case 416:
                    result = "خطا در ثبت اطلاعات";
                    break;
                case 417:
                    result = "شناسه پرداخت كننده نامعتبر است";
                    break;
                case 418:
                    result = "اشكال در تعريف اطلاعات مشتري";
                    break;
                case 419:
                    result = "تعداد دفعات ورود اطلاعات از حد مجاز گذشته است";
                    break;
                case 421:
                    result = "IP نامعتبر است";
                    break;
                case 51:
                    result = "تراكنش تكراري است";
                    break;
                case 54:
                    result = "تراكنش مرجع موجود نيست";
                    break;
                case 55:
                    result = "تراكنش نامعتبر است";
                    break;
                case 61:
                    result = "خطا در واريز";
                    break;
                default:
                    result = "خطا";
                    break;

            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    ////// <------------- checkStatus() -------------    

}
