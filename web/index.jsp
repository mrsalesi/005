<%-- 
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>

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
    String categuryId = jjTools.getParameter(request, "category");
    System.out.println("categuryId====" + categuryId);
    StringBuilder html1 = new StringBuilder();
    jjDatabaseWeb db;
    db = Server.db;
%>
<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US" style="direction: rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
        <title>تعاونی مسکن کارکنان دادگستری اصفهان</title>
        <meta  name="discription" content="سایت اطلاع رسانی  آگهی فروش امتیاز ها و پنل اعضای تعاونی مسکن کارکنان دادگستری اصفهان" />        
        <meta  name="author" content="شرکت پژوهشگران سیستم های هوشمند سپانو" />        
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="enamad" content="403829" />
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
        <link href="Manager/font-awesome.css" rel="stylesheet" />
        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/ionicons.css" rel="stylesheet" />
        <link href="StyleBody.css" rel="stylesheet" type="text/css"/>            
        <style>
            .morelink:visited {
                color: #eab702
            }
            a.morelink {
                text-decoration:none;
                outline: none;
            }
            .morecontent span {
                display: none;
            }
            .dateNews:after{
                content: "\f1ec";
                font-family: "fontAwesome";
                font-size: 11px;
                right: 0px;
                /*text-align: revert-layer;*/
                float: right;
                margin: 0px 5px;

            }
            .userIcon:after{
                content: "\f078";
                font-family: "fontAwesome";
                font-size: 10px;

            }
        </style>
    </head>
    <body class="header_sticky" style="font-family: vazir3">
        <section class="loading-overlay ajaxLoaderDiv" style="position: absolute; margin: auto; display: inline-flex">
            <div class="Loading-Page" style="margin: auto;width: 100px;max-width: 100%;overflow: hidden;">
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
                                <div id="userNameAfterLogin" class="textlogin" style="float: right;margin: 9px;color: #fff">
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

                                <!--                                                        <a class="register_url" style='display: none' onclick='$("#sw").load("login.html")'><i class="fa fa-user menu-extra-mobile"></i></a>-->
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
                                                <a class="has-mega">پروژه های شرکت</a>
                                                <div class="submenu mega-menu" style="">
                                                    <div class="row">
                                                        <div class="container">
                                                            <%
                                                                List<Map<String, Object>> row10 = jjDatabase.separateRow(db.Select(Category_Content.tableName, Category_Content._parent + "=39"));
                                                                for (int o = 0;o < row10.size();o++) {
                                                            %>
                                                            <div class="col-md-4">
                                                                <h2 style="text-align: right"><%=row10.get(o).get(Category_Content._title)%></h2>
                                                                <ul class="mega-menu-sub">
                                                                    <%
                                                                        List<Map<String, Object>> row9 = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + row10.get(o).get(Category_Content._id)));
                                                                        for (int b = 0; b < row9.size(); b++) {
                                                                    %>
                                                                    <li><a  href="indexContent.jsp?text=<%=row9.get(b).get(Content._title)%>" target="_blank"><%=row9.get(b).get(Content._title)%></a></li> 
                                                                        <%}%>

                                                                </ul>
                                                            </div>
                                                            <%}%>

                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li><a  onclick="new jj('do=Content.sw&panel=sw&text=تیم شرکت&jj=1').jjAjax2();return false;">تیم شرکت</a></li>
                                            <li><a  onclick="$('#sw').load('gallery.jsp')">گالری</a></li>
                                            <!--                                                                    <li><a >خدمات</a> </li>-->
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
            <div id="swLoginForm"></div>
            <div id="maincontent">
                <div id="sw">
                    <!--start slider-->
                    <%
                        List<Map<String, Object>> slider = jjDatabase.separateRow(db.Select(Pic.tableName, Pic._category_id + "=1"));
                    %>
                    <div class="tp-banner-container" id="home">
                        <div class="tp-banner">
                            <ul>
                                <%for (int i = 0;
                                            i < slider.size();
                                            i++) {
                                %>
                                <li data-transition="random-static" data-slotamount="7" data-masterspeed="1000" data-saveperformance="on">
                                    <img src="upload/<%=slider.get(i).get(Pic._url_name) + "." + slider.get(i).get(Pic._url_ex)%>" alt="slider-image" />
                                    <div class="tp-caption sfl title-slide center" data-x="40" data-y="100" data-speed="1000" data-start="1000" data-easing="Power3.easeInOut">
                                        <%=slider.get(i).get(Pic._title)%>
                                    </div>
                                    <div class="tp-caption sfr desc-slide center" data-x="40" data-y="240" data-speed="1000" data-start="1500" data-easing="Power3.easeInOut">
                                        <%=slider.get(i).get(Pic._discription)%>
                                    </div>
                                    <%
                                        if (!slider.get(i).get(Pic._title).toString().trim().isEmpty()) {
                                    %>
                                    <div class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" data-x="40" data-y="370" data-speed="1000" data-start="2000" data-easing="Power3.easeInOut"><a onclick="sw('<%=slider.get(i).get(Pic._title)%>');">بیشتر بخوانید</a> &nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-chevron-right"></i></div>
                                        <%
                                            }
                                        %>
                                    <div class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" data-x="225" data-y="370" data-speed="1000" data-start="2000" data-easing="Power3.easeInOut"><a onclick="sw('تماس با ما')">تماس با ما</a>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-chevron-right"></i></div>
                                </li>
                                <%}%>

                            </ul>
                        </div>
                    </div>
                    <!--end slider-->

                    <!--start slider samaneh-->

                    <section class="flat-row about what-we-do " id="services" style="direction: ltr !important;">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="title-section style3">
                                        <h1 class="title">سامانه ها</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="flat-iconbox-carosuel-wrap">
                                        <div class="flat-iconbox-carosuel" data-item="4" data-nav="false" data-dots="false" data-auto="false">
                                            <%
                                                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=40"));
                                                for (int j = 0; j < row.size(); j++) {
                                            %>

                                            <div class="iconbox-item root-back" style="padding: 5px 10px;text-align: right">
                                                <div class="iconbox  left square">
                                                    <div class="box-header">
                                                        <%if (j == 0) {%>
                                                        <i class="fa fa-user"></i>
                                                        <%}%>
                                                        <%if (j == 1) {%>
                                                        <i class="fa fa-registered"></i>
                                                        <%}%>
                                                        <%if (j == 2) {%>
                                                        <i class="fa fa-list"></i>
                                                        <%}%>
                                                        <%if (j == 3) {%>
                                                        <i class="fa fa-building"></i>
                                                        <%}%>
                                                        <div class="box-title"><a href="indexContent.jsp?text=<%=row.get(j).get(Content._title)%>" target="_blank"><%=row.get(j).get(Content._title)%></a></div>
                                                    </div>
                                                    <%=row.get(j).get(Content._explain)%>                                                                    
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--end slider samaneh-->
                    <!--start slider information-->
                    <section class="flat-row about background-color" id="about" style="background: #fff">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="title-section style3">
                                        <h1 class="title">اطلاعیه ها</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <%
                                    StringBuilder htmlNews = new StringBuilder();
                                    StringBuilder htmlNews2 = new StringBuilder();
                                    List<Map<String, Object>> journalNews = jjDatabase.separateRow(db.Select(News.tableName, News._category_id + "=2  order by " + News._date));
                                    int h = 0;
                                    int count = 0;

                                    if (journalNews.size()
                                            >= 6) {
                                        h = journalNews.size() - 6;
                                    } else {
                                        h = 0;
                                    }
                                %> 
                                <%
                                    for (int i = (journalNews.size() - 1);
                                            i >= h;
                                            i--) {
                                        count += 1;
                                        if (count <= 2) {
                                            htmlNews.append("<div class='col-md-6 col-lg-6 col-sm-6 col-xs-12'>"
                                                    + "<a href='indexNews.jsp?idNews=" + journalNews.get(i).get(News._id) + "'><div class='post-wrap'   onclick='loadinformation(" + journalNews.get(i).get(News._id) + ")'>"
                                                    + "<article class='post clearfix'><div class='featured-post' style='width:100%;height:200px'>"
                                                    + "<img style='width:100% !important;height:200px !important' src='upload/" + journalNews.get(i).get(News._pic) + "' alt='image'>"
                                                    + "</div><div class='content-post' style='display: grid;'><h3 class='title-post'>"
                                                    + "<a href='indexNews.jsp?idNews=" + journalNews.get(i).get(News._id) + "'>" + journalNews.get(i).get(News._title) + "</a></h3><div class='entry-post excerpt' style='font-size: 12px !important;line-height: 23px !important;'><p >" + journalNews.get(i).get(News._abstract) + "</p></div></div></article></div></a></div>");

                                        } else {
                                            htmlNews2.append("<div class='col-md-12 col-lg-12 col-sm-6 col-xs-12'>"
                                                    + "<a href='indexNews.jsp?idNews=" + journalNews.get(i).get(News._id) + "'>"
                                                    + "<li class='information' onclick='textNews(" + journalNews.get(i).get(News._id) + ")'>"
                                                    + "<div class='thumb' style='width:200px;height:120px'><img style='width:100%;height:120px' src='upload/" + journalNews.get(i).get(News._pic) + "' alt='image'>"
                                                    + "</div>"
                                                    + "<div class='text'><h3>" + journalNews.get(i).get(News._title) + "</h3><h6 style='font-size: 12px;'>" + journalNews.get(i).get(News._abstract) + "</h6>"
                                                    + "</div></li></a>"
                                                    + "</div>");

                                        }
                                    }
                                %> 
                                <div class="col-md-5 col-lg-5 col-sm-12 col-xs-12">
                                    <div class="widget widget-recent-news">
                                        <ul class="popular-news clearfix">
                                            <%=htmlNews2%>                                                                           
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-7 col-lg-7 col-sm-12 col-xs-12"><%=htmlNews%>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--end slider samaneh-->
                    <!--start about-->

                    <div  style='font-size: 12px !important;line-height: 23px !important;'>
                        <%=Content.getHtml(request, response, "درباره تعاون مسکن دادگستری", Server.db)%></div>

                    <section class="flat-row what-we-do" id="services">
                        <div class="container">
                            <div class="col-md-12">
                                <div class="title-section style3">
                                    <h1 class="title">اخرین اخبار تعاونی</h1>
                                </div>
                            </div>
                            <div class="row"  style=''>
                                <div class="col-md-12">
                                    <div class="flat-iconbox-carosuel-wrap">
                                        <div class="flat-iconbox-carosuel" data-item="4" data-nav="true" data-dots="false" data-auto="false">
                                            <%
                                                List<Map<String, Object>> rowNews = jjDatabase.separateRow(db.Select(News.tableName, News._category_id + "=3"));
                                                jjCalendar_IR dateLable = new jjCalendar_IR(rowNews.get(0).get(News._date).toString());
                                                String month = dateLable.getMonthName();
                                                int day = dateLable.getDay();
                                                int year = dateLable.getYear();
                                                for (int j = 0;
                                                        j < rowNews.size();
                                                        j++) {
                                            %>
                                            <div class="iconbox-item">
                                                <div class="iconbox left style1">
                                                    <div class="box-header">
                                                        <img src="upload/<%=rowNews.get(j).get(News._pic)%>" width='100%' style="height:160px !important"  alt="serives">

                                                            <div class="box-number">
                                                                <%=rowNews.get(j).get(News._visit)%>
                                                            </div>
                                                            <div class="dateNews" style="direction: rtl;padding:0px 7px" >
                                                                <%=day%><%=month%><%=year%>
                                                            </div>

                                                            <div class="box-title" style="height: 10vh">
                                                                <a href="indexNews.jsp?idNews=<%=rowNews.get(j).get(News._id)%>" style=""><%=rowNews.get(j).get(News._title)%></a>
                                                            </div>
                                                    </div>
                                                    <div class="box-content" style="padding: 0px 10px !important;height: 15vh;color: #303030;">
                                                        <%=rowNews.get(j).get(News._abstract)%>
                                                    </div>
                                                    <a href="indexNews.jsp?idNews=<%=rowNews.get(j).get(News._id)%>" class="box-redmore" style="padding: 5px 10px !important;text-align: left !important">ادامه خبر</a>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--end about-->
                    <!--start projectMe-->

                    <section class="flat-row about  portfolio-style2" id="work">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="title-section style2 style3">
                                        <h1 class="title">پروژه های شرکت</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="portfolio-filter">
                                        <li class="active"><a data-filter=".All" >نمایش همه</a></li>
                                            <%
                                                List<Map<String, Object>> rowCategouries = jjDatabase.separateRow(db.Select(Category_Content.tableName, Category_Content._parent + "=39"));
                                                String where = "";
                                                for (int b = 0;
                                                        rowCategouries.size()
                                                        > b; b++) {
                                                    where += ",'" + rowCategouries.get(b).get(Category_Content._id) + "'";
                                            %>
                                        <li ><a data-filter=".categouryId<%=b%>"><%= rowCategouries.get(b).get(Category_Content._title)%></a></li>
                                            <%
                                                }
                                            %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="flat-portfolio v4">
                            <div class="portfolio-wrap clearfix">
                                <div class="item All"  col-md-3 col-sm-12"  >
                                    <div class="flat-iconbox-carosuel-wrap">
                                        <div class="flat-iconbox-carosuel" data-item="4" data-nav="false" data-dots="false" data-auto="false">
                                            <%
                                                List<Map<String, Object>> rowAll = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + " IN (" + where.replaceFirst(",", "") + ")"));
                                                for (int b = 0; rowAll.size() > b; b++) {
                                            %>
                                            <div class="iconbox-item">
                                                <div class="iconbox left style2" style="width: 100%;height: 300px;">
                                                    <img src="upload/<%=rowAll.get(b).get(Content._pic)%>" alt="serives">
                                                        <div class="box-content">
                                                            <div class="box-title">
                                                                <a href="indexContent.jsp?text=<%=rowAll.get(b).get(Content._title)%>" 
                                                                   <% if (rowAll.get(b).get(Content._isAjax).toString().equals("1")) {%>
                                                                   onclick="sw('<%=rowAll.get(b).get(Content._title)%>');return false;" <%}%>><%=rowAll.get(b).get(Content._title)%></a>
                                                            </div>
                                                            <%=rowAll.get(b).get(Content._explain)%>                                                        
                                                        </div>
                                                        <ul class="portfolio-details">
                                                            <li><a href="indexContent.jsp?text=<%=rowAll.get(b).get(Content._title)%>" 
                                                                   <% if (rowAll.get(b).get(Content._isAjax).toString().equals("1")) {%>
                                                                   onclick="sw('<%=rowAll.get(b).get(Content._title)%>');return false;" <%}%> ><i class="fa fa-external-link"></i></a></li>
                                                        </ul>
                                                </div>
                                            </div><%
                                                }
                                            %>
                                        </div> 
                                    </div> 
                                </div>
                                <%
                                    for (int j = 0;
                                            rowCategouries.size()
                                            > j; j++) {
                                        List<Map<String, Object>> row2 = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + rowCategouries.get(j).get(Category_Content._id)));
                                        for (int b = 0; row2.size() > b; b++) {
                                %>
                                <div class="item categouryId<%=j%> col-md-3 col-sm-6"  >
                                    <div class="iconbox-item">
                                        <div class="iconbox left style2" style="width: 100%;height: 300px;">
                                            <img src="upload/<%=row2.get(b).get(Content._pic)%>" alt="<%=row2.get(b).get(Content._title).toString().replaceAll("\"", "")%>" >
                                                <div class="box-content">
                                                    <div class="box-title">
                                                        <a  onclick="sw('<%=row2.get(b).get(Content._title)%>')"><%=row2.get(b).get(Content._title)%></a>
                                                    </div>
                                                    <%=row2.get(b).get(Content._explain)%>                                                        
                                                </div>
                                                <ul class="portfolio-details">
                                                    <li><a class="" onclick="sw('<%=row2.get(b).get(Content._title)%>')"><i class="fa fa-external-link"></i></a></li>
                                                </ul>
                                        </div> 
                                    </div>
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </section>

                    <!--end projectMe-->
                    <!--start link-->

                    <section class="flat-row about  what-we-do " id="services" style="direction: ltr">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="title-section style3">
                                        <h1 class="title">پیوندها</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="flat-iconbox-carosuel-wrap">
                                        <div class="flat-iconbox-carosuel" data-item="4" data-nav="false" data-dots="false" data-auto="false">
                                            <%
                                                List<Map<String, Object>> row5 = jjDatabase.separateRow(db.Select(Product.tableName, Product._category_id + "=105"));
                                                for (int j = 0;
                                                        j < row5.size();
                                                        j++) {
                                            %>
                                            <div class="iconbox-item root-back">
                                                <div class="iconbox  left square">
                                                    <div class="box-header">
                                                        <div class="box-title"><img width="100%" src="upload/<%=row5.get(j).get(Product._pic1)%>"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

            </div>
            <!--end maincontent-->
            <!--end link-->



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
                                        <li class="address">
                                            اصفهان<br/><br/>خیابان نیکبخت<br/>
                                            <br/>تعاونی دادگستری اصفهان
                                        </li>
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
                                        <li class="footer-home">تعاون برای کار شایسته
                                        </li>
                                        <li class="footer-home">
                                            <a referrerpolicy="origin" target="_blank" href="https://trustseal.enamad.ir/?id=280314&amp;Code=Bb84FU0uCGo95feHpAfY">
                                                <img referrerpolicy="origin" src="https://Trustseal.eNamad.ir/logo.aspx?id=280314&amp;Code=Bb84FU0uCGo95feHpAfY" alt="" style="cursor:pointer;background-color: #8b8b8b;" id="Bb84FU0uCGo95feHpAfY"></a>
                                        </li>
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
<!--                <div class="widget widget_search">
                    <form role="search" method="get" class="search-form" action="#">
                        <label>
                            <input type="search" class="search-field" placeholder="جستجو" value="" name="s">
                        </label>
                        <input type="submit" class="search-submit" value="پیدا کردن">
                    </form>
                </div>-->
                <div id="nav_menu-2" class="widget widget-categories">
                    <h4 class="widget-title">صفحات</h4>
                    <ul>
                        <li><a href="index.jsp">صفحه اصلی</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax(false);return false;">پروژه های شرکت</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">گالری</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax(false);return false;">تماس با ما</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax(false);return false;">درباره ما</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <a class="go-top show">
            <i class="fa fa-angle-up"></i>
        </a>
        <!--start scrip-->
        <script src="Manager/js/jquery/jquery-1.10.2_1.js" type="text/javascript"></script>
        <script src="Manager/js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
        <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="template/js/jquery.cookie.js" type="text/javascript"></script>
        <script src="Manager/js/jj2.js" type="text/javascript"></script>
        <script src="template/js/index.js" type="text/javascript"></script>
        <script src="template/js/jquindex.jery.easing.js" type="text/javascript"></script>
        <script src="template/js/imagesloaded.min.js" type="text/javascript"></script>
        <script src="template/js/jquery.isotope.min.js" type="text/javascript"></script>
        <script src="template/js/jquery-waypoints.js" type="text/javascript"></script>
        <script src="template/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
        <script src="template/js/jquery.fitvids.js" type="text/javascript"></script>
        <script src="template/js/parallax.js" type="text/javascript"></script>
        <!--
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
        <script src="jsCms/comment.js" type="text/javascript"></script>
        <script>
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
                                var container = $('.portfolio-wrap');
                                var selector = ".All";
                                $(selector).css("opacity", "1");
                                $(selector).css("height", "");
                                $(this).addClass('active');
                                container.isotope({filter: selector});
                                return false;
                            }());
                            (function () {
                                var token = window.localStorage.getItem('user_token');
                                if (token === null || token === '' || token === 'undefined') {
                                    $('.register_url').show();
                                } else {
                                    $('.textlogin').show();
//                                                            new jj("do=Access_User.loginUserInHome&user_token=" + token + "&panel=userNameAfterLogin").jjAjax2(false);
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
        </script>
    </body>      
</html>
