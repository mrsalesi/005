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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fa-IR" lang="fa-IR" style="direction: rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>تعاونی دادگستری</title>
            <meta  name="author" content="sepano" />
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
            <link href="Manager/font-awesome.css" rel="stylesheet"/>
            <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
            <link href="Manager/ionicons.css" rel="stylesheet"/>
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
                                <a href="">
                                    <a  class="register_url flat-button button-color button-normal yellow " style="" onclick='$("#swLoginForm").load("login.html"), $("#sw").hide()'>ثبت نام/ورود</a>
                                    <a id="userNameAfterLogin" class="textlogin" style=""></a>
                                </a>
                                <a href=""><i class="fa fa-twitter"></i></a>
                                <a href=""><i class="fa fa-facebook"></i></a>
                                <a href=""><i class="fa fa-instagram"></i></a>
                                <a href=""><i class="fa fa-linkedin"></i></a>
                                <!--<a href=""><a id="userNameAfterLogin1" class="textlogin1" style="display: none"></a></a>-->

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
                                                <a class="has-mega"  onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2()">پروژه های شرکت</a>
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
                                                                    <li><a  onclick="new jj('do=Content.sw&panel=sw&text=' + $(this).html() + '&jj=1').jjAjax2();return false"><%=row9.get(b).get(Content._title)%></a></li> 
                                                                        <%}%>
                                                                </ul>
                                                            </div>
                                                            <%}%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li><a href="#" onclick="new jj('do=Content.sw&panel=sw&text=تیم شرکت&jj=1').jjAjax2();return false">تیم شرکت</a></li>
                                            <li><a href="#" onclick="$('#sw').load('gallery.jsp')">گالری</a></li>
                                            <!--                                                                    <li><a href="#">خدمات</a> </li>-->
                                            <li><a href="#" onclick="new jj('do=Content.sw&panel=sw&text=درباره ما&jj=1').jjAjax2()">درباره ی شرکت </a></li>
                                            <li><a href="#" onclick="new jj('do=Content.sw&panel=sw&text=تماس با ما&jj=1').jjAjax2()">تماس با ما</a></li>

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
            <div id="swLoginForm"></div>
            <div id="sw">



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
                                if (journalNews.size() >= 6) {
                                    h = journalNews.size() - 6;
                                } else {
                                    h = 0;
                                }
                            %> 
                            <%
                                for (int i = (journalNews.size() - 1); i >= h; i--) {
                                    count += 1;
                                    if (count <= 2) {
                                        htmlNews.append("<div class='col-md-6 col-lg-6 col-sm-6 col-xs-12'><a href='indexNews.jsp?idNews=" + journalNews.get(i).get(News._id) + "'><div class='post-wrap'   onclick='loadinformation(" + journalNews.get(i).get(News._id) + ")'><article class='post clearfix'><div class='featured-post' style='width:100%;height:200px'><img style='width:100% !important;height:200px !important' src='upload/" + journalNews.get(i).get(News._pic) + "' alt='image'></div><div class='content-post' style='display: grid;'><h3 class='title-post'><a href=''>" + journalNews.get(i).get(News._title) + "</a></h3><div class='entry-post excerpt' style='font-size: 12px !important;line-height: 23px !important;'><p >" + journalNews.get(i).get(News._abstract) + "</p></div></div></article></div></a></div>");

                                    } else {
                                        htmlNews2.append("<div class='col-md-12 col-lg-12 col-sm-6 col-xs-12'><a href='indexNews.jsp?idNews=" + journalNews.get(i).get(News._id) + "'><li class='information' onclick='textNews(" + journalNews.get(i).get(News._id) + ")'><div class='thumb' style='width:200px;height:120px'><img style='width:100%;height:120px' src='upload/" + journalNews.get(i).get(News._pic) + "' alt='image'></div><div class='text'><h3>" + journalNews.get(i).get(News._title) + "</h3><h6 style='font-size: 12px;'>" + journalNews.get(i).get(News._abstract) + "</h6></div></li></a></div>");

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
                                        <li class="address">اصفهان<br><br>خیابان نیکبخت<br>
                                                        <br>تعاونی دادگستری اصفهان</li>
                                                            <li class="phone">تلفن:031-323652650</li>
                                                            <li class="email">Info@example.com</li>
                                                            </ul>
                                                            </div>
                                                            </div>
                                                            </div>


                                                            </div>
                                                            </div>
                                                            </div>
                                                            </footer>
                                                            <!--end footer-->


                                                            <a class="go-top show">
                                                                <i class="fa fa-angle-up"></i>
                                                            </a>


                                                            <script src="template/js/jquery.min.js" type="text/javascript"></script>
                                                            <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
                                                            <script src="template/js/jj2.js" type="text/javascript"></script>
                                                            <script src="template/js/index.js" type="text/javascript"></script>
                                                            <script src="template/js/jquindex.jery.easing.js" type="text/javascript"></script>
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
//                                                                        $('.portfolio-filter li').on('click', function () {
//                                                                            var $container = $('.portfolio-wrap');
//                                                                            var selector = $(this).find("a").attr('data-filter');
//                                                                            if (selector == '.builder') {
//                                                                                $(selector).css("opacity", "1");
//                                                                                $(selector).css("height", "");
//                                                                                $(".All").css("opacity", "0");
//                                                                                $(".All").css("height", "0");
//                                                                                $(".electric").css("opacity", "0");
//                                                                                $(".electric").css("height", "0");
//                                                                                $(".hammer").css("opacity", "0");
//                                                                                $(".hammer").css("height", "0");
//                                                                            }
//                                                                            if (selector == '.All') {
//                                                                                $(selector).css("opacity", "1");
//                                                                                $(selector).css("height", "");
//                                                                                $(".builder").css("opacity", "0");
//                                                                                $(".builder").css("height", "0");
//                                                                                $(".electric").css("opacity", "0");
//                                                                                $(".electric").css("height", "0");
//                                                                                $(".hammer").css("opacity", "0");
//                                                                                $(".hammer").css("height", "0");
//                                                                            }
//                                                                            if (selector == '.electric') {
//                                                                                $(selector).css("opacity", "1");
//                                                                                $(selector).css("height", "");
//                                                                                $(".builder").css("opacity", "0");
//                                                                                $(".builder").css("height", "0");
//                                                                                $(".All").css("opacity", "0");
//                                                                                $(".All").css("height", "0");
//                                                                                $(".hammer").css("opacity", "0");
//                                                                                $(".hammer").css("height", "0");
//                                                                            }
//                                                                            if (selector == '.hammer') {
//                                                                                $(selector).css("opacity", "1");
//                                                                                $(selector).css("height", "");
//                                                                                $(".builder").css("opacity", "0");
//                                                                                $(".builder").css("height", "0");
//                                                                                $(".electric").css("opacity", "0");
//                                                                                $(".electric").css("height", "0");
//                                                                                $(".All").css("opacity", "0");
//                                                                                $(".All").css("height", "0");
//                                                                            }
//                                                                            $('.portfolio-filter li').removeClass('active');
//                                                                            $(this).addClass('active');
//                                                                            $container.isotope({filter: selector});
//                                                                            return false;
//                                                                        });




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
                                                            </script>
                                                            </body>      
                                                            </html>
