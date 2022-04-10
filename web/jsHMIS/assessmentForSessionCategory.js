/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ارزیابی کمیته
 * @type type
 */
var hmisAssessmentForSessionCategory = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
//        if ($("#swAssessmentForSessionCategoryForm").html() == '') {
//            $("#swAssessmentForSessionCategoryForm").load("formHMIS/05assessmentForSessionCategory.html", null, function () {
//                $("#cancel_AssessmentForSessionCategory").click(function (e) {
//                    hmisAssessmentForSessionCategory.m_clean();
//                });
//            });
//        }
        hmisAssessmentForSessionCategory.m_show_tbl();
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".refreshAssessmentForSessionCategory";
        param += "&panel=" + (containerId == null ? "swAssessmentForSessionCategoryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swAssessmentForSessionCategoryTbl').hide();
        $('#swAssessmentForSessionCategoryForm').show();
        hmisAssessmentForSessionCategory.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisAssessmentForSessionCategory.f_content_id).jjVal("");
        new jj("#" + hmisAssessmentForSessionCategory.f_title).jjVal("");
        new jj("#" + hmisAssessmentForSessionCategory.f_lang).jjVal("1");
        new jj("#" + hmisAssessmentForSessionCategory.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swAssessmentForSessionCategoryTbl').show();
        $('#swAssessmentForSessionCategoryForm').hide();
        if ($('#swAssessmentForSessionCategoryTbl').html() == "") {
            hmisAssessmentForSessionCategory.m_refresh();
        }
//        hmisAssessmentForSessionCategory.tabSizeTbl();
//        $('#swAssessmentForSessionCategoryTbl #refreshAssessmentForSessionCategory').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
//            destroy: true
//        });
    },
/**
 * 
 * @param {type} formId
 * @param {type} containerId
 * @returns {undefined}ارزیابی در ماژول کمیته
 */
    refreshMyAnswersAssessmentForSession: function (formId, containerId) {
//        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
//            hmisFormAnswerSets.loadForm(formId);
//            return ;
//        }
        hmisAssessmentForSessionCategory.m_show_form();
        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".refreshMyAnswersAssessmentForSession";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swAssessmentForSessionCategoryForm" : containerId);
        param += "&jj=1";

        new jj(param).jjAjax2(false);
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
        new jj("لیست پاسخ های شما تازه سازی بشود؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAssessmentForSessionCategory.refreshMyAnswersAssessmentForSession(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAssessmentForSessionCategory.refreshMyAnswersAssessmentForSession(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swAssessmentForSessionCategoryForm').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
