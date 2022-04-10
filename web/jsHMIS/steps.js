/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisSteps = {
    tableName: "Steps",
    f_id: "id",

    loadForm: function () {
        if ($("#swStepsForm").html() == '') {
            $("#swStepsForm").load("formHMIS/04Steps.html", null, function () {
                new jj("#startDate").jjCalendarWithYearSelector(1370, 1420);
                new jj("#endDate").jjCalendarWithYearSelector(1370, 1420);
                new jj('#sendFiles').jjAjaxFileUploadTitleUploadFiles('#attachFileDocumentary', '#documentary_attachFileDocumentary', 'documentary_titleFile1', '#documentary_attachFileTitle1', "#");
                new jj('#sendExecutorFilesSteps').jjAjaxFileUploadByTitleAndMultiFile('#attachExecutorFilesSteps', 'steps_executorFiles', 'steps_titleFileExecutor', "#showFilesExecutorDiv");
                new jj('#sendTrackerFilesSteps').jjAjaxFileUploadByTitleAndMultiFile('#attachTrackerFilesSteps', 'steps_trackerFiles', 'steps_titleFileTracker', "#showFilesTrackerDiv");

                $("#cancel_Steps").click(function (e) {
                    hmisSteps.m_show_tbl();
                    hmisSteps.m_clean();
                });


            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swStepsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swStepsTbl').hide();
        $('#swStepsForm').show();
        hmisSteps.tabSizeForm();
    },
    m_clean: function () {
        $('#stepsForm input:text').val("");
        $("#showFilesStepsDiv").html("");
        $("#downloadStepsFileDiv").html("");
    },
    m_add_new: function () {
        new jj("&do=" + hmisSteps.tableName + ".add_new").jjAjax2(false);
        $('#stepsForm').slideDown();
        $('#tblSteps').slideUp();
        $("#steps_executorGroupsId").val("");
        $("#steps_executorGroupsId").select2();
        $("#steps_executorRoleId").val("");
        $("#steps_executorRoleId").select2();
        $("#steps_executorUserId").val("");
        $("input[type=number]").val("");
        $("#steps_executorUserId").select2();
        $("#steps_trackerId").val("");
        $("#steps_trackerId").select2();
        $("#steps_description").val("");
        $("#steps_description").summernote('');
//        hmisSteps.m_show_form();
        hmisSteps.m_clean();

    },
    m_show_tbl: function () {
        $('#swStepsTbl').show();
        $('#refreshSteps').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        $('#swStepsForm').hide();
    },
    m_insert: function () {
        var flag = true;

        if ($("#steps_trackerId").val() == null) {
            $("#select2-steps_trackerId-container").addClass("required");
             new jj(" مسئول پیگیری را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-steps_trackerId-container").removeClass('required');
        }
        if ($("#steps_startDate").val() == "") {
            $("#steps_startDate").addClass("required");
             new jj(" تاریخ شروع گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_startDate").removeClass('required');
        }
        if ($("#steps_endDate").val() == "") {
            $("#steps_endDate").addClass("required");
             new jj(" تاریخ پایان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_endDate").removeClass('required');
        }
        if ($("#steps_cost").val() == "") {
            $("#steps_cost").addClass("required");
             new jj(" هزینه گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_cost").removeClass('required');
        }
        if ($("#steps_percent").val() == "") {
            $("#steps_percent").addClass("required");
             new jj("  درصد گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_percent").removeClass('required');
        }
        if ($("#steps_title").val() == "") {
            $("#steps_title").addClass("required");
             new jj(" عنوان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_title").removeClass('required');
        }
        if ($("#steps_executorUserId").val() == null && $("#steps_executorRoleId").val() == null&&$("#steps_executorGroupsId").val() == null) {
            $("#select2-steps_executorUserId-container").addClass("required");
            $("#select2-steps_executorRoleId-container").addClass("required");
            $("#select2-steps_executorGroupsId-container").addClass("required");
             new jj(" مسئول اجرا را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-steps_executorUserId-container").removeClass('required');
            $("#select2-steps_executorRoleId-container").removeClass('required');
            $("#select2-steps_executorGroupsId-container").removeClass('required');
        }

        if (flag == true) {

            var param = "";
            var temp = "";
            var steps_files = $("#stepsForm  .steps_files");
            for (var i = 0; i < steps_files.length; i++) {
                temp += $(steps_files[i]).val() + ",";
            }
            param += "&steps_files=" + temp;
            param += "&do=" + hmisSteps.tableName + ".insert";
            param += "&" + new jj('#stepsForm').jjSerial();
            param += "&hmis_plans_id=" + new jj("#hmis_plans_id").jjVal();
//            param += "&steps_description=" + $('#steps_description').summernote('code');
            param += "&steps_description=" + encodeURIComponent($('#steps_description').summernote('code'));

            new jj(param).jjAjax2(false);
            hmisSteps.m_show_tbl();
            hmisSteps.m_clean();
//        $("html, body").delay(1000).animate({scrollTop: $('#tblSteps').offset().top}, 800);

        }

    },
    m_edit: function () {
        var param = "";
        var temp = "";
        var temp1 = "";
        var steps_executorFiles = $("#swStepsForm  .steps_executorFiles");
        for (var i = 0; i < steps_executorFiles.length; i++) {
            temp += $(steps_executorFiles[i]).val() + ",";
        }
        var steps_trackerFiles = $("#swStepsForm  .steps_trackerFiles");
        for (var i = 0; i < steps_trackerFiles.length; i++) {
            temp1 += $(steps_trackerFiles[i]).val() + ",";
        }
        param += "&do=" + hmisSteps.tableName + ".edit";
        param += "&" + new jj('#swStepsForm').jjSerial();
        param += "&steps_executorFiles=" + temp;
        param += "&steps_trackerFiles=" + temp1;
        new jj(param).jjAjax2(false);
        hmisSteps.m_show_tbl();
        hmisSteps.m_clean();
    },
    editStepInPlansForAssess: function () {
        var flag = true;

//        if ($("#stepsInAssess_trackerId").val() == null || $("#stepsInAssess_trackerId").val() == "") {
//            $("#select2-stepsInAssess_trackerId-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-stepsInAssess_trackerId-container").removeClass('required');
//        }
        if ($("#stepsInAssess_startDate").val() == "") {
            $("#stepsInAssess_startDate").addClass("required");
             new jj(" تاریخ شروع گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#stepsInAssess_startDate").removeClass('required');
        }
        if ($("#stepsInAssess_endDate").val() == "") {
            $("#stepsInAssess_endDate").addClass("required");
             new jj(" تاریخ پایان را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#stepsInAssess_endDate").removeClass('required');
        }
        if ($("#stepsInAssess_cost").val() == "") {
            $("#stepsInAssess_cost").addClass("required");
             new jj(" هزینه گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#stepsInAssess_cost").removeClass('required');
        }
        if ($("#stepsInAssess_percent").val() == "") {
            $("#stepsInAssess_percent").addClass("required");
             new jj(" درصدگام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#stepsInAssess_percent").removeClass('required');
        }
        if ($("#stepsInAssess_title").val() == "") {
            $("#stepsInAssess_title").addClass("required");
             new jj(" عنوان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#stepsInAssess_title").removeClass('required');
        }
//        if ($("#stepsInAssess_executorUserId").val() == null && $("#steps_executorRoleId").val() == null) {
//            $("#select2-stepsInAssess_executorUserId-container").addClass("required");
//            $("#select2-stepsInAssess_executorRoleId-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-stepsInAssess_executorUserId-container").removeClass('required');
//            $("#select2-stepsInAssess_executorRoleId-container").removeClass('required');
//        }

        if (flag == true) {
            var temp = "";
            var steps_files = $("#stepsFormInAssess  .steps_files");
            for (var i = 0; i < steps_files.length; i++) {
                temp += $(steps_files[i]).val() + ",";
            }
            var param = "";
            param += "&do=" + hmisSteps.tableName + ".editStepInPlansForAssess";
            param += "&" + new jj('#stepsFormInAssess').jjSerial();
            param += "&hmis_steps_id=" + new jj('#hmis_stepsInAssess_id').jjVal();
            param += "&hmis_plans_id=" + new jj('#hmis_plansForAssess_id').jjVal();
            param += "&steps_files=" + temp;
//        param += "&approved_description=" + $('#approvedPrevious_description').summernote('code');

            new jj(param).jjAjax2(false);
            hmisSteps.m_show_tbl();
            hmisSteps.m_clean();
            $("#inputFileApprovedPreviousDiv").html("");
        }


//        } else {
//            new jj(valid).jjDialog();
//        }
    },
    /**
     * ای دی گام 
     * مرحله ابلاغ گام توسط مدیر
     * @param {type} id
     * @returns {undefined}
     */
    communicatedSteps: function (id) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".communicatedSteps";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    communicateStepsAll: function (id) {
        new jj("آیا از ابلاغ همه گام ها اطمینان دارید").jjModal_Yes_No("هشدار", "hmisSteps.communicateStepsAll_after_question(" + id + " );");

    },
    communicateStepsAll_after_question: function (id) {
        var param = "";
        param += "&hmis_plans_id=" + id;
        param += "&do=" + hmisSteps.tableName + ".communicateStepsAll";
        new jj(param).jjAjax2(false);
        hmisSteps.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("هشدار", "hmisSteps.m_delete_after_question("+id+");");

    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".delete";
        param += "&hmis_plans_id=" + new jj("#hmis_plans_id").jjVal();
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSteps.m_show_tbl();
        hmisSteps.m_clean();
    },
    m_select: function (id) {
        $('#showFilesExecutorDiv').html("");
        $('#showFilesTrackerDiv').html("");
        var param = "";
        param += "do=" + hmisSteps.tableName + ".selectStepsInPlansForAssess";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSteps.m_show_form();
    },
    /**
     * سلکت درتب 
     * پایش برنامه ها
     * @param {type} id
     * @returns {undefined}
     */
    selectStepsInPlansForAssess: function (id) {
        $("#showFilesStepsInAssessDiv").html("");
        $("#stepsFormInAssess").slideDown();
        $("html, body").delay(1000).animate({scrollTop: $('#stepsFormInAssess').offset().top}, 800);
        var param = "";
        param += "do=" + hmisSteps.tableName + ".selectStepsInPlansForAssess";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSteps.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".add_EN";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSteps.f_parent).jjVal(id);
        new jj("#" + hmisSteps.f_lang).jjVal("2");
        hmisSteps.m_show_form();
    },

    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".add_Ar";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSteps.f_parent).jjVal(id);
        new jj("#" + hmisSteps.f_lang).jjVal("3");
        hmisSteps.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".add_lang";
        param += "&" + hmisSteps.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSteps.f_parent).jjVal(id);
        new jj("#" + hmisSteps.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisSteps.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".searchTags";
        param += "&" + new jj('#swStepsForm').jjSerial();
        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisSteps.tableName + ".insertTags";
        param += "&" + new jj('#swStepsForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swSteps').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swSteps').css('height', 'auto');
    }

};

//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}
   
