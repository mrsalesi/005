/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import javax.swing.table.DefaultTableModel;
import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shiran1
 */
public class Documentary {

    public static String tableName = "hmis_documentary";
    public static String _id = "id";
    public static String _uploaderUser = "documentary_uploaderUserId";//
    public static String _title = "documentary_title";//عنوان فایل
    public static String _nameResponsibleLoading = "documentary_nameName";//نام بارگداری کننده
    public static String _responsibleLoadingRole = "documentary_responsibleLoadingRole";//نقش بارگذاری کننده در هنگام اینسرت در جدول مستندات که باید دقت داشت رفرش بر اساس این نیست و بر اساس جوین با جدول سنجه است
    public static String _LoadingPeriod = "documentary_LoadingPeriod";///دوره بارگذاری
    public static String _dateCreation = "documentary_dateCreation";///تاریخ ایجاد بهصورت سیستمی
    public static String _status = "documentary_status";///وضعیت بارگذاری
    public static String _attachFileDocumentary = "documentary_attachFileDocumentary";///دریافت بارگذاری
    public static String _gaugeId = "documentary_gaugeId";//عنوان فایل
    public static String _logStatus = "documentary_logStatus";///وضعیت بارگذاری
    public static String _comments = "documentary_comments";//برای درج توضیحات بارگذاری کننده که مثلا ممکن است فایل خاصی موجود نباشد
    public static String status_Uploaded = "بارگذاری شده";
    public static String status_noUploaded = "بارگذاری نشده";
    public static String lbl_insert = "ذخیره";
    public static String lbl_undo = "بازگشت به وضعیت اولیه";
    public static String lbl_edit = "ثبت موقت";
    public static String lbl_finalize = "دخیره و ثبت نهایی";

    public static int rul_rfs = 511;
    public static int rul_myRfs = 504;
    public static int rul_ins = 512;
    public static int rul_edt = 513;
    public static int rul_undo = 514;
    public static int rul_dlt = 10000; // مستندات ایجاد شده قابل حذف نیستند و با دادن عدد بزرگ کسی دسترسی به حذف ندارد

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            boolean accRefreshAll = Access_User.hasAccess(request, db, rul_rfs);
            if (!accRefreshAll) {
                Server.outPrinter(request, response, Js.modal("شما اجازه دسترسی به این قسمت را ندارید", "محدودیت سطح دسترسی!"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            String userRolesinSession[] = jjTools.getSeassionUserRole(request).split(",");
            DefaultTableModel dtm;
            dtm = db.otherSelect("SELECT " + tableName + ".* ,"
                    + ManagementGauges._axis + " ,"
                    + ManagementGauges._underTheAxis + " ,"
                    + ManagementGauges._standard + " ,"
                    + ManagementGauges._gauge + " ,"
                    + ManagementGauges._step + " ,"
                    + Role._title + "," + Access_User._name + "," + Access_User._family + " FROM  " + tableName
                    + " LEFT JOIN " + ManagementGauges.tableName + " ON " + _gaugeId + " = " + ManagementGauges.tableName + ".id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Role.tableName + " ON " + ManagementGauges._responsibleLoadingRole + " = hmis_role.id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Access_User.tableName + " ON " + Role._user_id + " = access_user.id " + ";");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>بار گذاری سنجه ها</div>"
                    + "    <div class='card-body pd-sm-30'>"
            );
             html.append(
                    "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisDocumentary.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>");
            html.append("<table id='refreshDocumentary' class='table display responsive' class='tahoma10' style='direction: rtl'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>سنجه</th>");
            html.append("<th class='c' width='20%'>مسئول بارگذاری</th>");
            html.append("<th class='c' width='20%'>نام بارگذاری کننده</th>");
            html.append("<th class='c' width='20%'>دوره بارگذاری </th>");
            html.append("<th class='c' width='20%'>تاریخ ایجاد(سیستمی)</th>");
            html.append("<th class='c' width='20%'>وضعیت بارگذاری</th>");
            html.append("<th class='c' width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='hmisDocumentary.m_select(" + row.get(i).get(_id) + ");' class='p " + getClassByStatus(row.get(i).get(_status).toString()) + " ' >");
                html.append("<td class='c' >" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c' >"
                        //                        + (row.get(i).get(_title).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._axis).toString()) + "->"
                        + (row.get(i).get(ManagementGauges._underTheAxis).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._standard).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._gauge).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._step).toString()) + "<br/>"
                        + "</td>");
                html.append("<td class='c' >"
                        + (row.get(i).get(Role._title).toString()) + "<br/>"
                        + (row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString())
                        + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_nameResponsibleLoading).toString()) + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_LoadingPeriod).toString()) + "</td>");
                html.append("<td class='c' >" + jjCalendar_IR.getViewFormat(row.get(i).get(_dateCreation).toString()) + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td ><i class='fa fa-eye'></i></td>");
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
                panel = "swDocumentaryTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshDocumentary", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست  سنجه ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refresh_my(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_myRfs)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت ندارید");
            }
            StringBuilder html = new StringBuilder();

            String userRolesinSession[] = jjTools.getSeassionUserRole(request).split(",");
            String where = " WHERE ";
            for (int i = 0; i < userRolesinSession.length; i++) {
                if (i == userRolesinSession.length - 1) {// برای آخری  OR نمیخواهیم
                    where += _responsibleLoadingRole + "=" + userRolesinSession[i];
                } else {
                    where += _responsibleLoadingRole + "=" + userRolesinSession[i] + " OR ";
                }
            }
            DefaultTableModel dtm;
            dtm = db.otherSelect("SELECT " + tableName + ".* ,"
                    + ManagementGauges._axis + " ,"
                    + ManagementGauges._underTheAxis + " ,"
                    + ManagementGauges._standard + " ,"
                    + ManagementGauges._gauge + " ,"
                    + ManagementGauges._step + " ,"
                    + Role._title + "," + Access_User._name + "," + Access_User._family + " FROM  " + tableName
                    + " LEFT JOIN " + ManagementGauges.tableName + " ON " + _gaugeId + " = " + ManagementGauges.tableName + ".id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Role.tableName + " ON " + ManagementGauges._responsibleLoadingRole + " = hmis_role.id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Access_User.tableName + " ON " + Role._user_id + " = access_user.id " + where + ";");
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>بار گذاری سنجه ها</div>"
                    + "    <div class='card-body pd-sm-30'>"
            );
            html.append(
                    "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisDocumentary.m_refresh_my();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table class='table display' id='refreshDocumentary' dir='rtl'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>سنجه</th>");
            html.append("<th class='c' width='20%'>مسئول بارگذاری</th>");
            html.append("<th class='c' width='20%'>نام بارگذاری کننده</th>");
            html.append("<th class='c' width='20%'>دوره بارگذاری </th>");
            html.append("<th class='c' width='20%'>تاریخ ایجاد(سیستمی)</th>");
            html.append("<th class='c' width='20%'>وضعیت بارگذاری</th>");
            html.append("<th class='c' width='5%'>ویرایش</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='hmisDocumentary.m_select(" + row.get(i).get(_id) + ");' class='p " + getClassByStatus(row.get(i).get(_status).toString()) + " ' >");
                html.append("<td class='c' >" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c' >"
                        //                        + (row.get(i).get(_title).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._axis).toString()) + "->"
                        + (row.get(i).get(ManagementGauges._underTheAxis).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._standard).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._gauge).toString()) + "<br/>"
                        + (row.get(i).get(ManagementGauges._step).toString()) + "<br/>"
                        + "</td>");
                html.append("<td class='c' >"
                        + (row.get(i).get(Role._title).toString()) + "<br/>"
                        + (row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString())
                        + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_nameResponsibleLoading).toString()) + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_LoadingPeriod).toString()) + "</td>");
                html.append("<td class='c' >" + jjCalendar_IR.getViewFormat(row.get(i).get(_dateCreation).toString()) + "</td>");
                html.append("<td class='c' >" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td ><a  style='cursor: pointer;height:30px'  ><i class='icon ion-upload'></i></a></td>");
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
                panel = "swDocumentaryTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshDocumentary", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست  سنجه ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
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
            StringBuilder script = new StringBuilder();
            String script1 = "";
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            ////////////////////////////////
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Documentary.tableName, _id + "=" + id));
            script.append(Js.setVal("#documentary_LoadingPeriod", row.get(0).get(_LoadingPeriod)));
            script.append(Js.setVal("#" + _comments, row.get(0).get(_comments)));
            script.append(Js.setVal("#documentary_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#documentary_attachFileDocumentary", row.get(0).get(_attachFileDocumentary)));
            String attachFiles = row.get(0).get(_attachFileDocumentary).toString();
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
                        html2.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFileDocumentary + "'  type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html2.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFileDocumentary + "'   type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }

            script1 += Js.setHtml("#inputAfterSelectGauge", html2);
            List<Map<String, Object>> rowGauge = jjDatabase.separateRow(db.Select(ManagementGauges.tableName, _id + "=" + row.get(0).get(Documentary._gaugeId)));
            /////در اوردن مشخصات سنجه

            script.append(Js.setHtml("#standard", rowGauge.get(0).get(ManagementGauges._standard)));
            script.append(Js.setHtml("#gauges", rowGauge.get(0).get(ManagementGauges._gauge)));
            script.append(Js.setHtml("#step", rowGauge.get(0).get(ManagementGauges._step)));
            script.append(Js.setHtml("#axis", rowGauge.get(0).get(ManagementGauges._axis)));
            script.append(Js.setHtml("#underAxis", rowGauge.get(0).get(ManagementGauges._underTheAxis)));
            script.append(Js.setHtml("#comment_documentary", rowGauge.get(0).get(ManagementGauges._comment)));
            ////////////////////////
            DefaultTableModel dtm = db.otherSelect(
                    "SELECT " + tableName + ".* ," + Role._title + "," + Access_User._name + "," + Access_User._family + " FROM  " + tableName
                    + " LEFT JOIN " + ManagementGauges.tableName + " ON " + _gaugeId + " = " + ManagementGauges.tableName + ".id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Role.tableName + " ON " + ManagementGauges._responsibleLoadingRole + " = hmis_role.id " // برای استخراج نام مسئول بارگذاری و نقش او
                    + " LEFT JOIN " + Access_User.tableName + " ON " + Role._user_id + " = access_user.id "
                    + " WHERE " + _gaugeId + "=" + rowGauge.get(0).get(ManagementGauges._id) + ";");

            List<Map<String, Object>> rowDocumentary = jjDatabase.separateRow(dtm);
//////////////سنجه هایی که قبلا ایجاد شده به صورت جدول نمایش داده می شود

            html1.append("<table class='table display responsive nowrap' id='refreshDocumentaryloading' dir='rtl'><thead>");
            html1.append("<th class='c' width='5%'>کد</th>");
            html1.append("<th class='c' width='30%'>عنوان مستند</th>");
            html1.append("<th class='c' width='20%'>مسئول بارگذاری در زمان ایجاد</th>");
            html1.append("<th class='c' width='20%'>نام بارگذاری کننده</th>");
            html1.append("<th class='c' width='20%'>دوره بارگذاری </th>");
            html1.append("<th class='c' width='20%'>تاریخ ایجاد(سیستمی)</th>");
            html1.append("<th class='c' width='20%'>وضعیت بارگذاری</th>");
            html1.append("<th class='c' width='20%'>فایل های پیوست</th>");
            html1.append("</thead><tbody>");
            for (int i = 0; i < rowDocumentary.size(); i++) {

                html1.append("<tr   class='" + getClassByStatus(rowDocumentary.get(i).get(_status).toString()) + "' >");
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(_id).toString()) + "</td>");
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(_title).toString()) + "</td>");
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(Role._title).toString()) + "</td>");
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(_nameResponsibleLoading).toString()) + "</td>");// @ToDo میتوانیم این قسمت را از  جوبن بگیریم ولی در اینجا ما نام شخص در آن لحظه را بصورت رشته ذخیره کرده ایم
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(_LoadingPeriod).toString()) + "</td>");
                html1.append("<td class='c' >" + (jjCalendar_IR.getViewFormat(rowDocumentary.get(i).get(_dateCreation).toString())) + "</td>");
                html1.append("<td class='c' >" + (rowDocumentary.get(i).get(_status).toString()) + "</td>");
                String attachFilesDocumentary = rowDocumentary.get(i).get(_attachFileDocumentary).toString();
                String[] attachFileDocumentarysArray = attachFilesDocumentary.split(",");
                html1.append("<td class='c' >");
                for (int j = 0; j < attachFileDocumentarysArray.length; j++) {
                    List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFileDocumentarysArray[j] + "'"));
                    if (!fileRow.isEmpty()) {
                        html1.append("<a href=upload/" + fileRow.get(0).get(UploadServlet._file_name) + ">" + "<i class='fa fa-download'></i>" + fileRow.get(0).get(UploadServlet._title) + " </a><br/>");
                    }
                }
                html1.append("</td >");
                html1.append("</tr>");
            }
            html1.append("</tbody></table>");
            html1.append("</div></div>");

            String script3 = "$('#savabeghDocumentary').html(\"" + html1.toString() + "\");\n";
            script3 += Js.table("#refreshDocumentaryloading", "400", 0, "", "سوابق سنجه ها");

            String htmlBottons = "";
            if (!row.get(0).get(_status).toString().equals(status_Uploaded)) {// اگر وضعیت بارگذاری شده نبود دکمه های ویرایش و ثبت نهایی را نشان بدهد
                boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
                if (accEdit) {// هم دکمه ی ویرایش و هم ثبت نهایی با هم
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjDocumentary.edit() + "' id='edit_Documentary'>" + lbl_edit + "</button></div>";
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_finalize + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjDocumentary.editAndFinalize() + "' id='delete_Documentary'>" + lbl_finalize + "</button></div>";
                }
            } else {
                boolean accUndo = Access_User.hasAccess(request, db, rul_undo);
                if (accUndo) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_undo + "' class='btn btn-indigo - btn-block mg-b-10' onclick='" + Js.jjDocumentary.undo(id) + "' id='delete_Documentary'>" + lbl_undo + "</button></div>";
                }
            }//@ToDo برای امنیت بیشتر میتوانیم چک کنیم که اگر کاربر دسترسی به ویرایش رکوردهای همه را نداشت و دسترسی ویرایش خودش را داشت دکمه ویرایش را فقط مواقعی نشان دهیم که رکورد  مخصوص به خودش را سلکت کرده باشد
            script.append(Js.setHtml("#Documentary_button", htmlBottons));
            Server.outPrinter(request, response, html.toString() + script + script1 + script3);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_LoadingPeriod, jjTools.getParameter(request, _LoadingPeriod));
            map.put(_comments, jjTools.getParameter(request, _comments));
            map.put(_uploaderUser, jjTools.getSeassionUserId(request));
            map.put(_nameResponsibleLoading, jjTools.getSeassionUserNameAndFamily(request));

            map.put(_attachFileDocumentary, jjTools.getParameter(request, _attachFileDocumentary));
            if (!jjTools.getParameter(request, _status).isEmpty()) {// اگر کاربر دکمه ثبت نهایی را زده بود هم ویرایش انجام میشود و هم وضعیت به بارگذاری شده تغییر می کند
                changeStatus(request, response, db, id, status_Uploaded);
            }
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.خطای 278";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, "hmisDocumentary.m_refresh_my();");

            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    public static String undo(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_undo)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            changeStatus(request, response, db, id, status_noUploaded);
            Server.outPrinter(request, response, Js.jjDocumentary.select(id));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
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
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
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
            Server.outPrinter(request, response, Js.jjMessenger.refresh());
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }

    }

    /**
     * این متد از کلاینت دستور نمی گیردو فقط سمت سرور استفاده میشود
     *
     * @param db
     * @param id
     * @param newSatus
     * @return
     * @throws Exception
     */
    public static String changeStatus(jjDatabaseWeb db, String id, String newSatus) throws Exception {
        try {
            db.otherStatement("UPDATE " + tableName + " SET " + _logStatus
                    + "=concat(ifnull(" + _logStatus + ",''),'"
                    + newSatus
                    + "-"
                    + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
                    + "') ,"
                    + _status + "='" + newSatus + "'  WHERE id=" + id + ";");

            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            return "";

        }
    }

    public static String changeStatus(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String id, String newSatus) throws Exception {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }
            db.otherStatement("UPDATE " + tableName + " SET " + _logStatus
                    + "=concat(ifnull(" + _logStatus + ",''),'"
                    + newSatus
                    + "-"
                    + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
                    + "') ,"
                    + _status + "='" + newSatus + "'  WHERE id=" + id + ";");

            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            String errorMessage = "عملیات تغییر وضعیت به درستی صورت نگرفت.Err114";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        }
    }

    public static String getClassByStatus(String status) {
        if (status.equals(status_noUploaded)) {
            return "yellow";
        } else if (status.equals(status_Uploaded)) {
            return "green";
        }
        return "";
    }
}
