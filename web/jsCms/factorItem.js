var cmsFactorItem = {
    tableName: "FactorItem",
    f_id: "id",
    loadForm: function () {
        if ($("#swFactorItemForm").html() == '') {
            $("#swFactorItemForm").load("formAccount/factorItem.html", null, function () {
                cmsFactorItem.m_getProductFactorItem("product_factor_item_productId");
                $("#product_factor_item_productId").select2({
                    width: '100%'
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsFactorItem.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swFactorItemTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=auto";
        new jj(param).jjAjax2(false);
        cmsFactorItem.tabSizeTbl();
    },
    m_getProductFactorItem: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsFactorItem.tableName + ".getProductFactorItem";
        new jj(param).jjAjax2(false);
    },
    m_getProductPrice: function (id) {
        var param = "";
        param += "id=" + id;
        param += "&do=" + cmsFactorItem.tableName + ".getProductPrice";
        new jj(param).jjAjax2(false);
    },
    m_getProductPriceAfterDiscount: function () {
        var param = "";
        var param1 = "";
        var param2 = "";
        var param3 = "";
        parseFloat()
        param = $("#product_factor_item_originalPrice").val();
        param1 = $("#product_factor_item_discountPercent").val();
        param2 = $("#product_factor_item_valueAdded").val();
        param3 = (param / 100) * (100 - param1);
        $("#product_factor_item_priceAfterDiscount").val((param / 100) * (100 - param1));
        var valueAdedd = (((param / 100) * (100 - param1)) / 100) * $("#product_factor_item_percentageOfValueAdded").val();
        $("#product_factor_item_valueAdded").val((((param / 100) * (100 - param1)) / 100) * $("#product_factor_item_percentageOfValueAdded").val());
        param3 = parseFloat(param3) + parseFloat(valueAdedd);
        $("#product_factor_item_totalPrice").val(param3);
        $("#product_factor_item_discountPercent").val(param1 + ".00");
    },
    m_getProductDiscountPercent: function () {
        var param = "";
        var param1 = "";
        var param2 = "";
        var param3 = "";
        param = $("#product_factor_item_originalPrice").val();
        param1 = $("#product_factor_item_priceAfterDiscount").val();
        param2 = $("#product_factor_item_percentageOfValueAdded").val();
        param3 = parseFloat(param) - parseFloat(param1);
        $("#product_factor_item_discountPercent").val(param3 / (parseFloat(param) / 100));
        $("#product_factor_item_valueAdded").val(((parseFloat(param1) / 100) * (parseFloat(param2))));
        param3 = parseFloat(param1) + parseFloat((param1 / 100) * (param2));
        $("#product_factor_item_totalPrice").val(param3);
    },
    calculateQuantity: function () {
        var param = "";
        var param1 = "";
        var param2 = "";
        var param3 = "";
        var param4 = "";
        if ($("#product_factor_item_quantity").val() == "" || $("#product_factor_item_quantity").val() == 0) {
            new jj("You must enter a number greater than 1").jjModal("Alert");
        } else {
            param = $("#unitPriceFactorItem").val();
            param1 = $("#product_factor_item_priceAfterDiscount").val();
            param2 = $("#product_factor_item_percentageOfValueAdded").val();
            param4 = $("#product_factor_item_discountPercent").val();
            if (param1 == "" || param1 == 0) {
                $("#product_factor_item_valueAdded").val(((parseFloat(param) * ($("#product_factor_item_quantity").val())) / 100) * parseFloat(param2));
                $("#product_factor_item_originalPrice").val((parseFloat(param) * ($("#product_factor_item_quantity").val())));
                param3 = parseFloat(((param * ($("#product_factor_item_quantity").val())) / 100) * param2) + parseFloat(param * ($("#product_factor_item_quantity").val()));
                $("#product_factor_item_totalPrice").val(param3);
            } else {
                $("#product_factor_item_priceAfterDiscount").val(((parseFloat(param) * ($("#product_factor_item_quantity").val())) / 100) * (100 - parseFloat(param4)));
                $("#product_factor_item_originalPrice").val((parseFloat(param) * ($("#product_factor_item_quantity").val())));
                $("#product_factor_item_valueAdded").val(((((parseFloat(param) * ($("#product_factor_item_quantity").val())) / 100) * (100 - parseFloat(param4))) / 100) * (parseFloat(param2)));
                $("#product_factor_item_totalPrice").val((((parseFloat(param) * ($("#product_factor_item_quantity").val())) / 100) * (100 - parseFloat(param4))) + (((((parseFloat(param) * ($("#product_factor_item_quantity").val())) / 100) * (100 - parseFloat(param4))) / 100) * (parseFloat(param2))));
            }
        }
    },
    m_show_form: function () {
        $('#swFactorItemTbl').hide();
        $('#swFactorItemForm').show();
        cmsFactorItem.tabSizeForm();
    },
    m_clean: function () {
        $("#product_factor_item_factorId").val('');
        $("#product_factor_item_discription").val('');
        $("#product_factor_item_totalPrice").val('');
        $("#product_factor_item_valueAdded").val('');
        $("#product_factor_item_percentageOfValueAdded").val('');
        $("#product_factor_item_priceAfterDiscount").val('');
        $("#product_factor_item_discountPercent").val('');
        $("#product_factor_item_originalPrice").val('');
        $("#product_factor_item_quantity").val('');
        $("#product_factor_item_productId").val("0");
        $("#product_factor_item_productId").select2();
    },
    m_add_new: function () {
        cmsFactorItem.m_show_form();
        cmsFactorItem.m_clean();
        new jj("do=" + cmsFactorItem.tableName + ".add_new").jjAjax2(false);
        cmsFactorItem.heightTab = 'auto';
    },
    m_show_tbl: function () {
        $('#swFactorItemTbl').show();
        $('#swFactorItemForm').hide();
        if ($('#swFactorItemTbl').html() == "") {
            cmsFactorItem.m_refresh();
        }
        cmsFactorItem.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsFactorItem.tableName + ".insert";
        param += "&" + new jj('#swFactorItemForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsFactorItem.m_show_tbl();
        cmsFactorItem.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsFactorItem.tableName + ".edit";
        param += "&" + new jj('#swFactorItemForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsFactorItem.m_show_tbl();
        cmsFactorItem.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("حذف ایتم فاکتور", 'cmsFactorItem.m_delete_after_question(' + id + ');\n', true);
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsFactorItem.tableName + ".delete";
        param += "&" + cmsFactorItem.f_id + "=" + (id == null ? "" : id);
        param += "&product_factor_item_factorId=" + $("#product_factor_item_factorId").val();
        ;
        new jj(param).jjAjax2(false);
        cmsFactorItem.m_show_tbl();
        cmsFactorItem.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsFactorItem.tableName + ".select";
        param += "&" + cmsFactorItem.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsFactorItem.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swFactor').css('height', 'auto');
        cmsFactorItem.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swFactor').css('height', 'auto');
        cmsFactorItem.heightTab = 'auto';
    },
    heightTab: "auto",
    mainTabSetSize: function () {
        $('#swFactor').css('height', cmsFactorItem.heightTab);
    }
};

