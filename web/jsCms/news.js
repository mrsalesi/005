var cmsNews = {
    tableName: "News",
    f_id: "id",
    f_news_id: "news_id",
    f_parent: "news_parent",
    f_content: "news_content",
    f_title: "news_title",
    f_date: "news_date",
    f_priority: "news_priority",
    f_category_id: "news_category_id",
    f_lang: "news_lang",
    f_category_id_select: "news_category_id_select",
    loadForm: function () {
        if ($("#swNewsForm").html() == '') {
            $("#swNewsForm").load("formCms/news.html", null, function () {
                $("#cancel_News").button().click(function (e) {
                    cmsNews.m_clean();
                    cmsNews.m_show_tbl();
                });
                new jj('#newsSendPic1').jjAjaxFileUpload2('news_file', '#news_attachPic', '#news_Pic_preview');
                new jj("#news_date").jjCalendarWithYearSelector(1397, 1420);
                $('#news_pic1').keyup(function () {
                    $('#news_Pic_preview').attr('src', 'upload/' + $('#news_pic1').val());
                    if ($('#news_pic1').val() == '') {
                        $('#news_Pic_preview').attr('src', 'img/preview.jpg');
                    }
                });
                cmsCategoryNews.getSelectOption("#swNewsForm .newsSelectOption");
                $(".newsSelectOption").select2({
                    width: '100%'
                });
                cmsNews.m_getGroups("news_privateGroupId");
                $("#news_privateGroupId").select2({
                    width: '100%'
                });
                cmsNews.m_getUser("news_privateUserId");
                $("#news_privateUserId").select2({
                    width: '100%'
                });
            });
        }
    },
    m_getGroups: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsNews.tableName + ".getGroups";
        new jj(param).jjAjax2(false);
    },
    m_getUser: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsNews.tableName + ".getUser";
        new jj(param).jjAjax2(false);
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsNews.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swNewsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsNews.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swNewsTbl').hide();
        $('#swNewsForm').show();
        $(".newsSelectOption").select2({
            width: '100%'
        });
        cmsNews.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swNewsForm").jjFormClean();
        new jj("#" + cmsNews.f_lang).jjVal("1");
        new jj("#" + cmsNews.f_parent).jjVal("0");
        new jj("#news_visit").jjVal("0");

        $("#news_pic_preview").attr('src', "img/news.png");

        $("#news_visit").removeAttr("disabled");
        new jj("#news_visit_checkbox").jjVal("1");

        new jj("#news_likes").jjVal("0");
        $("#news_likes").removeAttr("disabled");
        new jj("#news_likes_checkbox").jjVal("1");

        new jj("#news_disLikes").jjVal("0");
        $("#news_disLikes").removeAttr("disabled");
        new jj("#news_disLikes_checkbox").jjVal("1");
        $("#News_Language_button").hide();
        $('#news_content').summernote('code', '');
    },
    m_add_new: function () {
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsNews.tableName + ".add_new").jjAjax2(false);
        cmsNews.m_getCategory();
        cmsNews.m_show_form();
        cmsNews.m_clean();
    },
    m_show_tbl: function () {
        $('#swNewsTbl').show();
        $('#swNewsForm').hide();
        cmsNews.m_refresh();
        $('#refreshNews').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        if ($('#swNewsTbl').html() == "") {
        }
        cmsNews.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsNews.tableName + ".insert";
        param += "&" + new jj('#swNewsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsNews.tableName + ".edit";
        param += "&" + new jj('#swNewsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'cmsNews.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsNews.tableName + ".delete";
        param += "&" + cmsNews.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsNews.tableName + ".select";
        param += "&" + cmsNews.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsNews.m_show_form();
    },
    m_getOneNews: function (id, temp) {
        var param = "";
        param += "do=" + cmsNews.tableName + ".getOneNews";
        param += "&" + cmsNews.f_id + "=" + (id == null ? "" : id);
        param += "&temp=" + (temp == null ? "" : temp);
        new jj(param).jjAjax2(false);
    },
    m_getSlider: function (panel) {
        var param = "";
        param += "do=" + cmsNews.tableName + ".getSlider";
        param += "&panel=" + (panel == null ? "newsSlide" : panel);
        new jj(param).jjAjax2(false);
    },
    m_getCategory: function (id) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".getOptions";
        param += "&panel=" + cmsNews.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swNewsAll').css('height', 519);
        cmsNews.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swNewsAll').css('height', 463);
        cmsNews.heightTab = 463;
    },
    heightTab: "519",
    mainTabSetSize: function () {
        $('#swNewsAll').css('height', cmsNews.heightTab);
    }
}
// ----------------------------------------------------------------------------
var cmsCategoryNews = {
    tableName: "Category_News",
    f_id: "id",
    f_category_news_id: "category_news_id",
    f_parent: "category_news_parent",
    f_upperNode: "category_news_upperNode", //new in v1.5.0
    f_title: "category_news_title",
    f_lang: "category_news_lang",
    loadForm: function () {
        if ($("#swCategoryNewsForm").html() == '') {
            $("#swCategoryNewsForm").load("formCms/categoryNews.html", null, function () {
                $("#cancel_CategoryNews").button().click(function (e) {
                    cmsCategoryNews.m_clean();
                    cmsCategoryNews.m_show_tbl();
                });
                cmsCategoryNews.m_categoryNewsSelectOption("#swCategoryNewsForm .categoryNewsSelectOption");

            });
        }
    },
    /**
     * by mohammad==>>>>>>>>>>>>>>>>
     * only work for div with id like this:"#uperNodeDiv"+id,<br> this function replace whit ""(blank) all "a" tag in which id is ("uperNodeA+?") 
     * @
     * @        
     * @new in v1.5.0 Iransepano
     */
    m_disablechilds: function (id) {
        var innerhtml = $("#uperNodeDiv" + id).html().valueOf();
        innerhtml = innerhtml.replace(/>/gi, ">\n");
        innerhtml = innerhtml.replace(/<a.*id=\"uperNodeA.*\">/gi, "");
        innerhtml = innerhtml.replace(/<\/a>/gi, "");
        $("#uperNodeDiv" + id).html(innerhtml);
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCategoryNewsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsCategoryNews.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swCategoryNewsTbl').hide();
        $('#swCategoryNewsForm').show();
//        cmsCategoryNews.m_categoryNewsSelectOption("#swCategoryNewsForm .categoryNewsSelectOption");
        cmsCategoryNews.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + cmsCategoryNews.f_category_news_id).jjVal('');
        new jj("#" + cmsCategoryNews.f_title).jjVal('');
        new jj("#" + cmsCategoryNews.f_parent).jjVal('0');
        new jj("#" + cmsCategoryNews.f_lang).jjVal('1');
        new jj("#" + cmsCategoryNews.f_upperNode).jjVal('0');//new in v 1.5.0
        $("#CategoryNews_Language_button").hide();

    },
    m_add_new: function () {

        new jj("do=" + cmsCategoryNews.tableName + ".add_new").jjAjax2(false);
        cmsCategoryNews.m_show_form();
        cmsCategoryNews.m_clean();

    },
    m_show_tbl: function () {
        $('#swCategoryNewsTbl').show();
        $('#swCategoryNewsForm').hide();

        if ($('#swCategoryNewsTbl').html() == "") {
            cmsCategoryNews.m_refresh();
        }
        $('#refreshCategoryNews').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        cmsCategoryNews.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".insert";
        param += "&" + new jj('#swCategoryNewsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".edit";
        param += "&" + new jj('#swCategoryNewsForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'cmsCategoryNews.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".delete";
        param += "&" + cmsCategoryNews.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".select";
        param += "&" + cmsCategoryNews.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_form();
    },
    m_select_upper_node: function (upper_id) {//new in v 1.5.0
        $("#category_news_upperNode").val("" + upper_id);
        $(".selectedNode").removeClass("selectedNode");
        $("#uperNodeA" + upper_id).addClass("selectedNode");
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    getSelectOption: function (panel) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".getSelectOption";
        param += "&panel=" + panel;
        new jj(param).jjAjax2(false);
    },
    m_categoryNewsSelectOption: function (panel) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".categoryNewsSelectOption";
        param += "&panel=" + panel;
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swNewsAll').css('height', 519);
        cmsCategoryNews.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swNewsAll').css('height', 215);
        cmsCategoryNews.heightTab = 215;
    },
    heightTab: "519",
    mainTabSetSize: function () {
        $('#swNewsAll').css('height', cmsCategoryNews.heightTab);
    }
};