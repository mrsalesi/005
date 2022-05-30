<%--
    Document   : formResult
    Created on : May 17, 2019, 5:28:43 PM
    Author     : Mohammad
--%>
<%@page import="cms.tools.Server"%>
<%@page import="HMIS.Department"%>
<%@page import="jj.jjTime"%>
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
    jjDatabaseWeb db;
    Server.Connect();
    db = Server.db;
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
    System.out.println("formId//////" + formId);
    if (formId.equals("0")) {
        return;
    }
    String startDate = jjTools.getParameter(request, "formAnswers_date_from").replaceAll("/", "");
    String endDate = jjTools.getParameter(request, "formAnswers_date_to").replaceAll("/", "");
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId));
    //انتخاب همه ی پاسخ های داده شده به فرم
    String where = "";
    if (!startDate.equals("")) {
        where += " AND " + FormAnswerSet._date + ">=" + startDate;
    }
    if (!endDate.equals("")) {
        where += " AND " + FormAnswerSet._date + "<=" + endDate;
    }

//    String formAnswers_formId = formRow.get(0).get(Forms._id).toString();
//    List<Map<String, Object>> formQuestionRows = jjDatabaseWeb.separateRow(db.otherSelect(""
//            + " SELECT hmis_formquestions.id,hmis_formquestions.formQuestions_title FROM hmis_formquestions WHERE formQuestions_formID=" + formAnswers_formId + "  ORDER BY ABS(formQuestions_preority) ASC "));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>آمار  و نتایج کلی  <%=formRow.get(0).get(Forms._title)%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="./Manager/font-awesome.css" rel="stylesheet" />
        <link href="./Manager/ionicons.css" rel="stylesheet" />
        <link href="./Manager/perfect-scrollbar.css" rel="stylesheet"/>
        <!--DataTable-->
        <link href="./Manager/dataTable/jquery.dataTables.css" rel="stylesheet"/>
        <link href="./Manager/dataTable/select2.min.css" rel="stylesheet"/>
        <!--<link href="Manager/forms/spectrum.css" rel="stylesheet">-->        <!-- MedYar مدیار CSS -->
        <link href="./Manager/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./Manager/shamcey.css">
        <!--<link href="css/bootstap.min.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="Manager/cssw.css" rel="stylesheet" type="text/css"/>-->
        <link href="./Manager/css/HMIS.css" rel="stylesheet" type="text/css"/>
        <link href="./Manager/css/endUserStyle.css" rel="stylesheet" type="text/css"/>
        <link href="./Manager/dataTable/fixedHeader.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="">
            <div class="">
                <div class="">
                    <div class=''>
                        <img src='template/hospitalLogo.png' width="10%">
                    </div>
                    <div class="card bd-primary" style="min-width: 100%">
                        <div class="">
                            <div class="card bd-primary" id="showTableOfFormAnswer">
                                <div class="card-header bg-pink tx-white" style="cursor: pointer; " onclick="showTableOfFormAnswersTwo();">
                                    گرفتن خروجی</div>  
                                    <%
                                        String questionId = "";
                                    %>
                                <div id="formExcel2">
                                    <div class="col-lg-12 row mg-t-10">
                                        <div class="col-lg-4">
                                            از تاریخ
                                            <input id="formAnswers_date_from8" name="formAnswers_date_from" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که از این تاریخ به بعد تکمیل شده اند" type="text">
                                        </div>
                                        <div class="col-lg-4">
                                            تا تاریخ
                                            <input id="formAnswers_date_to8" name="formAnswers_date_to" style="direction: ltr;text-align: center;" class="myDateFarsi form-control" placeholder="فرم هایی که قبل از این تاریخ تکمیل شده اند" type="text">
                                        </div>
                                        <div class="col-lg-4">
                                            <div id="forms_buttons" class="col-lg-12 mg-t-20 row">
                                                <div class="col-lg">
                                                    <button   title="اکسل" class="btn btn-danger btn-block mg-b-10" 
                                                              onclick="showTableOfFormAnswers(<%=formId%>); $('#bodyShowTableOfFormAnswer').html('لطفا کمی صبر کنید ...')" >فایل اکسل</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="card bd-primary sh-pagebody" id="bodyShowTableOfFormAnswer" style=""></div>
                            </div>
                        </div>
                    </div>
                </div><!-- col-6 -->
            </div><!-- row -->
        </div>
        <script src="Manager/js/jquery/jquery-1.10.2_1.js" type="text/javascript"></script>
        <script src="Manager/js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
        <script src="Manager/popper.js"></script>
        <script src="Manager/bootstrap.js"></script>
        <!--    <link href="./css/jquery/orang/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
            <script src="./js/jquery/ui/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>-->
        <link href=./"Manager/js/jquery/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
        <script src="./Manager/js/jquery/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
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
        <script src="jsHMIS/formDiscriptions.js" type="text/javascript"></script>       
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

                                                                  $(document).ready(function () {
                                                                      new jj(".myDateFarsi").jjCalendarWithYearSelector(1395, 1420);

                                                                  });

//برای نمایش کلیه یجواب ها در یک جدول که ستون های آن هر کدام یک سوال باشد
                                                                  function showTableOfFormAnswersTwo() {
//                                        var param = "";
//                                        param += "do=Forms.showTableOfFormAnswers";
//                                        param += "&id=" + formId;
//                                        param += "&panel=" + "bodyShowTableOfFormAnswer";
//                                        param += "&jj=1";
//                                        new jj(param).jjAjax2(false);
                                                                      $('#showTableOfFormAnswers').dataTable(
                                                                              {
                                                                                  fixedColumns: {leftColumns: 2},
                                                                                  fixedHeader: true,
                                                                                  iDisplayLength: 100
                                                                                  , bJQueryUI: true
                                                                                  , sPaginationType: 'full_numbers'
                                                                                  , columnDefs: [{orderable: false, targets: 0}]
                                                                                  , paging: false
                                                                                  , dom: 'Bfrtip'
                                                                                  , buttons: ['copy', 'csv', 'excel', 'print']
                                                                              });
                                                                  }
//                                                function showTableOfFormAnswers2(formId) {
//                                                    var param = "";
//                                                    param += "do=FormAnswerSet.showInformationForm";
//                                                    param += "&formAnswers_formId=" + formId;
//                                                    param += "&" + new jj('#formExcel2').jjSerial();
//                                                    param += "&jj=1";
//                                                    new jj(param).jjAjax2(false);
//                                                    window.open("Server?" + param, '_self');
//                                                }
//                                                ;
                                                                  function showTableOfFormAnswers(formId) {
                                                                      var param = "";
                                                                      param += "do=Forms.showTableOfFormAnswers3";
//                                     param += "do=FormAnswerSet.showInformationForm";
                                                                      param += "&id=" + formId;
                                                                      param += "&" + new jj('#formExcel2').jjSerial();
                                                                      param += "&panel=" + "bodyShowTableOfFormAnswer";
                                                                      param += "&jj=1";
                                                                      new jj(param).jjAjax2(false);
                                                                      window.open("Server?" + param, '_self');
                                                                  }

        </script>
    </body>
</html>    
