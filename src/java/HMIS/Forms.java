/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import static cms.cms.Content.ConvertToWiki;
import static cms.cms.Content.resetAllAutoWikies;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class Forms {

    public static final String tableName = "hmis_forms";
    public static final String _id = "id";
    public static final String _code = "forms_code";
    public static final String _title = "forms_title";
    public static final String _category_id = "forms_category_id";
    public static final String _priority = "forms_priority";
//    دسترسی های یک فرم برای پر کردن فرم ها
    public static final String _departments = "forms_departments";//بخش یا بخش هایی که این فرم را باید ببینند
    public static final String _accessessRoles = "forms_accesseRoles";//سمت هایی که به این فرم دسترسی 
    public static final String _accessessUsers = "forms_accessesUsers";// اشخاصی که به این فرم دسترسی 
    public static final String _accessessGroupId = "forms_accessessGroupId";// گروهایی که به این فرم دسترسی 
//       
    public static final String _tags = "forms_tags";//برچسب هایی که میتوان به این فرم داد و بر اساس برچسب ها در بخش های مختلف نشان داده شود
    public static final String _icon = "forms_icon";
    public static final String _description = "forms_discription";
    public static final String _htmlContent = "forms_htmlContent";
    public static final String _htmlContentWithWikiLinks = "forms_htmlContentWithWikiLinks";
    public static final String _ownerId = "forms_ownerId";//آی دی ایجاد کننده ی فرم
    public static final String _ownerRole = "forms_ownerRole";//سمت ایجاد کننده ی فرم
    public static final String _resultAccessRole = "forms_resultAccessRole";//نتایج آماری را به چه نقش هایی نشان بدهیم
    public static final String _resultAccessUsers = "forms_resultAccessUsers";//شخص هایی که میتوانند نتایج آماری را ببینند
    public static final String _resultGroupId = "forms_resultGroupId";//گروهایی که میتوانند نتایج آماری را ببینند
    public static final String _isActive = "forms_isActive";
    public static final String _isRandomQuestion = "forms_isRandomQuestion";
    public static final String _isRandomOption = "forms_isRandomOption";
    public static final String _showQuestionsOneByOne = "forms_showQuestionsOneByOne";
    public static final String _isDateEditable = "forms_isDateEditable";//برای اینکه پر کننده ی فرم بتواند تاریخ را تغییر دهد
    public static final String _isChangeableUserId = "forms_isChangeableUserId";//برای اینکه پر کننده را تغییر داد 
    public static final String _isActiveDepartment = "forms_isActiveDepartment";
    public static final String _isRequiredDepartment = "forms_isRequiredDepartment";
    public static final String _creationDate = "forms_creationDate";
    public static final String _creationTime = "forms_creationTime";
    public static final String _expireDate = "forms_expireDate";
    public static final String _expireTime = "forms_expireTime";
    public static final String _css = "forms_css";// برای قراردادن کد های سی اس اس
    public static final String _javaScript = "forms_javaScript";// برای قراردادن کد های جاوا اسکریپت
    public static final String _visit = "forms_visit";
    public static final String _nextFormId = "forms_nextFormId";// آی دی فرم بعدی که بعد از این فرم باید لود بشود
    public static final String _isAutoWiki = "forms_isAutoWiki";//برای این فرم لینک اتو ویکی در محتوا ساخته شود یا خیر
    public static final String _showResultToQuestioner = "forms_showResultToQuestioner";//کسی که فرم را پر می کند نتیجه ی آزمونش را همان موقع ببیند یا نه
    public static final String _showAllResultToQuestioner = "forms_showAllResultToQuestioner";//کسی که فرم را پر می کند نتیجه ی آمار را همان موقع ببیند یا نه
    public static final String _showCometePreAproveForm = "forms_showCometePreAproveForm";//کسی که فرم را پر می کند نتیجه ی آمار را همان موقع ببیند یا نه
    public static final String _uniqueComplete = "forms_uniqueComplete";//کسی که فرم را پر می کند نتیجه ی آمار را همان موقع ببیند یا نه
    public static final String _hasAutoWikiInContent = "forms_hasAutoWikiInContent";//در محتوای این فرم اتو ویکی فعال باشد یا نه
    public static final String _autoWikIsUpdate = "forms_autoWikIsUpdate";//در محتوای این فرم اتو ویکی فعال باشد یا نه
    public static final String _formAnswerSetJspName = "forms_formAnswerSetJspName";//نام jsp مخصوص
    public static final String lbl_insert = "ثبت و افزودن سوال";
    public static final String lbl_delete = "حذف فرم";
    public static final String lbl_copy = "کپی فرم";
    public static final String lbl_edit = "ثبت ویرایش";
    public static final String lbl_add_en = "افزودن زبان انگلیسی";
    public static final String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static final String lbl_add_ar = "افزودن زبان عربی";
    public static final String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfsInfectionControl = 0;//نمایش زیر ماژول کنترل عفونت
    public static int rul_formsModule = 347;//60;
    public static int rul_rfsAll = 352;//60;
    public static int rul_rfsNursing = 353;//نمایش ماژول پرستاری
    public static int rul_rfs = 348;//60;
    public static int rul_rfs_own = 0;// 61;//فقط امکان دیدن فرم های ایجاد شده ی توسط خود ایجاد کننده را دارد // بر روی سمت
    public static int rul_changeOwnerForm = 354;//امکان تغییر مالک فرم
    public static int rul_ins = 349;// 62;
    public static int rul_edt = 350;// 63;
    public static int rul_copy = 0;// 63;
    public static int rul_dlt = 351;// 64;
    public static int rul_5 = 0;// 65;
    public static int rul_6 = 0;// 66;
    public static int rul_7 = 0;// 67;
    public static int rul_8 = 0;// 68;
    public static int rul_9 = 0;// 69;
    public static int rul_10 = 0;// 70;

//    public static int rul_lng5 = 68;
    public static final String lbl_add_ln2 = "برچسب";
    public static final String lbl_edit_ln2 = "ویرایش بخش انگلیسی";
    public static final String lbl_add_ln3 = "افزودن زبان عربی";
    public static final String lbl_edit_ln3 = "ویرایش بخش عربی";
    public static final String lbl_add_ln4 = "افزودن زبان آلمانی";
    public static final String lbl_edit_ln4 = "ویرایش بخش آلمانی";
    public static final String lbl_add_ln5 = "افزودن زبان چینی";
    public static final String lbl_edit_ln5 = "ویرایش بخش چینی";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            //@ToDo درست کردن کد های درسترسی برای قسمت هایی که کاربر فقط بتواند فرم های خودش را ببیند
            String where;
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                where = " hmis_forms.id >0 AND " + _ownerId + "=" + jjTools.getSeassionUserId(request);
            } else {
                where = " hmis_forms.id >0 ";
            }
            DefaultTableModel dtm = db.otherSelect("SELECT  hmis_forms.id," + _code + "," + _category_id + "," + _departments + " ," + _ownerId + "," + _isActive + "," + _title + " "
                    + ",COUNT(CASE WHEN formAnswers_status = 'ثبت اولیه' THEN 1 END)As sabteAvalie,\n"
                    + " COUNT(CASE WHEN formAnswers_status = 'ثبت نهایی' THEN 1 END)As sabteNahei"
                    + " FROM hmis_forms "
                    + " LEFT JOIN\n"
                    + "  hmis_formAnswerSet ON hmis_forms.id=formAnswers_formId"
                    + " where  "
                    + where + " GROUP BY hmis_forms.id "
                    + " ORDER BY hmis_forms.id ");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary'>");
            html.append("<div class='card-header col-lg-12 bg-primary tx-white'>لیست فرم ها و چک لیست های تعریف شده</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div class='col-lg mg-10'>"
                    + "<a href='#' class='sh-pagetitle-icon' style='float:right'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisForms.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "<a href='#' id='learnFormsIcon' class='sh-pagetitle-icon' style='float:left' title='آموزش ماژول فرم ساز'>"
                    + "<div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>"
                    + "</div>"
                    + "</a>"
                    + "<span  style='display:block;' class='col-lg-2 mg-t-10'>"
                    + "<div id='forms_learn' style='display:none;text-align:left'>"
                    + "<a href='http://94.184.89.113/upload/learnHMIS/Formsaz.mp4'>فیلم آموزشی</a>"
                    + "<br/>"
                    + "<a target='_blank' href='" + Server.mainSite + "/template/learn/forms.pdf'>فایل آموزشی</a>"
                    + "</div>"
                    + "</span>"
                    + "</div>"
                    + "");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append("<p class='mg-b-20 mg-sm-b-30'>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisForms.m_add_new();' >فرم یا چک لیست جدید</a>");
                html.append("</p>");
            }

            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refresh' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<tr>"
                    + "<th width='10%' class='r'>کد</th>");
            html.append("<th width='20%' class='c'>عنوان فرم</th>");
            html.append("<th width='10%' class='c'>گروه بندی</th>");
            html.append("<th width='10%' class='c'>مالک فرم</th>");
            html.append("<th width='10%' class='c'>بخش</th>");
            html.append("<th width='10%' class='c'>وضعیت فرم</th>");
            html.append("<th width='10%' class='c'>تعداد فرم های تکمیل شده</th>");
            html.append("<th width='5%' class='c'>ویرایش واصلاح</th>");
            html.append("<th width='5%' class='c'>حذف فرم</th>");
            html.append("<th width='10%' class='c'>اطلاعات تکمیل شده فرم</th>");
            html.append("<th width='5%' class='c'>آنالیزوآمار</th>");
            html.append("<th width='5%' class='c'>پیش نمایش فرم</th>");
            html.append("</tr>");
            html.append(""
                    + "            <tr>"
                    + "                <th></th>"
                    + "                <th>عنوان فرم</th>"
                    + "                <th>گروه بندی</th>"
                    + "                <th>مالک فرم</th>"
                    + "                <th>بخش</th>"
                    + "                <th>وضعیت فرم</th>\n"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "            </tr>\n"
                    + "        </thead>" + "<tbody>");
            for (int i = 0; i < row.size(); i++) {
                List<Map<String, Object>> answerSetRow = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, FormAnswerSet._formId + "=" + row.get(i).get(_id).toString()));
                html.append("<tr  class='mousePointer'>");
                String code = row.get(i).get(_code).toString().isEmpty() ? row.get(i).get(_id).toString() : row.get(i).get(_code).toString();
                html.append("<td class='c'>" + code + "<span></td>");
                html.append("<td class='c'>" + row.get(i).get(_title)
                        + "</td>"
                );
                html.append("<td class='c'>" + row.get(i).get(_category_id) + "</td>");
                html.append("<td class='c'>" + Access_User.getUserName(row.get(i).get(_ownerId).toString(), db) + "</td>");
                html.append("<td class='c'>" + Department.getDepartmentName(row.get(i).get(_departments).toString(), db) + "</td>");
                String isActive = row.get(i).get(_isActive).toString().equals("1") ? "فعال" : "غیرفعال";
                html.append("<td class='c'>" + isActive + "</td>");
                html.append("<td class='c'>"
                        + "<b style='color:red'>تعداد فرم های ثبت اولیه  :" + row.get(i).get("sabteAvalie")
                        + "<br/><b style='color:green'>"
                        + "تعداد فرم های ثبت نهایی "
                        + ":" + row.get(i).get("sabteNahei")
                        + "</td>");
                html.append("<td class='c'><i class='p icon ion-ios-gear' onclick='" + Js.jjForms.select(row.get(i).get(_id).toString()) + "'></i></td>");
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                if (accDelete) {
                    if (answerSetRow.size() > 0) {
                        html.append("<td class='c p'><i  class='fa fa-trash'  onclick='hmisForms.noDeleteForms();' ></i></td>");
                    } else {
                        html.append("<td class='c p'><i  class='fa fa-trash'  onclick='" + Js.jjForms.delete(row.get(i).get(_id).toString()) + "' ></i></td>");
                    }
                } else {
                    html.append("<td><div></div></td>");
                }
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showInformationForm&formAnswers_formId=" + row.get(i).get(_id) + "' target='_blank' >"
                        + "<i class='p fa fa-file-excel-o' ></i></a></td>");
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + row.get(i).get(_id) + "' target='_blank' >"
                        + "<i class='p fa fa-bar-chart'></i></a></td>");
                html.append("<td class='c'><a onclick='hmisFormAnswerSets.refreshMyAnswersAfterQuestion(" + row.get(i).get(_id) + ");' target='_blank' >"
                        + "<i class='p fa fa-eye' style='font-size: 2em;color: #008000'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swFormsTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += "$('#learnFormsIcon').click(function(){"
                    + "if($('#forms_learn').css('display')=='none'){"
                    + "$('#forms_learn').slideDown();"
                    + "}else{"
                    + "$('#forms_learn').slideUp();"
                    + "}"
                    + "});";
            script += "  $('#refresh').DataTable( {\n"
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
            script += Js.table("#refresh", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست فرم ها");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String refreshNursing(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsNursing)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            System.out.println(">>>>>>>>UserRoles is:" + userRoleInsession);
            String where = " AND ((";
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
                where += Forms._accessessGroupId + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
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

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + " where " + Forms._category_id + "='گزارشات پرستاری'  " + where));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            html.append("<table id='refreshNursing' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل یک فرم</th>");
            html.append("<th class='c'>اطلاعات تکمیل فرم</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("<tr>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("</tr>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + (!formRows.get(i).get(Forms._code).toString().isEmpty() ? "(" + formRows.get(i).get(Forms._code) + ") " : "") + formRows.get(i).get(Forms._id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='c'><i class='p icon fa fa-pencil' onclick='hmisNursing.refreshMyAnswersInNursing(" + formRows.get(i).get(Forms._id).toString() + ")'></i></td>");
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
                script.append(Js.setHtml("#swNursingTbl", html));
//                script.append(Js.table("#refreshNursing", "", 0, "", "لیست فرم پرستاری"));
                script.append("  $('#refreshNursing').DataTable( {\n"
                        + "        initComplete: function () {\n"
                        + "            this.api().columns([1]).every( function () {\n"
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
                        + "                                                                    , paging: false\n"
                        + "                                                                    , dom: 'Bfrtip'\n"
                        + "                                                                    , buttons: ['copy', 'csv', 'excel', 'print']\n"
                        + ""
                        + ""
                        + ""
                        + "    } );"
                        + "$('.columnSelectSearch').select2({\n"
                        + "                    width: '100%'\n"
                        + "                });");
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            StringBuilder script = new StringBuilder();
            if (!accIns) {
                script.append(Js.setHtml("#forms_buttons", ""));
                script.append(Js.modal("لطفا دوباره وارد شوید", "شما دسترسی به این قسمت ندارد"));
                Server.outPrinter(request, response, script);
                return "";
            }
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, _id + "=" + jjTools.getSeassionUserId(request)));//برای استخراج نام و نام خانوادگی کاربری که در سشن فعال است
//            if (!Access_User.hasAccess(request, db, rul_changeOwnerForm)) {
//                script.append(Js.setAttr("#" + _ownerId, "disabled", "disabled"));
//            } else {
//                script.append(Js.removeAttr("#" + _ownerId, "disabled"));
//            }
            script.append(Js.setVal("#" + _ownerId, jjTools.getSeassionUserId(request)));
            script.append(Js.select2("#" + _ownerId, "width:'100%'"));
            if (!Access_User.hasAccess(request, db, rul_changeOwnerForm)) {
                script.append(Js.setAttr("#" + _ownerId, "disabled", "disabled"));
                String userRolesOptions = Role.getUeserRolesSelectOption(request, response, db, true);//از سشن نقش های فرد وارد شده را می خواند 
                if (!userRolesOptions.isEmpty()) {// ممکن است کاربر جاری نقشی در سیستم نداشته باشد ولی دسترسی هایی داشته باشد
                    script.append(Js.setHtml("#" + _ownerRole, userRolesOptions));
                } else {
                    script.append(Js.setVal("#" + _ownerRole, "<option value=''></option>"));
                }
            } else {
                script.append(Js.removeAttr("#" + _ownerId, "disabled"));
                script.append(Js.setAttr("#" + _ownerId, "onchange", "hmisRole.getUeserRolesSelectOptionByUserId($(this).val(),'#" + _ownerRole + "');"));
            }

//            script.append(Js.setVal("#forms_ownerName", userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()));
//            script.append(Js.setValSelectOption("#" + _ownerId, String.valueOf(jjTools.getSeassionUserId(request))));
//            String userRolesOptions = Role.getUeserRolesSelectOption(request, response, db, true);//از سشن نقش های فرد وارد شده را می خواند 
//            if (!userRolesOptions.isEmpty()) {// ممکن است کاربر جاری نقشی در سیستم نداشته باشد ولی دسترسی هایی داشته باشد
//                script.append(Js.setHtml("#" + _ownerRole, userRolesOptions));
//            } else {
//                script.append(Js.setVal("#" + _ownerRole, "<option value=''></option>"));
//            }
            script.append(Js.setVal("#" + _creationDate, jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length(null, true))));
            script.append(Js.setVal("#" + _creationTime, jj.jjTime.getTime5lenth("")));
            script.append(Js.select2("#" + _ownerRole, "width:'100%'"));
            script.append(Js.setHtml("#forms_buttons", "<div class='col-lg-6'><input type='button' id='insert_Forms_new'  value=\"" + lbl_insert + "\" class='btn btn-success btn-block mg-b-10'></div>"));
            script.append(Js.click("#insert_Forms_new", Js.jjForms.insert()));
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
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_formAnswerSetJspName, jjTools.getParameter(request, _formAnswerSetJspName));

            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_departments, "," + jjTools.getParameter(request, _departments) + ",");
            map.put(_category_id, jjTools.getParameter(request, _category_id));//گروه بندی 
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_isActiveDepartment, jjTools.getParameter(request, _isActiveDepartment));
            map.put(_isChangeableUserId, jjTools.getParameter(request, _isChangeableUserId));
            map.put(_isRequiredDepartment, jjTools.getParameter(request, _isRequiredDepartment));
            map.put(_isRandomOption, jjTools.getParameter(request, _isRandomOption));
            map.put(_isRandomQuestion, jjTools.getParameter(request, _isRandomQuestion));
            map.put(_showQuestionsOneByOne, jjTools.getParameter(request, _showQuestionsOneByOne));
            map.put(_isDateEditable, jjTools.getParameter(request, _isDateEditable));
            map.put(_icon, jjTools.getParameter(request, _icon));
            if (!Access_User.hasAccess(request, db, rul_changeOwnerForm)) {
                map.put(_ownerId, jjTools.getSeassionUserId(request));
            } else {
                map.put(_ownerId, jjTools.getParameter(request, _ownerId));
            }
            map.put(_ownerRole, jjTools.getParameter(request, _ownerRole));
            map.put(_accessessUsers, "," + jjTools.getParameter(request, _accessessUsers) + ",");
            map.put(_accessessGroupId, "," + jjTools.getParameter(request, _accessessGroupId) + ",");
            map.put(_accessessRoles, "," + jjTools.getParameter(request, _accessessRoles) + ",");
            map.put(_resultAccessUsers, "," + jjTools.getParameter(request, _resultAccessUsers) + ",");
            map.put(_resultAccessRole, "," + jjTools.getParameter(request, _resultAccessRole) + ",");
            map.put(_resultGroupId, "," + jjTools.getParameter(request, _resultGroupId) + ",");
            if ("".equals(jjTools.getParameter(request, _creationDate))) {// اگر تاریخ شروع اعتبار وارد نکرده بود
                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(null, true));
            } else {
                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _accessessRoles), true));
            }
            jjCalendar_IR date = new jjCalendar_IR();
            map.put(_creationTime, jj.jjTime.getTime4lenth(jjTools.getParameter(request, _creationTime)));
            map.put(_expireDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _expireDate), false));
            map.put(_expireTime, jj.jjTime.getTime4lenth(jjTools.getParameter(request, _expireTime)));
            map.put(_nextFormId, jjTools.getParameter(request, _nextFormId).equals("") || jjTools.getParameter(request, _nextFormId).equals("null") ? "0" : jjTools.getParameter(request, _nextFormId));
            map.put(_uniqueComplete, jjTools.getParameter(request, _uniqueComplete));
            map.put(_showResultToQuestioner, jjTools.getParameter(request, _showResultToQuestioner));
            map.put(_showAllResultToQuestioner, jjTools.getParameter(request, _showAllResultToQuestioner));
            map.put(_showCometePreAproveForm, jjTools.getParameter(request, _showCometePreAproveForm));
            map.put(_hasAutoWikiInContent, jjTools.getParameter(request, _hasAutoWikiInContent));
            map.put(_css, jjTools.getParameter(request, _css));
            map.put(_javaScript, jjTools.getParameter(request, _javaScript));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));

            String needToAutoWiki = jjTools.getParameter(request, _hasAutoWikiInContent);
            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _htmlContent), db, false);
                map.put(_htmlContentWithWikiLinks, autoWikeContent);
            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
                map.put(_htmlContentWithWikiLinks, jjTools.getParameter(request, _htmlContent));
                map.put(_autoWikIsUpdate, jjTools.getParameter(request, _autoWikIsUpdate));
            }
            map.put(_isAutoWiki, jjTools.getParameter(request, _isAutoWiki));
            if ("1".equals(jjTools.getParameter(request, _isAutoWiki).toString())) {
                resetAllAutoWikies(request, db);
            }

            map.put(_autoWikIsUpdate, "1");//خود این محتوا آپدیت است
            map.put(_description, jjTools.getParameter(request, _description));

            List<Map<String, Object>> insertedFormRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            StringBuilder script = new StringBuilder();
            if (insertedFormRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            script.append(Js.jjForms.refresh());
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            script.append(Js.jjForms.select(insertedFormRow.get(0).get(_id).toString()));// برای اینکه در واقع مثل موقعی بشود که یک فرم قبلا ثبت شده را انتخاب کرده سلکت جاوا اسکریپتی را بعد از اینسرت صدا میزنیم
            script.append("$('#questionsAndoptionsForm').show();");
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * کپی فرم با سوالات تغییر shiran2 13981214
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String copy(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_copy)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String formId = jjTools.getParameter(request, _id).toString();
            System.out.println("formId=" + formId);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _id + "=" + formId));

            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_title, "کپی" + " " + row.get(0).get(_title));
            map.put(_code, row.get(0).get(_code));
            map.put(_formAnswerSetJspName, row.get(0).get(_formAnswerSetJspName));
            map.put(_departments, row.get(0).get(_departments));
            map.put(_isActive, row.get(0).get(_isActive));
            map.put(_isActiveDepartment, row.get(0).get(_isActiveDepartment));
            map.put(_isChangeableUserId, row.get(0).get(_isChangeableUserId));
            map.put(_isRequiredDepartment, row.get(0).get(_isRequiredDepartment));
            map.put(_isDateEditable, row.get(0).get(_isDateEditable));
            map.put(_icon, row.get(0).get(_icon));
            map.put(_ownerId, row.get(0).get(_ownerId));
            map.put(_ownerRole, row.get(0).get(_ownerRole));
            map.put(_accessessUsers, row.get(0).get(_accessessUsers));
            map.put(_accessessGroupId, row.get(0).get(_accessessGroupId));
            map.put(_accessessRoles, row.get(0).get(_accessessRoles));
            map.put(_resultAccessUsers, row.get(0).get(_resultAccessUsers));
            map.put(_resultAccessRole, row.get(0).get(_resultAccessRole));
            map.put(_resultGroupId, row.get(0).get(_resultGroupId));
            map.put(_isRandomOption, row.get(0).get(_isRandomOption));
            map.put(_isRandomQuestion, row.get(0).get(_isRandomQuestion));
            map.put(_showQuestionsOneByOne, row.get(0).get(_showQuestionsOneByOne));
//            if ("".equals(row.get(0).get(_creationDate).toString())) {// اگر تاریخ شروع اعتبار وارد نکرده بود
//                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(null, true));
//            } else {
//                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _accessessRoles), true));
//            }
            jjCalendar_IR date = new jjCalendar_IR();
            map.put(_creationDate, date.getDBFormat_8length());
            map.put(_creationTime, date.getHours_2length());
            map.put(_expireDate, row.get(0).get(_expireDate));
            map.put(_category_id, row.get(0).get(_category_id));
            map.put(_expireTime, row.get(0).get(_expireTime));
            map.put(_nextFormId, row.get(0).get(_nextFormId));
            map.put(_uniqueComplete, row.get(0).get(_uniqueComplete));
            map.put(_showResultToQuestioner, row.get(0).get(_showResultToQuestioner));
            map.put(_showAllResultToQuestioner, row.get(0).get(_showAllResultToQuestioner));
            map.put(_showCometePreAproveForm, row.get(0).get(_showCometePreAproveForm));
            map.put(_hasAutoWikiInContent, row.get(0).get(_hasAutoWikiInContent));
            map.put(_css, row.get(0).get(_css));
            map.put(_javaScript, row.get(0).get(_javaScript));
            map.put(_htmlContent, row.get(0).get(_htmlContent));

            String needToAutoWiki = jjTools.getParameter(request, _hasAutoWikiInContent);
            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
                String autoWikeContent = ConvertToWiki(request, row.get(0).get(_htmlContent).toString(), db, false);
                map.put(_htmlContentWithWikiLinks, autoWikeContent);
            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
                map.put(_htmlContentWithWikiLinks, row.get(0).get(_htmlContent));
                map.put(_autoWikIsUpdate, row.get(0).get(_autoWikIsUpdate));
            }
            map.put(_isAutoWiki, row.get(0).get(_isAutoWiki));
            if ("1".equals(row.get(0).get(_isAutoWiki).toString())) {
                resetAllAutoWikies(request, db);
            }

            map.put(_autoWikIsUpdate, "1");//خود این محتوا آپدیت است
            map.put(_description, row.get(0).get(_description));

            List<Map<String, Object>> insertedFormRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            List<Map<String, Object>> FormQuestionsRow = jjDatabaseWeb.separateRow(db.Select(
                    FormQuestions.tableName, FormQuestions._formID + "=" + formId));
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> formQuestionsMap = new HashMap<String, Object>();
            Map<String, Object> formQuestionsOptionMap = new HashMap<String, Object>();
            for (int i = 0; i < FormQuestionsRow.size(); i++) {
                formQuestionsMap.put(FormQuestions._formID, insertedFormRow.get(0).get(_id));
                formQuestionsMap.put(FormQuestions._code, insertedFormRow.get(0).get(_code));
                formQuestionsMap.put(FormQuestions._answersType, FormQuestionsRow.get(i).get(FormQuestions._answersType));
                formQuestionsMap.put(FormQuestions._htmlDiscription, FormQuestionsRow.get(i).get(FormQuestions._htmlDiscription));
                formQuestionsMap.put(FormQuestions._htmlDiscriptionInResult, FormQuestionsRow.get(i).get(FormQuestions._htmlDiscriptionInResult));
                formQuestionsMap.put(FormQuestions._defaultValue, FormQuestionsRow.get(i).get(FormQuestions._defaultValue));
                formQuestionsMap.put(FormQuestions._icon, FormQuestionsRow.get(i).get(FormQuestions._icon));
                formQuestionsMap.put(FormQuestions._isRequierd, FormQuestionsRow.get(i).get(FormQuestions._isRequierd));
                formQuestionsMap.put(FormQuestions._placeHolder, FormQuestionsRow.get(i).get(FormQuestions._placeHolder));
                formQuestionsMap.put(FormQuestions._title, FormQuestionsRow.get(i).get(FormQuestions._title));
                formQuestionsMap.put(FormQuestions._trueAnswer, FormQuestionsRow.get(i).get(FormQuestions._trueAnswer));
                formQuestionsMap.put(FormQuestions._weight, FormQuestionsRow.get(i).get(FormQuestions._weight));
                formQuestionsMap.put(FormQuestions._preority, FormQuestionsRow.get(i).get(FormQuestions._preority));
                String questionId = FormQuestionsRow.get(i).get(FormQuestions._id).toString();
                System.out.println("questionId=" + questionId);
                List<Map<String, Object>> FormQuestionsOptionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                List<Map<String, Object>> insertedFormQuestionRow = jjDatabaseWeb.separateRow(db.insert(FormQuestions.tableName, formQuestionsMap));
                if (FormQuestionsOptionRow.size() > 0) {
                    for (int j = 0; j < FormQuestionsOptionRow.size(); j++) {
                        formQuestionsOptionMap.put(FormQuestionOptions._icon, FormQuestionsOptionRow.get(j).get(FormQuestionOptions._icon));
                        formQuestionsOptionMap.put(FormQuestionOptions._code, FormQuestionsOptionRow.get(j).get(FormQuestionOptions._code));
                        formQuestionsOptionMap.put(FormQuestionOptions._lable, FormQuestionsOptionRow.get(j).get(FormQuestionOptions._lable));
                        formQuestionsOptionMap.put(FormQuestionOptions._value, FormQuestionsOptionRow.get(j).get(FormQuestionOptions._value));
                        formQuestionsOptionMap.put(FormQuestionOptions._question_id, insertedFormQuestionRow.get(0).get(FormQuestions._id));
                        db.insert(FormQuestionOptions.tableName, formQuestionsOptionMap);
                    }
                }
            }
            StringBuilder script = new StringBuilder();
            if (insertedFormRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            script.append(Js.jjForms.refresh());
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            script.append(Js.jjForms.select(insertedFormRow.get(0).get(_id).toString()));// برای اینکه در واقع مثل موقعی بشود که یک فرم قبلا ثبت شده را انتخاب کرده سلکت جاوا اسکریپتی را بعد از اینسرت صدا میزنیم
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            //@ToDo درست کردن کد های درسترسی برای قسمت هایی که کاربر فقط بتواند فرم های خودش را ببیند

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> answerSetRow = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, FormAnswerSet._formId + "=" + id));
            if (formRow.isEmpty()) {
                String errorMessage = "فرم مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            script.append(Js.setVal("#" + tableName + "_id", formRow.get(0).get(_id).toString()));
            script.append(Js.setVal("#" + _title, formRow.get(0).get(_title).toString()));
            script.append(Js.setVal("#" + _code, formRow.get(0).get(_code).toString()));

            script.append(Js.setValSelectOption("#" + _departments, formRow.get(0).get(_departments).toString()));
            script.append(Js.select2("#" + _departments, ""));

//            script.append(Js.setVal("#" + _departments, formRow.get(0).get(_departments).toString()));
//            script.append("$('#forms_departments').select2();\n");
            script.append(Js.setVal("#" + _isActive, formRow.get(0).get(_isActive)));
            script.append(Js.setVal("#" + _formAnswerSetJspName, formRow.get(0).get(_formAnswerSetJspName)));
            script.append(Js.setVal("#" + _isActiveDepartment, formRow.get(0).get(_isActiveDepartment)));
            script.append(Js.setVal("#" + _isChangeableUserId, formRow.get(0).get(_isChangeableUserId)));
            script.append(Js.setVal("#" + _isRequiredDepartment, formRow.get(0).get(_isRequiredDepartment)));
            script.append(Js.setVal("#" + _isRandomOption, formRow.get(0).get(_isRandomOption)));
            script.append(Js.setVal("#" + _isRandomQuestion, formRow.get(0).get(_isRandomQuestion)));
            script.append(Js.setVal("#" + _showQuestionsOneByOne, formRow.get(0).get(_showQuestionsOneByOne)));
            script.append(Js.setVal("#" + _isDateEditable, formRow.get(0).get(_isDateEditable)));
            script.append(Js.setVal("#" + _icon, formRow.get(0).get(_icon).toString()));
            if (!formRow.get(0).get(_icon).toString().isEmpty()) {//اگر عکس داشت نشان بدهد
                script.append(Js.setAttr("#forms_icon_Preview", "src", "upload/" + formRow.get(0).get(_icon).toString()));
            }
            script.append(Js.setValSelectOption("#" + _ownerId, formRow.get(0).get(_ownerId).toString()));
            script.append(Js.select2("#" + _ownerId, ""));
            if (!Access_User.hasAccess(request, db, rul_changeOwnerForm)) {
                script.append(Js.setAttr("#" + _ownerId, "disabled", "disabled"));
                script.append(Js.setVal("#" + _ownerId, jjTools.getSeassionUserId(request)));
                String userRolesOptions = Role.getUeserRolesSelectOption(request, response, db, true);//از سشن نقش های فرد وارد شده را می خواند 
                if (!userRolesOptions.isEmpty()) {// ممکن است کاربر جاری نقشی در سیستم نداشته باشد ولی دسترسی هایی داشته باشد
                    script.append(Js.setHtml("#" + _ownerRole, userRolesOptions));
                } else {
                    script.append(Js.setVal("#" + _ownerRole, "<option value=''></option>"));
                }
            } else {
                script.append(Js.removeAttr("#" + _ownerId, "disabled"));
                script.append(Js.setAttr("#" + _ownerId, "onchange", "hmisRole.getUeserRolesSelectOptionByUserId($(this).val(),'#" + _ownerRole + "');"));
//                script.append("hmisRole.getUeserRolesSelectOptionByUserId(" + formRow.get(0).get(_ownerId).toString() + ",'#" + _ownerRole + "');");
                script.append(Js.setValSelectOption("#" + _ownerRole, formRow.get(0).get(_ownerRole).toString()));
            }
            script.append(Js.select2("#" + _ownerRole, ""));

//            script.append(Js.setVal("#forms_ownerName", Access_User.getUserName(formRow.get(0).get(_ownerId).toString(), db)));
            script.append(Js.setVal("#" + _category_id, formRow.get(0).get(_category_id).toString()));
            script.append(Js.select2("#" + _category_id, ""));
            script.append(Js.setValSelectOption("#" + _accessessUsers, formRow.get(0).get(_accessessUsers).toString()));
            script.append(Js.select2("#" + _accessessUsers, ""));
            script.append(Js.setValSelectOption("#" + _accessessGroupId, formRow.get(0).get(_accessessGroupId).toString()));
            script.append(Js.select2("#" + _accessessGroupId, ""));
            script.append(Js.setValSelectOption("#" + _accessessRoles, formRow.get(0).get(_accessessRoles).toString()));
            script.append(Js.select2("#" + _accessessRoles, ""));
            script.append(Js.setValSelectOption("#" + _resultAccessRole, formRow.get(0).get(_resultAccessRole).toString()));
            script.append(Js.select2("#" + _resultAccessRole, ""));
            script.append(Js.setValSelectOption("#" + _resultGroupId, formRow.get(0).get(_resultGroupId).toString()));
            script.append(Js.select2("#" + _resultGroupId, ""));
            script.append(Js.setValSelectOption("#" + _resultAccessUsers, formRow.get(0).get(_resultAccessUsers).toString()));
            script.append(Js.select2("#" + _resultAccessUsers, ""));

            script.append(Js.setVal("#" + _creationDate, jjCalendar_IR.getViewFormat(formRow.get(0).get(_creationDate).toString())));
            script.append(Js.setVal("#" + _expireDate, jjCalendar_IR.getViewFormat(formRow.get(0).get(_expireDate).toString())));
            jjCalendar_IR date = new jjCalendar_IR();

            script.append(Js.setVal("#" + _creationTime, jj.jjTime.getTime5lenth(formRow.get(0).get(_creationTime).toString())));
            script.append(Js.setVal("#" + _expireTime, jj.jjTime.getTime5lenth(formRow.get(0).get(_expireTime).toString())));
            script.append(Js.setVal("#" + _nextFormId, formRow.get(0).get(_nextFormId).toString()));
            script.append(Js.select2("#" + _nextFormId, ""));
            script.append(Js.setVal("#" + _isAutoWiki, formRow.get(0).get(_isAutoWiki).toString()));
            script.append(Js.setVal("#" + _uniqueComplete, formRow.get(0).get(_uniqueComplete).toString()));
            script.append(Js.setVal("#" + _showAllResultToQuestioner, formRow.get(0).get(_showAllResultToQuestioner)));
            script.append(Js.setVal("#" + _showCometePreAproveForm, formRow.get(0).get(_showCometePreAproveForm)));
            script.append(Js.setVal("#" + _showResultToQuestioner, formRow.get(0).get(_showResultToQuestioner)));
            script.append(Js.setVal("#" + _hasAutoWikiInContent, formRow.get(0).get(_hasAutoWikiInContent)));
            script.append(Js.setVal("#" + _css, formRow.get(0).get(_css).toString()));
            script.append(Js.setVal("#" + _javaScript, formRow.get(0).get(_javaScript).toString()));
            script.append(Js.setValSummerNote("#" + _htmlContent, formRow.get(0).get(_htmlContent).toString()));
            script.append(Js.setValSummerNote("#" + _description, formRow.get(0).get(_description).toString()));

            String htmlBottons = "";
            String htmlBottons2 = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-warning btn-block mg-b-10' onclick='" + Js.jjForms.edit(id) + "' id='edit_Forms_new'>" + lbl_edit + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                if (answerSetRow.size() > 0) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-danger btn-block mg-b-10' onclick='hmisForms.noDeleteForms();' >" + lbl_delete + "</button></div>";
                } else {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-danger btn-block mg-b-10' onclick='" + Js.jjForms.delete(id) + "' id='edit_Forms_new'>" + lbl_delete + "</button></div>";
                }
            }
//            boolean accCopyQuestion = Access_User.hasAccess(request, db, FormQuestions.rul_copy);
//            if (accCopyQuestion) {
//                
//                    htmlBottons2 += "";
//                }

            boolean accCopy = Access_User.hasAccess(request, db, rul_copy);
            if (accCopy) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_copy + "' class='btn btn-info btn-block mg-b-10' onclick='hmisForms.copy(" + id + ")' id='edit_Forms_new'>" + lbl_copy + "</button></div>";
            }
            htmlBottons += "<div class='col-lg'><button title='پیش نمایش فرم' class='btn btn-success btn-block mg-b-10' onclick='hmisFormAnswerSets.refreshMyAnswersAfterQuestion(" + id + ");' >پیش نمایش فرم</button></div>";
            script.append(Js.setHtml("#forms_buttons", htmlBottons));
            script.append(Js.setHtml("#button_copyQuestionForm", htmlBottons2));
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//    /**
//     * برگرداندن سطر و ستون
//     *
//     * @param request
//     * @param response
//     * @param db
//     * @param needString
//     * @return
//     * @throws Exception
//     */
//    public static String showTableOfFormAnswers2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        try {
//            String startDate = jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", "");
//            String endDate = jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", "");
//            String formAnswers_formId = jjTools.getParameter(request, "id");
//            String sumTd = "";
//            StringBuilder html = new StringBuilder();
//            html.append("<table class='table table-striped table-bordered display wrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers2'>");
//            html.append("<thead>");
//            html.append("<tr>");
//            String questionId = "";
//            html.append("<th style='text-align:right;'  onclick='hightLightTableRow($(this));'>نام تکمیل کننده و تاریخ و ساعت تکمیل فرم</th>");
//            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
//                    + "SELECT hmis_formquestions.formQuestions_title,hmis_formquestions.formQuestions_code,"
//                    + " hmis_formquestions.id,formQuestions_weight FROM hmis_formquestions"
//                    + " WHERE formQuestions_formID=" + formAnswers_formId
//                    + " ORDER BY ABS(formQuestions_preority) ASC"));
//            for (int i = 0; i < formQuestionRows.size(); i++) {// 
//                html.append("<th style='text-align:right;'  onclick='hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title)
//                        + "<br/>" + formQuestionRows.get(i).get(FormQuestions._weight) + "</th>");
//                questionId += formQuestionRows.get(i).get(FormQuestions._id) + ",";
//            }
//            html.append("</tr>");
//            html.append("</thead>");
//            html.append("<tbody>");
//            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
//            String where = "";
//            if (!startDate.equals("")) {
//                where += " AND " + FormAnswerSet._date + ">=" + startDate;
//            }
//            if (!endDate.equals("")) {
//                where += " AND " + FormAnswerSet._date + "<=" + endDate;
//            }
//            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
//                    "SELECT "
//                    + "hmis_formanswerset.id,"
//                    + "formAnswers_time,"
//                    + "formAnswers_departmentId,"
//                    + "hmis_formanswerset.hmis_formanswerset_sum,"
//                    + "hmis_formanswerset.hmis_formanswerset_avg,"
//                    + "hmis_formanswerset.formAnswers_date,"
//                    + " formAnswers_userName "
//                    + " FROM hmis_formanswerset "
//                    + " WHERE formAnswers_formId= " + formAnswers_formId + "  " + where
//            ));
//
//            for (int i = 0; i < formAnswerSetRows.size(); i++) {// 
//                sumTd += "مجموع :" + formAnswerSetRows.get(i).get(FormAnswerSet._sum) + "<br/> میانگین" + formAnswerSetRows.get(i).get(FormAnswerSet._avg);
//                html.append("<tr>");
//                html.append("<td>"
//                        + "<br/>" + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
//                        + "<br/>" + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
//                        + "<br/>" + formAnswerSetRows.get(i).get(FormAnswerSet._userName)
//                        + "<br/>نام بخش:" + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
//                        + "<br/>" + sumTd
//                        + "</td>");
//
//                String[] questionIdArray = questionId.split(",");
//                for (int k = 0; k < questionIdArray.length; k++) {// هر سوال در ستون اول هر ردیف می آید
//                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "hmis_formquestions.id,hmis_formquestions.formQuestions_answersType ", FormQuestions._id + "=" + questionIdArray[k]));
//                    if (questionsRow.get(0).get(FormQuestions._answersType).equals("radio") || questionsRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
//                        // پاسخ همه ی کاربران به سوال
//                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
//                                "SELECT "
//                                + "formQuestionOptions_lable,"
//                                + "hmis_formquestions.id "
//                                + " FROM hmis_formanswers "
//                                + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
//                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
//                                + " WHERE "
//                                + " hmis_formanswers.formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
//                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
//                                + " ORDER BY ABS(formQuestions_preority) ASC"
//                                + "" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
//
//                        ));
//                        if (!userAnsewrRows.isEmpty()) {
//                            for (int j = 0; j < userAnsewrRows.size(); j++) {
//                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {//زمانی که چنین سوالی وجود داشت 
//                                    html.append("<td>" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + "<br/>"
//                                            + "</td>");
//                                } else {
//                                    html.append("<td>-</td>");
//                                }
//                            }
//                        } else {
//
//                            html.append("<td>-</td>");
//                        }
//                    } else if (questionsRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
//                        // @ToDo چک شود که برای چک باکس هاس استاندارد درست عمل می کند
//                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
//                                "SELECT "
//                                + " formQuestionOptions_lable"
//                                + ",hmis_formquestions.id"
//                                + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
//                                + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
//                                + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
//                                // + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
//                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
//                                + " WHERE "
//                                + " formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
//                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
//                                + " GROUP BY formanswers_questionId "
//                                + " ORDER BY ABS(formQuestions_preority) ASC;"
//                        ));
//                        if (!userAnsewrRows.isEmpty()) {
//                            for (int j = 0; j < userAnsewrRows.size(); j++) {
//                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {
//                                    html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>"
//                                            + userAnsewrRows.get(j).get("chechedValues") + "</td>");
//                                } else {
//                                    html.append("<td>()</td>");
//                                }
//                            }
//                        } else {
//                            html.append("<td>()</td>");
//                        }
//                    } else {
//                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
//                                "SELECT formanswers_answer,hmis_formquestions.id FROM hmis_formanswers "
//                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
//                                + " WHERE "
//                                + "  formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
//                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
//                                + " ORDER BY ABS(formQuestions_preority) ASC"
//                        // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
//                        ));
//                        if (!userAnsewrRows.isEmpty()) {
//                            for (int j = 0; j < userAnsewrRows.size(); j++) {
//                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {
//                                    html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
//                                } else {
//                                    html.append("<td>()</td>");
//                                }
//                            }
//                        } else {
//                            html.append("<td>()</td>");
//                        }
//                    }
//                }
//                html.append("</tr>");
//            }
////            html.append("<tr>");
////            html.append("<td>-</td>");
////            html.append("<td>-</td>");
////            html.append("<td>-</td>");
//////            html.append(sumTd);             
////            html.append("</tr>");      
//            html.append("</tbody></table>");
//            String panel = jjTools.getParameter(request, "panel");
//            String script = Js.setHtml("#" + panel, html.toString());
//            script += "$('#showTableOfFormAnswers2').dataTable("
//                    + "{"
//                    + "fixedColumns:   {leftColumns: 2},"
//                    + "fixedHeader: true,"
//                    + "iDisplayLength:100"
//                    + ",bJQueryUI: true"
//                    + ",sPaginationType: 'full_numbers'"
//                    + ",columnDefs: [    { orderable: false, targets: 0 }  ]"
//                    + ",paging: false"
//                    + ",dom: 'Bfrtip'"
//                    + ",buttons: ['copy', 'csv', 'excel', 'print']"
//                    + "});";
//            Server.outPrinter(request, response, script);
//            return script;
//        } catch (Exception ex) {
//            Server.outPrinter(request, response, Server.ErrorHandler(ex));
//            return "";
//        }
//    }
    /**
     * در گزارش فرم ها اولین خروجی اکسل
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String showTableOfFormAnswers(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String formAnswers_formId = jjTools.getParameter(request, "id");
//            List<Map<String, Object>> formRowAnswers = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formanswerset hfs left join hmis_formanswers hf on hf.formanswers_answerSet_id=hfs.id where formAnswers_formId=" + formAnswers_formId + ";"));
            String sumTd = "";
            StringBuilder html = new StringBuilder();
            html.append("<table class='table table-striped table-bordered display nowrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers'>");
            html.append("<thead>");

            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT id,formAnswers_time,formAnswers_departmentId,hmis_formanswerset_sum,hmis_formanswerset_avg,hfs.formAnswers_date,formAnswers_userName FROM hmis_formanswerset hfs  WHERE formAnswers_formId= " + formAnswers_formId
                    + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
            ));
            html.append("<tr>");
//            html.append("<th>" + "ردیف" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "عنوان سوال" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "وزن" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "حیطه" + "</th>");// ستون اول عنوان سوال است
            for (int i = 0; i < formAnswerSetRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
                html.append("<th>"
                        + "<br/>" + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
                        + "<br/>" + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
                        + "<br/>" + formAnswerSetRows.get(i).get(FormAnswerSet._userName)
                        + "<br/>" + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
                        + "<br>کد:" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
                        + "</th>");
                sumTd += "<td>مجموع :" + formAnswerSetRows.get(i).get(FormAnswerSet._sum) + "<br/> میانگین" + formAnswerSetRows.get(i).get(FormAnswerSet._avg) + "</td>";
            }
            html.append("</tr>");
            html.append("</thead>");
            html.append("<tbody>");
            // به تعداد سوالات هر فرم در حلقه پاسخ همه ی کاربران به آن سوال را می آوریم
            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
                    + "SELECT * FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + " ORDER BY ABS(formQuestions_preority) ASC"));
            for (int i = 0; i < formQuestionRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
                html.append("<tr>");
//                html.append("<td>" + (i + 1));
//                html.append("</td>");
                html.append("<td style='text-align:right;'  onclick='hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title) + "</td>");
                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._weight) + "</td>");
                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._range) + "</td>");
                if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("radio") || formQuestionRows.get(i).get(FormQuestions._answersType).equals("select_option")) {
                    // پاسخ همه ی کاربران به سوال
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT hmis_formanswerset.id,formQuestionOptions_value,formQuestionOptions_lable,formanswers_answerSet_id FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
                            + " AND hmis_formanswerset.id "
                            + " GROUP BY hmis_formanswerset.id "
                            + " ORDER BY hmis_formanswerset.id ASC;" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                    ));
                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>");
                                if (userAnsewrRows.get(j).get(FormQuestionOptions._lable).equals(userAnsewrRows.get(j).get(FormQuestionOptions._value))) {
                                } else {
                                    html.append(userAnsewrRows.get(j).get(FormQuestionOptions._value));
                                }

                                html.append("</td>");
                            } else {
                                html.append("<td></td>");
                            }
                        }
                    } else {
                        int k = 0;
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {//                               
                                if (k <= userAnsewrRows.size()) {
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get(FormQuestionOptions._lable).equals("")) {
                                            html.append("<td>(" + userAnsewrRows.get(k).get(FormQuestionOptions._lable) + ")<br/>");
                                            if (userAnsewrRows.get(k).get(FormQuestionOptions._lable).equals(userAnsewrRows.get(k).get(FormQuestionOptions._value))) {

                                            } else {
                                                html.append(userAnsewrRows.get(k).get(FormQuestionOptions._value));
                                            }
                                            html.append("</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
                                    }
                                }
                            }
                        }
                    }
                } else if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("checkbox")) {
                    // @ToDo چک شود که برای چک باکس هاس استاندارد درست عمل می کند
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                            + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
                            + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                            + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
                            + " AND hmis_formanswerset.id"
                            + " GROUP BY formanswers_answerSet_id ORDER BY hmis_formanswerset.id ASC;"
                    ));

                    /**
                     * حل مشکل خالی بود و وجو نداشتن جواب در فرم های تکمیل شده
                     */
                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>");
                                if (userAnsewrRows.get(j).get("chechedlables").equals(userAnsewrRows.get(j).get("chechedValues"))) {
                                } else {
                                    html.append(userAnsewrRows.get(j).get("chechedValues"));
                                }

                                html.append("</td>");
                            } else {
                                html.append("<td>()</td>");
                            }
                        }
                    } else {
                        int k = 0;
//                        ++++++++++++++++++++++++++
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {
                                if (k <= userAnsewrRows.size()) {
                                    System.out.println("j=" + j);
                                    System.out.println("k=" + k);
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get("chechedlables").equals("")) {
                                            html.append("<td>(" + userAnsewrRows.get(k).get("chechedlables") + ")<br/>");
                                            if (userAnsewrRows.get(k).get("chechedlables").equals(userAnsewrRows.get(k).get("chechedValues"))) {
                                            } else {
                                                html.append(userAnsewrRows.get(k).get("chechedValues"));
                                            }
                                            html.append("</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>");
                                    }
                                }
                            }

                        }
                    }

                } else {
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT formanswers_answer,formanswers_answerSet_id FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
                    //                            + " AND hmis_formanswerset.id "       
                    //                            + " GROUP BY hmis_formanswerset.id "
                    //                            + "ORDER BY hmis_formanswerset.id ASC;"
                    // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                    ));

                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
                            } else {
                                html.append("<td>()</td>");
                            }
                        }
                    } else {
                        int k = 0;
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {//                               
                                if (k <= userAnsewrRows.size()) {
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get(FormAnswers._answer).equals("")) {
                                            html.append("<td style='text-align:right;'>" + userAnsewrRows.get(k).get(FormAnswers._answer) + "</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
                                    }
                                }
                            }

                        }
                    }

                }
                html.append("</tr>");
            }
            html.append("<tr>");
            html.append("<td>-</td>");
            html.append("<td>-</td>");
            html.append("<td>-</td>");
            html.append(sumTd);
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("<a  onclick='transposeTbl();this.onclick=null;'><h1><b style='color:red;cursor:pointer;font-size:2rem'>برگرداندن سطر و ستون</b></h1></a>");
            html.append("<br/>"
                    + " <a style='cursor:pointer'  onclick='outPut();'><b>گرفتن خروجی</b></a>");
            String panel = jjTools.getParameter(request, "panel");
            String script = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * دومین خروجی اکسل با فیلتر تاریخ
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String showTableOfFormAnswers2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String formAnswers_formId = jjTools.getParameter(request, "id");
            String where = "";
            String startDate = jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", "");
            String endDate = jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", "");
            if (!startDate.equals("")) {
                where += " AND " + FormAnswerSet._date + ">=" + startDate;
            }
            if (!endDate.equals("")) {
                where += " AND " + FormAnswerSet._date + "<=" + endDate;
            }
            String sumTd = "";
            StringBuilder html = new StringBuilder();
            html.append("<table class='table table-striped table-bordered display nowrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers2'>");
            html.append("<thead>");

            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT id,formAnswers_time,formAnswers_departmentId,hmis_formanswerset_sum,hmis_formanswerset_avg,hfs.formAnswers_date,formAnswers_userName FROM hmis_formanswerset hfs  WHERE formAnswers_formId= " + formAnswers_formId
                    + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "' " + where // برای فرم های ثبت نهایی شده
            ));
            html.append("<tr>");
//            html.append("<th>" + "ردیف" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "عنوان سوال" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "وزن" + "</th>");// ستون اول عنوان سوال است
            html.append("<th>" + "حیطه" + "</th>");// ستون اول عنوان سوال است   
            for (int i = 0; i < formAnswerSetRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
                html.append("<th>"
                        + "<br/>" + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
                        + "<br/>" + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
                        + "<br/>" + formAnswerSetRows.get(i).get(FormAnswerSet._userName)
                        + "<br/>" + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
                        + "<br>کد:" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
                        + "</th>");
                sumTd += "<td>مجموع :" + formAnswerSetRows.get(i).get(FormAnswerSet._sum) + "<br/> میانگین" + formAnswerSetRows.get(i).get(FormAnswerSet._avg) + "</td>";
            }
            html.append("</tr>");
            html.append("</thead>");
            html.append("<tbody>");
            // به تعداد سوالات هر فرم در حلقه پاسخ همه ی کاربران به آن سوال را می آوریم
            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
                    + "SELECT * FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + " ORDER BY ABS(formQuestions_preority) ASC"));
            for (int i = 0; i < formQuestionRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
                html.append("<tr>");
//                html.append("<td>" + (i + 1));
//                html.append("</td>");
                html.append("<td style='text-align:right;'  onclick='hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title) + "</td>");
                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._weight) + "</td>");
                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._range) + "</td>");
                if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("radio") || formQuestionRows.get(i).get(FormQuestions._answersType).equals("select_option")) {
                    // پاسخ همه ی کاربران به سوال
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT hmis_formanswerset.id,formQuestionOptions_value,formQuestionOptions_lable,formanswers_answerSet_id FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "' " + where + " " // برای فرم های ثبت نهایی شده
                            + " AND hmis_formanswerset.id "
                            + " GROUP BY hmis_formanswerset.id "
                            + " ORDER BY hmis_formanswerset.id ASC;" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                    ));
                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>");
                                if (userAnsewrRows.get(j).get(FormQuestionOptions._lable).equals(userAnsewrRows.get(j).get(FormQuestionOptions._value))) {
                                } else {
                                    html.append(userAnsewrRows.get(j).get(FormQuestionOptions._value));
                                }

                                html.append("</td>");
                            } else {
                                html.append("<td></td>");
                            }
                        }
                    } else {
                        int k = 0;
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {//                               
                                if (k <= userAnsewrRows.size()) {
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get(FormQuestionOptions._lable).equals("")) {
                                            html.append("<td>(" + userAnsewrRows.get(k).get(FormQuestionOptions._lable) + ")<br/>");
                                            if (userAnsewrRows.get(k).get(FormQuestionOptions._lable).equals(userAnsewrRows.get(k).get(FormQuestionOptions._value))) {

                                            } else {
                                                html.append(userAnsewrRows.get(k).get(FormQuestionOptions._value));
                                            }
                                            html.append("</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
                                    }
                                }
                            }
                        }
                    }
                } else if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("checkbox")) {
                    // @ToDo چک شود که برای چک باکس هاس استاندارد درست عمل می کند
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                            + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
                            + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                            + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "' " + where + " " // برای فرم های ثبت نهایی شده
                            + " AND hmis_formanswerset.id"
                            + " GROUP BY formanswers_answerSet_id ORDER BY hmis_formanswerset.id ASC;"
                    ));

                    /**
                     * حل مشکل خالی بود و وجو نداشتن جواب در فرم های تکمیل شده
                     */
                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>");
                                if (userAnsewrRows.get(j).get("chechedlables").equals(userAnsewrRows.get(j).get("chechedValues"))) {
                                } else {
                                    html.append(userAnsewrRows.get(j).get("chechedValues"));
                                }

                                html.append("</td>");
                            } else {
                                html.append("<td>()</td>");
                            }
                        }
                    } else {
                        int k = 0;
//                        ++++++++++++++++++++++++++
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {
                                if (k <= userAnsewrRows.size()) {
                                    System.out.println("j=" + j);
                                    System.out.println("k=" + k);
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get("chechedlables").equals("")) {
                                            html.append("<td>(" + userAnsewrRows.get(k).get("chechedlables") + ")<br/>");
                                            if (userAnsewrRows.get(k).get("chechedlables").equals(userAnsewrRows.get(k).get("chechedValues"))) {
                                            } else {
                                                html.append(userAnsewrRows.get(k).get("chechedValues"));
                                            }
                                            html.append("</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>");
                                    }
                                }
                            }

                        }
                    }

                } else {
                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                            "SELECT formanswers_answer,formanswers_answerSet_id FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "' " + where + " " // برای فرم های ثبت نهایی شده
                    //                            + " AND hmis_formanswerset.id "       
                    //                            + " GROUP BY hmis_formanswerset.id "
                    //                            + "ORDER BY hmis_formanswerset.id ASC;"
                    // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                    ));

                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
                        for (int j = 0; j < userAnsewrRows.size(); j++) {
                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
                                html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
                            } else {
                                html.append("<td>()</td>");
                            }
                        }
                    } else {
                        int k = 0;
                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
                            if (j <= formAnswerSetRows.size()) {//                               
                                if (k <= userAnsewrRows.size()) {
                                    if (formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())) {
                                        if (!userAnsewrRows.get(k).get(FormAnswers._answer).equals("")) {
                                            html.append("<td style='text-align:right;'>" + userAnsewrRows.get(k).get(FormAnswers._answer) + "</td>");
                                            k++;
                                        } else {
                                            k++;
                                            html.append("<td>(NoAnswer)</td>");
                                        }
                                    } else {
                                        html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
                                    }
                                }
                            }

                        }
                    }

                }
                html.append("</tr>");
            }
            html.append("<tr>");
            html.append("<td>-</td>");
            html.append("<td>-</td>");
            html.append("<td>-</td>");
            html.append(sumTd);
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("<a  onclick='transposeTbl2();this.onclick=null;'><h1><b style='color:red;cursor:pointer;font-size:2rem'>برگرداندن سطر و ستون</b></h1></a>");
            html.append("<br/>"
                    + " <a style='cursor:pointer' onclick='outPut2();'><b>گرفتن خروجی</b></a>");
            String panel = jjTools.getParameter(request, "panel");
            String script = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String showTableOfFormAnswers3(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();

            String formId = jjTools.getParameter(request, "id");
            if (formId.equals("0")) {
                return "";
            }
            String startDate = jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", "");
            String endDate = jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", "");
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
            //انتخاب همه ی پاسخ های داده شده به فرم
            String where = "";
            if (!startDate.equals("")) {
                where += " AND " + FormAnswerSet._date + ">=" + startDate;
            }
            if (!endDate.equals("")) {
                where += " AND " + FormAnswerSet._date + "<=" + endDate;
            }

            String formAnswers_formId = formRow.get(0).get(Forms._id).toString();
            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
                    + " SELECT hmis_formquestions.id,hmis_formquestions.formQuestions_title FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + "  ORDER BY ABS(formQuestions_preority) ASC "));

            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
                    "SELECT "
                    + "hmis_formanswerset.id,"
                    + "formAnswers_departmentId,"
                    + "formAnswers_time,"
                    + "formAnswers_formId,"
                    + "hmis_formanswerset.hmis_formanswerset_sum,"
                    + "hmis_formanswerset.formAnswers_date,"
                    + " formAnswers_userName "
                    + " FROM hmis_formanswerset "
                    + " WHERE formAnswers_formId= " + formAnswers_formId + " " + where
                    + " " // برای فرم های ثبت نهایی شده  
            ));
            html.append(" <table class='table table-striped table-bordered display wrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers'>\n"
                    + "                                    <thead>\n"
                    + "                                        <tr>\n"
                    + "                                            <th style='text-align:right;'  onclick='hightLightTableRow($(this));'>اطلاعات  تکمیل  </th>");
            for (int i = 0; i < formQuestionRows.size(); i++) {
                html.append("<th  style ='text-align:right;' onclick = 'hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title).toString() + "</th>");
            }

            html.append("    </tr>\n"
                    + "                                    </thead>\n"
                    + "                                    <tbody>");
            for (int i = 0; i < formAnswerSetRows.size(); i++) {

                html.append(" <tr>   \n"
                        + "                                            <td>\n"
                        + "                                               " + formAnswerSetRows.get(i).get(FormAnswerSet._userName) + " "
                        + "                                                <br/>\n"
                        + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
                        + "                                                <br/>\n"
                        + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
                        + "                                                <br/>نام بخش:\n"
                        + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
                        + "                                                <br/>مجموع:\n"
                        + formAnswerSetRows.get(i).get(FormAnswerSet._sum).toString()
                        + "                                                <br/>\n"
                        + "                                                کد:" + formAnswerSetRows.get(i).get(FormAnswerSet._id).toString()
                        + "                                            </td>");
                for (int k = 0; k < formQuestionRows.size(); k++) {// هر سوال در ستون اول هر ردیف می آید
                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "hmis_formquestions.id,hmis_formquestions.formQuestions_answersType", FormQuestions._id + "=" + formQuestionRows.get(k).get(FormQuestions._id)));
                    if (questionsRow.get(0).get(FormQuestions._answersType).equals("radio") || questionsRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                        // پاسخ همه ی کاربران به سوال
                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT "
                                + "formQuestionOptions_lable,"
                                + "hmis_formquestions.id,formanswers_answerSet_id "
                                + " FROM hmis_formanswers "
                                + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
                                + " WHERE "
                                + " hmis_formanswers.formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
                                + " ORDER BY ABS(formQuestions_preority) ASC"
                                + "" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                        ));
                        if (!userAnsewrRows.isEmpty()) {
                            for (int j = 0; j < userAnsewrRows.size(); j++) {
                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {//زمانی که چنین سوالی وجود داشت 

                                    html.append("          <td>"
                                            + userAnsewrRows.get(j).get(FormQuestionOptions._lable)
                                            + "                                            </td>\n");
                                } else {
                                    html.append("  <td>-</td>");
                                }
                            }
                        } else {
                            html.append("  <td>-</td>");
                        }
                    } else if (questionsRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT "
                                + " formQuestionOptions_lable"
                                + ",hmis_formquestions.id,formanswers_answerSet_id"
                                + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
                                + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
                                + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
                                // + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
                                + " WHERE "
                                + " formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
                                + " GROUP BY formanswers_questionId "
                                + " ORDER BY ABS(formQuestions_preority) ASC;"
                        ));
                        if (!userAnsewrRows.isEmpty()) {
                            for (int j = 0; j < userAnsewrRows.size(); j++) {
                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {
                                    html.append("<td>" + userAnsewrRows.get(j).get("chechedlables") + "</td>");
                                } else {
                                    html.append("  <td>-</td>  ");
                                }
                            }
                        } else {
                            html.append(" <td>-</td>");
                        }
                    } else {
                        List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT formanswers_answer,formanswers_answerSet_id,hmis_formquestions.id FROM hmis_formanswers "
                                //                                                        + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
                                + " LEFT JOIN hmis_formquestions on formanswers_questionId = hmis_formquestions.id "
                                + " WHERE "
                                //                                                        + " formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
                                + "  formanswers_answerSet_id =" + formAnswerSetRows.get(i).get(FormAnswerSet._id)
                                + " AND hmis_formquestions.id=" + questionsRow.get(0).get(FormQuestions._id)
                                + " ORDER BY ABS(formQuestions_preority) ASC"
                        // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
                        ));
                        if (!userAnsewrRows.isEmpty()) {
                            for (int j = 0; j < userAnsewrRows.size(); j++) {
                                if (jjNumber.isDigit(userAnsewrRows.get(j).get(FormQuestions._id).toString())) {
                                    html.append(" <td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
                                } else {
                                    html.append("<td>-</td>");
                                }
                            }
                        } else {
                            html.append("<td>-</td>");
                        }
                    }
                }
                html.append(" </tr>");
            }
            html.append(" </tbody></table>");

            String panel = jjTools.getParameter(request, "panel");
            String script = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    //    public static String showTableOfFormAnswers(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
    //        try {
    //            String formAnswers_formId = jjTools.getParameter(request, "id");
    ////            List<Map<String, Object>> formRowAnswers = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formanswerset hfs left join hmis_formanswers hf on hf.formanswers_answerSet_id=hfs.id where formAnswers_formId=" + formAnswers_formId + ";"));
    //            String sumTd = "";
    //            StringBuilder html = new StringBuilder();
    //            html.append("<table class='table table-striped table-bordered display nowrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers'>");
    //            html.append("<thead>");
    //
    //            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
    //            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
    //            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                    "SELECT id,formAnswers_time,formAnswers_departmentId,hmis_formanswerset_sum,hmis_formanswerset_avg,hfs.formAnswers_date,formAnswers_userName FROM hmis_formanswerset hfs  WHERE formAnswers_formId= " + formAnswers_formId
    //                    + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //            ));
    //            System.out.println(">>>>>>>>formAnswerSetRows:" +  formAnswerSetRows.size());
    //            html.append("<tr>");
    //            html.append("<th>" + "ردیف" + "</th>");// ستون اول عنوان سوال است
    //            html.append("<th>" + "عنوان سوال" + "</th>");// ستون اول عنوان سوال است
    //            html.append("<th>" + "وزن" + "</th>");// ستون اول عنوان سوال است
    //            for (int i = 0; i < formAnswerSetRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
    //                System.out.println(">>>>>>>>formAnswerSetRows:" + (i + 1));
    //                html.append("<th>"                         
    //                        + (i + 1) + "<br/>" + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
    //                        + "<br/>" + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
    //                        + "<br/>" + formAnswerSetRows.get(i).get(FormAnswerSet._userName)
    //                        + "<br/>" + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
    //                        + "</th>");
    //                sumTd += "<td>مجموع :" + formAnswerSetRows.get(i).get(FormAnswerSet._sum) + "<br/> میانگین" + formAnswerSetRows.get(i).get(FormAnswerSet._avg) + "</td>";
    //            }
    //            html.append("</tr>");
    //            html.append("</thead>");
    //            html.append("<tbody>");
    //            // به تعداد سوالات هر فرم در حلقه پاسخ همه ی کاربران به آن سوال را می آوریم
    //            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
    //                    + "SELECT * FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + " ORDER BY ABS(formQuestions_preority) ASC"));
    //            System.out.println(">>>>>>>>formQuestionRows:" +  formQuestionRows.size());
    //            for (int i = 0; i < formQuestionRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
    //                html.append("<tr>");
    //                System.out.println("<<<<<<<<<<<formQuestionRows="+(i + 1));
    //                html.append("<td>" + (i + 1));
    //                html.append("</td>");
    //                html.append("<td style='text-align:right;'  onclick='hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title) + "</td>");
    //                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._weight) + "</td>");
    //                if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("radio") || formQuestionRows.get(i).get(FormQuestions._answersType).equals("select_option")) {
    //                    // پاسخ همه ی کاربران به سوال
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT hmis_formanswerset.id,formQuestionOptions_value,formQuestionOptions_lable FROM hmis_formanswers "
    //                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
    //                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //                            + " AND hmis_formanswerset.id "
    //                            + " GROUP BY hmis_formanswerset.id "
    //                            + " ORDER BY hmis_formanswerset.id ASC;" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
    //                    ));
    //                     System.out.println(">>>>>>>>userAnsewrRowsRadioAndOption:" +  userAnsewrRows.size());
    //                    
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>"
    //                                        + userAnsewrRows.get(j).get(FormQuestionOptions._value) + "</td>");
    //                            } else {
    //                                html.append("<td>(-)</td>");
    //                            }
    //                        }
    //                    } else {
    ////                        ++++++++++++++++++++++++++
    //                                int k=0;
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {                            
    //                            if(j <= formAnswerSetRows.size()){//                               
    //                                          if( k <= userAnsewrRows.size()){                                        
    //                                 if(formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())){  
    //                                     if(!userAnsewrRows.get(k).get(FormQuestionOptions._lable).equals("")){
    //                                    html.append("<td>(" + userAnsewrRows.get(k).get(FormQuestionOptions._lable) + ")<br/>"
    //                                        + userAnsewrRows.get(k).get(FormQuestionOptions._value) + "</td>");
    //                                      k++;                                     
    //                                      }else{
    //                                         k++;
    //                                          html.append("<td>(NoAnswer)</td>");
    //                                     }                                    
    //                                 }else{                                     
    //                                          html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
    //                                 }                                                                
    //                                }                              
    //                            }
    //                       
    //                        }
    ////                              ++++++++++++++++++++++++++
    ////                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
    ////                            if (j < userAnsewrRows.size()) {  
    ////                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    ////                                    html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>"
    ////                                            + userAnsewrRows.get(j).get(FormQuestionOptions._value) + "</td>");
    ////                                } else {
    ////                                    html.append("<td>(-)</td>");
    ////                                }
    ////                            } else {
    ////                                html.append("<td>(-)</td>");
    ////                            }
    ////                        }
    //                    }
    //                } else if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("checkbox")) {
    //                    // @ToDo چک شود که برای چک باکس هاس استاندارد درست عمل می کند
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
    //                            + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
    //                            + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
    //                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
    //                            + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND hmis_formanswerset.id"
    //                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //                            + " GROUP BY formanswers_answerSet_id ORDER BY hmis_formanswerset.id ASC;"
    //                    ));
    //
    //                     System.out.println(">>>>>>>>userAnsewrRowsCheckbox:" +  userAnsewrRows.size());
    //                    /**
    //                     * حل مشکل خالی بود و وجو نداشتن جواب در فرم های تکمیل شده
    //                     */
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>"
    //                                        + userAnsewrRows.get(j).get("chechedValues") + "</td>");
    //                            } else {
    //                                html.append("<td>(-)</td>");
    //                            }
    //                        }
    //                    } else {
    //                        int k=0;
    ////                        ++++++++++++++++++++++++++
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
    //                            
    //                            if(j <= formAnswerSetRows.size()){
    ////                               
    //                                          if( k <= userAnsewrRows.size()){  
    //                                       System.out.println("j="+j);  
    //                                       System.out.println("k="+k);  
    //                                 if(formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())){  
    //                                     if(!userAnsewrRows.get(k).get("chechedlables").equals("")){
    //                                      html.append("<td>(" + userAnsewrRows.get(k).get("chechedlables") + ")<br/>"
    //                                            + userAnsewrRows.get(k).get("chechedValues") + "</td>");
    //                                      k++;
    //                                     
    //                                      }else{
    //                                         k++;
    //                                          html.append("<td>(NoAnswer)</td>");
    //                                     }                                    
    //                                 }else{
    //                                     
    //                                          html.append("<td>(-)</td>");     
    //                                 }                                                                
    //                                }                              
    //                            }
    //                       
    //                        }
    ////                        ++++++++++++++++++++++++++        
    ////                        for (int j = 0; j < formAnswerSetRows.size(); j++) {//
    ////                            if (j < userAnsewrRows.size()) {//اگر سایز جواب ها  کوچکتر از سایز  تکمیل شده ها باشد  
    ////                                System.out.println("<<<<<<<<<<<afsaneh**="+jjNumber.isDigit(userAnsewrRows.get(j).get(FormAnswers._answerSet_id).toString()));
    ////                                System.out.println("<<<<<<<<<<<kiani**="+jjNumber.isDigit(formAnswerSetRows.get(i).get(FormAnswerSet._id).toString()));
    ////                                if(userAnsewrRows.get(j).get(FormAnswers._answerSet_id)==(formAnswerSetRows.get(j).get(FormAnswerSet._id))){
    ////                                  System.out.println("<<<<<<<<<<<id**="+formQuestionRows.get(i).get(FormQuestions._id).toString());
    ////                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    ////                                    html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>"
    ////                                            + userAnsewrRows.get(j).get("chechedValues") + "</td>");
    //////                             
    ////                                } else {
    ////                                    html.append("<td>(-)</td>");
    ////                                }
    ////                                }else{
    //////                                  
    ////                                     html.append("<td>(-)</td>");
    ////                                }
    ////                            } else {
    ////                                html.append("<td>(-)</td>");  //در این حالت اگر جوابی در 
    ////                                //answer 
    ////                                // ها وجود نداشت ستون خالی ایجاد می کند
    ////                            }
    ////                        }
    //////                        ++++++++++++++++++++++++++++++en
    //                    }
    //
    //                } else {
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT formanswers_answer FROM hmis_formanswers "
    //                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //                    //                            + " AND hmis_formanswerset.id "       
    //                    //                            + " GROUP BY hmis_formanswerset.id "
    //                    //                            + "ORDER BY hmis_formanswerset.id ASC;"
    //                    // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
    //                    ));
    //                     System.out.println(">>>>>>>>userAnsewrRows:" +  userAnsewrRows.size());
    //
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
    //                            } else {
    //                                html.append("<td>(-)</td>");
    //                            }
    //                        }
    //                    } else {
    ////                                    ++++++++++++++++++++++++++
    //                                int k=0;
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {                            
    //                            if(j <= formAnswerSetRows.size()){//                               
    //                                          if( k <= userAnsewrRows.size()){                                        
    //                                 if(formAnswerSetRows.get(j).get(FormAnswerSet._id).toString().equals(userAnsewrRows.get(k).get(FormAnswers._answerSet_id).toString())){  
    //                                     if(!userAnsewrRows.get(k).get(FormAnswers._answer).equals("")){
    //                                   html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
    //                                      k++;                                     
    //                                      }else{
    //                                         k++;
    //                                          html.append("<td>(NoAnswer)</td>");
    //                                     }                                    
    //                                 }else{                                     
    //                                          html.append("<td>(-)</td>"); //برای زمانی است که سوالی را بعدا اضافه کرده پس برای قبللی ها که تکمیل کردند جوابی وجود نداشته    
    //                                 }                                                                
    //                                }                              
    //                            }
    //                       
    //                        }
    ////                              ++++++++++++++++++++++++++
    ////                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
    ////                            if (j < userAnsewrRows.size()) {
    ////                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    ////                                    html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
    ////                                } else {
    ////                                    html.append("<td>(-)</td>");
    ////                                }
    ////                            } else {
    ////                                html.append("<td>(-)</td>");
    ////                            }
    ////                        }
    //                    }
    //
    //                }
    //                html.append("</tr>");
    //            }
    //            html.append("<tr>");
    //            html.append("<td>-</td>");
    //            html.append("<td>-</td>");
    //            html.append("<td>-</td>");
    //            html.append(sumTd);
    //            html.append("</tr>");
    //            html.append("</tbody></table>");
    //                  html.append("<a  onclick='transposeTbl();this.onclick=null;'><h1><b style='color:red;cursor:pointer'>برگرداندن سطر و ستون</b></h1></a>");
    //            html.append("<br/>"
    //                    + " <a onclick='outPut();'>گرفتن خروجی</a>");
    //            String panel = jjTools.getParameter(request, "panel");
    //            String script = Js.setHtml("#" + panel, html.toString());
    //            Server.outPrinter(request, response, script);
    //            return script;
    //        } catch (Exception ex) {
    //            Server.outPrinter(request, response, Server.ErrorHandler(ex));
    //            return "";
    //        }
    //    }
    //    

    //    public static String showTableOfFormAnswers2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
    //        try {
    //            String formAnswers_formId = jjTools.getParameter(request, "id");
    ////            List<Map<String, Object>> formRowAnswers = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formanswerset hfs left join hmis_formanswers hf on hf.formanswers_answerSet_id=hfs.id where formAnswers_formId=" + formAnswers_formId + ";"));
    //            String sumTd = "";
    //            StringBuilder html = new StringBuilder();
    //            html.append("<table class='table table-striped table-bordered display nowrap'  style='direction: rtl;width:100%' id='showTableOfFormAnswers2'>");
    //            html.append("<thead>");
    //
    //            // سلکت برای انتخاب ستون ها  که در ردیف اول جدول در هدر می ایند
    //            List<Map<String, Object>> formAnswerSetRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                    "SELECT id,formAnswers_time,formAnswers_departmentId,hmis_formanswerset_sum,hmis_formanswerset_avg,hfs.formAnswers_date,formAnswers_userName FROM hmis_formanswerset hfs  WHERE formAnswers_formId= " + formAnswers_formId
    //                    + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //            ));
    //            html.append("<tr>");
    //            html.append("<th>" + "ردیف" + "</th>");// ستون اول عنوان سوال است
    //            html.append("<th>" + "عنوان سوال" + "</th>");// ستون اول عنوان سوال است
    //            html.append("<th>" + "وزن" + "</th>");// ستون اول عنوان سوال است
    //            for (int i = 0; i < formAnswerSetRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
    //                html.append("<th>"
    //                        + (i + 1) + "<br/>" + jjCalendar_IR.getViewFormat(formAnswerSetRows.get(i).get(FormAnswerSet._date))
    //                        + "<br/>" + jjTime.getTime5lenth(formAnswerSetRows.get(i).get(FormAnswerSet._time).toString())
    //                        + "<br/>" + formAnswerSetRows.get(i).get(FormAnswerSet._userName)
    //                        + "<br/>" + Department.getDepartmentName(formAnswerSetRows.get(i).get(FormAnswerSet._departmentId).toString(), db)
    //                        + "</th>");
    //                sumTd += "<td>مجموع :" + formAnswerSetRows.get(i).get(FormAnswerSet._sum) + "<br/> میانگین" + formAnswerSetRows.get(i).get(FormAnswerSet._avg) + "</td>";
    //            }
    //            html.append("</tr>");
    //            html.append("</thead>");
    //            html.append("<tbody>");
    //            // به تعداد سوالات هر فرم در حلقه پاسخ همه ی کاربران به آن سوال را می آوریم
    //            List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
    //                    + "SELECT * FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + " ORDER BY ABS(formQuestions_preority) ASC"));
    //            for (int i = 0; i < formQuestionRows.size(); i++) {// هر سوال در ستون اول هر ردیف می آید
    //                html.append("<tr>");
    //                html.append("<td>" + (i + 1));
    //                html.append("</td>");
    //                html.append("<td style='text-align:right;'  onclick='hightLightTableRow($(this));'>" + formQuestionRows.get(i).get(FormQuestions._title) + "</td>");
    //                html.append("<td style='text-align:right;'>" + formQuestionRows.get(i).get(FormQuestions._weight) + "</td>");
    //                if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("radio") || formQuestionRows.get(i).get(FormQuestions._answersType).equals("select_option")) {
    //                    // پاسخ همه ی کاربران به سوال
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT hmis_formanswerset.id,formQuestionOptions_value,formQuestionOptions_lable FROM hmis_formanswers "
    //                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
    //                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //                            + " AND hmis_formanswerset.id "
    //                            + " GROUP BY hmis_formanswerset.id "
    //                            + " ORDER BY hmis_formanswerset.id ASC;" // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
    //                    ));
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>"
    //                                        + userAnsewrRows.get(j).get(FormQuestionOptions._value) + "</td>");
    //                            } else {
    //                                html.append("<td></td>");
    //                            }
    //                        }
    //                    } else {
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
    //                            if (j < userAnsewrRows.size()) {
    //                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                    html.append("<td>(" + userAnsewrRows.get(j).get(FormQuestionOptions._lable) + ")<br/>"
    //                                            + userAnsewrRows.get(j).get(FormQuestionOptions._value) + "</td>");
    //                                } else {
    //                                    html.append("<td>()</td>");
    //                                }
    //                            } else {
    //                                html.append("<td></td>");
    //                            }
    //                        }
    //                    }
    //                } else if (formQuestionRows.get(i).get(FormQuestions._answersType).equals("checkbox")) {
    //                    // @ToDo چک شود که برای چک باکس هاس استاندارد درست عمل می کند
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
    //                            + ",GROUP_CONCAT(formQuestionOptions_value SEPARATOR ', ') as chechedValues"
    //                            + ",GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ', ') as chechedlables FROM hmis_formanswers"
    //                            + " LEFT JOIN hmis_formquestionOptions ON formanswers_answer = hmis_formquestionOptions.id"
    //                            + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND hmis_formanswerset.id"
    //                            + " GROUP BY formanswers_answerSet_id ORDER BY hmis_formanswerset.id ASC;"
    //                    ));
    //
    //                    /**
    //                     * حل مشکل خالی بود و وجو نداشتن جواب در فرم های تکمیل شده
    //                     */
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>"
    //                                        + userAnsewrRows.get(j).get("chechedValues") + "</td>");
    //                            } else {
    //                                html.append("<td>()</td>");
    //                            }
    //                        }
    //                    } else {
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {//
    //                            if (j < userAnsewrRows.size()) {//اگر سایز جواب ها  کوچکتر از سایز  تکمیل شده ها باشد  
    //                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                    html.append("<td>(" + userAnsewrRows.get(j).get("chechedlables") + ")<br/>"
    //                                            + userAnsewrRows.get(j).get("chechedValues") + "</td>");
    //                                } else {
    //                                    html.append("<td>()</td>");
    //                                }
    //                            } else {
    //                                html.append("<td></td>");  //در این حالت اگر جوابی در 
    //                                //answer 
    //                                // ها وجود نداشت ستون خالی ایجاد می کند
    //                            }
    //                        }
    //                    }
    //
    //                } else {
    //                    List<Map<String, Object>> userAnsewrRows = jjDatabaseWeb.separateRow(db.otherSelect(
    //                            "SELECT formanswers_answer FROM hmis_formanswers "
    //                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id = hmis_formanswerset.id"
    //                            + " WHERE formanswers_questionId=" + formQuestionRows.get(i).get(FormQuestions._id)
    //                            + " AND formAnswers_status = '" + FormAnswerSet.statues_sabteNahei + "'" // برای فرم های ثبت نهایی شده
    //                    //                            + " AND hmis_formanswerset.id "       
    //                    //                            + " GROUP BY hmis_formanswerset.id "
    //                    //                            + "ORDER BY hmis_formanswerset.id ASC;"
    //                    // برای اینکه انسر ست هایی که پاک شده اند انسر هایش پاک نمی شود
    //                    ));
    //
    //                    if (userAnsewrRows.size() == formAnswerSetRows.size()) {
    //                        for (int j = 0; j < userAnsewrRows.size(); j++) {
    //                            if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
    //                            } else {
    //                                html.append("<td>()</td>");
    //                            }
    //                        }
    //                    } else {
    //                        for (int j = 0; j < formAnswerSetRows.size(); j++) {
    //                            if (j < userAnsewrRows.size()) {
    //                                if (jjNumber.isDigit(formQuestionRows.get(i).get(FormQuestions._id).toString())) {
    //                                    html.append("<td style='text-align:right;'>" + userAnsewrRows.get(j).get(FormAnswers._answer) + "</td>");
    //                                } else {
    //                                    html.append("<td>()</td>");
    //                                }
    //                            } else {
    //                                html.append("<td></td>");
    //                            }
    //                        }
    //                    }
    //
    //                }
    //                html.append("</tr>");
    //            }
    //            html.append("<tr>");
    //            html.append("<td>-</td>");
    //            html.append("<td>-</td>");
    //            html.append("<td>-</td>");
    //            html.append(sumTd);
    //            html.append("</tr>");
    //            html.append("</tbody></table>");
    //            String panel = jjTools.getParameter(request, "panel");
    //            String script = Js.setHtml("#" + panel, html.toString());
    //            script += " $('#showTableOfFormAnswers2').each(function() {\n"
    //                    + "        var $this = $(this);\n"
    //                    + "        var newrows = [];\n"
    //                    + "        $this.find(\"tr\").each(function(){\n"
    //                    + "            var i = 0;\n"
    //                    + "            $(this).find(\"td,th\").each(function(){\n"
    //                    + "                i++;\n"
    //                    + "                if(newrows[i] === undefined) { newrows[i] = $(\"<tr></tr>\"); }\n"
    //                    + "                newrows[i].append($(this));\n"
    //                    + "            });\n"
    //                    + "        });\n"
    //                    + "        $this.find(\"tr\").remove();\n"
    //                    + "        $.each(newrows, function(){\n"
    //                    + "            $this.append(this);\n"
    //                    + "        });\n"
    //                    + "    });";
    //            script += " var $span = $('#showTableOfFormAnswers2');\n"
    //                    + " $span.find('th').wrapInner('<td />').contents().unwrap();";
    //            script += " var t = $('table#showTableOfFormAnswers');\n"
    //                    + " var firstTr = t.find('tr:first').remove();\n"
    //                    + " firstTr.find('td').contents().unwrap().wrap('<th>');\n"
    //                    + " t.prepend($('<thead></thead>').append(firstTr));";
    ////            script += "$('#showTableOfFormAnswers2').dataTable("
    ////                    + "{"
    ////                    + "fixedColumns:   {leftColumns: 2},"
    ////                    + "fixedHeader: true,"
    ////                    + "iDisplayLength:100"
    ////                    + ",bJQueryUI: true"
    ////                    + ",sPaginationType: 'full_numbers'"
    ////                    + ",columnDefs: [    { orderable: false, targets: 0 }  ]"
    ////                    + ",paging: false"
    ////                    + ",dom: 'Bfrtip'"
    ////                    + ",buttons: ['copy', 'csv', 'excel', 'print']"
    ////                    + "});";  
    //            Server.outPrinter(request, response, script);
    //            return script;
    //        } catch (Exception ex) {
    //            Server.outPrinter(request, response, Server.ErrorHandler(ex));
    //            return "";
    //        }
    //    }
    //     String script = Js.setHtml("#" + panel, html.toString());
    //            script += " $('#showTableOfFormAnswers2').each(function() {\n"
    //                    + "        var $this = $(this);\n"
    //                    + "        var newrows = [];\n"
    //                    + "        $this.find(\"tr\").each(function(){\n"
    //                    + "            var i = 0;\n"
    //                    + "            $(this).find(\"td,th\").each(function(){\n"
    //                    + "                i++;\n"
    //                    + "                if(newrows[i] === undefined) { newrows[i] = $(\"<tr></tr>\"); }\n"
    //                    + "                newrows[i].append($(this));\n"
    //                    + "            });\n"
    //                    + "        });\n"
    //                    + "        $this.find(\"tr\").remove();\n"
    //                    + "        $.each(newrows, function(){\n"
    //                    + "            $this.append(this);\n"
    //                    + "        });\n"
    //                    + "    });";
    //            script += " var $span = $('#showTableOfFormAnswers2');\n"
    //                    + " $span.find('th').wrapInner('<td />').contents().unwrap();";
    //            script += " var t = $('table#showTableOfFormAnswers');\n"
    //                    + " var firstTr = t.find('tr:first').remove();\n"
    //                    + " firstTr.find('td').contents().unwrap().wrap('<th>');\n"
    //                    + " t.prepend($('<thead></thead>').append(firstTr));"; 
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            //@ToDo درست کردن کد های درسترسی برای قسمت هایی که کاربر فقط بتواند فرم های خودش را ببیند
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_formAnswerSetJspName, jjTools.getParameter(request, _formAnswerSetJspName));
            map.put(_departments, "," + jjTools.getParameter(request, _departments) + ",");
            map.put(_category_id, jjTools.getParameter(request, _category_id));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_isActiveDepartment, jjTools.getParameter(request, _isActiveDepartment));
            map.put(_isChangeableUserId, jjTools.getParameter(request, _isChangeableUserId));
            map.put(_isRequiredDepartment, jjTools.getParameter(request, _isRequiredDepartment));
            map.put(_isRandomOption, jjTools.getParameter(request, _isRandomOption));
            map.put(_isRandomQuestion, jjTools.getParameter(request, _isRandomQuestion));
            map.put(_showQuestionsOneByOne, jjTools.getParameter(request, _showQuestionsOneByOne));
            map.put(_isDateEditable, jjTools.getParameter(request, _isDateEditable));
            map.put(_icon, jjTools.getParameter(request, _icon));
            if (!Access_User.hasAccess(request, db, rul_changeOwnerForm)) {//دسترسی تغییر مالک فرم
                map.put(_ownerId, jjTools.getSeassionUserId(request));
                if (!jjNumber.isDigit(jjTools.getParameter(request, _ownerRole))) {//اگر در ریکوئست مشخص نکرده بود
                    String userRolesInSession = jjTools.getSeassionUserRole(request);
                    String userRols[] = userRolesInSession.split(",");
                    if (userRols.length == 0) {// اگر نقشی در سیستم نداشت تهی می گذاریم
                        map.put(_ownerRole, "");
                    } else {
                        // اگرچند نقش داشت و  مشخص نکرده بود نقش اولی اش را می گذاریم یا تهی می گذاریم
                        map.put(_ownerRole, userRols[0]);
                    }
                } else {
                    map.put(_ownerRole, jjTools.getParameter(request, _ownerRole));
                }
            } else {
                map.put(_ownerId, jjTools.getParameter(request, _ownerId));
                map.put(_ownerRole, jjTools.getParameter(request, _ownerRole));
            }
            //تعیین نقش ایجاد کننده یا مالک فرم            
            // ممکن است ادمین بخواهد برای سایرین فرم ها را ایجاد کند و باید این امکان را داشته باشد

            map.put(_accessessUsers, "," + jjTools.getParameter(request, _accessessUsers) + ",");
            map.put(_accessessGroupId, "," + jjTools.getParameter(request, _accessessGroupId) + ",");
            map.put(_accessessRoles, "," + jjTools.getParameter(request, _accessessRoles) + ",");
            map.put(_resultAccessUsers, "," + jjTools.getParameter(request, _resultAccessUsers) + ",");
            map.put(_resultAccessRole, "," + jjTools.getParameter(request, _resultAccessRole) + ",");
            map.put(_resultGroupId, "," + jjTools.getParameter(request, _resultGroupId) + ",");
            if ("".equals(jjTools.getParameter(request, _creationDate))) {// اگر تاریخ شروع اعتبار وارد نکرده بود
                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(null, true));
            } else {
                map.put(_creationDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _creationDate), true));
            }
            jjCalendar_IR date = new jjCalendar_IR();
            map.put(_creationTime, jj.jjTime.getTime4lenth(jjTools.getParameter(request, _creationTime)));//ToDo تبدیل به عدد برای قرار گرفتن در دیتا بیس
            map.put(_expireDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _expireDate), false));
            map.put(_expireTime, jj.jjTime.getTime4lenth(jjTools.getParameter(request, _expireTime)));
            map.put(_nextFormId, jjTools.getParameter(request, _nextFormId).equals("") || jjTools.getParameter(request, _nextFormId).equals("null") ? "0" : jjTools.getParameter(request, _nextFormId));
            map.put(_uniqueComplete, jjTools.getParameter(request, _uniqueComplete));
            map.put(_showResultToQuestioner, jjTools.getParameter(request, _showResultToQuestioner));
            map.put(_showAllResultToQuestioner, jjTools.getParameter(request, _showAllResultToQuestioner));
            map.put(_showCometePreAproveForm, jjTools.getParameter(request, _showCometePreAproveForm));
            map.put(_hasAutoWikiInContent, jjTools.getParameter(request, _hasAutoWikiInContent));
            map.put(_css, jjTools.getParameter(request, _css));
            map.put(_javaScript, jjTools.getParameter(request, _javaScript));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            String needToAutoWiki = jjTools.getParameter(request, _hasAutoWikiInContent);
            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _htmlContent), db, false);
                map.put(_htmlContentWithWikiLinks, autoWikeContent);
            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
                map.put(_htmlContentWithWikiLinks, jjTools.getParameter(request, _htmlContent));
//                map.put(_autoWikIsUpdate, jjTools.getParameter(request, _autoWikIsUpdate));
            }
            map.put(_isAutoWiki, jjTools.getParameter(request, _isAutoWiki).equals("") ? "0" : jjTools.getParameter(request, _isAutoWiki));
            if ("1".equals(jjTools.getParameter(request, _isAutoWiki).toString())) {
                resetAllAutoWikies(request, db);
            }
            map.put(_description, jjTools.getParameter(request, _description));
            if (db.update(tableName, map, _id + "=" + id)) {
                DefaultTableModel dtm = db.Select(tableName, _id + "=" + id);
                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
                StringBuilder html = new StringBuilder();
                html.append("<tr id='refreshFormsTr" + row.get(0).get(_id) + "'  class='mousePointer'>");
                html.append("<td class='r'>" + (row.get(0).get(_code).toString().isEmpty() ? row.get(0).get(_id) : row.get(0).get(_code)) + "</td>");
                html.append("<td class='r'>" + row.get(0).get(_title) + "</td>");
                html.append("<td class='c'><i class='p icon ion-ios-gear' onclick='" + Js.jjForms.select(row.get(0).get(_id).toString()) + "'></i></td>");
                html.append("<td class='c'><a href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + row.get(0).get(_id) + "' target='_blank' >"
                        + "<i class='p fa fa-bar-chart'></i></a></td>");
                html.append("</tr>");
                Server.outPrinter(request, response, Js.modal("ویرایش بدرستی انجام شد", "پیام سامانه") + Js.setHtml("refreshFormsTr", html.toString()));
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
            }
            //@ToDo درست کردن کد های درسترسی برای قسمت هایی که کاربر فقط بتواند فرم های خودش را ببیند else {
            String id = jjTools.getParameter(request, _id);
            if (!jjNumber.isDigit(id)) {
                Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
                return "";
            }

//                db.Select(tableName)//در پاسخ ها اگر کسی پاسخ نداده است قابل حذف است@ToDo
            if (db.otherStatement("DELETE t0,t1,t2 FROM hmis_forms as t0 LEFT JOIN hmis_formquestions as t1 on t0.id=t1.formQuestions_formID LEFT JOIN hmis_formquestionoptions as t2 ON formQuestionOptions_question_id = t1.id WHERE t0.id=" + id)) {
                Server.outPrinter(request, response, Js.jjForms.refresh() + Js.modal("همه سوال ها و گزینه ها حذف شدند", "پیام سامانه"));
                return "";
            } else {
                Server.outPrinter(request, response, Js.modal("عدم موفقیت عملیات حذف!!!", "پیام سامانه"));
                return "";
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
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(startDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(endDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));

            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص

                if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "'"
                            //                            + " AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id")+ "' "
                            + formquestionOption_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1
                                + (jjNumber.isDigit(hmis_filter_formquestion_id) ? "" : roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        ));
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                        String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                        data1.add(Double.valueOf(no));
                        color1 += "'#5b93d3',";
                        dataPieArray.add(Integer.valueOf(no));
                        if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                            List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                    "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                    + "WHERE " + FormAnswers._questionId + "=" + questionId
                                    //                                    + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                    + formquestionOption_id
                                    + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                                    + (jjNumber.isDigit(hmis_filter_formquestion_id) ? "" : roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                            ));
                            labels2 += " ' 999 " + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                            no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                            data2.add(Double.valueOf(no));
                            color2 += "'#677489',";
                        }
                    }
                }
            }
            if (dataType.equals("percentage")) {
                Double sum1 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data1.size(); i++) {
                    sum1 += data1.get(i);
                }
                for (int i = 0; i < data1.size(); i++) {
                    data1.set(i, (double) Math.round((data1.get(i) / sum1) * 10000) / 100);
                }
                Double sum2 = 0.0;// تعداد همه پاسخ ها برای نمودار مقایسه ای دایره ای  ومحاسبه ی درصد
                for (int i = 0; i < data2.size(); i++) {
                    sum2 += data2.get(i);
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: ["
                        + "{"
                        + "label: '" + startDate1 + "-" + endDate1 + "',"
                        + "data: " + data1.toString() + ","
                        + "backgroundColor: ["
                        + color1
                        + "]"
                        + "},"
                        + "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + color2
                        + "]"
                        + "},"
                        + "]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + "data: " + data1 + ","
                        + "borderColor: '#324463',"
                        + "label: '" + startDate1 + "-" + endDate1 + " ',"
                        + "borderWidth: 3,"
                        + "fill: false"
                        + "},{"
                        + "data: " + data2 + ","
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "borderColor: '#5B93D3',"
                        + "borderWidth: 3,"
                        + "fill: false"
                        + "      }]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";

            } else if (chartType.equals("pie")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'pie', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: ["
                        + "{"
                        + "label: '" + startDate1 + "-" + endDate1 + "',"
                        + "data: " + data1.toString() + ","
                        + "backgroundColor: ["
                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
                        + "]"
                        + "},"
                        + "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
                        + "]"
                        + "},"
                        + "]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        //                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter:Math.round},"// برای لیبل های دارای بکگراند سبز
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار دایره ای سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
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
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title, Forms._id + "=" + questionRow.get(0).get(FormQuestions._formID)));
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :");

            request.setAttribute("script", script);
            request.setAttribute("data", data1);
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع فیلتر سوال حذف شده و فقط گزینه را میگیرید
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String analysFromByQuestion2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            String id = jjTools.getParameter(request, _id);

            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(startDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(endDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));

            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص

                if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_departmentId"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + jjTools.getParameter(request, "hmis_filter_formquestion_id") + "'"
                            //                                                        + " AND formanswers_answer='" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id")+ "' "
                            + formquestionOption_id + hmis_filter_department_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id + hmis_filter_department_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1
                        //                                + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        //فیلتر نقش برداشته شد
                        ));
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                        String no = numbersOfThisOption1.get(0).get("no").toString();// صفر یا بیشتر
                        data1.add(Double.valueOf(no));
                        color1 += "'#5b93d3',";
                        dataPieArray.add(Integer.valueOf(no));
                        if (!dateCondition2.isEmpty()) {// اگر تاریخ مقایسه را هم وارد کرده بود
                            List<Map<String, Object>> numbersOfThisOption2 = jjDatabaseWeb.separateRow(db.otherSelect(
                                    "SELECT COUNT(*) AS no FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                    + "WHERE " + FormAnswers._questionId + "=" + questionId
                                    //                                    + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                    + formquestionOption_id + hmis_filter_department_id
                                    + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                            //                           //برداشته شد         + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                            ));
                            labels2 += " ' 999 " + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
                            no = numbersOfThisOption2.get(0).get("no").toString();// صفر یا بیشتر
                            data2.add(Double.valueOf(no));
                            color2 += "'#677489',";
                        }
                    }
                }
            }
            double avg = 0;
            double sum = 100;
            avg = sum / data1.size();
            if (dataType.equals("percentage")) {
                Double sum1 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data1.size(); i++) {
                    sum1 += data1.get(i);
                }
                for (int i = 0; i < data1.size(); i++) {
                    data1.set(i, (double) Math.round((data1.get(i) / sum1) * 10000) / 100);
                }
                Double sum2 = 0.0;// تعداد همه پاسخ ها برای نمودار مقایسه ای دایره ای  ومحاسبه ی درصد
                for (int i = 0; i < data2.size(); i++) {
                    sum2 += data2.get(i);
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: ["
                        + "{"
                        + "label: '" + startDate1 + "-" + endDate1 + "',"
                        + "data: " + data1.toString() + ","
                        + "backgroundColor: ["
                        + color1
                        + "]"
                        + "},"
                        + "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + color2
                        + "]"
                        + "},"
                        + "]"
                        + "},"
                        + "options: {"
                        + "    lineTension:0,"
                        + "    annotation: {"
                        + "    annotations: ["
                        + "{"
                        + "   type: 'line',"
                        + "   mode: 'horizontal',"
                        + "   scaleID: 'y-axis-0',"
                        + "   value: " + avg + ","
                        + "   borderColor: 'red',"
                        + "   borderWidth: 1,"
                        + "   label: {"
                        + "     backgroundColor:'gray',"
                        + "     enabled: true,"
                        + "     content: '" + avg + " " + "میانگین " + "' "
                        + "   }"
                        + " }"
                        + "]"
                        + "},"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + "data: " + data1 + ","
                        + "borderColor: '#324463',"
                        + "label: '" + startDate1 + "-" + endDate1 + " ',"
                        + "borderWidth: 3,"
                        + "fill: false"
                        + "},{"
                        + "data: " + data2 + ","
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "borderColor: '#5B93D3',"
                        + "borderWidth: 3,"
                        + "fill: false"
                        + "      }]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";

            } else if (chartType.equals("pie")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'pie', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: ["
                        + "{"
                        + "label: '" + startDate1 + "-" + endDate1 + "',"
                        + "data: " + data1.toString() + ","
                        + "backgroundColor: ["
                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
                        + "]"
                        + "},"
                        + "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
                        + "]"
                        + "},"
                        + "]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        //                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter:Math.round},"// برای لیبل های دارای بکگراند سبز
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار دایره ای سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
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
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, _id + "=" + id));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :" + formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));

            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * آنالیز گزینه های یک سوال ماهیانه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String analysFromByQuestionWithPeriodOneMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            String dateCondition2 = "";
//            
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data11 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data12 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data13 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data14 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data15 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data16 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data17 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data18 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data19 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data20 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data21 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data22 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_departmentId"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + questionId + "'"
                            + formquestionOption_id + hmis_filter_department_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS val,formAnswers_date _date FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id + hmis_filter_department_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + " group by formAnswers_date ASC  "
                        //                                + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        //فیلتر نقش برداشته شد     
                        ));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(numbersOfThisOption1, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
//                        String no = numbersOfThisOption1.get(0).get("val").toString();// صفر یا بیشتر

                        if (final_values_date.size() > 0) {
                            data11.add(Double.valueOf(final_values_date.get(0).get("val").toString().equals("-") ? "0" : final_values_date.get(0).get("val").toString()));
                            data12.add(Double.valueOf(final_values_date.get(1).get("val").toString().equals("-") ? "0" : final_values_date.get(1).get("val").toString()));
                            data13.add(Double.valueOf(final_values_date.get(2).get("val").toString().equals("-") ? "0" : final_values_date.get(2).get("val").toString()));
                            data14.add(Double.valueOf(final_values_date.get(3).get("val").toString().equals("-") ? "0" : final_values_date.get(3).get("val").toString()));
                            data15.add(Double.valueOf(final_values_date.get(4).get("val").toString().equals("-") ? "0" : final_values_date.get(4).get("val").toString()));
                            data16.add(Double.valueOf(final_values_date.get(5).get("val").toString().equals("-") ? "0" : final_values_date.get(5).get("val").toString()));
                            data17.add(Double.valueOf(final_values_date.get(6).get("val").toString().equals("-") ? "0" : final_values_date.get(6).get("val").toString()));
                            data18.add(Double.valueOf(final_values_date.get(7).get("val").toString().equals("-") ? "0" : final_values_date.get(7).get("val").toString()));
                            data19.add(Double.valueOf(final_values_date.get(8).get("val").toString().equals("-") ? "0" : final_values_date.get(8).get("val").toString()));
                            data20.add(Double.valueOf(final_values_date.get(9).get("val").toString().equals("-") ? "0" : final_values_date.get(9).get("val").toString()));
                            data21.add(Double.valueOf(final_values_date.get(10).get("val").toString().equals("-") ? "0" : final_values_date.get(10).get("val").toString()));
                            data22.add(Double.valueOf(final_values_date.get(11).get("val").toString().equals("-") ? "0" : final_values_date.get(11).get("val").toString()));
                        }
//                        data1.add(Double.valueOf(no));
//                        color1 += "'#5b93d3',";
//                        dataPieArray.add(Integer.valueOf(no));

                    }
                }
            }
            double avg = 0;
            double sum = 100;
            avg = sum / data1.size();
            if (dataType.equals("percentage")) {
                Double sum11 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum12 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum13 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum14 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum15 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum16 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum17 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum18 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum19 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum20 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum21 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum22 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data11.size(); i++) {
                    sum11 += data11.get(i);
                }
                for (int i = 0; i < data12.size(); i++) {
                    sum12 += data12.get(i);
                }
                for (int i = 0; i < data13.size(); i++) {
                    sum13 += data13.get(i);
                }
                for (int i = 0; i < data14.size(); i++) {
                    sum14 += data14.get(i);
                }
                for (int i = 0; i < data15.size(); i++) {
                    sum15 += data15.get(i);
                }
                for (int i = 0; i < data16.size(); i++) {
                    sum16 += data16.get(i);
                }
                for (int i = 0; i < data17.size(); i++) {
                    sum17 += data17.get(i);
                }
                for (int i = 0; i < data18.size(); i++) {
                    sum18 += data18.get(i);
                }
                for (int i = 0; i < data19.size(); i++) {
                    sum19 += data19.get(i);
                }
                for (int i = 0; i < data20.size(); i++) {
                    sum20 += data20.get(i);
                }
                for (int i = 0; i < data21.size(); i++) {
                    sum21 += data21.get(i);
                }
                for (int i = 0; i < data22.size(); i++) {
                    sum22 += data22.get(i);
                }
                Double sumTotal = sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19 + sum20 + sum21 + sum22;
                for (int i = 0; i < data11.size(); i++) {
                    data11.set(i, (double) Math.round((data11.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data12.size(); i++) {
                    data12.set(i, (double) Math.round((data12.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data13.size(); i++) {
                    data13.set(i, (double) Math.round((data13.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data14.size(); i++) {
                    data14.set(i, (double) Math.round((data14.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data15.size(); i++) {
                    data15.set(i, (double) Math.round((data15.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data16.size(); i++) {
                    data16.set(i, (double) Math.round((data16.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data17.size(); i++) {
                    data17.set(i, (double) Math.round((data17.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data18.size(); i++) {
                    data18.set(i, (double) Math.round((data18.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data19.size(); i++) {
                    data19.set(i, (double) Math.round((data19.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data20.size(); i++) {
                    data20.set(i, (double) Math.round((data20.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data21.size(); i++) {
                    data21.set(i, (double) Math.round((data21.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data22.size(); i++) {
                    sum22 += data22.get(i);
                }
                for (int i = 0; i < data22.size(); i++) {
                    data22.set(i, (double) Math.round((data22.get(i) / sumTotal) * 10000) / 100);
                }
                Double sum2 = 0.0;// تعداد همه پاسخ ها برای نمودار مقایسه ای دایره ای  ومحاسبه ی درصد
                for (int i = 0; i < data2.size(); i++) {
                    sum2 += data2.get(i);
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" ماهه اول\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه دوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه سوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data13 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه چهارم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data14 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه پنجم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data15 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه ششم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data16 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label:\"ماهه هفتم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data17 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه هشتم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data18 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه نهم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data19 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه دهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data20 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه یازدهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data21 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه دوازدهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data22 + ","
                        + "          }"
                        + "]"
                        + "},"
                        + "options: {"
                        + "    lineTension:0,"
                        + "    annotation: {"
                        + "    annotations: ["
                        + "{"
                        + "   type: 'line',"
                        + "   mode: 'horizontal',"
                        + "   scaleID: 'y-axis-0',"
                        + "   value: " + avg + ","
                        + "   borderColor: 'red',"
                        + "   borderWidth: 1,"
                        + "   label: {"
                        + "     backgroundColor:'gray',"
                        + "     enabled: true,"
                        + "     content: '" + avg + " " + "میانگین " + "' "
                        + "   }"
                        + " }"
                        + "]"
                        + "},"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{size: '15'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" ماهه اول\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه دوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه سوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data13 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه چهارم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data14 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه پنجم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data15 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه ششم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data16 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label:\"ماهه هفتم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data17 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه هشتم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data18 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه نهم\",\n"
                        + "            backgroundColor: \"brown\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data19 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه دهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data20 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه یازدهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data21 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" ماهه دوازدهم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data22 + ","
                        + "          }"
                        + "]"
                        //                        + "{"
                        //                        + "data: " + data2 + ","
                        //                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        //                        + "borderColor: '#5B93D3',"
                        //                        + "borderWidth: 3,"
                        //                        + "fill: false"
                        //                        + "      }"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";

            }
//            else if (chartType.equals("pie")) {
//                script
//                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
//                        + "var myChart1 = new Chart(ctx1, {"
//                        + "type: 'pie', data: {"
//                        + "labels: ["
//                        + labels1
//                        + "],"
//                        + "datasets: ["
//                        + "{"
//                        + "label: '" + startDate1 + "-" + endDate1 + "',"
//                        + "data: " + data1.toString() + ","
//                        + "backgroundColor: ["
//                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
//                        + "]"
//                        + "},"
//                        + "{"
//                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
//                        + "data: " + data2.toString() + ","
//                        + "backgroundColor: ["
//                        + "'#00b894','#fdcb6e','#d63031','#e84393','#fab1a0','#e67e22','#e67eaa'"
//                        + "]"
//                        + "},"
//                        + "]"
//                        + "},"
//                        + "options: {"
//                        + "plugins: {"
//                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
//                        + ","
//                        + "formatter: function(value, context) {"
//                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
//                        + "}"
//                        + "},"// برای لیبل ها یصورت سیز رنگ
//                        //                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter:Math.round},"// برای لیبل های دارای بکگراند سبز
//                        + "labels: [{"
//                        + "render: 'value',"
//                        + "position: 'inside',"
//                        + "precision: 1,"
//                        + "fontSize: 18,"
//                        + "fontStyle: 'bold',"
//                        + "textShadow: true,"
//                        + "fontColor: '#000'"
//                        + "}"
//                        + ",{"
//                        + "render: 'label',"
//                        + "position: 'outside',"
//                        + "fontSize: 20,"
//                        + "fontStyle: 'bold',"
//                        + "fontColor: '#000'"
//                        + "}"
//                        + "]"
//                        + "},"
//                        + "responsive: true,"
//                        + "title: {"
//                        + "display: true,"
//                        + "fontSize: 18,"
//                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
//                        + "text: 'نمودار دایره ای سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
//                        + "},"
//                        + "legend: {"
//                        + "display: true,"
//                        + "position: 'right'"
//                        + "},"
//                        + "animation: {"
//                        + "animateScale: true,"
//                        + "animateRotate: true"
//                        + "}"
//                        + "}"
//                        + "});";
//            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, _id + "=" + id));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :" + formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));

            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * آنالیز گزینه های یک سوال دو ماهه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String analysFromByQuestionWithPeriodThreeMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            String dateCondition2 = "";
//            
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data11 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data12 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data13 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data14 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_departmentId"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + questionId + "'"
                            + formquestionOption_id + hmis_filter_department_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS val,formAnswers_date _date FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id + hmis_filter_department_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + " group by formAnswers_date ASC  "
                        //                                + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        //فیلتر نقش برداشته شد    
                        ));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(numbersOfThisOption1, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
//                        String no = numbersOfThisOption1.get(0).get("val").toString();// صفر یا بیشتر

                        if (final_values_date.size() > 0) {
                            data11.add(Double.valueOf(final_values_date.get(0).get("val").toString().equals("-") ? "0" : final_values_date.get(0).get("val").toString()));
                            data12.add(Double.valueOf(final_values_date.get(1).get("val").toString().equals("-") ? "0" : final_values_date.get(1).get("val").toString()));
                            data13.add(Double.valueOf(final_values_date.get(2).get("val").toString().equals("-") ? "0" : final_values_date.get(2).get("val").toString()));
                            data14.add(Double.valueOf(final_values_date.get(3).get("val").toString().equals("-") ? "0" : final_values_date.get(3).get("val").toString()));
                        }
                    }
                }
            }
            double avg = 0;
            double sum = 100;
            avg = sum / data1.size();
            if (dataType.equals("percentage")) {
                Double sum11 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum12 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum13 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum14 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد

                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data11.size(); i++) {
                    sum11 += data11.get(i);
                }
                for (int i = 0; i < data12.size(); i++) {
                    sum12 += data12.get(i);
                }
                for (int i = 0; i < data13.size(); i++) {
                    sum13 += data13.get(i);
                }
                for (int i = 0; i < data14.size(); i++) {
                    sum14 += data14.get(i);
                }

                Double sumTotal = sum11 + sum12 + sum13 + sum14;
                for (int i = 0; i < data11.size(); i++) {
                    data11.set(i, (double) Math.round((data11.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data12.size(); i++) {
                    data12.set(i, (double) Math.round((data12.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data13.size(); i++) {
                    data13.set(i, (double) Math.round((data13.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data14.size(); i++) {
                    data14.set(i, (double) Math.round((data14.get(i) / sumTotal) * 10000) / 100);
                }

                Double sum2 = 0.0;// تعداد همه پاسخ ها برای نمودار مقایسه ای دایره ای  ومحاسبه ی درصد
                for (int i = 0; i < data2.size(); i++) {
                    sum2 += data2.get(i);
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" سه ماهه اول\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"سه ماهه دوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth:1,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"سه ماهه سوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data13 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" سه ماهه چهارم\",\n"
                        + "            backgroundColor: \"purple\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data14 + ","
                        + "          }\n"
                        + "]"
                        + "},"
                        + "options: {"
                        + "   responsive: true,"
                        + "    lineTension:0,"
                        + "    annotation: {"
                        + "    annotations: ["
                        + "{"
                        + "   type: 'line',"
                        + "   mode: 'horizontal',"
                        + "   scaleID: 'y-axis-0',"
                        + "   value: " + avg + ","
                        + "   borderColor: 'red',"
                        + "   borderWidth: 1,"
                        + "   label: {"
                        + "     backgroundColor:'gray',"
                        + "     enabled: true,"
                        + "     content: '" + avg + " " + "میانگین " + "' "
                        + "   }"
                        + " }"
                        + "]"
                        + "},"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'20',color: '#0b0b0b',font:{size: '10'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" سه ماهه اول\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"سه ماهه دوم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه سوم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data13 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" سه ماهه چهارم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data14 + ","
                        + "          }\n"
                        + "]"
                        //                        + "{"
                        //                        + "data: " + data2 + ","
                        //                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        //                        + "borderColor: '#5B93D3',"
                        //                        + "borderWidth: 3,"
                        //                        + "fill: false"
                        //                        + "      }"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";

            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, _id + "=" + id));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :" + formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String analysFromByQuestionWithPeriodSixMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            String dateCondition2 = "";
//            
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data11 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data12 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_departmentId"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + questionId + "'"
                            + formquestionOption_id + hmis_filter_department_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS val,formAnswers_date _date FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id + hmis_filter_department_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + " group by formAnswers_date ASC  "
                        //                                + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        //فیلتر نقش برداشته شد    
                        ));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(numbersOfThisOption1, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
//                        String no = numbersOfThisOption1.get(0).get("val").toString();// صفر یا بیشتر

                        if (final_values_date.size() > 0) {
                            data11.add(Double.valueOf(final_values_date.get(0).get("val").toString().equals("-") ? "0" : final_values_date.get(0).get("val").toString()));
                            data12.add(Double.valueOf(final_values_date.get(1).get("val").toString().equals("-") ? "0" : final_values_date.get(1).get("val").toString()));
                        }
                    }
                }
            }
            double avg = 0;
            double sum = 100;
            avg = sum / data1.size();
            if (dataType.equals("percentage")) {
                Double sum11 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum12 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد

                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data11.size(); i++) {
                    sum11 += data11.get(i);
                }
                for (int i = 0; i < data12.size(); i++) {
                    sum12 += data12.get(i);
                }

                Double sumTotal = sum11 + sum12;
                for (int i = 0; i < data11.size(); i++) {
                    data11.set(i, (double) Math.round((data11.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data12.size(); i++) {
                    data12.set(i, (double) Math.round((data12.get(i) / sumTotal) * 10000) / 100);
                }

                Double sum2 = 0.0;// تعداد همه پاسخ ها برای نمودار مقایسه ای دایره ای  ومحاسبه ی درصد
                for (int i = 0; i < data2.size(); i++) {
                    sum2 += data2.get(i);
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" سه ماهه اول\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"سه ماهه دوم\",\n"
                        + "            backgroundColor: \"pink\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth:1,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "]"
                        + "},"
                        + "options: {"
                        + "   responsive: true,"
                        + "    lineTension:0,"
                        + "    annotation: {"
                        + "    annotations: ["
                        + "{"
                        + "   type: 'line',"
                        + "   mode: 'horizontal',"
                        + "   scaleID: 'y-axis-0',"
                        + "   value: " + avg + ","
                        + "   borderColor: 'red',"
                        + "   borderWidth: 1,"
                        + "   label: {"
                        + "     backgroundColor:'gray',"
                        + "     enabled: true,"
                        + "     content: '" + avg + " " + "میانگین " + "' "
                        + "   }"
                        + " }"
                        + "]"
                        + "},"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'20',color: '#0b0b0b',font:{size: '10'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" سه ماهه اول\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"سه ماهه دوم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 1,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "]"
                        //                        + "{"
                        //                        + "data: " + data2 + ","
                        //                        + "label: '" + startDate2 + "-" + endDate2 + " ',"
                        //                        + "borderColor: '#5B93D3',"
                        //                        + "borderWidth: 3,"
                        //                        + "fill: false"
                        //                        + "      }"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, _id + "=" + id));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :" + formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String analysFromByQuestionWithPeriodTwoMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            String chartType = jjTools.getParameter(request, "chartType");
            String dataType = jjTools.getParameter(request, "dataType");
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            String dateCondition2 = "";
//            
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
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String script = "";
            String labels1 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاری
            String labels2 = "";// در این شرایط همه برچسب ها را در یک متغیر رشته ای می گذاریم
            String color1 = "";
            String color2 = "";
            List<Double> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data11 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data12 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data13 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data14 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data15 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Double> data16 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             

            if (jjNumber.isDigit(questionId)) {// اگر درخواست نمودار روی فقط یک سوال بود
                //خط زیر برای اینکه فیلتر کنیم فرم هایی که مثلا  سوال یک را گزینه دو انتخاب کرده اند
                String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
                if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ansertSetSQL = "(SELECT "
                            + "formAnswers_date"
                            + ",formAnswers_status"
                            + ",formanswers_departmentId"
                            + ",formanswers_answerSet_id AS id "
                            + " FROM hmis_formanswers "
                            + " LEFT Join hmis_formAnswerSet ON formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE "
                            + " formanswers_questionId='" + questionId + "'"
                            + formquestionOption_id + hmis_filter_department_id
                            + roleCondition
                            + ")";
                } else {
                    ansertSetSQL = FormAnswerSet.tableName;// اگر فیلتر خاصی مد نظر کاربر نبود همان جدول ست پاسخ ها را باید یگذاریم
                }
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionId));
                    for (int i = 0; i < optionRows.size(); i++) {// برای هر آپشن موجود یک ستون با نام آن آپشن ایجاد می کنیم
                        // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
                        //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
                        String formquestionOption_id = optionRows.get(i).get(FormQuestionOptions._id).toString();
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        List<Map<String, Object>> numbersOfThisOption1 = jjDatabaseWeb.separateRow(db.otherSelect(
                                "SELECT COUNT(*) AS val,formAnswers_date _date FROM " + ansertSetSQL + " ast left Join " + FormAnswers.tableName + " a on ast.id=a.formanswers_answerSet_id "
                                + "WHERE " + FormAnswers._questionId + "=" + questionId
                                //                                + " AND " + FormAnswers._answer + "=" + optionRows.get(i).get(FormQuestionOptions._id)
                                + formquestionOption_id + hmis_filter_department_id
                                + " AND " + FormAnswerSet._status + "= '" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + " group by formAnswers_date ASC  "
                        //                                + (roleCondition)// اگر فیلتر برای گزینه های سوال بود و فیلتر نقش ها هم میخواستیم در بالا حساب میشود خودش دوبار لازم نیست
                        //فیلتر نقش برداشته شد    
                        ));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(numbersOfThisOption1, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        labels1 += " '" + optionRows.get(i).get(FormQuestionOptions._lable).toString() + "(" + optionRows.get(i).get(FormQuestionOptions._value).toString() + ")',";// نام هر آپشن
//                        String no = numbersOfThisOption1.get(0).get("val").toString();// صفر یا بیشتر

                        if (final_values_date.size() > 0) {
                            data11.add(Double.valueOf(final_values_date.get(0).get("val").toString().equals("-") ? "0" : final_values_date.get(0).get("val").toString()));
                            data12.add(Double.valueOf(final_values_date.get(1).get("val").toString().equals("-") ? "0" : final_values_date.get(1).get("val").toString()));
                            data13.add(Double.valueOf(final_values_date.get(2).get("val").toString().equals("-") ? "0" : final_values_date.get(2).get("val").toString()));
                            data14.add(Double.valueOf(final_values_date.get(3).get("val").toString().equals("-") ? "0" : final_values_date.get(3).get("val").toString()));
                            data15.add(Double.valueOf(final_values_date.get(4).get("val").toString().equals("-") ? "0" : final_values_date.get(4).get("val").toString()));
                            data16.add(Double.valueOf(final_values_date.get(5).get("val").toString().equals("-") ? "0" : final_values_date.get(5).get("val").toString()));
                        }
                    }
                }
            }
            double avg = 0;
            double sum = 100;
            avg = sum / data1.size();
            if (dataType.equals("percentage")) {
                Double sum11 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum12 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum13 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum14 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum15 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد
                Double sum16 = 0.0;// تعداد همه پاسخ ها برای نمودار دایره ای  ومحاسبه ی درصد

                //double sum = IntStream.of(keyList).sum(); //if you are using java-8 
                for (int i = 0; i < data11.size(); i++) {
                    sum11 += data11.get(i);
                }
                for (int i = 0; i < data12.size(); i++) {
                    sum12 += data12.get(i);
                }
                for (int i = 0; i < data13.size(); i++) {
                    sum13 += data13.get(i);
                }
                for (int i = 0; i < data14.size(); i++) {
                    sum14 += data14.get(i);
                }
                for (int i = 0; i < data15.size(); i++) {
                    sum15 += data15.get(i);
                }
                for (int i = 0; i < data16.size(); i++) {
                    sum16 += data16.get(i);
                }

                Double sumTotal = sum11 + sum12 + sum13 + sum14 + sum15 + sum16;
                for (int i = 0; i < data11.size(); i++) {
                    data11.set(i, (double) Math.round((data11.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data12.size(); i++) {
                    data12.set(i, (double) Math.round((data12.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data13.size(); i++) {
                    data13.set(i, (double) Math.round((data13.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data14.size(); i++) {
                    data14.set(i, (double) Math.round((data14.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data15.size(); i++) {
                    data15.set(i, (double) Math.round((data15.get(i) / sumTotal) * 10000) / 100);
                }
                for (int i = 0; i < data16.size(); i++) {
                    data16.set(i, (double) Math.round((data16.get(i) / sumTotal) * 10000) / 100);
                }
                Double sum2 = 0.0;
                for (int i = 0; i < data2.size(); i++) {
                    data2.set(i, (double) Math.round((data2.get(i) / sum2) * 10000) / 100);
                }
            }
            if (chartType.equals("bar")) {
                script
                        += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                        + "var myChart1 = new Chart(ctx1, {"
                        + "type: 'bar', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " data:" + data11 + ","
                        + " label: \" دو ماهه اول\",\n"
                        + "           borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth: 1"
                        + "          },\n"
                        + "          {\n"
                        + "data:" + data12 + ","
                        + "            label: \"دو ماهه دوم\",\n"
                        + "            borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth:1"
                        + "          },\n"
                        + "          {\n"
                        + "data:" + data13 + ","
                        + "            label: \"دو ماهه سوم\",\n"
                        + "            borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth: 1"
                        + "          },\n"
                        + "          {\n"
                        + "data:" + data14 + ","
                        + "            label: \" دو ماهه چهارم\",\n"
                        + "            borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth: 1"
                        + "          },\n"
                        + "          {\n"
                        + "data:" + data15 + ","
                        + "            label: \" دو ماهه پنجم\",\n"
                        + "            borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth: 1"
                        + "          },\n"
                        + "          {\n"
                        + "data:" + data16 + ","
                        + "            label: \"دو ماهه ششم\",\n"
                        + "           borderColor: \"#3e95cd\","
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderWidth: 1"
                        + "          }"
                        + "]"
                        + "},"
                        + "options: {"
                        + "    lineTension:0,"
                        + "    annotation: {"
                        + "    annotations: ["
                        + "{"
                        + "   type: 'line',"
                        + "   mode: 'horizontal',"
                        + "   scaleID: 'y-axis-0',"
                        + "   value: " + avg + ","
                        + "   borderColor: 'red',"
                        + "   borderWidth: 1,"
                        + "   label: {"
                        + "     backgroundColor:'gray',"
                        + "     enabled: true,"
                        + "     content: '" + avg + " " + "میانگین " + "' "
                        + "   }"
                        + " }"
                        + "]"
                        + "},"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"
                        + "labels: {"
                        + "render: 'value'"
                        + "}"
                        + "},"
                        + "title: {"
                        + "display: true,"
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار ستونی  " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
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
                        + "  padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "fontSize: 20"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";
            } else if (chartType.equals("line")) {
                script
                        += "var ctx3 = document.getElementById('chartBar3').getContext('2d');"
                        + "var myChart3 = new Chart(ctx3, {"
                        + "type: 'line', data: {"
                        + "labels: ["
                        + labels1
                        + "],"
                        + "datasets: [{"
                        + " label: \" دو ماهه اول\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + " data:" + data11 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"ماهه دوم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data12 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"دو ماهه سوم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data13 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" دو ماهه چهارم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data14 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \" دو ماهه پنجم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data15 + ","
                        + "          },\n"
                        + "          {\n"
                        + "            label: \"دو ماهه ششم\",\n"
                        + "            backgroundColor: \"blue\",\n"
                        + "            borderColor: [],\n"
                        + "            borderWidth: 4,\n"
                        + "data:" + data16 + ","
                        + "          }\n"
                        + "]"
                        + "},"
                        + "options: {"
                        + "plugins: {"
                        + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                        + ","
                        + "formatter: function(value, context) {"
                        + "return  Math.round(value*100)/100 + '" + (dataType.equals("percentage") ? "%" : "") + "';"
                        + "}"
                        + "},"// برای لیبل ها یصورت سیز رنگ
                        + "labels: [{"
                        + "render: 'value',"
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
                        + "fontSize: 18,"
                        + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                        + "text: 'نمودار خطی سوال " + (dataType.equals("percentage") ? "برحسب درصد " : "بر حسب تعداد ") + questionRow.get(0).get(FormQuestions._title) + "'"
                        + "},"
                        + "legend: {"
                        + "display: true,"
                        + "position: 'right'"
                        + "},"
                        + "animation: {"
                        + "animateScale: true,"
                        + "animateRotate: true"
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
                        + "padding: 10,"
                        + "autoSkip: false,"
                        + "maxRotation: 90,"
                        + "minRotation: 70,"
                        + "beginAtZero: true,"
                        + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                        + "fontSize: 15"
                        + "}"
                        + "}]"
                        + "}"
                        + "}"
                        + "});";

            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, _id + "=" + id));
            request.setAttribute("ReportTitle", "آنالیز پاسخ های یک سوال :" + formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", labels1);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * برای ایجاد نمودار آنالیز سوال به سوال همه ی سوال های یک فرم در یک نمودار
     * بصورت درصدی توجه شود این نمودار برای سوال هایی چند گزینه ای است که مقدار
     * عددی داشته باشند سوالاتی که پاسخ آنها عددی است روی این نمودار نمیآیند مثل
     * سن و غیره غ ق ارزیابی ها هم در نتایج لحاظ نمیشود سوالاتی که وزن صفر دارند
     * لحاظ نمی شوند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formQuestionByQuestionAnalyses(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            List<Map<String, Object>> FormRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + jjTools.getParameter(request, "id")));// برای بررسی دسترسی و آوردن عنوان فرم در گزارش
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
            //بررسی اینکه کاربر فیلتر تاریخ هم میخواهد یا نه
            String dateCondition1 = "";
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            System.out.println("jjTools.getParameter(request, \"formAnswers_userRole\")=" + jjTools.getParameter(request, "formAnswers_userRole"));
            if (!jjTools.getParameter(request, "formAnswers_userRole").isEmpty() && !jjTools.getParameter(request, "formAnswers_userRole").equals("null")) {
                dateCondition1 += " AND " + FormAnswerSet._userRole + "=" + jjTools.getParameter(request, "formAnswers_userRole");//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(startDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(endDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }

            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String lable = "";
            List<Float> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Float> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            String color1 = "";
            String color2 = "";
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //سوالاتی  که میخواهیم روی محور افقی بیایند
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            if (questionId.contains("ALL") || questionId.equals("null")) {// اگر همه را  میخواست 
                questionId = "";
            }
            if (questionId != "") {
                questionId = " AND ( id=" + questionId;
                questionId = questionId.replaceAll(",", " OR id=");
                questionId += ")";
            }
            String where2 = "";//مشخص کردن سطح یا حیطه برای سوال
            if (!jjTools.getParameter(request, FormQuestions._range).equals("")) {
                where2 = " and ( " + FormQuestions._range + "='" + jjTools.getParameter(request, FormQuestions._range) + "')";
            }
            //ابتدا سوالاتی که وزن صفر ندارند و نوع آنها رادیو یا سلکت آپشن است را انتخاب می کنیم
            List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                    + questionId// شرط سوالات
                    + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option' OR formQuestions_answersType='checkbox')" + where2
                    + " ORDER BY " + FormQuestions._preority
                    + " ;"));
            //دیتاست اول =========================================================
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            for (int j = 0; j < questionsRows.size(); j++) {// به تعداد سوالاتی که چند گزینه ای هستند
                String maxSQL = "";
                float max = 0;
                // ماگزیمم ممکن در گزینه ها
                if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                    maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                            + " FROM `hmis_formquestionoptions`"
                            + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                    maxSQL = "SELECT sum(formQuestionOptions_value) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                            + " FROM `hmis_formquestionoptions`"
                            + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                }
                List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                    max = Float.valueOf(maxRow.get(0).get("max").toString());
                }
                if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ///اول انسرست هایی که شرط دوم را دارند و  در بازه ی زمانی مطولب و ثبت نهایی شده اند را انتخاب می کنیم
                    ansertSetSQL = "SELECT group_concat(hmis_formanswerset.id SEPARATOR  ',')  anserSets FROM `hmis_formanswers`"
                            + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=formanswers_answerSet_id"
                            + " where "
                            + " formanswers_questionId=" + jjTools.getParameter(request, "hmis_filter_formquestion_id") // انتخاب بخش و چون جوین زدیم لازم است و اگر نباشد اشتباه محاسبه می کند
                            //                            + " AND formanswers_answer=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") // شرط مخصوص انتخاب بخش 
                            + formquestionOption_id
                            + " AND  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                            + dateCondition1
                            + ";";
                    List<Map<String, Object>> answerssetsInCondition = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                    if (answerssetsInCondition.isEmpty() || answerssetsInCondition.get(0).get("anserSets").toString().isEmpty()) {
                        // اینجا چیزی ندهد و درواقع جواب صفر است
                        ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + "AND formanswers_answerSet_id<0" // <<<<<<<<<< مهم برای اینکه یک سطربا مقادری صفر و تهی حداقل  برگرداند که نال پوینتر نشود
                                + " group by formanswers_answerSet_id "
                                + ")t1";
                    } else {
                        String answerSetCondition = answerssetsInCondition.get(0).get("anserSets").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                        answerSetCondition = " AND ( formanswers_answerSet_id=" + answerSetCondition + " )";
                        ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + answerSetCondition
                                + " group by formanswers_answerSet_id "
                                + ")t1";
                    }
                } else {
                    // یک سطر حاوی مجموع و ماکزیمم امتیاز سوال و تعداد فرم های تکمیل شده 
                    if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                        ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max,sum(formQuestionOptions_value) as sum,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                + " FROM `hmis_formanswers` a1"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1
                                + ";";
                    } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                        ansertSetSQL = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                + "SELECT sum(formQuestionOptions_value) as sum1,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                + " FROM `hmis_formanswers` a1"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1
                                + " Group by formanswers_answerSet_id"
                                + ")t1;";
                    }
                }

                List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
//                String title = questionsRows.get(j).get(FormQuestions._title).toString();
//                if (title.length() > 50) {
//                    lable += "'" + title.substring(0, 20) + "..." + title.substring((title.length() - 21), (title.length() - 1)) + "',";//
                //20 کاراکتر 
                // از اول نشون میده 20
                // کاراکتر از آخر

//                } else {
//                    lable += "'" + title + "',";// چون لیبل ها مشترک است فقط در این حلقه تولید میشوند
//                }
                lable += "'" + questionsRows.get(j).get(FormQuestions._title) + "',";// چون لیبل ها مشترک است فقط در این حلقه تولید میشوند
                Float sum;
                Float valueInPercent = null;
                if (jjNumber.isFloat(answersToOneQuestionRows.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                    float count = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                    sum = Float.parseFloat(answersToOneQuestionRows.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                    if (max != 0 && sum != 0) {// برای پرهیز از تقسیم بر صفر
                    if (max != 0) {// shiran2 14001021
                        valueInPercent = (sum * 100) / (max * count);
                    }
                }// در غیر اینصورت صفر میشود
                data1.add(valueInPercent);
                color1 += "'#2ecc71',";

            }
            //ابتدا سوالاتی که وزن صفر ندارند و نوع آنها رادیو یا سلکت آپشن است را قبلا انتخاب کرده ایم
            //دیتاست دوم =========================================================
            float sum = 0;
            float percent = 0;
            float valueInPercent = 0;
            if (!dateCondition2.isEmpty()) {// اگر مقایسه هممیخواستیم دوباره برای بازه دوم به تعداد سوالات در حلقه عملیات محاسبه ی درصد هر سوال را تکرار می کنیم
                for (int j = 0; j < questionsRows.size(); j++) {// به تعداد سوالاتی که چند گزینه ای هستند
                    String maxSQL = "";
                    float max = 0;
                    // ماگزیمم ممکن در گزینه ها
                    if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio")) {
                        maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                                + " FROM `hmis_formquestionoptions`"
                                + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                    } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                        maxSQL = "SELECT sum(formQuestionOptions_value) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                                + " FROM `hmis_formquestionoptions`"
                                + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                    }
                    List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));

                    if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                        max = Float.valueOf(maxRow.get(0).get("max").toString());
                    }
                    if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                        ///اول انسرست هایی که شرط دوم را دارند و  در بازه ی زمانی مطولب و ثبت نهایی شده اند را انتخاب می کنیم
                        String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        ansertSetSQL = "SELECT group_concat(hmis_formanswerset.id SEPARATOR  ',')  anserSets FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=formanswers_answerSet_id"
                                + " where "
                                + " formanswers_questionId=" + jjTools.getParameter(request, "hmis_filter_formquestion_id") // انتخاب بخش و چون جوین زدیم لازم است و اگر نباشد اشتباه محاسبه می کند
                                //                                + " AND formanswers_answer=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") // شرط مخصوص انتخاب بخش 
                                + formquestionOption_id
                                + " AND  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition2
                                + ";";
                        List<Map<String, Object>> answerssetsInCondition = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        if (answerssetsInCondition.isEmpty() || answerssetsInCondition.get(0).get("anserSets").toString().isEmpty()) {
                            //@ اینجا چیزی ندهد و درواقع جواب صفر است
                            ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                    + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                    + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + "AND formanswers_answerSet_id<0" // <<<<<<<<<< مهم برای اینکه یک سطربا مقادری صفر و تهی حداقل  برگرداند که نال پوینتر نشود
                                    + " group by formanswers_answerSet_id "
                                    + ")t1";

                        } else {
                            String answerSetCondition = answerssetsInCondition.get(0).get("anserSets").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                            answerSetCondition = " AND ( formanswers_answerSet_id=" + answerSetCondition + " )";
                            ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                    + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                    + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + answerSetCondition
                                    + " group by formanswers_answerSet_id "
                                    + ")t1";
                        }
                    } else {
                        // یک سطر حاوی مجموع و ماکزیمم امتیاز سوال و تعداد فرم های تکمیل شده 
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max,sum(formQuestionOptions_value) as sum,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                                    + ";";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                    + "SELECT sum(formQuestionOptions_value) as sum1,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1;";

                        }
                    }
                    List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                    Float sum2;
                    Float valueInPercent2 = null;
                    if (jjNumber.isFloat(answersToOneQuestionRows.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                        float count2 = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                        sum2 = Float.parseFloat(answersToOneQuestionRows.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                        if (max != 0 && sum2 != 0) {// برای پرهیز از تقسیم بر صفر
                        if (max != 0) {// shiran2 14001021
                            valueInPercent2 = (sum2 * 100) / (max * count2);
                        }
                    }// در غیر اینصورت صفر میشود
                    data2.add(valueInPercent2);
                    color2 += "'#f1c40f',";
                }
            }
            //=================================================================
            //دیتاست دوم =========================================================
            String script = "";

            script += "Chart.Legend.prototype.afterFit = function() { this.height = this.height + 50;};";//برای ایجاد فاصله بین لجند بالای نمودار و خود نمودارها
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + lable
                    + "],"
                    + "datasets: ["
                    + "{"
                    + "label: '" + startDate1 + "-" + endDate1 + "دیتاست اول',"
                    + "data: " + data1.toString() + ","
                    + "backgroundColor: ["
                    + color1
                    + "]"
                    + "},";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " دیتاست مقایسه ای',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + color2
                        + "]"
                        + "},";
            }
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                System.out.println("*********************&&&&&&&&&&&&&&&&&&&&&*******************");
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int i = 0; i < optionRows.size(); i++) {
                    chartTitleExtraDiscription += " " + optionRows.get(i).get(FormQuestionOptions._lable);
                    if (i < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '16'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 +'%';"
                    + "}},"
                    + "labels: {"
                    + "render: 'value' "
                    + "}"
                    + "},"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['نمودار آنالیز سوال به سوال فرم : " + FormRow.get(0).get(_title) + " ', "
                    + " ' "
                    + chartTitleExtraDiscription
                    + " '] "
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 65,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("reportName", FormRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "آنالیز سوال به سوال:");
            request.setAttribute("script", script);
            request.setAttribute("data", data1);
            request.setAttribute("lables", lable);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;

        } catch (Exception ex) {
            ex.printStackTrace();
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * گزارش گیری روش دوم با استفاده از بخش در formanswerSet
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formQuestionByQuestionAnalyses2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            List<Map<String, Object>> FormRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + jjTools.getParameter(request, "id")));// برای بررسی دسترسی و آوردن عنوان فرم در گزارش
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            double sum1 = 0;
            double avg = 0;

            //بررسی اینکه کاربر فیلتر تاریخ هم میخواهد یا نه
            String dateCondition1 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//اگر بخش همه بود
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            String startDate1 = jjTools.getParameter(request, "formAnswers_date_from");
            String endDate1 = jjTools.getParameter(request, "formAnswers_date_to");
            String startDate2 = jjTools.getParameter(request, "formAnswers_date_from2");
            String endDate2 = jjTools.getParameter(request, "formAnswers_date_to2");
            System.out.println("jjTools.getParameter(request, \"formAnswers_userRole\")=" + jjTools.getParameter(request, "formAnswers_userRole"));
            if (!jjTools.getParameter(request, "formAnswers_userRole").isEmpty() && !jjTools.getParameter(request, "formAnswers_userRole").equals("null")) {
                dateCondition1 += " AND " + FormAnswerSet._userRole + "=" + jjTools.getParameter(request, "formAnswers_userRole");//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(startDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition1 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(endDate1, false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to2").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to2"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }

            List<Integer> dataPieArray = new ArrayList<>();// آرایه برای نمودار دایره ای// آرایه برای نمودار دایره ای
            String lable = "";
            List<Float> data1 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            List<Float> data2 = new ArrayList<>();// مقادیر هر دو نمودار در صورت وجود             
            String color1 = "";
            String color2 = "";
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //سوالاتی  که میخواهیم روی محور افقی بیایند
            String questionId = jjTools.getParameter(request, "hmis_formquestions_id");
            if (questionId.contains("ALL") || questionId.equals("null")) {// اگر همه را  میخواست 
                questionId = "";
            }
            if (questionId != "") {
                questionId = " AND ( id=" + questionId;
                questionId = questionId.replaceAll(",", " OR id=");
                questionId += ")";
            }
            String where2 = "";//مشخص کردن سطح یا حیطه برای سوال
            if (!jjTools.getParameter(request, FormQuestions._range).equals("")) {
                where2 = " and ( " + FormQuestions._range + "='" + jjTools.getParameter(request, FormQuestions._range) + "')";
            }
            //ابتدا سوالاتی که وزن صفر ندارند و نوع آنها رادیو یا سلکت آپشن است را انتخاب می کنیم
            List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                    + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                    + questionId// شرط سوالات
                    + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio'"
                    + " OR formQuestions_answersType='select_option' OR formQuestions_answersType='checkbox')" + where2
                    + " ORDER BY " + FormQuestions._preority
                    + " ;"));
            //دیتاست اول =========================================================
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            for (int j = 0; j < questionsRows.size(); j++) {// به تعداد سوالاتی که چند گزینه ای هستند
                String maxSQL = "";
                float max = 0;
                // ماگزیمم ممکن در گزینه ها
                if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                    maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                            + " FROM `hmis_formquestionoptions`"
                            + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                    maxSQL = "SELECT sum(formQuestionOptions_value) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                            + " FROM `hmis_formquestionoptions`"
                            + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                }
                List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                    max = Float.valueOf(maxRow.get(0).get("max").toString());
                }
                if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                    String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                    if (formquestionOption_id != "") {
                        formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                        formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                        formquestionOption_id += ")";
                    }
                    ///اول انسرست هایی که شرط دوم را دارند و  در بازه ی زمانی مطولب و ثبت نهایی شده اند را انتخاب می کنیم
                    ansertSetSQL = "SELECT group_concat(hmis_formanswerset.id SEPARATOR  ',')  anserSets FROM `hmis_formanswers`"
                            + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=formanswers_answerSet_id"
                            + " where "
                            + " formanswers_questionId=" + jjTools.getParameter(request, "hmis_filter_formquestion_id") // انتخاب بخش و چون جوین زدیم لازم است و اگر نباشد اشتباه محاسبه می کند
                            //                            + " AND formanswers_answer=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") // شرط مخصوص انتخاب بخش 
                            + formquestionOption_id + hmis_filter_department_id
                            + " AND  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                            + dateCondition1
                            + ";";
                    List<Map<String, Object>> answerssetsInCondition = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                    if (answerssetsInCondition.isEmpty() || answerssetsInCondition.get(0).get("anserSets").toString().isEmpty()) {
                        // اینجا چیزی ندهد و درواقع جواب صفر است
                        ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + "AND formanswers_answerSet_id<0" // <<<<<<<<<< مهم برای اینکه یک سطربا مقادری صفر و تهی حداقل  برگرداند که نال پوینتر نشود
                                + " group by formanswers_answerSet_id "
                                + ")t1";
                    } else {
                        String answerSetCondition = answerssetsInCondition.get(0).get("anserSets").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                        answerSetCondition = " AND ( formanswers_answerSet_id=" + answerSetCondition + " )";
                        ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + answerSetCondition
                                + " group by formanswers_answerSet_id "
                                + ")t1";
                    }
                } else {
                    // یک سطر حاوی مجموع و ماکزیمم امتیاز سوال و تعداد فرم های تکمیل شده 
                    if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                        ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max,sum(formQuestionOptions_value) as sum,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                + " FROM `hmis_formanswers` a1"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + hmis_filter_department_id
                                + ";";
                    } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                        ansertSetSQL = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                + "SELECT sum(formQuestionOptions_value) as sum1,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                + " FROM `hmis_formanswers` a1"
                                + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition1 + hmis_filter_department_id
                                + " Group by formanswers_answerSet_id"
                                + ")t1;";
                    }
                }

                List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
//                String title = questionsRows.get(j).get(FormQuestions._title).toString();
//                if (title.length() > 50) {
//                    lable += "'" + title.substring(0, 20) + "..." + title.substring((title.length() - 21), (title.length() - 1)) + "',";//
                //20 کاراکتر 
                // از اول نشون میده 20
                // کاراکتر از آخر

//                } else {
//                    lable += "'" + title + "',";// چون لیبل ها مشترک است فقط در این حلقه تولید میشوند
//                }
                lable += "'" + questionsRows.get(j).get(FormQuestions._title) + questionsRows.get(j).get(FormQuestions._preority) + "-" + "',";// چون لیبل ها مشترک است فقط در این حلقه تولید میشوند
                Float sum;
                Float valueInPercent = null;
                if (jjNumber.isFloat(answersToOneQuestionRows.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                    float count = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                    sum = Float.parseFloat(answersToOneQuestionRows.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                    if (max != 0 && sum != 0) {// برای پرهیز از تقسیم بر صفر
                    if (max != 0) {// شیران14001008          
                        valueInPercent = (sum * 100) / (max * count);
                    }
                    sum1 += valueInPercent;
                }// در غیر اینصورت صفر میشود     
                data1.add(valueInPercent);
                color1 += "'#2ecc71',";

            }
            avg = sum1 / questionsRows.size();
            //ابتدا سوالاتی که وزن صفر ندارند و نوع آنها رادیو یا سلکت آپشن است را قبلا انتخاب کرده ایم
            //دیتاست دوم =========================================================
            float sum = 0;
            float percent = 0;
            float valueInPercent = 0;
            if (!dateCondition2.isEmpty()) {// اگر مقایسه هممیخواستیم دوباره برای بازه دوم به تعداد سوالات در حلقه عملیات محاسبه ی درصد هر سوال را تکرار می کنیم
                for (int j = 0; j < questionsRows.size(); j++) {// به تعداد سوالاتی که چند گزینه ای هستند
                    String maxSQL = "";
                    float max = 0;
                    // ماگزیمم ممکن در گزینه ها
                    if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio")) {
                        maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                                + " FROM `hmis_formquestionoptions`"
                                + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                    } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                        maxSQL = "SELECT sum(formQuestionOptions_value) as max ,formQuestionOptions_value,formQuestionOptions_lable"
                                + " FROM `hmis_formquestionoptions`"
                                + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                    }
                    List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));

                    if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                        max = Float.valueOf(maxRow.get(0).get("max").toString());
                    }
                    if (!jjTools.getParameter(request, "hmis_filter_formquestion_id").isEmpty() && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بر اساس پاسخ های خاصی در ست پاسخ مد نظر بود
                        ///اول انسرست هایی که شرط دوم را دارند و  در بازه ی زمانی مطولب و ثبت نهایی شده اند را انتخاب می کنیم
                        String formquestionOption_id = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                        if (formquestionOption_id != "") {
                            formquestionOption_id = " AND ( formanswers_answer=" + formquestionOption_id;
                            formquestionOption_id = formquestionOption_id.replaceAll(",", " OR formanswers_answer=");
                            formquestionOption_id += ")";
                        }
                        ansertSetSQL = "SELECT group_concat(hmis_formanswerset.id SEPARATOR  ',')  anserSets FROM `hmis_formanswers`"
                                + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=formanswers_answerSet_id"
                                + " where "
                                + " formanswers_questionId=" + jjTools.getParameter(request, "hmis_filter_formquestion_id") // انتخاب بخش و چون جوین زدیم لازم است و اگر نباشد اشتباه محاسبه می کند
                                //                                + " AND formanswers_answer=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id") // شرط مخصوص انتخاب بخش 
                                + formquestionOption_id
                                + " AND  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                + dateCondition2
                                + ";";
                        List<Map<String, Object>> answerssetsInCondition = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        if (answerssetsInCondition.isEmpty() || answerssetsInCondition.get(0).get("anserSets").toString().isEmpty()) {
                            //@ اینجا چیزی ندهد و درواقع جواب صفر است
                            ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                    + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                    + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + "AND formanswers_answerSet_id<0" // <<<<<<<<<< مهم برای اینکه یک سطربا مقادری صفر و تهی حداقل  برگرداند که نال پوینتر نشود
                                    + " group by formanswers_answerSet_id "
                                    + ")t1";

                        } else {
                            String answerSetCondition = answerssetsInCondition.get(0).get("anserSets").toString().replaceAll(",", " OR formanswers_answerSet_id=");
                            answerSetCondition = " AND ( formanswers_answerSet_id=" + answerSetCondition + " )";
                            ansertSetSQL = "SELECT sum(sum1) as sum ,count(*) as no from("
                                    + " SELECT  formanswers_answerSet_id,sum(formQuestionOptions_value) AS sum1,(formQuestionOptions_lable) as no FROM `hmis_formanswers`"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=formanswers_answer"
                                    + " where formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + answerSetCondition
                                    + " group by formanswers_answerSet_id "
                                    + ")t1";
                        }
                    } else {
                        // یک سطر حاوی مجموع و ماکزیمم امتیاز سوال و تعداد فرم های تکمیل شده 
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max,sum(formQuestionOptions_value) as sum,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                                    + ";";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                    + "SELECT sum(formQuestionOptions_value) as sum1,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition2
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1;";

                        }
                    }
                    List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                    Float sum2;
                    Float valueInPercent2 = null;
                    if (jjNumber.isFloat(answersToOneQuestionRows.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                        float count2 = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                        sum2 = Float.parseFloat(answersToOneQuestionRows.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                        if (max != 0 && sum2 != 0) {// برای پرهیز از تقسیم بر صفر
                        if (max != 0) {//shira2 14001021
                            valueInPercent2 = (sum2 * 100) / (max * count2);
                        }
                    }// در غیر اینصورت صفر میشود
                    data2.add(valueInPercent2);
                    color2 += "'#f1c40f',";
                }
            }
            //=================================================================
            //دیتاست دوم =========================================================
            String script = "";
            script += "Chart.Legend.prototype.afterFit = function() { this.height = this.height + 50;};";//برای ایجاد فاصله بین لجند بالای نمودار و خود نمودارها
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + lable
                    + "],"
                    + "datasets: ["
                    + "{"
                    + "label: '" + startDate1 + "-" + endDate1 + "دیتاست اول',"
                    + "data: " + data1.toString() + ","
                    + "backgroundColor: ["
                    + color1
                    + "]"
                    + "},";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += "{"
                        + "label: '" + startDate2 + "-" + endDate2 + " دیتاست مقایسه ای',"
                        + "data: " + data2.toString() + ","
                        + "backgroundColor: ["
                        + color2
                        + "]"
                        + "},";
            }
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                System.out.println("*********************&&&&&&&&&&&&&&&&&&&&&*******************");
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int i = 0; i < optionRows.size(); i++) {
                    chartTitleExtraDiscription += " " + optionRows.get(i).get(FormQuestionOptions._lable);
                    if (i < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "    lineTension:0,"
                    + "    annotation: {"
                    + "    annotations: ["
                    + "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + avg + ","
                    + "   borderColor: 'red',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'gray',"
                    + "     enabled: true,"
                    + "     content: '" + avg + " " + "میانگین " + "' "
                    + "   }"
                    + " }"
                    + "]"
                    + "},"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '16'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 +'%';"
                    + "}},"
                    + "labels: {"
                    + "render: 'value' "
                    + "}"
                    + "},"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['نمودار آنالیز سوال به سوال فرم : " + FormRow.get(0).get(_title) + " ', "
                    + " ' "
                    + chartTitleExtraDiscription
                    + " '] "
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 65,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("ReportTitle", "آنالیز سوال به سوال:" + FormRow.get(0).get(_title));
            request.setAttribute("reportName", FormRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("data", data1.toString().replaceAll("\\[|\\]", ""));
            System.out.println("/////////data1.toString()=" + data1.toString().replaceAll("\\[|\\]", ""));
            request.setAttribute("lables", lable);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;

        } catch (Exception ex) {
            ex.printStackTrace();
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
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
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
            String groupBy = "";
            int div = jjNumber.isDigit(jjTools.getParameter(request, "div")) ? Integer.valueOf(jjTools.getParameter(request, "div")) : 30;// برای تقسیم بدنی بر اساس دوره زمانی روزانه
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس            
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
//            String where = " WHERE " + FormAnswerSet._formId + "= " + jjTools.getParameter(request, _id);
            /////////////////////////////////////////////////
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title, Forms._id + "" + jjTools.getParameter(request, "id")));
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");

            String where = " WHERE " + FormAnswerSet._formId + "= " + jjTools.getParameter(request, "id");
            if (jjNumber.isDigit(hmis_filter_formquestion_id) && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").equals("")) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";

//shiran2
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            }

            /////////////////////////////////////////////////
            String color = "";
            if (!jjTools.getParameter(request, "formAnswers_userRole").isEmpty() && !jjTools.getParameter(request, "formAnswers_userRole").equals("null")) {
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

            if (questionId.startsWith("avg-")) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                questionId = questionId.replace("avg-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    //@ToDo برای چک باکس جور دیگر باید عمل کرد  که مجموع را اول حساب کند بعد میانگین را

                    sql = "select avg(val) as val from ("
                            + "SELECT  sum(formQuestionOptions_value) as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND formQuestionOptions_value<>'' "
                            + roleOrUserCondition;
                    groupBy = "GROUP BY formanswers_answerSet_id)t ";// به پپرانتز آخر دقت کنید
                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "avg(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " AND formanswers_answer<>'' "
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //+ groupBy + ";"
                            ;

                }
            } else if (questionId.startsWith("sum-")) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                questionId = questionId.replace("sum-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    //@ToDo برای چک باکس جور دیگر باید عمل کرد  که مجموع را اول حساب کند بعد میانگین را
                    axel_y = "sum(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND formQuestionOptions_value<>'' "
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "sum(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + "AND formanswers_answer<>'' "
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                }
            } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                } else if (questionId.equals("avgOfPercents")) {
                    axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                } else if (questionId.equals("avg")) {
                    axel_y = "avg(hmis_formanswerset_sum)100";
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
            if (div >= 30) {
                datePointerTemp.addMonth(div / 30);
            } else {
                datePointerTemp.addDay(div);
            }
            while (datePointer.getDBFormat_8length() < endDate && datePointerTemp.getDBFormat_8length() <= endDate) {
                labels += " '" + datePointer.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer.getDBFormat_8length() + " ";
                if (div >= 30) {
                    datePointer.addMonth(div / 30);
                } else {
                    datePointer.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                }
                //-----------------------------------------------------------------------------------------------------------------
                dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer.getDBFormat_8length() + " ";
                List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition + roleOrUserCondition + groupBy));
                data += "'" + results.get(0).get("val") + "',";
                color += "'#5B93D3',";
                if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {// اگر در ریکوئست برای مقایسه تاریخ وارد شده بود  و اگر در ریکوئست چیزی نبود اینجا وارد نمیشود
                    labels += " '" + datePointer2.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                    dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer2.getDBFormat_8length() + " ";
                    if (div >= 30) {
                        datePointer2.addMonth(div / 30);
                    } else {
                        datePointer2.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                    }

                    dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer2.getDBFormat_8length() + " ";
                    List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition + roleOrUserCondition + groupBy));
                    data += "'" + results2.get(0).get("val") + "',";
                    color += "'#324463 ',";
                }
            }

            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
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
                    + "label: '#',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: ["
                    + color
                    + "]"
                    + "}]"
                    + "},"
                    + "options: {"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},"
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("ReportTitle", "آنالیز عملکرد دوره ای یک نقش و یا یک فرد برای این فرم :<br/>" + chartTitleExtraDiscription);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("script", script);
            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * روش دوم گزارش گیری روش فعلی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formCountResult_period2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
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
            int count = 0;
            double sum = 0.0;
            double avg = 0;
            String sql = "";
            String groupBy = "";
            int div = jjNumber.isDigit(jjTools.getParameter(request, "div")) ? Integer.valueOf(jjTools.getParameter(request, "div")) : 30;// برای تقسیم بدنی بر اساس دوره زمانی روزانه
            String roleOrUserCondition = " ";
            String departmentCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس            
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
//            String where = " WHERE " + FormAnswerSet._formId + "= " + jjTools.getParameter(request, _id);
            /////////////////////////////////////////////////
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");

            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");

//                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
//                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
//                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
//                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
//            }     
//            if (!hmis_filter_department_id.isEmpty()) {//
//                if (hmis_filter_department_id != "") {
//                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
//                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
//                    hmis_filter_department_id += ")";
//                }    
//            }  
            if (!hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                departmentCondition += "AND (";
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (departmentsIdArray.length - 1 == i) {//برای عنصر آخر اور نمیخواهد و باید پرانتز را ببنیدم
                        departmentCondition += "" + FormAnswerSet._departmentId + "=" + departmentsIdArray[i] + ")";
                    } else {
                        departmentCondition += FormAnswerSet._departmentId + "=" + departmentsIdArray[i] + " OR ";
                    }
                }
            }
            String where = " WHERE " + FormAnswerSet._formId + "= " + jjTools.getParameter(request, "id") + departmentCondition;

            if (jjNumber.isDigit(hmis_filter_formquestion_id) && !jjTools.getParameter(request, "hmis_filter_formquestionOption_id").equals("")) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'  "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";

//shiran2
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            }
            /////////////////////////////////////////////////
            String color = "";
            if (!jjTools.getParameter(request, "formAnswers_userRole").isEmpty() && !jjTools.getParameter(request, "formAnswers_userRole").equals("null")) {
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

            if (questionId.startsWith("avg-")) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                questionId = questionId.replace("avg-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    //@ToDo برای چک باکس جور دیگر باید عمل کرد  که مجموع را اول حساب کند بعد میانگین را

                    sql = "select avg(val) as val from ("
                            + "SELECT  sum(formQuestionOptions_value) as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND formQuestionOptions_value<>'' "
                            + roleOrUserCondition;
                    groupBy = "GROUP BY formanswers_answerSet_id)t ";// به پپرانتز آخر دقت کنید
                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "avg(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " AND formanswers_answer<>'' "
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //+ groupBy + ";"
                            ;

                }
            } else if (questionId.startsWith("sum-")) {// اگر یک سوال را انتخاب کرده بود ینابر این اینجا عدد داریم که آی دی سوال است
                questionId = questionId.replace("sum-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    //@ToDo برای چک باکس جور دیگر باید عمل کرد  که مجموع را اول حساب کند بعد میانگین را
                    axel_y = "sum(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + " FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND formQuestionOptions_value<>'' "
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "sum(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val ," + FormAnswerSet._formId + "," + FormAnswerSet._userId + ", " + FormAnswerSet._userName
                            + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + "AND formanswers_answer<>'' "
                            + where
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition //                            + groupBy + ";"
                            ;
                }
            } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                } else if (questionId.equals("avgOfPercents")) {
                    axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                } else if (questionId.equals("avg")) {
                    axel_y = "avg(hmis_formanswerset_sum)100";
                } else if (questionId.equals("sum")) {
                    axel_y = "sum(hmis_formanswerset_sum)";
                }
                //چدول زیر تعداد فرم های پر شده در هر روز را بدست می آور
                //@ToDO Use 'DIV' in SQL like this post  https://stackoverflow.com/questions/57229285/mysql-how-to-select-count-of-rows-group-by-sun-calendar-date-and-div-to-periodic/57231625#57231625
                sql = "SELECT  " + axel_y + " as val," + FormAnswerSet._date + ",formAnswers_departmentId  FROM hmis_formanswerset "
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
            if (div >= 30) {
                datePointerTemp.addMonth(div / 30);
            } else {
                datePointerTemp.addDay(div);
            }
            while (datePointer.getDBFormat_8length() < endDate && datePointerTemp.getDBFormat_8length() <= endDate) {
                labels += " '" + datePointer.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer.getDBFormat_8length() + " ";
                if (div >= 30) {
                    datePointer.addMonth(div / 30);
                } else {
                    datePointer.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                }
                //-----------------------------------------------------------------------------------------------------------------
                dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer.getDBFormat_8length() + " ";
                List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition + roleOrUserCondition + groupBy));
                data += "'" + results.get(0).get("val") + "',";
                count += results.size();
                sum += Double.parseDouble(results.get(0).get("val").toString().equals("") ? "0" : results.get(0).get("val").toString());
                color += "'#5B93D3',";
                if (!jjTools.getParameter(request, "formAnswers_date_from2").isEmpty()) {// اگر در ریکوئست برای مقایسه تاریخ وارد شده بود  و اگر در ریکوئست چیزی نبود اینجا وارد نمیشود
                    labels += " '" + datePointer2.getViewFormat_10length() + "',";// برچسب هر ستون که تاریخ شروع بازه است
                    dateCondition = " AND " + FormAnswerSet._date + ">=" + datePointer2.getDBFormat_8length() + " ";
                    if (div >= 30) {
                        datePointer2.addMonth(div / 30);
                    } else {
                        datePointer2.addDay(div);// به اندازه دوره به تاریخ اضافه می کنیم // اینجا به تاریخ اصافه میشود
                    }

                    dateCondition += " AND " + FormAnswerSet._date + "<" + datePointer2.getDBFormat_8length() + " ";
                    List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql + dateCondition + roleOrUserCondition + groupBy));
                    data += "'" + results2.get(0).get("val") + "',";
                    count += results2.size();
                    sum += Double.parseDouble(results2.get(0).get("val").toString().equals("") ? "0" : results2.get(0).get("val").toString());
                    color += "'#324463 ',";
                }
                avg = sum / count;
            }
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم

                for (int j = 0; j < departmentsIdArray.length; j++) {
                    chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[j], db);
                    if (j < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
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
                    + "label: '#',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: ["
                    + color
                    + "]"
                    + "}]"
                    + "},"
                    + "options: {"
                    + "    lineTension:0,"
                    + "    annotation: {"
                    + "    annotations: ["
                    + "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + avg + ","
                    + "   borderColor: 'red',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'gray',"
                    + "     enabled: true,"
                    + "     content: '" + avg + " " + "میانگین " + "' "
                    + "   }"
                    + " }"
                    + "]"
                    + "},"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},"
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, "id=" + id));
            request.setAttribute("ReportTitle", "آنالیز عملکرد دوره ای یک نقش و یا یک فرد برای این فرم :" + formRow.get(0).get(_title) + "<br/>" + chartTitleExtraDiscription);
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));

            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
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
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
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
            String where = "";
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._title, Forms._id + "=" + id));
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");

            where += " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (jjNumber.isDigit(hmis_filter_formquestion_id) && jjNumber.isDigit(jjTools.getParameter(request, "hmis_filter_formquestionOption_id"))) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";

//shiran2
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "'";
            }
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
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
            if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    axel_y = "avg(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                            + roleOrUserCondition
                            + groupBy + ";";

                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "avg(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition
                            + groupBy + ";";
                }

            } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
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
                            + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
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
                if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                        || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                        ) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                    axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                    axel_y = "sum(hmis_formanswerset_sum)";
                } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                    axel_y = "avg(hmis_formanswerset_sum)";
                }
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

            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
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
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text:" + " 'گزارش عملکرد" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%میانگین درصد های پاسخگویی" : "") + " ' "
                    + "},"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                    + ","
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 + '" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%" : "") + "';"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "آنالیز عملکرد پرسنل و افراد برای این فرم<br/>" + chartTitleExtraDiscription);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_turnover2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            double sum = 0.0;
            double avg = 0.0;
            String sql = "";
            String groupBy = "";
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String lableColumn = "";// برای اینکه در نتیجه  کوئری بدانیم بر چه اساسی نام ساتون ها را استحراج کنیم
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
            String where = "";
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");

            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            where += " WHERE " + FormAnswerSet._formId + "=" + id + hmis_filter_department_id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (jjNumber.isDigit(hmis_filter_formquestion_id) && jjNumber.isDigit(jjTools.getParameter(request, "hmis_filter_formquestionOption_id"))) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";

//shiran2   
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            }
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
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
            if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                        || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                    axel_y = "avg(formQuestionOptions_value)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                            + roleOrUserCondition
                            + groupBy + ";";

                } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                    axel_y = "avg(formanswers_answer)";
                    sql = "SELECT  " + axel_y + " as val" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                            + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                            + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                            + where
                            + dateCondition
                            + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                            + roleOrUserCondition
                            + groupBy + ";";
                }

            } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
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
                            + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
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
                if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                        || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                        ) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                    axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                    axel_y = "sum(hmis_formanswerset_sum)";
                } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                    axel_y = "avg(hmis_formanswerset_sum)";
                }
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
                sum += Double.parseDouble(val);
            }
            avg = sum / results.size();
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, "id=" + id));
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
                    + "    lineTension:0,"
                    + "    annotation: {"
                    + "    annotations: ["
                    + "{"
                    + "   type: 'line',"
                    + "   mode: 'horizontal',"
                    + "   scaleID: 'y-axis-0',"
                    + "   value: " + avg + ","
                    + "   borderColor: 'red',"
                    + "   borderWidth: 1,"
                    + "   label: {"
                    + "     backgroundColor:'gray',"
                    + "     enabled: true,"
                    + "     content: '" + avg + " " + "میانگین " + "' "
                    + "   }"
                    + " }"
                    + "]"
                    + "},"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text:" + " 'گزارش عملکرد" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%میانگین درصد های پاسخگویی" : "") + " ' "
                    + "},"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                    + ","
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 + '" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%" : "") + "';"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "آنالیز عملکرد پرسنل و افراد برای این فرم:" + formRow.get(0).get(_title) + "<br/>" + chartTitleExtraDiscription);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_turnoverWithPeriodThreeMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            double sum = 0.0;
            double avg = 0.0;
            String sql = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";

            String groupBy = "";
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String lableColumn = "";// برای اینکه در نتیجه  کوئری بدانیم بر چه اساسی نام ساتون ها را استحراج کنیم
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
            String where = "";
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            where += " WHERE " + FormAnswerSet._formId + "=" + id + hmis_filter_department_id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (jjNumber.isDigit(hmis_filter_formquestion_id) && jjNumber.isDigit(jjTools.getParameter(request, "hmis_filter_formquestionOption_id"))) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
//shiran2   
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            }
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }

            // تعیین محور افقی----------------------------------------------------------------
            if (!jjTools.getParameter(request, "axel_x_roles").equals("") && !jjTools.getParameter(request, "axel_x_roles").equals("null")) {// اگر محور افقی را برا اساس نقش ها تعیین خواسته باشد
                String roles[] = jjTools.getParameter(request, "axel_x_roles").split(",");
                lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
                if (jjTools.getParameter(request, "axel_x_roles").equals("formAnswers_userRole")) {
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                } else { // اگر یک یا چند عدد بود یعنی نمودار عملکرد آن نقش ها را می خواهد
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                    lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
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
            if (!jjTools.getParameter(request, "axel_x_roles").equals("") && !jjTools.getParameter(request, "axel_x_roles").equals("null")) {

                String roles[] = jjTools.getParameter(request, "axel_x_roles").split(",");

                for (int k = 0; k < roles.length; k++) {

                    if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "avg(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + " group By formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "avg(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + " GROUP By formAnswers_date;";
                        }

                    } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "sum(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + "  GROUP  By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "sum(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + "  GROUP  By  formAnswers_date;";
                        }
                    } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                        if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                                || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                                ) {
                            axel_y = "COUNT(hmis_formanswerset.id)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                            axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                        } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                            axel_y = "sum(hmis_formanswerset_sum)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                            axel_y = "avg(hmis_formanswerset_sum)";
                        }
                        sql = "SELECT  " + axel_y + " as val ,hmis_formanswerset.formAnswers_date _date" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + " FROM hmis_formanswerset "
                                + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                + where
                                + dateCondition
                                + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                + "  GROUP  By  formAnswers_date;";
                    }
                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
                    }
                    labels += " '" + (results.get(0).get(lableColumn) == null ? "نامشخص" : results.get(0).get(lableColumn).toString()) + "',";// نام هر آپشن

                }
            }

            if (!jjTools.getParameter(request, "axel_x_users").equals("") && !jjTools.getParameter(request, "axel_x_users").equals("null")) {
                String users[] = jjTools.getParameter(request, "axel_x_users").split(",");
                for (int k = 0; k < users.length; k++) {
                    if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "avg(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP  By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "avg(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP  By  formAnswers_date;";
                        }

                    } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "sum(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "sum(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP By  formAnswers_date;";
                        }
                    } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                        if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                                || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                                ) {
                            axel_y = "COUNT(hmis_formanswerset.id)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                            axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                        } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                            axel_y = "sum(hmis_formanswerset_sum)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                            axel_y = "avg(hmis_formanswerset_sum)";
                        }
                        sql = "SELECT  " + axel_y + " as val ,hmis_formanswerset.formAnswers_date _date" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + " FROM hmis_formanswerset "
                                + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                + where
                                + dateCondition
                                + " AND " + FormAnswerSet._userId + "=" + users[k]
                                + "  GROUP By  formAnswers_date;";
                    }
                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
                    }
                    labels += " '" + (results.get(0).get(lableColumn) == null ? "نامشخص" : results.get(0).get(lableColumn).toString()) + "',";// نام هر آپشن

                }
            }

            //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
//            for (int i = 0; i < results.size(); i++) {// برای هر سطر از جدول یک ستون در محور افق و مقدار آن را هم همان موقع اضافه می کنیم
//                // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
//                //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
//                labels += " '" + (results.get(i).get(lableColumn) == null ? "نامشخص" : results.get(i).get(lableColumn).toString()) + "',";// نام هر آپشن
//                String val = results.get(i).get("val").toString();// یک یا بیشتر
//                data += "'" + val + "',";
//                sum += Double.parseDouble(val);
//            }
//            avg = sum / results.size();
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, "id=" + id));
            String script = "";
            script += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],"
                    + "datasets: [{"
                    + " label: \"  سه ماهه اول\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه سوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه چهارم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },\n"
                    + "]"
                    + "},"
                    + "options: {"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                    + ","
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 + '" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%" : "") + "';"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "آنالیز عملکرد پرسنل و افراد برای این فرم:" + formRow.get(0).get(_title) + "<br/>" + chartTitleExtraDiscription);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_turnoverWithPeriodSixMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            double sum = 0.0;
            double avg = 0.0;
            String sql = "";
            String data11 = "";
            String data12 = "";

            String groupBy = "";
            String roleOrUserCondition = " ";
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String lableColumn = "";// برای اینکه در نتیجه  کوئری بدانیم بر چه اساسی نام ساتون ها را استحراج کنیم
            String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
            String data = "";
            String where = "";
            List<Map<String, Object>> answersSetsInOneCondition = null;
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (hmis_filter_department_id.equals("")) {//اگر بخش همه بود
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + jjTools.getParameter(request, "id")));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            if (!hmis_filter_department_id.isEmpty()) {//
                if (hmis_filter_department_id != "") {
                    hmis_filter_department_id = " AND ( formanswers_departmentId=" + hmis_filter_department_id;
                    hmis_filter_department_id = hmis_filter_department_id.replaceAll(",", " OR formanswers_departmentId=");
                    hmis_filter_department_id += ")";
                }
            }
            where += " WHERE " + FormAnswerSet._formId + "=" + id + hmis_filter_department_id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (jjNumber.isDigit(hmis_filter_formquestion_id) && jjNumber.isDigit(jjTools.getParameter(request, "hmis_filter_formquestionOption_id"))) {
                //shiran2
                String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id").toString().equals("null") ? "" : jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
                if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                    filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
                } else {
                }
                List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
                for (int i = 0; i < optionsRow.size(); i++) {
                    answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + ""));
                }
                where += " AND ( hmis_formanswerset.id="
                        + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
//shiran2   
            } else {
                where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            }
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }

            // تعیین محور افقی----------------------------------------------------------------
            if (!jjTools.getParameter(request, "axel_x_roles").equals("") && !jjTools.getParameter(request, "axel_x_roles").equals("null")) {// اگر محور افقی را برا اساس نقش ها تعیین خواسته باشد
                String roles[] = jjTools.getParameter(request, "axel_x_roles").split(",");
                lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
                if (jjTools.getParameter(request, "axel_x_roles").equals("formAnswers_userRole")) {
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                } else { // اگر یک یا چند عدد بود یعنی نمودار عملکرد آن نقش ها را می خواهد
                    groupBy = " GROUP BY " + FormAnswerSet._userRole;
                    lableColumn = Role._title;// چون با جدول نقش ها جوین می کنیم
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
            if (!jjTools.getParameter(request, "axel_x_roles").equals("") && !jjTools.getParameter(request, "axel_x_roles").equals("null")) {

                String roles[] = jjTools.getParameter(request, "axel_x_roles").split(",");

                for (int k = 0; k < roles.length; k++) {

                    if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "avg(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + " group By formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "avg(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + " GROUP By formAnswers_date;";
                        }

                    } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "sum(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + "  GROUP  By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "sum(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                    + "  GROUP  By  formAnswers_date;";
                        }
                    } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                        if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                                || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                                ) {
                            axel_y = "COUNT(hmis_formanswerset.id)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                            axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                        } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                            axel_y = "sum(hmis_formanswerset_sum)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                            axel_y = "avg(hmis_formanswerset_sum)";
                        }
                        sql = "SELECT  " + axel_y + " as val ,hmis_formanswerset.formAnswers_date _date" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + " FROM hmis_formanswerset "
                                + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                + where
                                + dateCondition
                                + " AND " + FormAnswerSet._userRole + "=" + roles[k]
                                + "  GROUP  By  formAnswers_date;";
                    }
                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                    }
                    labels += " '" + (results.get(0).get(lableColumn) == null ? "نامشخص" : results.get(0).get(lableColumn).toString()) + "',";// نام هر آپشن

                }
            }

            if (!jjTools.getParameter(request, "axel_x_users").equals("") && !jjTools.getParameter(request, "axel_x_users").equals("null")) {
                String users[] = jjTools.getParameter(request, "axel_x_users").split(",");
                for (int k = 0; k < users.length; k++) {
                    if (jjTools.getParameter(request, "axel_y").startsWith("avg-")) {// اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("avg-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "avg(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP  By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "avg(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP  By  formAnswers_date;";
                        }

                    } else if (jjTools.getParameter(request, "axel_y").startsWith("sum-")) {//اگر میانگین عددی امتیازات یک سوال را میخواست که بداند هر نقش یا فرد بصورت میانگین چه امتیازاتی داده
                        String questionId = jjTools.getParameter(request, "axel_y").replaceAll("sum-", "");
                        // اگر سوال انتخاب شده یک سوال چند گزینه ای باشد و هدف جمع آوری مجموع ارزش های آن باشد
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("radio")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")
                                || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox")) {
                            axel_y = "sum(formQuestionOptions_value)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + " LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + " LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND hmis_formquestionoptions.formQuestionOptions_value<>'' "//غیر قابل ارزیابی ها را نیاورد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP By  formAnswers_date;";

                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {
                            axel_y = "sum(formanswers_answer)";
                            sql = "SELECT  " + axel_y + " as val,hmis_formanswerset.formAnswers_date _date " + (lableColumn.isEmpty() ? "" : "," + lableColumn) + "  FROM hmis_formanswerset "
                                    + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                    + "LEFT JOIN hmis_formanswers on hmis_formanswerset.id=formanswers_answerSet_id"
                                    + where
                                    + dateCondition
                                    + " AND " + FormAnswers._questionId + "=" + questionId// ّرای محاسبه ی جاهایی که نوع فیلد عددی و جواب هم حتما عددی باشد
                                    + " AND " + FormAnswerSet._userId + "=" + users[k]
                                    + "  GROUP By  formAnswers_date;";
                        }
                    } else {//اگر مورد بررسی پاسخ ها و در واقع  آی دی یک سوال نبود  و مجموع نتایج یا میانگین و یا تعداد فرم های پر شه بود
                        if (jjTools.getParameter(request, "axel_y").equals("COUNT(hmis_formanswerset.id)")
                                || jjTools.getParameter(request, "axel_y").equalsIgnoreCase("null") || jjTools.getParameter(request, "axel_y") == ""// یا اگر تهی بود و چیز خاصی را برای گزارش گیری انتخاب نکرده بود
                                ) {
                            axel_y = "COUNT(hmis_formanswerset.id)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avgOfPercents")) {
                            axel_y = "avg(hmis_formanswerset_sum/hmis_formanswerset_avg)*100";
                        } else if (jjTools.getParameter(request, "axel_y").equals("sum")) {
                            axel_y = "sum(hmis_formanswerset_sum)";
                        } else if (jjTools.getParameter(request, "axel_y").equals("avg")) {
                            axel_y = "avg(hmis_formanswerset_sum)";
                        }
                        sql = "SELECT  " + axel_y + " as val ,hmis_formanswerset.formAnswers_date _date" + (lableColumn.isEmpty() ? "" : "," + lableColumn) + " FROM hmis_formanswerset "
                                + "LEFT JOIN hmis_role on hmis_role.id=formAnswers_userRole "
                                + where
                                + dateCondition
                                + " AND " + FormAnswerSet._userId + "=" + users[k]
                                + "  GROUP By  formAnswers_date;";
                    }
                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                    }
                    labels += " '" + (results.get(0).get(lableColumn) == null ? "نامشخص" : results.get(0).get(lableColumn).toString()) + "',";// نام هر آپشن

                }
            }

            //ّبرای آپشن دار ها بر اساس آپشن ها نمودار می دهیم
//            for (int i = 0; i < results.size(); i++) {// برای هر سطر از جدول یک ستون در محور افق و مقدار آن را هم همان موقع اضافه می کنیم
//                // برای اینکه بفهمیم هر گزینه توسط همه کاربران چند بار انتخاب شده
//                //فقط تعداد سطرهایی که پاسخ ها دقیقا همین آپشن هستند و ست پاسخ هم در وضعیت ثبت نهایی باشد
//                labels += " '" + (results.get(i).get(lableColumn) == null ? "نامشخص" : results.get(i).get(lableColumn).toString()) + "',";// نام هر آپشن
//                String val = results.get(i).get("val").toString();// یک یا بیشتر
//                data += "'" + val + "',";
//                sum += Double.parseDouble(val);
//            }
//            avg = sum / results.size();
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int j = 0; j < optionRows.size(); j++) {
                    chartTitleExtraDiscription += " " + optionRows.get(j).get(FormQuestionOptions._lable);
                    if (j < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, _title, "id=" + id));
            String script = "";
            script += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],"
                    + "datasets: [{"
                    + " label: \" شش ماهه اول\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"شش ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "]"
                    + "},"
                    + "options: {"
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'}"
                    + ","
                    + "formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 + '" + (jjTools.getParameter(request, "axel_y").equals("avg") ? "%" : "") + "';"
                    + "}"
                    + "},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 11,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontSize: 16,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "آنالیز عملکرد پرسنل و افراد برای این فرم:" + formRow.get(0).get(_title) + "<br/>" + chartTitleExtraDiscription);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * بدست آوردن میانگین در صد پاسخ گویی در فرم
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
//    public static String formCountResult_range(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        try {
//            String id = jjTools.getParameter(request, _id);
////            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
////            String roleCondition = "";
////            if (!userRole.isEmpty()) {//فیلتر نقش 
////                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
////            }
////            System.out.println("roleCondition : " + roleCondition);
//            String departmentCondition = "";
//            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
//            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
//            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }          
////            String dateCondition2 = "";
////            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
////                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
////            }
////            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
////                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
////            }
//            String labels = "";//برای ایجاد آرایه در چارت جی اس
//            String data = "";
//            String backgroundColorChart = "";
//            String data2 = "";
//            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
//            if (!hmis_filter_department_id.equals("")) {
//                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
//                                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;     
//
//            } 
////            else {      
////                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
////                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
////                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
////                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
////            }
//            String[] departmentsIdArray = hmis_filter_department_id.split(",");
//            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
//            if (!hmis_filter_range.equals("")) {
//                
//                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");          
//            } else {
//                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
//                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
//                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
//                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
//            }
//            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");   
//            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
//                if (!rangeQuestionIdArray[i].equals("")) {
//                    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT hmis_formquestions.id"
//                            + ",hmis_formquestions.formQuestions_answersType,formQuestions_weight"
//                            //                            + ",((formQuestions_weight*(MAX(hmis_formquestionOptions.formQuestionOptions_value)))*count(formanswers_questionId))as sumMaxAllAnswers"
//                            + ",count(formanswers_questionId)as count"
//                            + ",SUM(hmis_formquestionOptions.formQuestionOptions_value)as sumAnswers"
//                            + " FROM hmis_formanswers"
//                            + " left join hmis_formquestionOptions  on hmis_formanswers.formanswers_answer=hmis_formquestionOptions.id\n"
//                            + " left join hmis_formquestions  on hmis_formquestionOptions.formQuestionOptions_question_id=hmis_formquestions.id"
//                            + " left join hmis_formanswerset  on hmis_formquestions.formQuestions_formID=hmis_formanswerset.formAnswers_formId"
//                            + " WHERE  (" + FormQuestions._answersType + "='select_option'"
//                            + " OR " + FormQuestions._answersType + "='radio'"
//                            + " OR " + FormQuestions._answersType + "='checkbox')"       
//                            + " AND " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "'"
//                            + " AND formQuestions_formID=" + id + " "+dateCondition+" "+departmentCondition          
//                            + " group by hmis_formquestions.id "         
//                            + ""));
//                    float val = 0;
//                    for (int m = 0; m < questionsRow.size(); m++) {
//                        List<Map<String, Object>> questionsMaxOptions = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
//                                + " MAX(hmis_formquestionOptions.formQuestionOptions_value)MAXOption FROM hmis_formquestionOptions "
//                                + "where formQuestionOptions_question_id=" + questionsRow.get(m).get(FormQuestions._id)
//                        ));
//                        System.out.println("questionsRow.get(m).get(\"sumAnswers\").toString()=" + questionsRow.get(m).get("sumAnswers").toString());
//                        System.out.println("questionsRow.get(m).get(FormQuestions._id)=" + questionsRow.get(m).get(FormQuestions._id));
//                        System.out.println("questionsMaxOptions.get(0).get(\"MAXOption\").toString()=" + questionsMaxOptions.get(0).get("MAXOption").toString());
//                        val += (Float.parseFloat(questionsRow.get(m).get("sumAnswers").toString()) / (Float.parseFloat(questionsMaxOptions.get(0).get("MAXOption").toString()) * Float.parseFloat(questionsRow.get(m).get("count").toString()))) * 100;
//                        System.out.println("val=" + val);
//                    }
//                    labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر بخش
//                    data += "'" + val + "',";
//                    backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
//                }
//            }
//            System.out.println("/////////////////data///////////////////=" + data);             
//            String script = "";
//            script
//                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
//                    + "var myChart1 = new Chart(ctx1, {"   
//                    + "type: 'bar', data: {"
//                    + "labels: ["
//                    + labels
//                    + "],   "
//                    + "datasets: [{"
//                    + "label: '" + jjTools.getParameter(request, "formAnswers_date_from") + "-" + jjTools.getParameter(request, "formAnswers_date_to") + "',"
//                    + "data: ["
//                    + data
//                    + "],"
//                    + ""
//                    + "backgroundColor:[" + backgroundColorChart + "]"
//                    + "}";
////            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
////                script
////                        += ",{"
////                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
////                        + "data: ["
////                        + data2
////                        + "],"
////                        + "backgroundColor: 'red' "
////                        + "}";
////
////            }
//            String chartTitleExtraDiscription = "";
//            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
//                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
//                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
//                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
//                        chartTitleExtraDiscription += " و ";
//                    }
//                }
//            }
//            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
//            script
//                    += "]"
//                    + "},"
//                    + "options: {"
//                    + "title: {"
//                    + "display: true,"
//                    + "fontSize: 18,"
//                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
//                    + "text: '" + formRow.get(0).get(Forms._title) + " "
//                    //                    + chartTitleExtraDiscription
//                    + "'"
//                    + "},"
//                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
//                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
//                            "annotation: {"
//                            + " annotations: ["
//                            + "{"
//                            + "   type: 'line',"
//                            + "   mode: 'horizontal',"
//                            + "   scaleID: 'y-axis-0',"
//                            + "   value: '" + 100 + "',"
//                            + "   borderColor: 'black',"
//                            + "   borderWidth: 1,"
//                            + "   label: {"
//                            + "     backgroundColor:'gray',"
//                            + "     enabled: true,"
//                            + "     content: 'max : " + 100 + "%'"
//                            + "   }"
//                            + " }]},")
//                            : "") // اگر ماکزیمم صفر بود
//                    + "plugins: {"
//                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
//                    + "return  Math.round(value*100)/100 ;"
//                    + "}},"
//                    + "labels: {"
//                    + "render: 'value'"
//                    + "}"
//                    + "},"
//                    + "legend: {"
//                    + "display: true,"
//                    + "labels: {"
//                    + "display: true"
//                    + "}"
//                    + "},"
//                    + "scales: {"
//                    + "yAxes: [{"
//                    + "ticks: {"
//                    + "beginAtZero: true,"
//                    + "fontSize: 12,"
//                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
//                    //                    + "min: Math.min.apply(this,data_array) -5,"
//                    + "}"
//                    + "}],"
//                    + "xAxes: [{"
//                    + "ticks: {"
//                    + "  padding: 10,"
//                    + "autoSkip: false,"
//                    + "maxRotation: 90,"
//                    + "minRotation: 70,"
//                    + "beginAtZero: true,"
//                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
//                    + "fontSize: 15"
//                    + "}"
//                    + "}]"
//                    + "}"
//                    + "}"
//                    + "});";
//            request.setAttribute("script", script);
//            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه");
//            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            Server.outPrinter(request, response, script);
//            return script;
//        } catch (Exception ex) {
//            Server.outPrinter(request, response, Server.ErrorHandler(ex));
//            return "";
//        }
//    }
    public static String formCountResult_range(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            String id = jjTools.getParameter(request, _id);
//            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
//            String roleCondition = "";
//            if (!userRole.isEmpty()) {//فیلتر نقش 
//                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
//            }
//            System.out.println("roleCondition : " + roleCondition);
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            String ansertSetSQL2 = "";// برای اعمال فیلتر با توجه به یک جواب خاص

            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";

            String backgroundColorChart = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
            if (!hmis_filter_range.equals("")) {

                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");
            } else {
                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
            }
            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");
            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                Float val = null;
                Float val2 = null;
                float valueInPercent = 0;
                float valueInPercent2 = 0;

                if (!rangeQuestionIdArray[i].equals("")) {
                    int countQuestions = 0;
                    int countQuestions2 = 0;
                    List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                            + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                            + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option'"
                            + " OR formQuestions_answersType='checkbox') and ( " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "')  "
                            + " ;"));

                    for (int j = 0; j < questionsRows.size(); j++) {
                        String maxSQL = "";

                        float max = 0;
                        // ماگزیمم ممکن در گزینه ها
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                            maxSQL = "SELECT sum(formQuestionOptions_value*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        }
                        List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                        if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                            max = Float.valueOf(maxRow.get(0).get("max").toString());
                        }

                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
//                            ansertSetSQL = "SELECT max(formQuestionOptions_value) as max,sum(formQuestionOptions_value) as sum,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
//                                    + " FROM `hmis_formanswers` a1"
//                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
//                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
//                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
//                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
//                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
//                                    + dateCondition + departmentCondition
//                                    + ";";
//رفع
//باگ شده سلکت بالا
//shiran2 14000810

                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                    + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + ";";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                        + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + ";";
                            }
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                    + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1;";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT SUM(sum1) AS sum,count(*) AS no,formQuestionOptions_lable  FROM( "
                                        + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " Group by formanswers_answerSet_id"
                                        + ")t1;";
                            }
                        }

                        List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        Float sum;
                        if (jjNumber.isFloat(answersToOneQuestionRows.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                            float count = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                            sum = Float.parseFloat(answersToOneQuestionRows.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                            if (max != 0 && sum != 0) {// برای پرهیز از تقسیم بر صفر
                            if (questionsRows.size() == 1) {
                                if (max != 0) {
                                    valueInPercent += (sum * 100) / (max * count);
                                    countQuestions++;
                                }
                            } else {
                                valueInPercent += (sum);
                                System.out.println("valueInPercent=" + valueInPercent);
                                System.out.println("/////////////////////////////////////////");
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(0).get("no").toString());
                                System.out.println("countQuestions=" + countQuestions);
                                System.out.println("/////////////////////////////////////////");
                                if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                    valueInPercent = valueInPercent * 100;
                                    countQuestions = countQuestions * (int) max;
                                }
                            }
                        }// در غیر اینصورت صفر میشود                
                        if (!dateCondition2.isEmpty()) {
                            Float sum2;
                            List<Map<String, Object>> answersToOneQuestionRows2 = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL2));
                            if (jjNumber.isFloat(answersToOneQuestionRows2.get(0).get("sum").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows2.get(0).get("no").toString());
                                sum2 = Float.parseFloat(answersToOneQuestionRows2.get(0).get("sum").toString());;//مقدار ضربدر تعداد
//                                if (max != 0 && sum2 != 0) {// برای پرهیز از تقسیم بر صفر
//                                +++++++++++++++++++++++++++++++++++++++++++++++
//                                if (max != 0) {// shiran2 14001021
//                                    valueInPercent2 += (sum2 * 100) / (max * count);
//                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent2 += (sum2 * 100) / (max * count);
                                        countQuestions2++;
                                    }
                                } else {
                                    valueInPercent2 += (sum2);
                                    countQuestions2 += Integer.parseInt(answersToOneQuestionRows2.get(0).get("no").toString());
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent2 = valueInPercent2 * 100;
                                        countQuestions2 = countQuestions2 * (int) max;
                                    }
                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }// در غیر اینصورت صفر میشود          
                        }
                    }
                    val = valueInPercent / countQuestions;
                    val2 = valueInPercent2 / countQuestions2;

                    labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر رنج
                    data += "'" + val + "',";
                    data2 += "'" + val2 + "',";
                    backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
                }
            }
            System.out.println("/////////////////data///////////////////=" + data);
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + "label: '" + jjTools.getParameter(request, "formAnswers_date_from") + "-" + jjTools.getParameter(request, "formAnswers_date_to") + "',"
                    + "data: ["
                    + data
                    + "],"
                    + ""
                    + "backgroundColor:[" + backgroundColorChart + "]"
                    + "}";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += ",{"
                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
                        + "data: ["
                        + data2
                        + "],"
                        + "backgroundColor: 'red' "
                        + "}";

            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " "
                    //                    + chartTitleExtraDiscription
                    + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه" + ":" + formRow.get(0).get(Forms._title));
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_rangeWithPeriodOneMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
//            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
//            String roleCondition = "";
//            if (!userRole.isEmpty()) {//فیلتر نقش 
//                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
//            }
//            System.out.println("roleCondition : " + roleCondition);
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            String dateCondition2 = "";
//            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            String ansertSetSQL2 = "";// برای اعمال فیلتر با توجه به یک جواب خاص

            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";
            String data15 = "";
            String data16 = "";
            String data17 = "";
            String data18 = "";
            String data19 = "";
            String data20 = "";
            String data21 = "";
            String data22 = "";
            String backgroundColorChart = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
            if (!hmis_filter_range.equals("")) {

                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");
            } else {
                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
            }
            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");
            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                Float val = null;
                Float val2 = null;

                if (!rangeQuestionIdArray[i].equals("")) {
                    List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                            + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                            + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option'"
                            + " OR formQuestions_answersType='checkbox') and ( " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "')  "
                            + " ;"));
                    float valueInPercent2 = 0;
                    float valueInPercent11 = 0;
                    float valueInPercent12 = 0;
                    float valueInPercent13 = 0;
                    float valueInPercent14 = 0;
                    float valueInPercent15 = 0;
                    float valueInPercent16 = 0;
                    float valueInPercent17 = 0;
                    float valueInPercent18 = 0;
                    float valueInPercent19 = 0;
                    float valueInPercent20 = 0;
                    float valueInPercent21 = 0;
                    float valueInPercent22 = 0;
                    int countQuestions = 0;
                    for (int j = 0; j < questionsRows.size(); j++) {
                        String maxSQL = "";
                        float max = 0;
                        // ماگزیمم ممکن در گزینه ها
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                            maxSQL = "SELECT sum(formQuestionOptions_value*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        }
                        List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                        if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                            max = Float.valueOf(maxRow.get(0).get("max").toString());
                        }

                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                    + ",sum(formQuestionOptions_value*formQuestions_weight) as val,formQuestions_weight,"
                                    + "count(*) as no ,formQuestionOptions_value,hmis_formanswerset.formAnswers_date _date,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " GROUP BY formAnswers_date ASC;"
                                    + ";";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                        + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,hmis_formanswerset.formAnswers_date _date,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " GROUP BY formAnswers_date ASC;"
                                        + ";";
                            }
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS val,hmis_formanswerset.formAnswers_date _date,count(*) AS no,formQuestionOptions_lable"
                                    + "  FROM( "
                                    + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,"
                                    + "formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1 "
                                    + " ORDER BY formAnswers_date ASC;"
                                    + "  ;";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT SUM(sum1) AS val,count(*) AS no,formQuestionOptions_lable  FROM( "
                                        + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,"
                                        + "GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " Group by formanswers_answerSet_id"
                                        + ")t1 "
                                        + " ORDER BY formAnswers_date ASC;"
                                        + "";
                            }
                        }

                        List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(answersToOneQuestionRows, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        Float sum;
                        if (final_values_date.size() > 0) {
                            if (jjNumber.isFloat(final_values_date.get(0).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(0).get("no").toString());

                                sum = Float.parseFloat(final_values_date.get(0).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent11 += (sum * 100) / (max * count);
                                }

                            }
                            if (jjNumber.isFloat(final_values_date.get(1).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(1).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(1).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(1).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent12 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(2).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(2).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(2).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(2).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent13 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(3).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(3).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(3).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(3).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent14 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(4).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(4).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(4).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(4).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent15 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(5).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(5).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(5).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(5).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent16 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(6).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(6).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(6).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(6).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent17 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(7).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(7).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(7).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(7).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent18 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(8).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(8).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(8).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(8).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent19 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(9).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(9).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(9).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(9).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent20 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(10).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(10).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(10).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(10).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent21 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(11).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(11).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(11).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(11).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent22 += (sum * 100) / (max * count);
                                }
                            }
                        }
                    }
                    float questionSize = 0;
                    questionSize = questionsRows.size();
                    System.out.println("countQuestions=" + countQuestions);
                    data11 += "'" + valueInPercent11 / countQuestions + "',";
                    data12 += "'" + valueInPercent12 / countQuestions + "',";
                    data13 += "'" + valueInPercent13 / countQuestions + "',";
                    data14 += "'" + valueInPercent14 / countQuestions + "',";
                    data15 += "'" + valueInPercent15 / countQuestions + "',";
                    data16 += "'" + valueInPercent16 / countQuestions + "',";
                    data17 += "'" + valueInPercent17 / countQuestions + "',";
                    data18 += "'" + valueInPercent18 / countQuestions + "',";
                    data19 += "'" + valueInPercent19 / countQuestions + "',";
                    data20 += "'" + valueInPercent20 / countQuestions + "',";
                    data21 += "'" + valueInPercent21 / countQuestions + "',";
                    data22 += "'" + valueInPercent22 / countQuestions + "',";
                }
                labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر رنج
                backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
            }
            System.out.println("/////////////////data///////////////////=");
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \" ماهه اول\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه دوم\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه سوم\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه چهارم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه پنجم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data15 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه ششم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data16 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label:\"ماهه هفتم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data17 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه هشتم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data18 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه نهم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data19 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه دهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data20 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه یازدهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data21 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه دوازدهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data22 + "],"
                    + "          },\n";

//            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
//                script
//                        += ",{"
//                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
//                        + "data: ["
//                        + data2
//                        + "],"
//                        + "backgroundColor: 'red' "
//                        + "}";
//
//            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " "
                    //                    + chartTitleExtraDiscription
                    + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه" + ":" + formRow.get(0).get(Forms._title));
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_rangeWithPeriodTwoMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            String dateCondition2 = "";

            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            String ansertSetSQL2 = "";// برای اعمال فیلتر با توجه به یک جواب خاص

            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";
            String data15 = "";
            String data16 = "";

            String backgroundColorChart = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
            if (!hmis_filter_range.equals("")) {

                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");
            } else {
                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
            }
            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");
            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                Float val = null;
                Float val2 = null;

                if (!rangeQuestionIdArray[i].equals("")) {
                    List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                            + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                            + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option'"
                            + " OR formQuestions_answersType='checkbox') and ( " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "')  "
                            + " ;"));
                    float valueInPercent2 = 0;
                    float valueInPercent11 = 0;
                    float valueInPercent12 = 0;
                    float valueInPercent13 = 0;
                    float valueInPercent14 = 0;
                    float valueInPercent15 = 0;
                    float valueInPercent16 = 0;
                    int countQuestions = 0;
                    for (int j = 0; j < questionsRows.size(); j++) {
                        String maxSQL = "";
                        float max = 0;
                        // ماگزیمم ممکن در گزینه ها
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                            maxSQL = "SELECT sum(formQuestionOptions_value*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        }
                        List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                        if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                            max = Float.valueOf(maxRow.get(0).get("max").toString());
                        }

                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                    + ",sum(formQuestionOptions_value*formQuestions_weight) as val,formQuestions_weight,"
                                    + "count(*) as no ,formQuestionOptions_value,hmis_formanswerset.formAnswers_date _date,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " GROUP BY formAnswers_date ASC;"
                                    + ";";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                        + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,hmis_formanswerset.formAnswers_date _date,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " GROUP BY formAnswers_date ASC;"
                                        + ";";
                            }
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS val,hmis_formanswerset.formAnswers_date _date,count(*) AS no,formQuestionOptions_lable"
                                    + "  FROM( "
                                    + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,"
                                    + "formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1 "
                                    + " ORDER BY formAnswers_date ASC;"
                                    + "  ;";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT SUM(sum1) AS val,count(*) AS no,formQuestionOptions_lable  FROM( "
                                        + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,"
                                        + "GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " Group by formanswers_answerSet_id"
                                        + ")t1 "
                                        + " ORDER BY formAnswers_date ASC;"
                                        + "";
                            }
                        }

                        List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(answersToOneQuestionRows, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        Float sum;
                        if (final_values_date.size() > 0) {
                            if (jjNumber.isFloat(final_values_date.get(0).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(0).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(0).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(0).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent11 += (sum * 100) / (max * count);
                                }

                            }
                            if (jjNumber.isFloat(final_values_date.get(1).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(1).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(1).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(1).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent12 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(2).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(2).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(2).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(2).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent13 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(3).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(3).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(3).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(3).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent14 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(4).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(4).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(4).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(4).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent15 += (sum * 100) / (max * count);
                                }
                            }
                            if (jjNumber.isFloat(final_values_date.get(5).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(answersToOneQuestionRows.get(5).get("no").toString());
                                countQuestions += Integer.parseInt(answersToOneQuestionRows.get(0).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(5).get("val").toString());;//مقدار ضربدر تعداد
                                if (max != 0) {//shiran2 14001021
                                    valueInPercent16 += (sum * 100) / (max * count);
                                }
                            }

                        }
                    }
                    float questionSize = 0;
                    questionSize = questionsRows.size();
                    System.out.println("countQuestions=" + countQuestions);
                    data11 += "'" + valueInPercent11 / countQuestions + "',";
                    data12 += "'" + valueInPercent12 / countQuestions + "',";
                    data13 += "'" + valueInPercent13 / countQuestions + "',";
                    data14 += "'" + valueInPercent14 / countQuestions + "',";
                    data15 += "'" + valueInPercent15 / countQuestions + "',";
                    data16 += "'" + valueInPercent16 / countQuestions + "',";

                }
                labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر رنج
                backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
            }
            System.out.println("/////////////////data///////////////////=");
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \" دو ماهه اول\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه سوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" دو ماهه چهارم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" دو ماهه پنجم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data15 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه ششم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data16 + "],"
                    + "          },\n";

//            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
//                script
//                        += ",{"
//                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
//                        + "data: ["
//                        + data2
//                        + "],"
//                        + "backgroundColor: 'red' "
//                        + "}";
//
//            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " "
                    //                    + chartTitleExtraDiscription
                    + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه" + ":" + formRow.get(0).get(Forms._title));
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_rangeWithPeriodThreeMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            String dateCondition2 = "";

            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            String ansertSetSQL2 = "";// برای اعمال فیلتر با توجه به یک جواب خاص

            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";

            String backgroundColorChart = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
            if (!hmis_filter_range.equals("")) {

                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");
            } else {
                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
            }
            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");
            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                Float val = null;
                Float val2 = null;

                if (!rangeQuestionIdArray[i].equals("")) {
                    List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                            + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                            + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option'"
                            + " OR formQuestions_answersType='checkbox') and ( " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "')  "
                            + " ;"));
                    float valueInPercent11 = 0;
                    float valueInPercent2 = 0;
                    float valueInPercent12 = 0;
                    float valueInPercent13 = 0;
                    float valueInPercent14 = 0;
                    Double countQuestions1 = 0.0;
                    Double countQuestions2 = 0.0;
                    Double countQuestions3 = 0.0;
                    Double countQuestions4 = 0.0;
                    for (int j = 0; j < questionsRows.size(); j++) {
                        String maxSQL = "";
                        float max = 0;
                        // ماگزیمم ممکن در گزینه ها
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                            maxSQL = "SELECT sum(formQuestionOptions_value*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        }
                        List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                        if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                            max = Float.valueOf(maxRow.get(0).get("max").toString());
                        }

                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                    + ",sum(formQuestionOptions_value*formQuestions_weight) as val,formQuestions_weight,"
                                    + "count(*) as no ,formQuestionOptions_value,hmis_formanswerset.formAnswers_date _date,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " GROUP BY formAnswers_date ASC;"
                                    + ";";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                        + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,hmis_formanswerset.formAnswers_date _date,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " GROUP BY formAnswers_date ASC;"
                                        + ";";
                            }
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS val,hmis_formanswerset.formAnswers_date _date,count(*) AS no,formQuestionOptions_lable"
                                    + "  FROM( "
                                    + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,"
                                    + "formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1 "
                                    + " ORDER BY formAnswers_date ASC;"
                                    + "  ;";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT SUM(sum1) AS val,count(*) AS no,formQuestionOptions_lable  FROM( "
                                        + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,"
                                        + "GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " Group by formanswers_answerSet_id"
                                        + ")t1 "
                                        + " ORDER BY formAnswers_date ASC;"
                                        + "";
                            }
                        }

                        List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInversePeriodRange(answersToOneQuestionRows, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        System.out.println("final_values_date" + final_values_date);
                        Float sum;
                        if (final_values_date.size() > 0) {
                            if (jjNumber.isFloat(final_values_date.get(0).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(0).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(0).get("val").toString());;//مقدار ضربدر تعداد
//                                if (max != 0) {//shiran2 14001021                                 
//                                    valueInPercent11 += (sum * 100) / (max * count);                            
//                                }
//                                     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent11 += (sum * 100) / (max * count);
                                        countQuestions1++;
                                    }
                                } else {
                                    valueInPercent11 += (sum);
                                    countQuestions1 += Double.valueOf(final_values_date.get(0).get("no").toString());
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent11 = valueInPercent11 * 100;
                                        countQuestions1 = countQuestions1 * (int) max;
                                    }
                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }
                            if (jjNumber.isFloat(final_values_date.get(1).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(1).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(1).get("val").toString());;//مقدار ضربدر تعداد
//                                if (max != 0) {//shiran2 14001021                            
//                                    valueInPercent12 += (sum * 100) / (max * count);                           
//                                }
//                                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent12 += (sum * 100) / (max * count);
                                        countQuestions2++;
                                    }
                                } else {
                                    valueInPercent12 += (sum);
                                     countQuestions2 += Double.valueOf(final_values_date.get(1).get("no").toString());                                    
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent12 = valueInPercent12 * 100;
                                        countQuestions2 = countQuestions2 * (int) max;
                                    }
                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }
                            if (jjNumber.isFloat(final_values_date.get(2).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(2).get("no").toString());
//                                countQuestions3 += Double.valueOf(final_values_date.get(2).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(2).get("val").toString());;//مقدار ضربدر تعداد
//                                if (max != 0) {//shiran2 14001021
//                                    valueInPercent13 += (sum * 100) / (max * count);
//                                }
//                             ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent13 += (sum * 100) / (max * count);
                                        countQuestions3++;
                                    }
                                } else {
                                    valueInPercent13 += (sum);
                                    countQuestions3 += Double.valueOf(final_values_date.get(2).get("no").toString());                                    
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent13 = valueInPercent13 * 100;
                                        countQuestions3 = countQuestions3 * (int) max;
                                    }
                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }
                            if (jjNumber.isFloat(final_values_date.get(3).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(3).get("no").toString());
//                                countQuestions4 += Double.valueOf(final_values_date.get(3).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(3).get("val").toString());;//مقدار ضربدر تعداد
//                                if (max != 0) {//shiran2 14001021
//                                    valueInPercent14 += (sum * 100) / (max * count);
//                                }
//                                  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent14 += (sum * 100) / (max * count);
                                        countQuestions4++;
                                    }
                                } else {                                
                                    valueInPercent14 += (sum);
                                     countQuestions4 += Double.valueOf(final_values_date.get(3).get("no").toString());                                    
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent14 = valueInPercent14 * 100;
                                        countQuestions4 = countQuestions4 * (int) max;                                    
                                    }
                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }
                        }
                    }
                    float questionSize = 0;
                    questionSize = questionsRows.size();
//                    System.out.println("countQuestions=" + countQuestions);
                    data11 += "'" + valueInPercent11/countQuestions1 + "',";
                    data12 += "'" + valueInPercent12/countQuestions2 + "',";
                    data13 += "'" + valueInPercent13/countQuestions3 + "',";
                    data14 += "'" + valueInPercent14/countQuestions4 + "',";

                    labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر رنج
                    backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
                }
            }
            System.out.println("/////////////////data///////////////////=");
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \" سه ماهه اول\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه دوم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه سوم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" سه ماهه چهارم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },";
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " "
                    //                    + chartTitleExtraDiscription
                    + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه" + ":" + formRow.get(0).get(Forms._title));
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formCountResult_rangeWithPeriodSixMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            String dateCondition2 = "";

            jjCalendar_IR startDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "range_period"));
            String ansertSetSQL = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            String ansertSetSQL2 = "";// برای اعمال فیلتر با توجه به یک جواب خاص
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String backgroundColorChart = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
                departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + hmis_filter_department_id;
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            String hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range");
            if (!hmis_filter_range.equals("")) {

                hmis_filter_range = jjTools.getParameter(request, "hmis_filter_range").replaceAll(",", "%23A%23");
            } else {
                List<Map<String, Object>> rangeQuestionRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formquestions." + FormQuestions._range + ") SEPARATOR '%23A%23') as rangeQuestion"
                        + " FROM  hmis_formquestions where formQuestions_formID=" + id));
                hmis_filter_range = rangeQuestionRow.get(0).get("rangeQuestion").toString();
            }
            String[] rangeQuestionIdArray = hmis_filter_range.split("%23A%23");
            for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                Float val = null;
                Float val2 = null;

                if (!rangeQuestionIdArray[i].equals("")) {
                    List<Map<String, Object>> questionsRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_formquestions "
                            + "WHERE formQuestions_formID=" + jjTools.getParameter(request, "id")
                            + " AND formQuestions_weight !='0' and (formQuestions_answersType='radio' OR formQuestions_answersType='select_option'"
                            + " OR formQuestions_answersType='checkbox') and ( " + FormQuestions._range + "='" + rangeQuestionIdArray[i] + "')  "
                            + " ;"));
                    Double countQuestions1 = 0.0;
                    Double countQuestions2 = 0.0;
                    float valueInPercent2 = 0;
                    float valueInPercent11 = 0;
                    float valueInPercent12 = 0;
                    for (int j = 0; j < questionsRows.size(); j++) {
                        String maxSQL = "";
                        float max = 0;
                        // ماگزیمم ممکن در گزینه ها
                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            maxSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkBox")) {
                            maxSQL = "SELECT sum(formQuestionOptions_value*formQuestions_weight) as max ,formQuestions_weight,formQuestionOptions_value,formQuestionOptions_lable"
                                    + " FROM `hmis_formquestionoptions`"
                                    + "LEFT JOIN hmis_formquestions ON hmis_formquestionoptions.formQuestionOptions_question_id=hmis_formquestions.id"
                                    + " WHERE formQuestionOptions_question_id=" + questionsRows.get(j).get(FormQuestions._id) + "    ;";
                        }
                        List<Map<String, Object>> maxRow = jjDatabaseWeb.separateRow(db.otherSelect(maxSQL));
                        if (jjNumber.isFloat(maxRow.get(0).get("max").toString())) {
                            max = Float.valueOf(maxRow.get(0).get("max").toString());
                        }

                        if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("radio") || questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("select_option")) {
                            ansertSetSQL = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                    + ",sum(formQuestionOptions_value*formQuestions_weight) as val,formQuestions_weight,"
                                    + "count(*) as no ,formQuestionOptions_value,hmis_formanswerset.formAnswers_date _date,formQuestionOptions_lable "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " GROUP BY formAnswers_date ASC;"
                                    + ";";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max"
                                        + ",sum(formQuestionOptions_value*formQuestions_weight) as sum,hmis_formanswerset.formAnswers_date _date,formQuestions_weight,count(*) as no ,formQuestionOptions_value,formQuestionOptions_lable "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " GROUP BY formAnswers_date ASC;"
                                        + ";";
                            }
                        } else if (questionsRows.get(j).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {
                            ansertSetSQL = "SELECT SUM(sum1) AS val,hmis_formanswerset.formAnswers_date _date,count(*) AS no,formQuestionOptions_lable"
                                    + "  FROM( "
                                    + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,"
                                    + "formQuestions_weight,GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                    + " FROM `hmis_formanswers` a1"
                                    + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                    + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                    + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                    + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                    + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                    + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                    + dateCondition + departmentCondition
                                    + " Group by formanswers_answerSet_id"
                                    + ")t1 "
                                    + " ORDER BY formAnswers_date ASC;"
                                    + "  ;";
                            if (!dateCondition2.isEmpty()) {
                                ansertSetSQL2 = "SELECT SUM(sum1) AS val,count(*) AS no,formQuestionOptions_lable  FROM( "
                                        + "SELECT sum(formQuestionOptions_value*formQuestions_weight) as sum1,formQuestions_weight,"
                                        + "GROUP_CONCAT(formQuestionOptions_lable SEPARATOR ',') as formQuestionOptions_lable  "
                                        + " FROM `hmis_formanswers` a1"
                                        + " LEFT JOIN hmis_formquestionoptions ON hmis_formquestionoptions.id=a1.formanswers_answer"
                                        + " LEFT JOIN hmis_formanswerset ON hmis_formanswerset.id=a1.formanswers_answerSet_id"
                                        + " LEFT JOIN hmis_formquestions ON a1.formanswers_questionId=hmis_formquestions.id"
                                        + " WHERE a1.formanswers_questionId=" + questionsRows.get(j).get(FormQuestions._id)
                                        + " AND formQuestionOptions_value<>'' " // برای جدا کردن غ غ ا ها و آنهایی که مقدار ندارند
                                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "'"
                                        + dateCondition2 + departmentCondition
                                        + " Group by formanswers_answerSet_id"
                                        + ")t1 "
                                        + " ORDER BY formAnswers_date ASC;"
                                        + "";
                            }
                        }

                        List<Map<String, Object>> answersToOneQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(ansertSetSQL));
                        List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInversePeriodRange(answersToOneQuestionRows, startDate.getDBFormat_8length(), endDate.getDBFormat_8length(), periodInDay, "avg");
                        Float sum;
                        if (final_values_date.size() > 0) {
                            if (jjNumber.isFloat(final_values_date.get(0).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(0).get("no").toString());
//                                countQuestions1 += Double.valueOf(final_values_date.get(0).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(0).get("val").toString());;//مقدار ضربدر تعداد
//                                if (max != 0) {//shiran2 14001021
//                                    valueInPercent11 += (sum * 100) / (max * count);
//                                }
//                                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                   if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent11 += (sum * 100) / (max * count);
                                        countQuestions1++;
                                    }
                                } else {                                   
                                    valueInPercent11 += (sum);
                                     countQuestions1 += Double.valueOf(final_values_date.get(0).get("no").toString());                                    
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent11 = valueInPercent11 * 100;
                                        countQuestions1 = countQuestions1 * (int) max;                                      
                                    }
                                }
//                                   / ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                            }
                            if (jjNumber.isFloat(final_values_date.get(1).get("val").toString())) {// اگر مقدار همه ی پاسخ ها غیر قابل ارزیابی نبود  یا خالی نبود
                                float count = Float.parseFloat(final_values_date.get(1).get("no").toString());
//                                countQuestions2 += Double.valueOf(final_values_date.get(1).get("no").toString());
                                sum = Float.parseFloat(final_values_date.get(1).get("val").toString());//مقدار ضربدر تعداد     
//                                if (max != 0) {//shiran2 14001021      
//                                    valueInPercent12 += (sum * 100) / (max * count);
//                                }
//                                       ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                   if (questionsRows.size() == 1) {
                                    if (max != 0) {
                                        valueInPercent12 += (sum * 100) / (max * count);
                                        countQuestions2++;
                                    }
                                } else {                                   
                                    valueInPercent12 += (sum);
                                     countQuestions2 += Double.valueOf(final_values_date.get(1).get("no").toString());                                    
                                    if (j == questionsRows.size() - 1) {// برای آخری  OR نمیخواهیم
                                        valueInPercent12 = valueInPercent12 * 100;
                                        countQuestions2 = countQuestions2 * (int) max;                                      
                                    }
                                }
//                                   / ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            }
                        }
                    }

                    data11 += "'" + valueInPercent11 / countQuestions1 + "',";
                    data12 += "'" + valueInPercent12 / countQuestions2 + "',";

                    labels += " '" + rangeQuestionIdArray[i] + "',";// نام هر رنج
                    backgroundColorChart += "'#" + Plans.generateRandomChars("abcdef1234567", 6) + "',";
                }
            }
            System.out.println("/////////////////data///////////////////=");
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \" شش ماهه اول\",\n"
                    + "            backgroundColor: \"red\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"شش ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n";
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_range.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < rangeQuestionIdArray.length; i++) {
                    chartTitleExtraDiscription += " " + rangeQuestionIdArray[i];
                    if (i < rangeQuestionIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " "
                    //                    + chartTitleExtraDiscription
                    + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار حیطه به حیطه" + ":" + formRow.get(0).get(Forms._title));
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمودار مجموع یا میانگین کل یا یک سوال در بخش های مختلف
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
    public static String formSectionBySection(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
//                return "";
//            } else {
            String id = jjTools.getParameter(request, _id);
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }
            String hmis_filter_formquestion_id = jjTools.getParameter(request, "hmis_filter_formquestion_id");
            String filterCondition = jjTools.getParameter(request, "hmis_filter_formquestionOption_id");
            if (!filterCondition.isEmpty()) {// برای فقط گزینه های انتخاب شده را روی محور افقی  نمودار بیاورد
                filterCondition = " AND (id=" + filterCondition.replaceAll(",", " OR id=") + " )";
            }
            // گزینه های سوال بخش را استخراج می کنیم
            List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + hmis_filter_formquestion_id + filterCondition));
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data2 = "";
            for (int i = 0; i < optionsRow.size(); i++) {
                List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                        + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                        + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                        + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                        + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                        + dateCondition + roleCondition
                        + ""));
                List<Map<String, Object>> answersSetsInOneCondition2 = null;
                if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
                    answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(formanswers_answerSet_id) as str FROM hmis_formanswers "
                            + " LEFT JOIN hmis_formanswerset on formanswers_answerSet_id=hmis_formanswerset.id "
                            + " WHERE formanswers_questionId=" + optionsRow.get(i).get(FormQuestionOptions._question_id) + ""
                            + " AND formanswers_answer=" + optionsRow.get(i).get(FormQuestionOptions._id)
                            + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' "
                            + dateCondition2 + roleCondition
                            + ""));
                }

                String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                String sql = "";
                String sql2 = "";//برای مقایسه
                String questionId = jjTools.getParameter(request, "axel_y");
                // تعیین محور افقی----------------------------------------------------------------
                System.out.println(")))))))))))))))))))))))))))>>");
                if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                    axel_y = "COUNT(hmis_formanswerset.id)";
                    sql = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                            + " WHERE " + " ( hmis_formanswerset.id="
                            + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        sql2 = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                    }
                } else if (questionId.equals("avg")) {
                    String range = request.getParameter("formQuestions_range");
                    if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                        sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val  FROM"
                                + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                + " ) AS t2"
                                + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4";
                        }
                    } else {
                        //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                        sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        }
                    }
                } else if (questionId.equals("sum")) {
                    axel_y = "sum(hmis_formanswerset_sum)";
                    sql = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                            + " WHERE " + " ( hmis_formanswerset.id="
                            + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        sql2 = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                    }
                } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                    questionId = questionId.replaceAll("avg-", "");
                    // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                    List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                    String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                            + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id  WHERE   (hmis_formquestions.id=" + questionId + ");";
                    if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                        maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id  WHERE   (hmis_formquestions.id=" + questionId + ");";
                    };
                    List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                    maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                    if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                        sql = "SELECT avg(formanswers_answer) as val FROM hmis_formanswers "
                                + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                + " AND (formanswers_questionId=" + questionId + " )";
                    } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                        sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,count(*) as no FROM ("
                                + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable,sum(formQuestionOptions_value) as sum1 "
                                + " FROM hmis_formanswers "
                                + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                + " WHERE "
                                + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                + " AND (formanswers_questionId=" + questionId + " AND "
                                + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                + "group by formanswers_answerSet_id)"
                                + " t1";
                    }
                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql2 = "SELECT avg(formanswers_answer) as val FROM hmis_formanswers "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val FROM ("
                                    + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable,sum(formQuestionOptions_value) as sum1 "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + " group by formanswers_answerSet_id)"
                                    + " t1";
                        }
                    }
                } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                    questionId = questionId.replaceAll("sum-", "");
                    List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                    if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                        sql = "SELECT sum(formanswers_answer) as val FROM hmis_formanswers "
                                + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                + " AND (formanswers_questionId=" + questionId + " )";
                    } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                        sql = "SELECT sum(formQuestionOptions_value) as val FROM hmis_formanswers "
                                + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                + " WHERE "
                                + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                + " AND (formanswers_questionId=" + questionId + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) ";
                    }
                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
                            sql2 = "SELECT sum(formanswers_answer) as val FROM hmis_formanswers "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql2 = "SELECT sum(formQuestionOptions_value) as val FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) ";
                        }
                    }
                }

                List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                labels += " '" + (optionsRow.get(i).get(FormQuestionOptions._lable) == null ? "نامشخص" : optionsRow.get(i).get(FormQuestionOptions._lable)) + "',";// نام هر آپشن
                String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
                data += "'" + val + "',";
//                maxOfPossible += jjNumber.isFloat(val) ? Float.valueOf(val) : 0;
                if (!dateCondition2.isEmpty()) {//برای مقایسه
                    List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
                    String val2 = results2.get(0).get("val").toString();// 
                    data2 += "'" + val2 + "',";
                }
            }
//                if (!jjNumber.isDigit(id)) {
            //                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
            //                    return "";
            //                }
            //            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + "label: '" + jjTools.getParameter(request, "formAnswers_date_from") + "-" + jjTools.getParameter(request, "formAnswers_date_to") + "',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: '#5B93D3'"
                    + "}";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += ",{"
                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
                        + "data: ["
                        + data2
                        + "],"
                        + "backgroundColor: 'red' "
                        + "}";

            }
            String chartTitleExtraDiscription = "";
            if (!jjTools.getParameter(request, "hmis_filter_formquestionOption_id").isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, "id=" + jjTools.getParameter(request, "hmis_filter_formquestion_id")));
                chartTitleExtraDiscription += questionRow.get(0).get(FormQuestions._title);
                List<Map<String, Object>> optionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._lable,
                        " id=" + jjTools.getParameter(request, "hmis_filter_formquestionOption_id").replaceAll(",", " OR id=")));
                for (int i = 0; i < optionRows.size(); i++) {
                    chartTitleExtraDiscription += " " + optionRows.get(i).get(FormQuestionOptions._lable);
                    if (i < optionRows.size() - 1) {// برای اینکه اخری را کاما نگذارد
                        chartTitleExtraDiscription += " و ";
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: '" + formRow.get(0).get(Forms._title) + " " + chartTitleExtraDiscription + "'"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.setAttribute("ReportTitle", "نمودار بخش به بخش");
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formSectionBySection2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
//                return "";
//            } else {
            String id = jjTools.getParameter(request, _id);
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            for (int i = 0; i < departmentsIdArray.length; i++) {
                if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                    departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + departmentsIdArray[i];

                    List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                            + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                            + dateCondition + roleCondition + departmentCondition
                            + ""));
                    List<Map<String, Object>> answersSetsInOneCondition2 = null;
                    if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
                        answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                                + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                                + dateCondition2 + roleCondition + departmentCondition
                                + ""));
                    }

                    String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                    String sql = "";
                    String sql2 = "";//برای مقایسه
                    String questionId = jjTools.getParameter(request, "axel_y");
                    // تعیین محور افقی----------------------------------------------------------------
                    System.out.println(")))))))))))))))))))))))))))>>");
                    if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                        axel_y = "COUNT(hmis_formanswerset.id)";
                        sql = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        }
                    } else if (questionId.equals("avg")) {
                        String range = request.getParameter("formQuestions_range");
                        if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                            sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val  FROM"
                                        + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                        + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                        + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                        + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                        + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                        + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " ) AS t2"
                                        + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
                                        + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4";
                            }
                        } else {
                            //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                            sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val FROM hmis_formanswerset "
                                        + " WHERE " + " ( hmis_formanswerset.id="
                                        + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                            }
                        }
                    } else if (questionId.equals("sum")) {
                        axel_y = "sum(hmis_formanswerset_sum)";
                        sql = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")";
                        }
                    } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                        questionId = questionId.replaceAll("avg-", "");
                        // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                            maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                    + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        };
                        List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                        maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT avg(formanswers_answer) as val FROM hmis_formanswers "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,count(*) as no FROM ("
                                    + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable,sum(formQuestionOptions_value) as sum1 "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + "group by formanswers_answerSet_id)"
                                    + " t1";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                                sql2 = "SELECT avg(formanswers_answer) as val FROM hmis_formanswers "
                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " )";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val FROM ("
                                        + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable,sum(formQuestionOptions_value) as sum1 "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " AND "
                                        + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                        + " group by formanswers_answerSet_id)"
                                        + " t1";
                            }
                        }
                    } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                        questionId = questionId.replaceAll("sum-", "");
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT sum(formanswers_answer) as val FROM hmis_formanswers "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(formQuestionOptions_value) as val FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) ";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
                                sql2 = "SELECT sum(formanswers_answer) as val FROM hmis_formanswers "
                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " )";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(formQuestionOptions_value) as val FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) ";
                            }
                        }
                    }

                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    labels += " '" + Department.getDepartmentName(departmentsIdArray[i], db) + "',";// نام هر بخش
                    String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
                    data += "'" + val + "',";
//                maxOfPossible += jjNumber.isFloat(val) ? Float.valueOf(val) : 0;
                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
                        String val2 = results2.get(0).get("val").toString();// 
                        data2 += "'" + val2 + "',";
                    }
                }
            }
//                if (!jjNumber.isDigit(id)) {
            //                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
            //                    return "";
            //                }
            //            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + "label: '" + jjTools.getParameter(request, "formAnswers_date_from") + "-" + jjTools.getParameter(request, "formAnswers_date_to") + "',"
                    + "data: ["
                    + data
                    + "],"
                    + "backgroundColor: '#5B93D3'"
                    + "}";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += ",{"
                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
                        + "data: ["
                        + data2
                        + "],"
                        + "backgroundColor: 'red' "
                        + "}";

            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                        chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[i], db);
                        if (i < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                            chartTitleExtraDiscription += " - ";
                        }
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['" + formRow.get(0).get(Forms._title) + "' , '" + chartTitleExtraDiscription + "'],"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    //                    + "min: Math.min.apply(this,data_array) -5,"
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "نمودار بخش به بخش" + ":" + formRow.get(0).get(Forms._title));
            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تغییرات جدید
     *
     * فصل به فصل کردن نمودار ها سه ماه اول سه ماه دوم سه ماه سوم
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formSectionBySectionWithPeriodThreeMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "formAnswers_period"));
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }

            jjCalendar_IR periodDate = new jjCalendar_IR(String.valueOf(jjTools.getParameter(request, "formAnswers_date_from")));

            ///////////////////////////////////////////
            jjCalendar_IR startDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));

            ///////////////////////////////////////////
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            for (int i = 0; i < departmentsIdArray.length; i++) {
                if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                    departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + departmentsIdArray[i];
                    List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                            + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                            + dateCondition + roleCondition + departmentCondition
                            + ""));
                    List<Map<String, Object>> answersSetsInOneCondition2 = null;
                    if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
                        answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                                + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                                + dateCondition2 + roleCondition + departmentCondition
                                + ""));
                    }

                    String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                    String sql = "";
                    String sql2 = "";//برای مقایسه
                    String questionId = jjTools.getParameter(request, "axel_y");
                    // تعیین محور افقی----------------------------------------------------------------
                    System.out.println(")))))))))))))))))))))))))))>>");
                    if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                        axel_y = "COUNT(hmis_formanswerset.id)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date  FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val ,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                        }
                    } else if (questionId.equals("avg")) {
                        String range = request.getParameter("formQuestions_range");
                        if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                            sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4 "
                                    + " order by formAnswers_date ASC";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                        + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                        + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                        + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                        + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                        + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                        + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " ) AS t2"
                                        + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
                                        + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4"
                                        + " order by formAnswers_date ASC;";
                            }
                        } else {
                            //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                            sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                        + " WHERE " + " ( hmis_formanswerset.id="
                                        + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                        + " GROUP BY formAnswers_date ASC;";
                            }
                        }
                    } else if (questionId.equals("sum")) {
                        axel_y = "sum(hmis_formanswerset_sum)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                        }
                    } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                        questionId = questionId.replaceAll("avg-", "");
                        // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id"
                                + "  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                            maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                    + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id "
                                    + " WHERE   (hmis_formquestions.id=" + questionId + ");";
                        };
                        List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                        maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " ) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val"
                                    + ",_date"
                                    + ",count(*) as no FROM ("
                                    + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                    + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + "group by formanswers_answerSet_id)"
                                    + " t1 "
                                    + " GROUP BY _date ASC;";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                                sql2 = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + "  WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " ) "
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,_date FROM ("
                                        + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                        + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " AND "
                                        + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                        + " group by formanswers_answerSet_id)"
                                        + " t1 "
                                        + " GROUP BY _date ASC;";
                            }
                        }
                    } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                        questionId = questionId.replaceAll("sum-", "");
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT sum(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date"
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )"
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(formQuestionOptions_value) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId
                                    + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
                                sql2 = "SELECT sum(formanswers_answer) as val"
                                        + ",hmis_formanswerset.formAnswers_date  _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " )"
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(formQuestionOptions_value) as val"
                                        + ",hmis_formanswerset.formAnswers_date  _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId
                                        + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            }
                        }
                    }

                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
//                    jjTools.getParameter(request, "axel_y").startsWith("avg")
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
                    List<Map<String, Object>> FormFinalResultRow = new ArrayList<>();
                    labels += " '" + Department.getDepartmentName(departmentsIdArray[i], db) + "',";// نام هر بخش
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate1.getDBFormat_8length(), endDate1.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
//                        for (int k = 0; k < final_values_date.size(); k++) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
//                        }  
                    }
//                    String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
//                    data += "'" + val + "',";

                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
                        String val2 = results2.get(0).get("val").toString();// 
                        data2 += "'" + val2 + "',";
                    }
                }
            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \"سه ماهه اول\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه دوم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"سه ماهه سوم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },"
                    + "          {\n"
                    + "            label: \"سه ماهه چهارم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },";
//                    + "],"
//                    + "backgroundColor: '#5B93D3'"
//                    + "}";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += ",{"
                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
                        + "data: ["
                        + data2
                        + "],"
                        + "backgroundColor: 'red' "
                        + "}";

            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                        chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[i], db);
                        if (i < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                            chartTitleExtraDiscription += " - ";
                        }
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['" + formRow.get(0).get(Forms._title) + "' , '" + chartTitleExtraDiscription + "'],"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "نمودار بخش به بخش" + ":" + formRow.get(0).get(Forms._title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            System.out.println("/////////////////////////template/charts2.jsp");    
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String formSectionBySectionWithPeriodSixMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "formAnswers_period"));
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }

            jjCalendar_IR periodDate = new jjCalendar_IR(String.valueOf(jjTools.getParameter(request, "formAnswers_date_from")));

            ///////////////////////////////////////////
            jjCalendar_IR startDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));

            ///////////////////////////////////////////
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            String dateCondition2 = "";
            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
//            String data13 = "";
//            String data14 = "";
            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            for (int i = 0; i < departmentsIdArray.length; i++) {
                if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                    departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + departmentsIdArray[i];
                    List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                            + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                            + dateCondition + roleCondition + departmentCondition
                            + ""));
                    List<Map<String, Object>> answersSetsInOneCondition2 = null;
                    if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
                        answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                                + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                                + dateCondition2 + roleCondition + departmentCondition
                                + ""));
                    }

                    String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                    String sql = "";
                    String sql2 = "";//برای مقایسه
                    String questionId = jjTools.getParameter(request, "axel_y");
                    // تعیین محور افقی----------------------------------------------------------------
                    System.out.println(")))))))))))))))))))))))))))>>");
                    if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                        axel_y = "COUNT(hmis_formanswerset.id)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date  FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val ,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                        }
                    } else if (questionId.equals("avg")) {
                        String range = request.getParameter("formQuestions_range");
                        if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                            sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4 "
                                    + " order by formAnswers_date ASC";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                        + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                        + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                        + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                        + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                        + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                        + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " ) AS t2"
                                        + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
                                        + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4"
                                        + " order by formAnswers_date ASC;";
                            }
                        } else {
                            //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                            sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                            if (!dateCondition2.isEmpty()) {//برای مقایسه
                                sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                        + " WHERE " + " ( hmis_formanswerset.id="
                                        + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                        + " GROUP BY formAnswers_date ASC;";
                            }
                        }
                    } else if (questionId.equals("sum")) {
                        axel_y = "sum(hmis_formanswerset_sum)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            sql2 = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
                        }
                    } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                        questionId = questionId.replaceAll("avg-", "");
                        // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id"
                                + "  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                            maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                    + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id "
                                    + " WHERE   (hmis_formquestions.id=" + questionId + ");";
                        };
                        List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                        maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " ) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val"
                                    + ",_date"
                                    + ",count(*) as no FROM ("
                                    + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                    + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + "group by formanswers_answerSet_id)"
                                    + " t1 "
                                    + " GROUP BY _date ASC;";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                                sql2 = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + "  WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " ) "
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,_date FROM ("
                                        + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                        + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " AND "
                                        + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                        + " group by formanswers_answerSet_id)"
                                        + " t1 "
                                        + " GROUP BY _date ASC;";
                            }
                        }
                    } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                        questionId = questionId.replaceAll("sum-", "");
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT sum(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date"
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )"
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(formQuestionOptions_value) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId
                                    + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        }
                        if (!dateCondition2.isEmpty()) {//برای مقایسه
                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
                                sql2 = "SELECT sum(formanswers_answer) as val"
                                        + ",hmis_formanswerset.formAnswers_date  _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId + " )"
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                                sql2 = "SELECT sum(formQuestionOptions_value) as val"
                                        + ",hmis_formanswerset.formAnswers_date  _date "
                                        + " FROM hmis_formanswers "
                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                        + " WHERE "
                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                        + " AND (formanswers_questionId=" + questionId
                                        + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                            }
                        }
                    }

                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
//                    jjTools.getParameter(request, "axel_y").startsWith("avg")
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
                    List<Map<String, Object>> FormFinalResultRow = new ArrayList<>();
                    labels += " '" + Department.getDepartmentName(departmentsIdArray[i], db) + "',";// نام هر بخش
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate1.getDBFormat_8length(), endDate1.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
//                        for (int k = 0; k < final_values_date.size(); k++) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
//                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
//                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
//                        }  
                    }
//                    String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
//                    data += "'" + val + "',";

                    if (!dateCondition2.isEmpty()) {//برای مقایسه
                        List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
                        String val2 = results2.get(0).get("val").toString();// 
                        data2 += "'" + val2 + "',";
                    }
                }
            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \"شش ماهه اول\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"شش ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n";
            if (!dateCondition2.isEmpty()) {// اگر داده های مقایسه ای نداشتیم ستون اضافی هم ایحاد نکنیم
                script
                        += ",{"
                        + "label: '" + jjTools.getParameter(request, "formAnswers_date_from_c") + "-" + jjTools.getParameter(request, "formAnswers_date_to_c") + "',"
                        + "data: ["
                        + data2
                        + "],"
                        + "backgroundColor: 'red' "
                        + "}";

            }
            String chartTitleExtraDiscription = "";
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                        chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[i], db);
                        if (i < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                            chartTitleExtraDiscription += " - ";
                        }
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['" + formRow.get(0).get(Forms._title) + "' , '" + chartTitleExtraDiscription + "'],"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "نمودار بخش به بخش" + ":" + formRow.get(0).get(Forms._title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            System.out.println("/////////////////////////template/charts2.jsp");    
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * گزارش بخش به بخش به صورت دوماهه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formSectionBySectionWithPeriodTwoMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "formAnswers_period"));
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }

            jjCalendar_IR periodDate = new jjCalendar_IR(String.valueOf(jjTools.getParameter(request, "formAnswers_date_from")));
//            periodDate.setDate(periodDate.getYear(), 1, 1);//تاریخ پایان بشود تا اول سال جاری                     

            ///////////////////////////////////////////
            jjCalendar_IR startDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));

            ///////////////////////////////////////////
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
//            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            String dateCondition2 = "";
//            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";
            String data15 = "";
            String data16 = "";

            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            for (int i = 0; i < departmentsIdArray.length; i++) {
                if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                    departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + departmentsIdArray[i];
                    List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                            + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                            + dateCondition + roleCondition + departmentCondition
                            + ""));
                    List<Map<String, Object>> answersSetsInOneCondition2 = null;
//                    if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
//                        answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
//                                + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
//                                + dateCondition2 + roleCondition + departmentCondition
//                                + ""));
//                    }

                    String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                    String sql = "";
                    String sql2 = "";//برای مقایسه
                    String questionId = jjTools.getParameter(request, "axel_y");
                    // تعیین محور افقی----------------------------------------------------------------
                    System.out.println(")))))))))))))))))))))))))))>>");
                    if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                        axel_y = "COUNT(hmis_formanswerset.id)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date  FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            sql2 = "SELECT " + axel_y + "as val ,formAnswers_date  _date FROM hmis_formanswerset "
//                                    + " WHERE " + " ( hmis_formanswerset.id="
//                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                    + " GROUP BY formAnswers_date ASC;";
//                        }
                    } else if (questionId.equals("avg")) {
                        String range = request.getParameter("formQuestions_range");
                        if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                            sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4 "
                                    + " order by formAnswers_date ASC";
//                            if (!dateCondition2.isEmpty()) {//برای مقایسه
//                                sql2 = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
//                                        + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
//                                        + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
//                                        + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
//                                        + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
//                                        + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
//                                        + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " ) AS t2"
//                                        + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
//                                        + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4"
//                                        + " order by formAnswers_date ASC;";
//                            }
                        } else {
                            //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                            sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
//                            if (!dateCondition2.isEmpty()) {//برای مقایسه
//                                sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
//                                        + " WHERE " + " ( hmis_formanswerset.id="
//                                        + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                        + " GROUP BY formAnswers_date ASC;";
//                            }
                        }
                    } else if (questionId.equals("sum")) {
                        axel_y = "sum(hmis_formanswerset_sum)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            sql2 = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
//                                    + " WHERE " + " ( hmis_formanswerset.id="
//                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                    + " GROUP BY formAnswers_date ASC;";
//                        }
                    } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                        questionId = questionId.replaceAll("avg-", "");
                        // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id"
                                + "  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                            maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                    + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id "
                                    + " WHERE   (hmis_formquestions.id=" + questionId + ");";
                        };
                        List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                        maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " ) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val"
                                    + ",_date"
                                    + ",count(*) as no FROM ("
                                    + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                    + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + "group by formanswers_answerSet_id)"
                                    + " t1 "
                                    + " GROUP BY _date ASC;";
                        }
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
//                                sql2 = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + "  WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " ) "
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
//                                sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,_date FROM ("
//                                        + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
//                                        + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + " WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " AND "
//                                        + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
//                                        + " group by formanswers_answerSet_id)"
//                                        + " t1 "
//                                        + " GROUP BY _date ASC;";
//                            }
//                        }
                    } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                        questionId = questionId.replaceAll("sum-", "");
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT sum(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date"
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )"
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(formQuestionOptions_value) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId
                                    + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        }
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
//                                sql2 = "SELECT sum(formanswers_answer) as val"
//                                        + ",hmis_formanswerset.formAnswers_date  _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " )"
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
//                                sql2 = "SELECT sum(formQuestionOptions_value) as val"
//                                        + ",hmis_formanswerset.formAnswers_date  _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + " WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId
//                                        + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            }
//                        }
                    }

                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
//                    jjTools.getParameter(request, "axel_y").startsWith("avg")
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
                    List<Map<String, Object>> FormFinalResultRow = new ArrayList<>();
                    labels += " '" + Department.getDepartmentName(departmentsIdArray[i], db) + "',";// نام هر بخش
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate1.getDBFormat_8length(), endDate1.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
//                        for (int k = 0; k < final_values_date.size(); k++) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
                        data15 += "'" + final_values_date.get(4).get("val").toString() + "',";
                        data16 += "'" + final_values_date.get(5).get("val").toString() + "',";
//                        }  
                    }
//                    String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
//                    data += "'" + val + "',";

//                    if (!dateCondition2.isEmpty()) {//برای مقایسه
//                        List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
//                        String val2 = results2.get(0).get("val").toString();// 
//                        data2 += "'" + val2 + "',";
//                    }
                }
            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \"دو ماهه اول\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه دوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه سوم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه چهارم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه پنجم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data15 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"دو ماهه ششم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data16 + "],"
                    + "          },\n";

            String chartTitleExtraDiscription = "";
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                        chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[i], db);
                        if (i < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                            chartTitleExtraDiscription += " - ";
                        }
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['" + formRow.get(0).get(Forms._title) + "' , '" + chartTitleExtraDiscription + "'],"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "نمودار بخش به بخش" + ":" + formRow.get(0).get(Forms._title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            System.out.println("/////////////////////////template/charts2.jsp");    
            Server.outPrinter(request, response, script);
            return script;
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * گزارش بخش به بخش ماهیانه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String formSectionBySectionWithPeriodOneMounth(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            int periodInDay = Integer.valueOf(jjTools.getParameter(request, "formAnswers_period"));
            String userRole = jjTools.getParameter(request, "formAnswers_userRole");
            String roleCondition = "";
            if (!userRole.isEmpty()) {//فیلتر نقش 
                roleCondition = " AND  (" + FormAnswerSet._userRole + "=" + userRole + ")";
            }

            jjCalendar_IR periodDate = new jjCalendar_IR(String.valueOf(jjTools.getParameter(request, "formAnswers_date_from")));
//            periodDate.setDate(periodDate.getYear(), 1, 1);//تاریخ پایان بشود تا اول سال جاری                     

            ///////////////////////////////////////////
            jjCalendar_IR startDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", ""));
            jjCalendar_IR endDate1 = new jjCalendar_IR(jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", ""));

            ///////////////////////////////////////////
            String departmentCondition = "";
            float maxOfPossible = 0; // برای نمایش حداکثر ممکن نمودار
            String dateCondition = "";// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
//            if (!jjTools.getParameter(request, "formAnswers_date_from").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to").isEmpty()) {
//                dateCondition += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            String dateCondition2 = "";
//            if (!jjTools.getParameter(request, "formAnswers_date_from_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + ">=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_from_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
//            if (!jjTools.getParameter(request, "formAnswers_date_to_c").isEmpty()) {
//                dateCondition2 += " AND " + FormAnswerSet._date + "<=" + jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, "formAnswers_date_to_c"), false) + " ";//تاریخ را بصورت عددی در میآوریم که راحت مقایسه بشود
//            }
            System.out.println("roleCondition : " + roleCondition);
            String labels = "";//برای ایجاد آرایه در چارت جی اس
            String data = "";
            String data11 = "";
            String data12 = "";
            String data13 = "";
            String data14 = "";
            String data15 = "";
            String data16 = "";
            String data17 = "";
            String data18 = "";
            String data19 = "";
            String data20 = "";
            String data21 = "";
            String data22 = "";

            String data2 = "";
            String hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            if (!hmis_filter_department_id.equals("")) {
                hmis_filter_department_id = jjTools.getParameter(request, "hmis_filter_department_id");
            } else {
                List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
                        + "  GROUP_CONCAT(DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + ") SEPARATOR ',') as departmentId"
                        + " FROM  hmis_formanswerset where formAnswers_formId=" + id));
                hmis_filter_department_id = departmentRow.get(0).get("departmentId").toString();
            }
            String[] departmentsIdArray = hmis_filter_department_id.split(",");
            for (int i = 0; i < departmentsIdArray.length; i++) {
                if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                    departmentCondition = " AND " + FormAnswerSet._departmentId + "=" + departmentsIdArray[i];
                    List<Map<String, Object>> answersSetsInOneCondition = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
                            + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
                            + dateCondition + roleCondition + departmentCondition
                            + ""));
                    List<Map<String, Object>> answersSetsInOneCondition2 = null;
//                    if (!dateCondition2.isEmpty()) {// اگر بازه برای مقایسه هم داشتیم فرم ها را استخراج می کنیم
//                        answersSetsInOneCondition2 = jjDatabaseWeb.separateRow(db.otherSelect("SELECT GROUP_CONCAT(hmis_formanswerset.id) as str FROM hmis_formanswerset "
//                                + " WHERE  formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' AND formAnswers_formId= " + id + " "
//                                + dateCondition2 + roleCondition + departmentCondition
//                                + ""));
//                    }

                    String axel_y = "";// آنچه کاربر می خواهد؛ مثلا تعداد فرم ها یا مجموع امتیازات هر کاربر یا میانگین
                    String sql = "";
                    String sql2 = "";//برای مقایسه
                    String questionId = jjTools.getParameter(request, "axel_y");
                    // تعیین محور افقی----------------------------------------------------------------
                    System.out.println(")))))))))))))))))))))))))))>>");
                    if (questionId.equals("COUNT(hmis_formanswerset.id)")) {
                        axel_y = "COUNT(hmis_formanswerset.id)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date  FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            sql2 = "SELECT " + axel_y + "as val ,formAnswers_date  _date FROM hmis_formanswerset "
//                                    + " WHERE " + " ( hmis_formanswerset.id="
//                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                    + " GROUP BY formAnswers_date ASC;";
//                        }
                    } else if (questionId.equals("avg")) {
                        String range = request.getParameter("formQuestions_range");
                        if (!range.isEmpty()) {// اگر گزارش برای میانگین درصد پاسخگویی باشد و یک حیطه ی خاص از سوالات را بخواهیم محاسبه کنیم
                            sql = "SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
                                    + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
                                    + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
                                    + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
                                    + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
                                    + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
                                    + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " ) AS t2"
                                    + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3"
                                    + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4 "
                                    + " order by formAnswers_date ASC";
//                            if (!dateCondition2.isEmpty()) {//برای مقایسه
//                                sql2 = " SELECT t4.*,sum(val1),sum(maxPossibleVal),100*sum(val1)/sum(maxPossibleVal) AS val,formAnswers_date  _date  FROM"
//                                        + " (SELECT t3.*,IF(anserVal='','',max(CAST(formQuestionOptions_value AS SIGNED))) as maxPossibleVal,IF(anserVal='','',anserVal*formQuestions_weight) AS val1 FROM"
//                                        + " (SELECT t2.*,formQuestionOptions_value as anserVal FROM"
//                                        + " (SELECT t1.*,formQuestions_weight from hmis_formanswers t1"
//                                        + " LEFT JOIN hmis_formquestions on hmis_formquestions.id=formanswers_questionId"
//                                        + " WHERE formQuestions_formID=" + id + " and formQuestions_range='" + range + "' "
//                                        + " AND ( formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " ) AS t2"
//                                        + " LEFT JOIN  hmis_formquestionoptions ON hmis_formquestionoptions.id= formanswers_answer) AS t3 "
//                                        + " LEFT JOIN hmis_formquestionoptions ON formanswers_questionId= formQuestionOptions_question_id group by t3.id) AS t4"
//                                        + " order by formAnswers_date ASC;";
//                            }
                        } else {
                            //محموع و ماکزیمم هر فرم را در دیتابیس داریم و بین درصد پاسخگویی ها میانگین میگیریم
                            sql = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
                                    + " WHERE " + " ( hmis_formanswerset.id="
                                    + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                    + " GROUP BY formAnswers_date ASC;";
//                            if (!dateCondition2.isEmpty()) {//برای مقایسه
//                                sql2 = "SELECT avg((hmis_formanswerset_sum)/hmis_formanswerset_avg*100)" + " as val,formAnswers_date  _date FROM hmis_formanswerset "
//                                        + " WHERE " + " ( hmis_formanswerset.id="
//                                        + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                        + " GROUP BY formAnswers_date ASC;";
//                            }
                        }
                    } else if (questionId.equals("sum")) {
                        axel_y = "sum(hmis_formanswerset_sum)";
                        sql = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
                                + " WHERE " + " ( hmis_formanswerset.id="
                                + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
                                + " GROUP BY formAnswers_date ASC;";
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            sql2 = "SELECT " + axel_y + "as val,formAnswers_date  _date FROM hmis_formanswerset "
//                                    + " WHERE " + " ( hmis_formanswerset.id="
//                                    + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR hmis_formanswerset.id=")) + ")"
//                                    + " GROUP BY formAnswers_date ASC;";
//                        }
                    } else if (questionId.startsWith("avg-")) {// اگر در ریکودئست رسیده درخواست برای محاسبه ی  میانگین برای یک سوال بود 
                        questionId = questionId.replaceAll("avg-", "");
                        // در حالتی که میانگین را میخواهیم باید حداکثر مقدار ممکن را بصورت هط راهنما نشان بدهیم 
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        String maxPossibleSql = "SELECT max(CAST(formQuestionOptions_value AS SIGNED)) as max FROM hmis_formquestions"
                                + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id"
                                + "  WHERE   (hmis_formquestions.id=" + questionId + ");";
                        if (questionRow.get(0).get(FormQuestions._answersType).toString().equalsIgnoreCase("checkbox")) {// اگر چک باکس باشد ماکزیمم باید با جمع ارزش گزینه ها بدست بیاید
                            maxPossibleSql = "SELECT sum(formQuestionOptions_value) as max FROM hmis_formquestions"
                                    + " LEFT JOIN hmis_formquestionoptions on hmis_formquestions.id=formQuestionOptions_question_id "
                                    + " WHERE   (hmis_formquestions.id=" + questionId + ");";
                        };
                        List<Map<String, Object>> max = jjDatabaseWeb.separateRow(db.otherSelect(maxPossibleSql));
                        maxOfPossible = jjNumber.isFloat(max.get(0).get("max").toString()) ? Float.valueOf(max.get(0).get("max").toString()) : 0;
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " ) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val"
                                    + ",_date"
                                    + ",count(*) as no FROM ("
                                    + "SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
                                    + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " AND "
                                    + "concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
                                    + "group by formanswers_answerSet_id)"
                                    + " t1 "
                                    + " GROUP BY _date ASC;";
                        }
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
//                                sql2 = "SELECT avg(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + "  WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " ) "
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
//                                sql2 = "SELECT sum(sum1)/(count(*) *" + maxOfPossible + ")*100 as val,_date FROM ("
//                                        + " SELECT formanswers_answerSet_id,formQuestionOptions_value,formQuestionOptions_lable"
//                                        + ",sum(formQuestionOptions_value) as sum1 ,hmis_formanswerset.formAnswers_date as _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + " WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " AND "
//                                        + " concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "// برای جلوگیری از محاسبه ی غ غ ا در تعداد وقتی 
//                                        + " group by formanswers_answerSet_id)"
//                                        + " t1 "
//                                        + " GROUP BY _date ASC;";
//                            }
//                        }
                    } else if (questionId.startsWith("sum-")) {// اگر در ریکودئست آی دی سوال بود 
                        questionId = questionId.replaceAll("sum-", "");
                        List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._id + "=" + questionId));
                        if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی
                            sql = "SELECT sum(formanswers_answer) as val,hmis_formanswerset.formAnswers_date  _date"
                                    + " FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId + " )"
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
                            sql = "SELECT sum(formQuestionOptions_value) as val,hmis_formanswerset.formAnswers_date  _date FROM hmis_formanswers "
                                    + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
                                    + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
                                    + " WHERE "
                                    + " (formanswers_answerSet_id=" + (answersSetsInOneCondition.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
                                    + " AND (formanswers_questionId=" + questionId
                                    + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
                                    + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
                        }
//                        if (!dateCondition2.isEmpty()) {//برای مقایسه
//                            if (questionRow.get(0).get(FormQuestions._answersType).equals("number")) {//برای سوال های عددی    
//                                sql2 = "SELECT sum(formanswers_answer) as val"
//                                        + ",hmis_formanswerset.formAnswers_date  _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + "  WHERE  (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId + " )"
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            } else if (questionRow.get(0).get(FormQuestions._answersType).equals("radio") || questionRow.get(0).get(FormQuestions._answersType).equals("checkbox") || questionRow.get(0).get(FormQuestions._answersType).equals("select_option")) {
//                                sql2 = "SELECT sum(formQuestionOptions_value) as val"
//                                        + ",hmis_formanswerset.formAnswers_date  _date "
//                                        + " FROM hmis_formanswers "
//                                        + " LEFT JOIN hmis_formquestionoptions on formanswers_answer=hmis_formquestionoptions.id "// فعلا فقط مواردی که آپشن دارند را مقایسه می کند
//                                        + " LEFT JOIN hmis_formanswerset ON formanswers_answerSet_id=hmis_formanswerset.id "
//                                        + " WHERE "
//                                        + " (formanswers_answerSet_id=" + (answersSetsInOneCondition2.get(0).get("str").toString().isEmpty() ? "0" : answersSetsInOneCondition2.get(0).get("str").toString().replaceAll(",", " OR formanswers_answerSet_id=")) + ")"
//                                        + " AND (formanswers_questionId=" + questionId
//                                        + " AND concat('',formQuestionOptions_value * 1) = formQuestionOptions_value) "
//                                        + " GROUP BY hmis_formanswerset.formAnswers_date ASC;";
//                            }
//                        }
                    }

                    List<Map<String, Object>> results = jjDatabaseWeb.separateRow(db.otherSelect(sql));
//                    jjTools.getParameter(request, "axel_y").startsWith("avg")
                    String colectionType = jjTools.getParameter(request, "axel_y").startsWith("avg") ? "avg" : "sum";//برای مشخص کردن اینکه مجموع را میخواهیم یا میانگین در یک بازه که به متد بعدی بدهیم
                    List<Map<String, Object>> FormFinalResultRow = new ArrayList<>();
                    labels += " '" + Department.getDepartmentName(departmentsIdArray[i], db) + "',";// نام هر بخش
                    List<Map<String, Object>> final_values_date = Forms.createDataSetForFormsInverse(results, startDate1.getDBFormat_8length(), endDate1.getDBFormat_8length(), periodInDay, colectionType);
                    if (final_values_date.size() > 0) {
//                        for (int k = 0; k < final_values_date.size(); k++) {
                        data11 += "'" + final_values_date.get(0).get("val").toString() + "',";
                        data12 += "'" + final_values_date.get(1).get("val").toString() + "',";
                        data13 += "'" + final_values_date.get(2).get("val").toString() + "',";
                        data14 += "'" + final_values_date.get(3).get("val").toString() + "',";
                        data15 += "'" + final_values_date.get(4).get("val").toString() + "',";
                        data16 += "'" + final_values_date.get(5).get("val").toString() + "',";
                        data17 += "'" + final_values_date.get(6).get("val").toString() + "',";
                        data18 += "'" + final_values_date.get(7).get("val").toString() + "',";
                        data19 += "'" + final_values_date.get(8).get("val").toString() + "',";
                        data20 += "'" + final_values_date.get(9).get("val").toString() + "',";
                        data21 += "'" + final_values_date.get(10).get("val").toString() + "',";
                        data22 += "'" + final_values_date.get(11).get("val").toString() + "',";
//                        }  
                    }
//                    String val = results.get(0).get("val").toString().isEmpty() ? "0" : results.get(0).get("val").toString();// 
//                    data += "'" + val + "',";

//                    if (!dateCondition2.isEmpty()) {//برای مقایسه
//                        List<Map<String, Object>> results2 = jjDatabaseWeb.separateRow(db.otherSelect(sql2));
//                        String val2 = results2.get(0).get("val").toString();// 
//                        data2 += "'" + val2 + "',";
//                    }
                }
            }

            String where = " WHERE " + FormAnswerSet._formId + "=" + id;// برای اینکه ممکن است تاریخ شروع یا تاریح پایان هر کدام را نداشته باشد
            where += " AND " + FormAnswerSet._status + "='" + FormAnswerSet.statues_sabteNahei + "' ";
            String script = "";
            script
                    += "var ctx1 = document.getElementById('chartBar1').getContext('2d');"
                    + "var myChart1 = new Chart(ctx1, {"
                    + "type: 'bar', data: {"
                    + "labels: ["
                    + labels
                    + "],   "
                    + "datasets: [{"
                    + " label: \" ماهه اول\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + " data:[" + data11 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه دوم\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data12 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه سوم\",\n"
                    + "            backgroundColor: \"pink\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data13 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه چهارم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data14 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه پنجم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data15 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \"ماهه ششم\",\n"
                    + "            backgroundColor: \"purple\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data16 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label:\"ماهه هفتم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data17 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه هشتم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data18 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه نهم\",\n"
                    + "            backgroundColor: \"brown\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data19 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه دهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data20 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه یازدهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data21 + "],"
                    + "          },\n"
                    + "          {\n"
                    + "            label: \" ماهه دوازدهم\",\n"
                    + "            backgroundColor: \"blue\",\n"
                    + "            borderColor: [],\n"
                    + "            borderWidth: 4,\n"
                    + "data:[" + data22 + "],"
                    + "          },\n";

            String chartTitleExtraDiscription = "";
            if (!hmis_filter_department_id.isEmpty()) {// اگر فیلتر بخش گذاشته بود توی نمودار بنویسیم
                for (int i = 0; i < departmentsIdArray.length; i++) {
                    if (jjNumber.isDigit(departmentsIdArray[i]) && !departmentsIdArray[i].equals("0")) {
                        chartTitleExtraDiscription += " " + Department.getDepartmentName(departmentsIdArray[i], db);
                        if (i < departmentsIdArray.length - 1) {// برای اینکه اخری را کاما نگذارد
                            chartTitleExtraDiscription += " - ";
                        }
                    }
                }
            }
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, "id=" + id));
            script
                    += "]"
                    + "},"
                    + "options: {"
                    + "title: {"
                    + "display: true,"
                    + "fontSize: 18,"
                    + "fontFamily: \"'Iranian-Sans','BYekan', 'Arial', 'sans-serif','IRNazanin','BNazanin'\","
                    + "text: ['" + formRow.get(0).get(Forms._title) + "' , '" + chartTitleExtraDiscription + "'],"
                    + "},"
                    // برای ایجاد یک خط به عنوان ماکزیمم نمره ی قابل کسب که در حالت های میانگین کاربرد دارد
                    + (maxOfPossible > 0 ? (// اگر ماکزیمم وجود داشت و بزرگتر از صفر بود
                            "annotation: {"
                            + " annotations: ["
                            + "{"
                            + "   type: 'line',"
                            + "   mode: 'horizontal',"
                            + "   scaleID: 'y-axis-0',"
                            + "   value: '" + 100 + "',"
                            + "   borderColor: 'black',"
                            + "   borderWidth: 1,"
                            + "   label: {"
                            + "     backgroundColor:'gray',"
                            + "     enabled: true,"
                            + "     content: 'max : " + 100 + "%'"
                            + "   }"
                            + " }]},")
                            : "") // اگر ماکزیمم صفر بود
                    + "plugins: {"
                    + "datalabels: {backgroundColor:'#abec9d',borderRadius:5,anchor:'end',align:'top',padding:'3',color: '#0b0b0b',font:{weight: 'bolder',size: '20'},formatter: function(value, context) {"
                    + "return  Math.round(value*100)/100 ;"
                    + "}},"
                    + "labels: {"
                    + "render: 'value'"
                    + "}"
                    + "},"
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
                    + "fontSize: 12,"
                    + (maxOfPossible > 0 ? ("max: " + (105) + ",") : "")//مینیمم و ماکس نمودار را از طریق جاوا اسکریپت می دهیم 
                    + "}"
                    + "}],"
                    + "xAxes: [{"
                    + "ticks: {"
                    + "  padding: 10,"
                    + "autoSkip: false,"
                    + "maxRotation: 90,"
                    + "minRotation: 70,"
                    + "beginAtZero: true,"
                    + "fontFamily: \"'IRNazanin','BNazanin','Tahoma'\","
                    + "fontSize: 15"
                    + "}"
                    + "}]"
                    + "}"
                    + "}"
                    + "});";
            request.setAttribute("script", script);
            request.setAttribute("reportName", formRow.get(0).get(_title));
            request.setAttribute("ReportTitle", "نمودار بخش به بخش" + ":" + formRow.get(0).get(Forms._title));
//            request.setAttribute("data", data);
            request.setAttribute("lables", labels);
            request.getRequestDispatcher("template/charts.jsp").forward(request, response);
//            System.out.println("/////////////////////////template/charts2.jsp");    
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
            List<Map<String, Object>> rowAllActiveRols = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "," + _title, "id>=0", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            List<Map<String, Object>> rowAllActiveRol = jjDatabaseWeb.separateRow(db.otherSelect(""
                    + " select "
                    //                    + "COUNT(COUNT(CASE WHEN formAnswers_status = 'ثبت اولیه' THEN 1 END)As sabteAvalie))As sabteAvalie,"
                    //                    + "COUNT((CASE WHEN formAnswers_status = 'ثبت نهایی' THEN 1 END)As sabteNahei)As sabteNahei,"
                    + "hmis_forms.id,hmis_forms.forms_title"
                    + " from hmis_forms "
                    + " LEFT JOIN  " + FormAnswerSet.tableName + " ON hmis_forms.id=" + FormAnswerSet._formId
                    + ""));// بر اساس حروف الفبا مرتب باشد بهتر است
            optionHtml.append("<option  value=''>یک فرم را انتخاب کنید</option>");

            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_title)).append("</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".formSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String randomize(int arr[], int n) throws Exception {
        try {
            // Creating a object for Random class 
            Random r = new Random();

            // Start from the last element and swap one by one. We don't 
            // need to run for the first element that's why i > 0 
            for (int i = n - 1; i > 0; i--) {

                // Pick a random index from 0 to i 
                int j = r.nextInt(i);

                // Swap arr[i] with the element at random index 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

            // Prints the random array 
            System.out.println(Arrays.toString(arr));
            return Arrays.toString(arr);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * گرفتن فرم بصورت جاوایی
     *
     * @param db
     * @return
     * @throws Exception
     */
    public static String getHtmlfilterForm(jjDatabaseWeb db) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllActiveRols = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "," + _title, "id>=0", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            optionHtml.append("<option  value=''>یک فرم را انتخاب کنید</option>");
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_title)).append("</option>");
            }

            return optionHtml.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String refreshInfectionControl(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsInfectionControl)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            int userId = jjTools.getSeassionUserId(request);
            String userRoleInsession = jjTools.getSeassionUserRole(request);
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            System.out.println(">>>>>>>>UserRoles is:" + userRoleInsession);
            String where = " AND ((";
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
                where += Forms._accessessGroupId + " like " + "'%ALL%'" + " OR ";//فرم هایی که برای همه ی سمت ها دسترسی دارند را هم نشان بدهیم
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

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + " where " + Forms._category_id + "='کنترل عفونت'  " + where));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            html.append("<table id='refreshInfectionControl' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل یک فرم</th>");
            html.append("<th class='c'>اطلاعات تکمیل فرم</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append("<tr>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("<th></th>");
            html.append("</tr>");
            html.append("</thead><tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + (!formRows.get(i).get(Forms._code).toString().isEmpty() ? "(" + formRows.get(i).get(Forms._code) + ") " : "") + formRows.get(i).get(Forms._id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='c'><i class='p icon fa fa-pencil' onclick='hmisInfectionControl.refreshMyAnswersInfectionControl(" + formRows.get(i).get(Forms._id).toString() + ")'></i></td>");
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
                script.append(Js.setHtml("#swInfectionControlTbl", html));
//                script.append(Js.table("#refreshInfectionControl", "", 0, "", "لیست اخبار"));
                script.append("  $('#refreshInfectionControl').DataTable( {\n"
                        + "        initComplete: function () {\n"
                        + "            this.api().columns([1]).every( function () {\n"
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
                        + "    } );"
                        + "$('.columnSelectSearch').select2({\n"
                        + "                    width: '100%'\n"
                        + "                });");
                Server.outPrinter(request, response, script);
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static List<Map<String, Object>> createDataSetForFormsInverse(List<Map<String, Object>> dataset_val_date, int startDate, int endDate, int period, String sum_avg) {
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int i = 0;// اشاره به آخرین سطر داده ها       
//        while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), false) < startDate) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
//            i++;// از اول بازه باید شروع کنیم تا آخر بازه ی مورد نظر
//        }                             
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
                    if (jjNumber.isFloat(dataset_val_date.get(i).get("val").toString())) {
                        sum_a += Double.valueOf(dataset_val_date.get(i).get("val").toString());
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
                if (sum_a == 0.0) {
                    map.put("val", "-");
                } else {
                    map.put("val", sum_a / countForAvg);
                };
            } else {
                if (sum_a == 0.0) {
                    map.put("val", "-");
                } else {
                    map.put("val", sum_a);
                }
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

    public static List<Map<String, Object>> createDataSetForFormsInversePeriodRange(List<Map<String, Object>> dataset_val_date, int startDate, int endDate, int period, String sum_avg) {
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int i = 0;// اشاره به آخرین سطر داده ها       
//        while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), false) < startDate) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
//            i++;// از اول بازه باید شروع کنیم تا آخر بازه ی مورد نظر
//        }                             
        jjCalendar_IR datePointer = new jjCalendar_IR(String.valueOf(startDate));
        while (datePointer.getDBFormat_8length() < endDate) {
            jjCalendar_IR datePointer2 = new jjCalendar_IR(datePointer.getYear(), datePointer.getMonth(), datePointer.getDay());
            int periodInMont = period / 30;
            if (period >= 30) {// اگر دوره به ماه بود 
                datePointer2.addMonth(periodInMont);
            } else {// اگر دوره های روزانه یا هفتگی مد نظر بود 
                datePointer2.addDay(period);
            }
            Double countAnswer = 0.0;//محاسبه ی مجموع درون بازه برای هر دوره
            Double sum_a = 0.0;//محاسبه ی مجموع درون بازه برای هر دوره
            Double avg_a = 0.0;//محاسبه یا میانگین درون بازه برای هر دوره 
            int countForAvg = 0;
            // برای محاسبه ی تعداد داده های مجاز که غ غ ارزیابی نیستند
            while (i < dataset_val_date.size() && jjCalendar_IR.getDatabaseFormat_8length(dataset_val_date.get(i).get("_date").toString(), false) < datePointer2.getDBFormat_8length()) {// برای هر دوره ممکن است صفر یا چند بار این حلقه تکرار شود
                if (dataset_val_date.get(i) != null) {
                    if (jjNumber.isFloat(dataset_val_date.get(i).get("val").toString())) {
                        sum_a += Double.valueOf(dataset_val_date.get(i).get("val").toString());
                        countAnswer += Double.valueOf(dataset_val_date.get(i).get("no").toString());
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
                if (sum_a == 0.0) {
                    map.put("val", "-");
                    map.put("no", countAnswer);
                } else {
//                    map.put("val", sum_a / countForAvg);
                    map.put("val", sum_a );
                    System.out.println("q=" + sum_a );
                    map.put("no", countAnswer);
                };
            } else {
                if (sum_a == 0.0) {
                    map.put("val", "-");
                    map.put("no", countAnswer);
                } else {
                    map.put("val", sum_a);
                    map.put("no", countAnswer);
                }
            }
            System.out.println("-----------");
            System.out.println(datePointer.getViewFormat_10length());
            System.out.println(sum_a);
            System.out.println(countForAvg);
            System.out.println(sum_avg);
            System.out.println(countAnswer);
            finalResult.add(map);
            System.out.println(finalResult);

        }
        System.out.println("&&&&&&&&&&&&&&&>>>>" + sum_avg + " -->" + finalResult);
        return finalResult;
    }
}
