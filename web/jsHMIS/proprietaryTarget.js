/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisProprietaryTarget = {
    tableName: "ProprietaryTarget",

    f_id: "id",
    m_add_new: function () {
        new jj("&do=" + hmisProprietaryTarget.tableName + ".add_new").jjAjax2(false);
        $("#proprietaryForm").slideDown();
        $("#proprietaryTable").slideUp();
        $("#proprietaryNewForm").slideDown();
        new jj('#proprietaryForm').jjFormClean();
        $("#hmis_proprietarytarget_id").val("");
        $("#indicatorUrlAdd").html("");

    },

    m_insert: function () {
        var temp = $('#proprietaryForm #proprietaryNewForm input.indicatorUrlDiv');
        var temp1 = "";
        for (var i = 0; i < temp.length; i++) {
            temp1 += $(temp[i]).attr('value') + ","; //نام چک باکس عدد مورد نظر
        }
        var param = "";
        param += "do=" + hmisProprietaryTarget.tableName + ".insert";
//        param += "&" + new jj('#proprietaryForm').jjSerial();
        param += "&hmis_strategic_id=" + new jj("#hmis_strategic_id").jjVal();
        param += "&proprietaryTarget_title=" + new jj("#proprietaryTarget_title").jjVal();
        param += "&hmis_totaltarget_id=" + new jj("#hmis_totaltarget_id").jjVal();
        param += "&proprietaryTarget_indicate=" + encodeURIComponent(temp1);
        new jj(param).jjAjax2(false);

    },
    m_edit: function () {
        var param = "";
        var temp = $('#proprietaryForm #proprietaryNewForm input.indicatorUrlDiv');
        var temp1 = "";
        for (var i = 0; i < temp.length; i++) {
            temp1 += $(temp[i]).attr('value') + ","; //نام چک باکس عدد مورد نظر
        }
        var param = "";
        param += "&hmis_strategic_id=" + new jj("#hmis_strategic_id").jjVal();
        param += "&proprietaryTarget_title=" + new jj("#proprietaryTarget_title").jjVal();
        param += "&hmis_totaltarget_id=" + new jj("#hmis_totaltarget_id").jjVal();
        param += "&hmis_proprietarytarget_id=" + new jj("#hmis_proprietarytarget_id").jjVal();
        param += "&proprietaryTarget_indicate=" + encodeURIComponent(temp1);
        param += "&do=" + hmisProprietaryTarget.tableName + ".edit";
        new jj(param).jjAjax2(false);
    },

    m_delete: function (id) {
//        if (confirm("")) {
//            hmisProprietaryTarget.m_delete_after_question(id);
//        } else {
//        }
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("", "hmisProprietaryTarget.m_delete_after_question(" + id + ")");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisProprietaryTarget.tableName + ".delete";
        param += "&" + hmisProprietaryTarget.f_id + "=" + (id == null ? "" : id);
        param += "&hmis_strategic_id=" + $("#hmis_strategic_id").val();
        param += "&hmis_totaltarget_id=" + $("#hmis_totaltarget_id").val();
        new jj(param).jjAjax2(false);
    },

    m_select: function (id) {
        $('#proprietaryForm').slideDown();
        $("#proprietaryNewForm").slideDown();
        $("#refreshProprietary").slideUp();
        var param = "";
        param += "do=" + hmisProprietaryTarget.tableName + ".select";
        param += "&" + hmisProprietaryTarget.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        $("html, body").delay(1000).animate({scrollTop: $('#proprietaryForm').offset().top}, 800);
    },
    /**
     * اهداف اختصاصی مربود به یک هدف کلی
     * @param {type} totalTargetId
     * @returns {undefined}  */
    selectOptionProprietaryTarget: function (totalTargetId) {
        var param = "";
        param += "&totalTargetId=" + totalTargetId;
        $("#plans_strategic").val("");
        $("#plans_strategic").select2({
            width: '100%'
        });
        param += "&do=" + hmisProprietaryTarget.tableName + ".selectOptionProprietaryTarget";
        new jj(param).jjAjax2(false);

    },
//    getOptionProprietaryTarget: function (totalTargetId) {
//        var param = "";
//        param += "&totalTargetId=" + totalTargetId;
//        $("#plans_strategic").val("");
//        $("#plans_strategic").select2({
//            width: '100%'
//        });
//        param += "&do=" + hmisProprietaryTarget.tableName + ".selectOptionProprietaryTarget";
//        new jj(param).jjAjax2(false);
//
//    },
    /**
     * 
     * @param {type} id =totaltargetId
     * @param {type} panel proprietary
     * @returns {undefined}
     */
    getOptionForFilter: function (id, panel) {
        panel = (panel == null ? "plans_minorGoal" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        var param = "";
        param += "&do=" + hmisProprietaryTarget.tableName + ".getOptionForFilter";
        param += "&panel=" + (panel == null ? "plans_minorGoal" : panel);
        param += "&id=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    }
    ,

    /**
     * با انتخاب برنامه استراتژیک اهداف در سلکت ها قرار می گیرند
     * @returns {undefined}
     * ای دی استراتژیک
     */
//    getProprietary: function (strategicId) {
//        var param = "";
//        param += "&hmis_strategic_id=" + strategicId;
//        param += "&do=" + hmisProprietaryTarget.tableName + ".getProprietary";
//        new jj(param).jjAjax2(false);
//
//    },
};

//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

