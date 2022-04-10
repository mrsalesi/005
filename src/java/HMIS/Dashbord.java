/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import static HMIS.Messenger.*;
import cms.access.Access_User;
import cms.cms.Content;
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
 * @author mahdi
 */
public class Dashbord {

    public static int rul_accessAll = 130;
    public static int rul_rfsAll = 150;
    public static int rul_rfs = 151;
    public static int rul_ins = 152;
    public static int rul_edt = 153;
    public static int rul_dlt = 154;
    public static String wikiLinkColor = "blue";
    public static String lbl_insertDashbord = "ایجاد داشبورد من";

    public static String showDashbord(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
//            if (!Access_User.hasAccess(request, db, rul_dlt)) {
//                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
//                return "";
//            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Content.tableName, Content._ownerId + " = " + jjTools.getSeassionUserId(request) + " AND " + Content._title + " = 'داشبورد'"));
            String script = "";
            if (row.size() == 0) {
                html.append(Js.setHtml("#swDashbord", "<button title='" + lbl_insertDashbord + "' class='btn btn-outline-success btn-block col-lg-12' onclick='" + Js.jjDashbord.insertDashbord() + "' id='insert_MyDashbord'>" + lbl_insertDashbord + "</button>"));
            } else {
                script = "swDashbord('داشبورد', 'swDashbord');";
            }
            Server.outPrinter(request, response, html.toString() + script);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insertDashbord(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
//            if (!Access_User.hasAccess(request, db, rul_dlt)) {
//                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
//                return "";
//            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Content._autoWikIsUpdate, "0");//خود این محتوا آپدیت است
            map.put(Content._titleTag, "");
            map.put(Content._isAjax, 1);
            map.put(Content._priority, 10);
            map.put(Content._lang, 1);
            map.put(Content._category_id, 0);
            map.put(Content._visit, "0");
            map.put(Content._likes, "0");
            map.put(Content._hasLink, "0");
            map.put(Content._disLikes, "0");
            map.put(Content._target, "");
            map.put(Content._parent, 0);
            map.put(Content._priority, 10);
            map.put(Content._date, jjCalendar_IR.getDatabaseFormat_8length("", false));
            map.put(Content._style, "");
            map.put(Content._script, "hmisDashbord.dashbordMyMessage();swIndicators('خدمات سرپایی,سرپایی','swIndicatorsAccreditationDashbord');");
            map.put(Content._headerTags, jjTools.getParameter(request, Content._headerTags));
            map.put(Content._description, "ایجاد به صورت اتوماتیک");
            map.put(Content._content, ("<div class='col-lg-4 mg-b-20' id='swShowMyMessages'></div><div class='col-lg-8 mg-t-20 mg-b-20'> <div class='card rounded-0 card bd-primary'> <div class='card-header bg-indigo tx-white c'> شاخص ها </div><div class='card-body' id='swIndicatorsAccreditationDashbord' style='height:300px;overflow-y: scroll;'></div></div></div>").trim());
            map.put(Content._title, ("داشبورد").trim());
            map.put(Content._userCommensIsActive, 1);
            map.put(Content._isEditableOnlyByOwner, 1);
            map.put(Content._ownerId, jjTools.getSeassionUserId(request));
            if (db.insert(Content.tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjDashbord.showDashbord());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String dashbordMyMessage(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            StringBuilder html = new StringBuilder();

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _receiver + " = " + jjTools.getSeassionUserId(request) + " AND " + _status + " = '" + status_posted + "'"));
            html.append(" <div class='card bd-primary mg-t-20' >"
                    + "    <div class='card-header bg-indigo tx-white c' style='font-style:bold;'>پیام های دیده نشده من</div>"
                    + "        <div style='height:300px;overflow-y: scroll;'>");

            html.append("<table class='table' id='refreshShowMyMessages' dir='rtl'><thead>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='hmisDashbord.m_alert(" + row.get(i).get(_id) + ");' style='cursor:pointer;'>");
                html.append("<td style='text-align:center'>" + (row.get(i).get(_textMessage).toString()+"<br/>"
                +row.get(i).get(_sender).toString()+"   ::   " +jjCalendar_IR.getViewFormat(row.get(i).get(_postageDate)))+ "</td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "200";
            }
            if (panel.equals("")) {
                panel = "swShowMyMessages";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshShowMyMessages", height, 0, "", "لیست پیام ها");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String deleteDashbordMyMessage(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!db.delete(tableName, _id + " = " + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjDashbord.dashbordMyMessage());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
    
    public static String alert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            String id=jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id+"="+id));
            StringBuilder html=new StringBuilder();
            html.append("<div class=\"alert alert-info pd-y-20\" role=\"alert\">" +
"              <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>" +
"              <div class=\"d-sm-flex align-items-center justify-content-start\">" +
"                <i class=\"icon ion-ios-information alert-icon tx-52 mg-r-20\"></i>" +
"                <div class=\" mg-sm-t-0\">" +
"                  <h5 class=\"mg-b-2\">"+row.get(0).get(_title).toString()+"</h5><p class=\"mg-b-0 tx-gray\">"+row.get(0).get(_textMessage).toString()+"</p></div></div></div>");
            String html1=Js.append("#myAlert", html.toString());
            Server.outPrinter(request, response, html1);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
}
