/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisFormAnswerSets = {
    tableName: "FormAnswerSet",
    f_id: "id",
    loadForm: function (id) {
//        if ($("#swMyFormsForm").html() == '') {
        if (true) {
            $("#swMyFormsForm").load("formHMIS/02newFormAnswer.html", null, function () {
                hmisFormAnswerSets.refreshMyAnswers(id, "swOneFormToCompleteTable");// وقتی فرم لود می شود جدول سوابق پاسخ ها را هم بیاورد
            });
        }
    },
    /**
     * علاوه بر نمایش جدول پاسخ هایی که کاربر به این فرم داده است دکمه ی تکمیل فرم جدید را هم می آورد
     * @param {type} formId
     * @param {type} containerId
     * @returns {undefined}
     */
    refreshMyAnswers: function (formId, containerId) {
        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
            hmisFormAnswerSets.loadForm(formId);
            return;
        }
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".refreshMyAnswers";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swOneFormToCompleteTable" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_form();
    },
    /**
     * نمایش فرم های تکمیل شده برای مالک فرم 
     * @param {type} containerId
     * @returns {undefined}
     */
    showFormForOwner: function (panel) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".showFormForOwner";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsTable" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl();
    },
    /**
     * بازگشت فرم تکمیل شده به وضعیت ثبت اولیه
     * @param {type} id
     * @returns {undefined}
     */
    requestChangeStatus: function (id) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".requestChangeStatus";
        param += "&id=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    /**
     * سرچ براساس  ای دی و نام فرم
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    refreshWithSearchFormId: function (panel) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".refreshWithSearchFormId";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsTable" : panel);
        param += "&formAnswers_formId=" + new jj("#searchFormId").jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl();
    },

    /**
     * نمایش یک مودال برای رفرش کردن لیست پاسخ های کاربر به یک فرم خاص
     * برای اینکه تکمیل فرم در صفحه ی دیگری انجام میشود و بعد از تکمیل جدول فرم های تکمیل شده در صفحه ی قبل بدون تغییر باقی میماند
     * @param {type} formId
     * @param {type} containerId
     * @returns {undefined}
     */
    refreshMyAnswersAfterQuestion: function (formId) {
        var win = window.open('Server?do=FormAnswerSet.add_new&formAnswers_formId=' + formId, '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisFormAnswerSets.refreshMyAnswers(' + formId + ');');
    },
    selectMyAnswersAfterQuestion: function (formId, answerSetId) {
        var win = window.open('Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=' + formId + "&id=" + answerSetId + "", '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisFormAnswerSets.refreshMyAnswers(' + formId + ');');
    },
    /**
     * این تابع جدولی از فرم هایی که تا کنون پر شده را می آورد و میتوانیم جدید یکی پر کنیم 
     * @param {type} formId
     * @param {type} containerId
     * @returns {undefined}
     */
    showMyForms: function (containerId) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".showMyForms";
        param += "&panel=" + (containerId == null ? "swMyFormsTbl" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl();
    },
    /**
     * این تابع جدولی از فرم هایی که تا توسط کاربر تا کنون پر شده را می آورد و
     * فقط می تواند مشاهده کند یا یکی از موقت  هر را ویرایش کند  یا آمار آنرا ببیند
     * @param {type} formId
     * @param {type} panel
     * @returns {undefined}
     */
    showAllMyAnswerSets: function (panel) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".showAllMyAnswerSets";
        param += "&panel=" + (panel == null ? "swAllMyAnswerSetsTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl2();
    },
    /**
     * این تابع جدولی از همه ی  فرم هایی که تا کنون پر شده را می آورد 
     * کاربرد این فرم گزارش گرفتن آماری برای فرم هاست
     * @param {type} formId
     * @param {type} containerId
     * @returns {undefined}
     */
    showAllForms: function (panel) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".showAllForms";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl();
    },
    m_show_form: function () {
        $('#swMyFormsTbl').slideUp('slow');
        $('#swMyFormsForm').slideDown('slow');
    },
    m_clean: function () {
        new jj("#swOneFormToCompleteForm").jjFormClean();
    },
    m_add_new: function (formId) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".add_new&jj=1";
        param += "&formAnswers_formId=" + formId;
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_form();
        $('#swOneFormToCompleteForm').slideDown();
        hmisFormAnswerSets.m_clean();
    },
    m_show_tbl: function () {
        $('#swMyFormsTbl').show();
        $('#swMyFormsForm').hide();
        $('#formQuestions').hide();
        $('#swAllMyAnswerSetsTbl').hide();
    },
    m_show_tbl2: function () {
        $('#swAllMyAnswerSetsTbl').show();
        $('#swMyFormsTbl').hide();
        $('#swMyFormsForm').hide();
        $('#formQuestions').hide();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + this.tableName + ".insert";
        param += "&" + new jj('#swOneFormToCompleteForm').jjSerial();
        new jj(param).jjAjax2(false);

    },
    m_edit: function (id) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".edit";
        param += "&" + new jj('#swOneFormToCompleteForm #newForm').jjSerial();
        new jj(param).jjAjax2(false);
        this.m_show_tbl();
        this.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisFormAnswerSets.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".delete";
        param += "&" + hmisFormAnswerSets.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_tbl();
        hmisFormAnswerSets.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "&do=" + hmisFormAnswerSets.tableName + ".select";
        param += "&" + hmisFormAnswerSets.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFormAnswerSets.m_show_form();
        $("#formQuestions").show();// در سلکت فرم افزودن سوالات را نسان میدهیم چون وقتی فرم نداریم نباید بتوانیم سوالی را ایجاد کنیم
        hmisFormQuestions.m_refresh(id);//ّبرای نشان دادن گزینه های این فرم
    },
    tabSizeTbl: function () {
        $('#swForms').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swCommettes').css('height', 'auto');
    }
};