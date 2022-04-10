/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 *نوشته شده توسط شیران یک
 */

var hmisManagementGauges = {
    tableName: "ManagementGauges",
    f_id: "id",
    loadForm: function () {
        if ($("#swManagementGaugesForm").html() == '') {
            $("#swManagementGaugesForm").load("formHMIS/managementGauges.html", null, function () {
                new jj('#managementGauges_date').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_ManagementGauges").on('click', function (e) {
                    hmisManagementGauges.m_clean();
                    hmisManagementGauges.m_show_tbl();
                });

                hmisDepartment.selectOptionDepartment("#managementGauges_department");
                hmisManagementGauges.getoptionAxis("managementGauges_axis");
                hmisManagementGauges.getoptionUnderAxis("managementGauges_underTheAxis");
                hmisManagementGauges.getoptionStandard("managementGauges_standard");
                hmisRole.getSelectOptionRequierd("#swManagementGaugesForm #managementGauges_responsibleLoadingRole");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                hmisRole.getSelectOptionRequierd("#swManagementGaugesForm #managementGauges_responsibleGauge");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه



                ////////////////////////////یرای قسمت سرچ محور و زیر محور در قسمت رفرش


                $("#managementGauges_responsibleLoadingRole").select2({
                    minimumResultsForSearch: '',
                    width: '100%'
                });
                $('#managementGauges_comment').summernote({height: 150, tooltip: false});
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swManagementGaugesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisManagementGauges.tabSizeTbl();
    },
    //سرچ برساس محور و ریز محور
    searchByAxisAndUnderAxis: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".refreshManagementGauges";
        param += "&panel=" + (containerId == null ? "refreshGaugeTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&" + new jj("#managementGaugesSearchForm").jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisManagementGauges.tabSizeTbl();
    },

    changeSelectToInputAxis: function () {
        if ($('#axis_div').css('display') == 'none') {
            $('#axis_div').show();
            $('#managementGauges_axis').attr('name', 'managementGauges_axis');
            $('#managementGauges_axis').select2({width: '100%'});
            $('#managementGauges_axis1').hide();
            $('#managementGauges_axis1').attr('name', 'managementGauges_axis1');
        } else {

            $('#axis_div').hide();
            $('#managementGauges_axis').attr('name', 'managementGauges_axis1');
            $('#managementGauges_axis1').show();
            $('#managementGauges_axis1').focus();
            $('#managementGauges_axis1').attr('name', 'managementGauges_axis');
        }
    },
    changeSelectToInputUnderTheAxis: function () {
        if ($('#underTheAxis_div').css('display') == 'none') {
            $('#underTheAxis_div').show();
            $('#managementGauges_underTheAxis').attr('name', 'managementGauges_underTheAxis');
            $('#managementGauges_underTheAxis').select2({width: '100%'});
            ;
            $('#managementGauges_underTheAxis1').hide();
            $('#managementGauges_underTheAxis1').attr('name', 'managementGauges_underTheAxis1');
        } else {
            $('#underTheAxis_div').hide();
            $('#managementGauges_underTheAxis').attr('name', 'managementGauges_underTheAxis1');
            $('#managementGauges_underTheAxis1').show();
            $('#managementGauges_underTheAxis1').focus();
            $('#managementGauges_underTheAxis1').attr('name', 'managementGauges_underTheAxis');
        }
    },
    changeSelectToInputStandard: function () {
        if ($('#standard_div').css('display') == 'none') {
            $('#standard_div').show();
            $('#managementGauges_standard').attr('name', 'managementGauges_standard');
            $('#managementGauges_standard').select2({width: '100%'});
            $('#managementGauges_standard1').hide();
            $('#managementGauges_standard1').attr('name', 'managementGauges_standard1');
        } else {
            $('#standard_div').hide();
            $('#managementGauges_standard').attr('name', 'managementGauges_standard1');
            $('#managementGauges_standard1').show();
            $('#managementGauges_standard1').focus();
            $('#managementGauges_standard1').attr('name', 'managementGauges_standard');
        }
    },
    getoptionAxis: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".getoptionAxis";

        new jj(param).jjAjax2(false);
    },
    getoptionUnderAxis: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".getoptionUnderAxis";

        new jj(param).jjAjax2(false);
    },
    getoptionStandard: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".getoptionStandard";

        new jj(param).jjAjax2(false);
    },
//    *
//    برای دراوردن بخش ها
//    */
    selectOptionDepartment: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".selectOptionDepartment";

        new jj(param).jjAjax2(false);

    },
    selectOptionRoleResponsibleLoading: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".selectOptionRoleResponsibleLoading";

        new jj(param).jjAjax2(false);

    },
    selectOptionRoleResponsibleGauge: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisManagementGauges.tableName + ".selectOptionRoleResponsibleGauge";

        new jj(param).jjAjax2(false);

    },
    m_add_new: function () {
        new jj("do=" + hmisManagementGauges.tableName + ".add_new").jjAjax2(false);
        hmisManagementGauges.m_show_form();
        hmisManagementGauges.m_clean();
        $('#managementGauges_department').select2();
        $('#managementGauges_responsibleLoading').select2();
        $('#managementGauges_underTheAxis').select2();
        $('#managementGauges_responsibleGauge').select2();
        $('#managementGauges_step').select2();
        $('#managementGauges_gauge').select2();
        $('#managementGauges_standard').select2();
        $('#managementGauges_axis').select2();
    },
    m_show_form: function () {
        $('#swManagementGaugesTbl').hide();
        $('#swManagementGaugesForm').show();
        if ($('#swManagementGaugesTbl').html() == "") {
            hmisManagementGauges.m_refresh();
        }
        hmisManagementGauges.tabSizeForm();
    },
    m_clean: function () {
        $('#managementGauges_comment').summernote('code', '');
        new jj("#swManagementGaugesForm").jjFormClean();
        $("#managementGauges_axis").val('');
        $("#managementGauges_underTheAxis").val('');
        $("#managementGauges_standard").val('');
        $("#managementGauges_gauge").val('');
        $("#managementGauges_step").val('');
        $("#managementGauges_responsibleLoading").val('');
        $("#managementGauges_uploadPeriod").val('');
        $("#managementGauges_date").val('');
        $("#managementGauges_department").val('').trigger('change');
        $("#managementGauges_responsibleLoading").val('').trigger('change');
    },
    m_show_tbl: function () {
        $('#swManagementGaugesTbl').show();
        $('#swManagementGaugesForm').hide();
        if ($('#swManagementGaugesTbl').html() == "") {
            hmisManagementGauges.m_refresh();
        }
        hmisManagementGauges.tabSizeTbl();
    },
    m_insert: function () {
        var flag = true;
        var massages = "";
        if (new jj('#managementGauges_department').jjVal() !== "") {
            $('#select2-managementGauges_department-container').removeClass("required");
        } else {
            $('#select2-managementGauges_department-container').addClass("required");
            massages += "<br>" + "لطفا بخش را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_responsibleGauge').jjVal() !== "") {
            $("#select2-managementGauges_responsibleGauge-container").removeClass("required");
        } else {
            $('#select2-managementGauges_responsibleGauge-container').addClass("required");
            massages += "<br>" + "لطفا مسئول سنجه را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_axis').jjVal() !== "" || new jj('#managementGauges_axis1').jjVal() !== "") {
            $("#select2-managementGauges_axis-container").removeClass("required");
            $("#managementGauges_axis1").removeClass("required");
        } else {
            $('#select2-managementGauges_axis-container').addClass("required");
            $('#managementGauges_axis1').addClass("required");
            massages += "<br>" + "لطفا محور را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_underTheAxis').jjVal() !== "" || new jj('#managementGauges_underTheAxis1').jjVal() !== "") {
            $("#select2-managementGauges_underTheAxis-container").removeClass("required");
            $("#managementGauges_underTheAxis1").removeClass("required");
        } else {
            $('#select2-managementGauges_underTheAxis-container').addClass("required");
            $('#managementGauges_underTheAxis1').addClass("required");
            massages += "<br>" + "لطفا زیر محور را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_standard').jjVal() !== "" || new jj('#managementGauges_standard1').jjVal() !== "") {
            $("#select2-managementGauges_standard-container").removeClass("required");
            $("#managementGauges_standard1").removeClass("required");
        } else {
            $('#select2-managementGauges_standard-container').addClass("required");
            $("#managementGauges_standard1").addClass("required");
            massages += "<br>" + "لطفا استاندارد را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_gauge').jjVal() !== "") {
            $("#managementGauges_gauge").removeClass("required");
        } else {
            $("#managementGauges_gauge").addClass("required");
            massages += "<br>" + "لطفاسنجه را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_step').jjVal() !== "" || new jj('#managementGauges_step1').jjVal() !== "") {
            $("#select2-managementGauges_step-container").removeClass("required");
            $("#managementGauges_step1").removeClass("required");
        } else {
            $('#select2-managementGauges_step-container').addClass("required");
            $('#managementGauges_step1').addClass("required");
            massages += "<br>" + "لطفاگام را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_responsibleLoadingRole').jjVal() !== "") {
            $("#select2-managementGauges_responsibleLoadingRole-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_responsibleLoadingRole-container').addClass("required");
            massages += "<br>" + "لطفامسئول بارگذاری را انتخاب کنید";

            flag = false;
        }

        if (!flag) {
            new jj(massages).jjModal("پیام");
            return;
        }

        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".insert";
        param += "&managementGauges_comment=" + $('#managementGauges_comment').summernote('code').trim();
        param += "&" + new jj("#swManagementGaugesForm").jjSerial();

        param += "&jj=1";

        new jj(param).jjAjax2(false);
        hmisManagementGauges.m_show_tbl();
        hmisManagementGauges.m_clean();
        hmisManagementGauges.getoptionAxis("managementGauges_axis");
        hmisManagementGauges.getoptionUnderAxis("managementGauges_underTheAxis");
        hmisManagementGauges.getoptionStandard("managementGauges_standard");
    },
    /**
     * بدون اینکه فرم پاک بشود یک گام را ثبت بکند و گام بعدی را بتواند مجدد ثبت بکند
     * @returns {undefined}
     */
    insertAndNew: function () {
        var flag = true;
        var massages = "";
        if (new jj('#managementGauges_department').jjVal() !== "") {
            $('#select2-managementGauges_department-container').removeClass("required");
        } else {
            $('#select2-managementGauges_department-container').addClass("required");
            massages += "<br>" + "لطفا بخش را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_responsibleGauge').jjVal() !== "") {
            $("#select2-managementGauges_responsibleGauge-container").removeClass("required");
        } else {
            $('#select2-managementGauges_responsibleGauge-container').addClass("required");
            massages += "<br>" + "لطفا مسئول سنجه را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_axis').jjVal() !== "" || new jj('#managementGauges_axis1').jjVal() !== "") {
            $("#select2-managementGauges_axis-container").removeClass("required");
            $("#managementGauges_axis1").removeClass("required");
        } else {
            $('#select2-managementGauges_axis-container').addClass("required");
            $('#managementGauges_axis1').addClass("required");
            massages += "<br>" + "لطفا محور را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_underTheAxis').jjVal() !== "" || new jj('#managementGauges_underTheAxis1').jjVal() !== "") {
            $("#select2-managementGauges_underTheAxis-container").removeClass("required");
            $("#managementGauges_underTheAxis1").removeClass("required");
        } else {
            $('#select2-managementGauges_underTheAxis-container').addClass("required");
            $('#managementGauges_underTheAxis1').addClass("required");
            massages += "<br>" + "لطفا زیر محور را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_standard').jjVal() !== "" || new jj('#managementGauges_standard1').jjVal() !== "") {
            $("#select2-managementGauges_standard-container").removeClass("required");
            $("#managementGauges_standard1").removeClass("required");
        } else {
            $('#select2-managementGauges_standard-container').addClass("required");
            $("#managementGauges_standard1").addClass("required");
            massages += "<br>" + "لطفا استاندارد را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_gauge').jjVal() !== "") {
            $("#managementGauges_gauge").removeClass("required");
        } else {
            $("#managementGauges_gauge").addClass("required");
            massages += "<br>" + "لطفا سنجه را انتخاب کنید";
            flag = false;
        }
        if (new jj('#managementGauges_step').jjVal() !== "") {
            $("#managementGauges_step").removeClass("required");
        } else {
            $('#managementGauges_step').addClass("required");
            massages += "<br>" + "لطفا گام را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_responsibleLoadingRole').jjVal() !== "") {
            $("#select2-managementGauges_responsibleLoadingRole-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_responsibleLoadingRole-container').addClass("required");
            massages += "<br>" + "لطفامسئول بارگذاری را انتخاب کنید";
            flag = false;
        }

        if (!flag) {
            new jj(massages).jjModal("پیام");
            return;
        }
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".insert";
        param += "&managementGauges_comment=" + $('#managementGauges_comment').summernote('code').trim();
        param += "&" + new jj("#swManagementGaugesForm").jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_edit: function () {
        var flag = true;
        var massages = "";
        if (new jj('#managementGauges_department').jjVal() !== "") {
            $('#select2-managementGauges_department-container').removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_department-container').addClass("required");
            massages += "<br>" + "لطفا بخش را انتخاب کنید";
            flag = false;

        }
        if (new jj('#managementGauges_responsibleGauge').jjVal() !== "") {
            $("#select2-managementGauges_responsibleGauge-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_responsibleGauge-container').addClass("required");
            massages += "<br>" + "لطفا مسئول سنجه را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_axis').jjVal() !== "") {
            $("#select2-managementGauges_axis-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_axis-container').addClass("required");
            massages += "<br>" + "لطفا محور را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_underTheAxis').jjVal() !== "") {
            $("#select2-managementGauges_underTheAxis-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_underTheAxis-container').addClass("required");
            massages += "<br>" + "لطفا زیر محور را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_standard').jjVal() !== "") {
            $("#select2-managementGauges_standard-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_standard-container').addClass("required");
            massages += "<br>" + "لطفااستاندارد را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_standard').jjVal() !== "") {
            $("#select2-managementGauges_standard-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_standard-container').addClass("required");
            massages += "<br>" + "لطفااستاندارد را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_gauge').jjVal() !== "") {
            $("#select2-managementGauges_gauge-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_gauge-container').addClass("required");
            massages += "<br>" + "لطفاسنجه را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_step').jjVal() !== "") {
            $("#select2-managementGauges_step-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_step-container').addClass("required");
            massages += "<br>" + "لطفاگام را انتخاب کنید";

            flag = false;
        }
        if (new jj('#managementGauges_responsibleLoadingRole').jjVal() !== "") {
            $("#select2-managementGauges_responsibleLoadingRole-container").removeClass("required");
            massages += "";

        } else {
            $('#select2-managementGauges_responsibleLoadingRole-container').addClass("required");
            massages += "<br>" + "لطفامسئول بارگذاری را انتخاب کنید";

            flag = false;
        }

        if (!flag) {
            new jj(massages).jjModal("پیام");
            return;
        }
        var param = "";
        param += "&do=" + hmisManagementGauges.tableName + ".edit";
        param += "&managementGauges_comment=" + $('#managementGauges_comment').summernote('code').trim();
        param += "&" + new jj("#swManagementGaugesForm").jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisManagementGauges.m_show_tbl();
        hmisManagementGauges.m_clean();
//        hmisManagementGauges.selectOptionDepartment("managementGauges_department");
        hmisManagementGauges.getoptionAxis("managementGauges_axis");
        hmisManagementGauges.getoptionUnderAxis("managementGauges_underTheAxis");
        hmisManagementGauges.getoptionStandard("managementGauges_standard");

    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("هشدار برای حذف", 'hmisManagementGauges.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".delete";
        param += "&" + hmisManagementGauges.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisManagementGauges.m_show_tbl();
        hmisManagementGauges.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".select";
        param += "&" + hmisManagementGauges.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisManagementGauges.m_show_form();

    },
    /**
     *  محور
     * @param {type} StringAxis
     * @returns {undefined}
     */

    selectGetoptionUnderAxis: function () {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".selectGetoptionUnderAxis";
        param += "&managementGauges_axis=" + $('#managementGauges_axis_search').val();
        param += "&panel=managementGauges_underTheAxis_search";
        new jj(param).jjAjax2(false);

    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisManagementGauges.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swManagementGauges').css('height', 'auto');
        hmisManagementGauges.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swManagementGauges').css('height', 'auto');
        hmisManagementGauges.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swManagementGauges').css('height', hmisManagementGauges.heightTab);
    },
}


