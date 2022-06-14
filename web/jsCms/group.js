var cmsGroup = {
    tableName: "Access_Group",
    f_id: "id",
    f_group_id: "group_id",
    f_title: "group_title",
    f_des: "group_des",
    f_c: "group_c",
    loadForm: function () {
        if ($("#swGroupForm").html() == '') {
            $("#swGroupForm").load("formCms/group.html", null, function () {
                $("#cancel_Group").button().click(function (e) {
                    cmsGroup.m_clean();
                    cmsGroup.m_show_tbl();
                });
                cmsUser.getSelectOption("#swGroupForm #access_user_group_userId");
                $("#swGroupForm #access_user_group_userId").select2({
                    width: '100%'
                });
                addCheckboxFunction();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + cmsGroup.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swGroupTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? innerPanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        cmsGroup.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swGroupTbl').hide();
        $('#swGroupForm').show();
        cmsGroup.tabSizeForm();
        $("#swGroupForm #access_user_group_userId").select2({
            width: '100%'
        });
    },
    m_clean: function () {
        new jj("#" + cmsGroup.f_group_id).jjVal('');
        new jj("#" + cmsGroup.f_title).jjVal('');
        new jj("#" + cmsGroup.f_des).jjVal('');
        for (var i = 1; i < 501; i++) {//============ EDITED BY RASHIDI ========
            new jj("#C" + (i < 10 ? "0" + i : i)).jjVal('');
        }
    },
    m_add_new: function () {
        new jj("do=" + cmsGroup.tableName + ".add_new").jjAjax2(false);
        cmsGroup.m_show_form();
        cmsGroup.m_clean();

    },
    m_show_tbl: function () {
        $('#swGroupTbl').show();
        $('#swGroupForm').hide();

        if ($('#swGroupTbl').html() == "") {
            cmsGroup.m_refresh();
        }
        cmsGroup.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + cmsGroup.tableName + ".insert";
        param += "&" + cmsGroup.f_title + "=" + new jj("#" + cmsGroup.f_title).jjVal();
        param += "&" + cmsGroup.f_des + "=" + new jj("#" + cmsGroup.f_des).jjVal();
        for (var i = 1; i < 621; i++) { //============ EDITED BY RASHIDI ========
            //            new jj("#"+cmsGroup.f_c+(i<10?"0"+i:i)).jjVal('');
            var row = (i < 10 ? "0" + i : i);
            param += "&" + cmsGroup.f_c + row + "=" + new jj("#C" + row).jjVal();
        }
        new jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + cmsGroup.tableName + ".edit";
        param += "&" + cmsGroup.f_id + "=" + new jj("#" + cmsGroup.f_group_id).jjVal();
        param += "&" + cmsGroup.f_title + "=" + new jj("#" + cmsGroup.f_title).jjVal();
        param += "&" + cmsGroup.f_des + "=" + new jj("#" + cmsGroup.f_des).jjVal();
        for (var i = 1; i < 621; i++) {//============ EDITED BY RASHIDI ========
            //            new jj("#"+cmsGroup.f_c+(i<10?"0"+i:i)).jjVal('');
            var row = (i < 10 ? "0" + i : i);
            param += "&" + cmsGroup.f_c + row + "=" + new jj("#C" + row).jjVal();
        }
        new jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام سامانه", 'cmsGroup.m_delete_after_question(' + id + ');\n');
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + cmsGroup.tableName + ".delete";
        param += "&" + cmsGroup.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + cmsGroup.tableName + ".select";
        param += "&" + cmsGroup.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        cmsGroup.m_show_form();
        new jj("#" + cmsGroup.f_group_id).jjVal(id);
        new jj("#" + cmsGroup.f_id).jjVal(id);
    },
    tabSizeTbl: function () {
        $('#swAccessAll').css('height', 519);
        cmsGroup.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swAccessAll').css('height', 839);
        cmsGroup.heightTab = 829;
    },
    heightTab: "519",
    mainTabSetSize: function () {
        $('#swAccessAll').css('height', cmsGroup.heightTab);
    }
}
function addCheckboxFunction() {
    //کارتابل پیامها------------------------------------------------------
    //پیامهای خوانده نشده
    $("#C292").change(function (e) {
        if (new jj("#C292").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C293").change(function (e) {
        if (new jj("#C293").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C292").jjVal(true);
        }
    });
    $("#C294").change(function (e) {
        if (new jj("#C294").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C292").jjVal(true);
        }
    });
    $("#C295").change(function (e) {
        if (new jj("#C295").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C292").jjVal(true);
        }
    });
    $("#C296").change(function (e) {
        if (new jj("#C296").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C292").jjVal(true);
        }
    });
    //پیامهای دیده شده
    $("#C300").change(function (e) {
        if (new jj("#C300").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C301").change(function (e) {
        if (new jj("#C301").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C300").jjVal(true);
        }
    });
    $("#C302").change(function (e) {
        if (new jj("#C302").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C300").jjVal(true);
        }
    });
    $("#C303").change(function (e) {
        if (new jj("#C303").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C300").jjVal(true);
        }
    });
    $("#C304").change(function (e) {
        if (new jj("#C304").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C300").jjVal(true);
        }
    });
    //پیامهای من
    $("#C310").change(function (e) {
        if (new jj("#C310").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C311").change(function (e) {
        if (new jj("#C311").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C310").jjVal(true);
        }
    });
    $("#C312").change(function (e) {
        if (new jj("#C312").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C310").jjVal(true);
        }
    });
    $("#C313").change(function (e) {
        if (new jj("#C313").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C310").jjVal(true);
        }
    });
    $("#C314").change(function (e) {
        if (new jj("#C314").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C310").jjVal(true);
        }
    });
    //پیامها
    $("#C320").change(function (e) {
        if (new jj("#C320").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C321").change(function (e) {
        if (new jj("#C321").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C320").jjVal(true);
        }
    });
    $("#C322").change(function (e) {
        if (new jj("#C322").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C320").jjVal(true);
        }
    });
    $("#C323").change(function (e) {
        if (new jj("#C323").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C320").jjVal(true);
        }
    });
    $("#C324").change(function (e) {
        if (new jj("#C324").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C320").jjVal(true);
        }
    });
    //ایمیل
    $("#C330").change(function (e) {
        if (new jj("#C330").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C331").change(function (e) {
        if (new jj("#C331").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C330").jjVal(true);
        }
    });
    $("#C332").change(function (e) {
        if (new jj("#C332").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C330").jjVal(true);
        }
    });
    //پیامک
    $("#C335").change(function (e) {
        if (new jj("#C335").jjVal() == '1') {
            new jj("#C291").jjVal(true);
        }
    });
    $("#C336").change(function (e) {
        if (new jj("#C336").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C335").jjVal(true);
        }
    });
    $("#C337").change(function (e) {
        if (new jj("#C337").jjVal() == '1') {
            new jj("#C291").jjVal(true);
            new jj("#C335").jjVal(true);
        }
    });
    //------------------------------------------------------------------------------------
    //ماژول ها----------------------------------------------------------------------------------
    //مدیریت محتوای سازمانی
    $("#C271").change(function (e) {
        if (new jj("#C271").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C272").change(function (e) {
        if (new jj("#C272").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C271").jjVal(true);
        }
    });
    $("#C273").change(function (e) {
        if (new jj("#C273").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C271").jjVal(true);
        }
    });
    $("#C274").change(function (e) {
        if (new jj("#C274").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C271").jjVal(true);
        }
    });
    $("#C275").change(function (e) {
        if (new jj("#C275").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C271").jjVal(true);
        }
    });
    $("#C276").change(function (e) {
        if (new jj("#C276").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C271").jjVal(true);
        }
    });
    //مدیریت فایل
    $("#C280").change(function (e) {
        if (new jj("#C280").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C281").change(function (e) {
        if (new jj("#C281").jjVal() == '1') {
            new jj("#C280").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C282").change(function (e) {
        if (new jj("#C282").jjVal() == '1') {
            new jj("#C280").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C283").change(function (e) {
        if (new jj("#C283").jjVal() == '1') {
            new jj("#C280").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C284").change(function (e) {
        if (new jj("#C284").jjVal() == '1') {
            new jj("#C280").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    //دسته بندی فایل ها
    $("#C376").change(function (e) {
        if (new jj("#C376").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C377").change(function (e) {
        if (new jj("#C377").jjVal() == '1') {
            new jj("#C376").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C378").change(function (e) {
        if (new jj("#C378").jjVal() == '1') {
            new jj("#C376").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C379").change(function (e) {
        if (new jj("#C379").jjVal() == '1') {
            new jj("#C376").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C380").change(function (e) {
        if (new jj("#C380").jjVal() == '1') {
            new jj("#C376").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C285").change(function (e) {
        if (new jj("#C285").jjVal() == '1') {
            new jj("#C280").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    //دسته بندی تصاویر
    $("#C286").change(function (e) {
        if (new jj("#C286").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C287").change(function (e) {
        if (new jj("#C287").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C286").jjVal(true);
        }
    });
    $("#C288").change(function (e) {
        if (new jj("#C288").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C286").jjVal(true);
        }
    });
    $("#C289").change(function (e) {
        if (new jj("#C289").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C286").jjVal(true);
        }
    });
    $("#C290").change(function (e) {
        if (new jj("#C290").jjVal() == '1') {
            new jj("#C270").jjVal(true);
            new jj("#C286").jjVal(true);
        }
    });
    //اخبار سازمانی
    $("#C251").change(function (e) {
        if (new jj("#C251").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C252").change(function (e) {
        if (new jj("#C252").jjVal() == '1') {
            new jj("#C251").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C253").change(function (e) {
        if (new jj("#C253").jjVal() == '1') {
            new jj("#C251").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C254").change(function (e) {
        if (new jj("#C254").jjVal() == '1') {
            new jj("#C251").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C255").change(function (e) {
        if (new jj("#C255").jjVal() == '1') {
            new jj("#C251").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C256").change(function (e) {
        if (new jj("#C256").jjVal() == '1') {
            new jj("#C251").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    //دسته بندی اخبار سازمانی
    $("#C261").change(function (e) {
        if (new jj("#C261").jjVal() == '1') {
            new jj("#C270").jjVal(true);
        }
    });
    $("#C262").change(function (e) {
        if (new jj("#C262").jjVal() == '1') {
            new jj("#C261").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C263").change(function (e) {
        if (new jj("#C263").jjVal() == '1') {
            new jj("#C261").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C264").change(function (e) {
        if (new jj("#C264").jjVal() == '1') {
            new jj("#C261").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    $("#C265").change(function (e) {
        if (new jj("#C265").jjVal() == '1') {
            new jj("#C261").jjVal(true);
            new jj("#C270").jjVal(true);
        }
    });
    //برنامه استراتژیک
    $("#C341").change(function (e) {
        if (new jj("#C341").jjVal() == '1') {
            new jj("#C340").jjVal(true);
        }
    });
    $("#C342").change(function (e) {
        if (new jj("#C342").jjVal() == '1') {
            new jj("#C340").jjVal(true);
        }
    });
    $("#C343").change(function (e) {
        if (new jj("#C343").jjVal() == '1') {
            new jj("#C340").jjVal(true);
        }
    });
    $("#C344").change(function (e) {
        if (new jj("#C344").jjVal() == '1') {
            new jj("#C340").jjVal(true);
        }
    });
    //فرمها
    $("#C348").change(function (e) {
        if (new jj("#C348").jjVal() == '1') {
            new jj("#C347").jjVal(true);
        }
    });
    $("#C349").change(function (e) {
        if (new jj("#349").jjVal() == '1') {
            new jj("#C347").jjVal(true);
            new jj("#C348").jjVal(true);
        }
    });
    $("#C350").change(function (e) {
        if (new jj("#C350").jjVal() == '1') {
            new jj("#C347").jjVal(true);
            new jj("#C348").jjVal(true);
        }
    });
    $("#C351").change(function (e) {
        if (new jj("#C351").jjVal() == '1') {
            new jj("#C347").jjVal(true);
            new jj("#C348").jjVal(true);
        }
    });
    $("#C352").change(function (e) {
        if (new jj("#C352").jjVal() == '1') {
            new jj("#C347").jjVal(true);
            new jj("#C348").jjVal(true);
        }
    });
    //ارشیو فرمهای تکمیل شده
    $("#C355").change(function (e) {
        if (new jj("#C355").jjVal() == '1') {
            new jj("#C347").jjVal(true);
        }
    });
    //تکمیل فرمهای من
    $("#C361").change(function (e) {
        if (new jj("#C361").jjVal() == '1') {
            new jj("#C347").jjVal(true);
        }
    });
    //ارشیو فرم های تکمیل شده من
    $("#C368").change(function (e) {
        if (new jj("#C368").jjVal() == '1') {
            new jj("#C347").jjVal(true);
        }
    });


    //پروژه ها
    $("#C382").change(function (e) {
        if (new jj("#C382").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
    $("#C383").change(function (e) {
        if (new jj("#C383").jjVal() == '1') {
            new jj("#C382").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C384").change(function (e) {
        if (new jj("#C384").jjVal() == '1') {
            new jj("#C382").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C385").change(function (e) {
        if (new jj("#C385").jjVal() == '1') {
            new jj("#C382").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C386").change(function (e) {
        if (new jj("#C386").jjVal() == '1') {
            new jj("#C382").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
//    دسته بندی پروژه ها
    $("#C387").change(function (e) {
        if (new jj("#C387").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
    $("#C388").change(function (e) {
        if (new jj("#C388").jjVal() == '1') {
            new jj("#C387").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C389").change(function (e) {
        if (new jj("#C389").jjVal() == '1') {
            new jj("#C387").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C390").change(function (e) {
        if (new jj("#C390").jjVal() == '1') {
            new jj("#C387").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C391").change(function (e) {
        if (new jj("#C391").jjVal() == '1') {
            new jj("#C387").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
//    برچسب ها
    $("#C395").change(function (e) {
        if (new jj("#C395").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
    $("#C396").change(function (e) {
        if (new jj("#C396").jjVal() == '1') {
            new jj("#C395").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C397").change(function (e) {
        if (new jj("#C397").jjVal() == '1') {
            new jj("#C395").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C398").change(function (e) {
        if (new jj("#C398").jjVal() == '1') {
            new jj("#C395").jjVal(true);
            new jj("#C381").jjVal(true);
        }
    });
    $("#C399").change(function (e) {
        if (new jj("#C399").jjVal() == '1') {
            new jj("#C381").jjVal(true);
            new jj("#C395").jjVal(true);
        }
    });
//    $("#C399").change(function (e) {
//        if (new jj("#C399").jjVal() == '1') {
//            new jj("#C375").jjVal(true);
//            new jj("#C397").jjVal(true);
//        }
//    });
//فاکتورها
    $("#C405").change(function (e) {
        if (new jj("#C405").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
//    پرداخت ها
    $("#C410").change(function (e) {
        if (new jj("#C410").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
//    ایتم های سفارش داده شده
    $("#C415").change(function (e) {
        if (new jj("#C415").jjVal() == '1') {
            new jj("#C381").jjVal(true);
        }
    });
    $("#C401").change(function (e) {
        if (new jj("#C401").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C397").jjVal(true);
        }
    });
    //گامهای اجرایی
    $("#C404").change(function (e) {
        if (new jj("#C404").jjVal() == '1') {
            new jj("#C375").jjVal(true);
        }
    });
//    $("#C405").change(function (e) {
//        if (new jj("#C405").jjVal() == '1') {
//            new jj("#C375").jjVal(true);
//            new jj("#C404").jjVal(true);
//        }
//    });
    $("#C406").change(function (e) {
        if (new jj("#C406").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C404").jjVal(true);
        }
    });
    $("#C407").change(function (e) {
        if (new jj("#C407").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C404").jjVal(true);
        }
    });
    $("#C408").change(function (e) {
        if (new jj("#C408").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C404").jjVal(true);
        }
    });
    $("#C409").change(function (e) {
        if (new jj("#C409").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C404").jjVal(true);
        }
    });
    //گامهای اجرایی من
    $("#C411").change(function (e) {
        if (new jj("#C411").jjVal() == '1') {
            new jj("#C375").jjVal(true);
        }
    });
    $("#C412").change(function (e) {
        if (new jj("#C412").jjVal() == '1') {
            new jj("#C375").jjVal(true);
            new jj("#C411").jjVal(true);
        }
    });
    //گزارشهای برنامه ها
    $("#C418").change(function (e) {
        if (new jj("#C418").jjVal() == '1') {
            new jj("#C375").jjVal(true);
        }
    });
    //تعریف و تنظیمات کمیته ها
    $("#C426").change(function (e) {
        if (new jj("#C426").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    $("#C427").change(function (e) {
        if (new jj("#C427").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C426").jjVal(true);
        }
    });
    $("#C428").change(function (e) {
        if (new jj("#C428").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C426").jjVal(true);
        }
    });
    $("#C429").change(function (e) {
        if (new jj("#C429").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C426").jjVal(true);
        }
    });
    //میز هوشمند جلسات
    $("#C433").change(function (e) {
        if (new jj("#C433").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    $("#C434").change(function (e) {
        if (new jj("#C434").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C433").jjVal(true);
        }
    });
    $("#C435").change(function (e) {
        if (new jj("#C435").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C433").jjVal(true);
        }
    });
    $("#C436").change(function (e) {
        if (new jj("#C436").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C433").jjVal(true);
        }
    });
    $("#C437").change(function (e) {
        if (new jj("#C437").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C433").jjVal(true);
        }
    });
    //امضای صورت جلسات
    $("#C440").change(function (e) {
        if (new jj("#C440").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    //ارشیو صورت جلسات
    $("#C447").change(function (e) {
        if (new jj("#C447").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    //پایش مصوبات
    $("#C454").change(function (e) {
        if (new jj("#C454").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    $("#C455").change(function (e) {
        if (new jj("#C455").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C456").change(function (e) {
        if (new jj("#C456").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C457").change(function (e) {
        if (new jj("#C457").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C458").change(function (e) {
        if (new jj("#C458").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C459").change(function (e) {
        if (new jj("#C459").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C460").change(function (e) {
        if (new jj("#C460").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C452").change(function (e) {
        if (new jj("#C452").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    $("#C453").change(function (e) {
        if (new jj("#C453").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C454").jjVal(true);
        }
    });
    //ابلاغ صورتجلسه
    $("#C461").change(function (e) {
        if (new jj("#C461").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    $("#C462").change(function (e) {
        if (new jj("#C462").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C461").jjVal(true);
        }
    });
    //شاخص های کمیته
    $("#C468").change(function (e) {
        if (new jj("#C468").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    //ارزیابی کمیته ها
    $("#C475").change(function (e) {
        if (new jj("#C475").jjVal() == '1') {
            new jj("#C425").jjVal(true);
        }
    });
    $("#C476").change(function (e) {
        if (new jj("#C476").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C475").jjVal(true);
        }
    });
    $("#C477").change(function (e) {
        if (new jj("#C477").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C475").jjVal(true);
        }
    });
    $("#C478").change(function (e) {
        if (new jj("#C478").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C475").jjVal(true);
        }
    });
    $("#C479").change(function (e) {
        if (new jj("#C479").jjVal() == '1') {
            new jj("#C425").jjVal(true);
            new jj("#C475").jjVal(true);
        }
    });
    //شاخص ها
    $("#C482").change(function (e) {
        if (new jj("#C482").jjVal() == '1') {
            new jj("#C481").jjVal(true);
        }
    });
    $("#C483").change(function (e) {
        if (new jj("#C483").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C482").jjVal(true);
        }
    });
    $("#C484").change(function (e) {
        if (new jj("#C484").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C482").jjVal(true);
        }
    });
    $("#C485").change(function (e) {
        if (new jj("#C485").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C482").jjVal(true);
        }
    });
    $("#C486").change(function (e) {
        if (new jj("#C486").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C482").jjVal(true);
        }
    });
    //گزارشات و تنظیمات شاخص ها
    $("#C489").change(function (e) {
        if (new jj("#C489").jjVal() == '1') {
            new jj("#C481").jjVal(true);
        }
    });
    $("#C490").change(function (e) {
        if (new jj("#C490").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C489").jjVal(true);
        }
    });
    $("#C491").change(function (e) {
        if (new jj("#C491").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C489").jjVal(true);
        }
    });
    $("#C492").change(function (e) {
        if (new jj("#C492").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C489").jjVal(true);
        }
    });
    $("#C493").change(function (e) {
        if (new jj("#C493").jjVal() == '1') {
            new jj("#C481").jjVal(true);
            new jj("#C489").jjVal(true);
        }
    });
    //مدیریت سنجه ها
    $("#C497").change(function (e) {
        if (new jj("#C497").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C498").change(function (e) {
        if (new jj("#C498").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C499").change(function (e) {
        if (new jj("#C499").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C500").change(function (e) {
        if (new jj("#C500").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C501").change(function (e) {
        if (new jj("#C501").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    //بارگزاری مستندات من
    $("#C504").change(function (e) {
        if (new jj("#C504").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C512").jjVal(true);
            new jj("#C513").jjVal(true);
            new jj("#C514").jjVal(true);
        }
    });
    //ارشیو مستندات سنجه ها و استانداردها
    $("#C511").change(function (e) {
        if (new jj("#C511").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C512").change(function (e) {
        if (new jj("#C512").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C511").jjVal(true);
        }
    });
    $("#C513").change(function (e) {
        if (new jj("#C513").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C511").jjVal(true);
        }
    });
    $("#C514").change(function (e) {
        if (new jj("#C514").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C511").jjVal(true);
        }
    });
    //ایجاد مستند
    $("#C518").change(function (e) {
        if (new jj("#C518").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C519").change(function (e) {
        if (new jj("#C519").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C518").jjVal(true);
        }
    });
    $("#C520").change(function (e) {
        if (new jj("#C520").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C518").jjVal(true);
        }
    });
    $("#C521").change(function (e) {
        if (new jj("#C521").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C518").jjVal(true);
        }
    });
    //امضا و تایید مستندات من
    $("#C525").change(function (e) {
        if (new jj("#C525").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    //ابلاغ مستندات
    $("#C532").change(function (e) {
        if (new jj("#C532").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C533").change(function (e) {
        if (new jj("#C533").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    //مشاهده مستندات
    $("#C539").change(function (e) {
        if (new jj("#C539").jjVal() == '1') {
            new jj("#C496").jjVal(true);
        }
    });
    $("#C540").change(function (e) {
        if (new jj("#C540").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C539").jjVal(true);
        }
    });
    $("#C541").change(function (e) {
        if (new jj("#C541").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C539").jjVal(true);
        }
    });
    $("#C542").change(function (e) {
        if (new jj("#C542").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C539").jjVal(true);
        }
    });
    $("#C543").change(function (e) {
        if (new jj("#C543").jjVal() == '1') {
            new jj("#C496").jjVal(true);
            new jj("#C539").jjVal(true);
        }
    });
    //مدیریت فرم های بارش افکار
    $("#C547").change(function (e) {
        if (new jj("#C547").jjVal() == '1') {
            new jj("#C546").jjVal(true);
        }
    });
    $("#C548").change(function (e) {
        if (new jj("#C548").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C547").jjVal(true);
        }
    });
    $("#C549").change(function (e) {
        if (new jj("#C549").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C547").jjVal(true);
        }
    });
    $("#C550").change(function (e) {
        if (new jj("#C550").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C547").jjVal(true);
        }
    });
    $("#C551").change(function (e) {
        if (new jj("#C551").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C547").jjVal(true);
        }
    });
    //تکمیل فرم بارش افکار
    $("#C554").change(function (e) {
        if (new jj("#C554").jjVal() == '1') {
            new jj("#C546").jjVal(true);
        }
    });
    $("#C555").change(function (e) {
        if (new jj("#C555").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C554").jjVal(true);
        }
    });
    $("#C556").change(function (e) {
        if (new jj("#C556").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C554").jjVal(true);
        }
    });
    $("#C557").change(function (e) {
        if (new jj("#C557").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C554").jjVal(true);
        }
    });
    $("#C558").change(function (e) {
        if (new jj("#C558").jjVal() == '1') {
            new jj("#C546").jjVal(true);
            new jj("#C554").jjVal(true);
        }
    });
    //مستندات و حدول های امتیاز دهی
    $("#C562").change(function (e) {
        if (new jj("#C562").jjVal() == '1') {
            new jj("#C561").jjVal(true);
        }
    });
    $("#C563").change(function (e) {
        if (new jj("#C563").jjVal() == '1') {
            new jj("#C561").jjVal(true);
            new jj("#C562").jjVal(true);
        }
    });
    $("#C564").change(function (e) {
        if (new jj("#C564").jjVal() == '1') {
            new jj("#C561").jjVal(true);
            new jj("#C562").jjVal(true);
        }
    });
    $("#C565").change(function (e) {
        if (new jj("#C565").jjVal() == '1') {
            new jj("#C561").jjVal(true);
            new jj("#C562").jjVal(true);
        }
    });
    $("#C566").change(function (e) {
        if (new jj("#C566").jjVal() == '1') {
            new jj("#C561").jjVal(true);
            new jj("#C562").jjVal(true);
        }
    });
    //ماژول مدیریت بحران و حوادثٍٍ
    $("#C571").change(function (e) {
        if (new jj("#C571").jjVal() == '1') {
            new jj("#C570").jjVal(true);
        }
    });
    $("#C572").change(function (e) {
        if (new jj("#C572").jjVal() == '1') {
            new jj("#C570").jjVal(true);
            new jj("#C571").jjVal(true);
        }
    });
    $("#C573").change(function (e) {
        if (new jj("#C573").jjVal() == '1') {
            new jj("#C570").jjVal(true);
            new jj("#C571").jjVal(true);
        }
    });
    $("#C574").change(function (e) {
        if (new jj("#C574").jjVal() == '1') {
            new jj("#C570").jjVal(true);
            new jj("#C571").jjVal(true);
        }
    });
    $("#C575").change(function (e) {
        if (new jj("#C575").jjVal() == '1') {
            new jj("#C570").jjVal(true);
            new jj("#C571").jjVal(true);
        }
    });
    //مدل بهبود کیفیت FOCUS PDCA
    $("#C581").change(function (e) {
        if (new jj("#C581").jjVal() == '1') {
            new jj("#C580").jjVal(true);
        }
    });
    $("#C582").change(function (e) {
        if (new jj("#C582").jjVal() == '1') {
            new jj("#C580").jjVal(true);
            new jj("#C581").jjVal(true);
        }
    });
    $("#C583").change(function (e) {
        if (new jj("#C583").jjVal() == '1') {
            new jj("#C580").jjVal(true);
            new jj("#C581").jjVal(true);
        }
    });
    $("#C584").change(function (e) {
        if (new jj("#C584").jjVal() == '1') {
            new jj("#C580").jjVal(true);
            new jj("#C581").jjVal(true);
        }
    });
    $("#C585").change(function (e) {
        if (new jj("#C585").jjVal() == '1') {
            new jj("#C580").jjVal(true);
            new jj("#C581").jjVal(true);
        }
    });
    //تنظیمات
    $("#C611").change(function (e) {
        if (new jj("#C611").jjVal() == '1') {
            new jj("#C610").jjVal(true);
        }
    });
    //---------------------------------------------------------------------------------------------
//    سطح دسترسی----------------------------------------------------------------------------------------
// مدیریت بخش ها
    $("#C131").change(function (e) {
        if (new jj("#C131").jjVal() == '1') {
            new jj("#C130").jjVal(true);
        }
    });
    $("#C132").change(function (e) {
        if (new jj("#C132").jjVal() == '1') {
            new jj("#C131").jjVal(true);
            new jj("#C130").jjVal(true);
        }
    });
    $("#C133").change(function (e) {
        if (new jj("#C133").jjVal() == '1') {
            new jj("#C131").jjVal(true);
            new jj("#C130").jjVal(true);
        }
    });
    $("#C134").change(function (e) {
        if (new jj("#C134").jjVal() == '1') {
            new jj("#C131").jjVal(true);
            new jj("#C130").jjVal(true);
        }
    });
    $("#C135").change(function (e) {
        if (new jj("#C135").jjVal() == '1') {
            new jj("#C131").jjVal(true);
            new jj("#C130").jjVal(true);
        }
    });
    // مدیریت نقش ها
    $("#C141").change(function (e) {
        if (new jj("#C141").jjVal() == '1') {
            new jj("#C130").jjVal(true);
        }
    });
    $("#C142").change(function (e) {
        if (new jj("#C142").jjVal() == '1') {
            new jj("#C141").jjVal(true);
            new jj("#C130").jjVal(true);
        }
    });
    $("#C143").change(function (e) {
        if (new jj("#C143").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C141").jjVal(true);
        }
    });
    $("#C144").change(function (e) {
        if (new jj("#C144").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C141").jjVal(true);
        }
    });
    $("#C145").change(function (e) {
        if (new jj("#C145").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C141").jjVal(true);
        }
    });
    //مدیریت کاربران
    $("#C150").change(function (e) {
        if (new jj("#C150").jjVal() == '1') {
            new jj("#C130").jjVal(true);
        }
    });
    $("#C151").change(function (e) {
        if (new jj("#C151").jjVal() == '1') {
            new jj("#C130").jjVal(true);
        }
    });
    $("#C152").change(function (e) {
        if (new jj("#C152").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C151").jjVal(true);
        }
    });
    $("#C153").change(function (e) {
        if (new jj("#C153").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C151").jjVal(true);
        }
    });
    $("#C154").change(function (e) {
        if (new jj("#C154").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C151").jjVal(true);
        }
    });
    //مدیریت گروه ها
    $("#C161").change(function (e) {
        if (new jj("#C161").jjVal() == '1') {
            new jj("#C130").jjVal(true);
        }
    });
    $("#C162").change(function (e) {
        if (new jj("#C162").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C161").jjVal(true);
        }
    });
    $("#C163").change(function (e) {
        if (new jj("#C163").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C161").jjVal(true);
        }
    });
    $("#C164").change(function (e) {
        if (new jj("#C164").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C161").jjVal(true);
        }
    });
    $("#C165").change(function (e) {
        if (new jj("#C165").jjVal() == '1') {
            new jj("#C130").jjVal(true);
            new jj("#C161").jjVal(true);
        }
    });
}
