/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisIndicators = {
    tableName: "Indicators",
    f_id: "id",
    loadForm: function () {

        if ($("#swIndicatorsForm").html() == '') {
            $("#swIndicatorsForm").load("formHMIS/06newIndicator.html", null, function () {
                hmisIndicators.selectOptionForm(".formSelectOption");
                $("#indicators_scop").select2({
                    width: '100%'
                });
                $("#indicators_dimension").select2({
                    width: '100%'
                });
                $("#indicators_type").select2({
                    width: '100%'
                });
                $("#indicators_form_a").select2({
                    width: '100%'
                });
                $("#indicators_form_b").select2({
                    width: '100%'
                });
                $("#indicators_form_c").select2({
                    width: '100%'
                });

               
//                $("#indicators_form_e").select2({
//                    width: '100%'
//                });
//                $("#indicators_form_f").select2({
//                    width: '100%'
//                });
//                $("#indicators_form_g").select2({
//                    width: '100%'
//                });

//                $("#indicators_filterQuestion_a").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_a").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_b").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_c").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_d").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_e").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_f").select2({
//                    width: '100%'
//                });
//                $("#indicators_filterQuestion_g").select2({
//                    width: '100%'
//                });
//       
                new jj("#indicators_startDate").jjCalendar();
                hmisDepartment.selectOptionDepartment("#indicators_department_id");
                new jj('#send_indicators_icon').jjAjaxFileUpload2('indicators_icon_file', '', '#indicators_icon', '#indicators_icon_Preview');
                hmisRole.getSelectOption("#swIndicatorsForm .roleSelectOption"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                $(".roleSelectOption").select2({
                    width: '100%'
                });
//                hmisRole.getUeserRolesSelectOption("#swIndicatorsForm #forms_ownerRole");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                cmsUser.getSelectOption(".userSelectOption"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                $(".userSelectOption").select2({
                    width: '100%'
                });
                cmsUser.getSelectOptionNotAll("#indicators_responsibleUser"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                $(".userSelectOption").select2({
//                    width: '100%'
//                });
//                $("#forms_category_id").select2({
//                    minimumResultsForSearch: '',
//                    width: '100%'
//                });
//                $("#forms_departments").select2({
//                    minimumResultsForSearch: '',
//                    width: '100%'
//                });
//                $("#forms_accesseRoles").select2({
//                    minimumResultsForSearch: '',
//                    
//                    width: '100%'
//                });
//                hmisIndicators.m_refresh();
                $("#indicators_responsibleRole").on('select2:select', function (e) {// برای اینکه در یک زمان فقط یکی از بین نقش و کاربر را بتواند انتخاب کند
                    $("#indicators_responsibleUser").val("");
                    $("#indicators_responsibleUser").trigger("change");
                    if ($('#indicators_responsibleRole').val().indexOf("formAnswers_userRole") >= 0) {
                        $("#indicators_responsibleRole").select2("val", ["formAnswers_userRole"]);
                    }
                });
                $("#indicators_responsibleUser").on('select2:select', function (e) {
                    $("#indicators_responsibleRole").val("");
                    $("#indicators_responsibleRole").trigger("change");
                    if ($('#indicators_responsibleUser').val().indexOf("formAnswers_userName") >= 0) {
                        $("#indicators_responsibleUser").select2("val", ["formAnswers_userName"]);
                    }
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisIndicators.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swIndicatorsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += (tableHeight == null ? "" : "&height=" + tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisIndicators.m_show_tbl();
    },
    m_show_form: function () {
        $('#swIndicatorsTbl').hide();
        $('#swIndicatorsForm').show();
        hmisIndicators.tabSizeForm();
        hmisIndicators.m_clean();
    },
    m_clean: function () {
        new jj("#swIndicatorsForm").jjFormClean();
        $("#swIndicatorsForm .img-thumbnail").attr("src", "img/preview.jpg");
        $("#swIndicatorsForm .select2-hidden-accessible").val("");
        $("#swIndicatorsForm .select2-hidden-accessible").select2({with : '100%'});
    },
    m_add_new: function () {
        new jj("do=" + hmisIndicators.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisIndicators.m_show_form();
        $('#newCommetteForm').show();
        $('#formInvitation').hide();
        hmisIndicators.m_clean();
    },
    m_show_tbl: function () {
        $('#indicatorAdd').html("");
        $('#swIndicatorsTbl').show();
        $('#swIndicatorsTbl table').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        $('#swIndicatorsForm').hide();
        $('#formQuestions').hide();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + this.tableName + ".insert";
        param += "&" + new jj('#swIndicatorsForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    m_edit: function (id) {
        var param = "";
        param += "do=" + hmisIndicators.tableName + ".edit";
        param += "&" + new jj('#swIndicatorsForm #newIndicator').jjSerial();
        new jj(param).jjAjax2(false);
        hmisIndicators.m_refresh();
        hmisIndicators.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("حذف شاخص", "hmisIndicators.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisIndicators.tableName + ".delete";
        param += "&" + hmisIndicators.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisIndicators.m_show_tbl();
        hmisIndicators.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisIndicators.tableName + ".select";
        param += "&" + hmisIndicators.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisIndicators.m_show_form();
        hmisIndicators.m_clean();
        $("#formQuestions").show(); // در سلکت فرم افزودن سوالات را نسان میدهیم چون وقتی فرم نداریم نباید بتوانیم سوالی را ایجاد کنیم
        hmisFormQuestions.m_refresh(id); //ّبرای نشان دادن گزینه های این فرم
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swIndicators').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swCommettes').css('height', 'auto');
    },
    /**
     * سلکت همه ی فرم های موجود
     * @param {selector} panel selector .,#,..
     * @returns {از سمت سرور اسکریپت جی کوئری بر می گردد}
     */
    selectOptionForm: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisIndicators.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    /**
     *سلکت شاخص های فعال و
     *مربوط به مسئول 
     *شاخص و افرادی که به نتیج نمودارها دسترسی دارند
     * @param {type} panel
     * @returns {undefined}
     */
    getSelectOptionIndicators: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisIndicators.tableName + ".getSelectOptionIndicators";
        new jj(param).jjAjax2(false);
    },
    addd: function () {
        var i = 1;
        var available = $(".indicatorDiv");
        for (var j = 1; j < 7; j++) {
            var k = (j + 1);
            var num1 = String.fromCharCode(96 + k);
            if ($("#indicators_form_" + num1).length == 0) {
                i = j + 1;
                j = 8;
            }
           
        }
        if (i <= 3 || i > 7) {
            return;
        }
        var num2 = String.fromCharCode(96 + i);
        var temp = $('#rowsIndicator').html().replace(/_x/igm, ("_" + num2));
        $("#indicatorAdd").append(temp);
//<<<<<<< HEAD

//=======
        $("#indicators_form_d").select2({
            width: '100%'
        });
        $("#indicators_form_e").select2({
            width: '100%'
        });
        $("#indicators_form_f").select2({
            width: '100%'
        });
        $("#indicators_form_g").select2({
            width: '100%'
        });
        $("#indicators_filterOption_d").select2({
            width: '100%'
        });
        $("#indicators_filterOption_e").select2({
            width: '100%'
        });
        $("#indicators_filterOption_f").select2({
            width: '100%'
        });
        $("#indicators_filterOption_g").select2({
            width: '100%'
        });
//>>>>>>> origin/master
        $("#indicatorAdd .indicatorDivTemp").addClass("indicatorDiv");
        $("#indicatorAdd .indicatorDivTemp").removeClass("indicatorDivTemp");
        
                $("#indicators_form_d").select2({
                    width: '100%'
                });
                $("#indicators_form_e").select2({
                    width: '100%'
                });
                $("#indicators_form_f").select2({
                    width: '100%'
                });
                $("#indicators_form_g").select2({
                    width: '100%'
                });
//              
    },
    /**
     * بدلیل اینکه سلکت در جاوا به دلیل نام پنل بهم میریزد و
     * تابع  
     * onchange 
     * کار نمی کند 
     * از این دو تابع استفاده شده
     * y=$(this)
     * @param {type} y
     * @returns {undefined}
     */
    getNumericalQuestionAsOption2: function (y) {
        y.val();
        var x = y.attr('id');
        var lastChr = x[x.length - 1];//last _b,_c,_d,_e,_f,_g
        hmisFormQuestions.getNumericalQuestionAsOptionLimit(y.val(), "#indicators_" + lastChr + "");
    },
    getOptionForFilter2: function (y) {
        y.val();
        var x = y.attr('id');
        var lastChr = x[x.length - 1];//last _b,_c,_d,_e,_f,_g
        hmisFormQuestions.getOptionForFilter(y.val(), "#indicators_filterOption_" + lastChr + "");
    }

};
////////////////////////////////
////////////////////////////////
////////////////////////////////
////////////////////////////////اطلاعات شاخص
var hmisIndicatorsInformation = {
    tableName: "Indicators",
    f_id: "id",
    loadForm: function () {
        if ($("#swIndicatorsInformationForm").html() == '') {
            $("#swIndicatorsInformationForm").load("formHMIS/05nursing.html", null, function () {
//                $("#cancel_AssessmentForSessionCategory").click(function (e) {
//                    hmisNursing.m_clean();
//                    hmisNursing.m_show_tbl();   
//                });
            });
        }

    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisIndicatorsInformation.tableName + ".refreshIndicatorsInformation";
        param += "&panel=" + (containerId == null ? "swIndicatorsInformationTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += (tableHeight == null ? "" : "&height=" + tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisIndicatorsInformation.m_show_tbl();
    },
    refreshMyAnswerInIndicatorsInformation: function (formId, containerId) {

        hmisIndicatorsInformation.m_show_form();
        var param = "";
        param += "do=" + hmisIndicators.tableName + ".refreshMyAnswerInIndicatorsInformation";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swIndicatorsInformationForm" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisFormAnswerSets.m_show_form();
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisIndicatorsInformation.refreshMyAnswerInIndicatorsInformation(' + formId + ');');
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
        new jj("لیست پاسخ های شما تازه سازی بشود ؟").jjModal_Yes_No("آیا فرم شما با موفقیت ثبت شد?", 'hmisIndicatorsInformation.refreshMyAnswerInIndicatorsInformation(' + formId + ');');
    },
    m_show_form: function () {
        $('#swIndicatorsInformationTbl').hide();
        $('#swIndicatorsInformationForm').show();
        hmisIndicatorsInformation.tabSizeForm();
        hmisIndicatorsInformation.m_clean();
    },
    m_clean: function () {
        new jj("#swIndicatorsInformationForm").jjFormClean();
        $("#swIndicatorsInformationForm .img-thumbnail").attr("src", "img/preview.jpg");
        $("#swIndicatorsInformationForm .select2-hidden-accessible").val("");
        $("#swIndicatorsInformationForm .select2-hidden-accessible").select2({with : '100%'});
    },
    m_add_new: function () {
        new jj("do=" + hmisIndicatorsInformation.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisIndicatorsInformation.m_show_form();
        hmisIndicatorsInformation.m_clean();
    },
    m_show_tbl: function () {
        $('#swIndicatorsInformationTbl').show();
        $('#swIndicatorsInformationTbl table').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        $('#swIndicatorsInformationForm').hide();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + this.tableName + ".insert";
        param += "&" + new jj('#swIndicatorsInformationForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    m_edit: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorsInformation.tableName + ".edit";
        param += "&" + new jj('#swIndicatorsInformationForm #newIndicator').jjSerial();
        new jj(param).jjAjax2(false);
        hmisIndicatorsInformation.m_refresh();
        hmisIndicatorsInformation.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("حذف شاخص", "hmisIndicatorsInformation.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorsInformation.tableName + ".delete";
        param += "&" + hmisIndicatorsInformation.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisIndicatorsInformation.m_show_tbl();
        hmisIndicatorsInformation.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisIndicatorsInformation.tableName + ".select";
        param += "&" + hmisIndicatorsInformation.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisIndicatorsInformation.m_show_form();
        hmisIndicatorsInformation.m_clean();
        $("#formQuestions").show(); // در سلکت فرم افزودن سوالات را نسان میدهیم چون وقتی فرم نداریم نباید بتوانیم سوالی را ایجاد کنیم
        hmisFormQuestions.m_refresh(id); //ّبرای نشان دادن گزینه های این فرم
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swIndicatorsInformation').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swCommettes').css('height', 'auto');
    },
};
