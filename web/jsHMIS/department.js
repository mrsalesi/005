/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var hmisDepartment = {
    tableName: "Department",
    f_id: "id",
    loadForm: function () {
        if ($("#swDepartmentForm").html() == '') {
            $("#swDepartmentForm").load("formHMIS/department.html", null, function () {
                $("#cancel_Department").on('click', function (e) {
                    hmisDepartment.m_clean();
                    hmisDepartment.m_show_tbl();
                });
                new jj('#sendPicIcon').jjAjaxFileUpload2('upload_fileIcon', '#department_icon', '#PicPreviewIcon');
                $('#departmentIcon').keyup(function () {
                    $('#PicPreviewIcon').attr('src', 'upload/' + $('#departmentIcon').val());
                    if ($('#departmentIcon').val() == '') {
                        $('#PicPreviewIcon').attr('src', 'img/preview.jpg');
                    }
                });
                hmisDepartment.selectOptionDepartment("#department_location");
                hmisRole.getSelectOptionRequierd("#department_role_id");
                cmsUser.getSelectOptionNotAll("#department_user_id");
                $("#department_role_id").select2({
                    minimumResultsForSearch: '',
                    width: '100%'
                });
                $("#department_user_id").select2({
                    minimumResultsForSearch: '',
                    width: '100%'
                });
            });
        }
    },
    //**این تابع برای ست شدن زیر شاخه ها در تکست اریا

    setGroupName: function (id, parentId) {
        var param = "";
        param += "&id=" + id;
        param += "&parentId=" + parentId;
        param += "&do=" + cmsLocation.tableName + ".setGroupName";

        new jj(param).jjAjax2(false);

    },
    setParent: function (id) {

        if ($(".i" + id).hasClass('fa-plus')) {
            $(".ol" + id).show();
            $(".i" + id).addClass('fa-minus');
            $(".i" + id).removeClass('fa-plus');
        } else {
            $(".ol" + id).hide();
            $(".i" + id).addClass('fa-plus');
            $(".i" + id).removeClass('fa-minus');
        }
    },
    /**
     *با کلیک بر روی این تابع دانشکده ای که زیر مجموعه دارند 
     *اگر مثبت داشته باشد کلاس ان رابرمی دارد وزیر مجوعه های ان را هاید می کند
     *اگر منفی داشته باشد زیرر مجموعه هایش شو می شود .
     *ونمایش داده می شود
     * @param {type} id
     * @returns {undefined}
     */
    addClassParent: function (id) {

        if ($(".span" + id).hasClass("fa-plus")) {
            $(".span" + id).removeClass("fa-plus");
            $(".span" + id).addClass('fa-minus');
            $(".ol" + id + '.a').show();
        } else if ($(".span" + id).hasClass("fa-minus")) {

            $(".span" + id).addClass('fa-plus');
            $(".span" + id).removeClass('fa-minus');
            $(".ol" + id + '.a').hide();

        }


    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDepartment.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swDepartmentTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDepartment.tabSizeTbl();
    },
    bazgasht: function () {
        hmisDepartment.m_clean();
        hmisDepartment.m_show_tbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisDepartment.tableName + ".add_new").jjAjax2(false);
        $('.summernote').summernote({height: 150, tooltip: false});///برای تبدیل شدن به textEditor
        $('#department_location').val("null").trigger('change');
        $("#IconDownload").hide();
        hmisDepartment.m_show_form();
        hmisDepartment.m_clean();
    },
    m_show_form: function () {
        $('#swDepartmentTbl').hide();
        $('#swDepartmentForm').show();
        if ($('#swDepartmentTbl').html() == "") {
            hmisDepartment.m_refresh();
        }
        hmisDepartment.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swDepartmentForm").jjFormClean();
        $("#department_user_id").val('');
        $("#department_user_id").select2({
            minimumResultsForSearch: '',
            width: '100%'
        });
        $("#department_role_id").val('');
        $("#department_role_id").select2({
            minimumResultsForSearch: '',
            width: '100%'
        });
        $("#departmentIcon").html('');
        $('#PicPreviewIcon').attr('src', '');
        $("#selectHospital").val('');
        $('#department_publicContent').summernote('code', '');
        $('#department_praivateContent').summernote('code', '');
    },
    m_show_tbl: function () {
        $('#swDepartmentTbl').show();
        $('#swDepartmentForm').hide();
        if ($('#swDepartmentTbl').html() == "") {
            hmisDepartment.m_refresh();
        }
        hmisDepartment.m_refresh();
        hmisDepartment.tabSizeTbl();
    },
    /**
     * با انتخاب بخش کاربران و نقش های بخش را نمایش می دهد 
     * در دو پنل باید قرار بگیرد 
     * پنل کاربر 
     * پنل نقش
     * @returns {undefined}
     */
    getUserAndRoleInDepatrment: function (id, userPanel) {
        var param = "";
        param += "&userPanel=" + userPanel;
        param += "&id=" + id;
        param += "&do=" + hmisDepartment.tableName + ".getUserAndRoleInDepatrment";
        new jj(param).jjAjax2(false);
    },
    getUserAndRoleInGroup: function (id, userPanel) {
        var param = "";
        param += "&userPanel=" + userPanel;
        param += "&educationClass_registrants_groupId=" + id;
        param += "&do=" + cmsContent.tableName + ".getUserAndRoleInGroup";
        new jj(param).jjAjax2(false);
    },
    m_insert: function () {
        var param = "";
        param += "do=" + hmisDepartment.tableName + ".insert";
        param += "&" + new jj("#swDepartmentForm").jjSerial();
        param += "&department_praivateContent=" + $('#department_praivateContent').summernote('code');
        param += "&department_publicContent=" + $('#department_publicContent').summernote('code');
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDepartment.m_show_tbl();
        hmisDepartment.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "&do=" + hmisDepartment.tableName + ".edit";
        param += "&department_praivateContent=" + $('#department_praivateContent').summernote('code');
        param += "&department_publicContent=" + $('#department_publicContent').summernote('code');
        param += "&" + new jj("#swDepartmentForm").jjSerial();
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisDepartment.m_show_tbl();
        hmisDepartment.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisDepartment.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisDepartment.tableName + ".delete";
        param += "&" + hmisDepartment.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisDepartment.m_show_tbl();
        hmisDepartment.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisDepartment.tableName + ".select";
        param += "&" + hmisDepartment.f_id + "=" + (id == null ? "" : id);
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj(param).jjAjax2(false);
        hmisDepartment.m_show_form();


    },
    selectOptionDepartment: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisDepartment.tableName + ".selectOptionDepartment";
        new jj(param).jjAjax2(false);
    },
    /**
     * ای دی بخش
     * @param {type} id
     * @returns {undefined}
     */
    getViewDepartment: function (id) {
        var param = "";
        param += "&panel=swDepartmentLevel";
        param += "&id=" + id;
        param += "&do=" + hmisDepartment.tableName + ".getViewDepartment";
        new jj(param).jjAjax2(false); 

    },
    
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisDepartment.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swDepartment').css('height', 'auto');
        hmisDepartment.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swDepartment').css('height', 'auto');
        hmisDepartment.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swDepartment').css('height', hmisDepartment.heightTab);
    },
    ////این تابع برای دانلود آیکون در قسمت دپارتمان طراحی شده
    downloadIcon: function () {
        $('#downloadIcon').attr('href', 'upload/' + $('#uploaded_file').val());
    },
}


