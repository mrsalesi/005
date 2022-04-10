/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

/**
 *
 * @author PadidarNB
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cms.access.Access_User;
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
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author Shiran1 نقش ها
 */
public class Role {

    public static String tableName = "hmis_role";
    public static String _id = "id";
    public static String _title = "role_title";
    public static String _user_id = "role_user_id";//
    public static String _condition = "role_condition";//sh1: فعال یاغیر فعال بودن نقش
//    public static String _condition2 = "role_condition2";//
    public static String _comment = "role_comment";//
    public static String _discription = "role_discription";//
    public static String _name = "role_name";//

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static int rul_rfs = 141;
    public static int rul_ins = 142;
    public static int rul_edt = 143;
    public static int rul_dlt = 144;
    public static int rul_rfsAll = 145;

    /**
     * این جدول مخصوص بخش ها ست
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws java.lang.Exception
     */
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
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

            DefaultTableModel dtm = db.Select(Role.tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>نقش ها</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>نقش ها</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisRole.m_add_new();' > نقش جدید</a>"
                        + "        </p>");
            }
            html.append("<table class='table display' id='refreshRole' dir='rtl'><thead>");
            html.append("<th  class='c' width='5%'>کد</th>");
            html.append("<th  class='c' width='30%'>عنوان</th>");
            html.append("<th  class='c' width='20%'>وضعیت</th>");

            html.append("<th style='text-align: center;' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='hmisRole.m_select(" + row.get(i).get(_id) + ");' class='mousePointer' >");
                html.append("<td  class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td  class='c'>" + (row.get(i).get(_title).toString()) + "</td>");
                if ((row.get(i).get(_condition).toString()).equals("1")) {
                    html.append("<td  class='c'>فعال</td>");
                } else {
                    html.append("<td  class='c'>غیر فعال</td>");
                }
//                html.append("<td class='tahoma10' style='text-align: right;'>" + (row.get(i).get(_condition2).toString()) + "</td>");

                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='hmisRole.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div></div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swRoleTbl";
            }
            String script = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            script += Js.table("#refreshRole", "300", 0, "", "لیست نقش ها");

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * آی دی کاربر را می گیرد و نقش کاربر را بر می گرداند اگر آی دی کاربر را
     * نداشت آی دی را از سشن می خواند
     *
     * @param request panel,id
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getRoleName(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            StringBuilder script = new StringBuilder();
//            String script = "";

            String panel = jjTools.getParameter(request, "panel");
            String userId = jjTools.getParameter(request, "userId");

            List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "=" + userId));
            if (!UserRowRole.isEmpty()) {
                script.append(Js.setVal("#" + panel, UserRowRole.get(0).get(_title)));
            }

            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

    /**
     * آی دی نقش را می گیرد و نقش کاربر را بر می گرداند برای توابع سمت جاوا
     * مواقعی که میخواهیم عنوان نقش کاربر را بدست بیاوریم اگر نقشی نداشت تهی بر
     * میگرداند این متد به کلاینت چیزی نمیفرستد
     *
     * @param roleId
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getRoleName(String roleId, jjDatabaseWeb db) throws Exception {
        try {
            if (jjNumber.isDigit(roleId)) {
                List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + roleId));
                if (!UserRowRole.isEmpty()) {
                    return UserRowRole.get(0).get(_title).toString();
                }
            }
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    
    
    public static String getRoleUserName(String roleId, jjDatabaseWeb db) throws Exception {
        try {
            String users = "";
            if (roleId == "ALL") {
                List<Map<String, Object>> rolesRow = jjDatabase.separateRow(db.Select(tableName));
                for (int i = 0; i < rolesRow.size(); i++) {
                    List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + rolesRow.get(i).get(_user_id)));
                    users += UserRow.get(0).get(Access_User._name).toString() + " " + UserRow.get(0).get(Access_User._family).toString() + "(" + rolesRow.get(i).get(_title) + "),";
                }
                return users;

            }
            if (jjNumber.isDigit(roleId)) {
                List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + roleId));
                List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + UserRowRole.get(0).get(Role._user_id)));
                if (!UserRow.isEmpty()) {
                    return UserRowRole.get(0).get(Role._title) + "(" + UserRow.get(0).get(Access_User._name).toString() + " " + UserRow.get(0).get(Access_User._family).toString() + ")";
                }
            }
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            StringBuilder script = new StringBuilder();
            if (accIns) {
                script.append(Js.setHtml("#Role_button", "<div class='col-lg-6'><input type='button' id='insert_Role_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'></div>"));
                script.append(Js.click("#insert_Role_new", Js.jjRole.insert()));
            } else {
                script.append(Js.setHtml("#Role_button", ""));
            }
            Server.outPrinter(request, response, script.toString());
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
//
//    /**
//     * تابع درج date 1397/11/7
//     *
//     * @param request
//     * @param db
//     * @param isPost
//     * @return
//     * @throws Exception
//     */

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_condition, jjTools.getParameter(request, _condition));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_user_id, jjTools.getParameter(request, _user_id));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_comment, jjTools.getParameter(request, _comment));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjRole.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
//
//    /**
//     *
//     * @param id
//     */

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            StringBuilder script1 = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            ///ایجاد جدول کاربران 
            ///توسط شیران1
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            html.append(Js.setVal("#role_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _discription, row.get(0).get(_discription)));
            html.append(Js.setValSelectOption("#" + _user_id, row.get(0).get(_user_id).toString()));
            html.append(Js.select2("#" + _user_id, ""));
            html.append(Js.setVal("#" + _condition, row.get(0).get(_condition)));
            html.append(Js.setValSummerNote("#" + _comment, row.get(0).get(_comment)));

            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjRole.edit() + "' id='edit_Role'>" + lbl_edit + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjRole.delete(id) + "' id='delete_Role'>" + lbl_delete + "</button></div>";
            }
            script1.append(Js.setHtml("#Role_button", htmlBottons));
            String script = "";
            html2.append(""
                    + "    <div class=\"card bd-primary mg-t-20\">"
                    + " <div class=\"card-header bg-primary tx-white\">گزارشی از نقش استفاده شده در "
                    + "ماژول های برنامه</div>\n"
                    + "    <div class=\"card-body pd-sm-30\">"
                    + "    <div class=\"title\">"
                    + "    <div class=\"col-lg-12\">"
            );
            html2.append(getRoleIsInUse(id, Plans.tableName, Plans._supervisorRoleId, "بهبود کیفیت/ برنامه عملیاتی(مافوق)", db));
            html2.append(getRoleIsInUse(id, Plans.tableName, Plans._managerRoleId, "بهبود کیفیت/ برنامه عملیاتی(مدیر)", db));
            html2.append(getRoleIsInUse(id, Plans.tableName, Plans._improveQualityId, "بهبود کیفیت/ برنامه عملیاتی (مسئول بهبود کیفیت)", db));
            html2.append(getRoleIsInUse(id, Commettes.tableName, Commettes._superwizar, "کمیته (مدیر کمیته)", db));
            html2.append(getRoleIsInUse(id, Commettes.tableName, Commettes._secretary, "کمیته(دبیر کمیته)", db));
            html2.append(getRoleIsInUse(id, Commettes.tableName, Commettes._members, "عضو کمیته", db));
            html2.append(getRoleIsInUse(id, Approved.tableName, Approved._trackerId, "مصوبات صورتجلسه مسئول پیگیری", db));
            html2.append(getRoleIsInUse(id, Approved.tableName, Approved._executorRoleId, "مصوبات صورتجلسه(مسئول اجرا)", db));
            html2.append(getRoleIsInUse(id, Sessions.tableName, Sessions._communicatorRoleId, "جلسات(مسئول ابلاغ)", db));
            html2.append(getRoleIsInUse(id, Sessions.tableName, Sessions._Invitees, "صورتجلسه (مدعوین)", db));
            html2.append(getRoleIsInUse(id, Sessions.tableName, Sessions._audience, "جلسات(حضار)", db));
            html2.append(getRoleIsInUse(id, Sessions.tableName, Sessions._absentRole, "صورتجلسه (غائبین)", db));
            html2.append(getRoleIsInUse(id, Sessions.tableName, Sessions._signers, "صورتجلسه (امضاکننده)", db));
            html2.append(getRoleIsInUse(id, Steps.tableName, Steps._trackerId, "برنامه عملیاتی /بهبود کیفیت (گام ها(مسئول پیگیری))", db));
            html2.append(getRoleIsInUse(id, Steps.tableName, Steps._executorRoleId, "برنامه عملیاتی /بهبود کیفیت (گام ها (مسئول اجرا))", db));
            html2.append(getRoleIsInUse(id, Documentary.tableName, Documentary._responsibleLoadingRole, "مستندات(مسئول بارگزاری)", db));
            html2.append(getRoleIsInUse(id, Forms.tableName, Forms._accessessRoles, "فرم ها", db));
            html2.append(getRoleIsInUse(id, Forms.tableName, Forms._ownerRole, "فرم ها", db));
            html2.append(getRoleIsInUse(id, Forms.tableName, Forms._resultAccessRole, "فرم ها", db));
            html2.append(getRoleIsInUse(id, CreateDocumentary.tableName, CreateDocumentary._reciversRoles, "ایجاد مستند", db));
            html2.append(getRoleIsInUse(id, CreateDocumentary.tableName, CreateDocumentary._communicator, "ایجاد مستند(ابلاغ کننده)", db));
            html2.append(getRoleIsInUse(id, FormAnswerSet.tableName, FormAnswerSet._userRole, "تکمیل فرم", db));
            html2.append(getRoleIsInUse(id, Fmea.tableName, Fmea._member, "fmea (اعضا)", db));
            html2.append(getRoleIsInUse(id, ManagementGauges.tableName, ManagementGauges._responsibleLoadingRole, "مسئول بارگذاری سنجه", db));
            html2.append(getRoleIsInUse(id, ManagementGauges.tableName, ManagementGauges._responsibleGauge, "مسئول سنجه", db));
            html2.append(getRoleIsInUse(id, ManagementGauges.tableName, ManagementGauges._responsibleLoading, "مدیریت سنجه", db));
            html2.append("</div>"
                    + "</div>"
                    + "</div>"
            );

            script += html.toString() + script1;
            script += Js.setHtml("#reportsRole", html2.toString());
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * گزارش گیری از اینکه یک نقش در کدام جدول در کدام ستون از جدول قرار دارد
     */
    public static String getRoleIsInUse(String roleId, String tableName, String columnTable, String title, jjDatabaseWeb db) throws Exception {

        try {
            String scrip = "";
            if (jjNumber.isDigit(roleId)) {
                List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, "(" + columnTable + " LIKE '%" + roleId + "%') OR (" + columnTable + " LIKE '%," + roleId + ",%') OR (" + columnTable + " LIKE '%%23A%23" + roleId + "%23A%23%') OR (" + columnTable + "=" + roleId + ")"));
                for (int i = 0; i < Row.size(); i++) {
                    scrip += "<div class='col-lg-6 form-control mg-b-10' ><p>نقش مورد نظر در ماژول  "
                            + title
                            + " در کد "
                            + " (" + Row.get(i).get("id") + ")"
                            + "قرار دارد. </p>"
                            + "<br/>"
                            + "</div>";
                }
                return scrip;
            }
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String selectKarbar(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
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

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";

            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

//            html.append(Js.setVal("#role_" + _id, row.get(0).get(_id)));
//            for (int i = 0; i < row.size(); i++) {
            html.append(Js.setVal("#role_user_id", row.get(0).get(Access_User._id)));
            html.append(Js.setVal("#role_name", row.get(0).get(Access_User._name)));
            html.append(Js.setVal("#role_family", row.get(0).get(Access_User._family)));
            html.append(Js.setVal("#role_email", row.get(0).get(Access_User._email)));

//            }
            Server.outPrinter(request, response, html.toString());
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }
//

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_condition, jjTools.getParameter(request, _condition));
            map.put(_user_id, jjTools.getParameter(request, _user_id));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_comment, jjTools.getParameter(request, "role_comment"));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjRole.refresh());
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
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";

            }
            Server.outPrinter(request, response, Js.jjRole.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نقش های کاربری که در سشن است را بر می گرداند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getUeserRolesSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            int userId = jjTools.getSeassionUserId(request);
            StringBuilder optionHtml = new StringBuilder();
            List<Map<String, Object>> userRolesRows = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, _user_id + "=" + userId, _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < userRolesRows.size(); i++) {
                if (i == 0) {
                    optionHtml.append("<option  value='").append(userRolesRows.get(i).get(_id)).append("' selected='selected'>").append(userRolesRows.get(i).get(_title)).append("</option>");
                } else {
                    optionHtml.append("<option  value='").append(userRolesRows.get(i).get(_id)).append("'>").append(userRolesRows.get(i).get(_title)).append("</option>");
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = "#forms_ownerRole";
            }
            if (needString) {
                return optionHtml.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * نقش های کاربری که در که آی دی آنرا داریم را بر می گرداند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getUeserRolesSelectOptionByUserId(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String userId = jjTools.getParameter(request, _user_id);
            StringBuilder optionHtml = new StringBuilder();
            List<Map<String, Object>> userRolesRows = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, _user_id + "=" + userId, _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < userRolesRows.size(); i++) {
                if (i == 0) {
                    optionHtml.append("<option  value='").append(userRolesRows.get(i).get(_id)).append("' selected='selected'>").append(userRolesRows.get(i).get(_title)).append("</option>");
                } else {
                    optionHtml.append("<option  value='").append(userRolesRows.get(i).get(_id)).append("'>").append(userRolesRows.get(i).get(_title)).append("</option>");
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = "#forms_ownerRole";
            }
            if (needString) {
                return optionHtml.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * نقش را میگیرد و ای دی کاربر مربوط به ان نقش را بر می گرداند
     *
     * @param roleId آی دی نقش
     * @param db
     * @return آی دی کاربر
     * @throws Exception
     */
    public static String getUeserIdByUserRole(String roleId, jjDatabaseWeb db) throws Exception {
        try {

            StringBuilder optionHtml = new StringBuilder();
            List<Map<String, Object>> userRolesRows = jjDatabase.separateRow(db.Select(tableName, _user_id, _id + "=" + roleId));// بر اساس حروف الفبا مرتب باشد بهتر است
            if (!userRolesRows.isEmpty()) {
                return userRolesRows.get(0).get(_user_id).toString();
            }
            return "1";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * این متد همه نقش ها را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
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
            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, "id>=0 AND " + Role._condition + "=1", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            optionHtml.append("<option  value='ALL'>همه مسئولین سازمانی</option>");
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_title)).append("</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".roleSelectOption";
            }
            if (needString) {
                return optionHtml.toString();
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این متد نقش ها را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
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
    public static String getSelectOptionRequierd(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, "id>=0 AND "+Role._condition+"=1", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(Role.getRoleUserName(rowAllActiveRols.get(i).get(_id).toString(), db)).append("</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".roleSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

}
