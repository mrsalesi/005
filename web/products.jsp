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

<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>تعاونی دادگستری</title>
            <meta name="author" content="themesflat.com">

                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                    <link href="template/css/bootstrap.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/style.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/responsive.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/color1.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/animate.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/revelationSlider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/flexslider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/magpopup.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/owlcarusel.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/shortcodes.css" rel="stylesheet" type="text/css"/>
                    <link href="Manager/font-awesome.css" rel="stylesheet">
                        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
                        <link href="Manager/ionicons.css" rel="stylesheet">


                            <style id="fit-vids-style">.fluid-width-video-wrapper{width:100%;position:relative;padding:0;}.fluid-width-video-wrapper iframe,.fluid-width-video-wrapper object,.fluid-width-video-wrapper embed {position:absolute;top:0;left:0;width:100%;height:100%;}</style></head>
                            <body class="header_sticky">
                                <div id="main"></div>
                                <div id="main1">


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
                                                            <a href=""><i class="fa fa-twitter"></i></a>
                                                            <a href=""><i class="fa fa-facebook"></i></a>
                                                            <a href=""><i class="fa fa-instagram"></i></a>
                                                            <a href=""><i class="fa fa-linkedin"></i></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <header id="header" class="header clearfix">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="header-wrap clearfix">
                                                        <div class="col-md-2">
                                                            <div id="logo" class="logo">
                                                                <a href="" rel="home">
                                                                    <img src="template/img/logo.png" alt="image">
                                                                </a>
                                                            </div>
                                                            <div class="btn-menu">
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-10 pos-static">
                                                            <div class="nav-wrap has-megamenu">
                                                                <nav id="mainnav" class="mainnav">
                                                                    <ul class="menu">
                                                                        <li class="home">
                                                                            <a href="">صفحه اصلی</a>
                                                                        </li>
                                                                        <li class="has-mega-menu">
                                                                            <a class="has-mega" href="">پروژه های شرکت</a>

                                                                        </li>
                                                                        <li><a href="">تیم شرکت</a></li>
                                                                        <li><a href="">گالری</a></li>
                                                                        <li><a href="">خدمات</a>
                                                                        </li>
                                                                        <li><a href="">درباره ی شرکت </a>
                                                                        </li>
                                                                        <li><a href="">تماس با ما</a></li>


                                                                    </ul>
                                                                </nav>
                                                            </div>
                                                            <ul class="menu menu-extra">
                                                                <li class="off-canvas-toggle">
                                                                    <a  class="register_url flat-button button-color button-radius button-normal blue" style="display: none;margin: 27px;" onclick='$("#main").load("login.html"), $("#main1").hide()'>ثبت نام/ورود</a>
                                                                    <a id="userNameAfterLogin" class="textlogin flat-button button-color button-normal yellow " style="display: none;margin: 27px;"></a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </header>
                                        <div id="sw">
                                            <div class="page-title parallax parallax1" style="background-position: 50% 44px;">
                                                <div class="overlay"></div>
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="page-title-heading">
                                                                <h1 class="title">تماس با ما</h1>
                                                            </div>
                                                            <div class="breadcrumbs">
                                                                <ul>
                                                                    <li><a href="index.jsp">صفحه اصلی</a></li>
                                                                    <li><a href="">به تعاونی دادگستری خوش امدید</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> 
                                            </div>
                                            <section class="flat-row contact-page" style="background: #fff">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <form id="contactform" class="flat-contact-form style2 bg-dark height-small" method="post" action="./contact/contact-process.php">
                                                                <div class="field clearfix">
                                                                    <div class="wrap-type-input">
                                                                        <div class="input-wrap name">
                                                                            <input type="text" value="" tabindex="1" placeholder="نام" name="name" id="name" required>
                                                                        </div>
                                                                        <div class="input-wrap email">
                                                                            <input type="email" value="" tabindex="2" placeholder="نام خانوادگی" name="email" id="email" required>
                                                                        </div>
                                                                    </div>
                                                                    <div class="textarea-wrap">
                                                                        <textarea class="type-input" tabindex="3" placeholder="متن پیام" name="message" id="message-contact" required></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="submit-wrap">
                                                                    <button class="flat-button bg-theme">ارسال</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <h4 class="title-contact-info">اطلاعات تماس:</h4>
                                                            <ul class="flat-contact-info">
                                                                <li class="address">
                                                                    <i class="fa fa-home"></i> اصفهان خیابان نیکبخت تعاونی دادگستری اصفهان
                                                                </li>
                                                                <li class="phone">
                                                                    <i class="fa fa-phone-square"></i> تلفن:031-323652650
                                                                </li>
                                                                <li class="fax">
                                                                    <i class="fa fa-fax"></i> تلفن:031-323652650
                                                                </li>
                                                                <li class="email">
                                                                    <i class="fa fa-envelope"></i> <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="53273b363e3620353f322713303c3d2027212630277d303c3e"><a href="">ایمیل: taavoni@gmail.com</a></a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>

                                            <div class="flat-row map">
                                               <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3360.2314619918493!2d51.671216612191905!3d32.62665903478944!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3fbc3617cd838ac3%3A0x7647242cb80cfb7d!2z2KfYs9iq2KfZhiDYp9i12YHZh9in2YbYjCDYp9i12YHZh9in2YbYjCDYrtuM2KfYqNin2YYg2YbbjNqp2KjYrtiq2Iwg2KfbjNix2KfZhg!5e0!3m2!1sfa!2snl!4v1638787188009!5m2!1sfa!2snl" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                                            </div>

                                            <section class="flat-row mail-chimp">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-sm-7">
                                                            <div class="text">
                                                                <h3>به اطلاعات بیشتری نیاز دارید؟</h3>
                                                                <p>ما می توانیم با شما تماس بگیریم و مشکل شما را راهنمایی کنیم، فقط شماره تلفن خود را برای ما بگذارید</p>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-5">
                                                            <div class="subscribe">
                                                                <form method="post" id="subscribeForm" class="subscribe-form" action="./contact/subscribe-process.php">
                                                                    <label>
                                                                        <input type="email" id="emailsubscribe" name="email" placeholder="ورود شماره همراه..." value="" required="required" title="شماره همراه وارد کنید">
                                                                    </label>
                                                                    <input type="submit" class="submit" value="ارسال">
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                            
                                        </div>

                                        <footer class="footer" style="margin-top: 10px">
                                            <div class="footer-widgets">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <div class="widget widget_text widget_info">
                                                                <h4 class="widget-title">ادرس</h4>
                                                                <div class="textwidget">
                                                                    <ul class="footer-info">
                                                                        <li class="address">اصفهان<br><br>خیابان نیکبخت<br><br>تعاونی دادگستری اصفهان</li>
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
                                                                                                            <li class="footer-about arrow"><a>درباره ما</a></li>
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

                                                                                            <script src="template/js/jquery.min.js" type="text/javascript"></script>
                                                                                            <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
                                                                                            <script src="template/js/jj2.js" type="text/javascript"></script>
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
                                                                                            <script src="template/js/owl.carousel.js" type="text/javascript"></script>
                                                                                            <script src="template/js/jquery-validate.js" type="text/javascript"></script>
                                                                                            <script src="template/js/switcher.js" type="text/javascript"></script>
                                                                                            <script src="template/js/js.js" type="text/javascript"></script>
                                                                                            <script src="template/js/gmap3.min.js" type="text/javascript"></script>
                                                                                            <script src="template/js/main.js" type="text/javascript"></script>
                                                                                            <script src="template/js/jquery.themepunch.tools.min.js" type="text/javascript"></script>
                                                                                            <script src="template/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>
                                                                                            <script src="template/js/slider.js" type="text/javascript"></script>
                                                                                            <script src="template/js/util.js" type="text/javascript"></script>

                                                                                            <script>



                                                                        $('.portfolio-filter li').on('click', function () {
                                                                            var $container = $('.portfolio-wrap');
                                                                            var selector = $(this).find("a").attr('data-filter');
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

                                                                                new jj("do=Access_User.loginUserInHome&user_token=" + token + "&panel").jjAjax2(false);

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
                                                                        //                                                                                            </script>
                                                                                            </div></body>
                                                                                            </html>