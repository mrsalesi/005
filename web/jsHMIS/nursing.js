/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisNursing = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swNursingForm").html() == '') {
            $("#swNursingForm").load("formHMIS/05nursing.html", null, function () {
//                $("#cancel_AssessmentForSessionCategory").click(function (e) {
//                    hmisNursing.m_clean();
//                    hmisNursing.m_show_tbl();
//                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisForms.tableName + ".refreshNursing";
        param += "&panel=" + (containerId == null ? "swNursingTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisNursing.m_show_tbl();
    },

    m_show_form: function () {
        $('#swNursingTbl').slideUp('slow');
        $('#swNursingForm').slideDown('slow');
        hmisNursing.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisNursing.f_content_id).jjVal("");
        new jj("#" + hmisNursing.f_title).jjVal("");
        new jj("#" + hmisNursing.f_lang).jjVal("1");
        new jj("#" + hmisNursing.f_parent).jjVal("0");
    },

    m_show_tbl: function () {
        $('#swNursingTbl').slideDown();
        $('#swNursingForm').slideUp();
        if ($('#swNursingTbl').html() == "") {
            hmisNursing.m_refresh();
        }
        hmisNursing.tabSizeTbl();
        $('#swNursingTbl #refreshNursing').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_select: function (id) {

        var param = "";
        param += "do=" + hmisNursing.tableName + ".select";
//        param += "&communicatedSessions_id=" + id;
        new jj(param).jjAjax2(false);
        hmisNursing.m_show_form();
    },
    /**
     * نمایش فرم های تکمیل شده در ماژول پرستاری 
     * @param {type} containerId
     * @returns {undefined}
     */
//    showMyFormsInNursing: function (containerId) {
//        var param = "";
//        param += "do=" + hmisFormAnswerSets.tableName + ".showMyFormsInNursing";
//        param += "&panel=" + (containerId == null ? "swNursingTbl" : containerId);
//        param += "&jj=1";
//        new jj(param).jjAjax2(false);
//    },

    refreshMyAnswersInNursing: function (formId, containerId) {
//        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
//            hmisFormAnswerSets.loadForm(formId);
//            return ;
//        }
        hmisNursing.m_show_form();

        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".refreshMyAnswersInNursing";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swNursingForm" : containerId);
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisNursing.refreshMyAnswersInNursing(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisNursing.refreshMyAnswersInNursing(' + formId + ');');
    },
    tabSizeTbl: function () {
        $('#swNursing').css('height', "auto");
    },
    tabSizeForm: function () {
    },

};
