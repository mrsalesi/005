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
 * @author mahdi
 */
public class FocusSPdca {

    public static String tableName = "pdca";
    public static String _id = "id";
    public static String _html = "pdca_html";//مقدمه
    public static String _subject = "pdca_subject";

    public static int rul_focuseModule = 580;
    public static int rul_rfs = 581;
    public static int rul_ins = 582;
    public static int rul_edt = 583;
    public static int rul_dlt = 584;

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")){
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("<div class=\"card-header bg-primary tx-white\">لیست مدل بهبود کیفیت</div>\n");

            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class=\"card-body pd-sm-30\">\n"
                        + "<p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + "<a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisPdca.m_add_new();\" >مدل بهبود کیفیت جدید</a>\n"
                        + "</p>\n"
                        + "</div>");
            }
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshPdca' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='r' width='20%'>موضوع</th>");
            html.append("<th class='c' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_subject) + "</td>");
                html.append("<td class='c' style='cursor:pointer;'><i class='icon ion-ios-gear-outline'  onclick='hmisPdca.m_select(" + row.get(i).get(_id) + ")'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div>");
            String height = cms.tools.jjTools.getParameter(request, "height");
            String panel = cms.tools.jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swPdcaTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());

            html2 += Js.table("#refreshPdca", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "0" : "", "لیست بهبود کیفیت");

            Server.outPrinter(request, response, html2);
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
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_html, jjTools.getParameter(request, "html"));
            map.put(_subject, jjTools.getParameter(request, "subject"));
            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {

                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (cms.tools.jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }

            Server.outPrinter(request, response, Js.jjPdca.refresh());
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

                html.append(Js.setHtml("#pdca_insert_btn", "<button  id=\"insert_pdca_new\"  class=\"btn btn-outline-success  btn-block mg-t-20\" onclick=\"" + Js.jjPdca.insert() + "\">" + lbl_insert + "</button>"));
            }
            String script = html.toString();
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);

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
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setHtml("#FMEATableOne1", row.get(0).get(_html).toString()));
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//

            if (accEdt) {

                html2.append("<button  id='edit_pdca'  class='btn btn-outline-warning btn-block mg-t-20' onclick='" + Js.jjPdca.edit() + "'>" + lbl_edit + "</button>");

            }
            if (accDel) {

                html3.append("<button id='delete_pdca' class='btn btn-outline-danger btn-block mg-t-20' onclick='" + Js.jjPdca.delete(id) + "' >" + lbl_delete + " </button>");

            }

            String script = Js.setHtml("#pdca_edit_btn", html2);
            script += Js.setHtml("#pdca_delete_btn", html3);
            script += html.toString();
            script += "   new jj('#sendfile1').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile1', '', 'pdca_titleFile1', \"#showfiles1div\");\n"
                    + "            new jj('#sendfile2').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile2', '', 'pdca_titleFile2', \"#showfiles2div\");\n"
                    + "            new jj('#sendfile3').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile3', '', 'pdca_titleFile3', \"#showfiles3div\");\n"
                    + "            new jj('#sendfile4').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile4', '', 'pdca_titleFile4', \"#showfiles4div\");";
            script += "$('.form-control').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });";
            script += "    $('.isok').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 0; i <= 5; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر \n"
                    + "        $(avgInput).attr('value', sum);\n"
                    + "    });";
            script += " $('.isoksort').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 1; i <= 4; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\".sum\");// خانه آخر \n"
                    + "        $(avgInput).html(sum);\n"
                    + "    });";
            script += "$('.onkeyup').keyup(function () {\n"
                    + "                $(this).text($(this).val());\n"
                    + "            });";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_html, jjTools.getParameter(request, "htmledit"));
            map.put(_subject, jjTools.getParameter(request, "subject"));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjPdca.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "id");
            System.out.println("id=" + id);

            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjPdca.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

}
