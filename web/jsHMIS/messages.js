/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




var hmisMessages = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swMessagesForm").html() == '') {
            $("#swMessagesForm").load("formHMIS/messages.html", null, function () {
                new jj('#messages_postageDate').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_Messages").on('click', function (e) {
//                   alert(1);
                    hmisMessages.m_clean();
                    hmisMessages.m_show_tbl();
                });

                new jj('#sendMessages').jjAjaxFileUploadByTitleAndMultiFile('#MessagesAttachFile', '#messenger_file', 'messages_titleFile', '#showFileMessagesDiv');
                hmisMessages.selectOptionUserMessages("messages_receiver");
                $('#messages_textHTML').summernote();
                $('#messagesUploadFileDiv').hide();

            });
        }
    },
    selectOptionUserMessages: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisMessenger.tableName + ".selectOptionUserMessages";

        new jj(param).jjAjax2(false);
    },

    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".refreshMessages";
        param += "&panel=" + (containerId == null ? "swMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisMessages.tabSizeTbl();
    },
    m_refreshMessagesAll: function (containerId, sortField, tableHeight) {
//        $("#AllMessenger").slideDown();
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".refreshMessagesAll";
        param += "&panel=" + (containerId == null ? "swMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisMessages.tabSizeTbl();
    },
    bazgasht: function () {
        hmisMessages.m_clean();
        hmisMessages.m_show_tbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisMessenger.tableName + ".add_new").jjAjax2(false);
        $('#messenger_receiver').val("null").trigger('change');
//        $("#messenger_attachFileTitle").val("");
//        $("#messenger_attachFile").val("");
        $('#status').hide();
        $('#logStatus').hide();
        $(".inputAfterSelectManager").html("");
        $(".inputSelectorDiv").html("");
        hmisMessages.m_show_form();
        hmisMessages.m_clean();
    },
    m_show_form: function () {
        $('#swMessagesTbl').hide();
        $('#swMessagesForm').show();
        if ($('#swMessagesTbl').html() == "") {
            hmisMessages.m_refresh();
        }
        hmisMessages.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swMessagesForm").jjFormClean();
        $("#messenger_receiver").val('').trigger('change');
    },
    m_show_tbl: function () {
        $('#swMessagesTbl').show();
        $('#swMessagesForm').hide();
        if ($('#swMessagesTbl').html() == "") {
            hmisMessages.m_refresh();
        }
        hmisMessages.m_refresh();
        hmisMessages.tabSizeTbl();
    },

    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisMessages.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".delete";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisMessages.m_show_tbl();
        hmisMessages.m_clean();
    },

    m_select: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".selectMessages";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        $('#status').show();
        $('#logStatus').show();
        $(".inputSelectorDiv").html("");
        $(".inputAfterSelectManager").html("");
        new jj(param).jjAjax2(false);
        hmisMessages.m_show_form();

    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisMessages.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swMessages').css('height', 'auto');
        hmisMessages.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swMessages').css('height', 'auto');
        hmisMessages.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swMessages').css('height', hmisMessages.heightTab);
    },
}




