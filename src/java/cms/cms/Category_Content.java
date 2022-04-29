package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.PrintWriter;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Category_Content {

    public static String tableName = "category_content";
    public static String _id = "id";
    public static String _title = "category_content_title";
    public static String _parent = "category_content_parent";
    public static String _lang = "category_content_lang";
    public static String _level = "category_content_level";
    public static String _explain = "category_content_explain";
    public static String _pic = "category_content_pic";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static int rul_rfsAll = 265;
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + ">4"));
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی محتوای سازمانی</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی محتوای سازمانی</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsCategoryContent.m_add_new();' > دسته بندی جدید</a>"
                        + "        </p>");
            }
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshCategoryContent' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsCategoryContent.m_select(" + row.get(i).get(_id) + ");'  class='mousePointer' >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsCategoryContent.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swCategoryContentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryContent", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "4" : "", "لیست دسته های محتوای سازمانی");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryContent_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjCategoryContent.insert() + "' id='insert_CategoryContent'>" + lbl_insert + "</button>"));
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
            Content.resetCatchContentTitle(request, _parent, db, isPost);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }

            Map<String, Object> map = new HashMap();

            String parent = jjTools.getParameter(request, _parent);
            int level = 0;
            if (!parent.equals("0")) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName, _id + "=" + parent));
                level = Integer.parseInt(rows.get(0).get(_level).toString());
            }
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_lang, 1);
            map.put(_level, ++level);
            map.put(_pic, jjTools.getParameter(request, _pic));

            map.put(_explain, jjTools.getParameter(request, _explain));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCategoryContent.refresh());
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
            String parent = jjTools.getParameter(request, _parent);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_explain, jjTools.getParameter(request, _explain));
            map.put(_pic, jjTools.getParameter(request, _pic));
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            int level = 0;
            if (!parent.equals("0")) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName, _id + "=" + parent));
                level = Integer.parseInt(rows.get(0).get(_level).toString());
            }
            map.put(_level, ++level);
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
            Server.outPrinter(request, response, Js.jjCategoryContent.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
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
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id + " OR " + _parent + " = " + id);

            // change Content category to default
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Content._category_id, 1);
            db.update(Content.tableName, map, Content._category_id + "=" + id);
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
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _explain, row.get(0).get(_explain)));
            html.append(Js.setVal("#" + _pic, row.get(0).get(_pic)));
            html.append(Js.setValSelectOption("#" + _parent, row.get(0).get(_parent).toString()));
            html.append(Js.select2("#" + _parent, " width: '100%'"));
            html.append(Js.setVal("#content_category_attachPic", row.get(0).get(_pic)));
            html.append(Js.setSrc("#PicCategory", row.get(0).get(_pic)));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjCategoryContent.edit() + "' id='edit_CategoryContent'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjCategoryContent.delete(id) + "' id='delete_CategoryContent'>" + lbl_delete + "</button></div>";
            }
            html.append(Js.setHtml("#CategoryContent_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این متد همه نقش ها را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
     *
     * @param request panel سلکتور پنل است دقت شود ممکن است نامبر ساین نداشته
     * باشد یا نخواهد
     * @param response
     * @param db
     * @param needString
     * @return بصورت کد جی کوئری و یک سری آپشن برای قرار گرفتن در سلکتی که در
     * پنل معرفی شده
     * @throws Exception
     */
    public static String getSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            Document doc = Jsoup.parse("<option id='0' value='0'>دسته بندی خود را انتخاب کنید</option>");
            List<Map<String, Object>> rowAllActiveCategouries = jjDatabase.separateRow(db.otherSelect("SELECT * FROM category_content  ORDER BY category_content_level,category_content_parent;"));// بر اساس حروف الفبا مرتب باشد بهتر است

            for (int i = 0; i < rowAllActiveCategouries.size(); i++) {
                doc.select("#" + rowAllActiveCategouries.get(i).get(_parent)).after("<option id='" + rowAllActiveCategouries.get(i).get(_id)
                        + "'  value='" + rowAllActiveCategouries.get(i).get(_id) + "'>"
                        + dash(rowAllActiveCategouries.get(i).get(_level).toString())
                        + rowAllActiveCategouries.get(i).get(_title)
                        + "</option>");
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = "contentSelectOption";
            }
            if (needString) {
                return optionHtml.toString();
            }
            Server.outPrinter(request, response, Js.setHtml("." + panel, doc.select("body").toString()));
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

    /**
     * برای استفاده از آموزش های عمومی و اختصاصی
     *
     * لیست کردن محتواها براساس دسته
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getSubCategouriesContent(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            Integer categoryId = Integer.parseInt(jjTools.getParameter(request, "categoryContentId").toString());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryId));
            List<Map<String, Object>> rowParent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + categoryId));
            StringBuilder html = new StringBuilder("");
            if (!row.isEmpty()) {
            System.out.println("...............................................lkjnhbghjk.." + row.size());
                html.append("<div class=\"row\">\n"
                        + "    <img src='upload/" + rowParent.get(0).get(Category_Content._pic) + "' width=\"100%\" id=\"slide_kanon\" style=\"margin-bottom: 28px;;margin-top: 159px;max-height: 500px\">\n"
                        + "</div>");
                html.append("<div class=\"container-fluid\">\n"
                        + "            <div class='row'>");
                html.append("<div class='text-center text'>\n"
                        + "        <h2 class='mb-3 '>" + rowParent.get(0).get(_title) + "</h2>\n"
                        + "        <span class='brd-separetor'><img src='template/images/icons/title.png'></span>\n"
                        + "    </div>");
                for (int i = 0; i < row.size(); i++) {
//                if (!row.get(i).get(Category_Content._pic).toString().equals("")) {
                    html.append("<div class=\"col-md-3 col-sm-4\" onclick=''>");
                    html.append("<div class=\"wrimagecard wrimagecard-topimage\">");
                    html.append("<a href='kanoonha.jsp?category=" + row.get(i).get(Category_Content._id) + "'>");
                    html.append("<div class=\"wrimagecard-topimage_header\" style=\"background-color:rgba(187, 120, 36, 0.1) \">");
                    html.append("<center><img src='upload/" + row.get(i).get(Category_Content._pic).toString() + "' style=\"color:#BB7824\"></i></center></div>");
                    html.append(" <div class=\"wrimagecard-topimage_title\"><h4>" + row.get(i).get(Category_Content._title).toString() + "<div class=\"pull-right badge\">18</div></h4></div>");
                    html.append("</a></div></div>");
                }
                html.append("</div>");
                html.append("</div>");
            } else {

                List<Map<String, Object>> contentRows = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + categoryId));
                List<Map<String, Object>> contentRowsParent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + categoryId));
                html.append("<div class=\"row\">\n"
                        + "    <img src='upload/" + contentRowsParent.get(0).get(_pic) + "' width=\"100%\" id=\"slide_kanon\" style=\"margin-bottom: 28px;margin-top: 159px;max-height: 500px\">\n"
                        + "</div>");
                html.append("<div class=\"container-fluid\">\n"
                        + "            <div class='row'>");
                html.append("<div class='text-center text'>\n"
                        + "        <h2 class='mb-3 '>" + contentRowsParent.get(0).get(_title) + "</h2>\n"
                        + "        <span class='brd-separetor'><img src='template/images/icons/title.png'></span>\n"
                        + "    </div>");
                for (int i = 0; i < contentRows.size(); i++) {
                    html.append("<div class=\"col-md-3 col-sm-4\" onclick='sw_1(" + contentRows.get(i).get(Content._id).toString() + ")'>");
                    html.append("<div class=\"wrimagecard wrimagecard-topimage\">");
                    if (contentRows.get(0).get(Content._content_page).equals("")) {//صفحه جی اس پس اختصاصی دارد یا خیر
                        html.append("<a href='Server?do=Content.sw&text=" + contentRows.get(i).get(Content._title).toString() + "'>");
                    } else {
                        html.append("<a href='" + contentRows.get(0).get(Content._content_page) + "'>");
                    }
                    html.append("<div class=\"wrimagecard-topimage_header\" style=\"background-color:rgba(187, 120, 36, 0.1) \">");
                    html.append("<center><img src='upload/" + contentRows.get(i).get(Content._pic).toString() + "' style=\"color:#BB7824\"></i></center></div>");
                    html.append(" <div class=\"wrimagecard-topimage_title\"><h4>" + contentRows.get(i).get(Content._title).toString() + "<div class=\"pull-right badge\"></div></h4></div>");
                    html.append("</a></div></div>");
                }
                html.append("</div>");
                html.append("</div>");
            }
            String script = Js.setHtml("#sw", html);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getSubCategouriesContent_1(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            Integer categoryId = Integer.parseInt(jjTools.getParameter(request, "categoryContentId").toString());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryId));
            System.out.println("................................................" + row);
//            List<Map<String, Object>> rowParent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + categoryId));
            StringBuilder html = new StringBuilder("");
            if (!row.isEmpty()) {
            System.out.println(".......................................jhghj........." + row.size());
                html.append("<div class='trending-area fix'>\n"
                        + "                <div class='trending-main'>\n"
                        + "                    <div class='trending-bottom'>"
                        + "                    <div class='row'>");
                for (int i = 0; i < row.size(); i++) {
                    html.append("<div onclick='subKanoon(" + row.get(i).get(Category_Content._id) + ")' class='col-lg-3'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                    html.append("    <img src='upload/" + row.get(i).get(Category_Content._pic).toString() + "' alt=''>\n"
                            + "        </div>");
                    html.append("<div class='trend-bottom-cap'><span class='color1'>\n"
                            + "                " + row.get(i).get(Category_Content._title).toString() + "</span>");
                    html.append("<h4><a href=''>" + row.get(i).get(Category_Content._explain).toString() + "</a></h4></div>");
                    html.append("</div></div>");
                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
            } else {

                List<Map<String, Object>> contentRows = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + categoryId));
                List<Map<String, Object>> contentRowsParent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + categoryId));
                html.append("<div class='trending-area fix'>\n"
                        + "                <div class='trending-main'>\n"
                        + "                    <div class='trending-bottom'>"
                        + "                    <div class='row'>");

                for (int i = 0; i < contentRows.size(); i++) {
                    String page = contentRows.get(i).get(Content._content_page).toString();
                    if (page.equals("")) {
                        System.out.println("..");
                        html.append("" + contentRows.get(i).get(Content._content) + "");
                    } else {
                        System.out.println("...");
                        html.append("<a href='" + contentRows.get(0).get(Content._content_page) + "'>" + contentRows.get(i).get(Content._content) + "</a>");
                    }
                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
            }
            String script = Js.setHtml("#sw_1", html);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getSubGhoranContent(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            Integer categoryId = Integer.parseInt(jjTools.getParameter(request, "categoryContentId").toString());
            System.out.println("................................................");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Content.tableName, Content._id + "=" + categoryId));
            StringBuilder html = new StringBuilder("");
            if (!row.isEmpty()) {
                html.append(row.get(0).get(Content._content));
            }
            String script = Js.setHtml("#sw", html);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * برای استفاده از آموزش های عمومی و اختصاصی
     *
     * لیست کردن محتواها براساس دسته
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String dispatchJsp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> contentRows = jjDatabase.separateRow(db.Select(Content.tableName, Content._id + "=" + id));
            if (contentRows.get(0).get(Content._content_page).equals("")) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                request.getRequestDispatcher("content.jsp").forward(request, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                String page = contentRows.get(0).get(Content._content_page).toString();
                System.out.println("...." + page);
                request.getRequestDispatcher("'" + page + "'").forward(request, response);
            }

        } catch (Exception e) {
        }
        return null;
    }

}
