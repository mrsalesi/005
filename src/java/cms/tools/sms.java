/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

//import webServiceSms.SepahanGostar.SmsSepahanGostar;
import cms.access.Access_User;
import cms.cms.Tice_config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
//import ArrayOfInt;
//import ArrayOfLong;
//import ArrayOfString;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import static jj.jjNumber.isOdd;
//import org.tempuri.ArrayOfInt;
//import org.tempuri.ArrayOfLong;

/**
 *
 * @author Rashidi
 */
public class sms {

    public static String apiKey = "4D5539466B65356D742F763763646D396D6E5A4936413D3D";
    public static String webService = "kavenegar.com";
//    public static String userName = "mrsalesi";
//    public static String pass = "1234";
    public static String userName = "atefeh.rashidi@outlook.com";
    public static String pass = "12345";
    public static String sender = "300002525";

    public static String tableName = "sms";
    public static String _id = "id";
    public static String _text = "sms_text";
    public static String _sender = "sms_sender";
    public static String _receiver = "sms_receiver";
    public static String _characters = "sms_characters";
    public static String _status = "sms_status";
    public static String _date = "sms_date";
    public static String _sendTime = "sms_send_time";
    public static String _messageID = "sms_messageID";
    public static String _webService = "sms_webService";
    public static String _receiverId = "sms_receiver_id";
    public static String _receiverName = "sms_receiver_name";
    public static String _receiverFamily = "sms_receiver_family";
    public static String _comment = "sms_comment";
    public static String _subject = "sms_subject";
    public static String emptyField = "فیلدهای خالی را پر کنید.";
    public static String lbl_send = "ارسال پیامک";
//    public static int rul_rfs = 51;
//    public static int rul_ins = 52;
//    public static int rul_edt = 53;
//    public static int rul_dlt = 54;
//    public static int reserved=55;

    //   public static int rul_show_pics = 401;//نمایش تب پیامک//====== BY RASHIDI ======
//    public static int rul_show_SMS _reserved= 402 --- 420;// RESERVED : 402 -- 420//نمایش تب پیامک//====== BY RASHIDI ======
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt;
    public static int rul_dlt = 0;
//    public static int rul_rfs = 421;
//    public static int rul_ins = 422;
//    public static int rul_edt;
//    public static int rul_dlt = 423;
//    public static int rul_reserved = 424 --- 440;// RESERVED : 424 -- 440
////// ---------------- insert() ------------------->

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));

            html.append(" <div class=\"card-header bg-primary tx-white\">لیست پیامک ها</div>"
                    + "<a href='#' class='btn btn-outline-dark btn-icon mg-r-5'>"
                    + "<div style='font-size: 18px'><i class='icon ion-loop' onclick='cmsSMS.m_refresh();'></i>"
                    + "</div>"
                    + "</a>");
            html.append("<div class='table-wrapper'>");
            html.append("<table id='refreshSessions' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='20%'>messageid</th>");
            html.append("<th width='10%'>متن پیام</th>");
            html.append("<th width='10%'>موضوع پیام</th>");
            html.append("<th width='10%'>فرستنده</th>");
            html.append("<th width='10%'>گیرنده</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='15%'>نمایش</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {

                html.append("<tr onclick='cmsSMS.m_select(" + row.get(i).get(_id) + ");' class='p'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_messageID) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_text) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_subject) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_sender) + "</td>");
                html.append("<td class='r'>" + Access_User.getUserName(row.get(i).get(_receiverId).toString(), db) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                html.append("<td class='r'><i class='icon ion-coffee' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swSMSTbl";
            }
//            script +=Js.setHtml("#" + panel, html);
//            script +=Js.table("#smsTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "18" : "", "لیست پیامک ها");
//
//            script.append(Js.hide("#formDiv"));
//            script.append(Js.show("#tableDiv"));
//            script.append(Js.show("#showSendFormBtn"));
//            script.append(Js.hide("#smsTableShowBtn"));
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshSessions", "300", 0, "", "جلسات");

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

////// ---------------- refresh() ------------------->
//    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, hasAccess);
//                return "";
//            }
//
//            StringBuilder script = new StringBuilder();
////            StringBuilder html = new StringBuilder();
//            Map<String, Object> map = new HashMap<String, Object>();
//            //انتخاب پیامک هایی که وضعیت ان ها مشخص نیست هنوز در مخابرات هستند یا در صف ارسال هستند
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _status + " != 'رسیده به گیرنده' AND " + _status + " != 'نرسیده به گیرنده' AND " + _status + " != 'فیلتر شده' "));
////            ArrayOfInt smsStausKvNg = new ArrayOfInt();
//            ArrayOfLong smsMessageIdKvNg = new ArrayOfLong();
//            ArrayOfString smsStausSpKvNg = new ArrayOfString();
//
//            if (!row.isEmpty()) {
//                for (int i = 0; i < row.size(); i++) {
//                    smsMessageIdKvNg.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));//لیست کردن شناسه های پیامک ها که قبلن از کاوه نگار دریافت و ذخیره شدند
//                }
//                smsStausSpKvNg = SmsKaveNegar.getStatusByApiKey(smsMessageIdKvNg);//دریافت وضعیت پیامک ها از وب سرویس
//
//                for (int i = 0; i < row.size(); i++) { //آپدیت کردن وضعیت های دریافتی برای هر کدام از پیامک های انتخاب شده
//                    map.put(_status, smsStausSpKvNg.getString().get(i));
//                    if (!db.update(tableName, map, _id + " = " + row.get(i).get(_id))) {
//                        String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
//                        if (jjTools.isLangEn(request)) {
//                            errorMessage = "Edit Failed;";
//                        }
//                        Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessage));
//                        return "";
//                    }
//                }
//            }
//            row = jjDatabaseWeb.separateRow(db.JoinLeft(tableName, Access_User.tableName, "*", _receiverId, Access_User._id));//انتخاب تمام پیامک ها و یوزر مربوط به آن پیامک ها
//            StringBuilder tableBody = new StringBuilder();
//            String text = "";
////            String classRowType = "";
////            String smsStatus = "";
//            tableBody.append("<table id='smsTbl' class='tahoma10' style='direction: rtl;width:982px'><thead>");
////            tableBody.append("<tr class=\"tableHeader\">\n"
//            tableBody.append("<tr>\n"
//                    + "<th>کد</th>\n"
//                    + "<th>نام گیرنده</th>\n"
//                    + "<th>شماره گیرنده</th>\n"
//                    + "<th> فرستنده</th>\n"
//                    + "<th>متن پیامک</th>\n"
//                    + "<th>وضعیت</th>\n"
//                    + "<th> زمان ارسال پیامک</th>\n"
//                    + "<th>حذف </th>\n"
//                    + "<th>ارسال مجدد </th>\n"
//                    + "</tr>\n");
//            tableBody.append("</thead><tbody>");
//            for (int i = 0; i < row.size(); i++) {
//                //اگر متن پیامک بیشتر از ده کاراکتر بود
//                text = (row.get(i).get(_text).toString().length() > 10) ? row.get(i).get(_text).toString().substring(0, 10) + " ..." : row.get(i).get(_text).toString();
////                classRowType = isOdd(i) ? "oddRow" : "evenRow";
//                tableBody.append("<tr id=\"smsRowTr" + row.get(i).get(_messageID).toString() + "\" class='mousePointer'>\n"
//                        + "<td class='c'>" + row.get(i).get(_messageID).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_receiverName).toString() + " " + row.get(i).get(_receiverFamily).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_receiver).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_sender).toString() + "</td>\n"
//                        + "<td class='c' onmouseout=\"cmsSMS.hideFloatDiv(" + i + ");\" onmousemove=\"cmsSMS.showFloatDiv(" + i + ");\" >" + text.toString()
//                        + "<div class=\"floatBox\" id=\"floatBox" + i + "\">\n"
//                        + row.get(i).get(_text).toString()
//                        + "</div>\n"
//                        + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_status).toString() + "</td>\n"
//                        + "<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
//                        //                        + "<td id=\"smsWebServiceTd_" + row.get(i).get(_id).toString() + "\"> " + row.get(i).get(_webService).toString() + " </td>\n"
//                        + "<td class='c'><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick='cmsSMS.m_delete(" + row.get(i).get(_messageID) + ");'></td>\n"
//                        + "<td class='c'><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick='cmsSMS.m_select(" + row.get(i).get(_messageID) + ");'></td> "
//                        + " </tr>");
//            }
//            tableBody.append("</tbody></table>");
//            String height = jjTools.getParameter(request, "height");
//            String panel = jjTools.getParameter(request, "panel");
//            if (!jjNumber.isDigit(height)) {
//                height = "400";
//            }
//            if (panel.equals("")) {
//                panel = "swSMSTbl";
//            }
//            script.append(Js.setHtml("#" + panel, tableBody));
//            script.append(Js.table("#smsTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "18" : "", "لیست پیامک ها"));
//
////            script.append(Js.hide("#formDiv"));
////            script.append(Js.show("#tableDiv"));
////            script.append(Js.show("#showSendFormBtn"));
////            script.append(Js.hide("#smsTableShowBtn"));
//            Server.outPrinter(request, response, script.toString());
//            return "";
//        } catch (Exception ex) {
//            Server.outPrinter(request, response, Server.ErrorHandler(ex));
//            return "";
//        }
//    }
////// <---------------- refresh() -------------------
    ////// ---------------- refresh2() ------------------->
//    public static String refresh2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
////            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
////            if (!hasAccess.equals("")) {
////                return hasAccess;
////            }
//
//            StringBuilder script = new StringBuilder();
////            StringBuilder html = new StringBuilder();
//            Map<String, Object> map = new HashMap<String, Object>();
//            //انتخاب پیامک هایی که وضعیت ان ها مشخص نیست هنوز در مخابرات هستند یا در صف ارسال هستند
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _status + " != 'رسیده به گیرنده' AND " + _status + " != 'نرسیده به گیرنده' AND " + _status + " != 'فیلتر شده' "));
////            ArrayOfInt smsStausKvNg = new ArrayOfInt();
//            ArrayOfLong smsMessageIdKvNg = new ArrayOfLong();
//            ArrayOfString smsStausSpKvNg = new ArrayOfString();
////            org.tempuri.ArrayOfInt smsStausSpGs = new org.tempuri.ArrayOfInt();
//            org.tempuri.ArrayOfLong smsMessageIdSpGs = new org.tempuri.ArrayOfLong();
//            org.tempuri.ArrayOfString smsStausSpGs = new org.tempuri.ArrayOfString();
//            if (!row.isEmpty()) {
//                if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار بود
//                    for (int i = 0; i < row.size(); i++) {
//                        smsMessageIdKvNg.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));//لیست کردن شناسه های پیامک ها که قبلن از کاوه نگار دریافت و ذخیره شدند
//                    }
//                    smsStausSpKvNg = SmsKaveNegar.getStatusByApiKey(smsMessageIdKvNg);//دریافت وضعیت پیامک ها از وب سرویس
//                } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر بود
//                    for (int i = 0; i < row.size(); i++) {
//                        smsMessageIdSpGs.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));//لیست کردن شناسه های پیامک ها که قبلن از سپاهان گستر دریافت و ذخیره شدند
//                    }
//                    smsStausSpGs = SmsSepahanGostar.getStatus(smsMessageIdSpGs);//دریافت وضعیت پیامک ها از وب سرویس
//
//                }
//
//                for (int i = 0; i < row.size(); i++) { //آپدیت کردن وضعیت های دریافتی برای هر کدام از پیامک های انتخاب شده
//
//                    if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار بود
//                        ServerLog.Print("STATUS : " + smsStausSpKvNg.getString().get(i));
//                        map.put(_status, smsStausSpKvNg.getString().get(i));
//                    } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر بود
//                        ServerLog.Print("STATUS : " + smsStausSpGs.getString().get(i));
//                        map.put(_status, smsStausSpGs.getString().get(i));
//                    }
//                    if (!db.update(tableName, map, _id + " = " + row.get(i).get(_id))) {
//                        String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
//                        if (jjTools.isLangEn(request)) {
//                            errorMessage = "Edit Failed;";
//                        }
//                        return Js.dialog(errorMessage);
//                    }
//                }
//            }
//            row = jjDatabaseWeb.separateRow(db.JoinLeft(tableName, Access_User.tableName, "*", _receiverId, Access_User._id));//انتخاب تمام پیامک ها و یوزر مربوط به آن پیامک ها
//            StringBuilder tableBody = new StringBuilder();
//            String text = "";
//            String classRowType = "";
////            String smsStatus = "";
//            tableBody.append("<table id='smsTbl' class='tahoma10' style='direction: rtl;width:982px'><thead>");
////            tableBody.append("<tr class=\"tableHeader\">\n"
//            tableBody.append("<tr class=\"tableHeader\">\n"
//                    + "<th>کد</th>\n"
//                    + "<th>نام گیرنده</th>\n"
//                    + "<th>شماره گیرنده</th>\n"
//                    + "<th> فرستنده</th>\n"
//                    + "<th>متن پیامک</th>\n"
//                    + "<th>وضعیت</th>\n"
//                    + "<th> زمان ارسال پیامک</th>\n"
//                    + "<th>حذف </th>\n"
//                    + "<th>ارسال مجدد </th>\n"
//                    + "</tr>\n");
//            tableBody.append("</thead><tbody>");
//            for (int i = 0; i < row.size(); i++) {
//                //اگر متن پیامک بیشتر از ده کاراکتر بود
//                text = (row.get(i).get(_text).toString().length() > 10) ? row.get(i).get(_text).toString().substring(0, 10) + " ..." : row.get(i).get(_text).toString();
//                classRowType = isOdd(i) ? "oddRow" : "evenRow";
//                tableBody.append("<tr id=\"smsRowTr" + row.get(i).get(_messageID).toString() + "\" class=\"" + classRowType + "\" class='mousePointer'>\n"
//                        + "<td class='c'>" + row.get(i).get(_messageID).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_receiverName).toString() + " " + row.get(i).get(_receiverFamily).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_receiver).toString() + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_sender).toString() + "</td>\n"
//                        + "<td class='c' onmouseout=\"hideBox(0," + i + ");\" onmousemove=\"showBox(0," + i + ");\" >" + text.toString()
//                        + "<div class=\"floatBox\" id=\"floatBox_0" + i + "\">\n"
//                        + row.get(i).get(_text).toString()
//                        + "</div>\n"
//                        + "</td>\n"
//                        + "<td class='c'>" + row.get(i).get(_status).toString() + "</td>\n"
//                        + "<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
//                        //                        + "<td id=\"smsWebServiceTd_" + row.get(i).get(_id).toString() + "\"> " + row.get(i).get(_webService).toString() + " </td>\n"
//                        + "<td class='c'><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick='cmsSMS.m_delete(" + row.get(i).get(_messageID) + ");'></td>\n"
//                        + "<td class='c'><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick='cmsSMS.m_select(" + row.get(i).get(_messageID) + ");'></td> "
//                        + " </tr>");
//            }
//            tableBody.append("</tbody></table>");
//            String height = jjTools.getParameter(request, "height");
//            String panel = jjTools.getParameter(request, "panel");
//            if (!jjNumber.isDigit(height)) {
//                height = "400";
//            }
//            if (panel.equals("")) {
//                panel = "swSMSTbl";
//            }
//            script.append(Js.setHtml(panel, tableBody));
//            script.append(Js.table("#smsTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "18" : "", "لیست پیامک ها"));
//
////            script.append(Js.hide("#formDiv"));
////            script.append(Js.show("#tableDiv"));
////            script.append(Js.show("#showSendFormBtn"));
////            script.append(Js.hide("#smsTableShowBtn"));
//            return script.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
////// <---------------- refresh2() -------------------
    ////// ---------------- delete() ------------------->
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }
            StringBuilder script = new StringBuilder();
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessageId));
                return "";
            }
            if (!db.delete(tableName, _messageID + " = " + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessage));
            }
//            script.append(Js.hide("#smsRowTr" + id));
            script.append("$('#smsRowTr" + id + "').remove();");
            Server.outPrinter(request, response, script.toString());
            return "";
//            return refresh(request, db, isPost);

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
////// <---------------- delete() -------------------
    ////// ---------------- select() ------------------->

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
//            ServerLog.Print(">>> select");
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _messageID + " = " + jjTools.getParameter(request, _id)));
            if (row.isEmpty()) {
                ServerLog.Print("این رکورد وجود ندارد.");
                Server.outPrinter(request, response, Js.modal("پیام سامانه", "این رکورد وجود ندارد"));
                return Js.dialog(".");
            }
            script.append(smsSetting.selectWebService(request, response, db, needString));
            script.append(Js.setVal(_receiverName, row.get(0).get(_receiverName)));
            script.append(Js.setVal(_receiverFamily, row.get(0).get(_receiverFamily)));
            String date = jjCalendar_IR.getViewFormat(row.get(0).get(_date)) + " - " + row.get(0).get(_sendTime);
//            script.append(Js.setVal(_date, date));
            script.append(Js.setVal(_receiver, row.get(0).get(_receiver)));
            script.append(Js.setVal(_text, row.get(0).get(_text)));
            script.append(Js.setVal(_comment, row.get(0).get(_comment)));
            script.append(Js.setVal(_subject, row.get(0).get(_subject)));
//            script.append(Js.setHtml(_characters, row.get(0).get(_characters)));
//            script.append(Js.setHtml("#SMS_button", "<input type=\"button\" id=\"insert_SMS_new\" value=\"" + lbl_send + "\" class=\"tahoma10\">"));
            script.append(add_new(request, response, db, needString));
//            ServerLog.Print(script.toString());
//            request.setAttribute("panel_webServices",_webService);
//            request.setAttribute("panel_numbers",_sender);
//            script.append(smsSetting.selectWebService(request, db, isPost));
            ServerLog.Print(script.toString());
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <---------------- select() -------------------
    ////// ---------------- add_new() ------------------->
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(smsSetting.selectWebService(request, response, db, needString));
                html.append(Js.setHtml("#SMS_button", "<input type=\"button\" id=\"insert_SMS_new\" value=\"" + lbl_send + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_SMS_new", Js.jjSMS.insert()));//send sms
                html.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())));//send sms
                html.append(Js.setVal("#" + _status, "ارسال نشده، در حال ایجاد توسط کاربر..."));//send sms
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sendMessageByApi(HttpServletRequest request, jjDatabaseWeb db, String receptor, String message, String date, String type, String localid) throws IOException, SQLException, Exception {
        try {
            String apiKey = Tice_config.getValue(db, Tice_config._config_apiKey_sms_name);
            String sender = Tice_config.getValue(db, Tice_config._config_smsNumber_name);
            URL url = new URL("https://api.kavenegar.com/v1/" + apiKey + "/sms/send.json?receptor=" + receptor + "&sender="+sender+"&message=" + URLEncoder.encode(message, "UTF-8") + ""
            );// 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//خط بالا با استفاده از اچ تی تی پی یو ار ال کانکشن می توان صفخه وب را باز کرد و اطلاعات ان را داخل کانکشن بریزیم  
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //کد بالا اطلاعات گرفته از صفحه وب را که ممکن است هرچیزی باشد را به استرینگ تبدیل می کند ودر اااین این ذخیره می شود
            String inputLine;
            String result = "[";
            //در جی سان رشته با براکت باز آغاز می شود وبا براکت بسته پایان می یابد ولی تلگرام ازاین قاعده پیروی نمی کند برای همین باید براکت باز وبسته را در اول اخر ان تعریف کرد.
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
                //اگر این اطلاعاتی که به استرینگ تبدیل کردیم داخلش نال نبود به ریزالت اضافه کن 
            }
            result += "]";
            in.close();
            System.out.println("----------------------------------------");
            System.out.println(result);
            System.out.println("--------------------------------");
            JSONArray array;
// اطلاعاتی که داخل یو آر ال می باشد با فرمت جی سان می باشد این اطلاعات را داخل یک 
//متغیری از جی سان به نام array  تعری0ف میکنیم
            array = new JSONArray(result);
            JSONObject jsonObj = array.getJSONObject(0);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println(jsonObj.toString());//
            System.out.println(jsonObj.get("return"));
            JSONArray entries = jsonObj.getJSONArray("entries");
            System.out.println("messageid=" + entries.getJSONObject(0).get("messageid"));
            System.out.println("date=" + entries.getJSONObject(0).get("date"));
            System.out.println("message=" + entries.getJSONObject(0).get("message"));
            System.out.println("cost=" + entries.getJSONObject(0).get("cost"));
            System.out.println("status=" + entries.getJSONObject(0).get("status"));
            System.out.println("statustext=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("message=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("sender=" + entries.getJSONObject(0).get("sender"));
            //برای گرفتن اطلاعات کلید دوم از گت استفاده میکنیم 
            System.out.println("++++++++++++++++++++++++++++++++");
            int status = Integer.valueOf(jsonObj.getJSONObject("return").get("status").toString());
            List<Map<String, Object>> userIdRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, "user_mobile   LIKE '%" + receptor + "%'"));
            Map<String, Object> smsMap = new HashMap<>();
            if (userIdRow.size() == 1) {//اگر آیدی یکی بود در جدول ثبت شود هم نام و هم فامیلی
                int userId = Integer.valueOf(userIdRow.get(0).get(Access_User._id).toString());
                smsMap.put(_receiverId, userId);
                smsMap.put(_receiverName, userIdRow.get(0).get(Access_User._name));
                smsMap.put(_receiverFamily, userIdRow.get(0).get(Access_User._family));
            }

            String resultStatus = checkStatus(status);
            smsMap.put(_messageID, entries.getJSONObject(0).get("messageid"));
            smsMap.put(_receiver, receptor);
            smsMap.put(_date, entries.getJSONObject(0).get("date"));
            smsMap.put(_text, message);
            smsMap.put(_sender, entries.getJSONObject(0).get("sender"));
            smsMap.put(_status, resultStatus);
            db.insert(sms.tableName, smsMap);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("آدرس وب نامعتبر"); //اگر یو ار ال درست نباشد یا به اینترنت وصل نباشیم این اررور را به ما می دهد
            ex.printStackTrace();
        } catch (JSONException ex) {
            System.out.println("json error");
            ex.printStackTrace();
        }
        return "";
    }

    public static String sendCallByApi(HttpServletRequest request, jjDatabaseWeb db, String receptor, String message, String date, String type, String localid) throws IOException, SQLException, Exception {
        try {
            URL url = new URL("https://api.kavenegar.com/v1/2F576E315A7562634A38303670774648467057506E396B30414C4A4D357A4D65734663682B465A443776673D/call/maketts.json?receptor=" + receptor + "&&message=" + URLEncoder.encode(message, "UTF-8") + "");// 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//خط بالا با استفاده از اچ تی تی پی یو ار ال کانکشن می توان صفخه وب را باز کرد و اطلاعات ان را داخل کانکشن بریزیم  
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //کد بالا اطلاعات گرفته از صفحه وب را که ممکن است هرچیزی باشد را به استرینگ تبدیل می کند ودر اااین این ذخیره می شود
            String inputLine;
            String result = "[";
            //در جی سان رشته با براکت باز آغاز می شود وبا براکت بسته پایان می یابد ولی تلگرام ازاین قاعده پیروی نمی کند برای همین باید براکت باز وبسته را در اول اخر ان تعریف کرد.
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
                //اگر این اطلاعاتی که به استرینگ تبدیل کردیم داخلش نال نبود به ریزالت اضافه کن 
            }
            result += "]";
            in.close();
            System.out.println("----------------------------------------");
            System.out.println(result);
            System.out.println("--------------------------------");
            JSONArray array;
// اطلاعاتی که داخل یو آر ال می باشد با فرمت جی سان می باشد این اطلاعات را داخل یک 
//متغیری از جی سان به نام array  تعری0ف میکنیم
            array = new JSONArray(result);
            JSONObject jsonObj = array.getJSONObject(0);

            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println(jsonObj.toString());//
            System.out.println(jsonObj.get("return"));
            JSONArray entries = jsonObj.getJSONArray("entries");
            System.out.println("messageid=" + entries.getJSONObject(0).get("messageid"));
            System.out.println("date=" + entries.getJSONObject(0).get("date"));
            System.out.println("message=" + entries.getJSONObject(0).get("message"));
            System.out.println("cost=" + entries.getJSONObject(0).get("cost"));
            System.out.println("status=" + entries.getJSONObject(0).get("status"));
            System.out.println("statustext=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("message=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("sender=" + entries.getJSONObject(0).get("sender"));
            //برای گرفتن اطلاعات کلید دوم از گت استفاده میکنیم 
            System.out.println("++++++++++++++++++++++++++++++++");
            int status = Integer.valueOf(jsonObj.getJSONObject("return").get("status").toString());
            List<Map<String, Object>> userIdRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, "user_mobile   LIKE '%" + receptor + "%'"));
            Map<String, Object> smsMap = new HashMap<>();
            if (userIdRow.size() == 1) {//اگر آیدی یکی بود در جدول ثبت شود هم نام و هم فامیلی
                int userId = Integer.valueOf(userIdRow.get(0).get(Access_User._id).toString());
                smsMap.put(_receiverId, userId);
                smsMap.put(_receiverName, userIdRow.get(0).get(Access_User._name));
                smsMap.put(_receiverFamily, userIdRow.get(0).get(Access_User._family));
            }

            String resultStatus = checkStatus(status);
            smsMap.put(_messageID, entries.getJSONObject(0).get("messageid"));
            smsMap.put(_receiver, receptor);
            smsMap.put(_date, entries.getJSONObject(0).get("date"));
            smsMap.put(_text, message);
            smsMap.put(_sender, entries.getJSONObject(0).get("sender"));
            smsMap.put(_status, resultStatus);
            db.insert(sms.tableName, smsMap);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("آدرس وب نامعتبر"); //اگر یو ار ال درست نباشد یا به اینترنت وصل نباشیم این اررور را به ما می دهد
            ex.printStackTrace();
        } catch (JSONException ex) {
            System.out.println("json error");
            ex.printStackTrace();
        }
        return "";
    }

    public static String checkStatus(int statusCode) {

        String result = "";
        switch (statusCode) {
            case 200:
                result = "درخواست تایید شد";
                break;
            case 400:
                result = "پارامترها ناقص هستند";
                break;
            case 401:
                result = "حساب کاربری غیرفعال شده است";
                break;
            case 402:
                result = "عملیات ناموفق بود";
                break;
            case 403:
                result = "کد شناسائی API-Key معتبر نمی‌باشد";
                break;
            case 404:
                result = "متد نامشخص است";
                break;
            case 405:
                result = "متد Get/Post اشتباه است";
                break;
            case 406:
                result = "پارامترهای اجباری خالی ارسال شده اند";
                break;
            case 407:
                result = "دسترسی به اطلاعات مورد نظر برای شما امکان پذیر نیست";
                break;
            case 409:
                result = "سرور قادر به پاسخگوئی نیست بعدا تلاش کنید";
                break;
            case 411:
                result = "دریافت کننده نامعتبر است";
                break;
            case 412:
                result = "ارسال کننده نامعتبر است";
                break;
            case 413:
                result = "پیام خالی است و یا طول پیام بیش از حد مجاز می‌باشد. لاتین  ﻛﺎراﻛﺘﺮ و ﻓﺎرﺳﻲ 268 ﻛﺎراﻛﺘﺮ";
                break;
            case 414:
                result = "حجم درخواست بیشتر از حد مجاز است ،ارسال پیامک :هر فراخوانی حداکثر 200 رکوردو کنترل وضعیت :هر فراخوانی 3000 رکورد";
                break;
            case 415:
                result = "	اندیس شروع بزرگ تر از کل تعداد شماره های مورد نظر است";
                break;
            case 417:
                result = "	تاریخ ارسال اشتباه است و فرمت آن صحیح نمی باشد.";
                break;
            case 418:
                result = "اعتبار شما کافی نمی‌باشد";
                break;
            case 419:
                result = "طول آرایه متن و گیرنده و فرستنده هم اندازه نیست";
                break;
            case 422:
                result = "داده ها به دلیل وجود کاراکتر نامناسب قابل پردازش نیست";
                break;
            case 424:
                result = "الگوی مورد نظر پیدا نشد";
                break;
            case 426:
                result = "استفاده از این متد نیازمند سرویس پیشرفته می‌باشد";
                break;
            case 428:
                result = "ارسال کد از طریق تماس تلفنی امکان پذیر نیست";
                break;
            case 431:
                result = "ساختار کد صحیح نمی‌باشد";
                break;
            case 432:
                result = "پارامتر کد در متن پیام پیدا نشد";
                break;

        }
        return result;
    }

}
