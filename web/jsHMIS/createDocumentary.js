/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///ایجاد مستند

var hmisCreateDocumentary = {
    tableName: "CreateDocumentary",
    f_id: "id",
    loadForm: function () {
        if ($("#swCreateDocumentaryForm").html() == '') {
//        if (true) {
            $("#swCreateDocumentaryForm").load("formHMIS/createDocumentary.html", null, function () {
                new jj('#createDocumentary_date').jjCalendarWithYearSelector(1310, 1410);
//                new jj('#createDocumentary_createDate').jjCalendarWithYearSelector(1310, 1410);
                new jj('#createDocumentary_revisionDate').jjCalendarWithYearSelector(1310, 1410);
                new jj('#createDocumentary_revisionDateNext').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_CreateDocumentary").button().click(function (e) {
                    hmisCreateDocumentary.m_clean();
                    hmisCreateDocumentary.m_show_tbl();
                });


                new jj('#sendFile1').jjAjaxFileUpload2('createDocumentary_file1', '#createDocumentary_attachmentfile1', 'createDocumentary_titleFile1', '#PicPreviewFile1');
//                new jj('#sendFile1').jjAjaxFileUploadTitle('createDocumentary_file1', '#createDocumentary_attachmentfile1', 'createDocumentary_titleFile1', '#createDocumentary_attachmentfileTitle1', '#PicPreviewFile1');
                $('#user_file1').keyup(function () {
                    $('#PicPreviewFile1').attr('src', 'upload/' + $('#user_file1').val());
                    if ($('#user_file1').val() == '') {
                        $('#PicPreviewFile1').attr('src', 'img/preview.jpg');
                    }
                });
                new jj('#sendFile2').jjAjaxFileUpload2('createDocumentary_file2', '#createDocumentary_attachmentfile2', 'createDocumentary_titleFile2', '#PicPreviewFile2');
//                new jj('#sendFile2').jjAjaxFileUploadTitle('createDocumentary_file2', '#createDocumentary_attachmentfile2', 'createDocumentary_titleFile2', '#createDocumentary_attachmentfileTitle2', '#PicPreviewFile2');
                $('#user_file2').keyup(function () {
                    $('#PicPreviewFile2').attr('src', 'upload/' + $('#user_file2').val());
                    if ($('#user_file2').val() == '') {

                        $('#PicPreviewFile2').attr('src', 'img/preview.jpg');
                    }
                });
                new jj('#sendFile3').jjAjaxFileUpload2('createDocumentary_file3', '#createDocumentary_attachmentfile3', 'createDocumentary_titleFile3', '#PicPreviewFile3');
//                new jj('#sendFile3').jjAjaxFileUploadTitle('createDocumentary_file3', '#createDocumentary_attachmentfile3', 'createDocumentary_titleFile3', '#createDocumentary_attachmentfileTitle3', '#PicPreviewFile3');
                $('#user_file3').keyup(function () {
                    $('#PicPreviewFile3').attr('src', 'upload/' + $('#user_file3').val());
                    if ($('#user_file3').val() == '') {
                        $('#PicPreviewFile3').attr('src', 'img/preview.jpg');
                    }
                });
                hmisCreateDocumentary.getCategorySelectOption("#createDocumentary_category"); // برای قرار گرفتن سلکت آپشن شخص ها در قسمت های مربوطه
                cmsUser.getSelectOptionNotNull("#createDocumentary_signatory_user_x"); // برای قرار گرفتن سلکت آپشن شخص ها در قسمت های مربوطه
                $('.summernote').summernote({tooltip: false});
                // برای createDocumentary_communicator و createDocumentary_responsibleDocumentary 
                hmisRole.getSelectOptionRequierd("#swCreateDocumentaryForm #createDocumentary_responsibleDocumentary"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                hmisRole.getSelectOptionRequierd("#swCreateDocumentaryForm #createDocumentary_communicator"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                hmisRole.getSelectOption("#swCreateDocumentaryForm .rolesForCreateDocs"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                cmsUser.getSelectOption("#swCreateDocumentaryForm .usersForCreateDocs"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
//                $("#swCreateDocumentaryForm .select2").select2({     
//                    width: '100%' 
//                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCreateDocumentaryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.tabSizeTbl();
        hmisCreateDocumentary.m_show_tbl();
    },
    m_refreshSignatureMyDocumentation: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".refreshSignatureMyDocumentation";
        param += "&panel=" + (containerId == null ? "swCreateDocumentaryTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.tabSizeTbl();
    },
    /**
     * برای ابلاغ های مستندات
     * @param {type} containerId
     * @param {type} sortField
     * @param {type} tableHeight
     * @returns {undefined}
     */
    m_refreshCommunications: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".refreshCommunications";
        param += "&panel=" + (containerId == null ? "swCommunicationsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.tabSizeTbl();
    },
    selectOneDocuementToSign: function (id) {
        var param = "";
        param += "id=" + id;
        param += "&do=" + hmisCreateDocumentary.tableName + ".selectOneDocuementToSign";
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.m_show_form();
        hmisCreateDocumentary.m_clean();
//        hmisSignDocumentary.m_clean();
    },
    m_add_new: function () {
        new jj("do=" + hmisCreateDocumentary.tableName + ".add_new").jjAjax2(false);
        hmisCreateDocumentary.m_show_form();
        $('.form-group').val("null").trigger('change');
        $('#createDocumentary_responsibleDocumentary').select2({width: '100%'});
        $('#createDocumentary_communicator').select2({width: '100%'});
        $('#createDocumentary_reciversRoles').select2({width: '100%'});
        $('#createDocumentary_reciversUsers').select2({width: '100%'});
        var div = $('<div>'); //خواندن قالب پیشفرض
        div.load("template/createDocTemplate.html", null, function () {

            $("#createDocumentary_htmlContent").summernote('code', $(div).html());
        });
        $("#Downloadfile2").hide();
        $("#copyDocumentary").hide();
        $("#printDocumentary").hide();
        $("#Downloadfile3").hide();
        $("#Downloadfile1").hide();
        hmisCreateDocumentary.m_clean();
    },
    m_show_form: function () {
        $('#swCreateDocumentaryTbl').hide();
        $('#swCreateDocumentaryForm').show();
        new jj("#swCreateDocumentaryForm").jjFormClean();
        hmisCreateDocumentary.tabSizeForm();
        $('#createDocumentary_responsibleDocumentary').select2({width: '100%'});
        $('#createDocumentary_communicator').select2({width: '100%'});
        $('#createDocumentary_reciversRoles').select2({width: '100%'});
        $('#createDocumentary_reciversUsers').select2({width: '100%'});
    },
    m_show_formCopy: function () {
        $('#swCreateDocumentaryTbl').hide();
        $('#swCreateDocumentaryForm').show();
        hmisCreateDocumentary.tabSizeForm();
    },
    m_clean: function () {

        new jj("#swCreateDocumentaryForm").jjFormClean();
        $('#createDocumentary_htmlContent').summernote('code', '');
        $("#signatorysAdd").html('');
        $("#createDocumentary_communicator").val("");
        $("#createDocumentary_communicator").select2({with : '100%'});
        $("#createDocumentary_reciversRoles").val("");
        $("#createDocumentary_reciversRoles").select2({with : '100%'});
        $("#createDocumentary_reciversUsers").val("");
        $("#createDocumentary_reciversUsers").select2({with : '100%'});
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
        $('#swCreateDocumentaryTbl').show();
        $('#refreshCreateDocumentary').DataTable({destroy: true}); //برای اینکه درست نمایش داده بشود
        $('#swCreateDocumentaryForm').hide();
        hmisCreateDocumentary.tabSizeTbl();
    },
    m_insert: function () {
        var flag = true;
        var massages = "";
        if (new jj('#createDocumentary_title').jjVal() !== "") {
            $('#createDocumentary_title').removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_title').addClass("required");
            massages += "<br>" + "لطفا عنوان مستند را وارد کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_category').jjVal() == "" && new jj('#createDocumentary_category1').jjVal() == "") {
            $('#createDocumentary_category').addClass("required");
            massages += "<br>" + "لطفا دسته را وارد کنید";
            flag = false;
        } else {
            $("#createDocumentary_category").removeClass("required");
        }
        if (new jj('#createDocumentary_date').jjVal() !== "") {
            $("#createDocumentary_date").removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_date').addClass("required");
            massages += "<br>" + "لطفا تاریخ را وارد کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_responsibleDocumentary').jjVal() !== "") {
            $("#select2-createDocumentary_responsibleDocumentary-container").removeClass("required");
            massages += "";
        } else {
            $('#select2-createDocumentary_responsibleDocumentary-container').addClass("required");
            massages += "<br>" + "لطفا مسئول مستند را انتخاب کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_revisionDate').jjVal() !== "") {
            $("#createDocumentary_revisionDate").removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_revisionDate').addClass("required");
            massages += "<br>" + "لطفاتاریخ بازنگری  را مشخص کنید";
            flag = false;
        }


        if (!flag) {
            new jj(massages).jjModal("پیام");
            return;
        }
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".insert";
        param += "&" + new jj("#swCreateDocumentaryForm").jjSerial();
        param += "&form-group=" + $(".form-group option:selected").val();
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.m_show_tbl();
        hmisCreateDocumentary.m_clean();
    },
    m_edit: function () {
        var flag = true;
        var massages = "";
        if (new jj('#createDocumentary_title').jjVal() !== "") {
            $('#createDocumentary_title').removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_title').addClass("required");
            massages += "<br>" + "لطفا عنوان مستند را وارد کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_category').jjVal() == "" && new jj('#createDocumentary_category1').jjVal() == "") {
            $('#createDocumentary_category').addClass("required");
            massages += "<br>" + "لطفا دسته را وارد کنید";
            flag = false;
        } else {
            $("#createDocumentary_category").removeClass("required");
        }
        if (new jj('#createDocumentary_date').jjVal() !== "") {
            $("#createDocumentary_date").removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_date').addClass("required");
            massages += "<br>" + "لطفا تاریخ را وارد کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_responsibleDocumentary').jjVal() !== "") {
            $("#select2-createDocumentary_responsibleDocumentary-container").removeClass("required");
            massages += "";
        } else {
            $('#select2-createDocumentary_responsibleDocumentary-container').addClass("required");
            massages += "<br>" + "لطفا مسئوا مستنذ را انتخاب کنید";
            flag = false;
        }
        if (new jj('#createDocumentary_revisionDate').jjVal() !== "") {
            $("#createDocumentary_revisionDate").removeClass("required");
            massages += "";
        } else {
            $('#createDocumentary_revisionDate').addClass("required");
            massages += "<br>" + "لطفاتاریخ بازنگری  را مشخص کنید";
            flag = false;
        }
        if (!flag) {
            new jj(massages).jjModal("پیام");
            return;
        }
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".edit";
        param += "&" + new jj("#swCreateDocumentaryForm").jjSerial();
        param += "&" + new jj("#signatorys").jjSerial();
        param += "&" + new jj("#signatorysAdd").jjSerial();
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.m_show_tbl();
        hmisCreateDocumentary.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا میخواهید مستند را حذف کنید ؟").jjModal_Yes_No("حذف", "hmisCreateDocumentary.m_delete_after_question(" + id + ");\n");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".delete";
        param += "&" + hmisCreateDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisCreateDocumentary.m_show_tbl();
        hmisCreateDocumentary.m_clean();
    },
    setRolInTextField: function (obj) {
        var roleTite = $(obj).find("option:selected").html();
        $(obj).parent().find('input').val(roleTite);
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".select";
        param += "&" + hmisCreateDocumentary.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $("#copyDocumentary").show();
        $("#printDocumentary").show();
        $('.summernote').summernote({tooltip: false});
        hmisCreateDocumentary.m_show_form();
        hmisCreateDocumentary.m_clean();
    },
    copyDocumentary: function () {
        alert("مستند مورد نظر کپی شد");
        new jj("do=" + hmisCreateDocumentary.tableName + ".copyDocumentary").jjAjax2(false);
        hmisCreateDocumentary.m_show_formCopy();
        $("#signatorys").html('');
        $("#signatorysAdd").html('');
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    getCategorySelectOption: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisCreateDocumentary.tableName + ".getCategorySelectOption";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swCreateDocumentary').css('height', 'auto');
        hmisCreateDocumentary.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swCreateDocumentary').css('height', 'auto');
        hmisCreateDocumentary.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swCreateDocumentary').css('height', hmisCreateDocumentary.heightTab);
    },
    changeSelectToInputCategory: function () {
        if ($('#createDocumentary_category').css('display') == 'none') {
            $('#createDocumentary_category').show();
            $('#createDocumentary_category').attr('name', 'createDocumentary_category');
            $('#createDocumentary_category1').hide();
            $('#createDocumentary_category1').attr('name', 'createDocumentary_category1');
        } else {
            $('#createDocumentary_category').hide();
            $('#createDocumentary_category').attr('name', 'createDocumentary_category1');
            $('#createDocumentary_category1').show();
            $('#createDocumentary_category1').attr('name', 'createDocumentary_category');
        }
    },
    sendForCommunication: function (id) {
        new jj("آیا از ارسال مستند به ابلاغ کننده اطمینان دارید؟").jjModal_Yes_No("ارسال مستند به ابلاغ کننده", 'hmisCreateDocumentary.sendForCommunicationAfterQuestion(' + id + ');\n');
    },
    sendForCommunicationAfterQuestion: function (id) {
        var param = "";
        param += "do=" + hmisCreateDocumentary.tableName + ".sendForCommunication";
        param += "&id=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },

};
