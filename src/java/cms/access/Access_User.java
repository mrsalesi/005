package cms.access;

import HMIS.*;
import cms.cms.*;
import cms.cms.Product;
import cms.cms.Factor;
import cms.cms.Tags;
import cms.tools.Payment;
import cms.tools.*;
import static cms.tools.UploadServlet._logStatus;
import static cms.tools.UploadServlet._status;
import static cms.tools.UploadServlet.status_deleted;
import java.io.IOException;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;

public class Access_User {

    public static String tableName = "access_user";
    public static String _id = "id";
    public static String _pass = "user_pass";
    public static String _name = "user_name";
    public static String _family = "user_family";
    public static String _adminDescription = "user_adminDescription";
    public static String _email = "user_email";
    public static String _token = "user_token";
    public static String _passHint = "user_password_hint";//====== BY RASHIDI ======
    public static String _mobile = "user_mobile";
    public static String _phone = "user_phone";
    public static String _studentNumber = "user_studentNumber";
    public static String _AccountInformation = "user_AccountInformation";
    public static String _jensiat = "user_jensiat";
    public static String _codeMeli = "user_codeMeli";
    public static String _shomareShenasname = "user_shomareShenasname";
    public static String _passwordReminder = "user_passwordReminder";
    public static String _grade = "user_grade";
    public static String _DiscountCodes = "user_DiscountCodes";
    ////برای عکس پرسنلی
    ///توسط شیران1
    public static String _attachPicPersonal = "user_attachPicPersonal";
    ////برای عکس کارت پرسنلی
    ///توسط شیران1
    public static String _attachPicPersonnelCard = "user_attachPicPersonnelCard";
    ////برای عکس امضا
    ///توسط شیران1
    public static String _attachPicSignature = "user_attachPicSignature";

    public static String _address = "user_address";
    public static String _isActive = "user_is_active";
    public static String _registDate = "user_createDate";
    public static String _password_hint = "user_password_hint";
    public static String _question = "user_question";
    public static String _answer = "user_answer";
    public static String _birthdate = "user_birthdate";
    public static String _no1 = "user_no1";
    public static String _no2 = "user_no2";
    public static String _parent = "user_parent";
    public static String _weblog = "user_weblog";
    public static String _address2 = "user_address2";
    public static String _postalCode = "user_postalCode";
    public static String _file_personal = "user_file_personal";
    public static String _file_Signature = "user_file_Signature";
    public static String _upload_file = "user_upload_file";
    public static String _attachFile = "user_attachFile";
    public static String _attachFileUser = "user_attachFileUser";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String noAccessFa = "شما اجازه دسترسی ندارید.";
    public static String noAccessEn = "You don't have access.";
    public static String _char1 = "user_char1";//ذخیره سازی آدرس
    public static String _char2 = "user_char2";
    public static String _char3 = "user_char3";
    public static String _int1 = "user_int1";
    public static String _int2 = "user_int2";
    public static String _int3 = "user_int3";
    public static int rul_accessAll = 130;
    public static int rul_rfsAll = 150;
    public static int rul_rfs = 151;
    public static int rul_ins = 152;
    public static int rul_edt = 153;
    public static int rul_dlt = 154;
    public static String wikiLinkColor = "blue";

    /**
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                Server.outPrinter(request, response, "alert('دسترسی شما به اتمام رسیده لطفا دوباره وارد شوید');new jj(\"\").jjGo();");
                return "";
            }
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما دسترسی به این قسمت را ندارین");
                return "";
            }
            StringBuilder html = new StringBuilder();
            String where = "";
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                where = _parent + "=" + jjTools.getSeassionUserId(request);
            } else {
                where = _id + ">4";
            }
            DefaultTableModel dtm = db.Select(tableName, where);// تا شماره  پنج کاربر های رزرو هستند
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>کاربران</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>کاربران</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsUser.m_add_new();' > کاربر جدید</a>"
                        + "        </p>");
            }
            html.append("<table class='table display responsive nowrap' id='refreshAccessUser' dir='rtl'><thead>");
            html.append("<th style='text-align: center;' width='5%'>کد</th>");
            html.append("<th style='text-align: center;' width='30%'>ایمیل</th>");
            html.append("<th style='text-align: center;' width='20%'>تاریخ تولد</th>");
            html.append("<th style='text-align: center;' width='10%'>نام کاربری</th>");
            html.append("<th style='text-align: center;' width='30%'>نام و نام خانوادگی</th>");
            html.append("<th style='text-align: center;' width='20%'>ارسال رمز</th>");
            html.append("<th style='text-align: center;' width='10%'>وضعیت</th>");
            html.append("<th style='text-align: center;' width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr >");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_id)) + "</td>");
                html.append("<td class='tahoma10' style='text-align: left;'>" + (row.get(i).get(_email).toString()) + "</td>");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (jjCalendar_IR.getViewFormat(row.get(i).get(_birthdate))) + "</td>");
                html.append("<td class='tahoma10' style='text-align: right;'>" + (row.get(i).get(_name).toString()) + "</td>");
                html.append("<td class='tahoma10' style='text-align: right;'>" + (row.get(i).get(_family).toString()) + "</td>");
                html.append("<td class='tahoma10' style='text-align: center;'><button class='btn btn-outline-purple mg-b-10 btn-block' onclick='cmsUser.m_send(" + row.get(i).get(_id) + ")'>ارسال پیامک رمز </button></td>");
                String statusUser = row.get(i).get(_isActive).equals("1") ? "فعال" : "غیر فعال";
                html.append("<td class='tahoma10' style='text-align: center;' >" + statusUser + "</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;cursor: pointer;' class='icon ion-ios-gear-outline' onclick='cmsUser.m_select(" + row.get(i).get(_id) + ");'><a src='img/l.png' style='height:30px' ></a></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("</div></div>");

            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swUserTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshAccessUser", height + "px", 0, "", "لیست کاربران");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String changeStatus(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String id, String newSatus) throws Exception {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                return Js.dialog(errorMessageId);
            }
            db.otherStatement("UPDATE " + UploadServlet.tableName + " SET " + _logStatus
                    + "=concat(ifnull(" + _logStatus + ",''),'"
                    + newSatus
                    + "-"
                    + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "%23A%23"
                    + "') ,"
                    + _status + "='" + newSatus + "'  WHERE id=" + id + ";");

            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            String errorMessage = "عملیات تغییر وضعیت به درستی صورت نگرفت.Err114";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        }
    }

    public static String setAttach(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String token = jjTools.getParameter(request, _token);
            String fileName = jjTools.getParameter(request, _attachFile).toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_attachFileUser, fileName);
            db.update(tableName, map, _token + "=" + token);
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            String errorMessage = "عملیات تغییر وضعیت به درستی صورت نگرفت.Err114";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        }
        return null;
    }

    public static String setAttachPic(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String token = jjTools.getParameter(request, _token);
            String fileName = jjTools.getParameter(request, _attachPicPersonal).toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_attachPicPersonal, fileName);
            db.update(tableName, map, _token + "=" + token);
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            String errorMessage = "عملیات تغییر وضعیت به درستی صورت نگرفت.Err114";
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";

        }
        return null;
    }

    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder html = new StringBuilder();
        try {
//            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
//            if (accIns) {
//                html.append(Js.setHtml("#User_button", "<div class='row'><div class='col-lg-6'><input type=\"button\" id=\"insert_User_new\" value=\"" + lbl_insert + "\" class=\"tahoma10 btn btn-success btn-block mg-b-10 ui-button ui-corner-all ui-widget\"></div></div>"));
//                html.append(Js.buttonMouseClick("#insert_User_new", Js.jjUser.insert()));
//            }
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#User_button", "<div class='col-lg-6'><input type='button' id='insert_User_new'  value=\"" + lbl_insert + "\" class='btn btn-outline-success active btn-block mg-b-10'></div>"));
                html.append(Js.click("#insert_User_new", Js.jjUser.insert()));
            } else {
                html.append(Js.setHtml("#User_button", ""));
            }

            //برای نشان دادن لوگوی اختصاصی بیمارستان در قسمت اضافه کردن کاربان
            StringBuilder script2 = new StringBuilder();
            script2.append(Js.setAttr("#PicPreviewPersonal", "src", "img/preview.jpg"));
            script2.append(Js.setAttr("#PicPreview", "src", "img/preview.jpg"));
            script2.append(Js.setAttr("#PicPreviewSignature", "src", "img/preview.jpg"));
            script2.append("regularExpression = /^(?=.*[a-zA-Z\\\\u0621-\\\\u064A])(?=.*[0-9\\\\u0660-\\\\u0669])[a-zA-Za-z\\\\u0621-\\\\u064A0-9\\\\u0660-\\\\u0669]{6,}$/;");
            Server.outPrinter(request, response, html.toString() + script2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                Server.outPrinter(request, response, "شما دسنرسی به این قسمت را ندارین");
                return "";
            }
            String email = jjTools.getParameter(request, _email);
//            String message = isValidMessageForRegist(request, response, db, needString);
//            if (!message.equals("")) {
//                Server.outPrinter(request, response, message);
//                return "";
//            }
//            int size = jjDatabase.separateRow(db.Select(tableName, _email + "='" + jjTools.getParameter(request, _email).toLowerCase() + "'")).size();
//            if (size > 0) {
//                String errorMessage = "کاربری با این ایمیل در دیتابیس وجود دارد.";
//                if (jjTools.getParameter(request, "myLang").equals("2")) {
//                    errorMessage = "This email is being in database.";
//                }
//                Server.outPrinter(request, response, errorMessage);
//                return "";
//            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_attachPicPersonal, jjTools.getParameter(request, _attachPicPersonal));
            map.put(_parent, jjTools.getSeassionUserId(request));
            map.put(_attachPicPersonnelCard, jjTools.getParameter(request, _attachPicPersonnelCard));
            map.put(_attachPicSignature, jjTools.getParameter(request, _attachPicSignature));
            map.put(_email, email.toLowerCase());
            map.put(_family, jjTools.getParameter(request, _family));
            map.put(_AccountInformation, jjTools.getParameter(request, _AccountInformation));
            map.put(_passwordReminder, jjTools.getParameter(request, _passwordReminder));
            map.put(_grade, jjTools.getParameter(request, _grade));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_jensiat, jjTools.getParameter(request, _jensiat));
            map.put(_mobile, jjTools.getParameter(request, _mobile));
            map.put(_codeMeli, jjTools.getParameter(request, _codeMeli));
            map.put(_shomareShenasname, jjTools.getParameter(request, _shomareShenasname));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_char1, jjTools.getParameter(request, _char1));
            String char2 = jjTools.getParameter(request, _char2);
            map.put(_char2, jjNumber.isDigit(char2) ? Integer.parseInt(char2) : 0);
            String char3 = jjTools.getParameter(request, _char3);
            map.put(_char3, jjNumber.isDigit(char3) ? Integer.parseInt(char3) : 0);
            String int1 = jjTools.getParameter(request, _int1);
            map.put(_int1, jjNumber.isDigit(int1) ? Integer.parseInt(int1) : 0);
            map.put(_int2, jjTools.getParameter(request, _int2));
            map.put(_int3, jjTools.getParameter(request, _int3));
            String passHint = jjTools.getParameter(request, _passHint);
            map.put(_passHint, jjNumber.isDigit(passHint) ? Integer.parseInt(passHint) : 0);
            map.put(_no1, jjTools.getParameter(request, _no1));
            map.put(_no2, jjTools.getParameter(request, _no2));
            map.put(_postalCode, jjTools.getParameter(request, _postalCode));
            map.put(_question, jjTools.getParameter(request, _question));
            map.put(_answer, jjTools.getParameter(request, _answer));
            String studentNumber = jjTools.getParameter(request, _studentNumber);
            map.put(_studentNumber, jjNumber.isDigit(studentNumber) ? Integer.parseInt(studentNumber) : 0);
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_adminDescription, jjTools.getParameter(request, _adminDescription));
            map.put(_attachFileUser, jjTools.getParameter(request, _attachFileUser));
            map.put(_attachFile, jjTools.getParameter(request, _attachFile));

            map.put(_birthdate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birthdate), false));
            map.put(_registDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _registDate), false));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
            if (row.isEmpty()) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("2")) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Map<String, Object> mapGroup = new HashMap<String, Object>();
            String selectOptionGroupUser = jjTools.getParameter(request, "selectOptionGroupUser");
            String[] afterSplit = selectOptionGroupUser.split(",");
            for (int i = 0; i < afterSplit.length; i++) {
                mapGroup.put(Access_Group_User._user_id, row.get(0).get(_id));
                mapGroup.put(Access_Group_User._group_id, afterSplit[i]);
                db.insert(Access_Group_User.tableName, mapGroup);
            }
            Server.outPrinter(request, response, Js.jjUser.refresh());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String isValidMessageForRegist(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        String doAct = jjTools.getParameter(request, "do").toLowerCase();
        try {

            String email = jjTools.getParameter(request, _email).toLowerCase();
            String pass = jjTools.getParameter(request, _pass).toLowerCase();
            String mobile = jjTools.getParameter(request, _mobile).toLowerCase();

            //============ BY RASHIDI ========>
            // ------------- check email or mobile are not empty -------------------
            if (email.equals("") && mobile.equals("")) {
                String errorMsg = "وارد کردن ایمیل و یا شماره موبایل الزامیست";
                if (jjTools.isLangEn(request)) {
                    errorMsg = "Enter phone number or email address";
                }
                Server.outPrinter(request, response, errorMsg);
                return "";
            }
            //<============ BY RASHIDI ========
            // ------------- check valid email -------------------
            if ((!email.equals("") && mobile.equals("")) || (!email.equals("") && !mobile.equals(""))) {//====== BY RASHIDI ======
                String lengthMinMessageEmail = jjValidation.isEmailMessageFa(email, "ایمیل");
                if (!lengthMinMessageEmail.equals("")) {
                    if (jjTools.isLangEn(request)) {
                        lengthMinMessageEmail = jjValidation.isEmailMessageEn(email, "Email");
                    }
                    Server.outPrinter(request, response, (lengthMinMessageEmail));
                    return "";
                }
            }
            // ------------- check name is not empty -------------------
            String reqName = jjValidation.isFillMessageFa(jjTools.getParameter(request, _name), "نام");
            if (!reqName.equals("")) {
                if (jjTools.isLangEn(request)) {
                    reqName = jjValidation.isFillMessageEn(jjTools.getParameter(request, _name), "name");
                }
                Server.outPrinter(request, response, reqName);
                return "";
            }

            // ------------- check family is not empty -------------------
            String reqfamily = jjValidation.isFillMessageFa(jjTools.getParameter(request, _family), "نام خانوادگی");
            if (!reqfamily.equals("")) {
                if (jjTools.isLangEn(request)) {
                    reqfamily = jjValidation.isFillMessageEn(jjTools.getParameter(request, _name), "family");
                }
                Server.outPrinter(request, response, reqfamily);
                return "";
            }
            // ------------- check password is not empty -------------------

            String lengthMinMessagePassword = jjValidation.isLengthMinMessageFa(pass, 1, "رمز عبور");
            if (!lengthMinMessagePassword.equals("")) {
                if (jjTools.isLangEn(request)) {
                    lengthMinMessagePassword = jjValidation.isLengthMinMessageEn(pass, 1, "Password");
                }
                Server.outPrinter(request, response, (lengthMinMessagePassword));
                return "";
            }

            // ------------- check passwordHint is not empty -------------------
            String reqAnswer = jjValidation.isFillMessageFa(jjTools.getParameter(request, _passHint), "یادآور گذرواژه");
            if (!reqAnswer.equals("")) {
                if (jjTools.isLangEn(request)) {
                    reqAnswer = jjValidation.isFillMessageEn(jjTools.getParameter(request, _passHint), "password hint");
                }
                Server.outPrinter(request, response, reqAnswer);
                return "";
            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
        Server.outPrinter(request, response, "");
        return "";
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما دسنرسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String email = jjTools.getParameter(request, _email);

            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_email, email.toLowerCase());
            map.put(_family, jjTools.getParameter(request, _family));
            map.put(_isActive, jjTools.getParameter(request, _isActive).equals("1"));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_mobile, jjTools.getParameter(request, _mobile));
            map.put(_attachFile, jjTools.getParameter(request, _attachFile));
            map.put(_attachPicPersonal, jjTools.getParameter(request, _attachPicPersonal));
            map.put(_attachPicPersonnelCard, jjTools.getParameter(request, _attachPicPersonnelCard));
            map.put(_attachPicSignature, jjTools.getParameter(request, _attachPicSignature));
            map.put(_attachFileUser, jjTools.getParameter(request, _attachFileUser));
            map.put(_AccountInformation, jjTools.getParameter(request, _AccountInformation));
            map.put(_grade, jjTools.getParameter(request, _grade));
            map.put(_passwordReminder, jjTools.getParameter(request, _passwordReminder));
            map.put(_jensiat, jjTools.getParameter(request, _jensiat));
            map.put(_codeMeli, jjTools.getParameter(request, _codeMeli));
            map.put(_shomareShenasname, jjTools.getParameter(request, _shomareShenasname));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_pass, jjTools.getParameter(request, _pass).toLowerCase());
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_char1, jjTools.getParameter(request, _char1));
            String char2 = jjTools.getParameter(request, _char2);
            map.put(_char2, jjNumber.isDigit(char2) ? Integer.parseInt(char2) : 0);
            String char3 = jjTools.getParameter(request, _char3);
            map.put(_char3, jjNumber.isDigit(char3) ? Integer.parseInt(char3) : 0);
            String int1 = jjTools.getParameter(request, _int1);
            map.put(_int1, jjNumber.isDigit(int1) ? Integer.parseInt(int1) : 0);
            map.put(_int2, jjTools.getParameter(request, _int2));
            map.put(_int3, jjTools.getParameter(request, _int3));
            String passHint = jjTools.getParameter(request, _passHint);
            map.put(_passHint, jjNumber.isDigit(passHint) ? Integer.parseInt(passHint) : 0);
            map.put(_no1, jjTools.getParameter(request, _no1));
            map.put(_no2, jjTools.getParameter(request, _no2));
            map.put(_postalCode, jjTools.getParameter(request, _postalCode));
            map.put(_question, jjTools.getParameter(request, _question));
            map.put(_answer, jjTools.getParameter(request, _answer));
            String studentNumber = jjTools.getParameter(request, _studentNumber);
            map.put(_studentNumber, jjNumber.isDigit(studentNumber) ? Integer.parseInt(studentNumber) : 0);
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_adminDescription, jjTools.getParameter(request, _adminDescription));

            map.put(_birthdate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birthdate), false));
            map.put(_registDate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _registDate), false));
            String whrer;
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                whrer = _id + "=" + jjTools.getParameter(request, _id);
            } else {
                whrer = _id + "=" + jjTools.getParameter(request, _id) + " AND " + _parent + "=" + jjTools.getSeassionUserId(request);
            }
            System.out.println("..............................." + map);
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }

            // =========================
            if (!jjTools.getParameter(request, "selectOptionGroupUser").equals("") && !jjTools.getParameter(request, "selectOptionGroupUser").equals("null")) {
                db.delete(Access_Group_User.tableName, Access_Group_User._user_id + "=" + id);
                Map<String, Object> mapGroup = new HashMap<>();
                String selectOptionGroupUser = jjTools.getParameter(request, "selectOptionGroupUser");
                String[] afterSplit = selectOptionGroupUser.split(",");
                for (int i = 0; i < afterSplit.length; i++) {
                    mapGroup.put(Access_Group_User._user_id, id);
                    mapGroup.put(Access_Group_User._group_id, afterSplit[i]);
                    db.insert(Access_Group_User.tableName, mapGroup);
                }
            }
            // =========================
            List<Map<String, Object>> row1 = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            //در موقعی که شماره موبایل تغییر میکند پیامی به شماره قبلی و ایمیلی به شخص مورد نظر ارسال میشود
//            if (!row.get(0).get(_mobile).equals(row1.get(0).get(_mobile))) {
//                String errorMessage = "شماره موبایل شما به" + row1.get(0).get(_mobile).toString() + " تغییر کرد";
//                Messenger.sendMesseage(request, db, row.get(0).get(_id).toString(), "", "email,sms", "", "تغییر در پروفایل", errorMessage, "", "امنیتی", Tice_config.getValue(db, Tice_config._config_activeSmsChangeProfile_name), Tice_config.getValue(db, Tice_config._config_activeEmailChangeProfile_name));
//            }
//            //موقعی که ایمیل تغییر کرد پیامی به ایمیل قبلی و موبایل شخص مورد نظر ارسال میشود
//            if (!row.get(0).get(_email).equals(row1.get(0).get(_email))) {
//                String errorMessage = "ایمیل شما به" + row1.get(0).get(_email).toString() + " تغییر کرد";
//                Messenger.sendMesseage(request, db, id, "", "email,sms", "", "تغییر در پروفایل", errorMessage, "", "امنیتی", Tice_config.getValue(db, Tice_config._config_activeSmsChangeProfile_name), Tice_config.getValue(db, Tice_config._config_activeEmailChangeProfile_name));
//            }
//            //موقعی که پسورد عوض شد ایمیل و پیامی به شخص مورد نظر فرستاده میشود تا از اویرایش ناشی از هکر مطلع شود
//            if (!row.get(0).get(_pass).equals(row1.get(0).get(_pass))) {
//                String errorMessage = "پسورد شما به" + row1.get(0).get(_pass).toString() + " تغییر کرد";
//                Messenger.sendMesseage(request, db, id, "", "email,sms", "", "تغییر در پروفایل", errorMessage, "", "امنیتی", Tice_config.getValue(db, Tice_config._config_activeSmsChangeProfile_name), Tice_config.getValue(db, Tice_config._config_activeEmailChangeProfile_name));
//            }
            Server.outPrinter(request, response, Js.jjUser.refresh());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String editUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            String id = jjTools.getParameter(request, _id);
            System.out.println(jjTools.getSeassionUserId(request));
            if (!id.equals(jjTools.getSeassionUserId(request) + "")) {// برای اینکه کابر فقط بتواند پروفایل خودش را ویرایش کند
                Server.outPrinter(request, response, "alert('دوباره وارد شوید'); location.reload();");
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            String email = jjTools.getParameter(request, _email);

            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_email, email.toLowerCase());
            map.put(_family, jjTools.getParameter(request, _family));
            map.put(_isActive, jjTools.getParameter(request, _isActive).equals("1"));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_mobile, jjTools.getParameter(request, _mobile));
            map.put(_attachFileUser, jjTools.getParameter(request, _attachFileUser));
            map.put(_attachPicPersonal, jjTools.getParameter(request, _attachPicPersonal));
            map.put(_codeMeli, jjTools.getParameter(request, _codeMeli));
            map.put(_pass, jjTools.getParameter(request, _pass).toLowerCase());
            map.put(_address, jjTools.getParameter(request, _address));
            String int1 = jjTools.getParameter(request, _int1);
            map.put(_postalCode, jjTools.getParameter(request, _postalCode));
            String whrer;
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                whrer = _id + "=" + jjTools.getParameter(request, _id);
            } else {
                whrer = _id + "=" + jjTools.getParameter(request, _id) + " AND " + _parent + "=" + jjTools.getSeassionUserId(request);
            }
            System.out.println("..............................." + map);
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            // =========================                        
            Server.outPrinter(request, response, "alert('عملیات ویرایش به درستی انجام شد'); location.reload();");
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String editUserProfile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getSessionAttribute(request, "#ID");
            String email = jjTools.getParameter(request, _email);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_email, email.toLowerCase());
            map.put(_family, jjTools.getParameter(request, _family));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_attachFile, jjTools.getParameter(request, _attachFile));
            map.put(_attachPicPersonal, jjTools.getParameter(request, _attachPicPersonal));
            map.put(_attachPicPersonnelCard, jjTools.getParameter(request, _attachPicPersonnelCard));
            map.put(_attachPicSignature, jjTools.getParameter(request, _attachPicSignature));

            map.put(_AccountInformation, jjTools.getParameter(request, _AccountInformation));
            map.put(_grade, jjTools.getParameter(request, _grade));
            map.put(_passwordReminder, jjTools.getParameter(request, _passwordReminder));
            map.put(_jensiat, jjTools.getParameter(request, _jensiat));
            map.put(_codeMeli, jjTools.getParameter(request, _codeMeli));
            map.put(_shomareShenasname, jjTools.getParameter(request, _shomareShenasname));
            map.put(_address, jjTools.getParameter(request, _address));
//             map.put(_file_personal, jjTools.getParameter(request, _file_personal));
//            map.put(_file_Signature, jjTools.getParameter(request, _file_Signature));
//            map.put(_upload_file, jjTools.getParameter(request, _upload_file));

//            map.put(_file_personal, jjTools.getParameter(request, _));
//            String parent = jjTools.getParameter(request, _parent);
//            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_pass, jjTools.getParameter(request, _pass).toLowerCase());
//            map.put(_question, jjTools.getParameter(request, _question));
            map.put(_birthdate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birthdate), false));
            System.out.println("----------------------id--------------");
            System.out.println(id);
            System.out.println("--------------------------id------------");
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder script = new StringBuilder();
            System.out.println("------------------------------------");
            System.out.println(user.get(0).get(_attachPicPersonal).toString());
            System.out.println("--------------------------------------");
            script.append("$('#picUserProfile').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
            script.append("$('#picUserProfile1').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
            script.append("$('#emailUserProfile').html('" + user.get(0).get(_email).toString() + "');");
            script.append("$('#nameUserProfile').html('" + user.get(0).get(_name).toString() + user.get(0).get(_family).toString() + "');");
            script.append("alert('عملیات ویرایش به درستی انجام شد');");
//            script.append("loadFormsHmis();");
            Server.outPrinter(request, response, script.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     *
     * @param id
     */
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_dlt)) {
                Server.outPrinter(request, response, "شما دسنرسی به این قسمت را ندارین");
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
            if (id.equals("0") || id.equals("1") || id.equals("2") || id.equals("3") || id.equals("4") || id.equals("1")) {
                String errorMessage = "شما اجازه مشاهده اطلاعات این شخص را ندارید";
                Server.outPrinter(request, response, Js.dialog(errorMessage) + Js.jjUser.showTbl());
                return "";
            }
            //@ToDo کاربرانی که در قسمت های مختلف سیستم تراکنش داشته اند را نابید بتوانیم خذف کنیم
            String whrer = "";
            if (!Access_User.hasAccess(request, db, rul_rfsAll)) {
                whrer = _id + "=" + jjTools.getParameter(request, _id);
            } else {
                whrer = _id + "=" + jjTools.getParameter(request, _id) + " AND " + _parent + "=" + jjTools.getSeassionUserId(request);
            }
            if (!db.delete(tableName, whrer)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            if (id.equals("1")) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjUser.refresh());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        if (!Access_User.hasAccess(request, db, rul_rfs) && !Access_User.hasAccess(request, db, rul_rfs)) {
            Server.outPrinter(request, response, "شما دسنرسی به این قسمت را ندارین");
            return "";
        }
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

            if (id.equals("0") || id.equals("1") || id.equals("2") || id.equals("3") || id.equals("4")) {
                String errorMessage = "شما اجازه مشاهده اطلاعات این شخص را ندارید";
                Server.outPrinter(request, response, Js.dialog(errorMessage) + Js.jjUser.showTbl());
                return "";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            List<Map<String, Object>> rowGroup = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            String groupName = "";
            if (rowGroup.size() == 1) {
                groupName = rowGroup.get(0).get(Access_Group_User._group_id).toString();
                html.append(Js.setValSelectOption("#selectOptionGroupUser", groupName));
                html.append(Js.select2("#selectOptionGroupUser", ""));
            } else if (rowGroup.size() > 1) {
                for (int i = 0; i < rowGroup.size() - 1; i++) {
                    groupName += rowGroup.get(i).get(Access_Group_User._group_id).toString();
                    groupName += ",";
                }
                groupName += rowGroup.get(rowGroup.size() - 1).get(Access_Group_User._group_id).toString();
                html.append(Js.setValSelectOption("#selectOptionGroupUser", groupName));
                html.append(Js.select2("#selectOptionGroupUser", ""));
            }
            html.append(Js.setVal("#access_user_id", row.get(0).get(_id)));
            html.append(Js.setVal("#user_mobileUser", row.get(0).get(_mobile)));
            html.append(Js.setVal("#" + _int1, row.get(0).get(_int1)));
            html.append(Js.setVal("#" + _int2, row.get(0).get(_int2)));
            html.append(Js.setVal("#" + _int3, row.get(0).get(_int3)));
            html.append(Js.setVal("#" + _char1, row.get(0).get(_char1)));
            html.append(Js.setVal("#" + _char2, row.get(0).get(_char2)));
            html.append(Js.setVal("#" + _char3, row.get(0).get(_char3)));
            html.append(Js.setVal("#" + _no1, row.get(0).get(_no1)));
            html.append(Js.setVal("#" + _no2, row.get(0).get(_no2)));
            html.append(Js.setVal("#" + _question, row.get(0).get(_question)));
            html.append(Js.setVal("#" + _answer, row.get(0).get(_answer)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _postalCode, row.get(0).get(_postalCode)));
            html.append(Js.setVal("#" + _password_hint, row.get(0).get(_password_hint)));
            html.append(Js.setVal("#" + _adminDescription, row.get(0).get(_adminDescription)));
            html.append(Js.setVal("#user_emailUser", row.get(0).get(_email)));
            html.append(Js.setVal("#user_familyUser", row.get(0).get(_family)));
            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            html.append(Js.setVal("#user_nameUser", row.get(0).get(_name)));
            html.append(Js.setVal("#user_AccountInformationUser", row.get(0).get(_AccountInformation)));
            html.append(Js.setVal("#user_birthdateUser", jjCalendar_IR.getViewFormat(row.get(0).get(_birthdate))));
            html.append(Js.setVal("#user_registDate", jjCalendar_IR.getViewFormat(row.get(0).get(_registDate))));
            html.append(Js.setVal("#user_gradeUser", row.get(0).get(_grade)));
            html.append(Js.setVal("#user_jensiatUser", row.get(0).get(_jensiat)));
            html.append(Js.setVal("#user_codeMeliUser", row.get(0).get(_codeMeli)));
            html.append(Js.setVal("#user_shomareShenasnameUser", row.get(0).get(_shomareShenasname)));
            html.append(Js.setVal("#user_passUser", row.get(0).get(_pass)));
            html.append(Js.setVal("#user_passwordReminderUser", row.get(0).get(_passwordReminder)));
            html.append(Js.setVal("#user_attachPicPersonnelCard", row.get(0).get(_attachPicPersonnelCard)));
            html.append(Js.setVal("#user_attachPicPersonal", row.get(0).get(_attachPicPersonal)));
            html.append(Js.setVal("#user_attachPicSignature", row.get(0).get(_attachPicSignature)));
            html.append(Js.setVal("#user_is_active", row.get(0).get(_isActive)));
            html.append(Js.setAttr("#DownloadPicPersonal", "href", "upload/" + row.get(0).get(_attachPicPersonal)));
            html.append(Js.setAttr("#DownloadPicPersonnelCard", "href", "upload/" + row.get(0).get(_attachPicPersonnelCard)));
            html.append(Js.setAttr("#DownloadattachFileUser", "href", "upload/" + row.get(0).get(_attachFileUser)));
            if (!row.get(0).get(_attachFile).toString().isEmpty()) {//فایل های پیوست شده ی کاربر را  اگر چیزی داشته باشد پشت سر هم نشان میدهد
                String userFiles[] = row.get(0).get(_attachFile).toString().split(",");
                for (int k = 0; k < userFiles.length; k++) {
                    html.append(Js.append("#user_attachFileDiv", "<div class='col-lg-12 mg-l-15'><img class='wd-40 rounded-circle mg-r-20' src='upload/" + userFiles[k] + "'><a target='_blank' href='upload/" + userFiles[k] + "'>دانلود</a> <input class='" + _attachFile + "' type='hidden' value='" + userFiles[k] + "'><div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i></div></div>"));
                }
            }
            if (!row.get(0).get(_attachFileUser).toString().isEmpty()) {//فایل های پیوست شده ی کاربر را  اگر چیزی داشته باشد پشت سر هم نشان میدهد
                String userFilesUser[] = row.get(0).get(_attachFileUser).toString().split(",");
                for (int k = 0; k < userFilesUser.length; k++) {
                    html.append(Js.append("#user_attachFileUserDiv", "<div class='col-lg-12 mg-l-15'><img class='wd-40 rounded-circle mg-r-20' src='upload/" + userFilesUser[k] + "'><a target='_blank' href='upload/" + userFilesUser[k] + "'>دانلود</a> <input class='" + _attachFileUser + "' type='hidden' value='" + userFilesUser[k] + "'><div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i></div></div>"));
                }
            }

            /////برای دانلود عکس ها نوشته شده
            html.append(Js.setAttr("#DownloadPicSignature", "href", "upload/" + row.get(0).get(_attachPicSignature)));

            StringBuilder script = new StringBuilder();
            StringBuilder script2 = new StringBuilder();

            if (row.get(0).get(Access_User._attachPicPersonal).equals("")) {
                script.append(Js.setAttr("#PicPreviewPersonal", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreviewPersonal", "src", "upload/" + row.get(0).get(Access_User._attachPicPersonal).toString() + ""));
                script.append(Js.show("#DownloadPicPersonal"));

            }
            if (row.get(0).get(Access_User._attachPicPersonnelCard).equals("")) {
                script.append(Js.setAttr("#PicPreview", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreview", "src", "upload/" + row.get(0).get(Access_User._attachPicPersonnelCard).toString() + ""));
                script.append(Js.show("#DownloadPicPersonnelCard"));
            }
            if (row.get(0).get(Access_User._attachPicSignature).equals("")) {
                script.append(Js.setAttr("#PicPreviewSignature", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreviewSignature", "src", "upload/" + row.get(0).get(Access_User._attachPicSignature).toString() + ""));
                script.append(Js.show("#DownloadPicSignature"));
            }

            String attachFiles = row.get(0).get(_attachFile).toString();
            String[] attachFilesArray = attachFiles.split(",");
            for (int l = 0; l < attachFilesArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html2.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFile + "'  type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html2.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFile + "'   type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            script.append(Js.setHtml("#user_divUpload", html2));

            html.append(Js.setVal("#user_addressUser", row.get(0).get(_address)));
            html.append(Js.setValDate("#user_birthdateUserUser", jjCalendar_IR.getViewFormat(row.get(0).get(_birthdate))));
///////////////////////////
            /////این تابع برای نمایش فایل های اپلود شده توسط فردی که واردشده نوشته شده است
            /////شیران1

//            List<Map<String, Object>> rowUpload = jjDatabase.separateRow(db.Select(UploadServlet.tableName,UploadServlet. _loader_id + "=" + id));
//            html.append(Js.setVal("#uploaded_file", rowUpload.get(0).get(UploadServlet._file_name)));
            String htmlBottons = "";
            boolean accEdit = Access_User.hasAccess(request, db, rul_edt);
            if (accEdit) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjUser.edit() + "' id='edit_User'>" + lbl_edit + "</button></div>";
//
            }
            boolean accDelete = Access_User.hasAccess(request, db, rul_dlt);
            if (accDelete) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjUser.delete(id) + "' id='delete_User'>" + lbl_delete + "</button></div>";
            }
            script2.append(Js.setHtml("#User_button", htmlBottons));
            script2.append("regularExpression = /^(?=.*[a-zA-Z\\\\u0621-\\\\u064A])(?=.*[0-9\\\\u0660-\\\\u0669])[a-zA-Za-z\\\\u0621-\\\\u064A0-9\\\\u0660-\\\\u0669]{6,}$/;");

            Server.outPrinter(request, response, script2 + html.toString() + script.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String removeFile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (hasAccess.equals("0")) {
                Server.outPrinter(request, response, Js.modal("شما دسترسی به این قسمت را ندارین", "پیام سامانه"));
                return "";
            }
            String idUpload = jjTools.getParameter(request, "upload_id");///
            String idUser = jjTools.getParameter(request, "access_user_id");

            List<Map<String, Object>> rowUser = jjDatabase.separateRow(db.Select(tableName, _id + "=" + idUser));///برای در اوردن attachfile
            List<Map<String, Object>> rowupload = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._id + "=" + idUpload));////برای دراوردن اسم فایل
            String filename = rowupload.get(0).get(UploadServlet._file_name).toString() + "#A#";
            String attacheFiles = rowUser.get(0).get(_attachFile).toString();
            System.out.println(filename);
            System.out.println("____________________________________");
            System.out.println(attacheFiles);
            attacheFiles = attacheFiles.replace(filename, "");
            System.out.println(attacheFiles);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_attachFile, attacheFiles);
            System.out.println("____________________________________");

            db.update(tableName, map, _id + "=" + idUser);
            changeStatus(request, response, db, idUpload, status_deleted + " " + jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY"));
//            if (!db.delete(UploadServlet.tableName, UploadServlet._id + "=" + idUpload)) {
//                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Delete Fail;";
//                }
//                Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
//                return "";
//
//            }
//            String error = "فایل مورد نظر حذف شد";
//            Server.outPrinter(request, response, Js.modal(error, "پیام سامانه"));
            return "";
//

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";

        }
    }

    public static String selectProfile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getSessionAttribute(request, "#ID");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            html.append(Js.setVal("#access_UserProfile_id", row.get(0).get(_id)));
            html.append(Js.setVal("#userProfile_email", row.get(0).get(_email)));
            html.append(Js.setVal("#userProfile_family", row.get(0).get(_family)));
            html.append(Js.setVal("#userProfile_name", row.get(0).get(_name)));
            html.append(Js.setVal("#userProfile_AccountInformation", row.get(0).get(_AccountInformation)));
            html.append(Js.setVal("#userProfile_birthdate", jjCalendar_IR.getViewFormat(row.get(0).get(_birthdate))));
            html.append(Js.setVal("#userProfile_grade", row.get(0).get(_grade)));
            html.append(Js.setVal("#userProfile_jensiat", row.get(0).get(_jensiat)));
            html.append(Js.setVal("#userProfile_codeMeli", row.get(0).get(_codeMeli)));
            html.append(Js.setVal("#userProfile_shomareShenasname", row.get(0).get(_shomareShenasname)));
            html.append(Js.setVal("#userProfile_pass", row.get(0).get(_pass)));
            html.append(Js.setVal("#userProfile_passwordReminder", row.get(0).get(_passwordReminder)));
            html.append(Js.setVal("#userProfile_attachPicPersonnelCard", row.get(0).get(_attachPicPersonnelCard)));
            html.append(Js.setVal("#userProfile_attachPicPersonal", row.get(0).get(_attachPicPersonal)));
            html.append(Js.setVal("#userProfile_attachPicSignature", row.get(0).get(_attachPicSignature)));
            html.append(Js.setAttr("#DownloadPicPersonal_UserProfile", "href", "upload/" + row.get(0).get(_attachPicPersonal)));
            html.append(Js.setAttr("#DownloadPicPersonnelCard_UserProfile", "href", "upload/" + row.get(0).get(_attachPicPersonnelCard)));
            /////برای دانلود عکس ها نوشته شده
            html.append(Js.setAttr("#DownloadPicSignature_UserProfile", "href", "upload/" + row.get(0).get(_attachPicSignature)));
            StringBuilder script = new StringBuilder();
            StringBuilder script2 = new StringBuilder();

            if (row.get(0).get(Access_User._attachPicPersonal).equals("")) {
                script.append(Js.setAttr("#PicPreviewPersonal_UserProfile", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreviewPersonal_UserProfile", "src", "upload/" + row.get(0).get(Access_User._attachPicPersonal).toString() + ""));
                script.append(Js.show("#DownloadPicPersonal_UserProfile"));

            }
            if (row.get(0).get(Access_User._attachPicPersonnelCard).equals("")) {
                script.append(Js.setAttr("#PicPreview_UserProfile", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreview_UserProfile", "src", "upload/" + row.get(0).get(Access_User._attachPicPersonnelCard).toString() + ""));
                script.append(Js.show("#DownloadPicPersonnelCard_UserProfile"));
            }
            if (row.get(0).get(Access_User._attachPicSignature).equals("")) {
                script.append(Js.setAttr("#PicPreviewSignature_UserProfile", "src", "img/preview.jpg"));
            } else {
                script.append(Js.setAttr("#PicPreviewSignature_UserProfile", "src", "upload/" + row.get(0).get(Access_User._attachPicSignature).toString() + ""));
                script.append(Js.show("#DownloadPicSignature_UserProfile"));
            }

            String attachFiles = row.get(0).get(_attachFile).toString();
            String[] attachFilesArray = attachFiles.split(",");
            for (int l = 0; l < attachFilesArray.length; l++) {
                List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                if (!fileRow.isEmpty()) {
                    String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                    String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                    String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                    if (extension2.toLowerCase().equals("jpg")
                            || extension2.toLowerCase().equals("png")
                            || extension2.toLowerCase().equals("gif")
                            || extension2.toLowerCase().equals("svg")) {
                        html2.append("<div class='col-lg-12 media-body mg-l-15'>"
                                + "<img class='wd-40  mg-r-20' src='upload/" + attachFilesArray[l] + "'/>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'> " + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFile + "'  type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    } else {
                        html2.append("<div class='col-lg-12  media-body mg-l-15'>"
                                + "<i class='icon ion-ios-paper-outline'></i>"
                                + "<a  href='upload/" + attachFilesArray[l] + "'>" + "<i class='fa fa-download'></i>" + titleUpload + "</a>"
                                + "<input class='" + _attachFile + "'   type='hidden'  value='" + attachFilesArray[l] + "'/>"
                                + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close mg-5'></i>" + "</div>"
                                + "</div>"
                        );
                    }
                } else {
                    //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                }
            }
            script.append(Js.setHtml("#userProfile_divUpload", html2));

            html.append(Js.setVal("#userProfilr_address", row.get(0).get(_address)));
            html.append(Js.setValDate("#userProfile_birthdate", jjCalendar_IR.getViewFormat(row.get(0).get(_birthdate))));
            String htmlBottons = "";
            htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjUserProfile.edit() + "' id='edit_User'>" + lbl_edit + "</button></div>";
            script2.append(Js.setHtml("#UserProfile_button", htmlBottons));
            script2.append("regularExpression = /^(?=.*[a-zA-Z\\\\u0621-\\\\u064A])(?=.*[0-9\\\\u0660-\\\\u0669])[a-zA-Za-z\\\\u0621-\\\\u064A0-9\\\\u0660-\\\\u0669]{6,}$/;");
            Server.outPrinter(request, response, script2 + html.toString() + script.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String selectUserProfile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = String.valueOf(jjTools.getSeassionUserId(request));
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
                return "";
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            html.append(Js.setVal("#user_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _answer, row.get(0).get(_answer)));
            html.append(Js.setVal("#" + _email, row.get(0).get(_email)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _address2, row.get(0).get(_address2)));
            html.append(Js.setVal("#" + _mobile, row.get(0).get(_mobile)));
            html.append(Js.setVal("#" + _phone, row.get(0).get(_phone)));
            html.append(Js.setVal("#" + _postalCode, row.get(0).get(_postalCode)));
            html.append(Js.setVal("#" + _family, row.get(0).get(_family)));
            html.append(Js.setVal("#" + _name, row.get(0).get(_name)));
            html.append(Js.setVal("#" + _no1, row.get(0).get(_no1)));
            html.append(Js.setVal("#" + _no2, row.get(0).get(_no2)));
            html.append(Js.setVal("#" + _question, row.get(0).get(_question)));
            html.append(Js.setValDate("#" + _registDate, row.get(0).get(_registDate)));
            html.append(Js.setValDate("#" + _birthdate, row.get(0).get(_birthdate)));
            StringBuilder script2 = new StringBuilder();
            script2.append("regularExpression = /^(?=.*[a-zA-Z\\\\u0621-\\\\u064A])(?=.*[0-9\\\\u0660-\\\\u0669])[a-zA-Za-z\\\\u0621-\\\\u064A0-9\\\\u0660-\\\\u0669]{6,}$/;");
            Server.outPrinter(request, response, html.toString() + script2);
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

//    public static String getCheckboxList(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
//        StringBuffer html = new StringBuffer();
//        List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
//        String userId = jjTools.getParameter(request, Access_User._id);
//        if (row.size() > 0) {
//            html.append("<div align='center'><table border='1' style='width: 100%;height: 100' cellpadding='1'><tr>");
//            html.append("<td height='100' align='center' width='100%' bgcolor='white' valign='top'>");
//            html.append("<div style='padding: 0px;background-color: white;color: black ;border:0px solid black;width:100%;height:100px;overflow:auto;text-align: right'>");
//            String selected = "";
//            String groups = "";
//            List<Map<String, Object>> gr = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + userId));
//            for (int i = 0; i < gr.size(); i++) {
//                groups += "$" + gr.get(i).get(Access_Group_User._group_id) + "$";
//
//            }
//            if (jjNumber.isDigit(userId)) {
//                for (int i = 0; i < row.size(); i++) {
////                    int rowCount = db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + userId
////                            + " AND " + Access_Group_User._group_id + "=" + row.get(i).get(_id)).getRowCount();
//                    selected = groups.contains("$" + row.get(i).get(_id).toString() + "$") ? "checked" : "";
//                    html.append("<li style='width: 250px;font-size: 10pt;cursor: pointer;text-align: right;padding: 0px;'>");
//                    html.append("<input  type='checkbox' style='width: 30px;' "
//                            + "id='chk" + row.get(i).get(_id) + "' name='chk" + row.get(i).get(_id) + "' " + selected + "/>");
//                    html.append(row.get(i).get(_email));
//                    html.append("</li>");
//                }
//            }
//            html.append("</div></td></table></div>");
//        }
//        Server.outPrinter(request, response, html.toString();
//    }
    public static String login(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {

                String email = jjTools.getParameter(request, _email).toLowerCase().replace(" or ", "").toLowerCase();
                String passRequest = jjTools.getParameter(request, Access_User._pass).toLowerCase().replace(" or ", "").toLowerCase();
                // --------------------------------------------------------------- //
                if (email.equals("") || passRequest.equals("")) {
                    return "";
                }
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, Access_User._email + "='" + email
                        + "' AND " + Access_User._pass + "='" + passRequest + "'"));
                if (user.isEmpty()) {
                    Server.outPrinter(request, response, "alert('کاربری با این مشخصات وجود ندارد');new jj(\"\").jjGo();");
                    return "";
                } else if ("0".equals(user.get(0).get(Access_User._isActive).toString())) {
                    Server.outPrinter(request, response, "alert('شما کاربر غیر فعال میباشید لطفابرای پیگیری با مدیریت یا پشتیبانی تماس بگیرید');new jj(\"\").jjGo();");
                    return "";
                }
                List<Map<String, Object>> groups = jjDatabase.separateRow(
                        db.otherSelect("SELECT id, "
                                + "GROUP_CONCAT(group_id SEPARATOR ' OR id=') as group_id "//گروه هایی که کاربر در آن هست را کانکت میکند و آماده ی سلکت بعدی است
                                + "FROM access_user_group WHERE user_id=" + user.get(0).get(Access_User._id).toString() + " GROUP BY user_id;"));
                StringBuilder validInSeassion = new StringBuilder();
                StringBuilder noValidInSeassion = new StringBuilder();
                jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), user.get(0).get(Access_User._id).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), user.get(0).get(Access_User._name).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), user.get(0).get(Access_User._family).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), user.get(0).get(Access_User._pass).toString());
                List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "=" + user.get(0).get(Access_User._id)));
                if ((user.get(0).get(_no1)).toString().equals(user.get(0).get(_email)) || (user.get(0).get(_no1)).toString().equals(user.get(0).get(_pass))) {
                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("$( '#swUserProfile' ).show();\n");
                    html.append("hmisUserProfile.loadForm();\n"
                            + "                                    hmisUserProfile.m_select();\n"
                            + "                                    hmisUserProfile.mainTabSetSize();");
                } else {
                    if (roleRow.size() > 0) {// نقش هایی که این کاربر دارد
                        String rolesStr = "";
                        for (int i = 0; i < roleRow.size(); i++) {
                            rolesStr += roleRow.get(i).get(Role._id).toString() + ",";
                        }
                        jjTools.setSessionAttribute(request, "#ROLE_ID", rolesStr);// آی دی نقش میرود درسشن
                    }
                    if (user.get(0).get(Access_User._id).toString().equals("1") || user.get(0).get(Access_User._id).toString().equals("2")) {
                        html.append("$('#TrIdInUserForm').show();\n");
                    } else {
                        html.append("$('#TrIdInUserForm').hide();\n");
                    }
                    if (groups.size() > 0) {//اگر کاربر عضو گروهی نبود دسترسی به چیزی ندارد
                        List<Map<String, Object>> group = jjDatabase.separateRow(db.Select(Access_Group.tableName, Access_Group._id + "=" + groups.get(0).get(Access_Group_User._group_id)));
                        for (int j = 1; j < Access_Group.chkNumber; j++) {// به تعداد همه ی دسترسی هایی که در دیتابیس تعریف شده
                            String rulId = "";//defult value,to privent null pointer exeption in group_c
                            for (int i = 0; i < group.size(); i++) {// به تعداد گروه هایی که کاربر در آنها عضو است دسترسی را چک میکنیم
                                try {
                                    String columnName = "group_c" + (j < 10 ? "0" + j : j);// برای ستون های زیر ده یک صفر باید اضافه کنیم چون در دیتابیس از اول اینطوری بوده که بهتر است این صفر را برداریم
                                    rulId += group.get(i).get(columnName).toString().equals("1") ? "1" : "";// اگر دسترسی داشت یک میگذاریم اگر نداشت تهی 
                                } catch (Exception ex) {
                                    ServerLog.Print(
                                            "خطایی در پایگاه داده: ردیف "
                                            + "group_c" + (j < 10 ? "0" + j : j)
                                            + "وجود ندارد، این خطا از طریق استثنا ها مدیریت شد");
                                    rulId = "";
                                }
                            }
                            if (rulId.isEmpty()) {
                                noValidInSeassion.append("$" + j + "$");
                                html.append("$('#C" + (j < 10 ? "0" + j : j) + "').attr('disabled','disabled');\n");
                            } else {
                                if (!validInSeassion.toString().contains("$" + j + "$")) {
                                    validInSeassion.append("$" + j + "$");
                                }
                            }
                        }
                    }
                    jjTools.setSessionAttribute(request, "#ACCESS", validInSeassion.toString());
                    jjTools.setSessionAttribute(request, "#NOACCESS", noValidInSeassion.toString());

                    //////////////////////////////مدیریت اطلاعات عمومی///////////////////////////
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    //////////////////////////////مدیریت پیام ها///////////////////////////
                    if (Access_User.hasAccess(request, db, Messenger.rul_rfs_All)) {
                        html.append("$('#maduleSms').show();\n");
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    //////////////////////////////مدیریت اطلاعات عمومی///////////////////////////
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    /////////////////////////////ماژول فرم ساز///////////////////////////
                    if (Access_User.hasAccess(request, db, Forms.rul_formsModule)) {
                        html.append("$('#formsModule').show();\n");
                        if (Access_User.hasAccess(request, db, Forms.rul_rfs)) {
                            html.append("$( '#managmentForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveForms)) {
                            html.append("$( '#managmentComplitArshiveForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitFormsMe)) {
                            html.append("$( '#managmentFormsMe' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveFormsMe)) {
                            html.append("$( '#managmentComplitArshiveFormsMe' ).show();\n");
                        }
                    }
                    ///////////////////////////ماژول دسترسی///////////////////////
                    if (Access_User.hasAccess(request, db, rul_accessAll)) {
                        html.append("$('#accessli').show();\n");
                        if (Access_User.hasAccess(request, db, Access_User.rul_rfs)) {
                            html.append("$( '#swUserli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Access_Group.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Role.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Department.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    ///////////////////////////پروژه ها///////////////////////
                    if (Access_User.hasAccess(request, db, Product.rul_rfsAll)) {
                        html.append("$('#accountingModule').show();\n");
                        if (Access_User.hasAccess(request, db, Product.rul_rfs)) {
                            html.append("$( '#managmentProduct' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Product.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Tags.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Factor.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Payment.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    ///////////////////////////sitting///////////////////////
                    if (Access_User.hasAccess(request, db, Tice_config.rul_rfs)) {
                        html.append("$('#maduleSitteng').show();\n");
                    }
                    ////////////////////////////////////////////
                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("$( '#headpanel_left' ).show();\n");
                    html.append("$( '#headpanel_right' ).show();\n");
                    System.out.println("------------------------------------");
                    System.out.println(user.get(0).get(_attachPicPersonal).toString());
                    System.out.println("--------------------------------------");
                    if (user.get(0).get(_attachPicPersonal).equals("")) {
                        html.append("$('#picUserProfile').attr('src', 'Manager/img1.jpg');");
                        html.append("$('#picUserProfile1').attr('src', 'Manager/img1.jpg');");
                    } else {
                        html.append("$('#picUserProfile').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                        html.append("$('#picUserProfile1').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                    }
                    html.append("$('#emailUserProfile').html('" + user.get(0).get(_email).toString() + "');");
                    html.append("$('#nameUserProfile').html('" + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + "');");
                }
                //در موقع ورود به سامانه برای کاربری که وارد سامانه شده یک پیامک و ایمیل ارسال میشود
                System.out.println("//////////////////////////////////////");
                String errorMessage = user.get(0).get(_jensiat).toString() + " " + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + " " + " به سامانه وارد شدید";
                Messenger.sendMesseage(request, db, user.get(0).get(_id).toString(), "0", "sms,email", "", "ورود به سامانه", errorMessage, "", "", "امنیتی", Tice_config.getValue(db, Tice_config._config_activeSmsLogin_name), Tice_config.getValue(db, Tice_config._config_activeEmailLogin_name));
                System.out.println("//////////////////////////////////////");
            } else {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, _id + "=" + jjTools.getSessionAttribute(request, "#ID")));
                if ((user.get(0).get(_no1)).toString().equals(user.get(0).get(_email)) || (user.get(0).get(_no1)).toString().equals(user.get(0).get(_pass))) {
                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("$( '#swUserProfile' ).show();\n");
                    html.append("hmisUserProfile.loadForm();\n"
                            + "                                    hmisUserProfile.m_select();\n"
                            + "                                    hmisUserProfile.mainTabSetSize();");
                } else {
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    //////////////////////////////مدیریت پیام ها///////////////////////////
                    if (Access_User.hasAccess(request, db, Messenger.rul_rfs_All)) {
                        html.append("$('#maduleSms').show();\n");
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Messenger.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    //////////////////////////////مدیریت اطلاعات عمومی///////////////////////////
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#managmentContentCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_news' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#mujole_content_GroupingNews' ).show();\n");
                        }
                    }
                    /////////////////////////////ماژول فرم ساز///////////////////////////
                    if (Access_User.hasAccess(request, db, Forms.rul_formsModule)) {
                        html.append("$('#formsModule').show();\n");
                        if (Access_User.hasAccess(request, db, Forms.rul_rfs)) {
                            html.append("$( '#managmentForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveForms)) {
                            html.append("$( '#managmentComplitArshiveForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitFormsMe)) {
                            html.append("$( '#managmentFormsMe' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveFormsMe)) {
                            html.append("$( '#managmentComplitArshiveFormsMe' ).show();\n");
                        }
                    }
                    ///////////////////////////ماژول دسترسی///////////////////////
                    if (Access_User.hasAccess(request, db, rul_accessAll)) {
                        html.append("$('#accessli').show();\n");
                        if (Access_User.hasAccess(request, db, Access_User.rul_rfs)) {
                            html.append("$( '#swUserli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Access_Group.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Role.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Department.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    ///////////////////////////پروژه ها///////////////////////
                    if (Access_User.hasAccess(request, db, Product.rul_rfsAll)) {
                        html.append("$('#accountingModule').show();\n");
                        if (Access_User.hasAccess(request, db, Product.rul_rfs)) {
                            html.append("$( '#managmentProduct' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Product.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Tags.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Factor.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Payment.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    ///////////////////////////sitting///////////////////////
                    if (Access_User.hasAccess(request, db, Tice_config.rul_rfs)) {
                        html.append("$('#maduleSitteng').show();\n");
                    }
                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("$( '#headpanel_left' ).show();\n");
                    html.append("$( '#headpanel_right' ).show();\n");
                    System.out.println("------------------------------------");
                    System.out.println(user.get(0).get(_attachPicPersonal).toString());
                    System.out.println("--------------------------------------");
                    if (user.get(0).get(_attachPicPersonal).equals("")) {
                        html.append("$('#picUserProfile').attr('src', 'Manager/img1.jpg');");
                        html.append("$('#picUserProfile1').attr('src', 'Manager/img1.jpg');");
                    } else {
                        html.append("$('#picUserProfile').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                        html.append("$('#picUserProfile1').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                    }
                    html.append("$('#emailUserProfile').html('" + user.get(0).get(_email).toString() + "');");
                    html.append("$('#nameUserProfile').html('" + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + "');");

                }
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String loginMobile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                String email = jjTools.getParameter(request, _email).toLowerCase().replace(" or ", "").toLowerCase();
                String passRequest = jjTools.getParameter(request, Access_User._pass).toLowerCase().replace(" or ", "").toLowerCase();
                // --------------------------------------------------------------- //
                if (email.equals("") || passRequest.equals("")) {
                    return "";
                }
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, Access_User._email + "='" + email
                        + "' AND " + Access_User._pass + "='" + passRequest + "'"));
                if (user.isEmpty()) {
                    Server.outPrinter(request, response, "alert('کاربری با این مشخصات وجود ندارد');new jj(\"\").jjGo();");
                    return "";
                } else if ("0".equals(user.get(0).get(Access_User._isActive).toString())) {
                    Server.outPrinter(request, response, "alert('شما کاربر غیر فعال میباشید لطفابرای پیگیری با مدیریت یا پشتیبانی تماس بگیرید');new jj(\"\").jjGo();");
                    return "";
                }
                //در موقع ورود به سامانه برای کاربری که وارد سامانه شده یک پیامک و ایمیل ارسال میشود
                System.out.println("//////////////////////////////////////");
                String errorMessage = user.get(0).get(_jensiat).toString() + " " + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + " " + " به سامانه وارد شدین";
                Messenger.sendMesseage(request, db, user.get(0).get(_id).toString(), "0", "sms,email", "", "ورود به سامانه", errorMessage, "", "", "امنیتی", Tice_config.getValue(db, Tice_config._config_activeSmsLogin_name), Tice_config.getValue(db, Tice_config._config_activeEmailLogin_name));
                System.out.println("//////////////////////////////////////");
                List<Map<String, Object>> groups = jjDatabase.separateRow(
                        db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + user.get(0).get(Access_User._id)));
                StringBuilder validInSeassion = new StringBuilder();
                StringBuilder noValidInSeassion = new StringBuilder();
                jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), user.get(0).get(Access_User._id).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), user.get(0).get(Access_User._name).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), user.get(0).get(Access_User._family).toString());
                jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), user.get(0).get(Access_User._pass).toString());
                List<Map<String, Object>> roleRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "=" + user.get(0).get(Access_User._id)));
                if ((user.get(0).get(_no1)).toString().equals(user.get(0).get(_email)) || (user.get(0).get(_no1)).toString().equals(user.get(0).get(_pass))) {
                    html.append("$('#swLoging').hide();\n");
                    html.append("$('#allOfIndex').show();\n");
                    html.append("hmisUserProfile.loadForm();\n"
                            + "                                    hmisUserProfile.m_select();\n"
                            + "                                    hmisUserProfile.mainTabSetSize();");
                } else {
                    if (roleRow.size() > 0) {// نقش هایی که این کاربر دارد
                        String rolesStr = "";
                        for (int i = 0; i < roleRow.size(); i++) {
                            rolesStr += roleRow.get(i).get(Role._id).toString() + ",";
                        }
                        jjTools.setSessionAttribute(request, "#ROLE_ID", rolesStr);// آی دی نقش میرود درسشن
                    }
                    if (user.get(0).get(Access_User._id).toString().equals("1") || user.get(0).get(Access_User._id).toString().equals("2")) {
                        html.append("$('#TrIdInUserForm').show();\n");
                    } else {
                        html.append("$('#TrIdInUserForm').hide();\n");
                    }
                    for (int i = 0; i < groups.size(); i++) {
                        List<Map<String, Object>> group = jjDatabase.separateRow(
                                db.Select(Access_Group.tableName, Access_Group._id + "=" + groups.get(i).get(Access_Group_User._group_id)));
                        if (group.size() > 0) {
                            for (int j = 1; j < Access_Group.chkNumber; j++) {
                                String rulId = "0";//defult value,to privent null pointer exeption in group_c
                                try {
                                    rulId = group.get(0).get("group_c" + (j < 10 ? "0" + j : j)).toString();
                                } catch (Exception ex) {
                                    ServerLog.Print(
                                            "خطایی در پایگاه داده: ردیف "
                                            + "group_c" + (j < 10 ? "0" + j : j)
                                            + "وجود ندارد، این خطا از طریق استثنا ها مدیریت شد");
                                    rulId = "0";
                                }
                                if (rulId.equals("1")) {
                                    if (!validInSeassion.toString().contains("$" + j + "$")) {
                                        validInSeassion.append("$" + j + "$");
                                    }
                                } else {
                                    noValidInSeassion.append("$" + j + "$");
                                    html.append("$('#C" + (j < 10 ? "0" + j : j) + "').attr('disabled','disabled');\n");
                                }
                            }
                        }
                    }
                    jjTools.setSessionAttribute(request, "#ACCESS", validInSeassion.toString());
                    jjTools.setSessionAttribute(request, "#NOACCESS", noValidInSeassion.toString());

                    //////////////////////////////مدیریت اطلاعات عمومی///////////////////////////
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#categoryContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#managmentNews' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_News.rul_rfs)) {
                            html.append("$( '#managmentNewsCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, UploadServlet.rul_rfs)) {
                            html.append("$( '#managmentFiles' ).show();\n");
                        }
                    }
                    /////////////////////////////ماژول فرم ساز///////////////////////////
                    if (Access_User.hasAccess(request, db, Forms.rul_formsModule)) {
                        html.append("$('#formsModule').show();\n");
                        if (Access_User.hasAccess(request, db, Forms.rul_rfs)) {
                            html.append("$( '#managmentForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveForms)) {
                            html.append("$( '#managmentComplitArshiveForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitFormsMe)) {
                            html.append("$( '#managmentFormsMe' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveFormsMe)) {
                            html.append("$( '#managmentComplitArshiveFormsMe' ).show();\n");
                        }
                    }

                    ////////////////////////////ماژول کمیته ها///////////////////////////
                    if (Access_User.hasAccess(request, db, Commettes.rul_showCommitesModule)) {
                        html.append("$('#CommitesModule').show();\n");
                        if (Access_User.hasAccess(request, db, Commettes.rul_rfs)) {
                            html.append("$( '#CommettesLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfs)) {
                            html.append("$( '#SessionLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsMySessions)) {
                            html.append("$( '#MySessionLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsarchivesSessions)) {
                            html.append("$( '#archivesSessionsLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Approved.rul_rfsMyApproved)) {
                            html.append("$( '#ApprovedLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsCommunicatedSessions)) {
                            html.append("$( '#CommunicatedSessionsLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Approved.rul_rfsIndicatorCommettes)) {
                            html.append("$( '#IndicatorCommettesLi' ).show();\n");
                        }
                    }
                    ////////////////////////////ماژول مدیریت مستندات اعتبار بخشی///////////////////////////
                    if (Access_User.hasAccess(request, db, ManagementGauges.rul_moduleManagementGauges)) {
                        html.append("$('#moduleManagementGages').show();\n");
                        if (Access_User.hasAccess(request, db, ManagementGauges.rul_rfs_all)) {
                            html.append("$( '#rul_rfsManagementGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, ManagementGauges.rul_rfs_own)) {
                            html.append("$( '#rul_rfsManagementGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Documentary.rul_myRfs)) {
                            html.append("$( '#rul_rfsMyArchiveGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Documentary.rul_rfs)) {
                            html.append("$( '#rul_rfsArchiveGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs)) {
                            html.append("$( '#rul_rfsCreatDocumentry' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs_communicateAll)) {
                            html.append("$( '#rul_rfsCommunications' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs_communicateOwn)) {
                            html.append("$( '#rul_rfsCommunications' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfsSignMydocumention)) {
                            html.append("$( '#rul_rfsMySignDocumentary' ).show();\n");
                        }
                    }

                    //////////////////////////ماژول دسترسی/////////////////////
                    if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                        html.append("$('#accessli').show();\n");
                        if (Access_User.hasAccess(request, db, Access_User.rul_rfs)) {
                            html.append("$( '#swUserli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Access_Group.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Role.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Department.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    //////////////////////////////////////////////

                    html.append("$( '#swLoging' ).hide();\n");
                    System.out.println("------------------------------------");
                    System.out.println(user.get(0).get(_attachPicPersonal).toString());
                    System.out.println("--------------------------------------");
                    if (user.get(0).get(_attachPicPersonal).equals("")) {
                        html.append("$('#picUserProfile').attr('src', 'Manager/img1.jpg');");
                        html.append("$('#picUserProfile1').attr('src', 'Manager/img1.jpg');");
                    } else {
                        html.append("$('#picUserProfile').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                        html.append("$('#picUserProfile1').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                    }
                    html.append("$('#emailUserProfile').html('" + user.get(0).get(_email).toString() + "');");
                    html.append("$('#nameUserProfile').html('" + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + "');");
//                    html.append("USER_FAMILY="+user.get(0).get(_family).toString());
                }
            } else {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, _id + "=" + jjTools.getSessionAttribute(request, "#ID")));
                if ((user.get(0).get(_no1)).toString().equals(user.get(0).get(_email)) || (user.get(0).get(_no1)).toString().equals(user.get(0).get(_pass))) {
                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("hmisUserProfile.loadForm();\n"
                            + "                                    hmisUserProfile.m_select();\n"
                            + "                                    hmisUserProfile.mainTabSetSize();");
                } else {

                    //////////////////////////////مدیریت اطلاعات عمومی///////////////////////////
                    if (Access_User.hasAccess(request, db, Content.rul_mudulcontent)) {
                        html.append("$('#generalInformationModule').show();\n");
                        if (Access_User.hasAccess(request, db, Content.rul_rfs)) {
                            html.append("$( '#managmentContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Content.rul_rfs)) {
                            html.append("$( '#categoryContent' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Pic.rul_rfs)) {
                            html.append("$( '#managmentPic' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_Gallery.rul_rfs)) {
                            html.append("$( '#managmentPicCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, News.rul_rfs)) {
                            html.append("$( '#managmentNews' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Category_News.rul_rfs)) {
                            html.append("$( '#managmentNewsCategory' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, UploadServlet.rul_rfs)) {
                            html.append("$( '#managmentFiles' ).show();\n");
                        }
                    }
                    /////////////////////////////ماژول فرم ساز///////////////////////////
                    if (Access_User.hasAccess(request, db, Forms.rul_formsModule)) {
                        html.append("$('#formsModule').show();\n");
                        if (Access_User.hasAccess(request, db, Forms.rul_rfs)) {
                            html.append("$( '#managmentForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveForms)) {
                            html.append("$( '#managmentComplitArshiveForms' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitFormsMe)) {
                            html.append("$( '#managmentFormsMe' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, FormAnswerSet.rul_rfsComplitArshiveFormsMe)) {
                            html.append("$( '#managmentComplitArshiveFormsMe' ).show();\n");
                        }
                    }

                    ////////////////////////////ماژول کمیته ها///////////////////////////
                    if (Access_User.hasAccess(request, db, Commettes.rul_showCommitesModule)) {
                        html.append("$('#CommitesModule').show();\n");
                        if (Access_User.hasAccess(request, db, Commettes.rul_rfs)) {
                            html.append("$( '#CommettesLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfs)) {
                            html.append("$( '#SessionLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsMySessions)) {
                            html.append("$( '#MySessionLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsarchivesSessions)) {
                            html.append("$( '#archivesSessionsLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Approved.rul_rfsMyApproved)) {
                            html.append("$( '#ApprovedLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Sessions.rul_rfsCommunicatedSessions)) {
                            html.append("$( '#CommunicatedSessionsLi' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Approved.rul_rfsIndicatorCommettes)) {
                            html.append("$( '#IndicatorCommettesLi' ).show();\n");
                        }
                    }
                    ///////////////////////// ماژول شاخص ها/////////////////////
                    ////////////////////////////ماژول مدیریت مستندات اعتبار بخشی///////////////////////////
                    if (Access_User.hasAccess(request, db, ManagementGauges.rul_moduleManagementGauges)) {
                        html.append("$('#moduleManagementGages').show();\n");
                        if (Access_User.hasAccess(request, db, ManagementGauges.rul_rfs_all)) {
                            html.append("$( '#rul_rfsManagementGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, ManagementGauges.rul_rfs_own)) {
                            html.append("$( '#rul_rfsManagementGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Documentary.rul_myRfs)) {
                            html.append("$( '#rul_rfsMyArchiveGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Documentary.rul_rfs)) {
                            html.append("$( '#rul_rfsArchiveGages' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs)) {
                            html.append("$( '#rul_rfsCreatDocumentry' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs_communicateAll)) {
                            html.append("$( '#rul_rfsCommunications' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfs_communicateOwn)) {
                            html.append("$( '#rul_rfsCommunications' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, CreateDocumentary.rul_rfsSignMydocumention)) {
                            html.append("$( '#rul_rfsMySignDocumentary' ).show();\n");
                        }
                    }

                    //////////////////////////ماژول دسترسی/////////////////////
                    if (Access_User.hasAccess(request, db, rul_rfsAll)) {
                        html.append("$('#accessli').show();\n");
                        if (Access_User.hasAccess(request, db, Access_User.rul_rfs)) {
                            html.append("$( '#swUserli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Access_Group.rul_rfs)) {
                            html.append("$( '#swGroupli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Role.rul_rfs)) {
                            html.append("$( '#swRoleli' ).show();\n");
                        }
                        if (Access_User.hasAccess(request, db, Department.rul_rfs)) {
                            html.append("$( '#swDepartmentli' ).show();\n");
                        }
                    }
                    //////////////////////////////////////////////

                    html.append("$( '#swLoging' ).hide();\n");
                    html.append("new jj('do=Content.sw&panel=swAccreditationAxes&text=182&jj=1').jjAjax2(true);");
                    System.out.println("------------------------------------");
                    System.out.println(user.get(0).get(_attachPicPersonal).toString());
                    System.out.println("--------------------------------------");
                    if (user.get(0).get(_attachPicPersonal).equals("")) {
                        html.append("$('#picUserProfile').attr('src', 'Manager/img1.jpg');");
                        html.append("$('#picUserProfile1').attr('src', 'Manager/img1.jpg');");
                    } else {
                        html.append("$('#picUserProfile').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                        html.append("$('#picUserProfile1').attr('src', 'upload/" + user.get(0).get(_attachPicPersonal).toString() + "');");
                    }
                    html.append("$('#emailUserProfile').html('" + user.get(0).get(_email).toString() + "');");
                    html.append("$('#nameUserProfile').html('" + user.get(0).get(_name).toString() + " " + user.get(0).get(_family).toString() + "');");
//                    html.append("USER_FAMILY="+user.get(0).get(_family).toString());
                }
            }
            //            html.append("hmisDashbord.showDashbord();");
            html.append("new jj('do=Content.sw&panel=swAccreditationAxes&text=182&jj=1').jjAjax2(true);");
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String loginUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String email = jjTools.getParameter(request, _email).toLowerCase().replace(" or ", "").toLowerCase();
            String passRequest = jjTools.getParameter(request, Access_User._pass).toLowerCase().replace(" or ", "").toLowerCase();
            // --------------------------------------------------------------- //
            if (!email.equals("") && !passRequest.equals("")) {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, Access_User._email + "='" + email
                        + "' AND " + Access_User._pass + "='" + passRequest + "'"));
                if (user.size() == 1) {
                    Server.outPrinter(request, response, afterUserLoginOrRegist(request, response, db, needString, user.get(0)));
                    return "";
                } else {
                    String comment = "ایمیل و یا رمز عبور صحیح نمی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email or Password is not currect.";
                    }
                    Server.outPrinter(request, response, comment);
                    return "";
                }
            } else {
                String comment = "ایمیل و رمز عبور نباید تهی باشد.";
                if (jjTools.isLangEn(request)) {
                    comment = "Email and Password don't must be empty.";
                }
                Server.outPrinter(request, response, comment);
                return "";
            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String loginMstid(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = String.valueOf(jjTools.getSeassionUserId(request));
            String url = jjTools.getParameter(request, "url").toLowerCase().replace(" or ", "").toLowerCase();

            // --------------------------------------------------------------- //
            if (!id.equals("") && !id.equals("0")) {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, Access_User._id + "='" + id + "'"));
                if (user.size() == 1) {
                    System.out.println("//...////" + id);
                    Server.outPrinter(request, response, afterUserLoginOrRegistSite(request, response, db, url, user.get(0)));
                    return "";
                } else {
                    String comment = "ایمیل و یا رمز عبور صحیح نمی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email or Password is not currect.";
                    }
                    Server.outPrinter(request, response, comment);
                    return "";
                }
            } else {
                String comment = "ایمیل و رمز عبور نباید تهی باشد.";
                if (jjTools.isLangEn(request)) {
                    comment = "Email and Password don't must be empty.";
                }
                Server.outPrinter(request, response, "");
                return "";
            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String afterUserLoginOrRegist(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost, Map<String, Object> user) throws IOException {
        try {
            StringBuilder html = new StringBuilder();
            jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), user.get(Access_User._id).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), user.get(Access_User._name).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), user.get(Access_User._family).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), user.get(Access_User._pass).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._email.toUpperCase(), user.get(Access_User._email).toString());
            String panel = jjTools.getParameter(request, "panel");
            if (!panel.equals("")) {
                html.append(Js.setHtml("#userNameAfterLogin", "<a onclick='signOut();'>" + user.get(Access_User._name).toString() + "&nbsp;" + user.get(Access_User._family).toString() + "&nbsp;("
                        + (jjTools.isLangFa(request) ? "خروج" : "SignOut") + ")</a>") + ";\n");
            }
//            html.append("swPrivate('swPrivate');");
            Server.outPrinter(request, response, html.toString() + "$('#button_login').hide() ," + "$('#sw').html('');");
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String signOut(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws IOException {
        jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), "");
        jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), "");
        jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), "");
        jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), "");
        jjTools.setSessionAttribute(request, "#" + Access_User._email.toUpperCase(), "");
        request.getSession().invalidate();

        Server.outPrinter(request, response, "window.localStorage.setItem('user_token','');" + "window.location.href = '/';");
        return "";
    }

    public static String registUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String email = jjTools.getParameter(request, _email);
//            String mobile = jjTools.getParameter(request, _email);

            //============ BY RASHIDI ========>
            // ------------- check email or mobile are not empty -------------------
            if (email.equals("")) {
                String errorMsg = "وارد کردن ایمیل و یا شماره موبایل الزامیست";
                if (jjTools.isLangEn(request)) {
                    errorMsg = "Enter phone number or email address";
                }
                Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", errorMsg));
                return "";
            }
            //<============ BY RASHIDI ========

            String message = isValidMessageForRegist(request, response, db, needString);
            if (!message.equals("")) {
                Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", message));
                return "";
            }

            // ------------- check equal pass and repeat pass -------------------
            boolean eqPass = jjTools.getParameter(request, _pass).toLowerCase().equals(jjTools.getParameter(request, _pass + "_2").toLowerCase());
            if (!eqPass) {
                String mes = "رمز عبور با تکرار آن باید یکی باشند.";
                if (jjTools.isLangEn(request)) {
                    mes = "Password1 and Password2 must be equal.";
                }
                Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", mes));
                return "";
            }

            // ------------- check family is not empty -------------------
            String reqAnswer = jjValidation.isFillMessageFa(jjTools.getParameter(request, _family), "نام خانوادگی");
            if (!reqAnswer.equals("")) {
                if (jjTools.isLangEn(request)) {
                    reqAnswer = jjValidation.isFillMessageEn(jjTools.getParameter(request, _family), "family");
                }
                Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", reqAnswer));
                return "";
            }

            // --------------------------- data is valid ------------------------------
            //============ BY RASHIDI ========>
            //ایمیل یا شماره موبایل تکراری نباشد
            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(tableName, _email + "='" + email + "'"));
            if (userRow.isEmpty()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_email, email);
                map.put(_name, jjTools.getParameter(request, _name).toLowerCase());
                map.put(_family, jjTools.getParameter(request, _family).toLowerCase());
                map.put(_isActive, true);
                map.put(_pass, jjTools.getParameter(request, _pass));
                map.put(_passHint, jjTools.getParameter(request, _passHint));
                List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
                if (row.isEmpty()) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "registUser Fail;";
                    }
                    Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", errorMessage));
                    return "";
                } else {
                    String body = "";
                    body += "<p dir='rtl'>کاربر گرامی  "
                            + row.get(0).get(_name) + "&nbsp;" + row.get(0).get(_family) + "</p>\n"
                            + "<p dir='rtl'>از ثبت نام شما در سایت  "
                            + "  متشکریم</p>\n"
                            + "<p dir='rtl'>UserName/Email: " + row.get(0).get(_email) + "</p>\n"
                            + "<p dir='rtl'>And Password:" + row.get(0).get(_pass) + "</p>\n";
                    Server.sendEmail("", row.get(0).get(_email).toString(), "ثبت نام در سایت ", body, true, request, db);
                    Server.outPrinter(request, response, afterUserLoginOrRegist(request, response, db, needString, row.get(0)));
                    return "";
                }
            } else {
                String mes = "ایمیل تکراری است.";
                if (jjTools.isLangEn(request)) {
                    mes = "Email or phone number is reapeted";
                }
                Server.outPrinter(request, response, Js.setHtml("#registMessagePanel", mes));
                return "";
            }
            //<============ BY RASHIDI ========
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این متد فقط سمت جاوا فراخوانی می شود و پاسخی به کلاینت مستقیما نمی فرستد
     *
     * @param request
     * @param db
     * @param ruleId
     * @Server.outPrinter(request, response, Not Access Dialog if no access and
     * reurn "" if has access
     */
    public static String getAccessDialog(HttpServletRequest request, jjDatabaseWeb db, int ruleId) throws IOException {
        if (jjTools.getSeassionUserId(request) == 0) {
            return "0";
        }
        if (ruleId == 0) {
            return "0";
        }
        if (jjTools.getSessionAttribute(request, "#ACCESS").toLowerCase().contains("$" + String.valueOf(ruleId) + "$")) {
            return "1";
        } else {
            if (jjTools.isLangFa(request)) {
                return "0";
            } else {
                return "0";
            }
        }
    }

    public static String getUserName(String userId, jjDatabaseWeb db) throws Exception {
        try {
            if (jjNumber.isDigit(userId)) {
                List<Map<String, Object>> UserRowRole = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + userId));
                if (!UserRowRole.isEmpty()) {
                    return UserRowRole.get(0).get(_name).toString() + "-" + UserRowRole.get(0).get(_family).toString();
                }
            }
            return "";

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getAccessDialog(HttpServletRequest request, HttpServletResponse response, jjDatabase db, int ruleId) throws IOException {
        if (ruleId == 0) {
            Server.outPrinter(request, response, "");
            return "";
        }
        if (jjTools.getSessionAttribute(request, "#ACCESS").toLowerCase().contains("$" + String.valueOf(ruleId) + "$")) {
            Server.outPrinter(request, response, "");
            return "";
        } else {
            if (jjTools.isLangFa(request)) {
                Server.outPrinter(request, response, Js.dialog(noAccessFa));
                return "";
            } else {
                Server.outPrinter(request, response, Js.dialog(noAccessEn));
                return "";
            }
        }
    }

    public static boolean hasAccess(HttpServletRequest request, jjDatabaseWeb db, int ruleId) {
        if (ruleId == 0) {
            return true;
        }
        return jjTools.getSessionAttribute(request, "#ACCESS").toLowerCase().contains("$" + String.valueOf(ruleId) + "$");
    }

    public static String loginPortalUser(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        //نام کاربری و رمز عبور بهتر است در پایگاه داده با قسمت کاربران سینک شود
        try {
            String email = jjTools.getParameter(request, PortalUser._UserName).toLowerCase().replace(" or ", "").toLowerCase();
            String passRequest = jjTools.getParameter(request, PortalUser._pass).toLowerCase().replace(" or ", "").toLowerCase();
            String portalId = jjTools.getParameter(request, PortalUser._id);
            // --------------------------------------------------------------- //
            StringBuilder html = new StringBuilder();
            if (!email.equals("") || !passRequest.equals("")) {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        PortalUser.tableName, PortalUser._UserName + "='" + email
                        + "' AND " + PortalUser._pass + "='" + passRequest + "'"
                        + " AND " + PortalUser._id + "='" + portalId + "'"));
                if (user.size() == 1) {
                    Server.outPrinter(request, response, afterUserPortalLoginOrRegist(request, response, db, needString, user.get(0), portalId));
                    return "";
//                    Server.outPrinter(request, response, Js.dialog("کاربر گرامی خوش آمدید");
                } else {
                    String comment = "ایمیل و یا رمز عبور صحیح نمی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email or Password is not currect.";
                    }
                    Server.outPrinter(request, response, Js.setHtml("#loginMessagePanel", comment));
                    return "";
                }
            } else {
                String comment = "ایمیل و رمز عبور نباید تهی باشد.";
                if (jjTools.isLangEn(request)) {
                    comment = "Email and Password don't must be empty.";
                }
                Server.outPrinter(request, response, Js.dialog(comment));
                return "";
            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * برای ویرایش پروفایل کاربر هنوز کمی کار دارد
     *
     * برای فلوریت درست شد که قسمت سفارش آپدیت بشه
     *
     * @param request
     * @param db
     * @param isPost
     * @Server.outPrinter(request, response,
     * @throws Exception
     */
    public static String updateUserProfile(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {

            String id = String.valueOf(jjTools.getSeassionUserId(request));
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                Server.outPrinter(request, response, Js.dialog(errorMessageId));
                return "";
            }

            Map<String, Object> map = new HashMap<String, Object>();
            if (!jjTools.getParameter(request, _answer).isEmpty()) {
                map.put(_answer, jjTools.getParameter(request, _answer));
            }
            if (!jjTools.getParameter(request, _question).isEmpty()) {
                map.put(_question, jjTools.getParameter(request, _question));
            }
            if (!jjTools.getParameter(request, _answer).isEmpty()) {
                map.put(_family, jjTools.getParameter(request, _family));
            }
            if (!jjTools.getParameter(request, _name).isEmpty()) {
                map.put(_name, jjTools.getParameter(request, _name));
            }
            if (!jjTools.getParameter(request, _name).isEmpty()) {
                map.put(_no1, jjTools.getParameter(request, _no1));
            }
            if (!jjTools.getParameter(request, _no1).isEmpty()) {
                map.put(_no2, jjTools.getParameter(request, _no2));
            }
            if (!jjTools.getParameter(request, _no2).isEmpty()) {
                map.put(_address, jjTools.getParameter(request, _address));
            }
            map.put(_address2, jjTools.getParameter(request, _address2));//اگر آدرس 2 خالی باشد پاک کنیم برای اینکه اشتباها از قبل وارد نشده باشد
            if (!jjTools.getParameter(request, _address2).isEmpty()) {
                map.put(_postalCode, jjTools.getParameter(request, _postalCode));
            }
            if (!jjTools.getParameter(request, _mobile).isEmpty()) {
                map.put(_mobile, jjTools.getParameter(request, _mobile));
            }
            if (!jjTools.getParameter(request, _phone).isEmpty()) {
                map.put(_phone, jjTools.getParameter(request, _phone));
            }
            if (!jjTools.getParameter(request, _birthdate).isEmpty()) {
                map.put(_birthdate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birthdate), false));
            }

            if (id.equals("1")) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            // =========================
            Server.outPrinter(request, response, "");
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String afterUserPortalLoginOrRegist(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString, Map<String, Object> user, String portalId) throws IOException {
        StringBuilder html = new StringBuilder();
        try {
            request.getSession().invalidate();
            jjTools.setSessionAttribute(request, "#" + Portal._ownerId.toUpperCase(), portalId);//use in edit Portal.portalPostLoadforEdit()
            jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), user.get(PortalUser._UserId).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), user.get(PortalUser._firstName).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), user.get(PortalUser._lastName).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), user.get(PortalUser._pass).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._email.toUpperCase(), user.get(PortalUser._UserName).toString());

//            html.append("USER_ID = \"" + user.get(PortalUser._UserId).toString().replace("\"", "'") + "\";\n");
//            html.append("USER_NAME = \"" + user.get(PortalUser._firstName).toString().replace("\"", "'") + "\";\n");
//            html.append("USER_FAMILY = \"" + user.get(PortalUser._lastName).toString().replace("\"", "'") + "\";\n");
//            html.append("USER_EMAIL = \"" + user.get(PortalUser._UserName).toString().replace("\"", "'") + "\";\n");
//            html.append("USER_PASS = \"" + user.get(PortalUser._pass).toString().replace("\"", "'") + "\";\n");
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;

            html.append(Js.setHtml("#" + panel, user.get(PortalUser._firstName).toString() + "&nbsp;" + user.get(PortalUser._lastName).toString() + "&nbsp;("
                    + (jjTools.isLangFa(request) ? "خروج" : "SignOut") + ")") + ";\n");

            html.append(Js.setHtml("#jjLoginExitPanel", " <div>"
                    + user.get(PortalUser._firstName).toString().replace("\"", "'")
                    + "خوش آمدید"
                    + "<br/>"
                    + "<br/>"
                    + "<a onclick='portalUserLoadForm();'>"
                    + "ویرایش مشخصات"
                    + "</a>"
                    + "<br/>"
                    + "<a onclick='addNewPortalPost(" + portalId + ");'>"
                    + "افزودن مطلب جدید"
                    + "</a>"
                    + "<br/>"
                    + "<a onclick='loadPortalPostforEdit(" + portalId + ");'>"
                    + "ویرایش مطالب"
                    + "</a>"
                    + "<br/>"
                    + "</div>"));
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این متد کاربران فعال را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
     *
     * @param request panel درون ریکوئست اگر با نقطه شروع نشود آی دی در نظر می
     * گیرد و نامبر ساین اولش می گذارد
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

//            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.JoinLeft(tableName, Role.tableName, "access_user.id," + _name + "," + Role._title + "," + _family, _id, Role._user_id, " WHERE  access_user.id>5 AND " + _isActive + "=1"));// بر اساس حروف الفبا مرتب باشد بهتر است
            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.otherSelect(
                    "SELECT group_concat(hmis_role.role_title)as title,access_user.id,user_name,role_title,user_family,user_codeMeli FROM access_user\n"
                    + " LEFT JOIN hmis_role ON\n"
                    + " access_user.id = hmis_role.role_user_id  WHERE  access_user.id>5 AND user_is_active=1 group by id"));// بر اساس حروف الفبا مرتب باشد بهتر است
            optionHtml.append("<option  value='ALL'>تمام کاربران ثبت شده</option>");
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_family) + "-").append(rowAllActiveRols.get(i).get(_name)).append("(" + rowAllActiveRols.get(i).get(_codeMeli) + ")").append("</option>");
            }
            if (needString) {// اگر فقط اچ تی ام ال را بخواهیم که معمولا در کد های جی اس پی یا فراخوانی های سمت سرور اینگونه است
                return optionHtml.toString();
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".usersSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String getSelectOptionNotAll(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.Select(tableName, _id + "," + _name + "," + _family, "id>5 AND " + _isActive + "=1", _family));// بر اساس حروف الفبا مرتب باشد بهتر است
//            optionHtml.append("<option  value='ALL'>تمام کاربران ثبت شده</option>");
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_family) + "-").append(rowAllActiveRols.get(i).get(_name)).append("</option>");
            }
            if (needString) {// اگر فقط اچ تی ام ال را بخواهیم که معمولا در کد های جی اس پی یا فراخوانی های سمت سرور اینگونه است
                return optionHtml.toString();
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".usersSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * *
     * این تابع برای وارد شدن در سایت بعد از لاگین سایت اصلی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String loginUserInSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            if ((jjTools.getSessionAttribute(request, "#ID")).equals("")) {
                String email = jjTools.getParameter(request, _email).toLowerCase().replace(" or ", "").toLowerCase();
                String passRequest = jjTools.getParameter(request, Access_User._pass).toLowerCase().replace(" or ", "").toLowerCase();
                String url = jjTools.getParameter(request, "url").toLowerCase().replace(" or ", "").toLowerCase();

                // --------------------------------------------------------------- //
                if (!email.equals("") && !passRequest.equals("")) {
                    List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                            Access_User.tableName, Access_User._email + "='" + email
                            + "' AND " + Access_User._pass + "='" + passRequest + "'"));
                    if (user.size() == 1) {
                        Server.outPrinter(request, response, afterUserLoginOrRegistSite(request, response, db, url, user.get(0)));
                        return "";
                    } else {

                        String comment = "ایمیل و یا رمز عبور صحیح نمی باشد.";
                        if (jjTools.isLangEn(request)) {
                            comment = "Email or Password is not currect.";
                        }
                        Server.outPrinter(request, response, "$('#errorLogin').html('" + comment + "');");
                        return "";
                    }

                } else {
                    String comment = "ایمیل و رمز عبور نباید تهی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email and Password don't must be empty.";
                    }
                    Server.outPrinter(request, response, "$('#errorLogin').html('" + comment + "');");
                    return "";
                }
            } else {
                int id = jjTools.getSeassionUserId(request);
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + id));
                String panel = jjTools.getParameter(request, "panel");
                if (row.size() == 0) {
                } else {
                    if (panel.equals("")) {
                        panel = "#userNameAfterLogin";
                    }
                    html.append(Js.setHtml(panel, "<div class='singOutInSite'><i class='fa fa-user'></i><div class='userNameInSite' >" + row.get(0).get(Access_User._name).toString() + " " + row.get(0).get(Access_User._family).toString() + ""
                            + "</div><a onclick='signOut();'>(" + (jjTools.isLangFa(request) ? "خروج" : "SignOut") + ")</a></div>") + ";\n");
                }
            }
            Server.outPrinter(request, response, html.toString());
            return "";

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * *
     * این تابع برای ورود به سایت در صفحه اصلی سایت
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String loginUserInHome(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String user_token = jjTools.getParameter(request, "user_token").toLowerCase().replace(" or ", "").toLowerCase();
            String StudentId = jjTools.getParameter(request, "StudentId").toLowerCase().replace(" or ", "").toLowerCase();
            String passRequest = jjTools.getParameter(request, "pass").toLowerCase().replace(" or ", "").toLowerCase();
            String url = jjTools.getParameter(request, "url");

            // --------------------------------------------------------------- //
            if (!StudentId.equals("") && !passRequest.equals("")) {
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                        Access_User.tableName, Access_User._int1 + "='" + StudentId
                        + "' AND " + Access_User._pass + "='" + passRequest + "'"));
                if (user.size() == 1) {
                    if (user.get(0).get(_isActive).toString().equals("0")) {
                        Server.outPrinter(request, response, Js.modal("کاربر غیر فعال", "شما کاربر فعال این مجموعه نیستید"));
                    } else {
                        Server.outPrinter(request, response, afterUserLoginOrRegistSite(request, response, db, url, user.get(0)));
                    }
                    return "";
                } else {
                    String comment = "ایمیل و یا رمز عبور صحیح نمی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email or Password is not currect.";
                    }
                    Server.outPrinter(request, response, Js.setHtml("#loginMessagePanel", comment));
                    return "";
                }

            } else {
                if (!user_token.equals("")) {
                    List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(
                            Access_User.tableName, Access_User._token + "='" + user_token + "'"));
                    System.out.println(user.size() + ".........................................................................................p");

                    if (user.size() == 1) {
                        System.out.println(user.size() + ".........................................................................................p");

                        Server.outPrinter(request, response, afterUserLoginOrRegistSite(request, response, db, url, user.get(0)));
                        return "";
                    }
                } else {

                    String comment = "ایمیل و رمز عبور نباید تهی باشد.";
                    if (jjTools.isLangEn(request)) {
                        comment = "Email and Password don't must be empty.";
                    }

                    Server.outPrinter(request, response, Js.setHtml("#loginMessagePanel", comment));
                    return "";
                }
            }

        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
        return null;
    }

    /**
     * این تابع بعداز لاگین در سایت اصلی انجام می شود
     *
     * @param request
     * @param db
     * @param isPost
     * @param user
     * @return
     * @throws IOException
     */
    public static String afterUserLoginOrRegistSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, String url, Map<String, Object> user) throws IOException {
        StringBuilder html = new StringBuilder();
        try {
            if (user.get(Access_User._token).equals("")) {
                System.out.println("////////////////////token////////////////////////");
                String random = jjNumber.getRandom(10);
                random += jjNumber.getRandom(10);
                System.out.println("//////////////////////////token////////////////////////");
                Map<String, Object> map = new HashMap<>();
                map.put(_token, random);
                db.update(tableName, map, _id + "=" + user.get(Access_User._id));
                html.append("window.localStorage.setItem('user_token', '" + random + "');");
                System.out.println(".?.?+" + html);
            } else {
                html.append("window.localStorage.setItem('user_token', '" + user.get(Access_User._token) + "');");
            }
            jjTools.setSessionAttribute(request, "#" + Access_User._id.toUpperCase(), user.get(Access_User._id).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._name.toUpperCase(), user.get(Access_User._name).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._family.toUpperCase(), user.get(Access_User._family).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._pass.toUpperCase(), user.get(Access_User._pass).toString());
            jjTools.setSessionAttribute(request, "#" + Access_User._email.toUpperCase(), user.get(Access_User._email).toString());
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "userNameAfterLogin";
            }
            html.append(Js.setHtml("#userNameAfterLogin", "<nav id='mainnav'><ul class='menu'><li  class='userIcon'><i class='fa fa-user ' style='margin: 7px;'></i><ul class='submenu right-sub-menu'><li><a  href=''>" + user.get(Access_User._name).toString() + "&nbsp;" + user.get(Access_User._family).toString() + "</a></li><li><a  href='userProfile.jsp?user_token=" + user.get(Access_User._token).toString() + "'>پنل کاربری</a></li><li><a href='' onclick='signOut();'>خروج</a></li></ul></li></ul></nav>"));
//            html.append(Js.setHtml("#userNameAfterLogin", "<li class='  ' style='border:1px #eab702 solid'><a  style='cursor:pointer' href='userProfile.jsp?user_token=" + user.get(Access_User._token).toString() + "'>" + user.get(Access_User._name).toString() + "&nbsp;" + user.get(Access_User._family).toString() + "&nbsp;"
//                    + "<i class=\"fa fa-user-circle\"></i></a></li>") + ";\n");  
            html.append(Js.setHtml("#userNameAfterLogin1", "<li class='   ' style='color:black'>" + user.get(Access_User._name).toString() + "&nbsp;" + user.get(Access_User._family).toString() + "&nbsp;"
                    + "<a  style='cursor:pointer;' href='userProfile.jsp?user_token=" + user.get(Access_User._token).toString() + "''><i class=\"fa fa-user-circle\"></i></a></li>") + ";\n");
            html.append("sw('خوش آمدید'); ");
            String script = "";
            script += " var href =index.jsp;\n"
                    + "      window.location.href = href;";
            if (url.equals("")) {
                Server.outPrinter(request, response, html.toString() + "$('#button_login').hide()," + "$('#login_user').hide()" + ",$('.register_url').hide()");
            } else {
                Server.outPrinter(request, response, html.toString() + "$('#button_login').hide()," + "$('#login_user').hide()" + ";window.location.assign('" + url + "?user_token=" + user.get(Access_User._token) + "')");
            }
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * ثبت نام در سایت سامانه فرهنگی
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String registUserInSite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String email = jjTools.getParameter(request, _email);

            String url = jjTools.getParameter(request, "url");
            //============ BY RASHIDI ========>
            // ------------- check email or mobile are not empty -------------------
            if (email.equals("")) {
                String errorMsg = "وارد کردن ایمیل و یا شماره موبایل الزامیست";
                if (jjTools.isLangEn(request)) {
                    errorMsg = "Enter phone number or email address";
                }
                Server.outPrinter(request, response, "$('#errorregister').val('" + errorMsg + "');");
                return "";
            }

            List<Map<String, Object>> userRow = jjDatabase.separateRow(db.Select(tableName, _email + "='" + email + "'"));
            if (userRow.isEmpty()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_email, email);
                map.put(_name, jjTools.getParameter(request, _name).toLowerCase());
                map.put(_family, jjTools.getParameter(request, _family).toLowerCase());
                map.put(_isActive, true);
//                map.put(_parent, jjTools.getParameter(request, "parent"));
                map.put(_pass, jjTools.getParameter(request, _pass));
//                map.put(_passHint, jjTools.getParameter(request, _passHint));
                map.put(_mobile, jjTools.getParameter(request, _mobile));
//                map.put(_jensiat, jjTools.getParameter(request, _jensiat));
                map.put(_studentNumber, jjTools.getParameter(request, _studentNumber));
                map.put(_codeMeli, jjTools.getParameter(request, _codeMeli));
                int date = jjCalendar_IR.getDatabaseFormat_8length(null, true);
//		    map.put(_registDate,jjCalendar_IR.getViewFormat(date));
                map.put(_registDate, date);
                List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, map));
                if (row.isEmpty()) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("2")) {
                        errorMessage = "registUser Fail;";
                    }
                    Server.outPrinter(request, response, "$('#errorregister').val('" + errorMessage + "');");
                    return "";
                } else {
                    String body = "";
                    body += "<p dir='rtl'>کاربر گرامی  "
                            + row.get(0).get(_name) + "&nbsp;" + row.get(0).get(_family) + "</p>\n"
                            + "<p dir='rtl'>از ثبت نام شما در سایت  "
                            + "  متشکریم</p>\n"
                            + "<p dir='rtl'>UserName/Email: " + row.get(0).get(_email) + "</p>\n"
                            + "<p dir='rtl'>And Password:" + row.get(0).get(_pass) + "</p>\n";
                    Server.sendEmail("", row.get(0).get(_email).toString(), "ثبت نام در سایت ", body, true, request, db);
                    String script = "";
                    script += "alert('ثبت نام با موفقیت انجام گردید')";
                    Server.outPrinter(request, response, afterUserLoginOrRegistSite(request, response, db, url, row.get(0)));
//                    Server.outPrinter(request, response, script.toString() + "window.location.assign("+url+"?user_token)");
                    return "";
                }
            } else {
                String mes = "ایمیل تکراری است.";
                if (jjTools.isLangEn(request)) {
                    mes = "Email or phone number is reapeted";
                }
                Server.outPrinter(request, response, "$('#errorregister').val('" + mes + "');");
                return "";
            }
            //<============ BY RASHIDI ========
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    /**
     * این تابع برای مواقعی که میخواهیم به طرف نام کاربری و رمز عبور ولینک
     * دانلود تپ را بفرستیم استفاده میکنیم
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String send(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String reciversId = jjTools.getParameter(request, _id);
            List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + reciversId));// پیام فقط برای کابران فعال میرود : ویژگی
            String textMessage = " تعاونی مسکن کارکنان دادگستری اصفهان"
                    + "<br/>"
                    + " شماره عضویت:" + " " + userRow.get(0).get(Access_User._int1).toString()
                    + "<br/>"
                    + " رمز عبور:" + " " + userRow.get(0).get(Access_User._pass).toString() + " "
                    + "<br/>"
                    + "tmdke.ir";
            jjCalendar_IR date = new jjCalendar_IR();
            Messenger.sendMesseage(request, db, reciversId, "" + jjTools.getSeassionUserId(request) + "", "app,sms,email", String.valueOf(date.getDBFormat_8length()), "ارسال نام کاربری و رمز عبور", textMessage, "", "", "عادی", Tice_config.getValue(db, Tice_config._config_activeSms_name), Tice_config.getValue(db, Tice_config._config_activeEmail_name));
            Server.outPrinter(request, response, Js.modal("پیام با موفقیت ارسال شد", "ارسال پیام"));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }

    public static String updateAndroid(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            request.getRequestDispatcher("template/updateAndroid.jsp").forward(request, response);
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
        return "";
    }

    /**
     * درخواست پسورد فراموش شده درسامانه فرهنگی
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String sendEmailForgetPass(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {

        try {
            String script = "";
            String Email = jjTools.getParameter(request, "email");
            List<Map<String, Object>> emailRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._email + "='" + Email + "'"));
            if (emailRow.isEmpty()) {
                script += "alert('ایمیل را وارد کنید');";
                Server.outPrinter(request, response, script.toString());
                return "";
            } else {
                StringBuilder html = new StringBuilder();

                String Pass = emailRow.get(0).get(_pass).toString();

                if (Server.sendEmail("sepanoweb@gmail.com", Email, "درخواست پسورد فراموش شده", "pass:" + Pass, true, request, db)) {
                    script += "alert('ایمیل ارسال شد');";
                    Server.outPrinter(request, response, script.toString());
                    return "";
                }
                script += "alert('ایمیل ارسال نشد');";
                Server.outPrinter(request, response, script.toString());
                return "";

            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }

    }

    /**
     * ارسال مقاله در صفحه اصلی سایت اگر فرد لاگین باشد باید بتواند مقاله را
     * ارسال کند در غیر این صورت نمیتواند مقاله خود را ارسال نماید
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String sendArticel(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {

        try {
            String script = "";
            String Email = jjTools.getParameter(request, "email");
            List<Map<String, Object>> emailRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._email + "='" + Email + "'"));
            if (emailRow.isEmpty()) {
                script += "alert('ایمیل را وارد کنید');";
                Server.outPrinter(request, response, script.toString());
                return "";
            } else {
                StringBuilder html = new StringBuilder();

                String Pass = emailRow.get(0).get(_pass).toString();

                if (Server.sendEmail("sepanoweb@gmail.com", Email, "درخواست پسورد فراموش شده", "pass:" + Pass, true, request, db)) {
                    script += "alert('ایمیل ارسال شد');";
                    Server.outPrinter(request, response, script.toString());
                    return "";
                }
                script += "alert('ایمیل ارسال نشد');";
                Server.outPrinter(request, response, script.toString());
                return "";

            }
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }

    }

    /**
     * این متد کاربران فعال را بصورت آپشن برای قرار گرفتن در سلکت بر می گرداند
     *
     * @param request panel درون ریکوئست اگر با نقطه شروع نشود آی دی در نظر می
     * گیرد و نامبر ساین اولش می گذارد
     * @param response
     * @param db
     * @param needString
     * @return بصورت کد جی کوئری و یک سری آپشن برای قرار گرفتن در سلکتی که در
     * پنل معرفی شده
     * @throws Exception
     */
    public static String getSelectOptionNotNull(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        StringBuilder optionHtml = new StringBuilder();
        try {
            List<Map<String, Object>> rowAllActiveRols = jjDatabase.separateRow(db.Select(tableName, _id + "," + _name + "," + _family, "id>5 AND " + _isActive + "=1", _family));// بر اساس حروف الفبا مرتب باشد بهتر است
            for (int i = 0; i < rowAllActiveRols.size(); i++) {
                optionHtml.append("<option  value='").append(rowAllActiveRols.get(i).get(_id)).append("'>").append(rowAllActiveRols.get(i).get(_family) + "-").append(rowAllActiveRols.get(i).get(_name)).append("</option>");
            }
            if (needString) {// اگر فقط اچ تی ام ال را بخواهیم که معمولا در کد های جی اس پی یا فراخوانی های سمت سرور اینگونه است
                return optionHtml.toString();
            }
            String panel = jjTools.getParameter(request, "panel");
            if (panel.isEmpty()) {
                panel = ".usersSelectOption";
            }
            Server.outPrinter(request, response, Js.setHtml(panel, optionHtml));
            return "";
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
    }
}
