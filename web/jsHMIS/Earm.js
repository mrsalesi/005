/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * مدیریت خطاو خطر
 * @type type
 */


var hmisEarmAccidentsReporting = {
    tableName: "Earm",
    f_id: "id",
    loadForm: function () {
        if ($("#swEarmAccidentsReportingForm").html() == '') {
            $("#swEarmAccidentsReportingForm").load("formHMIS/05nursing.html", null, function () {

            });
        }
    },
    /* گزارش دهی حوادت
     * 
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisEarmAccidentsReporting.tableName + ".refreshEarmAccidentsReporting";
        param += "&panel=" + (containerId == null ? "swEarmAccidentsReportingTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swEarmAccidentsReportingTbl').slideUp('slow');
        $('#swEarmAccidentsReportingForm').slideDown('slow');
        hmisEarmAccidentsReporting.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisEarmAccidentsReporting.f_content_id).jjVal("");
        new jj("#" + hmisEarmAccidentsReporting.f_title).jjVal("");
        new jj("#" + hmisEarmAccidentsReporting.f_lang).jjVal("1");
        new jj("#" + hmisEarmAccidentsReporting.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swEarmAccidentsReportingTbl').slideDown();
        $('#swEarmAccidentsReportingForm').slideUp();
        $('#swAllMyAnswerSetsEARMAccidentsTbl').hide();
        if ($('#swEarmAccidentsReportingTbl').html() == "") {
            hmisEarmAccidentsReporting.m_refresh();
        }
        hmisEarmAccidentsReporting.tabSizeTbl();
        $('#swEarmAccidentsReportingTbl #refreshEarmAccidentsReporting').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    /**
     * نمایش آرشیو فرم های تکمیل شده گزارش  حوادث 
     * @param {type} panel
     * @returns {undefined}
     */
    showAllForms: function (panel) {
        var param = "";
        param += "do=" + hmisEarmAccidentsReporting.tableName + ".showAllFormsAccidents";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsEARMAccidentsTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEarmAccidentsReporting.m_show_tbl();
    },
    refreshMyAnswersEarmAccidentsReporting: function (formId, containerId) {
        hmisEarmAccidentsReporting.m_show_form();
        var param = "";
        param += "do=" + hmisEarmAccidentsReporting.tableName + ".refreshMyAnswersEarmAccidentsReporting";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swEarmAccidentsReportingForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmAccidentsReporting.refreshMyAnswersEarmAccidentsReporting(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmAccidentsReporting.refreshMyAnswersEarmAccidentsReporting(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swEarmAccidentsReporting').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
var hmisEarmRiskReporting = {
    tableName: "Earm",
    f_id: "id",
    loadForm: function () {
        if ($("#swEarmRiskReportingForm").html() == '') {
            $("#swEarmRiskReportingForm").load("formHMIS/05nursing.html", null, function () {

            });
        }
    },
    /* گزارش دهی حوادت
     * 
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisEarmRiskReporting.tableName + ".refreshEarmRiskReporting";
        param += "&panel=" + (containerId == null ? "swEarmRiskReportingTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swEarmRiskReportingTbl').slideUp('slow');
        $('#swEarmRiskReportingForm').slideDown('slow');
        hmisEarmRiskReporting.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisEarmRiskReporting.f_content_id).jjVal("");
        new jj("#" + hmisEarmRiskReporting.f_title).jjVal("");
        new jj("#" + hmisEarmRiskReporting.f_lang).jjVal("1");
        new jj("#" + hmisEarmRiskReporting.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swEarmRiskReportingTbl').slideDown();
        $('#swEarmRiskReportingForm').slideUp();
        if ($('#swEarmRiskReportingTbl').html() == "") {
            hmisEarmRiskReporting.m_refresh();
        }
        hmisEarmRiskReporting.tabSizeTbl();
        $('#swEarmRiskReportingTbl #refreshEarmRiskReporting').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    /**
     * نمایش آرشیو فرم های تکمیل شده گزارش خطاو خطر 
     * @param {type} panel
     * @returns {undefined}
     */
    showAllForms: function (panel) {
        var param = "";
        param += "do=" + hmisEarmRiskReporting.tableName + ".showAllFormsRisk";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsEARMRiskTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEarmRiskReporting.m_show_tbl();
    },
    refreshMyAnswersEarmRiskReporting: function (formId, containerId) {
        hmisEarmRiskReporting.m_show_form();
        var param = "";
        param += "do=" + hmisEarmRiskReporting.tableName + ".refreshMyAnswersEarmRiskReporting";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swEarmRiskReportingForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmRiskReporting.refreshMyAnswersEarmRiskReporting(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmRiskReporting.refreshMyAnswersEarmRiskReporting(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swEarmRiskReporting').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
var hmisEarmPatientSafety = {
    tableName: "Earm",
    f_id: "id",
    loadForm: function () {
        if ($("#swEarmPatientSafetyForm").html() == '') {
            $("#swEarmPatientSafetyForm").load("formHMIS/05nursing.html", null, function () {

            });
        }
    },
    /* ایمنی بیمار
     * 
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisEarmPatientSafety.tableName + ".refreshEarmPatientSafety";
        param += "&panel=" + (containerId == null ? "swEarmPatientSafetyTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swEarmPatientSafetyTbl').slideUp('slow');
        $('#swEarmPatientSafetyForm').slideDown('slow');
        hmisEarmPatientSafety.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisEarmPatientSafety.f_content_id).jjVal("");
        new jj("#" + hmisEarmPatientSafety.f_title).jjVal("");
        new jj("#" + hmisEarmPatientSafety.f_lang).jjVal("1");
        new jj("#" + hmisEarmPatientSafety.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swAllMyAnswerSetsEARMPatientTbl').hide();
        $('#swEarmPatientSafetyTbl').slideDown();
        $('#swEarmPatientSafetyForm').slideUp();
        if ($('#swEarmPatientSafetyTbl').html() == "") {
            hmisEarmPatientSafety.m_refresh();
        }
        hmisEarmPatientSafety.tabSizeTbl();
        $('#swEarmPatientSafetyTbl #refreshEarmPatientSafety').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    /**
     * نمایش آرشیو فرم های تکمیل شده ایمنی بیمار
     * @param {type} panel
     * @returns {undefined}
     */
    showAllForms: function (panel) {
        var param = "";
        param += "do=" + hmisEarmPatientSafety.tableName + ".showAllFormsPatient";
        param += "&panel=" + (panel == null ? "swAllAnswerSetsEARMPatientTbl" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEarmPatientSafety.m_show_tbl();
    },
    refreshMyAnswersEarmPatientSafety: function (formId, containerId) {
        hmisEarmPatientSafety.m_show_form();
        var param = "";
        param += "do=" + hmisEarmPatientSafety.tableName + ".refreshMyAnswersEarmPatientSafety";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swEarmPatientSafetyForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmPatientSafety.refreshMyAnswersEarmPatientSafety(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEarmPatientSafety.refreshMyAnswersEarmPatientSafety(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swEarmPatientSafety').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
