
// ----------------------------------------------------------------------------
var cmsPic = {
    tableName: "Pic",
    f_id: "id",
    f_pic_id: "pic_id",
    f_gallery_id: "gallery_id",
    f__title: "pic_title",
    f_pic: "pic_pic",
    f_category_id_select: "pic_gallery_id_select",
    f_lang: "pic_lang",
    f_parent: "pic_parent",
    loadForm: function () {
        if ($("#swPicForm").html() == '') {
            $("#swPicForm").load("formCms/pic.html", null, function () {

                $("#cancel_Pic").button().click(function (e) {
                    cmsPic.m_clean();
                    cmsPic.m_show_tbl();
                });
                cmsCategoryGallery.getSelectOptionCategoryGallery("categoryGallerySelectOption");
                new jj('#sendPic').jjAjaxFileUpload2('pic_file', '#pic_attachPic', '#pic_pic', '#Pic_preview');
                cmsPic.m_refresh();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsPic.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swPicTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsPic.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swPicTbl').hide();
        $('#swPicForm').show();
        $(".gallerySelectOption").select2({
            width: '100%'
        });
        cmsPic.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swPicForm").jjFormClean();
        $('#Pic_preview').attr("src", "img/preview.jpg");
        $('#pic_pic').html("");
        new jj("#" + cmsPic.f_lang).jjVal('1');
        new jj("#" + cmsPic.f_parent).jjVal('0');
    },
    m_add_new: function () {
        new jj("do=" + cmsPic.tableName + ".add_new").jjAjax2(false);
        cmsPic.m_show_form();
        cmsPic.m_clean();
    },
    m_show_tbl: function () {
        $('#swPicTbl').show();
        $('#swPicForm').hide();
        $('#refreshPic').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        if ($('#swPicTbl').html() == "") {
            cmsPic.m_refresh();
        }
        cmsPic.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsPic.tableName + ".insert";
        param += "&" + new jj("#swPicForm").jjSerial();
        param += "&" + $("#pic_discription").text();
        param += "&pic_address=" + $("#pic_pic").html();
        new jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsPic.tableName + ".edit";
        param += "&" + new jj("#swPicForm").jjSerial();
        param += "&" + $("#pic_discription").text();
        if ($("#pic_pic").html() === "") {
        }else{
            param += "&pic_address=" + $("#pic_pic").html();
        }
        new jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان داری؟").jjModal_Yes_No('حذف', 'cmsPic.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsPic.tableName + ".delete";
        param += "&" + cmsPic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    },
    m_select: function (id) {
        cmsPic.m_show_form();
        var param = "";
        param += "do=" + cmsPic.tableName + ".select";
        param += "&" + cmsPic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + cmsPic.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    m_getCategory: function (id) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".getOptions";
        param += "&panel=" + cmsPic.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsPic.tableName + ".add_EN";
        param += "&" + cmsPic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsPic.f_parent).jjVal(id);
        new jj("#" + cmsPic.f_lang).jjVal("2");
        cmsPic.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsPic.tableName + ".add_Ar";
        param += "&" + cmsPic.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsPic.f_parent).jjVal(id);
        new jj("#" + cmsPic.f_lang).jjVal("3");
        cmsPic.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsPic.tableName + ".add_lang";
        param += "&" + cmsPic.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsPic.f_parent).jjVal(id);
        new jj("#" + cmsPic.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsPic.m_show_form();
    },
    //<============ BY RASHIDI ========
    tabSizeTbl: function () {
        $('#swPicAll').css('height', 'auto');
        cmsPic.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swPicAll').css('height', 'auto');
        cmsPic.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swPicAll').css('height', cmsPic.heightTab);
    }
}
// ----------------------------------------------------------------------------
var cmsCategoryGallery = {
    tableName: "Category_Gallery",
    f_id: "id",
    f_category_gallery_id: "category_gallery_id",
    f_parent: "category_gallery_parent",
    f_title: "category_gallery_title",
    f_lang: "category_gallery_lang",
    loadForm: function () {
        if ($("#swCategoryGalleryForm").html() == '') {
            $("#swCategoryGalleryForm").load("formCms/categoryGallery.html", null, function () {
                $("#cancel_CategoryGallery").button().click(function (e) {
                    cmsCategoryGallery.m_clean();
                    cmsCategoryGallery.m_show_tbl();
                });

                cmsCategoryGallery.getSelectOptionCategoryGallery("categoryGallerySelectOption");
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCategoryGalleryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsCategoryGallery.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swCategoryGalleryTbl').hide();
        $('#swCategoryGalleryForm').show();
        cmsCategoryGallery.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swCategoryGalleryForm").jjFormClean();
        new jj("#" + cmsCategoryGallery.f_parent).jjVal('0');
        new jj("#" + cmsCategoryGallery.f_lang).jjVal('1');
        $("#CategoryGallery_Language_button").hide();
    },
    m_add_new: function () {
        new jj("do=" + cmsCategoryGallery.tableName + ".add_new").jjAjax2(false);
        cmsCategoryGallery.m_show_form();
        cmsCategoryGallery.m_clean();

    },
    m_show_tbl: function () {
        $('#swCategoryGalleryTbl').show();
        $('#swCategoryGalleryForm').hide();
        $('#refreshCategoryGallery').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        if ($('#swCategoryGalleryTbl').html() == "") {
            cmsCategoryGallery.m_refresh();
        }
        cmsCategoryGallery.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".insert";
        param += "&" + new jj("#swCategoryGalleryForm").jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".edit";
        param += "&" + new jj("#swCategoryGalleryForm").jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryGallery.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".delete";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".select";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_form();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    getSelectOption: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + cmsCategoryGallery.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    getSelectOptionCategoryGallery: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + cmsCategoryGallery.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    m_getOptions: function () {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".getOptions";
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".add_EN";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryGallery.f_parent).jjVal(id);
        new jj("#" + cmsCategoryGallery.f_lang).jjVal("2");
        cmsCategoryGallery.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".add_Ar";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryGallery.f_parent).jjVal(id);
        new jj("#" + cmsCategoryGallery.f_lang).jjVal("3");
        cmsCategoryGallery.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsCategoryGallery.tableName + ".add_lang";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryGallery.f_parent).jjVal(id);
        new jj("#" + cmsCategoryGallery.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsCategoryGallery.m_show_form();
    },
    //<============ BY RASHIDI ========
    tabSizeTbl: function () {
        $('#swPicAll').css('height', 'auto');
        cmsCategoryGallery.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swPicAll').css('height', 'auto');
        cmsCategoryGallery.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swPicAll').css('height', cmsCategoryGallery.heightTab);
    }
};
