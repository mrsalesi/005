/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import static HMIS.Documentary.status_noUploaded;
import static HMIS.FormAnswerSet._formId;
import static HMIS.FormAnswerSet._userGroup;
import static HMIS.FormAnswerSet._userId;
import static HMIS.FormAnswerSet._userRole;
import static HMIS.FormAnswerSet.statues_sabteAvalie;
import cms.access.Access_Group;
import cms.access.Access_User;
import cms.cms.Content;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shiran1 بخش ها
 */
public class Department {

    public static String tableName = "Department";
    public static String _id = "id";

    public static String _title = "department_title";
//    public static String _role_id = "department_role_id";
    public static String _user_id = "department_user_id";

    public static String _publicContent = "department_publicContent";
    public static String _praivateContent = "department_praivateContent";
    public static String _organizationalCode = "department_organizationalCode";
    public static String _description = "department_description";
    public static String _location = "department_location";
    public static String _level = "department_level";//تعیین سطح  بخش ها
    public static String _pic = "department_pic";
    public static String _icon = "department_icon";

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

//    public static int rul_rfs = 251;
//    public static int rul_ins = 252;
//    public static int rul_edt = 253;
//    public static int rul_dlt = 254;
    public static int rul_rfs = 131;
    public static int rul_ins = 132;
    public static int rul_edt = 133;
    public static int rul_dlt = 134;

    /**
     * این جدول مخصوص بخش ها ست
     *
     * @param height is int height of table
     * @param sort is number of default sort column number
     * @param panel is container id
     */
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
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            DefaultTableModel dtm = db.Select(Department.tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary'>"
                        + "    <div class='card-header bg-primary tx-white'>بخش ها</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary'>"
                        + "    <div class='card-header bg-primary tx-white'>بخش ها</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisDepartment.m_add_new();' > بخش جدید</a>"
                        + "        </p>");
            }
            html.append("<table class='table display' id='refreshParts' dir='rtl'><thead>");
            html.append("<th style='text-align: center;' width='5%'>کد</th>");
            html.append("<th style='text-align: center;' width='30%'>نام بخش</th>");
            html.append("<th style='text-align: center;' width='20%'>موقعیت</th>");
            html.append("<th style='text-align: center;' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='hmisDepartment.m_select(" + row.get(i).get(_id) + ");' class='mousePointer' >");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='tahoma10' style='text-align: center;'>" + getDepartmentName(row.get(i).get(_location).toString(), db) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='hmisDepartment.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div></div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swDepartmentTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshParts", "auto", 0, "", "لیست نقش ها");

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این متد بخش ها و زیر بخش ها را نشان میدهد توجه داشته باشید سلکت باید در
     * قسمت اچ تی ام ال سمت کلاینت موجود باشد و اینجا فقط آپشن ها بصورت سلسله
     * مراتبی ایجاد می شوند
     *
     * @param request panel درون ریکوئست
     * @param response
     * @param db
     * @param needString
     * @Server.outPrinter(request, response,بصورت کد جی کوئری و یک سری آپشن برای
     * قرار گرفتن در سلکتی که در پنل معرفی شده
     * @throws Exception
     */
    public static String selectOptionDepartment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        String html2 = "";
        try {
            String panel = jjTools.getParameter(request, "panel");
            Document doc = Jsoup.parse("<option value='' id='5'>بخش را انتخاب کنید</option>");
            List<Map<String, Object>> rowDepartments = jjDatabase.separateRow(db.otherSelect("SELECT * FROM department  ORDER BY department_location"));// بر اساس
            for (int i = 0; i < rowDepartments.size(); i++) {
                doc.select("#" + rowDepartments.get(i).get(_location)).after("<option id='" + rowDepartments.get(i).get(_id)
                        + "'  value='" + rowDepartments.get(i).get(_id) + "'>"
                        + dash(rowDepartments.get(i).get(_level).toString())
                        + rowDepartments.get(i).get(_title)
                        + "</option>");
            }
            if (panel == "") {
                panel = ".department_location";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, doc.select("body").toString()));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این تابع برای گذاشتن dash کنار بخش ها براساس سطح آنهاست
     *
     * @param level
     * @return
     */
    private static String dash(String level) {
        String dashanswer = "";
        for (int i = 1; i < Integer.parseInt(level); i++) {
            dashanswer += "-";
        }
        return dashanswer;
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        StringBuilder script = new StringBuilder();
        try {
            script.append("$('#department_location').select2();\n");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
//            if (accIns) {
//                html.append(Js.setHtml("#Department_button", "<div class='row'><div class='col-lg-6'><input type='button' id='insert_Department_new'  value=\"" + lbl_insert + "\" class='tahoma10 btn btn-success btn-block mg-b-10 ui-button ui-corner-all ui-widget'></div></div>"));
//                html.append(Js.buttonMouseClick("#insert_Department_new", Js.jjDepartment.insert()));
//            }
            if (accIns) {
                script.append(Js.setHtml("#Department_button", "<div class='col-lg-6'><input type='button' id='insert_Department_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'></div>"));
                script.append(Js.click("#insert_Department_new", Js.jjDepartment.insert()));
            }
            Server.outPrinter(request, response, html.toString() + script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * نمایش اطلاعات عمومی و خصوصی بخش در صفحه اولیه
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getViewDepartment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            StringBuilder html6 = new StringBuilder();
            StringBuilder html7 = new StringBuilder();
            String script = "";
            String script2 = "";
            String id = jjTools.getParameter(request, "id");
            String panel = jjTools.getParameter(request, "panel");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Department.tableName, _id + "=" + id));
            List<Map<String, Object>> row2 = jjDatabaseWeb.separateRow(db.Select(Department.tableName, _location + "=" + id));
            if (Integer.valueOf(id) != 0) {
                script += "$('#swDepartmentLevel" + row.get(0).get(_level) + "').remove();";
                List<Map<String, Object>> rowDepartment = jjDatabaseWeb.separateRow(db.Select(Department.tableName));
                for (int k = 0; k < rowDepartment.size(); k++) {///برای پاک کردن سطح های قبل 
                    int LevelSelected = Integer.parseInt(row.get(0).get(_level).toString());///سطح که روی ان کلیک شده است
                    int Level = Integer.parseInt(rowDepartment.get(k).get(_level).toString());//دراوردن سطح ها از جدول بخش ها
                    if (LevelSelected < Level) {// درصورتی که سطح انتخابیکوچکتر از سطح های موجود در جدول بخش ها بودیکی یکی سطح ها را خالی کن
                        script += "$('#swDepartmentLevel" + Level + "').remove();";
                    }
                }
                html.append(
                        "<div class='col-lg-12 mg-t-10 '  id='swDepartmentLevel" + row.get(0).get(_level) + "'></div>"
                        + ""
                        + "\n");

                html3.append("<div class='sh-pagebody'> "
                        + " <div class='row row-sm d-flex justify-content-center align-items-center'>");
                html3.append(""
                        + "          <div class=\"col-lg-12 mg-t-10 \">\n"
                        + "            <div class=\"row row-xs \">\n"
                        + "");
                for (int i = 0; i < row2.size(); i++) {
                    int level = Integer.parseInt(row2.get(0).get(_level).toString());
                    html3.append(""
                            + "              <div class=\"col-6 col-sm-4 col-md\">\n"
                            + "                <a id='level" + row2.get(i).get(_id) + "'   class=\"shortcut-icon \"    onclick='hmisDepartment.getViewDepartment(" + row2.get(i).get(_id) + ");'>"
                            + "                  <div>");
                            if(!row2.get(i).get(_icon).equals("")){
                            html3.append( " <img class=\"wd-60 rounded-circle\" src='upload/" + row2.get(i).get(_icon) + "'>");
                            }else{
                            }
                             html3.append( "                    <span ></span>\n"
                            + "                    <span >" + row2.get(i).get(_title) + "</span>\n"
                            + "                  </div>\n"
                            + "                </a>\n"
                            + "              </div><!-- col -->\n"
                            + "");
                    script += "$(document).on('click','#level" + row2.get(i).get(_id) + "', function () {"
                            + "$('a.shortcut-icon').removeClass('activeDepartment');$('#level" + row2.get(i).get(_id) + "').addClass('activeDepartment');"
                            + "});";

                }
                html3.append("</div>");
                html3.append("</div>");
                html3.append("</div>");
                html3.append("</div></div>");
                html2.append("<div class=''> "
                        + " <div class='row '>");
                html2.append(""
                        + "          <div class=\"col-lg-12 mg-t-10\">\n"
                        + "            <div class=\"col-md-12 \">\n"
                        + "");
                html2.append(""
                        + "<div class='slideToggle card-header bg-primary tx-white'>    مستندات بخش </div>  "
                        + "<div class='card rounded-0 card bd-primary'> "
                        + "       <div class='card-body'> "
                        + "          <div class='row'> "
                        + "     "
                        + "      <div class='col-lg-12' >     "
                        + "        <div class='card bd-primary mg-t-20'> "
                        + "             <div class='slideToggle card-header bg-teal tx-white'>               مستندات عمومی              </div>");
                html2.append("<div class=\"slideToggle-Body\">"
                        + " "
                        + "<div class='col-lg-12'>"
                        + Content.ConvertToWiki(request, row.get(0).get(_publicContent).toString(), db, needString)
                        + "</div></div></div></div>");
                html2.append(" <div class='col-lg-12' >" +
"          <div class=' card bd-primary mg-t-20'> " +
"   <div class='slideToggle card-header bg-teal tx-white'>               مستندات اختصاصی              </div>");
                html2.append("<div class=\"slideToggle-Body\">"
                        + Content.ConvertToWiki(request, row.get(0).get(_praivateContent).toString(), db, needString)
                        + ""
                        + "</div></div></div></div>");
                html2.append("</div>");
                html2.append("</div></div></div></div>");
                DefaultTableModel dtm;

                dtm = db.otherSelect("SELECT "
                        + Role._title + ","
                        + Access_User._name + " ,"
                        + Access_User._family + " ,"
                        + Documentary._LoadingPeriod + " ,"
                        + Documentary._attachFileDocumentary + " ,"
                        + Documentary._dateCreation + " ,"
                        + Documentary._gaugeId + " ,"
                        + Documentary._nameResponsibleLoading + " ,"
                        + Documentary._responsibleLoadingRole + " ,"
                        + Documentary._status + " ,"
                        + ManagementGauges._gauge + ","
                        + ManagementGauges._responsibleGauge + " ,"
                        + ManagementGauges._responsibleLoadingRole + " ,"
                        + ManagementGauges._date + " ,"
                        + ManagementGauges._axis + " ,"
                        + ManagementGauges._standard + " ,"
                        + ManagementGauges._underTheAxis + " ,"
                        + "MG.id  as managementgaugesId,"
                        + "DO.id  as documentaryId,"
                        + ManagementGauges._step + " "
                        + " FROM "
                        + ManagementGauges.tableName + " MG"
                        + " LEFT JOIN " + Documentary.tableName + " DO  ON " + "MG.id =" + Documentary._gaugeId
                        + " LEFT JOIN " + Role.tableName + " ON " + ManagementGauges._responsibleLoadingRole + "=hmis_role.id" // برای استخراج نام مسئول بارگذاری و نقش او
                        + " LEFT JOIN " + Access_User.tableName + " ON " + Role._user_id + " = access_user.id " + ""
                        + " WHERE "
                        + ManagementGauges._department + "=" + id + ""
                        + " group by " + Documentary._gaugeId
                );
                List<Map<String, Object>> rowgague = jjDatabase.separateRow(dtm);
                if (rowgague.size() > 0) {
                    html4.append(" "
                            + "<div class=\"col-md-12 \">"
                            + "<div class='slideToggle card-header bg-danger tx-white mg-b-0 mg-t-20'>سنجه های این بخش</div>"
                            + "<div class=\"card card-body rounded-0 bd-primary\">\n"
                            + "                        <div class=\"col-md-12 mg-t-10 \">\n"
                            + "                            <div class=\"card bd-primary mg-t-20\">");
                    for (int j = 0; j < rowgague.size(); j++) {
                        html4.append("<div class='slideToggle  card-header bg-primary tx-white'>"
                                + "" + rowgague.get(j).get(ManagementGauges._standard)
                                + "</div>"
                                + "<div class=\"slideToggle-Body\">\n"
                                + "<div class=\"card bd-primary pd-sm-30 \">\n"
                                + "<div class=\"card-header pd-sm-10\" style=\"background-color: #98e098\">\n"
                                + "<p class=\"\" style=\"color: black\">سنجه" + "-" + rowgague.get(j).get(ManagementGauges._gauge) + "</p></div>"
                        );
                        html4.append("<div class=\"col-md-12 mg-t-4\">"
                                + "<div class=\"row\"><!-- col -->\n"
                                + "<div class=\"card-header bg-purple tx-white\">"
                                + "گام" + "-" + rowgague.get(j).get(ManagementGauges._step) + "</div>");
                        html4.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
                        html4.append("<table class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'  id='refreshMangeMentGagues' ><thead>");
                        html4.append("<th style=\"background-color: #7e57c3\"  class='c' width='5%'>کد</th>");
                        html4.append("<th style=\"background-color: #7e57c3\" class='c' width='30%'>مسئول بارگذاری</th>");
                        html4.append("<th style=\"background-color: #7e57c3\" class='c' width='30%'>نام بارگذاری کننده</th>");
                        html4.append("<th style=\"background-color: #7e57c3\"  class='c' width='30%'>دوره بارگذاری</th>");
                        html4.append("<th style=\"background-color: #7e57c3\" class='c' width='30%'>تاریخ ایجاد (سیستمی)</th>");
                        html4.append("<th style=\"background-color: #7e57c3\"  class='c' width='30%'>وضعیت بارگذاری</th>");
                        html4.append("<th  style=\"background-color: #7e57c3\" class='c' width='30%'>دریافت/بارگذاری</th>");
                        html4.append("</thead><tbody>");
                        html4.append("<tr onclick='hmisDocumentary.selectDocumentaryDepartment(" + rowgague.get(j).get("documentaryId") + ");'   class='odd " + Documentary.getClassByStatus(rowgague.get(j).get(Documentary._status).toString()) + " '>");
                        html4.append("<td  class='c'>" + (rowgague.get(j).get("managementgaugesId").toString()) + "</td>");
                        html4.append("<td class='c' >"
                                + (rowgague.get(j).get(Role._title).toString()) + "<br/>"
                                + "</td>");
                        html4.append("<td class='c' >"
                                + (rowgague.get(j).get(Access_User._name).toString() + " " + rowgague.get(j).get(Access_User._family).toString())
                                + "</td>");
                        html4.append("<td class='c' >" + (rowgague.get(j).get(Documentary._LoadingPeriod).toString()) + "</td>");
                        html4.append("<td class='c' >" + jjCalendar_IR.getViewFormat(rowgague.get(j).get(Documentary._dateCreation).toString()) + "</td>");

                        html4.append("<td  class='c'>" + (rowgague.get(j).get(Documentary._status).toString()) + "</td>");
                        if (rowgague.get(j).get(Documentary._status).equals(status_noUploaded)) {
                            html4.append("<td><i  class=\"icon ion-upload\"></i></td>");
                        } else {
                            html4.append("<td  class='c'><i style=\"color: #0000ff\" class=\"fa fa-download\"></i></td>");
                        }

                        html4.append("</tr>");
                        html4.append("</table>");
                        String FlagsHtml = "";
                        if (!rowgague.get(j).get(Documentary._status).toString().isEmpty()) {
                            String statusFlags[] = rowgague.get(j).get(Documentary._status).toString().split(",");
                            for (int v = 0; v < statusFlags.length; v++) {
                                if (statusFlags[v].equals(Documentary.status_Uploaded)) {
                                    FlagsHtml += "<i class='icon ion-flag' style='color:green;font-size: 1em;padding: 0 5px;'></i>";
                                } else if (statusFlags[v].equals(Documentary.status_noUploaded)) {
                                    FlagsHtml += "<i class='icon ion-flag' style='color:red;font-size: 1em;padding: 0 5px;'></i>";
                                }
                            }
                        }
                        html4.append(FlagsHtml);
                        html4.append("</div></div></div></div></div></div>");
//                        html4.append("$('.slideToggle-Body').slideUp();$('.slideToggle').click(function () {        $(this).parent().find('.slideToggle-Body').slideToggle('slow');    });");

                    }
                    html4.append("</div></div></div></div></div>");

                }
                ///آوردن فرم ها از جدول فرم براساس بخش
                List<Map<String, Object>> formRows = jjDatabaseWeb.separateRow(
                        db.otherSelect("SELECT "
                                + FormAnswerSet.tableName + ".*,"
                                + Role._title + ","
                                + Access_Group._title + ","
                                + Forms._title
                                + " FROM " + FormAnswerSet.tableName
                                + " LEFT JOIN " + Forms.tableName + " ON   hmis_forms.id=" + FormAnswerSet._formId
                                + " LEFT JOIN " + Role.tableName + " ON   hmis_role.id=" + FormAnswerSet._userRole
                                + " LEFT JOIN " + Access_Group.tableName + " ON   access_group.id=" + FormAnswerSet._userGroup
                                + " WHERE  " + Forms._departments + "=" + id + " group by  hmis_forms.id"));
                if (formRows.size() > 0) {
                    html5.append(" "
                            + "    <div class=\"col-md-12 \"><div class='slideToggle card-header bg-warning  tx-white'>فرم های و چک لیست های اختصاصی بخش</div>"
                            + " <div class=\"card card-body rounded-0 bd-primary\">\n"
                            + "                        <div class=\"col-md-12 mg-t-10 \">\n"
                            + "                            <div class=\"card bd-primary mg-t-20\">");
                    html5.append("<div class=\"slideToggle-Body\"><div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
                    html5.append("<table class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;' id='refreshformsDepartment'><thead>");
                    html5.append("<th   class='c' width='5%'>کد</th>");
                    html5.append("<th  class='c' width='30%'>عنوان فرم</th>");
                    html5.append("<th  class='c' width='30%'>تکمیل فرم</th>");
                    html5.append("</thead><tbody>");
                    for (int k = 0; k < formRows.size(); k++) {
                        html5.append("<tr   class='mousePointer' >");
                        html5.append("<td  class='c'>" + (formRows.get(k).get(Forms._id).toString()) + "</td>");
                        html5.append("<td  class='c'>" + (formRows.get(k).get(Forms._title).toString()) + "</td>");
                        if (formRows.get(k).get(FormAnswerSet._status).equals(statues_sabteAvalie)) {// اگر ثبت اولیه بود آیکن ویرایش و تایع ویرایش را بگذاریم
                            html5.append("<td class='c'><a href='Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=" + formRows.get(k).get(FormAnswerSet._formId) + "&id=" + formRows.get(k).get(FormAnswerSet._id) + "' target='_blank'"
                                    + "><i class='fa fa-pencil' " + "</i></a></td>");
                        } else {
                            html5.append("<td class='c'><a href='Server?do=FormAnswerSet.showResult&formAnswers_formId=" + formRows.get(k).get(FormAnswerSet._formId) + "&id=" + formRows.get(k).get(FormAnswerSet._id) + "' target='_blank'"
                                    + "><i class='fa fa-eye' style='font-size: 2em;color: #b535e3;' " + "</i></a></td>");
                        }
//                        html5.append("<td class=\"c \"><i class=\"icon ion-clipboard\" style=\"color:#3610ff\" ></i></td>");
                        html5.append("</tr>");
                    }
                    html5.append("</table></div></div></div></div></div></div>");
                }
                ///آوردن برنامه عملیاتی   براساس بخش
                List<Map<String, Object>> rowPlans = jjDatabaseWeb.separateRow(db.Select(Plans.tableName, Plans._department + "=" + id));
                if (rowPlans.size() > 0) {
                    html6.append(" "
                            + "    <div class=\"col-md-12 \"><div class='slideToggle card-header bg-pink tx-white'>برنامه های عملیاتی و بهبود کیفیت</div>"
                            + " <div class=\"card card-body rounded-0 bd-primary\">\n"
                            + "                        <div class=\"col-md-12 mg-t-10 \">\n"
                            + "                            <div class=\"card bd-primary mg-t-20\"><div class=\"slideToggle-Body\">");
                    html6.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
                    html6.append("<table class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;' id='refreshPlansDepartment'><thead>");
                    html6.append("<th   class='c' width='5%'>کد</th>");
                    html6.append("<th   class='c' width='5%'>بخش</th>");
                    html6.append("<th  class='c' width='20%'>مسئول ایجاد برنامه</th>");
                    html6.append("<th  class='c' width='20%'>هدف کلان</th>");
                    html6.append("<th  class='c' width='20%'>هدف اختصاصی</th>");
                    html6.append("<th  class='c' width='20%'>عنوان استراتژی</th>");
                    html6.append("<th  class='c' width='10%'>تاریخ</th>");
                    html6.append("<th  class='c' width='10%'>پایش</th>");
                    html6.append("</thead><tbody>");
                    for (int n = 0; n < rowPlans.size(); n++) {
                        html6.append("<tr   class='mousePointer' onclick='hmisPlans.selectPlansDepartment(" + rowPlans.get(n).get(Plans._id) + ");'>");
                        html6.append("<td  class='c'>" + (rowPlans.get(n).get(Plans._id).toString()) + "</td>");
                        html6.append("<td  class='c'>" + Department.getDepartmentName(rowPlans.get(n).get(Plans._department).toString(), db) + "</td>");
                        html6.append("<td  class='c'>" + Role.getRoleUserName(rowPlans.get(n).get(Plans._managerRoleId).toString(), db) + "</td>");
                        if (jjNumber.isDigit(rowPlans.get(n).get(Plans._hugeGoal).toString())) {
                            List<Map<String, Object>> totalRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + rowPlans.get(n).get(Plans._hugeGoal)));
                            if (totalRow.size() > 0) {
                                html6.append("<td  class='c'>" + (totalRow.get(0).get(TotalTarget._title).toString()) + "</td>");
                            } else {
                                html6.append("<td  class='c'></td>");
                            }
                        } else {
                            html6.append("<td  class='c'></td>");
                        }
                        if (jjNumber.isDigit(rowPlans.get(n).get(Plans._minorGoal).toString())) {
                            List<Map<String, Object>> proprietaryRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + rowPlans.get(n).get(Plans._minorGoal)));
                            if (proprietaryRow.size() > 0) {
                                html6.append("<td  class='c'>" + (proprietaryRow.get(0).get(ProprietaryTarget._title).toString()) + "</td>");
                            } else {
                                html6.append("<td  class='c'></td>");
                            }
                        } else {
                            html6.append("<td  class='c'></td>");
                        }
                        if (jjNumber.isDigit(rowPlans.get(n).get(Plans._strategic).toString())) {
                            List<Map<String, Object>> strategyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._id + "=" + rowPlans.get(n).get(Plans._strategic)));
                            if (strategyRow.size() > 0) {
                                html6.append("<td  class='c'>" + (strategyRow.get(0).get(Strategy._title).toString()) + "</td>");
                            } else {
                                html6.append("<td  class='c'></td>");
                            }
                        } else {
                            html6.append("<td  class='c'></td>");
                        }
                        html6.append("<td  class='c'>" + (jjCalendar_IR.getViewFormat(rowPlans.get(n).get(Plans._date).toString())) + "</td>");
                        html6.append("<td class='c'><i class='icon ion-compose' ></i></td>");
                        html6.append("</tr>");
                    }
                    html6.append("</table></div></div></div></div></div></div>");
                }
                ///آوردن شاخص ها از جدول indicator براساس بخش
                List<Map<String, Object>> rowIndicator = jjDatabaseWeb.separateRow(db.Select(Indicators.tableName, Indicators._department_id + "=" + id));
                if (rowIndicator.size() > 0) {
                    html7.append(" "
                            + "    <div class=\"col-md-12 \"><div class='slideToggle card-header bg-indigo tx-white'>شاخص ها</div>"
                            + " <div class=\"card card-body rounded-0 bd-primary\">\n"
                            + "                        <div class=\"col-md-12 mg-t-10 \">\n"
                            + "                            <div class=\"card bd-primary mg-t-20\"><div class=\"slideToggle-Body\">");
                    html7.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
                    html7.append("<table class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;' id='refreshIndicatorDepartment' ><thead>");
                    html7.append("<th   class='c' width='5%'>کد</th>");
                    html7.append("<th   class='c' width='5%'>عنوان شاخص</th>");
                    html7.append("<th  class='c' width='10%'>بخش</th>");
                    html7.append("<th  class='c' width='20%'>مسئول شاخص</th>");
                    html7.append("<th  class='c' width='20%'>سمت تکمیل کننده</th>");
                    html7.append("<th  class='c' width='20%'>تناوب جمع آوری داده</th>");
                    html7.append("<th  class='c' width='10%'>مشاهده</th>");
                    html7.append("</thead><tbody>");
                    for (int m = 0; m < rowIndicator.size(); m++) {
                        html7.append("<tr  onclick='hmisIndicators.selectIndicatorDepartment(" + rowIndicator.get(m).get(Indicators._id) + ");' class='mousePointer' >");
                        html7.append("<td  class='c'>" + (rowIndicator.get(m).get(Indicators._id).toString()) + "</td>");
                        html7.append("<td  class='c'>" + (rowIndicator.get(m).get(Indicators._title).toString()) + "</td>");
                        html7.append("<td  class='c'>" + Department.getDepartmentName(rowIndicator.get(m).get(Indicators._department_id).toString(), db) + "</td>");
                        html7.append("<td  class='c'>" + Access_User.getUserName(rowIndicator.get(m).get(Indicators._responsibleUser).toString(), db) + "</td>");
                        String roles = rowIndicator.get(m).get(Indicators._responsibleRole).toString();
                        if (roles.length() > 1) {
                            String[] role = roles.split(",");
                            html7.append("<td  class='c'>");
                            for (int h = 0; h < role.length; h++) {
                                System.out.println("role" + role[h]);
                                html7.append("" + Role.getRoleName(role[h], db) + ",");
                            }
                            html7.append("</td>");
                        } else {
                            html7.append("<td  class='c'>" + Role.getRoleName(roles, db) + "</td>");
                        }
                        html7.append("<td  class='c'>" + (rowIndicator.get(m).get(Indicators._periodOfCollectiong).toString()) + "</td>");
                        html7.append("<td class=\"c \"><i class=\"icon ion-eye\" ></i></td>");
                        html7.append("</tr>");
                    }
                    html7.append("</table></div></div></div></div></div></div>");
                }
                script += "$('#swAccreditationAxes').show();\n";
                script += Js.table("#refreshformsDepartment", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "");
                script += Js.table("#refreshMangeMentGagues", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "");
                script += Js.table("#refreshIndicatorDepartment", "", 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "");
                script += Js.append("#swDepartmentLevel", html.toString());
                script += Js.append("#swDepartmentLevel" + row.get(0).get(_level), html3.toString());
                script += Js.setHtml("#sw", Content.ConvertToWiki(request, html2.toString(), db, needString));
                script += Js.setHtml("#swDepartmentMangeMentGauges", html4.toString());
                script += Js.setHtml("#swDepartmentForms", html5.toString());
                script += Js.setHtml("#swDepartmentPlans", html6.toString());
                script += Js.setHtml("#swDepartmentIndicator", html7.toString());
                script2 += "$('.slideToggle-Body').slideDown();    $('.slideToggle').click(function () {        $(this).parent().find('.slideToggle-Body').slideToggle('slow');    });";
            }
            Server.outPrinter(request, response, script + script2);
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String setGroupName(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String parentId = jjTools.getParameter(request, "parentId");
            String html2 = "";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Department.tableName, "*", Department._id + "=" + id));
            List<Map<String, Object>> rowparent = jjDatabase.separateRow(db.Select(Department.tableName, "*", Department._id + "=" + parentId));
            List<Map<String, Object>> rowparent1 = jjDatabase.separateRow(db.Select(Department.tableName, "*", Department._id + "=" + rowparent.get(0).get(_location).toString()));
            String level = row.get(0).get(_level).toString();
            if (Integer.valueOf(level) > 2 || Integer.valueOf(level) == 2) {//برای فرزندهایی که بیش از یک پدر دارند
                html.append(""
                        + "$('.organizational-chart').parent().parent().parent().find('textarea').val('" + rowparent.get(0).get(Department._title) + "-" + row.get(0).get(Department._title) + "');"
                        + "");
            } else {

                html.append(""
                        + "$('.organizational-chart').parent().parent().parent().find('textarea').val('" + row.get(0).get(Department._title) + "');"
                        + "");
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * تابع درج date 1397/11/7
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_user_id, jjTools.getParameter(request, _user_id));
            map.put(_organizationalCode, jjTools.getParameter(request, _organizationalCode));
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_publicContent, request.getParameter(_publicContent));
            map.put(_praivateContent, request.getParameter(_praivateContent));
            map.put(_location, jjTools.getParameter(request, _location));
            String parent = jjTools.getParameter(request, _location);
            int level = 0;
            if (!parent.equals("0")) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName, _id + "=" + parent));
                level = Integer.parseInt(rows.get(0).get(_level).toString());
            }
            map.put(_level, ++level);
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }

                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }

            Server.outPrinter(request, response, Js.jjDepartment.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     *
     * @param id
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }
            StringBuilder script = new StringBuilder();
            StringBuilder script1 = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            if (row.isEmpty()) {
//                String errorMessage = "رکوردی با این کد وجود ندارد.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Select Fail;";
//                }
//                Server.outPrinter(request, response,Js.dialog(errorMessage);
//            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            script.append(Js.setVal("#department_" + _id, row.get(0).get(_id)));
//////////////////////////////////////////////////////////////
            String userId = row.get(0).get(_user_id).toString();
            String[] userIdArray = userId.split(",");
            String temp = "";
            System.out.println("userIdArray=" + userIdArray.length);
            for (int i = 0; i < userIdArray.length; i++) {
                temp += "" + userIdArray[i] + ",";
            }
            html.append(Js.setVal("#" + _user_id, row.get(0).get(_user_id)));
            html.append(Js.setValSelectOption("#" + _user_id, temp));
            html.append(Js.select2("#" + _user_id, " width: '100%'"));
//////////////////////////////////////////////////////////////
//            String roleId = row.get(0).get(_role_id).toString();
//            String[] roleIdArray = roleId.split(",");
//            String temp2 = "";
//            System.out.println("roleIdArray=" + roleIdArray.length);
//            for (int i = 0; i < roleIdArray.length; i++) {
//                temp2 += "" + roleIdArray[i] + ",";
//            }
//            html.append(Js.setVal("#" + _role_id, row.get(0).get(_role_id)));
//            html.append(Js.setValSelectOption("#" + _role_id, temp2));
//            html.append(Js.select2("#" + _role_id, " width: '100%'"));

            script.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            script.append(Js.setAttr("#IconDownload", "href", "upload/" + row.get(0).get(_icon)));
            script.append(Js.setVal("#" + _organizationalCode, row.get(0).get(_organizationalCode)));
            script.append(Js.setVal("#department_icon", row.get(0).get(_icon)));
            html.append(Js.setAttr("#IconDownload", "href", "upload/" + row.get(0).get(_icon)));
            if (row.get(0).get(_icon).equals("")) {
                script.append(Js.setAttr("#PicPreviewIcon", "src", "img/preview.jpg"));
                script.append(Js.hide("#IconDownload"));
            } else {
                script.append(Js.setAttr("#PicPreviewIcon", "src", "upload/" + row.get(0).get(_icon).toString() + ""));
                script.append(Js.show("#IconDownload"));
            }

            script.append(Js.setVal("#" + _location, row.get(0).get(_location).toString()));
            script.append("$('#department_location').select2();\n");
            script.append(Js.setValSummerNote("#department_publicContent", row.get(0).get(_publicContent)));
            script.append(Js.setValSummerNote("#department_praivateContent", row.get(0).get(_praivateContent)));

            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjDepartment.edit() + "' id='edit_Department'>" + lbl_edit + "</button></div>";
//               
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjDepartment.delete(id) + "' id='delete_Department'>" + lbl_delete + "</button></div>";
            }
            script1.append(Js.setHtml("#Department_button", htmlBottons));
//            if (accEdt) {
////                if (!id.equals("1")) {
//                html2.append("<div class=\"row\"><div class=\"col-lg-6\"><input type=\"button\" id=\"edit_Department\" value=\"" + lbl_edit + "\" class=\"tahoma10 btn btn-success btn-block mg-b-10 ui-button ui-corner-all ui-widget\"/></div>");
//                html.append(Js.buttonMouseClick("#edit_Department", Js.jjDepartment.edit()));
////                }
//            }
//            if (accDel) {
////                if (!id.equals("1")) {
//                html2.append("<div class=\"col-lg-6\"><input type=\"button\" id=\"delete_Department\" value=\"" + lbl_delete + "\" class=\"tahoma10 btn btn-success btn-block mg-b-10 ui-button ui-corner-all ui-widget\"  /></div></div>");
//                html.append(Js.buttonMouseClick("#delete_Department", Js.jjDepartment.delete(id)));
////                }
//            }
            Server.outPrinter(request, response, html.toString() + script + script1);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * گرفتن نقش و کاربران در بخش مورد نظر
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getUserAndRoleInDepatrment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String userPanel = jjTools.getParameter(request, "userPanel");
            String id = jjTools.getParameter(request, _id);
            if (jjNumber.isDigit(id)) {
                String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
                if (!errorMessageId.equals("")) {
                    if (jjTools.isLangEn(request)) {
                        errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                    }
                    Server.outPrinter(request, response, Js.dialog(errorMessageId));
                    return "";
                }
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                String userId = row.get(0).get(_user_id).toString();
                String[] userIdArray = userId.split(",");
                if (!userId.equals("")) {
                    String user = "";
                    System.out.println("userIdArray=" + userIdArray.length);
                    for (int i = 0; i < userIdArray.length; i++) {
                        user += "" + userIdArray[i] + ",";
                    }
                    html.append("$('" + userPanel + "').val(" + row.get(0).get(_user_id) + ");");
                    html.append(Js.setValSelectOption(userPanel, user));
                    html.append(Js.select2(userPanel, "width: '100%'"));
                } else {
                }
            } else {
                html.append("$('" + userPanel + "').val('');$('" + userPanel + "').select2();");

            }
            /////////////////////////////////////////////////////////
//            String roleId = row.get(0).get(_role_id).toString();
//            String[] roleIdArray = roleId.split(",");
//            if (!roleId.equals("")) {
//                String role = "";
//                System.out.println("roleIdArray=" + roleIdArray.length);
//                for (int i = 0; i < roleIdArray.length; i++) {
//                    role += "" + roleIdArray[i] + ",";
//                }
//                html.append("$('"+rolePanel+"').val("+row.get(0).get(_role_id)+");");
//                html.append(Js.setValSelectOption(rolePanel, role));
//                html.append(Js.select2(rolePanel, "width: '100%'"));     
//            } else {
//            }
            Server.outPrinter(request, response, html);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
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
//           
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, request.getParameter(_title));
            map.put(_user_id, request.getParameter(_user_id));
            map.put(_organizationalCode, request.getParameter(_organizationalCode));
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_publicContent, request.getParameter("department_publicContent"));
            map.put(_praivateContent, request.getParameter("department_praivateContent"));
            ////برای نمایش موقعیت بخش
            map.put(_location, jjTools.getParameter(request, _location));
            System.out.println("id=" + id);

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjDepartment.refresh());
            return "";
//            Server.outPrinter(request, response,"";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
//            Content.catchProductTitle = null;
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }

            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }

            Server.outPrinter(request, response, Js.jjDepartment.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getDepartmentName(String departmentId, jjDatabaseWeb db) throws Exception {
        try {
            if (jjNumber.isDigit(departmentId)) {
                List<Map<String, Object>> department = jjDatabase.separateRow(db.Select(Department.tableName, Department._id + "=" + departmentId));
                if (!department.isEmpty()) {
                    return department.get(0).get(_title).toString();
                }
            }
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static void main(String[] args) {
        System.out.println("aaaaaaaaaaaa");
    }
}
