package cms.cms;

import cms.tools.jjTools;
import cms.tools.jjValidation;
import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Category_News {

    public static String tableName = "category_news";
    public static String _id = "id";
    public static String _title = "category_news_title";
    public static String _parent = "category_news_parent";
    public static String _lang = "category_news_lang";
    public static String _level = "category_news_level";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static int rul_rfs = 261;
    public static int rul_ins = 262;
    public static int rul_edt = 263;
    public static int rul_dlt = 264;

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
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
            html.append(" <div class='card bd-primary mg-t-20'>"
                    + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی اخبار سازمانی</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی اخبار سازمانی</div>"
                    + "    <div class='card-body pd-sm-30'>"
                    + "        <p class='mg-b-20 mg-sm-b-30'>"
                    + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsCategoryNews.m_add_new();' > دسته بندی جدید</a>"
                    + "        </p>");
            }
            html.append("<table id='refreshCategoryNews' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsCategoryNews.m_select(" + row.get(i).get(_id) + ");'  class='mousePointer' >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsCategoryNews.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swCategoryNewsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryNews", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست دسته های اخبار سازمانی");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getList(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            html.append("<br/>");
            html.append("<table id='refreshCategoryNews2' style='width:100%' class='tahoma10' dir='rtl'><thead>");
            html.append("<th width='5%'>ردیف</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            int counter = 0;
            String firstCode = "";
            for (int i = 0; i < row.size(); i++) {
                if (db.Select(News.tableName, News._category_id + "=" + row.get(i).get(_id)).getRowCount() > 0) {
                    counter += 1;
                    if (counter == 1) {
                        firstCode = row.get(i).get(_id).toString();
                    }
                    html.append("<tr  onclick='swGetNews(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                    html.append("<td class='c'>" + counter + "</td>");
                    html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                    html.append("<td class='c'><img src='img/l.png' style='height:30px' /></td>");
                    html.append("</tr>");
                }
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            String html2 = "";
            if (counter > 1) {
                html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
                html2 += Js.table("#refreshCategoryNews2", height, Integer.parseInt(sort), "", "لیست دسته های اخبار");
            } else if (counter == 1) {
                html2 = "swGetNews(" + firstCode + ");";
            }
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryNews_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjCategoryNews.insert() + "' id='insert_Categorynews'>" + lbl_insert + "</button>"));
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
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));
            int level = 0;
            if (!parent.equals("0")) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName, _id + "=" + parent));
                level = Integer.parseInt(rows.get(0).get(_level).toString());
            }
            map.put(_level, ++level);
            map.put(_lang, 1);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCategoryNews.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }

            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_title, jjTools.getParameter(request, _title));
            int level = 0;
            if (!parent.equals("")) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName, _id + "=" + parent));
                level = Integer.parseInt(rows.get(0).get(_level).toString());
            }
            map.put(_level, ++level);
            map.put(_lang, 1);

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
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCategoryNews.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
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

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
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
            if (id.equals("1")) {
                String errorMessageId2 = "شما اجازه حذف این رکورد را ندارید.";
                if (jjTools.isLangEn(request)) {
                    errorMessageId2 = "You can't delete this record.";
                }
                return Js.dialog(errorMessageId2);
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            db.delete(tableName, _parent + "=" + id + " OR " + _parent + " = " + id);

            // change gallery category to default
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Pic._category_id, 1);
            db.update(News.tableName, map, News._category_id + "=" + id);
            Server.outPrinter(request, response, Js.jjCategoryNews.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
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
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setValSelectOption("#" + _parent, row.get(0).get(_parent).toString()));
//            html.append(Js.select2("#" + _parent, ""));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjCategoryNews.edit() + "' id='edit_CategoryNews'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjCategoryNews.delete(id) + "' id='delete_CategoryNews'>" + lbl_delete + "</button></div>";
            }
            html.append(Js.setHtml("#CategoryNews_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * This method return onli " li " tags menu of top level products use to set
     * sub menu for products
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getMenu(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            StringBuilder html = new StringBuilder();
//          String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));// =0 means only toplevel nod
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "swRight" : panel;
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<li><a onclick='swGetNews(" + row.get(i).get(_id) + ");swRightNewsMenu();'>" + row.get(i).get(_title) + "</a></li>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * This method return onli " li " tags menu of top level products use to set
     * sub menu for products برای باز کردن عناصر منو در کی صفحه جدا نباید از
     * جیکوئری استفاده کنیم
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getMenuTargetBlank(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            String langId = "1";//by defult language is 'fa' for Farsi
            if (jjTools.isLangEn(request)) {
                langId = "2";
            }
            if (jjTools.isLangAr(request)) {
                langId = "3";
            }
            StringBuilder html = new StringBuilder();
//          String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));// =0 means only toplevel nod
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "swRight" : panel;
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<li><a href='" + Server.newsJSP + "?jj=0&id=" + row.get(i).get(_id) + "'>" + row.get(i).get(_title) + "</a></li>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String categoryNewsSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder optionHtml = new StringBuilder();
            Document doc = Jsoup.parse("<option id='0' value='0'>دسته اخبار مورد نظر را انتخاب کنید</option>");
            List<Map<String, Object>> rowAllActiveCategouries = jjDatabase.separateRow(db.Select(tableName, "*", _id, _level + "," + _parent));// بر اساس حروف الفبا مرتب باشد بهتر است

            for (int i = 0; i < rowAllActiveCategouries.size(); i++) {
                doc.select("#" + rowAllActiveCategouries.get(i).get(_parent)).after("<option id='" + rowAllActiveCategouries.get(i).get(_id)
                        + "'  value='" + rowAllActiveCategouries.get(i).get(_id) + "'>"
                        + dash(rowAllActiveCategouries.get(i).get(_level).toString())
                        + rowAllActiveCategouries.get(i).get(_title)
                        + "</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".categoryNewsSelectOption";
            }
            if (needString) {
                return optionHtml.toString();
            }
            Server.outPrinter(request, response, Js.setHtml(panel, doc.select("body").toString()) );
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder optionHtml = new StringBuilder();
            Document doc = Jsoup.parse("<option id='0' value='0'>+</option>");
            List<Map<String, Object>> rowAllActiveCategouries = jjDatabase.separateRow(db.Select(tableName, "*", _id, _level + "," + _parent));// بر اساس حروف الفبا مرتب باشد بهتر است

            for (int i = 0; i < rowAllActiveCategouries.size(); i++) {
                doc.select("#" + rowAllActiveCategouries.get(i).get(_parent)).after("<option id='" + rowAllActiveCategouries.get(i).get(_id)
                        + "'  value='" + rowAllActiveCategouries.get(i).get(_id) + "'>"
                        + dash(rowAllActiveCategouries.get(i).get(_level).toString())
                        + rowAllActiveCategouries.get(i).get(_title)
                        + "</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".newsSelectOption";
            }
            if (needString) {
                return optionHtml.toString();
            }
            Server.outPrinter(request, response, Js.setHtml(panel, doc.select("body").toString()) + Js.select2(panel, "width: '100%'"));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    private static String dash(String level) {
        String dashanswer = "";
        for (int i = 1; i < Integer.parseInt(level); i++) {
            dashanswer += "--";
        }
        return dashanswer;
    }
}
