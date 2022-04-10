/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import HMIS.FormAnswers;
import cms.access.Access_Group;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import jj.jjTime;

/**
 *
 * @author Mohammad
 */
public class FormAnswerSet {

    public static final String tableName = "hmis_formAnswerSet";
    public static final String _id = "id";
    public static final String _formId = "formAnswers_formId";
    public static final String _userId = "formAnswers_userId";
    public static final String _userName = "formAnswers_userName";
    public static final String _userRole = "formAnswers_userRole";//نقش هایی که کاربر به واسطه ی انها به این فرم دسترسی داشته است
    public static final String _userGroup = "formAnswers_userGroup";//نقش هایی که کاربر به واسطه ی انها به این فرم دسترسی داشته است
    public static final String _date = "formAnswers_date";
    public static final String _time = "formAnswers_time";//بخش یا بخش هایی که این فرم را باید ببینند
    public static final String _status = "formAnswers_status";//سمت هایی که به این فرم دسترسی 
    public static final String _departmentId = "formAnswers_departmentId";//سمت هایی که به این فرم دسترسی 
    public static final String _statusLog = "formAnswers_statusLog";//سمت هایی که به این فرم دسترسی 
    public static final String _userMAC = "formAnswers_userMAC";// مک آدرس پر کننده ی فرم
    public static final String _userIPV4 = "formAnswers_userIPV4";// آی پی پر کننده ی فرم
    public static final String _userIPV6 = "formAnswers_userIPV6";// آی پی ورژن شش پر کننده ی فرم
    public static final String _sum = "hmis_formanswerset_sum";// آی پی ورژن شش پر کننده ی فرم
    public static final String _avg = "hmis_formanswerset_avg";// آی پی ورژن شش پر کننده ی فرم
    public static final String lbl_insert = "ثبت و افزودن سوال";
    public static final String lbl_delete = "حذف فرم";
    public static final String lbl_edit = "ثبت ویرایش";
    public static final String lbl_add_en = "افزودن زبان انگلیسی";
    public static final String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static final String lbl_add_ar = "افزودن زبان عربی";
    public static final String lbl_edit_ar = "ویرایش بخش عربی";

    public static int rul_rfs = 0;//60;
    public static int rul_rfsComplitArshiveForms = 355;//60;
    public static int rul_rfsComplitFormsMe = 361;//60;
    public static int rul_rfsComplitArshiveFormsMe = 368;//60;

    public static int rul_rfs_own = 0;// 61;//فقط امکان دیدن فرم های ایجاد شده ی توسط خود ایجاد کننده را دارد // بر روی سمت
    public static int rul_ins = 0;// 62;
    public static int rul_edt = 0;// 63;
    public static int rul_dlt = 369;
    public static int rul_result_all = 0;// 65; //مشاهده ی نتایج همه ی فرم های تکمیل شده
    public static int rul_statistics_all = 0;// 66;
    public static int rul_7 = 0;// 67;
    public static int rul_8 = 0;// 68;
    public static int rul_9 = 0;// 69;
    public static int rul_10 = 0;// 70;

//    public static int rul_lng5 = 68;
    public static final String lbl_add_ln2 = "برچسب";
    public static final String statues_sabteAvalie = "ثبت اولیه";
    public static final String statues_sabteNahei = "ثبت نهایی";
    public static final String lbl_add_ln3 = "افزودن زبان عربی";
    public static final String lbl_edit_ln3 = "ویرایش بخش عربی";
    public static final String lbl_add_ln4 = "افزودن زبان آلمانی";
    public static final String lbl_edit_ln4 = "ویرایش بخش آلمانی";
    public static final String lbl_add_ln5 = "افزودن زبان چینی";
    public static final String lbl_edit_ln5 = "ویرایش بخش چینی";

    /**
     * نمایش فرم های در اختیار نقش یا شخص جاری در سشن
     *
     * @param request
     * @param response
     * @param db
     * @param needString // اگر یک کلاس جاوایی یا یک فایل جی اس پی ین نتایج را
     * میخواهد لازم نیست به صفحه ی دیگری برویم و پاسخ را بصورت استرینگ پس می
     * دهیم
     * @return
     * @throws Exception
     */
    public static String showMyForms(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsComplitFormsMe)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            System.out.println(">>>>>>>>UserRoles is:" + userRoleInsession);
            String where = " WHERE (";
            String userRoles[] = userRoleInsession.split(",");
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%," + userRoles[i] + ",%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!jjTools.getSeassionUserRole(request).isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            String userGroupId[] = userGroupInsession.split(",");
            for (int i = 0; i < userGroupId.length; i++) {
                where += Forms._accessessGroupId + " like " + "'%," + userGroupId[i] + ",%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!jjTools.getSessionAttribute(request, "#GROUP_ID").isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            where += Forms._accessessUsers + " like " + "'%," + userId + ",%'" + " OR ";
            where += Forms._accessessUsers + " like " + "'%ALL%' ) OR  ";
            where += "(" + Forms._accessessGroupId + "=',null,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "=',null,' AND ";
            where += Forms._accessessUsers + "=',null,' )"
                    + "AND ";
            where += Forms._isActive + "=1 AND ";
            where += " ( (" + Forms._expireDate + " > " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + ") ";
            where += " OR (";
            where += Forms._expireDate + " = " + jjCalendar_IR.getDatabaseFormat_8length(null, true); // تاریخ مساوی امروز باشد و
            where += " AND ";
            where += Forms._expireTime + " >= " + jjTime.getTime4lenth("") + ")"; // ساعت بزرگتر از ّساعت جاری باشد
            where += ")";
            where += "; ";

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + where));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            html.append("<table id='refreshMyForms' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل یک فرم</th>");
//            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr onclick='" + Js.jjFormAnswerSet.refreshMyAnswers(formRows.get(i).get(_id).toString()) + "'>");
                html.append("<td class='r'>" + (!formRows.get(i).get(Forms._code).toString().isEmpty() ? "(" + formRows.get(i).get(Forms._code) + ") " : "") + formRows.get(i).get(Forms._id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='c'><i class='p icon fa fa-pencil' onclick='" + Js.jjFormAnswerSet.refreshMyAnswers(formRows.get(i).get(_id).toString()) + "'></i></td>");
//                html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.refreshMyAnswers(formRows.get(i).get(_id).toString()) + "'></i></td>");
//                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(Forms._id) + "' target='_blank' >"
//                        + "<i class='p fa fa-bar-chart'></i></a></td>");
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
                script.append(Js.setHtml("#swMyFormsTbl", html));
                script.append(Js.table("#refreshMyForms", "", 0, "", "لیست اخبار"));
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش همه ی فرم های تکمیل شده برای استفاده از قابلیت های حدول هوشمند و
     * آمار گیری
     *
     * @param request
     * @param response
     * @param db
     * @param needString // اگر یک کلاس جاوایی یا یک فایل جی اس پی ین نتایج را
     * میخواهد لازم نیست به صفحه ی دیگری برویم و پاسخ را بصورت استرینگ پس می
     * دهیم
     * @return
     * @throws Exception
     */
    public static String showAllForms(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsComplitArshiveForms)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                    db.otherSelect("SELECT "
                            + tableName + ".*,"
                            + Role._title + ","
                            + Access_Group._title + ","
                            + FormAnswerSet._departmentId + ","
                            + Forms._title + ""
                            + " FROM " + tableName
                            + " LEFT JOIN " + Forms.tableName + " ON   hmis_forms.id=" + _formId
                            + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                            + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                            + ";"));
            List<Map<String, Object>> rowDistinct = jjDatabaseWeb.separateRow(db.otherSelect("SELECT"
                    + " DISTINCT(" + _formId + ")," + Forms._title + " ," + FormAnswerSet._formId
                    + " FROM  " + tableName
                    + " LEFT JOIN  hmis_forms ON " + FormAnswerSet._formId + "=hmis_forms.id"
            ));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary'>");
            html.append("<div class='card-header col-lg-12 bg-primary tx-white'>لیست فرم ها و چک لیست های تعریف شده</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div class='card bd-primary col-lg-12'>");
            html.append("<div class='card-header bg-info tx-white p row'>جستجو براساس نام گروه بندی فرم</div>");
            html.append("<div id='formSearch' >");
            html.append("<div class='pd-sm-30 row'>فرم");
            html.append("<select class='form-control'  name='formAnswers_formId'  id='searchFormId'"
                    + " onchange='hmisFormAnswerSets.refreshWithSearchFormId();'"
                    + ">");
            html.append("<option value=''></option>");
            html.append("<option value='ALL'>همه</option>");
            for (int i = 0; i < rowDistinct.size(); i++) {
                html.append("<option value='" + rowDistinct.get(i).get(FormAnswerSet._formId) + "'>" + rowDistinct.get(i).get(Forms._title) + "</option>");
            }
            html.append("</select>");
            html.append("</div>");
            html.append(""
                    + "<div class='col-lg'>\n "
                    + "<p class='col-lg'>"
                    + " <a style='color:#fff' class='btn btn-success pd-sm-x-15 mg-sm-r-4 tx-white col-lg' onclick='hmisFormAnswerSets.showFormForOwner();'>فرم های تکمیل شده مالک فرم </a>                                   "
                    + "</p>  "
                    + " </div> "
                    + "");
            html.append("</div>");
            html.append("<div class='col-lg-12'>"
                    + "<div class='col-lg' id='swAllAnswerSetsTable'></div>"
                    + "</div> "
                    + "</div> "
            );
            html.append("</div>");
            html.append("</div>");
//            html.append("</div>"); 

//            
//            html.append(""
//                    + "<div class='card bd-primary col-lg-12'>"
//                    //                    + "<div class='card- bg- tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n"
//                    + "<div class='card-header bg-info tx-white p row'>جستجو براساس نام فرم</div>"
//            );
//            html.append(""
//                    + "<div id='formSearch' class='col-lg-12'>"
//                    + "<div class='col-lg pd-sm-30'>نام فرم"
//                    + "<select class='form-control' id='searchFormId' onchange='hmisFormAnswerSets.refreshWithSearchFormId();'>");
//            html.append("<option value=''></option>");
//            html.append("<option value='ALL'>همه</option>");
//            for (int i = 0; i < rowDistinct.size(); i++) {
//                html.append("<option value='" + rowDistinct.get(i).get(FormAnswerSet._formId) + "'>" + rowDistinct.get(i).get(Forms._title) + "</option>");
//            }
//            html.append("</select>"
//                    + "</div>");
//        
//            html.append(""
//                    + "</div>"
//            );
//  
//            html.append(""
//                    + "<div class='col-lg-12'>"
//                    + "<div class='col-lg' id='swAllAnswerSetsTable'></div>"
//                    + "</div> "
//                    + "</div> "
//            );
//            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
//            html.append("<table id='swAllAnswerSetsTable' class='table  table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
//            html.append("<th class='r'>کد</th>");
//            html.append("<th class='r'>عنوان فرم</th>");
//            html.append("<th class='c'>تکمیل کننده</th>");
//            html.append("<th class='c'>نام بخش</th>");
//            html.append("<th class='c'>آی پی و مک آدرس</th>");
//            html.append("<th class='c'>نتایج</th>");
//            html.append("<th class='c'>مشاهده ی پاسخ</th>");
//            html.append("<th class='c'>حذف فرم</th>");
//            html.append("<th class='c'>آنالیز و آمار</th>");
//            html.append("</thead><tbody>");
//            for (int i = 0; i < formRows.size(); i++) {
//                html.append("<tr class='" + getClassByStatus(formRows.get(i).get(_status).toString()) + "'>");
//                html.append("<td class='r'>" + formRows.get(i).get(_id) + "</td>");
//                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
//                html.append("<td class='r'>" + formRows.get(i).get(_userName)
//                        + "<br/> " + formRows.get(i).get(Role._title)
//                        + "<br/> " + formRows.get(i).get(Access_Group._title)
//                        + "<br/>" + jjCalendar_IR.getViewFormat(formRows.get(i).get(_date)) + "-" + jjTime.getTime5lenth(formRows.get(i).get(_time).toString()) + "</td>");
//                html.append("<td class='r'>" + Department.getDepartmentName(formRows.get(i).get(FormAnswerSet._departmentId).toString(), db) + "</td>");
//                html.append("<td class='r'>" + formRows.get(i).get(_userIPV4) + "<br/>" + formRows.get(i).get(_userMAC) + "</td>");
//                html.append("<td class='l'>sum:" + formRows.get(i).get(_sum) + "<br/>max:" + formRows.get(i).get(_avg) + "</td>");
//                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
//                        + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
//                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
//                if (accDelete) {
//                    html.append("<td class='c p'><i  class='fa fa-trash'  onclick='" + Js.jjFormAnswerSet.delete(formRows.get(i).get(_id).toString()) + "' ></i></td>");
//                } else {
//                    html.append("<td><div></div></td>");
//                }
//                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "' target='_blank' >"
//                        + " <i class='p fa fa-bar-chart'></i></a></td>");
//                html.append("</tr>");
//            }
//            html.append("</tbody></table></div>");
//            html.append("</div>");
//            html.append("</div>");
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                String panel = jjTools.getParameter(request, "panel");

                if (panel.equals("")) {
                    panel = "swAllAnswerSets";
                }
                script.append(Js.setHtml("#" + panel, html));
//                script.append(Js.table("#swAllAnswerSetsTable", "", 0, "", "لیست فرم های تکمیل شده"));
                script.append("$('#searchFormId').select2();");
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String showFormForOwner(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsComplitArshiveForms)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String where = "";
            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                    db.otherSelect("SELECT "
                            + tableName + ".*,"
                            + Forms._title + ","
                            + Role._title + ","
                            + Access_Group._title + ","
                            + FormAnswerSet._departmentId + ""
                            + " FROM " + tableName
                            + " LEFT JOIN " + Forms.tableName + " ON hmis_forms.id=" + _formId
                            + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                            + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                            + "  where " + Forms._ownerId + "=" + jjTools.getSeassionUserId(request)
                    ));
            StringBuilder html = new StringBuilder();
//            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
//            html.append("<div class='card-body pd-sm-30  row col-lg-12'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='swAnswerSetsOwnerFormTable' class='table  table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل کننده</th>");
            html.append("<th class='c'>بخش</th>");
            html.append("<th class='c'>آی پی و مک آدرس</th>");
            html.append("<th class='c'>نتایج</th>");
            html.append("<th class='c'>مشاهده ی پاسخ</th>");
            html.append("<th class='c'>حذف فرم</th>");
            html.append("<th class='c'>برگشت فرم به ثبت اولیه</th>");
            html.append("<th class='c'>ویرایش</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr class='" + getClassByStatus(formRows.get(i).get(_status).toString()) + "'>");
                html.append("<td class='r'>" + formRows.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userName)
                        + "<br/> " + formRows.get(i).get(Role._title)
                        + "<br/> " + formRows.get(i).get(Access_Group._title)
                        + "<br/>" + jjCalendar_IR.getViewFormat(formRows.get(i).get(_date)) + "-" + jjTime.getTime5lenth(formRows.get(i).get(_time).toString()) + "</td>");
                html.append("<td class='r'>" + Department.getDepartmentName(formRows.get(i).get(FormAnswerSet._departmentId).toString(), db) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userIPV4) + "<br/>" + formRows.get(i).get(_userMAC) + "</td>");
                html.append("<td class='l'>sum:" + formRows.get(i).get(_sum) + "<br/>max:" + formRows.get(i).get(_avg) + "</td>");
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                        + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                if (accDelete) {
                    html.append("<td class='c p'><i  class='fa fa-trash'  onclick='" + Js.jjFormAnswerSet.delete(formRows.get(i).get(_id).toString()) + "' ></i></td>");
                    if (formRows.get(i).get(_status).toString().equals(statues_sabteNahei)) {//اگر وضعیت فرم تکمیل شده بود نمایش داده شود
                        html.append("<td class='c p'><i  class='icon ion-compose'  onclick='hmisFormAnswerSets.requestChangeStatus(" + formRows.get(i).get(FormAnswerSet._id).toString() + ")' ></i></td>");
                    } else {
                        html.append("<td><div></div></td>");
                    }
                    if (formRows.get(i).get(_status).toString().equals(statues_sabteAvalie)) {
                        html.append("<td class='c'><a "
                                + " onclick='hmisFormAnswerSets.selectMyAnswersAfterQuestion(" + formRows.get(i).get(_formId) + "," + formRows.get(i).get(_id) + " );' "
                                + "><i class='p icon fa fa-pencil'></i></a></td>");
                    } else {
                        html.append("<td><div></div></td>");
                    }
                } else {
                    html.append("<td><div></div></td>");
                    html.append("<td><div></div></td>");
                    html.append("<td><div></div></td>");
                }

                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "' target='_blank' >"
                        + " <i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            html.append("</div>");
//            html.append("</div>");
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                String panel = jjTools.getParameter(request, "panel");

                if (panel.equals("")) {
                    panel = "swAllAnswerSetsTbl";
                }
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.table("#swAnswerSetsOwnerFormTable", "", 0, "", "لیست فرم های تکمیل شده"));
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String refreshWithSearchFormId(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }

            if (!Access_User.hasAccess(request, db, rul_rfsComplitArshiveForms)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            if (jjTools.getParameter(request, FormAnswerSet._formId).equals("")) {
                return "";
            }
            String where = "";

            if (jjTools.getParameter(request, FormAnswerSet._formId).equals("ALL")) {
                where += " ";
            } else if (jjNumber.isDigit(jjTools.getParameter(request, FormAnswerSet._formId).toString())) {
                where += " where ";
                where += "   (formAnswers_formId=" + jjTools.getParameter(request, FormAnswerSet._formId) + ")";
            } else {
            }
            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                    db.otherSelect("SELECT "
                            + tableName + ".*,"
                            + Forms._title + ","
                            + Role._title + ","
                            + Access_Group._title + ","
                            + FormAnswerSet._departmentId + ""
                            + " FROM " + tableName
                            + " LEFT JOIN " + Forms.tableName + " ON   hmis_forms.id=" + _formId
                            + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                            + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                            + " " + where
                    ));
            StringBuilder html = new StringBuilder();
//            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
//            html.append("<div class='card-body pd-sm-30  row col-lg-12'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='swAnswerSetsOwnerFormTable' class='table  table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل کننده</th>");
            html.append("<th class='c'>بخش</th>");
            html.append("<th class='c'>آی پی و مک آدرس</th>");
            html.append("<th class='c'>نتایج</th>");
            html.append("<th class='c'>مشاهده ی پاسخ</th>");
            html.append("<th class='c'>حذف فرم</th>");
            html.append("<th class='c'>برگشت به وضعیت ثبت اولیه</th>");
            html.append("<th class='c'>ویرایش</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr class='" + getClassByStatus(formRows.get(i).get(_status).toString()) + "'>");
                html.append("<td class='r'>" + formRows.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userName)
                        + "<br/> " + formRows.get(i).get(Role._title)
                        + "<br/> " + formRows.get(i).get(Access_Group._title)
                        + "<br/>" + jjCalendar_IR.getViewFormat(formRows.get(i).get(_date)) + "-" + jjTime.getTime5lenth(formRows.get(i).get(_time).toString()) + "</td>");
                html.append("<td class='r'>" + Department.getDepartmentName(formRows.get(i).get(FormAnswerSet._departmentId).toString(), db) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userIPV4) + "<br/>" + formRows.get(i).get(_userMAC) + "</td>");
                html.append("<td class='l'>sum:" + formRows.get(i).get(_sum) + "<br/>max:" + formRows.get(i).get(_avg) + "</td>");
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                        + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                if (accDelete) {
                    html.append("<td class='c p'><i  class='fa fa-trash'  onclick='" + Js.jjFormAnswerSet.delete(formRows.get(i).get(_id).toString()) + "' ></i></td>");
                    if (formRows.get(i).get(_status).toString().equals(statues_sabteNahei)) {//اگر وضعیت فرم تکمیل شده بود نمایش داده شود
                        html.append("<td class='c p'><i  class='icon ion-compose'  onclick='hmisFormAnswerSets.requestChangeStatus(" + formRows.get(i).get(FormAnswerSet._id).toString() + ")' ></i></td>");
                    } else {
                        html.append("<td><div></div></td>");
                    }
                    if (formRows.get(i).get(_status).toString().equals(statues_sabteAvalie)) {
                        html.append("<td class='c'><a "
                                + " onclick='hmisFormAnswerSets.selectMyAnswersAfterQuestion(" + formRows.get(i).get(_formId) + "," + formRows.get(i).get(_id) + " );' "
                                + "><i class='p icon fa fa-pencil'></i></a></td>");
                    } else {
                        html.append("<td><div></div></td>");
                    }
                } else {
                    html.append("<td><div></div></td>");
                    html.append("<td><div></div></td>");
                    html.append("<td><div></div></td>");
                }
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "' target='_blank' >"
                        + " <i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            html.append("</div>");
//            html.append("</div>");    
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                String panel = jjTools.getParameter(request, "panel");

                if (panel.equals("")) {
                    panel = "swAllAnswerSetsTbl";
                }
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.table("#swAnswerSetsOwnerFormTable", "", 0, "", "لیست فرم های تکمیل شده"));
                script.append("$('#searchFormId').select2();");
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش همه ی فرم های تکمیل شده برای استفاده از قابلیت های حدول هوشمند و
     * آمار گیری
     *
     * @param request
     * @param response
     * @param db
     * @param needString // اگر یک کلاس جاوایی یا یک فایل جی اس پی ین نتایج را
     * میخواهد لازم نیست به صفحه ی دیگری برویم و پاسخ را بصورت استرینگ پس می
     * دهیم
     * @return
     * @throws Exception
     */
    public static String showAllMyAnswerSets(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsComplitArshiveFormsMe)) {// اگر کاربر لاگین نکرده بود
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                    db.otherSelect("SELECT "
                            + tableName + ".*,"
                            + Role._title + ","
                            + Access_Group._title + ","
                            + Forms._title
                            + " FROM " + tableName
                            + " LEFT JOIN " + Forms.tableName + " ON   hmis_forms.id=" + _formId
                            + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                            + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                            + " WHERE " + _userId + "=" + jjTools.getSeassionUserId(request) + ";"));
            StringBuilder html = new StringBuilder();
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='swAllMyAnswerSetsTable' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل کننده</th>");
            html.append("<th class='c'>آی پی و مک آدرس</th>");
            html.append("<th class='c'>نتایج</th>");
            html.append("<th class='c'>مشاهدی پاسخ</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr class='" + getClassByStatus(formRows.get(i).get(_status).toString()) + "'>");
                html.append("<td class='r'>" + formRows.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userName)
                        + "<br/> " + formRows.get(i).get(Role._title)
                        + "<br/> " + formRows.get(i).get(Access_Group._title)
                        + "<br/>" + jjCalendar_IR.getViewFormat(formRows.get(i).get(_date)) + "-" + jjTime.getTime5lenth(formRows.get(i).get(_time).toString()) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userIPV4) + "<br/>" + formRows.get(i).get(_userMAC) + "</td>");
                html.append("<td class='l'>sum:" + formRows.get(i).get(_sum) + "<br/>max:" + formRows.get(i).get(_avg) + "</td>");
                if (formRows.get(i).get(_status).equals(statues_sabteAvalie)) {// اگر ثبت اولیه بود آیکن ویرایش و تایع ویرایش را بگذاریم
                    html.append("<td class='c'><a href='Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                            + "><i class='fa fa-pencil' " + "</i></a></td>");
                } else {
                    html.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                            + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
                }
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "' target='_blank' >"
                        + " <i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            html.append("</div>");
            html.append("</div>");
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                String panel = jjTools.getParameter(request, "panel");

                if (panel.equals("")) {
                    panel = "swAllMyAnswerSets";
                }
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.table("#swAllMyAnswerSetsTable", "", 0, "", "لیست فرم ها"));
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش همه ی فرم های تکمیل شده برای استفاده از قابلیت های حدول هوشمند و
     * آمار گیری
     *
     * @param request
     * @param response
     * @param db
     * @param needString // اگر یک کلاس جاوایی یا یک فایل جی اس پی ین نتایج را
     * میخواهد لازم نیست به صفحه ی دیگری برویم و پاسخ را بصورت استرینگ پس می
     * دهیم
     * @return
     * @throws Exception
     */
    public static String showAllMyAnswerSetsMobile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs) || jjTools.getSeassionUserId(request) == 0) {// اگر کاربر لاگین نکرده بود
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                    db.otherSelect("SELECT "
                            + tableName + ".*,"
                            + Role._title + ","
                            + Access_Group._title + ","
                            + Forms._title
                            + " FROM " + tableName
                            + " LEFT JOIN " + Forms.tableName + " ON   hmis_forms.id=" + _formId
                            + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                            + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                            + " WHERE " + _userId + "=" + jjTools.getSeassionUserId(request) + ";"));
            StringBuilder html = new StringBuilder();
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='swAllMyAnswerSetsTable' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل کننده</th>");
            html.append("<th class='c'>آی پی و مک آدرس</th>");
            html.append("<th class='c'>نتایج</th>");
            html.append("<th class='c'>مشاهدی پاسخ</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr class='" + getClassByStatus(formRows.get(i).get(_status).toString()) + "'>");
                html.append("<td class='r'>" + formRows.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userName)
                        + "<br/> " + formRows.get(i).get(Role._title)
                        + "<br/> " + formRows.get(i).get(Access_Group._title)
                        + "<br/>" + jjCalendar_IR.getViewFormat(formRows.get(i).get(_date)) + "-" + jjTime.getTime5lenth(formRows.get(i).get(_time).toString()) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(_userIPV4) + "<br/>" + formRows.get(i).get(_userMAC) + "</td>");
                html.append("<td class='l'>sum:" + formRows.get(i).get(_sum) + "<br/>max:" + formRows.get(i).get(_avg) + "</td>");
                if (formRows.get(i).get(_status).equals(statues_sabteAvalie)) {// اگر ثبت اولیه بود آیکن ویرایش و تایع ویرایش را بگذاریم
                    html.append("<td class='c'><a href='Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                            + "><i class='fa fa-pencil' " + "</i></a></td>");
                } else {
                    html.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "&id=" + formRows.get(i).get(_id) + "' target='_blank'"
                            + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
                }
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formRows.get(i).get(_formId) + "' target='_blank' >"
                        + " <i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            html.append("</div>");
            html.append("</div>");
            String jj = jjTools.getParameter(request, "jj");
            if ("0".equals(jj)) {//برای ارجاع به فایل جی اس پی
                request.getRequestDispatcher("template/MyForms.jsp").forward(request, response);
            } else {// اگر این درخواست باید بصورت ایجکس پاسخ گفته شود
                StringBuilder script = new StringBuilder();
                String panel = jjTools.getParameter(request, "panel");

                if (panel.equals("")) {
                    panel = "swAllMyAnswerSets";
                }
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.table("#swAllMyAnswerSetsTable", "", 0, "", "لیست فرم ها"));
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * فرم های پر شده از یک نوع فرم را نشان می دهد ممکن است کاربر جاری پر کرده
     * باشد یا نقش جاری کسی که دسترسی دارد
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMyAnswers(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs_own)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder html = new StringBuilder();
            String formId = jjTools.getParameter(request, _formId);
            List<Map<String, Object>> FormTitleRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title + "," + Forms._uniqueComplete, Forms._id + "=" + formId));
            if (FormTitleRow.isEmpty()) {
                Server.outPrinter(request, response, Js.modal("تاریخ پر کردن فرم تمام شده یا شما به این فرم دسترسی ندارد", "فرم یافت نشد"));
                return "";
            }
            //ویژگی : اگر فردی در سمتی یک فرم را پر کند و فرد دیگری در آن سمت قرار گیرد میتواند فرم تکمیل شده ی فرم قبلی را ببیند همچنین مدیران با دسترسی بالاتر میبینند
            String where = " WHERE " + _formId + "=" + formId + " AND (" + _userId + "=" + userId;
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userRoles[] = userRoleInsession.split(",");
            if (!userRoleInsession.isEmpty()) {// اگر کاربر ر سیستم نقشی دارد
                for (int i = 0; i < userRoles.length; i++) {
                    where += " OR " + _userRole + " = " + userRoles[i];// ممکن است کاربر چند تا تقش داشته باشد
                }
            }

            where += ")";
            DefaultTableModel dtm = db.otherSelect("SELECT " + tableName + "." + _id + ","
                    + _date + ","
                    + _time + ","
                    + _status + ","
                    + _userName + ","
                    + _userMAC + ","
                    + _userIPV6 + ","
                    + Role._title + ","
                    + Access_Group._title
                    + " FROM " + tableName
                    + " "
                    + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                    + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                    + " " + where);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<p class='mg-b-20 mg-sm-b-30'>");
            html.append("عنوان :" + FormTitleRow.get(0).get(Forms._title));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
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
                where += Forms._accessessGroupId + " like " + "'%" + userGroup[i] + "%' OR ";// 
            }
            if (!userGroupInsession.isEmpty()) {// اگر کاربر جاری گروه در سیستم دارد
                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//
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
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  "
                        + " onclick='hmisFormAnswerSets.refreshMyAnswersAfterQuestion(" + formId + ");' "
                        + ">تکمیل یک نمونه فرم جدید</a> ");
            }
            html.append("</p>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            if (!row.isEmpty()) {
                html.append("<table id='refreshAnswers' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
                html.append("<th class='r'>کد</th>");
                html.append("<th class='c'>نام تکمیل کننده</th>");
                html.append("<th class='c'> سمت و گروه کاربری</th>");
                html.append("<th class='c'>تاریخ</th>");
                html.append("<th class='c'>ساعت</th>");
                html.append("<th class='c'>mac/ip</th>");
                html.append("<th class='c'>ویرایش</th>");
//                html.append("<th class='c'>آمار و نتایج</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr  class='mousePointer " + getClassByStatus(row.get(i).get(_status).toString()) + "' >");
                    html.append("<td class='r'>" + row.get(i).get(_id) + "</td>");
                    html.append("<td class='r'>" + row.get(i).get(_userName) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(Role._title) + "-" + row.get(i).get(Access_Group._title) + "</td>");
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_time) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_userMAC) + "<br/>" + row.get(i).get(_userIPV6) + "</td>");
                    html.append("<td class='c'><a "
                            + " onclick='hmisFormAnswerSets.selectMyAnswersAfterQuestion(" + formId + "," + row.get(i).get(_id).toString() + " );' "
                            + "><i class='p icon fa fa-pencil'></i></a></td>");
//                    html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.select(row.get(i).get(_id).toString()) + "'></i></td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table></div></div>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "swOneFormToCompleteTable";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshAnswers", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست فرم ها پر شدهی در دسترس شما");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, ex.getMessage());
            return ex.getMessage();
        }
    }

    public static String refreshMyAnswersInNursing(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs_own)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder html = new StringBuilder();
            String formId = jjTools.getParameter(request, _formId);
            List<Map<String, Object>> FormTitleRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title + "," + Forms._uniqueComplete, Forms._id + "=" + formId));
            if (FormTitleRow.isEmpty()) {
                Server.outPrinter(request, response, Js.modal("تاریخ پر کردن فرم تمام شده یا شما به این فرم دسترسی ندارد", "فرم یافت نشد"));
                return "";
            }
            //ویژگی : اگر فردی در سمتی یک فرم را پر کند و فرد دیگری در آن سمت قرار گیرد میتواند فرم تکمیل شده ی فرم قبلی را ببیند همچنین مدیران با دسترسی بالاتر میبینند
            String where = " WHERE " + _formId + "=" + formId + " AND (" + _userId + "=" + userId;
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            String userGroup[] = userGroupInsession.split(",");
            String userRoles[] = userRoleInsession.split(",");
            if (!userRoleInsession.isEmpty()) {// اگر کاربر ر سیستم نقشی دارد
                for (int i = 0; i < userRoles.length; i++) {
                    where += " OR " + _userRole + " = " + userRoles[i];// ممکن است کاربر چند تا تقش داشته باشد
                }
            }
            where += ")";
            DefaultTableModel dtm = db.otherSelect("SELECT " + tableName + "." + _id + ","
                    + _date + ","
                    + _time + ","
                    + _status + ","
                    + _userName + ","
                    + _userMAC + ","
                    + _userIPV6 + ","
                    + Role._title + "," + Access_Group._title + " FROM " + tableName
                    + " "
                    + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                    + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                    + " " + where);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<p class='mg-b-20 mg-sm-b-30'>");
            html.append("عنوان :" + FormTitleRow.get(0).get(Forms._title));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            //ویژگی : اگر کاربر قبلا دسترسی داشته و اکنون دسترسی اش برای فرم خاصی برداشته شده یا نقشش عوض شده که به فرمی دسترسی ندارد دیگر دکمهی یجدید را نمی بیند
            //تشخیص دسترسی نقش یا شخص کاربر برای تکمیل این فرم---------------------------------------------------
            where = " WHERE (";
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%" + userRoles[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!userRoleInsession.isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
            for (int i = 0; i < userGroup.length; i++) {
                where += Forms._accessessGroupId + " like " + "'%" + userGroup[i] + "%' OR ";// ممکن است کاربر چند تا گروه داشته باشد
            }
            if (!userGroupInsession.isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//فرم هایی که برای همه ی گروه ها دسترسی دارند را هم نشان بدهیم
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
                html.append("<a id='btnBackInNursing' style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisNursing.m_show_tbl();'  target='_blank'>"
                        + "<i class='p icon fa fa-arrow-right'></i></a>");
                html.append("<br/>");
                html.append("<br/>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  target='_blank' "
                        + " onclick='hmisNursing.refreshMyAnswersAfterQuestion(" + formId + ");' "
                        + ">تکمیل یک نمونه فرم جدید</a> ");
            }

            html.append("</p>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            if (!row.isEmpty()) {
                html.append("<table id='refreshAnswersInNursing' class='table display responsive' class='tahoma10' style='direction: rtl'><thead>");
                html.append("<th width='5%' class='r'>کد</th>");
                html.append("<th width='20%' class='c'>نام تکمیل کننده</th>");
                html.append("<th width='20%' class='c'>سمت و گروه کاربری</th>");
                html.append("<th width='20%' class='c'>تاریخ</th>");
                html.append("<th width='20%' class='c'>ساعت</th>");
                html.append("<th width='20%' class='c'>mac/ip</th>");
                html.append("<th width='20%' class='c'>ویرایش</th>");
//                html.append("<th width='20%' class='c'>آمار و نتایج</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr  class='mousePointer " + getClassByStatus(row.get(i).get(_status).toString()) + "' >");
                    html.append("<td class='r'>" + row.get(i).get(_id) + "</td>");
                    html.append("<td class='r'>" + row.get(i).get(_userName) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(Role._title) + "-" + row.get(i).get(Access_Group._title) + "</td>");
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_time) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_userMAC) + "<br/>" + row.get(i).get(_userIPV6) + "</td>");
                    html.append("<td class='c'><a  target='_blank'"
                            + " onclick='hmisNursing.selectMyAnswersAfterQuestion(" + formId + "," + row.get(i).get(FormAnswerSet._id).toString() + " );' "
                            + "><i class='p icon fa fa-pencil'></i></a></td>");
//                    html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.select(row.get(i).get(_id).toString()) + "'></i></td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table>");
                html.append("</div>");
                html.append("</div>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "swNursingForm";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshAnswersInNursing", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست فرم ها پر شدهی در دسترس شما");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, ex.getMessage());
            return ex.getMessage();
        }
    }

    /**
     * ارزیابی کمیته
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMyAnswersAssessmentForSession(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs_own)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder html = new StringBuilder();
            String formId = jjTools.getParameter(request, _formId);
            List<Map<String, Object>> FormTitleRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title + "," + Forms._uniqueComplete, Forms._id + "=" + formId));
            if (FormTitleRow.isEmpty()) {
                Server.outPrinter(request, response, Js.modal("تاریخ پر کردن فرم تمام شده یا شما به این فرم دسترسی ندارد", "فرم یافت نشد"));
                return "";
            }
            //ویژگی : اگر فردی در سمتی یک فرم را پر کند و فرد دیگری در آن سمت قرار گیرد میتواند فرم تکمیل شده ی فرم قبلی را ببیند همچنین مدیران با دسترسی بالاتر میبینند
            String where = " WHERE " + _formId + "=" + formId;
//            String where = " WHERE " + _formId + "=" + formId + " AND (" + _userId + "=" + userId;
//            String userRoleInsession = jjTools.getSeassionUserRole(request);
//            String userRoles[] = userRoleInsession.split(",");
//            if (!userRoleInsession.isEmpty()) {// اگر کاربر ر سیستم نقشی دارد
//                for (int i = 0; i < userRoles.length; i++) {
//                    where += " OR " + _userRole + " = " + userRoles[i];// ممکن است کاربر چند تا تقش داشته باشد
//                }
//            }
//            where += ")";   
            DefaultTableModel dtm = db.otherSelect("SELECT " + tableName + "." + _id + ","
                    + _date + ","
                    + _time + ","
                    + _status + ","
                    + _userName + ","
                    + _userMAC + ","
                    + _userIPV6 + ","
                    + Role._title + "," + Access_Group._title + " FROM " + tableName
                    + " "
                    + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                    + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                    + " " + where);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<p class='mg-b-20 mg-sm-b-30'>");
            html.append("عنوان :" + FormTitleRow.get(0).get(Forms._title));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            //ویژگی : اگر کاربر قبلا دسترسی داشته و اکنون دسترسی اش برای فرم خاصی برداشته شده یا نقشش عوض شده که به فرمی دسترسی ندارد دیگر دکمهی یجدید را نمی بیند
            //تشخیص دسترسی نقش یا شخص کاربر برای تکمیل این فرم---------------------------------------------------
            where = " WHERE ";
//                    + " (";
//            for (int i = 0; i < userRoles.length; i++) {            
//                where += Forms._accessessRoles + " like " + "'%" + userRoles[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
//            }
//            if (!userRoleInsession.isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
//                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
//            }
//  String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
//            String userGroup[] = userGroupInsession.split(",");  
//            for (int i = 0; i < userGroup.length; i++) {
//                where += Forms._accessessGroupId + " like " + "'%" + userGroup[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
//            }
//            if (!userGroupInsession.isEmpty()) {// اگر کاربر جاری گروه در سیستم دارد
//                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
//            }
//            where += Forms._accessessUsers + " like " + "'%" + userId + "%'" + " OR ";    
//            where += Forms._accessessUsers + " like " + "'%ALL%'" + " OR ";
//            where += Forms._accessessUsers + " = '' ) OR  ";
//where += "(" + Forms._accessessGroupId + "=',null,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
//            where += Forms._accessessRoles + "=',null,' AND ";
//            where += Forms._accessessUsers + "=',null,' )"
//                    + "AND ";
//            where += Forms._id + "=" + jjTools.getParameter(request, FormAnswerSet._formId) + "  AND  ";
            where += Forms._isActive + "=1 ; ";
            //--------------------------------------------------------------------------------------------------------------------
            if (accIns && db.otherSelect("SELECT * FROM " + Forms.tableName + where).getRowCount() > 0) {//و اگر نقش یا شخص این کاربر موجود در سشن به این فرم دسترسی داشته باشد  دکمه جدید را نشان بدهد
                html.append("<br/>");
                html.append("<a id='btnBackInAssessment' style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisAssessmentForSessionCategory.m_show_tbl();'  target='_blank'>"
                        + "<i class='p icon fa fa-arrow-right'></i></a>");
                html.append("<br/>");
                html.append("<br/>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white'  target='_blank' "
                        + " onclick='hmisAssessmentForSessionCategory.refreshMyAnswersAfterQuestion(" + formId + ");' "
                        + ">تکمیل یک نمونه فرم جدید</a> ");
            }

            html.append("</p>");
            html.append("<div class='table-wrapper'>");
            if (!row.isEmpty()) {
                html.append("<table id='refreshMyAnswersAssessmentForSessionTBL' class='table display responsive' class='tahoma10' style='direction: rtl'><thead>");
                html.append("<th width='5%' class='r'>کد</th>");
                html.append("<th width='20%' class='c'>نام تکمیل کننده</th>");
                html.append("<th width='20%' class='c'>سمت و گروه کاربری</th>");
                html.append("<th width='20%' class='c'>تاریخ</th>");
                html.append("<th width='20%' class='c'>ساعت</th>");
                html.append("<th width='20%' class='c'>mac/ip</th>");
                html.append("<th width='20%' class='c'>ویرایش</th>");
//                html.append("<th width='20%' class='c'>آمار و نتایج</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr  class='mousePointer " + getClassByStatus(row.get(i).get(_status).toString()) + "' >");
                    html.append("<td class='r'>" + row.get(i).get(_id) + "</td>");
                    html.append("<td class='r'>" + row.get(i).get(_userName) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(Role._title) + "-" + row.get(i).get(Access_Group._title) + "</td>");
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_time) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_userMAC) + "<br/>" + row.get(i).get(_userIPV6) + "</td>");
                    html.append("<td class='c'><a target='_blank'"
                            + " onclick='hmisAssessmentForSessionCategory.selectMyAnswersAfterQuestion(" + formId + "," + row.get(i).get(FormAnswerSet._id).toString() + " );' "
                            + "><i class='p icon fa fa-pencil'></i></a></td>");
//                    html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.select(row.get(i).get(_id).toString()) + "'></i></td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "swAssessmentForSessionCategoryForm";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshMyAnswersAssessmentForSessionTBL", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست فرم ها پر شدهی در دسترس شما");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, ex.getMessage());
            return ex.getMessage();
        }
    }

    /**
     * فرم های پر شده از یک نوع فرم را نشان می دهد ممکن است کاربر جاری پر کرده
     * باشد یا نقش جاری کسی که دسترسی دارد
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshMyAnswersMobile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs_own)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder html = new StringBuilder();
            String formId = jjTools.getParameter(request, _formId);
            List<Map<String, Object>> FormTitleRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title + "," + Forms._uniqueComplete, Forms._id + "=" + formId));
            if (FormTitleRow.isEmpty()) {
                Server.outPrinter(request, response, "کد صحیح نیست");
                return "";
            }
            //ویژگی : اگر فردی در سمتی یک فرم را پر کند و فرد دیگری در آن سمت قرار گیرد میتواند فرم تکمیل شده ی فرم قبلی را ببیند همچنین مدیران با دسترسی بالاتر میبینند
            String where = " WHERE " + _formId + "=" + formId + " AND (" + _userId + "=" + userId;
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            System.out.println("*&*&*&*&*" + userRoleInsession);
            String userRoles[] = userRoleInsession.split(",");
            if (!userRoleInsession.isEmpty()) {// اگر کاربر ر سیستم نقشی دارد
                for (int i = 0; i < userRoles.length; i++) {
                    where += " OR " + _userRole + " = " + userRoles[i];// ممکن است کاربر چند تا تقش داشته باشد
                }
            }
//            String userGroup[] = userRoleInsession.split(",");
//            if (!userGroupInsession.isEmpty()) {// اگر کاربر ر سیستم گروه دارد
//                for (int i = 0; i < userGroup.length; i++) {
//                    where += " OR " + _userGroup + " = " + userGroup[i];// ممکن است کاربر چند تا گروه داشته باشد
//                }
//            }
            where += ")";
            DefaultTableModel dtm = db.otherSelect("SELECT " + tableName + "." + _id + ","
                    + _date + ","
                    + _time + ","
                    + _status + ","
                    + _userName + ","
                    + _userMAC + ","
                    + _userIPV6 + ","
                    + Role._title + "," + Access_Group._title + " FROM " + tableName
                    + " "
                    + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + _userGroup
                    + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + _userRole
                    + " " + where);
//            DefaultTableModel dtm = db.JoinLeft(tableName, Role.tableName,
//                    tableName + "." + _id + ","
//                    + _date + ","
//                    + _time + ","
//                    + _status + ","
//                    + _userName + ","
//                    + _userMAC + ","
//                    + _userIPV6 + ","
//                    + Role._title, _userRole, Role._id, where);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<p class='mg-b-20 mg-sm-b-30'>");
            html.append("عنوان :" + FormTitleRow.get(0).get(Forms._title));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            //ویژگی : اگر کاربر قبلا دسترسی داشته و اکنون دسترسی اش برای فرم خاصی برداشته شده یا نقشش عوض شده که به فرمی دسترسی ندارد دیگر دکمهی یجدید را نمی بیند
            //تشخیص دسترسی نقش یا شخص کاربر برای تکمیل این فرم---------------------------------------------------
            where = " WHERE (";
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%" + userRoles[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!userRoleInsession.isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
                where += Forms._accessessRoles + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
            }
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
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' target='_blank'"
                        + " onclick='hmisFormAnswerSets.refreshMyAnswersAfterQuestion(" + formId + ");' "
                        + ">تکمیل یک نمونه فرم جدید</a> ");
            }

            html.append("</p>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            if (!row.isEmpty()) {
                html.append("<table id='refreshAnswers' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
                html.append("<th width='5%' class='r'>کد</th>");
                html.append("<th width='20%' class='c'>نام تکمیل کننده</th>");
                html.append("<th width='20%' class='c'>سمت و گروه کاربری</th>");
                html.append("<th width='20%' class='c'>تاریخ</th>");
                html.append("<th width='20%' class='c'>ساعت</th>");
                html.append("<th width='20%' class='c'>mac/ip</th>");
                html.append("<th width='20%' class='c'>ویرایش</th>");
//                html.append("<th width='20%' class='c'>آمار و نتایج</th>");
                html.append("</thead><tbody>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<tr  class='mousePointer " + getClassByStatus(row.get(i).get(_status).toString()) + "' >");
                    html.append("<td class='r'>" + row.get(i).get(_id) + "</td>");
                    html.append("<td class='r'>" + row.get(i).get(_userName) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(Role._title) + "-" + row.get(i).get(Access_Group._title) + "</td>");
                    html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date)) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_time) + "</td>");
                    html.append("<td class='c'>" + row.get(i).get(_userMAC) + "<br/>" + row.get(i).get(_userIPV6) + "</td>");
                    html.append("<td class='c'><a target='_blank'"
                            + " onclick='hmisFormAnswerSets.selectMyAnswersAfterQuestion(" + formId + "," + row.get(i).get(FormAnswerSet._id).toString() + " );' "
                            + "><i class='p icon fa fa-pencil'></i></a></td>");
//                    html.append("<td class='c'><i class='p fa fa-bar-chart' onclick='" + Js.jjFormAnswerSet.select(row.get(i).get(_id).toString()) + "'></i></td>");
                    html.append("</tr>");
                }
                html.append("</tbody></table></div></div>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "swOneFormToCompleteTable";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshAnswers", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست فرم ها پر شدهی در دسترس شما");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, ex.getMessage());
            return ex.getMessage();
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            //بررسی دسترسی برای تکمیل یک فرم بر اساس نقش و شخص------------------------------------------------
            if (!accIns) {//و اگر نقش یا شخص این کاربر موجود در سشن به این فرم دسترسی داشته باشد این متد کار می کند
                Server.outPrinter(request, response, Js.modal("شما اجازه ی ثبت این فرم را ندارید", "محدودیت دسترسی"));
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String where = " WHERE (";
            String userRoles[] = userRoleInsession.split(",");
            for (int i = 0; i < userRoles.length; i++) {
                where += Forms._accessessRoles + " like " + "'%" + userRoles[i] + "%' OR ";// ممکن است کاربر چند تا تقش داشته باشد
            }
            if (!jjTools.getSeassionUserRole(request).isEmpty()) {// اگر کاربر جاری نقشی در سیستم دارد
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
            where += Forms._accessessUsers + " = " + "''" + " OR ";
            where += Forms._accessessUsers + "='' ) AND  "; 
            where += Forms._id + "=" + jjTools.getParameter(request, FormAnswerSet._formId) + "  AND  ";
            where += Forms._isActive + "=1 AND";
            // شرط بررسی اینکه تاریخ انقضای فرم نگذشته باشد اینست که تاریخ بزرگتر از امروز باشد یا اگر تاریخ امروز بود ساعت بزرکتر از ساعت جاری باشد
            where += " ( (" + Forms._expireDate + " > " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + ") ";
            where += " OR (";
            where += Forms._expireDate + " = " + jjCalendar_IR.getDatabaseFormat_8length(null, true); // تاریخ مساوی امروز باشد و
            where += " AND ";
            where += Forms._expireTime + " >= " + jjTime.getTime4lenth("") + ")"; // ساعت بزرگتر از ّساعت جاری باشد
            where += ")";
            where += "; ";
            // اگر زمان این فرم گذشته باشد            
            //--------------------------------------------------------------------------------------------------------------------
            String formId = jjTools.getParameter(request, _formId);
            if (!jjNumber.isDigit(formId)) {
                Server.outPrinter(request, response, "کد فرم باید عدد باشد");
                return "";
            }
            for (int k = 0; k < request.getCookies().length; k++) {
                System.out.println("=========================COOKIES No " + k + ":");
                System.out.println(request.getCookies()[k].getName());
                System.out.println(request.getCookies()[k].getValue());
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
            if (formRow.isEmpty()) {
                Server.outPrinter(request, response, "چنین فرمی وجود ندارد");
                return "چنین فرمی وجود ندارد";
            }
            System.out.println("********************************");
            String userMAC = jjTools.getCookie(request, "#USER_MAC");
            if (formRow.get(0).get(Forms._uniqueComplete).toString().equals("1")) {// اگر از نوع فرم های یکبار پر کردنی بود
                where = FormAnswerSet._formId + "=" + formId + " AND (" + FormAnswerSet._userId + "=" + jjTools.getSeassionUserId(request) + " OR "
                        + FormAnswerSet._userMAC + "=" + userMAC + ") ;";// جایی که پاسخ در حالت ثبت اولیه نباشد و با همین فرم آی دی و همین کاربر یا همین مک
                List<Map<String, Object>> uniqueForm = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, where));
                if (!uniqueForm.isEmpty()) {// اگر فرم باید توسط هر کاربر بصورت یونیک پر شود و اگر قبلا ثبت نهایی کرده است 
                    Server.outPrinter(request, response, "شما مجاز به تکمیل مجدد این فرم نیستید");
                    return "شما مجاز به تکمیل مجدد این فرم نیستید";
                }
            } else {//اگر قبلا فرمی ایجاد کرده و ثبت نهایی نکرده
                //@ToDo می توانیم اینجا چک کنیم اگر پاسخ قبلی در وضعیت ثبت اولیه بود کاربر را بفرستیم به ویرایش 
            }
            //مهم : دز اینجا یک صفحه ی جی اس پی را در پاسخ می فرستیم
            request.setAttribute("db", db);
            System.out.println("------->>>>>template/newAnswer.jsp");
            db.otherStatement("UPDATE " + Forms.tableName + "  SET " + Forms._visit + "=" + Forms._visit + " + 1" + "    WHERE " + Forms._id + " =" + formId);// افزایش یک واحد به تعداد بازدید کنندگان فرم
            request.getRequestDispatcher("template/newAnswer.jsp").forward(request, response);
            return "";
        } catch (IOException | ServletException ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * نشان داده یک فرمکه ثبت شده برای ویرایش توسط کاربر ممکن است بعد از ثبت
     * اولیه باشد و ممکن است بعد از انتخاب از جدول
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String selectToEdit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        request.setAttribute("db", db);
        System.out.println("------->>>>>template/newAnswerSelectToEdit.jsp");
        request.getRequestDispatcher("template/newAnswerSelectToEdit.jsp").forward(request, response);
        return "";
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
        System.out.println("------->>>>>template/formResult.jsp");
        request.getRequestDispatcher("template/formResult.jsp").forward(request, response);
        return "";
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
    public static String showAllResult(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        request.setAttribute("db", db);
        System.out.println("------->>>>>template/formAllResult.jsp");
        request.getRequestDispatcher("template/formAllResult.jsp").forward(request, response);
        return "";
    }

    /**
     * نمایش جدول اطلاعات تکمیل فرم
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showInformationForm(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        request.setAttribute("db", db);
        System.out.println("");
        System.out.println("------->>>>>template/informationForm.jsp");
        request.getRequestDispatcher("template/informationForm.jsp").forward(request, response);
        return "";
    }

    /**
     * درج در دیتابیس برای اولین بار ثبت موقت فرم و ثبت نهایی و آزمون توسط کاربر
     * این تابع اگر فرم یونیک باشد و قبلا توسط این مک یا این کاربر پر شده باشد
     * اجازه تکمیل نمیدهد
     *
     * @param request
     * @param response
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
            String formId = jjTools.getParameter(request, _formId);
            String script = "";
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
            String userMAC = jjTools.getCookie(request, "#USER_MAC");
            if (formRow.isEmpty()) {
                Server.outPrinter(request, response, "این فرم وجود ندارد");
                return "این فرم وجود ندارد";
            } else if (formRow.get(0).get(Forms._uniqueComplete).toString().equals("1")) {// اگر فرم باید توسط هر کاربر بصورت یونیک پر شود
                //چک می کنیم که قبلا این کاربر یا این مک آدرس فرم را پر نکرده باشد
                String where = _formId + "=" + formId + " AND " + _userId + "=" + jjTools.getSeassionUserId(request);// جایی که پاسخ در حالت ثبت اولیه نباشد و با همین فرم آی دی و همین کاربر یا همین مک
//                        + _userMAC + "=" + userMAC + ")";// جایی که پاسخ در حالت ثبت اولیه نباشد و با همین فرم آی دی و همین کاربر یا همین مک
                List<Map<String, Object>> uniqueForm = jjDatabaseWeb.separateRow(db.Select(tableName, where));
                if (!uniqueForm.isEmpty()) {
                    script = "alert('این فرم قبلا توسط شما تکمیل شده است و شما مجازهستید فقط یکبار این فرم را تکمیل کنید');\n";
                    script += Js.setHtml("#formAnserSetBtn", "این فرم قبلا توسط شما تکمیل شده است و شما مجازهستید فقط یکبار این فرم را تکمیل کنید");
                    Server.outPrinter(request, response, script);
                    return script;
                }
            }

            Map<String, Object> map = new HashMap();
            map.put(_formId, formId);
            if (!jjTools.getParameter(request, _departmentId).equals("null") && !jjTools.getParameter(request, _departmentId).equals("")) {
                map.put(_departmentId, jjTools.getParameter(request, _departmentId));
            } else {
                map.put(_departmentId, 0);
            }
            map.put(_userId, jjTools.getSeassionUserId(request));
            if (jjTools.getSeassionUserRole(request).split(",").length > 1) {// اگر بیشتر از یک نقش در سشن داشت از ریکوئست نقش انتخابی کاربر را بخواند
                map.put(_userRole, jjTools.getParameter(request, _userRole));
            } else {// در غیر اینصورت نقش را  از سشن بخواند که ممکن است تهی باشدیعنی نقشی نداشته باشد
                map.put(_userRole, jjTools.getSeassionUserRole(request).replaceAll(",", ""));
            }
            if (jjTools.getSessionAttribute(request, "#GROUP_ID").split(",").length > 1) {// اگر بیشتر از یک گروه در سشن داشت از ریکوئست نقش انتخابی کاربر را بخواند
                map.put(_userGroup, jjTools.getParameter(request, _userGroup));
            } else {// در غیر اینصورت گروه را  از سشن بخواند که ممکن است تهی باشدیعنی نقشی نداشته باشد
                map.put(_userGroup, jjTools.getSessionAttribute(request, "#GROUP_ID").replaceAll(",", ""));
            }
            map.put(_userName, jjTools.getSeassionUserNameAndFamily(request));
            map.put(_userMAC, userMAC);
            map.put(_userIPV4, jjTools.getuserIP(request));
            map.put(_userIPV6, jjTools.getuserIP(request));

            jjCalendar_IR date = new jjCalendar_IR();
            if (formRow.get(0).get(Forms._isDateEditable).toString().equals("1")) {//اگر تاریخ توسط کاربر قابل ثبت باشد و اتوماتیک نباشد آنرا ار ریکوئست می گیریم
                map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            } else {
                map.put(_date, date.getDBFormat_8length());
            }
            map.put(_time, date.getTimeFormat_4length());

            //درج یک ست پاسخ در دیتابیس برای اتصال پاسخ های تکی به هم
            List<Map<String, Object>> answerSetRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            if (answerSetRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت. 12-324";
                Server.outPrinter(request, response, "alert('" + errorMessage + "')");
                return Js.modal(errorMessage, "پیام سامانه");
            }
            String id = answerSetRow.get(0).get(_id).toString();// وقتی در جدول ردیف مخصوص پاسخ را درج کردیم آی دی را مقدار دهی می کنیم
            //به تعداد سوالات موجود در فرم اصلی باید پاسخ ایجاد کنیم
            List<Map<String, Object>> questions = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._formID + "=" + formId));
            boolean flag = true;
            String qNo = "";
            float sum = 0;
            float max = 0;
            for (int i = 0; i < questions.size(); i++) {// در پاسخ های فرم در ستون پاسخ ها
                Map<String, Object> answerMap = new HashMap();
                String answerI = jjTools.getParameter(request, "q" + questions.get(i).get(_id));
                if (questions.get(i).get(FormQuestions._isRequierd).equals("1") && answerI.isEmpty()) {//در ثبت نهایی اگر پاسخ سوالی ضروری بود و کاربر پاسخ نداده بودباید فرم را از حالت ثبت نهایی خارج کنیم
                    flag = false;
                    qNo += "سوال شماره ی" + (i + 1) + "،";//سوال هایی که ضروری هستند و پاسخ داده نشده اند
                }
                if (questions.get(i).get(FormQuestions._answersType).equals("checkbox")) {//اگر چک باکس بود به تعداد تیک هایی که زده باید ردیف ایجاد کنیم
                    String answerOptionId[] = answerI.split(",");
                    for (int j = 0; j < answerOptionId.length; j++) {
                        answerMap.put(FormAnswers._questionId, questions.get(i).get(_id));
                        answerMap.put(FormAnswers._answer, answerOptionId[j]);
                        answerMap.put(FormAnswers._answerSet_id, id);
                        db.insert(FormAnswers.tableName, answerMap);// برای هر تیک چک باس یک سطر در جدول پاسخ ها داریم
                    }
                    if (!questions.get(i).get(FormQuestions._weight).equals("0")) {// اگر وزن سوال صفر نبود 
                        List<Map<String, Object>> sumOfOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                                + "SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " sum FROM hmis_formanswers"
                                + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                + " where formanswers_answerSet_id=" + id + " AND formanswers_questionId=" + questions.get(i).get("id")
                                + " AND formQuestionOptions_value<>'';;"
                                + ""));
                        // و کاربر جواب غیر قابل ارزیابی نداده بود
                        if (jjNumber.isFloat(sumOfOptionRow.get(0).get("sum").toString())) {
                            sum += Float.valueOf(sumOfOptionRow.get(0).get("sum").toString());//مجموعامتیاز کاربر در این سوال را جساب کن و به مجموع کلی فرم اضافه کن

                            // ماکسیمم نمره ی این سوال را نیز حساب کن
                            List<Map<String, Object>> maxOptionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " as max FROM hmis_formquestionoptions"
                                    + " WHERE  formQuestionOptions_question_id=" + questions.get(i).get("id")
                                    + ";"));
                            if (jjNumber.isFloat(maxOptionRow.get(0).get("max").toString())) {

                                max += Float.valueOf(maxOptionRow.get(0).get("max").toString());
                            }
                        }
                    }
                } else if (questions.get(i).get(FormQuestions._answersType).equals("radio") || questions.get(i).get(FormQuestions._answersType).equals("select_option")) {//اگر چک باکس بود به تعداد تیک هایی که زده باید ردیف ایجاد کنیم                
                    answerMap.put(FormAnswers._answer, answerI);// از ریکوئست بخوانیم مقدار پاسخ جدید را
                    if (!db.update(FormAnswers.tableName, answerMap, FormAnswers._answerSet_id + "=" + id
                            + " AND " + FormAnswers._questionId + "=" + questions.get(i).get(FormQuestions._id))) {//اگر قبلا پاسخی وجود داشت که بروز رسانی بکن و اگر نداشت اینسرت کن
                        answerMap.put(FormAnswers._answerSet_id, id);//اینجا سوالی که قبلا نبوده اضافه شده و باید پاسخ داده شود
                        answerMap.put(FormAnswers._questionId, questions.get(i).get(FormQuestions._id));
                        db.insert(FormAnswers.tableName, answerMap);
                    }
                    if (!questions.get(i).get(FormQuestions._weight).equals("0")) {// اگر وزن سوال صفر نبود 
                        List<Map<String, Object>> sumOfOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                                + "SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " sum FROM hmis_formanswers"
                                + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                + " where formanswers_answerSet_id=" + id + " AND formanswers_questionId=" + questions.get(i).get("id")
                                + " AND formQuestionOptions_value<>'';;"
                                + ""));
                        // و کاربر جواب غیر قابل ارزیابی نداده بود
                        if (jjNumber.isFloat(sumOfOptionRow.get(0).get("sum").toString())) {
                            sum += Float.valueOf(sumOfOptionRow.get(0).get("sum").toString());//مجموعامتیاز کاربر در این سوال را جساب کن و به مجموع کلی فرم اضافه کن

                            // ماکسیمم نمره ی این سوال را نیز حساب کن
                            List<Map<String, Object>> maxOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                                    + "SELECT max(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " as max FROM hmis_formquestionoptions"
                                    + " WHERE  formQuestionOptions_question_id=" + questions.get(i).get("id")
                                    + ";"));
                            if (jjNumber.isFloat(maxOptionRow.get(0).get("max").toString())) {
                                max += Float.valueOf(maxOptionRow.get(0).get("max").toString());

                            }
                        }

                    }
                } else if (questions.get(i).get(FormQuestions._answersType).equals("location")) {

                    answerMap.put(FormAnswers._questionId, questions.get(i).get(_id));
                    answerMap.put(FormAnswers._answer, jjTools.getParameter(request, "latLang"));
                    answerMap.put(FormAnswers._answerSet_id, id);
                    db.insert(FormAnswers.tableName, answerMap);// برای هر پاسخ یک سطر در جدول پاسخ ها داریم

                } else {
                    answerMap.put(FormAnswers._questionId, questions.get(i).get(_id));
                    answerMap.put(FormAnswers._answer, answerI);
                    answerMap.put(FormAnswers._answerSet_id, id);
                    db.insert(FormAnswers.tableName, answerMap);// برای هر پاسخ یک سطر در جدول پاسخ ها داریم
                }
            }
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            // بعد از بروز رسانی مقادیر مجموع و میانگین نهایی را بروز رسانی می کنیم
            map.clear();
            // محاسبه ی مجموع و میانگین نمره ها و امتیازات همراه با ضرایب و وزن ها

            map.put(_sum, sum);
            map.put(_avg, max);
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات محاسبه مقادیر فرم به درستی صورت نگرفت. 12-606";
                Server.outPrinter(request, response, "alert('" + errorMessage + "')");
                return Js.modal(errorMessage, "پیام سامانه");
            }
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            String message = "";
            //تغییر وضعیت==================================================
            if (jjTools.getParameter(request, _status).equals(statues_sabteNahei) && flag) {//اگر درخواست ثبت نهایی بود و به همه ی سوالات اجباری پاسخ داده بود
                changeStatus(id, statues_sabteNahei, db);
                message = "پاسخ شما در سامانه ثبت نهایی شد";
                script += "addFormIdToCookie(" + formId + ");\n";//در کوکی ست کنیم که این فرم قبلا ثبت نهایی شده است
            } else {
                changeStatus(id, statues_sabteAvalie, db);
                if (!qNo.isEmpty()) {// اگر به سوالات اجباری پاسخ نداده بود
                    message = "شما به " + qNo + "پاسخ نداده اید ";
                }
                message += ". فرم شما موقتا ثبت شد";
                script += "removeFormIdFromCookie(" + formId + ");\n";//در کوکی پاک کنیم که این فرم قبلا ثبت نهایی شده است
            }
            //==========================================================
            script += Js.setVal("#" + tableName + "_id", id);// وقتی که آی دی را بگذاریم در فرم سمت جاوا اسکریپت کنترل می کنیم که کدام تابع صدا زده بشود
            script += Js.modal(message, "پیام سامانه");
            script += "$('#formAnserSetBtn').html('فرم ثبت نهایی شد و شما دیگر قادر به تغییر یا ویرایش آن نیستید');\n";// برای اینکه کاربر نتواند دکمه درج را مجدد بزند
            script += "window.location.replace('Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=" + formId + "&id=" + id + "');\n";// برای اینکه کاربر نتواند دکمه درج را مجدد بزند

//                    return "alert('" + message + "')";//@ToDo چون رکورد فرم ثبت شده باید دکمه ثبت را به ویرایش تغییر بدهیم
            //@ToDo فرم بعد از این فرم چه باید باشد و صدا زدن ان در ثبت نهایی
            Server.outPrinter(request, response, script);
            return Js.setVal("#" + tableName + "_id", id);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String changeStatus(String id, String newStatus, jjDatabaseWeb db) throws Exception {
        try {
            if (!jjNumber.isDigit(id)) {
                return "خطا در تغییر وضعیت  12-375";
            }
            db.otherStatement("UPDATE " + tableName + " SET " + _statusLog
                    + "=concat(ifnull(" + _statusLog + ",''),'"
                    + newStatus
                    + ","
                    + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                    + ","
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
                    + "') ,"
                    + _status + "='" + newStatus + "'  WHERE id=" + id + ";");
            return "";
        } catch (Exception ex) {
            System.out.println("err: عملیات تغییر وضعیت به درستی صورت نگرفت.12-390");
            Server.ErrorHandler(ex);
            return "عملیات تغییر وضعیت به درستی صورت نگرفت.12-390";
        }
    }

    /**
     * ویرایش فرمی که ثبت موقت شده در این مرحله دسترسی در قسمت اینسرت بررسی شده
     * اینجا فقط بررسی می کنیم که فرم را خود فرد ایجاد کرده
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String formId = jjTools.getParameter(request, _formId);
        String id = jjTools.getParameter(request, _id);
        //اگر پاسخ را ثبت نهایی کرده بود  امکان تغییر ندارد
        if (db.Select(tableName, _id + "=" + id + " AND " + _status + "='" + statues_sabteNahei + "'").getRowCount() == 1) {
            Server.outPrinter(request, response, Js.modal("امکان ویرایش فرمی که ثبت نهایی شده وجود ندارد", "فرم قبلا ثبت نهایی شده!"));
            return "امکان ویرایش فرمی که ثبت نهایی شده وجود ندارد";
        }
        //@ToDo هرکسی بتواند فقط فرم خودش را ویرایش کند
        String script = "";
        List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
        String userMAC = jjTools.getCookie(request, "#USER_MAC");
        if (formRow.isEmpty()) {
            Server.outPrinter(request, response, Js.modal("این فرم وجود ندارد", "خطای 748"));
            return "این فرم وجود ندارد";
        }
        // هرکسی بتواند فقط فرم خودش را ویرایش کند
        List<Map<String, Object>> anserSetRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id + " AND " + _status + "='" + statues_sabteAvalie + "'"));
        if (anserSetRow.isEmpty()) {
            Server.outPrinter(request, response, Js.modal("این  ست پاسخ برای این فرم  وجود ندارد یا ثبت نهایی شده است", "خطای 752"));
            return "خطای 752";
        }
//        if (!anserSetRow.get(0).get(_userId).toString().equals(jjTools.getSeassionUserId(request) + "")) {
//            Server.outPrinter(request, response, Js.modal("شما مجاز به تغییر فرم دیگران نیستید", "خطای 758"));
//            return "شما مجاز به تغییر فرم دیگران نیستید";
//        }
        if (formRow.get(0).get(Forms._uniqueComplete).toString().equals("1")) {// اگر فرم باید توسط هر کاربر بصورت یونیک پر شود
            //چک می کنیم که قبلا این کاربر یا این مک آدرس فرم را پر نکرده باشد
            String where = _formId + "=" + formId + " AND (" + _userId + "=" + jjTools.getSeassionUserId(request) + " OR "
                    + _userMAC + "=" + userMAC + ") AND " + _status + "=" + statues_sabteNahei;// جایی که پاسخ در حالت ثبت نهایی نباشد و با همین فرم آی دی و همین کاربر یا همین مک
            List<Map<String, Object>> uniqueForm = jjDatabaseWeb.separateRow(db.Select(tableName, where));
            if (!uniqueForm.isEmpty()) {
                script = "alert('این فرم قبلا توسط شما تکمیل شده است و شما مجازهستید فقط یکبار این فرم را تکمیل کنید');\n";
                script += Js.setHtml("#formAnserSetBtn", "این فرم قبلا توسط شما تکمیل شده است و شما مجازهستید فقط یکبار این فرم را تکمیل کنید");
                Server.outPrinter(request, response, script);
                return script;
            }
        }
        Map<String, Object> map = new HashMap();
        map.put(_formId, formId);
        if (!jjTools.getParameter(request, "formAnswers_departmentId").equals("") && !jjTools.getParameter(request, _departmentId).equals("null")) {
            map.put(_departmentId, jjTools.getParameter(request, _departmentId));//بخش
        } else {
            map.put(_departmentId, 0);
        }

        //توجه توجه
        //shohreh shiran
        //زمانی که فردی بخواهد فرم دیگری را تغییر دهد پیغام می دهد که اجازه دسترسی ندارید
        // من برای اینکه فرم ها را به ثبت اولیه برگردانیم و نقش پر کننده تغییر نکند کد های پایین را کامنت کردم بعد از انجام عملیات باید باز گزدانده شود 
//        map.put(_userId, jjTools.getSeassionUserId(request));
//        if (jjTools.getSeassionUserRole(request).split(",").length > 1) {// اگر بیشتر از یک نقش در سشن داشت از ریکوئست نقش انتخابی کاربر را بخواند
//            map.put(_userRole, jjTools.getParameter(request, _userRole));
//        } else {// در غیر اینصورت نقش را  از سشن بخواند که ممکن است تهی باشدیعنی نقشی نداشته باشد
//            map.put(_userRole, jjTools.getSeassionUserRole(request).replaceAll(",", ""));
//        }
//        if (jjTools.getSessionAttribute(request, "#GROUP_ID").split(",").length > 1) {// اگر بیشتر از یک گروه در سشن داشت از ریکوئست نقش انتخابی کاربر را بخواند
//            map.put(_userGroup, jjTools.getParameter(request, _userGroup));
//        } else {// در غیر اینصورت گروه را  از سشن بخواند که ممکن است تهی باشدیعنی نقشی نداشته باشد
//            map.put(_userGroup, jjTools.getSessionAttribute(request, "#GROUP_ID").replaceAll(",", ""));
//        }
//        map.put(_userName, jjTools.getSeassionUserNameAndFamily(request));
//        map.put(_userMAC, userMAC);
//        map.put(_userIPV4, jjTools.getuserIP(request));
//        map.put(_userIPV6, jjTools.getuserIP(request));
        //توجه توجه
        jjCalendar_IR date = new jjCalendar_IR();
        if (formRow.get(0).get(Forms._isDateEditable).toString().equals("1")) {//اگر تاریخ توسط کاربر قابل ثبت باشد و اتوماتیک نباشد آنرا ار ریکوئست می گیریم
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
        } else {
            map.put(_date, date.getDBFormat_8length());
        }
        map.put(_time, date.getTimeFormat_4length());

        //آپدیت یک ست پاسخ در دیتابیس برای اتصال پاسخ های تکی به هم
        if (!db.update(tableName, map, _id + "=" + id)) {
            String errorMessage = "عملیات ویاریش به درستی صورت نگرفت. 12-324";
            Server.outPrinter(request, response, "alert('" + errorMessage + "')");
            return Js.modal(errorMessage, "پیام سامانه");
        }

        changeStatus(_id, statues_sabteAvalie, db);
        //ویژگی : اگر ادمین سوالات یک فرم را کم یا زیاد کند در ویرایش آم فرم اشکالی بوجود نمی آید
        List<Map<String, Object>> questions = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._formID + "=" + formId));
        //ویژگی : سوالات اجباری را در هنگام ویرایش هشدار می دهد
        boolean flag = true;
        String qNo = "";
        float sum = 0;
        float max = 0;
        for (int i = 0; i < questions.size(); i++) {//برای هر سوال
            map.clear();
            Map<String, Object> mapAnswer = new HashMap();
            String answer = jjTools.getParameter(request, "q" + questions.get(i).get(FormQuestions._id));
            if (questions.get(i).get(FormQuestions._answersType).equals("checkbox")) {//اگر چک باکس بود به تعداد تیک هایی که زده باید ردیف ایجاد کنیم
                db.delete(FormAnswers.tableName, FormAnswers._answerSet_id + "=" + id + " AND " + FormAnswers._questionId + "=" + questions.get(i).get(FormQuestions._id));//پاسخ های قبلی این چک باکس را پاک می کنیم
                String answerOptionId[] = answer.split(",");
                for (int j = 0; j < answerOptionId.length; j++) {
                    mapAnswer.put(FormAnswers._questionId, questions.get(i).get(_id));
                    mapAnswer.put(FormAnswers._answer, answerOptionId[j]);
                    mapAnswer.put(FormAnswers._answerSet_id, id);
                    db.insert(FormAnswers.tableName, mapAnswer);// برای هر تیک چک باس یک سطر در جدول پاسخ ها داریم
                    // برای محاسبه ی ارزش گزینه ای که داریم حساب می کنیم و اینکه آیا غ ق ارزیابی هست یا سلکت میزنیم
                }
                if (!questions.get(i).get(FormQuestions._weight).equals("0")) {// اگر وزن سوال صفر نبود 
                    List<Map<String, Object>> sumOfOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                            + "SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " sum FROM hmis_formanswers"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + " where formanswers_answerSet_id=" + id + " AND formanswers_questionId=" + questions.get(i).get("id")
                            + " AND formQuestionOptions_value<>'';;"
                            + ""));
                    // و کاربر جواب غیر قابل ارزیابی نداده بود
                    if (jjNumber.isFloat(sumOfOptionRow.get(0).get("sum").toString())) {
                        sum += Float.valueOf(sumOfOptionRow.get(0).get("sum").toString());//مجموعامتیاز کاربر در این سوال را جساب کن و به مجموع کلی فرم اضافه کن

                        // ماکسیمم نمره ی این سوال را نیز حساب کن
                        List<Map<String, Object>> maxOptionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " as max FROM hmis_formquestionoptions"
                                + " WHERE  formQuestionOptions_question_id=" + questions.get(i).get("id")
                                + ";"));
                        if (jjNumber.isFloat(maxOptionRow.get(0).get("max").toString())) {

                            max += Float.valueOf(maxOptionRow.get(0).get("max").toString());
                        }
                    }
                }
            } else if (questions.get(i).get(FormQuestions._answersType).equals("radio") || questions.get(i).get(FormQuestions._answersType).equals("select_option")) {//اگر چک باکس بود به تعداد تیک هایی که زده باید ردیف ایجاد کنیم                
                mapAnswer.put(FormAnswers._answer, answer);// از ریکوئست بخوانیم مقدار پاسخ جدید را
                if (!db.update(FormAnswers.tableName, mapAnswer, FormAnswers._answerSet_id + "=" + id
                        + " AND " + FormAnswers._questionId + "=" + questions.get(i).get(FormQuestions._id))) {//اگر قبلا پاسخی وجود داشت که بروز رسانی بکن و اگر نداشت اینسرت کن
                    mapAnswer.put(FormAnswers._answerSet_id, id);//اینجا سوالی که قبلا نبوده اضافه شده و باید پاسخ داده شود
                    mapAnswer.put(FormAnswers._questionId, questions.get(i).get(FormQuestions._id));
                    db.insert(FormAnswers.tableName, mapAnswer);
                }
                if (!questions.get(i).get(FormQuestions._weight).equals("0")) {// اگر وزن سوال صفر نبود 
                    List<Map<String, Object>> sumOfOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                            + "SELECT sum(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " sum FROM hmis_formanswers"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + " where formanswers_answerSet_id=" + id + " AND formanswers_questionId=" + questions.get(i).get("id")
                            + " AND formQuestionOptions_value<>'';;"
                            + ""));
                    // و کاربر جواب غیر قابل ارزیابی نداده بود
                    if (jjNumber.isFloat(sumOfOptionRow.get(0).get("sum").toString())) {
                        sum += Float.valueOf(sumOfOptionRow.get(0).get("sum").toString());//مجموعامتیاز کاربر در این سوال را جساب کن و به مجموع کلی فرم اضافه کن

                        // ماکسیمم نمره ی این سوال را نیز حساب کن
                        List<Map<String, Object>> maxOptionRow = jjDatabaseWeb.separateRow(db.otherSelect(""
                                + "SELECT max(formQuestionOptions_value)*" + questions.get(i).get(FormQuestions._weight) + " as max FROM hmis_formquestionoptions"
                                + " WHERE  formQuestionOptions_question_id=" + questions.get(i).get("id")
                                + ";"));
                        if (jjNumber.isFloat(maxOptionRow.get(0).get("max").toString())) {
                            max += Float.valueOf(maxOptionRow.get(0).get("max").toString());

                        }
                    }

                }
            } else {
                map.put(FormAnswers._answer, answer);// از ریکوئست بخوانیم مقدار پاسخ جدید را
                if (!db.update(FormAnswers.tableName, map, FormAnswers._answerSet_id + "=" + id + " AND " + FormAnswers._questionId + "=" + questions.get(i).get(FormQuestions._id))) {//اگر قبلا پاسخی وجود داشت که بروز رسانی بکن و اگر نداشت اینسرت کن
                    map.put(FormAnswers._answerSet_id, id);//اینجا سوالی که قبلا نبوده اضافه شده و باید پاسخ داده شود
                    map.put(FormAnswers._questionId, questions.get(i).get(FormQuestions._id));
                    db.insert(FormAnswers.tableName, map);
                }
            }
            if (questions.get(i).get(FormQuestions._isRequierd).equals("1") && answer.isEmpty()) {//در ثبت نهایی اگر پاسخ سوالی ضروری بود و کاربر پاسخ نداده بود باید فرم را از حالت ثبت نهایی خارج کنیم
                flag = false;
                qNo += "سوال شماره ی" + (i + 1) + "،";//سوال هایی که ضروری هستند و پاسخ داده نشده اند
            }
        }
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        // بعد از بروز رسانی مقادیر مجموع و میانگین نهایی را بروز رسانی می کنیم
        map.clear();
        map.put(_sum, sum);
        map.put(_avg, max);
        if (!db.update(tableName, map, _id + "=" + id)) {
            String errorMessage = "عملیات محاسبه مقادیر فرم به درستی صورت نگرفت. 12-606";
            Server.outPrinter(request, response, "alert('" + errorMessage + "')");
            return Js.modal(errorMessage, "پیام سامانه");
        }
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //ویژگی : تغییر وضعیت هنگام ویرایش یا ثبت نهایی فرم
        String message = "";
        if (jjTools.getParameter(request, _status).equals(statues_sabteNahei) && flag) {//اگر درخواست ثبت نهایی بود و به همه ی سوالات اجباری پاسخ داده بود  
            changeStatus(id, statues_sabteNahei, db);
            message = "پاسخ شما در سامانه ثبت نهایی شد";
            //برای مشاهده ی نتایج بعد از ثبت نهایی در دوحالت کلی و جزئی کاربر را ارجاع می دهیم به جی اس پی نتایج
            String html = "";
            script += Js.setHtml("#formAnserSetBtn", "فرم ثبت نهایی شده و قادر به ویرایش یا تغییر آن نیستید");
            if (formRow.get(0).get(Forms._showResultToQuestioner).equals("1")) {
                //ساخت دکمه های مشاهده ی نتایج و ارسال آن برای کاربر
                html += "<div class='col-lg-3'>"
                        + "<a class='btn btn-outline-primary mg-b-10  btn-block' href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formId + "&id=" + id + "' target='_blank' >مشاهده ی نتایج</a>"
                        + "</div>";

            }
            if (formRow.get(0).get(Forms._showAllResultToQuestioner).equals("1")) {
                //ساخت دکمه های مشاهده ی نتایج و ارسال آن برای کاربر
                html += "<div class='col-lg-3'>"
                        + "<a class='btn btn-outline-warning mg-b-10  btn-block' href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + formId + "' target='_blank' >مشاهده ی آمار کلی</a>"
                        + "</div>";
            }
            //اگر فرم بعدی گذاشته بود برای این فرم
            if (!formRow.get(0).get(Forms._nextFormId).toString().isEmpty()) {
                html += "<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' href='Server?do=FormAnswerSet.add_new&amp;formAnswers_formId=13' target='_blank'>"
                        + "تکمیل فرم بعدی  "
                        + "</a>";
            }
            script += Js.append("#formAnserSetBtn", html);

            if (formRow.get(0).get(Forms._uniqueComplete).equals("1")) {
                script += "addFormIdToCookie(" + formId + ");\n";//در کوکی ست کنیم که این فرم قبلا ثبت نهایی شده است                
            } else {
                script += "removeFormIdFromCookie(" + formId + ");\n";//در کوکی پاک کنیم که این فرم قبلا ثبت نهایی شده است
            }
        } else {
            changeStatus(id, statues_sabteAvalie, db);
            if (!qNo.isEmpty()) {// اگر به سوالات اجباری پاسخ نداده بود
                message = "شما به " + qNo + "پاسخ نداده اید ";
            }
            message += ". فرم شما موقتا ثبت شد";

//            script += "window.location.replace('Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=" + formId + "&id=" + id + "');\n";// برای اینکه کاربر نتواند دکمه درج را مجدد بزند
        }
        //==========================================================
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        script += Js.setVal("#" + tableName + "_id", id);// وقتی که آی دی را بگذاریم در فرم سمت جاوا اسکریپت کنترل می کنیم که کدام تابع صدا زده بشود
        script += Js.modal(message, "پیام سامانه");
        //@ToDo فرم بعد از این فرم چه باید باشد و صدا زدن ان در ثبت نهایی
        Server.outPrinter(request, response, script);
        return script;

    }

    public static String getClassByStatus(String status) {
        if (status.equals(statues_sabteAvalie)) {
            return "yellow";
        } else if (status.equals(statues_sabteNahei)) {
            return "green";
        }
        return "";
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            request.getRequestDispatcher("template/newFormToComplete.jsp").forward(request, response);

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (formRow.isEmpty()) {
                String errorMessage = "فرم مورد نظر یافت نشد";
                return Js.modal(errorMessage, "پیام سامانه");
            }
            StringBuilder script = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            script.append(Js.setVal("#" + tableName + "_id", formRow.get(0).get(_id).toString()));
//            script.append(Js.setVal("#" + _title, formRow.get(0).get(_title).toString()));
//            script.append(Js.setVal("#" + _title, formRow.get(0).get(_title).toString()));
//            script.append(Js.setVal("#" + _code, formRow.get(0).get(_code).toString()));
//            script.append(Js.setVal("#" + _departmentId, formRow.get(0).get(_departmentId).toString()));      
//            script.append("$('formAnswers_departmentId').select2();\n");
//            script.append(Js.setVal("#" + _isActive, formRow.get(0).get(_isActive).toString()));
//            script.append(Js.setVal("#" + _icon, formRow.get(0).get(_icon).toString()));
//            if (!formRow.get(0).get(_icon).toString().isEmpty()) {//اگر عکس داشت نشان بدهد
//                script.append(Js.setAttr("#forms_icon_Preview", "src", "upload/" + formRow.get(0).get(_icon).toString()));
//            }
//            script.append(Js.setVal("#" + _ownerId, formRow.get(0).get(_ownerId).toString()));
//            script.append(Js.setVal("#" + _ownerRole, formRow.get(0).get(_ownerRole).toString()));
//            script.append(Js.setVal("#" + _accessessUsers, formRow.get(0).get(_accessessUsers).toString()));
//            script.append(Js.setVal("#" + _accessessRoles, formRow.get(0).get(_accessessRoles).toString()));
//
//            script.append(Js.setVal("#" + _creationDate, jjCalendar_IR.getViewFormat(formRow.get(0).get(_creationDate).toString())));
//            script.append(Js.setVal("#" + _expireDate, jjCalendar_IR.getViewFormat(formRow.get(0).get(_expireDate).toString())));
//            jjCalendar_IR date = new jjCalendar_IR();
//
//            script.append(Js.setVal("#" + _creationTime, jj.jjTime.getTime5lenth(formRow.get(0).get(_creationTime).toString())));
//            script.append(Js.setVal("#" + _expireTime, jj.jjTime.getTime5lenth(formRow.get(0).get(_expireTime).toString())));
//
//            script.append(Js.setVal("#" + _nextFormId, formRow.get(0).get(_nextFormId).toString()));
//            script.append(Js.setVal("#" + _isAutoWiki, formRow.get(0).get(_isAutoWiki).toString()));
//            script.append(Js.setVal("#" + _hasAutoWikiInContent, formRow.get(0).get(_hasAutoWikiInContent).toString()));
//            script.append(Js.setVal("#" + _css, formRow.get(0).get(_css).toString()));
//            script.append(Js.setVal("#" + _javaScript, formRow.get(0).get(_javaScript).toString()));
//            script.append(Js.setValSummerNote("#" + _htmlContent, formRow.get(0).get(_htmlContent).toString()));
//            script.append(Js.setVal("#" + _description, formRow.get(0).get(_description).toString()));
//            script.append(Js.setVal("#" + _description, formRow.get(0).get(_description).toString()));
            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjForms.edit(id) + "' id='edit_Forms_new'>" + lbl_edit + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjForms.delete(id) + "' id='edit_Forms_new'>" + lbl_delete + "</button></div>";
            }
            script.append(Js.setHtml("#forms_buttons", htmlBottons));
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            return script.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            } else {
                String id = jjTools.getParameter(request, _id);
                if (!jjNumber.isDigit(id)) {
                    return Js.modal("کد صحیح نیست", "پیام سامانه");
                }
                String RoleId = jjTools.getSeassionUserRole(request);//
                if (db.delete(FormAnswers.tableName, FormAnswers._answerSet_id + "=" + id)) {
//                    db.delete(tableName, _id + "=" + id + " AND ( " + _userId + "=" + jjTools.getSeassionUserId(request) + ")");
                    db.delete(tableName, _id + "=" + id);//shiran//برای اینکه کسی که دسترسی حذف را دارد حذف کند
                    Server.outPrinter(request, response, "hmisFormAnswerSets.showAllForms();");
                } else {
                    return Js.modal("عدم موفقیت عملیات حذف!!!", "پیام سامانه");
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String requestChangeStatus(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            } else {
                String id = jjTools.getParameter(request, _id);
                if (!jjNumber.isDigit(id)) {
                    return Js.modal("کد صحیح نیست", "پیام سامانه");
                }
                String result = changeStatus(id, statues_sabteAvalie, db);
                if (result.isEmpty()) {

                    Server.outPrinter(request, response, Js.modal("فرم تکمیل شده به وضعیت ثبت اولیه تغییر کرد", "پیام سامانه"));
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

}
