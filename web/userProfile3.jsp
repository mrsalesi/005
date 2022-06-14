<%-- 
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>

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
<%@page import="cms.cms.Category_Gallery"%>
<%@page import="cms.cms.Comment"%>
<%@page import="cms.cms.News"%>
<%

    Server.Connect();
//    request.setAttribute("text", "تعاونی دادگستری");
//    Content.sw(request, response, Server.db, true);
    String user_token = jjTools.getParameter(request, "user_token");
    System.out.println("user_token====" + user_token);
    StringBuilder html1 = new StringBuilder();
    jjDatabaseWeb db;
    db = Server.db;
%>
<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US" style="direction: rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>تعاونی دادگستری</title>
            <meta  name="author" content="themesflat.com">
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                    <link href="template/css/bootstrap.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/style.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/responsive.css" rel="stylesheet" type="text/css"/>
                    <link href="template/colors/color1.css" rel="stylesheet" type="text/css" id="colors"/>
                    <link href="template/css/animate.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/revelationSlider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/flexslider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/magpopup.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/shortcodes.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/owlcarusel.css" rel="stylesheet" type="text/css"/>
                    <link href="Manager/font-awesome.css" rel="stylesheet">
                        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
                        <link href="Manager/ionicons.css" rel="stylesheet">
                            <link href="StyleBody.css" rel="stylesheet" type="text/css"/>
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
                                                        <li class="phone"><a href="">تلفن تماس:031-36639871-2</a></li>
                                                        <li class="email"><a href="">ایمیل: taavoni@gmail.com</a></li>
                                                    </ul>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="social-links">
                                                        <a href="">
                                                            <a  class="register_url flat-button button-color button-normal yellow " style="" onclick='$("#sw").load("login.html")'>ثبت نام/ورود</a>
                                                            <a id="userNameAfterLogin" class="textlogin" style="display: none;"></a>
                                                        </a>
                                                        <a href=""><i class="fa fa-twitter"></i></a>
                                                        <a href=""><i class="fa fa-facebook"></i></a>
                                                        <a href=""><i class="fa fa-instagram"></i></a>
                                                        <a href=""><i class="fa fa-linkedin"></i></a>
                                                        <!--<a href=""><a id="userNameAfterLogin1" class="textlogin1" style="display: none"></a></a>-->

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
                                                                <ul class="menu">
                                                                    <li class="home">
                                                                        <a href="index.jsp">صفحه اصلی</a>
                                                                    </li>
                                                                                                           <li class="has-mega-menu">
                                                                        <a class="has-mega" href="">پروژه های شرکت</a>
                                                                        <div class="submenu mega-menu" style="">
                                                                            <div class="row">
                                                                                <div class="container">
                                                                                    <%
                                                                                        List<Map<String, Object>> row10 = jjDatabase.separateRow(db.Select(Category_Content.tableName, Category_Content._parent + "=39"));
                                                                                        for (int o = 0; o < row10.size(); o++) {
                                                                                    %>
                                                                                    <div class="col-md-4">
                                                                                        <h2 style="text-align: right"><%=row10.get(o).get(Category_Content._title)%></h2>
                                                                                        <ul class="mega-menu-sub">
                                                                                            <%
                                                                                                List<Map<String, Object>> row9 = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + row10.get(o).get(Category_Content._id)));
                                                                                                for (int b = 0; b < row9.size(); b++) {
                                                                                            %>
                                                                                            <li><a href=""><i class="fa fa-flag"></i><%=row9.get(b).get(Content._title)%></a></li> 
                                                                                                    <%}%>

                                                                                        </ul>
                                                                                    </div>
                                                                                    <%}%>

                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li><a href="" onclick="new jj('do=Content.sw&panel=sw&text=تیم شرکت&jj=1').jjAjax2()">تیم شرکت</a></li>
                                                                    <li><a href="" onclick="$('#sw').load('gallery.jsp')">گالری</a></li>
                                                                    <li><a href="">خدمات</a> </li>
                                                                    <li><a href="" onclick="new jj('do=Content.sw&panel=sw&text=درباره ما&jj=1').jjAjax2()">درباره ی شرکت </a></li>
                                                                    <li><a href="" onclick="new jj('do=Content.sw&panel=sw&text=تماس با ما&jj=1').jjAjax2()">تماس با ما</a></li>
                                                                  
                                                                </ul>
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
                                    <div id="sw">
                                        <!--start slider-->
                                        <section class="main-content page-single page-about" style='background:#fff'>
                                            <div class="container">
                                                <div class="row">
                                                    <div class="page-content">
                                                        <div class="post-wrap">
                                                            <div class="col-md-12">
                                                                <div class="post-wrap item electric" style="opacity: 0;height: 0px">
                                                                    <%
                                                                        List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._token + "='" + user_token + "'"));

                                                                        List<Map<String, Object>> groupId = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + user.get(0).get(Access_User._id)));
                                                                        if (groupId.size() == 0) {
                                                                    %><%} else {
                                                                        List<Map<String, Object>> news = jjDatabase.separateRow(db.Select(News.tableName, News._privateGroupId + "=" + groupId.get(0).get(Access_Group_User._group_id)));
                                                                        if (news.size() == 0) {
                                                                    %><%} else {
                                                                        for (int i = 0; i < news.size(); i++) {
                                                                            jjCalendar_IR dateLable = new jjCalendar_IR(news.get(i).get(News._date).toString());
                                                                            String month = dateLable.getMonthName();
                                                                            int day = dateLable.getDay();
                                                                            int year = dateLable.getYear();
                                                                    %>
                                                                    <article class="post clearfix">
                                                                        <div class="featured-post">
                                                                            <img src="upload/<%=news.get(0).get(News._pic)%>" alt="image" width='100%'>
                                                                                <ul class="post-comment">
                                                                                    <li class="date">
                                                                                        <span class="day"><%=day%></span>
                                                                                    </li>
                                                                                    <li class="comment">
                                                                                        <%=month%>
                                                                                    </li>
                                                                                </ul>
                                                                        </div>
                                                                        <div class="content-post">
                                                                            <h2 class="title-post"><a href=""><%=news.get(0).get(News._title)%></a></h2>                                                                   
                                                                            <div class="entry-post excerpt">
                                                                                <p><%=news.get(0).get(News._content)%></p>
                                                                            </div>
                                                                        </div>
                                                                    </article>
                                                                    <%}%>
                                                                    <%}%>
                                                                    <%}%>
                                                                </div>                                                              
                                                                <div class="table item All" style="opacity: 0;height: 0px">
                                                                    <div class="table">
                                                                        <div class="table-header">
                                                                            <div class="header__item"><a id="name" class="filter__link" href="#">نام</a></div>
                                                                            <div class="header__item"><a id="wins" class="filter__link filter__link--number" href="#">بارگزاری اطلاعات</a></div>
                                                                        </div>
                                                                        <div class="table-content">	
                                                                            <div class="table-row">		
                                                                                <div class="table-data"><%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%></div><br>
                                                                                    <div class="table-data">  <div class="col-lg-12">
                                                                                            <div class="" id="user_divUpload"></div>
                                                                                        </div>
                                                                                        <div class="input-group col-lg-12">
                                                                                            <div class=""> عنوان فایل</div>
                                                                                            <span id="user_pic" class="form-control"></span>
                                                                                            <input class="form-control" id="user_titleFile" placeholder="فایل شما با این عنوان در سامانه ذخیره میشود" type="text" >
                                                                                                <input  class="flat-button button-color button-normal green" id="attachFileUser" name="attachFileUser" onchange="$(this).parent().parent().find('.form-control').html($(this).val().split(/[\\|/]/).pop());" style="display: none;" type="file">
                                                                                                    <a class="flat-button button-color button-normal green" id="userAttachFiles_sendFiles" onclick="jjAjaxFileUploadByTitleAndMultiFile('#attachFileUser', 'user_attachFile', 'user_titleFile', '#user_divUpload')" style="padding: 9px 5px;">ارسال فایل</a>
                                                                                                    <a class="flat-button button-color button-normal green" onclick="$(this).parent().find('input[type=file]').click();" style="padding: 9px 5px;">انتخاب فایل</a>
                                                                                                    <!--<span class="form-control"></span>-->
                                                                                                    </div> 
                                                                                                    </div>				
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    <section class="flat-row what-we-do offer item builder" id="services" style="opacity: 0;height: 0px">
                                                                                                        <div class="row">
                                                                                                            <div class="row">
                                                                                                                <div class="col-md-12">
                                                                                                                    <div class="title-section style3">
                                                                                                                        <h1 class="title">پروژه ها</h1>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="col-12">    

                                                                                                                <%
                                                                                                                    List<Map<String, Object>> projectMe = jjDatabase.separateRow(db.Select(Content.tableName, Content._privateGroupId + "=" + groupId.get(0).get(Access_Group_User._group_id)));
                                                                                                                    for (int i = 0; i < projectMe.size(); i++) {
                                                                                                                        jjCalendar_IR dateLable = new jjCalendar_IR(projectMe.get(i).get(Content._date).toString());
                                                                                                                        String month = dateLable.getMonthName();
                                                                                                                        int day = dateLable.getDay();
                                                                                                                        int year = dateLable.getYear();
                                                                                                                %>
                                                                                                                <div class="col-md-6 col-sm-6 col-xs-6">
                                                                                                                    <div class="iconbox-item">
                                                                                                                        <div class="iconbox left style3">
                                                                                                                            <img src="upload/<%=projectMe.get(i).get(Content._pic)%>" alt="serives">
                                                                                                                                <div class="box-content">
                                                                                                                                    <div class="box-title">
                                                                                                                                        <a href="#"><%=projectMe.get(i).get(Content._title)%></a>
                                                                                                                                    </div>
                                                                                                                                    <%=projectMe.get(i).get(Content._content)%>
                                                                                                                                    <a href="#" class=""></a>
                                                                                                                                    <i class="fa-solid fa-gauge"></i>
                                                                                                                                </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div><%}%>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </section>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    </div>
                                                                                                    <div class="page-sidebar">
                                                                                                        <div class="sidebar">
                                                                                                            <div class="card">
                                                                                                                <div class="card-body">
                                                                                                                    <div class="d-flex flex-column align-items-center text-center">
                                                                                                                        <%
                                                                                                                            System.out.print("..........????>>>>>>>>++" + user.get(0).get(Access_User._attachPicPersonal) + "..........++");
                                                                                                                            if (user.get(0).get(Access_User._attachPicPersonal).equals("")) {
                                                                                                                        %>
                                                                                                                        <img src="img/profile_pre.png" alt="Admin" class="rounded-circle" width="150">
                                                                                                                            <%} else {%>
                                                                                                                            <img src='upload/<%=user.get(0).get(Access_User._attachPicPersonal)%>' alt="Admin" class="rounded-circle" width="150">
                                                                                                                                <%}%>
                                                                                                                                <div class="mt-3">
                                                                                                                                    <h4><%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%></h4>
                                                                                                                                    <p class="text-secondary mb-1"><%=user.get(0).get(Access_User._email)%></p>
                                                                                                                                    <p class="text-muted font-size-sm"><%=user.get(0).get(Access_User._char3)%></p>
                                           <div class="row" style="margin-top: 2%;">
                    <div class="">
                        <span id="content_pic1"  class="form-control" style="display: none;"></span>
                        <input id="content_file" name="content_file" onchange="$(this).parent().find('.form-control').html($(this).val().split(/[|/]/).pop());
                                cmsprofile.jjAjaxFileUpload2('content_file', '#content_pic1', '#imagePreview')" style="display: none;" type="file">
                        <div class="chat-form_form_attach"  onclick="$(this).parent().find('input[type=file]').click();">

                            <span style="margin: 0 auto;display: block;">
                                <span class="flat-button button-color button-normal green" style="padding: 9px 5px;">ویرایش پروفایل</span>
                            </span>
                        </div>
                        <div id="imagePreview">
                        </div>
                    </div>
                </div>

                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        <h3 class="title-project-detail">مشارکت در پروژه ها</h3>
                                                                                                                                                        <ul class="entry-details-content">
                                                                                                                                                            <li class="date"><a data-filter=".All" href="#">
                                                                                                                                                                    <strong>داشبورد</strong>
                                                                                                                                                                </a></li>
                                                                                                                                                            <li class="location"><a  data-filter=".builder"  href="#">
                                                                                                                                                                    <strong>پروژه های من</strong>
                                                                                                                                                                </a></li>                                                                                                                              
                                                                                                                                                            <li class="location"><a  data-filter=".electric"  href="#">
                                                                                                                                                                    <strong>اطلاعیه های شخصی</strong>
                                                                                                                                                                </a></li>                                                                                                                              
                                                                                                                                                            <li class="investor"><a data-filter=".hammer"  href="#">
                                                                                                                                                                    <strong>پرداخت</strong>
                                                                                                                                                                </a></li>

                                                                                                                                                        </ul>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        </section>
                                                                                                                                                        </div>
                                                                                                                                                        </div>
                                                                                                                                                        <!--end link-->

                                                                                                                                                        <!--start scrip-->
                                                                                                                                                        <script src="template/js/jquery.min.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
                                                                                                                                                        <script src="Manager/js/ajaxfileupload.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jj2.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/index.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery.easing.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/imagesloaded.min.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery.isotope.min.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery-waypoints.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery.cookie.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/jquery.fitvids.js" type="text/javascript"></script>
                                                                                                                                                        <script src="template/js/parallax.js" type="text/javascript"></script><!--
                                                                                                                                                        -->  
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
                                                                                                                                                                                        <li class="address">اصفهان<br><br>خیابان نیکبخت<br>
                                                                                                                                                                                                        <br>تعاونی دادگستری اصفهان</li>
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
                                                                                                                                                                                                        <a href="" class="root-blue"><i class="fa fa-twitter"></i></a>
                                                                                                                                                                                                        <a href="" class="root-blue"><i class="fa fa-facebook"></i></a>
                                                                                                                                                                                                        <a href="" class="root-blue"><i class="fa fa-instagram"></i></a>
                                                                                                                                                                                                        <a href="" class="root-blue"><i class="fa fa-linkedin"></i></a>
                                                                                                                                                                                                        <a href="" class="root-blue"><i class="fa fa-skype"></i></a>
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
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="switcher-container">
                                                                                                                                                                                                        <h2>
                                                                                                                                                                                                        انتخاب رنگ<a href="" class="sw-click">
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
                                                                                                                                                                                                        <a href="#" class="sw-light">
                                                                                                                                                                                                        حاشیه</a>
                                                                                                                                                                                                        <a href="#" class="sw-dark">
                                                                                                                                                                                                        کامل</a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="sw-pattern pattern">
                                                                                                                                                                                                        <h3>
                                                                                                                                                                                                        پس زمینه:</h3>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/1.png">
                                                                                                                                                                                                        <img src="template/img/1.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/2.png">
                                                                                                                                                                                                        <img src="template/img/2.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/3.png">
                                                                                                                                                                                                        <img src="template/img/3.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/4.png">
                                                                                                                                                                                                        <img src="template/img/4.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/5.png">
                                                                                                                                                                                                        <img src="template/img/5.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/6.png">
                                                                                                                                                                                                        <img src="template/img/6.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/7.png">
                                                                                                                                                                                                        <img src="template/img/7.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/8.png">
                                                                                                                                                                                                        <img src="template/img/8.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/9.png">
                                                                                                                                                                                                        <img src="template/img/9.png" alt="image">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        <a href="#" class="sw-pattern" data-image="template/img/10.png">
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
                                                                                                                                                                                                        <script>
                                                                                                                                                        function jjAjaxFileUploadByTitleAndMultiFile(inputFileId, databaseColoumnName, inputFiletitle, divUploadSelector) {
                                                                                                                                                            if ($("#" + inputFileId.replace("#", "")).val() == "") {
                                                                                                                                                                new jj("ابتدا  فایلی را انتخاب نمایید.").jjModal();
                                                                                                                                                                return;
                                                                                                                                                            }
                                                                                                                                                            $.ajaxFileUpload({
                                                                                                                                                                url: 'UploadServlet',
                                                                                                                                                                secureuri: false,
                                                                                                                                                                fileElementId: inputFileId.replace("#", ""),
                                                                                                                                                                fileElementTitle: inputFiletitle.replace("#", ""),
                                                                                                                                                                dataType: 'JSON',
                                                                                                                                                                cache: false,
                                                                                                                                                                success: function (data) {
                                                                                                                                                                    if (data != "") {
                                                                                                                                                                        if (data != null) {
                                                                                                                                                                            data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
                                                                                                                                                                            data = data.replace('<PRE style="word-wrap: break-word; white-space: pre-wrap;">', '');
                                                                                                                                                                            data = data.replace("<PRE>", '').replace("</PRE>", '').replace("<pre>", '').replace("</pre>", '').replace("upload/", '').replace("Upload/", '');
                                                                                                                                                                            data = data.replace("/", '').replace("/", '').replace("\\", '');
                                                                                                                                                                        } else {
                                                                                                                                                                            new jj('فایل به درستی ارسال نشد.').jjModal();
                                                                                                                                                                            return;
                                                                                                                                                                        }
                                                                                                                                                                        $("#" + inputFileId.replace("#", "")).val('');
                                                                                                                                                                        if (data != "big") {
                                                                                                                                                                            var title = $("#" + inputFiletitle.replace("#", "")).val();
                                                                                                                                                                            var param = "";
                                                                                                                                                                            param += "&title=" + title;
                                                                                                                                                                            param += "&filename=" + data;
                                                                                                                                                                            param += "&do=Upload.setTitle";
                                                                                                                                                                            new jj(param).jjAjax2(false);
                                                                                                                                                                            $("#" + inputFileId.replace("#", "")).val('');
                                                                                                                                                                            var picName = data;
                                                                                                                                                                            var ext = data.split('.').pop();
                                                                                                                                                                            var ext2 = data.split('.').pop();
                                                                                                                                                                            // برای آلنکه بتوانیم چندین اینپوت فیلد که مربوط به یک ستون در دیتابیس است را سریال کنیم نام کلاس را دقیقا ستون معادل در دیتابیس می گذاریم و باید در هنگام ادیت یا اینسرت با یک حلقه ی تکرار این سریال را انجام دهیم
                                                                                                                                                                            if (ext == ("png") || ext == ("jpg") || ext == ("gif") || ext == ("svg")) {
                                                                                                                                                                                $(divUploadSelector).append("<div class=''>"
                                                                                                                                                                                        + "<img class='wd-40 rounded-circle mg-r-20' src='upload/" + data + "'/>"
                                                                                                                                                                                        + "<a  href='upload/" + data + "'>دانلود  " + title + "</a>"
                                                                                                                                                                                        + " <input class='" + databaseColoumnName.replace("#", "") + "' type='hidden'  value='" + data + "'>"
                                                                                                                                                                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i>" + "</div>"
                                                                                                                                                                                        + "</div>");
                                                                                                                                                                            } else if (ext == ("mp3") || ext == ("mp4") || ext == ("wmv") || ext == ("ogg")) {
                                                                                                                                                                                $(divUploadSelector).append("<div class=''>"
                                                                                                                                                                                        + "<i class='icon ion-ios-play-outline'></i>"
                                                                                                                                                                                        + "<a  href='upload/" + data + "'>دانلود  " + title + "</a>"
                                                                                                                                                                                        + " <input class='" + databaseColoumnName.replace("#", "") + "' type='hidden'  value='" + data + "'>"
                                                                                                                                                                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i>" + "</div>"
                                                                                                                                                                                        + "</div>");
                                                                                                                                                                            } else {
                                                                                                                                                                                $(divUploadSelector).append("<div class=''>"
                                                                                                                                                                                        + "<i class='icon ion-ios-paper-outline'></i>"
                                                                                                                                                                                        + "<a  href='upload/" + data + "'>دانلود  " + title + "</a>"
                                                                                                                                                                                        + " <input class='" + databaseColoumnName.replace("#", "") + "' type='hidden'  value='" + data + "'>"
                                                                                                                                                                                        + "<div class='btn btn-danger btn-icon mg-r-5 mg-b-10' onclick='$(this).parent().remove();'><i class='fa fa-close'></i>" + "</div>"
                                                                                                                                                                                        + "</div>");
                                                                                                                                                                            }
                                                                                                                                                                            var param = "";
                                                                                                                                                                            param += "&user_attachFile=" + data;
                                                                                                                                                                            param += "&user_token=" + <%=user_token%>;
                                                                                                                                                                            param += "&do=Access_User.setAttach";
                                                                                                                                                                            new jj(param).jjAjax2(false);
                                                                                                                                                                        } else {
                                                                                                                                                                            new jj('حجم فایل شما بیش اندازه بزرگ می باشد.').jjModal();
                                                                                                                                                                        }
                                                                                                                                                                    } else {
                                                                                                                                                                        new jj('فایل به درستی ارسال نشد.').jjModal();
                                                                                                                                                                    }
                                                                                                                                                                    $("#" + inputFiletitle.replace("#", "")).val('');
                                                                                                                                                                    $('#user_pic').html('');
                                                                                                                                                                }
                                                                                                                                                            });
                                                                                                                                                        }
                                                                                                                                                        ;
                                                                                                                                                        $(document).ready(function () {
                                                                                                                                                            var showChar = 100;
                                                                                                                                                            var ellipsestext = "...";
                                                                                                                                                            var moretext = "بیشتر بخوانید";
                                                                                                                                                            var lesstext = "پایان";
                                                                                                                                                            $('.more').each(function () {
                                                                                                                                                                var content = $(this).html();
                                                                                                                                                                if (content.length > showChar) {

                                                                                                                                                                    var c = content.substr(0, showChar);
                                                                                                                                                                    var h = content.substr(showChar - 2, content.length - showChar);
                                                                                                                                                                    var html = c + '<span class="moreellipses">' + ellipsestext + '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
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
                                                                                                                                                            var $container = $('.portfolio-wrap');
                                                                                                                                                            var selector = ".All";
                                                                                                                                                            //                                                                        $('.portfolio-filter li').removeClass('active');
                                                                                                                                                            //         
                                                                                                                                                            //                                                                         $(selector).css({"visibility": ""});
                                                                                                                                                            $(selector).css("opacity", "1");
                                                                                                                                                            $(selector).css("height", "");
                                                                                                                                                            $(this).addClass('active');
                                                                                                                                                            $container.isotope({filter: selector});
                                                                                                                                                            return false;
                                                                                                                                                        }());
                                                                                                                                                        $('.entry-details-content li').on('click', function () {
                                                                                                                                                            var $container = $('.portfolio-wrap');
                                                                                                                                                            var selector = $(this).find("a").attr('data-filter');
                                                                                                                                                            if (selector == '.builder') {
                                                                                                                                                                $(selector).css("opacity", "1");
                                                                                                                                                                $(selector).css("height", "");
                                                                                                                                                                $(".All").css("opacity", "0");
                                                                                                                                                                $(".All").css("height", "0");
                                                                                                                                                                $(".electric").css("opacity", "0");
                                                                                                                                                                $(".electric").css("height", "0");
                                                                                                                                                                $(".hammer").css("opacity", "0");
                                                                                                                                                                $(".hammer").css("height", "0");
                                                                                                                                                            }
                                                                                                                                                            if (selector == '.All') {
                                                                                                                                                                $(selector).css("opacity", "1");
                                                                                                                                                                $(selector).css("height", "");
                                                                                                                                                                $(".builder").css("opacity", "0");
                                                                                                                                                                $(".builder").css("height", "0");
                                                                                                                                                                $(".electric").css("opacity", "0");
                                                                                                                                                                $(".electric").css("height", "0");
                                                                                                                                                                $(".hammer").css("opacity", "0");
                                                                                                                                                                $(".hammer").css("height", "0");
                                                                                                                                                            }
                                                                                                                                                            if (selector == '.electric') {
                                                                                                                                                                $(selector).css("opacity", "1");
                                                                                                                                                                $(selector).css("height", "");
                                                                                                                                                                $(".builder").css("opacity", "0");
                                                                                                                                                                $(".builder").css("height", "0");
                                                                                                                                                                $(".All").css("opacity", "0");
                                                                                                                                                                $(".All").css("height", "0");
                                                                                                                                                                $(".hammer").css("opacity", "0");
                                                                                                                                                                $(".hammer").css("height", "0");
                                                                                                                                                            }
                                                                                                                                                            if (selector == '.hammer') {
                                                                                                                                                                $(selector).css("opacity", "1");
                                                                                                                                                                $(selector).css("height", "");
                                                                                                                                                                $(".builder").css("opacity", "0");
                                                                                                                                                                $(".builder").css("height", "0");
                                                                                                                                                                $(".electric").css("opacity", "0");
                                                                                                                                                                $(".electric").css("height", "0");
                                                                                                                                                                $(".All").css("opacity", "0");
                                                                                                                                                                $(".All").css("height", "0");
                                                                                                                                                            }
                                                                                                                                                            $('.portfolio-filter li').removeClass('active');
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
                                                                                                                                                                new jj("do=Access_User.loginUserInHome&user_token=" + token + "&panel=userNameAfterLogin").jjAjax2(false);
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
