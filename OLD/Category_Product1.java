package cms.cms;

import cms.access.*;
import cms.tools.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Category_Product {

    public static String tableName = "category_product";
    public static String _id = "id";
    public static String _title = "category_product_title";
    public static String _parent = "category_product_parent";
    public static String _lang = "category_product_lang";
    public static String _level = "category_product_level";
    public static String _explain = "category_product_explain";
    public static String _icon = "category_product_icon";
    public static String _pic = "category_product_pic";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";

    public static int rul_rfs = 241;
    public static int rul_ins = 242;
    public static int rul_edt = 243;
    public static int rul_dlt = 244;

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
                        + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی محصولات</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت دسته بندی محصولات</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsCategoryProducts.m_add_new();' > دسته بندی جدید</a>"
                        + "        </p>");
            }
            html.append("<table id='refreshCategoryProduct' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsCategoryProducts.m_select(" + row.get(i).get(_id) + ");'  class='mousePointer' >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsCategoryProducts.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swCategoryProductTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryProduct", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست دسته های اخبار سازمانی");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع لیست محصولات را ایجاد میکند و فقط موقعی که از سمت کلاینت اسکریپت
     * مینویسیم باید ایدی پدر محصول را وارد کنیم اگر فقط یک نوع از محصولات داریم
     * را 0 وارد میکنیم و در غیر این صورت ایدی مورد نظر را وراد میکنیم function
     * runProductList(){ var param = ""; param
     * +="do=Category_Product.getHierarchyDiv"; param += "&categoryProductId=0";
     * new jj(param).jjAjax2(false); }
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getHierarchyDiv(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            String html = "";
            String script = "";
            String panel = jjTools.getParameter(request, "panel");
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId").toString());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT "
                    + "c2p." + _id + ","
                    + "c2p." + Category_Product._level + ","
                    + "c2p." + Category_Product._parent + ","
                    + "c2p." + Category_Product._title + ","
                    + "p." + Product._category_id + ","
                    + "p." + Product._id + ","
                    + "p." + Product._name + ""
                    + " FROM category_product c2p "
                    + " left join account_product p on (c2p.id=p.account_product_category_id)"
                    + "WHERE " + " p.account_product_category_id =c2p.id "
            ));
            html += "<li  class='listHirechiveDiv listHirechiveDivDetails cursorPointer' id='HirechiveDiv' onclick='showShoppingCart();'><i class='fas fa-shopping-cart'></i><span class='listHirechiveDivSpan' id='productNums'>0</span></li>";
            html += "<li  class='listHirechiveDiv listHirechiveDivDetails cursorPointer' id='HirechiveDivFactor' onclick='showAllMyFactor();'><span>فاکتورهای من</span><span class='listHirechiveDivSpan' id='productNums'>0</span></li>";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, "*", _id, _level + "," + _parent));// بر اساس حروف الفبا مرتب باشد بهتر است
            if (jjTools.getParameter(request, "categoryProductId").toString().equals("4")) {
                html = getListConsulting(html, row, rowCategory, categoryProductId, db);
                if (panel == "") {
                    panel = "swRight";
                }
            }
            if (jjTools.getParameter(request, "categoryProductId").toString().equals("3")) {
                html = getListOnlineReservationApp(html, row, rowCategory, categoryProductId, db);
                if (panel == "") {
                    panel = "swRight";
                }
            }
            if (jjTools.getParameter(request, "categoryProductId").toString().equals("2")) {
                html = getListAcceptOnlineApp(html, row, rowCategory, categoryProductId, db);
                if (panel == "") {
                    panel = "swRight";
                }
            }
            if (jjTools.getParameter(request, "categoryProductId").toString().equals("1")) {
                html = getListProducts(html, row, rowCategory, categoryProductId, db);
                if (panel == "") {
                    panel = "swRight";
                }
            }
            if (isPost) {
                return html;
            } else {
                script += Js.setHtml("#" + panel, html);
                Server.outPrinter(request, response, script + "if (new jj(\"productNums\").jjCookieGet() !== \"\") {\n"
                        + "					$(\"#productNums\").html(new jj(\"productNums\").jjCookieGet());\n"
                        + "				} else {\n"
                        + "					$(\"#productNums\").html(0);\n"
                        + "				}");
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
    //ایجاد لیست سایت با تابع بازگشتی

    private static String getListProducts(String html, List<Map<String, Object>> row, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiled(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(_id).toString()));
            if (sumChiled == 0) {
                int sum = 0;
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).get(Product._category_id).equals(rowCategory.get(i).get(_id))) {
                        sum++;
                    }
                }
                html += "<li class='listHirechiveDiv' value='" + rowCategory.get(i).get(_title) + "' style='' onclick='runGetProducts(" + rowCategory.get(i).get(_id) + ");'><span>"
                        + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp"
                        + rowCategory.get(i).get(_title)
                        + "</span><span class='listHirechiveDivSpan'>" + sum + "</span></li>";
            } else {
                html += "<li class='listHirechiveDiv cursorPointer' id='HirechiveDiv" + rowCategory.get(i).get(Category_Product._id) + "' onclick='togleList(" + rowCategory.get(i).get(Category_Product._id) + ");' ><span>" + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp" + rowCategory.get(i).get(Category_Product._title) + "</span><i class='far fa-plus' id='down" + rowCategory.get(i).get(Category_Product._id) + "'></i><i class='far fa-minus' id='up" + rowCategory.get(i).get(Category_Product._id) + "' style='display:none;'></i></li><ul class='subMenuLstHirechiveDiv' id='HirechiveListDiv" + rowCategory.get(i).get(Category_Product._id) + "' style='display: none;'>";
                html = getListProducts(html, row, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
                html += "</ul>";
            }
        }
        return html;
    }
    //ایجاد لیست سایت با تابع بازگشتی

    private static String getListConsulting(String html, List<Map<String, Object>> row, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiled(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(_id).toString()));
            if (sumChiled == 0) {
                int sum = 0;
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).get(Product._category_id).equals(rowCategory.get(i).get(_id))) {
                        sum++;
                    }
                }
                html += "<li class='listHirechiveDiv' value='" + rowCategory.get(i).get(_title) + "' style='' onclick='runGetConsulting(" + rowCategory.get(i).get(_id) + ");'><span>"
                        + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp"
                        + rowCategory.get(i).get(_title)
                        + "</span><span class='listHirechiveDivSpan'>" + sum + "</span></li>";
            } else {
                html += "<li class='listHirechiveDiv cursorPointer' id='HirechiveDiv" + rowCategory.get(i).get(Category_Product._id) + "' onclick='togleList(" + rowCategory.get(i).get(Category_Product._id) + ");' ><span>" + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp" + rowCategory.get(i).get(Category_Product._title) + "</span><i class='far fa-plus' id='down" + rowCategory.get(i).get(Category_Product._id) + "'></i><i class='far fa-minus' id='up" + rowCategory.get(i).get(Category_Product._id) + "' style='display:none;'></i></li><ul class='subMenuLstHirechiveDiv' id='HirechiveListDiv" + rowCategory.get(i).get(Category_Product._id) + "' style='display: none;'>";
                html = getListConsulting(html, row, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
                html += "</ul>";
            }
        }
        return html;
    }
    //ایجاد لیست سایت با تابع بازگشتی

    private static String getListOnlineReservationApp(String html, List<Map<String, Object>> row, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiled(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(_id).toString()));
            if (sumChiled == 0) {
                int sum = 0;
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).get(Product._category_id).equals(rowCategory.get(i).get(_id))) {
                        sum++;
                    }
                }
                html += "<li class='listHirechiveDiv' value='" + rowCategory.get(i).get(_title) + "' style='' onclick='getOnlineReservationApp(" + rowCategory.get(i).get(_id) + ");'><span>"
                        + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp"
                        + rowCategory.get(i).get(_title)
                        + "</span><span class='listHirechiveDivSpan'>" + sum + "</span></li>";
            } else {
                html += "<li class='listHirechiveDiv cursorPointer' id='HirechiveDiv" + rowCategory.get(i).get(Category_Product._id) + "' onclick='togleList(" + rowCategory.get(i).get(Category_Product._id) + ");' ><span>" + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp" + rowCategory.get(i).get(Category_Product._title) + "</span><i class='far fa-plus' id='down" + rowCategory.get(i).get(Category_Product._id) + "'></i><i class='far fa-minus' id='up" + rowCategory.get(i).get(Category_Product._id) + "' style='display:none;'></i></li><ul class='subMenuLstHirechiveDiv' id='HirechiveListDiv" + rowCategory.get(i).get(Category_Product._id) + "' style='display: none;'>";
                html = getListConsulting(html, row, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
                html += "</ul>";
            }
        }
        return html;
    }
    //ایجاد لیست سایت با تابع بازگشتی

    private static String getListAcceptOnlineApp(String html, List<Map<String, Object>> row, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiled(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(_id).toString()));
            if (sumChiled == 0) {
                int sum = 0;
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).get(Product._category_id).equals(rowCategory.get(i).get(_id))) {
                        sum++;
                    }
                }
                html += "<li class='listHirechiveDiv' value='" + rowCategory.get(i).get(_title) + "' style='' onclick='runGetAcceptOnlineApp(" + rowCategory.get(i).get(_id) + ");'><span>"
                        + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp"
                        + rowCategory.get(i).get(_title)
                        + "</span><span class='listHirechiveDivSpan'>" + sum + "</span></li>";
            } else {
                html += "<li class='listHirechiveDiv cursorPointer' id='HirechiveDiv" + rowCategory.get(i).get(Category_Product._id) + "' onclick='togleList(" + rowCategory.get(i).get(Category_Product._id) + ");' ><span>" + dashForListProducts(rowCategory.get(i).get(_level).toString()) + "&nbsp" + rowCategory.get(i).get(Category_Product._title) + "</span><i class='far fa-plus' id='down" + rowCategory.get(i).get(Category_Product._id) + "'></i><i class='far fa-minus' id='up" + rowCategory.get(i).get(Category_Product._id) + "' style='display:none;'></i></li><ul class='subMenuLstHirechiveDiv' id='HirechiveListDiv" + rowCategory.get(i).get(Category_Product._id) + "' style='display: none;'>";
                html = getListConsulting(html, row, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
                html += "</ul>";
            }
        }
        return html;
    }

    //این تابع برای زیبا کردن لیست های سایت هست چون میخواهیم فرزندان هر کدومشون زیر خودشون نشون داده بشه
    private static String dashForListProducts(String level) {
        String dashanswer = "";
        for (int i = 2; i < Integer.parseInt(level); i++) {
            dashanswer += "--";
        }
        return dashanswer;
    }

    private static int getSumChiled(List<Map<String, Object>> rowCategory, Integer numberID) {
        Integer sum = 0;
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            if (Integer.parseInt(rowCategory.get(i).get(_parent).toString()) == numberID) {
                sum++;
            }
        }
        return sum;
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryProduct_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjCategoryProduct.insert() + "' id='insert_Categorynews'>" + lbl_insert + "</button>"));
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
            Server.outPrinter(request, response, Js.jjCategoryProduct.refresh());
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
            if (!parent.equals("0")) {
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
            Server.outPrinter(request, response, Js.jjCategoryProduct.refresh());
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id));
            int rowCount = row.size();
            if (rowCount > 0) {
                Server.outPrinter(request, response, "این رکورد قابل حذف نیست برای اینکه دارای چند زیر مجموعه میباشد");
                return "";
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjCategoryProduct.refresh());
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
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjCategoryProduct.edit() + "' id='edit_CategoryProduct'>" + lbl_edit + "</button>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjCategoryProduct.delete(id) + "' id='delete_CategoryProduct'>" + lbl_delete + "</button>";
            }
            html.append(Js.setHtml("#CategoryProduct_button", htmlBottons));
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

    public static String categoryProductsSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
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
                panel = ".categoryNewsSelectOption";
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

    public static String getSelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder optionHtml = new StringBuilder();
            Document doc = Jsoup.parse("<option id='0' value='0'>لطفا یک دسته را انتخاب کنید</option>");
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
                panel = ".categoryProductSelectOption";
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
//این تابع برای زیبا کردن سلکت اپشن هست چون میخواهیم فرزندان هر کدومشون زیر خودشون نشون داده بشه

    private static String dash(String level) {
        String dashanswer = "";
        for (int i = 1; i < Integer.parseInt(level); i++) {
            dashanswer += "--";
        }
        return dashanswer;
    }

    /**
     * این متد لیست یک سطحی از دسته بندی محوصلات برای سویپر اسلایدر مثل سایت
     * بامبو یا عطاویچ میدهد
     *
     * @param request
     * @param response
     * @param db

     * @return
     * @throws Exception
     */
    public static String getOneLevelListSweperSlider(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needHTML) throws Exception {
        try {
            String categuryId = jjTools.getParameter(request, "category");
            StringBuilder categouriesHtml = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + (categuryId == "" ? "0" : categuryId)));
            categouriesHtml.append("<div class='swiper1 col-lg'><div class='swiper-wrapper'>");
            for (int i = 0; i < row.size(); i++) {
                categouriesHtml.append("<div class='swiper-slide'><img src='" + row.get(i).get(_icon).toString() + "' />"
                        + "<div class='product_cat '>" + row.get(i).get(_title).toString() + "</div></div>");
            }
            categouriesHtml.append("</div></div>");
            // // محصولات فعال در هر دسته
            StringBuilder productsHtml = new StringBuilder();
            productsHtml.append("<div class='swiper2'><div class='swiper-wrapper'>");
            for (int i = 0; i < row.size(); i++) {
                productsHtml.append("<div class='row swiper-slide'>");
                request.setAttribute("categoryProductId", row.get(i).get(_id));
                productsHtml.append(Product.getList(request,response, db,true));
                productsHtml.append("</div>");
            }
            productsHtml.append("</div>");
            productsHtml.append("</div>");

//                productsHtml.append("<div class='swiper-slide'><img src='" + row.get(i).get(_icon).toString() + "' />"
//                        + "<div class='shop__single__details shop__cat '>" + row.get(i).get(_title).toString() + "</div></div>");
            if (needHTML) {
                return categouriesHtml.toString() + productsHtml.toString();
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "#swSweper";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, categouriesHtml.toString() + productsHtml.toString()));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=0"));
            for (int i = 0; i < row.size(); i++) {
                html.append(getchiledRoot(row.get(i).get(_id).toString(), row.get(i).get(_title).toString(), 0, db));
            }
            if (isPost) {
                return html.toString();
            }
            Server.outPrinter(request, response, Js.setHtml("#collapse0", html));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getchiledRoot(String categoryId, String name, int j, jjDatabaseWeb db) throws Exception {
        StringBuilder html = new StringBuilder();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryId));
        if (row.size() > 0) {
            html.append("<div id='heading" + categoryId + "'>\n"
                    + "    <h2 data-toggle='collapse' data-target='#collapse" + categoryId + "' aria-expanded='true' aria-controls='collapse" + categoryId + "' style='font-size: 15px;margin-bottom: .5rem;'>\n"
                    + "        <i class='fa text-primary' aria-hidden='true'></i>\n"
                    + "        " + name + "<span class='text-muted' >  (" + getCountProductCategory(categoryId, db) + ")</span>\n"
                    + "    </h2>\n"
                    + "</div>\n");
            html.append("<div class='collapse show hover_effect ml-3' id='collapse" + categoryId + "' aria-labelledby='heading" + categoryId + "' data-parent='#collapse" + j + "'>");
            for (int i = 0; i < row.size(); i++) {
                html.append(getchiledRoot(row.get(i).get(_id).toString(), row.get(i).get(_title).toString(), Integer.parseInt(categoryId), db));
            }
            html.append("</div>");
        } else if (row.size() == 0) {
            html.append("<h2  style='font-size: 15px;margin-bottom: .5rem;'>\n"
                    + "                <i class='fa fa-angle-double-left'></i>\n"
                    + "                <a style='cursor: pointer' onclick='swGetProducts(" + categoryId + ");'>" + name + "<span class='text-muted'>(" + getCountProductCategory(categoryId, db) + ")</span></a>\n"
                    + "            </h2>");
        }
        return html.toString();
    }

    public static int getCountProductCategory(String categoryId, jjDatabaseWeb db) throws Exception {
        int categoryProductId = Integer.parseInt(categoryId);
        String categoryID = "";
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
        categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
        String where = "";
        if (categoryID.equals("")) {
            where += Product._category_id + "=" + categoryProductId;
        } else {
            String[] splitCPID = categoryID.split(",");
            for (int i = 0; i < splitCPID.length; i++) {
                if (i == splitCPID.length - 1) {
                    where += Product._category_id + "=" + splitCPID[i];
                } else {
                    where += Product._category_id + "=" + splitCPID[i] + " OR ";
                }
            }
        }
        List<Map<String, Object>> products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._priority + "<=2" + " AND " + Product._active + "=1 AND (" + where + ")", Product._date));
        return products.size();
    }

    private static String getCategoryProducts(String categoryID, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiledProduct(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()));
            if (sumChiled == 0) {
                categoryID += rowCategory.get(i).get(Category_Product._id).toString() + ",";
            } else {
                categoryID = getCategoryProducts(categoryID, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
            }
        }
        return categoryID;
    }

    private static int getSumChiledProduct(List<Map<String, Object>> rowCategory, Integer numberID) {
        Integer sum = 0;
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            if (Integer.parseInt(rowCategory.get(i).get(Category_Product._parent).toString()) == numberID) {
                sum++;
            }
        }
        return sum;
    }
     /**
     * این تابع همه ای محصولات را از جدیدترین محصول تا قدیمی ترین محصول را نشان
     * میدهد
     *
     * @param request
     * @param response
     * @param dbcategoryProductId
     * @param isAjax
     * @return
     * @throws Exception
     */
    public static String getProducts(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            System.out.println("//");
            String script = "";
            StringBuilder html = new StringBuilder();
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            System.out.println("............" + categoryProductId);
            List<Map<String, Object>> rowCategory1 = jjDatabase.separateRow(db.Select(Category_Product.tableName, _parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
            System.out.println(",,,,,,,,,,,,,,," + rowCategory1);
            if (rowCategory1.isEmpty()) {
                System.out.println("kk");
                List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
                String categoryID = "";
                categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
                String where = "";
                if (categoryID.equals("")) {
                    System.out.println("////");
                    System.out.println("////"+categoryProductId);
                    where += Product._category_id + "=" + categoryProductId;
                    System.out.println("////"+where);
                } else {
                    String[] splitCPID = categoryID.split(",");
                    for (int i = 0; i < splitCPID.length; i++) {
                        if (i == splitCPID.length - 1) {
                            where += Product._category_id + "=" + splitCPID[i];
                        } else {
                            where += Product._category_id + "=" + splitCPID[i] + " OR ";
                        }
                    }
                }
                String panel = jjTools.getParameter(request, "panel");
                panel = panel.equals("") ? "sw" : panel;
                System.out.println(panel);
                List<Map<String, Object>> products;
                if (categoryProductId == 1) {
                    products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + Product._active + "=1 AND (" + where + ")", Product._date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
                } else {
                    System.out.println("????");
                    products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._active + "=1 AND (" + where + ")", Product._date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
                    System.out.println("//" + products);
                    script = "$('#sidebar').toggleClass('active');";
                }
                StringBuilder optionHtml = new StringBuilder();
                String categoryGroupId = "";
                int category = categoryProductId;
                while (category > 0) {
                    System.out.println("b");
                    System.out.println(category);
                    categoryGroupId += category + ",";
                    List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + category));
                    category = Integer.parseInt(row.get(0).get(Category_Product._parent).toString());
                }
                String[] seprateCategory = categoryGroupId.split(",");
                for (int i = seprateCategory.length - 1; i >= 0; i--) {
                    if (i == seprateCategory.length - 1) {
                        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[i]));
                        optionHtml.append("<a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + row.get(0).get(Category_Product._id) + ");'>" + row.get(0).get(Category_Product._title) + "</a> ");
                    } else {
                        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[i]));
                        optionHtml.append("&gt; <a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + row.get(0).get(Category_Product._id) + ");'>" + row.get(0).get(Category_Product._title) + "</a> ");
                    }
                }
                optionHtml.append("</p>");
                html.append("<div class=\"row\" style=\"margin-left: 0px;margin-right: 0px;\" dir=\"ltr\">");
                html.append("<div class=\"container-fluid container-news m-t-news-30\">\n"
                        + "                    <section class=\"section-default section-footer \">\n"
                        + "                        <div class=\"container container-news\">\n"
                        + "                            <div class=\"row\">\n"
                        + "                                <div class=\"col-md-12 top-50\">");
                for (int i = 0; i < products.size(); i++) {

                    html.append("<div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-12\" style='margin:2px 0px'>");
                    html.append("<div class=\"box-part text-center\">");
                    html.append("<img src='upload/" + products.get(i).get(Product._pic1) + "' width='100%' '>");
                    html.append("<div class=\"title\"><h4>" + products.get(i).get(Product._name) + "</h4></div>");
                    html.append("<div class=\"text\"><span>" + products.get(i).get(Product._quantity) + "ظرفیت:</span></div>");
                    html.append("<a onclick='addToShoppingCart(" + products.get(i).get(Product._id) + ")'>ثبت نام در این دوره</a>");
                    html.append("</div>");
                    html.append("</div>");
                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</section>");
                html.append("</div>");
                if (isJsp) {
                    return html.toString();
                } else {
                    Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.setHtml("#hrefCollapse", optionHtml.toString()) + script);
                    return "";
                }
            } else {
                html.append("<div class=\"container-fluid container-news m-t-news-30\">\n"
                        + "                    <section class=\"section-default section-footer \">\n"
                        + "                        <div class=\"container container-news\">\n"
                        + "                            <div class=\"row\">\n"
                        + "                                <div class=\"col-md-12 top-50\">");
                for (int i = 0; i < rowCategory1.size(); i++) {
                    html.append("<div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-12\" style='margin:2px 0px'>");
                    html.append("<div class=\"box-part text-center\">");
                    html.append("<i class=\"far fa-instagram fa-3x\" aria-hidden=\"true\"></i>");
                    html.append("<div class=\"title\"><h4>" + rowCategory1.get(i).get(Category_Product._title) + "</h4></div>");
                    html.append("<div class=\"text\"><span>اردو محور</span></div>");
                    html.append("<a href='products.jsp?category=" + rowCategory1.get(i).get(Category_Product._id) + "'>" + rowCategory1.get(i).get(Category_Product._title) + "</a>");
                    html.append("</div>");
                    html.append("</div>");
                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</section>");
                html.append("</div>");
                Server.outPrinter(request, response, Js.setHtml("#sw", html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
