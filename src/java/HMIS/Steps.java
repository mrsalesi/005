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
 * @author shohreh.shiran Date 1397.10.04
 */
public class Steps {

    public static String tableName = "hmis_steps";
    public static String _id = "id";
    public static String _plansId = "steps_plansId";//ای دی برنامه ها
    public static String _title = "steps_title";//عنوان
    public static String _executorRoleId = "steps_executorRoleId";//مسئول اجرا
    public static String _executorUserId = "steps_executorUserId";//مسئول اجرا
    public static String _trackerId = "steps_trackerId";//مسئول پیگیری
    public static String _otherIndicators = "steps_otherIndicators";//سایر شاخص ها
    public static String _startDate = "steps_startDate";//مسئول اجرا
    public static String _endDate = "steps_endDate";//مسئول اجرا
    public static String _files = "steps_files";//مستندات
    public static String _filesTracker = "steps_trackerFiles";//مستندات مسئول پیگیری
    public static String _filesExecutor = "steps_executorFiles";//مستندات  مسئول اجرا
    public static String _percent = "steps_percent";//درصد
    public static String _cost = "steps_cost";//هزینه
    public static String _statusLog = "steps_statusLog";//روند وضعیت
    public static String _status = "steps_status";//وضعیت
    public static String _isActive = "steps_isActive";//وضعیت
    public static String _descriptionTracker = "steps_descriptionTracker";//توضیحات پیگیری کننده
    public static String _descriptionExecutor = "steps_descriptionExecutor";//توضیحات اجرا  کننده
    public static String _progressPercent = "steps_progressPercent";//درصد پیشرفت
    public static String _percentExecutor = "steps_percentExecutor";//درصد پیشرفت گام مسئول اجرا یا مسئول پیگیری
    public static String _percentTracker = "steps_percentTracker";
    public static String _description = "steps_description";
    public static String _descriptionImproveQuality = "steps_descriptionImproveQuality";//توضیحات مسئول پایش یا بهبود کیفیت
    /////////////////////////////////////////////////
//    public static int rul_editStepsMyPlans = 389;//
//    public static int rul_editStepsInplans = 373;//
//    public static int rul_editStepsInManager = 374;//
    public static int rul_rfs = 404;
    public static int rul_ins = 405;
    public static int rul_edt = 406;
    public static int rul_edtStepInPlansForAssess = 407;
    public static int rul_edtStepInPlans = 408;
    public static int rul_dltStepInPlans = 409;
    public static int rul_edtStepByManager = 410;
    public static int rul_dltStepByManager = 411;
    public static int rul_edtStepInMyPlans = 412;
    public static int rul_dltStepInMyPlans = 413;
//    public static int rul_edtStepByImproveQuality = 415;
//    public static int rul_dltStepByImproveQuality = 416;

    public static int rul_rfsMySteps = 418;
    public static int rul_edtMySteps = 419;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";
    public static String status_inDoing = "در حال انجام";
    public static String status_unDone = "غیر قابل انجام";
    public static String status_done = "انجام شده";
    public static String status_initialRegistration = "ثبت اولیه";
//////////////////////////////////////////////////////////////////

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _status + "!='" + status_initialRegistration + "'");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("<div class=\"card-header bg-primary tx-white\">گام های اجرایی</div>\n");
            html.append(""
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisSteps.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshSteps' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='30%'>عنوان</th>");
            html.append("<th width='30%'>مسئول اجرا</th>");
            html.append("<th width='30%'>مسئول پیگیری</th>");
            html.append("<th width='5%'>وضعیت گام</th>");
            html.append("<th width='30%'>وضعیت</th>");
            html.append("<th width='20%'>تاریخ شروع</th>");
            html.append("<th width='20%'>تاریخ پایان</th>");
            html.append("<th width='20%'>هزینه</th>");
            html.append("<th width='5%'>پایش</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(_isActive).equals("1")) {
                    html.append("<tr  class='mousePointer " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'  onclick='new jj('وضعیت نهایی شده است').jjModal('پیام سامانه');'>");
                } else {
                    html.append("<tr  class='mousePointer " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'  onclick='hmisSteps.m_select(" + row.get(i).get(_id) + ")'>");
                }
                String isActive = row.get(i).get(_isActive).equals("0") ? "غیر نهایی" : "نهایی";
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + Access_User.getUserName(row.get(i).get(_executorUserId).toString(), db) + Role.getRoleUserName(row.get(i).get(_executorRoleId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName((row.get(i).get(_trackerId).toString()), db) + "</td>");
                html.append("<td class='c'>" + isActive + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate).toString()) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate).toString()) + "</td>");
                html.append("<td class='r'>" + jjNumber.getFormattedNumber(row.get(i).get(_cost).toString()) + "</td>");
                html.append("<td class='c'><i class='icon ion-compose'></i></td>");
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
                panel = "swStepsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshSteps", "300", 0, "", "گام ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این جدول گام هایی را نشان می دهد که ثبت اولیه نباشند گام مسئول پیگیری
     * باشد یا مسئول اجرا
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMySteps(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMySteps)) {
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
                    condition1 += " steps_executorRoleId =" + role[i] + " OR";
                    condition2 += " steps_trackerId =" + role[i] + " OR";
                }
                dtm = db.otherSelect("SELECT * FROM hmis_steps WHERE steps_status!='" + status_initialRegistration + "' AND (steps_executorUserId=" + jjTools.getSeassionUserId(request) + " OR " + condition1.substring(0, condition1.length() - 2) + " OR " + condition2.substring(0, condition2.length() - 2) + ")");
            } else {
                dtm = db.otherSelect("SELECT * FROM hmis_steps WHERE steps_status!='" + status_initialRegistration + "' AND  steps_executorUserId=" + jjTools.getSeassionUserId(request));
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\"> گام های اجرایی من</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\">گام های اجرایی من</div>\n");
            html.append("<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisMySteps.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
//            html.append("<div class='col-lg-3'>"
//                    + "<a href='#' id='learnPlansIcon' class='sh-pagetitle-icon' title='آموزش ماژول گام ها'>"
//                    + "<div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>"
//                    + "</div>"
//                    + "</a>"
//                    + "<span  style='display:block' class='col-lg-12'>"
//                    + "<div id='plans_learn' style='display:none'>"
//                    + "<a>فیلم آموزشی</a>"
//                    + "<br/>"
//                    + "<a>فایل آموزشی</a>"
//                    + "</div>"
//                    + "</span>"
//                    + "</div>"
//                    + "");

            html.append(""
                    + " <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    //                    + " <a class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" style='color:#fff' onclick=\"hmisPlans.m_add_new();\" >گام های اجرایی</a>\n"
                    + "  </p>\n"
                    + "");
//            html.append("<div class=\"card-header bg-primary tx-white\">گام های اجرایی</div>\n");
//            html.append(""
//                    + "<div class='col-lg-12'>"
//                    + "<a href='#' class='sh-pagetitle-icon'>"
//                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisMySteps.m_refresh();'></i>"
//                    + "</div>"
//                    + "</a>"
//                    + "</div>"
//                    + "");
            html.append("<div class=\"table-wrapper\">");
            html.append("<table id='refreshMySteps' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='30%'>عنوان</th>");
            html.append("<th width='30%'> مسئول اجرا</th>");
            html.append("<th width='30%'>مسئول پیگیری</th>");
            html.append("<th width='30%'>وضعیت</th>");
            html.append("<th width='30%'>وضعیت گام</th>");
            html.append("<th width='20%'>تاریخ شروع</th>");
            html.append("<th width='20%'>تاریخ پایان</th>");
            html.append("<th width='20%'>هزینه</th>");
            html.append("<th width='5%'>پایش</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(_isActive).equals("1")) {

                    html.append("<tr  class='mousePointer " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'  onclick='new jj('وضعیت نهایی شده است').jjModal('پیام سامانه');'>");
//                    html.append("<tr  class='mousePointer'  onclick='alert('وضعیت نهایی شده است');'>");
                } else {
                    html.append("<tr  class='mousePointer " + Sessions.getClassCssForVaziat(row.get(i).get(_status).toString()) + "'  onclick='hmisMySteps.m_select(" + row.get(i).get(_id) + ")'>");
                }
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_executorRoleId).toString(), db) + Access_User.getUserName(row.get(i).get(_executorUserId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName((row.get(i).get(_trackerId).toString()), db) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                String isActive = row.get(i).get(_isActive).equals("0") ? "غیر نهایی" : "نهایی";
                html.append("<td class='r'>" + isActive + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate).toString()) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate).toString()) + "</td>");
                html.append("<td class='r'>" + jjNumber.getFormattedNumber(row.get(i).get(_cost).toString()) + "</td>");
                html.append("<td class='c'><i class='icon ion-compose'></i></td>");

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
                panel = "swMyStepsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshMySteps", "300", 0, "", "گام ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            Map<String, Object> map = new HashMap<String, Object>();
            String script = "";
            String plansId = jjTools.getParameter(request, "hmis_plans_id");
            List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.otherSelect("SELECT COALESCE(SUM(steps_percent),0) sum "
                    + "FROM hmis_steps"
                    + " WHERE " + _plansId + "=" + plansId + ""));
            System.out.println("sum=" + StepsRow.get(0).get("sum").toString());
            System.out.println("idPlans=" + jjTools.getParameter(request, "hmis_plans_id"));

            float percent = 0;
            float sum = 0;
            if ((Float.parseFloat(StepsRow.get(0).get("sum").toString()) < 100 && Float.parseFloat(StepsRow.get(0).get("sum").toString()) >= 0)) {
                sum = Float.parseFloat(StepsRow.get(0).get("sum").toString());

                percent = Float.parseFloat(jjTools.getParameter(request, _percent)) + sum;//بدست آوزدن درصد
                if (percent <= 100 && percent >= 0) {
                    map.put(_percent, jjTools.getParameter(request, _percent));
                    map.put(_plansId, jjTools.getParameter(request, "hmis_plans_id"));
                    map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
                    map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
                    map.put(_cost, (jjTools.getParameter(request, _cost)));
//                    map.put(_isActive, jjTools.getParameter(request, _isActive));
                    map.put(_title, jjTools.getParameter(request, _title));
                    map.put(_otherIndicators, jjTools.getParameter(request, _otherIndicators));
                    map.put(_description, jjTools.getParameter(request, _description));
                    if (jjTools.getParameter(request, _executorRoleId).equals("null")) {
                        map.put(_executorUserId, jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ","));
                        map.put(_executorRoleId, "");
                    }
                    if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                        map.put(_executorUserId, "");
                        map.put(_executorRoleId, jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ","));
                    }
                    map.put(_trackerId, jjTools.getParameter(request, _trackerId));
                    map.put(_files, jjTools.getParameter(request, _files));
                    map.put(_status, status_initialRegistration);
                    map.put(_statusLog,
                            status_initialRegistration
                            + "-"
                            + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                            + "/ user_id="
                            + jjTools.getSeassionUserId(request) + " "
                            + "/ role_id="
                            + jjTools.getSeassionUserRole(request)
                            + " "
                            + new jjCalendar_IR().getTimeFormat_8length()
                            + "%23A%23"
                    );

                    if (db.insert(tableName, map).getRowCount() == 0) {
                        String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                        if (jjTools.isLangEn(request)) {
                            errorMessage = "Edit Fail;";
                        }
                        return Js.dialog(errorMessage);
                    }

                } else {
                    script += Js.modal("درصد گام صحیح نیست.", "پیام سامانه");
                }
            } else {
                if (Float.parseFloat(StepsRow.get(0).get("sum").toString()) >= 100) {
                    script += Js.modal("ثبت گام امکان نیست.", "پیام سامانه");
                }
            }

            script += "hmisPlans.m_select(" + plansId + ");";
            script += Js.jjPlans.refresh();
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * با زدن دکمه عملیات در جدول گامها اطلاعات سلکت می شوند .
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String selectStepsInPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String stepsId = jjTools.getParameter(request, Steps._id);
            String errorMessageId = jjValidation.isDigitMessageFa(stepsId, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(stepsId, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + stepsId));
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
            html.append(Js.setVal("#" + Steps.tableName + "_" + _id, row.get(0).get(Steps._id)));
            html.append(Js.setValDate("#" + Steps._endDate, row.get(0).get(Steps._endDate)));
            html.append(Js.setValDate("#" + Steps._startDate, row.get(0).get(Steps._startDate)));
            html.append(Js.setVal("#" + Steps._title, row.get(0).get(Steps._title)));
            html.append(Js.setVal("#" + Steps._cost, row.get(0).get(Steps._cost)));
            html.append(Js.setVal("#" + Steps._percent, row.get(0).get(Steps._percent)));
            html.append(Js.setVal("#" + Steps._otherIndicators, row.get(0).get(Steps._otherIndicators)));
            html.append(Js.setValSelectOption("#" + Steps._trackerId, row.get(0).get(Steps._trackerId).toString()));
            html.append(Js.select2("#" + Steps._trackerId, "width: '100%'"));

//            html.append(Js.setVal("#" + Steps._trackerId, row.get(0).get(Steps._trackerId)));
            html.append(Js.setVal("#" + Steps._files, row.get(0).get(Steps._files)));
            html.append(Js.setValSummerNote("#" + Steps._description, row.get(0).get(_description)));
            String attachFilesSteps = row.get(0).get(Steps._files).toString();
            String[] attachFilesStepsArray = attachFilesSteps.split(",");
            for (int l = 0; l < attachFilesStepsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            List<Map<String, Object>> planRow = jjDatabaseWeb.separateRow(db.Select(Plans.tableName, Plans._id + "=" + row.get(0).get(Steps._plansId)));
            if (planRow.get(0).get(Plans._status).equals(Plans.status_initialRegistration)) {
                if (Access_User.hasAccess(request, db, rul_edtStepInPlans)) {
                    html2.append("<div class='col-lg-6'> <button id='btn_editSteps' class=\"btn btn-outline-warning btn-block mg-b-10 tahoma10\" onclick=\"hmisPlans.editStepsInPlans(" + row.get(0).get(Steps._id) + ");\">ثبت تغییرات گام</button>\n</div>");
                }
                if (Access_User.hasAccess(request, db, rul_dltStepInPlans)) {

                    html2.append("<div class='col-lg-6'><button id='btn_deleteSteps' class=\"tahoma10 btn btn-outline-danger btn-block mg-b-10\" onclick=\"hmisSteps.m_delete(" + row.get(0).get(Steps._id) + ");\">حذف گام</button>\n</div>");

                }
            }

            String script = "";
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
                html.append("$('#steps_executorRoleId').val([" + temp + "]);"
                        + "$('#steps_executorRoleId').select2({  width: '100%'});"
                        + "$('#steps_executorUserId').val('');"
                        + "hmisPlans.executorAction('سمت');"
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
                html.append("$('#steps_executorUserId').val([" + temp2 + "]);"
                        + "$('#steps_executorUserId').select2({ width: '100%'});"
                        + "$('#steps_executorRoleId').val('');"
                        + "hmisPlans.executorAction('کاربران');"
                        + "$('input:radio[id=userExecutor]').attr('checked','checked');"
                );

            }

            script += Js.setHtml("#steps_button", html2);
            script += Js.setHtml("#downloadStepsFileDiv", html3);
            script += html.toString();

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * در پایش برنامه گامها
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectStepsInPlansForAssess(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String stepsId = jjTools.getParameter(request, Steps._id);
            String errorMessageId = jjValidation.isDigitMessageFa(stepsId, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(stepsId, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + stepsId));
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
            html.append(Js.setVal("#hmis_stepsInAssess_id", row.get(0).get(Steps._id)));
            html.append(Js.setValDate("#stepsInAssess_endDate", row.get(0).get(Steps._endDate)));
            html.append(Js.setValDate("#stepsInAssess_startDate", row.get(0).get(Steps._startDate)));
            html.append(Js.setVal("#stepsInAssess_title", row.get(0).get(Steps._title)));
            html.append(Js.setVal("#stepsInAssess_cost", row.get(0).get(Steps._cost)));
            html.append(Js.setVal("#stepsInAssess_percent", row.get(0).get(Steps._percent)));
            html.append(Js.setVal("#stepsInAssess_isActive", row.get(0).get(Steps._isActive)));
            html.append(Js.setHtml("#endDateStepsInAssess", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._endDate))));
            html.append(Js.setHtml("#trackerStepsInAssess", Role.getRoleName(row.get(0).get(Steps._trackerId).toString(), db)));

            html.append(Js.setHtml("#startDateStepsInAssess", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._startDate))));
            html.append(Js.setHtml("#titleStepsInAssess", row.get(0).get(Steps._title)));
            html.append(Js.setHtml("#otherIndicatorsStepsInAssess", row.get(0).get(Steps._otherIndicators)));
            if (row.get(0).get(Steps._executorRoleId).equals("")) {
                html.append(Js.setHtml("#stepsInAssess_executor", Access_User.getUserName(row.get(0).get(Steps._executorUserId).toString(), db)));
                html.append(Js.setHtml("#executorStepsInAssess", Access_User.getUserName(row.get(0).get(Steps._executorUserId).toString(), db)));
            }
            if (row.get(0).get(Steps._executorUserId).equals("")) {
                html.append(Js.setHtml("#executorStepsInAssess", Role.getRoleName(row.get(0).get(Steps._executorRoleId).toString(), db)));
                html.append(Js.setHtml("#stepsInAssess_executor", Role.getRoleName(row.get(0).get(Steps._executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#stepsInAssess_otherIndicators", row.get(0).get(Steps._otherIndicators)));

            html.append(Js.setHtml("#stepsInAssess_trackerId", Role.getRoleName(row.get(0).get(Steps._trackerId).toString(), db)));
            html.append(Js.setVal("#stepsInAssess_status", row.get(0).get(Steps._status)));
            html.append(Js.setVal("#stepsInAssess_files", row.get(0).get(Steps._files)));
            html.append(Js.setVal("#stepsInAssess_isActive", row.get(0).get(_isActive)));
            html.append(Js.setValSummerNote("#stepsInAssess_description", row.get(0).get(_description)));
            html.append(Js.setVal("#stepsInAssess_progressPercent", row.get(0).get(_progressPercent)));
            html.append(Js.setVal("#stepsInAssess_percent", row.get(0).get(_percent)));
            html.append(Js.setHtml("#percentTrackerInAssess", row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#percentExecutorInAssess", row.get(0).get(_percentExecutor)));
            html.append(Js.setHtml("#descriptionTrackerInAssess", row.get(0).get(_descriptionTracker)));
            html.append(Js.setHtml("#descriptionExecutorInAssess", row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionImproveQuality, row.get(0).get(_descriptionImproveQuality)));

            String attachFilesSteps = row.get(0).get(Steps._files).toString();
            String[] attachFilesStepsArray = attachFilesSteps.split(",");
            for (int l = 0; l < attachFilesStepsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            html3.append("<div class='row col-lg-12'>"
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
                        html3.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesExecutorArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesExecutor + "'   type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            ////////////////////////////////////////
            html3.append("<div class='row col-lg-12'>"
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
                        html3.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12 mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesTrackerArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _filesTracker + "'   type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            if (Access_User.hasAccess(request, db, rul_edtStepInPlansForAssess)) {
                html2.append("<div class='col-lg-6'> <button id='btn_editStepsInAssess' class=\"btn btn-outline-warning btn-block mg-b-10 tahoma10\" onclick=\"hmisSteps.editStepInPlansForAssess(" + row.get(0).get(Steps._id) + ");\">ثبت پایش</button>\n</div>");
            }
//            html2.append("<div class='col-lg-6'><button id='btn_deleteSteps' class=\"tahoma10 btn btn-outline-danger btn-block mg-b-10\" onclick=\"hmisSteps.m_delete(" + row.get(0).get(Steps._id) + ");\">حذف گام</button>\n</div>");

            String script = "";
            script += Js.setHtml("#stepsInAssess_button", html2);
            script += Js.setHtml("#downloadStepsInAssessFileDiv", html3);
            script += html.toString();

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش گام ها در قسمت برنامه عملیاتی من
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectStepsInMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String stepsId = jjTools.getParameter(request, "hmis_steps_id");
            String plansId = jjTools.getParameter(request, "hmis_plans_id");
            String errorMessageId = jjValidation.isDigitMessageFa(stepsId, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(stepsId, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + stepsId));
            List<Map<String, Object>> PlansRow = jjDatabase.separateRow(db.Select(Plans.tableName, Plans._id + "=" + plansId));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();

            html.append(Js.setVal("#hmis_stepsMyPlans_id", row.get(0).get(Steps._id)));
            html.append(Js.setVal("#MyPlansSteps_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._endDate))));
            html.append(Js.setVal("#MyPlansSteps_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._startDate))));
            html.append(Js.setVal("#MyPlansSteps_title", row.get(0).get(Steps._title)));
            html.append(Js.setVal("#MyPlansSteps_cost", row.get(0).get(Steps._cost)));
            html.append(Js.setVal("#MyPlansSteps_percent", row.get(0).get(Steps._percent)));
            html.append(Js.setVal("#MyPlansSteps_otherIndicators", row.get(0).get(Steps._otherIndicators)));
//            html.append(Js.setVal("#MyPlansSteps_trackerId", row.get(0).get(Steps._trackerId)));
            html.append(Js.setValSelectOption("#MyPlansSteps_trackerId", row.get(0).get(Steps._trackerId).toString()));
            html.append(Js.select2("#MyPlansSteps_trackerId", " width: '100%'"));

            html.append(Js.setVal("#MyPlansSteps_files", row.get(0).get(Steps._files)));
            html.append(Js.setHtml("#MyPlansSteps_status", row.get(0).get(Steps._status)));
            html.append(Js.setVal("#MyPlansSteps_isActive", row.get(0).get(_isActive)));
            html.append(Js.setValSummerNote("#MyPlansSteps_description", row.get(0).get(Steps._description)));
            String attachFilesSteps = row.get(0).get(Steps._files).toString();
            String[] attachFilesStepsArray = attachFilesSteps.split(",");
            for (int l = 0; l < attachFilesStepsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
//            if (PlansRow.get(0).get(Plans._status).equals(Plans.status_confirmationFinal)) {//تازمانی که وضعیت برنامه تایید نهایی است میتوان تغییر ات را روی گام ها انجام داد
            if (Access_User.hasAccess(request, db, rul_dltStepInMyPlans)) {
                html2.append("<div class='col-lg-6'><button id='btn_deleteMyPlansSteps' class=\"btn btn-outline-danger btn-block mg-b-10\" onclick=\"hmisMyPlans.deleteStepsMyPlans(" + row.get(0).get(Steps._id) + ");\">حذف گام</button></div>\n");
            }
            if (Access_User.hasAccess(request, db, rul_edtStepInMyPlans)) {
                html2.append("<div class='col-lg-6'><button id='btn_editMyPlansSteps' class=\"btn btn-outline-warning btn-block mg-b-10 tahoma10\" onclick=\"hmisMyPlans.editStepsMyPlans(" + row.get(0).get(Steps._id) + ");\">ثبت تغییرات گام</button></div>\n");
            }
//            }

            String script = "";
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
                html3.append(""
                        + "$('#MyPlansSteps_executorRoleId').val([" + temp + "]);"
                        + "$('#MyPlansSteps_executorRoleId').select2({ width: '100%'});"
                        + "$('#MyPlansSteps_executorUserId').val('');"
                        + "$('#MyPlansSteps_executorUserId').select2({ width: '100%'});"
                        + "hmisMyPlans.executorActionMyPlans('سمت');"
                        + "$('input:radio[id=ExecutorRole]').attr('checked','checked');"
                        + "$('input:radio[id=ExecutorUser]').removeAttr('checked');"
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
                html3.append(""
                        + "$('#MyPlansSteps_executorUserId').val([" + temp2 + "]);"
                        + "$('#MyPlansSteps_executorUserId').select2({width: '100%'});"
                        + "$('#MyPlansSteps_executorRoleId').val('');"
                        + "$('#MyPlansSteps_executorRoleId').select2({width: '100%'});"
                        + "hmisMyPlans.executorActionMyPlans('کاربران');"
                        + "$('input:radio[id=ExecutorUser]').attr('checked','checked');"
                        + "$('input:radio[id=ExecutorRole]').removeAttr('checked');"
                );
//
            }
            script += Js.setHtml("#MyPlansSteps_button", html2);
            script += Js.setHtml("#downloadFileMyPlansDiv", html4);
            script += html.toString();
            script += html3.toString();

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectStepsInManagerPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String stepsId = jjTools.getParameter(request, "stepsIdInManager");
            String plansId = jjTools.getParameter(request, "hmis_Managerplans_id");
            String errorMessageId = jjValidation.isDigitMessageFa(stepsId, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(stepsId, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + stepsId));
            List<Map<String, Object>> PlansRow = jjDatabase.separateRow(db.Select(Plans.tableName, Plans._id + "=" + plansId));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();

            html.append(Js.setVal("#stepsIdInManager", row.get(0).get(Steps._id)));
            html.append(Js.setVal("#ManagerPlansSteps_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._endDate))));
            html.append(Js.setVal("#ManagerPlansSteps_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(Steps._startDate))));
            html.append(Js.setVal("#ManagerPlansSteps_title", row.get(0).get(Steps._title)));
            html.append(Js.setVal("#ManagerPlansSteps_cost", row.get(0).get(Steps._cost)));
            html.append(Js.setVal("#ManagerPlansSteps_percent", row.get(0).get(Steps._percent)));
            html.append(Js.setVal("#ManagerPlansSteps_otherIndicators", row.get(0).get(Steps._otherIndicators)));
//            html.append(Js.setVal("#ManagerPlansSteps_trackerId", row.get(0).get(Steps._trackerId)));
            html.append(Js.setValSelectOption("#ManagerPlansSteps_trackerId", row.get(0).get(Steps._trackerId).toString()));
            html.append(Js.select2("#ManagerPlansSteps_trackerId", " width: '100%'"));

            html.append(Js.setVal("#ManagerPlansSteps_files", row.get(0).get(Steps._files)));
            html.append(Js.setValSummerNote("#ManagerPlansSteps_description", row.get(0).get(Steps._description)));
            html.append(Js.setVal("#ManagerPlansSteps_isActive", row.get(0).get(_isActive)));
            html.append(Js.setHtml("#ManagerPlansSteps_status", row.get(0).get(_status)));
//            if (PlansRow.get(0).get(Plans._status).equals(Plans.status_confirmByManager)) {
            if (Access_User.hasAccess(request, db, rul_edtStepByManager)) {
                html2.append("<button id='btn_editManagerPlansSteps' class=\"btn btn-outline-warning btn-block mg-b-10 tahoma10\" onclick=\"hmisPlans.editStepsInManager(" + row.get(0).get(Steps._id) + ");\">ثبت تغییرات گام</button>\n");
            }
            if (Access_User.hasAccess(request, db, rul_dltStepByManager)) {
                html2.append("<button id='btn_deleteManagerPlansSteps' class=\"btn btn-outline-warning btn-block mg-b-10 tahoma10\" onclick=\"hmisPlans.deleteStepsInManager(" + row.get(0).get(Steps._id) + ");\">حذف گام</button>\n");
            }
//            }
            String attachFilesSteps = row.get(0).get(Steps._files).toString();
            String[] attachFilesStepsArray = attachFilesSteps.split(",");
            for (int l = 0; l < attachFilesStepsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + Steps._files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String script = "";
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
                html3.append(""
                        + "$('#ManagerPlansSteps_executorRoleId').val([" + temp + "]);"
                        + "$('#ManagerPlansSteps_executorRoleId').select2({ width: '100%'});"
                        + "$('#ManagerPlansSteps_executorUserId').val('');"
                        + "$('#ManagerPlansSteps_executorUserId').select2({ width: '100%'});"
                        + "hmisManagerPlans.executorActionManagerPlans('سمت');"
                        + "$('input:radio[id=ExecutorRoleInManager]').attr('checked','checked');"
                        + "$('input:radio[id=ExecutorUserInManager]').removeAttr('checked');"
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
                html3.append(""
                        + "$('#ManagerPlansSteps_executorUserId').val([" + temp2 + "]);"
                        + "$('#ManagerPlansSteps_executorUserId').select2({width: '100%'});"
                        + "$('#ManagerPlansSteps_executorRoleId').val('');"
                        + "$('#ManagerPlansSteps_executorRoleId').select2({width: '100%'});"
                        + "hmisManagerPlans.executorActionManagerPlans('کاربران');"
                        + "$('input:radio[id=ExecutorUserInManager]').attr('checked','checked');"
                        + "$('input:radio[id=ExecutorRoleInManager]').removeAttr('checked');"
                );
//
            }
            script += Js.setHtml("#ManagerPlansSteps_button", html2);
            script += html.toString();
            script += html3.toString();
            script += Js.setHtml("#downloadFilesStepsInManager", html4.toString());

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String script = "";
            System.out.println("steps_id=" + jjTools.getParameter(request, "hmis_steps_id"));
            Map<String, Object> map = new HashMap<String, Object>();

            map.put(Steps._status, (jjTools.getParameter(request, Steps._status)));
            map.put(Steps._filesExecutor, jjTools.getParameter(request, Steps._filesExecutor));
            map.put(Steps._filesTracker, jjTools.getParameter(request, Steps._filesTracker));
            map.put(Steps._percentExecutor, jjTools.getParameter(request, Steps._percentExecutor));
            map.put(Steps._percentTracker, jjTools.getParameter(request, Steps._percentTracker));
            map.put(Steps._descriptionExecutor, jjTools.getParameter(request, Steps._descriptionExecutor));
            map.put(Steps._descriptionTracker, jjTools.getParameter(request, Steps._descriptionTracker));
            map.put(_statusLog,
                    jjTools.getParameter(request, Steps._status)
                    + "-"
                    + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                    + "/ user_id="
                    + jjTools.getSeassionUserId(request) + " "
                    + "/ role_id="
                    + jjTools.getSeassionUserRole(request)
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23");
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
            if (!db.update(Steps.tableName, map, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id"))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            } else {

                List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id")));
                script += "hmisSteps.m_select(" + jjTools.getParameter(request, "hmis_steps_id") + ");";
                script += "hmisSteps.m_refresh();";
                script += Js.modal("پایش انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش برنامه های گام های اجرایی من
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String editMySteps(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String script = "";
            String steps_id = jjTools.getParameter(request, _id);

            System.out.println("steps_id=" + jjTools.getParameter(request, _id));
            Map<String, Object> map = new HashMap<String, Object>();
//            System.out.println("jjTools.getParameter(request, Steps._progressPercent)=" + jjTools.getParameter(request, _progressPercent));

//            map.put(Steps._isActive, jjTools.getParameter(request, _isActive));
            map.put(Steps._status, jjTools.getParameter(request, _status));
            map.put(Steps._percentExecutor, jjTools.getParameter(request, _percentExecutor));
            map.put(Steps._percentTracker, jjTools.getParameter(request, _percentTracker));
            map.put(Steps._filesExecutor, jjTools.getParameter(request, _filesExecutor));
            map.put(Steps._filesTracker, jjTools.getParameter(request, _filesTracker));
            map.put(Steps._descriptionExecutor, (jjTools.getParameter(request, _descriptionExecutor)));
            map.put(Steps._descriptionTracker, (jjTools.getParameter(request, _descriptionTracker)));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Steps.changeStatus(request, db, steps_id, jjTools.getParameter(request, _status));
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            } else {

                script += "hmisMySteps.m_select(" + jjTools.getParameter(request, _id) + ");";
                script += "hmisMySteps.m_refresh();";
                script += Js.modal("پایش انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش گام در پایش برنامه ها
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editStepInPlansForAssess(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "hmis_steps_id");
            String planId = jjTools.getParameter(request, "hmis_plans_id");

            if (!Access_User.hasAccess(request, db, rul_edtStepInPlansForAssess)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            List<Map<String, Object>> stepsRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_files, jjTools.getParameter(request, _files));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_cost, jjTools.getParameter(request, _cost));
            map.put(_percent, jjTools.getParameter(request, _percent));
            map.put(_progressPercent, jjTools.getParameter(request, _progressPercent));
            map.put(_otherIndicators, jjTools.getParameter(request, _otherIndicators));
            map.put(_descriptionImproveQuality, jjTools.getParameter(request, _descriptionImproveQuality));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            System.out.println("planId=" + planId);
            String script = "hmisPlansForAssess.m_select(" + planId + ");";
            script += Js.modal("پایش انجام شد", "پیام سامانه");
            script += "$('#stepsFormInAssess').slideUp();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ب ویرایش اطلاعات گام ها در قسمت برنامه های عملیاتی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String editStepsInPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edtStepInPlans)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String script = "";
            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id")));
            System.out.println("steps_id=" + jjTools.getParameter(request, "hmis_steps_id"));
            Map<String, Object> map = new HashMap<String, Object>();
            int plansId = Integer.valueOf(Row.get(0).get(Steps._plansId).toString());
            List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.otherSelect("SELECT COALESCE(SUM(steps_percent),0) sum "
                    + "FROM hmis_steps"
                    + " WHERE " + _plansId + "=" + plansId + ""));
            System.out.println("sum=" + StepsRow.get(0).get("sum").toString());
            System.out.println("plansId=" + plansId);

            float percent = 0;
            float sum = 0;
            sum = Float.parseFloat(StepsRow.get(0).get("sum").toString());
            percent = (sum - Float.parseFloat(Row.get(0).get(Steps._percent).toString())) + Float.parseFloat(jjTools.getParameter(request, _percent));//بدست آوزدن درصد
            if ((percent <= 100 && percent >= 0)) {
//                if (percent <= 100 && percent >= 0) {
                map.put(_percent, Float.parseFloat(jjTools.getParameter(request, _percent)));
//                map.put(_isActive, jjTools.getParameter(request, _isActive));
                map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
                map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
                map.put(_cost, (jjTools.getParameter(request, _cost)));
                map.put(_title, jjTools.getParameter(request, _title));
                map.put(_otherIndicators, jjTools.getParameter(request, _otherIndicators));
                map.put(_description, jjTools.getParameter(request, _description));
                if (jjTools.getParameter(request, _executorRoleId).equals("null")) {
                    map.put(_executorRoleId, "");
                    map.put(_executorUserId, jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ","));
                }

                if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                    map.put(_executorUserId, "");
                    map.put(_executorRoleId, jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ","));
                }
                map.put(_trackerId, jjTools.getParameter(request, _trackerId));
                map.put(_files, jjTools.getParameter(request, _files));

                if (!db.update(Steps.tableName, map, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id"))) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                }

//                } else {
//                    script += Js.modal("درصد گام صحیح نیست.", "پیام سامانه");
//                }
            } else {

                script += Js.modal("درصد گام صحیح نیست", "پیام سامانه");

            }

            script += "hmisPlans.m_select(" + plansId + ");";
            script += Js.jjPlans.refresh();
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editStepsInManager(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edtStepByManager)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String script = "";
            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id")));
            System.out.println("steps_id=" + jjTools.getParameter(request, "hmis_steps_id"));
            Map<String, Object> map = new HashMap<String, Object>();
            int plansId = Integer.valueOf(Row.get(0).get(Steps._plansId).toString());
            List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.otherSelect("SELECT COALESCE(SUM(steps_percent),0) sum "
                    + "FROM hmis_steps"
                    + " WHERE " + _plansId + "=" + plansId + ""));
            System.out.println("sum=" + StepsRow.get(0).get("sum").toString());
            System.out.println("plansId=" + plansId);

            float percent = 0;
            float sum = 0;
            sum = Float.parseFloat(StepsRow.get(0).get("sum").toString());
            percent = (sum - Float.parseFloat(Row.get(0).get(Steps._percent).toString())) + Float.parseFloat(jjTools.getParameter(request, _percent));//بدست آوزدن درصد
            if ((percent <= 100 && percent >= 0)) {
//                if (percent <= 100 && percent >= 0) {
                map.put(_percent, Float.parseFloat(jjTools.getParameter(request, _percent)));
//                map.put(_isActive, jjTools.getParameter(request, _isActive));
                map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
                map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
                map.put(_cost, (jjTools.getParameter(request, _cost)));
                map.put(_title, jjTools.getParameter(request, _title));
                map.put(_otherIndicators, jjTools.getParameter(request, _otherIndicators));
                map.put(_description, jjTools.getParameter(request, _description));
                if (jjTools.getParameter(request, _executorRoleId).equals("null")) {
                    map.put(_executorRoleId, "");
                    map.put(_executorUserId, jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ","));
                }

                if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                    map.put(_executorUserId, "");
                    map.put(_executorRoleId, jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ","));
                }
                map.put(_trackerId, jjTools.getParameter(request, _trackerId));
                map.put(_files, jjTools.getParameter(request, _files));

                if (!db.update(Steps.tableName, map, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id"))) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                }

//                } else {
//                    script += Js.modal("درصد گام صحیح نیست.", "پیام سامانه");
//                }
            } else {

                script += Js.modal("درصد گام صحیح نیست", "پیام سامانه");

            }

            script += "hmisManagerPlans.m_select(" + plansId + ");";
//            script += Js.jjPlans.refresh();
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editStepsMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edtStepInMyPlans)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String script = "";
            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id")));
            System.out.println("steps_id=" + jjTools.getParameter(request, "hmis_steps_id"));
            Map<String, Object> map = new HashMap<String, Object>();
            int plansId = Integer.valueOf(Row.get(0).get(Steps._plansId).toString());
            List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.otherSelect("SELECT COALESCE(SUM(steps_percent),0) sum "
                    + "FROM hmis_steps"
                    + " WHERE " + _plansId + "=" + plansId + ""));
            System.out.println("sum=" + StepsRow.get(0).get("sum").toString());
            System.out.println("plansId=" + plansId);

            float percent = 0;
            float sum = 0;
            sum = Float.parseFloat(StepsRow.get(0).get("sum").toString());
            percent = (sum - Float.parseFloat(Row.get(0).get(Steps._percent).toString())) + Float.parseFloat(jjTools.getParameter(request, _percent));//بدست آوزدن درصد
            if ((percent <= 100 && percent >= 0)) {
//                if (percent <= 100 && percent >= 0) {

                map.put(_percent, Float.parseFloat(jjTools.getParameter(request, _percent)));
                map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
                map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
                map.put(_cost, (jjTools.getParameter(request, _cost)));
                map.put(_title, jjTools.getParameter(request, _title));
                map.put(_otherIndicators, jjTools.getParameter(request, _otherIndicators));
                map.put(_description, jjTools.getParameter(request, _description));
                if (jjTools.getParameter(request, _executorRoleId).equals("null")) {
                    map.put(_executorRoleId, "");
                    map.put(_executorUserId, jjTools.getParameter(request, _executorUserId).replaceAll("#A#", ","));
                }

                if (jjTools.getParameter(request, _executorUserId).equals("null")) {
                    map.put(_executorUserId, "");
                    map.put(_executorRoleId, jjTools.getParameter(request, _executorRoleId).replaceAll("#A#", ","));
                }
                map.put(_trackerId, jjTools.getParameter(request, _trackerId));
                map.put(_files, jjTools.getParameter(request, _files));

                if (!db.update(Steps.tableName, map, Steps._id + "=" + jjTools.getParameter(request, "hmis_steps_id"))) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                }

            } else {

                script += Js.modal("درصد گام صحیح نیست", "پیام سامانه");

            }

            script += "hmisMyPlans.m_select(" + plansId + ");";
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                //                html.append("<button onclick='hmisSteps.m_insert();' id=\"insert_Plans_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">ثبت گام</button>");
                html.append("  <div class='col-lg-12'>");
                html.append(" <button id='btn_insertSteps' class='btn btn-outline-success  btn-block mg-b-10' onclick='hmisSteps.m_insert();'>ثبت گام</button>");
                html.append("  </div>");
            }
            String script = Js.setHtml("#steps_button", html);

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ گام ها توسط مدیر
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String communicatedSteps(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = "";
            StringBuilder html = new StringBuilder();
            String ExeRId = "";
            String id = jjTools.getParameter(request, _id);
            boolean flag = true;
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> plansRow = jjDatabase.separateRow(db.Select(Plans.tableName, Plans._id + "=" + row.get(0).get(_plansId)));
            String ExecutorRoleId = row.get(0).get(_executorRoleId).toString();
            String ExecutorUserId = row.get(0).get(_executorUserId).toString();
            Map<String, Object> map = new HashMap();
            if (!ExecutorRoleId.equals("")) {
                String[] ExeRoleId = ExecutorRoleId.split(",");
                for (int i = 0; i < ExeRoleId.length; i++) {
                    if (jjNumber.isDigit(ExeRoleId[i])) {
                        map.put(_title, row.get(0).get(_title));
                        map.put(_endDate, row.get(0).get(_endDate).toString().replaceAll("/", ""));
                        map.put(_startDate, row.get(0).get(_startDate).toString().replaceAll("/", ""));
                        map.put(_executorRoleId, ExeRoleId[i]);
                        map.put(_trackerId, row.get(0).get(_trackerId));
                        map.put(_cost, row.get(0).get(_cost));
                        map.put(_plansId, row.get(0).get(_plansId));
                        map.put(_percent, row.get(0).get(_percent));
                        map.put(_otherIndicators, row.get(0).get(_otherIndicators));
                        map.put(_description, row.get(0).get(_description));
                        map.put(_files, row.get(0).get(_files));
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
                                + "%23A%23"
                        );
                        if (db.insert(tableName, map).getRowCount() == 0) {
                            flag = false;
                        } else {
                            String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[i], db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            } else {
                                text = "شما به عنوان مسئول اجرا انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[i], db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند

                    String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        text = tice_configMessage;
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else {
                        text = "شما به عنوان مسئول پیگیری انتخاب شده اید";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }
                }
            } else if (!ExecutorUserId.equals("")) {
                String[] ExeUserId = ExecutorUserId.split(",");
                for (int i = 0; i < ExeUserId.length; i++) {
                    String reciverId = "";
                    if (jjNumber.isDigit(ExeUserId[i])) {
                        map.put(_title, row.get(0).get(_title));
                        map.put(_endDate, row.get(0).get(_endDate).toString().replaceAll("/", ""));
                        map.put(_startDate, row.get(0).get(_startDate).toString().replaceAll("/", ""));
                        map.put(_executorUserId, ExeUserId[i]);
                        map.put(_trackerId, row.get(0).get(_trackerId));
                        map.put(_description, row.get(0).get(_description));
                        map.put(_cost, row.get(0).get(_cost));
                        map.put(_percent, row.get(0).get(_percent));
                        map.put(_plansId, row.get(0).get(_plansId));
                        map.put(_otherIndicators, row.get(0).get(_otherIndicators));
                        map.put(_files, row.get(0).get(_files));
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
                                + "%23A%23"
                        );
                        if (db.insert(tableName, map).getRowCount() == 0) {
                            flag = false;
                        } else {
                            String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, ExeUserId[i], "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title) + "شما به عنوان مسئول اجرا انتخاب شده اید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            } else {
                                text = "شما به عنوان مسئول اجرا انتخاب شده اید";
                                Messenger.sendMesseage(null, db, ExeUserId[i], "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title) + "شما به عنوان مسئول اجرا انتخاب شده اید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            }
                        }
                    }
                }
                if (flag == true) {
                    db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                    String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        text = tice_configMessage;
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title) + "شما به عنوان مسئول پیگیری انتخاب شده اید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                    } else {
                        text = "شما به عنوان مسئول پیگیری انتخاب شده اید";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(0).get(_title) + "شما به عنوان مسئول پیگیری انتخاب شده اید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                    }
                }
            }
            System.out.println("id=" + id);
            Server.outPrinter(request, response, "hmisManagerPlans.m_select(" + row.get(0).get(_plansId) + ");");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    //     / * ابلاغ همه گام ها یک برنامه
    //     *
    //     * @param request
    //     * @param response
    //     * @param db
    //     * @param isPost
    //     * @return
    //     * @throws Exception
    //     */

    public static String communicateStepsAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, Plans.rul_communicatedStep)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String text = "";
            StringBuilder html = new StringBuilder();
            String script = "";
            String ExeRId = "";
            String plansId = jjTools.getParameter(request, "hmis_plans_id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _plansId + "=" + plansId + " AND steps_status='" + status_initialRegistration + "'"));
            List<Map<String, Object>> plansRow = jjDatabase.separateRow(db.Select(Plans.tableName, Plans._id + "=" + plansId + ""));
            if (row.size() > 0) {

                for (int i = 0; i < row.size(); i++) {
                    String id = row.get(i).get(_id).toString();
                    String ExecutorRoleId = row.get(i).get(_executorRoleId).toString();
                    String ExecutorUserId = row.get(i).get(_executorUserId).toString();
                    boolean flag = true;
                    if (!ExecutorRoleId.equals("")) {
                        String[] ExeRoleId = ExecutorRoleId.split(",");
                        Map<String, Object> map = new HashMap();
                        for (int j = 0; j < ExeRoleId.length; j++) {
                            if (jjNumber.isDigit(ExeRoleId[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_executorRoleId, ExeRoleId[j]);
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_cost, row.get(i).get(_cost));
                                map.put(_plansId, row.get(i).get(_plansId));
                                map.put(_percent, row.get(i).get(_percent));
                                map.put(_otherIndicators, row.get(i).get(_otherIndicators));
                                map.put(_description, row.get(i).get(_description));
                                map.put(_files, row.get(i).get(_files));
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
                                        + "%23A%23"
                                );
                                if (db.insert(tableName, map).getRowCount() == 0) {
                                    flag = false;
                                } else {
                                    String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                        text = tice_configMessage;
                                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[j], db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                                    } else {
                                        text = "شما به عنوان مسئول اجرا انتخاب شده اید";
                                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(ExeRoleId[j], db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);// بعد از جداکردن رکورد سابق را پاک میکند
                            String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            } else {
                                text = "شما به عنوان مسئول پیگیری انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            }
                        }
                    } else if (!ExecutorUserId.equals("")) {
                        Map<String, Object> map = new HashMap();
                        String[] ExeUserId = ExecutorUserId.split(",");
                        for (int j = 0; j < ExeUserId.length; j++) {
                            if (jjNumber.isDigit(ExeUserId[j])) {
                                map.put(_title, row.get(i).get(_title));
                                map.put(_endDate, row.get(i).get(_endDate).toString().replaceAll("/", ""));
                                map.put(_startDate, row.get(i).get(_startDate).toString().replaceAll("/", ""));
                                map.put(_executorUserId, ExeUserId[j]);
                                map.put(_trackerId, row.get(i).get(_trackerId));
                                map.put(_cost, row.get(i).get(_cost));
                                map.put(_percent, row.get(i).get(_percent));
                                map.put(_plansId, row.get(i).get(_plansId));
                                map.put(_otherIndicators, row.get(i).get(_otherIndicators));
                                map.put(_files, row.get(i).get(_files));
                                map.put(_description, row.get(i).get(_description));
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
                                        + "%23A%23"
                                );
                                if (db.insert(tableName, map).getRowCount() == 0) {
                                    flag = false;
                                } else {
                                    String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                        text = tice_configMessage;
                                        Messenger.sendMesseage(null, db, ExeUserId[j], "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                                    } else {
                                        text = "شما به عنوان مسئول اجرا انتخاب شده اید";
                                        Messenger.sendMesseage(null, db, ExeUserId[j], "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                                    }
                                }
                            }
                        }
                        if (flag == true) {
                            db.delete(tableName, _id + "=" + id);
                            String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_communicateofPlanSteps_name);
                            if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                text = tice_configMessage;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            } else {
                                text = "شما به عنوان مسئول پیگیری انتخاب شده اید";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(i).get(_trackerId).toString(), db), "1", "sms,app,email", "", "عنوان برنامه عملیاتی:" + plansRow.get(0).get(Plans._title) + " عنوان گام:" + row.get(i).get(_title), text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            }
                        }

//
                    }
                }
                script += "hmisManagerPlans.m_select(" + plansId + ");";
            } else {
                script += Js.modal("دیگر امکان ابلاغ وجود ندارد", "پیام سامانه");
            }
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
     * اطلاعات گام در گام هابی اجرایی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
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

            /////////////////////////////////////////////////////
            String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
//            String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";//با داشتن الگوی regex
//            Pattern p1 = Pattern.compile(trackerId);
            Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)");

            String RolesId = jjTools.getSessionAttribute(request, "#ROLE_ID");
            Matcher m1 = p1.matcher(RolesId);   // get a matcher object

            if (m1.find()) {
                html.append(Js.removeAttr("#steps_descriptionTracker", "disabled"));
                html.append(Js.removeAttr("#steps_percentTracker", "disabled"));
                html.append("$('#trackerFileStepsDiv').show();");
                html5.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");

                String attachFilesTracker = row.get(0).get(Steps._filesTracker).toString();
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
                html.append(Js.setAttr("#steps_descriptionTracker", "disabled", "disabled"));
                html.append(Js.setAttr("#steps_percentTracker", "disabled", "disabled"));
                html.append("$('#trackerFileStepsDiv').hide();");

                html5.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");

////////////////////////////////////////////////////
                String attachFilesTracker = row.get(0).get(Steps._filesTracker).toString();
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
                                    //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

            }
            if (executorRoleId.equals(",")) {
                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "")) {
                    html.append(Js.removeAttr("#steps_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#steps_percentExecutor", "disabled"));//
                    html.append("$('#executorFileStepsDiv').show();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

/////////////////////////////////////////
                    String attachFilesSteps = row.get(0).get(Steps._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesSteps.split(",");
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
                    html.append(Js.setAttr("#steps_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#steps_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileStepsDiv').hide();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

///////////////////////////////////////////////////////
                    String attachFilesSteps = row.get(0).get(Steps._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesSteps.split(",");
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
                    html.append(Js.removeAttr("#steps_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#steps_percentExecutor", "disabled"));//
                    html.append("$('#executorFileStepsDiv').show();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

/////////////////////////////////////////
                    String attachFilesSteps = row.get(0).get(Steps._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesSteps.split(",");
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
                    html.append(Js.setAttr("#steps_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#steps_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileStepsDiv').hide();");

                    html4.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");

///////////////////////////////////////////////////////
                    String attachFilesSteps = row.get(0).get(Steps._filesExecutor).toString();
                    String[] attachFilesExecutorArray = attachFilesSteps.split(",");
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
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setVal("#" + _percent, row.get(0).get(_percent)));
            html.append(Js.setVal("#" + _percentExecutor, row.get(0).get(_percentExecutor)));
            html.append(Js.setVal("#" + _descriptionExecutor, row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#" + _descriptionTracker, row.get(0).get(_descriptionTracker)));
            html.append(Js.setVal("#" + _percentTracker, row.get(0).get(_percentTracker)));
            html.append(Js.setHtml("#" + _description, row.get(0).get(_description)));
            html.append(Js.setHtml("#trackerIdSteps", Role.getRoleUserName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setHtml("#executorRoleIdSteps", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setHtml("#executorRoleIdSteps", Role.getRoleUserName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#endDateSteps", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#startDateSteps", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));

            html3.append("<div class='col-lg-12'> "
                    + "فایل های گام"
                    + "</div>"
                    + "");
///////////////////////////////////////////////////////
            String attachFilesSteps = row.get(0).get(_files).toString();
            String[] attachFilesStepsArray = attachFilesSteps.split(",");
            for (int l = 0; l < attachFilesStepsArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12  mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12   mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
//            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_Steps' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjSteps.edit() + "' >ثبت پایش</button>");
                html2.append("</div>");
            }

            html2.append("</div>");
            script += "$('#showFilesExecutorDiv').html('');";
            script += "$('#showFilesTrackerDiv').html('');";
            script += Js.setHtml("#downloadFileExecutorDiv", html4);
            script += Js.setHtml("#downloadFileTrackerDiv", html5);
            script += Js.setHtml("#downloadFileStepsDiv", html3);

            script += Js.setHtml("#Steps_button", html2);
            script += html.toString();
//            script += Js.setHtml("#inputTextSelectorDiv", html3 + html4.toString() + html5.toString());
            Server.outPrinter(request, response, script);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//    
    /**
     * سلکت برای کسانی که یا مسئول پیگیری هستن یا مسئول اجرا در گام ها
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectMySteps(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
//            String trackerId = "*[1-3]";
//            String trackerId = "" + row.get(0).get(_trackerId).toString() + ",";
            Pattern p1 = Pattern.compile("(^" + row.get(0).get(_trackerId).toString() + ",)|(," + row.get(0).get(_trackerId).toString() + ",)");
            String RolesId = jjTools.getSessionAttribute(request, "#ROLE_ID");
            String executorRoleId = "" + (row.get(0).get(_executorRoleId).toString() + ",");//این علائم نشانه این است که فقط همان  کاراکتر وجود داشته باشد
            List<Map<String, Object>> RolesTrackerIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(0).get(_trackerId)));
            List<Map<String, Object>> UserTrackerIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesTrackerIdRow.get(0).get(Role._user_id)));
            Matcher m1 = p1.matcher(RolesId);   // get a matcher object

            if (m1.find()) {
                html.append(Js.removeAttr("#MySteps_descriptionTracker", "disabled"));
                html.append(Js.removeAttr("#MySteps_percentTracker", "disabled"));
                html.append("$('#trackerFileDiv').show();");
                html3.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");
                String attachFilesTracker = row.get(0).get(Steps._filesTracker).toString();
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
                            html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesTrackerArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesTrackerArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesTrackerArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html3.append("<div class='col-lg-12  media-body mg-l-15'>"
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
                html.append(Js.setAttr("#MySteps_descriptionTracker", "disabled", "disabled"));
                html.append(Js.setAttr("#MySteps_percentTracker", "disabled", "disabled"));
                html.append("$('#trackerFileDiv').hide();");
                html3.append("<div class='col-lg-12'> "
                        + "فایل های مسئول پیگیری"
                        + "</div>"
                        + "");
                String attachFilesMySteps = row.get(0).get(Steps._filesTracker).toString();
                String[] attachFilesMyStepsArray = attachFilesMySteps.split(",");
                for (int l = 0; l < attachFilesMyStepsArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesMyStepsArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesMyStepsArray[l].substring(attachFilesMyStepsArray[l].lastIndexOf(".") + 1, attachFilesMyStepsArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesMyStepsArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesMyStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'  type='hidden'  value='" + attachFilesMyStepsArray[l] + "'/>"
                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesMyStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _filesTracker + "'   type='hidden'  value='" + attachFilesMyStepsArray[l] + "'/>"
                                    //                                    + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

                ///////////////////////////////
            }
            if (executorRoleId.equals(",")) {

                if (row.get(0).get(_executorUserId).toString().equals("" + jjTools.getSeassionUserId(request) + "")) {
                    html.append(Js.removeAttr("#MySteps_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#MySteps_percentExecutor", "disabled"));//
                    html.append("$('#executorFileDiv').show();");
                    html3.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");
                    String attachFilesExecutor = row.get(0).get(Steps._filesExecutor).toString();
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
                                html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html3.append("<div class='col-lg-12  media-body mg-l-15'>"
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
                    html.append(Js.setAttr("#MySteps_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#MySteps_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileDiv').hide();");

                    html3.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");
                    String attachFilesExecutor = row.get(0).get(Steps._filesExecutor).toString();
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
                                html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html3.append("<div class='col-lg-12  media-body mg-l-15'>"
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
                    html.append(Js.removeAttr("#MySteps_descriptionExecutor", "disabled"));//
                    html.append(Js.removeAttr("#MySteps_percentExecutor", "disabled"));//
                    html.append("$('#executorFileDiv').show();");
                    html3.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");
                    String attachFilesExecutor = row.get(0).get(Steps._filesExecutor).toString();
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
                                html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html3.append("<div class='col-lg-12  media-body mg-l-15'>"
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
                    html.append(Js.setAttr("#MySteps_descriptionExecutor", "disabled", "disabled"));
                    html.append(Js.setAttr("#MySteps_percentExecutor", "disabled", "disabled"));
                    html.append("$('#executorFileDiv').hide();");

                    html3.append("<div class='col-lg-12'> "
                            + "فایل های مسئول اجرا"
                            + "</div>"
                            + "");
                    String attachFilesExecutor = row.get(0).get(Steps._filesExecutor).toString();
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
                                html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                        + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesExecutorArray[l] + "'/>"
                                        + "<a  href='upload/" + attachFilesExecutorArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                        + "<input class='" + _filesExecutor + "'  type='hidden'  value='" + attachFilesExecutorArray[l] + "'/>"
                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                        + "</div>"
                                );
                            } else {
                                html3.append("<div class='col-lg-12  media-body mg-l-15'>"
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
            }

            html.append(Js.setVal("#MySteps_trackerFiles", row.get(0).get(_filesTracker)));
            html.append(Js.setVal("#MySteps_executorFiles", row.get(0).get(_filesExecutor)));
            html.append(Js.setVal("#hmis_mySteps_id", row.get(0).get(_id)));
            html.append(Js.setVal("#MySteps_title", row.get(0).get(_title)));
            html.append(Js.setVal("#MySteps_status", row.get(0).get(_status)));
            System.out.println("_statusLog=" + row.get(0).get(_statusLog));
            html.append(Js.setHtml("#statusLogMySteps", row.get(0).get(_statusLog)));
            html.append(Js.setVal("#MySteps_percent", row.get(0).get(_percent)));
            html.append(Js.setVal("#MySteps_descriptionExecutor", row.get(0).get(_descriptionExecutor)));
            html.append(Js.setVal("#MySteps_descriptionTracker", row.get(0).get(_descriptionTracker)));
            html.append(Js.setHtml("#MySteps_description", row.get(0).get(_description)));
            html.append(Js.setVal("#MySteps_percentTracker", row.get(0).get(_percentTracker)));
            html.append(Js.setVal("#MySteps_percentExecutor", row.get(0).get(_percentExecutor)));
            html.append(Js.setHtml("#MySteps_descriptionImproveQuality", row.get(0).get(_descriptionImproveQuality)));
            html.append(Js.setVal("#MySteps_trackerId", Role.getRoleName(row.get(0).get(_trackerId).toString(), db)));
            if (row.get(0).get(_executorRoleId).equals("")) {
                html.append(Js.setVal("#MySteps_executorRoleId", Access_User.getUserName(row.get(0).get(_executorUserId).toString(), db)));
            } else if (row.get(0).get(_executorUserId).equals("")) {
                html.append(Js.setVal("#MySteps_executorRoleId", Role.getRoleName(row.get(0).get(_executorRoleId).toString(), db)));
            }
            html.append(Js.setVal("#MySteps_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#MySteps_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            html.append(Js.setVal("#MySteps_progressPercent", row.get(0).get(_progressPercent)));
            html.append(Js.setVal("#" + _files, row.get(0).get(_files)));
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
//            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_MySteps' class='btn btn-outline-warning btn-block mg-b-10 tahoma10' onclick='hmisMySteps.m_edit();' >ثبت پایش</button>");
                html2.append("</div>");
            }

            if (!row.get(0).get(Steps._files).toString().equals("")) {
                String[] File = (row.get(0).get(_files).toString().replaceAll("#A#", "%23A%23")).split("%23A%23");
                html3.append("<div class='col-lg-12'> "
                        + "فایل های گام"
                        + "</div>"
                        + "");
///////////////////////////////////////////////////////
                String attachFilesSteps = row.get(0).get(_files).toString();
                String[] attachFilesStepsArray = attachFilesSteps.split(",");
                for (int l = 0; l < attachFilesStepsArray.length; l++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesStepsArray[l] + "'"));
                    if (!fileRow.isEmpty()) {
                        String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                        String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                        String extension2 = attachFilesStepsArray[l].substring(attachFilesStepsArray[l].lastIndexOf(".") + 1, attachFilesStepsArray[l].length());
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")) {
                            html3.append("<div class='col-lg-12  mg-l-15'>"
                                    + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesStepsArray[l] + "'/>"
                                    + "<a  href='upload/" + attachFilesStepsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                    //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        } else {
                            html3.append("<div class='col-lg-12   mg-l-15'>"
                                    + "<i class='icon ion-ios-paper-outline'></i>"
                                    + "<a  href='upload/" + attachFilesStepsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                    + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesStepsArray[l] + "'/>"
                                    //                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                    + "</div>"
                            );
                        }
                    } else {
                        //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                    }
                }

            }

            html2.append("</div>");
            String script = Js.setHtml("#MySteps_button", html2);
            script += html.toString();
            script += "$('#showFilesExecutorDiv').html('');";//خالی کردن فایل ها 
            script += "$('#showFilesTrackerDiv').html('');";
            script += Js.setHtml("#FilesStepsDiv", html3);
//            script += Js.setHtml("#downloadTrackerFileMyStepsDiv", html5);
//            script += Js.setHtml("#downloadExecutorFileMyStepsDiv", html4);
            Server.outPrinter(request, response, script);

            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            if (!Access_User.hasAccess(request, db, rul_dlt)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            String plansId = jjTools.getParameter(request, "hmis_plans_id");

            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            System.out.println("plansId=" + plansId);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = Js.jjPlans.select(plansId);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String deleteStepsInManager(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dltStepByManager)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));

            System.out.println("id=" + id);
            String plansId = row.get(0).get(_plansId).toString();
            System.out.println("plansId=" + row.get(0).get(_plansId));
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "hmisManagerPlans.m_select(" + plansId + ");";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String deleteStepsMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dltStepInMyPlans)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));

            System.out.println("id=" + id);
            String plansId = row.get(0).get(_plansId).toString();
            System.out.println("plansId=" + plansId);

//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = "hmisMyPlans.m_select(" + plansId + ");";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
