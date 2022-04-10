/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/***
 * حقوق گیرنده خدمت
 * @type type
 */

var hmisSatisfaction = {
    tableName: "Satisfaction",
    f_id: "id",
    loadForm: function () {
        if ($("#swSatisfactionForm").html() == '') {
            $("#swSatisfactionForm").load("formHMIS/05nursing.html", null, function () {
//                $("#cancel_AssessmentForSessionCategory").click(function (e) {
//                    hmisSatisfaction.m_clean();
                    hmisSatisfaction.m_show_tbl();
//                });
            });
        }
    },
    refreshSatisfactionOfPatientReporting: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".refreshSatisfactionOfPatientReporting";
        param += "&panel=" + (containerId == null ? "swSatisfactionTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisSatisfaction.m_show_tbl();
    },
    refreshSatisfactionOfEmployeeReporting: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".refreshSatisfactionOfEmployeeReporting";
        param += "&panel=" + (containerId == null ? "swSatisfactionTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisSatisfaction.m_show_tbl();
    },

    m_show_form: function () {
        $('#swSatisfactionTbl').slideUp('slow');
        $('#swSatisfactionForm').slideDown('slow');
        hmisSatisfaction.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisSatisfaction.f_content_id).jjVal("");
        new jj("#" + hmisSatisfaction.f_title).jjVal("");
        new jj("#" + hmisSatisfaction.f_lang).jjVal("1");
        new jj("#" + hmisSatisfaction.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swSatisfactionTbl').slideDown();
        $('#swSatisfactionForm').slideUp();
        if ($('#swSatisfactionTbl').html() == "") {
//            hmisSatisfaction.m_refresh();
        }
        hmisSatisfaction.tabSizeTbl();
//        $('#swSatisfactionTbl #refreshSatisficationOfPatientReporting').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
//            destroy: true
//        });
//        $('#swSatisfactionTbl #refreshSatisficationOfEmployeeReporting').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
//            destroy: true
//        });
    },
    m_select: function (id) {

        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".select";
//        param += "&communicatedSessions_id=" + id;
        new jj(param).jjAjax2(false);
        hmisSatisfaction.m_show_form();
    },
    /**
     * نمایش فرم های تکمیل شده در ماژول پرستاری 
     * @param {type} containerId
     * @returns {undefined}
     */
//    showMyFormsInNursing: function (containerId) {
//        var param = "";
//        param += "do=" + hmisFormAnswerSets.tableName + ".showMyFormsInNursing";
//        param += "&panel=" + (containerId == null ? "swSatisfactionTbl" : containerId);
//        param += "&jj=1";
//        new jj(param).jjAjax2(false);
//    },

    /**
     * نمایش تکمیل فرم ها
     * @param {type} formId
     * @param {type} containerId
     * @returns {undefined}
     */
    refreshMyAnswersSatisfactionOfEmployeeReporting: function (formId, containerId) {
//        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
//            hmisFormAnswerSets.loadForm(formId);
//            return ;
//        }
        hmisSatisfaction.m_show_form();

        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".refreshMyAnswersSatisfactionOfEmployeeReporting";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swSatisfactionForm" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisFormAnswerSets.m_show_form();
    },
    refreshMyAnswersSatisfactionOfPatientReporting: function (formId, containerId) {
//        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
//            hmisFormAnswerSets.loadForm(formId);
//            return ;
//        }
        hmisSatisfaction.m_show_form();

        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".refreshMyAnswersSatisfactionOfPatientReporting";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swSatisfactionForm" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisFormAnswerSets.m_show_form();
    },
    refreshMyAnswersAfterQuestion: function (formId) {
        var win = window.open('Server?do=FormAnswerSet.add_new&formAnswers_formId='+formId, '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisSatisfaction.refreshMyAnswersSatisfactionOfPatientReporting(' + formId + ');');
    },
    selectMyAnswersAfterQuestion: function (formId, answerSetId) {
        var win = window.open('Server?do=FormAnswerSet.selectToEdit&formAnswers_formId='+ formId + "&id=" + answerSetId + "", '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisSatisfaction.refreshMyAnswersSatisfactionOfPatientReporting(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swSatisfaction').css('height', "auto");
    },
    tabSizeForm: function () {
    },

 /**
     * نمایش آرشیو فرم های تکمیل شده ایمنی فرم رضایت سنجی بیمار
     * @param {type} panel
     * @returns {undefined}
     */
    showAllFormsSatisfactionPatient: function (panel) {
        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".showAllFormsSatisfactionPatient";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsSatisfactionPatientTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisSatisfaction.m_show_tbl();
    },
    showAllFormsSatisfactionEmployee: function (panel) {
        var param = "";
        param += "do=" + hmisSatisfaction.tableName + ".showAllFormsSatisfactionEmployee";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsSatisfactionEmployeeTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisSatisfaction.m_show_tbl();
    },
};
