package cms.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import cms.access.Access_User;
import java.io.*;

import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.table.DefaultTableModel;
import jj.jjNumber;
import jj.jjPicture;

//import java.io.*,java.util.*, javax.servlet.*,java.sql.*
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;

//==================>shiri
//@WebServlet(name="uploadServlet3", urlPatterns = {"/uploadServlet3"},
//initParams = {
//        @WebInitParam(name = "FILE_UPLOAD_PATH", value = "/sepanoShop/upload")
//    })
//<=================shiri
public class UploadServlet extends HttpServlet {
////توسط miss shiran1

    private static long maxSize = 100000000;
    public static String tableName = "upload";
    public static String _id = "id";
    public static String _file_name = "upload_file_name";
    public static String _title = "upload_title";
    public static String _date = "upload_date";
    public static String _time = "upload_time";
    public static String _loader = "upload_loader";
    public static String _status = "upload_status";
    public static String _type = "upload_type";
    public static String _logStatus = "upload_logStatus";
    public static String status_deleted = "پاک شده توسط";
    public static int rul_rfs = 340;
    public static int rul_ins = 341;
    public static int rul_edt = 342;
    public static int rul_dlt = 343;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
//    public static String _loader_id = "upload_loader_id";

    //====================>shiri
//     private String fileUploadPath;
//    public void init(ServletConfig config) {
//        fileUploadPath = config.getInitParameter("FILE_UPLOAD_PATH");
//    }
    //<================shiri
//    private static String Save_Folder_Name = "/upload";
    private static final String Save_Folder_Name = "upload" + File.separator;
    public static final String Save_Folder_Name2 = "upload" + File.separator;

    Map<String, String> data = new HashMap<String, String>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(UploadServlet.class + ">>>>");
        jjTools.ShowAllParameter(request);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if (jjNumber.isDigit(jjTools.getParameter(request, "maxSize"))) {
            maxSize = Long.parseLong(jjTools.getParameter(request, "maxSize"));
        }

//        try {
//            Server.Connect();
//            jjDatabaseWeb db = Server.db;
//            Class.forName("com.mysql.jdbc.Driver");
//
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_hmis", "root", "m123456");
//            String INSERT_UPLOAD = "insert into upload(id, upload_file_name, upload_date,upload_pic) values (?, ?,  CURDATE(),?)";
//        } 
//        catch (SQLException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String name = request.getParameter("name");
        name = name == null ? "" : name;
        super.init(getServletConfig());
//        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
//        out.println();
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] contxtPath = request.getServletContext().getRealPath("/").split(pattern);
        String safePath = "";
//        for (int i = 0; i < contxtPath.length - 1; i++) {//return 2 folder up(parent of parent)
        for (int i = 0; i < contxtPath.length - 2; i++) {//return 2 folder up(parent of parent)
            safePath += contxtPath[i] + System.getProperty("file.separator");
        }
        String path = safePath + Save_Folder_Name;// upload\ in windows and upload/ in linux
        String result = "";
//        fileItemFactory.setSizeThreshold(1024 * 1024); //1 MB
        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    /*
                     * Field
                     */
//                    out.println("Field Name=" + item.getFieldName() + ", Value=" + item.getString());
                    data.put(item.getFieldName(), item.getString());
                } else {
                    /*
                     * File
                     */
                    //==============>shiri
                    File folderAddress = new File(path);//"/" +
                    String extension = "";
                    String nameWithoutExtension = item.getName();
                    if (item.getName().lastIndexOf(".") > -1) {
                        extension = item.getName().substring(item.getName().lastIndexOf("."));
                        nameWithoutExtension = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().lastIndexOf("."));
                    }
                    folderAddress.mkdirs();
                    nameWithoutExtension = "P";
                    File file = new File(folderAddress + "/" + nameWithoutExtension.toLowerCase() + jjNumber.getRandom(10) + extension.toLowerCase());
                    String i = "0000000000";
                    while (file.exists()) {
                        i = jjNumber.getRandom(10);
                        file = new File(folderAddress + "/" + nameWithoutExtension.toLowerCase() + i + extension.toLowerCase());
                    }
                    if (!name.equals("")) {
                        file = new File(folderAddress + "/" + name);
                    }
//                    out.println("File Name=" + item.getName()
//                            + ", Field Name=" + item.getFieldName()
//                            + ", Content type=" + item.getContentType()
//                            + ", File Size=" + item.getSize()
//                            + ", Save Address=" + file);
//                    out.println(file);
//                    String urlPath = request.getRequestURL().toString().replace("Upload2", "Upload") + "/" + file.getName().replace("\\", "/");
//                    out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body><input type='text' name='T1' size='58' value='" + urlPath + "'></body></html>");

                    data.put(item.getFieldName(), file.getAbsolutePath());
                    if (!file.getName().toLowerCase().endsWith(".exe")) {
                        item.write(file);
                    }
                    long size = file.length();
                    ServerLog.Print("?>>>>>>" + file + "   -    Size:" + size);
                    if (size > maxSize) {
                        file.delete();
                        result = "big";
                    } else {
                        result = file.getName().replace(" ", "%20");

                        ////ارتباط بادیتا بیس upload 
                        ////miss shiran1
                        Server.Connect();
                        jjDatabaseWeb db = Server.db;
                        Class.forName("com.mysql.jdbc.Driver");
//                       
                        Map<String, Object> map = new HashMap();
                        map.put(_file_name, result);
//                      
                        map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

                        map.put(_time, new jjCalendar_IR().getTimeFormat_8length());
                        map.put(_loader, (jjTools.getSessionAttribute(request, "#USER_NAME") + " " + jjTools.getSessionAttribute(request, "#USER_FAMILY")));
                        map.put(_logStatus, (jjTools.getParameter(request, _logStatus)));

                        ServerLog.Print("Write pic in: " + file + " size:" + file.length());
                        String name2 = file.getName().substring(0, file.getName().lastIndexOf("."));
                        String extension2 = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
                        File file2 = new File(file.getParent() + "/" + name2 + "_small." + extension2);
                        map.put(_title, jjTools.getParameter(request, _title));
                        map.put(_type, extension2);
                        db.insert(UploadServlet.tableName, map);
                        if (extension2.toLowerCase().equals("jpg")
                                || extension2.toLowerCase().equals("png")
                                || extension2.toLowerCase().equals("gif")
                                || extension2.toLowerCase().equals("svg")
                                || extension2.toLowerCase().equals("doc")
                                || extension2.toLowerCase().equals("docx")
                                || extension2.toLowerCase().equals("pdf")
                                || extension2.toLowerCase().equals("tiff")
                                || extension2.toLowerCase().equals("xls")
                                || extension2.toLowerCase().equals("xlsx")) {
                            jjPicture.doChangeSizeOfPic(file, file2, 250);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
        }
        System.out.println("______________________________");
        System.out.println(result);
        System.out.println("______________________________");

        out.print(result);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public Map<String, String> getData() {
        return data;
    }

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
                        + "    <div class='card-header bg-primary tx-white'>مدیریت فایل ها</div>"
                        + "    <div class='card-body pd-sm-30'>");
            } else {
                html.append(" <div class='card bd-primary mg-t-20'>"
                        + "    <div class='card-header bg-primary tx-white'>مدیریت فایل ها</div>"
                        + "    <div class='card-body pd-sm-30'>"
                        + "        <p class='mg-b-20 mg-sm-b-30'>"
                        + "  <a class='btn btn-success pd-sm-x-20 mg-sm-r-5' style='color: white;' onclick='cmsManagmentFiles.m_add_new();'>فایل جدید</a>   "
                        + "        </p>");
            }
            html.append("<table id='refreshManagmentFiles' class='table display responsive nowrap' dir='rtl'><thead>");
            html.append("<th class='c'>کد</th>");
            html.append("<th class='r'>عنوان</th>");
            html.append("<th class='c'>نام فایل</th>");
            html.append("<th class='c'>بارگذاری کننده</th>");
            html.append("<th class='c'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                String filename =row.get(i).get(_file_name).toString();
//                String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
//                if (extension.toLowerCase().equals("doc")
//                        || extension.toLowerCase().equals("txt")
//                        || extension.toLowerCase().equals("docx")
//                        || extension.toLowerCase().equals("pdf")
//                        || extension.toLowerCase().equals("tiff")
//                        || extension.toLowerCase().equals("xls")
//                        || extension.toLowerCase().equals("xlsx")) {
                    html.append("<tr  onclick='cmsManagmentFiles.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                    html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                    html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                    html.append("<td class='r'>" + (row.get(i).get(_file_name).toString()) + "</td>");
                    html.append("<td class='c'>" + (row.get(i).get(_loader).toString()) + "</td>");
                    html.append("<td style='text-align: center;color:red;font-size: 26px;' class='icon ion-ios-gear-outline'><a src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsPic.m_select(" + row.get(i).get(_id) + ");' ></a></td>");
                    html.append("</tr>");
//                }
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swManagmentFilesTbl";
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshManagmentFiles", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست تصاویر");
            Server.outPrinter(request, response, html2);
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
            html.append(Js.setVal("#titleFile", row.get(0).get(_title)));
            html.append(Js.setVal("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _type, row.get(0).get(_type)));
            html.append(Js.setVal("#" + _loader, row.get(0).get(_loader)));
            html.append(Js.setVal("#" + _logStatus, row.get(0).get(_logStatus)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setVal("#upload_file_name", row.get(0).get(_file_name)));
            html.append(Js.setAttr("#DownloadFile", "href", "upload/" + row.get(0).get(_file_name)));

            String htmlBottons = "";
            if (Access_User.hasAccess(request, db, rul_edt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_edit + "' class='btn btn-outline-warning btn-block mg-b-10' onclick='cmsManagmentFiles.m_edit();' id='edit_Upload'>" + lbl_edit + "</button></div>";
            }
            if (Access_User.hasAccess(request, db, rul_dlt)) {
                htmlBottons += "<div class='col-lg'><button title='" + lbl_delete + "' class='btn btn-outline-danger btn-block mg-b-10' onclick='cmsManagmentFiles.m_delete(" + id + ");' id='delete_Upload'>" + lbl_delete + "</button></div>";
            }
            html.append(Js.setHtml("#Upload_button", htmlBottons));
            Server.outPrinter(request, response, html.toString());
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
                return Js.dialog(errorMessageId);
            }

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            if (!db.delete(tableName, _id + "=" + id)) {//============ EDITED BY RASHIDI ========
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessage));
                return "";
            }
            StringBuilder script = new StringBuilder();
            script.append("cmsManagement.m_refresh();");
            Server.outPrinter(request, response, script);
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
            map.put(_date, jjTools.getParameter(request, _date));
//            map.put(_file_name, jjTools.getParameter(request, _file_name));
            map.put(_title, jjTools.getParameter(request, _title));
//            map.put(_logStatus, jjTools.getParameter(request, _logStatus));
//            map.put(_status, jjTools.getParameter(request, _status));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.modal("پیام سامانه", errorMessage));
                return "";
            }

            Server.outPrinter(request, response, "cmsManagmentFiles.m_refresh();");
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
                html.append(Js.setHtml("#Upload_button", "<button title='" + lbl_insert + "' class='btn btn-outline-success btn-block mg-b-10' onclick='cmsManagmentFiles.m_insert()' id='insert_Upload'>" + lbl_insert + "</button>"));
            }
            Server.outPrinter(request, response, html.toString() + "cmsManagmentFiles.m_refresh();");
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

}
