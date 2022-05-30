<%-- 
    Document   : formResult
    Created on : May 17, 2019, 5:28:43 PM
    Author     : Mohammad
--%>

<%@page import="cms.tools.Server"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="java.io.File"%>
<%@page import="jj.jjTime"%>
<%@page import="HMIS.Role"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="HMIS.FormQuestionOptions"%>
<%@page import="HMIS.FormAnswers"%>
<%@page import="HMIS.FormQuestions"%>
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
        Server.outPrinter(request, response,"NO DB");
        return;
    }
    jjDatabaseWeb db = (jjDatabaseWeb) request.getAttribute("db");
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
    String where = Forms._id + "=" + formId + " AND " + Forms._isActive + "=1";
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, where));
    String answerSetId = jjTools.getParameter(request, FormAnswerSet._id);
    List<Map<String, Object>> questionsAndAnswerRows = new ArrayList();
    if ((formRow.get(0).get(Forms._showResultToQuestioner).equals("1") || formRow.get(0).get(Forms._showAllResultToQuestioner).equals("1")) || Access_User.hasAccess(request, db, FormAnswerSet.rul_result_all)) {
        if (jjNumber.isDigit(answerSetId)) {// یعنی فرمی برای نمایش نتایج انتخاب شده است
            // از روی جواب ها همه ی سوالات را هم استخراج می کنیم
            String sql = "SELECT hmis_formquestions.*,formanswers_answerSet_id,formanswers_answerSet_id,formanswers_answer FROM hmis_formquestions LEFT JOIN hmis_formanswers on"
                    + " hmis_formanswers.formanswers_questionId= hmis_formquestions.id "
                    + " WHERE "
                    + " formQuestions_formID=" + formId + " AND hmis_formanswers.formanswers_answerSet_id=" + answerSetId
                    + " GROUP BY formanswers_questionId" // برای چک باکس ها چون به ازای هر تیک یک ردیف در جدول پاسخ ها قرار میگیرد اینجا گروه بندی میکنیم که به ازای هر سوال یک ردیف داشته باشیم
                    + " ORDER BY ABS(formQuestions_preority) ASC";// مرتب سازی بر اساس اولویت
            questionsAndAnswerRows = jjDatabaseWeb.separateRow(db.otherSelect(sql));
        } else {
            Server.outPrinter(request, response,"No answerSetId");
            return;
        }
    } else {
        Server.outPrinter(request, response, "No access for you ...");
        return ;
    }
    List<Map<String, Object>> AnswerSetRow = jjDatabaseWeb.separateRow(db.Select(FormAnswerSet.tableName, "id=" + answerSetId));
    String userRole = "";
    if (AnswerSetRow.isEmpty()) {
        Server.outPrinter(request, response,"No Answer Set");
        return;
    }
    if (jjNumber.isDigit(AnswerSetRow.get(0).get(FormAnswerSet._userRole).toString())) {
        List<Map<String, Object>> userRoleRow = jjDatabaseWeb.separateRow(db.Select(Role.tableName, Role._title, "id=" + AnswerSetRow.get(0).get(FormAnswerSet._userRole).toString()));
        userRole = userRoleRow.get(0).get(Role._title).toString();
    }
    float userTotalAnswerScore = 0;// ّبرای محاسبه ی امتیاز نهایی کاربر امتیاز رادیو های دارای عدد و تایپ نامبر
    float maxScore = 0;// حداکثری که تکمیل این فرم میتوانسته امتیاز داشته باشد که غ ق ارزیابی ها را اصلا لحاظ نمیکنیم

//word document
//    XWPFDocument doc = new XWPFDocument();
//    String path = request.getServletContext().getRealPath("/") + "\\wordNew" + ".docx";
//    XWPFParagraph p1 = doc.createParagraph();
//    p1.setAlignment(ParagraphAlignment.RIGHT);
//    XWPFRun run = p1.createRun();
//    run.setText("lolololo");
////    p1 = doc.createParagraph();
////    run = p1.createRun();    
//    run.setText("aadd");
//    p1 = doc.createParagraph();
//    run = p1.createRun();
//    run.setText("aaaaaaaaaaa");
//    CreateWord mte = new CreateWord(run, path);  
//    mte.transfer();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=formRow.get(0).get(Forms._title)%></title>
        <link href="Manager/font-awesome.css" rel="stylesheet">
        <link href="Manager/ionicons.css" rel="stylesheet">
        <link href="Manager/perfect-scrollbar.css" rel="stylesheet">
        <!--time picker-->
        <!--<link href="Manager/css/wickedpicker.min.css" rel="stylesheet" />-->
        <!--DataTable-->
        <link href="Manager/dataTable/jquery.dataTables.css" rel="stylesheet"/>
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
        <div class="sh-pagebody">
            <h1>عنوان فرم:  <%=formRow.get(0).get(Forms._title)%> </h1>
            <p class="mg-b-20 mg-sm-b-30">
                تکمیل کننده  : <%=AnswerSetRow.get(0).get(FormAnswerSet._userName)%>
                <br/> <%=userRole%>
                <br/> تاریخ ثبت پاسخ ها  : <%=jjCalendar_IR.getViewFormat(AnswerSetRow.get(0).get(FormAnswerSet._date))%>
                <br/> ساعت : <%=jjTime.getTime5lenth(AnswerSetRow.get(0).get(FormAnswerSet._time).toString())%> 

            </p>
            <%
                for (int i = 0; i < questionsAndAnswerRows.size(); i++) {//برای هرسوال 
                    if (questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("text")
                            || questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("textarea")
                            || questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("email")
                            || questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("date")) {

            %>
            <div class="card pd-sm-30 bd-primary question-row <%= questionsAndAnswerRows.get(i).get(FormQuestions._answersType)%>" 
                 id="q<%= questionsAndAnswerRows.get(i).get(FormQuestions._id)%>" >
                <div class="card-header bg-info tx-white">
                    <%if (!questionsAndAnswerRows.get(i).get(FormQuestions._icon).toString().trim().isEmpty()) {//اگر آیکن دارد %>
                    <img src="upload/<%= questionsAndAnswerRows.get(i).get(FormQuestions._icon)%>" class="wd-40" alt="">
                    <% }%>
                    <%= (i + 1) + "-" + questionsAndAnswerRows.get(i).get(FormQuestions._title)%> (<%= questionsAndAnswerRows.get(i).get(FormQuestions._weight)%>)
                </div>
                <% if (!questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer).toString().trim().isEmpty()) {//اگر برای این فرم پاسخ صحیح تعیین شده بود
%>
                <div class="mg-b-0">
                    پاسخ صحیحی : <%= questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer)%>
                </div>                
                <hr>
                <%
                    }
                %>
                <div class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._htmlDiscriptionInResult) /*نشان دادن توضیحات مربوط به روش رسیدن به پاسخ صحیح*/%>
                </div>
                <div class="media align-items-center">
                    <div class="media-body mg-l-15">
                        <div class="tx-inverse tx-14 mg-b-5">پاسخ کاربر</div>
                        <div class=""><%= questionsAndAnswerRows.get(i).get(FormAnswers._answer)%></div>
                    </div><!-- media-body -->
                </div><!-- media -->
            </div>   
            <%
                // اعداد را در ضریب انها ضرب می کند و در امتیاز نشان میدهد
            } else if (questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("number")) {
            %>
            <div class="card pd-20 pd-sm-30 bd-primary question-row <%= questionsAndAnswerRows.get(i).get(FormQuestions._answersType)%>" 
                 id="q<%= questionsAndAnswerRows.get(i).get(FormQuestions._id)%>" >
                <div class="card-header bg-info tx-white">
                    <%if (!questionsAndAnswerRows.get(i).get(FormQuestions._icon).toString().trim().isEmpty()) {//اگر آیکن دارد %>
                    <img src="upload/<%= questionsAndAnswerRows.get(i).get(FormQuestions._icon)%>" class="wd-40" alt="">
                    <% }%>
                    <%= (i + 1) + "-" + questionsAndAnswerRows.get(i).get(FormQuestions._title)%> (<%= questionsAndAnswerRows.get(i).get(FormQuestions._weight)%>)
                </div>
                <% if (!questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer).toString().trim().isEmpty()) {//اگر برای این فرم پاسخ صحیح تعیین شده بود
%>
                <div class="mg-b-0">
                    پاسخ صحیحی : <%= questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer)%>
                </div>
                <p class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._htmlDiscriptionInResult) /*نشان دادن توضیحات مربوط به روش رسیدن به پاسخ صحیح*/%>
                </p>
                <hr>
                <%
                    }
                    float userAnswerScore = 0;
                    if (!questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString().trim().isEmpty()) {//اگر پر کننده ی فرم جواب این سوال را خالی نگذاشته بود
                        // پیدا کردن متن پاسخ کاربر
                        if (jjNumber.isFloat(questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString())) {
                            userAnswerScore += Float.valueOf(questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString());
                        }
                        if (jjNumber.isFloat(questionsAndAnswerRows.get(i).get(FormQuestions._weight).toString())) {
                            userAnswerScore *= Float.valueOf(questionsAndAnswerRows.get(i).get(FormQuestions._weight).toString());
                        }
                    }
                    // اگر وزن سوال صفر نبود و پاسخ غ ق ا نبود
                    if (!questionsAndAnswerRows.get(i).get(FormQuestions._weight).equals("0") && jjNumber.isFloat(questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString())) {
                        userTotalAnswerScore += userAnswerScore;
                        //@ToDo برای محاسبه ی اینکه این سوال در ماکزیمم لحاظ شود چند چالش داریم، یکی اینکه ماکس این چقدر باشد، یکی اینکه از کجا بیاوریم
                        //@ToDo فعلا این را برنامه نویسی نمیکنیم چون خیلی نادر است منظور این باشد
                    }
                %>
                <div class="media align-items-center">
                    <div class="media-body mg-l-15">
                        <div class="tx-inverse tx-14 mg-b-5">پاسخ کاربر</div>
                        <div class=""><%= questionsAndAnswerRows.get(i).get(FormAnswers._answer)%></div>
                    </div><!-- media-body -->
                    <div class="media-body mg-l-15 yourScore">
                        <div class="tx-inverse tx-14 mg-b-5">امتیاز شما</div>
                        <p class="tx-12 mg-b-0"><%= questionsAndAnswerRows.get(i).get(FormQuestions._weight).equals("0") ? "سوال وزن صفر دارد" : userAnswerScore%></p>
                    </div><!-- media-body -->
                </div><!-- media -->
            </div>
            <%
            } else if (questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("radio")
                    || questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("select_option")) {
            %>
            <div class="card pd-20 pd-sm-30 bd-primary question-row <%= questionsAndAnswerRows.get(i).get(FormQuestions._answersType)%>" 
                 id="q<%= questionsAndAnswerRows.get(i).get(FormQuestions._id)%>" >
                <div class="card-header bg-info tx-white">
                    <%if (!questionsAndAnswerRows.get(i).get(FormQuestions._icon).toString().trim().isEmpty()) {//اگر آیکن دارد %>
                    <img src="upload/<%= questionsAndAnswerRows.get(i).get(FormQuestions._icon)%>" class="wd-40" alt="">
                    <% }%>
                    <%= (i + 1) + "-" + questionsAndAnswerRows.get(i).get(FormQuestions._title)%> (<%= questionsAndAnswerRows.get(i).get(FormQuestions._weight)%>)
                </div>
                <%
                    if (!questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer).toString().trim().isEmpty()) {//اگر برای این فرم پاسخ صحیح تعیین شده بود
%>
                <p class="mg-b-0">
                    پاسخ صحیح <%= questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer)%>
                </p>
                <%
                    }
                    String userAnswerText = "";
                    float userAnswerScore = 0;
                    if (!questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString().trim().isEmpty()) {//اگر پر کننده ی فرم جواب این سوال را خالی نگذاشته بود
                        // پیدا کردن متن پاسخ کاربر
                        List<Map<String, Object>> userAnswerOption = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName,
                                FormQuestionOptions._id + "=" + questionsAndAnswerRows.get(i).get(FormAnswers._answer)));
                        userAnswerText += userAnswerOption.get(0).get(FormQuestionOptions._lable).toString();
                        if (jjNumber.isFloat(userAnswerOption.get(0).get(FormQuestionOptions._value).toString())) {
                            userAnswerScore += Float.valueOf(userAnswerOption.get(0).get(FormQuestionOptions._value).toString());
                        }
                        if (jjNumber.isFloat(questionsAndAnswerRows.get(i).get(FormQuestions._weight).toString())) {
                            userAnswerScore *= Float.valueOf(questionsAndAnswerRows.get(i).get(FormQuestions._weight).toString());
                        }
                        // اگر وزن سوال صفر نبود و پاسخ غ ق ا نبود
                        if ((!questionsAndAnswerRows.get(i).get(FormQuestions._weight).equals("0")) && jjNumber.isFloat(userAnswerOption.get(0).get(FormQuestionOptions._value).toString())) {
                            userTotalAnswerScore += userAnswerScore;
                            // برای محاسبه ی میزان پاسخگویی که حداکثر امتیاز قابل اکتساب این سوال را اگر غ ق ارزیابی نباشد باید بدست آوریم
                            List<Map<String, Object>> maxScoreRow = jjDatabaseWeb.separateRow(db.otherSelect(
                                    "SELECT max(formQuestionOptions_value)*" + questionsAndAnswerRows.get(i).get(FormQuestions._weight) + " max FROM hmis_formquestionOptions"// ماکس ضربدر وزن سوال
                                    + "  WHERE formQuestionOptions_question_id=" + questionsAndAnswerRows.get(i).get("id").toString() + " ;"));
                            if (!maxScoreRow.isEmpty()) {
                                if (jjNumber.isFloat(maxScoreRow.get(0).get("max").toString())) {
                                    maxScore += Float.valueOf(maxScoreRow.get(0).get("max").toString());// به حداکثر امتیاز قابل کسب فرم این حداکثر امیتیاز گزینه ها را هم اضافه می کنیم
                                }
                            }
                        }
                    }
                %>
                <p class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._htmlDiscriptionInResult) /*نشان دادن توضیحات مربوط به روش رسیدن به پاسخ صحیح*/%>
                </p>
                <div class="media align-items-center">
                    <div class="media-body mg-l-15 yourAnswer">
                        <div class="tx-inverse tx-14 mg-b-5">گزینه ی انتخابی شما</div>
                        <p class="tx-12 mg-b-0"><%= userAnswerText%></p>
                    </div><!-- media-body -->
                    <div class="media-body mg-l-15 yourScore">
                        <div class="tx-inverse tx-14 mg-b-5">امتیاز شما</div>
                        <p class="tx-12 mg-b-0"><%= userAnswerScore%></p>
                    </div><!-- media-body -->
                </div><!-- media -->
            </div>
            <%
            } else if (questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("checkbox")) {
            %>
            <div class="card pd-20 pd-sm-30 bd-primary question-row <%= questionsAndAnswerRows.get(i).get(FormQuestions._answersType)%>" 
                 id="q<%= questionsAndAnswerRows.get(i).get(FormQuestions._id)%>" >
                <div class="card-header bg-info tx-white">
                    <%if (!questionsAndAnswerRows.get(i).get(FormQuestions._icon).toString().trim().isEmpty()) {//اگر آیکن دارد %>
                    <img src="upload/<%= questionsAndAnswerRows.get(i).get(FormQuestions._icon)%>" class="wd-40" alt="">
                    <% }%>
                    <%= (i + 1) + "-" + questionsAndAnswerRows.get(i).get(FormQuestions._title)%> (<%= questionsAndAnswerRows.get(i).get(FormQuestions._weight)%>)
                </div>
                <%
                    if (!questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer).toString().trim().isEmpty()) {//اگر برای این فرم پاسخ صحیح تعیین شده بود
%>
                <p class="mg-b-0">
                    پاسخ صحیح <%= questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer)%>
                </p>
                <%
                    }
                    String userAnswerText = "";
                    float userAnswerScore = 0;
                    boolean nullAnswerflag = false;// اگر پاسخ غیر قابل ارزیابی نداشتیم
                    String sql = "SELECT formQuestions_title,formQuestionOptions_value,formQuestionOptions_lable,formQuestions_weight FROM hmis_formquestions"
                            + " LEFT JOIN hmis_formanswers on formanswers_questionId= hmis_formquestions.id"
                            + " LEFT JOIN hmis_formquestionoptions on formanswers_answer= hmis_formquestionoptions.id"
                            + " WHERE formQuestions_formID=" + formId + " AND hmis_formanswers.formanswers_answerSet_id=" + answerSetId + " and formanswers_questionId=" + questionsAndAnswerRows.get(i).get(FormQuestions._id)
                            + ";";
                    List<Map<String, Object>> userAnswerOptions = jjDatabaseWeb.separateRow(db.otherSelect(sql));
                    for (int j = 0; j < userAnswerOptions.size(); j++) {//به تعداد گزینه های تیک زده شده ی کاربر
                        // پیدا کردن پاسخ های تیک خوزده ی کاربر
                        userAnswerText += userAnswerOptions.get(j).get(FormQuestionOptions._lable).toString() + "<br/>";
                        if (jjNumber.isFloat(userAnswerOptions.get(j).get(FormQuestionOptions._value).toString())) {
                            nullAnswerflag = true;// یعنی مقدار عددی حداقل یکی داشته ایم
                            userAnswerScore += Float.valueOf(userAnswerOptions.get(j).get(FormQuestionOptions._value).toString());
                        }
                    }
                    if (jjNumber.isFloat(userAnswerOptions.get(0).get(FormQuestions._weight).toString())) {// وزن در همه ی ردیف ها یکی هست
                        userAnswerScore *= Float.valueOf(userAnswerOptions.get(0).get(FormQuestions._weight).toString());
                    }
                    // اگر وزن سوال صفر نبود و پاسخ غ ق ا نبود
                    if (!questionsAndAnswerRows.get(i).get(FormQuestions._weight).equals("0") && nullAnswerflag) {
                        userTotalAnswerScore += userAnswerScore;
                        // برای محاسبه ی میزان پاسخگویی که حداکثر امتیاز قابل اکتساب این سوال را اگر غ ق ارزیابی نباشد باید بدست آوریم
                        List<Map<String, Object>> maxScoreRow = jjDatabaseWeb.separateRow(db.otherSelect(
                                // برای چک باکس ها باید مجموع امتیاز گزینه های قابل تیک زدن را بگذاریم به عنوان ماکس
                                "SELECT sum(formQuestionOptions_value)*" + questionsAndAnswerRows.get(i).get(FormQuestions._weight) + "  max FROM hmis_formquestionOptions" //ماکس ضربدر وزن سوال
                                + "  WHERE formQuestionOptions_question_id=" + questionsAndAnswerRows.get(i).get("id").toString() + " ;"));
                        if (!maxScoreRow.isEmpty()) {
                            if (jjNumber.isFloat(maxScoreRow.get(0).get("max").toString())) {
                                maxScore += Float.valueOf(maxScoreRow.get(0).get("max").toString());// به حداکثر امتیاز قابل کسب فرم این حداکثر امیتیاز گزینه ها را هم اضافه می کنیم
                            }
                        }
                    }
                %>
                <p class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._htmlDiscriptionInResult) /*نشان دادن توضیحات مربوط به روش رسیدن به پاسخ صحیح*/%>
                </p>
                <hr>
                <div class="media align-items-center">
                    <div class="media-body mg-l-15 yourAnswer">
                        <div class="tx-inverse tx-14 mg-b-5">گزینه های انتخابی شما</div>
                        <p class="tx-12 mg-b-0"><%= userAnswerText%></p>
                    </div><!-- media-body -->
                    <div class="media-body mg-l-15 yourScore">
                        <div class="tx-inverse tx-14 mg-b-5">امتیاز شما</div>
                        <p class="tx-12 mg-b-0"><%= userAnswerScore%></p>
                    </div><!-- media-body -->
                </div><!-- media -->
            </div>
            <%
            } else if (questionsAndAnswerRows.get(i).get(FormQuestions._answersType).toString().equals("file")) {

            %>
            <div class="card pd-20 pd-sm-30 bd-primary question-row <%= questionsAndAnswerRows.get(i).get(FormQuestions._answersType)%>" 
                 id="q<%= questionsAndAnswerRows.get(i).get(FormQuestions._id)%>" >
                <div class="card-header bg-info tx-white">
                    <%if (!questionsAndAnswerRows.get(i).get(FormQuestions._icon).toString().trim().isEmpty()) {//اگر آیکن دارد %>
                    <img src="upload/<%= questionsAndAnswerRows.get(i).get(FormQuestions._icon)%>" class="wd-40" alt="">
                    <% }%>
                    <%= (i + 1) + "-" + questionsAndAnswerRows.get(i).get(FormQuestions._title)%> (<%= questionsAndAnswerRows.get(i).get(FormQuestions._weight)%>)
                </div>
                <hr>   
                <div class="media align-items-center">
                    <div class="media-body mg-l-15">
                        <div class="tx-inverse tx-14 mg-b-5">فایل شما</div>
                        <%
                            String userFiles[] = questionsAndAnswerRows.get(i).get(FormAnswers._answer).toString().split(",");
                            for (int k = 0; k < userFiles.length; k++) {
                        %>
                        <a href="upload/<%= userFiles[k]%>" >
                            <img class="" src="upload/<%= userFiles[k]%>" style="max-height: 200px" />
                        </a>
                        <%
                            }
                        %>
                    </div><!-- media-body -->
                </div><!-- media -->
                <p class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._trueAnswer) /*پاسخ صحیح*/%>
                </p>
                <p class="mg-b-0">
                    <%= questionsAndAnswerRows.get(i).get(FormQuestions._htmlDiscriptionInResult) /*نشان دادن توضیحات مربوط به روش رسیدن به پاسخ صحیح*/%>
                </p>
            </div>
            <%
                    }
                }
            %>
            <div class="card pd-20 pd-sm-30 bd-primary question-row " >
                <div class="tx-inverse tx-14 mg-b-5">
                    امتیاز کسب شده شما : <%= userTotalAnswerScore%>
                </div>
                <div class="tx-inverse tx-14 mg-b-5">
                    حداکثر نمره ی قابل کسب برای این فرم  : <%= maxScore%>
                </div>
                <div class="tx-inverse tx-14 mg-b-5">
                    نسبت  امتیاز کسب شده به حداکثر نمره قابل کسب: <%= (userTotalAnswerScore / maxScore) * 100%>%
                </div>
            </div>            
        </div>


        <script src="js/jquery/jquery-1.10.2_1.js" type="text/javascript"></script>
        <script src="js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
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

    </body>
</html>
