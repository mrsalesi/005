package cms.cms;

import cms.tools.*;
import cms.access.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Enrolment {
//=================================================<mirhaj

    public static String tableName = "enrolment";
    public static String _id = "id";
    public static String _send_date = "enrolment_send_date";
    public static String _title = "enrolment_title";
//  در این قسمت سی متغیر از نوع کاراکترهای پانصد تایی تعریف شده اند    
//        دو متغر از نوع long تعریف شده اند._val31 , _val32
//      چهار متغیر برای ذخیره عکس ها و دو متغیر برای دخیره فایل ها. 

    public static String _val1 = "enrolment_val1";
    public static String _val2 = "enrolment_val2";
    public static String _val3 = "enrolment_val3";
    public static String _val4 = "enrolment_val4";
    public static String _val5 = "enrolment_val5";
    public static String _val6 = "enrolment_val6";
    public static String _val7 = "enrolment_val7";
    public static String _val8 = "enrolment_val8";
    public static String _val9 = "enrolment_val9";
    public static String _val10 = "enrolment_val10";
    public static String _val11 = "enrolment_val11";
    public static String _val12 = "enrolment_val12";
    public static String _val13 = "enrolment_val13";
    public static String _val14 = "enrolment_val14";
    public static String _val15 = "enrolment_val15";
    public static String _val16 = "enrolment_val16";
    public static String _val17 = "enrolment_val17";
    public static String _val18 = "enrolment_val18";
    public static String _val19 = "enrolment_val19";
    public static String _val20 = "enrolment_val20";
    public static String _val21 = "enrolment_val21";
    public static String _val22 = "enrolment_val22";
    public static String _val23 = "enrolment_val23";
    public static String _val24 = "enrolment_val24";
    public static String _val25 = "enrolment_val25";
    public static String _val26 = "enrolment_val26";
    public static String _val27 = "enrolment_val27";
    public static String _val28 = "enrolment_val28";
    public static String _val29 = "enrolment_val29";
    public static String _val30 = "enrolment_val30";
    public static String _val31 = "enrolment_val31";
    public static String _val32 = "enrolment_val32";
    public static String _pic1 = "enrolment_pic1";
    public static String _pic2 = "enrolment_pic2";
    public static String _pic3 = "enrolment_pic3";
    public static String _pic4 = "enrolment_pic4";
    public static String _file1 = "enrolment_file1";
    public static String _file2 = "enrolment_file2";

    public static String _val01 = "enrolment_val01";
    public static String _val02 = "enrolment_val02";
    public static String _val03 = "enrolment_val03";
    public static String _val04 = "enrolment_val04";
    public static String _val05 = "enrolment_val05";
    public static String _val06 = "enrolment_val06";
    public static String _val07 = "enrolment_val07";
    public static String _val08 = "enrolment_val08";
    public static String _val09 = "enrolment_val09";
    public static String _val010 = "enrolment_val010";
    public static String _val011 = "enrolment_val011";
    public static String _val012 = "enrolment_val012";
    public static String _val013 = "enrolment_val013";
    public static String _val014 = "enrolment_val014";
    public static String _val015 = "enrolment_val015";
    public static String _val016 = "enrolment_val016";
    public static String _val017 = "enrolment_val017";
    public static String _val018 = "enrolment_val018";
    public static String _val019 = "enrolment_val019";
    public static String _val020 = "enrolment_val020";
    public static String _val021 = "enrolment_val021";
    public static String _val022 = "enrolment_val022";
    public static String _val023 = "enrolment_val023";
    public static String _val024 = "enrolment_val024";
    public static String _val025 = "enrolment_val025";
    public static String _val026 = "enrolment_val026";
    public static String _val027 = "enrolment_val027";
    public static String _val028 = "enrolment_val028";
    public static String _val029 = "enrolment_val029";
    public static String _val030 = "enrolment_val030";
    public static String _val031 = "enrolment_val031";
    public static String _val032 = "enrolment_val032";

    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
//    public static int rul_rfs = 61;
//    public static int rul_dlt = 62;
//    public static int rul_senEmail = 0;

    public static int rul_rfs = 161;
//    public static int rul_ins = 262;
//    public static int rul_edt = 263;
    public static int rul_dlt = 162;
    public static int rul_senEmail = 163;
//    public static int rul_reserved = 164 --- 180;// RESERVED : 164 -- 180

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshEnrolment' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='30%'>موضوع</th>");
            html.append("<th width='20%'>ستون سوم</th>");
            html.append("<th width='20%'>ستون چهارم</th>");
            html.append("<th width='20%'>تاریخ ارسال</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='cmsEnrolment.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_val1).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_val2).toString()) + "</td>");
                html.append("<td class='r'>" + (jjCalendar_IR.getViewFormatRtlLeft(row.get(i).get(_send_date).toString())) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swEnrolmentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshEnrolment", height, 0, "", "لیست فرم های استخدام");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            if (row.size() == 0) {
                return Js.dialog("رکوردی با این کد وجود ندارد");
            }

            if (!_pic1.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_pic1).toString());
                jjFile.deletefile(request, db, true);
            }

            if (!_pic2.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_pic2).toString());
                jjFile.deletefile(request, db, true);
            }

            if (!_pic3.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_pic3).toString());
                jjFile.deletefile(request, db, true);
            }

            if (!_pic4.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_pic4).toString());
                jjFile.deletefile(request, db, true);
            }

            if (!_file1.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_file1).toString());
                jjFile.deletefile(request, db, true);
            }

            if (!_file2.equals("")) {
                request.setAttribute("fileName", row.get(0).get(_file2).toString());
                jjFile.deletefile(request, db, true);
            }

            return Js.jjEnrolment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_send_date, new jjCalendar_IR().getDBFormat_8length());
            if ((jjTools.getParameter(request, _val1) + jjTools.getParameter(request, _val01)).length() <= 500) {
                map.put(_val1, jjTools.getParameter(request, _val1) + jjTools.getParameter(request, _val01));
            } else {
                map.put(_val1, (jjTools.getParameter(request, _val1) + jjTools.getParameter(request, _val01)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val2) + jjTools.getParameter(request, _val02)).length() <= 500) {
                map.put(_val2, jjTools.getParameter(request, _val2) + jjTools.getParameter(request, _val02));
            } else {
                map.put(_val2, (jjTools.getParameter(request, _val2) + jjTools.getParameter(request, _val02)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val3) + jjTools.getParameter(request, _val03)).length() <= 500) {
                map.put(_val3, jjTools.getParameter(request, _val3) + jjTools.getParameter(request, _val03));
            } else {
                map.put(_val3, (jjTools.getParameter(request, _val3) + jjTools.getParameter(request, _val03)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val4) + jjTools.getParameter(request, _val04)).length() <= 500) {
                map.put(_val4, jjTools.getParameter(request, _val4) + jjTools.getParameter(request, _val04));
            } else {
                map.put(_val4, (jjTools.getParameter(request, _val4) + jjTools.getParameter(request, _val04)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val5) + jjTools.getParameter(request, _val05)).length() <= 500) {
                map.put(_val5, jjTools.getParameter(request, _val5) + jjTools.getParameter(request, _val05));
            } else {
                map.put(_val5, (jjTools.getParameter(request, _val5) + jjTools.getParameter(request, _val05)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val6) + jjTools.getParameter(request, _val06)).length() <= 500) {
                map.put(_val6, jjTools.getParameter(request, _val6) + jjTools.getParameter(request, _val06));
            } else {
                map.put(_val6, (jjTools.getParameter(request, _val6) + jjTools.getParameter(request, _val06)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val7) + jjTools.getParameter(request, _val07)).length() <= 500) {
                map.put(_val7, jjTools.getParameter(request, _val7) + jjTools.getParameter(request, _val07));
            } else {
                map.put(_val7, (jjTools.getParameter(request, _val7) + jjTools.getParameter(request, _val07)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val8) + jjTools.getParameter(request, _val08)).length() <= 500) {
                map.put(_val8, jjTools.getParameter(request, _val8) + jjTools.getParameter(request, _val08));
            } else {
                map.put(_val8, (jjTools.getParameter(request, _val8) + jjTools.getParameter(request, _val08)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val9) + jjTools.getParameter(request, _val09)).length() <= 500) {
                map.put(_val9, jjTools.getParameter(request, _val9) + jjTools.getParameter(request, _val09));
            } else {
                map.put(_val9, (jjTools.getParameter(request, _val9) + jjTools.getParameter(request, _val09)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val10) + jjTools.getParameter(request, _val010)).length() <= 500) {
                map.put(_val10, jjTools.getParameter(request, _val10) + jjTools.getParameter(request, _val010));
            } else {
                map.put(_val10, (jjTools.getParameter(request, _val10) + jjTools.getParameter(request, _val010)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val11) + jjTools.getParameter(request, _val011)).length() <= 500) {
                map.put(_val11, jjTools.getParameter(request, _val11) + jjTools.getParameter(request, _val011));
            } else {
                map.put(_val11, (jjTools.getParameter(request, _val11) + jjTools.getParameter(request, _val011)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val12) + jjTools.getParameter(request, _val012)).length() <= 500) {
                map.put(_val12, jjTools.getParameter(request, _val12) + jjTools.getParameter(request, _val012));
            } else {
                map.put(_val12, (jjTools.getParameter(request, _val12) + jjTools.getParameter(request, _val012)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val13) + jjTools.getParameter(request, _val013)).length() <= 500) {
                map.put(_val13, jjTools.getParameter(request, _val13) + jjTools.getParameter(request, _val013));
            } else {
                map.put(_val13, (jjTools.getParameter(request, _val13) + jjTools.getParameter(request, _val013)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val14) + jjTools.getParameter(request, _val014)).length() <= 500) {
                map.put(_val14, jjTools.getParameter(request, _val14) + jjTools.getParameter(request, _val014));
            } else {
                map.put(_val14, (jjTools.getParameter(request, _val14) + jjTools.getParameter(request, _val014)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val15) + jjTools.getParameter(request, _val015)).length() <= 500) {
                map.put(_val15, jjTools.getParameter(request, _val15) + jjTools.getParameter(request, _val015));
            } else {
                map.put(_val15, (jjTools.getParameter(request, _val15) + jjTools.getParameter(request, _val015)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val16) + jjTools.getParameter(request, _val016)).length() <= 500) {
                map.put(_val16, jjTools.getParameter(request, _val16) + jjTools.getParameter(request, _val016));
            } else {
                map.put(_val16, (jjTools.getParameter(request, _val16) + jjTools.getParameter(request, _val016)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val17) + jjTools.getParameter(request, _val017)).length() <= 500) {
                map.put(_val17, jjTools.getParameter(request, _val17) + jjTools.getParameter(request, _val017));
            } else {
                map.put(_val17, (jjTools.getParameter(request, _val17) + jjTools.getParameter(request, _val017)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val18) + jjTools.getParameter(request, _val018)).length() <= 500) {
                map.put(_val18, jjTools.getParameter(request, _val18) + jjTools.getParameter(request, _val018));
            } else {
                map.put(_val18, (jjTools.getParameter(request, _val18) + jjTools.getParameter(request, _val018)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val19) + jjTools.getParameter(request, _val019)).length() <= 500) {
                map.put(_val19, jjTools.getParameter(request, _val19) + jjTools.getParameter(request, _val019));
            } else {
                map.put(_val19, (jjTools.getParameter(request, _val19) + jjTools.getParameter(request, _val019)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val20) + jjTools.getParameter(request, _val020)).length() <= 500) {
                map.put(_val20, jjTools.getParameter(request, _val20) + jjTools.getParameter(request, _val020));
            } else {
                map.put(_val20, (jjTools.getParameter(request, _val20) + jjTools.getParameter(request, _val020)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val21) + jjTools.getParameter(request, _val021)).length() <= 500) {
                map.put(_val21, jjTools.getParameter(request, _val21) + jjTools.getParameter(request, _val021));
            } else {
                map.put(_val21, (jjTools.getParameter(request, _val21) + jjTools.getParameter(request, _val021)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val22) + jjTools.getParameter(request, _val022)).length() <= 500) {
                map.put(_val22, jjTools.getParameter(request, _val22) + jjTools.getParameter(request, _val022));
            } else {
                map.put(_val22, (jjTools.getParameter(request, _val22) + jjTools.getParameter(request, _val022)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val23) + jjTools.getParameter(request, _val023)).length() <= 500) {
                map.put(_val23, jjTools.getParameter(request, _val23) + jjTools.getParameter(request, _val023));
            } else {
                map.put(_val23, (jjTools.getParameter(request, _val23) + jjTools.getParameter(request, _val023)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val24) + jjTools.getParameter(request, _val024)).length() <= 500) {
                map.put(_val24, jjTools.getParameter(request, _val24) + jjTools.getParameter(request, _val024));
            } else {
                map.put(_val24, (jjTools.getParameter(request, _val24) + jjTools.getParameter(request, _val024)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val25) + jjTools.getParameter(request, _val025)).length() <= 500) {
                map.put(_val25, jjTools.getParameter(request, _val25) + jjTools.getParameter(request, _val025));
            } else {
                map.put(_val25, (jjTools.getParameter(request, _val25) + jjTools.getParameter(request, _val025)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val26) + jjTools.getParameter(request, _val026)).length() <= 500) {
                map.put(_val26, jjTools.getParameter(request, _val26) + jjTools.getParameter(request, _val026));
            } else {
                map.put(_val26, (jjTools.getParameter(request, _val26) + jjTools.getParameter(request, _val026)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val27) + jjTools.getParameter(request, _val027)).length() <= 500) {
                map.put(_val27, jjTools.getParameter(request, _val27) + jjTools.getParameter(request, _val027));
            } else {
                map.put(_val27, (jjTools.getParameter(request, _val27) + jjTools.getParameter(request, _val027)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val28) + jjTools.getParameter(request, _val028)).length() <= 500) {
                map.put(_val28, jjTools.getParameter(request, _val28) + jjTools.getParameter(request, _val028));
            } else {
                map.put(_val28, (jjTools.getParameter(request, _val28) + jjTools.getParameter(request, _val028)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val29) + jjTools.getParameter(request, _val029)).length() <= 500) {
                map.put(_val29, jjTools.getParameter(request, _val29) + jjTools.getParameter(request, _val029));
            } else {
                map.put(_val29, (jjTools.getParameter(request, _val29) + jjTools.getParameter(request, _val029)).substring(0, 499));
            }

            if ((jjTools.getParameter(request, _val30) + jjTools.getParameter(request, _val030)).length() <= 500) {
                map.put(_val30, jjTools.getParameter(request, _val30) + jjTools.getParameter(request, _val030));
            } else {
                map.put(_val30, (jjTools.getParameter(request, _val30) + jjTools.getParameter(request, _val030)).substring(0, 499));
            }
            map.put(_val31, jjTools.getParameter(request, _val31) + jjTools.getParameter(request, _val031));
            map.put(_val32, jjTools.getParameter(request, _val32) + jjTools.getParameter(request, _val032));

            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_file1, jjTools.getParameter(request, _file1));
            map.put(_file2, jjTools.getParameter(request, _file2));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "insert Fail;";
                }
                return Js.dialog(errorMessage);
            }

            String a = "عملیات ارسال فرم استخدام به درستی صورت پذیرفت";
            a += ".";
            a += "<br/>";
            a += "جهت مصاحبه با شما تماس گرفته خواهد شد.";
            return Js.dialog(a);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setValDate("#" + _title, row.get(0).get(_title)));
            html.append(Js.setValDate("#" + _send_date, row.get(0).get(_send_date)));
            String array[] = new String[2];

            for (int i = 1; i < 33; i++) {
                array[0] = array[1] = "";
                if (!row.get(0).get("enrolment_val" + i).toString().equals("")) {
                    if (row.get(0).get("enrolment_val" + i).toString().contains(":")) {
                        if (row.get(0).get("enrolment_val" + i).toString().endsWith(":")) {
                            array[1] = "";
                            array[0] = row.get(0).get("enrolment_val" + i).toString();
                            System.out.println("iiiiiiiiii" + i + "=" + array[0]);
                        } else {
                            array = row.get(0).get("enrolment_val" + i).toString().split(":");
                        }

                        if (!array[0].equals("")) {
                            html.append(Js.setVal("#" + "enrolment_val" + String.valueOf(i), array[0]));
                        } else {
                            html.append(Js.hide("#enrolment_val" + String.valueOf(i)));
                        }
                        if (!array[1].equals("")) {
                            html.append(Js.setVal("#" + "enrolment_val0" + String.valueOf(i), array[1]));
                            html.append(Js.show("#enrolment_val0" + String.valueOf(i)));//====== BY RASHIDI ======
                        } else {
                            html.append(Js.hide("#enrolment_val0" + String.valueOf(i)));
                        }
                    } else {
                        array[0] = row.get(0).get("enrolment_val" + i).toString();
                        html.append(Js.setVal("#" + "enrolment_val" + String.valueOf(i), ""));
                        html.append(Js.setVal("#" + "enrolment_val0" + String.valueOf(i), array[0]));
                        html.append(Js.show("#" + "enrolment_val0" + String.valueOf(i)));//====== BY RASHIDI ======
                    }
                } else {

                    html.append(Js.hide("#enrolment_val" + String.valueOf(i)));

                    html.append(Js.hide("#enrolment_val0" + String.valueOf(i)));

                }

            }

            for (int i = 1; i < 5; i++) {
                if (!row.get(0).get("enrolment_pic" + i).toString().equals("")) {
                    html.append(Js.setAttr("#enrolment_pic" + String.valueOf(i).toString(), "src", "upload/" + row.get(0).get("enrolment_pic" + String.valueOf(i)).toString()));
                } else {
                    html.append(Js.hide("#enrolment_pic" + String.valueOf(i)));
                }
            }
            html.append(Js.setHtml("#enrolment_file1", (row.get(0).get(_file1).toString().equals("") ? ""
                    : "<a href='upload/" + row.get(0).get(_file1).toString() + "'>"
                    + row.get(0).get(_file1).toString() + "</a>")));
            html.append(Js.setHtml("#enrolment_file2", (row.get(0).get(_file2).toString().equals("") ? ""
                    : "<a href='upload/" + row.get(0).get(_file2).toString() + "'>"
                    + row.get(0).get(_file2).toString() + "</a>")));

            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);

            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Enrolment\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Enrolment", Js.jjEnrolment.delete(id)));
            }
            return (Js.setHtml("#enrolment_btn", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
