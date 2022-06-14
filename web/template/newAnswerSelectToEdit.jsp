<%-- 
    Document   : newFormToComplete
    Created on : Apr 5, 2019, 4:33:44 PM
    Author     : Mohammad
--%>

<%@page import="cms.access.Access_User"%>
<%@page import="HMIS.Department"%>
<%@page import="cms.access.Access_Group"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="jdk.nashorn.internal.runtime.regexp.JdkRegExp"%>
<%@page import="jdk.nashorn.internal.runtime.regexp.RegExp"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.AbstractList"%>
<%@page import="jj.jjNumber"%>
<%@page import="HMIS.FormAnswerSet"%>
<%@page import="HMIS.Role"%>
<%@page import="HMIS.FormQuestionOptions"%>
<%@page import="HMIS.FormQuestions"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="HMIS.FormAnswers"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="HMIS.Forms"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("db") == null) {
        return;
    }
    jjDatabaseWeb db = (jjDatabaseWeb) request.getAttribute("db");
    String answerSetId = jjTools.getParameter(request, FormAnswerSet._id);
    List<Map<String, Object>> answerSet = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, "id=" + answerSetId));
    List<Map<String, Object>> questionsRows = new ArrayList();
    List<Map<String, Object>> rowDepartments = jjDatabaseWeb.separateRow(db.Select(Department.tableName, "id>=5"));
    StringBuilder departmentOptionsHtml = new StringBuilder();// برای انتخاب بخشهایی  که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم

    StringBuilder userOptionsHtml = new StringBuilder();// برای انتخاب بخشهایی  که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم
    List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, "id>5"));
    for (int i = 0; i < userRow.size(); i++) {
        if (!userRow.get(i).get(Access_User._id).toString().isEmpty() && !userRow.get(i).get(Access_User._id).toString().equals("0")) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
            System.out.println("<option value=" + userRow.get(i).get(Access_User._id).toString() + ">" + userRow.get(i).get(Access_User._name).toString() + " " + userRow.get(i).get(Access_User._family).toString() + "</option>");
            userOptionsHtml.append("<option value=" + userRow.get(i).get(Access_User._id).toString() + ">" + userRow.get(i).get(Access_User._name).toString() + " " + userRow.get(i).get(Access_User._family).toString() + "</option>");
        }
    }
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
    String where = Forms._id + "=" + formId + " AND " + Forms._isActive + "=1";
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, where));

    if (formRow.get(0).get(Forms._departments).toString().replaceAll(",", "").equals("")) {

        for (int i = 0; i < rowDepartments.size(); i++) {
            if (!rowDepartments.get(i).get(Department._id).toString().isEmpty() && !rowDepartments.get(i).get(Department._id).toString().equals("0")) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
                System.out.println("<option value=" + rowDepartments.get(i).get(Department._id).toString() + ">" + rowDepartments.get(i).get(Department._title).toString() + "</option>");
                departmentOptionsHtml.append("<option value=" + rowDepartments.get(i).get(Department._id).toString() + ">" + rowDepartments.get(i).get(Department._title).toString() + "</option>");
            }
        }
    } else {
        /**
         * این خط کد بخش هایی که در فرم تعریف شده است را می اورد shiran
         */
        String[] departmentsArray = formRow.get(0).get(Forms._departments).toString().split(",");
        for (int i = 0; i < departmentsArray.length; i++) {
            if (jjNumber.isDigit(departmentsArray[i])) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
                System.out.println("<option value=" + departmentsArray[i] + ">" + Department.getDepartmentName(departmentsArray[i], db) + "</option>");
                departmentOptionsHtml.append("<option value=" + departmentsArray[i] + ">" + Department.getDepartmentName(departmentsArray[i], db) + "</option>");
            }
        }
    }

    if (jjNumber.isDigit(answerSetId)) {// یعنی فرمی برای ویرایش انتخاب شده است

        /**
         * تغییر جدید رفع مشکل نمایش ندادن سوالات جدید در ویرایش فرم های تکمیل
         * شده shiran shohre
         *
         */
        String sql = "SELECT * "
                + " FROM hmis_formquestions"
                + " WHERE "
                + " formQuestions_formID=" + formId + ""
                + " order by ABS(formQuestions_preority) ," + FormQuestions._id;//اولویت بندی کردن سوالات

        questionsRows = jjDatabaseWeb.separateRow(db.otherSelect(sql));
    } else {
        return;//     
    }
    //@ToDo چک کنیم اگر فرم یونیک بودو توسط آی دی این کاربر یا مک آدرس این کاربر  قبلا پر شده بود دیگر  فرم را نشان ندهیم
    if (formRow.isEmpty()) {
        return;//@ToDo نشان دادن صفحه ی خطای عدم دسترسی برای موبایل و ...
    }
    StringBuilder jjfileUplaodScripts = new StringBuilder();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="فرم <%=formRow.get(0).get(Forms._title)%>">
        <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->   
        <title><%=formRow.get(0).get(Forms._title)%></title>
        <link href="./Manager/font-awesome.css" rel="stylesheet">
        <link href="./Manager/ionicons.css" rel="stylesheet">
        <link href="./Manager/perfect-scrollbar.css" rel="stylesheet">
        <!--time picker-->
        <!--<link href="Manager/css/wickedpicker.min.css" rel="stylesheet" />-->
        <!--DataTable-->
        <!--<link href="Manager/dataTable/jquery.dataTables.css" rel="stylesheet"/>-->
        <link href="Manager/dataTable/select2.min.css" rel="stylesheet"/>

        <!--TextEditor-->
        <!--<link href="Manager/textEditor/medium-editor.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/default.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/summernote-bs4.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/summernote.css" rel="stylesheet">-->

        <!--forms-->
        <!--<link href="Manager/forms/select2.min.css" rel="stylesheet">-->
        <!--<link href="Manager/forms/spectrum.css" rel="stylesheet">-->

        <!-- MedYar مدیار CSS -->
        <link href="./Manager/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./Manager/shamcey.css">
        <!--<link href="css/bootstap.min.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="Manager/cssw.css" rel="stylesheet" type="text/css"/>-->
        <link href="./Manager/css/HMIS.css" rel="stylesheet" type="text/css"/>
        <link href="./Manager/css/endUserStyle.css" rel="stylesheet" type="text/css"/>
        <!--<link href="template/css/questionStyle.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="template/css/questionStyle_1.css" rel="stylesheet" type="text/css"/>-->   
        <!--<link href="template/css/questionStyle_2.css" rel="stylesheet" type="text/css"/>-->   
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
        <link href="./StyleBody.css" rel="stylesheet" type="text/css"/>

        <%= formRow.get(0).get(Forms._css)%>
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
    <body>            
        <section class="flat-row background-black row-promobox">
            <div class="container">
                <div class="">
                    <div class="flat-promobox style1 color-white">
                        <h3>
                            فرم شما ثبت شد                                    
                        </h3>
                        <p>
                            پس از بررسی توسط کارشناسان در صورت لزوم به شما اطلاع رسانی میشود
                            .</p>
                        <div class="promobox-group-content">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="flat-row parallax parallax4" style="background-position: 50% 23px;">
            <div class="overlay">
                <div class="container">
                    <div class="color-white">
                        <div class="breadcrumbs">
                            <p>
                                فرم ها و درخواست های شما توسط سیستم مکانیزه دریافت و با ثبت تاریخ و نام تکمیل کننده در کارتابل کارشناسان قرار میگرد                                
                            </p>
                            <p>
                                بعضی از فرم ها نیازمند جلسات و یا تصمیم گیری است و بعد از آن شما باید اطلاعات دیگری را ارسال کنید    
                            </p>
                            <p>
                                بعضی فرم ها نیازمند اقداماتی است که باید در روال کار قرار گیرد     و بصورت خودکار فرایند آن انجام میشود و شما نتایج در کارتابل مشاهده خواهید کرد
                            </p>
                            <p>
                                در صورت لزوم بعد از بررسی کارشناسان از طریق شماره های تماس شما وضعیت و نتیحه را  به اطلاع شما میرسانند
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>