/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisApproved = {
    tableName: "Approved",
    f_id: "id",

    loadForm: function () {
        if ($("#swApprovedForm").html() == '') {
            $("#swApprovedForm").load("formHMIS/05approved.html", null, function () {
                new jj('#sendExecutorFilesApproved').jjAjaxFileUploadByTitleAndMultiFile('#attachExecutorFilesApproved', 'approved_filesExecutor', 'approved_titleFileExecutor', "#showFilesExecutorDiv");
                new jj('#sendTrackerFilesApproved').jjAjaxFileUploadByTitleAndMultiFile('#attachTrackerFilesApproved', 'approved_filesTracker', 'approved_titleFileTracker', "#showFilesTrackerDiv");

                $("#cancel_Approved").click(function (e) {
                    hmisApproved.m_clean();
                    hmisApproved.m_show_tbl();
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swApprovedTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//            hmisApproved.m_show_tbl();
    },
    refreshApprovedInSeeeion: function (sessionsId, panel) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refreshApprovedInSeeeion";
        param += "&panel=" + (panel == null ? "approvedInSessionsTbl" : panel);
        param += "&approved_sessionsId=" + sessionsId;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    refreshMyApproved: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refreshMyApproved";
        param += "&panel=" + (containerId == null ? "swApprovedTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    refreshMyCommettesApproved: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refreshMyCommettesApproved";
        param += "&panel=" + (containerId == null ? "swApprovedTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swApprovedTbl').slideUp();
        $('#swApprovedForm').slideDown();
        hmisApproved.tabSizeForm();

    },
    m_clean: function () {

        new jj("#insertApproved2 input:text").jjFormClean();
        new jj("#approved_trackerId").jjVal("");
        new jj("#approved_executorUserId").jjVal("");
        new jj("#approved_executorRoleId").jjVal("");
        $("#inputTextSelectorDiv").html("");
        $("#inputDownloadDiv").html("");
        $("#showFilesApprovedDiv").html("");


    },
    m_add_new: function () {
        var param = "";
        $('#insertApproved2 input:text').val("");
        $("#approved_executorUserId").attr("disabled", "disabled");
        $("#approved_executorGroupsId").attr("disabled", "disabled");
        $("#approved_executorRoleId").attr("checked", "checked");
        $("#approved_executorRoleId").val("");
        $("#approved_executorRoleId").select2();
        $("#approved_executorGroupsId").val("");
        $("#approved_executorGroupsId").select2();
        $("#approved_executorUserId").val("");
        $("#approved_executorUserId").select2();
        $("#approved_trackerId").val("");
        $("#approved_trackerId").select2();
        $('#approved_description').summernote('code', '');
//        $('#approved_description').html(escape($('#approved_description').summernote('code')));
        $('#approved_startDate').val("");
        $('#approved_endDate').val("");
        $("#sessions_titleFile1").val("");
        $("#fileApprovedDiv").html("");
        $("#user_pic").val("");
        $("#inputApprovedFileDiv").html("");
        new jj("#approved_startDate").jjCalendarWithYearSelector(1340, 1420);
        new jj("#approved_endDate").jjCalendarWithYearSelector(1340, 1420);
        param += "&hmis_sessions_id=" + new jj("#hmis_sessions_id").jjVal();
        param += "&do=" + hmisApproved.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisApproved.m_clean();
    },
    m_show_tbl: function () {
        $('#swApprovedTbl').slideDown();
        $('#swApprovedForm').slideUp();
        $('#refreshApproved').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    m_insert: function () {
        var temp = "";
        var temp1 = "";
        var temp2 = "";
        var execturesRoleId = $('#approved_executorRoleId').val();
        var execturesUserId = $('#approved_executorUserId').val();
        var execturesGroupsId = $('#approved_executorGroupsId').val();
        var flag = true;
        if ($("#approved_trackerId").val() == null) {
            $("#select2-approved_trackerId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-approved_trackerId-container").removeClass('required');
        }
        if ($("#approved_startDate").val() == "") {
            $("#approved_startDate").addClass("required");
            flag = false;
        } else {
            $("#approved_startDate").removeClass('required');
        }
        if ($("#approved_endDate").val() == "") {
            $("#approved_endDate").addClass("required");
            flag = false;
        } else {
            $("#approved_endDate").removeClass('required');
        }
        if ($("#approved_executorUserId").val() == null && $("#approved_executorRoleId").val() == null && $("#approved_executorGroupsId").val() == null) {
            $("#select2-approved_executorUserId-container").addClass("required");
            $("#select2-approved_executorRoleId-container").addClass("required");
            $("#select2-approved_executorGroupsId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-approved_executorUserId-container").removeClass('required');
            $("#select2-approved_executorRoleId-container").removeClass('required');
            $("#select2-approved_executorGroupsId-container").removeClass('required');
        }

        if (flag == true) {

            var param = "";
            param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
            if ($("input:radio[name=responsibleExecutor]:checked").val() == "سمت") {
                for (var i = 0; i < execturesRoleId.length; i++) {
                    temp2 += execturesRoleId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
                }
                param += "&approved_executorRoleId=" + temp2;
//                param += "&approved_executorUserId=";
//                param += "&approved_executorGroupsId=";

            } else if ($("input:radio[name=responsibleExecutor]:checked").val() == "کاربران") {
                for (var i = 0; i < execturesUserId.length; i++) {
                    temp1 += execturesUserId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
                }
//                param += "&approved_executorRoleId=";
//                param += "&approved_executorGroupsId=";
                param += "&approved_executorUserId=" + temp1;

            } else if ($("input:radio[name=responsibleExecutor]:checked").val() == "گروه") {
                for (var i = 0; i < execturesGroupsId.length; i++) {
                    temp1 += execturesGroupsId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
                }
//                param += "&approved_executorRoleId=";
//                param += "&approved_executorUserId=";
                param += "&approved_executorGroupsId=" + temp1;
            }

            var approved_file = $("#insertApproved2  .approved_file");
            for (var i = 0; i < approved_file.length; i++) {
                temp += $(approved_file[i]).val() + ",";
            }
            param += "&approved_file=" + temp;
            param += "&approved_description=" + $('#approved_description').summernote('code');
            param += "&do=" + hmisApproved.tableName + ".insert";
            param += "&" + new jj('#insertApproved2').jjSerial();
            new jj(param).jjAjax2(false);
            hmisApproved.m_show_tbl();
        }
    },
    m_edit: function () {
        var param = "";
        var temp = "";
        var temp1 = "";
        var approved_filesExecutor = $("#swApprovedForm  .approved_filesExecutor");
        for (var i = 0; i < approved_filesExecutor.length; i++) {
            temp += $(approved_filesExecutor[i]).val() + ",";
        }
        var approved_filesTracker = $("#swApprovedForm  .approved_filesTracker");
        for (var i = 0; i < approved_filesTracker.length; i++) {
            temp1 += $(approved_filesTracker[i]).val() + ",";
        }
        param += "&do=" + hmisApproved.tableName + ".edit";
        param += "&" + new jj('#swApprovedForm').jjSerial();
//        param += "&approved_description=" + $('#approved_description').summernote('code');

        param += "&id=" + $('#hmis_approved_id').val();
        param += "&approved_filesExecutor=" + temp;
        param += "&approved_filesTracker=" + temp1;


        new jj(param).jjAjax2(false);
        hmisApproved.m_show_tbl();
        hmisApproved.m_clean();
    },
    editInSessions: function () {

        var param = "";
        var temp = "";
        var temp2 = "";
        var temp1 = "";
        var flag = true;
        if ($("#approved_trackerId").val() == null) {
            $("#select2-approved_trackerId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-approved_trackerId-container").removeClass('required');
        }
        if ($("#approved_startDate").val() == "") {
            $("#approved_startDate").addClass("required");
            flag = false;
        } else {
            $("#approved_startDate").removeClass('required');
        }
        if ($("#approved_endDate").val() == "") {
            $("#approved_endDate").addClass("required");
            flag = false;
        } else {
            $("#approved_endDate").removeClass('required');
        }
        if ($("#approved_executorUserId").val() == null && $("#approved_executorRoleId").val() == null && $("#approved_executorGroupsId").val() == null) {
            $("#select2-approved_executorUserId-container").addClass("required");
            $("#select2-approved_executorRoleId-container").addClass("required");
            $("#select2-approved_executorGroupsId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-approved_executorUserId-container").removeClass('required');
            $("#select2-approved_executorRoleId-container").removeClass('required');
            $("#select2-approved_executorGroupsId-container").removeClass('required');
        }

        /////////////////////////////////////////////
        var execturesRoleId = $('#approved_executorRoleId').val();
        var execturesUserId = $('#approved_executorUserId').val();
        var execturesGroupsId = $('#approved_executorGroupsId').val();
        if ($("input:radio[name=responsibleExecutor]:checked").val() == "سمت") {
            for (var i = 0; i < execturesRoleId.length; i++) {
                temp2 += execturesRoleId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&approved_executorRoleId=" + temp2;
        } else if ($("input:radio[name=responsibleExecutor]:checked").val() == "کاربران") {
            for (var i = 0; i < execturesUserId.length; i++) {
                temp1 += execturesUserId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&approved_executorUserId=" + temp1;

        } else if ($("input:radio[name=responsibleExecutor]:checked").val() == "کاربران") {
            for (var i = 0; i < execturesGroupsId.length; i++) {
                temp1 += execturesGroupsId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&approved_executorGroupsId=" + temp1;
        }


        if (flag == true) {
            /////////////////////////////////////////////
            var approved_file = $("#insertApproved2  .approved_file");
            for (var i = 0; i < approved_file.length; i++) {
                temp += $(approved_file[i]).val() + ",";
            }
            param += "&do=" + hmisApproved.tableName + ".editInSessions";
            param += "&approved_description=" + $('#approved_description').summernote('code');
            param += "&" + new jj('#insertApproved2').jjSerial();
            param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
            param += "&approved_file=" + temp;

            new jj(param).jjAjax2(false);
            hmisApproved.m_show_tbl();
            hmisApproved.m_clean();
        }
    },
    /**
     * ویرایش مصوبه در قسمت ابلاغ مصوبه
     * @returns {undefined}
     */
    editApprovedInCommunicate: function () {

        var temp = "";
        var temp2 = "";
        var temp1 = "";

        /////////////////////////////////////////////
        var execturesRoleId = $('#communicatedApproved_executorRoleId').val();
        var execturesUserId = $('#communicatedApproved_executorUserId').val();
        var execturesGroupsId = $('#communicatedApproved_executorGroupsId').val();
        if ($("input:radio[name=roleExecutor]:checked").val() == "سمت") {
            for (var i = 0; i < execturesRoleId.length; i++) {
                temp2 += execturesRoleId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&approved_executorRoleId=" + temp2;

        } else if ($("input:radio[name=roleExecutor]:checked").val() == "کاربران") {
            for (var i = 0; i < execturesUserId.length; i++) {
                temp1 += execturesUserId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&approved_executorUserId=" + temp1;
        } else if ($("input:radio[name=roleExecutor]:checked").val() == "کاربران") {
            for (var i = 0; i < execturesGroupsId.length; i++) {
                temp1 += execturesGroupsId[i] + "%23A%23"; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
            param += "&communicatedApproved_executorGroupsId=" + temp1;
        }



        /////////////////////////////////////////////
        var param = "";
        var approved_file = $("#swCommunicatedSessionsForm  .approved_file");
        for (var i = 0; i < approved_file.length; i++) {
            temp += $(approved_file[i]).val() + ",";
        }
        param += "&do=" + hmisApproved.tableName + ".editApprovedInCommunicate";
        param += "&communicatedSessions_id=" + new jj('#communicatedSessions_id').jjVal();
        param += "&approved_file=" + temp;
        param += "&approved_description=" + $('#communicatedApproved_description').summernote('code');
        param += "&" + new jj('#insertApprovedCommunicated').jjSerial();

        new jj(param).jjAjax2(false);
        hmisApproved.m_show_tbl();
        hmisApproved.m_clean();

    },
    editApprovedPrevious: function () {
        var temp = "";
        var approved_file = $("#approvedPreviousDiv  .approved_file");
        for (var i = 0; i < approved_file.length; i++) {
            temp += $(approved_file[i]).val() + ",";
        }
        var param = "";
        param += "&do=" + hmisApproved.tableName + ".editApprovedPrevious";
        param += "&approved_description=" + $('#approvedPrevious_description').summernote('code');
        param += "&" + new jj('#approvedPreviousDiv').jjSerial();
        param += "&approvedId=" + new jj('#approvedPrevious_id').jjVal();
        param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
        param += "&approved_file=" + temp;

        new jj(param).jjAjax2(false);
        hmisApproved.m_show_tbl();
        hmisApproved.m_clean();
        $("#inputFileApprovedPreviousDiv").html("");



//        } else {
//            new jj(valid).jjDialog();
//        }
    },
//    m_validation: function () {// mohamdad
//        if (new jj("#content_title").jjVal().length < 1) {
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
//        return "";
//    },
    /**
     * ای دی مصوبات
     * @param {type} id
     * @returns {undefined}
     */

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisApproved.confirmationFinalApproved_after_question(" + id + ");");
    },
    confirmationFinalApproved_after_question: function (id) {
        var param = "";
//        alert(new jj('#hmis_sessions_id').jjVal());
        param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
        param += "&do=" + hmisApproved.tableName + ".delete";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisApproved.m_show_tbl();
        hmisApproved.m_clean();
    },
    deleteApprovedInCommunicate: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisApproved.confirmationFinalApprovedInCommunicate_after_question(" + id + ");");
    },
    confirmationFinalApprovedInCommunicate_after_question: function (id) {
        var param = "";
//        alert(new jj('#hmis_sessions_id').jjVal());
        param += "&communicatedSessions_id=" + new jj('#communicatedSessions_id').jjVal();
        param += "&do=" + hmisApproved.tableName + ".deleteApprovedInCommunicate";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisApproved.m_show_tbl();
        hmisApproved.m_clean();
    },
    m_select: function (id) {
//        $('#showFilesExecutorDiv').html("");
//        $('#showFilesTrackerDiv').html("");
//        $('#inputFileApprovedDiv').html("");
        new jj("#startDate").jjCalendarWithYearSelector(1340, 1420);
        new jj("#endDate").jjCalendarWithYearSelector(1340, 1420);
        var param = "";
        param += "do=" + hmisApproved.tableName + ".select";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisApproved.m_show_form();
    },
    /**
     *  انتخاب مصوبات در قسمت جلسات برای بررسی مصوبات قبلی این کمیته
     * @param {type} id
     * ای دی مصوبه انتخاب شده
     * @returns {undefined}
     */
    selectApprovedPrevious: function (id) {
        $("html, body").delay(1000).animate({scrollTop: $('#approvedPreviousDiv').offset().top}, 800);
        $("#approvedPrevious_description").summernote({height: 150, tooltip: false});

        $('#showFilesApprovedPreviousDiv').html("");
        $('#approvedPreviousDiv').slideDown();
        new jj("#approvedPrevious_startDate").jjCalendarWithYearSelector(1340, 1420);
        new jj("#approvedPrevious_endDate").jjCalendarWithYearSelector(1340, 1420);
        var param = "";
        param += "do=" + hmisApproved.tableName + ".selectApprovedPrevious";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $("#inputFileApprovedPreviousDiv").html("");
    },
    selectInSessions: function (id) {
        $("#approved_description").summernote({height: 150, tooltip: false});
        $("#inputApprovedFileDiv").html('');
        new jj("#approved_startDate").jjCalendarWithYearSelector(1340, 1420);
        new jj("#approved_endDate").jjCalendarWithYearSelector(1340, 1420);
        $("html, body").delay(1000).animate({scrollTop: $('#insertApproved2').offset().top}, 800);
        var param = "";
        param += "do=" + hmisApproved.tableName + ".selectInSessions";
        param += "&approved_id=" + id;
        param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
        new jj(param).jjAjax2(false);
    },
    /**
     * سلکت در جلسات ابلاغ شده من
     * @param {type} id
     * @returns {undefined}
     */
    selectInCommunicatedSessions: function (id) {
        hmisRole.getSelectOptionRequierd("#swCommunicatedSessionsForm #communicatedApproved_executorRoleId");
        hmisRole.getSelectOptionRequierd("#swCommunicatedSessionsForm #communicatedApproved_trackerId");
        cmsUser.getSelectOption("#swCommunicatedSessionsForm #communicatedApproved_executorUserId");
        cmsContent.m_getGroupsWithUsers("#communicatedApproved_executorGroupsId");

        new jj("#communicatedApproved_startDate").jjCalendarWithYearSelector(1340, 1420);
        new jj("#communicatedApproved_endDate").jjCalendarWithYearSelector(1340, 1420);
        $("html, body").delay(1000).animate({scrollTop: $('#insertApprovedCommunicated').offset().top}, 800);
        var param = "";
        param += "&do=" + hmisApproved.tableName + ".selectInCommunicatedSessions";
        param += "&communicatedApproved_id=" + id;
        param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
        new jj(param).jjAjax2(false);

        $("#communicatedApproved_description").summernote({height: 150, tooltip: false});
//        hmisApproved.m_show_form();
    },
    /**
     * ابلاغ مصوبات وایجاد رکورد به تعداد مسئولین اجرا
     * @param {type} id
     * @returns {undefined}
     */
    communicatedApproved: function (id) {
        var param = "";
//        param += "&hmis_sessions_id=" + new jj('#hmis_sessions_id').jjVal();
        param += "do=" + hmisApproved.tableName + ".communicatedApproved";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        hmisApproved.m_show_form();
    },

    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".add_EN";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisApproved.f_parent).jjVal(id);
        new jj("#" + hmisApproved.f_lang).jjVal("2");
        hmisApproved.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".add_Ar";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisApproved.f_parent).jjVal(id);
        new jj("#" + hmisApproved.f_lang).jjVal("3");
        hmisApproved.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".add_lang";
        param += "&" + hmisApproved.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisApproved.f_parent).jjVal(id);
        new jj("#" + hmisApproved.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisApproved.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".searchTags";
        param += "&" + new jj('#swApprovedForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".insertTags";
        param += "&" + new jj('#swApprovedForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swApproved').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swApproved').css('height', 'auto');
    },

    /////////////////////shiran////////////

    /**
     * زمانی که پیشنهادی به دبیر کمیته داده می شود می تواند آن را رد کند یا اینکه آن را به عنوان مصوبه بپذیرد
     * @param {type} id
     * @param {type} sessionsId
     * @returns {undefined}
     */
    actionIconAdd: function (id, sessionsId) {

        var param = "";
        param += "do=" + hmisApproved.tableName + ".addApproved";
        param += "&hmis_approved_id=" + id;
        param += "&sessionsId=" + sessionsId;
        new jj(param).jjAjax2(false);

    },
    /**
     * رد پیشنهاد برنامه عملیاتی
     * @param {type} id
     * @returns {undefined}
     */
    actionIconReject: function (id, sessionsId) {

        var param = "";
        param += "do=" + hmisApproved.tableName + ".rejectApproved";
        param += "&hmis_approved_id=" + id;
        param += "&sessionsId=" + sessionsId;

        new jj(param).jjAjax2(false);

    },

};