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
    String user_bills = jjTools.getParameter(request, "user_bills");
//    System.out.println("user_token====" + user_token);
    System.out.println("user_bills====>" + user_token);
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
                    <link href="Manager/font-awesome.css" rel="stylesheet">
                        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
                        <link href="Manager/ionicons.css" rel="stylesheet">
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
                                                                    <li><a href="" onclick="new jj('do=Content.sw&panel=sw&text=درباره ی ما&jj=1').jjAjax2()">درباره ی شرکت </a></li>
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
                                    <div id="sw" style="background: #fff">
                                        <div class="container">
                                            <div class="row">
                                                <div class="container-fluid invoice-container">

                                                    <%
                                                        List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.otherSelect("SELECT product_factor.*,product_factor_item.* FROM product_factor LEFT JOIN product_factor_item ON product_factor.id = product_factor_item.product_factor_item_factorId where product_factor.product_factor_serialNumber ='" + user_bills + "'"));
                                                        List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + rowFactor.get(0).get(Factor._userId)));

                                                        jjCalendar_IR dateLable1 = new jjCalendar_IR(rowFactor.get(0).get(Factor._date).toString());
                                                        String month1 = dateLable1.getMonthName();
                                                        int day1 = dateLable1.getDay();
                                                        int year1 = dateLable1.getYear();
                                                    %>
                                                    <div class="row invoice-header">
                                                        <div class="invoice-col right">

                                                            <p><img src="template/img/logo.png" title=""></p>
                                                            <h3>صورت حساب شماره  <%=rowFactor.get(0).get(Factor._serialNumber)%></h3>

                                                        </div>
                                                        <div class="invoice-col text-center">

                                                            <div class="invoice-status">
                                                                <span class="unpaid"><%=rowFactor.get(0).get(Factor._statuse)%></span>
                                                            </div>

                                                            <div class="small-text">
                                                                تاریخ سررسید: <%=day1%><%=month1%><%=year1%> 

                                                            </div>
                                                        

                                                                                        </div>
                                                                                        </div>

                                                                                        <hr>


                                                                                            <div class="row">
                                                                                                <div class="invoice-col ">
                                                                                                    <div class="panel panel-default">
                                                                                                        <div class="panel-heading">پرداخت به </div>
                                                                                                        <div class="panel-body">
                                                                                                            <address>
                                                                                                                تعاونی مسکن کارکنان دادگستری<br>
                                                                                                                    میزبان  فعالیت های ساخت وساز<br>                        
                                                                                                                        تلفن تماس:۰۳۱-۳۶۶۳۹۸۷۱-۲<br>
                                                                                                                            ایمیل: taavoni@gmail.com<br>
                                                                                                                                </address>
                                                                                                                                </div>
                                                                                                                                </div>

                                                                                                                                </div>
                                                                                                                                <div class="invoice-col right">
                                                                                                                                    <div class="panel panel-default">
                                                                                                                                        <div class="panel-heading">صورتحساب برای </div>
                                                                                                                                        <div class="panel-body">
                                                                                                                                            <address>
                                                                                                                                                <%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%><br><%=user.get(0).get(Access_User._email)%><br>
                                                                                                                                                        <%=user.get(0).get(Access_User._address)%>, <br>
                                                                                                                                                            <%=user.get(0).get(Access_User._postalCode)%><br>
                                                                                                                                                                <%=user.get(0).get(Access_User._mobile)%>
                                                                                                                                                                <br><br>
                                                                                                                                                                        شناسه ملی:<%=user.get(0).get(Access_User._codeMeli)%><br>
                                                                                                                                                                            </address>
                                                                                                                                                                            </div>
                                                                                                                                                                            </div>
                                                                                                                                                                            </div>
                                                                                                                                                                            </div>


                                                                                                                                                                                                <br>




                                                                                                                                                                                                    <div class="panel panel-default">
                                                                                                                                                                                                        <div class="panel-heading">
                                                                                                                                                                                                        <h3 class="panel-title"><strong>اقلام صورت حساب</strong></h3>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="panel-body" style="direction: rtl">
                                                                                                                                                                                                        <div class="table-responsive">
                                                                                                                                                                                                        <table class="table table-condensed">
                                                                                                                                                                                                        <thead>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td><strong>توضیحات</strong></td>
                                                                                                                                                                                                        <td width="20%" class="text-center"><strong>مقدار</strong></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </thead>
                                                                                                                                                                                                        <tbody>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>قسط اول دارد پرداخت در تاریخ*</td>
                                                                                                                                                                                                        <td class="text-center"><%=rowFactor.get(0).get(FactorItem._item_Installment1)%></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>قسط دوم دارد پرداخت در تاریخ</td>
                                                                                                                                                                                                        <td class="text-center"><%=rowFactor.get(0).get(FactorItem._item_Installment2)%></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>قسط سوم دارد پرداخت در تاریخ*</td>
                                                                                                                                                                                                        <td class="text-center"><%=rowFactor.get(0).get(FactorItem._item_Installment3)%> تومان </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>قسط چهارم دارد پرداخت درتاریخ*</td>
                                                                                                                                                                                                        <td class="text-center"> <%=rowFactor.get(0).get(FactorItem._item_Installment4)%> تومان </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="total-row text-right"><strong>جمع با تخفیف</strong></td>
                                                                                                                                                                                                        <td class="total-row text-center"><%=rowFactor.get(0).get(FactorItem._priceAfterDiscount)%> تومان </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="total-row text-right"><strong><%=rowFactor.get(0).get(FactorItem._percentageOfValueAdded)%>% ارزش افزوده</strong></td>
                                                                                                                                                                                                        <td class="total-row text-center"> <%=rowFactor.get(0).get(FactorItem._valueAdded)%>تومان </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="total-row text-right"><strong>کل</strong></td>
                                                                                                                                                                                                        <td class="total-row text-center"> <%=rowFactor.get(0).get(FactorItem._totalPrice)%> تومان </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </tbody>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                    </div>

                                                                                                                                                                                                    <p>* نشان دهنده آیتم های مالیاتی.</p>

                                                                                                                                                                                                    <div class="transactions-container small-text">
                                                                                                                                                                                                        <div class="table-responsive">
                                                                                                                                                                                                        <table class="table table-condensed">
                                                                                                                                                                                                        <thead>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="text-center"><strong>تاریخ پرداخت</strong></td>
                                                                                                                                                                                                        <td class="text-center"><strong>درگاه</strong></td>
                                                                                                                                                                                                        <td class="text-center"><strong>شناسه پرداخت</strong></td>
                                                                                                                                                                                                        <td class="text-center"><strong>مقدار</strong></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </thead>
                                                                                                                                                                                                        <tbody>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="text-center" colspan="4">معامله مربوطی یافت نشد</td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="text-right" colspan="3"><strong>باقی مانده حساب</strong></td>
                                                                                                                                                                                                        <td class="text-center"> 4,424,310 تومان </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </tbody>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                       
                <div class="sub-heading">
                    <span>جزئیات پرداخت</span>
                </div>

                <div class="alert alert-success text-center large-text" role="alert">
                     قابل پرداخت : &nbsp; <strong> 1,415,910 تومان </strong>
                </div>

                                <div id="paymentGatewaysContainer" class="form-group">
                    <p class="small text-muted">لطفا روش پرداخت خود را انتخاب نمایید</p>

                    <div class="text-center">
                                                    <label class="radio-inline">
                                <input type="radio"
                                       name="paymentmethod"
                                       value="namellat"
                                       data-payment-type="Invoices"
                                       data-show-local=""
                                       class="payment-methods"
                                        checked                                />
                                پرداخت آنلاین توسط کارت شتاب - درگاه بانک ملت
                            </label>
                                                    <label class="radio-inline">
                                <input type="radio"
                                       name="paymentmethod"
                                       value="nasaman"
                                       data-payment-type="Invoices"
                                       data-show-local=""
                                       class="payment-methods"
                                                                       />
                                اپلود فیش پرداخت شده
                            </label>
                        <a style="width: 30%;text-align: center;display: block;margin: 10px auto" data-filter=".hammer"  href="#" class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0">
                                                                                        <i class="fa fa-pencil-alt"></i> ادامه پرداخت
                                                                                    </a>
                                            </div>
                                                                                                                                                                                                    </div>

                                                                                                                                                                                                    <div class="pull-right btn-group btn-group-sm hidden-print">
                                                                                                                                                                                                        <a href="javascript:window.print()" class="btn btn-default"><i class="fas fa-print"></i> پرینت</a>
                                                                                                                                                                                                        <a href="dl.php?type=i&amp;id=903287" class="btn btn-default"><i class="fas fa-download"></i> دریافت فایل</a>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                    </div>

                                                                                                                                                                                                    <script src="template/js/jquery.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="Manager/js/ajaxfileupload.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="template/js/jj2.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="jsCms/user.js" type="text/javascript"></script>
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
                                                                                                                                                                                                    <script src="template/js/jquery.themepunch.tools.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="template/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="template/js/slider.js" type="text/javascript"></script> 
                                                                                                                                                                                                    <script src="template/js/util.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src='template/js/owl.carousel.js' type='text/javascript'></script>
                                                                                                                                                                                                    <script src="template/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script src="template/js/dataTables.responsive.min.js" type="text/javascript"></script>
                                                                                                                                                                                                    <script>
                                                                                                                                                                                                    $(document).ready(function () {
                                                                                                                                                                                                        $('#example').DataTable();
                                                                                                                                                                                                    });
                                                                                                                                                                                                    </script>

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

                                                                                                                                                                                                        <li class="footer-Allproject arrow"><a>درباره ی ما</a></li>
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

                                                                                                                                                                                                        $(document).ready(function () {
                                                                                                                                                                                                        new jj('#userAttachFiles_sendFiles').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUser', 'user_attachFile', 'user_titleFile', "#user_divUpload");
                                                                                                                                                                                                        new jj('#userAttachFiles_sendFilesAdmin').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUserAdmin', 'user_attachFileUser', 'user_titleFile_admin', "#user_divUpload1");
                                                                                                                                                                                                        new jj('#sendPic1').jjAjaxFileUpload2('user_file_personal', '', '#user_attachPicPersonal', '#PicPreviewPersonal');
                                                                                                                                                                                                        new jj('#sendPicSignature').jjAjaxFileUpload2('user_file_Signature', '', '#user_attachPicSignature', '#PicPreviewSignature');
                                                                                                                                                                                                        new jj('#sendPicupload').jjAjaxFileUpload2('uploaded_file', '', '#user_attachPicPersonnelCard', '#PicPreview');
                                                                                                                                                                                                        new jj('#sendPicFiles').jjAjaxFileUpload3('#attachFile', '#user_attachFile');
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
                                                                                                                                                                                                        $(selector).css("display", "block");
                                                                                                                                                                                                        $(this).addClass('active');
                                                                                                                                                                                                        $container.isotope({filter: selector});
                                                                                                                                                                                                        return false;
                                                                                                                                                                                                        }());
                                                                                                                                                                                                        $('.portfolio-filter').on('click', function () {
                                                                                                                                                                                                        //                                                                                                                                                                                            var $container = $('.portfolio-wrap');
                                                                                                                                                                                                        var selector = $(this).find("a").attr('data-filter');
                                                                                                                                                                                                        if (selector == '.builder') {
                                                                                                                                                                                                        $(selector).css("display", "block");
                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".electric").css("display", "none");

                                                                                                                                                                                                        $(".hammer").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.All') {
                                                                                                                                                                                                        $(selector).css("display", "block");
                                                                                                                                                                                                        $(".builder").css("display", "none");

                                                                                                                                                                                                        $(".electric").css("display", "none");


                                                                                                                                                                                                        $(".hammer").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.electric') {
                                                                                                                                                                                                        $(selector).css("display", "block");
                                                                                                                                                                                                        $(".builder").css("display", "none");


                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".hammer").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");

                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.hammer') {
                                                                                                                                                                                                        $(selector).css("display", "block");
                                                                                                                                                                                                        $(".builder").css("display", "none");

                                                                                                                                                                                                        $(".electric").css("display", "none");

                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");

                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.table-cancle') {
                                                                                                                                                                                                        $(selector).css("display", "none");
                                                                                                                                                                                                        $(".builder").css("display", "none");

                                                                                                                                                                                                        $(".electric").css("display", "none");

                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "block");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");

                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.table-unPaid') {
                                                                                                                                                                                                        $(selector).css("display", "none");
                                                                                                                                                                                                        $(".builder").css("display", "none");

                                                                                                                                                                                                        $(".electric").css("display", "none");

                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "block");
                                                                                                                                                                                                        $(".table-paid").css("display", "none");

                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (selector == '.table-paid') {
                                                                                                                                                                                                        $(selector).css("display", "none");
                                                                                                                                                                                                        $(".builder").css("display", "none");

                                                                                                                                                                                                        $(".electric").css("display", "none");

                                                                                                                                                                                                        $(".All").css("display", "none");
                                                                                                                                                                                                        $(".table-cancle").css("display", "none");
                                                                                                                                                                                                        $(".table-unPaid").css("display", "none");
                                                                                                                                                                                                        $(".table-paid").css("display", "block");

                                                                                                                                                                                                        }
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

                                                                                                                                                                                                        </div>    </body>
                                                                                                                                                                                                        </html>
