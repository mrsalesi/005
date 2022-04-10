/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.cms.Tice_config;
import cms.tools.Call;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import cms.tools.sms;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author شیران1 تابع نوشته شده برای پیام ها
 */
public class Messenger {

    public static String tableName = "hmis_Messenger";
    public static String _id = "id";
    public static String _title = "messenger_title";///عنوان پیام
    public static String _textMessage = "messenger_textMessage";///متن پیام
    public static String _textHTML = "messenger_textHTML";///متن پیام بصورت کامل همراه با لینک ها و سایر موارد بصورت اچ تی ام ال
    public static String _receiver = "messenger_receiver";//گیرنده پیام
    public static String _sender = "messenger_sender";//فرستنده پیام
    public static String _postageDate = "messenger_postageDate";///تاریخ ارسال
    public static String _dateOfCreation = "messenger_dateOfCreation";///تاریخ ایجاد
    public static String _displayed = "messenger_displayed";///دیده شده
    public static String _status = "messenger_status";///وضعیت پیام
    public static String _logStatus = "messenger_logStatus";///روند وضعیت پیام
    public static String _email = "messenger_email";///ایمیل
    public static String _sendingMethod = "messenger_sendingMethod";///روش ارسال
    public static String _file = "messenger_file";///
    public static String _type = "messenger_type";///

    public static String status_created = "ایجاد شده";///برای  وضعیت پیام نوشته شده است 
    public static String status_displayed = "دیده شده";
    public static String status_posted = "ارسال شده";
    public static String status_postQueue = "درصف ارسال";
    public static String status_deleted = "حذف شده";
    public static String lbl_insert = "ارسال پیام";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static int rul_rfs_karshenas = 291;//برای اینکه تشخیص دهیم کاربر مجوز دیدن پیامهای کارشناس را دارد یا نه
    public static int rul_rfs = 292;
    public static int rul_dlt = 295;
    public static int rul_edt = 294;
    public static int rul_senEmail = 330;
    public static int rul_print = 1;
    public static int rul_ins = 1;

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
// if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));

            StringBuilder html3 = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>پیام ها</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisMessenger.m_add_new();' > پیام جدید</a>"
                    + "        </p>");

            html.append("<table class='table display' id='refreshMessenger' dir='rtl'><thead>");
            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='5%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='5%' style='text-align:center'>فرستنده و گیرنده </th>");
            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='5%' style='text-align:center'>وضعیت پیام</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                String reciver = "";
                //اگر دیده شده خالی بود سطر بلد شودکلاس دیده نشده صدا زده شود در پابلیک استایل سی اس اس در غیر این صورت نال شود
                String[] reciverArray = row.get(i).get(_receiver).toString().split(",");
                for (int k = 0; k < reciverArray.length; k++) {
                    if (jjNumber.isDigit(reciverArray[k])) {
                        reciver += Access_User.getUserName(reciverArray[k], db) + ",";
                    }
                }
                html.append("<tr onclick='hmisMessenger.m_select(" + row.get(i).get(_id) + ");' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "' >" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + Access_User.getUserName((row.get(i).get(_sender).toString()), db) + "::(" + reciver + ")</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
//                if (accDel) {
//                }
                if (row.get(i).get(_status).toString().equals(status_displayed)) {
                    html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.alert()' ><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
//                    return Js.modal("امکان حذف برای شما وجود ندارد", "پیام سامانه");
                } else {
                    html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swMessengerTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMessenger", height, 0, "", "لیست پیام ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * پیام های حذف شده
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception shiran13980913
     */
    public static String refreshDeletedMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
// if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _status + "='" + status_deleted + "'"));

            StringBuilder html3 = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>پیام های حذف شده</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "        </p>");
            html.append("<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon' style='float:right'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisDeletedMessages.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>");

            html.append("<table class='table display' id='refreshDeletedMessages' dir='rtl'><thead>");
            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='5%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='5%' style='text-align:center'>فرستنده و گیرنده </th>");
            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='5%' style='text-align:center'>وضعیت پیام</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);

            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                String reciver = "";
                String[] reciverArray = row.get(i).get(_receiver).toString().split(",");
                for (int k = 0; k < reciverArray.length; k++) {
                    if (jjNumber.isDigit(reciverArray[k])) {
                        reciver += Access_User.getUserName(reciverArray[k], db) + ",";
                    }
                }
                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "' >" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + Access_User.getUserName((row.get(i).get(_sender).toString()), db) + "::(" + reciver + ")</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");

            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swDeletedMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshDeletedMessages", height, 0, "", "لیست پیام ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshMyMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            int id = jjTools.getSeassionUserId(request);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Messenger.tableName, _receiver + "=" + id));
            html.append(" <div class=\"card-header bg-primary tx-white\">لیست پیام های من</div>"
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisMyMessages.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>پیام های من</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "        </p>");

            html.append("<table class='table display responsive' id='refreshMyMessages' dir='rtl'><thead>");
            html.append("<th width='10%' style='text-align:center'>کد</th>");
            html.append("<th width='10%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='30%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='10%' style='text-align:center'>گیرنده</th>");
            html.append("<th width='10%' style='text-align:center'>فرستنده</th>");
            html.append("<th width='20%' style='text-align:center'>وضعیت پیام</th>");
            html.append("<th width='10%' style='text-align:center'>عملیات</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {

                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + Access_User.getUserName(row.get(i).get(_receiver).toString(), db) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (Access_User.getUserName(row.get(i).get(_sender).toString(), db)) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='' style='text-align: center;' onclick='hmisMyMessages.m_select(" + row.get(i).get(_id) + ");'><img src='template/contract.png' style='height:30px;margin:auto'/></td>");
                if (accDel) {
                    if (row.get(i).get(_status).toString().equals(status_displayed)) {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.alert()' ><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    } else {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMyMessages.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    }
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swMyMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMyMessages", height, 0, "", "لیست پیام های من");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            int id = jjTools.getSeassionUserId(request);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_messenger ORDER BY hmis_messenger.id DESC  LIMIT 500"));

            StringBuilder html3 = new StringBuilder();

            html.append(" <div id='AllMessenger' ></div><div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>500 پیام اخیر</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + " <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisMessages.m_refreshMessagesAll();' > کل پیام ها</a>"
                    + "        </p>"
            );

            html.append("<table class='table display' id='refreshMessages' dir='rtl'><thead>");
            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='10%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='15%' style='text-align:center'>وضعیت پیام</th>");

            html.append("<th width='5%' style='text-align:center'>عملیات</th>");
//       
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");

                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");

                html.append("<td class='' style='text-align: center;' onclick='hmisMessages.m_select(" + row.get(i).get(_id) + ");'><img src='template/contract.png' style='height:30px;margin:auto'/></td>");
                if (accDel) {
                    if (row.get(i).get(_status).toString().equals(status_displayed)) {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.alert()' ><img src='template/delet.png' style='height:30px;margin:auto'/></td>");

                    } else {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessages.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    }
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMessages", height, 0, "", "لیست پیام های اخیر");
            Server.outPrinter(request, response, html2);
            return "";
//       

        } catch (Exception e) {

            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshMessagesAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            int id = jjTools.getSeassionUserId(request);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Messenger.tableName));

            StringBuilder html3 = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>کل پیام ها</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "        </p>");

            html.append("<table class='table display responsive' id='refreshMessagesAll' dir='rtl'><thead>");

            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='10%' style='text-align:center'>ارسال در</th>");

            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='15%' style='text-align:center'>وضعیت پیام</th>");

            html.append("<th width='5%' style='text-align:center'>عملیات</th>");
//       
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {

                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");

                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");

                html.append("<td class='' style='text-align: center;' onclick='hmisMessages.m_select(" + row.get(i).get(_id) + ");'><img src='template/contract.png' style='height:30px;margin:auto'/></td>");
                if (accDel) {
                    if (row.get(i).get(_status).toString().equals(status_displayed)) {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.alert()' ><img src='template/delet.png' style='height:30px;margin:auto'/></td>");

                    } else {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessages.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    }
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMessages", height, 0, "", "لیست پیام های اخیر");
            Server.outPrinter(request, response, html2);

            return "";
//       

        } catch (Exception e) {

            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshSentMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            int id = jjTools.getSeassionUserId(request);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Messenger.tableName, _sender + "=" + jjTools.getSeassionUserId(request) + " AND ( " + _status + "='" + status_posted + "' OR " + _status + "='" + status_postQueue + "' )"));
            StringBuilder html3 = new StringBuilder();
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card-header bg-primary tx-white'>پیام های ارسالی</div>"
                        + " <div class='card-body pd-sm-30'>"
                );
                html.append(" <div class='col-lg-12'>"
                        + "<a href='#' class='sh-pagetitle-icon'>"
                        + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisSentMessages.m_refresh();'></i>"
                        + "</div>"
                        + "</a>"
                        + "</div>"
                );

                html.append("<div class='col-lg-12  mg-t-20'>"
                        + " <p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + " <a class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" style='color:#fff' onclick=\"hmisSentMessages.m_add_new();\" >پیام جدید</a>\n"
                        + "  </p>"
                        + "</div>");
            }
            html.append("<table class='table display responsive' id='refreshSentMessages' dir='rtl'><thead>");
            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='10%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='15%' style='text-align:center'>وضعیت پیام</th>");
            html.append("<th width='5%' style='text-align:center'>عملیات</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='' style='text-align: center;' onclick='hmisSentMessages.m_select(" + row.get(i).get(_id) + ");'><img src='template/contract.png' style='height:30px;margin:auto'/></td>");
                if (accDel) {
                    if (row.get(i).get(_status).toString().equals(status_postQueue)) {// اگر در صف ارسال بود بشود حذف کرد
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisSentMessages.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    } else {
                        html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisMessenger.alert()' ><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                    }
                }
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
                panel = "swSentMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshSentMessages", height, 0, "", "لیست پیام های ارسالی");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {

            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshRecivedMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            int id = jjTools.getSeassionUserId(request);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Messenger.tableName, _status + "='" + status_posted + "' AND " + _receiver + "=" + jjTools.getSeassionUserId(request)));
            StringBuilder html3 = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>پیام های دریافتی</div>"
                    + "<div class='col-lg-12 mg-b-20'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisRecivedMessages.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "        </p>");
            html.append("<table class='table display responsive' id='refreshRecivedMessages' dir='rtl'><thead>");
            html.append("<th width='5%' style='text-align:center'>کد</th>");
            html.append("<th width='10%' style='text-align:center'>ارسال در</th>");
            html.append("<th width='10%' style='text-align:center'> گیرنده و فرستنده</th>");
            html.append("<th width='15%' style='text-align:center'>متن پیام</th>");
            html.append("<th width='15%' style='text-align:center'>وضعیت پیام</th>");
            html.append("<th width='5%' style='text-align:center'>عملیات</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th style='text-align:center' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>");
                html.append("<td style='text-align:center' class='mousePointer" + " " + getClassCssForStatus((row.get(i).get(_status).toString())) + "'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)) + "</td>");
                html.append("<td style='text-align:center' class='c'>" + Access_User.getUserName(row.get(i).get(_receiver).toString(), db) + "," + Access_User.getUserName(row.get(i).get(_sender).toString(), db) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_textMessage).toString()) + "</td>");
                html.append("<td style='text-align:center' class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='' style='text-align: center;' onclick='hmisRecivedMessages.m_select(" + row.get(i).get(_id) + ");'><img src='template/contract.png' style='height:30px;margin:auto'/></td>");
                if (accDel) {
                    html.append("<td class='c mousePointer' style='text-align: center;' onclick='hmisRecivedMessages.m_delete(" + row.get(i).get(_id) + ");'><img src='template/delet.png' style='height:30px;margin:auto'/></td>");
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swRecivedMessagesTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshRecivedMessages", height, 0, "", "لیست پیام های دریافتی");

            Server.outPrinter(request, response, html2);
            return "";
//       

        } catch (Exception e) {

            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /*
     این تابع برای اضافه کردن روش ارسال نوشته شده
     */
    public static String sendMetod1(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String script6 = "<div >روش ارسال<input  type='checkbox' id='messenger_sendToEmail' name='messenger_sendingMethod'  value='email'  class='form-control'/>ایمیل"
                    + "<input type='checkbox' id='messenger_sendToSms' name='messenger_sendingMethod'  value='sms'   class='form-control'  />sms<"
                    + "<input type='checkbox' id='messenger_sendToApp' name='messenger_sendingMethod'  value='app'   class='form-control'  />app</div>";
            html.append(Js.setHtml("#sendingMetod1", script6));
            Server.outPrinter(request, response, html);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuilder html = new StringBuilder();

        String script = "";
        try {
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);

            if (accIns) {//@ToDo 
                script += Js.setHtml("#Messenger_button", "<div class='col-lg-6'>"
                        + "<input type='button' id='insert_Messenger_new' onclick='hmisMessenger.m_insert();' "
                        + " value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'>"
                        + "</div>");
            } else {
                script += Js.setHtml("#Messenger_button", "");
            }
            boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
            boolean accSms = Access_User.hasAccess(request, db, rul_ins);
            boolean accApp = Access_User.hasAccess(request, db, rul_ins);
            html.append("<div>روش ارسال");
            if (accEmail) {//@ToDo 
                html.append("<input  type='checkbox' id='messenger_sendToEmail'  name='messenger_sendingMethod'  value='email'  class='form-control sendMethod'/>ایمیل");
            }
            if (accSms) {//@ToDo 
                html.append("<input type='checkbox' id='messenger_sendToSms' name='messenger_sendingMethod'  value='sms'   class='form-control sendMethod'  />sms");
            }
            if (accApp) {//@ToDo 
                html.append("<input type='checkbox' id='messenger_sendToApp' name='messenger_sendingMethod'  value='app'   class='form-control sendMethod'  />app");
            }
            html.append("</div>");
            script += Js.setHtml("#sendingMetod", html);
            script += Js.setVal("#" + _sender, jjTools.getSeassionUserNameAndFamily(request));
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

////////    این تابع برای تغییر وضعیت پیام ونشان دادن روند وضعیت ایجاد شده توسظ شیران1
////////تاریخ ایجاد 1398/01/20
    public static String changeStatus(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String id, String newSatus) throws Exception {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                return Js.dialog(errorMessageId);
            }
            db.otherStatement("UPDATE " + tableName + " SET " + _logStatus
                    + "=concat(ifnull(" + _logStatus + ",''),'"
                    + newSatus
                    + "-"
                    + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
                    + "') ,"
                    + _status + "='" + newSatus + "'  WHERE id=" + id + ";");

            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            String errorMessage = "عملیات تغییر وضعیت به درستی صورت نگرفت.Err114";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        }
    }

    ////این تابع برای نمایش اسم کاربران در پیام ها 
//    selectoption گیرنده
    public static String selectOptionUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String html4 = "<option id='selectUser' style='color:black' value=''>گیرنده مورد نظر را انتخاب کنید</option>";
            Document doc = Jsoup.parse(html4);

            List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

            for (int i = 0; i < UserRow.size(); i++) {
//              

                String optionHtml = "<option id='selectUser" + UserRow.get(i).get(_id) + "'  value='" + UserRow.get(i).get(_id) + "'>"
                        + UserRow.get(i).get(Access_User._name)
                        + " "
                        + UserRow.get(i).get(Access_User._family)
                        + "</option>";
                doc.getElementById("selectUser").append(optionHtml);
            }

            if (panel == "") {
                panel = "messenger_receiver";
            }
            script += Js.setHtml("#" + panel, doc.getElementsByTag("body").toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /*
     selectOptionUserRecivedMessages
     این تابع برای دراوردن گیرنده ها وانتخاب انها به صورت سلکت آپشن برای پیام های خوانده نشده نوشته شده
     */
    public static String selectOptionUserRecivedMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String html4 = "<option id='selectUser' style='color:black' value=''>گیرنده مورد نظر را انتخاب کنید</option>";
            Document doc = Jsoup.parse(html4);

            List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

            for (int i = 0; i < UserRow.size(); i++) {
//              

                String optionHtml = "<option id='selectUser" + UserRow.get(i).get(_id) + "'  value='" + UserRow.get(i).get(_id) + "'>"
                        + UserRow.get(i).get(Access_User._name)
                        + " "
                        + UserRow.get(i).get(Access_User._family)
                        + "</option>";
                doc.getElementById("selectUser").append(optionHtml);
            }

            if (panel == "") {
                panel = "recivedMessages_receiver";
            }
            script += Js.setHtml("#" + panel, doc.getElementsByTag("body").toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /*
     selectOptionUserMyMessages
     این تابع برای دراوردن گیرنده ها وانتخاب انها به صورت سلکت آپشن برای پیام های من نوشته شده
     */
    public static String selectOptionUserMyMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }

            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String html4 = "<option id='selectUser' style='color:black' value=''>گیرنده مورد نظر را انتخاب کنید</option>";
            Document doc = Jsoup.parse(html4);

            List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

            for (int i = 0; i < UserRow.size(); i++) {
//              

                String optionHtml = "<option id='selectUser" + UserRow.get(i).get(_id) + "'  value='" + UserRow.get(i).get(_id) + "'>"
                        + UserRow.get(i).get(Access_User._name)
                        + " "
                        + UserRow.get(i).get(Access_User._family)
                        + "</option>";
                doc.getElementById("selectUser").append(optionHtml);
            }

            if (panel == "") {
                panel = "myMessages_receiver";
            }
            script += Js.setHtml("#" + panel, doc.getElementsByTag("body").toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /*
     selectOptionUserMyMessages
     این تابع برای دراوردن گیرنده ها وانتخاب انها به صورت سلکت آپشن برای پیام های دیده شده نوشته شده
     */
    public static String selectOptionUserSentMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String html4 = "<option id='selectUser' style='color:black' value=''>گیرنده مورد نظر را انتخاب کنید</option>";
            Document doc = Jsoup.parse(html4);

            List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

            for (int i = 0; i < UserRow.size(); i++) {
//              

                String optionHtml = "<option id='selectUser" + UserRow.get(i).get(_id) + "'  value='" + UserRow.get(i).get(_id) + "'>"
                        + UserRow.get(i).get(Access_User._name)
                        + " "
                        + UserRow.get(i).get(Access_User._family)
                        + "</option>";
                doc.getElementById("selectUser").append(optionHtml);
            }

            if (panel == "") {
                panel = "sentMessages_receiver";
            }
            script += Js.setHtml("#" + panel, doc.getElementsByTag("body").toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /*
     selectOptionUserMyMessages
     این تابع برای دراوردن گیرنده ها وانتخاب انها به صورت سلکت آپشن برای پیام ها نوشته شده
     */
    public static String selectOptionUserMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//           if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }

            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String html4 = "<option id='selectUser' style='color:black' value=''>گیرنده مورد نظر را انتخاب کنید</option>";
            Document doc = Jsoup.parse(html4);

            List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

            for (int i = 0; i < UserRow.size(); i++) {
//              

                String optionHtml = "<option id='selectUser" + UserRow.get(i).get(_id) + "'  value='" + UserRow.get(i).get(_id) + "'>"
                        + UserRow.get(i).get(Access_User._name)
                        + " "
                        + UserRow.get(i).get(Access_User._family)
                        + "</option>";
                doc.getElementById("selectUser").append(optionHtml);
            }

            if (panel == "") {
                panel = "messages_receiver";
            }
            script += Js.setHtml("#" + panel, doc.getElementsByTag("body").toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /**
     * تابع درج date 1397/12/19 shiran1
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(_sender, jjTools.getSeassionUserId(request));
//            map.put(_title, jjTools.getParameter(request, _title));
//            map.put(_textMessage, jjTools.getParameter(request, _textMessage));
//            map.put(_dateOfCreation, jjTools.getParameter(request, _dateOfCreation));
//            map.put(_file, jjTools.getParameter(request, _file));
//            map.put(_sendingMethod, jjTools.getParameter(request, _sendingMethod));
//            map.put(_displayed, jjTools.getParameter(request, _displayed));
//            map.put(_receiver, jjTools.getParameter(request, _receiver));
//            if (db.insert(tableName, map).getRowCount() == 0) {
//                String errorMessege = "عملیات درج بدرستی صورت نگرفت";
//                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessege));
//            }
            StringBuilder html = new StringBuilder();
            String attachFiles = jjTools.getParameter(request, _file);
            String[] attachFilesArray = attachFiles.split(",");
            for (int l = 0; l < attachFilesArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "</div>"
                        );
                    } else {
                        html.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String reciversId = jjTools.getParameter(request, _receiver);
            sendMesseage(request, db, reciversId, "" + jjTools.getSeassionUserId(request) + "", jjTools.getParameter(request, _sendingMethod), jjTools.getParameter(request, _postageDate).replaceAll("/", ""), jjTools.getParameter(request, _title), jjTools.getParameter(request, _textMessage), jjTools.getParameter(request, _textHTML) + html, "یادآوری","1","1");
            Server.outPrinter(request, response, Js.jjMessenger.refresh());
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    /**
     * shiran1
     *
     * @param id
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            int iduser = jjTools.getSeassionUserId(request);
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";

            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();
            String script2 = "";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder script6 = new StringBuilder();
            String script3 = "";
            String html5 = "";
            script.append(Js.setVal("#messenger_" + _id, row.get(0).get(_id)));
            script.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            script.append(Js.setVal("#" + _textMessage, row.get(0).get(_textMessage)));
            String receiver = row.get(0).get(_receiver).toString();
            String[] receiverArray = receiver.split(",");//آی دی  ها با این رشته از هم جدا می شود
            String temp = "";
            for (int j = 0; j < receiverArray.length; j++) {
                temp += "'" + receiverArray[j] + "',";
                System.out.println("receiverArray=" + temp);
            }
            script.append("$('#messenger_receiver').val([" + temp + "]);"
                    + "$('#messenger_receiver').select2({  minimumResultsForSearch: '',  width: '100%'});");
            script.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            String ravand = row.get(0).get(_logStatus).toString();
            ravand = ravand.replace("%23A%23", "\\n");
            System.out.println(ravand.replace("%23A%23", "\\n"));
            script.append(Js.setVal("#" + _logStatus, ravand));
            script.append(Js.setVal("#" + _sender, Access_User.getUserName(row.get(0).get(_sender).toString(), db)));
            script.append(Js.setVal("#" + _dateOfCreation, row.get(0).get(_dateOfCreation)));
            script.append(Js.setVal("#" + _postageDate, jjCalendar_IR.getViewFormat(row.get(0).get(_postageDate))));
            script.append(Js.setValSummerNote("#" + _textHTML, row.get(0).get(_textHTML)));
//            script.append(Js.setVal("#" + _sender, jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY")));
//            script.append(Js.setVal("#messenger_attachFile", row.get(0).get(_attachFile)));
//            script.append(Js.setVal("#messenger_attachFileTitle", row.get(0).get(_attachFileTitle)));
//            String attachFiles = row.get(0).get(_attachFile).toString();
////            script.append("hmisMessenger.send();");
//            String attachFileTitles = row.get(0).get(_attachFileTitle).toString();
//
//            String[] attachFilesArray = attachFiles.split("%23A%23");
//            String[] attachFileTitlesArray = attachFileTitles.split("%23A%23");
//
//            for (int l = 0; l < attachFilesArray.length && l < attachFileTitlesArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
//                List<Map<String, Object>> titleRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._title + "='" + attachFileTitlesArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        if (titleUpload.equals("")) {
//                            html2.append("<img class='col-xs-12' style='width:10%;float:right' src='upload/" + attachFilesArray[l] + "'/><input style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button class='col-lg-1 form-control'  style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>" + "حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                        } else {
//
//                            html2.append("<img class='col-xs-12' style='width:10%;float:right' src='upload/" + attachFilesArray[l] + "'/><input class='col-lg-12 form-control'  style='text-align: center' disabled='disabled'  value='" + attachFileTitlesArray[l] + "'/>" + "<input  style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button  class='col-lg-1' style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                        }
//                    } else {
//                        html2.append("<input class='col-lg-12 form-control'  style='text-align: center' disabled='disabled'  value='" + attachFileTitlesArray[l] + "'/>" + "<input  style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button  class='col-lg-1' style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                    }
//                }
//            }
///////////////////////////////shiran2
//            String attachFilesTracker = row.get(0).get(_file).toString();
//            String[] attachFilesTrackerArray = attachFilesTracker.split(",");
//            for (int l = 0; l < attachFilesTrackerArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesTrackerArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesTrackerArray[l].substring(attachFilesTrackerArray[l].lastIndexOf(".") + 1, attachFilesTrackerArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        html2.append("<div class='col-lg-12  mg-l-15'>"
//                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
//                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
//                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                + "</div>"
//                        );
//                    } else {
//                        html2.append("<div class='col-lg-12 mg-l-15'>"
//                                + "<i class='icon ion-ios-paper-outline'></i>"
//                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
//                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                + "</div>"
//                        );
//                    }
//                } else {
//                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
//                }
//            }

            script2 += Js.setHtml(".inputAfterSelectManager", html2);

            if ((row.get(0).get(_receiver).equals(jjTools.getSessionAttribute(request, "#ID")) && (!row.get(0).get(_status).equals(status_displayed)))) {

                changeStatus(request, response, db, id, status_displayed);

            }
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);

            String htmlBottons = "";
            String receivr = row.get(0).get(Messenger._receiver).toString();

            String[] receivrArray = receivr.split(",");
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);

            if (row.get(0).get(_status).equals(status_posted)) {
                script1.append(Js.setHtml("#Messenger_button", ""));
            }
            if (row.get(0).get(_status).equals(status_displayed)) {
                script1.append(Js.setHtml("#Messenger_button", ""));
            }
            if (row.get(0).get(_status).equals(status_postQueue)) {
                if (row.get(0).get(Messenger._sender).equals(jjTools.getSessionAttribute(request, "#ID"))) {
                    if (accEdit) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjMessenger.edit() + "' id='edit_Messenger'>" + lbl_edit + "</button></div>";
//               
                    }
                    boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                    if (accDelete) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjMessenger.delete(id) + "' id='delete_Messenger'>" + lbl_delete + "</button></div>";
                    }
                    script1.append(Js.setHtml("#Messenger_button", htmlBottons));
                } else {
                    if (accEdit) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjMessenger.edit() + "' id='edit_Messenger'>" + lbl_edit + "</button></div>";
//               
                    }
                    boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                    if (accDelete) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjMessenger.delete(id) + "' id='delete_Messenger'>" + lbl_delete + "</button></div>";
                    }
                    script1.append(Js.setHtml("#Messenger_button", htmlBottons));
                }
            } else {

            }
            boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
            boolean accApp = Access_User.hasAccess(request, db, rul_ins);
            boolean accSms = Access_User.hasAccess(request, db, rul_ins);

            html5 += "<div >روش ارسال";
            if (accEmail) {//@ToDo 
                html5 += "<input  type='checkbox' id='messenger_sendToEmail' name='messenger_sendingMethod'  value='email'  class='form-control'/>ایمیل";
            }
            if (accSms) {//@ToDo 
                html5 += "<input type='checkbox' id='messenger_sendToSms' name='messenger_sendingMethod'  value='sms'   class='form-control'  />sms";
            }
            if (accApp) {//@ToDo 
                html5 += "<input type='checkbox' id='messenger_sendToApp' name='messenger_sendingMethod'  value='app'   class='form-control'  />app";
            }
            html5 += "</div>";
            script6.append(Js.setHtml("#sendingMetod", html5));

            String[] arrayMethod = row.get(0).get(_sendingMethod).toString().split(",");
            for (int i = 0; i < arrayMethod.length; i++) {
                if (arrayMethod[i].equals("sms")) {
                    script6.append(Js.setAttr("#messenger_sendToSms", "checked", "checked"));
                }
                if (arrayMethod[i].equals("app")) {
                    script6.append(Js.setAttr("#messenger_sendToApp", "checked", "checked"));
                }
                if (arrayMethod[i].equals("email")) {
                    script6.append(Js.setAttr("#messenger_sendToEmail", "checked", "checked"));
                }
            }

            Server.outPrinter(request, response, html.toString() + script + script1 + script3 + script2 + html4 + script6);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    /*
     selectپیام های من
     این تابع برای select پیام های خوانده نشده نوشته شده است
     */
    public static String selectRecivedMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();
            StringBuilder script6 = new StringBuilder();
            String script2 = "";

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script3 = "";
            script.append(Js.setVal("#recivedMessages_" + _id, row.get(0).get(_id)));

            script.append(Js.setVal("#recivedMessages_title", row.get(0).get(_title)));
            script.append(Js.setVal("#recivedMessages_textMessage", row.get(0).get(_textMessage)));
//        
            String receiver = row.get(0).get(_receiver).toString();

            String[] receiverArray = receiver.split(",");//آی دی  ها با این رشته از هم جدا می شود
            String temp = "";
            for (int j = 0; j < receiverArray.length; j++) {
                temp += "'" + receiverArray[j] + "',";
                System.out.println("receiverArray=" + temp);
            }

            script.append("$('#recivedMessages_receiver').val([" + temp + "]);"
                    + "$('#recivedMessages_receiver').select2({ width: '100%'});");

//            
            script.append(Js.setVal("#recivedMessages_status", row.get(0).get(_status)));
            String ravand = row.get(0).get(_logStatus).toString();
            ravand = ravand.replace("%23A%23", "\\n");
            System.out.println(ravand.replace("%23A%23", "\\n"));
            script.append(Js.setVal("#recivedMessages_logStatus", ravand));

            script.append(Js.setVal("#recivedMessages_sender", jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY")));
            script.append(Js.setVal("#recivedMessages_dateOfCreation", row.get(0).get(_dateOfCreation)));
            script.append(Js.setVal("#recivedMessages_postageDate", jjCalendar_IR.getViewFormat(row.get(0).get(_postageDate))));

            if ((row.get(0).get(_receiver).equals(jjTools.getSessionAttribute(request, "#ID")) && (!row.get(0).get(_status).equals(status_displayed)))) {

                changeStatus(request, response, db, id, status_displayed);

            }
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);

            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
//                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjMessenger.editRecived() + "' id='edit_Messenger'>" + lbl_edit + "</button></div>";
//               
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
//                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjMessenger.deleteRecived(id) + "' id='delete_Messenger'>" + lbl_delete + "</button></div>";
            }
            script1.append(Js.setHtml("#RecivedMessages_button", htmlBottons));
            boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
            if (row.get(0).get(_status).equals(status_posted)) {

            } else {

                boolean accSms = Access_User.hasAccess(request, db, rul_ins);
                String html5 = "<div >روش ارسال";
                if (accEmail) {//@ToDo 
                    html5 += "<input  type='checkbox' id='messenger_sendToEmail' name='messenger_sendToEmail'  value='1'  class='form-control'/>ایمیل";
                }

                if (accSms) {//@ToDo 
                    html5 += "<input type='checkbox' id='messenger_sendToSms' name='messenger_sendToSms'  value='1'   class='form-control'  />sms";
                }
                html5 += "</div>";
                script6.append(Js.setHtml("#sendingMetod", html5));
            }

            Server.outPrinter(request, response, html.toString() + script + script1 + script3 + script2 + script6);
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    /*
     selectپیام های من
     این تابع برای select پیام های من نوشته شده است
     */
    public static String selectMyMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";

            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();
            StringBuilder script6 = new StringBuilder();
            String script2 = "";
            String html5 = "";

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script3 = "";
            script.append(Js.setVal("#myMessages_" + _id, row.get(0).get(_id)));
            script.append(Js.setVal("#myMessages_title", row.get(0).get(_title)));
            script.append(Js.setVal("#myMessages_textMessage", row.get(0).get(_textMessage)));
            script.append(Js.setHtml("#myMessenger_textHTML", row.get(0).get(_textHTML)));
            String receiver = row.get(0).get(_receiver).toString();

            String[] receiverArray = receiver.split(",");//آی دی  ها با این رشته از هم جدا می شود
            String temp = "";
            for (int j = 0; j < receiverArray.length; j++) {
                temp += "'" + receiverArray[j] + "',";
                System.out.println("receiverArray=" + temp);
            }
            script.append("$('#myMessages_receiver').val([" + temp + "]);"
                    + "$('#myMessages_receiver').select2({width: '100%'});");
            script.append(Js.setVal("#myMessages_status", row.get(0).get(_status)));
            String ravand = row.get(0).get(_logStatus).toString();
            ravand = ravand.replace("%23A%23", "<br/>");
            System.out.println(ravand.replace("%23A%23", "\\n"));
            script.append(Js.setVal("#myMessages_logStatus", ravand));
            script.append(Js.setVal("#myMessages_sender", Access_User.getUserName(row.get(0).get(_sender).toString(), db)));
            script.append(Js.setVal("#myMessages_reciver", Access_User.getUserName(row.get(0).get(_receiver).toString(), db)));
            script.append(Js.setVal("#myMessages_dateOfCreation", row.get(0).get(_dateOfCreation)));
            script.append(Js.setVal("#myMessages_postageDate", jjCalendar_IR.getViewFormat(row.get(0).get(_postageDate))));
//            String attachFilesTracker = row.get(0).get(_file).toString();
//            String[] attachFilesTrackerArray = attachFilesTracker.split(",");
//            for (int l = 0; l < attachFilesTrackerArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesTrackerArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesTrackerArray[l].substring(attachFilesTrackerArray[l].lastIndexOf(".") + 1, attachFilesTrackerArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        html2.append("<div class='col-lg-12  mg-l-15'>"
//                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
//                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
//                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                + "</div>"
//                        );
//                    } else {
//                        html2.append("<div class='col-lg-12 mg-l-15'>"
//                                + "<i class='icon ion-ios-paper-outline'></i>"
//                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
//                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                + "</div>"
//                        );
//                    }
//                } else {
//                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
//                }
//            }

//            script.append(Js.setVal("#myMessages_attachFile", row.get(0).get(_attachFile)));
//            script.append(Js.setVal("#myMessages_attachFileTitle", row.get(0).get(_attachFileTitle)));
//            String attachFiles = row.get(0).get(_attachFile).toString();
//
//            String attachFileTitles = row.get(0).get(_attachFileTitle).toString();
//
//            String[] attachFilesArray = attachFiles.split("%23A%23");
//            String[] attachFileTitlesArray = attachFileTitles.split("%23A%23");
//
//            for (int l = 0; l < attachFilesArray.length && l < attachFileTitlesArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
//                List<Map<String, Object>> titleRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._title + "='" + attachFileTitlesArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        if (titleUpload.equals("")) {
//                            html2.append("<img class='col-xs-12' style='width:10%;float:right' src='upload/" + attachFilesArray[l] + "'/><input style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button class='col-lg-1 form-control'  style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>" + "حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                        } else {
//
//                            html2.append("<img class='col-xs-12' style='width:10%;float:right' src='upload/" + attachFilesArray[l] + "'/><input class='col-lg-12 form-control'  style='text-align: center' disabled='disabled'  value='" + attachFileTitlesArray[l] + "'/>" + "<input  style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button  class='col-lg-1' style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                        }
//                    } else {
//                        html2.append("<input class='col-lg-12 form-control'  style='text-align: center' disabled='disabled'  value='" + attachFileTitlesArray[l] + "'/>" + "<input  style='text-align: center;' class='col-lg-12'  disabled='disabled'  value='" + attachFilesArray[l] + "'/>" + "<button  class='col-lg-1' style='background-color: #e16262;color: white;float:left' onclick='hmisMessenger.m_remove(" + idUpload + "," + id + ")'>حذف" + "</button><a  class='col-lg-1' style='background-color: green;color: white;float:left;text-align: center;padding-top: 2px;padding-bottom: 1px;margin-top: 1px;' href='upload/" + attachFilesArray[l] + "' >دانلود</a>");
//                    }
//                }
//            }
            script2 += Js.setHtml(".inputAfterSelectManager", html2);

            if ((row.get(0).get(_receiver).equals(jjTools.getSessionAttribute(request, "#ID")) && (!row.get(0).get(_status).equals(status_displayed)))) {

                changeStatus(request, response, db, id, status_displayed);

            }
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
            String htmlBottons = "";
            boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
            if (row.get(0).get(_status).equals(status_postQueue)) {
                boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
                if (accEdit) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjMessenger.editMyMessages() + "' id='edit_Messenger'>" + lbl_edit + "</button></div>";
                }
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                if (accDelete) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjMessenger.deleteMyMessages(id) + "' id='delete_Messenger'>" + lbl_delete + "</button></div>";
                }
                script1.append(Js.setHtml("#myMessages_button", htmlBottons));
            }

            if (row.get(0).get(_status).equals(status_displayed)) {
                script1.append(Js.setHtml("#myMessages_button", ""));
            }
            if (row.get(0).get(_status).equals(status_posted)) {
                script1.append(Js.setHtml("#myMessages_button", ""));
            }
            boolean accSms = Access_User.hasAccess(request, db, rul_ins);
            boolean accApp = Access_User.hasAccess(request, db, rul_ins);
            html5 += ("<div >روش ارسال");
            if (accEmail) {//@ToDo 
                html5 += ("<input  type='checkbox' id='messenger_sendToEmailMyMessages' name='messenger_sendMethod'  value='email'  class='form-control'/>ایمیل");
            }
            if (accSms) {//@ToDo 
                html5 += ("<input type='checkbox' id='messenger_sendToSmsMyMessages' name='messenger_sendMethod'  value='sms'  class='form-control' />sms");
            }
            if (accApp) {//@ToDo 
                html5 += ("<input type='checkbox' id='messenger_sendToAppMyMessages' name='messenger_sendMethod'  value='app'   class='form-control' />app");
            }
            html5 += ("</div>");

            script6.append(Js.setHtml("#sendingMetodMymessages", html5));
            String[] arrayMethod = row.get(0).get(_sendingMethod).toString().split(",");
            for (int i = 0; i < arrayMethod.length; i++) {
                if (arrayMethod[i].equals("sms")) {
                    script6.append(Js.setAttr("#messenger_sendToSmsMyMessages", "checked", "checked"));
                }
                if (arrayMethod[i].equals("app")) {
                    script6.append(Js.setAttr("#messenger_sendToAppMyMessages", "checked", "checked"));
                }
                if (arrayMethod[i].equals("email")) {
                    script6.append(Js.setAttr("#messenger_sendToEmailMyMessages", "checked", "checked"));
                }
            }

            Server.outPrinter(request, response, html.toString() + script + script1 + script3 + script2 + script6);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /*
     selectپیام های من
     این تابع برای select پیام های دیده شده نوشته شده است
     */
    public static String selectSentMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();
            StringBuilder script6 = new StringBuilder();
            String script2 = "";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String script3 = "";
            script.append(Js.setVal("#sentMessages_" + _id, row.get(0).get(_id)));
            script.append(Js.setVal("#sentMessages_title", row.get(0).get(_title)));
            script.append(Js.setVal("#sentMessages_textMessage", row.get(0).get(_textMessage)));
            script.append(Js.setVal("#sentMessages_textHTML", row.get(0).get(_textHTML)));
            String receiver = row.get(0).get(_receiver).toString();
            String[] receiverArray = receiver.split(",");//آی دی  ها با این رشته از هم جدا می شود
            String temp = "";
            for (int j = 0; j < receiverArray.length; j++) {
                temp += "'" + receiverArray[j] + "',";
                System.out.println("receiverArray=" + temp);
            }
            script.append("$('#sentMessages_receiver').val([" + temp + "]);"
                    + "$('#sentMessages_receiver').select2({ width: '100%'});");
            script.append(Js.setVal("#sentMessages_status", row.get(0).get(_status)));
            String ravand = row.get(0).get(_logStatus).toString();
            ravand = ravand.replace("%23A%23", "\\n");
            System.out.println(ravand.replace("%23A%23", "\\n"));
            script.append(Js.setVal("#sentMessages_logStatus", ravand));
            script.append(Js.setVal("#sentMessages_sender", jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY")));
            script.append(Js.setVal("#sentMessages_dateOfCreation", row.get(0).get(_dateOfCreation)));
            script.append(Js.setVal("#sentMessages_postageDate", jjCalendar_IR.getViewFormat(row.get(0).get(_postageDate))));

            script2 += Js.setHtml(".inputAfterSelectManager", html2);

            if ((row.get(0).get(_receiver).equals(jjTools.getSessionAttribute(request, "#ID")) && (!row.get(0).get(_status).equals(status_displayed)))) {

                changeStatus(request, response, db, id, status_displayed);

            }
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);

            String htmlBottons = "";
            String html5 = "";
            boolean accSms = Access_User.hasAccess(request, db, rul_ins);
            boolean accApp = Access_User.hasAccess(request, db, rul_ins);
            boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
            if (row.get(0).get(_status).equals(status_posted)) {
            } else {

            }
/////////////////////////////////////////////
            html5 += ("<div >روش ارسال");
            if (accEmail) {//@ToDo 
                html5 += ("<input  type='checkbox' id='messenger_sendToEmailSeen' name='messenger_sendMethodSeen'  value='email'  class='form-control'/>ایمیل");
            }
            if (accSms) {//@ToDo 
                html5 += ("<input type='checkbox' id='messenger_sendToSmsSeen' name='messenger_sendMethodSeen'  value='sms'  class='form-control' />sms");
            }
            if (accApp) {//@ToDo 
                html5 += ("<input type='checkbox' id='messenger_sendToAppSeen' name='messenger_sendMethodSeen'  value='app'   class='form-control' />app");
            }
            html5 += ("</div>");

            script6.append(Js.setHtml("#sendMethodSentMessages", html5));
            String[] arrayMethod = row.get(0).get(_sendingMethod).toString().split(",");
            for (int i = 0; i < arrayMethod.length; i++) {
                if (arrayMethod[i].equals("sms")) {
                    script6.append(Js.setAttr("#messenger_sendToSmsSeen", "checked", "checked"));
                }
                if (arrayMethod[i].equals("app")) {
                    script6.append(Js.setAttr("#messenger_sendToAppSeen", "checked", "checked"));
                }
                if (arrayMethod[i].equals("email")) {
                    script6.append(Js.setAttr("#messenger_sendToEmailSeen", "checked", "checked"));
                }
            }

            Server.outPrinter(request, response, html.toString() + script + script1 + script3 + script2 + script6);
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    /*
     selectپیام های من
     این تابع برای select پیام های دیده شده نوشته شده است
     */
    public static String selectMessages(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";

            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();
            String script2 = "";

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
//            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder script6 = new StringBuilder();
            String script3 = "";
            if (row.size() > 0) {

                script.append(Js.setVal("#messages_" + _id, row.get(0).get(_id)));
                script.append(Js.setVal("#messages_title", row.get(0).get(_title)));
                script.append(Js.setVal("#messages_textMessage", row.get(0).get(_textMessage)));
                script.append(Js.setValSummerNote("#messages_textHTML", row.get(0).get(_textHTML)));
                String receiver = row.get(0).get(_receiver).toString();
                String[] receiverArray = receiver.split(",");//آی دی  ها با این رشته از هم جدا می شود
                String temp = "";
                for (int j = 0; j < receiverArray.length; j++) {
                    temp += "'" + receiverArray[j] + "',";
                    System.out.println("receiverArray=" + temp);
                }
                script.append("$('#messages_receiver').val([" + temp + "]);"
                        + "$('#messages_receiver').select2({width: '100%'});");
                script.append(Js.setVal("#messages_status", row.get(0).get(_status)));
                String ravand = row.get(0).get(_logStatus).toString();
                ravand = ravand.replace("%23A%23", "\\n");
                System.out.println(ravand.replace("%23A%23", "\\n"));
                script.append(Js.setVal("#messages_logStatus", ravand));
                script.append(Js.setVal("#messages_sender", jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY")));
                script.append(Js.setVal("#messages_dateOfCreation", row.get(0).get(_dateOfCreation)));
                script.append(Js.setVal("#messages_postageDate", jjCalendar_IR.getViewFormat(row.get(0).get(_postageDate))));

                String htmlBottons = "";
                boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                boolean accEmail = Access_User.hasAccess(request, db, rul_ins);
                if ((row.get(0).get(_receiver).equals(jjTools.getSessionAttribute(request, "#ID")) && (!row.get(0).get(_status).equals(status_displayed)))) {
                    changeStatus(request, response, db, id, status_displayed);
                }
                if (row.get(0).get(_status).equals(status_posted)) {
                }
                if (row.get(0).get(_status).equals(status_postQueue)) {
                    String attachFiles = jjTools.getParameter(request, _file);
                    String[] attachFilesArray = attachFiles.split(",");
                    for (int l = 0; l < attachFilesArray.length; l++) {
                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                        if (!fileRow.isEmpty()) {
                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                            String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                            if (extension2.toLowerCase().equals("jpg")
                                    || extension2.toLowerCase().equals("png")
                                    || extension2.toLowerCase().equals("gif")
                                    || extension2.toLowerCase().equals("svg")) {
                                html.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html.append("<div class='col-lg-12   mg-l-15'>"
                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                        + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }
                    script6.append(" $('#messagesUploadFileDiv').show();");
                    if (accEdit) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjMessenger.editMessages() + "' id='edit_Messenger'>" + lbl_edit + "</button></div>";
                    }
                    if (accDelete) {
                        htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjMessenger.deleteMessages(id) + "' id='delete_Messenger'>" + lbl_delete + "</button></div>";
                    }
                }
//               
                script1.append(Js.setHtml("#Messages_button", htmlBottons));
                boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
                boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
                boolean accApp = Access_User.hasAccess(request, db, rul_ins);
                boolean accSms = Access_User.hasAccess(request, db, rul_ins);
                String html5 = "<div >روش ارسال";
                if (accEmail) {//@ToDo 
                    html5 += "<input  type='checkbox' id='messeges_sendToEmail' name='messenger_sendingMethod'  value='email'  class='form-control'/>ایمیل";
                }
                if (accSms) {//@ToDo 
                    html5 += "<input type='checkbox' id='messeges_sendToSms' name='messenger_sendingMethod'   value='sms'  class='form-control'  />sms";
                }
                if (accApp) {//@ToDo 
                    html5 += "<input type='checkbox' id='messeges_sendToApp' name='messenger_sendingMethod'   value='app'  class='form-control'  />app";
                }
                html5 += "</div>";
                script6.append(Js.setHtml("#sendMethodMessages", html5));
                String[] arrayMethod = row.get(0).get(_sendingMethod).toString().split(",");
                for (int i = 0; i < arrayMethod.length; i++) {
                    if (arrayMethod[i].equals("sms")) {
                        script6.append(Js.setAttr("#messeges_sendToSms", "checked", "checked"));
                    }
                    if (arrayMethod[i].equals("app")) {
                        script6.append(Js.setAttr("#messeges_sendToApp", "checked", "checked"));
                    }
                    if (arrayMethod[i].equals("email")) {
                        script6.append(Js.setAttr("#messeges_sendToEmail", "checked", "checked"));
                    }
                }
                script2 += Js.setHtml("downloadFileMessegesDiv", html);
                Server.outPrinter(request, response, html.toString() + script + script1 + script3 + script2 + script6);
            } else {
                Server.outPrinter(request, response, Js.modal("چنین رکوردی وجود ندارد", ""));
            }
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }
    ///////////////////این تابع برای پاک کردن فایل های پیوست
    ////////////////////توسط شیران1
    //////////بدون پاک کردن از دیتا بیس

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            StringBuilder html = new StringBuilder();
            String attachFiles = jjTools.getParameter(request, _file);
            String[] attachFilesArray = attachFiles.split(",");
            for (int l = 0; l < attachFilesArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                //                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                //                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_textMessage, jjTools.getParameter(request, _textMessage));
            map.put(_receiver, jjTools.getParameter(request, _receiver));
            String textHTML = jjTools.getParameter(request, _textHTML) + "دانلود فایل" + html;
            map.put(_textHTML, textHTML);
            map.put(_sender, jjTools.getSeassionUserNameAndFamily(request));
            map.put(_dateOfCreation, jjTools.getParameter(request, _dateOfCreation));
            map.put(_postageDate, jjTools.getParameter(request, _postageDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_sendingMethod, jjTools.getParameter(request, _sendingMethod));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";

            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }

            String result = changeStatus(request, response, db, id, status_deleted);
            if (result.isEmpty()) {
                Server.outPrinter(request, response, Js.jjMessenger.refresh());
            }
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }

    }

    //* 
    /**
     * ارسال پیام
     *
     * @param db
     * @param receiver//گیرنده یا گیرنده ها که با کاما انگلیسی از هم جدا میشوند
     * ','
     * @param sender // اگر یک باشد میشود سیستم
     * @param sendingMethod//روش ارسال پیام sms,app,email,phone
     * @param postageDate//تاریخ ارسال اگر نگذاریم تاریخ امروز است
     * @param موضوع پیام//متن پیام
     * @param textMessage//متن پیام
     * @return
     */
    public static String sendMesseage(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String receiver, String sender, String sendingMethod, String postageDate, String textMessage) throws IOException {
        Map<String, Object> map = new HashMap<>();

        //این تابع برای دریافت گیرنده ها نوشته شده 
        ///شیران1
        String[] receiverMessageArray = receiver.split(",");//آی دی گیرنده  ها با این رشته از هم جدا می شود
        for (int j = 0; j < receiverMessageArray.length; j++) {
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + receiverMessageArray[j]));
            for (int i = 0; i < userRow.size(); i++) {
                map.put(_receiver, receiverMessageArray[j]);
                map.put(_sender, sender);
                map.put(_sendingMethod, sendingMethod);
                map.put(_postageDate, postageDate);
                map.put(_textMessage, textMessage);
//        map.put(_postageDate, jjCalendar_IR.getDatabaseFormat_8length(null, true));
//                    html7.append(" " + userRow.get(0).get(_nam) + " " + userRow.get(0).get(_family) + "/");
            }
//                script7 = Js.setVal("#darkhastha_pishnahadkargroh", html7);
        }

        if (db.insert(tableName, map).getRowCount() > 0) {
            return "";
        }
        String errorMessage = "ارسال پیام با خطایی مواجه شده است";
        Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
        return "";
    }

    /* 
     /**
     * ارسال پیام توسط توابع سیستمی
     *
     * @param request برای گرفتن کاربر فعال در سشن
     * @param db
     * @param receiver گیرنده یا گیرنده ها که با کاما انگلیسی از هم جدا میشوند
     * @param sender اگر یک باشد میشود سیستم
     * @param sendingMethod روش ارسال پیام sms,app,email,phone
     * @param postageDate تاریخ ارسال اگر نگذاریم تاریخ امروز است
     * @param subject موضوع پیام
     * @param textMessage متن پیام خلاصه ی پیام
     * @param textHTML متن html پیام
     * @return
     */
    public static String sendMesseage(HttpServletRequest request, jjDatabaseWeb db, String receiver, String sender, String sendingMethod, String postageDate, String subject, String textMessage, String textHTML, String type, String statusSms, String statusEmail) throws IOException, SQLException, Exception {
        Map<String, Object> map = new HashMap<>();
        String[] receiverMessageArray = receiver.split(",");//آی دی گیرنده  ها با این رشته از هم جدا می شود
        //اگر تاریخ امروز یا روزهای قبل بود همین الان بر اساس گیرنده ها و روش ارسال جدا بکند و ارسال بکند در غیر اینصورت برای اینکه قابل ویرایش باشد جدا نمی کنیم
        if (postageDate == null || postageDate.equals("") || Integer.valueOf(postageDate) <= jjCalendar_IR.getDatabaseFormat_8length(null, true)) {
            for (int j = 0; j < receiverMessageArray.length; j++) {// به تعداد دریافت کننده ها
                if (jjNumber.isDigit(receiverMessageArray[j])) {// اگر آی دی داده شده عدد بود
                    List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + receiverMessageArray[j] + " AND " + Access_User._isActive + "=1"));// پیام فقط برای کابران فعال میرود : ویژگی
                    if (userRow.size() == 1) {// اگر چنین کاربری وجود داشت
                        map.put(_receiver, receiverMessageArray[j]);
                        map.put(_sender, sender);
                        map.put(_sendingMethod, sendingMethod);
                        map.put(_type, type);//نوع پیام
                        map.put(_postageDate, jjCalendar_IR.getDatabaseFormat_8length(postageDate, true));
                        map.put(_textMessage, textMessage);
                        map.put(_textHTML, textHTML);
                        map.put(_title, subject);
                        String logStatus = "";
                        System.out.println("sendingMethod=" + sendingMethod);
                        if (sendingMethod.contains("sms")) {
                            System.out.println("");
                            System.out.println("/////////////////////////////sms");
                            String mobile = userRow.get(0).get(Access_User._mobile).toString();
                            String[] mobileArray = userRow.get(0).get(Access_User._mobile).toString().split(",");
                            if (Tice_config.getValue(db, Tice_config._config_activeSms_name).equals("1")) {
                                if (statusSms.equals("1")) {
                                    if (mobileArray.length > 1) {
                                        for (int i = 0; i < mobileArray.length; i++) {
                                            logStatus += sms.sendMessageByApi(request, db, mobileArray[i], textMessage, "", "", "");
                                        }
                                    } else {
                                        if (mobileArray.length == 1) {
                                            logStatus += sms.sendMessageByApi(null, db, mobile, textMessage, "", "", "");
                                        }
                                    }
                                }
                            }
                        }
                        if (sendingMethod.contains("phone")) {
                            System.out.println("/////////////////////////////phone");
                            String mobile = userRow.get(0).get(Access_User._mobile).toString();
                            String[] mobileArray = userRow.get(0).get(Access_User._mobile).toString().split(",");
                            if (mobileArray.length > 1) {
                                for (int i = 0; i < mobileArray.length; i++) {
                                    logStatus += Call.sendCallByApi(request, db, mobileArray[i], textMessage, "", "", "");

                                }
                            } else {
                                if (mobileArray.length == 1) {
                                    logStatus += Call.sendCallByApi(null, db, mobile, textMessage, "", "", "");
                                }
                            }
                        }
                        if (sendingMethod.contains("email")) {//@ToDo موقع ارسال ایمیل در جدولی مخصوص ایمیل ها هم اینسرت بشود که سابقه بماند
                            if (Tice_config.getValue(db, Tice_config._config_activeEmail_name).equals("1")) {
                                if (statusEmail.equals("1")) {
                                    System.out.println("/////////////////////////////email");
                                    logStatus += Server.sendEmail("", userRow.get(0).get(Access_User._email).toString(), subject, textMessage + textHTML, true, request, db);
                                }
                            }

//                            if (resultEmail == true) {
//
//                            }
                        }
                        map.put(_logStatus, logStatus);
                        List<Map<String, Object>> messageRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
                        changeStatus(null, null, db, messageRow.get(0).get(_id).toString(), status_posted);
                    }
                }
            }

        } else if (Integer.valueOf(postageDate) > jjCalendar_IR.getDatabaseFormat_8length(null, true)) {//اگر تاریخ ارسال پیام برای چند روز بعد بود  
            for (int j = 0; j < receiverMessageArray.length; j++) {// به تعداد دریافت کننده ها
                if (jjNumber.isDigit(receiverMessageArray[j])) {// اگر آی دی داده شده عدد بود
                    List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + receiverMessageArray[j] + " AND " + Access_User._isActive + "=1"));// پیام فقط برای کابران فعال میرود : ویژگی
                    if (userRow.size() == 1) {// اگر چنین کاربری وجود داشت
                        map.put(_receiver, receiverMessageArray[j]);
                        map.put(_sender, sender);
                        map.put(_sendingMethod, sendingMethod);
                        map.put(_postageDate, jjCalendar_IR.getDatabaseFormat_8length(postageDate, true));
                        map.put(_textMessage, textMessage + "-" + subject);
                        map.put(_textHTML, textHTML);
                        map.put(_title, subject + " " + textMessage);
                        List<Map<String, Object>> messageRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
                        changeStatus(null, null, db, messageRow.get(0).get(_id).toString(), status_postQueue);//وضعیت پیام در صف ارسال قرار میگیرد

                    }
                }
            }
        }
//        if (db.insert(tableName, map).getRowCount() > 0) {
//            return "";
//        }
        String errorMessage = "ارسال پیام با خطایی مواجه شده است";
        return errorMessage;
    }

    /**
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return این تابع برای ارسال پیام به کسانی هست که امضا نکرده اند
     * @throws IOException
     */
    public static String sendMesseageToSignatory(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws IOException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String userId = request.getParameter("userId");///ای دی یوزر
            String IdDocumentary = request.getParameter("IdDocumentary");//عنوان مستند
            String titleSign = request.getParameter("titleSign");//عنوان امضا کننده را به صورت جی اس دریافت میکنیم
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(CreateDocumentary.tableName, _id + "='" + IdDocumentary + "'"));
            String title = row.get(0).get(CreateDocumentary._title).toString();
//            map.put(_textMessage, "مستند" + " " + title + "برای امضا به شمابه عنوان " + " " + titleSign + " " + "ارسال شده است" + " " + "لطفا مستند را امضا نمایید");
//            map.put(_title, "درخواست امضابرای" + " " + "مستند" + " " + title);
//            map.put(_receiver, userId);
//            map.put(_postageDate, jjCalendar_IR.getDatabaseFormat_8length("", true));
//            if (db.insert(tableName, map).getRowCount() == 0) {
//                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Edit Fail;";
//                }
//                Server.outPrinter(request, response, Js.dialog(errorMessage));
//                return "";
//            }
//            List<Map<String, Object>> insertedRow = jjDatabase.separateRow(db.insert(tableName, map));
            Messenger.sendMesseage(null, db, userId, "1", "sms,app,email", "", "درخواست امضابرای" + " " + "مستند" + " " + title, "مستند" + " " + title + "برای امضا به شمابه عنوان " + " " + titleSign + " " + "ارسال شده است" + " " + "لطفا مستند را امضا نمایید", "", "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleDocumentary_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleDocumentary_name));
//            changeStatus(request, response, db, insertedRow.get(0).get(_id).toString(), status_created);
            String errorMessage1 = "ارسال پیام به کاربر مورد نظر انجام گردید";
            Server.outPrinter(request, response, Js.modal(errorMessage1, "پیام سامانه"));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getClassCssForStatus(String satus) {
        if (satus.equals(status_created)) {
            return "status_created";
        } else if (satus.equals(status_displayed)) {
            return "status_displayed";
        } else if (satus.equals(status_postQueue)) {
            return "status_postQueue";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        } else if (satus.equals(status_posted)) {
            return "status_posted";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        }
        return "";
    }

    public static String ersalPayamBaEmail(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String email = jjTools.getParameter(request, _email);
            String Email = jjTools.getParameter(request, "email");
            List<Map<String, Object>> emailRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._email + "='" + Email + "'"));
            StringBuilder html = new StringBuilder();
            StringBuilder script = new StringBuilder();
            if (Server.sendEmail("sepanoweb@gmail.com", Email, "پیام های ارسال شده", "", true, request, db)) {
                String errorMessage = "ایمیل ارسال شد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String errorMessage = "ایمیل ارسال نشد";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    /**
     * پیام هایی که در صف ارسال هستن
     *
     * @throws Exception
     */
    public static void taskMessengerInQueue() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskMessengerInQueue###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(tableName, _status + "='" + status_postQueue + "'"));
        for (int i = 0; i < rows.size(); i++) {
            jjCalendar_IR date = new jjCalendar_IR(rows.get(i).get(_postageDate).toString());
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            if (date.getDBFormat_8length() == today) {
                sendMesseage(null, db, rows.get(i).get(_receiver).toString(), rows.get(i).get(_sender).toString(), rows.get(i).get(_sendingMethod).toString(), rows.get(i).get(_postageDate).toString(), rows.get(i).get(_title).toString(), rows.get(i).get(_textMessage).toString(), rows.get(i).get(_textHTML).toString(), "یادآوری","1","1");
                db.delete(tableName, _id + "=" + rows.get(i).get(_id));
            }
        }

        System.out.println("#################################################");
    }

//}
}
