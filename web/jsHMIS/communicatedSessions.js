/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisCommunicatedSessions = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swCommunicatedSessionsForm").html() == '') {
            $("#swCommunicatedSessionsForm").load("formHMIS/05SessionCommunicated.html", null, function () {

                $("#cancel_CommunicatedSessions").click(function (e) {
                    hmisCommunicatedSessions.m_clean();
                    hmisCommunicatedSessions.m_show_tbl();
                });

                new jj('#sendFilesApprovedCommunicatedSessions').jjAjaxFileUploadByTitleAndMultiFile('#attachFileApprovedCommunicatedSessions', 'approved_file', 'approved_titleFileCommunicatedSessions', "#showFilesApprovedCommunicatedSessionsDiv");

                 cmsContent.m_getGroupsWithUsers("#communicatedApproved_executorGroupsId");
                $("#communicatedApproved_executorGroupsId").select2({
                    width: '100%'
                });
                $("#communicatedApproved_executorRoleId").select2({
                    width: '100%'
                });
                $("#communicatedApproved_executorUserId").select2({
                    width: '100%'
                });
                $("#communicatedApproved_trackerId").select2({
                    width: '100%'
                });
                $("#communicatedSessions_otherDescription").summernote({height: 150, tooltip: false});
                $("#communicatedSessions_agendaSessions").summernote({height: 150, tooltip: false});
            });
        }

    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".refreshCommunicatedSessions";
        param += "&panel=" + (containerId == null ? "swCommunicatedSessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swCommunicatedSessionsTbl').slideUp('slow');
        $('#swCommunicatedSessionsForm').slideDown('slow');
        hmisCommunicatedSessions.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisCommunicatedSessions.f_content_id).jjVal("");
        new jj("#" + hmisCommunicatedSessions.f_title).jjVal("");
        new jj("#" + hmisCommunicatedSessions.f_lang).jjVal("1");
        new jj("#" + hmisCommunicatedSessions.f_parent).jjVal("0");
        $("#showFilesApprovedDiv").html("");


    },

    m_show_tbl: function () {
        $('#swCommunicatedSessionsTbl').show();
        $('#swCommunicatedSessionsForm').hide();
        if ($('#swCommunicatedSessionsTbl').html() == "") {
            hmisCommunicatedSessions.m_refresh();
        }
        hmisCommunicatedSessions.tabSizeTbl();
        $('#swCommunicatedSessionsTbl #refreshCommunicatedSessions').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_select: function (id) {
        $("#addNewApproved").hide();
        $('#insertApprovedCommunicated').slideUp();
        $('#communicatedApprovedTbl').slideDown();
        var param = "";
        param += "do=" + hmisSessions.tableName + ".selectCommunicatedSessions";
        param += "&communicatedSessions_id=" + id;
        new jj(param).jjAjax2(false);
        hmisCommunicatedSessions.m_show_form();
//        $("#insertApproved2").hide();
    },
    /**
     * ای دی ُجلسات
     * دکمه ارسال به مسئولین اجرا
     * @param {type} id
     * @returns {undefined}
     */
    sendToCommunicator: function (id) {
        new jj("آیا از ابلاغ صورتجلسه اطمینان دارید؟").jjModal_Yes_No('بعد از این عملیات امکان ویرایش وجود ندارد', " hmisCommunicatedSessions.sendToCommunicator_after_question(" + id + ");");
    },
    /**
     * ای دی جلسه
     * @param {type} id
     * @returns {undefined}
     */
    sendToCommunicator_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + hmisSessions.tableName + ".sendToCommunicator";
        new jj(param).jjAjax2(false);
        hmisCommunicatedSessions.m_clean();
    },
    /**
     * رد کردن صورت جلسه
     * ای دی جلسه
     * @param {type} id
     * @returns {undefined}
     */
    ignore: function (id) {
        new jj("آیا از رد کردن صورتجلسه اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisCommunicatedSessions.ignore_after_question(" + id + ");");
    },
    ignore_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + hmisSessions.tableName + ".ignore";
        new jj(param).jjAjax2(false);
        hmisCommunicatedSessions.m_clean();
    },
    communicateApprovedAll: function (id) {
        new jj("آیا از ابلاغ همه مصوبات اطمینان دارید").jjModal_Yes_No("هشدار", " hmisCommunicatedSessions.communicateApprovedAll_after_question(" + id + " );")


    },
    communicateApprovedAll_after_question: function (id) {
        var param = "";
        param += "&hmis_sessions_id=" + id;
        param += "&do=" + hmisApproved.tableName + ".communicateApprovedAll";
        new jj(param).jjAjax2(false);
        hmisCommunicatedSessions.m_clean();
    },
    /**
     * ای دی جلسات 
     * ای دی فرد امضا کننده
     * امضای کردن صورتجلسه
     * @returns {undefined}
     */
//    actionSigners: function (sessionsId, userId) {
//alert(';;;;;;;;;;;;;;;;;;;;;;;;;;');
//        var param = "";
//        param += "do=" + hmisSessions.tableName + ".actionSigners";
//        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
//        param += "&hmis_sessions_id="+sessionsId;
//        param += "&userId="+userId;
//        new jj(param).jjAjax2(false);
//        hmisSessions.m_show_form();
//    },

    tabSizeTbl: function () {
        $('#swCommunicatedSessions').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swCommunicatedSessions').css('height', 'auto');
    },
    executorAction: function (value) {
        if (value == "سمت") {
            $("#communicatedApproved_executorUserId").attr("disabled", "disabled");
            $("#communicatedApproved_executorGroupsId").attr("disabled", "disabled");
            $("#communicatedApproved_executorRoleId").removeAttr("disabled");
            $("#communicatedApproved_executorUserId").select2();
            $("#communicatedApproved_executorUserId").val("");  
            $("#communicatedApproved_executorGroupsId").select2();
            $("#communicatedApproved_executorGroupsId").val("");
//            $('input:radio[id=userExecutorCommunicated]').attr('checked', false);
//            $('input:radio[id=roleExecutorCommunicated]').attr('checked', true);
        } else if (value == "کاربران") {
            $("#communicatedApproved_executorRoleId").attr("disabled", "disabled");
            $("#communicatedApproved_executorGroupsId").attr("disabled", "disabled");
            $("#communicatedApproved_executorUserId").removeAttr("disabled");
            $("#communicatedApproved_executorRoleId").select2();
            $("#communicatedApproved_executorRoleId").val("");
            $("#communicatedApproved_executorGroupsId").select2();
            $("#communicatedApproved_executorGroupsId").val("");
//            $('input:radio[id=userExecutorCommunicated]').attr('checked', true);
//            $('input:radio[id=roleExecutorCommunicated]').attr('checked', false);
        } else if (value == "گروه") {
            $("#communicatedApproved_executorRoleId").attr("disabled", "disabled");
            $("#communicatedApproved_executorUserId").attr("disabled", "disabled");
            $("#communicatedApproved_executorGroupsId").removeAttr("disabled");
            $("#communicatedApproved_executorUserId").val("");
            $("#communicatedApproved_executorUserId").select2();
            $("#communicatedApproved_executorRoleId").val("");
            $("#communicatedApproved_executorRoleId").select2();
//            $('input:radio[id=groupExecutorCommunicated]').attr('checked', true);

        }

    },
};
