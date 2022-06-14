package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FactorItem {

    public static String tableName = "product_factor_item";
    public static String _id = "id";
    public static String _productId = "product_factor_item_productId";//کد محصول
    public static String _factorId = "product_factor_item_factorId";// کد فاکتور
    public static String _quantity = "product_factor_item_quantity";// کد فاکتور
    public static String _item_Installment1 = "product_factor_item_Installment1";// تعداد
    public static String _item_Installment2 = "product_factor_item_Installment2";// تعداد
    public static String _item_Installment3 = "product_factor_item_Installment3";// تعداد
    public static String _item_Installment4 = "product_factor_item_Installment4";// تعداد
    public static String _originalPrice = "product_factor_item_originalPrice";// قیمت اصلی
    public static String _discountPercent = "product_factor_item_discountPercent";// درصد تخفیف
    public static String _priceAfterDiscount = "product_factor_item_priceAfterDiscount";// قیمت محصول بعد از تخفیف
    public static String _percentageOfValueAdded = "product_factor_item_percentageOfValueAdded";// درصد ارزش افزوده
    public static String _valueAdded = "product_factor_item_valueAdded";// میزان ارزش افزوده
    public static String _totalPrice = "product_factor_item_totalPrice"; // قیمت کل
    public static String _discription = "product_factor_item_discription";// توضیحات
    public static String _date = "product_factor_item_date";
    public static String _time = "product_factor_item_time";
    public static String _statuse = "product_factor_item_statuse";
    public static String _statuslog = "product_factor_statuseLog";
    public static String _dueDate = "product_factor_dueDate";
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
    public static int rul_rfsAll = 1000;
    public static int rul_rfs = 201;
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
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            List<Map<String, Object>> rowProduct;
            List<Map<String, Object>> row;
            List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName));
            if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                row = jjDatabase.separateRow(db.otherSelect("SELECT * FROM product_factor_item ORDER BY product_factor_item_date DESC,product_factor_item_time DESC;"));
            } else {
                if (!Access_User.hasAccess(request, db, rul_rfs)) {
                    Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                    return "";
                } else {
                    rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._creator + "=" + jjTools.getSeassionUserId(request)));
                    String where = "";
                    for (int i = 0; i < rowProduct.size(); i++) {
                        if (i == rowProduct.size() - 1) {
                            where += _productId + "=" + rowProduct.get(i).get(_id);
                        } else {
                            where += _productId + "=" + rowProduct.get(i).get(_id) + " or ";
                        }
                    }
                    row = jjDatabase.separateRow(db.Select(tableName, "*", where, _date + " DESC," + _time + " DESC"));
                }
            }

            StringBuilder html = new StringBuilder();

            html.append(" <div class='card bd-primary mg-t-20'>");
            html.append("    <div class='card-header bg-primary tx-white'>مدیریت آیتم های فاکتور</div>"
                    + "    <div class='card-body pd-sm-30'>");
            if (Access_User.hasAccess(request, db, rul_ins)) {
                html.append("<p class='mg-b-20 mg-sm-b-30'>");
                html.append("<a style='color:#fff' class='btn btn-success pd-sm-x-20 mg-sm-r-5 tx-white' onclick='cmsFactorItem.m_add_new();' >آیتم جدید</a>");
                html.append("</p>");
            }
            html.append("<div style='width: 100%; padding-left: -10px;'>"
                    + "<div class='table-responsive'>");
            html.append("<table id='refreshFactorItem' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'>"
                    + "<thead>");
            html.append("<th width='10%' class='r'>کد</th>");
            html.append("<th width='20%' class='c'>سریال فاکتور</th>");
            html.append("<th width='20%' class='c'>محصول</th>");
            html.append("<th width='20%' class='c'>جمع کل</th>");
            html.append("<th width='20%' class='c'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(_statuse).equals(lbl_statusePaid)) {
                    html.append("<tr style='background-color:#9eed9099;'>");
                    html.append("<td class='c' style='background-color:#9eed9099;'>" + (row.get(i).get(_id).toString()) + "</td>");
                } else {
                    html.append("<tr style='background-color:#f1bfbf99;'>");
                    html.append("<td class='c' style='background-color:#f1bfbf99;'>" + (row.get(i).get(_id).toString()) + "</td>");
                }
                html.append("<td class='l'>" + (row.get(i).get(_factorId).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_productId).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_totalPrice).toString())) + "</td>");
                html.append("<td class='c icon ion-ios-gear-outline' onclick='cmsFactorItem.m_select(" + row.get(i).get(_id) + ");' style='cursor: pointer;color:red;font-size:26px;'></td>");
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
                panel = "swFactorItemTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactorItem", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "16" : "", "لیست آیتم های من");

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
                html.append(Js.setHtml("#FactorItem_button_insert", "<button id=\"insert_Factor_new\" title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjFactorItem.insert() + "'>" + lbl_insert + "</button>"));
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
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            Map<String, Object> mapFactor = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(_factorId, jjTools.getParameter(request, _factorId));
            map.put(_discountPercent, jjNumber.isFloat(jjTools.getParameter(request, _discountPercent))?jjTools.getParameter(request, _discountPercent):"0" );
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_originalPrice, jjNumber.isFloat(jjTools.getParameter(request, _originalPrice))?jjTools.getParameter(request, _originalPrice):"0" );
            map.put(_percentageOfValueAdded,  jjNumber.isFloat(jjTools.getParameter(request, _percentageOfValueAdded))?jjTools.getParameter(request, _percentageOfValueAdded):"0" );
            map.put(_priceAfterDiscount,  jjNumber.isFloat(jjTools.getParameter(request, _priceAfterDiscount))?jjTools.getParameter(request, _priceAfterDiscount):"0" );
            map.put(_productId, jjTools.getParameter(request, _productId));
            if (jjTools.getParameter(request, _quantity).equals("")) {
                map.put(_quantity, 1);
            }
            map.put(_totalPrice,  jjNumber.isFloat(jjTools.getParameter(request, _totalPrice))?jjTools.getParameter(request, _totalPrice):"0" );
            map.put(_valueAdded,  jjNumber.isFloat(jjTools.getParameter(request, _valueAdded))?jjTools.getParameter(request, _valueAdded):"0" );
            map.put(_date, dateIR.getDBFormat_8length());
            map.put(_dueDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _priceAfterDiscount), true));
            map.put(_time, dateIR.getTimeFormat_4length());
            map.put(_statuse, lbl_statuseUnPaid);
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, _id + "=" + jjTools.getParameter(request, _factorId)));
            double tottalAmountValueAdded = Double.parseDouble(row.get(0).get(Factor._totalAmountValueAdded).toString());
            double valueAdded = Double.parseDouble(jjTools.getParameter(request, _valueAdded));
            mapFactor.put(Factor._totalAmountValueAdded, (tottalAmountValueAdded + valueAdded));
            System.out.println("////////////////////////////////////////" + (tottalAmountValueAdded + valueAdded));
            double tottalAmount = Double.parseDouble(row.get(0).get(Factor._totalAmount).toString());
            double tottalPrice = Double.parseDouble(jjTools.getParameter(request, _totalPrice));
//            mapFactor.put(Factor._totalAmountValueAdded, Float.valueOf(row.get(0).get(Factor._totalAmountValueAdded).toString()) + Float.valueOf(jjTools.getParameter(request, _valueAdded)));
            mapFactor.put(Factor._totalAmount, tottalAmount + tottalPrice);
            if (!db.update(Factor.tableName, mapFactor, _id + "=" + jjTools.getParameter(request, _factorId))) {
                String errorMessage = "در فرم مشکلی پیش آمده است";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjFactorItem.refresh());
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_ins)) {
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
                Server.outPrinter(request, response, Js.dialog(errorMessage));
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
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Map<String, Object> map = new HashMap<>();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            map.put(_factorId, jjTools.getParameter(request, _factorId));
            map.put(_discountPercent, jjTools.getParameter(request, _discountPercent));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_originalPrice, jjTools.getParameter(request, _originalPrice));
            map.put(_percentageOfValueAdded, jjTools.getParameter(request, _percentageOfValueAdded));
            map.put(_priceAfterDiscount, jjTools.getParameter(request, _priceAfterDiscount));
            map.put(_productId, jjTools.getParameter(request, _productId));
            map.put(_quantity, jjTools.getParameter(request, _quantity));
            map.put(_totalPrice, jjTools.getParameter(request, _totalPrice));
            map.put(_valueAdded, jjTools.getParameter(request, _valueAdded));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjFactorItem.refresh());
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
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
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
            Server.outPrinter(request, response, Js.jjFactorItem.refresh());
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";

        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            jjCalendar_IR dateIR = new jjCalendar_IR();
            html.append(Js.setVal("#FactorItem_id", row.get(0).get(_id)));
            html.append(Js.setVal("#" + _factorId, row.get(0).get(_factorId)));
            html.append(Js.setValSelectOption("#" + _productId, row.get(0).get(_productId).toString()));
            html.append(Js.select2("#" + _productId, ""));
            html.append(Js.setVal("#" + _quantity, row.get(0).get(_quantity)));
            html.append(Js.setVal("#" + _originalPrice, row.get(0).get(_originalPrice)));
            html.append(Js.setVal("#" + _discountPercent, row.get(0).get(_discountPercent)));
            html.append(Js.setVal("#" + _priceAfterDiscount, row.get(0).get(_priceAfterDiscount)));
            html.append(Js.setVal("#" + _percentageOfValueAdded, row.get(0).get(_percentageOfValueAdded)));
            html.append(Js.setVal("#" + _valueAdded, row.get(0).get(_valueAdded)));
            html.append(Js.setVal("#" + _totalPrice, row.get(0).get(_totalPrice)));
            html.append(Js.setVal("#" + _discription, row.get(0).get(_discription)));
            html.append(Js.setVal("unitPriceFactorItem", Float.parseFloat(row.get(0).get(FactorItem._originalPrice).toString()) / Float.parseFloat(row.get(0).get(FactorItem._quantity).toString())));

            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
            if (accEdt) {
                html.append(Js.setHtml("#FactorItem_button_insert", "<button id=\"insert_FactorItem_edit\" title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjFactorItem.edit() + "'>" + lbl_edit + "</button>"));
            }
            if (accDel) {
                html.append(Js.setHtml("#FactorItem_button_delete", "<button id=\"insert_FactorItem_delete\" title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjFactorItem.delete(id) + "'>" + lbl_delete + "</button>"));
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
                return Js.dialog(errorMessage);
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
        return "";
    }

    /**
     * نشان دادن محصول هایی که خود شخص ایجاد کرده است
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getProductFactorItem(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuilder html = new StringBuilder();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Product.tableName, Product._active+"="+1));
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "product_factor_item_productId" : panel;
        html.append("<option id='0' value='0'>مورد معامله را انتخاب کنید...</option>");
        if (row.size() > 0) {
            for (int i = 0; i < row.size(); i++) {
                html.append("<option id='" + row.get(i).get(Product._id)
                        + "'  value='" + row.get(i).get(Product._id) + "'>"
                        + row.get(i).get(Product._name).toString()
                        + "</option>");
            }
            Server.outPrinter(request, response, Js.setHtml("#" + panel, html) + Js.select2(panel, "width: '100%'"));
        }
        return "";
    }

    /**
     * نشان دادن محصول هایی که خود شخص ایجاد کرده است
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String getProductPrice(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String orginalPrice = "";
        StringBuilder html = new StringBuilder();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Product.tableName, _id + "=" + jjTools.getParameter(request, _id)));
        orginalPrice = jjNumber.isDigit(row.get(0).get(Product._price2).toString())?row.get(0).get(Product._price2).toString():"0" ;
        html.append(Js.setVal("#" + _originalPrice, orginalPrice));
        html.append(Js.setVal("#unitPrice", orginalPrice));
        html.append(Js.setVal("#unitPriceFactorItem", orginalPrice));
        html.append(Js.setVal("#product_factor_item_quantity1", 1));
        String valueAdded = jjNumber.isDigit(row.get(0).get(Product._taxPercent).toString())?row.get(0).get(Product._taxPercent).toString():"0" ;
        html.append(Js.setVal("#" + _percentageOfValueAdded, valueAdded));
        html.append(Js.setVal("#" + _valueAdded, (Integer.parseInt(orginalPrice) / 100) * Integer.parseInt(valueAdded)));
        html.append(Js.setVal("#" + _totalPrice, Integer.parseInt(orginalPrice) + (Integer.parseInt(orginalPrice) / 100) * Integer.parseInt(valueAdded)));
        html.append(Js.setVal("#product_factor_item_originalPrice1", orginalPrice));
        html.append(Js.setVal("#product_factor_item_percentageOfValueAdded1", valueAdded));
        html.append(Js.setVal("#product_factor_item_valueAdded1", (Integer.parseInt(orginalPrice) / 100) * Integer.parseInt(valueAdded)));
        html.append(Js.setVal("#product_factor_item_totalPrice1", Integer.parseInt(orginalPrice) + (Integer.parseInt(orginalPrice) / 100) * Integer.parseInt(valueAdded)));
        Server.outPrinter(request, response, html.toString());
        return "";
    }
}
