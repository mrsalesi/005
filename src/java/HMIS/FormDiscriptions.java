/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import cms.tools.jjTools;
import java.util.ArrayList;
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
 * @author Mohammad
 */
public class FormDiscriptions {

    public static final String tableName = "hmis_formdiscriptions";
    public static final String _id = "id";
    public static final String _formId = "formDiscriptions_formId";
    public static final String _title = "formDiscriptions_title";
    public static final String _date = "formDiscriptions_date";
    public static final String _files = "formDiscriptions_files";
    public static final String _writerUserId = "formDiscriptions_writerUserId";
    public static final String _htmlContent = "formDiscriptions_discription";

    public static final String lbl_insert = "ثبت تحلیل";
    public static final String lbl_delete = "حذف تحلیل";
    public static final String lbl_edit = "ثبت ویرایش";  

    public static int rul_rfs = 0;//60;
    public static int rul_rfsAllForm = 0;//60;
    public static int rul_rfsMyForm = 0;//60;
    public static int rul_rfs_own = 0;// 61;//فقط امکان دیدن فرم های ایجاد شده ی توسط خود ایجاد کننده را دارد // بر روی سمت
    public static int rul_ins = 0;// 62;
    public static int rul_insForOther = 0;// برای اینکه بتواند برای بقیه فرم تعریف بکند @ToDo 
    public static int rul_edt = 0;// 63;
    public static int rul_dlt = 0;// 64;    
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
            String form_id = jjTools.getParameter(request,"formAnswers_formId");// آی دی در اینجا منظور آی دی فرم است و نه آی دی تحلیل
            if (!jjNumber.isDigit(form_id)) {
                Server.outPrinter(request, response, "خطای شماره ی 5432 , کد فرم باید عدد باشد");
                return "";
            }      
            DefaultTableModel dtm = db.Select(tableName, _formId + "=" + form_id);//@ToDo فقط ستون هایی که لازم هست را بگیریم که در مصرف حاقظه رم سرفه جویی بشود
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            StringBuilder html = new StringBuilder();
            html.append("<div class='card-header bg-primary tx-white'>لیست تحلیل های ثبت شده برای این فرم</div>\n");
            html.append("<div class='card-body pd-sm-30'>");
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append("<p class='mg-b-20 mg-sm-b-30'>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='hmisFormDiscriptions.m_add_new();' >ایجاد تحلیل جدید</a>");
                html.append("</p>");
            }
            html.append("<div class='table-wrapper'>");
            html.append("<table id='refreshFormDiscriptions' class='table display responsive' class='tahoma10' style='direction: rtl'><thead>");
            html.append("<th width='5%' class='r'>کد</th>");
            html.append("<th width='20%' class='r'>عنوان تحلیل</th>");
            html.append("<th width='20%' class='r'>تاریخ</th>");
            html.append("<th width='20%' class='c'>ویرایش و اصلاح</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='r'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'>" +jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
//                html.append("<td class='c'><i class='p icon ion-ios-gear' onclick='" + Js.jjFormDiscriptions.select(row.get(i).get(_id).toString()) + "' ></i></td>");
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
                panel = "refreshFormDiscriptionsDiv";
            }
            if (needString) {
                return html.toString();
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshForm", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست تحلیل های این فرم");
            Server.outPrinter(request, response, script);
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
                script.append(Js.setHtml("#formDiscription_buttons", ""));
                script.append(Js.modal("لطفا دوباره وارد شوید", "شما دسترسی به این قسمت ندارد"));
                Server.outPrinter(request, response, script);
                return "";
            }
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, _id + "=" + jjTools.getSeassionUserId(request)));//برای استخراج نام و نام خانوادگی کاربری که در سشن فعال است
            script.append(Js.setVal("#form_ownerName", userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()));
            script.append(Js.setHtml("#formDiscription_buttons", "<div class='col-lg-6'><input type='button' id='insert_formDiscriptions_new'  value=\"" + lbl_insert + "\" class='btn btn-success btn-block mg-b-10'></div>"));
//            script.append(Js.click("#insert_formDiscriptions_new", Js.jjFormDiscriptions.insert()));
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
            Map<String, Object> map = new HashMap();
            map.put(_formId, jjTools.getParameter(request, _formId));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));
            map.put(_writerUserId, jjTools.getSeassionUserId(request));
            map.put(_date, Integer.valueOf(jjTools.getParameter(request, _date).toString().replaceAll("/", ""))); 
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_files, jjTools.getParameter(request, _files));
            List<Map<String, Object>> insertedِDiscriptionRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            StringBuilder script = new StringBuilder();
            if (insertedِDiscriptionRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
//            script.append(Js.jjFormDiscriptions.refresh(jjTools.getParameter(request, _formId).toString()));
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
                Server.outPrinter(request, response, Js.modal("شما اجازه ی دسترسی به این قسمت را ندارید", "انقضای جلسه"));
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (formRow.isEmpty()) {
                String errorMessage = "فرم مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
            script.append(Js.setVal("#" + tableName + "_id", formRow.get(0).get(_id).toString()));
            script.append(Js.setVal("#" + _formId, formRow.get(0).get(_formId).toString()));
            script.append(Js.setValSummerNote("#" + _htmlContent, formRow.get(0).get(_htmlContent).toString()));
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, _id + "=" + formRow.get(0).get(_writerUserId).toString()));//برای استخراج نام و نام خانوادگی کاربری که فرم را ایجاد کرده است
            script.append(Js.setVal("#form_ownerName", userRow.get(0).get(Access_User._name).toString() + " " + userRow.get(0).get(Access_User._family).toString()));
            script.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(formRow.get(0).get(_date).toString())));
            script.append(Js.setVal("#" + _title, formRow.get(0).get(_title).toString()));
    String attachFilesDocument = formRow.get(0).get(_files).toString();
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
                        html.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesDocumentArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesDocumentArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'  type='hidden'  value='" + attachFilesDocumentArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesDocumentArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _files + "'   type='hidden'  value='" + attachFilesDocumentArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);   
            if (accEdit) {
//                htmlBottons += "<div class='col-lg-6'><button title='" + lbl_edit + "' class='btn btn-warning btn-block mg-b-10' onclick='" + Js.jjFormDiscriptions.edit(id) + "' id='edit_form_new'>" + lbl_edit + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
//                htmlBottons += "<div class='col-lg-6'><button title='" + lbl_delete + "' class='btn btn-danger btn-block mg-b-10' onclick='" + Js.jjFormDiscriptions.delete(id) + "' id='edit_form_new'>" + lbl_delete + "</button></div>";
            }
            script.append(Js.setHtml("#formDiscription_buttons", htmlBottons));
            script.append(Js.setHtml("#showFormDiscriptionDownloadFiles", html));
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            Server.outPrinter(request, response, script.toString());
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
            map.put(_formId, jjTools.getParameter(request, _formId));
            map.put(_htmlContent, jjTools.getParameter(request, _htmlContent));   
            map.put(_date,Integer.valueOf(jjTools.getParameter(request, _date).toString().replaceAll("/", "")));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_files, jjTools.getParameter(request, _files));
            if (!db.update(tableName, map, _id + "=" + id)) {
                Server.outPrinter(request, response, Js.modal("ویرایش انجام نشد", "پیام سامانه"));
                return "";
            } else {
//                Server.outPrinter(request, response, Js.jjFormDiscriptions.refresh(jjTools.getParameter(request, _formId).toString()) + Js.modal("ویرایش بدرستی انجام شد", "پیام سامانه"));
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
                    Server.outPrinter(request, response, 
                                    Js.modal("تحلیل حذف شد", "پیام سامانه"));
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

}
