/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///شیران1
//این تابع برای دراوردن موقعیت بخش ها نوشته شده
package HMIS;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
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
 * @author PadidarNB
 */
public class DepartmentPosition {
     public static String tableName = "DepartmentPosition";
    public static String _id = "id";
    public static String _parent = "departmentposition_parent";
//    public static String _title = "DepartmentPosition_title";
    public static String _level = "departmentposition_level";
    public static String _subcategory = "departmentposition_subcategory";//زیر شاخه بخش
    
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;
     public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Department.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            DefaultTableModel dtm = db.Select(Department.tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>بخش ها</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='hmisDepartment.m_add_new();' > بخش جدید</a>"
                    + "        </p>");

            html.append("<table class='table display responsive nowrap' id='refreshParts' dir='rtl'><thead>");
            html.append("<th style='text-align: center;' width='5%'>کد</th>");
//            html.append("<th style='text-align: center;' width='30%'>نام بخش</th>");
            html.append("<th style='text-align: center;' width='20%'>موقعیت</th>");
//            html.append("<th style='text-align: center;' width='10%'></th>");
//            html.append("<th style='text-align: center;' width='30%'></th>");
            html.append("<th style='text-align: center;' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='hmisDepartment.m_select(" + row.get(i).get(_id) + ");' class='mousePointer' >");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_id).toString()) + "</td>");
//                html.append("<td class='tahoma10' style='text-align: left;'>" + (row.get(i).get(_title).toString()) + "</td>");
//              
                html.append("<td class='tahoma10' style='text-align: right;'>" + (row.get(i).get(_subcategory).toString()) + "</td>");

                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='hmisDepartment.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
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
                panel = "swPartTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshParts", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست بخش ها");
             Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
             Server.outPrinter(request, response,Server.ErrorHandler(e));
            return "";
        }
    }

   

}

