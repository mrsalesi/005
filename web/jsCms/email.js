var cmsEmail = {
    tableName: "email",
    f_id: "id",
    f_email_id: "email_id",
    f_qu: "email_qu",
    f_an1: "email_an1",
    f_an2: "email_an2",
    f_an3: "email_an3",
    f_an4: "email_an4",
    f_an5: "email_an5",
    f_an6: "email_an6",
    f_lang: "email_lang",
    f_parent: "email_parent",
    loadForm: function () {
        if ($("#swEmailForm").html() == '') {
            $("#swEmailForm").load("formCms/email.html", null, function () {
                email_body_editor = new jj('#email_body').jjEditor();
                $("#email_text").keyup(function () {
                    cmsEmail.emailLeftChar('email_text', 'dummyID_Sendemail1_MsgBox_lblLeft', 'dummyID_Sendemail_MsgBox_lblemail', 'dummyID_Sendemail_MsgBox_lblMax', null);
                });
                $("#cancel_email").button().click(function (e) {
                    cmsEmail.m_clean();
                    cmsEmail.m_show_tbl();
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {

        var param = "";
        param += "&do=" + cmsEmail.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swEmailTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += (tableHeight == null ? "" : "&height=" + tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        cmsEmail.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swEmailTbl').hide();
        $('#swEmailForm').show();
        cmsEmail.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swEmailForm").jjFormClean();
        new jj(email_body_editor).jjEditorVal("");
        new jj("#" + cmsEmail.f_lang).jjVal('1');
        new jj("#" + cmsEmail.f_parent).jjVal('0');
    },
    m_add_new: function () {
        new jj("do=" + cmsEmail.tableName + ".add_new").jjAjax2(false);
        cmsEmail.m_show_form();
        cmsEmail.m_clean();
        $('#SettingEmail_show').html('');
    },
    m_show_tbl: function () {
        $('#swEmailTbl').show();
        $('#swEmailForm').hide();

        if ($('#swEmailTbl').html() == "") {
            cmsEmail.m_refresh();
        }
        cmsEmail.tabSizeTbl();
    },
//      EmailAddressValidate: function (emailId) {
//          alert(emailId);
//    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailId)) {
//        return "";
//    }else
//    {
//        return "لطفا آدرس ایمیل را به درستی وارد کنید."
//    }
//    },
    m_insert: function () {//send email
        var param = "";
//        var valid = cmsEmail. EmailAddressValidate($("#email_to").val());
//        if (valid == "") {
        //============ BY RASHIDI ========>
        var text = CKEDITOR.instances.email_body.getData();//گرفتن محتوای داخل تکست ادیتور
        alert(CKEDITOR.instances.email_body.getData());
        alert(CKEDITOR.instances.email_body.getData());

//            alert("domain : " + window.location);//http://localhost:8084/sepanoShop/cms.html
//            alert("domain : " + window.location.host);// localhost:8084
//            alert("domain : " + window.location.pathname); //sepanoShop/cms.html
//            alert("sadasdasdas" + $(location).attr('href'));//http://localhost:8084/sepanoShop/cms.html

        //آدرس روت پروژه را به دست می آورد
        var path = $(location).attr('href');
        var fileName = path.substring(0, path.lastIndexOf('/') + 1);

        $("#email_body_hiddenDiv").html(text);
        $('#email_body_hiddenDiv p a').each(function () {//آدرس تمامی لینک های موجود در تکست ادیتور را عوض می کند
            var url = encodeURIComponent(this.href);
            alert("url is : " + url);
            this.href = fileName + "clickTracker?click_tracker_link=" + url + "&click_tracker_visitor=" + $("#email_to").val();
            alert("url is : " + this.href);
        });
//        alert("email_body_hiddenDiv html is : " + $("#email_body_hiddenDiv").html());
//
//        alert("email_body html is : before : " + $("#email_body").html());
        CKEDITOR.instances.email_body.setData($("#email_body_hiddenDiv").html());
        $("#email_body").html($("#email_body_hiddenDiv").html());
//        alert("email_body html is : after : " + $("#email_body").html());
//        alert("email_body val is : before : " + $("#email_body").val());
        $("#email_body").val($("#email_body_hiddenDiv").html());
//        alert("email_body val is : after : " + $("#email_body").val());
        //<============ BY RASHIDI ========
        param += "do=" + cmsEmail.tableName + ".insert";
        param += "&" + new jj("#swEmailForm").jjSerial();
        alert(param);
        new jj(param).jjAjax2(false);
        cmsEmail.m_show_tbl();
        cmsEmail.m_clean();
//       } else {
//           new jj(valid).jjDialog();
//       }
    },
    m_edit: function () {
        newval = $("#email_comment").val() + "-ارسال مجدد";
        $("#email_comment").val(newval);
        cmsEmail.m_insert();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsEmail.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsEmail.tableName + ".delete";
        param += "&id=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsEmail.tableName + ".select";
        param += "&" + cmsEmail.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new cmsEmail.m_show_form();

    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + cmsEmail.tableName + ".add_EN";
        param += "&" + cmsEmail.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsEmail.f_parent).jjVal(id);
        new jj("#" + cmsEmail.f_lang).jjVal("2");
        cmsEmail.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + cmsEmail.tableName + ".add_ar";
        param += "&" + cmsEmail.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + cmsEmail.f_parent).jjVal(id);
        new jj("#" + cmsEmail.f_lang).jjVal("3");
        cmsEmail.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swSMSAll').css('height', 519);
        cmsEmail.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swSMSAll').css('height', 260);
        cmsEmail.heightTab = 260;
    },
    heightTab: "519",
    mainTabSetSize: function () {
        $('#swSMSAll').css('height', cmsEmail.heightTab);
    },
    // تشخیص یونیکد بودن متن
    isUnicode: function (str) {
        var letters = [];
        for (var i = 1; i <= str.length; i++) {
            letters[i] = str.substring((i - 1), i);
            if (letters[i].charCodeAt() > 255) {
                return true;
            }
        }
        return false;
    },
    // محاسبه تعداد کاراکتر های باقیمانده پیامک
    emailCount: 1,
    emailLeftChar: function (txtEmail, lblLeft, lblEmail, lblMax, txtSign) {
        var emailBody = $('#' + txtEmail).val(); //+ $('#' + txtSign).val();

        var isPersian = cmsEmail.isUnicode(emailBody);
        var maxLen = 0;
        var msgLen = emailBody.length;
        var currentLen = msgLen;

        var charLeft = 0;

        if (isPersian) {
            maxLen = 70;
            $('#' + txtEmail).css({
                'direction': 'rtl'
            });
        } else {
            maxLen = 160;
            $('#' + txtEmail).css({
                'direction': 'ltr'
            });
        }

        if (currentLen > maxLen) {

            if (msgLen > maxLen) {
                msgLen -= maxLen;
            }

            if ((msgLen % maxLen) != 0) {
                emailCount = parseInt(Math.floor(currentLen / maxLen)) + 1;

            } else {
                emailCount = parseInt(currentLen / maxLen);
            }

        } else {
            emailCount = 1;
        }

        $('#' + lblLeft).html(maxLen - msgLen);
        $('#' + lblEmail).html(emailCount);
        $('#' + lblMax).html(maxLen);

    },
    showFloatDiv: function (divId) {
        $("#floatBox" + divId).show();
    },
    hideFloatDiv: function (divId) {
        $("#floatBox" + divId).hide();
    }
}
// ----------------------------------------------------------------------------
var cmsSettingSMS = {
    tableName: "smsSetting",
    f_id: "id",
    f_which: "category_SMS_which",
    f_user_id: "category_SMS_user_id",
    f_answer: "category_SMS_answer",
//    loadForm: function () {
//        if ($("#swSettingSMSForm").html() == '') {
//            $("#swSettingSMSForm").load("formCms/SMSConfig.html", null, function () {
//                alert("loadform");
//                $("#SMSSetting_button").button().click(function (e) {
////                    cmsSettingSMS.m_show_tbl();
//                    cmsSettingSMS.m_clean();
//                });
//            });
//        }
//    },
    loadForm: function () {
//        if ($("#swSettingSMSForm").html() == '') {
        $("#swSettingSMSForm").load("formCms/SMSConfig.html", null, function () {
            cmsSettingSMS.m_refresh();
//                alert("loadform");
            $("#cancel_SMSSetting").button().click(function (e) {
//                    cmsSettingSMS.m_show_tbl();
                $("#swSMSAll").tabs({selected: 0});
            });
            //============ BY RASHIDI ========>
            $("#SMSSetting_button").button().click(function (e) {
//                    cmsSettingSMS.m_show_tbl();
//                    cmsSettingSMS.m_clean();
                cmsSettingSMS.m_edit();
                $("#swSMSAll").tabs({selected: 0});
            });

        });
//        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsSettingSMS.tableName + ".refresh";
//        param += "&panel=" + (containerId == null ? "swSettingSMSForm" : containerId);
//        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsSettingSMS.tabSizeTbl();
    },
//    m_show_tbl: function () {
//        $('#swSettingSMSTbl').show();
//        $('#swSettingSMSForm').hide();
//
//        if ($('#swSettingSMSTbl').html() == "") {
//            cmsSettingSMS.m_refresh();
//        }
//        cmsSettingSMS.tabSizeTbl();
//    },

    //<============ BY RASHIDI ========
    m_show_form: function () {
        $('#swSettingSMSForm').show();
        cmsSettingSMS.tabSizeForm();
    },
    m_clean: function () {
        new jj('#swSettingSMSForm').jjFormClean();
    },
    m_add_new: function () {
        jj("do=" + cmsSettingSMS.tableName + ".add_new").jjAjax2(false);
        cmsSettingSMS.m_show_form();
        cmsSettingSMS.m_clean();

    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsSettingSMS.tableName + ".insert";
        param += "&" + new jj('#swSettingSMSForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_tbl();
        cmsSettingSMS.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsSettingSMS.tableName + ".edit";
        param += "&" + new jj('#swSettingSMSForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsSettingSMS.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsSettingSMS.tableName + ".delete";
        param += "&" + cmsSettingSMS.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_tbl();
        cmsSettingSMS.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsSettingSMS.tableName + ".select";
        param += "&" + cmsSettingSMS.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_form();

    },
    heightTab: "514",
    tabSizeTbl: function () {
        $('#swSMSAll').css('height', 514);
        cmsSettingSMS.heightTab = 514;
    },
    tabSizeForm: function () {
        $('#swSMSAll').css('height', 514);
        cmsSettingSMS.heightTab = 514;
    },
    mainTabSetSize: function () {
        $('#swSMSAll').css('height', cmsSettingSMS.heightTab);
    }
}
