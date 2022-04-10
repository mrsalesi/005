<%--
    Document   : formResult
    Created on : May 17, 2019, 5:28:43 PM
    Author     : Mohammad   
--%><%@page import="HMIS.Department"%>
<%@page import="sun.security.util.Length"%>
<%@page import="HMIS.Role"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="HMIS.FormQuestionOptions"%>
<%@page import="HMIS.FormAnswers"%>
<%@page import="HMIS.FormQuestions"%>
<%@page import="HMIS.Forms"%>
<%@page import="jj.jjNumber"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cms.access.Access_User"%>
<%@page import="jj.jjDatabaseWeb"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="HMIS.Forms"%>
<%@page import="HMIS.FormAnswerSet"%>
<%@page import="cms.tools.jjTools"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("db") == null) {
        return;
    }
    jjDatabaseWeb db = (jjDatabaseWeb) request.getAttribute("db");
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
//    String where = Forms._id + "=" + formId + " AND " + Forms._isActive + "=1";
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
    //انتخاب همه ی پاسخ های داده شده به فرم
    String sql = " SELECT hmis_formquestions.*,hmis_formAnswerSet.*,role_title,formQuestionOptions_value,formQuestionOptions_lable,formanswers_answerSet_id,formanswers_answer,formanswers_questionId "
            + " FROM hmis_formanswers LEFT JOIN hmis_formquestions on"
            + " hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
            + " LEFT JOIN hmis_formAnswerSet  on hmis_formanswers.formanswers_answerSet_id= hmis_formAnswerSet.id"
            + " LEFT JOIN hmis_role  on hmis_formanswerset.formAnswers_userRole= hmis_role.id"
            + " LEFT JOIN hmis_formquestionoptions  on hmis_formanswers.formanswers_answer= hmis_formquestionoptions.id"
            + " WHERE "
            + " formQuestions_formID=" + formId + " AND formAnswers_status='" + FormAnswerSet.statues_sabteNahei + "' group by formanswers_answerSet_id,formanswers_questionId ;";
    List<Map<String, Object>> questionsAnswerRows = jjDatabaseWeb.separateRow(db.otherSelect(sql));
    List<Map<String, Object>> questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._formID + "=" + formId));//
//    List<Map<String, Object>> roleRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT hmis_role.id,role_title from hmis_formanswerset LEFT JOIN hmis_role on formAnswers_userRole=hmis_role.id group by formAnswers_userRole;"));
    List<Map<String, Object>> roleRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT DISTINCT(formAnswers_userRole),hmis_role.id,role_title from hmis_formanswerset LEFT JOIN hmis_role on formAnswers_userRole=hmis_role.id where formAnswers_formId=" + formId + " group by formAnswers_userRole;"));//نقش کسانی که فرم را پر کردن را نشون میده
    StringBuilder roleOptionsHtml = new StringBuilder();// برای انتخاب نقش هایی که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم
    roleOptionsHtml.append("<option value=''>نقش را انتخاب کنید</option>");
    for (int i = 0; i < roleRows.size(); i++) {
        if (!roleRows.get(i).get(Role._id).toString().isEmpty()) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
            roleOptionsHtml.append("<option value=" + roleRows.get(i).get(Role._id) + ">" + roleRows.get(i).get(Role._title) + "</option>");
        }
    }
    List<Map<String, Object>> userRows = jjDatabaseWeb.separateRow(db.otherSelect("SELECT access_user.id,user_name,user_family from hmis_formanswerset LEFT JOIN access_user on formAnswers_userId=access_user.id group by formAnswers_userId;"));
    StringBuilder userOptionsHtml = new StringBuilder();// برای انتخاب نقش هایی که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم
    for (int i = 0; i < userRows.size(); i++) {
        if (!userRows.get(i).get(Access_User._id).toString().isEmpty()) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
            System.out.println("<option value=" + userRows.get(i).get(Access_User._id) + ">" + userRows.get(i).get(Access_User._name + " " + Access_User._family) + "</option>");
            userOptionsHtml.append("<option value=" + userRows.get(i).get(Access_User._id) + ">" + userRows.get(i).get(Access_User._name) + " " + userRows.get(i).get(Access_User._family) + "</option>");
        }
    }
    List<Map<String, Object>> departmentRow = jjDatabaseWeb.separateRow(db.otherSelect("SELECT "
            + " DISTINCT(hmis_formanswerset." + FormAnswerSet._departmentId + "),department.department_title,department.id "
            + " FROM  hmis_formanswerset "
            + " LEFT JOIN department ON hmis_formanswerset." + FormAnswerSet._departmentId + "=department.id "
            + " where formAnswers_formId=" + formId));
    StringBuilder departmentOptionsHtml = new StringBuilder();// برای انتخاب بخشهایی  که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم
    for (int i = 0; i < departmentRow.size(); i++) {
        if (!departmentRow.get(i).get(Department._id).toString().isEmpty() && !departmentRow.get(i).get(Department._id).toString().equals("0")) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
            System.out.println("<option value=" + departmentRow.get(i).get(Department._id).toString() + ">" + departmentRow.get(i).get(Department._title).toString() + "</option>");
            departmentOptionsHtml.append("<option value=" + departmentRow.get(i).get(Department._id).toString() + ">" + departmentRow.get(i).get(Department._title).toString() + "</option>");
        }
    }
%>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>آمار  و نتایج کلی  <%=formRow.get(0).get(Forms._title)%></title>
        <link href="./Manager/font-awesome.css" rel="stylesheet" />
        <link href="./Manager/ionicons.css" rel="stylesheet" />
        <link href="./Manager/perfect-scrollbar.css" rel="stylesheet" />
        <!--time picker-->
        <!--<link href="Manager/css/wickedpicker.min.css" rel="stylesheet" />-->
        <!--DataTable-->
        <link href="./Manager/dataTable/jquery.dataTables.css" rel="stylesheet"/>
        <link href="./Manager/dataTable/select2.min.css" rel="stylesheet"/>
        <!--TextEditor-->
        <!--<link href="Manager/textEditor/medium-editor.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/default.css" rel="stylesheet">-->
        <link href="Manager/textEditor/summernote-bs4.css" rel="stylesheet">
        <link href="Manager/textEditor/summernote.css" rel="stylesheet">        <!--forms-->
        <link href="Manager/forms/select2.min.css" rel="stylesheet">
        <!--<link href="Manager/forms/spectrum.css" rel="stylesheet">-->        <!-- MedYar مدیار CSS -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="Manager/shamcey.css">
        <!--<link href="css/bootstap.min.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="Manager/cssw.css" rel="stylesheet" type="text/css"/>-->
        <link href="Manager/css/HMIS.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/css/endUserStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <style> 
        .required{border:1px solid red;}</style>
    <body>
        <div class="sh-pagebody">
            <div class="row row-sm">
                <div class="col-xl-12">
                    <div class='col-lg'  align='center'>
                        <img src='template/hospitalLogo.png' width="10%">
                    </div>
                    <p class="text-center">آمار  و نتایج کلی  <%=formRow.get(0).get(Forms._title)%></p>
                    <!--                    <div class="card ">
                                            <div class="sh-pagebody pd-0">-->
                    <div class="row row-sm">
                        <div class="col-xl-12">
                            <div class='card  mg-t-20'>
                                <div class='card-header bg-primary tx-white'>فیلم آموزشی</div>
                                <div class='card-body pd-sm-30'>
                                    <div class='col-lg mg-10'>

                                        <a href='#' id='learnAnalyze1Icon' class='sh-pagetitle-icon' style='float:left' title='آموزش ماژول مستندات'>
                                            <div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>
                                            </div>
                                        </a>
                                        <span  style='display:block;' class='col-lg-2 mg-t-10'>
                                            <div id='Analyze1_learn' style='display:block;text-align:left'>
                                                <a href='http://94.184.89.113/upload/learnHMIS/Analyze-form_Balini.mp4'>فیلم آموزشی آنالیز بالینی</a>
                                                <br/>
                                                <a target='_blank' href=''>فایل آموزشی آنالیز بالینی</a>
                                            </div>
                                        </span>

                                        <a href='#' id='learnAnalyze2Icon' class='sh-pagetitle-icon' style='float:left' title='آموزش ماژول گزارش'>
                                            <div style='font-size: 33px'><i class='fa fa-desktop mg-t-30'></i>
                                            </div>
                                        </a>
                                        <span  style='display:block;' class='col-lg-2 mg-t-10'>
                                            <div id='Analyze2_learn' style='display:block;text-align:left'>
                                                <a href='http://94.184.89.113/upload/learnHMIS/Analyze-form_Poshtibani.mp4'>فیلم آنالیز پشتیبانی</a>
                                                <br/>
                                                <a target='_blank' href=''> فایل آموزشی آنالیز پشتیبانی</a>
                                            </div>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <!--<div  class="card ">-->
<!--                            <div id='formSectionBySection' class="mg-b-5">
                                <div class="card-header tx-white" style="cursor: pointer;height:60px;background-color: #05a092;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="$('#bodyFormSectionBySection').toggle('fast');
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار نتایج فرم در بخش های مختلف </div>
                                <div class="card  sh-pagebody" id="bodyFormSectionBySection" style="display: none;">
                                    روی محور افقی بخش های مختلف را میتوانید مقایسه کنید 
                                    <div class="row">
                                        <div class="row col-lg-12 mg-t-10">
                                            <div class="col-lg-4">
                                                <span style="color: red">*</span>
                                                ترسیم کن  نمودار را بر اساس
                                                <select  autocomplete="off" id="axel_y" name="axel_y" class="form-control" style="width: 100%" onchange="
                                                        if ($(this).val() == 'avg') {
                                                            $('#formSectionBySection_rang').prop('disabled', false);
                                                        } else {
                                                            $('#formSectionBySection_rang').prop('disabled', true);
                                                        }">
                                                    <option value='avg'> میانگین درصد پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)' selected="selected">تعداد فرم ثبت نهایی شده</option>
                                                    <option value='sum'>مجموع امتیازات فرم</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>">مجموع عددی سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین عددی سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 ">
                                                <span style="color: red">*</span>
                                                فیلتر کن براساس نام <b style="color:red">بخش </b> و یا هر فیلتر مورد دلخواه :
                                                <select  autocomplete="false" id="hmis_filter_formquestion_id3" name="hmis_filter_formquestion_id" class="form-control requierd" style="width: 100%"
                                                         required="required"
                                                         onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id3');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>

                                            <div class="col-lg-4">
                                                فقط گزینه های زیر را روی نمودار بیاورد:
                                                <select  autocomplete="true" id="hmis_filter_formquestionOption_id3" name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 mg-t-10">

                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole" name="formAnswers_userRole"  autocomplete="false" class="form-control roleSelectOption" style="width: 100%" >
                                                    <%=roleOptionsHtml%>
                                                </select>  
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن بر اساس حیطه سوال (سطح)
                                                <select   id="formSectionBySection_rang" name="formQuestions_range" style="direction: ltr;text-align: center;" class="range form-control"></select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input  autocomplete="off" id="formAnswers_date_from4" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to4"  autocomplete="off" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from4_c"  autocomplete="off" name="formAnswers_date_from_c" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to4_c"  autocomplete="off"   name="formAnswers_date_to_c" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formSectionBySection(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>                                        *****************************************-->
                            <div id='formSectionBySection2' class="mg-b-5">
                                <div class="card-header tx-white" style="cursor: pointer;height:60px;background-color: #05a092;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="$('#bodyFormSectionBySection2').toggle('fast');
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار نتایج فرم در بخش های مختلف  </div>
                                <div class="card  sh-pagebody" id="bodyFormSectionBySection2" style="display: none;">
                                    روی محور افقی بخش های مختلف را میتوانید مقایسه کنید 
                                    <div class="row">
                                        <div class="row col-lg-12 mg-t-10">
                                            <div class="col-lg-4">
                                                <span style="color: red">*</span>
                                                ترسیم کن  نمودار را بر اساس
                                                <select  autocomplete="off" id="axel_y" name="axel_y" class="form-control" style="width: 100%" onchange="
                                                        if ($(this).val() == 'avg') {
                                                            $('#formSectionBySection_rang2').prop('disabled', false);
                                                        } else {
                                                            $('#formSectionBySection_rang2').prop('disabled', true);
                                                        }">
                                                    <option value='avg'> میانگین درصد پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)' selected="selected">تعداد فرم ثبت نهایی شده</option>
                                                    <option value='sum'>مجموع امتیازات فرم</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>">مجموع عددی سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین عددی سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 ">
                                                <span style="color: red">*</span>
                                                فیلتر کن براساس نام <b style="color:red">بخش </b>:
                                                <select  autocomplete="false" multiple id="hmis_filter_department_id" name="hmis_filter_department_id" class="form-control requierd department" style="width: 100%"
                                                         required="required"
                                                         onchange="">
                                                    <option value="">همه بخش ها</option>
                                                    <%=departmentOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4 mg-t-10">
                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole2" name="formAnswers_userRole"  autocomplete="false" class="form-control roleSelectOption" style="width: 100%" >
                                                    <%=roleOptionsHtml%>
                                                </select>  
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن بر اساس حیطه سوال (سطح)
                                                <select   id="formSectionBySection_rang2" name="formQuestions_range" style="direction: ltr;text-align: center;" class="range form-control"></select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input  autocomplete="off" id="formAnswers_date_from2-4" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to2-4"  autocomplete="off" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from2-4_c"  autocomplete="off" name="formAnswers_date_from_c" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ   
                                                <input id="formAnswers_date_to2-4_c"  autocomplete="off"   name="formAnswers_date_to_c" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formSectionBySection2(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>                       
                            <!--*****************************************-->
<!--                            <div class=" mg-b-5" id="formQuestionByQuestionAnalyses" >
                                <div class="card-header tx-white" style="cursor: pointer;height:60px;background-color: #51dea9;
                                     border-radius: 20px;line-height:  45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').toggle('fast');
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار سوالات فرم</div>
                                <div class="card  sh-pagebody" id="bodyFormQuestionByQuestionAnalyses" style="display: none;">                                            
                                    <p>
                                        درصد پاسخگویی به همه ی سوالات در یک نمودار نشان داده میشود
                                        <br/>
                                    </p>

                                    <div class="row">
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                انتخاب کن  سوال مورد نظر برای آنالیز و رسم نمودار :
                                                <select id="hmis_formquestions_id2"  autocomplete="off" name="hmis_formquestions_id" class="form-control" style="width: 100%" multiple="" >
                                                    <option value="ALL"> همه سوالات</option>                                                            <%                                                            for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>                                                            <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن براساس نام <b style="color:red">بخش </b> یا هر فیلتر دلخواه:
                                                <select id="hmis_filter_formquestion_id2"  autocomplete="off" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id2');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>

                                            <div class="col-lg-4">
                                                انتخاب کن براساس نام <b style="color:red">بخش </b> و یا حالت های فیلتر  انتخاب شده :
                                                <select id="hmis_filter_formquestionOption_id2"  autocomplete="off" name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                حیطه سوال (سطح)
                                                <select   id="formQuestionByQuestion_rang" name="formQuestions_range" style="direction: ltr;text-align: center;" class="range form-control"></select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole" name="formAnswers_userRole"  autocomplete="off" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_from1" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_to1" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input   autocomplete="off" id="formQuestionByQuestion_date_from1_c" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_to1_c" name="formAnswers_date_to2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formQuestionByQuestionAnalyses(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                                                                        <div class="col-lg-12 row">
                                                                                            نوع نمودار
                                                                                            <select id="chartType" name="chartType" class="form-control" style="width: 100%">
                                                                                                <option value="bar">ستونی</option>
                                                                                                <option value="line">خطی</option>
                                                                                            </select>
                                                                                        </div>
                                                                                        <div class="col-lg-12 row">
                                                                                            نوع پاسخ ها
                                                                                            <select id="dataType" name="dataType" class="form-control" style="width: 100%">
                                                                                                <option value="value">مقدار</option>
                                                                                                <option value="percentage">درصد</option>
                                                                                            </select>
                                                                                        </div>

                                    </div>
                                </div>   
                            </div>                         -->
                            <div class=" mg-b-5" id="formQuestionByQuestionAnalyses2" >
                                <div class="card-header tx-white" style="cursor: pointer;height:60px;background-color: #51dea9;
                                     border-radius: 20px;line-height:  45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').toggle('fast');
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار سوالات فرم</div>
                                <div class="card  sh-pagebody" id="bodyFormQuestionByQuestionAnalyses2" style="display: none;">                                            
                                    <p>
                                        درصد پاسخگویی به همه ی سوالات در یک نمودار نشان داده میشود
                                        <br/>
                                    </p>

                                    <div class="row">
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                انتخاب کن  سوال مورد نظر برای آنالیز و رسم نمودار :
                                                <select id="hmis_formquestions_id-2"  autocomplete="off" name="hmis_formquestions_id" class="form-control" style="width: 100%" multiple="" >
                                                    <option value="ALL"> همه سوالات</option>                                                            <%                                                            for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>                                                            <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن براساس نام <b style="color:red">بخش </b> یا هر فیلتر دلخواه:
                                                <select id="hmis_filter_formquestion_id-2"  autocomplete="off" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id-2');">  
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>

                                            <div class="col-lg-4">
                                                انتخاب کن براساس نام <b style="color:red">بخش </b> و یا حالت های فیلتر  انتخاب شده :
                                                <select id="hmis_filter_formquestionOption_id-2"  autocomplete="off" name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                فیلتر کن براساس نام <b style="color:red">بخش </b>:
                                                <select id="hmis_filter_department_id2" multiple="" autocomplete="off" name="hmis_filter_department_id" class="form-control" style="width: 100%">
                                                    <option value="">همه بخش ها</option>
                                                    <%=departmentOptionsHtml%>  
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                حیطه سوال (سطح)
                                                <select   id="formQuestionByQuestion_rang-2" name="formQuestions_range" style="direction: ltr;text-align: center;" class="range form-control"></select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole-2" name="formAnswers_userRole"  autocomplete="off" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_from1-2" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_to1-2" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input   autocomplete="off" id="formQuestionByQuestion_date_from1_c-2" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input  autocomplete="off" id="formQuestionByQuestion_date_to1_c-2" name="formAnswers_date_to2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formQuestionByQuestionAnalyses2(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>   
                            </div>                         
                            <!--</div>-->
<!--                            <div class="mg-b-5" id="analyzeFormByQuestion">
                                <div class="card-header  tx-white"  style="cursor: pointer;height:60px;background-color: #7681a5;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').toggle('fast');
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار پاسخ های داده شده به هر سوال </div>
                                <div class="card sh-pagebody" id="bodyAnalyzeFormByQuestion" style="display: none;">                                            
                                    <p>
                                        درصد و آمار گزینه های تکمیل شده را میدهد،
                                        <br/>

                                    <div class="row">
                                        <div class="row col-lg-12 mg-t-10">
                                            <div class="col-lg-4">
                                                انتخاب کن سوال مورد نظر برای آنالیز و رسم نمودار :
                                                <select id="hmis_formquestions_id" name="hmis_formquestions_id" class="form-control" style="width: 100%" >
                                                    <%                                                            for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>                                                        <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن براساس نام <b style="color:red">بخش </b> و یا فیلتر دلخواه :
                                                <select id="hmis_filter_formquestion_id" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                انتخاب کن نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id" name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 mg-t-10">
                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole" name="formAnswers_userRole" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from1" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to1" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from1_c" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to1_c" name="formAnswers_date_to2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4 ">
                                                نوع پاسخ ها
                                                <select id="dataType" name="dataType" class="form-control" style="width: 100%">
                                                    <option value="percentage">درصد</option>
                                                    <option value="value">مقدار</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 ">
                                                نوع نمودار
                                                <select id="chartType" name="chartType" class="form-control" style="width: 100%">
                                                    <option value="pie">دایره ای</option>
                                                    <option value="line">خطی</option>
                                                    <option value="bar">ستونی</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formAllResult_byQuestion(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>-->
                            <div class="mg-b-5" id="analyzeFormByQuestion2">
                                <div class="card-header  tx-white"  style="cursor: pointer;height:60px;background-color: #7681a5;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').toggle('fast');
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار پاسخ های داده شده به هر سوال </div>
                                <div class="card sh-pagebody" id="bodyAnalyzeFormByQuestion2" style="display: none;">                                            
                                    <p>
                                        درصد و آمار گزینه های تکمیل شده را میدهد،
                                        <br/>

                                    <div class="row">
                                        <div class="row col-lg-12 mg-t-10">
                                            <div class="col-lg-4">
                                                انتخاب کن سوال مورد نظر برای آنالیز و رسم نمودار :
                                                <select id="hmis_formquestions_id8" name="hmis_formquestions_id" class="form-control" style="width: 100%" >
                                                    <%                                                            for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>                                                        <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلتر کن براساس نام <b style="color:red">بخش </b> و یا فیلتر دلخواه :
                                                <select id="hmis_filter_formquestion_id8" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id8');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                انتخاب کن نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id8" name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 mg-t-10">
                                                فیلتر براساس نقش هایی که این فرم را تکمیل کرده اند
                                                <select id="formAnswers_userRole8" name="formAnswers_userRole" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">

                                            <div class="col-lg-12 row mg-t-10">
                                                <div class="col-lg-4">
                                                    فیلتر کن براساس نام <b style="color:red">بخش </b>:
                                                    <select id="hmis_filter_department_id8"  multiple="" autocomplete="off" name="hmis_filter_department_id" class="form-control" style="width: 100%">
                                                        <option value="">همه بخش ها</option>  
                                                        <%=departmentOptionsHtml%>  
                                                    </select>     
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from1-8" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to1-8" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row ">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from1_c-8" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to1_c-8" name="formAnswers_date_to2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4 ">
                                                نوع پاسخ ها
                                                <select id="dataType" name="dataType" class="form-control" style="width: 100%">
                                                    <option value="percentage">درصد</option>
                                                    <option value="value">مقدار</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 ">
                                                نوع نمودار
                                                <select id="chartType" name="chartType" class="form-control" style="width: 100%">
                                                    <option value="pie">دایره ای</option>
                                                    <option value="line">خطی</option>
                                                    <option value="bar">ستونی</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" onclick="formAllResult_byQuestion2(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
<!--                            <div id='formCountResult_period' class="mg-b-5">
                                <div class="card-header tx-white"  style="cursor: pointer;height:60px;background-color: #4e597e9e;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem"  onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').toggle('fast');
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار عملکرد تکمیل کنندگان در تکمیل این فرم</div>
                                <div class="card sh-pagebody" id="bodyFormCountResult_period" style="display: none;">
                                    <div class="row">
                                        <div class="row col-lg-12">
                                            <div class="col-lg-4">
                                                ترسیم کن نمودار را براساس 
                                                <select id="axel_y" autocomplete="off" name="axel_y" class="form-control" style="width: 100%">
                                                    <option value='avgOfPercents'> میانگین درصد های پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)'>تعداد فرم ثبت نهایی شده</option>
                                                    <option value='avg'>میانگین مجموع امتیازات فرم</option>
                                                    <option value='sum'>مجموع امتیازات فرم</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین نتایج در  دوره برای سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>"> مجموع نتایج در دوره برای سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>    
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلترکن براساس تکمیل کنندگان
                                                <select id="formAnswers_userRole" autocomplete="off" name="formAnswers_userRole" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                فیلتر براساس نام <b style="color:red">بخش </b> و یا هر فیلتر دلخواه   :
                                                <select id="hmis_filter_formquestion_id5" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id5');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id5"  name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                محور افقی دوره های زمانی :
                                                <select id="div" name="div" autocomplete="off"  class="form-control" style="width: 100%">
                                                    <option value="">انتخاب کنید</option>
                                                    <option value="1">روزانه</option>
                                                    <option value="7">7 روزه</option>
                                                    <option value="30" selected="selected">30 روزه</option>
                                                    <option value="60">60 روزه</option>
                                                    <option value="90">90 روزه</option>
                                                    <option value="180">180 روزه</option>
                                                    <option value="360">360 روزه</option>
                                                </select>
                                            </div>                                                
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from3" autocomplete="off" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to3" autocomplete="off" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from2_c" autocomplete="off" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" 
                                                                  onclick="formCountResult_period(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>-->
                            <div id='formCountResult_period2' class="mg-b-5">
                                <div class="card-header tx-white"  style="cursor: pointer;height:60px;background-color: #4e597e9e;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem"  onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').toggle('fast');
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار عملکرد تکمیل کنندگان در تکمیل این فرم </div>
                                <div class="card sh-pagebody" id="bodyFormCountResult_period2" style="display: none;">
                                    <div class="row">
                                        <div class="row col-lg-12">
                                            <div class="col-lg-4">
                                                ترسیم کن نمودار را براساس 
                                                <select id="axel_y-2" autocomplete="off" name="axel_y" class="form-control" style="width: 100%">
                                                    <option value='avgOfPercents'> میانگین درصد های پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)'>تعداد فرم ثبت نهایی شده</option>
                                                    <option value='avg'>میانگین مجموع امتیازات فرم</option>
                                                    <option value='sum'>مجموع امتیازات فرم</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {%>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین نتایج در  دوره برای سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>"> مجموع نتایج در دوره برای سوال <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>    
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                فیلترکن براساس تکمیل کنندگان
                                                <select id="formAnswers_userRole-2" autocomplete="off" name="formAnswers_userRole" class="form-control roleSelectOption" style="width: 100%" multiple="">
                                                    <%=roleOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                فیلتر براساس نام <b style="color:red">بخش </b> و یا هر فیلتر دلخواه   :
                                                <select id="hmis_filter_formquestion_id5-2" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id5-2');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id5-2"  name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">                                                    
                                            <div class="col-lg-4 ">
                                                <span style="color: red">*</span>  
                                                فیلتر کن براساس نام <b style="color:red">بخش </b>:
                                                <select  autocomplete="false" multiple id="hmis_filter_department_id5-2" name="hmis_filter_department_id" class="form-control requierd department" style="width: 100%"
                                                         required="required"
                                                         onchange="">
                                                    <option value="">همه بخش ها</option>
                                                    <%=departmentOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                محور افقی دوره های زمانی :
                                                <select id="div-2" name="div" autocomplete="off"  class="form-control" style="width: 100%">
                                                    <option value="">انتخاب کنید</option>
                                                    <option value="1">روزانه</option>
                                                    <option value="7">7 روزه</option>
                                                    <option value="30" selected="selected">30 روزه</option>
                                                    <option value="60">60 روزه</option>
                                                    <option value="90">90 روزه</option>
                                                    <option value="180">180 روزه</option>
                                                    <option value="360">360 روزه</option>
                                                </select>
                                            </div>                                                
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from3-2" autocomplete="off" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to3-2" autocomplete="off" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg mg-t-10">     مقایسه شود با نتایج دوره مشابه </div>
                                        <div class="col-lg-12 row">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from2_c-2" autocomplete="off" name="formAnswers_date_from2" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" 
                                                                  onclick="formCountResult_period2(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
<!--                            <div id='formCountResult_turnover' class="mg-b-5">
                                <div class="card-header  tx-white"  style="cursor: pointer;height:60px;background-color: #ef7474;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').toggle('fast');
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار مقایسه نتایج تکمیل کنندگان با همدیگر</div>
                                <div class="card sh-pagebody" id='bodyFormCountResult_turnover' style="display: none;">
                                    <div class="row">
                                        <div class="row col-lg-12">
                                            <div class="col-lg-12">
                                                این گزارش برای اینست که بدانیم نقش ها و یا افرا در یک نمودار به موارد مختلف یک فرم چگونه پاسخ داده اند
                                                <br/>
                                                <br/>
                                            </div >
                                            <div class="col-lg-4">
                                                ترسیم کنید نمودار را براساس
                                                <select id="axel_y" name="axel_y" class="form-control" style="width: 100%">
                                                    <option value='avgOfPercents'>میانگین درصد های پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)'>تعداد فرم ثبت نهایی شده</option>
                                                    <option value='avg'>میانگین مجموع نتایج فرم</option>
                                                    <option value='sum'>مجموع امتیازات</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if ((questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox"))) {
                                                    %>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین امتیاز های سوال  :
                                                        <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>">مجموع امتیاز های سوال  :
                                                        <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                </select> 
                                            </div>   
                                            <div class="col-lg-4">
                                                فیلتر براساس نام <b style="color:red">بخش </b> و یا فیلتر دلخواه  :
                                                <select id="hmis_filter_formquestion_id4" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id4');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id4"  name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                محور افقی  به تفکیک کاربران زیر :
                                                <select id="userFilter" name="axel_x_users" class="form-control roleSelectOption" style="width: 100%" multiple >
                                                    <option value="formAnswers_userName">همه ی کاربران</option>
                                                    <%=userOptionsHtml%> 
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                محور افقی  به تفکیک نقش های زیر :
                                                <select id="rolesFilter" name="axel_x_roles" class="form-control roleSelectOption" style="width: 100%" multiple >
                                                    <option value="formAnswers_userRole">همه ی نقش های سیستم</option>
                                                    <%=roleOptionsHtml%> 
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from2" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to2" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" 
                                                                  onclick="formCountResult_turnover(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>-->
                            <div id='formCountResult_turnover2' class="mg-b-5">
                                <div class="card-header  tx-white"  style="cursor: pointer;height:60px;background-color: #ef7474;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').toggle('fast');
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').hide();">آنالیز و رسم نمودار مقایسه نتایج تکمیل کنندگان با همدیگر </div>
                                <div class="card sh-pagebody" id='bodyFormCountResult_turnover2' style="display: none;">
                                    <div class="row">
                                        <div class="row col-lg-12">
                                            <div class="col-lg-12">
                                                این گزارش برای اینست که بدانیم نقش ها و یا افرا در یک نمودار به موارد مختلف یک فرم چگونه پاسخ داده اند
                                                <br/>
                                                <br/>
                                            </div >
                                            <div class="col-lg-4">
                                                ترسیم کنید نمودار را براساس
                                                <select id="axel_y-2" name="axel_y" class="form-control" style="width: 100%">
                                                    <option value='avgOfPercents'>میانگین درصد های پاسخگویی فرم(نتیجه فرم)</option>
                                                    <option value='COUNT(hmis_formanswerset.id)'>تعداد فرم ثبت نهایی شده</option>
                                                    <option value='avg'>میانگین مجموع نتایج فرم</option>
                                                    <option value='sum'>مجموع امتیازات</option>
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if ((questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("number")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox"))) {
                                                    %>
                                                    <option value="avg-<%=questionsRow.get(i).get(FormQuestions._id)%>">میانگین امتیاز های سوال  :
                                                        <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <option value="sum-<%=questionsRow.get(i).get(FormQuestions._id)%>">مجموع امتیاز های سوال  :
                                                        <%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                </select> 
                                            </div>   
                                            <div class="col-lg-4">
                                                فیلتر براساس نام <b style="color:red">بخش </b> و یا فیلتر دلخواه  :
                                                <select id="hmis_filter_formquestion_id4-2" name="hmis_filter_formquestion_id" class="form-control" style="width: 100%"
                                                        onchange="getOptionForFilter($(this).val(), '#hmis_filter_formquestionOption_id4-2');">
                                                    <option value="" selected="selected">یک سوال را انتخاب کنید</option>
                                                    <%
                                                        //با تغییر این سوال گزینه های مربوط به این سوال در سلکت آپشن بعدی باید بیاید
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                نام <b style="color:red">بخش </b> و یا حالت های فیلتر:
                                                <select id="hmis_filter_formquestionOption_id4-2"  name="hmis_filter_formquestionOption_id" class="form-control" style="width: 100%" multiple="">
                                                    <%
                                                        for (int i = 0; i < questionsRow.size(); i++) {//فقط چند گزینه ای ها را نشان میدهیم
                                                            if (questionsRow.get(i).get(FormQuestions._answersType).toString().equals("radio")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("select_option")
                                                                    || questionsRow.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
                                                    %>
                                                    <option value="<%=questionsRow.get(i).get(FormQuestions._id)%>"><%=questionsRow.get(i).get(FormQuestions._title)%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                محور افقی  به تفکیک کاربران زیر :
                                                <select id="userFilter-2" name="axel_x_users" class="form-control roleSelectOption" style="width: 100%" multiple >
                                                    <option value="formAnswers_userName">همه ی کاربران</option>
                                                    <%=userOptionsHtml%> 
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                محور افقی  به تفکیک نقش های زیر :
                                                <select id="rolesFilter-2" name="axel_x_roles" class="form-control roleSelectOption" style="width: 100%" multiple >
                                                    <option value="formAnswers_userRole">همه ی نقش های سیستم</option>
                                                    <%=roleOptionsHtml%> 
                                                </select>
                                            </div>   
                                        </div>
                                                   <div class="col-lg-12 row mg-t-10">                                                    
                                            <div class="col-lg-4 ">
                                                <span style="color: red">*</span>  
                                                فیلتر کن براساس نام <b style="color:red">بخش </b>:
                                                <select  autocomplete="false" multiple id="hmis_filter_department_id5-2" name="hmis_filter_department_id" class="form-control requierd department" style="width: 100%"
                                                         required="required"
                                                         onchange="">
                                                    <option value="">همه بخش ها</option>
                                                    <%=departmentOptionsHtml%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 row mg-t-10">
                                            <div class="col-lg-4">
                                                از تاریخ
                                                <input id="formAnswers_date_from2" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                تا تاریخ
                                                <input id="formAnswers_date_to2" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                    <div class="col-lg">
                                                        <button   title="رسم نمودار" class="btn btn-danger btn-block mg-b-10" 
                                                                  onclick="formCountResult_turnover2(<%= formId%>);" id="edit_Forms_new">رسم نمودار</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>

                            <div class="mg-b-5" id="showTableOfFormAnswer">
                                <div class="card-header  tx-white"  style="cursor: pointer;height:60px;background-color:#ea8a98;
                                     border-radius: 20px;line-height: 45px;text-align: center;font-size: 2rem" onclick="
                                             $('#bodyFormSectionBySection').hide();
                                             $('#bodyFormSectionBySection2').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses').hide();
                                             $('#bodyFormQuestionByQuestionAnalyses2').hide();
                                             $('#bodyFormCountResult_period').hide();
                                             $('#bodyFormCountResult_period2').hide();
                                             $('#bodyFormCountResult_turnover').hide();
                                             $('#bodyFormCountResult_turnover2').hide();
                                             $('#bodyAnalyzeFormByQuestion').hide();
                                             $('#bodyAnalyzeFormByQuestion2').hide();
                                             $('#bodyShowTableOfFormAnswer').toggle('fast');
                                             if ($('#bodyShowTableOfFormAnswer').html() == '') {
                                                 showTableOfFormAnswers(<%= formId%>);
                                                 $('#bodyShowTableOfFormAnswer').html('لطفا کمی صبر کنید ...')
                                             }">
                                    جدول اطلاعات تکمیل شده این فرم و خروجی اکسل</div>
                                <div class="card sh-pagebody" id="bodyShowTableOfFormAnswer" style="display: none;"></div>
                            </div>
                        </div>
                    </div>
                    <!--                        </div>
                                        </div>-->
                    <div class="card-body pd-sm-30">
                        <p class="mg-b-20 mg-sm-b-30">نمودار نتایج داده های سیستمی</p>
                        <div class="chartjs-size-monitor" style="">
                            <div id="chartBarDiv1" class="col-lg-6">
                                <canvas id="chartBar1"></canvas>
                            </div>
                            <div  id="chartBarDiv2" class="col-lg-6">
                                <canvas id="chartBar2" ></canvas>
                            </div>
                        </div>
                    </div>
                </div><!-- col-6 -->  
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <div class="col-lg-12">
                    <div class="card-header bg-dance tx-white p" style="height: 60px;line-height: 45px;text-align: center
                         ;font-size: 2rem" onclick="$('#FormAllResultOfferForm').slideToggle();">پیشنهاد برای بهبود عملکرد به کمیته</div>
                    <div class="card  sh-pagebody" id="FormAllResultOfferForm" style="display: none;" >
                    </div>
                </div>
                <!--                <div class="col-xl-12 mg-t-25 mg-xl-t-0">
                                    <div class="card bd-primary">
                                        <div class="card-header bg-primary tx-white">Multiple Color نمودار</div>
                                        <div class="card-body pd-sm-30"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
                                            <p class="mg-b-20 mg-sm-b-30">نمودار نتایج داده های سیستمی</p>
                                            <canvas id="chartBar2" class="chartjs-render-monitor" style="display: block; width: 100%; height: 308px;"></canvas>
                                        </div> card-body
                                    </div> card
                                </div> col-6 -->
            </div><!-- row -->
        </div>
        <script src="./js/jquery/jquery-1.10.2_1.js" type="text/javascript"></script>
        <script src="./js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
        <script src="Manager/popper.js"></script>
        <script src="Manager/bootstrap.js"></script>
        <!--    <link href="./css/jquery/orang/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
            <script src="./js/jquery/ui/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>-->
    <link href="Manager/js/jquery/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
    <script src="Manager/js/jquery/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
    <!--calendar-->
    <script src="./Manager/js/calendar/jquery.ui.datepicker-cc.js" type="text/javascript"></script>
    <script src="./Manager/js/calendar/jquery.ui.datepicker-cc-fa.js" type="text/javascript"></script>
    <script src="./Manager/js/calendar/calendar.all.js" type="text/javascript"></script>

    <!--upload برای سوالاتی که آپلود فایل نیاز دارند-->
    <script src="./Manager/js/ajaxfileupload.js" type="text/javascript"></script>
    <!--DataTable-->
    <script src="./Manager/dataTable/jquery.dataTables.js"></script>
    <script src="./Manager/dataTable/dataTables.responsive.js"></script>
    <script src="./Manager/dataTable/select2.min.js"></script>    <!--textEditor-->
    <!--<script src="Manager/textEditor/medium-editor.js"></script>-->
    <script src="Manager/textEditor/summernote-bs4.min.js"></script>    <!--jj2-->
    <script src="Manager/textEditor/summernote.js" type="text/javascript"></script>
    <!--<script src="./Manager/js/js.cookie.js" type="text/javascript"></script>-->  
    <script src="./Manager/js/jj2.js" type="text/javascript"></script>
    <script src="./Manager/js/index.js" type="text/javascript"></script>
    <!--jsHMIS-->    
    <script src="jsHMIS/plans.js" type="text/javascript"></script>
    <script src="jsHMIS/formQuestions.js" type="text/javascript"></script>
    <script src="jsHMIS/department.js" type="text/javascript"></script>
    <script src="jsHMIS/indicatorCommettes.js" type="text/javascript"></script>    
    <script src="jsCms/user.js" type="text/javascript"></script>
    <!--///نقش  ها-->
    <script src="jsHMIS/role.js" type="text/javascript"></script>
    <!--forms-->
    <!--charts.js-->
    <script src="./Manager/chart/Chart.js"></script>
    <script src="./Manager/chart/chartjs-plugin-labels.js" type="text/javascript"></script>

    <script src="./Manager/dataTable/dataTables.buttons.min.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/jszip.min.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/buttons.html5.min.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/pdfmake.min.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/buttons.print.min.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/buttons.flash.min.js" type="text/javascript"></script>
    <script type='text/javascript'>
                             $("#rolesFilter").on('select2:select', function (e) {// برای اینکه در یک زمان فقط یکی از بین نقش و کاربر را بتواند انتخاب کند
                                 $("#userFilter").val("");
                                 $("#userFilter").trigger("change");
                                 if ($('#rolesFilter').val().indexOf("formAnswers_userRole") >= 0) {
                                     $("#rolesFilter").select2("val", ["formAnswers_userRole"]);
                                 }
                             });
                             $("#userFilter").on('select2:select', function (e) {
                                 $("#rolesFilter").val("");
                                 $("#rolesFilter").trigger("change");
                                 if ($('#userFilter').val().indexOf("formAnswers_userName") >= 0) {
                                     $("#userFilter").select2("val", ["formAnswers_userName"]);
                                 }
                             });
                             /**
                              *برای نمایش نتایج بخش های مختلف که سوال را تکمیل کرده اند در دوره های زمانی مختلف بصورت های مختلف
                              * @returns {undefined}
                              */
                             function formSectionBySection(formId) {
                                 if ($("#hmis_filter_formquestion_id3").val() == "") {
                                     new jj("لطفا محور افقی را لازم را انتخاب کنید").jjModal("تکمیل فرم ها");
                                     return;
                                 }
                                 var param = "";
                                 param += "do=Forms.formSectionBySection";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formSectionBySection').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             function formSectionBySection2(formId) {
//                                     if ($("#hmis_filter_department_id").val() == "") {
//                                         new jj("لطفا محور افقی را لازم را انتخاب کنید").jjModal("تکمیل فرم ها");
//                                         return;
//                                     }
                                 var param = "";
                                 param += "do=Forms.formSectionBySection2";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formSectionBySection2').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             /**
                              *برای نمایش نتیج در دوره های زمانی مختلف بصورت های مختلف
                              * @returns {undefined}
                              */
                             function formCountResult_period(formId) {
                                 var param = "";
                                 param += "do=Forms.formCountResult_period";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formCountResult_period').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             function formCountResult_period2(formId) {
                                 var param = "";
                                 param += "do=Forms.formCountResult_period2";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formCountResult_period2').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             /**
                              *برای نمایش عملکرد پرسنل بصورت نمودار هایی که محور افقی نشان دهنده زمان است و محور عمودی عدد
                              * @returns {undefined}
                              */
                             function formCountResult_turnover(formId) {
                                 var param = "";
                                 param += "do=Forms.formCountResult_turnover";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formCountResult_turnover').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             function formCountResult_turnover2(formId) {
                                 var param = "";
                                 param += "do=Forms.formCountResult_turnover2";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formCountResult_turnover2').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             /**
                              *برای نمایش آنالیز سوال به سوال
                              * @returns {undefined}
                              */
                             function formQuestionByQuestionAnalyses(formId) {
                                 var param = "";
                                 param += "do=Forms.formQuestionByQuestionAnalyses";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formQuestionByQuestionAnalyses').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             /**
                              *  روش دوم گزارش گیری با بخش در فرم های تکمیل شدده
                              * @param {type} formId
                              * @returns {undefined}
                              */
                             function formQuestionByQuestionAnalyses2(formId) {
                                 var param = "";
                                 param += "do=Forms.formQuestionByQuestionAnalyses2";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#formQuestionByQuestionAnalyses2').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             /**
                              *برای نمایش نمودار های آنالیز سوال به سووال که پاسخ باید در یک صفحه ی دیگر با متد
                              *get باز شود تا قابل لینگ دهی باشد
                              * @returns {undefined}
                              */
                             function formAllResult_byQuestion(formId) {
                                 var param = "";
                                 param += "do=Forms.analysFromByQuestion";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#analyzeFormByQuestion').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             function formAllResult_byQuestion2(formId) {
                                 var param = "";
                                 param += "do=Forms.analysFromByQuestion2";
                                 param += "&id=" + formId;
                                 param += "&" + new jj('#analyzeFormByQuestion2').jjSerial();
                                 window.open("Server?" + param, '_blank');
                             }
                             ;
                             //برای نمایش کلیه یجواب ها در یک جدول که ستون های آن هر کدام یک سوال باشد
                             function showTableOfFormAnswers(formId) {
                                 var param = "";
                                 param += "do=Forms.showTableOfFormAnswers";
                                 param += "&id=" + formId;
                                 param += "&panel=" + "bodyShowTableOfFormAnswer";
                                 param += "&jj=1";
                                 new jj(param).jjAjax2(false);
                             }
                             ;
                             function getOptionForFilter(id, panel) {
                                 panel = (panel == null ? "hmis_filter_formquestionOption_id" : panel);
                                 if (!new jj(id).jjIsDigit()) {
                                     $(panel).html("<option value=''></option>");
                                     return;
                                 }
                                 var param = "";
                                 param += "do=FormQuestionOptions.getOptionForFilter";
                                 param += "&panel=" + (panel == null ? "hmis_filter_formquestionOption_id" : panel);
                                 param += "&formQuestionOptions_question_id=" + id;
                                 param += "&jj=1";
                                 new jj(param).jjAjax2(false);
                             }
                             ;
                             $(document).ready(function () {
                                 $("select").select2({
                                     width: '100%'
                                 });
//                                 hmisDepartment.selectOptionDepartment(".department");

                                 hmisFormQuestions.getoptionRangeFormQuestion(".range",<%=formId%>);
                                 hmisPlans.showOfferForm('FormAllResultOfferForm');
                                 $("#allAnswersTable").DataTable({
                                     dom: 'Bfrtip',
                                     buttons: [{
                                             extend: 'excelHtml5',
                                             customize: function (xlsx) {
                                                 var sheet = xlsx.xl.worksheets['sheet1.xml'];
                                                 // Loop over the cells in column `C`depart
                                                 $('row c[r^="C"]', sheet).each(function () {
                                                     // Get the value
                                                     if ($('is t', this).text() == 'New York') {
                                                         $(this).attr('s', '20');
                                                     }
                                                 });
                                             }
                                         }]
                                 });
                                 $(".department").select2({
                                     width: '100%'
                                 });

                                 $("#hmis_formquestions_id2").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id3").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id2").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id4").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id8").select2({
                                     width: '100%'
                                 });
                                 $("#hmis_filter_formquestionOption_id5").select2({
                                     width: '100%'
                                 });
                                 $(".roleSelectOption").select2({
                                     width: '100%'
                                 });
                                 new jj(".myDateFarsi").jjCalendarWithYearSelector(1395, 1420);

                                 if ($(this).val() == 'avg') {
                                     $('#formSectionBySection_rang').prop('disabled', false);
                                 } else {
                                     $('#formSectionBySection_rang').prop('disabled', true);
                                 }
                                 if ($(this).val() == 'avg') {
                                     $('#formSectionBySection_rang2').prop('disabled', false);
                                 } else {
                                     $('#formSectionBySection_rang2').prop('disabled', true);
                                 }
                             });
                             $('#learnAnalyze1Icon').click(function () {
                                 if ($('#Analyze1_learn').css('display') == 'none') {
                                     $('#Analyze1_learn').slideDown();
                                 } else {
                                     $('#Analyze1_learn').slideUp();
                                 }
                             });
                             $('#learnAnalyze2Icon').click(function () {
                                 if ($('#Analyze2_learn').css('display') == 'none') {
                                     $('#Analyze2_learn').slideDown();
                                 } else {
                                     $('#Analyze2_learn').slideUp();
                                 }
                             });
    </script>
</body>
</html>
