package cms.access;

import cms.tools.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class Access_Group_User {

    public static String tableName = "access_user_group";
    public static String _id = "id";
    public static String _user_id = "user_id";
    public static String _group_id = "group_id";
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;

    /**
     *
     * @param height is int height of table
     * @param sort is number of default sort column number
     * @param panel is container id
     */
    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }
        boolean accEdt = Access_User.hasAccess(request, db, rul_edt);
        boolean accDel = Access_User.hasAccess(request, db, rul_dlt);
        StringBuffer html = new StringBuffer();
        DefaultTableModel dtm = db.Select(tableName);
        List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
        html.append("<table id='refreshContent'><thead>");
        html.append("<th width='5%'>کد</th>");
        html.append("<th width='70%'>کد گروه</th>");
        if (accDel) {
            html.append("<th width='5%'>حذف</th>");
        }
        if (accEdt) {
            html.append("<th width='5%'>ویرایش</th>");
        }
        html.append("</thead><tbody>");
        for (int i = 0; i < row.size(); i++) {
            html.append("<tr>");
            html.append("<td class='tahoma10'>" + (row.get(i).get(_id).toString()) + "</td>");
            html.append("<td class='tahoma10' style='text-align: right;padding:5px'>" + (row.get(i).get(_group_id).toString()) + "</td>");
            if (accDel) {
                html.append("<td><img src='img/d.png' style='cursor: pointer;height:35px' onclick='DeleteContent(" + row.get(i).get(_id) + ")' /></td>");
            }
            if (accEdt) {
                html.append("<td><img src='img/e.png' style='cursor: pointer;height:35px' onclick='SelectContent(" + row.get(i).get(_id) + ")' /></td>");
            }
            html.append("</tr>");
        }
        html.append("</tbody></table>");
        String height = jjTools.getParameter(request, "height");
        String sort = jjTools.getParameter(request, "sort");
        String panel = jjTools.getParameter(request, "panel");
        if (!jjNumber.isDigit(height)) {
            height = "400";
        }
        if (!jjNumber.isDigit(sort)) {
            sort = "0";
        }
        if (panel.equals("")) {
            panel = "sw";
        }
        String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
        html2 += ("$('#refreshContent').dataTable({'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers', 'aaSorting': [[ " + sort + ", 'asc' ]] });\n");
        Server.outPrinter(request, response, html2);
        return "";
    }

    /**
     *
     * @param access_permission_title
     * @param access_permission_des
     */
    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        String user_id = jjTools.getParameter(request, _user_id);
        String group_id = jjTools.getParameter(request, _group_id);
        map.put(_id, jjTools.getParameter(request, _id));
        map.put(user_id, jjNumber.isDigit(user_id) ? jjTools.getParameter(request, user_id) : Integer.parseInt(jjTools.getParameter(request, user_id)));
        map.put(group_id, jjTools.getParameter(request, group_id));

        if (db.insert(tableName, map).getRowCount() == 0) {
            String errorMessage = "عملیات درج به درستی صورت نگرفت.";
            if (jjTools.getParameter(request, "myLang").equals("2")) {
                errorMessage = "Edit Fail;";
            }
            Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";
        }
        return "";
    }

    /**
     *
     * @param id
     * @param access_permission_title
     * @param access_permission_des
     */
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
        if (!hasAccess.equals("")) {
            Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
            return "";
        }

        Map<String, Object> map = new HashMap<String, Object>();

        String user_id = jjTools.getParameter(request, _user_id);
        String group_id = jjTools.getParameter(request, _group_id);
        map.put(user_id, jjNumber.isDigit(user_id) ? jjTools.getParameter(request, user_id) : Integer.parseInt(jjTools.getParameter(request, user_id)));
        map.put(group_id, jjTools.getParameter(request, group_id));

        String id = jjTools.getParameter(request, _id);
        String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
        if (!errorMessageId.equals("")) {
            if (jjTools.isLangEn(request)) {
                errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
            }
            Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
            return "";
        }
        if (!db.update(tableName, map, _id + "=" + id)) {
            String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Edit Fail;";
            }
             Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";
           
        }
        return "";
    }

    /**
     *
     * @param id
     */
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
        if (!hasAccess.equals("")) {
             Server.outPrinter(request, response, Js.modal(hasAccess, "پیام سامانه"));
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
        if (!db.delete(tableName, _id + "=" + id)) {
            String errorMessage = "عملیات حذف به درستی صورت نگرفت";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Delete Fail;";
            }
             Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";
        }
        return "";
    }

    /**
     *
     * @param id
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        String id = jjTools.getParameter(request, _id);
        String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
        if (!errorMessageId.equals("")) {
            if (jjTools.isLangEn(request)) {
                errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
            }
            Server.outPrinter(request, response, Js.modal(errorMessageId, "پیام سامانه"));
            return "";
            
        }
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
        if (row.size() == 0) {
            String errorMessage = "رکوردی با این کد وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Select Fail;";
            }
             Server.outPrinter(request, response, Js.modal(errorMessage, "پیام سامانه"));
            return "";
        }
        StringBuffer html = new StringBuffer();
        html.append(Js.setVal(tableName + "_" + _id, row.get(0).get(_id)));
        html.append(Js.setVal(_group_id, row.get(0).get(_group_id)));
        html.append(Js.setVal(_user_id, row.get(0).get(_user_id)));
       
          Server.outPrinter(request, response,html.toString());
            return "";
    }
    
    
     public static void main(String[] args) throws SQLException, Exception {
//        Server.sendEmail("shohreh.shiran@gmail.com", "shiran_shohreh@yahoo.com", "متن پیام", "<h1>سلام</h1><p>حضور بهم رسانید</p> ", true, null, db);
        Server.Connect();
        jjDatabaseWeb db = Server.db;
//        sms.sendMessageByApi(null, db, "09133368036", "تست سلام  ", "", "", "");        
        System.out.println("*******************inser_MediaPlanItem****************************************");
//obtaining input bytes from a file  
        FileInputStream fis = new FileInputStream(new File("D:\\work\\customersProject\\tmkde.ir\\وبسایت\\وبسایت\\مرق.xlsx"));
        int groupId = 14;
//creating workbook instance that refers to .xlsx file     
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//creating a Sheet object to retrieve the object  
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println("___________________Sheet_____________________");
        System.out.println(sheet.getLastRowNum());
        System.out.println("_____________________________________________");
        DataFormatter formatter = new DataFormatter();        
        int sum = 0;
        StringBuilder gherrrrr = new StringBuilder();
        StringBuilder ok = new StringBuilder();
        StringBuilder okOoofff = new StringBuilder();
        StringBuilder jerrrrrrrrrrr = new StringBuilder();
        int coutOk = 0;
        int coutOkOoofff = 0;
        int coutGherrrrr = 0;
        int coutJerrrrrrr = 0;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Map<String, Object> map = new HashMap<>();
            XSSFRow excelRow = sheet.getRow(i);
//            System.out.println(i + "(" + formatter.formatCellValue(excelRow.getCell(12)) + ":" + formatter.formatCellValue(excelRow.getCell(44)) + ")");
//            System.out.println(i + "[" + formatter.formatCellValue(excelRow.getCell(1)) + ":" + formatter.formatCellValue(excelRow.getCell(3)) + "]");
//            System.out.println(formatter.formatCellValue(excelRow.getCell(0)));
//            if (formatter.formatCellValue(excelRow.getCell(12)).equals(formatter.formatCellValue(excelRow.getCell(44)))) {// یعنی انتقال نداده است و این مالک نهایی هم هست
                sum++;
                List<Map<String, Object>> userRowInDb = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._int1 + "='" + formatter.formatCellValue(excelRow.getCell(0)) + "'"));
                if (userRowInDb.size() == 1) {
                    map.put(Access_Group_User._user_id, userRowInDb.get(0).get(_id));
                    map.put(Access_Group_User._group_id, groupId);
                    db.insert(Access_Group_User.tableName, map);
                    System.out.println("OK>>>>>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)));
                    ok.append(formatter.formatCellValue(excelRow.getCell(0)) + "<<<OK\n");
                    coutOk++;
                } else {
                    System.out.println("JERRRRRRRRRRR>>>>>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)));
                    jerrrrrrrrrrr.append("JERRRRRRRRRRR>>>>>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)) + "\n");
                    coutJerrrrrrr++;
                }
//            } 
//        if (jjNumber.isDigit(formatter.formatCellValue(excelRow.getCell(44)))) {// شاید مالک دوم در در سیستم کاربر ما باشد
//                userRowInDb = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + "='" + formatter.formatCellValue(excelRow.getCell(12)) + "'"));
//                if (userRowInDb.size() == 1) {
//                    if (formatter.formatCellValue(excelRow.getCell(3)).replaceAll("ي", "ی").equals(userRowInDb.get(0).get(Access_User._name).toString().replaceAll("ي", "ی"))
//                            && formatter.formatCellValue(excelRow.getCell(4)).replaceAll("ي", "ی").equals(userRowInDb.get(0).get(Access_User._family).toString().replaceAll("ي", "ی"))) {
//                        System.out.println("OKOOOOFfff>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)) + " " + userRowInDb.get(0).get(Access_User._name) + " " + userRowInDb.get(0).get(Access_User._family)
//                                + formatter.formatCellValue(excelRow.getCell(3)) + " " + formatter.formatCellValue(excelRow.getCell(4))
//                        );
//                        okOoofff.append("OKOOOOFfff>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)) + " " + userRowInDb.get(0).get(Access_User._name) + " " + userRowInDb.get(0).get(Access_User._family)
//                                + formatter.formatCellValue(excelRow.getCell(3)) + " " + formatter.formatCellValue(excelRow.getCell(4)) + "\n");
//                        map.put(Access_Group_User._user_id, userRowInDb.get(0).get(_id));
//                        map.put(Access_Group_User._group_id, groupId);
//                        db.insert(Access_Group_User.tableName, map);
//                        sum++;
//                        coutOkOoofff++;
//                    } else {
//                        System.out.println("GHrrrrrrrrrrrrr>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)) + " " + userRowInDb.get(0).get(Access_User._name) + "_" + userRowInDb.get(0).get(Access_User._family) + " "
//                                + formatter.formatCellValue(excelRow.getCell(3)) + "_" + formatter.formatCellValue(excelRow.getCell(4))
//                        );
//                        gherrrrr.append("GHrrrrrrrrrrrrr>>>" + "cell(0):" + formatter.formatCellValue(excelRow.getCell(0)) + " " + userRowInDb.get(0).get(Access_User._name) + "_" + userRowInDb.get(0).get(Access_User._family) + " "
//                                + formatter.formatCellValue(excelRow.getCell(3)) + "_" + formatter.formatCellValue(excelRow.getCell(4)) + "\n");
//                        coutGherrrrr++;
//                    }
//                }
//            }
//            System.out.println("cell(0):" + formatter.formatCellValue(row.getCell(0)));//نام پابلیشر 
//            System.out.println("cell(1):" + formatter.formatCellValue(row.getCell(1)));//نوع پابلیشر
//            System.out.println("cell(12):" + formatter.formatCellValue(row.getCell(2)));//توضیحات 
            System.out.println("___________________________________________________________________");
        }
        System.out.println("groupId" + groupId + ")))))))))))))))))))))):sum OK:" + sum);
        System.out.println("OK-----------------------------------------------------------------------------=" + coutOk);
        System.out.println(ok);
        System.out.println("OK_Move-----------------------------------------------------------------------------=" + coutOkOoofff);
        System.out.println(okOoofff);
        System.out.println("reCheck-----------------------------------------------------------------------------=" + coutGherrrrr);
        System.out.println(gherrrrr);
        System.out.println("ERRRRR-----------------------------------------------------------------------------=" + coutJerrrrrrr);
        System.out.println(jerrrrrrrrrrr);
    }
}
