/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisIndicatorCommettes = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swIndicatorCommettesForm").html() == '') {
            $("#swIndicatorCommettesForm").load("formHMIS/05indicatorCommettes.html", null, function () {
                hmisIndicatorCommettes.m_show_form();
                new jj(".dateFrom").jjCalendarWithYearSelector(1397, 1420);
                new jj(".dateTo").jjCalendarWithYearSelector(1397, 1420);
                $(".commettesTitle").select2({
                    width: '100%'
                });
                $(".AllcommettesTitle").select2({
                    width: '100%'
                });
                hmisIndicatorCommettes.getSelectOptionIndicatorCommettes();
                hmisIndicatorCommettes.getSelectOptionCommettesAll();
                hmisRole.getSelectOptionRequierd("#swIndicatorCommettesForm #roleIdOneAudience");
            });
        }

    },
    refreshIndicatorCommettes: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refreshIndicatorCommettes";
        param += "&panel=" + (containerId == null ? "swApprovedTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swIndicatorCommettesTbl').slideUp('slow');
        $('#swIndicatorCommettesForm').slideDown('slow');


        hmisIndicatorCommettes.tabSizeForm();
    },

    //نمایش لیست کمیته ها
    getSelectOptionIndicatorCommettes: function () {
        new jj("do=Commettes.getSelectOptionIndicatorCommettes").jjAjax2(false);

    },
    /**
     *  نمایش همه کمیته ها
     * @returns {undefined}
     */
    getSelectOptionCommettesAll: function () {
        new jj("do=Commettes.getSelectOptionCommettesAll").jjAjax2(false);

    },
    searchAudience: function () {
        $("#commettesTitleIndicator option:selected").val();
        $('#dataFrom').val();
        $('#dataTo').val();
        new jj("do=Commettes.getSelectOptionCommettes").jjAjax2(false);

    },
    ShowCommetteSessions: function (commettesId) {
        var flag = true;
        if ($("#dateFrom").val() === "") {
            $("#dateFrom").addClass('required');
            flag = false;
        } else {
            $("#dateFrom").removeClass('required');
        }
        if ($("#dateTo").val() === "") {
            $("#dateTo").addClass('required');
            flag = false;
        } else {
            $("#dateTo").removeClass('required');
        }
//        if ($("#commettesTitleIndicator").val() === "") {
//            $("#select2-commettesTitleIndicator-container").addClass('required');
//            flag = false;
//        } else {
//            $("#select2-commettesTitleIndicator-container").removeClass('required');
//        }
        if (flag === true) {
            var param = "";
            param += "&commettesId=" + commettesId;
            param += "&dateFrom=" + $('#dateFrom').val();
            param += "&dateTo=" + $('#dateTo').val();
            param += "&do=Commettes.ShowCommetteSessions";
            new jj(param).jjAjax2(false);
        } else {
            new jj("فیلد های تاریخ را پر کنید").jjModal("پیام سامانه");
        }
    },
    reportOneAudience: function (roleId) {
        var flag = true;
        if ($("#dateFromOneAudience").val() === "") {
            $("#dateFromOneAudience").addClass('required');
            flag = false;
        } else {
            $("#dateFromOneAudience").removeClass('required');
        }
        if ($("#dateToOneAudience").val() === "") {
            $("#dateToOneAudience").addClass('required');
            flag = false;
        } else {
            $("#dateToOneAudience").removeClass('required');
        }
//        if ($("#commettesTitleIndicator").val() === "") {
//            $("#select2-commettesTitleIndicator-container").addClass('required');
//            flag = false;
//        } else {
//            $("#select2-commettesTitleIndicator-container").removeClass('required');
//        }
        if (flag === true) {
            var param = "";
            param += "&roleIdOneAudience=" + roleId;
            param += "&dateFromOneAudience=" + $('#dateFromOneAudience').val();
            param += "&dateToOneAudience=" + $('#dateToOneAudience').val();
            param += "&do=Commettes.reportOneAudience";
            new jj(param).jjAjax2(false);
        } else {
            new jj("فیلد های تاریخ را پر کنید").jjModal("پیام سامانه");
        }
    },
    /**
     * نمایش گزارشات مصوبات
     * @param {type} commettesId
     * @returns {undefined}
     */
    showApprovedReport: function (commettesId) {
        var flag = true;
        if ($("#dateFromApproved").val() === "") {
            $("#dateFromApproved").addClass('required');
            flag = false;
        } else {
            $("#dateFromApproved").removeClass('required');
        }
        if ($("#dateToApproved").val() === "") {
            $("#dateToApproved").addClass('required');
            flag = false;
        } else {
            $("#dateToApproved").removeClass('required');
        }
        if ($("#commettesTitle1").val() === "") {
            $("#select2-commettesTitle1-container").addClass('required');
            flag = false;
        } else {
            $("#select2-commettesTitle1-container").removeClass('required');
        }
        if (flag === true) {
            var param = "";
            param += "&commettesId=" + commettesId;
            param += "&dateFromApproved=" + $('#dateFromApproved').val();
            param += "&dateToApproved=" + $('#dateToApproved').val();
            param += "&do=Commettes.showApprovedReport";
            new jj(param).jjAjax2(false);
        } else {
            new jj("فیلد های تاریخ را پر کنید").jjModal("پیام سامانه");
        }
    },
    m_clean: function () {
        new jj("#" + hmisIndicatorCommettes.f_content_id).jjVal("");
        new jj("#" + hmisIndicatorCommettes.f_title).jjVal("");
        new jj("#" + hmisIndicatorCommettes.f_lang).jjVal("1");
        new jj("#" + hmisIndicatorCommettes.f_parent).jjVal("0");

    },

    m_show_tbl: function () {
        $('#swIndicatorCommettesTbl').show();
        $('#swIndicatorCommettesForm').hide();
        if ($('#swIndicatorCommettesTbl').html() == "") {
            hmisIndicatorCommettes.m_refresh();
        }
        hmisIndicatorCommettes.tabSizeTbl();
    },
//    m_select: function (id) {
////        $("#addNewApproved").hide();
//        var param = "";
//        param += "do=" + hmisPlans.tableName + ".selectIndicatorCommettes";
//        param += "&" + hmisPlans.f_id + "=" + (id == null ? "" : id);
//        new jj(param).jjAjax2(false);
//        hmisIndicatorCommettes.m_show_form();
//    },



    tabSizeTbl: function () {
        $('#swIndicatorCommettes').css('height', 'auto');
    },
    tabSizeForm: function () {
        $('#swIndicatorCommettes').css('height', 'auto');
    },

};
