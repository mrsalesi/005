/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisMySteps = {
    tableName: "",
    f_id: "id",

    loadForm: function () {
        if ($("#swMyStepsForm").html() == '') {
            $("#swMyStepsForm").load("formHMIS/04MySteps.html", null, function () {
//                new jj('#sendExecutorFilesApproved').jjAjaxFileUploadByTitleAndMultiFile('#attachExecutorFilesApproved', 'approved_filesExecutor', 'approved_titleFileExecutor', "#showFilesExecutorDiv");

                new jj('#sendExecutorFileMySteps').jjAjaxFileUploadByTitleAndMultiFile('#attachExecutorFileMySteps', '#steps_executorFiles', 'steps_titleExecutorFileMySteps', "#showExecutorFileMyStepsDiv");
                new jj('#sendTrackerFileMySteps').jjAjaxFileUploadByTitleAndMultiFile('#attachTrackerFileMySteps', '#steps_trackerFiles', 'steps_titleTrackerFileMySteps', "#showTrackerFileMyStepsDiv");

                $("#statusLogMySteps").slideUp();
                $("#cancel_MySteps").click(function (e) {
                    hmisMySteps.m_clean();
                    hmisMySteps.m_show_tbl();
                });


            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".refreshMySteps";
        param += "&panel=" + (containerId == null ? "swMyStepsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swMyStepsTbl').hide();
        $('#swMyStepsForm').show();
        hmisMySteps.tabSizeForm();
    },
    m_clean: function () {

        $("#showFilesDiv").html("");
        $("#filesDownloadMyStepsDiv").html("");
    },
    m_add_new: function () {
        new jj("&do=" + hmisMySteps.tableName + ".add_new").jjAjax2(false);
        $('#stepsForm').slideDown();
        hmisMySteps.m_show_form();
        hmisMySteps.m_clean();

    },
    m_show_tbl: function () {
        $('#swMyStepsTbl').show();
        $('#swMyStepsForm').hide();
         $('#refreshMySteps').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {

        var param = "";
        param += "do=" + hmisMySteps.tableName + ".insert";
        param += "&" + new jj('#stepsForm').jjSerial();
        param += "&hmis_plans_id=" + new jj("#hmis_plans_id").jjVal();
        new jj(param).jjAjax2(false);
        hmisMySteps.m_show_tbl();
        hmisMySteps.m_clean();


    },
    m_edit: function () {

        var param = "";
        var temp = "";
        var steps_executorFiles = $(".steps_executorFiles");
        for (var i = 0; i < steps_executorFiles.length; i++) {
            temp += $(steps_executorFiles[i]).val() + ",";
        }
        param += "&steps_executorFiles=" + temp;
        var temp1 = "";
        var steps_trackerFiles = $(".steps_trackerFiles");
        for (var i = 0; i < steps_trackerFiles.length; i++) {
            temp1 += $(steps_trackerFiles[i]).val() + ",";
        }
        param += "&steps_trackerFiles=" + temp1;
        param += "&do=" + hmisSteps.tableName + ".editMySteps";
        param += "&" + new jj('#swMyStepsForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisMySteps.m_show_tbl();
        hmisMySteps.m_clean();
    },
    m_delete: function (id) {
                new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisApproved.hmisMySteps.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".delete";
        param += "&" + hmisMySteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisMySteps.m_show_tbl();
        hmisMySteps.m_clean();
    },
    m_select: function (id) {
        $('#showExecutorFileMyStepsDiv').html("");
        $('#showTrackerFileMyStepsDiv').html("");
        $('#MySteps_executorFiles').val("");
        var param = "";
        param += "do=" + hmisSteps.tableName + ".selectMySteps";
        param += "&" + hmisMySteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#showFilesMySteps2Div').html("");
        $('#showFilesMySteps1Div').html("");
        $('#user_pic').html("");
        hmisMySteps.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".add_EN";
        param += "&" + hmisMySteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisMySteps.f_parent).jjVal(id);
        new jj("#" + hmisMySteps.f_lang).jjVal("2");
        hmisMySteps.m_show_form();
    },

    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".add_Ar";
        param += "&" + hmisMySteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisMySteps.f_parent).jjVal(id);
        new jj("#" + hmisMySteps.f_lang).jjVal("3");
        hmisMySteps.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".add_lang";
        param += "&" + hmisMySteps.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisMySteps.f_parent).jjVal(id);
        new jj("#" + hmisMySteps.f_lang).jjVal(langId);
        hmisMySteps.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".searchTags";
        param += "&" + new jj('#swMyStepsForm').jjSerial();
        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisMySteps.tableName + ".insertTags";
        param += "&" + new jj('#swMyStepsForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swMySteps').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swMySteps').css('height', 'auto');
    }

};

//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

