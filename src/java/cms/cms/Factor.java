package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factor {

    public static String tableName = "product_factor";
    public static String _id = "id";
    public static String _userId = "product_factor_userId";//شخصی که فاکتور را پرداخت میکند
    public static String _creator = "product_factor_creator";// شخصی که فاکتور را ایجاد میکند
    public static String _serialNumber = "product_factor_serialNumber";// شماره سریال فاکتور
    public static String _nationalCode = "product_factor_nationalCode";// کد ملی
    public static String _economicCode = "product_factor_economicCode";// کد اقتصادی
    public static String _companyName = "product_factor_companyName";// نام شرکت
    public static String _address = "product_factor_address";// ادرس
    public static String _address2 = "product_factor_address2";
    public static String _zipCode = "product_factor_zipCode";
    public static String _zipCode2 = "product_factor_zipCode2";
    public static String _tell = "product_factor_tell";
    public static String _tell2 = "product_factor_tell2";
    public static String _date = "product_factor_date";
    public static String _dueDate = "product_factor_dueDate";
    public static String _paymentDate = "product_factor_paymentDate";
    public static String _time = "product_factor_time";
    public static String _statuse = "product_factor_statuse";
    public static String _attach = "product_factor_attach";
    public static String _discription = "product_factor_discription";
    public static String _totalAmountValueAdded = "product_factor_totalAmountValueAdded";
    public static String _totalAmount = "product_factor_totalAmount";
    public static String lbl_statusePaid = "پرداخت شده";
    public static String lbl_statuseUnPaid = "پرداخت نشده";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
//    public static int rul_rfs = 56;
//    public static int rul_ins = 57;
//    public static int rul_edt = 58;
//    public static int rul_dlt = 59;
//    public static int rul_lng = 60;
    static int pageCounter = 4;
//   public static int rul_show_factorsTab 
////    public static int rul_show_pics _reserved= 186 --- 200;// RESERVED : 186 -- 200//نمایش تب حسابداری//====== BY RASHIDI ======
    public static int rul_rfs = 405;
    public static int rul_ins = 202;
    public static int rul_edt = 203;
    public static int rul_dlt = 204;
    public static int rul_print = 205;
    public static int rul_lng2 = 206;
    public static int rul_lng3 = 207;//====== BY RASHIDI ======
    public static int rul_lng4 = 208;//====== BY RASHIDI ======
    public static int rul_lng5 = 209;//====== BY RASHIDI ======
////    public static int rul_reserved = 210 --- 220;// RESERVED : 210 -- 220

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM product_factor ORDER BY product_factor_date DESC,product_factor_time DESC;"));
            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("    <div class='card-header bg-primary tx-white'>مدیریت فاکتور</div>"
                    + "    <div class='card-body pd-sm-30'>");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append("<p class='mg-b-20 mg-sm-b-30'>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='cmsFactor.m_add_new();' >فاکتور جدید</a>");
                html.append("</p>");
            }
            html.append("<div style='width: 100%; padding-left: -10px;'>"
                    + "<div class='table-responsive'>");
            html.append("<table id='refreshFactor' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'>"
                    + "<thead>");
            html.append("<th width='10%' class='r'>کد</th>");
            html.append("<th width='15%' class='c'>تاریخ</th>");
            html.append("<th width='15%' class='c'>سریال فاکتور</th>");
            html.append("<th width='25%' class='c'>مشتری</th>");
            html.append("<th width='20%' class='c'>جمع کل</th>");
            html.append("<th width='20%' class='c'>وضعیت</th>");
            html.append("<th width='15%' class='c'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(_statuse).equals(lbl_statusePaid)) {
                    html.append("<tr style='background-color:#9eed9099;'>");
                    html.append("<td class='c' style='background-color:#9eed9099;'>" + (row.get(i).get(_id).toString()) + "</td>");
                } else {
                    html.append("<tr style='background-color:#f1bfbf99;'>");
                    html.append("<td class='c' style='background-color:#f1bfbf99;'>" + (row.get(i).get(_id).toString()) + "</td>");
                }
                html.append("<td class='c'>"
                        + "صدور:" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString())
                        + "<br/>"
                        + "سررسید:" + jjCalendar_IR.getViewFormat(row.get(i).get(_dueDate).toString())
                        + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_serialNumber).toString()) + "</td>");
                html.append("<td class='c'>" + (Access_User.getUserName(row.get(i).get(_userId).toString(), db)) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(row.get(i).get(_totalAmount).toString())) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_statuse).toString()) + "</td>");
                html.append("<td class='c icon ion-ios-gear-outline' onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' style='cursor: pointer;color:red;font-size:26px;'></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div>");
            html.append("</div>");
            html.append("</div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swFactorTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "16" : "", "لیست فاکتور های امروز");

            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نشان دادن جدول ایتم در فاکتور
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String refreshItemFactorInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, "id");
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + id));
            html3.append("<div class=\"table-wrapper\">\n");
            html3.append("<table id='refreshItemFactorInFactor' class='table display responsive' style='direction: rtl;'><thead>");
            html3.append("<th class='c' width='5%'>کد</th>");
            html3.append("<th class='c' width='10%'>محصول</th>");
            html3.append("<th class='c' width='15%'>قیمت اصلی</th>");
            html3.append("<th class='c' width='20%'>قیمت بعد از تخفیف</th>");
            html3.append("<th class='c' width='15%'>مبلغ ارزش افزوده </th>");
            html3.append("<th class='c' width='15%'>مبلغ کل</th>");
            html3.append("<th class='c' width='15%'>ویرایش</th>");
            html3.append("<th class='c' width='15%'>حذف</th>");
            html3.append("</thead><tbody>");
            for (int i = 0; i < rowFactorItem.size(); i++) {
                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                html3.append("<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
                html3.append("<td class='c'>" + rowProduct.get(0).get(Product._name) + "</td>");
                html3.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
                html3.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
                html3.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
                html3.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
                html3.append("<td class='c' onclick='cmsFactor.selectItemFactorInFactor(" + rowFactorItem.get(i).get(FactorItem._id) + ");' style='cursor: pointer;'><i class='fa fa-edit' style='color:#F49917;font-size:25px;'></i></td>");
                html3.append("<td class='c' onclick='cmsFactor.deleteItemFactorInFactor(" + rowFactorItem.get(i).get(FactorItem._id) + ");' style='cursor: pointer;'><i class='fa fa-trash' style='color:#dc3545;font-size:25px;'></i></td>");
                html3.append("</tr>");
            }
            html3.append("</tbody></table>");
            html.append(Js.setHtml("#FactorItemInFactor", html3.toString()));
            String script = Js.table("#refreshItemFactorInFactor", "auto", 0, "", "آیتم های فاکتور");
            script += "$('#refreshItemFactorInFactor').DataTable( {\n"
                    + "        \"paging\":   false,\n"
                    + "        \"ordering\": false,\n"
                    + "        \"info\":     false,\n"
                    + "        \"searching\": false"
                    + "    } );";

            Server.outPrinter(request, response, html.toString() + script);
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
                html.append(Js.setHtml("#Factor_button_insert", "<button id=\"insert_Factor_new\" title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjFactor.insert() + "'>" + lbl_insert + "</button>"));
                html.append(Js.setVal("#product_factor_creator", jjTools.getSeassionUserId(request)));
                String name = String.valueOf(jjTools.getSeassionUserId(request));
                html.append(Js.setVal("#product_factor_creatorName", Access_User.getUserName(name, db)));
                html.append(Js.setVal("#" + _date, jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))));
                html.append(Js.setVal("#" + _dueDate, jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))));
                html.append(Js.hide("#FactorItemInFactorForm"  ));
                html.append("$('#Factor_button_insert').parent().append('');");

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
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(_creator, jjTools.getParameter(request, _creator));
            map.put(_companyName, jjTools.getParameter(request, _companyName));
            map.put(_serialNumber, jjTools.getParameter(request, _serialNumber));
            map.put(_userId, jjTools.getParameter(request, _userId));
            map.put(_nationalCode, jjTools.getParameter(request, _nationalCode));
            map.put(_economicCode, jjTools.getParameter(request, _economicCode));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_address2, jjTools.getParameter(request, _address2));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_tell2, jjTools.getParameter(request, _tell2));
            map.put(_zipCode, jjTools.getParameter(request, _zipCode));
            map.put(_zipCode2, jjTools.getParameter(request, _zipCode2));
            map.put(_attach, jjTools.getParameter(request, _attach));
            map.put(_statuse, lbl_statuseUnPaid);
            if (jjTools.getParameter(request, _totalAmount).equals("")) {
                map.put(_totalAmount, 0);
            } else {
                map.put(_totalAmount, jjTools.getParameter(request, _totalAmount).replaceAll(",", ""));
            }
            if (jjTools.getParameter(request, _totalAmountValueAdded).equals("")) {
                map.put(_totalAmountValueAdded, 0);
            } else {
                map.put(_totalAmountValueAdded, jjTools.getParameter(request, _totalAmountValueAdded));
            }
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_dueDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _dueDate), true));
            map.put(_time, dateIR.getTimeFormat_4length());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای 245"));
                return "";
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#product_factor_item_factorId1", row.get(0).get(_id)));
            html.append(Js.setHtml("#Factor_button_insert", ""));
            Server.outPrinter(request, response, html.toString() + Js.jjFactor.refresh() + Js.jjFactor.select(row.get(0).get(_id).toString()));
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ثبت ایتم برای فاکتور
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insertItemFactorInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> mapFactor = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(FactorItem._factorId, jjTools.getParameter(request, FactorItem._factorId));
            if (jjTools.getParameter(request, FactorItem._discountPercent).equals("")) {
                map.put(FactorItem._discountPercent, 0);
            } else {
                map.put(FactorItem._discountPercent, jjTools.getParameter(request, FactorItem._discountPercent));
            }
            map.put(FactorItem._discription, jjTools.getParameter(request, FactorItem._discription));
            map.put(FactorItem._originalPrice, jjTools.getParameter(request, FactorItem._originalPrice));
            map.put(FactorItem._percentageOfValueAdded, jjTools.getParameter(request, FactorItem._percentageOfValueAdded));
            if (jjTools.getParameter(request, FactorItem._priceAfterDiscount).equals("")) {
                map.put(FactorItem._priceAfterDiscount, 0);
            } else {
                map.put(FactorItem._priceAfterDiscount, jjTools.getParameter(request, FactorItem._priceAfterDiscount));
            }
            map.put(FactorItem._productId, jjTools.getParameter(request, FactorItem._productId));
            if (jjTools.getParameter(request, FactorItem._quantity).equals("")) {
                map.put(FactorItem._quantity, 0);
            } else {
                map.put(FactorItem._quantity, 0);
            }
            map.put(FactorItem._totalPrice, jjTools.getParameter(request, FactorItem._totalPrice));
            map.put(FactorItem._valueAdded, jjTools.getParameter(request, FactorItem._valueAdded));
            map.put(FactorItem._date, dateIR.getDBFormat_8length());
            map.put(FactorItem._time, dateIR.getTimeFormat_4length());
            map.put(FactorItem._statuse, FactorItem.lbl_statuseUnPaid);
            if (db.insert(FactorItem.tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            double tottalAmountValueAdded = Double.parseDouble(row.get(0).get(Factor._totalAmountValueAdded).toString());
            double valueAdded = Double.parseDouble(jjTools.getParameter(request, FactorItem._valueAdded));
            mapFactor.put(Factor._totalAmountValueAdded, (tottalAmountValueAdded + valueAdded));
            System.out.println("////////////////////////////////////////" + (tottalAmountValueAdded + valueAdded));
            double tottalAmount = Double.parseDouble(row.get(0).get(Factor._totalAmount).toString());
            double tottalPrice = Double.parseDouble(jjTools.getParameter(request, FactorItem._totalPrice));
//            mapFactor.put(Factor._totalAmountValueAdded, Float.valueOf(row.get(0).get(Factor._totalAmountValueAdded).toString()) + Float.valueOf(jjTools.getParameter(request, _valueAdded)));
            mapFactor.put(Factor._totalAmount, tottalAmount + tottalPrice);
            if (!db.update(Factor.tableName, mapFactor, _id + "=" + jjTools.getParameter(request, FactorItem._factorId))) {
                String errorMessage = "در فرم مشکلی پیش آمده است";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#" + _totalAmountValueAdded, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmountValueAdded).toString())));
            html.append(Js.setVal("#" + _totalAmount, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmount).toString())));
            Server.outPrinter(request, response, html.toString() + "cmsFactor.m_refreshItemFactorInFactor(" + jjTools.getParameter(request, FactorItem._factorId) + ");");
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * کپی کردن یک فاکتور با تعداد اقساط آن
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String copyFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            System.out.println(">>>>>>>>>" + Integer.valueOf("4"));
            int installementCount = jjNumber.isDigit(jjTools.getParameter(request, "installementCount")) ? Integer.valueOf(jjTools.getParameter(request, "installementCount")) : 0;
            int installementPeriod = jjNumber.isDigit(jjTools.getParameter(request, "installementPeriod")) ? Integer.valueOf(jjTools.getParameter(request, "installementPeriod")) : 0;
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, Factor._id)));
            jjCalendar_IR nextInstallementDate = new jjCalendar_IR(row.get(0).get(_dueDate).toString());
            List<Map<String, Object>> factorItemRows = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + jjTools.getParameter(request, Factor._id)));
            for (int i = 0; i < installementCount; i++) {// به تعداد مورد درخواست کاربر در جدول اینسرت می کنیم
                Map<String, Object> map = new HashMap<>();
                jjCalendar_IR dateIR = new jjCalendar_IR();
                map.put(_creator, row.get(0).get(_creator));
                map.put(_companyName, row.get(0).get(_companyName));
                map.put(_serialNumber, jjNumber.isDigit(row.get(0).get(_serialNumber).toString()) ? (Integer.valueOf(row.get(0).get(_serialNumber).toString()) + i) : row.get(0).get(_serialNumber).toString() + i); // اگر سریال عدد بود یکی به آن اضافه کند بصورت اینتیجر اگر نبود بصورت استرینگ کاراکتر را به آن اضافه کند
                map.put(_userId, row.get(0).get(_userId));
                map.put(_nationalCode, row.get(0).get(_nationalCode));
                map.put(_economicCode, row.get(0).get(_economicCode));
                map.put(_address, row.get(0).get(_address));
                map.put(_address2, row.get(0).get(_address2));
                map.put(_tell, row.get(0).get(_tell));
                map.put(_tell2, row.get(0).get(_tell2));
                map.put(_zipCode, row.get(0).get(_zipCode));
                map.put(_zipCode2, row.get(0).get(_zipCode2));
                map.put(_statuse, lbl_statuseUnPaid);
                map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(null, true));
                nextInstallementDate.addMonth(installementPeriod);
                map.put(_dueDate, nextInstallementDate.getDBFormat_8length());
                map.put(_time, dateIR.getTimeFormat_4length());
                map.put(_totalAmountValueAdded, row.get(0).get(_totalAmountValueAdded));
                map.put(_totalAmount, row.get(0).get(_totalAmount));
                List<Map<String, Object>> rowNewFacotr = jjDatabase.separateRow(db.insert(tableName, map));
                if (rowNewFacotr.isEmpty()) {
                    String errorMessage = "عملیات درج فاکتور " + i + " ام به درستی صورت نگرفت.";
                    Server.outPrinter(request, response, Js.dialog(errorMessage));
                    return "";
                }
                for (int j = 0; j < factorItemRows.size(); j++) {// هر فاکتور بین صفر تا چند آیتم میتواند داشته باشد که باید کپی شود
                    Map<String, Object> mapItems = new HashMap<>();
                    mapItems.put(FactorItem._factorId, rowNewFacotr.get(0).get(_id));// آی دی فاکتور مهم است دقت شود که بابد آی دی فاکتوری باشد که جدیدا وارد شده
                    mapItems.put(FactorItem._discountPercent, factorItemRows.get(j).get(FactorItem._discountPercent));
                    mapItems.put(FactorItem._discription, factorItemRows.get(j).get(FactorItem._discription) + " قسط " + i + "ام ");
                    mapItems.put(FactorItem._originalPrice, factorItemRows.get(j).get(FactorItem._originalPrice));
                    mapItems.put(FactorItem._percentageOfValueAdded, factorItemRows.get(j).get(FactorItem._percentageOfValueAdded));
                    mapItems.put(FactorItem._priceAfterDiscount, factorItemRows.get(j).get(FactorItem._priceAfterDiscount));
                    mapItems.put(FactorItem._productId, factorItemRows.get(j).get(FactorItem._productId));
                    mapItems.put(FactorItem._quantity, factorItemRows.get(j).get(FactorItem._quantity));
                    mapItems.put(FactorItem._totalPrice, factorItemRows.get(j).get(FactorItem._totalPrice));
                    mapItems.put(FactorItem._valueAdded, factorItemRows.get(j).get(FactorItem._valueAdded));
                    mapItems.put(FactorItem._date, nextInstallementDate.getDBFormat_8length());// تاریخ این قسمت کاربردی ندارد
                    mapItems.put(FactorItem._time, dateIR.getTimeFormat_4length());
                    mapItems.put(FactorItem._statuse, lbl_statuseUnPaid);
                    if (db.insert(FactorItem.tableName, mapItems).getRowCount() == 0) {
                        String errorMessage = "عملیات درج یکی از آیتم ها به درستی صورت نگرفت.";
                        if (jjTools.getParameter(request, "myLang").equals("2")) {
                            errorMessage = "Edit Fail;";
                        }
                        Server.outPrinter(request, response, Js.dialog(errorMessage));
                        return "";
                    }
                }
            }

            Server.outPrinter(request, response, Js.modal("عملیات کپی انجام شد", "پیام سیستم"));
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
            String id = jjTools.getParameter(request, _id);
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(_creator, jjTools.getParameter(request, _creator));
            map.put(_companyName, jjTools.getParameter(request, _companyName));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_serialNumber, jjTools.getParameter(request, _serialNumber));
            map.put(_userId, jjTools.getParameter(request, _userId));
            map.put(_nationalCode, jjTools.getParameter(request, _nationalCode));
            map.put(_attach, jjTools.getParameter(request, _attach));
            map.put(_statuse, jjTools.getParameter(request, _statuse));
            map.put(_economicCode, jjTools.getParameter(request, _economicCode));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_address2, jjTools.getParameter(request, _address2));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_tell2, jjTools.getParameter(request, _tell2));
            map.put(_zipCode, jjTools.getParameter(request, _zipCode));
            map.put(_zipCode2, jjTools.getParameter(request, _zipCode2));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            map.put(_dueDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _dueDate), true));
            map.put(_time, dateIR.getTimeFormat_4length());
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Server.outPrinter(request, response, Js.jjFactor.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    /**
     * ویرایش ایتم در قسمت فاکتور
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editItemFactorInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, FactorItem.rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> mapFactor = new HashMap<>();
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._id + "=" + id));
            double tottalAmountValueAdded = Double.parseDouble(row.get(0).get(Factor._totalAmountValueAdded).toString());
            double valueAdded = Double.parseDouble(rowFactorItem.get(0).get(FactorItem._valueAdded).toString());
            mapFactor.put(Factor._totalAmountValueAdded, (tottalAmountValueAdded - valueAdded));
            double tottalAmount = Double.parseDouble(row.get(0).get(Factor._totalAmount).toString());
            double tottalPrice = Double.parseDouble(rowFactorItem.get(0).get(FactorItem._totalPrice).toString());
//            mapFactor.put(Factor._totalAmountValueAdded, Float.valueOf(row.get(0).get(Factor._totalAmountValueAdded).toString()) + Float.valueOf(jjTools.getParameter(request, _valueAdded)));
            mapFactor.put(Factor._totalAmount, tottalAmount - tottalPrice);
            if (!db.update(Factor.tableName, mapFactor, _id + "=" + jjTools.getParameter(request, FactorItem._factorId))) {
                String errorMessage = "در فرم مشکلی پیش آمده است";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            tottalAmountValueAdded = Double.parseDouble(row.get(0).get(Factor._totalAmountValueAdded).toString());
            valueAdded = Double.parseDouble(jjTools.getParameter(request, FactorItem._valueAdded));
            mapFactor.put(Factor._totalAmountValueAdded, (tottalAmountValueAdded + valueAdded));
            System.out.println("////////////////////////////////////////" + (tottalAmountValueAdded + valueAdded));
            tottalAmount = Double.parseDouble(row.get(0).get(Factor._totalAmount).toString());
            tottalPrice = Double.parseDouble(jjTools.getParameter(request, FactorItem._totalPrice));
//            mapFactor.put(Factor._totalAmountValueAdded, Float.valueOf(row.get(0).get(Factor._totalAmountValueAdded).toString()) + Float.valueOf(jjTools.getParameter(request, _valueAdded)));
            mapFactor.put(Factor._totalAmount, tottalAmount + tottalPrice);
            if (!db.update(Factor.tableName, mapFactor, _id + "=" + jjTools.getParameter(request, FactorItem._factorId))) {
                String errorMessage = "در فرم مشکلی پیش آمده است";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#" + _totalAmountValueAdded, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmountValueAdded).toString())));
            html.append(Js.setVal("#" + _totalAmount, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmount).toString())));
            html.append(Js.setVal("#FactorItemButtonEdit", "<button id='insert_ItemFactorInFactor_new' title='ثبت' class='btn btn-outline-success btn-block mg-t-10' onclick='cmsFactor.insertItemFactorInFactor();'>ثبت</button>"));
            Map<String, Object> map = new HashMap<>();
            map.put(FactorItem._discountPercent, jjTools.getParameter(request, FactorItem._discountPercent));
            map.put(FactorItem._discription, jjTools.getParameter(request, FactorItem._discription));
            map.put(FactorItem._originalPrice, jjTools.getParameter(request, FactorItem._originalPrice));
            map.put(FactorItem._percentageOfValueAdded, jjTools.getParameter(request, FactorItem._percentageOfValueAdded));
            map.put(FactorItem._priceAfterDiscount, jjTools.getParameter(request, FactorItem._priceAfterDiscount));
            map.put(FactorItem._productId, jjTools.getParameter(request, FactorItem._productId));
            map.put(FactorItem._quantity, jjNumber.isDigit(jjTools.getParameter(request, FactorItem._quantity)) ? jjTools.getParameter(request, FactorItem._quantity) : "0");
            map.put(FactorItem._totalPrice, jjTools.getParameter(request, FactorItem._totalPrice));
            map.put(FactorItem._valueAdded, jjTools.getParameter(request, FactorItem._valueAdded));
            if (!db.update(FactorItem.tableName, map, FactorItem._id + "=" + id)) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Server.outPrinter(request, response, html.toString() + "cmsFactor.m_refreshItemFactorInFactor(" + jjTools.getParameter(request, FactorItem._factorId) + ");");
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
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";

            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + id));
            if (row.size() == 0) {
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                    return "";

                }
                Server.outPrinter(request, response, "cmsFactor.m_show_tbl();cmsFactor.m_clean();" + Js.jjFactor.refresh());
                return "";
            } else {
                Server.outPrinter(request, response, Js.modal("این فاکتور شامل تعدادی آیتم است لطفا بعد از حذف آیتم های فاکتور اقدام به حذف فاکتور نمایید", "حذف فاکتور"));
                return "";
            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    /**
     * حذف ایتم در قسمت فاکتور
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String deleteItemFactorInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> mapFactor = new HashMap<>();
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._id + "=" + id));
            double tottalAmountValueAdded = Double.parseDouble(row.get(0).get(Factor._totalAmountValueAdded).toString());
            double valueAdded = Double.parseDouble(rowFactorItem.get(0).get(FactorItem._valueAdded).toString());
            mapFactor.put(Factor._totalAmountValueAdded, (tottalAmountValueAdded - valueAdded));
            double tottalAmount = Double.parseDouble(row.get(0).get(Factor._totalAmount).toString());
            double tottalPrice = Double.parseDouble(rowFactorItem.get(0).get(FactorItem._totalPrice).toString());
//            mapFactor.put(Factor._totalAmountValueAdded, Float.valueOf(row.get(0).get(Factor._totalAmountValueAdded).toString()) + Float.valueOf(jjTools.getParameter(request, _valueAdded)));
            mapFactor.put(Factor._totalAmount, tottalAmount - tottalPrice);
            if (!db.update(Factor.tableName, mapFactor, _id + "=" + jjTools.getParameter(request, FactorItem._factorId))) {
                String errorMessage = "در فرم مشکلی پیش آمده است";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, FactorItem._factorId)));
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#" + _totalAmountValueAdded, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmountValueAdded).toString())));
            html.append(Js.setVal("#" + _totalAmount, jjNumber.getFormattedNumber(rowFactor.get(0).get(_totalAmount).toString())));
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";

            }
            if (!db.delete(FactorItem.tableName, FactorItem._id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";

            }
            Server.outPrinter(request, response, html.toString() + "cmsFactor.m_refreshItemFactorInFactor(" + jjTools.getParameter(request, FactorItem._factorId) + ");");
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
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";

            }
            StringBuilder html = new StringBuilder();
            html.append(Js.show("#FactorItemInFactorForm"));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#product_factor_item_factorId1", row.get(0).get(_id)));
            html.append(Js.setVal("#" + _creator, row.get(0).get(_creator).toString()));
            html.append(Js.setVal("#product_factor_creatorName", Access_User.getUserName(row.get(0).get(_creator).toString(), db)));
            html.append(Js.setVal("#" + _companyName, row.get(0).get(_companyName)));
            html.append(Js.setVal("#" + _serialNumber, row.get(0).get(_serialNumber)));
            html.append(Js.setVal("#" + _statuse, row.get(0).get(_statuse)));
            html.append(Js.setVal("#" + _nationalCode, row.get(0).get(_nationalCode)));
            html.append(Js.setVal("#" + _economicCode, row.get(0).get(_economicCode)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _address2, row.get(0).get(_address2)));
            html.append(Js.setVal("#" + _tell, row.get(0).get(_tell)));
            html.append(Js.setVal("#" + _tell2, row.get(0).get(_tell2)));
            html.append(Js.setVal("#" + _zipCode, row.get(0).get(_zipCode)));
            html.append(Js.setVal("#" + _zipCode2, row.get(0).get(_zipCode2)));
            html.append(Js.setVal("#" + _totalAmount, jjNumber.getFormattedNumber(row.get(0).get(_totalAmount).toString())));
            html.append(Js.setVal("#" + _totalAmountValueAdded, jjNumber.getFormattedNumber(row.get(0).get(_totalAmountValueAdded).toString())));
            html.append(Js.setValSelectOption("#" + _userId, row.get(0).get(_userId).toString()));
            html.append(Js.select2("#" + _userId, ""));
            if (!row.get(0).get(_attach).toString().isEmpty()) {// برای اینکه اگر تهی بود یک فایل خالی ایجاد نکند
                String attachFiles[] = row.get(0).get(_attach).toString().split(",");
                for (int k = 0; k < attachFiles.length; k++) {
                    html.append(Js.append("#factor_attach_adminDiv", "<div class='col-lg-12 mg-l-15'><img class='wd-40 rounded-circle mg-r-20' src='upload/" + attachFiles[k] + "'><a target='_blank' href='upload/" + attachFiles[k] + "'>دانلود</a> <input class='" + _attach + "' type='hidden' value='" + attachFiles[k] + "'><div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i></div></div>"));
                }
            }
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
            if (accEdt) {
                html.append(Js.setHtml("#Factor_button_insert", "<button id=\"insert_Factor_edit\" title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjFactor.edit() + "'>" + lbl_edit + "</button>"));
            }
            if (accDel) {
                html.append(Js.setHtml("#Factor_button_delete", "<button id=\"insert_Factor_delete\" title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjFactor.delete(id) + "'>" + lbl_delete + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع برای سلکت کردن یک ایتم در فاکتور است
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String selectItemFactorInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._id + "=" + id));
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#FactorItem_id1", row.get(0).get(_id)));
            html.append(Js.setValSelectOption("#" + FactorItem._productId + 1, row.get(0).get(FactorItem._productId).toString()));
            html.append(Js.select2("#" + FactorItem._productId + 1, ""));
            html.append(Js.setVal("#" + FactorItem._quantity + 1, row.get(0).get(FactorItem._quantity)));
            html.append(Js.setVal("#" + FactorItem._originalPrice + 1, row.get(0).get(FactorItem._originalPrice)));
            html.append(Js.setVal("#unitPrice", Float.parseFloat(row.get(0).get(FactorItem._originalPrice).toString()) / Float.parseFloat(row.get(0).get(FactorItem._quantity).toString())));
            html.append(Js.setVal("#" + FactorItem._discountPercent + 1, row.get(0).get(FactorItem._discountPercent)));
            html.append(Js.setVal("#" + FactorItem._priceAfterDiscount + 1, row.get(0).get(FactorItem._priceAfterDiscount)));
            html.append(Js.setVal("#" + FactorItem._percentageOfValueAdded + 1, row.get(0).get(FactorItem._percentageOfValueAdded)));
            html.append(Js.setVal("#" + FactorItem._valueAdded + 1, row.get(0).get(FactorItem._valueAdded)));
            html.append(Js.setVal("#" + FactorItem._totalPrice + 1, row.get(0).get(FactorItem._totalPrice)));
            html.append(Js.setVal("#" + FactorItem._discription + 1, row.get(0).get(FactorItem._discription)));
            boolean accEdt = Access_User.hasAccess(request, db, FactorItem.rul_edt);
            if (accEdt) {
                html.append(Js.setHtml("#FactorItemButtonEdit", "<button id=\"FactorItem_edit\" title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-t-10' onclick='cmsFactor.editItemFactorInFactor();'>" + lbl_edit + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ایجاد فاکتور جدید برای ذخیره سبد کالا
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insertFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "='" + jjTools.getParameter(request, FactorItem._productId + "'")));
            double OriginalPrice = Double.parseDouble(jjTools.getParameter(request, FactorItem._originalPrice));
            double priceAfterDiscount = Double.parseDouble(jjTools.getParameter(request, FactorItem._priceAfterDiscount));
            double tax = Double.parseDouble(rowProduct.get(0).get(Product._taxPercent).toString());
            double peresentOfDiscount = (OriginalPrice - priceAfterDiscount) / (OriginalPrice / 100);
            double valueAdedd = ((priceAfterDiscount / 100) * tax);
            double totalAmount = priceAfterDiscount + valueAdedd;

            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_companyName, jjTools.getParameter(request, _companyName));
            map.put(_serialNumber, 0);
            map.put(_userId, jjTools.getParameter(request, _userId));
            map.put(_nationalCode, jjTools.getParameter(request, _nationalCode));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_economicCode, jjTools.getParameter(request, _economicCode));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_address2, jjTools.getParameter(request, _address2));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_tell2, jjTools.getParameter(request, _tell2));
            map.put(_zipCode, jjTools.getParameter(request, _zipCode));
            map.put(_zipCode2, jjTools.getParameter(request, _zipCode2));
            map.put(_statuse, jjTools.getParameter(request, _statuse));
            map.put(_totalAmountValueAdded, valueAdedd);
            map.put(_totalAmount, totalAmount);
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_time, dateIR.getTimeFormat_4length());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.size() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Map<String, Object> mapEdit = new HashMap<>();
            mapEdit.put(_serialNumber, row.get(0).get(_id));
            if (!db.update(tableName, mapEdit, _id + "=" + row.get(0).get(_id))) {
                String errorMessage = "عملیات ویرایش سریال فاکتور به درستی صورت نگرفت";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Map<String, Object> mapFactor = new HashMap<>();
            mapFactor.put(FactorItem._factorId, row.get(0).get(_id));
            mapFactor.put(FactorItem._discountPercent, peresentOfDiscount);
            mapFactor.put(FactorItem._originalPrice, OriginalPrice);
            mapFactor.put(FactorItem._percentageOfValueAdded, tax);
            mapFactor.put(FactorItem._priceAfterDiscount, priceAfterDiscount);
            mapFactor.put(FactorItem._productId, jjTools.getParameter(request, FactorItem._productId));
            if (jjTools.getParameter(request, FactorItem._quantity).equals("")) {
                mapFactor.put(FactorItem._quantity, 0);
            } else {
                mapFactor.put(FactorItem._quantity, jjTools.getParameter(request, FactorItem._quantity));
            }
            mapFactor.put(FactorItem._totalPrice, totalAmount);
            mapFactor.put(FactorItem._valueAdded, valueAdedd);
            mapFactor.put(FactorItem._date, dateIR.getDBFormat_8length());
            mapFactor.put(FactorItem._time, dateIR.getTimeFormat_4length());
            if (db.insert(FactorItem.tableName, mapFactor).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            jjTools.setSessionAttribute(request, "#FACTOR_ID", row.get(0).get(_id));
            StringBuffer html = new StringBuffer();
            html.append("<div class='card' style='border: none;'>\n"
                    + "    <div class='card-header tx-center' style='background: none;font-size: 26px;'>صورتحساب فروش کالا و خدمات</div>\n"
                    + "    <div class='card-body'>\n"
                    + "        <div class='roww'>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                شماره سریال فاکتور:   " + row.get(0).get(_id) + "\n"
                    + "            </div>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                تاریخ:   " + row.get(0).get(_date) + "\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + Tice_config.getValue(db, Tice_config._config_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شناسه ملی :   " + Tice_config.getValue(db, Tice_config._config_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + Tice_config.getValue(db, Tice_config._config_zipcodeCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + Tice_config.getValue(db, Tice_config._config_tellCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + Tice_config.getValue(db, Tice_config._config_addressCompany) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;margin-top: 5px;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Access_User.getUserName(row.get(0).get(_userId).toString(), db) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + row.get(0).get(_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + row.get(0).get(_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی/شناسه ملی :   " + row.get(0).get(_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + row.get(0).get(_zipCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + row.get(0).get(_tell) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + row.get(0).get(_address) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='roww' id='refreshItemFactorInFactor'>\n"
                    + "<div class='col-lg-12' style='text-align: left;'>");
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + row.get(0).get(_id)));
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshItemFactorInFactor' class='table display responsive'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>محصول</th>");
            html.append("<th class='c' width='10%'>قیمت اصلی</th>");
            html.append("<th class='c' width='10%'>درصد تخفیف</th>");
            html.append("<th class='c' width='10%'>قیمت بعد از تخفیف</th>");
            html.append("<th class='c' width='10%'>مبلغ ارزش افزوده </th>");
            html.append("<th class='c' width='10%'>درصد ارزش افزوده</th>");
            html.append("<th class='c' width='15%'>قیمت کل</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < rowFactorItem.size(); i++) {
                rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                html.append("<tr>"
                        + "<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
                html.append("<td class='c'>" + rowProduct.get(0).get(Product._name) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
                html.append("</tr>");
            }
            html.append("<tr>"
                    + "<td class='c'></td>");
            html.append("<td class='c'>جمع کل</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmountValueAdded) + "</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmount) + "</td>");
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("        </div></div>\n");
            //نمایش درگاه های پرداخت
            html.append("<div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>");
            List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
            if (!bankRow.isEmpty()) {
                for (int i = 0; i < bankRow.size(); i++) {
                    html.append(bankRow.get(i).get(PaymentSetting._bankName));
                    html.append("<input name='" + PaymentSetting._webService + "' id='" + bankRow.get(i).get(PaymentSetting._webService) + "' type='radio' value='" + bankRow.get(i).get(PaymentSetting._webService) + "'>");
                }
            } else {
                html.append("<input type='radio' style='display:none;' checked>");
            }
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
                    + "</div>"
                    + "    </div>\n"
                    + "</div>");
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "<button id='paymentFactor' title='پرداخت نهایی' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' onclick='payment(" + row.get(0).get(_totalAmount) + ");'>پرداخت نهایی</button>"
                    + "</div>"
                    + "    </div>\n"
                    + "</div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع برای ثبت فاکتور در قسمت ثبت سفارش در سایت است این متد بعد از ثبت
     * کردن فاکتور در جدول کاکتور ها کاربر را به صفحه ی فاکتور ارجاع میدهد
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insertFactorProductSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا برای ادامه فرایند خرید خود وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();

            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_companyName, jjTools.getParameter(request, _companyName));
            map.put(_serialNumber, 0);
            map.put(_userId, jjTools.getSessionAttribute(request, "#ID"));
            map.put(_nationalCode, jjTools.getParameter(request, _nationalCode));
            map.put(_economicCode, jjTools.getParameter(request, _economicCode));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_address2, jjTools.getParameter(request, _address2));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_tell2, jjTools.getParameter(request, _tell2));
            map.put(_zipCode, jjTools.getParameter(request, _zipCode));
            map.put(_zipCode2, jjTools.getParameter(request, _zipCode2));
            map.put(_totalAmountValueAdded, 0);
            map.put(_totalAmount, 0);
            map.put(_statuse, lbl_statuseUnPaid);
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_time, dateIR.getTimeFormat_4length());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Map<String, Object> mapEdit = new HashMap<>();
            mapEdit.put(_serialNumber, row.get(0).get(_id));
            if (!db.update(tableName, mapEdit, _id + "=" + row.get(0).get(_id))) {
                String errorMessage = "عملیات ویرایش سریال فاکتور به درستی صورت نگرفت";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }

            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            String[] productsId = null;
            String comment = "";
            String price = "";
            String where = "";
            Cookie[] cookies = request.getCookies();//آی دی کالاهای موجود در دیتابیس داخل کوکی دخیره شده است پس آنها را از کوکی میخانیم
            Cookie cookie = null;
            String productInCookieStr = "";
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equalsIgnoreCase("productsId")) {
                        productInCookieStr = cookies[i].getValue().replace("%2C", ",").replace("%3B", ";");
                        productInCookieStr = productInCookieStr.replaceFirst(";", "");// سمی کالن اولی را حذف میکنیم
                        productInCookieStr = productInCookieStr.replace(";;", ";");//

                        productInCookieStr = productInCookieStr.replaceAll(",.*;", ";");
                        ServerLog.Print("Product.buildShoppingCart =>Cookie: (product,number) " + productInCookieStr);
                        productsId = productInCookieStr.replaceAll(",.*;", ";").split(";");
                        ServerLog.Print("Product.buildShoppingCart =>Cookie: (product) " + productsId.toString());
                    } else //خواندن توضیحات همراه فاکتور
                    if (cookie.getName().equalsIgnoreCase(Factor._discription)) {
                        ServerLog.Print("Product.buildShoppingCart : COOKIE " + cookie.getValue());
                        comment = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8").replaceAll("[\"']", "");;
                        System.out.println(comment);
                    }
                }
            } else {
                return Js.dialog("خطا");
            }
            String[] productsIdCountArray = productInCookieStr.split(";");
            for (int i = 0; i < productsIdCountArray.length; i++) {
                if (i == productsIdCountArray.length - 1) {
                    where += Product._id + " = " + productsIdCountArray[i];
                } else {
                    where += Product._id + " = " + productsIdCountArray[i] + " OR ";
                }
            }
            List<Map<String, Object>> rowProducts = jjDatabase.separateRow(db.Select(Product.tableName, where));
            if (rowProducts.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.modal(errorMessage, "خطای سیستم");
            }
            for (int i = 0; i < rowProducts.size(); i++) {
                ///=========Price======>
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                            price = rowProducts.get(i).get(Product._price2).toString();
                        } else {
                            price = rowProducts.get(i).get(Product._discount).toString();
                        }
                    } else {
                        price = rowProducts.get(i).get(Product._price2).toString();
                    }
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
//                            System.out.println("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup1).toString())) {
                                String price1 = "".equals(rowProducts.get(i).get(Product._groupPrice1).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice1).toString();
                                price = price1;
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup2).toString())) {
                                String price2 = "".equals(rowProducts.get(i).get(Product._groupPrice2).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice2).toString();
                                if (price.equals("")) {
                                    price = price2;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                        price = price2;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup3).toString())) {
                                String price3 = "".equals(rowProducts.get(i).get(Product._groupPrice3).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice3).toString();
                                if (price.equals("")) {
                                    price = price3;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                        price = price3;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup4).toString())) {
                                String price4 = rowProducts.get(i).get(Product._groupPrice4).toString();
                                if (price.equals("")) {
                                    price = price4;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                        price = price4;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup5).toString())) {
                                String price5 = "".equals(rowProducts.get(i).get(Product._groupPrice5).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice5).toString();
                                if (price.equals("")) {
                                    price = price5;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                        price = price5;
                                    }
                                }
                            }
                        }
                        if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                                String price6 = rowProducts.get(i).get(Product._price2).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            } else {
                                String price6 = rowProducts.get(i).get(Product._discount).toString();
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
                                price = rowProducts.get(i).get(Product._price2).toString();
                            } else {
                                if (Integer.parseInt(price) > Integer.parseInt(rowProducts.get(i).get(Product._price2).toString())) {
                                    price = rowProducts.get(i).get(Product._price2).toString();
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                                price = rowProducts.get(i).get(Product._price2).toString();
                            } else {
                                price = rowProducts.get(i).get(Product._discount).toString();
                            }
                        } else {
                            price = rowProducts.get(i).get(Product._price2).toString();
                        }
                    }
                }
                double OriginalPrice = Double.parseDouble(rowProducts.get(i).get(Product._price2).toString());
                double priceAfterDiscount = Double.parseDouble(price);
                double tax = "".equals(rowProducts.get(i).get(Product._taxPercent).toString()) ? 0 : Double.parseDouble(rowProducts.get(i).get(Product._taxPercent).toString());
                double peresentOfDiscount = (OriginalPrice - priceAfterDiscount) / (OriginalPrice / 100);
                priceAfterDiscount = priceAfterDiscount * ("".equals(jjTools.getParameter(request, "factor_quantity" + rowProducts.get(i).get(_id))) ? 1 : Double.parseDouble(jjTools.getParameter(request, "factor_quantity" + rowProducts.get(i).get(_id))));
                double valueAdedd = ((priceAfterDiscount / 100) * tax);
                double totalAmount = priceAfterDiscount + valueAdedd;
                Map<String, Object> mapFactor = new HashMap<>();
                mapFactor.put(FactorItem._factorId, row.get(0).get(_id));
                mapFactor.put(FactorItem._discountPercent, peresentOfDiscount);
                mapFactor.put(FactorItem._originalPrice, OriginalPrice);
                mapFactor.put(FactorItem._percentageOfValueAdded, tax);
                mapFactor.put(FactorItem._priceAfterDiscount, priceAfterDiscount);
                mapFactor.put(FactorItem._productId, rowProducts.get(i).get(Product._id));
                if (jjTools.getParameter(request, "factor_quantity" + i + 1).equals("")) {
                    mapFactor.put(FactorItem._quantity, 1);
                } else {
                    mapFactor.put(FactorItem._quantity, jjTools.getParameter(request, FactorItem._quantity));
                }
                mapFactor.put(FactorItem._totalPrice, totalAmount);
                mapFactor.put(FactorItem._valueAdded, valueAdedd);
                mapFactor.put(FactorItem._date, dateIR.getDBFormat_8length());
                mapFactor.put(FactorItem._time, dateIR.getTimeFormat_4length());
                if (db.insert(FactorItem.tableName, mapFactor).getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                    return "";
                }

                Map<String, Object> mapEditFactor = new HashMap<>();
                List<Map<String, Object>> FactorEdit = jjDatabase.separateRow(db.Select(tableName, _id + "=" + row.get(0).get(_id)));
                totalAmount = totalAmount + Double.parseDouble(FactorEdit.get(0).get(_totalAmount).toString());
                valueAdedd = valueAdedd + Double.parseDouble(FactorEdit.get(0).get(_totalAmountValueAdded).toString());
                mapEditFactor.put(_totalAmount, totalAmount);
                mapEditFactor.put(_totalAmountValueAdded, valueAdedd);
                if (!db.update(tableName, mapEditFactor, _id + "=" + row.get(0).get(_id))) {
                    String errorMessage = "عملیات ویرایش سریال فاکتور به درستی صورت نگرفت";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                    return "";
                }
            }
            jjTools.setSessionAttribute(request, "#FACTOR_ID", row.get(0).get(_id));
            Server.outPrinter(request, response, "$(location).attr('href', '/005/factor.jsp?id=" + row.get(0).get(_id) + "')");

//            row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + row.get(0).get(_id)));
            StringBuilder html = new StringBuilder();
//            html.append("<div class='col-12 card' style='border: none;'>\n"
//                    + "    <div class='card-header tx-center' style='background: none;font-size: 26px;'>صورتحساب فروش کالا و خدمات</div>\n"
//                    + "    <div class='card-body'>\n"
//                    + "        <div class='roww'>\n"
//                    + "            <div class='col-lg-6'>\n"
//                    + "                شماره سریال فاکتور:   " + row.get(0).get(_id) + "\n"
//                    + "            </div>\n"
//                    + "            <div class='col-lg-6'>\n"
//                    + "                تاریخ:   " + row.get(0).get(_date) + "\n"
//                    + "            </div>\n"
//                    + "        </div>\n"
//                    + "        <div class='card' style='border: none;'>\n"
//                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>\n"
//                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
//                    + "                <div class='roww'>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        نام شخص حقیقی و حقوقی:   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        نام شرکت :   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شماره اقتصادی :   " + Tice_config.getValue(db, Tice_config._config_economicCode) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شناسه ملی :   " + Tice_config.getValue(db, Tice_config._config_nationalCode) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        کد پستی :   " + Tice_config.getValue(db, Tice_config._config_zipcodeCompany) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شماره تلفن و نمابر:   " + Tice_config.getValue(db, Tice_config._config_tellCompany) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-12'>\n"
//                    + "                        نشانی :   " + Tice_config.getValue(db, Tice_config._config_addressCompany) + "\n"
//                    + "                    </div>\n"
//                    + "                </div>\n"
//                    + "            </div>\n"
//                    + "        </div>\n"
//                    + "        <div class='card' style='border: none;margin-top: 5px;'>\n"
//                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>\n"
//                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
//                    + "                <div class='roww'>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        نام شخص حقیقی و حقوقی:   " + Access_User.getUserName(row.get(0).get(_userId).toString(), db) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        نام شرکت :   " + row.get(0).get(_companyName) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شماره اقتصادی :   " + row.get(0).get(_economicCode) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شماره ملی /شماسه ملی:   " + row.get(0).get(_nationalCode) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        کد پستی :   " + row.get(0).get(_zipCode) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-4'>\n"
//                    + "                        شماره تلفن و نمابر:   " + row.get(0).get(_tell) + "\n"
//                    + "                    </div>\n"
//                    + "                    <div class='col-lg-12'>\n"
//                    + "                        نشانی :   " + row.get(0).get(_address) + "\n"
//                    + "                    </div>\n"
//                    + "                </div>\n"
//                    + "            </div>\n"
//                    + "        </div>\n"
//                    + "        <div class='roww' id='refreshItemFactorInFactor'>\n"
//                    + "<div class='col-lg-12' style='text-align: left;'>");
//            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + row.get(0).get(_id)));
//            html.append("<div class=\"table-wrapper\">\n");
//            html.append("<table id='refreshItemFactorInFactor' class='table display responsive'><thead>");
//            html.append("<th class='c' width='5%'>کد</th>");
//            html.append("<th class='c' width='20%'>محصول</th>");
//            html.append("<th class='c' width='10%'>قیمت اصلی</th>");
//            html.append("<th class='c' width='10%'>تعداد</th>");
//            html.append("<th class='c' width='10%'>درصد تخفیف</th>");
//            html.append("<th class='c' width='10%'>قیمت بعد از تخفیف</th>");
//            html.append("<th class='c' width='10%'>مبلغ ارزش افزوده </th>");
//            html.append("<th class='c' width='10%'>درصد ارزش افزوده</th>");
//            html.append("<th class='c' width='15%'>قیمت کل</th>");
//            html.append("</thead><tbody>");
//            for (int i = 0; i < rowFactorItem.size(); i++) {
//                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
//                html.append("<tr>"
//                        + "<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
//                html.append("<td class='c'>" + rowProduct.get(0).get(Product._name) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
//                html.append("<td class='c'>" + (jjTools.getParameter(request, "factor_quantity" + rowProduct.get(0).get(Product._id))) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString())) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString())) + "</td>");
//                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
//                html.append("</tr>");
//            }
//            html.append("<tr>"
//                    + "<td class='c'></td>");
//            html.append("<td class='c'>جمع کل</td>");
//            html.append("<td class='c'></td>");
//            html.append("<td class='c'></td>");
//            html.append("<td class='c'></td>");
//            html.append("<td class='c'></td>");
//            html.append("<td class='c'>" + row.get(0).get(_totalAmountValueAdded) + "</td>");
//            html.append("<td class='c'></td>");
//            html.append("<td class='c'>" + row.get(0).get(_totalAmount) + "</td>");
//            html.append("</tr>");
//            html.append("</tbody></table>");
//            html.append("        </div></div>\n");
//            //نمایش درگاه های پرداخت
//            html.append("<div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>");
//            List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
//            if (!bankRow.isEmpty()) {
//                for (int i = 0; i < bankRow.size(); i++) {
//                    html.append(bankRow.get(i).get(PaymentSetting._bankName));
//                    html.append("<input name='" + PaymentSetting._webService + "' id='" + bankRow.get(i).get(PaymentSetting._webService) + "' type='radio' value='" + bankRow.get(i).get(PaymentSetting._webService) + "'>");
//                }
//            } else {
//                html.append("<input type='radio' style='display:none;' checked>");
//            }
//            html.append("</div>"
//                    + "<div class='col-lg-12'>"
//                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
//                    + "</div>"
//                    + "    </div>\n"
//                    + "</div>");
//            html.append("</div>"
//                    + "<div class='col-lg-12'>"
//                    + "<button id='paymentFactor' title='پرداخت نهایی' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' onclick='payment(" + row.get(0).get(_totalAmount) + ");'>پرداخت نهایی</button>"
//                    + "</div>"
//                    + "    </div>\n"
//                    + "</div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            //@ToDo اینها باید برداشته بشه
            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * این تابع برای ثبت فاکتور در قسمت ثبت سفارش در اپلیکیشن است
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insertFactorProductApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا برای ادامه فرایند خرید خود وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();

            map.put(_creator, jjTools.getSeassionUserId(request));
            map.put(_companyName, jjTools.getParameter(request, _companyName));
            map.put(_serialNumber, 0);
            map.put(_userId, jjTools.getParameter(request, _userId));
            map.put(_nationalCode, jjTools.getParameter(request, _nationalCode));
            map.put(_economicCode, jjTools.getParameter(request, _economicCode));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_address2, jjTools.getParameter(request, _address2));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_tell2, jjTools.getParameter(request, _tell2));
            map.put(_zipCode, jjTools.getParameter(request, _zipCode));
            map.put(_zipCode2, jjTools.getParameter(request, _zipCode2));
            map.put(_totalAmountValueAdded, 0);
            map.put(_totalAmount, 0);
            map.put(_statuse, lbl_statuseUnPaid);
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_time, dateIR.getTimeFormat_4length());
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.size() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }
            Map<String, Object> mapEdit = new HashMap<>();
            mapEdit.put(_serialNumber, row.get(0).get(_id));
            if (!db.update(tableName, mapEdit, _id + "=" + row.get(0).get(_id))) {
                String errorMessage = "عملیات ویرایش سریال فاکتور به درستی صورت نگرفت";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                return "";
            }

            jjCalendar_IR date = new jjCalendar_IR();
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            String[] productsId = jjTools.getParameter(request, "productsId").split(",");
            String comment = "";
            String price = "";
            String where = "";
            for (int i = 0; i < productsId.length; i++) {
                if (i == productsId.length - 1) {
                    where += Product._id + " = " + productsId[i];
                } else {
                    where += Product._id + " = " + productsId[i] + " OR ";
                }
            }
            List<Map<String, Object>> rowProducts = jjDatabase.separateRow(db.Select(Product.tableName, where));
            if (rowProducts.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.modal(errorMessage, "خطای سیستم");
            }
            for (int i = 0; i < rowProducts.size(); i++) {
                ///=========Price======>
                if (userId.equals("")) {//اگر کاربر لاگین نکرده باشد و آی دی اش در سشن نباشد
                    if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                        if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                            price = rowProducts.get(i).get(Product._price2).toString();
                        } else {
                            price = rowProducts.get(i).get(Product._discount).toString();
                        }
                    } else {
                        price = rowProducts.get(i).get(Product._price2).toString();
                    }
                } else {//اگر کاربر لاگین کرده باشد
                    List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + " = " + userId));//گروه کاربر را واکشی می کند
                    if (!userRow.isEmpty()) {//اگر کاربر عضو گروهی باشد که قیمت ویژه برای آن ست شده است قیمت مربوط به آن گروه نمایش داده می شود
                        for (int j = 0; j < userRow.size(); j++) {
                            String userGroup = userRow.get(j).get(Access_Group_User._group_id).toString();
//                            System.out.println("userGroup " + userGroup);
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup1).toString())) {
                                String price1 = "".equals(rowProducts.get(i).get(Product._groupPrice1).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice1).toString();
                                price = price1;
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup2).toString())) {
                                String price2 = "".equals(rowProducts.get(i).get(Product._groupPrice2).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice2).toString();
                                if (price.equals("")) {
                                    price = price2;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price2)) {
                                        price = price2;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup3).toString())) {
                                String price3 = "".equals(rowProducts.get(i).get(Product._groupPrice3).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice3).toString();
                                if (price.equals("")) {
                                    price = price3;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price3)) {
                                        price = price3;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup4).toString())) {
                                String price4 = rowProducts.get(i).get(Product._groupPrice4).toString();
                                if (price.equals("")) {
                                    price = price4;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price4)) {
                                        price = price4;
                                    }
                                }
                            }
                            if (userGroup.equalsIgnoreCase(rowProducts.get(i).get(Product._userGroup5).toString())) {
                                String price5 = "".equals(rowProducts.get(i).get(Product._groupPrice5).toString()) ? rowProducts.get(i).get(Product._price2).toString() : rowProducts.get(i).get(Product._groupPrice5).toString();
                                if (price.equals("")) {
                                    price = price5;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price5)) {
                                        price = price5;
                                    }
                                }
                            }
                        }
                        if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                                String price6 = rowProducts.get(i).get(Product._price2).toString();
                                if (price.equals("")) {
                                    price = price6;
                                } else {
                                    if (Integer.parseInt(price) > Integer.parseInt(price6)) {
                                        price = price6;
                                    }
                                }
                            } else {
                                String price6 = rowProducts.get(i).get(Product._discount).toString();
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
                                price = rowProducts.get(i).get(Product._price2).toString();
                            } else {
                                if (Integer.parseInt(price) > Integer.parseInt(rowProducts.get(i).get(Product._price2).toString())) {
                                    price = rowProducts.get(i).get(Product._price2).toString();
                                }
                            }
                        }
                    } else {//اگر کاربر عادی باشد قیمت اصلی نمایش داده میشود
                        if (Integer.parseInt(rowProducts.get(i).get(Product._discountDate).toString()) >= date.getDBFormat_8length()) {
                            if ("".equals(rowProducts.get(i).get(Product._discount).toString())) {
                                price = rowProducts.get(i).get(Product._price2).toString();
                            } else {
                                price = rowProducts.get(i).get(Product._discount).toString();
                            }
                        } else {
                            price = rowProducts.get(i).get(Product._price2).toString();
                        }
                    }
                }
                double OriginalPrice = Double.parseDouble(rowProducts.get(i).get(Product._price2).toString());
                double priceAfterDiscount = Double.parseDouble(price);
                double tax = Double.parseDouble(rowProducts.get(i).get(Product._taxPercent).toString());
                double peresentOfDiscount = (OriginalPrice - priceAfterDiscount) / (OriginalPrice / 100);
                priceAfterDiscount = priceAfterDiscount * Double.parseDouble(jjTools.getParameter(request, "factor_quantity" + rowProducts.get(i).get(_id)));
                double valueAdedd = ((priceAfterDiscount / 100) * tax);
                double totalAmount = priceAfterDiscount + valueAdedd;
                Map<String, Object> mapFactor = new HashMap<>();
                mapFactor.put(FactorItem._factorId, row.get(0).get(_id));
                mapFactor.put(FactorItem._discountPercent, peresentOfDiscount);
                mapFactor.put(FactorItem._originalPrice, OriginalPrice);
                mapFactor.put(FactorItem._percentageOfValueAdded, tax);
                mapFactor.put(FactorItem._priceAfterDiscount, priceAfterDiscount);
                mapFactor.put(FactorItem._productId, rowProducts.get(i).get(Product._id));
                if (jjTools.getParameter(request, "factor_quantity" + i + 1).equals("")) {
                    mapFactor.put(FactorItem._quantity, 1);
                } else {
                    mapFactor.put(FactorItem._quantity, jjTools.getParameter(request, FactorItem._quantity));
                }
                mapFactor.put(FactorItem._totalPrice, totalAmount);
                mapFactor.put(FactorItem._valueAdded, valueAdedd);
                mapFactor.put(FactorItem._date, dateIR.getDBFormat_8length());
                mapFactor.put(FactorItem._time, dateIR.getTimeFormat_4length());
                if (db.insert(FactorItem.tableName, mapFactor).getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                    return "";
                }

                Map<String, Object> mapEditFactor = new HashMap<>();
                List<Map<String, Object>> FactorEdit = jjDatabase.separateRow(db.Select(tableName, _id + "=" + row.get(0).get(_id)));
                totalAmount = totalAmount + Double.parseDouble(FactorEdit.get(0).get(_totalAmount).toString());
                valueAdedd = valueAdedd + Double.parseDouble(FactorEdit.get(0).get(_totalAmountValueAdded).toString());
                mapEditFactor.put(_totalAmount, totalAmount);
                mapEditFactor.put(_totalAmountValueAdded, valueAdedd);
                if (!db.update(tableName, mapEditFactor, _id + "=" + row.get(0).get(_id))) {
                    String errorMessage = "عملیات ویرایش سریال فاکتور به درستی صورت نگرفت";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "Edit Fail;";
                    }
                    Server.outPrinter(request, response, Js.modal(errorMessage, "خطای سیستم"));
                    return "";
                }
            }
            jjTools.setSessionAttribute(request, "#FACTOR_ID", row.get(0).get(_id));
            row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + row.get(0).get(_id)));
            StringBuffer html = new StringBuffer();
            html.append("<div class='col-12 card' style='border: none;'>\n"
                    + "    <div class='card-header tx-center' style='background: none;font-size: 26px;'>صورتحساب فروش کالا و خدمات</div>\n"
                    + "    <div class='card-body'>\n"
                    + "        <div class='roww'>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                شماره سریال فاکتور:   " + row.get(0).get(_id) + "\n"
                    + "            </div>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                تاریخ:   " + row.get(0).get(_date) + "\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + Tice_config.getValue(db, Tice_config._config_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شناسه ملی :   " + Tice_config.getValue(db, Tice_config._config_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + Tice_config.getValue(db, Tice_config._config_zipcodeCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + Tice_config.getValue(db, Tice_config._config_tellCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + Tice_config.getValue(db, Tice_config._config_addressCompany) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;margin-top: 5px;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Access_User.getUserName(row.get(0).get(_userId).toString(), db) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + row.get(0).get(_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + row.get(0).get(_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی /شماسه ملی:   " + row.get(0).get(_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + row.get(0).get(_zipCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + row.get(0).get(_tell) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + row.get(0).get(_address) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='row' id='refreshItemFactorInFactor'>\n"
                    + "<div class='col-lg-12' style='text-align: left;'>");
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + row.get(0).get(_id)));
            html.append("<div class='card shadow mb-4'>");
            html.append("<div class='card-header py-3' style='text-align: right;'>");
            html.append("<h6 class='m-0 font-weight-bold text-primary'>ریز فاکتور</h6>");
            html.append("</div>");
            html.append("<div class='card-body'>");
            html.append("<div class='table-responsive' style='margin-top:20px;'>");
            html.append("<table class='table table-bordered' id='refreshItemFactorInFactor' width='100%' cellspacing='0'>");
            html.append("<thead>");
            html.append("<th>کد</th>");
            html.append("<th>محصول</th>");
            html.append("<th>قیمت اصلی</th>");
            html.append("<th>تعداد</th>");
            html.append("<th>درصد تخفیف</th>");
            html.append("<th>قیمت بعد از تخفیف</th>");
            html.append("<th>مبلغ ارزش افزوده </th>");
            html.append("<th>درصد ارزش افزوده</th>");
            html.append("<th>قیمت کل</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < rowFactorItem.size(); i++) {
                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                html.append("<tr>"
                        + "<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
                html.append("<td>" + rowProduct.get(0).get(Product._name) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
                html.append("<td>" + (jjTools.getParameter(request, "factor_quantity" + rowProduct.get(0).get(Product._id))) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString())) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString())) + "</td>");
                html.append("<td>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
                html.append("</tr>");
            }
            html.append("<tr>"
                    + "<td></td>");
            html.append("<td>جمع کل</td>");
            html.append("<td></td>");
            html.append("<td></td>");
            html.append("<td></td>");
            html.append("<td></td>");
            html.append("<td>" + jjNumber.getFormattedNumber(row.get(0).get(_totalAmountValueAdded).toString()) + "</td>");
            html.append("<td></td>");
            html.append("<td>" + jjNumber.getFormattedNumber(row.get(0).get(_totalAmount).toString()) + "</td>");
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("</div></div></div></div></div>");
            //نمایش درگاه های پرداخت
            html.append("<div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>");
            List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
            if (!bankRow.isEmpty()) {
                for (int i = 0; i < bankRow.size(); i++) {
                    html.append(bankRow.get(i).get(PaymentSetting._bankName));
                    html.append("<input name='" + PaymentSetting._webService + "' id='" + bankRow.get(i).get(PaymentSetting._webService) + "' type='radio' value='" + bankRow.get(i).get(PaymentSetting._webService) + "'>");
                }
            } else {
                html.append("<input type='radio' style='display:none;' checked>");
            }
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
                    + "</div>"
                    + "    </div>\n"
                    + "</div>");
            html.append("</div>");
            html.append("<div class='col-lg-12' style='margin-top:10px;margin-bottom:10px;'>");
            html.append("<a href='#' id='paymentFactor' onclick='payment(" + row.get(0).get(_totalAmount) + ");' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text' style='width: 100%;'>پرداخت نهایی</span></a>");
            html.append("</div>");
            html.append("<div style='margin-top: 60px;'></div>");
            html.append("    </div>\n"
                    + "</div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

////// ------------- refreshFactorStatus() ------------->
    public static String refreshFactorStatus(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String factorId = jjTools.getSessionAttribute(request, "#FACTOR_ID").toLowerCase();
            Map<String, Object> map = new HashMap<String, Object>();
            String refId = request.getParameter("SaleReferenceId");
//            String refId = request.getAttribute(Payment._refrenceId).toString();
            if (!db.update(tableName, map, _id + "=" + factorId)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.modal(errorMessage, "خطای سیستم");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
        return "";
    }

    /**
     * گرفتن اطلاعات کاربر و نشاندن در فیلد های فاکتور
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getUserInfoInFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String userId = jjTools.getParameter(request, "userId");
        if (jjNumber.isDigit(userId)) {
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + userId));
            if (!row.isEmpty()) {
                StringBuilder script = new StringBuilder();
                script.append(Js.setVal("#" + _nationalCode, row.get(0).get(Access_User._codeMeli)));
                script.append(Js.setVal("#" + _address, row.get(0).get(Access_User._address)));
                script.append(Js.setVal("#" + _zipCode, row.get(0).get(Access_User._postalCode)));
                script.append(Js.setVal("#" + _tell, row.get(0).get(Access_User._mobile)));
                Server.outPrinter(request, response, script);
            }
        }
        return "";
    }

    /**
     * گرفتن یوزر ها و قرار دادن در سلکت تو
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getUserFactor(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_User.tableName));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "product_factor_userId" : panel;
        html.append("<option id='0' value='0'>خریدار را وارد کنید...</option>");
        if (row.size() > 0) {
            for (int i = 0; i < row.size(); i++) {
                html.append("<option id='" + row.get(i).get(Access_User._id)
                        + "'  value='" + row.get(i).get(Access_User._id) + "'>"
                        + row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString() + " " + row.get(i).get(Access_User._codeMeli).toString()
                        + "</option>");
            }
        }
        Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.select2(panel, "width: '100%'"));
        return "";
    }

    /**
     * نشان دادن جزئیات یک فاکتور برای سایت
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showFactorSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            List<Map<String, Object>> rowProduct;
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName,_userId +"="+jjTools.getSessionAttribute(request, "#ID")));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + jjTools.getParameter(request, _id)));
            StringBuffer html = new StringBuffer();
            html.append("<div class='card' style='border: none;'>\n"
                    + "    <div class='card-header tx-center' style='background: none;font-size: 26px;'>صورتحساب فروش کالا و خدمات</div>\n"
                    + "    <div class='card-body'>\n"
                    + "        <div class='roww'>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                شماره سریال فاکتور:   " + row.get(0).get(_id) + "\n"
                    + "            </div>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                تاریخ:   " + row.get(0).get(_date) + "\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + Tice_config.getValue(db, Tice_config._config_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی :   " + Tice_config.getValue(db, Tice_config._config_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + Tice_config.getValue(db, Tice_config._config_zipcodeCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + Tice_config.getValue(db, Tice_config._config_tellCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + Tice_config.getValue(db, Tice_config._config_addressCompany) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;margin-top: 5px;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Access_User.getUserName(row.get(0).get(_userId).toString(), db) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + row.get(0).get(_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + row.get(0).get(_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی :   " + row.get(0).get(_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + row.get(0).get(_zipCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + row.get(0).get(_tell) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + row.get(0).get(_address) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='roww' id='refreshItemFactorInFactor'>\n"
                    + "<div class='col-lg-12' style='text-align: left;'>");
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + row.get(0).get(_id)));
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshItemFactorInFactor' class='table display responsive'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>محصول</th>");
            html.append("<th class='c' width='10%'>قیمت اصلی</th>");
            html.append("<th class='c' width='10%'>درصد تخفیف</th>");
            html.append("<th class='c' width='10%'>قیمت بعد از تخفیف</th>");
            html.append("<th class='c' width='10%'>مبلغ ارزش افزوده </th>");
            html.append("<th class='c' width='10%'>درصد ارزش افزوده</th>");
            html.append("<th class='c' width='15%'>قیمت کل</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < rowFactorItem.size(); i++) {
                rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                html.append("<tr>"
                        + "<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
                html.append("<td class='c'>" + rowProduct.get(0).get(Product._name) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
                html.append("</tr>");
            }
            html.append("<tr>"
                    + "<td class='c'></td>");
            html.append("<td class='c'>جمع کل</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmountValueAdded) + "</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmount) + "</td>");
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("        </div></div>\n");
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
                    + "</div>"
                    + "    </div>\n"
                    + "</div>");
            if (row.get(0).get(_statuse).toString().equals(lbl_statuseUnPaid)) {
                //نمایش درگاه های پرداخت
                html.append("<div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>");
                List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
                if (!bankRow.isEmpty()) {
                    for (int i = 0; i < bankRow.size(); i++) {
                        html.append(bankRow.get(i).get(PaymentSetting._bankName));
                        html.append("<input name='" + PaymentSetting._webService + "' id='" + bankRow.get(i).get(PaymentSetting._webService) + "' type='radio' value='" + bankRow.get(i).get(PaymentSetting._webService) + "'>");
                    }
                } else {
                    html.append("<input type='radio' style='display:none;' checked>");
                }

                html.append("</div>"
                        + "<div class='col-lg-12'>"
                        + "<button id='paymentFactor' title='پرداخت نهایی' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' onclick='payment(" + row.get(0).get(_totalAmount) + ");'>پرداخت نهایی</button>"
                        + "</div>"
                        + "    </div>\n"
                        + "</div>");
            }
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نشان دادن جزئیات یک فاکتور برای اپلیکیشن
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showFactorApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            List<Map<String, Object>> rowProduct;
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName,_userId +"="+jjTools.getSessionAttribute(request, "#ID")));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + jjTools.getParameter(request, _id)));
            StringBuffer html = new StringBuffer();
            html.append("<div class='card' style='border: none;'>\n"
                    + "    <div class='card-header tx-center' style='background: none;font-size: 26px;'>صورتحساب فروش کالا و خدمات</div>\n"
                    + "    <div class='card-body'>\n"
                    + "        <div class='roww'>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                شماره سریال فاکتور:   " + row.get(0).get(_id) + "\n"
                    + "            </div>\n"
                    + "            <div class='col-lg-6'>\n"
                    + "                تاریخ:   " + row.get(0).get(_date) + "\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + Tice_config.getValue(db, Tice_config._config_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + Tice_config.getValue(db, Tice_config._config_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی :   " + Tice_config.getValue(db, Tice_config._config_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + Tice_config.getValue(db, Tice_config._config_zipcodeCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + Tice_config.getValue(db, Tice_config._config_tellCompany) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + Tice_config.getValue(db, Tice_config._config_addressCompany) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='card' style='border: none;margin-top: 5px;'>\n"
                    + "            <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>\n"
                    + "            <div class='card-body bd-primary' style='border:0.5px #000 solid;'>\n"
                    + "                <div class='roww'>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شخص حقیقی و حقوقی:   " + Access_User.getUserName(row.get(0).get(_userId).toString(), db) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        نام شرکت :   " + row.get(0).get(_companyName) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره اقتصادی :   " + row.get(0).get(_economicCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره ملی :   " + row.get(0).get(_nationalCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        کد پستی :   " + row.get(0).get(_zipCode) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-4'>\n"
                    + "                        شماره تلفن و نمابر:   " + row.get(0).get(_tell) + "\n"
                    + "                    </div>\n"
                    + "                    <div class='col-lg-12'>\n"
                    + "                        نشانی :   " + row.get(0).get(_address) + "\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class='roww' id='refreshItemFactorInFactor'>");
            List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + row.get(0).get(_id)));
            html.append("<div class='card shadow mb-4'>");
            html.append("<div class='card-header py-3'>");
            html.append("<h6 class='m-0 font-weight-bold text-primary'>ریز فاکتور</h6>");
            html.append("</div>");
            html.append("<div class='card-body'>");
            html.append("<div class='table-responsive' style='margin-top:20px;'>");
            html.append("<table class='table table-bordered' id='refreshItemFactorInFactor' width='100%' cellspacing='0'>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>محصول</th>");
            html.append("<th class='c' width='10%'>قیمت اصلی</th>");
            html.append("<th class='c' width='10%'>درصد تخفیف</th>");
            html.append("<th class='c' width='10%'>قیمت بعد از تخفیف</th>");
            html.append("<th class='c' width='10%'>مبلغ ارزش افزوده </th>");
            html.append("<th class='c' width='10%'>درصد ارزش افزوده</th>");
            html.append("<th class='c' width='15%'>قیمت کل</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < rowFactorItem.size(); i++) {
                rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                html.append("<tr>"
                        + "<td class='c'>" + rowFactorItem.get(i).get(FactorItem._id) + "</td>");
                html.append("<td class='c'>" + rowProduct.get(0).get(Product._name) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString())) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString())) + "</td>");
                html.append("</tr>");
            }
            html.append("<tr>"
                    + "<td class='c'></td>");
            html.append("<td class='c'>جمع کل</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmountValueAdded) + "</td>");
            html.append("<td class='c'></td>");
            html.append("<td class='c'>" + row.get(0).get(_totalAmount) + "</td>");
            html.append("</tr>");
            html.append("</tbody></table>");
            html.append("        </div></div>\n");
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
                    + "</div>"
                    + "</div>");

            if (row.get(0).get(_statuse).toString().equals(lbl_statuseUnPaid)) {
                //نمایش درگاه های پرداخت
                html.append("<div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>");
                List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
                if (!bankRow.isEmpty()) {
                    for (int i = 0; i < bankRow.size(); i++) {
                        html.append(bankRow.get(i).get(PaymentSetting._bankName));
                        html.append("<input name='" + PaymentSetting._webService + "' id='" + bankRow.get(i).get(PaymentSetting._webService) + "' type='radio' value='" + bankRow.get(i).get(PaymentSetting._webService) + "'>");
                    }
                } else {
                    html.append("<input type='radio' style='display:none;' checked>");
                }
                html.append("</div>"
                        + "<div class='col-lg-12' style='margin-top:10px;margin-bottom:10px;'>"
                        + "<a href='#' onclick='payment(" + row.get(0).get(_totalAmount) + ");' class='btn btn-success btn-icon-split' style='width: 100%;font-size:20px;'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text' style='width: 100%;'>پرداخت نهایی</span></a>"
                        + "</div>"
                        + "    </div>\n"
                        + "</div>");
                html.append("<div style='margin-top: 60px;'></div>");
            }
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش تمام فاکتور های من برای سایت
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showAllMyFactorSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _userId + "=" + jjTools.getSessionAttribute(request, "#ID")));
            StringBuffer html = new StringBuffer();
            html.append("<div class=\"col-12\">\n");
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshFactor' class='table display responsive'><thead>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>ایجاد کننده فاکتور</th>");
            html.append("<th class='c' width='15%'>قیمت کل</th>");
            html.append("<th class='c' width='15%'>وضعیت فاکتور</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='c'>" + Access_User.getUserName(row.get(i).get(_creator).toString(), db) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(row.get(i).get(_totalAmount).toString())) + "</td>");
                html.append("<td class='c' onclick='showDetailsInsertMyFactor(" + row.get(i).get(_id) + ");' style='cursor: pointer;'>" + row.get(i).get(_statuse) + "</td>");
                html.append("</tr>");
            }
            html.append("</table></div></div>");
            html.append("</div>"
                    + "<div class='col-lg-12'>"
                    + "<a onclick='window.location.reload();' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' style='cursor: pointer;'>انصراف</a>"
                    + "</div>"
                    + "    </div>\n"
                    + "</div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            String script1 = Js.table("#refreshFactor", "auto", 0, "", "فاکتورهای من");
            script1 += "$('#refreshItemFactorInFactor').DataTable( {\n"
                    + "        \"paging\":   false,\n"
                    + "        \"ordering\": false,\n"
                    + "        \"info\":     false,\n"
                    + "        \"searching\": false"
                    + "    } );";

            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * نمایش تمام فاکتور های من برای اپلیکیشن
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String showAllMyFactorApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, Js.modal("لطقا وارد شوید و در صورت نداشتن اکانت کاربری لطفا ثبت نام کنید", "ورود"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _userId + "=" + jjTools.getSessionAttribute(request, "#ID")));
            StringBuffer html = new StringBuffer();
            html.append("<div class='card shadow mb-4'>");
            html.append("<div class='card-header py-3'>");
            html.append("<h6 class='m-0 font-weight-bold text-primary'>فاکتور های من</h6>");
            html.append("</div>");
            html.append("<div class='card-body'>");
            html.append("<div class='table-responsive' style='margin-top:20px;'>");
            html.append("<table class='table table-bordered' id='refreshFactor' width='100%' cellspacing='0'>");
            html.append("<th class='c' width='5%'>کد</th>");
            html.append("<th class='c' width='30%'>ایجاد کننده فاکتور</th>");
            html.append("<th class='c' width='15%'>قیمت کل</th>");
            html.append("<th class='c' width='15%'>وضعیت فاکتور</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(_statuse).equals("پرداخت نشده")) {
                    html.append("<tr onclick='showDetailsInsertMyFactor(" + row.get(i).get(_id) + ");' style='cursor: pointer;background-color:#d52a1a73;color:#fff;'>");
                } else {
                    html.append("<tr onclick='showDetailsInsertMyFactor(" + row.get(i).get(_id) + ");' style='cursor: pointer;background-color:#1cc88a85;color:#fff;'>");
                }
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                html.append("<td class='c'>" + Access_User.getUserName(row.get(i).get(_creator).toString(), db) + "</td>");
                html.append("<td class='c'>" + (jjNumber.getFormattedNumber(row.get(i).get(_totalAmount).toString())) + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_statuse) + "</td>");
                html.append("</tr>");
            }
            html.append("</table></div></div>");
            html.append("<div class='col-lg-12'>"
                    + "تمامی قیمت ها به " + Tice_config.getValue(db, Tice_config._config_exchange_unite) + " میباشد"
                    + "</div>");
            html.append("</div>");

            html.append("<div style='margin-top: 60px;'></div>");
            StringBuilder script = new StringBuilder();
            script.append("$('html, body').animate({scrollTop: $('#sw').offset().top -100}, 500);");
            String script1 = Js.table("#refreshFactor", "auto", 0, "", "فاکتورهای من");
            script1 += "$('#refreshItemFactorInFactor').DataTable( {\n"
                    + "        \"paging\":   false,\n"
                    + "        \"ordering\": false,\n"
                    + "        \"info\":     false,\n"
                    + "        \"searching\": false"
                    + "    } );";

            Server.outPrinter(request, response, Js.setHtml("#sw", html.toString()) + script.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
