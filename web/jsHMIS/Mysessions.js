/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisMySessions = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
//        if ($("#swMySessionsForm").html() == '') {
//            $("#swMySessionsForm").load("formHMIS/05OneSession.html", null, function () {
//
//                $("#cancel_MySessions").button().click(function (e) {
//                    hmisMySessions.m_clean();
//                    hmisMySessions.m_show_tbl();
//                });
//                hmisMySessions.m_refresh();
//
//            });
//        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".refreshMySessions";
        param += "&panel=" + (containerId == null ? "swMySessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

    m_show_form: function () {
        $('#swMySessionsTbl').slideUp('slow');
        $('#swMySessionsForm').slideDown('slow');
        hmisMySessions.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisMySessions.f_content_id).jjVal("");
        new jj("#" + hmisMySessions.f_title).jjVal("");
        new jj("#" + hmisMySessions.f_lang).jjVal("1");
        new jj("#" + hmisMySessions.f_parent).jjVal("0");

    },

    m_show_tbl: function () {
        $('#swMySessionsTbl').show();
        $('#swMySessionsForm').hide();
        $('#refreshMySessions').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".selectMySessions";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_form();
    },

    tabSizeTbl: function () {
        $('#swMySessions').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swMySessions').css('height', 'auto');
    },

};
