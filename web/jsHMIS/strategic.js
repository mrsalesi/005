/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisStrategic = {
    tableName: "Strategic",
    f_id: "id",
    loadForm: function () {
        if ($("#swStrategicForm").html() == '') {
            $("#swStrategicForm").load("formHMIS/13strategicPlan.html", null, function () {
                $("#cancel_Strategic").click(function (e) {
                    hmisStrategic.m_clean();
                    hmisStrategic.m_show_tbl();
                });
            });
            $("#targetForm").hide();
            $("#strategicStep2").hide();
            $("#StrategicStep2-1").hide();
            $("#communicationForm").hide();
            $("#proprietaryTargetSelect").select2({
                width: '100%'
            });
            $("#targetTotalSelect").select2({
                width: '100%'
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swStrategicTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swStrategicTbl').slideUp();
        $('#swStrategicForm').slideDown();
        hmisStrategic.tabSizeForm();
    },
    m_clean: function () {
        new jj('#swStrategicForm').jjFormClean();
        $('#tableIFE input.onkeyup').attr('value', '');
        $('#IFEandEFE textarea.onkeyup').html('');
        $('#tableEFE input.onkeyup').attr('value', '');
        $('#tableIFE input.onclick').attr('value', '');
        $('#tableEFE input.onclick').attr('value', '');
        $('#tableIFE input.sumFinalVal').attr('value', '');
        $('#tableEFE input.sumFinalVal').attr('value', '');
        $('input.form-control').attr('value', '');
        $('input.onkeyup').attr('value', '');
        $('textarea.onkeyup').html("");
        $("#pointer").css("transform", "translate(0,0)");
        $('input').removeClass('red');
        $('input').removeClass('green');
        $("#tablePrioritizedDiv").html("");
        $('div.powerLowAndMotivationLow ').html("");
        $('div.powerLowAndMotivationHigh ').html("");
        $('div.powerHighAndMotivationHigh ').html("");
        $('div.powerHighAndMotivationLow ').html("");
        $('.chanceIFE').html("");
        $('.chanceEFE').html("");
        $('.weaknessIFE').html("");
        $('.weaknessEFE').html("");
    },
    m_add_new: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisStrategic.m_clean();
        $('#swStrategicForm').show();
        $('#swStrategicTbl').hide();
        $('#targetDiv').hide();
        $("#strategicStep2").hide();
        $("#communicationForm").hide();
        $("#StrategicStep2-1").hide();
        $('div#pointer').css({'transform': 'translate(0,0)'});
        $("#tablePrioritized").html("");
        $("#IFETbl tr.rowtableIFE").html("");
        $("#EFETbl tr.rowtableEFE").html("");
        $("#tablePrioritizedDiv").html("");
        $("#BeneficiariesInTbl tr.rowtableBeneficiariesIn").html("");
        $("#BeneficiariesOutTbl tr.rowtableBeneficiariesOut").html("");
        $("#Beneficiaries3Tbl").html("");
        $("#Beneficiaries4Tbl").html("");
        $("#tableQSPM1").html("");
        $("#tableQSPM2").html("");
        $("#tableQSPM3").html("");
        $("#tableQSPM4").html("");
    },
    m_show_tbl: function () {
        $('#swStrategicTbl').show();
        $('#swStrategicForm').hide();
        $('#refreshStrategic').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
//    m_insert: function () {
//        if ($("#tablePrioritizedDiv").html() != "" && $("#proprietaryTargetSelect").val() != null) {
//
//            new jj("از ثبت استراتژی ها اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", "hmisStrategic.m_insert_after_question();");
//        } else {
//            new jj("استراتژی ها و اهداف اختصاصی را تعریف کنید.").jjModal("پیام سامانه");
//        }
//    },
////insertStrategy
//    m_insert_after_question: function () {
//        var temp4 = $("#tablePrioritized tr.strategy");
//        var temp5 = "";
//        for (var i = 0; i < temp4.length; i++) {
//            temp5 += $(temp4[i]).find(".titleStrategy").html() + "%23A%23" + $(temp4[i]).find(".pointsStrategy").html() + "%23A%23" + $(temp4[i]).find(".indicatorUrlStrategy").val() + ",";
//
//        }
//        var param = "";
//        param += "&do=" + hmisStrategic.tableName + ".insertStrategy";
//        param += "&proprietaryTargetSelect=" + $("#proprietaryTargetSelect").val();
//        param += "&strategic_matrixQSPM=" + encodeURIComponent($("#tableQSPMDiv").html());
//        param += "&strategic_prioritized=" + $("#tablePrioritizedDiv").html();
//        param += "&trStrategy=" + temp5;
//        param += "&" + new jj('#swStrategicForm').jjSerial();
//        new jj(param).jjAjax2(false);
//        hmisStrategic.m_show_tbl();
//    },
    /**ثبت موقت
     * 
     * @returns {undefined}
     */
    temporaryInsert: function () {
        if (confirm("از ثبت برنامه اطمینان دارید؟")) {
            hmisStrategic.m_temporaryInsert_after_question();
        } else {
        }
//        new jj("از ثبت برنامه اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", "hmisStrategic.m_temporaryInsert_after_question();");
    },
    m_temporaryInsert_after_question: function () {

        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".temporaryInsert";
        param += "&strategic_IFEInternal=" + encodeURIComponent($("#tableIFE").html());
        param += "&strategic_EFEOuter=" + encodeURIComponent($("#tableEFE").html());
        param += "&strategic_IFEandEFE=" + encodeURIComponent($("#IFEandEFE").html());
        param += "&strategic_beneficiaries=" + encodeURIComponent($("#beneficiaries").html());
        param += "&strategic_matrixSWOT=" + encodeURIComponent($("#SWOTMatrixTable").html());
        param += "&strategic_matrixSWOT2=" + encodeURIComponent($("#SWOTMatrixTable2").html());
        param += "&strategic_strategicSWOT=" + encodeURIComponent($("#StartegiesTableOnSWOT").html());
        param += "&strategic_QSPM=" + encodeURIComponent($("#tableQSPMDiv").html());
        param += "&strategic_prioritized=" + encodeURIComponent($("#tablePrioritizedDiv").html());
        param += "&" + new jj('#swStrategicForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisStrategic.m_show_tbl();
    },

    m_edit: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".edit";
        param += "&strategic_beneficiaries=" + encodeURIComponent($("#beneficiaries").html());
        param += "&strategic_IFEInternal=" + encodeURIComponent($("#tableIFE").html());
        param += "&strategic_EFEOuter=" + encodeURIComponent($("#tableEFE").html());
        param += "&strategic_IFEandEFE=" + encodeURIComponent($("#IFEandEFE").html());
        param += "&strategic_matrixSWOT=" + encodeURIComponent($("#SWOTMatrixTable").html());
        param += "&strategic_matrixSWOT2=" + encodeURIComponent($("#SWOTMatrixTable2").html());
        param += "&strategic_strategicSWOT=" + encodeURIComponent($("#StartegiesTableOnSWOT").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        param += "&" + new jj('#swStrategicForm').jjSerial();
        new jj(param).jjAjax2(false);
//        hmisStrategic.m_show_tbl();
    },

    /**
     * ثبت قسمت ارزیابی داخلی و خارجی
     * @returns {undefined}
     */
    m_editQSPM: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editQSPM";
        param += "&strategic_QSPM=" + encodeURIComponent($("#tableQSPMDiv").html());
        param += "&strategic_prioritized=" + encodeURIComponent($("#tablePrioritizedDiv").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisStrategic.m_show_tbl();
    },
    editIFEandEFE: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editIFEandEFE";
        param += "&strategic_IFEandEFE=" + encodeURIComponent($("#IFEandEFE").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisStrategic.m_show_tbl();
    },
    editSWOTMatrixTable: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editSWOTMatrixTable";
        param += "&strategic_matrixSWOT=" + encodeURIComponent($("#SWOTMatrixTable").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    editStartegiesTableOnSWOT: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editStartegiesTableOnSWOT";
        param += "&strategic_strategicSWOT=" + encodeURIComponent($("#StartegiesTableOnSWOT").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    editSWOTMatrixTable2: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editSWOTMatrixTable2";
        param += "&strategic_matrixSWOT2=" + encodeURIComponent($("#SWOTMatrixTable2").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    editBeneficiaries: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editBeneficiaries";
        param += "&strategic_beneficiaries=" + encodeURIComponent($("#beneficiaries").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    editTableIFE: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editTableIFE";
        param += "&strategic_IFEInternal=" + encodeURIComponent($("#tableIFE").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    editTableEFE: function () {
        var param = "";
        param += "&do=" + hmisStrategic.tableName + ".editTableEFE";
        param += "&strategic_EFEOuter=" + encodeURIComponent($("#tableEFE").html());
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    /**
     * 
     * @param {type} id
     * @returns {undefined}
     */

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisStrategic.confirmationFinalStrategic_after_question(" + id + ");");
    },
    
    /**
     * نمایش لیست برنامه های استراتژیک
     * @returns {undefined}
     */
    getSelectOptionStrategic: function () {
        new jj("do=Strategic.getSelectOptionStrategic").jjAjax2(false);
    },
    /**
     * تایید نهایی استراتژیک
     * @param {type} id
     * @returns {undefined}
     * 
     */
    confirmationFinalStrategic_after_question: function (id) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".delete";
        param += "&" + hmisStrategic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisStrategic.m_show_tbl();
    },
    m_select: function (id) {
        hmisStrategic.m_clean();
        hmisRole.getSelectOptionRequierd("#swStrategicForm #strategic_reciversRoles"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
        cmsUser.getSelectOptionNotAll("#swStrategicForm #strategic_reciversUsers");
        $("#strategic_reciversUsers").select2({
            width: '100%'
        });
        $("#strategic_reciversRoles").select2({
            width: '100%'
        });
        $("#targetDiv").show();
        $("#targetForm").hide();
        $("#communicationForm").show();
        $("#strategicStep2").show();
        $("#StrategicStep2-1").show();
        $("#tablePrioritized").html("");
        $("#tableQSPMDiv").html("");
        $("#tableBeneficiaries4").html("");
        $("#tableBeneficiaries3").html("");
        $("#proprietaryTargetSelect").select2({
            width: '100%'
        });
        $("#targetTotalSelect").select2({
            width: '100%'
        });
        if ($("#tableBeneficiaries3 table tr.rowBeneficiaries3Tbl").length > 0) {
            alert($("#tableBeneficiaries3 table tr.rowBeneficiaries3Tbl").length)
            $("#tableBeneficiaries3 table").dataTable({searching: false, destroy: true, paging: false, "aasorting": [[0, 'desc']], "bInfo": false});
        }
        if ($("#tableBeneficiaries4 table tr.rowBeneficiaries4Tbl").length > 0) {
            $("#tableBeneficiaries4 table").dataTable({searching: false, destroy: true, paging: false, "aasorting": [[0, 'desc']], "bInfo": false});
        }
        hmisTotalTarget.getTargetForStrategic(id); //برای نمایش سلکت اهداف اختصاصی
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".select";
        param += "&" + hmisStrategic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisStrategic.m_show_form();
        hmisTotalTarget.m_refresh(id); //نمایش جدول اهداف کلی 
        hmisStrategy.m_refresh(id); //نمایش جدول  استراتژی
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".add_EN";
        param += "&" + hmisStrategic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisStrategic.f_parent).jjVal(id);
        new jj("#" + hmisStrategic.f_lang).jjVal("2");
        hmisStrategic.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".add_Ar";
        param += "&" + hmisStrategic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisStrategic.f_parent).jjVal(id);
        new jj("#" + hmisStrategic.f_lang).jjVal("3");
        hmisStrategic.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".add_lang";
        param += "&" + hmisStrategic.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisStrategic.f_parent).jjVal(id);
        new jj("#" + hmisStrategic.f_lang).jjVal(langId);
        hmisStrategic.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".searchTags";
        param += "&" + new jj('#swStrategicForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisStrategic.tableName + ".insertTags";
        param += "&" + new jj('#swStrategicForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    validateInput: function (inputValue) {


        var inputNumber = /^[0-9]*$/;
        return inputNumber.test(inputValue);
    },
    regexInput: function (obj) {
        alert(obj);
        var inputValue = $(obj).val();
//        hmisStrategic.validateInput(inputValue);
        $(obj).attr('value', inputValue);
    },
    /**
     * آی دی یک جدول را میگیرد و برای محاسبه نهایی ریسک و اهمیت نقاط ضعف و قوت ستون های وزن آنها را نرمال می کند تا مجموع هر ستون صد بشود
     * @param {type} TableId
     * @returns {void}
     *برای جدول های ارزیابی ذاخلی  به جدول های دیگر هم اضافه می شود
     */

    normalizeTable: function (TableId) {
        var sum = 0;
        var fillInputNo = 0; //تعداد پر شده
        var lenght = $("#" + TableId + " tr.row" + TableId + "").length;
        for (var j = 0; j < 10; j++) {//ستون ها
            for (var i = 0; i < lenght; i++) {//سطرها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            for (var k = 0; k < 5; k++) {
                var dif = 100 - sum;
                var mul = dif / 100;
                sum = 0; //برای محاسبه مجموع جدید بعد از نرمال سازی
                for (var i = 1; i < lenght; i++) {//سطرها
                    var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //چون ستون اول تکست اریا هست یک اضافه می کنیم
                    if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                        if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                            var oldVal = parseFloat($(nextInput).val());
                            var newVal = oldVal + (oldVal * mul);
                            $(nextInput).attr('value', newVal.toFixed(2));
                            sum += newVal;
                        }
                    }
                }
            }
            $(("#" + TableId + " .sumWeiths" + (j + 1))).attr('value', sum.toFixed(2)); //باید کلاسی مثل sumWeeiths1,2,3,... را داشته باشد
            mul = 1;
            sum = 0;
            hmisStrategic.calculteStrategicTable(TableId);
        }
//        hmisStrategic.addRowTableQSPM1();
    },
    calculteTableBeneficiaryIn: function (TableId) {
        var sum = 0;
        var fillInputNo = 0; //تعداد پر شده

        var lenght = $("#" + TableId + " tr.row" + TableId + "").length;
        for (var j = 0; j < 10; j++) {//ستون ها
            for (var i = 0; i < lenght; i++) {//سطرها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            for (var k = 0; k < 5; k++) {
                var dif = 100 - sum;
                var mul = dif / 100;
                sum = 0; //برای محاسبه مجموع جدید بعد از نرمال سازی
                for (var i = 0; i < lenght; i++) {//سطرها
                    var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //چون ستون اول تکست اریا هست یک اضافه می کنیم
                    if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                        if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                            var oldVal = parseFloat($(nextInput).val());
//                            var newVal = oldVal + (oldVal * mul);
//                            $(nextInput).attr('value', newVal);
//                            sum += newVal;
                            $(nextInput).attr('value', oldVal);
                            sum += oldVal;
                        }
                    }
                }
            }
            $(("#" + TableId + " .sumWeiths" + (j + 1))).attr('value', sum); //باید کلاسی مثل sumWeeiths1,2,3,... را داشته باشد
            mul = 1;
            sum = 0;
            hmisStrategic.calculteStrategicTable(TableId);
        }
        hmisStrategic.addRowBeneficiaries3();
    },
    calculteTableBeneficiaryOut: function (TableId) {
        var sum = 0;
        var fillInputNo = 0; //تعداد پر شده

        var lenght = $("#" + TableId + " tr.row" + TableId + "").length;
        for (var j = 0; j < 10; j++) {//ستون ها
            for (var i = 0; i < lenght; i++) {//سطرها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            for (var k = 0; k < 5; k++) {
//                var dif = 100 - sum;
//                var mul = dif / 100;
                sum = 0; //برای محاسبه مجموع جدید بعد از نرمال سازی
                for (var i = 0; i < lenght; i++) {//سطرها
                    var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //چون ستون اول تکست اریا هست یک اضافه می کنیم
                    if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                        if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                            var oldVal = parseFloat($(nextInput).val());
//                            var newVal = oldVal + (oldVal * mul);
//                            $(nextInput).attr('value', newVal);
//                            sum += newVal;
                            $(nextInput).attr('value', oldVal);
                            sum += oldVal;
                        }
                    }
                }
            }
            $(("#" + TableId + " .sumWeiths" + (j + 1))).attr('value', sum); //باید کلاسی مثل sumWeeiths1,2,3,... را داشته باشد
//            mul = 1;
            sum = 0;
            hmisStrategic.calculteStrategicTable(TableId);
        }
        hmisStrategic.addRowBeneficiaries4();
    },
    calculteStrategicTable: function (TableId) {

        //میانگین وزن های هر سطر
        var sum = 0;
        var lenght = $("#" + TableId + " tr.row" + TableId + "").length;
        var fillInputNo = 0; //تعداد پر شده
        for (var i = 1; i < lenght; i++) {//سطرها
            for (var j = 0; j < 10; j++) {//ستون ها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            var avgWeith = parseFloat(sum / fillInputNo);
            if (isNaN(avgWeith)) {
                $("#" + TableId + " .avgWeith" + (i + 1)).attr('value', "0"); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد
            } else {
                $("#" + TableId + " .avgWeith" + (i + 1)).attr('value', avgWeith.toFixed(2)); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد

            }

            sum = 0;
            fillInputNo = 0;
        }
        //***********************************************
        //ماینگین رتبه های هر سطر
        var finalVal = 0;
        var sumFinalVal = 0;
        for (var i = 0; i < lenght; i++) {//سطرها
            sum = 0;
            fillInputNo = 0; //تعداد پر شده
            for (var j = 0; j < 10; j++) {//ستون ها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.value:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
//                var inPutsInAllCol = $(nexttr).children("td input:nth-child(" + (i) + ")");                
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            var avgVals = parseFloat(sum / fillInputNo);
            if (isNaN(avgVals)) {
//            if (!avgVals.jjIsFloat()) {
//            $("#" + TableId + " .avgVals" + (i + 1)).attr('value', avgVals); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد
//            if (!avgVals.jjIsFloat()) {
                $("#" + TableId + " .avgVals" + (i + 1)).attr('value', "0"); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد
                finalVal = parseFloat("0") * parseFloat($("#" + TableId + " .avgWeith" + (i + 1)).val());
            } else {
                $("#" + TableId + " .avgVals" + (i + 1)).attr('value', avgVals.toFixed(2)); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد
                finalVal = parseFloat($("#" + TableId + " .avgVals" + (i + 1)).val()) * parseFloat($("#" + TableId + " .avgWeith" + (i + 1)).val());
            }
            //محاسبه امتیاز نهایی هر سطر برابر با میانگین وزن ها در میانگین نمره ها

            $("#" + TableId + " .finalVal" + (i + 1)).attr('value', finalVal.toFixed(2));
        }


        hmisStrategic.calculatorFinalVal(TableId);
    },
    normalizeTable2: function (TableId) {
        var sum = 0;
        var lenght = $("#" + TableId + " tr.row" + TableId + "").length;
        var fillInputNo = 0; //تعداد پر شده
        for (var j = 0; j < 10; j++) {//ستون ها
            for (var i = 0; i < lenght; i++) {//سطرها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
//                var nextInput2 = $("#" + TableId + " tbody tr:eq(" + i + ") td.weightPercent(" + i + ") input"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
//                var inPutsInAllCol = $(nexttr).children("td input:nth-child(" + (i) + ")");                
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                    }
                }
            }
            for (var k = 0; k < 5; k++) {
                var dif = 100 - sum;
                var mul = dif / 100;
                sum = 0; //برای محاسبه مجموع جدید بعد از نرمال سازی
                for (var i = 0; i < lenght; i++) {//سطرها
                    var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input"); //چون ستون اول تکست اریا هست یک اضافه می کنیم
                    if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                        if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                            var oldVal = parseFloat($(nextInput).val());
                            var newVal = oldVal + (oldVal * mul);
                            $(nextInput).attr('value', newVal);
                            sum += newVal;
                        }
                    }
                }
            }
            $(("#" + TableId + " .sumWeiths" + (j + 1))).attr('value', sum); //باید کلاسی مثل sumWeeiths1,2,3,... را داشته باشد
            mul = 1;
            sum = 0;
        }


    },
    /**
     * محاسبه وزن ها
     * @param {type} TableId
     * @returns {undefined}
     */
    actionCalculateWeight: function (html, val, weight) {
        if (val != '') {
            var result = parseFloat(val) * parseFloat(weight);
//        alert(result);
            html.parent().next('td').find('input').attr('value', result);
//        alert(result);
        }
    },
    /**
     * ابلاغه برنامه استراتژیک
     * @returns {undefined}
     */
    communication: function () {
        var param = "";
        param += "&id=" + new jj('#hmis_strategic_id').jjVal();
        param += "&" + new jj('#communicationForm').jjSerial();
        param += "&do=" + hmisStrategic.tableName + ".communication";
        new jj(param).jjAjax2(false);
    },
    actionCalculateQSPM: function (html, val, weight) {
        if (val != '') {
            var result = parseFloat(val) * parseFloat(weight);
//        hmisStrategic.calculateMaxTAS();//پیداکردن بزرگترین  tas
        } else if (val == '') {
            var result = 0;
        }
        var tableId = html.parent().parent().parent().parent().parent().find("table").attr('id');
        html.parent().next('td').find('input.TAS').attr('value', result.toFixed(2));
        hmisStrategic.normalizeTablesQSPM(tableId);
    },
    normalizeTablesQSPM: function (TableId) {
        //میانگین وزن های هر سطر
        var fillInputNo = 0; //تعداد پر شده

        for (var j = 0; j < 11; j++) {//ستون ها
            var sum = 0;
            for (var i = 0; i < $("#" + TableId + " tr.rowQSPM").length; i++) {//سطرها
                var nextInput = $("#" + TableId + " tbody tr:eq(" + i + ") td.weith:eq(" + j + ") input.TAS"); //سطر آی ام ستون های جی ام و  اینپوت های داخل  آن را میگیرد
                if ($(nextInput).size() == 1) {//اگر چنین عنصری پیدا شد
                    if ($(nextInput).val() != "" && $(nextInput).val() != "undefined" && $(nextInput) != null) {
                        sum += parseFloat($(nextInput).val());
                        fillInputNo++;
                        $("#" + TableId + " .sumTas" + (j)).attr('value', sum.toFixed(2)); //باید کلاسی مثل avgWeith,2,3,... را داشته باشد

                    }
                }
            }

        }

        hmisStrategic.calculateMaxTAS();
    },
    calculatorFinalVal: function (TableId) {

        var sumFinalVal = 0;
        var length = $("#" + TableId + "   tr > td .finalVal").length;
        for (var i = 1; i < length + 1; i++) {
            sumFinalVal += parseFloat($("#" + TableId + " td .finalVal" + (+i + 1)).val());
        }
        $("#" + TableId + " .sumFinalVal").attr('value', (sumFinalVal / 100).toFixed(2)); //دو رقم اعشار
//        $("#" + TableId + " .sumFinalVal").attr('value', Math.round((sumFinalVal / 100)));

//$("#"+TableId +" input ").toString().match(/\d+\.\d{2}/)
        $('div#pointer').css({'transform': 'translate(' + $('#tableIFE .sumFinalVal').val() * 100 + 'px' + ',' + $('#tableEFE .sumFinalVal').val() * (-100) + 'px' + ')'});
    },
    actionIconRemoveIFE: function (selector) {
        var param = 'Server?do=Strategic.Nothings';
        new jj(param).jjAjax2(false);
        $('.chanceIFE').html("");
        $('.weaknessIFE').html("");
        $(selector).parent().parent().find('input').addClass('red');
        $(selector).parent().parent().find('input').removeClass('green');
        var temp1 = $('#IFETbl  td input.red');
        var temp3 = $('#IFETbl  td input.green');
        var temp2 = '';
        var temp4 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += "<input class='form-control  onkeyup' type='text' value='" + $(temp1[i]).val() + "'>";
        }
        for (var i = 0; i < temp3.length; i++) {
            temp4 += " <input class='form-control  onkeyup' type='text' value='" + $(temp3[i]).val() + "' >";
        }
        $('.weaknessIFE').html(temp2);
        $('.chanceIFE').html(temp4);
//        hmisStrategic.addRowTableQSPM1(); //اضافه کردن به جدول 
    },
    actionIconAddIFE: function (selector) {
        var param = 'Server?do=Strategic.Nothings';
        new jj(param).jjAjax2(false);
        $('.weaknessIFE').html("");
        $('.chanceIFE').html("");
        $(selector).parent().parent().find('input').addClass('green');
        $(selector).parent().parent().find('input').removeClass('red');
        var temp1 = $('#IFETbl  td input.green');
        var temp3 = $('#IFETbl  td input.red');
        var temp2 = '';
        var temp4 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += "<input type='text' class='form-control  onkeyup' value='" + $(temp1[i]).val() + "'>";
        }
        for (var i = 0; i < temp3.length; i++) {
            temp4 += "<input type='text' class='form-control  onkeyup' value='" + $(temp3[i]).val() + "'>";
        }
        $('.chanceIFE').html(temp2);
        $('.weaknessIFE').html(temp4);
//        hmisStrategic.addRowTableQSPM1();
    },
    actionIconRemoveEFE: function (selector) {
        var param = 'Server?do=Strategic.Nothings';
        new jj(param).jjAjax2(false);
        $('.weaknessEFE').html("");
        $('.chanceEFE').html("");
        $(selector).parent().parent().find('input').addClass('red');
        $(selector).parent().parent().find('input').removeClass('green');
        var temp1 = $('#EFETbl  td input.red');
        var temp3 = $('#EFETbl  td input.green');
        var temp2 = '';
        var temp4 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += "<input type='text' class='form-control  onkeyup' value='" + $(temp1[i]).val() + "'>";
        }
        for (var i = 0; i < temp3.length; i++) {
            temp4 += "<input type='text' class='form-control  onkeyup' value='" + $(temp3[i]).val() + "'>";
        }
        $('.weaknessEFE').html(temp2);
        $('.chanceEFE').html(temp4);
//        hmisStrategic.addRowTableQSPM1();
    },
    actionIconAddEFE: function (selector) {
        var param = 'Server?do=Strategic.Nothings';
        new jj(param).jjAjax2(false);
        $('.weaknessEFE').html("");
        $('.chanceEFE').html("");
        $(selector).parent().parent().find('input').addClass('green');
        $(selector).parent().parent().find('input').removeClass('red');
        var temp1 = $('#EFETbl  td input.green');
        var temp3 = $('#EFETbl  td input.red');
        var temp2 = '';
        var temp4 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += "<input type='text'  class='form-control  onkeyup'  value='" + $(temp1[i]).val() + "'>";
        }
        for (var i = 0; i < temp3.length; i++) {
            temp4 += "<input type='text'  class='form-control  onkeyup'  value='" + $(temp3[i]).val() + "'>";
        }

        $('.chanceEFE').html(temp2);
        $('.weaknessEFE').html(temp4);
        $("#EFETbl  td input.green").length;
//        hmisStrategic.addRowTableQSPM1();
    },
    /**
     * اضافه کردن mo و po به جدول mo زیاد و قدرت زیاد وکم
     * @param {type} selector
     * @returns {undefined}
     */
    addToTblBen: function () {

//    addToTblBen: function (owner, averageMotive, averagePower) {
        $('#tblBen tr.rowBene td div.powerHighAndMotivationHigh').html("");
        $('#tblBen tr.rowBene td div.powerHighAndMotivationLow').html("");
        $('#tblBen tr.rowBene td div.powerLowAndMotivationHigh').html("");
        $('#tblBen tr.rowBene td div.powerLowAndMotivationLow').html("");
        var table = $("#Beneficiaries3Tbl tr.rowBeneficiaries3Tbl");
        var table2 = $("#Beneficiaries4Tbl tr.rowBeneficiaries4Tbl");
        var owner1 = "";
        var owner2 = "";
        var owner3 = "";
        var owner4 = "";
        for (var i = 0; i < table.length; i++) {
            var owner = "";
            var averagePower = $(table[i]).find("td.AveragePower div").html();
            var averageMotive = $(table[i]).find("td.AverageMotive div").html();
            owner = $(table[i]).find("td.owner div").html();
            if (parseFloat(averagePower) >= parseFloat(2.5) && parseFloat(averageMotive) >= parseFloat(2.5)) {
                owner1 += "<input type='text' class='onkeyup form-control' value='" + owner + "'>";
            } else if (parseFloat(averagePower) >= parseFloat(2.5) && parseFloat(averageMotive) <= parseFloat(2.5)) {
                owner2 += "<input type='text' class='onkeyup form-control' value='" + owner + "'>";
            } else if (parseFloat(averagePower) <= parseFloat(2.5) && parseFloat(averageMotive) >= parseFloat(2.5)) {
                owner3 += "<input type='text' class='onkeyup form-control' value='" + owner + "'>";
            } else if (parseFloat(averagePower) <= parseFloat(2.5) && parseFloat(averageMotive) <= parseFloat(2.5)) {
                owner4 += "<input type='text' class='onkeyup form-control' value='" + owner + "'>";
            }

        }
        for (var i = 0; i < table2.length; i++) {
            var owner = "";
            var averagePower = $(table2[i]).find("td.AveragePower div").html();
            var averageMotive = $(table2[i]).find("td.AverageMotive div").html();
            owner = $(table2[i]).find("td.owner div").html();
            if (parseFloat(averagePower) >= parseFloat(2.5) && parseFloat(averageMotive) >= parseFloat(2.5)) {
                owner1 += "<input type='text' class='form-control onkeyup' value='" + owner + "'>";
            } else if (parseFloat(averagePower) >= parseFloat(2.5) && parseFloat(averageMotive) <= parseFloat(2.5)) {
                owner2 += "<input type='text' class='form-control onkeyup' value='" + owner + "'>";
            } else if (parseFloat(averagePower) <= parseFloat(2.5) && parseFloat(averageMotive) >= parseFloat(2.5)) {
                owner3 += " <input type='text' class='form-control onkeyup' value='" + owner + "'>";
            } else if (parseFloat(averagePower) <= parseFloat(2.5) && parseFloat(averageMotive) <= parseFloat(2.5)) {
                owner4 += "<input type='text' class='form-control onkeyup' value='" + owner + "'>";
            }

        }
        $('#tblBen tr.rowBene div.powerHighAndMotivationHigh').html(owner1);
        $('#tblBen tr.rowBene div.powerHighAndMotivationLow').html(owner2);
        $('#tblBen tr.rowBene div.powerLowAndMotivationHigh').html(owner3);
        $('#tblBen tr.rowBene div.powerLowAndMotivationLow').html(owner4);
    },
    actionIconRemoveQSPM: function (selector) {
        $(selector).parent().parent().find('input').addClass('red');
        $(selector).parent().parent().find('input').removeClass('green');
        var temp1 = $('#tableQSPM  td input.red');
        var temp2 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += $(temp1[i]).val() + '\n';
        }
//        $('.weaknessEFE').attr('value', temp2);
    },
    actionIconAddQSPM: function (selector) {
        $(selector).parent().parent().find('input').addClass('green');
        $(this).parent().parent().find('input').removeClass('red');
        var temp1 = $('#tableQSPM  td input.green');
        var temp2 = '';
        for (var i = 0; i < temp1.length; i++) {
            temp2 += $(temp1[i]).val() + '//n';
        }
    },
    addRowIFE: function () {

        var length = 0;
        length = $("#tableIFE #IFETbl tr.rowtableIFE").length + 1;
        var tr = ("<tr class='rowtableIFE'><td>\n\
<input class='form-control onkeyup' placeholder='' style='height: 40px'/>\n\
<span><i class='ion-android-add-circle actionIconAdd' style='color:green;margin:2px 2px;cursor: pointer'    onclick='hmisStrategic.actionIconAddIFE($(this));'></i><i class='ion-minus-circled actionIconRemove' style='color:red;margin:2px 2px;cursor: pointer'    onclick='hmisStrategic.actionIconRemoveIFE($(this));'  ></i></span></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''/>\n\</td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  />\n\</td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input  class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input  class='form-control  onclick' min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='weith'><input  class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input  class='form-control  onclick' min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='avgWeight'><input  class='form-control  avgWeith" + length + "  onkeyup'  type='text' value=''  /></td>\n\
                                        <td class='avgPower'><input  class='form-control  avgVals" + length + " onkeyup'  type='text' value=''   /></td>\n\
                                        <td class='finalVal'><input  class='finalVal form-control  finalVal" + length + " onkeyup'  type='text' value=''  /></td>\n\
                                    </tr>");
        $("#tableIFE #IFETbl tr.rowtableIFE:last").after(tr);
        $('#IFETbl td.weith input[type=number].onclick').keyup(function () {
            if ($(this).val() > 100 || $(this).val() < 0) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#IFETbl tr td.value input[type=number].onclick').keyup(function () {

            if ($(this).val() < -4 || $(this).val() > 4) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#IFETbl  td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#IFETbl  td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
    },
    addRowBeneficiariesIn: function () {

        var length = 0;
        length = $("#tableBeneficiariesIn #BeneficiariesInTbl tr.rowtableBeneficiariesIn").length + 1;
        var tr = ("<tr class='rowtableBeneficiariesIn'><td class='owner'><input type='text' class='form-control onkeyup owner" + length + "' placeholder='' style='height: 40px'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup' type='number' value='' min='1' max='5' step='0.1'/>\n\</td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value='' step='0.1' />\n\</td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1' /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1' /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value=''   min='1' max='5' step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value=''  min='1' max='5' step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input  class='form-control  onclick onkeyup'  type='number' value=''  min='1' max='5' step='0.1' /></td>\n\
                                        <td class='value'><input  class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='weith'><input  class='form-control  onclick onkeyup'  type='number' value=''  min='1' max='5' step='0.1'/></td>\n\
                                        <td class='value'><input  class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='avgWeight'><input  class='form-control  avgWeith" + length + "  onkeyup'  type='text' value=''  step='0.1'/></td>\n\
                                        <td class='avgPower'><input  class='form-control  avgVals" + length + " onkeyup'  type='text' value=''   step='0.1'/></td>\n\
                                        <td class='finalVal'><input  class='finalVal form-control  finalVal" + length + " onkeyup'  type='text' value=''  step='0.1'/></td>\n\
                                    </tr>");
        $("#tableBeneficiariesIn #BeneficiariesInTbl tr.rowtableBeneficiariesIn:last").after(tr);
        $('#tableBeneficiariesIn #BeneficiariesInTbl  td input[type=number].onkeyup').on('keyup', function () {
            if ($(this).val() > 5 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableBeneficiariesIn td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableBeneficiariesIn #BeneficiariesInTbl  td input[type=number].onclick').on('click', function () {
            if ($(this).val() > 5 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
    },
    addRowBeneficiariesOut: function () {

//        $('#tableBeneficiariesOut #BeneficiariesOutTbl tr.rowtableBeneficiariesOut td input[type=number].onkeyup').keyup(function () {
//            if ($(this).val() > 5 || $(this).val() < 1) {
//                $(this).val('');
//            } else {
//                $(this).attr('value', $(this).val());
//            }
//        });

        var length = 0;
        length = $("#tableBeneficiariesOut #BeneficiariesOutTbl tr.rowtableBeneficiariesOut").length + 1;
        var tr = ("<tr class='rowtableBeneficiariesOut'><td class='owner'><input type='text' class='form-control onkeyup owner" + length + "' placeholder='' style='height: 40px'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup ' type='number' value='' min='1' max='5'  step='0.1'/>\n\</td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1'  />\n\</td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value=''  min='1' max='5' step='0.1'   /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5'  type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value=''  min='1' max='5' step='0.1'/></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1' /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1'  /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value=''  step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1' /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='weith'><input class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1'  /></td>\n\
                                        <td class='value'><input class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1' /></td>\n\
                                        <td class='weith'><input  class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5' step='0.1' /></td>\n\
                                        <td class='value'><input  class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='weith'><input  class='form-control  onclick onkeyup'  type='number' value='' min='1' max='5'  step='0.1'/></td>\n\
                                        <td class='value'><input  class='form-control  onclick onkeyup' min='1' max='5' type='number' value='' step='0.1'/></td>\n\
                                        <td class='avgWeight'><input  class='form-control  avgWeith" + length + "  onkeyup'  type='text' value='' step='0.1' /></td>\n\
                                        <td class='avgPower'><input  class='form-control  avgVals" + length + " onkeyup'  type='text' value=''   step='0.1'/></td>\n\
                                        <td class='finalVal'><input  class='finalVal form-control  finalVal" + length + " onkeyup'  type='text' value='' step='0.1' /></td>\n\
                                    </tr>");
        $("#tableBeneficiariesOut #BeneficiariesOutTbl tr.rowtableBeneficiariesOut:last").after(tr);
        $('#tableBeneficiariesOut #BeneficiariesOutTbl  td input[type=number].onkeyup').on('keyup', function () {
            if ($(this).val() > 5 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableBeneficiariesOut td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableBeneficiariesOut #BeneficiariesOutTbl  td input[type=number].onclick').on('click', function () {
            if ($(this).val() > 5 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
    },
    addRowBeneficiaries3: function () {

        var BeneficiariesInTbl = $('#BeneficiariesInTbl tr.rowtableBeneficiariesIn');
        $("#Beneficiaries3Tbl").html("");
        var tr = $("#Beneficiaries3Tbl").append("<thead>\n\
                    <tr>\n\
                    <th  class='c'>ردیف</th>\n\
                    <th class='c'>صاحبان منافع داخلی</th>\n\
                    <th class='c'>سمت</th>\n\
                    <th class='c'>نمره mo</th>\n\
                    <th class='c'>نمره po</th>\n\
                    <th class='c'>امتیاز نهایی</th>\n\
                    <th class='c'>انتظارات ذی نفعان</th>\n\
                    <th class='c'></th>\n\
                    </tr>\n\
                </thead>\n\
                <tbody>");
        if (BeneficiariesInTbl.length > 1) {

            for (var i = 1; i < BeneficiariesInTbl.length; i++) {
                var length = i + 1;
                var owner = $(BeneficiariesInTbl[i]).parent().find("td.owner input.owner" + length).val();
                var AvrageMotive = $(BeneficiariesInTbl[i]).parent().find("td.avgWeight input.avgWeith" + length).val();
                var AvragePower = $(BeneficiariesInTbl[i]).parent().find("td.avgPower input.avgVals" + length).val();
                var AvragefinalVal = $(BeneficiariesInTbl[i]).parent().find("td.finalVal input.finalVal" + length).val();
                var row = i;
                $("#Beneficiaries3Tbl").append("<tr class='rowBeneficiaries3Tbl'>\n\
 <td ><div  class='form-control  onkeyup' >" + row + "</div></td>\n\
 <td class='owner'><div  class='form-control  onkeyup' >" + owner + "</div></td>\n\
<td ><input  class='form-control  onkeyup' type='text'></td>\n\
<td class='AverageMotive' ><div  class='form-control  onkeyup' >" + AvrageMotive + " </div></td>\n\
<td class='AveragePower' ><div  class='form-control  onkeyup'> " + AvragePower + "</div></td>\n\
<td><div  class='form-control  onkeyup'  >" + AvragefinalVal + "</div></td>\n\
<td ><input  class='form-control  onkeyup' type='text'/></td>\n\
<td ><div></div></td>\n\
</tr>\n\
<script>\n\
\n\
  $('#Beneficiaries3Tbl  tr.rowBeneficiaries3Tbl td input.onkeyup').keyup(function () {$(this).attr('value', $(this).val());});</script>"
                        );
            }
        }
        hmisStrategic.addToTblBen();
        if ($("tableBeneficiaries3 table tr.rowBeneficiaries3Tbl").length > 0) {
            $("#tableBeneficiaries3 table").dataTable({searching: false, destroy: true, paging: false, "aasorting": [[0, 'desc']], "bInfo": false});
        }
    },
    addRowBeneficiaries4: function () {

        var BeneficiariesOutTbl = $('#BeneficiariesOutTbl tr.rowtableBeneficiariesOut');
        $("#Beneficiaries4Tbl").html("");
        var tr = $("#Beneficiaries4Tbl").append("<thead>\n\
                    <tr>\n\
                    <th  class='c'>ردیف</th>\n\
                    <th class='c'>صاحبان منافع خارجی</th>\n\
                    <th class='c'>سمت</th>\n\
                    <th class='c'>نمره mo</th>\n\
                    <th class='c'>نمره po</th>\n\
                    <th class='c'>امتیاز نهایی</th>\n\
                    <th class='c'>انتظارات ذی نفعان</th>\n\
                    <th class='c'></th>\n\
                    </tr>\n\
                </thead>\n\
                <tbody>");
        if (BeneficiariesOutTbl.length > 1) {
            for (var i = 1; i < BeneficiariesOutTbl.length; i++) {
                var length = i + 1;
                var owner = $(BeneficiariesOutTbl[i]).parent().find("td.owner input.owner" + length).val();
                var AvrageMotive = $(BeneficiariesOutTbl[i]).parent().find("td.avgWeight input.avgWeith" + length).val();
                var AvragePower = $(BeneficiariesOutTbl[i]).parent().find("td.avgPower input.avgVals" + length).val();
                var AvragefinalVal = $(BeneficiariesOutTbl[i]).parent().find("td.finalVal input.finalVal" + length).val();
                var row = i;
                $("#Beneficiaries4Tbl").append("<tr class='rowBeneficiaries4Tbl'>\n\
 <td ><div  class='form-control  onkeyup' >" + row + "</div></td>\n\
 <td class='owner' ><div  class='form-control  onkeyup' >" + owner + "</div></td>\n\
<td ><input  class='form-control  onkeyup'  type='text' value='' /></td>\n\
<td class='AverageMotive' ><div  class='form-control  onkeyup'  >" + AvrageMotive + "</div></td>\n\
<td class='AveragePower' ><div  class='form-control  onkeyup'  >" + AvragePower + "</div></td>\n\
<td ><div  class='form-control  onkeyup'  >" + AvragefinalVal + "</div></td>\n\
<td ><input  class='form-control  onkeyup'  type='text' value='' /></td>\n\
<td ><div></div></td>\n\
</tr>\n\
<script>\n\
  $('#Beneficiaries4Tbl  tr.rowBeneficiaries4Tbl td input.onkeyup').keyup(function () {\n\
                        $(this).attr('value', $(this).val());\n\
                    });</script>");
            }
        }

        hmisStrategic.addToTblBen();
        if ($("#tableBeneficiaries4 table tr.rowBeneficiaries4Tbl").length > 0) {
            $("#tableBeneficiaries4 table").dataTable({searching: false, destroy: true, paging: false, "aasorting": [[0, 'desc']], "bInfo": false});
        }

    },
    addRowEFE: function () {
        var length = 0;
        length = $("#tableEFE #EFETbl tr.rowtableEFE").length + 1;
        var tr = ("<tr class='rowtableEFE' ><td><input class='form-control onkeyup' placeholder='' style='height: 40px'/><span><i class='ion-android-add-circle actionIconAdd' style='color:green;margin:2px 2px;cursor: pointer'    onclick='hmisStrategic.actionIconAddEFE($(this));'></i><i class='ion-minus-circled actionIconRemove' style='color:red;margin:2px 2px;cursor: pointer'    onclick='hmisStrategic.actionIconRemoveEFE($(this));'  ></i></span></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100'  type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick'  min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='weith'><input class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input class='form-control  onclick' min='-4' max='4' type='number' value=''  /></td>\n\
                                        <td class='weith'><input  class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input  class='form-control  onclick' min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='weith'><input  class='form-control  onclick'  min='1' max='100' type='number' value=''   /></td>\n\
                                        <td class='value'><input  class='form-control  onclick' min='-4' max='4' type='number' value='' /></td>\n\
                                        <td class='avgWeight'><input  class='form-control  avgWeith" + length + "  onkeyup'  type='text' value=''  /></td>\n\
                                        <td class='avgPower'><input  class='form-control  avgVals" + length + " onkeyup'  type='text' value=''   /></td>\n\
                                        <td class='finalVal'><input  class='finalVal form-control  finalVal" + length + " onkeyup'  type='text' value=''  /></td>\n\
                                    </tr>");
        $("#tableEFE #EFETbl tr.rowtableEFE:last").after(tr);
        $('#EFETbl td.weith input[type=number].onclick').keyup(function () {
            if ($(this).val() > 100 || $(this).val() < 0) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#EFETbl tr td.value input[type=number].onclick').keyup(function () {

            if ($(this).val() < -4 || $(this).val() > 4) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#EFETbl  td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#EFETbl  td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
    },
    addRowTableWeight: function () {
        var length = 0;
        length = $("#tableWeight #weightTbl tr.rowtableWeight").length + 1;
        var tr = ("<tr class='rowtableWeight'>\n\
                    <td ><textarea  class='form-control  onkeyup'  style='width:150px' ></textarea></td>\n\
                    <td class='weith'><input type='number' min='0' max='100' class='form-control   weightPercent'  style='width:70px'/></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'   style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text' class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control   onkeyup ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text'  class='form-control  onkeyup  ' /></td>\n\
                    <td class='factor'><input type='number' min='1' max='5' class='form-control  onkeyup  onkeypress'  style='width:50px' /></td>\n\
                    <td class='result'><input type='text' class='form-control  onkeyup  ' /></td>\n\
                    </tr>\n\
                ");
        $("#tableWeight #weightTbl tr.rowtableWeight:last").after(tr);
        $('#weightTbl  tr.rowtableWeight td input[type=number].onkeyup').keyup(function () {
            if ($(this).val() > 5 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#weightTbl  tr.rowtableWeight td textarea.onkeyup').keyup(function () {
            $(this).text($(this).val());
        });
        $('#weightTbl  tr.rowtableWeight td input[type=number].weightPercent').keyup(function () {
            if ($(this).val() > 100 || $(this).val() < 0) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#weightTbl  tr.rowtableWeight td input[type=number].weightPercent').click(function () {
            if ($(this).val() > 100 || $(this).val() < 0) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#weightTbl  tr.rowtableWeight td input.onkeypress').click(function () {
            var weight = $(this).parent().parent().find('.weightPercent').attr('value');
            if (weight != 0) {
                hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);
            }
        });
        $('#weightTbl  tr.rowtableWeight td input.onkeypress').keyup(function () {
            var weight = $(this).parent().parent().find('.weightPercent').attr('value');
            if (weight != 0) {
                hmisStrategic.actionCalculateWeight($(this), $(this).val(), weight);
            }
        });
    },
    addRowTableQSPM1: function () {
        $("#tableQSPM1").html("");
        $("#tableQSPM2").html("");
        $("#tableQSPM3").html("");
        $("#tableQSPM4").html("");
        var weekness = $('table tr td .red');
        var tr = $("#strategicStep2 #tableQSPM1").append("\
<thead>\n\
\n\<tr>\n\
<th colspan='2' scope='colgroup' class='c'></th>\n\
<th colspan='2' scope='colgroup' class='c'>SO1</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO2</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO3</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO4</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO5</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO6</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO7</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO8</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO9</th>\n\
<th colspan='2' scope='colgroup' class='c'>SO10</th>\n\
        </tr>\n\
<tr>\n\
<td class='c' width='7%'>\n\
\n\نقاط قوت/ضعف\n\
<br/>\n\
فرصت /تهدید\n\
</td>\n\
<th class='c'>وزن</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS1'>SO1 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS2'>SO2 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS3'>SO3 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS4'>SO4 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS5'>SO5 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS6'>SO6 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS7'>SO7 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS8'>SO8 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS9'>SO9 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS10'>SO10 TAS</th>\n\
</tr>\n\
</thead>\n\
<tbody>");
        var tr2 = $("#tableQSPM2").append("\
<thead>\n\
<tr>\n\
<th colspan='2' scope='colgroup' class='c'></th>\n\
<th colspan='2' scope='colgroup' class='c'>WO1</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO2</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO3</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO4</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO5</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO6</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO7</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO8</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO9</th>\n\
<th colspan='2' scope='colgroup' class='c'>WO10</th>\n\
        </tr>\n\
<tr>\n\
<th class='c' width='7%'>\n\
نقاط قوت/ضعف\n\
<br/>\n\
فرصت /تهدید\n\</th>\n\
<th class='c'>وزن</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS1'>WO1 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS2'>WO2 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS3'>WO3 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS4'>WO4 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS5'>WO5 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS6'>WO6 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS7'>WO7 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS8'>WO8 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS9'>WO9 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS10'>WO10 TAS</th>\n\
</tr>\n\
</thead>");
        var tr3 = $("#tableQSPM3").append("\
<thead>\n\
\n\<tr>\n\
<th colspan='2' scope='colgroup' class='c'></th>\n\
<th colspan='2' scope='colgroup' class='c'>ST1</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST2</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST3</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST4</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST5</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST6</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST7</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST8</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST9</th>\n\
<th colspan='2' scope='colgroup' class='c'>ST10</th>\n\
        </tr>\n\
<tr>\n\
<th class='c' width='7%'>\n\
نقاط قوت/ضعف\n\
<br/>\n\
فرصت /تهدید\n\
</th>\n\
<th class='c'>وزن</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS1' >ST1 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS2' >ST2 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS3' >ST3 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS4' >ST4 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS5' >ST5 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS6' >ST6 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS7' >ST7 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS8' >ST8 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS9' >ST9 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS10' >ST10 TAS</th>\n\
</tr>\n\
</thead>\n\
");
        var tr4 = $("#tableQSPM4").append("\\n\
<thead>\n\
\n\<tr>\n\
<th colspan='2' scope='colgroup' class='c'></th>\n\
<th colspan='2' scope='colgroup' class='c'>WT1</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT2</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT3</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT4</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT5</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT6</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT7</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT8</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT9</th>\n\
<th colspan='2' scope='colgroup' class='c'>WT10</th>\n\
        </tr>\n\
<tr>\n\
<th class='c' width='7%'>نقاط قوت/ضعف\n\
<br/>\n\
فرصت /تهدید\n\</th>\n\
<th class='c'>وزن</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS1'>WT1 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS2'>WT2 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS3'>WT3 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS4'>WT4 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS5'>WT5 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS6'>WT6 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS7'>WT7 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS8'>WT8 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS9'>WT9 TAS</th>\n\
<th class='c'>AS</th>\n\
<th class='c resultTAS10'>WT10 TAS</th>\n\
</tr>\n\
                            </thead>\n\
");
        var tr = "";
        var tr2 = "";
        var tr3 = "";
        var tr4 = "";
        for (var i = 0; i < weekness.length; i++) {
            var AvrageWeight = $(weekness[i]).parent().parent().find('td.avgWeight input').val();
            tr = $("#tableQSPM1").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(weekness[i]).val() + "'></td>\n\
                            <td class='weith' style='width: 6%'><input class='form-control  onkeyup weightQSPM'  type='text' value='" + AvrageWeight + "'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'  ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'   min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
            tr2 = $("#tableQSPM2").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(weekness[i]).val() + "'></td>\n\
                            <td class='weith' style='width: 6%'><input class='form-control  onkeyup weightQSPM'  type='text' value='" + AvrageWeight + "'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                                                  </tr>\n\
");
            tr3 = $("#tableQSPM3").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(weekness[i]).val() + "'></td>\n\
                            <td class='weith' style=''><input class='form-control  onkeyup weightQSPM'  type='text' value='" + AvrageWeight + "'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
            tr4 = $("#tableQSPM4").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(weekness[i]).val() + "'></td>\n\
                            <td class='weith' style='width: 6%'><input class='form-control  onkeyup weightQSPM'  type='text' value='" + AvrageWeight + "'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup'  min='1' max='4' type='number'  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
        }

        var power = $('table tr td .green');
        for (var i = 0; i < power.length; i++) {
            var AvrageWeight = $(power[i]).parent().parent().find('td.avgWeight input').val();
            var tr = $("#tableQSPM1").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(power[i]).val() + "'></td>\n\
                            <td class='weith' style='width:6%'><input class='form-control  onkeyup weightQSPM'  type='text'  value='" + AvrageWeight + "'/></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
            var tr2 = $("#tableQSPM2").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(power[i]).val() + "'></td>\n\
                            <td class='weith' style='width: 6%'><input class='form-control  onkeyup weightQSPM'  type='text'  value='" + AvrageWeight + "'/></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value'  ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
            var tr3 = $("#tableQSPM3").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(power[i]).val() + "'></td>\n\
                            <td class='weith' style='width:6%'><input class='form-control  onkeyup weightQSPM'  type='text'  value='" + AvrageWeight + "'/></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' ><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>\n\
");
            var tr4 = $("#tableQSPM4").append("<tr class='rowQSPM'>\n\
                        <td><input class='form-control onkeyup weight' style='height: 40px' value='" + $(power[i]).val() + "'></td>\n\
                            <td class='weith' style='width: 6%'><input class='form-control  onkeyup weightQSPM'  type='text'  value='" + AvrageWeight + "'/></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%' ><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                            <td class='value' width='6%'><input class='form-control  onclick onkeyup '  min='1' max='4' type='number' /></td>\n\
                            <td class='weith' width='4%'><input class='form-control  onkeyup TAS'  type='text'  /></td>\n\
                                                        </tr>");
        }
        $("#tableQSPM1").append("<tr class='bg-teal'>\n\
                            <td style='vertical-align: central;text-align: left;color: white'>جمع TAS</td>\n\
                            <td></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas1' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas2' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas3' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas4' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas5' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas6' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas7' type='text' value='0'/></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas8' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas9' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas10' type='text' value='0' /></td>\n\
\n\</tr>").after(tr);
        $("#tableQSPM2").append("<tr class='bg-teal'>\n\
                            <td style='vertical-align: central;text-align: left;color: white'>جمع TAS</td>\n\
                            <td></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas1' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas2' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas3' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas4' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas5' type='text' value='0'  /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas6' type='text'  value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas7' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas8' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas9' type='text' value='0'  /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas10' type='text' value='0' /></td>\n\
\n\</tr>").after(tr2);
        $("#tableQSPM3").append("<tr class='bg-teal'>\n\
                            <td style='vertical-align: central;text-align: left;color: white'>جمع TAS</td>\n\
                            <td></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas1' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas2' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas3' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas4' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas5' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas6' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas7' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas8' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas9' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas10' type='text' value='0'  /></td>\n\
\n\</tr>").after(tr3);
        $("#tableQSPM4").append("<tr class='bg-teal'>\n\
                            <td style='vertical-align: central;text-align: left;color: white'>جمع TAS</td>\n\
                            <td></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas1' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas2' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas3' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas4' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas5' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas6' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas7' type='text' value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas8' type='text' value='0'  /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas9' type='text'  value='0' /></td>\n\
                            <td></td>\n\
                            <td><input  class='form-control  sumTas10' type='text' value='0'  /></td>\n\
\n\</tr>").after(tr4);
        $('#tableQSPM1 tr.rowQSPM td.value input.onkeyup').click(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM1 tr.rowQSPM td.value input.onkeyup').keyup(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM1  tr.rowQSPM td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM1 tr.rowQSPM td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM1 tr.rowQSPM td.value input[type=number].onkeyup').keyup(function () {
            if ($(this).val() > 4 || $(this).val() < 1) {
                $(this).val('');
                return;
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableQSPM2 tr.rowQSPM td.value input[type=number].onkeyup').keyup(function () {
            if ($(this).val() > 4 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableQSPM3 tr.rowQSPM td.value input[type=number].onkeyup').keyup(function () {
            if ($(this).val() > 4 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableQSPM4 tr.rowQSPM td.value input[type=number].onkeyup').keyup(function () {
            if ($(this).val() > 4 || $(this).val() < 1) {
                $(this).val('');
            } else {
                $(this).attr('value', $(this).val());
            }
        });
        $('#tableQSPM2 tr.rowQSPM td.value input.onkeyup').click(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM2 tr.rowQSPM td.value input.onkeyup').keyup(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM2  tr.rowQSPM td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM2 tr.rowQSPM td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM3 tr.rowQSPM td.value input.onkeyup').click(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM3 tr.rowQSPM td.value input.onkeyup').keyup(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM3  tr td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM3 tr.rowQSPM td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM4 tr.rowQSPM td.value input.onkeyup').click(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM4 tr.rowQSPM td.value input.onkeyup').keyup(function () {
            var weight = $(this).parent().parent().find('.weightQSPM').val();
            if (weight != 0) {
                hmisStrategic.actionCalculateQSPM($(this), $(this).val(), weight);
            }
        });
        $('#tableQSPM4  tr.rowQSPM td input.onkeyup').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#tableQSPM4 tr.rowQSPM td input.onclick').click(function () {
            $(this).attr('value', $(this).val());
        });
    },
    addRowTableQSPM: function () {
        $("#tableQSPM").append("<tr>\n\
                            <td><input class='form-control onkeyup' placeholder='' style='height: 40px'/><span>\n\
                              <i class='ion-android-add  actionIconAdd' style='color:green;margin:2px 2px;cursor: pointer'   onclick='hmisStrategic.actionIconAddQSPM($(this));' ></i>\n\
                            <i class='ion-minus-circled actionIconRemove' style='color:red;margin:2px 2px;cursor: pointer'    onclick='hmisStrategic.actionIconRemoveQSPM($(this));'  ></i>\n\
                            </span></td>\n\
                            <td class='weith' style='width: 120px'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='weith'><input class='form-control  onkeyup'  type='text' value='' /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
                            <td class='value'><input class='form-control  onclick'  min='1' max='4' type='number' value=''  /></td>\n\
</tr>");
    },
    calculateMaxTAS: function () {
        var TAS1 = $("#tableQSPM1 tr.bg-teal input");
        var TAS1Result = "";
        var TAS2 = $("#tableQSPM2 tr.bg-teal input");
        var TAS2Result = "";
        var TAS3 = $("#tableQSPM3 tr.bg-teal input");
        var TAS3Result = "";
        var TAS4 = $("#tableQSPM4 tr.bg-teal input");
        var TAS4Result = "";
        var tr = "";
        for (var i = 0; i < TAS1.length; i++) {
            if ($("#tableQSPM1 input.sumTas" + (i + 1)).val() !== "" && $("#tableQSPM1 input.sumTas" + (i + 1)).val() !== "0" && $("#tableQSPM1 input.sumTas" + (i + 1)).val() !== "0.00") {
                var resultTAS = $("#tableQSPM1 .resultTAS" + (i + 1)).html();
//                var $th = "'th:nth-child(" + (i + 1 + ")'";
//                alert($("#tableQSPM1  tr").find($th).html());
                var sumTAS = parseFloat($("#tableQSPM1 input.sumTas" + (i + 1)).val()).toFixed(2);
                tr += "<tr class='strategy'>\n\
                <td><div class='form-control  titleStrategy c' >" + resultTAS + "</div></td>\n\
                <td class='weith' ><div class='form-control   pointsStrategy c'>" + sumTAS + "</div></td> \n\
              ";
            }
        }
        for (var i = 0; i < TAS2.length; i++) {
            if ($("#tableQSPM2 input.sumTas" + (i + 1)).val() !== "" && $("#tableQSPM2 input.sumTas" + (i + 1)).val() !== "0" && $("#tableQSPM2 input.sumTas" + (i + 1)).val() !== "0.00") {
                var resultTAS = $("#tableQSPM2 .resultTAS" + (i + 1)).html();
                var sumTAS = parseFloat($("#tableQSPM2 input.sumTas" + (i + 1)).val()).toFixed(2);
                tr += "<tr class='strategy'>\n\
                <td><div class='form-control  onkeyup titleStrategy c' >" + resultTAS + "</div></td>\n\
                <td class='weith' ><div class='form-control   pointsStrategy c'>" + sumTAS + "</div></td>\n\
                ";
            }
        }
        for (var i = 0; i < TAS3.length; i++) {
            if ($("#tableQSPM3 input.sumTas" + (i + 1)).val() !== "" && $("#tableQSPM3 input.sumTas" + (i + 1)).val() !== "0" && $("#tableQSPM3 input.sumTas" + (i + 1)).val() !== "0.00") {
                var resultTAS = $("#tableQSPM3 .resultTAS" + (i + 1)).html();
                var sumTAS = parseFloat($("#tableQSPM3 input.sumTas" + (i + 1)).val()).toFixed(2);
                tr += "<tr class='strategy'>\n\
                <td><div class='form-control  onkeyup titleStrategy c' >" + resultTAS + "</div></td>\n\
                <td class='weith' ><div class='form-control   pointsStrategy c'>" + sumTAS + "</div></td> \n\
";
            }
        }
        for (var i = 0; i < TAS4.length; i++) {
            if ($("#tableQSPM4 input.sumTas" + (i + 1)).val() !== "" && $("#tableQSPM4 input.sumTas" + (i + 1)).val() !== "0" && $("#tableQSPM4 input.sumTas" + (i + 1)).val() !== "0.00") {
                var resultTAS = $("#tableQSPM4 .resultTAS" + (i + 1)).html();
                var sumTAS = parseFloat($("#tableQSPM4 input.sumTas" + (i + 1)).val()).toFixed(2);
                tr += "<tr class='strategy'>\n\
                <td><div class='form-control  onkeyup titleStrategy c' >" + resultTAS + "</div></td>\n\
                <td class='weith' ><div class='form-control   pointsStrategy c'>" + sumTAS + "</div></td> \n\
";
            }
        }

        $("#tablePrioritizedDiv").html("");
        $("#tablePrioritizedDiv").append("<table id='tablePrioritized' class='table display col-lg-12'>\n\
    <thead>\n\
        <tr>\n\
        <td class='c'>استراتژی</td>\n\
        <td class='c' >مجموع امتیازات </td>\n\
        </tr>\n\
        <tbody>" + tr + "\n\
        </tbody></thead>\n\
    <tbody>\n\
    </tbody>\n\
</table>\n\
<script>﻿\n\
 $('#tablePrioritized td input.onkeyup').keyup(function () {\n\
                $(this).attr('value', $(this).val()); \n\
                                         });\n\
</script>");
        var titleStrategy = $('#StartegiesTableOnSWOT input.inputSWOT');
        var titleStrategyQSPM = $('#tablePrioritizedDiv div.titleStrategy'); //ازجدول اولویت بندی استراتژی
        var option = "";
        for (var i = 0; i < titleStrategy.length; i++) {
            option += "<option value='" + $(titleStrategy[i]).val() + "'>" + $(titleStrategy[i]).val() + "</option>";
        }
        for (var i = 0; i < titleStrategyQSPM.length; i++) {
            option += "<option value='" + $(titleStrategyQSPM[i]).html() + "'>" + $(titleStrategyQSPM[i]).html() + "</option>";
        }
        $('#strategy_title').html(option);
        $("#tablePrioritized").dataTable({searching: false, destroy: true, paging: false, "aasorting": [[0, 'desc']], "bInfo": false});
    },
    removeRowTableQSPM: function () {
        if ($("#tableQSPM tr").length > 1) {
            $("#tableQSPM tr").last().remove();
        }
    },
    removeRowTableWeight: function () {
        if ($("#tableWeight #weightTbl tr.rowtableWeight").length > 1) {
            $("#tableWeight #weightTbl tr.rowtableWeight").last().remove();
        }
    },
    removeRowEFE: function () {
        if ($("#tableEFE #EFETbl tr.rowtableEFE").length > 1) {
            $("#tableEFE #EFETbl tr.rowtableEFE").last().remove();
//            hmisStrategic.addRowTableQSPM1();
        }
    },
    pointerMove: function () {
        var position = $("table#SWOTMatrixTable tr td#zeroPoint").position();
        var x = position.left + 'px';
        var y = position.top + 'px';
        alert(x + "," + y);
        $('#pointer').css({'transform': 'translate(' + x + ', ' + y + ')'});
    },
    /**
     * حذف سطر
     * @returns {undefined}
     */
    removeRowIFE: function () {
        if ($("#tableIFE #IFETbl tr.rowtableIFE").length > 1) {
            $("#tableIFE #IFETbl tr.rowtableIFE").last().remove();
//            hmisStrategic.addRowTableQSPM1(); //زمانی که سطری پاک می شود دوباره به تعداد نقاط ضعف و po شمارش می شود و یک سطر ساخته می شود

        } else {
        }
    },
    addRowSWOT: function (Html) {
//        var td = $(TdHtml).closest('td').attr('id');

        var buttonId = $(Html).attr('id');
        var tdId = $("#" + buttonId).closest('td').attr('id');
        var trId = $("#" + buttonId).closest('tr').attr('id');
        var input = "<input class='form-control  onkeyup inputSWOT' placeholder='استراتژی جدید'>";
        var script = "<script>\n\
$('#StartegiesTableOnSWOT tr td input.onkeyup').keyup(function (){\n\
$(this).attr('value', $(this).val()); \n\
hmisStrategic.actionTitleStrategy();});</script>";
        $("#StartegiesTableOnSWOT tr#" + trId + " td#" + tdId).append(input);
        $("#StartegiesTableOnSWOT").append(script);
    },
    actionTitleStrategy: function () {

        var titleStrategy = $('#StartegiesTableOnSWOT input.inputSWOT');
        var titleStrategyQSPM = $('#tablePrioritizedDiv div.titleStrategy'); //ازجدول اولویت بندی استراتژی
        var option = "";
        for (var i = 0; i < titleStrategy.length; i++) {
            option += "<option value='" + $(titleStrategy[i]).val() + "'>" + $(titleStrategy[i]).val() + "</option>";
        }
        for (var i = 0; i < titleStrategyQSPM.length; i++) {
            option += "<option value='" + $(titleStrategyQSPM[i]).html() + "'>" + $(titleStrategyQSPM[i]).html() + "</option>";
        }
//        $("#strategy_title").select2({
//            width: '100%'
//        });
        $('#strategy_title').html(option);
    },
    removeRowSWOT: function (buttonId) {
        var tdId = $("#" + buttonId).closest('td').attr('id');
        var trId = $("#" + buttonId).closest('tr').attr('id');
        $("#StartegiesTableOnSWOT tr#" + trId + " td#" + tdId + " .inputSWOT").last().remove();
    },
    removeRowBeneficiariesIn: function () {
        if ($("#tableBeneficiariesIn #BeneficiariesInTbl tr.rowtableBeneficiariesIn").length > 1) {
            $("#tableBeneficiariesIn #BeneficiariesInTbl tr.rowtableBeneficiariesIn").last().remove();
        } else {
        }
    },
    removeRowBeneficiariesOut: function () {
        if ($("#tableBeneficiariesOut #BeneficiariesOutTbl tr.rowtableBeneficiariesOut").length > 1) {
            $("#tableBeneficiariesOut #BeneficiariesOutTbl tr.rowtableBeneficiariesOut").last().remove();
        } else {
        }
    },
    /**
     * نام جدول مربوطه
     * @param {type} table
     * @returns {undefined}
     */
    removeRowBeneficiaries3: function (table) {
        if ($("#" + table + "Tbl tr.row" + table + "").length > 1) {

            $("#" + table + "Tbl tr.row" + table + " ").last().remove();
        } else {
        }
    },
    changeSelectToInputStrategic: function () {
        if ($('#strategy_title').css('display') == 'none') {
            $('#strategy_title').show();
            $('#strategy_title').attr('name', 'strategy_title');
            $('#strategy_title1').hide();
            $('#strategy_title1').attr('name', 'strategy_title1');
        } else {
            $('#strategy_title').hide();
            $('#strategy_title').attr('name', 'strategy_title');
            $('#strategy_title1').show();
            $('#strategy_title1').attr('name', 'strategy_title');
        }
    },
    tabSizeTbl: function () {
        $('#swStrategic').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swStrategic').css('height', 'auto');
    },
}
;
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
var hmisStrategicGuide = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swStrategicGuideForm").html() == '') {
            $("#swStrategicGuideForm").load("formHMIS/09FMEA.html", null, function () {
                hmisStrategicGuide.m_show_form();
            });
        }
    },
    m_show_form: function () {
        $('#swStrategicGuideForm').slideDown();
        hmisStrategic.tabSizeForm();
    },
    m_clean: function () {
        new jj('#swStrategicGuideForm').jjFormClean();
    },
//<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swStrategicGuide').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swStrategicGuide').css('height', 'auto');
    },
}
;
