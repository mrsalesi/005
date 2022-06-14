<%--
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>
<%@page import="HMIS.FormAnswers"%>
<%@page import="HMIS.Messenger"%>
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
<%@page import="cms.tools.UploadServlet"%>
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
    Server.Connect();
//    request.setAttribute("text", "تعاونی دادگستری");
//    Content.sw(request, response, Server.db, true);
    String user_token = jjTools.getParameter(request, "user_token");
    System.out.println("user_token====" + user_token);
    jjDatabaseWeb db;
    db = Server.db;
    if (user_token.isEmpty() && jjTools.getSeassionUserId(request) > 0) {
        List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + jjTools.getSeassionUserId(request)));;
        user_token = user.get(0).get(Access_User._token).toString();
    }
    if (jjTools.getSeassionUserId(request) == 0 && user_token.isEmpty()) {
        System.out.println("response.sendRedirect>>>" + Server.mainPage);
        response.sendRedirect(Server.mainPage);
        return;
    }
//    List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + jjTools.getSeassionUserId(request) + " OR " + Access_User._token + "='" + user_token + "'"));;
%>
<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US" style="direction: rtl"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <title>تعاونی دادگستری</title>
    <meta  name="author" content="themesflat.com"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link href="template/css/profile.css" rel="stylesheet" type="text/css"/>
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
    <link href="StyleBody.css" rel="stylesheet" type="text/css"/>
    <link href="template/css/dataTables.responsive.css" rel="stylesheet" type="text/css"/>
    <link href="template/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
    <style>
        #user_attachFileUserDiv img,user_attachFileDiv img {
            max-width: 25%;
        }
        #showFileMessengerDiv img,#showFileMessengerDiv_sale img,#showFileMessengerDiv_move img {
            max-width: 100px;
        }
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
        .cog-table{
            text-align: center;
            display: block;
            font-size: 26px;
        }
    </style>
</head>
<body class="header_sticky" style="font-family: vazir3">
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
                            <li class="email"><a >ایمیل: taavoni@gmail.com</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6">
                        <div class="social-links">
                            <% if (jjTools.getSeassionUserId(request) == 0) {
                            %>
                            <a  class="register_url flat-button button-color button-normal yellow " style="" onclick='$("#sw").load("login.html");'>ثبت نام/ورود</a>
                            <%                                    } else {
                                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + jjTools.getSeassionUserId(request)));
                            %>
                            <div id="userNameAfterLogin" class="textlogin" style="float: right;margin: 9px;">
                                <nav id="mainnav"><ul class="menu">
                                        سلام <%= jjTools.getSeassionUserNameAndFamily(request)%>
                                        <li class="userIcon"><i class="fa fa-user " style="margin: 7px;"></i>
                                            <ul class="submenu right-sub-menu">                                                
                                                <li><a href="userProfile.jsp?user_token=<%= user.get(0).get(Access_User._token)%>">پنل کاربری</a></li>
                                                <li><a  onclick="signOut();">خروج</a></li></ul></li></ul></nav>
                            </div>
                            <%
                                }
                            %>
                            <a ><i class="fa fa-twitter"></i></a>
                            <a ><i class="fa fa-facebook"></i></a>
                            <a ><i class="fa fa-instagram"></i></a>
                            <a ><i class="fa fa-linkedin"></i></a>
                            <!--<a ><a id="userNameAfterLogin1" class="textlogin1" style="display: none"></a></a>-->
                            <a class="register_url" style='display: none' onclick='$("#sw").load("login.html")'><i class="fa fa-user menu-extra-mobile"></i></a>
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
                                <a  rel="home">
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
                                    <ul class="menu">
                                        <li class="home">
                                            <a href="index.jsp">صفحه اصلی</a>
                                        </li>
                                        <li class="has-mega-menu">
                                            <a class="has-mega" >پروژه های شرکت</a>
                                            <div class="submenu mega-menu" style="">
                                                <div class="row">
                                                    <div class="container">
                                                        <%
                                                            List<Map<String, Object>> subCategoryOfProjects = jjDatabase.separateRow(db.Select(Category_Content.tableName, Category_Content._parent + "=39"));
                                                            for (int o = 0; o < subCategoryOfProjects.size(); o++) {
                                                        %>
                                                        <div class="col-md-4">
                                                            <h2 style="text-align: right"><%=subCategoryOfProjects.get(o).get(Category_Content._title)%></h2>
                                                            <ul class="mega-menu-sub">
                                                                <%
                                                                    List<Map<String, Object>> row9 = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + subCategoryOfProjects.get(o).get(Category_Content._id)));
                                                                    for (int b = 0; b < row9.size(); b++) {
                                                                %>
                                                                <li><a ><i class="fa fa-flag"></i><%=row9.get(b).get(Content._title)%></a></li>
                                                                        <%}%>
                                                            </ul>
                                                        </div>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li><a  onclick="new jj('do=Content.sw&panel=sw&text=تیم شرکت&jj=1').jjAjax2()">تیم شرکت</a></li>
                                        <li><a  onclick="$('#sw').load('gallery.jsp')">گالری</a></li>
                                        <li><a >خدمات</a> </li>
                                        <li><a  onclick="new jj('do=Content.sw&panel=sw&text=درباره ما&jj=1').jjAjax2()">درباره ی شرکت </a></li>
                                        <li><a  onclick="new jj('do=Content.sw&panel=sw&text=تماس با ما&jj=1').jjAjax2()">تماس با ما</a></li>
                                    </ul>
                                </nav>
                            </div>
                            <ul class="menu menu-extra" style="">
                                <li class="off-canvas-toggle">
                                    <a ><i class="fa fa-bars"></i></a>
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
        <div id="sw">
            <%
                List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._token + "='" + user_token + "'" + " OR " + Access_User._id + "=" + jjTools.getSeassionUserId(request)));
                List<Map<String, Object>> projectMe = null;
                List<Map<String, Object>> news = null;
                List<Map<String, Object>> rowFactor = null;
                rowFactor = jjDatabase.separateRow(db.otherSelect("SELECT product_factor.*,product_factor_item_productId,product_factor_item_factorId FROM product_factor "
                        + " LEFT JOIN product_factor_item ON product_factor.id = product_factor_item.product_factor_item_factorId "
                        + " WHERE product_factor.product_factor_userId ='" + user.get(0).get(Access_User._id) + "' GROUP BY product_factor.id"));
                String groups = "";//گروه هایی که کاربر در آنها عضویت دارد
//                 List<Map<String, Object>> myProjects =  jjDatabase.separateRow(db.otherSelect("SELECT group_title FROM access_user_group"
//                        + "  LEFT JOIN access_group g ON group_id=g.id"
//                        + " WHERE user_id="+jjTools.getSeassionUserId(request)+""
//                        + " AND group_title LIKE 'اعضای پروژه ی%' ;"));
                List<Map<String, Object>> groupId = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "='" + user.get(0).get(Access_User._id) + "'"));
                List<Map<String, Object>> rowMessageTiket = jjDatabase.separateRow(db.otherSelect("SELECT hmis_Messenger.*," + Access_User._name + "," + Access_User._family
                        + " FROM `db_taavoni`.`hmis_Messenger` LEFT  JOIN Access_user a on messenger_sender=a.id WHERE (" + Messenger._receiver + "=" + user.get(0).get(Access_User._id) + " OR "
                        + Messenger._sender + "=" + user.get(0).get(Access_User._id) + ") AND " + Messenger._type + "='" + Messenger.message_Advice + "' GROUP BY  " + Messenger._chatID + " ORDER BY id desc"));
                System.out.println("groupId.size()" + groupId.size());
                if (!groupId.isEmpty()) {
                    for (int z = 0; z < groupId.size(); z++) {
                        groups += "'" + groupId.get(z).get(Access_Group_User._group_id).toString() + "'";
                        if (z < groupId.size() - 1) {// برای اینکه اخری را کاما نگذارد
                            groups += " ,";
                        }
                    }
                    List<Map<String, Object>> subPrivateCategoryOfProjects = jjDatabase.separateRow(db.Select(Category_Content.tableName, Category_Content._parent + "=41"));
                    String projectCategoriesCondition = Content._category_id + "=41 ";// آی دی دسته یندی هایی که زیر مجموعه ی دسته بندی پروژه های شرکت هستند تا یک سطح
                    for (int i = 0; i < subPrivateCategoryOfProjects.size(); i++) {
                        projectCategoriesCondition += " OR " + Content._category_id + "=" + subPrivateCategoryOfProjects.get(i).get(Category_Content._id).toString();
                    }
                    projectMe = jjDatabase.separateRow(db.Select(Content.tableName, Content._privateGroupId + " IN (" + groups + ")" + " AND (" + projectCategoriesCondition + ")"));
                    System.out.println(projectMe.size());
                    news = jjDatabase.separateRow(db.Select(News.tableName, News._privateGroupId + " IN (" + groups + ")"));
                    System.out.println(news.size());
                    //rowFactor = jjDatabase.separateRow(db.Select(Factor.tableName, Factor._userId + "='" + user.get(0).get(Access_User._id) + "'"));

                }
                List<Map<String, Object>> moveAnsersRow = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_formanswers "
                        + " WHERE (formanswers_questionId='186' or formanswers_questionId='185') AND formanswers_answer='" + jjTools.getSeassionUserId(request) + "'")// اگر در پاسخ به انتقال گیرنده آی دی 
                );
                System.out.println("________________________________________");
            %>
            <section id="main-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 pull-md-right">
                            <div class="header-lined">
                                <h3><%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%> ,خوش امدید</h3>
                                <ol class="breadcrumb">
                                    <li>
                                        <a href="userProfile.jsp">اعضا
                                        </a>        </li>
                                    <li class="active">
                                        ناحیه کاربری
                                    </li>
                                </ol>
                            </div>
                        </div>
                        <div class="col-md-3 pull-md-left sidebar" style="margin-left: 0px !important">
                            <div menuitemname="netafrazId" class="panel panel-sidebar sidebar--netafrazId">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="id">شماره عضویت<span><%=user.get(0).get(Access_User._int1)%></span></div>
                                </div>
                            </div>
                            <div menuitemname="Client Details" class="panel panel-sidebar panel-sidebar">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-user"></i>&nbsp;                اطلاعات شما
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <strong>تعاونی مسکن کارکنان دادگستری</strong><br/><em><%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%></em><br/><%=user.get(0).get(Access_User._address)%><br/><%=user.get(0).get(Access_User._postalCode)%><br/><%=user.get(0).get(Access_User._mobile)%>
                                </div>
                                <div class="panel-footer clearfix  portfolio-filter" >
                                    <a style="width: 100%;text-align: center" data-filter=".hammer"   class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0">
                                        <i class="fa fa-pencil-alt"></i> بروز رسانی
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- Container for main page display content -->
                        <div class="col-md-9 pull-md-right">
                            <div class="tiles clearfix">
                                <div class="row">
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".AllProject">
                                            <div class="icon"><i class="fa fa-cube"></i></div>                                               
                                                <%
                                                    if (projectMe == null || projectMe.size() == 0) {
                                                %>
                                            <div class="stat"><%=0%></div>
                                            <%} else {%>
                                            <div class="stat"><%=projectMe.size()%></div>
                                            <%}%>
                                            <div class="title">پروژه ها</div>
                                            <div class="highlight bg-color-blue"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".AllInformation">
                                            <div class="icon"><i class="fa fa-globe"></i></div>
                                                <%
                                                    if (news == null || news.size() == 0) {
                                                %>
                                            <div class="stat"><%=0%></div>
                                            <%} else {%>
                                            <div class="stat"><%=news.size()%></div>
                                            <%}%>
                                            <div class="title">اطلاعیه ها</div>
                                            <div class="highlight bg-color-green"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".AllSms">
                                            <div class="icon"><i class="fa fa-comments"></i></div>
                                                <%
                                                    System.out.println("rowFactor: " + rowFactor);
                                                    System.out.println("projectMe:" + projectMe);
                                                    System.out.println("news" + news);
                                                    if (rowMessageTiket == null || rowMessageTiket.size() == 0) {
                                                        System.out.println("^^^^^ 5:");
                                                %>
                                            <div class="stat">0</div>
                                            <%} else {%>
                                            <div class="stat"><%=rowMessageTiket.size()%></div>
                                            <%}%>
                                            <div class="title">تیکت ها</div>
                                            <div class="highlight bg-color-red"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".table-cancle">
                                            <div class="icon"><i class="fa fa-credit-card"></i></div>
                                                <%
                                                    if (rowFactor == null || rowFactor.size() == 0) {
                                                %>
                                            <div class="stat"><%=0%></div>
                                            <%} else {%>
                                            <div class="stat"><%=rowFactor.size()%></div>
                                            <%}%>
                                            <div class="title">صورت حساب ها</div>
                                            <div class="highlight bg-color-gold"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".allSaleAds">
                                            <div class="icon"><i class="fa fa-credit-card"></i></div>
                                            <div class="stat">-</div>
                                            <div class="title">درخواست ثبت آگهی فروش</div>
                                            <div class="highlight bg-color-gold"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".allMoveSession">
                                            <div class="icon"><i class="fa fa-credit-card"></i></div>
                                            <div class="stat">-</div>
                                            <div class="title">درخواست جلسه انتقال</div>
                                            <div class="highlight bg-color-gold"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".allNewProjects">
                                            <div class="icon"><i class="fa fa-credit-card"></i></div>
                                            <div class="stat">-</div>
                                            <div class="title">ثبت نام پروژه ی جدید</div>
                                            <div class="highlight bg-color-gold"></div>
                                        </a>
                                    </div>
                                    <div class="col-sm-3 col-xs-6 tile portfolio-filter" >
                                        <a  data-filter=".allmoveForm">
                                            <div class="icon"><i class="fa fa-credit-card"></i></div>
                                            <div class="stat"><%= moveAnsersRow.size()%></div>
                                            <div class="title">تاریخچه نقل و انتقالات</div>
                                            <div class="highlight bg-color-gold"></div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div id="swContent" ></div>
                            <div class="" >
                                <%
                                    List<Map<String, Object>> rowWelcome = jjDatabase.separateRow(db.Select(Content.tableName, Content._id + "=" + 2));
                                    for (int b = 0; b < rowWelcome.size(); b++) {
                                %>
                                <div class="col-lg-12 panel panel-default panel-accent-purple">
                                    <div>
                                        <a href="Server?do=Content.sw&panel=sw&text=<%=rowWelcome.get(b).get(Content._title)%>" target="_blank"><i class="fa fa-flag"></i><%=rowWelcome.get(b).get(Content._title)%></a>
                                        <p><%=rowWelcome.get(b).get(Content._explain)%></p>
                                        <a href="Server?do=Content.sw&panel=sw&text=<%=rowWelcome.get(b).get(Content._title)%>" target="_blank"><i class="fa fa-arrow-alt-left"></i> مشاهده</a>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                            <div class="AllProject  allTabs"  style="display: none">
                                <div class="col-xs-12 main-content">
                                    <h2>پروژه ها</h2>
                                    <%
                                        if (projectMe == null || projectMe.size() == 0) {
                                    %>
                                    <div class="announcement-single">
                                        <h3>
                                            <span class="label label-default">
                                            </span>
                                            در حال حاضر پروژه ای ندارید
                                        </h3>
                                        <blockquote>
                                            <p>
                                            </p>
                                        </blockquote>
                                    </div>
                                    <%} else {
                                        System.out.print("******6");
                                        for (int i = 0; i < projectMe.size(); i++) {
                                            jjCalendar_IR dateLable = new jjCalendar_IR(projectMe.get(i).get(Content._date).toString());
                                            String month = dateLable.getMonthName();
                                            int day = dateLable.getDay();
                                            int year = dateLable.getYear();
                                    %>
                                    <div class="announcement-single">
                                        <h3>
                                            <span class="label label-default">
                                                <%=day%><%=month%><%=year%>
                                            </span>
                                            <a  href="Server?do=Content.sw&panel=sw&text=<%=projectMe.get(i).get(Content._title)%>" target="_blank"><%=projectMe.get(i).get(Content._title)%></a>
                                        </h3>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                            </div>
                            <div class="AllInformation allTabs"  style="display: none">
                                <div class="col-xs-12 main-content">
                                    <h2>اطلاعیه ها</h2>
                                    <%
                                        if (news == null || news.size() == 0) {
                                            System.out.print(">>>>7" + projectMe + "//" + news + "//" + rowFactor);
                                    %>
                                    <div class="announcement-single">
                                        <h3>
                                            <span class="label label-default">
                                            </span>
                                            <a >در حال حاضر اطلاعیه ای ندارید</a>
                                        </h3>
                                        <blockquote>
                                            <p>
                                            </p>
                                        </blockquote>
                                    </div>
                                    <%} else {%>
                                    <%
                                        for (int i = 0; i < news.size(); i++) {
                                            jjCalendar_IR dateLableNews = new jjCalendar_IR(news.get(i).get(News._date).toString());
                                            String monthNews = dateLableNews.getMonthName();
                                            int dayNews = dateLableNews.getDay();
                                            int yearNews = dateLableNews.getYear();
                                    %>
                                    <div class="announcement-single">
                                        <h3>
                                            <span class="label label-default">
                                                <%=dayNews%><%=monthNews%><%=yearNews%>
                                            </span>
                                            <a   onclick="selectNews(<%=news.get(0).get(News._id)%>)" ><%=news.get(0).get(News._title)%></a>
                                        </h3>
                                        <blockquote>
                                            <p>
                                                <%=news.get(0).get(News._content)%>
                                            </p>
                                        </blockquote>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                            </div>
                            <div class="AllSms allTabs"  style="display: none">
                                <div class="col-xs-12">
                                    <div class="card rounded-0" id='ticketTable'>
                                        <button class="btn btn-success pd-sm-x-20 mg-sm-r-5 col-lg-3" style="color: white;" onclick="add_newTicket();"> ثبت تیکت جدید</button>
                                        <table id="ticketDataTable"  style="width:100%;">
                                            <thead class="card-header bg-primary tx-white">
                                                <tr>
                                                    <th>موضوع</th>
                                                    <th> فرستنده</th>
                                                    <th>تاریخ</th>
                                                    <th>وضعیت</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (int i = 0; i < rowMessageTiket.size(); i++) {
                                                %>
                                                <tr onclick="selectTicket(<%=rowMessageTiket.get(i).get(Messenger._chatID)%>);" style="cursor: pointer">
                                                    <td><%=rowMessageTiket.get(i).get(Messenger._title)%><br/>#<%=rowMessageTiket.get(i).get(Messenger._chatID)%></td>
                                                    <td><%=rowMessageTiket.get(i).get(Access_User._name) + " " + rowMessageTiket.get(i).get(Access_User._family)%> </td>
                                                    <td><%= jjCalendar_IR.getViewFormat(rowMessageTiket.get(i).get(Messenger._postageDate).toString())%>
                                                        (<%=jjCalendar_IR.getViewFormatTime_8length(rowMessageTiket.get(i).get(Messenger._time).toString())%>) </td>
                                                    <td><%=rowMessageTiket.get(i).get(Messenger._status)%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div id="newTicket" class="row row-sm" style="display: none" >
                                    <div class="col-lg-12">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card rounded-0">
                                                    <div id="message_chat">
                                                    </div><!-- card-header -->
                                                    <div class="card-header bg-primary tx-white">
                                                        ارسال تیکت
                                                    </div><!-- card-header -->
                                                    <div class="card-body" id='formMassege'>
                                                        <div id="messengerForm">
                                                            <input type="hidden" id="messenger_id" name="id" />
                                                            <input type="hidden" id="messenger_chatID" name="messenger_chatID" />
                                                            <div class="row" id="ticketHeader">
                                                                <div class="col-lg" style="">
                                                                    عنوان
                                                                    <input class="form-control" id="messenger_title" name="messenger_title"  placeholder="عنوان پیام" type="text" />
                                                                    <input id="messenger_type" name="messenger_type"  type="hidden" value="پشتیبانی"/>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-lg-3 col-sm-6">
                                                                        بخش
                                                                        <div class="form-group" style=" margin-bottom: 20px;" >
                                                                            <select id="messenger_receiver" name="messenger_receiver"  class="form-control" >
                                                                                <option value="2619">امور اداری</option>
                                                                                <option value="3939">امور مالی</option>
                                                                                <option value="1532">مدیریت</option>
                                                                                <option value="1782">ریس هیات مدیره</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-lg-3 col-sm-6">
                                                                        فرستنده :
                                                                        <input   class="form-control" value="<%= jjTools.getSeassionUserNameAndFamily(request)%>" type="text"  disabled="disabled" />
                                                                        <input   class="form-control" id="messenger_sender" name="messenger_sender"  value="<%= jjTools.getSeassionUserId(request)%>" type="hidden"/>
                                                                    </div>
                                                                    <div class="col-lg-3 col-sm-6">
                                                                        تاریخ ارسال
                                                                        <input class="form-control" id="messenger_postageDate"   disabled="disabled"
                                                                               value="<%=jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))%>"  />
                                                                    </div>
                                                                    <div class="col-lg-3 col-sm-6" id="status">
                                                                        وضعیت
                                                                        <input type="text" id="messenger_status" name="messenger_status"   class="form-control"  disabled="disabled"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-lg-12" style="">
                                                                    متن پیام
                                                                    <textarea  class="form-control" id="messenger_textMessage" name="messenger_textMessage"  rows="7" ></textarea>
                                                                </div>
                                                            </div>
                                                            <div id="filePdfLoader" class="col-lg-12 mg-t-20" style="">
                                                                <div class="col-lg-12">پیوست کردن فایل
                                                                    <div class="" id="showFileMessengerDiv"></div>
                                                                </div>
                                                                <div class="input-group col-lg-12">
                                                                    <div class=""> بهتر است عنوان فایل را متناسب با محتوای آن انتخاب کنید</div>
                                                                    <input class="form-control" id="messenger_titleFile" placeholder="فایل شما با این عنوان در سامانه ذخیره میشود" type="text" />
                                                                    <input id="attachFileMessenger" name="attachFileMessenger" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());
                                                                            var temp = $(this).val();
                                                                            temp = temp.replace(/.*:/ig, '');
                                                                            temp = temp.replace(/\\.*\\/ig, '');
                                                                            $(this).parent().find('input[type=text]').val(temp);
                                                                           " style="display: none;" type="file" />
                                                                    <span id="sendFileMessenger" class="btn flat-button button-color button-normal green">ارسال فایل ری سرور<i class="fa fa-upload"></i></span>
                                                                    <span class="btn btn-primary" onclick=" $(this).parent().find('input[type=file]').click();">انتخاب فایل</span>
                                                                </div>
                                                                <div class="inputAfterSelectManager" ></div>
                                                            </div>
                                                            <div class="col-lg-4" id="sendingMetod"></div>
                                                            <!--                                                            <div class="col-lg-4" style="" id="logStatus" >
                                                                                                                            فرایند تیکت :
                                                                                                                            <div style="display: initial;" onclick="if ($('#messenger_logStatusDiv').css('display') == 'none') {
                                                                                                                                        $('#messenger_logStatusDiv').show('fast');
                                                                                                                                    } else {
                                                                                                                                        $('#messenger_logStatusDiv').hide('fast');
                                                                                                                                    }">
                                                                                                                                <img src="template/add_row.png" alt="Log"/>
                                                                                                                            </div>
                                                                                                                            <div id="messenger_logStatusDiv" style="display: none;">
                                                                                                                                <textarea id="messenger_logStatus" name="messenger_logStatus"  class="tahoma10" style="width: 99.8%;height: 260px;text-align: right" disabled="disabled">
                                                                                                                                </textarea>
                                                                                                                            </div>
                                                                                                                        </div>-->
                                                        </div>
                                                        <div class="row col-lg-12">
                                                            <button class="col-lg-4 flat-button button-color button-normal black" onclick="$('#newTicket').slideUp();$('#ticketTable').show();">بازگشت </button>
                                                            <button class="col-lg-6 flat-button button-color button-normal blue" onclick="sendTicket();">ارسال </button>
                                                        </div><!-- card -->
                                                    </div><!-- card -->
                                                </div><!-- col -->
                                            </div><!-- col -->
                                        </div>
                                    </div><!-- col-8 -->
                                </div><!-- col-8 -->
                            </div>
                            <div class="AllPaid allTabs"  style="display: none"></div>
                            <div class="table-paid card rounded-0" style="display: none">
                                <table id="factorTable" class="uk-table uk-table-hover uk-table-striped " style="width:100%;">
                                    <thead>
                                        <tr>
                                            <th>صورت حساب</th>
                                            <th>شماره صورتحساب</th>
                                            <th>تاریخ صورت حساب</th>
                                            <th>مبلغ</th>
                                            <th>وضعیت</th>
                                            <th>مشاهده</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% if (rowFactor != null) {
                                                for (int i = 0; i < rowFactor.size(); i++) {
                                                    String statusePaid = Factor.lbl_statuseUnPaid;
                                                    if (rowFactor.get(i).get(Factor._statuse).toString().equals(statusePaid)) {
                                        %>
                                        <%jjCalendar_IR dateLablePaid = new jjCalendar_IR(rowFactor.get(i).get(Factor._date).toString());
                                            System.out.println(".................--------------------..");
                                            System.out.println(rowFactor.get(i).get(Factor._id));
                                            String monthPaid = dateLablePaid.getMonthName();
                                            int dayPaid = dateLablePaid.getDay();
                                            int yearPaid = dateLablePaid.getYear();
//                                            List<Map<String, Object>> nameProject = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + rowFactor.get(i).get(Factor._id)) );%>
                                        <tr >
                                            <td><%=rowFactor.get(0).get(Factor._discription)%></td>
                                            <td><%=rowFactor.get(i).get(Factor._serialNumber)%></td>
                                            <td><%=dayPaid%><%=monthPaid%><%=yearPaid%></td>
                                            <td><%=rowFactor.get(i).get(Factor._totalAmount)%></td>
                                            <td><%=rowFactor.get(i).get(Factor._statuse)%></td>
                                            <td><a href="bills.jsp?user_bills=<%=rowFactor.get(i).get(Factor._serialNumber)%>"><i class="fa fa-cog cog-table"></i></a></td>
                                        </tr><%
                                                }
                                            }
                                        } else {
                                            System.out.print(">>>>3" + projectMe + "//" + news + "//" + rowFactor);%>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr><%}%>
                                    </tbody>
                                </table></div>
                            <div class="table-unPaid allTabs"  style="display: none">
                                <table id="example1" class="uk-table uk-table-hover uk-table-striped table-unPaid" style="width:100%;">
                                    <thead>
                                        <tr>
                                            <th>صورت حساب</th>
                                            <th>شماره صورتحساب</th>
                                            <th>تاریخ صورت حساب</th>
                                            <th>مبلغ</th>
                                            <th>وضعیت</th>
                                            <th>مشاهده</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%if (rowFactor != null) {
                                                for (int x = 0; x < rowFactor.size(); x++) {
                                                    String statuse = rowFactor.get(x).get(Factor._statuse).toString();
                                                    String statuseUnPaid = Factor.lbl_statuseUnPaid;
                                                    if (rowFactor.get(x).get(Factor._statuse).toString().equals(statuseUnPaid)) {
                                                        System.out.print("%$%$%$%$%$%$" + rowFactor.get(x).get(Factor._statuse).toString() + Factor.lbl_statuseUnPaid);
                                        %>
                                        <%      jjCalendar_IR dateLableUnPaid = new jjCalendar_IR(rowFactor.get(x).get(Factor._date).toString());
                                            String monthUnPaid = dateLableUnPaid.getMonthName();
                                            int dayUnPaid = dateLableUnPaid.getDay();
                                            int yearUnPaid = dateLableUnPaid.getYear();
                                            System.out.print("%$%$%$%$%$%$87687");
                                            System.out.println("...........^^^^........");
                                            List<Map<String, Object>> nameProject2 = jjDatabase.separateRow(db.Select(Product.tableName, Content._id + "=" + rowFactor.get(x).get(FactorItem._productId)));%>
                                        <tr >
                                            <td><%=rowFactor.get(x).get(Factor._discription)%></td>
                                            <td><%=rowFactor.get(x).get(Factor._serialNumber)%></td>
                                            <td><%=dayUnPaid%><%=monthUnPaid%><%=yearUnPaid%></td>
                                            <td><%=rowFactor.get(x).get(Factor._totalAmount)%></td>
                                            <td><%=rowFactor.get(x).get(Factor._statuse)%></td>
                                            <td><a href="bills.jsp?user_bills=<%=rowFactor.get(x).get(Factor._serialNumber)%>"><i class="fa fa-cog cog-table"></i></a></td>
                                        </tr><%
                                                }
                                            }
                                        } else {
                                            System.out.print(">>>>4" + projectMe + "//" + news + "//" + rowFactor);%>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr><%}%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>صورت حساب</th>
                                            <th>شماره صورتحساب</th>
                                            <th>تاریخ صورت حساب</th>
                                            <th>مبلغ</th>
                                            <th>وضعیت</th>
                                            <th>مشاهده</th>
                                        </tr>
                                    </tfoot>
                                </table></div>
                            <div class="table-cancle allTabs"  style="display: none">
                                <table id="example1" class="uk-table uk-table-hover uk-table-striped" style="width:100%;">
                                    <thead>
                                        <tr>
                                            <th>صورت حساب</th>
                                            <th>شماره صورتحساب</th>
                                            <th>تاریخ صورت حساب</th>
                                            <th>مبلغ</th>
                                            <th>وضعیت</th>
                                            <th>مشاهده</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%if (rowFactor != null) {
                                                for (int x = 0; x < rowFactor.size(); x++) {
                                                    String statuse = rowFactor.get(x).get(Factor._statuse).toString();
                                                    String statuseUnPaid = Factor.lbl_statuseUnPaid;
                                                    System.out.print("...././.>>>>/........" + rowFactor.get(x).get(Factor._statuse).toString() + Factor.lbl_statuseUnPaid);
                                        %>
                                        <%      jjCalendar_IR dateLableUnPaid = new jjCalendar_IR(rowFactor.get(x).get(Factor._date).toString());
                                            String monthUnPaid = dateLableUnPaid.getMonthName();
                                            int dayUnPaid = dateLableUnPaid.getDay();
                                            int yearUnPaid = dateLableUnPaid.getYear();
                                            System.out.print("%$%$%$%$%$%78777");
                                            List<Map<String, Object>> nameProject2 = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactor.get(x).get(FactorItem._productId)));%>
                                        <tr >
                                            <td><%=rowFactor.get(x).get(Factor._discription)%></td>
                                            <td><%=rowFactor.get(x).get(Factor._serialNumber)%></td>
                                            <td><%=dayUnPaid%><%=monthUnPaid%><%=yearUnPaid%></td>
                                            <td><%=rowFactor.get(x).get(Factor._totalAmount)%></td>
                                            <td><%=rowFactor.get(x).get(Factor._statuse)%></td>
                                            <td><a href="bills.jsp?user_bills=<%=rowFactor.get(x).get(Factor._serialNumber)%>"><i class="fa fa-cog cog-table"></i></a></td>
                                        </tr><%
                                            }
                                        } else {
                                            System.out.print(">>>>4" + projectMe + "//" + news + "//" + rowFactor);%>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr><%}%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="allMoveSession allTabs"  style="display: none">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card rounded-0">
                                                <div id="message_chat_move">
                                                </div><!-- card-header -->
                                                <div class="card-header bg-primary tx-white">
                                                    برای انتقال امتیاز یا ملک شما باید جلسه ای در تعاونی هماهنگ شود، برای هماهنگی درخواست خود را ثبت کنید
                                                </div><!-- card-header -->
                                                <div class="card-body" id="formMassege_move">
                                                    <div id="messengerForm_move">
                                                        <input type="hidden" id="messenger_id_move" name="id" />
                                                        <input type="hidden" id="messenger_chatID_move" name="messenger_chatID" />
                                                        <div class="row" id="ticketHeader_move">
                                                            <input class="form-control" id="messenger_title_move" name="messenger_title" type="hidden" value="درخواست جلسه ی انتقال" />
                                                            <input id="messenger_type_move" name="messenger_type"  type="hidden" value="درخواست جلسه ی انتقال"/>
                                                            <div class="row">
                                                                <div class="col-lg-3 col-sm-6">
                                                                    بخش
                                                                    <div class="form-group" style=" margin-bottom: 20px;" >
                                                                        <select id="messenger_receiver_move" name="messenger_receiver"  class="form-control" >
                                                                            <option value="2619">امور اداری</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6">
                                                                    فرستنده :
                                                                    <input   class="form-control" value="<%= jjTools.getSeassionUserNameAndFamily(request)%>" type="text"  disabled="disabled" />
                                                                    <input   class="form-control" id="messenger_sender_move" name="messenger_sender"  value="<%= jjTools.getSeassionUserId(request)%>" type="hidden"/>
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6">
                                                                    تاریخ ارسال
                                                                    <input class="form-control" id="messenger_postageDate_move"   disabled="disabled"
                                                                           value="<%=jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))%>"  />
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6" id="status_move">
                                                                    وضعیت
                                                                    <input type="text" id="messenger_status_move" name="messenger_status"   class="form-control"  disabled="disabled"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-12" style="">
                                                                متن پیام
                                                                <textarea  class="form-control" id="messenger_textMessage_move" name="messenger_textMessage"  rows="7" ></textarea>
                                                            </div>
                                                        </div>
                                                        <div id="filePdfLoader_move" class="col-lg-12 mg-t-20" style="">
                                                            <div class="col-lg-12">پیوست کردن فایل
                                                                <div class="" id="showFileMessengerDiv_move"></div>
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <div class=""> بهتر است عنوان فایل را متناسب با محتوای آن انتخاب کنید</div>
                                                                <input class="form-control" id="messenger_titleFile_move" placeholder="فایل شما با این عنوان در سامانه ذخیره میشود" type="text" />
                                                                <input id="attachFileMessenger_move" name="attachFileMessenger" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());" style="display: none;" type="file" />
                                                                <span id="sendFileMessenger_move" class="btn flat-button button-color button-normal green">ارسال فایل ری سرور<i class="fa fa-upload"></i></span>
                                                                <span class="btn btn-primary" onclick=" $(this).parent().find('input[type=file]').click();">انتخاب فایل</span>
                                                            </div>
                                                            <div class="inputAfterSelectManager" ></div>
                                                        </div>
                                                        <div class="col-lg-4" id="sendingMetod_move"></div>
                                                    </div>
                                                    <div class="row col-lg-12">
                                                        <button class="col-lg-4 flat-button button-color button-normal black" onclick="$('.allMoveSession').slideUp();">بازگشت </button>
                                                        <button class="col-lg-6 flat-button button-color button-normal blue" onclick="sendTicket_move();">ارسال </button>
                                                    </div><!-- card -->
                                                </div><!-- card -->
                                            </div><!-- col -->
                                        </div><!-- col -->
                                    </div>
                                </div><!-- col-8 -->
                            </div>
                            <div class="allNewProjects allTabs"  style="display: none">
                                <div class="card-header bg-primary tx-white">
                                    در حال حاضر پروژه ی فعالی برای ثبت نام وحود ندارد
                                </div>
                            </div>
                            <div class="allmoveForm allTabs"  style="display: none">
                                <div class="card-header bg-primary tx-white">
                                    نقل و انتقالی هایی که برای شما ثبت شده است
                                </div>
                                <%
                                    if (!moveAnsersRow.isEmpty()) {
                                        for (int z = 0; z < moveAnsersRow.size(); z++) {
                                            List<Map<String, Object>> ansersForOneMoveForm = jjDatabase.separateRow(db.otherSelect("SELECT * FROM hmis_formanswers  "
                                                    + " WHERE  formanswers_answerSet_id='" + moveAnsersRow.get(z).get(FormAnswers._answerSet_id) + "'  ;"));

                                %>
                                <table  style="width:90%;margin:  5px 5%;">
                                    <thead>
                                        <tr>
                                            <th>عنوان پروژه ی انتقال یافته</th>
                                            <th>تاریخ انتقال</th>
                                            <th>انتقال دهنده</th>
                                            <th>انتقال گیرنده</th>
                                            <th>پیوست</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>

                                            <td><%= ansersForOneMoveForm.get(0).get(FormAnswers._answer).toString()%></td>                                    
                                            <td><%= ansersForOneMoveForm.get(3).get(FormAnswers._answer).toString()%></td>                                 
                                            <td style="color: red"><%= Access_User.getUserName(ansersForOneMoveForm.get(1).get(FormAnswers._answer).toString(), db)%></td>                                    
                                            <td style="color: green"><%= Access_User.getUserName(ansersForOneMoveForm.get(2).get(FormAnswers._answer).toString(), db)%></td>                                    
                                            <td><%= ansersForOneMoveForm.get(4).get(FormAnswers._answer).toString().isEmpty() ? ""
                                                    : ("<a href='upload/" + ansersForOneMoveForm.get(4).get(FormAnswers._answer).toString() + "' target='_blank' >download</a>")%> </td>                                    
                                        </tr>
                                    </tbody>
                                </table>
                                <br/>
                                <%
                                        }
                                    }
                                %>
                            </div>
                            <div class="allSaleAds allTabs"  style="display: none">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card rounded-0">
                                                <div id="message_chat_sale">
                                                </div><!-- card-header -->
                                                <div class="card-header bg-primary tx-white">
                                                    ارسال درخواست ثبت ملک/امتیاز شما در سایت تعاونی
                                                </div><!-- card-header -->
                                                <div class="card-body" id="formMassege_sale">
                                                    <div id="messengerForm_sale">
                                                        <input type="hidden" id="messenger_id_sale" name="id" />
                                                        <input type="hidden" id="messenger_chatID_sale" name="messenger_chatID" />
                                                        <div class="row" id="ticketHeader_sale">
                                                            <input class="form-control" id="messenger_title_sale" name="messenger_title"  type="hidden"  value="درخواست ثبت آگهی فروش"/>
                                                            <input id="messenger_type_sale" name="messenger_type"  type="hidden" value="درخواست ثبت آگهی فروش"/>
                                                            <div class="row">
                                                                <div class="col-lg-3 col-sm-6">
                                                                    بخش
                                                                    <div class="form-group" style=" margin-bottom: 20px;" >
                                                                        <select id="messenger_receiver_sale" name="messenger_receiver"  class="form-control" >
                                                                            <option value="2619">امور اداری</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6">
                                                                    فرستنده :
                                                                    <input   class="form-control" value="<%= jjTools.getSeassionUserNameAndFamily(request)%>" type="text"  disabled="disabled" />
                                                                    <input   class="form-control" id="messenger_sender_sale" name="messenger_sender"  value="<%= jjTools.getSeassionUserId(request)%>" type="hidden"/>
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6">
                                                                    تاریخ ارسال
                                                                    <input class="form-control" id="messenger_postageDate_sale"   disabled="disabled"
                                                                           value="<%=jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))%>"  />
                                                                </div>
                                                                <div class="col-lg-3 col-sm-6" id="status_sale">
                                                                    وضعیت
                                                                    <input type="text" id="messenger_status_sale" name="messenger_status"   class="form-control"  disabled="disabled"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-12" style="">
                                                                متن پیام
                                                                <textarea  class="form-control" id="messenger_textMessage_sale" name="messenger_textMessage"  rows="7" ></textarea>
                                                            </div>
                                                        </div>
                                                        <div id="filePdfLoader_sale" class="col-lg-12 mg-t-20" style="">
                                                            <div class="col-lg-12">پیوست کردن فایل
                                                                <div class="" id="showFileMessengerDiv_sale"></div>
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <div class=""> بهتر است عنوان فایل را متناسب با محتوای آن انتخاب کنید</div>
                                                                <input class="form-control" id="messenger_titleFile_sale" placeholder="فایل شما با این عنوان در سامانه ذخیره میشود" type="text" />
                                                                <input id="attachFileMessenger_sale" name="attachFileMessenger" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());
                                                                        var temp = $(this).val();
                                                                        temp = temp.replace(/.*:/ig, '');
                                                                        temp = temp.replace(/\\.*\\/ig, '');
                                                                        $(this).parent().find('input[type=text]').val(temp);
                                                                       " style="display: none;" type="file" />
                                                                <span id="sendFileMessenger_sale" class="btn flat-button button-color button-normal green">ارسال فایل ری سرور<i class="fa fa-upload"></i></span>
                                                                <span class="btn btn-primary" onclick=" $(this).parent().find('input[type=file]').click();">انتخاب فایل</span>
                                                            </div>
                                                            <div class="inputAfterSelectManager" ></div>
                                                        </div>
                                                        <div class="col-lg-4" id="sendingMetod_sale"></div>
                                                    </div>
                                                    <div class="row col-lg-12">
                                                        <button class="col-lg-4 flat-button button-color button-normal black" onclick="$('.allSaleAds').slideUp();">بازگشت </button>
                                                        <button class="col-lg-6 flat-button button-color button-normal blue" onclick="sendTicket_sale();">ارسال </button>
                                                    </div><!-- card -->
                                                </div><!-- card -->
                                            </div><!-- col -->
                                        </div><!-- col -->
                                    </div>
                                </div><!-- col-8 -->
                            </div>
                            <!--edit info-->
                            <div id="AccessuserForm" class="hammer" style="display: none;">
                                <input type="hidden" name="token" value="" />
                                <div class="row">
                                    <div class="col-sm-12 col-xs-12 pull-left">
                                        <div class="form-group">
                                            <div class="col-lg-2" style=""></div>
                                            <div class="col-lg-8" style="">
                                                <div>
                                                    عکس پرسنلی
                                                </div>
                                                <input id="user_attachPicPersonal" name="user_attachPicPersonal" type="hidden">
                                                    <img id="PicPreviewPersonal" name="PicPreviewPersonal" class="img-thumbnail" src="img/profile_pre.png" style="width: 200px;height: 200px" />
                                                    <div class="row">
                                                        <input class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" id="sendPic1" type="submit" value="ارسال" />
                                                        <span class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" onclick="$(this).parent().find('input[type=file]').click();">انتخاب فایل</span>
                                                        <input class="form-control" id="user_file_personal" name="user_file_personal" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());" style="display: none;" type="file">
                                                            <span id="user_pic1" class="form-control" /></span>
                                                            <!--<span class="form-control" /></span>-->
                                                    </div>
                                                    <a id="DownloadPicPersonal"  style="display: none" target="_blank" download="">
                                                        دانلود عکس پرسنلی
                                                    </a>
                                            </div><div class="col-lg-2" style=""></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="hidden" id="access_user_id" name="id" value="<%=user.get(0).get(Access_User._id)%>" />
                                        <div class="form-group">
                                            <label for="inputFirstName" class="control-label">نام</label>
                                            <input type="text" id="user_name" name="user_name" value="<%=user.get(0).get(Access_User._name)%>" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="inputLastName" class="control-label">نام خانوادگی</label>
                                            <input type="text"  id="user_family" name="user_family" value="<%=user.get(0).get(Access_User._family)%>" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="inputCompanyName" class="control-label">پسورد</label>
                                            <input type="text" id="user_pass"  name="user_pass" value="<%=user.get(0).get(Access_User._pass)%>" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label">آدرس ایمیل</label>
                                            <input type="text" id="user_email" name="user_email" value="<%=user.get(0).get(Access_User._email)%>" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-xs-12 pull-right">
                                        <div class="form-group">
                                            <label for="inputAddress1" class="control-label">آدرس </label>
                                            <input type="text"  id="user_address" name="user_address" value="<%=user.get(0).get(Access_User._address)%>" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAddress2" class="control-label">کدپستی</label>
                                            <input type="text"  id="user_postalCode" name="user_postalCode"  value="<%=user.get(0).get(Access_User._postalCode)%>" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-xs-12 pull-left">
                                        <div class="form-group">
                                            <label class="control-label" for="customfield2">تلفن همراه </label>
                                            <div class="control">
                                                <input type="text"  id="user_mobile" name="user_mobile" size="30"  value="<%=user.get(0).get(Access_User._mobile)%>" class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-xs-12 pull-left">
                                        <div class="form-group">
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-lg-12">اسکن پرونده شما
                                                    <div class="" id="user_attachFileDiv">
                                                        <%
                                                            String userFiles[] = user.get(0).get(Access_User._attachFile).toString().split(",");
                                                            for (int k = 0; k < userFiles.length; k++) {
                                                        %>
                                                        <a href="upload/<%= userFiles[k]%>" >
                                                            <img class="" src="upload/<%= userFiles[k]%>" style="max-height: 200px" target='_blank' /> دانلود
                                                        </a>
                                                        <%
                                                            }
                                                        %>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">فایل هایی که شما پیوست کرده اید
                                                    <div class="" id="user_attachFileUserDiv">
                                                        <%
                                                            String userFilesUser[] = user.get(0).get(Access_User._attachFileUser).toString().split(",");
                                                            for (int k = 0; k < userFilesUser.length; k++) {
                                                        %>
                                                        <div class='col-lg-12 mg-l-15'>
                                                            <img class='wd-40 rounded-circle mg-r-20' src='upload/<%=userFilesUser[k]%>' />
                                                            <a target='_blank' href='upload/"<%=userFilesUser[k]%>"'>دانلود</a>
                                                            <input class='<%= Access_User._attachFileUser%>' type='hidden' value='<%=userFilesUser[k]%>'>
                                                                <div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i></div>
                                                        </div>
                                                        <%
                                                            }
                                                        %>
                                                    </div>
                                                </div>
                                                <div class="input-group col-lg-12" style="padding: 14px;">
                                                    <div class=""> بهتر است عنوان فایل را متناسب با محتوای آن انتخاب کنید</div>
                                                    <span id="user_pic" class="form-control" style="display: none"></span>
                                                    <input class="form-control" id="user_titleFile" placeholder="فایل شما با این عنوان در سامانه ذخیره میشود" type="text" />
                                                    <input id="attachFileUser" name="attachFileUser" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());
                                                            var temp = $(this).val();
                                                            temp = temp.replace(/.*:/ig, '');
                                                            temp = temp.replace(/\\.*\\/ig, '');
                                                            $(this).parent().find('input[type=text]').val(temp);
                                                           " style="display: none;" type="file" /><!--فایل انتخاب شده از بروزر-->
                                                    <input class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" id="userAttachFiles_sendFiles" style="margin: 5px" type="submit" value="ارسال"/>
                                                    <span class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" style="margin: 5px" onclick="$(this).parent().find('input[type=file]').click();">انتخاب فایل</span>
                                                    <!--<span class="form-control" /></span>-->
                                                    <a   id="DownloadAttachFile"  style="display: none" target="_blank" download>
                                                        دانلود
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <button style="text-align: center" type="submit" class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" onclick="cmsUser.m_editUser()">ذخیره تغییرات</button>
                                </div>
                            </div>
                            <!--end edit info-->
                            <div class="client-home-panels All"  style="display: none">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div menuitemname="Active Products/Services" class="panel panel-default panel-accent-gold">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">
                                                    <i class="fa fa-cube"></i>پروژه ها
                                                </h3>
                                            </div>
                                            <div class="list-group">
                                                <%
                                                    if (projectMe == null || projectMe.size() == 0) {
                                                %>
                                                <a menuitemname="0"  class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-0">
                                                    <p>شما در حال حاضر عضو پروژه ای نشده اید</p>
                                                </a>
                                                <%} else {
                                                    for (int i = 0; i < projectMe.size(); i++) {
                                                        jjCalendar_IR dateLable = new jjCalendar_IR(projectMe.get(i).get(Content._date).toString());
                                                        String month = dateLable.getMonthName();
                                                        int day = dateLable.getDay();
                                                        int year = dateLable.getYear();
                                                %>
                                                <a menuitemname="1" href="Server?do=Content.sw&panel=sw&text=<%=projectMe.get(i).get(Content._title)%>" target="_blank" class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-1">
                                                    <%=projectMe.get(i).get(Content._title)%><br/><span class="text-last-updated"><%=day%><%=month%><%=year%></span>
                                                </a>
                                                <%}%>
                                                <%}%>
                                            </div>
                                            <div class="panel-footer">
                                            </div>
                                        </div>
                                        <div menuitemname="Register a New Domain" class="panel panel-default panel-accent-emerald">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">
                                                    <i class="fa fa-globe"></i>اطلاعیه ها
                                                </h3>
                                            </div>
                                            <div class="list-group">
                                                <%
                                                    if (news == null || news.size() == 0) {
                                                        System.out.print(">>>>711" + projectMe + "//" + news + "//" + rowFactor);
                                                %>
                                                <a menuitemname="0"  class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-0">
                                                    <p>در حال حاضر اطلاعیه ای برای شما وجود ندارد</p>
                                                </a>
                                                <%} else {%>
                                                <%System.out.print(">>>>8" + projectMe + "//" + news + "//" + rowFactor);
                                                    for (int i = 0; i < news.size(); i++) {
                                                        jjCalendar_IR dateLableNews = new jjCalendar_IR(news.get(i).get(News._date).toString());
                                                        String monthNews = dateLableNews.getMonthName();
                                                        int dayNews = dateLableNews.getDay();
                                                        int yearNews = dateLableNews.getYear();
                                                %>
                                                <a menuitemname="1" onclick="selectNews(<%=news.get(0).get(News._id)%>)" class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-1">
                                                    <%=news.get(0).get(News._title)%><br/><span class="text-last-updated"><%=dayNews%><%=monthNews%><%=yearNews%></span>
                                                </a>
                                                <%}%>
                                                <%}%>
                                            </div>
                                            <div class="panel-footer">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div menuitemname="Recent Support Tickets" class="panel panel-default panel-accent-blue">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">
                                                    <i class="fa fa-comments"></i>پیام های شما
                                                </h3>
                                            </div>
                                            <div class="list-group">
                                                <a menuitemname="0" href="/announcements/92/----SSL--.html" class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-0">
                                                    پیامی در حال حاضر ندارید<br/><span class="text-last-updated">1400/11/08</span>
                                                </a>
                                            </div>
                                            <div class="panel-footer">
                                            </div>
                                        </div>
                                        <div menuitemname="Recent News" class="panel panel-default panel-accent-asbestos">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">
                                                    <i class="fa fa-file"></i>فایل های شما
                                                </h3>
                                            </div>
                                            <div class="list-group">
                                                <%
                                                    if (user.get(0).get(Access_User._attachFile) == "") {
                                                        System.out.print(">>>>1111" );
                                                %>
                                                <a menuitemname="0"  class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-0">
                                                    <p>فایلی برای شما اپلود نشده است</p>
                                                </a>
                                                <%} else {%>
                                                <% 
                                                    String attachFiles = user.get(0).get(Access_User._attachFile).toString();
                                                    String[] attachFilesArray = attachFiles.split(",");
                                                    for (int l = 0; l < attachFilesArray.length; l++) {
                                                        List<Map<String, Object>> fileRow = jjDatabase.separateRow(db.Select(UploadServlet.tableName, UploadServlet._file_name + "='" + attachFilesArray[l] + "'"));
                                                        if (!fileRow.isEmpty()) {
                                                            String idUpload = fileRow.get(0).get(UploadServlet._id).toString();
                                                            String titleUpload = fileRow.get(0).get(UploadServlet._title).toString();
                                                            String extension2 = attachFilesArray[l].substring(attachFilesArray[l].lastIndexOf(".") + 1, attachFilesArray[l].length());
                                                            if (extension2.toLowerCase().equals("jpg")
                                                                    || extension2.toLowerCase().equals("png")
                                                                    || extension2.toLowerCase().equals("gif")
                                                                    || extension2.toLowerCase().equals("svg")) {
                                                %><a menuitemname="1" href='upload/<%=attachFilesArray[l]%>' class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-1">
                                                    <%=attachFilesArray[l]%><br/><span class="text-last-updated"></span>
                                                </a><%
                                                } else {
                                                %><a menuitemname="1" href='upload/<%=attachFilesArray[l]%>' class="list-group-item" id="ClientAreaHomePagePanels-Recent_News-1">
                                                    <%=attachFilesArray[l]%><br/><span class="text-last-updated"></span>
                                                </a><%
                                                                }
                                                            } else {
                                                                //@ToDo  //کی از فایل ها اشتباها از سامانه حذف شده
                                                            }
                                                        }
                                                    }
                                                %>
                                            </div>
                                            <div class="panel-footer">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.main-content -->
                        <div class="col-md-3 pull-md-left sidebar sidebar-secondary"  style="margin-left: 0px !important">
                            <div menuitemname="Client Contacts" class="panel panel-sidebar panel-sidebar">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-bookmark"></i>&nbsp;          داشبورد
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="list-group  portfolio-filter">
                                    <a  data-filter=".All"   class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        &nbsp;                            داشبورد
                                    </a>
                                </div>
                                <div class="panel-footer clearfix panel-footer clearfix ">
                                    <a style="width: 100%;text-align: center" href="index.jsp" onclick="signOut()" class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0">
                                        خروج
                                    </a>
                                </div>
                            </div>
                            <div menuitemname="Client Shortcuts" class="panel panel-sidebar panel-sidebar">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-bookmark"></i>&nbsp;          صورت حساب ها
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="list-group portfolio-filter">
                                    <a menuitemname="Order New Services" data-filter=".table-paid"   class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        &nbsp;     پرداخت شده
                                    </a>
                                    <a menuitemname="Register New Domain" data-filter=".table-unPaid"  class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Register_New_Domain">
                                        &nbsp;     پرداخت نشده
                                    </a>
                                    <!--                                                                                                                                                                <a menuitemname="Logout" data-filter=".table-cancle"   class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Logout">
                                                                                &nbsp;     لغو شده
                                                                                </a>-->
                                </div>
                            </div>
                            <div menuitemname="Client Shortcuts" class="panel panel-sidebar panel-sidebar">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-cube"></i>&nbsp;                پروژه ها
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="list-group">
                                    <%
                                        if (projectMe == null || projectMe.size() == 0) {
                                            System.out.print(">>>>9" + projectMe + "//" + news + "//" + rowFactor);
                                    %>
                                    <a menuitemname="Order New Services"  class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        در حال حاضر در هیچ پروژه ای مشارکت نداشته اید
                                    </a>
                                    <%} else {
                                        for (int i = 0; i < projectMe.size(); i++) {
                                    %>
                                    <a href="Server?do=Content.sw&panel=sw&text=<%=projectMe.get(i).get(Content._title)%>" target="_blank" menuitemname="Order New Services"  class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        &nbsp; <%=projectMe.get(i).get(Content._title)%>
                                    </a>
                                    <%}%><%}%>
                                </div>
                            </div>
                            <div menuitemname="Client Shortcuts" class="panel panel-sidebar panel-sidebar">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="fa fa-globe"></i>&nbsp;                اطلاعیه ها
                                        <i class="fa fa-chevron-up panel-minimise pull-right"></i>
                                    </h3>
                                </div>
                                <div class="list-group">
                                    <%
                                        if (news == null || news.size() == 0) {
                                            System.out.print(">>>>10" + projectMe + "//" + news + "//" + rowFactor);
                                    %>
                                    <a menuitemname="Order New Services"  class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        ;در حال حاضر اطلاعیه ای وجود ندارد
                                    </a>
                                    <%} else {%>
                                    <%System.out.print(">>>>11" + projectMe + "//" + news + "//" + rowFactor);
                                        for (int i = 0; i < news.size(); i++) {
                                    %>
                                    <a onclick="selectNews(<%=news.get(0).get(News._id)%>)" menuitemname="Order New Services"  class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Order_New_Services">
                                        &nbsp;<%=news.get(0).get(News._title)%>
                                    </a><%}%>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <!--end scrip-->
    <!--start footer-->
    <footer class="footer" style="margin-top: 10px">
        <div class="footer-widgets">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="widget widget_text widget_info">
                            <h4 class="widget-title">ادرس</h4>
                            <div class="textwidget">
                                <ul class="footer-info">
                                    <li class="address">اصفهان<br/><br/>خیابان نیکبخت<br/>
                                        <br/>تعاونی دادگستری اصفهان</li>
                                    <li class="phone">تلفن:031-323652650</li>
                                    <li class="email">Info@example.com</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_text widget_info">
                            <h4 class="widget-title">منو</h4>
                            <div class="textwidget">
                                <ul class="footer-info">
                                    <li class="footer-home arrow"><a>صفحه اصلی</a></li>
                                    <li class="footer-Allproject arrow"><a>درباره ما</a></li>
                                    <li class="footer-Allproject arrow"><a>پروژه ها</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_text widget_info">
                            <h4 class="widget-title">خدمات</h4>
                            <div class="textwidget">
                                <ul class="footer-info">
                                    <li class="footer-home arrow"><a>پرداخت ها و صورتحساب ها</a></li>
                                    <li class="footer-about arrow"><a>پروژه ها من</a></li>
                                    <li class="footer-Allproject arrow"><a>پروژه های شرکت</a></li>
                                    <li class="footer-Allproject arrow"><a>پیام ها</a></li>
                                    <li class="footer-Allproject arrow"><a>گزارش پروژه ها</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_text widget_info">
                            <h4 class="widget-title">تعاونی دادگستری اصفهان</h4>
                            <div class="textwidget">
                                <ul class="">
                                    <li class="footer-home"><a>تعاون برای کار شایسته</a></li>
                                    <li class="footer-about">  <div class="social-links">
                                            <a  class="root-blue"><i class="fa fa-twitter"></i></a>
                                            <a  class="root-blue"><i class="fa fa-facebook"></i></a>
                                            <a  class="root-blue"><i class="fa fa-instagram"></i></a>
                                            <a  class="root-blue"><i class="fa fa-linkedin"></i></a>
                                            <a  class="root-blue"><i class="fa fa-skype"></i></a>
                                        </div></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!--end footer-->
    <div class="switcher-container">
        <h2>
            انتخاب رنگ<a class="sw-click">
                <i class="fa fa-cog">
                </i>
            </a>
        </h2>
        <div class="selector-box">
            <div class="clearfix">
            </div>
            <div class="sw-odd">
                <h3>
                    انتخاب رنگ</h3>
                <div class="ws-colors">
                    <a href="#color1" class="styleswitch current" id="color1">
                        &nbsp;<span class="cl1">
                        </span>
                    </a>
                    <a href="#color2" class="styleswitch" id="color2">
                        &nbsp;<span class="cl2">
                        </span>
                    </a>
                    <a href="#color3" class="styleswitch" id="color3">
                        &nbsp;<span class="cl3">
                        </span>
                    </a>
                    <a href="#color4" class="styleswitch" id="color4">
                        &nbsp;<span class="cl4">
                        </span>
                    </a>
                    <a href="#color5" class="styleswitch" id="color5">
                        &nbsp;<span class="cl5">
                        </span>
                    </a>
                    <a href="#color6" class="styleswitch" id="color6">
                        &nbsp;<span class="cl6">
                        </span>
                    </a>
                </div>
            </div>
            <div class="sw-even">
                <h3>
                    لایه بندی:</h3>
                <a  class="sw-light">
                    حاشیه</a>
                <a  class="sw-dark">
                    کامل</a>
            </div>
            <div class="sw-pattern pattern">
                <h3>
                    پس زمینه:</h3>
                <a  class="sw-pattern" data-image="template/img/1.png">
                    <img src="template/img/1.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/2.png">
                    <img src="template/img/2.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/3.png">
                    <img src="template/img/3.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/4.png">
                    <img src="template/img/4.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/5.png">
                    <img src="template/img/5.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/6.png">
                    <img src="template/img/6.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/7.png">
                    <img src="template/img/7.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/8.png">
                    <img src="template/img/8.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/9.png">
                    <img src="template/img/9.png" alt="image">
                </a>
                <a  class="sw-pattern" data-image="template/img/10.png">
                    <img src="template/img/10.png" alt="image">
                </a>
            </div>
            <div class="clearfix">
            </div>
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
                    <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">پروژه های شرکت</a></li>
                    <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();
                            return false;">گالری</a></li>
                    <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">تماس با ما</a></li>
                    <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">درباره ما</a></li>
                </ul>
            </div>
        </div>
    </div>
    <a class="go-top show">
        <i class="fa fa-angle-up"></i>
    </a>
    <!--end link-->
    <!--start scrip-->
    <script src="./Manager/js/jquery/jquery-3.5.1.min.js" type="text/javascript"></script>
    <!--<script src="template/js/jquery.min.js" type="text/javascript"></script>-->
    <script src="template/js/profile.js" type="text/javascript"></script>
    <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="Manager/js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="Manager/js/jj2.js" type="text/javascript"></script>
    <!--<script src="template/js/jj2.js" type="text/javascript"></script>-->
    <script src="jsCms/user.js" type="text/javascript"></script>
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
    <script src="template/js/jquery.themepunch.tools.min.js" type="text/javascript"></script>
    <script src="template/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>
    <script src="template/js/slider.js" type="text/javascript"></script>
    <script src="template/js/util.js" type="text/javascript"></script>
    <script src='template/js/owl.carousel.js' type='text/javascript'></script>
    <!--<script src="template/js/dataTables.bootstrap.min.js" type="text/javascript"></script>-->
    <script src="template/js/dataTables.responsive.min.js" type="text/javascript"></script>
    <script>
                        function selectProject(id) {
                            $(".All").css("display", "none");
                            $(".builder").css("display", "none");
                            $(".electric").css("display", "none");
                            $(".hammer").css("display", "none");
                            $(".table-cancle").css("display", "none");
                            $(".table-unPaid").css("display", "none");
                            $(".table-paid").css("display", "none");
                            new jj("do=Content.getProjectInfo&panel=swContent&id=" + id + "&jj=1").jjAjax2();
                        }
                        function selectNews(id) {
                            $(".All").css("display", "none");
                            $(".builder").css("display", "none");
                            $(".electric").css("display", "none");
                            $(".hammer").css("display", "none");
                            $(".table-cancle").css("display", "none");
                            $(".table-unPaid").css("display", "none");
                            $(".table-paid").css("display", "none");
                            new jj("do=News.getNewsInfo&panel=swContent&id=" + id + "&jj=1").jjAjax2();
                        }
                        $(document).ready(function () {
                            $('#example').DataTable();
                            $('#ticketDataTable').DataTable({"bLengthChange": false, info: false});
                            new jj('#sendFileMessenger').jjAjaxFileUploadByTitleAndMultiFile('#attachFileMessenger', 'messenger_attachFile', 'messenger_titleFile', "#showFileMessengerDiv");
                            new jj('#sendFileMessenger_sale').jjAjaxFileUploadByTitleAndMultiFile('#attachFileMessenger_sale', 'messenger_attachFile', 'messenger_titleFile_sale', "#showFileMessengerDiv_sale");
                            new jj('#sendFileMessenger_move').jjAjaxFileUploadByTitleAndMultiFile('#attachFileMessenger_move', 'messenger_attachFile', 'messenger_titleFile_move', "#showFileMessengerDiv_move");
                            //                                                                                                                                                                                                        new jj('#userAttachFiles_sendFilesAdmin').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUserAdmin', 'user_attachFileUser', 'user_titleFile_admin', "#user_attachFileUserDiv1");
                            //                                                                                                                                                                                                        new jj('#sendPic1').jjAjaxFileUpload2('user_file_personal', '', '#user_attachPicPersonal', '#PicPreviewPersonal');
                            //                                                                                                                                                                                                        new jj('#sendPicSignature').jjAjaxFileUpload2('user_file_Signature', '', '#user_attachPicSignature', '#PicPreviewSignature');
                            //                                                                                                                                                                                                        new jj('#sendPicupload').jjAjaxFileUpload2('uploaded_file', '', '#user_attachPicPersonnelCard', '#PicPreview');
                            //                                                                                                                                                                                                        new jj('#sendPicFiles').jjAjaxFileUpload3('#attachFile', '#user_attachFile');
                            var showChar = 100;
                            var ellipsestext = "...";
                            var moretext = "بیشتر بخوانید";
                            var lesstext = "پایان";
                            $('.more').each(function () {
                                var content = $(this).html();
                                if (content.length > showChar) {
                                    var c = content.substr(0, showChar);
                                    var h = content.substr(showChar - 2, content.length - showChar);
                                    var html = c + '<span class="moreellipses">' + ellipsestext + '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a  class="morelink">' + moretext + '</a></span>';
                                    $(this).html(html);
                                }
                            });
                            $(".morelink").click(function () {
                                if ($(this).hasClass("less")) {
                                    $(this).removeClass("less");
                                    $(this).html(moretext);
                                } else {
                                    $(this).addClass("less");
                                    $(this).html(lesstext);
                                }
                                $(this).parent().prev().toggle();
                                $(this).prev().toggle();
                                return false;
                            });
                        });
                        (function () {
                            new jj('#sendPic1').jjAjaxFileUpload2('user_file_personal', '#user_attachPicPersonal', '#PicPreviewPersonal');
                            new jj('#userAttachFiles_sendFiles').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUser', 'user_attachFileUser', 'user_titleFile', "#user_attachFileUserDiv");
                            var $container = $('.portfolio-wrap');
                            var selector = ".All";
                            //                                                                        $('.portfolio-filter li').removeClass('active');
                            //
                            //                                                                         $(selector).css({"visibility": ""});
                            $(selector).css("display", "block");
                            $(this).addClass('active');
                            $container.isotope({filter: selector});
                            return false;
                        }());
                        $('.portfolio-filter').on('click', function () {
                            $(".allTabs").css("display", "none");
                            $(".client-home-panels.All").css("display", "none");
                            var selector = $(this).find("a").attr('data-filter');
                            $(selector).css("display", "block");
                            $('.portfolio-filter').removeClass('active');
                            $(this).addClass('active');
                            $container.isotope({filter: selector});
                            return false;
                        });
                        (function () {
                            var token = window.localStorage.getItem('user_token');
                            if (token === null || token === '' || token === 'undefined') {
                                $('.register_url').show();
                            } else {
                                $('.textlogin').show();
                                //                                                                                    new jj("do=Access_User.loginUserInHome&user_token=" + token + "&panel=userNameAfterLogin").jjAjax2(false);
                            }
                        }());
                        //                                                                        $('.flat-iconbox-carosuel').owlCarousel({
                        //                                                                            items: 4,
                        //                                                                            loop: true,
                        //                                                                            pagination: false,
                        //                                                                            slideSpeed: 700,
                        //                                                                            paginationSpeed: 700,
                        //                                                                            rewindSpeed: 700,
                        //                                                                            lazyLoad: true
                        //                                                                        });
                        function loadNews(id) {
                            new jj("do=News.showNews&panel=sw&jj=1&id=" + id).jjAjax2(true);
                            $("html, body").animate({scrollTop: 0}, "slow");
                        }
                        function loadinformation(id) {
                            new jj("do=News.showInfo&panel=sw&jj=1&id=" + id).jjAjax2(true);
                            $("html, body").animate({scrollTop: 0}, "slow");
                        }
                        $(document).ready(function () {
                            persian = {0: '۰', 1: '۱', 2: '۲', 3: '۳', 4: '۴', 5: '۵', 6: '۶', 7: '۷', 8: '۸', 9: '۹'};
                            function traverse(el) {
                                if (el.nodeType == 3) {
                                    var list = el.data.match(/[0-9]/g);
                                    if (list != null && list.length != 0) {
                                        for (var i = 0; i < list.length; i++)
                                            el.data = el.data.replace(list[i], persian[list[i]]);
                                    }
                                }
                                for (var i = 0; i < el.childNodes.length; i++) {
                                    traverse(el.childNodes[i]);
                                }
                            }
                            traverse(document.body);
                        });
                        var cmsprofile = {//برای لود و فرستادن فایل اتچ از قسمت کاربر
                            tableName: "Access_User",
                            jjAjaxFileUpload2: function (inputFileId, inputFiletitle, viewImgSelector) {
                                var title = $("#content_pic1").html();
                                $.ajaxFileUpload({
                                    url: 'UploadServlet',
                                    secureuri: false,
                                    fileElementId: inputFileId.replace("#", ""),
                                    fileElementTitle: inputFiletitle.replace("#", ""),
                                    dataType: 'JSON',
                                    cache: false,
                                    success: function (data) {//اپلود و اینسرت همزمان
                                        if (data !== "") {
                                            var param = "";
                                            param += "do=" + cmsprofile.tableName + ".setAttachPic";
                                            param += "&user_attachPicPersonal=" + data;
                                            param += "&user_token=" + <%=user_token%>;
                                            new jj(param).jjAjax2(false);
                                        }
                                        if (data === "big") {
                                            new jj('حجم فایل شما بیش اندازه بزرگ می باشد').jjModal('خطا');
                                        }
                                        if ((data === "")) {
                                            new jj('فایل به درستی ارسال نشد').jjModal('خطا');
                                        }
                                    }
                                });
                            }
                        };
    </script>
</body>
</html>
