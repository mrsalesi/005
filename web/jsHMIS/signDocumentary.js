/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///ایجاد امضا وتایید مستندات من

var hmisSignDocumentary = {
    tableName: "SignDocumentary",
    f_id: "id",
    loadForm: function () {
//        if ($("#swCreateDocumentaryForm").html() == '') {
        if (true) {
            $("#swSignDocumentaryForm").load("formHMIS/createDocumentary.html", null, function () {
                new jj('#createDocumentary_date').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_CreateDocumentary").button().click(function (e) {

                    hmisSignDocumentary.m_clean();
                    hmisSignDocumentary.m_show_tbl();

                });
               

            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".refreshSignatureMyDocumentation";
        param += "&panel=" + (containerId == null ? "swSignDocumentaryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisSignDocumentary.tabSizeTbl();
    },
  
   
    m_add_new: function () {
        new jj("do=" + hmisSignDocumentary.tableName + ".add_new").jjAjax2(false);
        hmisSignDocumentary.m_show_form();

       


        hmisSignDocumentary.m_clean();


//        hmisSignDocumentary.m_clean();
    },
   
    
    m_show_form: function () {
        $('#swSignDocumentaryTbl').hide();
        $('#swSignDocumentaryForm').show();
        new jj("#swSignDocumentaryForm").jjFormClean();

        hmisSignDocumentary.tabSizeForm();
    },
    m_clean: function () {

        new jj("#swSignDocumentaryForm").jjFormClean();
        $('#createDocumentary_htmlContent').summernote('code', '');
        $("#signatorysAdd").html('');
        $("#signatorys").html('');
        $('#PicPreviewFile3').attr('src', '');
        $('#PicPreviewFile2').attr('src', '');
        $('#PicPreviewFile1').attr('src', '');
        $("#user_file2").html('');
        $("#user_file1").html('');
        $("#user_file3").html('');
        new jj("#createDocumentary_attachmentfile2").jjVal('');
        new jj("#createDocumentary_attachmentfile3").jjVal('');
        new jj("#createDocumentary_attachmentfile1").jjVal('');
    },
    m_show_tbl: function () {
        $('#swSignDocumentaryTbl').show();
        $('#swSignDocumentaryForm').hide();
        if ($('#swSignDocumentaryTbl').html() == "") {
            hmisSignDocumentary.m_refresh();
        }
        hmisSignDocumentary.m_refresh();
        hmisSignDocumentary.tabSizeTbl();

    },
    m_insert: function () {
        var param = "";
        param += "do=" + hmisSignDocumentary.tableName + ".insert";
        param += "&" + new jj("#swSignDocumentaryForm").jjSerial();
        param += "&form-group=" + $(".form-group option:selected").val();

        param += "&createDocumentary_htmlContent=" + $('#createDocumentary_htmlContent').summernote('code');
        new jj(param).jjAjax2(false);
        hmisSignDocumentary.m_show_tbl();
        hmisSignDocumentary.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisSignDocumentary.tableName + ".edit";

        param += "&createDocumentary_htmlContent=" + $('#createDocumentary_htmlContent').summernote('code');
        param += "&" + new jj("#swSignDocumentaryForm").jjSerial();
        param += "&" + new jj("#signatorys").jjSerial();
        param += "&" + new jj("#signatorysAdd").jjSerial();
        new jj(param).jjAjax2(false);
        hmisSignDocumentary.m_show_tbl();
        hmisSignDocumentary.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisSignDocumentary.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisSignDocumentary.tableName + ".delete";
        param += "&" + hmisSignDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSignDocumentary.m_show_tbl();
        hmisSignDocumentary.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisSignDocumentary.tableName + ".select";
        param += "&" + hmisSignDocumentary.f_id + "=" + (id == null ? "" : id);
        $('.summernote').summernote({height: 150,tooltip: false});
        new jj(param).jjAjax2(false);



        hmisSignDocumentary.m_show_form();
        hmisSignDocumentary.m_clean();

    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisSignDocumentary.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swSignDocumentary').css('height', 'auto');
        hmisSignDocumentary.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swSignDocumentary').css('height', 'auto');
        hmisSignDocumentary.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swSignDocumentary').css('height', hmisSignDocumentary.heightTab);
    },
}







