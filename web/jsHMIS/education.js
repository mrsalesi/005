/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisEducationDefinition = {
    tableName: "Education",
    f_id: "hmis_education_id",

    loadForm: function () {
        if ($("#swEducationDefinitionForm").html() == '') {
            $("#swEducationDefinitionForm").load("formHMIS/educationDefinition.html", null, function () {
                $("#swEducationDefinitionForm #cancel_EducationDefinition").click(function (e) {
                    hmisEducationDefinition.m_clean();
                    hmisEducationDefinition.m_show_tbl();
                });
                new jj('#swEducationDefinitionForm .date').jjCalendarWithYearSelector(1397, 1420);
                $('#swEducationDefinitionForm .time').wickedpicker();
                hmisEducationDefinition.selectOptionFormWithCategory("#swEducationDefinitionForm .selectOptionForm");
                cmsUser.getSelectOptionNotAll("#swEducationDefinitionForm #educationClass_teacher_userId");
                cmsUser.getSelectOption("#swEducationDefinitionForm .userSelectOption");
                hmisRole.getSelectOptionRequierd("#swEducationDefinitionForm .seconder");
                hmisDepartment.selectOptionDepartment("#education_department_id");
                cmsContent.m_getGroupsWithUsers(".targetGroup");
                hmisEducationDefinition.selectOptionCourse("#swEducationDefinitionForm  #education_prerequisite_courseId");
                $("#swEducationDefinitionForm  #education_department_id").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm .seconder").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm #educationClass_registrants_userId").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm #educationClass_preTest_formId").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm #educationClass_postTest_formId").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm #educationClass_teacher_userId").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm  .userSelectOption").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm .targetGroup").select2({
                    width: '100%'
                });
                $("#swEducationDefinitionForm #education_prerequisite_courseId").select2({
                    width: '100%'
                });
                $("#requiredNeedAssessmentDiv").show();
                $('#educationClassDiv').hide();
                $('#optionalForm').hide();
                $('#nesseccaryForm').show();
                new jj('#sendFilesEducationClass').jjAjaxFileUploadByTitleAndMultiFile('#attachFilesEducationClass', 'educationClass_files', 'educationClass_titleFile', "#showFilesEducationClassDiv");
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisEducationDefinition.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swEducationDefinitionTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationDefinition.m_show_tbl();
    },
    selectOptionEducationType: function () {

        if ($("#education_requiredNeedAssessment").not(':checked')) {
            $('#educationClassDiv').show();
            $('#optionalForm').hide();
            $('#educationClassTbl').show();
            $('#optionalForm select').val("");
            $('#nesseccaryForm').show();
             $('table#refreshTblEducationClass').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });  
        }
             
        if ($("#education_requiredNeedAssessment").is(':checked')) {
            $('#educationClassDiv').hide();
            $('#educationClassTbl').hide();
            $('#nesseccaryForm').hide();
            $('#optionalForm').show();
            $("#swEducationDefinitionForm .seconder").select2({
                width: '100%'
            });
             $('table#refreshNeedAssessmentInSelect').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        }
    },

    m_show_form: function () {
        $('#swEducationDefinitionTbl').slideUp();
        $('#swEducationDefinitionForm').slideDown();
        hmisEducationDefinition.tabSizeForm();

    },
    m_clean: function () {
        $('#educationClassDiv').hide();
        $('#educationClassTbl').hide();
        $('#optionalForm').hide();
        $('#abilitiesDiv').html("");
        $('#abilitiesAdd').html("");
        $('#nesseccaryForm').show();
        $("#requiredNeedAssessmentDiv").hide();
        $("#education_communicator_RoleId").val("");
        $("#education_offer_formId").val("");
        $("#educationClass_registrants_userId").val("");
        $("#education_type").val("");
        $("#education_requiredNeedAssessment").val("");
        $("#educationOptionalForm").hide("");
        $("#educationClassDiv").hide("");
        new jj("#swEducationDefinitionForm").jjFormClean();
        new jj("#educationClassForm").jjFormClean();
        new jj("#educationOptionalForm").jjFormClean();
        $("#swEducationDefinitionForm input:text").val("");
        $("#swEducationDefinitionForm  #educationClass_registrants_userId").select2({
            width: '100%'
        });
        $("#swEducationDefinitionForm #educationOptionalForm #education_offer_formId").select2({
            width: '100%'
        });
        $("#swEducationDefinitionForm #education_communicator_RoleId").select2({
            width: '100%'
        });

    },
    m_add_new: function () {
        $("#requiredNeedAssessmentDiv").hide();
        var param = "";
        param += "&do=" + hmisEducationDefinition.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationDefinition.m_clean();
        hmisEducationDefinition.m_show_form();
    },
    m_insert: function () {
        var param = "";
        var flag = true;
//        if ($("#education_requiredNeedAssessment").is(':ckecked') || $("#education_requiredNeedAssessment").not(':ckecked')) {
//            if ($("#education_title").val() == "") {
//                $("#education_title").addClass("required");
//                flag = false;
//            } else {
//                $("#education_title").removeClass('required');
//            }
//            if (flag == true) {
        param += "&do=" + hmisEducationDefinition.tableName + ".insert";
        param += "&" + new jj('#swEducationDefinitionForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisEducationDefinition.m_show_tbl();
//            }
//        }
    },
    m_edit: function () {
        var param = "";
//        var flag = true;
//        if ($("#education_requiredNeedAssessment").val() == "0") {
//
//        }
//        if ($("#education_requiredNeedAssessment").val() == "1") {
//            if ($("#education_communicatedStartDate").val() == "") {
//                $("#education_communicatedStartDate").addClass("required");
//                flag = false;
//            } else {
//                $("#education_communicatedStartDate").removeClass('required');
//            }
//            if ($("#education_communicatedEndDate").val() == "") {
//                $("#education_communicatedEndDate").addClass("required");
//                flag = false;
//            } else {
//                $("#education_communicatedEndDate").removeClass('required');
//            }
//        }
//        if (!$("#education_requiredNeedAssessment").val() == "") {
//
//            if ($("#education_title").val() == "") {
//                $("#education_title ").addClass("required");
//                flag = false;
//            } else {
//                $("#education_title").removeClass('required');
//            }
//            if (flag == true) {
//                var temp2 = "";
        var param = "";
        param += "&do=" + hmisEducationDefinition.tableName + ".edit";
        param += "&" + new jj('#swEducationDefinitionForm').jjSerial();
        param += "&id=" + new jj('#hmis_education_id').jjVal();
        new jj(param).jjAjax2(false);
        hmisEducationDefinition.m_show_tbl();
        hmisEducationDefinition.m_clean();
//            }
//        }
    },

    m_select: function (id) {
        $("#requiredNeedAssessmentDiv").show();
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".select";
        param += "&" + hmisEducationDefinition.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#abilitiesDiv').html("");
        $('#abilitiesAdd').html("");

//        if ($('#education_requiredNeedAssessment').val() == '0') {
//            $('#educationClassDiv').show();
//            $('#optionalForm').hide();
//            $('#nesseccaryForm').show();
//        }
//        if ($('#education_requiredNeedAssessment').val() == '1') {
//            $('#educationClassDiv').hide();
//            $('#optionalForm').show();
//            $('#nesseccaryForm').hide();
//
//        }
        $('#educationClassForm').hide();
        hmisEducationDefinition.m_show_form();
    },
    m_show_tbl: function () {
        $('#swEducationDefinitionTbl').slideDown();
        $('#swEducationDefinitionForm').slideUp();
        $('#refreshEducationDefinition').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisEducationDefinition.delete_after_question(" + id + ");");
    },
    delete_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisEducationDefinition.tableName + ".delete";
        param += "&" + hmisEducationDefinition.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisEducationDefinition.m_show_tbl();
        hmisEducationDefinition.m_clean();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".add_EN";
        param += "&" + hmisEducationDefinition.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationDefinition.f_parent).jjVal(id);
        new jj("#" + hmisEducationDefinition.f_lang).jjVal("2");
        hmisEducationDefinition.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".add_Ar";
        param += "&" + hmisEducationDefinition.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationDefinition.f_parent).jjVal(id);
        new jj("#" + hmisEducationDefinition.f_lang).jjVal("3");
        hmisEducationDefinition.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".add_lang";
        param += "&" + hmisEducationDefinition.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationDefinition.f_parent).jjVal(id);
        new jj("#" + hmisEducationDefinition.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationDefinition.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".searchTags";
        param += "&" + new jj('#swEducationDefinitionForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationDefinition.tableName + ".insertTags";
        param += "&" + new jj('#swEducationDefinitionForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },

    /////////////////////shiran////////////
    /**
     * انتخاب فرم
     * برای pretest post test
     * @param {type} panel
     * @returns {undefined}
     */
    selectOptionFormWithCategory: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisEducationDefinition.tableName + ".getSelectOptionWithCategory";
        new jj(param).jjAjax2(false);
    },
    selectOptionCourse: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisEducationDefinition.tableName + ".getSelectOptionCourse";
        new jj(param).jjAjax2(false);
    },
    checkAllUsersAssessment: function () {
        var Values = new Array();
        if ($('#checkAllUsersAssessment').attr("checked")) {
            $('input:checkbox[class=checkBoxUsersAssessment]').prop("checked", true);
        } else {
            $('input:checkbox[class=checkBoxUsersAssessment]').prop("checked", false);
        }
        var temp = $('#refreshNeedAssessmentInSelect input:checkbox[class=checkBoxUsersAssessment]:checked');
        for (var i = 0; i < temp.size(); i++) {
            Values.push($(temp[i]).attr("name"));
        }
        $("#education_registrants_userId").val(Values);
        $("#education_registrants_userId").select2();
    },
    checkUsersAssessment: function () {
        var Values = new Array();
        var temp = $('#refreshNeedAssessmentInSelect input:checkbox[class=checkBoxUsersAssessment]:checked');
        for (var i = 0; i < temp.size(); i++) {
            Values.push($(temp[i]).attr("name"));
        }
        $("#education_registrants_userId").val(Values);
        $("#education_registrants_userId").select2();
    }
};
var hmisEducationMyEducation = {
    tableName: "Education",
    f_id: "hmis_myEducation_id",

    loadForm: function () {
        if ($("#swEducationMyEducationForm").html() == '') {
            $("#swEducationMyEducationForm").load("formHMIS/MyEducation.html", null, function () {
                $("#cancel_myEducation").click(function (e) {
                    hmisEducationMyEducation.m_clean();
                    hmisEducationMyEducation.m_show_tbl();
                });
                hmisEducationDefinition.selectOptionCourse("#swEducationMyEducationForm  #myEducation_prerequisite_courseId");
                hmisEducationDefinition.selectOptionFormWithCategory("#swEducationMyEducationForm .selectForm");
                cmsUser.getSelectOptionNotAll("#swEducationMyEducationForm #myEducationClass_teacher_userId");
                hmisEducationDefinition.selectOptionFormWithCategory("#swEducationMyEducationForm .selectForm");

            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisEducationMyEducation.tableName + ".refreshMyEducation";
        param += "&panel=" + (containerId == null ? "swEducationMyEducationTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationMyEducation.m_show_tbl();
    },

    m_show_form: function () {
        $('#swEducationMyEducationTbl').slideUp();
        $('#swEducationMyEducationForm').slideDown();
        hmisEducationMyEducation.tabSizeForm();

    },
    m_clean: function () {
        $("#education_registrants").val("");
        new jj("#swEducationMyEducationForm").jjFormClean();
        $("#swEducationMyEducationForm input:text").val("");

    },
    m_add_new: function () {
        var param = "";
        param += "&do=" + hmisEducationMyEducation.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationMyEducation.m_clean();
        hmisEducationMyEducation.m_show_form();
    },
    m_insert: function () {
        var param = "";

        var flag = true;
//        if ($("#education_startDate").val() == "") {
//            $("#education_startDate").addClass("required");
//            flag = false;
//        } else {
//            $("#education_startDate").removeClass('required');
//        }
//        if ($("#education_endDate").val() == "") {
//            $("#education_endDate").addClass("required");
//            flag = false;
//        } else {
//            $("#education_endDate").removeClass('required');
//        }
//        if ($("#education_registrants").val() == "" || $("#education_registrants").val() == null) {
//            $("#education_registrants").addClass("required");
//            flag = false;
//        } else {
//            $("#education_registrants").removeClass('required');
//        }
//        if ($("#education_title").val() == "") {
//            $("#education_title ").addClass("required");
//            flag = false;
//        } else {
//            $("#education_title").removeClass('required');
//        }

        if (flag == true) {
            param += "&do=" + hmisEducationMyEducation.tableName + ".insert";
            param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
            new jj(param).jjAjax2(false);
            hmisEducationMyEducation.m_show_tbl();
            hmisEducationMyEducation.m_clean();
        }
    },
    m_edit: function () {
        var param = "";

        var flag = true;
        if ($("#education_startDate").val() == "") {
            $("#education_startDate").addClass("required");
            flag = false;
        } else {
            $("#education_startDate").removeClass('required');
        }
        if ($("#education_endDate").val() == "") {
            $("#education_endDate").addClass("required");
            flag = false;
        } else {
            $("#education_endDate").removeClass('required');
        }
        if ($("#education_registrants").val() == "" || $("#education_registrants").val() == null) {
            $("#education_registrants").addClass("required");
            flag = false;
        } else {
            $("#education_registrants").removeClass('required');
        }
        if ($("#education_title").val() == "") {
            $("#education_title ").addClass("required");
            flag = false;
        } else {
            $("#education_title").removeClass('required');
        }

        if (flag == true) {
            var temp2 = "";
            var param = "";
            param += "&do=" + hmisEducationMyEducation.tableName + ".edit";
            param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
//            param += "&id=" + new jj('#hmis_education_id').jjVal();
            new jj(param).jjAjax2(false);
            hmisEducationDefinition.m_show_tbl();
            hmisEducationDefinition.m_clean();
        }
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".selectMyEducation";
        param += "&" + hmisEducationMyEducation.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisEducationMyEducation.m_show_form();
    },
    m_show_tbl: function () {
        $('#swEducationMyEducationTbl').slideDown();
        $('#swEducationMyEducationForm').slideUp();
        $('#refreshEducationDefinition').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisEducationMyEducation.confirmationFinalEducationDefinition_after_question(" + id + ");");
    },

    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".add_EN";
        param += "&" + hmisEducationMyEducation.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyEducation.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyEducation.f_lang).jjVal("2");
        hmisEducationMyEducation.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".add_Ar";
        param += "&" + hmisEducationMyEducation.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyEducation.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyEducation.f_lang).jjVal("3");
        hmisEducationMyEducation.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".add_lang";
        param += "&" + hmisEducationMyEducation.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyEducation.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyEducation.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationMyEducation.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".searchTags";
        param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyEducation.tableName + ".insertTags";
        param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },

    /////////////////////shiran////////////

};

var hmisEducationClass = {
    tableName: "EducationClass",
    f_id: "hmis_educationclass_id",

    loadForm: function () {
        if ($("#swEducationClassForm").html() == '') {
            $("#swEducationClassForm").load("formHMIS/educationMyEducation.html", null, function () {
                $("#cancel_EducationClass").click(function (e) {
                    hmisEducationClass.m_clean();
                    hmisEducationClass.m_show_tbl();
                });
            });

        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisEducationClass.tableName + ".refreshMyEducation";
        param += "&panel=" + (containerId == null ? "swEducationMyEducationTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationClass.m_show_tbl();
    },

    m_show_form: function () {
        $('#swEducationMyEducationTbl').slideUp();
        $('#swEducationMyEducationForm').slideDown();
        hmisEducationClass.tabSizeForm();

    },
    m_clean: function () {
        $("#education_registrants").val("");
        new jj("#swEducationMyEducationForm #educationClassForm").jjFormClean();
        $("#swEducationMyEducationForm input:text").val("");
        $("#swEducationMyEducationForm input:text").val("");
        $("#swEducationMyEducationForm input:number").val("");
        $("#showFilesEducationClassDiv").html("");
        $("#downloadFilesEducationClassDiv").html("");
    },
    m_add_new: function () {
        $('#showFilesEducationClassDiv').html("");
        $("#downloadFilesEducationClassDiv").html("");
        var param = "";
        param += "&do=" + hmisEducationClass.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        $('#educationClassTbl').slideUp('slow');
        $('#educationClassForm').slideDown('slow');
        hmisEducationClass.m_clean();
    },
    m_insert: function () {
        var param = "";
        var flag = true;

        if ($("#education_requiredNeedAssessment").val() == "0") {
            if ($("#educationClass_startDate").val() == "") {
                $("#educationClass_startDate").addClass("required");
                flag = false;
            } else {
                $("#educationClass_startDate").removeClass('required');
            }
            if ($("#educationClass_startTime").val() == "") {
                $("#educationClass_startTime").addClass("required");
                flag = false;
            } else {
                $("#educationClass_startTime").removeClass('required');
            }
            if ($("#educationClass_endTime").val() == "") {
                $("#educationClass_endTime").addClass("required");
                flag = false;
            } else {
                $("#educationClass_endTime").removeClass('required');
            }
            if ($("#educationClass_endDate").val() == "") {
                $("#educationClass_endDate").addClass("required");
                flag = false;
            } else {
                $("#educationClass_endDate").removeClass('required');
            }
//            if ($("#educationClass_registrants_userId").val() == "" || $("#educationClass_registrants_userId").val() == null) {
//                $("#educationClass_registrants_userId").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_registrants_userId").removeClass('required');
//            }
        }
        if (flag == true) {
            var temp = "";
            var educationClass_files = $("#educationClassForm  .educationClass_files");
            for (var i = 0; i < educationClass_files.length; i++) {
                temp += $(educationClass_files[i]).val() + ",";
            }
            param += "&educationClass_files=" + temp;
            param += "&do=" + hmisEducationClass.tableName + ".insert";
            param += "&" + new jj('#educationClassForm').jjSerial();
            param += "&hmis_education_id=" + new jj('#hmis_education_id').jjVal();
            new jj(param).jjAjax2(false);
        }
    },
    m_edit: function () {
        var param = "";
        var flag = true;
//        if ($("#education_requiredNeedAssessment").val() == "الزامی") {
//            if ($("#educationClass_startDate").val() == "") {
//                $("#educationClass_startDate").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_startDate").removeClass('required');
//            }
//            if ($("#educationClass_startTime").val() == "") {
//                $("#educationClass_startTime").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_startTime").removeClass('required');
//            }
//            if ($("#educationClass_endTime").val() == "") {
//                $("#educationClass_endTime").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_endTime").removeClass('required');
//            }
//            if ($("#educationClass_endDate").val() == "") {
//                $("#educationClass_endDate").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_endDate").removeClass('required');
//            }
//            if ($("#educationClass_registrants_userId").val() == "" || $("#educationClass_registrants_userId").val() == null) {
//                $("#educationClass_registrants_userId").addClass("required");
//                flag = false;
//            } else {
//                $("#educationClass_registrants_userId").removeClass('required');
//            }
//        }
//        if (flag == true) {
        var param = "";
        var temp = "";
        var educationClass_files = $("#educationClassForm  .educationClass_files");
        for (var i = 0; i < educationClass_files.length; i++) {
            temp += $(educationClass_files[i]).val() + ",";
        }
        param += "&educationClass_files=" + temp;
        param += "&do=" + hmisEducationClass.tableName + ".edit";
        param += "&" + new jj('#educationClassForm').jjSerial();
        param += "&hmis_education_id=" + new jj('#hmis_education_id').jjVal();
        new jj(param).jjAjax2(false);
//        }
    },
    m_select: function (id) {
        $('#showFilesEducationClassDiv').html("");
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".select";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#educationClassTbl').slideUp('slow');
        $('#educationClassForm').slideDown('slow');
    },
    /**
     * ثبت نام در کلاس
     * @param {type} id کلاس
     * @returns {undefined}
     */
    registerInClass: function (id) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".registerInClass";
        param += "&hmis_myEducationClass_id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#myEducationClassTbl').slideDown('slow');
        $('#myEducationClassForm').slideUp('slow');
    },
    /**
     * لغو ثبت در این کلاس
     * @param {type} id کلاس
     * @returns {undefined}
     */
    cancelRegisterInClass: function (id) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".cancelRegisterInClass";
        param += "&hmis_myEducationClass_id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#educationClassTbl').slideDown('slow');
        $('#educationClassForm').slideUp('slow');
    },
    selectInMyEducationClass: function (id) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".selectInMyEducationClass";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $('#myEducationClassTbl').slideUp('slow');
        $('#myEducationClassForm').slideDown('slow');
        $('#downloadFilesMyEducationClassDiv').html("");
    },
    m_show_tbl: function () {
        $('#swEducationMyEducationTbl').slideDown();
        $('#swEducationMyEducationForm').slideUp();
        $('#refreshEducationDefinition').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisEducationClass.delete_after_question(" + id + ");");
    },
    delete_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisEducationClass.tableName + ".delete";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        hmisEducationClass.m_show_tbl();
//        hmisEducationClass.m_clean();
    },

    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".add_EN";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationClass.f_lang).jjVal("2");
        hmisEducationClass.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".add_Ar";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationClass.f_lang).jjVal("3");
        hmisEducationClass.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".add_lang";
        param += "&" + hmisEducationClass.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationClass.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationClass.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".searchTags";
        param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".insertTags";
        param += "&" + new jj('#swEducationMyEducationForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },

    /////////////////////shiran////////////

};

var hmisEducationMyClass = {
    tableName: "EducationClass",
    f_id: "hmis_educationMyClass_id",

    loadForm: function () {
        if ($("#swEducationMyClassForm").html() == '') {
            $("#swEducationMyClassForm").load("formHMIS/MyCalss.html", null, function () {
                $("#cancel_EducationMyClass").click(function (e) {
                    hmisEducationMyClass.m_clean();
                    hmisEducationMyClass.m_show_tbl();
                });

            });

        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisEducationMyClass.tableName + ".refreshEducationMyClass";
        param += "&panel=" + (containerId == null ? "swEducationMyClassTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationMyClass.m_show_tbl();
    },

    m_show_form: function () {
        $('#swEducationMyClassTbl').slideUp();
        $('#swEducationMyClassForm').slideDown();
        hmisEducationMyClass.tabSizeForm();

    },
    m_clean: function () {
        $("#education_registrants").val("");
        new jj("#swEducationMyClassForm").jjFormClean();
        $("#swEducationMyClassForm input:text").val("");

    },

    m_show_tbl: function () {
        $('#swEducationMyClassTbl').slideDown();
        $('#swEducationMyClassForm').slideUp();
    },

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisEducationMyEducation.confirmationFinalEducationDefinition_after_question(" + id + ");");
    },
    delete_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".delete";
        param += "&" + hmisEducationMyNeedAssessment.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        hmisEducationMyNeedAssessment.m_show_tbl(); 
//        hmisEducationMyNeedAssessment.m_clean();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".add_EN";
        param += "&" + hmisEducationMyClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyClass.f_lang).jjVal("2");
        hmisEducationMyClass.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".add_Ar";
        param += "&" + hmisEducationMyClass.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyClass.f_lang).jjVal("3");
        hmisEducationMyClass.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".add_lang";
        param += "&" + hmisEducationMyClass.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyClass.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyClass.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationMyClass.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".searchTags";
        param += "&" + new jj('#swEducationMyClassForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyClass.tableName + ".insertTags";
        param += "&" + new jj('#swEducationMyClassForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    refreshMyAnswersEducationMyClass: function (formId, containerId) {
        hmisEducationMyClass.m_show_form();
        var param = "";
        param += "do=" + hmisEducationClass.tableName + ".refreshMyAnswersEducationMyClass";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swEducationMyClassForm" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    refreshMyAnswersAfterQuestion: function (formId) {
        var win = window.open('Server?do=FormAnswerSet.add_new&formAnswers_formId=' + formId, '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEducationMyClass.refreshMyAnswersEducationMyClass(' + formId + ');');
    },
    selectMyAnswersAfterQuestion: function (formId, answerSetId) {
        var win = window.open('Server?do=FormAnswerSet.selectToEdit&formAnswers_formId=' + formId + "&id=" + answerSetId + "", '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisEducationMyClass.refreshMyAnswersEducationMyClass(' + formId + ');');
    },
};
/**
 * نیازسنجی  
 * @type type
 */
var hmisEducationMyNeedAssessment = {
    tableName: "EducationNeedAssessment",
    f_id: "hmis_educationNeedAssessment_id",
    loadForm: function () {
        if ($("#swEducationMyNeedAssessmentForm").html() == '') {
            $("#swEducationMyNeedAssessmentForm").load("formHMIS/educationMyNeedAssessment.html", null, function () {
                $("#cancel_EducationMyNeedAssessment").click(function (e) {
                    hmisEducationMyNeedAssessment.m_clean();
                    hmisEducationMyNeedAssessment.m_show_tbl();
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swEducationMyNeedAssessmentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationMyNeedAssessment.m_show_tbl();
    },
    /**
     * نمایش نظرسنجی هر فرد در سلکت
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    refreshMyNeedAssessmentInSelect: function (educationId, panel) {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".refreshMyNeedAssessmentInSelect";
        param += "&panel=" + (panel == null ? "educationNeedAssessmentTbl" : panel);
        param += "&educationNeedAssessment_education_id=" + educationId;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swEducationMyNeedAssessmentTbl').slideUp();
        $('#swEducationMyNeedAssessmentForm').slideDown();
        hmisEducationMyNeedAssessment.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swEducationMyNeedAssessmentForm").jjFormClean();
    },
    /**
     * ای دی کلاسی که اختیاری می باشد 
     * @param {type} educationId
     * @returns {undefined}
     */
    m_add_new: function () {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisEducationMyNeedAssessment.m_clean();
    },
    /**
     * 
     * نمایش  کلاس های فعال و اجباری
     * @returns {undefined}
     */
    showEducationsForm: function (educationId) {
//        alert(educationId);
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".showEducationsForm&jj=1";
        param += "&educationNeedAssessment_educationId=" + educationId;
        new jj(param).jjAjax2(false);
    },

    /**
     * اضافه کردن نیاز سنجی در تعریف دوره
     * @param {type} educationId
     * @param {type} ownerId
     * @returns {undefined}
     */
    insertEducationAssessmet: function () {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".insertEducationAssessmet";
        param += "&" + new jj("#optionalForm").jjSerial();
        param += "&educationNeedAssessment_education_id=" + new jj("#hmis_education_id").jjVal();
        param += "&education_targetGroup_groupId=" + new jj("#education_targetGroup_groupId").jjVal();

        new jj(param).jjAjax2(false);
    },

    /**
     * اضافه کردن نیاز سنجی زمان زدن دکمه ثبت نظر
     * @param {type} educationId
     * @param {type} ownerId
     * @returns {undefined}
     */
    m_insert: function (educationId, ownerId) {
        var param = "";
        var temp = $('#swEducationMyNeedAssessmentForm input:radio[name=educationNeedAssessment_isRequired_' + ownerId + ']:checked').val();
//        var temp2 = $('#swEducationMyNeedAssessmentForm input:hidden[class=seconders]');
        var temp3 = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".insert";
//        for (var i = 0; i < temp2.length; i++) {
//            temp3 += $(temp2[i]).attr('value') + ",";
//        }
        param += "&educationNeedAssessment_ownerUser_id=" + ownerId;
        param += "&educationNeedAssessment_education_id=" + educationId;
        param += "&educationNeedAssessment_isRequired=" + temp;
        param += "&educationNeedAssessmentsId=" + $("#assessmentsId_" + ownerId).val();
//        param += "&educationNeedAssessment_seconders=" + temp3;
        new jj(param).jjAjax2(false);
    },
    /**
     * 
     * @param {type} educationId
     * @returns {undefined}
     */
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".select";
        param += "&hmis_education_id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisEducationMyNeedAssessment.m_show_form();
        hmisEducationMyNeedAssessment.refreshMyNeedAssessmentInSelect(id);
    },
//    selectEducationInNeedAssessment: function (id) {
//        var param = "";
//        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".select";
//        param += "&hmis_education_id=" + (id == null ? "" : id);
//        new jj(param).jjAjax2(false);
//        hmisEducationMyNeedAssessment.m_show_form();
//        hmisEducationMyNeedAssessment.showEducationsForm($("#educationNeedAssessment_educationId").val());
//    },

    m_show_tbl: function () {
        $('#swEducationMyNeedAssessmentTbl').slideDown();
        $('#swEducationMyNeedAssessmentForm').slideUp();
//        $('#refreshEducationDefinition').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
//            destroy: true
//        });
    },
//    m_edit: function (id) {
//        var param = "";
//        var name = "";
//    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisEducationMyNeedAssessment.delete_after_question(" + id + ");");
    },
    delete_after_question: function (id) {
        var param = "";
        param += "&do=" + hmisEducationMyNeedAssessment.tableName + ".delete";
        param += "&" + hmisEducationMyNeedAssessment.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
//        hmisEducationMyNeedAssessment.m_show_tbl(); 
//        hmisEducationMyNeedAssessment.m_clean();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".add_EN";
        param += "&" + hmisEducationMyNeedAssessment.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyNeedAssessment.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyNeedAssessment.f_lang).jjVal("2");
        hmisEducationMyNeedAssessment.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".add_Ar";
        param += "&" + hmisEducationMyNeedAssessment.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyNeedAssessment.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyNeedAssessment.f_lang).jjVal("3");
        hmisEducationMyNeedAssessment.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".add_lang";
        param += "&" + hmisEducationMyNeedAssessment.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationMyNeedAssessment.f_parent).jjVal(id);
        new jj("#" + hmisEducationMyNeedAssessment.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationMyNeedAssessment.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".searchTags";
        param += "&" + new jj('#swEducationMyNeedAssessmentForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationMyNeedAssessment.tableName + ".insertTags";
        param += "&" + new jj('#swEducationMyNeedAssessmentForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    /////////////////////shiran////////////

};
/**
 * شناسنامه
 * @type type
 */
var hmisEducationCertificate = {
    tableName: "",
    f_id: "",
    loadForm: function () {
        if ($("#swEducationCertificateForm").html() == '') {
            $("#swEducationCertificateForm").load("formHMIS/educationCertificate.html", null, function () {
                $("#cancel_EducationCertificate").click(function (e) {
                });
                hmisEducationCertificate.m_show_form();
                new jj(".dateFrom").jjCalendarWithYearSelector(1397, 1420);
                new jj(".dateTo").jjCalendarWithYearSelector(1397, 1420);
                cmsUser.getSelectOptionNotAll("#swEducationCertificateForm .userSelectOption");
                $("#swEducationCertificateForm  .userSelectOption").select2({
                    width: '100%'
                });
            });
        }
    },
    m_show_form: function () {
        $('#swEducationCertificateForm').slideDown();
        hmisEducationCertificate.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swEducationCertificateForm").jjFormClean();
    },
    showTableOfCertificate: function () {
//        var param = "";
        $('#certificateSearch').attr('href', "Server?do=EducationClass.educationCertificate&dataFrom=" + $('#reportDataFrom').val() + "&dataTo=" + $('#reportDataTo').val() + "&userId=" + $('#certificateUserID').val());

    },

    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".add_EN";
        param += "&" + hmisEducationCertificate.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationCertificate.f_parent).jjVal(id);
        new jj("#" + hmisEducationCertificate.f_lang).jjVal("2");
        hmisEducationCertificate.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".add_Ar";
        param += "&" + hmisEducationCertificate.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationCertificate.f_parent).jjVal(id);
        new jj("#" + hmisEducationCertificate.f_lang).jjVal("3");
        hmisEducationCertificate.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".add_lang";
        param += "&" + hmisEducationCertificate.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisEducationCertificate.f_parent).jjVal(id);
        new jj("#" + hmisEducationCertificate.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisEducationCertificate.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".searchTags";
        param += "&" + new jj('#swEducationCertificateForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisEducationCertificate.tableName + ".insertTags";
        param += "&" + new jj('#swEducationCertificateForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    tabSizeForm: function () {
//        $('#swEducationDefinition').css('height', 'auto');
    },
    /////////////////////shiran////////////

};

        