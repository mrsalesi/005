/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisIndicatorDiscriptions = {
    tableName: "IndicatorDiscriptions",
    f_id: "id",
    loadForm: function () {
//        if ($("#swIndicatorsForm").html() == '') {
//        if (true) {
//        $('#showMyIndicatorResult').attr("href", "Server?do=Indicators.showResultDiscription&type=myIndicator");
//
//        $('#showAllIndicatorResult').attr("href", "Server?do=Indicators.showResultDiscription&type=AllIndicator");

        $('#MyIndicatorDiscriptions').attr("href", "Server?do=Indicators.showResultDiscription&type=myIndicator");
        $('#AllIndicatorDiscriptions').attr("href", "Server?do=Indicators.showResultDiscription&type=AllIndicator");
//        $("#swIndicatorDiscriptionsForm").load("formHMIS/06IndicatorReports.html", null, function () {
//            hmisDepartment.selectOptionDepartment("indicatorDiscriptions_department");
//            $("#indicatorDiscriptions_department").select2({
//                width: '100%'
//            });
//            hmisIndicators.selectOptionIndicators("#swIndicatorDiscriptionsForm .IndicatorsSelectOption");// 
        //               $('.summernote').summernote({height: 150,tooltip: false});///برای تبدیل شدن به textEditor و حذف تولتیپ برای اینکه درست تولتیپ ها اجرا نمیشود
//                new jj("#forms_creationDate").jjCalendarWithYearSelector(1340, 1420);
//                $('#forms_creationTime').wickedpicker({
//                    twentyFour: true
//                });
//            new jj("#indicatorDiscriptions_startDate").jjCalendar();
//            new jj("#indicatorDiscriptions_endDate").jjCalendar();
//                $('#forms_expireTime').wickedpicker({
//                    twentyFour: true
//                });
//                $("#formQuestions_answersType").val("select/option");//باید در فرم ست شود ولی عملا بعضی اوقات مقدار پیشفرض ست نمیشود
//                hmisIndicatorDiscriptions.selectOptionForm("#forms_nextFormId");// برای گرفتن لیست فرم های قبلی برای انتخاب فرم بعدی
//                new jj('#send_indicators_icon').jjAjaxFileUpload2('indicators_icon_file', '#indicators_icon', '#indicators_icon_Preview');
//                new jj('#send_formQuestions_icon').jjAjaxFileUpload2('formQuestions_icon_file', '#formQuestions_icon', '#formQuestions_icon_Preview');
//                new jj('#send_formQuestionOptions_icon').jjAjaxFileUpload2('formQuestionOptions_icon_file', '#formQuestionOptions_icon', '#formQuestionOptions_Preview');
//                new jj('#formQuestions_weight').jjDigitOnly(10, 20);
//                hmisRole.getSelectOption("#swIndicatorDiscriptionsForm.roleSelectOption");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                $(".roleSelectOption").select2({
//                    width: '100%'
//                });
////                hmisRole.getUeserRolesSelectOption("#swIndicatorDiscriptionsForm#forms_ownerRole");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                cmsUser.getSelectOption("#swIndicatorDiscriptionsForm.userSelectOption");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                $(".userSelectOption").select2({
//                    width: '100%'
//                });
//                cmsUser.getSelectOptionNotAll("#swIndicatorDiscriptionsForm#indicators_responsibleUser");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                $(".userSelectOption").select2({
//                    width: '100%'
//                });
//                $("#indicators_responsibleRole").on('select2:select', function (e) {// برای اینکه در یک زمان فقط یکی از بین نقش و کاربر را بتواند انتخاب کند
//                    $("#indicators_responsibleUser").val("");
//                    $("#indicators_responsibleUser").trigger("change");
//                    if ($('#indicators_responsibleRole').val().indexOf("formAnswers_userRole") >= 0) {
//                        $("#indicators_responsibleRole").select2("val", ["formAnswers_userRole"]);
//                    }
//                });
//                $("#indicators_responsibleUser").on('select2:select', function (e) {
//                    $("#indicators_responsibleRole").val("");
//                    $("#indicators_responsibleRole").trigger("change");
//                    if ($('#indicators_responsibleUser').val().indexOf("formAnswers_userName") >= 0) {
//                        $("#indicators_responsibleUser").select2("val", ["formAnswers_userName"]);
//                    }
//                });
//                hmisForms.selectOptionForm("#swIndicatorDiscriptionsForm .formSelectOption");
//
//        });
//        }
    },
    m_refresh: function (indicatorId, containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisIndicatorDiscriptions.tableName + ".refresh";
        param += "&id=" + indicatorId;
        param += "&panel=" + (containerId == null ? "refreshIndicatorDiscriptionsDiv" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += (tableHeight == null ? "" : "&height=" + tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisIndicatorDiscriptions.m_show_tbl();
    },
    m_show_form: function () {
        $('#swIndicatorDiscriptionsTbl').hide();
        $('#swIndicatorDiscriptionsForm').show();
        hmisIndicatorDiscriptions.tabSizeForm();
        hmisIndicatorDiscriptions.m_clean();
    },
    m_clean: function () {
        new jj("#indicatorDescriptionDiv").jjFormClean();
        $('#showIndicatorDiscriptionDownloadFiles').html("");
        $('#showIndicatorDiscriptionAttachedFiles').html("");
        $('#indicatiorDiscriptions_discription').val("");
        $("#indicatiorDiscriptions_discription").summernote('');     
//        $("#showIndicatorDiscriptionAttachedFiles").html("");

    },
    m_add_new: function () {
        new jj("do=" + hmisIndicatorDiscriptions.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisIndicatorDiscriptions.m_show_form();
        $('#indicatorDescriptionForm').show();
        $('#refreshIndicatorDiscriptionsDiv').hide();
        hmisIndicatorDiscriptions.m_clean();
    },
    m_show_tbl: function () {
        $('#swIndicatorDiscriptionsTbl').show();
        $('#swIndicatorDiscriptionsTbl table').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        $('#swIndicatorDiscriptionsForm').hide();
        $('#formQuestions').hide();
    },

    m_insert: function () {
        if (new jj('#indicatiorDiscriptions_date').jjVal() == "") {
            new jj('تاریخ را وارد کنید').jjModal("تاریخ تحلیل نباید تهی باشد");
            return;
        }
        if (new jj('#indicatiorDiscriptions_title').jjVal() == "") {
            new jj('عنوان تحلیل را وارد کنید').jjModal("حداکثر500 کاراکتر");
            return;
        }
        var param = "";
        var attachFilesMulti = $(".indicatiorDiscriptions_files");
        param += "do=" + this.tableName + ".insert";
        var temp = ""
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        param += "&indicatiorDiscriptions_files=" + temp;
        param += "&" + new jj('#indicatorDescriptionDiv').jjSerial();
        new jj(param).jjAjax2(false);
        $('#refreshIndicatorDiscriptionsDiv').slideDown();
        $('#indicatorDescriptionForm').slideUp();
    },
    m_edit: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorDiscriptions.tableName + ".edit";
        param += "&" + hmisIndicatorDiscriptions.f_id + "=" + (id == null ? "" : id);
        var attachFilesMulti = $(".indicatiorDiscriptions_files");
        var temp = ""
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        param += "&indicatiorDiscriptions_files=" + temp;
        param += "&" + new jj('#indicatorDescriptionDiv #indicatorDescriptionForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisIndicatorDiscriptions.m_clean();
        $('#refreshIndicatorDiscriptionsDiv').slideDown();
        $('#indicatorDescriptionForm').slideUp();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("حذف شاخص", "hmisIndicatorDiscriptions.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorDiscriptions.tableName + ".delete";
        param += "&" + hmisIndicatorDiscriptions.f_id + "=" + (id == null ? "" : id);
        param += "&indicatiorDiscriptions_indicatorId=" + new jj('#indicatiorDiscriptions_indicatorId').jjVal();
        new jj(param).jjAjax2(false);
        $('#refreshIndicatorDiscriptionsDiv').slideDown();
        $('#indicatorDescriptionForm').slideUp();
        hmisIndicatorDiscriptions.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorDiscriptions.tableName + ".select";
        param += "&" + hmisIndicatorDiscriptions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisIndicatorDiscriptions.m_clean();
        $('#refreshIndicatorDiscriptionsDiv').slideUp();
        $('#indicatorDescriptionForm').slideDown();
    },
    //<============ BY RASHIDI ========    

    tabSizeTbl: function () {
        $('#swIndicatorDiscriptions').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swIndicatorDiscriptions').css('height', 'auto');
    }
};    