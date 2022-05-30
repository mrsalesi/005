<%-- 
    Document   : newFormToComplete
    Created on : Apr 5, 2019, 4:33:44 PM
    Author     : Mohammad
--%>

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
<%@page import="cms.tools.jjTools"%>
<%@page import="HMIS.Forms"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("db") == null) {// در این حالت یعنی کاربری خودش این صفحه را فراخوانی کرده نه اینکه ما آنرا ایجاد کرده باشیم
        return;
    }
    jjDatabaseWeb db = (jjDatabaseWeb) request.getAttribute("db");
    List<Map<String, Object>> questionsRow = new ArrayList();
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId + " AND " + Forms._isActive + "=1"));
    questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._formID + "=" + formId));
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
        <title><%=formRow.get(0).get(Forms._title)%></title>
        <link href="./Manager/font-awesome.css" rel="stylesheet">
        <link href="./Manager/ionicons.css" rel="stylesheet">
        <link href="./Manager/perfect-scrollbar.css" rel="stylesheet">
        <!--time picker-->
        <!--<link href="Manager/css/wickedpicker.min.css" rel="stylesheet" />-->
        <!--DataTable-->
        <!--<link href="Manager/dataTable/jquery.dataTables.css" rel="stylesheet"/>-->
        <!--<link href="Manager/dataTable/select2.min.css" rel="stylesheet"/>-->

        <!--TextEditor-->
        <!--<link href="Manager/textEditor/medium-editor.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/default.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/summernote-bs4.css" rel="stylesheet">-->
        <!--<link href="Manager/textEditor/summernote.css" rel="stylesheet">-->

        <!--forms-->
        <!--<link href="Manager/forms/select2.min.css" rel="stylesheet">-->
        <!--<link href="Manager/forms/spectrum.css" rel="stylesheet">-->

        <!-- MedYar مدیار CSS -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="Manager/shamcey.css">
        <!--<link href="css/bootstap.min.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="Manager/cssw.css" rel="stylesheet" type="text/css"/>-->
        <link href="Manager/css/HMIS.css" rel="stylesheet" type="text/css"/>
        <link href="Manager/css/endUserStyle.css" rel="stylesheet" type="text/css"/>
        <%= formRow.get(0).get(Forms._css)%>
    </head>
    <body>
        <div id="swOneFormToCompleteForm">
            <div class="card bd-primary mg-t-20 mg-b-25" id="newFormQuestion">
                <div class="card-header bg-primary tx-white"><%=formRow.get(0).get(Forms._title)%></div>
                <div class="card-body pd-sm-30 mg-b-25">
                    <input id="hmis_formAnswerSet_id" name="id" type="hidden" value="" /><!-- این قسمت در حالت ویرایش مقدار دارد و در حالت ثبت اولیه ندارد-->
                    <input id="formAnswers_formId" name="formAnswers_formId" type="hidden" value="<%=formRow.get(0).get(Forms._id)%>" />
                    <p class="mg-b-20 mg-sm-b-30">
                        <%=formRow.get(0).get(Forms._description)%></p>
                        <%=formRow.get(0).get(Forms._htmlContent)%>
                        <% if (formRow.get(0).get(Forms._isDateEditable).toString().equals("1")) {/*اگر تاریخ قابل ویرایش بود*/
                        %>                        
                    <div class="row bg-white tx-dark">
                        تاریخ ثبت را وارد کنید
                        <div class="col-lg-3 ">
                            <input type="text" class="datepicker form-control" id="formAnswers_date" name="formAnswers_date" 
                                   placeholder="تاریخ ثبت فرم برای انجام محاسبات موارد گذشته" 
                                   value="<%= jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))%>"
                                   />                        
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <div id="swFormQuestionsForm" class="card bd-primary pd-10">
                        <div class="card-header bg-info tx-white">سوالات فرم : </div>
                        <%
                            for (int i = 0; i < questionsRow.size(); i++) {
                        %>
                        <div class="question col-lg-12 card bd-primary mg-t-10 <%= "questionDivq" + i + 1%>" id="<%= "questionDivq" + i + 1%>"><!-- قرار دادن کلاس برای سوال-->
                            <div class="card-body row">
                                <% if (!questionsRow.get(i).get(FormQuestions._icon).toString().isEmpty()) {/*اگرگزینه  آبکن داشت*/
                                %>
                                <div class="col-lg-1 col-sm-1">
                                    <img id="formQuestions_icon_Preview"  class="col-lg-12 img-thumbnail" src='./upload/<%= questionsRow.get(i).get(FormQuestions._icon)%> ' />
                                </div>
                                <%
                                    }
                                %>
                                <div class="col-lg">
                                    <div class="col-lg-12 ">
                                        <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "<span class='requierd star'>*</span>" : ""%> <!--  برای سوالات ضروری ستاره-->
                                        <%= i + 1 + "-" + questionsRow.get(i).get(FormQuestions._title).toString()%> <!-- عنوان سوال-->

                                        <%= questionsRow.get(i).get(FormQuestions._htmlDiscription).toString()%> <!--  توضیحات فرم-->
                                    </div>
                                    <%
                                        //اگر نوع پاسخ ها تکست اریا بود
                                        String questionType = questionsRow.get(i).get(FormQuestions._answersType).toString();
                                        if (questionType.equals("textarea")) {
                                    %>                                    
                                    <textarea class="clo-lg-12 form-control" 
                                              id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                              name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                              placeholder="<%=questionsRow.get(i).get(FormQuestions._placeHolder).toString()%>"
                                              <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                              ><%=questionsRow.get(i).get(FormQuestions._defaultValue).toString()%></textarea>
                                    <%
                                        //اگر تکست  بود 
                                    } else if (questionType.equals("text")) {
                                    %>
                                    <input class="clo-lg-12 form-control" type="text" 
                                           id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                           name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                           placeholder="<%= questionsRow.get(i).get(FormQuestions._placeHolder)%>" 
                                           value="<%= questionsRow.get(i).get(FormQuestions._defaultValue).toString()%>"
                                           <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                           >                                    
                                    <%
                                    } else if (questionType.equals("email")) {

                                    %>
                                    <input class="clo-lg-12 form-control" type="text" 
                                           id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                           name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                           placeholder="<%= questionsRow.get(i).get(FormQuestions._placeHolder)%>" 
                                           value="<%= questionsRow.get(i).get(FormQuestions._defaultValue).toString()%>"
                                           <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                           >                                    
                                    <%
                                        //اگر تاریخ بود کلاس دیت پیکر می گذاریم و در پایین جاوااسکریپ تبدیلش را گذاشته ایم
                                    } else if (questionType.equals("date")) {
                                    %>
                                    <input  class="clo-lg-12 form-control datepicker" type="text" 
                                            id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            placeholder="<%=questionsRow.get(i).get(FormQuestions._placeHolder)%>" 
                                            value="<%= questionsRow.get(i).get(FormQuestions._defaultValue).toString()%>"
                                            <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                            >
                                    <%
                                        //اگر تاریخ بود کلاس دیت پیکر می گذاریم و در پایین جاوااسکریپ تبدیلش را گذاشته ایم
                                    } else if (questionType.equals("number")) {
                                    %>
                                    <input  class="clo-lg-12 form-control" type="number" step="0.01"
                                            id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            placeholder="<%=questionsRow.get(i).get(FormQuestions._placeHolder)%>" 
                                            value="<%= questionsRow.get(i).get(FormQuestions._defaultValue).toString()%>"
                                            <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                            >                                    
                                    <%
                                        //اگر چک باکس بود یک اینپوت مخفی می گذاریم و گزینه های تیک زده شده در آن میروند
                                    } else if (questionType.equals("checkbox")) {
                                        List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionsRow.get(i).get(FormQuestions._id)));
                                        String defaultVal = questionsRow.get(i).get(FormQuestions._defaultValue).toString();
                                    %>
                                    <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                    <input  type="hidden" 
                                            id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>"
                                            name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            value="<%=defaultVal%>"
                                            <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                            >                                    
                                    <%
                                        for (int j = 0; j < optionsRow.size(); j++) {
                                            // @ToDo ّبرای گزینه های پیش فرض باید امکان راحتی در قسمت تعریف بگذاریم که تیک خور باشد
                                            String checked = "";
                                            if (defaultVal.startsWith(questionsRow.get(i).get(FormQuestions._id) + ",") || defaultVal.matches(".*," + questionsRow.get(i).get(FormQuestions._id) + ",.*")) {
                                                checked = " checked='checked'";
                                            } else {
                                                checked = "";
                                            }
                                    %>
                                    <div class="col-lg-2 row">
                                        <% if (!optionsRow.get(j).get(FormQuestionOptions._icon).toString().isEmpty()) {/*اگرگزینه  آیکن داشت*/
                                        %>
                                        <div class="col-lg">
                                            <img class="col-lg img-thumbnail" src='./upload/"<%= optionsRow.get(j).get(FormQuestionOptions._icon)%>'  />
                                        </div>
                                        <%
                                            }
                                        %>
                                        <div class="col-lg">
                                            <input type="checkbox"  
                                                   value="<%= optionsRow.get(j).get(FormQuestionOptions._id) /*آی دی گزینه ها را برای امنیت پاسخ های صحیح می گذاریم*/%>"
                                                   <%= (questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : "") + checked%>
                                                   >
                                            <label>
                                                <%= optionsRow.get(j).get(FormQuestionOptions._lable)%>
                                            </label>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    } else if (questionType.equals("radio")) {//اگر رادیو بود برای گزینه های هر سوال
                                        List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionsRow.get(i).get(FormQuestions._id)));
                                    %>
                                    <input  type="hidden"
                                            id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                            value="<%= questionsRow.get(i).get(FormQuestions._defaultValue)%>"
                                            <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                            >                                    
                                    <%
                                        for (int j = 0; j < optionsRow.size(); j++) {
                                    %>
                                    <div class="col-lg-2">
                                        <% if (!optionsRow.get(j).get(FormQuestionOptions._icon).toString().isEmpty()) {/*اگرگزینه  آیکن داشت*/
                                        %>
                                        <div class="col-lg">
                                            <img class="col-lg img-thumbnail" src='./upload/"<%= optionsRow.get(j).get(FormQuestionOptions._icon)%>'  />
                                        </div>
                                        <%
                                            }
                                        %>
                                        <div class="col-lg">
                                            <input type="radio" 
                                                   value="<%= optionsRow.get(j).get(FormQuestionOptions._id)%>" 
                                                   name="<%=questionsRow.get(i).get(FormQuestions._id)%>"
                                                   <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%>
                                                   >
                                            <label>
                                                <%= optionsRow.get(j).get(FormQuestionOptions._lable)%>
                                            </label>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    } else if (questionType.equals("select_option")) {//اگر سلکت آپشن بود برای گزینه های هر سوال
                                        List<Map<String, Object>> optionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName, FormQuestionOptions._question_id + "=" + questionsRow.get(i).get(FormQuestions._id)));
                                        String defaultVal = questionsRow.get(i).get(FormQuestions._defaultValue).toString();//مقدار پیش فرض                                        
                                    %>
                                    <div class="col-lg-12">
                                        <select class="form-control" 
                                                id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                                name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                                <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? "required" : ""%> >
                                            <%
                                                for (int j = 0; j < optionsRow.size(); j++) {
                                                    String optionsValue = optionsRow.get(j).get(FormQuestionOptions._id).toString();
                                                    String selected = "";
                                                    if (defaultVal.equals(optionsValue)) {// در رادوی ها آی دی یکی از آپشن ها میتواند در مقدار ذخیره شده باشد
                                                        System.out.println("-------<<<<<" + j);
                                                        selected = " selected='selected' ";
                                                    } else {
                                                        System.out.println("------->>>>>" + j);
                                                        selected = "";
                                                    }
                                            %>
                                            <option
                                                value="<%= optionsRow.get(j).get(FormQuestionOptions._id)%>" 
                                                <%= selected%>
                                                >
                                                <%= optionsRow.get(j).get(FormQuestionOptions._lable)%>
                                            </option>
                                            <%
                                                }
                                            %>                                        
                                        </select>
                                    </div>
                                    <%
                                    } else if (questionType.equals("file")) {//اگر فایل بود باید از فایل آپلود استفاده کنیم
                                        //اسکریپت هایی که برای هر فایل آپلود باید بسازیم
                                        jjfileUplaodScripts.append(
                                                "new jj('#send_q" + questionsRow.get(i).get(FormQuestions._id).toString() + "_icon').jjAjaxFileUpload2("
                                                + "'q" + questionsRow.get(i).get(FormQuestions._id).toString() + "_file'"
                                                + ", '#q" + questionsRow.get(i).get(FormQuestions._id).toString() + "'"
                                                + ", '#q" + questionsRow.get(i).get(FormQuestions._id).toString() + "_Preview');\n");
                                    %>
                                    <div class="col-lg-3" style="margin-bottom: 20px">
                                        <input id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>" 
                                               name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>"
                                               value="<%= questionsRow.get(i).get(FormQuestions._defaultValue)%>"
                                               <%= questionsRow.get(i).get(FormQuestions._isRequierd).toString().equals("1") ? " required" : ""%>
                                               type="hidden" />                                        
                                        <img id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>_Preview" class="img-thumbnail"  src="img/preview.jpg"/>
                                        <div class="row">
                                            <input  class="btn btn-primary" id="send_q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>_icon" type="submit"  value="ارسال">
                                            <span class="btn btn-primary" onclick="$(this).parent().find('input[type=file]').click();" >انتخاب فایل</span>
                                            <input  type="file" id="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>_file" name="q<%= questionsRow.get(i).get(FormQuestions._id).toString()%>_file"  onchange="$(this).parent().find('span.form-control').html($(this).val().split(/[\\|/]/).pop());" style="display: none;">
                                            <span class="form-control col-lg"></span>
                                        </div>
                                    </div>

                                    <%
                                        }
                                    %>                                
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>                        
                    </div>
                </div>

                <div class="row col-lg-12 mg-sm-b-30" style="margin-bottom: 100px" >
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                </div>
                <div  id ="newFormToCompleteBottons" class="row col-lg-12 formAnswerBtn" >
                    <%
                        String userRoles[] = jjTools.getSeassionUserRole(request).split(",");
                        if (userRoles.length > 1) {// اگر بیشتر از یک نقش داشت سلکت اپشن نقش هایش را نشانش می دهیم  که هر کدام را خواست انتخاب کند
%>
                    <div class="col-lg-12">
                        <select id="formAnswers_userRole" name="formAnswers_userRole" class="form-control" style="width: 100%">
                            <%= Role.getUeserRolesSelectOption(request, response, db, true)%>
                        </select>
                    </div>
                    <%
                        }
                    %>
                    <div class="col-lg-3">
                        <button class="btn btn-outline-secondary mg-b-10  btn-block" onclick="window.close();">بازگشت </button>
                    </div>
                    <div id="formAnserSetBtn" class="col-lg-8">
                        <div class="col-lg-6">
                            <button class="btn btn-outline-warning mg-b-10  btn-block" onclick="formAnswerSet_insert();">ثبت موقت </button>                
                        </div>
                        <div class="col-lg-6">
                            <button class="btn btn-outline-success mg-b-10  btn-block" onclick="formAnswerSet_insertAndFinalForm();">ثبت نهایی </button>                
                        </div>
                    </div>
                </div>
            </div><!-- card -->
        </div>


        <script src="Manager/js/jquery/jquery-1.10.2_1.js" type="text/javascript"></script>
        <script src="Manager/js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
        <script src="Manager/popper.js"></script>
        <script src="Manager/bootstrap.js"></script>


        <link href="css/jquery/orang/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery/ui/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
        <!--        //عوض کردن 
        <!--calendar-->        

        <!--<script src="Manager/perfect-scrollbar.jquery.js"></script>-->
        <!--<script src="Manager/moment.js"></script>-->
        <!--<script src="Manager/jquery.flot.js"></script>-->
        <!--<script src="Manager/jquery.flot.resize.js"></script>-->
        <!--<script src="Manager/jquery.flot.spline.js"></script>-->


        <!--calendar-->
        <script src="./Manager/js/calendar/jquery.ui.datepicker-cc.js" type="text/javascript"></script>
        <script src="./Manager/js/calendar/jquery.ui.datepicker-cc-fa.js" type="text/javascript"></script>
        <script src="./Manager/js/calendar/calendar.all.js" type="text/javascript"></script>
        <!--<script src="Manager/js/calendar/calendar.js" type="text/javascript"></script>-->
        <!--        <script src="Manager/js/calendar/wickedpicker.min.js" type="text/javascript"></script>
                <script src="Manager/js/calendar/wickedpickerSpec.js" type="text/javascript"></script>-->


        <!--upload برای سوالاتی که آپلود فایل نیاز دارند-->
        <script src="./Manager/js/ajaxfileupload.js" type="text/javascript"></script>
        <!--DataTable-->
        <!--<script src="Manager/dataTable/jquery.dataTables.js"></script>-->
        <!--<script src="Manager/dataTable/dataTables.responsive.js"></script>-->
        <!--<script src="Manager/dataTable/select2.min.js"></script>-->

        <!--textEditor-->
        <!--<script src="Manager/textEditor/medium-editor.js"></script>-->
        <!--<script src="Manager/textEditor/summernote-bs4.min.js"></script>-->

        <!--jj2-->
        <!--<script src="Manager/textEditor/summernote.js" type="text/javascript"></script>-->
        <script src="./Manager/js/js.cookie.js" type="text/javascript"></script>
        <script src="./Manager/js/jj2.js" type="text/javascript"></script>
        <script src="./Manager/js/index.js" type="text/javascript"></script>
        <!--jsHMIS-->
        <!--<script src="jsHMIS/HmisManager.js" type="text/javascript"></script>-->
        <!--<script src="jsCms/cmsManager.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/forms.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/formQuestions.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/formQuestionOptions.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/plansForAssess.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/plans.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/steps.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/sessions.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/approved.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/reports.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/commettes.js" type="text/javascript"></script>-->

        <!--<script src="jsCms/user.js" type="text/javascript"></script>-->
        <!--<script src="jsCms/group.js" type="text/javascript"></script>-->
        <!--<script src="jsHMIS/department.js" type="text/javascript"></script>-->
        <!--////پیام ها-->
        <!--<script src="jsHMIS/messenger.js" type="text/javascript"></script>-->
        <!--///نقش  ها-->
        <!--<script src="jsHMIS/role.js" type="text/javascript"></script>-->
        <!--<script src="js/jquery/ajaxfileupload.js" type="text/javascript"></script>-->
        <!--<script src="Manager/bootstrap-filestyle.js" type="text/javascript"></script>-->
        <!--<script src="Manager/bootstrap-filestyle.min.js" type="text/javascript"></script>-->
        <!--forms-->
        <!--<script src="Manager/forms/spectrum.js"></script>-->
        <!--charts.js-->
        <!--<script src="Manager/chart/Chart.js"></script>-->

        <!--<script src="Manager/shamcey.js"></script>-->
        <!--<script src="Manager/dashboard.js"></script>-->

        <!--<script src="Manager/js/HMIS.js" type="text/javascript"></script>-->
        <script type="text/javascript">

                                (function () {
            <%
                // وقتی فرم با موفقیت ثبت نهایی شد باید در کوکی های مرورگر و اپ کاربر کوکی فرم های یونیک را ذخیره کنیم
                // البته در موقع نشان دادن هم میتوانیم با جاوا در دیتا بیش چک کنیم که دیگر این فرم را نشان کاربر ندهیم
                // سمت کلاینت فرم های یونیک را در کوکی ست کنیم که موقع پر کردن بهشان اجازه ندهد
                if (formRow.get(0).get(Forms._uniqueComplete).toString().equals("1")) {// اگر فرم باید یونیک پر شود چک کنیم ببینیم در یونیک فرم های پر شده ی این مرورگر وجود دارد یا نه
%>
                                    var formid = <%=formId%>;
                                    var uniqueForms = Cookies.get('#UNIQUE_FORMS_Compleited');// فرم های که باید توسط هر کاربر یکبار تکمیل شوند بعد از تکمیل نهایی در این کوکی ذخیره میشوند
                                    if (uniqueForms) {
                                        var uniqueFormsId = uniqueForms.split(",");
                                        for (var i = 0; i < uniqueFormsId.length; i++) {
                                            if (uniqueFormsId[i] == formid) {// یعنی این فرم قبلا توسط این کاربر با موفقیت ثبت شده است
                                                $("#newFormToCompleteBottons .btn-outline-warning ,#newFormToCompleteBottons .btn-outline-success").remove();
                                                alert("شما قبلا به این فرم پاسخ گفته اید!!!");
                                            }
                                            ;
                                        }
                                    }

            <%
            } else {//برای حذف فرم هایی که قبلا یکتا بوده اند و الان دیگر یکتا نیستند ازکوکی باید اقدام کنیم
%>
                                    var formid = <%=formId%>;
                                    var uniqueForms = Cookies.get('#UNIQUE_FORMS_Compleited');// فرم های که باید توسط هر کاربر یکبار تکمیل شوندبعد از تکمیل نهایی در این کوکی ذخیره میشوند
                                    removeFormIdFromCookie(formid);

            <%
                }

            %>
                                    if (Cookies.get('#USER_MAC')) {
//                                        alert(Cookies.get('#USER_MAC'));
                                    } else {
                                        Cookies.set('#USER_MAC', Math.random() * 50000000000000000000);
                                    }
                                    new jj("body .datepicker").jjCalendar();
                                    new jj("body input[type='number']").jjSetTextFieldOnlyGetNumberAndDot(1000000, 0);// حداکثر طول رشته داخل نامبر
                                    $(".question input:checkbox").click(function () {
                                        var checked = $(this).parent().parent().parent().find("input:checkbox:checked");
                                        var newVal = "";
                                        for (var i = 0; i < checked.length; i++) {
                                            newVal += $(checked[i]).val() + ",";
                                        }
                                        $(this).parent().parent().parent().find("input:hidden").val(newVal);
                                    });
                                    $(".question input:radio").click(function () {
                                        $(this).parent().parent().parent().find("input:hidden").val($(this).val());
                                    });
                                })();
            <%=jjfileUplaodScripts%>
        </script>
        <%= formRow.get(0).get(Forms._javaScript)%>
    </body>
</html>
