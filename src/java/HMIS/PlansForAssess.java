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
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shohreh.shiran Date 1397.10.04
 */
public class PlansForAssess {

    public static String tableName = "";
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";

    /**
     * جدول برنامه های عملیاتی من
     * @param request
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception 
     */
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm1 = db.Select(Steps.tableName,Steps._executorUserId+"="+jjTools.getSessionAttribute(request,"#ID"));
            List<Map<String, Object>> stepsRow = jjDatabase.separateRow(dtm1);
            DefaultTableModel dtm = db.Select(Plans.tableName,Plans._id+"="+stepsRow.get(0).get(Steps._plansId));
            
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);

            html.append("    <div class=\"card-header bg-primary tx-white\">پایش برنامه های عملیاتی تعریف شده</div>\n");
            html.append(" <div class=\"card-body pd-sm-30\">\n"
                    + "                                        <p class=\"mg-b-20 mg-sm-b-30\">\n"
                    + "                                            <a style='color:#fff' class=\"btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white\" onclick=\"hmisPlansForAssess.m_add_new();\" >پایش  برنامه /پایش جدید</a>\n"
                    + "                                        </p>\n"
                    + "                                    </div>");
            html.append("        <div class=\"table-wrapper\">\n");
            html.append("<table id='refreshPlansForAssess' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
//            html.append("<th width='90%'>عنوان</th>");
//            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer'>");
                html.append("<td class='c'></td>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
//                html.append("<tr onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
//                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
//                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
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
                panel = "swPlansForAssessTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPlansForAssess", "300", 0, "", "برنامه های عملیاتی");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
