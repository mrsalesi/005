
// global pmsDavaran, pmsDarkhastDavaran, pmsKargrohha, pmsDarkhasthayeerjaeshodebeAzayekargroh, cmsPyamha, pmsDarkhasthayeErjaeShodeBeSarparastKargroh */

// // Server 
// Servlet name for response to user request
var setting_server = "Server";

// main content
// this page 'on page loading' show to user automaticaly
var setting_default_sw = "صفحه اصلی";
//var setting_default_sw = "خانه"; 

// search
// this is id of
var setting_search_input_id = "#jjSearchInput";
var setting_search_input_id_btn = "#jjSearchBtn";

// public comment form setting
var setting_comment_main_panel = "#sw";
var setting_comment_afted_send_text = setting_default_sw;

// news slider
var setting_news_slider = "#jjSliderNews";
http://localhost:8092/005/Server?do=FormAnswerSet.add_new&formAnswers_formId=60
        var setting_product_slider = "#jjSliderProduct";
// you must add this style to page
// <script type="text/javascript" src="js/slide/slider.js"></script>
// p#controls { margin:0; padding:0; position:relative; }
// #prevBtn { display:block; margin:0; overflow:hidden; width:16px; height:16px; position:absolute; left:-30px; top:-140px; }
// #nextBtn { display:block; margin:0; overflow:hidden; width:16px; height:16px; position:absolute; left: 650px; top:-140px; }
// #prevBtn a { display:block; width:16px; height:16px; background:url(js/slide/prev4.png) no-repeat 0 0; }
// #nextBtn a { display:block; width:16px; height:16px; background:url(js/slide/next4.png) no-repeat 0 0; }

// pic slider
var setting_flash_slider_panel = "#jjSliderFlash";
var setting_flash_slider_default_PicLink = "<a href='http://www.adobe.com/go/getflashplayer'><img src='upload/defualtPic.jpg' style='width: 150;height: 290'  alt='Get Adobe Flash player' /></a>";
var setting_flash_slider_panel_default_height = 290;
var setting_flash_slider_panel_default_width = 950;
// pic slider
var setting_pic_slider_panel = "#jjSliderPic";
var setting_pic_slider_responsive_panel = "#jjSliderPicResponsive";
var setting_pic_slider_delay = "6000";
// you must add this style to page
// <script type="text/javascript" src="js/slide/slider.js"></script>
// <script type="text/javascript" src="js/slider2/easySlider1.5.js"></script>
// .cs-buttons { display:block; margin:-32px 0 0; padding:0; font-size:0px; float:left;}
// .cs-buttons a { margin:0 2px; width:20px; height:20px; float:left; color:#fff; text-indent:-10000px; background:url(js/slide/slide_p.png) no-repeat center center;}
// .cs-buttons a.cs-active { color:#fff; background-image:url(js/slide/slide_a.png);}

//
var setting_login_exit_panel = ".jjLoginExitPanel";
//################کد های ویژه ی سمت کلاینت کاربر نهایی مثل فرم ها و محتوا و غیره#############
/**
 * این تابع اطلاعات یک فرم را برای ذخیره ی موقت و اینسرت اولیه به سرور می فرسد.
 * سوالات اجابری را چک نمی کند که تکمیل شده باشند
 * برای ویرایش و ثبت نهایی شرایط فرق دارد
 * @returns {undefined}
 */
function formAnswerSet_insert() {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
    var flag = true;
    var firsnonAnswered;
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
        new jj(" در صورت تایید در فرصت های دیگر باید به سوالات پاسخ بدهید،<br/>ثبت موقت بشود؟").jjModal_Yes_No("شما به تعدادی از سوالات پاسخ نداده اید",
                "new jj('do=FormAnswerSet.insert&'+new jj('#swOneFormToCompleteForm').jjSerial()).jjAjax2(false);");
        $([document.documentElement, document.body]).animate({
            scrollTop: $(firsnonAnswered).offset().top
        }, 500);
        return;
    } else {
        var param = "";
        param += "do=FormAnswerSet.insert";
        param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
        new jj(param).jjAjax2(false);
        $("formAnserSetBtn").html("");
    }
}
;
/**
 * ویرایش پاسخ های داده شده که در وضعیت ثبت اولیه هستند
 * سوالاتی که اجباری هستند را خطا نمیدهد
 */
function formAnswerSet_edit() {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
    var flag = true;
    var firsnonAnswered;
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
        new jj(" در صورت تایید در فرصت های دیگر باید به سوالات پاسخ بدهید،<br/>ثبت موقت بشود؟").jjModal_Yes_No("شما به تعدادی از سوالات پاسخ نداده اید",
                "new jj('do=FormAnswerSet.edit&'+new jj('#swOneFormToCompleteForm').jjSerial()).jjAjax2(false);");
        $([document.documentElement, document.body]).animate({
            scrollTop: $(firsnonAnswered).offset().top
        }, 500);
        return;
    } else {
        var param = "";
        param += "do=FormAnswerSet.edit";
        param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
        new jj(param).jjAjax2(false);
        $("formAnserSetBtn").html("");
    }
}
;
function formAnswerSet_delete(id) {
    new jj("حذف شود؟").jjModal_Yes_No("حذف فرم", "after_question_formAnswerSet_delete(" + id + ");");

}
;
function after_question_formAnswerSet_delete(id) {
    var param = "";
    param += "do=FormAnswerSet.delete";
    param += "&id=" + id;
    new jj(param).jjAjax2(false);
}
;
/**
 *  ذخیره ی و ثبت نهایی فرم
 *  به سرور می فرسد.
 * سوالات اجباری را چک می کند که تکمیل شده باشند و در غیر اینصورت اسکرول می کند به آن سوال
 * @returns {undefined}
 */
function formAnswerSet_editAndFinalForm() {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
    var flag = true;
    var firsnonAnswered;
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
        return;
    }
    var param = "";
    param += "do=FormAnswerSet.edit";
    param += "&formAnswers_status=ثبت نهایی";
    param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
    new jj(" در صورت تایید فرم قابل تغییر نیست،<br/>ثبت نهایی بشود؟").jjModal_Yes_No("ثبت نهایی فرم", "new jj('" + param + "').jjAjax2(false);");
}
/**
 *  ذخیره ی و ثبت نهایی فرم
 *  به سرور می فرسد.
 * سوالات اجباری را چک می کند که تکمیل شده باشند و در غیر اینصورت اسکرول می کند به آن سوال
 * @returns {undefined}
 */
function formAnswerSet_insertAndFinalForm() {
    alert();
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
    var flag = true;
    var firsnonAnswered;
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
        return;
    }
    var param = "";
    param += "do=FormAnswerSet.insert";
    param += "&formAnswers_status=ثبت نهایی";
    param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
    new jj(param).jjAjax2(false);
}

/**
 * پاک کردن آی دی فرم هایی که فقط یکبار باید پر میشدند ولی الان وضعیت تغییر کرده
 *  از کوکی
 *  ممکن است تغییر وضعیت بخاطر تغییر تنظیمات فرم باشد
 * @param {type} id
 * @returns {undefined}
 */
function  removeFormIdFromCookie(id) {
    var uniqueForms = Cookies.get('#UNIQUE_FORMS_Compleited');// فرم های که باید توسط هر کاربر یکبار تکمیل شوندبعد از تکمیل نهایی در این کوکی ذخیره میشوند
    if (uniqueForms) {
        var uniqueFormsId = uniqueForms.split(",");
        var temp = "";
        for (var i = 0; i < uniqueFormsId.length; i++) {
            if (uniqueFormsId[i] == id || uniqueFormsId[i] == "") {// یعنی این فرم قبلا توسط این کاربر با موفقیت ثبت شده است و در کوکی ها ست شده
                ;
            } else {
                temp += uniqueFormsId[i] + ",";
            }
        }
        Cookies.set("#UNIQUE_FORMS_Compleited", temp.substring(0, (temp.length - 1)));//برای حذف کامای آخر
    }
//    alert("set #removeFormIdFromCookie:" + uniqueForms);
}
;

/**
 * ست کردن آی دی فرم هایی که یکبار باید پر بشوند در کوکی
 * @param {type} id
 * @returns {undefined}
 */
function  addFormIdToCookie(id) {
    var uniqueForms = Cookies.get('#UNIQUE_FORMS_Compleited');// فرم های که باید توسط هر کاربر یکبار تکمیل شوندبعد از تکمیل نهایی در این کوکی ذخیره میشوند    
    if (uniqueForms) {
        var uniqueFormsId = uniqueForms.split(",");
        uniqueForms = "";
        for (var i = 0; i < uniqueFormsId.length; i++) {
            if (uniqueFormsId[i] == id) {// یعنی این فرم قبلا توسط این کاربر با موفقیت ثبت شده است و در کوکی ها ست شده
                ;
            } else {
                uniqueForms += uniqueFormsId[i] + ",";
            }
        }
        uniqueForms += id;
        Cookies.set("#UNIQUE_FORMS_Compleited", uniqueForms);
    } else {
        Cookies.set("#UNIQUE_FORMS_Compleited", (id));// اگر ست نشده بود
        uniqueForms = Cookies.get('#UNIQUE_FORMS_Compleited');
    }
    alert("set #UNIQUE_FORMS_Compleited:" + uniqueForms);
}
;



//#####################################################################
// -----------------------------------------------------------------
function initCms(lang) {
    lang = lang == null ? "fa" : lang.toString().toLowerCase();
    jj(setting_server).jjSetServletName();
    if (lang == 'en') {
        $("#sw").css('direction', 'ltr');
        $("#sw").css('text-align', 'left');
    }
    if (lang == 'fa') {
        $("#sw").css('direction', 'rtl');
        $("#sw").css('text-align', 'justify');
    }
    if (lang == 'ar') {
        $("#sw").css('direction', 'rtl');
        $("#sw").css('text-align', 'justify');
    }
    jj(lang).jjSetLanguage();
    if (setting_search_input_id != "") {
        if ($(setting_search_input_id).length > 0) {
            search(setting_search_input_id);
        }
    }
    if (setting_default_sw != "") {
        sw(setting_default_sw);
    }
}
function jjNewsSlider(newsSliderDivId) {
    if (!(newsSliderDivId == null || newsSliderDivId == "")) {
        setting_news_slider = newsSliderDivId;
    }
    if (setting_news_slider != "") {
        if ($(setting_news_slider).length > 0) {
            newsSlider2(setting_news_slider);
        }
    }
}
//============ BY RASHIDI ========>
function jjProductSlider(productSliderDivId) {
    if (!(productSliderDivId == null || productSliderDivId == "")) {
        setting_product_slider = productSliderDivId;
    }
    if (setting_product_slider != "") {
        if ($(setting_product_slider).length > 0) {
            productSlider2(setting_product_slider);
        }
    }
}
//<============ BY RASHIDI ========
function jjSerchBtnInit(searchDivId) {
    if (!(searchDivId === null || searchDivId === "")) {
        setting_search_input_id_btn = searchDivId;
    }
    if (setting_search_input_id_btn !== "") {
        if ($(setting_search_input_id_btn).length > 0) {
            $(setting_search_input_id_btn).click(function () {
                if ($(setting_search_input_id).length > 0) {
                    var text = new jj(setting_search_input_id).jjVal();
                    if (text.length > 2) {
                        searchAction(text);
                    }
                }
            });
        }
    }
}
function jjAutoSlider(selector) {
    try {
        if (selector != null) {
            setting_flash_slider_panel = selector;
        }
        if (setting_flash_slider_panel != "") {
            if (setting_flash_slider_default_PicLink != "") {
                if ($(setting_flash_slider_panel).html() == "") {
                    $(setting_flash_slider_panel).html(setting_flash_slider_default_PicLink);
                }
            }
            if ($(setting_flash_slider_panel).length > 0) {
                var flashvars = {};
                flashvars.xml = "config.xml";
                if (LANGUAGE == 'en') {
                    flashvars.xml = "config_en.xml";
                }
                if (LANGUAGE == 'ar') {
                    flashvars.xml = "config_ar.xml";
                }
                flashvars.font = "font.swf";
                var attributes = {};
                attributes.wmode = "transparent";
                attributes.id = "slider";
                var panelWidth = $(setting_flash_slider_panel).css('width').replace("px", "", 0);
                var panelHeight = $(setting_flash_slider_panel).css('height').replace("px", "", 0);
                panelWidth = panelWidth == null ? setting_flash_slider_panel_default_width : panelWidth;
                panelHeight = panelHeight == null ? setting_flash_slider_panel_default_height : panelHeight;
                panelHeight = panelHeight == "404" ? setting_flash_slider_panel_default_height : panelHeight;
                swfobject.embedSWF("js/cu3er.swf", setting_flash_slider_panel.replace("#", ""), panelWidth, panelHeight, "9", "expressInstall.swf", flashvars, attributes);
            } else if (setting_pic_slider_panel != "" && $(setting_pic_slider_panel).length > 0)
            {
                picSlider(setting_pic_slider_panel, setting_pic_slider_delay);
            } else if (setting_pic_slider_responsive_panel != "" && $(setting_pic_slider_responsive_panel).length > 0)
            {
                picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
            }
        }
    } catch (e) {
        setting_pic_slider_panel = setting_flash_slider_panel;
        if (setting_pic_slider_panel != "") {
            if ($(setting_pic_slider_panel).length > 0) {
                picSlider(setting_pic_slider_panel, setting_pic_slider_delay);
            }
        }
    }
}

var swArray = new Array();
function sw(titleTextOrId) {
    // ------------------  add request to Array for history --------------------
    swArray.push(titleTextOrId);
    // ------------------  clean value in titleTextOrId ------------------------
    titleTextOrId = titleTextOrId.toString().toLowerCase();
    while (titleTextOrId.indexOf("\n") > -1) {
        titleTextOrId = titleTextOrId.replace("\n", "");
    }
    while (titleTextOrId.indexOf("</span>") > -1) {
        titleTextOrId = titleTextOrId.replace("</span>", "");
    }
    while (titleTextOrId.indexOf("<span>") > -1) {
        titleTextOrId = titleTextOrId.replace("<span>", "");
    }
    titleTextOrId = new jj(titleTextOrId).jjTrim();

    // ------------------  switch sw and slider --------------------------------
    //    if(titleTextOrId=="خانه"){
    //        $('#sw').hide();
    //        $('#jjSliderPic').show();
    //        $('#jjSliderNews').show();
    //    }else{
    //        $('#sw').show();
    //        $('#jjSliderPic').hide();
    //        $('#jjSliderNews').hide();
    //    }

    // ------------------  append 'comment form' to sw -------------------------
    if (titleTextOrId.toString().toLowerCase() == "$comment") {
        $("#sw").append("<div id='pCommentDiv' class='pCommentDiv'></div>");
//        var commentPage = LANGUAGE == 'fa' ? 'public_comment_fa.html' : (LANGUAGE == 'en' ? 'public_comment_en.html' : 'public_comment_ar.html');
        var commentPage = LANGUAGE == '1' ? 'public_comment1.html' : (LANGUAGE == '2' ? 'public_comment2.html' : (LANGUAGE == '3' ? 'public_comment3.html' : (LANGUAGE == '4' ? 'public_comment4.html' : 'public_comment5.html')));//====== BY RASHIDI ======
//        alert(commentPage);
        $("#pCommentDiv").load("formCms/" + commentPage, null, function () {
            $('#insert_Comment').button().click(function () {
                if ($('#comment_full_name').val() == '') {
                    $('#comment_full_name').css("border", "red dashed");
                } else if ($('#comment_email').val() == '' && $('#comment_tell').val() == '') {
                    $('#comment_full_name').css("border", "none");
                    $('#comment_email').css("border", "red dashed");
                    $('#comment_tell').css("border", "red dashed");
                } else if ($('#comment_text').val() == '') {
                    $('#comment_full_name').css("border", "none");
                    $('#comment_tell').css("border", "none");
                    $('#comment_email').css("border", "none");
                    $('#comment_text').css("border", "red dashed");
                } else {
                    jj("do=Comment.insert&" + new jj("#swCommentForm").jjSerial()).jjAjax2(false, 'Server');
                    sw(setting_comment_afted_send_text);
                }
            });
            if (USER_NAME != '') {
                new jj('#comment_full_name').jjVal(USER_NAME + ' ' + USER_FAMILY);
                new jj('#comment_email').jjVal(USER_EMAIL);
                $('#comment_full_name').attr('disabled', 'disabled');
                $('#comment_email').attr('disabled', 'disabled');
                $('#comment_tell').focus();
            } else {
                $('#comment_full_name').focus();
            }
        });
        $("#swTitle").html("تماس با ما");
        swRightClear();
        return false;
    }
    // ------------------  append 'comment form' to sw -------------------------
    if (titleTextOrId.toString().toLowerCase() == "$comment2") {
        $("#sw").html("<div id='pCommentDiv'></div>");
        var commentPage = LANGUAGE == 'fa' ? 'public_comment_fa.html' : 'public_comment_en.html';
        $("#pCommentDiv").load("formCms/" + commentPage, null, function () {
            $('#insert_Comment').button().click(function () {
                jj("do=Comment.insert&" + new jj("#swCommentForm").jjSerial()).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            });
            if (USER_NAME != '') {
                new jj('#comment_full_name').jjVal(USER_NAME + ' ' + USER_FAMILY);
                new jj('#comment_email').jjVal(USER_EMAIL);
                $('#comment_full_name').attr('disabled', 'disabled');
                $('#comment_email').attr('disabled', 'disabled');
                $('#comment_tell').focus();
            } else {
                $('#comment_full_name').focus();
            }
        });
        $("#swTitle").html("تماس با ما");
        swRightClear();
        return false;
    }
    // ------------------  show login form dialog ------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$login") {
        if (USER_NAME == '') {
            showLoginForm();
        } else {
            new jj('کاربر محترم ' + USER_NAME + "&nbsp;" + USER_FAMILY + " آیا مایلید از سیستم خارج شوید؟").jjDialog_YesNo('signOut();")', true, "خروج");
        }
        return false;
    }
    if (titleTextOrId.toString().toLowerCase() == "$loginpardakht") {
        if (USER_NAME == '') {
            showLoginFormPardakht();
        } else {
            new jj('کاربر محترم ' + USER_NAME + "&nbsp;" + USER_FAMILY + " آیا مایلید از سیستم خارج شوید؟").jjDialog_YesNo('signOut();")', true, "خروج");
        }
        return false;
    }


    // ------------------  show enrolment form ---------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$enrolment") {
        $("#sw").append("<div id='enrolmentDiv'></div>");
        var enrolPage = LANGUAGE == 'fa' ? 'public_enrolment.html' : 'public_enrolment_en.html';
        $("#enrolmentDiv").load("formCms/" + enrolPage, null, function () {
            new jj('#enrolment_date').jjCalendarWithYearSelector(1340, 1380);
            $('#insert_enrolment').button().click(function () {
                jj("do=Enrolment.insert&" + new jj("#swEnrolmentFormPublic").jjSerial()).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            });

            $('#enrol_url_file').button().click(function () {
            });
            $('#upload_Enrol').button().click(function () {
                if (new jj('#enrol_url_file').jjVal() != '') {
                    $('#upload_Enrol').hide();
                    $('#enrol_url_file').hide();
                    $('#enrolment_pic').show()
                }
            });
            $('#enrolment_pic').button().click(function () {
                $('#upload_Enrol').show();
                $('#enrol_url_file').show();
                $('#enrolment_pic').hide()
            });
            new jj('#upload_Enrol').jjAjaxFileUpload('#enrol_url_file', '#enrolment_pic', '#enrol_url_pic_demo');
            new jj('#upload_Enrol').jjAjaxFileUpload('#enrol_url_file', '#enrolment_pic1', '#enrol_url_pic_demo');//====== BY RASHIDI ======

            $('#enrol_url_file2').button().click(function () {
            });
            $('#upload_Enrol2').button().click(function () {
                if (new jj('#enrol_url_file2').jjVal() != '') {
                    $('#upload_Enrol2').hide();
                    $('#enrol_url_file2').hide();
                    $('#enrolment_file').show()
                }
            });
            $('#enrolment_file').button().click(function () {
                $('#upload_Enrol2').show();
                $('#enrol_url_file2').show();
                $('#enrolment_file').hide()
            });
            new jj('#upload_Enrol2').jjAjaxFileUpload2('#enrol_url_file2', '#enrolment_file');
            new jj('#upload_Enrol2').jjAjaxFileUpload2('#enrol_url_file2', '#enrolment_file1');//====== BY RASHIDI ======
        });
        $("#swTitle").html("فرم استخدام");
        swRightClear();
        return false;
    }
    // ------------------  show news -------------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$news") {
        swGetNews();
        swRightNewsMenu("swRight");
        return false;
    }

    // ------------------  show forum categorys --------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$forum") {
        new jj("do=Category_Forum.getList&panel=sw").jjAjax2(true);
        $("#swTitle").html("انجمن ها");
        swRightClear();
        return false;
    }

    // ------------------  show polls ------------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$poll") {
        $("#swTitle").html("نظر سنجی ها");
        swRightClear();
        new jj("do=Poll.getList&panel=sw").jjAjax2(true);
        return false;
    }

    // ------------------  show pic gallery ------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$gallery") {
        $("#swTitle").html("گالری تصاویر");
        swRightClear();
        swGetGallery();
        return false;
    }
    // ------------------  show pic gallery ------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$product") {
        $("#swTitle").html("محصولات");
//        swRightProductMenu("swRight");
        swGetProducts();
        return false;
    }
    // ------------------  get data from content table  ------------------------
    new jj("do=Content.sw&text=" + titleTextOrId.toString() + "&panel=sw&title=swTitle&jj=1").jjAjax(true, null);
    swRightClear();

    /*
     *@augments if menu dosnt need , it must be empty
     **/
    $('html').scrollTop(100);
}
;
function swRightClear() {
//    $("#sidebarright").html("");
    $("#sidebarright").css("display", "none");
}


function showLoginForm() {
    if ($('#pshowLoginFormDiv').length == 0) {
        $("body").append("<div id='pshowLoginFormDiv'></div>");
        $("#pshowLoginFormDiv").load("formCms/public_login.html", null, function () {
            $('#loginBtn').button().click(function () {
                signIn();
            });
            $('#registBtn').button().click(function () {
                registInSite();
            });

            jj("#user_pass1").jjAddEnterKeyListener("signIn();");
            jj("#user_email1").jjAddEnterKeyListener("signIn();");
            jj("#user_answer").jjAddEnterKeyListener("registInSite();");
            jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);
            $("#loginRegistForm").dialog({
                autoOpen: false,
                height: 450,
                width: 790,
                modal: true,
                title: "ورود - ثبت نام",
                buttons: {
                    "لغو": function () {
                        $(this).dialog("close");
                    }
                },
                close: function () {
                    $(this).dialog('destroy');
                }
            });
            $("#loginRegistForm").dialog("open");
            return false;
        });
    }
    $("#loginRegistForm").dialog({
        autoOpen: false,
        height: 450,
        width: 790,
        modal: true,
        title: "ورود - ثبت نام",
        buttons: {
            "لغو": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $(this).dialog('destroy');
        }
    });
    $("#loginRegistForm").dialog("open");
}

var someStringInSw = "$comment,$login,$enrolment,$news,$forum,$gallery,$loginpardakht";
function refreshLastSwParameter() {
    if (swArray.length > 2) {
        refreshLastSwParameter3();
        return false;
    }
    if (swArray.length > 1) {
        refreshLastSwParameter2();
        return false;
    }
    if (swArray.length > 0) {
        refreshLastSwParameter1();
        return false;
    }
}
function refreshLastSwParameter1() {
    if (someStringInSw.indexOf(swArray[swArray.length - 1]) < 0) {
        sw(swArray[swArray.length - 1]);
    }
}
function refreshLastSwParameter2() {
    if (someStringInSw.indexOf(swArray[swArray.length - 1]) < 0) {
        sw(swArray[swArray.length - 1]);
    }
}
function refreshLastSwParameter3() {
    if (someStringInSw.indexOf(swArray[swArray.length - 2]) < 0) {
        sw(swArray[swArray.length - 2]);
    }
}


/////////////////////////ورود به سیستم Hmis
/**
 * ورود کاربرانب که به قسمت های
 * hmis
 * دسترسی دارند
 * @returns {Boolean}
 */
function login() {
    alert("خوش امدید");
//    var param="";
    if (new jj('#login_user_email').jjVal() == '') {
        $("#login_user_email").css("border", "red dashed");
        $("#loginMessagePanel7").html("ایمیل  نباید تهی باشد.");
        return false;
    } else if (new jj('#login_user_pass').jjVal() == '') {
        $("#login_user_email").css("border", "none");
        $("#login_user_pass").css("border", "red dashed");
        $("#loginMessagePanel7").html("رمز عبور نباید تهی باشد.");
        return false;
    }
    $("#login_user_email").css("border", "none");
    $("#login_user_pass").css("border", "none");
//    param += "&jj=1";
    new jj("do=Access_User.login&jj=1&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
    USER_EMAIL = new jj('#login_user_email').jjVal();

}
;

function validateEmail(email) {
    var emailPathern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//    var re = /^.*$/;
    return emailPathern.test(email);
}
function validatePhone(mobile) {
    var regx = /^(\+98|0)[0-9]{10}/;
//    var regx = /^(09|9)[0-9]{9}$/;
    return regx.test(mobile);
}
function validateweblog(weblog) {
    var regy = /^w{3}\.[a-z]+\.?[a-z]{2,3}(|\.[a-z]{2,3})$/;
    return regy.test(weblog);
}
function validateshomareshenasname(shomareshenasname) {
    var regx = /^[0-9]{1,10}$/;
    return regx.test(shomareshenasname);
}
function validatepersion(string) {
    var regx = /^([\u0600-\u06FF].{1,300}\s*)$/;
    return regx.test(string);
}

function validateenglish(string) {
    var regx = /^[a-z ,.'-]+$/i;
    return regx.test(string);
}
function validatedigit(digit) {
    var regx = /^\d*$/;
//    var regx = /^[0-9]$/;  
    return regx.test(digit);
}
function validatePass(lastname) {
//    var regx = (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/);
    var regx = (/^[0-9a-zA-Z]{4,}$/);//رمز به صورت یک کاراکتر کوچک سپس حداقل 4 رقم
    return regx.test(lastname);
}
//function validatePass(lastname) {
////    var regx = (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/);
//var regx = (/^[0-9a-zA-Z]{4,}$/);//رمز به صورت یک کاراکتر کوچک سپس حداقل 4 رقم
//    return regx.test(lastname);
//}

function validatecodemeli(codemeli) {
    var regx = /^[0-9]{10}$/;
    return regx.test(codemeli);
}
////////////کد پستی 
function validatepostalcode(postalcode) {
    var regx = /^[0-9]{10}$/;
    return regx.test(postalcode);
}

//////////////////// برای نمایش تابع رجیستر وپنهان کردن فرم وروداست ShowRegistForm 
/////////////تابه رجیستر جدید29/6
function registInSite() {
//     alert("bbb");
    var flag = true;
    ///name (E)
    var name = $("#user_name").val();
    if (validatepersion(name) && new jj('#user_name').jjVal() !== "") {
        $('#user_name').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel1").html('');

//        validateflag = true;
    } else {
        $("#user_name").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel1").html('');
        $("#errorRegistMessagePanel1").append('لطفا نام خود را وارد کنید');
        flag = false;
    }


//    ///TAKHASUS(E)
    var pass = $("#user_pass2").val();
    if (validatePass(pass) && new jj('#user_pass2').jjVal() != "") {
        $('#user_pass').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel2").html('');

//        validateflag = true;
    } else {
        $("#user_pass2").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel2").html('');
        $("#errorRegistMessagePanel2").append('برای رمز عبور لطفاحداقل 8کاراکتر وارد نمایید.');
        flag = false;
    }




    ///////////////////////pass2
    if (new jj('#user_pass_2').jjVal() != new jj('#user_pass2').jjVal()) {
        $('#user_pass_2').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel3").html('');
        $("#errorRegistMessagePanel3").append('لطفا تکرار پسورد خود راوارد کنید');
        flag = false;
    } else {
        $('#user_pass_2').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel3").html('');
    }
    var email = $("#user_email2").val();
    if (validateEmail(email) && new jj('#user_email2').jjVal() !== "") {
        $('#user_email2').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
//        validateflag = true;
        $("#errorRegistMessagePanel4").html('');
    } else {
        $("#user_email2").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel4").html('');
        $("#errorRegistMessagePanel4").append('لطفا ایمیل خود راوارد کنید');
        flag = false;
    }




    var family = $("#user_family").val();

    if (validatepersion(family) && new jj('#user_family').jjVal() !== "") {
        $('#user_family').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel5").html('');
//        validateflag = true;
    } else {
        $("#user_family").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel5").html('');
        $("#errorRegistMessagePanel5").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
        flag = false;
    }

//    if (new jj('#user_address').jjVal() == '') {
//        $('#user_address').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
//        $("#errorRegistMessagePanel13").html('');
//        $("#errorRegistMessagePanel13").append('لطفا آدرس خود را  وارد کنید');
//        flag = false;
//    } else {
//        $('#user_address').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
//        $("#errorRegistMessagePanel13").html('');
//    }


    if (new jj('#user_birthdate').jjVal() == '') {
        $('#user_birthdate').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel6").html('');
        $("#errorRegistMessagePanel6").append('لطفاتاریخ تولد خود را  وارد کنید');
        flag = false;
    } else {
        $('#user_birthdate').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel6").html('');
    }



    if (!flag) {
        return;
    }
    new jj("do=Access_User.registUser&jj=1&panel=" + setting_login_exit_panel.replace(".", "") + "&" + new jj("#registForm").jjSerial()).jjAjax2(false);
//    $("#registerBtn").hide();

//    jj("do=Access_User.registUser&" + new jj("#registForm").jjSerial()).jjAjax2(false);
}
/////ثبت نام کاربران سایت29/6 
function registInSiteUser() {
//    alert("registInSiteUser");

    var name = $("#user_name_register").val();
    if (validatepersion(name) && new jj('#user_name_register').jjVal() !== "") {
        $('#user_name_register').css("border", "1px solid #e5e5e5");
        $("#registMessagePanel").html('');

//        validateflag = true;
    } else {
        $("#user_name_register").css("border", "1px solid  red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفا نام خود را وارد کنید');
//    flag = false;
        return false;
    }
    var family = $("#user_family_register").val();
    if (validatepersion(family) && new jj('#user_family_register').jjVal() !== "") {
        $('#user_family_register').css("border", "1px solid #e5e5e5");
        $("#registMessagePanel").html('');
//        validateflag = true;
    } else {
        $("#user_family_register").css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
//    flag = false;
        return false;
    }
    var email = $("#user_email_register").val();
    if (validateEmail(email) && new jj('#user_email_register').jjVal() !== "") {
        $('#user_email_register').css("border", "1px solid #e5e5e5");
        $("#registMessagePanel").html('');
    } else {
        $("#user_email_register").css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفا ایمیل خود راوارد کنید');
//    flag = false;
        return false;
    }
    var mobile = $("#user_mobile_register").val();
    if (validatePhone(mobile) && new jj('#user_mobile1').jjVal() !== "") {
        $('#user_mobile_register').css("border", "1px solid #e5e5e5");
        $("#registMessagePanel").html('');
    } else {
        $("#user_mobile_register").css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفا شماره موبایل  خود راوارد کنید');
//    flag = false;
        return false;
    }
//
    var pass = $("#user_pass_register").val();
    if (validatePass(pass) && new jj('#user_pass_register').jjVal() != "") {
//         if(new jj('#user_pass').jjVal() !=""){
        $('#user_pass_register').css("border", "1px solid #e5e5e5");
        $("#registMessagePanel").html('');

//        validateflag = true;
    } else {
        $("#user_pass_register").css("border", "1px solid red");
//    $("#registMessagePanel").html('');
        $("#registMessagePanel").append('با حروف کوچک انگلیسی و بعد با حروف بزرگ شروع کنید و حداقل 8 کاراکتر');
//    flag = false;
        return false;
    }
//  ///////////////////////pass2
    if (new jj('#user_pass_2').jjVal() == "" || new jj('#user_pass_2').jjVal() != new jj('#user_pass_register').jjVal()) {
        $('#user_pass_2').css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفا تکرار پسورد خود راوارد کنید');

        return false;
    } else {
        $('#user_pass_2').css("border", "1px solid #e5e5e5");

        $("#registMessagePanel").html('');
    }
//
    if (new jj('#user_password_hint').jjVal() == '') {
        $('#user_password_hint').css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفایاد آور خود را  وارد کنید');

        return false;
    } else {
        $('#user_password_hint').css("border", "1px solid #e5e5e5");

        $("#registMessagePanel").html('');

    }
    $(".jjLoginExitPanel").val();


    new jj("do=Access_User.registUserSite&panel=" + setting_login_exit_panel.replace(".", "") + "&" + new jj("#registForm").jjSerial()).jjAjax2(false);

}




function rightMenu(id) {
    toggleList();

}















function ShowRegistForm() {
    $("#loginForm").hide();
    $("#registForm").show();

}
////////////////////////backToLogin برای نمایش صفحه ورود وپنهان کردن صفحه ثبت نام
function backToLogin() {
    $("#loginForm").show();
    $("#registForm").hide();
}
/////////////////
function registInSitePardakht() {
    var flag = true;


    var pass = $("#user_pass_pardakht2").val();
    if (validatePass(pass) && new jj('#user_pass_pardakht2').jjVal() != "") {
        $('#user_pass_pardakht2').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel5").html('');

//        validateflag = true;
    } else {
        $("#user_pass_pardakht2").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel5").html('');
        $("#errorRegistMessagePanel5").append('برای رمز عبور لطفاحداقل 8کاراکتر وارد نمایید.');
        flag = false;
    }
    if (new jj('#user_pass_2_pardakht').jjVal() != new jj('#user_pass_pardakht2').jjVal()) {
        $('#user_pass_2_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel6").html('');
        $("#errorRegistMessagePanel6").append('لطفا تکرار پسورد خود راوارد کنید');
        flag = false;
    } else {
        $('#user_pass_2_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel6").html('');
    }
    var email = $("#user_email_pardakht2").val();
    if (validateEmail(email) && new jj('#user_email_pardakht2').jjVal() !== "") {
        $('#user_email_pardakht2').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
//        validateflag = true;
        $("#errorRegistMessagePanel7").html('');
    } else {
        $("#user_email_pardakht2").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel7").html('');
        $("#errorRegistMessagePanel7").append('لطفا ایمیل خود راوارد کنید');
        flag = false;
    }


    var name = $("#user_name_pardakht").val();

    if (validatepersion(name) && new jj('#user_name_pardakht').jjVal() != "") {
        $('#user_name_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel9").html('');
//        validateflag = true;
    } else {
        $("#user_name_pardakht").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel9").html('');
        $("#errorRegistMessagePanel9").append('لطفا نام خودبه فارسی راوارد کنید');
        flag = false;
    }
////NAME(F)

    ////FAMILY(F)

    var family = $("#user_family_pardakht").val();

    if (validatepersion(family) && new jj('#user_family_pardakht').jjVal() !== "") {
        $('#user_family_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel10").html('');
//        validateflag = true;
    } else {
        $("#user_family_pardakht").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel10").html('');
        $("#errorRegistMessagePanel10").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
        flag = false;
    }

    if (new jj('#user_birthdate_pardakht').jjVal() == '') {
        $('#user_birthdate_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel20").html('');
        $("#errorRegistMessagePanel20").append('لطفاتاریخ تولد خود را  وارد کنید');
        flag = false;
    } else {
        $('#user_birthdate_pardakht').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel20").html('');
    }

//
//


    if (!flag) {
        return;
    }
// param += "&EmailPardakhtha=" + jj("#user_email_pardakht2").jjVal();
//    email = new jj('#user_email_pardakht2').jjVal();
    new jj("do=Access_User.registUserPardakht&panel=" + new jj("#registForm").jjSerial() + "&EmailPardakhtha=" + jj("#user_email_pardakht2").jjVal()).jjAjax2(false);
//    $("#registerBtn").hide();

//    jj("do=Access_User.registUser&" + new jj("#registForm").jjSerial()).jjAjax2(false);
}
var cmsChat = {// برای اینکه از سمت سرور این تابع فراخوانی مشود و خطا ندهد
    refreshChat: function () {
    }
}

function signOut() {
    USER_NAME = "";
    USER_FAMILY = "";
    USER_EMAIL = "";
    USER_PASS = "";
    new jj("do=Access_User.signOut").jjAjax2(true);
    $('.register_url').show();
    $('#userNameAfterLogin').html("");
//    $("jjLoginExitPanel").html('ورود / ثبت نام');
//    window.location = 'index_MySite.html';
    $("countPayamhaDidenashode").html('');
//reportStatus.();
//payamhayeman
//window.location = 'Server2?do=Category_Product.getSelectOptions&panel=swSelectOptions&id=0&jj=1';
//    showSabtenam();
//     $("#loginForm").show();
}
//function Exit(){
//    
//      $("#darkhast").hide();  
//      $("#loginForm").show();  
//      $("#recover").hide();  
//      $("#payamhayeDaryafti").hide();  
//      $("#payamhayeman").hide();  
//      $("#payamhayeErsali").hide();  
//      $("#payamHayeManTbl").hide();  
//    
//} 
/**
 * ایت تابع برای زمانی که کاربر لاگین کرده و زمانی که صفحه را رفرش می کند نام کاربری انرا نشان دهد 
 * @returns {undefined}
 */

function loginRefresh() {
//  new jj("do=Access_User.loginUser&panel=" + setting_login_exit_panel.replace("#", "")).jjAjax2(false);
    new jj("do=Access_User.loginMstid&panel=userNameAfterLogin").jjAjax2(false);
}
function swGetNewsCategory() {
    new jj("do=News.getList&panel=sw&id=0&jj=1").jjAjax2(true);//id=0=> top news(slider + priority 2)
}
function swGetForumCategory() {
    new jj("do=Category_Forum.getList&panel=sw").jjAjax2(true);
}
function swVoteToPoll(pollId, whichOneRecord) {
    if (USER_NAME == '') {
        sw('$login');
        return false;
    } else {
        new jj("do=Poll.voteToPoll&pollId=" + pollId + "&whichRecord=" + whichOneRecord).jjAjax2(true);
    }
}
function swGetGallery(galleryId) {
    var tempid = (galleryId) ? galleryId : "0";
    new jj("do=Pic.getGallery&panel=sw&id=" + tempid).jjAjax2(true);
    swRightClear();
}

//به دلیل اینکه با فانکش دیگر هم نام است کامنت کردم<>.<>
//function swGetProducts(productId) {
//
//    var tempid = (productId) ? productId : "0";
//    new jj("do=Product.getList&panel=swProuduct&id=" + tempid + "&jj=1&title=swTitle").jjAjax2(true);
//    swOptionsMenu("swSelectOptions");
//
//}
/**
 * 
 * @param {type} productId
 * @returns {undefined}
 * برای در آوردن لیست اردوها 
 * در سایت
 */
function swGetUrdU(productId) {

    var tempid = (productId) ? productId : "0";
    new jj("do=Product.getListUrduAll&panel=sw&id=" + tempid + "&jj=1&title=swTitle").jjAjax2(true);
    swOptionsMenu("swSelectOptions");

}
/**swGetProducts
 * این تابع برای نمایش محتوای داخل منو ها
 * یعنی مثلا گروه علمی چه محتوا هایی داخلش هست یاچه زیر گروه هایی دارد
 * @param {type} contentId
 * @returns {undefined}
 */
function swGetContent(contentId) {
    var tempid = (contentId) ? contentId : "0";
    new jj("do=Content.getListContent&panel=swGroups&id=" + tempid + "").jjAjax2(true);

}
/**این تابع برای سلکت تگ ها در صفحه اصلی سایت ونمایش محتوای تگ نوشته شده
 * 
 * @param {type} contentId
 * @returns {undefined}
 */
/**
 * این تابع برای در آوردن محتوای داخل تگ هاست
 * مثلا اگر تگ با عنوان علمی داریم باید  در بیاوریم که تگ علمی  شامل کدام محتوا ها هست
 * که در سمت راست زیر منوی تگ ها نمایش داده می شود
 * @param {type} contentId
 * @returns {undefined}
 */
function getContentTages(contentId) {
    var tempid = (contentId) ? contentId : "0";
    new jj("do=Content.getContentTages&panel=swContentTages&id=" + tempid + "").jjAjax2(true);


}
/**
 * این تابع برای قسمت گروه های سایت یاهمان کتگوری محتواست
 * این تابع برای پنهان کردن زیر منوهای کتگوری کانتک نوشته شده
 * @param {type} id
 * @returns {undefined}
 */
function togleList(id) {
    if ($("#HirechiveDiv" + id + " li").hasClass("fa-plus")) {
        $("#HirechiveDiv" + id + " li").attr("class", "fa fa-minus");
    } else if ($("#HirechiveDiv" + id + " li").hasClass("fa-minus")) {
        $("#HirechiveDiv" + id + " li").attr("class", "fa fa-plus");
    }
    if ($("#HirechiveListDiv" + id).hasClass("closedList")) {

        $("#HirechiveListDiv" + id).hide();
        $("#HirechiveListDiv" + id).addClass("openedList");
    } else if ($("#HirechiveListDiv" + id).hasClass("openedList")) {
        $("#HirechiveListDiv" + id).hide();
    }

}
function getOneproduct(newsId) {
//    if (jj(newsId).jjIsDigit()) {
    new jj("do=Product.getOneProduct&id=" + newsId.toString() + "&panel=sw&jj=1&title=swTitle").jjAjax2(true);
//         $("#commentForm").load("formCms/comment.html");
    //        $('#sliderPanel').hide();
    //        $('#bodyPanel').show();
    //        $('#sw').show();
    //        swTab(3);

//    }
}
;
function swRightProductMenu() {
//    var panel = (panel) ? panel : "swRight";
    $("#sidebarright").css("display", "block");
    $("#sidebarright").show();

//    new jj("do=Category_Product.getList&panel='#swRight'&id=0&jj=1").jjAjax2();
}
/**
 * این تابع برای ورود به سایت اصلی نوشته شده است
 * @returns {Boolean}
 */
function signInSite() {

    if (new jj('#user_email').jjVal() == '') {
        $("#user_email").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    } else if (new jj('#user_pass').jjVal() == '') {
        $("#user_email").css("border", "none");
        $("#user_pass").css("border", "red dashed");
        $("#loginMessagePanel").html("رمز عبور نباید تهی باشد.");
        return false;
    } else {
        $("#user_email").css("border", "none");
        $("#user_pass").css("border", "none");
        var param = "";
        param += "do=Access_User.loginUserInSite&panel=" + setting_login_exit_panel.replace(".", "");
        param += "&" + new jj("#loginForm").jjSerial();
        new jj(param).jjAjax2(false);
    }
}
function signInSite1() {

    if (new jj('#user_email').jjVal() == '') {
        $("#user_email").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    } else if (new jj('#user_pass').jjVal() == '') {
        $("#user_email").css("border", "none");
        $("#user_pass").css("border", "red dashed");
        $("#loginMessagePanel").html("رمز عبور نباید تهی باشد.");
        return false;
    } else {
        $("#user_email").css("border", "none");
        $("#user_pass").css("border", "none");
        var param = "";
        param += "do=Access_User.loginUserInSite&panel=" + setting_login_exit_panel.replace(".", "");
        param += "&" + new jj("#loginForm").jjSerial();
        new jj(param).jjAjax2(false);
    }
}
/**
 * این تابع برای دکمه ورود در صفحه اول سایت نوشته شده است
 * @returns {Boolean}
 */
function signInHome() {
    $("#emailInHome").css("border", "none");
    $("#passInHome").css("border", "none");
    var param = "";
    param += "do=Access_User.loginUserInHome&panel=" + setting_login_exit_panel.replace("#", "");
    param += "&StudentId=" + $("#emailInHome").val();
    param += "&pass=" + $("#passInHome").val();
//    var test = window.location.href;
//    var url = new URL(test);
//    var c = url.searchParams.get("url");
//    param += "&url=" + c;
    new jj(param).jjAjax2(false);
    $("#swLoginForm").hide();
    $("#sw").show();

    $("html, body").animate({scrollTop: 0}, "slow");
    return false;
}
function regist() {

    if ($('#user_name').val() === ("")) {
        $("#user_name").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_family').val() === ("")) {
        $("#user_family").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_codeMeli').val() === ("")) {
        $("#user_codeMeli").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_mobile').val() === ("")) {
        $("#user_mobile").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_emailReg').val() === ("")) {
        $("#user_emailReg").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_studentNumber').val() === ("")) {
        $("#user_studentNumber").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_pass').val() === ("")) {
        $("#user_pass").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }
    if ($('#user_passHint').val() === ("")) {
        $("#user_passHint").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    }

    $("#user_pass").css("border", "none");
    $("#user_studentNumber").css("border", "none");
    $("#user_emailReg").css("border", "none");
    $("#user_mobile").css("border", "none");
    $("#user_codeMeli").css("border", "none");
    $("#user_family").css("border", "none");
    $("#user_name").css("border", "none");
    $("#user_passHint").css("border", "none");
    var param = "";
    param += "do=Access_User.registUserInSite&panel=sw";
    param += "&" + new jj("#form_register").jjSerial();
    new jj(param).jjAjax2(false);
    setTimeout(function () {
        var script = "index.jsp";
        window.location.href = script;
    }, 1000);
    $("html, body").animate({scrollTop: 0}, "slow");
//    return false;
}

/**این تابع برای ثبت نام در سایت اصلی سامانه فرهنگی نوشته شده
 * این تابع برای ثبت نام در قسمت ورود به سامانه نوشته شده
 * 
 * @returns {undefined}
 */
function SignUpInSite() {
    var flag = true;
    var pass = $("#passRegist").val();
    if (validatePass(pass) && new jj('#passRegist').jjVal() != "") {
        $('#passRegist').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel1").html('');

    } else {
        $("#passRegist").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel1").html('');
        $("#errorRegistMessagePanel1").append('برای رمز عبور لطفاحداقل 8کاراکتر وارد نمایید.');
        flag = false;
    }
    if (new jj('#repeatPass').jjVal() != new jj('#passRegist').jjVal()) {
        $('#repeatPass').css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel2").html('');
        $("#errorRegistMessagePanel2").append('لطفا تکرار پسورد خود راوارد کنید');
        flag = false;
    } else {
        $('#repeatPass').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel2").html('');
    }
    var email = $("#emailRegist").val();
    if (validateEmail(email) && new jj('#emailRegist').jjVal() !== "") {
        $('#emailRegist').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel3").html('');
    } else {
        $("#emailRegist").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel3").html('');
        $("#errorRegistMessagePanel3").append('لطفا ایمیل خود راوارد کنید');
        flag = false;
    }
    ;
    var mobile = $("#mobile").val();
    if (validatePhone(mobile) && new jj('#mobile').jjVal() !== "") {
        $('#mobile').css("box-shadow", "2px 0px 10px 2px rgba(1,50, 60, 0.8)");
        $("#errorRegistMessagePanel4").html('');
    } else {
        $("#mobile").css("box-shadow", "2px 0px 10px 2px rgba(100, 10, 20,1)");
        $("#errorRegistMessagePanel4").html('');
        $("#errorRegistMessagePanel4").append('لطفا موبایل خود راوارد کنید');
        flag = false;
    }
    ;
    if (!flag) {
        return;
    }

    $("#mobile").css("box-shadow", "none");
    $("#emailRegist").css("box-shadow", "none");
    $("#repeatPass").css("box-shadow", "none");
    $("#passRegist").css("box-shadow", "none");
    var param = "";
    param += "do=Access_User.registUserInSite&panel=" + setting_login_exit_panel.replace("#", "");
    param += "&" + new jj("#sign-out").jjSerial();
    new jj(param).jjAjax2(false);
}
/**فراموشی رمز عبورو ارسال ایمیل برای یادآوری
 * 
 * @returns {undefined}
 */
function  sendEmailForgetPass() {
    var param = "";
    param += "do=Access_User.sendEmailForgetPass";
    param += "&email=" + $('#email').val();
    new jj(param).jjAjax2(false);
    $("#swLoginForm").hide();
    $("#sw").show();
    $("html, body").animate({scrollTop: 0}, "slow");
    return false;
}
/**این تابع 
 * برای نمایش فرم لاگین
 * هنگام کلیک بروی منوی ورود به سامانه
 * و همچینین کلیک بر روی دکمه ورود
 * فرم مربوط به ورود به سامانه نمایش داده می شود
 * @returns {undefined}
 */
function showformSignIn() {
    $("#sign-in").show();
    $("#sign-out").hide();
    $("#sendEmail").hide();
    $("#back").hide();
    $("#password").show();
    $("#login").show();
    $("#forgetPass").show();

}

/**با انتخاب ورود به سامانه
 * فرم ورود به سامانه نمایشداده می شود که با کلیک برروی ثبت نام فرم مربوط به ثبت نام نمایش داده می شود
 * این تابع برای نمایش فرم ثبت نام نوشته شده
 * 
 * @returns {undefined}
 */
function showformSignOut() {

    $("#sign-in").hide();
    $("#sign-out").show();

}
/**با انتخاب ورود به سامانه
 * این تابع برای فراموشی رمز عبور در قسمت ورود بعه سامانه نوشته شده 
 * @returns {undefined}در قسمت لاگین فراموشی رمز عبور
 */
function showFormForgetPass() {

    $("#password").hide();
    $("#login").hide();
    $("#forgetPass").hide();
    $("#sendEmail").show();
    $("#back").show();

}
/**
 * در صفحه اول سایت
 * این تابع برای فراموشی پسورد نوشته شده
 * 
 * @returns {undefined}
 */
function forgetPassInHome() {


    $("#form_login").hide();
    $("#form_forget").show();
//    $("#backInHome").show();

}
function registInHome() {


    $("#form_login").hide();
    $("#form_register").show();
//    $("#backInHome").show();

}
/**این تابع 
 * برای نمایش فرم لاگین
 * برای دکمه بازگشت موجود در صفحه اول سایت نوشته شده است
 * @returns {undefined}
 */
function BackInHome() {
    $("#form_login").show();
    $("#form_forget").hide();
}
function BackInHomeLogin() {
    $("#form_login").show();
    $("#form_register").hide();
}
/**
 * این تابع برای نمایش زیر منوهای سایت  هنکام ورد به سایت در قسمت گروه ها
 * یعنی برای درآوردن زیر منو های کتگوری کانتنت نوشته شده
 * یا همان getHirechiveDiv
 * @param {type} id
 * @returns {undefined}
 */
function swLeftContentMenu(panel) {
//    if ($("#swLeft").html() == "") {
    var panel = (panel) ? panel : "swLeft";
    new jj("do=Category_Content.getHierarchyDiv&panel=" + panel + "&id=0").jjAjax2();
}
//}
//============ BY RASHIDI ========>
function swOptionsMenu(panel) {
    var panel = (panel) ? panel : "swSelectOptions";
    new jj("do=Category_Product.getSelectOptions&panel=" + panel + "&id=0&jj=1").jjAjax2();
}
function swOptionsMenuContent(panel) {
    var panel = (panel) ? panel : "swSelectOptionsContent";
    new jj("do=Category_Content.getSelectOptions&panel=" + panel + "&id=0").jjAjax2();
}
function getProductMenu(panel) {
    var panel = (panel) ? panel : "ulMenu";
    new jj("do=Category_Product.getMenu&panel=" + panel + "&id=0&jj=1").jjAjax2();
}
//<============ BY RASHIDI ========
function swRightNewsMenu(panel) {
    var panel = (panel) ? panel : "swRight";
    new jj("do=Category_News.getHierarchyDiv&panel=" + panel + "&id=0&jj=1").jjAjax2(true);
}

function picDialog(address, title) {
    //    new jj(address).jjDialog(true,' ',10,750);
    //    new jj("<img  src="+address+" style='width:98%'/>").jjDialog(true,title,window.screen.height*90/100,"98%");
    //    alert(title);
    new jj("<img  src=" + address + " style='width:100%'/>").jjDialog(true, title, 650, 750);
}
function swGetNews(newsCategoryId) {
    var catId = (newsCategoryId) ? newsCategoryId : "0";
    new jj("do=News.getList&id=" + catId + "&panel=sw&jj=1&title=swTitle").jjAjax2(true);
}
function newsDisLike(newsId) {
    var temp = $("#swTopNewsDiv").html();
    temp = temp.replace("onclick=\"newsLike(" + newsId + ");\"", "");
    temp = temp.replace("onclick=\"newsDisLike(" + newsId + ");\"", "");
    $("#swTopNewsDiv").html(temp);
    new jj("do=News.newsDisLike&id=" + newsId).jjAjax2(true);
    new jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
}
;
function newsLike(newsId) {
    var temp = $("#swTopNewsDiv").html();
    temp = temp.replace("onclick=\"newsLike(" + newsId + ");\"", "");
    temp = temp.replace("onclick=\"newsDisLike(" + newsId + ");\"", "");
    $("#swTopNewsDiv").html(temp);
    new jj("do=News.newsLike&id=" + newsId).jjAjax2(true);
    new jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
}
;

//By Md
function productDisLike(productId) {
    new jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
    var temp = $("#swTopproductDiv").html();
    temp = temp.replace("onclick=\"productDisLike(" + productId + ");\"", "");
    temp = temp.replace("onclick=\"productLike(" + productId + ");\"", "");
    $("#swTopproductDiv").html(temp);
    new jj("do=Product.productDisLike&id=" + productId).jjAjax2(true);
}
;

//By Md
function productLike(productId) {
    jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
    var temp = $("#swTopproductDiv").html();
    temp = temp.replace("onclick=\"productLike(" + productId + ");\"", "");
    temp = temp.replace("onclick=\"productDisLike(" + productId + ");\"", "");
    $("#swTopproductDiv").html(temp);
    new jj("do=Product.productLike&id=" + productId).jjAjax2(true);
}
;
//============ BY RASHIDI ========>
function calculatePrice(prId) {//با تغییر تعداد هر کالای موجود در سبد خرید مجموع قیمت آن کالا تغییر می کند
    var unitPrice = $("#prRowTr" + prId + " .unitPrice").html();
    var num = $("#prRowTr" + prId + " .inputNum").val();
    $("#prRowTr" + prId + " .sumPrice").html(parseInt(unitPrice) * num);
    refreshSumPrice();
}
function refreshSumPrice() {// آپدیت شدن قیمت کل پرداختی

    var sum = 0;
    $(".sumPrice").each(function () {
        sum += parseInt($(this).html());
        $("#finalPrice").html(sum);
        $("#account_factor_sum").val(sum);
    });

//   
}

function addToShoppingCart(productId) {//آی دی کالای انتخابی را به کوکی اضافه می کند.
//    alert("یک کالا انتخاب شده");
//
//    new jj('productId=' + productId).jjAjaxCookie();//با سرولته مربوط به کوکی کار میکنه
    if (new jj('productsId').jjCookieGet().replace("%2C", ",").indexOf(productId) < 0) {//اگر این کالا قبلا انتخاب نشده باشد
        if (new jj('productsId').jjCookieGet() !== "") {//قبلا کوکی ست شده است
            new jj('productsId').jjCookieUpdate(productId + ',');
            new jj('productNums').jjCookieUpdate(1);
            $("#productNums").html(new jj('productNums').jjCookieGet());
            $("#productNumsCart").html(new jj('productNums').jjCookieGet());
//            $("#product").html(new jj('product').jjCookieGet());
//            $('#myToast').toast({delay: 3000});
//            $('#myToast').toast('show');
            new jj('دوره به صورتحساب شما اضافه شد.').jjModal("پیام سامانه");
//        new jj('productId=' + productId).jjAjaxCookie();//تابعی که درخواست رو به کوکی سرولت میفرسته.
        } else {//تا حالا کوکی ست نشده
            new jj('productsId').jjCookieSave(productId + ',');
            new jj('productNums').jjCookieSave(1);
            $("#productNums").html(1);
            $("#productNumsCart").html(1);
            new jj('دوره به صورتحساب شما اضافه شد.').jjModal("پیام سامانه");

//            showShoppingCart();
        }
    } else {
        new jj('این دوره پیش از این انتخاب شده است.').jjModal("پیام سامانه");

    }
    $("#shoppingCart").html(new jj('productNums').jjCookieGet());
}
;

function getDetailsProductsSite(id, panel) {
    $('#sidebarright').hide();
//    $('.news').hide();
//    $('#swfooter').hide();
//    $('.namd').hide();
//    $('.postscript-fifth').hide();

    var param = "";
    param += "do=Product.getOneProductSite";
    param += "&id=" + id;
    if (panel === undefined) {
        param += "&panel=sw";
    } else {
        param += "&panel=" + panel;
    }
    param += "&panel=sw";
    param += "&jj=1";

    new jj(param).jjAjax2(false);
}



function showShoppingCart() {//تابعی که سبد خرید را به صورت جدول می سازد فراخوانی میکند
    if (new jj('productsId').jjCookieGet() !== "") {
//        $("#sidebarright").css("displye","none");
//        swRightClear();
        var cookie = new jj('productsId').jjCookieGet();
        console.log("//" + cookie);
        new jj("do=Product.buildShoppingCart&productsId=" + cookie + "&panel=sw").jjAjax2(true);
        $("#divDargah").hide();
    } else {//تا حالا کوکی ست نشده
//        alert('کالایی انتخاب نشده است.')
        new jj('کالایی انتخاب نشده است').jjModal("پیام");
        $("#paymentTb").hide();
    }

}
;
function deletePrFromCart(productId) {
//    new jj("آیا از حذف این محصول اطمینان دارید؟").jjDialog_YesNo('afterDeletePrFromCart(' + productId + ');\n', true, "");
    new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف', 'afterDeletePrFromCart(' + productId + ');\n');
}
function afterDeletePrFromCart(productId) {//آیتمی را از سبد خرید حذف میکند.
    new jj('productsId').jjCookieReplace(productId + ",", "");
    new jj('productNums').jjCookieUpdate(-1);
    $("#productNums").html(new jj('productNums').jjCookieGet());
    $("#productNumsCart").html(new jj('productNums').jjCookieGet());
    $("#prRowTr" + productId).remove();
//    $("#product" + productId).remove();
//در صورت خالی شدن سبد خرید قسمت پرداختی ها هم نمایش داده نشود .
    var numberProduct = $("#productNums").html();
    if (numberProduct == 0) {
        $("#paymentTb").remove();
        $("#cartShop").css("display", "none");
        $("#form-company").css("display", "none");
        $("#cartEmpty").css("display", "block");

    }



    refreshSumPrice();


}
;
function prePaymentPardakht() {

//    if (!$(".bankDiv label input").is(':checked')) {
//        $(".bankDiv").css("border", "1px red dashed");
//    } else if (new jj('#user_address').jjVal() == '') {
//        $("#user_address").css("border", "1px red dashed");
//        $(".bankDiv").css("border", "0px gray dashed");
//    } else {
//        $("#user_address").css("border", "1px gray solid");
//        $(".bankDiv").css("border", "0px gray dashed");
//        var param = new jj("#sw").jjSerial();
//        new jj("do=Factor.insertFactor&" + param).jjAjax2(true);//ثبت فاکتور در دیتابیس
    new jj("do=Factor.insertFactor&idProduct=" + $("#product_factor_item_productId").val()).jjAjax2(true);//ثبت فاکتور در دیتابیس
}
function prePayment() {
    alert("1111111");
//    if (!$(".bankDiv label input").is(':checked')) {
//        $(".bankDiv").css("border", "1px red dashed");
//    } else if (new jj('#user_address').jjVal() == '') {
//        $("#user_address").css("border", "1px red dashed");
//        $(".bankDiv").css("border", "0px gray dashed");
//    } else {
//        $("#user_address").css("border", "1px gray solid");
//        $(".bankDiv").css("border", "0px gray dashed");
    var param = new jj("#sw").jjSerial();

//        param += "&do=Factor.insertFactor";
//        nameDargah($('.bankDiv input:radio[class=dargahha]:checked').val());
//        new jj(param).jjAjax2(true);//ثبت فاکتور در دیتابیس
    new jj('do=Factor.insertFactor').jjAjax2(true);
//    }
}

////        if ($('#orderFormDiv').length == 0) {
//        $("body").append("<div id='orderFormDiv'></div>");
//        $("#orderFormDiv").load("order_form.html", null, function () {
//            $('#orderBtn').button().click(function () {
//                alert(1);
//            });
//            $("#orderForm").dialog({
//                autoOpen: false,
//                height: 450,
//                width: 790,
//                modal: true,
//                title: "ثبت سفارش",
//                buttons: {
//                    "لغو": function () {
//                        $(this).dialog("close");
//                    }
//                },
//                close: function () {
//                    $(this).dialog('destroy');
//                }
//            });
//            $("#orderForm").dialog("open");
//            return false;
//        });
////    }
function Comment() {
    if ($('#comment_full_name').val() == '') {
        $('#comment_full_name').css("border", "red dashed");
    } else if ($('#comment_email').val() == '') {
        $('#comment_full_name').css("border", "none"); //if previus time was red and know intered
        $('#comment_email').css("border", "red dashed");
    } else if ($('#comment_text').val() == '') {
        $('#comment_email').css("border", "none"); //if previus time was red and know intered
        $('#comment_text').css("border", "red dashed");
    } else {
        jj("do=Comment.insert&" + new jj("#commentForm").jjSerial()).jjAjax2();
        sw(setting_default_sw);//برای نمایش صفحه اصلی بعد از ارسال فرم
    }
}
;
//<============ BY RASHIDI ========

//====================================mirhaj
function Save() {
    alert(new jj("#enrolment1Form").jjSerial());
    jj("do=Enrolment.insert&" + new jj("#enrolment1Form").jjSerial()).jjAjax2();
}
;
//==================================>mirhaj
function swNews(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.sw&id=" + newsId.toString() + "&panel=sw").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
//============ BY RASHIDI ========>
function swProduct(productId) {
    if (jj(productId).jjIsDigit()) {
//        new jj("do=Product.sw&id=" + productId.toString() + "&panel=swTitle").jjAjax2(true);
        new jj("do=Product.getOneProduct&id=" + productId.toString() + "&panel=sw").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;

//<============ BY RASHIDI ========
/*
 *Only use when div whit "id=swTopNewsDiv" is avalable in DOM( when getList() in serverside has ben caled)
 **/
function getOneNews(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.getOneNews&id=" + newsId.toString() + "&panel=swTopNewsDiv&jj=1&title=swTitle").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
//دریافت مقدار  ایدی هر دسته بندی و نمایش  تمام همان دسته در صفحه ایدی دسته بندی را به صورت داینامیک دریافت میکند  getCategoryId
function getCategoryId(id) {
    window.localStorage.setItem('category', id);
    $("#sidebarright").css("display", "block");
    new jj('do=Product.getProducts&panel=sw&categoryProductId=' + id).jjAjax2();
}
;
function product() {
    new jj('do=Product.getProducts&panel=sw&categoryProductId=1').jjAjax2();
}
;
function resultSearch0() {
    alert("product");
    new jj('do=Product.resultSearch1').jjAjax2();
}
;

function swNews2(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.swPishro&id=" + newsId.toString() + "&panel=sw").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(5);

    }
}

;
function swPic(titleTextOrId) {
    var panelSelector = "sw";
    if (jj(titleTextOrId).jjIsDigit()) {
        new jj("do=Pic.getGallery&id=" + titleTextOrId.toString() + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
//        new jj("do=Pic.select&id=" + titleTextOrId.toString() + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
    } else {
        var text = (titleTextOrId == null) ? "جاوارا" : new jj(titleTextOrId.toString()).jjTrim();
        while (text.indexOf("\n") > -1) {
            text = text.replace("\n", "");
        }
        new jj("do=Pic.sw&text=" + text + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
    }
}
;

function searchAction(text, panelSelector) {
    panelSelector = (panelSelector == null) ? "sw" : panelSelector;
    new jj("do=Content.searchTextInAll&text=" + text + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
}
/**
 * This method don't lets to user write string on TextField <br>
 * @param selector is TextFeildTagID selector; <br/>
 * @param maxLength is int (default: 10) <br/>
 */
function search(searchtxtSelector) {
    if (LANGUAGE == 'fa') {
        jj(searchtxtSelector).jjSetDefaultValueToTxt("جستجو");
    } else {
        jj(searchtxtSelector).jjSetDefaultValueToTxt("Search...");
    }
    var panelSelector = (panelSelector == null) ? "sw" : panelSelector;
    $(searchtxtSelector).keypress(function (event) {
        var keynum;
        if (window.event) { // IE
            keynum = event.keyCode
        } else if (event.which) { // Netscape/Firefox/Opera
            keynum = event.which
        }
        if (keynum == undefined) {
            event.returnValue = true;
            return true;
        } else {
            if (keynum == 13) {
                var text = new jj(searchtxtSelector).jjVal();
                if (text.length > 2) {
                    searchAction(text);
                }
                event.returnValue = true;
                return true;
            }
        }
    })
}

//============ BY RASHIDI ========>
//<============ BY RASHIDI ========
function picSlider(panel, delay) {
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    var param = "";
    param += "do=Pic.getPicSlider";
    param += "&panel=" + panel.replace("#", "");
    param += "&delay=" + delay;
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}
// لیلا برای ایجاد یک اسلایدر جدید این تابع را نوشتم 
function picMySlider(panel, delay) {
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    var param = "";
    param += "do=Pic.getPicSlider";
    param += "&panel=" + panel.replace("#", "");
    param += "&delay=" + delay;
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}

function picSlipprySlider(panel, delay) {
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    var param = "";
    param += "do=Pic.getPicSlipprySlider";
    param += "&panel=" + panel.replace("#", "");
    param += "&delay=" + delay;
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}


function autoLogin(email, pass) {

    $("#login_user_email").val(email);
    $("#login_user_pass").val(pass);
    jj("do=Access_User.loginUserSafheAsli&panel=" + setting_login_exit_panel.replace("#", "") + "&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
}
function profileShakhsi() {
    var param = "";
    param += "do=Access_User.profileShakhsi";

    jj(param).jjAjax2(false);
}


function forget() {
    $("#log_id").hide();
    $("#login_user_pass").hide();
    $("#regested_id").hide();
//    $("#pass").hide();
//        $("#user_pass").hide();
    $("#forgetEmail").hide();
    $("#BazgashtBaSafheWorod").show();
    $("#sendEmail").show();


}
function  sendEmail() {
    var param = "";
    param += "do=Access_User.sendEmail";
    param += "&email1=" + new jj('#login_user_email').jjVal();

    jj(param).jjAjax2(false);
}
/***
 * ارسال مقاله  در صفحه اصلی سایت
 * @returns {undefined}
 */
function sendArticel() {
    var param = "";
    param += "do=Access_User.sendArticel";
    param += "&email=" + new jj('#login_user_email').jjVal();

    new jj(param).jjAjax2(false);


}
/**
 * تغییر عکس در getoneProduct
 * با کلیک بر روی عکس مورد نظر عکس کلیک شده روی آن با عکس اصلی عوض می شود
 * @param {type} className
 * @returns {undefined}
 */
function changeProductPic(className) {
    var param = "";
    var src = "";
    var src1 = "";
    src = $('.mainPic.productPic').attr("src");//در آوردن عکس اصلی
    src1 = $(className).attr("href");//درآوردن عکسی که روی آن کلیک شده اسن
    $('.mainPic.productPic').attr("src", src1);// جایگزنی عکس اصلی با عکسی که روی آن کلیک شده است
    $('.mainPic.productPic').parent().attr("href", src1);// جایگزنی عکس اصلی با عکسی که روی آن کلیک شده است پدر اصلی هم باید لینک اش عوصض شود
    $(className).attr("href", src);//جایگزینی عکس کلیک شده با عکس اصلی
    $(className).children("img.productPic").attr("src", src);//جایگزینی عکس فرعی انتخاب شده با عکس اصلی اینجا عکس عوض می شود
    new jj(param).jjAjax2(false);
}
function changeSrc1() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img1").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img1").attr("src") + ")");
}
function changeSrc2() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img2").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img2").attr("src") + ")");
}
function changeSrc3() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img3").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img3").attr("src") + ")");
}
function changeSrc4() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img4").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img4").attr("src") + ")");
}
function changeSrc5() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img5").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img5").attr("src") + ")");
}
function changeSrc6() {
    $("#changeSrcInGetDetailProduct").attr("src", $("#img6").attr("src"));
    $("#figureImg").css("background-image", "url(" + $("#img6").attr("src") + ")");
}
function minprice() {
    new jj('do=Product.minprice').jjAjax2();
}
function sendmassege(value) {
    value = $("#phonNumber").val();
    if ((new jj("#phonNumber").jjVal() == '')) {
        $("#loginMessagePanel").html("شماره موبایل خود را وارد کنید.");
    } else {
        $("#loginMessagePanel").html("");
//    alert(value);
        var param = "";
        param += "do=Access_User.validnumder";
        param += "&" + new jj("#forget").jjSerial();
        new jj(param).jjAjax2(false);
    }

//    new jj('do=sms.sendMessageByApi&receptor='+value).jjAjax2();
}

function slidersite111() {
//    alert(888888);
    new jj('do=Pic.getsidersite&panel=jjslider&jj=1').jjAjax2();
}
//اسکریپ مربوط به تب ثبت نام و لاگین
function loadLoginTab() {
    $(".nav-tabs a").onclick(function () {
        $(this).tab('show');
    });
}
///////////////خالی کردن مقدرا سبد خرید بعد از هر رفرش   
function refreshCart() {
    if (new jj('productNums').jjCookieGet() !== "") {
        $("#productNums").html(new jj('productNums').jjCookieGet());
        $("#productNumsCart").html(new jj('productNums').jjCookieGet());
    } else {
        $("#productNums").html();
        $("#productNumsCart").html();
    }
}
//اسکریپت مربوط به منو و زیر منو تغییر حالت 
function sunmenu() {
    $("#test").mouseover(function () {
        $("#submenu").css("display", "block");
        $("#menu1").css("transform", "rotate(180deg)");
        $("#menu1").css("color", "red");
    });
    $("#submenu").mouseout(function () {
        $("#submenu").css("display", "none");
        $("#menu1").css("transform", "rotate(0deg)");
        $("#menu1").css("color", "#151515");
    });
}
//        <!--نمایش منو در موبایل-->
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}


function closeNav() {
    document.getElementById("mySidenav").style.width = "0"
            ;
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "#f6f6f6";
}
//js for zoom img------------>
//function zoom(e) {
//    var zoomer = e.currentTarget;
//    e.offsetX ? offsetX = e.offsetX : offsetX = e.touches[0].pageX
//    e.offsetY ? offsetY = e.offsetY : offsetX = e.touches[0].pageX
//    x = offsetX / zoomer.offsetWidth * 100
//    y = offsetY / zoomer.offsetHeight * 100
//    zoomer.style.backgroundPosition = x + '% ' + y + '%';
//}
function swGetProducts(productId) {
//    alert(productId);
//    window.localStorage.setItem('category', productId);
//    $('html,body').animate({
//        scrollTop: $(".sw").offset().top},
//    'slow');
//    $(".sw").css("margin-top", 130);
    new jj("do=Category_Product.getProducts&panel=sw_1&categoryProductId=" + productId + "&jj=1&title=swTitle").jjAjax2(true);
//        new jj("do=Pic.getsidersite&panel=jjslider&jj=1").jjAjax2();

}
function sw_1(id) {
    new jj("do=Category_Content.dispatchJsp&id=" + id + "&jj=1&title=swTitle").jjAjax3(true);

}
function swGetContent_canon(contentId) {
    window.localStorage.setItem('category', contentId);
//    $('html,body').animate({
//        scrollTop: $(".sw").offset().top},
//    'slow');
//    $(".sw").css("margin-top", 130);
    new jj("do=Category_Content.getSubCategouriesContent_1&panel=sw&categoryContentId=" + contentId + "&jj=1&title=swTitle").jjAjax2(true);
//        new jj("do=Pic.getsidersite&panel=jjslider&jj=1").jjAjax2();

}
function swGetGhoran_canon(contentId) {
    window.localStorage.setItem('category', contentId);
//        setTimeout(function(){
//    $('html,body').animate({
//        scrollTop: $(".sw").offset().top},
//    'slow');
//    $(".sw").css("margin-top", 130);
//        },2000);

    new jj("do=Category_Content.getSubGhoranContent&panel=sw&categoryContentId=" + contentId + "&jj=1&title=swTitle").jjAjax2(true);
//        new jj("do=Pic.getsidersite&panel=jjslider&jj=1").jjAjax2();

}
function minprice() {
    var category = window.localStorage.getItem("category");
    new jj("do=Product.sortMinPrice&panel=sw&categoryProductId=" + category + "&jj=1&title=swTitle").jjAjax2(true);
//    alert(category);

}
function maxprice() {
    var category = window.localStorage.getItem("category");
    new jj("do=Product.sortMaxPrice&panel=sw&categoryProductId=" + category + "&jj=1&title=swTitle").jjAjax2(true);
//    alert(category);

}
function newProduct() {
    var category = window.localStorage.getItem("category");
    new jj("do=Product.newProduct&panel=sw&categoryProductId=" + category + "&jj=1&title=swTitle").jjAjax2(true);
//    alert(category);

}
function bestsellingProduct() {
    var category = window.localStorage.getItem("category");
    new jj("do=Product.bestsellingProduct&panel=sw&categoryProductId=" + category + "&jj=1&title=swTitle").jjAjax2(true);
//    alert(category);

}
/////نمایش پیش فاکتور
//////در سایت فروشگاهی ابتدا ادرس سخص گیرنده سفارش را از  کاربر میگیرد و رد صورتی که کاربر فاکتور را هم بخواهدادرس شرکت را نیز باید تکمیل کند
function prePaymentProduct() {
    var name = $("#user_name_Receiver").val();
    if (validatepersion(name) && new jj('#user_name_Receiver').jjVal() !== "") {
        $('#user_name_Receiver').css("border", "1px solid #e5e5e5");
        $("#msg").html('');

    } else {
        $("#user_name_Receiver").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خود را به فارسی وارد کنید');
        return false;
    }
    var family = $("#user_family_Receiver").val();
    if (validatepersion(family) && new jj('#user_family_Receiver').jjVal() !== "") {
        $('#user_family_Receiver').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_family_Receiver").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
        return false;
    }
    var mobile = $("#user_phon").val();
    if (validatePhone(mobile) && new jj('#user_phon').jjVal() !== "") {
        $('#user_phon').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_phon").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا شماره موبایل  خود راوارد کنید');
        return false;
    }
    var postalcode = $("#product_factor_zipCode").val();
    if (validatepostalcode(postalcode) && new jj('#product_factor_zipCode').jjVal() !== "") {
        $('#product_factor_zipCode').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#product_factor_zipCode").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('کد پستی شامل ده رقم می باشد');
        return false;
    }
    var address = $("#product_factor_address").val();
    if (validatepersion(address) && new jj('#product_factor_address').jjVal() !== "") {
        $('#product_factor_address').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#product_factor_address").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append(' لطفا آدرس خود را به فارسی وارد کنید.');
        return false;
    }
    var form_company = document.getElementById('form_company');
    var checkbox = document.getElementById('checkbox');
    if (form_company.style['display'] == 'block') {
//      alert(00000);
        ////////     اعتبار سنجی فرم اطلاعات شرکتی به شرط اینکه تیک ارسال فاکتور زده شده باشد
        var companyName = $("#product_factor_companyName").val();
        if (validatepersion(companyName) && new jj('#product_factor_companyName').jjVal() !== "") {
            $('#product_factor_companyName').css("border", "1px solid #e5e5e5");
            $("#msgg").html('');
        } else {
            $("#product_factor_companyName").css("border", "1px solid red");
            $("#msgg").html('');
            $("#msgg").append('لطفا نام شرکت خود را به فارسی وارد کنید');
            return false;
        }
        var economicCode = $("#product_factor_economicCode").val();
        if (validateshomareshenasname(economicCode) && new jj('#product_factor_economicCode').jjVal() !== "") {
            $('#product_factor_economicCode').css("border", "1px solid #e5e5e5");
            $("#msgg").html('');
        } else {
            $("#product_factor_economicCode").css("border", "1px solid red");
            $("#msgg").html('');
            $("#msgg").append('لطفا کد را به صورت عدد وارد کنید.');
            return false;
        }
        var nationalCode = $("#product_factor_nationalCode").val();
        if (validateshomareshenasname(nationalCode) && new jj('#product_factor_nationalCode').jjVal() !== "") {
            $('#product_factor_nationalCode').css("border", "1px solid #e5e5e5");
            $("#msgg").html('');
        } else {
            $("#product_factor_nationalCode").css("border", "1px solid red");
            $("#msgg").html('');
            $("#msgg").append('لطفا کد را به صورت عدد وارد کنید.');
            return false;
        }
        var tell2 = $("#product_factor_tell2").val();
        if (validatePhone(tell2) && new jj('#product_factor_tell2').jjVal() !== "") {
            $('#product_factor_tell2').css("border", "1px solid #e5e5e5");
            $("#msgg").html('');
        } else {
            $("#product_factor_tell2").css("border", "1px solid red");
            $("#msgg").html('');
            $("#msgg").append('لطفا شماره موبایل خود را  صحیح وارد کنید');
            return false;
        }
        var address2 = $("#product_factor_address2").val();
        if (validatepersion(address2) && new jj('#product_factor_address2').jjVal() !== "") {
            $('#product_factor_address2').css("border", "1px solid #e5e5e5");
            $("#msgg").html('');
        } else {
            $("#product_factor_address2").css("border", "1px solid red");
            $("#msgg").html('');
            $("#msgg").append('لطفا ادرس خود را به فارسی وارد کنید');
            return false;
        }

    }
    ////////////////////////////////////////////////////////////////////////////////
    $(".mobileSlider").hide();
    $(".navbar").hide();
    var param = "";
    param += "do=Factor.insertFactorProduct";
    param += "&" + new jj('#sw').jjSerial();
    new jj(param).jjAjax2(false);
}

function payment(factorSum) {//عملیات پرداخت
    if (!$(".bankDiv input").is(':checked')) {
        $(".bankDiv").css("border", "1px red dashed");
    } else if (new jj('#user_address').jjVal() == '') {
        $("#user_address").css("border", "1px red dashed");
        $(".bankDiv").css("border", "0px gray dashed");
    } else {
        $("#user_address").css("border", "1px gray solid");
        $(".bankDiv").css("border", "0px gray dashed");
        var param = new jj("#sw").jjSerial();
        new jj("do=Payment.payment&payment_amount=" + factorSum + "&" + param).jjAjax2(false);
        new jj("لطفا کمی صبر کنید تا به صفحه پرداخت منتقل شوید").jjDialog();
    }
}
///////////////////جستوجو محصول در سایت بر اساس نام محصول
function serchProduct() {
    $('#sidebarright').show();
    var nameProduct = $("#keyword").val();
    if (validatepersion(nameProduct) && new jj('#keyword').jjVal() !== "") {
        new jj('do=Product.serchProduct&panel=sw&keyword=' + nameProduct).jjAjax2(true);
//        alert(nameProduct);
    }
}
function serchProductMobile() {
    var nameProduct = $("#keywordMobile").val();
    if (validatepersion(nameProduct) && new jj('#keywordMobile').jjVal() !== "") {
        new jj('do=Product.serchProduct&panel=sw&keyword=' + nameProduct).jjAjax2(true);
//        alert(nameProduct);
    }
}
///////ویرایش فرم پروفایل کاربر 
function editProfileform() {
    var name = $("#user_name_edit").val();
    if (validatepersion(name) && new jj('#user_name_edit').jjVal() !== "") {
        $('#user_name_edit').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_name_edit").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خود را به فارسی وارد کنید');
        return false;
    }
    var family = $("#user_family_edit").val();
    if (validatepersion(family) && new jj('#user_family_edit').jjVal() !== "") {
        $('#user_family_edit').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_family_edit").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
        return false;
    }

    $(setting_login_exit_panel).val();
    var params = "do=Access_User.editProfile&panel=" + setting_login_exit_panel.replace(".", "") + "&";
    params += new jj("#form_edit").jjSerial();
    new jj(params).jjAjax2(true);
}
/////
function refresh() {
    new jj('do=Product.getProducts&panel=sw&categoryProductId=1').jjAjax2(true);
    $('#sidebarright').show();
}
function myFactor() {
    new jj('do=Factor.showAllMyFactor').jjAjax2(false);
    $(".news").css("display", "none");
    $("#jjslider").css("display", "none");
    $("#swslider1").css("display", "none");
    $("#swfooter").css("display", "none");
    $("#swfooter").css("display", "none");
    $(".namd").css("display", "none");
    $("#scrollUp").css("display", "none");
    $("#footerbuttom").css("display", "none");
}
//pagination function 3 previous next go_to_page
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
function previous() {
    new_page = parseInt($('#current_page').val()) - 1;
    if ($('.active_page').prev('.page_link').length == true) {
        go_to_page(new_page);
    }
}
function next() {
    new_page = parseInt($('#current_page').val()) + 1;
    if ($('.active_page').next('.page_link').length == true) {
        go_to_page(new_page);
    }
}
function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val());
    start_from = page_num * show_per_page;
    end_on = start_from + show_per_page;
    $('#pagingBox').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');
    $('.page_link[longdesc=' + page_num + ']').addClass('active_page').siblings('.active_page').removeClass('active_page');
    $('#current_page').val(page_num);
}
function showUserFactor() {
//    alert(888888888);
    $(".news").css("display", "none");
    $("#sidebarright").css("display", "none");
    $("#jjslider").css("display", "none");
    $("#swslider1").css("display", "none");
    $("#swfooter").css("display", "none");
    $("#swfooter").css("display", "none");
    $("#scrollUp").css("display", "none");
//    $(".namd").css("display", "none"); 
//    $("#footerbuttom").css("display", "none");
    new jj('do=Factor.showAllMyFactor').jjAjax2(false);
}
///نمایش فرم ویرایش پروفایل از سمت cms
function showEditeProfile() {
//    alert("editeProfile");
    $('html, body').animate({scrollTop: $('#sw').offset().top}, 1500);
    sw('فرم ویرایش پروفایل');
    return false;
}
///نمایش فرم تغییر رمز عبور از cms
function  formEditUserPass() {
    $('html, body').animate({scrollTop: $('#sw').offset().top}, 1500);
    sw('فرم ویرایش رمز عبور کاربر');
    return false;
}
////////تغییر رمز عبور کاربران سایت فروشگاهی 
function chengePass() {
    var pass1 = $("#user_pass_old").val();
    if (new jj('#user_pass_old').jjVal() != "") {
        $('#user_pass_old').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_pass_old").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا پسورد فعلی خود را وارد کنید');
        return false;
    }
    var pass = $("#user_pass_edit").val();
    if (validatePass(pass) && new jj('#user_pass_edit').jjVal() != "") {
        $('#user_pass_edit').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_pass_edit").css("border", "1px solid red");
        $("#msg").append('رمز عبور حداقل 4 کاراکتر می باشد');
        return false;
    }
//  ///////////////////////pass2
    if (new jj('#user_pass_2_edit').jjVal() != new jj('#user_pass_edit').jjVal()) {
        $('#user_pass_2_edit').css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا تکرار پسورد خود راوارد کنید');

        return false;
    } else {
        $('#user_pass_2_edit').css("border", "1px solid #e5e5e5");

        $("#msg").html('');
    }
//
    if (new jj('#user_password_hint').jjVal() == '') {
        $('#user_password_hint').css("border", "1px solid red");
        $("#registMessagePanel").html('');
        $("#registMessagePanel").append('لطفایاد آور خود را  وارد کنید');

        return false;
    } else {
        $('#user_password_hint_edit').css("border", "1px solid #e5e5e5");

        $("#msg").html('');

    }
    var params = "do=Access_User.editPassUser&";
    params += new jj("#form_edit_pass").jjSerial();
    new jj(params).jjAjax2(true);
}

//انتخاب فایل از سیستمم
function chooseFile() {
    $("#pic_file").click();
}
///دریافت  نام فایل و قرار دادن در  قسمت ادرس فایل 
function namefile() {
    $("#pic_pic").html($("#pic_file").val().split(/[\\|/]/).pop());
}

function checkUserAddress() {
//    alert(22);
    new jj("do=Access_User.checkUserAddress&panel=sw").jjAjax2(true);
}
///نمایش اطلاعات کاربران سایت فروشگاهی  از پایگاه داده در فرم ویرایش اطلاعات 
function selectProfile(id) {
    new jj('do=Access_User.selectProfile1').jjAjax2(true);
}

///در صورت که تیک زده بشه فرم به کاربران نمایش داده 
//////دریافت ادرس دقیق گیرنده از کاربر   
function loadFormCompany() {
    var checkbox = document.getElementById('checkbox');
    var form_company = document.getElementById('form_company');
    var show_form_company = function () {
        if (checkbox.checked) {
            form_company.style['display'] = 'block';

        } else {
            form_company.style['display'] = 'none';
        }
    };
    checkbox.onclick = show_form_company;

    var param = "";
    param += "do=Factor.show";
    param += "&" + new jj('#form_address').jjSerial();
    param += "&" + new jj('#form_company').jjSerial();
    new jj(param).jjAjax2(false);
}


//ادرس کاربر را به صورت پیشفرض  به عنوان ادرس گیرنده محصول نمایش میدهد
//در قسمت سبد خرید  
function addressItem() {
//    alert('نمایش ادرس ها');
    new jj('do=Access_User.showAddressItems&panel=sw').jjAjax2(true);
}

function edite_address_customer() {
//    alert(0000);
    var param = "";
    param += "do=Factor.editeAddress";
    param += "&" + new jj('#form_address').jjSerial();
    param += "&" + new jj('#form_company').jjSerial();
    new jj(param).jjAjax2(false);
}
function validformadress() {
//    alert("validformadress");
    var name = $("#user_name").val();
    if (validatepersion(name) && new jj('#user_name').jjVal() !== "") {
        $('#user_name').css("border", "1px solid #e5e5e5");
        $("#msg").html('');

    } else {
        $("#user_name").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خود را به فارسی وارد کنید');
        return false;
    }
    var family = $("#user_family").val();
    if (validatepersion(family) && new jj('#user_family').jjVal() !== "") {
        $('#user_family').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_family").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خانوادگی  خودبه فارسی راوارد کنید');
        return false;
    }
    var mobile = $("#user_phon").val();
    if (validatePhone(mobile) && new jj('#user_phon').jjVal() !== "") {
        $('#user_phon').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_phon").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('لطفا شماره موبایل  خود راوارد کنید');
        return false;
    }
    var postalcode = $("#product_factor_zipCode").val();
    if (validatepostalcode(postalcode) && new jj('#product_factor_zipCode').jjVal() !== "") {
        $('#product_factor_zipCode').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#product_factor_zipCode").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append('کد پستی شامل ده رقم می باشد');
        return false;
    }
    var address = $("#product_factor_address").val();
    if (validatepersion(address) && new jj('#product_factor_address').jjVal() !== "") {
        $('#product_factor_address').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#product_factor_address").css("border", "1px solid red");
        $("#msg").html('');
        $("#msg").append(' لطفا آدرس خود را به فارسی وارد کنید.');
        return false;

    }

    $(".mobileSlider").hide();
    $(".navbar").hide();
    var param = "";
    param += "do=Factor.insertFactorProduct";
    param += "&" + new jj('#sw').jjSerial();
    new jj(param).jjAjax2(false);
}

/////////////////اعتبار سنجی فرم استخدامی  کارشناسی  mstid///////////////////////////////
function  validEmplye() {
    var name = $("#employe_info_name").val();
    if (validatepersion(name) && new jj('#employe_info_name').jjVal() !== "") {
        $('#employe_info_name').css("border", "1px solid #e5e5e5");
        $("#msg1").html('');
    } else {
        $("#employe_info_name").css("border", "1px solid  red");
        $("#msg1").html('');
        $("#msg1").append('لطفا نام خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_name').offset().top - 105}, 1500);
        return false;
    }
    var family = $("#employe_info_family").val();
    if (validatepersion(family) && new jj('#employe_info_family').jjVal() !== "") {
        $('#employe_info_family').css("border", "1px solid #e5e5e5");
        $("#msg2").html('');
    } else {
        $("#employe_info_family").css("border", "1px solid  red");
        $("#msg2").html('');
        $("#msg2").append('لطفا نام خانوادگی خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_family').offset().top - 105}, 1500);
        return false;
    }
    var father = $("#employe_info_father").val();
    if (validatepersion(father) && new jj('#employe_info_father').jjVal() !== "") {
        $('#employe_info_father').css("border", "1px solid #e5e5e5");
        $("#msg3").html('');
    } else {
        $("#employe_info_father").css("border", "1px solid  red");
        $("#msg3").html('');
        $("#msg3").append('لطفا نام پدر خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_father').offset().top - 105}, 1500);
        return false;
    }
    var codemeli = $("#employe_info_codemeli").val();
    if (validatecodemeli(codemeli) && new jj('#employe_info_codemeli').jjVal() !== "") {
        $('#employe_info_codemeli').css("border", "1px solid #e5e5e5");
        $("#msg4").html('');
    } else {
        $("#employe_info_codemeli").css("border", "1px solid  red");
        $("#msg4").html('');
        $("#msg4").append('کد ملی 10 رقمی می باشد.');
        $('html, body').animate({scrollTop: $('#employe_info_codemeli').offset().top - 105}, 1500);
        return false;
    }
    ////شماره شناسنامه
    var shomareshenasname = $("#employe_info_shomareShenasname").val();
    if (validateshomareshenasname(shomareshenasname) && new jj('#employe_info_shomareShenasname').jjVal() !== "") {
        $('#employe_info_shomareShenasname').css("border", "1px solid #e5e5e5");
        $("#msg5").html('');
    } else {
        $("#employe_info_shomareShenasname").css("border", "1px solid  red");
        $("#msg5").html('');
        $("#msg5").append('لطفا شماره شناسنامه خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_shomareShenasname').offset().top - 105}, 1500);
        return false;
    }
    ///شماره سریال شناسنامه
    var serialShenasname = $("#employe_info_serialShenasname").val();
    if (new jj('#employe_info_serialShenasname').jjVal() !== "") {
        if (!validatedigit(serialShenasname)) {
            $("#employe_info_serialShenasname").css("border", "1px solid  red");
            $("#msg6").html('');
            $("#msg6").append('سریال شناسنامه باید به صورت عدد واردشود.');
            $('html, body').animate({scrollTop: $('#employe_info_serialShenasname').offset().top - 105}, 1500);
            return false;
        }
    }
    var birthday = $("#employe_info_birthday").val();
    if (new jj('#employe_info_birthday').jjVal() !== "") {
        $('#employe_info_birthday').css("border", "1px solid #e5e5e5");
        $("#msg7").html('');
    } else {
        $("#employe_info_birthday").css("border", "1px solid  red");
        $("#msg7").html('');
        $("#msg7").append('لطفاتاریخ تولد خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_birthday').offset().top - 105}, 1500);
        return false;
    }
    ///محل صدور شناسنامه
    var localSodor = $("#employe_info_localSodor").val();
    if (new jj('#employe_info_localSodor').jjVal() !== "") {
        if (!validatepersion(localSodor)) {
            $("#employe_info_localSodor").css("border", "1px solid  red");
            $("#msg8").html('');
            $("#msg8").append('لطفامحل صدور شناسنامه را صحیح وارد کنید.');
            $('html, body').animate({scrollTop: $('#employe_info_localSodor').offset().top - 105}, 1500);
            return false;
        }
    }
    //////تعداد فزرندان
    var NOchildren = $("#employe_info_NOchildren").val();
    if (new jj('#employe_info_NOchildren').jjVal() !== "") {
        if (!validatedigit(NOchildren)) {
            $("#employe_info_NOchildren").css("border", "1px solid  red");
            $("#msg9").html('');
            $("#msg9").append('تعداد فرزندانتان را به صورت عدد وارد کنید.');
            $('html, body').animate({scrollTop: $('#employe_info_NOchildren').offset().top - 105}, 1500);
            return false;
        }
    }

    var phon1 = $("#employe_info_phon1").val();
    if (validatePhone(phon1) && new jj('#employe_info_phon1').jjVal() !== "") {
        $('#employe_info_phon1').css("border", "1px solid #e5e5e5");
        $("#msg10").html('');
    } else {
        $("#employe_info_phon1").css("border", "1px solid  red");
        $("#msg10").html('');
        $("#msg10").append('لطفاشماره موبایل خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_phon1').offset().top - 105}, 1500);
        return false;
    }
    var phon2 = $("#employe_info_phon2").val();
    if (validatePhone(phon2) && new jj('#employe_info_phon2').jjVal() !== "") {
        $('#employe_info_phon2').css("border", "1px solid #e5e5e5");
        $("#msg11").html('');
    } else {
        $("#employe_info_phon2").css("border", "1px solid  red");
        $("#msg11").html('');
        $("#msg11").append('لطفاشماره تلفن خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_phon2').offset().top - 105}, 1500);
        return false;
    }
    var email = $("#employe_info_email").val();
    if (validateEmail(email) && new jj('#employe_info_email').jjVal() !== "") {
        $('#employe_info_email').css("border", "1px solid #e5e5e5");
        $("#msg11").html('');
    } else {
        $("#employe_info_email").css("border", "1px solid  red");
        $("#msg12").html('');
        $("#msg12").append('لطفا ایمیل  خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_email').offset().top - 105}, 1500);
        return false;
    }

    var ostan = $("#employe_info_ostan").val();
    if (ostan !== "0") {
        $('#employe_info_ostan').css("border", "1px solid #e5e5e5");
        $("#msg13").html('');
    } else {
        $("#employe_info_ostan").css("border", "1px solid  red");
        $("#msg13").html('');
        $("#msg13").append('لطفا استان خود را انتخاب کنید');
        $('html, body').animate({scrollTop: $('#employe_info_ostan').offset().top - 105}, 1500);
        return false;
    }

    var city = $("#employe_info_city").val();
    if (city !== "0") {
        $('#employe_info_city').css("border", "1px solid #e5e5e5");
        $("#msg14").html('');
    } else {
        $("#employe_info_city").css("border", "1px solid red");
        $("#msg14").html("");
        $("#msg14").append("لطفا شهر خود را انتخاب کنید");
        $('html, body').animate({scrollTop: $("#employe_info_city").offset().top - 105}, 1500);
        return false;
    }
    var maxBime = $("#employe_info_maxBime").val();
    if (validatedigit(maxBime) && new jj('#employe_info_maxBime').jjVal() !== "") {
        $('#employe_info_maxBime').css("border", "1px solid #e5e5e5");
        $("#msg15").html('');
    } else {
        $("#employe_info_maxBime").css("border", "1px solid  red");
        $("#msg15").html('');
        $("#msg15").append('لطفامجموع سوابق بیمه را به عدد وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_maxBime').offset().top - 105}, 1500);
        return false;
    }
    var address = $("#employe_info_address").val();
    if (validatepersion(address) && new jj('#employe_info_address').jjVal() !== "") {
        $('#employe_info_address').css("border", "1px solid #e5e5e5");
        $("#msg16").html('');
    } else {
        $("#employe_info_address").css("border", "1px solid  red");
        $("#msg16").html('');
        $("#msg16").append('لطفاآدرس را به فارسی وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_address').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_lisans_stydy').jjVal() !== ""
            && new jj('#employe_info_lisans_University').jjVal() !== ""
            && new jj('#employe_info_lisans_trend').jjVal() !== ""
            && new jj('#employe_info_lisans_average').jjVal() !== ""
            && new jj('#employe_info_lisans_document').jjVal() !== ""
            && new jj('#employe_info_lisans_country').jjVal() !== ""
            ) {
        $('#lisans').css("border", "1px solid #e5e5e5");
    } else {
        $("#lisans").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('#lisans').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv1 = $("#employe_info_introducedPhone1").val();
    if (validatePhone(phonAdv1)
            && new jj('#employe_info_introducedName1').jjVal() !== ""
            && new jj('#employe_info_introducedfamily1').jjVal() !== ""
            && new jj('#employe_info_introducedPhone1').jjVal() !== ""
            ) {
        $('.infoo').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv2 = $("#employe_info_introducedPhone2").val();
    if (validatePhone(phonAdv2)
            && new jj('#employe_info_introducedName2').jjVal() !== ""
            && new jj('#employe_info_introducedfamily2').jjVal() !== ""
            && new jj('#employe_info_introducedPhone2').jjVal() !== ""
            ) {
        $('.infoo2').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo2").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo2').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_img').jjVal() !== "") {
        $('#PicPreviewPersonal_6').css("border", "1px solid #e5e5e5");
        $("#msg17").html('');
    } else {
        $("#PicPreviewPersonal_6").css("border", "1px solid  red");
        $("#msg17").html('');
        $("#msg17").append('لطفا فایل عکس را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#PicPreviewPersonal_6').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_cv').jjVal() !== "") {
        $('#PicPreviewPersonal_7').css("border", "1px solid #e5e5e5");
        $("#msg18").html('');
    } else {
        $("#PicPreviewPersonal_7").css("border", "1px solid  red");
        $("#msg18").html('');
        $("#msg18").append('لطفا فایل رزومه را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#PicPreviewPersonal_7').offset().top - 105}, 1500);
        return false;
    }
    return  true;

}

////////ردیافت اطلاعات از کاربران  فرم 1 mstid
function editEmploy2(code) {
    if (validEmplye()) {
        var param = "";
        param += "&do=EmployeForm2_info.edit";
        param += "&employe_info_code=" + code;
        param += "&" + new jj("#formcontent").jjSerial();
        new jj(param).jjAjax2(false);
    }
}
////////ردیافت اطلاعات از کاربران  فرم 1 mstid
function editEmploy(code) {
    if (validEmplye()) {
        var param = "";
        param += "&do=Employe_info.edit";
        param += "&employe_info_code=" + code;
        param += "&" + new jj("#formcontent").jjSerial();
        new jj(param).jjAjax2(false);
    }
}
////////ردیافت اطلاعات از کاربران  فرم 1 mstid
function insertemploye() {
    if (validEmplye()) {
        var param = "";
        param += "&do=Employe_info.insert";
        param += "&" + new jj("#formcontent").jjSerial();
        new jj(param).jjAjax2(false);
    }
}

;

/**
 * بازیابی رمز عبور
 * @returns {void}
 */
function recoveryEmploy() {
    if ($('#employe_info_code').val() == "" && $('#employe_info_mobile').val() == "") {
        $("#msgCode").html("کد رهگیری و موبایل را وارد کنید");
    }
    if ($('#employe_info_code').val() != "") {
        if (validatedigit($('#employe_info_code').val())) {
            alert("توصیه میشود  ابتدا کش مرورگر خود را پاک کنید یا حداقل قبل از ارسال کد چند بار مرورگر را رفرش کنید ");
            var params = "do=Employe_info.select"
                    + "&employe_info_code=" + $('#employe_info_code').val()
                    + "&employe_info_mobile=" + $('#employe_info_mobile').val();
            new jj(params).jjAjax2(true);
            $("#msgCode").html("کمی صبر کنید");
        } else {
            $("#msgCode").html("کد رهگیری را باید فقط عدد بصورت انگلیسی وارد کنید");
        }
    } else if ($('#employe_info_mobile').val() != "") {
//        if (!validatePhone($('#employe_info_mobile').val())) {
//            $("#msgCode").html("شماره تلفن را با اعداد انگلیسی وارد کنید");
//        } else {
        var params = "do=Employe_info.sendCodeToMobile"
                + "&employe_info_code=" + $('#employe_info_code').val()
                + "&employe_info_mobile=" + $('#employe_info_mobile').val();
        new jj(params).jjAjax2(true);
        $("#msgCode").html("در صورت صحت شماره تلفن، کد رهگیری به شماره ی شما پیامک خواهد شد");
        $('#employe_info_mobile').val("");
//        }
    }
}
;

/**
 * بازیابی رمز عبور
 * @returns {void}
 */
function recoveryEmploy2() {
    if ($('#employe_info_code').val() == "" && $('#employe_info_mobile').val() == "") {
        $("#msgCode").html("کد رهگیری و موبایل را وارد کنید");
    }
    if ($('#employe_info_code').val() != "") {
        if (validatedigit($('#employe_info_code').val())) {
            var params = "do=EmployeForm2_info.select"
                    + "&employe_info_code=" + $('#employe_info_code').val()
                    + "&employe_info_mobile=" + $('#employe_info_mobile').val();
            new jj(params).jjAjax2(true);
            $("#msgCode").html("کمی صبر کنید");
        } else {
            $("#msgCode").html("کد رهگیری را باید فقط عدد بصورت انگلیسی وارد کنید");
        }
    } else if ($('#employe_info_mobile').val() != "") {
//        if (!validatePhone($('#employe_info_mobile').val())) {
//            $("#msgCode").html("شماره تلفن را با اعداد انگلیسی وارد کنید");
//        } else {
        var params = "do=EmployeForm2_info.sendCodeToMobile"
                + "&employe_info_code=" + $('#employe_info_code').val()
                + "&employe_info_mobile=" + $('#employe_info_mobile').val();
        new jj(params).jjAjax2(true);
        $("#msgCode").html("در صورت صحت شماره تلفن، کد رهگیری به شماره ی شما پیامک خواهد شد");
//        }
    }
}
;

////دکتری////ردیافت اطلاعات از کاربران  فرم 2  mstid
function insertemploye2() {
    var name = $("#employe_info_name").val();
    if (validatepersion(name) && new jj('#employe_info_name').jjVal() !== "") {
        $('#employe_info_name').css("border", "1px solid #e5e5e5");
        $("#msg1").html('');
    } else {
        $("#employe_info_name").css("border", "1px solid  red");
        $("#msg1").html('');
        $("#msg1").append('لطفا نام خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_name').offset().top - 105}, 1500);
        return false;
    }
    var family = $("#employe_info_family").val();
    if (validatepersion(family) && new jj('#employe_info_family').jjVal() !== "") {
        $('#employe_info_family').css("border", "1px solid #e5e5e5");
        $("#msg2").html('');
    } else {
        $("#employe_info_family").css("border", "1px solid  red");
        $("#msg2").html('');
        $("#msg2").append('لطفا نام خانوادگی خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_family').offset().top - 105}, 1500);
        return false;
    }
    var father = $("#employe_info_father").val();
    if (validatepersion(father) && new jj('#employe_info_father').jjVal() !== "") {
        $('#employe_info_father').css("border", "1px solid #e5e5e5");
        $("#msg3").html('');
    } else {
        $("#employe_info_father").css("border", "1px solid  red");
        $("#msg3").html('');
        $("#msg3").append('لطفا نام پدر خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_father').offset().top - 105}, 1500);
        return false;
    }
    var codemeli = $("#employe_info_codemeli").val();
    if (validatecodemeli(codemeli) && new jj('#employe_info_codemeli').jjVal() !== "") {
        $('#employe_info_codemeli').css("border", "1px solid #e5e5e5");
        $("#msg4").html('');
    } else {
        $("#employe_info_codemeli").css("border", "1px solid  red");
        $("#msg4").html('');
        $("#msg4").append('کد ملی 10 رقمی می باشد.');
        $('html, body').animate({scrollTop: $('#employe_info_codemeli').offset().top - 105}, 1500);
        return false;
    }
    ////شماره شناسنامه
    var shomareshenasname = $("#employe_info_shomareShenasname").val();
    if (validateshomareshenasname(shomareshenasname) && new jj('#employe_info_shomareShenasname').jjVal() !== "") {
        $('#employe_info_shomareShenasname').css("border", "1px solid #e5e5e5");
        $("#msg5").html('');
    } else {
        $("#employe_info_shomareShenasname").css("border", "1px solid  red");
        $("#msg5").html('');
        $("#msg5").append('لطفا شماره شناسنامه خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_shomareShenasname').offset().top - 105}, 1500);
        return false;
    }
    ///شماره سریال شناسنامه
    var serialShenasname = $("#employe_info_serialShenasname").val();
    if (new jj('#employe_info_serialShenasname').jjVal() !== "") {
        if (!validatedigit(serialShenasname)) {
            $("#employe_info_serialShenasname").css("border", "1px solid  red");
            $("#msg6").html('');
            $("#msg6").append('سریال شناسنامه باید به صورت عدد واردشود.');
            $('html, body').animate({scrollTop: $('#employe_info_serialShenasname').offset().top - 105}, 1500);
            return false;
        }
    }
    var birthday = $("#employe_info_birthday").val();
    if (new jj('#employe_info_birthday').jjVal() !== "") {
        $('#employe_info_birthday').css("border", "1px solid #e5e5e5");
        $("#msg7").html('');
    } else {
        $("#employe_info_birthday").css("border", "1px solid  red");
        $("#msg7").html('');
        $("#msg7").append('لطفاتاریخ تولد خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#employe_info_birthday').offset().top - 105}, 1500);
        return false;
    }
    ///محل صدور شناسنامه
    var localSodor = $("#employe_info_localSodor").val();
    if (new jj('#employe_info_localSodor').jjVal() !== "") {
        if (!validatepersion(localSodor)) {
            $("#employe_info_localSodor").css("border", "1px solid  red");
            $("#msg8").html('');
            $("#msg8").append('لطفامحل صدور شناسنامه را صحیح وارد کنید.');
            $('html, body').animate({scrollTop: $('#employe_info_localSodor').offset().top - 105}, 1500);
            return false;
        }
    }
    //////تعداد فزرندان
    var NOchildren = $("#employe_info_NOchildren").val();
    if (new jj('#employe_info_NOchildren').jjVal() !== "") {
        if (!validatedigit(NOchildren)) {
            $("#employe_info_NOchildren").css("border", "1px solid  red");
            $("#msg9").html('');
            $("#msg9").append('تعداد فرزندانتان را به صورت عدد وارد کنید.');
            $('html, body').animate({scrollTop: $('#employe_info_NOchildren').offset().top - 105}, 1500);
            return false;
        }
    }

    var phon1 = $("#employe_info_phon1").val();
    if (validatePhone(phon1) && new jj('#employe_info_phon1').jjVal() !== "") {
        $('#employe_info_phon1').css("border", "1px solid #e5e5e5");
        $("#msg10").html('');
    } else {
        $("#employe_info_phon1").css("border", "1px solid  red");
        $("#msg10").html('');
        $("#msg10").append('لطفاشماره موبایل خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_phon1').offset().top - 105}, 1500);
        return false;
    }
    var phon2 = $("#employe_info_phon2").val();
    if (validatePhone(phon2) && new jj('#employe_info_phon2').jjVal() !== "") {
        $('#employe_info_phon2').css("border", "1px solid #e5e5e5");
        $("#msg11").html('');
    } else {
        $("#employe_info_phon2").css("border", "1px solid  red");
        $("#msg11").html('');
        $("#msg11").append('لطفاشماره تلفن خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_phon2').offset().top - 105}, 1500);
        return false;
    }
    var email = $("#employe_info_email").val();
    if (validateEmail(email) && new jj('#employe_info_email').jjVal() !== "") {
        $('#employe_info_email').css("border", "1px solid #e5e5e5");
        $("#msg11").html('');
    } else {
        $("#employe_info_email").css("border", "1px solid  red");
        $("#msg12").html('');
        $("#msg12").append('لطفا ایمیل  خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_email').offset().top - 105}, 1500);
        return false;
    }

    var ostan = $("#employe_info_ostan").val();
    if (ostan !== "0") {
        $('#employe_info_ostan').css("border", "1px solid #e5e5e5");
        $("#msg13").html('');
    } else {
        $("#employe_info_ostan").css("border", "1px solid  red");
        $("#msg13").html('');
        $("#msg13").append('لطفا استان خود را انتخاب کنید');
        $('html, body').animate({scrollTop: $('#employe_info_ostan').offset().top - 105}, 1500);
        return false;
    }

    var city = $("#employe_info_city").val();
    if (city !== "0") {
        $('#employe_info_city').css("border", "1px solid #e5e5e5");
        $("#msg14").html('');
    } else {
        $("#employe_info_city").css("border", "1px solid red");
        $("#msg14").html("");
        $("#msg14").append("لطفا شهر خود را انتخاب کنید");
        $('html, body').animate({scrollTop: $("#employe_info_city").offset().top - 105}, 1500);
        return false;
    }
    var maxBime = $("#employe_info_maxBime").val();
    if (validatedigit(maxBime) && new jj('#employe_info_maxBime').jjVal() !== "") {
        $('#employe_info_maxBime').css("border", "1px solid #e5e5e5");
        $("#msg15").html('');
    } else {
        $("#employe_info_maxBime").css("border", "1px solid  red");
        $("#msg15").html('');
        $("#msg15").append('لطفامجموع سوابق بیمه را به عدد وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_maxBime').offset().top - 105}, 1500);
        return false;
    }

    var address = $("#employe_info_address").val();
    if (validatepersion(address) && new jj('#employe_info_address').jjVal() !== "") {
        $('#employe_info_address').css("border", "1px solid #e5e5e5");
        $("#msg16").html('');
    } else {
        $("#employe_info_address").css("border", "1px solid  red");
        $("#msg16").html('');
        $("#msg16").append('لطفاآدرس را به فارسی وارد کنید.');
        $('html, body').animate({scrollTop: $('#employe_info_address').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_lisans_stydy').jjVal() !== ""
            && new jj('#employe_info_lisans_University').jjVal() !== ""
            && new jj('#employe_info_lisans_trend').jjVal() !== ""
            && new jj('#employe_info_lisans_average').jjVal() !== ""
            && new jj('#employe_info_lisans_document').jjVal() !== ""
            && new jj('#employe_info_lisans_country').jjVal() !== ""
            ) {
        $('#lisans').css("border", "1px solid #e5e5e5");
    } else {
        $("#lisans").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('#lisans').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_MA_stydy').jjVal() !== ""
            && new jj('#employe_info_MA_University').jjVal() !== ""
            && new jj('#employe_info_MA_trend').jjVal() !== ""
            && new jj('#employe_info_MA_average').jjVal() !== ""
            && new jj('#employe_info_MA_country').jjVal() !== ""
            && new jj('#employe_info_MA_document').jjVal() !== ""
            ) {
        $('#karshenasiArshad').css("border", "1px solid #e5e5e5");
    } else {
        $("#karshenasiArshad").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('#karshenasiArshad').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_PHD_stydy').jjVal() !== ""
            && new jj('#employe_info_PHD_University').jjVal() !== ""
            && new jj('#employe_info_PHD_trend').jjVal() !== ""
            && new jj('#employe_info_PHD_average').jjVal() !== ""
            && new jj('#employe_info_PHD_document').jjVal() !== ""
            && new jj('#employe_info_PHD_country').jjVal() !== ""
            ) {
        $('#dotoryDiv').css("border", "1px solid #e5e5e5");
    } else {
        $("#dotoryDiv").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('#dotoryDiv').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv1 = $("#employe_info_introducedPhone1").val();
    if (validatePhone(phonAdv1)
            && new jj('#employe_info_introducedName1').jjVal() !== ""
            && new jj('#employe_info_introducedfamily1').jjVal() !== ""
            && new jj('#employe_info_introducedPhone1').jjVal() !== ""
            ) {
        $('.infoo').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv2 = $("#employe_info_introducedPhone2").val();
    if (validatePhone(phonAdv2)
            && new jj('#employe_info_introducedName2').jjVal() !== ""
            && new jj('#employe_info_introducedfamily2').jjVal() !== ""
            && new jj('#employe_info_introducedPhone2').jjVal() !== ""
            ) {
        $('.infoo2').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo2").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo2').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_img').jjVal() !== "") {
        $('#PicPreviewPersonal_6').css("border", "1px solid #e5e5e5");
        $("#msg17").html('');
    } else {
        $("#PicPreviewPersonal_6").css("border", "1px solid  red");
        $("#msg17").html('');
        $("#msg17").append('لطفا فایل عکس را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#PicPreviewPersonal_6').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#employe_info_cv').jjVal() !== "") {
        $('#PicPreviewPersonal_7').css("border", "1px solid #e5e5e5");
        $("#msg18").html('');
    } else {
        $("#PicPreviewPersonal_7").css("border", "1px solid  red");
        $("#msg18").html('');
        $("#msg18").append('لطفا فایل رزومه را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#PicPreviewPersonal_7').offset().top - 105}, 1500);
        return false;
    }
    var param = "";
    param += "&do=EmployeForm2_info.insert";
    param += "&" + new jj("#formcontent").jjSerial();
    new jj(param).jjAjax2(false);
}

////////////////////////ساخت فرم دوره تخصصی در mstid به صورت داینامیک
var numderCourse = 2;
function DynamicsCourseForm() {
//       alert("77777");
    $("#save_btn").hide();
    $("#add_btn").click(function (e) {
        e.preventDefault();
        if (numderCourse <= 10) {
            appendRow(numderCourse);
        }
        numderCourse++;
//        $("#save_btn").show();
    });
    $('#input-wrapper').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        numderCourse--;

        if (numderCourse === 0) {
            $('#save_btn').hide();
        }
        numderCourse++;

    });
}
function appendRow(n) {
    $("#input-wrapper").append(
            '<div id="form_' + n + '">' +
            '<div class="border-input">' +
            '<label class="label1">دوره' + n + '</label>' +
            '<div class="rs-select2 js-select-simple select--no-search">' +
            '<div  class="border-form">' +
            '<div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">نام دوره تخصصی</label>' +
            '<input class="input--style-4" type="text" name="employe_info_courseName_' + n + '" id="employe_info_courseName_' + n + '">' +
            ' </div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1"> موسسه برگزار کننده</label>' +
            '<input class="input--style-4" type="text" name="employe_info_schoolName_' + n + '" id="employe_info_schoolName_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            ' <div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">گواهینامه دارد/ندارد</label>' +
            '<input class="input--style-4" type="text" name="employe_info_schooldocument_' + n + '" id="employe_info_schooldocument_' + n + '">' +
            '</div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">تاریخ برگزاری دوره</label>' +
            '<input class="input--style-4" type="text" name="employe_info_schoolStartDate_' + n + '" id="employe_info_schoolStartDate_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="btnDelete">' +
            '<button id="form_' + n + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
            );

}



///ساخت فرم آشنایی با نرم افزار  به صورت پویا  
var numberSfot = 2;
function DynamicsSoftForm() {
//    alert(555);
    $("#save_btn_saft").hide();
    $("#add_btn_saft").click(function (e) {
        e.preventDefault();
        if (numberSfot <= 5) {
            appendRow_saft(numberSfot);
        }
        numberSfot++;
//        $("#save_btn_saft").show();
    });
    $('#input-software').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        numberSfot--;
        if (numberSfot === 2) {
            $('#save_btn_saft').hide();
        }

    });
}
function appendRow_saft(n) {
    $("#input-software").append(
            '<div  id="formSoft' + n + '"  class="formStyle">' +
            '<div class="border-input">' +
            '<label class="label1">نرم افزار ' + n + '</label>' +
            '<div class="rs-select2 js-select-simple select--no-search">' +
            '<div class="border-form">' +
            '<div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">نام نرم افزار تخصصی</label>' +
            '<input class="input--style-4" type="text" name="employe_info_softName_' + n + '" id="employe_info_softName_' + n + '">' +
            ' </div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">سوابق  استفاده از نرم افزار  در پروژه ها</label>' +
            '<input class="input--style-4" type="text" name="employe_info_softProject_' + n + '" id="employe_info_softProject_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            ' <div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">میزان تسلط(کاملا مسلط/مسلط/آشنا)</label>' +
            '<input class="input--style-4" type="text" name="employe_info_softknow_' + n + '" id="employe_info_softknow_' + n + '">' +
            '</div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">گواهی نامه دارد/ندارد</label>' +
            '<input class="input--style-4" type="text" name="employe_info_softDocument_' + n + '" id="employe_info_softDocument_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="btnDelete">' +
            '<button id="formSoft' + n + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
            );
}



//////ساخت  فرم زبان های به صورت پویا 
var NoLanguage = 2;
function DynamicsLanguageForm() {
    $("#save_btn_Language").hide();
    $("#add_btn_Language").click(function (e) {
        e.preventDefault();
        if (NoLanguage <= 3) {
            appendRow_lang(NoLanguage);
        }
        NoLanguage++;
//        $("#save_btn_Language").show();
    });
    $('#input-Language').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        NoLanguage--;
        if (NoLanguage === 2) {
            $('#save_btn_Language').hide();
        }

    });
}
function appendRow_lang(n) {
    $("#input-Language").append(
            '<div id="formLanguage' + n + '"    class="border-input">' +
            '<label class="label1">زبان' + n + '</label>' +
            '<div class="rs-select2 js-select-simple select--no-search">' +
            '<div  class="border-form">' +
            '<div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">زبان</label>' +
            '<input class="input--style-4" type="text" name="employe_info_lanName_' + n + '" id="employe_info_lanName_' + n + '"">' +
            ' </div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">مکالمه(کاملا مسلط/مسلط/آشنا)</label>' +
            '<input class="input--style-4" type="text" name="employe_info_lanKnow_' + n + '"" id="employe_info_lanKnow_' + n + '"">' +
            '</div>' +
            '</div>' +
            '</div>' +
            ' <div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">ترجمه(کاملا مسلط/مسلط/آشنا)</label>' +
            '<input class="input--style-4" type="text" name="employe_info_lanTranslation_' + n + '"" id="employe_info_lanTranslation_' + n + '"">' +
            '</div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">نوشتن(کاملا مسلط/مسلط/آشنا)</label>' +
            '<input class="input--style-4" type="text" name="employe_info_lanWrite_' + n + '"" id="employe_info_lanWrite_' + n + '"">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">خواندن(کاملا مسلط/مسلط/آشنا)</label>' +
            '<input class="input--style-4" type="text" name="employe_info_lanRead_' + n + '"" id="employe_info_lanRead_' + n + '"">' +
            '</div>' +
            '</div>' +
            '<div class="btnDelete">' +
            '<button id="formLanguage' + n + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
            );
}
////////////////////form 2 /////////////////form 2//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////ساخت فرم دوره تخصصی در mstid به صورت داینامیک
var numderCourse = 2;
function DynamicsCourseForm2() {
//       alert("77777");
    $("#save_btn").hide();
    $("#add_btn").click(function (e) {
        e.preventDefault();
        if (numderCourse <= 10) {
            appendRow();
        }
        numderCourse++;
//        $("#save_btn").show();
        $('#input-wrapper').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
            var id = this.getAttribute("id");
            $('div[id=' + id + ']').remove();
            numderCourse--;
            if (numderCourse === 0) {
                $('#save_btn').hide();
            }

        });
    });
    function appendRow(n) {
        $("#input-wrapper").append(
                '<div id="form_' + n + '">' +
                '<div class="border-input">' +
                '<label class="label1">دوره' + n + '</label>' +
                '<div class="rs-select2 js-select-simple select--no-search">' +
                '<div  class="border-form">' +
                '<div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">نام دوره تخصصی</label>' +
                '<input class="input--style-4" type="text" name="employe_info_courseName_' + n + '" id="employe_info_courseName_' + n + '">' +
                ' </div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1"> موسسه برگزار کننده</label>' +
                '<input class="input--style-4" type="text" name="employe_info_schoolName_' + n + '" id="employe_info_schoolName_' + n + '">' +
                '</div>' +
                '</div>' +
                '</div>' +
                ' <div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">گواهینامه دارد/ندارد</label>' +
                '<input class="input--style-4" type="text" name="employe_info_schooldocument_' + n + '" id="employe_info_schoolName_' + n + '">' +
                '</div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">تاریخ برگزاری دوره</label>' +
                '<input class="input--style-4" type="text" name="employe_info_schoolStartDate_' + n + '" id="employe_info_schoolStartDate_' + n + '">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="btnDelete">' +
                '<button id="form_' + n + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
                );
    }


}
///ساخت فرم آشنایی با نرم افزار  به صورت پویا  
function DynamicsSoftForm2() {
//    alert(555);
    var numberSfot = 2;
    $("#save_btn_saft").hide();
    $("#add_btn_saft").click(function (e) {
        e.preventDefault();
        if (numberSfot <= 5) {
            appendRow_saft();
        }
        numberSfot++;
//        $("#save_btn_saft").show();
    });
    function appendRow_saft() {
        $("#input-software").append(
                '<div  id="formSoft' + numberSfot + '"  class="formStyle">' +
                '<div class="border-input">' +
                '<label class="label1">نرم افزار ' + numberSfot + '</label>' +
                '<div class="rs-select2 js-select-simple select--no-search">' +
                '<div class="border-form">' +
                '<div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">نام نرم افزار تخصصی</label>' +
                '<input class="input--style-4" type="text" name="employe_info_softName_' + numberSfot + '" id="employe_info_softName_' + numberSfot + '">' +
                ' </div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">سوابق  استفاده از نرم افزار  در پروژه ها</label>' +
                '<input class="input--style-4" type="text" name="employe_info_softProject_' + numberSfot + '" id="employe_info_softProject_' + numberSfot + '">' +
                '</div>' +
                '</div>' +
                '</div>' +
                ' <div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">میزان تسلط(کاملا مسلط/مسلط/آشنا)</label>' +
                '<input class="input--style-4" type="text" name="employe_info_softknow_' + numberSfot + '" id="employe_info_softknow_' + numberSfot + '">' +
                '</div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">گواهی نامه دارد/ندارد</label>' +
                '<input class="input--style-4" type="text" name="employe_info_softDocument_' + numberSfot + '" id="employe_info_softDocument_' + numberSfot + '">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="btnDelete">' +
                '<button id="formSoft' + numberSfot + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
                );
    }
    $('#input-software').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        numberSfot--;
        if (numberSfot === 2) {
            $('#save_btn_saft').hide();
        }

    });

}
//////ساخت  فرم زبان های به صورت پویا 
function DynamicsLanguageForm2() {
    var NoLanguage = 2;
    $("#save_btn_Language").hide();
    $("#add_btn_Language").click(function (e) {
        e.preventDefault();
        if (NoLanguage <= 3) {
            appendRow_saft();
        }
        NoLanguage++;
//        $("#save_btn_Language").show();
    });
    function appendRow_saft() {
        $("#input-Language").append(
                '<div id="formLanguage' + NoLanguage + '"    class="border-input">' +
                '<label class="label1">زبان' + NoLanguage + '</label>' +
                '<div class="rs-select2 js-select-simple select--no-search">' +
                '<div  class="border-form">' +
                '<div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">زبان</label>' +
                '<input class="input--style-4" type="text" name="employe_info_lanName_' + NoLanguage + '" id="employe_info_lanName_' + NoLanguage + '"">' +
                ' </div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">مکالمه(کاملا مسلط/مسلط/آشنا)</label>' +
                '<input class="input--style-4" type="text" name="employe_info_lanKnow_' + NoLanguage + '"" id="employe_info_lanKnow_' + NoLanguage + '"">' +
                '</div>' +
                '</div>' +
                '</div>' +
                ' <div class="row row-space">' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">ترجمه(کاملا مسلط/مسلط/آشنا)</label>' +
                '<input class="input--style-4" type="text" name="employe_info_lanTranslation_' + NoLanguage + '"" id="employe_info_lanTranslation_' + NoLanguage + '"">' +
                '</div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">نوشتن(کاملا مسلط/مسلط/آشنا)</label>' +
                '<input class="input--style-4" type="text" name="employe_info_lanWrite_' + NoLanguage + '"" id="employe_info_lanWrite_' + NoLanguage + '"">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="coll-2">' +
                '<div class="input-group">' +
                '<label class="label1">خواندن(کاملا مسلط/مسلط/آشنا)</label>' +
                '<input class="input--style-4" type="text" name="employe_info_lanRead_' + NoLanguage + '"" id="employe_info_lanRead_' + NoLanguage + '"">' +
                '</div>' +
                '</div>' +
                '<div class="btnDelete">' +
                '<button id="formLanguage' + NoLanguage + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
                );
    }
    $('#input-Language').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        NoLanguage--;
        if (NoLanguage === 2) {
            $('#save_btn_Language').hide();
        }

    });

}

/////////////////////////////////////فرم سابقه  کاری/////
var numderCompany = 2;
function DynamicsCompanyForm2() {
//       alert("5555");
    $("#save_btn_Company").hide();
    $("#add_btn_Company").click(function (e) {
        e.preventDefault();
        if (numderCompany <= 4) {
            appendRow_Company(numderCompany);
        }
        numderCompany++;
//        $("#save_btn").show();
    });
    $('#input-Company').on('click', '.deleteBtn', function () {
//        alert(this.getAttribute("id"));
        var id = this.getAttribute("id");
        $('div[id=' + id + ']').remove();
        numderCompany--;
        if (numderCompany === 0) {
            $('#save_btn_Company').hide();
        }

    });

}
function appendRow_Company(n) {
    $("#input-Company").append(
            '<div id="form_' + n + '">' +
            '<div class="border-input">' +
            '<label class="label1">سوابق' + n + '</label>' +
            '<div class="rs-select2 js-select-simple select--no-search">' +
            '<div  class="border-form">' +
            '<div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">نام شرکت/سازمان</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_' + n + '" id="employe_info_Company_' + n + '">' +
            ' </div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">تاریخ شروع</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_startDate_' + n + '" id="employe_info_Company_startDate_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            ' <div class="row row-space">' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">تاریخ پایان </label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_endtDate_' + n + '" id="employe_info_Company_endtDate_' + n + '">' +
            '</div>' +
            '</div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">سمت</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_post_' + n + '" id="employe_info_Company_post_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="row row-space">' +
            ' <div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">عنوان پروژه/فعالیت</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_nameProject_' + n + '" id="employe_info_Company_nameProject_' + n + '">' +
            ' </div>' +
            ' </div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">علت خاتمه همکاری</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_end_' + n + '" id="employe_info_Company_end_' + n + '">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="row row-space">' +
            ' <div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1">شماره تماس</label>' +
            '<input class="input--style-4" type="text" name="employe_info_Company_phone_' + n + '" id="employe_info_Company_phone_' + n + '">' +
            ' </div>' +
            ' </div>' +
            '<div class="coll-2">' +
            '<div class="input-group">' +
            '<label class="label1"></label>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="btnDelete">' +
            '<button id="form_' + n + '" class="btn btn-danger deleteBtn"><i class="">حذف</i></button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
            );


}
;






///////////////////////////////////////
function formDesigner() {
//    alert("formDesigner");
    var param = "";
    param += "&do=Employe_info_d.insert";
    param += "&" + new jj("#formDesigner").jjSerial();
    new jj(param).jjAjax2(false);
}
function formCompany() {
//    alert("formCompany");
    var param = "";
    param += "&do=Empolye_info_company.insert";
    param += "&" + new jj("#formCompany").jjSerial();
    new jj(param).jjAjax2(false);
}
/////////فرم سیستمهای خبره
function formExper() {
//    alert("formExper");
    var param = "";
    param += "&do=Empolye_info_expert.insert";
    param += "&" + new jj("#formExper").jjSerial();
    new jj(param).jjAjax2(false);
}
function userCode() {
    window.location = "index.jsp";
//    var param ="";
//     param += "&do=Employe_info.select";
//    param += "&" + new jj("#getCode").jjSerial();
//     new jj(param).jjAjax2(false);
}


function selectUserForm() {
    var param = "";
    param += "do=Employe_info.select";
    new jj(param).jjAjax2(false);
}
//////slider mstid
function slidersite() {
    new jj("do=Pic.getsidersite&panel=jjslider&jj=1").jjAjax2();
//    picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
}
function slidersite2() {
    new jj("do=Pic.getsidersite2&panel=carousel&jj=1").jjAjax2();

//    picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
}
function slidersite4() {
    new jj("do=Pic.getsidersite4&panel=carousel_4&jj=1").jjAjax2();

//    picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
}
function slidersite3() {
    new jj("do=Pic.getsidersite3&panel=carousel_3&jj=1").jjAjax2();

//    picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
}
function getNews() {
    new jj("do=Content.getContentAsnews&panel=jjNews&jj=1").jjAjax2(true);
}
function login_mstid() {
    var name = $("#user_name").val();
    if (!validatepersion(name) && new jj('#user_name').jjVal() !== "") {
        $('#user_name').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_name").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نام کاربری خود را وارد کنید.');
        return false;
    }
    var pass = $("#user_pass").val();
    if (validatePass(pass) && new jj('#user_pass').jjVal() !== "") {
        $('#user_pass').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#user_pass").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('رمز عبور خود را وارد کنید');
        return false;
    }
    var param = "";
    param += new jj("#form_login").jjSerial();
    param += "&do=Access_User.loginMstid&panels=userNameAfterLogin";
    new jj(param).jjAjax2();
}
///////لینک های سامانه در صفحه اصلی 
//function samane(){
//   window.location = 'Server?do=Content.sw&text=سامانه جامع اطلاعاتی و تامین'; 
//}


function samane1() {
    window.location = 'http://www.mstid.com/MSTID/Server?do=FormAnswerSet.add_new&formAnswers_formId=39';
//    href="httwinp://www.mstid.com/MSTID/Server?do=FormAnswerSet.add_new&formAnswers_formId=39";
}
function samane2() {
    window.location = 'http://www.mstid.com/MSTID/Server?do=FormAnswerSet.add_new&formAnswers_formId=40';

}
function samane3() {
    window.location = 'http://www.mstid.com/MSTID/Server?do=FormAnswerSet.add_new&formAnswers_formId=41';

}

function m_sendMassege() {
    var param = "";
    param += new jj("#formMassege").jjSerial();
    param += "&do=Messenger.sendMesseage";
    new jj(param).jjAjax2(false);


}

function insertComment() {
    var param = "";
//    param += "comment_email=" + $("#comment_email").val();
    param += "&comment_tell=" + $("#comment_tell").val();
    param += "&comment_full_name=" + $("#comment_full_name").val();
    param += "&comment_text=" + $("#comment_text").val();
    param += "&comment_refrenceId=" + window.localStorage.getItem('user_token');
    new jj("do=Comment.insert&" + param).jjAjax2(false);
    new jj("#swCommentForm").jjFormClean();
}
function add_newTicket() {
    $("#ticketHeader").show();//این قسمت فقط برای اولین پیام فعال باشد
    $("#message_chat").html("");//اگر قبلا یک چت را سلکت کرده باشد باید آنها را پاک کنیم
    $('#newTicket').slideDown();
    $('#ticketTable').hide();
    $('#messenger_chatID').val("");
    new jj('#formMassege').jjFormClean();


}
function sendTicket() {
    if (new jj('#messenger_title').jjVal() < 5) {
        new jj("عنوان را مختصر و واضح وارد کنید").jjModal("پیام سیستم");
        $("#messenger_title").css("border", "dashed red thin");
        return false;
    } else {
        $("#messenger_textMessage").css("border", "unset");
    }
    if (new jj('#messenger_textMessage').jjVal() < 10) {
        new jj("متن را وارد کنید").jjModal("پیام سیستم");
        $("#messenger_textMessage").css("border", "dashed red thin");
        return false;
    } else {
        $("#messenger_textMessage").css("border", "unset");
    }
    var params = new jj('#formMassege').jjSerial() + '&do=Messenger.insertChat&jj=1';
    params += "&messenger_attachFile=";// چون ممکن است چند فایل بارگذاری کرده باشند در حلقه از دیو نگهدارنده ی فایل های بارگذاری شده میخوانیم
    var messenger_attachFile = $(".messenger_attachFile");
    for (var i = 0; i < messenger_attachFile.length; i++) {
        params += $(messenger_attachFile[i]).val() + ",";
    }
    new jj(params).jjAjax(false);
    new jj("بعد از ارسال پیام میتوانید با رفرش کردن صفحه وضعیت پیام را در جدول ببینید").jjModal_Yes_No("صفحه رفرش بشود ؟", "location.reload();");
}
function selectTicket(id) {
    $("#ticketHeader").hide();//این قسمت فقط برای اولین پیام فعال باشد
    new jj('#formMassege').jjFormClean();
    $('#newTicket').slideDown();
    $('#ticketTable').hide();
    var params = "messenger_chatID=" + id + "&do=Messenger.refreshChat&jj=1";
    new jj(params).jjAjax(false);
}
//