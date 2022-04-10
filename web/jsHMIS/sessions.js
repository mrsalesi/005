/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisSessions = {
    tableName: "Sessions",
    f_id: "id",
    loadForm: function () {
        if ($("#swSessionsForm").html() == '') {
            $("#swSessionsForm").load("formHMIS/05OneSession.html", null, function () {
                new jj("#sessions_nextSessionDate").jjCalendarWithYearSelector(1397, 1420);
                new jj('#sendFilesSessions').jjAjaxFileUploadByTitleAndMultiFile('#attachFileSessions', 'sessions_file', 'sessions_titleFile1', "#showFilesSessionsDiv");
                new jj('#sendFilesPdfSessions').jjAjaxFileUploadByTitleAndMultiFile('#attachFilePdfSessions', 'sessions_filePdfSessions', 'sessions_titleFilePdf', "#showFilesPdfSessionsDiv");
                new jj('#sendFilesApproved').jjAjaxFileUploadByTitleAndMultiFile('#attachFileApproved', 'approved_file', 'approved_titleFile1', "#showFilesApprovedDiv");
                new jj('#sendFilesApprovedPrevious').jjAjaxFileUploadByTitleAndMultiFile('#attachFileApprovedPrevious', 'approved_file', 'approvedPrevious_titleFile1', "#showFilesApprovedPreviousDiv");

                $('#sessions_agendaSessions').summernote({height: 150, tooltip: false});
                $('#sessions_sessionDescription').summernote({height: 150, tooltip: false});
                var $div = $('<div>');
                $div.load('template/otherDescription.html', function () {
                    $('#sessions_otherDescription').summernote('code', $(this).html());
                });
                hmisRole.getSelectOptionRequierd("#swSessionsForm .roleSelectOption");
                cmsUser.getSelectOption("#swSessionsForm  .userSelectOption");
                cmsContent.m_getGroupsWithUsers("#approved_executorGroupsId");
                $("#approved_executorGroupsId").select2({
                    width: '100%'
                });
                $("#approved_executorRoleId").select2({
                    width: '100%'
                });
                $("#approved_executorUserId").select2({
                    width: '100%'
                });
                $("#approved_trackerId").select2({
                    width: '100%'
                });
                $("#sessions_communicatorRoleId").select2({
                    width: '100%'
                });
            });
        }

    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swSessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    /**
     * ای دی کمیته برای سرچ صورتجلسات
     * @param {type} commettesId
     * @returns {undefined}
     */
    refreshWithSearch: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisSessions.tableName + ".refreshWithSearch";
        param += "&panel=" + (containerId == null ? "refreshSessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&sessions_commettesId=" + new jj("#commettesIdInsearch").jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    /**
     * آرشیو صورت جلسه
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    archivesSessionsRefresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".archivesSessionsRefresh";
        param += "&panel=" + (containerId == null ? "swArchivesSessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swSessionsTbl').hide();
        $('#swSessionsForm').show();
        hmisSessions.tabSizeForm();
    },
    m_clean: function () {
        $('#inputTextSelectorSessionsDiv').html("");
        $('#inputApprovedFileDiv').html("");
        $('#filesDownloadDiv').html("");
        $('#showFilesSessionsDiv').html("");
        $('#showFilesPdfSessionsDiv').html("");
    },

    m_show_tbl: function () {
        $('#swSessionsForm').hide();
        $('#swSessionsTbl').show();
        $('#refreshSessions').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    m_insert: function () {
        var temp2 = "";
        var flag = true;
        if ($("#sessions_communicatorRoleId").val() == "") {
            $("#sessions_communicatorRoleId").addClass("required");
            flag = false;
        } else {
            $("#sessions_communicatorRoleId").removeClass('required');
        }
		
		     
		
        if (flag == true) {
            var temp = $("#inputTextSelectorDiv input");
            for (var i = 0; i < temp.length; i++) {
                temp2 += $(temp[i]).val() + ",";
            }
            var param = "";
            param += "do=" + hmisSessions.tableName + ".insert";
            param += "&" + new jj('#swSessionsForm').jjSerial();
            new jj(param).jjAjax2(false);
            hmisSessions.m_show_tbl();
            hmisSessions.m_clean();
        }

    },
    m_edit: function () {
        var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار نقش ها 
        var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
        var temp5 = $('#audience input:checkbox[class=checkboxAudienceInSide]:checked'); //مدعوین داخل سازمان ای دی یوزر
        var temp2 = "";
        var temp4 = "";
        var temp6 = "";
        var temp7 = "";
        var temp8 = "";
        var temp9 = $('#audience input:checkbox[class=checkboxAudience]:not(:checked)'); //مدعوین سمت دار نقش ها 
        var temp10 = $('#audience input:checkbox[class=checkboxAudienceInSide]:not(:checked)'); //مدعوین داخل سازمان ای دی یوزر
        var temp11 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:not(:checked)'); //مدعوین خارج از سازمان
        var temp12 = "";
        var temp13 = "";
        var temp14 = "";

        for (var i = 0; i < temp9.length; i++) {
            temp12 += $(temp9[i]).attr('name') + ","; //id Role audience
        }

        for (var i = 0; i < temp10.length; i++) {
            temp13 += $(temp10[i]).attr('name') + ","; //id user audience
        }
        for (var i = 0; i < temp11.length; i++) {
            temp14 += $(temp11[i]).val() + "%23A%23"; //
        }
        for (var i = 0; i < temp.length; i++) {
            temp2 += $(temp[i]).attr('name') + ","; //id user audience
        }

        for (var i = 0; i < temp3.length; i++) {
//ایمیل مهمانان خارج از سازمان
            temp4 += $(temp3[i]).val() + "%23A%23";
        }
        for (var i = 0; i < temp5.length; i++) {
// مهمانان داخل  سازمان
            temp6 += $(temp5[i]).attr('name') + ",";
        }

        var flag = true;
        if ($("#sessions_communicatorRoleId").val() == ""|| $("#sessions_communicatorRoleId").val() === null) {
            $("#select2-sessions_communicatorRoleId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-sessions_communicatorRoleId-container").removeClass('required');
        }
		 if ($("#sessions_sessionDescription").val() == "") {
            $(".note-editor").addClass("required");
            flag = false;
        } else {
            $(".note-editor").removeClass('required');  
        }
		  
        if (flag == true) {
            var param = "";
            var sessions_file = $("#swSessionsForm  .sessions_file");
            for (var i = 0; i < sessions_file.length; i++) {
                temp7 += $(sessions_file[i]).val() + ",";
            }
            var sessions_filePdfSessions = $("#swSessionsForm  .sessions_filePdfSessions");
            for (var i = 0; i < sessions_filePdfSessions.length; i++) {
                temp8 += $(sessions_filePdfSessions[i]).val() + ",";
            }
            param += "do=" + hmisSessions.tableName + ".edit";
            param += "&" + new jj('#swSessionsForm').jjSerial();
            param += "&sessions_agenda=" + $('#sessions_agendaSessions').val();
            param += "&sessions_absentRole=" + temp12;
            param += "&sessions_absentUserInSide=" + temp13;
            param += "&sessions_absentOutSide=" + temp14;
            param += "&sessions_audience=" + temp2;
            param += "&sessions_audienceOutSide=" + temp4;
            param += "&sessions_audienceInSide=" + temp6;
            param += "&sessions_file=" + temp7;
            param += "&sessions_filePdfSessions=" + temp8;
            param += "&sessions_sessionDescription=" + $('#sessions_sessionDescription').summernote('code');

//            param += "&id=" + new jj("#hmis_sessions_id").jjVal();
            new jj(param).jjAjax2(false);
            hmisSessions.m_show_tbl();
            hmisSessions.m_clean();  
        }

    },
//    m_validation: function () {// mohamdad
//        if (new jj("#content_title").jjVal().length < 1) {
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
//        return "";
//    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟ با حذف این صورتجلسه همه مصوبات زیر مجموعه صورتجلسات حذف می شود.").jjDialog_YesNo(' hmisSessions.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".delete";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
    },
    m_select: function (id) {
        $("#approvedInSessionsTbl").show();
        $("#insertApproved2").hide();
        $("#approvedPreviousDiv").hide();
        $("#showFilesApprovedDiv").html('');
        $("#showFilesSessionsDiv").html("");
        $("#addNewApproved").show();
        $('#approvedPreviousDiv').hide();
        var param = "";
        param += "do=" + hmisSessions.tableName + ".select";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisApproved.refreshApprovedInSeeeion(id);//نمایش جدول مصوبات در سلکت صورتجلسه
        hmisSessions.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_EN";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal("2");
        hmisSessions.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_Ar";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal("3");
        hmisSessions.m_show_form();
    },
//============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_lang";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisSessions.m_show_form();
    },
//<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
//============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTags";
        param += "&" + new jj('#swSessionsForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".insertTags";
        param += "&" + new jj('#swSessionsForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
//<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swSessions').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swSessions').css('height', "auto");
    },
/////////////////////shiran2////////////
    /**
     * 
     * @param {type} id sessions
     * @returns {undefined}
     */
    confirmationFinalSessions: function (id) {

        var flag = true;
        //اگر ارسال فایل اجباری باشد
//        if($("#showFilesPdfSessionsDiv").html()==="" || $(".sessions_filePdfSessions").val()===""){
//            $("#filePdfLoader").addClass("required");
//            flag=false;
//        }else{
//            
//            $("#filePdfLoader").removeClass("required");
//        }

        if ($("#sessions_communicatorRoleId").val() === "" || $("#sessions_communicatorRoleId").val() === null) {
            $("#select2-sessions_communicatorRoleId-container").addClass("required");
            flag = false;
        } else {
            $("#select2-sessions_communicatorRoleId-container").removeClass('required');
        }
        if (flag == true) {

            var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار
            var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
//            if (temp.size() == 0) {
//                new jj("حضار در جلسه را مشخص نمایید").jjModal("پیام سامانه");
//            } else {
            new jj("آیا از تایید و ارسال صورت جلسه جهت ابلاغ اطمینان دارید؟").jjModal_Yes_No('بعد از این عملیات امکان ویرایش وجود ندارد', "hmisSessions.confirmationFinalSessions_after_question(" + id + ");");

//            }
        }
    },
    confirmationFinalSessions_after_question: function (id) {
        var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار
        var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
        var temp5 = $('#audience input:checkbox[class=checkboxAudienceInSide]:checked'); //مدعوین خارج از سازمان
        var temp2 = "";
        var temp4 = "";
        var temp6 = "";
        var temp7 = "";
        var temp8 = "";
        var temp9 = $('#audience input:checkbox[class=checkboxAudience]:not(:checked)'); //مدعوین سمت دار نقش ها 
        var temp10 = $('#audience input:checkbox[class=checkboxAudienceInSide]:not(:checked)'); //مدعوین داخل سازمان ای دی یوزر
        var temp11 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:not(:checked)'); //مدعوین خارج از سازمان
        var temp12 = "";
        var temp13 = "";
        var temp14 = "";
        for (var i = 0; i < temp.length; i++) {
            temp2 += $(temp[i]).attr('name') + ","; //id user audience
        }
        for (var i = 0; i < temp5.length; i++) {
            temp6 += $(temp5[i]).attr('name') + ","; //id user audience
        }
        for (var i = 0; i < temp3.length; i++) {
//ایمیل مهمانان خارج از سازمان
            temp4 += $(temp3[i]).val() + "%23A%23";
        }

        var sessions_file = $("#swSessionsForm  .sessions_file");
        for (var i = 0; i < sessions_file.length; i++) {
            temp7 += $(sessions_file[i]).val() + ",";
        }
        var sessions_filePdfSessions = $("#swSessionsForm  .sessions_filePdfSessions");
        for (var i = 0; i < sessions_filePdfSessions.length; i++) {
            temp8 += $(sessions_filePdfSessions[i]).val() + ",";
        }
        for (var i = 0; i < temp9.length; i++) {
            temp12 += $(temp9[i]).attr('name') + ","; //id Role audience
        }
        for (var i = 0; i < temp10.length; i++) {
            temp13 += $(temp10[i]).attr('name') + ","; //id user audience
        }
        for (var i = 0; i < temp11.length; i++) {
            temp14 += $(temp11[i]).val() + ","; //
        }
        var param = "";
        param += "&do=" + hmisSessions.tableName + ".confirmationFinalSessions";
        param += "&" + new jj('#swSessionsForm').jjSerial();
        param += "&id=" + id;
        param += "&sessions_audience=" + temp2;
        param += "&sessions_audienceOutSide=" + temp4;
        param += "&sessions_audienceInSide=" + temp6;
        param += "&sessions_file=" + temp7;
        param += "&sessions_filePdfSessions=" + temp8;
        param += "&sessions_absentRole=" + temp12;
        param += "&sessions_absentUserInSide=" + temp13;
        param += "&sessions_absentOutSide=" + temp14;
        param += "&sessions_sessionDescription=" + $('#sessions_sessionDescription').summernote('code');

        new jj(param).jjAjax2(false);
        hmisSessions.m_clean();
    },
    /**
     * ای دی ُجلسات
     * دکمه ارسال به مسئولین اجرا
     * @param {
     type} id
     * @returns {undefined}
     */
//    sendToTrackerAndExecutor: function (id) {
//
//        if (confirm("آیا مصوبات صورتجلسه به مسئولین ابلاغ شود؟")) {
//            hmisSessions.sendToTrackerAndExecutor_after_question(id);
//        } else {
//        }
//
//    },
//    sendToTrackerAndExecutor_after_question: function (id) {
//        var param = "";
//        param += "&id=" + id;
//        param += "&do=" + hmisSessions.tableName + ".sendToTrackerAndExecutor";
//        new jj(param).jjAjax2(false);
//        hmisSessions.m_clean();
//    },
    /**
     * مسئول اجرا یا نقش های مسئول اجرا
     * @param {type} value
     * @returns {undefined}
     */
    executorAction: function (value) {
        if (value == "سمت") {
            $("#approved_executorUserId").attr("disabled", "disabled");
            $("#approved_executorGroupsId").attr("disabled", "disabled");
            $("#approved_executorRoleId").removeAttr("disabled");
            $("#approved_executorUserId").val("");
            $("#approved_executorUserId").select2();
            $("#approved_executorGroupsId").val("");
            $("#approved_executorGroupsId").select2();
        } else if (value == "کاربران") {
            $("#approved_executorRoleId").attr("disabled", "disabled");
            $("#approved_executorGroupsId").attr("disabled", "disabled");
            $("#approved_executorUserId").removeAttr("disabled");
            $("#approved_executorRoleId").val("");
            $("#approved_executorRoleId").select2();
            $("#approved_executorGroupsId").val("");
            $("#approved_executorGroupsId").select2();

        } else if (value == "گروه") {
            $("#approved_executorRoleId").attr("disabled", "disabled");
            $("#approved_executorUserId").attr("disabled", "disabled");
            $("#approved_executorGroupsId").removeAttr("disabled");
            $("#approved_executorRoleId").val("");
            $("#approved_executorRoleId").select2();
            $("#approved_executorUserId").val("");
            $("#approved_executorUserId").select2();
        }

    },
//    m_remove: function (idUpload, id) {
//        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisSessions.removeFile(" + idUpload + "," + id + ");");
//    },
//    removeFile: function (idUpload, sessionsId) {
//
//        var param = "";
//        param += "do=" + hmisSessions.tableName + ".removeFile";
//        param += "&upload_id=" + idUpload;
//        param += "&hmis_sessions_id=" + sessionsId;
//        new jj(param).jjAjax2(false);
////        hmisSessions.m_show_tbl();
////        hmisSessions.m_clean();
//    },
};
