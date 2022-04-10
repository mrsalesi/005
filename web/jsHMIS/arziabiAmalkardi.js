/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ازریابی عمکلکردی
 * @type type
 */


var hmisArziabiAmalkardi = {
    tableName: "ArziabiAmalkardi",
    f_id: "id",
    loadForm: function () {
        if ($("#swArziabiAmalkardiForm").html() == '') {
            $("#swArziabiAmalkardiForm").load("", null, function () {

            });      
        }
    },
    /*
     * 
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisArziabiAmalkardi.tableName + ".refreshArziabiAmalkardi";
        param += "&panel=" + (containerId == null ? "swArziabiAmalkardiTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swArziabiAmalkardiTbl').slideUp('slow');
        $('#swArziabiAmalkardiForm').slideDown('slow');
        hmisArziabiAmalkardi.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisArziabiAmalkardi.f_content_id).jjVal("");
        new jj("#" + hmisArziabiAmalkardi.f_title).jjVal("");
        new jj("#" + hmisArziabiAmalkardi.f_lang).jjVal("1");
        new jj("#" + hmisArziabiAmalkardi.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swArziabiAmalkardiTbl').slideDown();
        $('#swArziabiAmalkardiForm').slideUp();
        if ($('#swArziabiAmalkardiTbl').html() == "") {
            hmisArziabiAmalkardi.m_refresh();
        }
        hmisArziabiAmalkardi.tabSizeTbl();
        $('#swArziabiAmalkardiTbl #refreshArziabiAmalkardi').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersArziabiAmalkardi: function (formId, containerId) {
        hmisArziabiAmalkardi.m_show_form();
        var param = "";
        param += "do=" + hmisArziabiAmalkardi.tableName + ".refreshMyAnswersArziabiAmalkardi";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swArziabiAmalkardiForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisArziabiAmalkardi.refreshMyAnswersArziabiAmalkardi(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisArziabiAmalkardi.refreshMyAnswersArziabiAmalkardi(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swArziabiAmalkardi').css('height', "auto");
    },
    tabSizeForm: function () {
    },
  
};  
