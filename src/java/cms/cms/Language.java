/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.cms;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.ServerLog;
import cms.tools.jjTools;
import static cms.tools.jjTools.today;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;

/**
 *
 * @author Rashidi
 */
public class Language {
    public static String tableName = "language";
    public static String _id = "id";
    public static String _name = "language_name";
    public static String _noItem = "language_no_item";
    public static String _available = "language_available";
    public static String _notAvailable = "language_not_available";
    public static String _remainTime = "language_remain_time";
    public static String _day = "language_day";
    public static String _hour = "language_hour";
    public static String _minute = "language_minute";
    public static String _like = "language_like";
    public static String _disLike = "language_dis_like";
    public static String _visited = "language_visited";
    public static String _addCart = "language_add_cart";
    public static String _related = "language_related";
    public static String _detail = "language_detail";
    public static String _product = "language_product";
    public static String _news = "language_news";
    public static String _more = "language_more";
    public static String _payment = "language_payment";
    public static String _contBuy = "language_cont_buy";
    public static String _isUser = "language_is_user";
    public static String _content = "language_content";
    public static String _isNotUser = "language_is_not_user";
    public static String _productName = "language_product_name";
    public static String _number = "language_number";
    public static String _price = "language_price";
    public static String _sumPrice = "language_sum_price";
    public static String _delete = "language_delete";
    public static String _sumPayment = "language_sumPayment";
    public static String _address = "language_address";
    public static String _recordNotAvalable = "language_recordNotAvalable";// چنین رکوردی وجود ندارد
    public static String _keyWord = "language_key_word";
    public static int rul_rfs = 481;
    public static int rul_ins;
    public static int rul_edt = 482;
    public static int rul_dlt;
//    public static int rul_reserved = 484 --- 500;// RESERVED : 484 -- 500

    ////// ---------------- setLang() ------------------->
    public static String setLang(HttpServletRequest request) {
        try {
            jjCalendar_IR ir = new jjCalendar_IR();
//            String nowDateAndTime = ir.toString();
            String nowDate = ir.getViewFormat_10length();
            if (today.equals("")) {
                today = nowDate;
            } else if (!today.equals(nowDate)) {
                today = nowDate;
//                beingSeassionToday = new StringBuffer();
            }

            if (!jjTools.getParameter(request, "myLang").equals("")) {
//                String ipAddress = request.getHeader("X-FORWARDED-FOR");
//                if (ipAddress == null) {
//                    ipAddress = request.getRemoteAddr();
//                }
//                String sesID = request.getSession(true).getId();
//                if (beingSeassionToday.toString().indexOf("#" + sesID + "#") == -1) {
//                    beingSeassionToday.append("#" + sesID + "#");
//                }

//                >>>> By mohammad commented................................................
//                if (!ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                    File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
//                    File visitor = new File(folderAddress.getAbsolutePath() + "/visitor.txt");
//                    String ipData = jjWebURL.getWebSiteSource("http://api.ipinfodb.com/v3/ip-city/?key=20b96dca8b9a5d37b0355e9461c66e76eed30a2274422fa6213d9de6ffb2b34e&ip=" + ipAddress);
//                    if (ipData.indexOf(";") > -1) {
//                        String[] split = ipData.split(";");
//                        ipData = split.length > 5 ? ipAddress + " - " + split[4] + " - " + split[5] + " - " + split[6] : "Unknow IP";
//                    }
//                    jjFileTxt.write(visitor, nowDateAndTime + " - " + ipData, true, true);
//                }
//                .......................................
                jjTools.setSessionAttribute(request, "myLang", jjTools.getParameter(request, "myLang").toLowerCase());
            }
            if (!jjTools.getSessionAttribute(request, "myLang").equals("1")) {
                if (!jjTools.getSessionAttribute(request, "myLang").equals("2")) {
                    if (!jjTools.getSessionAttribute(request, "myLang").equals("3")) {
                        if (!jjTools.getSessionAttribute(request, "myLang").equals("4")) {
                            if (!jjTools.getSessionAttribute(request, "myLang").equals("5")) {
                                jjTools.setSessionAttribute(request, "myLang", Server.defaultLang);
                            }
                        }
                    }
                }
            }
//            return Js.setHtml("#jjTodayUserCount", (beingSeassionToday.toString().split("##").length + 250));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <---------------- setLang() -------------------
    ////// ---------------- select() ------------------->
    /**
     * یک عبارت را به زبان خواسته شده بر می گرداند
     *
     * @param sentense
     * @param lang
     * @param db
     * @return
     * @throws Exception
     */
    public static String getSentense(String sentense, String lang, jjDatabaseWeb db) throws Exception {
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, sentense, _id + "=" + lang));
        return row.get(0).get(sentense).toString();
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String langId = jjTools.getParameter(request, "myLang");
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + langId));
            if (row.isEmpty()) {
                ServerLog.Print("رکوردی با این کد وجود ندارد.");
                return Js.dialog("رکوردی با این کد وجود ندارد.");
            }

            return script.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <---------------- select() -------------------
    ////// ---------------- refresh() ------------------->
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
//            String langId = jjTools.getParameter(request, "myLang");
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
            if (row.isEmpty()) {
                ServerLog.Print("رکوردی با این کد وجود ندارد.");
                return Js.dialog("رکوردی با این کد وجود ندارد.");
            }

            script.append(Js.setHtml("#" + _noItem, row.get(0).get(_noItem)));
            script.append(Js.setHtml("#" + _available, row.get(0).get(_available)));
            script.append(Js.setHtml("#" + _notAvailable, row.get(0).get(_notAvailable)));
            script.append(Js.setHtml("#" + _remainTime, row.get(0).get(_remainTime)));
            script.append(Js.setHtml("#" + _day, row.get(0).get(_day)));
            script.append(Js.setHtml("#" + _hour, row.get(0).get(_hour)));
            script.append(Js.setHtml("#" + _minute, row.get(0).get(_minute)));
            script.append(Js.setHtml("#" + _like, row.get(0).get(_like)));
            script.append(Js.setHtml("#" + _disLike, row.get(0).get(_disLike)));
            script.append(Js.setHtml("#" + _visited, row.get(0).get(_visited)));
            script.append(Js.setHtml("#" + _addCart, row.get(0).get(_addCart)));
            script.append(Js.setHtml("#" + _related, row.get(0).get(_related)));
            script.append(Js.setHtml("#" + _detail, row.get(0).get(_detail)));
            script.append(Js.setHtml("#" + _product, row.get(0).get(_product)));
            script.append(Js.setHtml("#" + _news, row.get(0).get(_news)));
            script.append(Js.setHtml("#" + _more, row.get(0).get(_more)));
            script.append(Js.setHtml("#" + _payment, row.get(0).get(_payment)));
            script.append(Js.setHtml("#" + _contBuy, row.get(0).get(_contBuy)));
            script.append(Js.setHtml("#" + _isUser, row.get(0).get(_isUser)));
            script.append(Js.setHtml("#" + _isNotUser, row.get(0).get(_isNotUser)));
            script.append(Js.setHtml("#" + _productName, row.get(0).get(_productName)));
            script.append(Js.setHtml("#" + _number, row.get(0).get(_number)));
            script.append(Js.setHtml("#" + _price, row.get(0).get(_price)));
            script.append(Js.setHtml("#" + _sumPrice, row.get(0).get(_sumPrice)));
            script.append(Js.setHtml("#" + _delete, row.get(0).get(_delete)));
            script.append(Js.setHtml("#" + _sumPayment, row.get(0).get(_sumPayment)));
            script.append(Js.setHtml("#" + _address, row.get(0).get(_address)));
            script.append(Js.setHtml("#" + _recordNotAvalable, row.get(0).get(_recordNotAvalable)));
            script.append(Js.setHtml("#" + _content, row.get(0).get(_content)));
//            script.append(Js.setHtml("#" + _keyWord, row.get(0).get(_keyWord)));
            int j = 0;
            for (int i = 0; i < 5; i++) {
                j = i + 1;
                script.append(Js.setVal("#" + _noItem + j, row.get(i).get(_noItem)));
                script.append(Js.setVal("#" + _available + j, row.get(i).get(_available)));
                script.append(Js.setVal("#" + _notAvailable + j, row.get(i).get(_notAvailable)));
                script.append(Js.setVal("#" + _remainTime + j, row.get(i).get(_remainTime)));
                script.append(Js.setVal("#" + _day + j, row.get(i).get(_day)));
                script.append(Js.setVal("#" + _hour + j, row.get(i).get(_hour)));
                script.append(Js.setVal("#" + _minute + j, row.get(i).get(_minute)));
                script.append(Js.setVal("#" + _like + j, row.get(i).get(_like)));
                script.append(Js.setVal("#" + _disLike + j, row.get(i).get(_disLike)));
                script.append(Js.setVal("#" + _visited + j, row.get(i).get(_visited)));
                script.append(Js.setVal("#" + _addCart + j, row.get(i).get(_addCart)));
                script.append(Js.setVal("#" + _related + j, row.get(i).get(_related)));
                script.append(Js.setVal("#" + _detail + j, row.get(i).get(_detail)));
                script.append(Js.setVal("#" + _product + j, row.get(i).get(_product)));
                script.append(Js.setVal("#" + _news + j, row.get(i).get(_news)));
                script.append(Js.setVal("#" + _more + j, row.get(i).get(_more)));
                script.append(Js.setVal("#" + _payment + j, row.get(i).get(_payment)));
                script.append(Js.setVal("#" + _contBuy + j, row.get(i).get(_contBuy)));
                script.append(Js.setVal("#" + _isUser + j, row.get(i).get(_isUser)));
                script.append(Js.setVal("#" + _isNotUser + j, row.get(i).get(_isNotUser)));
                script.append(Js.setVal("#" + _productName + j, row.get(i).get(_productName)));
                script.append(Js.setVal("#" + _number + j, row.get(i).get(_number)));
                script.append(Js.setVal("#" + _price + j, row.get(i).get(_price)));
                script.append(Js.setVal("#" + _sumPrice + j, row.get(i).get(_sumPrice)));
                script.append(Js.setVal("#" + _delete + j, row.get(i).get(_delete)));
                script.append(Js.setVal("#" + _sumPayment + j, row.get(i).get(_sumPayment)));
                script.append(Js.setVal("#" + _address + j, row.get(i).get(_address)));
                script.append(Js.setHtml("#" + _recordNotAvalable, row.get(0).get(_recordNotAvalable)));
                script.append(Js.setHtml("#" + _content, row.get(0).get(_content)));
//                script.append(Js.setVal("#" + _keyWord + j, row.get(i).get(_keyWord)));
            }
            return script.toString();

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ////// <---------------- refresh() -------------------
    ///---------------------------- edit ------------------>
    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuilder script = new StringBuilder();
            StringBuilder html = new StringBuilder();
            String errorMessage = "";
            for (int i = 1; i <= 5; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_noItem, jjTools.getParameter(request, _noItem + i));
                map.put(_available, jjTools.getParameter(request, _available + i));
                map.put(_notAvailable, jjTools.getParameter(request, _notAvailable + i));
                map.put(_remainTime, jjTools.getParameter(request, _remainTime + i));
                map.put(_day, jjTools.getParameter(request, _day + i));
                map.put(_hour, jjTools.getParameter(request, _hour + i));
                map.put(_minute, jjTools.getParameter(request, _minute + i));
                map.put(_like, jjTools.getParameter(request, _like + i));
                map.put(_disLike, jjTools.getParameter(request, _disLike + i));
                map.put(_visited, jjTools.getParameter(request, _visited + i));
                map.put(_addCart, jjTools.getParameter(request, _addCart + i));
                map.put(_related, jjTools.getParameter(request, _related + i));
                map.put(_detail, jjTools.getParameter(request, _detail + i));
                map.put(_product, jjTools.getParameter(request, _product + i));
                map.put(_news, jjTools.getParameter(request, _news + i));
                map.put(_more, jjTools.getParameter(request, _more + i));
                map.put(_payment, jjTools.getParameter(request, _payment + i));
                map.put(_contBuy, jjTools.getParameter(request, _contBuy + i));
                map.put(_isUser, jjTools.getParameter(request, _isUser + i));
                map.put(_isNotUser, jjTools.getParameter(request, _isNotUser + i));
                map.put(_productName, jjTools.getParameter(request, _productName + i));
                map.put(_number, jjTools.getParameter(request, _number + i));
                map.put(_price, jjTools.getParameter(request, _price + i));
                map.put(_sumPrice, jjTools.getParameter(request, _sumPrice + i));
                map.put(_delete, jjTools.getParameter(request, _delete + i));
                map.put(_sumPayment, jjTools.getParameter(request, _sumPayment + i));
                map.put(_address, jjTools.getParameter(request, _address + i));
                map.put(_recordNotAvalable, jjTools.getParameter(request, _recordNotAvalable + i));
                map.put(_content, jjTools.getParameter(request, _content + i));

//                map.put(_keyWord, jjTools.getParameter(request, _keyWord + i));
                if (!db.update(tableName, map, _id + " = " + i)) {
                    errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edit Failed;";
                    }
                } else {
                    errorMessage = "عملیات ویرایش به درستی صورت گرفت.";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Edited;";
                    }
                }

            }
            return Js.dialog(errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- edit ------------------
}
