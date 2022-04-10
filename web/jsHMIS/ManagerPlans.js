/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisManagerPlans = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swManagerPlansForm").html() == '') {
            $("#swManagerPlansForm").load("formHMIS/04ManagerPlan.html", null, function () {
                $("#cancel_ManagerPlans").click(function (e) {
                    hmisManagerPlans.m_clean();
                    hmisManagerPlans.m_show_tbl();
                });
                new jj('#sendFilesManagerPlans').jjAjaxFileUploadByTitleAndMultiFile('#attachFileManagerPlans', 'plans_files', 'ManagerPlans_titleFile', "#showFilesManagerPlansDiv");
                new jj('#sendFilesStepsManagerPlans').jjAjaxFileUploadByTitleAndMultiFile('#attachFilesStepsManagerPlans', 'steps_files', 'steps_titleFilesManagerPlans', "#showFilesStepsManagerPlansDiv");
                $('#commettesSecretoryDiv').hide();
                new jj("#ManagerPlansSteps_startDate").jjCalendarWithYearSelector(1370, 1420);
                new jj("#ManagerPlansSteps_endDate").jjCalendarWithYearSelector(1370, 1420);
                $("#ManagerPlans_description").summernote();
                hmisTotalTarget.getOptionForFilter("#swManagerPlansForm #ManagerPlans_hugeGoal");////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
                cmsUser.getSelectOption("#swManagerPlansForm #ManagerPlansSteps_executorUserId");
                hmisRole.getSelectOptionRequierd("#swManagerPlansForm #ManagerPlansSteps_executorRoleId");
                hmisRole.getSelectOptionRequierd("#swManagerPlansForm #ManagerPlansSteps_trackerId");
                hmisRole.getSelectOptionRequierd("#swManagerPlansForm #ManagerPlans_improveQualityId");
                hmisDepartment.selectOptionDepartment("#ManagerPlans_department");
                hmisDepartment.selectOptionDepartment("#ManagerPlans_domain");
                cmsContent.m_getGroupsWithUsers("#ManagerPlansSteps_executorGroupsId");    
                $("#ManagerPlansSteps_executorGroupsId").select2({
                    width: '100%'
                });
                $("#ManagerPlansSteps_trackerId").select2({
                    width: '100%'
                });
                $("#ManagerPlansSteps_executorRoleId").select2({
                    width: '100%'
                });
                $("#ManagerPlansSteps_executorUserId").select2({
                    width: '100%'
                });
                $("#ManagerPlans_improveQualityId").select2({
                    width: '100%'
                });
                $("#ManagerPlans_department").select2({
                    width: '100%'
                });
                $("#ManagerPlans_domain").select2({
                    width: '100%'
                });
                $("#ManagerPlans_hugeGoal").select2({
                    width: '100%'
                });
                $("#ManagerPlans_minorGoal").select2({
                    width: '100%'
                });
                $("#ManagerPlans_strategic").select2({
                    width: '100%'
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".refreshManagerPlans";
        param += "&panel=" + (containerId == null ? "swManagerPlansTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swManagerPlansTbl').slideUp('slow');
        $('#swManagerPlansForm').slideDown('slow');
        hmisManagerPlans.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisManagerPlans.f_content_id).jjVal("");
        new jj("#" + hmisManagerPlans.f_title).jjVal("");
        new jj("#" + hmisManagerPlans.f_lang).jjVal("1");
        new jj("#" + hmisManagerPlans.f_parent).jjVal("0");

    },

    m_show_tbl: function () {
        $('#swManagerPlansTbl').show();
        $('#refreshManagerPlans').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        $('#swManagerPlansForm').hide();

    },
    m_edit: function () {
        var flag = true;
        if ($("#ManagerPlans_department").val() == null || $("#ManagerPlans_department").val() == "") {
            $("#select2-ManagerPlans_department-container").addClass("required");
             new jj(" بخش  را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlans_department-container").removeClass('required');
        }
        if ($("#ManagerPlans_domain").val() == null || $("#ManagerPlans_domain").val() == "") {
            $("#select2-ManagerPlans_domain-container").addClass("required");
             new jj("دامنه را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlans_domain-container").removeClass('required');
        }

        if (flag == true) {
            var param = "";
            param += "&do=" + hmisPlans.tableName + ".editInManagerPlans";
            param += "&" + new jj('#swManagerPlansForm').jjSerial();
            param += "&jj=1";
            var temp = "";
            var plans_files = $("#swManagerPlansForm .plans_files");
            for (var i = 0; i < plans_files.length; i++) {
                temp += $(plans_files[i]).val() + ",";
            }
            param += "&plans_files=" + temp;

            new jj(param).jjAjax2(false);
            hmisManagerPlans.m_show_tbl();
            hmisManagerPlans.m_clean();
        }
    },
    m_select: function (id) {
//        $("#addNewApproved").hide();
        var param = "";
        param += "do=" + hmisPlans.tableName + ".selectManagerPlans";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisManagerPlans.m_show_form();
        $("#showFilesManagerPlansDiv").html("");
        $("#minorGoalMyPlans").html("");
        $("#hugeGoalMyPlans").html("");
        $("#strategicMyPlans").html("");
    },
    /**
     * تایید برنامه عملیاتی توسط مدیر
     * @param {type} id
     * @returns {undefined}
     */
    confirmByManager: function (id) {
        new jj("برنامه عملیاتی را تایید می کنید؟").jjModal_Yes_No('پیام هشدار قبل از رد برنامه', " hmisManagerPlans.confirmByManager_after_question(" + id + ");");
    },
    /**
     * ای دی برنامه
     * @param {type} id
     * @returns {undefined}
     */
    confirmByManager_after_question: function (id) {
        var flag = true;
        if ($("#ManagerPlans_department").val() == null || $("#ManagerPlans_department").val() == "") {
            $("#select2-ManagerPlans_department-container").addClass("required");
             new jj(" بخش را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlans_department-container").removeClass('required');
        }
        if ($("#ManagerPlans_domain").val() == null || $("#ManagerPlans_domain").val() == "") {
            $("#select2-ManagerPlans_domain-container").addClass("required");
             new jj(" دامنه را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlans_domain-container").removeClass('required');
        }
//        if ($("#ManagerPlans_strategic").val() == null || $("#ManagerPlans_strategic").val() == "") {
//            $("#select2-ManagerPlans_strategic-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-ManagerPlans_strategic-container").removeClass('required');
//        }
        if (flag == true) {
            var param = "";
            param += "&id=" + id;
            param += "&do=" + hmisPlans.tableName + ".confirmByManager";
            new jj(param).jjAjax2(false);
            hmisManagerPlans.m_clean();
        }
    },

    /**
     * درخواست ویرایش و برگشتن به وضعیت ثبت اولیه
     * @param {type} id
     * @returns {undefined}
     */
    requestEditManagerPlans: function (id) {
        new jj("آیا برنامه جهت ویرایش به ایجاد کننده برنامه ارسال شود؟").jjModal_Yes_No("پیام هشدار", "hmisManagerPlans.requestEditManagerPlans_after_question(" + id + ");");
    },
    requestEditManagerPlans_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + hmisPlans.tableName + ".requestEditManagerPlans";
        param += "&plans_description=" + new jj("#ManagerPlans_description").jjVal();
        new jj(param).jjAjax2(false);
        hmisManagerPlans.m_clean();
    },
    /**
     * نمایش گامها وجزئیات گام ها  در برنامه عملیاتی من
     * @param {type} id
     * @returns {undefined}
     */
    selectStepsInManagerPlans: function (stepsId, plansId) {
        var param = "";
        param += "&do=" + hmisSteps.tableName + ".selectStepsInManagerPlans";
        param += "&hmis_Managerplans_id=" + plansId;
        param += "&stepsIdInManager=" + stepsId;
        new jj(param).jjAjax2(false);
        $('#stepsFormInManager').show(); //نمایش گام ها 
        $('#showFilesStepsManagerPlansDiv').html("");
        $('#ManagerPlansStepsForm').show(); //  
        $("html, body").delay(1000).animate({scrollTop: $('#ManagerPlansStepsForm').offset().top}, 800);
    },
    /**
     * رد برنامه عملیاتی توسط مافوق
     * 
     */
    ignoreByManager: function (id) {
        new jj("آیا  برنامه عملیاتی را رد می کنید؟").jjModal_Yes_No('پیام هشدار', "hmisManagerPlans.ignoreByManager_after_question(" + id + ");");
    },
    /**
     * ای دی برنامه عملیاتی من
     * @param {type} id
     * @returns {undefined}
     */
    ignoreByManager_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&plans_description=" + new jj("#ManagerPlans_description").jjVal();
        param += "&do=" + hmisPlans.tableName + ".ignoreByManager";
        new jj(param).jjAjax2(false);
        hmisManagerPlans.m_clean();
    },

    tabSizeTbl: function () {
        $('#swManagerPlans').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swManagerPlans').css('height', 'auto');
    },
    executorActionManagerPlans: function (value) {

        if (value == "سمت") {
            $("#ManagerPlansSteps_executorRoleId").removeAttr("disabled");
            $("#ManagerPlansSteps_executorUserId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorGroupsId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorUserId").val("");
            $("#ManagerPlansSteps_executorUserId").select2();
            $("#ManagerPlansSteps_executorGroupsId").val("");
            $("#ManagerPlansSteps_executorGroupsId").select2();
            $('input:radio[id=ExecutorUserInManager]').attr('checked', false);
            $('input:radio[id=ExecutorGroupInManager]').attr('checked', false);
            $('input:radio[id=ExecutorRoleInManager]').attr('checked', true);
        } else if (value == "کاربران") {
            $("#ManagerPlansSteps_executorRoleId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorGroupsId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorUserId").removeAttr("disabled");
            $("#ManagerPlansSteps_executorRoleId").select2();
            $("#ManagerPlansSteps_executorRoleId").val("");
            $("#ManagerPlansSteps_executorGroupsId").select2();
            $("#ManagerPlansSteps_executorGroupsId").val("");
            $('input:radio[id=ExecutorUserInManager]').attr('checked', true);
            $('input:radio[id=ExecutorRoleInManager]').attr('checked', false);
            $('input:radio[id=ExecutorGroupInManager]').attr('checked', false);
        } else if (value == "گروه") {
            $("#ManagerPlansSteps_executorRoleId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorUserId").attr("disabled", "disabled");
            $("#ManagerPlansSteps_executorGroupsId").removeAttr("disabled");
            $("#ManagerPlansSteps_executorRoleId").select2();
            $("#ManagerPlansSteps_executorRoleId").val("");
            $("#ManagerPlansSteps_executorUserId").select2();
            $("#ManagerPlansSteps_executorUserId").val("");
            $('input:radio[id=ExecutorGroupInManager]').attr('checked', true);
            $('input:radio[id=ExecutorRoleInManager]').attr('checked', false);
            $('input:radio[id=ExecutorUserInManager]').attr('checked', false);

        }

    },
};
