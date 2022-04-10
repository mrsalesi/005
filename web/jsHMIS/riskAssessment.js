/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ازریابی ریسک
 * @type type
 */


var hmisRiskAssessment = {
    tableName: "RiskAssessment",
    f_id: "id",
    loadForm: function () {
        if ($("#swRiskAssessmentForm").html() == '') {
            $("#swRiskAssessmentForm").load("formHMIS/05nursing.html", null, function () {

            });
        }
    },
    /*ارزیابی ریسک
     * 
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisRiskAssessment.tableName + ".refreshRiskAssessment";
        param += "&panel=" + (containerId == null ? "swRiskAssessmentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swRiskAssessmentTbl').slideUp('slow');
        $('#swRiskAssessmentForm').slideDown('slow');
        hmisRiskAssessment.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisRiskAssessment.f_content_id).jjVal("");
        new jj("#" + hmisRiskAssessment.f_title).jjVal("");
        new jj("#" + hmisRiskAssessment.f_lang).jjVal("1");
        new jj("#" + hmisRiskAssessment.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swRiskAssessmentTbl').slideDown();
        $('#swRiskAssessmentForm').slideUp();
        if ($('#swRiskAssessmentTbl').html() == "") {
            hmisRiskAssessment.m_refresh();
        }
        hmisRiskAssessment.tabSizeTbl();
        $('#swRiskAssessmentTbl #refreshRiskAssessment').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersRiskAssessment: function (formId, containerId) {
        hmisRiskAssessment.m_show_form();
        var param = "";
        param += "do=" + hmisRiskAssessment.tableName + ".refreshMyAnswersRiskAssessment";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swRiskAssessmentForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisRiskAssessment.refreshMyAnswersRiskAssessment(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisRiskAssessment.refreshMyAnswersRiskAssessment(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swRiskAssessment').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
