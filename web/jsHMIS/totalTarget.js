/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisTotalTarget = {
    tableName: "TotalTarget",

    f_id: "id",

    m_refresh: function (strategicId, panel) {
        var param = "";
        param += "do=" + hmisTotalTarget.tableName + ".refresh";
        param += "&panel=" + (panel == null ? "targetTbl" : panel);
        param += "&totalTarget_strategicId=" + strategicId;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_add_new: function () {
        new jj("&do=" + hmisTotalTarget.tableName + ".add_new").jjAjax2(false);
        $("#targetForm").slideDown();
        $("#targetTbl").slideUp();
        $("#proprietaryForm").hide();
        new jj('#targetForm').jjFormClean();
        $("#hmis_totaltarget_id").val("");

    },

    m_insert: function () {
        var param = "";
        param += "do=" + hmisTotalTarget.tableName + ".insert";
        param += "&" + new jj('#targetForm').jjSerial();
        param += "&hmis_strategic_id=" + new jj("#hmis_strategic_id").jjVal();
        new jj(param).jjAjax2(false);

    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisTotalTarget.tableName + ".edit";
        param += "&hmis_strategic_id=" + new jj("#hmis_strategic_id").jjVal();
        param += "&" + new jj('#targetForm').jjSerial();
        new jj(param).jjAjax2(false);

    },

    m_delete: function (id) {
        new jj("آیا از حذف هدف کلی اطمینان دارید؟").jjModal_Yes_No("پیام هشدار", "hmisTotalTarget.m_delete_after_question(" + id + ")");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisTotalTarget.tableName + ".delete";
        param += "&" + hmisTotalTarget.f_id + "=" + (id == null ? "" : id);
        param += "&hmis_strategic_id=" + $("#hmis_strategic_id").val();
        new jj(param).jjAjax2(false);
    },

    m_select: function (id) {
        $('#targetTbl').slideUp();
        $('#targetForm').slideDown();
        $('#proprietaryForm').slideDown();
        var param = "";
        param += "do=" + hmisTotalTarget.tableName + ".select";
        param += "&" + hmisTotalTarget.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        $("html, body").delay(1000).animate({scrollTop: $('#targetForm').offset().top}, 800);
    },
    /**
     * با انتخاب برنامه استراتژیک اهداف در سلکت ها قرار می گیرند
     * @returns {undefined}
     * ای دی استراتژیک
     */
//    getTarget: function () {
//        var param = "";
//        param += "&do=" + hmisTotalTarget.tableName + ".getTarget";
//        new jj(param).jjAjax2(false);
//
//    },
    getOptionForFilter: function (panel) {
        var param = "";
        panel = (panel == null ? "plans_hugeGoal" : panel);
        param += "&do=" + hmisTotalTarget.tableName + ".getOptionForFilter";
        param += "&panel=" + (panel == null ? "plans_hugeGoal" : panel);
        param += "&jj=1";
        new jj(param).jjAjax2(false);

    },
    /**
     * برای برنامه استراتژیک
     * @param {type} strategicId
     * @returns {undefined}
     */
    getTargetForStrategic: function (strategicId) {
        var param = "";

        param += "&do=" + hmisTotalTarget.tableName + ".getTargetForStrategic";
        param += "&totalTarget_strategicId=" + strategicId;
        new jj(param).jjAjax2(false);

    },
};

//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

