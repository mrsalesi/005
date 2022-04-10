package cms.cms;

import cms.tools.*;
import cms.access.*;
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
    public static String _pic = "category_content_pic";
    public static String _explain = "category_content_explain";
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName,_id+">2"));
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
            map.put(_explain, jjTools.getParameter(request, _explain));
            map.put(_pic, jjTools.getParameter(request, _pic));
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
            html.append(Js.select2("#" + _parent, ""));
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
}
