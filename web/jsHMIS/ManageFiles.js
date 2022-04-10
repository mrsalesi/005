var hmisManageFiles = {
    tableName: "ManageFiles",
    loadForm: function () {
        if ($("#swContentForm").html() == '') {
            $("#swContentForm").load("formHmis/manageFiles.html", null, function () {
                $("#content_title").keyup(function () {
                    $("#content_link").html("Server?do=Content.sw&text=" + $("#content_title").val());// نشان دادن لینک صفحه به کاربر
                    $("#content_titleTag").val($("#content_title").val());// تگ تایتل بصورت پیش فرض همان عنوان مطلب باشد
                });
                new jj('#sendPic1').jjAjaxFileUpload2('content_file', '#content_attachPic', '#Pic_preview');
                new jj("#content_date").jjCalendarWithYearSelector(1397, 1420);
                $('#content_pic1').keyup(function () {
                    $('#Pic_preview').attr('src', 'upload/' + $('#content_pic1').val());
                    if ($('#content_pic1').val() == '') {
                        $('#Pic_preview').attr('src', 'img/preview.jpg');
                    }
                });
                $("#cancel_Content").click(function (e) {
                    hmisManageFiles.m_clean();
                    hmisManageFiles.m_show_tbl();
                });
                hmisManageFiles.m_refresh();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swContentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        hmisManageFiles.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swContentTbl').hide();
        $('#swContentForm').show();
        hmisManageFiles.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swContentForm").jjFormClean();
        new jj("#" + hmisManageFiles.f_content_id).jjVal("");
        new jj("#" + hmisManageFiles.f_title).jjVal("");
        new jj("#" + hmisManageFiles.f_content_project).jjVal("");//---shiran2---حذف متن وارد شده هنگام ورود مجدد//
        new jj("#" + hmisManageFiles.f_lang).jjVal("1");
        new jj("#" + hmisManageFiles.f_parent).jjVal("0");
        
        $("#content_tags_div").val("");
        $("#Pic_preview").attr("src","img/preview.jpg");
        $("#content_visit_checkbox").attr("checked","checked");
        $("#content_likes_checkbox").attr("checked","checked");
        $("#content_disLikes_checkbox").attr("checked","checked");
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
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + hmisManageFiles.tableName + ".add_new").jjAjax2(false);
        hmisManageFiles.m_show_form();
        hmisManageFiles.m_clean();
        
    },
    m_show_tbl: function () {
        $('#swContentTbl').show();
        $('#swContentForm').hide();
        $('#refreshContent').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
        if ($('#swContentTbl').html() == "") {
            hmisManageFiles.m_refresh();
        }
        hmisManageFiles.tabSizeTbl();
    },
    m_insert: function () {
        var valid = hmisManageFiles.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + hmisManageFiles.tableName + ".insert";
            param += "&" + new jj('#swContentForm').jjSerial();
            param += "&content_content=" + $('#content_content').summernote('code');
            new jj(param).jjAjax2(false);
            hmisManageFiles.m_show_tbl();
            hmisManageFiles.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_edit: function () {
        var valid = hmisManageFiles.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + hmisManageFiles.tableName + ".edit";
            param += "&" + new jj('#swContentForm').jjSerial();
            param += "&content_content=" + $('#content_content').summernote('code');
            new jj(param).jjAjax2(false);
            hmisManageFiles.m_show_tbl();
            hmisManageFiles.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_validation: function () {// mohamdad
        if (new jj("#content_title").jjVal().length < 1) {
            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        }
        return "";
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisManageFiles.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".delete";
        param += "&" + hmisManageFiles.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisManageFiles.m_show_tbl();
        hmisManageFiles.m_clean();
    },
    m_select: function (id) {
        var param = "";
        $('.summernote').summernote();
        param += "do=" + hmisManageFiles.tableName + ".select";
        param += "&" + hmisManageFiles.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisManageFiles.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".add_EN";
        param += "&" + hmisManageFiles.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + hmisManageFiles.f_parent).jjVal(id);
        new jj("#" + hmisManageFiles.f_lang).jjVal("2");
        hmisManageFiles.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".add_Ar";
        param += "&" + hmisManageFiles.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + hmisManageFiles.f_parent).jjVal(id);
        new jj("#" + hmisManageFiles.f_lang).jjVal("3");
        hmisManageFiles.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".add_lang";
        param += "&" + hmisManageFiles.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        jj(param).jjAjax2(false);
        new jj("#" + hmisManageFiles.f_parent).jjVal(id);
        new jj("#" + hmisManageFiles.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisManageFiles.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".searchTags";
        param += "&" + new jj('#swContentForm').jjSerial();
        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisManageFiles.tableName + ".insertTags";
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
////            $('#tabs').css('height','auto');
////        }else{
////            $('#tabs').css('height','auto');
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
    var str = $("#" + hmisManageFiles.f_tags).val();
    var tagName = $("#contetn_tag_span" + deletedTagNo).html().toString();
    var reg = new RegExp(tagName, "g");
    str = str.replace(reg, "");
    $("#" + hmisManageFiles.f_tags).val(str);
    $("#contetn_tag_span" + deletedTagNo).remove();
}
//<============ BY RASHIDI ========  

