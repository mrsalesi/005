<%@page import="HMIS.FormQuestionOptions"%>
<%@page import="HMIS.Role"%>
<%@page import="HMIS.FormQuestions"%>
<%@page import="HMIS.Forms"%>
<%@page import="HMIS.FormAnswerSet"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="cms.access.Access_User"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("db") == null) {// در این حالت یعنی کاربری خودش این صفحه را فراخوانی کرده نه اینکه ما آنرا ایجاد کرده باشیم
        return;
    }
    jjDatabaseWeb db = (jjDatabaseWeb) request.getAttribute("db");
    List<Map<String, Object>> questionsRow = new ArrayList();
    String formId = jjTools.getParameter(request, FormAnswerSet._formId);
    List<Map<String, Object>> formRow = jjDatabaseWeb.separateRow(db.Select(Forms.tableName, Forms._id + "=" + formId + " AND " + Forms._isActive + "=1"));
    questionsRow = jjDatabaseWeb.separateRow(db.Select(FormQuestions.tableName, FormQuestions._formID + "=" + formId + " order by ABS(formQuestions_preority)," + FormQuestions._id));//اولویت بندی کردن سوالات
    //@ToDo چک کنیم اگر فرم یونیک بودو توسط آی دی این کاربر یا مک آدرس این کاربر  قبلا پر شده بود دیگر  فرم را نشان ندهیم   
    if (formRow.isEmpty()) {
        return;//@ToDo نشان دادن صفحه ی خطای عدم دسترسی برای موبایل و ... 
    }
    StringBuilder jjfileUplaodScripts = new StringBuilder();
    StringBuilder userOptionsHtml = new StringBuilder();// برای انتخاب بخشهایی  که این فرم را پر کرده اند در گزارش های مختلف یکبار ایجاد می کنیم و چند بار استفاده می کنیم
    List<Map<String, Object>> userRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, "id>5"));
    for (int i = 0; i < userRow.size(); i++) {
        if (!userRow.get(i).get(Access_User._id).toString().isEmpty() && !userRow.get(i).get(Access_User._id).toString().equals("0")) {//اگر نقش مورد نظر تهی نباشد چون ممکن است نقش یک ست پاسخ تهی باشد
            System.out.println("<option value=" + userRow.get(i).get(Access_User._id).toString() + ">" + userRow.get(i).get(Access_User._name).toString() + " " + userRow.get(i).get(Access_User._family).toString() + "</option>");
            userOptionsHtml.append("<option value=" + userRow.get(i).get(Access_User._id).toString() + ">" + userRow.get(i).get(Access_User._name).toString() + " " + userRow.get(i).get(Access_User._family).toString() + "</option>");
        }
    }

%>
<html style="cursor: auto;"><head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="فرم گزارش داوطلبانه خطا">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--//برای رسپانسیو نشان دادن در گوشی-->
        <title>گزارش داوطلبانه خطا</title>
        <link href="./Manager/font-awesome.css" rel="stylesheet">
        <link href="./Manager/ionicons.css" rel="stylesheet"> 
        <link href="./Manager/perfect-scrollbar.css" rel="stylesheet">
        <!-- MedYar مدیار CSS -->
        <link href="./Manager/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link  href="./Manager/shamcey.css" rel="stylesheet">
        <link href="./Manager/css/HMIS.css" rel="stylesheet" type="text/css">
        <link href="./Manager/css/endUserStyle.css" rel="stylesheet" type="text/css">
        <link href="../Manager/dataTable/select2.min.css" rel="stylesheet">
        <!--<link href="template/css/questionStyle.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="template/css/questionStyle_1.css" rel="stylesheet" type="text/css"/>-->         
        <!--<link href="template/css/questionStyle_2.css" rel="stylesheet" type="text/css"/>-->            

        <style>
		
            html{ font-size: 0.8em;
                  font-weight: normal;
                  cursor: pointer;
                  line-height: 32px;
                  font-family: Tahoma, Arial;
                  height: auto;
            }
			body{
                color:#000 !important;
            }




        </style>
    </head>      
    <body style="cursor: auto;">
        <div class="ajaxLoaderDiv" style="display: none;">   
            <div class="d-flex justify-content-center form-control col-lg-12 loader11" style="display: block;width: 100%;height: 100%;position: fixed;z-index: 100000;opacity:0.8;">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <div class="card text-center" id="next1">
            <div class="card-header text-white bg-info mb-3">
                <h2>مرحله اول </h2>
            </div>
            <div class="card-body">
                <h3 class="card-title">همکار گرامی </h3>
                <p class="card-text">این فرم در راستای ارتقاء ایمنی بیمار و کارکنان و به منظور پیشگیری از وقوع خطای درمانی و به اشتراک گذاری تجربیات  و آموخته ها تنظیم گردیده است، گزارش خطاهای درمانی، کاملا داوطلبانه می باشد و اطلاعات مربوط به آن صرفا در دسترس واحد ایمنی بیمار قرار خواهد گرفت و سایرین امکان دسترسی به آن را ندارند. لذا اطمینان از عدم امکان شناسایی افراد، از شما تقاضا می شود خطاهای درمانی را گزارش نمایید</p>
                <a onclick="$('#swOneFormToCompleteForm').slideDown();
                        $('#next1').slideUp();" class="btn btn-primary  text-white">بعدی</a>
            </div>           
        </div>
        <div id="swOneFormToCompleteForm" style="display: none;">
            <div class="card mg-t-20 mg-b-25" id="newFormQuestion" >  
                <div id="jsCustomTest" class="shadow-drop shadow-curled col-lg-12" style="display: block;margin: 0 auto;text-align: center">
                    <h2 id="testHeaderH2">گزارش داوطلبانه خطا</h2> 
                </div>
                <div class="card-body pd-sm-30 mg-b-25">
                    <input id="hmis_formAnswerSet_id" name="id" type="hidden" value="" /><!-- این قسمت در حالت ویرایش مقدار دارد و در حالت ثبت اولیه ندارد-->
                    <input id="formAnswers_formId" name="formAnswers_formId" type="hidden" value="241" />

                    <p class="mg-b-20 mg-sm-b-30">
                    <p><br></p></p>
                    <p><br></p>

                    <div class="row  tx-dark  p-3 mb-5 rounded" style="box-shadow: 2px 10px 10px 10px #5b93d3;">

                        تاریخ ثبت را وارد کنید
                        <div class="col-lg-3">
                            <input type="text" class="datepicker form-control" id="formAnswers_date" name="formAnswers_date"  style="font-size:12px"
                                   placeholder="تاریخ ثبت فرم برای انجام محاسبات موارد گذشته" 
                                   value="<%= jjCalendar_IR.getViewFormat_10length(jjCalendar_IR.getDatabaseFormat_8length(null, true))%>"
                                   />                        
                        </div>


                        نام بخش را وارد کنید:
                        <div class="col-lg-3">
                            <i class='requierd star fa fa-star' style='color:#cd0047;position:absolute;z-index:10000'> </i>  
                            <select class="form-control   required" id="formAnswers_departmentId" name="formAnswers_departmentId">
                                <option value="">نام بخش را انتخاب کنید</option>
                                <option value=5>بیمارستان</option><option value=6>اداری</option><option value=7>بالینی</option><option value=8>پاراکلینیکی</option><option value=9>CCU</option><option value=10>CSSD</option><option value=11>ICU1</option><option value=12> ICU OH</option><option value=13>سوپروایز MRI</option><option value=14>NICU</option><option value=16>اسکوپی</option><option value=17>بلوک زایمان</option><option value=18>اتاق عمل</option><option value=19>مالی</option><option value=20>منابع انسانی </option><option value=21>امید</option><option value=22>انبار</option><option value=23>انتظامات</option><option value=25>اورژانس</option><option value=26>آزمایشگاه</option><option value=29>بهداشت حرفه ای </option><option value=30>بهداشت محیط</option><option value=33>پذیرش و ترخیص و بیمه </option><option value=34>سوپروایزر پزشکی هسته ای</option><option value=35>تاسیسات</option><option value=36>تجهیزات پزشکی</option><option value=37>تصویربرداری</option><option value=38>مسئول تغذیه ورژیم درمانی</option><option value=39>مسئول مرکز تلفن</option><option value=40>توسعه مدیریت و بهبود کیفیت</option><option value=41> داروخانه</option><option value=42>سوپروایزر درمانگاه</option><option value=44>روابط عمومی</option><option value=46>سپند</option><option value=47>سپهر</option><option value=48> سپید</option><option value=49>ستاره</option><option value=50>ستایش</option><option value=51>سهند</option><option value=52>شنوائی سنجی</option><option value=53> فیزیوتراپی</option><option value=55>لندری</option><option value=56>مدارک پزشکی </option><option value=63>مشاورین</option><option value=65>مسئولین فنی</option><option value=66>امور حقوقی</option><option value=67>رئیس حسابداری</option><option value=68>مسئول درآمد</option><option value=69>سوپروایزر  کنترل عفونت</option><option value=70>واحد آموزش</option><option value=72>ایمنی بیمار</option><option value=73>پزشکان</option><option value=74>مدیر پاراکلینیک</option><option value=75>IPD(امور بیماران بین الملل)</option><option value=76>سوپروایزر سیتی آنژیو</option><option value=77>فناوری اطلاعات </option><option value=78>بهبود کیفیت</option><option value=79>تجارب مشتریان</option><option value=83>سوپروایزر بالینی</option><option value=84>سوپروایزر آموزشی</option><option value=85>رئیس مراقبتهایی دارویی</option><option value=86> کلینیک</option><option value=87>هماهنگ کننده ایمنی و نظارت بر درمان </option><option value=88>امور پزشکان</option><option value=89>پزشک مقیم </option><option value=90>کارشناس تغذیه </option><option value=91>سرمهماندار</option><option value=92>رئیس امور پشتیبانی </option><option value=93>مسئول بودجه و گزارشات</option><option value=95>مسئول ممیزی و حسابرسی داخلی </option><option value=96>کترینک</option><option value=97>ICU2</option><option value=98>مدیریت پرستاری</option><option value=99>بیمارستان مجازی</option><option value=100>چشم</option><option value=101>IVF</option><option value=102>جنرال</option><option value=103>آنژیوگرافی </option><option value=104>کت لب </option><option value=105>CCU2</option><option value=106>بانک خون</option>
                            </select>                        
                        </div>


                    </div>
                    <div id="swFormQuestionsForm" class="card  pd-10">
                        <div class="card-header  tx-white formTitr" style="background-color:blue" >سوالات فرم : </div>

                        <div id="pageFormTwo">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله دوم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>

                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="question col-lg-12 card  mg-t-10 questionDivq01" id="questionDivq01"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 1.</span>
                                            نام بخش یا واحد گزارش دهنده <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7987" 
                                                        name="q7987" 
                                                        required >
				  <%
                                                   List<Map<String, Object>> questinOptionsRowOne = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7987"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowOne.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowOne.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowOne.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--
                                                    <option value="">
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34748" 

                                                        >
                                                        سهند
                                                    </option>

                                                    <option
                                                        value="34749" 

                                                        >
                                                        سپید
                                                    </option>

                                                    <option
                                                        value="34750" 

                                                        >
                                                        سپند
                                                    </option>

                                                    <option
                                                        value="34751" 

                                                        >
                                                        ستایش
                                                    </option>

                                                    <option
                                                        value="34752" 

                                                        >
                                                        سپهر
                                                    </option>

                                                    <option
                                                        value="34753" 

                                                        >
                                                        بلوک زایمان
                                                    </option>

                                                    <option
                                                        value="34754" 

                                                        >
                                                        اتاق عمل
                                                    </option>

                                                    <option
                                                        value="34755" 

                                                        >
                                                        کت لب
                                                    </option>

                                                    <option
                                                        value="34756" 

                                                        >
                                                        اسکوپی
                                                    </option>

                                                    <option
                                                        value="34757" 

                                                        >
                                                        ستاره
                                                    </option>

                                                    <option
                                                        value="34758" 

                                                        >
                                                        امید
                                                    </option>

                                                    <option
                                                        value="34759" 

                                                        >
                                                        well baby
                                                    </option>

                                                    <option
                                                        value="34760" 

                                                        >
                                                        Nicu
                                                    </option>

                                                    <option
                                                        value="34761" 

                                                        >
                                                        Icu-g
                                                    </option>

                                                    <option
                                                        value="34762" 

                                                        >
                                                        Icu-oh
                                                    </option>

                                                    <option
                                                        value="34763" 

                                                        >
                                                        Ccu1
                                                    </option>

                                                    <option
                                                        value="34764" 

                                                        >
                                                        ccu2
                                                    </option>

                                                    <option
                                                        value="34765" 

                                                        >
                                                        cssd
                                                    </option>

                                                    <option
                                                        value="34766" 

                                                        >
                                                        پزشک هسته ای 
                                                    </option>

                                                    <option
                                                        value="34767" 

                                                        >
                                                        تصویر برداری 
                                                    </option>

                                                    <option
                                                        value="34768" 

                                                        >
                                                        آزمایشگاه
                                                    </option>

                                                    <option
                                                        value="34769" 

                                                        >
                                                        کلینیک
                                                    </option>

                                                    <option
                                                        value="34770" 

                                                        >
                                                        فیزیوتراپی
                                                    </option>

                                                    <option
                                                        value="34771" 

                                                        >
                                                        مدیریت
                                                    </option>

                                                    <option
                                                        value="34772" 

                                                        >
                                                        دفتر پرستاری
                                                    </option>

                                                    <option
                                                        value="34773" 

                                                        >
                                                        بهبود کیفیت
                                                    </option>

                                                    <option
                                                        value="34774" 

                                                        >
                                                        فناوری اطلاعات
                                                    </option>

                                                    <option
                                                        value="34775" 

                                                        >
                                                        پذیرش
                                                    </option>

                                                    <option
                                                        value="34776" 

                                                        >
                                                        ترخیص
                                                    </option>

                                                    <option
                                                        value="34777" 

                                                        >
                                                        بیمه
                                                    </option>

                                                    <option
                                                        value="34778" 

                                                        >
                                                        انبار
                                                    </option>

                                                    <option
                                                        value="34779" 

                                                        >
                                                        لندری
                                                    </option>

                                                    <option
                                                        value="34780" 

                                                        >
                                                        روابط عمومی
                                                    </option>

                                                    <option
                                                        value="34781" 

                                                        >
                                                        تغذیه
                                                    </option>

                                                    <option
                                                        value="34782" 

                                                        >
                                                        مرکز تلفن
                                                    </option>

                                                    <option
                                                        value="34783" 

                                                        >
                                                        ipd
                                                    </option>

                                                    <option
                                                        value="34784" 

                                                        >
                                                        بهداشت حرفه ای
                                                    </option>

                                                    <option
                                                        value="34785" 

                                                        >
                                                        بهداشت محیط
                                                    </option>

                                                    <option
                                                        value="34786" 

                                                        >
                                                        مدارک پزشکی
                                                    </option>

                                                    <option
                                                        value="34787" 

                                                        >
                                                        مالی
                                                    </option>

                                                    <option
                                                        value="34788" 

                                                        >
                                                        داروخانه بستری
                                                    </option>

                                                    <option
                                                        value="34789" 

                                                        >
                                                        داروخانه سرپایی
                                                    </option>

                                                    <option
                                                        value="34790" 

                                                        >
                                                        منابع انسانی
                                                    </option>

                                                    <option
                                                        value="34791" 

                                                        >
                                                        تجهیزات پزشکی
                                                    </option>

                                                    <option
                                                        value="34792" 

                                                        >
                                                        تاسیسات
                                                    </option>

                                                    <option
                                                        value="34793" 

                                                        >
                                                        انتظامات
                                                    </option>

                                                    <option
                                                        value="41999" 

                                                        >
                                                        اورژانس
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq11" id="questionDivq11"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 2.</span>
                                            نام بخش یا واحدی که خطا در آن اتفاق افتاده است <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7988" 
                                                        name="q7988" 
                                                        required >
                                                    
                                                                <%
                                                   List<Map<String, Object>> questinOptionsRowTwo = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7988"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowTwo.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowTwo.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowTwo.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34796" 

                                                        >
                                                        سهند
                                                    </option>

                                                    <option
                                                        value="34797" 

                                                        >
                                                        سپید
                                                    </option>

                                                    <option
                                                        value="34798" 

                                                        >
                                                        ستایش
                                                    </option>

                                                    <option
                                                        value="34799" 

                                                        >
                                                        سپهر
                                                    </option>

                                                    <option
                                                        value="34800" 

                                                        >
                                                        بلوک زایمان
                                                    </option>

                                                    <option
                                                        value="34801" 

                                                        >
                                                        اتاق عمل
                                                    </option>

                                                    <option
                                                        value="34802" 

                                                        >
                                                        کت لب
                                                    </option>

                                                    <option
                                                        value="34803" 

                                                        >
                                                        اسکوپی
                                                    </option>

                                                    <option
                                                        value="34804" 

                                                        >
                                                        ستاره
                                                    </option>

                                                    <option
                                                        value="34805" 

                                                        >
                                                        امید
                                                    </option>

                                                    <option
                                                        value="34806" 

                                                        >
                                                        well baby
                                                    </option>

                                                    <option
                                                        value="34807" 

                                                        >
                                                        Nice
                                                    </option>

                                                    <option
                                                        value="34808" 

                                                        >
                                                        Icu-g
                                                    </option>

                                                    <option
                                                        value="34809" 

                                                        >
                                                        Icu-oh
                                                    </option>

                                                    <option
                                                        value="34810" 

                                                        >
                                                        Ccu1
                                                    </option>

                                                    <option
                                                        value="34811" 

                                                        >
                                                        ccu2
                                                    </option>

                                                    <option
                                                        value="34812" 

                                                        >
                                                        cssd
                                                    </option>

                                                    <option
                                                        value="34813" 

                                                        >
                                                        پزشکی هسته ای
                                                    </option>

                                                    <option
                                                        value="34814" 

                                                        >
                                                        تصویر برداری
                                                    </option>

                                                    <option
                                                        value="34815" 

                                                        >
                                                        آزمایشگاه
                                                    </option>

                                                    <option
                                                        value="34816" 

                                                        >
                                                        کلینیک
                                                    </option>

                                                    <option
                                                        value="34817" 

                                                        >
                                                        فیزیوتراپی
                                                    </option>

                                                    <option
                                                        value="34818" 

                                                        >
                                                        مدیریت
                                                    </option>

                                                    <option
                                                        value="34819" 

                                                        >
                                                        دفتر پرستاری
                                                    </option>

                                                    <option
                                                        value="34820" 

                                                        >
                                                        بهبود کیفیت
                                                    </option>

                                                    <option
                                                        value="34821" 

                                                        >
                                                        فناوری اطلاعات
                                                    </option>

                                                    <option
                                                        value="34822" 

                                                        >
                                                        پذیرش
                                                    </option>

                                                    <option
                                                        value="34823" 

                                                        >
                                                        ترخیص
                                                    </option>

                                                    <option
                                                        value="34824" 

                                                        >
                                                        بیمه
                                                    </option>

                                                    <option
                                                        value="34825" 

                                                        >
                                                        انبار
                                                    </option>

                                                    <option
                                                        value="34826" 

                                                        >
                                                        لندری
                                                    </option>

                                                    <option
                                                        value="34827" 

                                                        >
                                                        روابط عمومی
                                                    </option>

                                                    <option
                                                        value="34828" 

                                                        >
                                                        تغذیه
                                                    </option>

                                                    <option
                                                        value="34829" 

                                                        >
                                                        مرکز تلفن
                                                    </option>

                                                    <option
                                                        value="34830" 

                                                        >
                                                        ipd
                                                    </option>

                                                    <option
                                                        value="34831" 

                                                        >
                                                        بهداشت حرفه ای
                                                    </option>

                                                    <option
                                                        value="34832" 

                                                        >
                                                        بهداشت محیط
                                                    </option>

                                                    <option
                                                        value="34833" 

                                                        >
                                                        مدارک پزشکی
                                                    </option>

                                                    <option
                                                        value="34834" 

                                                        >
                                                        مالی
                                                    </option>

                                                    <option
                                                        value="34835" 

                                                        >
                                                        داروخانه بستری
                                                    </option>

                                                    <option
                                                        value="34836" 

                                                        >
                                                        داروخانه سرپایی
                                                    </option>

                                                    <option
                                                        value="34837" 

                                                        >
                                                        منابع انسانی
                                                    </option>

                                                    <option
                                                        value="34838" 

                                                        >
                                                        تجهیزات پزشکی
                                                    </option>

                                                    <option
                                                        value="34839" 

                                                        >
                                                        تاسیسات
                                                    </option>

                                                    <option
                                                        value="34840" 

                                                        >
                                                        انتظامات
                                                    </option>

                                                    <option
                                                        value="42000" 

                                                        >
                                                        اورژانس
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq21" id="questionDivq21"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 3.</span>
                                            سمت سازمانی فرد گزارش دهنده <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7989" 
                                                        name="q7989" 
                                                        required >
                                                                 <%
                                                   List<Map<String, Object>> questinOptionsRowThree = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7989"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowThree.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowThree.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowThree.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                    
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34842" 

                                                        >
                                                        مدیر
                                                    </option>

                                                    <option
                                                        value="34843" 

                                                        >
                                                        سوپروایزر
                                                    </option>

                                                    <option
                                                        value="34844" 

                                                        >
                                                        پرستار
                                                    </option>

                                                    <option
                                                        value="34845" 

                                                        >
                                                        ماما
                                                    </option>

                                                    <option
                                                        value="34846" 

                                                        >
                                                        کارشناس اتاق عمل
                                                    </option>

                                                    <option
                                                        value="34847" 

                                                        >
                                                        کارشناس هوشبری
                                                    </option>

                                                    <option
                                                        value="34848" 

                                                        >
                                                        کمک بهیار
                                                    </option>

                                                    <option
                                                        value="34849" 

                                                        >
                                                        منشی
                                                    </option>

                                                    <option
                                                        value="34850" 

                                                        >
                                                        داروساز اقماری
                                                    </option>

                                                    <option
                                                        value="34851" 

                                                        >
                                                        متصدی دارویی
                                                    </option>

                                                    <option
                                                        value="34852" 

                                                        >
                                                        فیزیوتراپ
                                                    </option>

                                                    <option
                                                        value="34853" 

                                                        >
                                                        کارشناس
                                                    </option>

                                                    <option
                                                        value="34854" 

                                                        >
                                                        متصدی
                                                    </option>

                                                    <option
                                                        value="34855" 

                                                        >
                                                        کاخ دار
                                                    </option>

                                                    <option
                                                        value="34856" 

                                                        >
                                                        مهماندار
                                                    </option>

                                                    <option
                                                        value="42001" 

                                                        >
                                                        پزشک
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq31" id="questionDivq31"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 4.</span>
                                            نام و نام خانوادگی فرد گزارش دهنده <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q7990" 
                                                   name="q7990" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq41" id="questionDivq41"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 5.</span>
                                            سن فرد گزارش دهنده خطا <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  class="col-lg-12 form-control" type="number"  style="text-align: left;direction: ltr"
                                                    id="q7991" 
                                                    name="q7991" 
                                                    placeholder="" 
                                                    value=""
                                                    required
                                                    >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq51" id="questionDivq51"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 6.</span>
                                            سابقه کار فرد گزارش کننده <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7992" 
                                                        name="q7992" 
                                                        required >
                                                                   <%
                                                   List<Map<String, Object>> questinOptionsRowFour = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7992"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowFour.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowFour.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowFour.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34858" 

                                                        >
                                                        کمتر از 5 سال
                                                    </option>

                                                    <option
                                                        value="34859" 

                                                        >
                                                        بین 5 تا 10 سال
                                                    </option>

                                                    <option
                                                        value="34860" 

                                                        >
                                                        بین 10 تا 15 سال
                                                    </option>

                                                    <option
                                                        value="34861" 

                                                        >
                                                        بین 15 تا 20
                                                    </option>

                                                    <option
                                                        value="42002" 

                                                        >
                                                        بالای 20 سال
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq61" id="questionDivq61"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 7.</span>
                                            شیفتی که خطا اتفاق افتاده است <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7993" 
                                                        name="q7993" 
                                                        required >
                                                    
                                                                       <%
                                                   List<Map<String, Object>> questinOptionsRowFive = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7993"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowFive.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowFive.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowFive.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34863" 

                                                        >
                                                        صبح
                                                    </option>

                                                    <option
                                                        value="34864" 

                                                        >
                                                        عصر
                                                    </option>

                                                    <option
                                                        value="42003" 

                                                        >
                                                        شب
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq71" id="questionDivq71"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 8.</span>
                                            شیفتی که خطا در آن گزارش شده است <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7994" 
                                                        name="q7994" 
                                                        required >
                                                 <%
                                                   List<Map<String, Object>> questinOptionsRowSix = jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7994"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowSix.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowSix.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowSix.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34866" 

                                                        >
                                                        صبح
                                                    </option>

                                                    <option
                                                        value="34867" 

                                                        >
                                                        عصر
                                                    </option>

                                                    <option
                                                        value="42004" 

                                                        >
                                                        شب
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="gotoPage3(this);" class="btn btn-primary  text-white">بعدی</a>
                                    <a onclick="$('#next1').slideDown();
                                            $('#swOneFormToCompleteForm').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                        </div>
                        <div id="pageFormThere" style="display: none;">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله سوم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

                            <div class="question col-lg-12 card  mg-t-10 questionDivq81" id="questionDivq81"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 9.</span>
                                            سن بیمار <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  class="col-lg-12 form-control" type="number"  style="text-align: left;direction: ltr"
                                                    id="q7995" 
                                                    name="q7995" 
                                                    placeholder="" 
                                                    value=""

                                                    >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq91" id="questionDivq91"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 10.</span>
                                            جنسیت بیمار <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  type="hidden"
                                                    id="q7996" 
                                                    name="q7996" 
                                                    value=""
                                                    required
                                                    >                                    


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34868" 
                                                           name="7996"
                                                           required
                                                           >
                                                    <label>
                                                        مرد
                                                    </label>
                                                </div>
                                            </div>


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34869" 
                                                           name="7996"
                                                           required
                                                           >
                                                    <label>
                                                        زن
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq101" id="questionDivq101"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 11.</span>
                                            تاریخ بستری <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  class="col-lg-12 form-control datepicker" type="text" 
                                                    id="q7997" 
                                                    name="q7997" 
                                                    placeholder="" 
                                                    value=""
                                                    required
                                                    >

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq111" id="questionDivq111"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 12.</span>
                                            نحوه ورود به بیمارستان <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7998" 
                                                        name="q7998" 
                                                        required >
                                                     <%
                                                   List<Map<String, Object>> questinOptionsRowSeven= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7998"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowSeven.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowSeven.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowSeven.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34871" 

                                                        >
                                                        اورژانسی
                                                    </option>

                                                    <option
                                                        value="42005" 

                                                        >
                                                        الکتیو
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq121" id="questionDivq121"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 13.</span>
                                            نوع خدمت <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q7999" 
                                                        name="q7999" 
                                                        required >
                                                       <%
                                                   List<Map<String, Object>> questinOptionsRowEight= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=7999"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowEight.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowEight.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowEight.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34873" 

                                                        >
                                                        بستری
                                                    </option>

                                                    <option
                                                        value="42006" 

                                                        >
                                                        سرپایی
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="gotoPage4(this);" class="btn btn-primary  text-white">بعدی</a>
                                    <a onclick="$('#pageFormTwo').slideDown();
                                            $('#pageFormThere').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                        </div>
                        <div id="pageFormFour" style="display: none;">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله چهارم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

                            <div class="question col-lg-12 card  mg-t-10 questionDivq131" id="questionDivq131"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 14.</span>
                                            نوع گزارش <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q8000" 
                                                        name="q8000" 
                                                        required >
                                                    
                                                       <%
                                                   List<Map<String, Object>> questinOptionsRowNine= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=8000"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowNine.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowNine.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowNine.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34875" 

                                                        >
                                                        احتمال خطا وجود داشته است
                                                    </option>

                                                    <option
                                                        value="42007" 

                                                        >
                                                        خطای رخ داده شده است
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq141" id="questionDivq141"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 15.</span>
                                            نوع خطا <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q8001" 
                                                        name="q8001" 
                                                        required >
                                                     <%
                                                   List<Map<String, Object>> questinOptionsRowTen= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=8001"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowTen.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowTen.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowTen.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34877" 

                                                        >
                                                        no harm event ( خطای بدون عارضه )
                                                    </option>

                                                    <option
                                                        value="34878" 

                                                        >
                                                        Accident (خطاهای منجر به بروز آسیب ) 
                                                    </option>

                                                    <option
                                                        value="34879" 

                                                        >
                                                        sentinel event ( خطاهای منجر به بروز آسیب های فاجعه آمیز)
                                                    </option>

                                                    <option
                                                        value="42008" 

                                                        >
                                                        near miss ( نزدیک به خطا)
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq151" id="questionDivq151"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 16.</span>
                                            پیامد خطا <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q8002" 
                                                        name="q8002" 
                                                        required >
                                                        <%
                                                   List<Map<String, Object>> questinOptionsRowEeven= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=8002"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowEeven.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowEeven.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowEeven.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34881" 

                                                        >
                                                        آسیب موقت
                                                    </option>

                                                    <option
                                                        value="34882" 

                                                        >
                                                        آسیب پایدار
                                                    </option>

                                                    <option
                                                        value="34883" 

                                                        >
                                                        فوت
                                                    </option>

                                                    <option
                                                        value="42009" 

                                                        >
                                                        بدون آسیب
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq161" id="questionDivq161"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 17.</span>
                                            آیا حیطه خطا بر اساس 9 راه حل ایمنی می باشد <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  type="hidden"
                                                    id="q8003" 
                                                    name="q8003" 
                                                    value=""
                                                    required
                                                    >                                    


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34884" 
                                                           name="8003"
                                                           required
                                                           >
                                                    <label>
                                                        بلی
                                                    </label>
                                                </div>
                                            </div>


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34885" 
                                                           name="8003"
                                                           required
                                                           >
                                                    <label>
                                                        خیر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq171" id="questionDivq171"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 18.</span>
                                            کدام مورد ایمنی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q8004" 
                                                        name="q8004" 
                                                        required >
                                                    <%
                                                   List<Map<String, Object>> questinOptionsRowTwelve= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=8004"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowTwelve.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowTwelve.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowTwelve.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>

<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34887" 

                                                        >
                                                        توجه به داروهایی با نام و تلفظ مشابه
                                                    </option>

                                                    <option
                                                        value="34888" 

                                                        >
                                                        اجتناب از اتصالات نادرست سوند و لوله ها
                                                    </option>

                                                    <option
                                                        value="34889" 

                                                        >
                                                        استفاده صرفا یکباره از وسایل تزریقات
                                                    </option>

                                                    <option
                                                        value="34890" 

                                                        >
                                                        بهبود بهداشت دست برای جلوگیری از عفونت
                                                    </option>

                                                    <option
                                                        value="34891" 

                                                        >
                                                        انجام پروسیجر صحیح در محل صحیح بدن بیمار
                                                    </option>

                                                    <option
                                                        value="34892" 

                                                        >
                                                        ارتباط موثر در زمان تحویل بیمار
                                                    </option>

                                                    <option
                                                        value="34893" 

                                                        >
                                                        کنترل غلظت محلولهای الکترولیت
                                                    </option>

                                                    <option
                                                        value="34894" 

                                                        >
                                                        اطمینان از صحت دارو درمانی در مراحل انتقالی ارایه خدمات
                                                    </option>

                                                    <option
                                                        value="42010" 

                                                        >
                                                        شناسایی صحیح بیمار
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq181" id="questionDivq181"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 19.</span>
                                            آیا خطا جزء وقایع ناخواسته تهدید کننده حیات است <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input  type="hidden"
                                                    id="q8005" 
                                                    name="q8005" 
                                                    value=""
                                                    required
                                                    >                                    


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34895" 
                                                           name="8005"
                                                           required
                                                           >
                                                    <label>
                                                        بلی
                                                    </label>
                                                </div>
                                            </div>


                                            <div class="col-lg">

                                                <div class="col-lg">
                                                    <input type="radio" 
                                                           value="34896" 
                                                           name="8005"
                                                           required
                                                           >
                                                    <label>
                                                        خیر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq191" id="questionDivq191"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 20.</span>
                                            کدام وقایع ناخواسته <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <div class="col-lg-12">
                                                <select class="form-control" 
                                                        id="q8006" 
                                                        name="q8006" 
                                                        required >
                                                          <%
                                                   List<Map<String, Object>> questinOptionsRowThirteen= jjDatabaseWeb.separateRow(db.Select(FormQuestionOptions.tableName , FormQuestionOptions._question_id +"=8006"));                                                                                                                                                  
                                                    for (int i = 0; i < questinOptionsRowThirteen.size(); i++) {                                                   
                                                    %>
                                                    <option value="<%= questinOptionsRowThirteen.get(i).get(FormQuestionOptions._id)%>">
                                                     <%= questinOptionsRowThirteen.get(i).get(FormQuestionOptions._lable)%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
<!--                                                    <option
                                                        value="" 

                                                        >
                                                        انتخاب کنید
                                                    </option>

                                                    <option
                                                        value="34898" 

                                                        >
                                                        کد 1 انجام عمل جراحی به صورت اشتباه روی عضو سالم
                                                    </option>

                                                    <option
                                                        value="34899" 

                                                        >
                                                        کد 2 .انجام عمل جراحی به صورت اشتباه روی بیمار دیگر
                                                    </option>

                                                    <option
                                                        value="34900" 

                                                        >
                                                        کد 3 .انجام عمل جراحی با روش اشتباه بر روی بیمار
                                                    </option>

                                                    <option
                                                        value="34901" 

                                                        >
                                                        کد 4  جا گذاشتن هر گونه divice اعم  گاز، قیچی، پنس و ...
                                                    </option>

                                                    <option
                                                        value="34902" 

                                                        >
                                                        5 .مرگ در حین عمل جراحی یا بلافاصله بعد از عمل در بیمار دارای وضعیت سلامت طبیعی (کلاس یک طبقه بندی ASAانجمن بیهوشی آمریكا . )
                                                    </option>

                                                    <option
                                                        value="34903" 

                                                        >
                                                        کد 6 .تلقیح مصنوعی با دهنده ( DONOR ) اشتباه درزوجین نابارور 
                                                    </option>

                                                    <option
                                                        value="34904" 

                                                        >
                                                        کد 7 .مرگ یا ناتواتی جدی بیمار به دنبال هر گونه استفاده از دارو و تجهیزات آلوده میكروبی 
                                                    </option>

                                                    <option
                                                        value="34905" 

                                                        >
                                                        کد 8 .مرگ یا ناتوانی جدی بیمار به دنبال استفاده از دستگاه های آلوده
                                                    </option>

                                                    <option
                                                        value="34906" 

                                                        >
                                                        کد 9 .مرگ یا ناتوانی جدی بیمار به دنبال هر گونه آمبولی عروقی
                                                    </option>

                                                    <option
                                                        value="34907" 

                                                        >
                                                        کد 10 .ترخیص و تحویل نوزاد به شخص و یا اشخاص غیر از ولی قانون
                                                    </option>

                                                    <option
                                                        value="34908" 

                                                        >
                                                        کد 11 .مفقود شدن بیمار در زمان بستری که بیش از 4ساعت طول بكشد  (مثال  :زندانیان بستریکد 12 .خودکشی یا اقدام به خودکشی در مرکز درمانی
                                                    </option>

                                                    <option
                                                        value="34909" 

                                                        >
                                                        کد 12 .خودکشی یا اقدام به خودکشی در مرکز درمانی کد 13 .مرگ یا ناتوانی جدی بیمار به دنبال هر گونه اشتباه در تزریق نوع دارو، دوزدار ، وزمان تزریق دارو
                                                    </option>

                                                    <option
                                                        value="34910" 

                                                        >
                                                        کد 13 .مرگ یا ناتوانی جدی بیمار به دنبال هر گونه اشتباه در تزریق نوع دارو، دوزدار ، وزمان تزریق دارو
                                                    </option>

                                                    <option
                                                        value="34911" 

                                                        >
                                                        کد 14 .مرگ یا ناتوانی جدی مرتبط با واکنش همولیتیک به علت تزریق گروه خون اشتباه در فرآورده های خونی .
                                                    </option>

                                                    <option
                                                        value="34912" 

                                                        >
                                                        کد 15 .کلیه موارد مرگ یا عارضه مادر و نوزاد بر اثر زایمان طبیعی و یا سزارین 
                                                    </option>

                                                    <option
                                                        value="34913" 

                                                        >
                                                        کد 16 .مرگ یا ناتوانی جدی به دنبال هیپوگلیسمی در مرکز درمانی
                                                    </option>

                                                    <option
                                                        value="34914" 

                                                        >
                                                        کد 17 .زخم بستر درجه 3یا 4بعد از پذیرش بیمار
                                                    </option>

                                                    <option
                                                        value="42011" 

                                                        >
                                                        کد 18 .کرنیكتروس نوزاد ناشی از تعلل در درمان
                                                    </option>-->

                                                </select>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="gotoPage5(this);" class="btn btn-primary  text-white">بعدی</a>
                                    <a onclick="$('#pageFormThere').slideDown();
                                            $('#pageFormFour').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                        </div>
                        <div id="pageFormFive" style="display: none;">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله پنجم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

                            <div class="question col-lg-12 card  mg-t-10 questionDivq201" id="questionDivq201"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 21.</span>
                                            دارویی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8007"
                                                    name="q8007" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34915"

                                                           >
                                                    <label>
                                                        شناسایی بیمار به طور ناصحیح
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34916"

                                                           >
                                                    <label>
                                                        اشتباه در دوز دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34917"

                                                           >
                                                    <label>
                                                        عدم آگاهی از عوارض دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34918"

                                                           >
                                                    <label>
                                                        حذف یک دوز
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34919"

                                                           >
                                                    <label>
                                                        /انتخاب داروی اشتباه
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34920"

                                                           >
                                                    <label>
                                                        /اشتباه در زمان دادن دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34921"

                                                           >
                                                    <label>
                                                        نحوه اشتباه درراه مصرف دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34922"

                                                           >
                                                    <label>
                                                        ثبت ناصحیح مستندات مربوط به نوع دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34923"

                                                           >
                                                    <label>
                                                        /شباهت دارویی در شکل
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34924"

                                                           >
                                                    <label>
                                                        /شباهت دارویی در نام دارو
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34925"

                                                           >
                                                    <label>
                                                        تداخل دارویی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34971"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq211" id="questionDivq211"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 22.</span>
                                            سایر دارویی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8017" 
                                                   name="q8017" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq221" id="questionDivq221"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 23.</span>
                                            ترانسفوژیون خون <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8008"
                                                    name="q8008" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34926"

                                                           >
                                                    <label>
                                                        گروه خونی اشتباه
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34927"

                                                           >
                                                    <label>
                                                        عدم ثبت عوارض انتقال خون
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34928"

                                                           >
                                                    <label>
                                                        شناسایی بیمار به طور ناصحیح
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34929"

                                                           >
                                                    <label>
                                                        عدم گزارش تزریق خون
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34930"

                                                           >
                                                    <label>
                                                        ثبت ناقص فرم پایش و نظارت فرایند خون
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34931"

                                                           >
                                                    <label>
                                                        پایش نادرست بیمار حین تزریق فراورده خون
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34932"

                                                           >
                                                    <label>
                                                        عدم تطابق گروه خونی و شماره اهدا کننده روی کیسه خون با اطلاعات موجود بر روی فرم مشخصات
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34972"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq231" id="questionDivq231"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 24.</span>
                                            سایر ترانسفوژیون خون <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8018" 
                                                   name="q8018" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq241" id="questionDivq241"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 25.</span>
                                            تجهیزاتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8009"
                                                    name="q8009" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34933"

                                                           >
                                                    <label>
                                                        مفقودی تجهیزات
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34934"

                                                           >
                                                    <label>
                                                        عدم مهارت در بکارگیری دستگاه
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34935"

                                                           >
                                                    <label>
                                                        واشینگ غیر اصولی تجهیزات
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34936"

                                                           >
                                                    <label>
                                                        نگهداری تجهیزات معیوب در بخش  (عدم اعلام خرابی به واحد تجهیزات پزشکی)
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34973"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq251" id="questionDivq251"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 26.</span>
                                            سایر تجهیزاتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8019" 
                                                   name="q8019" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq261" id="questionDivq261"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 27.</span>
                                            مراقبتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8010"
                                                    name="q8010" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34937"

                                                           >
                                                    <label>
                                                        سقوط بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34938"

                                                           >
                                                    <label>
                                                        فلبیت
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34939"

                                                           >
                                                    <label>
                                                        زخم بستر
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34940"

                                                           >
                                                    <label>
                                                        سوختگی در بخش
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34941"

                                                           >
                                                    <label>
                                                        ترومای زایمانی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34942"

                                                           >
                                                    <label>
                                                        شناسایی بیمار به طور ناصحیح  (تشابه اسمی)
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34943"

                                                           >
                                                    <label>
                                                        بسته نشدن دستبند شناسایی بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34974"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq271" id="questionDivq271"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 28.</span>
                                            سایر مراقبتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8020" 
                                                   name="q8020" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq281" id="questionDivq281"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 29.</span>
                                            آزمایشگاهی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8011"
                                                    name="q8011" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34944"

                                                           >
                                                    <label>
                                                        شرایط نمونه گیری و نگهداری نمونه به صورت نامناسب
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34945"

                                                           >
                                                    <label>
                                                        عدم ثبت آزمایشات درخواستی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34946"

                                                           >
                                                    <label>
                                                        جواب اشتباه آزمایش (اشتباه تایپ)
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34947"

                                                           >
                                                    <label>
                                                        عدم اعلام نمونه اورژانس
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34948"

                                                           >
                                                    <label>
                                                        غلط بودن جواب آزمایش
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34949"

                                                           >
                                                    <label>
                                                        تاخیر در پاسخ دهی آزمایش
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34975"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq291" id="questionDivq291"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 30.</span>
                                            سایر آزمایشگاهی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8021" 
                                                   name="q8021" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq301" id="questionDivq301"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 31.</span>
                                            تصویر برداری <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8012"
                                                    name="q8012" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34950"

                                                           >
                                                    <label>
                                                        اتفاق ناگوار در انتقال بیمار به تصویربرداری
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34951"

                                                           >
                                                    <label>
                                                        عدم هماهنگی در ارجاع بیمار به بخش تصویربرداری
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34952"

                                                           >
                                                    <label>
                                                        بیمار به طور ناصحیح
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34953"

                                                           >
                                                    <label>
                                                        ناقص بودن درخواست تصویربرداری
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34954"

                                                           >
                                                    <label>
                                                        عدم حضور پرستار هنگام انتقال بیمار/عدم ثبت درخواست تصویربرداری در سیستم HIS
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34955"

                                                           >
                                                    <label>
                                                        پوزیشن نامناسب بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34956"

                                                           >
                                                    <label>
                                                        عدم بکارگیری وسایل حفاظتی بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34957"

                                                           >
                                                    <label>
                                                        اندام و موضع اشتباه
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34958"

                                                           >
                                                    <label>
                                                        شناسایی بیمار به صورت نا صحیح در رادیولوژی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34959"

                                                           >
                                                    <label>
                                                        عدم آماده سازی صحیح قبل از گرافی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34976"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq311" id="questionDivq311"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 32.</span>
                                            سایر تصویربرداری <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8022" 
                                                   name="q8022" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq321" id="questionDivq321"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 33.</span>
                                            ثبتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8013"
                                                    name="q8013" 
                                                    value=""

                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34960"

                                                           >
                                                    <label>
                                                        ناخوانا بودن دستورات پزشک
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34961"

                                                           >
                                                    <label>
                                                        خطا در ثبت فرایند مشاوره
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34962"

                                                           >
                                                    <label>
                                                        خطا در ثبت کاردکس و کارت دارویی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34963"

                                                           >
                                                    <label>
                                                        قرار دادن مدارک اشتباه بیمار در پرونده دیگر
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34964"

                                                           >
                                                    <label>
                                                        نقص درگزارشات پرستاری
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34965"

                                                           >
                                                    <label>
                                                        نسخه نویسی ناقص پزشک
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34966"

                                                           >
                                                    <label>
                                                        خطا در ثبت دستورات شفاهی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34967"

                                                           >
                                                    <label>
                                                        خطا در ثبت علایم حیاتی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34968"

                                                           >
                                                    <label>
                                                        ارتباط ناموثر
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34969"

                                                           >
                                                    <label>
                                                        خطا در ثبت رضایت اگاهانه
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34970"

                                                           >
                                                    <label>
                                                        خطا درثبت اطلاعات هویتی بیمار به صورت صحیح
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="34977"

                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq331" id="questionDivq331"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 34.</span>
                                            سایر ثبتی <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8023" 
                                                   name="q8023" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>

                            <div class="question col-lg-12 card  mg-t-10 questionDivq341" id="questionDivq341"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 35.</span>
                                            سایر خطاها <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8014" 
                                                   name="q8014" 
                                                   placeholder="" 
                                                   value=""

                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="$('#pageFormSix').slideDown();
                                            $('#pageFormFive').slideUp();" class="btn btn-primary  text-white">بعدی</a>
                                    <a onclick="$('#pageFormFour').slideDown();
                                            $('#pageFormFive').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                        </div>
                        <div id="pageFormSix" style="display: none;">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله ششم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="question col-lg-12 card  mg-t-10 questionDivq351" id="questionDivq351"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 36.</span>
                                            شرح خطا (لطفا به شكل خلاصه و دقیق توضیح فرمائید  که چگونه این خطا اتفاق افتاده است) <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <input class="col-lg-12 form-control" type="text" 
                                                   id="q8015" 
                                                   name="q8015" 
                                                   placeholder="" 
                                                   value=""
                                                   required
                                                   >                                    

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="gotoPage6(this);" class="btn btn-primary  text-white">بعدی</a>
                                    <a onclick="$('#pageFormFive').slideDown();
                                            $('#pageFormSix').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                        </div>
                        <div id="pageFormSeven" style="display: none;">
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="card text-center" >
                                <div class="card-header text-white bg-info mb-3">
                                    <h2>مرحله هفتم</h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                        
                                </div>           
                            </div>
                            <!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                            <div class="question col-lg-12 card  mg-t-10 questionDivq361" id="questionDivq361"><!-- قرار دادن کلاس برای سوال-->
                                <div class="card-body row ">

                                    <div class="col-lg-12 questionCss">
                                        <!--<div class="col-lg">-->
                                        <h3><span> 37.</span>
                                            علت بروز خطا (به نظر شما چه عواملی زمینه ساز بروز خطا بوده اند ) <!-- عنوان سوال-->
                                            <span style="float:left;">
                                                حیطه : 
                                            </span>
                                            <i class='requierd star fa fa-star' style='color:#cd0047'> </i> <!--  برای سوالات ضروری ستاره-->
                                            <br/>  <!--  توضیحات فرم-->
                                        </h3>
                                        <!--</div>-->

                                        <div class="col-lg-12 ">


                                            <!--برای ذخیره ی رشته ی انتخاب شده ها-->
                                            <input  type="hidden" 
                                                    id="q8016"
                                                    name="q8016" 
                                                    value=""
                                                    required
                                                    >                                    

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41985"
                                                           required
                                                           >
                                                    <label>
                                                        شیفت طولانی و پشت سر هم
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41986"
                                                           required
                                                           >
                                                    <label>
                                                        كمبود نیرو نسبت به بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41987"
                                                           required
                                                           >
                                                    <label>
                                                        ارتباط نامناسب با همكاران
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41988"
                                                           required
                                                           >
                                                    <label>
                                                        نقص در آموزش مستمر
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41989"
                                                           required
                                                           >
                                                    <label>
                                                        عدم توانایی در انجام مهارتهای عملی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41990"
                                                           required
                                                           >
                                                    <label>
                                                        كمبود آگاهی پرسنل ( مهارت علمی )
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41991"
                                                           required
                                                           >
                                                    <label>
                                                        تازه كار بودن پرسنل
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41992"
                                                           required
                                                           >
                                                    <label>
                                                        مهارت ناكافی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41993"
                                                           required
                                                           >
                                                    <label>
                                                        کمبود دانش
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41994"
                                                           required
                                                           >
                                                    <label>
                                                        تاخیر در انجام مداخلات و مراقبت ها توسط كادر درمان ( پزشك-پرستار)
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41995"
                                                           required
                                                           >
                                                    <label>
                                                        فقدان یك خط مشی واحد در سازمان
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41996"
                                                           required
                                                           >
                                                    <label>
                                                        بی توجهی
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41997"
                                                           required
                                                           >
                                                    <label>
                                                        ارتباط ناموثر با بیمار
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-lg row">

                                                <div class="col-lg">
                                                    <input type="checkbox"  
                                                           value="41998"
                                                           required
                                                           >
                                                    <label>
                                                        سایر
                                                    </label>
                                                </div>
                                            </div>

                                        </div>
                                        <!--//برای نمایش تک تک سواالات-->
                                        <!--                                    <input type="button" name="previous" class="previous action-button" value="Previous" style="display:none">
                                                                            <input type="button" name="next" class="next action-button" value="Next" style="display:none">-->
                                    </div>
                                </div>
                            </div>
                            <div class="card text-center">
                                <div class="card-header text-white mb-3">
                                    <h2></h2>
                                </div>
                                <div class="card-body">
                                    <h3 class="card-title"></h3>                                
                                    <a onclick="$('#pageFormSix').slideDown();
                                            $('#pageFormSeven').slideUp();" class="btn btn-primary  text-white">قبلی</a>
                                </div>           
                            </div>
                           
                        </div>
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
                             <!--</div> card -->
                <div  id ="newFormToCompleteBottons" class="row col-lg-12 formAnswerBtn" >

                    <%                        String userRoles[] = jjTools.getSeassionUserRole(request).split(",");                             
                               

                    
                        if (userRoles.length > 1) {// اگر بیشتر از یک نقش داشت سلکت اپشن نقش هایش را نشانش می دهیم  که هر کدام را خواست انتخاب کند
                    %>                  
                    <div class="col-lg-12">
                      <select id="formAnswers_userRole" name="formAnswers_userRole" class="form-control">
                            <%    for (int i = 0; i < userRoles.length; i++) {%>
                            <option value="<%=userRoles[i]%>">
                                <%=Role.getRoleName(userRoles[i], db)%>  
                            </option>
                            <%
                                }%>
                        </select>
                    </div>
                    <%
                        }
                    %>  


                    <div class="row col-sm-12 mg-t-20">
                        <div id="formAnserSetBtn" class="row col-12" style="display: none">
<!--                            <div class="col-6">
                                <button id="sabtMovaghat" class="btn btn-outline-warning mg-b-10  btn-block" onclick="formAnswerSet_insert(this);">ثبت موقت </button>                
                            </div>-->
                            <div class="col-6">
                                <button class="btn btn-outline-success mg-b-10  btn-block" onclick="formAnswerSet_insertAndFinalForm2(this);">ثبت نهایی </button>                
                            </div>
                        </div>
                    </div>
                </div>

        </div>
        </div>
        <script src="./Manager/js/jquery/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="./js/jquery/jquery-migrate-1.2.0.js" type="text/javascript"></script>
        <script src="./Manager/popper.js"></script>
        <script src="./Manager/bootstrap.js"></script>
        <!--<link href="../Manager/dataTable/select2.min.css" rel="stylesheet">-->

    <link href="./css/jquery/orang/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css">
    <script src="./js/jquery/ui/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
    <script src="./Manager/js/calendar/jquery.ui.datepicker-cc.js" type="text/javascript"></script>
    <script src="./Manager/js/calendar/jquery.ui.datepicker-cc-fa.js" type="text/javascript"></script>
    <script src="./Manager/js/calendar/calendar.all.js" type="text/javascript"></script>

    <!--upload برای سوالاتی که آپلود فایل نیاز دارند-->
    <script src="../Manager/js/ajaxfileupload.js" type="text/javascript"></script>

    <!--jj2-->
    <script src="./Manager/shamcey.js"></script>
    <script src="./Manager/js/js.cookie.js" type="text/javascript"></script>
    <script src="./Manager/dataTable/select2.min.js"></script>
    <script src="./Manager/js/jj2.js" type="text/javascript"></script>
    <script src="./Manager/js/index.js" type="text/javascript"></script>
    <script src="./jsHMIS/department.js" type="text/javascript"></script>
    <script src="./jsHMIS/role.js" type="text/javascript"></script>
    <script src="./jsCms/user.js" type="text/javascript"></script>  
    <!--<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>-->
    <script src="https://maps.google.com/maps/api/js?sensor=false"></script>
    <!--<script src="template/js/questionJs_1.js" type="text/javascript"></script>-->
    <script type="text/javascript">
        function formAnswerSet_insertAndFinalForm2(obj) {
            
      var requireds = $("#pageFormSeven input[type=hidden][required],#pageFormSeven input[type=text]:required,#pageFormSeven input[type=number]:required,select.required,#pageFormSeven select:required");
    
    var flag = true;
    var firsnonAnswered;
    var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
    $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
    for (var i = 0; i < requireds.length; i++) {
        if ($(requireds[i]).val().trim() === "") {
            $(requireds[i]).parent().parent().addClass('redBorder');
            if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
                firsnonAnswered = $(requireds[i]).parent().parent();
            }
            flag = false;
        } else {
            $(requireds[i]).parent().parent().removeClass('redBorder');
        }
    }
    if (!flag) {
        new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
        $([document.documentElement, document.body]).animate({
            scrollTop: $(firsnonAnswered).offset().top
        }, 500);
        $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
        return;
    }
    var param = "";
    param += "do=FormAnswerSet.insert";
    param += "&formAnswers_status=ثبت نهایی";
    param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
    if ($('#latitude').val() != "" && $('#longitude').val() != "") {
        param += "&latLang=" + $('#latitude').val() + "," + $('#longitude').val();
    }
    new jj(param).jjAjax2(false);
}
  
                                                new jj("body input[type='number']").jjSetTextFieldOnlyGetNumberAndDot(10000000, 0);// حداکثر طول رشته داخل نامبر
                                                $(".questionCss input:checkbox").click(function () {
                                                    var checked = $(this).parent().parent().parent().find("input:checkbox:checked");
                                                    var newVal = "";
                                                    for (var i = 0; i < checked.length; i++) {
                                                        newVal += $(checked[i]).val() + ",";
                                                    }
                                                    $(this).parent().parent().parent().find("input:hidden").val(newVal);
                                                });
                                                $(".questionCss input:radio").click(function () {
                                                    $(this).parent().parent().parent().find("input:hidden").val($(this).val());
                                                });
    </script>    
    <script type="text/javascript">
//  $("select").select2({
//                width: '100%'
//            });
        (function () {

            $('#ajaxLoaderDiv').show();
            $(window).on('load', function () {
                $('#ajaxLoaderDiv').hide();
            });
        <%  if (formRow.get(0).get(Forms._isChangeableUserId).toString().equals("1")) {
                System.out.println("jjTools.getSessionAttribute(request, '#ID')=" + jjTools.getSessionAttribute(request, "#ID"));%>
            new jj("#formAnswers_userId").jjVal('<%=jjTools.getSessionAttribute(request, "#ID").equals("0") ? "" : jjTools.getSessionAttribute(request, "#ID")%>');
            $("#formAnswers_userId").on('change', function () {
                hmisRole.getUeserRolesSelectOptionByUserId($("#formAnswers_userId").val(), "#formAnswers_userRole");
            });
        <%}%>

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
                        //$("#newFormToCompleteBottons .btn-outline-warning ,#newFormToCompleteBottons .btn-outline-success").remove();
                        // alert("شما قبلا به این فرم پاسخ گفته اید!!!");
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
          
            hmisDepartment.selectOptionDepartment(".section");
            $(".section").select2({
                width: '100%'
            });
        })();
        <%=jjfileUplaodScripts%>

    </script>
    <div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>
    <script>
//        function initialize() {
//            if (navigator.geolocation) {
//                navigator.geolocation.getCurrentPosition(showMap, showError);
//            } else {
//                alert("Sorry, your browser does not support HTML5 geolocation.");
//            }
//        }

//        // Define callback function for successful attempt
//        function showMap(position) {
//            // Get location data
//            var $latitude = document.getElementById('latitude');
//            var $longitude = document.getElementById('longitude');
//            var lat = position.coords.latitude;
//            var long = position.coords.longitude;
//            var latlong = new google.maps.LatLng(lat, long);
//            $latitude.value = latlong.lat();
//            $longitude.value = latlong.lng();
//
//            var myOptions = {
//                center: latlong,
//                zoom: 16,
//                mapTypeControl: true,
//                navigationControlOptions: {
//                    style: google.maps.NavigationControlStyle.SMALL
//                }
//            }
//
//            var map = new google.maps.Map(document.getElementById("embedMap"), myOptions);
//            //    var marker = new google.maps.Marker({ position:latlong, map:map, title:"You are here!" });
//
//            var marker = new google.maps.Marker({
//                position: latlong,
//                map: map,
//                title: 'Drag Me!',
//                draggable: true
//            });
//
//            google.maps.event.addListener(marker, 'dragend', function (marker) {
//                var latLng = marker.latLng;
//                $latitude.value = latLng.lat();
//                $longitude.value = latLng.lng();
//            });
//
//        }

        // Define callback function for failed attempt
        function showError(error) {
            if (error.code == 1) {
                result.innerHTML = "You've decided not to share your position, but it's OK. We won't ask you again.";
            } else if (error.code == 2) {
                result.innerHTML = "The network is down or the positioning service can't be reached.";
            } else if (error.code == 3) {
                result.innerHTML = "The attempt timed out before it could get the location data.";
            } else {
                result.innerHTML = "Geolocation failed due to unknown error.";
            }
        }
//        initialize();
    </script> 
    <script type="text/javascript">
        function gotoPage3(obj) {
//          var requireds = $("#pageFormTwo input[type=hidden][required]:text,#pageFormTwo input[required]:hidden,select.required, select:required");
            var requireds = $("#pageFormTwo input[type=hidden][required],#pageFormTwo input[type=text]:required,#pageFormTwo input[type=number]:required,select.required,#pageFormTwo select:required");
//            alert(requireds.length);
            var flag = true;
            var firsnonAnswered;
            var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
            $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
            for (var i = 0; i < requireds.length; i++) {
                if ($(requireds[i]).val().trim() === "") {
                    $(requireds[i]).parent().parent().addClass('redBorder');
                    if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
                        firsnonAnswered = $(requireds[i]).parent().parent();
                    }
                    flag = false;
                } else {
                    $(requireds[i]).parent().parent().removeClass('redBorder');
                    $(obj).attr("onclick", tempStr);
                }
            }
            if (!flag) {
                new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
                $([document.documentElement, document.body]).animate({
                    scrollTop: $(firsnonAnswered).offset().top
                }, 500);
                $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
                return;
            }
            $('#pageFormThere').slideDown();
            $('#pageFormTwo').slideUp();
        }
        function gotoPage4(obj) {
//          var requireds = $("#pageFormTwo input[type=hidden][required]:text,#pageFormTwo input[required]:hidden,select.required, select:required");
            var requireds = $("#pageFormThere input[type=hidden][required],#pageFormThere input[type=text]:required,#pageFormThere input[type=number]:required,select.required,#pageFormThere select:required");

//            alert(requireds.length);
            var flag = true;
            var firsnonAnswered;
            var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
            $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
            for (var i = 0; i < requireds.length; i++) {
                if ($(requireds[i]).val().trim() === "") {
                    $(requireds[i]).parent().parent().addClass('redBorder');
                    if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
                        firsnonAnswered = $(requireds[i]).parent().parent();
                    }
                    flag = false;
                } else {
                    $(requireds[i]).parent().parent().removeClass('redBorder');
                    $(obj).attr("onclick", tempStr);
                }
            }
            if (!flag) {
                new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
                $([document.documentElement, document.body]).animate({
                    scrollTop: $(firsnonAnswered).offset().top
                }, 500);
                $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
                return;
            }
            $('#pageFormFour').slideDown();
            $('#pageFormThere').slideUp();
        }

        function gotoPage5(obj) {
//          var requireds = $("#pageFormTwo input[type=hidden][required]:text,#pageFormTwo input[required]:hidden,select.required, select:required");
            var requireds = $("#pageFormFour input[type=hidden][required],#pageFormFour input[type=text]:required,#pageFormFour input[type=number]:required,select.required,#pageFormFour select:required");

//            alert(requireds.length);
            var flag = true;
            var firsnonAnswered;
            var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
            $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
            for (var i = 0; i < requireds.length; i++) {
                if ($(requireds[i]).val().trim() === "") {
                    $(requireds[i]).parent().parent().addClass('redBorder');
                    if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
                        firsnonAnswered = $(requireds[i]).parent().parent();
                    }
                    flag = false;
                } else {
                    $(requireds[i]).parent().parent().removeClass('redBorder');
                    $(obj).attr("onclick", tempStr);
                }
            }
            if (!flag) {
                new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
                $([document.documentElement, document.body]).animate({
                    scrollTop: $(firsnonAnswered).offset().top
                }, 500);
                $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
                return;
            }
            $('#pageFormFive').slideDown();
            $('#pageFormFour').slideUp();
        }
        function gotoPage6(obj) {
            $("#formAnserSetBtn").css('display','block');
//          var requireds = $("#pageFormTwo input[type=hidden][required]:text,#pageFormTwo input[required]:hidden,select.required, select:required");
            var requireds = $("#pageFormSix input[type=hidden][required],#pageFormSix input[type=text]:required,#pageFormSix input[type=number]:required,select.required,#pageFormSix select:required");

//            alert(requireds.length);
            var flag = true;
            var firsnonAnswered;
            var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
            $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
            for (var i = 0; i < requireds.length; i++) {
                if ($(requireds[i]).val().trim() === "") {
                    $(requireds[i]).parent().parent().addClass('redBorder');
                    if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
                        firsnonAnswered = $(requireds[i]).parent().parent();
                    }
                    flag = false;
                } else {
                    $(requireds[i]).parent().parent().removeClass('redBorder');
                    $(obj).attr("onclick", tempStr);
                }
            }
            if (!flag) {
                new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
                $([document.documentElement, document.body]).animate({
                    scrollTop: $(firsnonAnswered).offset().top
                }, 500);
                $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
                return;
            }
              $('#pageFormSeven').slideDown();
               $('#pageFormSix').slideUp();
        }
       
      
        $("#questionDivq171").hide();
        $("#questionDivq191").hide();
        //for question 17 option yes
        $("input[value=34884]").on("change", function () {
            $("#questionDivq171").show();
            $("#q8004").attr("required", "required");
        });
        //for question 17 option no
        $("input[value=34885]").on("change", function () {
            $("#questionDivq171").hide();
            $("#q8004").removeAttr("required");
            
        });
        //for question 19 option no
        $("input[value=34895]").on("change", function () {
            $("#questionDivq191").show();
            $("#q8006").attr("required", "required");
        });
        //for question 19 option no
        $("input[value=34896]").on("change", function () {
            $("#questionDivq191").hide();
            $("#q8006").removeAttr("required");
        });



        ///////////////////////////////
        //for question 22 option no
        $("#questionDivq211").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34971") {
                    $("#questionDivq211").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34971") {
                        $("#questionDivq211").hide();
                    }
                }
            }
        });

//        ///////////////////////////////
        //for question 23 option no
        $("#questionDivq231").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34972") {
                    $("#questionDivq231").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34972") {
                        $("#questionDivq231").hide();
                    }
                }
            }
        });
//        /////////////////////////////// 
//         //for question 24 option no
        $("#questionDivq251").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34973") {
                    $("#questionDivq251").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34973") {
                        $("#questionDivq251").hide();
                    }
                }
            }
        });
//        /////////////////////////////// 
//       //for question 25 option no
        $("#questionDivq271").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34974") {
                    $("#questionDivq271").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34974") {
                        $("#questionDivq271").hide();
                    }
                }
            }
        });
//        /////////////////////////////// 
//        //for question 26 option no
        $("#questionDivq291").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34975") {
                    $("#questionDivq291").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34975") {
                        $("#questionDivq291").hide();
                    }
                }
            }
        });
//        /////////////////////////////// 
//         //for question 27 option no
        $("#questionDivq311").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34976") {
                    $("#questionDivq311").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34976") {
                        $("#questionDivq311").hide();
                    }
                }
            }
        });
//        /////////////////////////////// 
//              //for question 28 option no
        $("#questionDivq331").hide();
        $("input[type=checkbox]").click(function () {
            var value = $(this).attr("value");
            if (this.checked == true) {
                if (value == "34977") {
                    $("#questionDivq331").show();
                }
            } else {
                if (this.checked == false) {
                    if (value == "34977") {
                        $("#questionDivq331").hide();
                    }
                }
            }
        });
//        
//        







    </script>



</body></html>