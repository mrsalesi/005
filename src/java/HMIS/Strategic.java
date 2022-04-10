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
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shohreh.shiran Date 1397.11.1
 */
public class Strategic {

    public static String tableName = "hmis_strategic";
    public static String _id = "id";
    public static String _introduction = "strategic_introduction";//مقدمه
    public static String _prophecy = "strategic_prophecy";//رسالت
    public static String _prospect = "strategic_prospect";//چشم انداز /دور نما
    public static String _IFEinternal = "strategic_IFEInternal";//ارزیابی داخلی 
    public static String _EFEOuter = "strategic_EFEOuter";//ارزیابی خارجی
    public static String _IFEandEFE = "strategic_IFEandEFE";//جدول ارزیابی داخلی وخارجی
    public static String _beneficiaries = "strategic_beneficiaries";//ذی نفعان
    public static String _matrixSWOT = "strategic_matrixSWOT";//ماتریس 
    public static String _matrixQSPM = "strategic_matrixQSPM";//ماتریس 
    public static String _strategicSWOT = "strategic_strategicSWOT";//استراتژیک swot 
    public static String _IssuesStrategic = "strategic_issuesStrategic";//مسائل استراتژیک 
    public static String _title = "strategic_title";//عنوان استراتژیک 
    public static String _description = "strategic_description";//توضیحات استراتژیک 
    public static String _prioritized = "strategic_prioritized";//اولویت بندی شده
    public static int rul_rfs = 340;
    public static int rul_ins = 341;
    public static int rul_edt = 342;
    public static int rul_dlt = 343;
    public static int rul_prt = 344;
//    public static int rul_rfsAll = 21;
//    public static int rul_rfs = 22;
//    public static int rul_ins = 23;
//    public static int rul_edt = 24;
//    public static int rul_dlt = 25;

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

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
//               boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//               boolean accRef= Access_User.hasAccess(request, db, rul_rfs);
            StringBuilder html = new StringBuilder();

            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("<div class=\"sh-breadcrumb\">\n"
                    + "            <nav class=\"breadcrumb\">\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">مدیار</a>\n"
                    + "                <a class=\"breadcrumb-item\" href=\"http://medyarweb.ir\">MedYar</a>\n"
                    + "                <span class=\"breadcrumb-item active\">برنامه استراتژیک</span>\n"
                    + "            </nav>\n"
                    + "        </div>"
                    + "<div class=\"card-header bg-primary tx-white\">لیست برنامه های استراتژیک</div>\n");
            html.append(""
                                        + "<div class='col-lg-12'>"

                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisStrategic.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");
            html.append("<div class=\"card-body pd-sm-30\">");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append("<p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + "<a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisStrategic.m_add_new();\" >برنامه استراتژیک جدید</a>\n"
                        + "</p>\n");
            }
            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshStrategic' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th class='r' width='20%'>کد</th>");
            html.append("<th class='r' width='70%'>عنوان</th>");
            html.append("<th class='r' width='10%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='p'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisStrategic.m_select(" + row.get(i).get(_id) + ")'></i></td>");
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
                panel = "swStrategicTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());

            html2 += Js.table("#refreshStrategic", "300", 0, "", "کمیته ها");
//          

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
    public static String insertStrategy(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            jjTools.ShowAllParameter(request);
            StringBuilder html = new StringBuilder();
            String script = "";
            jjCalendar_IR ir = new jjCalendar_IR();
//            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> mapStrategy = new HashMap<String, Object>();
            String[] trStrategy = jjTools.getParameter(request, "trStrategy").toString().split(",");

//            if (!db.update(tableName, map, _id + "=" + id)) {
//                String errorMessage = "عملیات ثبت استراتژی به درستی صورت نگرفت.";
//                script += Js.modal(errorMessage, "پیام سامانه");
//            } else {
            for (int i = 0; i < trStrategy.length; i++) {
                List<Map<String, Object>> proprietaryRow = jjDatabase.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + jjTools.getParameter(request, "proprietaryTargetSelect")));
                String[] parametersStrategy = trStrategy[i].replaceAll("#A#", "%23A%23").split("%23A%23");
                mapStrategy.put(Strategy._strategicId, Integer.valueOf(jjTools.getParameter(request, _id)));
                mapStrategy.put(Strategy._points, parametersStrategy[1]);
                mapStrategy.put(Strategy._title, parametersStrategy[0]);
                if (!parametersStrategy[2].equals("")) {
                    mapStrategy.put(Strategy._indicatorUrl, parametersStrategy[2]);
                }
                mapStrategy.put(Strategy._proprietaryTargetId, jjTools.getParameter(request, "proprietaryTargetSelect"));
//                mapStrategy.put(Strategy._totalTargetId, proprietaryRow.get(0).get(ProprietaryTarget._totalTargetId));
                db.insert(Strategy.tableName, mapStrategy);
            }

//            script += Js.jjStrategic.refresh();
            script += Js.jjStrategic.select(id);
//            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ثبت موقت
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String temporaryInsert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            jjTools.ShowAllParameter(request);
            StringBuilder html = new StringBuilder();
            String script = "";
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> mapStrategy = new HashMap<String, Object>();
            map.put(_EFEOuter, jjTools.getParameter(request, _EFEOuter));
            map.put(_IFEandEFE, jjTools.getParameter(request, _IFEandEFE));
            map.put(_IFEinternal, jjTools.getParameter(request, _IFEinternal));
            map.put(_strategicSWOT, jjTools.getParameter(request, _strategicSWOT));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_introduction, jjTools.getParameter(request, _introduction));
            map.put(_IssuesStrategic, jjTools.getParameter(request, _IssuesStrategic));
            map.put(_matrixSWOT, jjTools.getParameter(request, _matrixSWOT));
            map.put(_prophecy, jjTools.getParameter(request, _prophecy));
            map.put(_prospect, jjTools.getParameter(request, _prospect));
            map.put(_beneficiaries, jjTools.getParameter(request, "strategic_beneficiaries"));
            String[] trStrategy = jjTools.getParameter(request, "trStrategy").toString().split(",");
            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                script += Js.modal(errorMessage, "پیام سامانه");
            } else {
                List<Map<String, Object>> StrategicRow = jjDatabaseWeb.separateRow(dtm);
//                script += Js.jjStrategic.refresh();
                script += Js.jjStrategic.select(StrategicRow.get(0).get(_id).toString());
                script += "$('#strategicStep2').slideDown();";
                script += " hmisStrategic.addRowTableQSPM1();";
            }
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
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Strategic_button", "<button  id=\"temporaryInsert_Strategic_new\"  class=\"btn btn-outline-success  btn-block \" onclick='hmisStrategic.temporaryInsert();'>ثبت موقت</button>"));
            }

            String script = html.toString();
            script += "$('#BeneficiariesTbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                    $(this).attr('value', $(this).val());\n"
                    + "                });\n"
                    + "     $('#tablePrioritizedDiv #tablePrioritized td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                $('#BeneficiariesTbl td input.onclick').click(function () {\n"
                    + "                    $(this).attr('value', $(this).val());\n"
                    + "                });\n"
                    + "                $('#BeneficiariesTbl #calculateBeneficiaries').click(function () {\n"
                    + "                    hmisStrategic.calculateBeneficiaries('BeneficiariesTbl');\n"
                    + "                });\n"
                    + "  $('#Beneficiaries2Tbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries2Tbl td input.onclick').click(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries2Tbl #calculateBeneficiaries2').click(function () {\n"
                    + "                        hmisStrategic.calculateBeneficiaries2('Beneficiaries2Tbl');\n"
                    + "                    });"
                    + "   $('#Beneficiaries3Tbl  tr.rowBeneficiaries3 td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries3Tbl td #addRowBeneficiaries3').click(function () {\n"
                    + "                        hmisStrategic.addRowBeneficiaries3('Beneficiaries3');\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries3Tbl td #removeRowBeneficiaries3').click(function () {\n"
                    + "                        hmisStrategic.removeRowBeneficiaries3('Beneficiaries3');\n"
                    + "                    });\n"
                    + "    $('#Beneficiaries4Tbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries4Tbl td #addRowBeneficiaries4').click(function () {\n"
                    + "                        hmisStrategic.addRowBeneficiaries3('Beneficiaries4');\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries4Tbl td #removeRowBeneficiaries4').click(function () {\n"
                    + "                        hmisStrategic.removeRowBeneficiaries3('Beneficiaries4');\n"
                    + "                    });"
                    + "$('#tblBen  tr.rowBene td textarea.onkeyup').keyup(function () {\n"
                    + "                    $(this).text($(this).val());\n"
                    + "                });"
                    + "\n"
                    + "\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td textarea.onkeyup').keyup(function () {\n"
                    + "                        $(this).text( $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeyup').click(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeypress').click(function () {\n"
                    + "                        var weight = $(this).parent().parent().find('.weightPercent').attr('value');\n"
                    + "                        if (weight != 0) {\n"
                    + "                            hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);\n"
                    + "                        }\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeypress').keyup(function () {\n"
                    + "                        var weight = $(this).parent().parent().find('.weightPercent').attr('value');\n"
                    + "                        if (weight != 0) {\n"
                    + "                            hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);\n"
                    + "                        }\n"
                    + "                    });\n"
                    + "\n"
                    + "                    $('#weightTbl #calculateWeight').click(function () {\n"
                    + "                        hmisStrategic.normalizeTable2('tableWeight');\n"
                    + "\n"
                    + "                    });"
                    + "       $('#IFETbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "\n"
                    + "                        });\n"
                    + "                        $('#IFETbl td input.onclick').click(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#IFETbl #calculeteIFE').click(function () {\n"
                    + "                            hmisStrategic.normalizeTable('tableIFE');\n"
                    + "                        });"
                    + "     $('#EFETbl  td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#EFETbl  td input.onclick').click(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#EFETbl #calculeteEFE').click(function () {\n"
                    + "                            hmisStrategic.normalizeTable('tableEFE');\n"
                    + "                        });"
                    + "                      ";

//            
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
            StringBuilder html4 = new StringBuilder();
            StringBuilder html5 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setHtml("#beneficiaries", row.get(0).get(_beneficiaries).toString()));
            html.append(Js.setHtml("#tableIFE", row.get(0).get(_IFEinternal).toString()));
            html.append(Js.setHtml("#tableEFE", row.get(0).get(_EFEOuter).toString()));
            html.append(Js.setHtml("#IFEandEFE", row.get(0).get(_IFEandEFE).toString()));
            html.append(Js.setHtml("#SWOTMatrixTable", row.get(0).get(_matrixSWOT).toString()));
            html.append(Js.setHtml("#StartegiesTableOnSWOT", row.get(0).get(_strategicSWOT)));
            html.append(Js.setVal("#" + _prophecy, row.get(0).get(_prophecy)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _prospect, row.get(0).get(_prospect)));
            html.append(Js.setVal("#" + _IssuesStrategic, row.get(0).get(_IssuesStrategic)));
            html.append(Js.setVal("#" + _introduction, row.get(0).get(_introduction)));
//     
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button  id='edit_Strategic'  class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjStrategic.edit() + "'>" + lbl_edit + "</button>");
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button id='delete_Strategic' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjStrategic.delete(id) + "' >" + lbl_delete + " </button>");
                html2.append("</div>");
            }
            html5.append("<div class=\"col-lg\">");
            html5.append("<button  id=\"insert_Strategic_new\"  class=\"btn btn-outline-success  btn-block \" onclick='" + Js.jjStrategic.insert() + "'>ثبت استراتژی ها</button>");
            html5.append("</div>");

            html5.append("</div>");

            //////////////////////////جدول اهداف
            List<Map<String, Object>> targetRow = jjDatabaseWeb.separateRow(db.Select(TotalTarget.tableName, TotalTarget._strategicId + "=" + row.get(0).get(_id)));
            html3.append(" <div class=\"card-body pd-sm-30\">\n"
                    + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisTotalTarget.m_add_new();\" >هدف جدید</a>\n"
                    + "                                        </p>\n"
                    + "                                    </div>");
            html3.append("        <div class=\"card-header bg-primary tx-white\">اهداف کلی</div>\n");
            html3.append("        <div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshStrategic' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th class='c' width='10%'>کد</th>");
            html3.append("<th class='c' width='50%'>عنوان هدف کلی</th>");
            html3.append("<th class='r' width='50%'>عملیات</th>");
            html3.append("</thead><tbody>");
            for (int i = 0; i < targetRow.size(); i++) {
                html3.append("<tr  class='mousePointer'>");
                html3.append("<td class='c'>" + targetRow.get(i).get(TotalTarget._id) + "</td>");
                html3.append("<td class='c'>" + targetRow.get(i).get(TotalTarget._title) + "</td>");
                html3.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisTotalTarget.m_select(" + targetRow.get(i).get(TotalTarget._id) + ");'></i></td>");
                html3.append("</tr>");
            }
            html3.append("</tbody></table>");
            html3.append("</div>");
            /////////////////////////////strategy
            List<Map<String, Object>> startegyRow = jjDatabaseWeb.separateRow(db.Select(Strategy.tableName, Strategy._strategicId + "=" + row.get(0).get(_id)));
            html4.append(" <div class=\"card-body pd-sm-30\">\n"
                    + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisStrategy.m_add_new();\" >استراتژی جدید</a>\n"
                    + "                                        </p>\n"
                    + "                                    </div>");

            html4.append("        <div class=\"table-wrapper\">\n");
            html4.append("<table id='refreshProprietary' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html4.append("<th class='r' width='10%'>کد</th>");
            html4.append("<th class='r' width='30%'>عنوان هدف کلی</th>");
            html4.append("<th class='r' width='30%'>عنوان هدف اختصاصی</th>");
            html4.append("<th class='r' width='30%'>عنوان استراتژی</th>");
            html4.append("<th class='r' width='30%'>شاخص</th>");
            html4.append("<th class='r' width='30%'>ویرایش</th>");
            html4.append("</thead><tbody>");
            for (int i = 0; i < startegyRow.size(); i++) {
                List<Map<String, Object>> proprietaryRow = jjDatabase.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._id + "=" + startegyRow.get(i).get(Strategy._proprietaryTargetId)));
                List<Map<String, Object>> totalTargetRow = jjDatabase.separateRow(db.Select(TotalTarget.tableName, TotalTarget._id + "=" + proprietaryRow.get(0).get(ProprietaryTarget._totalTargetId)));

                html4.append("<tr  class='p'>");
                html4.append("<td class=''>" + startegyRow.get(i).get(Strategy._id) + "</td>");
                html4.append("<td class=''>" + totalTargetRow.get(0).get(TotalTarget._title) + "</td>");
                html4.append("<td class=''>" + proprietaryRow.get(0).get(ProprietaryTarget._title) + "</td>");
                html4.append("<td class=''>" + startegyRow.get(i).get(Strategy._title) + "</td>");
                html4.append("<td class=''>" + startegyRow.get(i).get(Strategy._indicatorUrl) + "</td>");
                html4.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisStrategy.m_select(" + startegyRow.get(i).get(Strategy._id) + ");'></i></td>");
                html4.append("</tr>");
            }
            html4.append("</tbody></table>");
            html4.append("</div>");
            ///////////////////////////////////

            String script = Js.setHtml("#Strategic_button", html2);
            script += Js.setHtml("#StrategyBtn", html5);//استراتزی
            script += html.toString();
            script += Js.setHtml("#targetTbl", html3);
            script += Js.setHtml("#strategyTbl", html4);
            script += "        $('div#pointer').css({'transform': 'translate(' + $('#tableIFE td .sumFinalVal').val()*40 + 'px'+ ',' + $('#tableEFE td .sumFinalVal').val()  *-44+ 'px'+ ')'});\n";
            script += "$('#targetDiv').show('slow');";
            script += "hmisStrategic.addRowTableQSPM1();";
            script += "$('#BeneficiariesTbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                    $(this).attr('value', $(this).val());\n"
                    + "                });\n"
                    + "     $('#tablePrioritizedDiv #tablePrioritized td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                $('#BeneficiariesTbl td input.onclick').click(function () {\n"
                    + "                    $(this).attr('value', $(this).val());\n"
                    + "                });\n"
                    + "                $('#BeneficiariesTbl #calculateBeneficiaries').click(function () {\n"
                    + "                    hmisStrategic.calculateBeneficiaries('BeneficiariesTbl');\n"
                    + "                });\n"
                    + "  $('#Beneficiaries2Tbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries2Tbl td input.onclick').click(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries2Tbl #calculateBeneficiaries2').click(function () {\n"
                    + "                        hmisStrategic.calculateBeneficiaries2('Beneficiaries2Tbl');\n"
                    + "                    });"
                    + "   $('#Beneficiaries3Tbl  tr.rowBeneficiaries3 td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries3Tbl td #addRowBeneficiaries3').click(function () {\n"
                    + "                        hmisStrategic.addRowBeneficiaries3('Beneficiaries3');\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries3Tbl td #removeRowBeneficiaries3').click(function () {\n"
                    + "                        hmisStrategic.removeRowBeneficiaries3('Beneficiaries3');\n"
                    + "                    });\n"
                    + "    $('#Beneficiaries4Tbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries4Tbl td #addRowBeneficiaries4').click(function () {\n"
                    + "                        hmisStrategic.addRowBeneficiaries3('Beneficiaries4');\n"
                    + "                    });\n"
                    + "                    $('#Beneficiaries4Tbl td #removeRowBeneficiaries4').click(function () {\n"
                    + "                        hmisStrategic.removeRowBeneficiaries3('Beneficiaries4');\n"
                    + "                    });"
                    + "$('#tblBen  tr.rowBene td textarea.onkeyup').keyup(function () {\n"
                    + "                    $(this).text($(this).val());\n"
                    + "                });"
                    + "\n"
                    + "\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeyup').keyup(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td textarea.onkeyup').keyup(function () {\n"
                    + "                        $(this).text($(this).val());\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeyup').click(function () {\n"
                    + "                        $(this).attr('value', $(this).val());\n"
                    + "                    });\n"
                    + "\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeypress').click(function () {\n"
                    + "                        var weight = $(this).parent().parent().find('.weightPercent').attr('value');\n"
                    + "                        if (weight != 0) {\n"
                    + "                            hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);\n"
                    + "                        }\n"
                    + "                    });\n"
                    + "                    $('#weightTbl  tr.rowWeight td input.onkeypress').keyup(function () {\n"
                    + "                        var weight = $(this).parent().parent().find('.weightPercent').attr('value');\n"
                    + "                        if (weight != 0) {\n"
                    + "                            hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);\n"
                    + "                        }\n"
                    + "                    });\n"
                    + "\n"
                    + "                    $('#weightTbl #calculateWeight').click(function () {\n"
                    + "                        hmisStrategic.normalizeTable2('tableWeight');\n"
                    + "\n"
                    + "                    });"
                    + "       $('#IFETbl  tr td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "\n"
                    + "                        });\n"
                    + "                        $('#IFETbl td input.onclick').click(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#IFETbl #calculeteIFE').click(function () {\n"
                    + "                            hmisStrategic.normalizeTable('tableIFE');\n"
                    + "                        });"
                    + "     $('#EFETbl  td input.onkeyup').keyup(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#EFETbl  td input.onclick').click(function () {\n"
                    + "                            $(this).attr('value', $(this).val());\n"
                    + "                        });\n"
                    + "                        $('#EFETbl #calculeteEFE').click(function () {\n"
                    + "                            hmisStrategic.normalizeTable('tableEFE');\n"
                    + "                        });"
                    + "                      ";

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
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);

            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_EFEOuter, jjTools.getParameter(request, _EFEOuter));
            map.put(_IFEandEFE, jjTools.getParameter(request, _IFEandEFE));
            map.put(_IFEinternal, jjTools.getParameter(request, _IFEinternal));
            map.put(_strategicSWOT, jjTools.getParameter(request, _strategicSWOT));
            map.put(_introduction, jjTools.getParameter(request, _introduction));
            map.put(_IssuesStrategic, jjTools.getParameter(request, _IssuesStrategic));
            map.put(_matrixQSPM, jjTools.getParameter(request, _matrixQSPM));
            map.put(_matrixSWOT, jjTools.getParameter(request, _matrixSWOT));
            map.put(_prophecy, jjTools.getParameter(request, _prophecy));
            map.put(_prospect, jjTools.getParameter(request, _prospect));
            map.put(_beneficiaries, jjTools.getParameter(request, _beneficiaries));
            map.put(_prioritized, jjTools.getParameter(request, _prioritized));
            map.put(_title, jjTools.getParameter(request, _title));
//            map.put(_description, jjTools.getParameter(request, _description));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjStrategic.refresh());
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
            System.out.println("id=" + id);
            List<Map<String, Object>> proprietaryTargetRow = jjDatabase.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._strategicId + "=" + id));
            List<Map<String, Object>> totalTargetRow = jjDatabase.separateRow(db.Select(TotalTarget.tableName, TotalTarget._strategicId + "=" + id));
            List<Map<String, Object>> strategyRow = jjDatabase.separateRow(db.Select(Strategy.tableName, Strategy._strategicId + "=" + id));
            String script = "";
            if (strategyRow.size() == 0 && proprietaryTargetRow.size() == 0 && totalTargetRow.size() == 0) {
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
                script += Js.jjStrategic.refresh();
            } else {
                script += Js.modal("امکان حذف وجود ندارد", "پیام سامانه");
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش لیست استراتژیک ها
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getSelectOptionStrategic(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        String script = "";
        try {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "," + _title, "id>=0", _title));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < row.size(); i++) {
                optionHtml.append("<option   value='").append(row.get(i).get(_id)).append("'>").append(row.get(i).get(_title)).append("</option>");
            }
            script += Js.setHtml(".strategicTitle", optionHtml);

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

}
