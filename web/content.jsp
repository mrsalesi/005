<%-- 
    Document   : index
    Created on : May 28, 2020, 11:51:50 PM
    Author     : Afsaneh Kiani
--%>

<%@page import="cms.tools.jjTools"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="cms.cms.Pic"%>
<%@page import="cms.cms.Content"%>
<%@page import="cms.tools.Server"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    ServerLog.Print("~~~~" + request.getRequestURL());//for debuging
    jjTools.ShowAllAttribute(request);
    String categuryId = jjTools.getParameter(request, "category");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="x-ua-compatible" content="ie=edge">-->
        <title><%= request.getAttribute(Content._titleTag).toString().isEmpty() ? request.getAttribute(Content._title) : request.getAttribute(Content._titleTag).toString()%> </title>

        <meta name="description" content="<%=request.getAttribute(Content._description).toString()%>"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="template1/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/SliderStyle.css" rel="stylesheet" type="text/css"/>
        <!--<link href="template1/css/font-awesome.css" rel="stylesheet" type="text/css"/>-->
        <link href="template1/css/rtl-theme.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/sassy-social-share-public.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/sassy-social-share-svg.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/style-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/styles-rtl.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/theme.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/newslider.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/cards-horizontal-slider/slick.min.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/cards-horizontal-slider/slick-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/cards-horizontal-slider/cards-horizontal-slider.css" rel="stylesheet" type="text/css"/>
        <link href="template1/remixicon/remixicon.css" rel="stylesheet" type="text/css"/>
        <link href="template1/css/card.scss" rel="stylesheet" type="text/css"/>
        <link href="template1/css/modal.css" rel="stylesheet" type="text/css"/>
        <script src="template/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=G-PDLCQWG6GK"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'G-PDLCQWG6GK');
        </script>
        <style>
            a{
                color: #0062cc;
            }
            h1{
                background: #0062cc;
                border-radius: 4px;
                color: white;
            }

        </style>
        <%= request.getAttribute(Content._style)%>
    </head>
    <body class="text-right">
    <header id="header" class="header-narrow header-semi-transparent header-flex header-bg-custom">
        <div class="header-body">
            <div class="header-container">
                <div class="header-row">
                    <div class="header-column">
                        <div class="header-logo">
                            <img  src="template1/img/logo2.png" class="custom-logo" alt=""   style="width:20%" />
                            <span class="header-nav header-nav-stripe">
                                <a class="btn header-btn-collapse-nav" data-toggle="collapse"
                                   data-target=".header-nav-main">
                                    <i class="fa fa-bars"></i>
                                </a>
                                <div class="header-nav-main header-nav-main-square header-nav-main-effect-2 header-nav-main-sub-effect-1 collapse">
                                    <nav>
                                        <button class="menu-toggle" aria-controls="primary-menu" aria-expanded="false">Primary Menu</button>
                                        <div class="menu-">
                                            <ul id="primary-menu" class="menu">
                                                <li id="menu-item-77" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-77"><a href="index_content.jsp"  aria-current="page">صفحه اصلی</a></li>
                                                <li id="menu-item-80" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-80"><a href="#">ایین نامه های کانونی</a>
                                                    <ul class="sub-menu">
                                                        <li id="menu-item-204" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-204"><a href="Server?do=Content.sw&text=ایین نامه های کانون های فرهنگی و هنری">ایین نامه کانون های فرهنگی و هنری</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="">ایین نامه داخلی شورای هماهنگی کانونها</a></li>
                                                    </ul> 
                                                </li>
                                                <li id="menu-item-79" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-79"><a href="Server?do=Content.sw&text=اساسنامه کانونهای فرهنگی و هنری"  aria-current="page">اساسنامه ی کانون های فرهنگی و هنری</a></li>
                                                <li id="menu-item-80" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-80"><a href="">جشنواره ها</a>
                                                    <ul class="sub-menu">
                                                        <li id="menu-item-204" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-204"><a href="">جشنواره رویش دانشگاهی</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="Server?do=Content.sw&text=جشنواره رویش ملی">جشنواره رویش ملی</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="">جشنواره شعر و ادب و داستان</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="">جشنواره تیاتر</a></li>
                                                    </ul> 
                                                </li>
                                                <li id="menu-item-81" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-81"><a href="/"  aria-current="page">نمایشگاه مجازی</a></li>
                                                <li id="menu-item-80" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-80"><a href="">فرم ها</a>
                                                    <ul class="sub-menu">
                                                        <li id="menu-item-204" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-204"><a href="Server?do=Content.sw&text=فرم ارائه طرح های کانونی">فرم ارایه ی طرح های کانونی</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="Server?do=Content.sw&text=فرم کاندیداتوری شورای مرکزی کانون">فرم کاندیداتوری شورای مرکزی کانون</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="Server?do=Content.sw&text=فرم درخواست مجوز تاسیس کانون">فرم درخواست مجوز تاسیس کانون</a></li>
                                                        <li id="menu-item-207" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-207"><a href="Server?do=Content.sw&text=فرم نظرسنجی کارشناس کانونها">فرم نظر سنجی کارشناس کانونها</a></li>
                                                    </ul> 
                                                </li>
                                                <li id="menu-item-83" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-83"><a href="/"  aria-current="page">اخبار کانونی</a></li>
                                                <li id="menu-item-84" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-84"><a onclick="showFormSabtenam(234);">فرم های ثبت نام کلاس ها</a>

                                                </li>
                                                <li id="menu-item-447" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-447"><a href="">ارتباط با ما</a></li>

                                            </ul>
                                        </div>                                    <!-- </ul> -->
                                    </nav>
                            </span>
                        </div>

                    </div>


                    <div class="header-column">
                        <div class="header-row">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="header-redline"></div>
</header>
<div class="row">
    <img src="template1/img/kanon_slide.png" width="100%">
</div>
<!-- //Header -->
<!-- Strat Slider Area -->      
<main class="main-content" id="maincontent" style="padding-top:117px;" >
    <div  id="sw">
        <%= request.getAttribute(Content._content)%>
    </div>
</main>
<footer class="short" id="footer">
    <%=Content.getHtml(request, response, "فوتر", Server.db)%>
</footer>

<!-- JS Files -->
<script src="template1/js/jquery-3.0.0.js" type="text/javascript"></script>    
<script src="Manager/js/jj2.js" type="text/javascript"></script>
<script src="template1/js/index.js" type="text/javascript"></script>
<script src="template1/js/bootstrap-3-3-7.min.js" type="text/javascript"></script>
<!--<script src="template1/js/bootstrap-4.0.0.min.js" type="text/javascript"></script>-->
<script src="Manager/js/ajaxfileupload.js" type="text/javascript"></script>
<script src='template1/js/cards-horizontal-slider.js' type='text/javascript'></script>
<script src="template1/js/slick.min.js" type="text/javascript"></script>
<script src="template1/js/theme_002.js" type="text/javascript"></script>
<script src="template1/newform/js/global.js" type="text/javascript"></script> 
<script src="template1/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="template1/js/calendar/Mh1PersianDatePicker.js" type="text/javascript"></script>
<script src="template1/js/city.js" type="text/javascript"></script>
<script type="text/javascript">
                                                    $(document).ready(function () {
                                                        //                                            swGetWorkshop();
                                                        //                                            swGetClass();
                                                        //                                            swGetArticle();

                                                        if (new jj('productsId').jjCookieGet() !== "") {
                                                            $("#shoppingCart").html(new jj('productNums').jjCookieGet());
                                                        } else {
                                                            $("#shoppingCart").html(0);
                                                        }
                                                        $("#demo1").slippry({});
                                                        new jj("do=Access_User.loginUserInSite&jj=1").jjAjax2(false);
                                                        $("#scrollUp").click(function () {
                                                            window.scrollTo(0, 0);

                                                        });
                                                    });
                                                    $(".fancybox").fancybox({
                                                        openEffect: 'fade',
                                                        closeEffect: 'fade',
                                                        helpers: {
                                                            title: {
                                                                type: 'over'
                                                            }
                                                        }
                                                    });






</script>
<%= request.getAttribute(Content._script)%>
</body>
</html>

