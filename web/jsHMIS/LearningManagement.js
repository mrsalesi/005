/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisLearningDangerManagment = {
    tableName: "",
    f_id: "id",
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".refreshLearningDangerManagment";
        param += "&panel=" + (containerId == null ? "swLearningDangerManagment" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisLearningDangerManagment.tabSizeTbl();
        hmisLearningDangerManagment.m_show_tbl();
    },
    m_show_tbl: function () {
        $('#refreshLearningDangerManagment').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    tabSizeTbl: function () {
        $('#swLearningDangerManagment').css('height', 'auto');
    }
};
var hmisLearningErrorAndDangerManagment = {
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".refreshLearningErrorAndDangerManagment";
        param += "&panel=" + (containerId == null ? "swLearningErrorAndDangerManagment" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisLearningErrorAndDangerManagment.tabSizeTbl();
        hmisLearningErrorAndDangerManagment.m_show_tbl();
    },
    m_show_tbl: function () {
        $('#refreshLearningErrorAndDangerManagment').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    tabSizeTbl: function () {
        $('#swLearningErrorAndDangerManagment').css('height', 'auto');
    }
};


     