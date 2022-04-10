/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.cms.Content;
import cms.cms.Tice_config;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import jj.jjTime;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.mariuszgromada.math.mxparser.Argument;
//import org.mariuszgromada.math.mxparser.Expression;

/**
 *
 * @author Mohammad
 */
public class Indicators {

    public static final String tableName = "hmis_indicatiors";
    public static final String _id = "id";
    public static final String _code = "indicators_code";
    public static final String _title = "indicators_title";
    public static final String _icon = "indicators_icon";
    public static final String _type = "indicators_type";
    public static final String _scop = "indicators_scop";
    public static final String _department_id = "indicators_department_id";
    public static final String _dimension = "indicators_dimension";
    public static final String _priority = "indicators_priority";
    public static final String _difinition = "indicators_difinition";
    public static final String _collectingReason = "indicators_collectingReason";
    public static final String _comments = "indicators_comments";
    public static final String _startDate = "indicators_startDate";
    public static final String _periodOfCollectiong = "indicators_periodOfCollectiong";
    public static final String _periodOfAnalysis = "indicators_periodOfAnalysis";
    public static final String _resultAccessRols = "indicators_resultAccessRols";
    public static final String _resultAccessUsers = "indicators_resultAccessUsers";
    public static final String _responsibleUser = "indicators_responsibleUser";
    public static final String _responsibleRole = "indicators_responsibleRole";
    public static final String _isActive = "indicators_isActive";
    public static final String _sendSmsForIndicatorPeriod = "indicators_sendSmsForIndicatorPeriod";
    public static final String _sendSmsAfterInsertFormAnswerSet = "indicators_sendSmsAfterInsertFormAnswerSet";
    public static final String _isAutoWiki = "indicators_isAutoWiki";
    public static final String _source_a = "indicators_source_a";
    public static final String _form_a = "indicators_form_a";
    public static final String _question_a = "indicators_question_a";
    public static final String _filterQuestion_a = "indicators_filterQuestion_a";
    public static final String _filterOption_a = "indicators_filterOption_a";
    public static final String _a = "indicators_a";
    public static final String _source_b = "indicators_source_b";
    public static final String _form_b = "indicators_form_b";
    public static final String _question_b = "indicators_question_b";
    public static final String _filterQuestion_b = "indicators_filterQuestion_b";
    public static final String _filterOption_b = "indicators_filterOption_b";
    public static final String _b = "indicators_b";
    public static final String _source_c = "indicators_source_c";
    public static final String _form_c = "indicators_form_c";
    public static final String _question_c = "indicators_question_c";
    public static final String _filterQuestion_c = "indicators_filterQuestion_c";
    public static final String _filterOption_c = "indicators_filterOption_c";
    public static final String _c = "indicators_c";
    public static final String _source_d = "indicators_source_d";
    public static final String _form_d = "indicators_form_d";
    public static final String _question_d = "indicators_question_d";
    public static final String _filterQuestion_d = "indicators_filterQuestion_d";
    public static final String _filterOption_d = "indicators_filterOption_d";
    public static final String _d = "indicators_d";
    public static final String _source_e = "indicators_source_e";
    public static final String _form_e = "indicators_form_e";
    public static final String _question_e = "indicators_question_e";
    public static final String _filterQuestion_e = "indicators_filterQuestion_e";
    public static final String _filterOption_e = "indicators_filterOption_e";
    public static final String _e = "indicators_e";
    public static final String _source_f = "indicators_source_f";
    public static final String _form_f = "indicators_form_f";
    public static final String _question_f = "indicators_question_f";
    public static final String _filterQuestion_f = "indicators_filterQuestion_f";
    public static final String _filterOption_f = "indicators_filterOption_f";
    public static final String _f = "indicators_f";
    public static final String _source_g = "indicators_source_g";
    public static final String _form_g = "indicators_form_g";
    public static final String _question_g = "indicators_question_g";
    public static final String _filterQuestion_g = "indicators_filterQuestion_g";
    public static final String _filterOption_g = "indicators_filterOption_g";
    public static final String _g = "indicators_g";
    public static final String _goodFloor = "indicators_goodFloor";
    public static final String _goodTop = "indicators_goodTop";
    public static final String _warnningFloor = "indicators_warnningFloor";
    public static final String _warnningTop = "indicators_warnningTop";
    public static final String _dangerFloor = "indicators_dangerFloor";
    public static final String _dangerTop = "indicators_dangerTop";
    public static final String _formula = "indicators_formula";
    public static final String _ownerId = "indicators_ownerId";
//    public static final String _ownerRole = "indicators_ownerRole";
    public static final String _htmlContent = "indicators_htmlContent";
    public static final String _htmlContentWithWikiLinks = "indicators_htmlContentWithWikiLinks";

    public static final String lbl_insert = "ثبت شاخص";
    public static final String lbl_delete = "حذف شاخص";
    public static final String lbl_edit = "ثبت ویرایش";

    public static int rul_moduleIndicator = 481;//60;
    public static int rul_rfs = 482;//60;
    public static int rul_rfsAll = 0;//60;
    public static int rul_rfsInformation = 487;//60;
    public static int rul_rfs_own = 0;// 61;//فقط امکان دیدن فرم های ایجاد شده ی توسط خود ایجاد کننده را دارد // بر روی سمت
    public static int rul_ins = 483;// 62;
    public static int rul_insForOther = 0;// برای اینکه بتواند برای بقیه شاخص تعریف بکند @ToDo 
    public static int rul_edt = 484;// 63;
    public static int rul_dlt = 485;// 64;
    public static int rul_5 = 0;// 65;
    public static int rul_6 = 0;// 66;
    public static int rul_7 = 0;// 67;
    public static int rul_8 = 0;// 68;
    public static int rul_9 = 0;// 69;
    public static int rul_10 = 0;// 70;

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm = db.Select(tableName);//@ToDo فقط ستون هایی که لازم هست را بگیریم که در مصرف حاقظه رم سرفه جویی بشود
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            StringBuilder html = new StringBuilder();
            html.append("<div class='card-header bg-primary tx-white'>لیست شاخص های تعریف شده</div>\n");
            
            
            html.append("<div class='col-lg mg-10'>"
//                    + "<a href='#' class='sh-pagetitle-icon' style='float:right'>"
//                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisStrategic.m_refresh();'></i>"
//                    + "</div>"
//                    + "</a>"
                    + "<a href='#' id='learnFormsIcon' class='sh-pagetitle-icon' style='float:left' title='آموزش ماژول شاخص'>"
                    + "<div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>"
                    + "</div>"
                    + "</a>"
                    + "<span  style='display:block;' class='col-lg-2 mg-t-10'>"
                    + "<div id='forms_learn' style='display:none;text-align:left'>"
                    + "<a href='http://94.184.89.113/upload/learnHMIS/shakhes.mp4'>فیلم آموزشی</a>"
                    + "<br/>"
                    + "<a target='_blank' href=''>فایل آموزشی</a>"
                    + "</div>"
                    + "</span>"
                    + "</div>"
                    + "");

            
          
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append("<p class='mg-b-20 mg-sm-b-30'>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisIndicators.m_add_new();' >تعریف شاخص جدید</a>");
                html.append("</p>");
            }
            html.append("<div class='table-wrapper'>");
            html.append("<table id='refreshIndicators' class='table display responsive'><thead>");
            html.append("<th width='5%' class='r'>کد</th>");
            html.append("<th width='20%' class='r'>عنوان شاخص</th>");
            html.append("<th width='20%' class='c'>ویرایش و اصلاح</th>");
            html.append("<th width='20%' class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + (row.get(i).get(_code).toString().isEmpty() ? row.get(i).get(_id).toString() : row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='c'><i class='p icon ion-ios-gear' onclick='" + Js.jjIndicators.select(row.get(i).get(_id).toString()) + "' style='color:#ec570e!important'></i></td>");
                html.append("<td class='c'><a href='Server?do=Indicators.showResult&id=" + row.get(i).get(_id) + "' target='_blank' >"
                        + "<i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");            
            }
            html.append("</tbody></table>");
            html.append("</div>");
            html.append("</div>");       
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swIndicatorsTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshIndicators", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست شاخص ها");
             script += "$('#learnIndicatorIcon').click(function(){"
                    + "if($('#indicator_learn').css('display')=='none'){"
                    + "$('#indicator_learn').slideDown();"
                    + "}else{"
                    + "$('#indicator_learn').slideUp();"
                    + "}"
                    + "});";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * اطلاعات شاخص
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshIndicatorsInformation(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsInformation)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            System.out.println(">>>>>>>>UserRoles is:" + userRoleInsession);
            String where = " AND ((";
            String userRoles[] = userRoleInsession.split(",");
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%," + userRoles[i] + ",%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!jjTools.getSeassionUserRole(request).isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            where += Forms._accessessUsers + " like " + "'%," + userId + ",%'" + " OR ";
            where += Forms._accessessUsers + " like " + "'%ALL%' ) OR  ";
            where += "(" + Forms._accessessGroupId + "=',null,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "=',null,' AND ";
            where += Forms._accessessUsers + "=',null,' )"
                    + " AND ";
            where += Forms._isActive + "=1 AND ";
            where += " ( (" + Forms._expireDate + " > " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + ") ";
            where += " OR (";
            where += Forms._expireDate + " = " + jjCalendar_IR.getDatabaseFormat_8length(null, true); // تاریخ مساوی امروز باشد و
            where += " AND ";
            where += Forms._expireTime + " >= " + jjTime.getTime4lenth("") + ")"; // ساعت بزرگتر از ّساعت جاری باشد
            where += "))";
            where += "; ";

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + " where " + Forms._category_id + "='اطلاعات شاخص'  " + where));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            html.append("<table id='refreshIndicatorsInformation' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل یک فرم</th>");
            html.append("<th class='c'>اطلاعات تکمیل فرم</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + (!formRows.get(i).get(Forms._code).toString().isEmpty() ? "(" + formRows.get(i).get(Forms._code) + ") " : "") + formRows.get(i).get(Forms._id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='c'><i class='p icon fa fa-pencil' onclick='hmisIndicatorsInformation.refreshMyAnswerInIndicatorsInformation(" + formRows.get(i).get(Forms._id).toString() + ")'></i></td>");
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showInformationForm&formAnswers_formId=" + formRows.get(i).get(Forms._id) + "' target='_blank' >"
                        + "<i class='p fa fa-file-excel-o' ></i></a></td>");
                html.append("<td class='c'>"
                        + "<a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(Forms._id) + "' target='_blank' >"
                        + "<i class='p fa fa-bar-chart'></i></a>"
                        + "</td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            html.append("</div>");
            html.append("</div>");
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                script.append(Js.setHtml("#swIndicatorsInformationTbl", html));
                script.append(Js.table("#refreshIndicatorsInformation", "", 0, "", "لیست اخبار"));
                Server.outPrinter(request, response, script);
            }
            return "";

//            
//            
//            
//            
//            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
//                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
//                return "";
//            }
//            if (!Access_User.hasAccess(request, db, rul_rfsInformation)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
//            //@ToDo درست کردن کد های درسترسی برای قسمت هایی که کاربر فقط بتواند فرم های خودش را ببیند
//            String where;
//            if (!Access_User.hasAccess(request, db, Forms.rul_rfsAll)) {
//                where = "hmis_forms.id>0 AND " + Forms._ownerId + "=" + jjTools.getSeassionUserId(request);
//            } else {
//                where = "hmis_forms.id>0";
//            }
//            where += " AND " + Forms._category_id + "='اطلاعات شاخص'";
//            DefaultTableModel dtm = db.Select(Forms.tableName, Forms._id + "," + Forms._code + "," + Forms._title, where);
//
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
//            StringBuilder html = new StringBuilder();
//            html.append("<div class='card-header bg-primary tx-white'>لیست فرم مربوط به اطلاعات شاخص  </div>");
//            html.append("<div class='card-body pd-sm-30'>");
//            html.append("<div style='width: 100%; padding-left: -10px;'>"
//                    + "<div class='table-responsive'>");
//            html.append("<table id='refreshIndicatorsInformation' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'>"
//                    + "<thead>");
//            html.append("<th width='5%' class='r'>کد</th>");
//            html.append("<th width='20%' class='r'>عنوان فرم</th>");
//            html.append("<th width='20%' class='c'>تکمیل فرم</th>");
//            html.append("<th width='20%' class='c'>اطلاعات تکمیل فرم</th>");
//            html.append("<th width='20%' class='c'>آنالیز و آمار</th>");
//            html.append("</thead>"
//                    + "<tbody>");
//            int userId = jjTools.getSeassionUserId(request);
//            String userRoleInsession = jjTools.getSeassionUserRole(request);
//            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr  class='mousePointer'>");
//                html.append("<td class='r'>" + (row.get(i).get(Forms._code).toString().isEmpty() ? row.get(i).get(Forms._id).toString() : row.get(i).get(Forms._code).toString()) + "</td>");
//                html.append("<td class='r'>" + row.get(i).get(Forms._title) + "</td>");
//                String where1 = " WHERE (";
//                String userRoles[] = userRoleInsession.split(",");
//                for (int j = 0; j < userRoles.length; j++) {
//                    where1 += Forms._accessessRoles + " like " + "'%," + userRoles[j] + ",%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
//                }
//                if (!jjTools.getSeassionUserRole(request).isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
//                    where1 += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
//                }
//                where1 += Forms._accessessUsers + " like " + "'%," + userId + ",%'" + " OR ";
//                where1 += Forms._accessessUsers + " like " + "'%ALL%' ) AND  ";
//                where1 += Forms._isActive + "=1 AND ";
//                where1 += " ( (" + Forms._expireDate + " > " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + ") ";
//                where1 += " OR (";
//                where1 += Forms._expireDate + " = " + jjCalendar_IR.getDatabaseFormat_8length(null, true); // تاریخ مساوی امروز باشد و
//                where1 += " AND ";
//                where1 += Forms._expireTime + " >= " + jjTime.getTime4lenth("") + ")"; // ساعت بزرگتر از ّساعت جاری باشد
//                where1 += ")";
//                where1 += " AND ";
//                where1 += Forms._id + "=" + row.get(i).get(Forms._id) + " ";
//                where1 += "; ";
//                List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + where1));
//                if (formRows.size() > 0) {
//                    html.append("<td class='c'><i class='p icon fa fa-pencil'  style='color:#ec570e!important'  onclick='hmisIndicatorsInformation.refreshMyAnswerInIndicatorsInformation(" + formRows.get(0).get(Forms._id).toString() + ")" + "'></i></td>");//نمایش تکمیل فرم های این فرم
//                } else {
//                    html.append("<td>"
//                            + "<div></div>"
//                            + "</td>");
//                }
//                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showInformationForm&formAnswers_formId=" + row.get(i).get(Forms._id) + "' target='_blank' >"
//                        + "<i class='p fa fa-file-excel-o' ></i></a></td>");
//                html.append("<td class='c'>"
//                        + "<a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + row.get(i).get(Forms._id) + "' target='_blank' >"
//                        + "<i class='p fa fa-bar-chart'></i></a>"
//                        + "</td>");
//                html.append("</tr>");
//            }
//            html.append("</tbody></table>"
//                    + "</div>");
//            html.append("</div>");
//            html.append("</div>");
//            String height = jjTools.getParameter(request, "height");
//            String panel = jjTools.getParameter(request, "panel");
//            if (!jjNumber.isDigit(height)) {
//                height = "";
//            }
//            if (panel.equals("")) {
//                panel = "swIndicatorsInformationTbl";
//            }
//            String script = Js.setHtml("#" + panel, html.toString());
//            script += Js.table("#refreshIndicatorsInformation", height, 0, Access_User.getAccessDialog(request, db, Forms.rul_ins).equals("") ? "2" : "", "لیست فرم ها");
//            Server.outPrinter(request, response, script);
//            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش گزارش اطلاعات شاخص در ماژول اطلاعات شاخص
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMyAnswerInIndicatorsInformation(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, FormAnswerSet.rul_rfs_own)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder html = new StringBuilder();
            String formId = jjTools.getParameter(request, FormAnswerSet._formId);
            List<Map<String, Object>> FormTitleRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title + "," + Forms._uniqueComplete, Forms._id + "=" + formId));
            if (FormTitleRow.isEmpty()) {
                Server.outPrinter(request, response, Js.modal("تاریخ پر کردن فرم تمام شده یا شما به این فرم دسترسی ندارد", "فرم یافت نشد"));
                return "";
            }
            //ویژگی : اگر فردی در سمتی یک فرم را پر کند و فرد دیگری در آن سمت قرار گیرد میتواند فرم تکمیل شده ی فرم قبلی را ببیند همچنین مدیران با دسترسی بالاتر میبینند
            String where = " WHERE " + FormAnswerSet._formId + "=" + formId + " AND (" + FormAnswerSet._userId + "=" + userId;
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userRoles[] = userRoleInsession.split(",");
            if (!userRoleInsession.isEmpty()) {// اگر کاربر ر سیستم نقشی دارد
                for (int i = 0; i < userRoles.length; i++) {
                    where += " OR " + FormAnswerSet._userRole + " = " + userRoles[i];// ممکن است کاربر چند تا تقش داشته باشد
                }
            }
            where += ")";
            DefaultTableModel dtm = db.JoinLeft(FormAnswerSet.tableName, Role.tableName,
                    FormAnswerSet.tableName + "." + FormAnswerSet._id + ","
                    + FormAnswerSet._date + ","
                    + FormAnswerSet._time + ","
                    + FormAnswerSet._status + ","
                    + FormAnswerSet._userName + ","
                    + FormAnswerSet._userMAC + ","
                    + FormAnswerSet._userIPV6 + ","
                    + Role._title, FormAnswerSet._userRole, Role._id, where);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<p class='mg-b-20 mg-sm-b-30'>");
            html.append("عنوان :" + FormTitleRow.get(0).get(Forms._title));
            boolean accIns = Access_User.hasAccess(request, db, FormAnswerSet.rul_ins);
            //ویژگی : اگر کاربر قبلا دسترسی داشته و اکنون دسترسی اش برای فرم خاصی برداشته شده یا نقشش عوض شده که به فرمی دسترسی ندارد دیگر دکمهی یجدید را نمی بیند
            //تشخیص دسترسی نقش یا شخص کاربر برای تکمیل این فرم---------------------------------------------------
            where = " WHERE (";
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%" + userRoles[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!userRoleInsession.isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            String userGroup[] = userGroupInsession.split(",");
            for (int i = 0; i < userGroup.length; i++) {
                where += Forms._accessessGroupId + " like " + "'%" + userGroup[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!userGroupInsession.isEmpty()) {// اگر کاربر جاری گروه در سیستم دارد
                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            where += Forms._accessessUsers + " like " + "'%" + userId + "%'" + " OR ";
            where += Forms._accessessUsers + " like " + "'%ALL%'" + " OR ";
            where += Forms._accessessUsers + " = '' ) OR  ";
            where += "(" + Forms._accessessGroupId + "=',null,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "=',null,' AND ";
            where += Forms._accessessUsers + "=',null,' )AND ";
            where += Forms._id + "=" + jjTools.getParameter(request, FormAnswerSet._formId) + "  AND  ";
            where += Forms._isActive + "=1 ; ";
            //--------------------------------------------------------------------------------------------------------------------
            if (accIns && db.otherSelect("SELECT * FROM " + Forms.tableName + where).getRowCount() > 0) {//و اگر نقش یا شخص این کاربر موجود در سشن به این فرم دسترسی داشته باشد  دکمه جدید را نشان بدهد
                html.append("<br/>");
                html.append("<a id='btnBackInIndicators' style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisIndicatorsInformation.m_show_tbl();'  target='_blank'>"//دکمه برگشت به عقب
                        + "<i class='p icon fa fa-arrow-right'></i></a>");
                html.append("<br/>");
                html.append("<br/>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' target='_blank' "
                        + " onclick='hmisIndicatorsInformation.refreshMyAnswersAfterQuestion(" + formId + ");' "
                        + ">تکمیل یک نمونه فرم جدید</a> ");
            }

            html.append("</p>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            if (!row.isEmpty()) {
                html.append("<table id='refreshMyAnswerInIndicatorsInformation' class='table display responsive' class='tahoma10' style='direction: rtl'><thead>");
                html.append("<th width='5%' class='r'>کد</th>");
                html.append("<th width='20%' class='c'>نام تکمیل کننده</th>");
                html.append("<th width='20%' class='c'>سمت</th>");
                html.append("<th width='20%' class='c'>تاریخ</th>");
                html.append("<th width='20%' class='c'>ساعت</th>");
                html.append("<th width='20%' class='c'>mac/ip</th>");
                html.append("<th width='20%' class='c'>ویرایش</th>");
//                html.append("<th width='20%' class='c'>آمار و نتایج</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr  class='mousePointer " + FormAnswerSet.getClassByStatus(row.get(i).get(FormAnswerSet._status).toString()) + "' >");
                    html.append("<td class='r'>" + row.get(i).get(FormAnswerSet._id) + "</td>");
                    html.append("<td class='r'>" + row.get(i).get(FormAnswerSet._userName) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(Role._title) + "</td>");
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(FormAnswerSet._date)) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(FormAnswerSet._time) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(FormAnswerSet._userMAC) + "<br/>" + row.get(i).get(FormAnswerSet._userIPV6) + "</td>");
                    html.append("<td class='c'><a  target='_blank'"
                            + " onclick='hmisIndicatorsInformation.selectMyAnswersAfterQuestion(" + formId + "," + row.get(i).get(FormAnswerSet._id).toString() + " );' "
                            + "><i class='p icon fa fa-pencil' style='color:#ec570e!important'></i></a></td>");
//                    html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.select(row.get(i).get(FormAnswerSet._id).toString()) + "'></i></td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table>");
                html.append("</div>");
                html.append("</div>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "swIndicatorsInformationForm";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshMyAnswerInIndicatorsInformation", "", 0, Access_User.getAccessDialog(request, db, FormAnswerSet.rul_ins).equals("") ? "2" : "", "لیست فرم ها پر شدهی در دسترس شما");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, ex.getMessage());
            return ex.getMessage();
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, _id + "=" + jjTools.getSeassionUserId(request)));//برای استخراج نام و نام خانوادگی کاربری که در سشن فعال است
            script.append(Js.setVal(_ownerId, jjTools.getSeassionUserId(request)));
            script.append(Js.setVal("#indicators_ownerName", userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()));
//            String userRolesOptions = Role.getUeserRolesSelectOption(request, response, db, true);
//            if (!userRolesOptions.isEmpty()) {// ممکن است کاربر جاری نقشی در سیستم نداشته باشد ولی دسترسی هایی داشته باشد
//                script.append(Js.setHtml("#" + _ownerRole, userRolesOptions));
//            } else {
//                script.append(Js.setVal("#" + _ownerRole, "<option value=''></option>"));
//            }
//            script.append(Js.select2("#" + _ownerRole, "width:'100%'"));
            if (Access_User.hasAccess(request, db, rul_ins)) {
                script.append(Js.setHtml("#indicator_buttons", "<div class='col-lg-6'><input type='button' id='insert_indicators_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success btn-block mg-b-10'></div>"));
                script.append(Js.click("#insert_indicators_new", Js.jjIndicators.insert()));
            }
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     *
     *
     * @param request
     * @param response
     * @param db
     * @param needString
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
            if (jjTools.getSeassionUserId(request) <= 0) {
                String script = "alert('برای دسترسی به این قسمت باید وارد شوید');";
                script += "new jj('index.html').jjGo();";
                Server.outPrinter(request, response, script);
                return "";
            }
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap();
            map.put(_source_a, jjTools.getParameter(request, _source_a));
            map.put(_form_a, jjTools.getParameter(request, _form_a));
            map.put(_question_a, jjTools.getParameter(request, _question_a));
            map.put(_filterQuestion_a, jjTools.getParameter(request, _filterQuestion_a));
            map.put(_filterOption_a, jjTools.getParameter(request, _filterOption_a));
            map.put(_a, jjTools.getParameter(request, _a));

            map.put(_source_b, jjTools.getParameter(request, _source_b));
            map.put(_form_b, jjTools.getParameter(request, _form_b));
            map.put(_question_b, jjTools.getParameter(request, _question_b));
            map.put(_filterQuestion_b, jjTools.getParameter(request, _filterQuestion_b));
            map.put(_filterOption_b, jjTools.getParameter(request, _filterOption_b));
            map.put(_b, jjTools.getParameter(request, _b));

            map.put(_source_c, jjTools.getParameter(request, _source_c));
            map.put(_form_c, jjTools.getParameter(request, _form_c));
            map.put(_question_c, jjTools.getParameter(request, _question_c));
            map.put(_filterQuestion_c, jjTools.getParameter(request, _filterQuestion_c));
            map.put(_filterQuestion_c, jjTools.getParameter(request, _filterQuestion_c));
            map.put(_c, jjTools.getParameter(request, _c));

            map.put(_source_d, jjTools.getParameter(request, _source_d));
            map.put(_form_d, jjTools.getParameter(request, _form_d));
            map.put(_question_d, jjTools.getParameter(request, _question_d));
            map.put(_filterQuestion_d, jjTools.getParameter(request, _filterQuestion_d));
            map.put(_filterOption_d, jjTools.getParameter(request, _filterOption_d));
            map.put(_d, jjTools.getParameter(request, _d));

            map.put(_source_e, jjTools.getParameter(request, _source_e));
            map.put(_form_e, jjTools.getParameter(request, _form_e));
            map.put(_question_e, jjTools.getParameter(request, _question_e));
            map.put(_filterQuestion_e, jjTools.getParameter(request, _filterQuestion_e));
            map.put(_filterOption_e, jjTools.getParameter(request, _filterOption_e));
            map.put(_e, jjTools.getParameter(request, _e));

            map.put(_source_f, jjTools.getParameter(request, _source_f));
            map.put(_form_f, jjTools.getParameter(request, _form_f));
            map.put(_question_f, jjTools.getParameter(request, _question_f));
            map.put(_filterQuestion_f, jjTools.getParameter(request, _filterQuestion_f));
            map.put(_filterOption_f, jjTools.getParameter(request, _filterOption_f));

            map.put(_f, jjTools.getParameter(request, _f));

            map.put(_source_g, jjTools.getParameter(request, _source_g));
            map.put(_form_g, jjTools.getParameter(request, _form_g));
            map.put(_question_g, jjTools.getParameter(request, _question_g));
            map.put(_filterQuestion_g, jjTools.getParameter(request, _filterQuestion_g));
            map.put(_filterOption_g, jjTools.getParameter(request, _filterOption_g));
            map.put(_g, jjTools.getParameter(request, _g));

            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_collectingReason, jjTools.getParameter(request, _collectingReason));
            map.put(_comments, jjTools.getParameter(request, _comments));
            map.put(_dangerFloor, jjTools.getParameter(request, _dangerFloor));
            map.put(_dangerTop, jjTools.getParameter(request, _dangerTop));
            map.put(_department_id, !jjNumber.isDigit(jjTools.getParameter(request, _department_id)) ? "1" : jjTools.getParameter(request, _department_id));// اگر بخش را مشخص نکرده بود پیشفرض می رود در بخش اول که بهبود کیفیت است
            map.put(_difinition, jjTools.getParameter(request, _difinition));
            map.put(_dimension, jjTools.getParameter(request, _dimension));
            map.put(_formula, jjTools.getParameter(request, _formula));
            map.put(_goodFloor, jjTools.getParameter(request, _goodFloor));
            map.put(_goodTop, jjTools.getParameter(request, _goodTop));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            map.put(_htmlContentWithWikiLinks, Content.ConvertToWiki(request, jjTools.getParameter(request, _htmlContent), db, false));
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_sendSmsAfterInsertFormAnswerSet, jjTools.getParameter(request, _sendSmsAfterInsertFormAnswerSet));
            map.put(_sendSmsForIndicatorPeriod, jjTools.getParameter(request, _sendSmsForIndicatorPeriod));
            map.put(_isAutoWiki, jjTools.getParameter(request, _isAutoWiki));
            map.put(_ownerId, jjTools.getSeassionUserId(request));
//            System.out.println("ownerid="+jjTools.getSeassionUserId(request));
//            map.put(_ownerRole, jjTools.getParameter(request, _ownerRole));
            map.put(_startDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _startDate), true));
            map.put(_periodOfAnalysis, jjTools.getParameter(request, _periodOfAnalysis));
            map.put(_periodOfCollectiong, jjTools.getParameter(request, _periodOfCollectiong));
            map.put(_priority, jjTools.getParameter(request, _priority));
            if (!jjTools.getParameter(request, _responsibleRole).isEmpty() || jjTools.getParameter(request, _responsibleRole).equals("null")) {// مسئول فقط یک نقش یا یک فرد است و هر دو در هر زمان نمیتواند پر باشد
                map.put(_responsibleRole, jjTools.getParameter(request, _responsibleRole));
                map.put(_responsibleUser, "");
            } else {
                map.put(_responsibleUser, jjTools.getParameter(request, _responsibleUser));
                map.put(_responsibleRole, "");
            }
            map.put(_resultAccessRols, jjTools.getParameter(request, _resultAccessRols));
            map.put(_scop, jjTools.getParameter(request, _scop));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_type, jjTools.getParameter(request, _type));
            map.put(_warnningFloor, jjTools.getParameter(request, _warnningFloor));
            map.put(_warnningTop, jjTools.getParameter(request, _warnningTop));

//            String needToAutoWiki = jjTools.getParameter(request, _hasAutoWikiInContent);
//            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
//                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _htmlContent), db, false);
//                map.put(_htmlContentWithWikiLinks, autoWikeContent);
//            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
//                map.put(_htmlContentWithWikiLinks, jjTools.getParameter(request, _htmlContent));
////                map.put(_autoWikIsUpdate, jjTools.getParameter(request, _autoWikIsUpdate));
//            }
            List<Map<String, Object>> insertedIndicatorRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            StringBuilder script = new StringBuilder();
            if (insertedIndicatorRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            script.append(Js.jjIndicators.refresh());
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (jjTools.getSeassionUserId(request) <= 0) {
                String script = "alert('برای دسترسی به این قسمت باید وارد شوید');";
                script += "new jj('index.html').jjGo();";
                Server.outPrinter(request, response, script);
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, Js.modal("شما اجازه ی دسترسی به این قسمت را ندارید", "انقضای جلسه"));
                return "";
            }
            String html = "";
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> indicatorRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (indicatorRow.isEmpty()) {
                String errorMessage = "فرم مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            script.append(Js.setVal("#" + tableName + "_id", indicatorRow.get(0).get(_id).toString()));
            script.append(Js.setVal("#" + _source_a, indicatorRow.get(0).get(_source_a).toString()));
            script.append(Js.setVal("#" + _form_a, indicatorRow.get(0).get(_form_a).toString()));
            script.append(Js.select2("#" + _form_a, ""));
            script.append(Js.setHtml("#" + _a, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_a).toString(), db)));
            script.append(Js.setVal("#" + _a, indicatorRow.get(0).get(_a).toString()));
            script.append(Js.select2("#" + _a, ""));
            if (!indicatorRow.get(0).get(_form_a).toString().equals("")) {
                String formId_a = indicatorRow.get(0).get(_form_a).toString();
                String question_a = FormQuestions.getHtmlFilterQuestion(formId_a, db);//بدست آوردن سوالات فرم 
                String option_a = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_a).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_a, option_a));
                script.append(Js.setHtml("#" + _filterQuestion_a, question_a));
                script.append(Js.setVal("#" + _filterQuestion_a, indicatorRow.get(0).get(_filterQuestion_a).toString()));
                script.append(Js.select2("#" + _filterQuestion_a, ""));
                script.append(Js.setVal("#" + _filterOption_a, indicatorRow.get(0).get(_filterOption_a).toString()));
                script.append(Js.select2("#" + _filterOption_a, ""));
            }
            if (!indicatorRow.get(0).get(_form_b).toString().equals("")) {
                script.append(Js.setVal("#" + _source_b, indicatorRow.get(0).get(_source_b).toString()));
                script.append(Js.setVal("#" + _form_b, indicatorRow.get(0).get(_form_b).toString()));
                script.append(Js.select2("#" + _form_b, ""));
                script.append(Js.setHtml("#" + _b, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_b).toString(), db)));
                script.append(Js.setVal("#" + _b, indicatorRow.get(0).get(_b).toString()));
                script.append(Js.select2("#" + _b, ""));

                String formId_b = indicatorRow.get(0).get(_form_b).toString();
                String question_b = FormQuestions.getHtmlFilterQuestion(formId_b, db);//بدست آوردن سوالات فرم 
                String option_b = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_b).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_b, option_b));
                script.append(Js.setHtml("#" + _filterQuestion_b, question_b));
                script.append(Js.setVal("#" + _filterOption_b, indicatorRow.get(0).get(_filterOption_b).toString()));
                script.append(Js.select2("#" + _filterOption_b, ""));
                script.append(Js.setVal("#" + _filterQuestion_b, indicatorRow.get(0).get(_filterQuestion_b).toString()));
                script.append(Js.select2("#" + _filterQuestion_b, ""));
            }
            if (!indicatorRow.get(0).get(_form_c).toString().equals("")) {
                script.append(Js.setVal("#" + _source_c, indicatorRow.get(0).get(_source_c).toString()));
                script.append(Js.setVal("#" + _form_c, indicatorRow.get(0).get(_form_c).toString()));
                script.append(Js.select2("#" + _form_c, ""));
                script.append(Js.setHtml("#" + _c, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_c).toString(), db)));
                script.append(Js.setVal("#" + _c, indicatorRow.get(0).get(_c).toString()));
                script.append(Js.select2("#" + _c, ""));

                String formId_c = indicatorRow.get(0).get(_form_c).toString();
                String question_c = FormQuestions.getHtmlFilterQuestion(formId_c, db);//بدست آوردن سوالات فرم 
                String option_c = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_c).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_c, option_c));
                script.append(Js.setHtml("#" + _filterQuestion_c, question_c));
                script.append(Js.setVal("#" + _filterOption_c, indicatorRow.get(0).get(_filterOption_c).toString()));
                script.append(Js.select2("#" + _filterOption_c, ""));
                script.append(Js.setVal("#" + _filterQuestion_c, indicatorRow.get(0).get(_filterQuestion_c).toString()));
                script.append(Js.select2("#" + _filterQuestion_c, ""));         
            }
            if (!indicatorRow.get(0).get(_form_d).toString().equals("")) {
                script.append(Js.setHtml("#" + _form_d, Forms.getHtmlfilterForm(db)));
                script.append(Js.setVal("#" + _form_d, indicatorRow.get(0).get(_form_d).toString()));
                script.append(Js.select2("#" + _form_d, ""));
                script.append(Js.setVal("#" + _source_d, indicatorRow.get(0).get(_source_d).toString()));
                script.append(Js.setHtml("#" + _d, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_d).toString(), db)));
                script.append(Js.setVal("#" + _d, indicatorRow.get(0).get(_d).toString()));
                script.append(Js.select2("#" + _d, ""));
                html += ""
                        + "<div class='row col-lg-12 ' id='rowIndicator'>"
                        + " <div class=\"row card-body pd-sm-10 alert-dismissible \">                        \n"
                        + "                                <div class=\"col-lg-2\">\n"
                        + "                                    متغیر _d\n"
                        + "                                    <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_source_d\" name=\"indicators_source_d\">\n"
                        + "                                            <option value=\"forms\">فرم ها</option>\n"
                        + "                                            <option value=\"webService\">webService</option>\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control formSelectOption\" id=\"indicators_form_d\" name=\"indicators_form_d\" onchange=\"hmisIndicators.getNumericalQuestionAsOption2($(this));\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_d\" name=\"indicators_d\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"row col-lg-12\">\n"
                        + "                                    <div class=\"col-lg-4\">\n"
                        + "                                        فیلتر کن آنهایی که بخش یا سوال زیر را :\n"
                        + "                                        <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterQuestion\" id=\"indicators_filterQuestion_d\"  name=\"indicators_filterQuestion_d\" onchange=\"hmisIndicators.getOptionForFilter2($(this));\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    <div class=\"col-lg-3\">\n"
                        + "                                        بصورت زیر انتخاب کرده اند:\n"
                        + "                                        <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterOption\" id=\"indicators_filterOption_d\" name=\"indicators_filterOption_d\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "<div class='col-lg-2'>"
                        + "<button class='btn btn-outline-danger btn-block mg-t-20 mg-b-20  buttonRemove'>حذف</button>"
                        + "</div>"
                        + "                                </div><!-- col -->";
                String formId_d = indicatorRow.get(0).get(_form_d).toString();
                String question_d = FormQuestions.getHtmlFilterQuestion(formId_d, db);//بدست آوردن سوالات فرم 
                String option_d = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_d).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_d, option_d));
                script.append(Js.setHtml("#" + _filterQuestion_d, question_d));
                script.append(Js.setVal("#" + _filterOption_d, indicatorRow.get(0).get(_filterOption_d).toString()));
                script.append(Js.select2("#" + _filterOption_d, ""));
                script.append(Js.setVal("#" + _filterQuestion_d, indicatorRow.get(0).get(_filterQuestion_d).toString()));
                script.append(Js.select2("#" + _filterQuestion_d, ""));
            }

            if (!indicatorRow.get(0).get(_form_e).toString().equals("")) {
                script.append(Js.setHtml("#" + _form_e, Forms.getHtmlfilterForm(db)));
                script.append(Js.setVal("#" + _form_e, indicatorRow.get(0).get(_form_e).toString()));
                script.append(Js.select2("#" + _form_e, ""));
                script.append(Js.setVal("#" + _source_e, indicatorRow.get(0).get(_source_e).toString()));

                script.append(Js.setHtml("#" + _e, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_e).toString(), db)));
                script.append(Js.setVal("#" + _e, indicatorRow.get(0).get(_e).toString()));
                script.append(Js.select2("#" + _e, ""));

                html += " "
                        + "<div class='row col-lg-12 ' id='rowIndicator'>"
                        + "<div class=\"row card-body pd-sm-10 alert-dismissible \">                        \n"
                        + "                                <div class=\"col-lg-2\">\n"
                        + "                                    متغیر _e\n"
                        + "                                    <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_source_e\" name=\"indicators_source_e\">\n"
                        + "                                            <option value=\"forms\">فرم ها</option>\n"
                        + "                                            <option value=\"webService\">webService</option>\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control formSelectOption\" id=\"indicators_form_e\" name=\"indicators_form_e\" onchange=\"hmisIndicators.getNumericalQuestionAsOption2($(this));\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_e\" name=\"indicators_e\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"row col-lg-12\">\n"
                        + "                                    <div class=\"col-lg-4\">\n"
                        + "                                        فیلتر کن آنهایی که بخش یا سوال زیر را :\n"
                        + "                                        <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterQuestion\" id=\"indicators_filterQuestion_e\"  name=\"indicators_filterQuestion_e\" onchange=\"hmisIndicators.getOptionForFilter2($(this));\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    <div class=\"col-lg-3\">\n"
                        + "                                        بصورت زیر انتخاب کرده اند:\n"
                        + "                                        <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterOption\" id=\"indicators_filterOption_e\" name=\"indicators_filterOption_e\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "<div class='col-lg-2'>"
                        + "<button class='btn btn-outline-danger btn-block mg-t-20 mg-b-20  buttonRemove'>حذف</button>"
                        + "</div>"
                        + "                                </div><!-- col -->";

                String formId_e = indicatorRow.get(0).get(_form_e).toString();
                String question_e = FormQuestions.getHtmlFilterQuestion(formId_e, db);//بدست آوردن سوالات فرم 
                String option_e = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_e).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_e, option_e));
                script.append(Js.setHtml("#" + _filterQuestion_e, question_e));
                script.append(Js.setVal("#" + _filterOption_e, indicatorRow.get(0).get(_filterOption_e).toString()));
                script.append(Js.select2("#" + _filterOption_e, ""));
                script.append(Js.setVal("#" + _filterQuestion_e, indicatorRow.get(0).get(_filterQuestion_e).toString()));
                script.append(Js.select2("#" + _filterQuestion_e, ""));
            }
            if (!indicatorRow.get(0).get(_form_f).toString().equals("")) {
                script.append(Js.setHtml("#" + _form_f, Forms.getHtmlfilterForm(db)));
                script.append(Js.setVal("#" + _form_f, indicatorRow.get(0).get(_form_f).toString()));
                script.append(Js.select2("#" + _form_f, ""));
                script.append(Js.setVal("#" + _source_f, indicatorRow.get(0).get(_source_f).toString()));
                script.append(Js.setHtml("#" + _f, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_f).toString(), db)));
                script.append(Js.setVal("#" + _f, indicatorRow.get(0).get(_f).toString()));
                script.append(Js.select2("#" + _f, ""));
                html += " "
                        + "<div class='row col-lg-12 ' id='rowIndicator'>"
                        + "<div class=\"row card-body pd-sm-10 alert-dismissible \">                        \n"
                        + "                                <div class=\"col-lg-2\">\n"
                        + "                                    متغیر _f\n"
                        + "                                    <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_source_f\" name=\"indicators_source_f\">\n"
                        + "                                            <option value=\"forms\">فرم ها</option>\n"
                        + "                                            <option value=\"webService\">webService</option>\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control formSelectOption\" id=\"indicators_form_f\" name=\"indicators_form_f\" onchange=\"hmisIndicators.getNumericalQuestionAsOption2($(this));\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_f\" name=\"indicators_f\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"row col-lg-12\">\n"
                        + "                                    <div class=\"col-lg-4\">\n"
                        + "                                        فیلتر کن آنهایی که بخش یا سوال زیر را :\n"
                        + "                                        <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterQuestion\" id=\"indicators_filterQuestion_f\"  name=\"indicators_filterQuestion_f\" onchange=\"hmisIndicators.getOptionForFilter2($(this));\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    <div class=\"col-lg-3\">\n"
                        + "                                        بصورت زیر انتخاب کرده اند:\n"
                        + "                                        <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterOption\" id=\"indicators_filterOption_f\" name=\"indicators_filterOption_f\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "<div class='col-lg-2'>"
                        + "<button class='btn btn-outline-danger btn-block mg-t-20 mg-b-20  buttonRemove'>حذف</button>"
                        + "</div>"
                        + "                                </div><!-- col -->";
                String formId_f = indicatorRow.get(0).get(_form_f).toString();
                String question_f = FormQuestions.getHtmlFilterQuestion(formId_f, db);//بدست آوردن سوالات فرم 
                String option_f = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_f).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_f, option_f));
                script.append(Js.setHtml("#" + _filterQuestion_f, question_f));
                script.append(Js.setVal("#" + _filterOption_f, indicatorRow.get(0).get(_filterOption_f).toString()));
                script.append(Js.select2("#" + _filterOption_f, ""));
                script.append(Js.setVal("#" + _filterQuestion_f, indicatorRow.get(0).get(_filterQuestion_f).toString()));
                script.append(Js.select2("#" + _filterQuestion_f, ""));
            }
            if (!indicatorRow.get(0).get(_form_g).toString().equals("")) {
                script.append(Js.setHtml("#" + _form_g, Forms.getHtmlfilterForm(db)));
                script.append(Js.setVal("#" + _form_g, indicatorRow.get(0).get(_form_g).toString()));
                script.append(Js.select2("#" + _form_g, ""));
                script.append(Js.setVal("#" + _source_g, indicatorRow.get(0).get(_source_g).toString()));
                script.append(Js.setHtml("#" + _g, FormQuestions.getHtmlNumericalQuestionAsOption(indicatorRow.get(0).get(_form_g).toString(), db)));
                script.append(Js.setVal("#" + _g, indicatorRow.get(0).get(_g).toString()));
                script.append(Js.select2("#" + _g, ""));
                html += " "
                        + "<div class='row col-lg-12 ' id='rowIndicator'>"
                        + "<div class=\"row card-body pd-sm-10 alert-dismissible \">                        \n"
                        + "                                <div class=\"col-lg-2\">\n"
                        + "                                    متغیر _g\n"
                        + "                                    <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_source_g\" name=\"indicators_source_g\">\n"
                        + "                                            <option value=\"forms\">فرم ها</option>\n"
                        + "                                            <option value=\"webService\">webService</option>\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control formSelectOption\" id=\"indicators_form_g\" name=\"indicators_form_g\" onchange=\"hmisIndicators.getNumericalQuestionAsOption2($(this));\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"col-lg-5\">\n"
                        + "                                    &nbsp;\n"
                        + "                                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                        <select class=\"form-control\" id=\"indicators_g\" name=\"indicators_g\" >\n"
                        + "                                        </select>\n"
                        + "                                    </div><!-- form-group -->\n"
                        + "                                </div><!-- col -->\n"
                        + "                                <div class=\"row col-lg-12\">\n"
                        + "                                    <div class=\"col-lg-4\">\n"
                        + "                                        فیلتر کن آنهایی که بخش یا سوال زیر را :\n"
                        + "                                        <div class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterQuestion\" id=\"indicators_filterQuestion_g\"  name=\"indicators_filterQuestion_g\" onchange=\"hmisIndicators.getOptionForFilter2($(this));\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    <div class=\"col-lg-3\">\n"
                        + "                                        بصورت زیر انتخاب کرده اند:\n"
                        + "                                        <div  class=\"form-group has-success mg-b-0\">\n"
                        + "                                            <select class=\"form-control filterOption\" id=\"indicators_filterOption_g\" name=\"indicators_filterOption_g\" >\n"
                        + "                                            </select>\n"
                        + "                                        </div><!-- form-group -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "                                    </div><!-- col -->\n"
                        + "<div class='col-lg-2'>"
                        + "<button class='btn btn-outline-danger btn-block mg-t-20 mg-b-20  buttonRemove'>حذف</button>"
                        + "</div>"
                        + "                                </div><!-- col -->";
                String formId_g = indicatorRow.get(0).get(_form_g).toString();
                String question_g = FormQuestions.getHtmlFilterQuestion(formId_g, db);//بدست آوردن سوالات فرم 
                String option_g = FormQuestionOptions.getHtmlOptionForFilter(indicatorRow.get(0).get(_filterQuestion_g).toString(), db);
                script.append(Js.setHtml("#" + _filterOption_g, option_g));
                script.append(Js.setHtml("#" + _filterQuestion_g, question_g));
                script.append(Js.setVal("#" + _filterOption_g, indicatorRow.get(0).get(_filterOption_g).toString()));
                script.append(Js.select2("#" + _filterOption_g, ""));
                script.append(Js.setVal("#" + _filterQuestion_g, indicatorRow.get(0).get(_filterQuestion_g).toString()));
                script.append(Js.select2("#" + _filterQuestion_g, ""));
            }
            script.append(Js.setVal("#" + _code, indicatorRow.get(0).get(_code).toString()));
            script.append(Js.setVal("#" + _collectingReason, indicatorRow.get(0).get(_collectingReason).toString()));
            script.append(Js.setVal("#" + _comments, indicatorRow.get(0).get(_comments).toString()));
            script.append(Js.setVal("#" + _dangerFloor, indicatorRow.get(0).get(_dangerFloor).toString()));
            script.append(Js.setVal("#" + _dangerTop, indicatorRow.get(0).get(_dangerTop).toString()));
            script.append(Js.setVal("#" + _department_id, indicatorRow.get(0).get(_department_id).toString()));
            script.append("$('#" + _department_id + "' ).select2();");
            script.append(Js.setVal("#" + _difinition, indicatorRow.get(0).get(_difinition).toString()));
            script.append(Js.setValSelectOption("#" + _dimension, indicatorRow.get(0).get(_dimension).toString()));
            script.append(Js.select2("#" + _dimension, ""));
            script.append(Js.setVal("#" + _dimension, indicatorRow.get(0).get(_dimension).toString()));
            script.append(Js.setVal("#" + _formula, indicatorRow.get(0).get(_formula).toString()));
            script.append(Js.setVal("#" + _goodFloor, indicatorRow.get(0).get(_goodFloor).toString()));
            script.append(Js.setVal("#" + _goodTop, indicatorRow.get(0).get(_goodTop).toString()));
            script.append(Js.setValSummerNote("#" + _htmlContent, indicatorRow.get(0).get(_htmlContent).toString()));
            script.append(Js.setVal("#" + _icon, indicatorRow.get(0).get(_icon).toString()));
            if (!indicatorRow.get(0).get(_icon).toString().isEmpty()) {//اگر عکس داشت نشان بدهد
                script.append(Js.setAttr("#indicators_icon_Preview", "src", "upload/" + indicatorRow.get(0).get(_icon).toString()));
            }
            script.append(Js.setVal("#" + _isActive, indicatorRow.get(0).get(_isActive).toString()));
            script.append(Js.setVal("#" + _sendSmsForIndicatorPeriod, indicatorRow.get(0).get(_sendSmsForIndicatorPeriod).toString()));
            script.append(Js.setVal("#" + _sendSmsAfterInsertFormAnswerSet, indicatorRow.get(0).get(_sendSmsAfterInsertFormAnswerSet).toString()));
            script.append(Js.setVal("#" + _isAutoWiki, indicatorRow.get(0).get(_isAutoWiki).toString()));
            script.append(Js.setVal("#" + _ownerId, indicatorRow.get(0).get(_ownerId).toString()));
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, _id + "=" + indicatorRow.get(0).get(_ownerId).toString()));//برای استخراج نام و نام خانوادگی کاربری که شاخص را ایجاد کرده است
            script.append(Js.setVal("#indicators_ownerName", userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()));
//            //برای اینکه نقش های ایجاد کننده شاخص را که فعلا در سشن است را بتوانیم دربیاوریم که فعلا هم کاربرد خاصی ندارد
//            String userRolesOptions = Role.getUeserRolesSelectOption(request, response, db, true);
//            if (!userRolesOptions.isEmpty()) {// ممکن است کاربر جاری نقشی در سیستم نداشته باشد ولی دسترسی هایی داشته باشد
//                script.append(Js.setHtml("#" + _ownerRole, userRolesOptions));
//            } else {
//                script.append(Js.setVal("#" + _ownerRole, "<option value=''></option>"));
//            }
//            script.append(Js.setVal("#" + _ownerRole, formRow.get(0).get(_ownerRole).toString()));            
//            script.append(Js.select2("#" + _ownerRole, "width:'100%'"));
            script.append(Js.setVal("#" + _startDate, jjCalendar_IR.getViewFormat(indicatorRow.get(0).get(_startDate).toString())));
            script.append(Js.setVal("#" + _periodOfAnalysis, indicatorRow.get(0).get(_periodOfAnalysis).toString()));
            script.append(Js.select2("#" + _periodOfAnalysis, ""));
            script.append(Js.setVal("#" + _periodOfCollectiong, indicatorRow.get(0).get(_periodOfCollectiong).toString()));
            script.append(Js.select2("#" + _periodOfCollectiong, ""));
            script.append(Js.setVal("#" + _priority, indicatorRow.get(0).get(_priority).toString()));
            script.append(Js.setValSelectOption("#" + _responsibleRole, indicatorRow.get(0).get(_responsibleRole).toString()));
            script.append(Js.select2("#" + _responsibleRole, ""));
            script.append(Js.setValSelectOption("#" + _responsibleUser, indicatorRow.get(0).get(_responsibleUser).toString()));
            script.append(Js.select2("#" + _responsibleUser, ""));
            script.append(Js.setValSelectOption("#" + _resultAccessUsers, indicatorRow.get(0).get(_resultAccessUsers).toString()));
            script.append(Js.select2("#" + _resultAccessUsers, ""));
            script.append(Js.setValSelectOption("#" + _resultAccessRols, indicatorRow.get(0).get(_resultAccessRols).toString()));
            script.append(Js.select2("#" + _resultAccessRols, ""));
            script.append(Js.setValSelectOption("#" + _scop, indicatorRow.get(0).get(_scop).toString()));
            script.append(Js.select2("#" + _scop, ""));
            script.append(Js.setVal("#" + _title, indicatorRow.get(0).get(_title).toString()));
            script.append(Js.setValSelectOption("#" + _type, indicatorRow.get(0).get(_type).toString()));
            script.append(Js.select2("#" + _type, ""));
            script.append(Js.setVal("#" + _warnningFloor, indicatorRow.get(0).get(_warnningFloor).toString()));
            script.append(Js.setVal("#" + _warnningTop, indicatorRow.get(0).get(_warnningTop).toString()));

            script.append(Js.setValSelectOption("#" + _resultAccessUsers, indicatorRow.get(0).get(_resultAccessUsers).toString()));
            script.append(Js.select2("#" + _resultAccessUsers, ""));

            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg-6'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjIndicators.edit(id) + "' id='edit_ndicators_new'>" + lbl_edit + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg-6'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjIndicators.delete(id) + "' id='edit_ndicators_new'>" + lbl_delete + "</button></div>";
            }
            String script2 = Js.setHtml("#indicatorAdd", html);

            script.append(Js.setHtml("#indicator_buttons", htmlBottons));
//            script.append("hmisForms.selectOptionForm(\".formSelectOption\");");
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            Server.outPrinter(request, response, script2 + script.toString());
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

            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap();

            map.put(_source_a, jjTools.getParameter(request, _source_a));
            map.put(_form_a, jjTools.getParameter(request, _form_a));
            map.put(_question_a, jjTools.getParameter(request, _question_a));
            map.put(_filterQuestion_a, jjTools.getParameter(request, _filterQuestion_a));
            map.put(_filterOption_a, jjTools.getParameter(request, _filterOption_a));
            map.put(_a, jjTools.getParameter(request, _a));

            map.put(_source_b, jjTools.getParameter(request, _source_b));
            map.put(_form_b, jjTools.getParameter(request, _form_b));
            map.put(_question_b, jjTools.getParameter(request, _question_b));
            map.put(_filterQuestion_b, jjTools.getParameter(request, _filterQuestion_b));
            map.put(_filterOption_b, jjTools.getParameter(request, _filterOption_b));
            map.put(_b, jjTools.getParameter(request, _b));

            map.put(_source_c, jjTools.getParameter(request, _source_c));
            map.put(_form_c, jjTools.getParameter(request, _form_c));
            map.put(_question_c, jjTools.getParameter(request, _question_c));
            map.put(_filterQuestion_c, jjTools.getParameter(request, _filterQuestion_c));
            map.put(_filterOption_c, jjTools.getParameter(request, _filterOption_c));
            map.put(_c, jjTools.getParameter(request, _c));

            map.put(_source_d, jjTools.getParameter(request, _source_d));
            map.put(_form_d, jjTools.getParameter(request, _form_d));
            map.put(_question_d, jjTools.getParameter(request, _question_d));
            map.put(_filterQuestion_d, jjTools.getParameter(request, _filterQuestion_d));
            map.put(_filterOption_d, jjTools.getParameter(request, _filterOption_d));
            map.put(_d, jjTools.getParameter(request, _d));

            map.put(_source_e, jjTools.getParameter(request, _source_e));
            map.put(_form_e, jjTools.getParameter(request, _form_e));
            map.put(_question_e, jjTools.getParameter(request, _question_e));
            map.put(_filterQuestion_e, jjTools.getParameter(request, _filterQuestion_e));
            map.put(_filterOption_e, jjTools.getParameter(request, _filterOption_e));
            map.put(_e, jjTools.getParameter(request, _e));

            map.put(_source_f, jjTools.getParameter(request, _source_f));
            map.put(_form_f, jjTools.getParameter(request, _form_f));
            map.put(_question_f, jjTools.getParameter(request, _question_f));
            map.put(_filterQuestion_f, jjTools.getParameter(request, _filterQuestion_f));
            map.put(_filterOption_f, jjTools.getParameter(request, _filterOption_f));
            map.put(_f, jjTools.getParameter(request, _f));

            map.put(_source_g, jjTools.getParameter(request, _source_g));
            map.put(_form_g, jjTools.getParameter(request, _form_g));
            map.put(_question_g, jjTools.getParameter(request, _question_g));
            map.put(_filterQuestion_g, jjTools.getParameter(request, _filterQuestion_g));
            map.put(_filterOption_g, jjTools.getParameter(request, _filterOption_g));
            map.put(_g, jjTools.getParameter(request, _g));

            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_collectingReason, jjTools.getParameter(request, _collectingReason));
            map.put(_comments, jjTools.getParameter(request, _comments));
            map.put(_dangerFloor, jjTools.getParameter(request, _dangerFloor));
            map.put(_dangerTop, jjTools.getParameter(request, _dangerTop));
            map.put(_department_id, !jjNumber.isDigit(jjTools.getParameter(request, _department_id)) ? "1" : jjTools.getParameter(request, _department_id));// اگر بخش را مشخص نکرده بود پیشفرض می رود در بخش اول که بهبود کیفیت است
            map.put(_difinition, jjTools.getParameter(request, _difinition));
            map.put(_dimension, jjTools.getParameter(request, _dimension));
            map.put(_formula, jjTools.getParameter(request, _formula));
            map.put(_goodFloor, jjTools.getParameter(request, _goodFloor));
            map.put(_goodTop, jjTools.getParameter(request, _goodTop));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            map.put(_htmlContentWithWikiLinks, Content.ConvertToWiki(request, jjTools.getParameter(request, _htmlContent), db, false));
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_sendSmsForIndicatorPeriod, jjTools.getParameter(request, _sendSmsForIndicatorPeriod));
            map.put(_sendSmsAfterInsertFormAnswerSet, jjTools.getParameter(request, _sendSmsAfterInsertFormAnswerSet));
            map.put(_isAutoWiki, jjTools.getParameter(request, _isAutoWiki));
//            map.put(_ownerId, jjTools.getParameter(request, jjTools.getSeassionUserId(request) + ""));
//            map.put(_ownerRole, jjTools.getParameter(request, _ownerRole));
            map.put(_startDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _startDate), true));
            map.put(_periodOfAnalysis, jjTools.getParameter(request, _periodOfAnalysis));
            map.put(_periodOfCollectiong, jjTools.getParameter(request, _periodOfCollectiong));
            map.put(_priority, jjTools.getParameter(request, _priority));
            if (!jjTools.getParameter(request, _responsibleRole).isEmpty() || jjTools.getParameter(request, _responsibleRole).equals("null")) {// مسئول فقط یک نقش یا یک فرد است و هر دو در هر زمان نمیتواند پر باشد
                map.put(_responsibleRole, jjTools.getParameter(request, _responsibleRole));
                map.put(_responsibleUser, "");
            } else {
                map.put(_responsibleUser, jjTools.getParameter(request, _responsibleUser));
                map.put(_responsibleRole, "");
            }
            map.put(_resultAccessRols, jjTools.getParameter(request, _resultAccessRols));
            map.put(_resultAccessUsers, jjTools.getParameter(request, _resultAccessUsers));
            map.put(_scop, jjTools.getParameter(request, _scop));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_type, jjTools.getParameter(request, _type));
            map.put(_warnningFloor, jjTools.getParameter(request, _warnningFloor));
            map.put(_warnningTop, jjTools.getParameter(request, _warnningTop));

            if (db.update(tableName, map, _id + "=" + id)) {
                Server.outPrinter(request, response, Js.modal("ویرایش بدرستی انجام شد", "پیام سامانه"));
                return "";
            } else {
                Server.outPrinter(request, response, Js.modal("ویرایش انجام نشد", "پیام سامانه"));
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            } else {
                String id = jjTools.getParameter(request, _id);
                if (!jjNumber.isDigit(id)) {
                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
                    return "";
                }
//                db.Select(tableName)//سوال بپرسد که تحلیل ها را هم پاک بکند یا نه@ToDo
                if (db.delete(tableName, "id=" + id)) {
                    Server.outPrinter(request, response, Js.jjIndicators.refresh() + Js.modal("شاخص حذف شد", "پیام سامانه"));
                    return "";
                } else {
                    Server.outPrinter(request, response, Js.modal("عدم موفقیت عملیات حذف!!!", "پیام سامانه"));
                    return "";
                }
            }

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * برای ایجاد نمودار آنالیزی فرم روی یک سوال یا تک تک سوالات
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String analysFromByQuestion(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
//                return "";
//            } else {
//                String id = jjTools.getParameter(request, _id);
//                if (!jjNumber.isDigit(id)) {
//                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
//                    return "";
//                }
//            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");

            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");

            String dateCondition1 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + ">" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + "<" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String roleCondition = "";
            if (!jjTools.getParameter(request, FormAnswerSet._userRole).isEmpty()) {// اگر فیلتر روی نقش خاصی میخواست
                String role[] = jjTools.getParameter(request, FormAnswerSet._userRole).split(",");
                if (jjNumber.isDigit(role[0])) {// اگر اولین نقش عدد بود مشکلی پیش نمی آید برای بقیه
                    roleCondition += " AND ( " + FormAnswerSet._userRole + "=" + role[0];// برای اولین نقش شرط پرانتز می خواهد
                    for (int i = 1; i < role.length; i++) {// برای نقش های انتخابی دیگر
                        if (jjNumber.isDigit(role[i])) {// اگر کد مورد نظر عددی باشد ، برای همه ی نقش ها ممکن است تهی بیاید
                            System.out.println(role[i]);
                            roleCondition += " OR " + FormAnswerSet._userRole + "=" + role[i];
                        }
                    }
                    roleCondition += ")";
                } else {// اگر اولین نقش عدد نبود احتمالا همه است و روی نقش ها حساسیت نمی گذاریم اگر چیزی نگذارید نال است
                    roleCondition += "";// برای اولین نقش شرط پرانتز می خواهد
                }
            }

            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            Integer sum = 0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
            if (chartType.equals("bar")) {
                if (dataType.equals("value")) {// اگر کاربر نمودار ستونی بر اساس مقدار خواسته باشد
                    String script = "";
                    String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رسشته ای می گذاریم
                    String color1 = "";
                    List<Integer> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
                    if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                        //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                        String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                        if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                            ansertSetSQL = "(SELECT "
                                    + "formAnswers_date"
                                    + ",formAnswers_status"
                                    + ",formanswers_answerSet_id AS id "
                                    + " FROM hmis_formanswers "
                                    + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "' AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") + "')";
                        } else {
                            ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                        }
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                            for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                                // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                                //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                                List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                        "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                        + "WHERE " + FormAnswers._questionId + "=" + questionId
                                        + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                        + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                        + roleCondition
                                        + dateCondition1
                                ));
                                labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                                String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                                sum += Integer.valueOf(no);
                                data1.add(Integer.valueOf(no));
                                color1 += "'#5b93d3',";
                                dataPieArray.add(Integer.valueOf(no));
                                if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                                    List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                            "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                            + "WHERE " + FormAnswers._questionId + "=" + questionId
                                            + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                            + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                            + roleCondition
                                            + dateCondition2
                                    ));
                                    labels1 += " ' 2 - " + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                                    no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                                    data1.add(Integer.valueOf(no));
                                    color1 += "'#677489',";
                                }
                            }
                        }
                    }
                    String data = "";
                    for (int i = 0; i < data1.size(); i++) {
                        data += data1.get(i) + ",";
                    }
                    script
                            += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                            + "var myChart1 = new Chart(ctx1, {"
                            + "type: 'bar', data: {"
                            + "labels: ["
                            + labels1
                            + "],"
                            + "datasets: [{"
                            + "label: '# of Votes',"
                            + "data: ["
                            + data
                            + "],"
                            + "backgroundColor: ["
                            + color1
                            + "]"
                            + "}]"
                            + "},"
                            + "options: {"
                            + "plugins: {"
                            + "labels: {"
                            + "render: 'value'"
                            + "}"
                            + "},"
                            + "title: {"
                            + "display: true,"
                            + "text: 'نمودار ستونی'"
                            + "},"
                            + "legend: {"
                            + "display: false,"
                            + "labels: {"
                            + "display: false"
                            + "}"
                            + "},"
                            + "scales: {"
                            + "yAxes: [{"
                            + "ticks: {"
                            + "beginAtZero: true,"
                            + "fontSize: 10,"
                            + "}"
                            + "}],"
                            + "xAxes: [{"
                            + "ticks: {"
                            + "beginAtZero: true,"
                            + "fontSize: 11"
                            + "}"
                            + "}]"
                            + "}"
                            + "}"
                            + "});";
                    request.setAttribute("script", script);
                    request.getRequestDispatcher("template/charts.jsp").forward(request, response);
                    Server.outPrinter(request, response, script);
                    return script;
                } else if (dataType.equals("percentage")) {
                    String script = "";
                    String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رسشته ای می گذاریم
                    String color1 = "";
                    List<Integer> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
                    if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                        //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                        String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                        if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                            ansertSetSQL = "(SELECT "
                                    + "formAnswers_date"
                                    + ",formAnswers_status"
                                    + ",formanswers_answerSet_id AS id "
                                    + " FROM hmis_formanswers "
                                    + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "' AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") + "')";
                        } else {
                            ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                        }
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                            for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                                // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                                //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                                List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                        "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                        + "WHERE " + FormAnswers._questionId + "=" + questionId
                                        + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                        + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                        + roleCondition
                                        + dateCondition1
                                ));
                                labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                                String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                                sum += Integer.valueOf(no);
                                data1.add(Integer.valueOf(no));
                                color1 += "'#5b93d3',";
                                dataPieArray.add(Integer.valueOf(no));
                                if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                                    List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                            "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                            + "WHERE " + FormAnswers._questionId + "=" + questionId
                                            + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                            + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                            + roleCondition
                                            + dateCondition2
                                    ));
                                    labels1 += " ' 2 - " + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                                    no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                                    data1.add(Integer.valueOf(no));
                                    color1 += "'#677489',";
                                }
                            }
                        }
                    }
                    String data = "";
                    for (int i = 0; i < data1.size(); i++) {
                        if (sum != 0) {
                            data += data1.get(i) * 100 / sum + ",";
                        } else {
                            data += 0 + ",";
                        }
                    }
                    script
                            += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                            + "var myChart1 = new Chart(ctx1, {"
                            + "type: 'bar', data: {"
                            + "labels: ["
                            + labels1
                            + "],"
                            + "datasets: [{"
                            + "label: '# of Votes',"
                            + "data: ["
                            + data
                            + "],"
                            + "backgroundColor: ["
                            + color1
                            + "]"
                            + "}]"
                            + "},"
                            + "options: {"
                            + "plugins: {"
                            + "labels: {"
                            + "render: 'value'"
                            + "}"
                            + "},"
                            + "title: {"
                            + "display: true,"
                            + "text: 'نمودار ستونی برحسب درصد %'"
                            + "},"
                            + "legend: {"
                            + "display: false,"
                            + "labels: {"
                            + "display: false"
                            + "}"
                            + "},"
                            + "scales: {"
                            + "yAxes: [{"
                            + "ticks: {"
                            + "beginAtZero: true,"
                            + "fontSize: 10,"
                            + "}"
                            + "}],"
                            + "xAxes: [{"
                            + "ticks: {"
                            + "beginAtZero: true,"
                            + "fontSize: 11"
                            + "}"
                            + "}]"
                            + "}"
                            + "}"
                            + "});";
                    request.setAttribute("script", script);
                    request.getRequestDispatcher("template/charts.jsp").forward(request, response);
                    Server.outPrinter(request, response, script);
                    return script;
                }
            } else if (chartType.equals("pie")) {
                String dataPie = "";// داده برای نمودار دایره ای
                String colorPie = "";
                String script = "";
                String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رسشته ای می گذاریم
                String color1 = "";
                List<Integer> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
                if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                    //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                    String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                    if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                        ansertSetSQL = "(SELECT "
                                + "formAnswers_date"
                                + ",formAnswers_status"
                                + ",formanswers_answerSet_id AS id "
                                + " FROM hmis_formanswers "
                                + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                                + " WHERE "
                                + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "' AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") + "')";
                    } else {
                        ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                    }
                    List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                    //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                    if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                        List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                        for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                            // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                            //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                            List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                    "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                    + "WHERE " + FormAnswers._questionId + "=" + questionId
                                    + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                    + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                    + roleCondition
                                    + dateCondition1
                            ));
                            labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                            String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                            data1.add(Integer.valueOf(no));
                            color1 += "'#5b93d3','#5b9313','#5b9373','#5b93a3','#5b93da',";
                            if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                                List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                        "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                        + "WHERE " + FormAnswers._questionId + "=" + questionId
                                        + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                        + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                        + roleCondition
                                        + dateCondition2
                                ));
                                labels1 += " ' 2 - " + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                                no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                                data1.add(Integer.valueOf(no));
                            }
                        }
                    }
                }

                for (int j = 0; j < data1.size(); j++) {
                    dataPie += data1.get(j) + ",";
                }

                script
                        += "  var ctx7 = document.getElementById('chartBar2');"
                        + "var myPieChart7 = new Chart(ctx7, {"
                        + "type: 'pie',"
                        + "data: {datasets: [{"
                        + "data: ["
                        + dataPie + ""
                        + "],"
                        + "backgroundColor: ["
                        + "'#677489',"
                        + "'#218bc2',"
                        + "'#7CBDDF',"
                        + "'#5B93D3',"
                        + "'#324fac',"
                        + "'#324463'"
                        + "],"
                        + "}],"
                        + "labels: [" + labels1 + "]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "labels: [{"
                        + "render: 'pecentage',"
                        + "position: 'inside',"
                        + "precision: 1,"
                        + "fontSize: 18,"
                        + "fontStyle: 'bold',"
                        + "textShadow: true,"
                        + "fontColor: '#000'"
                        + "}"
                        + ",{"
                        + "render: 'label',"
                        + "position: 'outside',"
                        + "fontSize: 20,"
                        + "fontStyle: 'bold',"
                        + "fontColor: '#000'"
                        + "}"
                        + "]"
                        + "},"
                        + "responsive: true,"
                        + "title: {"
                        + "display: true,"
                        + "text: 'نمودار دایره ای سوال'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
                        + "}"
                        + "}"
                        + "});";
                request.setAttribute("script", script);
                request.getRequestDispatcher("template/charts.jsp").forward(request, response);
                Server.outPrinter(request, response, script);
                return script;

            } else if (chartType.equals("line")) {
                String data1 = "";// داده برای نمودار اول
                String data2 = "";
                String script = "";
                String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رسشته ای می گذاریم
                List<Integer> dataArray1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
                List<Integer> dataArray2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
                if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                    //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                    String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                    if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                        ansertSetSQL = "(SELECT "
                                + "formAnswers_date"
                                + ",formAnswers_status"
                                + ",formanswers_answerSet_id AS id "
                                + " FROM hmis_formanswers "
                                + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                                + " WHERE "
                                + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "' AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") + "')";
                    } else {
                        ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                    }
                    List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                    //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                    if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                        List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                        for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                            // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                            //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                            List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                    "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                    + "WHERE " + FormAnswers._questionId + "=" + questionId
                                    + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                    + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                    + roleCondition
                                    + dateCondition1
                            ));
                            labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                            String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                            dataArray1.add(Integer.valueOf(no));
                            if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                                List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                        "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                        + "WHERE " + FormAnswers._questionId + "=" + questionId
                                        + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                        + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                        + roleCondition
                                        + dateCondition2
                                ));
                                no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                                dataArray2.add(Integer.valueOf(no));
                            }
                        }
                    }
                }

                for (int j = 0; j < dataArray1.size(); j++) {
                    data1 += dataArray1.get(j) + ",";
                }
                for (int j = 0; j < dataArray2.size(); j++) {
                    data2 += dataArray2.get(j) + ",";
                }

                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + "data: [" + data1 + "],"
                        + "borderColor: '#324463',"
                        + "borderWidth: 1,"
                        + "fill: false"
                        + "},{"
                        + "data: [" + data2 + "],"
                        + "borderColor: '#5B93D3',"
                        + "borderWidth: 1,"
                        + "fill: false"
                        + "      }]"
                        + "},"
                        + "options: {"
                        + "legend: {"
                        + "display: false,"
                        + "labels: {"
                        + "display: false"
                        + "}"
                        + "},"
                        + "scales: {"
                        + "yAxes: [{"
                        + "ticks: {"
                        + "beginAtZero: true,"
                        + "fontSize: 10,"
                        + "}"
                        + "}],"
                        + "xAxes: [{"
                        + "ticks: {"
                        + "beginAtZero: true,"
                        + "fontSize: 11"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
                request.setAttribute("script", script);
                request.getRequestDispatcher("template/charts.jsp").forward(request, response);
                Server.outPrinter(request, response, script);
                return script;
            }
//                script
//                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
//                        + "var myChart3 = new Chart(ctx3, {"
//                        + "type: 'line', data: {"
//                        + "labels: ["
//                        + labels
//                        + "],"
//                        + "datasets: [{"
//                        + "data: [" + data + "],"
//                        + "borderColor: '#324463',"
//                        + "borderWidth: 1,"
//                        + "fill: false"
//                        + "},{"
//                        + "data: [" + dataPie + "],"
//                        + "borderColor: '#5B93D3',"
//                        + "borderWidth: 1,"
//                        + "fill: false"
//                        + "      }]"
//                        + "},"
//                        + "options: {"
//                        + "legend: {"
//                        + "display: false,"
//                        + "labels: {"
//                        + "display: false"
//                        + "}"
//                        + "},"
//                        + "scales: {"
//                        + "yAxes: [{"
//                        + "ticks: {"
//                        + "beginAtZero: true,"
//                        + "fontSize: 10,"
//                        + "}"
//                        + "}],"
//                        + "xAxes: [{"
//                        + "ticks: {"
//                        + "beginAtZero: true,"
//                        + "fontSize: 11"
//                        + "}"
//                        + "}]"
//                        + "}"
//                        + "}"
//                        + "});";
//
//
//

//            request.setAttribute("script", script);
//            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمودار عملکرد پرسنل یا نقش ها روی یک فرم
     * <br/>
     * مثلا هر نفشی چند بار این فرم را پر کرده است
     * <br/>
     * یا هر فردی چند بار این فرم را پر کرده است
     * <br/>
     * یا در یک بازه ی زمانی چطور این فرم را تکمیل کرده اند
     * <br/>
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formCountResult_period(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
//                return "";
//            } else {
//                String id = jjTools.getParameter(request, _id);
//                if (!jjNumber.isDigit(id)) {
//                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
//                    return "";
//                }
//            }
            String sql = "";
            String groupBy = " GROUP BY formAnswers_date";
            int div = jjNumber.isDigit(jjTools.getParameter(request, "div")) ? Integer.valueOf(jjTools.getParameter(request, "div")) : 30;// برای تقسیم بدنی بر اساس دوره زمانی روزانه
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس            
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
//            String where = " WHERE " + FormAnswerSet._formId + "= " + jjTools.getParameter(request, _id);
            String where = " WHERE 1=1 ";
            String color = "";
            if (!jjTools.getParameter(request, "formAnswers_userRole").isEmpty()) {
                String roles[] = jjTools.getParameter(request, "formAnswers_userRole").split(",");
                roleOrUserCondition += "AND (";
                for (int i = 0; i < roles.length; i++) {
                    if (roles.length - 1 == i) {//برای عنصر آخر اور نمیخواهد و باید پرانتز را ببنیدم
                        roleOrUserCondition += "" + FormAnswerSet._userRole + "=" + roles[i] + ")";
                    } else {
                        roleOrUserCondition += FormAnswerSet._userRole + "=" + roles[i] + " OR ";
                    }
                }
            }
            String questionId = jjTools.getParameter(request, "axel_y");
            if (jjNumber.isDigit(questionId)) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    axel_y = "avg(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "avg(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                }
            } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                } else if (questionId.equals("avg")) {
                    axel_y = "avg(hmis_formanswerset_avg)";
                } else if (questionId.equals("sum")) {
                    axel_y = "sum(hmis_formanswerset_sum)";
                }
                //چدول زیر تعداد فرم های پر شده در هر روز را بدست می آور
                //@ToDO Use 'DIV' in SQL like this post  https://stackoverflow.com/questions/57229285/mysql-how-to-select-count-of-rows-group-by-sun-calendar-date-and-div-to-periodic/57231625#57231625
                sql = "SELECT  " + axel_y + " as val," + FormAnswerSet._date + "  FROM hmis_formanswerset "
                        + where;
            }
            int startDate;
            if (jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {// اگر تاریخ شروع نداده بود سال قبل را نشان بدهد
                jjCalendar_IR lastYearDate = new jjCalendar_IR();
                lastYearDate.setDate(lastYearDate.getYear() - 1, lastYearDate.getMonth(), lastYearDate.getDay());
                startDate = lastYearDate.getDBFormat_8length();
            } else {
                startDate = jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), true);
            }
            int endDate = jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), true);//اگر تاریخ پایان نداده بود امروز  را نشان بدهد
            String dateCondition = " ";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            // اولین تاریخ در دیتابیس را میگیریم و به تعداد دوره اضافه می کنیم و حمع می زنیم
            int startDate2;
            if (jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {// اگر تاریخ شروع نداده بود سال قبل را نشان بدهد
                jjCalendar_IR lastYearDate2 = new jjCalendar_IR();
                lastYearDate2.setDate(lastYearDate2.getYear() - 2, lastYearDate2.getMonth(), lastYearDate2.getDay());
                startDate2 = lastYearDate2.getDBFormat_8length();
            } else {
                startDate2 = jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), true);
            }
            int i = 0;
            jjCalendar_IR datePointer = new jjCalendar_IR(String.valueOf(startDate));
            jjCalendar_IR datePointer2 = new jjCalendar_IR(String.valueOf(startDate2));
            jjCalendar_IR datePointerTemp = new jjCalendar_IR(String.valueOf(startDate));
            datePointerTemp.addDay(div);
            while (datePointer.getDBFormat_8length() < endDate && datePointerTemp.getDBFormat_8length() < endDate) {
                labels += " '" + datePointer.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer.getDBFormat_8length() + " ";
                datePointer.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer.getDBFormat_8length() + " ";
                List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition));
                data += "'" + results.get(0).get("val") + "',";
                color += "'#5B93D3',";
                if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {// اگر در ریکوئست برای مقایسه تاریخ وارد شده بود  و اگر در ریکوئست چیزی نبود اینجا وارد نمیشود
                    labels += " '" + datePointer2.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                    dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer2.getDBFormat_8length() + " ";
                    datePointer2.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                    dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer2.getDBFormat_8length() + " ";
                    List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition));
                    data += "'" + results2.get(0).get("val") + "',";
                    color += "'#324463 ',";
                }
                datePointerTemp.addDay(div);//برای اینکه در پایان بازه اگر کمتر از یک ماه داشتیم ستون اضافه ایجاد نکند
            }
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],"
                    + "datasets: [{"
                    + "label: '# of Votes',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: ["
                    + color
                    + "]"
                    + "}]"
                    + "},"
                    + "options: {"
                    + "legend: {"
                    + "display: true,"
                    + "labels: {"
                    + "display: true"
                    + "}"
                    + "},"
                    + "scales: {"
                    + "yAxes: [{"
                    + "ticks: {"
                    + "beginAtZero: true,"
                    + "fontSize: 10,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "beginAtZero: true,"
                    + "fontSize: 11"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            script += Js.modal("http://kjhkk.com/Server?do=Forms.analysFromByQuestion", "لینک این گزارش را کپی کنید");
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نشان دادن نتایج یک فرم که ثبت شده به کاربری که آنرا ثبت کرده یا کاربری که
     * مجوز مشاهده دارد
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showResult(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        request.setAttribute("db", db);
        System.out.println("------->>>>>template/indicatorResult.jsp");
        request.getRequestDispatcher("template/indicatorResult.jsp").forward(request, response);
        return "";
    }

    /**
     * نشان دادن نتایج یک شاخص که ثبت شده به کاربری که آنرا ثبت کرده یا کاربری
     * که مجوز مشاهده دارد
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showResultDiscription(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        request.setAttribute("db", db);
        jjTools.ShowAllParameter(request);

        System.out.println("------->>>>>template/indicatorResultDiscription.jsp");
        request.getRequestDispatcher("template/indicatorResultDiscription.jsp").forward(request, response);
        return "";
    }

    /**
     * نمودار یک مقدار در طول زمان
     * <br/>
     * مثلا هر نفشی به یک سوال چگونه در دوره های مختلف پاسخ داده
     * <br/>
     * و یا یک فرم برای چندین نقش چگونه تکمیل شده است
     * <br/>
     * یا در بازه های زمانی چطور این فرم را تکمیل کرده اند
     * <br/>
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formCountResult_turnover(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
//                return "";
//            } else {
            String id = jjTools.getParameter(request, _id);
//                if (!jjNumber.isDigit(id)) {
//                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
//                    return "";
//                }
//            }
            String sql = "";
            String groupBy = "";
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String lableColumn = "";// برای اینکه در نتیجه  کوئری بدانیم بر چه اساسی نام ساتون ها را استحراج کنیم
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
            String where = " WHERE " + FormAnswerSet._formId + "=" + id + " ";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            // تعیین محور افقی----------------------------------------------------------------
            if (!jjTools.getParameter(request, "axel_x_roles").equals("") && !jjTools.getParameter(request, "axel_x_roles").equals("null")) {// اگر محور افقی را برا اساس نقش ها تعیین خواسته باشد
                lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
                if (jjTools.getParameter(request, "axel_x_roles").equals("formAnswers_userRole")) {
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                } else { // اگر یک یا چند عدد بود یعنی نمودار عملکرد آن نقش ها را می خواهد
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                    lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
                    String roles[] = jjTools.getParameter(request, "axel_x_roles").split(",");
                    roleOrUserCondition += "AND (";
                    for (int i = 0; i < roles.length; i++) {
                        if (roles.length - 1 == i) {//برای عنصر آخر اور نمیخواهد و باید پرانتز را ببنیدم
                            roleOrUserCondition += "" + FormAnswerSet._userRole + "=" + roles[i] + ")";
                        } else {
                            roleOrUserCondition += FormAnswerSet._userRole + "=" + roles[i] + " OR ";
                        }
                    }
                }
            } else if (jjTools.getParameter(request, "axel_x_users").equals("formAnswers_userName")) {// اگر کاربر انتخاب کرده بود که محور افقی بر اساس کاربران باشد کوئری اس کیو ال را بر این اساس گروه بندی می کنیم
                groupBy = " GROUP BY " + FormAnswerSet._userName;
                lableColumn = FormAnswerSet._userName;
            } else if (!jjTools.getParameter(request, "axel_x_users").equals("null")) { // اگر یک یا چند عدد بود یعنی نمودار عملکرد آن افراد خاص را می خواهد
                groupBy = " GROUP BY " + FormAnswerSet._userId;
                lableColumn = FormAnswerSet._userName;
                String users[] = jjTools.getParameter(request, "axel_x_users").split(",");
                roleOrUserCondition += "AND (";
                for (int i = 0; i < users.length; i++) {
                    if (users.length - 1 == i) {//برای عنصر آخر اور نمیخواهد و باید پرانتز را ببنیدم
                        roleOrUserCondition += FormAnswerSet._userId + "=" + users[i] + ")";
                    } else {
                        roleOrUserCondition += FormAnswerSet._userId + "=" + users[i] + " OR ";
                    }
                }
            }
            //------------------------------------------------------------------------------------
            String questionId = jjTools.getParameter(request, "axel_y");
            if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                axel_y = "COUNT(hmis_formanswerset.id)";
            } else if (questionId.equals("avg")) {
                axel_y = "avg(hmis_formanswerset_avg)";

            } else if (questionId.equals("sum")) {
                axel_y = "sum(hmis_formanswerset_sum)";
            }
            // تعریف معیار محور عمودی که مثلا تعداد فرم های پر شده باشد یا محموع یا میانگین یا غیره
            if (jjNumber.isDigit(questionId)) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    axel_y = "sum(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition
                            + groupBy + ";";

                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "sum(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition
                            + groupBy + ";";
                }
            } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + " FROM hmis_formanswerset "
                        + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                        + where
                        + dateCondition
                        + roleOrUserCondition
                        + groupBy + ";";
            }

            //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
            List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));

            for (int i = 0; i < results.size(); i++) {// برای هر سطر از جدول یک ستون در محور افق و مقدار آن را هم همان موقع اضافه می کنیم
                // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                labels += " '" + (results.get(i).get(lableColumn) == null ? "نامشخص" : results.get(i).get(lableColumn).toString()) + "',";// نام هر آپشن
                String val = results.get(i).get("val").toString();// یک یا بیشتر
                data += "'" + val + "',";
            }

            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],"
                    + "datasets: [{"
                    + "label: '# of Votes',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: ["
                    + "'#5B93D3',"
                    + "'#324463',"
                    + "'#677489',"
                    + "'#218bc2',"
                    + "'#7CBDDF',"
                    + "]"
                    + "}]"
                    + "},"
                    + "options: {"
                    + "legend: {"
                    + "display: true,"
                    + "labels: {"
                    + "display: true"
                    + "}"
                    + "},"
                    + "scales: {"
                    + "yAxes: [{"
                    + "ticks: {"
                    + "beginAtZero: true,"
                    + "fontSize: 10,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "beginAtZero: true,"
                    + "fontSize: 11"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            script += Js.modal("http://kjhkk.com/Server?do=Forms.analysFromByQuestion", "لینک این گزارش را کپی کنید");
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این متد فرم ها را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
     *
     * @param request panel سلکتور پنل است دقت شود ممکن است نامبر ساین نداشته
     * باشد یا نخواهد
     * @param response
     * @param db
     * @param needString
     * @return بصورت کد جی کوئری و یک سری آپشن برای قرار گرفتن در سلکتی که در
     * پنل معرفی شده
     * @throws Exception
     */
    public static String getSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllActiveRols = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "," + Forms._title, "id>=0", Forms._title));// بر اساس حروف الفبا مرتب باشد بهتر است

            optionHtml.append("<option  value=''>یک فرم را انتخاب کنید</option>");
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(Forms._id)).append("'>").append(rowAllActiveRols.get(i).get(Forms._title)).append("</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".formSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getSelectOptionIndicators(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllIndicator = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._id + "," + Indicators._title, "id>=0", Indicators._title));// بر اساس حروف الفبا مرتب باشد بهتر است

            optionHtml.append("<option  value=''>یک شاخص  را انتخاب کنید</option>");
            for (int i = 0; i < rowAllIndicator.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllIndicator.get(i).get(Indicators._id)).append("'>").append(rowAllIndicator.get(i).get(Indicators._title)).append("</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".indicatorSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این تایع یک ست مرتب بر اساس تاریخ و جمع بندی نشده داریم که مثلا در یک روز
     * ممکن است چند داده داشته باشیم خروحی یک ست داده که اول بازه تاریخ است و در
     * جاهایی که داده نداریم صفر بر میگرداند
     * <br/>
     * -----------برای دوره های 30 روزه-------------------
     * <br/>
     * 1398/06/21 ---- 123 ماه ششم سی و یک روز دارد
     * <br/>
     * 1398/07/20 ---- 123
     * <br/>
     * 1398/08/21 ---- 123
     * <br/>
     * 1398/09/21 ---- 123
     * <br/>
     *
     * @param dataset_val_date یک سری سطر که بصورت زوج مرتب تاریخ و مقدار هستند
     * @param startDate تاریخ شروع دوره بندی
     * @param endDate تاریخ پایان دوره بندی
     * @param period دوره ی زمانی که چند روز یکبار را جمع آوری کند
     * @param sum_avg نوع جمع آوری داده ها در هر بازه که محموع باشد یا میانگین
     * @return
     */
    public static List<Map<String, Object>> createDataSetForIndicator(List<Map<String, Object>> dataset_val_date, int startDate, int endDate, int period, String sum_avg) {
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int i = 0;// برای سطر های داده های هر متغیر
        for (jjCalendar_IR datePointer = new jjCalendar_IR(String.valueOf(startDate)); datePointer.getDBFormat_8length() < endDate; datePointer.addDay(period)) {
            jjCalendar_IR datePointer2 = new jjCalendar_IR(datePointer.getYear(), datePointer.getMonth(), datePointer.getDay());
            datePointer2.addDay(period);
            Double sum_a = 0.0;//محاسبه ی مجموع درون بازه برای هر دوره
            Double avg_a = 0.0;//محاسبه یا میانگین درون بازه برای هر دوره 
            int countForAvg = 0;
            while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), true) < datePointer2.getDBFormat_8length()) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
                if (dataset_val_date.get(i) != null) {
                    if (Integer.valueOf(dataset_val_date.get(i).get("_date").toString()) < datePointer2.getDBFormat_8length()) {
                        if (jjNumber.isFloat(dataset_val_date.get(i).get("_val").toString())) {
                            sum_a += Double.valueOf(dataset_val_date.get(i).get("_val").toString());
                            countForAvg++;
                        }
                    }
                }
                i++;//یک ردیف تعیین تکلیف شد
            }
            Map<String, Object> map = new HashMap();
            map.put("_date", datePointer.getViewFormat_10length());
            if (sum_avg.equals("avg")) {
                if (countForAvg == 0) {// برای محاسبه ی میانگین
                    countForAvg = 1;
                }
                map.put("_val", sum_a / countForAvg);
            } else {
                map.put("_val", sum_a);
            }
            finalResult.add(map);
        }
//        System.out.println("&&&&&&&&&&&&&&&>>>>" + sum_avg + " -->" + finalResult);
        return finalResult;
    }

    /**
     * از آخر به اول می شود و مثل تابع قبل این تایع یک ست مرتب بر اساس تاریخ و
     * جمع بندی نشده داریم که مثلا در یک روز ممکن است چند داده داشته باشیم خروحی
     * یک ست داده که اول بازه تاریخ است و در جاهایی که داده نداریم صفر بر
     * میگرداند
     * <br/>
     * -----------برای دوره های 30 روزه-------------------
     * <br/>
     * 1398/06/21 ---- 123 ماه ششم سی و یک روز دارد
     * <br/>
     * 1398/07/20 ---- 123
     * <br/>
     * 1398/08/21 ---- 123
     * <br/>
     * 1398/09/21 ---- 123
     * <br/>
     *
     * @param dataset_val_date یک سری سطر که بصورت زوج مرتب تاریخ و مقدار هستند
     * @param startDate تاریخ شروع دوره بندی
     * @param endDate تاریخ پایان دوره بندی
     * @param period دوره ی زمانی که چند روز یکبار را جمع آوری کند
     * @param sum_avg نوع جمع آوری داده ها در هر بازه که محموع باشد یا میانگین
     * @return یک ست داده ای که آخرین ست آن مربوط به دوره ی مثلا سه ماهه آخر است
     */
    public static List<Map<String, Object>> createDataSetForIndicatorInverse(List<Map<String, Object>> dataset_val_date, int startDate, int endDate, int period, String sum_avg) {
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int i = 0;// اشاره به آخرین سطر داده ها
        while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), false) < startDate) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
            i++;// از اول بازه باید شروع کنیم تا آخر بازه ی مورد نظر
        }
        jjCalendar_IR datePointer = new jjCalendar_IR(String.valueOf(startDate));
        while (datePointer.getDBFormat_8length() < endDate) {
            jjCalendar_IR datePointer2 = new jjCalendar_IR(datePointer.getYear(), datePointer.getMonth(), datePointer.getDay());
            int periodInMont = period / 30;
            if (period >= 30) {// اگر دوره به ماه بود 
                datePointer2.addMonth(periodInMont);
            } else {// اگر دوره های روزانه یا هفتگی مد نظر بود 
                datePointer2.addDay(period);
            }
            Double sum_a = 0.0;//محاسبه ی مجموع درون بازه برای هر دوره
            Double avg_a = 0.0;//محاسبه یا میانگین درون بازه برای هر دوره 
            int countForAvg = 0;
            // برای محاسبه ی تعداد داده های مجاز که غ غ ارزیابی نیستند
            while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), false) < datePointer2.getDBFormat_8length()) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
                if (dataset_val_date.get(i) != null) {
                    if (jjNumber.isFloat(dataset_val_date.get(i).get("_val").toString())) {
                        sum_a += Double.valueOf(dataset_val_date.get(i).get("_val").toString());
                        countForAvg++;
                    }
                }
                i++;//یک ردیف تعیین تکلیف شد
            }
            Map<String, Object> map = new HashMap();
            map.put("_date", datePointer.getViewFormat_10length());
            if (period >= 30) {// اگر دوره به ماه بود 
                datePointer.addMonth(periodInMont);
            } else {// اگر دوره های روزانه یا هفتگی مد نظر بود 
                datePointer.addDay(period);
            }
            if (sum_avg.startsWith("avg")) {
                if (countForAvg == 0) {// برای محاسبه ی میانگین
                    countForAvg = 1;
                }
                map.put("_val", sum_a / countForAvg);
            } else {
                map.put("_val", sum_a);
            }
            System.out.println("-----------");
            System.out.println(datePointer.getViewFormat_10length());
            System.out.println(sum_a);
            System.out.println(countForAvg);
            System.out.println(sum_avg);
            finalResult.add(map);
        }
        System.out.println("&&&&&&&&&&&&&&&>>>>" + sum_avg + " -->" + finalResult);
        return finalResult;
    }

    public static String IndicatorCompareResult(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        String id = jjTools.getParameter(request, _id);
        request.setAttribute("db", db);
        System.out.println("------->>>>>template/indicatorResult.jsp");
        request.getRequestDispatcher("template/indicatorResult.jsp").forward(request, response);
        return "";
    }

    public static String IndicatorCompareResultFilterBySection(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        String id = jjTools.getParameter(request, _id);
        request.setAttribute("db", db);
        System.out.println("------->>>>>template/indicatorResultFilterBySection.jsp");
        request.getRequestDispatcher("template/indicatorResultFilterBySection.jsp").forward(request, response);
        return "";
    }

    /**
     * یک ست پاسخ برای استفاده از شاخص ها برمی گرداند
     *
     * @param indicatorId
     * @param db
     * @param errorStr
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Map<String, Object>> getIndicatorResultset(int indicatorId, jjDatabaseWeb db, String errorStr, int startDate, int endDate) {
        int id = indicatorId;
//    String where = Forms._id + "=" + formId + " AND " + Forms._isActive + "=1";
        List<Map<String, Object>> indicatorRow = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._id + "=" + id));
        String formid = indicatorRow.get(0).get(Indicators._a).toString();
        String sql_a = "";
        String filterCondition_a = "";
        String sql_b = "";
        String filterCondition_b = "";
        String sql_c = "";
        String filterCondition_c = "";
        String sql_d = "";
        String filterCondition_d = "";
        String sql_e = "";
        String filterCondition_e = "";
        String sql_f = "";
        String filterCondition_f = "";
        String sql_g = "";
        String filterCondition_g = "";
        // اگر پاسخ ها با شرط مثلا اینکه بخش را فلان گزینه انتخاب کرده بود مد نظر باشد باید ست های پاسخی که این شرط را احراز می کنند را پیدا کنیم
        String dateCondition = FormAnswerSet._date + ">=" + startDate + " AND " + FormAnswerSet._date + "<=" + endDate;
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_a).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_a) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_a)
                    + " AND " + dateCondition
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_a = " AND (formanswers_answerSet_id=";
                filterCondition_a += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                filterCondition_a += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_a = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_a = filterCondition_a.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_a = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_a + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                    + " AND " + dateCondition
                    + " order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
            filterCondition_a = filterCondition_a.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_a = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_a + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei
                    + " AND " + dateCondition
                    + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_a = indicatorRow.get(0).get(Indicators._a).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_a = questionId_a.replaceAll("sum_", "");
            questionId_a = questionId_a.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_a)) {
                sql_a = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_a));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                System.out.println("______+" + indicatorRow.get(0).get(_filterOption_a).toString());
                System.out.println("______+" + indicatorRow.get(0).get(_filterOption_a).toString());
                System.out.println("______+" + indicatorRow.get(0).get(_filterOption_a).toString());
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_a = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_a + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " AND " + dateCondition
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_a = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_a + filterCondition_a
                            + " AND formQuestions_answersType='number' "
                            + " AND " + dateCondition
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";
                }
            }
        }
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_b).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_b) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_b)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_b = " AND (formanswers_answerSet_id=";
                filterCondition_b += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                filterCondition_b += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_b = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_b = filterCondition_b.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_b = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_b + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            filterCondition_b = filterCondition_b.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_b = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_b + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_b = indicatorRow.get(0).get(Indicators._b).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_b = questionId_b.replaceAll("sum_", "");
            questionId_b = questionId_b.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_b)) {
                sql_b = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_b));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_b = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_b + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_b = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_b + filterCondition_b
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";

                }

            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_c).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_c) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_c)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_c = " AND (formanswers_answerSet_id=";
                filterCondition_c += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                filterCondition_c += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_c = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_c = filterCondition_c.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_c = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_c + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            filterCondition_c = filterCondition_c.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_c = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_c + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_c = indicatorRow.get(0).get(Indicators._c).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_c = questionId_c.replaceAll("sum_", "");
            questionId_c = questionId_c.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_c)) {
                sql_c = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_c));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_c = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_c + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_c = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_c + filterCondition_c
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";

                }

            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_d).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_d) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_d)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_d = " AND (id=";
                filterCondition_d += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR id=");
                filterCondition_d += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_d = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_d = filterCondition_d.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_d = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_d + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            filterCondition_d = filterCondition_d.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_d = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_d + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_d = indicatorRow.get(0).get(Indicators._d).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_d = questionId_d.replaceAll("sum_", "");
            questionId_d = questionId_d.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_d)) {
                sql_d = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_d));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_d = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_d + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {
//                            System.out.println(filterCondition);
                    sql_d = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_d + filterCondition_d
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";
                }
            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$        
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_e).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_e) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_e)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_e = " AND (formanswers_answerSet_id=";
                filterCondition_e += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                filterCondition_e += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_e = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_e = filterCondition_e.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_e = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_e + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            filterCondition_e = filterCondition_e.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_e = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_e + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_e = indicatorRow.get(0).get(Indicators._e).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_e = questionId_e.replaceAll("sum_", "");
            questionId_e = questionId_e.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_e)) {
                sql_e = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_e));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_e = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_e + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_e = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_e + filterCondition_e
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";

                }

            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_f).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_f) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_f)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_f = " AND (formanswers_answerSet_id=";
                filterCondition_f += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                filterCondition_f += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_f = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_f = filterCondition_f.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_f = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_f + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
            filterCondition_f = filterCondition_f.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_f = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_f + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_f = indicatorRow.get(0).get(Indicators._f).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_f = questionId_f.replaceAll("sum_", "");
            questionId_f = questionId_f.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_f)) {
                sql_f = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_f));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_f = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_f + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_f = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_f + filterCondition_f
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";

                }

            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //#################################################################
        if (jjNumber.isDigit(indicatorRow.get(0).get(_filterOption_g).toString())) {
            //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
            List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                    + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                    + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                    + " where formanswers_questionId=" + indicatorRow.get(0).get(_filterQuestion_g) + " AND  formanswers_answer=" + indicatorRow.get(0).get(_filterOption_g)
                    + " ; ;"
            ));
            if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                filterCondition_g = " AND (id=";
                filterCondition_g += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR id=");
                filterCondition_g += " )";
            } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                filterCondition_g = " AND id<0 ";
            }
        }
        if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
            filterCondition_g = filterCondition_g.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
            formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
            sql_g = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_g + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else if (formid.startsWith("avgOfForm_")) {
            filterCondition_g = filterCondition_g.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
            formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
            sql_g = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                    + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                    + formid + filterCondition_g + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
        } else {// اگر متوسط و مجموع فرم مد نظر نبود        
            String questionId_g = indicatorRow.get(0).get(Indicators._g).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
            questionId_g = questionId_g.replaceAll("sum_", "");
            questionId_g = questionId_g.replaceAll("avg_", "");
            if (!jjNumber.isDigit(questionId_g)) {
                sql_g = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
            } else {
                //انتخاب همه ی پاسخ های داده شده به فرم
                List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_g));
                if (questionsRow.isEmpty()) {
                    System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                    return indicatorRow;
                }
                if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                        || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                    sql_g = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_g + filterCondition_a
                            + " AND formQuestionOptions_value<>''  "
                            + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " group by formanswers_answerSet_id"
                            + " ORDER by formAnswers_date ASC;;";

                } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                    sql_g = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                            + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_g + filterCondition_g
                            + " AND formQuestions_answersType='number' "
                            + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                            + " order by formAnswers_date ASC;";

                }

            }
        }
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        // بعد از بدست آورن اس کیو ال ها حالا دیتا ست های خام را برای محاسبه نهایی بدست می آوریم

        System.out.println(
                "run sql_a=");
        List<Map<String, Object>> values_date_a = jjDatabaseWeb.separateRow(db.otherSelect(sql_a));// داده در یک تاریخ مشخص

        System.out.println(
                "run sql_b=");
        List<Map<String, Object>> values_date_b = jjDatabaseWeb.separateRow(db.otherSelect(sql_b));// داده در یک تاریخ مشخص
//
        System.out.println(
                "run sql_c=");
        List<Map<String, Object>> values_date_c = jjDatabaseWeb.separateRow(db.otherSelect(sql_c));// داده در یک تاریخ مشخص
//
        System.out.println(
                "run sql_d=");
        List<Map<String, Object>> values_date_d = jjDatabaseWeb.separateRow(db.otherSelect(sql_d));// داده در یک تاریخ مشخص
//
        System.out.println(
                "run sql_e=");
        List<Map<String, Object>> values_date_e = jjDatabaseWeb.separateRow(db.otherSelect(sql_e));// داده در یک تاریخ مشخص
//
        System.out.println(
                "run sql_f=");
        List<Map<String, Object>> values_date_f = jjDatabaseWeb.separateRow(db.otherSelect(sql_f));// داده در یک تاریخ مشخص
        System.out.println(
                "run sql_g=");
        List<Map<String, Object>> values_date_g = jjDatabaseWeb.separateRow(db.otherSelect(sql_g));// داده در یک تاریخ مشخص

        int periodInDay = jjNumber.isDigit(indicatorRow.get(0).get(Indicators._periodOfCollectiong).toString()) ? Integer.valueOf(indicatorRow.get(0).get(Indicators._periodOfCollectiong).toString()) : 30;
        String colectionType_a = indicatorRow.get(0).get(Indicators._a).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_a = Indicators.createDataSetForIndicatorInverse(values_date_a, startDate, endDate, periodInDay, colectionType_a);
        String colectionType_b = indicatorRow.get(0).get(Indicators._b).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_b = Indicators.createDataSetForIndicatorInverse(values_date_b, startDate, endDate, periodInDay, colectionType_b);
        String colectionType_c = indicatorRow.get(0).get(Indicators._c).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_c = Indicators.createDataSetForIndicatorInverse(values_date_c, startDate, endDate, periodInDay, colectionType_c);
        String colectionType_d = indicatorRow.get(0).get(Indicators._d).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_d = Indicators.createDataSetForIndicatorInverse(values_date_d, startDate, endDate, periodInDay, colectionType_d);
        String colectionType_e = indicatorRow.get(0).get(Indicators._e).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_e = Indicators.createDataSetForIndicatorInverse(values_date_e, startDate, endDate, periodInDay, colectionType_e);
        String colectionType_f = indicatorRow.get(0).get(Indicators._f).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_f = Indicators.createDataSetForIndicatorInverse(values_date_f, startDate, endDate, periodInDay, colectionType_f);
        String colectionType_g = indicatorRow.get(0).get(Indicators._g).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
        List<Map<String, Object>> final_values_date_g = Indicators.createDataSetForIndicatorInverse(values_date_g, startDate, endDate, periodInDay, colectionType_g);
//    System.out.println(datePointer.getDBFormat_8length());
        double sum = 0;
        double avg = 0;
        String labels = "";
        String data = "";
        List<Map<String, Object>> indicatorFinalResultRow = new ArrayList<>();

        if (final_values_date_a.size() > 0) {
            for (int i = 0; i < final_values_date_a.size(); i++) {
                labels += "'" + final_values_date_a.get(i).get("_date") + "',";
//                Argument a = new Argument("a = " + (jjNumber.isFloat(final_values_date_a.get(i).get("_val").toString()) ? final_values_date_a.get(i).get("_val").toString() : "0"));
//                Argument b = new Argument("b = " + (jjNumber.isFloat(final_values_date_b.get(i).get("_val").toString()) ? final_values_date_b.get(i).get("_val").toString() : "0"));
//                Argument c = new Argument("c = " + (jjNumber.isFloat(final_values_date_c.get(i).get("_val").toString()) ? final_values_date_c.get(i).get("_val").toString() : "0"));
//                Argument d = new Argument("d = " + (jjNumber.isFloat(final_values_date_d.get(i).get("_val").toString()) ? final_values_date_d.get(i).get("_val").toString() : "0"));
//                Argument x = new Argument("x = " + (jjNumber.isFloat(final_values_date_e.get(i).get("_val").toString()) ? final_values_date_e.get(i).get("_val").toString() : "0"));
//                Argument y = new Argument("y = " + (jjNumber.isFloat(final_values_date_f.get(i).get("_val").toString()) ? final_values_date_f.get(i).get("_val").toString() : "0"));
//                Argument z = new Argument("z = " + (jjNumber.isFloat(final_values_date_g.get(i).get("_val").toString()) ? final_values_date_g.get(i).get("_val").toString() : "0"));
//            Constant d = new Constant("a = 60");
//                Expression exp = new Expression(indicatorRow.get(0).get(_formula).toString(), a);
//                Expression exp = new Expression(indicatorRow.get(0).get(_formula).toString(), a, b, c, d, x, y, z);
//                double v = exp.calculate();
                Map<String, Object> map = new HashMap<>();
                map.put("_date", final_values_date_a.get(i).get("_date"));// تاریخ یکی برای بقیه هم صدق می کند
//                map.put("_val", v);
                indicatorFinalResultRow.add(map);

//            System.out.println("(a*b)/c = " + v);
//                sum += v;
//                data += "'" + v + "',";
            }
            avg = sum / final_values_date_a.size();
        }
        return indicatorFinalResultRow;
    }

    /**
     * در بخش های مختلف
     *
     * @param indicatorId
     * @param db
     * @param errorStr
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Map<String, Object>> getIndicatorResultsetFilterBySection(int indicatorId, jjDatabaseWeb db, String errorStr, int startDate, int endDate, int questionId, String questionOptionId) {
        int id = indicatorId;
        List<Map<String, Object>> indicatorFinalResultRow = new ArrayList<>();
        List<Map<String, Object>> indicatorRow = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._id + "=" + id));

        String[] sectionArray = questionOptionId.split(",");

        for (int j = 0; j < sectionArray.length; j++) {
            List<Map<String, Object>> sectionLable = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._id + "=" + sectionArray[j]));
            String formid = indicatorRow.get(0).get(Indicators._a).toString();
            String sql_a = "";
            String filterCondition_a = "";
            String sql_b = "";
            String filterCondition_b = "";
            String sql_c = "";
            String filterCondition_c = "";
            String sql_d = "";
            String filterCondition_d = "";
            String sql_e = "";
            String filterCondition_e = "";
            String sql_f = "";
            String filterCondition_f = "";
            String sql_g = "";
            String filterCondition_g = "";
            // اگر پاسخ ها با شرط مثلا اینکه بخش را فلان گزینه انتخاب کرده بود مد نظر باشد باید ست های پاسخی که این شرط را احراز می کنند را پیدا کنیم
            String dateCondition = FormAnswerSet._date + ">=" + startDate + " AND " + FormAnswerSet._date + "<=" + endDate;

            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " AND " + dateCondition
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_a = " AND (formanswers_answerSet_id=";
                    filterCondition_a += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                    filterCondition_a += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_a = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_a = filterCondition_a.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_a = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_a + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                        + " AND " + dateCondition
                        + " order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
                filterCondition_a = filterCondition_a.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_a = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_a + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei
                        + " AND " + dateCondition
                        + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_a = indicatorRow.get(0).get(Indicators._a).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_a = questionId_a.replaceAll("sum_", "");
                questionId_a = questionId_a.replaceAll("avg_", "");
                System.out.println("////////////////////////////////quest" + questionId_a);
                if (!jjNumber.isDigit(questionId_a)) {
                    sql_a = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_a));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    System.out.println("______+" + sectionArray[j]);
                    System.out.println("______+" + sectionArray[j]);
                    System.out.println("______+" + sectionArray[j]);
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_a = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_a + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " AND " + dateCondition
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_a = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_a + filterCondition_a
                                + " AND formQuestions_answersType='number' "
                                + " AND " + dateCondition
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";
                    }
                }
            }
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_b = " AND (formanswers_answerSet_id=";
                    filterCondition_b += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                    filterCondition_b += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_b = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_b = filterCondition_b.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_b = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_b + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                filterCondition_b = filterCondition_b.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_b = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_b + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_b = indicatorRow.get(0).get(Indicators._b).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_b = questionId_b.replaceAll("sum_", "");
                questionId_b = questionId_b.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_b)) {
                    sql_b = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_b));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_b = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_b + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_b = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_b + filterCondition_b
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";

                    }

                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_c = " AND (formanswers_answerSet_id=";
                    filterCondition_c += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                    filterCondition_c += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_c = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_c = filterCondition_c.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_c = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_c + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                filterCondition_c = filterCondition_c.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_c = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_c + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_c = indicatorRow.get(0).get(Indicators._c).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_c = questionId_c.replaceAll("sum_", "");
                questionId_c = questionId_c.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_c)) {
                    sql_c = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_c));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_c = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_c + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_c = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_c + filterCondition_c
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";

                    }

                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_d = " AND (id=";
                    filterCondition_d += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR id=");
                    filterCondition_d += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_d = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_d = filterCondition_d.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_d = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_d + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                filterCondition_d = filterCondition_d.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد            
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_d = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_d + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_d = indicatorRow.get(0).get(Indicators._d).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_d = questionId_d.replaceAll("sum_", "");
                questionId_d = questionId_d.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_d)) {
                    sql_d = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_d));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_d = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_d + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {
//                            System.out.println(filterCondition);
                        sql_d = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_d + filterCondition_d
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";
                    }
                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$        
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_e = " AND (formanswers_answerSet_id=";
                    filterCondition_e += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                    filterCondition_e += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_e = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_e = filterCondition_e.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_e = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_e + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                filterCondition_e = filterCondition_e.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_e = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_e + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_e = indicatorRow.get(0).get(Indicators._e).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_e = questionId_e.replaceAll("sum_", "");
                questionId_e = questionId_e.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_e)) {
                    sql_e = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_e));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_e = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_e + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_e = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_e + filterCondition_e
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";

                    }

                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_f = " AND (formanswers_answerSet_id=";
                    filterCondition_f += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                    filterCondition_f += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_f = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_f = filterCondition_f.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_f = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_f + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
                filterCondition_f = filterCondition_f.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_f = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_f + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_f = indicatorRow.get(0).get(Indicators._f).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_f = questionId_f.replaceAll("sum_", "");
                questionId_f = questionId_f.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_f)) {
                    sql_f = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_f));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_f = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_f + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_f = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_f + filterCondition_f
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";

                    }

                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            //#################################################################
            if (jjNumber.isDigit(sectionArray[j])) {
                //@ToDo این قسمت بهینه شود چون لازم نیست چند بار این کد تکرار شود
                List<Map<String, Object>> filteredResultsets = jjDatabaseWeb.separateRow(db.otherSelect(
                        "SELECT GROUP_CONCAT( " + FormAnswers._answerSet_id + " SEPARATOR ',')AS answerSet_id "
                        + " FROM `hmis_formanswerset` left JOIN hmis_formanswers"
                        + " ON  formanswers_answerSet_id=hmis_formanswerset.id "
                        + " where formanswers_questionId=" + questionId + " AND  formanswers_answer=" + sectionArray[j]
                        + " ; ;"
                ));
                if (filteredResultsets.size() > 0) { // اگر با این شرایط پاسخی پیدا شد
                    filterCondition_g = " AND (id=";
                    filterCondition_g += filteredResultsets.get(0).get("answerSet_id").toString().replaceAll(",", " OR id=");
                    filterCondition_g += " )";
                } else {//اگر با این شرایط پاسخی وجود نداشت کاری باید بکنیم که کوئری بعدی نتیجه ای نداشته باشد و در واقع تهی باشد
                    filterCondition_g = " AND id<0 ";
                }
            }
            if (formid.startsWith("sumOfForm_")) {//اگر متغیر مورد نظر مسئول شاخص مجموع امتیازات فرم باشد
                filterCondition_g = filterCondition_g.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
                formid = formid.replace("sumOfForm_", "");// فقط اعداد باقی بماند 
                sql_g = "SELECT hmis_formanswerset_sum  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_g + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else if (formid.startsWith("avgOfForm_")) {
                filterCondition_g = filterCondition_g.replaceAll("formanswers_answerSet_id", "id");//  برای اینکه در این حالت سلکت روی خود انسرست است تفاوت دارد
//اگر متغیر مورد نظر مسئول شاخص میانگین امتیازات فرم باشد            
                formid = formid.replace("avgOfForm_", "");// فقط اعداد باقی بماند        
                sql_g = "SELECT 100*hmis_formanswerset_sum/hmis_formanswerset_avg  _val" // درصد پاسخگویی
                        + ",formAnswers_date  _date  FROM hmis_formanswerset WHERE  formAnswers_formId="
                        + formid + filterCondition_g + " AND hmis_formanswerset.formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' order by formAnswers_date ASC;";
            } else {// اگر متوسط و مجموع فرم مد نظر نبود        
                String questionId_g = indicatorRow.get(0).get(Indicators._g).toString();//اگر در این حالت بودیم مجموع یا میانگین یک فرم در یک بازه را نیاز داریم چون در این فیلد هم کد سوال و هم نوع مجموع گیری را داریم
                questionId_g = questionId_g.replaceAll("sum_", "");
                questionId_g = questionId_g.replaceAll("avg_", "");
                if (!jjNumber.isDigit(questionId_g)) {
                    sql_g = "SELECT hmis_formanswerset_avg  _val,formAnswers_date  _date  FROM hmis_formanswerset WHERE  id<0;"; // لیست تهی اگر این متغیر ست نشده باشد
                } else {
                    //انتخاب همه ی پاسخ های داده شده به فرم
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId_g));
                    if (questionsRow.isEmpty()) {
                        System.out.println("این سوال حذف شده و امکان ادامه محاسبه ی شاخص وجود ندارد. شما میتوانید شاخص را اصلاح کنید" + "خطای شماره ی 4453");
                        return indicatorRow;
                    }
                    if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("radio")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("select_option")
                            || questionsRow.get(0).get(FormQuestions._answersType).toString().equals("checkbox")) {

//                            System.out.println(filterCondition);
                        sql_g = "SELECT sum(formQuestionOptions_value) '_val',formAnswers_date  '_date'  " //sum هم برای چک باکس و ه رادیو ها جواب میدهد
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_g + filterCondition_a
                                + " AND formQuestionOptions_value<>''  "
                                + " AND (formQuestions_answersType='radio' or formQuestions_answersType='select_option' or formQuestions_answersType='checkbox')  "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " group by formanswers_answerSet_id"
                                + " ORDER by formAnswers_date ASC;;";

                    } else if (questionsRow.get(0).get(FormQuestions._answersType).toString().equals("number")) {

//                            System.out.println(filterCondition);
                        sql_g = "SELECT formanswers_answer  '_val',formAnswers_date  '_date'  "
                                + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                                + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id "
                                + " WHERE  hmis_formanswers.formanswers_questionId=" + questionId_g + filterCondition_g
                                + " AND formQuestions_answersType='number' "
                                + " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'"
                                + " order by formAnswers_date ASC;";

                    }

                }
            }
            //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            // بعد از بدست آورن اس کیو ال ها حالا دیتا ست های خام را برای محاسبه نهایی بدست می آوریم

            System.out.println(
                    "run sql_a=");
            List<Map<String, Object>> values_date_a = jjDatabaseWeb.separateRow(db.otherSelect(sql_a));// داده در یک تاریخ مشخص

            System.out.println(
                    "run sql_b=");
            List<Map<String, Object>> values_date_b = jjDatabaseWeb.separateRow(db.otherSelect(sql_b));// داده در یک تاریخ مشخص
//
            System.out.println(
                    "run sql_c=");
            List<Map<String, Object>> values_date_c = jjDatabaseWeb.separateRow(db.otherSelect(sql_c));// داده در یک تاریخ مشخص
//
            System.out.println(
                    "run sql_d=");
            List<Map<String, Object>> values_date_d = jjDatabaseWeb.separateRow(db.otherSelect(sql_d));// داده در یک تاریخ مشخص
//
            System.out.println(
                    "run sql_e=");
            List<Map<String, Object>> values_date_e = jjDatabaseWeb.separateRow(db.otherSelect(sql_e));// داده در یک تاریخ مشخص
//
            System.out.println(
                    "run sql_f=");
            List<Map<String, Object>> values_date_f = jjDatabaseWeb.separateRow(db.otherSelect(sql_f));// داده در یک تاریخ مشخص
            System.out.println(
                    "run sql_g=");
            List<Map<String, Object>> values_date_g = jjDatabaseWeb.separateRow(db.otherSelect(sql_g));// داده در یک تاریخ مشخص

            int periodInDay = 1000;
            String colectionType_a = indicatorRow.get(0).get(Indicators._a).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_a = Indicators.createDataSetForIndicatorInverse(values_date_a, startDate, endDate, periodInDay, colectionType_a);
            String colectionType_b = indicatorRow.get(0).get(Indicators._b).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_b = Indicators.createDataSetForIndicatorInverse(values_date_b, startDate, endDate, periodInDay, colectionType_b);
            String colectionType_c = indicatorRow.get(0).get(Indicators._c).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_c = Indicators.createDataSetForIndicatorInverse(values_date_c, startDate, endDate, periodInDay, colectionType_c);
            String colectionType_d = indicatorRow.get(0).get(Indicators._d).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_d = Indicators.createDataSetForIndicatorInverse(values_date_d, startDate, endDate, periodInDay, colectionType_d);
            String colectionType_e = indicatorRow.get(0).get(Indicators._e).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_e = Indicators.createDataSetForIndicatorInverse(values_date_e, startDate, endDate, periodInDay, colectionType_e);
            String colectionType_f = indicatorRow.get(0).get(Indicators._f).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_f = Indicators.createDataSetForIndicatorInverse(values_date_f, startDate, endDate, periodInDay, colectionType_f);
            String colectionType_g = indicatorRow.get(0).get(Indicators._g).toString().startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
            List<Map<String, Object>> final_values_date_g = Indicators.createDataSetForIndicatorInverse(values_date_g, startDate, endDate, periodInDay, colectionType_g);
//    System.out.println(datePointer.getDBFormat_8length());
            double sum = 0;
            double avg = 0;
            String labels = "";
            String data = "";

            if (final_values_date_a.size() > 0) {
                for (int i = 0; i < final_values_date_a.size(); i++) {
                    labels += "'" + final_values_date_a.get(i).get("_date") + "',";
//                    Argument a = new Argument("a = " + (jjNumber.isFloat(final_values_date_a.get(i).get("_val").toString()) ? final_values_date_a.get(i).get("_val").toString() : "0"));
//                    Argument b = new Argument("b = " + (jjNumber.isFloat(final_values_date_b.get(i).get("_val").toString()) ? final_values_date_b.get(i).get("_val").toString() : "0"));
//                    Argument c = new Argument("c = " + (jjNumber.isFloat(final_values_date_c.get(i).get("_val").toString()) ? final_values_date_c.get(i).get("_val").toString() : "0"));
//                    Argument d = new Argument("d = " + (jjNumber.isFloat(final_values_date_d.get(i).get("_val").toString()) ? final_values_date_d.get(i).get("_val").toString() : "0"));
//                    Argument x = new Argument("x = " + (jjNumber.isFloat(final_values_date_e.get(i).get("_val").toString()) ? final_values_date_e.get(i).get("_val").toString() : "0"));
//                    Argument y = new Argument("y = " + (jjNumber.isFloat(final_values_date_f.get(i).get("_val").toString()) ? final_values_date_f.get(i).get("_val").toString() : "0"));
//                    Argument z = new Argument("z = " + (jjNumber.isFloat(final_values_date_g.get(i).get("_val").toString()) ? final_values_date_g.get(i).get("_val").toString() : "0"));
//            Constant d = new Constant("a = 60");
//                Expression exp = new Expression(indicatorRow.get(0).get(_formula).toString(), a);
//                    Expression exp = new Expression(indicatorRow.get(0).get(_formula).toString(), a, b, c, d, x, y, z);
//                    double v = exp.calculate();
                    Map<String, Object> map = new HashMap<>();
                    map.put("_date", final_values_date_a.get(i).get("_date"));// تاریخ یکی برای بقیه هم صدق می کند
//                    map.put("_val", v);
                    map.put("_lable", sectionLable.get(0).get(FormQuestionOptions._lable));//questionOption
                    indicatorFinalResultRow.add(map);
//            System.out.println("(a*b)/c = " + v);
//                    sum += v;
//                    data += "'" + v + "',";
                }
            }

            avg = sum / final_values_date_a.size();
        }
        return indicatorFinalResultRow;
    }

    public static String swIndicators(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int countRow = 0;
            System.out.println("////////////////////////////");
            String title = jjTools.getParameter(request, "text");
            System.out.println(title);
            System.out.println("////////////////////////////");
            String[] splitTitle = title.split(",");
            System.out.println(splitTitle.length);
            System.out.println("////////////////////////////");
            StringBuilder html = new StringBuilder();
            html.append("<table id='swIndicatorsRefresh' class='table'><thead>");
            html.append("<th width='5%' class='r'>کد</th>");
            html.append("<th width='75%' class='r'>عنوان شاخص</th>");
            html.append("<th width='20%' class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int j = 0; j < splitTitle.length; j++) {
                DefaultTableModel dtm;
                if (jjNumber.isDigit(splitTitle[j])) {
                    dtm = db.Select(tableName, _id + "='" + splitTitle[j] + "'");//@ToDo فقط ستون هایی که لازم هست را بگیریم که در مصرف حاقظه رم سرفه جویی بشود
                } else {
                    dtm = db.Select(tableName, _title + "='" + splitTitle[j] + "'");//@ToDo فقط ستون هایی که لازم هست را بگیریم که در مصرف حاقظه رم سرفه جویی بشود
                }
                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
                countRow += row.size();
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr>");
                    html.append("<td class='r'>" + (row.get(i).get(_code).toString().isEmpty() ? row.get(i).get(_id).toString() : row.get(i).get(_code).toString()) + "</td>");
                    html.append("<td class='r' onclick='alert(22)';>" + row.get(i).get(_title).toString() + "</td>");
                    html.append("<td class='c'><a href='Server?do=Indicators.showResult&id=" + row.get(i).get(_id) + "' target='_blank' >"
                            + "<i class='p fa fa-bar-chart'></i></a></td>");
                    html.append("</tr>");
                }
            }
            html.append("</tbody></table>");
            String height = "200";
//            String height = jjTools.getParameter(request, "height");
            if (!jjNumber.isDigit(height)) {
                height = "200";
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel == "") {
                panel = "swIndicatorsAccreditationDashbord";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            if (countRow > 5) {
                script += Js.table("#swIndicatorsRefresh", height + "px", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست شاخص ها");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String updateIndicators(HttpServletRequest request, String indicatorId, jjDatabaseWeb db) throws Exception {
        try {
            String err = "";
            jjCalendar_IR endDate = new jjCalendar_IR();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + indicatorId));
//            if (row.get(0).get(_sendSmsAfterInsertFormAnswerSet).equals("1")) {//اگر  شاخص مورد نظر اجازه پیام را داشته باشد
            jjCalendar_IR startDate = new jjCalendar_IR(row.get(0).get(_startDate).toString());
            List<Map<String, Object>> set = getIndicatorResultset(Integer.parseInt(indicatorId), db, err, startDate.getDBFormat_8length(), endDate.getDBFormat_8length());
            String _date = set.get(set.size() - 1).get("_date").toString();
            String _val = set.get(set.size() - 1).get("_val").toString();
            System.out.println("_date=" + _date);
            System.out.println("_val=" + _val);//آخرین سطر مربوط به شاخص می آورد
            String text = "شاخص   " + " " + row.get(0).get(_title);
            if (Float.parseFloat(_val) <= Float.parseFloat(row.get(0).get(_dangerFloor).toString()) && Float.parseFloat(_val) >= Float.parseFloat(row.get(0).get(_dangerTop).toString())) {
//                    Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_responsibleRole).toString(), db), "1", "sms,app,email", null, "هشدار شاخص", text + " " + " در حیطه هشدار ", "", "هشدار", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
                System.out.println("danger");
            }
            if (Float.parseFloat(_val) <= Float.parseFloat(row.get(0).get(_goodFloor).toString()) && Float.parseFloat(_val) >= Float.parseFloat(row.get(0).get(_goodTop).toString())) {
                System.out.println("good");
//                    Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_responsibleRole).toString(), db), "1", "sms,app,email", null, "هشدار شاخص", text + " " + " در حیطه هشدار ", "", "هشدار", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
            }
            if (Float.parseFloat(_val) <= Float.parseFloat(row.get(0).get(_warnningFloor).toString()) && Float.parseFloat(_val) >= Float.parseFloat(row.get(0).get(_warnningTop).toString())) {
                System.out.println("warning");
//                    Messenger.sendMesseage(null, db, Role.getUeserIdByUserRole(row.get(0).get(_responsibleRole).toString(), db), "1", "sms,app,email", null, "هشدار شاخص", text + " " + " در حیطه هشدار ", "", "هشدار", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
            }
//            }
            return "";

        } catch (Exception ex) {
            return "";
        }
    }

    public static void taskIndicatorsReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskIndicatorsReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._startDate, _isActive + "=1 AND " + _sendSmsForIndicatorPeriod + "=1"));
        System.out.println("size=" + rows.size());
        String receivers = "";
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("rows.get(i).get(_startDate).toString()=" + rows.get(i).get(Indicators._startDate).toString());
            jjCalendar_IR date1 = new jjCalendar_IR(rows.get(i).get(Indicators._startDate).toString());
            jjCalendar_IR date2 = new jjCalendar_IR(rows.get(i).get(Indicators._startDate).toString());
            int periodCollectiong = Integer.valueOf(rows.get(i).get(_periodOfCollectiong).toString());
            int periodAnalysis = Integer.valueOf(rows.get(i).get(_periodOfAnalysis).toString());
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            while (today > date1.getDBFormat_8length()) {
                date1.addDay(periodCollectiong);//جمع آوری داده
            }
            while (today > date2.getDBFormat_8length()) {
                date2.addDay(periodAnalysis);//آنالیزداده
            }
//            String[] receiverMessageArray = receiver.split(",");//آی دی گیرنده  ها با این رشته از هم جدا می شود
            //اگر تاریخ امروز یا روزهای قبل بود همین الان بر اساس گیرنده ها و روش ارسال جدا بکند و ارسال بکند در غیر اینصورت برای اینکه قابل ویرایش باشد جدا نمی کنیم
            if (!rows.get(0).get(_responsibleRole).equals("") || !rows.get(0).get(_responsibleRole).equals("null")) {
                receivers = Role.getUeserIdByUserRole(rows.get(0).get(_responsibleRole).toString(), db);
            } else {
                receivers = rows.get(0).get(_responsibleUser).toString();
            }
            /////////////////////////////;کدپایین برای این است که پیام برای افرادی که دسترسی به شاخص دارنن  ارسال شود
//            List<Map<String, Object>> formsRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT  "
//                    + "GROUP_CONCAT(" + Forms._accessessUsers + " SEPARATOR '')AS ConcatAccessessUsers ,"
//                    + "GROUP_CONCAT(" + Forms._accessessRoles + " SEPARATOR '')AS ConcatAccessessRoles, "
//                    +Indicators._responsibleRole+","+Indicators._responsibleUser
//                    + " FROM hmis_forms," + Indicators.tableName + ""
//                    + " WHERE hmis_forms.id=" + Indicators._form_a + " OR "
//                    + " hmis_forms.id=" + Indicators._form_b + " OR "
//                    + " hmis_forms.id=" + Indicators._form_c + " OR "
//                    + " hmis_forms.id=" + Indicators._form_d + " OR "
//                    + " hmis_forms.id=" + Indicators._form_e + " OR "
//                    + " hmis_forms.id=" + Indicators._form_f + " OR "
//                    + " hmis_forms.id=" + Indicators._form_g));
//
//            receivers = formsRow.get(0).get("ConcatAccessessUsers").toString();
//            String[] ArrayReceiversRoles = formsRow.get(0).get("ConcatAccessessRoles").toString().split(",");
//            for (int j = 0; j < ArrayReceiversRoles.length; j++) {
//                receivers += Role.getUeserIdByUserRole(ArrayReceiversRoles[j], db);
//            }

            if (date1.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                String text = "لطفا نسبت به تکمیل و جمع آوری اطلاعات شاخص " + " " + rows.get(i).get(_title) + " " + " اقدام نمایید";
//                Messenger.sendMesseage(null, db, receivers, "1", "sms,app,email", null, "یادآوری تکمیل فرم", text, "", "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
                System.out.println("<<<<<<<ارسال پیام برای ایجاد کننده شاخصSend MESSAGE()");
                System.out.println("#################################################");
            }

            if (date2.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                String text = "لطفا نسبت به تحلیل شاخص  " + " " + rows.get(i).get(_title) + " " + " اقدام نمایید";
//                Messenger.sendMesseage(null, db, receivers, "1", "sms,app,email", null, "یادآوری تکمیل فرم", text, "", "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
//                Indicators.updateIndicators(null, rows.get(i).get(Indicators._id).toString(), db);//برای بررسی وضعیت

                System.out.println("<<<<<<<ارسال پیام برای ایجاد کننده شاخصSend MESSAGE()");
                System.out.println("#################################################");
            }
        }
    }

    public static String getIndicatorsChartScripts(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        String id = jjTools.getParameter(request, "id");
        if (!jjNumber.isDigit(id)) {
            return "";
        }

        DefaultTableModel dtm = db.otherSelect("SELECT  hmis_forms.id,hmis_forms.forms_title FROM " + Indicators.tableName
                + " LEFT JOIN  hmis_forms  ON  hmis_indicatiors.indicators_form_a = hmis_forms.id "
                + " or hmis_indicatiors.indicators_form_b = hmis_forms.id or "
                + " hmis_indicatiors.indicators_form_c = hmis_forms.id or "
                + " hmis_indicatiors.indicators_form_d = hmis_forms.id or "
                + " hmis_indicatiors.indicators_form_e = hmis_forms.id or "
                + " hmis_indicatiors.indicators_form_f = hmis_forms.id or "
                + " hmis_indicatiors.indicators_form_g = hmis_forms.id  WHERE hmis_indicatiors.id=" + id
        );//اگر بخواهیم شاخص هایی که با این فرم ثبت شده اند را در بیاوریم از این سلکت استفاده می کنیم 
        List<Map<String, Object>> indicatorFormsRow = jjDatabaseWeb.separateRow(dtm);
        List<Map<String, Object>> indicatorRow = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._id + "=" + id));
        //-----------------------------------------
        jjCalendar_IR startDate1 = new jjCalendar_IR(jjTools.getParameter(request, "indicator_startDate1").replaceAll("/", ""));
        jjCalendar_IR endDate1 = new jjCalendar_IR(jjTools.getParameter(request, "indicator_endDate1").replaceAll("/", ""));
        if (jjTools.getParameter(request, "indicator_startDate1").replaceAll("/", "").isEmpty()) {
            if (jjNumber.isDigit(indicatorRow.get(0).get(Indicators._startDate).toString())) {
                startDate1 = new jjCalendar_IR(indicatorRow.get(0).get(Indicators._startDate).toString());
                endDate1 = new jjCalendar_IR();//today
                if (indicatorRow.get(0).get(Indicators._periodOfCollectiong).toString().equals("30")) {
                    endDate1.setDate(endDate1.getYear(), endDate1.getMonth(), 1);//تا روز اول ماه جاری برای اینکه ممکن است وسط ماه جاری باشیم و داده ها کامل نباشد و آلارم اشتباه بدهد
                } else if (indicatorRow.get(0).get(Indicators._periodOfCollectiong).toString().equals("90")) {
                    if (endDate1.getMonth() <= 3) {
                        endDate1.setDate(endDate1.getYear(), 1, 1);//تاریخ پایان بشود تا اول سال جاری                     
                    } else {
                        endDate1.setDate(endDate1.getYear(), endDate1.getMonth() / 3 * 3 + 1, 1);//تا روز اول ماه جاری برای اینکه ممکن است وسط ماه جاری باشیم و داده ها کامل نباشد و آلارم اشتباه بدهد
                        endDate1.addDay(-1);
                    }
                    startDate1.setDate(endDate1.getYear() - 1, endDate1.getMonth(), endDate1.getDay());// در حالت سه ماهه تاریخ شروع را از اول سال قبل میگیریم
                    endDate1.addDay(-1);
                }
            } else {
                startDate1 = new jjCalendar_IR();
                startDate1.addYear(-1);
            }
            System.out.println("*&&&&&&&&&&&&********" + indicatorRow.get(0).get(Indicators._periodOfCollectiong).toString() + "***");
            System.out.println(startDate1);
            System.out.println(endDate1);
        }
        String err = "";
        List<Map<String, Object>> set1 = Indicators.getIndicatorResultset(Integer.parseInt(id), db, err, startDate1.getDBFormat_8length(), endDate1.getDBFormat_8length());
        List<Map<String, Object>> set2 = new ArrayList<>();
        System.out.println("-----------------------------------------------------------------------------------------------");
        if (!jjTools.getParameter(request, "indicator_startDate2").isEmpty()) {// اگر مقایسه با سال بعد مورد انتظار باشد
            jjCalendar_IR startDate2 = new jjCalendar_IR(jjTools.getParameter(request, "indicator_startDate2").replaceAll("/", ""));
            jjCalendar_IR endDate2 = new jjCalendar_IR(jjTools.getParameter(request, "indicator_startDate2").replaceAll("/", ""));
            endDate2.addDay((int) startDate1.different(endDate1));//
            set2 = Indicators.getIndicatorResultset(Integer.parseInt(id), db, err, startDate2.getDBFormat_8length(), endDate2.getDBFormat_8length());
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        double sum = 0;
        double avg = 0;
        String labels = "";
        String label1_2 = "";
        String data = "";
        String data2 = "";
        String data1_2 = "";
        String color1_2 = "";
        if (set1.size() > 0) {
            for (int i = 0; i < set1.size(); i++) {
                labels += "'" + set1.get(i).get("_date") + "',";
                data += "'" + set1.get(i).get("_val").toString() + "',";
                if (!set2.isEmpty()) {
                    data2 += "'" + set2.get(i).get("_val").toString() + "',";// ّبرای نمودار خطی چون باید دوتا دیتا ست مجزا داشته باشیم
                    label1_2 += "'" + set1.get(i).get("_date") + "',";
                    label1_2 += "'" + set2.get(i).get("_date") + "',";
                    data1_2 += "'" + set1.get(i).get("_val").toString() + "',";
                    data1_2 += "'" + set2.get(i).get("_val").toString() + "',";// برای نمودار ستونی چون نمیشود با دوتا دیتاست جدا محور افقی را درست تاریخ بزنیم
                    color1_2 += "'#0a89e3',";
                    color1_2 += "'#ca1853',";
                }
                sum += (jjNumber.isFloat(set1.get(i).get("_val").toString()) ? Double.parseDouble(set1.get(i).get("_val").toString()) : 0);
            }
            avg = sum / set1.size();
        }
        // چون ممکن است سطح هشدار بالاتر از سطح مطلوب باشد مثل میزان تلفات که کم آن مطلوب است
        String dataArrayStr = data + "," + data2;//برای اینکه بتوانیم مینیمم و ماکسیمم درستی بدست بیاوریم
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._goodTop).toString())) {
            dataArrayStr += ",'" + indicatorRow.get(0).get(Indicators._goodTop).toString() + "'";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._dangerTop).toString())) {
            dataArrayStr += ",'" + indicatorRow.get(0).get(Indicators._dangerTop).toString() + "'";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._goodFloor).toString())) {
            dataArrayStr += ",'" + indicatorRow.get(0).get(Indicators._goodFloor).toString() + "'";
        } else if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._dangerFloor).toString())) {
            dataArrayStr += ",'" + indicatorRow.get(0).get(Indicators._dangerFloor).toString() + "'";
        }

        String script = "";

        script += "var data_array = ['0'," + dataArrayStr.replaceAll("'NaN',", "").replaceAll(",+", ",") + "];";// یعنی اگر از یک کاما بیشتر بود ,+
        if (jjTools.getParameter(request, "chartType").equals("bar") || jjTools.getParameter(request, "chartType").isEmpty()) {
            script += "var ctx" + id + " = document.getElementById('getIndecatorlineChart" + id + "');"
                    + "var myChart" + id + " = new Chart(ctx" + id + ", { "
                    + "    type: 'bar',"
                    + "    data: {"
                    + "      labels: [" + (set2.isEmpty() ? labels : label1_2) + "],"
                    + "      datasets: ["
                    + "      {"
                    + "        label: 'از تاریخ " + startDate1.getViewFormat_10length() + " تا " + endDate1.getViewFormat_10length() + "',"
                    + "        lineTension:0.1,"
                    + "        data: [" + (set2.isEmpty() ? data : data1_2) + "],"
                    + "        borderColor: 'rgba(100,100,100,1)',"
                    + "        backgroundColor:" + (set2.isEmpty() ? "'#0a89e3'" : ("[" + color1_2 + "],"))
                    + "      }";
        } else {
            script += "var ctx" + id + " = document.getElementById('getIndecatorlineChart" + id + "');"
                    + "var myChart" + id + " = new Chart(ctx" + id + ", { "
                    + "    type: 'line',"
                    + "    data: {"
                    + "      labels: [" + labels + "],"
                    + "      datasets: ["
                    + "      {"
                    + "        label: 'از تاریخ " + startDate1.getViewFormat_10length() + " تا " + endDate1.getViewFormat_10length() + "',"
                    + "        lineTension:0.1,"
                    + "        data: [" + data + "],"
                    + "        borderColor: 'rgba(20,20,200,1)',"
                    + "        backgroundColor:'rgba(20,20,200,0.1)',"
                    + "      }";
            if (!set2.isEmpty()) {
                script += "      ,{"
                        + "        label: ' مقایسه با دوره مشابه از تاریخ" + jjTools.getParameter(request, "indicator_startDate2") + " ',"
                        + "        data: [" + data2 + "],"
                        + "        borderColor: 'rgba(202,24,83,1)',"
                        + "        backgroundColor:'rgba(202,24,83,0.1)',"
                        + "      }";
            }
        }
        script += "]"
                + "    },"
                + "    options: {"
                + "    lineTension:0,"
                + "    annotation: {"
                + "    annotations: ["
                + "{"
                + "   type: 'line',"
                + "   mode: 'horizontal',"
                + "   scaleID: 'y-axis-0',"
                + "   value: " + avg + ","
                + "   borderColor: 'gray',"
                + "   borderWidth: 1,"
                + "   label: {"
                + "     backgroundColor:'gray',"
                + "     enabled: true,"
                + "     content: '" + avg + " " + "میانگین " + "' "
                + "   }"
                + " },";
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._goodTop).toString())) {// رسم خط های افقی برای تعیین محدوده ها
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + (indicatorRow.get(0).get(Indicators._goodTop).toString()) + ","
                    + "   borderColor: 'green',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'green',"
                    + "     enabled: true,"
                    + "     position: 'right',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._goodTop).toString()) + " " + "مطلوب" + " ' "
                    + "   }"
                    + " },";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._goodFloor).toString())) {
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + (indicatorRow.get(0).get(Indicators._goodFloor).toString()) + ","
                    + "   borderColor: 'green',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'green',"
                    + "     enabled: true,"
                    + "     position: 'right',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._goodFloor).toString()) + " " + "مطلوب" + " ' "
                    + "   }"
                    + " },";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._warnningTop).toString())) {
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + (indicatorRow.get(0).get(Indicators._warnningTop).toString()) + ","
                    + "   borderColor: 'yellow',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'yellow',"
                    + "     enabled: true,"
                    + "     position: 'left',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._warnningTop).toString()) + " " + "هشدار" + " ' "
                    + "   }"
                    + " },";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._warnningFloor).toString())) {
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + (indicatorRow.get(0).get(Indicators._warnningFloor).toString()) + ","
                    + "   borderColor: 'yellow',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'yellow',"
                    + "     enabled: true,"
                    + "     position: 'left',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._warnningFloor).toString()) + " " + "هشدار" + " ' "
                    + "   }"
                    + " },";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._dangerTop).toString())) {
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + indicatorRow.get(0).get(Indicators._dangerTop).toString() + ","
                    + "   borderColor: 'red',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'red',"
                    + "     enabled: true,"
                    + "     position: 'right',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._dangerTop).toString()) + " " + "خطر" + " ' "
                    + "   }"
                    + " },";
        }
        if (jjNumber.isFloat(indicatorRow.get(0).get(Indicators._dangerFloor).toString())) {
            script += "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + indicatorRow.get(0).get(Indicators._dangerFloor).toString() + ","
                    + "   borderColor: 'red',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'red',"
                    + "     enabled: true,"
                    + "     position: 'right',"
                    + "     content: '" + (indicatorRow.get(0).get(Indicators._dangerFloor).toString()) + " " + "خطر" + " ' "
                    + "   }"
                    + " },";
        }
        script += "]"
                + "},"
                + "plugins: {"
                + "datalabels:"
                + "{"
                + "backgroundColor:'#abec9d',"
                + "borderRadius:5,"
                + "anchor:'end',"
                + "align:'top',"
                + "padding:'3',"
                + "color: '#0b0b0b',"
                + "font:"
                + "{"
                + "weight: 'bolder'"
                + ",size: '20'"
                + "},"
                + "formatter: function(value, context) {"
                + "return  Math.round(value*100)/100 ;"
                + "}"
                + "}"
                + "}"
                + ","
                + "responsive: true,"
                + "      title: {"
                + "        display: true,"
                + "      },"
                + "      legend: {"
                + "        display: true,"
                + "          labels: {"
                + "            display: true"
                + "          }"
                + "      },"
                + "      scales: {"
                + "        yAxes: [{"
                + "          ticks: {"
                + "            beginAtZero:true,"
                + "            fontSize: 10,"
                + "            max: Math.max.apply(this,data_array) +5,"//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                + "            min: Math.min.apply(this,data_array) -5,"
                + "          }"
                + "        }],"
                + "        xAxes: [{"
                + "          ticks: {"
                + "            beginAtZero:true,"
                + "            fontSize: 11"
                + "          }"
                + "        }]"
                + "      }"
                + "    }"
                + "  });";

        return script;
    }

    public static void taskIndicatorReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskIndicatorReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(tableName, _isActive + "=1 AND " + Indicators._periodOfCollectiong + " !='' AND " + Indicators._sendSmsForIndicatorPeriod + "=1 "));
        System.out.println("size=" + rows.size());
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("rows.get(i).get(_date).toString()=" + rows.get(i).get(_startDate).toString());
            jjCalendar_IR date = new jjCalendar_IR(rows.get(i).get(_startDate).toString());
            int period = Integer.valueOf(rows.get(i).get(_periodOfCollectiong).toString());
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            while (today > date.getDBFormat_8length()) {
                date.addDay(period);
            }
            String reciversID = "";
            if (date.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                if (!rows.get(i).get(_responsibleRole).equals("null") && !rows.get(i).get(_responsibleRole).equals("")) {
                    String[] responsibleRoleArray = rows.get(i).get(_responsibleRole).toString().split(",");
                    for (int j = 0; j < responsibleRoleArray.length; j++) {
                        reciversID += Role.getUeserIdByUserRole(responsibleRoleArray[j], db);
                    }
                } else if (!rows.get(i).get(_responsibleUser).equals("null") && !rows.get(i).get(_responsibleUser).equals("")) {
                    String[] responsibleUserArray = rows.get(i).get(_responsibleUser).toString().split(",");
                    for (int j = 0; j < responsibleUserArray.length; j++) {
                        reciversID += responsibleUserArray[j];
                    }   
                }
                System.out.println("reciversID=" + reciversID);
                String text
                        = "مسئول محترم"
                        + " <br/>"
                        + "شاخص :" + rows.get(i).get(_title).toString()
                        + "<br/>"
                        + "لطفا نسبت به جمع آوری داده های شاخص اقدام نمایید";
//                Messenger.sendMesseage(null, db, reciversID, "1", "sms,app,email", null, "یاد آوری سامانه : جمع آوری داده های شاخص  " + rows.get(i).get(_title), text, text, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleIndicators_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleIndicators_name));
                System.out.println("#################################################");
            }
        }
    }

}
                 
