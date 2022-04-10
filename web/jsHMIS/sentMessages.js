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
var hmisSentMessages = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swSentMessagesForm").html() == '') {
            $("#swSentMessagesForm").load("formHMIS/sentMessages.html", null, function () {
                new jj('#sentMessages_postageDate').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_SentMessages").on('click', function (e) {
                    hmisSentMessages.m_clean();
                    hmisSentMessages.m_show_tbl();
                });
                $('#sentMessages_textHTML').summernote({height: 150, tooltip: false});
                new jj('#sendFileSentMessages').jjAjaxFileUploadByTitleAndMultiFile('#attachFileSentMessages', '#messenger_file', 'messanger_titleFileSentMessages', "#showFileMessagesDiv");
                cmsContent.m_getGroupsWithUsers(".groupSelectOption");    
                cmsUser.getSelectOptionNotAll("#swSentMessagesForm #sentMessages_receiver");
                $("#sentMessages_receiver").select2({
                    width: '100%'
                });
                $("#messenger_sendingMethod").select2({
                    width: '100%'
                });
                 $(".groupSelectOption").select2({
                    width: '100%'
                });
            });
        }
    },
    m_add_new: function () {
        new jj("do=" + hmisMessenger.tableName + ".add_new").jjAjax2(false);
        $('#messenger_receiver').val("null").trigger('change');
        $('#status').hide();
        $('#logStatus').hide();
        $(".inputAfterSelectManager").html("");
        $(".inputSelectorDiv").html("");
        hmisSentMessages.m_show_form();
        hmisSentMessages.m_clean();
    },
     m_insert: function () {
        var temp = "";
        var messenger_file = $("#swSentMessagesForm  .messenger_file");
        for (var i = 0; i < messenger_file.length; i++) {
            temp += $(messenger_file[i]).val() + ",";
        }
        var param = "";
        param += "&do=" + hmisMessenger.tableName + ".insert";
        param += "&messenger_attachFile=" + temp;
        param += "&" + new jj("#swSentMessagesForm").jjSerial();       
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisMessenger.m_clean();   
    },
//      m_insert: function () {
//        var temp = "";
//        var messenger_file = $("#swSentMessagesForm  .messenger_file");
//        for (var i = 0; i < messenger_file.length; i++) {
//            temp += $(messenger_file[i]).val() + ",";
//        }
//        var temp1 = "";
//        var sendMethod = $("input:checkbox[name=messenger_sendingMethod]:checked");
//        for (var i = 0; i < sendMethod.length; i++) {
//            temp1 += $(sendMethod[i]).val() + ",";
//        }
//        var param = "";
//        param += "&messenger_file=" + temp;
//        param += "&messenger_sendingMethod=" + temp1;
//        param += "&do=" + hmisMessenger.tableName + ".insert";
//        param += "&" + new jj("#swSentMessagesForm").jjSerial();
//        param += "&jj=1";
////        param += "&messenger_receiver=" + $("#messenger_receiver option:selected").val();
//        new jj(param).jjAjax2(false);
////        hmisMessenger.m_show_tbl();
////        hmisMessenger.m_clean();
//    },
    selectOptionUserSentMessages: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisMessenger.tableName + ".selectOptionUserSentMessages";
        new jj(param).jjAjax2(false);
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".refreshSentMessages";
        param += "&panel=" + (containerId == null ? "swSentMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisSentMessages.tabSizeTbl();
    },

    bazgasht: function () {
        hmisSentMessages.m_clean();
        hmisSentMessages.m_show_tbl();
    },
    m_show_form: function () {
        $('#swSentMessagesTbl').hide();
        $('#swSentMessagesForm').show();
        if ($('#swSentMessagesTbl').html() == "") {
            hmisSentMessages.m_refresh();
        }
        hmisSentMessages.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swSentMessagesForm").jjFormClean();
        $("#messenger_receiver").val('').trigger('change');
    },
    m_show_tbl: function () {
        $('#swSentMessagesTbl').show();
        $('#swSentMessagesForm').hide();
        if ($('#swSentMessagesTbl').html() == "") {
            hmisSentMessages.m_refresh();
        }
        hmisSentMessages.m_refresh();
        hmisSentMessages.tabSizeTbl();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisSentMessages.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".delete";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSentMessages.m_show_tbl();
        hmisSentMessages.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".selectSentMessages";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        $('#status').show();
        $('#logStatus').show();
        $(".inputSelectorDiv").html("");
        $(".inputAfterSelectManager").html("");
        $('#Messenger_button').html("");
        new jj(param).jjAjax2(false);
        hmisSentMessages.m_show_form();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisSentMessages.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swSentMessages').css('height', "auto");
        hmisSentMessages.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swSentMessages').css('height', 'auto');
        hmisSentMessages.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swSentMessages').css('height', hmisSentMessages.heightTab);
    },

}




