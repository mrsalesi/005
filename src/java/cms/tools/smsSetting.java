/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.access.Access_User;
//import webServiceSms.SepahanGostar.SmsSepahanGostar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import static jj.jjNumber.isOdd;

/**
 *
 * @author Rashidi
 */
public class smsSetting {

    public static String tableName = "sms_setting";
    public static String _id = "id";
    public static String _apiKey = "smsSetting_apiKey";
    public static String _userName = "smsSetting_userName";
    public static String _pass = "smsSetting_pass";
    public static String _senderNumber = "smsSetting_exclusive_numbers";
    public static String _webService = "smsSetting_webService";
    public static String _wsdl = "smsSetting_wsdl";
    public static String _domain = "smsSetting_domain";
    public static String _comment = "smsSetting_comment";
//    public static String _credit = "smsSetting_credit";
    public static String emptyField = "فیلدهای خالی را پر کنید.";
    public static String lbl_edit = "ویرایش";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
//    public static int rul_rfs;
//    public static int rul_ins;
//    public static int rul_edt;
//    public static int rul_dlt;

    public static int rul_rfs = 441;
    public static int rul_ins;
    public static int rul_edt = 442;
    public static int rul_dlt;
////    public static int rul_reserved = 443 --- 460;// RESERVED : 443 -- 460
    ///---------------------------- refresh ------------------>
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
//            ServerLog.Print(">>> refresh");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
            script.append(Js.setVal("#smsSetting_webService", row.get(0).get(_webService)));
            script.append(Js.setVal("#smsSetting_wsdl", row.get(0).get(_wsdl)));
            script.append(Js.setVal("#smsSetting_exclusive_numbers", row.get(0).get(_senderNumber)));
            script.append(Js.setVal("#smsSetting_apiKey", row.get(0).get(_apiKey)));
            script.append(Js.setVal("#smsSetting_userName", row.get(0).get(_userName)));
            script.append(Js.setVal("#smsSetting_pass", row.get(0).get(_pass)));
            script.append(Js.setVal("#smsSetting_domain", row.get(0).get(_domain)));
            script.append(Js.setVal("#sms_id", row.get(0).get(_id)));
            script.append(Js.setVal("#" + _comment, row.get(0).get(_comment)));
//            script.append(Js.setHtml(panel, tableBody));
//            script.append(Js.table("#smsSettingTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "23" : "", "لیست تنظیمات پیامک ها"));
            return script.toString();
//            return html.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- refresh ------------------
    ///---------------------------- refresh2 ------------------>
    public static String refresh2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
//            ServerLog.Print(">>> refresh");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
//            if (row.isEmpty()) {
//                ServerLog.Print("هیچ رکوردی وجود ندارد.");
//                return Js.dialog("هیچ رکوردی وجود ندارد.");
//            }
            StringBuilder tableBody = new StringBuilder();
            String classRowType = "";
            String apiKey = "";
            String senderNumbers = "";
            String wsdl = "";
            tableBody.append("<table id='smsSettingTbl' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            tableBody.append("<tr class=\"tableHeader\">\n"
                    + "<th>سرویس دهنده</th>\n"
                    + "<th> کد امنیتی</th>\n"
                    + "<th>نام کاربری</th>\n"
                    + "<th>گذرواژه</th>\n"
                    + "<th>نام دامنه</th>\n"
                    + "<th>شماره های اختصاصی</th>\n"
                    + "<th>WSDL</th>\n"
                    + "<th>مانده حساب</th>\n"
                    + "<th>  حذف</th>\n"
                    + "<th>  ویرایش</th>\n"
                    + "</tr>\n");
            tableBody.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                apiKey = (row.get(i).get(_apiKey).toString().length() > 10) ? row.get(i).get(_apiKey).toString().substring(0, 10) + " ..." : row.get(i).get(_apiKey).toString();
                senderNumbers = (row.get(i).get(_senderNumber).toString().length() > 10) ? row.get(i).get(_senderNumber).toString().substring(0, 10) + " ..." : row.get(i).get(_senderNumber).toString();
                wsdl = (row.get(i).get(_wsdl).toString().length() > 10) ? row.get(i).get(_wsdl).toString().substring(0, 10) + " ..." : row.get(i).get(_wsdl).toString();
                classRowType = isOdd(i) ? "oddRow" : "evenRow";
                tableBody.append("<tr id=\"settingRowTr" + row.get(i).get(_id).toString() + "\" class=\"" + classRowType + "\">\n"
                        + "<td>" + row.get(i).get(_webService).toString() + "</span></td>\n"
                        + "<td onmouseout=\"hideBox(1," + row.get(i).get(_id) + ");\" onmousemove=\"showBox(1," + row.get(i).get(_id) + ");\"> " + apiKey
                        + "<div class=\"floatBox\" style=\"right:15%;\" id=\"floatBox_1" + row.get(i).get(_id) + "\">\n"
                        + row.get(i).get(_apiKey).toString()
                        + "</div>\n"
                        + "</td>\n"
                        + "<td>" + row.get(i).get(_userName).toString() + "</td>\n"
                        + "<td>" + row.get(i).get(_pass).toString() + "</td>\n"
                        + "<td>" + row.get(i).get(_domain).toString() + "</td>\n"
                        + "<td onmouseout=\"hideBox(2," + row.get(i).get(_id) + ");\" onmousemove=\"showBox(2," + row.get(i).get(_id) + ");\">" + senderNumbers
                        + "<div class=\"floatBox\" style=\"right:60%;\" id=\"floatBox_2" + row.get(i).get(_id) + "\">\n"
                        + row.get(i).get(_senderNumber).toString()
                        + "</div>\n"
                        + " </td>\n"
                        + "<td onmouseout=\"hideBox(3," + row.get(i).get(_id) + ");\" onmousemove=\"showBox(3," + row.get(i).get(_id) + ");\">" + wsdl
                        + "<div class=\"floatBox\" style=\"right:65%;\" id=\"floatBox_3" + row.get(i).get(_id) + "\">\n"
                        + row.get(i).get(_wsdl).toString()
                        + "</div>\n"
                        + " </td>\n"
                        + "<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"deleteSettingRow(" + row.get(i).get(_id) + ");\" ></td>\n"
                        + "<td><img class=\"iconImages\" src=\"iconImages/settingIcon.png\" onclick=\"editdRow(" + row.get(i).get(_id) + ");\" ></td> "
                        + " </tr>");
            }
            tableBody.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swSettingSMSTbl";
            }
//            html.append(Js.hide("#formSettingDiv"));
//            html.append(Js.show("#tableSettingDiv"));
//            script.append(Js.setHtml("#allSettingTbl", tableBody));
//            script.append(Js.setHtml(panel, tableBody));
//            script.append(Js.table("#smsSettingTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "23" : "", "لیست تنظیمات پیامک ها"));
            return script.toString();
//            return html.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- refresh2 ------------------
    ///---------------------------- insert ------------------>
    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
//            ServerLog.Print(">>> INSERT");
            Map<String, Object> map = new HashMap<String, Object>();

//            map.put(_id, 1);
            if (jjTools.getParameter(request, _apiKey).equalsIgnoreCase("") || jjTools.getParameter(request, _userName).equalsIgnoreCase("") || jjTools.getParameter(request, _pass).equalsIgnoreCase("") || jjTools.getParameter(request, _senderNumber).equalsIgnoreCase("") || jjTools.getParameter(request, _webService).equalsIgnoreCase("") || jjTools.getParameter(request, _wsdl).equalsIgnoreCase("")) {
                return Js.dialog(emptyField);
            }
//            ServerLog.Print("PARAMETERS : " + jjTools.getParameter(request, _apiKey) + "     " + jjTools.getParameter(request, _userName) + "    " + jjTools.getParameter(request, _pass) + "    " + jjTools.getParameter(request, _senderNumber) + "    " + jjTools.getParameter(request, _webService) + "      " + jjTools.getParameter(request, _wsdl));
            map.put(_apiKey, jjTools.getParameter(request, _apiKey));
            map.put(_userName, jjTools.getParameter(request, _userName));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_senderNumber, jjTools.getParameter(request, _senderNumber));
            map.put(_webService, jjTools.getParameter(request, _webService));
            map.put(_wsdl, jjTools.getParameter(request, _wsdl));
            map.put(_domain, jjTools.getParameter(request, _domain));
            map.put(_comment, jjTools.getParameter(request, _comment));
            if (db.insert(tableName, map).getRowCount() < 1) {
                String errorMessage = "عملیات به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Insert Failed;";
                }
                return Js.dialog(errorMessage);
            }
//            script.append(Js.setVal("#smsSetting_webService", ""));
//            script.append(Js.setVal("#smsSetting_apiKey", ""));
//            script.append(Js.setVal("#smsSetting_userName", ""));
//            script.append(Js.setVal("#smsSetting_pass", ""));
//            script.append(Js.setVal("#smsSetting_wsdl", ""));
//            script.append(Js.setVal("#smsSetting_exclusive_numbers", ""));
            script.append(Js.show("#tableSettingDiv"));
            script.append(Js.hide("#formSettingDiv"));
            refresh(request, db, isPost);
//            return refresh(request, db, isPost);
            return script.toString();
//            return Js.jjSmsSetting.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- insert ------------------
    ///---------------------------- edit ------------------>
    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            if (jjTools.getParameter(request, _apiKey).equalsIgnoreCase("") || jjTools.getParameter(request, _userName).equalsIgnoreCase("") || jjTools.getParameter(request, _pass).equalsIgnoreCase("") || jjTools.getParameter(request, _senderNumber).equalsIgnoreCase("") || jjTools.getParameter(request, _webService).equalsIgnoreCase("") || jjTools.getParameter(request, _wsdl).equalsIgnoreCase("")) {
                return Js.dialog(emptyField);
            }
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
//            ServerLog.Print(">>> EDIT");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_apiKey, jjTools.getParameter(request, _apiKey));
            map.put(_webService, jjTools.getParameter(request, _webService));
            map.put(_userName, jjTools.getParameter(request, _userName));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_senderNumber, jjTools.getParameter(request, _senderNumber));
            map.put(_wsdl, jjTools.getParameter(request, _wsdl));
            map.put(_domain, jjTools.getParameter(request, _domain));
            map.put(_comment, jjTools.getParameter(request, _comment));
            String errorMessage = "";
            if (!db.update(tableName, map, _id + " = " + jjTools.getParameter(request, _id))) {
                errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Failed;";
                }
            } else {
                errorMessage = "ویرایش به درستی صورت پذیرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edited;";
                }
            }
            return Js.dialog(errorMessage);
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + jjTools.getParameter(request, _id)));
//
////            if (row.isEmpty()) {
////                ServerLog.Print("این رکورد وجود ندارد.");
////                return Js.dialog("این رکورد وجود ندارد.");
////            }
////            return refresh(request, db, isPost);
//            return script.toString();
////            return Js.jjSmsSetting.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- edit ------------------
    ///---------------------------- edit2 ------------------>
    public static String edit2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            if (jjTools.getParameter(request, _apiKey).equalsIgnoreCase("") || jjTools.getParameter(request, _userName).equalsIgnoreCase("") || jjTools.getParameter(request, _pass).equalsIgnoreCase("") || jjTools.getParameter(request, _senderNumber).equalsIgnoreCase("") || jjTools.getParameter(request, _webService).equalsIgnoreCase("") || jjTools.getParameter(request, _wsdl).equalsIgnoreCase("")) {
                return Js.dialog(emptyField);
            }
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
//            ServerLog.Print(">>> EDIT");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_apiKey, jjTools.getParameter(request, _apiKey));
            map.put(_webService, jjTools.getParameter(request, _webService));
            map.put(_userName, jjTools.getParameter(request, _userName));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_senderNumber, jjTools.getParameter(request, _senderNumber));
            map.put(_wsdl, jjTools.getParameter(request, _wsdl));
            map.put(_domain, jjTools.getParameter(request, _domain));
            if (!db.update(tableName, map, _id + " = " + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Failed;";
                }
                return Js.dialog(errorMessage);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + jjTools.getParameter(request, _id)));
            if (row.isEmpty()) {
                ServerLog.Print("این رکورد وجود ندارد.");
                return Js.dialog("این رکورد وجود ندارد.");
            }
            String apiKey = "";
            String senderNumbers = "";
            String wsdl = "";
            apiKey = (row.get(0).get(_apiKey).toString().length() > 10) ? row.get(0).get(_apiKey).toString().substring(0, 10) + " ..." : row.get(0).get(_apiKey).toString();
            senderNumbers = (row.get(0).get(_senderNumber).toString().length() > 10) ? row.get(0).get(_senderNumber).toString().substring(0, 10) + " ..." : row.get(0).get(_senderNumber).toString();
            wsdl = (row.get(0).get(_wsdl).toString().length() > 10) ? row.get(0).get(_wsdl).toString().substring(0, 10) + " ..." : row.get(0).get(_wsdl).toString();
            html.append("<td>").append(row.get(0).get(_webService)).append("</td>");
            html.append("<td onmouseout=\"hideBox(1," + row.get(0).get(_id).toString() + ");\" onmousemove=\"showBox(1," + row.get(0).get(_id).toString() + ");\">").append(apiKey).append("<div class=\"floatBox\" style=\"right:15%;\" id=\"floatBox_1" + row.get(0).get(_id).toString() + "\" >\n"
                    + row.get(0).get(_apiKey).toString()
                    + "</div></td>");
            html.append("<td>").append(row.get(0).get(_userName)).append("</td>");
            html.append("<td>").append(row.get(0).get(_pass)).append("</td>");
            html.append("<td>").append(row.get(0).get(_domain)).append("</td>");
            html.append("<td onmouseout=\"hideBox(2," + row.get(0).get(_id).toString() + ");\" onmousemove=\"showBox(2," + row.get(0).get(_id).toString() + ");\">").append(senderNumbers).append("<div class=\"floatBox\" style=\"right:60%;\" id=\"floatBox_2" + row.get(0).get(_id).toString() + "\" >\n"
                    + row.get(0).get(_senderNumber).toString()
                    + "</div></td>");
            html.append("<td onmouseout=\"hideBox(3," + row.get(0).get(_id).toString() + ");\" onmousemove=\"showBox(3," + row.get(0).get(_id).toString() + ");\">").append(wsdl).append("<div class=\"floatBox\" style=\"right:65%;\" id=\"floatBox_3" + row.get(0).get(_id).toString() + "\" >\n"
                    + row.get(0).get(_wsdl).toString()
                    + "</div></td>");
            html.append("<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"deleteSettingRow(").append(row.get(0).get(_id)).append(");\" ></td>");
            html.append("<td><img class=\"iconImages\" src=\"iconImages/settingIcon.png\" onclick=\"editdRow(").append(row.get(0).get(_id)).append(");\" ></td>");
            script.append(Js.show("#tableSettingDiv"));
            script.append(Js.hide("#formSettingDiv"));
            script.append(Js.setHtml("#settingRowTr" + row.get(0).get(_id).toString(), html)
            );
//            return refresh(request, db, isPost);
            return script.toString();
//            return Js.jjSmsSetting.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- edit2 ------------------
    ///---------------------------- select ------------------>
    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + jjTools.getParameter(request, _id)));
            if (row.isEmpty()) {
                ServerLog.Print("این رکورد وجود ندارد.");
                return Js.dialog("این رکورد وجود ندارد.");
            }
            script.append(Js.setVal(_webService, row.get(0).get(_webService)));
            script.append(Js.setVal(_apiKey, row.get(0).get(_apiKey)));
            script.append(Js.setVal(_userName, row.get(0).get(_userName)));
            script.append(Js.setVal(_pass, row.get(0).get(_pass)));
            script.append(Js.setVal(_senderNumber, row.get(0).get(_senderNumber)));
            script.append(Js.setVal(_wsdl, row.get(0).get(_wsdl)));
            script.append(Js.setVal(_domain, row.get(0).get(_domain)));
            script.append(Js.setVal(_comment, row.get(0).get(_comment)));
            script.append(Js.setVal("#smsSettingRowId", row.get(0).get(_id)));
//            ServerLog.Print(script.toString());
            return script.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    ///<---------------------------- select ------------------

    ///---------------------------- delete ------------------>
    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
//            ServerLog.Print(">>> DELETE");
            if (!db.delete(tableName, _id + " = " + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Failed;";
                }
                return Js.dialog(errorMessage);
            }
//            script.append(Js.hide("#settingRow_" + jjTools.getParameter(request, _id)));
            script.append("$('#settingRowTr" + jjTools.getParameter(request, _id) + "').remove();");
            return script.toString();
//            return refresh(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- delete ------------------
    ///----------------- setGlobalParam ----------------->
//    public static String setGlobalParam(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        //همون اول که وارد میشه به پن ارسال اس ام اس یا کلا همون موقع که لاگین میکنه همون یه دونه وب سرویس اس ام اس اش انتخاب بشه و پارامترها ست بشن
//    }
    ///<----------------- setGlobalParam -----------------

    ///---------------------------- selectWebService ------------------>
    public static String selectWebService(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder script = new StringBuilder();
        StringBuilder html1 = new StringBuilder();
        StringBuilder html2 = new StringBuilder();
        ServerLog.Print(">>> smsSetting.selectWebService");
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
        if (row.isEmpty()) {
            ServerLog.Print("هیچ رکوردی وجود ندارد.");
            return Js.dialog("هیچ رکوردی وجود ندارد.");
        }
        for (int i = 0; i < row.size(); i++) {
            html1.append("<option onclick=\"selectNumbers(").append(row.get(i).get(_id)).append(")\">").append(row.get(i).get(_webService).toString()).append("</option>");
        }
        String str = row.get(0).get(_senderNumber).toString();
        sms.apiKey = row.get(0).get(_apiKey).toString();
        sms.webService = row.get(0).get(_webService).toString();
        String numbers[] = str.split(",");
        for (int j = 0; j < numbers.length; j++) {
            html2.append("<option>").append(numbers[j]).append("</option>");
        }
//        ServerLog.Print(script);
//        script.append(Js.setHtml("#" + jjTools.getParameter(request, "panel_webServices"), html1));
        script.append(Js.setHtml("#sms_sender", html2));
        return script.toString();
    }

    ///<---------------------------- selectWebService ------------------
    ///---------------------------- selectNumbers ------------------>
    public static String selectNumbers(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuilder script = new StringBuilder();
        StringBuilder selectBody = new StringBuilder();
//        ServerLog.Print(">>> selectWebService");
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + jjTools.getParameter(request, _id)));
        if (row.isEmpty()) {
            ServerLog.Print("هیچ رکوردی وجود ندارد.");
            return Js.dialog("هیچ رکوردی وجود ندارد.");
        }
        sms.apiKey = row.get(0).get(_apiKey).toString();
        sms.webService = row.get(0).get(_webService).toString();
        sms.userName = row.get(0).get(_userName).toString();
        sms.pass = row.get(0).get(_pass).toString();
//        sms.domain = row.get(0).get(_domain).toString();

        String str = row.get(0).get(_senderNumber).toString();
//        str = str.replaceAll("\\s+", ",");
//        str = str.replaceAll(",+", ",");
        String numbers[] = str.split(",");
        sms.sender = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            selectBody.append("<option>").append(numbers[i]).append("</option>");
        }
        return script.append(Js.setHtml("#" + jjTools.getParameter(request, "panel"), selectBody)).toString();
    }

    ///<---------------------------- selectNumbers ------------------
    ////// ---------------- add_new() ------------------->
    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#SMSSetting_button", "<input type=\"button\" id=\"insert_SMSSetting_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_SMS_new", Js.jjSMS.insert()));//send sms

            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    ////// <---------------- add_new() -------------------

    public static void main(String[] args) {

    }
}
