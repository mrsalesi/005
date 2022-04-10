/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisFormQuestions = {
    tableName: "FormQuestions",
    f_id: "id",
    /**
     * 
     * @param {type} formId آی دی فرم حتما باید باشد
     * @param {type} panel
     * @returns {undefined}
     */
    m_refresh: function (formId, panel) {
        var param = "";
        param += "do=" + hmisFormQuestions.tableName + ".refresh";
        param += "&panel=" + (panel == null ? "swFormQuestionsTbl" : panel);
        param += "&formQuestions_formID=" + formId;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormQuestions.m_show_tbl();
    },
    m_show_form: function () {
        $('#swFormQuestionsTbl').slideUp('slow');
        $('#swFormQuestionsForm').slideDown('slow');
        hmisFormQuestions.tabSizeForm();
//                new jj("#swFormQuestionsForm").jjFormClean();

//        alert(2);
//        hmisFormQuestions.m_clean();
    },
    m_clean: function () {
        new jj("#swFormQuestionsForm").jjFormClean();
        $("#swFormQuestionOptionsTbl").html(""); // برای پاک کردن آپشن ها و گزینه های قبلی که ممکن است مربوط به سوال قبلی باشد
        $("#formQuestions_numberForCopy").val(""); // برای پاک کردن آپشن ها و گزینه های قبلی که ممکن است مربوط به سوال قبلی باشد

    },
    /**
     * زمانی که میخواهیم سوالی ایجاد کنیم داخل فرمی که ازش تکمیل فرم داریم این هشدار  داده می شود
     * @param {type} id
     * @returns {undefined}
     */
    m_add_newAndAlert: function () {
        new jj("با ایجاد اطلاعات جدید ممکن است نتایج فرم ها تغییر کند آیا موافق هستید؟").jjModal_Yes_No('حذف سوال و گزینه های آن', ' hmisFormQuestions.m_add_new();');
    },
    m_add_new: function () {
        new jj("do=" + hmisFormQuestions.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisFormQuestions.m_show_form();
        hmisFormQuestions.m_clean();
        $('.formTypeSelector').hide();    
        $("#formQuestions_range").select2({
            width: '100%'
        });
    },
    m_show_tbl: function () {
        $('#swFormQuestionsTbl').slideDown('slow');
        $('#swFormQuestionsForm').slideUp('slow');
//        $('#refreshForms').dataTable({//
        $('#swFormQuestionsTbl table').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var flag = true;
        if ($("#formQuestions_preority").val() == "") {
            $("#formQuestions_preority").addClass("required");
            flag = false;
        } else {
            $("#formQuestions_preority").removeClass('required');
        }
        if (flag == true) {
            var param = "";
            param += "do=" + hmisFormQuestions.tableName + ".insert";
            param += "&" + new jj('#swFormQuestionsForm').jjSerial();
            param += "&formQuestions_formID=" + $("#hmis_forms_id").val();
            new jj(param).jjAjax2(false);
            hmisFormQuestions.m_clean();
        }
    },
    m_edit: function () {
        var flag = true;
        if ($("#formQuestions_preority").val() == "") {
            $("#formQuestions_preority").addClass("required");
            flag = false;
        } else {
            $("#formQuestions_preority").removeClass('required');
        }
        if (flag == true) {
            var param = "";
            param += "do=" + hmisFormQuestions.tableName + ".edit";
            param += "&" + new jj('#swFormQuestionsForm').jjSerial();
            param += "&formQuestions_formID=" + $("#hmis_forms_id").val();
            new jj(param).jjAjax2(false);
        }
        hmisFormQuestions.m_clean();
    },
    copy: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestions.tableName + ".copy";
        param += "&id=" + id;
        param += "&formQuestions_numberForCopy=" + new jj('#formQuestions_numberForCopy').jjVal();
        param += "&formQuestions_formID=" + $("#hmis_forms_id").val();
        new jj(param).jjAjax2(false);
    },
    /**
     *کپی یک سوال از فرم دیگر در فرم 
     * @param {type} id
     * @param {type} FormId
     * @returns {undefined}
     */
    copyQuestionInForm: function (id, FormId) {
        var param = "";
        param += "do=" + hmisFormQuestions.tableName + ".copyQuestionInform";
        param += "&id=" + id;
        param += "&formQuestions_formID=" + FormId;
        new jj(param).jjAjax2(false);
    },
    m_delete: function (id) {
        new jj("آیا از حذف این سوال اطمینان دارید؟").jjModal_Yes_No('حذف سوال و گزینه های آن', ' hmisFormQuestions.m_delete_after_question(' + id + ');');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestions.tableName + ".delete";
        param += "&" + hmisFormQuestions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        hmisFormQuestions.m_show_tbl();
//        hmisFormQuestions.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestions.tableName + ".select";
        param += "&" + hmisForms.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFormQuestions.m_show_form();
        $('.formTypeSelector').show();
        $("#formQuestions_range").select2({
            width: '100%'
        });
//        $("html, body").delay(1000).animate({scrollTop: $('#formTypeSelector').offset().top}, 800);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swForms').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swCommettes').css('height', 'auto');
    },
    /**
     * برای نشان دادن سوال های یک فرم که مقدار عددی دارند یا در نهایت قابل محاسبه هستند مثل رادیو ها و سلکت اپشن هایی که عدد دارند
     * این تابع در شاخص ها فراخوانی شده
     * @param {type} id
     * @param {type} panel
     * @returns {undefined}
     */
    getNumericalQuestionAsOption: function (id, panel) {
        panel = (panel == null ? "hmis_filter_formquestionOption_id" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        var param = "";
        param += "do=FormQuestions.getNumericalQuestionAsOption";
        param += "&panel=" + (panel == null ? "hmis_filter_formquestionOption_id" : panel);
        param += "&formQuestions_formID=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        var panelFilterQuestion = $(panel).parent().parent().parent().find('select.filterQuestion').attr('id');
        $(panel).parent().parent().parent().find('select.filterOption').html('');
        hmisFormQuestions.getQuestion(id, '#' + panelFilterQuestion);
    },
    getNumericalQuestionAsOptionLimit: function (id, panel) {
        panel = (panel == null ? "hmis_filter_formquestionOption_id" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        var param = "";
        param += "do=FormQuestions.getNumericalQuestionAsOption";
        param += "&panel=" + (panel == null ? "hmis_filter_formquestionOption_id" : panel);
        param += "&formQuestions_formID=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        var panelFilterQuestion = $(panel).parent().parent().parent().find('select.filterQuestion').attr('id');
        $(panel).parent().parent().parent().find('select.filterOption').html('');
        hmisFormQuestions.getQuestionLimit(id, '#' + panelFilterQuestion);//محدودیت تعداد سوال
    },
    /**
     *  سوالی را  براساس پاسخ ها محاسبه میکند
     * @param {type} formid
     * @param {type} panel
     * @returns {undefined}
     */
    getQuestion: function (id, panel) {
        panel = (panel == null ? "hmis_filter_formQuestionFilter_id" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        var param = "";
        param += "do=FormQuestions.getQuestion";
        param += "&panel=" + (panel == null ? "hmis_filter_formQuestionFilter_id" : panel);
        param += "&formQuestions_formID=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    getQuestionLimit: function (id, panel) {
        panel = (panel == null ? "hmis_filter_formQuestionFilter_id" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        var param = "";
        param += "do=FormQuestions.getQuestionLimit";
        param += "&panel=" + (panel == null ? "hmis_filter_formQuestionFilter_id" : panel);
        param += "&formQuestions_formID=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    getOptionForFilter: function (id, panel) {
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
    },
    getoptionRangeFormQuestion: function (panel, formID) {
        var param = "";
        param += "panel=" + panel;
        param += "&formQuestions_formId=" + formID;
        param += "&do=" + hmisFormQuestions.tableName + ".getoptionRangeFormQuestion";
        new jj(param).jjAjax2(false);
    },
    /**
     * حیطه سوال
     * @returns {undefined}
     */
     changeSelectToInputRange: function () {
        if ($('#rangeQuestion_div').css('display') == 'none') {
            $('#rangeQuestion_div').show();
            $('#formQuestions_range').attr('name', 'formQuestions_range');
            $('#formQuestions_range1').hide();
            $('#formQuestions_range1').attr('name', 'formQuestions_range1');
        } else {
            $('#rangeQuestion_div').hide();
            $('#formQuestions_range').attr('name', 'formQuestions_range1');
            $('#formQuestions_range1').show();
            $('#formQuestions_range1').attr('name', 'formQuestions_range');
        }
    }
    
    
   
};
