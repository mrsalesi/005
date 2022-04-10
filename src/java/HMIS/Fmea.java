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
import com.sun.prism.j2d.J2DPipeline;
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
 * @author shohreh.shiran Date 1398.05.27
 */
public class Fmea {

    public static String tableName = "hmis_fmea";
    public static String _id = "id";
    public static String _title = "fmea_title";//
    public static String _guideTable = "fmea_guideTable";//
    public static String _beforeCorrectiveAction = "fmea_beforeCorrectiveAction";//جدول قبل از اقدام اصلاحی
    public static String _afterCorrectiveAction = "fmea_afterCorrectiveAction";//جدول بعد از اقدام اصلاحی
//    public static String _rpnBefore = "fmea_rpnBefore";//
//    public static String _rpnAfter = "fmea_rpnAfter";//
    public static String _member = "fmea_member";//

    public static int rul_fmeaModule = 561;
    public static int rul_rfs = 562;
    public static int rul_ins = 563;
    public static int rul_edt = 564;
    public static int rul_dlt = 565;
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
            html.append("<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size: 33px'><i class='fa fa-refresh mg-t-30' onclick='hmisFmea.m_refresh();'></i>"
                    + "</div>"
                    + "</a>");
            html.append("        <div class=\"card-header bg-primary tx-white\">لیست FMEA</div><div class=\"card-body pd-sm-30\">");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append("<p class=\"mg-b-20 mg-sm-b-30\">\n"
                        + "<a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisFmea.m_add_new();\" >FMEA جدید</a></p>");
            }
            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshFmea' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th class='r' width='20%'>کد</th>");
            html.append("<th class='r' width='60%'>عنوان</th>");
            html.append("<th class='r' width='20%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisFmea.m_select(" + row.get(i).get(_id) + ")'></i></td>");
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

            html2 += Js.table("#refreshFmea", "300", 0, "", "");

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
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            map.put(_afterCorrectiveAction, jjTools.getParameter(request, _afterCorrectiveAction));
            map.put(_beforeCorrectiveAction, jjTools.getParameter(request, _beforeCorrectiveAction));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_member, jjTools.getParameter(request, _member));
            map.put(_guideTable, jjTools.getParameter(request, _guideTable));
            db.insert(tableName, map);
            Server.outPrinter(request, response, Js.jjFmea.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ایجاد هدف جدید
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Fmea_button", "<button  id=\"insert_Fmea_new\"  class=\"btn btn-outline-success  btn-block \" onclick='hmisFmea.m_insert();'>" + lbl_insert + "</button>"));
            }
            String script = html.toString();
            script += " $('#FMEATableOne table textarea').keyup(function () {\n"
                    + "        $(this).text($(this).val());\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableOne table input').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableOne input.onkeyup').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableOne #addRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.addRow('tableFMEA1_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableOne #removeRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.removeRow('tableFMEA1_1');\n"
                    + "    });\n"
                    + "    $('#FMEATableOne #sameRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.updateTablesOne();//یکسانسازی جدول های پایین با جدول اول\n"
                    + "    });\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "    $('#FMEATableOne input.form-control.is-valid').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 0; i < 5; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر \n"
                    + "        $(avgInput).attr('value', Math.round(sum / j));\n"
                    + "\n"
                    + "\n"
                    + "        for (var i = 1; i <= $(\"#tableFMEA1_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "            var RPNresult = $(\"#tableFMEA1_1 tbody tr:nth-child(\" + i + \") td:last-child input\").val() *\n"
                    + "                    $(\"#tableFMEA1_2 tbody tr:nth-child(\" + i + \")  td:last-child input\").val() *\n"
                    + "                    $(\"#tableFMEA1_3 tbody tr:nth-child(\" + i + \")  td:last-child input\").val();\n"
                    + "            $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").attr('value', RPNresult);\n"
                    + "            if (RPNresult >= $(\"#lowFrom\").val() && RPNresult <= $(\"#lowTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-success\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-success\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#middleFrom\").val() && RPNresult <= $(\"#middleTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-warning\");\n"
                    + "\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-warning\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#highFrom\").val() && RPNresult <= $(\"#highTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-danger\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-danger\");\n"
                    + "            }\n"
                    + "        }\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo table textarea').keyup(function () {\n"
                    + "        $(this).text($(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo input.onkeyup').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo table input').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo #addRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.addRowTwo('tableFMEA2_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo #removeRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.removeRowTwo('tableFMEA2_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo #sameRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.updateTablesTwo();\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo input.form-control.is-valid').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 0; i < 5; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر \n"
                    + "        $(avgInput).attr('value', Math.round(sum / j));\n"
                    + "\n"
                    + "//            محاسبهحاصلضرب میانگین های جدول دوم\n"
                    + "        for (var i = 1; i <= $(\"#tableFMEA2_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "            var RPNresult = $(\"#tableFMEA2_1 tbody tr:nth-child(\" + i + \") td:last-child input\").val()\n"
                    + "                    * $(\"#tableFMEA2_2 tbody tr:nth-child(\" + i + \")  td:last-child input\").val()\n"
                    + "                    * $(\"#tableFMEA2_3 tbody tr:nth-child(\" + i + \")  td:last-child input\").val();\n"
                    + "            $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").attr('value', RPNresult);\n"
                    + "            if (RPNresult >= $(\"#lowFromTwo\").val() && RPNresult <= $(\"#lowToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-success\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-success\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#middleFromTwo\").val() && RPNresult <= $(\"#middleToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-warning\");\n"
                    + "\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-warning\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#highFromTwo\").val() && RPNresult <= $(\"#highToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-danger\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-danger\");\n"
                    + "            }\n"
                    + "        }\n"
                    + "        //            تفریق قبل و بعد برای هر ردیف\n"
                    + "       $(\"#finalResult\").html(\"\");\n"
                    + "            for (var i = 1; i <= $(\"#tableFMEA2_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "                var div = $(\"#finalResult\").append(\"<div class='col-lg-12'> RPN2-RPN1\\n\\\n"
                    + "                            <input id='finalResult\" + i + \"' class='form-control is-valid onkeyup'  type='text' />ّ\\n\\\n"
                    + "                        </div>\");\n"
                    + "                var rpn1 = $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").val();\n"
                    + "                var rpn2 = $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").val();\n"
                    + "                $(\"#finalResult\" + i).attr('value', rpn2 - rpn1);\n"
                    + " var subtraction = rpn2 - rpn1;\n"
                    + "                if (subtraction >= $(\"#lowFrom\").val() && RPNresult <= $(\"#lowTo\").val()) {\n"
                    + "                    $(\"#finalResult\" + i).addClass(\"alert-success\");\n"
                    + "                } else {\n"
                    + "                   $(\"#finalResult\" + i).removeClass(\"alert-success\");\n"
                    + "                }\n"
                    + "                if (subtraction >= $(\"#middleFrom\").val() && RPNresult <= $(\"#middleTo\").val()) {\n"
                    + "                   $(\"#finalResult\" + i).addClass(\"alert-warning\");\n"
                    + "                } else {\n"
                    + "                   $(\"#finalResult\" + i).removeClass(\"alert-warning\");\n"
                    + "                }\n"
                    + "                if (subtraction >= $(\"#highFrom\").val() && RPNresult <= $(\"#highTo\").val()) {\n"
                    + "                   $(\"#finalResult\" + i).addClass(\"alert-danger\");\n"
                    + "                } else {\n"
                    + "                    $(\"#finalResult\" + i).removeClass(\"alert-danger\");\n"
                    + "                }\n"
                    + ""
                    + "            }"
                    + "\n"
                    + "    });";
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
            String membersId = row.get(0).get(_member).toString();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            String[] memberIdArray = membersId.split(",");
            String temp = "";
            System.out.println("memberIdArray" + memberIdArray.length);
            for (int i = 0; i < memberIdArray.length; i++) {
                temp += "" + memberIdArray[i] + ",";
            }

            html.append(Js.setVal(_title, row.get(0).get(_title)));
            html.append(Js.setHtml("#beforeCorrectiveActionDiv", row.get(0).get(_beforeCorrectiveAction)));
            html.append(Js.setHtml("#afterCorrectiveActionDiv", row.get(0).get(_afterCorrectiveAction)));
            html.append(Js.setHtml("#guideTableDiv", row.get(0).get(_guideTable)));
            html.append(Js.setValSelectOption("#" + _member, temp));
            html.append(Js.select2("#" + _member, " width: '100%'"));
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button  id='edit_Fmea'  class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjFmea.edit() + "'>" + lbl_edit + "</button>");
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button id='delete_Fmea' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjFmea.delete(id) + "' >" + lbl_delete + " </button>");
                html2.append("</div>");
            }
            html2.append("</div>");
            String script = Js.setHtml("#Fmea_button", html2);

            script += html.toString();
            script += " $('#FMEATableOne table textarea').keyup(function () {\n"
                    + "        $(this).text($(this).val());\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableOne table input').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableOne input.onkeyup').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableOne #addRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.addRow('tableFMEA1_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableOne #removeRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.removeRow('tableFMEA1_1');\n"
                    + "    });\n"
                    + "    $('#FMEATableOne #sameRowTableFMEA1_1').click(function () {\n"
                    + "        hmisFmea.updateTablesOne();//یکسانسازی جدول های پایین با جدول اول\n"
                    + "    });\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "    $('#FMEATableOne input.form-control.is-valid').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 0; i < 5; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر \n"
                    + "        $(avgInput).attr('value', Math.round(sum / j));\n"
                    + "\n"
                    + "\n"
                    + "        for (var i = 1; i <= $(\"#tableFMEA1_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "            var RPNresult = $(\"#tableFMEA1_1 tbody tr:nth-child(\" + i + \") td:last-child input\").val() *\n"
                    + "                    $(\"#tableFMEA1_2 tbody tr:nth-child(\" + i + \")  td:last-child input\").val() *\n"
                    + "                    $(\"#tableFMEA1_3 tbody tr:nth-child(\" + i + \")  td:last-child input\").val();\n"
                    + "            $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").attr('value', RPNresult);\n"
                    + "            if (RPNresult >= $(\"#lowFrom\").val() && RPNresult <= $(\"#lowTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-success\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-success\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#middleFrom\").val() && RPNresult <= $(\"#middleTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-warning\");\n"
                    + "\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-warning\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#highFrom\").val() && RPNresult <= $(\"#highTo\").val()) {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-danger\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-danger\");\n"
                    + "            }\n"
                    + "        }\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo table textarea').keyup(function () {\n"
                    + "        $(this).text($(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo input.onkeyup').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo table input').keyup(function () {\n"
                    + "        $(this).attr('value', $(this).val());\n"
                    + "    });\n"
                    + "    $('#FMEATableTwo #addRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.addRowTwo('tableFMEA2_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo #removeRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.removeRowTwo('tableFMEA2_1');\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo #sameRowTableFMEA2_1').click(function () {\n"
                    + "        hmisFmea.updateTablesTwo();\n"
                    + "    });\n"
                    + "\n"
                    + "    $('#FMEATableTwo input.form-control.is-valid').on('keyup', function (ev) {\n"
                    + "        var sum = 0;\n"
                    + "        var j = 0;\n"
                    + "        for (var i = 0; i < 5; i++) {\n"
                    + "            var nextInput = $(this).parent().parent().find(\"input\")[i];\n"
                    + "            if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {\n"
                    + "                sum += parseFloat($(nextInput).val());\n"
                    + "                j++;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر \n"
                    + "        $(avgInput).attr('value', Math.round(sum / j));\n"
                    + "\n"
                    + "//            محاسبهحاصلضرب میانگین های جدول دوم\n"
                    + "        for (var i = 1; i <= $(\"#tableFMEA2_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "            var RPNresult = $(\"#tableFMEA2_1 tbody tr:nth-child(\" + i + \") td:last-child input\").val()\n"
                    + "                    * $(\"#tableFMEA2_2 tbody tr:nth-child(\" + i + \")  td:last-child input\").val()\n"
                    + "                    * $(\"#tableFMEA2_3 tbody tr:nth-child(\" + i + \")  td:last-child input\").val();\n"
                    + "            $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").attr('value', RPNresult);\n"
                    + "            if (RPNresult >= $(\"#lowFromTwo\").val() && RPNresult <= $(\"#lowToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-success\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-success\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#middleFromTwo\").val() && RPNresult <= $(\"#middleToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-warning\");\n"
                    + "\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-warning\");\n"
                    + "            }\n"
                    + "            if (RPNresult >= $(\"#highFromTwo\").val() && RPNresult <= $(\"#highToTwo\").val()) {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").addClass(\"alert-danger\");\n"
                    + "            } else {\n"
                    + "                $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").removeClass(\"alert-danger\");\n"
                    + "            }\n"
                    + "        }\n"
                    + "        //            تفریق قبل و بعد برای هر ردیف\n"
                    + "       $(\"#finalResult\").html(\"\");\n"
                    + "            for (var i = 1; i <= $(\"#tableFMEA2_4 tbody tr\").length; i++) {// به تعداد سطر های جدول\n"
                    + "                var div = $(\"#finalResult\").append(\"<div class='col-lg-12'> RPN2-RPN1\\n\\\n"
                    + "                            <input id='finalResult\" + i + \"' class='form-control is-valid onkeyup'  type='text' />ّ\\n\\\n"
                    + "                        </div>\");\n"
                    + "                var rpn1 = $(\"#tableFMEA1_4 tbody tr:nth-child(\" + i + \") input:last-child\").val();\n"
                    + "                var rpn2 = $(\"#tableFMEA2_4 tbody tr:nth-child(\" + i + \") input:last-child\").val();\n"
                    + "                $(\"#finalResult\" + i).attr('value', rpn2 - rpn1);\n"
                    + " var subtraction = rpn2 - rpn1;\n"
                    + "                if (subtraction >= $(\"#lowFrom\").val() && RPNresult <= $(\"#lowTo\").val()) {\n"
                    + "                    $(\"#finalResult\" + i).addClass(\"alert-success\");\n"
                    + "                } else {\n"
                    + "                   $(\"#finalResult\" + i).removeClass(\"alert-success\");\n"
                    + "                }\n"
                    + "                if (subtraction >= $(\"#middleFrom\").val() && RPNresult <= $(\"#middleTo\").val()) {\n"
                    + "                   $(\"#finalResult\" + i).addClass(\"alert-warning\");\n"
                    + "                } else {\n"
                    + "                   $(\"#finalResult\" + i).removeClass(\"alert-warning\");\n"
                    + "                }\n"
                    + "                if (subtraction >= $(\"#highFrom\").val() && RPNresult <= $(\"#highTo\").val()) {\n"
                    + "                   $(\"#finalResult\" + i).addClass(\"alert-danger\");\n"
                    + "                } else {\n"
                    + "                    $(\"#finalResult\" + i).removeClass(\"alert-danger\");\n"
                    + "                }\n"
                    + ""
                    + "            }"
                    + "    });";
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
            System.out.println("id=" + id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                Server.outPrinter(request, response, Js.modal(errorMessage,"پیام سامانه"));
//            }

            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_beforeCorrectiveAction, jjTools.getParameter(request, _beforeCorrectiveAction));
            map.put(_afterCorrectiveAction, jjTools.getParameter(request, _afterCorrectiveAction));
            map.put(_guideTable, jjTools.getParameter(request, _guideTable));
            map.put(_member, jjTools.getParameter(request, _member));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjFmea.refresh());
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

            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjFmea.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//    /**
//     * با انتخاب برنامه استراتژیک می خواهیم در قسمت برنامه عملیاتی اههداف آن
//     * برنامه به صورت لیست برایمان نمایش داده شود
//     *
//     * @param request
//     * @param response
//     * @param db
//     * @param needString
//     * @return
//     * @throws Exception
//     */
//    public static String getTarget(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        StringBuilder optionHtmlTotal = new StringBuilder();//اهداف کلی
//        StringBuilder optionHtmlProprietary = new StringBuilder();//اهداف اختصاصی و جزئی
//        String script = "";
//        try {
//            String strategicId = jjTools.getParameter(request, "hmis_strategic_id");
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT target_total,target_proprietary FROM hmis_target WHERE target_strategicId= " + strategicId + ""));// بر اساس حروف الفبا مرتب باشد بهتر است
////
////            for (int i = 0; i < row.size(); i++) {
////                optionHtmlTotal.append("<option   value='").append(row.get(i).get(_total)).append("'>").append(row.get(i).get(_total)).append("</option>");
////            }
////            for (int i = 0; i < row.size(); i++) {
////                optionHtmlProprietary.append("<option   value='").append(row.get(i).get(_proprietary)).append("'>").append(row.get(i).get(_proprietary)).append("</option>");
////            }
////            script += Js.setHtml(".targetTotal", optionHtmlTotal);
////            script += Js.setHtml(".targetProprietary", optionHtmlProprietary);//
//
//            Server.outPrinter(request, response, script);
//            return "";
//        } catch (Exception e) {
//            Server.outPrinter(request, response, Server.ErrorHandler(e));
//            return "";
//        }
//    }
}
