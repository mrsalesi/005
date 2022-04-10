/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

///ایجاد مستند

var hmisDocumentary = {
    tableName: "Documentary",
    f_id: "id",
    loadForm: function () {
//        if ($("#swDocumentaryForm").html() == '') {
        if (true) {
            $("#swDocumentaryForm").load("formHMIS/documentary.html", null, function () {
                new jj("#documentary_dateCreation").jjCalendarWithYearSelector(1340, 1420);
//                new jj('#documentary_LoadingPeriod').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_Documentary").button().click(function (e) {
                    hmisDocumentary.m_clean();
                    hmisDocumentary.m_show_tbl();
                });
                new jj('#sendFiles').jjAjaxFileUploadByTitleAndMultiFile('#attachFileDocumentary', 'documentary_attachFileDocumentary', 'documentary_titleFile', "#documentary_divUpload");
            });
        }
    },
    m_refresh_my: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".refresh_my";
        param += "&panel=" + (containerId == null ? "swDocumentaryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDocumentary.tabSizeTbl();
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swDocumentaryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDocumentary.tabSizeTbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisDocumentary.tableName + ".add_new").jjAjax2(false);
        hmisDocumentary.m_show_form();




        hmisDocumentary.m_clean();


    },
    m_show_form: function () {
        $('#swDocumentaryTbl').hide();
        $('#swDocumentaryForm').show();
        new jj("#swDocumentaryForm").jjFormClean();

        hmisDocumentary.tabSizeForm();
    },
    m_show_formCopy: function () {
        $('#swDocumentaryTbl').hide();
        $('#swDocumentaryForm').show();
        hmisDocumentary.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swDocumentaryForm").jjFormClean();
        $("#inputAfterSelectGauge").html('');
        $("#documentary_divUpload").html('');
    },
    m_show_tbl: function () {
        $('#swDocumentaryTbl').show();
        $('#swDocumentaryForm').hide();
//        if ($('#swDocumentaryTbl').html() == "") {
//            hmisDocumentary.m_refresh();
//        }
//        hmisDocumentary.m_refresh();
        hmisDocumentary.tabSizeTbl();

    },
    m_edit: function (id) {
        var documentary_attachFileDocumentary = $("#swDocumentaryForm .documentary_attachFileDocumentary");
        var temp = ""
        for (var i = 0; i < documentary_attachFileDocumentary.length; i++) {
            temp += $(documentary_attachFileDocumentary[i]).val() + ",";
        }

        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".edit";
        param += "&documentary_attachFileDocumentary=" + temp;
        param += "&" + new jj("#swDocumentaryForm").jjSerial();
        new jj(param).jjAjax2(false);
//        hmisDocumentary.m_show_tbl();
        $('#swDocumentaryTbl').show();
        $('#swDocumentaryForm').hide();
        hmisDocumentary.m_clean();
        $(".inputSelectorDiv").html('');

    },
    m_editAndFinalize: function () {
        var noFileWarningMassage = "";// پیام هشدار برای اینکه اگر فایل بارگذاری نکرده بود مطلع بشود
        if ($("#swDocumentaryForm .documentary_attachFileDocumentary").length == 0) {
            noFileWarningMassage = "شما برای این سنجه فایلی بارگذاری نکرده اید" + " <br/>";
        }
        new jj(noFileWarningMassage + "آیا از تایید نهایی این رکورد اطمینان دارید؟").jjModal_Yes_No("ثبت نهایی", 'hmisDocumentary.m_editAndFinalizeAfterQuestion();');
    },
    m_editAndFinalizeAfterQuestion: function () {
        var documentary_attachFileDocumentary = $("#swDocumentaryForm .documentary_attachFileDocumentary");
        var temp = ""
        for (var i = 0; i < documentary_attachFileDocumentary.length; i++) {
            temp += $(documentary_attachFileDocumentary[i]).val() + ",";
        }
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".edit";
        param += "&documentary_attachFileDocumentary=" + temp;
        param += "&documentary_status=بارگذاری شده";// برای تغییر وصعیت و بعد از ویرایش انجام می شود
        param += "&" + new jj("#swDocumentaryForm").jjSerial();
        new jj(param).jjAjax2(false);
//        hmisDocumentary.m_show_tbl();
        $('#swDocumentaryTbl').show();
        $('#swDocumentaryForm').hide();
        hmisDocumentary.m_clean();
        $(".inputSelectorDiv").html('');

    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisDocumentary.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".delete";
        param += "&" + hmisDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisDocumentary.m_show_tbl();
        hmisDocumentary.m_clean();
    },
    undo: function (id) {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".undo";
        param += "&" + hmisDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisDocumentary.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".select";
        param += "&" + hmisDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $(".inputSelectorDiv").html("");
        hmisDocumentary.m_show_form();
        hmisDocumentary.m_clean();

    },
    copyDocumentary: function () {
        alert("مستند مورد نظر کپی شد");
        new jj("do=" + hmisDocumentary.tableName + ".copyDocumentary").jjAjax2(false);
        hmisDocumentary.m_show_formCopy();
        $("#signatorys").html('');
        $("#signatorysAdd").html('');
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisDocumentary.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swDocumentary').css('height', 'auto');
        hmisDocumentary.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swDocumentary').css('height', 'auto');
        hmisDocumentary.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swDocumentary').css('height', hmisDocumentary.heightTab);
    },
}







