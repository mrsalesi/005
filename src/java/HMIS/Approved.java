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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shohreh.shiran Date 1397.12.18 Session:صورت جلسه و دعوتنامه
 */
public class Approved {

    public static String tableName = "hmis_approved";
    public static String _id = "id";
    public static String _isActive = "approved_isActive";
    public static String _commettesId = "approved_commettesId";//ای دی کمیته
    public static String _sessionsId = "approved_sessionsId";//ایدی جلسه
    public static String _offererId = "approved_offererId";//ایدی شخص پیشنهاد کننده یوزر
    public static String _title = "approved_title";//عنوان مصوبه
    public static String _startDate = "approved_startDate";//تاریخ شروع
    public static String _endDate = "approved_endDate";//تاریخ پایان
    public static String _trackerId = "approved_trackerId";//مسئول پیگیری نقش 
    public static String _executorRoleId = "approved_executorRoleId";//مسئول اجراسمت نقش
    public static String _executorUserId = "approved_executorUserId";//مسئول اجرا
    public static String _status = "approved_status";//وضعیت
    public static String _statusLog = "approved_statusLog";//روند وضعیت
    public static String _description = "approved_description";//توضیحات دبیر کمیته
    public static String _descriptionExecutor = "approved_descriptionExecutor";//توضیحات مسئولین اجرا
    public static String _descriptionTracker = "approved_descriptionTracker";//توضیحات مسئول پیگیری
    public static String _file = "approved_file";//مستندات از طرف دبیر کمیته
    public static String _filesTracker = "approved_filesTracker";//مستندات مسئول اجرا یا مسئول پیگیری
    public static String _filesExecutor = "approved_filesExecutor";//مستندات مسئول اجرا یا مسئول پیگیری
    public static String _percentExecutor = "approved_percentExecutor";//درصد پیشرفت گام مسئول اجرا یا مسئول پیگیری
    public static String _percentTracker = "approved_percentTracker";//رصد پیشرفت گام مسئول اجرا یا مسئول پیگیری
    public static String _percent = "approved_percent";//رصد پیشرفت گام مسئول اجرا یا مسئول پیگیری

    public static int rul_rejectApproved = 452;//دکمه فرم پیشنهاد 
    public static int rul_dltApprovedInCommunicate = 453;//حذف مصوبه در ابلاغ
    public static int rul_rfsMyApproved = 454;//نمایش مصوبات من
    public static int rul_ins = 455;
    public static int rul_edt = 456;
    public static int rul_dlt = 457;
    public static int rul_rfsAll = 458;//نمایش همه مصوبات
    public static int rul_rfsMyCommettesApproved = 459;//نمایش مصوبات کمیته من
    public static int rul_communicateApprovedAll = 462;// دکمه ابلاغ همه مصوبات  توسط 
    public static int rul_communicatedApproved = 460;//دکمه  ابلاغ مصوبه ها 
    public static int rul_rfsIndicatorCommettes = 468;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static String status_inDoing = "در حال انجام";
    public static String status_unDone = "غیر قابل انجام";
    public static String status_offer = "پیشنهاد";
    public static String status_reject = "رد پیشنهاد";
    public static String status_done = "انجام شده";
    public static String status_initialRegistration = "ثبت اولیه";
    public static String status_communicated = "ابلاغ شده";
//    public static String status_finished = "خاتمه یافته";

    /**
     * نمایش مصوبه های که با مربوط به مسئول پیگیری است یا مربوط به مسئولین اجرا
     * است
     *
     * @param request
     * @param response
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String refreshMyApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
//            boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//            boolean accRef = Access_User.hasAccess(request, db, rul_rfs);
//////////////////////////////////////////////////////////////////
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
            String roles = jjTools.getSeassionUserRole(request);
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
                    condition2 += " approved_trackerId =" + role[i] + " OR";
                }

                dtm = db.otherSelect("SELECT hmis_approved.id,hmis_sessions.sessions_title,hmis_commettes.commettes_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_status,approved_title,approved_endDate,approved_startDate,approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND"
                        + " approved_status != '" + status_initialRegistration + "'"
                        + " AND"
                        + " approved_status != '" + status_offer + "'"
                        + " AND "
                        + "approved_status != '" + status_reject + "'"
                        + " AND "
                        + "approved_status != '" + status_communicated + "'"
                        + " AND (approved_executorUserId=" + jjTools.getSeassionUserId(request) + " OR " + condition1.substring(0, condition1.length() - 2) + " OR " + condition2.substring(0, condition2.length() - 2) + ")");
            } else {

                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title,hmis_commettes.commettes_title,hmis_approved.id,approved_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_status,approved_endDate,approved_startDate,approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + " FROM hmis_approved\n"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' AND approved_status!='" + status_offer + "'"
                        + " "
                        + "AND approved_status!='" + status_reject + "' "
                        + "AND approved_status!='" + status_communicated + "' "
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request));
            }
//////////////////////////////////////////////////////////////////
            html.append("<div class='row col-sm mg-t-40'>");
            html.append("<div class='col-sm-x'>"
                    + "<p class=''> "
                    + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyApproved();'>مصوبات من </a>\n "
                    + "</p>"
                    + "</div> ");
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-1 mg-l-40'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyCommettesApproved();'>مصوبات کمیته های من </a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-3'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.m_refresh();'>همه مصوبات</a> "
                        + " </p> \n"
                        + " </div> ");
            }
            html.append("</div>"
                    + "<div class='card-header'>مصوبات من</div>");
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshMyApproved' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان صورتجلسه</th>");
            html.append("<th width='10%'>عنوان کمیته</th>");
            html.append("<th width='10%'>عنوان مصوبه</th>");
            html.append("<th width='15%'> مسئول اجرا</th>");
            html.append("<th width='20%'>مسئول پیگیری</th>");
            html.append("<th width='15%'>تاریخ شروع </th>");
            html.append("<th width='15%'>تاریخ پایان </th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='15%'>وضعیت پایش</th>");
            html.append("<th width='40%'>پایش</th>");
            html.append("</thead><tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                if (row.get(i).get(_isActive).equals("0")) {

                    html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                } else {
                    html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                }
                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                    }
                }
                if (!row.get(i).get(Approved._executorUserId).equals("")) {
                    if (row.get(i).get(_executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        }
                    }

                }

                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                System.out.println("" + row.get(i).get(_id));
                html.append("<td class='r'>" + row.get(i).get(Sessions._title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Commettes._title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title).toString().replaceAll("%23A%23", "-") + "</td>");
                html.append("<td class='r'>" + ExecutorUserName + ExecutorRoleName + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate)) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                String statusApproved = row.get(i).get(_isActive).equals("1") ? "نهایی" : "غیر نهایی";
                html.append("<td class='r'>" + statusApproved + "</td>");
                html.append("<td class='r'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
//            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMyApproved", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش مصوبه های که با مربوط به مسئول پیگیری است یا مربوط به مسئولین اجرا
     * است
     *
     * @param request
     * @param response
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String refreshIndicatorCommettes(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsIndicatorCommettes)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
//            boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//            boolean accRef = Access_User.hasAccess(request, db, rul_rfs);
//////////////////////////////////////////////////////////////////

            DefaultTableModel dtm;
            DefaultTableModel dtm2;
            DefaultTableModel dtm3;
            DefaultTableModel dtm4;
            DefaultTableModel dtm5;
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();

            dtm = db.otherSelect("SELECT approved_status,Count(approved_status)AS Total,"
                    + " 100*(Count(approved_status)/ (SELECT Count(*) From hmis_approved))AS COUNT "
                    + " FROM hmis_approved "
                    + " WHERE approved_status!='" + status_communicated + "' GROUP BY approved_status "
                    + "");

            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshIndicatorCommettes' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='10%'>وضعیت</th>");

            html.append("<th width='15%'>درصد</th>");
            html.append("<th width='15%'>تعداد</th>");

            html.append("</thead><tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + row.get(i).get(_status) + "</td>");
                html.append("<td class='c'>" + row.get(i).get("COUNT") + "</td>");
                html.append("<td class='c'>" + row.get(i).get("Total") + "</td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName));
            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName));
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshIndicatorCommettes1' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='10%'>نام ونام خانوادگی فرد </th>");
            html.append("<th width='10%'>تعداد حضور </th>");
            html.append("<th width='10%'>تعداد غیبت </th>");
            html.append("<th width='10%'>نام کمیته </th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < roleRow.size(); i++) {

                dtm2 = db.otherSelect("SELECT hmis_commettes.commettes_title,"
                        + "sessions_commettesId,count(sessions_audience)AS Audience"
                        + " FROM hmis_sessions "
                        + " LEFT JOIN hmis_commettes"
                        + " ON sessions_commettesId=hmis_commettes.id"
                        + " WHERE sessions_commettesId AND sessions_audience LIKE  '%" + roleRow.get(i).get(Role._id).toString() + "\\%23A\\%23%'"
                //                        + " group by sessions_commettesId"
                );
                dtm3 = db.otherSelect("SELECT hmis_commettes.commettes_title,"
                        + "sessions_commettesId"
                        + ",count(sessions_absentRole)AS Absent "
                        + " FROM hmis_sessions "
                        + " LEFT JOIN hmis_commettes"
                        + " ON sessions_commettesId=hmis_commettes.id"
                        + " WHERE sessions_commettesId AND sessions_absentRole LIKE '%" + roleRow.get(i).get(Role._id).toString() + "\\%23A\\%23%'"
                //                        + " group by sessions_commettesId"
                );
                List<Map<String, Object>> audienceRow = jjDatabase.separateRow(dtm2);
                List<Map<String, Object>> absentRow = jjDatabase.separateRow(dtm3);
                for (int j = 0; j < audienceRow.size(); j++) {
                    html.append("<tr  class='p'>");
                    html.append("<td class='c'>" + Role.getRoleUserName(roleRow.get(i).get(Role._id).toString(), db) + "</td>");
                    html.append("<td class='c'>" + audienceRow.get(j).get("Audience") + "</td>");
                    for (int k = 0; k < absentRow.size(); k++) {
                        html.append("<td class='c'>" + absentRow.get(k).get("Absent") + "</td>");
                    }
                    html.append("<td class='c'>" + audienceRow.get(j).get(Commettes._title) + "</td>");
                    html.append("</tr>");
                }

            }

            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshIndicatorCommettes", "300", 0, "", "جلسات");
            html2 += Js.table("#refreshIndicatorCommettes1", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * همه مصوبات
     *
     * @param request
     * @param response
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
//            boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//            boolean accRef = Access_User.hasAccess(request, db, rul_rfs);
//////////////////////////////////////////////////////////////////
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();

            dtm = db.otherSelect("SELECT hmis_sessions.sessions_title,hmis_commettes.commettes_title,hmis_approved.id,approved_title,hmis_approved.approved_isActive,sessions_status,\n"
                    + "approved_status,approved_endDate,approved_startDate,approved_executorRoleId,approved_executorUserId,approved_trackerId"
                    + " FROM hmis_approved\n"
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                    + " WHERE  approved_status!='" + status_offer + "'"
                    + "AND approved_status!='" + status_reject + "' "
                    + "AND approved_status!='" + status_communicated + "' "
            );

//////////////////////////////////////////////////////////////////
            html.append("<div class='row col-sm mg-t-40'> ");
            if (Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                html.append("<div class='col-sm-x'>     \n   "
                        + "<p class=''> "
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyApproved();'>مصوبات من </a>\n "
                        + "</p>"
                        + "</div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-1 mg-l-40'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyCommettesApproved();'>مصوبات کمیته های من </a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-3'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.m_refresh();'>همه مصوبات</a> "
                        + " </p> \n"
                        + " </div> ");
            }
            html.append("</div>"
                    + "<div class='card-header'>همه مصوبات</div>"
            );
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshApproved' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان صورتجلسه</th>");
            html.append("<th width='10%'>عنوان کمیته</th>");
            html.append("<th width='10%'>عنوان مصوبه</th>");
            html.append("<th width='15%'> مسئول اجرا</th>");
            html.append("<th width='20%'>مسئول پیگیری</th>");
            html.append("<th width='15%'>تاریخ شروع </th>");
            html.append("<th width='15%'>تاریخ پایان </th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='15%'>وضعیت پایش</th>");
            html.append("<th width='40%'>پایش</th>");
            html.append("</thead><tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                if (row.get(i).get(_isActive).equals("0")) {

                    html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                } else {
                    html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                }
                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                    }
                }
                if (!row.get(i).get(Approved._executorUserId).equals("")) {
                    if (row.get(i).get(_executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        }
                    }

                }

                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                System.out.println("" + row.get(i).get(_id));
                html.append("<td class='r'>" + row.get(i).get(Sessions._title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Commettes._title) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title).toString().replaceAll("%23A%23", "-") + "</td>");
                html.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate)) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                String statusApproved = row.get(i).get(_isActive).equals("1") ? "نهایی" : "غیر نهایی";
                html.append("<td class='r'>" + statusApproved + "</td>");
                html.append("<td class='r'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
//            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshApproved", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * مصوبات مربوط به کمیته های من
     *
     * @param request
     * @param response
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String refreshMyCommettesApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
//            boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//            boolean accRef = Access_User.hasAccess(request, db, rul_rfs);
//////////////////////////////////////////////////////////////////
            html.append("<div class='row col-sm mg-t-40'> ");
            if (Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                html.append("<div class='col-sm-x'>     \n   "
                        + "<p class=''> "
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyApproved();'>مصوبات من </a>\n "
                        + "</p>"
                        + "</div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-1 mg-l-40'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyCommettesApproved();'>مصوبات کمیته های من </a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-3'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.m_refresh();'>همه مصوبات</a> "
                        + " </p> \n"
                        + " </div>");
            }
            html.append(" </div>"
                    + "<div class='card-header'>مصوبات کمیته من</div>");
            String roles = jjTools.getSeassionUserRole(request);
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += Commettes._secretary + "=" + role[i] + " OR ";
                }

                dtm = db.otherSelect("SELECT hmis_commettes.*"
                        + ", hmis_approved.id,hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_status,approved_title,approved_endDate,approved_startDate,approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                        + " WHERE  "
                        + " approved_status != '" + status_offer + "'"
                        + " AND "
                        + "approved_status != '" + status_communicated + "'"
                        + " AND "
                        + condition1.substring(0, condition1.length() - 3));

                html.append("<div class='card-header'>مصوبات کمیته من</div>");
                html.append("<div class='table-wrapper'>\n");
                html.append("<table id='refreshApproved' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
                html.append("<th width='5%'>کد</th>");
                html.append("<th width='10%'>عنوان صورتجلسه</th>");
                html.append("<th width='10%'>عنوان کمیته</th>");
                html.append("<th width='10%'>عنوان مصوبه</th>");
                html.append("<th width='15%'> مسئول اجرا</th>");
                html.append("<th width='20%'>مسئول پیگیری</th>");
                html.append("<th width='15%'>تاریخ شروع </th>");
                html.append("<th width='15%'>تاریخ پایان </th>");
                html.append("<th width='15%'>وضعیت</th>");
                html.append("<th width='15%'>وضعیت پایش</th>");
                html.append("<th width='40%'>پایش</th>");
                html.append("</thead><tbody>");

                List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
                if (row.size() > 0) {
                    for (int i = 0; i < row.size(); i++) {
                        String ExecutorUserName = "";
                        String ExecutorRoleName = "";
                        if (row.get(i).get(_isActive).equals("0")) {

                            html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                        } else {
                            html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                        }
                        if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                            String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                            String[] executorRoleIdArray = executorRoleId.split(",");
                            for (int j = 0; j < executorRoleIdArray.length; j++) {
                                ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                            }
                        }
                        if (!row.get(i).get(Approved._executorUserId).equals("")) {
                            if (row.get(i).get(_executorUserId).equals("ALL")) {
                                ExecutorUserName = "تمام کاربران ثبت شده";
                            } else {
                                String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                                String[] executorUserIdArray = executorUserId.split(",");
                                for (int j = 0; j < executorUserIdArray.length; j++) {
                                    System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                                    ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                                }
                            }

                        }

                        html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                        System.out.println("" + row.get(i).get(_id));
                        html.append("<td class='r'>" + row.get(i).get(Sessions._title) + "</td>");
                        html.append("<td class='r'>" + row.get(i).get(Commettes._title) + "</td>");
                        html.append("<td class='r'>" + row.get(i).get(_title).toString().replaceAll("%23A%23", "-") + "</td>");
                        html.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                        html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db) + "</td>");
                        html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate)) + "</td>");
                        html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "</td>");
                        html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                        String statusApproved = row.get(i).get(_isActive).equals("1") ? "نهایی" : "غیر نهایی";
                        html.append("<td class='r'>" + statusApproved + "</td>");
                        html.append("<td class='r'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                        html.append("</tr>");
                    }
                    html.append("</tbody></table>");
                } else {

                }

            }
//            else {
//                html.append("<div class='row col-sm mg-t-40'> "
//                        + "<div class='col-sm-x'>     \n   "
//                        + "<p class=''> "
//                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyApproved();'>مصوبات من </a>\n "
//                        + "</p>"
//                        + "</div> <div class='col-lg-1 mr-l-50'>\n "
//                        + "<p class=''>"
//                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.refreshMyCommettesApproved();'>مصوبات کمیته های من </a>                                   "
//                        + "</p>  "
//                        + " </div> "
//                        + "<div class='col-lg-3'> \n  "
//                        + " <p class=''>\n"
//                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisApproved.m_refresh();'>همه مصوبات</a> "
//                        + " </p> \n"
//                        + " </div> </div>"
//                        + "<div class='card-header'>مصوبات کمیته من</div>");
//            }
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshApproved", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
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
            System.out.println("_executorId=" + jjTools.getParameter(request, _executorRoleId));
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");
            List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
            StringBuilder html = new StringBuilder();
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_isActive, 0);
            map.put(_commettesId, sessionsRow.get(0).get(Sessions._commetteId));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ",")));
            map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ",")));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            map.put(_status, status_initialRegistration);
            map.put(_sessionsId, sessionsId);
            map.put(_statusLog,
                    status_initialRegistration
                    + ":"
                    + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                    + " user_id="
                    + jjTools.getSeassionUserId(request) + " "
                    + " role_id="
                    + jjTools.getSeassionUserRole(request)
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
            );

            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "$('#approvedTbl').slideDown();$('#insertApproved2').slideUp();";
            script += Js.jjSessions.select(sessionsId);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));//اگر وضعیت جلسه ابلاغ شده است دیگر وصوبه ثبت نشود
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
//                    if (!SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_communicated)) {
                    html.append(Js.setHtml("#ApprovedInSessions_button", "<button  class=\"btn btn-outline-success  btn-block mg-b-10\" id=\"insert_Approved_new\" onclick='" + Js.jjApproved.insert() + "'>" + lbl_insert + "</button>"));
//                    }
                }
            }

            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            String script = "";
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";

            }

            String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";//با داشتن الگوی regex
//            Pattern p1 = Pattern.compile(trackerId);
            Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)");
            String RolesId = jjTools.getSessionAttribute(request, "#ROLE_ID");
            Matcher m1 = p1.matcher(RolesId);   // get a matcher object
/////////////////////////////////نقش مسئول پیگیری/////////////////////////
            if (m1.find() == true) {
                html.append(Js.removeAttr("#approved_descriptionTracker", "disabled"));
                html.append(Js.removeAttr("#approved_percentTracker", "disabled"));
                html.append("$('#trackerFileApprovedDiv').show();");
                html5.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");

                String attachFilesTracker = row.get(0).get(Approved._filesTracker).toString();
                String[] attachFilesTrackerArray = attachFilesTracker.split(",");
                for (int l = 0; l < attachFilesTrackerArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesTrackerArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesTrackerArray[l].substring(attachFilesTrackerArray[l].lastIndexOf(".") + 1, attachFilesTrackerArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html5.append("<div class='col-lg-12  mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html5.append("<div class='col-lg-12   mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

            } else {
                html.append(Js.setAttr("#approved_descriptionTracker", "disabled", "disabled"));
                html.append(Js.setAttr("#approved_percentTracker", "disabled", "disabled"));
                html.append("$('#trackerFileApprovedDiv').hide();");

                html5.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");

////////////////////////////////////////////////////
                String attachFilesTracker = row.get(0).get(Approved._filesTracker).toString();
                String[] attachFilesTrackerArray = attachFilesTracker.split(",");
                for (int l = 0; l < attachFilesTrackerArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesTrackerArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesTrackerArray[l].substring(attachFilesTrackerArray[l].lastIndexOf(".") + 1, attachFilesTrackerArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html5.append("<div class='col-lg-12  mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                    //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html5.append("<div class='col-lg-12   mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                    //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

            }
            /////////////////////شخص مسئول //////////////////////
            if (executorRoleId.equals(",")) {
                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "")) {
                    html.append(Js.removeAttr("#approved_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#approved_percentExecutor", "disabled"));//
                    html.append("$('#executorFileApprovedDiv').show();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

                    String attachFilesApproved = row.get(0).get(Approved._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesApproved.split(",");
                    for (int l = 0; l < attachFilesExecutorArray.length; l++) {
                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesExecutorArray[l] + "'"));
                        if (!fileRow.isEmpty()) {
                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                            String extension2 = attachFilesExecutorArray[l].substring(attachFilesExecutorArray[l].lastIndexOf(".") + 1, attachFilesExecutorArray[l].length());
                            if (extension2.toLowerCase().equals("jpg")
                                    || extension2.toLowerCase().equals("png")
                                    || extension2.toLowerCase().equals("gif")
                                    || extension2.toLowerCase().equals("svg")) {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }

///////////////////////////////////////////////////////////
                } else {
                    html.append(Js.setAttr("#approved_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#approved_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileApprovedDiv').hide();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

///////////////////////////////////////////////////////
                    String attachFilesApproved = row.get(0).get(Approved._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesApproved.split(",");
                    for (int l = 0; l < attachFilesExecutorArray.length; l++) {
                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesExecutorArray[l] + "'"));
                        if (!fileRow.isEmpty()) {
                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                            String extension2 = attachFilesExecutorArray[l].substring(attachFilesExecutorArray[l].lastIndexOf(".") + 1, attachFilesExecutorArray[l].length());
                            if (extension2.toLowerCase().equals("jpg")
                                    || extension2.toLowerCase().equals("png")
                                    || extension2.toLowerCase().equals("gif")
                                    || extension2.toLowerCase().equals("svg")) {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html4.append("<div class='col-lg-12   mg-l-15'>"
                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }

                }
            } else {

                Pattern p2 = Pattern.compile("(^" + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + ",)");//برای رول ها ومچ کردن با regex
                Matcher m2 = p2.matcher(RolesId);   // get a matcher object
                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "") || m2.find()) {
                    html.append(Js.removeAttr("#approved_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#approved_percentExecutor", "disabled"));//
                    html.append("$('#executorFileApprovedDiv').show();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

/////////////////////////////////////////
                    String attachFilesApproved = row.get(0).get(Approved._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesApproved.split(",");
                    for (int l = 0; l < attachFilesExecutorArray.length; l++) {
                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesExecutorArray[l] + "'"));
                        if (!fileRow.isEmpty()) {
                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                            String extension2 = attachFilesExecutorArray[l].substring(attachFilesExecutorArray[l].lastIndexOf(".") + 1, attachFilesExecutorArray[l].length());
                            if (extension2.toLowerCase().equals("jpg")
                                    || extension2.toLowerCase().equals("png")
                                    || extension2.toLowerCase().equals("gif")
                                    || extension2.toLowerCase().equals("svg")) {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }
                } else {
                    html.append(Js.setAttr("#approved_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#approved_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileApprovedDiv').hide();");
                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

                    String attachFilesApproved = row.get(0).get(Approved._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesApproved.split(",");
                    for (int l = 0; l < attachFilesExecutorArray.length; l++) {
                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesExecutorArray[l] + "'"));
                        if (!fileRow.isEmpty()) {
                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                            String extension2 = attachFilesExecutorArray[l].substring(attachFilesExecutorArray[l].lastIndexOf(".") + 1, attachFilesExecutorArray[l].length());
                            if (extension2.toLowerCase().equals("jpg")
                                    || extension2.toLowerCase().equals("png")
                                    || extension2.toLowerCase().equals("gif")
                                    || extension2.toLowerCase().equals("svg")) {
                                html4.append("<div class='col-lg-12  mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "</div>"
                                );
                            } else {
                                html4.append("<div class='col-lg-12   mg-l-15'>"
                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }

                }
            }
            ///////////////////////////////////////////////////////////////////////////
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#myApproved_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _filesTracker, row.get(0).get(_filesTracker)));
            html.append(Js.setVal("#" + _filesExecutor, row.get(0).get(_filesExecutor)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setHtml("#approved_descriptionApproved", row.get(0).get(_description)));
            html.append(Js.setHtml("#approved_percentSecretary", row.get(0).get(_percent)));
            html.append(Js.setVal("#" + _percentExecutor, row.get(0).get(_percentExecutor)));
            html.append(Js.setVal("#" + _descriptionExecutor, row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionTracker, row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#" + _percentTracker, row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#trackerId", Role.getRoleUserName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Role.getRoleUserName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setHtml("#endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setHtml("#startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            html3.append("فایل های مصوبه ");

///////////////////////////////////////////////////////
            String attachFilesApproved = row.get(0).get(Approved._file).toString();
            String[] attachFilesApprovedArray = attachFilesApproved.split(",");
            for (int l = 0; l < attachFilesApprovedArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_Approved' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjApproved.edit() + "' >ثبت پایش</button>");
                html2.append("</div>");
            }

            html2.append("</div>");
            script += Js.setHtml("#Approved_button", html2);
            script += "$('#showFilesExecutorDiv').html('');";
            script += "$('#showFilesTrackerDiv').html('');";
            script += html.toString();
            script += Js.setHtml("#downloadFileApprovedDiv", html3.toString());
            script += Js.setHtml("#downloadFileTrackerDiv", html5);
            script += Js.setHtml("#downloadFileExecutorDiv", html4);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * انتخاب مصوبات و سلکت آن ها در جلسات
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectInSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String script = "";
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "approved_id");
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.get(0).get(_status).equals(status_reject)) {
                script += "$('#insertApproved2').hide();";
            } else {
                script += "$('#insertApproved2').slideDown();";
                script += "$('#approvedTbl').slideUp();";
                if (row.size() == 0) {
                    String errorMessage = "رکوردی با این کد وجود ندارد.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Select Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
                if (row.get(0).get(_executorUserId).equals("")) {

                    String executorRoleId = (row.get(0).get(_executorRoleId).toString());
                    System.out.println("executorRoleId=" + executorRoleId);
                    String[] exeRoleId = executorRoleId.split(",");
                    String temp = "";
                    System.out.println("exeRoleId" + exeRoleId.length);
                    for (int i = 0; i < exeRoleId.length; i++) {
                        temp += "'" + exeRoleId[i] + "',";
                    }
                    System.out.println("temp=" + temp + "executorRoleId=" + executorRoleId);
                    html.append("$('#approved_executorRoleId').val([" + temp + "]);"
                            + "$('#approved_executorRoleId').select2({  width: '100%'});"
                            + "$('#approved_executorUserId').val('');"
                            + "hmisSessions.executorAction('سمت');"
                            + "$('input:radio[id=roleExecutor]').attr('checked','checked');"
                    );
                } else if (row.get(0).get(_executorRoleId).equals("")) {
                    String executorUserId = (row.get(0).get(_executorUserId).toString());
                    System.out.println("executorUserId=" + executorUserId);
                    String[] exeUserId = executorUserId.split(",");
                    String temp2 = "";
                    System.out.println("exeUserId" + exeUserId.length);
                    for (int i = 0; i < exeUserId.length; i++) {
                        temp2 += "'" + exeUserId[i] + "',";
                    }
                    html.append("$('#approved_executorUserId').val([" + temp2 + "]);"
                            + "$('#approved_executorUserId').select2({ width: '100%'});"
                            + "$('#approved_executorRoleId').val('');"
                            + "hmisSessions.executorAction('کاربران');"
                            + "$('input:radio[id=userExecutor]').attr('checked','checked');"
                    );

                }
                html.append(Js.setVal("#approved_id", row.get(0).get(_id)));
                html.append(Js.setVal("#" + _title, row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
                html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
                html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
                System.out.println("********************" + row.get(0).get(_statusLog));
                html.append(Js.setValSummerNote("#" + _description, row.get(0).get(_description)));

                html.append(Js.setValSelectOption("#" + _trackerId, row.get(0).get(_trackerId).toString()));
                html.append(Js.select2("#" + _trackerId, " width: '100%'"));

                html.append(Js.setVal("#" + _endDate, jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
                html.append(Js.setVal("#" + _startDate, jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));

////////////////////////////////////////////////////////
                String attachFilesApproved = row.get(0).get(_file).toString();
                String[] attachFilesApprovedArray = attachFilesApproved.split(",");
                for (int l = 0; l < attachFilesApprovedArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }
/////////////////////////////////////////////////////
                boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
                boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
                html2.append("<div class='row'>");
                List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
                if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
                    if (accEdt) {
                        html2.append("<div class=\"col-lg-6\">");
                        html2.append("<button  id='edit_ApprovedInSessions' class='btn btn-outline-warning btn-block mg-b-10' onclick='hmisApproved.editInSessions();' >" + lbl_edit + "</button>");
                        html2.append("</div>");
                    }
                    if (accDel) {
                        html2.append("<div class=\"col-lg-6\">");
                        html2.append("<button id='delete_ApprovedInSessions'  class='btn btn-outline-danger btn-block mg-b-10' onclick='hmisApproved.m_delete(" + id + ");'>" + lbl_delete + "</button>");
                        html2.append("</div>");
                    }
                } else {
                }
                html2.append("</div>");
                script += Js.setHtml("#ApprovedInSessions_button", html2);
                script += html.toString();
                script += Js.setHtml("#fileApprovedDiv", html3);
            }
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ صورتجلسه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectInCommunicatedSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String script = "";
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "communicatedApproved_id");
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            script += "$('#insertApprovedCommunicated').slideDown();";
            script += "$('#communicatedApprovedTbl').slideUp();";
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }

            if (row.get(0).get(_executorUserId).equals("")) {

                String executorRoleId = (row.get(0).get(_executorRoleId).toString());
                System.out.println("executorRoleId=" + executorRoleId);
                String[] exeRoleId = executorRoleId.split(",");
                String temp = "";
                System.out.println("exeRoleId" + exeRoleId.length);
                for (int i = 0; i < exeRoleId.length; i++) {
                    temp += "'" + exeRoleId[i] + "',";
                }
                html.append("$('#communicatedApproved_executorRoleId').val([" + temp + "]);"
                        + "$('#communicatedApproved_executorRoleId').select2({  width: '100%'});"
                        + "$('#communicatedApproved_executorUserId').val('');"
                        + "hmisCommunicatedSessions.executorAction('سمت');"
                        + "$('input:radio[id=roleExecutor]').attr('checked','checked');"
                );

            } else if (row.get(0).get(_executorRoleId).equals("")) {
                String executorUserId = (row.get(0).get(_executorUserId).toString());
                System.out.println("executorUserId=" + executorUserId);
                String[] exeUserId = executorUserId.split(",");
                String temp2 = "";
                System.out.println("exeUserId" + exeUserId.length);
                for (int i = 0; i < exeUserId.length; i++) {
                    temp2 += "'" + exeUserId[i] + "',";
                }

                html.append("$('#communicatedApproved_executorUserId').val([" + temp2 + "]);"
                        + "$('#communicatedApproved_executorUserId').select2({ width: '100%'});"
                        + "$('#communicatedApproved_executorRoleId').val('');"
                        + "hmisCommunicatedSessions.executorAction('کاربران');"
                        + "$('input:radio[id=userExecutor]').attr('checked','checked');"
                );

            }
            html.append(Js.setVal("#communicatedApproved_id", row.get(0).get(_id)));
            html.append(Js.setVal("#communicatedApproved_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#communicatedApproved_file", row.get(0).get(_file)));
            html.append(Js.setVal("#communicatedApproved_status", row.get(0).get(_status)));
            html.append(Js.setValSummerNote("#communicatedApproved_description", row.get(0).get(_description)));
            html.append(Js.setValSelectOption("#communicatedApproved_trackerId", row.get(0).get(_trackerId).toString()));
            html.append("$('#communicatedApproved_trackerId').select2({ width: '100%'});");
            html.append(Js.setVal("#communicatedApproved_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#communicatedApproved_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));

///////////////////////////////////////////
            String attachFilesApproved = row.get(0).get(_file).toString();
            String[] attachFilesApprovedArray = attachFilesApproved.split(",");
            for (int l = 0; l < attachFilesApprovedArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            ////////////////////////////////
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
            if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created) || SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_confirmationFinal)) {
                if (accEdt) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button  id='edit_communicatedApprovedInSessions' class='btn btn-outline-warning btn-block mg-b-10' onclick='hmisApproved.editApprovedInCommunicate();' >" + lbl_edit + "</button>");
                    html2.append("</div>");
                }
                if (accDel) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button id='delete_communicatedApprovedInSessions'  class='btn btn-outline-danger btn-block mg-b-10' onclick='hmisApproved.deleteApprovedInCommunicate(" + id + ");'>" + lbl_delete + "</button>");
                    html2.append("</div>");
                }
            } else {
            }

            html2.append("</div>");
            script += Js.setHtml("#communicatedApprovedInSessions_button", html2);
            script += html.toString();
            script += Js.setHtml("#inputDownloadCommunicatedApprovedDiv", html3);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * انتخاب مصوبات در قسمت جلسات برای بررسی مصوبات قبلی این کمیته
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectApprovedPrevious(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }

            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();

            html.append(Js.setVal("#approvedPrevious_id", row.get(0).get(_id)));
            html.append(Js.setVal("#approvedPrevious_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#approved_file", row.get(0).get(_file)));
            html.append(Js.setVal("#" + _percent, row.get(0).get(_percent)));
            html.append(Js.setHtml("#approvedPrevious_percentExecutor", row.get(0).get(_percentExecutor)));
            html.append(Js.setHtml("#approvedPrevious_percentTracker", row.get(0).get(_percentTracker)));
            html.append(Js.setVal("#approved_isActive", row.get(0).get(_isActive)));
            html.append(Js.setVal("#approvedPrevious_status", row.get(0).get(_status)));
            html.append(Js.setValSummerNote("#approvedPrevious_description", row.get(0).get(_description)));
            html.append(Js.setVal("#approvedPrevious_descriptionExecutor", row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#approvedPrevious_descriptionTracker", row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#approvedPrevious_trackerId", Role.getRoleUserName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) {
                html.append(Js.setVal("#approvedPrevious_executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null")) {
                html.append(Js.setVal("#approvedPrevious_executorRoleId", Role.getRoleUserName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#approvedPrevious_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#approvedPrevious_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            html.append(Js.setHtml("#approvedPrevious_statusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            //////////////////////////////////////
            html4.append("<div class='row col-lg-12'>"
                    + "فایل های مصوبه"
                    + "</div>");
            String attachFilesApproved = row.get(0).get(_file).toString();
            String[] attachFilesApprovedArray = attachFilesApproved.split(",");
            for (int l = 0; l < attachFilesApprovedArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            ////////////////////////////////////////
            html4.append("<div class='row col-lg-12'>"
                    + "فایل های مسئولین اجرا"
                    + "</div>");
            String attachFilesExecutor = row.get(0).get(_filesExecutor).toString();
            String[] attachFilesExecutorArray = attachFilesExecutor.split(",");
            for (int l = 0; l < attachFilesExecutorArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesExecutorArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesExecutorArray[l].substring(attachFilesExecutorArray[l].lastIndexOf(".") + 1, attachFilesExecutorArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            ////////////////////////////////////////
            html4.append("<div class='row col-lg-12'>"
                    + "فایل های مسئول پیگیری"
                    + "</div>");
            String attachFilesTracker = row.get(0).get(_filesTracker).toString();
            String[] attachFilesTrackerArray = attachFilesTracker.split(",");
            for (int l = 0; l < attachFilesTrackerArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesTrackerArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesTrackerArray[l].substring(attachFilesTrackerArray[l].lastIndexOf(".") + 1, attachFilesTrackerArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12 mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesTracker + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//

            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_ApprovedPrevious' class='btn btn-outline-warning btn-block mg-b-10' onclick='hmisApproved.editApprovedPrevious();' >" + lbl_edit + "</button>");
                html2.append("</div>");
            }

            String script = "";
            script += Js.setHtml("#ApprovedPrevious_button", html2);//دکمه های مربوط به مصوبات قبلی
            script += Js.setHtml("#ApprovedPrevious_FileInsessions", html4);//فایل های مسئولین
            script += html.toString();
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String changeStatus(HttpServletRequest request, jjDatabaseWeb db, String id, String newSatus) {
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
                        + "   user_id="
                        + jjTools.getSeassionUserId(request) + "   roleId="
                        + jjTools.getSessionAttribute(request, "#ROLE_ID")
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
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
            map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
            map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
            map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
            map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));

            String result = changeStatus(request, db, id, jjTools.getParameter(request, _status));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }

                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
//                if (result.isEmpty()) {
//                    script += "hmisApproved.refreshMyApproved();";
//                script += "hmisApproved.m_select(" + id + ");";
//                script += Js.modal("تغییرات انجام شد", "پیام سامانه");
//                }
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش اطلاعات مصوبه در جلسات
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editInSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, Sessions.rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "approved_id");
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            System.out.println("/////////////////////////////////////");
            jjTools.ShowAllParameter(request);
            System.out.println("/////////////////////////////////////");
            System.out.println("jjTools.getParameter(request, _description)=" + jjTools.getParameter(request, _description));
            Map<String, Object> map = new HashMap<>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ",")));
                map.put(_executorUserId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("null")) {

                map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ",")));
                map.put(_executorRoleId, "");
            }

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#insertApproved2').slideUp();";
            script += "$('#approvedTbl').slideDown();";
            script += "$('#showFilesApprovedDiv').html('');";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش اطلاعات مصوبه در ابلاغ صورت جلسه
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editApprovedInCommunicate(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "communicatedApproved_id");
            String sessionsId = jjTools.getParameter(request, "communicatedSessions_id");
            System.out.println("session_id=" + sessionsId);
            System.out.println("/////////////////////////////////////");
            jjTools.ShowAllParameter(request);
            System.out.println("/////////////////////////////////////");
            Map<String, Object> map = new HashMap<>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId)));
                map.put(_executorUserId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("null")) {

                map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId)));
                map.put(_executorRoleId, "");
            }

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "hmisCommunicatedSessions.m_select(" + sessionsId + ");";
            script += "$('#insertApprovedCommunicated').slideUp();";
            script += "$('#communicatedApprovedTbl').slideDown();";
            script += "$('#showFilesApprovedDiv').html('');";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش مصوبات مربوط به جلسات قبلی
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editApprovedPrevious(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, Sessions.rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "approvedId");
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            List<Map<String, Object>> approvedRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_percent, jjTools.getParameter(request, _percent));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            System.out.println("sessionsId=" + sessionsId);
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#approvedPreviousDiv').slideUp();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ای دی مصوبات
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);

            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#insertApproved2').slideUp();";
            script += "$('#approvedTbl').slideDown();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String deleteApprovedInCommunicate(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dltApprovedInCommunicate)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String sessionsId = jjTools.getParameter(request, "communicatedSessions_id");

            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);

            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "hmisCommunicatedSessions.m_select(" + sessionsId + ");";
            script += "$('#insertApprovedCommunicated').slideUp();";
            script += "$('#communicatedApprovedTbl').slideDown();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * به تعداد مسئولین رکورد ساخته می شود اگر مسئول یکی بود رکوردی ایجاد نمی
     * شود وفقط وضعیت مصوبه به ابلاغ شده تغییر می کند
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String communicatedApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatedApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String text = "";
            StringBuilder html = new StringBuilder();
            String ExeRId = "";

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> sessionRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._title, Sessions._id + "=" + row.get(0).get(_sessionsId)));
            String ExecutorRoleId = row.get(0).get(_executorRoleId).toString();
            String ExecutorUserId = row.get(0).get(_executorUserId).toString();
            Map<String, Object> map = new HashMap();
            boolean flag = true;
            if (!ExecutorRoleId.equals("")) {

                String[] ExeRoleId = ExecutorRoleId.split(",");
                for (int i = 0; i < ExeRoleId.length; i++) {
                    if (jjNumber.isDigit(ExeRoleId[i])) {
                        map.put(_title, row.get(0).get(_title));
                        map.put(_endDate, row.get(0).get(_endDate).toString().replaceAll("/", ""));
                        map.put(_startDate, row.get(0).get(_startDate).toString().replaceAll("/", ""));
                        map.put(_file, row.get(0).get(_file));
                        map.put(_description, row.get(0).get(_description));
                        map.put(_executorRoleId, ExeRoleId[i]);
                        map.put(_trackerId, row.get(0).get(_trackerId));
                        map.put(_sessionsId, row.get(0).get(_sessionsId));
                        map.put(_commettesId, row.get(0).get(_commettesId));
                        map.put(_status, status_inDoing);
                        map.put(_statusLog,
                                status_initialRegistration
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + "%23A%23" + Sessions.status_communicated
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + status_inDoing
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + "user_id="
                                + jjTools.getSeassionUserId(request) + " "
                                + "roleId="
                                + jjTools.getSessionAttribute(request, "#ROLE_ID")
                                + "%23A%23"
                        );
                        if (db.insert(tableName, map).getRowCount() == 0) {
                            flag = false;
                        } else {
                            String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[i], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                            } else {
                                text = "شما به عنوان مسئول اجرا انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[i], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        text = tice_configMessage;
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                    } else {
                        text = "شما به عنوان مسئول پیگیری  انتخاب شده اید";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                }
            } else if (!ExecutorUserId.equals("")) {
                String[] ExeUserId = ExecutorUserId.split(",");
                for (int i = 0; i < ExeUserId.length; i++) {
                    if (jjNumber.isDigit(ExeUserId[i])) {
                        map.put(_title, row.get(0).get(_title));
                        map.put(_endDate, row.get(0).get(_endDate).toString().replaceAll("/", ""));
                        map.put(_startDate, row.get(0).get(_startDate).toString().replaceAll("/", ""));
                        map.put(_file, row.get(0).get(_file));
                        map.put(_description, row.get(0).get(_description));
                        map.put(_executorUserId, ExeUserId[i]);
                        map.put(_trackerId, row.get(0).get(_trackerId));
                        map.put(_sessionsId, row.get(0).get(_sessionsId));
                        map.put(_commettesId, row.get(0).get(_commettesId));
                        map.put(_status, status_inDoing);
                        map.put(_statusLog,
                                status_initialRegistration
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + "%23A%23" + Sessions.status_communicated
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + "%23A%23"
                                + status_inDoing
                                + "-"
                                + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                + " "
                                + new jjCalendar_IR().getTimeFormat_8length()
                                + "user_id="
                                + jjTools.getSeassionUserId(request) + " "
                                + "  roleId="
                                + jjTools.getSessionAttribute(request, "#ROLE_ID")
                                + "%23A%23"
                        );
                        if (db.insert(tableName, map).getRowCount() == 0) {
                            flag = false;
                        } else {
                            String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, ExeUserId[i], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                            } else {
                                text = "شما به عنوان مسئول اجرا  انتخاب شده اید";
                                Messenger.sendMesseage(null, db, ExeUserId[i], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        text = tice_configMessage;
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                    } else {
                        text = "شما به عنوان مسئول پیگیری  انتخاب شده اید";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                }
            }
            System.out.println("id=" + id);

            String script = "hmisCommunicatedSessions.m_refresh();";
            script += "hmisCommunicatedSessions.m_select(" + row.get(0).get(_sessionsId) + ");";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ همه مصوبات یک جلسه
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String communicateApprovedAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicateApprovedAll)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String script = "";
            String ExeRId = "";
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");
            List<Map<String, Object>> sessionRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
            List< Map< String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _sessionsId + "=" + sessionsId + " AND approved_status='" + status_initialRegistration + "'"));
            String text = "";

            StringBuilder html = new StringBuilder();
            if (row.size() > 0) {

                for (int i = 0; i < row.size(); i++) {

                    String id = row.get(i).get(_id).toString();
                    String ExecutorRoleId = row.get(i).get(_executorRoleId).toString();
                    String ExecutorUserId = row.get(i).get(_executorUserId).toString();

                    if (!ExecutorRoleId.equals("")) {
                        boolean flag = true;
                        String[] ExeRoleId = ExecutorRoleId.split(",");
                        Map<String, Object> map = new HashMap();
                        for (int j = 0; j < ExeRoleId.length; j++) {
                            if (jjNumber.isDigit(ExeRoleId[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_file, row.get(0).get(_file));
                                map.put(_description, row.get(i).get(_description));
                                map.put(_executorRoleId, ExeRoleId[j]);
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_sessionsId, row.get(i).get(_sessionsId));
                                map.put(_commettesId, row.get(i).get(_commettesId));
                                map.put(_status, status_inDoing);
                                map.put(_statusLog,
                                        status_initialRegistration
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + "%23A%23" + Sessions.status_communicated
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + status_inDoing
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + "user_id="
                                        + jjTools.getSeassionUserId(request) + " "
                                        + "roleId="
                                        + jjTools.getSessionAttribute(request, "#ROLE_ID")
                                        + "%23A%23"
                                );
                                if (db.insert(tableName, map).getRowCount() == 0) {
                                    flag = false;
                                } else {
                                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                        text = tice_configMessage;
                                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[j], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                                    } else {
                                        text = Role.getUeserIdByUserRole(ExeRoleId[j], db) + "شما به عنوان مسئول اجرا انتخاب شده اید";
                                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[j], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                            String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + "", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                            } else {
                                text = Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db) + "شما به عنوان مسئول پیگیری انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + "", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                            }

                        }
                    } else if (!ExecutorUserId.equals("")) {
                        boolean flag = true;
                        Map<String, Object> map = new HashMap();
                        String[] ExeUserId = ExecutorUserId.split(",");
                        for (int j = 0; j < ExeUserId.length; j++) {
                            String reciverId = "";
                            if (jjNumber.isDigit(ExeUserId[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_file, row.get(i).get(_file));
                                map.put(_description, row.get(i).get(_description));
                                map.put(_executorUserId, ExeUserId[j]);
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_sessionsId, row.get(i).get(_sessionsId));
                                map.put(_commettesId, row.get(i).get(_commettesId));
                                map.put(_status, status_inDoing);
                                map.put(_statusLog,
                                        status_initialRegistration
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + "%23A%23" + Sessions.status_communicated
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + "%23A%23"
                                        + status_inDoing
                                        + "-"
                                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                                        + " "
                                        + new jjCalendar_IR().getTimeFormat_8length()
                                        + "user_id="
                                        + jjTools.getSeassionUserId(request) + " "
                                        + "  roleId="
                                        + jjTools.getSessionAttribute(request, "#ROLE_ID")
                                        + "%23A%23"
                                );
                                if (db.insert(tableName, map).getRowCount() == 0) {
                                    flag = false;
                                } else {
                                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                        text = tice_configMessage;
                                        Messenger.sendMesseage(null, db, ExeUserId[j], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                                    } else {
                                        text = Access_User.getUserName(ExeUserId[j], db) + "شما به عنوان مسئول اجرا انتخاب شده اید";
                                        Messenger.sendMesseage(null, db, ExeUserId[j], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                            String tice_configMessage = Tice_config.getValue(db, Tice_config._config_communicateofSessionApprovals_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));

                            } else {
                                text = Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db) + "شما به عنوان مسئول پیگیری انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
//
//
                    }
                }
                script += "hmisCommunicatedSessions.m_refresh();";
                script += "hmisCommunicatedSessions.m_select(" + row.get(0).get(_sessionsId) + ");";
            } else {
                Server.outPrinter(request, response, Js.modal("دیگر امکان ابلاغ وجود ندارد", "پیام سامانه"));
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * زمان اضافه کردن یک پیشنهاد به مصوبات مان این تایع استفاده می کنیم
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String addApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//              if (!Access_User.hasAccess(request, db, rul_communicateApprovedAll)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            String id = jjTools.getParameter(request, "hmis_approved_id");
            String sessionsId = jjTools.getParameter(request, "sessionsId");
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
            List<Map<String, Object>> approvedRow = jjDatabase.separateRow(db.Select(Approved.tableName, Approved._id + "=" + id));

            String script = "";
            if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
                Map<String, Object> map = new HashMap<>();
                changeStatus(request, db, id, status_initialRegistration);
                map.put(_sessionsId, sessionsId);

                if (!db.update(tableName, map, _id + "=" + id)) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                } else {
                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_acceptOffer_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        String text = tice_configMessage;
                        String html = "";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(approvedRow.get(0).get(_offererId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + SessionsRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + approvedRow.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    script += "hmisSessions.m_select(" + sessionsId + ");";
                }
            } else {
                script += Js.modal("این پیشنهاد به دلیل تایید شدن جلسه قابل ثبت یا رد نمی باشد ", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
//

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    /**
     * رد پیشنهاد عملیاتی
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String rejectApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_rejectApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "hmis_approved_id");
            String sessionsId = jjTools.getParameter(request, "sessionsId");
            String script = "";
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
            List<Map<String, Object>> approvedRow = jjDatabase.separateRow(db.Select(Approved.tableName, Approved._id + "=" + id));

            Map<String, Object> map = new HashMap<>();
            if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {

                changeStatus(request, db, id, status_reject);
                map.put(_status, status_reject);
                map.put(_sessionsId, sessionsId);

                if (!db.update(tableName, map, _id + "=" + id)) {
                    String errorMessage = "عملیات  به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                } else {
                    String tice_configMessage = Tice_config.getValue(db, Tice_config._config_rejectOffer_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        String text = tice_configMessage;
                        String html = "";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(approvedRow.get(0).get(_offererId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + SessionsRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + approvedRow.get(0).get(_title), text, html.toString(), "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    script += "hmisSessions.m_select(" + sessionsId + ");";
                }
            } else {
                script += Js.modal("این پیشنهاد به دلیل تایید شدن جلسه قابل ثبت یا رد نمی باشد ", "پیام سامانه");

            }
            Server.outPrinter(request, response, script);
            return "";
//

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

}
