package cms.cms;

import cms.tools.*;
import cms.access.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Journal {

    public static String tableName = "jornal";
    public static String _id = "id";
    public static String _title = "jornal_title";//عنوان
    public static String _price = "jornal_price";//عنوان
    public static String _like = "jornal_like";//عنوان
    public static String _dislike = "jornal_dislike";//عنوان
    public static String _discription = "jornal_discription";//پدر والد
    public static String _parent = "jornal_parent";//پدر والد
    public static String _file = "jornal_file";//فایل
    public static String _date = "jornal_date";//تاریخ
    public static String _status = "jornal_status";//وضعیت
    public static String _pic = "jornal_pic";//عکس
    public static String _Issue = "jornal_Issue";//زمینه نشریه
    public static String _DateRelease = "jornal_DateRelease";//تاریخ انتشار
    public static String _ReleaseNumber = "jornal_ReleaseNumber";//
    public static String _Concessionaire = "jornal_Concessionaire";//صاحب امتیاز
    public static String _Responsible = "jornal_Responsible";//مسئول
    public static String _chiefEditor = "jornal_chiefEditor";//دبیر
    public static String _writer1 = "jornal_writer1";//دبیر
    public static String _writer2 = "jornal_writer2";//دبیر
    public static String _writer3 = "jornal_writer3";//دبیر
    public static String _visit = "jornal_visit";//دیدن
    public static String _hasInContentAutoWiki = "jornal_hasInContentAutoWiki";
    public static String _autoWikIsUpdate = "jornal_autoWikIsUpdate";//برای اینکه بفهمیم که اتوویکی زخیره شده نیاز به بروز رسانی دارد یا نه
    public static String _content = "jornal_content";
    public static String status_pending = "در حال بررسی";
    public static String status_ok = "تایید شده";
    public static String status_nok = "عدم تایید";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_ok = "تایید";
    public static String lbl_nok = "عدم تایید";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
//    public static int rul_rfs = 11;
//    public static int rul_edt = 12;
//    public static int rul_dlt = 13;
//    public static int rul_senEmail = 14;

    public static int rul_ins = 384;
    public static int rul_ok = 387;
    public static int rul_nok = 388;
    public static int rul_rfs = 1;
    public static int rul_rfsAll = 383;
    public static int rul_dlt = 386;
    public static int rul_edt = 385;
    public static int rul_senEmail = 4;
    public static int rul_print = 5;
//    public static int rul_reserved = 6 --- 20;  /// RESERVED : 6 --- 20

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuffer html = new StringBuffer();
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>نشریات</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>نشریات</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsJournal.m_add_new();' > مجوز جدید</a>"
                        + "        </p>");
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table class='table display responsive nowrap' id='refreshJournal' dir='rtl'><thead>");
            html.append("<th  style='text-align: center;' width='5%'>کد</th>");
            html.append("<th  style='text-align: center;' width='5%'>تاریخ</th>");
            html.append("<th  style='text-align: center;' width='5%'>نام نشریه</th>");
            html.append("<th  style='text-align: center;' width='5%'>مدیر مسئول</th>");
            html.append("<th  style='text-align: center;' width='5%'>وضعیت نشریه</th>");
            html.append("<th  style='text-align: center;' width='5%'>عملیات</th>");
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            if (accDel) {
                html.append("<th  style='text-align: center;' width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td  class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td  class='tahoma10' style='text-align: center;'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td  class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td  class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_Responsible).toString()) + "</td>");
                html.append("<td  class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_status).toString()) + "</td>");
                html.append("<td class='icon ion-ios-gear-outline c mousePointer' onclick='cmsJournal.m_select(" + row.get(i).get(_id) + ");'></td>");
                if (accDel) {
                    html.append("<td class='c mousePointer' onclick='cmsJournal.m_delete(" + row.get(i).get(_id) + ");'></td>");
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swJournalTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshComment", height, 0, "", "لیست پیام مدیر به کاربران");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            StringBuilder html = new StringBuilder();
            StringBuilder options = new StringBuilder();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#account_product_button", "<button title='" + lbl_insert + "' class='form-control btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjJournal.insert() + "' id='insert_account_product'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

//    public static String sendAnswer(HttpServletRequest request,HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_senEmail);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
//            String email = jjTools.getParameter(request, _email);
//            if (!jjValidation.isEmail(email)) {
//                String errorMessage = "ایمیل موجود معتبر نمی باشد.";
//                return Js.dialog(errorMessage);
//            }
//            if (!Server.sendEmail(Server.emailAccount, email, "Your Answer from " + Server.emailAccount, jjTools.getParameter(request, _answer), true, request,db)) {
//                String errorMessage = "ارسال پیام به درستی صورت نپذیرفت.";
//                return Js.dialog(errorMessage);
//            }
//            String message = "ارسال پیام به درستی صورت پذیرفت";
//            return Js.dialog(message);
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ok);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_date, new jjCalendar_IR().getDBFormat_8length());
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_Concessionaire, jjTools.getParameter(request, _Concessionaire));
            map.put(_DateRelease, jjTools.getParameter(request, _DateRelease));
            map.put(_Issue, jjTools.getParameter(request, _Issue));
            map.put(_ReleaseNumber, jjTools.getParameter(request, _ReleaseNumber));
            map.put(_Responsible, jjTools.getParameter(request, _Responsible));
            map.put(_chiefEditor, jjTools.getParameter(request, _chiefEditor));
            map.put(_pic, jjTools.getParameter(request, _pic));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_parent, jjTools.getParameter(request, _parent));
            map.put(_price, jjTools.getParameter(request, _price));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_writer1, jjTools.getParameter(request, _writer1));
            map.put(_writer2, jjTools.getParameter(request, _writer2));
            map.put(_writer3, jjTools.getParameter(request, _writer3));
            map.put(_status, status_pending);

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//             if (!Access_User.hasAccess(request, db, rul_ins)) {
//                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارین");
//                return "";
//            }
            Map<String, Object> map = new HashMap<String, Object>();
            String needToAutoWiki = jjTools.getParameter(request, _hasInContentAutoWiki);
//            if ("1".equalsIgnoreCase(needToAutoWiki)) {//اگر تیک خورده که محتوایش شامل اتو ویکی باشد که در اکثر مواقع اینطور است
//                String autoWikeContent = ConvertToWiki(request, jjTools.getParameter(request, _content), db, false);
//                map.put(_contentWithWikiLinks, autoWikeContent);
//            } else {//اگر تیک اتوویکی ندارد نیاز نیست محتوایش اتوویکی داشته باشد
//                map.put(_contentWithWikiLinks, jjTools.getParameter(request, _content));
//             
//            }
//            if ("1".equalsIgnoreCase(jjTools.getParameter(request, _hasLink))) {//اگر این محتوا باید اتوویکی بشود بنابر این باید اتوویکی همه ی محتواها آپدیت بشوند
//                db.otherStatement("Update " + tableName + " SET " + _autoWikIsUpdate + "=0 WHERE " + _hasInContentAutoWiki + "=1");//فقط آنهایی که در محتوایشان اتو ویکی دارند
//                //@ToDo برای جدوال اخبار و محتوا هم همین اتفاق باید باشد
//            }
            map.put(_date, new jjCalendar_IR().getDBFormat_8length());
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_status, status_pending);
            map.put(_pic, jjTools.getParameter(request, _pic));
            map.put(_parent, jjTools.getParameter(request, _parent));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_chiefEditor, jjTools.getParameter(request, _chiefEditor));
            map.put(_Responsible, jjTools.getParameter(request, _Responsible));
            map.put(_ReleaseNumber, jjTools.getParameter(request, _ReleaseNumber));
            map.put(_Issue, jjTools.getParameter(request, _Issue));
            map.put(_DateRelease, jjTools.getParameter(request, _DateRelease));
            map.put(_Concessionaire, jjTools.getParameter(request, _Concessionaire));
            map.put(_writer1, jjTools.getParameter(request, _writer1));
            map.put(_writer2, jjTools.getParameter(request, _writer2));
            map.put(_writer3, jjTools.getParameter(request, _writer3));
            map.put(_price, jjTools.getParameter(request, _price));
            System.out.println("vbnm,./" + map);
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }

            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            return Js.jjComment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String LikeJournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
          
            String id = jjTools.getParameter(request, _id);
            System.out.println(",,,,"+id);
//            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map2 = new HashMap<String, Object>();
//            map.put(Product._date, new jjCalendar_IR().getDBFormat_8length());
//            map.put(Product._name, jjTools.getParameter(request, _title));
//            map.put(Product._pic5, jjTools.getParameter(request, _pic));
//            map.put(Product._category_id, jjTools.getParameter(request, _parent));
//            map.put(Product._pic6, jjTools.getParameter(request, _file));
//            map.put(Product._prop6, jjTools.getParameter(request, _chiefEditor));
//            map.put(Product._prop5, jjTools.getParameter(request, _Responsible));
//            map.put(Product._prop2, jjTools.getParameter(request, _ReleaseNumber));
//            map.put(Product._prop1, jjTools.getParameter(request, _Issue));
//            map.put(Product._prop3, jjTools.getParameter(request, _DateRelease));
//            map.put(Product._prop4, jjTools.getParameter(request, _Concessionaire));
//            map.put(Product._prop7, jjTools.getParameter(request, _writer1));
//            map.put(Product._prop8, jjTools.getParameter(request, _writer2));
//            map.put(Product._prop9, jjTools.getParameter(request, _writer3));
            map2.put(_status, status_ok);
            if (!db.update(tableName, map2, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjComment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String disLikeJournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String id = jjTools.getParameter(request, _id);
            Map<String, Object> map = new HashMap<String, Object>();
   
            map.put(_status, status_nok);
            map.put(_discription, _discription);
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
     
            return Js.jjComment.refresh();
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
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            StringBuffer html2 = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _price, row.get(0).get(_price)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _pic, row.get(0).get(_pic)));
            html.append(Js.setVal("#" + _Concessionaire, row.get(0).get(_Concessionaire)));
            html.append(Js.setVal("#" + _DateRelease, row.get(0).get(_DateRelease)));
            html.append(Js.setVal("#" + _Issue, row.get(0).get(_Issue)));
            html.append(Js.setVal("#" + _ReleaseNumber, row.get(0).get(_ReleaseNumber)));
            html.append(Js.setVal("#" + _Responsible, row.get(0).get(_Responsible)));
            html.append(Js.setVal("#" + _chiefEditor, row.get(0).get(_chiefEditor)));
            html.append(Js.setVal("#" + _writer1, row.get(0).get(_writer1)));
            html.append(Js.setVal("#" + _writer2, row.get(0).get(_writer2)));
            html.append(Js.setVal("#" + _writer3, row.get(0).get(_writer3)));

            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
            System.out.println("..."+rul_ok);
            boolean accOk = Access_User.hasAccess(request, db, rul_ok);
            System.out.println("..."+accOk);
            boolean accNok = Access_User.hasAccess(request, db, rul_nok);

            if (accEdt) {
                html2.append("<input class=\"form-control btn btn-outline-primary mg-b-10 btn-blue\" type=\"button\" id=\"edit_Comment\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_Comment", Js.jjJournal.edit()));
            }
            if (accDel) {
                html2.append("<input class=\"form-control btn btn-outline-danger  mg-b-10 btn--green\" type=\"button\" id=\"delete_Comment\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Comment", Js.jjJournal.delete(id)));
            }
            if (accOk) {
                html2.append("<input class=\"form-control btn btn-outline-success mg-b-10 btn-primary\" type=\"button\" id=\"ok_Comment\" value=\"" + lbl_ok + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#ok_Comment", Js.jjJournal.ok(id)));
            }
            if (accNok) {
                html2.append("<input class=\"form-control btn  btn-outline-warning mg-b-10 btn-corner\" type=\"button\" id=\"nok_Comment\" value=\"" + lbl_nok + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#nok_Comment", Js.jjJournal.nok(id)));
            }
            Server.outPrinter(request, response, Js.setHtml("#account_product_button", html2.toString()) + html.toString());
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
