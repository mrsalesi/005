/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisStrategy = {
    tableName: "Strategy",

    f_id: "id",
 m_refresh: function (strategicId, panel) {
        var param = "";
        param += "do=" + hmisStrategy.tableName + ".refresh";
        param += "&panel=" + (panel == null ? "strategyTbl" : panel);
        param += "&strategy_strategicId=" + strategicId;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_add_new: function () {
        $('#strategyTbl').slideUp('slow');
        $('#proprietaryTargetSelect').val("");
          $("#proprietaryTargetSelect").select2({
        width: '100%'
        });
        $('#strategyNewForm').slideDown('slow');
        new jj("&do=Strategy.add_new").jjAjax2(false);
        var strategicId = $("#hmis_strategic_id").val();
        hmisTotalTarget.getTargetForStrategic(strategicId); //برای نمایش سلکت اهداف اختصاصی
    },

    m_insert: function () {
        var param = "";
        param += "&id=" + new jj("#hmis_strategic_id").jjVal();
        if (!$("#proprietaryTargetSelect").val() == "") {
            param += "&do=Strategy.insert";
            param += "&" + new jj("#strategyNewForm").jjSerial();
            param += "&strategy_proprietaryTargetId=" + new jj("#proprietaryTargetSelect").jjVal();

            new jj(param).jjAjax2(false);
        } else {
            new jj("هدف اختصاصی را مشخص نمایید.").jjModal("پیام سامانه");
        }
    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisStrategy.tableName + ".edit";
        param += "&id=" + new jj("#hmis_strategy_id").jjVal();
        param += "&strategy_indicatorUrl=" + new jj('#strategy_indicatorUrl').jjVal();
        param += "&strategy_proprietaryTargetId=" + $('#proprietaryTargetSelect').val();
        param += "&" + new jj("#strategyNewForm").jjSerial();
        new jj(param).jjAjax2(false);

    },

    m_delete: function (id) {
//        if (confirm("")) {
//            hmisStrategy.m_delete_after_question(id);
//        } else {
//        }
        new jj("آیا از حذف استراتژی اطمینان دارید؟").jjModal_Yes_No("پیام هشدار", "hmisStrategy.m_delete_after_question(" + id + ")");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisStrategy.tableName + ".delete";
        param += "&" + hmisStrategy.f_id + "=" + (id == null ? "" : id);
        param += "&id=" + new jj("#hmis_strategy_id").jjVal();
        new jj(param).jjAjax2(false);
    },

    m_select: function (id) {
        var param = "";
        $('#strategyTbl').slideUp('slow');
        $('#strategyNewForm').slideDown('slow');
        param += "&id=" + id;
//        if (!$("#proprietaryTargetSelect").val() == "") {
        param += "&do=Strategy.select";
        param += "&strategy_proprietaryTargetId=" + new jj("#proprietaryTargetSelect").jjVal();
        new jj(param).jjAjax2(false);
//        } else {
//            new jj("هدف اختصاصی را مشخص نمایید.").jjModal("پیام سامانه");
//        }
    },
//     selectOptionStrategyPlan: function (strategyId) {
//        var param = "";
//        param += "&strategyId=" + strategyId;
//        param += "&do=" + hmisStrategy.tableName + ".selectOptionStrategyPlan";
//        new jj(param).jjAjax2(false);
//
//    },
    /**
     * استراتژی های مربوط به هدف اختصاصی
     * @param {type} proprietaryTargetId
     * @returns {undefined}
     */
    getOptionForFilter: function (id, panel) {
        var param = "";
        panel = (panel == null ? "plans_strategic" : panel);
        if (!new jj(id).jjIsDigit()) {
            $(panel).html("<option value=''></option>");
            return;
        }
        param += "&panel=" + (panel == null ? "plans_strategic" : panel);
        param += "&proprietaryTargetId=" + id;
        param += "&do=" + hmisStrategy.tableName + ".getOptionForFilter";
        new jj(param).jjAjax2(false);

    },
};


//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

