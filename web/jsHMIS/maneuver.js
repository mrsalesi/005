/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ازریابی ریسک
 * @type type
 */


var hmisManeuver = {
    tableName: "Maneuver",
    f_id: "id",
    loadForm: function () {
        if ($("#swManeuverForm").html() == '') {
            $("#swManeuverForm").load("formHMIS/05nursing.html", null, function () {

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
        param += "do=" + hmisManeuver.tableName + ".refreshManeuver";
        param += "&panel=" + (containerId == null ? "swManeuverTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swManeuverTbl').slideUp('slow');
        $('#swManeuverForm').slideDown('slow');
        hmisManeuver.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisManeuver.f_content_id).jjVal("");
        new jj("#" + hmisManeuver.f_title).jjVal("");
        new jj("#" + hmisManeuver.f_lang).jjVal("1");
        new jj("#" + hmisManeuver.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swManeuverTbl').slideDown();
        $('#swManeuverForm').slideUp();
        if ($('#swManeuverTbl').html() == "") {
            hmisManeuver.m_refresh();
        }
        hmisManeuver.tabSizeTbl();
        $('#swManeuverTbl #refreshManeuver').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersManeuver: function (formId, containerId) {
        hmisManeuver.m_show_form();
        var param = "";
        param += "do=" + hmisManeuver.tableName + ".refreshMyAnswersManeuver";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swManeuverForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisManeuver.refreshMyAnswersManeuver(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisManeuver.refreshMyAnswersManeuver(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swManeuver').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
