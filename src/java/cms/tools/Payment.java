/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import behPardakhtMellat.bpmPayment;
import cms.access.Access_User;
import cms.cms.Factor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;
import zarinpal.Zarinpal;


/**
 *
 * @author Rashidi
 */
public class Payment {

    public static String tableName = "payment";
    public static String _id = "id";
    public static String _orderId = "payment_order_id";
    public static String _factorId = "payment_factor_id";
    public static String _userId = "payment_user_id";
    public static String _userName = "payment_user_Name";
    public static String _refrenceId = "payment_sale_refrence_id";
    public static String _amount = "payment_amount";
    public static String _status = "payment_status";
    public static String _statusLog = "payment_statusLog";
    //@ToDo اضافه کردن لاگ استیتو در کد ها
    public static String _comments = "payment_comments";
    //@ToDo اضافه کردن توضیحات در پنل در کد ها
    public static String _date = "payment_date";
    public static String status_sabteavalie = "ثبت اولیه";
    public static String status_pardakhtShode = "پرداخت شده";
    public static String status_pardakhtNaShode = "پرداخت نشده";   
    public static String lbl_delete = "حذف";
    public static int rul_rfs =410;
    public static int rul_dlt = 411;
//    public static int rul_ins = 183;
//    public static int rul_edt = 184;

    ////// ------------- payment() ------------->  
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshPayment' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>شماره فاکتور</th>");
            html.append("<th width='10%'>شماره سفارش</th>");
            html.append("<th width='15%'>مشتری</th>");
            html.append("<th width='10%'>شماره مرجع</th>");
            html.append("<th width='13%'>مبلغ</th>");
            html.append("<th width='20%'>وضعیت</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsPayment.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id).toString() + "</td>");
                html.append("<td class='l'>" + row.get(i).get(_factorId).toString() + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_orderId).toString() + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_userId).toString() + " . " + row.get(i).get(_userName).toString() + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_refrenceId).toString() + "</td>");
                html.append("<td class='l'>" + row.get(i).get(_amount).toString() + "</td>");
                html.append("<td class='l'>" + row.get(i).get(_status).toString() + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px' /></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swProduct10Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPayment", height, 0, "", "لیست تراکنش ها");
            return html2;
        } catch (Exception ex) { 
            return Server.ErrorHandler(ex);
        }
    }
    ////// <------------- refresh() -------------  
    ////// ------------- insert() ------------->  

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String userId = jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase());
            String where = Access_User._id + "='" + userId + "'";
            List<Map<String, Object>> rowUser = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, where));

            String refId = request.getParameter("SaleReferenceId");
            where = _refrenceId + "='" + refId + "'";
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, where));
            if (row.isEmpty()) {
                Map<String, Object> map = new HashMap<String, Object>();
                jjCalendar_IR dateIR = new jjCalendar_IR();
                map.put(_orderId, request.getAttribute(_orderId).toString());
                map.put(_factorId, jjTools.getSessionAttribute(request, "#FACTOR_ID").toLowerCase());
                map.put(_userId, userId);
                map.put(_userName, rowUser.size() == 0 ? "" : (rowUser.get(0).get(Access_User._name).toString() + " " + rowUser.get(0).get(Access_User._family).toString()));
                map.put(_refrenceId, refId);
                map.put(_amount, request.getAttribute(_amount));
                map.put(_status, request.getAttribute(_status).toString());
                map.put(_date, dateIR.getDBFormat_8length());
                Server.db.insert(tableName, map);
            }

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
        return "";
    }

    ////// <------------- insert() -------------    
    ////// ------------- select() ------------->  
    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String id = jjTools.getParameter(request, _id);
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
            html.append(Js.setVal(_orderId, row.get(0).get(_orderId)));
            html.append(Js.setVal(_userName, row.get(0).get(_userName)));
            html.append(Js.setVal(_refrenceId, row.get(0).get(_refrenceId)));
            html.append(Js.setVal(_amount, row.get(0).get(_amount)));
            html.append(Js.setVal(_status, row.get(0).get(_status)));
            html.append(Js.setVal(_date, row.get(0).get(_date)));
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_payment\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_payment", Js.jjPayment.delete(id)));
            }
            return (Js.setHtml("#Payment_button", html2.toString())) + html.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <------------- select() -------------    
    ////// ------------- delete() ------------->
    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
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
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPayment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <------------- delete() -------------    
    ////// ------------- refreshFactorStatus() ------------->
    public static String refreshFactorStatus(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        return Factor.refreshFactorStatus(request, db, isPost);
    }
//    public static String refreshFactorStatus(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
//        try {
//            String factorId = jjTools.getSessionAttribute(request, "#FACTOR_ID").toLowerCase();
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(_status, 1);
//            if (!db.update(Factor.tableName, map, Factor._id + "=" + factorId)) {
//                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Edit Fail;";
//                }
//                return Js.dialog(errorMessage);
//            }
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//        return "";
//    }
    ////// <------------- refreshFactorStatus() -------------  
      ////// ------------- payment() ------------->  
    public static String payment(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws IOException {
        try {
            String webService = jjTools.getParameter(request, PaymentSetting._webService);
            System.out.println("webServer=" + webService);
            ServerLog.Print("@@@ bank web service is :" + webService);
            if (webService.equals("behPardakhtMellat")) {
                Server.outPrinter(request, response, bpmPayment.bpmPayRequest(request, db, isPost));
                return "";
//	  return bpmPayment.bpmPayRequest(request, db, isPost);
            } else if (webService.equals("sadadMelli")) {
                return "";
//                return sadadMelli.sadadPayRequest(amount, orderId, request, db, isPost);
            } else if (webService.equals("zarinpal")) {
                Server.outPrinter(request, response, Zarinpal.ZarinPaymentRequest(request,response, db, isPost));
                return "";
//                return ZarinPal.xrdiniPaymentRequest(request, db, isPost);

            }
//                else if (webService.equals("parsian")) {
//	  return parsian.Parsian.doPayment(request, db, isPost);
//	}
//		else {
//                ServerLog.Print("loadOrderForm");
//                return "loadOrderForm();";
//            }
            return "";
//            if (true) {
//            }
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
        ////// <------------- payment() ------------- 

}
