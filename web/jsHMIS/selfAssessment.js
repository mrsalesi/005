/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ازریابی ریسک
 * @type type
 */


var hmisSelfAssessment = {
    tableName: "SelfAssessment",
    f_id: "id",
    loadForm: function () {
        if ($("#swSelfAssessmentForm").html() == '') {
            $("#swSelfAssessmentForm").load("formHMIS/05nursing.html", null, function () {

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
        param += "do=" + hmisSelfAssessment.tableName + ".refreshSelfAssessment";
        param += "&panel=" + (containerId == null ? "swSelfAssessmentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swSelfAssessmentTbl').slideUp('slow');
        $('#swSelfAssessmentForm').slideDown('slow');
        hmisSelfAssessment.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisSelfAssessment.f_content_id).jjVal("");
        new jj("#" + hmisSelfAssessment.f_title).jjVal("");
        new jj("#" + hmisSelfAssessment.f_lang).jjVal("1");
        new jj("#" + hmisSelfAssessment.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swSelfAssessmentTbl').slideDown();
        $('#swSelfAssessmentForm').slideUp();
        if ($('#swSelfAssessmentTbl').html() == "") {
            hmisSelfAssessment.m_refresh();
        }
        hmisSelfAssessment.tabSizeTbl();
        $('#swSelfAssessmentTbl #refreshSelfAssessment').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersSelfAssessment: function (formId, containerId) {
        hmisSelfAssessment.m_show_form();
        var param = "";
        param += "do=" + hmisSelfAssessment.tableName + ".refreshMyAnswersSelfAssessment";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swSelfAssessmentForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisSelfAssessment.refreshMyAnswersSelfAssessment(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisSelfAssessment.refreshMyAnswersSelfAssessment(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swSelfAssessment').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
