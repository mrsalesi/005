/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarinpal;

import cms.access.Access_User;
import cms.cms.Factor;
import cms.cms.Tice_config;
import cms.tools.Js;
import cms.tools.Payment;
import cms.tools.PaymentSetting;
import cms.tools.Server;
import cms.tools.jjTools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mohammad
 */
public class Zarinpal {

    /**
     *
     */
    public static String MerchantID = "";//کد پذيرنده ضروری

    /**
     *
     */
    public static Integer Amount = 10000;//برحسب ریال ضروری

    /**
     *
     */
    public static String Description = "";//توضيحات مربوط تراكنش ضروری

    /**
     *
     */
    public static String Email;//ایمیل خریدار غیر ضروری

    /**
     *
     */
    public static String Mobile;//شماره تماس خریدار غیر ضروری

    /**
     *
     */
    public static String CallbackURL = Server.siteName + "/PaymentResultZarrinPal.jsp";//صفحه بازگشت مشتري پس از انجام عمل پرداخت ضروری

    ////// ------------- floritPaymentRequest() ------------->

    /**
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     */
        public static String ZarinPaymentRequest(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) {
        try {
            String userEmail = "";
            String userMobile = "";
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName, PaymentSetting._webService + "='zarinpal'"));
            if (row.isEmpty()) {
                return "تنظیمات درگاه در پایگاه داده را بررسی کنید";
            }
            MerchantID = row.get(0).get(PaymentSetting._terminalId).toString();
            String factorId = jjTools.getParameter(request, Payment._factorId);


//            Amount = 100;
            List<Map<String, Object>> tic_config = jjDatabaseWeb.separateRow(db.Select(Tice_config.tableName, Tice_config._config_Name + "='" + Tice_config._config_exchange_unite + "'"));
            if (tic_config.get(0).get(Tice_config._config_value).equals("ریال")) {
                Amount = Integer.parseInt(jjTools.getParameter(request, Payment._amount));
            } else if (tic_config.get(0).get(Tice_config._config_value).equals("تومان")) {
                Amount = Integer.parseInt(jjTools.getParameter(request, Payment._amount))*10;
            }
            URL getAuthorityUrl = new URL("https://api.zarinpal.com/pg/v4/payment/request.json");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("merchant_id", MerchantID);
            params.put("amount", 10000);
            params.put("callback_url", CallbackURL);
            params.put("description", "پرداخت");//توضیحات
            params.put("mobile", "");// موبایل کاربر
            params.put("email", "");//ایمیل کاربر

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpsURLConnection conn = (HttpsURLConnection) getAuthorityUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                System.out.print((char) c);
                result.append((char) c);
            }

            JSONObject jsonObj = new JSONObject(result.toString());
            String authority = jsonObj.getJSONObject("data").get("authority").toString();// کد یکتا برای هر رزداخت که یازده دقیقه اعتبار دارد
            int code = (int) jsonObj.getJSONObject("data").get("code");
            String fee_type = jsonObj.getJSONObject("data").get("fee_type").toString();
            String fee = jsonObj.getJSONObject("data").get("fee").toString();

            System.out.println(jsonObj);
            System.out.println(authority);
            System.out.println(code);
            System.out.println(fee_type);
            System.out.println(fee);

            if (code == 100) {// کد موفق بودن دریافت آثوریتی
                URL pamentPageURL = new URL("https://www.zarinpal.com/pg/StartPay/" + authority);
                //قراردادن یک رکورد در جدول تراکنش ها
                int userId = jjTools.getSeassionUserId(request);//اگر کاربر لاگین نکرده باشد مشکلی پیش نمیآید به عنوان کاربر میهان پرداخت انجام میشود
                String where = Access_User._id + "='" + userId + "'";
                List<Map<String, Object>> rowUser = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, where));
                Map<String, Object> map = new HashMap<String, Object>();
                jjCalendar_IR dateIR = new jjCalendar_IR();
                map.put(Payment._date, dateIR.getDBFormat_8length());
                map.put(Payment._orderId, request.getParameter(Payment._factorId));
                map.put(Payment._factorId, request.getParameter(Payment._factorId));
                map.put(Payment._userId, userId);
                map.put(Payment._factorId, request.getParameter(Payment._factorId));
                map.put(Payment._userName, rowUser.isEmpty() ? "" : (rowUser.get(0).get(Access_User._name).toString() + " " + rowUser.get(0).get(Access_User._family).toString()));
                map.put(Payment._refrenceId, authority);// آثوریتی را در رفرنس آی دی می گذاریم
                map.put(Payment._amount, request.getParameter(Payment._amount));
              map.put(Payment._status, Payment.status_sabteavalie);
                db.insert(Payment.tableName, map);
                            StringBuilder script = new StringBuilder();
                script.append("window.location.replace('" + pamentPageURL + "');");
                System.out.println(script);
                return script.toString();
            }
            String resultErr = checkStatus(code);
            return Js.dialog(resultErr);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    ////// <------------- floritPaymentRequest() -------------
    ////// ------------- checkStatus() ------------->

    /**
     *
     * @param statusCode
     * @return
     */
        public static String checkStatus(int statusCode) {
        String result = "";
        switch (statusCode) {
            case -1:
                result = "<h3 class='result' style='color: red;'>" + "اطلاعات ارسال شده ناقص است." + "</h3>";
                break;
            case -2:
                result = "<h3 class='result' style='color: red;'>" + "IP و یا مرچنت كد پذيرنده صحيح نیست." + "</h3>";
                break;
            case -3:
                result = "<h3 class='result' style='color: red;'>" + "با توجه به محدوديت هاي شاپرك امكان پرداخت با رقم درخواست شده ميسر نمي باشد." + "</h3>";
                break;
            case -4:
                result = "<h3 class='result' style='color: red;'>" + "سطح تاييد پذيرنده پايين تر از سطح نقره اي است." + "</h3>";
                break;
            case -9:
                result = "<h3 class='result' style='color: red;'>" + "خطای اعتبار سنجی." + "</h3>";
                break;
            case -10:
                result = "<h3 class='result' style='color: red;'>" + "ای پی و يا مرچنت كد پذيرنده صحيح نيست" + "</h3>";
                break;
            case -11:
                result = "<h3 class='result' style='color: red;'>" + "مرچنت کد فعال نیست لطفا با تیم پشتیبانی ما تماس بگیرید" + "</h3>";
                break;
            case -12:
                result = "<h3 class='result' style='color: red;'>" + "تلاش بیش از حد در یک بازه زمانی کوتاه." + "</h3>";
                break;
            case -15:
                result = "<h3 class='result' style='color: red;'>" + "ترمینال شما به حالت تعلیق در آمده با تیم پشتیبانی تماس بگیرید" + "</h3>";
                break;
            case -16:
                result = "<h3 class='result' style='color: red;'>" + "سطح تاييد پذيرنده پايين تر از سطح نقره اي است." + "</h3>";
                break;
            case -21:
                result = "<h3 class='result' style='color: red;'>" + "هيچ نوع عمليات مالي براي اين تراكنش يافت نشد." + "</h3>";
                break;
            case -22:
                result = "<h3 class='result' style='color: red;'>" + "تراكنش نا موفق مي باشد." + "</h3>";
                break;
            case -33:
                result = "<h3 class='result' style='color: red;'>" + "رقم تراكنش با رقم پرداخت شده مطابقت ندارد." + "</h3>";
                break;
            case -34:
                result = "<h3 class='result' style='color: red;'>" + "سقف تقسيم تراكنش از لحاظ تعداد يا رقم عبور نموده است" + "</h3>";
                break;
            case -40:
                result = "<h3 class='result' style='color: red;'>" + "اجازه دسترسي به متد مربوطه وجود ندارد." + "</h3>";
                break;
            case -41:
                result = "<h3 class='result' style='color: red;'>" + "اطلاعات ارسال شده مربوط به additional data غیر معتبر می باشد." + "</h3>";
                break;
            case -42:
                result = "<h3 class='result' style='color: red;'>" + "مدت زمان معتبر طول عمر شناسه پرداخت بايد بين 30 دقيه تا 45 روز مي باشد." + "</h3>";
                break;
            case -54:
                result = "<h3 class='result' style='color: red;'>" + "درخواست مورد نظر آرشيو شده است." + "</h3>";
                break;
            case 100:
                result = "<h3 class='result' style='color: rgb(25, 197, 147);'>" + "با تشکر از شما." + "</h3>";
                break;
            case 101:
                result = "<h3 class='result' style='color: red;'>" + "عمليات پرداخت موفق بوده و قبلا PaymentVerification تراکنش انجام شده است." + "</h3>";
                break;
        }
        return result;
    }

    ////// <------------- checkStatus() -------------

    /**
     *
     * @param args
     * @throws MalformedURLException
     * @throws IOException
     * @throws JSONException
     */
    
    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        URL getAuthorityUrl = new URL("https://api.zarinpal.com/pg/v4/payment/request.json");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("merchant_id", "72e8e9a3-b212-4732-bb73-29438abc4d2e");
        params.put("amount", "1000");
        params.put("callback_url", "http://medyarweb.com");
        params.put("description", "tesssst");//توضیحات
        params.put("mobile", "tesssst");// موبایل کاربر
        params.put("email", "tesssst");//ایمیل کاربر

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpsURLConnection conn = (HttpsURLConnection) getAuthorityUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        for (int c; (c = in.read()) >= 0;) {
            System.out.print((char) c);
            result.append((char) c);
        }

        JSONObject jsonObj = new JSONObject(result.toString());
        String authority = jsonObj.getJSONObject("data").get("authority").toString();// کد یکتا برای هر رزداخت که یازده دقیقه اعتبار دارد
        String code = jsonObj.getJSONObject("data").get("code").toString();
        String fee_type = jsonObj.getJSONObject("data").get("fee_type").toString();
        String fee = jsonObj.getJSONObject("data").get("fee").toString();

        System.out.println(jsonObj);
        System.out.println(authority);
        System.out.println(code);
        System.out.println(fee_type);
        System.out.println(fee);

        URL pamentPageURL = new URL("https://www.zarinpal.com/pg/StartPay/" + authority);

    }
}
