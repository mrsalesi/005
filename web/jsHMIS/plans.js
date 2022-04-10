/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisPlans = {
    tableName: "Plans",
    f_id: "id",
    loadForm: function () {
        if ($("#swPlansForm").html() == '') {
            $("#swPlansForm").load("formHMIS/04newPlan.html", null, function () {
                $("#cancel_Plans").click(function (e) {
                    hmisPlans.m_clean();
                    hmisPlans.m_show_tbl();
                });

                new jj('#sendFilesPlans').jjAjaxFileUploadByTitleAndMultiFile('#attachFilePlans', 'plans_files', 'plans_titleFile', "#showFilesPlansDiv");
                new jj('#sendFilesSteps').jjAjaxFileUploadByTitleAndMultiFile('#attachFileSteps', 'steps_files', 'steps_titleFile', "#showFilesStepsDiv");
                new jj("#steps_startDate").jjCalendarWithYearSelector(1370, 1420);
                new jj("#steps_endDate").jjCalendarWithYearSelector(1370, 1420);
                $("#plans_description").summernote({height: 150, tooltip: false});
                $("#steps_description").summernote({height: 150, tooltip: false});
                //////////////////////////////
                hmisPlans.getoptionRange("plans_range");
//                hmisTotalTarget.getTarget();////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
                hmisTotalTarget.getOptionForFilter("#swPlansForm #plans_hugeGoal");////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
                hmisRole.getSelectOptionRequierd("#swPlansForm #plans_improveQualityId");
                hmisRole.getSelectOptionRequierd("#swPlansForm #steps_trackerId");
                hmisRole.getSelectOptionRequierd("#swPlansForm #steps_executorRoleId");
                cmsUser.getSelectOption("#swPlansForm #steps_executorUserId");
                hmisRole.getSelectOptionRequierd("#swPlansForm #plans_supervisorRolId");
                hmisDepartment.selectOptionDepartment("#plans_department");
                hmisDepartment.selectOptionDepartment("#plans_domain");
                 cmsContent.m_getGroupsWithUsers("#steps_executorGroupsId");
                $("#steps_executorGroupsId").select2({
                    width: '100%'
                });
                $("#plans_improveQualityId").select2({
                    width: '100%'
                });
                $("#steps_trackerId").select2({
                    width: '100%'
                });

                $("#plans_supervisorRolId").select2({
                    width: '100%'
                });
                $("#plans_department").select2({
                    width: '100%'
                });
                $("#plans_hugeGoal").select2({
                    width: '100%'
                });
                $("#plans_minorGoal").select2({
                    width: '100%'
                });
                $("#plans_strategic").select2({
                    width: '100%'
                });
                $("#plans_domain").select2({
                    width: '100%'
                });
                $("#steps_executorRoleId").select2({
                    width: '100%'
                });
                $("#steps_executorUserId").select2({
                    width: '100%'
                });
            });
        }


    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swPlansTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swPlansTbl').hide();
        $('#swPlansForm').show();
        hmisPlans.tabSizeForm();
    },
    m_clean: function () {
        new jj('#hmis_plans_id').jjVal("");
        new jj('#hmis_steps_id').jjVal("");
        new jj('#stepsForm input:text').jjVal("");
        new jj('#stepsForm input[type=number]').jjVal("");
        new jj('#newFormPlans').jjFormClean();
        new jj('#newFormPlans select').jjFormClean();
        new jj('#stepsForm input:text').jjVal("");
        new jj('#stepsForm select').jjVal("");
        $('#refreshTblSteps').html("");
        $('#hugeGoal').html("");
        $('#minorGoal').html("");
        $('#strategic').html("");
        $('#titlePlans').html("");
        $('#improveQualityRoleId').html("");
        $('#period').html("");
        $('#range').html("");
        $('#domain').html("");
        $('#showFilesPlansDiv').html("");
    },
    m_add_new: function () {
        new jj("do=" + hmisPlans.tableName + ".add_new&jj=1").jjAjax2(false);
        $("#plans_supervisorRolId").val("");
        $("#btns_plans").html("");
        $("#plans_supervisorRolId").select2({
            width: '100%'
        });
        $("#plans_strategic").val("");
        $("#plans_strategic").select2({
            width: '100%'
        });
        $("#plans_domain").val("");
        $("#plans_domain").select2({
            width: '100%'
        });
        $("#plans_hugeGoal").val("");
        $("#plans_hugeGoal").select2({
            width: '100%'
        });
        $("#plans_minorGoal").val("");
        $("#plans_minorGoal").select2({
            width: '100%'
        });
        $("#plans_department").val("");
        $("#plans_department").select2({
            width: '100%'
        });
        $('#recordPlans').show(); //مخفی کردن دکمه ثبت 
//        $('#newFormPlans').show(); //مخفی کردن فرم پلن 
        $('#stepsForm').hide(); //فرم گام
        $('#tblSteps').hide(); //فرم گام
        $('#editPlansButton').hide(); ///ثبت تغییرات پلن
//        $('#formQuestions').hide(); //
        hmisPlans.m_show_form();
        hmisPlans.m_clean();
        //        oEditor.execCommand( 'bold');

    },
    m_show_tbl: function () {
        $('#swPlansTbl').show();
        $('#swPlansForm').hide();
        $('#refreshPlans').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    m_insert: function () {
        var flag = true;
        if ($("#plans_department").val() == null || $("#plans_department").val() == "") {
            $("#select2-plans_department-container").addClass("required");
             new jj(" بخش را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_department-container").removeClass('required');
        }
        if ($("#plans_domain").val() == null || $("#plans_domain").val() == "") {
            $("#select2-plans_domain-container").addClass("required");
             new jj(" دامنه را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_domain-container").removeClass('required');
        }
//        if ($("#plans_strategic").val() == null) {
//            $("#select2-plans_strategic-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-plans_strategic-container").removeClass('required');
//        }
        if ($("#plans_supervisorRolId").val() == null) {
            $("#select2-plans_supervisorRolId-container").addClass("required");
             new jj(" مافوق را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_supervisorRolId-container").removeClass('required');
        }
        if ($("#plans_improveQualityId").val() == null) {
            $("#select2-plans_improveQualityId-container").addClass("required");
             new jj(" مسئول پایش را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_improveQualityId-container").removeClass('required');
        }
//        if ($("#plans_managerRoleId").val() == null) {
//            $("#select2-plans_managerRoleId-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-plans_managerRoleId-container").removeClass('required');
//        } 
        if (flag == true) {
            var temp = "";
            var param = "";
            param += "&do=" + hmisPlans.tableName + ".insert";
            param += "&" + new jj('#newFormPlans').jjSerial();
            param += "&plans_supervisorRolId=" + $("#plans_supervisorRolId").val();
            var plans_files = $("#swPlansForm  .plans_files");
            for (var i = 0; i < plans_files.length; i++) {
                temp += $(plans_files[i]).val() + ",";
            }
            param += "&plans_files=" + temp;
            new jj(param).jjAjax2(false);

            $('#stepsForm').show(); //نمایش گام ها 
            $('#recordPlans').hide(); //مخفی کردن دکمه ثبت 
        }
    },
    m_edit: function () {
        var flag = true;
        if ($("#plans_department").val() == null || $("#plans_department").val() == "") {
            $("#select2-plans_department-container").addClass("required");
             new jj(" بخش را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_department-container").removeClass('required');
        }
        if ($("#plans_domain").val() == null || $("#plans_domain").val() == "") {
            $("#select2-plans_domain-container").addClass("required");
             new jj(" دامنه را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_domain-container").removeClass('required');
        }
//        if ($("#plans_strategic").val() == null || $("#plans_strategic").val() == "") {
//            $("#select2-plans_strategic-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-plans_strategic-container").removeClass('required');
//        }
        if ($("#plans_supervisorRolId").val() == null || $("#plans_supervisorRolId").val() == "") {
            $("#select2-plans_supervisorRolId-container").addClass("required");
             new jj(" مافوق را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_supervisorRolId-container").removeClass('required');
        }
        if ($("#plans_improveQualityId").val() == null || $("#plans_improveQualityId").val() == "") {
            $("#select2-plans_improveQualityId-container").addClass("required");
             new jj(" مسئول پایش را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-plans_improveQualityId-container").removeClass('required');
        }
        if (flag == true) {
            var param = "";
            var temp = "";
            var plans_files = $("#swPlansForm .plans_files");
            for (var i = 0; i < plans_files.length; i++) {
                temp += $(plans_files[i]).val() + ",";
            }
            param += "&do=" + hmisPlans.tableName + ".edit";
            param += "&" + new jj('#swPlansForm').jjSerial();
            param += "&plans_supervisorRolId=" + $("#plans_supervisorRolId").val();
            param += "&plans_files=" + temp;
            param += "&jj=1";
            new jj(param).jjAjax2(false);  
            hmisPlans.m_show_tbl();
            hmisPlans.m_clean();
        }
    },
    /**
     *ارسال برنامه عملیاتی به مافوق 
     *مافوق هم در برنامه عملیاتی من برنامه را یا تایید میکند یا رد میکند
     *   
     * @returns {undefined}  */
    communicatingToSupervisor: function (id) {
        if (!$("#plans_supervisorRolId").val() == "") {
            new jj("آیا برنامه به مافوق جهت تایید ارسال شود؟").jjModal_Yes_No("پیام هشدار قبل از ارسال به مافوق ", "hmisPlans.communicatingToSupervisor_after_question(" + id + ");");
        } else {
            new jj(" مافوق را انتخاب کنید.").jjModal("پیام سامانه");
        }
    },
    communicatingToSupervisor_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisPlans.tableName + ".communicatingToSupervisor";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        param += "&" + new jj('#swPlansForm').jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisPlans.m_show_tbl();
        hmisPlans.m_clean();

    },
    /**
     * ویرایش اطلاعات گام ها در قسمت plans
     * 
     * @returns {undefined}
     */
    editStepsInPlans: function () {
        var flag = true;
        if ($("#steps_trackerId").val() == null || $("#steps_trackerId").val() == "") {
            $("#select2-steps_trackerId-container").addClass("required");
             new jj(" مسئول پیگیری را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-steps_trackerId-container").removeClass('required');
        }
        if ($("#steps_startDate").val() == "") {
            $("#steps_startDate").addClass("required");
             new jj(" تاریخ شروع را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#steps_startDate").removeClass('required');
        }
        if ($("#steps_endDate").val() == "") {
            $("#steps_endDate").addClass("required");
             new jj(" تاریخ پایان را انتخاب کنید").jjModal("پیام سامانه");
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
             new jj(" درصد گام را انتخاب کنید").jjModal("پیام سامانه");
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
        if ($("#steps_executorUserId").val() == null && $("#steps_executorRoleId").val() == null&&$("#steps_executorGroupsId").val() == null ) {
            $("#select2-steps_executorUserId-container").addClass("required");
            $("#select2-steps_executorRoleId-container").addClass("required");
            $("#select2-steps_executorGroupsId-container").addClass("required");
             new jj(" مسئول اجرا را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-steps_executorUserId-container").removeClass('required');
            $("#select2-steps_executorGroupsId-container").removeClass('required');
            $("#select2-steps_executorRoleId-container").removeClass('required');
        }

        if (flag == true) {
            var param = "";
            var temp = "";
            var steps_files = $("#stepsForm  .steps_files");
            for (var i = 0; i < steps_files.length; i++) {
                temp += $(steps_files[i]).val() + ",";
            }
            param += "&steps_files=" + temp;
            param += "&do=" + hmisSteps.tableName + ".editStepsInPlans";
            param += "&" + new jj('#stepsForm').jjSerial();
            param += "&steps_description=" + encodeURIComponent($('#steps_description').summernote('code'));
            param += "&jj=1";
            new jj(param).jjAjax2(false);
            $("#stepsForm").slideUp();
            $("#tblSteps").slideDown();
        }
    },
    deleteStepsInManager: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'hmisPlans.deleteStepsInManager_after_question(' + id + ');');

    },
    deleteStepsInManager_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisSteps.tableName + ".deleteStepsInManager";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        $("#stepsFormInManager").slideUp();
    },
    editStepsInManager: function () {
        var flag = true;
        if ($("#ManagerPlansSteps_trackerId").val() == null || $("#ManagerPlansSteps_trackerId").val() == "") {
            $("#select2-ManagerPlansSteps_trackerId-container").addClass("required");
             new jj(" مسئول پیگیری را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlansSteps_trackerId-container").removeClass('required');
        }
        if ($("#ManagerPlansSteps_startDate").val() == "") {
            $("#ManagerPlansSteps_startDate").addClass("required");
             new jj(" تاریخ شروع گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#ManagerPlansSteps_startDate").removeClass('required');
        }
        if ($("#ManagerPlansSteps_endDate").val() == "") {
            $("#ManagerPlansSteps_endDate").addClass("required");
             new jj(" تاریخ پایان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#ManagerPlansSteps_endDate").removeClass('required');
        }
        if ($("#ManagerPlansSteps_cost").val() == "") {
            $("#ManagerPlansSteps_cost").addClass("required");
             new jj(" هزینه گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#ManagerPlansSteps_cost").removeClass('required');
        }
        if ($("#ManagerPlansSteps_percent").val() == "") {
            $("#ManagerPlansSteps_percent").addClass("required");
             new jj(" درصد گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#ManagerPlansSteps_percent").removeClass('required');
        }
        if ($("#ManagerPlansSteps_title").val() == "") {
            $("#ManagerPlansSteps_title").addClass("required");
             new jj(" عنوان گام را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#ManagerPlansSteps_title").removeClass('required');
        }
        if ($("#ManagerPlansSteps_executorUserId").val() == null && $("#ManagerPlansSteps_executorRoleId").val() == null&&$("#ManagerPlansSteps_executorGroupsId").val() == null ) {
            $("#select2-ManagerPlansSteps_executorUserId-container").addClass("required");
            $("#select2-ManagerPlansSteps_executorRoleId-container").addClass("required");
            $("#select2-ManagerPlansSteps_executorGroupsId-container").addClass("required");
             new jj(" مسئول اجرا را انتخاب کنید").jjModal("پیام سامانه");
            flag = false;
        } else {
            $("#select2-ManagerPlansSteps_executorUserId-container").removeClass('required');
            $("#select2-ManagerPlansSteps_executorRoleId-container").removeClass('required');
            $("#select2-ManagerPlansSteps_executorGroupsId-container").removeClass('required');
        }

        if (flag == true) {
            var param = "";
            var temp = "";
            var steps_files = $("#stepsFormInManager  .steps_files");
            for (var i = 0; i < steps_files.length; i++) {
                temp += $(steps_files[i]).val() + ",";
            }
            param += "&steps_files=" + temp;
            param += "&do=" + hmisSteps.tableName + ".editStepsInManager";
            param += "&" + new jj('#stepsFormInManager').jjSerial();
            param += "&jj=1";
            new jj(param).jjAjax2(false);
            $("#stepsFormInManager").slideUp();
        }
    },
//    m_validation: function () {// mohamdad
//        if (new jj("#content_title").jjVal().length < 1) {
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
//        return "";
//    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'hmisPlans.m_delete_after_question(' + id + ');');

    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".delete";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPlans.m_show_tbl();
        hmisPlans.m_clean();
    },
    /**
     * 13971115
     * @param {type} id
     * @returns {undefined}
     */
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".select";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPlans.m_show_form();

        $('#stepsForm').hide(); //نمایش فرم گامها
        $('#tblSteps').show(); //نمایش فرم گامها
        $('#correctionForm').hide(); // فرم اصلاحیه 
        $("#decriptionStepsDiv").hide();
        $("#showFilesPlansDiv").html("");
//        hmisTotalTarget.getOptionForFilter("#swPlansForm #plans_hugeGoal");////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
    },
    /**
     * 13971116
     * @param {type} id
     * @returns {undefined}
     */
    selectStepsInPlans: function (id) {
        $("#showFilesStepsDiv").html("");
        var param = "";
        param += "do=" + hmisSteps.tableName + ".selectStepsInPlans";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $("#stepsForm").slideDown();
        $("#tblSteps").slideUp();
        $('#editPlansButton').show(); //نمایش دکمه تغییرات
//        $('#stepsForm').show(); //
//        $("html, body").delay(1000).animate({scrollTop: $('#stepsForm').offset().top}, 800);
    },


    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".add_EN";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlans.f_parent).jjVal(id);
        new jj("#" + hmisPlans.f_lang).jjVal("2");
        hmisPlans.m_show_form();
    },
    /**
     * تایید توسط مافوق
     * @param {type} id
     * @returns {undefined}
     */
    confirmBySupervisor: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".confirmBySupervisor";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    executorAction: function (value) {
        if (value == "سمت") {
            $("#steps_executorUserId").attr("disabled", "disabled");
            $("#steps_executorGroupsId").attr("disabled", "disabled");
            $("#steps_executorRoleId").removeAttr("disabled");
            $("#steps_executorUserId").val("");
            $("#steps_executorUserId").select2();
            $("#steps_executorGroupsId").val("");
            $("#steps_executorGroupsId").select2();
        } else if (value == "کاربران") {
            $("#steps_executorRoleId").attr("disabled", "disabled");
            $("#steps_executorGroupsId").attr("disabled", "disabled");
            $("#steps_executorUserId").removeAttr("disabled");
            $("#steps_executorRoleId").select2();
            $("#steps_executorRoleId").val("");
            $("#steps_executorGroupsId").select2();
            $("#steps_executorGroupsId").val("");
        } else if (value == "گروه") {
            $("#steps_executorRoleId").attr("disabled", "disabled");
            $("#steps_executorUserId").attr("disabled", "disabled");
            $("#steps_executorGroupsId").removeAttr("disabled");
            $("#steps_executorRoleId").select2();
            $("#steps_executorRoleId").val("");
            $("#steps_executorUserId").select2();
            $("#steps_executorUserId").val("");
        }

    },

    /**
     * 
     * @param {type} DivId
     * @returns {undefined}
     */
    changeSelectToInputRange: function () {
        if ($('#range_div').css('display') == 'none') {
            $('#range_div').show();
            $('#plans_range').attr('name', 'plans_range');
            $('#plans_range1').hide();
            $('#plans_range1').attr('name', 'plans_range1');
        } else {  
            $('#range_div').hide();
            $('#plans_range').attr('name', 'plans_range1');
            $('#plans_range1').show();
            $('#plans_range1').attr('name', 'plans_range');
        }
    },
    /**
     * 
     * @param {type} DivId
     * @returns {undefined}
     */
    changeSelectToInputTitle: function () {
        if ($('#title_div').css('display') == 'none') {
            $('#title_div').show();
            $('#plans_title').attr('name', 'plans_title');
            $('#plans_title1').hide();
            $('#plans_title1').attr('name', 'plans_title1');
        } else {
            $('#title_div').hide();
            $('#plans_title').attr('name', 'plans_title1');
            $('#plans_title1').show();
            $('#plans_title1').attr('name', 'plans_title');
        }
    },
    getoptionRange: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisPlans.tableName + ".getoptionRange";
        new jj(param).jjAjax2(false);
    },
    /**
     * نمایش فرم  اقدامات اصلاحی
     * 
     * @param {type} DivId
     * @returns {undefined}
     */
    showOfferForm: function (DivId) {
        var param = "";
        param += "DivId=" + DivId;
        param += "&do=" + hmisPlans.tableName + ".offerForm";
        new jj(param).jjAjax2(false);

    },

    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".add_Ar";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlans.f_parent).jjVal(id);
        new jj("#" + hmisPlans.f_lang).jjVal("3");
        hmisPlans.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".add_lang";
        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlans.f_parent).jjVal(id);
        new jj("#" + hmisPlans.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisPlans.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".searchTags";
        param += "&" + new jj('#swPlansForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".insertTags";
        param += "&" + new jj('#swPlansForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swPlans').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swPlans').css('height', 'auto');
    }

};
