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
    String text = jjTools.getParameter(request, "text");
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
        <title>تعاونی دادگستری</title>
        <meta  name="author" content="themesflat.com">
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
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
                                <div id="userNameAfterLogin" class="textlogin" style="float: right;margin: 9px;">
                                    <nav id="mainnav"><ul class="menu">
                                            <li class="userIcon"><i class="fa fa-user " style="margin: 7px;"></i>
                                                <ul class="submenu right-sub-menu">
                                                    <li><a >سلام <%= jjTools.getSeassionUserNameAndFamily(request)%></a></li>
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
                                                                for (int o = 0;
                                                                        o < row10.size();
                                                                        o++) {
                                                            %>
                                                            <div class="col-md-4">
                                                                <h2 style="text-align: right"><%=row10.get(o).get(Category_Content._title)%></h2>
                                                                <ul class="mega-menu-sub">
                                                                    <%
                                                                        List<Map<String, Object>> row9 = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + row10.get(o).get(Category_Content._id)));
                                                                        for (int b = 0; b < row9.size(); b++) {
                                                                    %>
                                                                    <li><a  onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;"><%=row9.get(b).get(Content._title)%></a></li> 
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

                    <%
                        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Content.tableName, Content._title + "='" + text +"'"));
                        Map<String, Object> map = new HashMap<>();
                        int visit = Integer.parseInt(row.get(0).get(Content._visit).toString()) + 1;
                        map.put(Content._visit, visit);
                        jjCalendar_IR dateLable = new jjCalendar_IR(row.get(0).get(Content._date).toString());
                        String month = dateLable.getMonthName();
                        int day = dateLable.getDay();
                        int year = dateLable.getYear();
                        db.update(Content.tableName, map, Content._id + "=" + row.get(0).get(Content._id));
//                        List<Map<String, Object>> rowComment = jjDatabaseWeb.separateRow(db.Select(Comment.tableName, Comment._refrenceId + " = " + row.get(0).get(News._id)));

//                        List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Content.tableName, Content._category_id + "=" + row.get(0).get(News._category_id)));
                    %>
                    <div class="post-wrap">
                        <article class="post clearfix">
                            <!-- <div class="featured-post">
                                <img src="upload/<%=row.get(0).get(Content._pic)%>" alt="image" style="width: 100%;height: 400px">
                                    <ul class="post-comment">
                                        <li class="date">
                                            <span class="day"> <%=day%> </span>
                                        </li>
                                        <li class="comment">
                                            <%=month%>
                                        </li>
                                        <li class="comment">
                                            <%=year%>
                                        </li>
                                    </ul>
                            </div> -->
                            <div class="content-post">
<!--                                <ul class="meta-post clearfix">
                                    <li class="author">
                                        <p>admin</p>
                                    </li>
                                    <li class="date">
                                        <p><%= jjCalendar_IR.getViewFormat(row.get(0).get(Content._date).toString())%></p>
                                    </li>
                                    <li class="vote">
                                        <p><%=row.get(0).get(Content._visit)%></p>
                                    </li>
                                </ul>-->
                                <div class="entry-post excerpt">
                                    <p><%=row.get(0).get(Content._hasInContentAutoWiki).toString().equals("1")? row.get(0).get(Content._contentWithWikiLinks) : row.get(0).get(Content._content)%></p>
                                    <hr/>
                                </div>
                            </div>
                        </article>
                    </div>
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
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">پروژه های شرکت</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">گالری</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">تماس با ما</a></li>
                        <li><a onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false;">درباره ما</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <a class="go-top show">
            <i class="fa fa-angle-up"></i>
        </a>
        <!--start scrip-->
        <script src="template/js/jquery.min.js" type="text/javascript"></script>
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
