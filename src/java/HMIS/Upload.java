/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.tools.Js;
import cms.tools.Server;
import cms.tools.UploadServlet;
import static cms.tools.UploadServlet._file_name;
import static cms.tools.UploadServlet._id;
import static cms.tools.UploadServlet._title;
import static cms.tools.UploadServlet.tableName;
import cms.tools.jjTools;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjDatabase;
import jj.jjDatabaseWeb;

/**
 *
 * @author shiran
 */
public class Upload {
     public static String setTitle(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
         StringBuilder html = new StringBuilder();
         String title = jjTools.getParameter(request, "title");
         String filename = jjTools.getParameter(request, "filename");
//         String pic = jjTools.getParameter(request, "pic");
         
         List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(UploadServlet.tableName,_file_name+"='"+filename+"'"));
       String uploadFilename=rows.get(0).get(UploadServlet._file_name).toString();
          Map<String, Object> map = new HashMap<String, Object>();
          

            map.put(_title, title);
            if (!db.update(tableName, map, _file_name + "='" + uploadFilename+"'")) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
        
        
        } catch (Exception e) {
            Server.outPrinter(request, response, Server.ErrorHandler(e));
            return "";
        }
        return "";
    }
}
