/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




var hmisMyMessages = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swMyMessagesForm").html() == '') {
            $("#swMyMessagesForm").load("formHMIS/myMessages.html", null, function () {
                new jj('#myMessages_postageDate').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_MyMessages").on('click', function (e) {
//                   alert(1);
                    hmisMyMessages.m_clean();
                    hmisMyMessages.m_show_tbl();
                });
//                $("#myMessenger_textHTML").summernote();
                new jj('#sendMyMessage').jjAjaxFileUploadTitleUploadFiles('#MyMessageAttachFile', '#myMessages_attachFile', 'myMessages_titleFile', '#myMessages_attachFileTitle');


                hmisMyMessages.selectOptionUserMyMessages("myMessages_receiver");

            });


        }
    },
    //این تابع برای در اوردن user هاوانتخاب از select options نوشته شده
    selectOptionUserMyMessages: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisMessenger.tableName + ".selectOptionUserMyMessages";

        new jj(param).jjAjax2(false);
    },

    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".refreshMyMessages";
        param += "&panel=" + (containerId == null ? "swMyMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisMyMessages.tabSizeTbl();
    },
    bazgasht: function () {
        hmisMyMessages.m_clean();
        hmisMyMessages.m_show_tbl();
    },

    m_show_form: function () {
        $('#swMyMessagesTbl').hide();
        $('#swMyMessagesForm').show();
        if ($('#swMyMessagesTbl').html() == "") {
            hmisMyMessages.m_refresh();
        }
        hmisMyMessages.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swMyMessagesForm").jjFormClean();
        $("#myMessages_receiver").val('').trigger('change');

    },
    m_show_tbl: function () {
        $('#swMyMessagesTbl').show();
        $('#swMyMessagesForm').hide();
        if ($('#swMyMessagesTbl').html() == "") {
            hmisMyMessages.m_refresh();
        }
        hmisMyMessages.m_refresh();
        hmisMyMessages.tabSizeTbl();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisMyMessages.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".delete";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisMyMessages.m_show_tbl();
        hmisMyMessages.m_clean();
    },

    m_select: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".selectMyMessages";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        $('#status').show();
        $('#logStatus').show();
        $(".inputSelectorDiv").html("");
        $(".inputAfterSelectManager").html("");
        new jj(param).jjAjax2(false);
        hmisMyMessages.m_show_form();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisMyMessages.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swMyMessages').css('height', 'auto');
        hmisMyMessages.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swMyMessages').css('height', 'auto');
        hmisMyMessages.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swMyMessages').css('height', hmisMyMessages.heightTab);
    },
    selectTicket: function (id) {
        hmisMyMessages.loadForm();
        hmisMyMessages.m_show_form();
        $("#ticketHeader").hide();//این قسمت فقط برای اولین پیام فعال باشد
        new jj('#formMassege').jjFormClean();
        $('#newTicket').slideDown();
        $('#ticketTable').hide();
        var params = "messenger_chatID=" + id + "&do=Messenger.refreshChat&jj=1";
        new jj(params).jjAjax(false);
    }
    //    m_add_new: function () {
//        new jj("do=" + hmisMyMessages.tableName + ".add_new").jjAjax2(false);
//        $('#messenger_receiver').val("null").trigger('change');
//        $("#messenger_attachFileTitle").val("");
//        $("#messenger_attachFile").val("");
//        $('#status').hide();
//        $('#logStatus').hide();
//        $(".inputAfterSelectManager").html("");
//        $(".inputSelectorDiv").html("");
//        hmisMyMessages.m_show_form();
//        hmisMyMessages.m_clean();
//
//
//
//        //
////        part_content_editor = new jj('#department_publicContent').jjEditor();
////        part_praivate_editor = new jj('#department_praivateContent').jjEditor();
////        cmsUser.m_getGroups();
//    },
//    m_insert: function () {
//
//        var param = "";
//        param += "do=" + hmisMyMessages.tableName + ".insert";
//        param += "&" + new jj("#swMyMessagesForm").jjSerial();
//
//        param += "&jj=1";
//        param += "&messenger_receiver=" + $("#messenger_receiver option:selected").val();
//        new jj(param).jjAjax2(false);
//
//        hmisMyMessages.m_show_tbl();
//        hmisMyMessages.m_clean();
//    },
//    m_edit: function () {
//        var param = "";
//        param += "&do=" + hmisMyMessages.tableName + ".edit";
//
//        param += "&" + new jj("#swMyMessagesForm").jjSerial();
//        param += "&jj=1";
//        param += "&messenger_receiver=" + $("#messenger_receiver option:selected").val();
//        new jj(param).jjAjax2(false);
//        $(".inputSelectorDiv").html('');
//
//
//        $(".inputAfterSelectManager").html("");
//        hmisMyMessages.m_show_tbl();
//        hmisMyMessages.m_clean();
//    },
//    sendMesseageToSignatory: function (userId, IdDocumentary) {
//        var param = "";
//        param += "&userId=" + userId;
//        param += "&IdDocumentary=" + IdDocumentary;
//
//        param += "&titleSign=" + $("#payam").parent().parent().find(".c").val();
//        param += "&do=" + hmisMyMessages.tableName + ".sendMesseageToSignatory";
//        param += "&jj=1";
//        new jj(param).jjAjax2(false);
//
//    }
    
}


