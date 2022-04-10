/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import static cms.tools.Server.databaseName;
import static cms.tools.Server.password;
import static cms.tools.Server.port;
import static cms.tools.Server.serverHostIP;
import static cms.tools.Server.userName;
import static cms.tools.email.tableName;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;

/**
 *
 * @author hoda
 */
public class EmailTracker extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmailTracker</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmailTracker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServerLog.Print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      //  jjDatabaseWeb db =new jjDatabaseWeb(userName, password, databaseName, serverHostIP, port);;
        Server.Connect();
      jjDatabaseWeb db=Server.db;
        int count = 0;
        String id =request.getParameter(email._id);
        ServerLog.Print("id====>"+ id);
        
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(email.tableName, email._id + "=" + id));
        if (!row.isEmpty()) {
            count = Integer.parseInt(row.get(0).get(email._visit_count).toString());
            ServerLog.Print("cont ====>" + count);
            count++;
        }
        ///////-------------DATE------->

        jjCalendar_IR dateIR = new jjCalendar_IR();
        int dot = dateIR.toString().indexOf("-");
        String date = "";
        String time = "";
        if (dot > -1) {
            date = dateIR.toString().substring(0, dot - 1);
            time = dateIR.toString().substring(dot + 2, dateIR.toString().length());
        }
        ServerLog.Print("DATE : " + date + " TIME : " + time);
        int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);
            ///////<-------------DATE-------

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(email._visit_status, "مشاهده شده");
        map.put(email._visit_time, time);
        map.put(email._visit_date, dateInt);
        map.put(email._visit_count, count);
        db.update(email.tableName, map, email._id + "=" + id);
        
        PrintWriter out = jjTools.getWriterUTF8(request, response);
        try {
            ServerLog.Print(email.refresh(request,response, db, true));
        out.print(email.refresh(request,response, db, true));
        } catch (Exception ex) {
            Logger.getLogger(EmailTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
