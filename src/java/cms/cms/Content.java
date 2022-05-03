package cms.cms;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Content {
    
    public static String tableName = "content";
    public static String _id = "id";
    public static String _title = "content_title";
    public static String _content = "content_content";
    public static String _parent = "content_parent";
    public static String _lang = "content_lang";
    public static String _privateGroupId = "content_privateGroupId";
    public static String _privateUserId = "content_privateUserId";
    public static String _link = "content_link";
    public static String _hasLink = "content_has_link";//====== BY RASHIDI ======برای این محتوا لینک در محتوا ساخته شود یا خیر
    public static String _tags = "content_tags";//====== BY RASHIDI ======
    public static String _titleTag = "content_titleTag";// این تگ ها برای سئو استفاده میشود
    public static String _description = "content_description";
    public static String _headerTags = "content_headerTags";
    public static String _style = "content_style";
    public static String _script = "content_script";
    public static String _autoWikIsUpdate = "content_autoWikIsUpdate";//برای اینکه بفهمیم که اتوویکی زخیره شده نیاز به بروز رسانی دارد یا نه
    public static String _isAjax = "content_isAjax";
    public static String _hasInContentAutoWiki = "content_hasInContentAutoWiki";//====== BY Mohammad======در این محتوا لینک های اتوماتیک وجود داشته باشد یا نه
    public static String _date = "content_date";
    public static String _contentWithWikiLinks = "content_contentWithWikiLinks";//برای ذخیره ی محتوا با حالت اتوویکی بعد از اعمال اتوویکی محتوا در این فیلد ذخیره میشود
    public static String _priority = "content_priority";//اولیت برای نمایش
    public static String _category_id = "content_category_id";//دسته بندی برای محتوای سایت هم بد نیست داشته باشیم
    public static String _pic = "content_pic";//تصویر کوچک برای لینک دهی در شبکه های اجتماعی و یا اینکه در قسمت های سایت
    public static String _rating = "content_rating";//رتبه ای کاربران داده اند
    public static String _visit = "content_visit";//تعداد بازدید ها
    public static String _likes = "content_likes";//تعداد لایک ها
    public static String _disLikes = "content_disLikes";//تعداد مخالف ها
    public static String _userCommensIsActive = "content_userCommentsIsActive";//کامنت گذاری کاربران فعال باشد
    public static String _target = "content_target";//در چه صفحه ای باز شود، همان صفحه یا صفحه ی دیگر که برای اتو ویکی غیر فعالی میشود
    public static String _isEditableOnlyByOwner = "content_isEditableOnlyByOwner";//برای قالب ها که فقط توسط ایجاد کننده قابل ویرایش و حذف باشد
    public static String _ownerId = "content_ownerId";//برای قالب ها که فقط توسط ایجاد کننده قابل ویرایش و حذف باشد
    public static String _explain = "content_explain";
    public static String _content_page = "content_content_page";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
//    public static int rul_rfs = 1;
//    public static int rul_ins = 2;
//    public static int rul_edt = 3;
//    public static int rul_dlt = 4;
//    public static int rul_lng = 5;

    public static int rul_mudulcontent = 270;
    public static int rul_rfsAll = 276;
    public static int rul_rfs = 271;
    public static int rul_ins = 272;
    public static int rul_edt = 273;
    public static int rul_dlt = 274;
    public static int rul_prt = 275;
    public static int rul_lng2 = 0;//====== BY RASHIDI ======
    public static int rul_lng3 = 0;//====== BY RASHIDI ======
    public static int rul_lng4 = 0;//====== BY RASHIDI ======
    public static int rul_lng5 = 0;//====== BY RASHIDI ======
//    public static int rul_rfs = 21;
//    public static int rul_ins = 22;
//    public static int rul_edt = 23;
//    public static int rul_dlt = 24;
//    public static int rul_lng2 = 25;//====== BY RASHIDI ======
//    public static int rul_lng3 = 26;//====== BY RASHIDI ======
//    public static int rul_lng4 = 27;//====== BY RASHIDI ======
//    public static int rul_lng5 = 28;//====== BY RASHIDI ======
//    public static int rul_reserved = 29 --- 40;// RESERVED : 29 -- 40
    ///**************LANGUAAGE ADDED*******>
//    public static int rul_lng2 = 25;//====== BY RASHIDI ======
//    public static int rul_lng3 = 26;//====== BY RASHIDI ======
//    public static int rul_lng4 = 27;//====== BY RASHIDI ======
//    public static int rul_lng5 = 28;//====== BY RASHIDI ======
    public static String lbl_add_ln2 = "افزودن زبان انگلیسی";//====== BY RASHIDI ======
    public static String lbl_edit_ln2 = "ویرایش بخش انگلیسی";//====== BY RASHIDI ======
    public static String lbl_add_ln3 = "افزودن زبان عربی";//====== BY RASHIDI ======
    public static String lbl_edit_ln3 = "ویرایش بخش عربی";//====== BY RASHIDI ======
    public static String lbl_add_ln4 = "افزودن زبان آلمانی";//====== BY RASHIDI ======
    public static String lbl_edit_ln4 = "ویرایش بخش آلمانی";//====== BY RASHIDI ======
    public static String lbl_add_ln5 = "افزودن زبان چینی";//====== BY RASHIDI ======
    public static String lbl_edit_ln5 = "ویرایش بخش چینی";//====== BY RASHIDI ======
    ///<**************LANGUAAGE ADDED*******

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
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
            DefaultTableModel dtm;
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
//                dtm = db.Select(tableName, _id + "," + _title, _ownerId+"="+jjTools.getSeassionUserId(request));
                dtm = db.otherSelect("SELECT content.id," + _title + "," + _ownerId + "," + Access_User._name + "," + Access_User._family + " FROM " + Content.tableName
                        + " LEFT JOIN " + Access_User.tableName + " ON " + _ownerId + " = access_user.id WHERE " + _ownerId + " = " + jjTools.getSeassionUserId(request) + ";");
            } else {
//                dtm = db.Select(tableName, _id + "," + _title);
                dtm = db.otherSelect("SELECT " + tableName + "." + _id + "," + _title + "," + _ownerId + "," + Access_User._name + "," + Access_User._family + " FROM " + Content.tableName
                        + " LEFT JOIN " + Access_User.tableName + " ON " + _ownerId + " = access_user.id ;");
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت محتوا</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت محتوا</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsContent.m_add_new();' > محتوای جدید</a>"
                        + "        </p>");
            }
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshContent' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th style='text-align: center;' width='10%' >کد</th>");
            html.append("<th style='text-align: center;' width='30%'>عنوان</th>");
            html.append("<th style='text-align: center;' width='40%'>نام و نام خانوادگی</th>");
            html.append("<th style='text-align: center;' width='20%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr onclick='$(this).html().print();' class='mousePointer'>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
                html.append("<tr onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='tahoma10 c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='tahoma10 r' >" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='tahoma10 c' >" + (row.get(i).get(Access_User._name).toString()) + " " + (row.get(i).get(Access_User._family).toString()) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div></div></div>");
            String height = "auto";
//            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshContent", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست محتویات سایت");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //جدول بسته های آموزشی مدیریت خطر و حوادث و بلایا را نمایش میدهد
    public static String refreshLearningDangerManagment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(Category_Content.tableName, _id + "=21");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            DefaultTableModel dtm1 = db.Select(tableName, _category_id + "=21");
            List<Map<String, Object>> row1 = jjDatabase.separateRow(dtm1);
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>بسته های آموزشی مدیریت خطر و حوادث و بلایا</div>"
                    + "    <div class='card-body pd-sm-30'>");
            html.append("<table id='refreshLearningDangerManagmentTbl' class='table display responsive nowrap' dir='rtl' style='width: 100%;'><thead>");
            html.append("<th style='text-align: center;' width='10%'>کد</th>");
            html.append("<th style='text-align: center;' width='40%'>عنوان</th>");
            html.append("<th style='text-align: center;' width='40%'>دسته</th>");
            html.append("<th style='text-align: center;' width='10%'>مشاهده</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row1.size(); i++) {
                html.append("<tr>");
                html.append("<td class='tahoma10 c' >" + (row1.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='tahoma10 c' >" + (row1.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='tahoma10 c' >" + (row.get(i).get(Category_Content._title).toString()) + "</td>");
                html.append("<td class='tahoma10 c' style='cursor: pointer;'><a href='content.jsp?id=" + row1.get(i).get(_id) + "' target='_blank'><i class='fa fa-desktop'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            String height = "auto";
//            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshLearningDangerManagmentTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست محتویات سایت");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //جدول بسته های آموزشی مدیریت خطر و حوادث و بلایا را نمایش میدهد
    public static String refreshLearningErrorAndDangerManagment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(Category_Content.tableName, _id + "=22");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            DefaultTableModel dtm1 = db.Select(tableName, _category_id + "=22");
            List<Map<String, Object>> row1 = jjDatabase.separateRow(dtm1);
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>بسته های آموزشی مدیریت خطا وخطر</div>"
                    + "    <div class='card-body pd-sm-30'>");
            html.append("<table id='refreshLearningErrorAndDangerManagmentTbl' class='table display responsive nowrap' dir='rtl' style='width: 100%;'><thead>");
            html.append("<th style='text-align: center;' width='10%'>کد</th>");
            html.append("<th style='text-align: center;' width='40%'>عنوان</th>");
            html.append("<th style='text-align: center;' width='40%'>دسته</th>");
            html.append("<th style='text-align: center;' width='10%'>مشاهده</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row1.size(); i++) {
                html.append("<tr>");
                html.append("<td class='tahoma10 c' >" + (row1.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='tahoma10 c' >" + (row1.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='tahoma10 c' >" + (row.get(i).get(Category_Content._title).toString()) + "</td>");
                html.append("<td class='tahoma10 c' style='cursor: pointer;'><a href='content.jsp?id=" + row1.get(i).get(_id) + "' target='_blank'><i class='fa fa-desktop'></i></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div>");
            String height = "auto";
//            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshLearningErrorAndDangerManagmentTbl", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست محتویات سایت");
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
                html.append(Js.setHtml("#Content_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjContent.insert() + "' id='insert_Content'>" + lbl_insert + "</button>"));
            }
            String script = "$('#tags_name').keyup(function () {\n"
                    + "                    if ($(\"#tags_name\").val() === \"\") {\n"
                    + "                        $(\"#content_search_tags_result\").hide();\n"
                    + "                    }\n"
                    + "                    cmsContent.m_searchTags();\n"
                    + "                });";
            Server.outPrinter(request, response, html.toString() + script);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String getProjectInfo(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String panel = jjTools.getParameter(request, "panel");
            html.append("    <div class=\"post-wrap item electric\">");
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
    
    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            String needToAutoWiki = jjTools.getParameter(request, _hasInContentAutoWiki);
            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _content), db, false);
                map.put(_contentWithWikiLinks, autoWikeContent);
                map.put(_autoWikIsUpdate, "1");//خود این محتوا آپدیت است
            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
                map.put(_contentWithWikiLinks, jjTools.getParameter(request, _content));
                map.put(_autoWikIsUpdate, "0");//خود این محتوا آپدیت نیست
            }
            if ("1".equalsIgnoreCase(jjTools.getParameter(request, _hasLink))) {//اگر این محتوا باید اتوویکی بشود بنابر این باید اتوویکی همه ی محتواها آپدیت بشوند
                db.otherStatement("Update " + tableName + " SET " + _autoWikIsUpdate + "=0 WHERE " + _hasInContentAutoWiki + "=1");//فقط آنهایی که در محتوایشان اتو ویکی دارند
                //@ToDo برای جدوال اخبار و محتوا هم همین اتفاق باید باشد
            }
            map.put(_privateGroupId, request.getParameter(_privateGroupId));
            map.put(_titleTag, jjTools.getParameter(request, _titleTag));
//            if (request.getParameter(_privateGroupId).equals("")) {
//                map.put(_privateGroupId, 0);
//            } else {
//                map.put(_privateGroupId, request.getParameter(_privateGroupId));
//            }
            map.put(_privateUserId, request.getParameter(_privateUserId));
//            if (request.getParameter(_privateUserId).equals("")) {
//            } else {
//                map.put(_privateUserId, request.getParameter(_privateUserId));
//            }
            map.put(_isAjax, jjTools.getParameter(request, _isAjax));
            map.put(_priority, jjTools.getParameter(request, _priority));
            String lang = request.getParameter(_lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            map.put(_category_id, jjTools.getParameter(request, _category_id));
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
            map.put(_target, jjTools.getParameter(request, _target));
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_priority, jjTools.getParameter(request, _priority));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), false));
            map.put(_style, jjTools.getParameter(request, _style));
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_headerTags, jjTools.getParameter(request, _headerTags));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_content, jjTools.getParameter(request, _content).trim());
            map.put(_title, jjTools.getParameter(request, _title).trim());
            map.put(_tags, jjTools.getParameter(request, _tags));
            map.put(_content_page, jjTools.getParameter(request, _content_page));
            map.put(_pic, jjTools.getParameter(request, "content_attachPic"));
            map.put(_userCommensIsActive, jjTools.getParameter(request, _userCommensIsActive));
            map.put(_isEditableOnlyByOwner, jjTools.getParameter(request, _isEditableOnlyByOwner));
            map.put(_ownerId, jjTools.getSeassionUserId(request));
            map.put(_explain, jjTools.getParameter(request, _explain));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
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
    
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            resetCatchContentTitle(request, _content, db, isFromClient);
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            String userId = String.valueOf(jjTools.getSeassionUserId(request));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if ("1".equalsIgnoreCase(row.get(0).get(_isEditableOnlyByOwner).toString()) && !userId.equalsIgnoreCase(row.get(0).get(_ownerId).toString())) {
                return Js.dialog("این محتوا فقط توسط ایجاد کننده قابل ویرایش است");
            }
            
            resetCatchContentTitle(request, _content, db, isFromClient);
            Map<String, Object> map = new HashMap();
            System.out.println(jjTools.getParameter(request, _hasInContentAutoWiki));
            String needToAutoWiki = jjTools.getParameter(request, _hasInContentAutoWiki);
            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _content), db, false);
                map.put(_contentWithWikiLinks, autoWikeContent);
            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
                map.put(_contentWithWikiLinks, jjTools.getParameter(request, _content));
                map.put(_autoWikIsUpdate, "1");//اینجا این محتوا بروز شد باید در موقع فراخوانی از سمت کاربر نهایی هم همین کار را بکنیم
            }
            //---------------
            if (!row.get(0).get(_title).equals(jjTools.getParameter(request, _title))) {
                if ("1".equalsIgnoreCase(jjTools.getParameter(request, _hasLink))) {//اگر این محتوا باید اتوویکی بشود بنابر این باید اتوویکی همه ی محتواها آپدیت بشوند
                    //اگر عنوان تغییر نکرده بود نیازی به آپدیت  اتو ویکی های سایر محتوا ها نیست
                    if (!jjTools.getParameter(request, _title).equalsIgnoreCase(row.get(0).get(_title).toString())) {
                        db.otherStatement("Update " + tableName + " SET " + _autoWikIsUpdate + "=0 WHERE " + _hasInContentAutoWiki + "=1");//فقط آنهایی که در محتوایشان اتو ویکی دارند
                    }
                    //@ToDo برای جدوال اخبار و محتوا هم همین اتفاق باید باشد
                }
            }
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_content, jjTools.getParameter(request, _content).trim());
            map.put(_titleTag, jjTools.getParameter(request, _titleTag));
            map.put(_privateGroupId, request.getParameter(_privateGroupId));
            map.put(_privateUserId, request.getParameter(_privateUserId));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_headerTags, jjTools.getParameter(request, _headerTags));
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_isAjax, jjTools.getParameter(request, _isAjax));
            map.put(_content_page, jjTools.getParameter(request, _content_page));
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            map.put(_priority, jjTools.getParameter(request, _priority));
            map.put(_category_id, jjTools.getParameter(request, _category_id));
            map.put(_pic, jjTools.getParameter(request, "content_attachPic"));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_likes, jjTools.getParameter(request, _likes));
            map.put(_explain, jjTools.getParameter(request, _explain));
            map.put(_disLikes, jjTools.getParameter(request, _disLikes));
            map.put(_userCommensIsActive, jjTools.getParameter(request, _userCommensIsActive));
            map.put(_target, jjTools.getParameter(request, _target));
            map.put(_isEditableOnlyByOwner, jjTools.getParameter(request, _isEditableOnlyByOwner));
            map.put(_ownerId, jjTools.getSeassionUserId(request));
            map.put(_title, jjTools.getParameter(request, _title).trim());
            map.put(_hasLink, jjTools.getParameter(request, _hasLink));//============ BY RASHIDI ========
            map.put(_tags, jjTools.getParameter(request, _tags));//============ BY RASHIDI ========
            map.put(_style, jjTools.getParameter(request, _style));//============ BY RASHIDI ========

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
            resetCatchContentTitle(request, _content, db, isFromClient);
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
            if (!row.isEmpty()) {
                String[] list = new File(request.getServletContext().getRealPath("/upload")).list();
            }
            if (!db.delete(tableName, _id + "=" + id + " OR " + _parent + " = " + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            db.delete(tableName, _parent + "=" + id);
            Server.outPrinter(request, response, Js.jjContent.refresh());
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
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
            StringBuilder script = new StringBuilder();
            
            script.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                script.append(Js.setVal("#" + _title, title));
            }
            script.append(Js.setVal("#" + _titleTag, row.get(0).get(_titleTag)));
            script.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            script.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            script.append(Js.setValSelectOption("#" + _privateUserId, row.get(0).get(_privateUserId).toString()));
            script.append(Js.select2("#" + _privateUserId, ""));
            script.append(Js.setValSelectOption("#" + _privateGroupId, row.get(0).get(_privateGroupId).toString()));
            script.append(Js.select2("#" + _privateGroupId, ""));
            script.append(Js.setVal("#" + _description, row.get(0).get(_description)));
            script.append(Js.setVal("#" + _headerTags, row.get(0).get(_headerTags)));
            script.append(Js.setVal("#" + _style, row.get(0).get(_style)));
            script.append(Js.setVal("#" + _explain, row.get(0).get(_explain)));
            script.append(Js.setVal("#" + _content_page, row.get(0).get(_content_page)));
            script.append(Js.setVal("#" + _script, row.get(0).get(_script)));
            script.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            script.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            script.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            script.append(Js.setVal("#content_attachPic", row.get(0).get(_pic)));
            script.append(Js.setSrc("#Pic", row.get(0).get(_pic)));
            script.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date))));
            script.append(Js.setValSummerNote("#" + _content, row.get(0).get(_content)));
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            script.append(Js.setVal("#" + _visit, visit));
            
            if (visit < 0) {//That means it is disabled now and it has been enabled
                script.append(Js.setVal("#" + _visit + "_checkbox", 0));
                script.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                script.append(Js.setVal("#" + _visit + "_checkbox", 1));
                script.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            Integer likes = Integer.valueOf(row.get(0).get(_likes).toString());
            script.append(Js.setVal("#" + _likes, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                script.append(Js.setVal("#" + _likes + "_checkbox", 0));
                script.append(Js.setAttr("#" + _likes, "disabled", "true"));
            } else {
                script.append(Js.setVal("#" + _likes + "_checkbox", 1));
                script.append(Js.removeAttr("#" + _likes, "disabled"));
            }
            Integer dislikes = Integer.valueOf(row.get(0).get(_disLikes).toString());
            script.append(Js.setVal("#" + _disLikes, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                script.append(Js.setVal("#" + _disLikes + "_checkbox", 0));
                script.append(Js.setAttr("#" + _disLikes, "disabled", "true"));
            } else {
                script.append(Js.setVal("#" + _disLikes + "_checkbox", 1));
                script.append(Js.removeAttr("#" + _disLikes, "disabled"));
            }
            
            script.append(Js.setVal("#" + _userCommensIsActive, row.get(0).get(_userCommensIsActive)));
            script.append(Js.setVal("#" + _target, row.get(0).get(_target)));
            script.append(Js.setVal("#" + _isEditableOnlyByOwner, row.get(0).get(_isEditableOnlyByOwner)));
            script.append(Js.setVal("#" + _ownerId, row.get(0).get(_ownerId)));
            
            script.append(Js.setVal("#" + _isAjax, row.get(0).get(_isAjax)));
            script.append(Js.setHtml("#" + _link, "Server?do=Content.sw&text=" + title));
            script.append(Js.setVal("#" + _hasLink, row.get(0).get(_hasLink)));
            String tagsHtml = "";
            String tagsString = "";
            if (!row.get(0).get(_tags).toString().equals("")) {
                tagsString = row.get(0).get(_tags).toString();
                String[] tags = tagsString.split(",");
                for (int i = 0; i < tags.length; i++) {
                    ServerLog.Print(i + " " + tags[i]);
                    tagsHtml += "<span id='contetn_tag_span" + i + "' calss='tags' onclick='deleteContentTag(" + i + ");'>" + tags[i] + ",</span> ";
                }
            }
            script.append(Js.setHtml("#content_tags_div", tagsHtml));
            String userId = String.valueOf(jjTools.getSeassionUserId(request));
            //اگر این محتوا فقط توسط مالک قابل تغییر بود و کاربر جاری عیر از ایجاد کننده بود
            if ("1".equalsIgnoreCase(row.get(0).get(_isEditableOnlyByOwner).toString()) && !userId.equalsIgnoreCase(row.get(0).get(_ownerId).toString())) {
                script.append(Js.setHtml("#Content_button", "."));
            } else {
                String htmlBottons = "";
                if (Access_User.hasAccess(request, db, rul_edt)) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjContent.edit() + "' id='edit_Content'>" + lbl_edit + "</button></div>";
                }
                if (Access_User.hasAccess(request, db, rul_dlt)) {
                    htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjContent.delete(id) + "' id='delete_Content'>" + lbl_delete + "</button></div>";
                }
                script.append(Js.setHtml("#Content_button", htmlBottons));
            }
            
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
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
            StringBuilder html2 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 2));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type=\"button\" id=\"insert_content_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_content_new", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
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
            StringBuilder html2 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 3));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type='button' id='insert_content_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_content_new_ar", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//============ BY RASHIDI ========>
////// ------------- add_lang() ------------->

    public static String add_lang(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String lang = jjTools.getParameter(request, "myLang");
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
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, lang));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Content_button", "<input type='button' id='insert_content_new_lang' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_content_new_lang", Js.jjContent.insert()));
                html.append(Js.hide("#Content_Language_button"));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//<============ BY RASHIDI ========
////// <------------- add_lang() -------------

    public static String getTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=" + (jjTools.isLangFa(request) ? 1 : 2)));
            String panel = jjTools.getParameter(request, "panel");
            html.append("<table  class='mousePointer' dir='" + jjTools.getLangDir(request) + "' style='width:100%'>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr style='text-align: right;height:25px'><td><a onclick='sw(" + (row.get(i).get(_id)) + ");return false;'"
                        + " href='Server?do=Content.sw&text=" + (row.get(i).get(_id)) + "' >"
                        + (row.get(i).get(_title)) + "</a></td></tr>");
            }
            html.append("</table>");
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String getSomeTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String exept = jjTools.getParameter(request, "exept");
            exept = exept.equals("") ? "خانه،درباره ما، تماس با ما، محصولات" : exept;
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=" + (jjTools.isLangFa(request) ? 1 : 2)));
            String panel = jjTools.getParameter(request, "panel");
            for (int i = 0; i < row.size(); i++) {
                if (!exept.toString().contains((row.get(i).get(_title).toString()))) {
                    html.append("<li><a onclick=\"sw(" + (row.get(i).get(_id)) + ");\">" + (row.get(i).get(_title))
                            + "</a></li>");
                }
            }
            return Js.append("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String searchTextInTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String text = jjTools.getParameter(request, "text");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _title + " LIKE '%" + text + "%' "));
            html.append("<table class='news_cat mousePointer' dir='" + jjTools.getLangDir(request) + "' style='width:100%'>");
            String title = !jjTools.isLangEn(request) ? "عناوین یافت شده:" : " Search Status:";
            if (row.size() < 1) {
                title = !jjTools.isLangEn(request) ? "موردی یافت نشد." : "Not found this statement.";
            };
            html.append("<tr class='tblHeader'><td width=100% style='text-align: right'>" + title + "<br/><br/></td></tr>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='tblBody' style='text-align: right'><td  onclick=sw(" + (row.get(i).get(_id)) + ");>"
                        + (i + 1) + ". " + (row.get(i).get(_title)) + "</td>" + "</tr>");
            }
            html.append("</table>");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String searchTextInAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String title = "حاصل جستجو";
            if (jjTools.isLangEn(request)) {
                title = "Search result:";
            }
            String text = jjTools.getParameter(request, "text");
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _title + " LIKE '%" + text + "%'" + " OR " + _content + " LIKE '%" + text + "%'"));
            List<Map<String, Object>> rowNews = jjDatabase.separateRow(db.Select(News.tableName, News._title + " LIKE '%" + text + "%'" + " OR " + News._content + " LIKE '%" + text + "%'"));
            List<Map<String, Object>> rowProducts = jjDatabase.separateRow(db.Select(Product.tableName, Product._name + " LIKE '%" + text + "%'" + " OR " + Product._content + " LIKE '%" + text + "%'"));//====== BY RASHIDI ======
            if (row.size() < 1 && rowNews.size() < 1 && rowProducts.size() < 1) {
                return Js.setHtml("#" + panel, !jjTools.isLangEn(request) ? "موردی یافت نشد." : "Not found this statement in database.") + (titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, title));
            }
            html.append("<table class='searchResult'>");
            html.append("<tr class='tahoma9'><th>"
                    + (jjTools.isLangEn(request) ? "Search result:" : "نتایج جستجو:")
                    + "</th></tr>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=sw(" + (row.get(i).get(_id)) + ");>"
                        + (i + 1) + ". " + (row.get(i).get(_title)) + "</td></tr>");
            }
            for (int i = 0; i < rowNews.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=swNews(" + (rowNews.get(i).get(News._id)) + ");>"
                        + (i + 1) + ". " + (rowNews.get(i).get(News._title)) + "</td></tr>");
            }
            //====== BY RASHIDI ======>
            for (int i = 0; i < rowProducts.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=getOneproduct(" + (rowProducts.get(i).get(Product._id)) + ");>"
                        + (i + 1) + ". " + (rowProducts.get(i).get(Product._name)) + "</td></tr>");
            }
            //<====== BY RASHIDI ======
            html.append("</table>");
            String script = Js.setHtml("#" + panel, html.toString());
            script += (titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, title));
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String swtest(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        return "0000000000000000000000000000000000000000";
    }

    /**
     * برای نمایش یک محتوا در یک صفحه جی اس پی از این متد استفاده می کنیم
     *
     * @param request
     * @param db
     * @param isFromClient
     * @return
     * @throws Exception
     */
    public static String swNoAjax(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (jjTools.getParameter(request, "jj") == "") {// اگر دخواست ایجکس نبود
                request.getRequestDispatcher(Server.contentJSP);
            }
            String text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
            ServerLog.Print("========== id" + text);
            ServerLog.Print(jjTools.getParameter(request, "jj"));
            String lang = jjTools.getSessionAttribute(request, "myLang");
            
            List<Map<String, Object>> swContent = new ArrayList<Map<String, Object>>();
            if (jjNumber.isDigit(text)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + text + " AND " + _lang + " = '" + lang + "'"));
//                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + tagName));
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "'" + " AND " + _lang + " = " + lang));
//                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + tagName + "'"));
            }
            if (swContent.size() > 0) {
                String content = ConvertToWiki(request, swContent.get(0).get(_content).toString(), db, isFromClient);
//                String title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, swContent.get(0).get(_title));
                //is not by Ajax
                return content;
            } else {
                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail;";
                }
                return errorMessage;
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String sw(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            System.out.println(".............0.............2.............");
            String text;
            if (!needHtml) {//یعنی درخواست برای این متد از سمت کلاینت به سرولت سرور رسیده و پارامتر ها را باید از ریکوئست بخوانیم
                System.out.println("********************");
                text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
            } else {// یعنی اطلاعاتی که میخواهیم در اتریبیوت ها هست نه پارامتر ها چون درخواست را احتمالا یک فایل جی اس پی فرستاده
                System.out.println("$$$$$$$$$$$$$$$$$");
                jjTools.ShowAllAttribute(request);
                text = jjTools.getAttribute(request, "text");
                System.out.println("    ///" + text);
            }
            String panel = jjTools.getParameter(request, "panel").isEmpty() ? "sw" : jjTools.getParameter(request, "panel");
            String lang = jjTools.getLangNum(request);//'کد زبان اگر تهی بود یک بر میگرداند

            List<Map<String, Object>> swContent;
            if (jjNumber.isDigit(text)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + text));
                //ToDo برای سافت ریدایرکت برای حل مشکل دو لینک به یک مطلب برای سئو
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "'"));
            }
            if (swContent.size() > 0) {
                boolean userAccess = false;//فرض میکنیم کاربر دسترسی ندارد اگر خلافش ثابت شود یک میشود
                String userId = jjTools.getSeassionUserId(request) + "";
                String swContentPriveteUsers = "," + swContent.get(0).get(_privateUserId).toString() + ",";//
                // بررسی دسترسی کاربر به محتوا که اگر محتوا اخصاصی کاربران خاصی بود به این کاربر نشان داده نمیشود
                if (swContentPriveteUsers.contains("," + userId + ",")
                        || swContentPriveteUsers.contains(",0,") //اگر محتوای برای مهمان ها قابل نمایش بود برای این کاربر هم قابل نمایش است
                        || (swContentPriveteUsers.contains(",ALL,") && !userId.equals("0"))// اگر برای همه ی کاربران عضو بود و این کاربر مهمان نبود چون کاربر اگر مهمان باشد آی دی اش صفر است
                        ) {
                    userAccess = true;
                }// 
                if (!userAccess) {
                    List<Map<String, Object>> swGroup = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + userId));
                    String swContentPriveteGroups = "," + swContent.get(0).get(_privateGroupId).toString() + ",";//
                    if ((swContentPriveteGroups.contains(",ALL,") && swGroup.size() > 0) || swContentPriveteGroups.contains(",0,")) {//اگر محتوای مورد نظر دسترسی همه گروه ها را داشت و کاربر جداقل عضو یک گروه باشد باید دسترسی بدهیم
                        userAccess = true;
                    }
                    for (int i = 0; i < swGroup.size() && userAccess == false; i++) {//وقتی نتیجه مثبت بود از جلقه حارج شود چون دسترسی مجاز است و ادامه لازم نیست
                        if (swContentPriveteGroups.contains("," + swGroup.get(i).get(Access_Group_User._group_id) + ",")) {
                            userAccess = true;
                        }
                    }
                }
                if (!userAccess) {// اگر مشخص نشد که کاربر دسترسی دارد دستورات زیر اجرا و کاربر یک محتوای دیگر را مشاهده میکند
                    String script = "sw('شما مجوز دسترسی به این محتوا را ندارید');";// ّباید در دیتابیس یک محتوا با این عنوان داشته باشیم
                    Server.outPrinter(request, response, script);
                    return "";
                }
                //////////////////////////update visit
                Map<String, Object> mapVisit = new HashMap<>();
                int visit = Integer.parseInt(swContent.get(0).get(_visit).toString());
                visit++;
                mapVisit.put(_visit, visit);
                db.update(tableName, mapVisit, "id=" + swContent.get(0).get(_id).toString());
                ////////////////////////
                String title = swContent.get(0).get(_title).toString();
                String content;
                if ("1".equals(swContent.get(0).get(_hasInContentAutoWiki).toString())) {// اگر در محتوایش بایدلینک های  اتو ویکی داشته باشد
                    if ("1".equals(swContent.get(0).get(_autoWikIsUpdate).toString())) {//اگر اتو ویکی اش آپدیت بود
                        content = swContent.get(0).get(_contentWithWikiLinks).toString();
                    } else {//اگر اتو ویکی آپدیت نبود آنرا آپدیت می کنیم و همزمان در دیتابیس هم ذخیره می کنیم
                        content = ConvertToWiki(request, swContent.get(0).get(_content).toString(), db, needHtml);
                        Map<String, Object> map = new HashMap<>();
                        map.put(_contentWithWikiLinks, content);
                        map.put(_autoWikIsUpdate, 1);//الان اتو ویکی آپدیت شده است
                        db.update(tableName, map, "id=" + swContent.get(0).get(_id).toString());
                        request.setAttribute(_content, content);
                    }
                } else {//اگر محتوا نباید حاوی لینک های اتو ویکی باشد
                    content = swContent.get(0).get(_content).toString();
                }
                boolean requestIsAjax = jjTools.getParameter(request, "jj").equals("1");
                System.out.println("..n" + jjTools.getParameter(request, "panel"));
                if (!requestIsAjax) {// اگر ایجگس نباشد ما اتریبویت های جدید را ست می کنیم و یا پاس میدهیم به فایل جی اس پی یا اینکه یک موجود جاوایی خودش آنها را بر می دارد
                    System.out.println("..." + requestIsAjax);
//                    return content;
                    String address = request.getServletContext().getRealPath("/");
//                    request.setAttribute(_lang, lang);
                    request.setAttribute(_title, title);
                    request.setAttribute(_content, content);
                    request.setAttribute(_titleTag, swContent.get(0).get(_titleTag).toString());
                    request.setAttribute(_description, swContent.get(0).get(_description).toString());
                    request.setAttribute(_style, swContent.get(0).get(_style).toString());
                    request.setAttribute(_script, swContent.get(0).get(_script).toString());
                    request.setAttribute(_date, jjCalendar_IR.getViewFormat(swContent.get(0).get(_date).toString()));
                    request.setAttribute(_pic, swContent.get(0).get(_pic).toString());
                    request.setAttribute(_visit, swContent.get(0).get(_visit).toString());
                    request.setAttribute(_likes, swContent.get(0).get(_likes).toString());
                    request.setAttribute(_disLikes, swContent.get(0).get(_disLikes).toString());
                    request.setAttribute(_userCommensIsActive, swContent.get(0).get(_userCommensIsActive).toString());
                    if (!needHtml) {
                        System.out.println(".............1.............1.............");
                        
                        String page = swContent.get(0).get(_content_page).toString();
                        if (page.equals("")) {
                            request.getRequestDispatcher(Server.contentJSP).forward(request, Server.Publicresponse);
                        } else {
                            request.getRequestDispatcher(page).forward(request, Server.Publicresponse);
                        }
                        System.out.println("(((((((((((((((((((((((((((((((((((((((((((((");
                        return "";
                    } else {// اگر یک کلاس جاوایی ایا یک فایل جی اس پی ین نتایج را میخواهد لازم نیست به صفحه ی دیگری برویم
                        System.out.println("***********...................................**********************" + panel);
                        String script = Js.setHtml("#" + panel, content);
//                        Server.outPrinter(request, response, script);
                        return script;
                    }
                }
                //is called by Ajax
                System.out.println(panel);
                String script = Js.setHtml("#" + jjTools.getParameter(request, "panel"), content);
                System.out.println("//////////////////////////////////////////////" + panel);
                script += "document.title = '" + title + "';";
                script += Js.setHtml("title", title);
                script += Js.append("head", swContent.get(0).get(_style).toString());
                script += Js.append("body", swContent.get(0).get(_script).toString());
                script += Js.setAttr("meta[name=description]", "content", swContent.get(0).get(_description).toString());
                String panell = jjTools.getParameter(request, "panel").toString();

//                script += "$(\"html, body\").delay(1000).animate({scrollTop: $('#" + panell + "').offset().top}, 800);";
//                script += "$('.sw').css('margin-top',200)";
                Server.outPrinter(request, response, script);
                return "";
            } else {
                String errorMessage = "چنین محتوایی در سیستم وجود ندارد";
                Server.outPrinter(request, response, Js.setHtml("#" + panel, errorMessage));
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String getDashbord(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            String text;
            if (!needHtml) {//یعنی درخواست برای این متد از سمت کلاینت به سرولت سرور رسیده و پارامتر ها را باید از ریکوئست بخوانیم
                System.out.println("********************");
                text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
            } else {// یعنی اطلاعاتی که میخواهیم در اتریبیوت ها هست نه پارامتر ها چون درخواست را احتمالا یک فایل جی اس پی فرستاده
                System.out.println("$$$$$$$$$$$$$$$$$");
                jjTools.ShowAllAttribute(request);
                text = jjTools.getAttribute(request, "text");
            }
            String panel = jjTools.getParameter(request, "panel").isEmpty() ? "sw" : jjTools.getParameter(request, "panel");
            String lang = jjTools.getLangNum(request);//'کد زبان اگر تهی بود یک بر میگرداند

            List<Map<String, Object>> swContent;
            if (jjNumber.isDigit(text)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + text + " AND " + _ownerId + "=" + jjTools.getSeassionUserId(request)));
                //ToDo برای سافت ریدایرکت برای حل مشکل دو لینک به یک مطلب برای سئو
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "' AND " + _ownerId + "=" + jjTools.getSeassionUserId(request)));
            }
            if (swContent.size() > 0) {
                String title = swContent.get(0).get(_title).toString();
                String content;
                if ("1".equals(swContent.get(0).get(_hasInContentAutoWiki).toString())) {// اگر در محتوایش بایدلینک های  اتو ویکی داشته باشد
                    if ("1".equals(swContent.get(0).get(_autoWikIsUpdate).toString())) {//اگر اتو ویکی اش آپدیت بود
                        content = swContent.get(0).get(_contentWithWikiLinks).toString();
                    } else {//اگر اتو ویکی آپدیت نبود آنرا آپدیت می کنیم و همزمان در دیتابیس هم ذخیره می کنیم
                        content = ConvertToWiki(request, swContent.get(0).get(_content).toString(), db, needHtml);
                        Map<String, Object> map = new HashMap<>();
                        map.put(_contentWithWikiLinks, content);
                        map.put(_autoWikIsUpdate, 1);//الان اتو ویکی آپدیت شده است
                        db.update(tableName, map, "id=" + swContent.get(0).get(_id).toString());
                        request.setAttribute(_content, content);
                    }
                } else {//اگر محتوا نباید حاوی لینک های اتو ویکی باشد
                    content = swContent.get(0).get(_content).toString();
                }
                boolean requestIsAjax = jjTools.getParameter(request, "jj").equals("1");
                if (!requestIsAjax) {// اگر ایجگس نباشد ما اتریبویت های جدید را ست می کنیم و یا پاس میدهیم به فایل جی اس پی یا اینکه یک موجود جاوایی خودش آنها را بر می دارد
//                    return content;
                    String address = request.getServletContext().getRealPath("/");
//                    request.setAttribute(_lang, lang);
                    request.setAttribute(_title, title);
                    request.setAttribute(_content, content);
                    request.setAttribute(_titleTag, swContent.get(0).get(_titleTag).toString());
                    request.setAttribute(_description, swContent.get(0).get(_description).toString());
                    request.setAttribute(_style, swContent.get(0).get(_style).toString());
                    request.setAttribute(_script, swContent.get(0).get(_script).toString());
                    request.setAttribute(_date, jjCalendar_IR.getViewFormat(swContent.get(0).get(_date).toString()));
                    request.setAttribute(_pic, swContent.get(0).get(_pic).toString());
                    request.setAttribute(_visit, swContent.get(0).get(_visit).toString());
                    request.setAttribute(_likes, swContent.get(0).get(_likes).toString());
                    request.setAttribute(_disLikes, swContent.get(0).get(_disLikes).toString());
                    request.setAttribute(_userCommensIsActive, swContent.get(0).get(_userCommensIsActive).toString());
                    if (!needHtml) {
                        request.getRequestDispatcher(Server.contentJSP).forward(request, Server.Publicresponse);
                        System.out.println("(((((((((((((((((((((((((((((((((((((((((((((");
                        return "";
                    } else {// اگر یک کلاس جاوایی ایا یک فایل جی اس پی ین نتایج را میخواهد لازم نیست به صفحه ی دیگری برویم
                        String script = Js.setHtml("#" + panel, content);
//                        Server.outPrinter(request, response, script);
                        return script;
                    }
                }
                //is called by Ajax
                System.out.println(panel);
                String script = Js.setHtml("#" + jjTools.getParameter(request, "panel"), content);
                script += "document.title = '" + title + "';";
                script += Js.setHtml("title", title);
                script += Js.append("head", swContent.get(0).get(_style).toString());
                script += swContent.get(0).get(_script).toString();
                script += Js.setAttr("meta[name=description]", "content", swContent.get(0).get(_description).toString());
                Server.outPrinter(request, response, script);
                return "";
            } else {
                String errorMessage = "";
                Server.outPrinter(request, response, Js.setHtml("#" + panel, errorMessage));
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String swContentMobile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
//            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
//
//            } else {
            String panel = jjTools.getParameter(request, "panel");
            String text = jjTools.getParameter(request, "text");
            List<Map<String, Object>> swContent;
            swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "'"));
            String script = Js.setHtml("#" + panel, swContent.get(0).get(_content));
            script += Js.append("head", swContent.get(0).get(_style).toString());
            script += swContent.get(0).get(_script).toString();
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String swPrivate(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                
            } else {
                String text = "";
                String panel = "";
                if (!needHtml) {//یعنی درخواست برای این متد از سمت کلاینت به سرولت سرور رسیده و پارامتر ها را باید از ریکوئست بخوانیم
                    System.out.println("********************");
                    text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
                    panel = java.net.URLDecoder.decode(jjTools.getParameter(request, "panel"), "utf-8");
                } else {// یعنی اطلاعاتی که میخواهیم در اتریبیوت ها هست نه پارامتر ها چون درخواست را احتمالا یک فایل جی اس پی فرستاده
                    System.out.println("$$$$$$$$$$$$$$$$$");
                    jjTools.ShowAllAttribute(request);
                    text = jjTools.getAttribute(request, "text");
                    panel = jjTools.getAttribute(request, "panel");
                }
                List<Map<String, Object>> swContent;
                swContent = jjDatabase.separateRow(db.Select(tableName, _privateUserId + " LIKE '%," + jjTools.getSeassionUserId(request) + ",%'"));
                if (swContent.size() > 0) {
                    String title = swContent.get(0).get(_title).toString();
                    StringBuilder html = new StringBuilder();
                    boolean requestIsAjax = jjTools.getParameter(request, "jj").equals("1");
                    for (int i = 0; i < swContent.size(); i++) {
                        String titleName = swContent.get(i).get(_title).toString();
                        titleName = titleName.replaceAll(titleName, "<a href='Server?do=Content.sw&panel=sw&text=" + titleName
                                + "' onclick='sw(" + swContent.get(i).get(_id) + ");return false;' class='p jjLink hoverAutoWiki'>"
                                + titleName + "</a>");
                        html.append("<div class='col-lg-6'>" + titleName + "</div>");
                    }
                    if (!requestIsAjax) {// اگر ایجگس نباشد ما اتریبویت های جدید را ست می کنیم و یا پاس میدهیم به فایل جی اس پی یا اینکه یک موجود جاوایی خودش آنها را بر می دارد
                        if (!needHtml) {
                            request.getRequestDispatcher(Server.contentJSP).forward(request, Server.Publicresponse);
                            System.out.println("/////////////////////////");
                            return "";
                        } else {// اگر یک کلاس جاوایی ایا یک فایل جی اس پی ین نتایج را میخواهد لازم نیست به صفحه ی دیگری برویم
                            String script = Js.setHtml("#" + panel, html);
                            return script;
                        }
                    }
                    //is called by Ajax
                    System.out.println(panel);
                    String script = Js.setHtml("#" + jjTools.getParameter(request, "panel"), html);
                    script += "document.title = '" + title + "';";
                    script += Js.setHtml("title", title);
                    script += Js.append("head", swContent.get(0).get(_style).toString());
                    script += swContent.get(0).get(_script).toString();
                    script += Js.setAttr("meta[name=description]", "content", swContent.get(0).get(_description).toString());
                    Server.outPrinter(request, response, script);
                    return "";
                } else {
                    String errorMessage = "";
                    Server.outPrinter(request, response, Js.setHtml("#" + panel, errorMessage));
                    return "";
                }
            }
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
//    public static List<Map<String, Object>> catchContentTitle = null;
//    public static List<Map<String, Object>> catchProductTitle = null;
    public static List<Map<String, Object>> catchNewsTitle = null;
    public static String previousLang = null;

    /**
     * برای اینکه لیست اتوویکی بروز رسانی بشود زبان را باید درست کنیم الان برای
     * یک زبان درست است
     *
     * @param request
     * @param content
     * @param db
     * @param isFromClient
     */
    public static void resetCatchContentTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        ServerLog.Print("===>>>>>resetCatchContentTitle" + context.getContextPath() + context.getServerInfo());
        String lang = "1";// زبان را باید دقت کنیم بعدا درست باشد یعنی برای هر زبان یک متغیر در اسکوپ اپلیکیشن می خواهیم
//        context.setAttribute("catchContentTitle", jjDatabase.separateRow(db.Select(tableName, _lang + " = " + lang + " AND " + _hasLink + " =1")));
        context.setAttribute("catchContentTitle", jjDatabase.separateRow(db.Select(tableName, _title + "," + _id + "," + _isAjax, _hasLink + "=1", "CHAR_LENGTH(" + _title + ")DESC")));
    }
    
    public static void resetCatchProductTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        ServerLog.Print("===>>>>>resetCatchProductTitle" + context.getContextPath() + context.getServerInfo());
        String lang = "1";// زبان را باید دقت کنیم بعدا درست باشد یعنی برای هر زبان یک متغیر در اسکوپ اپلیکیشن می خواهیم
        context.setAttribute("catchProductTitle", jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + " = " + lang + " AND " + Product._hasLink + " =1")));
    }
    
    public static List<Map<String, Object>> getCatchContentTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        if (context.getAttribute("catchContentTitle") == null) {
            resetCatchContentTitle(request, content, db, isFromClient);
        }
        return (List<Map<String, Object>>) context.getAttribute("catchContentTitle");
    }
    
    public static List<Map<String, Object>> getCatchProductTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        if (context.getAttribute("catchProductTitle") == null) {
            resetCatchProductTitle(request, content, db, isFromClient);
        }
        return (List<Map<String, Object>>) context.getAttribute("catchProductTitle");
    }

    /**
     * وقتی یک متنی به اتوویکی ها اضافه می شود باید این تایع فراخوانی شود که
     * لینک این مطلب را در همه ی محتواهایی که باید اتوویکی شوند بگذارد
     *
     * @param request
     * @param content
     * @param db
     * @param isFromClient
     * @throws Exception
     */
    public static void resetAllAutoWikies(HttpServletRequest request, jjDatabaseWeb db) throws Exception {
        //@ToDo  این قسمت برای این است که وقتی که یک محتوا ویرایش می شود همه ی اتو ویکی ها باید بروز رسانی بشوند. 

        //این تابع خوب است تایمیر   داشته باشد که مثلا ده ثانیه صبر کند و بعد این عمل را انجام دهد که عمل ویرایرش در نظر کاربر کند نباشد
        System.out.println("");
    }
    
    public static String ConvertToWiki(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            content = content.replaceAll("&lt;", "&_l_t_;");
            content = content.replaceAll("&gt;", "&_g_t_;");
            String lang = jjTools.getLangNum(request);
            if (previousLang == null) {
                previousLang = lang;
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);
            List<Map<String, Object>> catchContentTitle = getCatchContentTitle(request, _content, db, isFromClient);
            for (int i = 0; i < catchContentTitle.size(); i++) {
                Document doc = Jsoup.parse(content);
                String ti = catchContentTitle.get(i).get(_title).toString();
                Elements els = doc.body().select("* :not(a)");
                for (Element e : els) {
                    List<TextNode> tnList = e.textNodes();
                    for (TextNode tn : tnList) {
                        String orig = tn.text();
                        if (ti.length() > 2) {//اگر طول کاراکتر ها کوچک باشد اتوویکی نمی شود
                            if (catchContentTitle.get(i).get(_isAjax).equals("1")) {
                                tn.text(orig.replaceAll(ti, "<a href='"+Server.contentJSP+"?text=" + ti
                                        + "' onclick='sw(" + catchContentTitle.get(i).get(_id) + ");return false;' class='p jjLink hoverAutoWiki'>"
                                        + ti + "</a>"));
                            } else {
                                tn.text(orig.replaceAll(ti, "<a href='"+Server.contentJSP+"?text=" + ti
                                        + "'  target='_blank' class='p jjLink hoverAutoWiki' >"
                                        + ti + "</a>"));
                            }
                            
                        }
                    }
                }
                content = doc.toString().replaceAll("&gt;", ">").replaceAll("&lt;", "<");
            }
            content = content.replaceAll("&_l_t_;", "&lt;");
            content = content.replaceAll("&_g_t_;", "&gt;");
            List<Map<String, Object>> catchProductTitle = getCatchProductTitle(request, content, db, isFromClient);
            for (int i = 0; i < catchProductTitle.size(); i++) {
                String ti = catchProductTitle.get(i).get(Product._name).toString();
                String id = catchProductTitle.get(i).get(_id).toString();
                if (ti.length() > 2) {
                    content = content.replace(ti, "<a href='Server?do=Product.getOneProduct&id=" + id + "&panel=" + panel + "' onclick='swProduct(" + id + ");return false;' class='mousePointer jjLink'>" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
                    content = content.replace("&nbsp;" + ti + "&nbsp;", "<a onclick=swProduct('" + id + "'); class='mousePointer jjLink' >" + ti + "&nbsp;</a>");
                }
            }
//            for (int i = 0; i < catchNewsTitle.size(); i++) {
//                String ti = catchNewsTitle.get(i).get(News._title).toString();
//                String id = catchNewsTitle.get(i).get(_id).toString();
//                if (ti.length() > 2) {
////                    content = content.replace(ti, "<a href=\"Server?do=News.sw&id=" + id + "&panel=" + panel + "\" onclick='swNews(" + id + ");return false;' class='mousePointer jjLink' >" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
//                    content = content.replace(ti, "<a href='Server?do=News.getOneNews&id=" + id + "&panel=" + panel + "' onclick='swNews(" + id + ");return false;' class='mousePointer jjLink' >" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
//                    content = content.replace("&nbsp;" + ti + "&nbsp;", "<a onclick=swNews('"
//                            + id + "'); class='mousePointer jjLink' >" + ti + "&nbsp;</a>");
//                }
//            }
            //============ BY RASHIDI ========> 
            //برای درست نمایش دادن مقدار alt در عکسها.
            //برای مقدار ویژگی
            //alt
            //لینک ساخته نشود.
            Pattern pattern = Pattern.compile("alt=['\"]<a href=[\"'][a-zA-Z\\?=\\.&0-9'\"\\s\\(\\);>ا-ی]*</a>['\"]");//alt='<a href="Server?do=className.method?parameters=value" onclick="method(1);return false;" class="mousePointer jjLink">title</a>'
            Matcher matcher;
            List<String> listMatches = new ArrayList<String>();
            matcher = pattern.matcher(content);
            while (matcher.find()) {
                listMatches.add(matcher.group(0));
            }
            
            for (int j = 0; j < listMatches.size(); j++) {
                content = content.replace(listMatches.get(j), listMatches.get(j).replaceFirst("<a href=[\"'][a-zA-Z\\?=\\.&0-9'\"\\s\\(\\);>ا-ی]*>", "").replace("</a>", ""));
            }
            //<============ BY RASHIDI ======= 
            String searchValue = "نظرسنجی";
            int starter = 0;
            while (content.indexOf(searchValue, starter) > 0) {
                int searchValueIndex = content.indexOf(searchValue, starter);
                if (searchValueIndex > -1) {
                    if (content.length() > (searchValueIndex + (searchValue.length() + 4))) {
                        String val = content.substring(searchValueIndex, searchValueIndex + (searchValue.length() + 4));
                        String idString = val.replace(searchValue, "").trim();
                        if (jjNumber.isDigit(idString)) {
                            content = content.replace(val, Poll.getOnePoll(request, db, Integer.parseInt(idString)));
                        }
                    }
                }
                starter = searchValueIndex + 1;
            }
            return content;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    
    public static String searchTags(HttpServletRequest request, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder script = new StringBuilder();
            String text = jjTools.getParameter(request, Tags._name);
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "content_search_tags_result" : panel);
            List<Map<String, Object>> rowTags = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._name + " LIKE '%" + text + "%'"));//تگ وارد شده پیش از این در جدول تگ ها ثبت شده یانه
            if (!rowTags.isEmpty()) {
                html.append("<table class='searchResult'>");
                for (int i = 0; i < rowTags.size(); i++) {
                    html.append("<tr class='tahoma9'>");
                    html.append("<td id=\"tagsResult_td" + i + "\" onclick=\"selectSearchResult(" + i + ");\">" + rowTags.get(i).get(Tags._name) + "</td></tr>");
                }
                html.append("</table>");
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.show("#" + panel));
            } else {
                script.append(Js.setHtml("#" + panel, ""));
                script.append(Js.hide("#" + panel));
            }
            return script.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //گرفتن گروه ها و قرار دادن در سلکت تو
    public static String getGroups(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_Group.tableName));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "content_privateGroupId" : panel;
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
        panel = panel.equals("") ? "content_privateUserId" : panel;
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

    //این تابع اطلاع رسانی ها را به عنوان یک اسلایدر میفرستد سمت صفحه اطلاع رسانی
    public static String contentSlider(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuilder html = new StringBuilder();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _priority + "=1"));
        int j = 1;
        for (int i = 0; i < row.size(); i++) {
            if (j == 1) {
                html.append("<li class=\"slide1\">\n"
                        + "                                        <div class=\"container\">\n"
                        + "                                            <div class=\"flex_caption1\">\n"
                        + "\n"
                        + "                                                <p class=\"slide-heading FromTop\">" + row.get(i).get(_title).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <p class=\"sub-line FromBottom\">" + row.get(i).get(_explain).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <a href=\"\" class=\"slider-read FromLeft\">ادامه مطلب</a>\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"flex_caption2 FromRight\" style=\"background-image: url(upload/" + row.get(i).get(_pic).toString() + ");\"></div>\n"
                        + "                                        </div>\n"
                        + "                                    </li>");
            } else if (j == 2) {
                html.append("<li class=\"slide2\">\n"
                        + "                                        <div class=\"container\">\n"
                        + "                                            <div class=\"slide flex_caption1\">\n"
                        + "                                                <p class=\"slide-heading FromTop\">" + row.get(i).get(_title).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <p class=\"sub-line FromRight\">" + row.get(i).get(_explain).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <a href=\"\" class=\"slider-read FromLeft\">ادامه مطلب</a>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"flex_caption2 FromBottom\" style=\"background-image: url(upload/" + row.get(i).get(_pic).toString() + ");\"></div>\n"
                        + "                                        </div>\n"
                        + "                                    </li>");
            } else if (j == 3) {
                html.append("<li class=\"slide3\">\n"
                        + "                                        <div class=\"container\">\n"
                        + "                                            <div class=\"slide flex_caption1\">\n"
                        + "                                                <p class=\"slide-heading FromTop\">" + row.get(i).get(_title).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <p class=\"sub-line FromRight\">" + row.get(i).get(_explain).toString() + "</p><br/>\n"
                        + "\n"
                        + "                                                <a href=\"\" class=\"slider-read FromLeft\">ادامه مطلب</a>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"flex_caption2 FromRight\" style=\"background-image: url(upload/" + row.get(i).get(_pic).toString() + ");\"></div>\n"
                        + "                                        </div>\n"
                        + "                                    </li>");
            }
            if (j > 2) {
                j = 1;
            } else {
                j++;
            }
        }
//        Server.outPrinter(request, response, html.toString());
        return html.toString();
    }
    
    public static String getNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
            html.append("<div class='tab-top-nav col-12'><h1>اخبار</h1></div><div style='align:right;margin-top: 60px;'></div>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<div class='col-12' style='margin-top: 10px;' onclick='sw(" + row.get(i).get(_id) + ");'>");
                html.append("<div class='row shadow' style='margin-left: 0px;margin-right: 0px;background-color: #fff;'>\n"
                        + "                        <div class='col-lg-12' style='text-align:center;'>\n"
                        + "                            <img src='upload/" + row.get(i).get(_pic) + "' width='150' height='150' alt='trumpet' style='border-radius: 100%;margin-top: 2px;'/>\n"
                        + "                        </div>\n"
                        + "                        <div class='card promoting-card col-lg-12 ' style='border: 0px;'>\n"
                        + "                            <div class='card-body d-flex flex-row' style='padding-bottom: 10px;'>\n"
                        + "                                <div>\n"
                        + "                                    <h4 class='card-title font-weight-bold mb-2'>" + row.get(i).get(_title) + "</h4>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                                <div class='collapse-content'>\n"
                        + "                                    <p class='card-text' id='collapseContent'>" + row.get(i).get(_explain) + "</p>\n"
                        + "                                    <span class='float-left p-1 my-1 mr-0 mml-1 collapsed'><i class='far fa-calendar pl-2 ml-1'></i>" + jjCalendar_IR.getViewFormat_10length(Integer.parseInt(row.get(i).get(_date).toString())) + "</span>\n"
                        + "                                    <span class='float-right p-1 my-1 mr-0 mml-1 collapsed' ><i class='fal fa-eye'></i>" + row.get(i).get(_visit) + "</span>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>");
                html.append("</div>");
            }
            
            html.append("<div class='col-12' style='margin-top: 10px;height:55px;'></div>");
            if (panel.equals("")) {
                panel = "sw";
            }
            String script = Js.setHtml("#" + panel, html.toString());
            script += "";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * محتوای عمومی
     *
     * @param request
     * @param response
     * @param db
     * @param needHtml
     * @return
     * @throws Exception
     */
    public static String buildContentCategory(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String categoryId = jjTools.getParameter(request, _category_id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT  "
                    + "c2c." + Category_Content._title + ","
                    + "c." + Content._title + ","
                    + "c." + Content._id + ","
                    + "c." + Content._privateUserId + ","
                    + "c." + Content._privateGroupId + ","
                    + "c." + Content._pic
                    + " FROM  content  c "
                    + " left join category_content c2c ON  c." + Content._category_id + "=c2c." + Category_Content._id
                    + " WHERE c." + Content._category_id + "=" + categoryId
            ));
            html.append("<section class='page-section ' id='portfolio'>\n"
                    + "            <div class='container'>\n"
                    + "                <div class='text-center'>\n"
                    + "                    <h2 class='text-uppercase' style='color:white;text-shadow:0 0 10px #000'>" + row.get(0).get(Category_Content._title).toString() + "</h2>\n"
                    + "                </div>\n"
                    + "                <div class='row mg-t-20'>");
            for (int i = 0; i < row.size(); i++) {
                boolean flag = false;
                boolean flag2 = false;
                System.out.println("jjTools.getSessionAttribute(request, \"#GROUP_ID\")=" + jjTools.getSessionAttribute(request, "#GROUP_ID"));
                String privateGroupId = "," + row.get(i).get(_privateGroupId).toString() + ",";
                if (!jjTools.getSessionAttribute(request, "#GROUP_ID").equals("")) {
                    String[] seprateGroup = jjTools.getSessionAttribute(request, "#GROUP_ID").split(",");
                    for (int k = 0; k < seprateGroup.length; k++) {
                        Pattern p = Pattern.compile("," + seprateGroup[k] + ",");
                        Matcher m = p.matcher("," + row.get(i).get(_privateGroupId).toString() + ",");
                        if (!m.find() && !privateGroupId.contains(",0,")) {
                            flag = false;
                        } else if (privateGroupId.contains(",0,") || m.find()) {
                            flag = true;
                        }
                    }
                }
                Pattern p = Pattern.compile("(^" + jjTools.getSeassionUserId(request) + ",)|(," + jjTools.getSeassionUserId(request) + ",)|(," + jjTools.getSeassionUserId(request) + "$)|" + jjTools.getSeassionUserId(request));
                Matcher m = p.matcher(row.get(i).get(_privateUserId).toString());
                if (!m.find()) {
                    flag2 = false;
                } else {
                    flag2 = true;
                }
                if (flag2 == true || flag == true) {
                    html.append("<div class='col-lg-4 col-sm-6 mb-4' onclick='sw(" + row.get(i).get(_id).toString() + ");'>\n");
                    html.append("    <div class='portfolio-item'>\n");
                    html.append("            <img class='img-fluid' style='float:right;border-radius:15px;box-shadow:0 0 10px #eee;max-width:25%;width:18%;height:70px;' src='upload/" + row.get(i).get(_pic).toString() + "' alt='" + row.get(i).get(_title).toString() + "'>\n");
                    html.append("        <div class='portfolio-caption' style='background-color:rgb(70 65 65 / 20%);border-radius:10px 20px 20px 10px'>\n");
                    html.append("            <p class= style='font-weight:600;color:white;text-shadow:0 0 10px #000'>" + row.get(i).get(_title).toString() + "</p>\n");
                    html.append("        </div>\n");
                    html.append("    </div>\n");
                    html.append("</div>");
                }
            }
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
            Server.outPrinter(request, response, Js.setHtml("#sw", html));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String swPublicLearning(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=3"));
            html.append("<section class='page-section ' id='portfolio'>\n"
                    + "            <div class='container'>\n"
                    + "                <div class='text-center'>\n"
                    + "                    <h2 class='text-uppercase' style='color:white;text-shadow:0 0 10px #000'>عمومی</h2>\n"
                    + "                </div>\n"
                    + "                <div class='row'>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<div class='col-lg-4 col-sm-6 mb-4'>\n");
                html.append("    <div class='portfolio-item'>\n");
                html.append("        <a class='portfolio-link' onclick='sw(" + row.get(i).get(_id).toString() + ");'>\n");
                html.append("            <img class='img-fluid' style='border-radius:50%;box-shadow:0 0 10px #eee;max-width:50%' src='upload/" + row.get(i).get(_pic).toString() + "' alt='" + row.get(i).get(_title).toString() + "'>\n");
                html.append("        </a>\n");
                html.append("        <div class='portfolio-caption' style='background-color:rgb(70 65 65 / 20%);border-radius:20%'>\n");
                html.append("            <div class='portfolio-caption-heading' style='color:white;text-shadow:0 0 10px #000'>" + row.get(i).get(_title).toString() + "</div>\n");
                html.append("        </div>\n");
                html.append("    </div>\n");
                html.append("</div>");
            }
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
            Server.outPrinter(request, response, Js.setHtml("#sw", html));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    
    public static String swPrivateLearning(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHtml) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, "*", _category_id + "=4"));
            
            html.append("<section class='page-section ' id='portfolio'>\n"
                    + "            <div class='container'>\n"
                    + "                <div class='text-center'>\n"
                    + "                    <h2 class='text-uppercase' style='color:white;text-shadow:0 0 10px #000'>مطالب ویژه شما</h2>\n"
                    + "                </div>\n"
                    + "                <div class='row'>");
            
            for (int i = 0; i < row.size(); i++) {
                boolean flag = false;
                boolean flag2 = false;
                System.out.println("jjTools.getSessionAttribute(request, \"#GROUP_ID\")=" + jjTools.getSessionAttribute(request, "#GROUP_ID"));
                String privateGroupId = "," + row.get(i).get(_privateGroupId).toString() + ",";
                if (!jjTools.getSessionAttribute(request, "#GROUP_ID").equals("")) {
                    String[] seprateGroup = jjTools.getSessionAttribute(request, "#GROUP_ID").split(",");
                    for (int k = 0; k < seprateGroup.length; k++) {
                        if (!privateGroupId.contains("," + seprateGroup[k] + ",") && !privateGroupId.contains(",0,")) {
                            flag = false;
                        } else if (privateGroupId.contains(",0,") || privateGroupId.contains("," + seprateGroup[k] + ",")) {
                            flag = true;
                        }
                    }
                }
                Pattern p = Pattern.compile("(^" + jjTools.getSeassionUserId(request) + ",)|(," + jjTools.getSeassionUserId(request) + ",)|(," + jjTools.getSeassionUserId(request) + "$)|" + jjTools.getSeassionUserId(request));
                Matcher m = p.matcher(row.get(i).get(_privateUserId).toString());
                if (!m.find()) {
                    flag2 = false;
                } else {
                    flag2 = true;
                }
                if (flag2 == true || flag == true) {
                    html.append("<div class='col-lg-4 col-sm-6 mb-4'>\n");
                    html.append("    <div class='portfolio-item'>\n");
                    html.append("        <a class='portfolio-link' onclick='sw(" + row.get(i).get(_id).toString() + ");'>\n");
                    html.append("            <img class='img-fluid' style='border-radius:50%;box-shadow:0 0 10px #eee;max-width:50%' src='upload/" + row.get(i).get(_pic).toString() + "' alt='" + row.get(i).get(_title).toString() + "'>\n");
                    html.append("        </a>\n");
                    html.append("        <div class='portfolio-caption' style='background-color:rgb(70 65 65 / 20%);border-radius:5%'>\n");
                    html.append("            <div class='portfolio-caption-heading' style='color:white;text-shadow:0 0 10px #000'>" + row.get(i).get(_title).toString() + "</div>\n");
                    html.append("        </div>\n");
                    html.append("    </div>\n");
                    html.append("</div>");
                }
            }
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
            Server.outPrinter(request, response, Js.setHtml("#sw", html));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//    public static String getUserAndRoleInGroup(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        try {
//            StringBuilder html = new StringBuilder();
//            String id = jjTools.getParameter(request, "educationClass_registrants_groupId");
//
//            String userPanel = jjTools.getParameter(request, "userPanel");
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                Server.outPrinter(request, response, Js.dialog(errorMessageId));
//                return "";
//            }
////            String usersId = Access_Group.getUserIdByGroupId(id, db);
//            List<String> newList = new ArrayList<>();// 
//            String[] arrayStr = usersId.split(",");
//            for (int i = 0; i < arrayStr.length; i++) {//اگر ای دی کاربر تکراری بود داخل لیست جدید نمی آید
//                if (!newList.contains(arrayStr[i])) {
//                    newList.add(arrayStr[i]);
//                }
//            }
//            usersId = String.join(",", newList);
//            html.append("$('" + userPanel + "').val(" + usersId + ");");
//            html.append(Js.setValSelectOption(userPanel, usersId));
//            html.append(Js.select2(userPanel, "width: '100%'"));
//
//            /////////////////////////////////////////////////////////
////            String roleId = row.get(0).get(_role_id).toString();
////            String[] roleIdArray = roleId.split(",");
////            if (!roleId.equals("")) {
////                String role = "";
////                System.out.println("roleIdArray=" + roleIdArray.length);
////                for (int i = 0; i < roleIdArray.length; i++) {
////                    role += "" + roleIdArray[i] + ",";
////                }
////                html.append("$('"+rolePanel+"').val("+row.get(0).get(_role_id)+");");
////                html.append(Js.setValSelectOption(rolePanel, role));
////                html.append(Js.select2(rolePanel, "width: '100%'"));     
////            } else {
////            }
//            Server.outPrinter(request, response, html);
//            return "";
//        } catch (Exception e) {
//            Server.outPrinter(request, response, Server.ErrorHandler(e));
//            return "";
//        }
//    }
    /**
     * ایت تابع یکسری کد اچ تی ام را بر می گردانند
     *
     * @param request
     * @param response
     * @param title
     * @param db
     * @return
     * @throws Exception
     */
    public static String getHtml(HttpServletRequest request, HttpServletResponse response, String title, jjDatabaseWeb db) throws Exception {
        try {
            
            String lang = jjTools.getLangNum(request);//'کد زبان اگر تهی بود یک بر میگرداند
            List<Map<String, Object>> swContent;
            if (jjNumber.isDigit(title)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + title + " AND " + _lang + "='" + lang + "'"));
                //ToDo برای سافت ریدایرکت برای حل مشکل دو لینک به یک مطلب برای سئو
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + title + "'" + " AND " + _lang + "='" + lang + "'"));
            }
            if (swContent.size() > 0) {
                
                String content;
                if ("1".equals(swContent.get(0).get(_hasInContentAutoWiki).toString())) {// اگر در محتوایش بایدلینک های  اتو ویکی داشته باشد
                    if ("1".equals(swContent.get(0).get(_autoWikIsUpdate).toString())) {//اگر اتو ویکی اش آپدیت بود
                        content = swContent.get(0).get(_contentWithWikiLinks).toString();
                    } else {//اگر اتو ویکی آپدیت نبود آنرا آپدیت می کنیم و همزمان در دیتابیس هم ذخیره می کنیم
                        content = ConvertToWiki(request, swContent.get(0).get(_content).toString(), db, false);
                        Map<String, Object> map = new HashMap<>();
                        map.put(_contentWithWikiLinks, content);
                        map.put(_autoWikIsUpdate, 1);//الان اتو ویکی آپدیت شده است
                        db.update(tableName, map, "id=" + swContent.get(0).get(_id).toString());
                        request.setAttribute(_content, content);
                    }
                } else {//اگر محتوا نباید حاوی لینک های اتو ویکی باشد
                    content = swContent.get(0).get(_content).toString();
                }
                
                return content;
            } else {
                String errorMessage = Language.getSentense(Language._recordNotAvalable, lang, db);
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail";
                }
                String panel = jjTools.getParameter(request, "panel");
                return Js.setHtml("#" + panel, errorMessage);
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
//            return Server.ErrorHandler(ex);
        }
    }
}
