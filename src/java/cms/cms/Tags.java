/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.cms;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author A S U S
 */
public class Tags {

    public static String tableName = "tags";
    public static String _id = "id";
    public static String _name = "tags_name";

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";

    public static int rul_rfs = 100;
    public static int rul_ins = 101;
    public static int rul_dlt = 102;
    public static int rul_edt = 103;
//    public static int rul_lng = 104;

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, Js.modal("شما دسترسی به این قسمت را ندارین", "هشدار امنیتی"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت برچسب ها</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت برچسب ها</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsTags.m_add_new();' > برچسب جدید</a>"
                        + "        </p>");
            }
            html.append("<table id='refreshTags' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th width='5%'>ردیف</th>");
            html.append("<th width='20%'>کد</th>");
            html.append("<th width='60%'>نام</th>");
            html.append("<th width='60%' class='c'>حذف</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr >");/////////////////////////////////////////////////////////////////////////////////////
                html.append("<td class='c'>" + i + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_name).toString()) + "</td>");
                html.append("<td class='c'><i onclick='cmsTags.m_delete(" + (row.get(i).get(_id).toString()) + ")' style='cursor: pointer;color: red;' class='icon ion-power tx-red'></i></td>");/////////////////////////////////////////////////////////////////////////////////////
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swTagsTbl";
            }

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshTags", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست محصولات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#tags_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjTags.insert() + "' id='insert_tags'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, Js.modal("شما دسترسی به این قسمت را ندارین", "هشدار امنیتی"));
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_name, jjTools.getParameter(request, _name));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjTags.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, Js.modal("شما دسترسی به این قسمت را ندارین", "هشدار امنیتی"));
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_id, jjTools.getParameter(request, _id));
            map.put(_name, jjTools.getParameter(request, _name));

            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, "");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, Js.modal("شما دسترسی به این قسمت را ندارین", "هشدار امنیتی"));
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Product.tableName, Product._tags));
            for (int l = 0; l < row.size(); l++) {
                if (!row.get(l).get(Product._tags).equals("") || row.get(l).get(Product._tags).equals("0")) {
                    String tags = row.get(l).get(Product._tags).toString();
                    String[] tagsArry = tags.split(",");
                    for (int j = 0; j < tagsArry.length; j++) {
                        if (tagsArry[j].equals(id)) {
                            Server.outPrinter(request, response, Js.modal("این برچسب در چند قسمت به کاربرده شده است از حذف ان معضوریم", "هشدار"));
                            return "";
                        }
                    }
                }
            }
            List<Map<String, Object>> rowContent = jjDatabase.separateRow(db.Select(Product.tableName, Content._tags));
            for (int i = 0; i < rowContent.size(); i++) {
                if (!rowContent.get(i).get(Content._tags).equals("") || rowContent.get(i).get(Content._tags).equals("0")) {
                    String tags = rowContent.get(i).get(Content._tags).toString();
                    String[] tagsArry = tags.split(",");
                    for (int j = 0; j < tagsArry.length; j++) {
                        if (tagsArry[j].equals(id)) {
                            Server.outPrinter(request, response, Js.modal("این برچسب در چند قسمت به کاربرده شده است از حذف ان معضوریم", "هشدار"));
                            return "";
                        }
                    }
                }
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                Server.outPrinter(request, response, Js.modal(errorMessage, "هشدار"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjTags.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            html.append(Js.setVal(_id, row.get(0).get(_id)));
            html.append(Js.setVal(_name, row.get(0).get(_name)));
            Server.outPrinter(request, response, (Js.setHtml("#tags_button", html2.toString())) + html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    ///قرار دادن برچسب ها در سلکت تو برای محصولات و محتوا

    public static String getSelectOptionTags(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? ".selectOptionTags" : panel;
            html.append("<option id='0' value='0'>برچسب مورد نظر خود را انتخاب کنید در صورت وجود نداشتن ان را در قسمت دسته بندی برچسب اضافه کنید</option>");
            if (row.size() > 0) {
                for (int i = 0; i < row.size(); i++) {
                    html.append("<option id='" + row.get(i).get(_id)
                            + "'  value='" + row.get(i).get(_id) + "'>"
                            + row.get(i).get(_name).toString()
                            + "</option>");
                }
            }
            Server.outPrinter(request, response, Js.setHtml(panel, html + Js.select2(panel, "width: '100%'")));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
}
