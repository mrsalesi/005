var cmsLangSetting = {
    tableName: "Language",
    f_id: "id",
    loadForm: function () {
        if ($("#swLangSettingForm").html() == '') {
            $("#swLangSettingForm").load("formCms/language.html", null, function () {
                cmsLangSetting.m_refresh();
                $("#cancel_LangSetting").button().click(function (e) {
                    $("#swLangSettingAll").tabs({selected: 0});
                });
                //============ BY RASHIDI ========>
                $("#LangSetting_button").button().click(function (e) {
                    cmsLangSetting.m_edit();
                    $("#swLangSettingAll").tabs({selected: 0});
                });

            }
            );
        }
//        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsLangSetting.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swSettingLangForm" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsLangSetting.tabSizeTbl();
    },
    //<============ BY RASHIDI ========
    m_show_form: function () {
        $('#swLangSettingForm').show();
        cmsLangSetting.tabSizeForm();
    },
    m_clean: function () {
        new jj('#swLangSettingForm').jjFormClean();
    },
    m_add_new: function () {
        jj("do=" + cmsLangSetting.tableName + ".add_new").jjAjax2(false);
        cmsLangSetting.m_show_form();
        cmsLangSetting.m_clean();

    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsLangSetting.tableName + ".insert";
        param += "&" + new jj('#swLangSettingForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsLangSetting.m_show_tbl();
        cmsLangSetting.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsLangSetting.tableName + ".edit";
        param += "&" + new jj('#swLangSettingForm').jjSerial();
        jj(param).jjAjax2(false);
//        cmsLangSetting.m_clean();
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsLangSetting.tableName + ".delete";
        param += "&" + cmsLangSetting.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        cmsLangSetting.m_show_tbl();
        cmsLangSetting.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsLangSetting.tableName + ".select";
        param += "&" + cmsLangSetting.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        cmsLangSetting.m_show_form();

    },
    heightTab: "514",
    tabSizeTbl: function () {
        $('#swSettingAll').css('height', 514);
        cmsLangSetting.heightTab = 514;
    },
    tabSizeForm: function () {
        $('#swSettingAll').css('height', 514);
        cmsLangSetting.heightTab = 514;
    },
    mainTabSetSize: function () {
        $('#swSettingAll').css('height', cmsLangSetting.heightTab);
    }
}
