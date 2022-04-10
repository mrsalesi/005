/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
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
 * @author Mohammad
 */
public class FormQuestions {

    public static final String tableName = "hmis_formquestions";
    public static final String _id = "id";
    public static final String _formID = "formQuestions_formID";
    public static final String _title = "formQuestions_title";
    public static final String _defaultValue = "formQuestions_defaultValue";
    public static final String _placeHolder = "formQuestions_placeHolder";
    public static final String _isRequierd = "formQuestions_isRequierd";
    public static final String _icon = "formQuestions_icon";
    public static final String _weight = "formQuestions_weight";
    public static final String _answersType = "formQuestions_answersType";
    public static final String _htmlDiscription = "formQuestions_htmlDiscription";
    public static final String _trueAnswer = "formQuestions_trueAnswer";
    public static final String _htmlDiscriptionInResult = "formQuestions_htmlDiscriptionInResult";
    public static final String _preority = "formQuestions_preority";//ترتیب سوال
    public static final String _range = "formQuestions_range";//حیطه سوال

    public static int rul_rfs = 0;//60;
    public static int rul_rfs_own = 0;// 61;//فقط امکان دیدن فرم های ایجاد شده ی توسط خود ایجاد کننده را دارد // بر روی سمت
    public static int rul_ins = 0;// 62;
    public static int rul_edt = 0;// 63;
    public static int rul_dlt = 0;// 64;
    public static int rul_5 = 0;// 65;
    public static int rul_6 = 0;// 66;
    public static int rul_7 = 0;// 67;
    public static int rul_8 = 0;// 68;
    public static int rul_9 = 0;// 69;
    public static int rul_10 = 0;// 70;

    public static final String lbl_insert = "ثبت و افزودن سوال به فرم";
    public static final String lbl_delete = "حذف سوال";
    public static final String lbl_edit = "ثبت ویرایش سوال";
    public static final String lbl_copy = "کپی این سوال با گزینه های آن";
    public static final String lbl_add_en = "افزودن زبان انگلیسی";
    public static final String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static final String lbl_add_ar = "افزودن زبان عربی";
    public static final String lbl_edit_ar = "ویرایش بخش عربی";

//    public static int rul_lng5 = 68;
    public static final String lbl_add_ln2 = "برچسب";
    public static final String lbl_edit_ln2 = "ویرایش بخش انگلیسی";
    public static final String lbl_add_ln3 = "افزودن زبان عربی";
    public static final String lbl_edit_ln3 = "ویرایش بخش عربی";
    public static final String lbl_add_ln4 = "افزودن زبان آلمانی";
    public static final String lbl_edit_ln4 = "ویرایش بخش آلمانی";
    public static final String lbl_add_ln5 = "افزودن زبان چینی";
    public static final String lbl_edit_ln5 = "ویرایش بخش چینی";

    /**
     * این متد موقع سلکت یک فرم برای ویرایش سوالات یک فرم فراخوانی می شود یعنی
     * در hmisForms.m_selet();
     *
     * @param request آی دی فرم در پارامتر ها باید باشد
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();

            String formId = jjTools.getParameter(request, _formID);
            if (formId.isEmpty()) {
                return ""; //ToDo بعدا به صورت آلرت مناسب در بیاید 
            }
            DefaultTableModel dtm = db.Select(tableName, _formID + "=" + formId);//@ToDo فقط ستون هایی که لازم هست را بگیریم که در مصرف حاقظه رم سرفه جویی بشود
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            List<Map<String, Object>> formAnswerSetRow = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, FormAnswerSet._formId + "=" + formId));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                if (formAnswerSetRow.size() > 0) {
                    html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20  mg-b-5 tx-white' onclick='hmisFormQuestions.m_add_newAndAlert();' >افزودن سوال جدید</a>");
                } else {
                    html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20  mg-b-5 tx-white' onclick='hmisFormQuestions.m_add_new();' >افزودن سوال جدید</a>");
                }
            }
            html.append("<div style='width: 100%; padding-left: -10px;'>"
                    + "<div class='table-responsive'>");
            html.append("<table id='refreshFormQuestions' class='table table-striped table-hover dt-responsive display ' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th width='5%' class='c'>ترتیب سوال</th>");
            html.append("<th width='5%' class='c'>کد</th>");
            html.append("<th width='20%' class='c'>عنوان سوال</th>");
            html.append("<th width='20%' class='c'>ویرایش و اصلاح</th>");
            html.append("<th width='20%' class='c'>آنالیز و آمار</th>");

            html.append("<th width='20%' class='c'>حذف سوال</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer'>");
                html.append("<td class='c'>").append(row.get(i).get(_preority)).append("</td>");
                html.append("<td class='c'>").append(row.get(i).get(_id)).append("</td>");
                html.append("<td class='c'>").append(row.get(i).get(_title)).append("</td>");
                html.append("<td class='c p' onclick='").append(Js.jjFormQuestions.select(row.get(i).get(_id).toString())).append("'><i class='icon ion-ios-gear' onclick='hmisFormQuestions.m_select(").append(row.get(i).get(_id)).append(");' ></i></td>");
                html.append("<td class='c p'><a target='_blank' href='Server?do=FormAnswerSet.showAllResult&formAnswers_formId=" + row.get(i).get(_formID) + "'><i class='fa fa-bar-chart' style='color:#0866c6'></i></a></td>");
                boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
                if (accDelete) {
                    html.append("<td class='c p'><i  class='fa fa-trash' onclick='" + Js.jjFormQuestions.delete(row.get(i).get(_id).toString()) + "'   value='" + lbl_delete + "'></i></td>");
                } else {
                    html.append("<td><div></div></td>");
                }

                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            html.append("</div></div>");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swNewsTbl";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += Js.table("#refreshFormQuestions", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست اخبار");
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder script = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                script.append(Js.setHtml("#form_Question_buttons", "<div class='col-lg'><button onclick='" + Js.jjFormQuestions.insert() + "' value='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10'>" + lbl_insert + "</button>"));
                script.append("$('#formQuestions_preority').val(1*$('#formQuestions_preority').val()+1);");
            } else {
                script.append(Js.setHtml("#form_Question_buttons", ""));
            }
            script.append(Js.setVal("#hmis_formquestions_id", ""));
            script.append(Js.hide("#swFormQuestionOptionsForm"));
            script.append(Js.setHtml("#swFormQuestionOptionsTbl", ""));
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
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (formRow.isEmpty()) {
                String errorMessage = "فرم مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            StringBuilder script = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            script.append(Js.setVal("#" + tableName + "_id", formRow.get(0).get(_id).toString()));
            script.append(Js.setVal("#formQuestions_range1", formRow.get(0).get(_range).toString()));          
            script.append(Js.setVal("#"+_range, formRow.get(0).get(_range).toString()));
            script.append(Js.select2("#formQuestions_range", " width: '100%'"));   
            script.append(Js.setVal("#" + _title, formRow.get(0).get(_title).toString()));
            script.append(Js.setVal("#" + _icon, formRow.get(0).get(_icon).toString()));
            script.append(Js.setVal("#" + _preority, formRow.get(0).get(_preority).toString()));
            script.append(Js.setVal("#" + _answersType, formRow.get(0).get(_answersType).toString()));
            script.append(Js.setVal("#" + _trueAnswer, formRow.get(0).get(_trueAnswer).toString()));
            script.append(Js.removeClass(".formTypeSelector li.active", "active"));
            script.append(Js.addClass("#field_" + formRow.get(0).get(_answersType).toString(), "active"));
            if (formRow.get(0).get(_answersType).toString().equals("radio") || formRow.get(0).get(_answersType).toString().equals("select_option") || formRow.get(0).get(_answersType).toString().equals("checkbox")) {
                script.append(Js.hide("#defaultValueDiv"));//مقدار پیش فرض  مخفی شود
                script.append(Js.hide("#placeHolderDiv"));//placeHolderDivمخفی شود
                script.append(Js.hide("#swFormQuestionOptionsForm"));  
                script.append(Js.jjFormQuestionOptions.refresh(id));
            } else {// اگر از نوع فیلد های بدون گزینه بود جدول و فرم گزینه ها مخفی بشود
                script.append(Js.hide("#swFormQuestionOptionsTbl"));
                script.append(Js.show("#defaultValueDiv"));//
                script.append(Js.show("#placeHolderDiv"));//
                script.append(Js.hide("#swFormQuestionOptionsForm"));
                script.append(Js.setVal("#" + _placeHolder, formRow.get(0).get(_placeHolder).toString()));
                script.append(Js.setVal("#" + _defaultValue, formRow.get(0).get(_defaultValue).toString()));
            }
            //کاربر بعد از ثبت مشخصات فرم یاد سوالات فرم را یکی یکی یا دسته ای اضافه کند
            if (!formRow.get(0).get(_icon).toString().isEmpty()) {//اگر عکس داشت نشان بدهد
                script.append(Js.setAttr("#formQuestions_icon_Preview", "src", "upload/" + formRow.get(0).get(_icon).toString()));
            } else {
                script.append(Js.setAttr("#formQuestions_icon_Preview", "src", "img/preview.jpg"));

            }
            script.append(Js.setValSummerNote("#" + _htmlDiscription, formRow.get(0).get(_htmlDiscription).toString()));
            script.append(Js.setValSummerNote("#" + _htmlDiscriptionInResult, formRow.get(0).get(_htmlDiscriptionInResult).toString()));
            script.append(Js.setVal("#" + _isRequierd, formRow.get(0).get(_isRequierd).toString()));
            script.append(Js.setVal("#" + _weight, formRow.get(0).get(_weight).toString()));
            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button onclick='" + Js.jjFormQuestions.edit(id) + "' id='edit_Forms_new'  value='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10'>" + lbl_edit + "</button></div>";
            }
            boolean accInsert = Access_User.hasAccess(request, db, rul_ins);// برای کپی سوال باید اجازه ی ایجاد سوال را داشته باشد
            if (accInsert) {
                htmlBottons += "<div class='col-lg'><button onclick='" + Js.jjFormQuestions.copy(id) + "' id='edit_Forms_new'  value='" + lbl_copy + "' class='btn btn-outline-info btn-block mg-b-10'>" + lbl_copy + "</button></div>";
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button onclick='" + Js.jjFormQuestions.delete(id) + "' id='edit_Forms_new'  value='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10'>" + lbl_delete + "</button></div>";
            }
            script.append(Js.setHtml("#form_Question_buttons", htmlBottons));
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * برای کپی گرفتن از یک سوال با همه ی گزینه های آن
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
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            String numberForCopy = jjTools.getParameter(request, "formQuestions_numberForCopy");
            if (!jjNumber.isDigit(numberForCopy)) {
                numberForCopy = "1";
            }
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (questionRow.isEmpty()) {
                String errorMessage = "کد سوال صحیح نیست یا سوال مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            for (int j = 0; j < Integer.valueOf(numberForCopy); j++) {
                Map<String, Object> map = new HashMap();
                map.put(_formID, questionRow.get(0).get(_formID).toString());
                map.put(_title, "کپی " + questionRow.get(0).get(_title).toString());
                map.put(_icon, questionRow.get(0).get(_icon).toString());
                map.put(_placeHolder, questionRow.get(0).get(_placeHolder).toString());
                map.put(_answersType, questionRow.get(0).get(_answersType).toString());
                map.put(_trueAnswer, questionRow.get(0).get(_trueAnswer).toString());
                map.put(_answersType, questionRow.get(0).get(_answersType).toString());
                if (questionRow.get(0).get(_range).toString().equals("null") || questionRow.get(0).get(_range).toString().equals("") || questionRow.get(0).get(_range).toString().equals(null)) {
                    map.put(_range, "");
                } else {
                    map.put(_range, questionRow.get(0).get(_range).toString());
                }
                map.put(_answersType, questionRow.get(0).get(_answersType).toString());
                map.put(_defaultValue, questionRow.get(0).get(_defaultValue).toString());
                map.put(_htmlDiscription, questionRow.get(0).get(_htmlDiscription).toString());
                map.put(_htmlDiscriptionInResult, questionRow.get(0).get(_htmlDiscriptionInResult).toString());
                map.put(_isRequierd, questionRow.get(0).get(_isRequierd).toString());
                map.put(_preority, questionRow.get(0).get(_preority).toString());
                map.put(_weight, questionRow.get(0).get(_weight).toString());
                List<Map<String, Object>> insertedQuestionRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
                if (insertedQuestionRow.isEmpty()) {
                    String errorMessage = "عملیات کپی به درستی صورت نگرفت.";
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
                List<Map<String, Object>> questionOptionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + id));
                for (int i = 0; i < questionOptionRows.size(); i++) {
                    System.out.println("Copy --->");
                    Map<String, Object> map2 = new HashMap();
                    map2.put(FormQuestionOptions._lable, questionOptionRows.get(i).get(FormQuestionOptions._lable));
                    map2.put(FormQuestionOptions._icon, questionOptionRows.get(i).get(FormQuestionOptions._icon));
                    map2.put(FormQuestionOptions._question_id, insertedQuestionRow.get(0).get(_id));//آی دی سوالی که همین الآن اینسرت شده در جدول سوال ها
                    map2.put(FormQuestionOptions._value, questionOptionRows.get(i).get(FormQuestionOptions._value));
                    db.insert(FormQuestionOptions.tableName, map2);
                }
            }
            StringBuilder script = new StringBuilder();
            script.append(Js.jjFormQuestions.refresh(jjTools.getParameter(request, _formID)));
            script.append(Js.jjForms.select(jjTools.getParameter(request, _formID)));
            script.append(Js.jjFormQuestions.showTbl());
            script.append(Js.modal("سوال با گزینه ها کپی شد", "پیام سامانه"));
//            String errorMessage = "سوال با کد " + insertedQuestionRow.get(0).get(_id) + " کپی شد" + " تعداد گزینه ها " + questionOptionRows.size();
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            String errorMessage = "خطا در کپی سوال یا گزینه های ان رخ داده  " + "خطای شماره ی 6895";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            ex.printStackTrace();
            return "";
        }
    }

    public static String copyQuestionInform(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (questionRow.isEmpty()) {
                String errorMessage = "کد سوال صحیح نیست یا سوال مورد نظر یافت نشد";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Map<String, Object> map = new HashMap();
            map.put(_formID, jjTools.getParameter(request, _formID));
            map.put(_title, questionRow.get(0).get(_title).toString());
            map.put(_icon, questionRow.get(0).get(_icon).toString());
            map.put(_placeHolder, questionRow.get(0).get(_placeHolder).toString());
            if (questionRow.get(0).get(_range).equals("null") || questionRow.get(0).get(_range).equals("") || questionRow.get(0).get(_range).equals(null)) {
                map.put(_range, "");
            } else {
                map.put(_range, questionRow.get(0).get(_range).toString());
            }
            map.put(_answersType, questionRow.get(0).get(_answersType).toString());
            map.put(_trueAnswer, questionRow.get(0).get(_trueAnswer).toString());
            map.put(_answersType, questionRow.get(0).get(_answersType).toString());
            map.put(_defaultValue, questionRow.get(0).get(_defaultValue).toString());
            map.put(_htmlDiscription, questionRow.get(0).get(_htmlDiscription).toString());
            map.put(_htmlDiscriptionInResult, questionRow.get(0).get(_htmlDiscriptionInResult).toString());
            map.put(_isRequierd, questionRow.get(0).get(_isRequierd).toString());
            map.put(_weight, questionRow.get(0).get(_weight).toString());
            List<Map<String, Object>> insertedQuestionRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            if (insertedQuestionRow.isEmpty()) {
                String errorMessage = "عملیات کپی به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> questionOptionRows = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + id));
            for (int i = 0; i < questionOptionRows.size(); i++) {
                System.out.println("Copy --->");
                Map<String, Object> map2 = new HashMap();
                map2.put(FormQuestionOptions._lable, questionOptionRows.get(i).get(FormQuestionOptions._lable));
                map2.put(FormQuestionOptions._icon, questionOptionRows.get(i).get(FormQuestionOptions._icon));
                map2.put(FormQuestionOptions._question_id, insertedQuestionRow.get(0).get(_id));//آی دی سوالی که همین الآن اینسرت شده در جدول سوال ها
                map2.put(FormQuestionOptions._value, questionOptionRows.get(i).get(FormQuestionOptions._value));
                db.insert(FormQuestionOptions.tableName, map2);
            }
            StringBuilder script = new StringBuilder();
            script.append(Js.jjFormQuestions.refresh(insertedQuestionRow.get(0).get(_formID).toString()));
            script.append(Js.jjFormQuestions.showTbl());
            String errorMessage = "سوال با کد " + insertedQuestionRow.get(0).get(_id) + " کپی شد" + " تعداد گزینه ها " + questionOptionRows.size();
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه") + script);
            return "";
        } catch (Exception ex) {
            String errorMessage = "خطا در کپی سوال یا گزینه های ان رخ داده  " + "خطای شماره ی 6895";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            ex.printStackTrace();
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
            Map<String, Object> map = new HashMap<>();
            map.put(_isRequierd, jjTools.getParameter(request, _isRequierd));
            if (jjTools.getParameter(request, _range).equals("null") || jjTools.getParameter(request, _range).equals("") || jjTools.getParameter(request, _range).equals(null)) {
                map.put(_range, "");
            } else {
                map.put(_range, jjTools.getParameter(request, _range));
            }
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_weight, jjTools.getParameter(request, _weight).isEmpty() ? "0" : jjTools.getParameter(request, _weight));
            map.put(_placeHolder, jjTools.getParameter(request, _placeHolder));
            map.put(_preority, jjTools.getParameter(request, _preority));
            map.put(_defaultValue, jjTools.getParameter(request, _defaultValue));
            map.put(_answersType, "text"); // برای ثبت اولیه مقدار تکس باشد زمان ویرایش بتواند نوع آنرا عوض کند
            map.put(_trueAnswer, jjTools.getParameter(request, _trueAnswer));
            map.put(_htmlDiscription, jjTools.getParameter(request, _htmlDiscription));
            map.put(_htmlDiscriptionInResult, jjTools.getParameter(request, _htmlDiscriptionInResult));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_formID, jjTools.getParameter(request, _formID));
            List<Map<String, Object>> insertedQuestionRow = jjDatabaseWeb.separateRow(db.insert(tableName, map));
            StringBuilder script = new StringBuilder();
            if (insertedQuestionRow.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            // اگر سوال چند گزینه ای باشد فرم بسته نشود و از کاربر بخواهیم گزینه ها را وارد کند
            if (insertedQuestionRow.get(0).get(_answersType).equals("radio")
                    || insertedQuestionRow.get(0).get(_answersType).equals("select_option")
                    || insertedQuestionRow.get(0).get(_answersType).equals("checkbox")) {
                script.append(Js.jjForms.select(jjTools.getParameter(request, _formID)));
                script.append(Js.jjFormQuestions.select(insertedQuestionRow.get(0).get(_id).toString()));
                script.append(Js.modal("گزینه های سوال را اضافه کنید", "پیام سامانه"));
            } else {
                // اگر سوال چند گزینه ای باشد فرم بسته نشود و از کاربر بخواهیم گزینه ها را وارد کند
                script.append(Js.jjFormQuestions.refresh(jjTools.getParameter(request, _formID)));
                script.append(Js.jjForms.select(jjTools.getParameter(request, _formID)));
                script.append(Js.jjFormQuestions.select(insertedQuestionRow.get(0).get(_id).toString()));
//                script.append(Js.jjFormQuestions.showTbl());
//                script.append(Js.modal("سوال ثبت شد", "پیام سامانه"));
            }
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
            if (id.isEmpty() || !jjNumber.isDigit(id)) {
                Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
                return "";
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            if (jjTools.getParameter(request, _range).equals("null") || jjTools.getParameter(request, _range).equals("") || jjTools.getParameter(request, _range).equals(null)) {
                map.put(_range, "");
            } else {
                map.put(_range, jjTools.getParameter(request, _range));
            }
            map.put(_isRequierd, jjTools.getParameter(request, _isRequierd));
            map.put(_icon, jjTools.getParameter(request, _icon));
            map.put(_weight, jjTools.getParameter(request, _weight).isEmpty() ? "0" : jjTools.getParameter(request, _weight));
            map.put(_trueAnswer, jjTools.getParameter(request, _trueAnswer));
            map.put(_htmlDiscriptionInResult, jjTools.getParameter(request, _htmlDiscriptionInResult));
            map.put(_htmlDiscription, jjTools.getParameter(request, _htmlDiscription));
            map.put(_defaultValue, jjTools.getParameter(request, _defaultValue));
            map.put(_placeHolder, jjTools.getParameter(request, _placeHolder));
            map.put(_preority, jjTools.getParameter(request, _preority));
//            map.put(_formID, jjTools.getParameter(request, _formID));نباید تغییر کند قاعدتا ولی بگذاریم هم طوری نمی شود
            StringBuilder script = new StringBuilder();
            // اگر قبلا حداقل یک بار تکمیل شده بود احتمالا  در شرابط خاصی میتواند نوع سوال را ویرایش کند
            List<Map<String, Object>> answeredToThisQuestionRow = jjDatabaseWeb.separateRow(db.Select(FormAnswers.tableName, FormAnswers._questionId + "=" + id));
            System.out.println("questionRow.size()" + answeredToThisQuestionRow.size());
            if (answeredToThisQuestionRow.size() > 0) {
                List<Map<String, Object>> questionRow = jjDatabaseWeb.separateRow(db.Select(tableName, "id=" + id));
                if ((questionRow.get(0).get(_answersType).equals("radio") || questionRow.get(0).get(_answersType).equals("select_option"))
                        && (jjTools.getParameter(request, _answersType).equals("radio") || jjTools.getParameter(request, _answersType).equals("select_option"))) {
                    map.put(_answersType, jjTools.getParameter(request, _answersType));
                    script.append(Js.modal("امکان ویرایش نوع این سوال وجود ندارد, توجه : فقط در مواردی که سوال از نوع رادیو باشد به سلکت آپشن قابل تبدیل است و بالعکس", "قبلا افرادی این فرم را تکمیل کرده اند"));
                }
            } else {
                map.put(_answersType, jjTools.getParameter(request, _answersType));
            }
            if (db.update(tableName, map, _id + "=" + id)) {
                script.append(Js.jjFormQuestions.refresh(jjTools.getParameter(request, _formID)));
                script.append(Js.jjFormQuestions.showTbl());
                script.append(Js.jjForms.select(jjTools.getParameter(request, _formID)));
                Server.outPrinter(request, response,
                        //                        Js.modal("ویرایش بدرستی انجام شد", "پیام سامانه") + 
                        script);
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
            //@ToDoدر ورژن استاندارد سوالاتی که پاسخ داده شنده اند را نمیتوانیم حذف کنیم 
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";

            } else {
                String id = jjTools.getParameter(request, _id);
                if (!jjNumber.isDigit(id)) {
                    Server.outPrinter(request, response, Js.modal("کد صحیح نیست", "پیام سامانه"));
                    return "";
                }
                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
//                db.Select(tableName)//در پاسخ ها اگر کسی پاسخ نداده است قابل حذف است@ToDo
                if (db.otherStatement("DELETE t1,t2 FROM " + tableName + " AS t1 LEFT JOIN " + FormQuestionOptions.tableName + " AS t2 ON formQuestionOptions_question_id = t1.id WHERE t1.id=" + id)) {
                    Server.outPrinter(request, response, Js.jjFormQuestions.refresh(row.get(0).get(_formID).toString()) + Js.modal("همه گزینه های این سوال هم حذف شدند", "پیام سامانه"));
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
     * این متد سوال هایی از یک فرم را بر می گرداند که مقدار عددی داشته باشند و
     * انتخاب یکی از آنها الزامی است و تهی ندارد این تابع برای شاخص ها کاربرد
     * دارد و دقت کنیم میانگین برای فرم نداریم و درصد پاسخگویی داریم
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
    public static String getNumericalQuestionAsOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            String formId = jjTools.getParameter(request, _formID);// اگر نباشد تهی برمیگرداند
            if (needString) {// برای زمانی که آپشن ها را فقط بصورت اچ تی ام ال برای یک کلاس جاوایی لازم داریم
                formId = jjTools.getAttribute(request, _formID);// اگر درخواست از کلاینت باشد وارد این شرط نمی شود
            }
            if (jjNumber.isDigit(formId)) {
                List<Map<String, Object>> rowQuestion = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "," + _title,
                        _formID + "=" + formId
                        + " AND (" + _answersType + "='number' OR "
                        + _answersType + "='radio' OR "
                        + _answersType + "='checkbox' OR "
                        + _answersType + "='select_option' )", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
                optionHtml.append("<option  value='sumOfForm_" + formId + "'>جمع امتیازات این فرم</option>");
                optionHtml.append("<option  value='avgOfForm_" + formId + "'>میانگین درصد پاسخگویی این فرم</option>");
                for (int i = 0; i < rowQuestion.size(); i++) {
                    optionHtml.append("<option  value='avg_").append(rowQuestion.get(i).get(_id)).append("'>-میانگین بازه ای-").append(rowQuestion.get(i).get(_title)).append("</option>");
                    optionHtml.append("<option  value='sum_").append(rowQuestion.get(i).get(_id)).append("'>-مجموع بازه ای-").append(rowQuestion.get(i).get(_title)).append("</option>");
                }
                if (needString) {
                    return optionHtml.toString();
                }
                String panel = jjTools.getParameter(request, "panel");
                if (panel.isEmpty()) {
                    panel = "#hmis_indicatiors_a_forms";
                }
                Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            }
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getQuestion(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            String formId = jjTools.getParameter(request, _formID);// اگر نباشد تهی برمیگرداند
            if (needString) {// برای زمانی که آپشن ها را فقط بصورت اچ تی ام ال برای یک کلاس جاوایی لازم داریم
                formId = jjTools.getAttribute(request, _formID);// اگر درخواست از کلاینت باشد وارد این شرط نمی شود
            }
            List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, _id + "," + _title,
                    FormQuestions._formID + "=" + formId
                    + " AND (" + _answersType + "='number' OR "
                    + _answersType + "='radio' OR "
                    + _answersType + "='checkbox' OR "
                    + _answersType + "='select_option' )", _title));
            if (jjNumber.isDigit(formId)) {
                for (int i = 0; i < questionsRow.size(); i++) {
                    optionHtml.append("<option  value='").append(questionsRow.get(i).get(_id)).append("'>").append(questionsRow.get(i).get(_title)).append("</option>");
                }

                if (needString) {
                    return optionHtml.toString();
                }
                String panel = jjTools.getParameter(request, "panel");
                if (panel.isEmpty()) {
                    panel = "#hmis_indicatiors_a_forms";
                }
                Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            }
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * محدودیت تعداد سوال در شاخص ها
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getQuestionLimit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            String formId = jjTools.getParameter(request, _formID);// اگر نباشد تهی برمیگرداند
            if (needString) {// برای زمانی که آپشن ها را فقط بصورت اچ تی ام ال برای یک کلاس جاوایی لازم داریم
                formId = jjTools.getAttribute(request, _formID);// اگر درخواست از کلاینت باشد وارد این شرط نمی شود
            }
            List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, _id + "," + _title,
                    FormQuestions._formID + "=" + formId
                    + " AND (" + _answersType + "='number' OR "
                    + _answersType + "='radio' OR "
                    + _answersType + "='checkbox' OR "
                    + _answersType + "='select_option' )", _title + " LIMIT 3 "));
            if (jjNumber.isDigit(formId)) {
                for (int i = 0; i < questionsRow.size(); i++) {
                    optionHtml.append("<option  value='").append(questionsRow.get(i).get(_id)).append("'>").append(questionsRow.get(i).get(_title)).append("</option>");
                }

                if (needString) {
                    return optionHtml.toString();
                }
                String panel = jjTools.getParameter(request, "panel");
                if (panel.isEmpty()) {
                    panel = "#hmis_indicatiors_a_forms";
                }
                Server.outPrinter(request, response, Js.setHtml(panel, optionHtml) + Js.select2(panel, ""));
            }
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getHtmlFilterQuestion(String formId, jjDatabaseWeb db) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, _id + "," + _title,
                    FormQuestions._formID + "=" + formId
                    + " AND (" + _answersType + "='number' OR "
                    + _answersType + "='radio' OR "
                    + _answersType + "='checkbox' OR "
                    + _answersType + "='select_option' )", _title));
            if (jjNumber.isDigit(formId)) {
                for (int i = 0; i < questionsRow.size(); i++) {
                    optionHtml.append("<option  value='").append(questionsRow.get(i).get(_id)).append("'>").append(questionsRow.get(i).get(_title)).append("</option>");
                }
            }
            return optionHtml.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getHtmlNumericalQuestionAsOption(String formId, jjDatabaseWeb db) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            formId.replaceAll("_sum", "");
            formId.replaceAll("_avg", "");
            if (jjNumber.isDigit(formId)) {
                List<Map<String, Object>> rowQuestion = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "," + _title,
                        _formID + "=" + formId
                        + " AND (" + _answersType + "='number' OR "
                        + _answersType + "='radio' OR "
                        + _answersType + "='checkbox' OR "
                        + _answersType + "='select_option' )", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
                optionHtml.append("<option  value='sumOfForm_" + formId + "'>مجموع امتیازات این فرم</option>");
                optionHtml.append("<option  value='avgOfForm_" + formId + "'>میانگین امتیازات این فرم</option>");
                for (int i = 0; i < rowQuestion.size(); i++) {
                    optionHtml.append("<option  value='avg_").append(rowQuestion.get(i).get(_id)).append("'>").append(rowQuestion.get(i).get(_title) + "-میانگین بازه ای-").append("</option>");
                    optionHtml.append("<option  value='sum_").append(rowQuestion.get(i).get(_id)).append("'>").append(rowQuestion.get(i).get(_title) + "-مجموع بازه ای-").append("</option>");
                }
            }
            return optionHtml.toString();

        } catch (Exception e) {

            return "";
        }
    }

    /**
     * گرفتن رنج سوال حیطه سوال
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getoptionRangeFormQuestion(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            String formID = jjTools.getParameter(request, "formQuestions_formId");
            System.out.println("formID=" + formID);
            html.append("<option  style='color:black' value=''>انتخاب حیطه سوال</option>");
            DefaultTableModel dtm;
            if (jjNumber.isDigit(formID)) {
                dtm = db.SelectDistinct(FormQuestions.tableName, FormQuestions._range, FormQuestions._formID + "=" + formID);
            } else {
                dtm = db.SelectDistinct(FormQuestions.tableName, FormQuestions._range);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            for (int i = row.size() - 1; i >= 0; i--) {

                html.append("<option value='" + row.get(i).get(_range).toString() + "'"
                        + (row.get(i).get(_range).toString().equals(_range) ? " selected='selected'>" : ">")
                        + row.get(i).get(_range).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)

            }
            if (panel == "") {
                panel = "formQuestions_range";
            }
            script += Js.setHtml(panel, html.toString());

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
}
