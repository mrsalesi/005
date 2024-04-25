<%-- 
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>

<%@page import="jj.jjNumber"%>
<%@page import="cms.tools.Payment"%>
<%@page import="com.sun.xml.internal.ws.api.ha.HaInfo"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URL"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="cms.cms.Tice_config"%>
<%@page import="cms.access.Access_Group_User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="cms.tools.Js"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="cms.access.Access_User"%>
<%@page import="jj.jjDatabase"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cms.tools.Server"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="javax.xml.ws.Holder"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="cms.tools.Server"%>
<%@page import="cms.cms.Content"%>
<%@page import="cms.cms.Pic"%>
<%@page import="cms.cms.Category_Product"%>
<%@page import="cms.cms.Category_Content"%>
<%@page import="cms.cms.Product"%>
<%@page import="cms.cms.Journal"%>
<%@page import="cms.cms.Factor"%>
<%@page import="cms.cms.FactorItem"%>
<%@page import="cms.cms.Category_Gallery"%>
<%@page import="cms.cms.Comment"%>
<%@page import="cms.cms.News"%>
<%
    jjTools.ShowAllParameter(request);
    String paymentError = "";
    if (jjNumber.isDigit(jjTools.getParameter(request, "paymentId"))) {//اگر در پارامتر ها کد پرداخت بود یعنی از صفحه ی پرداخت برگشته و باید وضعیت پرداخت را چک و اعمال کنیم
        System.out.println("_____________________billPaymentStatus.jsp : PAYMENT STATUS_____________________");
        Server.Connect();
//    request.setAttribute("text", "تعاونی دادگستری");
//    Content.sw(request, response, Server.db, true);

        String user_token = jjTools.getParameter(request, "user_token");
        String paymentToken = jjTools.getParameter(request, "Token");
        String user_bills = jjTools.getParameter(request, "id");
//    System.out.println("user_token====" + user_token);
        System.out.println("user_bills====>" + user_token);
        jjDatabaseWeb db;
        db = Server.db;
        List<Map<String, Object>> payemntForVerifyRow = jjDatabase.separateRow(db.Select(Payment.tableName,
                Payment._id + "=" + jjTools.getParameter(request, "paymentId")//رف نام همان آی دی پرداخت است که بانک بر گردانده است و  با پیمنت آی دی یکی استF
        ));
        List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName, "id=" + payemntForVerifyRow.get(0).get(Payment._factorId)));
        List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + rowFactor.get(0).get(Factor._userId)));
        Map<String, Object> map = new HashMap<>();
        String sepTerminalKey = Tice_config.getValue(Server.db, "config_sepTerminalId");
        System.out.println(" call back url:::::~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>");
        if (jjTools.getParameter(request, "State").equals("OK")) {// وضعیت را با استفاده از پارامتر های برگشتی از درگاه پرداخت بررسی میکنیم            
            String refnum = jjTools.getParameter(request, "RefNum");
            String Amount = jjTools.getParameter(request, "Amount");
            String TerminalId = jjTools.getParameter(request, "TerminalId");
            String ResNum = jjTools.getParameter(request, "ResNum");
            String State = jjTools.getParameter(request, "State");
            String Status = jjTools.getParameter(request, "Status");
            String Rrn = jjTools.getParameter(request, "Prn");
            String TraceNo = jjTools.getParameter(request, "TraceNo");
            String Token = jjTools.getParameter(request, "Token");
            String MID = jjTools.getParameter(request, "MID");
            String SecurePan = jjTools.getParameter(request, "SecurePan");
            JSONObject cred = new JSONObject();
            if (!payemntForVerifyRow.get(0).get(Payment._amount).equals(Amount)) {
                paymentError += "مقدار پرداختی شما با مقدار فاکتور همخوانی ندارد. در صورت کسر وجه بعد از یک ساعت به حساب شما برگشت میشود";
            } else {
                cred.put("TerminalNumber", sepTerminalKey);
                cred.put("RefNum", refnum);
                String url = "http://sep.tkd-esf.ir:8000/verifyTxnRandomSessionkey/ipg/VerifyTransaction";
                System.out.println("raw data to sep.tkd-esf.ir >>>" + url + ">>>" + cred.toString());
                HttpURLConnection con = null;
                con = (HttpURLConnection) new URL(url).openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                byte[] outputBytes = cred.toString().getBytes("UTF-8");
                OutputStream os = con.getOutputStream();
                os.write(outputBytes);
                InputStream is = con.getInputStream();
                String resultStr = IOUtils.toString(is, "UTF-8");
                System.out.println(">>>>>>>>Token Json" + resultStr);
                JSONObject result = new JSONObject(resultStr);
                if (result.get("Success").toString().equals("true")) {
                    JSONObject TransactionDetail = result.getJSONObject("TransactionDetail");
                    System.out.println("ResultDescription:" + result.get("ResultDescription").toString());
                    map.clear();
                    map.put(Payment._comments, result.get("ResultDescription").toString());
                    map.put(Payment._orderId, TransactionDetail.get("RefNum").toString());
                    map.put(Payment._comments, TransactionDetail.get("RRN").toString() + "-" + TransactionDetail.get("MaskedPan").toString());
                    db.update(Payment.tableName, map, Payment._id + "=" + payemntForVerifyRow.get(0).get(Payment._id));
                    paymentError += "پرداخت با موفقیت انجام شد";
                    Payment.changeStatus(db, jjTools.getParameter(request, "paymentId"), Payment.status_pardakhtShode);
                    Factor.changeStatus(db, payemntForVerifyRow.get(0).get(Payment._factorId).toString(), Factor.status_pardakhtShode);
                    
                } else {
                    paymentError += result.get("ResultDescription").toString();
                    System.out.println("ResultDescription" + result.get("ResultDescription").toString());
                    System.out.println("ResultCode" + result.get("ResultCode").toString());
                }
            }
            System.out.println("~_~_~_~_~_");

        } else {
            switch (jjTools.getParameter(request, "State")) {
                case "CanceledByUser":
                    paymentError += "کاربر انصراف داده است";
                    break;
                case "Failed":
                    paymentError += "پرداخت انجام نشد.";
                    break;
                case "SessionIsNull":
                    paymentError += "کاربر در بازه زمانی تعیین شده پاسخی ارسال نکرده است.";
                    break;
                case "InvalidParameters":
                    paymentError += "پارامترهای ارسالی نامعتبر است";
                    break;
                case "MerchantIpAddressIsInvalid":
                    paymentError += "آدرس سرور پذیرنده نامعتبر است ";
                    break;
                case "TokenNotFound":
                    paymentError += "توکن ارسال شده یافت نشد";
                    break;
                case "TerminalNotFound":
                    paymentError += "شماره ترمینال ارسال شده یافت نشد";
                    break;

            }

            paymentError += "  پرداخت نشده  -  کد    ";
            Payment.changeStatus(db, jjTools.getParameter(request, "paymentId"), Payment.status_pardakhtNaShode);
        }
    }
    String sepToken = "";//اگر وضعیت فاکتور پرداخت شده بود ایجاد میشود

//    System.out.println("refreshToken\n" + result.get("refreshToken").toString());

%>
<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US" style="direction: rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
        <title>تعاونی دادگستری</title>
        <meta  name="author" content="themesflat.com"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
        <link href="template/css/profile.css" rel="stylesheet" type="text/css"/>
        <link href="StyleBody.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/bootstrap2.css" rel="stylesheet" type="text/css"/>
        <!--<link href="template/css/bootstrap.css" rel="stylesheet" type="text/css"/>-->
        <link href="template/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/responsive.css" rel="stylesheet" type="text/css"/>
        <link href="template/colors/color1.css" rel="stylesheet" type="text/css" id="colors"/>
        <link href="template/css/animate.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/revelationSlider.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/flexslider.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/magpopup.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/shortcodes.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/owlcarusel.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/font-awesome.css" rel="stylesheet"/>
        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/ionicons.css" rel="stylesheet"/>
        <link href="template/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/dataTables.responsive.css" rel="stylesheet" type="text/css"/>
        <style>
            .main-body {
                padding: 15px;
            }
            .card {
                box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
            }

            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid rgba(0,0,0,.125);
                border-radius: .25rem;
            }

            .card-body {
                flex: 1 1 auto;
                min-height: 1px;
                padding: 1rem;
            }

            .gutters-sm {
                margin-right: -8px;
                margin-left: -8px;
            }

            .gutters-sm>.col, .gutters-sm>[class*=col-] {
                padding-right: 8px;
                padding-left: 8px;
            }
            .mb-3, .my-3 {
                margin-bottom: 1rem!important;
            }

            .bg-gray-300 {
                background-color: #e2e8f0;
            }
            .h-100 {
                height: 100%!important;
            }
            .shadow-none {
                box-shadow: none!important;
            } 
            .entry-details-content li:hover{
                border-bottom:1px #eab702 solid;
            }
            .table {
                width: 100%;
                border: 1px solid #EEEEEE;
            }

            .table-header {
                display: flex;
                width: 100%;
                background: #000;
                padding: 18px 0;
            }

            .table-row {
                display: flex;
                width: 100%;
                padding: 18px 0;
            }
            .table-row:nth-of-type(odd) {
                background: #EEEEEE;
            }

            .table-data, .header__item {
                flex: 1 1 20%;
                text-align: center;
            }

            .header__item {
                text-transform: uppercase;
            }

            .filter__link {
                color: white;
                text-decoration: none;
                position: relative;
                display: inline-block;
                padding-left: 24px;
                padding-right: 24px;
            }
            .filter__link::after {
                content: "";
                position: absolute;
                right: -18px;
                color: white;
                font-size: 12px;
                top: 50%;
                transform: translateY(-50%);
            }
            .filter__link.desc::after {
                content: "(desc)";
            }
            .filter__link.asc::after {
                content: "(asc)";
            }
        </style>
    </head>
    <body  style="font-family: vazir3">

        <section class="loading-overlay">
            <div class="Loading-Page">
                <h2 class="loader">درحال بارگیری</h2>
            </div>
        </section>
        <div class="boxed">
            <div class="top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <ul class="flat-information">
                                <li class="phone"><a href="tel:031-36639871">تلفن تماس:031-36639871-2</a></li>
                                <li class="email"><a href="">ایمیل: taavoni@gmail.com</a></li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <div class="social-links">
                                <!--                                                        <a href="">
                                                                                            <a  class="register_url flat-button button-color button-normal yellow " style="" onclick='$("#sw").load("login.html")'>ثبت نام/ورود</a>
                                                                                            <a id="userNameAfterLogin" class="textlogin" style="display: none;"></a>
                                                                                        </a>-->
                                <a href=""><i class="fa fa-twitter"></i></a>
                                <a href=""><i class="fa fa-facebook"></i></a>
                                <a href=""><i class="fa fa-instagram"></i></a>
                                <a href=""><i class="fa fa-linkedin"></i></a>
                                <!--<a href=""><a id="userNameAfterLogin1" class="textlogin1" style="display: none"></a></a>-->

                                <!--<a class="register_url" style='display: none' onclick='$("#sw").load("login.html")'><i class="fa fa-user menu-extra-mobile"></i></a>-->
                                <!--<a id="userNameAfterLogin" class="prof textlogin flat-button button-color button-normal yellow " style="display: none;margin: 0px 5px;"></a>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <header id="header" class="header clearfix">
                <div class="container">
                    <div class="row">
                        <div class="header-wrap clearfix">
                            <div class="col-md-3">
                                <div id="logo" class="logo">
                                    <a href="" rel="home">
                                        <img src="template/img/logo.png" alt="image">
                                    </a>
                                </div>
                                <div class="btn-menu">
                                    <span></span>
                                </div>
                            </div>
                            <div class="col-md-9 pos-static">
                                <div class="nav-wrap has-megamenu">
                                    <nav id="mainnav" class="mainnav">
                                        
                                    </nav>
                                </div>
                                <ul class="menu menu-extra" style="">
                                    <li class="off-canvas-toggle">
                                        <a href="#"><i class="fa fa-bars"></i></a>
                                    </li>

                                </ul><!--
                                <ul class="menu menu-extra-mobile" style="">
                                    <li class="off-canvas-toggle">
                                        <a  class="register_url flat-button button-border button-normal " style="display: none;margin: 27px 2px;" onclick='$("#sw").load("login.html")'><i class="fa fa-user"></i></a>
                                        <a id="userNameAfterLogin" class="textlogin flat-button button-border button-normal  " style="display: none;margin: 27px 2px;"></a>
                                    </li>
                                </ul>-->
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div id="sw" style="background: #fff">
                <div class="container">
                    <h2 class="text-info">
                        <%= paymentError%>
                    </h2>
                    <a  style="width: 30%;text-align: center;display: block;margin: 10px auto" 
                        data-filter=".hammer"  href="userProfile.jsp" class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" >بازگشت بخه پروفایل</a>
                </div>                
                <!--end footer-->
            </div>
        </div>       
        <div id="site-off-canvas">
            <span class="close"></span>
            <div class="wrapper">
                <div class="widget widget_search">
                    <form role="search" method="get" class="search-form" action="#">
                        <label>
                            <input type="search" class="search-field" placeholder="جستجو" value="" name="s">
                        </label>
                        <input type="submit" class="search-submit" value="پیدا کردن">
                    </form>
                </div>
                <div id="nav_menu-2" class="widget widget-categories">
                    <h4 class="widget-title">صفحات</h4>
                    <ul>
                        <li><a href="index.jsp">صفحه اصلی</a></li>
                        <li><a href="#">پروژه های شرکت</a></li>
                        <li><a href="#">خدمات</a></li>
                        <li><a href="#">گالری</a></li>
                        <li><a href="#">تماس با ما</a></li>
                        <li><a href="#">درباره ما</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <a class="go-top show">
            <i class="fa fa-angle-up"></i>
        </a>

        <script src="template/js/jquery.min.js" type="text/javascript"></script>
        <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="Manager/js/ajaxfileupload.js" type="text/javascript"></script>
        <script src="template/js/profile.js" type="text/javascript"></script>
        <script src="template/js/index.js" type="text/javascript"></script>
        <script src="template/js/jquery.easing.js" type="text/javascript"></script>
        <script src="template/js/imagesloaded.min.js" type="text/javascript"></script>
        <script src="template/js/jquery.isotope.min.js" type="text/javascript"></script>
        <script src="template/js/jquery-waypoints.js" type="text/javascript"></script>
        <script src="template/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
        <script src="template/js/jquery.cookie.js" type="text/javascript"></script>
        <script src="template/js/jquery.fitvids.js" type="text/javascript"></script>
        <script src="template/js/parallax.js" type="text/javascript"></script>

        <script src="template/js/smoothscroll.js" type="text/javascript"></script>
        <script src="template/js/jquery.flexslider-min.js" type="text/javascript"></script>
        <script src="template/js/jquery-validate.js" type="text/javascript"></script>
        <script src="template/js/switcher.js" type="text/javascript"></script>
        <script src="template/js/js.js" type="text/javascript"></script>
        <script src="template/js/gmap3.min.js" type="text/javascript"></script>
        <script src="template/js/main.js" type="text/javascript"></script>
        <script src='template/js/owl.carousel.js' type='text/javascript'></script>
        <script>

        </script>
        <script>





        </script>
    </body>      
</html>
