package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;

public class News {

    public static String tableName = "news";
    public static String _id = "id";
    public static String _date = "news_date";
    public static String _priority = "news_priority";
    public static String _title = "news_title";
    public static String _content = "news_content";
    public static String _category_id = "news_category_id";
    public static String _parent = "news_parent";
    public static String _lang = "news_lang";
    public static String _pic = "news_pic";//v1.5.0
    public static String _privateGroupId = "news_privateGroupId";//v1.5.0
    public static String _privateUserId = "news_privateUserId";//v1.5.0
    public static String _abstract = "news_abstract";//v1.5.0
    public static String _rating = "news_rating";//v1.5.0
    public static String _visit = "news_visit";//v1.5.0
    public static String _likes = "news_likes";//v1.5.0
    public static String _disLikes = "news_disLikes";//v1.5.0
    public static String _hasLink = "news_has_link";//====== BY RASHIDI ======برای این خبر لینک در محتوا ساخته شود یا خیر
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static int rul_rfs = 251;
    public static int rul_ins = 252;
    public static int rul_edt = 253;
    public static int rul_dlt = 254;
    public static int rul_prt = 255;
    public static int rul_rfsAll = 256;

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _parent + "=0");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت اخبار سازمانی</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت اخبار سازمانی</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsNews.m_add_new();' > اخبار سازمانی جدید</a>"
                        + "        </p>");
            }
            html.append("<table id='refreshNews' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='15%'>تاریخ خبر</th>");
            html.append("<th width='70%'>عنوان خبر</th>");
            html.append("<th width='5%'>دسته</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsNews.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>/" + (row.get(i).get(_category_id).toString()) + "/</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsNews.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swNewsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshNews", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست اخبار سازمانی");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String isBeing(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_parent).toString().equals(id)) {
                    return rows.get(i).get(_id).toString();
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#News_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjNews.insert() + "' id='insert_News'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_parent, 0);
            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_pic, jjTools.getParameter(request, "news_attachPic"));//====== BY RASHIDI ======
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            map.put(_priority, jjTools.getParameter(request, _priority));
            String category = jjTools.getParameter(request, _category_id);
            map.put(_privateGroupId, request.getParameter(_privateGroupId));
            map.put(_privateUserId, request.getParameter(_privateUserId));
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 0);
            map.put(_lang, 1);
            map.put(_abstract, jjTools.getParameter(request, _abstract));
            map.put(_hasLink, jjTools.getParameter(request, _hasLink));
            if ((jjTools.getParameter(request, _visit)).equals("")) {
                map.put(_visit, "0");
            } else {
                map.put(_visit, jjTools.getParameter(request, _visit));
            }
            if ((jjTools.getParameter(request, _likes)).equals("")) {
                map.put(_likes, "0");
            } else {
                map.put(_likes, jjTools.getParameter(request, _likes));
            }
            if ((jjTools.getParameter(request, _disLikes)).equals("")) {
                map.put(_disLikes, "0");
            } else {
                map.put(_disLikes, jjTools.getParameter(request, _disLikes));
            }
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_parent, 0);
            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_pic, jjTools.getParameter(request, "news_attachPic"));
            map.put(_abstract, jjTools.getParameter(request, _abstract));
//            map.put(_rating, jjTools.getParameter(request, _rating));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_likes, jjTools.getParameter(request, _likes));
            map.put(_disLikes, jjTools.getParameter(request, _disLikes));

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            map.put(_priority, jjTools.getParameter(request, _priority));
            map.put(_privateGroupId, request.getParameter(_privateGroupId));
            map.put(_privateUserId, request.getParameter(_privateUserId));
            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 0);
            map.put(_lang, 1);
            map.put(_hasLink, jjTools.getParameter(request, _hasLink));
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjNews.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() != 0) {
                String toString = row.get(0).get(_content).toString();
                //
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                String[] list = new File(request.getServletContext().getRealPath("/upload")).list();

//                for (int i = 0; i < list.length; i++) {
//                    if (toString.indexOf("src=\"upload/" + list[i] + "\"") > -1) {
//                        File pics = new File(folderAddress.getAbsolutePath() + "/" + list[i]);
//                        if (pics.exists()) {
//                            pics.delete();
//                        }
//                        File pics_small = new File(folderAddress.getAbsolutePath() + "/" + list[i].replace(".", "_small."));
//                        if (pics_small.exists()) {
//                            pics_small.delete();
//                        }
//                    }
//                }
            }
            if (!db.delete(tableName, _id + "=" + id + " OR " + _parent + " = " + id)) {//============ EDITED BY RASHIDI ========
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id);
            return Js.jjNews.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getParent(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_parent).toString().equals(id)) {
                    return rows.get(i).get(_id).toString();
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title).toString()));
            html.append(Js.setValSelectOption("#" + _category_id, row.get(0).get(_category_id).toString()));
            html.append(Js.select2("#" + _category_id, ""));
            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setValSummerNote("#" + _abstract, row.get(0).get(_abstract)));
            html.append(Js.setSrc("#news_Pic", row.get(0).get(_pic)));
            html.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            html.append(Js.setValSummerNote("#" + _content, row.get(0).get(_content)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setValSelectOption("#" + _privateUserId, row.get(0).get(_privateUserId).toString()));
            html.append(Js.select2("#" + _privateUserId, ""));
            html.append(Js.setValSelectOption("#" + _privateGroupId, row.get(0).get(_privateGroupId).toString()));
            html.append(Js.select2("#" + _privateGroupId, ""));
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            Integer likes = Integer.valueOf(row.get(0).get(_likes).toString());
            html.append(Js.setVal("#" + _likes, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _likes + "_checkbox", 0));
                html.append(Js.setAttr("#" + _likes, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _likes + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _likes, "disabled"));
            }
            Integer dislikes = Integer.valueOf(row.get(0).get(_disLikes).toString());
            html.append(Js.setVal("#" + _disLikes, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _disLikes + "_checkbox", 0));
                html.append(Js.setAttr("#" + _disLikes, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _disLikes + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _disLikes, "disabled"));
            }
            html.append(Js.setVal("#" + _hasLink, row.get(0).get(_hasLink)));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjNews.edit() + "' id='edit_News'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjNews.delete(id) + "' id='delete_News'>" + lbl_delete + "</button></div>";
            }
            html.append(Js.setHtml("#News_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getOneNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = "";
            //to incriment visited time
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
            List<Map<String, Object>> row = null;
            row.size();
            row = jjDatabase.separateRow(db.Select(News.tableName, _id + " = " + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            html.append("<div class='newsmainDiv'>");
            html.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
            int visit = new Integer(row.get(0).get(_visit).toString());
            if (visit >= 0) {
                html.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
            }
            int disLikes = new Integer(row.get(0).get(_disLikes).toString());
            if (disLikes >= 0) {
                html.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " مخالف </div>");
            }
            int likes = new Integer(row.get(0).get(_likes).toString());
            if (likes >= 0) {
                html.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " موافق </div>");
            }
            html.append("<img class='newsPicDiv' src='" + row.get(0).get(_pic).toString() + "'/>");
            html.append("<span class='newsTitlespan'><h3>" + row.get(0).get(_title).toString() + "<h3></span>");
            html.append("<div class='newsabstarctDiv'>" + row.get(0).get(_abstract).toString() + "</div>");
            html.append("<div id='newContentDiv' class='newContentDiv'></div>");

            html.append("<span class='moreDatale'>"
                    + "<a href='Server?do=News.getList&id=" + row.get(0).get(_category_id).toString() + "' onclick='swGetNews(" + row.get(0).get(_category_id).toString() + ");return false;'>" + " مطالب مرتبط" + "</a>"
                    + "</span>");
            html.append("</div>");

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.setHtml("#newContentDiv", row.get(0).get(_content).toString());
//            row.get(0).get(_content).toString()
//        html2 += Js.table("#sss", height, sort, "");

            //============ BY RASHIDI ========>
            title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, row.get(0).get(_title).toString());
            //is not by Ajax
            boolean senderIsMyJs = jjTools.getParameter(request, "jj").equals("1");
            if (!senderIsMyJs) {
//                    return content;
                String address = request.getServletContext().getRealPath("/");
                File file = new File(address + "/" + Server.mainPageJSP);
                StringBuilder html3 = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                if (!file.exists()) {
                    return html2;
                } else {
                    int index = html3.indexOf("<title");//finding title tag
                    if (index > -1) {
                        index = html3.indexOf(">", index); //finding title tag ( <title> )
                        title = row.get(0).get(_title).toString() + ", ";
                        html3 = html3.insert(index + 1, title);
                    }
                    //replace content in <div id="sw"> ... </div>...........
                    index = html3.indexOf("\"sw\"");//finding div in wich id="sw"
                    if (index > -1) {
                        index = html3.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
                        html3 = html3.insert(index + 1, html);
                    }
                    //......................................................
                }
                int index = html3.indexOf("initCms();");// برای اینکه در صفحه خبر فراخوانی نشود
                html3.delete(index, index + 10);
                index = html3.indexOf("jjAutoSlider();");
                html3.delete(index, index + 15);
//                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + html.toString());
                return html3.toString();
            }
            //is not by Ajax
            //<============ BY RASHIDI ========

            return html2 + title;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String newsDisLike(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _disLikes + " = " + _disLikes + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String newsLike(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _likes + " = " + _likes + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlider(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            html.append("<ul>");
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _priority + "=1 AND " + _lang + "=" + (jjTools.isLangFa(request) ? "1" : "2")));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<li  style='width: " + width + "px;'><table class='tahoma10' style='width: " + width
                        + "px;height: " + height + "px;'>");
                html.append("<tr><td valign='top' class='tahoma12'  dir='" + jjTools.getLangDir(request) + "' style='vertical-align: middle;text-align: " + jjTools.getLangAlign(request) + ";height:20px;padding:10px'>");
                html.append(allNews.get(i).get(_title));
                html.append("</td></tr><tr>");
                html.append("<td valign='top'  dir='" + jjTools.getLangDir(request) + "' style='height:" + (Integer.parseInt(height) - 120) + "px;vertical-align: top;text-align: " + jjTools.getLangAlign(request) + ";padding:10px'>");
                html.append("<div style='overflow: hidden;height:" + (Integer.parseInt(height) - 120) + "px'>");
                html.append(allNews.get(i).get(_content));
                html.append("</div>");
                html.append("</td></tr><tr>");
                html.append("<td valign='top'  dir='rtl' style='text-align: left;height:20px;padding:10px'>");
                html.append("<a onclick=swNews(" + allNews.get(i).get(_id) + ");>");
                html.append(jjTools.isLangFa(request) ? "ادامه مطلب ..." : "... more");
                html.append("</a></td></tr></table></li>");
            }
            html.append("</ul>");
            String panel = jjTools.getParameter(request, "panel");
            String prevBtnSelector = jjTools.getParameter(request, "prevBtnSelector");
            String nextBtnSelector = jjTools.getParameter(request, "nextBtnSelector");
            String auto = jjTools.getParameter(request, "auto");
            String speed = jjTools.getParameter(request, "speed");
            if (panel.equals("")) {
                panel = "newsSlide";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, prevBtnSelector, nextBtnSelector, speed, auto);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlider2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String lang = jjTools.getLangNum(request);
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            StringBuilder html = new StringBuilder();
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            html.append("<ul>");

            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _priority + "=1 AND " + _lang + "=" + jjTools.getLangNum(request)));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<li><div dir='" + jjTools.getLangDir(request)
                        + "' style='overflow: hidden;height:" + height + "px;width:" + width + "px;text-align: "
                        + jjTools.getLangAlign(request)
                        + ";'>");
                html.append("<div class='NewsTitle'>" + allNews.get(i).get(_title).toString() + "</div>");
                html.append("<div class='NewsPIC'><img src='" + allNews.get(i).get(_pic).toString() + "'/></div>");
                html.append("<div class='NewsAbstract' >");
                html.append(allNews.get(i).get(_abstract).toString().replace("<li>", "").replace("<li dir='rtl'>", "").replace("</li>", ""));
                html.append("</div>");
                html.append("</div><div class='newsMore' valign='top'  dir='" + jjTools.getLangDir(request)
                        + "' style='text-align: left;height:17px;vertical-align: top;'>");
                html.append("<a class='newsMoreInSlider' onclick=swNews(" + allNews.get(i).get(_id) + ");>");
                html.append(langSetting.get(0).get(Language._detail));
                html.append("</a></div></li>");
            }
            html.append("</ul>");
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "jjSliderNews";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript2(panel);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getjournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> journal = jjDatabase.separateRow(db.Select(Journal.tableName, Journal._parent + "=" + id + " order by " + Journal._date));
            StringBuilder html = new StringBuilder();
            html.append("<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\"  style=\"margin: 10px 0px;\">\n"
                    + "                                        <div class=\"courses-page-top-area\">\n"
                    + "                                            <div class=\"courses-page-top-right\">\n"
                    + "                                                <ul>\n"
                    + "                                                    <li class=\"active\"><a href=\"#gried-view\" data-toggle=\"tab\" aria-expanded=\"true\"><i class=\"fa fa-th-large\"></i>ارشیو نشریات</a></li>\n"
                    + "                                                </ul>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"courses-page-top-left\">\n"
                    + "                                                <p>نتایج:" + journal.size() + "</p>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>");
            for (int h = 0; h < journal.size(); h++) {
                List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + journal.get(h).get(Journal._id)));
                html.append("<div onclick='showJournal(" + journal.get(h).get(Journal._id) + ")' class='list-item col-lg-4 col-md-6 col-sm-12 col-xs-12' >");
                html.append("<div class=\"courses-box1\">");
                html.append("<div class='single-item-wrapper'>");
                html.append("<div class=\"courses-img-wrapper hvr-bounce-to-bottom\"  style=\"width: 100%\">");
                html.append("<img class='img-responsive' style='height: 40vh;width: fit-content' src='upload/" + journal.get(h).get(Journal._pic) + "' alt='courses'>");
                html.append("<a href='#'><i class='fa fa-link' aria-hidden='true'></i></a>");
                html.append("</div>");
                html.append("<div class='courses-content-wrapper'>");
                html.append("<h5 class='item-title' style=''><a href='#'>" + journal.get(h).get(Journal._title) + "</a></h5>");
                html.append("<p class='item-content' style=''>" + journal.get(h).get(Journal._discription) + "</p>");
                html.append("<ul class='courses-info' style='bottom: 0px'>");
                html.append("<li>تعداد پیام ها");
                html.append("<br><span>" + rowComment.size() + "</span></li>");
                html.append("<li>تعداد مشاهده");
                html.append("<br><span>" + journal.get(h).get(Journal._visit) + "</span></li>");
                html.append("<li>تاریخ ایجاد");
                html.append("<br><span>" + journal.get(h).get(Journal._DateRelease) + "</span></li>");
                html.append("</ul>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
            }
            String html2 = Js.setHtml("#swJournal", html.toString());
//            html2 += Js.table("#refreshComment", height, 0, "", "لیست پیام مدیر به کاربران");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String showjournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> journal = jjDatabase.separateRow(db.Select(Journal.tableName, Journal._id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            int visit = Integer.parseInt(journal.get(0).get(Journal._visit).toString()) + 1;
            map.put(Journal._visit, visit);
            db.update(Journal.tableName, map, Journal._id + "=" + id);
            StringBuilder html = new StringBuilder();
            html.append("<div class='row'>\n"
                    + "    <div class='col-lg-9 col-md-9 col-sm-8 col-xs-12'>\n"
                    + "        <div class='course-details-inner'>\n"
                    + "            <h2 class='title-default-left title-bar-high'>" + journal.get(0).get(Journal._title) + "</h2>\n"
                    + "            <p>" + journal.get(0).get(Journal._discription) + "</p>\n"
                    + "            <img src='upload/" + journal.get(0).get(Journal._pic) + "' class='img-responsive' alt='course'>\n"
                    + "            <div class='course-details-tab-area'>\n"
                    + "                <div class='row'>\n"
                    + "                    <div class='col-lg-12 col-md-12 col-sm-12'>\n"
                    + "                        <ul class='course-details-tab-btn'>\n"
                    + "                            <li class='active'><a href='#features' data-toggle='tab' aria-expanded='false'>مشخصات</a></li>\n"
                    + "                            <li class=''><a href='#reviews' data-toggle='tab' aria-expanded='false'>ارسال کامنت</a></li>\n"
                    + "                        </ul>\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12 col-md-12 col-sm-12'>\n"
                    + "                        <div class='tab-content'>\n"
                    + "                            <div class='tab-pane fade active in' id='features'>\n"
                    + "                                <ul class='course-feature'>\n"
                    + "                                    <li>عنوان نشریه:" + journal.get(0).get(Journal._title) + "</li>\n"
                    + "                                    <li>زمینه نشریه:" + journal.get(0).get(Journal._Issue) + "</li>\n"
                    + "                                    <li>شماره نشریه:" + journal.get(0).get(Journal._ReleaseNumber) + "</li>\n"
                    + "                                    <li>تاریخ انتشار:" + journal.get(0).get(Journal._DateRelease) + "</li>\n"
                    + "                                    <li>صاحب امتیاز :" + journal.get(0).get(Journal._Concessionaire) + "</li>\n"
                    + "                                    <li>مدیر مسئول:" + journal.get(0).get(Journal._Responsible) + "</li>\n"
                    + "                                    <li>سردبیر:" + journal.get(0).get(Journal._chiefEditor) + "</li>\n"
                    + "                                    <li>بازدید:" + journal.get(0).get(Journal._visit) + "</li>\n"
                    + "                                </ul>\n"
                    + "                            </div>                                     \n"
                    + "                            <div class='tab-pane fade' id='reviews'>\n");
            html.append(getComment(db, journal.get(0).get(_id).toString()));
            html.append(postComment(journal.get(0).get(_id).toString()));
            html.append("</div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    <div class='col-lg-3 col-md-3 col-sm-4 col-xs-12'>\n"
                    + "        <div class='sidebar'>\n"
                    + "            <div class='sidebar-box'>\n"
                    + "                <div class='sidebar-box-inner'>\n"
                    + "                    <h3 class='sidebar-title'>قیمت نشریه</h3>\n"
                    + "                    <div class='sidebar-course-price'>\n"
                    + "                        <span>" + journal.get(0).get(Journal._price) + "</span>\n"
                    + "                        <a href='#' class='enroll-btn'>پرداخت</a>\n"
                    + "                        <a href='upload/" + journal.get(0).get(Journal._file) + "' class='download-btn'>دانلود پی دی اف</a>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>\n"
                    + "");
            String html2 = Js.setHtml("#swJournal", html.toString());
//            html2 += Js.table("#refreshComment", height, 0, "", "لیست پیام مدیر به کاربران");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String lang = jjTools.getLangNum(request);
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + id));
            StringBuilder html = new StringBuilder();
            Map<String, Object> map = new HashMap<>();
            int visit = Integer.parseInt(row.get(0).get(_visit).toString()) + 1;
            map.put(_visit, visit);
            db.update(Journal.tableName, map, _id + "=" + id);

            html.append("<div class='row'>\n"
                    + "        <div class='row news-details-page-inner'>\n"
                    + "            <div class='col-lg-12 col-md-12 col-sm-12 col-xs-12'>\n"
                    + "                <div class='news-img-holder'>\n"
                    + "                    <img src='upload/" + row.get(0).get(_pic) + "' class='img-responsive' alt='research'>\n"
                    + "                </div>\n"
                    + "                <h2 class='title-default-left-bold-lowhight'><a href='#'>" + row.get(0).get(_title) + "</a></h2>\n"
                    + "                <p>" + row.get(0).get(_abstract) + "</p>\n");
            html.append(getComment(db, id));
            html.append(postComment(row.get(0).get(_id).toString()));

            html.append("</div></div></div>");
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "jjSliderNews";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getContentNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String lang = jjTools.getLangNum(request);
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + id));
            StringBuilder html = new StringBuilder();
            Map<String, Object> map = new HashMap<>();
            int visit = Integer.parseInt(row.get(0).get(_visit).toString()) + 1;
            map.put(_visit, visit);
            db.update(Journal.tableName, map, _id + "=" + id);
             jjCalendar_IR dateLable = new jjCalendar_IR(row.get(0).get(News._date).toString());
                                                            String month = dateLable.getMonthName();
                                                            int day = dateLable.getDay();
                                                            int year = dateLable.getYear();
            html.append("<div class='post-wrap'>");
                     html.append("<article class='post clearfix'>");
                     html.append("<div class='featured-post'>\n");
                     html.append("<img src='upload/"+row.get(0).get(News._pic)+"' alt='"+row.get(0).get(News._title)+"' style='width: 100%;height: 400px'>");
                     html.append("<ul class='post-comment'>");
                     html.append("<li class='date'>");
                     html.append("<span class='day'>"+day+"</span>");
                     html.append("</li>");
                     html.append("<li class='comment'>");
                     html.append(month);
                     html.append("</li>");
                     html.append("</ul>");
                     html.append("</div>");
                     html.append("<div class='content-post'>\n");
                     html.append("<h2 class='title-post'><a href=''>"+row.get(0).get(News._title)+"</a></h2>");
                     html.append("<ul class='meta-post clearfix'>");
                     html.append("<li class='author'>");
                     html.append("<a href=''></a>");
                     html.append("</li>");
                     html.append("<li class='vote'>");
                     html.append("<a href=''>"+row.get(0).get(News._visit)+"</a>");
                     html.append("</li>");
                     html.append("</ul>");
                     html.append("<div class='entry-post excerpt'>\n");
                     html.append("<p>"+row.get(0).get(News._content)+"</p>");
                     html.append("</div>");
                     html.append("</div>");
                     html.append("</article>");
                     html.append("</div>");

            html.append("</div></div></div>");
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "sw";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript2(String divId) {
        try {
//        return ";\n";
            return "$('#" + divId + "').easySlider({controlsBefore:'<p id=\"controls\">',controlsAfter:'</p>',auto: true,continuous: true});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getComment(jjDatabaseWeb db, String id) {
        try {
//        return ";\n";
            List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + id));
            StringBuilder html = new StringBuilder();

            html.append("<div class='course-details-comments'>\n"
                    + "                    <h3 class='sidebar-title'>پیام ها</h3>\n");
            int x = 0;
            if (rowComment.size() > 5) {
                x = rowComment.size() - 5;
            } else {
                x = 0;
            }
            for (int i = (rowComment.size() - 1); i >= x; i--) {
                html.append("                    <div class='media' id='comment'>\n"
                        + "                        <a href='#' class='pull-left'>\n"
                        + "                            <img alt='Comments' src='template1/img/comment.jpg' class='media-object' style='width:80px'>\n"
                        + "                        </a>\n"
                        + "                        <span class='media-body'>\n"
                        + "                            <h3><a href='#'>" + rowComment.get(i).get(Comment._name_Full) + "</a></h3>\n"
                        + "                            <p>" + rowComment.get(i).get(Comment._text) + "</p>\n"
                        + "                        </div>\n"
                        + "                ");
            }
            html.append("</div>");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String postComment(String id) {
        try {
            System.out.println("//" + id);
            StringBuilder html = new StringBuilder();
            html.append("<div class='leave-comments' style='direction:rtl'>\n"
                    + "                    <h3 class='sidebar-title'>ارسال دیدگاه</h3>\n"
                    + "                    <div class='row'>\n"
                    + "                        <div class='contact-form'>\n"
                    + "                            <form id='swCommentForm'>\n"
                    + "                                <fieldset>\n"
                    + "                                    <div class='col-sm-4'>\n"
                    + "                                        <div class='form-group'>\n"
                    + "                                            <input name='comment_email' id='comment_email' type='email' placeholder='ایمیل' class='form-control'>\n"
                    + "                                            <div class='help-block with-errors'></div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class='col-sm-4'>\n"
                    + "                                        <div class='form-group'>\n"
                    + "                                            <input name='comment_tell' id='comment_tell' type='' placeholder='شماره همراه' class='form-control'>\n"
                    + "                                            <div class='help-block with-errors'></div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class='col-sm-4'>\n"
                    + "                                        <div class='form-group'>\n"
                    + "                                            <input type='text' name='comment_full_name' id='comment_full_name' placeholder='نام و نام خانوادگی' class='form-control'>\n"
                    + "                                            <input type='hidden' name='comment_refrenceId' id='comment_refrenceId' placeholder='' class='form-control' value='" + id + "'>\n"
                    + "                                            <div class='help-block with-errors'></div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class='col-sm-12'>\n"
                    + "                                        <div class='form-group'>\n"
                    + "                                            <textarea name='comment_text' placeholder='دیدگاه' class='textarea form-control' id='comment_text' rows='8' cols='20'></textarea>\n"
                    + "                                            <div class='help-block with-errors'></div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                        <div class='leave-comments'>\n"
                    + "                                        <div class=\"col-sm-12 contact-form\" id='review-form'>\n"
                    + "                                                        <div class=\"form-group\">\n"
                    + "                                                            <div class=\"rate-wrapper\">\n"
                    + "                                                                <div class=\"rate-label\">رای خود راثبت کنید:</div>\n"
                    + "                                                                <div class=\"rate\">\n"
                    + "                                                                    <div name='comment_rait'  class=\"rate-item\">1<i class=\"fa fa-star\" aria-hidden=\"true\"></i></div>\n"
                    + "                                                                    <div name='comment_rait'  class=\"rate-item\">2<i class=\"fa fa-star\" aria-hidden=\"true\"></i></div>\n"
                    + "                                                                    <div name='comment_rait'  class=\"rate-item \">3<i class=\"fa fa-star\" aria-hidden=\"true\"></i></div>\n"
                    + "                                                                    <div name='comment_rait'  class=\"rate-item\">4<i class=\"fa fa-star\" aria-hidden=\"true\"></i></div>\n"
                    + "                                                                    <div name='comment_rait'  class=\"rate-item\">5<i class=\"fa fa-star\" aria-hidden=\"true\"></i></div>\n"
                    + "                                                                </div>\n"
                    + "                                                            </div>\n"
                    + "                                                        </div>\n"
                    + "                                                    </div>\n"
                    + "                                                    </div>\n"
                    + "                                   <script src='farhangi/js/main.js' type='text/javascript'></script>\" \n"
                    + "\n"
                    + "                                </fieldset>\n"
                    + "                            </form>\n"
                    + "                                    <div class='col-sm-12'>\n"
                    + "                                        <div class='form-group margin-bottom-none'>\n"
                    //                    + "                                            <a onclick='cmsComment.m_insert()' class='view-all-accent-btn'>ارسال دیدگاه</a>\n"
                    + "                                            <button type=\"submit\" class=\"view-all-accent-btn\" onclick='insertComment()'>ارسال دیدگاه</button>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript(String divId, String prevBtnSelector, String nextBtnSelector, String speed, String auto) {
        try {
            speed = speed == "" ? "200" : speed;
            auto = auto == "" ? "4000" : auto;
            return "$('#" + divId + "').jCarouselLite({"
                    + "vertical: false,"
                    + "hoverPause:true,"
                    + "btnPrev: '#" + prevBtnSelector + "',"
                    + "btnNext: '#" + nextBtnSelector + "',"
                    + "visible: 1,"
                    + "auto:" + auto + ","
                    + "speed:" + speed + ""
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0
        try {

            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, "اخبار");
            /*طبقه بندی اخبار را تابع دیگری که از جاوا اسکریپت فراخوانی می شود برمیگرداند*/
            Integer category_id = new Integer(jjTools.getParameter(request, "id").toString());
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topNewsRow;
            if (category_id == 0) {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=1 AND " + News._priority + "<=2", _date));
            } else {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=1 AND " + News._category_id + "=" + category_id, _date));
            }

            //---------------one news post creation
                 /*لیست اخبار را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            temphtml.append("<div id='swTopNewsDiv' class='topNewsDiv'>");
            if (topNewsRow.isEmpty()) {
                temphtml.append("<div class='noAnyThing'>!!! در این بخش موردی برای نمایش وجود ندارد<div>");
            } else {
                for (int i = 0; i < topNewsRow.size(); i++) {
                    String id = topNewsRow.get(i).get(_id).toString();
                    temphtml.append("<div class='newsmainDiv'>");
                    temphtml.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(topNewsRow.get(i).get(_date).toString()) + "</span>");
                    int visit = new Integer(topNewsRow.get(i).get(_visit).toString());
                    if (visit >= 0) {
                        temphtml.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
                    }
                    int disLikes = new Integer(topNewsRow.get(i).get(_disLikes).toString());
                    if (disLikes >= 0) {
                        temphtml.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " مخالف </div>");
                    }
                    int likes = new Integer(topNewsRow.get(i).get(_likes).toString());
                    if (likes >= 0) {
                        temphtml.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " موافق </div>");
                    }
                    temphtml.append("<a href='Server?do=News.getOneNews&id=" + id + "' onclick='getOneNews(" + id + ");return false;' ><img class='newsPicDiv' src='" + topNewsRow.get(i).get(_pic).toString() + "'/></a>");
                    temphtml.append("<span class='newsTitlespan'><h3>" + topNewsRow.get(i).get(_title).toString() + "</h3></span>");
                    temphtml.append("<div class='newsabstarctDiv'><h4>" + topNewsRow.get(i).get(_abstract).toString() + "</h4></div>");
                    temphtml.append("<span class='moreDatale'>"
                            + "<a href='Server?do=News.getOneNews&id=" + id + "' onclick='getOneNews(" + id + ");return false;'>" + "ادامه مطلب" + "</a>"
                            + "</span>");
                    temphtml.append("<span class='coGruopNews'>"
                            + "<a href='Server?do=News.getList&id=" + topNewsRow.get(i).get(_category_id).toString() + "' onclick='swGetNews(" + topNewsRow.get(i).get(_category_id).toString() + ");return false;'>" + "مطالب مرتبط" + "</a>"//============ EDITED BY RASHIDI ========
                            + "</span>");
                    temphtml.append("</div>");
                }
            }
            temphtml.append("</div>");
            html3.append(Js.setHtml("#swTopNewsDiv", temphtml.toString()));
            String script = "$('#" + panel + "').html(\"" + temphtml.toString() + "\");\n";
//        html2 += Js.table("#sss", height, sort, "");

            //============ BY RASHIDI ========>
            //is not by Ajax
            boolean senderIsMyJs = jjTools.getParameter(request, "jj").equals("1");
            if (!senderIsMyJs) {
                return temphtml.toString();
//                String address = request.getServletContext().getRealPath("/");
//                File file = new File(address + "/" + Server.mainPage);
//                StringBuilder html4 = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
//                if (!file.exists()) {
//                    return html2 + html3;
//                } else {
//                    int index = html4.indexOf("<title");//finding title tag
//                    if (index > -1) {
//                        index = html4.indexOf(">", index); //finding title tag ( <title> )
//                        title = "اخبار" + ", ";
//                        html4 = html4.insert(index + 1, title);
//                    }
//                    //replace content in <div id="sw"> ... </div>...........
//                    index = html4.indexOf("\"sw\"");//finding div in wich id="sw"
//                    if (index > -1) {
//                        index = html4.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
//                        html4 = html4.insert(index + 1, temphtml);
//                    }
//                    //......................................................
//                }
//                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + html.toString());
//                return html4.toString();
            }
            //is not by Ajax
            //<============ BY RASHIDI ========

            return script + title;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + id));
            html.append("<a onclick='swGetNewsCategory();' class='newsLink'>");
            html.append(jjTools.isLangFa(request) ? "اخبار" : "News");
            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span>" + rowCategory.get(0).get(Category_News._title) + "<br/><br/>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id));
            html.append("<table id='refreshNews22' class='tahoma10' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ خبر</th>");
            html.append("<th width='80%'>عنوان خبر</th>");

            html.append("<th width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            int counter = 0;
            for (int i = 0; i < row.size(); i++) {
                counter += 1;
                html.append("<tr  onclick='swNews(").append(row.get(i).get(_id)).append(");' class='mousePointer'>");
                html.append("<td class='tahoma10 c'>").append(counter).append("</td>");
                html.append("<td class='tahoma10'>").append(jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString())).append("</td>");
                html.append("<td class='tahoma10 r'>").append(row.get(i).get(_title).toString()).append("</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshNews22", height, Integer.parseInt(sort), "", "لیست اخبار");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String showNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            int visit = Integer.parseInt(row.get(0).get(_visit).toString()) + 1;
            map.put(_visit, visit);
            db.update(tableName, map, _id + "=" + id);
            List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + row.get(0).get(News._id)));

            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(_category_id)));
            System.out.println("//////////////////////////////////////" + rowCategory.size());
            html.append(" <div class='col-md-12'>\n"
                    + "                <div class='post-wrap'>\n"
                    + "                    <article class='post clearfix'>\n"
                    + "                        <div class='featured-post'>\n"
                    + "                            <img src='upload/" + row.get(0).get(_pic) + "' alt='image'>                      \n"
                    + "                        </div>\n"
                    + "                        <div class='content-post'>\n"
                    + "                            <h2 class='title-post'><a href=''>" + row.get(0).get(_title) + "</a></h2>                      \n"
                    + "                            <div class='entry excerpt'>\n"
                    + "                                <p>" + row.get(0).get(_abstract) + "</p>                          \n"
                    + "                            </div>         \n"
                    + "                        </div>\n"
                    + "                    </article>");

            html.append("            </div></div>\n");

            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String showInfo(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            int visit = Integer.parseInt(row.get(0).get(_visit).toString()) + 1;
            map.put(_visit, visit);
            db.update(tableName, map, _id + "=" + id);
            List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + row.get(0).get(News._id)));

            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(_category_id)));
            System.out.println("//////////////////////////////////////" + rowCategory.size());
            html.append("<section class='main-content blog-posts blog-single' style='background:#fff'>\n"
                    + "    <div class='container'>\n"
                    + "        <div class='row'>\n"
                    + "            <div class='col-md-8'>\n"
                    + "                <div class='post-wrap'>\n"
                    + "                    <article class='post clearfix'>\n"
                    + "                        <div class='featured-post'>\n"
                    + "                            <img src='upload/" + row.get(0).get(_pic) + "' alt='image'>                      \n"
                    + "                        </div>\n"
                    + "                        <div class='content-post'>\n"
                    + "                            <h2 class='title-post'><a href=''>" + row.get(0).get(_title) + "</a></h2>                      \n"
                    + "                            <div class='entry excerpt'>\n"
                    + "                                <p>" + row.get(0).get(_abstract) + "</p>                          \n"
                    + "                            </div>         \n"
                    + "                        </div>\n"
                    + "                    </article>");

            html.append("            </div></div>\n");
            html.append(" <div class='col-md-4'>  <div class='sidebar'>\n"
                    + "            \n"
                    + "                    <div class='widget widget-recent-news'>\n"
                    + "                        <h5 class='widget-title'>اطلاعیه</h5><ul class='popular-news clearfix'>\n"
            );
            for (int i = 0; i < rowCategory.size(); i++) {
                html.append("<li onclick='loadinformation(" + rowCategory.get(i).get(_id) + ")'>\n"
                        + "                                <div class='thumb'>\n"
                        + "                                    <img src='upload/" + rowCategory.get(i).get(_pic) + "' alt='image' style='height:50px;width:50px'>\n"
                        + "                                </div>\n"
                        + "                                <div class='text'>\n"
                        + "                                    <h6>\n"
                        + "                                        <a href='#'>" + rowCategory.get(i).get(_title) + "</a>\n"
                        + "                                    </h6>\n"
                        + "                                </div>\n"
                        + "                            </li>\n");
            }
            html.append("            </ul>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div></div></div></section>");

            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            if (jjTools.isLangEn(request)) {
//                return sw_En(request, db, isPost);
//            }
//            if (jjTools.isLangAr(request)) {
//                return sw_Ar(request, db, isPost);
//            }
            String lang = jjTools.getLangNum(request);
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));

            String id = jjTools.getParameter(request, "id").trim();
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> rowContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (rowContent.size() > 0) {
                    StringBuilder html = new StringBuilder();

                    List<Map<String, Object>> rowParent = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + rowContent.get(0).get(_category_id)));
                    String persianTitle = "Unknown";
                    if (rowParent.size() > 0) {
                        persianTitle = rowParent.get(0).get(Category_News._title).toString();
//                        if (!jjTools.isLangFa(request)) {
//                            rowParent = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._parent + "=" + rowContent.get(0).get(_category_id)));
//                        }
                        html.append("<a onclick='swGetNewsCategory();' class='newsLink'>");
                        html.append(langSetting.get(0).get(Language._news));
                        if (rowParent.size() > 0) {
                            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                    + rowParent.get(0).get(_parent) + ");' class='newsLink'>");
                            html.append(rowParent.get(0).get(Category_News._title));
                        } else {
                            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                    + rowContent.get(0).get(_category_id)
                                    + ");' class='newsLink'>");
                            html.append(persianTitle);
                        }
                    }
                    html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><span class='newsLink'>");
                    html.append(rowContent.get(0).get(_title).toString() + "</span><br/><br/><span class='newsContent'>" + rowContent.get(0).get(_content).toString() + "</span>");
                    return Js.setHtml("#" + panel, html.toString());
                }
            }
            String errorMessage = "رکوردی با این محتوا وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "wiki Text News Fail;";
            }
            return Js.setHtml("#" + panel, errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getListForApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuilder html3 = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
//            String catId = jjTools.getParameter(request, _category_id);
            //to incriment visited time
            List<Map<String, Object>> topNewsRow = jjDatabase.separateRow(db.Select(tableName));
            if (topNewsRow.isEmpty()) {
                return "";
            }
            html.append("<div id='swTopNewsDiv'></div>");
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            for (int i = 0; i < topNewsRow.size(); i++) {
                String id = topNewsRow.get(i).get(_id).toString();
                temphtml.append("<div  onclick='getSiteOneNews(" + id + ");' class='newsmainDiv'>");
                temphtml.append("<div><img class='newsPicDiv' src='" + Server.siteName + "/" + topNewsRow.get(i).get(_pic).toString() + "'/></div>");
                temphtml.append("<div class='newsTitlespan'><span>" + topNewsRow.get(i).get(_title).toString() + "</span></div>");
                temphtml.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(topNewsRow.get(i).get(_date).toString()) + "</span>");
                int visit = new Integer(topNewsRow.get(i).get(_visit).toString());
                if (visit >= 0) {
                    temphtml.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
                }
                int disLikes = new Integer(topNewsRow.get(i).get(_disLikes).toString());
                if (disLikes >= 0) {
                    temphtml.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + "  </div>");
                }
                int likes = new Integer(topNewsRow.get(i).get(_likes).toString());
                if (likes >= 0) {
                    temphtml.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + "  </div>");
                }
                temphtml.append("<div class='newsabstarctDiv'><h5>" + topNewsRow.get(i).get(_abstract).toString() + "</h5></div>");
                temphtml.append("</div>");
            }
            html3.append(Js.setHtml("#swTopNewsDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').append(\"" + html.toString() + "\");\n";
//            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";

            return html2 + html3;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOneNewsForApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            //to incriment visited time
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
            List<Map<String, Object>> row;
            row = jjDatabase.separateRow(db.Select(News.tableName, _id + " = " + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            html.append("<div class='oneNewsmainDiv'>");
            html.append("<div class='oneNewsImgDiv'><img class='oneNewsImg' src='" + Server.siteName + "/" + row.get(0).get(_pic).toString() + "'/></div>");
            html.append("<span class='oneNewsTitlespan'><h3>" + row.get(0).get(_title).toString() + "</h3></span>");
            html.append("<span class='oneNewsDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
            int visit = new Integer(row.get(0).get(_visit).toString());
            if (visit >= 0) {
                html.append("<div class='oneNewsvisitDiv'>" + visit + " بار مشاهده </div>");
            }
            html.append("<div class='oneNewsabstarctDiv'>" + row.get(0).get(_abstract).toString() + "</div>");
            html.append("<div id='oneNewContentDiv' class='oneNewContentDiv'></div>");
            int likes = new Integer(row.get(0).get(_likes).toString());
            if (likes >= 0) {
                html.append("<div class='oneNewslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " </div>");
            }
            int disLikes = new Integer(row.get(0).get(_disLikes).toString());
            if (disLikes >= 0) {
                html.append("<div class='oneNewsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " </div>");
            }
            html.append("<div class='newsShareDiv' onclick=\\\"socialShareLink('" + row.get(0).get(_title).toString() + "','','','" + Server.siteName + "/Server?do=News.getOneNews&id=" + row.get(0).get(_id).toString() + "');\\\"></div>");
            html.append("<span class='oneMoreDatale'>"
                    + "<a href='Server?do=News.getList&id=" + row.get(0).get(_category_id).toString() + "' onclick='swGetNews(" + row.get(0).get(_category_id).toString() + ");return false;'>" + " مطالب مرتبط" + "</a>"
                    + "</span>");
            html.append("</div>");

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.setHtml("#oneNewContentDiv", row.get(0).get(_content).toString());

            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * By ebrahemi search width packages 1399/04/09
     */
    public static String SearchNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String title = "نتایج جستجو";
            String text = jjTools.getParameter(request, "text");
            String panel = "sw";
            List<Map<String, Object>> rowNews = jjDatabase.separateRow(db.Select(tableName, _title + " LIKE '%" + text + "%'"));
            if (rowNews.isEmpty()) {
                html.append("<h6 style='text-align:center'>نتیجه ای دریافت نشد</h6>");
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()));
                return "";
            } else if (!rowNews.isEmpty()) {
                Map<String, Object> map = new HashMap<>();
                int visit = Integer.parseInt(rowNews.get(0).get(News._visit).toString()) + 1;
                map.put(News._visit, visit);
                db.update(tableName, map, News._id + "=" + rowNews.get(0).get(News._id).toString());
                List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + rowNews.get(0).get(News._id)));

                List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(News.tableName, News._category_id + "=" + rowNews.get(0).get(News._category_id)));
                for (int i = 0; i < rowNews.size(); i++) {
                    jjCalendar_IR dateLable = new jjCalendar_IR(rowNews.get(i).get(News._date).toString());
                    String month = dateLable.getMonthName();
                    int day = dateLable.getDay();
                    int year = dateLable.getYear();
                    html.append("<div class=\"post-wrap\">\n"
                            + "                                                            <article class=\"post clearfix\">\n"
                            + "                                                                <div class=\"featured-post\">\n"
                            + "                                                                    <img src='upload/" + rowNews.get(i).get(News._pic) + "' alt=\"image\" style=\"width: 100%;height: 400px\">\n"
                            + "                                                                        <ul class=\"post-comment\">\n"
                            + "                                                                            <li class=\"date\">\n"
                            + "                                                                                <span class=\"day\">" + day + "</span>\n"
                            + "                                                                            </li>\n"
                            + "                                                                            <li class=\"comment\">\n"
                            + "                                                                                " + month + "\n"
                            + "                                                                            </li>\n"
                            + "                                                                        </ul>\n"
                            + "                                                                </div>\n"
                            + "                                                                <div class=\"content-post\">\n"
                            + "                                                                    <h2 class=\"title-post\"><a href=\"\">" + rowNews.get(i).get(News._title) + "></a></h2>\n"
                            + "                                                                    <ul class=\"meta-post clearfix\">\n"
                            + "                                                                        <li class=\"author\">\n"
                            + "                                                                            <a href=\"#\">admin</a>\n"
                            + "                                                                        </li>\n"
                            + "\n"
                            + "                                                                        <li class=\"vote\">\n"
                            + "                                                                            <a href=\"#\">" + rowNews.get(i).get(News._visit) + "</a>\n"
                            + "                                                                        </li>\n"
                            + "                                                                    </ul>\n"
                            + "                                                                    <div class=\"entry-post excerpt\">\n"
                            + "                                                                        <p>" + rowNews.get(i).get(News._content) + "</p>\n"
                            + "                                                                    </div>\n"
                            + "                                                                </div>\n"
                            + "                                                            </article>\n"
                            + "                                                        </div>");
                }
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()));
                return "";
            }

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
        return "";
    }

    //گرفتن گروه ها و قرار دادن در سلکت تو
    public static String getGroups(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_Group.tableName));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "news_privateGroupId" : panel;
        html.append("<option id='0' value='0'>ALL</option>");
        if (row.size() > 0) {
            for (int i = 0; i < row.size(); i++) {
                html.append("<option id='" + row.get(i).get(Access_Group._id)
                        + "'  value='" + row.get(i).get(Access_Group._id) + "'>"
                        + row.get(i).get(Access_Group._title)
                        + "</option>");
            }
        }
        Server.outPrinter(request, response, Js.setHtml("#" + panel, html + Js.select2(panel, "width: '100%'")));
        return "";
    }

    //گرفتن یوزر ها و قرار دادن در سلکت تو
    public static String getUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_User.tableName));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "news_privateUserId" : panel;
        html.append("<option id='0' value='0'>ALL</option>");
        if (row.size() > 0) {
            for (int i = 0; i < row.size(); i++) {
                html.append("<option id='" + row.get(i).get(Access_User._id)
                        + "'  value='" + row.get(i).get(Access_User._id) + "'>"
                        + row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString()
                        + "</option>");
            }
        }
        Server.outPrinter(request, response, Js.setHtml("#" + panel, html + Js.select2(panel, "width: '100%'")));
        return "";
    }

    public static String getNewsInfo(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String panel = jjTools.getParameter(request, "panel");
            html.append("    <div class=\"post-wrap item electric\" >");
            jjCalendar_IR dateLable = new jjCalendar_IR(row.get(0).get(_date).toString());
            String month = dateLable.getMonthName();
            int day = dateLable.getDay();
            int year = dateLable.getYear();
            html.append("<article class='post clearfix'>"
                    + "    <div class='featured-post'>\n"
                    + "        <img src='upload/" + row.get(0).get(_pic) + "' alt='' width='100%'>\n"
                    + "            <ul class='post-comment'>"
                    + "                <li class='date'>"
                    + "                    <span class='day'>" + day + "</span>"
                    + "                </li>"
                    + "                <li class='comment'>"
                    + "                    " + month + ""
                    + "                </li>"
                    + "            </ul>"
                    + "    </div>"
                    + "    <div class='content-post'>\n"
                    + "        <h2 class='title-post'><a href=''>" + row.get(0).get(_title) + "</a></h2>                                                                   \n"
                    + "        <div class='entry-post excerpt'>\n"
                    + "            <p>" + row.get(0).get(_content) + "</p>"
                    + "        </div>"
                    + "    </div>"
                    + "</article>");
            html.append(" </div> ");
            Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
