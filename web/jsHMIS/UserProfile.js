var hmisUserProfile = {
    tableName: "Access_User",
    f_id: "id",
    f_user_id: "access_UserProfile_id",
    f_pass: "userProfile_pass",
    f_passwordReminder: "userProfile_passwordReminder",
    f_address: "userProfile_address",
    f_name: "userProfile_name",
    f_family: "userProfile_family",
    f_email: "userProfile_email",
    f_isActive: "userProfile_is_active",
    f_registDate: "userProfile_createDate",
    f_birthdate: "userProfile_birthdate",
    f_question: "userProfile_question",
    f_answer: "userProfile_answer",
    f_AccountInformation: "userProfile_AccountInformation",
    f_codeMeli: "userProfile_codeMeli",
    f_shomareShenasname: "userProfile_shomareShenasname",
    f_mobile: "userProfile_mobile",
    f_parent: "userProfile_parent",
    f_grade: "userProfile_grade",
    f_button: "UserProfile_button",
    loadForm: function () {
        if ($("#swUserProfile").html() == '') {
            $("#swUserProfile").load("formHMIS/userProfile.html", null, function () {
                new jj('#userProfile_birthdate').jjCalendarWithYearSelector(1310, 1410);
                
                new jj('#userProfileAttachFiles_sendFiles').jjAjaxFileUploadByTitleAndMultiFile('#attachFileUserProfile', 'userProfile_attachFile', 'userProfile_titleFile', "#userProfile_divUpload");
//                new jj('#sendPic1').jjAjaxFileUploadPersonal('#user_file_personal', '#user_attachAxPersonal', '#PicPreviewPersonal');
                new jj('#sendPic1_UserProfile').jjAjaxFileUpload2('userProfile_file_personal', '#userProfile_attachPicPersonal', '#PicPreviewPersonal_UserProfile');
                $('#userProfile_pic1').keyup(function () {
                    $('#PicPreviewPersonal_UserProfile').attr('src', 'upload/' + $('#userProfile_pic1').val());
                    if ($('#userProfile_pic1').val() == '') {
                        $('#PicPreviewPersonal_UserProfile').attr('src', 'img/preview.jpg');
                    }
                });
                new jj('#sendPicSignature_UserProfile').jjAjaxFileUpload2('userProfile_file_Signature', '#userProfile_attachPicSignature', '#PicPreviewSignature_UserProfile');
                $('#userProfile_pic2').keyup(function () {
                    $('#PicPreviewSignature_UserProfile').attr('src', 'upload/' + $('#userProfile_pic2').val());
                    if ($('#userProfile_pic2').val() == '') {
                        $('#PicPreviewSignature_UserProfile').attr('src', 'img/preview.jpg');
                    }
                });
                new jj('#sendPicupload_UserProfile').jjAjaxFileUpload2('uploaded_file_UserProfile', '#userProfile_attachPicPersonnelCard', '#PicPreview_UserProfile');
                $('#userProfile_pic3').keyup(function () {
                    $('#PicPreview_UserProfile').attr('src', 'upload/' + $('#userProfile_pic3').val());
                    if ($('#userProfile_pic3').val() == '') {
                        $('#PicPreview_UserProfile').attr('src', 'img/preview.jpg');
                    }
                });
                hmisUserProfile.m_select();
            });
        }
    },
    m_select: function () {
        var param = "";
        param += "do=" + hmisUserProfile.tableName + ".selectProfile";
        param += "&jj=1";
        $('#swUserProfile').show();
        new jj(param).jjAjax2(false);
    },
    m_clean: function () {

        $('#user_emailUser').css("border-color", "unset");
        $('#user_passUser').css("border-color", "unset");
        ;
        $("#errorRegistMessagePanel1").html('');
        $("#errorRegistMessagePanel2").html('');
        new jj("#swUserForm").jjFormClean();
        new jj("#user_grade").jjVal('');
        $('#PicPreview_UserProfile').attr('src', '');
        $('#PicPreviewPersonal_UserProfile').attr('src', 'img/preview.jpg');
        $('#PicPreviewSignature_UserProfile').attr('src', 'img/preview.jpg');
        $("#inputTextSelectorDiv").html('');
        $("#userProfile_pic1").html('img/preview.jpg');
        $("#userProfile_pic2").html('img/preview.jpg');
        $("#userProfile_pic3").html('img/preview.jpg');
        $("#inputAfterSelect").html('img/preview.jpg');
        $("#user_divUpload").html('');

        new jj("#userProfile_attachPicPersonal").jjVal('');
        new jj("#userProfile_attachPicPersonnelCard").jjVal('');
        new jj("#userProfile_attachPicSignature").jjVal('');
        new jj("#" + hmisUserProfile.f_user_id).jjVal('');
        new jj("#" + hmisUserProfile.f_pass).jjVal('');
        new jj("#" + hmisUserProfile.f_name).jjVal('');
        new jj("#" + hmisUserProfile.f_family).jjVal('');
        new jj("#" + hmisUserProfile.f_email).jjVal('');
        new jj("#" + hmisUserProfile.f_isActive).jjVal('');
        new jj("#" + hmisUserProfile.f_registDate).jjVal('');
        new jj("#" + hmisUserProfile.f_birthdate).jjVal('');
        new jj("#" + hmisUserProfile.f_question).jjVal('');
        new jj("#" + hmisUserProfile.f_answer).jjVal('');
        new jj("#" + hmisUserProfile.f_address).jjVal('');
        new jj("#" + hmisUserProfile.f_AccountInformation).jjVal('');
        new jj("#" + hmisUserProfile.f_parent).jjVal('');
    },
    m_edit: function () {
        var attachFilesMulti = $("#userProfile_divUpload .userProfile_attachFile");
        var temp = ""
        for (var i = 0; i < attachFilesMulti.length; i++) {
            temp += $(attachFilesMulti[i]).val() + ",";
        }
        var param = "";
        param += "do=" + hmisUserProfile.tableName + ".editUserProfile";
//        param += "&pic=" + $('#user_file_personal').val();
        param += "&pic=" + $('#swUser').val();
        param += "&user_attachAxPersonal=" + $('#userProfile_attachPicPersonal').val();
        param += "&user_attachAxPersonnelCard=" + $('#userProfile_attachPicPersonnelCard').val();
        param += "&user_attachAxSignature=" + $('#userProfile_attachPicSignature').val();
        param += "&user_attachFile=" + temp;
        param += "&" + new jj('#swUserProfile').jjSerial(param);


        new jj(param).jjAjax2(false);
        $("#inputTextSelectorDiv").html('');

    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swAccessAll').css('height', hmisUserProfile.heightTab);
    }
}
