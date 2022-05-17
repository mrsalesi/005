/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import static HMIS.Sessions._commetteId;
import static HMIS.Sessions.getClassCssForVaziat;
import cms.access.Access_Group;
import cms.access.Access_User;
import cms.cms.Tice_config;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.ArrayList;
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
    public static String _indicatorDescription_id = "approved_indicatorDescription_id";//ای دی تحلیل
    public static String _sessionsId = "approved_sessionsId";//ایدی جلسه
    public static String _offererId = "approved_offererId";//ایدی شخص پیشنهاد کننده یوزر
    public static String _title = "approved_title";//عنوان مصوبه
    public static String _startDate = "approved_startDate";//تاریخ شروع
    public static String _endDate = "approved_endDate";//تاریخ پایان
    public static String _trackerId = "approved_trackerId";//مسئول پیگیری نقش 
    public static String _executorRoleId = "approved_executorRoleId";//مسئول اجراسمت نقش
    public static String _executorUserId = "approved_executorUserId";//مسئول اجرا
    public static String _executorGroupsId = "approved_executorGroupsId";//مسئول اجرا گروه کاربری
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
    public static String _executor_userName = "approved_executor_userName";//مسئول اجزا 
    public static String _tracker_userName = "approved_tracker_userName";// مسئول پیگیری

    public static int rul_rejectApproved = 452;//دکمه فرم پیشنهاد 
    public static int rul_dltApprovedInCommunicate = 453;//حذف مصوبه در ابلاغ
    public static int rul_rfsMyApproved = 454;//نمایش مصوبات من
    public static int rul_ins = 455;
    public static int rul_edt = 456;
    public static int rul_dlt = 457;
    public static int rul_rfsAll = 458;//نمایش همه مصوبات
    public static int rul_rfsMyCommettesApproved = 459;//نمایش مصوبات کمیته من
    public static int rul_communicateApprovedAll = 465;// دکمه ابلاغ همه مصوبات  توسط 
    public static int rul_communicatedApproved = 460;//دکمه  ابلاغ مصوبه ها 
    public static int rul_rfsIndicatorCommettes = 468;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static String status_inDoing = "در دست اقدام";
//    public static String status_onGoing = "در دست اقدام";
    public static String status_unDone = "غیر قابل انجام";
    public static String status_offer = "پیشنهاد";
    public static String status_reject = "رد پیشنهاد";
    public static String status_done = "انجام شده";
    public static String status_incomplete = "انجام نشده";
    public static String status_initialRegistration = "ثبت اولیه";
    public static String status_communicated = "ابلاغ شد";
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
                dtm = db.otherSelect("SELECT DISTINCT(hmis_approved.approved_commettesId),"
                        + "hmis_commettes.commettes_title" + "," + Approved._commettesId
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_approved.approved_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND"
                        + " approved_status != '" + status_initialRegistration + "'"
                        + " AND"
                        + " approved_status != '" + status_offer + "'"
                        + " AND "
                        + "approved_status != '" + status_reject + "' "
                        //                        + " AND "
                        //                        + "approved_status != '" + status_communicated + "'"
                        + " AND (approved_executorUserId=" + jjTools.getSeassionUserId(request) + " "
                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
                        + " OR " + condition2.substring(0, condition2.length() - 2) + ")"
                        + " GROUP BY approved_commettesId "
                        + "");
            } else {
                dtm = db.otherSelect("SELECT DISTINCT(hmis_approved.approved_commettesId)"
                        + ",hmis_commettes.commettes_title" + "," + Approved._commettesId
                        + " FROM hmis_approved\n "
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_approved.approved_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' AND approved_status!='" + status_offer + "' "
                        + " "
                        + " AND approved_status!='" + status_reject + "' "
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        + " group by approved_commettesId "
                );
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<div class='row pd-sm-30 mg-t-40'>");
            if (Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                html.append("<div class='col-lg-4'>"
                        + "<p class=''> "
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.refreshMyApproved();'>پایش مصوبات من </a>\n "
                        + "</p>"
                        + "</div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-4'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-15 mg-sm-r-4 tx-white col-lg' onclick='hmisApproved.refreshMyCommettesApproved();'>پایش نهایی مصوبات توسط دبیرکمیته</a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-4'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.m_refresh();'>پایش همه مصوبات</a> "
                        + " </p> \n"
                        + " </div> ");
            }

            html.append("</div>");
            html.append("<div class=' card-header'>مصوبات من</div>");
            html.append("<div class='card bd-primary'>"
                    + "<div class='card-header bg-info tx-white p'>جستجو براساس نام کمیته</div>"
                    + "<div id='myApprovedSearchForm' class='row col-lg-12'>"
                    + "<div class='col-lg pd-sm-30'>نام کمیته"
                    + ":<select class='form-control' name='approved_commettesId' id='commettesIdInMyApproved'>");
            html.append("<option value=''>همه</option>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<option value='" + row.get(i).get(Approved._commettesId) + "'>" + row.get(i).get(Commettes._title) + "</option>");
            }
            html.append("</select>");
            html.append("</div>");
            html.append("<div class='col-lg  pd-sm-30'>نوع مصوبه"
                    + ":<select class='form-control' name='approved_status' id='statusInMyApproved' >");
            html.append("<option value=''>همه</option>");
            html.append("<option value='در دست اقدام'>در دست اقدام</option>");
            html.append("<option value='غیر قابل انجام'>غیر قابل انجام</option>");
            html.append("<option value='انجام شده'>انجام شده</option>");
            html.append("<option value='انجام نشده'>انجام نشده</option>");
            html.append("</select>");
            html.append("</div>");
            html.append("<div class='col-lg  pd-sm-30'>تاریخ شروع"
                    + "<input id='approved_startDateSearch' name='approved_startDate' class='date form-control' type=\"text\"  style=\"display: block;\">"
                    + "</div>");
            html.append("<div class='col-lg  pd-sm-30'>تاریخ پایان"
                    + "<input id='approved_endDateSearch' name='approved_endDate'  class='date form-control' type=\"text\"  style=\"display: block;\">"
                    + "</div>");
            html.append("<div class='col-lg  pd-sm-30 mg-t-20'>"
                    + "<button title='جستجو' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.refreshMyApprovedWithSearch();'>جستجو</button>"
                    + "</div>");
            html.append("</div>");
            html.append("<div class='row col-lg-12'>"
                    + "<div class='col-lg row' id='refreshMyApproved'></div>"
                    + "</div> "
            );
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += "  $('select').select2({width: '100%'});";
            html2 += "  new jj('.date').jjCalendarWithYearSelector(1397, 1420);";

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
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
            String roles = jjTools.getSeassionUserRole(request);
            dtm = db.otherSelect("SELECT DISTINCT(hmis_approved.approved_commettesId)"
                    + ",hmis_commettes.commettes_title"
                    + "," + Approved._commettesId
                    + " FROM hmis_approved\n "
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON hmis_approved.approved_commettesId=hmis_commettes.id\n"
                    + " WHERE sessions_status='" + Sessions.status_communicated + "' AND approved_status!='" + status_offer + "' "
                    + " "
                    + " AND approved_status!='" + status_reject + "' "
                    + " group by approved_commettesId "
            );
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<div class='row pd-sm-30 mg-t-40'>");
            if (Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                html.append("<div class='col-lg-4'>"
                        + "<p class=''> "
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.refreshMyApproved();'>پایش مصوبات من </a>\n "
                        + "</p>"
                        + "</div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-4'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-15 mg-sm-r-4 tx-white col-lg' onclick='hmisApproved.refreshMyCommettesApproved();'>پایش نهایی مصوبات توسط دبیرکمیته</a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-4'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.m_refresh();'>پایش همه مصوبات</a> "
                        + " </p> \n"
                        + " </div> ");
            }

            html.append("</div>");
            html.append("<div class=' card-header'>همه مصوبات </div>");
            html.append("<div class='card bd-primary'>"
                    + "<div class='card-header bg-info tx-white p'>جستجو براساس نام کمیته</div>"
                    + "<div id='allApprovedSearchForm' class='row col-lg-12'>"
                    + "<div class='col-lg pd-sm-30'>نام کمیته"
                    + ":<select class='form-control' name='approved_commettesId' id='commettesIdInAllApproved'>");
            html.append("<option value=''>همه</option>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<option value='" + row.get(i).get(Approved._commettesId) + "'>" + row.get(i).get(Commettes._title) + "</option>");
            }
            html.append("</select>");
            html.append("</div>");
            html.append("<div class='col-lg  pd-sm-30'>نوع مصوبه"
                    + ":<select class='form-control' name='approved_status' id='statusInAllApproved' >");
            html.append("<option value=''>همه</option>");
            html.append("<option value='در دست اقدام'>در دست اقدام</option>");
            html.append("<option value='غیر قابل انجام'>غیر قابل انجام</option>");
            html.append("<option value='انجام شده'>انجام شده</option>");
            html.append("<option value='انجام نشده'>انجام نشده</option>");
            html.append("</select>");
            html.append("</div>");
            html.append("<div class='col-lg  pd-sm-30'>تاریخ شروع"
                    + "<input id='approved_startDateSearch' name='approved_startDate' class='date form-control' type=\"text\"  style=\"display: block;\">"
                    + "</div>");
            html.append("<div class='col-lg  pd-sm-30'>تاریخ پایان"
                    + "<input id='approved_endDateSearch' name='approved_endDate'  class='date form-control' type=\"text\"  style=\"display: block;\">"
                    + "</div>");
            html.append("<div class='col-lg  pd-sm-30 mg-t-20'>"
                    + "<button title='جستجو' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.refreshAllApprovedWithSearch();'>جستجو</button>"
                    + "</div>");
            html.append("</div>");
            html.append("<div class='row col-lg-12'>"
                    + "<div class='col-lg row' id='refreshAllApproved'></div>"
                    + "</div> "
            );
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += "  $('select').select2({width: '100%'});";
            html2 += "  new jj('.date').jjCalendarWithYearSelector(1397, 1420);";

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش جدول پایش مصوبات براساس سرچ
     *
     * @param request
     * @param response
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
//    public static String refreshMyApprovedWithSearch(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
//        try {
//            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
//            DefaultTableModel dtm;
//            StringBuilder html = new StringBuilder();
//            String roles = jjTools.getSeassionUserRole(request);
//            String commettesId = jjTools.getParameter(request, Approved._commettesId);
//            System.out.println("????????????????????commettesId=" + commettesId);
//            String status = jjTools.getParameter(request, Approved._status);
//            String startDate = jjTools.getParameter(request, Approved._startDate).replaceAll("/", "");
//            String endDate = jjTools.getParameter(request, Approved._endDate).replaceAll("/", "");
//            String where = "";
//            if (!roles.equals("")) {
//                String[] role = roles.split(",");
//                String condition1 = "";
//                String condition2 = "";
//                for (int i = 0; i < role.length; i++) {
//                    System.out.println("role" + role[i]);
//                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
//                    condition2 += " approved_trackerId =" + role[i] + " OR";
//                }
//
//                if (!status.equals("")) {
//                    where += " AND " + Approved._status + "='" + status + "' ";
//                }
//                if (!commettesId.equals("")) {
//                    where += " AND " + Approved._commettesId + "=" + commettesId;
//                }
//
//                if (!startDate.equals("")) {
//                    where += " AND " + Approved._startDate + ">=" + startDate;
//                }
//                if (!endDate.equals("")) {
//                    where += " AND " + Approved._endDate + "<=" + endDate;
//
//                }
//
//                dtm = db.otherSelect("SELECT hmis_approved.id,hmis_sessions.sessions_title,"
//                        + "hmis_commettes.commettes_title ,"
//                        + "hmis_approved.approved_isActive,sessions_status,\n"
//                        + "approved_status,approved_title,approved_endDate,"
//                        + "approved_startDate,approved_executorRoleId,"
//                        + "approved_descriptionExecutor,approved_descriptionTracker,"
//                        + "approved_percentExecutor,approved_percentTracker,"
//                        + "approved_executorUserId,approved_trackerId"
//                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
//                        + " FROM hmis_approved"
//                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
//                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
//                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
//                        + " AND "
//                        + " approved_status != '" + status_initialRegistration + "'"
//                        + " AND "
//                        + " approved_status != '" + status_offer + "'"
//                        + " AND "
//                        + "approved_status != '" + status_reject + "'"
//                        + " AND "
//                        + "approved_status != '" + status_communicated + "' "
//                        + where
//                        + " AND (approved_executorUserId=" + jjTools.getSeassionUserId(request)
//                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
//                        + " OR " + condition2.substring(0, condition2.length() - 2) + ")"
//                        + "");
//            } else {
//                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title"
//                        + ",hmis_commettes.commettes_title,hmis_approved.id,"
//                        + "approved_title,hmis_approved.approved_isActive,sessions_status,\n"
//                        + "approved_descriptionExecutor,approved_descriptionTracker,"
//                        + "approved_percentExecutor,approved_percentTracker,"
//                        + "approved_status,approved_endDate,approved_startDate,"
//                        + "approved_executorRoleId,approved_executorUserId,approved_trackerId"
//                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
//                        + " FROM hmis_approved\n "
//                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
//                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
//                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
//                        + " AND approved_status!='" + status_reject + "' "
//                        + " AND approved_status!='" + status_communicated + "' "
//                        + " AND approved_status!='" + status_offer + "' "
//                        + where
//                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request));
//            }
//            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
//
//            html.append("</div>"
//                    + "<div class='card-header'>مصوبات من</div>");
//            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
//            html.append("<table id='refreshMyApprovedTbl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
//            html.append("<th class='c' width='5%'>کد</th>");
//            html.append("<th class='c' width='35%'>عنوان مصوبه</th>");
//            html.append("<th class='c' width='35%'>عنوان</th>");
//            html.append("<th class='c' width='15%'>توضیحات کلی مصوبه</th>");
//            html.append("<th class='c' width='20%'>مسئول اجرا</th>");
//            html.append("<th class='c' width='20%'>مسئول پیگیری</th>");
//            html.append("<th class='c' width='5%'>پایش</th>");
//            html.append(""
//                    + "            <tr>"
//                    + "                <th></th>"
//                    + "                <th>عنوان </th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "            </tr>\n"
//                    + "        </thead>" + "<tbody>");
//            for (int i = 0; i < row.size(); i++) {
//                String ExecutorUserName = "";
//                String ExecutorRoleName = "";
//                if (row.get(i).get(_isActive).equals("0")) {
//                    html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ");' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
//                } else {
//                    html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
//                }
//                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
//                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
//                    String[] executorRoleIdArray = executorRoleId.split(",");
//                    for (int j = 0; j < executorRoleIdArray.length; j++) {
//                        ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
//                    }
//                }
//                if (!row.get(i).get(Approved._executorUserId).equals("")) {
//                    if (row.get(i).get(_executorUserId).equals("ALL")) {
//                        ExecutorUserName = "تمام کاربران ثبت شده";
//                    } else {
//                        String executorUserId = row.get(i).get(Approved._executorUserId).toString();
//                        String[] executorUserIdArray = executorUserId.split(",");
//                        for (int j = 0; j < executorUserIdArray.length; j++) {
//                            System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
//                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
//                        }
//                    }
//                }
//                String statusApproved = row.get(i).get(_isActive).equals("1") ? "نهایی" : "غیر نهایی";
//                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
//                html.append("<td class='r'>"
//                        + row.get(i).get(_title).toString().replaceAll("%23A%23", "-")
//                        + "</td>");
//                html.append("<td class='r'>"
//                        + "<b>"
//                        + "عنوان صورتجلسه"
//                        + ":</b><br/>" + row.get(i).get(Sessions._title) + "<br/>");
//                html.append("<b>"
//                        + "عنوان کمیته:</b>"
//                        + "<br/>" + row.get(i).get(Commettes._title) + "</td>");
//
//                html.append("<td>"
//                        + "<b>"
//                        + "تاریخ شروع و پایان:</b><br/>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
//                        + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
//                        + "<b>"
//                        + "وضعیت پایش:</b>"
//                        + "<br/>" + statusApproved + "<br/>");
//                html.append("<b>"
//                        + "وضعیت مصوبه</b><br/>" + "  " + row.get(i).get(_status) + "</td>");
//
//                html.append("<td>");
//                if (row.get(i).get(_isActive).equals("1")) {
//                    if (row.get(i).get(_executorRoleId).equals("")) {
//                        html.append("<b>"
//                                + "مسئول اجرا:</b>"
//                                + "<br/>" + ExecutorUserName + "<br/>");
//                    } else {
//                        html.append("<b>"
//                                + "مسئول اجرا"
//                                + "</b>"
//                                + "<br/>" + ExecutorRoleName + " - " + row.get(i).get(_executor_userName) + "<br/>");
//                    }
//                } else {
//                    html.append("<b>"
//                            + "مسئول اجرا:"
//                            + "</b>"
//                            + "<br/>" + ExecutorRoleName + ExecutorUserName + "<br/>");
//                }
//                html.append("<b>"
//                        + "توضیحات مسئول اجرا:</b>"
//                        + "<br/>"
//                        + row.get(i).get(Approved._descriptionExecutor));
//                html.append("<br/>"
//                        + "<b>"
//                        + "درصد پیشرفت"
//                        + ":</b><br/>"
//                        + "<div class=\"progress \">"
//                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentExecutor).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentExecutor).toString() + "%;height:100%;line-height:20px\">\n"
//                        + "" + row.get(i).get(_percentExecutor).toString() + "%\n"
//                        + "</div>\n"
//                        + "</div>"
//                        + "</td>"
//                );
//                html.append("<td>");
//                html.append("<b>"
//                        + "مسئول پیگیری:</b><br/>" + Role.getRoleName(row.get(i).get(_trackerId).toString(), db) + " - " + row.get(i).get(_tracker_userName) + "<br/>");
//                html.append("<b>"
//                        + "توضیحات مسئول پیگیری"
//                        + ":</b><br/>" + row.get(i).get(Approved._descriptionTracker));
//                html.append("<br/>"
//                        + "<b>"
//                        + "درصد پیشرفت:"
//                        + "</b>"
//                        + "<br/>"
//                        + "<div class=\"progress \">"
//                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentTracker).toString() + "%;height:100%;line-height:20px\">\n"
//                        + "" + row.get(i).get(_percentTracker).toString() + "%\n"
//                        + "</div>\n"
//                        + "</div>"
//                        + " </td>");
//                html.append("<td class='c'><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                html.append("</tr>");
//            }
//            html.append("</tbody></table></div></div>");
//            String height = jjTools.getParameter(request, "height");
//            String panel = jjTools.getParameter(request, "panel");
//            if (!jjNumber.isDigit(height)) {
//                height = "auto";
//            }
//            if (panel.equals("")) {
//                panel = "swApprovedTbl";
//            }
//            String html2 = Js.setHtml("#" + panel, html.toString());
////            html2 += Js.table("#refreshMyApprovedTbl", "auto", 0, "", "جلسات");
//            html2 += "  $('#refreshMyApprovedTbl').DataTable( {\n"
//                    + "        initComplete: function () {\n"
//                    + "            this.api().columns([]).every( function () {\n"
//                    + "                var column = this;\n"
//                    + "                var select = $('<select><option value=\"\"></option></select>')\n"
//                    + "                    .appendTo( $(column.header()).empty() )\n"
//                    + "                    .on( 'change', function () {\n"
//                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
//                    + "                            $(this).val()\n"
//                    + "                        );\n"
//                    + " \n"
//                    + "                        column\n"
//                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
//                    + "                            .draw();\n"
//                    + "                    } );\n"
//                    + " \n"
//                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
//                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
//                    + "                } );\n"
//                    + "            } );\n"
//                    + "        },paging:false"
//                    + "    } );"
//                    + "$('select').select2({\n"
//                    + "                    width: '100%'\n"
//                    + "                });";
//            Server.outPrinter(request, response, html2);
//            return "";
//        } catch (Exception ex) {
//            Server.outPrinter(request, response, Server.ErrorHandler(ex));
//            return "";
//        }
//    }
    public static String refreshMyApprovedWithSearch(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
            String roles = jjTools.getSeassionUserRole(request);
            String commettesId = jjTools.getParameter(request, Approved._commettesId);
            System.out.println("????????????????????commettesId=" + commettesId);
            String status = jjTools.getParameter(request, Approved._status);
            String startDate = jjTools.getParameter(request, Approved._startDate).replaceAll("/", "");
            String endDate = jjTools.getParameter(request, Approved._endDate).replaceAll("/", "");
            String where = "";
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
                    condition2 += " approved_trackerId =" + role[i] + " OR";
                }

                if (!status.equals("")) {
                    where += " AND " + Approved._status + "='" + status + "' ";
                }
                if (!commettesId.equals("")) {
                    where += " AND " + Approved._commettesId + "=" + commettesId;
                }

                if (!startDate.equals("")) {
                    where += " AND " + Approved._startDate + ">=" + startDate;
                }
                if (!endDate.equals("")) {
                    where += " AND " + Approved._endDate + "<=" + endDate;

                }

                dtm = db.otherSelect("SELECT "
                        + " DISTINCT approved_title "
                        + ",GROUP_CONCAT( hmis_approved.id) approvedId"
                        + ",GROUP_CONCAT( hmis_approved.approved_status) status"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorRoleId) executorRoleId\n"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorUserId) executorUserId"
                        + ",GROUP_CONCAT( hmis_approved.approved_isActive) activeApproved"
                        + ",approved_trackerId,approved_startDate,approved_endDate,hmis_approved.approved_percentTracker"
                        + ",hmis_approved.id,hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title "
                        + ",hmis_sessions.id"
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND "
                        + " approved_status != '" + status_initialRegistration + "'"
                        + " AND "
                        + " approved_status != '" + status_offer + "'"
                        + " AND "
                        + "approved_status != '" + status_reject + "' "
                        + where
                        + " AND (approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
                        + " OR " + condition2.substring(0, condition2.length() - 2) + ")"
                        + " group by hmis_approved.approved_title"
                        + " ORDER BY hmis_approved.id DESC"
                        + "");
            } else {
                dtm = db.otherSelect("SELECT "
                        + " DISTINCT approved_title "
                        + ",GROUP_CONCAT( hmis_approved.id) approvedId"
                        + ",GROUP_CONCAT( hmis_approved.approved_status) status"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorRoleId) executorRoleId\n"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorUserId) executorUserId"
                        + ",GROUP_CONCAT( hmis_approved.approved_isActive) activeApproved"
                        + ",approved_trackerId,approved_startDate,approved_endDate"
                        + " ,hmis_sessions.sessions_title,hmis_approved.approved_percentTracker"
                        + ",hmis_commettes.commettes_title"
                        + ",hmis_sessions.id"
                        + " FROM hmis_approved\n "
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND approved_status!='" + status_reject + "' "
                        + " AND approved_status!='" + status_offer + "' "
                        + where
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        + " group by hmis_approved.approved_title "
                        + " ORDER BY hmis_approved.id DESC ");
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("</div>"
                    + "<div class='card-header'>مصوبات من</div>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshMyApprovedTbl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='10%'>کد</th>");

            html.append("<th class='c' width='20%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='10%'>عنوان </th>");
            html.append("<th class='c' width='10%'>تاریخ</th>");
            html.append("<th class='c' width='10%'>مسئول اجرا</th>");
            html.append("<th class='c' width='10%'>مسئول پیگیری</th>");
            html.append("<th class='c' width='10%'>وضعیت مصوبه</th>");
            html.append("<th class='c' width='10%'>وضعیت پایش</th>");
            html.append("<th class='c' width='10%'>پایش</th>");
            html.append("<th class='c' width='10%'>پایش</th>");
            html.append("<tr>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("</tr>");

            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                if (!row.get(i).get("executorRoleId").equals("")) {
                    String executorRoleId = row.get(i).get("executorRoleId").toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                    }
                }
                if (!row.get(i).get("executorUserId").equals("")) {
                    String executorUserId = row.get(i).get("executorUserId").toString();
                    String[] executorUserIdArray = executorUserId.split(",");
                    for (int j = 0; j < executorUserIdArray.length; j++) {
                        System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                        ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                    }
                }
                html.append("<tr>");
                html.append("<td class='c'>" + row.get(i).get("approvedId").toString().replaceAll(",", "<br/>") + "</td>");

                html.append("<td class='c'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='c'><b>عنوان صورتجلسه:</b><br/>" + row.get(i).get(Sessions._title) + "<br/>");
                html.append("<b>عنوان کمیته</b>:<br/>" + row.get(i).get(Commettes._title)
                        //                        + "<br/>"
                        //                        + "<b>"
                        //                        + "درصد پیشرفت"
                        //                        + ":</b><br/>"
                        //                        + "<div class=\"progress \">"
                        //                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentTracker).toString() + "%;height:100%;line-height:20px\">\n"
                        //                        + "" + row.get(i).get(_percentTracker).toString() + "%\n"
                        //                        + "</div>\n"
                        //                        + "</div>" 
                        + "</td>");
                html.append("<td  class='c'>"
                        + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                        //                        + " <br/> " 
                        //                        + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                        + "");
                html.append("</td>");
                html.append("<td  class='c'>" + ExecutorUserName + ExecutorRoleName);
                html.append("</td>");
                html.append("<td  class='c'>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db));
                html.append("</td>");
                html.append("<td class='c'>");
                String[] statusApprovedArray = row.get(i).get("activeApproved").toString().split(",");
                for (int k = 0; k < statusApprovedArray.length; k++) {
                    html.append(statusApprovedArray[k].equals("0") ? "غیرنهایی" : "نهایی");
                    html.append("<br/>");
                }
                html.append("</td>");
                html.append("<td>");
                String[] flagStatusArray = row.get(i).get("status").toString().split(",");
                for (int j = 0; j < flagStatusArray.length; j++) {

                    if (flagStatusArray[j].equals(Approved.status_done)) {
                        html.append("<i class='fa fa-flag' style='color:green;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_inDoing)) {
                        html.append("<i class='fa fa-flag' style='color:#f7e30e;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_unDone)) {
                        html.append("<i class='fa fa-flag' style='color:#7f7a7a;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_incomplete)) {
                        html.append("<i class='fa fa-flag' style='color:red;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    html.append("<br/>");
                }
                html.append("</td>");

                html.append("<td class='c' ><select  id='statusMyApproved'>"
                        + "<option  value=''>تغییر وضعیت به</option>"
                        + "<option   style='color:#f7e30e' value='" + Approved.status_inDoing + "'>" + Approved.status_inDoing + "</option>"
                        + "<option  style='color:green' value='" + Approved.status_done + "'>" + Approved.status_done + "</option>"
                        + "<option  style='color:#7f7a7a' value='" + Approved.status_unDone + "'>" + Approved.status_unDone + "</option>"
                        + "<option  style='color:red' value='" + Approved.status_incomplete + "'>" + Approved.status_incomplete + "</option>"
                        + "</select><br/><button class='btnPayeshMyApproved btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  id='" + row.get(i).get("approvedId") + "' >ثبت پایش</button></td>");
                html.append("<td class='c payesh' id='" + row.get(i).get("approvedId") + "'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshMyApprovedTbl", "auto", 0, "", "جلسات");

            html2 += "  $('#refreshMyApprovedTbl').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,2,3,4,5]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearch\"><option value=\"\"></option></select>')\n"
                    + "                    .appendTo( $(column.header()).empty() )\n"
                    + "                    .on( 'change', function () {\n"
                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
                    + "                            $(this).val()\n"
                    + "                        );\n"
                    + " \n"
                    + "                        column\n"
                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
                    + "                            .draw();\n"
                    + "                    } );\n"
                    + " \n"
                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
                    + "                } );\n"
                    + "            } );\n"
                    + "        },paging:false"
                    + "        ,order:[],aaSorting:[]"
                    + "    } );"
                    + "$('.columnSelectSearch').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
            html2 += "    $('#refreshMyApprovedTbl .btnPayeshMyApproved').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + " var status=$(this).parent().find('select#statusMyApproved').val();"
                    + "if($(this).parent().find('select#statusMyApproved').val()!=''){"
                    + "hmisApproved.editAllMyApproved(value,status);"
                    + "   } "
                    + "});";
            html2 += "    $('#refreshMyApprovedTbl .payesh').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + "hmisApproved.selectforAssessmnetMyApproved(value);"
                    + "    });";

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String refreshApprovedInSeeeion(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            String SessionsId = jjTools.getParameter(request, Approved._sessionsId);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + SessionsId));
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(Approved.tableName, Approved._sessionsId + "=" + SessionsId + " "
            //                    + "AND " + Approved._status + "!='" + Approved.status_communicated + "'"
            );
            DefaultTableModel dtm2 = db.otherSelect("SELECT DISTINCT approved_title "
                    + ",GROUP_CONCAT( hmis_approved.id) approvedId"
                    + ",GROUP_CONCAT( hmis_approved.approved_status) status"
                    + ",GROUP_CONCAT( hmis_approved.approved_executorRoleId) executorRoleId"
                    + ",GROUP_CONCAT( hmis_approved.approved_executorUserId) executorUserId"
                    + ",approved_trackerId,\n"
                    + "approved_status,approved_endDate,approved_startDate,"
                    + " hmis_sessions.sessions_title,hmis_commettes.commettes_title,approved_isActive"
                    + " FROM hmis_approved\n"
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                    + " WHERE  approved_status!='" + status_offer + "'"
                    + " AND approved_status!='" + status_reject + "' "
                    + " AND approved_status!='" + status_initialRegistration + "' "
                    + " AND  hmis_approved.approved_sessionsId=" + SessionsId
                    + " group by approved_title "
                    + " ORDER BY hmis_approved.id DESC"
            );
            DefaultTableModel dtm1 = db.Select(Approved.tableName, Approved._commettesId + "=" + row.get(0).get(_commetteId) + " AND " + Approved._status + "='" + Approved.status_offer + "'");
            List<Map<String, Object>> approvedCommunicatedRow = jjDatabase.separateRow(dtm2);
            List<Map<String, Object>> approvedRow = jjDatabase.separateRow(dtm);
            List<Map<String, Object>> approvedOffererRow = jjDatabase.separateRow(dtm1);//نمایش جدول پیشنهاد ها
            System.out.println("approvedOffererRow.size()=" + approvedOffererRow.size());
            html.append("  <div style='width: 100%; padding-left: -10px;min-height:.01%'>\n"
                    + "                                    <div class='table-responsive'>");
            html.append("<table id='refreshApprovedInSession' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='30%'> مسئول اجرا</th>");
            html.append("<th class='c' width='20%'>مسئول پیگیری</th>");
            html.append("<th class='c' width='10%'>تاریخ شروع و پایان </th>");
            html.append("<th class='c' width='5%'>وضعیت</th>");
            html.append("<th class='c' width='5%'>ویرایش</th>");
            html.append("<tr>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("</tr>");
            html.append("</thead><tbody>");
            for (int i = 0; i < approvedOffererRow.size(); i++) {//پیشنهادات  برنامه عملیاتی  در جدول مصوبات که کمیته می تواند ان را رد کند یا بپذیرد
                String ExecutorUserNameOffer = "";
                String ExecutorRoleNameOffer = "";

                if (!approvedOffererRow.get(i).get(Approved._executorRoleId).equals("") && !approvedOffererRow.get(i).get(Approved._executorRoleId).equals("null")) {
                    String executorRoleId = approvedOffererRow.get(i).get(Approved._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleNameOffer += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                        System.out.println("ExecutorRoleNameOffer=" + ExecutorRoleNameOffer);
                    }
                }
                if (!approvedOffererRow.get(i).get(Approved._executorUserId).equals("") && !approvedOffererRow.get(i).get(Approved._executorUserId).equals("null")) {
                    String executorUserId = approvedOffererRow.get(i).get(Approved._executorUserId).toString();
                    String[] executorUserIdArray = executorUserId.split(",");
                    for (int j = 0; j < executorUserIdArray.length; j++) {
                        ExecutorUserNameOffer += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        System.out.println("ExecutorUserNameOffer=" + ExecutorUserNameOffer);
                    }
                }
                html.append("<tr style='background-color:#eee' class='p " + getClassCssForVaziat(approvedOffererRow.get(i).get(Approved._status).toString()) + "'>");
                html.append("<td class='c'>" + approvedOffererRow.get(i).get(Approved._id) + "</td>");
                html.append("<td class='c'>" + approvedOffererRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                html.append("<td class='c'>" + ExecutorUserNameOffer + ExecutorRoleNameOffer + "</td>");
                html.append("<td class='c'>" + Role.getRoleName(approvedOffererRow.get(i).get(Approved._trackerId).toString(), db) + "</td>");
                html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(approvedOffererRow.get(i).get(Approved._startDate)) + " ");
                html.append("<br/>" + jjCalendar_IR.getViewFormat(approvedOffererRow.get(i).get(Approved._endDate)) + "</td>");
                html.append("<td class='c' >" + approvedOffererRow.get(i).get(Approved._status) + "</td>");

                html.append("<td class='c'><span><i class=\"ion-android-add actionIconAdd\" style=\"color:green;margin:2px 2px;cursor: pointer\" onclick=\"hmisApproved.actionIconAdd(" + approvedOffererRow.get(i).get(Approved._id) + "," + SessionsId + ");\"></i><i class=\"ion-android-remove actionIconRemove\" style=\"margin:2px 2px;cursor: pointer\" onclick=\"hmisApproved.actionIconReject(" + approvedOffererRow.get(i).get(Approved._id) + "," + SessionsId + " );\"></i></span></td>");
                html.append("</tr>");
            }

            if (!row.get(0).get(Sessions._status).equals(Sessions.status_communicated)) {
                String trackerRoleName = "";
                for (int i = 0; i < approvedRow.size(); i++) {
                    String ExecutorRoleName = "";
                    String ExecutorUserName = "";
                    String ExecutorGroupName = "";

                    if (!approvedRow.get(i).get(Approved._executorRoleId).equals("") && !approvedRow.get(i).get(Approved._executorRoleId).equals("null")) {
                        String executorRoleId = approvedRow.get(i).get(Approved._executorRoleId).toString();
                        String[] executorRoleIdArray = executorRoleId.split(",");
                        for (int j = 0; j < executorRoleIdArray.length; j++) {
                            ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                            System.out.println("ExecutorRoleName=" + ExecutorRoleName);
                        }
                    }
                    if (!approvedRow.get(i).get(Approved._executorUserId).equals("") && !approvedRow.get(i).get(Approved._executorUserId).equals("null")) {
                        String executorUserId = approvedRow.get(i).get(Approved._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            System.out.println("ExecutorUserName=" + ExecutorUserName);
                        }
                    }
                    if (!approvedRow.get(i).get(Approved._executorGroupsId).equals("") && !approvedRow.get(i).get(Approved._executorGroupsId).equals("null")) {
                        String executorGroupId = approvedRow.get(i).get(Approved._executorGroupsId).toString();
                        String[] executorGroupIdArray = executorGroupId.split(",");
                        for (int j = 0; j < executorGroupIdArray.length; j++) {
//                            ExecutorGroupName += Access_Group.getGroupName(executorGroupIdArray[j], db) + ",";
                            System.out.println("executorGroupsId=" + ExecutorGroupName);
                        }
                    }
                    html.append("<tr onclick='hmisApproved.selectInSessions(" + approvedRow.get(i).get(Approved._id) + ");' class='p " + getClassCssForVaziat(approvedRow.get(i).get(Approved._status).toString()) + "'>");
                    html.append("<td class='c'>" + approvedRow.get(i).get(Approved._id) + "</td>");
                    html.append("<td class='c'>" + approvedRow.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                    html.append("<td class='c'>" + ExecutorRoleName + ExecutorUserName + ExecutorGroupName + "</td>");
                    if (!approvedRow.get(i).get(Approved._trackerId).toString().equals("")) {
                        String trackerRoleId = approvedRow.get(i).get(Approved._trackerId).toString();
                        trackerRoleName = Role.getRoleName(trackerRoleId, db);
                        html.append("<td class='c'>" + trackerRoleName + "</td>");
                    } else {
                        html.append("<td class='c'><div></div></td>");
                    }
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._startDate)) + "");
                    html.append("<br/>" + jjCalendar_IR.getViewFormat(approvedRow.get(i).get(Approved._endDate)) + "</td>");
                    html.append("<td class='c'>" + approvedRow.get(i).get(Approved._status) + "</td>");
                    html.append("<td class='c'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                    html.append("</tr>");
                }
            } else {
                for (int i = 0; i < approvedCommunicatedRow.size(); i++) {
                    String ExecutorUserName = "";
                    String ExecutorRoleName = "";
                    String[] approvedIdArray = approvedCommunicatedRow.get(i).get("approvedId").toString().split(",");
                    html.append("<tr>");
                    if (!approvedCommunicatedRow.get(i).get("executorRoleId").equals("")) {
                        String executorRoleId = approvedCommunicatedRow.get(i).get("executorRoleId").toString();
                        String[] executorRoleIdArray = executorRoleId.split(",");
                        for (int j = 0; j < executorRoleIdArray.length; j++) {
                            ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                        }
                    }
                    if (!approvedCommunicatedRow.get(i).get("executorUserId").equals("")) {
                        String executorUserId = approvedCommunicatedRow.get(i).get("executorUserId").toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                        }
                    }
                    html.append("<td class='c'>" + approvedCommunicatedRow.get(i).get("approvedId").toString().replaceAll(",", "<br/>") + "</td>");
                    html.append("<td class='r'>"
                            + approvedCommunicatedRow.get(i).get(_title).toString().replaceAll("%23A%23", "-"));
                    html.append("</td>");
//                    html.append("<td class='r'>"
//                            + "<b>"
//                            + "عنوان صورتجلسه"
//                            + ":</b><br/>" + approvedCommunicatedRow.get(i).get(Sessions._title) + "<br/>");
//                    html.append("<b>"
//                            + "عنوان کمیته:</b>"
//                            + "<br/>" + approvedCommunicatedRow.get(i).get(Commettes._title) + "</td>");
                    html.append("<td>" + ExecutorUserName + ExecutorRoleName);
                    html.append("</td>");
                    html.append("<td>" + Role.getRoleUserName(approvedCommunicatedRow.get(i).get(_trackerId).toString(), db));
                    html.append("</td>");
                    html.append("<td>"
                            + jjCalendar_IR.getViewFormat(approvedCommunicatedRow.get(i).get(_startDate))
                            + " <br/> " + jjCalendar_IR.getViewFormat(approvedCommunicatedRow.get(i).get(_endDate)) + "<br/>"
                            + "");
                    html.append("</td>");
                    html.append("<td>");
                    String[] flagStatusArray = approvedCommunicatedRow.get(i).get("status").toString().split(",");
                    for (int j = 0; j < flagStatusArray.length; j++) {
                        if (flagStatusArray[j].equals(Approved.status_done)) {
                            html.append("<i class='fa fa-flag' style='color:green;font-size: 1em;padding: 0 5px;'></i>");
                        }
                        if (flagStatusArray[j].equals(Approved.status_inDoing)) {
                            html.append("<i class='fa fa-flag' style='color:#f7e30e;font-size: 1em;padding: 0 5px;'></i>");
                        }
                        if (flagStatusArray[j].equals(Approved.status_unDone)) {
                            html.append("<i class='fa fa-flag' style='color:#7f7a7a;font-size: 1em;padding: 0 5px;'></i>");
                        }
                        if (flagStatusArray[j].equals(Approved.status_incomplete)) {
                            html.append("<i class='fa fa-flag' style='color:red;font-size: 1em;padding: 0 5px;'></i>");
                        }
                    }
                    html.append("</td>");
                    html.append("<td><div>-</div></td>");
                    html.append("</tr>");
                }
            }

            html.append("</tbody></table>"
                    + "</div></div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "approvedInSessionsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshApprovedInSession", "auto", 0, "", "جلسات");
            html2 += "  $('#refreshApprovedInSession').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,2,3]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearch\"><option value=\"\"></option></select>')\n"
                    + "                    .appendTo( $(column.header()).empty() )\n"
                    + "                    .on( 'change', function () {\n"
                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
                    + "                            $(this).val()\n"
                    + "                        );\n"
                    + " \n"
                    + "                        column\n"
                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
                    + "                            .draw();\n"
                    + "                    } );\n"
                    + " \n"
                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
                    + "                } );\n"
                    + "            } );\n"
                    + "        },"
                    + "                                                                    iDisplayLength: 100\n"
                    + "                                                                    , bJQueryUI: true\n"
                    + "                                                                    , sPaginationType: 'full_numbers'\n"
                    + "                                                                    , columnDefs: [{orderable: false, targets: 0}]\n"
                    //                    + ",'scrollY':'200px'"  
                    //                    + ",'scrollCollapse':'true'"
                    + "                                                                    , paging: false\n"
                    + "                                                                    ,order:[],aaSorting:[]"
                    + "                                                                    , dom: 'Bfrtip'\n"
                    + "                                                                    , buttons: ['copy', 'csv', 'excel', 'print']"
                    + "    } );"
                    + "$('.columnSelectSearch').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
//            html2 += "   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#refreshApprovedInSession').DataTable({\n"
//                    + "        pageLength: 100"
//                    + ", dom: 'Bfrtip'\n"
//                    + "    , buttons: ['copy', 'csv', 'excel', 'print']"
//                    + "        ,order:[],aaSorting:[]"
//                    + "       , rowGroup: {\n"
//                    + "            dataSrc: [1,3,5],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#refreshApprovedInSession tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    + "    $('#refreshApprovedInSession tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
            //                    + "$('#Grouping').on( 'click', function() {\n"
            //                     +"   var collapsedGroups = {};\n"
            //                    + "    var top = '';\n"
            //                    + "    var table = $('#refreshApprovedInSession').DataTable({\n"
            //                    + "        pageLength: 100"
            //                    + "        ,order: [\n"
            //                    + "            [2, 'asc'],\n"
            //                    + "            [1, 'asc']\n"
            //                    + "        ],\n"
            //                    + "        rowGroup: {\n"
            //                    + "            dataSrc: [2, 1],\n"
            //                    + "            startRender: function(rows, group, level) {\n"
            //                    + "                var all;\n"
            //                    + "\n"
            //                    + "                if (level === 0) {\n"
            //                    + "                    top = group;\n"
            //                    + "                    all = group;\n"
            //                    + "                } else {\n"
            //                    + "                    // if parent collapsed, nothing to do\n"
            //                    + "                    if (!!collapsedGroups[top]) {\n"
            //                    + "                        return;\n"
            //                    + "                    }\n"
            //                    + "                    all = top + group;\n"
            //                    + "                  if (collapsedGroups[all] === undefined) {\n"
            //                    + "                    collapsedGroups[all] = true;\n"
            //                    + "                  }\n"
            //                    + "                }\n"
            //                    + "\n"
            //                    + "                 var collapsed = !!collapsedGroups[all];\n"
            //                    + "\n"
            //                    + "                rows.nodes().each(function(r) {\n"
            //                    + "                    r.style.display = collapsed ? 'none' : '';\n"
            //                    + "                });\n"
            //                    + "\n"
            //                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
            //                    + "                return $('<tr/>')\n"
            //                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
            //                    + "                    .attr('data-name', all)\n"
            //                    + "                    .toggleClass('collapsed', collapsed);\n"
            //                    + "            }\n"
            //                    + "        }\n"
            //                    + "    });\n"
            //                    + " $('#refreshApprovedInSession tbody tr.group-start').each(function() {\n"
            //                    + "    var name = $(this).data('name');\n"
            //                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "  });\n"
            //                    
            //                    + "    $('#refreshApprovedInSession tbody').on('click', 'tr.dtrg-start', function() {\n"
            //                    + "        var name = $(this).data('name');\n"
            //                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "        table.draw(false);\n"
            //                    + "    });"
            //              
            //                    + "});"
            //                    + "$('#unGrouping').on( 'click', function() {\n"
            //           
            //                    + "  	table.rowGroup().disable().draw();	"
            //                    + "});"
//                    + "";   
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
                    //                    + " WHERE "
                    //                    + "approved_status!='" + status_communicated + "' "
                    + " GROUP BY approved_status "
                    + "");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshIndicatorCommettes' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='10%'>وضعیت</th>");
            html.append("<th class='c' width='15%'>درصد</th>");
            html.append("<th class='c' width='15%'>تعداد</th>");
            html.append("</thead><tbody>");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + row.get(i).get(_status) + "</td>");
                html.append("<td class='c'>" + row.get(i).get("COUNT") + "</td>");
                html.append("<td class='c'>" + row.get(i).get("Total") + "</td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName));
            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName));
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshIndicatorCommettes1' class='table table-striped table-hover dt-responsive display' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='10%'>نام ونام خانوادگی فرد </th>");
            html.append("<th class='c' width='10%'>تعداد حضور </th>");
            html.append("<th class='c' width='10%'>تعداد غیبت </th>");
            html.append("<th class='c' width='10%'>نام کمیته </th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < roleRow.size(); i++) {
                dtm2 = db.otherSelect("SELECT hmis_commettes.commettes_title,"
                        + "sessions_commettesId,count(sessions_audience)AS Audience"
                        + " FROM hmis_sessions "
                        + " LEFT JOIN hmis_commettes"
                        + " ON sessions_commettesId=hmis_commettes.id"
                        + " WHERE sessions_commettesId AND (sessions_audience LIKE  '" + roleRow.get(i).get(Role._id).toString() + ",%' OR sessions_audience LIKE  '%," + roleRow.get(i).get(Role._id).toString() + ",%' OR sessions_audience LIKE  '%," + roleRow.get(i).get(Role._id).toString() + "' OR sessions_audience=" + roleRow.get(i).get(Role._id).toString() + " )"
                //                        + " group by sessions_commettesId"
                );
                dtm3 = db.otherSelect("SELECT hmis_commettes.commettes_title,"
                        + "sessions_commettesId"
                        + ",count(sessions_absentRole)AS Absent "
                        + " FROM hmis_sessions "
                        + " LEFT JOIN hmis_commettes"
                        + " ON sessions_commettesId=hmis_commettes.id"
                        + " WHERE sessions_commettesId AND (sessions_absentRole LIKE '" + roleRow.get(i).get(Role._id).toString() + ",%' OR  sessions_absentRole LIKE '%," + roleRow.get(i).get(Role._id).toString() + ",%'  OR  sessions_absentRole LIKE '%," + roleRow.get(i).get(Role._id).toString() + "' OR sessions_audience=" + roleRow.get(i).get(Role._id).toString() + ")"
                //                        + " group by sessions_commettesId"
                );
                List<Map<String, Object>> audienceRow = jjDatabase.separateRow(dtm2);
                List<Map<String, Object>> absentRow = jjDatabase.separateRow(dtm3);
                for (int j = 0; j < audienceRow.size(); j++) {
                    html.append("<tr  class='p'>");
                    html.append("<td class='c'>" + Role.getRoleName(roleRow.get(i).get(Role._id).toString(), db) + "</td>");
                    html.append("<td class='c'>" + audienceRow.get(j).get("Audience") + "</td>");
                    for (int k = 0; k < absentRow.size(); k++) {
                        html.append("<td class='c'>" + absentRow.get(k).get("Absent") + "</td>");
                    }
                    html.append("<td class='c'>" + audienceRow.get(j).get(Commettes._title) + "</td>");
                    html.append("</tr>");
                }

            }

            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshIndicatorCommettes", "auto", 0, "", "جلسات");
            html2 += Js.table("#refreshIndicatorCommettes1", "auto", 0, "", "جلسات");
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
    public static String refreshAllApprovedWithSearch(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
            String commettesId = jjTools.getParameter(request, Approved._commettesId);
            System.out.println("????????????????????commettesId=" + commettesId);
            String status = jjTools.getParameter(request, Approved._status);
            String startDate = jjTools.getParameter(request, Approved._startDate).replaceAll("/", "");
            String endDate = jjTools.getParameter(request, Approved._endDate).replaceAll("/", "");
            String where = "";
            if (!status.equals("")) {
                where += " AND " + Approved._status + "='" + status + "' ";
            }
            if (!commettesId.equals("")) {
                where += " AND " + Approved._commettesId + "=" + commettesId;
            }
            if (!startDate.equals("")) {
                where += " AND " + Approved._startDate + ">=" + startDate;
            }
            if (!endDate.equals("")) {
                where += " AND " + Approved._endDate + "<=" + endDate;
            }

            dtm = db.otherSelect("SELECT DISTINCT approved_title "
                    + ",GROUP_CONCAT( hmis_approved.id) approvedId"
                    + ",GROUP_CONCAT( hmis_approved.approved_status) status"
                    + ",GROUP_CONCAT( hmis_approved.approved_executorRoleId) executorRoleId"
                    + ",GROUP_CONCAT( hmis_approved.approved_executorUserId) executorUserId"
                    + ",GROUP_CONCAT( hmis_approved.approved_isActive) activeApproved"
                    + ",approved_trackerId,\n"
                    + "approved_status,approved_endDate,approved_startDate,"
                    + " hmis_sessions.sessions_title,hmis_commettes.commettes_title,approved_isActive"
                    + " FROM hmis_approved\n"
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                    + " WHERE  approved_status!='" + status_offer + "'"
                    + " AND approved_status!='" + status_reject + "' "
                    + " AND approved_status!='" + status_initialRegistration + "' "
                    + where
                    + " group by approved_title"
                    + " ORDER BY hmis_approved.id DESC"
            );

            html.append("<div class='card-header'>همه مصوبات</div>");
            //            html.append("<button id=\"Grouping\">گروهبندی جدول </button>");  
//            html.append("<button id=\"unGrouping\">جداسازی جدول</button>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshApproved' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;white-space: word-wrap: break-word;'><thead>");
            html.append("<th class='c' width='10%'>کد</th>");
            html.append("<th class='c' width='20%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='20%'>عنوان کمیته</th>");
            html.append("<th class='c' width='10%'>مسئول اجرا</th>");
            html.append("<th class='c' width='10%'>مسئول پیگیری</th>");
            html.append("<th class='c' width='10%'>تاریخ شروع وپایان</th>");
            html.append("<th class='c' width='10%'>وضعیت پایش</th>");
            html.append("<th class='c' width='10%'>وضعیت مصوبه</th>");
            html.append("<th class='c' width='10%'>پایش</th>");
            html.append("<th class='c' width='10%'>پایش</th>");
            html.append(""
                    + "            <tr>"
                    + "                <th></th>"
                    + "                <th>عنوان </th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "            </tr>\n"
                    + "        </thead>" + "<tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                String[] approvedIdArray = row.get(i).get("approvedId").toString().split(",");
                html.append("<tr>");
                if (!row.get(i).get("executorRoleId").equals("")) {
                    String executorRoleId = row.get(i).get("executorRoleId").toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                    }
                }
                if (!row.get(i).get("executorUserId").equals("")) {
                    String executorUserId = row.get(i).get("executorUserId").toString();
                    String[] executorUserIdArray = executorUserId.split(",");
                    for (int j = 0; j < executorUserIdArray.length; j++) {
                        System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                        ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                    }
                }
                html.append("<td class='c'>" + row.get(i).get("approvedId").toString().replaceAll(",", "<br/>") + "</td>");
                html.append("<td class='r'>"
                        + row.get(i).get(_title).toString().replaceAll("%23A%23", "-"));
                html.append("</td>");
                html.append("<td class='r'>"
                        + "<b>"
                        + "عنوان صورتجلسه"
                        + ":</b><br/>" + row.get(i).get(Sessions._title) + "<br/>");
                html.append("<b>"
                        + "عنوان کمیته:</b>"
                        + "<br/>" + row.get(i).get(Commettes._title) + "</td>");
                html.append("<td>" + ExecutorUserName + ExecutorRoleName);
                html.append("</td>");
                html.append("<td>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db));
                html.append("</td>");
                html.append("<td>"
                        + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                        //                        + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                        + "");
                html.append("</td>");
                html.append("<td class='c'>");
                String[] statusApprovedArray = row.get(i).get("activeApproved").toString().split(",");
                for (int k = 0; k < statusApprovedArray.length; k++) {
                    html.append(statusApprovedArray[k].equals("0") ? "غیرنهایی" : "نهایی");
                    html.append("<br/>");
                }
                html.append("</td>");
                html.append("<td>");
                String[] flagStatusArray = row.get(i).get("status").toString().split(",");
                for (int j = 0; j < flagStatusArray.length; j++) {
                    if (flagStatusArray[j].equals(Approved.status_done)) {
                        html.append("<i class='fa fa-flag' style='color:green;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_inDoing)) {
                        html.append("<i class='fa fa-flag' style='color:#f7e30e;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_unDone)) {
                        html.append("<i class='fa fa-flag' style='color:#7f7a7a;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    if (flagStatusArray[j].equals(Approved.status_incomplete)) {
                        html.append("<i class='fa fa-flag' style='color:red;font-size: 1em;padding: 0 5px;'></i>");
                    }
                    html.append("<br/>");
                }
                html.append("</td>");
                html.append("<td class='c' ><select  id='status'>"
                        + "<option  value=''>تغییر وضعیت به</option>"
                        + "<option  style='color:#f7e30e'  value='" + Approved.status_inDoing + "'>" + Approved.status_inDoing + "</option>"
                        + "<option  style='color:green' value='" + Approved.status_done + "'>" + Approved.status_done + "</option>"
                        + "<option  style='color:#7f7a7a' value='" + Approved.status_unDone + "'>" + Approved.status_unDone + "</option>"
                        + "<option  style='color:red' value='" + Approved.status_incomplete + "'>" + Approved.status_incomplete + "</option>"
                        + "</select><br/><button class='btnPayesh btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  id='" + row.get(i).get("approvedId") + "' >ثبت پایش</button></td>");
                html.append("<td class='c payesh' id='" + row.get(i).get("approvedId") + "'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshApproved", "auto", 0, "", "جلسات");
            html2 += "  $('#refreshApproved').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,2,4,3,5,6]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearch\"><option value=\"\"></option></select>')\n"
                    + "                    .appendTo( $(column.header()).empty() )\n"
                    + "                    .on( 'change', function () {\n"
                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
                    + "                            $(this).val()\n"
                    + "                        );\n"
                    + " \n"
                    + "                        column\n"
                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
                    + "                            .draw();\n"
                    + "                    } );\n"
                    + " \n"
                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
                    + "                } );\n"
                    + "            } );\n"
                    + "        },paging:false"
                    + "        ,order:[],aaSorting:[]"
                    + "    } );"
                    + "$('.columnSelectSearch').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
            html2 += "    $('#refreshApproved .payesh').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + "hmisApproved.m_selectForAssessmnetApproved(value);"
                    + "    });";
            html2 += "    $('#refreshApproved .btnPayesh').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + " var status=$(this).parent().find('select#status').val();"
                    + "if($(this).parent().find('select#status').val()!=''){"
                    + "hmisApproved.editAllApprovedAll(value,status);"
                    + "   }"
                    + " });";
            //این خط کد برای زمانی است که بخواهیم جدول مورد نظر را مرج کنیم
//            html2 += "   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#refreshApproved').DataTable({\n"
//                    + "        pageLength: 100"
//                    + "        ,order: [\n"
//                    + "            [2, 'asc'],\n"
//                    + "            [1, 'asc']\n"
//                    + "        ],\n"
//                    + "        rowGroup: {\n"
//                    + "            dataSrc: [2, 1],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#refreshApproved tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    + "    $('#refreshApproved tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
//                    + "$('#Grouping').on( 'click', function() {\n"
//                     +"   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#refreshApproved').DataTable({\n"
//                    + "        pageLength: 100"
//                    + "        ,order: [\n"
//                    + "            [2, 'asc'],\n"
//                    + "            [1, 'asc']\n"
//                    + "        ],\n"
//                    + "        rowGroup: {\n"
//                    + "            dataSrc: [2, 1],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#refreshApproved tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    
//                    + "    $('#refreshApproved tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
//              
//                    + "});"
//                    + "$('#unGrouping').on( 'click', function() {\n"
//           
//                    + "  	table.rowGroup().disable().draw();	"
//                    + "});"
//                    + "";

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
            html.append("<div class='row pd-sm-30 mg-t-40'> ");
            if (Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
                html.append("<div class='col-lg-4'>     \n   "
                        + "<p class=''> "
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.refreshMyApproved();'>پایش  مصوبات من </a>\n "
                        + "</p>"
                        + "</div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsMyCommettesApproved)) {
                html.append("<div class='col-lg-4'>\n "
                        + "<p class=''>"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-15 mg-sm-r-4 tx-white col-lg' onclick='hmisApproved.refreshMyCommettesApproved();'>پایش نهایی مصوبات توسط دبیرکمیته </a>                                   "
                        + "</p>  "
                        + " </div> ");
            }
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append("<div class='col-lg-4'> \n  "
                        + " <p class=''>\n"
                        + " <a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white col-lg' onclick='hmisApproved.m_refresh();'>پایش همه مصوبات</a> "
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
                    if (i == role.length - 1) {// برای آخری  OR نمیخواهیم
                        condition1 += "(" + Commettes._secretary + " like '" + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + ",%'  OR " + Commettes._secretary + " like '%," + role[i] + "' OR " + Commettes._secretary + "=" + role[i] + " ) ";
                    } else {
                        condition1 += "(" + Commettes._secretary + " like '" + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + ",%'  OR " + Commettes._secretary + " like '%," + role[i] + "' OR " + Commettes._secretary + "=" + role[i] + " )OR ";
                    }
                }
                dtm = db.otherSelect("SELECT"
                        + " DISTINCT approved_title "
                        + ",GROUP_CONCAT( hmis_approved.id) approvedId"
                        + ",GROUP_CONCAT( hmis_approved.approved_status) status"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorRoleId) executorRoleId"
                        + ",GROUP_CONCAT( hmis_approved.approved_executorUserId) executorUserId"
                        + ",GROUP_CONCAT( hmis_approved.approved_isActive) activeApproved"
                        + ",approved_trackerId,approved_startDate,approved_endDate"
                        + " ,hmis_commettes.*"
                        + ", hmis_approved.id,hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title,hmis_approved.approved_isActive,sessions_status"
                        + ",approved_status,approved_title,approved_endDate,approved_startDate"
                        + ",approved_descriptionExecutor,approved_descriptionTracker"
                        + ",approved_percentExecutor,approved_percentTracker"
                        + ",approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName
                        + "," + Approved._tracker_userName
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                        + " WHERE  "
                        + " approved_status != '" + status_offer + "' "
                        //                        + " AND "
                        //                        + "approved_status != '" + status_communicated + "'"
                        + " AND "
                        + "approved_status != '" + status_initialRegistration + "'"
                        + " AND "
                        + "approved_status != '" + status_reject + "'"
                        + " AND "
                        + "(" + condition1 + ")"
                        + " GROUP BY hmis_approved.approved_title "
                        + " ORDER BY hmis_approved.id DESC "
                        + "");

                html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
                html.append("<table id='refreshApproved' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
                html.append("<th class='c' width='5%'>کد</th>");
                html.append("<th class='c' width='25%'>عنوان</th>");
                html.append("<th class='c' width='15%'>عنوان صورتجلسه</th>");
                html.append("<th class='c' width='20%'>تاریخ</th>");
                html.append("<th class='c' width='20%'>مسئول اجرا</th>");
                html.append("<th class='c' width='10%'>مسئول پیگیری</th>");
                html.append("<th class='c' width='10%'>وضعیت پایش</th>");
                html.append("<th class='c' width='10%'>وضعیت مصوبه</th>");
                html.append("<th class='c' width='10%'>پایش</th>");
                html.append("<th class='c' width='5%'>پایش</th>");
                html.append(""
                        + "            <tr>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "            </tr>\n"
                );
                html.append("</thead><tbody>");

                List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
                if (row.size() > 0) {
                    for (int i = 0; i < row.size(); i++) {
                        String ExecutorUserName = "";
                        String ExecutorRoleName = "";
                        if (!row.get(i).get("executorRoleId").equals("")) {
                            String executorRoleId = row.get(i).get("executorRoleId").toString();
                            String[] executorRoleIdArray = executorRoleId.split(",");
                            for (int j = 0; j < executorRoleIdArray.length; j++) {
                                ExecutorRoleName += Role.getRoleName(executorRoleIdArray[j], db) + ",";
                            }
                        }
                        if (!row.get(i).get("executorUserId").equals("")) {
                            String executorUserId = row.get(i).get("executorUserId").toString();
                            String[] executorUserIdArray = executorUserId.split(",");
                            for (int j = 0; j < executorUserIdArray.length; j++) {
                                System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                                ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            }
                        }
                        html.append("<tr>");
                        html.append("<td class='c'>" + row.get(i).get("approvedId").toString().replaceAll(",", "<br/>") + "</td>");
                        html.append("<td class='c'>" + row.get(i).get(_title) + "</td>");
                        html.append("<td class='c'><b>عنوان صورتجلسه:</b><br/>" + row.get(i).get(Sessions._title) + "<br/>");
                        html.append("<b>عنوان کمیته</b>:<br/>" + row.get(i).get(Commettes._title) + "</td>");
                        html.append("<td  class='c'>"
                                + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                                //                                + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                                + "");
                        html.append("</td>");
                        html.append("<td  class='c'>" + ExecutorUserName + ExecutorRoleName);
                        html.append("</td>");
                        html.append("<td  class='c'>" + Role.getRoleUserName(row.get(i).get(_trackerId).toString(), db));
                        html.append("</td>");
                        html.append("<td class='c'>");
                        String[] statusApprovedArray = row.get(i).get("activeApproved").toString().split(",");
                        for (int k = 0; k < statusApprovedArray.length; k++) {
                            html.append(statusApprovedArray[k].equals("0") ? "غیرنهایی" : "نهایی");
                            html.append("<br/>");
                        }
                        html.append("</td>");
                        html.append("<td>");
                        String[] flagStatusArray = row.get(i).get("status").toString().split(",");
                        for (int j = 0; j < flagStatusArray.length; j++) {
                            if (flagStatusArray[j].equals(Approved.status_done)) {
                                html.append("<i class='fa fa-flag' style='color:green;font-size: 1em;padding: 0 5px;'></i>");
                            }
                            if (flagStatusArray[j].equals(Approved.status_inDoing)) {
                                html.append("<i class='fa fa-flag' style='color:#f7e30e;font-size: 1em;padding: 0 5px;'></i>");
                            }
                            if (flagStatusArray[j].equals(Approved.status_unDone)) {
                                html.append("<i class='fa fa-flag' style='color:#7f7a7a;font-size: 1em;padding: 0 5px;'></i>");
                            }
                            if (flagStatusArray[j].equals(Approved.status_incomplete)) {
                                html.append("<i class='fa fa-flag' style='color:red;font-size: 1em;padding: 0 5px;'></i>");
                            }
                            html.append("<br/>");
                        }
                        html.append("</td>");
                        html.append("<td class='c' ><select  id='status'>"
                                + "<option  value=''>تغییر وضعیت به</option>"
                                + "<option  style='color:#f7e30e'  value='" + Approved.status_inDoing + "'>" + Approved.status_inDoing + "</option>"
                                + "<option  style='color:green' value='" + Approved.status_done + "'>" + Approved.status_done + "</option>"
                                + "<option  style='color:#7f7a7a' value='" + Approved.status_unDone + "'>" + Approved.status_unDone + "</option>"
                                + "<option  style='color:red' value='" + Approved.status_incomplete + "'>" + Approved.status_incomplete + "</option>"
                                + "</select><br/><button class='btnPayesh btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  id='" + row.get(i).get("approvedId") + "' >ثبت پایش</button></td>");
                        html.append("<td class='c payesh' id='" + row.get(i).get("approvedId") + "'><i class='icon ion-compose' style='color:#a02311'></i></td>");
                        html.append("</tr>");
                    }
                    html.append("</tbody></table>");
                } else {
                }
            }
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshApproved", "auto", 0, "", "جلسات");
            html2 += "  $('#refreshApproved').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,3,4,5,6]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearch\"><option value=\"\"></option></select>')\n"
                    + "                    .appendTo( $(column.header()).empty() )\n"
                    + "                    .on( 'change', function () {\n"
                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
                    + "                            $(this).val()\n"
                    + "                        );\n"
                    + " \n"
                    + "                        column\n"
                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
                    + "                            .draw();\n"
                    + "                    } );\n"
                    + " \n"
                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
                    + "                } );\n"
                    + "            } );\n"
                    + "        },paging:false"
                    + "        ,order:[],aaSorting:[]"
                    //                    + ",aoColumnDefs: [ "
                    //                    + "        { bSortable: false, aTargets: [  1, 2,3,4,5] }, "
                    //                    + "        { bSearchable: false, aTargets: [ 1, 2, 3,4,5 ] }"
                    //                    + "    ]"
                    + "    } );"
                    + "$('.columnSelectSearch').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });" //                    +"  $('#refresh').DataTable( {\n"
                    //                    + "aoColumnDefs: [ "    
                    //                    + "        { bSortable: false, aTargets: [  1, 2,3,4,5] }, "
                    //                    + "        { bSearchable: false, aTargets: [ 0, 1, 2, 3,4,5 ] }"
                    //                    + "    ]"
                    //                    });"
                    ;
            html2 += "    $('#refreshApproved .payesh').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + "hmisApproved.selectforAssessmnetApprovedforSecretary(value);"
                    + "    });";
            html2 += "    $('#refreshApproved .btnPayesh').on('click', function() {\n"
                    + "        var value = $(this).attr('id');\n"
                    + " var status=$(this).parent().find('select#status').val();"
                    + "if($(this).parent().find('select#status').val()!=''){"
                    + "hmisApproved.editAllApprovedForSecretary(value,status);"
                    + "   } "
                    + "});";

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
            if (jjTools.getParameter(request, _executorRoleId).equals("null") || jjTools.getParameter(request, _executorRoleId).equals("")) {
                map.put(_executorRoleId, "");
            } else {
                map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ",")));
            }
            if (jjTools.getParameter(request, _executorGroupsId).equals("null") || jjTools.getParameter(request, _executorGroupsId).equals("")) {
                map.put(_executorGroupsId, "");
            } else {
                map.put(_executorGroupsId, (jjTools.getParameter(request, _executorGroupsId).replaceAll("#A#", ",")));
            }
            if (jjTools.getParameter(request, _executorUserId).equals("null") || jjTools.getParameter(request, _executorUserId).equals("")) {
                map.put(_executorUserId, "");
            } else {
                map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ",")));
            }
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
            String script = "hmisApproved.refreshApprovedInSeeeion(" + sessionsId + ");";
            script += "$('#insertApproved2').slideUp();";
            script += "$('#approvedInSessionsTbl').slideDown();";
            script += "$('#showFilesApprovedDiv').html('');";
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
                    html.append(Js.setHtml("#ApprovedInSessions_button", "<button  class=\"btn btn-success  btn-block mg-b-10\" id=\"insert_Approved_new\" onclick='" + Js.jjApproved.insert() + "'>" + lbl_insert + "</button>"));
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_approved "
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                    + " where hmis_approved.id=" + id));
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
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                html.append(Js.removeAttr("#endDate", "disabled"));
                html.append(Js.removeAttr("#startDate", "disabled"));
                html.append(Js.removeAttr("#approved_isActiveApproved", "disabled"));
                html.append(Js.removeAttr("#approved_descriptionApproved", "disabled"));
                html.append(Js.removeAttr("#approved_percentSecretary", "disabled"));
                html.append("$('#secretaryFileApprovedDiv').show();");
//                html.append(Js.setAttr("#startDate", "disabled", "disabled"));
//                html.append(Js.setAttr("#endDate", "disabled", "disabled"));
//                html.append(Js.setAttr("#approved_descriptionApproved", "disabled", "disabled"));
//                html.append(Js.setAttr("#approved_percentSecretary", "disabled", "disabled"));
//                html.append(Js.setAttr("#approved_isActiveApproved", "disabled", "disabled"));
//                html.append("$('#secretaryFileApprovedDiv').hide();");
                html.append(Js.removeAttr("#approved_descriptionTracker", "disabled"));
                html.append(Js.removeAttr("#approved_percentTracker", "disabled"));
                html.append("$('#trackerFileApprovedDiv').show();");
                html.append(Js.removeAttr("#approved_descriptionExecutor", "disabled"));//
                html.append(Js.removeAttr("#approved_percentExecutor", "disabled"));//
                html.append("$('#executorFileApprovedDiv').show();");
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
                html4.append("<div class='col-lg-12'> "
                        + "فایل های مصوبه"
                        + "</div>"
                        + "");

                String attachFilesApprovedSecretary = row.get(0).get(Approved._file).toString();
                String[] attachFilesSecretaryArray = attachFilesApprovedSecretary.split(",");
                for (int l = 0; l < attachFilesSecretaryArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesSecretaryArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesSecretaryArray[l].substring(attachFilesSecretaryArray[l].lastIndexOf(".") + 1, attachFilesSecretaryArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html4.append("<div class='col-lg-12  mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSecretaryArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html4.append("<div class='col-lg-12  mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

            } else {
                String secretaryRoleId = "" + (row.get(0).get(Commettes._secretary).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
                String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
                String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";//با داشتن الگوی regex
                Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + "$)");
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
                                        + "</div>"
                                );
                            } else {
                                html5.append("<div class='col-lg-12   mg-l-15'>"
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
                } else {

                    Pattern p2 = Pattern.compile("(^" + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + "$)");//برای رول ها ومچ کردن با regex
                    Matcher m2 = p2.matcher(RolesId);   // get a matcher object
                    if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "") || m2.find()) {
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

                String[] secretaryArray = row.get(0).get(Commettes._secretary).toString().split(",");
                boolean flag = true;
                for (int i = 0; i < secretaryArray.length; i++) {
                    Pattern p3 = Pattern.compile("(^" + secretaryArray[i] + ",)|(," + secretaryArray[i] + ",)|(," + secretaryArray[i] + "$)");//برای رول ها ومچ کردن با regex
                    Matcher m3 = p3.matcher(RolesId);   // get a matcher object
                    if (m3.find()) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }

                if (flag == true) {
                    html.append(Js.removeAttr("#startDate", "disabled"));//
                    html.append(Js.removeAttr("#endDate", "disabled"));//
                    html.append(Js.removeAttr("#approved_descriptionApproved", "disabled"));//
                    html.append(Js.removeAttr("#approved_percentSecretary", "disabled"));//
                    html.append(Js.removeAttr("#approved_isActiveApproved", "disabled"));//
                    html.append("$('#secretaryFileApprovedDiv').show();");
                } else {
                    html.append(Js.setAttr("#startDate", "disabled", "disabled"));
                    html.append(Js.setAttr("#endDate", "disabled", "disabled"));
                    html.append(Js.setAttr("#approved_descriptionApproved", "disabled", "disabled"));
                    html.append(Js.setAttr("#approved_percentSecretary", "disabled", "disabled"));
                    html.append(Js.setAttr("#approved_isActiveApproved", "disabled", "disabled"));
                    html.append("$('#secretaryFileApprovedDiv').hide();");
                }

            }

            ///////////////////////////////////////////////////////////////////////////
            html.append(Js.setVal("#" + tableName + "_" + _id, id));
            html.append(Js.setVal("#myApproved_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _filesTracker, row.get(0).get(_filesTracker)));
            html.append(Js.setVal("#" + _filesExecutor, row.get(0).get(_filesExecutor)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.select2("#" + _status, " width: '100%'"));
            html.append(Js.setVal("#approved_isActiveApproved", row.get(0).get(_isActive)));
            html.append(Js.setVal("#approved_descriptionApproved", row.get(0).get(_description)));
            html.append(Js.setVal("#approved_percentSecretary", row.get(0).get(_percent)));
            html.append(Js.setVal("#" + _percentExecutor, row.get(0).get(_percentExecutor)));
            html.append(Js.setVal("#" + _descriptionExecutor, row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionTracker, row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#" + _percentTracker, row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#trackerId", Role.getRoleName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Role.getRoleName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
//            html3.append("فایل های مصوبه ");
//            String attachFilesApproved = row.get(0).get(Approved._file).toString();
//            String[] attachFilesApprovedArray = attachFilesApproved.split(",");
//            for (int l = 0; l < attachFilesApprovedArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        html3.append("<div class='col-lg-12  mg-l-15'>"
//                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
//                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
//                                + "</div>"
//                        );
//                    } else {
//                        html3.append("<div class='col-lg-12   mg-l-15'>"
//                                + "<i class='icon ion-ios-paper-outline'></i>"
//                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
//                                + "</div>"
//                        );
//                    }
//                } else {
//                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
//                }
//            }       

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_Approved' class='btn btn-warning btn-block mg-b-10' onclick='" + Js.jjApproved.edit() + "' >ثبت پایش</button>");
                html2.append("</div>");
            }

            html2.append("</div>");
            script += "$('#approvedsTable').slideUp();";
            script += "$('#approvedForm').slideDown();";
            script += Js.setHtml("#Approved_button", html2);
            script += "$('#showFilesSecretaryDiv').html('');";
            script += "$('#showFilesExecutorDiv').html('');";
            script += "$('#showFilesTrackerDiv').html('');";
            script += html.toString();
            script += Js.setHtml("#downloadFileApprovedDiv", html3.toString());
            script += Js.setHtml("#downloadFileTrackerDiv", html5);
            script += Js.setHtml("#downloadFileExecutorDiv", html4);
//            script += "$('#editApprovedAllForm').hide();";

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectMyApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_approved "
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                    + " where hmis_approved.id=" + id));
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

            String secretaryRoleId = "" + (row.get(0).get(Commettes._secretary).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";//با داشتن الگوی regex
            Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + "$)");
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
                                    + "</div>"
                            );
                        } else {
                            html5.append("<div class='col-lg-12   mg-l-15'>"
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
            } else {

                Pattern p2 = Pattern.compile("(^" + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + "$)");//برای رول ها ومچ کردن با regex
                Matcher m2 = p2.matcher(RolesId);   // get a matcher object
                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "") || m2.find()) {
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

//            String[] secretaryArray = row.get(0).get(Commettes._secretary).toString().split(",");
//            boolean flag = true;
//            for (int i = 0; i < secretaryArray.length; i++) {
//                Pattern p3 = Pattern.compile("(^" + secretaryArray[i] + ",)|(," + secretaryArray[i] + ",)|(," + secretaryArray[i] + "$)");//برای رول ها ومچ کردن با regex
//                Matcher m3 = p3.matcher(RolesId);   // get a matcher object
//                if (m3.find()) {
//                    flag = true;
//                    break;
//                } else {
//                    flag = false;
//                }
//            }
//
//            if (flag == true) {
//                html.append(Js.removeAttr("#startDate", "disabled"));//
//                html.append(Js.removeAttr("#endDate", "disabled"));//
//                html.append(Js.removeAttr("#approved_descriptionApproved", "disabled"));//
//                html.append(Js.removeAttr("#approved_percentSecretary", "disabled"));//
//                html.append(Js.removeAttr("#approved_isActiveApproved", "disabled"));//
//                html.append("$('#secretaryFileApprovedDiv').show();");
//                html3.append("<div class='col-lg-12'> "
//                        + "فایل های دبیر کمیته"
//                        + "</div>"
//                        + "");
//                String attachFilesApprovedSecretary = row.get(0).get(Approved._file).toString();
//                String[] attachFilesSecretaryArray = attachFilesApprovedSecretary.split(",");
//                for (int l = 0; l < attachFilesSecretaryArray.length; l++) {
//                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesSecretaryArray[l] + "'"));
//                    if (!fileRow.isEmpty()) {
//                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                        String extension2 = attachFilesSecretaryArray[l].substring(attachFilesSecretaryArray[l].lastIndexOf(".") + 1, attachFilesSecretaryArray[l].length());
//                        if (extension2.toLowerCase().equals("jpg")
//                                || extension2.toLowerCase().equals("png")
//                                || extension2.toLowerCase().equals("gif")
//                                || extension2.toLowerCase().equals("svg")) {
//                            html3.append("<div class='col-lg-12  mg-l-15'>"
//                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSecretaryArray[l] + "'/>"
//                                    + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                    + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
//                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                    + "</div>"
//                            );
//                        } else {
//                            html3.append("<div class='col-lg-12  mg-l-15'>"
//                                    + "<i class='icon ion-ios-paper-outline'></i>"
//                                    + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                    + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
//                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
//                                    + "</div>"
//                            );
//                        }
//                    } else {
//                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
//                    }
//                }
//            } else {
            html.append(Js.setAttr("#startDate", "disabled", "disabled"));
            html.append(Js.setAttr("#endDate", "disabled", "disabled"));
            html.append(Js.setAttr("#approved_descriptionApproved", "disabled", "disabled"));
            html.append(Js.setAttr("#approved_percentSecretary", "disabled", "disabled"));
            html.append(Js.setAttr("#approved_isActiveApproved", "disabled", "disabled"));
            html.append("$('#secretaryFileApprovedDiv').hide();");
            html3.append("<div class='col-lg-12'> "
                    + "فایل های مصوبه"
                    + "</div>"
                    + "");

            String attachFilesSecretaryApproved = row.get(0).get(Approved._file).toString();
            String[] attachFilesSecretaryArray = attachFilesSecretaryApproved.split(",");
            for (int l = 0; l < attachFilesSecretaryArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesSecretaryArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesSecretaryArray[l].substring(attachFilesSecretaryArray[l].lastIndexOf(".") + 1, attachFilesSecretaryArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSecretaryArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesSecretaryArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesSecretaryArray[l] + "'/>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

//            }
            ///////////////////////////////////////////////////////////////////////////
            html.append(Js.setVal("#" + tableName + "_" + _id, id));
            html.append(Js.setVal("#myApproved_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _filesTracker, row.get(0).get(_filesTracker)));
            html.append(Js.setVal("#" + _filesExecutor, row.get(0).get(_filesExecutor)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.select2("#" + _status, " width: '100%'"));
            html.append(Js.setVal("#approved_isActiveApproved", row.get(0).get(_isActive)));
            html.append(Js.setVal("#approved_descriptionApproved", row.get(0).get(_description)));
            html.append(Js.setVal("#approved_percentSecretary", row.get(0).get(_percent)));
            html.append(Js.setVal("#" + _percentExecutor, row.get(0).get(_percentExecutor)));
            html.append(Js.setVal("#" + _descriptionExecutor, row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionTracker, row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#" + _percentTracker, row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#trackerId", Role.getRoleName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Role.getRoleName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
//            html3.append("فایل های مصوبه ");
//            String attachFilesApproved = row.get(0).get(Approved._file).toString();
//            String[] attachFilesApprovedArray = attachFilesApproved.split(",");
//            for (int l = 0; l < attachFilesApprovedArray.length; l++) {
//                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesApprovedArray[l] + "'"));
//                if (!fileRow.isEmpty()) {
//                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
//                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
//                    String extension2 = attachFilesApprovedArray[l].substring(attachFilesApprovedArray[l].lastIndexOf(".") + 1, attachFilesApprovedArray[l].length());
//                    if (extension2.toLowerCase().equals("jpg")
//                            || extension2.toLowerCase().equals("png")
//                            || extension2.toLowerCase().equals("gif")
//                            || extension2.toLowerCase().equals("svg")) {
//                        html3.append("<div class='col-lg-12  mg-l-15'>"
//                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesApprovedArray[l] + "'/>"
//                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'  type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
//                                + "</div>"
//                        );
//                    } else {
//                        html3.append("<div class='col-lg-12   mg-l-15'>"
//                                + "<i class='icon ion-ios-paper-outline'></i>"
//                                + "<a  href='upload/" + attachFilesApprovedArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
//                                + "<input class='" + _file + "'   type='hidden'  value='" + attachFilesApprovedArray[l] + "'/>"
//                                + "</div>"
//                        );
//                    }
//                } else {
//                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
//                }
//            }

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                if (row.get(0).get(_isActive).equals("0")) {
                    html2.append("<div class=\"col-lg-12\">");
                    html2.append("<button  id='edit_Approved' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.editMyApproved();' >ثبت پایش</button>");
                    html2.append("</div>");
                }
            }
            html2.append("</div>");
            script += "$('#approvedsTable').slideUp();";
            script += "$('#approvedForm').slideDown();";
            script += Js.setHtml("#Approved_button", html2);
            script += "$('#showFilesSecretaryDiv').html('');";
            script += "$('#showFilesExecutorDiv').html('');";
            script += "$('#showFilesTrackerDiv').html('');";
            script += html.toString();
            script += Js.setHtml("#downloadFileApprovedDiv", html3.toString());
            script += Js.setHtml("#downloadFileTrackerDiv", html5);
            script += Js.setHtml("#downloadFileExecutorDiv", html4);
//            script += "$('#editApprovedAllForm').hide();";

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectApprovedSecretary(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_approved "
                    + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                    + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                    + " where hmis_approved.id=" + id));
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
//            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
//
//            } else {
            String secretaryRoleId = "" + (row.get(0).get(Commettes._secretary).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";//با داشتن الگوی regex
            Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + "$)");
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
                                    + "</div>"
                            );
                        } else {
                            html5.append("<div class='col-lg-12   mg-l-15'>"
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
            } else {

                Pattern p2 = Pattern.compile("(^" + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + ",)|(," + row.get(0).get(_executorRoleId).toString() + "$)");//برای رول ها ومچ کردن با regex
                Matcher m2 = p2.matcher(RolesId);   // get a matcher object
                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "") || m2.find()) {
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

            String[] secretaryArray = row.get(0).get(Commettes._secretary).toString().split(",");
            boolean flag = true;
            for (int i = 0; i < secretaryArray.length; i++) {
                Pattern p3 = Pattern.compile("(^" + secretaryArray[i] + ",)|(," + secretaryArray[i] + ",)|(," + secretaryArray[i] + "$)");//برای رول ها ومچ کردن با regex
                Matcher m3 = p3.matcher(RolesId);   // get a matcher object
                if (m3.find()) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }

            if (flag == true) {
                html.append(Js.removeAttr("#startDate", "disabled"));//
                html.append(Js.removeAttr("#endDate", "disabled"));//
                html.append(Js.removeAttr("#approved_descriptionApproved", "disabled"));//
                html.append(Js.removeAttr("#approved_percentSecretary", "disabled"));//
                html.append(Js.removeAttr("#approved_isActiveApproved", "disabled"));//
                html.append("$('#secretaryFileApprovedDiv').show();");

            } else {
                html.append(Js.setAttr("#startDate", "disabled", "disabled"));
                html.append(Js.setAttr("#endDate", "disabled", "disabled"));
                html.append(Js.setAttr("#approved_descriptionApproved", "disabled", "disabled"));
                html.append(Js.setAttr("#approved_percentSecretary", "disabled", "disabled"));
                html.append(Js.setAttr("#approved_isActiveApproved", "disabled", "disabled"));
                html.append("$('#secretaryFileApprovedDiv').hide();");

            }

            ///////////////////////////////////////////////////////////////////////////
            html.append(Js.setVal("#" + tableName + "_" + _id, id));
            html.append(Js.setVal("#myApproved_title", row.get(0).get(_title).toString().replaceAll("%23A%23", "-")));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _filesTracker, row.get(0).get(_filesTracker)));
            html.append(Js.setVal("#" + _filesExecutor, row.get(0).get(_filesExecutor)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.select2("#" + _status, " width: '100%'"));
            html.append(Js.setVal("#approved_isActiveApproved", row.get(0).get(_isActive)));
            html.append(Js.setVal("#approved_descriptionApproved", row.get(0).get(_description)));
            html.append(Js.setVal("#approved_percentSecretary", row.get(0).get(_percent)));
            html.append(Js.setVal("#" + _percentExecutor, row.get(0).get(_percentExecutor)));
            html.append(Js.setVal("#" + _descriptionExecutor, row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionTracker, row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#" + _percentTracker, row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#trackerId", Role.getRoleName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setHtml("#executorRoleId", Role.getRoleName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            html3.append("فایل های مصوبه ");
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
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12  mg-l-15'>"
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

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_Approved' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.editMyCommetteApproved();' >ثبت پایش</button>");
                html2.append("</div>");
            }
            html2.append("</div>");
            script += "$('#approvedsTable').slideUp();";
            script += "$('#approvedForm').slideDown();";
            script += Js.setHtml("#Approved_button", html2);
            script += "$('#showFilesSecretaryDiv').html('');";
            script += "$('#showFilesExecutorDiv').html('');";
            script += "$('#showFilesTrackerDiv').html('');";
            script += html.toString();
            script += Js.setHtml("#downloadFileApprovedDiv", html3.toString());
            script += Js.setHtml("#downloadFileTrackerDiv", html5);
            script += Js.setHtml("#downloadFileExecutorDiv", html4);
//            script += "$('#editApprovedAllForm').hide();";

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectforAssessmnetApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String approvedId = jjTools.getParameter(request, "approvedId");
            System.out.println("approvedId=" + approvedId);
            String[] approvedIdArray = approvedId.split(",");
            String where1 = "";
            for (int i = 0; i < approvedIdArray.length; i++) {
                if (i == approvedIdArray.length - 1) {// برای آخری  OR نمیخواهیم
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + ")";
                } else {
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + "  )OR ";
                }
            }
            StringBuilder html = new StringBuilder();
            String script = "";
            /////////////////////////////////////////////////////////////
//            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            DefaultTableModel dtm;
            String roles = jjTools.getSeassionUserRole(request);
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
//                String condition3 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
                    condition2 += " approved_trackerId =" + role[i] + " OR";
//                    condition3 += " approved_trackerId =" + role[i] + " OR";
                }
                dtm = db.otherSelect("SELECT hmis_approved.id,"
                        + "hmis_approved.approved_isActive,"
                        + "approved_status,approved_title,approved_endDate,"
                        + "approved_startDate,approved_executorRoleId,"
                        + "approved_descriptionExecutor,approved_descriptionTracker,"
                        + "approved_percentExecutor,approved_percentTracker,"
                        + "hmis_commettes.commettes_secretary,"
                        + "approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " where "
                        //                                + "(approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        //                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
                        //                        + " OR " + condition2.substring(0, condition2.length() - 2) + ") "
                        //                        + " AND  " 
                        + (where1)
                );
            } else {
                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title,hmis_approved.id,"
                        + "approved_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_descriptionExecutor,approved_descriptionTracker,"
                        + "approved_percentExecutor,approved_percentTracker,"
                        + "approved_status,approved_endDate,approved_startDate,hmis_commettes.commettes_secretary,"
                        + "approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved\n "
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND approved_status!='" + status_reject + "' "
                        //                        + " AND approved_status!='" + status_communicated + "' "
                        + " AND approved_status!='" + status_offer + "'"
                        + " AND "
                        + where1
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request)
                );
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("</div>"
                    + "<div class='card-header'>مصوبات من</div>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='MyApprovedTbl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='35%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='15%'>توضیحات کلی مصوبه</th>");
            html.append("<th class='c' width='20%'>مسئول اجرا</th>");
            html.append("<th class='c' width='20%'>مسئول پیگیری</th>");
            html.append("<th class='c'  width='10%'><input id='checkAllApproved'  type='checkbox' onchange='hmisApproved.checkAllApproved();'></th>");
            html.append("<th class='c' width='5%'>پایش</th>");
            html.append(""
                    + "            <tr>"
                    + "                <th></th>"
                    + "                <th>عنوان </th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "            </tr>\n");
            html.append("</thead>" + "<tbody>");
            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                    ExecutorRoleName = Role.getRoleName(executorRoleId, db);
                }
                if (!row.get(i).get(Approved._executorUserId).equals("")) {
                    String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                    ExecutorUserName = Access_User.getUserName(executorUserId, db);
                }
                String statusApproved = row.get(i).get(_isActive).equals("1") ? "<b style='color:red'>نهایی</b>" : "غیر نهایی";
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>"
                        + row.get(i).get(_title).toString().replaceAll("%23A%23", "-")
                        + "</td>");
                html.append("<td>"
                        + "<b>"
                        + "تاریخ شروع و پایان:</b><br/>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                        + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                        + "<b>"
                        + "وضعیت پایش:</b>"
                        + "<br/>" + statusApproved + "<br/>");
                html.append("<b>"
                        + "وضعیت مصوبه</b><br/>" + "  " + row.get(i).get(_status) + ""
                        + "<br/>"
                        + "<b>دبیر کمیته:</b><br/>" + Role.getRoleName(row.get(i).get(Commettes._secretary).toString(), db)
                        + "</td>");
                html.append("<td>");
                if (row.get(i).get(_isActive).equals("1")) {
                    if (row.get(i).get(_executorRoleId).equals("")) {
                        html.append("<b>"
                                + "مسئول اجرا:</b>"
                                + "<br/>" + ExecutorUserName + "<br/>");
                    } else {
                        html.append("<b>"
                                + "مسئول اجرا"
                                + "</b>"
                                + "<br/>" + ExecutorRoleName + " - " + row.get(i).get(_executor_userName) + "<br/>");
                    }
                } else {
                    html.append("<b>"
                            + "مسئول اجرا:"
                            + "</b>"
                            + "<br/>" + ExecutorRoleName + ExecutorUserName + "<br/>");
                }
                html.append("<b>"
                        + "توضیحات مسئول اجرا:</b>"
                        + "<br/>"
                        + row.get(i).get(Approved._descriptionExecutor));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت"
                        + ":</b><br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentExecutor).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentExecutor).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentExecutor).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + "</td>"
                );
                html.append("<td>");
                html.append("<b>"
                        + "مسئول پیگیری:</b><br/>" + Role.getRoleName(row.get(i).get(_trackerId).toString(), db) + " - " + row.get(i).get(_tracker_userName) + "<br/>");
                html.append("<b>"
                        + "توضیحات مسئول پیگیری"
                        + ":</b><br/>" + row.get(i).get(Approved._descriptionTracker));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت:"
                        + "</b>"
                        + "<br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentTracker).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentTracker).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + " </td>");
                html.append("<td class='c' width='20%'>"
                        + "<input  class='checkBoxApproved' onchange='hmisApproved.checkApproved();' type='checkbox'  id='" + row.get(i).get(_id) + "'  name='" + row.get(i).get(_id) + "' />"
                        + "</td>");
//                if (row.get(i).get(_isActive).equals("0")) {
                html.append("<td class='c' onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                } else {
//                    html.append("<td class='c'><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                }
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            script += Js.setHtml("#approvedsTable", html.toString());
            script += Js.setVal("#approvedsId", approvedId);

//            script += Js.table("#MyApprovedTbl", "auto", 0, "", "جلسات");
            script += "  $('#MyApprovedTbl').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,2,3,4]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearch\"><option value=\"\"></option></select>')\n"
                    + "                    .appendTo( $(column.header()).empty() )\n"
                    + "                    .on( 'change', function () {\n"
                    + "                        var val = $.fn.dataTable.util.escapeRegex(\n"
                    + "                            $(this).val()\n"
                    + "                        );\n"
                    + " \n"
                    + "                        column\n"
                    + "                            .search( val ? '^'+val+'$' : '', true, false )\n"
                    + "                            .draw();\n"
                    + "                    } );\n"
                    + " \n"
                    + "                column.data().unique().sort().each( function ( d, j ) {\n"
                    + "                    select.append( '<option value=\"'+d+'\">'+d+'</option>' )\n"
                    + "                } );\n"
                    + "            } );\n"
                    + "        },paging:false"
                    + "        ,order:[],aaSorting:[]"
                    + "    } );"
                    + "$('.columnSelectSearch').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
//            script += "   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
//                    + "        pageLength: 100"
//                    + "        ,order: [\n"
//                    //                    + "            [2, 'asc'],\n"
//                    + "            [1, 'asc']\n"
//                    + "        ],\n"
//                    + "        rowGroup: {\n"
//                    + "            dataSrc: [1],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    + "    $('#MyApprovedTbl tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
            //                    + "$('#Grouping').on( 'click', function() {\n"
            //                     +"   var collapsedGroups = {};\n"
            //                    + "    var top = '';\n"
            //                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
            //                    + "        pageLength: 100"
            //                    + "        ,order: [\n"
            //                    + "            [2, 'asc'],\n"
            //                    + "            [1, 'asc']\n"
            //                    + "        ],\n"
            //                    + "        rowGroup: {\n"
            //                    + "            dataSrc: [2, 1],\n"
            //                    + "            startRender: function(rows, group, level) {\n"
            //                    + "                var all;\n"
            //                    + "\n"
            //                    + "                if (level === 0) {\n"
            //                    + "                    top = group;\n"
            //                    + "                    all = group;\n"
            //                    + "                } else {\n"
            //                    + "                    // if parent collapsed, nothing to do\n"
            //                    + "                    if (!!collapsedGroups[top]) {\n"
            //                    + "                        return;\n"
            //                    + "                    }\n"
            //                    + "                    all = top + group;\n"
            //                    + "                  if (collapsedGroups[all] === undefined) {\n"
            //                    + "                    collapsedGroups[all] = true;\n"
            //                    + "                  }\n"
            //                    + "                }\n"
            //                    + "\n"
            //                    + "                 var collapsed = !!collapsedGroups[all];\n"
            //                    + "\n"
            //                    + "                rows.nodes().each(function(r) {\n"
            //                    + "                    r.style.display = collapsed ? 'none' : '';\n"
            //                    + "                });\n"
            //                    + "\n"
            //                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
            //                    + "                return $('<tr/>')\n"
            //                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
            //                    + "                    .attr('data-name', all)\n"
            //                    + "                    .toggleClass('collapsed', collapsed);\n"
            //                    + "            }\n"
            //                    + "        }\n"
            //                    + "    });\n"
            //                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
            //                    + "    var name = $(this).data('name');\n"
            //                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "  });\n"
            //                    + "    $('#MyApprovedTbl body').on('click', 'tr.dtrg-start', function() {\n"
            //                    + "        var name = $(this).data('name');\n"
            //                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "        table.draw(false);\n"
            //                    + "    });"
            //              
            //                    + "});"
            //                    + "$('#unGrouping').on( 'click', function() {\n"
            //           
            //                    + "  	table.rowGroup().disable().draw();	"
            //                    + "});"
//                    + "";
            if (approvedIdArray.length > 1) {
                script += "$('#editApprovedAllForm').show();";
            } else {
                script += "$('#editApprovedAllForm').hide();";
            }
            script += "$('#editAllApprovedBtn').attr('onclick','hmisApproved.editAllApproved()');";

            ////////////////////////////////
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectforAssessmnetMyApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String approvedId = jjTools.getParameter(request, "approvedId");
            System.out.println("approvedId=" + approvedId);
            String[] approvedIdArray = approvedId.split(",");
            String where1 = "";
            for (int i = 0; i < approvedIdArray.length; i++) {
                if (i == approvedIdArray.length - 1) {// برای آخری  OR نمیخواهیم
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + ")";
                } else {
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + "  )OR ";
                }
            }
            StringBuilder html = new StringBuilder();
            String script = "";
            /////////////////////////////////////////////////////////////
//            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            DefaultTableModel dtm;
            String roles = jjTools.getSeassionUserRole(request);
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
//                String condition3 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
                    condition2 += " approved_trackerId =" + role[i] + " OR";
//                    condition3 += " approved_trackerId =" + role[i] + " OR";
                }
                dtm = db.otherSelect("SELECT hmis_approved.id,"
                        + "hmis_approved.approved_isActive,"
                        + "approved_status,approved_title,approved_endDate,"
                        + "approved_startDate,approved_executorRoleId,"
                        + "approved_descriptionExecutor,approved_descriptionTracker,"
                        + "approved_percentExecutor,approved_percentTracker,"
                        + "hmis_commettes.commettes_secretary,"
                        + "approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " where "
                        //                                + "(approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        //                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
                        //                        + " OR " + condition2.substring(0, condition2.length() - 2) + ") "
                        //                        + " AND  " 
                        + (where1)
                );
            } else {
                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title,hmis_approved.id,"
                        + "approved_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_descriptionExecutor,approved_descriptionTracker,"
                        + "approved_percentExecutor,approved_percentTracker,"
                        + "approved_status,approved_endDate,approved_startDate,hmis_commettes.commettes_secretary,"
                        + "approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved\n "
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND approved_status!='" + status_reject + "' "
                        //                        + " AND approved_status!='" + status_communicated + "' "
                        + " AND approved_status!='" + status_offer + "'"
                        + " AND "
                        + where1
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request)
                );
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("</div>"
                    + "<div class='card-header'>مصوبات من</div>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='MyApprovedTbl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='35%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='15%'>توضیحات کلی مصوبه</th>");
            html.append("<th class='c' width='20%'>مسئول اجرا</th>");
            html.append("<th class='c' width='20%'>مسئول پیگیری</th>");
            html.append("<th class='c'  width='10%'><input id='checkAllApproved'  type='checkbox' onchange='hmisApproved.checkAllApproved();'></th>");
            html.append("<th class='c' width='5%'>پایش</th>");
            html.append(""
                    + "            <tr>"
                    + "                <th></th>"
                    + "                <th>عنوان </th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "            </tr>\n");
            html.append("</thead>" + "<tbody>");
            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                    ExecutorRoleName = Role.getRoleName(executorRoleId, db);
                }
                if (!row.get(i).get(Approved._executorUserId).equals("")) {
                    String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                    ExecutorUserName = Access_User.getUserName(executorUserId, db);
                }
                String statusApproved = row.get(i).get(_isActive).equals("1") ? "<b style='color:red'>نهایی</b>" : "غیر نهایی";
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>"
                        + row.get(i).get(_title).toString().replaceAll("%23A%23", "-")
                        + "</td>");
                html.append("<td>"
                        + "<b>"
                        + "تاریخ شروع و پایان:</b><br/>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                        + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                        + "<b>"
                        + "وضعیت پایش:</b>"
                        + "<br/>" + statusApproved + "<br/>");
                html.append("<b>"
                        + "وضعیت مصوبه</b><br/>" + "  " + row.get(i).get(_status) + ""
                        + "<br/>"
                        + "<b>دبیر کمیته:</b><br/>" + Role.getRoleName(row.get(i).get(Commettes._secretary).toString(), db)
                        + "</td>");
                html.append("<td>");
                if (row.get(i).get(_isActive).equals("1")) {
                    if (row.get(i).get(_executorRoleId).equals("")) {
                        html.append("<b>"
                                + "مسئول اجرا:</b>"
                                + "<br/>" + ExecutorUserName + "<br/>");
                    } else {
                        html.append("<b>"
                                + "مسئول اجرا"
                                + "</b>"
                                + "<br/>" + ExecutorRoleName + " - " + row.get(i).get(_executor_userName) + "<br/>");
                    }
                } else {
                    html.append("<b>"
                            + "مسئول اجرا:"
                            + "</b>"
                            + "<br/>" + ExecutorRoleName + ExecutorUserName + "<br/>");
                }
                html.append("<b>"
                        + "توضیحات مسئول اجرا:</b>"
                        + "<br/>"
                        + row.get(i).get(Approved._descriptionExecutor));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت"
                        + ":</b><br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentExecutor).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentExecutor).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentExecutor).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + "</td>"
                );
                html.append("<td>");
                html.append("<b>"
                        + "مسئول پیگیری:</b><br/>" + Role.getRoleName(row.get(i).get(_trackerId).toString(), db) + " - " + row.get(i).get(_tracker_userName) + "<br/>");
                html.append("<b>"
                        + "توضیحات مسئول پیگیری"
                        + ":</b><br/>" + row.get(i).get(Approved._descriptionTracker));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت:"
                        + "</b>"
                        + "<br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentTracker).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentTracker).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + " </td>");
                html.append("<td class='c' width='20%'>"
                        + "<input  class='checkBoxApproved' onchange='hmisApproved.checkApproved();' type='checkbox'  id='" + row.get(i).get(_id) + "'  name='" + row.get(i).get(_id) + "' />"
                        + "</td>");
//                if (row.get(i).get(_isActive).equals("0")) {
                html.append("<td class='c' onclick='hmisApproved.selectMyApproved(" + row.get(i).get(_id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                } else {
//                    html.append("<td class='c'><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                }
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            script += Js.setHtml("#approvedsTable", html.toString());
            script += Js.setVal("#approvedsId", approvedId);

            script += Js.table("#MyApprovedTbl", "auto", 0, "", "جلسات");
//            script += "   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
//                    + "        pageLength: 100"
//                    + "        ,order: [\n"
//                    //                    + "            [2, 'asc'],\n"
//                    + "            [1, 'asc']\n"
//                    + "        ],\n"
//                    + "        rowGroup: {\n"
//                    + "            dataSrc: [1],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    + "    $('#MyApprovedTbl tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
            //                    + "$('#Grouping').on( 'click', function() {\n"
            //                     +"   var collapsedGroups = {};\n"
            //                    + "    var top = '';\n"
            //                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
            //                    + "        pageLength: 100"
            //                    + "        ,order: [\n"
            //                    + "            [2, 'asc'],\n"
            //                    + "            [1, 'asc']\n"
            //                    + "        ],\n"
            //                    + "        rowGroup: {\n"
            //                    + "            dataSrc: [2, 1],\n"
            //                    + "            startRender: function(rows, group, level) {\n"
            //                    + "                var all;\n"
            //                    + "\n"
            //                    + "                if (level === 0) {\n"
            //                    + "                    top = group;\n"
            //                    + "                    all = group;\n"
            //                    + "                } else {\n"
            //                    + "                    // if parent collapsed, nothing to do\n"
            //                    + "                    if (!!collapsedGroups[top]) {\n"
            //                    + "                        return;\n"
            //                    + "                    }\n"
            //                    + "                    all = top + group;\n"
            //                    + "                  if (collapsedGroups[all] === undefined) {\n"
            //                    + "                    collapsedGroups[all] = true;\n"
            //                    + "                  }\n"
            //                    + "                }\n"
            //                    + "\n"
            //                    + "                 var collapsed = !!collapsedGroups[all];\n"
            //                    + "\n"
            //                    + "                rows.nodes().each(function(r) {\n"
            //                    + "                    r.style.display = collapsed ? 'none' : '';\n"
            //                    + "                });\n"
            //                    + "\n"
            //                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
            //                    + "                return $('<tr/>')\n"
            //                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
            //                    + "                    .attr('data-name', all)\n"
            //                    + "                    .toggleClass('collapsed', collapsed);\n"
            //                    + "            }\n"
            //                    + "        }\n"
            //                    + "    });\n"
            //                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
            //                    + "    var name = $(this).data('name');\n"
            //                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "  });\n"
            //                    + "    $('#MyApprovedTbl body').on('click', 'tr.dtrg-start', function() {\n"
            //                    + "        var name = $(this).data('name');\n"
            //                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "        table.draw(false);\n"
            //                    + "    });"
            //              
            //                    + "});"
            //                    + "$('#unGrouping').on( 'click', function() {\n"
            //           
            //                    + "  	table.rowGroup().disable().draw();	"
            //                    + "});"
//                    + "";
            if (approvedIdArray.length > 1) {
                script += "$('#editApprovedAllForm').show();";
            } else {
                script += "$('#editApprovedAllForm').hide();";
            }
            script += "$('#editAllApprovedBtn').attr('onclick','hmisApproved.editAllMyApproved2()');";
            ////////////////////////////////
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * پایش مصوبه توسط دبیر کمیته
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectforAssessmnetApprovedforSecretary(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String approvedId = jjTools.getParameter(request, "approvedId");
            System.out.println("approvedId=" + approvedId);
            String[] approvedIdArray = approvedId.split(",");
            String where1 = "";
            for (int i = 0; i < approvedIdArray.length; i++) {
                if (i == approvedIdArray.length - 1) {// برای آخری  OR نمیخواهیم
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + ")";
                } else {
                    where1 += "(hmis_approved.id=" + approvedIdArray[i] + "  )OR ";
                }
            }
            StringBuilder html = new StringBuilder();
            String script = "";
            /////////////////////////////////////////////////////////////
//            if (!Access_User.hasAccess(request, db, rul_rfsMyApproved)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            DefaultTableModel dtm;
            String roles = jjTools.getSeassionUserRole(request);
            if (!roles.equals("")) {
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
                String condition3 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " approved_executorRoleId =" + role[i] + " OR";
                    condition2 += " approved_trackerId =" + role[i] + " OR";
                    condition3 += " approved_trackerId =" + role[i] + " OR";
                }
                dtm = db.otherSelect("SELECT hmis_approved.id"
                        + ",hmis_approved.approved_isActive"
                        + ",approved_status,approved_title,approved_endDate"
                        + ",approved_startDate,approved_executorRoleId"
                        + ",approved_descriptionExecutor"
                        + ",approved_descriptionTracker"
                        + ",approved_description"
                        + ",approved_percent"
                        + ",approved_percentExecutor,approved_percentTracker"
                        + ",hmis_commettes.commettes_secretary"
                        + ",approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                        + " where "
                        //                                + "(approved_executorUserId=" + jjTools.getSeassionUserId(request)
                        //                        + " OR " + condition1.substring(0, condition1.length() - 2) + ""
                        //                        + " OR " + condition2.substring(0, condition2.length() - 2) + ") "
                        //                        + " AND  " 
                        + (where1)
                );
            } else {
                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title"
                        + ",hmis_commettes.commettes_title,hmis_approved.id"
                        + ",approved_title,hmis_approved.approved_isActive,sessions_status\n"
                        + ",approved_descriptionExecutor,approved_descriptionTracker"
                        + ",approved_percentExecutor,approved_percentTracker"
                        + ",approved_status,approved_endDate,approved_startDate,hmis_commettes.commettes_secretary"
                        + ",approved_description"
                        + ",approved_percent"
                        + ",approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + "," + Approved._executor_userName + "," + Approved._tracker_userName
                        + " FROM hmis_approved\n "
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON approved_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' "
                        + " AND approved_status!='" + status_reject + "' "
                        //                        + " AND approved_status!='" + status_communicated + "' "
                        + " AND approved_status!='" + status_offer + "' "
                        + " AND "
                        + where1
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request)
                );
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("</div>"
                    + "<div class='card-header'></div>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='MyApprovedTbl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='20%'>عنوان مصوبه</th>");
            html.append("<th class='c' width='15%'>توضیحات کلی</th>");
            html.append("<th class='c' width='15%'>مسئول اجرا</th>");
            html.append("<th class='c' width='15%'>مسئول پیگیری</th>");
            html.append("<th class='c' width='15%'>دبیرکمیته</th>");
//            html.append("<th class='c'  width='10%'><input id='checkAllApproved'  type='checkbox' onchange='hmisApproved.checkAllApproved();'></th>");
            html.append("<th class='c' width='5%'>پایش</th>");
//            html.append(""
//                    + "            <tr>"
//                    + "                <th></th>"
//                    + "                <th </th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "                <th></th>"     
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "                <th></th>"
//                    + "            </tr>\n");
            html.append("</thead>" + "<tbody>");
            for (int i = 0; i < row.size(); i++) {
                String ExecutorUserName = "";
                String ExecutorRoleName = "";
                html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'>");
                if (!row.get(i).get(Approved._executorRoleId).equals("")) {
                    String executorRoleId = row.get(i).get(Approved._executorRoleId).toString();
                    ExecutorRoleName = Role.getRoleName(executorRoleId, db);
                }
                if (!row.get(i).get(Approved._executorUserId).equals("")) {
                    String executorUserId = row.get(i).get(Approved._executorUserId).toString();
                    ExecutorUserName = Access_User.getUserName(executorUserId, db);
                }
                String statusApproved = row.get(i).get(_isActive).equals("1") ? "<b style='color:red'>نهایی</b>" : "غیر نهایی";
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>"
                        + row.get(i).get(_title).toString().replaceAll("%23A%23", "-")
                        + "</td>");
                html.append("<td>"
                        + "<b>"
                        + "تاریخ شروع و پایان:</b><br/>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate))
                        + " <br/> " + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "<br/>"
                        + "<b>"
                        + "وضعیت پایش:</b>"
                        + "<br/>" + statusApproved + "<br/>");
                html.append("<b>"
                        + "وضعیت مصوبه</b><br/>" + "  " + row.get(i).get(_status) + ""
                        + "</td>");
                html.append("<td>");
                if (row.get(i).get(_isActive).equals("1")) {
                    if (row.get(i).get(_executorRoleId).equals("")) {
                        html.append("<b>"
                                + "مسئول اجرا:</b>"
                                + "<br/>" + ExecutorUserName + "<br/>");
                    } else {
                        html.append("<b>"
                                + "مسئول اجرا"
                                + "</b>"
                                + "<br/>" + ExecutorRoleName + " - " + row.get(i).get(_executor_userName) + "<br/>");
                    }
                } else {
                    html.append("<b>"
                            + "مسئول اجرا:"
                            + "</b>"
                            + "<br/>" + ExecutorRoleName + ExecutorUserName + "<br/>");
                }
                html.append("<b>"
                        + "توضیحات مسئول اجرا:</b>"
                        + "<br/>"
                        + row.get(i).get(Approved._descriptionExecutor));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت"
                        + ":</b><br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentExecutor).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentExecutor).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentExecutor).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + "</td>"
                );
                html.append("<td>");
                html.append("<b>"
                        + "مسئول پیگیری:</b><br/>" + Role.getRoleName(row.get(i).get(_trackerId).toString(), db) + " - " + row.get(i).get(_tracker_userName) + "<br/>");
                html.append("<b>"
                        + "توضیحات مسئول پیگیری"
                        + ":</b><br/>" + row.get(i).get(Approved._descriptionTracker));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت:"
                        + "</b>"
                        + "<br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percentTracker).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percentTracker).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + " </td>");
                html.append("<td>");
                html.append("<b>"
                        + "دبیرکمیته:</b><br/>" + Role.getRoleName(row.get(i).get(Commettes._secretary).toString(), db) + "<br/>");
                html.append("<b>"
                        + "توضیحات دبیرکمیته"
                        + ":</b><br/>" + row.get(i).get(Approved._description));
                html.append("<br/>"
                        + "<b>"
                        + "درصد پیشرفت:"
                        + "</b>"
                        + "<br/>"
                        + "<div class=\"progress \">"
                        + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + row.get(i).get(_percent).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + row.get(i).get(_percent).toString() + "%;height:100%;line-height:20px\">\n"
                        + "" + row.get(i).get(_percent).toString() + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + " </td>");
//                html.append("<td class='c' width='20%'>"
//                        + "<input  class='checkBoxApproved' onchange='hmisApproved.checkApproved();' type='checkbox'  id='" + row.get(i).get(_id) + "'  name='" + row.get(i).get(_id) + "' />"
//                        + "</td>");
//                if (row.get(i).get(_isActive).equals("0")) {//دراین حالت مسئول دبیر کمیته اگر مصوبه نهایی بود باید بتواند مصوبات را باز کند
                html.append("<td class='c' onclick='hmisApproved.selectApprovedSecretary(" + row.get(i).get(_id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                } else {
//                    html.append("<td class='c'><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                }
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            script += Js.setHtml("#approvedsTable", html.toString());
            script += Js.setVal("#approvedsId", approvedId);

            script += Js.table("#MyApprovedTbl", "auto", 0, "", "جلسات");
//            script += "   var collapsedGroups = {};\n"
//                    + "    var top = '';\n"
//                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
//                    + "        pageLength: 100"
//                    + "        ,order: [\n"
//                    //                    + "            [2, 'asc'],\n"
//                    + "            [1, 'asc']\n"
//                    + "        ],\n"
//                    + "        rowGroup: {\n"
//                    + "            dataSrc: [1],\n"
//                    + "            startRender: function(rows, group, level) {\n"
//                    + "                var all;\n"
//                    + "\n"
//                    + "                if (level === 0) {\n"
//                    + "                    top = group;\n"
//                    + "                    all = group;\n"
//                    + "                } else {\n"
//                    + "                    // if parent collapsed, nothing to do\n"
//                    + "                    if (!!collapsedGroups[top]) {\n"
//                    + "                        return;\n"
//                    + "                    }\n"
//                    + "                    all = top + group;\n"
//                    + "                  if (collapsedGroups[all] === undefined) {\n"
//                    + "                    collapsedGroups[all] = true;\n"
//                    + "                  }\n"
//                    + "                }\n"
//                    + "\n"
//                    + "                 var collapsed = !!collapsedGroups[all];\n"
//                    + "\n"
//                    + "                rows.nodes().each(function(r) {\n"
//                    + "                    r.style.display = collapsed ? 'none' : '';\n"
//                    + "                });\n"
//                    + "\n"
//                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
//                    + "                return $('<tr/>')\n"
//                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
//                    + "                    .attr('data-name', all)\n"
//                    + "                    .toggleClass('collapsed', collapsed);\n"
//                    + "            }\n"
//                    + "        }\n"
//                    + "    });\n"
//                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
//                    + "    var name = $(this).data('name');\n"
//                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "  });\n"
//                    + "    $('#MyApprovedTbl tbody').on('click', 'tr.dtrg-start', function() {\n"
//                    + "        var name = $(this).data('name');\n"
//                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
//                    + "        table.draw(false);\n"
//                    + "    });"
            //                    + "$('#Grouping').on( 'click', function() {\n"
            //                     +"   var collapsedGroups = {};\n"
            //                    + "    var top = '';\n"
            //                    + "    var table = $('#MyApprovedTbl').DataTable({\n"
            //                    + "        pageLength: 100"
            //                    + "        ,order: [\n"
            //                    + "            [2, 'asc'],\n"
            //                    + "            [1, 'asc']\n"
            //                    + "        ],\n"
            //                    + "        rowGroup: {\n"
            //                    + "            dataSrc: [2, 1],\n"
            //                    + "            startRender: function(rows, group, level) {\n"
            //                    + "                var all;\n"
            //                    + "\n"
            //                    + "                if (level === 0) {\n"
            //                    + "                    top = group;\n"
            //                    + "                    all = group;\n"
            //                    + "                } else {\n"
            //                    + "                    // if parent collapsed, nothing to do\n"
            //                    + "                    if (!!collapsedGroups[top]) {\n"
            //                    + "                        return;\n"
            //                    + "                    }\n"
            //                    + "                    all = top + group;\n"
            //                    + "                  if (collapsedGroups[all] === undefined) {\n"
            //                    + "                    collapsedGroups[all] = true;\n"
            //                    + "                  }\n"
            //                    + "                }\n"
            //                    + "\n"
            //                    + "                 var collapsed = !!collapsedGroups[all];\n"
            //                    + "\n"
            //                    + "                rows.nodes().each(function(r) {\n"
            //                    + "                    r.style.display = collapsed ? 'none' : '';\n"
            //                    + "                });\n"
            //                    + "\n"
            //                    + "                // Add category name to the <tr>. NOTE: Hardcoded colspan\n"
            //                    + "                return $('<tr/>')\n"
            //                    + "                    .append('<td colspan=\"8\">' + group + ' (' + rows.count() + ')</td>')\n"
            //                    + "                    .attr('data-name', all)\n"
            //                    + "                    .toggleClass('collapsed', collapsed);\n"
            //                    + "            }\n"  
            //                    + "        }\n"
            //                    + "    });\n"
            //                    + " $('#MyApprovedTbl tbody tr.group-start').each(function() {\n"
            //                    + "    var name = $(this).data('name');\n"
            //                    + "    collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "  });\n"
            //                    + "    $('#MyApprovedTbl body').on('click', 'tr.dtrg-start', function() {\n"
            //                    + "        var name = $(this).data('name');\n"
            //                    + "        collapsedGroups[name] = !collapsedGroups[name];\n"
            //                    + "        table.draw(false);\n"
            //                    + "    });"
            //              
            //                    + "});"
            //                    + "$('#unGrouping').on( 'click', function() {\n"
            //           
            //                    + "  	table.rowGroup().disable().draw();	"
            //                    + "});"
//                    + "";
//            if (approvedIdArray.length > 1) {
//                script += "$('#editApprovedAllForm').show();";
//            } else {
//                script += "$('#editApprovedAllForm').hide();";
//            }
            ////////////////////////////////
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
                script += "$('#approvedInSessionsTbl').slideUp();";
                if (row.size() == 0) {
                    String errorMessage = "رکوردی با این کد وجود ندارد.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Select Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
                if ((row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null")) && (row.get(0).get(_executorGroupsId).equals("") || row.get(0).get(_executorGroupsId).equals("null"))) {

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
                            + "$('#approved_executorGroupsId').val('');"
                            + "hmisSessions.executorAction('سمت');"
                            + "$('input:radio[id=roleExecutor]').attr('checked','checked');"
                    );
                } else if ((row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) && (row.get(0).get(_executorGroupsId).equals("") || row.get(0).get(_executorGroupsId).equals("null"))) {
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

                } else if ((row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) && (row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null"))) {
                    String executorGroupsId = (row.get(0).get(_executorGroupsId).toString());
                    System.out.println("executorGroupsId=" + executorGroupsId);
                    String[] exeGroupsId = executorGroupsId.split(",");
                    String temp2 = "";
                    System.out.println("exeGroupsId" + exeGroupsId.length);
                    for (int i = 0; i < exeGroupsId.length; i++) {
                        temp2 += "'" + exeGroupsId[i] + "',";
                    }
                    html.append("$('#approved_executorGroupsId').val([" + temp2 + "]);"
                            + "$('#approved_executorGroupsId').select2({ width: '100%'});"
                            + "$('#approved_executorRoleId').val('');"
                            + "$('#approved_executorUserId').val('');"
                            + "hmisSessions.executorAction('گروه');"
                            + "$('input:radio[id=groupExecutor]').attr('checked','checked');"
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
                boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
                boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
                html2.append("<div class='row'>");
                List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
                if (SessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
                    if (accEdt) {
                        html2.append("<div class=\"col-lg-6\">");
                        html2.append("<button  id='edit_ApprovedInSessions' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.editInSessions();' >" + lbl_edit + "</button>");
                        html2.append("</div>");
                    }
                    if (accDel) {
                        html2.append("<div class=\"col-lg-6\">");
                        html2.append("<button id='delete_ApprovedInSessions'  class='btn btn-danger btn-block mg-b-10' onclick='hmisApproved.m_delete(" + id + ");'>" + lbl_delete + "</button>");
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

            if ((row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null")) && (row.get(0).get(_executorGroupsId).equals("") || row.get(0).get(_executorGroupsId).equals("null"))) {

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
                        + "$('#communicatedApproved_executorGroupsId').val('');"
                        + "hmisCommunicatedSessions.executorAction('سمت');"
                        + "$('input:radio[id=roleExecutorCommunicated]').attr('checked','checked');"
                );

            } else if ((row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) && (row.get(0).get(_executorGroupsId).equals("") || row.get(0).get(_executorGroupsId).equals("null"))) {
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
                        + "$('#communicatedApproved_executorGroupsId').val('');"
                        + "hmisCommunicatedSessions.executorAction('کاربران');"
                        + "$('input:radio[id=userExecutorCommunicated]').attr('checked','checked');"
                );
            } else if ((row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) && (row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null"))) {
                String executorGroupsId = (row.get(0).get(_executorGroupsId).toString());
                System.out.println("executorGroupsId=" + executorGroupsId);
                String[] exeGroupsId = executorGroupsId.split(",");
                String temp2 = "";
                System.out.println("exeGroupsId" + exeGroupsId.length);
                for (int i = 0; i < exeGroupsId.length; i++) {
                    temp2 += "'" + exeGroupsId[i] + "',";
                }

                html.append("$('#communicatedApproved_executorGroupsId').val([" + temp2 + "]);"
                        + "$('#communicatedApproved_executorGroupsId').select2({ width: '100%'});"
                        + "$('#communicatedApproved_executorRoleId').val('');"
                        + "$('#communicatedApproved_executorUserId').val('');"
                        + "hmisCommunicatedSessions.executorAction('گروه');"
                        + "$('input:radio[id=groupExecutorCommunicated]').attr('checked','checked');"
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
                    html2.append("<button  id='edit_communicatedApprovedInSessions' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.editApprovedInCommunicate();' >" + lbl_edit + "</button>");
                    html2.append("</div>");
                }
                if (accDel) {
                    html2.append("<div class=\"col-lg-6\">");
                    html2.append("<button id='delete_communicatedApprovedInSessions'  class='btn btn-danger btn-block mg-b-10' onclick='hmisApproved.deleteApprovedInCommunicate(" + id + ");'>" + lbl_delete + "</button>");
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
            html.append(Js.select2("#approvedPrevious_status", " width: '100%'"));

            html.append(Js.setValSummerNote("#approvedPrevious_description", row.get(0).get(_description)));
            html.append(Js.setHtml("#approvedPrevious_descriptionExecutor", row.get(0).get(_descriptionExecutor)));
            html.append(Js.setHtml("#approvedPrevious_descriptionTracker", row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#approvedPrevious_trackerId", Role.getRoleName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("") || row.get(0).get(_executorRoleId).equals("null")) {
                html.append(Js.setVal("#approvedPrevious_executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("") || row.get(0).get(_executorUserId).equals("null")) {
                html.append(Js.setVal("#approvedPrevious_executorRoleId", Role.getRoleName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#approvedPrevious_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#approvedPrevious_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
//            html.append(Js.setHtml("#approvedPrevious_statusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
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

//            if (accEdt) {
//                html2.append("<div class=\"col-lg-12\">");
//                html2.append("<button  id='edit_ApprovedPrevious' class='btn btn-warning btn-block mg-b-10' onclick='hmisApproved.editApprovedPrevious();' >" + lbl_edit + "</button>");
//                html2.append("</div>");
//            }
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            System.out.println("id=" + id);
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
            map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
            map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
            map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));
            map.put(_percent, jjTools.getParameter(request, _percent));
            if (jjTools.getParameter(request, _isActive).equals("1")) {
                map.put(_tracker_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), db));
                if (!row.get(0).get(_executorRoleId).equals("null") && !row.get(0).get(_executorRoleId).equals("") && !row.get(0).get(_executorRoleId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_executorRoleId).toString(), db), db));
                }
                if (!row.get(0).get(_executorUserId).equals("null") && !row.get(0).get(_executorUserId).equals("") && !row.get(0).get(_executorUserId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db));
                }
            }
            String result = changeStatus(request, db, id, jjTools.getParameter(request, _status));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
            }
            script += "hmisApproved.m_selectForAssessmnetApproved($('#approvedsId').val());";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editMyApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            System.out.println("id=" + id);
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
            map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
            map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
            map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));
            map.put(_percent, jjTools.getParameter(request, _percent));
            if (jjTools.getParameter(request, _isActive).equals("1")) {
                map.put(_tracker_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), db));
                if (!row.get(0).get(_executorRoleId).equals("null") && !row.get(0).get(_executorRoleId).equals("") && !row.get(0).get(_executorRoleId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_executorRoleId).toString(), db), db));
                }
                if (!row.get(0).get(_executorUserId).equals("null") && !row.get(0).get(_executorUserId).equals("") && !row.get(0).get(_executorUserId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db));
                }
            }
            String result = changeStatus(request, db, id, jjTools.getParameter(request, _status));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
            }
            script += "hmisApproved.selectforAssessmnetMyApproved($('#approvedsId').val());";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editMyCommetteApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            System.out.println("id=" + id);
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
            map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
            map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
            map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));
            map.put(_percent, jjTools.getParameter(request, _percent));
            if (jjTools.getParameter(request, _isActive).equals("1")) {
                map.put(_tracker_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), db));
                if (!row.get(0).get(_executorRoleId).equals("null") && !row.get(0).get(_executorRoleId).equals("") && !row.get(0).get(_executorRoleId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_executorRoleId).toString(), db), db));
                }
                if (!row.get(0).get(_executorUserId).equals("null") && !row.get(0).get(_executorUserId).equals("") && !row.get(0).get(_executorUserId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db));
                }
            }
            String result = changeStatus(request, db, id, jjTools.getParameter(request, _status));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
            }
            script += "hmisApproved.selectforAssessmnetApprovedforSecretary($('#approvedsId').val());";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * پایش تمام مصوباتی که یک چندین مسدئل پیگیری دارد
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editAllApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String[] approvedsIdArray = jjTools.getParameter(request, "approvedIdforAssessment").split(",");
            String script = "";
            for (int i = 0; i < approvedsIdArray.length; i++) {

                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _isActive, _id + "=" + approvedsIdArray[i]));
                System.out.println("id=" + approvedsIdArray[i]);
                Map<String, Object> map = new HashMap<>();
//                map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
//                map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
//                map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
                map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
                map.put(_status, jjTools.getParameter(request, _status));
//                map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
                map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));
//                if (Integer.valueOf(row.get(0).get(_isActive).toString()) == 0) {
                String result = changeStatus(request, db, approvedsIdArray[i], jjTools.getParameter(request, _status));
                db.update(tableName, map, _id + "=" + approvedsIdArray[i]);
//                }
            }
            script += "hmisApproved.m_selectForAssessmnetApproved($('#approvedsId').val());";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editAllMyApproved2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String[] approvedsIdArray = jjTools.getParameter(request, "approvedIdforAssessment").split(",");
            String script = "";
            for (int i = 0; i < approvedsIdArray.length; i++) {

                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _isActive, _id + "=" + approvedsIdArray[i]));
                System.out.println("id=" + approvedsIdArray[i]);
                Map<String, Object> map = new HashMap<>();
//                map.put(_filesExecutor, jjTools.getParameter(request, _filesExecutor));
//                map.put(_filesTracker, jjTools.getParameter(request, _filesTracker));
//                map.put(_descriptionExecutor, jjTools.getParameter(request, _descriptionExecutor));
                map.put(_descriptionTracker, jjTools.getParameter(request, _descriptionTracker));
                map.put(_status, jjTools.getParameter(request, _status));
//                map.put(_percentExecutor, jjTools.getParameter(request, _percentExecutor));
                map.put(_percentTracker, jjTools.getParameter(request, _percentTracker));
                if (Integer.valueOf(row.get(0).get(_isActive).toString()) == 0) {
                    String result = changeStatus(request, db, approvedsIdArray[i], jjTools.getParameter(request, _status));
                    db.update(tableName, map, _id + "=" + approvedsIdArray[i]);
                }
            }
            script += "hmisApproved.selectforAssessmnetMyApproved($('#approvedsId').val());";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تغییر وضعیت در پایش ها بصورت مستقیم در جدول پایش در مسئولین کمیته
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editAllApprovedForSecretary(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String[] approvedsIdArray = jjTools.getParameter(request, "approvedsId").split(",");
            String script = "";
            for (int i = 0; i < approvedsIdArray.length; i++) {
                System.out.println("status/////");
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + approvedsIdArray[i]));
                System.out.println("id=" + approvedsIdArray[i]);
                Map<String, Object> map = new HashMap<>();
                map.put(_status, jjTools.getParameter(request, _status));
                map.put(_isActive, 1);
                map.put(_tracker_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), db));
                if (!row.get(0).get(_executorRoleId).equals("null") && !row.get(0).get(_executorRoleId).equals("") && !row.get(0).get(_executorRoleId).equals("0")) {

                    map.put(_executor_userName, Access_User.getUserName(Role.getUeserIdByUserRole(row.get(0).get(_executorRoleId).toString(), db), db));
                }
                if (!row.get(0).get(_executorUserId).equals("null") && !row.get(0).get(_executorUserId).equals("") && !row.get(0).get(_executorUserId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db));
                }

//                if (Integer.valueOf(row.get(0).get(_isActive).toString()) == 0) {
                String result = changeStatus(request, db, approvedsIdArray[i], jjTools.getParameter(request, _status));
                db.update(tableName, map, _id + "=" + approvedsIdArray[i]);
//                }  
            }
            script += "hmisApproved.refreshMyCommettesApproved();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * در جدول مصوبات من بطور مستقیم مصوبه پایش می شود توسط مسئول پیگیری یا
     * مسئول اجرا
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editAllMyApproved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String[] approvedsIdArray = jjTools.getParameter(request, "approvedsId").split(",");
            String script = "";
            for (int i = 0; i < approvedsIdArray.length; i++) {
                System.out.println("status/////");
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _isActive, _id + "=" + approvedsIdArray[i]));
                System.out.println("id=" + approvedsIdArray[i]);
                Map<String, Object> map = new HashMap<>();
                map.put(_status, jjTools.getParameter(request, _status));
                if (Integer.valueOf(row.get(0).get(_isActive).toString()) == 0) {
                    String result = changeStatus(request, db, approvedsIdArray[i], jjTools.getParameter(request, _status));
                    db.update(tableName, map, _id + "=" + approvedsIdArray[i]);
                }
            }
            script += "hmisApproved.refreshMyApprovedWithSearch();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * پایش همه مصوبات بصورت مستقیم
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editAllApprovedAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String[] approvedsIdArray = jjTools.getParameter(request, "approvedsId").split(",");
            String script = "";
            for (int i = 0; i < approvedsIdArray.length; i++) {
                System.out.println("status/////");
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _isActive, _id + "=" + approvedsIdArray[i]));
                System.out.println("id=" + approvedsIdArray[i]);
                Map<String, Object> map = new HashMap<>();
                map.put(_status, jjTools.getParameter(request, _status));
//                if (Integer.valueOf(row.get(0).get(_isActive).toString()) == 0) {
                String result = changeStatus(request, db, approvedsIdArray[i], jjTools.getParameter(request, _status));
                db.update(tableName, map, _id + "=" + approvedsIdArray[i]);
//                }  
            }
            script += "hmisApproved.m_refresh();";
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
            Map<String, Object> map = new HashMap<>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            if (jjTools.getParameter(request, _executorUserId).equals("") && jjTools.getParameter(request, _executorGroupsId).equals("")) {
                map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ",")));
                map.put(_executorUserId, "");
                map.put(_executorGroupsId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("") && jjTools.getParameter(request, _executorGroupsId).equals("")) {
                map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ",")));
                map.put(_executorRoleId, "");
                map.put(_executorGroupsId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("") && jjTools.getParameter(request, _executorUserId).equals("")) {
                map.put(_executorGroupsId, (jjTools.getParameter(request, _executorGroupsId).replaceAll("#A#", ",")));
                map.put(_executorRoleId, "");
                map.put(_executorUserId, "");
            }

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "hmisApproved.refreshApprovedInSeeeion(" + sessionsId + ");";
            script += "$('#insertApproved2').slideUp();";
            script += "$('#approvedInSessionsTbl').slideDown();";
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
            if (jjTools.getParameter(request, _executorUserId).equals("") && jjTools.getParameter(request, _executorGroupsId).equals("")) {
                map.put(_executorRoleId, (jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ",")));
                map.put(_executorUserId, "");
                map.put(_executorGroupsId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("") && jjTools.getParameter(request, _executorGroupsId).equals("")) {
                map.put(_executorUserId, (jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ",")));
                map.put(_executorRoleId, "");
                map.put(_executorGroupsId, "");
            } else if (jjTools.getParameter(request, _executorRoleId).equals("") && jjTools.getParameter(request, _executorUserId).equals("")) {
                map.put(_executorGroupsId, (jjTools.getParameter(request, _executorGroupsId).replaceAll("#A#", ",")));
                map.put(_executorRoleId, "");
                map.put(_executorUserId, "");
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
            map.put(_status, jjTools.getParameter(request, _status).equals("null") || jjTools.getParameter(request, _status).equals("") ? approvedRow.get(0).get(Approved._status) : jjTools.getParameter(request, _status));
            map.put(_isActive, jjTools.getParameter(request, _isActive));//نهایی
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_percent, jjTools.getParameter(request, _percent));
            if (jjTools.getParameter(request, _isActive).equals("1")) {
                map.put(_tracker_userName, Access_User.getUserName(Role.getUeserIdByUserRole(approvedRow.get(0).get(_trackerId).toString(), db), db));
                if (!approvedRow.get(0).get(_executorRoleId).equals("null") && !approvedRow.get(0).get(_executorRoleId).equals("")) {

                    map.put(_executor_userName, Access_User.getUserName(Role.getUeserIdByUserRole(approvedRow.get(0).get(_executorRoleId).toString(), db), db));
                }
                if (!approvedRow.get(0).get(_executorUserId).equals("null") && !approvedRow.get(0).get(_executorUserId).equals("") && !approvedRow.get(0).get(_executorUserId).equals("0")) {
                    map.put(_executor_userName, Access_User.getUserName(approvedRow.get(0).get(_executorUserId).toString(), db));
                }
            }

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
            script += "$('#approvedInSessionsTbl').slideDown();";
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
            String usersId = "";
            if (!Access_User.hasAccess(request, db, rul_communicatedApproved)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            String text = "";
            StringBuilder html = new StringBuilder();
            String ExeRId = "";

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            html.append("<br/>");
            html.append("<br/>");
            html.append("عنوان مصوبه:" + row.get(0).get(Approved._title).toString().replaceAll("%23A%23", "-"));
            html.append("<br/>");
            html.append("تاریخ شروع :" + row.get(0).get(Approved._startDate));
            html.append("<br/>");
            html.append("تاریخ پایان :" + row.get(0).get(Approved._endDate));
            html.append("<br/>");
            String attachFiles = row.get(0).get(Approved._file).toString();
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
                        html.append(""
                                + "<img class='wd-40  mg-r-20' src='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'>" + titleUpload + "</a>"
                        );
                    } else {
                        html.append(""
                                + "<div>"
                                + "<a  href='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'>" + titleUpload + "</a>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            List<Map<String, Object>> sessionRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._title, Sessions._id + "=" + row.get(0).get(_sessionsId)));
            String ExecutorRoleId = row.get(0).get(_executorRoleId).toString();
            String ExecutorUserId = row.get(0).get(_executorUserId).toString();
            String ExecutorGroupsId = row.get(0).get(_executorGroupsId).toString();
            Map<String, Object> map = new HashMap();
            boolean flag = true;
            if (!ExecutorRoleId.equals("") && !ExecutorRoleId.equals("null")) {

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
                        map.put(_offererId, row.get(0).get(_offererId));
                        map.put(_indicatorDescription_id, row.get(0).get(_indicatorDescription_id));
                        map.put(_status, status_communicated);
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
                                + status_communicated
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
                            text = "لطفا نسبت به پایش اجرای مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[i], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                            + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                            + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                            + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                }
            } else if (!ExecutorUserId.equals("") && !ExecutorUserId.equals("null")) {
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
                        map.put(_offererId, row.get(0).get(_offererId));
                        map.put(_indicatorDescription_id, row.get(0).get(_indicatorDescription_id));
                        map.put(_status, status_communicated);
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
                                + status_communicated
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
                            text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, ExeUserId[i], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
//                    text = "لطفا نسبت به پیگیری پایش اجرای مصوبه با عنوان " + row.get(0).get(_title) + " " + " به عنوان مسئول پیگیری اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                            + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                            + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                            + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                }
            } else if (!ExecutorGroupsId.equals("") && !ExecutorGroupsId.equals("null")) {
                String[] ExeGroupsId = ExecutorGroupsId.split(",");
                for (int i = 0; i < ExeGroupsId.length; i++) {
//                    usersId += Access_Group.getUserIdByGroupId(ExeGroupsId[i], db) + ",";
                }
                List<String> newList = new ArrayList<>();// 
                String[] arrayStr = usersId.split(",");
                for (int i = 0; i < arrayStr.length; i++) {//اگر ای دی کاربر تکراری بود داخل لیست جدید نمی آید
                    if (!newList.contains(arrayStr[i])) {
                        newList.add(arrayStr[i]);
                    }
                }
                usersId = String.join(",", newList);//تبدیل به استرینگ می شود برای خروجی
                String[] usersIdArray = usersId.split(",");
                for (int i = 0; i < usersIdArray.length; i++) {
                    if (jjNumber.isDigit(usersIdArray[i])) {
                        map.put(_title, row.get(0).get(_title));
                        map.put(_endDate, row.get(0).get(_endDate).toString().replaceAll("/", ""));
                        map.put(_startDate, row.get(0).get(_startDate).toString().replaceAll("/", ""));
                        map.put(_file, row.get(0).get(_file));
                        map.put(_description, row.get(0).get(_description));
                        map.put(_executorGroupsId, "");
                        map.put(_executorUserId, usersIdArray[i]);
                        map.put(_trackerId, row.get(0).get(_trackerId));
                        map.put(_sessionsId, row.get(0).get(_sessionsId));
                        map.put(_commettesId, row.get(0).get(_commettesId));
                        map.put(_offererId, row.get(0).get(_offererId));
                        map.put(_indicatorDescription_id, row.get(0).get(_indicatorDescription_id));
                        map.put(_status, status_communicated);
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
                                + status_communicated
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
//                            text = "لطفا نسبت به پایش اجرای مصوبه با عنوان " + row.get(0).get(_title) + " " + " به عنوان مسئول اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                            text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, usersIdArray[i], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    } else if (usersIdArray[i].equals("")) {
//                        Server.outPrinter(request, response, Js.modal("فردی به عنوان مسئول اجرا در گروه موردنظر وجود ندارد", "پیام سامانه خطای شماره200"));
//                        return "";  
                    }
                }

                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
//                    text = "لطفا نسبت به پیگیری پایش اجرای مصوبه با عنوان " + row.get(0).get(_title) + " " + " به عنوان مسئول پیگیری اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                            + "عنوان مصوبه:" + row.get(0).get(_title) + " "
                            + "تاریخ شروع:" + row.get(0).get(_startDate).toString() + " "
                            + "تاریخ پایان:" + row.get(0).get(_endDate).toString() + " ";
                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(0).get(_endDate).toString());
                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                }
            }
            System.out.println("id=" + id);
            String script = "";
//                    "hmisCommunicatedSessions.m_refresh();";
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
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            String script = "";
            String ExeRId = "";
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");
            List<Map<String, Object>> sessionRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
            List< Map< String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _sessionsId + "=" + sessionsId + " AND approved_status='" + status_initialRegistration + "'"));
            String text = "";

            StringBuilder html = new StringBuilder();
            if (row.size() > 0) {

                for (int i = 0; i < row.size(); i++) {
                    html.append("<br/>");
                    html.append("<br/>");
                    html.append("عنوان مصوبه:" + row.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-"));
                    html.append("<br/>");
                    html.append("تاریخ شروع :" + row.get(i).get(Approved._startDate));
                    html.append("<br/>");
                    html.append("تاریخ پایان :" + row.get(i).get(Approved._endDate));
                    html.append("<br/>");
                    String attachFiles = row.get(i).get(Approved._file).toString();
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
                                html.append(""
                                        + "<img class='wd-40  mg-r-20' src='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'/>"
                                        + "<a  href='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'>" + titleUpload + "</a>"
                                );
                            } else {
                                html.append(""
                                        + "<div>"
                                        + "<a  href='" + Server.mainSite + "/upload/" + attachFilesArray[l] + "'>" + titleUpload + "</a>"
                                        + "</div>"
                                );
                            }
                        } else {
                            //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                        }
                    }
                    String id = row.get(i).get(_id).toString();
                    String ExecutorRoleId = row.get(i).get(_executorRoleId).toString();
                    String ExecutorUserId = row.get(i).get(_executorUserId).toString();
                    String ExecutorGroupsId = row.get(i).get(_executorGroupsId).toString();

                    if (!ExecutorRoleId.equals("") && !ExecutorRoleId.equals("null")) {
                        boolean flag = true;
                        String[] ExeRoleId = ExecutorRoleId.split(",");
                        Map<String, Object> map = new HashMap();
                        for (int j = 0; j < ExeRoleId.length; j++) {
                            if (jjNumber.isDigit(ExeRoleId[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_file, row.get(i).get(_file));
                                map.put(_description, row.get(i).get(_description));
                                map.put(_executorRoleId, ExeRoleId[j]);
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_sessionsId, row.get(i).get(_sessionsId));
                                map.put(_commettesId, row.get(i).get(_commettesId));
                                map.put(_offererId, row.get(i).get(_offererId));
                                map.put(_indicatorDescription_id, row.get(i).get(_indicatorDescription_id));
                                map.put(_status, status_communicated);
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
                                        + status_communicated
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
//                                    text = "لطفا نسبت به پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول اجرای مصوبه در تاریخ مقرر اقدام فرمایید";

                                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                            + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                            + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                            + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[j], db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
//                            text = "لطفا نسبت به پیگیری پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول پیگیری اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                            text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + "", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    } else if (!ExecutorUserId.equals("") && !ExecutorUserId.equals("null")) {
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
                                map.put(_offererId, row.get(i).get(_offererId));
                                map.put(_indicatorDescription_id, row.get(i).get(_indicatorDescription_id));
                                map.put(_status, status_communicated);
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
                                        + status_communicated
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
//                                    text = "لطفا نسبت به پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                            + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                            + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                            + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                        Messenger.sendMesseage(null, db, ExeUserId[j], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
//                            text = "لطفا نسبت به پیگیری  پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول پیگیری اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                            text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    } else if (!ExecutorGroupsId.equals("") && !ExecutorGroupsId.equals("null")) {
                        boolean flag = true;
                        Map<String, Object> map = new HashMap();
                        String[] ExeGroupsId = ExecutorGroupsId.split(",");
                        String usersId = "";
                        for (int j = 0; j < ExeGroupsId.length; j++) {
//                            usersId += Access_Group.getUserIdByGroupId(ExeGroupsId[j], db);
                        }
                        List<String> newList = new ArrayList<>();// 
                        String[] arrayStr = usersId.split(",");
                        for (int j = 0; j < arrayStr.length; j++) {//اگر ای دی کاربر تکراری بود داخل لیست جدید نمی آید
                            if (!newList.contains(arrayStr[j])) {
                                newList.add(arrayStr[j]);
                            }
                        }
                        usersId = String.join(",", newList);//تبدیل به استرینگ می شود برای خروجی
                        String[] usersIdArray = usersId.split(",");

                        for (int j = 0; j < usersIdArray.length; j++) {
                            String reciverId = "";
                            if (jjNumber.isDigit(usersIdArray[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_file, row.get(i).get(_file));
                                map.put(_description, row.get(i).get(_description));
                                map.put(_executorUserId, usersIdArray[j]);
                                map.put(_executorGroupsId, "");
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_sessionsId, row.get(i).get(_sessionsId));
                                map.put(_commettesId, row.get(i).get(_commettesId));
                                map.put(_offererId, row.get(i).get(_offererId));
                                map.put(_indicatorDescription_id, row.get(i).get(_indicatorDescription_id));
                                map.put(_status, status_communicated);
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
                                        + status_communicated
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
//                                    text = "لطفا نسبت به پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                                    text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول اجرا در تاریخ مقرر اقدام فرمایید" + " "
                                            + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                            + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                            + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                            + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                                    jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                                    jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                                    if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                        Messenger.sendMesseage(null, db, usersIdArray[j], "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                                    }
                                }
                            } else if (usersIdArray[j].equals("")) {
//                                Server.outPrinter(request, response, Js.modal("فردی به عنوان مسئول اجرا در گروه موردنظر وجود ندارد", "پیام سامانه خطای شماره200"));
//                                return "";  
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
//                            text = "لطفا نسبت به پیگیری  پایش اجرای مصوبه با عنوان " + row.get(i).get(_title) + " " + " به عنوان مسئول پیگیری اجرای مصوبه در تاریخ مقرر اقدام فرمایید";
                            text = "لطفا نسبت به پایش  مصوبه به عنوان مسئول پیگیری در تاریخ مقرر اقدام فرمایید" + " "
                                    + "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " "
                                    + "عنوان مصوبه:" + row.get(i).get(_title) + " "
                                    + "تاریخ شروع:" + row.get(i).get(_startDate).toString() + " "
                                    + "تاریخ پایان:" + row.get(i).get(_endDate).toString() + " ";
                            jjCalendar_IR startDate = new jjCalendar_IR(row.get(i).get(_startDate).toString());
                            jjCalendar_IR endDate = new jjCalendar_IR(row.get(i).get(_endDate).toString());
                            if (today <= startDate.getDBFormat_8length() || endDate.getDBFormat_8length() >= today) {
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + sessionRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + row.get(i).get(_title) + " ", text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                            }
                        }
                    }
                }
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
     * زمان اضافه کردن یک پیشنهاد به مصوبات مان این تایع استفاده می کنیم تایید
     * مصوبه
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
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.JoinLeft(Sessions.tableName, Commettes.tableName, "hmis_sessions.sessions_title,hmis_sessions.sessions_status,hmis_commettes.commettes_title", Sessions._commetteId, Commettes._id + " WHERE hmis_sessions.id=" + sessionsId));
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
                        String text = "اقدام اصلاحی شمابا عنوان   /پیشنهاد " + approvedRow.get(0).get(_title) + " " + " درکمیته " + SessionsRow.get(0).get(Commettes._title) + " " + tice_configMessage;
                        String html = "";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(approvedRow.get(0).get(_offererId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + SessionsRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + approvedRow.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
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
            List<Map<String, Object>> SessionsRow = jjDatabase.separateRow(db.JoinLeft(Sessions.tableName, Commettes.tableName, "hmis_sessions.sessions_status,hmis_sessions.sessions_title,hmis_commettes.commettes_title", Sessions._commetteId, Commettes._id + " WHERE hmis_sessions.id=" + sessionsId));
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
                        String text = "اقدام اصلاحی شمابا عنوان   /پیشنهاد " + approvedRow.get(0).get(_title) + " " + " درکمیته " + SessionsRow.get(0).get(Commettes._title) + " " + tice_configMessage;
                        String html = "";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(approvedRow.get(0).get(_offererId).toString(), db), "1", "sms,app,email", "", "عنوان صورتجلسه:" + SessionsRow.get(0).get(Sessions._title) + " عنوان مصوبه:" + approvedRow.get(0).get(_title), text, html.toString(), "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
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
    public static String refreshMyApprovedInDashbord(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
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
                        + " approved_status != '" + Approved.status_initialRegistration + "'"
                        + " AND"
                        + " approved_status != '" + Approved.status_offer + "'"
                        + " AND "
                        + "approved_status != '" + Approved.status_reject + "' "
                        //                        + " AND "
                        //                        + "approved_status != '" + Approved.status_communicated + "'"
                        + " AND (approved_executorUserId=" + jjTools.getSeassionUserId(request) + " OR " + condition1.substring(0, condition1.length() - 2) + " OR " + condition2.substring(0, condition2.length() - 2) + ")");
            } else {

                dtm = db.otherSelect("SELECT hmis_sessions.sessions_title,hmis_commettes.commettes_title,hmis_approved.id,approved_title,hmis_approved.approved_isActive,sessions_status,\n"
                        + "approved_status,approved_endDate,approved_startDate,approved_executorRoleId,approved_executorUserId,approved_trackerId"
                        + " FROM hmis_approved\n"
                        + " LEFT JOIN hmis_sessions ON approved_sessionsId=hmis_sessions.id\n"
                        + " LEFT JOIN hmis_commettes ON hmis_sessions.sessions_commettesId=hmis_commettes.id\n"
                        + " WHERE sessions_status='" + Sessions.status_communicated + "' AND approved_status!='" + Approved.status_offer + "'"
                        + " "
                        + "AND approved_status!='" + Approved.status_reject + "' "
                        //                        + "AND approved_status!='" + Approved.status_communicated + "' "
                        + " AND approved_executorUserId=" + jjTools.getSeassionUserId(request));
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append(" <div class='card bd-primary mg-t-20' >"
                    + "    <div class='card-header bg-indigo tx-white c' style='font-style:bold;'>مصوبه های من</div>"
                    + "        <div style='height:300px;overflow-y: scroll;'>");

            html.append("<table class='table' id='refreshShowMyApprove' dir='rtl'><thead>");
            html.append("</thead><tbody>");
            if (row.size() > 10) {
                int j = 10;
                int i = row.size() - 1;
                while (j > 0) {
                    if (row.get(i).get(_isActive).equals("0")) {

                        html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + " odd'>");
                    } else {
                        html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + " odd'>");
                    }
                    html.append("<td style='text-align:center'>" + (row.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-")) + "</td>");
                    html.append("</tr>");
                    i--;
                    j--;
                }
            } else {
                for (int i = 0; i < row.size(); i++) {
                    if (row.get(i).get(_isActive).equals("0")) {
                        html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + " odd'>");
                    } else {
                        html.append("<tr  class='p " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + " odd'>");
                    }
                    html.append("<td style='text-align:center'><a href='#swMyApprov' onclick='hmisApproved.m_refresh();hmisApproved.loadForm();' class='nav-link ShowOneDiv active'>" + (row.get(i).get(Approved._title).toString().replaceAll("%23A%23", "-")) + "</a></td>");
                    html.append("</tr>");
                }
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swMyApprov";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, html2 + "$('.ShowOneDiv').click(function () {showOneDiv(this);});");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
