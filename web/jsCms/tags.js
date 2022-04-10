var cmsTags = {
    tableName: "tags",
    f_id: "id",
    f_title: "tags_title",
    loadForm: function () {
        if ($("#swTagsForm").html() == '') {
            $("#swTagsForm").load("formCms/Tagshtml", null, function () {
            });
        }
    },
    m_refresh: function (containerId) {
        var param = "";
        param += "do=" + cmsTagstableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swTagsTbl" : containerId);
        new jj(param).jjAjax2(false);
        cmsTagstabSizeTbl();
    },
    m_show_form: function () {
        $('#swTagsTbl').hide();
        $('#swTagsForm').show();
        cmsTagstabSizeForm();
    },
    m_clean: function () {
        new jj("#swTagsForm").jjFormClean();
    },
    m_add_new: function () {
        new jj("do=" + cmsTagstableName + ".add_new").jjAjax2(false);
        cmsTagsm_show_form();
        cmsTagsm_clean();

    },
    m_show_tbl: function () {
        $('#swTagsTbl').show();
        $('#swTagsForm').hide();
        $('#refreshTags').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            "iDisplayLength": 10,
            destroy: true
        });
        cmsTagstabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsTagstableName + ".insert";
        param += "&" + new jj('#swTagsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsTagsm_show_tbl();
        cmsTagsm_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsTagstableName + ".edit";
        param += "&" + new jj('#swTagsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsTagsm_show_tbl();
        cmsTagsm_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف برچسب', 'cmsTagsm_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsTagstableName + ".delete";
        param += "&" + cmsTagsf_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsTagsm_show_tbl();
        cmsTagsm_clean();
    },
    tabSizeTbl: function () {
        $('#swContent').css('height', 'auto');
    },
    tabSizeForm: function () {
        $('#swContent').css('height', 'auto');
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsTagstableName + ".select";
        param += "&" + cmsTagsf_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_getSelectOptionTags: function (selector) {
        var param = "";
        param += "do=" + cmsTagstableName + ".getSelectOptionTags";
        param += "&panel=" + selector;
        new jj(param).jjAjax2(false);
    }
};