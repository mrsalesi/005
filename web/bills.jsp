<%-- 
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>

<%@page import="jj.jjNumber"%>
<%@page import="cms.tools.Payment"%>
<%@page import="com.sun.xml.internal.ws.api.ha.HaInfo"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URL"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="cms.cms.Tice_config"%>
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
    System.out.println("_____________________PAYMENT_____________________");
    Server.Connect();
//    request.setAttribute("text", "تعاونی دادگستری");
//    Content.sw(request, response, Server.db, true);

    String user_token = jjTools.getParameter(request, "user_token");
    String user_bills = jjTools.getParameter(request, "id");
//    System.out.println("user_token====" + user_token);
    System.out.println("user_bills====>" + user_token);
    jjDatabaseWeb db;
    db = Server.db;
    String paymentError = "";

    HttpURLConnection con = null;
    List<Map<String, Object>> rowFactor = jjDatabase.separateRow(db.otherSelect("SELECT product_factor.* ,"
            + FactorItem._priceAfterDiscount + "," + FactorItem._percentageOfValueAdded + "," + FactorItem._valueAdded + "," + FactorItem._totalPrice
            + " FROM product_factor LEFT JOIN product_factor_item ON product_factor.id = product_factor_item.product_factor_item_factorId where product_factor.id ='" + user_bills + "'"));
    List<Map<String, Object>> rowFactorItems = jjDatabase.separateRow(db.Select(FactorItem.tableName, FactorItem._factorId + "=" + rowFactor.get(0).get(Factor._id)));
    List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + rowFactor.get(0).get(Factor._userId)));
    Map<String, Object> map = new HashMap<>();
    String sepTerminalKey = Tice_config.getValue(Server.db, "config_sepTerminalId");
    if (jjNumber.isDigit(jjTools.getParameter(request, "paymentId"))) {//اگر در پارامتر ها کد پرداخت بود یعنی از صفحه ی پرداخت برگشته و باید وضعیت پرداخت را چک و اعمال کنیم
        if (jjTools.getParameter(request, "State").equals("OK")) {// وضعیت را با استفاده از پارامتر های برگشتی از درگاه پرداخت بررسی میکنیم            
            System.out.println(" call back url:::::~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>");
            String refnum = jjTools.getParameter(request, "RefNum");
            String Amount = jjTools.getParameter(request, "Amount");
            String TerminalId = jjTools.getParameter(request, "TerminalId");
            String ResNum = jjTools.getParameter(request, "ResNum");
            String State = jjTools.getParameter(request, "State");
            String Status = jjTools.getParameter(request, "Status");
            String Rrn = jjTools.getParameter(request, "Prn");
            String TraceNo = jjTools.getParameter(request, "TraceNo");
            String Token = jjTools.getParameter(request, "Token");
            String MID = jjTools.getParameter(request, "MID");
            String SecurePan = jjTools.getParameter(request, "SecurePan");
            jjTools.ShowAllParameter(request);
            JSONObject cred = new JSONObject();
            List<Map<String, Object>> payemntForVerifyRow = jjDatabase.separateRow(db.Select(Payment.tableName,
                    Payment._id + "=" + jjTools.getParameter(request, "paymentId")//رف نام همان آی دی پرداخت است که بانک بر گردانده است و  با پیمنت آی دی یکی استF
            ));
            if (!payemntForVerifyRow.get(0).get(Payment._amount).equals(Amount)) {
                paymentError += "مقدار پرداختی شما با مقدار فاکتور همخوانی ندارد. در صورت کسر وجه بعد از یک ساعت به حساب شما برگشت میشود";
            } else {
                cred.put("TerminalNumber", sepTerminalKey);
                cred.put("RefNum", refnum);
                String url = "http://sep.tkd-esf.ir:8000/verifyTxnRandomSessionkey/ipg/VerifyTransaction";
                System.out.println("raw data to sep.tkd-esf.ir >>>" + cred.toString());
                con = (HttpURLConnection) new URL(url).openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                byte[] outputBytes = cred.toString().getBytes("UTF-8");
                OutputStream os = con.getOutputStream();
                os.write(outputBytes);
                InputStream is = con.getInputStream();
                String resultStr = IOUtils.toString(is, "UTF-8");
                System.out.println(">>>>>>>>Token Json" + resultStr);
                JSONObject result = new JSONObject(resultStr);
                if (result.get("Success").toString().equals("true")) {
                    JSONObject TransactionDetail = result.getJSONObject("TransactionDetail");
                    System.out.println("ResultDescription:" + result.get("ResultDescription").toString());
                    map.clear();
                    map.put(Payment._comments, result.get("ResultDescription").toString());
                    map.put(Payment._orderId, TransactionDetail.get("RefNum").toString());
                    map.put(Payment._comments, TransactionDetail.get("RRN").toString() + "-" + TransactionDetail.get("MaskedPan").toString());
                    db.update(Payment.tableName, map, Payment._id + "=" + payemntForVerifyRow.get(0).get(Payment._id));
                    paymentError += "پرداخت با موفقیت انجام شد";
                    Payment.changeStatus(db, jjTools.getParameter(request, "paymentId"), Payment.status_pardakhtShode);
                    Factor.changeStatus(db, payemntForVerifyRow.get(0).get(Payment._factorId).toString(), Factor.status_pardakhtShode);
                } else {
                    paymentError += result.get("ResultDescription").toString();
                    System.out.println("ResultDescription" + result.get("ResultDescription").toString());
                    System.out.println("ResultCode" + result.get("ResultCode").toString());
                }
            }
            System.out.println("~_~_~_~_~_");

        } else {
            switch (jjTools.getParameter(request, "State")) {
                case "CanceledByUser":
                    paymentError += "کاربر انصراف داده است";
                    break;
                case "Failed":
                    paymentError += "پرداخت انجام نشد.";
                    break;
                case "SessionIsNull":
                    paymentError += "کاربر در بازه زمانی تعیین شده پاسخی ارسال نکرده است.";
                    break;
                case "InvalidParameters":
                    paymentError += "پارامترهای ارسالی نامعتبر است";
                    break;
                case "MerchantIpAddressIsInvalid":
                    paymentError += "آدرس سرور پذیرنده نامعتبر است ";
                    break;
                case "TokenNotFound":
                    paymentError += "توکن ارسال شده یافت نشد";
                    break;
                case "TerminalNotFound":
                    paymentError += "شماره ترمینال ارسال شده یافت نشد";
                    break;

            }

            paymentError += "  پرداخت نشده  -  کد    ";
            Payment.changeStatus(db, jjTools.getParameter(request, "paymentId"), Payment.status_pardakhtNaShode);
        }
    }
    String sepToken = "";//اگر وضعیت فاکتور پرداخت شده بود ایجاد میشود
    if (rowFactor.get(0).get(Factor._statuse).toString().equals(Factor.statusePardakhtNaShode)) {
        map.put(Payment._amount, rowFactor.get(0).get(Factor._totalAmount));
        map.put(Payment._date, jjCalendar_IR.getDatabaseFormat_8length(null, true));
        map.put(Payment._userId, jjTools.getSeassionUserId(request));
        map.put(Payment._factorId, rowFactor.get(0).get(Factor._id));
        map.put(Payment._status, Payment.status_sabteavalie);
        List<Map<String, Object>> payemntRow = jjDatabase.separateRow(db.insert(Payment.tableName, map));
        //ارسال درخواست توکن برای بانک صادرات
        JSONObject cred = new JSONObject();
        cred.put("action", "token");
        cred.put("TerminalId", sepTerminalKey);
        cred.put("Amount", rowFactor.get(0).get(Factor._totalAmount));
        cred.put("ResNum", payemntRow.get(0).get(Payment._id));
        cred.put("RedirectUrl", "https://www.tkd-esf.ir/billPaymentStatus.jsp?" + "paymentId=" + payemntRow.get(0).get(Payment._id));
//        cred.put("RedirectUrl", "http://localhost:8084/005/billPaymentStatus.jsp?" + "paymentId=" + payemntRow.get(0).get(Payment._id));
        String url = "http://sep.tkd-esf.ir:8000/onlinepg/onlinepg";
        con = (HttpURLConnection) new URL(url).openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        byte[] outputBytes = cred.toString().getBytes("UTF-8");
        OutputStream os = con.getOutputStream();
        os.write(outputBytes);
        InputStream is = con.getInputStream();
        String resultStr = IOUtils.toString(is, "UTF-8");
        System.out.println(">>>>>>>>Token Json" + resultStr);
        JSONObject result = new JSONObject(resultStr);
        System.out.println("status:" + result.get("status").toString());
        if (result.get("status").toString().equals("1")) {
            System.out.println("token:" + result.get("token").toString());
            sepToken = result.get("token").toString();
            map.put(Payment._refrenceId, result.get("token").toString());
            db.update(Payment.tableName, map, Payment._id + "=" + payemntRow.get(0).get(Payment._id));
        } else {
            System.out.println("errorCode:" + result.get("errorCode").toString());
            System.out.println("errorDesc:" + result.get("errorDesc").toString());

        }
    }
//    System.out.println("refreshToken\n" + result.get("refreshToken").toString());

%>
<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US" style="direction: rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
        <title>تعاونی دادگستری</title>
        <meta  name="author" content="themesflat.com"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
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
        <link href="Manager/font-awesome.css" rel="stylesheet"/>
        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/ionicons.css" rel="stylesheet"/>
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
            <div id="sw" style="background: #fff">
                <div class="container">
                    <div class="row">
                        <div class="container-fluid invoice-container">

                            <%
                                jjCalendar_IR dateLable1 = new jjCalendar_IR(rowFactor.get(0).get(Factor._dueDate).toString());
                                String month1 = dateLable1.getMonthName();
                                int day1 = dateLable1.getDay();
                                int year1 = dateLable1.getYear();
                            %>
                            <div class="row invoice-header">
                                <div class="invoice-col right">

                                    <p><img src="template/img/logo.png" title=""></p>
                                    <h3>صورت حساب شماره  <%=rowFactor.get(0).get(Factor._serialNumber)%></h3>
                                    <span>کد  <%=rowFactor.get(0).get(Factor._id)%></span>
                                    <br><%=paymentError%>


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
                                                    تعاونی چند منظوره کارکنان دادگستری اصفهان<br/>
                                                  <br/>                        
                                                    تلفن تماس:۰۳۱-۳۶۶۳۹۸۷۱-۲<br/>
                                                    ایمیل: tmkde@yahoo.com<br/>
                                                </address>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="invoice-col right">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">صورتحساب برای </div>
                                            <div class="panel-body">
                                                <address>
                                                    <%=user.get(0).get(Access_User._name)%> <%=user.get(0).get(Access_User._family)%><br/><%=user.get(0).get(Access_User._email)%><br/>
                                                    <%=user.get(0).get(Access_User._address)%>, <br/>
                                                    <%=user.get(0).get(Access_User._postalCode)%><br/>
                                                    <%=user.get(0).get(Access_User._mobile)%>
                                                    <br/>
                                                    شناسه ملی:<%=user.get(0).get(Access_User._codeMeli)%><br/>
                                                    <br/>
                                                    <%=rowFactor.get(0).get(Factor._discription)%>
                                                </address>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <br/>




                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><strong>صورت حساب</strong></h3>
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
                                                    <% for (int x = 0; x < rowFactorItems.size(); x++) { %>
                                                    <!--@Todo-->
                                                    <% }%>
                                                    <tr>                                                                                                                                                                                                        
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

                                <!--<p>* نشان دهنده آیتم های مالیاتی.</p>-->
                                <%if (rowFactor.get(0).get(Factor._statuse).toString().equals(Factor.status_pardakhtShode)) {
                                        //اگر وضعیت پرداخت شده بود چزئیات پرداخت را نشان میدهیم
                                    List<Map<String, Object>> paymentRow = jjDatabase.separateRow(db.Select(Payment.tableName,
                                            Payment._factorId + "=" + rowFactor.get(0).get(Factor._id)
                                            + " AND "
                                            + Payment._status + "='" + Payment.status_pardakhtShode+"'")
                                    );
                                    if (paymentRow.size() > 0) {

                                %>
                                <div class="transactions-container small-text">
                                    <div class="table-responsive">
                                        <table class="table table-condensed">

                                            <thead>
                                                <tr>
                                                    <td class="text-center"><strong>تاریخ پرداخت</strong></td>
                                                    <td class="text-center"><strong>مبلغ</strong></td>
                                                    <td class="text-center"><strong>شناسه پرداخت</strong></td>
                                                    <td class="text-center"><strong>توضیحات</strong></td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="text-center"><strong><%= paymentRow.get(0).get(Payment._date)  %></strong></td>
                                                    <td class="text-center"><strong><%= paymentRow.get(0).get(Payment._amount)  %></strong></td>
                                                    <td class="text-center"><strong><%= paymentRow.get(0).get(Payment._refrenceId)  %></strong></td>
                                                    <td class="text-center"><strong><%= paymentRow.get(0).get(Payment._comments)  %></strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%}
                                    } %>

                                <div class="sub-heading">
                                    <span>جزئیات پرداخت</span>
                                </div>

                                <% if (rowFactor.get ( 
                                        0).get(Factor._statuse).toString().equals(Factor.statusePardakhtNaShode)) {
                                %>
                                <div class="alert alert-success text-center large-text" role="alert">
                                    قابل پرداخت : &nbsp; <strong> <%=rowFactor.get(0).get(FactorItem._totalPrice)%> ریال</strong>
                                </div>
                                <div id="paymentGatewaysContainer" class="form-group">
                                    <!--<p class="small text-muted">لطفا روش پرداخت خود را انتخاب نمایید</p>-->

                                    <div class="text-center">
                                        <label class="radio-inline">
                                            <img src="template/img/logoBankSaderat.jpg"
                                                 <input type="radio"
                                                   name="paymentmethod"
                                                   value="namellat"
                                                   data-payment-type="Invoices"
                                                   data-show-local=""
                                                   class="payment-methods"
                                                   checked                                />
                                                پرداخت آنلاین توسط کارت شتاب - درگاه بانک صادرات
                                        </label>
                                        <!--                                        <label class="radio-inline">
                                                                                    <input type="radio"
                                                                                           name="paymentmethod"
                                                                                           value="nasaman"
                                                                                           data-payment-type="Invoices"
                                                                                           data-show-local=""
                                                                                           class="payment-methods"
                                                                                           />
                                                                                    اپلود فیش پرداخت شده
                                                                                </label>-->
                                        <form onload="document.forms['forms'].submit()" action="https://sep.shaparak.ir/OnlinePG/OnlinePG" method="post" > 
                                            <input type="hidden" name="Token" value="<%= sepToken%>" />                                                                                         
                                            <input name="GetMethod" type="hidden" value="false"/> <!--true | false | empty string | null--> 
                                            <input value="پرداخت" type="submit" style="width: 30%;text-align: center;display: block;margin: 10px auto" data-filter=".hammer"  href="#" class="tp-caption sfl flat-button-slider bg-button-slider-32bfc0" />                                              
                                        </form>                                        
                                    </div>
                                </div>
                                <% }%>

                                <div class="pull-right btn-group btn-group-sm hidden-print">
                                    <a href="javascript:window.print()" class="btn btn-default"> پرینت</a>
                                    <!--                                    <a href="dl.php?type=i&amp;id=903287" class="btn btn-default"><i class="fas fa-download"></i> دریافت فایل</a>-->
                                </div>
                        </div>
                    </div>
                </div>                
                <!--end footer-->
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
