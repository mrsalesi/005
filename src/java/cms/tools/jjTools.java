/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

//import cms.ticeAccess.Tice_User;
import HMIS.Role;
import cms.access.Access_User;
//import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.*;
import jj.jjCalendar_IR;
//import jj.jjFileTxt;
import jj.jjNumber;
//import jj.jjWebURL;

/**
 *
 * @author Arvin2
 */
public class jjTools {

    public static PrintWriter getWriterUTF8(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            OutputStream os = response.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            return new PrintWriter(osw);
//            return response.getWriter();
        } catch (Exception e) {
            Server.ErrorHandler(e);
        }
        return null;
    }
    public static String today = "";
//    public static StringBuffer beingSeassionToday = new StringBuffer();
    public static int todayUserCount;

    public static String setLang(HttpServletRequest request) {
        try {
//            jjCalendar_IR ir = new jjCalendar_IR();
//            String nowDate = ir.getViewFormat_10length();
//            if (today.equals("")) {
//                today = nowDate;
//            } else if (!today.equals(nowDate)) {
//                today = nowDate;
////                beingSeassionToday = new StringBuffer();
//            }

            if (!jjTools.getParameter(request, "myLang").equals("")) {//اگر در ریکوئست زبان را تعیین کرده بود یعنی احتمال زیاد میخواهیم زبان تغییر کند
                //زبان باید یک عدد باشد بین یک تا پنج
                jjTools.setSessionAttribute(request, "myLang", jjTools.getParameter(request, "myLang"));

                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {//میتوانیم آی پی کاربران را ذخیره کنیم
                    ipAddress = request.getRemoteAddr();
                }
//                String sesID = request.getSession(true).getId();
//                if (beingSeassionToday.toString().indexOf("#" + sesID + "#") == -1) {
//                    beingSeassionToday.append("#" + sesID + "#");
//                }

//                >>>> By mohammad commented................................................
//                if (!ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                    File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
//                    File visitor = new File(folderAddress.getAbsolutePath() + "/visitor.txt");
//                    String ipData = jjWebURL.getWebSiteSource("http://api.ipinfodb.com/v3/ip-city/?key=20b96dca8b9a5d37b0355e9461c66e76eed30a2274422fa6213d9de6ffb2b34e&ip=" + ipAddress);
//                    if (ipData.indexOf(";") > -1) {
//                        String[] split = ipData.split(";");
//                        ipData = split.length > 5 ? ipAddress + " - " + split[4] + " - " + split[5] + " - " + split[6] : "Unknow IP";
//                    }
//                    jjFileTxt.write(visitor, nowDateAndTime + " - " + ipData, true, true);
//                }
//                .......................................
            } else if (jjTools.getSessionAttribute(request, "myLang").equals("")) {//اگر در ریکوئست نبود و در سشن هم نبود مقدار پیشفرض را بگذار در سشن
                jjTools.setSessionAttribute(request, "myLang", Server.defaultLang);
            }
//            if (!jjTools.getSessionAttribute(request, "myLang").equals("")) {
//                if (!jjTools.getSessionAttribute(request, "myLang").equals("2")) {
//                        jjTools.setSessionAttribute(request, "myLang", Server.defaultLang);
//                    if (!jjTools.getSessionAttribute(request, "myLang").equals("3")) {
//                    }
//                }
//            }
//            return Js.setHtml("#jjTodayUserCount", (beingSeassionToday.toString().split("##").length + 250));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static void setPoolStatus(HttpServletRequest request) {
        try {
            if (!jjTools.getParameter(request, "IS_POLL_NEED_LOGIN").equals("")) {
                Server.pollNeedLogin = jjTools.getParameter(request, "IS_POLL_NEED_LOGIN").toLowerCase().equals("true");
            }
            if (!jjTools.getParameter(request, "IS_POLL_SOW_AFTER_VOTE").equals("")) {
                Server.pollShowAfterVote = !jjTools.getParameter(request, "IS_POLL_SOW_AFTER_VOTE").toLowerCase().equals("false");
            }
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
        }
    }

    public static void setLangFa(HttpServletRequest request) {
        jjTools.setSessionAttribute(request, "myLang", "1");
    }

    public static void setLangEn(HttpServletRequest request) {
        jjTools.setSessionAttribute(request, "myLang", "2");
    }

    public static boolean isLangFa(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("1");
//        return jjTools.getSessionAttribute(request, "myLang").equals("fa");
    }

    public static boolean isLangEn(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("2");
    }

    public static boolean isLangAr(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("3");
    }
//============ BY RASHIDI ========>

    /**
     * زبان را از سشن می خواند و یک عدد بر می گرداند که اگر ست نشده بود زبان
     * پیشفرض را بر می گرداند
     *
     * @param request
     * @return
     */
    public static String getLangNum(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("") ? Server.defaultLang : jjTools.getSessionAttribute(request, "myLang");
    }

    public static String getLangNumDir(HttpServletRequest request) {//برای یکسری زبان ها راست چین یکسری زبان ها چپ چین
        return jjTools.getSessionAttribute(request, "myLang").equals("1") ? "rtl" : "ltr";
    }
//<============ BY RASHIDI ========

    public static String getLangDir(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("2") ? "ltr" : "rtl";
    }

    public static String getLangAlign(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("1") ? "right" : "left";
    }

    public static boolean isUserLogin(HttpServletRequest request) {
        return (jjNumber.isDigit(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase())));
    }
    /**
     * آی دی کاربر بعد از لاگین را بر می گرداند
     * در صورتیکه کاربر لاگین نکرده باشد صفر بر می گرداند
     * @param request
     * @return 
     */
    public static int getSeassionUserId(HttpServletRequest request) {
        //@ToDo شاید بهتر باشد که منفی یک برگردانیم
        return jjNumber.isDigit(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase())) ? Integer.parseInt(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase())) : 0;
    }

    /**
     * رشته ای را برمیگرداند که در آن نام و نام خانوادگی کاربر فعال در سشن را بی
     * می گرداند اگر نال باشد رشته ی تهی بر میگرداند اگر نباشد بین نام و نام
     * خانوادگی بک فاصله می گذارد
     *
     * @param request
     * @return 5%A%21%A%55%A%
     */
    public static String getSeassionUserNameAndFamily(HttpServletRequest request) {
        return (jjTools.getSessionAttribute(request, "#USER_NAME") == null ? "" : (jjTools.getSessionAttribute(request, "#USER_NAME") + " ")) + (jjTools.getSessionAttribute(request, "#USER_FAMILY") == null ? "" : jjTools.getSessionAttribute(request, "#USER_FAMILY"));
    }
    

    /**
     * رشته ای را برمیگرداند که در آن نقش های کاربر با کاراکتر های خاص از هم جدا
     * میشوند
     *
     * @param request
     * @return 5%A%21%A%55%A%
     */
    public static String getSeassionUserRole(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "#ROLE_ID") == null ? "" : jjTools.getSessionAttribute(request, "#ROLE_ID");
    }

    /**
     * نام کوکی ست شده را میگیرد و مقدار آنرا برمیگرداند
     *
     * @param request
     * @param key نام کوکی
     * @return مقدار داخل کوکی
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(key)) {
                return cookies[i].getValue();
            }
        }
        return "";
    }

    /**
     * آی پی سیستم کاربر را برمیگرداند
     *
     * @param request
     * @return IPv4 or IPv6
     */
    public static String getuserIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value == null) {
            return "";
        } else {
//                return mode.toString().equals("null") ? "" :(new String(request.getParameter(name).getBytes(), "UTF-8"));
            return value;
        }
    }

    public static String getAttribute(HttpServletRequest request, String name) {
        Object mode = request.getAttribute(name);
        if (mode == null) {
            return "";
        } else {
            return mode.toString().equals("null") ? "" : mode.toString();
        }
    }

    public static void ShowAllParameter(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        ServerLog.Print("-----------ShowAllParameter-----------");
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            ServerLog.Print(key + "=" + request.getParameter(key));
        }
        ServerLog.Print("--------------_________---------------");
    }

    public static void ShowAllAttribute(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        ServerLog.Print("-----------ShowAllAttribute-----------");
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            ServerLog.Print(key + "=" + request.getAttribute(key));
        }
        ServerLog.Print("--------------_________---------------");
    }

    public static String getSessionAttribute(HttpServletRequest request, String name) {
        try {
            Object mode = request.getSession().getAttribute(name);
            if (mode == null) {
                return "";
            } else {
                return mode.toString().equals("null") ? "" : mode.toString();
            }
        } catch (Exception e) {
            return Server.ErrorHandler(e);
        }
    }

    public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        try {
            ServerLog.Print(">> In session '" + name + "' = '" + value + "'");
            request.getSession(true).setAttribute(name, value);
        } catch (Exception e) {
            Server.ErrorHandler(e);
        }
    }

    public static String getAtLeastChar(String html) {
        return html.equals("") ? "&nbsp;" : html;
    }

    public static String getAtLeastChar(Object html) {
        return getAtLeastChar(html.toString());
    }
}
