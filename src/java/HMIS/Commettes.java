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
import jj.jjTime;

/**
 *
 * @author shohreh.shiran Date 1397.11.1
 */
public class Commettes {

    public static String tableName = "hmis_commettes";
    public static String _id = "id";
    public static String _isActive = "commettes_isActive";//وضعیت فعال وغیر فعال
    public static String _creatorId = "commettes_creatorId";//عنوان کمیته
    public static String _title = "commettes_title";//عنوان کمیته
    public static String _superwizar = "commettes_superwizar";//رئیس کمیته
    public static String _secretary = "commettes_secretary";// دبیر کمیته
    public static String _period = "commettes_period";//برگزاری جلسات
    public static String _members = "commettes_members";//اعضای کمیته  همان نقش میشود از جدول نقش ها می اید 
    public static String _regulationFile = "commettes_regulationFile";//آئین نامه
    public static String _documnetsFile = "commettes_documentsFile";//داکیومنت
    public static String _description = "commettes_description";//توضیحات
    public static String _date = "commettes_date";//تاریخ 

    public static int rul_showCommitesModule = 424;
    public static int rul_rfsAll = 425;
    public static int rul_rfs = 426;
    public static int rul_ins = 427;
    public static int rul_edt = 428;
    public static int rul_dlt = 429;
    public static int rul_rfsAssessmentForSessionCategory = 430;
    public static int rul_copy = 431;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            DefaultTableModel dtm = null;
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                String roles = jjTools.getSeassionUserRole(request);
                if (!roles.equals("")) {
                    String[] role = roles.split(",");
                    String condition1 = "";
                    for (int i = 0; i < role.length; i++) {
                        System.out.println("role" + role[i]);
                        if (i == role.length - 1) {// برای آخری  OR نمیخواهیم
                            condition1 += "(" + Commettes._secretary + " like '" + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + "'  OR " + Commettes._secretary + "=" + role[i] + " ) ";
                        } else {
                            condition1 += "(" + Commettes._secretary + " like '" + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + ",%' OR " + Commettes._secretary + " like '%," + role[i] + "'  OR " + Commettes._secretary + "=" + role[i] + " )OR ";
                        }
                    }
                    condition1 += " OR " + Commettes._creatorId + "=" + jjTools.getSeassionUserId(request);
                    dtm = db.Select(tableName, condition1);
                }
            } else {
                dtm = db.Select(tableName);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            StringBuilder html = new StringBuilder();
            html.append(""
                    + "<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">کمیته ها</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\">لیست کمیته ها و کارگروه ها</div>\n");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" "
                        + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 mg-t-10 tx-white\" onclick=\"hmisCommettes.m_add_new();\" >کمیته جدید</a>\n"
                        + "                                        </p>\n"
                        + "                                    ");
            }

            html.append("     <div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshCommettes' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='20%'>وضعیت</th>");
            html.append("<th class='c' width='20%'>نام کمیته</th>");
            html.append("<th class='c' width='20%'>دبیر کمیته</th>");
            html.append("<th class='c' width='20%'>رئیس کمیته</th>");
            html.append("<th class='c' width='20%'>دعوتنامه</th>");
            html.append("<th class='c' width='20%'>عملیات</th>");
            html.append(""
                    + "            <tr>"
                    + "                <th>کد</th>"
                    + "                <th>وضعیت</th>"
                    + "                <th>نام</th>"
                    + "                <th>دبیر</th>"
                    + "                <th>رئیس</th>"
                    + "                <th></th>\n"
                    + "                <th></th>\n"
                    + "            </tr>\n"
                    + "        </thead>" + "<tbody>");
            for (int i = 0; i < row.size(); i++) {
                String secretaryTitle = "";
                String[] secretaryArrayId = row.get(i).get(_secretary).toString().split(",");
                for (int j = 0; j < secretaryArrayId.length; j++) {
                    secretaryTitle += Role.getRoleName(secretaryArrayId[j], db) + "-";
                }

                html.append("<tr  class='p'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                String StatusActive = row.get(i).get(_isActive).equals("1") ? "فعال" : "غیرفعال";
                html.append("<td class='c'>" + StatusActive + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='c'>" + secretaryTitle + "</td>");
                html.append("<td class='c'>" + Role.getRoleName(row.get(i).get(_superwizar).toString(), db) + "</td>");
                html.append("<td class='c'><i class='icon ion-email' onclick='hmisCommettes.showInvitationForm(" + row.get(i).get(_id) + ");' ></i></td>");
                html.append("<td class='c'><i class='icon ion-ios-gear'  onclick='hmisCommettes.m_select(" + row.get(i).get(_id) + ")'></i></td>");
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
                panel = "swCommettesTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshCommettes", "auto", 0, "", "کمیته ها");
            script += "  $('#refreshCommettes').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([1,2,3,4,5]).every( function () {\n"
                    + "                var column = this;\n"
                    + "                var select = $('<select class=\"columnSelectSearchCommettes\"><option value=\"\"></option></select>')\n"
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
                    + "$('.columnSelectSearchCommettes').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
            //////////////////////////////برای سلکت های موجود در فرم کمیتهها/////////////
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ارزیابی کیته
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refreshAssessmentForSessionCategory(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfsAssessmentForSessionCategory)) {
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
            String userGroupInsession = jjTools.getSessionAttribute(request, "#GROUP_ID");
            String userGroup[] = userGroupInsession.split(",");
            for (int i = 0; i < userGroup.length; i++) {
                where += Forms._accessessGroupId + " like " + "'%," + userGroup[i] + ",%' OR ";// 
            }
            if (!userGroupInsession.isEmpty()) {// اگر کاربر جاری گروه در سیستم دارد
                where += Forms._accessessGroupId + " like " + "'%,0,%'" + " OR ";//
            }
            where += Forms._accessessUsers + " like " + "'%," + userId + ",%'" + " OR ";
            where += Forms._accessessUsers + " like " + "'%ALL%' ) OR  ";
            where += "(" + Forms._accessessGroupId + "=',null,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "=',null,' AND ";
            where += Forms._accessessUsers + "=',null,' )OR ";
            where += "(" + Forms._accessessGroupId + "=',,' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "=',,' AND ";
            where += Forms._accessessUsers + "=',,' )OR ";
            where += "(" + Forms._accessessGroupId + "='' AND "; // این سه تا شرط برای زمانی که کاربر معمولی بتواند ببیند
            where += Forms._accessessRoles + "='' AND ";
            where += Forms._accessessUsers + "='' )AND ";
            where += Forms._isActive + "=1 AND ";
            where += " ( (" + Forms._expireDate + " > " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + ") ";
            where += " OR (";
            where += Forms._expireDate + " = " + jjCalendar_IR.getDatabaseFormat_8length(null, true); // تاریخ مساوی امروز باشد و
            where += " AND ";
            where += Forms._expireTime + " >= " + jjTime.getTime4lenth("") + ")"; // ساعت بزرگتر از ّساعت جاری باشد
            where += "))";
            where += "; ";

            List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM " + Forms.tableName + " where " + Forms._category_id + "='ارزیابی کمیته' " + where));
            StringBuilder html = new StringBuilder();
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("<div class='card-header bg-primary tx-white'>لیست فرم ها و چک لیست های در اختیار شما</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            html.append("<table id='refreshAssessmentForSessionCategory' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='r'>کد</th>");
            html.append("<th class='r'>عنوان فرم</th>");
            html.append("<th class='c'>تکمیل یک فرم</th>");
            html.append("<th class='c'>اطلاعات تکمیل فرم</th>");
            html.append("<th class='c'>آنالیز و آمار</th>");
            html.append(""
                    + "            <tr>"
                    + "                <th></th>"
                    + "                <th>عنوان </th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "                <th></th>"
                    + "            </tr>\n"
                    + "        </thead>" + "<tbody>");
            for (int i = 0; i < formRows.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + (!formRows.get(i).get(Forms._code).toString().isEmpty() ? "(" + formRows.get(i).get(Forms._code) + ") " : "") + formRows.get(i).get(Forms._id) + "</td>");
                html.append("<td class='r'>" + formRows.get(i).get(Forms._title) + "</td>");
                html.append("<td class='c'><i class='p icon fa fa-pencil' onclick='hmisAssessmentForSessionCategory.refreshMyAnswersAssessmentForSession(" + formRows.get(i).get(Forms._id).toString() + ")'></i></td>");
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
                script.append(Js.setHtml("#swAssessmentForSessionCategoryTbl", html));
//                script.append(Js.table("#refreshAssessmentForSessionCategory", "", 0, "", "لیست "));
                script.append("  $('#refreshAssessmentForSessionCategory').DataTable( {\n"
                        + "        initComplete: function () {\n"
                        + "            this.api().columns([1]).every( function () {\n"
                        + "                var column = this;\n"
                        + "                var select = $('<select><option value=\"\"></option></select>')\n"
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
                        + "$('select').select2({\n"
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

    /**
     * creator shohre shiran 13971211
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
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            System.out.println("commettes_creatorId=" + jjTools.getSessionAttribute(request, "#ID"));
            map.put(_creatorId, jjTools.getSeassionUserId(request));
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_members, jjTools.getParameter(request, _members));
            map.put(_secretary, jjTools.getParameter(request, _secretary));
            map.put(_superwizar, jjTools.getParameter(request, _superwizar));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_regulationFile, jjTools.getParameter(request, _regulationFile));
            map.put(_documnetsFile, jjTools.getParameter(request, _documnetsFile));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_date, jjTools.getParameter(request, _date).replaceAll("/", ""));

            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {

                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }

            Server.outPrinter(request, response, Js.jjCommettes.refresh());
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
                html.append(Js.setHtml("#Commette_button", "<button  id=\"insert_Commette_new\"  class=\"btn btn-success active btn-block mg-b-10\" onclick='" + Js.jjCommettes.insert() + "'>" + lbl_insert + "</button>"));
            }
            String script = html.toString();

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
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);//ای دی کمیته
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
            String commettesId = row.get(0).get(_id).toString();
            String membersId = row.get(0).get(_members).toString();
            String secretaryId = row.get(0).get(_secretary).toString();
            html.append("$('#" + _secretary + "').val([" + row.get(0).get(_secretary) + "]);$('#" + _secretary + "').select2({  width: '100%'});");
            html.append("$('#" + _superwizar + "').val([" + row.get(0).get(_superwizar) + "]);$('#" + _superwizar + "').select2({  width: '100%'});");
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _documnetsFile, row.get(0).get(_documnetsFile)));
//            html.append(Js.setVal("#" + _regulationFile, row.get(0).get(_regulationFile)));
            html.append(Js.setVal("#" + _period, row.get(0).get(_period)));
            html.append(Js.select2("#" + _period, " width: '100%'"));
            html.append(Js.setVal("#" + _description, row.get(0).get(_description)));
            html.append(Js.setVal("#" + _members, row.get(0).get(_members)));
            String[] memberIdArray = membersId.split(",");
            String temp = "";
            System.out.println("memberIdArray" + memberIdArray.length);
            for (int i = 0; i < memberIdArray.length; i++) {
                temp += "" + memberIdArray[i] + ",";
            }
            html.append(Js.setValSelectOption("#" + _members, temp));
            html.append(Js.select2("#" + _members, " width: '100%'"));

            String[] secretaryIdArray = secretaryId.split(",");
            String temp1 = "";
            System.out.println("secretaryIdArray" + secretaryIdArray.length);
            for (int i = 0; i < secretaryIdArray.length; i++) {
                temp1 += "" + secretaryIdArray[i] + ",";
            }
            html.append(Js.setValSelectOption("#" + _secretary, temp1));
            html.append(Js.select2("#" + _secretary, " width: '100%'"));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _regulationFile, row.get(0).get(_regulationFile)));
            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            html.append(Js.setAttr("#PicPreviewAttach", "src", "upload/" + row.get(0).get(_regulationFile) + ""));
            ///////////////////////////////////////////////////
            String attachFilesRegulation = row.get(0).get(_regulationFile).toString();
            String[] attachFilesRegulationArray = attachFilesRegulation.split(",");
            for (int l = 0; l < attachFilesRegulationArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesRegulationArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesRegulationArray[l].substring(attachFilesRegulationArray[l].lastIndexOf(".") + 1, attachFilesRegulationArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html4.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesRegulationArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesRegulationArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _regulationFile + "'  type='hidden'  value='" + attachFilesRegulationArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html4.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesRegulationArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _regulationFile + "'   type='hidden'  value='" + attachFilesRegulationArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String attachFilesDocument = row.get(0).get(_documnetsFile).toString();
            String[] attachFilesDocumentArray = attachFilesDocument.split(",");
            for (int l = 0; l < attachFilesDocumentArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesDocumentArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesDocumentArray[l].substring(attachFilesDocumentArray[l].lastIndexOf(".") + 1, attachFilesDocumentArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html3.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesDocumentArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesDocumentArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _documnetsFile + "'  type='hidden'  value='" + attachFilesDocumentArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html3.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesDocumentArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _documnetsFile + "'   type='hidden'  value='" + attachFilesDocumentArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

            ///////////////////////////////////////////////////
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            boolean accCopy = Access_User.hasAccess(request, db, rul_copy);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"mg-t-20  col-lg\">");
                html2.append("<button  id='edit_Commettes'  class='btn btn-warning btn-block mg-b-10' onclick='" + Js.jjCommettes.edit() + "'>" + lbl_edit + "</button>");
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"mg-t-20  col-lg\">");
                html2.append("<button id='delete_Commettes' class='btn btn-danger btn-block mg-b-10' onclick='" + Js.jjCommettes.delete(id) + "' >" + lbl_delete + " </button>");
                html2.append("</div>");
            }
            if (accCopy) {
                html2.append("<div class=\"mg-t-20  col-lg\">");
                html2.append("<button id='copy_Commettes' class='btn btn-success btn-block mg-b-10' onclick='hmisCommettes.copy(" + id + ")' >کپی</button>");
                html2.append("</div>");
            }
            html2.append("</div>");
            String script = Js.setHtml("#Commette_button", html2);

            script += html.toString();
            script += Js.setHtml(".inputSelectorDiv", html3.toString() + html4.toString());
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
            String id = jjTools.getParameter(request, _id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                Server.outPrinter(request, response, Js.modal(errorMessage,"پیام سامانه"));
//            }
            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_period, jjTools.getParameter(request, _period));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_members, jjTools.getParameter(request, _members));
            map.put(_secretary, jjTools.getParameter(request, _secretary));
            map.put(_superwizar, jjTools.getParameter(request, _superwizar));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_regulationFile, jjTools.getParameter(request, _regulationFile));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_date, jjTools.getParameter(request, _date).toString().replaceAll("/", ""));
            map.put(_documnetsFile, jjTools.getParameter(request, _documnetsFile));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCommettes.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویژگی: زمانی حذف صورت میگیرید که کمیته مورد نظر جایی استفاده نشده باشد
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            String script = "";
            List<Map<String, Object>> RowAll = jjDatabase.separateRow(db.otherSelect("SELECT hmis_commettes.id, hmis_sessions.sessions_commettesId  FROM hmis_commettes "
                    + " LEFT JOIN hmis_sessions ON hmis_commettes.id=hmis_sessions.sessions_commettesId WHERE hmis_sessions.sessions_commettesId=" + id + ""));
            boolean flag = true;
            System.out.println("rowAll=" + RowAll.size());
            if (RowAll.size() > 0) {
                flag = false;

            }

            if (flag == false) {
                script += Js.modal("امکان حذف کمیته وجود ندارد", "پیام سامانه");
            } else {
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    script += Js.modal(errorMessage, "پیام سامانه");
                    return "";
                }
                script += Js.jjCommettes.refresh();
            }

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * دعوتنامه نمایش داده می شود
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showInvitationForm(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//
//             if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String commettesId = jjTools.getParameter(request, _id);
            System.out.println("commettesId=" + commettesId);
            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + commettesId));
            List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + commettesId));
            String memberId = commettesRow.get(0).get(Commettes._members).toString();
            String[] membersId = (memberId).split(",");
            for (int i = 0; i < membersId.length; i++) {

                List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + membersId[i]));
                if (!roleRow.isEmpty()) {
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + roleRow.get(0).get(Role._user_id)));
                    if (!userRow.isEmpty()) {
                        html.append("<label class=\"ckbox\">");
                        html.append("<input type = \"checkbox\" value ='" + roleRow.get(0).get(Role._id) + "' class=\"checkBoxInvitees\" name = \"sessions_Invitees\" id = \"sessions_Invitees" + roleRow.get(0).get(Role._user_id) + "\" > ");
                        html.append("<span>" + userRow.get(0).get(Access_User._name) + " " + userRow.get(0).get(Access_User._family) + "-" + roleRow.get(0).get(Role._title) + "");
                        html.append("</span>");
                        html.append("</label>");
                    }
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////////////
//            List<Map<String, Object>> usersRow = jjDatabase.separateRow(db.Select(Access_User.tableName));

//            html2.append("<option value=''>انتخاب</option>");//مهمانان داخل سازمان را انتخاب می کنیم
//            for (int i = 0; i < usersRow.size(); i++) {
//                html2.append("<option value='" + usersRow.get(i).get(Access_User._id) + "'>" + usersRow.get(i).get(Access_User._name) + " " + usersRow.get(i).get(Access_User._family) + "</option> ");
//            }
            String script = "";
//            script += Js.setHtml("#sessions_InviteesInSide", html2);
            script += Js.setHtml("#invitessDiv", html);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ارسال پیام برای مدعوین داخل و خارج از سازمان و کاربرانی که در سیستم ثبت
     * نام نکرده اند در اکسس یوزر ثبت می شوند
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String sendComment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String Email = "";
            String nameAndFamily = "";
            String phone = "";
            String role = "";

            String script = "";
            String textComment = jjTools.getParameter(request, "textComment");//متن پیام
            String inviteesIdComment = jjTools.getParameter(request, "inviteesIdComment");//ای دی مدعوین 
            String inviteesInSideIdComment = jjTools.getParameter(request, "inviteesInSideIdComment");//ای دی مدعوین مهمان
            String inviteesOutSideIdComment = jjTools.getParameter(request, "inviteesOutSideIdComment");//ای دی مدعوین داخل سازمان
            String[] inviteesOutSideId = (inviteesOutSideIdComment.replaceAll("#A#", "%23A%23")).split("%23A%23");

            for (int i = 0; i < inviteesOutSideId.length; i++) {
                String[] userInformation = inviteesOutSideId[i].split(",");
                nameAndFamily = userInformation[0];
                phone = userInformation[1];
                Email = userInformation[2];
                role = userInformation[3];
                Server.sendEmail("", Email, "دعوتنامه" + textComment + "", "", isPost, request, db);
            }

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش لیست کمیته ها
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getSelectOptionIndicatorCommettes(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        String script = "";
        try {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, "id>=0", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < row.size(); i++) {
                optionHtml.append("<option   value='").append(row.get(i).get(_id)).append("'>").append(row.get(i).get(_title)).append("</option>");
            }
            script += Js.setHtml(".commettesTitle", optionHtml);

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getSelectOptionCommettesAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        String script = "";
        String panel = jjTools.getParameter(request, "panel");
        try {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, "id>=0", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            optionHtml.append("<option  id='AllCommettes' value='All'>همه کمیته ها</option>");
            for (int i = 0; i < row.size(); i++) {
                optionHtml.append("<option   value='").append(row.get(i).get(_id)).append("'>").append(row.get(i).get(_title)).append("</option>");
            }
//            script += Js.setHtml(".AllcommettesTitle", optionHtml);
            if (panel.equals("")) {
                panel = "AllcommettesTitle";
            }
            script += Js.setHtml(panel, optionHtml);

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * انتخاب کمیته و
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String ShowCommetteSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        String script = "";
        StringBuilder html2 = new StringBuilder();
        try {
            String commettesId = jjTools.getParameter(request, "commettesId");
            String dateFrom = jjTools.getParameter(request, "dateFrom").replaceAll("/", "");
            String dateTo = jjTools.getParameter(request, "dateTo").replaceAll("/", "");
            DefaultTableModel dtm1;
            DefaultTableModel dtm2;
            DefaultTableModel dtm3;
            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<table id='refreshAudience' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html2.append("<th class='c' width='15%'>نام ونام خانوادگی</th>");
            html2.append("<th class='c' width='15%'>تعداد جلسات </th>");
            html2.append("<th class='c' width='15%'>تعداد دعوت شدگان </th>");
            html2.append("<th class='c' width='20%'>تعداد و درصد حضور</th>");
            html2.append("<th class='c' width='15%'>تعداد و درصد غیبت</th>");
            html2.append("</thead><tbody>");

            List<Map<String, Object>> RoleRow = jjDatabaseWeb.separateRow(db.Select(Role.tableName));

            dtm1 = db.otherSelect("SELECT count(hmis_sessions.id)As countSessions , "
                    + "GROUP_CONCAT(sessions_invitees SEPARATOR '')AS countInvitees ,"
                    + "GROUP_CONCAT(sessions_audience SEPARATOR '')AS countAudience,"
                    + "GROUP_CONCAT(sessions_absentRole SEPARATOR '')AS countAbsent"
                    + " FROM hmis_sessions"
                    + " LEFT JOIN hmis_commettes ON  hmis_sessions.sessions_commettesId=hmis_commettes.id "
                    + " WHERE hmis_sessions.sessions_commettesId=" + commettesId + "  AND "
                    + "" + dateFrom + " <= " + Sessions._date + " AND " + dateTo + " >= " + Sessions._date
                    + " AND hmis_sessions.sessions_status!='" + Sessions.status_created + "' "
            );

            List<Map<String, Object>> sessionsRow = jjDatabaseWeb.separateRow(dtm1);
            if (sessionsRow.size() > 0) {
                int countSessions = Integer.valueOf(sessionsRow.get(0).get("countSessions").toString());
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + sessionsRow.get(0).get("countInvitees").toString());
                String[] countAudienceArray = sessionsRow.get(0).get("countAudience").toString().split(",");
                String[] countInviteesArray = sessionsRow.get(0).get("countInvitees").toString().split(",");
                String[] countAbsentArray = sessionsRow.get(0).get("countAbsent").toString().split(",");
                for (int i = 0; i < RoleRow.size(); i++) {
                    int countInvitees = 0;
                    if (!sessionsRow.get(0).get("countInvitees").toString().equals("")) {
                        for (int k = 0; k < countInviteesArray.length; k++) {
                            if (countInviteesArray[k].equals(RoleRow.get(i).get(Role._id))) {
                                countInvitees++;
                            }
                        }
                    }
                    int countAudience = 0;
                    if (!sessionsRow.get(0).get("countAudience").toString().equals("")) {
                        for (int k = 0; k < countAudienceArray.length; k++) {
                            if (countAudienceArray[k].equals(RoleRow.get(i).get(Role._id))) {
                                countAudience++;
                            }
                        }
                    }
                    int countAbsent = 0;
                    if (!sessionsRow.get(0).get("countAbsent").toString().equals("")) {
                        for (int k = 0; k < countAbsentArray.length; k++) {
                            if (countAbsentArray[k].equals(RoleRow.get(i).get(Role._id))) {
                                countAbsent++;
                            }
                        }
                    }
                    System.out.println("------" + countInvitees);
                    if (countInvitees > 0) {//اگر از نقش ها فردی دعوت شده بود نمایش دهد
                        html2.append("<tr class='p '>");
                        html2.append("<td class='c'>" + Role.getRoleName(RoleRow.get(i).get(Role._id).toString(), db) + "</td>");
                        html2.append("<td class='c'>" + sessionsRow.get(0).get("countSessions") + "</td>");
                        html2.append("<td class='c'>" + countInvitees + "</td>");
//                    html2.append("<td class='r'>" + (int) Math.round(countInvitees) + "</td>");
                        float audiencePercent = 0;
                        float absentPercent = 0;
                        if (countInviteesArray.length >= 1) {
                            audiencePercent = ((float) countAudience / (float) countSessions) * 100;
                            absentPercent = ((float) countAbsent / (float) countSessions) * 100;
                            html2.append("<td class='c'>" + countAudience + "-" + (int) Math.round(audiencePercent) + "%</td>");
                            html2.append("<td class='c'>" + countAbsent + "-" + (int) Math.round(absentPercent) + "%</td>");
                        } else {
                            html2.append("<td class='c'>" + countAudience + "-0%</td>");
                            html2.append("<td class='c'>" + countAbsent + "-0%</td>");
                        }

                        html2.append("</tr>");

                    }
                }
            }
            html2.append("</tbody></table>");
            script += Js.setHtml("#result1", html2);
            script += " $('#refreshAudience').dataTable(\n"
                    + "                                                                {\n"
                    + "                                                                    iDisplayLength: 100\n"
                    + "                                                                    , bJQueryUI: true\n"
                    + "                                                                    , sPaginationType: 'full_numbers'\n"
                    + "                                                                    , columnDefs: [{orderable: false, targets: 0}]\n"
                    + "                                                                    , paging: false\n"
                    + "                                                                    , dom: 'Bfrtip'\n"
                    + "                                                                    , buttons: ['copy', 'csv', 'excel', 'print']\n"
                    + "                                                                });";
//            script += Js.table("#refreshAudience", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * نمایش یک فرد در همه کمیته ها و گزارش آن
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String reportOneAudience(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        String script = "";
        StringBuilder html2 = new StringBuilder();
        try {
            String roleIdOneAudience = jjTools.getParameter(request, "roleIdOneAudience");
            String commetteId = jjTools.getParameter(request, "commettesTitleIndicator");
            String dateFrom = jjTools.getParameter(request, "dateFromOneAudience").replaceAll("/", "");
            String dateTo = jjTools.getParameter(request, "dateToOneAudience").replaceAll("/", "");
            DefaultTableModel dtm1;
            String where = "";
            if (!commetteId.equals("")&&!commetteId.equals("All")) {
                where += " AND " + Sessions._commetteId + "=" + commetteId;
            }  
            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<table id='refreshOneAudience' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html2.append("<th class='c' width='15%'>نام ونام خانوادگی</th>");
            html2.append("<th class='c' width='15%'>تعداد جلسات </th>");
            html2.append("<th class='c' width='15%'>تعداد دعوت شدگان </th>");
            html2.append("<th class='c' width='20%'>تعداد و درصد حضور</th>");
            html2.append("<th class='c' width='15%'>تعداد و درصد غیبت</th>");
            html2.append("</thead><tbody>");

            List<Map<String, Object>> RoleRow = jjDatabaseWeb.separateRow(db.Select(Role.tableName));

            dtm1 = db.otherSelect("SELECT count(hmis_sessions.id)As countSessions , "
                    + "GROUP_CONCAT(sessions_invitees SEPARATOR '')AS countInvitees ,"
                    + "GROUP_CONCAT(sessions_audience SEPARATOR '')AS countAudience,"
                    + "GROUP_CONCAT(sessions_absentRole SEPARATOR '')AS countAbsent"
                    + " FROM hmis_sessions"
                    + " LEFT JOIN hmis_commettes ON  hmis_sessions.sessions_commettesId=hmis_commettes.id "
                    + " WHERE " + dateFrom + " <= " + Sessions._date + " AND " + dateTo + " >= " + Sessions._date + " AND sessions_status!='" + Sessions.status_created + "' " + where
            );//بین این دوتاریخ چقدر جلسه برگزار شده

            List<Map<String, Object>> sessionsRow = jjDatabaseWeb.separateRow(dtm1);
            if (sessionsRow.size() > 0) {
                int countSessions = Integer.valueOf(sessionsRow.get(0).get("countSessions").toString());
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + sessionsRow.get(0).get("countInvitees").toString());
                String[] countAudienceArray = sessionsRow.get(0).get("countAudience").toString().split(",");
                String[] countInviteesArray = sessionsRow.get(0).get("countInvitees").toString().split(",");
                String[] countAbsentArray = sessionsRow.get(0).get("countAbsent").toString().split(",");
                int countInvitees = 0;
                if (!sessionsRow.get(0).get("countInvitees").toString().equals("")) {//تعداد دعوت
                    for (int k = 0; k < countInviteesArray.length; k++) {
                        if (countInviteesArray[k].equals(roleIdOneAudience)) {
                            countInvitees++;
                        }
                    }
                }
                int countAudience = 0;
                if (!sessionsRow.get(0).get("countAudience").toString().equals("")) {//تعداد حضور
                    for (int k = 0; k < countAudienceArray.length; k++) {
                        if (countAudienceArray[k].equals(roleIdOneAudience)) {
                            countAudience++;
                        }
                    }
                }
                int countAbsent = 0;
                if (!sessionsRow.get(0).get("countAbsent").toString().equals("")) {//تعداد غیبت
                    for (int k = 0; k < countAbsentArray.length; k++) {
                        if (countAbsentArray[k].equals(roleIdOneAudience)) {
                            countAbsent++;
                        }
                    }
                }
                System.out.println("------" + countInvitees);
                html2.append("<tr class='p '>");
                html2.append("<td class='c'>" + Role.getRoleName(roleIdOneAudience, db) + "</td>");
                html2.append("<td class='c'>" + sessionsRow.get(0).get("countSessions") + "</td>");
                html2.append("<td class='c'>" + countInvitees + "</td>");
                float audiencePercent = 0;
                float absentPercent = 0;
                if (countInviteesArray.length >= 1) {
                    audiencePercent = ((float) countAudience / (float) countInvitees) * 100;
                    absentPercent = ((float) countAbsent / (float) countInvitees) * 100;
                    html2.append("<td class='c'>" + countAudience + "-" + (int) Math.round(audiencePercent) + "%</td>");
                    html2.append("<td class='c'>" + countAbsent + "-" + (int) Math.round(absentPercent) + "%</td>");
                } else {
                    html2.append("<td class='c'>" + countAudience + "-0%</td>");
                    html2.append("<td class='c'>" + countAbsent + "-0%</td>");
                }
                html2.append("</tr>");
            }
            html2.append("</tbody></table>");
            script += Js.setHtml("#result3", html2);
            script += " $('#refreshOneAudience').dataTable(\n"
                    + "                                                                {\n"
                    + "                                                                    iDisplayLength: 100\n"
                    + "                                                                    , bJQueryUI: true\n"
                    + "                                                                    , sPaginationType: 'full_numbers'\n"
                    + "                                                                    , columnDefs: [{orderable: false, targets: 0}]\n"
                    + "                                                                    , paging: false\n"
                    + "                                                                    , dom: 'Bfrtip'\n"
                    + "                                                                    , buttons: ['copy', 'csv', 'excel', 'print']\n"
                    + "                                                                });";
//            script += Js.table("#refreshOneAudience", "300", 0, "", "جلسات");  
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * نمایش گزارشات مصوبات
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String showApprovedReport(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        String script = "";
        StringBuilder html2 = new StringBuilder();
        try {
            String commettesId = jjTools.getParameter(request, "commettesId");
            String dateFrom = jjTools.getParameter(request, "dateFromApproved").replaceAll("/", "");
            String dateTo = jjTools.getParameter(request, "dateToApproved").replaceAll("/", "");
            DefaultTableModel dtm1;
            DefaultTableModel dtm2;
            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<table id='refreshReportApproved1' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html2.append("<th class='c' width='15%'>نام کمیته</th>");
            html2.append("<th class='c' width='15%'>تعداد مصوبات  دردست اقدام  </th>");
            html2.append("<th class='c' width='20%'>تعداد مصوبات  غیر قابل انجام</th>");
            html2.append("<th class='c' width='15%'>تعداد مصوبات  انجام شده</th>");
            html2.append("<th class='c' width='15%'>تعداد مصوبات  انجام نشده</th>");
            html2.append("<th class='c' width='15%'>تعداد مصوبات ثبت اولیه</th>");
            html2.append("</thead><tbody>");
            String otherSelect = "";
            String where = "";

            if (commettesId.equals("All")) {
                where = "";
            } else {
                where = "hmis_approved.approved_commettesId=" + commettesId + "  AND  ";
            }
            dtm1 = db.otherSelect("SELECT  GROUP_CONCAT(DISTINCT(" + Commettes._title + ") SEPARATOR '--')AS commettesTitle"
                    + " ,GROUP_CONCAT(DISTINCT(hmis_approved.id) SEPARATOR ',')AS id,"
                    + "COUNT(hmis_approved.id) as countApproved,\n"
                    + "COUNT(CASE WHEN approved_status='" + Approved.status_inDoing + "' THEN 1 ELSE NULL END) as countApprovedStatus1,\n"
                    + "COUNT(CASE WHEN approved_status='" + Approved.status_unDone + "' THEN 1 ELSE NULL END) as countApprovedStatus2,\n"
                    + "COUNT(CASE WHEN approved_status='" + Approved.status_done + "' THEN 1 ELSE NULL END) as countApprovedStatus3,"
                    + "COUNT(CASE WHEN approved_status='" + Approved.status_incomplete + "' THEN 1 ELSE NULL END) as countApprovedStatus5,"
                    + "COUNT(CASE WHEN approved_status='" + Approved.status_initialRegistration + "' THEN 1 ELSE NULL END) as countApprovedStatus4"
                    + "," + Approved._isActive
                    + "," + Approved._tracker_userName
                    + "," + Approved._executor_userName
                    //                    + "," + Approved._percentExecutor
                    //                    + "," + Approved._percentTracker
                    //                    + "," + Approved._percent
                    + " FROM hmis_approved "
                    + " LEFT JOIN hmis_sessions ON  hmis_approved.approved_sessionsId=hmis_sessions.id "
                    + " LEFT JOIN hmis_commettes ON  hmis_approved.approved_commettesId=hmis_commettes.id "
                    + " WHERE " + where + dateFrom + " <=hmis_sessions.sessions_date  AND " + dateTo + " >=hmis_sessions.sessions_date  "
            //                    + " WHERE " + where + dateFrom + " <=hmis_sessions.sessions_date  AND " + dateTo + " >=hmis_sessions.sessions_date  AND " + Approved._isActive + "=1  "
            );

            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm1);
            System.out.println("row.size()=" + row.size());
            if (Integer.valueOf(row.get(0).get("countApproved").toString()) > 0) {
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + row.get(0).get("countApprovedStatus1").toString());
                html2.append("<tr class='p '>");
                float countAllApproved = Float.parseFloat(row.get(0).get("countApproved").toString());
                float percentApprovedInDoing = (Float.parseFloat(row.get(0).get("countApprovedStatus1").toString()) / countAllApproved) * 100;
                float percentApprovedUnDoing = (Float.parseFloat(row.get(0).get("countApprovedStatus2").toString()) / countAllApproved) * 100;
                float percentApprovedIncomplete = (Float.parseFloat(row.get(0).get("countApprovedStatus5").toString()) / countAllApproved) * 100;
                float percentApprovedDone = (Float.parseFloat(row.get(0).get("countApprovedStatus3").toString()) / countAllApproved) * 100;
                float percentApprovedInitialRegistration = (Float.parseFloat(row.get(0).get("countApprovedStatus4").toString()) / countAllApproved) * 100;
                html2.append("<td class='c'>" + row.get(0).get("commettesTitle") + "</td>");
                html2.append("<td   class='c ' style='background-color:#ffffbd  !important' >" + row.get(0).get("countApprovedStatus1") + "-" + String.format("%.2f", percentApprovedInDoing) + "%</td>");
                html2.append("<td  class='c ' style='background-color:#7f7a7a !important'>" + row.get(0).get("countApprovedStatus2") + "-" + String.format("%.2f", percentApprovedUnDoing) + "%</td>");
                html2.append("<td  class='c' style='background-color:#daffd9 !important'>" + row.get(0).get("countApprovedStatus3") + "-" + String.format("%.2f", percentApprovedDone) + "%</td>");
                html2.append("<td  class='c' style='background-color:#ffcaba !important'>" + row.get(0).get("countApprovedStatus5") + "-" + String.format("%.2f", percentApprovedIncomplete) + "%</td>");
                html2.append("<td  class='c' style=''>" + row.get(0).get("countApprovedStatus4") + "-" + String.format("%.2f", percentApprovedInitialRegistration) + "%</td>");
                html2.append("</tr>");
                html2.append("</tbody></table>");
                html2.append("<div class=\"table-wrapper\">\n");
                html2.append("<table id='refreshReportApproved2' class='table display responsive wrap' class='tahoma10' style='direction: rtl;'><thead>");
                html2.append("<th class='c' width='10%'>کد</th>");
                html2.append("<th class='c' width='30%'>عنوان مصوبه</th>");
                html2.append("<th class='c' width='20%'>نام کمیته</th>");
                html2.append("<th class='c' width='10%'>مسئول اجرا #</th>");
                html2.append("<th class='c' width='10%'>مسئول پیگیری @</th>");
                html2.append("<th class='c' width='10%'>تاریخ شروع و پایان</th>");
                html2.append("<th class='c' width='10%'>وضعیت</th>");
                html2.append(""
                        + "            <tr>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th>عنوان </th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "                <th></th>"
                        + "            </tr>\n");
                html2.append("</thead><tbody>");
                String[] approvedIds = row.get(0).get("id").toString().split(",");
                for (int i = 0; i < approvedIds.length; i++) {
                    String ExecutorUserName = "";
                    String ExecutorRoleName = "";
                    List<Map<String, Object>> approvedRow = jjDatabase.separateRow(db.JoinLeft(Approved.tableName, Commettes.tableName, "*", Approved._commettesId, Commettes._id, " WHERE " + "hmis_approved.id=" + approvedIds[i]));
                    if (!approvedRow.get(0).get(Approved._executorRoleId).equals("")) {
                        String executorRoleId = approvedRow.get(0).get(Approved._executorRoleId).toString();
                        String[] executorRoleIdArray = executorRoleId.split(",");
                        for (int j = 0; j < executorRoleIdArray.length; j++) {
                            ExecutorRoleName += "#"+Role.getRoleName(executorRoleIdArray[j], db) + ",";   
                        }
                    }
                    if (!approvedRow.get(0).get(Approved._executorUserId).equals("")) {
                        if (approvedRow.get(0).get(Approved._executorUserId).equals("ALL")) {
                            ExecutorUserName = "تمام کاربران ثبت شده";
                        } else {
                            String executorUserId = approvedRow.get(0).get(Approved._executorUserId).toString();
                            String[] executorUserIdArray = executorUserId.split(",");
                            for (int j = 0; j < executorUserIdArray.length; j++) {
                                System.out.println("executorUserIdArray=" + executorUserIdArray[j]);
                                ExecutorUserName += "#"+Access_User.getUserName(executorUserIdArray[j], db) + ",";
                            }
                        }
                    }

                    html2.append("<tr class='p " + Sessions.getClassCssForVaziat(approvedRow.get(0).get(Approved._status).toString()) + "'>");
                    html2.append("<td class='c'>" + approvedIds[i] + "</td>");
                    html2.append("<td class='c'>" + approvedRow.get(0).get(Approved._title).toString().replaceAll("%23A%23", "-") + "</td>");
                    html2.append("<td class='c'>" + approvedRow.get(0).get(Commettes._title) + "</td>");
                    html2.append("<td class='c'>");
                    if (approvedRow.get(0).get(Approved._isActive).equals("1")) {
                        html2.append(ExecutorRoleName +" (" +approvedRow.get(0).get(Approved._executor_userName)+")");
                    } else {
                        html2.append(ExecutorUserName + ExecutorRoleName);
                    }
                    html2.append(
                            "<br/><div class=\"progress \">"
                            + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + approvedRow.get(0).get(Approved._percentExecutor).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + approvedRow.get(0).get(Approved._percentExecutor).toString() + "%;height:100%;line-height:20px\">\n"
                            + "" + approvedRow.get(0).get(Approved._percentExecutor).toString() + "%\n"
                            + "</div>\n"
                            + "</div>"
                    );
                    html2.append("</td>");
                    html2.append("<td class='c'>" +"@"+ Role.getRoleName(approvedRow.get(0).get(Approved._trackerId).toString(), db) + "("+approvedRow.get(0).get(Approved._tracker_userName)+")");
                    html2.append("<br/>"
                            + "<div class=\"progress \">"
                            + "<div class='progress-bar bg-success' role=\"progressbar \" aria-valuenow=" + approvedRow.get(0).get(Approved._percentTracker).toString() + " aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + approvedRow.get(0).get(Approved._percentTracker).toString() + "%;height:100%;line-height:20px\">"
                            + "" + approvedRow.get(0).get(Approved._percentTracker).toString() + "%\n"
                            + "</div>\n"
                            + "</div>"
                    );
                    html2.append("</td>");
                    html2.append("<td class='c'>" + jjCalendar_IR.getViewFormat(approvedRow.get(0).get(Approved._startDate)) + "</br> " + jjCalendar_IR.getViewFormat(approvedRow.get(0).get(Approved._endDate)) + "</td>");
                    html2.append("<td  class='c '>" + approvedRow.get(0).get(Approved._status) + "</td>");
                    html2.append("</tr>");
                }
                html2.append("</tbody></table>");
            }
            script += Js.setHtml("#result2", html2);
            script += " $('#refreshReportApproved1').dataTable(\n"
                    + "                                                                {\n"
                    + "                                                                    iDisplayLength: 100\n"
                    + "                                                                    , bJQueryUI: true\n"
                    + "                                                                    , sPaginationType: 'full_numbers'\n"
                    + "                                                                    , columnDefs: [{orderable: false, targets: 0}]\n"
                    + "                                                                    , paging: false\n"
                    + "                                                                    , dom: 'Bfrtip'\n"
                    + "                                                                    , buttons: ['copy', 'csv', 'excel', 'print']\n"
                    + "                                                                });";
            script += "  $('#refreshReportApproved2').DataTable( {\n"
                    + "        initComplete: function () {\n"
                    + "            this.api().columns([0,1,2,6]).every( function () {\n"          
                    + "                var column = this;\n"     
                    + "                var select = $('<select><option value=\"\"></option></select>')\n"
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
                    + "$('select').select2({\n"
                    + "                    width: '100%'\n"
                    + "                });";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static void main(String[] args) throws Exception {

        taskCommettesReminder();
    }

    public static void taskCommettesReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskCommettesReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(tableName, _isActive + "=1 AND " + _period + " !='' "));
        System.out.println("size=" + rows.size());
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("rows.get(i).get(_date).toString()=" + rows.get(i).get(_date).toString());
            jjCalendar_IR date = new jjCalendar_IR(rows.get(i).get(_date).toString());
            int period = Integer.valueOf(rows.get(i).get(_period).toString());
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            while (today > date.getDBFormat_8length()) {
                date.addDay(period);
            }
            if (date.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                String secretaryId = Role.getUeserIdByUserRole(rows.get(i).get(_secretary).toString(), db);
                System.out.println("secretaryId=" + secretaryId);
                String text = "دبیر محترم  "
                        + " "
                        + rows.get(i).get(_title).toString()
                        + " "
                        + "لطفا نسبت به تنظیم دستور جلسه ی کمیته"
                        + " "
                        + " از قسمت تعریف وتنظیم کمیته ها  اقدام فرمایید";
                Messenger.sendMesseage(null, db, secretaryId, "1", "sms,app,email", null, "یاد آوری سامانه : جلسه دوره ای  " + rows.get(i).get(_title), text, text, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                System.out.println("<<<<<<<ارسال پیام برای دبیر کمیته و رئیس کمیتهSend MESSAGE()");
                System.out.println("#################################################");
            }
        }
    }
//    public static void taskNextSessionsReminder() throws Exception {
//        System.out.println("#################################################");
//        System.out.println("######>>>>>RUN:taskNextSessionsReminder###########");
//        Server.Connect();
//        jjDatabaseWeb db = Server.db;
//        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Sessions.tableName,Sessions._nextDate));
//        System.out.println("size=" + rows.size());
//        for (int i = 0; i < rows.size(); i++) {
//            System.out.println("rows.get(i).get(_date).toString()=" + rows.get(i).get(Sessions._nextDate).toString());
//            jjCalendar_IR date = new jjCalendar_IR(rows.get(i).get(Sessions._nextDate).toString());
//            int period = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeNextSessions_name).toString());
//            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
//            while (today > date.getDBFormat_8length()) {
//                date.addDay(period);
//            }
//            if (date.getDBFormat_8length() == today) {
//                System.out.println(">>>>>>>" + today);
//                String creatorId = rows.get(i).get(Sessions._creatorId).toString();
//                String text = Access_User.getUserName(creatorId,db)+"  " + rows.get(i).get(_title).toString() + "لطفا نسبت به تنظیم دستور جلسه ی کمیته از قسمت تعریف وتنظیم کمیته ها  اقدام فرمایید";
//                Messenger.sendMesseage(null, db, creatorId, "1", "sms,app,email", null, "یاد آوری سامانه : جلسه دوره ای  " + rows.get(i).get(_title), text, text, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
//                System.out.println("<<<<<<<ارسال پیام برای ایجاد کننده صورتجلسهSend MESSAGE()");
//                System.out.println("#################################################");
//            }
//        }
//    }

    public static void taskNextPlansReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskNextPlansReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Plans.tableName, Plans._date));
        System.out.println("size=" + rows.size());
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("rows.get(i).get(_date).toString()=" + rows.get(i).get(Plans._date).toString());
            jjCalendar_IR date = new jjCalendar_IR(rows.get(i).get(Plans._date).toString());
            int period = Integer.valueOf(rows.get(i).get(Plans._period).toString());
            int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
            while (today > date.getDBFormat_8length()) {
                date.addDay(period);
            }
            if (date.getDBFormat_8length() == today) {
                System.out.println(">>>>>>>" + today);
                String creatorId = rows.get(i).get(Plans._creatorId).toString();
                String text = " مسئول محترم "
                        + rows.get(i).get(Plans._title).toString()
                        + "لطفا نسبت به تنظیم برنامه "
                        + "<br/>"
                        + " از قسمت ماژول برنامه های عملیاتی و بهبود کیفیت اقدام فرمایید";
                Messenger.sendMesseage(null, db, creatorId, "1", "sms,app,email", null, "یاد آوری سامانه : برنامه دوره ای  " + rows.get(i).get(Plans._title), text, text, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                System.out.println("<<<<<<<ارسال پیام برای ایجاد کننده برنامه عملیاتیSend MESSAGE()");
                System.out.println("#################################################");
            }
        }
    }

    public static void taskStepsReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskStepsReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Steps.tableName, Steps._status + "='" + Steps.status_inDoing + "' AND " + Steps._isActive + "=0"));
        System.out.println("size=" + rows.size());
        if (rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                System.out.println("rows.get(i).get(_Enddate).toString()=" + rows.get(i).get(Steps._endDate).toString());
                System.out.println("rows.get(i).get(_Startdate).toString()=" + rows.get(i).get(Steps._startDate).toString());
                jjCalendar_IR startDate = new jjCalendar_IR(rows.get(i).get(Steps._startDate).toString());
                jjCalendar_IR EndDate = new jjCalendar_IR(rows.get(i).get(Steps._endDate).toString());
                int periodStartDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeStepsStartDate_name).toString());
                int periodEndDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeStepsEndDate_name).toString());
                int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
                if (today < startDate.getDBFormat_8length()) {
                    startDate.addDay(periodStartDate);
                }
                if (today < EndDate.getDBFormat_8length()) {
                    EndDate.addDay(periodEndDate);
                }

                String textHtml = "";
                if (startDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Steps._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Steps._executorUserId).toString();
                        String text = "مسئول اجرا محترم گام باعنوان :"
                                + " " + rows.get(i).get(Steps._title) + "در تاریخ "
                                + " "
                                + " " + rows.get(i).get(Steps._startDate).toString() + " آغاز می شود"
                                + "";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else if (rows.get(i).get(Steps._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم  گام باعنوان :"
                                + " " + rows.get(i).get(Steps._title) + "در تاریخ  "
                                + " " + rows.get(i).get(Steps._startDate).toString() + ""
                                + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._trackerId).toString(), db);
                    String text = "مسئول پیگیری محترم  گام باعنوان :"
                            + " " + rows.get(i).get(Steps._title) + "در تاریخ "
                            + " "
                            + " " + rows.get(i).get(Steps._startDate).toString() + " "
                            + " آغاز می شود.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    System.out.println("<<<<<<<ارسال پیام مسئول اجرا و پیگیریSend MESSAGE()");
                    System.out.println("#################################################");
                }
                if (EndDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Steps._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Steps._executorUserId).toString();
                        String text = "مسئول اجرا محترم  گام باعنوان :"
                                + " " + rows.get(i).get(Steps._title) + "در تاریخ "
                                + " " + rows.get(i).get(Steps._endDate).toString() + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else if (rows.get(i).get(Steps._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم گام باعنوان :"
                                + "" + rows.get(i).get(Steps._title) + " "
                                + "در تاریخ "
                                + " " + rows.get(i).get(Steps._endDate) + " "
                                + "پایان می یابد.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._trackerId).toString(), db);

                    String text = "مسئول پیگیری محترم گام باعنوان :"
                            + " " + rows.get(i).get(Steps._title) + "در تاریخ "
                            + " " + rows.get(i).get(Steps._endDate) + " "
                            + "پایان می یابد.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری", "", Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name), Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    System.out.println("#################################################");
                }
            }
        }
    }

    public static void taskApprovedReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskApprovedReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Approved.tableName, Approved._status + "='" + Approved.status_inDoing + "' AND " + Approved._isActive + "=0"));
        System.out.println("size=" + rows.size());
        if (rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                System.out.println("rows.get(i).get(_Enddate).toString()=" + rows.get(i).get(Approved._endDate).toString());
                System.out.println("rows.get(i).get(_Startdate).toString()=" + rows.get(i).get(Approved._startDate).toString());
                jjCalendar_IR startDate = new jjCalendar_IR(rows.get(i).get(Approved._startDate).toString());
                jjCalendar_IR EndDate = new jjCalendar_IR(rows.get(i).get(Approved._endDate).toString());
                int periodStartDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeApprovedStartDate_name).toString());
                int periodEndDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeApprovedEndDate_name).toString());
                int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
                if (today < startDate.getDBFormat_8length()) {
                    startDate.addDay(periodStartDate);
                }
                if (today < EndDate.getDBFormat_8length()) {
                    EndDate.addDay(periodEndDate);
                }

                String textHtml = "";
                if (startDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Approved._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Approved._executorUserId).toString();
                        String text = "مسئول اجرا  محترم مصوبه باعنوان :"
                                + rows.get(i).get(Approved._title)
                                + "در تاریخ "
                                + startDate + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    } else if (rows.get(i).get(Approved._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم مصوبه باعنوان :"
                                + " " + rows.get(i).get(Approved._title) + " "
                                + "در تاریخ "
                                + " " + rows.get(i).get(Approved._startDate).toString() + " "
                                + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._trackerId).toString(), db);
                    String text = "مسئول پیگیری محترم  مصوبه باعنوان :"
                            + " " + rows.get(i).get(Approved._title) + "در تاریخ "
                            + " "
                            + " " + rows.get(i).get(Approved._startDate).toString() + " "
                            + "آغاز می شود .";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری", "", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    System.out.println("<<<<<<<ارسال پیام مسئول اجرا و پیگیریSend MESSAGE()");
                    System.out.println("#################################################");
                }
                if (EndDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Approved._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Approved._executorUserId).toString();
                        String text = "مسئول اجرا محترم مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ "
                                + " " + rows.get(i).get(Approved._endDate).toString() + ""
                                + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    } else if (rows.get(i).get(Approved._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم  مصوبه باعنوان :"
                                + " " + rows.get(i).get(Approved._title) + "در تاریخ "
                                + " " + rows.get(i).get(Approved._endDate).toString() + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._trackerId).toString(), db);

                    String text = "مسئول پیگیری محترم  مصوبه باعنوان :"
                            + " " + rows.get(i).get(Approved._title) + " "
                            + "در تاریخ "
                            + " "
                            + " " + rows.get(i).get(Approved._endDate).toString() + "پایان می یابد.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "", "یادآوری", Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name), Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    System.out.println("#################################################");
                }
            }
        }
    }

    /**
     * کپی از کمیته shiran2 13990612
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

            String commettesId = jjTools.getParameter(request, _id).toString();
            System.out.println("commettesId=" + commettesId);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Commettes.tableName, Commettes._id + "=" + commettesId));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, "کپی" + " " + row.get(0).get(_title));
            map.put(_date, row.get(0).get(_date));
            map.put(_regulationFile, row.get(0).get(_regulationFile));
            map.put(_description, row.get(0).get(_description));
            map.put(_isActive, row.get(0).get(_isActive));
            map.put(_members, row.get(0).get(_members));
            map.put(_documnetsFile, row.get(0).get(_documnetsFile));
            map.put(_period, row.get(0).get(_period));
            map.put(_superwizar, row.get(0).get(_superwizar));
            map.put(_secretary, row.get(0).get(_secretary));
            List<Map<String, Object>> insertedCommettesRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));

            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            Server.outPrinter(request, response, "hmisCommettes.m_select(" + insertedCommettesRow.get(0).get(_id) + ");");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
