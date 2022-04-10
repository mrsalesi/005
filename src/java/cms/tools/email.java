/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.access.Access_User;
import java.io.File;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import jj.jjFileTxt;
import jj.jjNumber;

/**
 *
 * @author Rashidi
 */
public class email {

    public static String tableName = "email";
    public static String _host = "mail.hafteghlim-ins.com";
    public static String _user = "mail@hafteghlim-ins.com";
    public static String _pass = "m!@#$%^&*()";
    public static String _id = "id";
    public static String _from = "email_from";
    public static String _to = "email_to";
    public static String _subject = "email_subject";
    public static String _body = "email_body";
    public static String _date = "email_date";
    public static String _comment = "email_comment";
    public static String _sendTime = "email_send_time";
    public static String _status = "email_status";
    public static String _visit_status = "email_visit_status";
    public static String _visit_time = "email_visit_time";
    public static String _visit_date = "email_visit_date";
    public static String _visit_count = "email_visit_count";
    public static String lbl_send = "ارسال ایمیل";
    public static String hostName = "http://localhost:8084/sepanoShop/";
//    public static int rul_ins = 6;
//    public static int rul_rfs = 6;
//    public static int rul_dlt = 6;

    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt=0;
    public static int rul_dlt = 0;
//    public static int rul_rfs = 461;
//    public static int rul_ins = 462;
//    public static int rul_edt;
//    public static int rul_dlt = 463;
////    public static int rul_reserved = 464 --- 480;// RESERVED : 464 -- 480
//    public static String sendEmail(String from, String to, String subject, String body, boolean isRtl, HttpServletRequest request) {
    //////--------------- sendEmailWithRequest() ----------------------->

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
 if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String from = jjTools.getParameter(request, _from);
            String to = jjTools.getParameter(request, _to);
            ServerLog.Print("to====>" + to);
            String subject = jjTools.getParameter(request, _subject);
            String comment = jjTools.getParameter(request, _comment);
            String body = jjTools.getParameter(request, _body);
            String bodyTable = body;
            boolean isRtl = Boolean.valueOf(jjTools.getParameter(request, "isRtl"));

            ServerLog.Print(">> FROM : " + from + " TO : " + to + " SUBJECT : " + subject + " BODY : " + body + " ISRTL : " + isRtl);
            String id = "";
            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.host", _host);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(sessionDebug);
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setHeader("Content-Type", "UTF-8");
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[0];
            if (to.indexOf(",") > 0) {
                String[] split = to.split(",");
                address = new InternetAddress[split.length];
                for (int i = 0; i < split.length; i++) {
                    address[i] = new InternetAddress(split[i]);
                }
            } else {
                address = new InternetAddress[1];
                address[0] = new InternetAddress(to);
            }
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject, "UTF-8");
            msg.setSentDate(new Date());
            File file = new File(request.getServletContext().getRealPath("/print") + "/email.html");
            if (file.exists()) {
                body = jjFileTxt.read(file).replace("bodybody", body);

            } else {
                msg.setText(body, "UTF-8");
            }
            ///////-------------DATE------->

            jjCalendar_IR dateIR = new jjCalendar_IR();
            int dot = dateIR.toString().indexOf("-");
            String date = "";
            String time = "";
            if (dot > -1) {
                date = dateIR.toString().substring(0, dot - 1);
                time = dateIR.toString().substring(dot + 2, dateIR.toString().length());
            }
            ServerLog.Print("DATE : " + date + " TIME : " + time);
            int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);
            ///////<-------------DATE-------
            ///------------ insert ------->
            ServerLog.Print(">>  INSERT EMAIL INFO TO DB : ");
            Map<String, Object> emailMap = new HashMap<String, Object>();
            for (int i = 0; i < address.length; i++) {
                emailMap.put(_from, from);
                emailMap.put(_to, address[i].toString());
                emailMap.put(_subject, subject);
                emailMap.put(_body, bodyTable);
                emailMap.put(_date, dateInt);
                emailMap.put(_sendTime, time);
                emailMap.put(_visit_status, "مشاهده نشده");
                emailMap.put(_visit_count, 0);
                emailMap.put(_comment, comment);
                emailMap.put(_status, "در حال ارسال");
                ServerLog.Print("EMAIL INFO : " + emailMap);
                if (db.insert(tableName, emailMap).getRowCount() != 0) {
                    ServerLog.Print("اطلاعات ایمیل درج شد.");
                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id, _from + "='" + from + "' AND " + _to + "='" + address[i].toString() + "' AND " + _subject + "='" + subject + "'" + " AND " + _date + "=" + dateInt + " AND " + _sendTime + "='" + time + "' AND " + _body + " = '" + bodyTable + "'"));
                    if (!row.isEmpty()) {
                        id = row.get(0).get(_id).toString();
                    }
                } else {
                    ServerLog.Print("اطلاعات ایمیل درج نشد.");
                }

            }
            ///<------------ insert -------
            StringBuilder script = new StringBuilder();

//            script.append(Js.setVal("#email_receiver", ""));
//            script.append(Js.setVal("#email_subject", ""));
//            script.append(Js.setHtml("#email_text", ""));
//            refresh(request, db, isPost);
//            script.append(Js.show("#showSendEmailFormBtn"));
//            script.append(Js.hide("#showEmailTableBtn"));
//            script.append(Js.hide("#formDiv"));
//            script.append(Js.show("#tableDiv"));
            body += "<img id='loadPic' alt='pic' src='" + hostName + "email/index.jpg?id=" + id + "' >";//hoda shiri

            body += "<a href=\"" + hostName + "clickTracker?click_tracker_link=" + URLEncoder.encode("Server?do=Content.sw&text=57", "UTF-8") + "&click_tracker_visitor=" + to + "\">تست</a>";
            ServerLog.Print("BODY EMAIL ^^^^^^^^^^^^^^^^^^^^^^ : " + body);
            msg.setText(body, "UTF-8");
            String htmlBody = "<p style='font-size : 20px; text-align :" + (isRtl ? "right" : "left") + " direction :" + (isRtl ? "right" : "left") + ";' >" + body + "</p>";
            msg.setContent(htmlBody, "text/html;charset=UTF-8");
            msg.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(_host, _user, _pass);

            ServerLog.Print("send========>");
            transport.sendMessage(msg, msg.getAllRecipients());
            script.append("alert('ارسال شد.');");
            ServerLog.Print("send========");
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < address.length; i++) {
                map.put(_status, "ارسال شد.");
                db.update(tableName, map, _id + "=" + id);
            }
            transport.close();
            Server.outPrinter(request, response, Js.jjEmail.refresh() + script.toString());
            return "";
        } catch (Exception e) {
//       Server.ErrorHandler(e);
            ServerLog.Print(e);
            Server.outPrinter(request, response, Js.modal("پیام سامانه ", "ارسال نشد. " + e));
            return "";
        }
    }
    //////<--------------- sendEmailWithRequest() -----------------------
//////--------------- sendEmailByParameters() ----------------------->

    public static String sendEmailByParameters(String from, String to, String subject, String body, boolean isRtl, HttpServletRequest request) {
        try {
            jjDatabaseWeb db = new jjDatabaseWeb();
            db.ConnectCustom();
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.host", _host);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(sessionDebug);
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setHeader("Content-Type", "UTF-8");
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[0];
            String[] split = null;
            if (to.indexOf(",") > 0) {
                split = to.split(",");
                address = new InternetAddress[split.length];
                for (int i = 0; i < split.length; i++) {
                    address[i] = new InternetAddress(split[i]);
                }
            } else {
                address = new InternetAddress[1];
                address[0] = new InternetAddress(to);
            }
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject, "UTF-8");
            msg.setSentDate(new Date());
            File file = new File(request.getServletContext().getRealPath("/print") + "/email.html");
            if (file.exists()) {
                body = jjFileTxt.read(file).replace("bodybody", body);
            } else {
                msg.setText(body, "UTF-8");
            }
            msg.setText(body, "UTF-8");
            String htmlBody = "<p style='font-size : 20px; text-align :" + (isRtl ? "right" : "left") + " direction :" + (isRtl ? "right" : "left") + ";' >" + body + "</p>";
            msg.setContent(htmlBody, "text/html;charset=UTF-8");
            msg.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(_host, _user, _pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            ///////-------------DATE------->

            jjCalendar_IR dateIR = new jjCalendar_IR();
            int dot = dateIR.toString().indexOf("-");
            String date = "";
            String time = "";
            if (dot > -1) {
                date = dateIR.toString().substring(0, dot - 1);
                time = dateIR.toString().substring(dot + 2, dateIR.toString().length());
            }
            ServerLog.Print("DATE : " + date + " TIME : " + time);
            int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);
            ///////<-------------DATE-------

            ///------------ insert ------->
            ServerLog.Print(">>  INSERT EMAIL INFO TO DB : ");
            Map<String, Object> emailMap = new HashMap<String, Object>();
            for (int i = 0; i < address.length; i++) {
                emailMap.put(_from, from);
                emailMap.put(_to, address[i]);
                emailMap.put(_subject, subject);
                emailMap.put(_body, body);
                emailMap.put(_date, dateInt);
                emailMap.put(_sendTime, time);
                emailMap.put(_status, " ارسال شد.");
                ServerLog.Print("EMAIL INFO : " + emailMap);
                if (db.insert(tableName, emailMap).getRowCount() != 0) {
                    ServerLog.Print("اطلاعات ایمیل درج شد.");
                } else {
                    ServerLog.Print("اطلاعات ایمیل درج نشد.");
                }
            }
            ///<------------ insert -------

            return "alert('ارسال شد.');";
        } catch (Exception e) {
//       Server.ErrorHandler(e);
            ServerLog.Print(e);
            return "alert('ارسال نشد.');";
        }
    }

    //////<--------------- sendEmailByParameters() -----------------------
    //////--------------- refresh() ----------------------->
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
           if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

//            StringBuilder script = new StringBuilder();
            StringBuilder tableBody = new StringBuilder();
            String text = "";

            String classRowType = "";
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
//            if (!row.isEmpty()) {
                tableBody.append("<table id='emailTbl' class='tahoma10' style='direction: rtl;width:982px'><thead>");
                tableBody.append(""
                        + "<th >ایمیل گیرنده</th>\n"
                        + "<th> ایمیل فرستنده</th>\n"
                        + "<th>وضعیت-موضوع ایمیل</th>\n"
                        + "<th>متن ایمیل</th>\n"
                        + "<th> زمان ارسال ایمیل</th>\n"
                        + "<th>آخرین زمان دریافت ایمیل</th>\n"
                        + "<th> وضعیت دریافت</th>\n"
                        + "<th>حذف </th>\n"
                        //   + "<th>ارسال مجدد </th>\n"
                        + "");
                tableBody.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {

                    text = (row.get(i).get(_body).toString().length() > 10) ? row.get(i).get(_body).toString().substring(0, 10) + " ..." : row.get(i).get(_body).toString();

                    tableBody.append("<tr id=\"emailRowTr" + row.get(i).get(_id).toString() + "\" >\n"
                            + "<td>" + row.get(i).get(_to).toString() + "</td>\n"
                            + "<td>" + row.get(i).get(_from).toString() + "</td>\n"
                            + "<td>" + row.get(i).get(_status).toString() + "-" + row.get(i).get(_subject).toString() + "</td>\n"
                            + "<td onmouseout=\"hideBox(" + i + ");\" onmousemove=\"showBox(" + i + ");\" >" + text.toString()
                            + "<div class=\"floatBox\" id=\"floatBox" + i + "\" style=\"right:62%;\">\n"
                            + row.get(i).get(_body).toString()
                            + "</div>\n"
                            + "</td>\n"
                            + "<td>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
                            + "<td>" + jjCalendar_IR.getViewFormat(row.get(i).get(_visit_date).toString()) + " - " + row.get(i).get(_visit_time).toString() + "</td>\n"
                            + "<td>" + row.get(i).get(_visit_status).toString() + "</td>\n"
                            + "<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"cmsEmail.m_delete(" + row.get(i).get(_id) + ");\" ></td>\n"
                            //  + "<td><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick=\"cmsEmail.m_select(" + row.get(i).get(_id) + ");\" ></td> "
                            + " </tr>");
                }
//            }
            tableBody.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swEmailTbl";
            }
            String script = Js.setHtml("#" + panel, tableBody);
            script += Js.table("#emailTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "23" : "", "لیست ایمیل");

//            script.append(Js.hide("#formDiv"));
//            script.append(Js.show("#tableDiv"));
//            script.append(Js.show("#showSendFormBtn"));
//            script.append(Js.hide("#smsTableShowBtn"));
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    //////<--------------- refresh() -----------------------
    ////// ---------------- select() ------------------->

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            ServerLog.Print(">> SELECT");
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + jjTools.getParameter(request, _id)));
            if (row.isEmpty()) {
                ServerLog.Print("این رکورد وجود ندارد.");
                Server.outPrinter(request, response, Js.modal("پیام سامانه", "این رکورد وجود ندارد."));
                return "";
            }
            script.append(Js.setVal(_from, row.get(0).get(_from)));
            script.append(Js.setVal(_to, row.get(0).get(_to)));
            script.append(Js.setVal(_subject, row.get(0).get(_subject)));
            script.append(Js.setHtml(_body, row.get(0).get(_body)));
            ServerLog.Print(script.toString());
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
////// <---------------- select() -------------------

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {

                html.append(Js.setHtml("#email_button", "<input type=\"button\" id=\"insert_email_new\" value=\"" + lbl_send + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_email_new", Js.jjEmail.insert()));//send email
                html.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())));//send email
                html.append(Js.setVal("#" + _status, "ارسال نشده، در حال ایجاد توسط کاربر..."));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//         if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
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
            if (!db.delete(tableName, _id + " = " + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessage));
                return "";
            }
//            script.append(Js.hide("#smsRowTr" + id));
            script.append("$('#emailRowTr" + id + "').remove();");
            Server.outPrinter(request, response, script.toString());
            return "";
//            return refresh(request, db, isPost);

        } catch (Exception ex) {

            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
//    public static void main(String[] args) {
//        sendEmailByParameters("a.rashidi1993@gmail.com","a_rashidi72@yahoo.com","تست","تسسست",true);
//    }
}
