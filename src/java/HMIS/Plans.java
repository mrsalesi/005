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
import java.util.Random;
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
 * @author shohreh.shiran Date 1397.10.25
 */
public class Plans {

    public static String tableName = "hmis_plans";
    public static String _id = "id";
    public static String _typeOfProgram = "plans_typeOfProgram";//نوع برنامه عملیاتی
    public static String _creatorId = "plans_creatorId";//ایجاد کننده برنامه عملیاتی
    public static String _improveQualityId = "plans_improveQualityId";//مسئول بهبود کیفیت
    public static String _supervisorRoleId = "plans_supervisorRolId";//سمت مافوق
    public static String _title = "plans_title";//عنوان برنامه عملیاتی
    public static String _minorGoal = "plans_minorGoal";//هدف جزئی
    public static String _hugeGoal = "plans_hugeGoal";//هدف کلان
    public static String _managerRoleId = "plans_managerRoleId";// ای دی مدیر
    public static String _strategic = "plans_strategic";//استراتژیک
    public static String _range = "plans_range";//حیطه
    public static String _causeProblem = "plans_causeProblem";//علت مشکل
    public static String _method = "plans_method";//شیوه شناسایی مشکل
    public static String _titleOfTheProblem = "plans_titleOfTheProblem";//عنوان مشکل
    public static String _period = "plans_period";//دوره پایش
    public static String _domain = "plans_domain";//دامنه
    public static String _department = "plans_department";//بخش
    public static String _status = "plans_status";//وضعیت
    public static String _statusLog = "plans_statusLog";//روند وضعیت
    public static String _description = "plans_description";//توضیحات
    public static String _correction = "plans_correction";//اصلاحیه
    public static String _date = "plans_date";//تاریخ
    public static String _commettesId = "plans_commettesId";//ای دی کمیته
    public static String _files = "plans_files";//ای دی کمیته

    public static int rul_modulePlans = 375;
    public static int rul_rfs = 376;
    public static int rul_rfsAll = 380;
    public static int rul_communicatingToSupervisor = 381;//ابلاغ به مافوق 
    public static int rul_ignoreBySupervisor = 384;//رد برنامه 
    public static int rul_rfsMyPlans = 383;
    public static int rul_ignoreByImproveQuality = 385;//رد برنامه 
    public static int rul_requestEdit = 386;//درخواست ویرایش توسط ایجاد کننده برنامه عملیاتی 
    public static int rul_communicatedBySupervisor = 387;//ابلاغ به بهبود کیفیت وتایید توسط ما فوق
    public static int rul_communicatedByImproveQuality = 388;//ابلاغ به کمیته و تایید توسط بهبود کیفیت
    public static int rul_rfsAllMyPlans = 390;
    public static int rul_rfsPlansForAssess = 397;
    public static int rul_rfsAllPlansForAssess = 398;
    public static int rul_communicatedOfferFormToCommettes = 1;//دکمه ابلاغ فرم پیشنهادی به عنوان مصوبه  جلسه
    public static int rul_ins = 377;
    public static int rul_edt = 378;
    public static int rul_dlt = 379;

    public static int rul_rfsManagerPlans = 391;
    public static int rul_communicatedStep = 392;//ابلاغ گام توسط مدیر 
    public static int rul_confirmByManager = 393;//مدیر
    public static int rul_ignorByManager = 394;//رد برنامه 
    public static int rul_requestEdtByManager = 395;//درخواست ویرایش توسط ایجاد کننده برنامه عملیاتی 
    public static int rul_rfsAllManagerPlans = 396;
    public static int rul_report = 420;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";
    public static String lbl_repair = "اصلاح";
    public static String lbl_communicatingToSupervisor = "تایید و ارسال برنامه به مافوق جهت ابلاغ";
    public static String status_initialRegistration = "ثبت اولیه";
    public static String status_edit = "در حال ویرایش";
    public static String status_correctionPlans = "اصلاحیه";
    public static String status_confirmationFinal = "تایید نهایی";
    public static String status_confirmationSupervisor = "تایید مافوق";//ارسال شده به مسئول پایش
    public static String status_ignoreBySupervisor = "رد برنامه توسط مافوق";
    public static String status_ignoreByImproveQuality = "رد برنامه توسط مسئول بهبود کیفیت";
    public static String status_confirmByImproveQuality = "ارسال شده به کمیته، در انتظار ابلاغ";

    public static String status_confirmByManager = "ابلاغ شده";
    public static String status_ignorByManager = "رد برنامه توسط مدیر";
    public static String vaziat = "";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            boolean accRulRef = Access_User.hasAccess(request, db, rul_rfs);//
            boolean accRulRefAll = Access_User.hasAccess(request, db, rul_rfsAll);//
            DefaultTableModel dtm;
            StringBuilder html = new StringBuilder();
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                dtm = db.Select(tableName);
            } else {
                dtm = db.Select(tableName, _creatorId + "=" + jjTools.getSeassionUserId(request));
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">برنامه عملیاتی/بهبود</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\">برنامه های عملیاتی/بهبود کیفیت تعریف شده</div>\n");
            html.append("<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon' style='float:right'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisPlans.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "<a href='#' id='learnPlansIcon' class='sh-pagetitle-icon' style='float:left' title='آموزش ماژول برنامه عملیاتی'>"
                    + "<div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "<span  style='display:block' class='col-lg-1'>"
                    + "<div id='plans_learn' style='display:none'>"
                    + "<a>فیلم آموزشی</a>"
                    + "<br/>"
                    + "<a>فایل آموزشی</a>"
                    + "</div>"
                    + "</span>"
                    + "");
            html.append("<div class='col-lg-3'>"
                    + "</div>"
                    + "");

            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append(""
                        + " <p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + " <a class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" style='color:#fff' onclick=\"hmisPlans.m_add_new();\" > برنامه عملیاتی/بهبود کیفیت جدید</a>\n"
                        + "  </p>");
            }
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<div align=\"right\">");
            html.append("<table id='refreshPlans' class='table display responsive' ><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان</th>");
            html.append("<th width='5%'>نوع برنامه عملیاتی </th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='5%'>وضعیت</th>");
            html.append("<th width='30%'>درصد کل پیشرفت برنامه</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                float percent = 0;
                List<Map<String, Object>> StepsRow = jjDatabaseWeb.separateRow(db.Select(Steps.tableName, Steps._plansId + "=" + row.get(i).get(_id) + " AND steps_status!='" + Steps.status_initialRegistration + "'"));
                if (StepsRow.size() > 0) {

                    for (int j = 0; j < StepsRow.size(); j++) {//جمع درصد تکمیل گام
                        percent += Float.parseFloat(StepsRow.get(j).get(Steps._percent).toString()) * (Float.parseFloat(StepsRow.get(j).get(Steps._progressPercent).toString()) / 100);
                    }
                }
                System.out.println("percent" + percent);
                html.append("<tr  onclick=hmisPlans.m_select(" + row.get(i).get(_id) + ") class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_typeOfProgram).toString()) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td >"
                        + "<div class=\"progress\">\n"
                        + "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=" + percent + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + percent + "%;\">\n"
                        + "" + percent + "%\n"
                        + "</div>\n"
                        + "</div>"
                        + "</td>");
                html.append("<td class='c'><i class='icon ion-ios-gear-outline'></i></td>");
                html.append("</tr>");
            }

            html.append("</tbody></table>");
            html.append("</div>");
            html.append("</div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swPlansTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPlans", "300", 0, "", "برنامه های عملیاتی");
            html2 += "$('#learnPlansIcon').click(function(){"
                    + "if($('#plans_learn').css('display')=='none'){"
                    + "$('#plans_learn').slideDown();"
                    + "}else{"
                    + "$('#plans_learn').slideUp();"
                    + "}"
                    + "});";

            Server.outPrinter(request, response, html2);
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
                html.append(Js.setHtml("#buttonRecord", "<button id='recordPlans'  class='btn btn-outline-success  btn-block mg-b-10' onclick='hmisPlans.m_insert();'>ثبت  برنامه</button>"));
            }
            html.append(Js.setVal("#plans_status", status_initialRegistration));
            html.append("$('#editPlansDiv').html('');");
            Server.outPrinter(request, response, html.toString());

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تابع درج date 1397/11/2 tuesday
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_creatorId, jjTools.getSeassionUserId(request));
            map.put(_files, jjTools.getParameter(request, _files));
            map.put(_supervisorRoleId, jjTools.getParameter(request, _supervisorRoleId));
            map.put(_improveQualityId, jjTools.getParameter(request, _improveQualityId));
            map.put(_causeProblem, jjTools.getParameter(request, _causeProblem));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length("", true));
            map.put(_department, jjTools.getParameter(request, _department));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_domain, jjTools.getParameter(request, _domain));
            System.out.println("jjTools.getParameter(request, _minorGoal)=" + jjTools.getParameter(request, _minorGoal));
            System.out.println("jjTools.getParameter(request, _hugeGoal)=" + jjTools.getParameter(request, _hugeGoal));
            System.out.println("jjTools.getParameter(request, _strategic)=" + jjTools.getParameter(request, _strategic));
            if (jjTools.getParameter(request, _strategic).equals("null") || jjTools.getParameter(request, _strategic).equals("0")) {
                map.put(_strategic, "0");
            } else {
                map.put(_strategic, jjTools.getParameter(request, _strategic));

            }
            if (jjTools.getParameter(request, _minorGoal).equals("null") || jjTools.getParameter(request, _minorGoal).equals("0")) {
                map.put(_minorGoal, "0");
            } else {
                map.put(_minorGoal, jjTools.getParameter(request, _minorGoal));
            }
            if (jjTools.getParameter(request, _hugeGoal).equals("null") || jjTools.getParameter(request, _hugeGoal).equals("0")) {
                map.put(_hugeGoal, "0");
            } else {
                map.put(_hugeGoal, jjTools.getParameter(request, _hugeGoal));
            }
            map.put(_method, jjTools.getParameter(request, _method));
            map.put(_range, jjTools.getParameter(request, _range));
            map.put(_managerRoleId, 1);//ای دی مدیر بیمارستان که به صورت اتوماتیک وارد بیمارستان می شوذ
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_typeOfProgram, jjTools.getParameter(request, _typeOfProgram));
            map.put(_titleOfTheProblem, jjTools.getParameter(request, _titleOfTheProblem));
            map.put(_status, status_initialRegistration);
            map.put(_statusLog, status_initialRegistration
                    + ":"
                    + "-"
                    + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                    + ""
                    + "User_Id= "
                    + jjTools.getSeassionUserId(request)
                    + "Role_Id= "
                    + jjTools.getSeassionUserRole(request)
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23");//            در زمان زدن دکمه ثبت وضعیت نمونه ثبت اولیه می شود ودر روند وضعیت ثبت اولیه با تاریخ وساعت ثبت می شود
            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> PlansRow = jjDatabaseWeb.separateRow(dtm);
            html.append(Js.setHtml("#period", PlansRow.get(0).get(_period)));//دوره پایش
            html.append(Js.setHtml("#strategic", PlansRow.get(0).get(_strategic)));
            html.append(Js.setHtml("#hugeGoal", PlansRow.get(0).get(_hugeGoal)));//هدف کلان
            html.append(Js.setHtml("#minorGoal", PlansRow.get(0).get(_minorGoal)));//هدف جزئی
            html.append(Js.setHtml("#improveQualityRoleId", Role.getRoleName(PlansRow.get(0).get(_improveQualityId).toString(), db)));//هدف جزئی
//            html.append(Js.setHtml("#managerRoleId", Role.getRoleName(PlansRow.get(0).get(_managerRoleId).toString(), db)));//مسول پایش
            html.append(Js.setHtml("#range", PlansRow.get(0).get(_range)));//حیطه

            String script = "";
            script += html;
            script += "hmisPlans.m_select( " + PlansRow.get(0).get(_id) + ");";
            script += Js.setVal("#hmis_plans_id", PlansRow.get(0).get(_id));//ایدی پلن
            script += " $('#btn_addNewSteps').slideDown();";//نمایش دکمه اد نیو برای جدول گام ها
            script += " hmisSteps.m_add_new();";//نمایش دکمه اد نیو برای جدول گام ها

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ای دی پلن
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
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            StringBuilder html6 = new StringBuilder();
            String script = "";
            List<Map<String, Object>> totalTargetRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + row.get(0).get(_hugeGoal)));
            List<Map<String, Object>> ProprietaryTargetRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + row.get(0).get(_minorGoal)));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _typeOfProgram, row.get(0).get(_typeOfProgram)));
            if (row.get(0).get(_typeOfProgram).equals("برنامه بهبود کیفیت")) {
                html.append("$('#planImprovementDiv').slideDown();");
            } else {
                html.append("$('#planImprovementDiv').slideUp();");
            }
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _range, row.get(0).get(_range)));
            html.append("$('#" + _department + "').val([" + row.get(0).get(_department) + "]);$('#" + _department + "').select2({  width: '100%'});");
            html.append("$('#" + _supervisorRoleId + "').val([" + row.get(0).get(_supervisorRoleId) + "]);$('#" + _supervisorRoleId + "').select2({  width: '100%'});");
            html.append("$('#" + _improveQualityId + "').val([" + row.get(0).get(_improveQualityId) + "]);$('#" + _improveQualityId + "').select2({  width: '100%'});");
            html.append("$('#" + _domain + "').val([" + row.get(0).get(_domain) + "]);$('#" + _domain + "').select2({  width: '100%'});");

            html.append(Js.setValSummerNote("#" + _description, row.get(0).get(_description)));
            html.append(Js.setVal("#" + _causeProblem, row.get(0).get(_causeProblem)));
            html.append(Js.setVal("#" + _method, row.get(0).get(_method)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setVal("#" + _titleOfTheProblem, row.get(0).get(_titleOfTheProblem)));
            html.append(Js.setVal("#" + _managerRoleId, row.get(0).get(_managerRoleId)));
            html.append(Js.setVal("#" + _strategic, row.get(0).get(_strategic)));
            html.append(Js.setVal("#" + _period, row.get(0).get(_period)));
            html.append(Js.setValSelectOption("#" + _strategic, row.get(0).get(_strategic).toString()));
            html.append(Js.select2("#" + _strategic, " width: '100%'"));

            html.append(Js.setValSelectOption("#" + _minorGoal, row.get(0).get(_minorGoal).toString()));
            html.append(Js.select2("#" + _minorGoal, " width: '100%'"));
            html.append(Js.setValSelectOption("#" + _hugeGoal, row.get(0).get(_hugeGoal).toString()));
            html.append(Js.select2("#" + _hugeGoal, " width: '100%'"));
            html.append(Js.setHtml("#problem", (row.get(0).get(_titleOfTheProblem).toString())));
            html.append(Js.setHtml("#causeProblem", (row.get(0).get(_causeProblem).toString())));
            html.append(Js.setHtml("#methodProblem", (row.get(0).get(_method).toString())));
            html.append(Js.setHtml("#titlePlans", (row.get(0).get(_title).toString())));
            html.append(Js.setHtml("#statusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            html.append(Js.setHtml("#period", row.get(0).get(_period)));//دوره پایش
            html.append(Js.setVal("#" + _minorGoal, row.get(0).get(_minorGoal)));
            html.append(Js.setVal("#" + _hugeGoal, row.get(0).get(_hugeGoal)));
            List<Map<String, Object>> proprietaryRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + row.get(0).get(_minorGoal)));
            if (proprietaryRow.size() > 0) {
                html.append(Js.setHtml("#minorGoal", proprietaryRow.get(0).get(ProprietaryTarget._title)));
            }
            List<Map<String, Object>> totalRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + row.get(0).get(_hugeGoal)));
            if (totalRow.size() > 0) {
                html.append(Js.setHtml("#hugeGoal", totalRow.get(0).get(TotalTarget._title)));
            }
            List<Map<String, Object>> strategyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._id + "=" + row.get(0).get(_strategic)));
            if (strategyRow.size() > 0) {
                html.append(Js.setHtml("#strategic", strategyRow.get(0).get(Strategy._title)));
            }

            html.append(Js.setHtml("#improveQualityRoleId", Role.getRoleName(row.get(0).get(_improveQualityId).toString(), db)));//مسول پایش
            html.append(Js.setHtml("#range", row.get(0).get(_range)));//حیطه
            if (!row.get(0).get(_domain).equals("null") && !row.get(0).get(_domain).equals("")) {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.Select(Department.tableName, Department._id + "=" + row.get(0).get(_domain)));
                html.append(Js.setHtml("#domain", departmentRow.get(0).get(Department._title).toString()));//دامنه
            }

            boolean accCommunicatingToSupervisor = Access_User.hasAccess(request, db, rul_communicatingToSupervisor);//ابلاغ به مافوق
            boolean accDlt = Access_User.hasAccess(request, db, rul_dlt);//
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            if (row.get(0).get(_status).equals(status_initialRegistration)) {//ثبت اولیه
                if (accCommunicatingToSupervisor) {
                    html3.append("<div class='col-lg-12'>");
                    html3.append("<button onclick='hmisPlans.communicatingToSupervisor(" + id + ");' id=\"communicatingToSupervisor_Plans\"  class=\"btn btn-outline-success  btn-block mg-b-10\">" + lbl_communicatingToSupervisor + "</button>");
                    html3.append("</div>");
                }
                if (accEdt) {
                    html4.append("<div class='col-lg-2'>");
                    html4.append("<button onclick='hmisPlans.m_edit(" + id + ");' id='edit_Plans' class=\"btn btn-outline-success  btn-block mg-b-10\">ثبت تغییرات</button>");
                    html4.append("</div>");
                }
                if (accDlt) {
                    html4.append("<div class='col-lg-2'>");
                    html4.append("<button onclick='hmisPlans.m_delete(" + id + ");' id='delete_Plans' class=\"btn btn-outline-danger  btn-block mg-b-10\">حذف برنامه عملیاتی</button>");
                    html4.append("</div>");
                }
            }

            script += "$('#recordPlans').slideUp();";//ثیت برنامه عملیاتی
            ////////////////////////////نمایش جدول گامها//////////////////
            List<Map<String, Object>> StepsRow = jjDatabase.separateRow(db.Select(Steps.tableName, Steps._plansId + "=" + id));
            if (Access_User.hasAccess(request, db, Steps.rul_ins)) {//
                if (row.get(0).get(_status).equals(status_initialRegistration)) {

                    html2.append(" "
                            + " <a class=\"btn btn-success pd-sm-x-20  tx-white\" style='color:#fff' onclick=\"hmisSteps.m_add_new();\" >گام جدید</a>\n"
                    );
                }
            }
            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<div align=\"right\">");
            html2.append("<table id='refreshTblSteps' class=\"table table-responsive\"><thead>");
            html2.append("<th width='5%'>کد</th>");
            html2.append("<th width='20%'>عنوان گام </th>");
            html2.append("<th width='10%'>درصد تکمیل گام</th>");
            html2.append("<th width='10%'> مسئول اجرا</th>");
            html2.append("<th width='10%'>مسئول پیگیری</th>");
            html2.append("<th width='10%'>تاریخ شروع</th>");
            html2.append("<th width='10%'>تاریخ پایان</th>");
            html2.append("<th width='10%'>هزینه</th>");
            html2.append("<th width='5%'>شاخص دستیابی</th>");
            html2.append("<th width='10%'>درصد پیشرفت گام</th>");
            html2.append("<th width='5%'>وضعیت گام</th>");
            html2.append("<th width='5%'>وضعیت</th>");
            html2.append("<th width='5%'>عملیات</th>");
            html2.append("</thead><tbody>");
            for (int i = 0; i < StepsRow.size(); i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";
                String trackerRoleName = "";

                if (!StepsRow.get(i).get(Steps._executorRoleId).equals("")) {
                    String executorRoleId = StepsRow.get(i).get(Steps._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("ExecutorRoleName=" + ExecutorRoleName);
                    }
                }
                if (!StepsRow.get(i).get(Steps._executorUserId).equals("")) {
                    if (StepsRow.get(i).get(Steps._executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = StepsRow.get(i).get(Steps._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            System.out.println("ExecutorUserName=" + ExecutorUserName);
                        }
                    }
                }
                float percent = 0;
                percent = Float.parseFloat(StepsRow.get(i).get(Steps._percent).toString()) * (Float.parseFloat(StepsRow.get(i).get(Steps._progressPercent).toString()) / 100);
                String isActive = StepsRow.get(i).get(Steps._isActive).equals("0") ? "غیر نهایی" : "نهایی";

                html2.append("<tr  onclick='hmisPlans.selectStepsInPlans(" + StepsRow.get(i).get(Steps._id) + ")' class='mousePointer " + Sessions.getClassCssForVaziat(StepsRow.get(i).get(Steps._status).toString()) + "'>");
                html2.append("<td class='c'>" + StepsRow.get(i).get(Steps._id) + "</td>");
                html2.append("<td class='r'>" + (StepsRow.get(i).get(Steps._title).toString()) + "</td>");
                html2.append("<td class='r'>" + (StepsRow.get(i).get(Steps._percent).toString()) + "%</td>");
                html2.append("<td class='r'>" + ExecutorUserName + "" + ExecutorRoleName + "</td>");
                String trackerRoleId = StepsRow.get(i).get(Steps._trackerId).toString();
                trackerRoleName = Role.getRoleUserName(trackerRoleId, db);
                html2.append("<td class='r'>" + trackerRoleName + "</td>");
                html2.append("<td class='r'>" + jjCalendar_IR.getViewFormat(StepsRow.get(i).get(Steps._startDate).toString()) + "</td>");
                html2.append("<td class='r'>" + jjCalendar_IR.getViewFormat(StepsRow.get(i).get(Steps._endDate).toString()) + "</td>");
                html2.append("<td class='r'>" + jjNumber.getFormattedNumber(StepsRow.get(i).get(Steps._cost).toString()) + "</td>");
                html2.append("<td class='r'>" + (StepsRow.get(i).get(Steps._otherIndicators).toString()) + "</td>");
                if (!StepsRow.get(i).get(Steps._status).toString().equals(Steps.status_initialRegistration)) {//زمانی که وضعیت گام ثبت اولیه نبود ستون پیشرفت گام را نمایش دهد
                    html2.append("<td class='r'>" + percent + "%</td>");
                } else {
                    html2.append("<td class='r'><div></div></td>");
                }
                html2.append("<td class='r'>" + isActive + "</td>");
                html2.append("<td class='r'>" + (StepsRow.get(i).get(Steps._status).toString()) + "</td>");
                html2.append("<td class='c'><i class='icon ion-ios-gear-outline'></i></td>");
                html2.append("</tr>");
            }
            html2.append("</tbody></table>");
            html2.append("</div>");
            html2.append("</div>");
            html.append(Js.setVal("#" + _files, row.get(0).get(_files)));

            //////////////////////////////////////
            String attachFilesSessions = row.get(0).get(_files).toString();
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
                        html5.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html5.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
//            html3.append("<div class=\"col-lg-6\">");
//            html3.append("<div class='sh-pagetitle-icon'>");
//            html3.append("<a href='Server?do=Plans.downloadPlans&id=" + id + "' title='چاپ برنامه عملیاتی' target='_blank'  class='active btn-block mg-l-10'><i class='fa fa-print mg-t-3'></i></a>");
//            html3.append("</div>");
//            html3.append("</div>");
            script += Js.setHtml("#downloadPlansFileDiv", html5.toString());
            script += Js.setHtml("#tblSteps", html2.toString());
            script += Js.setHtml("#editPlansDiv", html4.toString());
            script += Js.setHtml("#btns_plans", html3.toString());
            script += "hmisProprietaryTarget.selectOptionProprietaryTarget(" + row.get(0).get(_hugeGoal) + ");";
            script += "hmisProprietaryTarget.selectOptionStrategy(" + row.get(0).get(_minorGoal) + ");";
//

            script += html;
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تغییر وضعیت برنامه عملیاتی
     *
     * @param db
     * @param id
     * @param newSatus
     * @return
     */
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
                        + jjTools.getSeassionUserId(request) + " "
                        + "  roleId="
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

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id).toString();
            String script = "";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_supervisorRoleId, jjTools.getParameter(request, _supervisorRoleId));
            map.put(_causeProblem, jjTools.getParameter(request, _causeProblem));
            map.put(_files, jjTools.getParameter(request, _files));
            map.put(_department, jjTools.getParameter(request, _department));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_domain, jjTools.getParameter(request, _domain));

            if (jjTools.getParameter(request, _strategic).equals("null") || jjTools.getParameter(request, _strategic).equals("0")) {
                map.put(_strategic, "0");
            }
            if (jjTools.getParameter(request, _minorGoal).equals("null") || jjTools.getParameter(request, _minorGoal).equals("0")) {
                map.put(_minorGoal, "0");
            }
            if (jjTools.getParameter(request, _hugeGoal).equals("null") || jjTools.getParameter(request, _hugeGoal).equals("0")) {
                map.put(_hugeGoal, "0");
            }
            map.put(_method, jjTools.getParameter(request, _method));
            map.put(_range, jjTools.getParameter(request, _range));
//            map.put(_managerRoleId, jjTools.getParameter(request, _managerRoleId));
            map.put(_improveQualityId, jjTools.getParameter(request, _improveQualityId));
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_typeOfProgram, jjTools.getParameter(request, _typeOfProgram));
            map.put(_titleOfTheProblem, jjTools.getParameter(request, _titleOfTheProblem));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
                script += "hmisPlans.m_select(" + id + ");";
                script += Js.modal("تغییرات انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editInMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id).toString();
            String script = "";
            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(_supervisorRoleId, jjTools.getParameter(request, _supervisorRoleId));
            map.put(_causeProblem, jjTools.getParameter(request, _causeProblem));
            map.put(_department, jjTools.getParameter(request, _department));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_domain, jjTools.getParameter(request, _domain));
            map.put(_files, jjTools.getParameter(request, _files));
            if (jjTools.getParameter(request, _strategic).equals("null") || jjTools.getParameter(request, _strategic).equals("0")) {
                map.put(_strategic, "0");
            }
            if (jjTools.getParameter(request, _minorGoal).equals("null") || jjTools.getParameter(request, _minorGoal).equals("0")) {
                map.put(_minorGoal, "0");
            }
            if (jjTools.getParameter(request, _hugeGoal).equals("null") || jjTools.getParameter(request, _hugeGoal).equals("0")) {
                map.put(_hugeGoal, "0");
            }
            map.put(_method, jjTools.getParameter(request, _method));
            map.put(_range, jjTools.getParameter(request, _range));
//            map.put(_managerRoleId, jjTools.getParameter(request, _managerRoleId));//اگر مدیر قایل انتخاب بود
            map.put(_improveQualityId, jjTools.getParameter(request, _improveQualityId));
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_typeOfProgram, jjTools.getParameter(request, _typeOfProgram));
            map.put(_titleOfTheProblem, jjTools.getParameter(request, _titleOfTheProblem));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
//                script += "hmisMyPlans.m_select(" + id + ");";
//                script += Js.modal("تغییرات انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editInManagerPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id).toString();
            String script = "";
            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(_supervisorRoleId, jjTools.getParameter(request, _supervisorRoleId));
            map.put(_causeProblem, jjTools.getParameter(request, _causeProblem));
            map.put(_department, jjTools.getParameter(request, _department));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_domain, jjTools.getParameter(request, _domain));
            map.put(_files, jjTools.getParameter(request, _files));

            if (jjTools.getParameter(request, _strategic).equals("null") || jjTools.getParameter(request, _strategic).equals("0")) {
                map.put(_strategic, "0");
            }
            if (jjTools.getParameter(request, _minorGoal).equals("null") || jjTools.getParameter(request, _minorGoal).equals("0")) {
                map.put(_minorGoal, "0");
            }
            if (jjTools.getParameter(request, _hugeGoal).equals("null") || jjTools.getParameter(request, _hugeGoal).equals("0")) {
                map.put(_hugeGoal, "0");
            }
            map.put(_method, jjTools.getParameter(request, _method));
            map.put(_range, jjTools.getParameter(request, _range));
//            map.put(_managerRoleId, jjTools.getParameter(request, _managerRoleId));//اگر مدیر انتخابی باشد
            map.put(_improveQualityId, jjTools.getParameter(request, _improveQualityId));
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_typeOfProgram, jjTools.getParameter(request, _typeOfProgram));
            map.put(_titleOfTheProblem, jjTools.getParameter(request, _titleOfTheProblem));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            } else {
//                script += "hmisManagerPlans.m_select(" + id + ");";
//                script += Js.modal("تغییرات انجام شد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تایید توسط مافوق وابلاغ به مسئول بهبود وکیفیت
     *
     * @param request
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String confirmBySupervisor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatedBySupervisor)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            changeStatus(request, db, id, status_confirmationSupervisor);
            //تایید توسط ما فوق
            String script = "";
            script += Js.modal("پیام سامانه", "برنامه عملیاتی توسط مافوق" + row.get(0).get(_supervisorRoleId) + "تایید شد.");
//            script += "hmisPlans.m_select(" + id + ");";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * ابلاغ به مافوق
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String communicatingToSupervisor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatingToSupervisor)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//ای دی برنامه عملیاتی
            String script = "";

            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_supervisorRoleId, jjTools.getParameter(request, _supervisorRoleId));
            map.put(_causeProblem, jjTools.getParameter(request, _causeProblem));
            map.put(_department, jjTools.getParameter(request, _department));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_domain, jjTools.getParameter(request, _domain));
            map.put(_hugeGoal, jjTools.getParameter(request, _hugeGoal));
            map.put(_minorGoal, jjTools.getParameter(request, _minorGoal));
            map.put(_method, jjTools.getParameter(request, _method));
            map.put(_range, jjTools.getParameter(request, _range));
            map.put(_managerRoleId, 1);
            map.put(_strategic, jjTools.getParameter(request, _strategic));
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_typeOfProgram, jjTools.getParameter(request, _typeOfProgram));
            map.put(_titleOfTheProblem, jjTools.getParameter(request, _titleOfTheProblem));
            String text = "مافوق محترم لطفا وارد برنامه مدیار شوید و برنامه عملیاتی را تایید کنید";
            StringBuilder html = new StringBuilder();
            //                    html.append("<h2>فایل  جلسه:" + insertedSessionsRow.get(0).get(_InviteesFile).toString() + "</h2>");
//                    html.append("<h2>ساعت  جلسه:" + insertedSessionsRow.get(0).get(_time).toString() + "</h2>");
            html.append("<a href=" + Server.mainSite + "Server?do=Plans.downloadPlans&id=" + id + "></a>");
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                script += Js.modal(errorMessage, "پیام سامانه");
            }
            String result = changeStatus(request, db, id, status_confirmationFinal);
            if (result.isEmpty()) {
                script += Js.modal("برنامه به " + Role.getRoleName(jjTools.getParameter(request, _supervisorRoleId), db) + " جهت تایید ارسال شد.", "پیام سامانه");
//                script += Js.jjPlans.select(id);
                //ارسال پیام و ایمیل و اپ برای مافوق انتخاب شده
                String tice_configMessage = Tice_config.getValue(db, Tice_config._config_sendPlantoSupervisor_name);

                if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                    text = tice_configMessage;
                    Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _supervisorRoleId), db), "1", "sms,app,email", "", "  مافوق محترم " + Role.getRoleUserName(jjTools.getParameter(request, _supervisorRoleId), db) + " برنامه عملیاتی با عنوان : " + Row.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                } else {
                    text = "برنامه عملیاتی به شما ابلاغ گردید.";
                    Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _supervisorRoleId), db), "1", "sms,app,email", "", "  مافوق محترم " + Role.getRoleUserName(jjTools.getParameter(request, _supervisorRoleId), db) + " برنامه عملیاتی با عنوان : " + Row.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                }

            } else {
                script += Js.modal(".ابلاغ انجام نشد", "پیام سامانه");
            }

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ برنامه عملیاتی به مسئول بهبود کیفیت
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String communicatedBySupervisor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatedBySupervisor)) {//
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//ای دی برنامه عملیاتی
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            String script = "";
            StringBuilder html = new StringBuilder();

            if (!jjTools.getParameter(request, _improveQualityId).equals("")) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_improveQualityId, jjTools.getParameter(request, _improveQualityId));

                if (!db.update(tableName, map, _id + "=" + id)) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                } else {
                    String result = changeStatus(request, db, id, status_confirmationSupervisor);//تایید توسط مافوق وابلاغ به مسئول بهبود کیفیت
                    if (result.isEmpty()) {
                        script += Js.modal("برنامه به مسئول پایش (واحد بهبود کیفیت) ارسال شد", "پیام سامانه");
//                        script += "hmisMyPlans.m_refresh();";
//                        script += "hmisMyPlans.m_select(" + id + ");";
                        String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_sendPlantoImproveQuality_name);
                        if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                            String text = tice_configMessage;
                            Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _improveQualityId), db), "1", "sms,app,email", "", "مسئول پایش " + Role.getRoleUserName(jjTools.getParameter(request, _improveQualityId), db) + "  برنامه عملیاتی " + jjTools.getParameter(request, _title) + " برای شما ابلاغ شده است پیگیری نمایید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                        } else {
                            String text = "لطفا وارد برنامه مدیار شوید و برنامه عملیاتی را تایید کنید";
                            Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(jjTools.getParameter(request, _improveQualityId), db), "1", "sms,app,email", "", "مسئول پایش " + Role.getRoleUserName(jjTools.getParameter(request, _improveQualityId), db) + "  برنامه عملیاتی " + jjTools.getParameter(request, _title) + " برای شما ابلاغ شده است پیگیری نمایید", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                        }

                    } else {
                        script += Js.modal(".ابلاغ انجام نشد", "پیام سامانه");
                    }
                }
            } else {
                script += Js.modal("مسئول بهبود کیفیت را انتخاب نمایید", "پیام سامانه");

            }

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ابلاغ برنامه به کمیته توسط مسئول بهبود کیفیت
     *
     */
    public static String communicatedByImproveQuality(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatedByImproveQuality)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//ای دی برنامه عملیاتی
            String script = "";

            List<Map<String, Object>> plansRow = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            if (!jjTools.getParameter(request, _commettesId).equals("")) {
                List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + jjTools.getParameter(request, _commettesId)));
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Object> approvedMap = new HashMap<String, Object>();
                map.put(_commettesId, jjTools.getParameter(request, _commettesId));
                if (!db.update(tableName, map, _id + "=" + id)) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                } else {
                    approvedMap.put(Approved._commettesId, jjTools.getParameter(request, _commettesId));
                    approvedMap.put(Approved._title, "برنامه عملیاتی:" + plansRow.get(0).get(_title));
                    approvedMap.put(Approved._offererId, plansRow.get(0).get(_creatorId));//ای دی پیشنهاد دهنده
                    approvedMap.put(Approved._trackerId, plansRow.get(0).get(_managerRoleId));//باید مدیر باشد
                    approvedMap.put(Approved._executorRoleId, plansRow.get(0).get(_managerRoleId));//باید مدیر باشد که اگر قبول نئپداشت برنامه را بتواند آن را رد کند
                    approvedMap.put(Approved._status, Approved.status_offer);//وضعیت پیشنهاد شده
                    approvedMap.put(Approved._statusLog, Approved.status_offer
                            + ":"
                            + "-"
                            + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                            + " user_id="
                            + jjTools.getSeassionUserId(request) + " "
                            + " role_id="
                            + jjTools.getSeassionUserRole(request)
                            + " "
                            + new jjCalendar_IR().getTimeFormat_8length()
                            + "%23A%23");

                    DefaultTableModel dtm = db.insert(Approved.tableName, approvedMap);
                    if (dtm.getRowCount() == 0) {
                        String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                        if (jjTools.isLangEn(request)) {
                            errorMessage = "Edit Fail;";
                        }
                        script += Js.modal(errorMessage, "پیام سامانه");
                    } else {
                        String result = changeStatus(request, db, id, status_confirmByImproveQuality);//تایید توسط بهبود کیفیت وابلاغ به  کمیته
                        if (result.isEmpty()) {
                            script += Js.modal("عنوان برنامه به کمیته " + commettesRow.get(0).get(Commettes._title) + "ارسال گردید", "پیام سامانه");
//                            script += "hmisMyPlans.m_refresh();";
//                            script += "hmisMyPlans.m_select(" + id + ");";
                            StringBuilder html = new StringBuilder();
                            String tice_configMessageManager = Tice_config.getValue(db,  Tice_config._config_sendPlantoManager_name);
                            if (!tice_configMessageManager.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                String text = tice_configMessageManager;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(plansRow.get(0).get(Plans._managerRoleId).toString(), db), "1", "sms,app,email", "", "مدیر بیمارستان،  برنامه عملیاتی با عنوان: " + plansRow.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            } else {
                                String text = "وارد برنامه مدیار شوید و برنامه را تایید کنید.";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(plansRow.get(0).get(Plans._managerRoleId).toString(), db), "1", "sms,app,email", "", "مدیر بیمارستان،  برنامه عملیاتی با عنوان: " + plansRow.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            }
                            String tice_configMessageCommettes = Tice_config.getValue(db,  Tice_config._config_sendPlantoCommittee_name);
                            if (!tice_configMessageCommettes.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                                String text = tice_configMessageCommettes;
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(commettesRow.get(0).get(Commettes._secretary).toString(), db), "1", "sms,app,email", "", "دبیر کمیته،" + commettesRow.get(0).get(Commettes._title) + " برنامه عملیاتی با عنوان: " + plansRow.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));

                            } else {
                                String text = "مصوبه پیشنهادی در کمیته شما ایجاد شد";
                                Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(commettesRow.get(0).get(Commettes._secretary).toString(), db), "1", "sms,app,email", "", "دبیر کمیته،" + commettesRow.get(0).get(Commettes._title) + " برنامه عملیاتی با عنوان: " + plansRow.get(0).get(_title) + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                            }

                        } else {
                            script += Js.modal(".ابلاغ انجام نشد", "پیام سامانه");
                        }
                    }
                }
            } else {
                script += Js.modal("دبیر کمیته را انتخاب نمایید", "پیام سامانه");

            }
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * زمانی که برنامه عملیاتی تایید نهایی می شود و برای مافوق ارسال می شود
     * مافوق در این قسمت جدول می آید و برای بهبود کیفت ارسال می کند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsMyPlans)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm;
            String OtherSelect = "";
            if (Access_User.hasAccess(request, db, rul_rfsAllMyPlans)) {
                OtherSelect = "SELECT"
                        + " P.id,"
                        + "P.plans_title"
                        + ",P.plans_date"
                        + ",P.plans_status"
                        + ",P.plans_supervisorRolId"
                        + ",P.plans_improveQualityId"
                        + " FROM hmis_plans P"
                        + " LEFT JOIN  hmis_role      R      ON  P.plans_supervisorRolId=R.id"
                        + " WHERE  P.plans_status!='" + status_initialRegistration + "' AND (( P.plans_status='" + status_confirmationFinal + "' )"
                        + " OR ( P.plans_status='" + status_confirmationSupervisor + "') )";
            } else {
                String roles = jjTools.getSeassionUserRole(request);
                String[] role = roles.split(",");
                String condition1 = "";
                String condition2 = "";
                for (int i = 0; i < role.length; i++) {
                    System.out.println("role" + role[i]);
                    condition1 += " p.plans_supervisorRolId =" + role[i] + " OR";
                    condition2 += " p.plans_improveQualityId=" + role[i] + " OR";
                }
                OtherSelect = "SELECT"
                        + " P.id,"
                        + "P.plans_title"
                        + ",P.plans_date"
                        + ",P.plans_status"
                        + ",P.plans_supervisorRolId"
                        + ",P.plans_improveQualityId"
                        + " FROM hmis_plans P"
                        + " LEFT JOIN  hmis_role      R      ON  P.plans_supervisorRolId=R.id"
                        + " WHERE  P.plans_status!='" + status_initialRegistration + "' AND (( P.plans_status='" + status_confirmationFinal + "'  AND " + condition1.substring(0, condition1.length() - 2) + ")"
                        + " OR ( P.plans_status='" + status_confirmationSupervisor + "'  AND  " + condition2.substring(0, condition2.length() - 2) + ") )";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            int sessionUserId = jjTools.getSeassionUserId(request);// ای دی فرد وارد شونده
            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">برنامه عملیاتی/بهبود</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\"> لیست برنامه های عملیاتی  من</div>\n");
            html.append("<div class=\"table-wrapper\">\n");
            html.append(""
                    + "<div class='col-lg-12'>\"\n"
                    + "<a href='#' class='sh-pagetitle-icon'><div style='font-size: 33px'><i class='fa fa-refresh  mg-t-30' onclick='hmisMyPlans.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append("<table id='refreshMyPlans' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان برنامه عملیاتی</th>");
            html.append("<th width='15%'> مافوق</th>");
            html.append("<th width='15%'>مسئول پایش (بهبود کیفیت)</th>");
            html.append("<th width='20%'>تاریخ شروع</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='40%'>انتقال به میز هوشمند</th>");
            html.append("</thead><tbody>");

            ///نمایش برنامه عملیاتی مربوط به مافوق
            dtm = db.otherSelect(OtherSelect);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + row.get(i).get(Plans._id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Plans._title) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(Plans._supervisorRoleId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(Plans._improveQualityId).toString(), db) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(Plans._date)) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Plans._status) + "</td>");
                html.append("<td class='r' onclick='hmisMyPlans.m_select(" + row.get(i).get(Plans._id) + ")'>"
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
                panel = "swMyPlansTbl";
            }

            String html2 = Js.setHtml("#" + panel, html.toString());

            html2 += Js.table("#refreshMyPlans", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    /**
     * انتخاب برنامه عملیاتی من
     *
     * خصوصیات سکت اگر در برنامه عملیاتی من برنامه توسط مافوق تایید شده باشد در
     * سلکت مافوق ابلاغ به کمیته نمایش داده نمی شود
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);// ای دی برنامه عملیاتی
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
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            if (row.get(0).get(_typeOfProgram).equals("برنامه بهبود کیفیت")) {
                html.append("$('#myPlanImprovementDiv').slideDown();");
            } else {
                html.append("$('myPlanImprovementDiv').slideUp();");
            }
            String script = "";
            html.append(Js.setVal("#hmis_myPlans_id", row.get(0).get(_id)));
            //////////////////////////////////////////////////////////////////

            List<Map<String, Object>> proprietaryRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + row.get(0).get(_minorGoal)));
            if (proprietaryRow.size() > 0) {
                html.append(Js.setHtml("#minorGoalMyPlans", proprietaryRow.get(0).get(ProprietaryTarget._title)));
            }
            List<Map<String, Object>> totalRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + row.get(0).get(_hugeGoal)));
            if (totalRow.size() > 0) {
                html.append(Js.setHtml("#hugeGoalMyPlans", totalRow.get(0).get(TotalTarget._title)));
            }
            List<Map<String, Object>> strategyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._id + "=" + row.get(0).get(_strategic)));
            if (strategyRow.size() > 0) {
                html.append(Js.setHtml("#strategicMyPlans", strategyRow.get(0).get(Strategy._title)));
            }

            //////////////////////////////////////////////////////////////////
            html.append(Js.setVal("#myPlans_strategic", row.get(0).get(_strategic)));
            html.append(Js.setVal("#myPlans_minorGoal", row.get(0).get(_minorGoal)));
            html.append(Js.setVal("#myPlans_hugeGoal", row.get(0).get(_hugeGoal)));
            html.append(Js.setVal("#myPlans_typeOfProgram", row.get(0).get(_typeOfProgram)));
            html.append(Js.setValSelectOption("#myPlans_strategic", row.get(0).get(_strategic).toString()));
            html.append(Js.select2("#myPlans_strategic", " width: '100%'"));
            html.append(Js.setVal("#myPlans_title", row.get(0).get(_title)));
            html.append("$('#myPlans_department').val([" + row.get(0).get(_department) + "]);$('#myPlans_department').select2({  width: '100%'});");
            html.append(Js.setHtml("#myPlans_supervisorRolId", Role.getRoleUserName(row.get(0).get(_supervisorRoleId).toString(), db)));
            html.append("$('#myPlans_domain').val([" + row.get(0).get(_domain) + "]);$('#myPlans_domain').select2({  width: '100%'});");
            html.append(Js.setValSelectOption("#myPlans_minorGoal", row.get(0).get(_minorGoal).toString()));
            html.append(Js.select2("#myPlans_minorGoal", " width: '100%'"));
            html.append(Js.setValSelectOption("#myPlans_hugeGoal", row.get(0).get(_hugeGoal).toString()));
            html.append(Js.select2("#myPlans_hugeGoal", " width: '100%'"));
            html.append("$('#" + _improveQualityId + "').val([" + row.get(0).get(_improveQualityId) + "]);$('#" + _improveQualityId + "').select2({  width: '100%'});");
            html.append(Js.setVal("#myPlans_range", row.get(0).get(_range)));
            html.append(Js.setValSummerNote("#myPlans_description", row.get(0).get(_description)));
            html.append(Js.setVal("#myPlans_causeProblem", row.get(0).get(_causeProblem)));
            html.append(Js.setVal("#myPlans_method", row.get(0).get(_method)));
            html.append(Js.setVal("#myPlans_status", row.get(0).get(_status)));
            html.append(Js.setVal("#myPlans_titleOfTheProblem", row.get(0).get(_titleOfTheProblem)));
            html.append(Js.setVal("#myPlans_thePeriodAssess", row.get(0).get(_period)));
            html.append(Js.setHtml("#myPlansStatusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            html.append(Js.setHtml("#titleMyPlans", (row.get(0).get(_title).toString())));
            html.append(Js.setHtml("#statusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            html.append(Js.setHtml("#periodMyPlans", row.get(0).get(_period)));//دوره پایش
            html.append(Js.setHtml("#improveQualityRoleIdMyPlans", Role.getRoleName(row.get(0).get(_improveQualityId).toString(), db)));//مسول پایش
            html.append(Js.setHtml("#rangeMyPlans", row.get(0).get(_range)));//حیطه
            List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.Select(Department.tableName, Department._id + "=" + row.get(0).get(_domain)));
            html.append(Js.setHtml("#domainMyPlans", departmentRow.get(0).get(Department._title).toString()));//دامنه
            html.append(Js.setVal("#filesMyPlans", row.get(0).get(_files)));

            //////////////////////////////////////
            String attachFilesSessions = row.get(0).get(_files).toString();
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
                        html5.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html5.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String[] RolesId = jjTools.getSeassionUserRole(request).split(",");

//جایی که مسئول بهبود کیقیت میخواهد برنامه را ابلاغ کند
            if (row.get(0).get(_status).equals(status_confirmationSupervisor)) {
                ///////////////////////////////
                if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                    boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
                    if (accEdt) {
                        html2.append("<div class=\"col-lg-2\">");
                        html2.append("<button id='edit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.m_edit(" + id + ");' >ثبت تغییرات</button>");
                        html2.append("</div>");
                    }
                    boolean accIgnore = Access_User.hasAccess(request, db, rul_ignoreByImproveQuality);
                    if (accIgnore) {
                        html2.append("<div class=\"col-lg-2\">");
                        html2.append("<button id='ignoreImproveQuality_MyPlans' class='btn btn-outline-danger btn-block mg-b-10'  onclick='hmisMyPlans.ignoreByImproveQuality(" + id + ");' >رد برنامه</button>");
                        html2.append("</div>");
                    }
                    boolean accCommunicateTocommettes = Access_User.hasAccess(request, db, rul_communicatedByImproveQuality);
                    if (accCommunicateTocommettes) {
                        html2.append("<div class=\"col-lg-3\">");
                        html2.append("<button  id='communicatorToCommette_MyPlans' class='btn btn-outline-success  btn-block mg-b-10'  onclick='hmisMyPlans.communicatedByImproveQuality(" + id + ");' >تایید و ارسال  عنوان برنامه به کمیته جهت بررسی </button>");
                        html2.append("</div>");
                    }

                    boolean accRequestEdit = Access_User.hasAccess(request, db, rul_requestEdit);
                    if (accRequestEdit) {
                        html2.append("<div class='col-lg-2'>");
                        html2.append("<button id='requestEdit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.requestEditMyPlans(" + id + ");' >درخواست ویرایش</button>");
                        html2.append("</div>");
                    }
                    html2.append("</div>");
                    script += "hmisIndicatorCommettes.getSelectOptionIndicatorCommettes();";//نمایش کمیته ها

                } else {

                    if (row.get(0).get(_improveQualityId) != "0") {
                        Pattern pj = Pattern.compile("(^" + row.get(0).get(_improveQualityId).toString() + ",)|(," + row.get(0).get(_improveQualityId).toString() + ",)");
                        String RoleId = jjTools.getSeassionUserRole(request);
                        Matcher mj = pj.matcher(RoleId);
                        if (mj.find()) {
                            script += Js.setAttr("#myPlans_supervisorRolId", "disabled", "disabled");
                            script += Js.setAttr("#plans_improveQualityId", "disabled", "disabled");
                            script += "$('#commettesSecretoryDiv').show();";
                            html2.append("<div class='row'>");
                            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
                            if (accEdt) {
                                html2.append("<div class=\"col-lg-2\">");
                                html2.append("<button id='edit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.m_edit(" + id + ");' >ثبت تغییرات</button>");
                                html2.append("</div>");
                            }
                            boolean accIgnore = Access_User.hasAccess(request, db, rul_ignoreByImproveQuality);
                            if (accIgnore) {
                                html2.append("<div class=\"col-lg-2\">");
                                html2.append("<button id='ignoreImproveQuality_MyPlans' class='btn btn-outline-danger btn-block mg-b-10'  onclick='hmisMyPlans.ignoreByImproveQuality(" + id + ");' >رد برنامه</button>");
                                html2.append("</div>");
                            }
                            boolean accCommunicateTocommettes = Access_User.hasAccess(request, db, rul_communicatedByImproveQuality);
                            if (accCommunicateTocommettes) {
                                html2.append("<div class=\"col-lg-3\">");
                                html2.append("<button  id='communicatorToCommette_MyPlans' class='btn btn-outline-success  btn-block mg-b-10'  onclick='hmisMyPlans.communicatedByImproveQuality(" + id + ");' >تایید و ارسال  عنوان برنامه به کمیته جهت بررسی </button>");
                                html2.append("</div>");
                            }

                            boolean accRequestEdit = Access_User.hasAccess(request, db, rul_requestEdit);
                            if (accRequestEdit) {
                                html2.append("<div class='col-lg-2'>");
                                html2.append("<button id='requestEdit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.requestEditMyPlans(" + id + ");' >درخواست ویرایش</button>");
                                html2.append("</div>");
                            }
                            html2.append("</div>");
                            script += "hmisIndicatorCommettes.getSelectOptionIndicatorCommettes();";//نمایش کمیته ها

                        } else {

                        }
                    }
                }
            }
            ///////////////////////////////

            //دکمه ابلاغ برای مسئول ابلاغ
            if (row.get(0).get(_status).equals(status_confirmationFinal)) {

                html2.append("<div class='row'>");
                boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
                if (accEdt) {
                    html2.append("<div class=\"col-lg-2\">");
                    html2.append("<button id='edit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.m_edit(" + id + ");' >ثبت تغییرات</button>");
                    html2.append("</div>");
                }
                boolean accRequestEdt = Access_User.hasAccess(request, db, rul_requestEdit);
                if (accRequestEdt) {
                    html2.append("<div class=\"col-lg-2\">");
                    html2.append("<button id='requestEdit_MyPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisMyPlans.requestEditMyPlans(" + id + ");' >درخواست ویرایش </button>");
                    html2.append("</div>");
                }
                boolean accCommunicatedBySupervisor = Access_User.hasAccess(request, db, rul_communicatedBySupervisor);
                if (accCommunicatedBySupervisor) {
                    html2.append("<div class=\"col-lg-4\">");
                    html2.append("<button  id='communicatorTosupervisor_MyPlans' class='btn btn-outline-success  btn-block mg-b-10'  onclick='hmisMyPlans.communicatedBySupervisor(" + id + ");' >تایید وارسال به مسئول پایش (واحد بهبود کیفیت)</button>");
                    html2.append("</div>");
                }
                boolean accIgnoreBySupervisor = Access_User.hasAccess(request, db, rul_ignoreBySupervisor);
                if (accIgnoreBySupervisor) {
                    html2.append("<div class=\"col-lg-2\">");
                    html2.append("<button id='ignoreSupervisor_MyPlans' class='btn btn-outline-danger btn-block mg-b-10'  onclick='hmisMyPlans.ignoreBySupervisor(" + id + ");' >رد برنامه </button>");
                    html2.append("</div>");
                }
                html2.append("</div>");
            }

            DefaultTableModel dtm = db.Select(Steps.tableName, Steps._plansId + "=" + id);
            List<Map<String, Object>> stepsRow = jjDatabase.separateRow(dtm);

            html3.append(
                    "<div class=\"table-wrapper\">\n");
            html3.append(
                    "<div align=\"right\">");
            html3.append(
                    "<table id='refreshTblSteps' class=\"table table-responsive\"><thead>");
            html3.append(
                    "<th width='5%'>کد</th>");
            html3.append(
                    "<th width='20%'>عنوان گام </th>");
            html3.append(
                    "<th width='10%'>درصد تکمیل گام</th>");
            html3.append(
                    "<th width='10%'> مسئول اجرا</th>");
            html3.append(
                    "<th width='10%'>مسئول پیگیری</th>");
            html3.append(
                    "<th width='10%'>تاریخ شروع</th>");
            html3.append(
                    "<th width='10%'>تاریخ پایان</th>");
            html3.append(
                    "<th width='10%'>هزینه</th>");
            html3.append(
                    "<th width='5%'>شاخص دستیابی</th>");
            html3.append(
                    "<th width='5%'>وضعیت</th>");
            html3.append(
                    "<th width='5%'>عملیات</th>");
            html3.append(
                    "</thead><tbody>");
            for (int i = 0;
                    i < stepsRow.size();
                    i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";
                String trackerRoleName = "";

                if (!stepsRow.get(i).get(Steps._executorRoleId).equals("")) {
                    String executorRoleId = stepsRow.get(i).get(Steps._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("ExecutorRoleName=" + ExecutorRoleName);
                    }
                }
                if (!stepsRow.get(i).get(Steps._executorUserId).equals("")) {
                    if (stepsRow.get(i).get(Steps._executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = stepsRow.get(i).get(Steps._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            System.out.println("ExecutorUserName=" + ExecutorUserName);
                        }
                    }
                }

                float percent = 0;
                percent = Float.parseFloat(stepsRow.get(i).get(Steps._percent).toString()) * (Float.parseFloat(stepsRow.get(i).get(Steps._progressPercent).toString()) / 100);

                html3.append("<tr class='mousePointer " + Sessions.getClassCssForVaziat(stepsRow.get(i).get(Steps._status).toString()) + "'>");
                html3.append("<td class='c'>" + stepsRow.get(i).get(Steps._id) + "</td>");
                html3.append("<td class='r'>" + (stepsRow.get(i).get(Steps._title).toString()) + "</td>");
                if (!stepsRow.get(i).get(Steps._status).toString().equals(Steps.status_initialRegistration)) {//زمانی که وضعیت گام ثبت اولیه نبود ستون پیشرفت گام را نمایش دهد
                    html3.append("<td class='r'>" + percent + "%</td>");
                } else {
                    html3.append("<td class='r'><div></div></td>");
                }
                html3.append("<td class='r'>" + ExecutorRoleName + ExecutorUserName + "</td>");
                String trackerRoleId = stepsRow.get(i).get(Steps._trackerId).toString();
                trackerRoleName = Role.getRoleUserName(trackerRoleId, db);
                html3.append("<td class='r'>" + trackerRoleName + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._startDate).toString()) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._endDate).toString()) + "</td>");
                html3.append("<td class='r'>" + jjNumber.getFormattedNumber(stepsRow.get(i).get(Steps._cost).toString()) + "</td>");
                html3.append("<td class='r'>" + (stepsRow.get(i).get(Steps._otherIndicators).toString()) + "</td>");
                html3.append("<td class='r'>" + (stepsRow.get(i).get(Steps._status).toString()) + "</td>");
                Pattern pj = Pattern.compile("(^" + row.get(0).get(_improveQualityId).toString() + ",)|(," + row.get(0).get(_improveQualityId).toString() + ",)");
                Pattern pj1 = Pattern.compile("(^" + row.get(0).get(_supervisorRoleId).toString() + ",)|(," + row.get(0).get(_supervisorRoleId).toString() + ",)");

//                Pattern pj = Pattern.compile("" + row.get(0).get(_improveQualityId).toString() + ",");
//                Pattern pj1 = Pattern.compile("" + row.get(0).get(_supervisorRoleId).toString() + ",");
                String RoleId = jjTools.getSeassionUserRole(request);
                Matcher mj = pj.matcher(RoleId);
                Matcher mj1 = pj1.matcher(RoleId);
                if ((row.get(0).get(_status).equals(status_confirmationSupervisor) && mj.find()) || (row.get(0).get(_status).equals(status_confirmationFinal) && mj1.find())) {
                    html3.append("<td class='c'  onclick='hmisMyPlans.selectStepsInMyPlans(" + stepsRow.get(i).get(Steps._id) + "," + id + ");' ><i class='icon ion-ios-gear-outline'></i></td>");
                } else {
                    html3.append("<td class='c'><div></div></td>");
                }
                html3.append("</tr>");
            }

            html3.append(
                    "</tbody></table>");
            html3.append(
                    "</div>");
            html3.append(
                    "</div>");

            //////////////////////////////////////////////////////////////
            script += Js.setHtml("#downloadMyPlansFileDiv", html5);
            script += Js.setHtml("#myPlans_button", html2);
            script += Js.table("#refreshStepsInMyPlans", "300", 0, "", "جلسات");
            script += Js.setHtml("#tblStepsMyPlans", html3);
            script += html.toString();
            script += "hmisProprietaryTarget.selectOptionProprietaryTarget(" + row.get(0).get(_hugeGoal) + ");";
            script += "hmisProprietaryTarget.selectOptionStrategy(" + row.get(0).get(_minorGoal) + ");";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * رد کردن برنامه توسط مافوق در قسمت برنامه های عملیاتی من
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String ignoreBySupervisor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ignoreBySupervisor)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی برنامه
            String script = "";
            String result = changeStatus(request, db, id, status_ignoreBySupervisor);//رد
            Map<String, Object> map = new HashMap<>();
            map.put(Plans._description, jjTools.getParameter(request, Plans._description));
            db.update(Plans.tableName, map, _id + "=" + id);
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "برنامه عملیاتی رد شد";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisMyPlans.m_select(" + id + ");";//انتخاب برنامه عملیاتی من
                script += "hmisMyPlans.m_refresh();";//رفرش برنامه های عملیاتی من
            } else {
                String errorMessageing = "برنامه عملیاتی رد نشد";
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
     * درخواست ویرایش توسط مسئول پایش ومافوق
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String requestEditMyPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_requestEdit)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی برنامه
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            String script = "";
            String result = changeStatus(request, db, id, status_initialRegistration);//وضعیت برنامه به ثبت اولیه تغییر  می کند
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "درخواست ویرایش انجام شد";
                String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_requestEditingthePlan_name);
                if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                    String text = tice_configMessage;
                    Messenger.sendMesseage(null, db, row.get(0).get(_creatorId).toString(), "1", "sms,app,email", "", "برنامه را ویرایش نمایید", text, "", "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                } else {
                    String text = "برنامه عملیاتی با عنوان " + row.get(0).get(_title) + "برای ویرایش در کارتابل شما قرار گرفته است لطفا برای ویرایش و تایید اقدام نمایید";
                    Messenger.sendMesseage(null, db, row.get(0).get(_creatorId).toString(), "1", "sms,app,email", "", "برنامه را ویرایش نمایید", text, "", "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                }

                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisMyPlans.m_refresh();";//رفرش برنامه های عملیاتی من
                script += "hmisMyPlans.m_show_tbl();";//رفرش برنامه های عملیاتی من

            } else {
                String errorMessageing = "درخواست ویرایش ارسال نشد";
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
     * درخواست ویرایش توسط مدیر وتغییر وضعیت به ثبت اولیه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String requestEditManagerPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_requestEdtByManager)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی برنامه

            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            String script = "";
            String result = changeStatus(request, db, id, status_initialRegistration);//وضعیت برنامه به ثبت اولیه تغییر  می کند
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "درخواست ویرایش بدرستی ارسال شذ";
                 String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_requestEditingthePlan_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        String text = tice_configMessage;
                        Messenger.sendMesseage(null, db, row.get(0).get(_creatorId).toString(), "1", "sms,app,email", "", "برنامه را ویرایش نمایید", text, "", "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                } else {
                    String text = "برنامه عملیاتی با عنوان " + row.get(0).get(_title) + "برای ویرایش در کارتابل شما قرار گرفته است لطفا برای ویرایش و تایید اقدام نمایید";
                    Messenger.sendMesseage(null, db, row.get(0).get(_creatorId).toString(), "1", "sms,app,email", "", "برنامه را ویرایش نمایید", text, "", "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                }

                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisManagerPlans.m_refresh();";//رفرش برنامه های عملیاتی من
                script += "hmisManagerPlans.m_show_tbl();";//رفرش برنامه های عملیاتی من

            } else {
                String errorMessageing = "درخواست ویرایش ارسال نشد";
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
     * رد برنامه توسط مسئول بهبوود کیفیت
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String ignoreByImproveQuality(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);//  ای دی جلسات
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(Plans._description, jjTools.getParameter(request, Plans._description));
            db.update(Plans.tableName, map, _id + "=" + id);
            String result = changeStatus(request, db, id, status_ignoreByImproveQuality);//رد
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "برنامه عملیاتی رد شد";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisMyPlans.m_select(" + id + ")";//انتخاب برنامه عملیاتی من
                script += "hmisMyPlans.m_refresh()";//رفرش برنامه های عملیاتی من
            } else {
                String errorMessageing = "برنامه عملیاتی رد نشد";
                script += Js.modal(errorMessageing, "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////////برنامه های عملیاتی مدیر///////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * برنامه های عملیاتی مدیر
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshManagerPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsManagerPlans)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm;
            String otherSelect = "";
            if (Access_User.hasAccess(request, db, rul_rfsAllManagerPlans)) {
                otherSelect = "SELECT"
                        + " P.id,"
                        + "P.plans_title"
                        + ",P.plans_date"
                        + ",P.plans_status"
                        + ",P.plans_supervisorRolId"
                        + ",P.plans_managerRoleId"
                        + ",P.plans_improveQualityId"
                        + " FROM hmis_plans P"
                        + " WHERE  (P.plans_status='" + status_confirmByImproveQuality + "' OR  P.plans_status='" + status_confirmByManager + "' ) ";

            } else {
                String roles = jjTools.getSeassionUserRole(request).toString();
                String[] roleArray = roles.split(",");
                String condition1 = "";
                String condition2 = "";
                for (int i = 0; i < roleArray.length; i++) {
                    System.out.println("role" + roleArray[i]);
                    condition1 += " plans_managerRoleId =" + roleArray[i] + " OR";
                }
                otherSelect = "SELECT"
                        + " P.id,"
                        + "P.plans_title"
                        + ",P.plans_date"
                        + ",P.plans_status"
                        + ",P.plans_supervisorRolId"
                        + ",P.plans_managerRoleId"
                        + ",P.plans_improveQualityId"
                        + " FROM hmis_plans P"
                        + " WHERE  (P.plans_status='" + status_confirmByImproveQuality + "' OR  P.plans_status='" + status_confirmByManager + "' ) "
                        + "  AND ( " + condition1.substring(0, condition1.length() - 2) + " ) ";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            int sessionUserId = jjTools.getSeassionUserId(request);// ای دی فرد وارد شونده
            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">برنامه عملیاتی/</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\"> لیست برنامه های عملیاتی  من</div>\n");
            html.append(""
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'><div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisManagerPlans.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");

            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshManagerPlans' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان برنامه عملیاتی</th>");
            html.append("<th width='10%'>مدیر بیمارستان</th>");
            html.append("<th width='15%'> مافوق</th>");
            html.append("<th width='15%'>مسئول پایش(بهبود کیفیت)</th>");
            html.append("<th width='20%'>تاریخ شروع</th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='40%'>انتقال به میز هوشمند</th>");
            html.append("</thead><tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect(otherSelect));

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + row.get(i).get(Plans._id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Plans._title) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_managerRoleId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_supervisorRoleId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleUserName(row.get(i).get(_improveQualityId).toString(), db) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(Plans._date)) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Plans._status) + "</td>");
                html.append("<td class='r' onclick='hmisManagerPlans.m_select(" + row.get(i).get(Plans._id) + ")'>"
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
                panel = "swManagerPlansTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshManagerPlans", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));

            return "";
        }
    }

    public static String selectManagerPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);// ای دی برنامه عملیاتی
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
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            if (row.get(0).get(_typeOfProgram).equals("برنامه بهبود کیفیت")) {
                html.append("$('#ManagerPlanImprovementDiv').slideDown();");
            } else {
                html.append("$('#ManagerPlanImprovementDiv').slideUp();");
            }
            String script = "";
            html.append(Js.setVal("#hmis_Managerplans_id", row.get(0).get(_id)));
            html.append(Js.setVal("#ManagerPlans_title", row.get(0).get(_title)));
            html.append(Js.setVal("#ManagerPlans_typeOfProgram", row.get(0).get(_typeOfProgram)));
            html.append(Js.setVal("#ManagerPlans_range", row.get(0).get(_range)));
            html.append(Js.setValSummerNote("#ManagerPlans_description", row.get(0).get(_description)));
            html.append(Js.setVal("#ManagerPlans_causeProblem", row.get(0).get(_causeProblem)));
            html.append(Js.setValSelectOption("#ManagerPlans_strategic", row.get(0).get(_strategic).toString()));
            html.append(Js.select2("#ManagerPlans_strategic", " width: '100%'"));
            html.append(Js.setValSelectOption("#ManagerPlans_minorGoal", row.get(0).get(_minorGoal).toString()));
            html.append(Js.select2("#ManagerPlans_minorGoal", " width: '100%'"));
            html.append(Js.setVal("#ManagerPlans_domain", row.get(0).get(_domain)));
            html.append(Js.setVal("#ManagerPlans_department", row.get(0).get(_department)));
            html.append(Js.setValSelectOption("#ManagerPlans_domain", row.get(0).get(_domain).toString()));
            html.append(Js.select2("#ManagerPlans_domain", " width: '100%'"));
            html.append(Js.setValSelectOption("#ManagerPlans_department", row.get(0).get(_department).toString()));
            html.append(Js.select2("#ManagerPlans_department", " width: '100%'"));
            html.append(Js.setValSelectOption("#ManagerPlans_hugeGoal", row.get(0).get(_hugeGoal).toString()));
            html.append(Js.select2("#ManagerPlans_hugeGoal", " width: '100%'"));
            html.append(Js.setVal("#ManagerPlans_method", row.get(0).get(_method)));
            html.append(Js.setVal("#ManagerPlans_strategic", row.get(0).get(_strategic)));
            html.append(Js.setVal("#ManagerPlans_status", row.get(0).get(_status)));
            html.append(Js.setVal("#ManagerPlans_hugeGoal", row.get(0).get(_hugeGoal)));
            html.append(Js.setVal("#ManagerPlans_minorGoal", row.get(0).get(_minorGoal)));
            html.append(Js.setVal("#ManagerPlans_titleOfTheProblem", row.get(0).get(_titleOfTheProblem)));
            html.append(Js.setVal("#ManagerPlans_thePeriodAssess", row.get(0).get(_period)));
            html.append(Js.setHtml("#ManagerPlansStatusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            html.append("$('#ManagerPlans_improveQualityId').val([" + row.get(0).get(_improveQualityId) + "]);$('#ManagerPlans_improveQualityId').select2({  width: '100%'});");
            html.append(Js.setHtml("#ManagerPlans_supervisorRolId", Role.getRoleName(row.get(0).get(_supervisorRoleId).toString(), db)));
            html.append(Js.setValSummerNote("#ManagerPlans_description", row.get(0).get(_description).toString()));
            html.append(Js.setHtml("#ManagerPlansStatusLog", (row.get(0).get(_statusLog).toString()).replaceAll("%23A%23", "<br/>")));
            html.append(Js.setHtml("#titleManagerPlans", (row.get(0).get(_title).toString())));
            html.append(Js.setHtml("#periodManagerPlans", row.get(0).get(_period)));//دوره پایش
//////////////////////////////////////////////////

            List<Map<String, Object>> proprietaryRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + row.get(0).get(_minorGoal)));
            if (proprietaryRow.size() > 0) {
                html.append(Js.setHtml("#minorGoalManagerPlans", proprietaryRow.get(0).get(ProprietaryTarget._title)));
            }
            List<Map<String, Object>> totalRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + row.get(0).get(_hugeGoal)));
            if (totalRow.size() > 0) {
                html.append(Js.setHtml("#hugeGoalManagerPlans", totalRow.get(0).get(TotalTarget._title)));
            }
            List<Map<String, Object>> strategyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._id + "=" + row.get(0).get(_strategic)));
            if (strategyRow.size() > 0) {
                html.append(Js.setHtml("#strategicManagerPlans", strategyRow.get(0).get(Strategy._title)));
            }
//////////////////////////////////////////////////

            html.append(Js.setHtml("#improveQualityRoleIdManagerPlans", Role.getRoleName(row.get(0).get(_improveQualityId).toString(), db)));//مسول پایش
            html.append(Js.setHtml("#rangeManagerPlans", row.get(0).get(_range)));//حیطه
            html.append(Js.setHtml("#titleManagerPlans", row.get(0).get(_title)));//حیطه
            List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.Select(Department.tableName, Department._id + "=" + row.get(0).get(_domain)));
            html.append(Js.setHtml("#domainManagerPlans", departmentRow.get(0).get(Department._title).toString()));//دامنه

            //تایید توسط مدیر
            if (row.get(0).get(_status).equals(status_confirmByImproveQuality)) {
                boolean accIgnoreByManager = Access_User.hasAccess(request, db, rul_ignorByManager);
                if (accIgnoreByManager) {
                    html2.append("<div class=\"col-lg-3\">");
                    html2.append("<button id='ignore_ManagerPlans' class='btn btn-outline-danger btn-block mg-b-10'  onclick='hmisManagerPlans.ignoreByManager(" + id + ");' >رد برنامه</button>");
                    html2.append("</div>");
                }
                boolean accConfirmByManager = Access_User.hasAccess(request, db, rul_confirmByManager);
                if (accConfirmByManager) {
                    html2.append("<div class=\"col-lg-3\">");
                    html2.append("<button  id='confirm_ManagerPlans' class='btn btn-outline-success  btn-block mg-b-10'  onclick='hmisManagerPlans.confirmByManager(" + id + ");' >تایید برنامه وابلاغ گام ها</button>");
                    html2.append("</div>");
                }

                boolean accRequestEdtByManager = Access_User.hasAccess(request, db, rul_requestEdtByManager);
                if (accRequestEdtByManager) {
                    html2.append("<div class=\"col-lg-3\">");
                    html2.append("<button id='requestEdit_ManagerPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisManagerPlans.requestEditManagerPlans(" + id + ");' >درخواست ویرایش</button>");
                    html2.append("</div>");
                }
                boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
                if (accEdt) {
                    html2.append("<div class=\"col-lg-3\">");
                    html2.append("<button id='edit_ManagerPlans' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisManagerPlans.m_edit(" + id + ");' >ثبت تغییرات</button>");
                    html2.append("</div>");
                }
            }

            DefaultTableModel dtm = db.Select(Steps.tableName, Steps._plansId + "=" + id + "");
            List<Map<String, Object>> stepsRow = jjDatabase.separateRow(dtm);
            html3.append("<div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshStepsInManagerPlans' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th width='5%'>کد</th>");
            html3.append("<th width='10%'>عنوان گام</th>");
            html3.append("<th width='10%'>شخص مسئول اجرا</th>");
            html3.append("<th width='10%'>سمت مسئول اجرا</th>");
            html3.append("<th width='10%'>مسئول پیگیری</th>");
            html3.append("<th width='15%'>تاریخ شروع </th>");
            html3.append("<th width='15%'>تاریخ پایان </th>");
            html3.append("<th width='10%'>درصد گام از کل</th>");
            html3.append("<th width='15%'>شاخص ارزیابی</th>");
            html3.append("<th width='15%'>وضعیت</th>");
            html3.append("<th width='15%'>هزینه</th>");
            html3.append("<th width='15%'>ابلاغ</th>");
            html3.append("<th width='40%'>ویرایش</th>");
            html3.append("</thead><tbody>");
            boolean accCommunicatedSteps = Access_User.hasAccess(request, db, rul_communicatedStep);

            for (int i = 0; i < stepsRow.size(); i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";
                html3.append("<tr class='mousePointer " + Sessions.getClassCssForVaziat(stepsRow.get(i).get(Steps._status).toString()) + "'>");
                html3.append("<td class='c'>" + stepsRow.get(i).get(Steps._id) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._title) + "</td>");
                if (!stepsRow.get(i).get(Steps._executorRoleId).equals("")) {
                    String executorRoleId = stepsRow.get(i).get(Steps._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    System.out.println("executorRoleId=" + executorRoleId);
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("executorRoleIdArray=" + executorRoleIdArray[j]);
                    }
                }
                if (!stepsRow.get(i).get(Steps._executorUserId).equals("")) {
                    if (stepsRow.get(i).get(Steps._executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = stepsRow.get(i).get(Steps._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            System.out.println("executorRoleIdArray=" + ExecutorUserName);
                        }
                    }
                }
                html3.append("<td class='r'>" + ExecutorUserName + "</td>");
                html3.append("<td class='r'>" + ExecutorRoleName + "</td>");
                html3.append("<td class='r'>" + Role.getRoleUserName(stepsRow.get(i).get(Steps._trackerId).toString(), db) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._startDate)) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._endDate)) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._percent) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._otherIndicators) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._status) + "</td>");
                html3.append("<td class='r'>" + jjNumber.getFormattedNumber(stepsRow.get(i).get(Steps._cost).toString()) + "</td>");
                if (row.get(0).get(_status).equals(status_confirmByManager) && stepsRow.get(i).get(Steps._status).equals(Steps.status_initialRegistration)) {//دکمه ابلاغ زمانی فعال می شود که مدیر برنامه عملیاتی را تایید کرده باشد
                    if (accCommunicatedSteps) {
                        html3.append("<td class='r'><button class='btn btn-outline-success  btn-block mg-b-10' onclick='hmisSteps.communicatedSteps(" + stepsRow.get(i).get(Steps._id) + ");'>ابلاغ گام ها</button></td>");
                    }
                } else {
                    html3.append("<td class='r'><div>--</div></td>");
                }
                if (Access_User.hasAccess(request, db, rul_rfsAllManagerPlans)) {
                    if ((row.get(0).get(_status).equals(status_confirmByImproveQuality))) {
                        html3.append("<td class='r' onclick='hmisManagerPlans.selectStepsInManagerPlans(" + stepsRow.get(i).get(Steps._id) + "," + id + ");' ><i class='icon ion-gear-a' style='color:#a02311'></i></td>");

                    } else {
                        html3.append("<td class='c'><div>--</div></td>");
                    }
                } else {
                    Pattern pj = Pattern.compile("(^" + row.get(0).get(_managerRoleId).toString() + ",)|(," + row.get(0).get(_managerRoleId).toString() + ",)");
                    String RoleId = jjTools.getSeassionUserRole(request);
                    Matcher mj = pj.matcher(RoleId);
                    if ((row.get(0).get(_status).equals(status_confirmByImproveQuality) && mj.find())) {
                        html3.append("<td class='r' onclick='hmisManagerPlans.selectStepsInManagerPlans(" + stepsRow.get(i).get(Steps._id) + "," + id + ");' ><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                    } else {
                        html3.append("<td class='c'><div>--</div></td>");
                    }
                }

                html3.append("</tr>");
            }
            html3.append("</tbody></table>");
            if (stepsRow.size() > 0) {// اگر گامی وجود داشت
                if (row.get(0).get(_status).equals(status_confirmByManager)) {//دکمه ابلاغ زمانی فعال می شود که مدیر برنامه عملیاتی را تایید کرده باشد
                    if (accCommunicatedSteps) {
                        html2.append("<div class=\"col-lg-12\">");
                        html2.append("<button id='communicateAll_communicatedSteps' class='btn btn-outline-warning btn-block mg-b-10'  onclick='hmisSteps.communicateStepsAll(" + id + ");' >ابلاغ همه گامها </button>");
                        html2.append("</div>");
                    }
                }
            }
            ///////////////////////////////////files///////////////////
            html.append(Js.setVal("#filesManagerPlans", row.get(0).get(_files)));
            String attachFilesSessions = row.get(0).get(_files).toString();
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
                        html4.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesSessionsArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesSessionsArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesSessionsArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            //////////////////////////////////////////////////////////////
            script += Js.setHtml("#downloadManagerPlansFileDiv", html4);
            script += Js.setHtml("#ManagerPlans_button", html2);
            script += Js.setHtml("#tblStepsManagerPlans", html3);
//            script += Js.table("#refreshStepsInManagerPlans", "300", 0, "", "جلسات");
            script += html.toString();
            script += "hmisProprietaryTarget.selectOptionProprietaryTarget(" + row.get(0).get(_hugeGoal) + ");";
            script += "hmisProprietaryTarget.selectOptionStrategy(" + row.get(0).get(_minorGoal) + ");";

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * سلکت پایش برنامه ها گام های مربوطه نمایش داده می شود
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectPlansForAssess(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);// ای دی برنامه عملیاتی
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
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            String script = "";
            html.append(Js.setVal("#hmis_plansForAssess_id", row.get(0).get(_id)));

            DefaultTableModel dtm = db.Select(Steps.tableName, Steps._plansId + "=" + id + "");
            List<Map<String, Object>> stepsRow = jjDatabase.separateRow(dtm);
            html3.append("<div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshAssessForPlans' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th width='5%'>کد</th>");
            html3.append("<th width='10%'>عنوان گام</th>");
            html3.append("<th width='10%'> مسئول اجرا</th>");
            html3.append("<th width='10%'>مسئول پیگیری</th>");
            html3.append("<th width='15%'>تاریخ شروع </th>");
            html3.append("<th width='15%'>تاریخ پایان </th>");
            html3.append("<th width='10%'>درصد گام از کل</th>");
            html3.append("<th width='10%'>درصد پیشرفت گام</th>");
            html3.append("<th width='15%'>شاخص ارزیابی</th>");
            html3.append("<th width='15%'>وضعیت</th>");
            html3.append("<th width='15%'>وضعیت گام</th>");
            html3.append("<th width='15%'>هزینه</th>");
            html3.append("<th width='40%'>پایش</th>");
            html3.append("</thead><tbody>");

            for (int i = 0; i < stepsRow.size(); i++) {
                String ExecutorRoleName = "";
                String ExecutorUserName = "";
                html3.append("<tr class='mousePointer " + Sessions.getClassCssForVaziat(stepsRow.get(i).get(Steps._status).toString()) + "'>");
                html3.append("<td class='c'>" + stepsRow.get(i).get(Steps._id) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._title) + "</td>");
                if (!stepsRow.get(i).get(Steps._executorRoleId).equals("")) {
                    String executorRoleId = stepsRow.get(i).get(Steps._executorRoleId).toString();
                    String[] executorRoleIdArray = executorRoleId.split(",");
                    System.out.println("executorRoleId=" + executorRoleId);
                    for (int j = 0; j < executorRoleIdArray.length; j++) {
                        ExecutorRoleName += Role.getRoleUserName(executorRoleIdArray[j], db) + ",";
                        System.out.println("executorRoleIdArray=" + executorRoleIdArray[j]);
                    }
                }
                if (!stepsRow.get(i).get(Steps._executorUserId).equals("")) {
                    if (stepsRow.get(i).get(Steps._executorUserId).equals("ALL")) {
                        ExecutorUserName = "تمام کاربران ثبت شده";
                    } else {
                        String executorUserId = stepsRow.get(i).get(Steps._executorUserId).toString();
                        String[] executorUserIdArray = executorUserId.split(",");
                        for (int j = 0; j < executorUserIdArray.length; j++) {
                            ExecutorUserName += Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            System.out.println("executorRoleIdArray=" + ExecutorUserName);
                        }
                    }
                }

                String isActive = stepsRow.get(i).get(Steps._isActive).equals("0") ? "غیر نهایی" : "نهایی";
                html3.append("<td class='r'>" + ExecutorUserName + ExecutorRoleName + "</td>");
                html3.append("<td class='r'>" + Role.getRoleUserName(stepsRow.get(i).get(Steps._trackerId).toString(), db) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._startDate)) + "</td>");
                html3.append("<td class='r'>" + jjCalendar_IR.getViewFormat(stepsRow.get(i).get(Steps._endDate)) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._percent) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._progressPercent) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._otherIndicators) + "</td>");
                html3.append("<td class='r'>" + stepsRow.get(i).get(Steps._status) + "</td>");
                html3.append("<td class='r'>" + isActive + "</td>");
                html3.append("<td class='r'>" + jjNumber.getFormattedNumber(stepsRow.get(i).get(Steps._cost).toString()) + "</td>");
                if (Access_User.hasAccess(request, db, rul_rfsAllPlansForAssess)) {
                    if ((row.get(0).get(_status).equals(status_confirmByManager))) {
//                        if (Access_User.hasAccess(request, db, Steps.rul_edtStepInPlansForAssess)) {
                        html3.append("<td class='r' onclick='hmisSteps.selectStepsInPlansForAssess(" + stepsRow.get(i).get(Steps._id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
//                        }
                    } else {
                        html3.append("<td class='c'><div>--</div></td>");
                    }
                } else {
                    //اگر وضعیت برنامه ابلاغ شده باشد و مسئول بهبود کیفیت وارد شده  با شد می تواند به ویرایش گام دسترسی داشته باشد
                    Pattern pj = Pattern.compile("(^" + row.get(0).get(_improveQualityId).toString() + ",)|(," + row.get(0).get(_improveQualityId).toString() + ",)");

                    String RoleId = jjTools.getSeassionUserRole(request);
                    Matcher mj = pj.matcher(RoleId);
                    if ((row.get(0).get(_status).equals(status_confirmByManager) && mj.find())) {
                        html3.append("<td class='r' onclick='hmisSteps.selectStepsInPlansForAssess(" + stepsRow.get(i).get(Steps._id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
                    } else {
                        html3.append("<td class='c'><div>--</div></td>");
                    }
                }
//اگر وضعیت برنامه ابلاغ شده باشد و مسئول بهبود کیفیت وارد شده  با شد می تواند به ویرایش گام دسترسی داشته باشد
                Pattern pj = Pattern.compile("(^" + row.get(0).get(_improveQualityId).toString() + ",)|(," + row.get(0).get(_improveQualityId).toString() + ",)");

                String RoleId = jjTools.getSeassionUserRole(request);
                Matcher mj = pj.matcher(RoleId);
                if ((row.get(0).get(_status).equals(status_confirmByManager) && mj.find())) {
                    html3.append("<td class='r' onclick='hmisSteps.selectStepsInPlansForAssess(" + stepsRow.get(i).get(Steps._id) + ");' ><i class='icon ion-compose' style='color:#a02311'></i></td>");
                } else {
                    html3.append("<td class='c'><div>--</div></td>");
                }

                html3.append("</tr>");
            }
            html3.append("</tbody></table>");

            //////////////////////////////////////////////////////////////
            script += Js.setHtml("#plansForAssess_button", html2);
            script += Js.setHtml("#tblPlansForAssess", html3);
            script += html.toString();

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * رد توسط مدیر
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String ignoreByManager(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ignorByManager)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی برنامه عملیاتی
            String script = "";
            Map<String, Object> map = new HashMap<>();
            map.put(Plans._description, jjTools.getParameter(request, Plans._description));
            db.update(Plans.tableName, map, _id + "=" + id);
            String result = changeStatus(request, db, id, status_ignorByManager);//رد
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "برنامه عملیاتی رد شد";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisManagerPlans.m_select(" + id + ")";//انتخاب برنامه عملیاتی مدیر
                script += "hmisManagerPlans.m_refresh()";//رفرش برنامه های عملیاتی مدیر
            } else {
                String errorMessageing = "برنامه عملیاتی رد نشد";
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
     * تایید توسط مدیر تایید برنامه عملیاتی در تب ابلاغ برنامه ها توسط مدیر
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String confirmByManager(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_confirmByManager)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);//  ای دی جلسات
            String script = "";
            String result = changeStatus(request, db, id, status_confirmByManager);//ابلاغ
            if (result.isEmpty()) {//اگر خطایی نبود
                String errorMessageing = "تایید و ارسال بدرستی انجام شد گام های برنامه را ابلاغ نمایید";
                script += Js.modal(errorMessageing, "پیام سامانه");
                script += "hmisManagerPlans.m_select(" + id + ");";
            } else {
                String errorMessageing = "تایید انجام نشد";
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
     * پایش برنامه ها
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshPlansForAssess(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfsPlansForAssess)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            boolean accRulRef = Access_User.hasAccess(request, db, rul_rfsPlansForAssess);//
            boolean accRulRefAll = Access_User.hasAccess(request, db, rul_rfsAllPlansForAssess);//

            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String html2 = "";
            DefaultTableModel dtm;
            String select = "";
            if (accRulRefAll) {
                select = "SELECT * FROM hmis_plans WHERE " + _status + "='" + status_confirmByManager + "'";
            } else {
                String RoleId = jjTools.getSeassionUserRole(request);//
                if (RoleId != "") {
                    String[] RoleIdArray = jjTools.getSeassionUserRole(request).split(",");//
                    String like = "";
                    for (int i = 0; i < RoleIdArray.length; i++) {
                        System.out.println("role" + RoleIdArray[i]);
                        like += " plans_improveQualityId   LIKE '%" + RoleIdArray[i] + "%' OR";
                    }
                    select = "SELECT * FROM hmis_plans WHERE " + _status + "='" + status_confirmByManager + "' AND " + like.substring(0, like.length() - 2);
                } else {
                }
            }
            dtm = db.otherSelect(select);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">برنامه عملیاتی/بهبود</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + ""
                    + "<div class=\"card-header bg-primary tx-white\">پایش برنامه ها</div>\n");
            html.append(""
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size:33px'><i class='fa fa-refresh mg-t-30' onclick='hmisPlansForAssess.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
            );
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<div align=\"right\">");
            html.append("<table id='refreshPlansForAssess' class='table display responsive' ><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان</th>");
            html.append("<th width='5%'>بخش /واحد</th>");
            html.append("<th width='10%'>مسئول ایجاد برنامه</th>");
            html.append("<th width='15%'>مسئول پایش(واحد بهبود کیفیت)</th>");
            html.append("<th width='10%'>هدف کلان</th>");
            html.append("<th width='10%'>هدف اختصاصی</th>");
            html.append("<th width='10%'>استراتژی</th>");
            html.append("<th width='10%'>نوع برنامه عملیاتی </th>");
            html.append("<th width='5%'>تاریخ ایجاد</th>");
            html.append("<th width='5%'>وضعیت</th>");
            html.append("<th width='20%'>درصد پیشرفت</th>");
            html.append("<th width='5%'>پایش</th>");
            html.append("</thead><tbody>");

            for (int i = 0; i < row.size(); i++) {
                List<Map<String, Object>> strategyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._id + "=" + row.get(i).get(_strategic)));
                List<Map<String, Object>> totalTargetRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + row.get(i).get(_hugeGoal)));
                List<Map<String, Object>> proprietaryTargetRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + row.get(i).get(_minorGoal)));
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.Select(Department.tableName, Department._title, Department._id + "=" + row.get(i).get(_department)));
                float percent = 0;
                List<Map<String, Object>> StepsRow = jjDatabaseWeb.separateRow(db.Select(Steps.tableName, Steps._plansId + "=" + row.get(i).get(_id) + " AND steps_status!='" + Steps.status_initialRegistration + "'"));
                if (StepsRow.size() > 0) {
                    for (int j = 0; j < StepsRow.size(); j++) {//جمع درصد تکمیل گام
                        percent += Float.parseFloat(StepsRow.get(j).get(Steps._percent).toString()) * (Float.parseFloat(StepsRow.get(j).get(Steps._progressPercent).toString()) / 100);
                    }
                }

                System.out.println("percent" + percent);
                html.append("<tr onclick='hmisPlansForAssess.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + (departmentRow.get(0).get(Department._title).toString()) + "</td>");
                html.append("<td class='r'>" + Access_User.getUserName(row.get(i).get(_creatorId).toString(), db) + "</td>");
                html.append("<td class='r'>" + Role.getRoleName(row.get(i).get(_improveQualityId).toString(), db) + "</td>");
                if (totalTargetRow.size() > 0) {
                    html.append("<td class='r'>" + (totalTargetRow.get(0).get(TotalTarget._title).toString()) + "</td>");
                } else {
                    html.append("<td class='r'>-</td>");
                }
                if (proprietaryTargetRow.size() > 0) {
                    html.append("<td class='r'>" + (proprietaryTargetRow.get(0).get(ProprietaryTarget._title).toString()) + "</td>");
                } else {
                    html.append("<td class='r'>-</td>");
                }
                if (strategyRow.size() > 0) {
                    html.append("<td class='r'>" + (strategyRow.get(0).get(Strategy._title)) + "</td>");
                } else {
                    html.append("<td class='r'>-</td>");
                }

                html.append("<td class='r'>" + (row.get(i).get(_typeOfProgram).toString()) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='r'>" + percent + "%</td>");
                html.append("<td class='c'><i class=\"icon ion-compose\" style=\"color:#a02311\"></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div>");
            html.append("</div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swPlansForAssessTbl";
            }
            html2 += Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPlansForAssess", "300", 0, "", "پایش برنامه های عملیاتی");
//            }else {
//                html2 += Js.modal("وارد شوید", "پیام سامانه");
//            }

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String downloadPlans(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, Sessions._id);// ای دی جلسه

            request.setAttribute("db", db);
            System.out.println("------->>>>>template/planPrint.jsp");
            request.getRequestDispatcher("template/planPrint.jsp").forward(request, response);

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * گزارش برنامه عملیاتی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String report(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String script = "";
            List<Map<String, Object>> row = jjDatabase.separateRow(
                    db.otherSelect("SELECT hmis_proprietaryTarget.proprietaryTarget_title,"
                            + "department.department_title,hmis_strategy.strategy_title,plans_status,"
                            + "hmis_totalTarget.totalTarget_title,plans_title,hmis_plans.id "
                            + " FROM hmis_plans"
                            + " LEFT JOIN hmis_totalTarget ON plans_hugeGoal=hmis_totalTarget.id"
                            + " LEFT JOIN hmis_proprietaryTarget ON plans_minorGoal=hmis_proprietaryTarget.id"
                            + " LEFT JOIN hmis_strategy ON plans_strategic=hmis_strategy.id"
                            + " LEFT JOIN department ON plans_department=department.id"
                            + " WHERE " + _hugeGoal + "=" + jjTools.getParameter(request, _hugeGoal) + " AND  "
                            + "" + _minorGoal + "=" + jjTools.getParameter(request, _minorGoal) + " AND " + _strategic + "=" + jjTools.getParameter(request, _strategic)
                    ));

            if (row.size() > 0) {
                html.append("<div class=\"table-wrapper\">\n");
                html.append("<div align=\"right\">");
                html.append("<table id='reportPlan' class=\"table table-responsive\"><thead>");
                html.append("<th width='5%'>کد</th>");
                html.append("<th width='10%'>عنوان برنامه عملیاتی</th>");
                html.append("<th width='10%'>عنوان هدف کلی </th>");
                html.append("<th width='10%'>عنوان هدف اختصاصی </th>");
                html.append("<th width='10%'>عنوان استراتژی </th>");
                html.append("<th width='10%'>عنوان بخش </th>");
                html.append("<th width='20%'>درصد پیشرفت</th>");
                html.append("<th width='20%'>وضعیت</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    float percent = 0;
                    List<Map<String, Object>> StepsRow = jjDatabaseWeb.separateRow(db.Select(Steps.tableName, Steps._plansId + "=" + row.get(i).get(_id) + " AND steps_status!='" + Steps.status_initialRegistration + "'"));
                    if (StepsRow.size() > 0) {

                        for (int j = 0; j < StepsRow.size(); j++) {//جمع درصد تکمیل گام
                            percent += Float.parseFloat(StepsRow.get(j).get(Steps._percent).toString()) * (Float.parseFloat(StepsRow.get(j).get(Steps._progressPercent).toString()) / 100);
                        }
                    }
                    html.append("<tr>");
                    html.append("<td>" + row.get(i).get(_id) + "</td>");
                    html.append("<td>" + row.get(i).get(_title) + "</td>");
                    html.append("<td>" + row.get(i).get(TotalTarget._title) + "</td>");
                    html.append("<td>" + row.get(i).get(ProprietaryTarget._title) + "</td>");
                    html.append("<td>" + row.get(i).get(Strategy._title) + "</td>");
                    html.append("<td>" + row.get(i).get(Department._title) + "</td>");
                    html.append("<td >"
                            + "<div class=\"progress\">\n"
                            + "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=" + percent + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + percent + "%;\">\n"
                            + "" + percent + "%\n"
                            + "</div>\n"
                            + "</div>"
                            + "</td>");
                    html.append("<td>" + row.get(i).get(_status) + "</td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table>");
                html.append("</div>");
                html.append("</div>");
                script += Js.setHtml("#reportPlansTbl", html);
            } else {
                script += Js.modal("برنامه عملیاتی با این موارد موجود نیست", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش فرم پیشنهاددات که ای دیوی که قرار است این فرم در آن قرار بگیرد را
     * می گیرد وخودش یک فرم با ای دی رندوم ایجاد می کند با یک سری script
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String offerForm(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        String DivId = jjTools.getParameter(request, "DivId");
        String charRandom = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 5);//ایجاد کد رندم
        String script = "";
        String[] rolesId = jjTools.getSeassionUserRole(request).split(",");
        String roleName = "";
        for (int i = 0; i < rolesId.length; i++) {
            roleName += Role.getRoleName(rolesId[i], db) + "/";
        }

        try {
            html.append(""
                    + "<div id='" + charRandom + "'>"
                    + "  <div class='card-header bg-primary tx-white'>میتوانید با توجه به وضعیت شاخص به کمیته ها و کارگروه های مرتبط پیشنهاد اقدام اصلاحی بدهید</div>"
                    + "<p>این پیشنهادات در جلسه کمیته بررسی و در صورت صلاحدید نظر اعضای کمیته تصویب میشود</p>"
                    + "<div class='col-lg-12 card-body pd-sm-30'>"
                    + "<div class='card bd-primary mg-t-3'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-4'>"
                    + "پیشنهاد دهنده"
                    + "<input class='form-control' value='" + Access_User.getUserName(String.valueOf(jjTools.getSeassionUserId(request)), db) + "' name='approved_offererId' id='" + charRandom + "_offererId'  disabled='disabled' placeholder='کسی که این پیشنهاد را داده برای ذخیره در آرشیو اقدام های اصلاحی' type='text' value='رضا سعیدی - مسئول کنترل عفونت'>"
                    + "</div>"
                    + "<div class='col-lg-4'>"
                    + " عنوان مسئله"
                    + "<input id='" + charRandom + "_text1'  class='form-control title' placeholder='عنوان مسئله' type='text'>"
                    + "</div>"
                    + "<div class='col-lg-4'>"
                    + " علت مسئله"
                    + "<input class='form-control title' placeholder='علت مسئله'  id='" + charRandom + "_text2'  type='text'>"
                    + "</div>"
                    + "<div class='col-lg-12'>"
                    + " اقدام اصلاحی"
                    + "<input class='form-control title' id='" + charRandom + "_text3'  placeholder='پیشنهاد واضح شما برای اصلاح که در کمیته تصویب یا رد می شود' type='text'>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-lg-6'>"
                    + "انتخاب سمت مسئول اجرا"
                    + "<select id='" + charRandom + "_executorRoleId'  multiple='' name='approved_executorRoleId' class='form-control' >"
                    + "</select>"
                    + "</div>"
                    + "<div class='col-lg-6'>"
                    + "انتخاب اشخاص مسئول اجرا "
                    + "<select id='" + charRandom + "_executorUserId' multiple='' name='approved_executorUserId' class='form-control'>"
                    + "</select>"
                    + "</div>"
                    + "<div class='row col-lg-12'>"
                    + "<div class='col-lg-3'>"
                    + "مسئول پیگیری:"
                    + "<select class='form-control' id='" + charRandom + "_trackerId' name='approved_trackerId'>"
                    + "</select>"
                    + "</div>"
                    + "<div class='col-lg-3'>"
                    + "تاریخ شروع:"
                    + "<div class='form-group has-success mg-b-0'>"
                    + "<input class='form-control is-valid' id='" + charRandom + "_startDate' name='approved_startDate' placeholder='تاریخ را وارد کنید' type='text'>"
                    + "</div>"
                    + "</div>"
                    + "<div class='col-lg-3'>"
                    + " تاریخ پایان:"
                    + "<div class='form-group has-success mg-b-0'>"
                    + "<input class='form-control is-valid' id='" + charRandom + "_endDate' name='approved_endDate' placeholder='تاریخ را وارد کنید' type='text'>"
                    + "</div>"
                    + "</div>"
                    + "<div class='col-lg-3'>"
                    + "انتخاب کمیته"
                    + "<select class='form-control commettesTitle' id='" + charRandom + "_commettes' name='approved_commettesId'>"
                    + "</select>"
                    + "</div>"
                    + "</div>"
                    + "<div class='col-lg-12' style=''>"
                    + "<div class='col-lg-12'>بارگذاری مستندات :"
                    + "<div class='' id='showFilesApproved" + charRandom + "Div'></div>"
                    + "</div>"
                    + "<div class='input-group col-lg-12'>"
                    + "<div class=''> عنوان فایل</div>"
                    + "'<span id='user_pic' class='form-control'></span>"
                    + "'<input class='form-control' id='approved_titleFile1" + charRandom + "' placeholder='فایل شما با این عنوان در سامانه ذخیره میشود' type='text' >"
                    + "'<input id='attachFileApproved" + charRandom + "' name='attachFileApproved" + charRandom + "'  style='display: none;' type='file'>"
                    + "'<input class='btn btn-primary' id='sendFilesApproved" + charRandom + "' type='submit' value='ارسال'>"
                    + "<span class='btn btn-primary' id='spanUploadFile" + charRandom + "'>انتخاب فایل</span>"
                    + "</div>"
                    + "<div id='fileApprovedDiv'></div>"
                    + "</div>"
                    + "<div class='col-lg-12 mg-t-20'>"
                    + "<textarea rows='3' id='" + charRandom + "_description' name='approved_description' class='form-control' placeholder='توضیحات برنامه عملیاتی'></textarea>"
                    + "</div>"
                    + "<div class='col-lg-3  mg-t-20'>"
                    + "<div class='form-group has-success mg-b-0'>"
                    + "<button id='cancel_" + charRandom + "' class='btn btn-secondary btn-block mg-b-10' >لغو</button>"
                    + "</div>"
                    + "</div>"
                    + "<div class='col-lg-3 mg-t-20'>"
                    + "<button class='btn btn-success btn-block mg-b-10' id='" + charRandom + "_communicatedOfferFormToCommettes' >ارسال به کمیته جهت بررسی</button>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "");

            script += Js.setHtml("#" + DivId, html);
            script += "new jj('#sendFilesApproved" + charRandom + "').jjAjaxFileUploadByTitleAndMultiFile('#attachFileApproved" + charRandom + "', 'approved_file', 'approved_titleFile1" + charRandom + "', '#showFilesApproved" + charRandom + "Div');\n"
                    + "hmisRole.getSelectOptionRequierd('#" + charRandom + " #" + charRandom + "_trackerId');\n"
                    + "hmisRole.getSelectOptionRequierd('#" + charRandom + " #" + charRandom + "_executorRoleId');\n"
                    + "cmsUser.getSelectOption('#" + charRandom + " #" + charRandom + "_executorUserId');\n"
                    + "hmisIndicatorCommettes.getSelectOptionIndicatorCommettes();\n"
                    + "new jj('#" + charRandom + " #" + charRandom + "_startDate').jjCalendarWithYearSelector(1370, 1420);\n"
                    + "new jj('#" + charRandom + " #" + charRandom + "_endDate').jjCalendarWithYearSelector(1370, 1420);\n"
                    + "$('#" + charRandom + " #cancel_" + charRandom + "').click(function(){\n"
                    + "$('#" + charRandom + "').slideUp();\n"
                    + "});\n"
                    + "$('#" + charRandom + "_description').summernote();\n"
                    + "$('#" + charRandom + " #" + charRandom + "_communicatedOfferFormToCommettes').click(function(){\n"
                    + "var param='';\n"
                    + " var flag = true;\n"
                    + "        if ($('#" + charRandom + "_trackerId').val() == null) {\n"
                    + "            $(\"#select2-" + charRandom + "_trackerId-container\").addClass(\"required\");\n"
                    + "            flag = false;\n"
                    + "        } else {\n"
                    + "            $(\"#select2-" + charRandom + "_trackerId-container\").removeClass('required');\n"
                    + "        }\n"
                    + "        if ($(\"#" + charRandom + "_startDate\").val() == \"\") {\n"
                    + "            $(\"#" + charRandom + "_startDate\").addClass(\"required\");\n"
                    + "            flag = false;\n"
                    + "        } else {\n"
                    + "            $(\"#" + charRandom + "_startDate\").removeClass('required');\n"
                    + "        }\n"
                    + "        if ($(\"#" + charRandom + "_endDate\").val() == \"\") {\n"
                    + "            $(\"#" + charRandom + "_endDate\").addClass(\"required\");\n"
                    + "            flag = false;\n"
                    + "        } else {\n"
                    + "            $(\"#" + charRandom + "_endDate\").removeClass('required');\n"
                    + "        }\n"
                    + "        if ($(\"#" + charRandom + "_executorUserId\").val() == null && $(\"#approved_executorRoleId\").val() == null) {\n"
                    + "            $(\"#select2-" + charRandom + "_executorUserId-container\").addClass(\"required\");\n"
                    + "            $(\"#select2-" + charRandom + "_executorRoleId-container\").addClass(\"required\");\n"
                    + "            flag = false;\n"
                    + "        } else {\n"
                    + "            $(\"#select2-" + charRandom + "_executorUserId-container\").removeClass('required');\n"
                    + "            $(\"#select2-" + charRandom + "_executorRoleId-container\").removeClass('required');\n"
                    + "        }\n"
                    + "\n"
                    + "        if (flag == true) {\n"
                    + "param +='&do=Plans.communicatedOfferFormToCommettes';\n"
                    + "var temp=$('#" + charRandom + " input.title');\n"
                    + "var temp2='';\n"
                    + "var temp3='';\n"
                    + " var approved_file = $('#showFilesApproved" + charRandom + "Div .approved_file');\n"
                    + "            for (var i = 0; i < approved_file.length; i++) {\n"
                    + "                temp3 += $(approved_file[i]).val() + \",\";\n"
                    + "            }\n"
                    + "            param += \"&approved_file=\" + temp3;\n"
                    + ""
                    + "for(var i=0;i<temp.length;i++){\n"
                    + "temp2 +=$(temp[i]).attr('value')+'%23A%23';\n"
                    + "}\n"
                    + "param += '&' + new jj('#" + charRandom + "').jjSerial();\n"
                    + "param +='&approved_title='+temp2;\n"
                    + "param +='&approved_description='+$('#" + charRandom + "_description').summernote('code');\n"
                    + "new jj(param).jjAjax2(false);\n"
                    + "}\n"
                    + "});\n"
                    + "$('#attachFileApproved" + charRandom + "').change(function(){\n"
                    + "$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\\\|/]/).pop());\n"
                    + "});\n"
                    + "$('#spanUploadFile" + charRandom + "').click(function(){\n"
                    + "$(this).parent().find('input[type=file]').click();\n"
                    + "});\n"
                    + "$('#" + charRandom + " #" + charRandom + "_executorRoleId').change(function(){\n"
                    + "$('#" + charRandom + " #" + charRandom + "_executorUserId').html('');\n"
                    + "cmsUser.getSelectOption('#" + charRandom + " #" + charRandom + "_executorUserId');\n"
                    + "});\n"
                    + "$('#" + charRandom + " #" + charRandom + "_executorUserId').change(function(){\n"
                    + "$('#" + charRandom + " #" + charRandom + "_executorRoleId').html('');\n"
                    + "hmisRole.getSelectOptionRequierd('#" + charRandom + " #" + charRandom + "_executorRoleId');\n"
                    + "});\n"
                    + " $('#" + charRandom + " #" + charRandom + "_commettes').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });\n"
                    + " $('#" + charRandom + " #" + charRandom + "_trackerId').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });\n"
                    + " $('#" + charRandom + " #" + charRandom + "_executorRoleId').select2({"
                    + "                    width: '100%'\n"
                    + "                });\n"
                    + " $('#" + charRandom + " #" + charRandom + "_executorUserId').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });\n"
                    + "";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * ترکیبی از حروف و عدد به صورت رندوم
     *
     * @param candidateChars
     * @param length
     * @return
     */
    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }

    /**
     * ابلاغ فرم پیشنهادی به کمیته انتخاب شده
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String communicatedOfferFormToCommettes(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_communicatedOfferFormToCommettes)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String script = "";
            if (!jjTools.getParameter(request, Approved._commettesId).equals("") && !jjTools.getParameter(request, Approved._commettesId).equals("null")) {
                List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + jjTools.getParameter(request, Approved._commettesId)));
                Map<String, Object> approvedMap = new HashMap<String, Object>();
                approvedMap.put(Approved._commettesId, jjTools.getParameter(request, Approved._commettesId));
                approvedMap.put(Approved._title, jjTools.getParameter(request, Approved._title).replaceAll("#A#", "%23A%23"));
                approvedMap.put(Approved._endDate, jjTools.getParameter(request, Approved._endDate).replaceAll("/", ""));
                approvedMap.put(Approved._startDate, jjTools.getParameter(request, Approved._startDate).replaceAll("/", ""));
                approvedMap.put(Approved._description, jjTools.getParameter(request, Approved._description));
                approvedMap.put(Approved._offererId, jjTools.getSeassionUserId(request));//ای دی پیشنهاد دهنده
                approvedMap.put(Approved._trackerId, jjTools.getParameter(request, Approved._trackerId));//باید مدیر باشد
                approvedMap.put(Approved._file, jjTools.getParameter(request, Approved._file));//باید مدیر باشد
                if (jjTools.getParameter(request, Approved._executorRoleId).equals("") || jjTools.getParameter(request, Approved._executorRoleId).equals("null")) {
                    approvedMap.put(Approved._executorUserId, jjTools.getParameter(request, Approved._executorUserId));//باید مدیر باشد که اگر قبول نئپداشت برنامه را بتواند آن را رد کند
                } else if (jjTools.getParameter(request, Approved._executorUserId).equals("") || jjTools.getParameter(request, Approved._executorUserId).equals("null")) {
                    approvedMap.put(Approved._executorRoleId, jjTools.getParameter(request, Approved._executorRoleId));//باید مدیر باشد که اگر قبول نئپداشت برنامه را بتواند آن را رد کند
                }
                approvedMap.put(Approved._status, Approved.status_offer);//وضعیت پیشنهاد شده
                approvedMap.put(Approved._statusLog, Approved.status_offer
                        + ":"
                        + "-"
                        + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                        + " user_id="
                        + jjTools.getSeassionUserId(request) + " "
                        + " role_id="
                        + jjTools.getSeassionUserRole(request)
                        + " "
                        + new jjCalendar_IR().getTimeFormat_8length()
                        + "%23A%23");
                DefaultTableModel dtm = db.insert(Approved.tableName, approvedMap);
                if (dtm.getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                } else {
                    StringBuilder html = new StringBuilder();
                    script += Js.modal("عنوان مصوبه پیشنهادی " + commettesRow.get(0).get(Commettes._title) + " ارسال گردید", "پیام سامانه");
                     String tice_configMessage = Tice_config.getValue(db,  Tice_config._config_offerFormtotheCommittee_name);
                    if (!tice_configMessage.equals("")) {//اگر در تنظیمات پیام ها چنین عنوانی  یافت شد اگر فعال بود پیامی که در تنظیمات ما تعریف شده را بیاور
                        String text = tice_configMessage;
                            Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(commettesRow.get(0).get(Commettes._secretary).toString(), db), "1", "sms,app,email", "", "دبیر کمیته،" + commettesRow.get(0).get(Commettes._title) + " برنامه عملیاتی با عنوان: " + jjTools.getParameter(request, Approved._title).toString().replaceAll("#A#", "-") + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else {
                        String text = "وارد برنامه مدیار شوید و مصوبه پیشنهاد شده را تایید کنید.";
                        Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(commettesRow.get(0).get(Commettes._secretary).toString(), db), "1", "sms,app,email", "", "دبیر کمیته،" + commettesRow.get(0).get(Commettes._title) + " برنامه عملیاتی با عنوان: " + jjTools.getParameter(request, Approved._title).toString().replaceAll("#A#", "-") + "به شما ابلاغ شده است ", text, html.toString(), "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }

                }

            } else {
                script += Js.modal(" کمیته را انتخاب نمایید", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
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
            String script = "";
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_steps "
                    + "LEFT JOIN hmis_plans ON hmis_steps.steps_plansId=hmis_plans.id WHERE  hmis_steps.steps_plansId=" + id + ""));//اگر گامی در این برنامه وجود داشته باشد برنامه قابل حذف نیست
            System.out.println("row.size()=" + row.size());
            if (row.size() == 0) {
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
//                script += Js.jjPlans.select(id);
                script += Js.jjPlans.refresh();
            } else {
                script += Js.modal("امکان حذف  برنامه وجود ندارد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getoptionRange(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        try {
            StringBuilder html3 = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            html.append("<option  style='color:black' value=''>انتخاب حیطه</option>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(Plans.tableName, _range));

            for (int i = row.size() - 1; i >= 0; i--) {

                html.append("<option value='" + row.get(i).get(_range).toString() + "'"
                        + (row.get(i).get(_range).toString().equals(_range) ? " selected='selected'>" : ">")
                        + row.get(i).get(_range).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)

            }
            if (panel == "") {
                panel = "plans_range";
            }
            script += Js.setHtml("#" + panel, html.toString());

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
}
