/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisPlansForAssess = {
    tableName: "",
    f_id: "id",

    loadForm: function () {
        if ($("#swPlansForAssessForm").html() == '') {

            $("#swPlansForAssessForm").load("formHMIS/04newAssess.html", null, function () {

                $("#cancel_PlansForAssess").click(function (e) {
                    hmisPlansForAssess.m_clean();
                    hmisPlansForAssess.m_show_tbl();
                });
                new jj('#sendFilesStepsInAssess').jjAjaxFileUploadByTitleAndMultiFile('#attachFilesStepsInAssess', 'steps_files', 'steps_titleFilesInAssess', "#showFilesStepsInAssessDiv");
                new jj("#stepsInAssess_startDate").jjCalendarWithYearSelector(1370, 1420);
                new jj("#stepsInAssess_endDate").jjCalendarWithYearSelector(1370, 1420);
                $("#stepsInAssess_description").summernote({height: 150, tooltip: false});
                $("#stepsFormInAssess").hide();
                //<============ BY RASHIDI ========
                hmisPlansForAssess.m_refresh();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".refreshPlansForAssess";
        param += "&panel=" + (containerId == null ? "swPlansForAssessTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        himsPlansForAssess.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swPlansForAssessTbl').hide();
        $('#swPlansForAssessForm').show();
        hmisPlansForAssess.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisPlansForAssess.f_content_id).jjVal("");

    },
    m_add_new: function () {
        new jj("do=" + hmisPlansForAssess.tableName + ".add_new").jjAjax2(false);
        hmisPlansForAssess.m_show_form();
        hmisPlansForAssess.m_clean();

    },
    m_show_tbl: function () {
        $('#swPlansForAssessTbl').show();
        $('#swPlansForAssessForm').hide();
        $('#refreshPlansForAssess').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
    },
    m_insert: function () {
        var valid = hmisPlansForAssess.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + hmisPlansForAssess.tableName + ".insert";
            param += "&" + new jj('#swPlansForAssessForm').jjSerial();
            new jj(param).jjAjax2(false);
            hmisPlansForAssess.m_show_tbl();
            hmisPlansForAssess.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_edit: function () {

        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".edit";
        param += "&" + new jj('#swPlansForAssessForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisPlansForAssess.m_show_tbl();
        hmisPlansForAssess.m_clean();

    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisPlansForAssess.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".delete";
        param += "&" + hmisPlansForAssess.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPlansForAssess.m_show_tbl();
        hmisPlansForAssess.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisPlans.tableName + ".selectPlansForAssess";
        param += "&" + hmisPlansForAssess.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPlansForAssess.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".add_EN";
        param += "&" + hmisPlansForAssess.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlansForAssess.f_parent).jjVal(id);
        new jj("#" + hmisPlansForAssess.f_lang).jjVal("2");
        hmisPlansForAssess.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".add_Ar";
        param += "&" + hmisPlansForAssess.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlansForAssess.f_parent).jjVal(id);
        new jj("#" + hmisPlansForAssess.f_lang).jjVal("3");
        hmisPlansForAssess.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".add_lang";
        param += "&" + hmisPlansForAssess.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisPlansForAssess.f_parent).jjVal(id);
        new jj("#" + hmisPlansForAssess.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisPlansForAssess.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".searchTags";
        param += "&" + new jj('#swPlansForAssessForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisPlansForAssess.tableName + ".insertTags";
        param += "&" + new jj('#swPlansForAssessForm').jjSerial();
        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swPlansForAssess').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swPlansForAssess').css('height', 'auto');
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
////            $('#tabs').css('height',bb+44);
////        }
//    }
};
//============ BY RASHIDI ========> 
function selectSearchResult(selectedTagNo) {
    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
    $("#content_search_tags_result").hide();
}

//function deleteContentTag(deletedTagNo) {
//    new jj("آیا از حذف این برچسب اطمینان دارید؟").jjDialog_YesNo('afterDeleteContentTag(' + deletedTagNo + ');\n', true, "");
//}
//function afterDeleteContentTag(deletedTagNo) {
//
////    var myString = $("#" + cmsContent.f_tags).val();
////    var oldWord = $("#contetn_tag_span" + deletedTagNo).html().toString();
////    var reg = new RegExp(oldWord, "g");
////    myString = myString.replace(reg, "");
////    alert(myString);
//
//    var str = $("#" + cmsContent.f_tags).val();
//    var tagName = $("#contetn_tag_span" + deletedTagNo).html().toString();
//    var reg = new RegExp(tagName, "g");
//    str = str.replace(reg, "");
//    $("#" + cmsContent.f_tags).val(str);
//    $("#contetn_tag_span" + deletedTagNo).remove();
//}
//<============ BY RASHIDI ========  