/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjDatabase;
import jj.jjDatabaseWeb;

/**
 *
 * @author Rashidi
 */
@WebServlet(name = "clickTracker", urlPatterns = {"/clickTracker"})
public class clickTracker extends HttpServlet {

    public static jjDatabaseWeb db;
    public static String databaseName = "db_sepanoshop";
    public static String userName = "root";
    public static String password = "m123456";
    public static final String port = "3306";
    public static final String serverHostIP = "localhost";
    public static String tableName = "click_tracker";
    public static String _id = "id";
    public static String _link = "click_tracker_link";
    public static String _visitor = "click_tracker_visitor";
    public static String _clickCount = "click_tracker_clickCount";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Connect();
            
            String errorMessage = "";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _link + " = '" + jjTools.getParameter(request, _link) + "' AND " + _visitor + " = '" + jjTools.getParameter(request, _visitor) + "'"));
            if (row.isEmpty()) {
                ServerLog.Print("row.size : " + row.size());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_link, jjTools.getParameter(request, _link));
                map.put(_visitor, jjTools.getParameter(request, _visitor));
                map.put(_clickCount, 1);
                if (db.insert(tableName, map).getRowCount() == 0) {
                    errorMessage = "عملیات درج به درستی صورت نگرفت.";
                }
            } else {
                if (!db.otherStatement("UPDATE " + tableName + " SET " + _clickCount + " = " + _clickCount + "+1 WHERE " + _id + "=" + row.get(0).get(_id))) {
//                    ServerLog.Print("عملیات ویرایش به درستی صورت نگرفت.");
                    errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                }
            }
            ServerLog.Print(errorMessage);
            response.sendRedirect(jjTools.getParameter(request, _link));
        }
    }

    public static void Connect() {
        if (db == null) {
            db = new jjDatabaseWeb(userName, password, databaseName, serverHostIP, port);
        }
        db.ConnectCustom();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(clickTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(clickTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
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
