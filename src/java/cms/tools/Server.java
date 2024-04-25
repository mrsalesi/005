package cms.tools;

//
import cms.cms.*;
//import cms.cms.Product;
import cms.access.*;
import HMIS.*;
import HMIS.Upload;
import cms.cms.Language;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import jj.jjTime;

public class Server extends HttpServlet {

    public static String mainSite = "https://www.tkd-esf.ir";
    public static String portalPage = "";
//    public static String databaseName = "db_ershad";
    public static String databaseName = "db_taavoni";
    public static String serverIp = "https://www.tkd-esf.ir";
    public static String userName = "root";
    public static String password = "m123456";
//    public static String userName = "root";
//    public static String password = "m123456";
    public static String userNameSMS = "0";
    public static String passwordSMS = "0";
    public static String defaultLang = "1"; // fa
    public static String smsKey = "0";
    public static String smsPanelNumber = "0";
    public static String siteName = "https://www.tkd-esf.ir";// callback url
    public static String emailAccount = "ebrahemimorteza@gmail.com";
    public static String passEmail = "sepahan901614003";
    public static String smtpAcount = "smtp.gmail.com";
    public static String mainPage = "index.jsp";
    public static String mainPageJSP = "index.jsp";
    public static String contentJSP = "indexContent.jsp";
    public static String newsJSP = "indexNews.jsp";
    public static String productJSP = "index_1.html";
    public static String currentPath = "";
    //********************************************************************

    public static jjDatabaseWeb db;
//    public static final String port = "3306";
    public static final String port = "3307";
    public static final String serverHostIP = "localhost";
    public static boolean pollNeedLogin = false;
    public static boolean pollShowAfterVote = true;
    public static HttpServletResponse Publicresponse;//By Md

    private static final List<Class> clazzes = new ArrayList<>();

    public static List<Class> getClazzes() {
        if (clazzes.isEmpty()) {
            clazzes.add(Access_Group.class);
            clazzes.add(Access_Group_User.class);
            clazzes.add(Access_User.class);
            clazzes.add(Forum.class);
            clazzes.add(Backup.class);
            clazzes.add(Poll.class);
            clazzes.add(sms.class);// ADDED BY RASHIDI
            clazzes.add(smsSetting.class);// ADDED BY RASHIDI
            clazzes.add(Enrolment.class);
            clazzes.add(Enrolment3.class);
            clazzes.add(Portal.class);
            clazzes.add(PortalUser.class);
            clazzes.add(cms.cms.Factor.class);
            clazzes.add(cms.tools.Payment.class);
            clazzes.add(cms.cms.FactorItem.class);
            clazzes.add(PaymentSetting.class);
//            clazzes.add(Customer.class);
            clazzes.add(Tags.class);//SHAHSANAEI 
            clazzes.add(Language.class);// ADDED BY RASHIDI
            clazzes.add(email.class);
//            clazzes.add(Search.class);
            clazzes.add(Upload.class);
            clazzes.add(Pic.class);
            clazzes.add(Journal.class);
            clazzes.add(cms.cms.Product.class);
            clazzes.add(cms.cms.Comment.class);
//            clazzes.add(Product.class);
            clazzes.add(cms.cms.Category_Content.class);
//            clazzes.add(Supporter.class);//توسط ابراهیمی
//            clazzes.add(Requestleave.class);//توسط ابراهیمی
            ///////////////////////////////////////////////////

//            clazzes.add(HMIS.Strategic.class);
            clazzes.add(HMIS.Forms.class);   //فرم ساز
            clazzes.add(HMIS.FormQuestions.class);   //فرم ساز
            clazzes.add(HMIS.FormQuestionOptions.class);   //فرم ساز
            clazzes.add(HMIS.Indicators.class);   //فرم ساز
            /////////////////////shiran2         ////////////////////  برنامه عملیاتی
            clazzes.add(HMIS.FormAnswerSet.class);   //فرم ساز
            clazzes.add(Reports.class);//گزارش های برنامه 
            clazzes.add(Role.class);
            clazzes.add(Category_Product.class);
            ////////////shirn1 پیام     
            clazzes.add(Messenger.class);
            clazzes.add(HMIS.Messenger.class);
            ///////////////////////////    shiran2      ماژول استراتژیک
//            clazzes.add(Setting.class);//تنظیمات یادآوری
            clazzes.add(cms.cms.Content.class);
//            clazzes.add(FilesUpload.class);// by safe-paradise 
//            clazzes.add(MyFilesManager.class);
            clazzes.add(cms.cms.Category_Gallery.class);
            clazzes.add(News.class);
            clazzes.add(cms.cms.Category_News.class);
//            clazzes.add(HMIS.Package.class); ////by safe-paradise
//            clazzes.add(HMIS.Lessons.class); ////by safe-paradise
//            clazzes.add(HMIS.Reminder.class); ////by safe-paradise
//            clazzes.add(HMIS.Tag.class); ////by safe-paradise
//            clazzes.add(HMIS.DeletedFiles.class); ////by safe-paradise
//            clazzes.add(HMIS.Payment.class); ////by safe-paradise
//            clazzes.add(HMIS.Factor.class); ////by safe-paradise
//            clazzes.add(HMIS.Discounts.class); ////by safe-paradise
//            clazzes.add(cms.cms.Tice_config.class); ////by safe-paradise
//            clazzes.add(zarinPal.ZarinPal.class); ////by safe-paradise
        }
        return clazzes;
    }

    protected void run(HttpServletRequest request, HttpServletResponse response, boolean isFromClient) throws ServletException, IOException, Exception {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        Publicresponse = response;
        Connect();
//            jjTools.setLang(request);
//            request.getSession(true).setMaxInactiveInterval(6000);
//            script.append(jjTools.setLang(request));/////*****************/*/*/*/*/*/*/*/*/
        jjTools.setPoolStatus(request);
        String Action = jjTools.getParameter(request, "do");
        String clazz = jjTools.getParameter(request, "tbl");
        String method = jjTools.getParameter(request, "act");
//            String dbName = jjTools.getParameter(request, "db");
//            if (!dbName.equals("")) {
//                databaseName = dbName;
//                jjTools.setSessionAttribute(request, "databaseName", dbName);
//            }
//            databaseName = jjTools.getSessionAttribute(request, "databaseName").equals("") ? databaseName : jjTools.getSessionAttribute(request, "databaseName");
        int dot = Action.indexOf(".");
        if (dot > -1) {
            clazz = Action.substring(0, dot);
            method = Action.substring(dot + 1, Action.length());
        }
        // -----------------------------------------------------------------

        response.addHeader("Access-Control-Allow-Origin", "*");// Ø¨Ø±Ø§ÛŒ Ù�Ø¹Ø§Ù„ Ú©Ø±Ø¯Ù† Ø§ÛŒØ¬Ú©Ø³ Ø¯Ø± Ù…Ø±ÙˆØ±Ú¯Ø± Ù‡Ø§ Ù„Ø§Ø²Ù… Ø§Ø³Øª
        run(getClazzes(), clazz, method, request, response, db);

//        StringBuilder script = new StringBuilder();
//        script.append(run(getClazzes(), clazz, method, request, db, isFromClient));
//        if (script.length() == 0) {// ÛŒØ¹Ù†ÛŒ Ø§Ú¯Ø± Ù¾Ø§Ø³Ø® Ø¯Ø± Ø±Ø§Ù† ØªÙ‡ÛŒ Ø¨ÙˆØ¯ ÛŒØ¹Ù†ÛŒ Ø±ÛŒÚ©ÙˆØ¦Ø³Øª Ù¾Ø§Ø³ Ø¯Ø§Ø¯Ù‡ Ø´Ø¯Ù‡ Ø¨Ù‡ ÛŒÚ© Ù�Ø§ÛŒÙ„ Ø¬ÛŒ Ø§Ø³ Ù¾ÛŒ
//            ServerLog.Print("***request has been passed to one jsp, Finish Server.java jobs... ***");//By MrSalesi
//            return;
//        }
//        try (PrintWriter out = jjTools.getWriterUTF8(request, response)) {
//            ServerLog.Print(script);//By Md
//            //ServerLog.Print(script.toString());
//            out.print(script);
//        } //By Md
//        script.append(Language.setLang(request));
        // Runtime.getRuntime().gc();
        System.gc();

    }

    /**
     * مثل یک تابع عمل می کند و خروجی را میغرستد به کلاینت خروجی معمولا اسکریپت
     * است چون کد های اچ تی ام ال معمولا از طریق جی اس پی ها تولید می شوند
     *
     * @param request
     * @param response
     * @param script معمولا اسکریپت است
     */
    public static void outPrinter(HttpServletRequest request, HttpServletResponse response, StringBuilder script) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(script);
        out.close();
        out.flush();
        ServerLog.Print(script);
    }

    /**
     * برای اسکریپت های کوچک این تابع کلاس استرینگ معمولی می گیرد مثل یک تابع
     * عمل می کند و خروجی را میغرستد به کلاینت خروجی معمولا اسکریپت است چون کد
     * های اچ تی ام ال معمولا از طریق جی اس پی ها تولید می شوند
     *
     * @param request
     * @param response
     * @param script معمولا اسکریپت است از کلاس استرینگ معمولی
     */
    public static void outPrinter(HttpServletRequest request, HttpServletResponse response, String script) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(script);
        out.close();
        out.flush();
        ServerLog.Print(script);
    }

    public static void Connect() {
        if (db == null) {
            db = new jjDatabaseWeb(userName, password, databaseName, serverHostIP, port);
        }
        db.ConnectCustom();
    }

    public static void run(List<Class> clazz, String className, String methodName, HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db) throws Exception {
        try {
            jjTools.ShowAllParameter(request);
            Language.setLang(request);//============ BY RASHIDI ========
//            jjTools.ShowAllAttribute(request);
            String Action = jjTools.getParameter(request, "do");
//            String reqClazz = "";
//            String method = "";
//            String dbName = jjTools.getParameter(request, "db");
//            if (!dbName.equals("")) {
//                databaseName = dbName;
//                jjTools.setSessionAttribute(request, "databaseName", dbName);
//            }
//            databaseName = jjTools.getSessionAttribute(request, "databaseName").equals("") ? databaseName : jjTools.getSessionAttribute(request, "databaseName");
            int dot = Action.indexOf(".");
            if (dot > -1) {
//                reqClazz = Action.substring(0, dot);
//                method = Action.substring(dot + 1, Action.length());
            }
            for (int j = 0; j < clazz.size(); j++) {
                if (clazz.get(j).getSimpleName().equals(className)) {
                    Method[] methods = clazz.get(j).getMethods();
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].getName().equals(methodName)) {
                            ServerLog.Print("Run: " + className + "." + methods[i].getName() + "()" + "--->" + jjTools.getSeassionUserId(request) + " " + jjCalendar_IR.getDatabaseFormat_8length(null, true) + "-" + jjTime.getTime4lenth(""));
                            methods[i].invoke(null, request, response, db, false);//پارامتر آخر را فقط جی اس پی ها و توابع جاوایی داخل هم فراخوانی می کنند
                            return;
                        }
                    }
                }
            }
//            return "";
        } catch (Exception ex) {
            ErrorHandler(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            run(request, response, true);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            run(request, response, true);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String ErrorHandler(Exception ex) {
        ex.printStackTrace();
        try {
            db.ConnectCustom();
            System.err.println(ex.toString());
            StringBuilder dbErrorWrite = new StringBuilder();
//            StringBuffer returnDialog = new StringBuffer();
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement st = stackTrace[i];
                if (st.getClassName().startsWith("cms") || st.getClassName().startsWith("tice")) {
                    if (!st.getClassName().startsWith("cms.tools.Server")) {
                        dbErrorWrite.append("<p style='direction:ltr'>").append(st.getClassName()).append(".").append(st.getMethodName()).append(" > line:").append(st.getLineNumber()).append("</p>");
                    }
                }
            }
            dbErrorWrite.append("<p style='direction:ltr'>").append(ex.toString()).append("</p>");
//            returnDialog.append("<p style='float: left;direction:ltr'>" + ex.toString() + "</p>");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
            map.put(Comment._email, "mrsalesi@gmail.com");
            map.put(Comment._name_Full, "Ø³ÛŒØ³ØªÙ…");
            map.put(Comment._tell, "03112683807");

            map.put(Comment._text, dbErrorWrite.toString());
            map.put(Comment._title, "Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…");
            map.put(Comment._answer, "");
            db.insert(Comment.tableName, map);
            return Js.modal(dbErrorWrite.toString(), "systemException");
        } catch (Exception ex2) {
            return Js.modal("Error in Server ErrorHandler", "systemException");
        }
    }
//    public static void setSettingProject(String siteName, String userName, String password, String databaseName, String defaultLang) {
//        Server.siteName = siteName;
//        Server.userName = userName;
//        Server.password = password;
//        Server.databaseName = databaseName;
//        Server.defaultLang = defaultLang;
//    }

    public static boolean sendEmail(String from, String to, String subject, String body, boolean isRtl, HttpServletRequest request, jjDatabaseWeb db) {

        //        String host = "mail.hafteghlim-ins.com";
//        String user = "mail@hafteghlim-ins.com";
//        String pass = "m!@#$%&*()";
        String host = Server.smtpAcount;
        String user = Server.emailAccount;
        String pass = Server.passEmail;
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.localhost", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            if (from.isEmpty() || from == null) {
                from = Server.emailAccount;
            }
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[0];
            if (to.indexOf(",") > 0) {
                String[] split = to.split(",");
                address = new InternetAddress[split.length];
                for (int i = 0; i < split.length; i++) {
                    address[i] = new InternetAddress(split[i]);
                }
            } else {
                address = new InternetAddress[1];
                address[0] = new InternetAddress(to);
            }
            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(subject, "UTF-8");
//            message.setHeader("Content-Type", "UTF-8");
            message.setHeader("Content-Type", "text/html;charset=UTF-8");

//            File file = new File(request.getServletContext().getRealPath("/print") + "/email.html");
//            if (file.exists()) {
//                body = jjFileTxt.read(file).replace("bodybody", body);
//            } else {
            message.setText(body, "UTF-8");
//            }
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            ServerLog.Print("SEND EMAIL ~> " + to);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            Map<String, Object> emailMap = new HashMap<>();
            emailMap.put(email._from, from);
            emailMap.put(email._to, to);
            emailMap.put(email._body, body);
            emailMap.put(email._subject, subject);
//            emailMap.put(email._host, smtpAcount);
            String script = "";
            emailMap.put(email._status, "true");
            db.insert(email.tableName, emailMap);
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();

        }

        ServerLog.Print("");
        return true;
    }
}
