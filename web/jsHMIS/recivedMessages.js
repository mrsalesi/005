var hmisRecivedMessages = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swRecivedMessagesForm").html() == '') {
            $("#swRecivedMessagesForm").load("formHMIS/recivedMessages.html", null, function () {
                new jj('#recivedMessages_postageDate').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_RecivedMessages").on('click', function (e) {
//                   alert(1);
                    hmisRecivedMessages.m_clean();
                    hmisRecivedMessages.m_show_tbl();
                });
                new jj('#sendFileReciveMessages').jjAjaxFileUploadByTitleAndMultiFile('#attachFileReciveMessages', 'messenger_file', 'messanger_titleFileReciveMessages', "#showFileMessagesReciveDiv");
                hmisRecivedMessages.selectOptionUserRecivedMessages("recivedMessages_receiver");
            });
        }
    },
    selectOptionUserRecivedMessages: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisMessenger.tableName + ".selectOptionUserRecivedMessages";

        new jj(param).jjAjax2(false);
    },
    sendMesseageToSignatory: function (userId, IdDocumentary) {
        var param = "";
        param += "&userId=" + userId;
        param += "&IdDocumentary=" + IdDocumentary;

        param += "&titleSign=" + $("#payam").parent().parent().find(".c").val();
        param += "&do=" + hmisRecivedMessages.tableName + ".sendMesseageToSignatory";
        param += "&jj=1";
        new jj(param).jjAjax2(false);

    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".refreshRecivedMessages";
        param += "&panel=" + (containerId == null ? "swRecivedMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisRecivedMessages.tabSizeTbl();
    },
    bazgasht: function () {
        hmisRecivedMessages.m_clean();
        hmisRecivedMessages.m_show_tbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisRecivedMessages.tableName + ".add_new").jjAjax2(false);
        $('#messenger_receiver').val("null").trigger('change');
//         $("#messenger_attachFileTitle").val("");
//        $("#messenger_attachFile").val("");
        $('#status').hide();
        $('#logStatus').hide();
        $(".inputAfterSelectManager").html("");
        $(".inputSelectorDiv").html("");
        hmisRecivedMessages.m_show_form();
        hmisRecivedMessages.m_clean();



        //
//        part_content_editor = new jj('#department_publicContent').jjEditor();
//        part_praivate_editor = new jj('#department_praivateContent').jjEditor();
//        cmsUser.m_getGroups();
    },
    m_show_form: function () {
        $('#swRecivedMessagesTbl').hide();
        $('#swRecivedMessagesForm').show();
        if ($('#swRecivedMessagesTbl').html() == "") {
            hmisRecivedMessages.m_refresh();
        }
        hmisRecivedMessages.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swRecivedMessagesForm").jjFormClean();
        $("#messenger_receiver").val('').trigger('change');

    },
    m_show_tbl: function () {
        $('#swRecivedMessagesTbl').show();
        $('#swRecivedMessagesForm').hide();
        if ($('#swRecivedMessagesTbl').html() == "") {
            hmisRecivedMessages.m_refresh();
        }
        hmisRecivedMessages.m_refresh();
        hmisRecivedMessages.tabSizeTbl();
    },
    m_insert: function () {
        var temp = "";
        var messenger_file = $("#swRecivedMessagesForm  .messenger_file");
        for (var i = 0; i < messenger_file.length; i++) {
            temp += $(messenger_file[i]).val() + ",";
        }
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".insertAnswerMesseges";
        param += "&messenger_attachFile=" + temp;
        param += "&" + new jj("#swRecivedMessagesForm").jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisRecivedMessages.m_show_tbl();
        hmisRecivedMessages.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "&do=" + hmisMessenger.tableName + ".edit";

        param += "&" + new jj("#swRecivedMessagesForm").jjSerial();
        param += "&jj=1";
        param += "&messenger_receiver=" + $("#recivedMessages_receiver option:selected").val();
        new jj(param).jjAjax2(false);
        $(".inputSelectorDiv").html('');


        $(".inputAfterSelectManager").html("");
        hmisRecivedMessages.m_show_tbl();
        hmisRecivedMessages.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisRecivedMessages.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".delete";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisRecivedMessages.m_show_tbl();
        hmisRecivedMessages.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".selectRecivedMessages";
        param += "&" + hmisMessenger.f_id + "=" + (id == null ? "" : id);
        $('#status').show();
        $('#logStatus').show();
        $(".inputSelectorDiv").html("");
        $(".inputAfterSelectManager").html("");

//        $('#UserSelectOption').trigger('change');

        new jj(param).jjAjax2(false);



        hmisRecivedMessages.m_show_form();


    },
    finishConsulting: function (id) {
        var param = "";
        param += "do=" + hmisMessenger.tableName + ".finishConsulting";
        param += "&statusemessenger_ratin_patient=" + id;
        param += "&statusemessenger_chatID=" + $("#statusemessenger_chatID").val();
        new jj(param).jjAjax2(false);
        hmisRecivedMessages.m_show_tbl();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisRecivedMessages.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swRecivedMessages').css('height', 'auto');
        hmisRecivedMessages.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swRecivedMessages').css('height', 'auto');
        hmisRecivedMessages.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swRecivedMessages').css('height', hmisRecivedMessages.heightTab);
    },
}




