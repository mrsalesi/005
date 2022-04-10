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

var hmisDeletedMessages = {
    tableName: "Messenger",
    f_id: "id",
    loadForm: function () {
        if ($("#swDeletedMessagesForm").html() == '') {
            $("#swDeletedMessagesForm").load("formHMIS/DeletedMessages.html", null, function () {
                $("#cancel_deletedMessages").on('click', function (e) {
                    hmisDeletedMessages.m_clean();
                    hmisDeletedMessages.m_show_tbl();
                });
            });
        }
    },

    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDeletedMessages.tableName + ".refreshDeletedMessages";
        param += "&panel=" + (containerId == null ? "swDeletedMessagesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDeletedMessages.tabSizeTbl();
    },

    m_show_form: function () {
        $('#swDeletedMessagesTbl').hide();
        $('#swDeletedMessagesForm').show();
        if ($('#swDeletedMessagesTbl').html() == "") {
            hmisDeletedMessages.m_refresh();
        }
        hmisDeletedMessages.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swDeletedMessagesForm").jjFormClean();

    },
    m_show_tbl: function () {
        $('#swDeletedMessagesTbl').show();
        $('#swDeletedMessagesForm').hide();
        if ($('#swDeletedMessagesTbl').html() == "") {
            hmisDeletedMessages.m_refresh();
        }
        hmisDeletedMessages.m_refresh();
        hmisDeletedMessages.tabSizeTbl();
    },

    m_select: function (id) {
        var param = "";
        param += "do=" + hmisDeletedMessages.tableName + ".select";
        param += "&" + hmisDeletedMessages.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisDeletedMessages.m_show_form();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisDeletedMessages.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swDeletedMessages').css('height', "auto");
        hmisDeletedMessages.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swDeletedMessages').css('height', 'auto');
        hmisDeletedMessages.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swDeletedMessages').css('height', hmisDeletedMessages.heightTab);
    },
}


