/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @type type
 */


var hmisAmozeshBePersonel = {
    tableName: "Amozesh",
    f_id: "id",
    loadForm: function () {
        if ($("#swAmozeshBePersonelForm").html() == '') {
            $("#swAmozeshBePersonelForm").load("", null, function () {

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
        param += "do=" + hmisAmozeshBePersonel.tableName + ".refreshAmozeshBePersonel";
        param += "&panel=" + (containerId == null ? "swAmozeshBePersonelTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swAmozeshBePersonelTbl').slideUp('slow');
        $('#swAmozeshBePersonelForm').slideDown('slow');
        hmisAmozeshBePersonel.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisAmozeshBePersonel.f_content_id).jjVal("");
        new jj("#" + hmisAmozeshBePersonel.f_title).jjVal("");
        new jj("#" + hmisAmozeshBePersonel.f_lang).jjVal("1");
        new jj("#" + hmisAmozeshBePersonel.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swAmozeshBePersonelTbl').slideDown();
        $('#swAmozeshBePersonelForm').slideUp();
        if ($('#swAmozeshBePersonelTbl').html() == "") {
            hmisAmozeshBePersonel.m_refresh();
        }
        hmisAmozeshBePersonel.tabSizeTbl();
        $('#swAmozeshBePersonelTbl #refreshAmozeshBePersonel').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersAmozeshBePersonel: function (formId, containerId) {
        hmisAmozeshBePersonel.m_show_form();
        var param = "";
        param += "do=" + hmisAmozeshBePersonel.tableName + ".refreshMyAnswersAmozeshBePersonel";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swAmozeshBePersonelForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAmozeshBePersonel.refreshMyAnswersAmozeshBePersonel(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAmozeshBePersonel.refreshMyAnswersAmozeshBePersonel(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swAmozeshBePersonel').css('height', "auto");
    },
    tabSizeForm: function () {
    },
  
};  
var hmisAmozeshBeBimar = {
    tableName: "Amozesh",
    f_id: "id",
    loadForm: function () {
        if ($("#swAmozeshBeBimarForm").html() == '') {
            $("#swAmozeshBeBimarForm").load("", null, function () {

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
        param += "do=" + hmisAmozeshBeBimar.tableName + ".refreshAmozeshBeBimar";
        param += "&panel=" + (containerId == null ? "swAmozeshBeBimarTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swAmozeshBeBimarTbl').slideUp('slow');
        $('#swAmozeshBeBimarForm').slideDown('slow');
        hmisAmozeshBeBimar.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisAmozeshBeBimar.f_content_id).jjVal("");
        new jj("#" + hmisAmozeshBeBimar.f_title).jjVal("");
        new jj("#" + hmisAmozeshBeBimar.f_lang).jjVal("1");
        new jj("#" + hmisAmozeshBeBimar.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swAmozeshBeBimarTbl').slideDown();
        $('#swAmozeshBeBimarForm').slideUp();
        if ($('#swAmozeshBeBimarTbl').html() == "") {
            hmisAmozeshBeBimar.m_refresh();
        }
        hmisAmozeshBeBimar.tabSizeTbl();
        $('#swAmozeshBeBimarTbl #refreshAmozeshBeBimar').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    refreshMyAnswersAmozeshBeBimar: function (formId, containerId) {
        hmisAmozeshBeBimar.m_show_form();
        var param = "";
        param += "do=" + hmisAmozeshBeBimar.tableName + ".refreshMyAnswersAmozeshBeBimar";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swAmozeshBeBimarForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAmozeshBeBimar.refreshMyAnswersAmozeshBeBimar(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisAmozeshBeBimar.refreshMyAnswersAmozeshBeBimar(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swAmozeshBeBimar').css('height', "auto");
    },
    tabSizeForm: function () {
    },
  
};  
