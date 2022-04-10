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

    public static int rul_showCommitesModule = 425;
    public static int rul_rfs = 426;
    public static int rul_ins = 427;
    public static int rul_edt = 428;
    public static int rul_dlt = 429;
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
            StringBuilder html = new StringBuilder();

            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
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
                        + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisCommettes.m_add_new();\" >کمیته جدید</a>\n"
                        + "                                        </p>\n"
                        + "                                    ");
            }

            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshCommettes' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th class='r' width='5%'>کد</th>");
            html.append("<th class='r' width='20%'>وضعیت</th>");
            html.append("<th class='r' width='20%'>نام کمیته</th>");
            html.append("<th class='r' width='20%'>دبیر کمیته</th>");
            html.append("<th class='r' width='20%'>رئیس کمیته</th>");
            html.append("<th class='r' width='20%'>دعوتنامه</th>");
            html.append("<th class='r' width='20%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                List<Map<String, Object>> secretaryTitle = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(_secretary)));
                List<Map<String, Object>> superwizerTitle = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(_superwizar)));
                html.append("<tr  class='p'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                String StatusActive = row.get(i).get(_isActive).equals("1") ? "فعال" : "غیرفعال";
                html.append("<td class='r'>" + StatusActive + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'>" + secretaryTitle.get(0).get(Role._title) + "</td>");
                html.append("<td class='r'>" + superwizerTitle.get(0).get(Role._title) + "</td>");
                html.append("<td class='r'><i class='icon ion-email' onclick='hmisCommettes.showInvitationForm(" + row.get(i).get(_id) + ");' style='color:#ffcd00!important'></i></td>");
                html.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisCommettes.m_select(" + row.get(i).get(_id) + ")'></i></td>");
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
                panel = "swCommettesTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());

            script += Js.table("#refreshCommettes", "300", 0, "", "کمیته ها");
            //////////////////////////////برای سلکت های موجود در فرم کمیتهها/////////////

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
                html.append(Js.setHtml("#Commette_button", "<button  id=\"insert_Commette_new\"  class=\"btn btn-outline-success active btn-block mg-b-10\" onclick='" + Js.jjCommettes.insert() + "'>" + lbl_insert + "</button>"));
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
            html.append("$('#" + _secretary + "').val([" + row.get(0).get(_secretary) + "]);$('#" + _secretary + "').select2({  width: '100%'});");
            html.append("$('#" + _superwizar + "').val([" + row.get(0).get(_superwizar) + "]);$('#" + _superwizar + "').select2({  width: '100%'});");
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _documnetsFile, row.get(0).get(_documnetsFile)));
            html.append(Js.setVal("#" + _regulationFile, row.get(0).get(_regulationFile)));
            html.append(Js.setVal("#" + _period, row.get(0).get(_period)));
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
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"mg-t-20  col-lg\">");
                html2.append("<button  id='edit_Commettes'  class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjCommettes.edit() + "'>" + lbl_edit + "</button>");
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"mg-t-20  col-lg\">");
                html2.append("<button id='delete_Commettes' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjCommettes.delete(id) + "' >" + lbl_delete + " </button>");
                html2.append("</div>");
            }
            html2.append("</div>");
            String script = Js.setHtml("#Commette_button", html2);
//            if (row.get(0).get(_members).toString().equals("")) {//اعضای کمیته
//
//            } else {
//                String membersId = row.get(0).get(_members).toString();
//                String[] memberIdArray = (membersId.replaceAll("#A#", "%23A%23")).split("%23A%23");
//                for (int i = 0; i < memberIdArray .length; i++) {
//                    script += ("$('#tableRolesDiv #refreshRoles #" + memberIdArray [i] + "').attr('class','icon ion-checkmark-circled').css('color','green');");
//                }
//            }

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
            map.put(_date, jjTools.getParameter(request, _date).replaceAll("/", ""));
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
                List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + roleRow.get(0).get(Role._user_id)));

                html.append("<label class=\"ckbox\">");
                html.append("<input type = \"checkbox\" value ='" + roleRow.get(0).get(Role._id) + "' class=\"checkBoxInvitees\" name = \"sessions_Invitees\" id = \"sessions_Invitees" + roleRow.get(0).get(Role._user_id) + "\" > ");
                html.append("<span>" + userRow.get(0).get(Access_User._name) + " " + userRow.get(0).get(Access_User._family) + "-" + roleRow.get(0).get(Role._title) + "");
                html.append("</span>");
                html.append("</label>");
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
            String inviteesOutSideIdComment = jjTools.getParameter(request, "inviteesOutSideIdComment");//ای دی مدعوین مهمان
            String inviteesInSideIdComment = jjTools.getParameter(request, "inviteesInSideIdComment");//ای دی مدعوین داخل سازمان
            String[] inviteesOutSideId = (inviteesInSideIdComment.replaceAll("#A#", "%23A%23")).split("%23A%23");

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
            html2.append("<th width='15%'>نام ونام خانوادگی</th>");
            html2.append("<th width='15%'>تعداد جلسات </th>");
            html2.append("<th width='15%'>تعداد دعوت شدگان </th>");
            html2.append("<th width='20%'>تعداد و درصد حضور</th>");
            html2.append("<th width='15%'>تعداد و درصد غیبت</th>");
            html2.append("</thead><tbody>");

            List<Map<String, Object>> RoleRow = jjDatabaseWeb.separateRow(db.Select(Role.tableName));

            dtm1 = db.otherSelect("SELECT count(hmis_sessions.id)As countSessions , "
                    + "GROUP_CONCAT(sessions_invitees SEPARATOR '')AS countInvitees ,"
                    + "GROUP_CONCAT(sessions_audience SEPARATOR '')AS countAudience,"
                    + "GROUP_CONCAT(sessions_absentRole SEPARATOR '')AS countAbsent"
                    + " FROM hmis_sessions"
                    + " LEFT JOIN hmis_commettes ON  hmis_sessions.sessions_commettesId=hmis_commettes.id "
                    + " WHERE hmis_sessions.sessions_commettesId=" + commettesId + "  AND " + dateFrom + " < " + Commettes._date + " AND " + dateTo + " > " + Commettes._date + " "
            );

            List<Map<String, Object>> sessionsRow = jjDatabaseWeb.separateRow(dtm1);
            if (sessionsRow.size() > 0) {
                int countSessions = Integer.valueOf(sessionsRow.get(0).get("countSessions").toString());
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + sessionsRow.get(0).get("countInvitees").toString());
                String[] countAudienceArray = sessionsRow.get(0).get("countAudience").toString().split("%23A%23");
                String[] countInviteesArray = sessionsRow.get(0).get("countInvitees").toString().split("%23A%23");
                String[] countAbsentArray = sessionsRow.get(0).get("countAbsent").toString().split("%23A%23");
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
                        html2.append("<td class='r'>" + Role.getRoleUserName(RoleRow.get(i).get(Role._id).toString(), db) + "</td>");
                        html2.append("<td class='r'>" + sessionsRow.get(0).get("countSessions") + "</td>");
                        html2.append("<td class='r'>" + countInvitees + "</td>");
//                    html2.append("<td class='r'>" + (int) Math.round(countInvitees) + "</td>");
                        float audiencePercent = 0;
                        float absentPercent = 0;
                        if (countInviteesArray.length >= 1) {
                            audiencePercent = ((float) countAudience / (float) countSessions) * 100;
                            absentPercent = ((float) countAbsent / (float) countSessions) * 100;
                            html2.append("<td class='r'>" + countAudience + "-" + (int) Math.round(audiencePercent) + "%</td>");
                            html2.append("<td class='r'>" + countAbsent + "-" + (int) Math.round(absentPercent) + "%</td>");
                        } else {
                            html2.append("<td class='r'>" + countAudience + "-0%</td>");
                            html2.append("<td class='r'>" + countAbsent + "-0%</td>");
                        }

                        html2.append("</tr>");

                    }
                }
            }
            html2.append("</tbody></table>");
            script += Js.table("#refreshAudience", "300", 0, "", "جلسات");
            script += Js.setHtml("#result1", html2);
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
            String dateFrom = jjTools.getParameter(request, "dateFromOneAudience").replaceAll("/", "");
            String dateTo = jjTools.getParameter(request, "dateToOneAudience").replaceAll("/", "");
            DefaultTableModel dtm1;

            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<table id='refreshOneAudience' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html2.append("<th width='15%'>نام ونام خانوادگی</th>");
            html2.append("<th width='15%'>تعداد جلسات </th>");
            html2.append("<th width='15%'>تعداد دعوت شدگان </th>");
            html2.append("<th width='20%'>تعداد و درصد حضور</th>");
            html2.append("<th width='15%'>تعداد و درصد غیبت</th>");
            html2.append("</thead><tbody>");

            List<Map<String, Object>> RoleRow = jjDatabaseWeb.separateRow(db.Select(Role.tableName));

            dtm1 = db.otherSelect("SELECT count(hmis_sessions.id)As countSessions , "
                    + "GROUP_CONCAT(sessions_invitees SEPARATOR '')AS countInvitees ,"
                    + "GROUP_CONCAT(sessions_audience SEPARATOR '')AS countAudience,"
                    + "GROUP_CONCAT(sessions_absentRole SEPARATOR '')AS countAbsent"
                    + " FROM hmis_sessions"
                    + " LEFT JOIN hmis_commettes ON  hmis_sessions.sessions_commettesId=hmis_commettes.id "
                    + " WHERE " + dateFrom + " < " + Commettes._date + " AND " + dateTo + " > " + Commettes._date + " "
            );

            List<Map<String, Object>> sessionsRow = jjDatabaseWeb.separateRow(dtm1);
            if (sessionsRow.size() > 0) {
                int countSessions = Integer.valueOf(sessionsRow.get(0).get("countSessions").toString());
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + sessionsRow.get(0).get("countInvitees").toString());
                String[] countAudienceArray = sessionsRow.get(0).get("countAudience").toString().split("%23A%23");
                String[] countInviteesArray = sessionsRow.get(0).get("countInvitees").toString().split("%23A%23");
                String[] countAbsentArray = sessionsRow.get(0).get("countAbsent").toString().split("%23A%23");
                int countInvitees = 0;
                if (!sessionsRow.get(0).get("countInvitees").toString().equals("")) {
                    for (int k = 0; k < countInviteesArray.length; k++) {
                        if (countInviteesArray[k].equals(roleIdOneAudience)) {
                            countInvitees++;
                        }
                    }
                }
                int countAudience = 0;
                if (!sessionsRow.get(0).get("countAudience").toString().equals("")) {
                    for (int k = 0; k < countAudienceArray.length; k++) {
                        if (countAudienceArray[k].equals(roleIdOneAudience)) {
                            countAudience++;
                        }
                    }
                }
                int countAbsent = 0;
                if (!sessionsRow.get(0).get("countAbsent").toString().equals("")) {
                    for (int k = 0; k < countAbsentArray.length; k++) {
                        if (countAbsentArray[k].equals(roleIdOneAudience)) {
                            countAbsent++;
                        }
                    }
                }
                System.out.println("------" + countInvitees);
//                if (sessionsRow.size() > 0) {
                html2.append("<tr class='p '>");
                html2.append("<td class='r'>" + Role.getRoleUserName(roleIdOneAudience, db) + "</td>");
                html2.append("<td class='r'>" + sessionsRow.get(0).get("countSessions") + "</td>");
                html2.append("<td class='r'>" + countInvitees + "</td>");
//                    html2.append("<td class='r'>" + (int) Math.round(countInvitees) + "</td>");
                float audiencePercent = 0;
                float absentPercent = 0;
                if (countInviteesArray.length >= 1) {
                    audiencePercent = ((float) countAudience / (float) countSessions) * 100;
                    absentPercent = ((float) countAbsent / (float) countSessions) * 100;
                    html2.append("<td class='r'>" + countAudience + "-" + (int) Math.round(audiencePercent) + "%</td>");
                    html2.append("<td class='r'>" + countAbsent + "-" + (int) Math.round(absentPercent) + "%</td>");
                } else {
                    html2.append("<td class='r'>" + countAudience + "-0%</td>");
                    html2.append("<td class='r'>" + countAbsent + "-0%</td>");
                }

                html2.append("</tr>");

//                }
            }
            html2.append("</tbody></table>");
            script += Js.table("#refreshOneAudience", "300", 0, "", "جلسات");
            script += Js.setHtml("#result3", html2);
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
            DefaultTableModel dtm3;
            html2.append("<div class=\"table-wrapper\">\n");
            html2.append("<table id='refreshReportApproved' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html2.append("<th width='15%'>نام کمیته</th>");
            html2.append("<th width='15%'>تعداد مصوبات  در حال انجام  </th>");
            html2.append("<th width='20%'>تعداد مصوبات  غیر قابل انجام</th>");
            html2.append("<th width='15%'>  تعداد مصوبات  انجام شده</th>");
            html2.append("</thead><tbody>");

            dtm1 = db.otherSelect("SELECT " + Commettes._title + ","
                    + "COUNT(hmis_approved.id) as countApproved,\n"
                    + "COUNT(CASE WHEN approved_status='در حال انجام' THEN 1 ELSE NULL END) as countApprovedStatus1,\n"
                    + "COUNT(CASE WHEN approved_status='غیر قابل انجام' THEN 1 ELSE NULL END) as countApprovedStatus2,\n"
                    + "COUNT(CASE WHEN approved_status='انجام شده' THEN 1 ELSE NULL END) as countApprovedStatus3"
                    + " FROM hmis_approved"
                    + " LEFT JOIN hmis_commettes ON  hmis_approved.approved_commettesId=hmis_commettes.id "
                    + " WHERE hmis_approved.approved_commettesId=" + commettesId + "  AND " + dateFrom + " < " + Commettes._date + " AND " + dateTo + " > " + Commettes._date + " AND " + Approved._isActive + "=1 "
            );
            dtm2 = db.otherSelect("SELECT "
                    + "COUNT(hmis_approved.id) as countApproved\n"
                    + " FROM hmis_approved"
                    + " LEFT JOIN hmis_commettes ON  hmis_approved.approved_commettesId=hmis_commettes.id "
                    + " WHERE hmis_approved.approved_commettesId=" + commettesId + "  AND " + dateFrom + " < " + Commettes._date + " AND " + dateTo + " > " + Commettes._date + " AND  " + Approved._isActive + "=1"
            //                            + " hmis_approved.approved_status!='" + Approved.status_initialRegistration + "' AND"
            //                    + "  hmis_approved.approved_status!='" + Approved.status_communicated + "'"
            );
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm1);
            System.out.println("row.size()=" + row.size());
//            List<Map<String, Object>> approvedRow = jjDatabaseWeb.separateRow(dtm2);
            if (Integer.valueOf(row.get(0).get("countApproved").toString()) > 0) {
                System.out.println("sessionsRow.get(0).get(\"countInvitees\").toString()=" + row.get(0).get("countApprovedStatus1").toString());
                html2.append("<tr class='p '>");
                float countAllApproved = Float.parseFloat(row.get(0).get("countApproved").toString());
                float percentApprovedInDoing = (Float.parseFloat(row.get(0).get("countApprovedStatus1").toString()) / countAllApproved) * 100;
                float percentApprovedUnDoing = (Float.parseFloat(row.get(0).get("countApprovedStatus2").toString()) / countAllApproved) * 100;
                float percentApprovedDone = (Float.parseFloat(row.get(0).get("countApprovedStatus3").toString()) / countAllApproved) * 100;
                html2.append("<td class='r'>" + row.get(0).get(Commettes._title) + "</td>");
                html2.append("<td class='r'>" + row.get(0).get("countApprovedStatus1") + "-" + (int) Math.round(percentApprovedInDoing) + "%</td>");
                html2.append("<td class='r'>" + row.get(0).get("countApprovedStatus2") + "-" + (int) Math.round(percentApprovedUnDoing) + "%</td>");
                html2.append("<td class='r'>" + row.get(0).get("countApprovedStatus3") + "-" + (int) Math.round(percentApprovedDone) + "%</td>");
                html2.append("</tr>");
            }
            html2.append("</tbody></table>");
            script += Js.table("#refreshReportApproved", "300", 0, "", "جلسات");
            script += Js.setHtml("#result2", html2);
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
                String text = "دبیر محترم  " + rows.get(i).get(_title).toString() + "لطفا نسبت به تنظیم دستور جلسه ی کمیته از قسمت تعریف وتنظیم کمیته ها  اقدام فرمایید";
                Messenger.sendMesseage(null, db, secretaryId, "1", "sms,app,email", null, "یاد آوری سامانه : جلسه دوره ای  " + rows.get(i).get(_title), text, text, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
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
    public static void taskStepsReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskStepsReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Steps.tableName, Steps._status + "='" + Steps.status_inDoing + "'"));
        System.out.println("size=" + rows.size());
        if (rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                System.out.println("rows.get(i).get(_Enddate).toString()=" + rows.get(i).get(Steps._endDate).toString());
                System.out.println("rows.get(i).get(_Startdate).toString()=" + rows.get(i).get(Steps._startDate).toString());
                jjCalendar_IR startDate = new jjCalendar_IR(rows.get(i).get(Steps._startDate).toString());
                jjCalendar_IR EndDate = new jjCalendar_IR(rows.get(i).get(Steps._startDate).toString());
                int periodStartDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeStepsStartDate_name).toString());
                int periodEndDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeStepsEndDate_name).toString());
                int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
                while (today > startDate.getDBFormat_8length()) {
                    startDate.addDay(periodStartDate);
                }
                while (today > EndDate.getDBFormat_8length()) {
                    EndDate.addDay(periodEndDate);
                }

                String textHtml = "";
                if (startDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Steps._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Steps._executorUserId).toString();
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(executorUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else if (rows.get(i).get(Steps._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(convertRoleIdToUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._trackerId).toString(), db);
                    String text = "مسئول پیگیری محترم " + Access_User.getUserName(trackerUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + " آغاز می شود.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    System.out.println("<<<<<<<ارسال پیام مسئول اجرا و پیگیریSend MESSAGE()");
                    System.out.println("#################################################");
                }
                if (EndDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Steps._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Steps._executorUserId).toString();
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(executorUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    } else if (rows.get(i).get(Steps._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(convertRoleIdToUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Steps._trackerId).toString(), db);

                    String text = "مسئول پیگیری محترم " + Access_User.getUserName(trackerUserId, db) + " گام باعنوان :" + rows.get(i).get(Steps._title) + "در تاریخ " + startDate + "پایان می یابد.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام گام با عنوان" + rows.get(i).get(Steps._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModulePlans_name),Tice_config.getValue(db, Tice_config._config_activeEmailModulePlans_name));
                    System.out.println("#################################################");
                }
            }
        }
    }

    public static void taskApprovedReminder() throws Exception {
        System.out.println("#################################################");
        System.out.println("######>>>>>RUN:taskStepsReminder###########");
        Server.Connect();
        jjDatabaseWeb db = Server.db;
        List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(db.Select(Approved.tableName, Approved._status + "='" + Approved.status_inDoing + "'"));
        System.out.println("size=" + rows.size());
        if (rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                System.out.println("rows.get(i).get(_Enddate).toString()=" + rows.get(i).get(Approved._endDate).toString());
                System.out.println("rows.get(i).get(_Startdate).toString()=" + rows.get(i).get(Approved._startDate).toString());
                jjCalendar_IR startDate = new jjCalendar_IR(rows.get(i).get(Approved._startDate).toString());
                jjCalendar_IR EndDate = new jjCalendar_IR(rows.get(i).get(Approved._startDate).toString());
                int periodStartDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeApprovedStartDate_name).toString());
                int periodEndDate = Integer.valueOf(Tice_config.getValue(db, Tice_config._config_reminderDayBeforeApprovedEndDate_name).toString());
                int today = jjCalendar_IR.getDatabaseFormat_8length(null, true);
                while (today > startDate.getDBFormat_8length()) {
                    startDate.addDay(periodStartDate);
                }
                while (today > EndDate.getDBFormat_8length()) {
                    EndDate.addDay(periodEndDate);
                }

                String textHtml = "";
                if (startDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Approved._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Approved._executorUserId).toString();
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(executorUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    } else if (rows.get(i).get(Approved._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(convertRoleIdToUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + " آغاز می شود.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._trackerId).toString(), db);
                    String text = "مسئول پیگیری محترم " + Access_User.getUserName(trackerUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + " آغاز می شود.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از شروع فرصت انجام گام با عنوان" + rows.get(i).get(Approved._title) + "",text,textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    System.out.println("<<<<<<<ارسال پیام مسئول اجرا و پیگیریSend MESSAGE()");
                    System.out.println("#################################################");
                }
                if (EndDate.getDBFormat_8length() == today) {
                    System.out.println(">>>>>>>" + today);
                    if (rows.get(i).get(Approved._executorRoleId).toString().equals("")) {
                        String executorUserId = rows.get(i).get(Approved._executorUserId).toString();
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(executorUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, executorUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    } else if (rows.get(i).get(Approved._executorUserId).toString().equals("")) {
                        String convertRoleIdToUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._executorRoleId).toString(), db);
                        String text = "مسئول اجرا محترم " + Access_User.getUserName(convertRoleIdToUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + " پایان می یابد.";
                        Messenger.sendMesseage(null, db, convertRoleIdToUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    }
                    String trackerUserId = Role.getUeserIdByUserRole(rows.get(i).get(Approved._trackerId).toString(), db);

                    String text = "مسئول پیگیری محترم " + Access_User.getUserName(trackerUserId, db) + " مصوبه باعنوان :" + rows.get(i).get(Approved._title) + "در تاریخ " + startDate + "پایان می یابد.";
                    Messenger.sendMesseage(null, db, trackerUserId, "1", "sms,app,email", null, "یادآوری  قبل از پایان فرصت انجام مصوبه با عنوان" + rows.get(i).get(Approved._title) + "", text, textHtml, "یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleCommittee_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleCommittee_name));
                    System.out.println("#################################################");
                }
            }
        }
    }

}
