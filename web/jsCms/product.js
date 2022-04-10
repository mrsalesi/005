var cmsProduct = {
    tableName: "Product",
    f_id: "id",
    f_title: "product_title",
    //By Md
    f_category_id_select: "account_product_category_select",
    f_priority: "account_product_priority",
    f_parent: "account_product_parent",
    f_date: "account_product_date",
    f_lang: "account_product_lang",
    loadForm: function () {
        if ($("#swProductForm").html() == '') {
            $("#swProductForm").load("formAccount/product.html", null, function () {
                new jj("#account_product_date").jjCalendarWithYearSelector(1397, 1420);
                new jj("#account_product_discount_Date").jjCalendarWithYearSelector(1397, 1420);
                cmsProduct.m_getGroupUser("selectOptionGroupUserProduct");
                cmsCategoryProducts.m_getOptions("categoryProductSelectOption");
//                cmsTagsm_getSelectOptionTags("#account_product_tags");
//                $("#account_product_tags").select2({
//                    width: '100%'
//                });
//                new jj("#account_product_endDate").jjCalendarWithYearSelector(1397, 1420);
//                $('#account_product_endTime').wickedpicker({
//                    twentyFour: true
//                });
//                new jj("#account_product_startDate").jjCalendarWithYearSelector(1397, 1420);
//                $('#account_product_startTime').wickedpicker({
//                    twentyFour: true
//                });
                //////////////////////////picture
                new jj('#account_product_sendPic1').jjAjaxFileUpload2('account_product_file1', '', '#account_product_pic1', '#account_product_preview1');
                new jj('#account_product_sendPic2').jjAjaxFileUpload2('account_product_file2', '', '#account_product_pic2', '#account_product_preview2');
                new jj('#account_product_sendPic3').jjAjaxFileUpload2('account_product_file3', '', '#account_product_pic3', '#account_product_preview3');
                new jj('#account_product_sendPic4').jjAjaxFileUpload2('account_product_file4', '', '#account_product_pic4', '#account_product_preview4');
                new jj('#account_product_sendPic5').jjAjaxFileUpload2('account_product_file5', '', '#account_product_pic5', '#account_product_preview5');
                new jj('#account_product_sendPic6').jjAjaxFileUpload2('account_product_file6', '', '#account_product_pic6', '#account_product_preview6');
                ////////////******************************
                $('#account_product_discount').keyup(function () {//محاسبه تخفیف
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#discountPercent').val((((price - $('#account_product_discount').val()) * 100) / price).toFixed(2)); //عدد درصد تخفیف را تا دو رقم بعد اعشار نمایش میدهد
                });
                $('#account_product_groupPrice1').keyup(function () {
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#pricePercent1').val((((price - $('#account_product_groupPrice1').val()) * 100) / price).toFixed(2));
                });
                $('#account_product_groupPrice2').keyup(function () {
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#pricePercent2').val((((price - $('#account_product_groupPrice2').val()) * 100) / price).toFixed(2));
                });
                $('#account_product_groupPrice3').keyup(function () {
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#pricePercent3').val((((price - $('#account_product_groupPrice3').val()) * 100) / price).toFixed(2));
                });
                $('#account_product_groupPrice4').keyup(function () {
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#pricePercent4').val((((price - $('#account_product_groupPrice4').val()) * 100) / price).toFixed(2));
                });
                $('#account_product_groupPrice5').keyup(function () {
                    var price = $('#account_product_price2').val();
//                            alert((price - $('#account_product_groupPrice2').val())*100);
                    $('#pricePercent5').val((((price - $('#account_product_groupPrice5').val()) * 100) / price).toFixed(2));
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swProductTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsProduct.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swProductTbl').hide();
        $('#swProductForm').show();
        cmsProduct.tabSizeForm();
    },
    m_clean: function () {
        new jj("#account_products_active").jjVal("1");
        new jj("#account_product_pic1").jjVal("");
        new jj("#account_product_pic2").jjVal("");
        new jj("#account_product_pic3").jjVal("");
        new jj("#account_product_pic4").jjVal("");
        new jj("#account_product_pic5").jjVal("");
        new jj("#account_product_pic6").jjVal("");
        $("#account_product_preview1").attr("src","img/preview.jpg");
        $("#account_product_preview2").attr("src","img/preview.jpg");
        $("#account_product_preview3").attr("src","img/preview.jpg");
        $("#account_product_preview4").attr("src","img/preview.jpg");
        $("#account_product_preview5").attr("src","img/preview.jpg");
        $("#account_product_preview6").attr("src","img/preview.jpg");
        new jj("#account_product_name").jjVal("");
        new jj("#account_product_code").jjVal("");
        new jj("#account_product_price1").jjVal("");
        new jj("#account_product_price2").jjVal("");
        new jj("#account_product_taxPercent").jjVal("");
        new jj("#account_product_quantity").jjVal("");
        new jj("#account_product_groupPrice1").jjVal("");
        new jj("#pricePercent1").jjVal("");
        new jj("#account_product_groupPrice2").jjVal("");
        new jj("#pricePercent2").jjVal("");
        new jj("#account_product_groupPrice3").jjVal("");
        new jj("#pricePercent3").jjVal("");
        new jj("#account_product_groupPrice4").jjVal("");
        new jj("#pricePercent4").jjVal("");
        new jj("#account_product_groupPrice5").jjVal("");
        new jj("#pricePercent5").jjVal("");
        new jj("#account_product_groupPrice").jjVal("");
        new jj("#pricePercent").jjVal("");
        new jj("#account_product_discount_Date").jjVal("");
        new jj("#account_product_val1").jjVal("");
        new jj("#account_product_val2").jjVal("");
        new jj("#account_product_val3").jjVal("");
        new jj("#account_product_val4").jjVal("");
        new jj("#account_product_val5").jjVal("");
        new jj("#account_product_val6").jjVal("");
        new jj("#account_product_val7").jjVal("");
        new jj("#account_product_val8").jjVal("");
        new jj("#account_product_val9").jjVal("");
        new jj("#account_product_val10").jjVal("");
        new jj("#account_product_val11").jjVal("");
        new jj("#account_product_val12").jjVal("");
        new jj("#account_product_val13").jjVal("");
        new jj("#account_product_val14").jjVal("");
        new jj("#account_product_val15").jjVal("");
        new jj("#account_product_val16").jjVal("");
        new jj("#account_product_val17").jjVal("");
        new jj("#account_product_val18").jjVal("");
        new jj("#account_product_val19").jjVal("");
        new jj("#account_product_val20").jjVal("");
        new jj("#account_product_prop1").jjVal("");
        new jj("#account_product_prop2").jjVal("");
        new jj("#account_product_prop3").jjVal("");
        new jj("#account_product_prop4").jjVal("");
        new jj("#account_product_prop5").jjVal("");
        new jj("#account_product_prop6").jjVal("");
        new jj("#account_product_prop7").jjVal("");
        new jj("#account_product_prop8").jjVal("");
        new jj("#account_product_prop9").jjVal("");
        new jj("#account_product_prop10").jjVal("");
        new jj("#account_product_prop11").jjVal("");
        new jj("#account_product_prop12").jjVal("");
        new jj("#account_product_prop13").jjVal("");
        new jj("#account_product_prop14").jjVal("");
        new jj("#account_product_prop15").jjVal("");
        new jj("#account_product_prop16").jjVal("");
        new jj("#account_product_prop17").jjVal("");
        new jj("#account_product_prop18").jjVal("");
        new jj("#account_product_prop19").jjVal("");
        new jj("#account_product_prop20").jjVal("");
        //By Md
        new jj("#account_products_abstract").jjVal("");
        $("#account_product_pic_name_preview").attr('src', "img/news.png");
        new jj("#" + cmsProduct.f_priority).jjVal("1");
        new jj("#" + cmsProduct.f_parent).jjVal("0");
        new jj("#account_product_visit").jjVal("0");
        $("#account_product_visit").removeAttr("disabled");
        new jj("#account_product_visit_checkbox").jjVal("1");
        new jj("#account_product_like").jjVal("0");
        $("#account_product_like").removeAttr("disabled");
        new jj("#account_product_like_checkbox").jjVal("1");
        new jj("#account_product_dislike").jjVal("0");
        $("#account_product_dislike").removeAttr("disabled");
        new jj("#account_product_dislike_checkbox").jjVal("1");
        $("#Product_Language_button").hide();
        new jj("#" + cmsProduct.f_lang).jjVal("1");
    },
    m_add_new: function () {
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsProduct.tableName + ".add_new").jjAjax2(false);
//        cmsProduct.m_getCategory(); //By Md
        cmsProduct.m_show_form();
        cmsProduct.m_clean();
        $('#account_product_name').focus();
    },
    m_show_tbl: function () {
        $('#swProductTbl').show();
        $('#swProductForm').hide();
        if ($('#swProductTbl').html() == "") {
            cmsProduct.m_refresh();
        }
        cmsProduct.tabSizeTbl();
        $('#refreshProduct').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".insert";
        param += "&" + new jj('#swProductForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".edit";
        param += "&" + new jj('#swProductForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف', 'cmsProduct.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".delete";
        param += "&" + cmsProduct.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    },
    m_copyProduct: function (id) {
        new jj("آیا میخواهید از این محصول کپی بگیرید؟").jjModal_Yes_No('کپی', 'cmsProduct.m_copyProduct_after_question(' + id + ');\n');
    },
    m_copyProduct_after_question: function (id) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".copy";
        param += "&id="+id;
        new jj(param).jjAjax2(false);
    },
    m_select: function (id) {
        $('.summernote').summernote({height: 150, tooltip: false});
        var param = "";
        param += "do=" + cmsProduct.tableName + ".select";
        param += "&" + cmsProduct.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsProduct.m_show_form();
        cmsProduct.m_getCategory(id); //By Md
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".insertTags";
        param += "&" + new jj('#swProductForm').jjSerial();
        param += "&panel=account_product_tags_div";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swProduct2').css('height', 'auto');
        cmsProduct.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swProduct2').css('height', 'auto');
        cmsProduct.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swProduct2').css('height', cmsProduct.heightTab);
    },
    //By Md
    m_getCategory: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".getOptions";
        param += "&panel=" + cmsProduct.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_getGroupUser: function (selector) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".selectOptionGroupUser";
        param += "&panel=" + selector;
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".add_EN";
        param += "&" + cmsProduct.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsProduct.f_parent).jjVal(id);
        new jj("#" + cmsProduct.f_lang).jjVal("2");
        cmsProduct.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".add_ar";
        param += "&" + cmsProduct.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsProduct.f_parent).jjVal(id);
        new jj("#" + cmsProduct.f_lang).jjVal("3");
        cmsProduct.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".add_lang";
        param += "&" + cmsProduct.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + cmsProduct.f_parent).jjVal(id);
        new jj("#" + cmsProduct.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsProduct.m_show_form();
    },
    //<============ BY RASHIDI ========
    //===========================SHAHSANAEI=============================>
    showTopProducts: function (containerId, maxNo, split) {//این تابع عددی از کاربر می گیرد و به همان تعداد جدیدترین کالاها را به او برمی گرداند همچنین این تعداد کالا را به بخش هایی که مد نظر کاربر است تقسیم می کند
        var param = "";
        param += "do=" + cmsProduct.tableName + ".getTopProducts";
        param += "&panel=" + (containerId == null ? "showTopProductsDiv" : containerId);
        param += "&maxNo=" + (maxNo == null ? 10 : maxNo); //حداکثر تعداد کالاهایی که کاربر میخواهد نمایش داده شود
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsProduct.m_show_form();
//        cmsProduct.m_getCategory(id); //By Md
    },
    showdevidedProducts: function (split, page, containerId) {//این تابع کالاهای صفحه پیج که تعداد آن ها برابر اسپلیت است را به کاربر نمایش می دهد.
        var param = "";
        param += "do=" + cmsProduct.tableName + ".showDevidedProducts";
        param += "&panel=" + (containerId == null ? "showDividedProductsDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        param += "&page=" + (page == null ? 0 : page); //این متغیر بیانگر این است که کاربر میخواهد کالاهای کدام صفحه را ببیند
        jj(param).jjAjax2(false);
//        cmsProduct.m_show_form();
//        cmsProduct.m_getCategory(id); //By Md
    },
    calculateNumOfPages: function (containerId, split) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsProduct.tableName + ".calculateNumOfPages";
        param += "&panel=" + (containerId == null ? "pagingDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsProduct.m_show_form();
//        cmsProduct.m_getCategory(id); //By Md
    },
    showAllTags: function (containerId) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsProduct.tableName + ".getAllTags";
        param += "&panel=" + (containerId == null ? "account_product_tag1" : containerId);
        jj(param).jjAjax2(false);
//        cmsProduct.m_show_form();
//        cmsProduct.m_getCategory(id); //By Md
    },
    loadProduct: function (start, end, containerId) {
        var param = "";
        param += "do=" + cmsProduct.tableName + ".loadProduct";
        param += "&panel=" + (containerId == null ? "products" : containerId);
        param += "&start=" + (start == null ? 0 : start);
        param += "&end=" + (end == null ? 5 : end);
        jj(param).jjAjax2(false);
//        cmsProduct.m_show_form();
//        cmsProduct.m_getCategory(id); //By Md
    },
    loadChange: function (containerId) {
//        var param = "";
//        param += "do=" + cmsProduct.tableName + ".numOfProducts";
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
// ----------------------------------------------------------------------------
//By Md
var cmsCategoryProducts = {
    tableName: "Category_Product",
    f_id: "id",
    f_category_product_id: "category_product_id",
    f_parent: "category_product_parent",
    f_upperNode: "category_product_upperNode", //new in v1.5.0
    f_title: "category_product_title",
    f_lang: "category_product_lang",
    loadForm: function () {
        if ($("#swCategoryProductForm").html() == '') {
            $("#swCategoryProductForm").load("formAccount/categoryProduct.html", null, function () {
                cmsCategoryProducts.m_getOptions("categoryProductSelectOption");
            });
        }
    },
    m_disablechilds: function (id) {
        var innerhtml = $("#uperNodeDiv" + id).html().valueOf();
        innerhtml = innerhtml.replace(/>/gi, ">\n");
        innerhtml = innerhtml.replace(/<a.*id=\"uperNodeA.*\">/gi, "");
        innerhtml = innerhtml.replace(/<\/a>/gi, "");
        $("#uperNodeDiv" + id).html(innerhtml);
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCategoryProductTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swCategoryProductTbl').hide();
        $('#swCategoryProductForm').show();
        cmsCategoryProducts.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + cmsCategoryProducts.f_category_product_id).jjVal('');
        new jj("#" + cmsCategoryProducts.f_title).jjVal('');
        new jj("#" + cmsCategoryProducts.f_parent).jjVal('0');
        new jj("#" + cmsCategoryProducts.f_lang).jjVal('1');
        $("#Categoryproduct_Language_button").hide();
    },
    m_add_new: function () {
        new jj("do=" + cmsCategoryProducts.tableName + ".add_new").jjAjax2(false);
        cmsCategoryProducts.m_show_form();
        cmsCategoryProducts.m_clean();
        cmsCategoryProducts.m_getOptions("categoryProductSelectOption");
    },
    m_show_tbl: function () {
        $('#swCategoryProductTbl').show();
        $('#swCategoryProductForm').hide();
        if ($('#swCategoryProductTbl').html() == "") {
            cmsCategoryProducts.m_refresh();
        }
        cmsCategoryProducts.tabSizeTbl();
        $('#refreshCategoryProduct').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".insert";
        param += "&" + new jj('#swCategoryProductForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".edit";
        param += "&" + new jj('#swCategoryProductForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryProducts.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".delete";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".select";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_form();
    },
    m_select_upper_node: function (upper_id) {//new in v 1.5.0
        $("#category_product_upperNode").val("" + upper_id);
        $(".selectedNode").removeClass("selectedNode");
        $("#uperNodeA" + upper_id).addClass("selectedNode");
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".getMenu";
        jj(param).jjAjax2(false);
    },
    m_getOptions: function (selector) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".getSelectOption";
        param += "&panel=" + selector;
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swProductAll').css('height', 'auto');
        cmsCategoryProducts.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swProductAll').css('height', 'auto');
        cmsCategoryProducts.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swProductAll').css('height', cmsCategoryProducts.heightTab);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".add_EN";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryProducts.f_parent).jjVal(id);
        new jj("#" + cmsCategoryProducts.f_lang).jjVal("2");
        cmsCategoryProducts.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".add_Ar";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryProducts.f_parent).jjVal(id);
        new jj("#" + cmsCategoryProducts.f_lang).jjVal("3");
        cmsCategoryProducts.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".add_lang";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryProducts.f_parent).jjVal(id);
        new jj("#" + cmsCategoryProducts.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsCategoryProducts.m_show_form();
    },
    //<============ BY RASHIDI ========
}
