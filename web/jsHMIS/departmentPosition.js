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

var hmisDepartmentPositionPosition = {
    tableName: "DepartmentPosition",
    f_id: "id",

    loadForm: function () {
        if ($("#DepartmentPositionForm").html() == '') {
            $("#DepartmentPositionForm").load("formHMIS/departmentPosition.html", null, function () {

//                hmisDepartmentPosition.m_refresh();

               $("#cancel_DepartmentPosition").button().click(function (e) {
//                   alert(1);
                    hmisDepartmentPosition.m_clean();
                    hmisDepartmentPosition.m_show_tbl();
                });
//                $("#sendPicIcon").button().click(function () {
////                       
//                });
//
//                $("#uploaded_fileIcon").button().click(function () {
//                });
//                new jj('#sendPicIcon').jjAjaxFileUpload('#uploaded_fileIcon', '#department_Icon');
//
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "DepartmentPositionTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        jj(param).jjAjax2(false);
        hmisDepartmentPosition.tabSizeTbl();
    },
    bazgasht:function(){
       hmisDepartmentPosition.m_clean();
       hmisDepartmentPosition.m_show_tbl();  
    },
    m_add_new: function () {
        jj("do=" + hmisDepartmentPosition.tableName + ".add_new").jjAjax2(false);
        hmisDepartmentPosition.m_show_form();
        hmisDepartmentPosition.m_clean();
        $('.summernote').summernote({height: 150,tooltip: false});///برای تبدیل شدن به textEditor
        
        //
//        part_content_editor = new jj('#department_publicContent').jjEditor();
//        part_praivate_editor = new jj('#department_praivateContent').jjEditor();
//        cmsUser.m_getGroups();
    },
    m_show_form: function () {
        $('#DepartmentPositionTbl').hide();
        $('#DepartmentPositionForm').show();
        hmisDepartmentPosition.tabSizeForm();
    },
    m_clean: function () {
        new jj("#DepartmentPositionForm").jjFormClean();

    },
    m_show_tbl: function () {
        $('#DepartmentPositionTbl').show();
        $('#DepartmentPositionForm').hide();
        if ($('#DepartmentPositionTbl').html() == "") {
            hmisDepartmentPosition.m_refresh();
        }
        hmisDepartmentPosition.m_refresh();
        hmisDepartmentPosition.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".insert";
        param += "&" + new jj("#DepartmentPositionForm").jjSerial();
       param += $('#department_publicContent').summernote('code');
       param += $('#department_praivateContent').summernote('code');
          param += "&jj=1";
        jj(param).jjAjax2(false);
        hmisDepartmentPosition.m_show_tbl();
        hmisDepartmentPosition.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".edit";
         param += $('#department_publicContent').summernote('code');
       param += $('#department_praivateContent').summernote('code');
        param += "&" + new jj("#DepartmentPositionForm").jjSerial();
        jj(param).jjAjax2(false);
        hmisDepartmentPosition.m_show_tbl();
        hmisDepartmentPosition.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisDepartmentPosition.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".delete";
        param += "&" + hmisDepartmentPosition.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        hmisDepartmentPosition.m_show_tbl();
        hmisDepartmentPosition.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".select";
        param += "&" + hmisDepartmentPosition.f_id + "=" + (id == null ? "" : id);
        $('.summernote').summernote({height: 150,tooltip: false});
        jj(param).jjAjax2(false);



        hmisDepartmentPosition.m_show_form();
//        part_content_editor = new jj('#department_publicContent').jjEditor();
//         part_praivate_editor = new jj('#department_praivateContent').jjEditor();

    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisDepartmentPosition.tableName + ".getMenu";
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#DepartmentPosition').css('height', 'auto');
        hmisDepartmentPosition.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#DepartmentPosition').css('height', 'auto');
        hmisDepartmentPosition.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#DepartmentPosition').css('height', hmisDepartmentPosition.heightTab);
    },
    ////این تابع برای دانلود آیکون در قسمت دپارتمان طراحی شده
//    downloadIcon:function(){
//        $('#downloadIcon').attr('href', 'upload/' + $('#uploaded_file').val());
//    },
}



