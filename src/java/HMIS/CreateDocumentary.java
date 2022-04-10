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

/**
 *
 * @author PadidarNB
 */
public class CreateDocumentary {

    public static String tableName = "hmis_createDocumentary";
    public static String _id = "id";
    public static String _title = "createDocumentary_title";
    public static String _date = "createDocumentary_date";
    public static String _summary = "createDocumentary_summary";
    public static String _file1 = "createDocumentary_file1";//فایل پیوست
    public static String _file2 = "createDocumentary_file2";// فایل پیوست
    public static String _file3 = "createDocumentary_file3";//فایل پیوست
    public static String _attachmentfile1 = "createDocumentary_attachmentfile1";//فایل پیوست
    public static String _attachmentfile2 = "createDocumentary_attachmentfile2";//فایل پیوست
    public static String _attachmentfile3 = "createDocumentary_attachmentfile3";//فایل پیوست
    public static String _titleFile3 = "createDocumentary_titleFile3";//عنوان فایل3
    public static String _titleFile2 = "createDocumentary_titleFile2";//
    public static String _titleFile1 = "createDocumentary_titleFile1";//
    public static String _category = "createDocumentary_category";//
    public static String _status = "createDocumentary_status";//وضعیت امضا
    public static String _logStatus = "createDocumentary_logStatus";//جزییات امضا
    public static String _revisionDate = "createDocumentary_revisionDate";//تاریخ بازنگری
    public static String _responsibleDocumentary = "createDocumentary_responsibleDocumentary";//مسئول مستند
    public static String _communicator = "createDocumentary_communicator";//ابلاغ کننده 
    public static String _reciversRoles = "createDocumentary_reciversRoles";// گیرندگان بر اساس نقش آنها
    public static String _reciversUsers = "createDocumentary_reciversUsers";// گیرندگان بر اساس آی دی کاربری آنها

    public static String _htmlContent = "createDocumentary_htmlContent";//SUMMERNOTE
    public static String _signatory_user_1 = "createDocumentary_signatory_user_1";//فرد امضا کننده
    public static String _signatory_user_2 = "createDocumentary_signatory_user_2";//امضا کننده
    public static String _signatory_user_3 = "createDocumentary_signatory_user_3";//امضا کننده
    public static String _signatory_user_4 = "createDocumentary_signatory_user_4";//امضا کننده
    public static String _signatory_user_5 = "createDocumentary_signatory_user_5";//امضا کننده
    public static String _signatory_user_6 = "createDocumentary_signatory_user_6";//امضا کننده
    public static String _signatory_user_7 = "createDocumentary_signatory_user_7";//امضا کننده
    public static String _signatory_user_8 = "createDocumentary_signatory_user_8";//امضا کننده
    public static String _signatory_user_9 = "createDocumentary_signatory_user_9";//امضا کننده
    public static String _signatory_user_10 = "createDocumentary_signatory_user_10";//امضا کننده
    public static String _signatory_user_11 = "createDocumentary_signatory_user_11";//امضا کننده
    public static String _signatory_user_12 = "createDocumentary_signatory_user_12";//امضا کننده
    public static String _signatory_user_13 = "createDocumentary_signatory_user_13";//امضا کننده
    public static String _signatory_user_14 = "createDocumentary_signatory_user_14";//امضا کننده
    public static String _signatory_user_15 = "createDocumentary_signatory_user_15";//امضا کننده
    public static String _signatory_user_16 = "createDocumentary_signatory_user_16";//امضا کننده
    public static String _signatory_user_17 = "createDocumentary_signatory_user_17";//امضا کننده
    public static String _signatory_user_18 = "createDocumentary_signatory_user_18";//امضا کننده
    public static String _signatory_user_19 = "createDocumentary_signatory_user_19";//امضا کننده
    public static String _signatory_user_20 = "createDocumentary_signatory_user_20";//امضا کننده

    public static String _signatory_title_1 = "createDocumentary_signatory_title_1";//امضا کننده عنوان
    public static String _signatory_title_2 = "createDocumentary_signatory_title_2";//امضا کننده عنوان
    public static String _signatory_title_3 = "createDocumentary_signatory_title_3";//امضا کننده عنوان
    public static String _signatory_title_4 = "createDocumentary_signatory_title_4";//امضا کننده عنوان
    public static String _signatory_title_5 = "createDocumentary_signatory_title_5";//امضا کننده عنوان
    public static String _signatory_title_6 = "createDocumentary_signatory_title_6";//امضا کننده عنوان
    public static String _signatory_title_7 = "createDocumentary_signatory_title_7";//امضا کننده عنوان
    public static String _signatory_title_8 = "createDocumentary_signatory_title_8";//امضا کننده عنوان
    public static String _signatory_title_9 = "createDocumentary_signatory_title_9";//امضا کننده عنوان
    public static String _signatory_title_10 = "createDocumentary_signatory_title_10";//امضا کننده عنوان
    public static String _signatory_title_11 = "createDocumentary_signatory_title_11";//امضا کننده عنوان
    public static String _signatory_title_12 = "createDocumentary_signatory_title_12";//امضا کننده عنوان
    public static String _signatory_title_13 = "createDocumentary_signatory_title_13";//امضا کننده عنوان
    public static String _signatory_title_14 = "createDocumentary_signatory_title_14";//امضا کننده عنوان
    public static String _signatory_title_15 = "createDocumentary_signatory_title_15";//امضا کننده عنوان
    public static String _signatory_title_16 = "createDocumentary_signatory_title_16";//امضا کننده عنوان
    public static String _signatory_title_17 = "createDocumentary_signatory_title_17";//امضا کننده عنوان
    public static String _signatory_title_18 = "createDocumentary_signatory_title_18";//امضا کننده عنوان
    public static String _signatory_title_19 = "createDocumentary_signatory_title_19";//امضا کننده عنوان
    public static String _signatory_title_20 = "createDocumentary_signatory_title_20";//امضا کننده عنوان

    public static String _signatory_signature_1 = "createDocumentary_signatory_signature_1";//امضا کننده امضا
    public static String _signatory_signature_2 = "createDocumentary_signatory_signature_2";//امضا کننده امضا
    public static String _signatory_signature_3 = "createDocumentary_signatory_signature_3";//امضا کننده امضا
    public static String _signatory_signature_4 = "createDocumentary_signatory_signature_4";//امضا کننده امضا
    public static String _signatory_signature_5 = "createDocumentary_signatory_signature_5";//امضا کننده امضا
    public static String _signatory_signature_6 = "createDocumentary_signatory_signature_6";//امضا کننده امضا
    public static String _signatory_signature_7 = "createDocumentary_signatory_signature_7";//امضا کننده امضا
    public static String _signatory_signature_8 = "createDocumentary_signatory_signature_8";//امضا کننده امضا
    public static String _signatory_signature_9 = "createDocumentary_signatory_signature_9";//امضا کننده امضا
    public static String _signatory_signature_10 = "createDocumentary_signatory_signature_10";//امضا کننده امضا
    public static String _signatory_signature_11 = "createDocumentary_signatory_signature_11";//امضا کننده امضا
    public static String _signatory_signature_12 = "createDocumentary_signatory_signature_12";//امضا کننده امضا
    public static String _signatory_signature_13 = "createDocumentary_signatory_signature_13";//امضا کننده امضا
    public static String _signatory_signature_14 = "createDocumentary_signatory_signature_14";//امضا کننده امضا
    public static String _signatory_signature_15 = "createDocumentary_signatory_signature_15";//امضا کننده امضا
    public static String _signatory_signature_16 = "createDocumentary_signatory_signature_16";//امضا کننده امضا
    public static String _signatory_signature_17 = "createDocumentary_signatory_signature_17";//امضا کننده امضا
    public static String _signatory_signature_18 = "createDocumentary_signatory_signature_18";//امضا کننده امضا
    public static String _signatory_signature_19 = "createDocumentary_signatory_signature_19";//امضا کننده امضا
    public static String _signatory_signature_20 = "createDocumentary_signatory_signature_20";//امضا کننده امضا
    public static String _signatory_comment_1 = "createDocumentary_signatory_comment_1";//امضا کننده توضیح
    public static String _signatory_comment_2 = "createDocumentary_signatory_comment_2";//امضا کننده توضیح
    public static String _signatory_comment_3 = "createDocumentary_signatory_comment_3";//امضا کننده توضیح
    public static String _signatory_comment_4 = "createDocumentary_signatory_comment_4";//امضا کننده توضیح
    public static String _signatory_comment_5 = "createDocumentary_signatory_comment_5";//امضا کننده توضیح
    public static String _signatory_comment_6 = "createDocumentary_signatory_comment_6";//امضا کننده توضیح
    public static String _signatory_comment_7 = "createDocumentary_signatory_comment_7";//امضا کننده توضیح
    public static String _signatory_comment_8 = "createDocumentary_signatory_comment_8";//امضا کننده توضیح
    public static String _signatory_comment_9 = "createDocumentary_signatory_comment_9";//امضا کننده توضیح
    public static String _signatory_comment_10 = "createDocumentary_signatory_comment_10";//امضا کننده توضیح
    public static String _signatory_comment_11 = "createDocumentary_signatory_comment_11";//امضا کننده توضیح
    public static String _signatory_comment_12 = "createDocumentary_signatory_comment_12";//امضا کننده توضیح
    public static String _signatory_comment_13 = "createDocumentary_signatory_comment_13";//امضا کننده توضیح
    public static String _signatory_comment_14 = "createDocumentary_signatory_comment_14";//امضا کننده توضیح
    public static String _signatory_comment_15 = "createDocumentary_signatory_comment_15";//امضا کننده توضیح
    public static String _signatory_comment_16 = "createDocumentary_signatory_comment_16";//امضا کننده توضیح
    public static String _signatory_comment_17 = "createDocumentary_signatory_comment_17";//امضا کننده توضیح
    public static String _signatory_comment_18 = "createDocumentary_signatory_comment_18";//امضا کننده توضیح
    public static String _signatory_comment_19 = "createDocumentary_signatory_comment_19";//امضا کننده توضیح
    public static String _signatory_comment_20 = "createDocumentary_signatory_comment_20";//امضا کننده توضیح

    public static String _signatory_role_1 = "createDocumentary_signatory_role_1";//نقش امضا کننده
    public static String _signatory_role_2 = "createDocumentary_signatory_role_2";
    public static String _signatory_role_3 = "createDocumentary_signatory_role_3";
    public static String _signatory_role_4 = "createDocumentary_signatory_role_4";
    public static String _signatory_role_5 = "createDocumentary_signatory_role_5";
    public static String _signatory_role_6 = "createDocumentary_signatory_role_6";
    public static String _signatory_role_7 = "createDocumentary_signatory_role_7";
    public static String _signatory_role_8 = "createDocumentary_signatory_role_8";
    public static String _signatory_role_9 = "createDocumentary_signatory_role_9";
    public static String _signatory_role_10 = "createDocumentary_signatory_role_10";
    public static String _signatory_role_11 = "createDocumentary_signatory_role_11";
    public static String _signatory_role_12 = "createDocumentary_signatory_role_12";
    public static String _signatory_role_13 = "createDocumentary_signatory_role_13";
    public static String _signatory_role_14 = "createDocumentary_signatory_role_14";
    public static String _signatory_role_15 = "createDocumentary_signatory_role_15";
    public static String _signatory_role_16 = "createDocumentary_signatory_role_16";
    public static String _signatory_role_17 = "createDocumentary_signatory_role_17";
    public static String _signatory_role_18 = "createDocumentary_signatory_role_18";
    public static String _signatory_role_19 = "createDocumentary_signatory_role_19";
    public static String _signatory_role_20 = "createDocumentary_signatory_role_20";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";
    public static String status_created = "ایجاد شده";
    public static String status_waitForCommunication = "در انتظار ابلاغ";
    public static String status_communicated = "ابلاغ شده";
    public static int rul_rfs = 518;
    public static int rul_rfs_communicateAll = 532;// مجوز دسترسی به مشاهده ی مستندات در مسیر ابلاغ برای خود ابلاغ کننده
    public static int rul_rfs_communicateOwn = 533;// مجوز دسترسی به مشاهده ی مستندات در مسیر ابلاغ برای خود ابلاغ کننده
    public static int rul_ins = 519;
    public static int rul_edt = 520;
    public static int rul_dlt = 521;
    public static int rul_rfsSignMydocumention = 525;

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
           if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();

            DefaultTableModel dtm = db.Select(CreateDocumentary.tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "<div class='card-header bg-primary tx-white'>مستند</div>"
                    + "<div class='card-body pd-sm-30'>"
                    + "<p class='mg-b-20 mg-sm-b-30'>"
                    + "    <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisCreateDocumentary.m_add_new();' > مستند جدید</a>"
                    + "</p>");

            html.append("<table class='table display responsive nowrap' id='refreshCreateDocumentary' dir='rtl'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>عنوان مستند</th>");
            html.append("<th class='c' width='30%'>تاییدامضا </th>");
            html.append("<th class='c' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c'>");
                ///این forبرای این است که کسانی که امضا کردند تیک سبز میخورد
                for (int j = 1; j <= 20; j++) {
                    if (!row.get(i).get("createDocumentary_signatory_user_" + j).equals("")) {
                        if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("1")) {
                            html.append("<img src='template/tick.png' style='height:15px;'/>");
//                    }
                        } else if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("0")) {

                            html.append("<img src='template/remove.png' style='height:19px;'/>");
                        } else if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("-1")) {

                            html.append("<img src='template/icons8-help-48.png' style='height:20px;'/>");
                        }
                    }
                }
                html.append("</td >");
                html.append("<td class='c'><a onclick='hmisCreateDocumentary.m_select(" + row.get(i).get(_id) + ");' ><i class='p icon ion-ios-gear-outline'></i></a></td>");
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
                panel = "swCreateDocumentaryTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshCreateDocumentary", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست مستندات");
            String script = "hmisSignDocumentary.m_refresh();";
            Server.outPrinter(request, response, html2 + script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    ////////    این تابع برای تغییر وضعیت پیام ونشان دادن روند وضعیت ایجاد شده توسظ شیران1
////////تاریخ ایجاد 1398/01/20
    public static String changeStatus(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String id, String newSatus) throws Exception {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                return Js.dialog(errorMessageId);
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
    /*
     این تابع برای نمایش جدول امضا وتایید مستندات من نوشته شده توسط شیران1
     */

    public static String refreshSignatureMyDocumentation(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
// if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
//                return "";
//            }
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "<div class='card-header bg-primary tx-white'>تایید وامضا مستندات من</div>"
                    + "<div class='card-body pd-sm-30'>"
            );

            html.append("<table class='table display responsive nowrap' id='refreshSignatureMyDocumentation' dir='rtl'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>عنوان مستند </th>");
            html.append("<th class='c' width='30%'>تایید امضا </th>");
            html.append("<th class='c' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
//            DefaultTableModel dtm = db.Select(CreateDocumentary.tableName);///////////////برای کسانی که در setlectoption هستند والان لاگین شدن
            for (int j = 1; j <= 20; j++) {
                String userid = String.valueOf(jjTools.getSeassionUserId(request));
                DefaultTableModel dtm = db.Select(CreateDocumentary.tableName, "createDocumentary_signatory_user_" + j + "='" + jjTools.getSessionAttribute(request, "#ID") + "'");//برای کسانی که در setlectoption هستند والان لاگین شدن
                List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
                for (int i = 0; i < row.size(); i++) {
                    if (userid.equals(row.get(i).get("createDocumentary_signatory_user_" + j))) {

                        html.append("<tr class='p'>");
                        html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");

                        html.append("<td class='c'>" + row.get(i).get(CreateDocumentary._title).toString() + "</td>");
                        html.append("<td class='c'>");
                        ///این forبرای این است که کسانی که امضا کردند تیک سبز میخورد
                        if (!row.get(i).get("createDocumentary_signatory_user_" + j).equals("")) {
                            if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("1")) {
                                html.append("<img src='template/tick.png' style='height:15px;'/>");
//                    }
                            } else if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("0")) {
                                html.append("<img src='template/remove.png' style='height:19px;'/>");
                            } else if (row.get(i).get("createDocumentary_signatory_signature_" + j).equals("-1")) {
                                html.append("<img src='template/icons8-help-48.png' style='height:20px;'/>");
                            }
                        }
                        html.append("</td >");
                        html.append("<td style='c'><a href='Server?do=CreateDocumentary.selectOneDocuementToSign&id=" + row.get(i).get(_id) + "' target='_blank'><i class='fa fa-pencil'></i></a>" + "</td>");
                        html.append("</tr>");
                    }
                }
            }

            html.append("</tbody></table>");
            html.append("</div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swSignDocumentaryTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshSignatureMyDocumentation", height, 0, "", "لیست مستندات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String refreshCommunications(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs_communicateAll) && Access_User.hasAccess(request, db, rul_rfs_communicateOwn)) {
                return "شما اجازه دسترسی به این بخش را ندارد";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "<div class='card-header bg-primary tx-white'>تایید وامضا مستندات من</div>"
                    + "<div class='card-body pd-sm-30'>"
            );

            html.append("<table class='table display responsive nowrap' id='swCommunicationsTbl' dir='rtl'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>عنوان مستند </th>");
            html.append("<th class='c' width='30%'>تایید امضا </th>");
            html.append("<th class='c' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
//            DefaultTableModel dtm = db.Select(CreateDocumentary.tableName);///////////////برای کسانی که در setlectoption هستند والان لاگین شدن
            DefaultTableModel dtm = new DefaultTableModel();
            if (Access_User.hasAccess(request, db, rul_rfs_communicateAll)) {// اگر به مشاهده ی همه مصوبات در حال ابلاغ دسترسی داشته باشد
                dtm = db.Select(CreateDocumentary.tableName, _status + "='" + status_communicated + "' OR " + _status + "='" + status_waitForCommunication + "'");//همه ی مشتنداتی که منتظر ابلاغ هستند یا ابلاغ شده اند                
            } else if (Access_User.hasAccess(request, db, rul_rfs_communicateOwn)) {// فقط مستنداتی که نقش آنها با یکی از نقش های  این کاربر جاری یکی است
                String UserRoles[] = jjTools.getSeassionUserRole(request).split(",");
                String RoleCondition = "";
                for (int j = 1; j <= UserRoles.length; j++) {
                    RoleCondition += " AND " + _communicator + "=" + UserRoles[j];
                }
                dtm = db.Select(CreateDocumentary.tableName, _status + "='" + status_communicated + "' OR " + _status + "='" + status_waitForCommunication + "'" + RoleCondition);
            }
            String userid = String.valueOf(jjTools.getSeassionUserId(request));
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='" + getClassByStatus(row.get(i).get(_status).toString()) + "'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c'>" + row.get(i).get(CreateDocumentary._title).toString() + "</td>");
                html.append("<td class='c'>" + row.get(i).get(CreateDocumentary._title).toString() + "</td>");
                html.append("<td style='c'><a href='Server?do=CreateDocumentary.selectOneDocuementToSign&id=" + row.get(i).get(_id) + "' target='_blank'><i class='fa fa-pencil'></i></a>" + "</td>");
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
                panel = "swCommunicationsTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshCommunicationsMyDocumentation", height, 0, "", "لیست مستندات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String selectOneDocuementToSign(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {

        try {
            String id = jjTools.getParameter(request, "id");
            int userID = jjTools.getSeassionUserId(request);
            String where = _id + "=" + id + " AND (";
            for (int i = 1; i < 20; i++) {
                where += " createDocumentary_signatory_user_" + i + "=" + userID + " OR ";
            }
            where += " createDocumentary_signatory_user_20" + "=" + userID + ")";

//        where += " AND " + ;
            if (db.Select(tableName, where).getRowCount() != 1) {
                String errorMessage = "شما اجازه ی دسترسی ندارید.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }

            request.setAttribute("db", db);
            request.getRequestDispatcher("template/signMyDocumentary.jsp").forward(request, response);

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
        return "";
    }

    public static String getCategorySelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _category, _category + "!='' "));
            StringBuilder optionHtml = new StringBuilder();
            for (int i = 0; i < row.size(); i++) {
                if (i == 0) {
                    optionHtml.append("<option  value='").append(row.get(i).get(_category)).append("' selected='selected'>").append(row.get(i).get(_category)).append("</option>");
                } else {
                    optionHtml.append("<option  value='").append(row.get(i).get(_category)).append("'>").append(row.get(i).get(_category)).append("</option>");
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = "#createDocumentary_category";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        StringBuilder script = new StringBuilder();
        try {

//            script.append("$('.form-group').select2({ width: '100%'});\n");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);

            if (accIns) {
                script.append(Js.setHtml("#CreateDocumentary_button", "<div class='col-lg-6'><input type='button' id='insert_CreateDocumentary_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'></div>"));
                script.append(Js.click("#insert_CreateDocumentary_new", Js.jjCreateDocumentary.insert()));
            }
            Server.outPrinter(request, response, html.toString() + script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
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
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_summary, jjTools.getParameter(request, _summary));
            map.put(_category, jjTools.getParameter(request, _category));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date).toString(), true));
            map.put(_revisionDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _revisionDate).toString(), true));
            map.put(_titleFile1, jjTools.getParameter(request, _titleFile1));
            map.put(_titleFile2, jjTools.getParameter(request, _titleFile2));
            map.put(_titleFile3, jjTools.getParameter(request, _titleFile3));
            map.put(_attachmentfile1, jjTools.getParameter(request, _attachmentfile1));
            map.put(_attachmentfile2, jjTools.getParameter(request, _attachmentfile2));
            map.put(_attachmentfile3, jjTools.getParameter(request, _attachmentfile3));
            map.put(_file1, jjTools.getParameter(request, _file1));
            map.put(_file2, jjTools.getParameter(request, _file2));
            map.put(_file3, jjTools.getParameter(request, _file3));
            map.put(_responsibleDocumentary, jjTools.getParameter(request, _responsibleDocumentary));
            map.put(_communicator, jjTools.getParameter(request, _communicator));
            map.put(_reciversRoles, jjTools.getParameter(request, _reciversRoles));
            map.put(_reciversUsers, jjTools.getParameter(request, _reciversUsers));

            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            map.put(_signatory_user_1, jjTools.getParameter(request, _signatory_user_1));
            map.put(_signatory_user_2, jjTools.getParameter(request, _signatory_user_2));
            map.put(_signatory_user_3, jjTools.getParameter(request, _signatory_user_3));
            map.put(_signatory_user_4, jjTools.getParameter(request, _signatory_user_4));
            map.put(_signatory_user_5, jjTools.getParameter(request, _signatory_user_5));
            map.put(_signatory_user_6, jjTools.getParameter(request, _signatory_user_6));
            map.put(_signatory_user_7, jjTools.getParameter(request, _signatory_user_7));
            map.put(_signatory_user_8, jjTools.getParameter(request, _signatory_user_8));
            map.put(_signatory_user_9, jjTools.getParameter(request, _signatory_user_9));
            map.put(_signatory_user_10, jjTools.getParameter(request, _signatory_user_10));
            map.put(_signatory_user_11, jjTools.getParameter(request, _signatory_user_11));
            map.put(_signatory_user_12, jjTools.getParameter(request, _signatory_user_12));
            map.put(_signatory_user_13, jjTools.getParameter(request, _signatory_user_13));
            map.put(_signatory_user_14, jjTools.getParameter(request, _signatory_user_14));
            map.put(_signatory_user_15, jjTools.getParameter(request, _signatory_user_15));
            map.put(_signatory_user_16, jjTools.getParameter(request, _signatory_user_16));
            map.put(_signatory_user_17, jjTools.getParameter(request, _signatory_user_17));
            map.put(_signatory_user_18, jjTools.getParameter(request, _signatory_user_18));
            map.put(_signatory_user_19, jjTools.getParameter(request, _signatory_user_19));
            map.put(_signatory_user_20, jjTools.getParameter(request, _signatory_user_20));
            map.put(_signatory_title_1, jjTools.getParameter(request, _signatory_title_1));
            map.put(_signatory_title_2, jjTools.getParameter(request, _signatory_title_2));
            map.put(_signatory_title_3, jjTools.getParameter(request, _signatory_title_3));
            map.put(_signatory_title_4, jjTools.getParameter(request, _signatory_title_4));
            map.put(_signatory_title_5, jjTools.getParameter(request, _signatory_title_5));
            map.put(_signatory_title_6, jjTools.getParameter(request, _signatory_title_6));
            map.put(_signatory_title_7, jjTools.getParameter(request, _signatory_title_7));
            map.put(_signatory_title_8, jjTools.getParameter(request, _signatory_title_8));
            map.put(_signatory_title_9, jjTools.getParameter(request, _signatory_title_9));
            map.put(_signatory_title_10, jjTools.getParameter(request, _signatory_title_10));
            map.put(_signatory_title_11, jjTools.getParameter(request, _signatory_title_11));
            map.put(_signatory_title_12, jjTools.getParameter(request, _signatory_title_12));
            map.put(_signatory_title_13, jjTools.getParameter(request, _signatory_title_13));
            map.put(_signatory_title_14, jjTools.getParameter(request, _signatory_title_14));
            map.put(_signatory_title_15, jjTools.getParameter(request, _signatory_title_15));
            map.put(_signatory_title_16, jjTools.getParameter(request, _signatory_title_16));
            map.put(_signatory_title_17, jjTools.getParameter(request, _signatory_title_17));
            map.put(_signatory_title_18, jjTools.getParameter(request, _signatory_title_18));
            map.put(_signatory_title_19, jjTools.getParameter(request, _signatory_title_19));
            map.put(_signatory_title_20, jjTools.getParameter(request, _signatory_title_20));
            map.put(_signatory_comment_1, jjTools.getParameter(request, _signatory_comment_1));
            map.put(_signatory_comment_2, jjTools.getParameter(request, _signatory_comment_2));
            map.put(_signatory_comment_3, jjTools.getParameter(request, _signatory_comment_3));
            map.put(_signatory_comment_4, jjTools.getParameter(request, _signatory_comment_4));
            map.put(_signatory_comment_5, jjTools.getParameter(request, _signatory_comment_5));
            map.put(_signatory_comment_6, jjTools.getParameter(request, _signatory_comment_6));
            map.put(_signatory_comment_7, jjTools.getParameter(request, _signatory_comment_7));
            map.put(_signatory_comment_8, jjTools.getParameter(request, _signatory_comment_8));
            map.put(_signatory_comment_9, jjTools.getParameter(request, _signatory_comment_9));
            map.put(_signatory_comment_10, jjTools.getParameter(request, _signatory_comment_10));
            map.put(_signatory_comment_11, jjTools.getParameter(request, _signatory_comment_11));
            map.put(_signatory_comment_12, jjTools.getParameter(request, _signatory_comment_12));
            map.put(_signatory_comment_13, jjTools.getParameter(request, _signatory_comment_13));
            map.put(_signatory_comment_14, jjTools.getParameter(request, _signatory_comment_14));
            map.put(_signatory_comment_15, jjTools.getParameter(request, _signatory_comment_15));
            map.put(_signatory_comment_16, jjTools.getParameter(request, _signatory_comment_16));
            map.put(_signatory_comment_17, jjTools.getParameter(request, _signatory_comment_17));
            map.put(_signatory_comment_18, jjTools.getParameter(request, _signatory_comment_18));
            map.put(_signatory_comment_19, jjTools.getParameter(request, _signatory_comment_19));
            map.put(_signatory_comment_20, jjTools.getParameter(request, _signatory_comment_20));
            map.put(_signatory_role_1, jjTools.getParameter(request, _signatory_role_1));
            map.put(_signatory_role_2, jjTools.getParameter(request, _signatory_role_2));
            map.put(_signatory_role_3, jjTools.getParameter(request, _signatory_role_3));
            map.put(_signatory_role_4, jjTools.getParameter(request, _signatory_role_4));
            map.put(_signatory_role_5, jjTools.getParameter(request, _signatory_role_5));
            map.put(_signatory_role_6, jjTools.getParameter(request, _signatory_role_6));
            map.put(_signatory_role_7, jjTools.getParameter(request, _signatory_role_7));
            map.put(_signatory_role_8, jjTools.getParameter(request, _signatory_role_8));
            map.put(_signatory_role_9, jjTools.getParameter(request, _signatory_role_9));
            map.put(_signatory_role_10, jjTools.getParameter(request, _signatory_role_10));
            map.put(_signatory_role_11, jjTools.getParameter(request, _signatory_role_11));
            map.put(_signatory_role_12, jjTools.getParameter(request, _signatory_role_12));
            map.put(_signatory_role_13, jjTools.getParameter(request, _signatory_role_13));
            map.put(_signatory_role_14, jjTools.getParameter(request, _signatory_role_14));
            map.put(_signatory_role_15, jjTools.getParameter(request, _signatory_role_15));
            map.put(_signatory_role_16, jjTools.getParameter(request, _signatory_role_16));
            map.put(_signatory_role_17, jjTools.getParameter(request, _signatory_role_17));
            map.put(_signatory_role_18, jjTools.getParameter(request, _signatory_role_18));
            map.put(_signatory_role_19, jjTools.getParameter(request, _signatory_role_19));
            map.put(_signatory_role_20, jjTools.getParameter(request, _signatory_role_20));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            } else {
                //@ToDo نمایش وضعیت مستند
                changeStatus(request, response, db, row.get(0).get(_id).toString(), status_created);// ایجاد وضعیت ایجاد شده 
            }
            Server.outPrinter(request, response, Js.jjCreateDocumentary.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("");
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

            StringBuilder html = new StringBuilder();
            script.append(Js.setVal("#createDocumentary_" + _id, row.get(0).get(_id)));

            script.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            script.append(Js.setVal("#" + _titleFile1, row.get(0).get(_titleFile1)));
            script.append(Js.setVal("#" + _titleFile2, row.get(0).get(_titleFile2)));
            script.append(Js.setVal("#" + _titleFile3, row.get(0).get(_titleFile3)));
            script.append(Js.setVal("#" + _responsibleDocumentary, row.get(0).get(_responsibleDocumentary)));
            script.append(Js.select2("#" + _responsibleDocumentary, ""));
            script.append(Js.setVal("#" + _communicator, row.get(0).get(_communicator)));
            script.append(Js.select2("#" + _communicator, ""));
            script.append(Js.setValSelectOption("#" + _reciversRoles, row.get(0).get(_reciversRoles).toString()));
            script.append(Js.select2("#" + _reciversRoles, ""));
            script.append(Js.setValSelectOption("#" + _reciversUsers, row.get(0).get(_reciversUsers).toString()));
            script.append(Js.select2("#" + _reciversUsers, ""));
            script.append(Js.setVal("#" + _category, row.get(0).get(_category)));
            script.append(Js.setAttr("#Downloadfile3", "href", "upload/" + row.get(0).get(_attachmentfile3)));
            script.append(Js.setAttr("#Downloadfile2", "href", "upload/" + row.get(0).get(_attachmentfile2)));
            script.append(Js.setAttr("#Downloadfile1", "href", "upload/" + row.get(0).get(_attachmentfile1)));

            script.append(Js.setVal("#createDocumentary_attachmentfile3", row.get(0).get(_attachmentfile3)));
            script.append(Js.setVal("#createDocumentary_attachmentfile2", row.get(0).get(_attachmentfile2)));
            script.append(Js.setVal("#createDocumentary_attachmentfile1", row.get(0).get(_attachmentfile1)));
//            script.append(Js.setVal("#createDocumentary_attachmentfile1", row.get(0).get(_attachmentfileTitle1)));
//            script.append(Js.setVal("#title1", row.get(0).get(_attachmentfileTitle1)));

            if (row.get(0).get(_attachmentfile1).equals("")) {
                script.append(Js.setAttr("#PicPreviewFile1", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreviewFile1", "src", "upload/" + row.get(0).get(_attachmentfile1).toString() + ""));
                script.append(Js.show("#Downloadfile1"));
            }
            if (row.get(0).get(_attachmentfile2).equals("")) {
                script.append(Js.setAttr("#PicPreviewFile2", "src", "img/preview.jpg"));
                script.append(Js.hide("#Downloadfile2"));
            } else {
                script.append(Js.setAttr("#PicPreviewFile1", "src", "upload/" + row.get(0).get(_attachmentfile2).toString() + ""));
                script.append(Js.show("#Downloadfile2"));
            }
            if (row.get(0).get(_attachmentfile3).equals("")) {
                script.append(Js.setAttr("#PicPreviewFile3", "src", "img/preview.jpg"));
                script.append(Js.hide("#Downloadfile3"));
            } else {
                script.append(Js.setAttr("#PicPreviewFile3", "src", "upload/" + row.get(0).get(_attachmentfile3).toString() + ""));
                script.append(Js.show("#Downloadfile3"));
            }
            StringBuilder script2 = new StringBuilder();
            StringBuilder script3 = new StringBuilder();
            StringBuilder script4 = new StringBuilder();
            StringBuilder script5 = new StringBuilder();
            String html3 = "";
            String html4 = "";
            String htmlRole = "";///برای در آوردن نقش ها
            String html6 = "";
            String temp = "";

///این قسمت برای نمایش امضا کنندگان نوشته شده
/////توسط شیران1
            for (int i = 1; i <= 20; i++) {
                htmlRole = "";
                if (!row.get(0).get("createDocumentary_signatory_user_" + i).toString().isEmpty()) {//این ifاگر امضا کرده باشدو داخل امضا کننده خالی بود
                    List<Map<String, Object>> UserRow = jjDatabase.separateRow(db.Select(Access_User.tableName, _id + "=" + row.get(0).get("createDocumentary_signatory_user_" + i).toString()));///برای در اوردن نام وفامیلی امضا کننده
                    System.out.println(">>>>>>>>>>>>>>>>>>>" + i);
                    if ((row.get(0).get("createDocumentary_signatory_signature_" + i).equals("0") || row.get(0).get("createDocumentary_signatory_signature_" + i).equals("1"))) {///اگر امضا کننده 0یا 1 باشد و همچنین یوزر امضا کننده خالی باشد این if میشود
                        html3 += "<div class='row col-lg-12 ' id='row" + i + "'>\n"
                                + "<div class=\"col-lg-3\">\n"
                                + "عنوان امضا کننده\n"
                                + "<input class='form-control'  id='signatory_title_" + i + "' name='createDocumentary_signatory_title_" + i + "' disabled='disabled' value='" + row.get(0).get("createDocumentary_signatory_title_" + i).toString() + "' />"
                                + "</div>\n"
                                + "<div class=\"col-lg-3\">\n"
                                + "فرد امضا کننده\n"
                                + "<input id='createDocumentary_signatory_user_" + (i) + "' name=id='createDocumentary_signatory_user_" + (i) + "'  disabled='disabled'  class='signerDiv form-control' value='" + UserRow.get(0).get(Access_User._name).toString() + " " + UserRow.get(0).get(Access_User._family).toString() + "' />"
                                + "</div>\n"
                                + "<div class=\"col-lg-3\">\n"
                                + "سمت امضا کننده\n"
                                + "<input id='signatory_role_" + i + "' name='createDocumentary_signatory_role_" + i + "' class='form-control' disabled='disabled' value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "'  />";
                        List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "=" + row.get(0).get("createDocumentary_signatory_user_" + i).toString()));
                        for (int k = 0; k < UserRowRole.size(); k++) {
//
                            htmlRole += "<option value='" + UserRowRole.get(k).get(Role._title) + "'"
                                    + (UserRowRole.get(k).get(Role._title).equals(Role._title) ? " selected='selected'>" : ">")
                                    + UserRowRole.get(k).get(Role._title) + "</option>\n";//'option' and 'value' for this fild is same('value' is not necessary)
//
                        }
                        //                                + "<select id='signatory_role_" + i + "' name='createDocumentary_signatory_role_" + i + "' class='form-control' disabled='disabled' value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "' onchange='$(this).parent.find($(this).val());'  />"
                        html3 += "<select id='signatory_role_" + i + "'  name='createDocumentary_signatory_role_" + i + "' class='form-control'  value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "' onchange='$(this).parent()'  /></select>"
                                + "</div>";

                        if (row.get(0).get("createDocumentary_signatory_signature_" + i).equals("0")) {
                            html3 += "<div class='col-lg-3'><img src='template/remove.png' style='height:34px;margin-top: 21px; '/></div>";
                        }
                        if (row.get(0).get("createDocumentary_signatory_signature_" + i).equals("1")) {
                            html3 += "<div class='col-lg-3'><img src='template/tick.png' style='height:30px;margin-top: 22px;'/></div>";
                        }
                        html3 += "</div>";
                        html6 += Js.setHtml("#signatory_role_" + i, htmlRole);
                    } else {
                        html3 += "<div class='row col-lg-12 ' id='row'>"
                                + "<div class='col-lg-3'>"
                                + "عنوان امضا کننده"
                                + "<input class='form-control c'  id='signatory_title_" + i + "' "
                                + "name='createDocumentary_signatory_title_" + i + "' "
                                + "value='" + row.get(0).get("createDocumentary_signatory_title_" + i).toString() + "' />"
                                + "</div>"
                                + "<div class='col-lg-3'>"
                                + "فرد امضا کننده"
                                + "<select id='createDocumentary_signatory_user_" + (i) + "' "
                                + "name='createDocumentary_signatory_user_" + (i) + "' class='signerDiv form-control' > "
                                + "<option value='" + UserRow.get(0).get(_id) + "' >"
                                + UserRow.get(0).get(Access_User._name).toString() + " " + UserRow.get(0).get(Access_User._family).toString()
                                + "</option>"
                                + "</select>"
                                + "</div>"
                                + "<div class='col-lg-3'>"
                                + "سمت امضا کننده"
                                + "<input id='signatory_role_" + i + "' name='createDocumentary_signatory_role_" + i + "' class='form-control'  value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "'  />";
                        List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "=" + row.get(0).get("createDocumentary_signatory_user_" + i).toString()));
                        for (int k = 0; k < UserRowRole.size(); k++) {
//
                            htmlRole += "<option value='" + UserRowRole.get(k).get(Role._title) + "'"
                                    + (UserRowRole.get(k).get(Role._title).equals(Role._title) ? " selected='selected'>" : ">")
                                    + UserRowRole.get(k).get(Role._title) + "</option>\n";//'option' and 'value' for this fild is same('value' is not necessary)
//
                        }
                        html3 += "<select id='signatory_roles_" + i + "'  name='createDocumentary_signatory_role_" + i + "' class='form-control'  value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "' "
                                + "onchange='hmisCreateDocumentary.setRolInTextField(this);'/></select>"
                                //                        html3 += "<select id='signatory_roles_" + i + "'  name='createDocumentary_signatory_role_" + i + "' class='form-control'  value='" + row.get(0).get("createDocumentary_signatory_role_" + i).toString() + "' onchange='hmisCreateDocumentary.selectRole($(this).val(),signatory_role_"+ i+");'/></select>"
                                + "</div>"
                                + "<div class='col-lg-3'>"
                                + "<button class='btn btn-outline-danger btn-block mg-t-20 mg-b-20  buttonRemove'>حذف</button>"
                                + "<button class='btn btn-outline btn-block mg-t-20 mg-b-20' id='payam' onclick='hmisMessenger.sendMesseageToSignatory(" + UserRow.get(0).get(Access_User._id) + "," + row.get(0).get(_id) + ")'>ارسال پیام</button>"
                                + "</div>"
                                + "</div>";
                    }
                }
//                script4.append(Js.setVal("signatory_roles_" + i, html));
//                html5+=Js.setHtml("#signatory_roles_" + i," ");
                html6 += Js.setHtml("#signatory_roles_" + i, htmlRole);
            }
            script4.append(Js.setHtml("#signatorys", html3));
            script.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString())));
            script.append(Js.setVal("#" + _revisionDate,jjCalendar_IR.getViewFormat(row.get(0).get(_revisionDate).toString())));    
            
            script.append(Js.setVal("#" + _summary, row.get(0).get(_summary).toString()));
            script.append(Js.setVal("#" + _category, row.get(0).get(_category).toString()));

            script.append(Js.setValSummerNote("#createDocumentary_htmlContent", row.get(0).get(_htmlContent)));

            boolean flag = true;
            //ویژگی : اگر یکی از امضا کنندگان مستند را رد بکند دیگر امکان یرایش مستند وجود ندارد ولی امکان حذف وجود دارد
            for (int j = 1; j <= 20; j++) {
                if (row.get(0).get("createDocumentary_signatory_signature_" + j).equals("0")) {
                    flag = false;
                }
            }
            String htmlBottons = "";
            if (flag == false) {
                script1.append(Js.setHtml("#CreateDocumentary_button", ""));
            } else {
                boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
                if (accEdit && row.get(0).get(_status).equals(status_created)) {// اگر وضعیت ابلاغ شده یا در انتظار ابلاغ بود دکمه ابلاغ را نشان ندهیم
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjCreateDocumentary.edit() + "' id='edit_CreateDocumentary'>" + lbl_edit + "</button></div>";
                    htmlBottons += "<div class='col-lg'><button title='ارسال جهت ابلاغ' class='btn btn-outline-purple btn-block mg-b-10' onclick='hmisCreateDocumentary.sendForCommunication(" + id + ");' id='edit_CreateDocumentary'>ارسال جهت ابلاغ</button></div>";
                }
                script1.append(Js.setHtml("#CreateDocumentary_button", htmlBottons));
            }

            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjCreateDocumentary.delete(id) + "' id='delete_CreateDocumentary'>" + lbl_delete + "</button></div>";
            }
            script1.append(Js.setHtml("#CreateDocumentary_button", htmlBottons));

            Server.outPrinter(request, response, html.toString() + script + script2 + script3 + script4 + script1 + html6);
            return "";
//
//           
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

//    *
//    این تابع برای کپی کردن فایل یعنی اگر بخواهیم صفحه ای که داخلش هستیم را کپی کنیم ازاین تابع استفاده می کنیم
//*/
    public static String copyDocumentary(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        StringBuilder script = new StringBuilder();
        try {

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);

            if (accIns) {
                script.append(Js.setHtml("#CreateDocumentary_button", "<div class='col-lg-6'><input type='button' id='insert_CreateDocumentary_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'></div>"));
                script.append(Js.click("#insert_CreateDocumentary_new", Js.jjCreateDocumentary.insert()));
            }
            Server.outPrinter(request, response, html.toString() + script);
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_date, jjTools.getParameter(request, _date));
            map.put(_revisionDate, jjTools.getParameter(request, _revisionDate));
            map.put(_category, jjTools.getParameter(request, _category));
            map.put(_summary, jjTools.getParameter(request, _summary));
            map.put(_titleFile1, jjTools.getParameter(request, _titleFile1));
            map.put(_titleFile2, jjTools.getParameter(request, _titleFile2));
            map.put(_titleFile3, jjTools.getParameter(request, _titleFile3));
            map.put(_attachmentfile1, jjTools.getParameter(request, _attachmentfile1));
            map.put(_attachmentfile2, jjTools.getParameter(request, _attachmentfile2));
            map.put(_attachmentfile3, jjTools.getParameter(request, _attachmentfile3));
            map.put(_responsibleDocumentary, jjTools.getParameter(request, _responsibleDocumentary));
            map.put(_communicator, jjTools.getParameter(request, _communicator));
            map.put(_reciversRoles, jjTools.getParameter(request, _reciversRoles));
            map.put(_reciversUsers, jjTools.getParameter(request, _reciversUsers));
////////////////////اگرuserخالی بود
///////////////هیچ کاری نکند یعنی هرچی داخل دیتا بیس هست را نشان بدهد در غیر این صورت 
/////////////داخلش map می شود
            boolean editable = true;
            for (int i = 1; i <= 20; i++) {
                if (row.get(0).get("createDocumentary_signatory_signature_" + i).equals("0") || row.get(0).get("createDocumentary_signatory_signature_" + i).equals("1")) {
                    editable = false;
                    ;//نام امضا کننده با وضعیت امضا غیر قابل ویرایش
                } else {
                    //مشخصاتی که قبلا در محل امضا کننده هست را برای ویرایش می گذاریم
                    map.put("createDocumentary_signatory_user_" + i, jjTools.getParameter(request, "createDocumentary_signatory_user_" + i));
                    map.put("createDocumentary_signatory_title_" + i, jjTools.getParameter(request, "createDocumentary_signatory_title_" + i));
                    map.put("createDocumentary_signatory_role_" + i, jjTools.getParameter(request, "createDocumentary_signatory_role_" + i));
                }
            }
            if (editable) {// اگر قبلا کسی امضا یا رد کرده باشد محتوا قابل تغییر نیست
                map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            }

            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCreateDocumentary.refresh());
            return "";
//            Server.outPrinter(request, response,"";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String saveSign(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            String id = jjTools.getParameter(request, "id");
//           

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(CreateDocumentary.tableName, _id + "=" + id));
            if (row.isEmpty()) {
                return "چنین رکوردی یافت نشد";
            }
            Map<String, Object> map = new HashMap();
            String errorMessage = "";
            boolean flag = true;
            for (int i = 1; i <= 20; i++) {

                if (!row.get(0).get("createDocumentary_signatory_user_" + i).toString().isEmpty()) {
                    if (row.get(0).get("createDocumentary_signatory_signature_" + i).toString().equals("0")) {
                        flag = false;
                    }
                    String userid = String.valueOf(jjTools.getSeassionUserId(request));
                    if (userid.equals(row.get(0).get("createDocumentary_signatory_user_" + i))) {
                        errorMessage = "";
                        if ("0".equals(row.get(0).get("createDocumentary_signatory_signature_" + i)) || "1".equals(row.get(0).get("createDocumentary_signatory_signature_" + i))) {

                            errorMessage = "شما قبلا این مستند را ثبت کرده اید و اکنون مجاز به تغییر رای خود نیستید.";
                        } else {
                            map.put("createDocumentary_signatory_comment_" + i, jjTools.getParameter(request, "createDocumentary_signatory_comment_" + i));
                            map.put("createDocumentary_signatory_signature_" + i, jjTools.getParameter(request, "createDocumentary_signatory_signature_" + i));
                            errorMessage = "";
                            i = 21;//برای خروج از حلقه
                        }
                    } else {
                        errorMessage = "شما کاربر مجاز برای تایید یا رد این مستند نیستید.";
                    }
                }
            }
            if (!flag) {
                errorMessage = "این سند توسط یکی از امضا کنندگان رد و باطل شده است";
            }
            if (!errorMessage.isEmpty()) {
                Server.outPrinter(request, response, Js.modal(errorMessage, "شما اجازه این عملیات را ندارید"));
                return "";
            }
            System.out.println("id=" + id);
            if (!db.update(tableName, map, _id + "=" + id)) {
                errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            StringBuilder script = new StringBuilder();
            for (int j = 1; j < 20; j++) {
                if (!row.get(0).get("createDocumentary_signatory_user_" + j).toString().equals("")) {
                    String userid = String.valueOf(jjTools.getSeassionUserId(request));
                    if (userid.equals(row.get(0).get("createDocumentary_signatory_user_" + j))) {
                        if (jjTools.getParameter(request, "createDocumentary_signatory_signature_" + j).equals("0")) {
                            script.append("alert('مستند مورد نظر  رد  و باطل شد')");
                            Server.outPrinter(request, response, script);
                        } else if (jjTools.getParameter(request, "createDocumentary_signatory_signature_" + j).equals("1")) {
                            script.append(Js.setHtml("#formSign" + j, ""));
                            script.append("alert('مستند مورد نظر تایید و امضا شد');");
                        }
                    }
                }
            }
            script.append("location.reload();");// برای رفرش شده صفحه ی جی اس پی
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String sendForCommunication(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(CreateDocumentary.tableName, _id + "=" + id));
            if (row.isEmpty()) {
                return "چنین رکوردی یافت نشد";
            }
            changeStatus(request, response, db, id, status_waitForCommunication);
            //@ToDo ارسال پیام برای ابلاغ کننده
            Server.outPrinter(request, response, Js.jjCreateDocumentary.refresh() + "\n" + Js.modal("مستند در انتظار ابلاغ توسط ابلاغ کننده است", "پیام سامانه"));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ایلاغ نهایی مستند
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String communication(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (jjTools.getSeassionUserId(request) == 0) {// اگر کاربر لاگین نکرده بود
                Server.outPrinter(request, response, Js.modal("لطفا در سامانه وارد شود", "پیام سامانه"));
                return "";
            }
            String id = jjTools.getParameter(request, "id");
            Map<String, Object> map = new HashMap<>();
            map.put(_reciversRoles, jjTools.getParameter(request, _reciversRoles));
            map.put(_reciversUsers, jjTools.getParameter(request, _reciversUsers));
            db.update(tableName, map, id);//گیرندگانی که ابلاغ کننده مشحص کرده را بروز رسانی می کنیم
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String communicatorUserId = "" + jjTools.getSeassionUserId(request);//برای امنیت آی دی شخصی که درسشن است به عنوان ابلاغ کننده ثبت میشود
            StringBuilder html = new StringBuilder();
            html.append("<a href='" + Server.siteName + "/Server?do=CreateDocumentary.selectOneDocuementToSign&id=77' >");
            html.append(row.get(0).get(_title).toString());
            html.append("لینک مستند");
            html.append("</a>");
//            if(jjTools.getParameter(request, _reciversRoles).contains("ALL")){// اگر همه ی نقش ها را تیک زده بود
//                List<Map<String, Object>> row = jjDatabase.separateRow(db.JoinLeft(Role.tableName, Access_User.tableName,Access_User._id, Role._commentd, _title, _category))
//            String reciverRoles[] = jjTools.getParameter(request, _reciversRoles).split(",");
//            }
            String reciverRoles[] = jjTools.getParameter(request, _reciversRoles).split(",");
            String receiversUserIDs = "";// گیرندگان
            for (int i = 0; i < reciverRoles.length; i++) {
                if (jjNumber.isDigit(reciverRoles[i])) {
                    receiversUserIDs = Role.getUeserIdByUserRole(reciverRoles[i], db) + ",";
                }
            }
            receiversUserIDs += jjTools.getParameter(request, _reciversUsers);
            Messenger.sendMesseage(request, db, receiversUserIDs, communicatorUserId, "app,sms,email", null, "ابلاغیه", row.get(0).get(_title).toString(), html.toString(),"یادآوری",Tice_config.getValue(db, Tice_config._config_activeSmsModuleDocumentary_name),Tice_config.getValue(db, Tice_config._config_activeEmailModuleDocumentary_name));
            changeStatus(request, response, db, id, status_communicated);// تغییر وضعیت به ایلاغ شده
            //@ToDo ارسال پیام برای ابلاغ کننده
            Server.outPrinter(request, response, Js.modal("مستند ابلاغ و به گیرندگان پیام مشاهده ی مشتند ارسال شد", "پیام سامانه"));
            return "";
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

            Server.outPrinter(request, response, Js.jjCreateDocumentary.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getClassByStatus(String status) {
        if (status.equals(status_waitForCommunication)) {
            return "yellow";
        } else if (status.equals(status_communicated)) {
            return "green";
        }
        return "";
    }

}
