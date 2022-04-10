package cms.cms;

import cms.tools.Js;
import cms.tools.Server;
import cms.tools.ServerLog;
import cms.tools.jjTools;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjDatabase;
import jj.jjDatabaseWeb;

public class Search {

    public static String _text_val = "searchInPortal";
    public static String _portal_post_val01 = "portal_post_val01";
    public static String _portal_post_val02 = "portal_post_val02";
    public static String _portal_post_val03 = "portal_post_val03";
    public static String _portal_post_val04 = "portal_post_val04";
    public static String _portal_post_val05 = "portal_post_val05";
    public static String _search_val = "search_val";
    public static String _portal_user_pageTitle1 = "portal_user_pageTitle1";
    public static String _portal_user_pageTitle2 = "portal_user_pageTitle2";
    public static String _portal_user_content = "portal_user_content";
    public static String _pageTitle1 = "portal_user_pageTitle1";
    public static String _id = "id";

    public static String getFormForSearchInPortalPosts(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String panel = jjTools.getParameter(request, "panel") != null ? jjTools.getParameter(request, "panel") : "swSearchInPortalDiv";
        List<Map<String, Object>> portalProps = jjDatabaseWeb.separateRow(db.SelectDistinct(Portal.tableName,
                Portal._prop1 + "," + Portal._prop2 + "," + Portal._prop3 + "," + Portal._prop4 + "," + Portal._prop5));
        StringBuilder html = new StringBuilder("");
        html.append("<div><input type='text' name='searchInPortal' id='searchInPortal' /><div>فیلتر نتایج جستجو با :"
                + "</div><div class='ads clearfix bg-info'>")
                .append(portalProps.get(0).get(Portal._prop1).toString())
                .append("<select style='width:190px;' class='form-control' id='portal_post_val01' name='portal_post_val01'>")
                .append(Portal.get_val1List(request, db, true))
                .append("</select></div><div class='ads clearfix bg-info'>")
                .append(portalProps.get(0).get(Portal._prop2))
                .append("<select style='width:190px;' class='form-control' id='portal_post_val02' name='portal_post_val02'>")
                .append(Portal.get_val2List(request, db, true))
                .append("    </select>" + "</div>" + "<div class='ads clearfix bg-info'>")
                .append(portalProps.get(0).get(Portal._prop3))
                .append("    <select style='width:190px;' class='form-control' id='portal_post_val03' name='portal_post_val03'>")
                .append(Portal.get_val3List(request, db, true)).append("</select></div><div class='ads clearfix bg-info'>")
                .append(portalProps.get(0).get(Portal._prop4)).append("<select style='width:190px;' class='form-control' id='portal_post_val04' name='portal_post_val04'>").append(Portal.get_val4List(request, db, true)).append("    </select>" + "</div>" + "<div class='ads clearfix bg-info'>").append(portalProps.get(0).get(Portal._prop5)).append("    <select style='width:190px;' class='form-control' id='portal_post_val05' name='portal_post_val05'>")
                .append(Portal.get_val5List(request, db, true))
                .append("</select>"
                        + "</div>"
                        + "    <input type='submit' value='جستجو ...' onclick='searchInPortalPosts();"
                        + "        return false' />"
                        + "                    </div>");
        return Js.setHtml("#" + panel, html.toString());
    }

    ;
    public static String searchInPortalPosts(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            String val01 = jjTools.getParameter(request, _portal_post_val01);
            String val02 = jjTools.getParameter(request, _portal_post_val02);
            String val03 = jjTools.getParameter(request, _portal_post_val03);
            String val04 = jjTools.getParameter(request, _portal_post_val04);
            String val05 = jjTools.getParameter(request, _portal_post_val05);
            String text_val = jjTools.getParameter(request, _text_val);
            String where = "";
//            String where1 ="";
            if (!val01.equals("")) {
                where += Portal._val1 + " ='" + val01.toString() + "'";
            }
            if (!val02.equals("")) {
                if (!where.equals("")) {
                    where += " and ";
                }
                where += Portal._val2 + " ='" + val02.toString() + "'";
            }
            if (!val03.equals("")) {
                if (!where.equals("")) {
                    where += " and ";
                }
                where += Portal._val3 + " ='" + val03.toString() + "'";
            }
            if (!val04.equals("")) {
                if (!where.equals("")) {
                    where += " and ";
                }
                where += Portal._val4 + " ='" + val04.toString() + "'";
            }
            if (!val05.equals("")) {
                if (!where.equals("")) {
                    where += " and ";
                }
                where += Portal._val5 + " ='" + val05.toString() + "'";
            }
            if (!text_val.equals("")) {
                if (!where.equals("")) {
                    where += " and ( ";
                }
                where += Portal._val1 + " like " + "'%" + text_val + "%'" + " or ";
                where += Portal._val2 + " like " + "'%" + text_val + "%'" + " or ";
                where += Portal._val3 + " like " + "'%" + text_val + "%'" + " or ";
                where += Portal._val4 + " like " + "'%" + text_val + "%'" + " or ";
                where += Portal._val5 + " like " + "'%" + text_val + "%'";
                if (where.contains("(")) {
                    where += ")";
                }
            }

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Portal.tableName, where));

            ServerLog.Print("row" + row);
            if (row.isEmpty()) {
                String errorMessage = "نتیجه ای یافت نشد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            for (int i = 0; i < row.size(); i++) {
                html.append("<table>");
                html.append("<tr> <td>");
                html.append("<input type=\"text\" id=\"portal_post_prop" + i + "\" name=\"portal_post_prop" + i + "\" disabled=\"disabled\" value='" + row.get(i).get(Portal._prop1) + "'/>");
                html.append("</td><td>");
                html.append("<input type=\"text\" id=\"portal_post_val" + i + "\" name=\"portal_post_val" + i + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._val1) + "'/>");
                html.append("</td></tr><tr><td>");
                html.append("<input type=\"text\" id=\"portal_post_prop" + i + 1 + "\" name=\"portal_post_prop" + i + 1 + "\" disabled=\"disabled\" value='" + row.get(i).get(Portal._prop2) + "'/>");
                html.append("</td><td>");
                html.append("<input type=\"text\" id=\"portal_post_val" + i + 1 + "\" name=\"portal_post_val" + i + 1 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._val2) + "'/>");
                html.append("</td></tr><tr><td>");
                html.append("<input type=\"text\" id=\"portal_post_prop" + i + 2 + "\" name=\"portal_post_prop" + i + 2 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._prop3) + "'/>");
                html.append("</td><td>");
                html.append("<input type=\"text\" id=\"portal_post_val" + i + 2 + "\" name=\"portal_post_val" + i + 2 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._val3) + "'/>");
                html.append("</td></tr><tr><td>");
                html.append("<input type=\"text\" id=\"portal_post_prop" + i + 3 + "\" name=\"portal_post_prop" + i + 3 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._prop4) + "'/>");
                html.append("</td><td>");
                html.append("<input type=\"text\" id=\"portal_post_val" + i + 3 + "\" name=\"portal_post_val" + i + 3 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._val4) + "'/>");
                html.append("</td></tr><tr><td>");
                html.append("<input type=\"text\" id=\"portal_post_prop" + i + 4 + "\" name=\"portal_post_prop" + i + 4 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._prop5) + "'/>");
                html.append("</td><td>");
                html.append("<input type=\"text\" id=\"portal_post_val" + i + 4 + "\" name=\"portal_post_val" + i + 4 + "\" disabled=\"disabled\"value='" + row.get(i).get(Portal._val5) + "'/>");
                if (!row.get(i).get(Portal._pic1).equals("")) {
                    html.append("</td></tr><tr> <img src='upload/" + row.get(i).get(Portal._pic1).toString() + "' id=\"portal_post_pic" + i + "\" name=\"portal_post_pic" + i + "\"style=\"width: 20%\"/>\n"
                            + "            <img id=\"preview_portalPost_pic" + i + "\" name=\"preview_portalPost_pic" + i + "\"/>");
                }
                
                html.append("</tr>");
                html.append("</table>");

                
            }

            return (Js.setHtml(panel, html.toString()));

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }

    }

   
public static String getFormForSearchInPortalUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String panel = jjTools.getParameter(request, "panel") != null ? jjTools.getParameter(request, "panel") : "swSearchInPortalUserDiv";
        StringBuilder html = new StringBuilder("");
        html.append("<div><input type='text' name='search_val' id='search_val' />")
                .append("    <input type='submit' value='جستجو ...' onclick='cmssearch.searchInPortalUser();' /></div>");
        return Js.setHtml("#" + panel, html.toString());
    }
    
    public static String searchInPortalUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            String searchval = jjTools.getParameter(request, _search_val);
            String where = "";
            if (!searchval.equals("")) {
                where += _portal_user_pageTitle1 + " like " + "'%" + searchval + "%'" + " or ";
                where += _portal_user_pageTitle2 + " like " + "'%" + searchval + "%'" + " or ";
                where += _portal_user_content + " like " + "'%" + searchval + "%'";
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(PortalUser.tableName, where));
            ServerLog.Print("row" + row);
            if (row.isEmpty()) {
                String errorMessage = "نت?جه ا? ?افت نشد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < row.size(); i++) {
                
                 html.append(
                         
                        "<a href=\""+Server.portalPage+"?PortalId=" + row.get(i).get(_id) + "\"><span><b><i class=\"fa fa-caret-left\"></i><i class=\"fa fa-caret-left\"></i></b></span> "
                        + "<font> " + row.get(i).get(_pageTitle1).toString() + " </font></a></br>");
            }
            return (Js.setHtml("#search2_button", html.toString()));

    }
    catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    
}
}
