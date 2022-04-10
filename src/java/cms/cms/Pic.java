package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;

public class Pic {

    public static String tableName = "pic";
    public static String _id = "id";
    public static String _category_id = "gallery_id";
    public static String _title = "pic_title";
    public static String _address = "pic_address";
    public static String _html1 = "pic_html1";
    public static String _html2 = "pic_html2";
    public static String _html3 = "pic_html3";
    public static String _scriptOnClickImg = "pic_scriptOnClickImg";
    public static String _url_name = "pic_pic_name";
    public static String _url_ex = "pic_pic_ex";
    public static String _parent = "pic_parent";
    public static String _lang = "pic_lang";
    public static String _discription = "pic_discription";
//    public static String _price = "pic_price";
//    public static String _comment = "pic_comment";
//    public static String _margin = "pic_margin";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
//    public static int rul_rfs = 42;
//    public static int rul_ins = 43;
//    public static int rul_edt = 44;
//    public static int rul_dlt = 45;
//    public static int rul_lng = 46;

//   public static int rul_show_pics = 101;//نمایش تب تصاویر//====== BY RASHIDI ======
//    public static int rul_show_pics _reserved= 102 --- 120;// RESERVED : 102 -- 120//نمایش تب تصاویر//====== BY RASHIDI ======
    public static int rul_rfs = 280;
    public static int rul_ins = 281;
    public static int rul_edt = 282;
    public static int rul_dlt = 283;
    public static int rul_prt = 284;
    public static int rul_rfsAll = 285;
    public static String fileUploadPath = "/../upload";

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
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            if (!Access_User.hasAccess(request, db, rul_ins)) {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت تصاویر</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت تصاویر</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "            <a  class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsPic.m_add_new();' > تصویر جدید</a>"
                        + "        </p>");
            }
            html.append("<div style='width: 100%; padding-left: -10px;'><div class='table-responsive'>");
            html.append("<table id='refreshPic' class='table table-striped table-hover dt-responsive display nowrap' cellspacing='0' style='direction: rtl;'><thead>");
            html.append("<th class='c'>کد</th>");
            html.append("<th class='r'>عنوان</th>");
            html.append("<th class='c'>نام فایل</th>");
            html.append("<th class='c'>حجم</th>");
            html.append("<th class='c'>تصویر کوچک</th>");
            html.append("<th class='c'>دسته</th>");
            html.append("<th class='c'>عملیات</th>");
            html.append("</thead><tbody>");
            String address = "upload/";
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex).toString()) + "</td>");
                html.append("<td class='c'>");
                if (row.get(i).get(_url_name).toString().equals("")) {
                } else {
                    File f = new File(fileUploadPath + "/" + (row.get(i).get(_url_name).toString().replace("%20", " ") + "." + row.get(i).get(_url_ex).toString()));
                    if (f.exists()) {
                        html.append((f.length() / 1024) + "kb");
                    } else {
//                        html.append("موجود نمی باشد");
                    }
                }
                html.append("</td>");
                html.append("<td class='c'>" + "<img style='max-height:40px' src='" + address + (row.get(i).get(_url_name).toString() + "_small." + row.get(i).get(_url_ex).toString()) + "' />" + "</td>");
                html.append("<td class='c'>/" + (row.get(i).get(_category_id).toString()) + "/</td>");
                html.append("<td style='text-align: center;color:red;font-size: 26px;cursor: pointer;' class='icon ion-ios-gear-outline' onclick='cmsPic.m_select(" + row.get(i).get(_id) + ");'></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table></div></div></div></div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swPicTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshPic", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست تصاویر");
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
                html.append(Js.setHtml("#Pic_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='" + Js.jjPic.insert() + "' id='insert_Pic'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String galleryJournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder htmlgallery = new StringBuilder();

            List<Map<String, Object>> gallery = jjDatabase.separateRow(db.Select(Pic.tableName, Pic._category_id + "=12"));
            System.out.println(">>>>>>>>UserRoles is:" + gallery.size());
            htmlgallery.append("<div class=\"article_center_content\" style=\"height: 100%\">\n" +
"            <div class=\"tarlanweb_center\">");
            htmlgallery.append("<div class='section-top-border'>");
            htmlgallery.append("<h3 style='text-align: right'> گالری عکسها</h3>");
            htmlgallery.append("<div class='row gallery-item'>");
            for (int i = 0; i < gallery.size(); i++) {
                htmlgallery.append("<div class='col-md-4'>");
                htmlgallery.append("<a href='upload/" + gallery.get(i).get(Pic._url_name) + "." + gallery.get(i).get(Pic._url_ex) + "' class='img-pop-up'>");
                htmlgallery.append("<div class='single-gallery-image' style='background: url(upload/" + gallery.get(i).get(Pic._url_name) + "." + gallery.get(i).get(Pic._url_ex) + ")'></div>");
                htmlgallery.append("</a>");
                htmlgallery.append("</div>");
            }
            htmlgallery.append("</div>");
            htmlgallery.append("</div>");
            htmlgallery.append("</div>");
            htmlgallery.append("</div>"); 
            Server.outPrinter(request, response, Js.setHtml("#sw", htmlgallery.toString()));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String honorsJournal(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder htmlHonors = new StringBuilder();

            List<Map<String, Object>> honors = jjDatabase.separateRow(db.Select(Pic.tableName, Pic._category_id + "=13"));
                   htmlHonors.append("<div class=\"article_center_content\" style=\"height: 100%\">\n" +
"            <div class=\"tarlanweb_center\">");
            htmlHonors.append("<div class='section-top-border'>");
            htmlHonors.append("<h3 style='text-align: right'> افتخارات</h3>");
            htmlHonors.append("<div class='row gallery-item'>");
            for (int i = 0; i < honors.size(); i++) {
                htmlHonors.append("<div class='col-md-4'>");
                htmlHonors.append("<a href='upload/" + honors.get(i).get(Pic._url_name) + "." + honors.get(i).get(Pic._url_ex) + "' class='img-pop-up'>");
                htmlHonors.append("<div class='single-gallery-image' style='background: url(upload/" + honors.get(i).get(Pic._url_name) + "." + honors.get(i).get(Pic._url_ex) + ")'></div>");
                htmlHonors.append("</a>");
                htmlHonors.append("</div>");
            }
            htmlHonors.append("</div>");
            htmlHonors.append("</div>");
            htmlHonors.append("</div>");
            htmlHonors.append("</div>"); 
            Server.outPrinter(request, response, Js.setHtml("#sw", htmlHonors.toString()));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String textNews(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder htmlTextNews = new StringBuilder();

            String idNews_all = request.getParameter("idNews");
            System.out.println(">>>>>>>>UserRoles is:" + idNews_all);

                List<Map<String, Object>> rowNew = jjDatabase.separateRow(db.Select(News.tableName));

                for (int i = 0; i < rowNew.size(); i++) {
                    System.out.println(">>>>>>>>UserRoles is:" + rowNew.size());
                    if (rowNew.get(i).get(News._id).equals(idNews_all)) {
                        htmlTextNews.append("<div class='right_article_box box_shadows'>");
                        htmlTextNews.append("<div class='singles_txt_box'>");
                        htmlTextNews.append("<div id='attachment_23677' style='width: 714px' class='wp-caption aligncenter'>");
                        htmlTextNews.append("<a href=''><img aria-describedby='caption-attachment-23677' loading='lazy' alt='' src='upload/" + rowNew.get(i).get(News._pic) + "' sizes='(max-width: 704px) 100vw, 704px'  width='704' height='346'></a>");
                        htmlTextNews.append(" <p id='caption-attachment-23677' class='wp-caption-text'>" + rowNew.get(i).get(News._content) + "</p>");
                        htmlTextNews.append("</div></div></div>");
                    } else {
                        htmlTextNews.append("<div class='left_article_box' onclick='textNews(`"+rowNew.get(i).get(News._id)+"`)'>");
                        htmlTextNews.append("<a class='top_side_tt' > <span> " + rowNew.get(i).get(News._content) + "</span> </a>");
                        htmlTextNews.append("</div>");
                    }

                }
            Server.outPrinter(request, response, Js.setHtml("#sw", htmlTextNews.toString()));
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

            map.put(_category_id, jjNumber.isDigit(jjTools.getParameter(request, _category_id)) ? Integer.parseInt(jjTools.getParameter(request, _category_id)) : 0);
            map.put(_title, jjTools.getParameter(request, _title));
            String picName = jjTools.getParameter(request, _url_name);
            String extension = "";
            int dot = picName.lastIndexOf(".");
            if (dot > 0) {
                map.put(_url_name, picName.substring(0, dot));
                extension = picName.substring(dot + 1, picName.length());
                map.put(_url_ex, extension);
            }
            if (extension.toLowerCase().equals("jpg") || extension.toLowerCase().equals("png") || extension.toLowerCase().equals("gif")) {
            } else {
                return Js.dialog("نوع فایل تصویر باید jpg، png و یا gif باشد.");
            }
            map.put(_lang, 1);
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_html1, jjTools.getParameter(request, _html1).trim());
            map.put(_html2, jjTools.getParameter(request, _html2).trim());
            map.put(_html3, jjTools.getParameter(request, _html3).trim());
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_scriptOnClickImg, jjTools.getParameter(request, _scriptOnClickImg));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                return Js.dialog(errorMessage);
            }
            WritXmlConfigSliderFlash(request, db, isPost);
            Server.outPrinter(request, response, Js.jjPic.refresh());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "دسترسی به این قسمت را ندارید");
                return "";
            }
            List<Map<String, Object>> lastRow = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            Map<String, Object> map = new HashMap<String, Object>();
            String statement2 = "";
            map.put(_category_id, jjTools.getParameter(request, _category_id));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_discription, jjTools.getParameter(request, _discription));
            map.put(_html1, jjTools.getParameter(request, _html1).trim());
            map.put(_html2, jjTools.getParameter(request, _html2).trim());
            map.put(_html3, jjTools.getParameter(request, _html3).trim());
            if (jjTools.getParameter(request, _address).equals("")) {
            } else {
                map.put(_address, jjTools.getParameter(request, _address));
            }
            map.put(_scriptOnClickImg, jjTools.getParameter(request, _scriptOnClickImg));
            String picName = jjTools.getParameter(request, _url_name);
            int dot = picName.lastIndexOf(".");
            if (dot > -1) {
                map.put(_url_name, picName.substring(0, dot));
                map.put(_url_ex, picName.substring(dot + 1, picName.length()));
            }
            map.put(_lang, 1);
            map.put(_parent, 0);
            if (lastRow.size() > 0) {
                String last = lastRow.get(0).get(_url_name) + "." + lastRow.get(0).get(_url_ex);
                String newPic = map.get(_url_name) + "." + map.get(_url_ex);
                if (!last.equals(newPic)) {
                    // File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                    File pic = new File(fileUploadPath + "/" + lastRow.get(0).get(_url_name).toString().replace("%20", " ") + "." + lastRow.get(0).get(_url_ex));
                    if (pic.exists()) {
                        pic.delete();
                    }
                    File picSmall = new File(fileUploadPath + "/" + lastRow.get(0).get(_url_name).toString().replace("%20", " ") + "_small." + lastRow.get(0).get(_url_ex));
                    if (picSmall.exists()) {
                        picSmall.delete();
                    }
                }
            }

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            WritXmlConfigSliderFlash(request, db, isPost);
            Server.outPrinter(request, response, Js.jjPic.refresh() + statement2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String changeAllPrice(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String int1 = jjTools.getParameter(request, "int1");
            String int2 = jjTools.getParameter(request, "int2");

            if (jjNumber.isDigit(int1)) {
                if (jjNumber.isDigit(int2)) {
                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put(_price, Integer.parseInt(int2));
//                    db.update(tableName, map, _price + "=" + int1);
                }

            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String WritXmlConfigSliderFlash(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        ServerLog.Print("WritXmlConfigSliderFlash");
        if (jjTools.getParameter(request, _category_id).equals("1")) {
            String address = request.getServletContext().getRealPath("/js/prettyPhoto");
            String address2 = request.getServletContext().getRealPath("/");
            File file = new File(address + "/sample.xml");
            File file_fa = new File(address2 + "/config.xml");
            File file_en = new File(address2 + "/config_en.xml");
            File file_ar = new File(address2 + "/config_ar.xml");
            String text = "";
            StringBuffer slide = new StringBuffer();
            StringBuffer slide_en = new StringBuffer();
            StringBuffer slide_ar = new StringBuffer();
            if (file.exists()) {
                text = jjFileTxt.read(file);
                int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
                List<Map<String, Object>> slideRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
                //=================>shiri
                //     File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
                //<=================shiri
//                BRIEF TRANSITION GUIDE
//		direction: left, right, up, down
//		slicing: horizontal, vertical
//		num - number of slices each transition consists of
//		shader - transition shading type - none, flat, phong
//		cube_color - define cube color during transition (hex value)
//		duration - time for each sliced cube transition
//		delay – time each sliced cube will wait before starting transition.
//		z_multiplier - z offset enables jo-jo effect of the cubes on z axis during transition. Higher Z numbers create a pull back effect.
                String direction[] = {"left", "right", "up", "down"};
                String shader[] = {"none", "flat", "phong"};
                int counter = 0;
                int directionCounter = -1;
                for (int i = 0; i < slideRow.size(); i++) {
                    //=================>shiri
//                    File f = new File(folderAddressUpload.getAbsolutePath()
//                            + "/" + (slideRow.get(i).get(_url_name).toString().replace("%20", " ") + "."
//                            + slideRow.get(i).get(_url_ex).toString()));

                    File f = new File(fileUploadPath
                            + "/" + (slideRow.get(i).get(_url_name).toString().replace("%20", " ") + "."
                            + slideRow.get(i).get(_url_ex).toString()));
                    //<=================shiri
                    if (f.exists()) {
                        counter += 1;
                        directionCounter += 1;
                        String s = ("<slide>\n<url>upload/" + f.getName()
                                + "</url>\n<link target='_self'>"
                                + "</link>\n</slide>\n"
                                + "<transition num='3' slicing='" + (counter % 2 == 0 ? "vertical" : "horizontal")
                                + "' direction='" + (direction[directionCounter])
                                + "' shader='" + (counter % 2 == 0 ? "flat" : "phong") + "' delay='0.08' z_multiplier='1' />\n");
                        if (slideRow.get(i).get(_lang).toString().equals("1")) {
                            slide.append(s);
                        } else if (slideRow.get(i).get(_lang).toString().equals("2")) {
                            slide_en.append(s);
                        } else {
                            slide_ar.append(s);
                        }
                        directionCounter = directionCounter > 2 ? -1 : directionCounter;
//                        slide += "\n"
//                                + "        <slide><url>upload/" + f.getName()
//                                + "</url><link target='_self'>your_page_link.htm</link><description>\n"
//                                + "                <heading>" + Server.siteName
//                                + "!</heading><paragraph>" + slideRow.get(i).get(_title)
//                                + "</paragraph></description></slide>\n"
//                                + "        <transition num='" + slideRow.size() + "' slicing='vertical' direction='up' shader='flat' delay='0.08' z_multiplier='1' />";
                    }
                }

            }
            jjFileTxt.write(file_fa, text.replace("jjSlide", slide.toString()));
            jjFileTxt.write(file_en, text.replace("jjSlide", slide_en.toString()));
            jjFileTxt.write(file_ar, text.replace("jjSlide", slide_ar.toString()));
        }
        return "";
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

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() > 0) {

                // File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                //    File pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_url_name).toString().replace("%20", " ") + "." + row.get(0).get(_url_ex));
                File pic = new File(fileUploadPath + "/" + row.get(0).get(_url_name).toString().replace("%20", " ") + "." + row.get(0).get(_url_ex));
                if (pic.exists()) {
                    pic.delete();
                }
                File picSmall = new File(fileUploadPath + "/" + row.get(0).get(_url_name).toString().replace("%20", " ") + "_small." + row.get(0).get(_url_ex));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
            }
            if (!db.delete(tableName, _id + "=" + id)) {//============ EDITED BY RASHIDI ========
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            Server.outPrinter(request, response, Js.jjPic.refresh());
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
            html.append(Js.setValSelectOption("#" + tableName + "_" + _category_id, row.get(0).get(_category_id).toString()));
            html.append(Js.select2("#" + tableName + "_" + _category_id, ""));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#Pic", (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _discription, row.get(0).get(_discription)));
            html.append(Js.setVal("#" + _html1, row.get(0).get(_html1)));
            html.append(Js.setVal("#" + _html2, row.get(0).get(_html2)));
            html.append(Js.setVal("#" + _html3, row.get(0).get(_html3)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _scriptOnClickImg, row.get(0).get(_scriptOnClickImg)));
            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjPic.edit() + "' id='edit_Pic'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjPic.delete(id) + "' id='delete_Pic'>" + lbl_delete + "</button></div>";
            }
            html.append(Js.setHtml("#Pic_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
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

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
            html.append(Js.setVal("#" + _lang, "2"));

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Pic_button", "<input type=\"button\" id=\"insert_Pic_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_Pic_new", Js.jjPic.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
            html.append(Js.setVal("#" + _lang, "3"));

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Pic_button", "<input type='button' id='insert_Pic_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_Pic_new_ar", Js.jjPic.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//============ BY RASHIDI ========>
////// ------------- add_lang() ------------->

    public static String add_lang(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String lang = jjTools.getParameter(request, "myLang");
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
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
            html.append(Js.setVal("#" + _lang, lang));
            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Pic_button", "<input type='button' id='insert_pic_new_lang' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_pic_new_lang", Js.jjPic.insert()));
                html.append(Js.hide("#Pic_Language_button"));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//<============ BY RASHIDI ========
////// <------------- add_lang() -------------

    public static String sw_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = jjTools.getParameter(request, "text").trim();
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            if (jjNumber.isDigit(text)) {
                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            }
            if (row.size() > 0) {
                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
                if (picRow.size() < 1) {
                    String errorMessage = "تصویری در این گالری وجود ندارد";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "No Exist any pictuare in this gallery ;";
                    }
                    return Js.setHtml("#" + panel, errorMessage);
                }
                StringBuffer content = new StringBuffer();
                int counter = 0;
                int counter2 = 0;
                content.append("<table  width='100%'>");
                for (int i = 0; i < picRow.size(); i++) {
                    counter++;
                    counter2++;
                    if (counter == 1) {
                        content.append("<tr style='vertical-align: top' >");
                    } else if (counter == 2) {
                        counter = 0;
                    }

                    content.append("<td>");
                    content.append("<table ><tr><td><img src='upload/"
                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
                            + "' width='300'></td></tr><tr><td class='c'>"
                            + picRow.get(i).get(_title)
                            + "</td></tr></table>");
                    content.append("</td>");

                    if (counter2 == 2) {
                        content.append("</tr>");
                        counter2 = 0;
                    }
                }
                if (picRow.size() % 2 != 0) {
                    content.append("<td>&nbsp;</td></tr>");
                }
                content.append("</table>");
                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
            } else {
                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail;";
                }
                return Js.setHtml("#" + panel, errorMessage);
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (jjTools.isLangEn(request)) {
                return sw_En(request, db, isPost);
            }
            String text = jjTools.getParameter(request, "text").trim();
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            if (jjNumber.isDigit(text)) {
                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + "='" + text + "'"));
            }
            if (row.size() > 0) {
                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
                if (picRow.size() < 1) {
                    String errorMessage = "تصویری در این گالری وجود ندارد";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "No Exist any pictuare in this gallery ;";
                    }
                    return Js.setHtml("#" + panel, errorMessage);
                }
                StringBuffer content = new StringBuffer();
                int counter = 0;
                int counter2 = 0;
                content.append("<table  width='100%'>");
                for (int i = 0; i < picRow.size(); i++) {
                    counter++;
                    counter2++;
                    if (counter == 1) {
                        content.append("<tr style='vertical-align: top' >");
                    } else if (counter == 2) {
                        counter = 0;
                    }

                    content.append("<td>");
                    content.append("<table ><tr><td><img src='upload/"
                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
                            + "' width='300'></td></tr><tr><td class='c'>"
                            + picRow.get(i).get(_title)
                            + "</td></tr></table>");
                    content.append("</td>");

                    if (counter2 == 2) {
                        content.append("</tr>");
                        counter2 = 0;
                    }
                }
                if (picRow.size() % 2 != 0) {
                    content.append("<td>&nbsp;</td></tr>");
                }
                content.append("</table>");
                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
            } else {
                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail;";
                }
                return Js.setHtml("#" + panel, errorMessage);
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + (jjTools.isLangFa(request) ? "1" : "2")));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<a href='#'><img src='upload/" + allNews.get(i).get(_url_name) + "." + allNews.get(i).get(_url_ex)
                        + "' width='" + width + "px' style='width:" + width + "px;' alt='" + allNews.get(i).get(_title) + "' /></a>");
            }
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, width, height, delay);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getNewsSliderApp(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
            html.append("<div class='carousel'>");
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<div class='carousel-cell'><a href='#' ><img src='upload/" + allNews.get(i).get(_url_name) + "." + allNews.get(i).get(_url_ex) + "' height='150' width='100%' alt='" + allNews.get(i).get(_title) + "' style='border-radius:10px;' /></a></div>");
            }
            html.append("</div>");
            html.append("<div class='carousel-progress'>");
            html.append("<div class='progress'></div>");
            html.append("</div>");
            if (panel.equals("")) {
                panel = "slick-1";
            }
            String html2 = Js.setHtml("." + panel, html.toString());
            String script = "var duration = 4;\n"
                    + "var interval = 10;\n"
                    + "var slider = document.querySelector('.carousel');\n"
                    + "var sliderWrapper = document.querySelector('.carousel-wrapper');\n"
                    + "var progressBar = document.querySelector('.progress');\n"
                    + "\n"
                    + "var flkty = new Flickity( slider, {\n"
                    + "  cellSelector: '.carousel-cell',\n"
                    + "  wrapAround: true,\n"
                    + "  prevNextButtons: false,\n"
                    + "});\n"
                    + "\n"
                    + "// Pause control\n"
                    + "var isPaused = false;\n"
                    + "\n"
                    + "sliderWrapper.addEventListener('mouseenter', function() {\n"
                    + "  isPaused = true;\n"
                    + "});\n"
                    + "\n"
                    + "sliderWrapper.addEventListener('mouseleave', function() {\n"
                    + "  isPaused = false;\n"
                    + "});\n"
                    + "\n"
                    + "// Main function\n"
                    + "\n"
                    + "var percentTime,\n"
                    + "    step,\n"
                    + "    tick;\n"
                    + "\n"
                    + "function startProgressbar() {\n"
                    + "  resetProgressbar();\n"
                    + "  percentTime = 0;\n"
                    + "  isPaused = false;\n"
                    + "  tick = window.setInterval(increase, interval);\n"
                    + "};\n"
                    + "\n"
                    + "function increase() {\n"
                    + "  if (!isPaused) {\n"
                    + "    step = (duration * 1000) / interval;\n"
                    + "    percentTime += 100 / step;\n"
                    + "    progressBar.style.width = percentTime + \"%\";\n"
                    + "    if (percentTime >= 100) {\n"
                    + "      flkty.next();\n"
                    + "      startProgressbar();\n"
                    + "    }\n"
                    + "  }\n"
                    + "}\n"
                    + "\n"
                    + "function resetProgressbar() {\n"
                    + "  progressBar.style.width = 0 + '%';\n"
                    + "  clearTimeout(tick);\n"
                    + "}\n"
                    + "startProgressbar();";
            Server.outPrinter(request, response, html2 + script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getSlipprySliderRunScript(String divId, String width, String height, String delay) {
        try {
//        speed = speed == "" ? "200" : speed;
            return "var demo=$('#" + divId + "').slippry({"
                    // transition: 'fade',
                    // useCSS: true,
                    // speed: 1000,
                    // pause: 3000,
                    // auto: true,
                    // preload: 'visible',
                    // autoHover: false
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript(String divId, String width, String height, String delay) {
        try {
//        speed = speed == "" ? "200" : speed;
            return "$('#" + divId + "').coinslider({"
                    + "width:" + width + ","
                    + "height:" + height + ","
                    + "delay:" + delay + ","
                    + "opacity:1,"
                    + "spw:7,"
                    + "sph:5,"
                    + "sDelay:30,"
                    + "titleSpeed:1500,"
                    + "effect:'',"
                    + "navigation:true,"
                    + "links:true,"
                    + "hoverPause:true"
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getPicSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "650";
            height = jjNumber.isDigit(height) ? height : "228";
            int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + lang));
            for (int i = 0; i < allPic.size(); i++) {
                File f = new File(fileUploadPath + "/" + (allPic.get(i).get(_url_name).toString().replace("%20", " ") + "." + allPic.get(i).get(_url_ex).toString()));
                if (f.exists()) {
                    html.append("<a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "'/></a>");
                }
            }
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, width, height, delay);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getPicSlipprySlider(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String sliderId = "demo1";
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "650";
            height = jjNumber.isDigit(height) ? height : "228";
            List<Map<String, Object>> category = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='اسلایدر'"));
            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + category.get(0).get(Category_Gallery._id)));
            html.append("<ul id='" + sliderId + "'>");
            for (int i = 0; i < allPic.size(); i++) {
                html.append("<li><a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "' " + "alt='"
                        + allPic.get(i).get(_title) + "'"
                        + " /></a></li>");
            }
            html.append("</ul>");
            if (panel.equals("")) {
                panel = "jjSliderPicResponsive";
            }
//            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += getSlipprySliderRunScript(sliderId, width, height, delay);
//            Server.outPrinter(request, response, html.toString());
            return html.toString();
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getGallery(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            html3.append("<div class=\"ht__bradcaump__area\">\n"
                    + "            <div class=\"ht__bradcaump__container\">\n"
                    + "              \n"
                    + "                <div class=\"container\">\n"
                    + "                    <div class=\"row\">\n"
                    + "                        <div class=\"col-lg-12\">\n"
                    + "                            <div class=\"bradcaump__inner text-center\">\n"
                    + "                                <h2 class=\"bradcaump-title\">گالری</h2>\n"
                    + "                                <nav class=\"bradcaump-inner\">\n"
                    + "                                  <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a>\n"
                    + "                                  <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span>\n"
                    + "                                  <span class=\"breadcrumb-item active\">گالری</span>\n"
                    + "                                </nav>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>");
            html3.append("<div class='junior__gallery__area gallery-page-one gallery__masonry__activation gallery--3 bg-image--25 section-padding--lg'>\n");
            html3.append("<div class=\"container\">\n");
            html3.append("<div class=\"row\">\n");
            html3.append("<div class=\"col-sm-12\">\n");
            html3.append("<div class=\"gallery__menu\">\n");
            html3.append("<a onclick='swGetGallery(" + 0 + ");'>" + "همه عکس ها" + "</a>");

            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + " >" + 4));//============ EDITED BY RASHIDI ========
            //============ BY RASHIDI ========>
            for (int i = 0; i < categoryRow.size(); i++) {
                html3.append("<a onclick='swGetGallery("
                        + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
            }
            html3.append("</div >");
            html3.append("</div >");
            html3.append("</div >");

            html.append(html3.toString());
            //پیدا کردن دسته گالری انتخاب شده 
            int selected = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (!jjNumber.isDigit(id)) {
                    //اگر عنوان گروه وارد شده بوود و عددی به عنوان آی دی گروه تصاویر نبود
                    if (categoryRow.get(i).get(Category_Gallery._title).toString().equals(id)) {
                        selected = i;
                    }
                } else {
                    if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
                        selected = i;
                    }
                }
            }
            //============ BY RASHIDI ========>
//  if (selected != 0) {
//    html3.append("<button data-filter=\"*\" class=\"is-checked\">" + categoryRow.get(selected).get(Category_Gallery._title) + "</button>");
//  }
            List<Map<String, Object>> row;
            if (jjTools.getParameter(request, "id").toString().equals("0")) {
//  if (id.equals("0")) {
//    row = jjDatabase.separateRow(db.SelectAllDESCOrder(tableName, _category_id + ">=" +4, _id));//============ EDITED BY RASHIDI ========
                row = jjDatabase.separateRow(db.Select(tableName, _category_id + ">=" + 4));//============ EDITED BY RASHIDI ========
            } else {
                row = jjDatabase.separateRow(db.SelectAllDESCOrder(tableName, _category_id + "=" + id, _id));//============ EDITED BY RASHIDI ========
            }
            html.append("<div class=\"row galler__wrap masonry__wrap mt--80\">\n");
            if (!row.isEmpty()) {
                for (int i = 0; i < row.size(); i++) {
                    html.append("<div class=\"col-lg-3 col-md-4 col-sm-6 col-12 gallery__item cat--2\">\n");
                    html.append("<div class=\"gallery\">\n");

                    html.append("<div  class=\"imgDivProductCss\">\n");
                    html.append("<a class='fancybox' data-fancybox-group='gallery'  href='upload/" + row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex) + "'>");// 
//                    html.append("<img  class='fancybox' src='upload/" + row.get(i).get(_url_name).toString() +"_small." + row.get(i).get(_url_ex) + "' alt='" + row.get(i).get(_title).toString() + "'/>\n");
                    html.append("<img   src='upload/" + row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex) + "' alt='" + row.get(i).get(_title).toString() + "'/>\n");
                    html.append("</a>\n");
                    html.append("</div>\n");
                    html.append("<div class=\"gallery__hover__inner\">\n");
                    html.append("<div class=\"gallery__hover__action\">\n");
                    html.append("<h4 class=\"gallery__title\"><a>" + row.get(i).get(_title).toString() + "</a></h4>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                }
            } else {
                html.append("<div class=\"ht__bradcaump__area\">\n"
                        + "            <div class=\"ht__bradcaump__container\">\n"
                        + "                            <div class=\"container\">\n"
                        + "                    <div class=\"row\">\n"
                        + "                        <div class=\"col-lg-12\">\n"
                        + "                            <div class=\"bradcaump__inner text-center\">\n"
                        + "                                <h2 class=\"bradcaump-title\">رکوردی موجود نیست</h2>                               \n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>");
            }
            html.append("</div>\n");
            html.append("</div>\n");
            html.append("</div>\n");
            html3.append("</div >\n");

            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getGalleryHome(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            html3.append("<div class=\"ht__bradcaump__area\">\n"
                    + "            <div class=\"ht__bradcaump__container\">\n"
                    + "              \n"
                    + "                <div class=\"container\">\n"
                    + "                    <div class=\"row\">\n"
                    + "                        <div class=\"col-lg-12\">\n"
                    + "                            <div class=\"bradcaump__inner text-center\">\n"
                    + "                                <h2 class=\"bradcaump-title\">گالری</h2>\n"
                    + "                                <nav class=\"bradcaump-inner\">\n"
                    + "                                  <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a>\n"
                    + "                                  <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span>\n"
                    + "                                  <span class=\"breadcrumb-item active\">گالری</span>\n"
                    + "                                </nav>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>");
            html3.append("<div class='junior__gallery__area gallery-page-one gallery__masonry__activation gallery--3 bg-image--25 section-padding--lg'>\n");
            html3.append("<div class=\"container\">\n");
            html3.append("<div class=\"row\">\n");
            html3.append("<div class=\"col-sm-12\">\n");
            html3.append("<div class=\"gallery__menu\">\n");
            html3.append("<a onclick='swGetGallery(" + 0 + ");'>" + "همه عکس ها" + "</a>");

            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + " >" + 4));//============ EDITED BY RASHIDI ========
            //============ BY RASHIDI ========>
            for (int i = 0; i < categoryRow.size(); i++) {
                html3.append("<a onclick='swGetGallery("
                        + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
            }
            html3.append("</div >");
            html3.append("</div >");
            html3.append("</div >");

            html.append(html3.toString());
            //پیدا کردن دسته گالری انتخاب شده 
            int selected = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (!jjNumber.isDigit(id)) {
                    //اگر عنوان گروه وارد شده بوود و عددی به عنوان آی دی گروه تصاویر نبود
                    if (categoryRow.get(i).get(Category_Gallery._title).toString().equals(id)) {
                        selected = i;
                    }
                } else {
                    if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
                        selected = i;
                    }
                }
            }
            //============ BY RASHIDI ========>
//  if (selected != 0) {
//    html3.append("<button data-filter=\"*\" class=\"is-checked\">" + categoryRow.get(selected).get(Category_Gallery._title) + "</button>");
//  }
            List<Map<String, Object>> row;
            if (jjTools.getParameter(request, "id").toString().equals("0")) {
//  if (id.equals("0")) {
//    row = jjDatabase.separateRow(db.SelectAllDESCOrder(tableName, _category_id + ">=" +4, _id));//============ EDITED BY RASHIDI ========
                row = jjDatabase.separateRow(db.Select(tableName, _category_id + ">=" + 4));//============ EDITED BY RASHIDI ========
            } else {
                row = jjDatabase.separateRow(db.SelectAllDESCOrder(tableName, _category_id + "=" + id, _id));//============ EDITED BY RASHIDI ========
            }
            html.append("<div class=\"row galler__wrap masonry__wrap mt--80\">\n");
            if (!row.isEmpty()) {
                for (int i = 0; i < row.size(); i++) {
                    html.append("<div class=\"col-lg-3 col-md-4 col-sm-6 col-12 gallery__item cat--2\">\n");
                    html.append("<div class=\"gallery\">\n");

                    html.append("<div  class=\"imgDivProductCss\">\n");
                    html.append("<a class='fancybox' data-fancybox-group='gallery'  href='upload/" + row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex) + "'>");// 
//                    html.append("<img  class='fancybox' src='upload/" + row.get(i).get(_url_name).toString() +"_small." + row.get(i).get(_url_ex) + "' alt='" + row.get(i).get(_title).toString() + "'/>\n");
                    html.append("<img   src='upload/" + row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex) + "' alt='" + row.get(i).get(_title).toString() + "'/>\n");
                    html.append("</a>\n");
                    html.append("</div>\n");
                    html.append("<div class=\"gallery__hover__inner\">\n");
                    html.append("<div class=\"gallery__hover__action\">\n");
                    html.append("<h4 class=\"gallery__title\"><a>" + row.get(i).get(_title).toString() + "</a></h4>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                    html.append("</div>\n");
                }
            } else {
                html.append("<div class=\"ht__bradcaump__area\">\n"
                        + "            <div class=\"ht__bradcaump__container\">\n"
                        + "                            <div class=\"container\">\n"
                        + "                    <div class=\"row\">\n"
                        + "                        <div class=\"col-lg-12\">\n"
                        + "                            <div class=\"bradcaump__inner text-center\">\n"
                        + "                                <h2 class=\"bradcaump-title\">رکوردی موجود نیست</h2>                               \n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>");
            }
            html.append("</div>\n");
            html.append("</div>\n");
            html.append("</div>\n");
            html3.append("</div >\n");

            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getGallery_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
//            if(jjNumber.isDigit(id)){
//                List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=2" + " limit 1"));
//                if(categoryRow.isEmpty()){
//                    return Js.setHtml("#" + panel, "There is not any gallery to show");
//                }
//                id = categoryRow.get(0).get(Category_Gallery._id).toString();
//            }
            id = jjNumber.isDigit(id) ? id : "1";
            //select an category as "selected"
            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=2"));
            if (id.equals("1")) {
                if (categoryRow.isEmpty()) {
                    return Js.setHtml("#" + panel, "There is not any gallery In english language ...");
                } else {
                    id = categoryRow.get(0).get(_id).toString();
                }
            }
            //Display all categories in English languge as "ul" & "li" tags
            int selectedI = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
                    selectedI = i;
                }
            }
            html3.append("Gallery");
            html3.append("<ul class='picLink'>");
            html3.append("<li><a class='picLink' onclick='swGetGallery("
                    + categoryRow.get(selectedI).get(Category_Gallery._id) + ");' >" + categoryRow.get(selectedI).get(Category_Gallery._title) + "</a></li>");
            for (int i = 0; i < categoryRow.size(); i++) {
                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
                    if (!categoryRow.get(selectedI).get(Category_Gallery._id).toString().equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
                        html3.append("<li class='picLinkFlash'>" + "<a  class='picLink' onclick='swGetGallery("
                                + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a></li>");
                    }
                }
            }
            html3.append("</ul>");
            html.append(html3.toString());

            List<Map<String, Object>> galleriRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + "=" + id));
            if (!galleriRow.isEmpty()) {
                String parentId = galleriRow.get(0).get(Category_Gallery._parent).toString();
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + parentId + " AND " + _lang + "=2"));
                for (int i = 0; i < row.size(); i++) {
                    html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                            + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
                            + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
                            + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                            + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
                }
            }
            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getGallery_Ar(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            StringBuffer html3 = new StringBuffer();
            String id = jjTools.getParameter(request, "id");
            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._parent + "=" + id + " AND " + Category_Gallery._lang + "=3"));
            html3.append("<span class='picLink'>"
                    + "آلبوم"
                    + "</span><span class='picLinkFlash'>&nbsp;>&nbsp;</span>");
            String idd = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._id).toString();
            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._title).toString();
            html3.append("<a class='picLink'>" + title + "</a>");

            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=3"));
            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
            int counter2 = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
                    if (!idd.equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
                        counter2 += 1;
                        html3.append((counter2 == 1 ? "<span class='picLinkFlash'> أو </span>" : "<span class='picLinkFlash'>, </span>")
                                + "<a  class='picLink' onclick='swGetGallery("
                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
                    }
                }
            }

            html3.append("<span class='picLinkFlash'>)</span>");
            if (counter2 > 0) {
                html.append(html3.toString());
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=3"));

            for (int i = 0; i < row.size(); i++) {
                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
                    + "showGallery();\n";
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///////slidermstid
    public static String getsidersite(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            System.out.println("slider::::::::::::mstid");
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("%", "100");
            String height = jjTools.getParameter(request, "height").replace("px", "100");
            width = jjNumber.isDigit(width) ? width : "200";
            List<Map<String, Object>> rowPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
            int i;
            for (i = 0; i <= rowPic.size(); i++) {
                html.append("<input name='cs_anchor1' id='cs_slide1_" + i + "' type='radio' class='cs_anchor slide'>\n");
            }
            html.append("<input name='cs_anchor1' id='cs_play1' type='radio' class='cs_anchor' checked=''>");
            html.append("<ul>\n");
            html.append("<div style=\"width: 100%; visibility: hidden; font-size: 0px; line-height: 0;\">\n"
                    + "                                            <img src='upload/" + rowPic.get(0).get(_url_name) + "." + rowPic.get(0).get(_url_ex) + "' style=\"width: 100%;\">\n"
                    + "                                        </div>");
            for (i = 0; i < rowPic.size(); i++) {
                html.append("<li class='num" + i + " img'>\n"
                        + "<img src='upload/" + rowPic.get(i).get(_url_name) + "." + rowPic.get(i).get(_url_ex) + "' title=\"Hyundai i20\">\n"
                        + "</li>\n");
            }
            html.append("</ul>\n");
            html.append("<div class=\"cs_bullets\">\n");
            for (i = 0; i < rowPic.size(); i++) {
                html.append(""
                        + "                                        <label class='num" + i + "' for=\"cs_slide1_0\">\n"
                        + "                                            <span class=\"cs_point\"></span>                                           \n"
                        + "                                        </label>\n"
                );
            }
            html.append("</div>");

//               String panel = jjTools.getParameter(request, "panel");
            return html.toString();
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
        }

        return null;
    }

    public static String getsidersite3(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");

            List<Map<String, Object>> rowPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=10"));
            for (int i = 0; i < rowPic.size(); i++) {
                html.append(" <div>");
                html.append("<div class='card'>");
                html.append("<div class='card-header'>");
                html.append(" <img src='upload/" + rowPic.get(i).get(_url_name) + "." + rowPic.get(i).get(_url_ex) + "' class='' alt=''>");
                html.append(" </div>");
                html.append("    <div class='card-body'>\n"
                        + "                            <div class='card-content'>\n"
                        + "                                <div class='card-title' style='text-align: right;' ></div>\n"
                        + "                                <div class='card-text'>\n"
                        + "                                    <p style='text-align: right;'></p>\n"
                        + "                                    <div class='social'>\n"
                        + "                                        <a href=''><i class='ri-twitter-fill'></i></a>\n"
                        + "                                        <a href=''><i class='ri-facebook-fill'></i></a>\n"
                        + "                                        <a href=''><i class='ri-instagram-fill'></i></a>\n"
                        + "                                        <a href=''> <i class='ri-linkedin-box-fill'></i> </a>\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>");
                html.append(" </div></div>");

            }
            html2.append("<script src='template1/js/cards-horizontal-slider.js' type='text/javascript'></script>");

            Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()) + Js.setHtml(".script", html2.toString()));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
        }

        return null;
    }

    public static String getsidersite4(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");

            List<Map<String, Object>> rowPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=9"));
            for (int i = 0; i < rowPic.size(); i++) {
                html.append(" <div>");
                html.append("<div class='card'>");
                html.append("<div class='card-header'>");
                html.append(" <img src='upload/" + rowPic.get(i).get(_url_name) + "." + rowPic.get(i).get(_url_ex) + "' class='' alt=''>");
                html.append(" </div>");
                html.append(" </div></div>");

            }
            html2.append("<script src='template1/js/cards-horizontal-slider.js' type='text/javascript'></script>");

            Server.outPrinter(request, response, Js.setHtml("#" + panel, html.toString()) + Js.setHtml(".script", html2.toString()));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
        }

        return null;
    }

}
