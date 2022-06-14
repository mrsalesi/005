var cmsUser = {
    tableName: "Access_User",
    f_id: "id",
    f_user_id: "access_user_id",
    f_pass: "user_passUser",
    f_name: "user_nameUser",
    f_family: "user_familyUser",
    f_email: "user_email",
    f_isActive: "user_is_active",
    f_registDate: "user_createDate",
    f_birthdate: "user_birthdateUser",
    f_question: "user_questionUser",
    f_answer: "user_answerUser",
    f_no1: "user_no1User",
    f_no2: "user_no2User",
    f_parent: "user_parentUser",
    loadForm: function () {
        if ($("#swUserForm").html() == '') {
            $("#swUserForm").load("formCms/user.html", null, function () {
                new jj('#user_birthdateUser').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_User").button().click(function (e) {
                    cmsUser.m_clean();
                    cmsUser.m_show_tbl();
                });
//                new jj('#userAttachFiles_sendFiles').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUser', 'user_attachFile', 'user_titleFile', "#user_divUpload");
                new jj('#userAttachFiles_sendFilesAdmin').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUserAdmin', 'user_attachFile', 'user_titleFile_admin', "#user_attachFileDiv");
                new jj('#sendPic1').jjAjaxFileUpload2('user_file_personal', '#user_attachPicPersonal', '#PicPreviewPersonal');
                new jj('#sendPicSignature').jjAjaxFileUpload2('user_file_Signature', '#user_attachPicSignature', '#PicPreviewSignature');
                new jj('#sendPicupload').jjAjaxFileUpload2('uploaded_file', '#user_attachPicPersonnelCard', '#PicPreview');
                new jj('#sendPicFiles').jjAjaxFileUpload3('#attachFile', '#user_attachFile');
                cmsUser.m_getGroups("selectOptionGroupUser");
                $("#selectOptionGroupUser").select2({
                    width: '100%'
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsUser.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swUserTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        cmsUser.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swUserTbl').hide();
        $('#swUserForm').show();
        cmsUser.tabSizeForm();
    },
    m_clean: function () {

        $('#user_attachFileDiv').html("");
        $('#user_attachFileUserDiv').html("");
        $('#selectOptionGroupUser').html("");
        $('#user_emailUser').css("border-color", "unset");
        $('#user_passUser').css("border-color", "unset");
        ;
        $("#errorRegistMessagePanel1").html('');
        $("#errorRegistMessagePanel2").html('');
        new jj("#swUserForm").jjFormClean();
        new jj("#user_grade").jjVal('');
        $('#PicPreview').attr('src', '');
        $('#PicPreviewPersonal').attr('src', 'img/preview.jpg');
        $('#PicPreviewSignature').attr('src', 'img/preview.jpg');
        $("#inputTextSelectorDiv").html('');
        $("#user_pic1").html('img/preview.jpg');
        $("#user_pic2").html('img/preview.jpg');
        $("#user_pic3").html('img/preview.jpg');
        $("#user_pic4").html('img/preview.jpg');
        $("#inputAfterSelect").html('img/preview.jpg');
        $("#user_divUpload").html('');

        new jj("#user_attachPicPersonal").jjVal('');
        new jj("#user_attachPicPersonnelCard").jjVal('');
        new jj("#user_attachPicSignature").jjVal('');


//         $("#usersPicPreview1").removeAttr("src");
//         $("#userPicPreview1").removeAttr("src");
        new jj("#" + cmsUser.f_user_id).jjVal('');
        new jj("#" + cmsUser.f_pass).jjVal('');
        new jj("#" + cmsUser.f_name).jjVal('');
        new jj("#" + cmsUser.f_family).jjVal('');
        new jj("#" + cmsUser.f_email).jjVal('');
        new jj("#" + cmsUser.f_registDate).jjVal('');
        new jj("#" + cmsUser.f_birthdate).jjVal('');
        new jj("#" + cmsUser.f_question).jjVal('');
        new jj("#" + cmsUser.f_answer).jjVal('');
        new jj("#" + cmsUser.f_no1).jjVal('');
        new jj("#" + cmsUser.f_no2).jjVal('');
        new jj("#" + cmsUser.f_parent).jjVal('');
        new jj("#access_user").jjVal('');
        new jj("#User_button").jjVal('');
        cmsUser.m_getGroups("selectOptionGroupUser");
        $("#selectOptionGroupUser").select2({
            width: '100%'
        });
    },
    m_add_new: function () {
        $('.summernote').summernote({height: 150, tooltip: false});
        new jj("do=" + cmsUser.tableName + ".add_new").jjAjax2(false);
        $("#DownloadPicPersonal").hide();
        $("#DownloadPicPersonnelCard").hide();
        $("#DownloadPicSignature").hide();
        cmsUser.m_show_form();
        cmsUser.m_clean();
    },
    m_show_tbl: function () {
        $('#swUserTbl').show();
        $('#swUserForm').hide();

        if ($('#swUserTbl').html() == "") {
            cmsUser.m_refresh();
        }
        cmsUser.tabSizeTbl();
    },
    m_insert: function () {
        var attachFilesMulti = $("#user_attachFileDiv .user_attachFile");
        var temp = ""
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() == "" ? "" : ($(attachFilesMulti[i]).val() + ",");
        }
        var param = "";
        param += "do=" + cmsUser.tableName + ".insert";
        param += "&user_attachFile=" + temp;
        param += "&" + new jj('#AccessuserForm').jjSerial();
        new jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
//        }
    },
    m_edit: function () {
        var attachFilesMulti = $("#user_attachFileDiv .user_attachFile");
        var temp = "";
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() == "" ? "" : ($(attachFilesMulti[i]).val() + ",");
        }
        var attachFilesMulti2 = $("#user_attachFileUserDiv .user_attachFileUser");
        var temp2 = "";
        for (var i = 0; i < attachFilesMulti2.length; i++) {
            temp += $(attachFilesMulti2[i]).val() == "" ? "" : ($(attachFilesMulti2[i]).val() + ",");
        }
        var param = "";
        param += "do=" + cmsUser.tableName + ".edit";
        param += "&pic=" + $('#swUser').val();
        param += "&user_attachAxPersonal=" + $('#user_attachPicPersonal').val();
        param += "&user_attachAxPersonnelCard=" + $('#user_attachPicPersonnelCard').val();
        param += "&user_attachAxSignature=" + $('#user_attachPicSignature').val();
        param += "&user_attachFile=" + temp;
        param += "&user_attachFileUser=" + temp2;
        param += "&" + new jj('#swUser').jjSerial();
        new jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        $("#inputTextSelectorDiv").html('');
        cmsUser.m_clean();
    },
    m_editUser: function () {
        var attachFilesMulti = $("#user_attachFileUserDiv .user_attachFileUser");
        var temp = "";
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() == "" ? "" : ($(attachFilesMulti[i]).val() + ",");
        }
        var param = "";
        param += "do=" + cmsUser.tableName + ".editUser";
        param += "&user_attachFileUser=" + temp;
        param += "&user_name=" + $("#user_name").val();
        param += "&user_family=" + $("#user_family").val();
        param += "&user_pass=" + $("#user_pass").val();
        param += "&user_email=" + $("#user_email").val();
        param += "&user_address=" + $("#user_address").val();
        param += "&user_postalCode=" + $("#user_postalCode").val();
        param += "&user_mobile=" + $("#user_mobile").val();
        param += "&user_attachPicPersonnelCard=" + $("#user_attachPicPersonnelCard").val();
        param += "&id=" + $("#access_user_id").val();
//        param += "&" + new jj('#AccessuserForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsUser.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsUser.tableName + ".delete";
        param += "&" + cmsUser.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsUser.tableName + ".select";
        param += "&" + cmsUser.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        $("#user_pic4").html('');
        cmsUser.m_show_form();
    },
    m_getGroups: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsGroup.tableName + ".selectOptionGroupUser";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swAccessAll').css('height', 'auto');
        cmsUser.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swAccessAll').css('height', 'auto');
        cmsUser.heightTab = 'auto';
    },
    heightTab: "auto",
    mainTabSetSize: function () {
        $('#swAccessAll').css('height', cmsUser.heightTab);
    },
    m_remove: function (idUpload, id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "cmsUser.removeFile(" + idUpload + "," + id + ");");
    },
    removeFile: function (idUpload, idUser) {

        var param = "";
        param += "do=" + cmsUser.tableName + ".removeFile";
        param += "&upload_id=" + idUpload;
        param += "&access_user_id=" + idUser;
        new jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
    },
    /**
     *  قراردادن نام کاربران فعال در سلکت آپشن ها
     *  @param {type} selector میتوانیم کلاس بدهیم
     * @returns {undefined}
     * @Example: cmsUser.getSelectOption("#userform .usersSelectOption");
     */
    getSelectOption: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsUser.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
    /**
     *  قراردادن نام کاربران فعال در سلکت آپشن ها
     *  @param {type} selector میتوانیم کلاس بدهیم
     * @returns {undefined}
     * @Example: cmsUser.getSelectOption("#userform .usersSelectOption");
     */
    getSelectOptionNotNull: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsUser.tableName + ".getSelectOptionNotNull";
        new jj(param).jjAjax2(false);
    },
    /**
     لیست همه ی کاربران که گزینه ی انتخاب همه ی کاربران ثبت شده را ندارد
     بدون انتخاب هم میتواند باشد
     *  @param {type} selector میتوانیم کلاس بدهیم
     * @returns {undefined}
     * @Example: cmsUser.getSelectOption("#userform .usersSelectOption");
     */
    getSelectOptionNotAll: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + cmsUser.tableName + ".getSelectOptionNotNull";
        new jj(param).jjAjax2(false);
    },
    //نام کاربری و رمز عبور کاربر را با پیامک ارسال میکند
    m_send: function (id) {
        var param = "";
        param += "do=" + cmsUser.tableName + ".send";
        param += "&id=" + id;
        new jj(param).jjAjax2(false);
    },

};
function loginToCMS() {
    new jj("do=Access_User.login&" + (new jj("#swLoginForm").jjSerial())).jjAjax2();

}

var cmsUploadExcelUser = {
    tableName: "ExcelToMysql",
    f_id: "id",
    loadForm: function () {
        if ($("#swUploadExcelUserForm").html() == '') {
            $("#swUploadExcelUserForm").load("formCms/excelToMysql.html", null, function () {

                new jj('#sendExcelFileUsers').jjAjaxFileUpload2('user_excelFileUsers', '', '#user_attachExcelFileUsers', '');
            });
        }
    },
//    m_refresh: function (containerId, sortField, tableHeight) {
//        var param = "";
//        param += "do=" + cmsUser.tableName + ".refresh";
//        param += "&panel=" + (containerId == null ? "swUploadExcelUserTbl" : containerId);
//        param += "&sort=" + (sortField == null ? "0" : sortField);
//        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
//        param += "&jj=1";
//        new jj(param).jjAjax2(false);
//        cmsUser.tabSizeTbl();
//    },
    m_show_form: function () {
//        $('#swUploadExcelUserTbl').hide();
        $('#swUploadExcelUserForm').show();
        cmsUploadExcelUser.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swUploadExcelUserForm").jjVal('');
    },
    m_add_new: function () {
        new jj("do=" + cmsUploadExcelUser.tableName + ".add_new").jjAjax2(false);

        cmsUploadExcelUser.m_show_form();
        cmsUploadExcelUser.m_clean();
    },
    m_show_tbl: function () {
        $('#swUploadExcelUserTbl').show();
        $('#swUploadExcelUserForm').hide();
        if ($('#swUploadExcelUserTbl').html() == "") {
            cmsUploadExcelUser.m_refresh();
        }
        cmsUploadExcelUser.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "&user_attachExcelFileUsers=" + $('#user_attachExcelFileUsers').val();
        param += "&do=ExcelToMysql.transfer";
        new jj(param).jjAjax2(false);
    },
    m_edit: function () {
        var attachFilesMulti = $("#user_divUpload .user_attachFile");
        var temp = ""
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        var param = "";
        param += "do=" + cmsUploadExcelUser.tableName + ".edit";
        param += "&user_attachFile=" + temp;
        new jj(param).jjAjax2(false);
        cmsUploadExcelUser.m_show_tbl();
        cmsUploadExcelUser.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsUploadExcelUser.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsUploadExcelUser.tableName + ".delete";
        param += "&" + cmsUploadExcelUser.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsUploadExcelUser.m_show_tbl();
        cmsUploadExcelUser.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsUploadExcelUser.tableName + ".select";
        param += "&" + cmsUploadExcelUser.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsUploadExcelUser.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swUploadExcelUserForm').css('height', 'auto');
        cmsUser.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swUploadExcelUserForm').css('height', 'auto');
        cmsUser.heightTab = 'auto';
    },
    heightTab: "auto",
    mainTabSetSize: function () {
        $('#swUploadExcelUserForm').css('height', cmsUploadExcelUser.heightTab);
    },
};

