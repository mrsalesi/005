/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import java.util.ArrayList;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author mahdi
 */
public class ManageFiles {

    public static String tableName = "manageFiles";
    public static String _id = "id";
    public static String _title = "manageFiles_title";
    public static String _file = "manageFiles_file";
    public static String _group = "manageFiles_group";
    public static String _creator = "manageFiles_creator";
    public static String _date = "manageFiles_date";
    public static String _description = "manageFiles_description";
    
    public static int rul_rfsAll = 285;
    public static int rul_rfs = 280;
    public static int rul_ins = 281;
    public static int rul_edt = 282;
    public static int rul_dlt = 283;
    public static int rul_prt = 284;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            boolean hasAccess = Access_User.hasAccess(request, db, rul_rfs);
            if (!hasAccess) {
                Server.outPrinter(request, response, "دوست عزیز دسترسی به این قسمت را ندارین!!!!!!");
                return "";
            }
            
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _creator + "=" + jjTools.getSeassionUserId(request));
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>مدیریت فایل</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsContent.m_add_new();' > فایل جدید</a>"
                    + "        </p>");
            html.append("<table id='refreshManageFile' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th style='text-align: center;' width='5%' >کد</th>");
            html.append("<th style='text-align: center;' width='20%'>عنوان</th>");
            html.append("<th style='text-align: center;' width='20%'> تاریخ تغیرات</th>");
            html.append("<th style='text-align: center;' width='10%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr onclick='$(this).html().print();' class='mousePointer'>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
                html.append("<tr onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='tahoma10 c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='tahoma10 r' >" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='tahoma10 l' >" + (row.get(i).get(_date).toString()) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>" );
                  
            String height = cms.tools.jjTools.getParameter(request, "height");
            String panel = cms.tools.jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshManageFile", height, 0, Access_User.hasAccess(request, db, rul_ins) ? "1" : "", "لیست فایل ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#manageFiles_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjManageFiles.insert() + "' id='insert_manageFiles'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            boolean hasAccess = Access_User.hasAccess(request, db, rul_ins);
            if (!hasAccess) {
                Server.outPrinter(request, response, "دوست عزیز دسترسی به این قسمت را ندارین!!!!!!");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), false));
            map.put(_group, jjTools.getParameter(request, _group));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjContent.refresh());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            boolean hasAccess = Access_User.hasAccess(request, db, rul_edt);
            if (!hasAccess) {
                Server.outPrinter(request, response, "دوست عزیز دسترسی به این قسمت را ندارین!!!!!!");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), false));
            map.put(_group, jjTools.getParameter(request, _group));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (cms.tools.jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjContent.refresh());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            boolean hasAccess = Access_User.hasAccess(request, db, rul_dlt);
            if (!hasAccess) {
                Server.outPrinter(request, response, "دوست عزیز دسترسی به این قسمت را ندارین!!!!!!");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (!row.isEmpty()) {
                String[] list = new File(request.getServletContext().getRealPath("/upload")).list();
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (cms.tools.jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjContent.refresh());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            String id = cms.tools.jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                return Js.dialog(errorMessage);
            }
            StringBuilder script = new StringBuilder();
            script.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            script.append(Js.setVal("#" + tableName + "_" + _title, row.get(0).get(_title)));
            script.append(Js.setVal("#" + tableName + "_" + _file, row.get(0).get(_file)));
            script.append(Js.setVal("#" + tableName + "_" + _description, row.get(0).get(_description)));
            script.append(Js.setVal("#" + tableName + "_" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            script.append(Js.setVal("#" + tableName + "_" + _group, row.get(0).get(_group)));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjManageFiles.edit() + "' id='edit_manageFiles'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjManageFiles.delete(id) + "' id='delete_manageFiles'>" + lbl_delete + "</button></div>";
            }
            script.append(Js.setHtml("#manageFiles_button", htmlBottons));
            Server.outPrinter(request, response, script.toString() + script.toString());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
