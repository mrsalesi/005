/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisReports = {
    tableName: "",
    f_id: "id",
    loadForm: function () {
        if ($("#swReportsForm").html() == '') {
            $("#swReportsForm").load("formHMIS/04reportPlan.html", null, function () {
                hmisTotalTarget.getOptionForFilter("#swReportsForm #report_hugeGoal");////آوردن استراتژی های موجود به صورت اپشندر جا هایی  که کلاس استزاتژیک تایتل هست
                hmisDepartment.selectOptionDepartment("#report_department");
                hmisReports.m_show_form();
                $("#report_hugeGoal").select2({
                    width: '100%'
                });
                $("#report_minorGoal").select2({
                    width: '100%'
                });
                $("#report_strategic").select2({
                    width: '100%'
                });
                $("#report_department").select2({
                    width: '100%' 
                });
            });
        }
    },
//    m_refresh: function (containerId,sortField,tableHeight) {
//        var param = "";
//        param += "do=" + hmisReports.tableName + ".refresh";
//        param += "&panel=" + (containerId == null ? "swReportsTbl" : containerId);
//        param += "&sort=" + (sortField == null ? "0" : sortField);
//        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
//        param += "&jj=1";
//        new jj(param).jjAjax2(false);
////        himsPlansForAssess.tabSizeTbl();
//    },
    m_show_form: function () {
        $('#swReportsTbl').hide();
        $('#swReportsForm').show();
        hmisReports.tabSizeForm();

    },
    m_clean: function () {
        new jj("#" + hmisReports.f_content_id).jjVal("");
        new jj("#" + hmisReports.f_title).jjVal("");
        new jj("#" + hmisReports.f_lang).jjVal("1");
        new jj("#" + hmisReports.f_parent).jjVal("0");
        new jj("#tags_name").jjVal("");
//        new jj(content_content_editor).jjEditorVal("");
//        $("#Content_Language_button").hide();
    },
    /**
     * نمایش گزارش
     * @returns {undefined}
     */
    report: function () {
//        var flag = true;
//        if ($("#report_department").val() == null || $("#report_department").val() == "") {
//            $("#select2-report_department-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-plans_department-container").removeClass('required');
//        }
//        if ($("#report_strategic").val() == null) {
//            $("#select2-report_strategic-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-report_strategic-container").removeClass('required');
//        }
//        if ($("#report_minorGoal").val() == null) {
//            $("#select2-report_minorGoal-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-report_minorGoal-container").removeClass('required');
//        }
//        if ($("#report_hugeGoal").val() == null) {
//            $("#select2-report_hugeGoal-container").addClass("required");
//            flag = false;
//        } else {
//            $("#select2-report_hugeGoal-container").removeClass('required');
//        }
//        if (flag == true) {
            var param = "";
            param += "&" + new jj("#swReportsForm").jjSerial();
            param += "&do=" + hmisPlans.tableName + ".report";
            new jj(param).jjAjax2(false);
//        }
    },
    m_show_tbl: function () {
        $('#swReportsTbl').show();
        $('#swReportsForm').hide();
        if ($('#swReportsTbl').html() == "") {
            hmisReports.m_refresh();
        }
        hmisReports.tabSizeTbl();
    },
    tabSizeTbl: function () {
        $('#swReports').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swReports').css('height', 'auto');
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
//    var str = $("#" + hmisReports.f_tags).val();
//    var tagName = $("#contetn_tag_span" + deletedTagNo).html().toString();
//    var reg = new RegExp(tagName, "g");
//    str = str.replace(reg, "");
//    $("#" + hmisReports.f_tags).val(str);
//    $("#contetn_tag_span" + deletedTagNo).remove();
//}
//<============ BY RASHIDI ========  