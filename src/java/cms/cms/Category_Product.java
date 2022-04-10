package cms.cms;

import HMIS.Plans;
import cms.tools.*;
import cms.access.*;
import static cms.cms.Content.previousLang;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Category_Product {

    public static String tableName = "category_product";
    public static String _id = "id";
    public static String _title = "category_product_title";
    public static String _parent = "category_product_parent";
    public static String _lang = "category_product_lang";
    public static String _level = "category_product_level";
    public static String _pic = "category_product_pic";
    public static String _script = "category_product_script";
    public static String _explain = "category_product_explain";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";

    public static int rul_rfs = 387;
    public static int rul_ins = 388;
    public static int rul_edt = 389;
    public static int rul_dlt = 390;

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
     * این متد برای برگرداندن لیست تا یک سطح برای استفاده در اپلیکیشن برای لیست
     * نوبت بکار میرود
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getSubCategouries(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            Integer categoryId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId").toString());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + categoryId));
            StringBuilder html = new StringBuilder("");
            html.append("<section class='page-section ' id='portfolio'>\n"
                    + "            <div class='container'>\n"
                    //                    + "                <div class='text-center'>\n"
                    //                    + "                    <h2 class='text-uppercase' style='color:white;text-shadow:0 0 10px #000'>" + row.get(0).get(Category_Product._title).toString() + "</h2>\n"
                    //                    + "                </div>\n"
                    + "                <div class='row mg-t-20'>");
            for (int i = 0; i < row.size(); i++) {
                if (!row.get(i).get(_pic).toString().equals("")) {
                    html.append("<div class=\"col-6 mb-3\" onclick='getSubCategouries(" + row.get(i).get(_id).toString() + ");'>");
                    html.append("<img class=\"col-12 rounded\" style=\"padding:0;height:90%\" src='upload/" + row.get(i).get(_pic).toString() + "' alt='" + row.get(i).get(_title).toString() + "'></a>");
                    html.append("</div>");
                } else {

                    html.append(" <div class='col-6 mb-3' onclick='getSubCategouries(" + row.get(i).get(_id).toString() + ");'>    "
                            + "  <div class='col-12' style='background-color: #" + Plans.generateRandomChars("abcdef1234567", 6) + ";border-radius: 8px;height:90%;'>   "
                            + "             <div class=\"row\">  "
                            + "   <h4  style='font-size:1rem;color:white;padding:30%'> " + row.get(i).get(Category_Product._title).toString()
                            + "   </h4>                   "
                            + "        </div>         "
                            + "   </div>   "
                            + "  </div>  ");
                }
            }
            html.append("</div>");
            List<Map<String, Object>> productRows = jjDatabase.separateRow(db.Select(Product.tableName, Product._category_id + "=" + categoryId));
            html.append(" <div class='row mg-t-20'>");
            for (int i = 0; i < productRows.size(); i++) {
                html.append("<div class='col-lg-4 col-sm-6 mb-4' onclick='sw(" + productRows.get(i).get(Product._id).toString() + ");'>");
                html.append("    <div class='portfolio-item'>\n");
                html.append("            <img class='img-fluid' style='float:right;border-radius:15px;box-shadow:0 0 10px #eee;max-width:25%;width:18%;height:70px;' src='upload/" + productRows.get(i).get(Product._pic1).toString() + "' alt='" + productRows.get(i).get(Product._name).toString() + "'>\n");
                html.append("        <div class='portfolio-caption' style='background-color:rgb(70 65 65 / 20%);border-radius:10px 20px 20px 10px'>\n");
                html.append("            <p  style='color:white;text-shadow:0 0 10px #000;font-weight:600'>" + productRows.get(i).get(Product._name).toString() + "</p>\n");
                html.append("        </div>\n");
                html.append("    </div>\n");
                html.append("</div>");
            }
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
//            html.append("</section>");
            String script = Js.setHtml("#sw", html);
            Server.outPrinter(request, response, script);
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

    public static String getSubCategouriesProduct(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
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
            String script = Js.setHtml("#sw", html);
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
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
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_pic, jjTools.getParameter(request, "category_product_pic"));
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
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_pic, jjTools.getParameter(request, "category_product_pic"));
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
            html.append(Js.setVal("#product_category_attachPic", row.get(0).get(_pic)));
            html.append(Js.setSrc("#PicCategoryProduct", row.get(0).get(_pic)));
            html.append(Js.setHtml("#category_product_script", row.get(0).get(_script)));
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

//    /**
//     * این تابع همه ای محصولات را از جدیدترین محصول تا قدیمی ترین محصول را نشان
//     * میدهد
//     *
//     * @param request
//     * @param response
//     * @param dbcategoryProductId
//     * @param isAjax
//     * @return
//     * @throws Exception
//     */
//    public static String getSubProductHeader(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
//        try {
//            String categoryProductId = jjTools.getParameter(request, "categoryProduct");
//            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + categoryProductId));
//            if (!rowCategory.isEmpty()) {
//                List<Map<String, Object>> rowCategory_level2 = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + categoryProductId));
//            } else {
////                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._category_id + "=" + categoryProductId));
//
//            }
//
//        } catch (Exception ex) {
//            Server.outPrinter(request, response, Server.ErrorHandler(ex));
//            return "";
//        }
//        return null;
//    }
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
            System.out.println("//morte");
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
                System.out.println("////" + categoryID);
                String where = "";
                if (categoryID.equals("")) {
                    where += Product._category_id + "=" + categoryProductId;
                    System.out.println("////" + where);
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
                System.out.println("////" + categoryProductId);
                String panel = jjTools.getParameter(request, "panel");
                panel = panel.equals("") ? "sw_1" : panel;
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
                html.append("<div class='trending-area fix' style='width:100%'>\n"
                        + "                <div class='trending-main'>\n"
                        + "                    <div class='trending-bottom'>"
                        + "                    <div class='row'>");
                for (int i = 0; i < products.size(); i++) {
                    if (rowCategory1.size() <= 2) {
                        html.append("<div onclick='RgisterName(" + products.get(i).get(Product._id) + ")' class='col-lg-6 col-md-6'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                    } else {
                        html.append("<div onclick='RgisterName(" + products.get(i).get(Product._id) + ")' class='col-lg-4 col-md-4'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                    }
                    html.append("    <img src='upload/" + products.get(i).get(Product._pic1).toString() + "' alt=''>\n"
                            + "        </div>");
                    html.append("<div class='trend-bottom-cap'><span class='color1'>\n"
                            + "                " + products.get(i).get(Product._name) + "</span>");
                    html.append("<h4><a href=''>" + products.get(i).get(Product._quantity).toString() + "</a></h4></div>");
                    html.append("</div></div>");

//                    html.append("<div class=\"col-md-3 col-sm-6\">");
//                    html.append("<div class=\"wrimagecard wrimagecard-topimage\">");
//                    html.append("<a >");
//                    html.append("<div class=\"wrimagecard-topimage_header\" style=\"background-color:rgba(187, 120, 36, 0.1) \">");
//                    if (products.get(i).get(Product._pic1).equals("")) {
//                        html.append("<center><img src='template1/img/nashrye.jpg' style=\"color:#BB7824\"></center></div>");
//                    } else {
//                        html.append("<center><img src='upload/" + products.get(i).get(Product._pic1) + "' style=\"color:#BB7824\"></center></div>");
//                    }
//                    html.append("<div class=\"wrimagecard-topimage_title\">");
//                    html.append("<h4>" + products.get(i).get(Product._name) + "\n"
//                            + "            </h4><button type='submit' class='lab-btn' onclick='addToShoppingCart(" + products.get(i).get(Product._id) + ")'><span>شرکت در دوره</span></button></div></div></a></div>");
                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");

                if (isJsp) {
                    return html.toString();
                } else {
                    Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.setHtml("#hrefCollapse", optionHtml.toString()) + script);
                    return "";
                }
            } else {
                html.append("<div class='trending-area fix' style='width:100%'>\n"
                        + "                <div class='trending-main'>\n"
                        + "                    <div class='trending-bottom'>"
                        + "                    <div class='row'>");
                for (int i = 0; i < rowCategory1.size(); i++) {
                    if (rowCategory1.size() <= 2) {
                        if (rowCategory1.get(i).get(_script).equals("1")) {
                            System.out.println("........new jj(\'do=Content.sw&panel=sw&text=گواهی شرکت در دوره&jj=1\').jjAjax2()" + rowCategory1.get(i).get(_script));
                            html.append("<div onclick='sw_product(`" + rowCategory1.get(i).get(_title) + "`)' class='col-lg-6 col-md-6'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                        } else {
                            html.append("<div onclick='swGetProducts(" + rowCategory1.get(i).get(Category_Product._id) + ")' class='col-lg-6 col-md-6'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                        }
                    } else {
                        if (rowCategory1.get(i).get(_script).equals("1")) {

//                            System.out.println("........"+name);
                            html.append("<div onclick=\"sw_product(`" + rowCategory1.get(i).get(_title) + "`)\" class='col-lg-4 col-md-4'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");

                        } else {
                            html.append("<div onclick='swGetProducts(" + rowCategory1.get(i).get(Category_Product._id) + ")' class='col-lg-4 col-md-4'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                        }
                    }
                    html.append("    <img src='upload/" + rowCategory1.get(i).get(Category_Product._pic).toString() + "' alt=''>\n"
                            + "        </div>");
                    html.append("<div class='trend-bottom-cap'><span class='color1'>\n"
                            + "                " + rowCategory1.get(i).get(Category_Product._title) + "</span>");
                    html.append("<h4><a href=''>" + rowCategory1.get(i).get(Category_Product._explain).toString() + "</a></h4></div>");
                    html.append("</div></div>");

                }
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                html.append("</div>");
                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._category_id + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
                System.out.println("......................>>>>>>>>>>>" + rowProduct.size());
                if (rowProduct.isEmpty()) {
                } else {
                    html.append("<div class='trending-area fix' style='width:100%'>\n"
                            + "                <div class='trending-main'>\n"
                            + "                    <div class='trending-bottom'>"
                            + "                    <div class='row'>");
                    for (int i = 0; i < rowProduct.size(); i++) {
                        if (rowCategory1.size() <= 2) {
                            html.append("<div onclick='RgisterName(" + rowProduct.get(i).get(Product._id) + ")' class='col-lg-6 col-md-6'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                        } else {
                            html.append("<div onclick='RgisterName(" + rowProduct.get(i).get(Product._id) + ")' class='col-lg-4 col-md-4'><div class='single-bottom mb-35'><div class='trend-bottom-img mb-30'>");
                        }
                        html.append("    <img src='upload/" + rowProduct.get(i).get(Product._pic1).toString() + "' alt=''>\n"
                                + "        </div>");
                        html.append("<div class='trend-bottom-cap'><span class='color1'>\n"
                                + "                " + rowProduct.get(i).get(Product._name) + "</span>");
                        html.append("<h4><a href=''>" + rowProduct.get(i).get(Product._quantity).toString() + "</a></h4></div>");
                        html.append("</div></div>");

//                  
                    }
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");

                }
//              

                Server.outPrinter(request, response, Js.setHtml("#sw_1", html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
