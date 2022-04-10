/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisTiceConfig = {
    tableName: "Tice_config",
    f_id: "id",
    loadForm: function () {
        if ($("#swTiceConfigForm").html() == '') {
            $("#swTiceConfigForm").load("formHMIS/tice_config.html", null, function () {
                $("#cancel_TiceConfig").click(function (e) {
                    hmisTiceConfig.m_clean();
                    hmisTiceConfig.m_show_tbl();
                });

            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=Tice_config.refresh";
        param += "&panel=" + (containerId == null ? "swTiceConfigTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        hmisTiceConfig.tabSizeTbl();
    },
    //<============ BY RASHIDI ========
    m_show_form: function () {
        $('#swTiceConfigForm').show();
        $('#swTiceConfigTbl').hide();
        hmisTiceConfig.tabSizeForm();
    },
    m_show_tbl: function () {
        $('#swTiceConfigTbl').show();
        $('#swTiceConfigForm').hide();
        hmisTiceConfig.m_refresh();
        hmisTiceConfig.tabSizeTbl();
    },
    m_clean: function () {
        new jj('#swTiceConfigForm').jjFormClean();
    },
    m_add_new: function () {
        new jj("do=" + Tice_config.tableName + ".add_new").jjAjax2(false);
        hmisTiceConfig.m_show_form();
        hmisTiceConfig.m_clean();

    },
    m_insert: function () {
        var param = "";
        param += "do=" + Tice_config.tableName + ".insert";
        param += "&" + new jj('#swTiceConfigForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisTiceConfig.m_show_tbl();
        hmisTiceConfig.m_clean();
    },
    m_edit: function (id) {
        var param = "";
        param += "do=Tice_config.edit";
        param += "&id=" + id;
        param += "&" + new jj('#formTiceConfigform').jjSerial();
        new jj(param).jjAjax2(false);
        hmisTiceConfig.m_show_tbl();
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + Tice_config.tableName + ".delete";
        param += "&" + Tice_config.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisTiceConfig.m_show_tbl();
        hmisTiceConfig.m_clean();
    },

    m_select: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + "Tice_config.select";
        new jj(param).jjAjax2(false);
        hmisTiceConfig.tabSizeTbl();
        hmisTiceConfig.m_show_form();


    },

    heightTab: 'auto',
    tabSizeTbl: function () {
        $('#swTiceConfig').css('height', "auto");
        hmisTiceConfig.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swTiceConfig').css('height', 'auto');
        hmisTiceConfig.heightTab = 'auto';
    },
    mainTabSetSize: function () {
        $('#swTiceConfig').css('height', hmisTiceConfig.heightTab);
    }
}
