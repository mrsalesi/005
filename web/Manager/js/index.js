
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
var setting_login_exit_panel = "#jjLoginExitPanel";
//################کد های ویژه ی سمت کلاینت کاربر نهایی مثل فرم ها و محتوا و غیره#############
/**
 * این تابع اطلاعات یک فرم را برای ذخیره ی موقت و اینسرت اولیه به سرور می فرسد.
 * سوالات اجابری را چک نمی کند که تکمیل شده باشند
 * برای ویرایش و ثبت نهایی شرایط فرق دارد
 * @returns {undefined}
 */
function formAnswerSet_insert(obj) {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
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
        new jj(" در صورت تایید در فرصت های دیگر باید به سوالات پاسخ بدهید،<br/>ثبت موقت بشود؟").jjModal_Yes_No("شما به تعدادی از سوالات پاسخ نداده اید",
                "new jj('do=FormAnswerSet.insert&'+new jj('#swOneFormToCompleteForm').jjSerial()).jjAjax2(false);");
        $([document.documentElement, document.body]).animate({
            scrollTop: $(firsnonAnswered).offset().top
        }, 500);
        $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
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
    if ($('#latitude').val() != "" && $('#longitude').val() != "") {
        param += "&latLang=" + $('#latitude').val() + "," + $('#longitude').val();
    }
    new jj(" در صورت تایید فرم قابل تغییر نیست،<br/>ثبت نهایی بشود؟").jjModal_Yes_No("ثبت نهایی فرم", "new jj('" + param + "').jjAjax2(false);");
}

/**
 *  ذخیره ی و ثبت نهایی فرم
 *  به سرور می فرسد.
 *  و بعد دیگر کار نمی کند چون آنکلیک را برمیدارد
 * سوالات اجباری را چک می کند که تکمیل شده باشند و در غیر اینصورت اسکرول می کند به آن سوال
 * @returns {undefined}
 */
function formAnswerSet_insertAndFinalForm(obj) {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
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
function formAnswerSet_insertAndFinalForm_2(obj) {
    var requireds = $("#swOneFormToCompleteForm input[required]:text,#swOneFormToCompleteForm input[required]:hidden");
    var flag = true;
    var firsnonAnswered;
    var tempStr = $(obj).attr("onclick");// تابع پشت دکمه را بصورت موقت میگذاریم در یک متغیر و از پشت دکمه پاک میکنیم
    $(obj).attr("onclick", "");// برای اینکه در مرتبه ی اول که این دکمه را زد دیگر ثبت انجام نشود   
//    for (var i = 0; i < requireds.length; i++) {
//        if ($(requireds[i]).val().trim() === "") {
//            $(requireds[i]).parent().parent().addClass('redBorder');
//            if (flag) {//فقط اولین عنصری که اجباری بوده و پر نشده این حالت دارد برای بعدی ها فلگ فالس می شود
//                firsnonAnswered = $(requireds[i]).parent().parent();
//            }
//            flag = false;
//        } else {
//            $(requireds[i]).parent().parent().removeClass('redBorder');
//        }
//    }
//    if (!flag) {
//        new jj("شما به تعدادی از سوالات پاسخ نداده اید").jjModal(" در صورت تایید نهایی دیگر اجازه ی ویرایش فرم را نخواهید داشت");
//        $([document.documentElement, document.body]).animate({
//            scrollTop: $(firsnonAnswered).offset().top
//        }, 500);
//        $(obj).attr("onclick", tempStr);// برای اینکه تابع ثبت نهایی مجدد برود پشت دکمه قرار بگیرد
//        return;
//    }
    var param = "";
    param += "do=FormAnswerSet.insert";
    param += "&formAnswers_status=ثبت نهایی";
    param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
    if ($('#latitude').val() != "" && $('#longitude').val() != "") {
        param += "&latLang=" + $('#latitude').val() + "," + $('#longitude').val();
    }
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
 * این تابع برای دکمه ورود در صفحه اول سایت نوشته شده است
 * @returns {Boolean}
 */
function signInHome() {

    if ($('#emailInHome').val() === ("")) {
        $("#emailInHome").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل  نباید تهی باشد.");
        return false;
    } else if ($('#passInHome').val() === ("")) {
        $("#passInHome").css("border", "red dashed");
        $("#loginMessagePanel").html("رمز عبور نباید تهی باشد.");
        return false;
    }
    $("#emailInHome").css("border", "none");
    $("#passInHome").css("border", "none");
    var param = "";
    param += "do=Access_User.loginUserInHome&panel=" + setting_login_exit_panel.replace("#", "");
 param += "&StudentId=" + $("#emailInHome").val();
    param += "&pass=" + $("#passInHome").val();
    new jj(param).jjAjax2(false);
     var href = "";
      window.location.href = href;
}
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
function registMafad() {

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
    if ($('#user_email').val() === ("")) {
        $("#user_email").css("border", "red dashed");
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
    $("#user_email").css("border", "none");
    $("#user_mobile").css("border", "none");
    $("#user_codeMeli").css("border", "none");
    $("#user_family").css("border", "none");
    $("#user_name").css("border", "none");
    $("#user_passHint").css("border", "none");
    var param = "";
    param += "do=Access_User.registUserInSite&panel=" + setting_login_exit_panel.replace("#", "");
    param += "&" + new jj("#form_register").jjSerial();
    new jj(param).jjAjax2(false);
     var href = "";
      window.location.href = href;
    
}
function  sendEmailForgetPass() {
    var param = "";
    param += "do=Access_User.sendEmailForgetPass";
    param += "&email=" + $('#email').val();
    new jj(param).jjAjax2(false);
          window.location.href = href;

}

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
    new jj("do=Content.sw&text=" + titleTextOrId.toString() + "&panel=sw&title=swTitle&jj=1").jjAjax2(true);
    swRightClear();

    /*
     *@augments if menu dosnt need , it must be empty
     **/
}
;
function swRightClear() {
    $("#swRight").html("");
}


function scrollInFirstPage() {
    $("html, body").delay(1500).animate({scrollTop: $("#forScrollInTheFirstPage").offset().top}, 800);
}
function swDashbord(textTitle, panel) {
    new jj("do=Content.getDashbord&text=" + textTitle + "&panel=" + panel + "&title=swTitle&jj=1").jjAjax2(true);
}
function swPrivate(panel) {
    new jj("do=Content.swPrivate&panel=" + panel + "&title=swTitle&jj=1").jjAjax2(true);
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
            new jj("#login_user_pass").jjAddEnterKeyListener("loginn();");
            new jj("#user_pass1").jjAddEnterKeyListener("signIn();");
            new jj("#user_email1").jjAddEnterKeyListener("signIn();");
            new jj("#user_answer").jjAddEnterKeyListener("registInSite();");
            new jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);
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
//برای نشان دادن سنجه ها در محورهای اعتبار بخشی و داشبورد
function swGauges(zirMehvar, panel) {
    new jj("do=ManagementGauges.swGauges&text=" + zirMehvar + "&panel=" + panel + "&title=swTitle&jj=1").jjAjax2(true);
}
/*
 *برای نشان دادن شاخص ها در محورهای اعتبار بخشی و داشبورد
 اگر بجای تایل عدد بدهیم هم میشود
 * @returns {Boolean}
 *
 */
function swIndicators(title, panel) {
    var params = "do=Indicators.swIndicators";
    params += "&text=" + title;
    params += panel == null ? "" : "&panel=" + panel;
    params += "&title=swTitle&jj=1";
    new jj(params).jjAjax2(true);
}

/////////////////////////ورود به سیستم Hmis
/**
 * ورود کاربرانب که به قسمت های
 * hmis
 * دسترسی دارند
 * @returns {Boolean}
 */
function login() {
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
function forgetPassword() {
    if (new jj('#login_user_email').jjVal() == '') {
        $("#login_user_email").css("border", "red dashed");
        $("#loginMessagePanel7").html("ایمیل  نباید تهی باشد.");
        return false;
    } else {
        $("#login_user_email").css("border", "none");
        new jj("do=Access_User.forgetPassword&jj=1&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
    }
}
;
function validateEmail(email) {
    var emailPathern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//    var re = /^.*$/;
    return emailPathern.test(email);
}
function validatePhone(mobile) {
    var regx = /^(09|9|03)[0-9]{9}$/;
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
    var regx = /^([\u0600-\u06FF].{1,30}\s*)$/;
    return regx.test(string);
}

function validateenglish(string) {
    var regx = /^[a-z ,.'-]+$/i;
    return regx.test(string);
}
function validatePass(lastname) {
    var regx = (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/);
    return regx.test(lastname);
}

function validatecodemeli(codemeli) {
    var regx = /^[0-9]{10}$/;
    return regx.test(codemeli);
}
function validatedigit(digit) {
    var regx = /^\d*$/;
//    var regx = /^[0-9]$/;  
    return regx.test(digit);
}


function registInSite() {
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

//   


//
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

    new jj("do=Access_User.registUser&jj=1&panel=" + setting_login_exit_panel.replace("#", "") + "&" + new jj("#registForm").jjSerial()).jjAjax2(false);
//    $("#registerBtn").hide();

//    jj("do=Access_User.registUser&" + new jj("#registForm").jjSerial()).jjAjax2(false);
}
//////////////////// برای نمایش تابع رجیستر وپنهان کردن فرم وروداست ShowRegistForm 
function ShowRegisterForm() {
    new jj("do=Access_User.add_newSignUp").jjAjax2(false);
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
    new jj("do=Access_User.registUserPardakht&panel=" + new jj("#registFormPardakht").jjSerial() + "&EmailPardakhtha=" + jj("#user_email_pardakht2").jjVal()).jjAjax2(false);
//    $("#registerBtn").hide();

//    jj("do=Access_User.registUser&" + new jj("#registForm").jjSerial()).jjAjax2(false);
}
function loginUser(panel, email, pass) {
    var param = "";
    param += "do=Access_User.loginUser";
    param += "&user_email=" + email;
    param += "&user_pass=" + pass;
    param += "&panel=" + panel;
    new jj(param).jjAjax2(false);
}
function signOut() {
    USER_NAME = "";
    USER_FAMILY = "";
    USER_EMAIL = "";
    USER_PASS = "";
    new jj("do=Access_User.signOut").jjAjax2(true);
//    $("#loginForm").show();
//    $("#jjLoginExitPanel").html('ورود / ثبت نام');
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
//function swGetProducts(productId) {
////    swRightProductMenu("swRight");
//    var tempid = (productId) ? productId : "0";
//    new jj("do=Product.getSubCategouriesProduct&panel=sw_1&id=" + tempid + "&jj=1&title=swTitle").jjAjax2(true);
//    swOptionsMenu("swSelectOptions");
//
//}
function getOneproduct(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=Product.getOneProduct&id=" + newsId.toString() + "&panel=sw&jj=1&title=swTitle").jjAjax2(true);
//         $("#commentForm").load("formCms/comment.html");
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
function swRightProductMenu(panel) {
    var panel = (panel) ? panel : "swRight";
    new jj("do=Category_Product.getHierarchyDiv&panel=" + panel + "&id=0&jj=1").jjAjax2();
}
//============ BY RASHIDI ========>
function swOptionsMenu(panel) {
    var panel = (panel) ? panel : "swSelectOptions";
    new jj("do=Category_Product.getSelectOptions&panel=" + panel + "&id=0&jj=1").jjAjax2();
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

    $(".shoppingCart .sumPrice").each(function () {
        sum += parseInt($(this).html());
    });
    $("#finalPrice").html(sum);
    $("#account_factor_sum").val(sum);
}

function addToShoppingCart(productId) {//آی دی کالای انتخابی را به کوکی اضافه می کند.

//    new jj('productId=' + productId).jjAjaxCookie();//با سرولته مربوط به کوکی کار میکنه
    if (new jj('productsId').jjCookieGet().replace("%2C", ",").indexOf(productId) < 0) {//اگر این کالا قبلا انتخاب نشده باشد
        if (new jj('productsId').jjCookieGet() !== "") {//قبلا کوکی ست شده است
            new jj('productsId').jjCookieUpdate(productId + ',');
            new jj('productNums').jjCookieUpdate(1);
            $("#productNums").html(new jj('productNums').jjCookieGet());
//            $("#product").html(new jj('product').jjCookieGet());
            new jj('کالا به سبد خرید اضافه شد.').jjDialog();
//        new jj('productId=' + productId).jjAjaxCookie();//تابعی که درخواست رو به کوکی سرولت میفرسته.
        } else {//تا حالا کوکی ست نشده
//        alert(productId);
            new jj('productsId').jjCookieSave(productId + ',');
            new jj('productNums').jjCookieSave(1);
            $("#productNums").html(1);
            showShoppingCart();
        }
    } else {
        new jj('این کالا پیش از این انتخاب شده است.').jjDialog();
    }

}
;
//function showShoppingCart() {//تابعی که سبد خرید را به صورت جدول می سازد فراخوانی میکند
//    if (new jj('productsId').jjCookieGet() !== "") {
//        swRightClear();
//        new jj("do=Product.buildShoppingCart&panel=sw").jjAjax2(true);
//        $("#divDargah").hide();
//    } else {//تا حالا کوکی ست نشده
////        alert('کالایی انتخاب نشده است.')
//        new jj('کالایی انتخاب نشده است').jjDialog();
//    }
//
//}
;
function deletePrFromCart(productId) {
    new jj("آیا از حذف این محصول اطمینان دارید؟").jjDialog_YesNo('afterDeletePrFromCart(' + productId + ');\n', true, "");
}
function afterDeletePrFromCart(productId) {//آیتمی را از سبد خرید حذف میکند.
    new jj('productsId').jjCookieReplace(productId + ",", "");
    new jj('productNums').jjCookieUpdate(-1);
    $("#productNums").html(new jj('productNums').jjCookieGet());
    $("#prRowTr" + productId).remove();
//    $("#product" + productId).remove();
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
    new jj("do=Factor.insertFactor&").jjAjax2(true);//ثبت فاکتور در دیتابیس
}
function prePayment() {

    if (!$(".bankDiv label input").is(':checked')) {
        $(".bankDiv").css("border", "1px red dashed");
    } else if (new jj('#user_address').jjVal() == '') {
        $("#user_address").css("border", "1px red dashed");
        $(".bankDiv").css("border", "0px gray dashed");
    } else {
        $("#user_address").css("border", "1px gray solid");
        $(".bankDiv").css("border", "0px gray dashed");
        var param = new jj("#sw").jjSerial();

        param += "&do=Factor.insertFactor";
//        nameDargah($('.bankDiv input:radio[class=dargahha]:checked').val());
        new jj(param).jjAjax2(true);//ثبت فاکتور در دیتابیس
    }
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
                    searchAction(text)
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

/**
 * این تابع یک سلول از جدول را میگیرد و سطری که در آن قرار دارد را رنگی میکند
 * @param {type} rowObj
 * @returns {undefined}
 */
hightLightTableRow = function (rowObj) {

    $('.selectedRow').addClass('selectedRow_light');
    $('.selectedRow').removeClass('selectedRow');
    $(rowObj).parent().addClass('selectedRow');
};
///////////form-mstid-valid//////////////////////
function  validEmplye() {
    var name = $("#q793").val();
    if (validatepersion(name) && new jj('#q793').jjVal() !== "") {
        $('#q793').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q793").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q793').offset().top - 105}, 1500);
        return false;
    }
    var family = $("#q794").val();
    if (validatepersion(family) && new jj('#q794').jjVal() !== "") {
        $('#q794').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q794").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خانوادگی خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q794').offset().top - 105}, 1500);
        return false;
    }
    var father = $("#q795").val();
    if (validatepersion(father) && new jj('#q795').jjVal() !== "") {
        $('#q795').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q795").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام پدر خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q795').offset().top - 105}, 1500);
        return false;
    }
    var codemeli = $("#q796").val();
    if (validatecodemeli(codemeli) && new jj('#q796').jjVal() !== "") {
        $('#q796').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q796").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('کد ملی 10 رقمی می باشد.');
        $('html, body').animate({scrollTop: $('#q796').offset().top - 105}, 1500);
        return false;
    }
    ////شماره شناسنامه
    var shomareshenasname = $("#q797").val();
    if (validateshomareshenasname(shomareshenasname) && new jj('#q797').jjVal() !== "") {
        $('#q797').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q797").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا شماره شناسنامه خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#q797').offset().top - 105}, 1500);
        return false;
    }
    ///شماره سریال شناسنامه
    var serialShenasname = $("#q799").val();
    if (new jj('#q799').jjVal() !== "") {
        if (!validatedigit(serialShenasname)) {
            $("#q799").css("border", "1px solid  red");
            $("#msg").html('');
            $("#msg").append('سریال شناسنامه باید به صورت عدد واردشود.');
            $('html, body').animate({scrollTop: $('#q799').offset().top - 105}, 1500);
            return false;
        }
    }
    var birthday = $("#q801").val();
    if (new jj('#q801').jjVal() !== "") {
        $('#q801').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q801").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاتاریخ تولد خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#q801').offset().top - 105}, 1500);
        return false;
    }
    ///محل صدور شناسنامه
    var localSodor = $("#q802").val();
    if (new jj('#q802').jjVal() !== "") {
        if (!validatepersion(localSodor)) {
            $("#q802").css("border", "1px solid  red");
            $("#msg").html('');
            $("#msg").append('لطفامحل صدور شناسنامه را صحیح وارد کنید.');
            $('html, body').animate({scrollTop: $('#q802').offset().top - 105}, 1500);
            return false;
        }
    }
    //////تعداد فزرندان
    var NOchildren = $("#q806").val();
    if (new jj('#q806').jjVal() !== "") {
        if (!validatedigit(NOchildren)) {
            $("#q806").css("border", "1px solid  red");
            $("#msg").html('');
            $("#msg").append('تعداد فرزندانتان را به صورت عدد وارد کنید.');
            $('html, body').animate({scrollTop: $('#q806').offset().top - 105}, 1500);
            return false;
        }
    }

    var phon1 = $("#q807").val();
    if (validatePhone(phon1) && new jj('#q807').jjVal() !== "") {
        $('#q807').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q807").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاشماره موبایل خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q807').offset().top - 105}, 1500);
        return false;
    }
    var phon2 = $("#q808").val();
    if (validatePhone(phon2) && new jj('#q808').jjVal() !== "") {
        $('#q808').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q808").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاشماره تلفن خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q808').offset().top - 105}, 1500);
        return false;
    }
    var email = $("#q810").val();
    if (validateEmail(email) && new jj('#q810').jjVal() !== "") {
        $('#q810').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q810").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا ایمیل  خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q810').offset().top - 105}, 1500);
        return false;
    }

    var ostan = $("#q812").val();
    if (ostan !== "0") {
        $('#q812').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q812").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا استان خود را انتخاب کنید');
        $('html, body').animate({scrollTop: $('#q812').offset().top - 105}, 1500);
        return false;
    }

    var city = $("#q813").val();
    if (city !== "0") {
        $('#q813').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    }
    else {
        $("#q813").css("border", "1px solid red");
        $("#msg").html("");
        $("#msg").append("لطفا شهر خود را انتخاب کنید");
        $('html, body').animate({scrollTop: $("#q813").offset().top - 105}, 1500);
        return false;
    }
    var maxBime = $("#q814").val();
    if (validatedigit(maxBime) && new jj('#q814').jjVal() !== "") {
        $('#q814').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q814").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفامجموع سوابق بیمه را به عدد وارد کنید.');
        $('html, body').animate({scrollTop: $('#q814').offset().top - 105}, 1500);
        return false;
    }
    var address = $("#q815").val();
    if (validatepersion(address) && new jj('#q815').jjVal() !== "") {
        $('#q815').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q815").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاآدرس را به فارسی وارد کنید.');
        $('html, body').animate({scrollTop: $('#q815').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#q834').jjVal() !== ""
            && new jj('#q835').jjVal() !== ""
            && new jj('#q836').jjVal() !== ""
            && new jj('#q837').jjVal() !== ""
            && new jj('#q841').jjVal() !== ""
            && new jj('#q840').jjVal() !== ""
            ) {
        $('#lisans').css("border", "1px solid #e5e5e5");
    } else {
        $("#lisans").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('#lisans').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv1 = $("#q959").val();
    if (validatePhone(phonAdv1)
            && new jj('#q954').jjVal() !== ""
            && new jj('#q955').jjVal() !== ""
            && new jj('#q959').jjVal() !== ""
            ) {
        $('.infoo').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo').offset().top - 105}, 1500);
        return false;
    }
    var phonAdv2 = $("#q965").val();
    if (validatePhone(phonAdv2)
            && new jj('#q960').jjVal() !== ""
            && new jj('#q961').jjVal() !== ""
            && new jj('#q965').jjVal() !== ""
            ) {
        $('.infoo2').css("border", "1px solid #e5e5e5");
    } else {
        $(".infoo2").css("border", "2px solid  red");
        $('html, body').animate({scrollTop: $('.infoo2').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#q973').jjVal() !== "") {
        $('#q973_Preview').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q973_Preview").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا فایل عکس را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#q973_Preview').offset().top - 105}, 1500);
        return false;
    }
    if (new jj('#q974').jjVal() !== "") {
        $('#q974_Preview').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q974_Preview").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا فایل رزومه را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#q974_Preview').offset().top - 105}, 1500);
        return false;
    }
    return  true;

}
function expert() {
    var name = $("#q1007").val();
    if (validatepersion(name) && new jj('#q1007').jjVal() !== "") {
        $('#q1007').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1007").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q1007').offset().top - 105}, 1500);
        return false;
    }
    var family = $("#q1008").val();
    if (validatepersion(family) && new jj('#q1008').jjVal() !== "") {
        $('#q1008').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1008").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام خانوادگی خود را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q1008').offset().top - 105}, 1500);
        return false;
    }
    var codemeli = $("#q1009").val();
    if (validatecodemeli(codemeli) && new jj('#q1009').jjVal() !== "") {
        $('#q1009').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1009").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('کد ملی 10 رقمی می باشد.');
        $('html, body').animate({scrollTop: $('#q1009').offset().top - 105}, 1500);
        return false;
    }

    var birthday = $("#q1010").val();
    if (new jj('#q1010').jjVal() !== "") {
        $('#q1010').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1010").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاتاریخ تولد خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#q1010').offset().top - 105}, 1500);
        return false;
    }
    /////محل تولد
    var birthday_local = $("#q1011").val();
    if (validatepersion(birthday_local) && new jj('#q1011').jjVal() !== "") {
        $('#q1011').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1011").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا محل تولد خود را وارد کنید');
        $('html, body').animate({scrollTop: $('#q1011').offset().top - 105}, 1500);
        return false;
    }
    ////مدت سابقه کاری 
    var work_experience = $("#q1014").val();
    if (validatedigit(work_experience) && new jj('#q1014').jjVal() !== "") {
        $('#q1014').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1014").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفامجموع سوابق کار را به عدد وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1014').offset().top - 105}, 1500);
        return false;
    }
    ////////////
    var address = $("#q1015").val();
    if (validatepersion(address) && new jj('#q1015').jjVal() !== "") {
        $('#q1015').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1015").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا آدرس خود را به فارسی وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1015').offset().top - 105}, 1500);
        return false;
    }
    var phon1 = $("#q1016").val();
    if (validatePhone(phon1) && new jj('#q1016').jjVal() !== "") {
        $('#q1016').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1016").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاشماره تلفن  خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1016').offset().top - 105}, 1500);
        return false;
    }
    var phon2 = $("#q1018").val();
    if (validatePhone(phon2) && new jj('#q1018').jjVal() !== "") {
        $('#q1018').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1018").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاشماره موبایل خود را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1018').offset().top - 105}, 1500);
        return false;
    }
    ////مقطع
    var madrak = $("#q1021").val();
    if (validatepersion(madrak) && new jj('#q1021').jjVal() !== "") {
        $('#q1021').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1021").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام مقطع تحصیلی خود را  وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1021').offset().top - 105}, 1500);
        return false;
    }
    ///رشته
    var reshteh = $("#q1022").val();
    if (validatepersion(reshteh) && new jj('#q1022').jjVal() !== "") {
        $('#q1022').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1022").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام رشته تحصیلی خود را  وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1022').offset().top - 105}, 1500);
        return false;
    }

    /////////////نام دانشگاه
    var name_school = $("#q1023").val();
    if (validatepersion(name_school) && new jj('#q1022').jjVal() !== "") {
        $('#q1023').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1023").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام رشته تحصیلی خود را  وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1023').offset().top - 105}, 1500);
        return false;
    }
    ///////////////////کشور یا شهر
    var contry = $("#q1024").val();
    if (validatepersion(contry) && new jj('#q1024').jjVal() !== "") {
        $('#q1024').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1024").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام رشته تحصیلی خود را  وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1024').offset().top - 105}, 1500);
        return false;
    }
    /////////////تاریخ شروع
    var date_start = $("#q1025").val();
    if (new jj('#q1025').jjVal() !== "") {
        $('#q1025').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1025").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا تاریخ شروع مقطع تحصیلی خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1025').offset().top - 105}, 1500);
        return false;
    }
    ///////تاریخ پایان
    var date_end = $("#q1026").val();
    if (new jj('#q1026').jjVal() !== "") {
        $('#q1026').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1026").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا تاریخ پایان مقطع تحصیلی خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1026').offset().top - 105}, 1500);
        return false;
    }
    ////////////معدل
    var average = $("#q1027").val();
    if (validatedigit(average) && new jj('#q1027').jjVal() !== "") {
        $('#q1027').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1027").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا تاریخ پایان مقطع تحصیلی خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1027').offset().top - 105}, 1500);
        return false;
    }
    //////////
    var help = $("#q1071").val();
    if (new jj('#q1071').jjVal() !== "") {
        $('#q1071').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1071").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا زمینه همکاری خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1071').offset().top - 105}, 1500);
        return false;
    }

    ///////////بار گذاری فایل
    var help = $("#q1083").val();
    if (new jj('#q1083').jjVal() !== "") {
        $('#q1083').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1083").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا رزومه کاری خود را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#q1083').offset().top - 105}, 1500);
        return false;
    }

}
//////////////////////////اعتبار سنجی فرم شرکت های دانش بنیان
function company() {
    var name_company = $("#q975").val();
    if (validatepersion(name_company) && new jj('#q975').jjVal() !== "") {
        $('#q975').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q975").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام شرکت را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q975').offset().top - 105}, 1500);
        return false;
    }
    var name_companyE = $("#q976").val();
    if (!validatepersion(name_companyE) && new jj('#q976').jjVal() !== "") {
        $('#q976').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q976").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام شرکت را به انگلیسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q976').offset().top - 105}, 1500);
        return false;
    }
    var asasname = $("#q987").val();
    if (validatepersion(asasname) && new jj('#q987').jjVal() !== "") {
        $('#q987').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q987").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا موضوع فعالیت در اساسنامه شرکت را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q987').offset().top - 105}, 1500);
        return false;
    }
    var ostan = $("#q993").val();
    if (validatepersion(ostan) && new jj('#q993').jjVal() !== "") {
        $('#q993').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q993").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفااستان محل سکونت خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q993').offset().top - 105}, 1500);
        return false;
    }

    var shomareSabt = $("#q977").val();
    if (validatedigit(shomareSabt) && new jj('#q977').jjVal() !== "") {
        $('#q977').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q977").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شماره ثبت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q977').offset().top - 105}, 1500);
        return false;
    }
    var date_sabt = $("#q978").val();
    if (new jj('#q978').jjVal() !== "") {
        $('#q978').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q978").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('تاریخ ثبت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q978').offset().top - 105}, 1500);
        return false;
    }

    var local_sabt = $("#q980").val();
    if (validatepersion(local_sabt) && new jj('#q980').jjVal() !== "") {
        $('#q980').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q980").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شهر محل ثبت را وارد نمایید..');
        $('html, body').animate({scrollTop: $('#q980').offset().top - 105}, 1500);
        return false;
    }
    var type_company = $("#q981").val();
    if (new jj('#q981').jjVal() !== "") {
        $('#q981').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q981").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نوع شرکت را مشخص کنید.');
        $('html, body').animate({scrollTop: $('#q981').offset().top - 105}, 1500);
        return false;
    }
    var name_famil_manager = $("#q983").val();
    if (validatepersion(name_famil_manager) && new jj('#q983').jjVal() !== "") {
        $('#q983').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q983").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نام و نام خانوادگی مدیر عامل شرکت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q983').offset().top - 105}, 1500);
        return false;
    }
    var phon_manager = $("#q984").val();
    if (validatePhone(phon_manager) && new jj('#q984').jjVal() !== "") {
        $('#q984').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q984").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شماره همراه مدیر عامل شرکت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q984').offset().top - 105}, 1500);
        return false;
    }
    var familyconnector = $("#q985").val();
    if (validatepersion(familyconnector) && new jj('#q985').jjVal() !== "") {
        $('#q985').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q985").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نام و نام خانوادگی رابط شرکت را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q985').offset().top - 105}, 1500);
        return false;
    }
    var phonconnector = $("#q986").val();
    if (validatePhone(phonconnector) && new jj('#q986').jjVal() !== "") {
        $('#q986').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q986").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شماره تلفن رابط شرکت را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q986').offset().top - 105}, 1500);
        return false;
    }
    var local_type = $("#q988").val();
    if (new jj('#q988').jjVal() !== "") {
        $('#q988').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q988").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نوع محل را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q988').offset().top - 105}, 1500);
        return false;
    }
    var local_type_1 = $("#q989").val();
    if (new jj('#q989').jjVal() !== "") {
        $('#q989').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q989").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('نوع محل اسقرار را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q989').offset().top - 105}, 1500);
        return false;
    }

    var local_type_2 = $("#q990").val();
    if (new jj('#q990').jjVal() !== "") {
        $('#q990').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q990").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append(' محل اسقرار را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q990').offset().top - 105}, 1500);
        return false;
    }
    var phon3 = $("#q991").val();
    if (validatePhone(phon3) && new jj('#q991').jjVal() !== "") {
        $('#q991').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q991").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شماره تلفن  شرکت را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q991').offset().top - 105}, 1500);
        return false;
    }

    var namabar = $("#q992").val();
    if (new jj('#q992').jjVal() !== "") {
        $('#q992').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q992").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('شماره نمابر  شرکت را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q992').offset().top - 105}, 1500);
        return false;
    }

    var ostan = $("#q979").val();
    if (new jj('#q979').jjVal() !== "") {
        $('#q979').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q979").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('استان خود را انتخاب نمایید.');
        $('html, body').animate({scrollTop: $('#q979').offset().top - 105}, 1500);
        return false;
    }
    ///////انتخاب شهر
//          var shahr = $("#q994").val();
//    if ( new jj('#q994').jjVal() !== "") {
//        $('#q994').css("border", "1px solid #e5e5e5");
//        $("#msg").html('');
//    } else {
//        $("#q994").css("border", "1px solid  red");
//        $("#msg").html('');
//        $("#msg").append('شهر خود را انتخاب نمایید.');
//        $('html, body').animate({scrollTop: $('#q994').offset().top - 105}, 1500);
//        return false;
//    }
    var address = $("#q995").val();
    if (validatepersion(address) && new jj('#q995').jjVal() !== "") {
        $('#q995').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q995").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('آدرس  را واردکنید.');
        $('html, body').animate({scrollTop: $('#q995').offset().top - 105}, 1500);
        return false;
    }
    var help = $("#q1000").val();
    if (new jj('#q1000').jjVal() !== "") {
        $('#q1000').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1000").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('زمینه همکاری خود را مشخص کنید.');
        $('html, body').animate({scrollTop: $('#q1000').offset().top - 105}, 1500);
        return false;
    }
    /////
    if (new jj('#q1001').jjVal() !== "") {
        $('#q1001').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1001").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('زمینه همکاری خود را مشخص کنید.');
        $('html, body').animate({scrollTop: $('#q1001').offset().top - 105}, 1500);
        return false;
    }
}
////////////////////اعتبار سنجی فرم شرکت های طراحی و ساخت///////////////////
function designing() {
    var name_company = $("#q1085").val();
    if (validatepersion(name_company) && new jj('#q1085').jjVal() !== "") {
        $('#q1085').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1085").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا نام قانونی  شرکت را به فارسی وارد کنید');
        $('html, body').animate({scrollTop: $('#q1085').offset().top - 105}, 1500);
        return false;
    }
    var number_company = $("#q1086").val();
    if (validatedigit(number_company) && new jj('#q1086').jjVal() !== "") {
        $('#q1086').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1086").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا شماره اقتصادی شرکت راوارد نمایید.');
        $('html, body').animate({scrollTop: $('#q1086').offset().top - 105}, 1500);
        return false;
    }
    var date_sabt = $("#q1088").val();
    if (new jj('#q1088').jjVal() !== "") {
        $('#q1088').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1088").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا تاریخ ثبت شرکت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q1088').offset().top - 105}, 1500);
        return false;
    }
    var local_sabt = $("#q1089").val();
    if (new jj('#q1089').jjVal() !== "") {
        $('#q1089').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1089").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا محل ثبت شرکت را وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q1089').offset().top - 105}, 1500);
        return false;
    }
    var Legal_status = $("#q1090").val();
    if (new jj('#q1090').jjVal() !== "") {
        $('#q1090').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1090").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('وضعیت قانونی شرکت را انتخاب کنید.');
        $('html, body').animate({scrollTop: $('#q1090').offset().top - 105}, 1500);
        return false;
    }
    var ostan = $("#q1094").val();
    if (validatepersion(ostan) && new jj('#q1094').jjVal() !== "") {
        $('#q1094').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1094").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفااستان محل سکونت خود را وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1094').offset().top - 105}, 1500);
        return false;
    }
    ///////انتخاب شهر
//          var shahr = $("#q1095").val();
//    if ( new jj('#q1095').jjVal() !== "") {
//        $('#q1095').css("border", "1px solid #e5e5e5");
//        $("#msg").html('');
//    } else {
//        $("#q1095").css("border", "1px solid  red");
//        $("#msg").html('');
//        $("#msg").append('شهر خود را انتخاب نمایید.');
//        $('html, body').animate({scrollTop: $('#q1095').offset().top - 105}, 1500);
//        return false;
//    }
    var email = $("#q1097").val();
    if (validateEmail(email) && new jj('#q1097').jjVal() !== "") {
        $('#q1097').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1097").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفا ایمیل خود را صحیح وارد نمایید.');
        $('html, body').animate({scrollTop: $('#q1097').offset().top - 105}, 1500);
        return false;
    }
    var phon = $("#q1102").val();
    if (validatePhone(phon) && new jj('#q1102').jjVal() !== "") {
        $('#q1102').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1102").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append('لطفاشماره موبایل رابط شرکت را صحیح وارد کنید.');
        $('html, body').animate({scrollTop: $('#q1102').offset().top - 105}, 1500);
        return false;
    }
    var file_upload = $("#q1105").val();
    if (validatePhone(phon) && new jj('#q1105').jjVal() !== "") {
        $('#q1105').css("border", "1px solid #e5e5e5");
        $("#msg").html('');
    } else {
        $("#q1105").css("border", "1px solid  red");
        $("#msg").html('');
        $("#msg").append(' لطفا اساسنامه شرکت را بارگذاری کنید.');
        $('html, body').animate({scrollTop: $('#q1105').offset().top - 105}, 1500);
        return false;
    }

}