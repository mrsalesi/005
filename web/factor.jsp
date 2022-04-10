<%-- 
    Document   : index
    Created on : May 28, 2020, 11:51:50 PM
    Author     : Afsaneh Kiani
--%>


<%@page import="cms.tools.Payment"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="cms.cms.Tice_config"%>
<%@page import="cms.tools.PaymentSetting"%>
<%@page import="jj.jjNumber"%>
<%@page import="jj.jjNumber"%>
<%@page import="cms.cms.FactorItem"%>
<%@page import="cms.cms.Factor"%>
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
    System.out.println("factor.jsp>>>");
    Server.Connect();
    jjDatabaseWeb db = Server.db;
    String id = jjTools.getParameter(request, "id");
    List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Factor.tableName, Factor._id + "=" + id));


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="x-ua-compatible" content="ie=edge">-->
        <title>صورتحساب</title>
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
        <div class="ajaxLoaderDiv">
            <div class="d-flex justify-content-center  mt-5   col-lg-12" style="position:fixed; left:50%; top:50%;transform: translate(-50%, -50%);z-index: 999;">
                <img src='Manager/ajaxLoader.gif' style='height: 100px;width: 100px;'/>
            </div>
        </div>
        <div class="wrapper" id="wrapper">
            <!-- Header -->
            <header id="header" class="jnr__header header--one clearfix">
                <!-- Start Header Top Area -->
                
                <!-- End Mainmenu Area -->
            </header>
            <!-- //Header -->
            <!-- Strat Slider Area -->
            <main class="main-content text-right" id="maincontent" style="margin-top: 50px;direction: rtl;">
                <div class='ht__bradcaump__area'>
                    <div class='ht__bradcaump__container'>
                        <div class='container'>
                            <div class='row'>
                                <div class='col-lg-12'>
                                    <div class='bradcaump__inner text-center'>
                                        <h2 class='bradcaump-title'>صورتحساب</h2>
                                        <nav class='bradcaump-inner'>
                                            <a class='breadcrumb-item' href='factor.jsp?id=<%=id%>'>صورتحساب</a>
                                            <span class='brd-separetor'><img src='template/images/icons/next.png' alt='/'></span>
                                            <a class='breadcrumb-item' href='shoppingCart.jsp'>سبد خرید</a>
                                            <span class='brd-separetor'><img src='template/images/icons/next.png' alt='/'></span>
                                            <span class='breadcrumb-item active'>فروشگاه</span>
                                            <span class='brd-separetor'><img src='template/images/icons/next.png' alt='/'></span>
                                            <a class='breadcrumb-item' href='products.jsp'>صفحه اصلی</a>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="row">
                        <div class="col-md-12" id="sw">
                                <div >
                                    <div class='row'>
                                        <div class='col-lg-4'>
                                            وضعیت پرداخت:   <%= row.get(0).get(Factor._statuse)%>
                                        </div>
                                        <div class='col-lg-4'>
                                            شماره سریال فاکتور:   <%= row.get(0).get(Factor._id)%>
                                        </div>
                                        <div class='col-lg-4'>
                                            تاریخ:   <%= jjCalendar_IR.getViewFormat(row.get(0).get(Factor._date))%>
                                        </div>
                                    </div>
                                    <div class='card' style='border: none;'>
                                        <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات فروشنده</div>
                                        <div class='card-body bd-primary' style='border:0.5px #000 solid;'>
                                            <div class='row'>
                                                <div class='col-lg-4'>
                                                    نام شخص حقیقی و حقوقی:   <%= Tice_config.getValue(db, Tice_config._config_companyName)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    نام شرکت :   <%= Tice_config.getValue(db, Tice_config._config_companyName)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شماره اقتصادی :   <%= Tice_config.getValue(db, Tice_config._config_economicCode)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شناسه ملی :   <%= Tice_config.getValue(db, Tice_config._config_nationalCode)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    کد پستی :   <%= Tice_config.getValue(db, Tice_config._config_zipcodeCompany)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شماره تلفن و نمابر:   <%= Tice_config.getValue(db, Tice_config._config_tellCompany)%>
                                                </div>
                                                <div class='col-lg-12'>
                                                    نشانی :   <%= Tice_config.getValue(db, Tice_config._config_addressCompany)%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='card' style='border: none;margin-top: 5px;'>
                                        <div class='card-header bd-primary mg' style='border:0.5px #000 solid;margin-bottom: 3px;'>مشخصات خریدار</div>
                                        <div class='card-body bd-primary' style='border:0.5px #000 solid;'>
                                            <div class='row'>
                                                <div class='col-lg-4'>
                                                    نام شخص حقیقی و حقوقی:   <%= Access_User.getUserName(row.get(0).get(Factor._userId).toString(), db)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    نام شرکت :   <%=row.get(0).get(Factor._companyName)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شماره اقتصادی :   <%= row.get(0).get(Factor._economicCode)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شماره ملی /شناسه ملی:   <%= row.get(0).get(Factor._nationalCode)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    کد پستی :   <%= row.get(0).get(Factor._zipCode)%>
                                                </div>
                                                <div class='col-lg-4'>
                                                    شماره تلفن و نمابر:   <%= row.get(0).get(Factor._tell)%>
                                                </div>
                                                <div class='col-lg-12'>
                                                    نشانی :   <%= row.get(0).get(Factor._address)%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='row' id='refreshItemFactorInFactor'>
                                        <div style='text-align: left; font-size: 0.7em'>
                                            <div class=''>                                                
                                                <table id='refreshItemFactorInFactor' class='table table-striped table-bordered col-lg-12'><thead>
                                                        <th scope='col' >کد-قیمت</th>
                                                        <th scope='col' >تعداد</th>
                                                        <!--<th scope='col' >درصد تخفیف</th>-->
                                                        <th scope='col' >قیمت بعد از تخفیف</th>
                                                        <th scope='col' >مبلغ ارزش افزوده </th>
<!--                                                        <th scope='col' >درصد ارزش افزوده</th>-->
                                                        <th scope='col' >قیمت کل</th>
                                                    </thead><tbody>
                                                        <%                List<Map<String, Object>> rowFactorItem = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + id));
                                                            for (int i = 0; i < rowFactorItem.size(); i++) {
                                                                List<Map<String, Object>> rowProduct = jjDatabase.separateRow(db.Select(Product.tableName, Product._id + "=" + rowFactorItem.get(i).get(FactorItem._productId)));
                                                        %>
                                                        <tr>
                                                        <td scope="row"><%= rowFactorItem.get(i).get(FactorItem._id)%><br/><%= rowProduct.get(0).get(Product._name)%><br/><%= (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._originalPrice).toString()))%></td>
                                                        <td scope='col'><%= rowFactorItem.get(i).get(FactorItem._quantity).toString() %></td>
                                                        <!--<td scope='col'><%=  (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._discountPercent).toString()))%></td>-->
                                                        <td scope='col'><%= (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._priceAfterDiscount).toString()))%></td>
                                                        <td scope='col'><%= (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._valueAdded).toString()))%></td>
                                                        <!--<td scope='col'><%=  (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._percentageOfValueAdded).toString()))%></td>-->
                                                        <td scope='col'><%= (jjNumber.getFormattedNumber(rowFactorItem.get(i).get(FactorItem._totalPrice).toString()))%></td>
                                                        </tr>
                                                        <%}%>
                                                        <tr>
                                                        <td scope="row"></td>
                                                        <td scope='col'></td>
                                                        <td scope='col'><%= row.get(0).get(Factor._totalAmountValueAdded)%></td>
                                                        <td scope='col'>جمع کل</td>
                                                        <td scope='col'><%= row.get(0).get(Factor._totalAmount)%></td>
                                                        </tr>
                                                    </tbody></table>
                                            </div></div>
                                    </div>
                                    <div class='col-12 bankDiv' style='text-align: center;margin-top: 10px;'>
                                        <%
                                            List<Map<String, Object>> bankRow = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName));
                                            if (!bankRow.isEmpty()) {
                                                for (int i = 0; i < bankRow.size(); i++) {%>
                                        <%=bankRow.get(i).get(PaymentSetting._bankName)%>
                                        <input name='<%=PaymentSetting._webService%>' id='<%=PaymentSetting._webService%>' type='radio' value='<%=bankRow.get(i).get(PaymentSetting._webService)%>'>
                                        <%
                                            }
                                        } else {
                                        %>
                                        <input type='radio' style='display:none;' checked>
                                        <%
                                            }
                                        %>
                                        <input name='<%=Payment._factorId%>' id='<%=Payment._factorId%>' type='hidden' value='<%= id %>'>

                                    </div>
                                    <div class='col-lg-12'>
                                        تمامی قیمت ها به  <%=Tice_config.getValue(db, Tice_config._config_exchange_unite)%>  میباشد
                                    </div>
                                </div>
                        </div>
                        <div class='col-lg-12'>
                            <button id='paymentFactor' title='پرداخت نهایی' class='btn btn-outline-success btn-block mg-t-10 mg-b-10' onclick='payment(<%= row.get(0).get(Factor._totalAmount)%>);
                                    '>پرداخت نهایی</button>
                        </div>
                    </div>
                </div>
            </main>
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