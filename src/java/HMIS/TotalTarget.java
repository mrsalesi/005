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
public class TotalTarget {

    public static String tableName = "hmis_totaltarget";
    public static String _id = "id";
    public static String _strategicId = "totalTarget_strategicId";
    public static String _title = "totalTarget_title";//هدف کلی

    public static int rul_rfsAll = 0;
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;
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
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
//               boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//               boolean accRef= Access_User.hasAccess(request, db, rul_rfs);
            StringBuilder html = new StringBuilder();

            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("        <div class=\"card-header bg-primary tx-white\">لیست استراتژیک ها</div>\n");
            html.append(" <div class=\"card-body pd-sm-30\">\n"
                    + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisStrategic.m_add_new();\" >برنامه استراتژیک جدید</a>\n"
                    + "                                        </p>\n"
                    + "                                    </div>");
            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshStrategic' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html.append("<th class='r' width='5%'>کد</th>");
            html.append("<th class='r' width='20%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisStrategic.m_select(" + row.get(i).get(_id) + ")'></i></td>");
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
                panel = "swStrategicTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());

            html2 += Js.table("#refreshStrategic", "300", 0, "", "کمیته ها");

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
            String StrategicId = jjTools.getParameter(request, "hmis_strategic_id");
            jjTools.ShowAllParameter(request);
            StringBuilder html = new StringBuilder();
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_strategicId, StrategicId);
            map.put(_title, jjTools.getParameter(request, _title));
            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {

                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            List< Map< String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            String totalTargetId = row.get(0).get(_id).toString().toString();
            String script = "$('#proprietaryForm').slideDown();"
                    + "$('#hmis_totalTarget_id').val(" + totalTargetId + ");";
            script += Js.jjStrategic.select(StrategicId);
            Server.outPrinter(request, response, script);
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
                html.append(Js.setHtml("#target_button", "<button  id=\"insert_Target_new\"  class=\"btn btn-outline-success  btn-block \" onclick='hmisTotalTarget.m_insert();'>" + lbl_insert + "</button>"));
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
            String id = jjTools.getParameter(request, _id);

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, "*", _id + "=" + id));
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
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            List<Map<String, Object>> proprietaryRow = jjDatabaseWeb.separateRow(db.Select(ProprietaryTarget.tableName, ProprietaryTarget._totalTargetId + "=" + row.get(0).get(_id)));

            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button  id='edit_tolalTarget'  class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjTarget.edit() + "'>" + lbl_edit + "</button>");
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"col-lg\">");
                html2.append("<button id='delete_totalTarget' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjTarget.delete(id) + "' >" + lbl_delete + " </button>");
                html2.append("</div>");
            }
            ////////////////////////////////////////اهداف اختصاصی
            html3.append(" <div class=\"card-body pd-sm-30\">\n"
                    + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisProprietaryTarget.m_add_new();\" >هدف اختصاصی جدید</a>\n"
                    + "                                        </p>\n"
                    + "                                    </div>");
//            html3.append("        <div class=\"card-header bg-primary tx-white\">اهداف اختصاصی</div>\n");
            html3.append("        <div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshProprietary' class='table display responsive' class='tahoma10' style='direction: rtl;'><thead>");
            html3.append("<th class='r' width='10%'>کد</th>");
            html3.append("<th class='r' width='30%'>هدف کلی</th>");
            html3.append("<th class='r' width='30%'>عنوان هدف اختصاصی</th>");
            html3.append("<th class='r' width='30%'>عملیات</th>");
            html3.append("</thead><tbody>");
            for (int i = 0; i < proprietaryRow.size(); i++) {
                html3.append("<tr  class='p'>");
                html3.append("<td class=''>" + proprietaryRow.get(i).get(ProprietaryTarget._id) + "</td>");
                html3.append("<td class=''>" + row.get(0).get(_title) + "</td>");
                html3.append("<td class=''>" + proprietaryRow.get(i).get(ProprietaryTarget._title) + "</td>");
                html3.append("<td class='r'><i class='icon ion-ios-gear-outline'  onclick='hmisProprietaryTarget.m_select(" + proprietaryRow.get(i).get(ProprietaryTarget._id) + ")'></i></td>");
                html3.append("</tr>");
            }
            html3.append("</tbody></table>");
            html3.append("</div>");
//            html2.append("</div>");
            String script = Js.setHtml("#target_button", html2);
            script += Js.setHtml("#proprietaryTable", html3);
            script += html.toString();
            script += "$('#proprietaryForm').slideDown();";
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
            String id = jjTools.getParameter(request, "hmis_totaltarget_id");
            System.out.println("id=" + id);

            List<Map<String, Object>> Row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String strategicId = jjTools.getParameter(request, "hmis_strategic_id");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
//            map.put(_proprietary, jjTools.getParameter(request, _proprietary));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                return "";
            }
            String script = Js.jjTarget.select(id);
            script += "$('#targetForm').hide();";
            Server.outPrinter(request, response, script);
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
            String strategicId = jjTools.getParameter(request, "hmis_strategic_id");
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.otherSelect("SELECT * FROM hmis_totalTarget T  "
                    + " LEFT JOIN  ON T.id=P.plans_hugeGoal "
                    + " LEFT JOIN hmis_proprietaryTarget Pro ON T.id=Pro.proprietaryTarget_totalTargetId"
                    + " WHERE  P.plans_hugeGoal=" + id + " OR Pro.proprietaryTarget_totalTargetId=" + id + ""));

            
            
            
            if (row.size() > 0) {
                Server.outPrinter(request, response, Js.modal("امکان حذف این هدف وجود ندارد", "پیام سامانه"));
            } else {
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
                    return "";
                }
                Server.outPrinter(request, response, Js.jjStrategic.select(strategicId));
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * با انتخاب برنامه استراتژیک می خواهیم در قسمت برنامه عملیاتی اههداف آن
     * برنامه به صورت لیست برایمان نمایش داده شود
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getTarget(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtmlTotal = new StringBuilder();//اهداف کلی
        String script = "";
        try {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT totalTarget_title,id FROM hmis_totalTarget"));// بر اساس حروف الفبا مرتب باشد بهتر است

            for (int i = 0; i < row.size(); i++) {
                optionHtmlTotal.append("<option   value='").append(row.get(i).get(_id)).append("'>").append(row.get(i).get(_title)).append("</option>");
            }
            if (row.size() == 1) {
                script += "hmisProprietaryTarget.selectOptionProprietaryTarget(" + row.get(0).get(_id) + ");";
            }
            script += Js.setHtml(".targetTotal", optionHtmlTotal);

            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

}
