package cms.cms;

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

public class Product {
//
//    public static String picturePath = "C:/Users/kps/Desktop/checkout2/build/web/upload";//request.getcontext.getrealpath("/upload")
    //

    public static String tableName = "account_product";
    public static String _id = "id";
    public static String _page = "account_product_page";
    public static String _creator = "account_product_creator";
    public static String _date = "account_product_date";
    public static String _name = "account_product_name";
    public static String _quantity = "account_product_quantity";//====== BY RASHIDI ======
    public static String _sameProducts = "account_product_sameProducts";//====== BY Modalaliyan ======
    public static String _lang = "account_product_lang";//by mohammad
    public static String _parent = "account_product_parent";//by mohammad
    public static String _priority = "account_product_priority";//by mohammad
    public static String _visit = "account_product_visit";//by mohammad
    public static String _order = "account_product_order";//by mohammad
    public static String _rating = "account_product_rating";//by mohammad
    public static String _like = "account_product_like";//by mohammad
    public static String _dislike = "account_product_dislike";//by mohammad
    public static String _category_id = "account_product_category_id";//by mohammad
    public static String _pic1 = "account_product_pic1";
    public static String _pic2 = "account_product_pic2";
    public static String _pic3 = "account_product_pic3";
    public static String _pic4 = "account_product_pic4";
    public static String _pic5 = "account_product_pic5";
    public static String _pic6 = "account_product_pic6";
    public static String _pic_ext = "account_product_pic_ext";
    public static String _price1 = "account_product_price1";
    public static String _price2 = "account_product_price2";
    public static String _currency = "account_product_currency";//====== BY RASHIDI ======
    public static String _code = "account_product_code";
    public static String _prop1 = "account_product_prop1";
    public static String _val1 = "account_product_val1";
    public static String _prop2 = "account_product_prop2";
    public static String _val2 = "account_product_val2";
    public static String _prop3 = "account_product_prop3";
    public static String _val3 = "account_product_val3";
    public static String _prop4 = "account_product_prop4";
    public static String _val4 = "account_product_val4";
    public static String _prop5 = "account_product_prop5";
    public static String _val5 = "account_product_val5";
    public static String _prop6 = "account_product_prop6";
    public static String _val6 = "account_product_val6";
    public static String _prop7 = "account_product_prop7";
    public static String _val7 = "account_product_val7";
    public static String _prop8 = "account_product_prop8";
    public static String _val8 = "account_product_val8";
    public static String _prop9 = "account_product_prop9";
    public static String _val9 = "account_product_val9";
    public static String _prop10 = "account_product_prop10";
    public static String _val10 = "account_product_val10";
    public static String _prop11 = "account_product_prop11";
    public static String _val11 = "account_product_val11";
    public static String _prop12 = "account_product_prop12";
    public static String _val12 = "account_product_val12";
    public static String _prop13 = "account_product_prop13";
    public static String _val13 = "account_product_val13";
    public static String _prop14 = "account_product_prop14";
    public static String _val14 = "account_product_val14";
    public static String _prop15 = "account_product_prop15";
    public static String _val15 = "account_product_val15";
    public static String _prop16 = "account_product_prop16";
    public static String _val16 = "account_product_val16";
    public static String _prop17 = "account_product_prop17";
    public static String _val17 = "account_product_val17";
    public static String _prop18 = "account_product_prop18";
    public static String _val18 = "account_product_val18";
    public static String _prop19 = "account_product_prop19";
    public static String _val19 = "account_product_val19";
    public static String _prop20 = "account_product_prop20";
    public static String _val20 = "account_product_val20";
    public static String _groupPrice1 = "account_product_groupPrice1";//====== BY RASHIDI ======
    public static String _userGroup1 = "account_product_userGroup1";//====== BY RASHIDI ======
    public static String _groupPrice2 = "account_product_groupPrice2";//====== BY RASHIDI ======
    public static String _userGroup2 = "account_product_userGroup2";//====== BY RASHIDI ======
    public static String _groupPrice3 = "account_product_groupPrice3";//====== BY RASHIDI ======
    public static String _userGroup3 = "account_product_userGroup3";//====== BY RASHIDI ======
    public static String _groupPrice4 = "account_product_groupPrice4";//====== BY RASHIDI ======
    public static String _userGroup4 = "account_product_userGroup4";//====== BY RASHIDI ======
    public static String _groupPrice5 = "account_product_groupPrice5";//====== BY RASHIDI ======
    public static String _userGroup5 = "account_product_userGroup5";//====== BY RASHIDI ======
    public static String _taxPercent = "account_product_taxPercent";//====== BY RASHIDI ======
    public static String _discount = "account_product_discount";//====== BY RASHIDI ======
    public static String _discountDate = "account_product_discount_Date";//====== BY RASHIDI ======
    public static String _discountTime = "account_product_discount_Time";//====== BY RASHIDI ======
    public static String _hasLink = "account_product_has_link";//====== BY RASHIDI ======برای این محصول لینک در محتوا ساخته شود یا خیر
    public static String _tags = "account_product_tags";
    public static String _relatedProducts = "account_products_relatedProducts";
    public static String _active = "account_products_active";
    public static String _block = "account_products_block";
    public static String _abstract = "account_products_abstract";
    public static String _content = "account_products_content";
    public static String _contentWithWikiLinks = "account_product_contentWithWikiLinks";
    public static String _titleTag = "account_product_titleTag";
    public static String _description = "account_product_description";
    public static String _style = "account_product_style";
    public static String _script = "account_product_script";
    public static String _hasInContentAutoWiki = "account_product_hasInContentAutoWiki";
    public static String _autoWikIsUpdate = "account_product_autoWikIsUpdate";//برای اینکه بفهمیم که اتوویکی زخیره شده نیاز به بروز رسانی دارد یا نه
    public static String _startDate = "account_product_startDate";
    public static String _startTime = "account_product_startTime";
    public static String _endDate = "account_product_endDate";
    public static String _endTime = "account_product_endTime";

    //;ثبت نام در کلاسها
    public static String tableNameCours = "mafad_class";
    public static String _idCours = "id";
    public static String _userId = "cours_userId";
    public static String _classId = "cours_classId";
    public static String _className = "cours_className";
    public static String _capacity = "cours_capacity";
    public static String _master = "cours_master";
    public static String _status = "cours_status";
    public static String Registering_status = "در حال ثبت نام";
    public static String compelete_status = "تکمیل";

    public static String s;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
//    public static int rul_rfs = 56;
//    public static int rul_ins = 57;
//    public static int rul_dlt = 59;
//    public static int rul_edt = 58;

    public static int rul_rfsAll = 381;
    public static int rul_rfs = 382;
    public static int rul_ins = 383;
    public static int rul_edt = 384;
    public static int rul_dlt = 385;
    public static int rul_lng2 = 225;//====== BY RASHIDI ======
    public static int rul_lng3 = 226;//====== BY RASHIDI ======
    public static int rul_lng4 = 227;//====== BY RASHIDI ======
    public static int rul_lng5 = 228;//====== BY RASHIDI ======
////    public static int rul_reserved = 229 --- 240;  /// RESERVED : 229 -- 240
    ///**************LANGUAAGE ADDED*******>
//    public static int rul_lng = 60;//====== COMMENTED BY RASHIDI ======
//    public static int rul_lng2 = 15;//====== BY RASHIDI ======
//    public static int rul_lng3 = 16;//====== BY RASHIDI ======
//    public static int rul_lng4 = 17;//====== BY RASHIDI ======
//    public static int rul_lng5 = 18;//====== BY RASHIDI ======
    public static String lbl_add_ln2 = "افزودن زبان انگلیسی";//====== BY RASHIDI ======
    public static String lbl_edit_ln2 = "ویرایش بخش انگلیسی";//====== BY RASHIDI ======
    public static String lbl_add_ln3 = "افزودن زبان عربی";//====== BY RASHIDI ======
    public static String lbl_edit_ln3 = "ویرایش بخش عربی";//====== BY RASHIDI ======
    public static String lbl_add_ln4 = "افزودن زبان آلمانی";//====== BY RASHIDI ======
    public static String lbl_edit_ln4 = "ویرایش بخش آلمانی";//====== BY RASHIDI ======
    public static String lbl_add_ln5 = "افزودن زبان چینی";//====== BY RASHIDI ======
    public static String lbl_edit_ln5 = "ویرایش بخش چینی";//====== BY RASHIDI ======
    ///<**************LANGUAAGE ADDED*******
    static int pageCounter = 4;
    //By Md

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارین");
                return "";
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>محصولات</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت محصولات</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsProduct.m_add_new();' > محصولات جدید</a>"
                        + "        </p>");
            }

            html.append("<table id='refreshProduct' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='20%'>کد محصول</th>");
            html.append("<th width='45%'>عنوان</th>");
            html.append("<th width='10%'>قیمت</th>");
            html.append("<th width='10%'>دسته</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("<th width='5%'>کپی</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_name).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_price2).toString()) + "</td>");//By Md             
                html.append("<td class='r'>/" + row.get(i).get(_category_id).toString() + "/</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;cursor: pointer;' class='c icon ion-ios-gear-outline' onclick='cmsProduct.m_select(" + row.get(i).get(_id) + ");'></td>");
                html.append("<td style='text-align: center;color:red;cursor: pointer;' class='c' onclick='cmsProduct.m_copyProduct(" + row.get(i).get(_id) + ");'><i class='fa fa-copy'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swProductTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshProduct", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "لیست محصولات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String classRegistrationNames(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            System.out.println("..//..");
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید')");
                return "";
            }
//            if (!Access_User.hasAccess(request, db, rul_rfs)) {
//                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارین");
//                return "";
//            }
            String idProduct = jjTools.getParameter(request, "idProduct");
            String id = String.valueOf(jjTools.getSeassionUserId(request));
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableNameCours, _classId + "='" + idProduct + "'");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            DefaultTableModel dtm1 = db.Select(tableNameCours, _userId + "='" + id + "'");//چک کند کاربر هست یا خیر
            List<Map<String, Object>> row1 = jjDatabase.separateRow(dtm);
            html.append("<div class='section-top-border' style='width:100%'>");
            if (row1.isEmpty()) {
                html.append("<a onclick='registerClass(" + idProduct + ")' class='genric-btn info'>ثبت نام در این دوره</a>");
            }
            html.append("<div class='progress-table-wrap'>");
            html.append("<div class='progress-table'>");
            html.append("<div class='table-head'>\n"
                    + "                <div class='serial'>ردیف</div>\n"
                    + "                <div class='country'>نام و نام خانوادگی</div>\n"
                    + "                <div class='visit'>شماره دانشجویی</div>\n"
                    + "                <div class='percentage'>شماره همراه</div>\n"
                    + "            </div>");
            for (int i = 0; i < row.size(); i++) {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "='" + row.get(i).get(_userId) + "'"));
                html.append(" <div class='table-row'>");
                html.append("<div class='serial'>" + row.get(i).get(_idCours) + "</div>");
                html.append("<div class='country'>" + user.get(0).get(Access_User._name) + " " + user.get(0).get(Access_User._family) + "</div>");
                html.append("<div class='visit'>" + user.get(0).get(Access_User._studentNumber) + "</div>");
                html.append("<div class='percentage'>" + user.get(0).get(Access_User._mobile) + "</div>");
                html.append("</div>");
            }
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");

            Server.outPrinter(request, response, Js.setHtml("#sw_1", html.toString()));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String insertClassRegistrationNames(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید')");
                return "";
            }
            String id = String.valueOf(jjTools.getSeassionUserId(request));
            String idProduct = jjTools.getParameter(request, "idProduct");
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> mapEdit = new HashMap<>();
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> dtm = jjDatabase.separateRow(db.Select(tableName, _id + "='" + idProduct + "'"));
            if (dtm.get(0).get(_quantity).equals("0")) {
                Server.outPrinter(request, response, "alert('ظرفیت این دوره پر شده است برای اطلاعات بیشتر به پشتیبان پیام دهید')");
                return "";
            } else {
                map.put(_userId, id);
                map.put(_classId, idProduct);
                map.put(_capacity, dtm.get(0).get(_quantity));
                map.put(_className, dtm.get(0).get(_name).toString());
                map.put(_status, compelete_status.toString());
                if (db.insert(tableNameCours, map).getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("en")) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.dialog(errorMessage));
                    return "";
                }
                mapEdit.put(_quantity, Integer.parseInt(dtm.get(0).get(_quantity).toString()) - 1);
                if (!db.update(tableName, mapEdit, _id + "=" + idProduct)) {
                    String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.dialog(errorMessage));
                    return "";
                }
            }
            Server.outPrinter(request, response, "RgisterName(" + idProduct + "),"+"addToShoppingCart(" + idProduct + ")");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder options = new StringBuilder();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#account_product_button", "<button title='" + lbl_insert + "' class='form-control btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjProduct.insert() + "' id='insert_account_product'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع محصول زا کپی میکند
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String copy(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارین");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            Map<String, Object> map = new HashMap<>();
            map.put(_contentWithWikiLinks, row.get(0).get(_contentWithWikiLinks));
            map.put(_autoWikIsUpdate, row.get(0).get(_autoWikIsUpdate));//خود این محتوا آپدیت است
            map.put(_titleTag, row.get(0).get(_titleTag));
            map.put(_style, row.get(0).get(_style));
            map.put(_script, row.get(0).get(_script));
            map.put(_description, row.get(0).get(_description));
            map.put(_content, row.get(0).get(_content));
            map.put(_code, row.get(0).get(_code));
            map.put(_creator, row.get(0).get(_creator));
            map.put(_name, row.get(0).get(_name));
            map.put(_quantity, row.get(0).get(_quantity));
            map.put(_page, row.get(0).get(_page));
            map.put(_pic1, row.get(0).get(_pic1));
            map.put(_pic2, row.get(0).get(_pic2));
            map.put(_pic3, row.get(0).get(_pic3));
            map.put(_pic4, row.get(0).get(_pic4));
            map.put(_pic5, row.get(0).get(_pic5));
            map.put(_pic6, row.get(0).get(_pic6));
            map.put(_pic_ext, row.get(0).get(_pic_ext));
            map.put(_prop1, row.get(0).get(_prop1));
            map.put(_val1, row.get(0).get(_val1));
            map.put(_prop2, row.get(0).get(_prop2));
            map.put(_val2, row.get(0).get(_val2));
            map.put(_prop3, row.get(0).get(_prop3));
            map.put(_val3, row.get(0).get(_val3));
            map.put(_prop4, row.get(0).get(_prop4));
            map.put(_val4, row.get(0).get(_val4));
            map.put(_prop5, row.get(0).get(_prop5));
            map.put(_val5, row.get(0).get(_val5));
            map.put(_prop6, row.get(0).get(_prop6));
            map.put(_val6, row.get(0).get(_val6));
            map.put(_prop7, row.get(0).get(_prop7));
            map.put(_val7, row.get(0).get(_val7));
            map.put(_prop8, row.get(0).get(_prop8));
            map.put(_val8, row.get(0).get(_val8));
            map.put(_prop9, row.get(0).get(_prop9));
            map.put(_val9, row.get(0).get(_val9));
            map.put(_prop10, row.get(0).get(_prop10));
            map.put(_val10, row.get(0).get(_val10));
            map.put(_prop11, row.get(0).get(_prop11));
            map.put(_val11, row.get(0).get(_val11));
            map.put(_prop12, row.get(0).get(_prop12));
            map.put(_val12, row.get(0).get(_val12));
            map.put(_prop13, row.get(0).get(_prop13));
            map.put(_val13, row.get(0).get(_val13));
            map.put(_prop14, row.get(0).get(_prop14));
            map.put(_val14, row.get(0).get(_val14));
            map.put(_prop15, row.get(0).get(_prop15));
            map.put(_val15, row.get(0).get(_val15));
            map.put(_prop16, row.get(0).get(_prop16));
            map.put(_val16, row.get(0).get(_val16));
            map.put(_prop17, row.get(0).get(_prop17));
            map.put(_val17, row.get(0).get(_val17));
            map.put(_prop18, row.get(0).get(_prop18));
            map.put(_val18, row.get(0).get(_val18));
            map.put(_prop19, row.get(0).get(_prop19));
            map.put(_val19, row.get(0).get(_val19));
            map.put(_prop20, row.get(0).get(_prop20));
            map.put(_val20, row.get(0).get(_val20));

            map.put(_price1, row.get(0).get(_price1));
            map.put(_price2, row.get(0).get(_price2));
            map.put(_currency, row.get(0).get(_currency));//====== BY RASHIDI ======
            //By Md
            map.put(_abstract, row.get(0).get(_abstract));
            map.put(_content, row.get(0).get(_content));
            map.put(_category_id, row.get(0).get(_category_id));
            map.put(_priority, row.get(0).get(_priority));
            if (row.get(0).get(_active).equals("true")) {
                map.put(_active, 1);
            } else {
                map.put(_active, 0);
            }
            if (row.get(0).get(_block).equals("true")) {
                map.put(_block, 1);
            } else {
                map.put(_block, 0);
            }
            map.put(_like, row.get(0).get(_like));
            map.put(_dislike, row.get(0).get(_dislike));
            map.put(_order, row.get(0).get(_order));
            map.put(_visit, row.get(0).get(_visit));
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_parent, row.get(0).get(_parent));
            map.put(_lang, row.get(0).get(_lang));

            map.put(_tags, row.get(0).get(_tags));
            map.put(_relatedProducts, row.get(0).get(_relatedProducts));

            map.put(_groupPrice1, row.get(0).get(_groupPrice1));
            map.put(_userGroup1, row.get(0).get(_userGroup1));
            map.put(_groupPrice2, row.get(0).get(_groupPrice2));
            map.put(_userGroup2, row.get(0).get(_userGroup2));
            map.put(_groupPrice3, row.get(0).get(_groupPrice3));
            map.put(_userGroup3, row.get(0).get(_userGroup3));
            map.put(_groupPrice4, row.get(0).get(_groupPrice4));
            map.put(_userGroup4, row.get(0).get(_userGroup4));
            map.put(_groupPrice5, row.get(0).get(_groupPrice5));
            map.put(_userGroup5, row.get(0).get(_userGroup5));
            map.put(_taxPercent, row.get(0).get(_taxPercent));
            map.put(_discount, row.get(0).get(_discount));
            map.put(_discountDate, row.get(0).get(_discountDate));
            map.put(_discountTime, row.get(0).get(_discountTime));
            map.put(_startDate, row.get(0).get(_startDate));
            map.put(_startTime, row.get(0).get(_startTime));
            map.put(_endDate, row.get(0).get(_endDate));
            map.put(_endTime, row.get(0).get(_endTime));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "Error"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjProduct.refresh() + Js.modal("کپی " + row.get(0).get(_name) + " به درستی انجام شد...", "کپی"));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارین");
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
                map.put(_autoWikIsUpdate, jjTools.getParameter(request, _autoWikIsUpdate));
                map.put(_autoWikIsUpdate, "0");//خود این محتوا آپدیت است
            }
            if ("1".equalsIgnoreCase(jjTools.getParameter(request, _hasLink))) {//اگر این محتوا باید اتوویکی بشود بنابر این باید اتوویکی همه ی محتواها آپدیت بشوند
                db.otherStatement("Update " + tableName + " SET " + _autoWikIsUpdate + "=0 WHERE " + _hasInContentAutoWiki + "=1");//فقط آنهایی که در محتوایشان اتو ویکی دارند
                //@ToDo برای جدوال اخبار و محتوا هم همین اتفاق باید باشد
            }
            map.put(_titleTag, jjTools.getParameter(request, _titleTag));
            map.put(_style, jjTools.getParameter(request, _style));
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_content, jjTools.getParameter(request, _content).trim());
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_name, jjTools.getParameter(request, _name));
            if (jjTools.getParameter(request, _quantity).equals("")) {
                map.put(_quantity, 0);//====== BY RASHIDI ======
            } else {
                map.put(_quantity, jjTools.getParameter(request, _quantity));//====== BY RASHIDI ======
            }
            map.put(_page, jjTools.getParameter(request, _page));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_pic6, jjTools.getParameter(request, _pic6));
            map.put(_pic_ext, jjTools.getParameter(request, _pic_ext));
            map.put(_prop1, jjTools.getParameter(request, _prop1));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_prop2, jjTools.getParameter(request, _prop2));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_prop3, jjTools.getParameter(request, _prop3));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_prop4, jjTools.getParameter(request, _prop4));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_prop5, jjTools.getParameter(request, _prop5));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_prop6, jjTools.getParameter(request, _prop6));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_prop7, jjTools.getParameter(request, _prop7));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_prop8, jjTools.getParameter(request, _prop8));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_prop9, jjTools.getParameter(request, _prop9));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_prop10, jjTools.getParameter(request, _prop10));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_prop11, jjTools.getParameter(request, _prop11));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_prop12, jjTools.getParameter(request, _prop12));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_prop13, jjTools.getParameter(request, _prop13));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_prop14, jjTools.getParameter(request, _prop14));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_prop15, jjTools.getParameter(request, _prop15));
            map.put(_val15, jjTools.getParameter(request, _val15));
            map.put(_prop16, jjTools.getParameter(request, _prop16));
            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_prop17, jjTools.getParameter(request, _prop17));
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_prop18, jjTools.getParameter(request, _prop18));
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_prop19, jjTools.getParameter(request, _prop19));
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_prop20, jjTools.getParameter(request, _prop20));
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);

            map.put(_price1, jjNumber.isDigit(jjTools.getParameter(request, _price1)) ? Integer.parseInt(jjTools.getParameter(request, _price1)) : 0);
            map.put(_price2, jjNumber.isDigit(jjTools.getParameter(request, _price2)) ? Integer.parseInt(jjTools.getParameter(request, _price2)) : 0);
            map.put(_currency, jjTools.getParameter(request, _currency));//====== BY RASHIDI ======
            //By Md
            map.put(_abstract, jjTools.getParameter(request, _abstract));
            map.put(_content, jjTools.getParameter(request, _content));

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 1);

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            String active = jjTools.getParameter(request, _priority);
            map.put(_active, jjNumber.isDigit(active) ? Integer.parseInt(active) : 0);
            String block = jjTools.getParameter(request, _block);
            map.put(_block, jjNumber.isDigit(block) ? Integer.parseInt(block) : 0);
            String like = jjTools.getParameter(request, _like);
            map.put(_like, jjNumber.isDigit(like) ? Integer.parseInt(like) : 0);
            String dislike = jjTools.getParameter(request, _dislike);
            map.put(_dislike, jjNumber.isDigit(dislike) ? Integer.parseInt(dislike) : 0);
            String order = jjTools.getParameter(request, _order);
            map.put(_order, jjNumber.isDigit(order) ? Integer.parseInt(order) : 0);
            String visit = jjTools.getParameter(request, _visit);
            map.put(_visit, jjNumber.isDigit(visit) ? Integer.parseInt(visit) : 0);
            jjCalendar_IR dateIR = new jjCalendar_IR();//====== BY RASHIDI ======
            if (jjTools.getParameter(request, _date).equals("")) {
                map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            } else {
                map.put(_date, dateIR.getDBFormat_8length());
            }
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            map.put(_tags, jjTools.getParameter(request, _tags));
            map.put(_relatedProducts, jjTools.getParameter(request, _relatedProducts));

            map.put(_groupPrice1, jjTools.getParameter(request, _groupPrice1));//====== BY RASHIDI ======
            map.put(_userGroup1, jjTools.getParameter(request, _userGroup1));//====== BY RASHIDI ======
            map.put(_groupPrice2, jjTools.getParameter(request, _groupPrice2));//====== BY RASHIDI ======
            map.put(_userGroup2, jjTools.getParameter(request, _userGroup2));//====== BY RASHIDI ======
            map.put(_groupPrice3, jjTools.getParameter(request, _groupPrice3));//====== BY RASHIDI ======
            map.put(_userGroup3, jjTools.getParameter(request, _userGroup3));//====== BY RASHIDI ======
            map.put(_groupPrice4, jjTools.getParameter(request, _groupPrice4));//====== BY RASHIDI ======
            map.put(_userGroup4, jjTools.getParameter(request, _userGroup4));//====== BY RASHIDI ======
            map.put(_groupPrice5, jjTools.getParameter(request, _groupPrice5));//====== BY RASHIDI ======
            map.put(_userGroup5, jjTools.getParameter(request, _userGroup5));//====== BY RASHIDI ======
            map.put(_taxPercent, jjTools.getParameter(request, _taxPercent));//====== BY RASHIDI ======
            map.put(_discount, jjTools.getParameter(request, _discount));//====== BY RASHIDI ======
//            map.put(_sameProducts, jjTools.getParameter(request, _sameProducts));//====== BY modalaliyan ======

            map.put(_discountDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _discountDate), true));//====== BY RASHIDI ======
            map.put(_discountTime, dateIR.getTimeFormat_4length());
            if (jjTools.getParameter(request, _startDate).equals("")) {
                map.put(_startDate, 0);
            } else {
                map.put(_startDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _startDate), isPost));
            }
            if (jjTools.getParameter(request, _startTime).equals("")) {
                map.put(_startTime, 0);
            } else {
                map.put(_startTime, jjCalendar_IR.getDatabaseFormatTime_4length(jjTools.getParameter(request, _startTime), isPost));
            }
            if (jjTools.getParameter(request, _endDate).equals("")) {
                map.put(_endDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _endDate), isPost));
            } else {
            }
            if (jjTools.getParameter(request, _endTime).equals("")) {
                map.put(_endTime, 0);
            } else {
                map.put(_endTime, jjCalendar_IR.getDatabaseFormatTime_4length(jjTools.getParameter(request, _endTime), isPost));
            }

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjProduct.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارین");
                return "";
            }
//            System.out.println("EDIT");
            String id = jjTools.getParameter(request, _id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_style, jjTools.getParameter(request, _style));
            map.put(_script, jjTools.getParameter(request, _script));
            map.put(_page, jjTools.getParameter(request, _page));
            map.put(_quantity, jjTools.getParameter(request, _quantity));//====== BY RASHIDI ======
            map.put(_hasLink, jjTools.getParameter(request, _hasLink));
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            Path path;
            String pic = row.get(0).get(_pic1).toString();
            if (!jjTools.getParameter(request, _pic1).equalsIgnoreCase(pic)) {
                map.put(_pic1, jjTools.getParameter(request, _pic1));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }

            pic = row.get(0).get(_pic2).toString();
            if (!jjTools.getParameter(request, _pic2).equalsIgnoreCase(pic)) {
                map.put(_pic2, jjTools.getParameter(request, _pic2));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                        ServerLog.Print("Product.edit : file " + pic + " deleted!");
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }
            pic = row.get(0).get(_pic3).toString();
            if (!jjTools.getParameter(request, _pic3).equalsIgnoreCase(pic)) {
                map.put(_pic3, jjTools.getParameter(request, _pic3));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                        ServerLog.Print("Product.edit : file " + pic + " deleted!");
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }

            pic = row.get(0).get(_pic4).toString();
            if (!jjTools.getParameter(request, _pic4).equalsIgnoreCase(pic)) {
                map.put(_pic4, jjTools.getParameter(request, _pic4));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                        ServerLog.Print("Product.edit : file " + pic + " deleted!");
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }

            pic = row.get(0).get(_pic5).toString();
            if (!jjTools.getParameter(request, _pic5).equalsIgnoreCase(pic)) {
                map.put(_pic5, jjTools.getParameter(request, _pic5));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
//                        Files.delete(path);
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                        ServerLog.Print("Product.edit : file " + pic + " deleted!");
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }

            pic = row.get(0).get(_pic6).toString();
            if (!jjTools.getParameter(request, _pic6).equalsIgnoreCase(pic)) {
                map.put(_pic6, jjTools.getParameter(request, _pic6));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }

            pic = row.get(0).get(_pic_ext).toString();
            if (!jjTools.getParameter(request, _pic_ext).equalsIgnoreCase(pic)) {
                map.put(_pic_ext, jjTools.getParameter(request, _pic_ext));
                path = FileSystems.getDefault().getPath(request.getServletContext().getRealPath("/upload"), pic);
                if (Files.exists(path)) {
                    try {
                        request.setAttribute("fileName", pic);
                        jjFile.deletefile(request, db, isPost);
                    } catch (IOException | SecurityException e) {
                        System.err.println(e);
                    }
                }
            }
            map.put(_pic_ext, jjTools.getParameter(request, _pic_ext));
            map.put(_prop1, jjTools.getParameter(request, _prop1));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_prop2, jjTools.getParameter(request, _prop2));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_prop3, jjTools.getParameter(request, _prop3));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_prop4, jjTools.getParameter(request, _prop4));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_prop5, jjTools.getParameter(request, _prop5));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_prop6, jjTools.getParameter(request, _prop6));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_prop7, jjTools.getParameter(request, _prop7));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_prop8, jjTools.getParameter(request, _prop8));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_prop9, jjTools.getParameter(request, _prop9));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_prop10, jjTools.getParameter(request, _prop10));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_prop11, jjTools.getParameter(request, _prop11));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_prop12, jjTools.getParameter(request, _prop12));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_prop13, jjTools.getParameter(request, _prop13));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_prop14, jjTools.getParameter(request, _prop14));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_prop15, jjTools.getParameter(request, _prop15));
            map.put(_val15, jjTools.getParameter(request, _val15));
            map.put(_prop16, jjTools.getParameter(request, _prop16));
            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_prop17, jjTools.getParameter(request, _prop17));
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_prop18, jjTools.getParameter(request, _prop18));
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_prop19, jjTools.getParameter(request, _prop19));
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_prop20, jjTools.getParameter(request, _prop20));
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);

            map.put(_price1, jjNumber.isDigit(jjTools.getParameter(request, _price1)) ? Integer.parseInt(jjTools.getParameter(request, _price1)) : 0);
            map.put(_price2, jjNumber.isDigit(jjTools.getParameter(request, _price2)) ? Integer.parseInt(jjTools.getParameter(request, _price2)) : 0);
            map.put(_currency, jjTools.getParameter(request, _currency));//============ BY RASHIDI ========
            //By Md
            map.put(_abstract, jjTools.getParameter(request, _abstract));
            map.put(_content, jjTools.getParameter(request, _content));

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 0);

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

            String active = jjTools.getParameter(request, _active);
            map.put(_active, jjNumber.isDigit(active) ? Integer.parseInt(active) : 1);
            String block = jjTools.getParameter(request, _block);
            map.put(_block, jjNumber.isDigit(block) ? Integer.parseInt(block) : 0);
            String like = jjTools.getParameter(request, _like);
            map.put(_like, jjNumber.isDigit(like) ? Integer.parseInt(like) : 0);
            String dislike = jjTools.getParameter(request, _dislike);
            map.put(_dislike, jjNumber.isDigit(dislike) ? Integer.parseInt(dislike) : 0);
            String order = jjTools.getParameter(request, _order);
            map.put(_order, jjNumber.isDigit(order) ? Integer.parseInt(order) : 0);
            String visit = jjTools.getParameter(request, _visit);
            map.put(_visit, jjNumber.isDigit(visit) ? Integer.parseInt(visit) : 0);
            String rating = jjTools.getParameter(request, _rating);
            map.put(_rating, jjNumber.isDigit(rating) ? Integer.parseInt(rating) : 0);

            map.put(_tags, jjTools.getParameter(request, _tags));
            map.put(_relatedProducts, jjTools.getParameter(request, _relatedProducts));
            map.put(_groupPrice1, jjTools.getParameter(request, _groupPrice1));//====== BY RASHIDI ======
            map.put(_userGroup1, jjTools.getParameter(request, _userGroup1));//====== BY RASHIDI ======
            map.put(_groupPrice2, jjTools.getParameter(request, _groupPrice2));//====== BY RASHIDI ======
            map.put(_userGroup2, jjTools.getParameter(request, _userGroup2));//====== BY RASHIDI ======
            map.put(_groupPrice3, jjTools.getParameter(request, _groupPrice3));//====== BY RASHIDI ======
            map.put(_userGroup3, jjTools.getParameter(request, _userGroup3));//====== BY RASHIDI ======
            map.put(_groupPrice4, jjTools.getParameter(request, _groupPrice4));//====== BY RASHIDI ======
            map.put(_userGroup4, jjTools.getParameter(request, _userGroup4));//====== BY RASHIDI ======
            map.put(_groupPrice5, jjTools.getParameter(request, _groupPrice5));//====== BY RASHIDI ======
            map.put(_userGroup5, jjTools.getParameter(request, _userGroup5));//====== BY RASHIDI ======
            map.put(_taxPercent, jjTools.getParameter(request, _taxPercent));//====== BY RASHIDI ======
            map.put(_discount, jjTools.getParameter(request, _discount));//====== BY RASHIDI ======
            map.put(_discountDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _discountDate), true));//====== BY RASHIDI ======
            jjCalendar_IR dateIR = new jjCalendar_IR();//====== BY RASHIDI ======
            map.put(_discountTime, dateIR.getTimeFormat_5length());
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjProduct.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارین");
                return "";
            }
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            Path path;
            try {
                request.setAttribute("fileName", row.get(0).get(_pic1).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic2).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic3).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic4).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic5).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic6).toString());
                jjFile.deletefile(request, db, isPost);
                request.setAttribute("fileName", row.get(0).get(_pic_ext).toString());
                jjFile.deletefile(request, db, isPost);
            } catch (IOException | SecurityException e) {
                System.err.println(e);
            }
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }
            if (!db.delete(tableName, _id + "=" + id + " OR " + _parent + " = " + id)) {//============ EDITED BY RASHIDI ========
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjProduct.refresh());
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
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _code, row.get(0).get(_code)));
            html.append(Js.setVal("#" + _name, row.get(0).get(_name)));
            html.append(Js.setVal("#" + _quantity, row.get(0).get(_quantity)));
            html.append(Js.setVal("#" + _page, row.get(0).get(_page)));
            html.append(Js.setVal("#" + _pic1, row.get(0).get(_pic1)));
            html.append(Js.setVal("#" + _pic2, row.get(0).get(_pic2)));
            html.append(Js.setVal("#" + _pic3, row.get(0).get(_pic3)));
            html.append(Js.setVal("#" + _pic4, row.get(0).get(_pic4)));
            html.append(Js.setVal("#" + _pic5, row.get(0).get(_pic5)));
            html.append(Js.setVal("#" + _pic6, row.get(0).get(_pic6)));
            html.append(Js.setVal("#" + _pic_ext, row.get(0).get(_pic_ext)));
            html.append(Js.setVal("#" + _price1, row.get(0).get(_price1)));
            html.append(Js.setVal("#" + _price2, row.get(0).get(_price2)));
            html.append(Js.setVal("#" + _currency, row.get(0).get(_currency)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _prop1, row.get(0).get(_prop1)));
            html.append(Js.setVal("#" + _prop2, row.get(0).get(_prop2)));
            html.append(Js.setVal("#" + _prop3, row.get(0).get(_prop3)));
            html.append(Js.setVal("#" + _prop4, row.get(0).get(_prop4)));
            html.append(Js.setVal("#" + _prop5, row.get(0).get(_prop5)));
            html.append(Js.setVal("#" + _prop6, row.get(0).get(_prop6)));
            html.append(Js.setVal("#" + _prop7, row.get(0).get(_prop7)));
            html.append(Js.setVal("#" + _prop8, row.get(0).get(_prop8)));
            html.append(Js.setVal("#" + _prop9, row.get(0).get(_prop9)));
            html.append(Js.setVal("#" + _prop10, row.get(0).get(_prop10)));
            html.append(Js.setVal("#" + _prop11, row.get(0).get(_prop11)));
            html.append(Js.setVal("#" + _prop12, row.get(0).get(_prop12)));
            html.append(Js.setVal("#" + _prop13, row.get(0).get(_prop13)));
            html.append(Js.setVal("#" + _prop14, row.get(0).get(_prop14)));
            html.append(Js.setVal("#" + _prop15, row.get(0).get(_prop15)));
            html.append(Js.setVal("#" + _prop16, row.get(0).get(_prop16)));
            html.append(Js.setVal("#" + _prop17, row.get(0).get(_prop17)));
            html.append(Js.setVal("#" + _prop18, row.get(0).get(_prop18)));
            html.append(Js.setVal("#" + _prop19, row.get(0).get(_prop19)));
            html.append(Js.setVal("#" + _prop20, row.get(0).get(_prop20)));
            html.append(Js.setVal("#" + _val1, row.get(0).get(_val1)));
            html.append(Js.setVal("#" + _val2, row.get(0).get(_val2)));
            html.append(Js.setVal("#" + _val3, row.get(0).get(_val3)));
            html.append(Js.setVal("#" + _val4, row.get(0).get(_val4)));
            html.append(Js.setVal("#" + _val5, row.get(0).get(_val5)));
            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
            html.append(Js.setVal("#" + _val7, row.get(0).get(_val7)));
            html.append(Js.setVal("#" + _val8, row.get(0).get(_val8)));
            html.append(Js.setVal("#" + _val9, row.get(0).get(_val9)));
            html.append(Js.setVal("#" + _val10, row.get(0).get(_val10)));
            html.append(Js.setVal("#" + _val11, row.get(0).get(_val11)));
            html.append(Js.setVal("#" + _val12, row.get(0).get(_val12)));
            html.append(Js.setVal("#" + _val13, row.get(0).get(_val13)));
            html.append(Js.setVal("#" + _val14, row.get(0).get(_val14)));
            html.append(Js.setVal("#" + _val15, row.get(0).get(_val15)));
            html.append(Js.setVal("#" + _val16, row.get(0).get(_val16)));
            html.append(Js.setVal("#" + _val17, row.get(0).get(_val17)));
            html.append(Js.setVal("#" + _val18, row.get(0).get(_val18)));
            html.append(Js.setVal("#" + _val19, row.get(0).get(_val19)));
            html.append(Js.setVal("#" + _val20, row.get(0).get(_val20)));
//            html.append(Js.setVal(_sameProducts, row.get(0).get(_sameProducts)));

            html.append(Js.setVal("#" + _groupPrice1, row.get(0).get(_groupPrice1) == "" ? row.get(0).get(_price2) : row.get(0).get(_groupPrice1)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _groupPrice2, row.get(0).get(_groupPrice2) == "" ? row.get(0).get(_price2) : row.get(0).get(_groupPrice2)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _groupPrice3, row.get(0).get(_groupPrice3) == "" ? row.get(0).get(_price2) : row.get(0).get(_groupPrice3)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _groupPrice4, row.get(0).get(_groupPrice4) == "" ? row.get(0).get(_price2) : row.get(0).get(_groupPrice4)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _groupPrice5, row.get(0).get(_groupPrice5) == "" ? row.get(0).get(_price2) : row.get(0).get(_groupPrice5)));//====== BY RASHIDI ======
            html.append(Js.setValSelectOption("#" + _userGroup1, row.get(0).get(_userGroup1).toString()));//====== BY RASHIDI ======
            html.append(Js.setValSelectOption("#" + _userGroup2, row.get(0).get(_userGroup2).toString()));//====== BY RASHIDI ======
            html.append(Js.setValSelectOption("#" + _userGroup3, row.get(0).get(_userGroup3).toString()));//====== BY RASHIDI ======
            html.append(Js.setValSelectOption("#" + _userGroup4, row.get(0).get(_userGroup4).toString()));//====== BY RASHIDI ======
            html.append(Js.setValSelectOption("#" + _userGroup5, row.get(0).get(_userGroup5).toString()));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _taxPercent, row.get(0).get(_taxPercent)));//====== BY RASHIDI ======

            html.append(Js.setVal("#" + _discount, row.get(0).get(_discount)));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _discountDate, jjCalendar_IR.getViewFormat(row.get(0).get(_discountDate).toString())));//====== BY RASHIDI ======
            html.append(Js.setVal("#" + _abstract, row.get(0).get(_abstract)));
            html.append(Js.setValSummerNote("#" + _content, row.get(0).get(_content)));
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic1).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview1", "src", "upload/" + row.get(0).get(_pic1).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic2).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview2", "src", "upload/" + row.get(0).get(_pic2).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic3).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview3", "src", "upload/" + row.get(0).get(_pic3).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic4).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview4", "src", "upload/" + row.get(0).get(_pic4).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic5).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview5", "src", "upload/" + row.get(0).get(_pic5).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic6).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_preview6", "src", "upload/" + row.get(0).get(_pic6).toString()));
            }
            /*اگر محصول عکس داشت*/
            if (!row.get(0).get(_pic_ext).toString().isEmpty()) {
                html.append(Js.setAttr("#productPicPreviewExt", "src", "upload/" + row.get(0).get(_pic_ext).toString()));
            }

            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString())));

            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setValSelectOption("#" + _category_id, row.get(0).get(_category_id).toString()));

            Integer likes = Integer.valueOf(row.get(0).get(_like).toString());
            html.append(Js.setVal("#" + _like, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _like + "_checkbox", 0));
                html.append(Js.setAttr(_like, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _like + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _like, "disabled"));
            }

            Integer dislikes = Integer.valueOf(row.get(0).get(_dislike).toString());
            html.append(Js.setVal("#" + _dislike, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _dislike + "_checkbox", 0));
                html.append(Js.setAttr("#" + _dislike, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _dislike + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _dislike, "disabled"));
            }

            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            html.append(Js.setVal("#" + _hasLink, row.get(0).get(_hasLink)));
            Integer order = Integer.valueOf(row.get(0).get(_order).toString());
            html.append(Js.setVal("#" + _order, order));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _order + "_checkbox", 0));
                html.append(Js.setAttr("#" + _order, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _order + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _order, "disabled"));
            }
            html.append(Js.setValSelectOption("#" + _tags, row.get(0).get(_tags).toString()));
            html.append(Js.select2("#" + _tags, ""));
            html.append(Js.setVal("#" + _description, row.get(0).get(_description)));
            html.append(Js.setVal("#" + _titleTag, row.get(0).get(_titleTag)));
            html.append(Js.setVal("#" + _style, row.get(0).get(_style)));
            html.append(Js.setVal("#" + _script, row.get(0).get(_script)));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjProduct.edit() + "' id='edit_Product'>" + lbl_edit + "</button>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjProduct.delete(id) + "' id='delete_Product'>" + lbl_delete + "</button>";
            }
            html.append(Js.setHtml("#account_product_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String buyOneProduct(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "id");
            String panel = jjTools.getParameter(request, "panel");
            String pr = panel.replace("pr", "");
            if (!id.equals("")) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _name + " LIKE '%" + id + "%' OR " + _code + " LIKE '%" + id + "%' " + (jjNumber.isDigit(id) ? "OR id=" + id : "")));
                if (row.size() > 0) {
                    String find = "";
                    for (int i = 0; i < row.size(); i++) {
                        find += "<a style='color:blue' onclick='setProductToFactor(" + row.get(i).get(_id) + "," + pr + ");'>"
                                + row.get(i).get(_id) + ". " + row.get(i).get(_name) + "</a><br/>";
                    }
                    return Js.setHtml("#" + panel, find) + (row.size() > 0 ? Js.setVal("#account_factor_pr_id_" + pr, row.get(0).get(_id)) : "");
                } else {
                    return Js.setHtml("#" + panel, "موردی یافت نشد.") + Js.setVal("#" + panel, "") + Js.setVal("#account_factor_pr_id_" + pr, "");
                }
            } else {
                return Js.setHtml("#" + panel, "");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String searchProduct(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = jjTools.getParameter(request, "text");
            String panel = jjTools.getParameter(request, "panel");
            String pr = panel.replace("pr", "");
            if (!text.equals("")) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _name + " LIKE '%" + text + "%' OR " + _code + " LIKE '%" + text + "%' " + (jjNumber.isDigit(text) ? "OR id=" + text : "")));
                if (row.size() > 0) {
                    String find = "";
                    for (int i = 0; i < row.size(); i++) {
                        find += "<a style='color:blue' onclick='setProductToFactor(" + row.get(i).get(_id) + "," + pr + ");'>"
                                + row.get(i).get(_id) + ". " + row.get(i).get(_name) + "</a><br/>";
                    }
                    return Js.setHtml("#" + panel, find) + (row.size() > 0 ? Js.setVal("#account_factor_pr_id_" + pr, row.get(0).get(_id)) : "");
                } else {
                    return Js.setHtml("#" + panel, "موردی یافت نشد.") + Js.setVal("#" + panel, "") + Js.setVal("#account_factor_pr_id_" + pr, "");
                }
            } else {
                return Js.setHtml("#" + panel, "");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String setProductToFactor(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String pr = panel.replace("pr", "");
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                return hasAccess;
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
            if (row.isEmpty()) {
                String errorMessage = "مشتری با این کد وجود ندارد";
                return Js.dialog(errorMessage);
            }

            StringBuilder html = new StringBuilder();

            html.append(Js.setVal("#account_factor_pr_name_" + pr, row.get(0).get(_name)));
            html.append(Js.setVal("#account_factor_pr_unit_" + pr, row.get(0).get(_val1)));
            html.append(Js.setVal("#account_factor_pr_fee_" + pr, row.get(0).get(_price2)));
            html.append(Js.setVal("#" + _id + "_" + pr, row.get(0).get(_id)));
            html.append("calculateSum").append(pr).append("();\n");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /*
     *return a list of product by catrgory_id /n if catrgory_id==0 then return top product(cat 1,2) *
     */
    public static String getList2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0         
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            //<============BY RASHIDI ========
            if (jjTools.isLangEn(request)) {
                return getList_En(request, db, isPost);
            }
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            /*طبقه بندی محصولات را برمیگرداند*/
//            html3.append(Category_Product.getHierarchyDiv(re, db));
            Integer category_id = new Integer(jjTools.getParameter(request, "id"));
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topProductRow;
            if (category_id == 0) {
                topProductRow = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + "=1 AND " + Product._priority + "<=2"));
            } else {
                topProductRow = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + "=1 AND " + Product._category_id + "=" + category_id));
            }

            //---------------one product post creation
            /*لیست محصولات را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopproductDiv' class='topproductDiv'></div>");
            if (topProductRow.isEmpty()) {
                temphtml.append("<div class='noAnyThing'><span>!!! در این بخش موردی برای نمایش وجود ندارد</span></div>");
            } else {
                for (int i = 0; i < topProductRow.size(); i++) {
                    String id = topProductRow.get(i).get(_id).toString();
                    temphtml.append("<div class='productmainDiv'>");
//                    temphtml.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(topProductRow.get(i).get(_date).toString()) + "</span>");
                    int visit = new Integer(topProductRow.get(i).get(_visit).toString());
                    if (visit >= 0) {
                        temphtml.append("<div class='prVDiv'> <span>" + visit + " بار مشاهده </span></div>");//==> prVDiv : productvisitDiv
                    }
                    int disLikes = new Integer(topProductRow.get(i).get(_dislike).toString());
                    if (disLikes >= 0) {
                        temphtml.append("<div class='prDLDiv' onclick='productDisLike(" + id + ");' > <span>" + disLikes + " مخالف </span></div>");//==> prDLDiv : productDisLikeDiv
                    }
                    int likes = new Integer(topProductRow.get(i).get(_like).toString());
                    if (likes >= 0) {
                        temphtml.append("<div class='prLDiv' onclick='productLike(" + id + ");'>  <span>" + likes + " موافق </span></div>");//==> prLDiv : productlikeDiv
                    }
                    //============ EDITED BY RASHIDI ========>
                    String src1 = topProductRow.get(i).get(_pic1).toString();
//                    String src2 = topProductRow.get(i).get(_pic2).toString();
//                    String src3 = topProductRow.get(i).get(_pic3).toString();
//                    String src4 = topProductRow.get(i).get(_pic4).toString();
//                    String src5 = topProductRow.get(i).get(_pic5).toString();
//                    String src6 = topProductRow.get(i).get(_pic6).toString();
//                    String src_ext = topProductRow.get(i).get(_pic_ext).toString();

                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src1.matches("upload/p[0-9]{10}.{4}")) {
                    String smalPicSrc = src1.replace(".", "_small.");//select small pic
                    temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'  onclick='getOneproduct(" + id + ")';/>");//======EDITED BY RASHIDI ======
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src1 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src2.matches("upload/p[0-9]{10}.{4}")) {
//                      smalPicSrc = src2.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src2 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src3.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src3.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src3 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src4.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src4.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src4 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src5.matches("upload/p[0-9]{10}.{4}")) {
//                        smalPicSrc = src5.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src5 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src6.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src6.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src6 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src_ext.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src_ext.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src_ext + "'/>");
//                    }

                    //<============ EDITED BY RASHIDI ========
                    temphtml.append("<span class='productTitlespan'><h3>" + topProductRow.get(i).get(_name).toString() + "</h3></span>");
                    //============ BY RASHIDI ========>
                    int quantity = new Integer(topProductRow.get(i).get(_quantity).toString());
                    if (quantity <= 0) {
                        temphtml.append("<div class='prQDiv'>  <span> نا موجود</span></div>");//==> prQDiv : productlQuantityDiv
                    } else {
                        temphtml.append("<div class='prQDiv'>  <span> تعداد موجود  " + quantity + " </span></div>");//==> prQDiv : productlQuantityDiv
                    }
                    //======>PRICE
                    if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                        price = topProductRow.get(i).get(_price2).toString();//قیمت اصلی را به کاربر نمایش می دهد
                        discountPrice = topProductRow.get(i).get(_discount).toString() == "" ? "" : topProductRow.get(i).get(_discount).toString();//اگر تخفیف برایش در نظر گرفته شود تخفیف هم نمایش داده می شود.
                    } else {//اگر کاربر لاگین کرده باشد
                        List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                        if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                            String userGroup = userRow.get(0).get(Access_Group_User._group_id).toString();
//                            ServerLog.Print("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup1).toString())) {
                                price = topProductRow.get(i).get(_groupPrice1).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup2).toString())) {
                                price = topProductRow.get(i).get(_groupPrice2).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup3).toString())) {
                                price = topProductRow.get(i).get(_groupPrice3).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup4).toString())) {
                                price = topProductRow.get(i).get(_groupPrice4).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup5).toString())) {
                                price = topProductRow.get(i).get(_groupPrice5).toString();
                            } else {
                                price = topProductRow.get(i).get(_price2).toString();
                            }
                        } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                            price = topProductRow.get(i).get(_price2).toString();
                            discountPrice = topProductRow.get(i).get(_discount).toString() == "" ? "" : topProductRow.get(i).get(_discount).toString();
                        }
                    }
                    ServerLog.Print("PRICE : " + price);
                    ServerLog.Print("DISCOUNTPRICE : " + discountPrice);

                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود

                        //=======> Time of Discount
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(topProductRow.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            temphtml.append("<span class='productTitlespan'><h3 style='text-decoration: line-through;' >" + price + "</h3></span>");
                            temphtml.append("<span class='productTitlespan'><h3>" + discountPrice + "</h3></span>");
                            temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainTime + " روز</h3></span>");
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            int remainHours = 0, remainMinutes = 0;
                            String timeArr[] = topProductRow.get(i).get(_discountTime).toString().split(":");
                            ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                            remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                            remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                            if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                temphtml.append("<span class='productTitlespan'><h3 style='text-decoration: line-through;' >" + price + "</h3></span>");
                                temphtml.append("<span class='productTitlespan'><h3>" + discountPrice + "</h3></span>");
                                ServerLog.Print("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                                temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                                temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + "دقیقه : " + remainHours + " ساعت</h3></span>");
//                        temphtml.append("<span class='productTitlespan'><h3>" + "دقیقه"+ " : " + "ساعت" + " </h3></span>");

                            } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                            }
//                            System.out.println("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                            temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                        } else {
                            temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                        }

                        //<======= Time of Discount
                    }
                    //<======PRICE

                    //<============ BY RASHIDI ========
                    //By Md----------------------
                    /*نمایش جزئیات محصول*/
                    temphtml.append("<div class='productabstarctDiv'><h4>" + topProductRow.get(i).get(_page).toString());
                    for (int j = 1; j <= 5; j++) {
                        String Key = "account_product_val" + String.valueOf(j);
                        if ((topProductRow.get(i).get(Key) != null) && (!topProductRow.get(i).get(Key).toString().equals("0"))) {
//                        if (j == 1) {
//                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        } else {
                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        }
                        }
                    }

                    temphtml.append("</h4></div>");
                    //----------------------------
                    temphtml.append("<span class='moreDatale'>"
                            + "<a onclick='getOneproduct(" + id + ");'>" + "ادامه مطلب" + "</a>"
                            + "</span><br/>");
                    temphtml.append("<span class='coGruopproduct'>"
                            + "<a onclick='swGetProducts(" + topProductRow.get(i).get(_category_id).toString() + ");'>" + "مطالب مرتبط" + "</a>"
                            + "</span>");
                    temphtml.append("<img src='iconImages/cart.png' title='افزودن به سبد خرید' onclick='addToShoppingCart(" + topProductRow.get(i).get(_id).toString() + ")'>");//====== BY RASHIDI ======
                    temphtml.append("</div>");
                }
            }
            html3.append(Js.setHtml("#swTopproductDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            return html2 + html3;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //ایجاد or با تابع بازگشتی
    private static String getCategoryProducts(String categoryID, List<Map<String, Object>> rowCategoryFirst, Integer categoryProductId, jjDatabaseWeb db) {
        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + categoryProductId));// بر اساس حروف الفبا مرتب باشد بهتر است
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            Integer sumChiled = getSumChiled(rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()));
            if (sumChiled == 0) {
                categoryID += rowCategory.get(i).get(Category_Product._id).toString() + ",";
            } else {
                categoryID = getCategoryProducts(categoryID, rowCategoryFirst, Integer.parseInt(rowCategory.get(i).get(Category_Product._id).toString()), db);
            }
        }
        return categoryID;
    }

    private static int getSumChiled(List<Map<String, Object>> rowCategory, Integer numberID) {
        Integer sum = 0;
        for (int i = rowCategory.size() - 1; i >= 0; i--) {
            if (Integer.parseInt(rowCategory.get(i).get(Category_Product._parent).toString()) == numberID) {
                sum++;
            }
        }
        return sum;
    }

    //BY ENG mahdi hosseini
//این تابع با انتخاب یک دسته بندی اگر فرزندی داشت ادامه پیدا میکند و اگر فرزند نداشت هر چه محصول بااین دسته بندی ایجاد شده را به نمایش میگذارد 
    public static String getCategorySelectOption(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {//new in v1.5.0 لیست تمام محصولات را با توجه به زبان انتخاب شده برمیگرداند
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html1 = new StringBuilder();
            String script = "";
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                DefaultTableModel dtm = db.Select(Content.tableName, Content._title + "='ورود و ثبت نام'");
                List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
                return row.get(0).get(Content._content).toString();
            } else {
                if (needString) {
                    html1.append("<div class=\"col-sm-6 col-xs-12 object hidden-xs hidden-sm\">\n"
                            + "                    <svg class=\"svg\" version=\"1.1\" id=\"Layer_1\" xmlns=\"http://www.w3.org/2000/svg\" xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" viewBox=\"0 0 610 467\" style=\"enable-background:new 0 0 610 467;\" xml:space=\"preserve\">\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_1_\" gradientUnits=\"userSpaceOnUse\" x1=\"34\" y1=\"365.0896\" x2=\"591\" y2=\"365.0896\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#0071BC\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"8.528569e-02\" style=\"stop-color:#0876BE\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.2198\" style=\"stop-color:#1F82C4\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.3866\" style=\"stop-color:#4497CE\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5792\" style=\"stop-color:#77B3DB\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.7907\" style=\"stop-color:#B8D7EC\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#FFFFFF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st0\" d=\"M591,274.1L300.2,442c-4.8,2.7-10.6,2.7-15.4,0L34,297.2v8.1c0,1.9,1,3.7,2.7,4.7l250.6,144.7\n"
                            + "                          c3.2,1.9,7.2,1.9,10.4,0l289.5-167.1c2.3-1.4,3.8-3.8,3.8-6.5V274.1z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_2_\" gradientUnits=\"userSpaceOnUse\" x1=\"34.3472\" y1=\"286.1167\" x2=\"590.749\" y2=\"286.1167\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st1\" d=\"M292.5,444.1c-2.4,0-4.8-0.6-6.9-1.8L34.3,297.2l288.1-166.3c3.1-1.8,6.6-2.7,10.1-2.7\n"
                            + "                          c3.6,0,7.1,0.9,10.1,2.7l248.1,143.2L299.4,442.3C297.3,443.5,295,444.1,292.5,444.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st2\" d=\"M332.6,128.4c3.5,0,7,0.9,10,2.7l247.7,143L299.3,442c-2.1,1.2-4.4,1.8-6.8,1.8s-4.7-0.6-6.8-1.8L34.8,297.2\n"
                            + "                          l287.7-166.1C325.6,129.3,329,128.4,332.6,128.4 M332.6,127.9c-3.5,0-7.1,0.9-10.3,2.7L33.8,297.2l251.7,145.3\n"
                            + "                          c2.2,1.3,4.6,1.9,7,1.9s4.9-0.6,7-1.9l291.7-168.4L342.8,130.6C339.6,128.8,336.1,127.9,332.6,127.9L332.6,127.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"backline\" class=\"st3\">\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st4\" d=\"M176.3,279.3c-7.7,3.7-14.4,8.7-22,12.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M226.5,250.2c-13.4,7.7-26.7,15.5-40.1,23.3\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M278.6,219.9c-14,7.9-27.9,15.9-41.6,24.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M320.1,196.6c-7.1,4-14.1,8-21.2,12\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M358,174.4c-9.2,5.6-18.4,11-27.7,16.3\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M189.6,292.8c-5.4,3.1-10.7,6.2-16.1,9.3c-0.3,0.2-0.6,0.3-0.9,0.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M208.9,281.7c-3,1.7-6,3.5-9,5.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M240.2,263.6c-6.2,3.6-12.4,7.2-18.7,10.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M274.5,243.8c-8.1,4.7-16.2,9.3-24.3,14\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M298.9,229.8c-3.7,2.1-7.4,4.3-11.1,6.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M333.3,209.9c-7.9,4.6-15.9,9.2-23.8,13.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M376.4,185c-11,6.3-22,12.7-32.9,19\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M203.1,306.2c-4.4,2.7-7.8,5.2-12.2,7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M224.5,294c-3.8,2.2-7.6,4.3-11.3,6.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M251.3,278.5c-4.8,2.8-9.5,5.5-14.3,8.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M270.2,267.6c-2.2,1.2-4.3,2.5-6.5,3.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M309.3,244.8c-8.7,5.1-17.4,10.2-26.1,15.3\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M346.4,223.1c-8.9,5.2-17.7,10.3-26.6,15.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M388.8,198.9c-10.7,6-21.4,12-32,18.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M266.2,291.1c-18.6,11.5-38.2,21.3-56.8,32.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M321.4,259.2c-14.1,8.1-28.2,16.2-42.3,24.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M359.8,236.7c-9.1,5.5-18.3,10.8-27.5,16.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M399.3,214.3c-5,2.9-10,5.7-15,8.7c-4.5,2.6-8.8,6.2-13.8,7.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M413.2,206.2c-1.3,0.8-2.6,1.6-3.9,2.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M287.8,300.1c-20,11.5-40.1,22.9-60.1,34.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M373.7,250.2c-8,4.7-16,9.4-24.1,14.1\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M410.7,229.4c-9,4.9-17.8,10.2-26.8,15\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M431.5,216.8c-3.3,2.5-6.9,4.5-10.6,6.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M251.1,342.4c-1.6,1-3.3,2-5,2.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M386.9,263.8c-24.2,13.7-48.2,27.8-72.3,41.8c-6.9,4-13.8,7.9-20.6,11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M422,243.5c-8.1,4.7-16.2,9.4-24.4,14.1\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M449.9,227.4c-5.6,3.2-11.2,6.4-16.8,9.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M294,338.6c-10.2,5.1-19.7,11.4-29.5,17.1\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M321.1,322.9c-2.9,1.7-5.7,3.4-8.6,5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M434.5,257.6c-20.1,11.5-40.3,23-60.4,34.6c-5.6,3.2-11.2,6.5-16.8,9.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M468.3,238c-7.7,4.5-15.5,9-23.3,13.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M300.2,356.6c-5.8,3.3-11.6,6.5-17.4,9.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M329.9,339.3c-5.5,3.3-11.1,6.5-16.6,9.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M388.3,305.4c-10.5,5.9-20.9,11.9-31.3,18\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M445.5,272.4c-9.7,5.6-19.3,11.2-29,16.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M486.6,248.6c-10.2,5.9-20.4,11.8-30.6,17.7\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st4\" d=\"M309.7,346.2c8.4,4.9,16.6,10.1,25.1,14.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M254.5,314.5c5.2,3,10.4,6.1,15.6,9.1\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M190.2,277.5c17.3,9.9,34.5,19.8,51.7,29.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M171.5,266.7c2.2,1.3,4.4,2.5,6.6,3.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M351.2,346c1.6,0.9,3.2,1.9,4.8,2.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M313.9,324.5c6,3.5,12,6.9,18.1,10.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M276.2,302.7c7.7,4.5,15.4,8.9,23.1,13.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M254.1,290c3.8,2.2,7.7,4.5,11.6,6.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M192.7,254.5c3.1,1.8,6.2,3.6,9.3,5.4c8,4.6,16,9.2,24,13.8c5.9,3.4,11.8,6.8,17.7,10.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M371.3,333c2,1.2,4,2.4,6,3.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M344.4,317.2c2.8,1.6,5.6,3.3,8.4,4.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M308.8,296.7c4.7,2.7,9.4,5.4,14.1,8.1\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M279.5,280c6.2,3.5,12.5,7.1,18.7,10.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M214,242.3c3.2,2.2,7.4,3.9,11.7,6.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M372.6,310c8.6,4.8,17.5,9.1,25.9,14.3\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M354.5,298.9c1.5,0.7,2.9,1.5,4.3,2.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M330.3,285c4.5,2.6,8.9,5.1,13.4,7.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M299.9,267.4c6.3,3.7,12.6,7.3,19,10.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M283.7,257.9c1.7,1,3.3,2,5,2.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M235.2,230c13,7.1,25.8,14.5,38.6,22\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M402.6,302.1c5.7,3.3,11.4,6.6,17.1,9.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M375.9,286.8c3.9,2.2,7.7,4.5,11.6,6.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M353.6,274.1c3.8,2.2,7.7,4.3,11.5,6.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M287.5,235.9c7.5,4.4,15,8.8,22.5,13.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M256.4,217.8c7,4.1,14.1,8.1,21.1,12.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M427.5,292c5,1.8,9.2,5.2,13.4,7.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M309.1,224.4c37.9,21.7,74.8,44.3,113.3,64.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M277.6,205.5c0.8,0.4,1.6,0.9,2.4,1.3\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M378.8,239.5c24.4,14.2,49.5,28.5,73.7,42.6\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M298.8,193.3c14.7,7.6,28.1,16.8,42.9,24.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M447.2,254.5c12,6.9,24.1,13.9,36.1,20.8\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st4\" d=\"M320.9,181.5c32.8,18.5,64.7,37.8,97.6,56.3\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"on\">\n"
                            + "                    <path class=\"st5\" d=\"M260.4,433.8c0,1.5-1,2.1-2.3,1.3c-1.3-0.7-2.3-2.6-2.3-4c0-1.5,1-2.1,2.3-1.3S260.4,432.3,260.4,433.8z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st6 onpath\" d=\"M270.3,439.5c0,1.5-1,2.1-2.3,1.3c-1.3-0.7-2.3-2.6-2.3-4c0-1.5,1-2.1,2.3-1.3S270.3,438,270.3,439.5z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M280.2,445.2c0,1.5-1,2.1-2.3,1.3c-1.3-0.7-2.3-2.6-2.3-4s1-2.1,2.3-1.3C279.2,441.9,280.2,443.7,280.2,445.2z\n"
                            + "                          \"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g id=\"LineB2\">\n"
                            + "                    <line class=\"st8\" x1=\"73.5\" y1=\"187.5\" x2=\"73.5\" y2=\"202.5\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st8\" x1=\"73.5\" y1=\"161.5\" x2=\"73.5\" y2=\"171.5\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st8\" x1=\"88.5\" y1=\"153.5\" x2=\"88.5\" y2=\"168.5\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st8\" x1=\"91.5\" y1=\"198.5\" x2=\"91.5\" y2=\"213.5\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st8\" x1=\"91.5\" y1=\"177.5\" x2=\"91.5\" y2=\"185.5\"></line>\n"
                            + "\n");
                    html1.append("                    </g>\n"
                            + "                    <g id=\"LineB1\">\n"
                            + "                    <line class=\"st9\" x1=\"168\" y1=\"336.2\" x2=\"188.2\" y2=\"348.6\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st9\" x1=\"205.6\" y1=\"334.5\" x2=\"230.8\" y2=\"349.9\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st9\" x1=\"125.4\" y1=\"313.1\" x2=\"145.6\" y2=\"325.4\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st9\" x1=\"149\" y1=\"302.4\" x2=\"169\" y2=\"314.6\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st9\" x1=\"181.8\" y1=\"327.1\" x2=\"190\" y2=\"332.1\"></line>\n"
                            + "\n"
                            + "                    <line class=\"st9\" x1=\"200.9\" y1=\"362.8\" x2=\"218.2\" y2=\"373.3\"></line>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g class=\"st10\">\n"
                            + "                    <path class=\"st11\" d=\"M484.4,296.7c0-0.1,0-0.2,0-0.4c-2.4,2-5.3,3.4-8,4.9c-6.9,4-14.1,7.9-21,11.8c-1.9,1.1-3.3,2.5-5.2,3.5\n"
                            + "                          c-3.5,1.8-7.1-0.3-10.3-1.7c-1.2-0.5-1.8-1.6-2.9-2.3c-1.4-0.9-3.2-1.3-4.7-2c-4.9-2.6-9.6-5.6-14.4-8.3\n"
                            + "                          c-5.6-3.2-11.1-6.4-16.7-9.7c-4-2.3-8.1-4.6-12.1-7c-3.8-2.2-7.9-4.3-11.5-6.9c-0.9-0.7-2.6-1.2-2.6-2.5l-0.2,3.5\n"
                            + "                          c0,1.1,2.7,2.8,6,4.8c0,0,59,34.8,59.3,35c0.1,0,4,2.5,6.5,2.5c2.1,0,4.4-1.4,6.2-2.4c2.8-1.5,28.3-16.5,30.3-17.8\n"
                            + "                          c1.1-0.7,1.5-1.1,1.4-2.4C484.4,298.4,484.3,297.6,484.4,296.7z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st12\" d=\"M478.6,291.6c4,2.3,7.4,4,5,5.6l-34.9,20.3c-2.5,1.4-5.6-0.5-9.6-2.9l-58.5-33.8c-4-2.3-6.9-4.1-5-5.6\n"
                            + "                          l34.9-20.3c2.6-1.9,5.6,0.5,9.6,2.9L478.6,291.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st5 paper1\" d=\"M477,291.4c3.9,2.2,7.1,4,4.8,5.4l-33.6,19.5c-2.5,1.5-5.4-0.5-9.3-2.8L382.6,281c-3.9-2.2-7-3.4-4.8-5.4\n"
                            + "                          l33.6-19.5c2.7-1.7,5.4,0.5,9.3,2.8L477,291.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"paper\">\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_3_\" gradientUnits=\"userSpaceOnUse\" x1=\"381.443\" y1=\"271.1018\" x2=\"410.7388\" y2=\"271.1018\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st13\" d=\"M410.7,263.7c0.2,0.1-3.6,2.5-8.5,5.4l-9.6,5.6c-4.9,2.8-9,5.1-9.2,5l-1.9-1.1c-0.2-0.1,3.6-2.5,8.5-5.4\n"
                            + "                          l9.6-5.6c4.9-2.8,9-5.1,9.2-5L410.7,263.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_4_\" gradientUnits=\"userSpaceOnUse\" x1=\"386.2292\" y1=\"272.798\" x2=\"419.4848\" y2=\"272.798\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st14\" d=\"M419.5,264.3c0.2,0.1-4.2,2.8-9.8,6.1l-11,6.4c-5.6,3.3-10.3,5.8-10.5,5.7l-1.9-1.1\n"
                            + "                          c-0.2-0.1,4.2-2.8,9.8-6.1l11-6.4c5.6-3.3,10.3-5.8,10.5-5.7L419.5,264.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_5_\" gradientUnits=\"userSpaceOnUse\" x1=\"446.1336\" y1=\"292.47\" x2=\"463.715\" y2=\"292.47\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st15\" d=\"M463.7,288.1c0.1,0.1-2.1,1.5-5.1,3.2l-5.7,3.3c-2.9,1.7-5.4,3-5.5,2.9l-1.3-0.7c-0.1-0.1,2.1-1.5,5.1-3.2\n"
                            + "                          l5.7-3.3c2.9-1.7,5.4-3,5.5-2.9L463.7,288.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_6_\" gradientUnits=\"userSpaceOnUse\" x1=\"449.8626\" y1=\"296.138\" x2=\"463.7612\" y2=\"296.138\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st16\" d=\"M463.8,292.8c0.1,0.1-1.6,1.2-3.9,2.5l-4.5,2.6c-2.3,1.3-4.2,2.3-4.3,2.3l-1.2-0.7\n"
                            + "                          c-0.1-0.1,1.6-1.2,3.9-2.5l4.5-2.6c2.3-1.3,4.2-2.3,4.3-2.3L463.8,292.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_7_\" gradientUnits=\"userSpaceOnUse\" x1=\"453.8281\" y1=\"297.1475\" x2=\"471.0779\" y2=\"297.1475\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st17\" d=\"M471.1,292.8c0.1,0.1-2.1,1.5-5,3.1l-5.6,3.3c-2.9,1.7-5.3,3-5.4,2.9l-1.2-0.7c-0.1-0.1,2.1-1.5,5-3.1\n"
                            + "                          l5.6-3.3c2.9-1.7,5.3-3,5.4-2.9L471.1,292.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_8_\" gradientUnits=\"userSpaceOnUse\" x1=\"395.9037\" y1=\"288.5218\" x2=\"437.4376\" y2=\"288.5218\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st18\" d=\"M436,290.7c2.3,1.3,1.9,3.7-0.9,5.3l-5.4,3.2c-2.8,1.6-6.9,1.8-9.2,0.5l-23.1-13.3\n"
                            + "                          c-2.3-1.3-1.9-3.7,0.9-5.3l5.4-3.2c2.8-1.6,6.9-1.9,9.2-0.5L436,290.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_9_\" gradientUnits=\"userSpaceOnUse\" x1=\"413.9422\" y1=\"278.3771\" x2=\"455.4735\" y2=\"278.3771\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st19\" d=\"M454,280.5c2.3,1.3,1.9,3.7-0.9,5.3l-5.4,3.2c-2.8,1.6-6.9,1.9-9.2,0.5l-23.1-13.3\n"
                            + "                          c-2.3-1.3-1.9-3.7,0.9-5.3l5.4-3.2c2.8-1.6,6.9-1.9,9.2-0.5L454,280.5z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_10_\" gradientUnits=\"userSpaceOnUse\" x1=\"431.4645\" y1=\"304.8534\" x2=\"456.0399\" y2=\"304.8534\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n");
                    html1.append("                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st20\" d=\"M455.6,304.6c1.1,0.6,0.2,2.2-2,3.5l-4.3,2.5c-2.2,1.3-4.9,1.8-6,1.1l-11.3-6.5c-1.1-0.6-0.2-2.2,2-3.5\n"
                            + "                          l4.3-2.5c2.2-1.3,4.9-1.8,6-1.1L455.6,304.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"gear\">\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g class=\"st10\">\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M305.5,71.6l0-4.1c0-1.3,1.4-2.1,2.7-2.4c1.8-0.5,3.8-0.3,5.2,0.5c0.2,0.1,0.4,0.2,0.5,0.3l0,4.1\n"
                            + "                          c-0.2-0.1-0.3-0.2-0.5-0.3c-1.3-0.8-3.4-1-5.2-0.5C306.8,69.6,305.5,70.4,305.5,71.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M305.5,71.8L305.5,71.8c0-0.1,0-0.1,0-0.2l0-4.1c0,0.1,0,0.1,0,0.2C305.5,69.1,305.5,70.5,305.5,71.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M305.5,72.1c0-0.1,0-0.2,0-0.3c0-1.4,0-2.7,0-4.1c0,0.1,0,0.2,0,0.3C305.5,69.2,305.5,70.9,305.5,72.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M304.5,68l0,4.1c0-0.1,0-0.1,0-0.2c0-0.1,0-0.2,0-0.2l0-4.1c0,0.1,0,0.2,0,0.2\n"
                            + "                          C304.5,67.9,304.5,67.9,304.5,68z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M348.8,73.3l0,4.1c0-0.8-0.6-1.4-1.4-1.9c-1.3-0.8-3.2-1-4.9-0.6c-2.5,0.6-5.3,0.2-7.1-0.9\n"
                            + "                          c-1.2-0.7-1.9-1.7-1.9-2.7c0-0.7-0.5-1.4-1.3-1.9c-1-0.6-2.5-0.9-3.9-0.8c-1.4,0.1-2.5,0.5-3.3,1.2c-1.1,1-2.9,1.6-4.9,1.8\n"
                            + "                          c-2,0.1-4-0.3-5.4-1.1c-0.2-0.1-0.5-0.3-0.7-0.4l0-4.1c0.2,0.2,0.4,0.3,0.7,0.4c1.4,0.8,3.4,1.2,5.4,1.1c2-0.1,3.8-0.8,4.9-1.8\n"
                            + "                          c0.8-0.7,2-1.1,3.3-1.2c1.4-0.1,2.9,0.2,3.9,0.8c0.8,0.5,1.3,1.1,1.3,1.9c0,1,0.7,2,1.9,2.7c1.9,1.1,4.7,1.4,7.1,0.9\n"
                            + "                          c1.8-0.4,3.6-0.2,4.9,0.6C348.2,71.8,348.8,72.5,348.8,73.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M305.5,68l0,4.1c0,1.2-0.9,2.3-2.5,3c-1.7,0.8-4,1-6.1,0.6c-2.4-0.4-4.3,0.3-5.2,1.1\n"
                            + "                          c-0.5,0.4-0.8,0.9-0.8,1.5l0-4.1c0-0.6,0.3-1.1,0.8-1.5c0.9-0.8,2.8-1.5,5.2-1.1c2.1,0.4,4.4,0.1,6.1-0.6\n"
                            + "                          C304.6,70.3,305.5,69.2,305.5,68z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M348.8,77.4c0,0.5-0.3,1-0.9,1.5c0-1.4,0-2.7,0-4.1c0.6-0.5,0.9-1,0.9-1.5\n"
                            + "                          C348.8,74.5,348.8,76.1,348.8,77.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M347.9,78.9L347.9,78.9c-0.4,0.4-0.7,0.7-1,1.1c0-1.4,0-2.7,0-4.1c0.2-0.4,0.5-0.8,1-1.1\n"
                            + "                          C347.9,76.2,347.9,77.6,347.9,78.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M346.9,80c0-1.4,0-2.7,0-4.1c0,0,0,0,0,0C346.9,77.3,346.9,78.7,346.9,80C346.9,80,346.9,80,346.9,80z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M291.9,80c-0.8-0.6-1.1-1.1-1.1-1.7l0-4.1c0,0.5,0.3,1.1,1.1,1.7c0,0,0,0,0,0\n"
                            + "                          C291.9,77.3,291.9,78.6,291.9,80C291.9,80,291.9,80,291.9,80z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M349.8,73.3l0,4.1c0,0.6-0.3,1.3-1.1,1.9c-0.7,0.6-1,1.2-1,1.9l0-4.1c0-0.6,0.3-1.3,1-1.9\n"
                            + "                          C349.4,74.6,349.8,73.9,349.8,73.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M346.9,80c-0.2,0.4-0.3,0.7-0.3,1.1l0-4.1c0-0.4,0.1-0.7,0.3-1.1c0,1,0,2.1,0,3.1c0,0.2,0,0.3,0,0.5\n"
                            + "                          c0,0,0,0.1,0,0.1c0,0.1,0,0.1,0,0.2C346.9,79.9,346.9,80,346.9,80z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M292.5,78.4l0,4.1c0-0.7-0.4-1.5-1.3-2.1c-0.9-0.6-1.3-1.4-1.3-2.1l0-4.1c0,0.7,0.4,1.4,1.3,2.1\n"
                            + "                          C292,76.9,292.5,77.6,292.5,78.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M293.4,82.4c0-0.9-0.5-1.8-1.5-2.5c0-1.4,0-2.7,0-4.1c1,0.7,1.5,1.6,1.5,2.5\n"
                            + "                          C293.4,79.6,293.4,81.2,293.4,82.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M355.1,83.4l0,4.1c0-0.7-0.5-1.4-1.3-1.9c-0.6-0.3-1.3-0.6-2.1-0.7c-1.1-0.2-2.2-0.5-3-1\n"
                            + "                          c-1.3-0.8-2-1.7-2-2.7l0-4.1c0,1,0.7,2,2,2.7c0.8,0.5,1.9,0.8,3,1c0.8,0.1,1.6,0.4,2.1,0.7C354.7,82,355.1,82.7,355.1,83.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M301.9,84.7c-0.6,1.1-0.8,2.2-0.8,3.3l0-4.1c0-1.1,0.3-2.2,0.8-3.3c0,0.9,0,1.9,0,2.8c0,0.2,0,0.4,0,0.6\n"
                            + "                          c0,0.1,0,0.2,0,0.3c0,0,0,0.1,0,0.1C301.9,84.6,301.9,84.7,301.9,84.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M301.9,84.7c0-1.4,0-2.7,0-4.1c0.5-1,1.4-2,2.4-3c6-5.1,18-6.5,26.9-3c1,0.4,2,0.9,2.9,1.4\n"
                            + "                          c3.8,2.2,5.7,5.1,5.7,8l0,4.1c0-2.9-1.9-5.8-5.7-8c-0.9-0.5-1.8-1-2.9-1.4c-8.9-3.5-20.9-2.1-26.9,3\n"
                            + "                          C303.3,82.7,302.4,83.7,301.9,84.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M338.8,84l0,4.1c0-2.8-1.9-5.5-5.4-7.6c-0.8-0.5-1.7-0.9-2.7-1.3c-8.4-3.3-19.8-2-25.5,2.8\n"
                            + "                          c-2.1,1.8-3.1,3.9-3.1,5.9l0-4.1c0-2,1-4.1,3.1-5.9c5.7-4.9,17.1-6.1,25.5-2.8c1,0.4,1.9,0.8,2.7,1.3\n"
                            + "                          C336.9,78.5,338.8,81.2,338.8,84z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M293.4,78.4l0,4.1c0,0.4-0.1,0.7-0.3,1c-0.6,1.2-2.2,2.2-4.3,2.6c-1.6,0.3-2.7,1-3,1.9\n"
                            + "                          c-0.1,0.2-0.1,0.4-0.1,0.6l0-4.1c0-0.2,0-0.4,0.1-0.6c0.4-0.9,1.5-1.6,3-1.9c2.1-0.4,3.7-1.4,4.3-2.6\n"
                            + "                          C293.4,79.1,293.4,78.7,293.4,78.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M355,88.1c-0.4,0.9-1.5,1.6-3,1.9c0-1.4,0-2.7,0-4.1c1.6-0.3,2.7-1,3-1.9c0.1-0.2,0.1-0.4,0.1-0.6l0,4.1\n"
                            + "                          C355.1,87.7,355.1,87.9,355,88.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M287.1,90.5c-0.9-0.5-1.3-1.2-1.3-1.9l0-4.1c0,0.7,0.5,1.4,1.3,1.9C287.1,87.8,287.1,89.2,287.1,90.5\n"
                            + "                          c0-1.4,0-2.7,0-4.1c0.6,0.3,1.3,0.6,2.1,0.7c0,0,0,0,0,0c0,1.4,0,2.7,0,4.1c0,0,0,0,0,0C288.4,91.1,287.7,90.9,287.1,90.5z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M292.3,92.2c-0.8-0.5-1.9-0.8-3-1c0-1.4,0-2.7,0-4.1c1.2,0.2,2.2,0.5,3,1c0.5,0.3,0.9,0.6,1.2,1\n"
                            + "                          c0,1.4,0,2.7,0,4.1C293.2,92.9,292.8,92.5,292.3,92.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M293.5,93.2C293.5,93.2,293.5,93.2,293.5,93.2c0-1.4,0-2.7,0-4.1c0,0,0,0,0,0\n"
                            + "                          C293.5,90.5,293.5,91.9,293.5,93.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M347.7,92.6c-0.2,0.3-0.3,0.7-0.3,1l0-4.1c0-0.3,0.1-0.7,0.3-1c0.6-1.2,2.2-2.2,4.3-2.6\n"
                            + "                          c0,1.4,0,2.7,0,4.1C349.9,90.4,348.3,91.4,347.7,92.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M356.1,83.4l0,4.1c0,1.3-1.3,2.6-3.8,3.1c-2.4,0.5-3.8,1.8-3.9,3.1l0-4.1c0-1.3,1.4-2.6,3.9-3.1\n"
                            + "                          C354.9,86,356.1,84.7,356.1,83.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n");
                    html1.append("                    <path class=\"st22\" d=\"M293.5,93.2c0,0,0-0.1,0-0.1c0,0,0-0.1,0-0.1c0-0.1,0-0.2,0-0.2c0-0.2,0-0.3,0-0.5c0-1.1,0-2.1,0-3.2\n"
                            + "                          c0.5,0.5,0.7,1.1,0.7,1.7l0,4.1C294.2,94.4,294,93.8,293.5,93.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M293.2,90.9l0,4.1c0-0.9-0.6-1.7-1.7-2.3c-0.7-0.4-1.6-0.7-2.6-0.8c-1.1-0.2-1.9-0.5-2.6-0.8\n"
                            + "                          c-1.1-0.6-1.6-1.5-1.6-2.3l0-4.1c0,0.9,0.5,1.7,1.6,2.3c0.7,0.4,1.5,0.7,2.6,0.8c1,0.2,1.9,0.5,2.6,0.8\n"
                            + "                          C292.6,89.2,293.2,90,293.2,90.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M350,93.7l0,4.1c0-0.5-0.3-1.1-1.1-1.7c-1-0.7-1.5-1.6-1.5-2.5l0-4.1c0,0.9,0.5,1.8,1.5,2.5\n"
                            + "                          C349.8,92.6,350,93.2,350,93.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M294.2,90.9l0,4.1c0,0.8-0.4,1.6-1.2,2.2c-0.6,0.5-0.9,1-0.9,1.5l0-4.1c0-0.5,0.3-1,0.9-1.5\n"
                            + "                          C293.8,92.4,294.2,91.7,294.2,90.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M339.8,84l0,4.1c0,2.2-1.1,4.3-3.3,6.2c-2.9,2.5-7.3,4.2-12.4,4.7c-5.1,0.6-10.2,0-14.5-1.7\n"
                            + "                          c-1-0.4-2-0.9-2.9-1.4c-3.8-2.2-5.7-5.1-5.7-8l0-4.1c0,2.9,1.9,5.8,5.7,8c0.9,0.5,1.8,1,2.9,1.4c4.3,1.7,9.5,2.3,14.5,1.7\n"
                            + "                          c5.1-0.6,9.5-2.2,12.4-4.7C338.7,88.4,339.8,86.2,339.8,84z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M349.2,99.3c-0.9,0.8-2.8,1.5-5.2,1.1c0-1.2,0-2.4,0-3.6l0-0.5c2.4,0.5,4.3-0.3,5.2-1.1\n"
                            + "                          C349.2,96.6,349.2,98,349.2,99.3c0-1.4,0-2.7,0-4.1c0.5-0.4,0.8-0.9,0.8-1.5l0,4.1C350,98.4,349.7,98.9,349.2,99.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M293.5,100.7c-0.8-0.5-1.4-1.2-1.4-1.9l0-4.1c0,0.8,0.6,1.5,1.4,1.9C293.5,97.9,293.5,99.3,293.5,100.7\n"
                            + "                          c0-1.4,0-2.7,0-4.1c1.3,0.8,3.2,1,5,0.6c0,1.4,0,2.7,0,4.1C296.7,101.6,294.9,101.4,293.5,100.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M337.9,101c-1.6,0.7-2.5,1.8-2.5,3l0-4.1c0-1.2,0.9-2.3,2.5-3C337.9,98.3,337.9,99.6,337.9,101\n"
                            + "                          c0-1.4,0-2.7,0-4.1c1.7-0.8,4-1,6.1-0.6l0,4.1C341.9,100,339.6,100.2,337.9,101z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M335.4,100.4l0,4.1c0-0.1,0-0.1,0-0.2c0-0.1,0-0.2,0-0.3l0-4.1c0,0.1,0,0.2,0,0.3\n"
                            + "                          C335.4,100.3,335.4,100.3,335.4,100.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M327,106c0-1.4,0-2.7,0-4.1c0.2,0.1,0.3,0.2,0.5,0.3c1.3,0.8,3.4,1,5.2,0.5c0,1.4,0,2.7,0,4.1\n"
                            + "                          c0-1.4,0-2.7,0-4.1c1.3-0.3,2.7-1.2,2.7-2.4l0,4.1c0,1.3-1.4,2.1-2.7,2.4c-1.8,0.5-3.8,0.3-5.2-0.5\n"
                            + "                          C327.4,106.3,327.2,106.1,327,106z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M344,96.3c-2.1-0.4-4.4-0.2-6.1,0.6c-1.7,0.8-2.7,2-2.5,3.3c0.1,1.4-1.3,2.2-2.7,2.6\n"
                            + "                          c-1.8,0.5-3.8,0.3-5.2-0.5c-0.2-0.1-0.4-0.2-0.5-0.3c-0.2-0.2-0.4-0.3-0.7-0.4c-1.4-0.8-3.4-1.2-5.4-1.1\n"
                            + "                          c-2,0.1-3.8,0.8-4.9,1.8c-0.8,0.7-2,1.1-3.3,1.2c-1.4,0.1-2.9-0.2-3.9-0.8c-0.8-0.5-1.3-1.1-1.3-1.9c0-1-0.7-2-1.9-2.7\n"
                            + "                          c-1.9-1.1-4.7-1.4-7.1-0.9c-1.8,0.4-3.6,0.2-5-0.6c-1.4-0.8-2.1-2.2-0.5-3.5c1.9-1.5,1.6-3.6-0.7-5c-0.8-0.5-1.9-0.8-3-1\n"
                            + "                          c-0.9-0.1-1.6-0.4-2.1-0.7c-1.1-0.6-1.6-1.6-1.2-2.6c0.4-0.9,1.5-1.6,3-1.9c2.1-0.4,3.7-1.4,4.3-2.6c0.6-1.2,0.1-2.5-1.3-3.5\n"
                            + "                          c-1.6-1.1-1.2-2.4-0.3-3.2c0.9-0.8,2.8-1.5,5.2-1.1c2.1,0.4,4.4,0.1,6.1-0.6c1.7-0.8,2.7-2,2.5-3.3c-0.2-1.4,1.3-2.2,2.7-2.6\n"
                            + "                          c1.8-0.5,3.8-0.3,5.2,0.5c0.2,0.1,0.4,0.2,0.5,0.3c0.2,0.2,0.4,0.3,0.7,0.4c1.4,0.8,3.4,1.2,5.4,1.1c2-0.1,3.8-0.8,4.9-1.8\n"
                            + "                          c0.8-0.7,2-1.1,3.3-1.2c1.4-0.1,2.9,0.2,3.9,0.8c0.8,0.5,1.3,1.1,1.3,1.9c0,1,0.7,2,1.9,2.7c1.9,1.1,4.7,1.4,7.1,0.9\n"
                            + "                          c1.8-0.4,3.6-0.2,4.9,0.6c1.4,0.8,2.1,2.2,0.5,3.5c-1.9,1.5-1.6,3.6,0.7,5c0.8,0.5,1.9,0.8,3,1c0.8,0.1,1.6,0.4,2.1,0.7\n"
                            + "                          c1.1,0.6,1.6,1.6,1.2,2.6c-0.4,0.9-1.5,1.6-3,1.9c-2.1,0.4-3.7,1.4-4.3,2.6c-0.6,1.2-0.1,2.5,1.3,3.5c1.6,1.1,1.2,2.4,0.3,3.2\n"
                            + "                          C348.3,96,346.4,96.7,344,96.3z M324.2,95c5.1-0.6,9.5-2.2,12.4-4.7c5.2-4.4,4.2-10.4-2.4-14.2c-0.9-0.5-1.8-1-2.9-1.4\n"
                            + "                          c-8.9-3.5-20.9-2.1-26.9,3c-5.2,4.4-4.2,10.4,2.4,14.2c0.9,0.5,1.8,1,2.9,1.4C313.9,94.9,319.1,95.6,324.2,95\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M334.1,76c6.6,3.8,7.6,9.8,2.4,14.2c-2.9,2.5-7.3,4.2-12.4,4.7c-5.1,0.6-10.2,0-14.5-1.7\n"
                            + "                          c-1-0.4-2-0.9-2.9-1.4c-6.6-3.8-7.6-9.8-2.4-14.2c6-5.1,18-6.5,26.9-3C332.3,75.1,333.2,75.5,334.1,76z M310.2,92.8\n"
                            + "                          c8.4,3.3,19.8,2,25.5-2.8c5-4.3,3.8-9.9-2.3-13.5c-0.8-0.5-1.7-0.9-2.7-1.3c-8.4-3.3-19.8-2-25.5,2.8c-5,4.3-3.8,9.9,2.3,13.5\n"
                            + "                          C308.3,92,309.2,92.4,310.2,92.8\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M301.7,101c-0.1,0-0.1,0-0.2,0c0-1.4,0-2.7,0-4.1c0.1,0,0.1,0,0.2,0C301.7,98.3,301.7,99.6,301.7,101z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M301.7,101c0-1.4,0-2.7,0-4.1c0.4,0,0.8,0.1,1.2,0.2c0,1.4,0,2.7,0,4.1C302.5,101.1,302.1,101,301.7,101z\n"
                            + "                          \"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M301.5,101c-1-0.1-2,0-3,0.3c0-1.4,0-2.7,0-4.1c1-0.2,2-0.3,3-0.3C301.5,98.3,301.5,99.6,301.5,101z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M351.1,93.7l0,4.1c0,2-3.4,3.8-7.4,3.1l0-4.1C347.7,97.6,351,95.8,351.1,93.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M336.4,104l0-4.1c0-2.2,3.7-3.8,7.3-3.1l0,4.1C340,100.2,336.4,101.8,336.4,104z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M302.9,101.2c0-1.4,0-2.7,0-4.1c1,0.2,1.9,0.5,2.7,1c1.2,0.7,1.9,1.7,1.9,2.7c0,1.4,0,2.7,0,4.1\n"
                            + "                          c0-1-0.7-2-1.9-2.7C304.8,101.7,303.9,101.3,302.9,101.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M321,104.5c-2,0.1-3.8,0.8-4.9,1.8c0-1.4,0-2.7,0-4.1c1.1-1,2.8-1.6,4.9-1.8c2-0.1,4,0.3,5.4,1.1\n"
                            + "                          c0,1.4,0,2.7,0,4.1c0-1.4,0-2.7,0-4.1c0.2,0.1,0.5,0.3,0.7,0.4l0,4.1c-0.2-0.2-0.4-0.3-0.7-0.4\n"
                            + "                          C324.9,104.7,323,104.3,321,104.5z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M308.9,106.7c-0.8-0.5-1.3-1.1-1.3-1.9c0-1.4,0-2.7,0-4.1c0,0.7,0.5,1.4,1.3,1.9c1,0.6,2.5,0.9,3.9,0.8\n"
                            + "                          c0,1.4,0,2.7,0,4.1c0-1.4,0-2.7,0-4.1c1.4-0.1,2.6-0.5,3.3-1.2c0,0,0,0,0,0c0,1.4,0,2.7,0,4.1c0,0,0,0,0,0\n"
                            + "                          c-0.8,0.7-2,1.1-3.3,1.2C311.3,107.5,309.9,107.3,308.9,106.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M336.4,100.4l0,4.1c0,2.9-6.1,4.3-9.6,2.3c-0.2-0.1-0.4-0.3-0.6-0.4c-0.2-0.1-0.4-0.3-0.6-0.4\n"
                            + "                          c-2.5-1.4-6.8-1.2-8.7,0.6c-2.1,1.9-6.4,1.9-8.8,0.5c-1-0.6-1.6-1.3-1.6-2.3c0-0.9-0.7-1.7-1.7-2.3c-1.5-0.9-3.8-1.2-6.1-0.7\n"
                            + "                          c-2.3,0.5-4.6,0.1-6-0.7c-1.1-0.6-1.7-1.5-1.7-2.3l0-4.1c0,0.9,0.7,1.7,1.7,2.3c1.5,0.8,3.7,1.2,6,0.7c2.2-0.5,4.6-0.1,6.1,0.7\n"
                            + "                          c1,0.6,1.6,1.4,1.7,2.3c0,0.9,0.6,1.7,1.6,2.3c2.3,1.4,6.7,1.4,8.8-0.5c2-1.8,6.2-2,8.7-0.6c0.2,0.1,0.4,0.2,0.6,0.4\n"
                            + "                          c0.2,0.2,0.4,0.3,0.6,0.4C330.3,104.7,336.4,103.3,336.4,100.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M343.7,96.8c-3.8-0.7-7.6,1-7.3,3.3c0.3,3.1-6,4.6-9.5,2.5c-0.2-0.1-0.4-0.3-0.6-0.4\n"
                            + "                          c-0.2-0.1-0.4-0.3-0.6-0.4c-2.5-1.4-6.8-1.2-8.7,0.6c-2.1,1.9-6.4,1.9-8.8,0.5c-1-0.6-1.6-1.3-1.6-2.3c0-0.9-0.7-1.7-1.7-2.3\n"
                            + "                          c-1.5-0.9-3.8-1.2-6.1-0.7c-2.3,0.5-4.6,0.1-6-0.7c-1.8-1-2.4-2.8-0.6-4.2c1.7-1.4,1.2-3.1-0.6-4.2c-0.7-0.4-1.6-0.7-2.6-0.8\n"
                            + "                          c-1.1-0.2-1.9-0.5-2.6-0.8c-2.7-1.6-2.1-4.6,2.2-5.4c3.8-0.7,5.1-3.4,2.5-5.2c-3.6-2.5,0.7-6.2,6-5.2c3.8,0.7,7.6-1,7.3-3.3\n"
                            + "                          c-0.3-3.1,6-4.6,9.5-2.5c0.2,0.1,0.4,0.3,0.6,0.4c0.2,0.1,0.4,0.3,0.6,0.4c2.5,1.4,6.8,1.2,8.7-0.6c2.1-1.9,6.4-1.9,8.8-0.5\n"
                            + "                          c1,0.6,1.6,1.3,1.6,2.3c0,0.9,0.7,1.7,1.6,2.3c1.5,0.9,3.8,1.2,6.1,0.7c2.3-0.5,4.6-0.1,6,0.7c1.8,1,2.4,2.8,0.6,4.2\n"
                            + "                          c-1.7,1.4-1.2,3.1,0.6,4.2c0.7,0.4,1.6,0.7,2.6,0.8c1.1,0.2,1.9,0.5,2.6,0.8c2.7,1.6,2.1,4.6-2.2,5.4c-3.8,0.7-5.1,3.4-2.6,5.2\n"
                            + "                          C353.3,94.2,349.1,97.8,343.7,96.8z M337.9,96.9c1.7-0.8,4-1,6.1-0.6c2.4,0.5,4.3-0.3,5.2-1.1c0.9-0.8,1.4-2.1-0.3-3.2\n"
                            + "                          c-1.4-1-1.9-2.3-1.3-3.5c0.6-1.2,2.2-2.2,4.3-2.6c1.6-0.3,2.7-1,3-1.9c0.4-0.9-0.1-1.9-1.2-2.6c-0.6-0.3-1.3-0.6-2.1-0.7\n"
                            + "                          c-1.1-0.2-2.2-0.5-3-1c-2.3-1.3-2.6-3.4-0.7-5c1.6-1.3,0.9-2.7-0.5-3.5c-1.3-0.8-3.2-1-4.9-0.6c-2.5,0.6-5.3,0.2-7.1-0.9\n"
                            + "                          c-1.2-0.7-1.9-1.7-1.9-2.7c0-0.7-0.5-1.4-1.3-1.9c-1-0.6-2.5-0.9-3.9-0.8c-1.4,0.1-2.5,0.5-3.3,1.2c-1.1,1-2.9,1.6-4.9,1.8\n"
                            + "                          c-2,0.1-4-0.3-5.4-1.1c-0.2-0.1-0.5-0.3-0.7-0.4c-0.2-0.1-0.3-0.2-0.5-0.3c-1.3-0.8-3.4-1-5.2-0.5c-1.4,0.4-2.8,1.2-2.7,2.6\n"
                            + "                          c0.1,1.3-0.8,2.5-2.5,3.3c-1.7,0.8-4,1-6.1,0.6c-2.4-0.4-4.3,0.3-5.2,1.1c-0.9,0.8-1.4,2.1,0.3,3.2c1.4,1,1.9,2.3,1.3,3.5\n"
                            + "                          c-0.6,1.2-2.2,2.2-4.3,2.6c-1.6,0.3-2.7,1-3,1.9c-0.4,0.9,0.1,1.9,1.2,2.6c0.6,0.3,1.3,0.6,2.1,0.7c1.2,0.2,2.2,0.5,3,1\n"
                            + "                          c2.3,1.3,2.6,3.4,0.7,5c-1.6,1.3-0.8,2.7,0.5,3.5c1.3,0.8,3.2,1,5,0.6c2.5-0.6,5.3-0.2,7.1,0.9c1.2,0.7,1.9,1.7,1.9,2.7\n"
                            + "                          c0,0.7,0.5,1.4,1.3,1.9c1,0.6,2.5,0.9,3.9,0.8c1.4-0.1,2.6-0.5,3.3-1.2c1.1-1,2.8-1.6,4.9-1.8c2-0.1,4,0.3,5.4,1.1\n"
                            + "                          c0.2,0.1,0.5,0.3,0.7,0.4c0.2,0.1,0.3,0.2,0.5,0.3c1.3,0.8,3.4,1,5.2,0.5c1.4-0.4,2.8-1.2,2.7-2.6\n"
                            + "                          C335.3,98.9,336.2,97.7,337.9,96.9\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g class=\"st10\">\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M384.2,51.2l0,2.2c0,0.1,0,0.2,0,0.2c0,0.1-0.1,0.3-0.1,0.4l0-2.2c0-0.1,0-0.3,0.1-0.4\n"
                            + "                          C384.2,51.4,384.2,51.3,384.2,51.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M385,51.2l0,2.2c0,0.1,0,0.2,0,0.3c0,0.1,0,0.2,0,0.3l0-2.2c0-0.1,0-0.2,0-0.3\n"
                            + "                          C385,51.4,385,51.3,385,51.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M384.2,51.2l0,2.2c0-0.3-0.2-0.6-0.6-0.9c-0.4-0.3-1.1-0.4-1.7-0.4c-0.4,0-1,0.1-1.6,0.4\n"
                            + "                          c-0.7,0.4-1.7,0.7-2.7,0.7c-1,0-2.1-0.2-2.8-0.6c-0.3-0.2-0.5-0.4-0.7-0.6c-0.1-0.1-0.3-0.3-0.4-0.4c-0.6-0.3-1.5-0.4-2.3-0.3\n"
                            + "                          c-0.8,0.2-1.3,0.5-1.5,1.1c-0.2,0.7-0.9,1.4-2,1.7c-1.1,0.3-2.5,0.3-3.6,0c-1.1-0.3-2.1-0.1-2.6,0.2c-0.4,0.2-0.7,0.5-0.7,0.9\n"
                            + "                          l0-2.2c0-0.4,0.3-0.7,0.7-0.9c0.6-0.3,1.5-0.6,2.6-0.2c1.1,0.3,2.5,0.4,3.6,0c1.1-0.3,1.9-1,2-1.7c0.1-0.5,0.6-0.9,1.5-1.1\n"
                            + "                          c0.8-0.2,1.7-0.1,2.3,0.3c0.2,0.1,0.3,0.2,0.4,0.4c0.2,0.2,0.4,0.4,0.7,0.6c0.7,0.4,1.7,0.7,2.8,0.6c1,0,2-0.3,2.7-0.7\n"
                            + "                          c0.5-0.3,1.1-0.4,1.6-0.4c0.6,0,1.2,0.1,1.7,0.4C384,50.6,384.2,50.9,384.2,51.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M361,54.7l0,2.2c0-0.3-0.1-0.6-0.4-0.9c-0.3-0.3-0.4-0.6-0.4-0.9l0-2.2c0,0.3,0.1,0.6,0.4,0.9\n"
                            + "                          C360.9,54.1,361,54.4,361,54.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M361.8,54.7l0,2.2c0-0.4-0.2-0.7-0.5-1.1c-0.2-0.2-0.3-0.4-0.3-0.6l0-2.2c0,0.2,0.1,0.4,0.3,0.6\n"
                            + "                          C361.6,54,361.8,54.3,361.8,54.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M382.3,57.4c-1.6-0.9-3.7-1.5-5.9-1.7c0-0.7,0-1.5,0-2.2c2.3,0.2,4.3,0.8,5.9,1.7\n"
                            + "                          C382.3,55.9,382.3,56.7,382.3,57.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M384.1,54l0-2.2c0,0.6,0.4,1.2,1.1,1.6c0.9,0.5,2.2,0.7,3.4,0.6c1-0.1,1.7,0.2,2,0.3\n"
                            + "                          c0.4,0.3,0.7,0.6,0.7,1l0,2.2c0-0.4-0.3-0.7-0.7-1c-0.3-0.2-1-0.4-2-0.3c-1.2,0.1-2.5-0.1-3.4-0.6\n"
                            + "                          C384.5,55.2,384.1,54.6,384.1,54z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M392.1,55.3l0,2.2c0,0.4-0.3,0.9-0.9,1.2c-0.6,0.3-0.9,0.8-0.9,1.3l0-2.2c0-0.5,0.3-0.9,0.9-1.3\n"
                            + "                          C391.8,56.2,392.1,55.7,392.1,55.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M391.3,55.3l0,2.2c0,0.2-0.1,0.6-0.7,0.9c-0.7,0.4-1.2,1-1.2,1.6l0-2.2c0-0.6,0.4-1.2,1.2-1.6\n"
                            + "                          C391.2,55.9,391.3,55.5,391.3,55.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M361.8,54.7l0,2.2c0,0.3-0.1,0.7-0.4,1c-0.6,0.7-1.6,1.1-2.9,1.2c-0.9,0.1-1.5,0.4-1.8,0.8\n"
                            + "                          c-0.1,0.1-0.1,0.3-0.1,0.4l0-2.2c0-0.1,0-0.3,0.1-0.4c0.3-0.5,0.9-0.8,1.8-0.8c1.3-0.1,2.3-0.5,2.9-1.2\n"
                            + "                          C361.7,55.4,361.8,55.1,361.8,54.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M376.3,55.7c-5.8-0.5-11.3,1.8-12.1,5.2c0-0.7,0-1.5,0-2.2c0.9-3.4,6.3-5.7,12.1-5.2l0,0\n"
                            + "                          C376.3,54.2,376.3,54.9,376.3,55.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M385.4,61.8c0-1.6-1.1-3.2-3.2-4.4c0-0.7,0-1.5,0-2.2c2,1.2,3.2,2.8,3.2,4.4\n"
                            + "                          C385.4,60.3,385.4,61.2,385.4,61.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M384.6,59.6l0,2.2c0-1.6-1.1-3-2.9-4.1c-1.4-0.8-3.3-1.4-5.5-1.6c-5.4-0.5-10.4,1.7-11.2,4.8\n"
                            + "                          c-0.1,0.3-0.1,0.6-0.1,0.8l0-2.2c0-0.3,0-0.6,0.1-0.8c0.8-3.1,5.8-5.3,11.2-4.8c2.2,0.2,4.1,0.8,5.5,1.6\n"
                            + "                          C383.5,56.6,384.6,58,384.6,59.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M385.4,59.6l0,2.2c0,0.3,0,0.6-0.1,0.9c-0.9,3.4-6.3,5.7-12.1,5.2c-2.3-0.2-4.3-0.8-5.9-1.7\n"
                            + "                          c-2-1.2-3.2-2.8-3.2-4.4l0-2.2c0,1.6,1.1,3.2,3.2,4.4c1.6,0.9,3.7,1.5,5.9,1.7c5.8,0.5,11.3-1.8,12.1-5.2\n"
                            + "                          C385.4,60.2,385.4,59.9,385.4,59.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M364.2,60.8c-0.1,0.3-0.1,0.6-0.1,0.9l0-2.2c0-0.3,0-0.6,0.1-0.9c0,0.4,0,0.7,0,1.1c0,0,0,0.1,0,0.1\n"
                            + "                          c0,0.1,0,0.3,0,0.4c0,0.1,0,0.2,0,0.3C364.2,60.7,364.2,60.8,364.2,60.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M391.7,59.8l0,2.2c-0.4-0.1-0.8-0.3-1.1-0.4c-0.7-0.4-1.1-1-1.1-1.6l0-2.2c0,0.6,0.4,1.2,1.1,1.6\n"
                            + "                          C390.9,59.6,391.2,59.7,391.7,59.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M392.9,61l0,2.2c0-0.3-0.2-0.7-0.6-0.9c-0.2-0.1-0.4-0.2-0.7-0.3l0-2.2c0.3,0.1,0.5,0.2,0.7,0.3\n"
                            + "                          C392.7,60.3,392.9,60.6,392.9,61z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M392.9,63.2c0,0.1,0,0.3-0.1,0.4c0-0.7,0-1.5,0-2.2c0.1-0.1,0.1-0.3,0.1-0.4\n"
                            + "                          C392.9,61.6,392.9,62.5,392.9,63.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M356.6,60.4l0-2.2c0,0.3,0.2,0.7,0.6,0.9c0.2,0.1,0.4,0.2,0.7,0.3c0.4,0.1,0.8,0.3,1.1,0.4\n"
                            + "                          c0.7,0.4,1.1,1,1.1,1.6l0,2.2c0-0.6-0.4-1.2-1.1-1.6c-0.3-0.2-0.7-0.3-1.1-0.4c-0.2-0.1-0.5-0.2-0.7-0.3\n"
                            + "                          C356.8,61,356.6,60.7,356.6,60.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M359.3,61.4l0,2.2c0-0.5-0.3-0.9-0.9-1.2c-0.2-0.1-0.5-0.3-0.9-0.4c-0.4-0.1-0.7-0.2-0.9-0.4\n"
                            + "                          c-0.6-0.3-0.9-0.8-0.9-1.2l0-2.2c0,0.4,0.3,0.9,0.9,1.2c0.2,0.1,0.5,0.3,0.9,0.4c0.3,0.1,0.6,0.2,0.9,0.4\n"
                            + "                          C359,60.4,359.3,60.9,359.3,61.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M392.8,63.6c-0.3,0.5-0.9,0.8-1.8,0.8c0-0.7,0-1.5,0-2.2c0.9-0.1,1.5-0.4,1.8-0.8c0,0.4,0,0.7,0,1.1\n"
                            + "                          c0,0,0,0.1,0,0.1c0,0.3,0,0.6,0,0.8C392.8,63.5,392.8,63.6,392.8,63.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M391,64.4c-1.3,0.1-2.3,0.5-2.9,1.2c0-0.7,0-1.5,0-2.2c0.6-0.7,1.6-1.1,2.9-1.2C391,63,391,63.7,391,64.4\n"
                            + "                          z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M360.1,61.4l0,2.2c0,0.6-0.4,1.2-1.2,1.6c-0.6,0.3-0.7,0.7-0.7,0.9l0-2.2c0-0.2,0.1-0.6,0.7-0.9\n"
                            + "                          C359.7,62.5,360.1,62,360.1,61.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M358.2,66.1l0-2.2c0,0,0,0,0,0L358.2,66.1C358.2,66,358.2,66,358.2,66.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M393.8,61l0,2.2c0,0.8-0.9,1.6-2.6,1.7c-1.6,0.1-2.6,0.9-2.6,1.7l0-2.2c0-0.8,1-1.6,2.6-1.7\n"
                            + "                          C392.8,62.6,393.7,61.8,393.8,61z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M388.1,65.6c-0.3,0.3-0.4,0.7-0.4,1l0-2.2c0-0.3,0.1-0.7,0.4-1c0,0.4,0,0.7,0,1.1c0,0,0,0.1,0,0.1\n"
                            + "                          c0,0.3,0,0.6,0,0.8C388.1,65.6,388.1,65.6,388.1,65.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M388.5,66.2l0,2.2c0-0.2-0.1-0.4-0.3-0.6c-0.3-0.3-0.5-0.7-0.5-1.1l0-2.2c0,0.4,0.2,0.7,0.5,1.1\n"
                            + "                          C388.4,65.7,388.5,66,388.5,66.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M382.3,55.2c2.4,1.4,3.5,3.4,3,5.3c-0.9,3.4-6.3,5.7-12.1,5.2c-2.3-0.2-4.3-0.8-5.9-1.7\n"
                            + "                          c-2.4-1.4-3.5-3.4-3-5.3c0.9-3.4,6.3-5.7,12.1-5.2C378.6,53.7,380.7,54.3,382.3,55.2z M373.3,65.2c5.4,0.5,10.4-1.7,11.2-4.8\n"
                            + "                          c0.5-1.9-0.7-3.7-2.8-4.9c-1.4-0.8-3.3-1.4-5.5-1.6c-5.4-0.5-10.4,1.7-11.2,4.8c-0.5,1.9,0.7,3.7,2.8,4.9\n"
                            + "                          C369.3,64.5,371.1,65,373.3,65.2\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M388.5,68.4c0,0.4-0.3,0.7-0.7,0.9c0-0.7,0-1.5,0-2.2c0.4-0.2,0.7-0.5,0.7-0.9\n"
                            + "                          C388.5,66.8,388.5,67.7,388.5,68.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M385.2,69.5c-1.1-0.3-2.4-0.4-3.6,0c0-0.7,0-1.5,0-2.2c1.1-0.3,2.5-0.3,3.6,0\n"
                            + "                          C385.2,68,385.2,68.7,385.2,69.5z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M365.4,67.3l0,2.2c0-0.6-0.4-1.2-1.1-1.6c-0.9-0.5-2.2-0.8-3.4-0.6c-1,0.1-1.7-0.2-2-0.3\n"
                            + "                          c-0.4-0.2-0.7-0.6-0.7-0.9l0-2.2c0,0.3,0.3,0.7,0.7,0.9c0.3,0.2,1,0.4,2,0.3c1.2-0.1,2.5,0.1,3.4,0.6\n"
                            + "                          C365,66.2,365.4,66.7,365.4,67.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M364.6,67.3l0,2.2c0-0.5-0.3-0.9-0.9-1.2c-0.7-0.4-1.7-0.6-2.7-0.5c-1.1,0.1-2-0.1-2.7-0.5\n"
                            + "                          c-0.6-0.3-0.9-0.8-0.9-1.3l0-2.2c0,0.5,0.3,0.9,0.9,1.3c0.7,0.4,1.6,0.6,2.7,0.5c1.1-0.1,2,0.1,2.7,0.5\n"
                            + "                          C364.3,66.4,364.6,66.8,364.6,67.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M387.9,69.2c-0.6,0.3-1.5,0.6-2.6,0.2c0-0.7,0-1.5,0-2.2c1.1,0.3,2.1,0.1,2.6-0.2\n"
                            + "                          C387.9,67.8,387.9,68.5,387.9,69.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M365.4,67.3l0,2.2c0,0.1,0,0.3-0.1,0.4c0,0.1,0,0.2,0,0.2l0-2.2c0-0.1,0-0.2,0-0.2\n"
                            + "                          C365.4,67.6,365.4,67.4,365.4,67.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M374.7,70.9c-0.7-0.4-1.7-0.7-2.8-0.6c0-0.7,0-1.5,0-2.2c1,0,2.1,0.2,2.8,0.6c0,0,0,0,0,0\n"
                            + "                          C374.7,69.5,374.7,70.2,374.7,70.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M371.9,70.3c-1,0-2,0.3-2.7,0.7c0-0.7,0-1.5,0-2.2c0.7-0.4,1.7-0.7,2.7-0.7\n"
                            + "                          C371.9,68.8,371.9,69.6,371.9,70.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M381.7,69.4c-1.1,0.3-1.9,1-2,1.7c0-0.7,0-1.5,0-2.2c0.2-0.7,0.9-1.4,2-1.7\n"
                            + "                          C381.7,68,381.7,68.7,381.7,69.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M369.2,71c-0.5,0.3-1.1,0.4-1.5,0.4l0-2.2c0.4,0,1-0.1,1.5-0.4C369.2,69.5,369.2,70.3,369.2,71z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M365.3,70.1l0-2.2c0,0.3,0.2,0.6,0.6,0.9c0.4,0.3,1,0.4,1.7,0.4l0,2.2c-0.6,0-1.2-0.1-1.7-0.4\n"
                            + "                          C365.6,70.8,365.3,70.5,365.3,70.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M375.4,71.6c-0.2-0.2-0.4-0.4-0.7-0.6c0-0.7,0-1.5,0-2.2c0.3,0.2,0.6,0.4,0.7,0.6\n"
                            + "                          C375.4,70.1,375.4,70.8,375.4,71.6z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M375.9,71.9c-0.2-0.1-0.3-0.2-0.4-0.4c0-0.7,0-1.5,0-2.2c0.1,0.1,0.3,0.3,0.4,0.4\n"
                            + "                          C375.9,70.5,375.9,71.2,375.9,71.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M379.6,71.1c-0.1,0.5-0.6,0.9-1.4,1.1c0-0.7,0-1.5,0-2.2c0.8-0.2,1.3-0.5,1.4-1.1c0,0.4,0,0.7,0,1.1\n"
                            + "                          c0,0,0,0.1,0,0.1c0,0.1,0,0.2,0,0.3c0,0.2,0,0.4,0,0.5C379.6,71.1,379.6,71.1,379.6,71.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M388.2,65.5c0.6,0.6,0.2,1.2-0.4,1.5c-0.6,0.3-1.5,0.6-2.6,0.2c-1.1-0.3-2.4-0.4-3.6,0\n"
                            + "                          c-1.1,0.3-1.9,1-2,1.7c-0.1,0.5-0.6,0.9-1.4,1.1c-0.8,0.2-1.7,0.1-2.3-0.3c-0.2-0.1-0.3-0.2-0.4-0.4c-0.2-0.2-0.4-0.4-0.7-0.6\n"
                            + "                          c-0.7-0.4-1.7-0.7-2.8-0.6c-1,0-2,0.3-2.7,0.7c-0.5,0.3-1.1,0.4-1.5,0.4c-0.6,0-1.2-0.1-1.7-0.4c-0.5-0.3-0.7-0.7-0.6-1.1\n"
                            + "                          c0.2-0.7-0.2-1.4-1.1-2c-0.9-0.5-2.2-0.8-3.4-0.6c-1,0.1-1.7-0.2-2-0.3c-0.4-0.3-0.7-0.6-0.7-1c0-0.2,0.1-0.6,0.7-0.9\n"
                            + "                          c0.7-0.4,1.2-1,1.2-1.6c0-0.6-0.4-1.2-1.1-1.6c-0.3-0.2-0.7-0.3-1.1-0.4c-0.2-0.1-0.5-0.2-0.7-0.3c-0.6-0.3-0.8-0.8-0.5-1.3\n"
                            + "                          c0.3-0.5,0.9-0.8,1.8-0.8c1.3-0.1,2.3-0.5,2.9-1.2c0.6-0.7,0.5-1.4-0.1-2.1c-0.6-0.6-0.2-1.2,0.4-1.5c0.6-0.3,1.5-0.6,2.6-0.2\n"
                            + "                          c1.1,0.3,2.5,0.4,3.6,0c1.1-0.3,1.9-1,2-1.7c0.1-0.5,0.6-0.9,1.5-1.1c0.8-0.2,1.7-0.1,2.3,0.3c0.2,0.1,0.3,0.2,0.4,0.4\n"
                            + "                          c0.2,0.2,0.4,0.4,0.7,0.6c0.7,0.4,1.7,0.7,2.8,0.6c1,0,2-0.3,2.7-0.7c0.5-0.3,1.1-0.4,1.6-0.4c0.6,0,1.2,0.1,1.7,0.4\n"
                            + "                          c0.5,0.3,0.7,0.7,0.6,1.1c-0.2,0.7,0.2,1.4,1.1,2c0.9,0.5,2.2,0.7,3.4,0.6c1-0.1,1.7,0.2,2,0.3c0.4,0.3,0.7,0.6,0.7,1\n"
                            + "                          c0,0.2-0.1,0.6-0.7,0.9c-0.7,0.4-1.2,1-1.2,1.6c0,0.6,0.4,1.2,1.1,1.6c0.3,0.2,0.7,0.3,1.1,0.4c0.3,0.1,0.5,0.2,0.7,0.3\n"
                            + "                          c0.6,0.3,0.8,0.9,0.5,1.3c-0.3,0.5-0.9,0.8-1.8,0.8c-1.3,0.1-2.3,0.5-2.9,1.2C387.6,64.1,387.6,64.9,388.2,65.5z M373.2,65.7\n"
                            + "                          c5.8,0.5,11.3-1.8,12.1-5.2c0.5-1.9-0.6-3.9-3-5.3c-1.6-0.9-3.7-1.5-5.9-1.7c-5.8-0.5-11.3,1.8-12.1,5.2\n"
                            + "                          c-0.5,1.9,0.6,3.9,3,5.3C368.9,64.9,370.9,65.5,373.2,65.7\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st22\" d=\"M378.2,72.2c-0.8,0.2-1.7,0.1-2.3-0.3c0-0.7,0-1.5,0-2.2c0.6,0.3,1.5,0.4,2.3,0.3\n"
                            + "                          C378.2,70.7,378.2,71.5,378.2,72.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M389.3,66.2l0,2.2c0,1.2-2.3,2.2-4.5,1.5c-1.9-0.6-4.2,0.1-4.4,1.3c-0.3,1.5-3.5,2-5.1,1\n"
                            + "                          c-0.2-0.1-0.4-0.3-0.6-0.5c-0.2-0.2-0.4-0.3-0.6-0.5c-1.2-0.7-3.1-0.7-4.3,0c-1.3,0.8-3.3,0.7-4.4,0c-0.5-0.3-0.9-0.7-0.9-1.2\n"
                            + "                          l0-2.2c0,0.5,0.3,0.9,0.9,1.2c1.1,0.6,3.1,0.8,4.4,0c1.2-0.7,3.2-0.7,4.3,0c0.2,0.1,0.4,0.3,0.6,0.5c0.2,0.2,0.4,0.4,0.6,0.5\n"
                            + "                          c1.7,1,4.8,0.5,5.1-1c0.3-1.2,2.5-1.9,4.4-1.3C387,68.4,389.3,67.4,389.3,66.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st21\" d=\"M388.9,65.3c1.5,1.5-1.4,3.2-4.1,2.4c-1.9-0.6-4.2,0.1-4.4,1.3c-0.3,1.5-3.5,2-5.1,1\n"
                            + "                          c-0.2-0.1-0.4-0.3-0.6-0.5c-0.2-0.2-0.4-0.3-0.6-0.5c-1.2-0.7-3.1-0.7-4.3,0c-1.3,0.8-3.3,0.7-4.4,0c-0.6-0.4-1-0.9-0.8-1.5\n"
                            + "                          c0.2-0.6-0.2-1.2-0.8-1.5c-0.7-0.4-1.7-0.6-2.7-0.5c-1.1,0.1-2-0.1-2.7-0.5c-1.1-0.6-1.4-1.8,0-2.5c1.3-0.7,1.2-1.8,0-2.5\n"
                            + "                          c-0.2-0.1-0.5-0.3-0.9-0.4c-0.4-0.1-0.7-0.2-0.9-0.4c-1.6-1-0.9-2.8,1.8-3c2.1-0.2,3.3-1.5,2.2-2.6c-1.5-1.5,1.4-3.2,4.1-2.4\n"
                            + "                          c1.9,0.6,4.2-0.1,4.4-1.3c0.3-1.5,3.5-2,5.1-1c0.2,0.1,0.4,0.3,0.6,0.5c0.2,0.2,0.4,0.3,0.6,0.5c1.2,0.7,3.1,0.7,4.3,0\n"
                            + "                          c1.3-0.8,3.3-0.7,4.4,0c0.6,0.4,1,0.9,0.8,1.5c-0.2,0.6,0.2,1.2,0.8,1.5c0.7,0.4,1.7,0.6,2.7,0.5c1.1-0.1,2,0.1,2.7,0.5\n"
                            + "                          c1.1,0.6,1.4,1.8,0,2.5c-1.3,0.7-1.2,1.8,0,2.5c0.2,0.1,0.5,0.3,0.9,0.4c0.4,0.1,0.6,0.2,0.9,0.4c1.6,1,0.9,2.8-1.7,3\n"
                            + "                          C389,62.9,387.9,64.2,388.9,65.3z M385.2,67.3c1.1,0.3,2.1,0.1,2.6-0.2c0.6-0.3,1-0.9,0.4-1.5c-0.6-0.6-0.6-1.4-0.1-2.1\n"
                            + "                          c0.6-0.7,1.6-1.1,2.9-1.2c0.9-0.1,1.5-0.4,1.8-0.8c0.3-0.5,0.1-1-0.5-1.3c-0.2-0.1-0.4-0.2-0.7-0.3c-0.4-0.1-0.8-0.3-1.1-0.4\n"
                            + "                          c-0.7-0.4-1.1-1-1.1-1.6c0-0.6,0.4-1.2,1.2-1.6c0.6-0.3,0.7-0.7,0.7-0.9c0-0.4-0.3-0.7-0.7-1c-0.3-0.2-1-0.4-2-0.3\n"
                            + "                          c-1.2,0.1-2.5-0.1-3.4-0.6c-0.9-0.5-1.3-1.2-1.1-2c0.1-0.4-0.1-0.8-0.6-1.1c-0.4-0.3-1.1-0.4-1.7-0.4c-0.4,0-1,0.1-1.6,0.4\n"
                            + "                          c-0.7,0.4-1.7,0.7-2.7,0.7c-1,0-2.1-0.2-2.8-0.6c-0.3-0.2-0.5-0.4-0.7-0.6c-0.1-0.1-0.3-0.3-0.4-0.4c-0.6-0.3-1.5-0.4-2.3-0.3\n"
                            + "                          c-0.8,0.2-1.3,0.5-1.5,1.1c-0.2,0.7-0.9,1.4-2,1.7c-1.1,0.3-2.5,0.3-3.6,0c-1.1-0.3-2.1-0.1-2.6,0.2c-0.6,0.3-0.9,0.9-0.4,1.5\n"
                            + "                          c0.6,0.6,0.7,1.4,0.1,2.1c-0.6,0.7-1.6,1.1-2.9,1.2c-0.9,0.1-1.5,0.4-1.8,0.8c-0.3,0.5-0.1,1,0.5,1.3c0.2,0.1,0.4,0.2,0.7,0.3\n"
                            + "                          c0.4,0.1,0.8,0.3,1.1,0.4c0.7,0.4,1.1,1,1.1,1.6c0,0.6-0.4,1.2-1.2,1.6c-0.6,0.3-0.7,0.7-0.7,0.9c0,0.4,0.2,0.7,0.7,1\n"
                            + "                          c0.3,0.2,1,0.4,2,0.3c1.2-0.1,2.5,0.1,3.4,0.6c0.9,0.5,1.3,1.2,1.1,2c-0.1,0.4,0.1,0.8,0.6,1.1c0.4,0.3,1,0.4,1.7,0.4\n"
                            + "                          c0.4,0,1-0.1,1.5-0.4c0.7-0.4,1.7-0.7,2.7-0.7c1,0,2.1,0.2,2.8,0.6c0.3,0.2,0.6,0.4,0.7,0.6c0.1,0.1,0.3,0.3,0.4,0.4\n"
                            + "                          c0.6,0.3,1.5,0.4,2.3,0.3c0.8-0.2,1.3-0.5,1.4-1.1c0.2-0.7,0.9-1.4,2-1.7C382.8,66.9,384.1,66.9,385.2,67.3\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <path id=\"line\" class=\"st23\" d=\"M81.5,144.5c0,0,0,78.3,0,117.6c10.7,10.5,21.4,21,32.1,31.5l180.4,106\n"
                            + "                          c27.5-15.7,55.1-31.5,82.6-47.2c3.5-2,3.6-7.1,0.1-9.2c-13.1-7.9-26.1-15.8-39.2-23.7c-1.9-1.2-3.1-3.2-3.1-5.5l0.7-65.4\n"
                            + "                          c51.3-30.6,102.7-61.2,154-91.8c-0.2-29.9-0.3-59.8-0.5-89.6c-8.8-5.1-11-6.4-19.8-11.4c-51.2,29.2-102.4,58.4-153.6,87.7\n"
                            + "                          c-0.8,0.5-1.8,0.5-2.7,0c-11.5-6.3-23-12.5-34.6-18.8c-0.8-0.4-1.7,0.1-1.7,1c0,4.5,0,9.1-0.1,13.6\"></path>\n"
                            + "\n"
                            + "                    <circle id=\"point\" class=\"st24 point\" cx=\"81.5\" cy=\"144.1\" r=\"2.7\"></circle>\n"
                            + "\n"
                            + "                    <g id=\"cloude\">\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_11_\" gradientUnits=\"userSpaceOnUse\" x1=\"73.2148\" y1=\"86.8921\" x2=\"104.9321\" y2=\"86.8921\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st25\" d=\"M74.7,99.1c-0.9,0-1.5-0.8-1.5-2.1c0-2.2,1.5-4.8,3.4-5.9c0.5-0.3,1-0.4,1.4-0.4c0.1,0,0.3,0,0.4,0l0.7,0.2\n"
                            + "                          L79,90.3c0-0.4-0.1-0.7-0.1-1.1c0-4.7,3.3-10.4,7.3-12.7c1.1-0.6,2.2-1,3.1-1c1.3,0,2.3,0.6,3,1.7l0.4,0.6l0.5-0.6\n"
                            + "                          c0.7-0.9,1.5-1.6,2.3-2c0.7-0.4,1.4-0.6,2-0.6c1.4,0,2.3,1.1,2.3,3l0,1.3l0.8-1c0.5-0.5,1-1,1.5-1.3c0.5-0.3,0.9-0.4,1.3-0.4\n"
                            + "                          c0.9,0,1.4,0.7,1.4,2c0,2.1-1.5,4.6-3.2,5.6L76.1,98.7C75.6,99,75.1,99.1,74.7,99.1C74.7,99.1,74.7,99.1,74.7,99.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st26\" d=\"M97.5,75.2c1.6,0,1.8,1.8,1.8,2.5l0.1,2.6l1.7-2c0.4-0.5,0.9-0.9,1.3-1.2c0.4-0.2,0.8-0.3,1.1-0.3\n"
                            + "                          c0.8,0,0.9,0.9,0.9,1.5c0,1.9-1.4,4.2-3,5.2L75.8,98.3c-0.4,0.2-0.8,0.4-1.1,0.4c-0.9,0-1-1.1-1-1.6c0-2,1.4-4.4,3.1-5.4\n"
                            + "                          c0.4-0.2,0.8-0.4,1.1-0.4c0.1,0,0.2,0,0.3,0l1.4,0.4l-0.2-1.5c0-0.4-0.1-0.7-0.1-1c0-4.5,3.2-10,7.1-12.3c1-0.6,2-0.9,2.9-0.9\n"
                            + "                          c1.1,0,2,0.5,2.5,1.5l0.7,1.3l0.9-1.1c0.7-0.8,1.4-1.5,2.2-1.9C96.4,75.4,97,75.2,97.5,75.2 M97.5,74.2c-0.7,0-1.5,0.2-2.3,0.7\n"
                            + "                          c-0.9,0.5-1.7,1.3-2.5,2.1c-0.7-1.3-1.9-2-3.4-2c-1,0-2.2,0.3-3.4,1c-4.2,2.4-7.6,8.3-7.6,13.1c0,0.4,0,0.8,0.1,1.1\n"
                            + "                          c-0.2,0-0.3-0.1-0.5-0.1c-0.5,0-1,0.2-1.6,0.5c-2,1.2-3.6,4-3.6,6.3c0,1.6,0.8,2.6,2,2.6c0.5,0,1-0.2,1.6-0.5l25.6-14.8\n"
                            + "                          c1.9-1.1,3.5-3.8,3.5-6c0-1.6-0.8-2.5-1.9-2.5c-0.5,0-1,0.2-1.6,0.5c-0.6,0.3-1.1,0.8-1.6,1.4C100.3,75.4,99.2,74.2,97.5,74.2\n"
                            + "                          L97.5,74.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_12_\" gradientUnits=\"userSpaceOnUse\" x1=\"29.3286\" y1=\"140.1426\" x2=\"125.4302\" y2=\"140.1426\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#D9EEFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#C5E5FF\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st27\" d=\"M34.8,177.4c-3.4,0-5.5-2.8-5.5-7.2c0-6.7,4.7-14.9,10.5-18.2c1.6-0.9,3.1-1.4,4.5-1.4c0.5,0,1,0.1,1.4,0.2\n"
                            + "                          l0.7,0.2l-0.1-0.7c-0.2-1.2-0.2-2.3-0.2-3.3c0-14.2,10-31.5,22.3-38.5c3.4-2,6.7-3,9.8-3c4.2,0,7.6,2,9.7,5.6l0.4,0.6l0.5-0.6\n"
                            + "                          c2.2-2.7,4.7-4.8,7.2-6.3c2.3-1.3,4.5-2,6.5-2c4.7,0,7.8,3.8,7.9,9.9l0,1.3l0.8-1c1.4-1.7,3-3,4.6-4c1.5-0.9,3-1.3,4.4-1.3\n"
                            + "                          c3.2,0,5.2,2.6,5.2,6.9c0,6.4-4.5,14.3-10.1,17.5l-76,43.9C37.7,177,36.2,177.4,34.8,177.4L34.8,177.4z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st26\" d=\"M102.4,103.4c4.4,0,7.3,3.6,7.4,9.4l0.1,2.6l1.7-2c1.4-1.7,2.9-3,4.5-3.9c1.5-0.8,2.9-1.3,4.1-1.3\n"
                            + "                          c2.9,0,4.7,2.4,4.7,6.4c0,6.3-4.4,13.9-9.9,17.1l-76,43.9c-1.5,0.9-3,1.3-4.3,1.3c-3.1,0-5-2.6-5-6.7c0-6.5,4.6-14.5,10.3-17.8\n"
                            + "                          c1.5-0.9,3-1.3,4.3-1.3c0.4,0,0.9,0.1,1.3,0.2l1.4,0.4l-0.2-1.5c-0.1-1.2-0.2-2.2-0.2-3.2c0-14,9.9-31.1,22-38.1\n"
                            + "                          c3.4-1.9,6.6-2.9,9.5-2.9c4,0,7.2,1.9,9.3,5.4l0.7,1.3l0.9-1.1c2.2-2.6,4.6-4.7,7-6.1C98.3,104,100.5,103.4,102.4,103.4\n"
                            + "                          M102.4,102.4c-2,0-4.3,0.7-6.8,2.1c-2.6,1.5-5.1,3.8-7.3,6.4c-2.2-3.8-5.7-5.9-10.1-5.9c-3,0-6.4,1-10,3.1\n"
                            + "                          c-12.4,7.2-22.5,24.6-22.5,39c0,1.2,0.1,2.3,0.2,3.4c-0.5-0.1-1-0.2-1.5-0.2c-1.4,0-3.1,0.5-4.8,1.5c-5.9,3.4-10.8,11.8-10.8,18.6\n"
                            + "                          c0,4.9,2.4,7.7,6,7.7c1.4,0,3.1-0.5,4.8-1.5l76-43.9c5.7-3.3,10.4-11.3,10.4-17.9c0-4.7-2.3-7.4-5.7-7.4c-1.4,0-3,0.5-4.6,1.4\n"
                            + "                          c-1.7,1-3.3,2.4-4.7,4.1C110.7,106.2,107.3,102.4,102.4,102.4L102.4,102.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"gear_cloude\">\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st5\" d=\"M69.1,134.2c0.2-0.2,0.3-0.5,0.5-0.8c0.6-1.3,0.5-2.2-0.2-2.7c-0.3-0.2-0.6-0.6-0.8-0.9\n"
                            + "                          c-0.1-0.2-0.1-0.6,0-0.8c0.6-1.1,1.3-2.3,1.9-3.4c0.1-0.2,0.3-0.4,0.4-0.5c0.1-0.1,0.2-0.1,0.4-0.1c0.6,0.1,1.3,0.2,1.9,0.4\n"
                            + "                          c0.2,0,0.4,0.1,0.5,0c0.1-0.1,0.2-0.2,0.4-0.4c0.1-0.1,0.2-0.2,0.3-0.3c0.9-0.7,1.1-1.5,1.2-2.4c0-0.6,0.2-1.2,0.3-1.8\n"
                            + "                          c0.1-0.4,0.2-0.8,0.5-1.1c1-0.6,2-1.2,3-1.7c0.2,0.3,0.4,0.5,0.5,0.8c0.2,0.6,0.2,1.2,0.4,1.9c0.1,0.3,0.2,0.4,0.5,0.5\n"
                            + "                          c0.4,0.1,0.7,0,1-0.2c0.4-0.3,0.9-0.7,1.3-1.4c0.3-0.5,0.8-1,1.1-1.6c0.1-0.2,0.3-0.3,0.4-0.4c0.2-0.1,0.3-0.1,0.5,0\n"
                            + "                          c0.6,0.4,1.2,0.7,1.8,1c0.3,0.2,0.3,0.5,0.1,1.1c-0.4,0.9-0.8,1.9-1.2,2.8c-0.2,0.4-0.1,0.6-0.1,0.9c0.2,1.1,1,1.1,1.9,0.6\n"
                            + "                          c0.4-0.2,0.8-0.3,1.1-0.4c0.3,0,0.5,0.1,0.7,0.2c0,1.1,0,2.3,0,3.4c-0.3,0.3-0.5,0.8-0.8,1c-0.7,0.6-1.4,1.1-2.1,1.7\n"
                            + "                          c-0.1,0.1-0.1,0.1-0.2,0.1c-0.3,0.3-0.9,2.4-0.7,2.7c0.4,0.5,0.8,0.9,1.3,1.4c0.3,0.3,0.3,0.7-0.1,1.3c-0.6,0.9-1.1,1.9-1.6,2.8\n"
                            + "                          c-0.2,0.3-0.4,0.6-0.6,0.7c-0.2,0.1-0.3,0.1-0.5,0.1c-0.6-0.2-1.2-0.3-1.8-0.4c-0.1,0-0.2,0-0.4,0.1c-0.1,0-0.2,0.1-0.2,0.2\n"
                            + "                          c-1,0.8-1.6,1.9-1.6,3.1c0,0.5-0.2,1-0.2,1.5c-0.1,0.4-0.2,0.8-0.5,1.1c-1,0.6-1.9,1.1-2.9,1.7c-0.3,0.1-0.5-0.1-0.5-0.4\n"
                            + "                          c-0.1-0.7-0.3-1.4-0.4-2.1c-0.1-0.4-0.3-0.5-0.6-0.5c-0.4,0-0.7,0-1,0.2c-0.5,0.3-0.9,0.8-1.3,1.5c-0.3,0.5-0.7,1-1.1,1.4\n"
                            + "                          c-0.2,0.2-0.3,0.4-0.5,0.5c-0.2,0.1-0.3,0.1-0.5,0c-0.6-0.3-1.2-0.7-1.8-1c-0.3-0.2-0.3-0.5-0.1-0.9c0.4-1,0.9-2,1.3-3\n"
                            + "                          c0.1-0.2,0.1-0.5,0.1-0.8c-0.2-1.2-0.9-1.3-2-0.7c-0.4,0.2-0.8,0.3-1.1,0.4c-0.2,0-0.4-0.1-0.7-0.2c0-1.1,0-2.3,0-3.4\n"
                            + "                          c0.3-0.3,0.5-0.8,0.8-1C67.7,135.4,68.4,134.8,69.1,134.2z M73,134.9c0.1,3,2.3,4.2,4.8,2.7c0,0,0.1,0,0.1-0.1\n"
                            + "                          c2.7-1.6,4.8-5.4,4.8-8.4c0-2.9-2.1-4.3-4.9-2.7C75.1,128,72.9,131.9,73,134.9\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st5\" d=\"M85.9,85.6c0.1-0.1,0.1-0.2,0.2-0.3c0.2-0.5,0.2-0.8-0.1-1c-0.1-0.1-0.2-0.2-0.3-0.3c0-0.1,0-0.2,0-0.3\n"
                            + "                          c0.2-0.4,0.5-0.8,0.7-1.2c0-0.1,0.1-0.1,0.1-0.2c0,0,0.1,0,0.1,0c0.2,0,0.5,0.1,0.7,0.1c0.1,0,0.1,0,0.2,0c0,0,0.1-0.1,0.1-0.1\n"
                            + "                          c0,0,0.1-0.1,0.1-0.1c0.3-0.2,0.4-0.5,0.4-0.9c0-0.2,0.1-0.4,0.1-0.6c0-0.2,0.1-0.3,0.2-0.4c0.4-0.2,0.7-0.4,1.1-0.6\n"
                            + "                          c0.1,0.1,0.1,0.2,0.2,0.3c0.1,0.2,0.1,0.4,0.1,0.7c0,0.1,0.1,0.2,0.2,0.2c0.1,0,0.3,0,0.4-0.1c0.2-0.1,0.3-0.3,0.5-0.5\n"
                            + "                          c0.1-0.2,0.3-0.4,0.4-0.6c0.1-0.1,0.1-0.1,0.1-0.1c0.1,0,0.1,0,0.2,0c0.2,0.1,0.4,0.3,0.7,0.4c0.1,0.1,0.1,0.2,0,0.4\n"
                            + "                          c-0.2,0.3-0.3,0.7-0.4,1c-0.1,0.1-0.1,0.2,0,0.3c0.1,0.4,0.3,0.4,0.7,0.2c0.1-0.1,0.3-0.1,0.4-0.1c0.1,0,0.2,0,0.3,0.1\n"
                            + "                          c0,0.4,0,0.8,0,1.2c-0.1,0.1-0.2,0.3-0.3,0.4c-0.2,0.2-0.5,0.4-0.8,0.6c0,0,0,0-0.1,0.1c-0.1,0.1-0.3,0.9-0.2,1\n"
                            + "                          c0.1,0.2,0.3,0.3,0.5,0.5c0.1,0.1,0.1,0.3,0,0.5c-0.2,0.3-0.4,0.7-0.6,1c-0.1,0.1-0.1,0.2-0.2,0.3c-0.1,0-0.1,0-0.2,0\n"
                            + "                          c-0.2-0.1-0.4-0.1-0.7-0.1c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0.1c-0.4,0.3-0.6,0.7-0.6,1.1c0,0.2-0.1,0.4-0.1,0.5\n"
                            + "                          c0,0.1-0.1,0.3-0.2,0.4c-0.3,0.2-0.7,0.4-1,0.6c-0.1,0-0.2,0-0.2-0.2c0-0.3-0.1-0.5-0.1-0.8c0-0.2-0.1-0.2-0.2-0.2\n"
                            + "                          c-0.1,0-0.2,0-0.4,0.1c-0.2,0.1-0.3,0.3-0.5,0.5c-0.1,0.2-0.3,0.3-0.4,0.5c-0.1,0.1-0.1,0.1-0.2,0.2c-0.1,0-0.1,0-0.2,0\n"
                            + "                          c-0.2-0.1-0.4-0.2-0.7-0.4c-0.1-0.1-0.1-0.2,0-0.3c0.2-0.4,0.3-0.7,0.5-1.1c0-0.1,0-0.2,0-0.3c-0.1-0.4-0.3-0.5-0.7-0.2\n"
                            + "                          c-0.1,0.1-0.3,0.1-0.4,0.1c-0.1,0-0.2,0-0.2-0.1c0-0.4,0-0.8,0-1.2c0.1-0.1,0.2-0.3,0.3-0.4C85.4,86,85.7,85.8,85.9,85.6z\n"
                            + "                          M87.3,85.8c0,1.1,0.8,1.5,1.7,1c0,0,0,0,0,0c1-0.6,1.7-1.9,1.7-3c0-1-0.7-1.5-1.8-1C88.1,83.4,87.3,84.8,87.3,85.8\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st5\" d=\"M97.5,126.6c0.1-0.1,0.1-0.2,0.2-0.3c0.2-0.6,0.2-1-0.1-1.1c-0.1-0.1-0.2-0.2-0.3-0.4c0-0.1,0-0.2,0-0.3\n"
                            + "                          c0.3-0.5,0.5-1,0.8-1.5c0.1-0.1,0.1-0.2,0.2-0.2c0.1,0,0.1,0,0.2,0c0.3,0.1,0.5,0.1,0.8,0.2c0.1,0,0.2,0,0.2,0\n"
                            + "                          c0.1,0,0.1-0.1,0.2-0.2c0-0.1,0.1-0.1,0.1-0.1c0.4-0.3,0.5-0.6,0.5-1c0-0.2,0.1-0.5,0.1-0.8c0-0.2,0.1-0.3,0.2-0.5\n"
                            + "                          c0.4-0.2,0.9-0.5,1.3-0.7c0.1,0.1,0.2,0.2,0.2,0.3c0.1,0.3,0.1,0.5,0.2,0.8c0,0.1,0.1,0.2,0.2,0.2c0.2,0,0.3,0,0.4-0.1\n"
                            + "                          c0.2-0.1,0.4-0.3,0.5-0.6c0.1-0.2,0.3-0.4,0.5-0.7c0.1-0.1,0.1-0.1,0.2-0.2c0.1,0,0.1,0,0.2,0c0.3,0.2,0.5,0.3,0.8,0.5\n"
                            + "                          c0.1,0.1,0.1,0.2,0,0.5c-0.2,0.4-0.3,0.8-0.5,1.2c-0.1,0.2-0.1,0.3,0,0.4c0.1,0.5,0.4,0.5,0.8,0.2c0.2-0.1,0.3-0.1,0.5-0.2\n"
                            + "                          c0.1,0,0.2,0,0.3,0.1c0,0.5,0,1,0,1.5c-0.1,0.1-0.2,0.3-0.3,0.4c-0.3,0.3-0.6,0.5-0.9,0.7c0,0-0.1,0-0.1,0.1\n"
                            + "                          c-0.1,0.1-0.4,1-0.3,1.1c0.2,0.2,0.4,0.4,0.5,0.6c0.1,0.1,0.1,0.3,0,0.6c-0.2,0.4-0.5,0.8-0.7,1.2c-0.1,0.1-0.2,0.3-0.3,0.3\n"
                            + "                          c-0.1,0-0.1,0-0.2,0c-0.3-0.1-0.5-0.1-0.8-0.2c0,0-0.1,0-0.2,0.1c0,0-0.1,0-0.1,0.1c-0.4,0.4-0.7,0.8-0.7,1.3\n"
                            + "                          c0,0.2-0.1,0.4-0.1,0.7c0,0.2-0.1,0.3-0.2,0.5c-0.4,0.2-0.8,0.5-1.2,0.7c-0.1,0-0.2,0-0.2-0.2c0-0.3-0.1-0.6-0.2-0.9\n"
                            + "                          c0-0.2-0.1-0.2-0.3-0.2c-0.2,0-0.3,0-0.4,0.1c-0.2,0.1-0.4,0.3-0.6,0.6c-0.1,0.2-0.3,0.4-0.5,0.6c-0.1,0.1-0.1,0.2-0.2,0.2\n"
                            + "                          c-0.1,0-0.1,0-0.2,0c-0.3-0.2-0.5-0.3-0.8-0.4c-0.1-0.1-0.1-0.2,0-0.4c0.2-0.4,0.4-0.9,0.6-1.3c0-0.1,0-0.2,0-0.3\n"
                            + "                          c-0.1-0.5-0.4-0.6-0.9-0.3c-0.2,0.1-0.3,0.1-0.5,0.2c-0.1,0-0.2,0-0.3-0.1c0-0.5,0-1,0-1.5c0.1-0.1,0.2-0.3,0.3-0.4\n"
                            + "                          C96.9,127.1,97.2,126.9,97.5,126.6z M99.2,126.9c0,1.3,1,1.8,2.1,1.2c0,0,0,0,0,0c1.1-0.7,2.1-2.3,2-3.6c0-1.2-0.9-1.8-2.1-1.1\n"
                            + "                          C100.1,124,99.2,125.7,99.2,126.9\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"phone\">\n"
                            + "                    <linearGradient id=\"SVGID_13_\" gradientUnits=\"userSpaceOnUse\" x1=\"226.5039\" y1=\"228.8974\" x2=\"314.8386\" y2=\"228.8974\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#FFFFFF\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#0071BC\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st28\" d=\"M302.4,343.3L241.2,308c-6.9-4-12.5-14.7-12.5-23.8V121.4c0-4.7-3.8-5.7-1.5-7.4c0.1-0.1,5.5-2.5,5.6-2.5\n"
                            + "                          c2.5-1,5.2-1.4,8.4,0.5l61.1,35.3c6.9,4,12.5,14.7,12.5,23.8v162.7c0,4.9-1.6,8.4-4.1,10c-0.1,0.1-6.6,3.2-6.8,3.2\n"
                            + "                          C301.7,347.8,305.4,345,302.4,343.3z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st29\" d=\"M297.6,345.4l-61.1-35.3c-6.9-4-12.5-14.7-12.5-23.8V123.6c0-9.2,5.6-13.4,12.5-9.4l61.1,35.3\n"
                            + "                          c6.9,4,12.5,14.7,12.5,23.8V336C310.1,345.2,304.5,349.4,297.6,345.4z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st30\" d=\"M296.5,346l-61.1-35.3c-6.9-4-12.5-14.7-12.5-23.8V124.2c0-9.2,5.6-13.4,12.5-9.4l61.1,35.3\n"
                            + "                          c6.9,4,12.5,14.7,12.5,23.8v162.7C309,345.8,303.4,350,296.5,346z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st31\" d=\"M224.6,288V125c0-8.1,4.9-11.8,10.9-8.3l60.8,35.1c6,3.5,10.9,12.8,10.9,20.9v162.9c0,8.1-4.9,11.8-10.9,8.3\n"
                            + "                          l-60.8-35.1C229.5,305.4,224.6,296,224.6,288z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st32\" d=\"M227.1,289V126.9c0-6.7,4.1-9.8,9.2-6.9l59.5,34.3c5.1,2.9,9.2,10.8,9.2,17.5v162.1c0,6.7-4.1,9.8-9.2,6.9\n"
                            + "                          l-59.5-34.3C231.2,303.6,227.1,295.7,227.1,289z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st33\" d=\"M288,151.2c0,3.4-2.4,4.8-5.3,3L249.3,135c-3-1.7-5.4-5.8-5.4-9.2l-7.1-4.1c-4.8-2.8-8.6-0.5-8.6,4.9v163.9\n"
                            + "                          c0,5.5,3.9,12.1,8.6,14.9L295,339c4.8,2.8,8.6,0.5,8.6-4.9V170.2c0-5.5-3.9-12.1-8.6-14.9L288,151.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g id=\"message\">\n"
                            + "                    <linearGradient id=\"SVGID_14_\" gradientUnits=\"userSpaceOnUse\" x1=\"246.7357\" y1=\"211.8007\" x2=\"282.6808\" y2=\"211.8007\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#BDCCD4;stop-opacity:0.5\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.4079\" style=\"stop-color:#84BCE3;stop-opacity:0.4592\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8047\" style=\"stop-color:#52AEF0;stop-opacity:0.4195\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#3FA9F5;stop-opacity:0.4\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st34\" d=\"M250.4,218.2l27.1,15.6c2.3,1.3,5.2-0.3,5.2-3v-20c0-2.1-1.1-4-2.9-5l-27.7-16c-2.4-1.4-5.3,0.3-5.3,3.1v19\n"
                            + "                          C246.7,214.5,248.1,216.9,250.4,218.2z\"></path>\n"
                            + "\n"
                            + "                    <linearGradient id=\"SVGID_15_\" gradientUnits=\"userSpaceOnUse\" x1=\"249.1169\" y1=\"202.6583\" x2=\"279.8028\" y2=\"202.6583\" gradientTransform=\"matrix(1 -8.397516e-03 8.397516e-03 1 -1.8653 2.1274)\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#0071BC;stop-opacity:0.2\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#0071BC;stop-opacity:0.5\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st35\" d=\"M263.1,214.8c5.3-1.9,10.5-3.8,15.8-5.7c0.9-0.3,1.1-1.6,0.2-2.1l-28.5-16.4c-1-0.6-2.2,0.6-1.6,1.6\n"
                            + "                          c4.2,7.4,8.4,14.7,12.7,22.1C262,214.7,262.6,215,263.1,214.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g id=\"server\">\n"
                            + "                    <path class=\"st36\" d=\"M105,310.1L78.9,295c-1.6-0.9-1.4-2.5,0.4-3.6l25.2-14.6c1.9-1.1,4.7-1.2,6.3-0.3l26.1,15.1\n"
                            + "                          c1.6,0.9,1.4,2.5-0.4,3.6l-25.2,14.6C109.4,310.9,106.6,311,105,310.1z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st37\" d=\"M145.6,280.1L145.6,280.1v-11.3l-25.3,0l-8.7-5.1c-2-1.2-5.5-1-7.8,0.3l-8.2,4.7l-25.3,0v11.6\n"
                            + "                          c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9c2,1.2,5.5,1,7.8-0.3l31.6-18.2C145.1,282.1,145.7,281.1,145.6,280.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st37\" d=\"M107.9,263c-1.5,0-3,0.4-4.2,1.1l-8.2,4.7l-25.3,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9\n"
                            + "                          c1,0.6,2.3,0.8,3.6,0.8V263z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M73.1,268.8l-2.9,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l1.5,0.9V268.8z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M104.8,301.6c1.7,0.7,4.2,0.7,6.3-0.1v-13.2h-6.3V301.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M104.3,289.8l-32.7-18.9c-2-1.2-1.8-3.2,0.6-4.5l31.6-18.2c2.3-1.3,5.8-1.5,7.8-0.3l32.7,18.9\n"
                            + "                          c2,1.2,1.8,3.2-0.6,4.5l-31.6,18.2C109.8,290.8,106.3,291,104.3,289.8z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M104.9,286.4l-27.4-15.8c-1.7-1-1.5-2.7,0.5-3.8l26.5-15.3c1.9-1.1,4.9-1.2,6.6-0.3l27.4,15.8\n"
                            + "                          c1.7,1,1.5,2.7-0.5,3.8l-26.5,15.3C109.5,287.3,106.6,287.4,104.9,286.4z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7 lightserver \" d=\"M133.6,283.1c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C133,281.9,133.6,282.3,133.6,283.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M137.8,280.7c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C137.3,279.5,137.8,279.8,137.8,280.7z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M142.1,278.2c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C141.5,277,142.1,277.4,142.1,278.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st37\" d=\"M145.6,262.1L145.6,262.1v-11.3l-25.3,0l-8.7-5.1c-2-1.2-5.5-1-7.8,0.3l-8.2,4.7l-25.3,0v11.6\n"
                            + "                          c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9c2,1.2,5.5,1,7.8-0.3l31.6-18.2C145.1,264,145.7,263,145.6,262.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st37\" d=\"M107.9,245c-1.5,0-3,0.4-4.2,1.1l-8.2,4.7l-25.3,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9\n"
                            + "                          c1,0.6,2.3,0.8,3.6,0.8V245z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M73.1,250.8l-2.9,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l1.5,0.9V250.8z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M104.8,283.6c1.7,0.7,4.2,0.7,6.3-0.1v-13.2h-6.3V283.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M104.3,271.8l-32.7-18.9c-2-1.2-1.8-3.2,0.6-4.5l31.6-18.2c2.3-1.3,5.8-1.5,7.8-0.3l32.7,18.9\n"
                            + "                          c2,1.2,1.8,3.2-0.6,4.5l-31.6,18.2C109.8,272.8,106.3,272.9,104.3,271.8z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M104.9,268.4l-27.4-15.8c-1.7-1-1.5-2.7,0.5-3.8l26.5-15.3c1.9-1.1,4.9-1.2,6.6-0.3l27.4,15.8\n"
                            + "                          c1.7,1,1.5,2.7-0.5,3.8l-26.5,15.3C109.5,269.2,106.6,269.4,104.9,268.4z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M133.6,265.1c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C133,263.9,133.6,264.2,133.6,265.1z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M137.8,262.6c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C137.3,261.4,137.8,261.8,137.8,262.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M142.1,260.2c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C141.5,259,142.1,259.3,142.1,260.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st37\" d=\"M145.6,244L145.6,244v-11.3l-25.3,0l-8.7-5.1c-2-1.2-5.5-1-7.8,0.3l-8.2,4.7l-25.3,0v11.6\n"
                            + "                          c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9c2,1.2,5.5,1,7.8-0.3l31.6-18.2C145.1,246,145.7,245,145.6,244z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st37\" d=\"M107.9,226.9c-1.5,0-3,0.4-4.2,1.1l-8.2,4.7l-25.3,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l32.7,18.9\n"
                            + "                          c1,0.6,2.3,0.8,3.6,0.8V226.9z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M73.1,232.7l-2.9,0v11.6c-0.1,0.8,0.4,1.5,1.3,2.1l1.5,0.9V232.7z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st38\" d=\"M104.8,265.6c1.7,0.7,4.2,0.7,6.3-0.1v-13.2h-6.3V265.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M104.3,253.7l-32.7-18.9c-2-1.2-1.8-3.2,0.6-4.5l31.6-18.2c2.3-1.3,5.8-1.5,7.8-0.3l32.7,18.9\n"
                            + "                          c2,1.2,1.8,3.2-0.6,4.5l-31.6,18.2C109.8,254.8,106.3,254.9,104.3,253.7z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st5\" d=\"M143.7,233l-31.6,18.2c-2.3,1.3-5.8,1.5-7.8,0.3l-32.7-18.9c-0.4-0.3-0.8-0.6-1-0.9c-0.7,1.1-0.4,2.3,1,3.1\n"
                            + "                          l32.7,18.9c2,1.2,5.5,1,7.8-0.3l31.6-18.2c1.8-1,2.3-2.5,1.6-3.6C145,232.1,144.4,232.5,143.7,233z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M133.6,247c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3S133.6,246.2,133.6,247z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M137.8,244.6c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C137.3,243.4,137.8,243.7,137.8,244.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7 lightserver\" d=\"M142.1,242.1c0,0.8-0.6,1.9-1.3,2.3c-0.7,0.4-1.3,0.1-1.3-0.8c0-0.8,0.6-1.9,1.3-2.3\n"
                            + "                          C141.5,240.9,142.1,241.3,142.1,242.1z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st38\" d=\"M106.2,242l-15.3-8.8c-0.9-0.5-0.8-1.5,0.3-2.1l14.8-8.5c1.1-0.6,2.7-0.7,3.7-0.2l15.3,8.8\n"
                            + "                          c0.9,0.5,0.8,1.5-0.3,2.1l-14.8,8.5C108.8,242.5,107.2,242.6,106.2,242z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M107.7,234.6l-2.1-1.2c-0.1-0.1-0.1-0.2,0-0.3l2-1.2c0.2-0.1,0.4-0.1,0.5,0l2.1,1.2c0.1,0.1,0.1,0.2,0,0.3\n"
                            + "                          l-2,1.2C108,234.7,107.8,234.7,107.7,234.6z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st37\" d=\"M90.9,233.2L90.9,233.2c0.1,0,0.2-0.1,0.3-0.1l14.8-8.5c1.1-0.6,2.7-0.7,3.7-0.2l15.3,8.8\n"
                            + "                          c0.9-0.6,0.9-1.5,0-2l-15.3-8.8c-0.9-0.5-2.6-0.5-3.7,0.2l-14.8,8.5C90.1,231.7,90,232.7,90.9,233.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7\" d=\"M102.2,257c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C101.8,255.8,102.2,256.5,102.2,257z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,255.5c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,254.2,99.4,254.9,99.4,255.5z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M102.2,260.4c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5s0.4-0.8,0.9-0.5\n"
                            + "                          C101.8,259.1,102.2,259.8,102.2,260.4z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,258.8c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,257.5,99.4,258.2,99.4,258.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7\" d=\"M102.2,275c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5s0.4-0.8,0.9-0.5C101.8,273.7,102.2,274.4,102.2,275\n"
                            + "                          z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,273.4c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,272.2,99.4,272.8,99.4,273.4z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M102.2,278.3c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C101.8,277.1,102.2,277.8,102.2,278.3z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,276.7c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,275.5,99.4,276.2,99.4,276.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st7\" d=\"M102.2,292.9c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C101.8,291.7,102.2,292.4,102.2,292.9z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,291.4c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,290.1,99.4,290.8,99.4,291.4z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M102.2,296.3c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5s0.4-0.8,0.9-0.5\n"
                            + "                          C101.8,295,102.2,295.7,102.2,296.3z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st7\" d=\"M99.4,294.7c0,0.6-0.4,0.8-0.9,0.5c-0.5-0.3-0.9-1-0.9-1.5c0-0.6,0.4-0.8,0.9-0.5\n"
                            + "                          C99,293.4,99.4,294.1,99.4,294.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"data\">\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_16_\" gradientUnits=\"userSpaceOnUse\" x1=\"479.5204\" y1=\"400.2741\" x2=\"524.5748\" y2=\"400.2741\" gradientTransform=\"matrix(1 0 0 1 -205.3628 0)\">\n"
                            + "                    <stop offset=\"0.1301\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.6525\" style=\"stop-color:#1E2630\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#232D39\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st39\" d=\"M319.2,389.7v8.5c0,0.5,0,0.9-0.2,1.4c-1.2,6.4-10.8,11.3-22.4,11.3c-2.5,0-4.8-0.2-7-0.6\n"
                            + "                          c-8.2-1.5-14.3-5.6-15.3-10.6c-0.1-0.5-0.2-0.9-0.2-1.4l0-4.5v-4h5.5l10,0l24,0H319.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <path class=\"st21\" d=\"M319.2,389.7c0,1.1-0.3,2.2-0.8,3.3c-1.4,3.1-4.8,5.7-9.4,7.4c-3.6,1.3-7.8,2.1-12.4,2.1\n"
                            + "                          c-2.5,0-4.8-0.2-7-0.6c-1.9-0.3-3.7-0.8-5.3-1.5c-4.6-1.7-8-4.3-9.4-7.4c-0.5-1-0.8-2.1-0.8-3.2c0-5.7,6.5-10.4,15.5-12.1\n"
                            + "                          c2.2-0.4,4.6-0.6,7-0.6C309.1,377,319.2,382.7,319.2,389.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_17_\" gradientUnits=\"userSpaceOnUse\" x1=\"479.5204\" y1=\"388.2741\" x2=\"524.5748\" y2=\"388.2741\" gradientTransform=\"matrix(1 0 0 1 -205.3628 0)\">\n"
                            + "                    <stop offset=\"0.1301\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.6525\" style=\"stop-color:#1E2630\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#232D39\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st40\" d=\"M319.2,377.7v8.5c0,0.5,0,0.9-0.2,1.4c-1.2,6.4-10.8,11.3-22.4,11.3c-2.5,0-4.8-0.2-7-0.6\n"
                            + "                          c-8.2-1.5-14.3-5.6-15.3-10.6c-0.1-0.5-0.2-0.9-0.2-1.4l0-4.5v-4h5.5l10,0l24,0H319.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <path class=\"st21\" d=\"M319.2,377.7c0,1.1-0.3,2.2-0.8,3.3c-1.4,3.1-4.8,5.7-9.4,7.4c-3.6,1.3-7.8,2.1-12.4,2.1\n"
                            + "                          c-2.5,0-4.8-0.2-7-0.6c-1.9-0.3-3.7-0.8-5.3-1.5c-4.6-1.7-8-4.3-9.4-7.4c-0.5-1-0.8-2.1-0.8-3.2c0-5.7,6.5-10.4,15.5-12.1\n"
                            + "                          c2.2-0.4,4.6-0.6,7-0.6C309.1,365,319.2,370.7,319.2,377.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"SVGID_18_\" gradientUnits=\"userSpaceOnUse\" x1=\"479.5204\" y1=\"376.2741\" x2=\"524.5748\" y2=\"376.2741\" gradientTransform=\"matrix(1 0 0 1 -205.3628 0)\">\n"
                            + "                    <stop offset=\"0.1301\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.6525\" style=\"stop-color:#1E2630\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#232D39\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path class=\"st41\" d=\"M319.2,365.7v8.5c0,0.5,0,0.9-0.2,1.4c-1.2,6.4-10.8,11.3-22.4,11.3c-2.5,0-4.8-0.2-7-0.6\n"
                            + "                          c-8.2-1.5-14.3-5.6-15.3-10.6c-0.1-0.5-0.2-0.9-0.2-1.4l0-4.5v-4h5.5l10,0l24,0H319.2z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <path class=\"st21\" d=\"M319.2,365.7c0,1.1-0.3,2.2-0.8,3.3c-1.4,3.1-4.8,5.7-9.4,7.4c-3.6,1.3-7.8,2.1-12.4,2.1\n"
                            + "                          c-2.5,0-4.8-0.2-7-0.6c-1.9-0.3-3.7-0.8-5.3-1.5c-4.6-1.7-8-4.3-9.4-7.4c-0.5-1-0.8-2.1-0.8-3.2c0-5.7,6.5-10.4,15.5-12.1\n"
                            + "                          c2.2-0.4,4.6-0.6,7-0.6C309.1,353,319.2,358.7,319.2,365.7z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"XMLID_10_\" gradientUnits=\"userSpaceOnUse\" x1=\"315.5905\" y1=\"403.3549\" x2=\"317.2893\" y2=\"406.3207\" gradientTransform=\"matrix(-1 0 0 1 624.447 0)\">\n"
                            + "                    <stop offset=\"0.1212\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.1244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5307\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8632\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8699\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path id=\"XMLID_54_\" class=\"st42\" d=\"M307,406.2l0.6,0.4l0.1-0.1c0.4-0.1,1-0.5,1.3-1.1c0.2-0.4,0.4-0.9,0.4-1.2\n"
                            + "                          c0-0.2,0-0.4-0.1-0.6l0-0.1c0,0-0.6-0.4-0.6-0.4l0,0v0c-0.5-0.2-1.2,0.3-1.7,1.1c-0.2,0.4-0.3,0.7-0.4,1.1\n"
                            + "                          C306.6,405.7,306.7,406.1,307,406.2z\"></path>\n"
                            + "\n"
                            + "                    <ellipse id=\"XMLID_53_\" transform=\"matrix(0.4994 -0.8664 0.8664 0.4994 -196.535 469.9913)\" class=\"st43\" cx=\"308.4\" cy=\"405.1\" rx=\"1.8\" ry=\"1\"></ellipse>\n"
                            + "\n"
                            + "                    <radialGradient id=\"SVGID_19_\" cx=\"228.1148\" cy=\"404.3167\" r=\"1.3438\" fx=\"227.6301\" fy=\"404.3277\" gradientTransform=\"matrix(-1 0 0 1 536.678 0)\" gradientUnits=\"userSpaceOnUse\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#B3FF0E\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#7FB50A\"></stop>\n"
                            + "\n"
                            + "                    </radialGradient>\n"
                            + "                    <path class=\"st44\" d=\"M307.9,406.4c0.3,0,0.8-0.3,1.2-1c0.4-0.8,0.4-1.5,0.1-1.7c-0.1,0-0.1,0-0.2,0c-0.3,0-0.8,0.3-1.2,1\n"
                            + "                          c-0.4,0.8-0.4,1.5-0.1,1.7C307.7,406.4,307.8,406.4,307.9,406.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"XMLID_11_\" gradientUnits=\"userSpaceOnUse\" x1=\"315.5905\" y1=\"391.3549\" x2=\"317.2893\" y2=\"394.3207\" gradientTransform=\"matrix(-1 0 0 1 624.447 0)\">\n"
                            + "                    <stop offset=\"0.1212\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.1244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5307\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8632\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8699\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path id=\"XMLID_2_\" class=\"st45\" d=\"M307,394.2l0.6,0.4l0.1-0.1c0.4-0.1,1-0.5,1.3-1.1c0.2-0.4,0.4-0.9,0.4-1.2\n"
                            + "                          c0-0.2,0-0.4-0.1-0.6l0-0.1c0,0-0.6-0.4-0.6-0.4l0,0v0c-0.5-0.2-1.2,0.3-1.7,1.1c-0.2,0.4-0.3,0.7-0.4,1.1\n"
                            + "                          C306.6,393.7,306.7,394.1,307,394.2z\"></path>\n"
                            + "\n"
                            + "                    <ellipse id=\"XMLID_1_\" transform=\"matrix(0.4994 -0.8664 0.8664 0.4994 -186.1384 463.9839)\" class=\"st43\" cx=\"308.4\" cy=\"393.1\" rx=\"1.8\" ry=\"1\"></ellipse>\n"
                            + "\n"
                            + "                    <radialGradient id=\"SVGID_20_\" cx=\"228.1148\" cy=\"392.3167\" r=\"1.3438\" fx=\"227.6301\" fy=\"392.3277\" gradientTransform=\"matrix(-1 0 0 1 536.678 0)\" gradientUnits=\"userSpaceOnUse\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#B3FF0E\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#7FB50A\"></stop>\n"
                            + "\n"
                            + "                    </radialGradient>\n"
                            + "                    <path class=\"st46\" d=\"M307.9,394.4c0.3,0,0.8-0.3,1.2-1c0.4-0.8,0.4-1.5,0.1-1.7c-0.1,0-0.1,0-0.2,0c-0.3,0-0.8,0.3-1.2,1\n"
                            + "                          c-0.4,0.8-0.4,1.5-0.1,1.7C307.7,394.4,307.8,394.4,307.9,394.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <linearGradient id=\"XMLID_12_\" gradientUnits=\"userSpaceOnUse\" x1=\"315.5905\" y1=\"379.3549\" x2=\"317.2893\" y2=\"382.3207\" gradientTransform=\"matrix(-1 0 0 1 624.447 0)\">\n"
                            + "                    <stop offset=\"0.1212\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.1244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5244\" style=\"stop-color:#202B33\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.5307\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8632\" style=\"stop-color:#161C24\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"0.8699\" style=\"stop-color:#13161A\"></stop>\n"
                            + "\n"
                            + "                    </linearGradient>\n"
                            + "                    <path id=\"XMLID_4_\" class=\"st47\" d=\"M307,382.2l0.6,0.4l0.1-0.1c0.4-0.1,1-0.5,1.3-1.1c0.2-0.4,0.4-0.9,0.4-1.2\n"
                            + "                          c0-0.2,0-0.4-0.1-0.6l0-0.1c0,0-0.6-0.4-0.6-0.4l0,0v0c-0.5-0.2-1.2,0.3-1.7,1.1c-0.2,0.4-0.3,0.7-0.4,1.1\n"
                            + "                          C306.6,381.7,306.7,382.1,307,382.2z\"></path>\n"
                            + "\n"
                            + "                    <ellipse id=\"XMLID_3_\" transform=\"matrix(0.4994 -0.8664 0.8664 0.4994 -175.7418 457.9765)\" class=\"st43\" cx=\"308.4\" cy=\"381.1\" rx=\"1.8\" ry=\"1\"></ellipse>\n"
                            + "\n"
                            + "                    <radialGradient id=\"SVGID_21_\" cx=\"228.1148\" cy=\"380.3167\" r=\"1.3438\" fx=\"227.6301\" fy=\"380.3277\" gradientTransform=\"matrix(-1 0 0 1 536.678 0)\" gradientUnits=\"userSpaceOnUse\">\n"
                            + "                    <stop offset=\"0\" style=\"stop-color:#B3FF0E\"></stop>\n"
                            + "\n"
                            + "                    <stop offset=\"1\" style=\"stop-color:#7FB50A\"></stop>\n"
                            + "\n"
                            + "                    </radialGradient>\n"
                            + "                    <path class=\"st48\" d=\"M307.9,382.4c0.3,0,0.8-0.3,1.2-1c0.4-0.8,0.4-1.5,0.1-1.7c-0.1,0-0.1,0-0.2,0c-0.3,0-0.8,0.3-1.2,1\n"
                            + "                          c-0.4,0.8-0.4,1.5-0.1,1.7C307.7,382.4,307.8,382.4,307.9,382.4z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g>\n"
                            + "                    <g class=\"st10\">\n"
                            + "                    <path class=\"st49\" d=\"M472.1,67.2C472.1,67.2,472.1,67.2,472.1,67.2c-0.1-0.1-0.2-0.1-0.3-0.1c0,0,0,0,0,0c0,0-0.1,0-0.1,0\n"
                            + "                          c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0c0,0,0,0,0,0c-0.1,0-0.2,0-0.3,0c0,0,0,0,0,0c0,0,0,0-0.1,0\n"
                            + "                          c-0.1,0-0.2,0-0.2,0c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0c0,0-0.1,0-0.1,0c-0.1,0-0.1,0-0.2,0c0,0-0.1,0-0.1,0\n"
                            + "                          c0,0-0.1,0-0.1,0c-0.1,0-0.2,0.1-0.3,0.1c0,0-0.1,0-0.1,0c0,0,0,0-0.1,0c-0.2,0.1-0.4,0.2-0.6,0.3l-138.9,80.2l5.7,3.3\n"
                            + "                          l138.9-80.2c0.2-0.1,0.4-0.2,0.6-0.3c0.1,0,0.1-0.1,0.2-0.1c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.1,0,0.2-0.1c0.1,0,0.1,0,0.2,0\n"
                            + "                          c0.1,0,0.2,0,0.3,0c0,0,0.1,0,0.1,0c0.1,0,0.2,0,0.3,0c0,0,0,0,0.1,0c0.1,0,0.2,0,0.4,0c0,0,0.1,0,0.1,0c0.1,0,0.2,0,0.3,0.1\n"
                            + "                          c0,0,0.1,0,0.1,0c0.1,0,0.2,0.1,0.3,0.2L472.1,67.2z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st50\" d=\"M329.8,147.7c-0.3,0.1-0.5,0.3-0.8,0.5c0,0-0.1,0.1-0.1,0.1c-0.2,0.2-0.5,0.4-0.7,0.6c0,0,0,0-0.1,0\n"
                            + "                          c0,0-0.1,0.1-0.1,0.1c-0.1,0.1-0.2,0.2-0.3,0.3c0,0-0.1,0.1-0.1,0.1c0,0-0.1,0.1-0.1,0.1c-0.1,0.1-0.1,0.2-0.2,0.2\n"
                            + "                          c0,0.1-0.1,0.1-0.1,0.1c0,0-0.1,0.1-0.1,0.1c0,0.1-0.1,0.1-0.1,0.2c0,0-0.1,0.1-0.1,0.1c-0.1,0.1-0.1,0.1-0.1,0.2\n"
                            + "                          c0,0-0.1,0.1-0.1,0.1c0,0,0,0,0,0.1c-0.1,0.1-0.2,0.3-0.2,0.4c0,0,0,0,0,0c0,0,0,0.1,0,0.1c-0.1,0.1-0.1,0.2-0.2,0.3\n"
                            + "                          c0,0,0,0.1-0.1,0.1c0,0,0,0.1-0.1,0.1c0,0,0,0.1-0.1,0.1c-0.1,0.1-0.1,0.2-0.2,0.3c0,0,0,0,0,0.1c0,0,0,0,0,0\n"
                            + "                          c-0.1,0.1-0.1,0.3-0.2,0.4c0,0,0,0.1,0,0.1c0,0.1,0,0.1-0.1,0.2c0,0.1-0.1,0.1-0.1,0.2c0,0.1,0,0.1-0.1,0.2c0,0.1,0,0.1-0.1,0.2\n"
                            + "                          c0,0,0,0.1,0,0.1c0,0.1,0,0.1-0.1,0.2c0,0.1,0,0.2-0.1,0.2c0,0,0,0.1,0,0.1c0,0.1,0,0.1,0,0.2c0,0.1,0,0.3-0.1,0.4\n"
                            + "                          c0,0,0,0.1,0,0.1c0,0,0,0.1,0,0.1c0,0.3-0.1,0.6-0.1,0.8l0.3,103.4c0,1.5,0.5,2.6,1.4,3.1l5.7,3.3c-0.9-0.5-1.4-1.6-1.4-3.1\n"
                            + "                          l-0.3-103.4c0-0.3,0-0.6,0.1-0.8c0-0.1,0-0.1,0-0.2c0-0.1,0-0.3,0.1-0.4c0-0.1,0-0.2,0.1-0.3c0-0.1,0-0.2,0.1-0.2\n"
                            + "                          c0-0.1,0.1-0.2,0.1-0.3c0-0.1,0-0.1,0.1-0.2c0-0.1,0.1-0.3,0.1-0.4c0-0.1,0-0.1,0.1-0.2c0.1-0.2,0.1-0.4,0.2-0.5c0,0,0,0,0-0.1\n"
                            + "                          c0.1-0.2,0.1-0.3,0.2-0.5c0,0,0-0.1,0.1-0.1c0.1-0.1,0.2-0.3,0.2-0.4c0,0,0-0.1,0-0.1c0.1-0.2,0.2-0.3,0.3-0.5\n"
                            + "                          c0,0,0.1-0.1,0.1-0.1c0.1-0.1,0.2-0.2,0.2-0.3c0-0.1,0.1-0.1,0.1-0.2c0.1-0.1,0.1-0.2,0.2-0.3c0.1-0.1,0.1-0.2,0.2-0.2\n"
                            + "                          c0.1-0.1,0.1-0.1,0.2-0.2c0.1-0.1,0.2-0.2,0.3-0.3c0.1-0.1,0.1-0.1,0.2-0.2c0.2-0.2,0.4-0.4,0.7-0.6c0,0,0.1-0.1,0.1-0.1\n"
                            + "                          c0.2-0.2,0.5-0.4,0.8-0.5L329.8,147.7z\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st51\" d=\"M474.4,70.9c2.7-1.5,4.8-0.3,4.8,2.8l0.3,103.4c0,3.1-2.1,6.8-4.8,8.3l-138.9,80.2\n"
                            + "                          c-2.7,1.5-4.8,0.3-4.8-2.8l-0.3-103.4c0-3.1,2.1-6.8,4.8-8.3L474.4,70.9z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st52\" d=\"M474.1,73.3c1.1-0.6,1.9-0.1,1.9,1.1l0.3,103.4c0,1.2-0.9,2.7-1.9,3.4l-138.9,80.2\n"
                            + "                          c-1.1,0.6-1.9,0.1-1.9-1.1l-0.3-103.4c0-1.2,0.9-2.7,1.9-3.4L474.1,73.3z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    <g id=\"code\">\n"
                            + "                    <path class=\"st53\" d=\"M341.8,177.9c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M354.4,196.3c14.2-8.1,28.4-16.3,42.5-24.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M354.4,184.3c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M401.3,169.4c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n");
                    html1.append("                    <path class=\"st53\" d=\"M354.3,222.2c14.2-8.1,28.4-16.3,42.5-24.4\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M401.3,195.2c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M366.1,164c9.6-5.5,19.1-11,28.7-16.5\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M354.4,209.4c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M380.3,194.5c4.8-2.7,9.6-5.5,14.3-8.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M341.8,242.6c6.9-4,13.8-8,20.8-11.9\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M366.1,228.7c4.8-2.7,9.6-5.5,14.3-8.2\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M427.5,180.1c7.9-4.6,15.9-9.1,23.8-13.7\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M416.5,149.1c6.7-3.9,13.5-7.7,20.2-11.6\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st53\" d=\"M379.4,170.4c10.7-6.2,21.5-12.3,32.2-18.5\"></path>\n"
                            + "\n"
                            + "                    <g>\n"
                            + "                    <path class=\"st54\" d=\"M352.8,153.2c0,1.6,1,2.4,2.2,1.7c1.2-0.7,2.2-2.6,2.2-4.2c0-1.6-1-2.4-2.2-1.7\n"
                            + "                          C353.8,149.7,352.8,151.6,352.8,153.2z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st55\" d=\"M346.2,157c0,1.6,1,2.4,2.2,1.7c1.2-0.7,2.2-2.6,2.2-4.2c0-1.6-1-2.4-2.2-1.7\n"
                            + "                          C347.2,153.5,346.2,155.4,346.2,157z\"></path>\n"
                            + "\n"
                            + "                    <path class=\"st56\" d=\"M339.6,160.8c0,1.6,1,2.4,2.2,1.7c1.2-0.7,2.2-2.6,2.2-4.2c0-1.6-1-2.4-2.2-1.7\n"
                            + "                          C340.6,157.3,339.6,159.2,339.6,160.8z\"></path>\n"
                            + "\n"
                            + "                    </g>\n"
                            + "                    </g>\n"
                            + "                    </svg>\n"
                            + "                </div>\n");
                    html1.append("                <div id=\"swTop\" class=\"col-sm-6 col-xs-12 object hidden-xs hidden-sm\" style=\"background-color: #fff;margin-top: 150px;\" dir=\"rtl\">");
                    html1.append("<div class='roww' id='level0' style='background-color: #fff;margin-top:10px;'>");
                    html1.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html1.append("</div>");
                    html1.append("<div class='col-lg-6 col-sm-6 col-md-8 col-sm-10 col-xs-12' style='background-color: #fff;'>");
                    html1.append("<input class='form-control' style='text-align: center;border-radius: 5px;' type='text' value='" + jjTools.getSeassionUserNameAndFamily(request) + "' disabled='disabled'/>");
                    html1.append("</div>");
                    html1.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html1.append("</div>");
                    html1.append("</div>");
                    html1.append("<div class='roww' id='level1' style='background-color: #fff;margin-top:10px;'>");
                    html1.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html1.append("</div>");
                    html1.append("<div class='col-lg-6 col-sm-6 col-md-8 col-sm-10 col-xs-12' style='background-color: #fff;'>");
                    html1.append("<input class='form-control' style='text-align: center;border-radius: 5px;' type='text' value='" + jjTools.getSessionAttribute(request, "#USER_MOBILE") + "' disabled='disabled'/>");
                    html1.append("</div>");
                    html1.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html1.append("</div>");
                    html1.append("</div>");
                }
                String categoryProductID = jjTools.getParameter(request, "categoryProductId");
                DefaultTableModel dtm = db.Select(Category_Product.tableName, Category_Product._parent + "=" + categoryProductID);
                List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
                DefaultTableModel dtmGetExplain = db.Select(Category_Product.tableName, Category_Product._id + "=" + categoryProductID);
                List<Map<String, Object>> rowgetExplain = jjDatabase.separateRow(dtmGetExplain);
                if (row.size() > 0) {
                    html.append("<div class='roww' id='level" + row.get(0).get(Category_Product._level) + "' style='background-color: #fff;margin-top:10px;'>");
                    html.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html.append("</div>");
                    html.append("<div class='col-lg-6 col-sm-6 col-md-8 col-sm-10 col-xs-12' style='background-color: #fff;'>");
                    html.append("<select id='selectAcceptOnline" + categoryProductID + "' class='form-control tahoma10' onchange='if(value>1){removeChiled(value);getCategorySelectOption(value);}' style='border-radius: 5px;cursor: pointer;'><option value='1' selected='selected'>" + rowgetExplain.get(0).get(Category_Product._explain) + "</option>");
                    for (int i = 0; i < row.size(); i++) {
                        html.append("<option id='" + row.get(i).get(Category_Product._id) + "'  value = '" + row.get(i).get(Category_Product._level) + row.get(i).get(Category_Product._id) + "' >" + row.get(i).get(Category_Product._title) + "</option>");
                    }
                    html.append("</select>");
                    html.append("</div>");
                    html.append("<div class='col-lg-3 col-sm-3 col-md-2 col-sm-1 col-xs-12' style='background-color: #fff;'>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    List<Map<String, Object>> row2 = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + categoryProductID));
                    List<Map<String, Object>> row3 = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + row2.get(0).get(Category_Product._parent)));
                    for (int j = 0; j < row3.size(); j++) {
                        if (row3.get(j).get(Category_Product._id).equals(categoryProductID)) {

                        } else {
                            script += "$('#" + row3.get(j).get(Category_Product._id) + "').removeAttr('selected');";
                        }
                    }
                    script += "$('#swTop').html($('#swTop').html()+\"" + html.toString() + "\");$('#" + categoryProductID + "').attr('selected','selected');";
                    script += "$('#sw').html('');";
                } else {
                    DefaultTableModel dtm1 = db.Select(Category_Product.tableName, Category_Product._id + "=" + categoryProductID);
                    List<Map<String, Object>> row1 = jjDatabase.separateRow(dtm1);
                    String categoruId = row1.get(0).get(Category_Product._id).toString();
                    System.out.println(categoruId);
                    String getHtml = getListCategoryProduct(categoruId, db);
                    script = ("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
                    script += "$('#sw').html(\"" + getHtml + "\");";
                }
                if (needString) {
                    return html1.toString() + html.toString();
                }
                Server.outPrinter(request, response, script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getList(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {//new in v1.5.0 لیست تمام محصولات را با توجه به زبان انتخاب شده برمیگرداند
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = 1;
            if (needString = true) {
                categoryProductId = Integer.parseInt(jjTools.getAttribute(request, "categoryProductId"));

            } else {
                categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            }
            String categoryID = "";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
            categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
            String queryOR = "";
            if (categoryID.equals("")) {
                queryOR += _category_id + "=" + categoryProductId;
            } else {
                String[] splitCPID = categoryID.split(",");
                for (int i = 0; i < splitCPID.length; i++) {
                    if (i == splitCPID.length - 1) {
                        queryOR += _category_id + "=" + splitCPID[i];
                    } else {
                        queryOR += _category_id + "=" + splitCPID[i] + " OR ";
                    }
                }
            }
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, langSetting.get(0).get(Language._product));
            Integer category_id = new Integer(jjTools.getParameter(request, "id").equals("") ? "0" : jjTools.getParameter(request, "id").toString()); //============ EDITED BY RASHIDI ======== اگر آی دی در پارامترها نبود از اتریبیوت ها میخاند.
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topProductRow;
            if (category_id == 0) {
                topProductRow = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + queryOR, _date + "," + _quantity));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
//                        db.Select(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2"));
            } else {
                //============ BY RASHIDI ========>
                //***************توجه*********************
                //به جای این چند خط کد باید یک کوئری اسکیوال جایگزین شود که کتگوری های مد نظر را بگیرد.---->
                String where = "";

//                List<Map<String, Object>> categoryLang = jjDatabase.separateRow(db.Select(tableName, where));
                List<Map<String, Object>> allCategoryProduct = jjDatabase.separateRow(db.Select(Category_Product.tableName));
//                List<Map<String, Object>> allCategoryProduct = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._lang +" = "+lang));
//                System.out.println("allCategoryProduct : " + allCategoryProduct);
                List<String> categoryList = new ArrayList<>();

                categoryList.add(category_id.toString());
                for (int i = 0; i < categoryList.size(); i++) {
                    for (int j = 0; j < allCategoryProduct.size(); j++) {
//                        if (allCategoryProduct.get(j).get(Category_Product._upperNode).toString().equals(categoryList.get(i)) && allCategoryProduct.get(j).get(Category_Product._lang).toString().equals(lang)) {
                        if (allCategoryProduct.get(j).get(Category_Product._level).toString().equals(categoryList.get(i))) {
                            categoryList.add(allCategoryProduct.get(j).get(Category_Product._id).toString());
                        }
                    }
                }
                System.out.println("categoryList : " + categoryList);
                where = "";
                for (int i = 0; i < categoryList.size() - 1; i++) {
                    where += _category_id + " = " + categoryList.get(i) + " OR ";
                }
                where += _category_id + " = " + categoryList.get(categoryList.size() - 1);

                topProductRow = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._active + "=1" + " AND " + Product._lang + "=" + lang + " AND " + where, _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
//                topProductRow = jjDatabase.separateRow(
//                        db.Select(Product.tableName, Product._lang + "=" + lang + " AND " + where));
                //به جای این چند خط کد باید یک کوئری اسکیوال جایگزین شود که کتگوری های مد نظر را بگیرد.<----
                //<============ BY RASHIDI ========
            }

            //---------------one product post creation
            /*لیست محصولات را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopproductDiv' class='topproductDiv'></div>");
            if (topProductRow.isEmpty()) {
                temphtml.append("<div class='noAnyThing'> " + langSetting.get(0).get(Language._noItem) + " </div>");
            } else {
                for (int i = 0; i < topProductRow.size(); i++) {
                    String id = topProductRow.get(i).get(_id).toString();
                    temphtml.append("<div class='hoverProduct forMe col-sm-12 col-lg-6'>");
//                    temphtml.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(topProductRow.get(i).get(_date).toString()) + "</span>");

                    //============ EDITED BY RASHIDI ========>
                    String src1 = topProductRow.get(i).get(_pic1).toString();
//                    String src2 = topProductRow.get(i).get(_pic2).toString();
//                    String src3 = topProductRow.get(i).get(_pic3).toString();
//                    String src4 = topProductRow.get(i).get(_pic4).toString();
//                    String src5 = topProductRow.get(i).get(_pic5).toString();
//                    String src6 = topProductRow.get(i).get(_pic6).toString();
//                    String src_ext = topProductRow.get(i).get(_pic_ext).toString();

                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src1.matches("upload/p[0-9]{10}.{4}")) {
//                    String smalPicSrc = src1.replace(".", "_small.");//select small pic
                    String smalPicSrc = src1.replace(".", ".");//select small pic
//                    temphtml.append("<div class='pro-img'><img class='prPicDiv' src='upload/" + smalPicSrc + "' onclick='getOneproduct(" + id + ");return false;' alt='" + topProductRow.get(i).get(_name).toString().replace("'", "").replace("\"", "") + "' /></div>");//======EDITED BY RASHIDI ======
                    temphtml.append("<div class='pro-img'><a style='cursor: pointer;' onclick='getOneproduct(" + id + ")'><img class='prPicDiv' src='upload/" + smalPicSrc + "' alt='" + topProductRow.get(i).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a></div>");//======No Ajax By Mohammad ======
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src1 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src2.matches("upload/p[0-9]{10}.{4}")) {
//                      smalPicSrc = src2.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src2 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src3.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src3.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src3 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src4.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src4.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src4 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src5.matches("upload/p[0-9]{10}.{4}")) {
//                        smalPicSrc = src5.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src5 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src6.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src6.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src6 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src_ext.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src_ext.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src_ext + "'/>");
//                    }

                    //<============ EDITED BY RASHIDI ========
//                    temphtml.append("<div class='prTitleSpan'><h4 onclick='getOneproduct(" + id + ");'>" + topProductRow.get(i).get(_name).toString() + "</h4></div>");
//                    temphtml.append("<div class='prTitleSpan'><h4 onclick='getOneproduct(" + id + ");return false;'><a href='Server?do=Product.getOneProduct&id=" + id + "&panel=" + panel + "' >" + topProductRow.get(i).get(_name).toString() + "</a></h4></div>");
                    temphtml.append("<div class='prTitleSpan'><h4><a style='cursor: pointer;' onclick='getOneproduct(" + id + ")'>" + topProductRow.get(i).get(_name).toString() + "</a></h4></div>");
                    //============ BY RASHIDI ========>
                    //======>PRICE
                    if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                        price = topProductRow.get(i).get(_price2).toString();//قیمت اصلی را به کاربر نمایش می دهد
                        discountPrice = "".equals(topProductRow.get(i).get(_discount).toString()) ? "" : topProductRow.get(i).get(_discount).toString();//اگر تخفیف برایش در نظر گرفته شود تخفیف هم نمایش داده می شود.
                    } else {//اگر کاربر لاگین کرده باشد
                        List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                        if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                            String userGroup = userRow.get(0).get(Access_Group_User._group_id).toString();
//                            System.out.println("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup1).toString())) {
                                price = "".equals(topProductRow.get(i).get(_groupPrice1).toString()) ? topProductRow.get(i).get(_price2).toString() : topProductRow.get(i).get(_groupPrice1).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup2).toString())) {
                                price = "".equals(topProductRow.get(i).get(_groupPrice2).toString()) ? topProductRow.get(i).get(_price2).toString() : topProductRow.get(i).get(_groupPrice2).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup3).toString())) {
                                price = "".equals(topProductRow.get(i).get(_groupPrice3).toString()) ? topProductRow.get(i).get(_price2).toString() : topProductRow.get(i).get(_groupPrice3).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup4).toString())) {
                                price = topProductRow.get(i).get(_groupPrice4).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow.get(i).get(_userGroup5).toString())) {
                                price = "".equals(topProductRow.get(i).get(_groupPrice5).toString()) ? topProductRow.get(i).get(_price2).toString() : topProductRow.get(i).get(_groupPrice5).toString();
                            } else {
                                price = topProductRow.get(i).get(_price2).toString();
                            }
                        } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                            price = topProductRow.get(i).get(_price2).toString();
                            discountPrice = topProductRow.get(i).get(_discount).toString() == "" ? "" : topProductRow.get(i).get(_discount).toString();
                        }
                    }
                    ServerLog.Print("PRICE : " + price);
                    ServerLog.Print("DISCOUNTPRICE : " + discountPrice);

                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        temphtml.append("<div class='prPriceDiv'><span>" + price + "</span> <span>" + topProductRow.get(i).get(_currency) + "</span></div>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود

                        //=======> Time of Discount
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(topProductRow.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            temphtml.append("<div class='prPrePrice'><span style='text-decoration: line-through;' >" + price + "</span> <span style='text-decoration: line-through;'>" + topProductRow.get(i).get(_currency) + "</span></div>");
                            temphtml.append("<div class='prDisCountDiv'><span>" + discountPrice + "</span> <span>" + topProductRow.get(i).get(_currency) + "</span></div>");
                            temphtml.append("<div class='prRemTimeSpan'><span>" + langSetting.get(0).get(Language._remainTime) + "</span> : <span>" + remainTime + "</span> <span>" + langSetting.get(0).get(Language._day) + "</span></div>");//==> prRemTimeSpan : productRemainTime
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            int remainHours = 0, remainMinutes = 0;
                            String timeArr[] = topProductRow.get(i).get(_discountTime).toString().split(":");
                            ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                            remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                            remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                            if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                temphtml.append("<div class='prPrePrice'><span style='text-decoration: line-through;' >" + price + "</span> <span style='text-decoration: line-through;'>" + topProductRow.get(i).get(_currency) + "</span></div>");
                                temphtml.append("<div class='prDisCountDiv'><span>" + discountPrice + "</span><span> " + topProductRow.get(i).get(_currency) + "</span></div>");
                                ServerLog.Print("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
                                temphtml.append("<div class='prRemTimeSpan'><span>" + langSetting.get(0).get(Language._remainTime) + "</span> : <span>" + remainHours + "</span> <span>" + langSetting.get(0).get(Language._hour) + "</span> <span>" + remainMinutes + "</span> <span>" + langSetting.get(0).get(Language._minute) + "</span></div>");

                            } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
//                                temphtml.append("<div class='prPriceDiv'><span>" + price + "</span> <span>" + topProductRow.get(i).get(_currency) + "</span></div>");
                            }
                        } else {
//                            temphtml.append("<div class='prPriceDiv'><span>" + price + "</span> <span>" + topProductRow.get(i).get(_currency) + "</span></div>");
                        }

                        //<======= Time of Discount
                    }
                    //<======PRICE

                    temphtml.append("<div class='detailBtn'>");

                    //============ BY RASHIDI ========>
                    int quantity = new Integer(topProductRow.get(i).get(_quantity).toString());
                    if (quantity > 0) {
                        temphtml.append("<a class='minusShopingCart' style='cursor: pointer;' class='coGruopproduct' onclick='addToShoppingCart(" + topProductRow.get(i).get(_id).toString() + ");'><i class='fas fa-minus'></i></a>");
                        temphtml.append("<a class='shopingCart' style='cursor: pointer;' class='coGruopproduct' onclick='addToShoppingCart(" + topProductRow.get(i).get(_id).toString() + ");'><i class='fas fa-cart-plus'></i></a>");
                        temphtml.append("<a class='plusShopingCart' style='cursor: pointer;' class='coGruopproduct' onclick='addToShoppingCart(" + topProductRow.get(i).get(_id).toString() + ");'><i class='fas fa-plus'></i></a>");
//                    temphtml.append("<a href=\"Server?do=Product.getOneProduct&id=" + id + "&panel=" + panel + "\" class='moreDetaile' onclick='getOneproduct(" + id + ");return false;'><span>" + langSetting.get(0).get(Language._detail) + "</span></a>");
                        temphtml.append("<a class='moredetail' style='cursor: pointer;' onclick='getOneproduct(" + id + ")'><span>" + langSetting.get(0).get(Language._detail) + "</span></a>");//برای چند زبانه مشکل پیش میاید چون فایل جی اس پی چند زبانه نیست

                        temphtml.append("<div class='prQtyDiv avalable'>" + langSetting.get(0).get(Language._available) + "</div>");//==> prQtyDivLn : productQuantityDiv
                    } else {
                        temphtml.append("<div class='prQtyDiv notAvalable'>" + langSetting.get(0).get(Language._notAvailable) + "</div>");//==> prQtyDivLn : productQuantityDiv
                    }
                    temphtml.append("</div>");
                    //<============ BY RASHIDI ========
                    //<============ BY RASHIDI ========
                    //By Md----------------------
                    /*نمایش جزئیات محصول*/
                    temphtml.append("<div class='prabstarctDiv'><h4>" + topProductRow.get(i).get(_page).toString());
//                    temphtml.append("<div class=''>");
                    for (int j = 1; j <= 5; j++) {
                        String prop = "account_product_prop" + String.valueOf(j);
                        String val = "account_product_val" + String.valueOf(j);
                        if ((topProductRow.get(i).get(val) != null) && (!topProductRow.get(i).get(val).toString().equals("")) && (topProductRow.get(i).get(prop) != null) && (!topProductRow.get(i).get(prop).toString().equals(""))) {
//                        if (j == 1) {
//                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        } else {
                            temphtml.append("<div class='prPropVal'><span class='prProp'>" + topProductRow.get(i).get(prop).toString() + "</span> : <span class='prVal'>" + topProductRow.get(i).get(val).toString() + "</span></div>");
//                        }
                        }
                    }
//                    temphtml.append("</div>");
                    temphtml.append("</h4></div>");

                    //----------------------------
//                    temphtml.append("<span class='moreDatale" + lang + "'>"
//                            + "<a onclick='getOneproduct(" + id + ");'>" + "ادامه مطلب" + "</a>"
//                            + "</span><br/>");
//                    temphtml.append("<span class='coGruopproduct" + lang + "'>"
//                            + "<a onclick='swGetProducts(" + topProductRow.get(i).get(_category_id).toString() + ");'>" + "مطالب مرتبط" + "</a>"
//                            + "</span>");
//                    temphtml.append("<img src='iconImages/cart.png' onclick='addToShoppingCart(" + topProductRow.get(i).get(_id).toString() + ")'>");//====== BY RASHIDI ======
//                    int visit = new Integer(topProductRow.get(i).get(_visit).toString());
//                    if (visit >= 0) {
//                        temphtml.append("<div class='prVDivLn" + lang + "'> <span>" + visit + "بار مشاهده</span></div>");//==> prVDiv : productvisitDiv
//                    }
                    int disLikes = new Integer(topProductRow.get(i).get(_dislike).toString());
                    if (disLikes >= 0) {
                        temphtml.append("<div class='prDLDiv' onclick='productDisLike(" + id + ");' > <span>" + disLikes + langSetting.get(0).get(Language._disLike) + "</span></div>");//==> prDLDiv : productDisLikeDiv
                    }
                    int likes = new Integer(topProductRow.get(i).get(_like).toString());
                    if (likes >= 0) {
                        temphtml.append("<div class='prLDiv' onclick='productLike(" + id + ");'>  <span>" + likes + langSetting.get(0).get(Language._like) + "</span></div>");//==> prLDiv : productlikeDiv
                    }
                    temphtml.append("</div>");
                }
            }
//            html3.append(Js.hide("#commentForm"));
//            html3.append(Js.setHtml("#swLeft", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + temphtml.toString() + "\");\n";
            //============ BY RASHIDI ========>
            if (needString) {
                return temphtml.toString();
            }
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
//BY ENG mahdi hosseini
//این تابع دسته بندی را میگیرد و محصولاتی را که با ان دسته بندی ایجاد شده را نمایش میدهد

    public static String getListCategoryProduct(String categoryID, jjDatabaseWeb db) {
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + categoryID));
        StringBuilder html = new StringBuilder();
        if (row.size() == 0) {
            html.append("یافت نشد...............");
        } else {
            for (int i = 0; i < row.size(); i++) {
                html.append("<div class='hoverProduct roww' style='cursor: pointer;' onclick='getOneproductForCategory(" + row.get(i).get(_id) + ")'>");
                html.append("<div class='pro-img col-lg-4'>");
                html.append("<a onclick='getOneproductForCategory(" + row.get(i).get(_id) + ")'>");
                html.append("<img class='prPicDiv' src='upload/" + row.get(i).get(_pic1) + "' alt='" + row.get(i).get(_name) + "'></a></div>");
                html.append("<div class='prTitleSpan col-lg-4'><h4><a onclick='getOneproductForCategory(" + row.get(i).get(_id) + ")'>" + row.get(i).get(_name) + "</a></h4></div>");
                html.append("<div class='prabstarctDiv col-lg-4'><h4>");
                html.append("<div class='prPropValForAcceptOnline'>");
                html.append("<span class='prProp'>" + row.get(i).get(_prop1) + "</span> : <span class='prVal'>" + row.get(i).get(_val1) + "</span></div>");
                html.append("<div class='prPropValForAcceptOnline'>");
                html.append("<span class='prProp'>" + row.get(i).get(_prop2) + "</span> : <span class='prVal'>" + row.get(i).get(_val2) + "</span></div>");
                html.append("<div class='prPropValForAcceptOnline'><span class='prProp'>" + row.get(i).get(_prop3) + "</span> : <span class='prVal'>" + row.get(i).get(_val3) + "</span></div>");
                html.append("<div class='prPropValForAcceptOnline'><span class='prProp'>" + row.get(i).get(_prop4) + "</span> : <span class='prVal'>" + row.get(i).get(_val4) + "</span></div></h4>");
                html.append("</div></div>");
            }
        }
        return html.toString();
    }
//============ BY RASHIDI ========>

    public static String getList_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0لیست کالاهای انگلیسی را برمیگرداند
        try {
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            /*طبقه بندی محصولات را برمیگرداند*/
//            html3.append(Category_Product.getHierarchyDiv(re, db));
            Integer category_id = new Integer(jjTools.getParameter(request, "id"));
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topProductRow;
            if (category_id == 0) {
                topProductRow = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + "=2 AND " + Product._priority + "<=2"));
            } else {
                topProductRow = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + "=2 AND " + Product._category_id + "=" + category_id));
            }

            //---------------one product post creation
            /*لیست محصولات را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopproductDiv' class='topproductDiv'></div>");
            if (topProductRow.isEmpty()) {
                temphtml.append("<div class='noAnyThing'><span>NO ITEM!!!</span></div>");
            } else {
                for (Map<String, Object> topProductRow1 : topProductRow) {
                    String id = topProductRow1.get(_id).toString();
                    temphtml.append("<div class='productmainDiv'>");
//                    temphtml.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(topProductRow.get(i).get(_date).toString()) + "</span>");
                    int visit = new Integer(topProductRow1.get(_visit).toString());
                    if (visit >= 0) {
                        temphtml.append("<div class='prVDiv'> <span>").append(visit).append(" visited</span></div>");//==> prVDiv : productvisitDiv
                    }
                    int disLikes = new Integer(topProductRow1.get(_dislike).toString());
                    if (disLikes >= 0) {
                        temphtml.append("<div class='prDLDiv' onclick='productDisLike(").append(id).append(");' > <span>").append(disLikes).append(" dislike </span></div>");//==> prDLDiv : productDisLikeDiv
                    }
                    int likes = new Integer(topProductRow1.get(_like).toString());
                    if (likes >= 0) {
                        temphtml.append("<div class='prLDiv' onclick='productLike(").append(id).append(");'>  <span>").append(likes).append(" like </span></div>");//==> prLDiv : productlikeDiv
                    }   //============ EDITED BY RASHIDI ========>
                    String src1 = topProductRow1.get(_pic1).toString();
//                    String src2 = topProductRow.get(i).get(_pic2).toString();
//                    String src3 = topProductRow.get(i).get(_pic3).toString();
//                    String src4 = topProductRow.get(i).get(_pic4).toString();
//                    String src5 = topProductRow.get(i).get(_pic5).toString();
//                    String src6 = topProductRow.get(i).get(_pic6).toString();
//                    String src_ext = topProductRow.get(i).get(_pic_ext).toString();
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src1.matches("upload/p[0-9]{10}.{4}")) {
                    String smalPicSrc = src1.replace(".", "_small.");//select small pic
                    temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'  onclick='getOneproduct(" + id + ")';/>");//======EDITED BY RASHIDI ======
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src1 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src2.matches("upload/p[0-9]{10}.{4}")) {
//                      smalPicSrc = src2.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src2 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src3.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src3.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src3 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src4.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src4.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src4 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src5.matches("upload/p[0-9]{10}.{4}")) {
//                        smalPicSrc = src5.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src5 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src6.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src6.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src6 + "'/>");
//                    }
                    //اگر تصویراز میان تصاویر سایت بود، تصویر کوچک را برگرداند
//                    if (src_ext.matches("upload/p[0-9]{10}.{4}")) {
//                         smalPicSrc = src_ext.replace(".", "_small.");//select small pic
//                        temphtml.append("<img class='productPicDiv' src='upload/" + smalPicSrc + "'/>");
//                    } else {
//                        temphtml.append("<img class='productPicDiv' src='upload/" + src_ext + "'/>");
//                    }
                    //<============ EDITED BY RASHIDI ========
                    temphtml.append("<span class='productTitlespan'><h3>" + topProductRow1.get(_name).toString() + "</h3></span>");
                    //============ BY RASHIDI ========>
                    int quantity = new Integer(topProductRow1.get(_quantity).toString());
                    if (quantity <= 0) {
                        temphtml.append("<div class='prQDiv_En'>  <span> not exist!!</span></div>");//==> prQDiv : productlQuantityDiv
                    } else {
                        temphtml.append("<div class='prQDiv_En'> <span> quantity  " + quantity + " </span></div>");//==> prQDiv : productlQuantityDiv
                    }   //======>PRICE
                    if (userId.equals("")) {
                        //اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                        price = topProductRow1.get(_price2).toString(); //قیمت اصلی را به کاربر نمایش می دهد
                        discountPrice = topProductRow1.get(_discount).toString() == "" ? "" : topProductRow1.get(_discount).toString(); //اگر تخفیف برایش در نظر گرفته شود تخفیف هم نمایش داده می شود.
                    } else {
                        //اگر کاربر لاگین کرده باشد
                        List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                        if (!userRow.isEmpty()) {
                            //اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                            String userGroup = userRow.get(0).get(Access_Group_User._group_id).toString();
//                            System.out.println("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(topProductRow1.get(_userGroup1).toString())) {
                                price = topProductRow1.get(_groupPrice1).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow1.get(_userGroup2).toString())) {
                                price = topProductRow1.get(_groupPrice2).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow1.get(_userGroup3).toString())) {
                                price = topProductRow1.get(_groupPrice3).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow1.get(_userGroup4).toString())) {
                                price = topProductRow1.get(_groupPrice4).toString();
                            } else if (userGroup.equalsIgnoreCase(topProductRow1.get(_userGroup5).toString())) {
                                price = topProductRow1.get(_groupPrice5).toString();
                            } else {
                                price = topProductRow1.get(_price2).toString();
                            }
                        } else {
                            //اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                            price = topProductRow1.get(_price2).toString();
                            discountPrice = topProductRow1.get(_discount).toString() == "" ? "" : topProductRow1.get(_discount).toString();
                        }
                    }
                    ServerLog.Print("PRICE : " + price);
                    ServerLog.Print("DISCOUNTPRICE : " + discountPrice);
                    if (discountPrice.equalsIgnoreCase("")) {
                        //اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                    } else {
                        //اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود

                        //=======> Time of Discount
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());
                        int remainTime = Integer.parseInt(topProductRow1.get(_discountDate).toString()) - dateIR.getDBFormat_8length(); //تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);
                        if (remainTime > 0) {
                            //اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            temphtml.append("<span class='productTitlespan'><h3 style='text-decoration: line-through;' >" + price + "</h3></span>");
                            temphtml.append("<span class='productTitlespan'><h3>" + discountPrice + "</h3></span>");
                            temphtml.append("<span class='productTitlespan'><h3>remain time: " + remainTime + " روز</h3></span>");
                        } else if (remainTime == 0) {
                            int remainHours = 0, remainMinutes = 0;
                            String[] timeArr = topProductRow1.get(_discountTime).toString().split(":");
                            ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                            remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                            remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                            if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                temphtml.append("<span class='productTitlespan'><h3 style='text-decoration: line-through;' >" + price + "</h3></span>");
                                temphtml.append("<span class='productTitlespan'><h3>" + discountPrice + "</h3></span>");
                                ServerLog.Print("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                                temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                                temphtml.append("<span class='productTitlespan'><h3>remain time: " + remainMinutes + "Minutes : " + remainHours + " Hours</h3></span>");
//                        temphtml.append("<span class='productTitlespan'><h3>" + "دقیقه"+ " : " + "ساعت" + " </h3></span>");

                            } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                            }
//                            System.out.println("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                            temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                        } else {
                            temphtml.append("<span class='productTitlespan'><h3>" + price + "</h3></span>");
                        }
                        //<======= Time of Discount
                    }
                    //<======PRICE
                    //<============ BY RASHIDI ========
                    //By Md----------------------
                    /*نمایش جزئیات محصول*/
                    temphtml.append("<div class='productabstarctDiv'><h4>" + topProductRow1.get(_page).toString());
                    for (int j = 1; j <= 5; j++) {
                        String Key = "account_product_val" + String.valueOf(j);
                        if ((topProductRow1.get(Key) != null) && (!topProductRow1.get(Key).toString().equals("0"))) {
//                        if (j == 1) {
//                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        } else {
                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow1.get(Key).toString() + "</h5></span>");
//                        }
                        }
                    }
                    temphtml.append("</h4></div>");
                    //----------------------------
                    temphtml.append("<span class='moreDatale'>"
                            + "<a onclick='getOneproduct(" + id + ");'>" + "cont." + "</a>"
                            + "</span><br/>");
                    temphtml.append("<span class='coGruopproduct'>"
                            + "<a onclick='swGetProducts(" + topProductRow1.get(_category_id).toString() + ");'>" + "related" + "</a>" + "</span>");
                    temphtml.append("<img src='iconImages/cart.png' title='add to shopping cart' onclick='addToShoppingCart(" + topProductRow1.get(_id).toString() + ")'>"); //====== BY RASHIDI ======
                    temphtml.append("</div>");
                }
            }
            html3.append(Js.setHtml("#swTopproductDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            return html2 + html3;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

//<============ BY RASHIDI ========
    public static String getOneProduct2(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            //<============BY RASHIDI ========
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            //to incriment visited time
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
            List<Map<String, Object>> row;
            row = jjDatabase.separateRow(db.Select(tableName, _id + " = " + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            html.append("<div>");
            if (lang.equals("1")) {
                html.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
            } else {
                jjCalendar_IR irDate = new jjCalendar_IR(row.get(0).get(_date).toString());
                jjCalendar_EN enDate = new jjCalendar_EN();
                enDate = irDate.convertPersianToGregorian();
                html.append("<span class='productDatespan'>" + enDate.getViewFormat_10length() + "</span>");
            }
            //============ BY RASHIDI ========>
            html.append("<div class='productPicDiv" + lang + "'>");
            String src1 = row.get(0).get(_pic1).toString();
            String src2 = row.get(0).get(_pic2).toString();
            String src3 = row.get(0).get(_pic3).toString();
            String src4 = row.get(0).get(_pic4).toString();
            String src5 = row.get(0).get(_pic5).toString();
            String src6 = row.get(0).get(_pic6).toString();
            String src_ext = row.get(0).get(_pic_ext).toString();
            String smalPicSrc = "";
            smalPicSrc = src1.replace(".", "_small.");//select small pic
            html.append("<img class='mainPic productPic' src='upload/" + smalPicSrc + "'/>");
            html.append("<div class='productExtraDiv'>");

            smalPicSrc = src2.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            smalPicSrc = src3.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            smalPicSrc = src4.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            smalPicSrc = src5.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            smalPicSrc = src6.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            smalPicSrc = src_ext.replace(".", "_small.");
            html.append("<img class='productPic' src='upload/" + smalPicSrc + "'/>");

            html.append("</div></div>");

            html.append("<span class='productTitlespan'><h3>" + row.get(0).get(_name).toString() + "</h3></span>");

            //======>PRICE
            if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                price = row.get(0).get(_price2).toString();//قیمت اصلی را به کاربر نمایش می دهد
                discountPrice = row.get(0).get(_discount).toString() == "" ? "" : row.get(0).get(_discount).toString();//اگر تخفیف برایش در نظر گرفته شود تخفیف هم نمایش داده می شود.
            } else {//اگر کاربر لاگین کرده باشد
                List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                    String userGroup = userRow.get(0).get(Access_Group_User._group_id).toString();
//                    System.out.println("userGroup " + userGroup);
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup1).toString())) {
                        price = row.get(0).get(_groupPrice1).toString();
                    } else if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup2).toString())) {
                        price = row.get(0).get(_groupPrice2).toString();
                    } else if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup3).toString())) {
                        price = row.get(0).get(_groupPrice3).toString();
                    } else if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup4).toString())) {
                        price = row.get(0).get(_groupPrice4).toString();
                    } else if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup5).toString())) {
                        price = row.get(0).get(_groupPrice5).toString();
                    } else {
                        price = row.get(0).get(_price2).toString();
                    }
                } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                    price = row.get(0).get(_price2).toString();
                    discountPrice = row.get(0).get(_discount).toString() == "" ? "" : row.get(0).get(_discount).toString();
                }
            }

            //<======PRICE
            html.append("<div class='productabstarctDiv'><h4>" + row.get(0).get(_page).toString());
            html.append("<div class='productPropVal'>");
            for (int j = 1; j <= 5; j++) {
                String prop = "account_product_prop" + String.valueOf(j);
                String val = "account_product_val" + String.valueOf(j);
                if ((row.get(0).get(val) != null) && (!row.get(0).get(val).toString().equals("")) && (row.get(0).get(prop) != null) && (!row.get(0).get(prop).toString().equals(""))) {
//                        if (j == 1) {
//                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        } else {
                    html.append("<span><h5>" + row.get(0).get(val).toString() + " : " + row.get(0).get(prop).toString() + "</h5></span>");
//                        }
                }
            }
            html.append("</div>");
            html.append("</h4></div>");
            ServerLog.Print("PRICE : " + price);
            ServerLog.Print("DISCOUNTPRICE : " + discountPrice);

            if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                html.append("<div class='productPrice'><h4>" + price + "</h4></div>");
            } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود

                //=======> Time of Discount
                //محاسبه زمان باقی مانده تا پایان تخفیف
                jjCalendar_IR dateIR = new jjCalendar_IR();
//                System.out.println("DATE : " + dateIR.getDBFormat_8length());

                int remainTime = Integer.parseInt(row.get(0).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                ServerLog.Print("remain day : " + remainTime);

                if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                    html.append("<div class='productPrice'><h5 style='text-decoration: line-through;' >" + price + "</h5></div>");
                    html.append("<div class='productDisCount'><h4>" + discountPrice + "</h4></span>");
                    html.append("<div class='productRemTime'><h3>" + langSetting.get(0).get(Language._remainTime) + " : " + remainTime + langSetting.get(0).get(Language._day) + "</h3></div>");
                } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                    int remainHours = 0, remainMinutes = 0;
                    String timeArr[] = row.get(0).get(_discountTime).toString().split(":");
                    ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                    remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                    remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                    if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                        html.append("<div class='productPrice'><h5 style='text-decoration: line-through;' >" + price + "</h5></div>");
                        html.append("<div class='productDisCount'><h4>" + discountPrice + "</h4></div>");
                        ServerLog.Print("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                                temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                        html.append("<div class='productRemTime'><h3>" + langSetting.get(0).get(Language._remainTime) + " : " + remainMinutes + langSetting.get(0).get(Language._minute) + remainHours + langSetting.get(0).get(Language._hour) + "</h3></div >");
//                        temphtml.append("<span class='productTitlespan'><h3>" + "دقیقه"+ " : " + "ساعت" + " </h3></span>");

                    } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                        html.append("<div class='productPrice'><h4>" + price + "</h4></div>");
                    }
//                            System.out.println("remainHours : " + remainHours + " & remainMinutes : " + remainMinutes);
//                            temphtml.append("<span class='productTitlespan'><h3>زمان باقی مانده : " + remainMinutes + " : " + remainHours + "</h3></span>");
                } else {
                    html.append("<div class='productPrice'><h4>" + price + "</h4></div>");
                }

                //<======= Time of Discount
            }
            int quantity = new Integer(row.get(0).get(_quantity).toString());
            if (quantity <= 0) {
                html.append("<div class='productQtyDiv'>  <span>" + langSetting.get(0).get(Language._notAvailable) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
            } else {
                html.append("<div class='prductQtyDiv'>  <span>" + langSetting.get(0).get(Language._available) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
            }
            int disLikes = new Integer(row.get(0).get(_dislike).toString());
            if (disLikes >= 0) {
                html.append("<div class='productDLDiv' onclick='productDisLike(" + id + ");' ><span>" + disLikes + " " + langSetting.get(0).get(Language._disLike) + "</span></div>");
            }
            int likes = new Integer(row.get(0).get(_like).toString());
            if (likes >= 0) {
                html.append("<div class='productLDiv' onclick='productLike(" + id + ");' ><span>" + likes + " " + langSetting.get(0).get(Language._like) + "</span></div>");
            }
            int visit = new Integer(row.get(0).get(_visit).toString());
            if (visit >= 0) {
                html.append("<div class='productVDiv' ><span>" + visit + " " + langSetting.get(0).get(Language._visited) + "</span></div>");
            }
//            html.append("<img src='iconImages/cart.png' onclick='addToShoppingCart(" + row.get(0).get(_id).toString() + ")'>");//====== BY RASHIDI ======
            //<============ BY RASHIDI ========

//            html.append("<span class='moreDataleLn" + lang + "'>"
//                    + "<a onclick='swGetProducts(" + row.get(0).get(_category_id).toString() + ");'>" + " مطالب مرتبط" + "</a>"
//                    + "</span>");
            html.append("</div>");
            html.append("<div class='ProductdetailBtn'>");
            html.append("<a class='coGruopproduct' onclick='addToShoppingCart(" + row.get(0).get(_id).toString() + ");'>" + langSetting.get(0).get(Language._addCart) + "</a>");
            html.append("<a class='moreDetaile' onclick='swGetProducts(" + row.get(0).get(_category_id).toString() + ");'>" + langSetting.get(0).get(Language._related) + "</a>");
            html.append("</div>");
            //1-->
//            html.append("<div id='commentForm' name='commentForm'></div>");//====== BY RASHIDI ======
////            html.append("$(\"#commentForm\").load(\"formCms/comment.html\")");
//            String script = "$('#" + panel + "').html(\"" + html.toString() + "\");";
//            script += ("$('#commentForm').load('formCms/public_comment_fa.html', null, function () {$('#comment_refrenceId').val('tblName=" + tableName + "&id=" + id + "');});");//====== BY RASHIDI ======
//            script += Js.setVal("#comment_refId", "Product_" + id);
            //1<--
            //2-->
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");";
            html2 += Js.setHtml("#newContentDiv", row.get(0).get(_content).toString());
            html2 += Js.show("#commentForm");
            html2 += Js.setVal("#comment_refrenceId", "tblName=" + tableName + "&id=" + id);
            //2<--
//            row.get(0).get(_content).toString()
//        script += Js.table("#sss", height, sort, "");
//            script += (Js.append("#commentForm", "<input type='text' id='comment_refId' name='comment_refId' value='Product_" + id + "'/>"));
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

//By Md
    public static String productDisLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _dislike + " = " + _dislike + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String productLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _like + " = " + _like + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * این تایع آیتم های موجوددر سبد خرید را از دیتابیس واکشی می کند و در قالب
     * یک جدول برمیگرداند تا به کاربر نمایش داده شود. این تابع برای اپلیکیشن
     * طراحی شده است
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String buildShoppingCart(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String panel = jjTools.getParameter(request, "panel");
            String productsID = jjTools.getParameter(request, "productsId");
            panel = panel == null ? "sw" : panel;
            String comment = "";
            String lang = jjTools.getSessionAttribute(request, "myLang") == "" ? "1" : jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            String price = "";
            int sumPrice = 0;
            String where = "";
            String[] productsId = productsID.split(",");
            for (int i = 0; i < productsId.length; i++) {
                if (i == productsId.length - 1) {
                    where += _id + " = " + productsId[i];
                } else {
                    where += _id + " = " + productsId[i] + " OR ";
                }
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, where));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
//            System.out.println(row);
            StringBuilder html = new StringBuilder();
            html.append("<div class='card shadow mb-4'>");
            html.append("<div class='card-header py-3 text-primary'>");
            html.append("فاکتور  پرداخت دوره");
            html.append("</div>");
            html.append("<div class='card-body'>");
            html.append("<input type='hidden' id='product_factor_item_productId' class='' value='" + langSetting.get(0).get(Language._id) + "'>");
            html.append("<div class='table-responsive' style='margin-top:20px;'>");
            html.append("<table class='table table-bordered' id='shoppingCartApp' width='100%' cellspacing='0'>");
            html.append("<th style='text-align:center;'>" + langSetting.get(0).get(Language._productName) + "</th>");
            html.append("<th style='text-align:right;'>" + langSetting.get(0).get(Language._number) + "</th>");
            html.append("<th style='text-align:center;'>" + langSetting.get(0).get(Language._price) + "</th>");
            html.append("<th style='text-align:center;'>" + langSetting.get(0).get(Language._sumPrice) + "</th>");
            html.append("<th style='text-align:center;'>" + langSetting.get(0).get(Language._delete) + "</th>");
            html.append("</thead><tbody>");
            List<Map<String, Object>> tic_config = jjDatabaseWeb.separateRow(db.Select(Tice_config.tableName, Tice_config._config_Name + "='" + Tice_config._config_exchange_unite + "'"));
            for (int i = 0; i < row.size(); i++) {
                ///=========Price======>
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(row.get(i).get(_discount).toString())) {
                            price = row.get(i).get(_price2).toString();
                        } else {
                            price = row.get(i).get(_discount).toString();
                        }
                    } else {
                        price = row.get(i).get(_price2).toString();
                    }
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                    System.out.println(userRow.size());
                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
//                            System.out.println("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup1).toString())) {
                                String price1 = "".equals(row.get(i).get(_groupPrice1).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice1).toString();
                                price = price1;
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup2).toString())) {
                                String price2 = "".equals(row.get(i).get(_groupPrice2).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice2).toString();
                                if (price.equals("")) {
                                    price = price2;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                        price = price2;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup3).toString())) {
                                String price3 = "".equals(row.get(i).get(_groupPrice3).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice3).toString();
                                if (price.equals("")) {
                                    price = price3;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                        price = price3;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup4).toString())) {
                                String price4 = row.get(i).get(_groupPrice4).toString();
                                if (price.equals("")) {
                                    price = price4;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                        price = price4;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup5).toString())) {
                                String price5 = "".equals(row.get(i).get(_groupPrice5).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice5).toString();
                                if (price.equals("")) {
                                    price = price5;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                        price = price5;
                                    }
                                }
                            }
                        }
                        if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(row.get(i).get(_discount).toString())) {
                                String price6 = row.get(i).get(_price2).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            } else {
                                String price6 = row.get(i).get(_discount).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            }
                        } else {
                            if (price.equals("")) {
                                price = row.get(i).get(_price2).toString();
                            } else {
                                if (Integer.parseInt(price) > Integer.parseInt(row.get(i).get(_price2).toString())) {
                                    price = row.get(i).get(_price2).toString();
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(row.get(i).get(_discount).toString())) {
                                price = row.get(i).get(_price2).toString();
                            } else {
                                price = row.get(i).get(_discount).toString();
                            }
                        } else {
                            price = row.get(i).get(_price2).toString();
                        }
                    }
                }
                sumPrice += Integer.parseInt(price);
                ///<=========Price======
                Integer number = i + 1;
                html.append("<tr id='prRowTr" + row.get(i).get(_id).toString() + "'>");
                html.append("<td class='c'>" + row.get(i).get(_name).toString() + "</td>");
                html.append("<td class='c'><input class='inputNum' type='number' id='factor_quantity" + row.get(i).get(_id).toString() + "' name='factor_quantity" + row.get(i).get(_id).toString() + "' value='1' onchange='calculatePrice(" + row.get(i).get(_id).toString() + ");'></td>");
                html.append("<td class='c'><span class='unitPrice'>" + price + "</span> <span>" + tic_config.get(0).get(Tice_config._config_value) + "</span></td>");
                html.append("<td class='c'><span class='sumPrice'>" + price + "</span> <span>" + tic_config.get(0).get(Tice_config._config_value) + "</span></td>");
                html.append("<td class='c deleteRow' style='cursor: pointer;' onclick='deletePrFromCart(" + row.get(i).get(_id).toString() + ");'><i class='far fa-trash-alt'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div></div</div>");
            html.append("<div class='col-lg-4 col-md-2' style='align:center;'>");
            html.append("</div>");
            html.append("<div class='col-lg-4 col-md-8' style='padding-right:18px;padding-left:18px;'>");
            html.append("<div class='col-12 finalPriceDiv'>");
            html.append("<span>" + langSetting.get(0).get(Language._sumPayment) + "</span>");
            html.append("<span id='finalPrice'>" + sumPrice + "</span><span>" + tic_config.get(0).get(Tice_config._config_value) + "</span>");
            html.append("</div>");
            html.append("</div>");
            html.append("<div class='col-lg-4 col-md-2' style='align:center;'>");
            html.append("</div>");
            html.append("</div>");
            html.append("<div class='card shadow mb-4 mg-t-10' style='margin-left:10px;margin-right:10px;'>\n"
                    + "                        <div class='card-header py-3 tx-primary' style='text-align: center;' onclick='toggleFactorBeforPay();'>\n"
                    + "                           <div class='col-12'>"
                    + "                             <div class=\"row\">\n"
                    + "                                 <div class='col-11' style='padding-top: 5px;'>اطلاعات تکمیلی فاکتور</div>\n"
                    + "                                 <div class='col-1'><i class='fad fa-angle-double-down' style='font-size: 20px;padding-top: 5px;'></i></div>\n"
                    + "                             </div>\n"
                    + "                           </div>\n"
                    + "                        </div><!-- card-header -->\n"
                    + "                        <div class='card-body border-left-primary' id='factorBeforPay' style='display: none;'>\n"
                    + "                            <div class=\"row mg-t-20\">\n"
                    + "                                <input type=\"hidden\" name=\"id\" id=\"product_factor_id\">\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    نام شرکت\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_companyName\" id=\"product_factor_companyName\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    نام  شرکت کننده:\n"
                    + "                                    <input type=\"text\" class=\"form-control\" value='" + jjTools.getSeassionUserNameAndFamily(request) + "'>                    \n"
                    + "                                    <input type='hidden' id=\"product_factor_userId\" name=\"product_factor_userId\" class=\"form-control\" value='" + jjTools.getSeassionUserId(request) + "'>                    \n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    کد ملی /شناسه ملی :\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_nationalCode\" id=\"product_factor_nationalCode\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    کد اقتصادی:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_economicCode\" id=\"product_factor_economicCode\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-12\">\n"
                    + "                                    آدرس محل سکونت:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_address\" id=\"product_factor_address\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    شماره تلفن:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_tell\" id=\"product_factor_tell\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    کد پستی:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_zipCode\" id=\"product_factor_zipCode\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-12\">\n"
                    + "                                    آدرس  2:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_address2\" id=\"product_factor_address2\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    شماره تلفن 2:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_tell2\" id=\"product_factor_tell2\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"col-lg-6\">\n"
                    + "                                    کد پستی 2:\n"
                    + "                                    <input class=\"form-control\" type=\"text\" name=\"product_factor_zipCode2\" id=\"product_factor_zipCode2\">\n"
                    + "                                </div>\n"
                    + "                    </div></div></div>");
            html.append("<div class='col-lg-12' style='margin-top:10px;margin-bottom:10px;'>");
            html.append("<a href='#' onclick='prePayment();' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text' style='width: 100%;'>پرداخت</span></a>");
            html.append("</div>");
            html.append("<div style='margin-top: 60px;'></div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#" + panel + "').offset().top -100}, 500);");
            script.append(Js.setHtml("#" + panel, html.toString()));
//            String script1 = "$('#shoppingCartApp').DataTable( {"
//                    + "        'paging':   false,"
//                    + "        'ordering': false,"
//                    + "        'info':     false,"
//                    + "        'searching': false"
//                    + "    } );";
            System.out.println(panel);
            System.out.println(script.toString());
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String preShoppingCartSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String lang = jjTools.getParameter(request, "lang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            String panel = jjTools.getParameter(request, "panel");
            int totalPrice = 0;
            int disCountPrice = 0;
            Cookie[] cookies = request.getCookies();//آی دی کالاهای موجود در دیتابیس داخل کوکی دخیره شده است پس آنها را از کوکی میخانیم
            String productInCookieStr = "";
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equalsIgnoreCase("productsId")) {
                        productInCookieStr = cookies[i].getValue().replace("%2C", ",").replace("%3B", ";");
                        productInCookieStr = productInCookieStr.replaceFirst(";", "");// سمی کالن اولی را حذف میکنیم
                        productInCookieStr = productInCookieStr.replace(";;", ";");//                         
                        ServerLog.Print("Product.buildShoppingCart =>Cookie: (product,number) " + productInCookieStr);

                    }
                }
            } else {
                return Js.dialog("خطا");
            }
            String[] productsIdCountArray = productInCookieStr.split(";");
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
            html.append("<div class='cartbox__items'>");
            for (int i = 0; i < productsIdCountArray.length; i++) {
                String temp = productsIdCountArray[i];
                String prId = temp.replaceAll(",\\d+", "");
                String count = temp.replaceAll("\\d+,", "");
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + prId));
                html.append("<div class='cartbox__item' id='prRowTr" + row.get(0).get(_id).toString() + "'>"
                        + "<div class='cartbox__item__thumb' onclick='getDetailsProduct(" + row.get(0).get(_id) + ");closeCart();' style='cursor: pointer;'>"
                        + "<img src='upload/" + row.get(0).get(_pic1) + "' alt='small thumbnail'>"
                        + "</div>"
                        + "<div class='cartbox__item__content'>"
                        + "<div style='text-align:right;'><a class='product-name' onclick='getDetailsProduct(" + row.get(0).get(_id) + ");closeCart();' style='cursor: pointer;'>" + row.get(0).get(_name) + "</a></div>");
                int pric = Integer.parseInt(row.get(0).get(_price1).toString()) * Integer.valueOf(count);
                totalPrice += pric;
                html.append(getPriceProduct(request, db, row, langSetting, 0));
                html.append("<div class='sumPriceNoDiscount'>تعداد:" + count + "=" + pric + "ریال</div>");
                int disCount = getOnlyDiscountPriceProduct(request, db, row, langSetting, 0);
                if (disCount == 0) {
                    disCountPrice += pric;
                } else {
                    disCountPrice += disCount;
                }
                html.append("</div>"
                        + "<button class='cartbox__item__remove' onclick='deletePrFromCart(" + row.get(0).get(_id) + ");'>"
                        + "<i class='fa fa-trash'></i>"
                        + "</button>"
                        + "</div>");
            }
            html.append("</div>");
            html.append("<div class='cartbox__total'>");
            html.append("<div>");
            if (totalPrice != disCountPrice) {
                html.append("<div><span class='cartbox__total__title'>قیمت بدون تخفیف</span><span class='price'>" + jjNumber.getFormattedNumber(String.valueOf(totalPrice)) + "</span></div>");
                html.append("<div><span class='cartbox__total__title'>قیمت با تخفیف</span><span class='price'>" + jjNumber.getFormattedNumber(String.valueOf(disCountPrice)) + "</span></div>");
            }
            html.append("<div class='grandtotal'> قابل پرداخت<span class='price'>" + jjNumber.getFormattedNumber(String.valueOf(disCountPrice)) + "</span></div>");
            html.append("</div>");

            html.append("</div>");
            if (isPost) {
                return html.toString();
            }
            script.append(Js.setHtml("#" + panel, html.toString()));
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String buildFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            panel = panel == null ? "sw" : panel;
            String[] productsId = null;
            String lang = jjTools.getSessionAttribute(request, "myLang") == "" ? "1" : jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));

            String price = "";
            String originalPrice = "";
            int sumPrice = 0;
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
//            System.out.println(row);
            StringBuilder html = new StringBuilder();
            html.append("<input id='product_id' name='product_id' style='display: none;' value='" + id + "'>");
            html.append("<div class='col-lg-12'>");
            html.append("<section class='signup'><div class='container'>\n");
            html.append("<div class='col-lg-12'>");
            html.append("<table class='col-lg-12' class='tahoma10' style='text-align:center;border:1px solid #D8DCE3;'><thead>");
            html.append("<th style='text-align:center;background-color:#D8DCE3;border:1px solid #D8DCE3;'>" + langSetting.get(0).get(Language._productName) + "</th>");
            html.append("<th style='text-align:center;background-color:#D8DCE3;border:1px solid #D8DCE3;'>" + langSetting.get(0).get(Language._price) + "</th>");
            html.append("<th style='text-align:center;background-color:#D8DCE3;border:1px solid #D8DCE3;'>مبلغ قابل پرداخت</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(row.get(i).get(_discount).toString())) {
                            price = row.get(i).get(_price2).toString();
                        } else {
                            price = row.get(i).get(_discount).toString();
                        }
                    } else {
                        price = row.get(i).get(_price2).toString();
                    }
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup1).toString())) {
                                String price1 = "".equals(row.get(i).get(_groupPrice1).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice1).toString();
                                if (price.equals("")) {
                                    price = price1;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                        price = price1;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup2).toString())) {
                                String price2 = "".equals(row.get(i).get(_groupPrice2).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice2).toString();
                                if (price.equals("")) {
                                    price = price2;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                        price = price2;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup3).toString())) {
                                String price3 = "".equals(row.get(i).get(_groupPrice3).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice3).toString();
                                if (price.equals("")) {
                                    price = price3;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                        price = price3;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup4).toString())) {
                                String price4 = row.get(i).get(_groupPrice4).toString();
                                if (price.equals("")) {
                                    price = price4;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                        price = price4;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(row.get(i).get(_userGroup5).toString())) {
                                String price5 = "".equals(row.get(i).get(_groupPrice5).toString()) ? row.get(i).get(_price2).toString() : row.get(i).get(_groupPrice5).toString();
                                if (price.equals("")) {
                                    price = price5;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                        price = price5;
                                    }
                                }
                            }
                        }
                        if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(row.get(i).get(_discount).toString())) {
                                String price6 = row.get(i).get(_price2).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            } else {
                                String price6 = row.get(i).get(_discount).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            }
                        } else {
                            if (price.equals("")) {
                                price = row.get(i).get(_price2).toString();
                            } else {
                                if (Integer.parseInt(price) > Integer.parseInt(row.get(i).get(_price2).toString())) {
                                    price = row.get(i).get(_price2).toString();
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(row.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(row.get(i).get(_discount).toString())) {
                                price = row.get(i).get(_price2).toString();
                            } else {
                                price = row.get(i).get(_discount).toString();
                            }
                        } else {
                            price = row.get(i).get(_price2).toString();
                        }
                    }
                }
                System.out.println(price);
                sumPrice += Integer.parseInt(price);
                originalPrice = row.get(i).get(_price2).toString();
                ///<=========Price======
                Integer number = i + 1;
                html.append("<tr id='prRowTr" + row.get(i).get(_id).toString() + "'>");
                html.append("<td style='border:1px solid #D8DCE3;'><input type='hidden' value='" + row.get(i).get(_id).toString() + "'>" + row.get(i).get(_name).toString() + "</td>");
                html.append("<td style='border:1px solid #D8DCE3;'><span class='unitPrice'>" + jjNumber.getFormattedNumber(row.get(i).get(_price2).toString()) + "</span> <span>" + row.get(i).get(_currency) + "</span></td>");
                html.append("<td style='border:1px solid #D8DCE3;'><span class='sumPrice'>" + jjNumber.getFormattedNumber(price) + "</span> <span>" + row.get(i).get(_currency) + "</span></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div>");
            html.append("<div class='card rounded-0 mg-t-10'>\n"
                    + "                        <div class='card-header bg-primary tx-white' style='text-align: center;'>\n"
                    + "                            اطلاعات تکمیلی فاکتور\n"
                    + "                        </div><!-- card-header -->\n"
                    + "                        <div class='card-body' id='factorBeforPay'>\n"
                    + "                            <div class='row mg-t-20'>\n"
                    + "                                <input type='hidden' name='id' id='product_factor_id'>\n"
                    + "                                <input type='hidden' name='product_factor_item_productId' id='product_factor_item_productId' value='" + id + "'>\n"
                    + "                                <input type='hidden' name='product_factor_item_originalPrice' id='product_factor_item_originalPrice' value='" + originalPrice + "'>\n"
                    + "                                <input type='hidden' name='product_factor_item_priceAfterDiscount' id='product_factor_item_priceAfterDiscount' value='" + price + "'>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    نام شرکت\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_companyName' id='product_factor_companyName'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    نام مشتری:\n"
                    + "                                    <input type='text' class='form-control' value='" + jjTools.getSeassionUserNameAndFamily(request) + "'>                    \n"
                    + "                                    <input type='hidden' id='product_factor_userId' name='product_factor_userId' class='form-control' value='" + jjTools.getSeassionUserId(request) + "'>                    \n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    کد ملی:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_nationalCode' id='product_factor_nationalCode'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    کد اقتصادی:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_economicCode' id='product_factor_economicCode'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-12'>\n"
                    + "                                    آدرس مشتری:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_address' id='product_factor_address'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    شماره تلفن:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_tell' id='product_factor_tell'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    کد پستی:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_zipCode' id='product_factor_zipCode'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-12'>\n"
                    + "                                    آدرس مشتری 2:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_address2' id='product_factor_address2'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    شماره تلفن 2:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_tell2' id='product_factor_tell2'>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-6'>\n"
                    + "                                    کد پستی 2:\n"
                    + "                                    <input class='form-control' type='text' name='product_factor_zipCode2' id='product_factor_zipCode2'>\n"
                    + "                                </div>\n"
                    + "                    </div></div></div>");
            html.append("<div class='col-12' style='text-align: center;margin-top: 10px;'>");
            html.append("<button id='insert_ItemFactorInFactor_new' title='ثبت' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' onclick='prePayment();'>پرداخت</button>");
            html.append("</div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#" + panel + "').offset().top -100}, 500);");
            script.append(Js.setHtml("#" + panel, html.toString()));
//            script.append(Js.show("#paymentBtn"));
            System.out.println(panel);
            System.out.println(script.toString());
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getTopProducts(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String maxNo = (jjTools.getParameter(request, "maxNo").equals("")) ? "10" : jjTools.getParameter(request, "maxNo");//تعداد محصولاتی که کاربر خواستار دیدن آن هاست
            String split = jjTools.getParameter(request, "split");//کاربر میخواهد محصولات را چندتا چندتا ببیند
            List<Map<String, Object>> topProducts = jjDatabase.separateRow(db.SelectDescLimit(tableName, _active + " = 1 ", _date, maxNo));
            for (int i = 0; i < 50 && i < topProducts.size(); i++) {//maximum 50 top product return
                if ((double) i / (double) Integer.parseInt(split) == i / Integer.parseInt(split)) {//برای هرگروه از کالاها که با توجه به متغیر اسپلیت تعریف میشوند یک دایو جداگانه میسازد
                    html.append("<div style='border:1px solid gray; padding:2px;' id='productGroup(" + (i / Integer.parseInt(split)) + ")' class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">");
                }
                if (Boolean.valueOf(topProducts.get(i).get(_active).toString())) {
                    html.append("<div class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">"
                            + "<div class=\"thumbnail\">"
                            + "<a title = \"" + topProducts.get(i).get(_name) + "\" class=\"fancy fancybox\" href=\"upload/" + topProducts.get(i).get(_pic1).toString() + "\" data-fancybox-group=\"gallery\">"
                            //                            + "<img src=\"upload/" + topProducts.get(i).get(_pic1).toString().replace(".", "_small.") + "\" onclick='cmsProduct.m_select(" + topProducts.get(i).get(_id).toString() + ") alt=\"" + topProducts.get(i).get(_name) + "\">"
                            + "<img src=\"upload/" + topProducts.get(i).get(_pic1).toString().replace(".", ".") + "\" onclick='cmsProduct.m_select(" + topProducts.get(i).get(_id).toString() + ") alt=\"" + topProducts.get(i).get(_name) + "\">"
                            + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                            + "</a>"
                            + "<div class=\"caption\">"
                            + "<a href=\"#\" onclick='cmsProduct.m_select(" + topProducts.get(i).get(_id).toString() + ")'>" + topProducts.get(i).get(_name) + "</a>"
                            + "<p><a class=\"btn btn-default button\" href=\"#\" onclick=\"cmsProduct.m_select(" + topProducts.get(i).get(_id).toString() + ");return false;\">"
                            + "بیشتر بخوانید "
                            + "</a></p>"
                            + "</div>"
                            + "</div>"
                            + "</div>");
                }
                if ((double) (i + 1) / (double) Integer.parseInt(split) == (i + 1) / Integer.parseInt(split)) {//اگر دایو جدیدی برای یک گروه کالا ساخته شده باشد اینجا بسته میشود
                    html.append("</div>");
                }
            }
            if (panel.equals("")) {
                panel = "showTopProductsDiv";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String showDevidedProducts(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String split = jjTools.getParameter(request, "split");//کاربر میخواهد محصولات را چندتا چندتا ببیند
            String page = jjTools.getParameter(request, "page");//کاربر میخواهد محصولات کدام صفحه را ببیند

            List<Map<String, Object>> allProducts = jjDatabase.separateRow(db.Select(tableName));

            int start = Integer.parseInt(split) * Integer.parseInt(page) - Integer.parseInt(split);//در این دو خط معلوم میشود از چه محصولی تا چه محصولی به کاربر نمایش داده شود
            int end = start + Integer.parseInt(split);

            for (int i = start; i < end && i < allProducts.size(); i++) {
//                html.append("<div style='border:1px solid gray; padding:2px;' class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">");
                if (Boolean.valueOf(allProducts.get(i).get(_active).toString())) {
                    html.append("<div class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">"
                            + "<div class=\"thumbnail\">"
                            + "<a title = \"" + allProducts.get(i).get(_name) + "\" class=\"fancy fancybox\" href=\"upload/" + allProducts.get(i).get(_pic1).toString() + "\" data-fancybox-group=\"gallery\">"
                            + "<img src=\"upload/" + allProducts.get(i).get(_pic1).toString().replace(".", "_small.") + "\" onclick='cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ") alt=\"" + allProducts.get(i).get(_name) + "\">"
                            + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                            + "</a>"
                            + "<div class=\"caption\">"
                            + "<a href=\"#\" onclick='cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ")'>" + allProducts.get(i).get(_name) + "</a>"
                            + "<p><a class=\"btn btn-default button\" href=\"#\" onclick=\"cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ");return false;\">"
                            + "بیشتر بخوانید "
                            + "</a></p>"
                            + "</div>"
                            + "</div>"
                            + "</div>");
                }
            }

            if (panel.equals("")) {
                panel = "showDividedProductsDiv";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String calculateNumOfPages(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String split = jjTools.getParameter(request, "split");//کاربر میخواهد محصولات را چندتا چندتا ببیند

            List<Map<String, Object>> allProducts = jjDatabase.separateRow(db.Select(tableName));

            int numOfPages = Math.floorDiv(allProducts.size(), Integer.parseInt(split));
            System.out.println(".................................int : " + numOfPages);
            if (numOfPages != (double) allProducts.size() / (double) Integer.parseInt(split)) {//اگر تعداد کل کالاها بر متغیر اسپلیت بخش پذیر نباشد یعنی تعدادی کالا اضافه داریم و باید برای آن ها یک صفحه دیگر اضافه شود
                numOfPages++;
            }
            ;
            html.append("<tr>");
            int temp;
            for (int j = 0; j < numOfPages; j++) {
                temp = j + 1;
                html.append("<td onclick=\"cmsProduct.showdevidedProducts(" + split + ", " + temp + ");\">" + temp + "</td>");//?????????????????????????????????????????????????????????????????????
            }
            html.append("</tr>");
            html.append(Js.show("pagingDiv"));
            String html2 = Js.setHtml("#pagingDiv", html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String loadProduct(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String start = jjTools.getParameter(request, "start");
            String end = jjTools.getParameter(request, "end");
            List<Map<String, Object>> allProducts = jjDatabase.separateRow(db.Select(tableName));
            for (int i = Integer.parseInt(start); i < Integer.parseInt(end) && i < allProducts.size(); i++) {
                html.append("<div class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">"
                        + "<div class=\"thumbnail\">"
                        + "<a title = \"" + allProducts.get(i).get(_name) + "\" class=\"fancy fancybox\" href=\"upload/" + allProducts.get(i).get(_pic1).toString() + "\" data-fancybox-group=\"gallery\">"
                        + "<img src=\"upload/" + allProducts.get(i).get(_pic1).toString().replace(".", "_small.") + "\" onclick='cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ") alt=\"" + allProducts.get(i).get(_name) + "\">"
                        + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                        + "</a>"
                        + "<div class=\"caption\">"
                        + "<a href=\"#\" onclick='cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ")'>" + allProducts.get(i).get(_name) + "</a>"
                        + "<p><a class=\"btn btn-default button\" href=\"#\" onclick=\"cmsProduct.m_select(" + allProducts.get(i).get(_id).toString() + ");return false;\">"
                        + "بیشتر بخوانید "
                        + "</a></p>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }

            if (panel.equals("")) {
                panel = "products";
            }
            String html2 = Js.append("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static int numOfProducts(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        List<Map<String, Object>> allProducts = jjDatabase.separateRow(db.Select(tableName));
        return allProducts.size();
    }
//<===============================SHAHSANAEI==============================
//public static void main(String[] args) {
//        long timeNow = System.currentTimeMillis();
//        System.out.println(timeNow);
//    }

    public static String getAllTags(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");

            List<Map<String, Object>> allTags = jjDatabase.separateRow(db.Select("tags"));

            for (int j = 0; j < allTags.size(); j++) {
                html.append("<input type='checkbox' name='tagsCheckBox' value='" + allTags.get(j).get("tags_name") + "'> " + allTags.get(j).get("name") + " </input>");//?????????????????????????????????????????????????????????????????????
            }
            String html2 = Js.setHtml(_tags, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//============ BY RASHIDI ========>

    public static String getSlider2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String lang = jjTools.getLangNum(request);
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            html.append("<ul>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _priority + "=1 AND " + _lang + "=" + jjTools.getLangNum(request)));
            for (int i = 0; i < row.size(); i++) {
                html.append("<li><div dir='" + jjTools.getLangNumDir(request)//برای یکسری زبان ها چپ چین باید بشه یک سری ها راست چین
                        + "' style='overflow: hidden;height:" + height + "px;width:" + width + "px;text-align: "
                        + jjTools.getLangAlign(request)//برای یکسری زبان ها چپ چین باید بشه یک سری ها راست چین
                        + ";'>");
                html.append("<div class='productTitle'>" + row.get(i).get(_name).toString() + "</div>");
                html.append("<div class='productPIC'><img src='upload/" + row.get(i).get(_pic1).toString() + "'/></div>");
                html.append("<div class='productAbstract' >");
                String content = Content.ConvertToWiki(request, row.get(i).get(_abstract).toString().replace("<li>", "").replace("<li dir='rtl'>", "").replace("</li>", ""), db, isPost);
//                html.append(row.get(i).get(_abstract).toString().replace("<li>", "").replace("<li dir='rtl'>", "").replace("</li>", ""));
                html.append(content);
                html.append("</div>");
                html.append("</div><div valign='top' class='productMore' dir='" + jjTools.getLangDir(request)
                        + "' style='text-align: left;vertical-align: top;'>");
//                        + "' style='text-align: left;height:17px;padding:1px;vertical-align: top;'>");
                html.append("<a class='productMoreInSlider' onclick=getOneproduct(" + row.get(i).get(_id) + ");return false>");
                html.append(langSetting.get(0).get(Language._detail));
                html.append("</a></div></li>");
            }
            html.append("</ul>");
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "jjSliderProduct";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript2(panel);
            return html2;
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

    /**
     * این تابع همه ای محصولات را از جدیدترین محصول تا قدیمی ترین محصول را نشان
     * میدهد
     *
     * @param request
     * @param response
     * @param db
     * @param isAjax
     * @return
     * @throws Exception
     */
    public static String getProducts(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            String script = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            String categoryID = "";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
            categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
            String where = "";
            if (categoryID.equals("")) {
                where += _category_id + "=" + categoryProductId;
            } else {
                String[] splitCPID = categoryID.split(",");
                for (int i = 0; i < splitCPID.length; i++) {
                    if (i == splitCPID.length - 1) {
                        where += _category_id + "=" + splitCPID[i];
                    } else {
                        where += _category_id + "=" + splitCPID[i] + " OR ";
                    }
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products;
            if (categoryProductId == 1) {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            } else {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
                script = "$('#sidebar').toggleClass('active');";
            }
            StringBuilder optionHtml = new StringBuilder();
            String categoryGroupId = "";
            int category = categoryProductId;
            while (category > 0) {
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
            StringBuilder html = new StringBuilder();
            html.append("<div class=\"row\" style=\"margin-left: 0px;margin-right: 0px;\" dir=\"ltr\">");
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='productCss'>\n"
                        + "                    <div class='bodyProductCss'>\n"
                        + "                        <div class=\"mainProductCss\"  onclick='getDetailsProduct(" + products.get(i).get(_id) + ");'>\n"
                        + "                            <div class=\"txt-brand-label-pro-info\" style='z-index: 2;'></div>\n"
                        + "                            <div class='imgDivProductCss'>\n"
                        + "                                <img src='upload/" + products.get(i).get(_pic1) + "' width=\"200\" height=\"200\" alt='" + products.get(i).get(_name) + "'/><br>\n"
                        + "                                <span></span>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"card contentProductCss\">\n"
                        + "                                <div class=\"card-body bodyContentProductCss\">"
                        + "                                  <div class=\"mb-0 nameProductCss\">" + products.get(i).get(_name).toString() + "</div>\n"
                        + "                                   <div class=\"rateContentProductCss\">\n"
                        + "                                        <span class='rate'></span>\n"
                        + "                                        <span class='rateNumber'></span>\n"
                        //                        + "                                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                                        <span class='star'></span>\n"
                        + "                                    </div>\n");
                html.append(getPriceProduct(request, db, products, langSetting, i));
                //<======PRICE
                if (products.get(i).get(_quantity).equals("0")) {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\">نا موجود</div>\n");
                } else if (Integer.parseInt(products.get(i).get(_quantity).toString()) < 11) {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\"><i class='far fa-bell'></i>" + products.get(i).get(_quantity) + " عدد از این محصول در انبار وجود دارد</div>\n");
                } else {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\"></div>\n");
                }
                html.append("                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                        <div class=\"footerProduct\">\n"
                        + "                            <div class='footerProductDetails' onclick='addToLikeCart(" + products.get(i).get(_id) + ");'>\n"
                        + "                                <a><i class=\"fal fa-heart\"></i></a>\n"
                        + "                                <div class=\"footerProductPluse\">+</div>\n"
                        + "                            </div>\n"
                        + "                            <div class='footerProductCart' onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'>\n"
                        + "                                <a ><i class=\"fal fa-shopping-cart\"></i></a>\n"
                        + "                                <a ><i class=\"fal fa-bookmark\" style='display: none;'></i></a>\n"
                        + "                                <div class=\"footerProductPluse\">+</div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>");
            }
            html.append("                </div>");
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.setHtml("#hrefCollapse", optionHtml.toString()) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع مشاورانی که در دیتابیس هست را واکشی میکند و به نمایش میگذارد
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getConsulting(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            String categoryID = "";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
            categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
            String where = "";
            if (categoryID.equals("")) {
                where += _category_id + "=" + categoryProductId;
            } else {
                String[] splitCPID = categoryID.split(",");
                for (int i = 0; i < splitCPID.length; i++) {
                    if (i == splitCPID.length - 1) {
                        where += _category_id + "=" + splitCPID[i];
                    } else {
                        where += _category_id + "=" + splitCPID[i] + " OR ";
                    }
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products;
            if (categoryProductId == 1) {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            } else {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            }
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='col-12' style='height: auto;border-bottom: 1px solid #868ba159;overflow: hidden;'>\n"
                        + "    <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;' onclick='getDetailsConsulting(" + products.get(i).get(_id) + ");'>\n"
                        + "        <div class='col-lg-3 col-md-3 col-sm-4 col-xs-4' style='text-align: center;'>\n"
                        + "            <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='trumpet' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span><br/>");
                    html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-9 col-md-9 col-sm-8 col-xs-8' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_val" + j).toString().isEmpty()) {
                        if (!products.get(i).get("account_product_val" + j).toString().equals("0")) {
                            if (products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                                html.append("<h5>" + products.get(i).get("account_product_val" + j).toString() + "</h5>");
                            }
                        }
                    }
                }
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class='row'>\n"
                        + "        <div class='col-10' style='text-align: center;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                }
                html.append("        </div>\n"
                        + "        <div class='col-2' style='text-align: center;padding-bottom: 10px;' onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'><a href='#'><i class='fal fa-check-circle' style='font-size: 25px;padding-top: 10px;color:#000;margin-top: -25px;'></i></a></div>\n"
                        + "    </div>\n"
                        + "</div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع نوبت های انلاین که در دیتابیس هست را واکشی میکند و به نمایش
     * میگذارد
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getOnlineReservationApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            String categoryID = "";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
            categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
            String where = "";
            if (categoryID.equals("")) {
                where += _category_id + "=" + categoryProductId;
            } else {
                String[] splitCPID = categoryID.split(",");
                for (int i = 0; i < splitCPID.length; i++) {
                    if (i == splitCPID.length - 1) {
                        where += _category_id + "=" + splitCPID[i];
                    } else {
                        where += _category_id + "=" + splitCPID[i] + " OR ";
                    }
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products;
            if (categoryProductId == 1) {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + _active + "=1 AND (" + where + ") group by account_product_name ", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            } else {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + _active + "=1 AND (" + where + ") group by account_product_name ", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            }
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='col-12' style='height: auto;border-bottom: 1px solid #868ba159;overflow: hidden;'>\n"
                        + "    <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;' onclick='getDetailsOnlineReservationApp(" + products.get(i).get(_id) + ");'>\n"
                        + "        <div class='col-lg-3 col-md-3 col-sm-4 col-xs-4' style='text-align: center;'>\n"
                        + "            <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='trumpet' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + price + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-9 col-md-9 col-sm-8 col-xs-8' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_val" + j).toString().isEmpty()) {
                        if (!products.get(i).get("account_product_val" + j).toString().equals("0")) {
                            if (products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                                html.append("<h5>" + products.get(i).get("account_product_val" + j).toString() + "</h5>");
                            }
                        }
                    }
                }
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "        <div class='col-12' style='text-align: center;margin-bottom: 5px;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                }
                html.append("        </div>\n"
                        + "</div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * جزئیات نوبت دهی انلاین را نشان میدهد
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getDetailsOnlineReservationApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            Integer id = Integer.parseInt(jjTools.getParameter(request, "id"));
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products = jjDatabase.separateRow(db.Select(Product.tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='tab-top-nav col-12'><div class='row'><div class='col-12'><h1>پروفایل پزشک</h1></div></div></div>");
                html.append("<div class='col-12' style='margin-top: 50px;height: auto;'></div><div class='col-12' style='height: auto;'>"
                        + "                <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;'>"
                        + "                    <div class='col-lg-2 col-md-2 col-sm-3 col-xs-3' style='text-align: center;'>\n"
                        + "                        <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='عکس پزشک' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-10 col-md-10 col-sm-9 col-xs-9' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_val" + j).toString().isEmpty()) {
                        if (!products.get(i).get("account_product_val" + j).toString().equals("0")) {
                            if (products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                                html.append("<h5>" + products.get(i).get("account_product_val" + j).toString() + "</h5>");
                            }
                        }
                    }
                }
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class='row'>\n"
                        + "        <div class='col-12' style='text-align: center;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                }
                html.append("        </div>\n"
                        + "    </div>\n"
                        + "</div>");
                html.append("<div class=\"col-12\" style=\"height: auto;margin-top: 10px;text-align: center;\">\n"
                        + "                <div style=\"font-size: 20px;\" dir=\"ltr\">\n"
                        + "                    <a class=\"rate\" onclick=\"\">\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\" style=\"color: #00000063;\"></i></span><br>\n"
                        + "                        <span style=\"color: #00000063;\" dir=\"rtl\">(125 نظر)</span>\n"
                        + "                    </a>\n"
                        + "                </div>\n"
                        + "            </div>");
                html.append("<div class=\"col-12\" style=\"height: auto;margin-top: 10px;text-align: center;\">\n"
                        + "                <select class='form-control'>");
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _name + "='" + products.get(i).get(_name) + "'"));
                html.append("<option value='0' selected=selected>لطفا روز مورد نظر خود را انتخاب کنید...</option>");
                for (int z = 0; z < row.size(); z++) {
                    html.append("<option value='" + row.get(z).get(_id) + "' onclick='selectOptionDate(" + row.get(z).get(_id) + ");'>" + jjCalendar_IR.getViewFormat_10length(Integer.parseInt(row.get(z).get(_date).toString())) + "</option>");
                }
                html.append("            </select>");
                html.append("</div>");
                html.append("<div class=\"col-12\" style=\"height: auto;margin-top: 10px;text-align: center;\" id='selectOptionDate'>\n");
                html.append("</div>");
                html.append("<div class='card shadow mb-4' style='margin-top:10px;margin-right:10px;margin-left:10px;'>");
                html.append("<div class='card-header py-3 text-primary'>"
                        + "            <div class=\"col-12\" onclick='toggleExplainMore();' >"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-11\" style=\"padding-top: 5px;\">توضیحات بیشتر</div>\n"
                        + "                    <div class=\"col-1\"><i class=\"fad fa-angle-double-down\" style=\"font-size: 20px;padding-top: 5px;\"></i></div>\n"
                        + "                </div>\n"
                        + "            </div>");
                html.append("</div>");
                html.append("<div class='card-body border-left-primary' id='explainMore' style='display:none;'>"
                        + "            <div class=\"col-12\"  style='height: auto;margin-top: 10px;margin-bottom:10px;'>"
                        + "                    <div class=\"col-10\" style=\"padding-bottom: 5px;padding-top: 5px;cursor: pointer;\">" + products.get(i).get(_content) + "</div>\n"
                        + "            </div>"
                        + "            </div>"
                        + "            </div>"
                        + "            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;margin-bottom:10px;\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                    <div class=\"col-10\" style=\"border-radius: 20px;background-color: #37379f;color: #fff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\" onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'>ثبت نوبت پزشک</div>\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                </div>\n"
                        + "            </div>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                        html.append("            <div class=\"col-12\" style=\"height: 40px;border-top: 1px solid #868ba159;\">\n"
                                + "                <div class=\"row\">\n"
                                + "                    <div class=\"col-4\"><h4 style=\"padding-right: 10px;padding-top: 5px;\">" + products.get(i).get("account_product_prop" + j).toString() + "</h4></div>\n"
                                + "                    <div class=\"col-8\"><h4 style=\"padding-top: 5px;\">" + products.get(i).get("account_product_val" + j) + "</h4></div>\n"
                                + "                </div>\n"
                                + "            </div>");
                    }
                }
                html.append("</div>\n"
                        + "        <div class=\"col-12\" style=\"height: 60px;\">\n"
                        + "        </div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + products.get(0).get(_script).toString());
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String selectOptionDate(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> rowGetDate = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _startDate + "='" + rowGetDate.get(0).get(_startDate) + "'"));
            html.append("<select class='form-control'>");
            html.append("<option value='0' selected=selected>ساعت مورد نظر خود را انتخاب کنید...</option>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<option value='" + row.get(i).get(_id) + "' onclick='addToShoppingCart(" + row.get(i).get(_id) + ");'>" + jjCalendar_IR.getViewFormatTim_5length(row.get(i).get(_startTime).toString()) + " تا " + jjCalendar_IR.getViewFormatTim_5length(row.get(i).get(_endTime).toString()) + "</option>");
            }
            html.append("</select>");
            Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()));
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع پذیرش آنلاین که در دیتابیس هست را واکشی میکند و به نمایش میگذارد
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getAcceptOnlineApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
            String categoryID = "";
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
            categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
            String where = "";
            if (categoryID.equals("")) {
                where += _category_id + "=" + categoryProductId;
            } else {
                String[] splitCPID = categoryID.split(",");
                for (int i = 0; i < splitCPID.length; i++) {
                    if (i == splitCPID.length - 1) {
                        where += _category_id + "=" + splitCPID[i];
                    } else {
                        where += _category_id + "=" + splitCPID[i] + " OR ";
                    }
                }
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "swAcceptOnlineApp" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products;
            if (categoryProductId == 1) {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            } else {
                products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
            }
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='col-12' style='height: auto;border-bottom: 1px solid #868ba159;overflow: hidden;'>\n"
                        + "    <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;' onclick='getDetailsAcceptOnlineApp(" + products.get(i).get(_id) + ");'>\n"
                        + "        <div class='col-lg-3 col-md-3 col-sm-4 col-xs-4' style='text-align: center;'>\n"
                        + "            <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='trumpet' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + price + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-9 col-md-9 col-sm-8 col-xs-8' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                html.append("<h5>" + products.get(i).get(_prop1).toString() + " : " + products.get(i).get(_val1) + "</h5>");
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class='row'>\n"
                        + "        <div class='col-10' style='text-align: center;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    if (rowTag.size() > 0) {
                        html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                    }
                }
                html.append("        </div>\n"
                        + "        <div class='col-2' style='text-align: center;padding-bottom: 10px;' onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'><a href='#'><i class='fal fa-check-circle' style='font-size: 25px;padding-top: 10px;color:#000;margin-top: -25px;'></i></a></div>\n"
                        + "    </div>\n"
                        + "</div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getDetailsConsulting(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            Integer id = Integer.parseInt(jjTools.getParameter(request, "id"));
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products = jjDatabase.separateRow(db.Select(Product.tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='tab-top-nav col-12'><div class='row'><div class='col-12'><h1>پروفایل مشاوره</h1></div></div></div>");
                html.append("<div class='col-12' style='margin-top: 50px;height: auto;'></div><div class='col-12' style='height: auto;'>"
                        + "                <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;'>"
                        + "                    <div class='col-lg-2 col-md-2 col-sm-3 col-xs-3' style='text-align: center;'>\n"
                        + "                        <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='عکس مشاور' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-10 col-md-10 col-sm-9 col-xs-9' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_val" + j).toString().isEmpty()) {
                        if (!products.get(i).get("account_product_val" + j).toString().equals("0")) {
                            if (products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                                html.append("<h5>" + products.get(i).get("account_product_val" + j).toString() + "</h5>");
                            }
                        }
                    }
                }
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class='row'>\n"
                        + "        <div class='col-12' style='text-align: center;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                }
                html.append("        </div>\n"
                        + "    </div>\n"
                        + "</div>");
                html.append("<div class=\"col-12\" style=\"height: auto;margin-top: 10px;text-align: center;\">\n"
                        + "                <div style=\"font-size: 20px;\" dir=\"ltr\">\n"
                        + "                    <a class=\"rate\" onclick=\"\">\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                        <span><i class=\"fad fa-star\" style=\"color: #00000063;\"></i></span><br>\n"
                        + "                        <span style=\"color: #00000063;\" dir=\"rtl\">(125 نظر)</span>\n"
                        + "                    </a>\n"
                        + "                </div>\n"
                        + "            </div>");
                //                        + "            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;\">\n"
                //                        + "                <div class=\"row\">\n"
                //                        + "                    <div class=\"col-6\">\n"
                //                        + "                        <div class=\"row\">\n"
                //                        + "                            <div class=\"col-1\"></div>\n"
                //                        + "                            <div class=\"col-10 shadow\" style=\"border-radius: 10px;border: 1px solid #9999ff;color: #9999ff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\">افزودن به علاقه مندی ها</div>\n"
                //                        + "                            <div class=\"col-1\"></div>\n"
                //                        + "                        </div>\n"
                //                        + "                    </div>\n"
                //                        + "                    <div class=\"col-6\">\n"
                //                        + "                        <div class=\"row\">\n"
                //                        + "                            <div class=\"col-1\"></div>\n"
                //                        + "                            <div class=\"col-10 shadow\" style=\"border-radius: 10px;border: 1px solid #9999ff;color: #9999ff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\">دنبال کردن</div>\n"
                //                        + "                            <div class=\"col-1\"></div>\n"
                //                        + "                        </div>\n"
                //                        + "                    </div>\n"
                //                        + "                </div>\n"
                //                        + "            </div>\n"
                html.append("<div class='card shadow mb-4' style='margin-top:10px;margin-right:10px;margin-left:10px;'>");
                html.append("<div class='card-header py-3 text-primary'>"
                        + "            <div class=\"col-12\" onclick='toggleExplainMore();' >"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-11\" style=\"padding-top: 5px;\">توضیحات بیشتر</div>\n"
                        + "                    <div class=\"col-1\"><i class=\"fad fa-angle-double-down\" style=\"font-size: 20px;padding-top: 5px;\"></i></div>\n"
                        + "                </div>\n"
                        + "            </div>");
                html.append("</div>");
                html.append("<div class='card-body border-left-primary' id='explainMore' style='display:none;'>"
                        + "            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;margin-bottom:10px;\">"
                        + "                    <div class=\"col-10\" style=\"padding-bottom: 5px;padding-top: 5px;cursor: pointer;\">" + products.get(i).get(_content) + "</div>\n"
                        + "            </div>"
                        + "            </div>"
                        + "            </div>"
                        + "            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;margin-bottom:10px;\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                    <div class=\"col-10\" style=\"border-radius: 20px;background-color: #37379f;color: #fff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\" onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'>ثبت مشاور</div>\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                </div>\n"
                        + "            </div>");
//                        + "            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;text-align: center;\">\n"
//                        + "                <div class=\"row\">\n"
//                        + "                    <div class=\"col-6\">\n"
//                        + "                        <div class=\"row\">\n"
//                        + "                            <div class=\"col-2\"></div>\n"
//                        + "                            <div class=\"col-9\" style=\"\">1 پست</div>\n"
//                        + "                            <div class=\"col-1\"></div>\n"
//                        + "                        </div>\n"
//                        + "                    </div>\n"
//                        + "                    <div class=\"col-6\">\n"
//                        + "                        <div class=\"row\">\n"
//                        + "                            <div class=\"col-1\"></div>\n"
//                        + "                            <div class=\"col-9\" style=\"\">187 دنبال کننده</div>\n"
//                        + "                            <div class=\"col-2\"></div>\n"
//                        + "                        </div>\n"
//                        + "                    </div>\n"
//                        + "                </div>\n"
//                        + "            </div>\n"
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                        html.append("            <div class=\"col-12\" style=\"height: 40px;border-top: 1px solid #868ba159;\">\n"
                                + "                <div class=\"row\">\n"
                                + "                    <div class=\"col-4\"><h4 style=\"padding-right: 10px;padding-top: 5px;\">" + products.get(i).get("account_product_prop" + j).toString() + "</h4></div>\n"
                                + "                    <div class=\"col-8\"><h4 style=\"padding-top: 5px;\">" + products.get(i).get("account_product_val" + j) + "</h4></div>\n"
                                + "                </div>\n"
                                + "            </div>");
                    }
                }
                //                        + "            <div class=\"col-12\" style=\"height: 40px;border-top: 1px solid #868ba159;\">\n"
                //                        + "                <div class=\"row\">\n"
                //                        + "                    <div class=\"col-10\" style=\"\"><h4 style=\"padding-right: 10px;padding-top: 5px;\">اخرین مطالب</h4></div>\n"
                //                        + "                    <div class=\"col-2\" style=\"text-align: center;\"><a ><i class=\"fad fa-angle-double-left\" style=\"font-size: 30px;padding-top: 5px;color:#000;\"></i></a></div>\n"
                //                        + "                </div>\n"
                //                        + "            </div>\n"
                //                        + "            <div class=\"col-12\" style=\"height: 40px;border-top: 1px solid #868ba159;border-bottom: 1px solid #868ba159;\">\n"
                //                        + "                <div class=\"row\">\n"
                //                        + "                    <div class=\"col-10\" style=\"\"><h4 style=\"padding-right: 10px;padding-top: 5px;\">آخرین نظرات کاربران مشاوره داده شده</h4></div>\n"
                //                        + "                    <div class=\"col-2\" style=\"text-align: center;\"><a ><i class=\"fad fa-angle-double-left\" style=\"font-size: 30px;padding-top: 5px;color:#000;\"></i></a></div>\n"
                //                        + "                </div>\n"
                //                        + "            </div>\n"
                html.append("            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                    <div class=\"col-10\" style=\"border-radius: 20px;background-color: red;color: #fff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\">گزارش تخلف</div>\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div class=\"col-12\" style=\"height: 60px;\">\n"
                        + "        </div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + products.get(0).get(_script).toString());
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع برای دیدن جزئیات پذیرش آنلاین استفاده میشود
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getDetailsAcceptOnlineApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String script = "";
            Integer id = Integer.parseInt(jjTools.getParameter(request, "id"));
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            System.out.println(panel);
            List<Map<String, Object>> products = jjDatabase.separateRow(db.Select(Product.tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='tab-top-nav col-12'><div class='row'><div class='col-12'><h1>پروفایل پزشک</h1></div></div></div>");
                html.append("<div class='col-12' style='margin-top: 50px;height: auto;'></div><div class='col-12' style='height: auto;'>"
                        + "                <div class='row' style='margin-left: 0px;margin-right: 0px;background-color: #fff;'>"
                        + "                    <div class='col-lg-2 col-md-2 col-sm-3 col-xs-3' style='text-align: center;'>\n"
                        + "                        <img src='upload/" + products.get(i).get(_pic1) + "' width='70' height='70' alt='عکس پزشک' style='border-radius: 100%;margin-top: 10px;margin-bottom: 10px;'/><br>");
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(products.get(i).get(_discount).toString())) {
                        } else {
                            discountPrice = products.get(i).get(_discount).toString();
                        }
                    }
                    price = products.get(i).get(_price2).toString();
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        System.out.println("userRow:" + userRow.size());
                        price = products.get(i).get(_price2).toString();
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                                String price1 = products.get(i).get(_groupPrice1).toString();
                                if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                                    price1 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                                    price = price1;
                                    discountPrice = price1;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                                String price2 = products.get(i).get(_groupPrice2).toString();
                                if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                                    price2 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                    price = price2;
                                    discountPrice = price2;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                                String price3 = products.get(i).get(_groupPrice3).toString();
                                if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                                    price3 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                    price = price3;
                                    discountPrice = price3;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                                String price4 = products.get(i).get(_groupPrice4).toString();
                                if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                                    price4 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                    price = price4;
                                    discountPrice = price4;
                                }
                            }
                            if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                                String price5 = products.get(i).get(_groupPrice5).toString();
                                if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                                    price5 = products.get(i).get(_price2).toString();
                                }
                                if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                    price = price5;
                                    discountPrice = price5;
                                }
                            }
                        }
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                String price6 = products.get(i).get(_discount).toString();
                                if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                    price = price6;
                                    discountPrice = price6;
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(products.get(i).get(_discount).toString())) {
                            } else {
                                discountPrice = products.get(i).get(_discount).toString();
                            }
                        }
                        price = products.get(i).get(_price2).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
                System.out.println("discountPrice:" + discountPrice);
                if (products.get(i).get(_quantity).equals("0")) {
                } else {
                    if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                        html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                        html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                    } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                        //محاسبه زمان باقی مانده تا پایان تخفیف
                        jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                        int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                        ServerLog.Print("remain day : " + remainTime);

                        if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                remainHours = remainHours + (remainTime * 24);
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                            }
                        } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                int remainHours = 0, remainMinutes = 0;
                                String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                                ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                                remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                                remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                                if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span><br/>");
                                } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                                    html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + price + products.get(i).get(_currency) + "</span>");
                                    html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                                }
                            }
                        } else {
                            if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span><br/>");
                                html.append("<span style='color: #fff;background-color: #E61D0F;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</span>");
                            } else {
                                html.append("<span style='color: #fff;background-color: #11a31ceb;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</span>");
                                html.append("<span style='color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'></span>");
                            }
                        }
                    }
                }
                html.append("</div>\n"
                        + "        <div class='card promoting-card col-lg-10 col-md-10 col-sm-9 col-xs-9' style='border: 0px;'>\n"
                        + "            <div class='card-body d-flex flex-row mr-0' style='padding-bottom: 0px;'>\n"
                        + "                <div>\n"
                        + "                    <h4 class='card-title font-weight-bold mb-0'>" + products.get(i).get(_name) + "</h4>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class='card-body' style='padding-top: 0px;'>\n"
                        + "                <div class='collapse-content' style='padding-top: 0px;margin-top: 0px;'>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_val" + j).toString().isEmpty()) {
                        if (!products.get(i).get("account_product_val" + j).toString().equals("0")) {
                            if (products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                                html.append("<h5>" + products.get(i).get("account_product_val" + j).toString() + "</h5>");
                            }
                        }
                    }
                }
                html.append("                </div>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class='row'>\n"
                        + "        <div class='col-12' style='text-align: center;'>");
                String[] tag = products.get(i).get(_tags).toString().split(",");
                for (int j = 0; j < tag.length; j++) {
                    List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + tag[j]));
                    if (rowTag.size() > 0) {
                        html.append("<span style='background-color: #9999ff;color: #fff;padding: 3px;border-radius: 10px;padding-left: 6px;padding-right: 6px;'>" + rowTag.get(0).get(Tags._name) + "</span>&nbsp;");
                    }
                }
                html.append("        </div>\n"
                        + "    </div>\n"
                        + "</div>");
                html.append("            <div class=\"col-12\" style=\"height: auto;margin-top: 10px;margin-bottom:10px;\">\n"
                        + "                <div class=\"row\">\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                    <div class=\"col-10\" style=\"border-radius: 20px;background-color: #37379f;color: #fff;text-align: center;padding-bottom: 5px;padding-top: 5px;cursor: pointer;\" onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'>ثبت پذیرش</div>\n"
                        + "                    <div class=\"col-1\"></div>\n"
                        + "                </div>\n"
                        + "            </div>");
                for (int j = 1; j < 21; j++) {
                    if (!products.get(i).get("account_product_prop" + j).toString().isEmpty()) {
                        html.append("            <div class=\"col-12\" style=\"height: 40px;border-top: 1px solid #868ba159;\">\n"
                                + "                <div class=\"row\">\n"
                                + "                    <div class=\"col-4\"><h4 style=\"padding-right: 10px;padding-top: 5px;\">" + products.get(i).get("account_product_prop" + j).toString() + "</h4></div>\n"
                                + "                    <div class=\"col-8\"><h4 style=\"padding-top: 5px;\">" + products.get(i).get("account_product_val" + j) + "</h4></div>\n"
                                + "                </div>\n"
                                + "            </div>");
                    }
                }
                html.append("        </div>\n"
                        + "        <div class=\"col-12\" style=\"height: 60px;\">\n"
                        + "        </div>");
            }
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + products.get(0).get(_script).toString());
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            panel = (panel.equals("") ? "swTitle" : panel);
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (row.size() > 0) {
                    StringBuilder html = new StringBuilder();

                    List<Map<String, Object>> rowParent = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + row.get(0).get(_category_id)));
                    String persianTitle = "Unknown";
                    if (rowParent.size() > 0) {
                        persianTitle = rowParent.get(0).get(Category_Product._title).toString();
//                        if (!jjTools.isLangFa(request)) {
//                            productCategory1 = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._parent + "=" + row.get(0).get(_category_id)));
//                        }
                        html.append("<a onclick='swGetProducts();' class='productLink'>");
                        html.append(langSetting.get(0).get(Language._product));
                        if (rowParent.size() > 0) {
                            html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetProducts("
                                    + row.get(0).get(_category_id) + ");' class='productLink'>");
                            html.append(rowParent.get(0).get(Category_Product._title));
                        } else {
                            html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetProducts("
                                    + row.get(0).get(_category_id)
                                    + ");' class='productLink'>");
                            html.append(persianTitle);
                        }
                    }
                    html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><span class='productLink'>");
                    html.append(row.get(0).get(_name).toString() + "</span><br/>");
//                    html.append(row.get(0).get(_name).toString() + "</span><br/><br/><span class='productContent'>" + row.get(0).get(_content).toString() + "</span>");
                    return Js.setHtml("#" + panel, html.toString());
                }
            }
            String errorMessage = "رکوردی با این محتوا وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "wiki Text Product Fail;";
            }
            return Js.setHtml("#" + panel, errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getTitle(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, id + "=" + id));
            return row.get(0).get(_name).toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOneProductSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String lang = jjTools.getSessionAttribute(request, "myLang") == "" ? "1" : jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            //<============BY RASHIDI ========
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuilder html2 = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = "";
            //============ BY RASHIDI ========>
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (row.isEmpty()) {
                    String errorMessage = "رکوردی با این کد وجود ندارد.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Select Fail;";
                    }
                    return Js.dialog(errorMessage);
                }
                if (row.size() > 0) {
                    /////***********توجه*************
                    //به جای این چند خط کد باید یک کوئری اسکیوال جایگزین شود که کتگوری های مد نظر را بگیرد.---->
                    List<Map<String, Object>> allCategoryProduct = jjDatabase.separateRow(db.Select(Category_Product.tableName));
                    String currentCategory = row.get(0).get(_category_id).toString();
                    List<String> categoryNames = new ArrayList<String>();
                    List<String> categoryIds = new ArrayList<String>();
                    for (int i = 0; i < allCategoryProduct.size(); i++) {
                        if (allCategoryProduct.get(i).get(Category_Product._id).toString().equals(currentCategory)) {
                            categoryNames.add(allCategoryProduct.get(i).get(Category_Product._title).toString());
                            categoryIds.add(allCategoryProduct.get(i).get(Category_Product._id).toString());
                            currentCategory = allCategoryProduct.get(i).get(Category_Product._level).toString();
                        }
                    }
                    html.append("<div class='card shadow mb-4' style='margin-top:10px;border:0px;direction:rtl;'>");
                    html.append("<div class='card-header py-3 text-primary' style='text-align:right;border:0px;'>");
                    html.append("<a style='cursor: pointer;' class='productLink'>");
                    html.append(langSetting.get(0).get(Language._product));
                    for (int i = categoryNames.size() - 1; i >= 0; i--) {
                        html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><a style='cursor: pointer;' class='productLink'>");
                        html.append(categoryNames.get(i));
                    }
                    html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><span class='productLink'>");
                    html.append(row.get(0).get(_name).toString() + "</span>");
                    html.append("</div>");
                    html.append("<div class='card-body' style='border:0px;'>");
                    html.append("<div class='oneProductDiv'>");
                    boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
                    html.append("<div class='row'>");
                    String src1 = row.get(0).get(_pic1).toString();
                    String src2 = row.get(0).get(_pic2).toString();
                    String src3 = row.get(0).get(_pic3).toString();
                    String src4 = row.get(0).get(_pic4).toString();
                    String src5 = row.get(0).get(_pic5).toString();
                    String src6 = row.get(0).get(_pic6).toString();
                    String src_ext = row.get(0).get(_pic_ext).toString();
                    String smalPicSrc = "";
                    html.append("<div class='col-lg-6'><div class='col-12' style='margin-top:30px;margin-bottom:30px;'><img id='changeSrcInGetDetailProduct' src='upload/" + row.get(0).get(_pic1) + "' style='display:block;margin:auto;width:80%;height:auto;'></div>"
                            + "<div class='row'>");

                    if (!src1.equalsIgnoreCase("")) {
                        smalPicSrc = src1.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img1' src='upload/" + row.get(0).get(_pic1) + "' onclick='changeSrc1();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src2.equalsIgnoreCase("")) {
                        smalPicSrc = src2.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img2' src='upload/" + row.get(0).get(_pic2) + "' onclick='changeSrc2();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src3.equalsIgnoreCase("")) {
                        smalPicSrc = src3.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img3' src='upload/" + row.get(0).get(_pic3) + "' onclick='changeSrc3();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src4.equalsIgnoreCase("")) {
                        smalPicSrc = src4.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img4' src='upload/" + row.get(0).get(_pic4) + "' onclick='changeSrc4();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src5.equalsIgnoreCase("")) {
                        smalPicSrc = src5.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img5' src='upload/" + row.get(0).get(_pic5) + "' onclick='changeSrc5();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src6.equalsIgnoreCase("")) {
                        smalPicSrc = src6.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img6' src='upload/" + row.get(0).get(_pic6) + "' onclick='changeSrc6();' style='width:100%;height:100px;cursor: pointer;'></div>");
                    }
                    if (!src_ext.equalsIgnoreCase("")) {
                        smalPicSrc = src_ext.replace(".", "_small.");
                        html.append("<img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' />");
                    }
                    html.append("</div></div>");
                    html.append("<div class='col-lg-6 card shadow mb-4' style='margin-top:10px;text-align:right;border:0px;'>");
                    html.append("<div class='card-header py-3 text-primary' style='border:0px;'>");
                    html.append("<span class='productTitlespan'><h3>" + row.get(0).get(_name).toString() + "</h3></span>");
                    html.append("</div>");
                    html.append("<div class='card-body border-left-primary' style='direction:rtl;border:0px;'>");
                    html.append("<div class='productabstarctDiv'>");
                    html.append("<div>");
                    for (int j = 1; j <= 20; j++) {
                        String prop = "account_product_prop" + String.valueOf(j);
                        String val = "account_product_val" + String.valueOf(j);
                        if ((row.get(0).get(val) != null) && (!row.get(0).get(val).toString().equals("")) && (row.get(0).get(prop) != null) && (!row.get(0).get(prop).toString().equals(""))) {
                            html.append("<div class='productPropVal'><span class='productProp'>" + row.get(0).get(prop).toString() + "</span> : <span class='productVal'>" + row.get(0).get(val).toString() + "</span></div>");
                        }
                    }
                    html.append("</div>");
                    html.append("</div>");
                    html.append("<div class='productExDetail'>");

                    int quantity = new Integer(row.get(0).get(_quantity).toString());
                    if (quantity <= 0) {
                        html.append("<div class='productQtyDiv'>  <span style='color:red;'>" + langSetting.get(0).get(Language._notAvailable) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
                    } else {
                        html.append("<div class='productQtyDiv'>  <span>" + langSetting.get(0).get(Language._available) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
                    }
                    html.append("</div>");
//                    html.append("<div class='productContentDiv'>" + Content.ConvertToWiki(request, row.get(0).get(_content).toString().replace('"', '\''), db, needString) + "</div>");//====== BY RASHIDI ======
                    html.append("<div class='productContentDiv'>" + row.get(0).get(_content).toString() + "</div>");//====== BY RASHIDI ======
                    html.append("</div></div>");
                    html.append("</div></div>");
                    html.append("<div class='row'>");
                    html.append("<div class='col-lg-6' style='margin-top:10px;margin-bottom:10px;'>");
                    html.append("<a href='#' onclick='addToShoppingCart(" + row.get(0).get(_id).toString() + ");' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'><i class='fas fa-cart-plus'></i></span><span class='text' style='width: 100%;'>" + langSetting.get(0).get(Language._addCart) + "</span></a>");
                    html.append("</div>");
                    html.append("<div class='col-lg-6' style='margin-top:10px;margin-bottom:10px;'>");
                    html.append("<a href='#' onclick='addToShoppingCart(" + row.get(0).get(_id).toString() + ");' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'></span><span class='text' style='width: 100%;'>ادامه خرید</span></a>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    html2.append("$('#" + panel + "').html(\"" + html.toString() + "\");");
                }

                //============ BY RASHIDI ========>   
                title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, row.get(0).get(_name).toString());
                if (needString) {
                    return html.toString();
                }
            }
            Server.outPrinter(request, response, html2.toString() + title);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * این تابع برای دیدن جزئیات یک محصول استفاده میشود
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String getOneProductApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String lang = jjTools.getSessionAttribute(request, "myLang") == "" ? "1" : jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            //<============BY RASHIDI ========
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuilder html2 = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = "";
            //============ BY RASHIDI ========>
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (row.isEmpty()) {
                    String errorMessage = "رکوردی با این کد وجود ندارد.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Select Fail;";
                    }
                    return Js.dialog(errorMessage);
                }
                if (row.size() > 0) {
                    /////***********توجه*************
                    //به جای این چند خط کد باید یک کوئری اسکیوال جایگزین شود که کتگوری های مد نظر را بگیرد.---->
                    List<Map<String, Object>> allCategoryProduct = jjDatabase.separateRow(db.Select(Category_Product.tableName));
                    String currentCategory = row.get(0).get(_category_id).toString();
                    List<String> categoryNames = new ArrayList<String>();
                    List<String> categoryIds = new ArrayList<String>();
                    for (int i = 0; i < allCategoryProduct.size(); i++) {
                        if (allCategoryProduct.get(i).get(Category_Product._id).toString().equals(currentCategory)) {
                            categoryNames.add(allCategoryProduct.get(i).get(Category_Product._title).toString());
                            categoryIds.add(allCategoryProduct.get(i).get(Category_Product._id).toString());
                            currentCategory = allCategoryProduct.get(i).get(Category_Product._level).toString();
                        }
                    }
                    html.append("<div class='card shadow mb-4'>");
                    html.append("<div class='card-header py-3 text-primary'>");
                    html.append("<a style='cursor: pointer;' class='productLink'>");
                    html.append(langSetting.get(0).get(Language._product));
                    for (int i = categoryNames.size() - 1; i >= 0; i--) {
                        html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><a style='cursor: pointer;' class='productLink'>");
                        html.append(categoryNames.get(i));
                    }
                    html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><span class='productLink'>");
                    html.append(row.get(0).get(_name).toString() + "</span>");
                    html.append("</div>");
                    html.append("<div class='card-body'>");
                    html.append("<div class='oneProductDiv'>");
                    boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
                    html.append("<div>");
                    String src1 = row.get(0).get(_pic1).toString();
                    String src2 = row.get(0).get(_pic2).toString();
                    String src3 = row.get(0).get(_pic3).toString();
                    String src4 = row.get(0).get(_pic4).toString();
                    String src5 = row.get(0).get(_pic5).toString();
                    String src6 = row.get(0).get(_pic6).toString();
                    String src_ext = row.get(0).get(_pic_ext).toString();
                    String smalPicSrc = "";
                    html.append("<div class='col-12 row' style='margin-left: 0px;margin-right: 0px;' dir='rtl'><div class='col-lg-4 col-md-4 col-sm-6 col-xs-12'><div class='col-12' style='margin-top:30px;margin-bottom:30px;'><img id='changeSrcInGetDetailProduct' src='upload/" + row.get(0).get(_pic1) + "' style='display:block;margin:auto;width:80%;height:auto;'></div>"
                            + "<div class='row'>");

                    if (!src1.equalsIgnoreCase("")) {
                        smalPicSrc = src1.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img1' src='upload/" + row.get(0).get(_pic1) + "' onclick='changeSrc1();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src2.equalsIgnoreCase("")) {
                        smalPicSrc = src2.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img2' src='upload/" + row.get(0).get(_pic2) + "' onclick='changeSrc2();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src3.equalsIgnoreCase("")) {
                        smalPicSrc = src3.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img3' src='upload/" + row.get(0).get(_pic3) + "' onclick='changeSrc3();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src4.equalsIgnoreCase("")) {
                        smalPicSrc = src4.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img4' src='upload/" + row.get(0).get(_pic4) + "' onclick='changeSrc4();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src5.equalsIgnoreCase("")) {
                        smalPicSrc = src5.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img5' src='upload/" + row.get(0).get(_pic5) + "' onclick='changeSrc5();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src6.equalsIgnoreCase("")) {
                        smalPicSrc = src6.replace(".", "_small.");
                        html.append("<div class='col-2'><img id='img6' src='upload/" + row.get(0).get(_pic6) + "' onclick='changeSrc6();' style='width:100%;height:100px;'></div>");
                    }
                    if (!src_ext.equalsIgnoreCase("")) {
                        smalPicSrc = src_ext.replace(".", "_small.");
                        html.append("<img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' />");
                    }
                    html.append("</div></div></div>");
                    html.append("<div class='card shadow mb-4' style='margin-top:10px;'>");
                    html.append("<div class='card-header py-3 text-primary'>");
                    html.append("<span class='productTitlespan'><h3>" + row.get(0).get(_name).toString() + "</h3></span>");
                    html.append("</div>");
                    html.append("<div class='card-body border-left-primary'>");
                    html.append("<div class='productabstarctDiv'><h4>" + row.get(0).get(_page).toString());
                    html.append("<div>");
                    for (int j = 1; j <= 20; j++) {
                        String prop = "account_product_prop" + String.valueOf(j);
                        String val = "account_product_val" + String.valueOf(j);
                        if ((row.get(0).get(val) != null) && (!row.get(0).get(val).toString().equals("")) && (row.get(0).get(prop) != null) && (!row.get(0).get(prop).toString().equals(""))) {
                            html.append("<div class='productPropVal'><span class='productProp'>" + row.get(0).get(prop).toString() + "</span> : <span class='productVal'>" + row.get(0).get(val).toString() + "</span></div>");
                        }
                    }
                    html.append("</div>");
                    html.append("</h4></div>");
                    html.append("<div class='productExDetail'>");

                    int quantity = new Integer(row.get(0).get(_quantity).toString());
                    if (quantity <= 0) {
                        html.append("<div class='productQtyDiv'>  <span style='color:red;'>" + langSetting.get(0).get(Language._notAvailable) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
                    } else {
                        html.append("<div class='productQtyDiv'>  <span>" + langSetting.get(0).get(Language._available) + "</span></div>");//==> productQtyDivLn : productlQuantityDiv
                    }
                    html.append("</div>");
//                    html.append("<div class='productContentDiv'>" + Content.ConvertToWiki(request, row.get(0).get(_content).toString().replace('"', '\''), db, needString) + "</div>");//====== BY RASHIDI ======
                    html.append("<div class='productContentDiv'>" + row.get(0).get(_content).toString() + "</div>");//====== BY RASHIDI ======
                    html.append("</div></div>");
                    html.append("</div></div>");
                    html.append("<div style='margin-top:10px;margin-bottom:10px;'>");
                    html.append("<a href='#' onclick='addToShoppingCart(" + row.get(0).get(_id).toString() + ");' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'><i class='fas fa-cart-plus'></i></span><span class='text' style='width: 100%;'>" + langSetting.get(0).get(Language._addCart) + "</span></a>");
                    html.append("</div>");
                    html.append("<div style='margin-top: 60px;'></div>");
                    html.append("</div>");
                    html.append("</div>");
                    html.append("</div>");
                    html2.append("$('#" + panel + "').html(\"" + html.toString() + "\");");
                }

                //============ BY RASHIDI ========>   
                title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, row.get(0).get(_name).toString());
                if (needString) {
                    return html.toString();
                }
            }
            Server.outPrinter(request, response, html2.toString() + title);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOneProductForCategory(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            //============ BY RASHIDI ========>
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            String lang = jjTools.getSessionAttribute(request, "myLang") == "" ? "1" : jjTools.getSessionAttribute(request, "myLang");
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
            //<============BY RASHIDI ========
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuilder html2 = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            String titlePanel = jjTools.getParameter(request, "title");
            String title = "";
            //============ BY RASHIDI ========>
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (row.isEmpty()) {
                    String errorMessage = "رکوردی با این کد وجود ندارد.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Select Fail;";
                    }
                    return Js.dialog(errorMessage);
                }
                if (row.size() > 0) {
                    /////***********توجه*************
                    //به جای این چند خط کد باید یک کوئری اسکیوال جایگزین شود که کتگوری های مد نظر را بگیرد.---->
                    List<Map<String, Object>> allCategoryProduct = jjDatabase.separateRow(db.Select(Category_Product.tableName));
                    String currentCategory = row.get(0).get(_category_id).toString();
                    List<String> categoryNames = new ArrayList<String>();
                    List<String> categoryIds = new ArrayList<String>();
                    for (int i = 0; i < allCategoryProduct.size(); i++) {
                        if (allCategoryProduct.get(i).get(Category_Product._id).toString().equals(currentCategory)) {
                            categoryNames.add(allCategoryProduct.get(i).get(Category_Product._title).toString());
                            categoryIds.add(allCategoryProduct.get(i).get(Category_Product._id).toString());
                            currentCategory = allCategoryProduct.get(i).get(Category_Product._level).toString();
                        }
                    }

                    html.append("<div class='roww'>");
                    html.append("<div class='oneProductDiv'><div class='titleDiv'>");
                    for (int i = categoryNames.size() - 1; i >= 0; i--) {
                        html.append("<a style='cursor: pointer;' onclick='getCategoryProduct("
                                + categoryIds.get(i) + ");' class='productLink'>");
                        html.append(categoryNames.get(i));
                    }
                    html.append("</a><span class='productLinkFlash'>&nbsp;>&nbsp;</span><span class='productLink'>");
                    html.append(row.get(0).get(_name).toString() + "</span><br/></div>");
                    boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
                    html.append("<div>");
                    //============ BY RASHIDI ========>
                    if (lang.equals("1")) {
                        html.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
                    } else {
                        jjCalendar_IR irDate = new jjCalendar_IR(row.get(0).get(_date).toString());
                        jjCalendar_EN enDate = new jjCalendar_EN();
                        enDate = irDate.convertPersianToGregorian();
                        html.append("<span class='productDatespan'>" + enDate.getViewFormat_10length() + "</span>");
                    }
                    html.append("<div class='productPicDiv'>");
                    String src1 = row.get(0).get(_pic1).toString();
                    String src2 = row.get(0).get(_pic2).toString();
                    String src3 = row.get(0).get(_pic3).toString();
                    String src4 = row.get(0).get(_pic4).toString();
                    String src5 = row.get(0).get(_pic5).toString();
                    String src6 = row.get(0).get(_pic6).toString();
                    String src_ext = row.get(0).get(_pic_ext).toString();
                    String smalPicSrc = "";
//                    smalPicSrc = src1.replace(".", "_small.");//select small pic
                    html.append("<a class='productExtraLink' href='upload/" + src1 + "'><img class='mainPic productPic' src='upload/" + src1 + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "'/></a>");
                    html.append("<div class='productExtraDiv'>");

                    if (!src1.equalsIgnoreCase("")) {
                        smalPicSrc = src1.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src1 + "'> <img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "'/></a>");
                    }
                    if (!src2.equalsIgnoreCase("")) {
                        smalPicSrc = src2.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src2 + "'><img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a>");
                    }
                    if (!src3.equalsIgnoreCase("")) {
                        smalPicSrc = src3.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src3 + "'><img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a>");
                    }
                    if (!src4.equalsIgnoreCase("")) {
                        smalPicSrc = src4.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src4 + "'><img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a>");
                    }
                    if (!src5.equalsIgnoreCase("")) {
                        smalPicSrc = src5.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src5 + "'><img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a>");
                    }
                    if (!src6.equalsIgnoreCase("")) {
                        smalPicSrc = src6.replace(".", "_small.");
                        html.append("<a onclick='changeProductPic(this);return false;' class='productPicLink' href='upload/" + src6 + "'><img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' /></a>");
                    }
                    if (!src_ext.equalsIgnoreCase("")) {
                        smalPicSrc = src_ext.replace(".", "_small.");
                        html.append("<img class='productPic' src='upload/" + smalPicSrc + "' alt='" + row.get(0).get(_name).toString().replace("'", "").replace("\"", "") + "' />");
                    }
                    html.append("</div></div>");
                    html.append("<span class='productTitlespan'><h3>" + row.get(0).get(_name).toString() + "</h3></span>");
                    html.append("<div class='productabstarctDiv'><h4>" + row.get(0).get(_page).toString());
                    html.append("<div>");
                    for (int j = 1; j <= 20; j++) {
                        String prop = "account_product_prop" + String.valueOf(j);
                        String val = "account_product_val" + String.valueOf(j);
                        if ((row.get(0).get(val) != null) && (!row.get(0).get(val).toString().equals("")) && (row.get(0).get(prop) != null) && (!row.get(0).get(prop).toString().equals(""))) {
                            html.append("<div class='productPropVal'><span class='productProp'>" + row.get(0).get(prop).toString() + "</span> : <span class='productVal'>" + row.get(0).get(val).toString() + "</span></div>");
                        }
                    }
                    html.append("</div>");
                    html.append("</h4></div>");
                    html.append("<div class='productContentDiv'>" + row.get(0).get(_content).toString() + "</div>");//====== BY RASHIDI ======
                    html.append("</div>");
                    html.append("<div class='ProductdetailBtn' style='margin-bottom: 50px;'>");
                    html.append("<a class='coGruopproduct' onclick='showFactor(" + row.get(0).get(_id).toString() + ");'><span style='font-size:17px;'>مشاهده فاکتور و پرداخت</span></a>");
                    html.append("<a class='moreDetaile' href='Server?do=Product.getList&panel=sw&id=" + row.get(0).get(_category_id).toString() + "' onclick='swGetProducts(" + row.get(0).get(_category_id).toString() + ");return false;'><span>" + langSetting.get(0).get(Language._related) + "</span></a>");
                    html.append("</div></div></div>");
                    html.append("<div class='roww' id='swFactorDiv'>");
                    html.append("</div>");
                    html2.append("$('html, body').animate({scrollTop: $('#" + panel + "').offset().top -100}, 500);");
                    html2.append("$('#" + panel + "').html(\"" + html.toString() + "\");");
                }

                //============ BY RASHIDI ========>   
                title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, row.get(0).get(_name).toString());
                if (needString) {
                    return html.toString();
                }
            }
            Server.outPrinter(request, response, html2.toString() + title);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return Server.ErrorHandler(ex);
        }
    }
//<============ BY HUSSAINI ========
    //این قسمت برای گرفتن گروه های کاربری برای 

    public static String selectOptionGroupUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        String creator = jjTools.getSessionAttribute(request, "#ID");
        List<Map<String, Object>> row;
        row = jjDatabase.separateRow(db.Select(Access_Group.tableName));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "selectOptionGroupUser" : panel;
        html.append("<option value='0' selected='selected'>لطفا گروه های کاربری خود را انتخاب کنید</option>");
        if (row.size() > 0) {
            for (int i = 0; i < row.size(); i++) {
                html.append("<option value='" + row.get(i).get(Access_Group._id) + "'>"
                        + row.get(i).get(Access_Group._title)
                        + "</option>");
            }
        }
        Server.outPrinter(request, response, Js.setHtml("." + panel, html));
        return "";

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
                String ti = catchContentTitle.get(i).get(_name).toString();
                Elements els = doc.body().select("* :not(a)");
                for (Element e : els) {
                    List<TextNode> tnList = e.textNodes();
                    for (TextNode tn : tnList) {
                        String orig = tn.text();
                        if (ti.length() > 2) {//اگر طول کاراکتر ها کوچک باشد اتوویکی نمی شود
                            tn.text(orig.replaceAll(ti, "<a href='Server?do=Content.sw&panel=" + panel + "&text=" + ti
                                    + "' onclick='sw(" + catchContentTitle.get(i).get(_id) + ");return false;' class='p jjLink hoverAutoWiki'>"
                                    + ti + "</a>"));
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

    public static void resetCatchContentTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        ServerLog.Print("===>>>>>resetCatchContentTitle" + context.getContextPath() + context.getServerInfo());
        String lang = "1";// زبان را باید دقت کنیم بعدا درست باشد یعنی برای هر زبان یک متغیر در اسکوپ اپلیکیشن می خواهیم
//        context.setAttribute("catchContentTitle", jjDatabase.separateRow(db.Select(tableName, _lang + " = " + lang + " AND " + _hasLink + " =1")));
        context.setAttribute("catchContentTitle", jjDatabase.separateRow(db.Select(tableName, _name + "," + _id, _hasLink + "=1", "CHAR_LENGTH(" + _name + ")DESC")));
    }

    public static void resetCatchProductTitle(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isFromClient) {
        ServletContext context = request.getSession().getServletContext();//ست کردن متغیر هایی در اسکوپ اپلیکیشن برای اینکه همه کاربر ها بتوانند از این متغیر ها استفاده کنند
        ServerLog.Print("===>>>>>resetCatchProductTitle" + context.getContextPath() + context.getServerInfo());
        String lang = "1";// زبان را باید دقت کنیم بعدا درست باشد یعنی برای هر زبان یک متغیر در اسکوپ اپلیکیشن می خواهیم
        context.setAttribute("catchProductTitle", jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + " = " + lang + " AND " + Product._hasLink + " =1")));
    }

    /**
     * این تابع برای گرفتن قیمت محصول است اگر تخفیفی داشت در ان اعمال میشود
     * getPriceProduct(HttpServletRequest request, jjDatabaseWeb db,
     * List<Map<String, Object>> products(محصولات را بر میگرداند), int i(شمارنده
     * for میباشد))
     *
     * @param request
     * @param db
     * @param products کل محصول را میگیریم
     * @param langSetting
     * @param i این محصول در چه سطری وجود دارد
     * @return
     *
     * @throws Exception
     */
    public static String getPriceProduct(HttpServletRequest request, jjDatabaseWeb db, List<Map<String, Object>> products, List<Map<String, Object>> langSetting, int i) throws Exception {
        String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
        jjCalendar_IR date = new jjCalendar_IR();
        String price = "";
        String discountPrice = "";
        StringBuilder html = new StringBuilder();
        System.out.println("//////////////////user id///////////////////" + userId);
        if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
            if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                if ("".equals(products.get(i).get(_discount).toString())) {
                } else {
                    discountPrice = products.get(i).get(_discount).toString();
                }
            }
            price = products.get(i).get(_price2).toString();
        } else {//اگر کاربر لاگین کرده باشد
            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

            if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                System.out.println("userRow:" + userRow.size());
                price = products.get(i).get(_price2).toString();
                for (int j = 0; j < userRow.size(); j++) {
                    String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                        String price1 = products.get(i).get(_groupPrice1).toString();
                        if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                            price1 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                            price = price1;
                            discountPrice = price1;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                        String price2 = products.get(i).get(_groupPrice2).toString();
                        if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                            price2 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                            price = price2;
                            discountPrice = price2;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                        String price3 = products.get(i).get(_groupPrice3).toString();
                        if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                            price3 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                            price = price3;
                            discountPrice = price3;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                        String price4 = products.get(i).get(_groupPrice4).toString();
                        if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                            price4 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                            price = price4;
                            discountPrice = price4;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                        String price5 = products.get(i).get(_groupPrice5).toString();
                        if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                            price5 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                            price = price5;
                            discountPrice = price5;
                        }
                    }
                }
                if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(products.get(i).get(_discount).toString())) {
                    } else {
                        String price6 = products.get(i).get(_discount).toString();
                        if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                            price = price6;
                            discountPrice = price6;
                        }
                    }
                }
            } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(products.get(i).get(_discount).toString())) {
                    } else {
                        discountPrice = products.get(i).get(_discount).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
            }
        }
        price = products.get(i).get(_price2).toString();
        System.out.println("discountPrice:" + discountPrice);
        if (products.get(i).get(_quantity).equals("0")) {
            html.append("<div class='mb-0 priceNoDiscountContectProductCss'></div>");
            html.append("<div class='mb-0 discountPriceContectProductCss'></div>");
            html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
        } else {
            if (discountPrice.equalsIgnoreCase("")) {//اگر کالا تخفیف نداشت یا کاربر عضو گروهی بود فقط قیمت اصلی نمایش داده می شود
                html.append("<div class='mb-0 priceNoDiscountContectProductCss'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                html.append("<div class='mb-0 discountPriceContectProductCss'></div>");
                html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
            } else {//اگر کالا تخفیف داشت  قیمت اصلی و  قیمت تخفیف و زمان باقی مانده تا اتمام تخفیف نمایش داده می شود
                //محاسبه زمان باقی مانده تا پایان تخفیف
                jjCalendar_IR dateIR = new jjCalendar_IR();
//                        System.out.println("DATE : " + dateIR.getDBFormat_8length());

                int remainTime = Integer.parseInt(products.get(i).get(_discountDate).toString()) - dateIR.getDBFormat_8length();//تعداد روزهای باقی مانده
                ServerLog.Print("remain day : " + remainTime);

                if (remainTime > 0) {//اگر بیشتر از یک روز تا پایان تخفیف مانده باشد.
                    if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                        html.append("<div class='mb-0 priceNoDiscountContectProductCss' style='text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 discountPriceContectProductCss'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
                    } else {
                        int remainHours = 00, remainMinutes = 00, remainSecound = 60;
                        String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                        remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                        remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                        remainHours = remainHours + (remainTime * 24);
                        html.append("<div class='mb-0 priceNoDiscountContectProductCss' style='text-decoration: line-through;' >" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 discountPriceContectProductCss'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 endTimeDiscountContectProductCss'><span>" + langSetting.get(0).get(Language._remainTime) + "</span> :<span style='float:left;'><span>" + remainHours + "</span>:<span>" + remainMinutes + "</span>:<span>" + remainSecound + "</span>&nbsp<span><i class='fal fa-clock'></i></span></span></div>");
                    }
                } else if (remainTime == 0) {//اگر کمتر از یک روز تا پایان تخفیف مانده باشد بر حسب ساعت و دقیقه زمان باقی مانده را نمایش می دهد
                    if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_discount).toString())) {
                        html.append("<div class='mb-0 priceNoDiscountContectProductCss' style='text-decoration: line-through;'>" + price + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 discountPriceContectProductCss'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
                    } else {
                        int remainHours = 0, remainMinutes = 0;
                        String timeArr[] = products.get(i).get(_discountTime).toString().split(":");
                        ServerLog.Print("currentHour : " + dateIR.getHours() + " & currentMinutes : " + dateIR.getMinutes());
                        remainMinutes = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (60 + Integer.parseInt(timeArr[1]) - dateIR.getMinutes()) : Integer.parseInt(timeArr[1]) - dateIR.getMinutes();
                        remainHours = Integer.parseInt(timeArr[1]) - dateIR.getMinutes() < 0 ? (Integer.parseInt(timeArr[0]) - dateIR.getHours() - 1) : Integer.parseInt(timeArr[0]) - dateIR.getHours();
                        if (remainHours >= 0) {//اگر هنوز ساعاتی تا پایان تخفیف مانده بود
                            html.append("<div class='mb-0 priceNoDiscountContectProductCss' style='text-decoration: line-through;'>" + price + products.get(i).get(_currency) + "</div>");
                            html.append("<div class='mb-0 discountPriceContectProductCss'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</div>");
                            html.append("<div class='mb-0 endTimeDiscountContectProductCss'><span>" + langSetting.get(0).get(Language._remainTime) + "</span> : <span>" + remainHours + "</span> <span>" + langSetting.get(0).get(Language._hour) + "</span> <span>" + remainMinutes + "</span> <span>" + langSetting.get(0).get(Language._minute) + "</span></div>");
                        } else {//اگر ساعت منفی شد یعنی زمان تخفیف به اتمام رسیده است و دوباره کالا با حالت نرمال و بدون تخفیف نمایش داده می شود
                            html.append("<div class='mb-0 priceNoDiscountContectProductCss'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                            html.append("<div class='mb-0 discountPriceContectProductCss'></div>");
                            html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
                        }
                    }
                } else {
                    if (Integer.parseInt(discountPrice) < Integer.parseInt(products.get(i).get(_price2).toString())) {
                        html.append("<div class='mb-0 priceNoDiscountContectProductCss' style='text-decoration: line-through;'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 discountPriceContectProductCss'>" + jjNumber.getFormattedNumber(discountPrice) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
                    } else {
                        html.append("<div class='mb-0 priceNoDiscountContectProductCss'>" + jjNumber.getFormattedNumber(price) + products.get(i).get(_currency) + "</div>");
                        html.append("<div class='mb-0 discountPriceContectProductCss'></div>");
                        html.append("<div class='mb-0 endTimeDiscountContectProductCss'></div>");
                    }
                }
            }
        }
        return html.toString();
    }

    public static int getOnlyDiscountPriceProduct(HttpServletRequest request, jjDatabaseWeb db, List<Map<String, Object>> products, List<Map<String, Object>> langSetting, int i) throws Exception {
        String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
        jjCalendar_IR date = new jjCalendar_IR();
        String price = "";
        String discountPrice = "";
        StringBuilder html = new StringBuilder();
        System.out.println("//////////////////user id///////////////////" + userId);
        if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
            if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                if ("".equals(products.get(i).get(_discount).toString())) {
                } else {
                    discountPrice = products.get(i).get(_discount).toString();
                }
            }
            price = products.get(i).get(_price2).toString();
        } else {//اگر کاربر لاگین کرده باشد
            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند

            if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                System.out.println("userRow:" + userRow.size());
                price = products.get(i).get(_price2).toString();
                for (int j = 0; j < userRow.size(); j++) {
                    String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup1).toString())) {
                        String price1 = products.get(i).get(_groupPrice1).toString();
                        if ((products.get(i).get(_groupPrice1).toString()).equals("")) {
                            price1 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                            price = price1;
                            discountPrice = price1;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup2).toString())) {
                        String price2 = products.get(i).get(_groupPrice2).toString();
                        if ((products.get(i).get(_groupPrice2).toString()).equals("")) {
                            price2 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                            price = price2;
                            discountPrice = price2;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup3).toString())) {
                        String price3 = products.get(i).get(_groupPrice3).toString();
                        if ((products.get(i).get(_groupPrice3).toString()).equals("")) {
                            price3 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                            price = price3;
                            discountPrice = price3;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup4).toString())) {
                        String price4 = products.get(i).get(_groupPrice4).toString();
                        if ((products.get(i).get(_groupPrice4).toString()).equals("")) {
                            price4 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                            price = price4;
                            discountPrice = price4;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(products.get(i).get(_userGroup5).toString())) {
                        String price5 = products.get(i).get(_groupPrice5).toString();
                        if ((products.get(i).get(_groupPrice5).toString()).equals("")) {
                            price5 = products.get(i).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                            price = price5;
                            discountPrice = price5;
                        }
                    }
                }
                if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(products.get(i).get(_discount).toString())) {
                    } else {
                        String price6 = products.get(i).get(_discount).toString();
                        if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                            price = price6;
                            discountPrice = price6;
                        }
                    }
                }
            } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                if (Integer.parseInt(products.get(i).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(products.get(i).get(_discount).toString())) {
                    } else {
                        discountPrice = products.get(i).get(_discount).toString();
                    }
                }
                price = products.get(i).get(_price2).toString();
            }
        }
        if (discountPrice.equals("")) {
            discountPrice = "0";
        }
        return Integer.parseInt(discountPrice);
    }

    /**
     * این تابع جزئیات محصول را به مشتری نشان میدهد
     *
     * @param request
     * @param response
     * @param db
     * @param isJsp
     * @return
     * @throws Exception
     */
    public static String getDetailsProduct(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            jjCalendar_IR date = new jjCalendar_IR();
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            String script = null;
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "sw";
            }
            StringBuilder optionHtml = new StringBuilder();
            String categoryGroupId = "";
            int category = Integer.parseInt(row.get(0).get(_category_id).toString());
            while (category > 0) {
                categoryGroupId += category + ",";
                List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + category));
                category = Integer.parseInt(rowCategory.get(0).get(Category_Product._parent).toString());
            }
            String[] seprateCategory = categoryGroupId.split(",");
            List<Map<String, Object>> rowRoot = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[seprateCategory.length - 1]));
            optionHtml.append("<p style='text-align: right;'>");
            optionHtml.append("<a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + rowRoot.get(0).get(Category_Product._id) + ");'>" + rowRoot.get(0).get(Category_Product._title) + "</a> ");
            for (int i = seprateCategory.length - 2; i >= 0; i--) {
                List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[i]));
                optionHtml.append("&gt; <a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + rowCategory.get(0).get(Category_Product._id) + ");'>" + rowCategory.get(0).get(Category_Product._title) + "</a> ");
            }
            optionHtml.append("&gt; <a>" + row.get(0).get(_name) + "</a></p>");
            html.append("<section style='background-image: url(template/images/bg/6.jpg);background-repeat-x: no-repeat;'>\n"
                    + "    <div class=\"container\">\n"
                    + "        <span class=\"muted\">" + optionHtml + "</span>\n"
                    + "        <div class=\"row\">\n"
                    + "            <div class=\"col-md-6 mt-4\">\n"
                    + "                <div class=\"product-details\">\n"
                    + "                    <a href=\"images/tirepic.jpg\">\n"
                    + "                        <div class=\"brand-label\">\n"
                    + "                            <div class=\"txt-brand-label-pro-info\"></div>\n"
                    + "                            <a>\n"
                    + "                                <img id='changeSrcInGetDetailProduct' src='upload/" + row.get(0).get(_pic1) + "' class=\"img-fluid\" alt='image' style='width:100%;height:400px;'>\n"
                    + "                                <div class='row'>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic1).toString().equals("")) {
            } else {
                html.append("<img id='img1' src='upload/" + row.get(0).get(_pic1) + "' onclick='changeSrc1();' class='img-fluid' alt='image1' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic2).toString().equals("")) {
            } else {
                html.append("<img id='img2' src='upload/" + row.get(0).get(_pic2) + "' onclick='changeSrc2();' class='img-fluid' alt='image2' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic3).toString().equals("")) {
            } else {
                html.append("<img id='img3' src='upload/" + row.get(0).get(_pic3) + "' onclick='changeSrc3();' class='img-fluid' alt='image3' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic4).toString().equals("")) {
            } else {
                html.append("<img id='img4' src='upload/" + row.get(0).get(_pic4) + "' onclick='changeSrc4();' class='img-fluid' alt='image4' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic5).toString().equals("")) {
            } else {
                html.append("<img id='img5' src='upload/" + row.get(0).get(_pic5) + "' onclick='changeSrc5();' class='img-fluid' alt='image5' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'>\n");
            if (row.get(0).get(_pic6).toString().equals("")) {
            } else {
                html.append("<img id='img6' src='upload/" + row.get(0).get(_pic6) + "' onclick='changeSrc6();' class='img-fluid' alt='image6' style='width:100%;height:70px;cursor: pointer;'>\n");
            }
            html.append("</div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img1' src='upload/" + row.get(0).get(_pic1) + "' onclick='changeSrc1();' class='img-fluid' alt='image1' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img2' src='upload/" + row.get(0).get(_pic2) + "' onclick='changeSrc2();' class='img-fluid' alt='image2' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img3' src='upload/" + row.get(0).get(_pic3) + "' onclick='changeSrc3();' class='img-fluid' alt='image3' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img4' src='upload/" + row.get(0).get(_pic4) + "' onclick='changeSrc4();' class='img-fluid' alt='image4' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img5' src='upload/" + row.get(0).get(_pic5) + "' onclick='changeSrc5();' class='img-fluid' alt='image5' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    //		  + "    <div class='col-2 mt-2' style='padding-right: 1px;padding-left: 1px;'><img id='img6' src='upload/" + row.get(0).get(_pic6) + "' onclick='changeSrc6();' class='img-fluid' alt='image6' style='width:100%;height:70px;cursor: pointer;'></div>\n"
                    + "</div>"
                    + "                            </a>\n"
                    + "                        </div>\n"
                    + "                    </a>\n"
                    + "                    <div class=\"content\" style='margin-bottom: 20px;'>\n"
                    + "                        <ul class=\"nav nav-pills  justify-content-center\" id=\"pills-tab\" role=\"tablist\">\n"
                    + "                            <li class=\"nav-item\">\n"
                    + "                                <a class=\"nav-link active\" id=\"pills-home-tab\" data-toggle=\"pill\" href=\"#pills-home\"\n"
                    + "                                   role=\"tab\" aria-controls=\"pills-home\"\n"
                    + "                                   aria-selected=\"true\">توضیحات</a>\n"
                    + "                            </li>\n"
                    + "                        </ul>\n");
            html.append("<div class=\"tab-content\" id=\"pills-tabContent\">");
            html.append("<div class=\"tab-pane fade show active\" id=\"pills-home\" role=\"tabpanel\" aria-labelledby=\"pills-home-tab\" style='text-align: right;'>" + row.get(0).get(_content) + "</div>");

            html.append("                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"col-md-6 mt-4\">\n"
                    + "                <div class=\"sidebar\">\n"
                    + "                    <!-- User Profile widget -->\n"
                    + "                    <div class=\"widget user text-center\">\n"
                    + "                        <h4>" + row.get(0).get(_name) + "</h4>\n");
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String price = "";
            String discountPrice = "";
            System.out.println("//////////////////user id///////////////////" + userId);
            if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                if (Integer.parseInt(row.get(0).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(row.get(0).get(_discount).toString())) {
                    } else {
                        discountPrice = row.get(0).get(_discount).toString();
                    }
                }
                price = row.get(0).get(_price2).toString();
            } else {//اگر کاربر لاگین کرده باشد
                List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));
                price = row.get(0).get(_price2).toString();
                for (int j = 0; j < userRow.size(); j++) {
                    String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup1).toString())) {
                        String price1 = row.get(0).get(_groupPrice1).toString();
                        if ((row.get(0).get(_groupPrice1).toString()).equals("")) {
                            price1 = row.get(0).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price1)) {
                            price = price1;
                            discountPrice = price1;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup2).toString())) {
                        String price2 = row.get(0).get(_groupPrice2).toString();
                        if ((row.get(0).get(_groupPrice2).toString()).equals("")) {
                            price2 = row.get(0).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                            price = price2;
                            discountPrice = price2;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup3).toString())) {
                        String price3 = row.get(0).get(_groupPrice3).toString();
                        if ((row.get(0).get(_groupPrice3).toString()).equals("")) {
                            price3 = row.get(0).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                            price = price3;
                            discountPrice = price3;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup4).toString())) {
                        String price4 = row.get(0).get(_groupPrice4).toString();
                        if ((row.get(0).get(_groupPrice4).toString()).equals("")) {
                            price4 = row.get(0).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                            price = price4;
                            discountPrice = price4;
                        }
                    }
                    if (userGroup.equalsIgnoreCase(row.get(0).get(_userGroup5).toString())) {
                        String price5 = row.get(0).get(_groupPrice5).toString();
                        if ((row.get(0).get(_groupPrice5).toString()).equals("")) {
                            price5 = row.get(0).get(_price2).toString();
                        }
                        if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                            price = price5;
                            discountPrice = price5;
                        }
                    }
                }
                if (Integer.parseInt(row.get(0).get(_discountDate).toString()) >= date.getDBFormat_8length()) {
                    if ("".equals(row.get(0).get(_discount).toString())) {
                    } else {
                        String price6 = row.get(0).get(_discount).toString();
                        if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                            price = price6;
                            discountPrice = price6;
                        }
                    }
                }
            }
            price = row.get(0).get(_price2).toString();
            if (discountPrice.equals("")) {
                html.append("                        <p class=\"p-3 h5\">" + jjNumber.getFormattedNumber(row.get(0).get(_price2).toString()) + " " + row.get(0).get(_currency) + "</p>\n");
            } else {
                html.append("                        <p class=\"p-3 h5\" style='text-decoration: line-through;'>" + jjNumber.getFormattedNumber(row.get(0).get(_price2).toString()) + " " + row.get(0).get(_currency) + "</p>\n");
                html.append("                        <p class=\"p-3 h5\" style='color: #e51111;font-size: 20px;'>" + jjNumber.getFormattedNumber(discountPrice) + " " + row.get(0).get(_currency) + "</p>\n");
            }
            if (!row.get(0).get(_tags).equals("")) {
                String[] seprateTags = row.get(0).get(_tags).toString().split(",");
                for (int i = 0; i < seprateTags.length; i++) {
                    if (!seprateTags[i].equals("null")) {
                        List<Map<String, Object>> rowTags = jjDatabase.separateRow(db.Select(Tags.tableName, Tags._id + "=" + seprateTags[i]));
                        html.append("<p>" + rowTags.get(0).get(Tags._name) + "<p/>\n");
                    }
                }
            }
            html.append("                        <ul class=\"list-inline mt-20\">\n"
                    + "                            <li class=\"list-inline-item\">\n"
                    + "                                <a class=\"btn btn-contact d-inline-block  btn-primary px-lg-5 my-1 px-md-3\" style='color:#fff;cursor: pointer;' onclick='addToShoppingCart(" + row.get(0).get(_id) + ");'>افزودن به سبد کالا</a>\n"
                    + "                            </li>\n"
                    //                    + "                            <li class=\"list-inline-item\">\n"
                    //                    + "                                <a class=\"btn btn-contact d-inline-block  btn-primary px-lg-5 my-1 px-md-3\" style='color:#fff;cursor: pointer;' onclick='addToShoppingCart(" + row.get(0).get(_id) + ");'>پرداخت</a>\n"
                    //                    + "                            </li>\n"
                    + "                            </ul>\n");
            html.append("<div id=\"pills-profile\" style='text-align: right;'>\n"
                    + "                                <h3 class=\"tab-title\" style='text-align: center;margin-top: 10px;'>ویژگی ها</h3>\n"
                    + "                                <table class=\"table table-bordered product-table\" style='direction: rtl;'>\n"
                    + "                                    <tbody>\n");
            for (int i = 1; i < 21; i++) {
                if (!row.get(0).get("account_product_prop" + i).equals("") && !row.get(0).get("account_product_val" + i).equals("")) {
                    html.append("                                    <tr>\n"
                            + "                                        <td style='text-align: right;'>" + row.get(0).get("account_product_prop" + i) + "</td>\n"
                            + "                                        <td style='text-align: right;'>" + row.get(0).get("account_product_val" + i) + "</td>\n"
                            + "                                    </tr>\n");
                }
            }
            html.append("                                    </tbody>\n"
                    + "                                </table>\n"
                    + "                            </div>\n");
            html.append("                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</section>");
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
//  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * این تابع همه ای محصولات را از جدیدترین محصول تا قدیمی ترین محصول را نشان
     * میدهد
     *
     * @param request
     * @param response
     * @param db
     * @param isAjax
     * @return
     * @throws Exception
     */
    public static String getProductsBeloved(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isJsp) throws Exception {
        try {
            String script = "";
            String lang = jjTools.getSessionAttribute(request, "myLang");
            lang = lang.equals("") ? "1" : lang;
            List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
//	Integer categoryProductId = Integer.parseInt(jjTools.getParameter(request, "categoryProductId"));
//	String categoryID = "";
//	List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Product.tableName));// بر اساس حروف الفبا مرتب باشد بهتر است
//	categoryID = getCategoryProducts(categoryID, rowCategory, categoryProductId, db);
//	String where = "";
//	if (categoryID.equals("")) {
//	  where += _category_id + "=" + categoryProductId;
//	} else {
//	  String[] splitCPID = categoryID.split(",");
//	  for (int i = 0; i < splitCPID.length; i++) {
//	    if (i == splitCPID.length - 1) {
//		where += _category_id + "=" + splitCPID[i];
//	    } else {
//		where += _category_id + "=" + splitCPID[i] + " OR ";
//	    }
//	  }
//	}
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "swProductBeloved" : panel;
            System.out.println(panel);
//	List<Map<String, Object>> products=jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "=3",_date + " LIMIT 3"));
            List<Map<String, Object>> products = jjDatabase.separateRow(db.SelectDescLimit(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "=3", _date, "3"));
//	if (categoryProductId == 1) {
//	  products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + Product._priority + "<=2" + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
//	} else {
//	  products = jjDatabase.separateRow(db.SelectAllDESCOrder(Product.tableName, Product._lang + "=" + lang + " AND " + _active + "=1 AND (" + where + ")", _date));//بر اساس جدیدترین زمان ثبت کالا انتخاب میشوند.
//	  script = "$('#sidebar').toggleClass('active');";
//	}
            StringBuilder optionHtml = new StringBuilder();
            String categoryGroupId = "";
//	int category = categoryProductId;
//	while (category > 0) {
//	  categoryGroupId += category + ",";
//	  List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + category));
//	  category = Integer.parseInt(row.get(0).get(Category_Product._parent).toString());
//	}
//	String[] seprateCategory = categoryGroupId.split(",");
//	for (int i = seprateCategory.length - 1; i >= 0; i--) {
//	  if (i == seprateCategory.length - 1) {
//	    List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[i]));
//	    optionHtml.append("<a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + row.get(0).get(Category_Product._id) + ");'>" + row.get(0).get(Category_Product._title) + "</a> ");
//	  } else {
//	    List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Product.tableName, Category_Product._id + "=" + seprateCategory[i]));
//	    optionHtml.append("&gt; <a style='cursor: pointer;' class='text-info' onclick='swGetProducts(" + row.get(0).get(Category_Product._id) + ");'>" + row.get(0).get(Category_Product._title) + "</a> ");
//	  }
//	}
            optionHtml.append("</p>");
            StringBuilder html = new StringBuilder();
            html.append("<div class=\"row\" style=\"margin-left: 0px;margin-right: 0px;\" dir=\"ltr\">");
            for (int i = 0; i < products.size(); i++) {
                html.append("<div class='productCss'>\n"
                        + "                    <div class='bodyProductCss'>\n"
                        //		    + "                        <div class=\"mainProductCss\"  href='"+Server.productJSP+"?id="+ products.get(i).get(_id)+"'>\n"
                        //		    + "                            <a  onclick="+Server.productJSP+"?id="+ products.get(i).get(_id)+">\n"
                        //		    + "                            <a>\n"
                        + "                  <div class=\"mainProductCss\"  onclick='getDetailsProduct(" + products.get(i).get(_id) + ");'>\n"
                        + "                            <div class=\"txt-brand-label-pro-info\" style='z-index: 2;'></div>\n"
                        + "                            <div class='imgDivProductCss'>\n"
                        + "                                <img src='upload/" + products.get(i).get(_pic1) + "' width=\"200\" height=\"200\" alt='" + products.get(i).get(_name) + "'/><br>\n"
                        + "                                <span></span>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"card contentProductCss\">\n"
                        + "                                <div class=\"card-body bodyContentProductCss\">"
                        + "                                  <div class=\"mb-0 nameProductCss\">" + products.get(i).get(_name).toString() + "</div>\n"
                        + "                                   <div class=\"rateContentProductCss\">\n"
                        + "                                        <span class='rate'></span>\n"
                        + "                                        <span class='rateNumber'></span>\n"
                        //                        + "                                        <span><i class=\"fad fa-star\"></i></span>\n"
                        + "                                        <span class='star'></span>\n"
                        + "                                    </div>\n");
                html.append(getPriceProduct(request, db, products, langSetting, i));
                //<======PRICE
                if (products.get(i).get(_quantity).equals("0")) {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\">نا موجود</div>\n");
                } else if (Integer.parseInt(products.get(i).get(_quantity).toString()) < 11) {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\"><i class='far fa-bell'></i>" + products.get(i).get(_quantity) + " عدد از این محصول در انبار وجود دارد</div>\n");
                } else {
                    html.append("                                    <div class=\"mb-0 quantityContentProductCss\"></div>\n");
                }
                html.append("                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        //		     + "                            </a>\n"
                        + "                        <div class=\"footerProduct\">\n"
                        + "                            <div class='footerProductDetails' onclick='addToLikeCart(" + products.get(i).get(_id) + ");'>\n"
                        + "                                <a><i class=\"fal fa-heart\"></i></a>\n"
                        + "                                <div class=\"footerProductPluse\">+</div>\n"
                        + "                            </div>\n"
                        + "                            <div class='footerProductCart' onclick='addToShoppingCart(" + products.get(i).get(_id) + ");'>\n"
                        + "                                <a ><i class=\"fal fa-shopping-cart\"></i></a>\n"
                        + "                                <a ><i class=\"fal fa-bookmark\" style='display: none;'></i></a>\n"
                        + "                                <div class=\"footerProductPluse\">+</div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>");
            }
            html.append("                </div>");
            if (isJsp) {
                return html.toString();
            } else {
                Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.setHtml("#hrefCollapse", optionHtml.toString()) + script);
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
