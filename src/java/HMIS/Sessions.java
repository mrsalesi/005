/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.cms.Tice_config;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shohreh.shiran Date 1397.12.11 Session:صورت جلسه و دعوتنامه
 */
public class Sessions {

    public static String tableName = "hmis_sessions";
    public static String _id = "id";
    public static String _creatorId = "sessions_creatorId";//ای دی ایجاد کننده
    public static String _title = "sessions_title";//عنوان جلسه
    public static String _commetteId = "sessions_commettesId";//ایدی کمیته
    public static String _communicatorRoleId = "sessions_communicatorRoleId";//ابلاغ کننده
    public static String _invitationDate = "sessions_invitationDate";//تاریخ ارسال دعوتنامه
    public static String _date = "sessions_date";//تاریخ جلسه
    public static String _time = "sessions_time";//تایم جلسه
//    public static String _dateReminder = "sessions_dateReminder";//تاریخ یادآوری
    public static String _timeReminder = "sessions_timeReminder";//ساعت یادآوری
    public static String _Invitees = "sessions_Invitees";//مدعوین داخل سازمان نقش ها//ای دی نقش ها
    public static String _InviteesInSide = "sessions_InviteesInSide";//مهمانان داخل سازمان یوزرها
    public static String _InviteesOutSide = "sessions_InviteesOutSide";//مدعوین خارج از سازمان
    public static String _status = "sessions_status";//وضعیت
    public static String _statusLog = "sessions_statusLog";//روند وضعیت
    public static String _nextDate = "sessions_nextSessionDate";//تاریخ جلسه بعد
    public static String _otherDescription = "sessions_otherDescription";//بررسی دستور جلسه
    public static String _file = "sessions_file";// فایل های بارگذاری شده
    public static String _InviteesFile = "sessions_InviteesFile";//فایل برای مدعوین
    public static String _sessionDescription = "sessions_sessionDescription";//شرح جلسه
    public static String _audience = "sessions_audience";// حضار در جلسه 
    public static String _audienceInSide = "sessions_audienceInSide";// داخل  سازمان حضار در جلسه
    public static String _audienceOutSide = "sessions_audienceOutSide";// خارج از سازمان حضار در جلسه
    public static String _signers = "sessions_signers";// امضا کنندگان
    public static String _signersUserInSide = "sessions_signersUserInSide";// امضا کنندگان داخل سازمان
    public static String _agenda = "sessions_agenda";//دستور جلسه
    public static String _filePdfSessions = "sessions_filePdfSessions";//دستور جلسه
    public static String _absentRole = "sessions_absentRole";//اعضای غایب با نقش 
    public static String _absentUserInside = "sessions_absentUserInSide";//مهمان اخل
    public static String _absentOutSide = "sessions_absentOutSide";//دستور جلسه  
    public static String _contextInvitation = "sessions_contextInvitation";//دستور جلسه  

    public static int rul_rfs = 433;
    public static int rul_ins = 434;
    public static int rul_edt = 435;
    public static int rul_dlt = 436;
    public static int rul_rfsarchivesSessions = 447;
    public static int rul_rfsMySessions = 440;
    public static int rul_rfsCommunicatedSessions = 461;
    public static int rul_rfsAllCommunicatedSessions = 462;
    public static int rul_Communicate_CommunicateSessions = 463;//دکمه ابلاغ صورتجلسه
    public static int rul_ignore_CommunicateSessions = 464;//دکمه رد صورتجلسه 
    public static int rul_CommunicateApprovedAll_CommunicateSessions = 465;//ابلاغ همه مصوبات
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";
    public static String status_confirmationFinal = "تایید نهایی";
    public static String status_sendComment = "ارسال دعوتنامه";
    public static String status_created = "ایجاد شده";
    public static String status_communicated = "ابلاغ شده";
    public static String status_ignore = "رد شده";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();

            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT S.sessions_time,S.sessions_title,"
                    + "S.sessions_audience,S.sessions_audienceInSide,S.sessions_signers,S.sessions_signersUserInSide,"
                    + "S.sessions_date,S.sessions_status,"
                    + "S.id,C.commettes_title,C.commettes_secretary"
                    + ",A.user_name,A.user_family "
                    + "FROM hmis_sessions S"
                    + " LEFT JOIN hmis_commettes C ON S.sessions_commettesId=C.id"
                    + " LEFT JOIN hmis_role R ON C.commettes_secretary=R.id"
                    + " LEFT JOIN access_user A ON R.role_user_id=A.id"
            //                    + " INNER JOIN hmis_approved  AP ON S.id=AP.approved_sessionsId"
            //                    + " WHERE S.id having sum(AP.approved_status='" + status_communicated + "')=0 "
            ));

            html.append(" <div class=\"card-header bg-primary tx-white\">لیست جلسات</div>"
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size:33px'><i class='fa fa-refresh mg-t-30' onclick='hmisSessions.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append("<div class='table-wrapper'>");
            html.append("<table id='refreshSessions' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان جلسه</th>");
            html.append("<th width='10%'> کمیته</th>");
            html.append("<th width='10%'>دبیر کمیته</th>");
            html.append("<th width='20%'>تاریخ و ساعت شروع</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='15%'>امضا</th>");
            html.append("<th width='15%'>انتقال به میز هوشمند</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {

                html.append("<tr onclick='hmisSessions.m_select(" + row.get(i).get(_id) + ")' class='p " + getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Commettes._title) + "</td>");
                html.append("<td class='r'>" + Role.getRoleName(row.get(i).get(Commettes._secretary).toString(), db) + "</td>");
                html.append("<td class='r' style='direction:ltr'>" + row.get(i).get(_date) + "-" + row.get(i).get(_time) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                String signersUserInSide = row.get(i).get(_signersUserInSide).toString();
                String signers = row.get(i).get(_signers).toString();

                html.append("<td class='r'>");

                String audiencesInSide = row.get(i).get(_audienceInSide).toString();
                String audiences = row.get(i).get(_audience).toString();
                if (!audiences.equals("")) {
                    String[] audiencesArray = row.get(i).get(_audience).toString().split("%23A%23");
                    for (int k = 0; k < audiencesArray.length; k++) {
                        if (!row.get(i).get(_signers).equals("")) {
                            Pattern pj = Pattern.compile("(^" + audiencesArray[k] + ",)|(," + audiencesArray[k] + ",)");

                            Matcher mj = pj.matcher(signers);
                            if (mj.find()) {
                                html.append("<i class=\"icon ion-flag\" style=\"color:green;font-size: 1em;padding: 0 5px;\"></i>");
                            } else {
                                html.append("<i class=\"icon ion-flag\" style=\"color:red;font-size: 1em;padding: 0 5px;\"></i>");
                            }

                        } else {
                            html.append("<i class=\"icon ion-flag\" style=\"color:red;font-size: 1em;padding: 0 5px;\"></i>");
                        }
                    }
                }

                if (!audiencesInSide.equals("")) {
                    String[] audiencesInSideArray = row.get(i).get(_audienceInSide).toString().split("%23A%23");
                    for (int k = 0; k < audiencesInSideArray.length; k++) {
                        if (!row.get(i).get(_signersUserInSide).equals("")) {
                            String[] signerUserIdArray = (signersUserInSide).split(",");
                            Pattern pj = Pattern.compile("(^" + audiencesInSideArray[k] + ",)|(," + audiencesInSideArray[k] + ",)");
                            Matcher mj = pj.matcher(signersUserInSide);
                            if (mj.find()) {
                                html.append("<i class=\"icon ion-flag\" style=\"color:green;font-size: 1em;padding: 0 5px;\"></i>");
                            } else {
                                html.append("<i class=\"icon ion-flag\" style=\"color:red;font-size: 1em;padding: 0 5px;\"></i>");
                            }

                        } else {
                            html.append("<i class=\"icon ion-flag\" style=\"color:red;font-size: 1em;padding: 0 5px;\"></i>");
                        }
                    }
                }

                html.append("</td>");
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
                panel = "swSessionsTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshSessions", "300", 0, "", "جلسات");

//            script += Js.setHtml("#sessions_communicatorRoleId", html1);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    /**
     * آرشیو صورت جلسات
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String archivesSessionsRefresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsarchivesSessions)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _status + "='" + status_communicated + "'");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append(" <div class=\"card-header bg-primary tx-white\">لیست جلسات</div>\n");
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='archivesSessionsRefresh' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان جلسه</th>");
            html.append("<th width='15%'> کمیته</th>");
            html.append("<th width='20%'>دبیر کمیته</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='40%'>دریافت صورتجلسه</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                List<Map<String, Object>> commettedRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + row.get(i).get(_commetteId)));

                html.append("<tr class='p " + getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'>" + commettedRow.get(0).get(Commettes._title) + "</td>");
                html.append("<td class='r'>" + Role.getRoleName(commettedRow.get(0).get(Commettes._secretary).toString(), db) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                String filePdfSessions = row.get(i).get(_filePdfSessions).toString();
                String[] filePdfSessionsArray = filePdfSessions.split(",");
                html.append("<td class='r'>");
                for (int j = 0; j < filePdfSessionsArray.length; j++) {
                    if (!filePdfSessionsArray[j].equals("")) {//اگر فایلی موجود بود 
                        html.append("<a href='upload/" + filePdfSessionsArray[j] + "' title='" + filePdfSessionsArray[j] + "' target=\"_blank\" ><i class='icon ion-code-download' style='color='#000'></i></a>");

                    } else {
                        html.append("<div></div>");
                    }
                }
                html.append("</td>");

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
                panel = "swArchivesSessionsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#archivesSessionsRefresh", "300", 0, "", "جلسات");

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    /**
     * creator shohre shiran 13971211
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Commette_button", "<input type=\"button\" id=\"insert_Commette_new\" value=\"" + lbl_insert + "\" class=\"btn btn-success btn-block mg-b-10 tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Sessions_new", Js.jjSessions.insert()));
            }
            Server.outPrinter(request, response, html.toString());
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971211
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);// ای دی جلسه

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + row.get(0).get(_commetteId)));
            String commetteId = row.get(0).get(_commetteId).toString();// ای دی کمیته
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }

            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            StringBuilder html8 = new StringBuilder();
            StringBuilder html9 = new StringBuilder();
            StringBuilder html10 = new StringBuilder();
            String script = "";
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setHtml("#commettesTitle", commettesRow.get(0).get(Commettes._title) + "  -جلسه" + jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            html.append(Js.setHtml("#sessions_sessionsDate", jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            html.append(Js.setValSummerNote("#sessions_agendaSessions", row.get(0).get(_agenda)));
            html.append(Js.setVal("#sessions_titleSessions", row.get(0).get(_title).toString()));
            if (row.get(0).get(_otherDescription).equals("")) {
                script += "var $div = $('<div>');$div.load('template/otherDescription.html', function () {$('#sessions_otherDescription').summernote('code',$(this).html());});";
            } else {

                html.append(Js.setValSummerNote("#" + _otherDescription, row.get(0).get(_otherDescription)));
            }

            html.append("$('#sessions_communicatorRoleId').val([" + row.get(0).get(_communicatorRoleId) + "]);$('#sessions_communicatorRoleId').select2({  width: '100%'});");
            html.append(Js.setValSummerNote("#" + _sessionDescription, row.get(0).get(_sessionDescription)));
            html.append(Js.setValDate("#sessions_invitationDateSessions", row.get(0).get(_invitationDate)));
            html.append(Js.setVal("#" + _nextDate, jjCalendar_IR.getViewFormat(row.get(0).get(_nextDate))));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _filePdfSessions, row.get(0).get(_filePdfSessions)));

            //////////////////////////////////////
            String attachFilesSessions = row.get(0).get(_file).toString();
            String[] attachFilesSessionsArray = attachFilesSessions.split(",");
            for (int l = 0; l < attachFilesSessionsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesSessionsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesSessionsArray[l].substring(attachFilesSessionsArray[l].lastIndexOf(".") + 1, attachFilesSessionsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html8.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html8.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            if (!commettesRow.get(0).get(Commettes._regulationFile).equals("")) {
                html9.append("<div class='col-lg-12 media-body mg-l-15'>فایل های  کمیته </div>");
            }
            String attachFilesCommettesDocumnet = commettesRow.get(0).get(Commettes._documnetsFile).toString();
            String[] attachFilesCommettesDocumnetArray = attachFilesCommettesDocumnet.split(",");
            for (int l = 0; l < attachFilesCommettesDocumnetArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesCommettesDocumnetArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesCommettesDocumnetArray[l].substring(attachFilesCommettesDocumnetArray[l].lastIndexOf(".") + 1, attachFilesCommettesDocumnetArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html9.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesCommettesDocumnetArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesCommettesDocumnetArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Commettes._documnetsFile + "'  type='hidden'  value='" + attachFilesCommettesDocumnetArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {

                        html9.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesCommettesDocumnetArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Commettes._documnetsFile + "'   type='hidden'  value='" + attachFilesCommettesDocumnetArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            if (!commettesRow.get(0).get(Commettes._regulationFile).equals("")) {
                html9.append("<div class='col-lg-12 media-body mg-l-15'>فایل های آیین نامه </div>");
            }

            String attachFilesCommettesRegulation = commettesRow.get(0).get(Commettes._regulationFile).toString();
            String[] attachFilesCommettesRegulationArray = attachFilesCommettesRegulation.split(",");
            for (int l = 0; l < attachFilesCommettesRegulationArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesCommettesRegulationArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesCommettesRegulationArray[l].substring(attachFilesCommettesRegulationArray[l].lastIndexOf(".") + 1, attachFilesCommettesRegulationArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html9.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesCommettesRegulationArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesCommettesRegulationArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Commettes._regulationFile + "'  type='hidden'  value='" + attachFilesCommettesRegulationArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html9.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesCommettesRegulationArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Commettes._regulationFile + "'   type='hidden'  value='" + attachFilesCommettesRegulationArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String attachFilesPdfSessions = row.get(0).get(_filePdfSessions).toString();
            String[] attachFilesPdfSessionsArray = attachFilesPdfSessions.split(",");
            for (int l = 0; l < attachFilesPdfSessionsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesPdfSessionsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesPdfSessionsArray[l].substring(attachFilesPdfSessionsArray[l].lastIndexOf(".") + 1, attachFilesPdfSessionsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html10.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesPdfSessionsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filePdfSessions + "'  type='hidden'  value='" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html10.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesPdfSessionsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filePdfSessions + "'   type='hidden'  value='" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            //////////////////////////////////////

            String InviteesInSideId = row.get(0).get(_InviteesInSide).toString();
            String[] InviteeInSideId = (InviteesInSideId.replaceAll("#A#", "%23A%23")).split("%23A%23");
            String audiences = row.get(0).get(_audience).toString();
            String audiencesInSide = row.get(0).get(_audienceInSide).toString();
            String audiencesOutSide = row.get(0).get(_audienceOutSide).toString();
            String[] audienceName = (audiences.replaceAll("#A#", "%23A%23")).split("%23A%23");
            String[] audiencesInSideName = (audiencesInSide.replaceAll("#A#", "%23A%23")).split("%23A%23");
            String[] audiencesOutSideName = (audiencesOutSide.replaceAll("#A#", "%23A%23")).split("%23A%23");
            System.out.println("InviteeInSideId.length=" + InviteeInSideId.length);
            if (!InviteesInSideId.equals("")) {//اگر فردی ب.
                if (InviteeInSideId.length >= 1) {//اگر  فردی وجود داشته باشد
                    //دعوت شدگان مهمان داخل سازمان
                    System.out.println("InviteeInSideId=" + InviteeInSideId.length);

                    for (int i = 0; i < InviteeInSideId.length; i++) {

                        boolean flag = false;

                        if (InviteeInSideId[i].equals("ALL")) {
                            List<Map<String, Object>> userAllRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._id + ">5"));
                            for (int m = 0; m < userAllRow.size(); m++) {
                                Pattern pj = Pattern.compile("(^" + userAllRow.get(m).get(Access_User._id) + "%23A%23)|(%23A%23" + userAllRow.get(m).get(Access_User._id) + "%23A%23)");
                                Matcher mj = pj.matcher(audiencesInSide);
                                if (mj.find()) {
                                    flag = true;
                                } else {
                                }
                                String attrChecked = "";
                                if (flag == true) {
                                    attrChecked = "checked='checked'";
                                } else {
                                    attrChecked = "";
                                }
                                html4.append("<div class=\"form-group has-danger mg-b-0\">\n"
                                        + "                        <label class=\"ckbox\">\n"
                                        + "                            <input class='checkboxAudienceInSide' " + attrChecked + " id='" + userAllRow.get(m).get(Access_User._id).toString() + "' name='" + userAllRow.get(m).get(Access_User._id).toString() + "' value='" + userAllRow.get(m).get(Access_User._name).toString() + " " + userAllRow.get(m).get(Access_User._family).toString() + "' type=\"checkbox\"><span>"
                                        + "مهمان داخل سازمان" + "-" + userAllRow.get(m).get(Access_User._name).toString() + " " + userAllRow.get(m).get(Access_User._family).toString()
                                        + "</span>"
                                        + "</label>"
                                        + "</div>"
                                );
                            }
                        } else {
                            Pattern pj = Pattern.compile("(^" + InviteeInSideId[i] + "%23A%23)|(%23A%23" + InviteeInSideId[i] + "%23A%23)");
                            Matcher mj = pj.matcher(audiencesInSide);
                            if (mj.find()) {
                                flag = true;
                            } else {
                            }
                            String attrChecked = "";
                            if (flag == true) {
                                attrChecked = "checked='checked'";
                            } else {
                                attrChecked = "";
                            }
                            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + InviteeInSideId[i]));
                            html4.append("<div class=\"form-group has-danger mg-b-0\">\n"
                                    + "                        <label class=\"ckbox\">\n"
                                    + "                            <input class='checkboxAudienceInSide' " + attrChecked + " id='InviteeInSideId" + i + "' name='" + InviteeInSideId[i] + "' value='" + userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString() + "' type=\"checkbox\">"
                                    + "<span>"
                                    + "مهمان داخل سازمان" + "-" + userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()
                                    + "</span>"
                                    + "</label>"
                                    + "</div>"
                            );

                        }
                    }
                } else {
                }
            }

            //دعوتشدگان مهمان خارج از سازمان
            String InviteesOutSideId = row.get(0).get(_InviteesOutSide).toString();
            if (!InviteesOutSideId.equals("")) {
                String[] InviteeOutSideId = (InviteesOutSideId.replaceAll("#A#", "%23A%23")).split("%23A%23");
                System.out.println("InviteeOutSideId.length=" + InviteeOutSideId.length);

                System.out.println("InviteeOutSideId=" + InviteeOutSideId.length);
                for (int i = 0; i < InviteeOutSideId.length; i++) {
                    String temp = InviteeOutSideId[i];
                    String phone = "";
                    String[] InviteeOutSideDetail = InviteeOutSideId[i].split(",");
                    System.out.println("email////////////////////////////////////////" + InviteeOutSideDetail[2]);
                    phone = InviteeOutSideDetail[1];//در آوردن ایمیل فرد
                    //در این قسمت اگر مهمانانو واعضای سازمان حضور داشته باشند  در سلک تیک میخورد
                    boolean flag = false;
                    for (int j = 0; j < audiencesOutSideName.length; j++) {
                        Pattern pj = Pattern.compile("(^" + InviteeOutSideId[i] + "%23A%23)|(%23A%23" + InviteeOutSideId[i] + "%23A%23)");
                        Matcher mj = pj.matcher(audiencesOutSide);
                        if (mj.find()) {
                            flag = true;
                        } else {
                        }
                    }
                    String attrChecked = "";
                    if (flag == true) {
                        attrChecked = "checked='checked'";
                    } else {
                        attrChecked = "";
                    }
                    html4.append("<div class=\"form-group has-danger mg-b-0\">\n"
                            + "<label class=\"ckbox\">\n"
                            + " <input class='checkboxAudienceOutSide' " + attrChecked + " id='InviteeOutSideId" + i + "'  value='" + InviteeOutSideId[i] + "' type=\"checkbox\"><span>"
                            + "مهمان خارج سازمان" + "-" + InviteeOutSideId[i]
                            + "");
                    html4.append("</span>");
                    html4.append("</label>");
                    html4.append("</div>");
                }
            } else {
            }
            //دعوتشدگان مسئول در سازمان
//            if (!row.get(0).get(_Invitees).toString().equals("")) {//اگر مسئولی دعوت شده بود
            String InvitedsId = row.get(0).get(_Invitees).toString().replaceAll("#A#", "%23A%23");
            String[] invitedId = (InvitedsId).split("%23A%23");
            if (!row.get(0).get(_Invitees).toString().equals("")) {
                if (invitedId.length >= 1) {
                    System.out.println("invitedId=" + invitedId.length);
                    for (int i = 0; i < invitedId.length; i++) {
                        List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + invitedId[i]));
                        List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + roleRow.get(0).get(Role._user_id)));
                        boolean flag = false;
                        for (int j = 0; j < audienceName.length; j++) {
                            Pattern pj = Pattern.compile("(^" + invitedId[i] + "%23A%23)|(%23A%23" + invitedId[i] + "%23A%23)");
                            Matcher mj = pj.matcher(audiences);
                            if (mj.find()) {
                                flag = true;
                            } else {
                            }
                        }

                        String attrChecked = "";
                        if (flag == true) {
                            attrChecked = "checked='checked'";
                        } else {
                            attrChecked = "";
                        }
                        html4.append("<div class=\"form-group has-danger mg-b-0\">\n"
                                + "                        <label class=\"ckbox\">\n"
                                + "                            <input class='checkboxAudience' " + attrChecked + "  id='Invitee" + i + "' name='" + invitedId[i] + "' type=\"checkbox\" value='" + roleRow.get(0).get(Role._title).toString() + "-" + userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString() + "'><span>"
                                + roleRow.get(0).get(Role._title).toString() + "-" + userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()
                                + "");
                        html4.append("</span>");
                        html4.append("</label>");
                        html4.append("</div>");

                    }
                }
            } else {
            }
//            }

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            if (row.get(0).get(_status).equals(status_created)) {
                if (accEdt) {

                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button onclick='hmisSessions.m_edit();' id='edit_Sessions'  class='btn btn-outline-warning btn-block mg-b-10'>ثبت موقت</button>");
                    html2.append("</div>");
                }
                if (accEdt) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button onclick='hmisSessions.confirmationFinalSessions(" + id + ");' id='Confirmation_Sessions'  class='btn btn-outline-success  btn-block mg-b-10'>تایید نهایی وارسال به مسئول ابلاغ</button>");
                    html2.append("</div>");
                }
            }
            html2.append("<div class='sh-pagetitle-icon'>");
            html2.append("<a href='Server?do=Sessions.downloadSessions&id=" + id + "' title='چاپ صورتجلسه' target='_blank'  class='active btn-block mg-l-10'><i class='fa fa-print mg-t-3'></i></a>");
            html2.append("</div>");

            DefaultTableModel dtm = db.Select(Approved.tableName, Approved._sessionsId + "=" + id + " AND " + Approved._status + "!='" + Approved.status_communicated + "' ");
            DefaultTableModel dtm1 = db.Select(Approved.tableName, Approved._commettesId + "=" + row.get(0).get(_commetteId) + " AND " + Approved._status + "='" + Approved.status_offer + "'");
            List<Map<String, Object>> approvedRow = jjDatabase.separateRow(dtm);
            List<Map<String, Object>> approvedOffererRow = jjDatabase.separateRow(dtm1);//نمایش جدول پیشنهاد ها
            System.out.println("approvedOffererRow.size()=" + approvedOffererRow.size());
///////////////////////////////////////////////////مصوبات مربوط به آن جلسه
            html3.append("<div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshApprovedInSession' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th width='5%'>کد</th>");
            html3.append("<th width='10%'>عنوان مصوبه</th>");
            html3.append("<th width='15%'> مسئول اجرا</th>");
//            html3.append("<th width='15%'>شخص مسئول اجرا</th>");
            html3.append("<th width='20%'>مسئول پیگیری</th>");
            html3.append("<th width='15%'>تاریخ شروع </th>");
            html3.append("<th width='15%'>تاریخ پایان </th>");
            html3.append("<th width='15%'>وضعیت</th>");
            html3.append("<th width='40%'>ویرایش</th>");
            html3.append("</thead><tbody>");

            for (int i = 0; i < approvedOffererRow.size(); i++) {//پیشنهادات  برنامه عملیاتی  در جدول مصوبات که کمیته می تواند ان را رد کند یا بپذیرد

                html3.append("<tr style='background-color:#eee' class='p " + getClassCssForVaziat(approvedOffererRow.get(i).get(Approved._status).toString()) + "'>");
                html3.append("<td class='c'>" + approvedOffererRow.get(i).get(Approved._id) + "</td>");
                html3.append("<td class='r'>" + approvedOffererRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                html3.append("<td class='r'>" + Role.getRoleUserName(approvedOffererRow.get(i).get(Approved._executorRoleId).toString(), db) + "</td>");
//                html3.append("<td class='r'><div></div></td>");
                html3.append("<td class='r'>" + Role.getRoleUserName(approvedOffererRow.get(i).get(Approved._trackerId).toString(), db) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedOffererRow.get(i).get(Approved._startDate)) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedOffererRow.get(i).get(Approved._endDate)) + "</td>");
                html3.append("<td class='r' >" + approvedOffererRow.get(i).get(Approved._status) + "</td>");
                html3.append("<td class='r'><span><i class=\"ion-android-add actionIconAdd\" style=\"color:green;margin:2px 2px;cursor: pointer\" onclick=\"hmisApproved.actionIconAdd(" + approvedOffererRow.get(i).get(Approved._id) + "," + id + ");\"></i><i class=\"ion-android-remove actionIconRemove\" style=\"margin:2px 2px;cursor: pointer\" onclick=\"hmisApproved.actionIconReject(" + approvedOffererRow.get(i).get(Approved._id) + "," + id + " );\"></i></span></td>");
                html3.append("</tr>");
            }
            String trackerRoleName = "";
            for (int i = 0; i < approvedRow.size(); i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";

                if (!approvedRow.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = approvedRow.get(i).get(Approved._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("ExecutorRoleName=" + ExecutorRoleName);
                    }
                }
                if (!approvedRow.get(i).get(Approved._executorUserId).equals("")) {
                    String executorUserId = approvedRow.get(i).get(Approved._executorUserId).toString();
                    String[] executorUserIdArray = executorUserId.split(",");
                    for (int j = 0; j < executorUserIdArray.length; j++) {
                        ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        System.out.println("ExecutorUserName=" + ExecutorUserName);
                    }
                }
                html3.append("<tr onclick='hmisApproved.selectInSessions(" + approvedRow.get(i).get(Approved._id) + ");' class='p " + getClassCssForVaziat(approvedRow.get(i).get(Approved._status).toString()) + "'>");
                html3.append("<td class='c'>" + approvedRow.get(i).get(Approved._id) + "</td>");
                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                html3.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                if (!approvedRow.get(i).get(Approved._trackerId).toString().equals("")) {
                    String trackerRoleId = approvedRow.get(i).get(Approved._trackerId).toString();
                    trackerRoleName = Role.getRoleUserName(trackerRoleId, db);
                    html3.append("<td class='r'>" + trackerRoleName + "</td>");
                } else {
                    html3.append("<td class='r'><div></div></td>");
                }
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._startDate)) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._endDate)) + "</td>");
                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._status) + "</td>");
                html3.append("<td class='r'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                html3.append("</tr>");
            }

            html3.append("</tbody></table>");

            //////////////////////نمایش جدول  مصوبات قبلی در جلسات
            List<Map<String, Object>> ApprovedPrevoiusRow = jjDatabase.separateRow(db.otherSelect("SELECT hmis_approved.id"
                    + "," + Approved._startDate + "," + Approved._endDate + ","
                    + Approved._status + "," + Approved._trackerId + ","
                    + Approved._executorUserId + "," + Approved._executorRoleId
                    + "," + Approved._title
                    + "," + Approved._isActive
                    + ",hmis_sessions.sessions_commettesId FROM hmis_sessions LEFT JOIN hmis_approved "
                    + " ON hmis_sessions.id=hmis_approved.approved_sessionsid WHERE sessions_commettesId=" + commetteId + ""
                    + " AND hmis_sessions.id!=" + id + " "
                    + " AND " + Approved._status + "!='" + Approved.status_reject + "' AND "
                    + "" + Approved._status + "!='" + Approved.status_offer + "'"
                    + "AND  " + Approved._status + "!='" + Approved.status_communicated + "' AND  " + Approved._status + "!='" + Approved.status_initialRegistration + "'  "));//نمایش جلساتی که این کمیته تشکیل داده به غیر جلسه ای که داخلش هستیم
            System.out.println("ApprovedPrevoiusRow.size()=" + ApprovedPrevoiusRow.size());
            if (ApprovedPrevoiusRow.size() > 0) {
                html1.append("        <div class=\"table-wrapper\">\n");
                html1.append("<table id='refreshApprovedPreviousInSession' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
                html1.append("<th width='5%'>کد</th>");
                html1.append("<th width='10%'>عنوان مصوبه</th>");
                html1.append("<th width='15%'> مسئول اجرا</th>");
                html1.append("<th width='15%'>مسئول پیگیری</th>");
                html1.append("<th width='15%'>تاریخ شروع </th>");
                html1.append("<th width='15%'>تاریخ پایان </th>");
                html1.append("<th width='15%'>وضعیت</th>");
                html1.append("<th width='15%'>وضعیت پایش</th>");
                html1.append("<th width='20%'>ویرایش</th>");
                html1.append("</thead><tbody>");

//                for (int j = 0; j < ApprovedPrevoiusRow.size(); j++) {
//                    List<Map<String, Object>> ApprovedPrevoiusRow = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_approved WHERE hmis_approved.approved_sessionsid=" + sessionsRow.get(j).get(_id) + " AND " + Approved._status + "!='" + Approved.status_reject + "' AND " + Approved._sessionsId + "=" + sessionsRow.get(j).get(_id) + " AND " + Approved._status + "!='" + Approved.status_offer + "' "));//نمایش جلساتی که این کمیته تشکیل داده به غیر جلسه ای که داخلش هستیم
//                    if (ApprovedPrevoiusRow.size() > 0) {
                script += "$('#refreshApprovedPreviousInSession').show();";

                for (int i = 0; i < ApprovedPrevoiusRow.size(); i++) {
                    String ExecutorRoleName = "";
                    String ExecutorUserName = "";
                    if (!ApprovedPrevoiusRow.get(i).get(Approved._executorRoleId).equals("")) {
                        String executorRoleId = ApprovedPrevoiusRow.get(i).get(Approved._executorRoleId).toString();
                        String[] executorRoleIdArray = executorRoleId.split(",");
                        for (int j = 0; j < executorRoleIdArray.length; j++) {
                            ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        }
                    }
                    if (!ApprovedPrevoiusRow.get(i).get(Approved._executorUserId).equals("")) {
                        String executorUserId = ApprovedPrevoiusRow.get(i).get(Approved._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        }
                    }
                    html1.append("<tr onclick='hmisApproved.selectApprovedPrevious(" + ApprovedPrevoiusRow.get(i).get(Approved._id) + ")' class='p " + getClassCssForVaziat(ApprovedPrevoiusRow.get(i).get(Approved._status).toString()) + "'>");
                    html1.append("<td class='c'>" + ApprovedPrevoiusRow.get(i).get(Approved._id) + "</td>");
                    html1.append("<td class='r'>" + ApprovedPrevoiusRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                    html1.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                    html1.append("<td class='r'>" + Role.getRoleUserName(ApprovedPrevoiusRow.get(i).get(Approved._trackerId).toString(), db) + "</td>");
                    html1.append("<td class='r'>" + jjCalendar_IR.getViewFormat(ApprovedPrevoiusRow.get(i).get(Approved._startDate)) + "</td>");
                    html1.append("<td class='r'>" + jjCalendar_IR.getViewFormat(ApprovedPrevoiusRow.get(i).get(Approved._endDate)) + "</td>");
                    html1.append("<td class='r'>" + ApprovedPrevoiusRow.get(i).get(Approved._status) + "</td>");
                    String activeApprovedPrevious = ApprovedPrevoiusRow.get(i).get(Approved._isActive).toString().equals("0") ? "غیر نهایی" : "نهایی";
                    html1.append("<td class='r'>" + activeApprovedPrevious + "</td>");
                    html1.append("<td class='r'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                    html1.append("</tr>");

                }

                html1.append("</tbody></table>");

            } else {
            }
            if (!row.get(0).get(_signers).equals("")) {
                html5.append("<div class='card-body pd-sm-30'>"
                        + "<div class='card-header btn-teal tx-white' style='direction:rtl'>(نقش)امضا کنندگان سازمان:</div>");
                String[] signerRoleIdArray = row.get(0).get(_signers).toString().split(",");
                for (int i = 0; i < signerRoleIdArray.length; i++) {
                    html5.append(Role.getRoleUserName(signerRoleIdArray[i], db));
                    html5.append("<br/>");
                }
                html5.append("</div>");
            }
            if (!row.get(0).get(_signersUserInSide).equals("")) {
                html5.append("<div class='card-body pd-sm-30'>"
                        + "<div class='card-header btn-teal tx-white' style='direction:rtl'>(شخص)امضا کنندگان سازمان داخل سازمان:</div>");
                String[] signerUserIdArray = row.get(0).get(_signersUserInSide).toString().split(",");
                for (int i = 0; i < signerUserIdArray.length; i++) {
                    html5.append(Access_User.getUserName(signerUserIdArray[i], db));
                    html5.append("<br/>");
                }
                html5.append("</div>");
            }

            //////////////////////////////////////////////////////////////
            script += Js.setHtml("#approvedPreviousTbl", html1);//جدول مصوبات قبلی
            script += Js.setHtml("#Sessions_button", html2);
            script += Js.table("#refreshApprovedInSession", "300", 0, "", "جلسات");
            script += Js.table("#refreshApprovedPreviousInSession", "300", 0, "", "جلسات");
            script += Js.setHtml("#approvedTbl", html3);//جدول مصوبه ها
            script += Js.setHtml("#audience", html4);//مدعوین
            script += Js.setHtml("#signers", html5);//امضا
            script += html.toString();
            script += Js.setHtml("#filesDownloadDiv", html8);//فایل های بارگذاری شده
            script += Js.setHtml("#commettesFileDiv", html9);//فایل های بارگذاری شده
            script += Js.setHtml("#filePdfSessionsDiv", html10);//فایل های بارگذاری شده
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971211
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "hmis_sessions_id");

            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String script = "";
            Map<String, Object> map = new HashMap<String, Object>();
            if (jjTools.getParameter(request, _communicatorRoleId).equals("null")) {
                map.put(_communicatorRoleId, 0);
            } else {
                map.put(_communicatorRoleId, jjTools.getParameter(request, _communicatorRoleId));
            }
            map.put(_agenda, jjTools.getParameter(request, _agenda));
            map.put(_otherDescription, jjTools.getParameter(request, _otherDescription));
            map.put(_nextDate, jjTools.getParameter(request, _nextDate).replaceAll("/", ""));
            map.put(_sessionDescription, jjTools.getParameter(request, _sessionDescription));
            map.put(_audienceInSide, jjTools.getParameter(request, _audienceInSide).replaceAll("#A#", "%23A%23"));
            map.put(_audience, jjTools.getParameter(request, _audience).replaceAll("#A#", "%23A%23"));
            map.put(_audienceOutSide, jjTools.getParameter(request, _audienceOutSide).replaceAll("#A#", "%23A%23"));
            map.put(_file, jjTools.getParameter(request, _file).replaceAll("#A#", "%23A%23"));
            map.put(_filePdfSessions, jjTools.getParameter(request, _filePdfSessions).replaceAll("#A#", "%23A%23"));
            map.put(_absentOutSide, jjTools.getParameter(request, _absentOutSide).replaceAll("#A#", "%23A%23"));
            map.put(_absentRole, jjTools.getParameter(request, _absentRole).replaceAll("#A#", "%23A%23"));
            map.put(_absentUserInside, jjTools.getParameter(request, _absentUserInside).replaceAll("#A#", "%23A%23"));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {

                script += Js.jjSessions.showTbl();
//                script += Js.jjSessions.select(id);
//                script += Js.modal("تغییرات انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
//            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     *
     * تایید نهایی صورتجلسه و ارسال به مسئول ابلاغ
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String confirmationFinalSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//           if (!Access_User.hasAccess(request, db, rul_confirmationFinalSessions)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            String id = jjTools.getParameter(request, _id);//ای دی جلسات
            String script = "";

            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_otherDescription, jjTools.getParameter(request, _otherDescription));
            map.put(_agenda, jjTools.getParameter(request, _agenda));
            map.put(_communicatorRoleId, jjTools.getParameter(request, _communicatorRoleId));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_nextDate, jjTools.getParameter(request, _nextDate).replaceAll("/", ""));
            map.put(_sessionDescription, jjTools.getParameter(request, _sessionDescription));
            map.put(_audience, jjTools.getParameter(request, _audience).replaceAll("#A#", "%23A%23"));
            map.put(_audienceInSide, jjTools.getParameter(request, _audienceInSide).replaceAll("#A#", "%23A%23"));
            map.put(_audienceOutSide, jjTools.getParameter(request, _audienceOutSide).replaceAll("#A#", "%23A%23"));
            map.put(_filePdfSessions, jjTools.getParameter(request, _filePdfSessions).replaceAll("#A#", "%23A%23"));
            String audienceOutSides = jjTools.getParameter(request, _audienceOutSide).toString();
            map.put(_absentOutSide, jjTools.getParameter(request, _absentOutSide).replaceAll("#A#", "%23A%23"));
            map.put(_absentRole, jjTools.getParameter(request, _absentRole).replaceAll("#A#", "%23A%23"));
            map.put(_absentUserInside, jjTools.getParameter(request, _absentUserInside).replaceAll("#A#", "%23A%23"));
            String[] audienceOutSide = (audienceOutSides.replaceAll("#A#", "%23A%23")).split("%23A%23");//ایمیل افراد خارج از سازمان
            for (int i = 0; i < audienceOutSide.length; i++) {
                System.out.println("@To Do send Email");//ارسال ایمیل به ایمیل های موجوددر دیتا بیس
            }
            //            if (!jjTools.getParameter(request, _audience).equals("")) {
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                script += Js.modal(errorMessage, "پیام سامانه");
            } else {
                String result = changeStatus(db, id, status_confirmationFinal);
                if (result.isEmpty()) {
                    StringBuilder html = new StringBuilder();
                    html.append("<a href=" + Server.mainSite + "Server?do=Sessions.selectMySessions&id=" + id + "></a>");
                    script += Js.modal("ارسال به مسئول ابلاغ انجام شد", "پیام سامانه");
                    script += Js.jjSessions.select(id);
                    script += Js.jjSessions.refresh();
                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_sendSessionToCommunicatorRole_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        String text = tice_configMessage;
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _communicatorRoleId), db), "1", "sms,app,email", "", "صورتجلسه" + Row.get(0).get(_title) + "", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    } else {
                        String text = "لطفا وارد سامانه مدیار شوید و صورتجلسه را ابلاغ کنید";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _communicatorRoleId), db), "1", "sms,app,email", "", "صورتجلسه" + Row.get(0).get(_title) + "", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                } else {
                    script += Js.modal(".ارسال به مسئول ابلاغ انجام نشد", "پیام سامانه");
                }
            }
//            } else {//در این خط کد اگر حضار را انتخاب نکرده باشد نمیتوان تایید نهایی انجام شود
//                String errorMessage = "حضار در جلسه انتخاب نمایید.";
//                script += Js.modal(errorMessage, "پیام سامانه");
//            }

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ارسال پیام برای مدعوین انتخاب شده13971212 درج اطلاعات
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String Invitees(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String script = "";
            String commettesId = jjTools.getParameter(request, "commettesId");//شماره های مدعوین برای ارسال پیام
            List<Map<String, Object>> ComemttesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + commettesId));
            if (ComemttesRow.get(0).get(Commettes._isActive).equals("1")) {//وضعیت کمیته چک می شود اگر غیر فعال بود پیامی ارسال نمی شود
                String inviteesId = jjTools.getParameter(request, _Invitees).replaceAll("#A#", "%23A%23");//ای دی مدعوین برای ارسال پیام
                String inviteesOutSideId = jjTools.getParameter(request, _InviteesOutSide).replaceAll("#A#", "%23A%23");//شماره های مدعوین خارج از سازمان برای ارسال پیام
                String inviteesInSideId = jjTools.getParameter(request, _InviteesInSide).replaceAll("#A#", "%23A%23");//شماره های مدعوین داخل سازمان برای ارسال پیام
                Map<String, Object> map = new HashMap<>();
                map.put(_creatorId, jjTools.getSeassionUserId(request));//ایدی مدعوین
                map.put(_Invitees, inviteesId);//ایدی مدعوین
                map.put(_InviteesFile, jjTools.getParameter(request, _InviteesFile).replaceAll("#A#", "%23A%23"));//ای دی های مدعوین
                map.put(_InviteesOutSide, inviteesOutSideId);//ای دی های مدعوین کاراکتر های %23َ%23 باید باشد
                map.put(_InviteesInSide, inviteesInSideId);// داخل سازمان ای دی های مدعوین
                map.put(_title, jjTools.getParameter(request, _title));//عنوان جلسه
                map.put(_date, jjTools.getParameter(request, _date).replaceAll("/", ""));//تاریخ جلسه
                map.put(_time, jjTools.getParameter(request, _time));//ساعت جلسه
                map.put(_agenda, jjTools.getParameter(request, _agenda));//دستور جلسه
                map.put(_timeReminder, jjTools.getParameter(request, _timeReminder));//ساعت یاد آوری
                map.put(_invitationDate, jjTools.getParameter(request, _invitationDate).replaceAll("/", ""));//تاریخ ارسال دعوتنامه
                map.put(_contextInvitation, jjTools.getParameter(request, _contextInvitation));//متن دعوتنامه
                map.put(_commetteId, commettesId);//ای دی کمیته
                map.put(_status, status_created);//
                map.put(_statusLog,
                        status_created
                        + "-"
                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                        + " "
                        + new jjCalendar_IR().getTimeFormat_8length()
                        + "%23A%23"
                );
                //اگر تاریخ ارسال به اعضا با تاریخ امروز یکی بود همان  موقع ارسال شود 
//            if (jjTools.getParameter(request, _invitationDate).equals(jjCalendar_IR.getDatabaseFormat_8length("", true))) {
//                
//            }else{}
//            

                DefaultTableModel dtm = db.insert(tableName, map);

                if (dtm.getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    script += Js.modal(errorMessage, "پیام سامانه");
                    Server.outPrinter(request, response, script);
                    return "";
                }
                List<Map<String, Object>> insertedSessionsRow = jjDatabaseWeb.separateRow(dtm);
                jjCalendar_IR date = new jjCalendar_IR(insertedSessionsRow.get(0).get(_invitationDate).toString());
                int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
//                if (date.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                String invitees = insertedSessionsRow.get(0).get(Sessions._Invitees).toString().replaceAll("%23A%23", ",").replaceAll("#A#", ",");
                String reciver = "";
                String[] inviteesArray = invitees.split(",");
                for (int i = 0; i < inviteesArray.length; i++) {
                    reciver += Role.getUeserIdByUserRole(inviteesArray[i], db) + ",";
                }
                reciver += insertedSessionsRow.get(0).get(Sessions._InviteesInSide).toString().replaceAll("%23A%23", ",").replaceAll("#A#", ",");
                String[] inviteesOutSideArray = insertedSessionsRow.get(0).get(Sessions._InviteesOutSide).toString().split("%23A%23");
                if (!insertedSessionsRow.get(0).get(Sessions._InviteesOutSide).equals("")) {
                    for (int i = 0; i < inviteesOutSideArray.length; i++) {
                        String[] inviteesOutSideDetail = inviteesOutSideArray[i].split(",");
                        String mobile = inviteesOutSideDetail[1];
                        String email = inviteesOutSideDetail[2];
                        String name = inviteesOutSideDetail[0];
                        System.out.println("name=" + inviteesOutSideDetail[0]);
                        System.out.println("email=" + inviteesOutSideDetail[2]);
                        List<Map<String, Object>> accessUserRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._email + "='" + email + "'"));
                        if (accessUserRow.size() == 0) {
                            Map<String, Object> mapAccessUser = new HashMap<>();
                            mapAccessUser.put(Access_User._name, name);
                            mapAccessUser.put(Access_User._mobile, mobile);
                            mapAccessUser.put(Access_User._isActive, "1");
                            mapAccessUser.put(Access_User._email, email);
                            mapAccessUser.put(Access_User._parent, jjTools.getSeassionUserId(request));
                            DefaultTableModel dtmAccessUser = db.insert(Access_User.tableName, mapAccessUser);
                            if (dtmAccessUser.getRowCount() == 0) {
                                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                                if (jjTools.isLangEn(request)) {
                                    errorMessage = "Edit Fail;";
                                }
                                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                                return "";
                            }
                            List<Map<String, Object>> UserRow = jjDatabase.separateRow(dtmAccessUser);
                            reciver += UserRow.get(0).get(Access_User._id);
                        } else {
                            reciver += accessUserRow.get(0).get(Access_User._id);
                        }
                    }
                }
                System.out.println("reciver=/////////////" + reciver);          
                String invitationDate = insertedSessionsRow.get(0).get(Sessions._invitationDate).toString();
                String text = "" + insertedSessionsRow.get(0).get(_contextInvitation).toString() + "";
                StringBuilder html = new StringBuilder();
                html.append(
                        "<div style='direction:rtl'>"
                //                                + "<p>متن دعوتنامه : " + insertedSessionsRow.get(0).get(_contextInvitation).toString() + "</p>"
                );
                html.append("<h1>عنوان جلسه:" + insertedSessionsRow.get(0).get(_title).toString() + "</h1>");
                html.append("<h2>تاریخ  جلسه:" + insertedSessionsRow.get(0).get(_date).toString() + "</h2>");
                html.append("<h2>ساعت  جلسه:" + insertedSessionsRow.get(0).get(_time).toString() + "</h2>");
                html.append("<a href=" + Server.mainSite + "Server?do=Sessions.selectMySessions&id=" + insertedSessionsRow.get(0).get(_id).toString() + ">لینک صورتجلسه</a>");
                html.append("<p>متن دستور جلسه :"
                        + insertedSessionsRow.get(0).get(_agenda) + "</p></div>"
                );
                String attachFiles = insertedSessionsRow.get(0).get(Sessions._InviteesFile).toString();
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

//                html.append("<a href=upload/" + insertedSessionsRow.get(0).get(_InviteesFile) + ">فایل </a>");
                Messenger.sendMesseage(null, db, reciver, "1", "sms,app,email", invitationDate, "دعوتنامه کمیته ی " + ComemttesRow.get(0).get(Commettes._title) + " : " + insertedSessionsRow.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                System.out.println("<<<<<<<ارسال پیام به به دعوتشدگانSend MESSAGE()");
                System.out.println("#################################################");
//                }
                script += "hmisCommettes.m_show_tbl();";
                script += Js.modal("از قسمت میز هوشند جلسات وضعیت دعوتنامه وجلسه را می توانید بررسی کنید.", "پیام سامانه");

            } else if (ComemttesRow.get(0).get(Commettes._isActive).equals("0")) {
                String errorMessage = "کمیته مورد نظر فعال نمی باشد،پیامی ارسال نشد .";
                script += Js.modal(errorMessage, "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String changeStatus(jjDatabaseWeb db, String id, String newSatus) {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                return Js.dialog(errorMessageId);
            }
            String oldStatus = jjDatabaseWeb.separateRow(db.Select(tableName, _status, _id + "=" + id)).get(0).get(_status).toString();

            if (!oldStatus.equals(newSatus)) {
                db.otherStatement("UPDATE " + tableName + " SET " + _statusLog
                        + "=concat(ifnull(" + _statusLog + ",''),'"
                        + newSatus
                        + "-"
                        + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                        + " "
                        + new jjCalendar_IR().getTimeFormat_8length()
                        + "%23A%23"
                        + "') ,"
                        + _status + "='" + newSatus + "'  WHERE id=" + id + ";");
            }
            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            return "عملیات تغییر وضعیت بدرستی صورت نگرفت. Err166";
        }
    }

    /**
     * جدول جلسات من در این جدول جلساتی که برگزار شده است برای امضا وتایید سمت
     * حضار می اید وحضار آن را تایید می کنند ویژگی تابع این است که برای دعوت
     * شدگان نمایش داده می شود
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMySessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMySessions)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            int sessionUserId = jjTools.getSeassionUserId(request);// 
            html.append("<div class='card-header bg-primary tx-white'> لیست جلسات من</div>"
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisMySessions.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append("<div class='table-wrapper'>");
            html.append("<table id='refreshMySessions' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان جلسه</th>");
            html.append("<th width='15%'> کمیته</th>");
            html.append("<th width='20%'>دبیر کمیته</th>");
            html.append("<th width='20%'>تاریخ و ساعت شروع</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='40%'>امضای صورتجلسه</th>");
            html.append("</thead><tbody>");
            String RoleId = jjTools.getSeassionUserRole(request);// 
            String[] RoleIdArray = jjTools.getSeassionUserRole(request).split(",");// 
            String like = "";
            for (int i = 0; i < RoleIdArray.length; i++) {
                System.out.println("role" + RoleIdArray[i]);
                like += " sessions_audience   LIKE '%" + RoleIdArray[i] + "%' OR";
            }

            List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_sessions WHERE "
                    + "(" + like.substring(0, like.length() - 2) + ") "
                    + " AND sessions_status='" + Sessions.status_confirmationFinal + "' "
                    + " OR ( sessions_audienceInSide  LIKE '%" + jjTools.getSeassionUserId(request) + "%'  ) "
                    + ""));
            //در جدول جلسات  در فیلد حضار داخل سازمان ای دی فردی که وارد شد شبیه یکی از این فیلد ها بود پیدا کن اگر جز ایجاد کننده ها بود  نمایش داده شود اگر وضعیت تایید نهایی هم بود نمایش داده شود
            for (int i = 0; i < sessionsRow.size(); i++) {
                List<Map<String, Object>> commettedRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + sessionsRow.get(i).get(Sessions._commetteId)));
                html.append("<tr>");
                html.append("<td class='c'>" + sessionsRow.get(i).get(Sessions._id) + "</td>");
                html.append("<td class='r'>" + sessionsRow.get(i).get(Sessions._title) + "</td>");
                html.append("<td class='r'>" + commettedRow.get(0).get(Commettes._title) + "</td>");
                List<Map<String, Object>> RoleRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + commettedRow.get(0).get(Commettes._secretary)));
                List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RoleRow.get(0).get(Role._user_id)));
                html.append("<td class='r'>" + userRow.get(0).get(Access_User._name) + " " + userRow.get(0).get(Access_User._family) + "</td>");
                html.append("<td class='r' style='direction:ltr'>" + sessionsRow.get(i).get(Sessions._date) + "-" + sessionsRow.get(i).get(Sessions._time) + "</td>");
                html.append("<td class='r'>" + sessionsRow.get(i).get(Sessions._status) + "</td>");
                html.append("<td class='r'><a href=\"Server?do=Sessions.selectMySessions&id=" + sessionsRow.get(i).get(Sessions._id) + "\" target=\"_blank\"><i class='p icon fa fa-pencil' style='color:#0866c6'></i></a></td>");
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
                panel = "swMySessionsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMySessions", "300", 0, "", "جلسات");

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    /**
     * جلسات ابلاغ شده جلساتی که وضعیت آنها تایید نهایی هسند برای ابلاغ مصوبات
     * توسط ابلاغ کننده در این جدول می آیند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshCommunicatedSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsCommunicatedSessions)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            boolean accRfsAll = Access_User.hasAccess(request, db, rul_rfsAllCommunicatedSessions);
//            boolean accRfsMe = Access_User.hasAccess(request, db, rul_rfsMeCommunicatedSessions);
            String OtherSelect = "";
            String RoleId = "";
            if (accRfsAll) {
                OtherSelect = "SELECT"
                        + " S.id,S.sessions_title "
                        + ",C.commettes_title,C.commettes_secretary,A.user_name,A.user_family,S.sessions_communicatorRoleId"
                        + ",S.sessions_date,S.sessions_time"
                        + ",S.sessions_status"
                        + " FROM hmis_sessions S"
                        + " LEFT JOIN  hmis_commettes C      ON   S.sessions_commettesId=C.id"
                        + " LEFT JOIN  hmis_role      R      ON   C.commettes_secretary=R.id"
                        + " LEFT JOIN  access_user    A      ON   R.role_user_id=A.id"
                        + " WHERE  S.sessions_status!='" + status_created + "' ";
            } else {
                if (!RoleId.equals("")) {
                    RoleId = jjTools.getSeassionUserRole(request);// 
                    String[] RoleIdArray = jjTools.getSeassionUserRole(request).split(",");// 
                    String roleCondition = "";
                    for (int i = 0; i < RoleIdArray.length; i++) {
                        System.out.println("role" + RoleIdArray[i]);
                        roleCondition += " S.sessions_communicatorRoleId =  " + RoleIdArray[i] + " OR";
                    }
                    OtherSelect = "SELECT"
                            + " S.id,S.sessions_title "
                            + ",C.commettes_title,C.commettes_secretary,A.user_name,A.user_family,S.sessions_communicatorRoleId"
                            + ",S.sessions_date,S.sessions_time"
                            + ",S.sessions_status"
                            + " FROM hmis_sessions S"
                            + " LEFT JOIN  hmis_commettes C      ON   S.sessions_commettesId=C.id"
                            + " LEFT JOIN  hmis_role      R      ON   C.commettes_secretary=R.id"
                            + " LEFT JOIN  access_user    A      ON   R.role_user_id=A.id"
                            + " WHERE  S.sessions_status!='" + status_created + "' "
                            + "AND (" + roleCondition.substring(0, roleCondition.length() - 2)
                            + ")";
                } else {
                    Server.outPrinter(request, response, Js.modal("نقشی وجود ندارد", "پیام سامانه"));
                }
            }
            DefaultTableModel dtm = db.otherSelect(OtherSelect);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            int sessionUserId = jjTools.getSeassionUserId(request);// ای دی فرد وارد شونده
            html.append(""
                    + "<div class=\"card-header bg-primary tx-white\"> لیست جلسات من</div>\n"
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>\n"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisCommunicatedSessions.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");

            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshCommunicatedSessions' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان جلسه</th>");
            html.append("<th width='15%'> کمیته</th>");
            html.append("<th width='20%'>دبیر کمیته</th>");
            html.append("<th width='20%'>مسئول ابلاغ</th>");
            html.append("<th width='20%'>تاریخ و ساعت شروع</th>");
            html.append("<th width='5%'>وضعیت</th>");
            html.append("<th width='5%'>انتقال به میز هوشمند</th>");
            html.append("</thead><tbody>");

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='p " + getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + row.get(i).get(Sessions._id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Sessions._title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Commettes._title) + "</td>");
                html.append("<td class='r'>" + Role.getRoleName(row.get(i).get(Commettes._secretary).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleName(row.get(i).get(Sessions._communicatorRoleId).toString(), db) + "</td>");
                html.append("<td class='r' style='direction:ltr'>" + row.get(i).get(Sessions._date) + "-" + row.get(i).get(Sessions._time) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Sessions._status) + "</td>");
                html.append("<td class='r' onclick='hmisCommunicatedSessions.m_select(" + row.get(i).get(Sessions._id) + ")'>"
                        + "<i class='icon ion-coffee' style='color:#a02311'></i>"
                        + "</td>");
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
                panel = "swCommunicatedSessionsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCommunicatedSessions", "300", 0, "", "جلسات");

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    /**
     * نمایش صورت جلسه وپرینت
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String downloadSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, Sessions._id);// ای دی جلسه

            request.setAttribute("db", db);
            System.out.println("------->>>>>template/sessionPrint.jsp");
            request.getRequestDispatcher("template/sessionPrint.jsp").forward(request, response);

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectMySessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, Sessions._id);// ای دی جلسه

            request.setAttribute("db", db);
            System.out.println("------->>>>>template/showSessions.jsp");

            request.getRequestDispatcher("template/showSessions.jsp").forward(request, response);

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ صورت جلسه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectCommunicatedSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, "communicatedSessions_id");// ای دی جلسه

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + row.get(0).get(_commetteId)));
            String commetteId = row.get(0).get(_commetteId).toString();// ای دی کمیته
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();

            String script = "";
            html.append(Js.setVal("#communicatedSessions_id", row.get(0).get(_id)));
            html.append(Js.setHtml("#commettesTitleCommunicatedSessions", commettesRow.get(0).get(Commettes._title) + "-جلسه" + row.get(0).get(_date)));
            html.append(Js.setHtml("#communicatedSessions_sessionsDate", jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            html.append(Js.setValSummerNote("#communicatedSessions_agendaSessions", row.get(0).get(_agenda)));
            html.append(Js.setVal("#communicatedSessions_titleSessions", row.get(0).get(_title)));
            html.append(Js.setValSummerNote("#communicatedSessions_otherDescription", row.get(0).get(_otherDescription)));
            html.append(Js.setHtml("#communicatedSessions_communicatorId", Role.getRoleUserName(row.get(0).get(_communicatorRoleId).toString(), db)));
            html.append(Js.setValSummerNote("#communicatedSessions_sessionDescription", row.get(0).get(_sessionDescription)));
            html.append(Js.setVal("#communicatedSessions_invitationDate", jjCalendar_IR.getViewFormat(row.get(0).get(_invitationDate))));
            html.append(Js.setVal("#communicatedSessions_nextDate", jjCalendar_IR.getViewFormat(row.get(0).get(_nextDate))));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));

//            html4.append("<div class='col-lg-12 media-body mg-l-15'>Pdfفایل نهایی صورت جلسه</div>");
            String attachFilesPdfSessions = row.get(0).get(_filePdfSessions).toString();
            String[] attachFilesPdfSessionsArray = attachFilesPdfSessions.split(",");
            for (int l = 0; l < attachFilesPdfSessionsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesPdfSessionsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesPdfSessionsArray[l].substring(attachFilesPdfSessionsArray[l].lastIndexOf(".") + 1, attachFilesPdfSessionsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesPdfSessionsArray[l] + "'>"
                                + "<button class='btn btn-outline-success  btn-block mg-b-10'>Pdfفایل نهایی صورت جلسه"
                                + "</button>"
                                + "<input class='" + _filePdfSessions + "'  type='hidden'  value='" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesPdfSessionsArray[l] + "'>"
                                + "<button class='btn btn-outline-success  btn-block mg-b-10'>Pdfفایل نهایی صورت جلسه"
                                + "</button>"
                                + "</a>"
                                + "<input class='" + _filePdfSessions + "'   type='hidden'  value='" + attachFilesPdfSessionsArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String attachFiles = row.get(0).get(_file).toString();
            String[] attachFilesArray = attachFiles.split(",");
            for (int l = 0; l < attachFilesArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                    html1.append("<div class='col-lg-12 media-body mg-l-15'>فایل های صورت جلسه</div>");
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html1.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html1.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            //دکمه ابلاغ برای مسئول ابلاغ
            //براساس نقش
//            String communicatorRoleId = row.get(0).get(_communicatorRoleId).toString();
//            Pattern p1 = Pattern.compile("(^" + communicatorRoleId + ",)|(," + communicatorRoleId + ",)");// در صورتی که فرد نقشش این باشه
//            String RolesId = jjTools.getSessionAttribute(request, "#ROLE_ID");
//            Matcher m1 = p1.matcher(RolesId);   // get a matcher object
            boolean accCommunicateBtn = Access_User.hasAccess(request, db, rul_Communicate_CommunicateSessions);
            boolean accIgnoreBtn = Access_User.hasAccess(request, db, rul_ignore_CommunicateSessions);
            boolean accCommunicateApprovedAllBtn = Access_User.hasAccess(request, db, rul_CommunicateApprovedAll_CommunicateSessions);
//            if (row.get(0).get(_status).equals(status_confirmationFinal) && m1.find()) {//در صورتی که بخواهیم از روی نقش بهش دسترسی بدهیم
            if (row.get(0).get(_status).equals(status_confirmationFinal)) {
                System.out.println("jjTools.getSeassionUserId(request)=" + jjTools.getSeassionUserId(request) + "row.get(0).get(_communicatorId)" + row.get(0).get(_communicatorRoleId));
                if (accCommunicateBtn) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button  id='sendToCommunicator_communicatedSessions' class='btn btn-outline-success  btn-block mg-b-10'  onclick='hmisCommunicatedSessions.sendToCommunicator(" + id + ");' >ابلاغ صورتجلسه</button>");
                    html2.append("</div>");
                }

                if (accIgnoreBtn) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button id='ignore_communicatedSessions' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisCommunicatedSessions.ignore(" + id + ");' >رد صورتجلسه</button>");
                    html2.append("</div>");
                }
            }

            html2.append("<div class=\"col-lg-6\">");
            html2.append("</div>");

//دراین سلکت گفته شده که دو وضعیت ردپیشنهااد و پیشنهاد نمایش داده نشود
            DefaultTableModel dtm = db.otherSelect("SELECT * FROM hmis_approved WHERE " + Approved._sessionsId + "=" + id
                    + " AND " + Approved._status + "!='" + Approved.status_reject + "' "
                    + " AND " + Approved._status + "!='" + Approved.status_offer + "' "
                    + " AND " + Approved._status + "!='" + Approved.status_communicated + "'  ");

            List<Map<String, Object>> approvedRow = jjDatabase.separateRow(dtm);
            if (approvedRow.size() > 0 && accCommunicateApprovedAllBtn) {
//                if (row.get(0).get(_status).equals(status_communicated) && m1.find()) {//دکمه ابلاغ تمام مصوبه ها در صورتی که براساس نقش باشد
                if (row.get(0).get(_status).equals(status_communicated)) {//دکمه ابلاغ تمام مصوبه ها
                    //اگر وضعیت جلسه ابلاغ شده بود
                    html2.append("<div class=\"col-lg-12\">");
                    html2.append("<button id='communicateAll_communicatedSessions' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisCommunicatedSessions.communicateApprovedAll(" + id + ");' >ابلاغ همه مصوبات </button>");
                    html2.append("</div>");
                }
            }

            html3.append("<div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshApprovedIncommunicatedSessions' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th width='5%'>کد</th>");
            html3.append("<th width='10%'>عنوان مصوبه</th>");
            html3.append("<th width='15%'>مسئول اجرا</th>");
            html3.append("<th width='20%'>مسئول پیگیری</th>");
            html3.append("<th width='15%'>تاریخ شروع </th>");
            html3.append("<th width='15%'>تاریخ پایان </th>");
            html3.append("<th width='15%'>وضعیت</th>");
            html3.append("<th width='15%'>عملیات</th>");
            html3.append("<th width='40%'>نمایش</th>");
            html3.append("</thead><tbody>");
            for (int i = 0; i < approvedRow.size(); i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";

                html3.append("<tr class='p " + Sessions.getClassCssForVaziat(approvedRow.get(i).get(Approved._status).toString()) + "'>");
                html3.append("<td class='c'>" + approvedRow.get(i).get(Approved._id) + "</td>");
                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                if (!approvedRow.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = approvedRow.get(i).get(Approved._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    System.out.println("executorRoleId=" + executorRoleId);
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("executorRoleIdArray=" + executorRoleIdArray[j]);

                    }
                }
                if (!approvedRow.get(i).get(Approved._executorUserId).equals("")) {
                    String executorUserId = approvedRow.get(i).get(Approved._executorUserId).toString();
                    String[] executorUserIdArray = executorUserId.split(",");
                    for (int j = 0; j < executorUserIdArray.length; j++) {
                        ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        System.out.println("executorRoleIdArray=" + ExecutorUserName);
                    }
                }
                html3.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                html3.append("<td class='r'>" + Role.getRoleUserName(approvedRow.get(i).get(Approved._trackerId).toString(), db) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._startDate)) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._endDate)) + "</td>");
                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._status) + "</td>");
                if (accCommunicateApprovedAllBtn) {
                    if (row.get(0).get(_status).equals(status_communicated) && approvedRow.get(i).get(Approved._status).equals(Approved.status_initialRegistration)) {//دکمه ابلغ مصوبه زمانی فعال می شود که خود صورت جلسه ابلاغ شده باشد
                        html3.append("<td class='r'><button class='btn btn-outline-success  btn-block mg-b-10' onclick='hmisApproved.communicatedApproved(" + approvedRow.get(i).get(Approved._id) + ");'> ابلاغ مصوبه</button></td>");
                    } else {
                        html3.append("<td class='r'><div></div></td>");
                    }
                }
                html3.append("<td class='r' onclick='hmisApproved.selectInCommunicatedSessions(" + approvedRow.get(i).get(Approved._id) + ");' ><i class='fa fa-eye' style='color:#a02311'></i></td>");
                html3.append("</tr>");
            }
            html3.append("</tbody></table>");

            //////////////////////////////////////////////////////////////
            script += Js.setHtml("#communicatedSessions_button", html2);
//            script += Js.setHtml("#filesPdfSessions", html2);
            script += Js.table("#refreshApprovedIncommunicatedSessions", "300", 0, "", "مصوبات");
            script += Js.setHtml("#communicatedApprovedTbl", html3);
            script += html.toString();
            script += Js.setHtml("#communicatedApproved_executorRoleId", html5);
            script += Js.setHtml("#sessionsFilePdfDiv", html4);
            script += Js.setHtml("#sessionsFileDiv", html1);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }

    }

    /**
     * عملیات امضای فرد برای صورتجلسه مربوطه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String actionSigners(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, Sessions._id);// ای دی جلسه
            String script = "";
            String[] RoleIdArray = jjTools.getParameter(request, "RoleId").split(",");// ایدی یوزر
            String like = "";
            List<Map<String, Object>> sessionsRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT  *  FROM hmis_sessions WHERE  hmis_sessions.id=" + id + ""));
            if (!jjTools.getParameter(request, "RoleId").equals("")) {
                if (RoleIdArray.length >= 1) {
                    String signerRoleId = "";
                    if (!sessionsRow.get(0).get(Sessions._audience).equals("")) {
                        String[] audienceRoleId = sessionsRow.get(0).get(Sessions._audience).toString().split("%23A%23");

                        for (int j = 0; j < RoleIdArray.length; j++) {
                            for (int k = 0; k < audienceRoleId.length; k++) {
                                if (RoleIdArray[j].equals(audienceRoleId[k])) {
                                    signerRoleId += RoleIdArray[j] + ",";
                                    like += " sessions_signers   LIKE '%" + RoleIdArray[j] + "%' OR";
                                    List<Map<String, Object>> sessionsRow2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT  *  FROM hmis_sessions WHERE ( " + like.substring(0, like.length() - 2) + ")  AND hmis_sessions.id=" + id + ""));
                                    if (sessionsRow2.isEmpty()) {
                                        db.otherStatement("UPDATE " + tableName + " SET " + _signers
                                                + "=concat(ifnull(" + _signers + ",''),'"
                                                + signerRoleId
                                                + "')"
                                                + " WHERE id=" + id + ";");
                                        script += "confirm('کاربر گرامی امضای شما ثبت گردید');";

                                    } else {
                                        script += "confirm('امضای شما قبلا ثبت شده است');";

                                    }
                                }

                            }
                        }

                    }

                }
            }
            if (!sessionsRow.get(0).get(Sessions._audienceInSide).equals("")) {
                List<Map<String, Object>> sessionsRow2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT  *  FROM hmis_sessions WHERE  sessions_audienceInSide LIKE '%" + jjTools.getSeassionUserId(request) + "%'  AND hmis_sessions.id=" + id + ""));
                if (!sessionsRow2.isEmpty()) {
                    List<Map<String, Object>> signersInSideRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT  *  FROM hmis_sessions WHERE  sessions_signersUserInSide LIKE '%" + jjTools.getSeassionUserId(request) + "%'  AND hmis_sessions.id=" + id + ""));
                    if (signersInSideRow.isEmpty()) {
                        db.otherStatement("UPDATE " + tableName + " SET " + _signersUserInSide
                                + "=concat(ifnull(" + _signersUserInSide + ",''),'"
                                + jjTools.getSeassionUserId(request)
                                + ",')"
                                + " WHERE id=" + id + ";");
                        script += "confirm('کاربر گرامی امضای شما ثبت گردید');";

                    } else {
                        script += "confirm('امضای شما قبلا ثبت شده است');";

                    }
                }
            }

//                if (sessionsRow.isEmpty()) {
//                    db.otherStatement("UPDATE " + tableName + " SET " + _signers
//                            + "=concat(ifnull(" + _signers + ",''),'"
//                            + like
//                            + "')"
//                            + " WHERE id=" + id + ";");
//                    script += "confirm('کاربر گرامی امضای شما ثبت گردید');";
//
//                } else {
//                    script += "confirm('امضای شما قبلا ثبت شده است');";
//
//                }
            script += "$('#signerSessions').hide()";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تغییر وضعیت جلسات به ابلاغ شده ونمایش اطلاعات مصوبه در جدول مصوبات به
     * مسئولین اجرا و پیگیری
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String sendToTrackerAndExecutor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);//  ای دی جلسات
            String script = "";
            String result = changeStatus(db, id, status_communicated);
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "ابلاغ انجام شد";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += Js.jjSessions.select(id);
            } else {
                String errorMessageing = "ابلاغ انجام نشد";
                script += Js.modal(errorMessageing, "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ صورتجلسه توسط ابلاغ کننده یا مدیر
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String sendToCommunicator(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_Communicate_CommunicateSessions)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی جلسات
            String script = "";
            String result = changeStatus(db, id, status_communicated);//ابلاغ
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "وضعیت صورتجلسه به ابلاغ شده تغییر یافت لطفا مصوبات را ابلاغ کنید.";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisCommunicatedSessions.m_select(" + id + ");";
            } else {
                String errorMessageing = "ابلاغ انجام نشد";
                script += Js.modal(errorMessageing, "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * رد کردن صورتجلسه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String ignore(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ignore_CommunicateSessions)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی جلسات
            String script = "";
            String result = changeStatus(db, id, status_ignore);//رد
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "صورتجلسه رد شد";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisCommunicatedSessions.m_select(" + id + ")";
            } else {
                String errorMessageing = "رد نشد";
                script += Js.modal(errorMessageing, "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getClassCssForVaziat(String status) {
        if (status.equals(status_communicated)) {
            return "green";
        } else if (status.equals(status_confirmationFinal)) {
            return "green";
        } else if (status.equals(status_created)) {
            return "yellow";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        } else if (status.equals(status_ignore)) {
            return "red";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        } else if (status.equals(Approved.status_done)) {
            return "green";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        } else if (status.equals(Approved.status_inDoing)) {
            return "yellow";
        } else if (status.equals(Approved.status_initialRegistration)) {
            return "yellow";
        } else if (status.equals(Approved.status_unDone)) {
            return "red";
        } else if (status.equals(Approved.status_reject)) {
            return "red";
        } else if (status.equals(Approved.status_offer)) {
            return "status_offer";

//        } else if (status.equals(Steps.status_done)) {
//            return "status_done";
//        } else if (status.equals(Steps.status_inDoing)) {
//            return "status_inDoing";
//        } else if (status.equals(Steps.status_initialRegistration)) {
//            return "status_initialRegistration";
//        } else if (status.equals(Steps.status_unDone)) {
//            return "status_unDone";
        }
        return "";
    }
}
