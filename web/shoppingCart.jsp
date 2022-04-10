<%-- 
    Document   : index
    Created on : May 28, 2020, 11:51:50 PM
    Author     : Afsaneh Kiani
--%>


<%@page import="cms.tools.PaymentSetting"%>
<%@page import="jj.jjNumber"%>
<%@page import="com.oracle.jrockit.jfr.Producer"%>
<%@page import="cms.cms.Product"%>
<%@page import="cms.cms.Language"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="jj.jjDatabase"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="cms.tools.Js"%>
<%@page import="cms.access.Access_User"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="cms.cms.Category_Product"%>
<%@page import="cms.cms.Pic"%>
<%@page import="cms.cms.Content"%>
<%@page import="cms.tools.Server"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    System.out.println("shoppingCart.jsp>>>");
    Server.Connect();
    jjDatabaseWeb db = Server.db;
    String lang = jjTools.getParameter(request, "lang");
    lang = lang.equals("") ? "1" : lang;
    List<Map<String, Object>> langSetting = jjDatabaseWeb.separateRow(db.Select(Language.tableName, Language._id + " = " + lang));
    int totalPrice = 0;
    int disCountPrice = 0;
    Cookie[] cookies = request.getCookies();//آی دی کالاهای موجود در دیتابیس داخل کوکی دخیره شده است پس آنها را از کوکی میخانیم
    String productInCookieStr = "";
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equalsIgnoreCase("productsId")) {
                productInCookieStr = cookies[i].getValue().replace("%2C", ",").replace("%3B", ";");
                productInCookieStr = productInCookieStr.replaceFirst(";", "");// سمی کالن اولی را حذف میکنیم
                productInCookieStr = productInCookieStr.replace(";;", ";");//                                                
            }
        }
    } else {
    }
    String[] productsIdCountArray = productInCookieStr.split(";");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="x-ua-compatible" content="ie=edge">-->
        <title>سبد خرید</title>
        <meta name="description" content=""/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <!-- Favicons -->
        <!--<link href="Manager/fontAwsom/all.css" rel="stylesheet" type="text/css"/>-->
        <link rel="shortcut icon" href="template/images/logo/pic2.png">
        <link rel="apple-touch-icon" href="template/images/icon.png">
        <!-- Stylesheets -->
        <link rel="stylesheet" href="template/css/bootstrap.min.css">	
        <link rel="stylesheet" href="template/css/plugins.css">
        <link rel="stylesheet" href="template/css/style.css">
        <link href="Manager/css/cssProductAndList.css" rel="stylesheet" type="text/css"/>
        <link href="template/css/style_1.css" rel="stylesheet" type="text/css"/>
        <!-- Cusom css -->
        <link rel="stylesheet" href="template/css/custom.css">
        <link href="js/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
        <!-- Modernizer js -->
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
    </head>
    <body >
        <div class="ajaxLoaderDiv" style='display: none'>
            <div class="d-flex justify-content-center  mt-5   col-lg-12" style="position:fixed; left:50%; top:50%;transform: translate(-50%, -50%);z-index: 999;">
                <img src='Manager/ajaxLoader.gif' style='height: 100px;width: 100px;'/>
            </div>
        </div>
        <div class="wrapper" id="wrapper">
            <!-- Strat Slider Area -->
            <main class="main-content" id="maincontent" style="margin-top: 50px;direction: rtl;">
                <div class='ht__bradcaump__area'>
                    <div class='ht__bradcaump__container'>
                        <div class='container'>
                            <div class='row'>
                                <div class='col-lg-12'>
                                    <div class='bradcaump__inner text-center'>
                                        <h2 class='bradcaump-title'>سفارش آنلاین</h2>
                                        <nav class='bradcaump-inner'>
                                            <a class='breadcrumb-item' href='shoppingCart.jsp'>سبد خرید</a>
                                            <span class='brd-separetor'><img src='template/images/icons/next.png' alt='/'></span>
                                            <span class='breadcrumb-item active'>منوی غذا</span>
                                            <span class='brd-separetor'><img src='template/images/icons/next.png' alt='/'></span>
                                            <a class='breadcrumb-item' href='products.jsp?category=1'>صفحه اصلی</a>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12" id="sw">
                            <%for (int i = 0; i < productsIdCountArray.length; i++) {
                                    String temp = productsIdCountArray[i];
                                    String prId = temp.replaceAll(",\\d+", "");
                                    String count = temp.replaceAll("\\d+,", "");
                                    List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + prId));
                            %>
                            <div class='cartbox__item' id='prRowTr<%= row.get(0).get(Product._id).toString()%>' >
                                <div class='cartbox__item__thumb' onclick='getDetailsProduct(<%=  row.get(0).get(Product._id)%>);closeCart();' style='cursor: pointer;'>
                                    <img src='upload/<%=  row.get(0).get(Product._pic1)%>' alt='small thumbnail'>
                                </div>
                                <div class='cartbox__item__content'>
                                    <div style='text-align:right;'><a class='product-name' onclick='getDetailsProduct(<%=  row.get(0).get(Product._id)%>);closeCart();' style='cursor: pointer;'><%=  row.get(0).get(Product._name)%></a></div>
                                        <%
                                            int pric = Integer.parseInt(row.get(0).get(Product._price1).toString()) * Integer.valueOf(count);
                                            totalPrice += pric;
                                        %>
                                    <div class='sumPriceNoDiscount'><%= count%></span>*<%= row.get(0).get(Product._price1)%><%= row.get(0).get(Product._currency)%></div>
                                    <div class='sumPriceNoDiscount'><span>=<%= pric%><%= row.get(0).get(Product._currency)%></div>
                                    <%
                                        int disCount = Product.getOnlyDiscountPriceProduct(request, db, row, langSetting, 0);
                                        if (disCount == 0) {
                                            disCountPrice += pric;
                                        } else {
                                            disCountPrice += disCount;
                                        }
                                    %>
                                </div>
                                <button class='cartbox__item__remove' onclick='deletePrFromCart(<%= row.get(0).get(Product._id).toString()%>);'>
                                    <i class='fa fa-trash'></i>
                                </button>
                            </div>

                            <%
                                }
                            %>
                    <div class='cartbox__total'>
                        <div>
                            <%
                                if (totalPrice != disCountPrice) {
                            %>
                            <div><span class='cartbox__total__title'>قیمت بدون تخفیف</span><span class='price'> <%= jjNumber.getFormattedNumber(String.valueOf(totalPrice))%> </span></div>
                            <div><span class='cartbox__total__title'>قیمت با تخفیف</span><span class='price'> <%= jjNumber.getFormattedNumber(String.valueOf(disCountPrice))%></span></div>
                            <%
                            } else {
                            %>
                            <div class='grandtotal'> قابل پرداخت<span class='price'><%= jjNumber.getFormattedNumber(String.valueOf(disCountPrice))%></span></div>
                                <%
                                    }
                                    List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
                                    if (!bankRow.isEmpty()) {
                                        for (int i = 0; i < bankRow.size(); i++) {
                                %>                            
                            <%=bankRow.get(i).get(PaymentSetting._bankName)%>
                            <input name="<%=PaymentSetting._webService%>"  id="<%= bankRow.get(i).get(PaymentSetting._webService)%>"  type='radio' value="<%=bankRow.get(i).get(PaymentSetting._webService)%>" >
                            <%
                                }
                            } else {
                            %>
                            <input type='radio' style='display:none;' checked>
                            <%
                                }
                            %>
                            <div class="cartbox__buttons">
                                <a class="cartbox-close bg-success" onclick="new jj('do=Factor.insertFactorProductSite&').jjAjax2(true);" style="cursor: pointer;"><span>پرداخت</span></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-3 col-md-4 col-sm-5 col-12 product-grid-list" id="swRight">
                                <div class="category-sidebar">
                                    <div class="widget category-list" id="accordion">
                                        <div class="collapse show hover_effect" id="collapse0" aria-labelledby="heading0" data-parent="#accordion" style="text-align: right;">


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        </div>
                    </div>
            </main>
            <footer id="footer" class="footer-area footer--2 row">
                <div id="userNameAfterLogin" class="footerMenu fixed-bottom text-center text-white bg-danger p-1 row">
                    <% if(jjTools.getSeassionUserId(request)==0 ){
                        
                        %>
                    <div class="col-5">                                                            
                        <a class="login-trigger p-2 text-white" href="#" >
                            <i class="fa fa-user tx-10" style="font-size: 2em"></i>
                            ورود
                        </a>
                    </div>
                    <div class="col-2">
                    </div>
                    <div class="col-5">
                        <a class="accountbox-trigger p-2 text-white tx-center" href="#">
                            <i class="fa fa-user-plus" style="font-size: 2em"></i>                                
                            ثبت نام
                        </a>
                    </div>
                    <div class="col-12 pd-t-10">
                        <h1 style="font-size: 0.8em">
                            فست فود بامبو همدان
                        </h1>
                        pizzabambo.com
                    </div>
                </div>
                        <%
                    }
                    %>
                <!-- .Start Footer Contact Area -->
                <div class="footer__contact__area bg__cat--2">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="pd-12 mt-20">
                                    <p>
                                    </p>
                                    <p>
                                    </p>
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>
                <!-- .End Footer Contact Area -->

            </footer>

            <!-- //Footer Area -->
            <!-- Cartbox -->
            <div class="cartbox-wrap" dir='rtl'>
                <div class="cartbox text-right">
                    <button class="cartbox-close"><i class="zmdi zmdi-close"></i></button>
                    <div class="cartbox__inner text-left" id='preShoppingCart'>

                    </div>
                    <div class="cartbox__buttons">
                        <a class="cartbox-close bg-success" href="shoppingCart.jsp" style="cursor: pointer;"><span>دیدن سبد کالا</span></a>
                    </div>
                </div>
            </div>
            <!-- //Cartbox -->

            <!-- Register Form -->
            <div class="accountbox-wrapper">
                <div class="accountbox">
                    <div class="accountbox__inner">
                        <h4>ثبت نام</h4>
                        <div class="accountbox__login" id="registerForm">
                            <div>
                                <div class="single-input">
                                    <input id="user_register_family" name="user_family" type="text" placeholder="نام و نام خانوادگی ">
                                </div>
                                <div class="single-input">
                                    <input id="user_register_mobile" name="user_mobile" type="text" placeholder="موبایل">
                                </div>
                                <div class="single-input">
                                    <input id="user_register_email" name="user_email" type="text" placeholder="ایمیل">
                                </div>
                                <div class="single-input">
                                    <input id="user_register_pass" name="user_pass" type="password" placeholder="رمز">
                                </div>
                                <div class="single-input">
                                    <input id="user_password_hint" name="user_password_hint" type="password" placeholder="تکرار رمز">
                                </div>
                                <div class="single-input">
                                    <input id="user_register_address" name="user_address" type="text" placeholder="آدرس">
                                </div>

                                <div class="single-input text-center">
                                    <button onclick="register();" class="sign__btn">ثبت نام</button>
                                </div>
                                <div class="single-input" style="color:red;">
                                    <input type="text" id="errorregister">
                                </div>		   
                            </div>
                        </div>
                        <span class="accountbox-close-button"><i class="zmdi zmdi-close"></i></span>
                    </div>
                    <h3>کافه رستوران بامبو</h3>
                </div>
            </div><!-- //Register Form -->

            <!-- Login Form -->
            <div class="login-wrapper" id="loginForm">
                <div class="accountbox">
                    <div class="accountbox__inner">
                        <h4>ورود</h4>
                        <div class="accountbox__login">
                            <div>
                                <div class="single-input">
                                    <input type="text" id="user_email" name="user_email" placeholder="مویابل یا ایمیل یا نام کاربری">
                                </div>
                                <div class="single-input">
                                    <input type="password" id="user_pass" name="user_pass" placeholder="رمز">
                                </div>
                                <div class="single-input text-center">
                                    <button class="sign__btn" onclick="login();">ورود</button>
                                </div>
                                <div class="bg-warning text-center text-right" style="color:red;font-size: 0.7em;direction: rtl" id="errorLogin">
                                </div>
                                <div class="accountbox-login__others text-center">
                                    <ul class="dacre__social__link d-flex justify-content-center">
                                        <li class="facebook"><a target="_blank" href="https://www.instagram.com/fastfood_bambo/"><i class="fab fa-instagram"></i></a></li>
                                        <li class="twitter"><a target="_blank" href="https://t.me/fastfood_bambo"><i class="fab fa-telegram"></i></a></li>
                                        <li class="vimeo"><a target="_blank" href="https://api.whatsapp.com/send?abid=fastfood_bambo"><i class="fab fa-whatsapp"></i></a></li>
                                        <li class="pinterest"><a  href="tel:08132522626"><i class="fa fa-phone"></i></a></li>
                                    </ul>


                                </div>
                            </div>
                        </div>
                        <span class="accountbox-close-button"><i class="zmdi zmdi-close" ></i></span>
                    </div>
                    <h3>کافه رستوران بامبو</h3>
                </div>
            </div><!-- //Login Form -->

        </div><!-- //Main wrapper -->

        <!-- JS Files -->
        <script src="template/js/vendor/jquery-3.2.1.min.js"></script>
        <script src="template/js/jquery.cookie.min.js;" type="text/javascript"></script>
        <!--<script src="template/js/jquery-1.11.1.min.js" type="text/javascript"></script>-->
        <script src="template/js/popper.min.js"></script>
        <script src="template/js/bootstrap4-5.js" type="text/javascript"></script>
        <script src="template/js/vendor/modernizr-3.5.0.min.js"></script>            
        <script src="template/js/plugins.js"></script>
        <script src="template/js/active.js"></script>
        <script src="Manager/js/jj2.js" type="text/javascript"></script>
        <script src="template/js/index.js" type="text/javascript"></script>
        <!-- swiper js -->
        <!-- Modernizer js -->
        <script type="text/javascript">
                                        function toggleFactorBeforPay() {
                                            $("#factorBeforPay").toggle();
                                        }
                                        ;
                                        $(document).ready(function () {
                                            if (new jj('productsId').jjCookieGet() !== "") {
                                                $("#shoppingCart").html(new jj('productNums').jjCookieGet());
                                            } else {
                                                $("#shoppingCart").html(0);
                                            }
                                            new jj("do=Access_User.loginUserInSite&panel=#userNameAfterLogin&jj=1").jjAjax2(false);
                                            $("#scrollUp").click(function () {
                                                window.scrollTo(0, 0);
                                            });
                                        });
        </script>
        <div class="toast fade hide" id="myToast" style="position: fixed; top: 20px; right: 20px;background-color: #89d700;z-index: 10000">
            <div class="toast-body">
                <div style="color: #fff;"><i class="fal fa-check"></i>کالا به سبد خرید اضافه شد   </div>
            </div>
        </div>
    </body>
</html>