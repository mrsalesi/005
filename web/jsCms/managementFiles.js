/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cmsManagmentFiles = {
    tableName: "UploadServlet",
    f_id: "id",
    
    loadForm: function () {
        if ($("#swManagmentFilesForm").html() == '') {
            $("#swManagmentFilesForm").load("formCms/managementFiles.html", null, function () {
               new jj('#sendFile').jjAjaxFileUpload2('upload_file','#upload_attachFile','#upload_title','');
               new jj('#upload_date').jjCalendar();
               cmsManagmentFiles.m_refresh();
        });
            }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swManagmentFilesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsManagmentFiles.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swManagmentFilesTbl').hide();
        $('#swManagmentFilesForm').show();
        cmsManagmentFiles.tabSizeForm();
    },
    m_clean: function () {
         new jj("#managementFiles").jjFormClean();
    },
    m_add_new: function () {
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsManagmentFiles.tableName + ".add_new").jjAjax2(false);
//        cmsManagmentFiles.m_getCategory(); //By Md
        cmsManagmentFiles.m_show_form();
        cmsManagmentFiles.m_clean();
        $("#managementFiles").hide();
        $("#addNewFile").show();
//        $('#account_product_name').focus();
    },
    m_show_tbl: function () {
        $('#swManagmentFilesTbl').show();
        $('#swManagmentFilesForm').hide();
        if ($('#swManagmentFilesTbl').html() == "") {
            cmsManagmentFiles.m_refresh();
        }
        cmsManagmentFiles.tabSizeTbl();
        $('#refreshProduct').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".insert";
        param += "&" + new jj('#swManagmentFilesForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsManagmentFiles.m_show_tbl();
        cmsManagmentFiles.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".edit";
        param += "&" + new jj('#swManagmentFilesForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsManagmentFiles.m_show_tbl();
        cmsManagmentFiles.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف','cmsManagmentFiles.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".delete";
        param += "&" + cmsManagmentFiles.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsManagmentFiles.m_show_tbl();
        cmsManagmentFiles.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".select";
        param += "&" + cmsManagmentFiles.f_id + "=" + (id == null ? "" : id);
        $("#managementFiles").show();
         $("#addNewFile").hide();
        new jj(param).jjAjax2(false);
        cmsManagmentFiles.m_show_form();
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".insertTags";
        param += "&" + new jj('#swManagmentFilesForm').jjSerial();
        param += "&panel=account_product_tags_div";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swManagmentFiles2').css('height', 463);
        cmsManagmentFiles.heightTab = 463;
    },
    tabSizeForm: function () {
        $('#swManagmentFiles2').css('height', 315);
        cmsManagmentFiles.heightTab = 315;
    },
    heightTab: "463",
    mainTabSetSize: function () {
        $('#swManagmentFiles2').css('height', cmsManagmentFiles.heightTab);
    },
    //By Md
    m_getCategory: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".getOptions";
        param += "&panel=" + cmsManagmentFiles.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_getGroupUser: function (selector) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".selectOptionGroupUser";
        param += "&panel=" + selector;
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".add_EN";
        param += "&" + cmsManagmentFiles.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsManagmentFiles.f_parent).jjVal(id);
        new jj("#" + cmsManagmentFiles.f_lang).jjVal("2");
        cmsManagmentFiles.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".add_ar";
        param += "&" + cmsManagmentFiles.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsManagmentFiles.f_parent).jjVal(id);
        new jj("#" + cmsManagmentFiles.f_lang).jjVal("3");
        cmsManagmentFiles.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".add_lang";
        param += "&" + cmsManagmentFiles.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + cmsManagmentFiles.f_parent).jjVal(id);
        new jj("#" + cmsManagmentFiles.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsManagmentFiles.m_show_form();
    },
    //<============ BY RASHIDI ========
    //===========================SHAHSANAEI=============================>
    showTopProducts: function (containerId, maxNo, split) {//این تابع عددی از کاربر می گیرد و به همان تعداد جدیدترین کالاها را به او برمی گرداند همچنین این تعداد کالا را به بخش هایی که مد نظر کاربر است تقسیم می کند
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".getTopProducts";
        param += "&panel=" + (containerId == null ? "showTopProductsDiv" : containerId);
        param += "&maxNo=" + (maxNo == null ? 10 : maxNo); //حداکثر تعداد کالاهایی که کاربر میخواهد نمایش داده شود
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsManagmentFiles.m_show_form();
//        cmsManagmentFiles.m_getCategory(id); //By Md
    },
    showdevidedProducts: function (split, page, containerId) {//این تابع کالاهای صفحه پیج که تعداد آن ها برابر اسپلیت است را به کاربر نمایش می دهد.
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".showDevidedProducts";
        param += "&panel=" + (containerId == null ? "showDividedProductsDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        param += "&page=" + (page == null ? 0 : page); //این متغیر بیانگر این است که کاربر میخواهد کالاهای کدام صفحه را ببیند
        jj(param).jjAjax2(false);
//        cmsManagmentFiles.m_show_form();
//        cmsManagmentFiles.m_getCategory(id); //By Md
    },
    calculateNumOfPages: function (containerId, split) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".calculateNumOfPages";
        param += "&panel=" + (containerId == null ? "pagingDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsManagmentFiles.m_show_form();
//        cmsManagmentFiles.m_getCategory(id); //By Md
    },
    showAllTags: function (containerId) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".getAllTags";
        param += "&panel=" + (containerId == null ? "account_product_tag1" : containerId);
        jj(param).jjAjax2(false);
//        cmsManagmentFiles.m_show_form();
//        cmsManagmentFiles.m_getCategory(id); //By Md
    },
    loadProduct: function (start, end, containerId) {
        var param = "";
        param += "do=" + cmsManagmentFiles.tableName + ".loadProduct";
        param += "&panel=" + (containerId == null ? "products" : containerId);
        param += "&start=" + (start == null ? 0 : start);
        param += "&end=" + (end == null ? 5 : end);
        jj(param).jjAjax2(false);
//        cmsManagmentFiles.m_show_form();
//        cmsManagmentFiles.m_getCategory(id); //By Md
    },
    loadChange: function (containerId) {
//        var param = "";
//        param += "do=" + cmsManagmentFiles.tableName + ".numOfProducts";
//        var products = jj(param).jjAjax2(false);
//        return products;
    },
    //<===========================SHAHSANAEI=============================

    //============ BY RASHIDI ========>
    m_showProperties: function (divId) {
        var elementId = divId == "" ? "extraPropertiesTbl" : divId;
        if ($("#" + elementId).css('display') == 'none') {
            $("#" + elementId).show();
            $("#ion-minus-circled").show();
            $("#ion-plus-circled").hide();
        } else {
            $("#ion-plus-circled").show();
            $("#" + elementId).hide();
            $("#ion-minus-circled").hide();
        }
    }
};
