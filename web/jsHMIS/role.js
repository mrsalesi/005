var hmisRole = {
    tableName: "Role",
    f_id: "id",

    loadForm: function () {
        if ($("#swRoleForm").html() == '') {
            $("#swRoleForm").load("formHMIS/role.html", null, function () {
                new jj('#role_date').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_Role").button().click(function (e) {
                    hmisRole.m_clean();
                    hmisRole.m_show_tbl();
                });
                cmsUser.getSelectOptionNotAll("#role_user_id");// برای قرار گرفتن سلکت آپشن نقش ها در قسمت های مربوطه
                $("#role_user_id").select2({
                    width: '100%' 
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisRole.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swRoleTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisRole.tabSizeTbl();
    },
    bazgasht: function () {
        hmisRole.m_clean();
        hmisRole.m_show_tbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisRole.tableName + ".add_new").jjAjax2(false);
        hmisRole.m_show_form();
        $('.summernote').summernote({height: 150,tooltip: false});///برای تبدیل شدن به textEditor
        $("#ListKarbaran").show();
        $("#ListKarbaranDarSelect").hide();
        $("#role_name").val('');
        $("#role_family").val('');
        $("#role_email").val('');
        $("#role_condition").val('');
         $('#role_comment').summernote('code', '');
//        $("#role_condition").val('');
//        hmisRole.m_clean();

//        part_content_editor = new jj('#department_publicContent').jjEditor();
//        part_praivate_editor = new jj('#department_praivateContent').jjEditor();
//        cmsUser.m_getGroups();
    },
    m_show_form: function () {
        $('#swRoleTbl').hide();
        $('#swRoleForm').show();
        hmisRole.m_clean();
        hmisRole.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swRoleForm").jjFormClean();
        new jj("#role_condition").jjVal("1");

    },
    m_show_tbl: function () {
        $('#swRoleTbl').show();
        $('#swRoleForm').hide();
        if ($('#swRoleTbl').html() == "") {
            cmsUser.m_refresh();
        }
        hmisRole.m_refresh();
        hmisRole.tabSizeTbl();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + hmisRole.tableName + ".insert";
        param += "&" + new jj("#swRoleForm").jjSerial();
//         param += "&role_condition=" + $('#role_condition1 input:radio[name=role_condition]:checked').val();
        param += "&role_comment=" + $('#role_comment').summernote('code');
        new jj(param).jjAjax2(false);
        hmisRole.m_show_tbl();
        hmisRole.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisRole.tableName + ".edit";
        param += "&" + new jj("#swRoleForm").jjSerial();
         param += "&role_comment=" + $('#role_comment').summernote('code');
        new jj(param).jjAjax2(false);
        hmisRole.m_show_tbl();
        hmisRole.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisRole.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisRole.tableName + ".delete";
        param += "&" + hmisRole.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisRole.m_show_tbl();
        hmisRole.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisRole.tableName + ".select";
        param += "&" + hmisRole.f_id + "=" + (id == null ? "" : id);
        $('.summernote').summernote({height: 150,tooltip: false});
        new jj(param).jjAjax2(false);
        $("#ListKarbaran").hide();       
        $("#ListKarbaranDarSelect").show();
        hmisRole.m_show_form();
    },
    m_selectKarbar: function (id) {
        var param = "";
        param += "do=" + hmisRole.tableName + ".selectKarbar";
        param += "&" + hmisRole.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
/**
 *  قراردادن نقش ها در سلکت آپشن ها
 * @param {type} selector میتوانیم هر سلکتوری بدهیم مثلا سلکتور کلاس بدهیم
 * @returns {undefined}
 */
    getSelectOption: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisRole.tableName + ".getSelectOption";
        new jj(param).jjAjax2(false);
    },
/**
 *  قراردادن نقش ها در سلکت آپشن ها
 * @param {type} selector میتوانیم هر سلکتوری بدهیم مثلا سلکتور کلاس بدهیم
 * @returns {undefined}
 */
    getSelectOptionRequierd: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisRole.tableName + ".getSelectOptionRequierd";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swRole').css('height', 'auto');
        hmisRole.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swRole').css('height', 'auto');
        hmisRole.heightTab = 'auto';
    },
    heightTab: "515",
    mainTabSetSize: function () {
        $('#swRole').css('height', hmisRole.heightTab);
    },
    /**
     * عنوان نقش کاربر با آی دی مشخص در یک تکست فیلد
     * @param {type} userId آی دی کاربر
     * @param {type} panel آی دی تکست فیلد
     * @returns {undefined}
     */
    getRoleName: function (userId,panel) {
        var param = "";
       param += "panel=" + panel;
       param += "&userId=" +userId; 
       param += "&do=" +hmisRole.tableName + ".getRoleName";
        new jj(param).jjAjax2(false);
    },
    
  /**
 *  قراردادن نقش های این کاربر جاری در سشن را در سلکت آگشن
 *  @param {type} selector میتوانیم کلاس بدهیم
 * @returns {undefined}
 * @Example: cmsUser.getSelectOption("#userform .usersSelectOption");
 */
    getUeserRolesSelectOption: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + hmisRole.tableName + ".getUeserRolesSelectOption";
        new jj(param).jjAjax2(false);
    },
  /**
 *  قراردادن نقش های یک کاربر  در سلکت آپشن
 *  @param {type} selector میتوانیم کلاس بدهیم
 * @returns {undefined}
 * @Example: cmsUser.getSelectOption("#userform .usersSelectOption");
 */
    getUeserRolesSelectOptionByUserId: function (userId,selector) {
        var param = "";
        param += "role_user_id=" + userId;
        param += "&panel=" + selector;
        param += "&do=" + hmisRole.tableName + ".getUeserRolesSelectOptionByUserId";
        new jj(param).jjAjax2(false);
    }

};



