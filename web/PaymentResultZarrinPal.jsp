<%-- 
    Document   : zarinPalResult
    Created on : Apr 19, 2017, 2:34:27 PM
    Author     : Rashidi
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="javax.net.ssl.HttpsURLConnection"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="cms.tools.PaymentSetting"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.net.URL"%>
<%@page import="cms.cms.Product"%>
<%@page import="jj.jjNumber"%>
<%@page import="cms.cms.Tice_config"%>
<%@page import="cms.cms.FactorItem"%>

<%@page import="cms.access.Access_User"%>
<%@page import="com.sun.xml.internal.ws.util.xml.XMLReaderComposite.State"%>
<%@page import="jj.jjDatabase"%>
<%@page import="cms.tools.Payment"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="cms.cms.Factor"%>

<%@page import="jj.jjCalendar_IR"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cms.tools.Server"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="javax.xml.ws.Holder"%>
<%@page import="zarinpal.Zarinpal"%>
<%@page import="cms.tools.ServerLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="icon" href="images_pamchal/img/circleLogo.png"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>پرداخت بانکی زرین پال| نتیجه تراکنش</title>
        <link href="css/bootstap.min.css" rel="stylesheet" type="text/css"/>


        <style>
            .result {
                width: 100%;
                text-align: center;
                color: red;
            }
            @font-face {
                font-family:'iranSans';
                src:url('images_XRdini/font/Iranian-Sans.eot');
                src:url('images_XRdini/font/Iranian-Sans.eot?#iefix') format('embedded-opentype'),url('images_XRdini/font/Iranian-Sans.woff') format('woff'),url('images_XRdini/font/Iranian-Sans.TTF') format('truetype');
                font-weight:normal;
                font-style:normal
            }
            *{
                font-family: iranSans,tahoma;
                direction: rtl;
            }

        </style>

    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="card car-header-title" >

                    <%
                        jjTools.ShowAllParameter(request);
                        jjDatabaseWeb db;
                        Server.Connect();
                        db = Server.db;
                        // در این مرحله چک میکنیم که پرداخت در بانک واقعا صورت گرفته یا نه
                        URL getAuthorityUrl = new URL("https://api.zarinpal.com/pg/v4/payment/verify.json");
                        Map<String, Object> params = new LinkedHashMap<>();
                        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(PaymentSetting.tableName, PaymentSetting._webService + "='zarinpal'"));
                        if (row.isEmpty()) {
                    %>
                    <div class="card-body">
                        <h5 class="card-title">بازکشت از صفحه ی پرداخت</h5>
                        <div class="alert alert-danger" role="alert">
                            تنظیمات درگاه در سایت درست نیست
                        </div>

                        <p class="card-text"></p>                            
                    </div>
                    <%
                            return;
                        }
                        String authority = jjTools.getParameter(request, "Authority");
                        List<Map<String, Object>> paymentRow = jjDatabaseWeb.separateRow(db.Select(Payment.tableName, Payment._refrenceId + "= '" + authority + "' "));
                        if (paymentRow.isEmpty()) {
                    %>
                    <div class="card-body">
                        <h5 class="card-title">بازکشت از صفحه ی پرداخت</h5>
                        <div class="alert alert-danger" role="alert">
                            تراکنش قبلا تایید شده یا کد احراز اصالت صحیح نیست
                        </div>
                        شماره احراز هویت
                        <p class="card-text"><%=authority%></p>                            
                    </div>
                    <%
                            return;
                        }
                        params.put("merchant_id", row.get(0).get(PaymentSetting._terminalId).toString());
                        params.put("authority", paymentRow.get(0).get(Payment._refrenceId).toString());
                        params.put("amount", paymentRow.get(0).get(Payment._amount).toString());
                        System.out.println(row.get(0).get(PaymentSetting._terminalId).toString());
                        System.out.println(paymentRow.get(0).get(Payment._refrenceId).toString());
                        System.out.println(paymentRow.get(0).get(Payment._amount).toString());
                        StringBuilder postData = new StringBuilder();
                        for (Map.Entry<String, Object> param : params.entrySet()) {
                            if (postData.length() != 0) {
                                postData.append('&');
                            }
                            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                            postData.append('=');
                            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                        }
                        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                        HttpsURLConnection conn = (HttpsURLConnection) getAuthorityUrl.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                        conn.setDoOutput(true);
                        conn.getOutputStream().write(postDataBytes);

                        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        StringBuilder result = new StringBuilder();
                        for (int c; (c = in.read()) >= 0;) {
                            System.out.print((char) c);
                            result.append((char) c);
                        }
                        JSONObject jsonObj = new JSONObject(result.toString());
                        if (jsonObj.has("data")) {
                            String ref_id = jsonObj.getJSONObject("data").get("ref_id").toString();
                            String card_pan = jsonObj.getJSONObject("data").get("card_pan").toString();
                            String card_hash = jsonObj.getJSONObject("data").get("card_hash").toString();
                            int code = (int) jsonObj.getJSONObject("data").get("code");
                            String fee_type = jsonObj.getJSONObject("data").get("fee_type").toString();
                            String fee = jsonObj.getJSONObject("data").get("fee").toString();

                            System.out.println(jsonObj);
                            System.out.println(authority);
                            System.out.println(code);
                            System.out.println(fee_type);
                            System.out.println(fee);
                            if (code == 100 || code == 101) {// کد موفق بودن اصل تراکنش 
                                //آپدیت کردن وضعیت تراکنش
                                int userId = jjTools.getSeassionUserId(request);//اگر کاربر لاگین نکرده باشد مشکلی پیش نمیآید به عنوان کاربر میهان پرداخت انجام میشود
                                String where = Access_User._id + "='" + userId + "'";
                                List<Map<String, Object>> rowUser = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, where));
                                Map<String, Object> map = new HashMap();
                                if (code == 100) {
                                    jjCalendar_IR dateIR = new jjCalendar_IR();
                                    map.put(Payment._date, dateIR.getDBFormat_8length());
                                    map.put(Payment._userId, userId);
                                    map.put(Payment._userName, rowUser.isEmpty() ? "" : (rowUser.get(0).get(Access_User._name).toString() + " " + rowUser.get(0).get(Access_User._family).toString()));
                                    map.put(Payment._comments, ref_id + "شماره ارجاء");
                                    map.put(Payment._status, Payment.status_pardakhtShode);// وضعیت تراکنش را پرداخت شده میزنیم @ToDo بهتر هست تابع چنج استتوس بگذاریم
                                    db.update(Payment.tableName, map, Payment._refrenceId + "='" + paymentRow.get(0).get(Payment._refrenceId) + "'");
                                    List<Map<String, Object>> factorRow = jjDatabase.separateRow(db.Select(Factor.tableName, Factor._id + "=" + paymentRow.get(0).get(Payment._factorId)));
                                    if (!factorRow.isEmpty()) {//  برای پرداخت های بدون فاکتور @ToDo
                                        map.clear();// برای افزایش سرعت بهتر است شی جدید نسازیم                
                                        map.put(FactorItem._statuse, FactorItem.lbl_statusePaid);
                                        db.update(FactorItem.tableName, map, FactorItem._factorId + "='" + paymentRow.get(0).get(Payment._factorId) + "'");
                                        map.clear();// برای افزایش سرعت بهتر است شی جدید نسازیم                
                                        map.put(Factor._statuse, Factor.lbl_statusePaid);
                                        map.put(Factor._discription, "شماره ارجاع:" + Payment._refrenceId);
                                        db.update(Factor.tableName, map, Factor._id + "='" + paymentRow.get(0).get(Payment._factorId) + "'");
                                    }
                                }

                    %>  
                    <div class="card text-center">
                        <div class="card-header">
                            وضعیت پرداخت
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">بازکشت از صفحه ی پرداخت</h5>
                            <div class="alert alert-success" role="alert">
                                پرداخت انجام شد
                            </div>
                            شماره ارجاع
                            <p class="card-text"><%=ref_id%></p>
                            <p class="card-text"><%=Zarinpal.checkStatus(code) + "," + code%></p>
                        </div>
                        <div class="card-footer text-muted">
                            <%= jjCalendar_IR.getViewFormat(paymentRow.get(0).get(Payment._date))%>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                        String message = jsonObj.getJSONObject("errors").get("message").toString();
                        String validations = jsonObj.getJSONObject("errors").get("validations").toString();
                        int code = (int) jsonObj.getJSONObject("errors").get("code");

                        System.out.println(message);
                        System.out.println(validations);
                        System.out.println(code);


                    %>  
                    <div class="card text-center">
                        <div class="card-header">
                            وضعیت پرداخت
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">بازکشت از صفحه ی پرداخت</h5>
                            <div class="alert alert-danger" role="alert">
                                پرداخت انجام نشد
                            </div>
                            <p class="card-text"><%=message%></p>
                            <p class="card-text"><%=Zarinpal.checkStatus(code) + "," + code%></p>
                        </div>
                        <div class="card-footer text-muted">
                            <%= jjCalendar_IR.getViewFormat(paymentRow.get(0).get(Payment._date))%>
                        </div>
                    </div>
                </div>
                <%
                    }


                %>





            </div>
        </div>
    </div>


    <!-- scrollmagic plugin -->
    <script src="./template/js/ScrollMagic.min.js"></script>
    <script src="./template/js/animation.gsap.min.js"></script>
    <!-- animated typer plugin -->
    <script src="./Manager/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="./js/bootstrap.js" type="text/javascript"></script>
    <script src="./js/jquery/jquery.cookie.min.js" type="text/javascript"></script>
    <script src="./Manager/js/jj2.js" type="text/javascript"></script>
    <script src="./Manager/js/index.js" type="text/javascript"></script>

</body>
</html>
