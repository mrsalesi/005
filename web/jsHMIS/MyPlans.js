/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisMyPlans = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swMyPlansForm").html() == '') {
            $("#swMyPlansForm").load("formHMIS/04MyPlan.html", null, function () {

                $("#cancel_MyPlans").click(function (e) {
                    hmisMyPlans.m_clean();
                    hmisMyPlans.m_show_tbl();
                });
                new jj('#sendFilesMyPlans').jjAjaxFileUploadByTitleAndMultiFile('#attachFileMyPlans', 'plans_files', 'MyPlans_titleFile', "#showFilesMyPlansDiv");
                new jj('#sendFilesStepsMyPlans').jjAjaxFileUploadByTitleAndMultiFile('#attachFilesStepsMyPlans', 'steps_files', 'steps_titleFilesMyPlans', "#showFilesStepsMyPlansDiv");
                $('#commettesSecretoryDiv').hide();
                new jj("#myPlans_startDate").jjCalendarWithYearSelector(1370, 1420);
                new jj("#myPlans_endDate").jjCalendarWithYearSelector(1370, 1420);
                $("#MyPlansSteps_description").summernote({height: 150, tooltip: false});
                hmisIndicatorCommettes.getSelectOptionIndicatorCommettes();
                hmisTotalTarget.getOptionForFilter("#swMyPlansForm #myPlans_hugeGoal");
                hmisRole.getSelectOptionRequierd("#swMyPlansForm #MyPlansSteps_trackerId");
                hmisRole.getSelectOptionRequierd("#swMyPlansForm #MyPlansSteps_executorRoleId");
                cmsUser.getSelectOption("#swMyPlansForm #MyPlansSteps_executorUserId");
                hmisRole.getSelectOptionRequierd("#swMyPlansForm #plans_improveQualityId");
                hmisRole.getSelectOptionRequierd("#swMyPlansForm #plans_managerRoleId");//زمانی که برنامه برای مسئول پایش ارسال میشود  مسئول پایش می تواند مسئول ابلاغ را ببیند و انتخاب کند
                hmisDepartment.selectOptionDepartment("#myPlans_department");
                hmisDepartment.selectOptionDepartment("#myPlans_domain");
                cmsContent.m_getGroupsWithUsers("#MyPlansSteps_executorGroupsId");
                $("#MyPlansSteps_executorGroupsId").select2({
                    width: '100%'
                });
                $("#plans_commettesId").select2({
                    width: '100%'
                });
                $("#plans_managerRoleId").select2({
                    width: '100%'
                });
                $("#plans_improveQualityId").select2({
                    width: '100%'
                });
                $("#myPlans_department").select2({
                    width: '100%'
                });
                $("#myPlans_domain").select2({
                    width: '100%'
                });
                $("#MyPlansSteps_executorRoleId").select2({
                    width: '100%'
                });
                $("#MyPlansSteps_executorUserId").select2({
                    width: '100%'
                });
                $("#MyPlansSteps_trackerId").select2({
                    width: '100%'
                });
                $("#myPlans_hugeGoal").select2({
                    width: '100%'
                });
                $("#myPlans_minorGoal").select2({
                    width: '100%'
                });
                $("#myPlans_strategic").select2({
                    width: '100%'
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".refreshMyPlans";
        param += "&panel=" + (containerId == null ? "swMyPlansTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swMyPlansTbl').slideUp('slow');
        $('#swMyPlansForm').slideDown('slow');
        hmisMyPlans.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisMyPlans.f_content_id).jjVal("");
        new jj("#" + hmisMyPlans.f_title).jjVal("");
        new jj("#" + hmisMyPlans.f_lang).jjVal("1");
        new jj("#" + hmisMyPlans.f_parent).jjVal("0");
    },
    m_show_tbl: function () {
        $('#swMyPlansTbl').show();
        $('#swMyPlansForm').hide();
        $('#refreshMyPlans').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    m_edit: function () {
        var flag = true;
//        if ($("#myPlans_supervisorRolId").val() == null || $("#myPlans_supervisorRolId").val()=="") {
//            $("#select2-myPlans_supervisorRolId-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-myPlans_supervisorRolId-container").removeClass('required');
//        }
        if ($("#myPlans_department").val() == null || $("#myPlans_department").val() == "") {
            $("#select2-myPlans_department-container").addClass("required");
            new jj("بخش را انتخاب کنید").jjModal("هشدار");
            flag = false;
        } else {
            $("#select2-myPlans_department-container").removeClass('required');
        }
        if ($("#myPlans_domain").val() == null || $("#myPlans_domain").val() == "") {
            $("#select2-myPlans_domain-container").addClass("required");
            new jj("دامنه را انتخاب کنید").jjModal("هشدار");
            flag = false;
        } else {
            $("#select2-myPlans_domain-container").removeClass('required');
        }
//        if ($("#myPlans_strategic").val() == null || $("#myPlans_strategic").val() == "") {
//            $("#select2-myPlans_strategic-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-myPlans_strategic-container").removeClass('required');
//        }
        if ($("#plans_commettesId").val() == null || $("#plans_commettesId").val() == "") {
            $("#select2-plans_commettesId-container").addClass("required");
            new jj("کمیته را انتخاب کنید").jjModal("هشدار");
            flag = false;
        } else {
            $("#select2-plans_commettesId-container").removeClass('required');
        }
        if ($("#plans_managerRoleId").val() == null || $("#plans_managerRoleId").val() == "") {
            $("#select2-plans_managerRoleId-container").addClass("required");
            new jj("مسئول ابلاغ را انتخاب کنید").jjModal("هشدار");  
            flag = false;
        } else {
            $("#select2-plans_managerRoleId-container").removeClass('required');
        }
        if ($("#plans_improveQualityId").val() == null || $("#plans_improveQualityId").val() == "") {
            $("#select2-plans_improveQualityId-container").addClass("required");
            new jj("مسئول پایش را انتخاب کنید").jjModal('');
            flag = false;
        } else {
            $("#select2-plans_improveQualityId-container").removeClass('required');
        }
        if (flag == true) {
            var param = "";
            var temp = "";
            var plans_files = $("#swMyPlansForm .plans_files");
            for (var i = 0; i < plans_files.length; i++) {
                temp += $(plans_files[i]).val() + ",";
            }
            param += "&plans_files=" + temp;
            param += "&do=" + hmisPlans.tableName + ".editInMyPlans";
            param += "&" + new jj('#swMyPlansForm').jjSerial();
            param += "&jj=1";
            new jj(param).jjAjax2(false);
            hmisMyPlans.m_show_tbl();
            hmisMyPlans.m_clean();
        }
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".selectMyPlans";
        param += "&" + hmisMyPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $("#showFilesMyPlansDiv").html("");
        hmisMyPlans.m_show_form();
        $("#minorGoalMyPlans").html("");
        $("#hugeGoalMyPlans").html("");
        $("#strategicMyPlans").html("");
    },
    /**
     * تایید برنامه عملیاتی توسط مافوق
     * وارسال به بهبود کیفیت
     * @param {type} id
     * @returns {undefined}
     */
    communicatedBySupervisor: function (id) {
        if (!$("#plans_improveQualityId option:selected").val() == "") {
            new jj("برنامه به مسئول پایش (واحد بهبود کیفیت )ارسال شود؟").jjModal_Yes_No("پیام هشدار قبل از ارسال به مسئول بهبود کیفیت", "hmisMyPlans.communicatedBySupervisor_after_question(" + id + ");");
        } else {
            new jj("مسئول پایش را انتخاب کنید.").jjModal("پیام سامانه")
        }

    },
    /**
     * ای دی برنامه
     * @param {type} id
     * @returns {undefined}
     */
    communicatedBySupervisor_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&plans_improveQualityId=" + new jj("#plans_improveQualityId").jjVal(); //ای دی بهبود کیفیت
        param += "&" + new jj('#swMyPlansForm').jjSerial();
        param += "&do=" + hmisPlans.tableName + ".communicatedBySupervisor";
        new jj(param).jjAjax2(false);
        hmisMyPlans.m_clean();
    },
    /**
     * تایید و ارسال به واحد کمیته مورد نظر 
     * توسط واحد بهبود کیفیت
     * @param {type} id
     * @returns {undefined}
     */
    communicatedByImproveQuality: function (id) {
        if (!$("#plans_commettesId option:selected").val() == "" && !$("#plans_managerRoleId option:selected").val() == "") {
            new jj("برنامه به کمیته انتخاب شده ارسال شود؟").jjModal_Yes_No("پیام هشدار قبل از ارسال به کمیته", "hmisMyPlans.communicatedByImproveQuality_after_question(" + id + ");");
        } else {
            new jj(" کمیته و مسئول ابلاغ را انتخاب کنید").jjModal("پیام سامانه");
        }

    },
    /**
     * ای دی برنامه
     * @param {type} id
     * @returns {undefined}
     */
    communicatedByImproveQuality_after_question: function (id) {
        var flag = true;
        if ($("#myPlans_department").val() == null || $("#myPlans_department").val() == "") {
            $("#select2-myPlans_department-container").addClass("required");
             new jj(" بخش  را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-myPlans_department-container").removeClass('required');
        }
        if ($("#myPlans_domain").val() == null || $("#myPlans_domain").val() == "") {
            $("#select2-myPlans_domain-container").addClass("required");
             new jj(" دامنه را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-myPlans_domain-container").removeClass('required');
        }

        if (flag == true) {
            var param = "";
            param += "&id=" + id;
            param += "&plans_commettesId=" + new jj("#plans_commettesId").jjVal(); //ای دی دبیر کمیته
            param += "&plans_managerRoleId=" + new jj("#plans_managerRoleId").jjVal(); //ای دی دبیر کمیته
            param += "&do=" + hmisPlans.tableName + ".communicatedByImproveQuality";
            new jj(param).jjAjax2(false);
            hmisMyPlans.m_clean();
        }
    },
    /**
     * نمایش گامها وجزئیات گام ها  در برنامه عملیاتی من
     * @param {type} id
     * @returns {undefined}
     */
    selectStepsInMyPlans: function (stepsId, plansId) {
        $("#showFilesStepsMyPlansDiv").html("");
        var param = "";
        param += "do=" + hmisSteps.tableName + ".selectStepsInMyPlans";
        param += "&hmis_plans_id=" + plansId;
        param += "&hmis_steps_id=" + stepsId;
        new jj(param).jjAjax2(false);
        $('#stepsForm1').show(); //نمایش گام ها 
        $('#myPlansStepsForm').show(); //
        $("html, body").delay(1000).animate({scrollTop: $('#myPlansStepsForm').offset().top}, 800);
    },
    /**
     * رد برنامه عملیاتی توسط مافوق
     * 
     */
    ignoreBySupervisor: function (id) {
        new jj("آیا  برنامه عملیاتی را رد می کنید؟").jjModal_Yes_No('پیام هشدار', "hmisMyPlans.ignoreBySupervisor_after_question(" + id + ");");
    },
    /**
     * ای دی برنامه عملیاتی من
     * @param {type} id
     * @returns {undefined}
     */
    ignoreBySupervisor_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&plans_description=" + new jj("#myPlans_description").jjVal();
        param += "&do=" + hmisPlans.tableName + ".ignoreBySupervisor";
        new jj(param).jjAjax2(false);
        hmisMyPlans.m_clean();
    },
    /**
     * درخواست ویرایش و برگشتن به وضعیت ثبت اولیه
     * @param {type} id
     * @returns {undefined}
     */
    requestEditMyPlans: function (id) {
        new jj("آیا برنامه جهت ویرایش به ایجاد کننده برنامه ارسال شود؟").jjModal_Yes_No("پیام هشدار قبل از ویرایش برنامه ", "hmisMyPlans.requestEditMyPlans_after_question(" + id + ");");
    },
    requestEditMyPlans_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + hmisPlans.tableName + ".requestEditMyPlans";
        param += "&plans_description=" + new jj("#myPlans_description").jjVal();
        new jj(param).jjAjax2(false);
        hmisMyPlans.m_clean();
    },
    ignoreByImproveQuality: function (id) {
        new jj("آیا  برنامه عملیاتی را رد می کنید؟").jjModal_Yes_No('پیام هشدار', "hmisMyPlans.ignoreByImproveQuality_after_question(" + id + ");");
    },
    /**
     * ای دی برنامه عملیاتی من
     * @param {type} id
     * @returns {undefined}
     */
    ignoreByImproveQuality_after_question: function (id) {
        var param = "";
        param += "&plans_description=" + new jj("#myPlans_description").jjVal();
        param += "&id=" + id;
        param += "&do=" + hmisPlans.tableName + ".ignoreByImproveQuality";
        new jj(param).jjAjax2(false);
        hmisMyPlans.m_clean();
    },
    executorActionMyPlans: function (value) {
        if (value == "سمت") {
            $("#MyPlansSteps_executorUserId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorGroupsId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorRoleId").removeAttr("disabled");
            $("#MyPlansSteps_executorUserId").val("");
            $("#MyPlansSteps_executorUserId").select2();
            $("#MyPlansSteps_executorGroupsId").val("");
            $("#MyPlansSteps_executorGroupsId").select2();
            $('input:radio[id=ExecutorUserMyPlans]').attr('checked', false);
            $('input:radio[id=ExecutorGroupMyPlans]').attr('checked', false);
            $('input:radio[id=ExecutorRoleMyPlans]').attr('checked', true);
        } else if (value == "کاربران") {
            $("#MyPlansSteps_executorRoleId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorGroupsId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorUserId").removeAttr("disabled");
            $("#MyPlansSteps_executorRoleId").select2();
            $("#MyPlansSteps_executorRoleId").val("");
            $("#MyPlansSteps_executorGroupsId").select2();
            $("#MyPlansSteps_executorGroupsId").val("");
            $('input:radio[id=ExecutorUserMyPlans]').attr('checked', true);
            $('input:radio[id=ExecutorGroupMyPlans]').attr('checked', false);
            $('input:radio[id=ExecutorRoleMyPlans]').attr('checked', false);
        } else if (value == "گروه") {
            $("#MyPlansSteps_executorRoleId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorUserId").attr("disabled", "disabled");
            $("#MyPlansSteps_executorGroupsId").removeAttr("disabled");
            $("#MyPlansSteps_executorRoleId").select2();
            $("#MyPlansSteps_executorRoleId").val("");
            $("#MyPlansSteps_executorUserId").select2();
            $("#MyPlansSteps_executorUserId").val("");
            $('input:radio[id=ExecutorUserMyPlans]').attr('checked', false);
            $('input:radio[id=ExecutorRoleMyPlans]').attr('checked', false);
            $('input:radio[id=ExecutorGroupMyPlans]').attr('checked', true);
        }

    },
    editStepsMyPlans: function () {
        var flag = true;
        if ($("#MyPlansSteps_trackerId").val() == null || $("#MyPlansSteps_trackerId").val() == "") {
            $("#select2-MyPlansSteps_trackerId-container").addClass("required");
             new jj(" مسئول پیگیری را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;   
        } else {
            $("#select2-MyPlansSteps_trackerId-container").removeClass('required');
        }
        if ($("#MyPlansSteps_startDate").val() == "") {
            $("#MyPlansSteps_startDate").addClass("required");
             new jj(" تاریخ شروع گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#MyPlansSteps_startDate").removeClass('required');
        }
        if ($("#MyPlansSteps_endDate").val() == "") {
            $("#MyPlansSteps_endDate").addClass("required");
             new jj(" تاریخ پایان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#MyPlansSteps_endDate").removeClass('required');
        }
        if ($("#MyPlansSteps_cost").val() == "") {
            $("#MyPlansSteps_cost").addClass("required");
             new jj(" هزینه گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#MyPlansSteps_cost").removeClass('required');
        }
        if ($("#MyPlansSteps_percent").val() == "") {
            $("#MyPlansSteps_percent").addClass("required");
             new jj(" درصد گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#MyPlansSteps_percent").removeClass('required');
        }
        if ($("#MyPlansSteps_title").val() == "") {
            $("#MyPlansSteps_title").addClass("required");
             new jj(" عنوان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#MyPlansSteps_title").removeClass('required');
        }
        if ($("#MyPlansSteps_executorUserId").val() == null && $("#MyPlansSteps_executorRoleId").val() == null && $("#MyPlansSteps_executorGroupsId").val() == null) {
            $("#select2-MyPlansSteps_executorUserId-container").addClass("required");
            $("#select2-MyPlansSteps_executorRoleId-container").addClass("required");
            $("#select2-MyPlansSteps_executorGroupsId-container").addClass("required");
             new jj(" مسئول اجرا  را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-MyPlansSteps_executorUserId-container").removeClass('required');
            $("#select2-MyPlansSteps_executorRoleId-container").removeClass('required');
            $("#select2-MyPlansSteps_executorGroupsId-container").removeClass('required');
        }

        if (flag == true) {
            var param = "";
            var temp = "";
            var steps_files = $("#stepsForm1  .steps_files");
            for (var i = 0; i < steps_files.length; i++) {
                temp += $(steps_files[i]).val() + ",";
            }
            param += "&steps_files=" + temp;
            param += "&do=" + hmisSteps.tableName + ".editStepsMyPlans";
            param += "&" + new jj('#stepsForm1').jjSerial();
            param += "&jj=1";
            new jj(param).jjAjax2(false);
            $("#stepsForm1").slideUp();
        }
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
        $('#swMyPlans').css('height', 'auto');
    },
    tabSizeForm: function () {
        $('#swMyPlans').css('height', 'auto');
    },
    deleteStepsMyPlans: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'hmisPlans.deleteStepsMyPlans_after_question(' + id + ');');
    },
    deleteStepsMyPlans_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisSteps.tableName + ".deleteStepsInMyPlans";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        $("#myPlansStepsForm").slideUp();
    },

};


     