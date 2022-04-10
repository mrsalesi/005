var cmsJournal = {
    tableName: "Journal",
    f_id: "id",
    f_title: "product_title",
    //By Md
    f_category_id_select: "account_product_category_select",
    f_priority: "account_product_priority",
    f_parent: "account_product_parent",
    f_date: "account_product_date",
    f_lang: "account_product_lang",
    loadForm: function () {
        if ($("#swJournalForm").html() == '') {
            $("#swJournalForm").load("formAccount/journal.html", null, function () {
                new jj("#account_product_date").jjCalendarWithYearSelector(1397, 1420);
                new jj("#account_product_discount_Date").jjCalendarWithYearSelector(1397, 1420);
                cmsJournal.m_getGroupUser("selectOptionGroupUserProduct");
                cmsJournal.m_getOptions("categoryProductSelectOption");
                cmsTagsm_getSelectOptionTags("#account_product_tags");
                $("#account_product_tags").select2({
                    width: '100%'
                });
                new jj("#account_product_endDate").jjCalendarWithYearSelector(1397, 1420);
                $('#account_product_endTime').wickedpicker({
                    twentyFour: true
                });
                new jj("#account_product_startDate").jjCalendarWithYearSelector(1397, 1420);
                $('#account_product_startTime').wickedpicker({
                    twentyFour: true
                });
                //////////////////////////picture
                new jj('#account_product_sendPic1').jjAjaxFileUpload2('account_product_file1', '', '#jornal_file', '#account_product_preview1');
                new jj('#account_product_sendPic2').jjAjaxFileUpload2('account_product_file2', '', '#jornal_pic', '#account_product_preview2');
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
        param += "do=" + cmsJournal.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swJournalTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsJournal.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swJournalTbl').hide();
        $('#swJournalForm').show();
        cmsJournal.tabSizeForm();
    },
    m_clean: function () {
        new jj("#account_products_active").jjVal("1");
        new jj("#jornal_title").jjVal("");
        new jj("#jornal_parent").jjVal("");
        new jj("#jornal_file").jjVal("");
        new jj("#jornal_date").jjVal("");
        new jj("#jornal_status").jjVal("");
        new jj("#jornal_pic").jjVal("");
        new jj("#jornal_Issue").jjVal("");
        $("#account_product_preview1").attr("src", "img/preview.jpg");
        $("#account_product_preview2").attr("src", "img/preview.jpg");
        $("#account_product_preview3").attr("src", "img/preview.jpg");
        $("#account_product_preview4").attr("src", "img/preview.jpg");
        $("#account_product_preview5").attr("src", "img/preview.jpg");
        $("#account_product_preview6").attr("src", "img/preview.jpg");
        new jj("#jornal_DateRelease").jjVal("");
        new jj("#jornal_ReleaseNumber").jjVal("");
        new jj("#jornal_Concessionaire").jjVal("");
        new jj("#jornal_Responsible").jjVal("");
        new jj("#jornal_chiefEditor").jjVal("");
        new jj("#jornal_visit").jjVal("");
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
        new jj("#" + cmsJournal.f_priority).jjVal("1");
        new jj("#" + cmsJournal.f_parent).jjVal("0");
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
        new jj("#" + cmsJournal.f_lang).jjVal("1");
    },
    m_add_new: function () {
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsJournal.tableName + ".add_new").jjAjax2(false);
//        cmsJournal.m_getCategory(); //By Md
        cmsJournal.m_show_form();
        cmsJournal.m_clean();
        $('#account_product_name').focus();
    },
    m_show_tbl: function () {
        alert("5");
        $('#swJournalTbl').show();
        $('#swJournalForm').hide();
        if ($('#swJournalTbl').html() == "") {
            cmsJournal.m_refresh();
        }
        cmsJournal.tabSizeTbl();
        $('#refreshProduct').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".insert";
        param += "&" + new jj('#swJournalForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_tbl();
        cmsJournal.m_clean();
//        cmsJournal.m_getCategory(id); //By Md
    },
        m_ok: function (id) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".LikeJournal";
        param += "&id=" + id;
//        param += "&lessons_attachFile2=" + temp1;
        param += "&" + new jj('#swJournalForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_tbl();
        cmsJournal.m_clean();
    },
    m_nok: function (id) {
        var attachFilesMulti = $("#lessons_divUpload .lessons_attachFile");
        var attachFilesMulti2 = $("#lessons_divUpload2 .lessons_attachFile");
        var temp = "";
        var temp1 = "";
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        for (var i = 0; i < attachFilesMulti2.length; i++) {
            temp1 += $(attachFilesMulti2[i]).val() + ",";
        }
        var param = "";
        param += "do=" + cmsJournal.tableName + ".disLikeJournal";
        param += "&id=" + id;
        param += "&lessons_attachFile2=" + temp1;
        param += "&" + new jj('#swJournalForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_tbl();
        cmsJournal.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".edit";
        param += "&" + new jj('#swJournalForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_tbl();
        cmsJournal.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف', 'cmsJournal.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".delete";
        param += "&" + cmsJournal.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_tbl();
        cmsJournal.m_clean();
    },
    m_copyProduct: function (id) {
        new jj("آیا میخواهید از این محصول کپی بگیرید؟").jjModal_Yes_No('کپی', 'cmsJournal.m_copyProduct_after_question(' + id + ');\n');
    },
    m_copyProduct_after_question: function (id) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".copy";
        param += "&id=" + id;
        new jj(param).jjAjax2(false);
    },
    m_select: function (id) {
        $('.summernote').summernote({height: 150, tooltip: false});
        var param = "";
        param += "do=" + cmsJournal.tableName + ".select";
        param += "&" + cmsJournal.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsJournal.m_show_form();
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".insertTags";
        param += "&" + new jj('#swJournalForm').jjSerial();
        param += "&panel=account_product_tags_div";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swProduct2').css('height', 'auto');
        cmsJournal.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swProduct2').css('height', 'auto');
        cmsJournal.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swProduct2').css('height', cmsJournal.heightTab);
    },
    //By Md
    m_getCategory: function (id) {
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".getOptions";
        param += "&panel=" + cmsJournal.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_getGroupUser: function (selector) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".selectOptionGroupUser";
        param += "&panel=" + selector;
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".add_EN";
        param += "&" + cmsJournal.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsJournal.f_parent).jjVal(id);
        new jj("#" + cmsJournal.f_lang).jjVal("2");
        cmsJournal.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".add_ar";
        param += "&" + cmsJournal.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsJournal.f_parent).jjVal(id);
        new jj("#" + cmsJournal.f_lang).jjVal("3");
        cmsJournal.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".add_lang";
        param += "&" + cmsJournal.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + cmsJournal.f_parent).jjVal(id);
        new jj("#" + cmsJournal.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsJournal.m_show_form();
    },
    //<============ BY RASHIDI ========
    //===========================SHAHSANAEI=============================>
    showTopProducts: function (containerId, maxNo, split) {//این تابع عددی از کاربر می گیرد و به همان تعداد جدیدترین کالاها را به او برمی گرداند همچنین این تعداد کالا را به بخش هایی که مد نظر کاربر است تقسیم می کند
        var param = "";
        param += "do=" + cmsJournal.tableName + ".getTopProducts";
        param += "&panel=" + (containerId == null ? "showTopProductsDiv" : containerId);
        param += "&maxNo=" + (maxNo == null ? 10 : maxNo); //حداکثر تعداد کالاهایی که کاربر میخواهد نمایش داده شود
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsJournal.m_show_form();
//        cmsJournal.m_getCategory(id); //By Md
    },
    showdevidedProducts: function (split, page, containerId) {//این تابع کالاهای صفحه پیج که تعداد آن ها برابر اسپلیت است را به کاربر نمایش می دهد.
        var param = "";
        param += "do=" + cmsJournal.tableName + ".showDevidedProducts";
        param += "&panel=" + (containerId == null ? "showDividedProductsDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        param += "&page=" + (page == null ? 0 : page); //این متغیر بیانگر این است که کاربر میخواهد کالاهای کدام صفحه را ببیند
        jj(param).jjAjax2(false);
//        cmsJournal.m_show_form();
//        cmsJournal.m_getCategory(id); //By Md
    },
    calculateNumOfPages: function (containerId, split) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsJournal.tableName + ".calculateNumOfPages";
        param += "&panel=" + (containerId == null ? "pagingDiv" : containerId);
        param += "&split=" + (split == null ? 10 : split); //این متغیر بیانگر این است که کاربر میخواهد کالاها را چندتا چندتا ببیند
        jj(param).jjAjax2(false);
//        cmsJournal.m_show_form();
//        cmsJournal.m_getCategory(id); //By Md
    },
    showAllTags: function (containerId) {//این تابع تعداد صفحات مورد نیاز برای نمایش همه کالاها را با توجه به تعداد کالایی که کاربر میخواهد ببیند محاسبه می کند.
        var param = "";
        param += "do=" + cmsJournal.tableName + ".getAllTags";
        param += "&panel=" + (containerId == null ? "account_product_tag1" : containerId);
        jj(param).jjAjax2(false);
//        cmsJournal.m_show_form();
//        cmsJournal.m_getCategory(id); //By Md
    },
    loadProduct: function (start, end, containerId) {
        var param = "";
        param += "do=" + cmsJournal.tableName + ".loadProduct";
        param += "&panel=" + (containerId == null ? "products" : containerId);
        param += "&start=" + (start == null ? 0 : start);
        param += "&end=" + (end == null ? 5 : end);
        jj(param).jjAjax2(false);
//        cmsJournal.m_show_form();
//        cmsJournal.m_getCategory(id); //By Md
    },
    loadChange: function (containerId) {
//        var param = "";
//        param += "do=" + cmsJournal.tableName + ".numOfProducts";
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
            $("#swCategoryProductForm").load("formCms/categoryProduct.html", null, function () {
                cmsCategoryProducts.m_getOptions("categoryProductSelectOption");

                $('#category_product_picIcon').keyup(function () {
                    $('#PicPreviewLessonsIcon').attr('src', 'upload/' + $('#category_product_picIcon').val());
                    if ($('#category_product_picIcon').val() == '') {
                        $('#PicPreviewLessonsIcon').attr('src', 'las_settings/img/preview.jpg');
                    }
                });
                new jj('#sendPicLessons').jjAjaxFileUpload2('category_product_file_icon', '#category_product_picIcon', '#PicPreviewLessonsIcon');
                new jj('#sendPicLessons2').jjAjaxFileUpload2('category_product_file_icon2', '#category_product_picIcon2', '#PicPreviewLessonsIcon2');
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
        var attachFilesMulti = $("#lessons_divUpload .lessons_attachFile");
        var attachFilesMulti2 = $("#lessons_divUpload2 .lessons_attachFile");
        var temp = "";
        var temp1 = "";
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        for (var i = 0; i < attachFilesMulti2.length; i++) {
            temp1 += $(attachFilesMulti2[i]).val() + ",";
        }
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".insert";
        param += "&lessons_attachFile=" + temp;
        param += "&lessons_attachFile2=" + temp1;
        param += "&" + new jj('#swCategoryProductForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    },

    m_edit: function () {
        var attachFilesMulti = $("#lessons_divUpload .lessons_attachFile");
        var attachFilesMulti2 = $("#lessons_divUpload2 .lessons_attachFile");
        var temp = "";
        var temp1 = "";
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        for (var i = 0; i < attachFilesMulti2.length; i++) {
            temp1 += $(attachFilesMulti2[i]).val() + ",";
        }
        var param = "";
        param += "do=" + cmsCategoryProducts.tableName + ".edit";
        param += "&lessons_attachFile=" + temp;
        param += "&lessons_attachFile2=" + temp1;
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
