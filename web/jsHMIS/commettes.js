/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisCommettes = {
    tableName: "Commettes",
    f_id: "id",
    loadForm: function () {
        if ($("#swCommettesForm").html() == '') {
            $("#swCommettesForm").load("formHMIS/05newCommette.html", null, function () {
                $('#sessions_time').wickedpicker();
                new jj("#commettes_date").jjCalendarWithYearSelector(1397, 1420);
                new jj("#sessions_date").jjCalendarWithYearSelector(1397, 1420);
                new jj("#sessions_invitationDate").jjCalendarWithYearSelector(1397, 1420);
                $('#sessions_agenda').summernote({height: 150, tooltip: false});
                new jj('#sendDocumentCommettes').jjAjaxFileUploadByTitleAndMultiFile('#attachDocumentCommettes', 'commettes_documentsFile', 'commettes_titleFile1', "#showFiles1Div");
                new jj('#sendFilesCommettes').jjAjaxFileUploadByTitleAndMultiFile('#attachFileCommettes', 'commettes_regulationFile', 'commettes_titleFile2', "#showFiles2Div");
                new jj('#sendFilesInvitees').jjAjaxFileUploadByTitleAndMultiFile('#attachFileInvitees', 'sessions_InviteesFile', 'sessions_titleFile', "#showFilesDiv");
                cmsUser.getSelectOption("#formInvitation #sessions_InviteesInSide"); // برای قرار گرفتن سلکت آپشن کاربران ها در قسمت های مربوطه
                hmisRole.getSelectOptionRequierd("#swCommettesForm .Roles"); // برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                cmsContent.m_getGroupsWithUsers("#sessions_InviteesGroupId");
                $("#sessions_InviteesGroupId").select2({
                    width: '100%'
                });
                $("#commettes_secretary").select2({
                    width: '100%'
                });
                $("#commettes_superwizar").select2({
                    width: '100%'
                });
                $("#commettes_members").select2({
                    width: '100%'
                });
                $("#sessions_InviteesInSide").select2({
                    width: '100%'
                });
                $('#newCommetteForm').show();
                $('#formInvitation').hide();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swCommettesTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swCommettesTbl').hide();
        $('#swCommettesForm').show();
        hmisCommettes.tabSizeForm();
    },
    m_clean: function () {
        $("#commettes_secretary").val("");
        $("#commettes_secretary").select2({
            width: '100%'
        });
        $("#commettes_members").val("");
        new jj("#swCommettesForm").jjFormClean();
        $("#showFiles1Div").html("");
        $("#showFiles2Div").html("");
        $(".inputSelectorDiv").html("");
        $("#swCommettesForm input:text").val("");
        $("#commettes_members").select2({
            width: '100%'
        });
    },
    m_add_new: function () {
        new jj("do=" + hmisCommettes.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisCommettes.m_show_form();
        $('#newCommetteForm').show();
        $('#formInvitation').hide();
        hmisCommettes.m_clean();
        $("#commettes_secretary").select2({
            width: '100%'
        });
        $("#commettes_superwizar").select2({
            width: '100%'
        });
    },
    m_show_tbl: function () {
        $('#swCommettesTbl').show();
        $('#swCommettesForm').hide();
        $('#refreshCommettes').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });

    },
    m_insert: function () {
        var param = "";
        var commettes_documentsFile = $("#swCommettesForm #newCommetteForm  .commettes_documentsFile");
        var temp = ""
        for (var i = 0; i < commettes_documentsFile.length; i++) {
            temp += $(commettes_documentsFile[i]).val() + ",";
        }
        var commettes_regulationFile = $("#swCommettesForm #newCommetteForm .commettes_regulationFile");
        var temp2 = ""
        for (var i = 0; i < commettes_regulationFile.length; i++) {
            temp2 += $(commettes_regulationFile[i]).val() + ",";
        }

        var flag = true;
        if ($("#commettes_date").val() == "") {
            $("#commettes_date").addClass("required");
            flag = false;
        } else {
            $("#commettes_date").removeClass('required');
        }
        if ($("#commettes_superwizar").val() == "" || $("#commettes_superwizar").val() == null) {
            $("#select2-commettes_superwizar-container").addClass("required");
            flag = false;
        } else {
            $("#select2-commettes_superwizar-container").removeClass('required');
        }
        if ($("#commettes_title").val() == "") {
            $("#commettes_title ").addClass("required");
            flag = false;
        } else {
            $("#commettes_title").removeClass('required');
        }
        if ($("#commettes_secretary").val() == "" || $("#commettes_secretary").val() == null) {
            $("#select2-commettes_secretary-container").addClass("required");
            flag = false;
        } else {
            $("#select2-commettes_secretary-container").removeClass("required");
        }
        if ($("#commettes_members").val() == "" || $("#commettes_members").val() == null) {
            $("#formQuestionsCommettes span.select2-selection--multiple").addClass("required");
//                                $("html, body").delay(1000).animate({scrollTop: $('#refreshRoles').offset().top}, 800);

            flag = false;
        } else {
            $("#formQuestionsCommettes span.select2-selection--multiple").removeClass("required");
        }
        if (flag == true) {
            param += "&do=" + hmisCommettes.tableName + ".insert";
            param += "&commettes_documentsFile=" + temp;
            param += "&commettes_regulationFile=" + temp2;
            param += "&" + new jj('#newCommetteForm').jjSerial();
            new jj(param).jjAjax2(false);
            hmisCommettes.m_show_tbl();
            hmisCommettes.m_clean();
        }
        $("html, body").delay(1000).animate({scrollTop: $('#newCommetteForm').offset().top}, 800);
    },
    m_edit: function () {
        var flag = true;
        if ($("#commettes_superwizar option:selected").val() == "" || $("#commettes_superwizar option:selected").val() == "null") {
            $("#commettes_superwizar").addClass("required");
            flag = false;
        } else {
            $("#commettes_superwizar").removeClass('required');
        }
        if ($("#commettes_title").val() == "") {
            $("#commettes_title ").addClass("required");
            flag = false;
        } else {
            $("#commettes_title").removeClass('required');
        }
        if ($("#commettes_secretary").val() == "" || $("#commettes_secretary").val() == null) {
            $("#commettes_secretary").addClass("required");
            flag = false;
        } else {
            $("#commettes_secretary").removeClass("required");
        }
        if ($("#commettes_members").val() == "" || $("#commettes_members").val() == null) {
            $("#formQuestionsCommettes span.select2-selection--multiple").addClass("required");
//                                $("html, body").delay(1000).animate({scrollTop: $('#refreshRoles').offset().top}, 800);

            flag = false;
        } else {
            $("#formQuestionsCommettes span.select2-selection--multiple").removeClass("required");
        }
        if ($("#commettes_date").val() == "") {
            $("#commettes_date").addClass("required");
            flag = false;
        } else {
            $("#commettes_date").removeClass('required');
        }
        if (flag == true) {
            var temp2 = "";
            var param = "";
            param += "&do=" + hmisCommettes.tableName + ".edit";
            param += "&" + new jj('#newCommetteForm').jjSerial();
            param += "&id=" + new jj('#hmis_commettes_id').jjVal();
            var commettes_documentsFile = $("#swCommettesForm #newCommetteForm .commettes_documentsFile");
            var temp = ""
            for (var i = 0; i < commettes_documentsFile.length; i++) {
                temp += $(commettes_documentsFile[i]).val() + ",";
            }
            var commettes_regulationFile = $("#swCommettesForm #newCommetteForm .commettes_regulationFile");
            var temp2 = ""
            for (var i = 0; i < commettes_regulationFile.length; i++) {
                temp2 += $(commettes_regulationFile[i]).val() + ",";
            }
            param += "&commettes_documentsFile=" + temp;
            param += "&commettes_regulationFile=" + temp2;
            new jj(param).jjAjax2(false);
            hmisCommettes.m_show_tbl();
            hmisCommettes.m_clean();
        }
    },
//    m_validation: function () {// mohamdad
//        if (new jj("#content_title").jjVal().length < 1) {
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
//        return "";
    //    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisCommettes.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".delete";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisCommettes.m_show_tbl();
        hmisCommettes.m_clean();
    },
    copy: function (id) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".copy";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisCommettes.m_show_tbl();
        hmisCommettes.m_clean();
    },
    m_select: function (id) {
//        hmisCommettes.members();//برای اینکه افرادی عضو نیستن هم نمایش دهد
        $('#showFiles2Div').html("");
        $('#showFiles1Div').html("");
        $('#swCommettesForm  select').removeClass("required");
        $('#swCommettesForm  input').removeClass("required");
        $('#swCommettesForm  #refreshRoles').removeClass("required");
        $('#inputTextSelectorDiv1').val("");
        $('#inputTextSelectorDiv2').val("");
        $('#commettes_documentsFile').val("");
        $('#commettes_regulationFile').val("");
        $('#newCommetteForm').show();
        $('#formInvitation').hide();
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".select";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisCommettes.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".add_EN";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisCommettes.f_parent).jjVal(id);
        new jj("#" + hmisCommettes.f_lang).jjVal("2");
        hmisCommettes.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".add_Ar";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisCommettes.f_parent).jjVal(id);
        new jj("#" + hmisCommettes.f_lang).jjVal("3");
        hmisCommettes.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".add_lang";
        param += "&" + hmisCommettes.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisCommettes.f_parent).jjVal(id);
        new jj("#" + hmisCommettes.f_lang).jjVal(langId);
        //        alert(id+"&&&&&"+langId);
        hmisCommettes.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".searchTags";
        param += "&" + new jj('#swCommettesForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisCommettes.tableName + ".insertTags";
        param += "&" + new jj('#swCommettesForm').jjSerial();
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swCommettes').css('height', "auto");
    },
    tabSizeForm: function () {
//        $('#swCommettes').css('height', "auto");
    },
    /////////////////////shiran////////////
    showInvitationForm: function (commeteId) {
        $("#sessions_InviteesGroupsId").val('');
        $("#sessions_InviteesGroupsId").select2({width: '100%'});
        $("#sessions_InviteesUsersId").val('');
        $("#sessions_InviteesUsersId").select2({width: '100%'});
        $("#sessions_InviteesInSide").val('');
        $("#sessions_InviteesInSide").select2({width: '100%'});
        $("#formInvitation textarea#sessions_agenda").val('');
        new jj("#formInvitation").jjFormClean();
        $('#hmis_commettes_id').val(commeteId);
        $('#newCommetteForm').hide();
        $('#formInvitation').show();
        $('#addButton1').show();
        $('#showFilesDiv').html("");
        $("#swCommettesForm input:text").val("");
//        var $div = $('<div>');
//        $div.load('template/agenda.html', function () {
//            $('#sessions_agenda').summernote('code', $(this).html());
//        });
        hmisCommettes.m_show_form();
        var param = "";
        param += "&id=" + new jj("#hmis_commettes_id").jjVal();
        param += "&do=" + hmisCommettes.tableName + ".showInvitationForm";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    /**
     * ارسال دعوتنامه و ایجاد صورتجلسه
     * @returns {undefined}
     */
    Invitees: function () {
        var sessions_InviteesFile = $("#swCommettesForm #formInvitation .sessions_InviteesFile");
        var temp2 = ""
        for (var i = 0; i < sessions_InviteesFile.length; i++) {
            temp2 += $(sessions_InviteesFile[i]).val() + ",";
        }
        var param = "";
        var temp = $('#InviteesDiv input:checkbox[class=checkBoxInvitees]:checked'); //مدعوین سمت دار
        var InviteesOutSide = $('#InviteesDiv .invitedOutSide');
        var InviteesInSide = $('#sessions_InviteesInSide').val();
//        if (temp.size() == 0) {//اگر تیک عضوی را نزده بود
//            alert("لطفا مدعوین را انتخاب کنید");
//            temp.addClass('required');
//            return;
//        }
        var temp1 = "";
        var temp3 = "";
        var temp4 = "";
        var temp5 = "";
        ///////////////////////مدعوین سمت دار
        for (var i = 0; i < temp.length; i++) {
            temp1 += $(temp[i]).attr('value') + ","; //نام چک باکس عدد مورد نظر
        }
        /////////////////////////////مهمانان داخل سازمان
        if (InviteesInSide != null) {
            for (var i = 0; i < InviteesInSide.length; i++) {
                temp3 += InviteesInSide[i] + ","; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
        }
        ///////////////////////////////////////مهمانان خارج از سازمان
        //این قسمت نام و نام خانوادگی فرد مهمان را میگیرد واگر خالی باشد چیزی ارسال نمی شود 
        // اول دیو کلی را می گیرد  بعد اینپوت هایی که داخل دیو ها هستند را در می آورد
        var flag = true;
        var InviteesOutSideName = "";
        if (InviteesOutSide.length > 1) {
            for (var i = 1; i < InviteesOutSide.length; i++) {
                var InputInviteesOutSide = $("#InviteesDiv #invitedOutSide_" + [i] + " input");
                var name = $("#InviteesDiv #invitedOutSide_" + [i] + " .name");
                var phone = $("#InviteesDiv #invitedOutSide_" + [i] + " .phone");
                var email = $("#InviteesDiv #invitedOutSide_" + [i] + " .email");
                var role = $("#InviteesDiv #invitedOutSide_" + [i] + " .role");
                if ($("#InviteesDiv #invitedOutSide_" + [i] + " .email").val() != "") {
                    if (!hmisCommettes.validateEmail(email)) {
                        flag = false;
                    }
                } else {
                    $(email).addClass('required');
                    flag = false;
                }
                if ($("#InviteesDiv #invitedOutSide_" + [i] + " .name").val() != "" && $("#InviteesDiv #invitedOutSide_" + [i] + " .phone").val() != "" && $("#InviteesDiv #invitedOutSide_" + [i] + " .role").val() != "" && (hmisCommettes.validateName(name)) && (hmisCommettes.validatePhone(phone)) && (hmisCommettes.validateName(role))) {
                    temp4 += $("#InviteesDiv #invitedOutSide_" + [i] + " .name").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .phone").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .email").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .role").val() + "%23A%23";
                    InviteesOutSideName = "#InviteesDiv #invitedOutSide_" + [i] + " input";
                } else if ($("#InviteesDiv #invitedOutSide_" + [i] + " .name").val() == "") {
                    $(name).addClass('required');
                    flag = false;
                } else if ($("#InviteesDiv #invitedOutSide_" + [i] + " .phone").val() == "") {
                    $(phone).addClass('required');
                    flag = false;
                } else if ($("#InviteesDiv #invitedOutSide_" + [i] + " .role").val() == "") {
                    $(role).addClass('required');
                    flag = false;

                }
            }
        }


        if ($('#sessions_date').val() === "") {
            $('#sessions_date').addClass('required');
        } else {
            $('#sessions_date').removeClass('required');
        }
        if ($('#sessions_contextInvitation').val() === "") {
            $('#sessions_contextInvitation').addClass('required');
        } else {
            $('#sessions_contextInvitation').removeClass('required');
        }
        if ($('#sessions_invitationDate').val() === "") {
            $('#sessions_invitationDate').addClass('required');
        } else {
            $('#sessions_invitationDate').removeClass('required');
        }
        if ($('#sessions_time').val() === "") {
            $('#sessions_time').addClass('required');
        } else {
            $('#sessions_time').removeClass('required');
        }
        if ($("#formInvitation input.required").length > 0) {//اگر فیلدی خالی وجو داشتکه کلاسش required بود
            flag = false;
        }
        if (flag) {

            new jj("درصورت ارسال دعوتنامه پیام برای گیرندگان ارسال خواهد شد وقابل برگشت نیست").jjModal_Yes_No("دعوتنامه ارسال شود؟", "hmisCommettes.InviteesAfterQuestion();");
        }


    },
    InviteesAfterQuestion: function () {//مدعوین
        var sessions_InviteesFile = $("#swCommettesForm #formInvitation .sessions_InviteesFile");
        var temp2 = ""
        for (var i = 0; i < sessions_InviteesFile.length; i++) {
            temp2 += $(sessions_InviteesFile[i]).val() + ",";
        }
        var param = "";
        var temp = $('#InviteesDiv input:checkbox[class=checkBoxInvitees]:checked'); //مدعوین سمت دار
        var InviteesOutSide = $('#InviteesDiv .invitedOutSide');
        var InviteesInSide = $('#sessions_InviteesInSide').val();
//        if (temp.size() == 0) {//اگر تیک عضوی را نزده بود
//            alert("لطفا مدعوین را انتخاب کنید");
//            temp.addClass('required');
//            return;
//        }
        var temp1 = "";
        var temp3 = "";
        var temp4 = "";
        var temp5 = "";
        ///////////////////////مدعوین سمت دار
        for (var i = 0; i < temp.length; i++) {
            temp1 += $(temp[i]).attr('value') + ","; //نام چک باکس عدد مورد نظر
        }
        /////////////////////////////مهمانان داخل سازمان
        if (InviteesInSide != null) {
            for (var i = 0; i < InviteesInSide.length; i++) {
                temp3 += InviteesInSide[i] + ","; //انتخاب چندین نفر وارسال ای دی افراد با جداساز
            }
        }
        ///////////////////////////////////////مهمانان خارج از سازمان
        //این قسمت نام و نام خانوادگی فرد مهمان را میگیرد واگر خالی باشد چیزی ارسال نمی شود 
        // اول دیو کلی را می گیرد  بعد اینپوت هایی که داخل دیو ها هستند را در می آورد
        var flag = true;
        var InviteesOutSideName = "";
        if (InviteesOutSide.length > 1) {
            for (var i = 1; i < InviteesOutSide.length; i++) {
                var InputInviteesOutSide = $("#InviteesDiv #invitedOutSide_" + [i] + " input");
                var name = $("#InviteesDiv #invitedOutSide_" + [i] + " .name");
                var phone = $("#InviteesDiv #invitedOutSide_" + [i] + " .phone");
                var email = $("#InviteesDiv #invitedOutSide_" + [i] + " .email");
                var role = $("#InviteesDiv #invitedOutSide_" + [i] + " .role");
                if ($("#InviteesDiv #invitedOutSide_" + [i] + " .email").val() != "") {
                    if (!hmisCommettes.validateEmail(email)) {
                        flag = false;
                    }
                } else {
                    $(email).removeClass('required');
                    flag = true;
                }
                if ($("#InviteesDiv #invitedOutSide_" + [i] + " .name").val() != "" && $("#InviteesDiv #invitedOutSide_" + [i] + " .phone").val() != "" && $("#InviteesDiv #invitedOutSide_" + [i] + " .role").val() != "" && (hmisCommettes.validateName(name)) && (hmisCommettes.validatePhone(phone)) && (hmisCommettes.validateName(role))) {
                    temp4 += $("#InviteesDiv #invitedOutSide_" + [i] + " .name").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .phone").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .email").val() + "," + $("#InviteesDiv #invitedOutSide_" + [i] + " .role").val() + "%23A%23";
                    InviteesOutSideName = "#InviteesDiv #invitedOutSide_" + [i] + " input";
                } else {
                    flag = false;
                }
            }
        }

        if ($('#sessions_date').val() === "") {
            $('#sessions_date').addClass('required');
        } else {
            $('#sessions_date').removeClass('required');
        }
        if ($('#sessions_contextInvitation').val() === "") {
            $('#sessions_contextInvitation').addClass('required');
        } else {
            $('#sessions_contextInvitation').removeClass('required');
        }
        if ($('#sessions_invitationDate').val() === "") {
            $('#sessions_invitationDate').addClass('required');
        } else {
            $('#sessions_invitationDate').removeClass('required');
        }
        if ($('#sessions_time').val() === "") {
            $('#sessions_time').addClass('required');
        } else {
            $('#sessions_time').removeClass('required');
        }
        if ($("#formInvitation input.required").length > 0) {//اگر فیلدی خالی وجو داشتکه کلاسش required بود
            flag = false;
        }
        if (flag === true) {
            param += "&sessions_InviteesFile=" + temp2;
            param += "&sessions_Invitees=" + temp1;
            param += "&sessions_InviteesInSide=" + temp3; //مهمان داخل  سازمان
            param += "&sessions_InviteesOutSide=" + temp4; //مهمان خارج از  سازمان
            param += "&sessions_agenda=" + $('#sessions_agenda').summernote('code');
            param += "&commettesId=" + new jj("#hmis_commettes_id").jjVal(); //ای دی کمیته
            param += "&" + new jj('#formInvitation').jjSerial();
            param += "&do=Sessions.Invitees&jj=1";
            new jj(param).jjAjax2(false);
        } else {
            new jj("همه فیلد ها را پر کنید.").jjModal("پیام سامانه");
        }
    },
//    /**
//     * اضافه کردن نقش ها
//     * @param {type} i
//     * @returns {undefined}
//     */
//    addMembers: function (i) {
//        var temp1 = "";
//        var param = "";
//        if ($("#td" + i + " i").attr('class') === "icon ion-checkmark-circled") {
//            $("#td" + i + " i").attr('class', 'icon ion-plus-circled').css("color", "red");
//        } else if ($("#td" + i + " i").attr('class') === "icon ion-plus-circled") {
//            var RoleId = $("#td" + i + " i").attr('id');
//            $("#td" + i + " i").attr('class', 'icon ion-checkmark-circled').css("color", "green");
//
//        }
//        var temp = $("#tableRolesDiv #refreshRoles .ion-checkmark-circled");
//        for (var i = 0; i < temp.size(); i++) {
//            temp1 += $(temp[i]).attr('id') + "%23A%23";
//        }
//        $('#commettes_members').val(temp1);
//
//
//    },
//    members: function (i) {
//        new jj("do=Commettes.members&jj=1").jjAjax2(false);
//
//    },
    emailRegex: function (email) {
        var emailPathern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return emailPathern.test(email);
    },
    validateEmail: function (obj) {
        var email = $(obj).val();
        if (!hmisCommettes.emailRegex(email)) {
//            $(obj).css('border', '1px solid red');
            $(obj).addClass('required');
            return false;
        } else {
//            $(obj).css('border', '1px solid #23BF08');
            $(obj).removeClass('required');
            return true;
        }

    },
    dateRegex: function (date) {
        var datePathern = /^$|^([1۱][۰-۹ 0-9]{3}[/\/]([0 ۰][۱-۶ 1-6])[/\/]([0 ۰][۱-۹ 1-9]|[۱۲12][۰-۹ 0-9]|[3۳][01۰۱])|[1۱][۰-۹ 0-9]{3}[/\/]([۰0][۷-۹ 7-9]|[1۱][۰۱۲012])[/\/]([۰0][1-9 ۱-۹]|[12۱۲][0-9 ۰-۹]|(30|۳۰)))$/; //تاریخ شمسی
        return datePathern.test(date);
    },
    validateDate: function (obj) {
        var date = $(obj).val();
        if (!hmisCommettes.dateRegex(date)) {
//            $(obj).css('border', '1px solid red');
            $(obj).addClass('required');
            return false;
        } else {
//            $(obj).css('border', '1px solid #23BF08');
            $(obj).removeClass('required');
            return true;
        }

    },
    /**
     * نام ونام خانوادگی  فرد فقط فارسی باشد
     * @param {type} str
     * @returns {Boolean}
     */
    regexName: function (str) {
        var regx = /^[\u0600-\u06FF\s]+$/;
        return regx.test(str);
    },
    mobileRegex: function (mobile) {
        var regx = /^(\+989|(09)|(9))([0-9]{9})$/;
        return regx.test(mobile);
    },
    validatePhone: function (obj) {
        var mobile = $(obj).val();
        if (!hmisCommettes.mobileRegex(mobile)) {
//            $(obj).css('border', '1px solid red');
            $(obj).addClass('required');
            return false;
        } else {
//            $(obj).css('border', '1px solid #23BF08');
            $(obj).removeClass('required');
            return true;
        }

    },
    validateName: function (obj) {
        var name = $(obj).val();
        if (!hmisCommettes.regexName(name)) {
            $(obj).addClass('required');
            return false;
        } else {
            $(obj).removeClass('required');
            return true;
        }

    },
    contextLimited: function (id) {
//        alert(id);
        var value = $("#" + id + "").val();
//        alert("/////////////////////////////"+value);
//        var length = (((value.trim()).replace(/[\s]+/g, ' ')).split(' '));

        if (value.length > 300) {
            alert('300 کاراکتر وارد نمایید');
            $("#" + id + "").addClass("required");
            return false;
        } else {
            $("#" + id + "").removeClass("required");
        }
    },
    /**
     * ارسال پیام و
     *  دعوت  همه افراد انتخاب شده
     *   برای برگزاری جلسه توسط کمیته
     * @returns {undefined}
     */
    sendComment: function (text, inviteesId, inviteesOutSideId, inviteesInSideId) {
        var param = "";
        param += "&textComment=" + text;
        param += "&inviteesIdComment=" + inviteesId;
        param += "&inviteesOutSideIdComment=" + inviteesOutSideId;
        param += "&inviteesInSideIdComment=" + inviteesInSideId;
        param += "&do=" + hmisCommettes.tableName + ".sendComment" + "&jj=1";
        new jj(param).jjAjax2(false);
    },
    /**id
     *ایدی رکورد موردنطر
     *
     *
     *
     *
     * @param {type} idUpload
     * @param {type} id
     * @param {type} hmisName
     
     */
//    m_remove: function (idUpload, id, nameFile) {
//        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisCommettes.removeFile(" + idUpload + "," + id + "," + nameFile + ");");
//    },
//    removeFile: function (idUpload, id, nameFile) {
//        var param = "";
//        param += "do=" + hmisCommettes.tableName + ".removeFile";
//        param += "&upload_id=" + idUpload;
//        param += "&id=" + id;
//        param += "&nameFile=" + nameFile;
//        new jj(param).jjAjax2(false);
////        hmisSessions.m_show_tbl();
////        hmisSessions.m_clean();
//    },
};
