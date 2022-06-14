var cmsContent = {
    tableName: "Content",
    f_id: "id",
    f_content_id: "content_id",
    f_parent: "content_parent",
    f_content: "content_content",
    f_content_project: "content_title_project", //---shiran2---//
    f_title: "content_title",
    f_lang: "content_lang",
    f_tags: "content_tags",
    loadForm: function () {
        if ($("#swContentForm").html() == '') {
            $("#swContentForm").load("formCms/content.html", null, function () {
                $("#content_title").keyup(function () {
                    $("#content_link").html("Server?do=Content.sw&text=" + $("#content_title").val());// نشان دادن لینک صفحه به کاربر
                    $("#content_titleTag").val($("#content_title").val());// تگ تایتل بصورت پیش فرض همان عنوان مطلب باشد
                });
                new jj('#sendPic1').jjAjaxFileUpload2('content_file', '#content_attachPic', '#Pic_preview');
                new jj("#content_date").jjCalendarWithYearSelector(1397, 1420);
                cmsCategoryContent.getSelectOptionCategoryContent("categoryContentSelectOption");
                cmsContent.m_getGroups("content_privateGroupId");
                $("#content_privateGroupId").select2({
                    width: '100%'
                });
                cmsContent.m_getUser("content_privateUserId");
                $("#content_privateUserId").select2({
                    width: '100%'
                });
                cmsTagsm_getSelectOptionTags("#content_tags");
                $("#content_tags").select2({
                    width: '100%'
                });
                $("#cancel_Content").click(function (e) {
                    cmsContent.m_clean();
                    cmsContent.m_show_tbl();
                });
                cmsContent.m_refresh();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swContentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        new jj(param).jjAjax2(false);
        cmsContent.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swContentTbl').hide();
        $('#swContentForm').show();
        cmsContent.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swContentForm").jjFormClean();
        new jj("#" + cmsContent.f_content_id).jjVal("");
        new jj("#" + cmsContent.f_title).jjVal("");
        new jj("#" + cmsContent.f_content_project).jjVal("");//---shiran2---حذف متن وارد شده هنگام ورود مجدد//
        new jj("#" + cmsContent.f_lang).jjVal("1");
        new jj("#" + cmsContent.f_parent).jjVal("0");

        $("#content_tags_div").val("");
        $("#Pic_preview").attr("src", "img/preview.jpg");
        $("#content_visit_checkbox").attr("checked", "checked");
        $("#content_likes_checkbox").attr("checked", "checked");
        $("#content_disLikes_checkbox").attr("checked", "checked");
        $("#content_disLikes").val("");
        $("#content_likes").val("");
        $("#content_visit").val("");
        $("#tags_name").val("");
        $("#content_ownerId").val("");
        $("#content_link").html("");
        $("#content_tags_div").html("");
        $("#content_priority").val("1");
        $("#content_search_tags_result").val("");
        $('#content_content').summernote('code', '');
    },
    m_add_new: function () {
        $("#newContentCopy .btn").attr("onclick", "");
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsContent.tableName + ".add_new").jjAjax2(false);
        cmsContent.m_show_form();
        cmsContent.m_clean();

    },
    /**
     * 
     * @param {type} selector
     * @returns {undefined}
     */
    m_getGroupsWithUsers: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsGroup.tableName + ".getGroupsWithUsers";
        new jj(param).jjAjax2(false);
    },
//    m_getGroups: function (selector) {
//        var param = "";
//        param += "panel=" + selector;
//        param += "&do=" + cmsContent.tableName + ".getGroups";
//        new jj(param).jjAjax2(false);
//    },
//    m_getUser: function (selector) {
//        var param = "";
//        param += "panel=" + selector;
//        param += "&do=" + cmsContent.tableName + ".getUser";
//        new jj(param).jjAjax2(false);
//    },
    m_show_tbl: function () {
        $('#swContentTbl').show();
        $('#swContentForm').hide();
        $('#refreshContent').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            "iDisplayLength": 25,
            destroy: true
        });
        cmsContent.tabSizeTbl();
    },
    m_insert: function () {
        var valid = cmsContent.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + cmsContent.tableName + ".insert";
            param += "&" + new jj('#swContentForm').jjSerial();
            new jj(param).jjAjax2(false);
            cmsContent.m_show_tbl();
            cmsContent.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    copy: function () {
        var valid = cmsContent.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + cmsContent.tableName + ".copy";
            param += "&content_title=" + $("#newContentTitle").val();
            param += "&id=" + $("#content_id").val();
            new jj(param).jjAjax2(false);
            cmsContent.m_show_tbl();
            cmsContent.m_clean();
        } else {
            new jj(valid).jjModal("پیام سیستم");
        }
    },
    m_edit: function () {
        var valid = cmsContent.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + cmsContent.tableName + ".edit";
            param += "&" + new jj('#swContentForm').jjSerial();
            new jj(param).jjAjax2(false);
            cmsContent.m_show_tbl();
            cmsContent.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_getGroups: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsContent.tableName + ".getGroups";
        new jj(param).jjAjax2(false);
    },
    m_getUser: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsContent.tableName + ".getUser";
        new jj(param).jjAjax2(false);
    },
    m_validation: function () {// mohamdad
        if (new jj("#content_title").jjVal().length < 1) {
            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        }
        return "";
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('حذف محتوا', 'cmsContent.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".delete";
        param += "&" + cmsContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsContent.m_show_tbl();
        cmsContent.m_clean();
    },
    m_select: function (id) {
        $("#newContentCopy .btn").attr("onclick", "cmsContent.copy();");
        var param = "";
        param += "do=" + cmsContent.tableName + ".select";
        param += "&" + cmsContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsContent.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".add_EN";
        param += "&" + cmsContent.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsContent.f_parent).jjVal(id);
        new jj("#" + cmsContent.f_lang).jjVal("2");
        cmsContent.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".add_Ar";
        param += "&" + cmsContent.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + cmsContent.f_parent).jjVal(id);
        new jj("#" + cmsContent.f_lang).jjVal("3");
        cmsContent.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".add_lang";
        param += "&" + cmsContent.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + cmsContent.f_parent).jjVal(id);
        new jj("#" + cmsContent.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsContent.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + cmsContent.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + cmsContent.tableName + ".searchTags";
        param += "&" + new jj('#swContentForm').jjSerial();
        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + cmsContent.tableName + ".insertTags";
        param += "&" + new jj('#swContentForm').jjSerial();
        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swContent').css('height', 'auto');
    },
    tabSizeForm: function () {
        $('#swContent').css('height', 'auto');
    }
//    mainTabSetSize: function () {
////        var aa = $("#swContent").children();
////        var bb = 0;
////        for(i=0; i < aa.length; i++){  
////            if($(aa[i]).css("display")!='none'){
////                bb+= new jj($(aa[i]).css("height")).jjConvertToInt() ;
////            }
////        }
////        if(bb==0){
////            $('#tabs').css('height',572);
////        }else{
////            $('#tabs').css('height',bb+44);
////        }
//    }
};
//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

function deleteContentTag(deletedTagNo) {
    new jj("آیا از حذف این برچسب اطمینان دارید؟").jjDialog_YesNo('afterDeleteContentTag(' + deletedTagNo + ');\n', true, "");
}
function afterDeleteContentTag(deletedTagNo) {
    var str = $("#" + cmsContent.f_tags).val();
    var tagName = $("#contetn_tag_span" + deletedTagNo).html().toString();
    var reg = new RegExp(tagName, "g");
    str = str.replace(reg, "");
    $("#" + cmsContent.f_tags).val(str);
    $("#contetn_tag_span" + deletedTagNo).remove();
}
//<============ BY RASHIDI ========  
// ---------------------------------by mahdi hosseini-------------------------------------------
var cmsCategoryContent = {
    tableName: "Category_Content",
    f_id: "id",
    f_category_content_id: "category_content_id",
    f_parent: "category_content_parent",
    f_title: "category_content_title",
    f_lang: "category_content_lang",
    loadForm: function () {
        if ($("#swCategoryContentForm").html() == '') {
            $("#swCategoryContentForm").load("formCms/categoryContent.html", null, function () {
                new jj('#sendPic1').jjAjaxFileUpload2('content_file', '', '#category_content_pic', '#Pic_preview');
                $("#cancel_CategoryContent").button().click(function (e) {
                    cmsCategoryContent.m_clean();
                    cmsCategoryContent.m_show_tbl();
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCategoryContentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsCategoryContent.tabSizeTbl();
    },
    m_show_form: function () {
        cmsCategoryContent.getSelectOptionCategoryContent("categoryContentSelectOption");
        $('#swCategoryContentTbl').hide();
        $('#swCategoryContentForm').show();
        cmsCategoryContent.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swCategoryContentForm").jjFormClean();
        new jj("#" + cmsCategoryContent.f_parent).jjVal('0');
        new jj("#" + cmsCategoryContent.f_lang).jjVal('1');
        $("#CategoryContent_Language_button").hide();
    },
    m_add_new: function () {
        new jj("do=" + cmsCategoryContent.tableName + ".add_new").jjAjax2(false);
        cmsCategoryContent.m_show_form();
        cmsCategoryContent.m_clean();

    },
    m_show_tbl: function () {
        $('#swCategoryContentTbl').show();
        $('#swCategoryContentForm').hide();
        $('#refreshCategoryContent').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        if ($('#swCategoryContentTbl').html() == "") {
            cmsCategoryContent.m_refresh();
        }
        cmsCategoryContent.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".insert";
        param += "&" + new jj("#swCategoryContentForm").jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryContent.m_show_tbl();
        cmsCategoryContent.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".edit";
        param += "&" + new jj("#swCategoryContentForm").jjSerial();
        new jj(param).jjAjax2(false);
        cmsCategoryContent.m_show_tbl();
        cmsCategoryContent.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryContent.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".delete";
        param += "&" + cmsCategoryContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryContent.m_show_tbl();
        cmsCategoryContent.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".select";
        param += "&" + cmsCategoryContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsCategoryContent.m_show_form();
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    getSelectOption: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + cmsCategoryContent.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    getSelectOptionCategoryContent: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + cmsCategoryContent.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    m_getOptions: function () {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".getOptions";
        new jj(param).jjAjax2(false);
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".add_EN";
        param += "&" + cmsCategoryContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryContent.f_parent).jjVal(id);
        new jj("#" + cmsCategoryContent.f_lang).jjVal("2");
        cmsCategoryContent.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".add_Ar";
        param += "&" + cmsCategoryContent.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryContent.f_parent).jjVal(id);
        new jj("#" + cmsCategoryContent.f_lang).jjVal("3");
        cmsCategoryContent.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + cmsCategoryContent.tableName + ".add_lang";
        param += "&" + cmsCategoryContent.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsCategoryContent.f_parent).jjVal(id);
        new jj("#" + cmsCategoryContent.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        cmsCategoryContent.m_show_form();
    },
    //<============ BY RASHIDI ========
    tabSizeTbl: function () {
        $('#swCategoryContentTbl').css('height', 'auto');
        cmsCategoryContent.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swCategoryContentForm').css('height', 'auto');
        cmsCategoryContent.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swCategoryContent').css('height', cmsCategoryContent.heightTab);
    },

};
