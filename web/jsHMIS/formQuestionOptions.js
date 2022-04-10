/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisFormQuestionOptions = {
    tableName: "FormQuestionOptions",
    f_id: "id",
    /**
     * 
     * @param {type} formId آی دی فرم حتما باید باشد
     * @param {type} containerId
     * @returns {undefined}
     */
    m_refresh: function (questionId, containerId) {
        if (new jj(questionId).jjIsDigit()) {// اگر آی دی سوال موجود نبود گزینه ها را نشان نمی دهیم
            var param = "";
            param += "do=" + hmisFormQuestionOptions.tableName + ".refresh";
            param += "&panel=" + (containerId == null ? "swFormQuestionOptionsTbl" : containerId);
            param += "&formQuestionOptions_question_id=" + questionId;
            param += "&jj=1";
            new jj(param).jjAjax2(false);
            hmisFormQuestionOptions.m_show_tbl();
        } else {
            return;
        }
    },
    m_show_form: function () {
        $('#swFormQuestionOptionsTbl').slideUp('slow');
        $('#swFormQuestionOptionsForm').slideDown('slow');
        hmisFormQuestionOptions.tabSizeForm();
        hmisFormQuestionOptions.m_clean();
    },
    m_clean: function () {
        new jj("#swFormQuestionOptionsForm").jjFormClean();
        $("#formQuestionOptions_value").val("");

    },
    m_add_new: function () {
        new jj("do=" + hmisFormQuestionOptions.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisFormQuestionOptions.m_show_form();
        hmisFormQuestionOptions.m_clean();

    },
    m_show_tbl: function () {
        $('#swFormQuestionOptionsTbl').slideDown('slow');
        $('#swFormQuestionOptionsForm').slideUp('slow');
    },
    /**
     * ایجاد سوال بخش با گزینه هایش 
     * @returns {undefined}
     */
    createDepartmentQuestion: function (questionId) {
        new jj("آیا از ایجاد سوال بخش با گزینه هاش اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', 'hmisFormQuestionOptions.createDepartmentQuestion_after_question(' + questionId + ');\n');
    },
    createDepartmentQuestion_after_question: function (questionId) {
        var param = "";
        param += "&hmis_formquestions_id=" + questionId;
        param += "&do=" + hmisFormQuestionOptions.tableName + ".insertDepartmentQuestionOptions";
        new jj(param).jjAjax2(false);
    },
    /**
     * ایجاد کاربران  بخش
     * 
     * @param {type} questionId
     * @returns {undefined}
     */
    createUserDepartment: function (questionId) {
        if($('#forms_departments').val()!=""){
        new jj("آیا کاربران بخش  مورد نظر اضافه شوند؟").jjModal_Yes_No('پیام هشدار', 'hmisFormQuestionOptions.createUserDepartment_after_question(' + questionId + ');\n');
        }else{
            $("html, body").delay(1000).animate({scrollTop: $('#fromDetail').offset().top}, 800);
             new jj("باید بخش مورد نظر در فرم را انتخاب کنید.").jjModal('پیام هشدار');
        }
   
    },
    createUserDepartment_after_question: function (questionId) {
        var param = "";
        param += "&hmis_formquestions_id=" + questionId;
        param += "&do=" + hmisFormQuestionOptions.tableName + ".insertUserDepartmentQuestionOptions";
        new jj(param).jjAjax2(false);
    },
    m_insert: function () {
//        var valid =  hmisFormQuestionOptions.m_validation();
//        if (valid == "") {        
        var param = "";
        param += "do=" + hmisFormQuestionOptions.tableName + ".insert";
        param += "&" + new jj('#swFormQuestionOptionsForm').jjSerial();
        param += "&formQuestionOptions_question_id=" + $("#hmis_formquestions_id").val();
        new jj(param).jjAjax2(false);
   
    },
    m_edit: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestionOptions.tableName + ".edit";
        param += "&" + new jj('#swFormQuestionOptionsForm').jjSerial();
        new jj(param).jjAjax2(false);

    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', 'hmisFormQuestionOptions.m_delete_after_question(' + id + ');\n');

    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestionOptions.tableName + ".delete";
        param += "&" + hmisFormQuestionOptions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFormQuestionOptions.m_show_tbl();
        hmisFormQuestionOptions.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisFormQuestionOptions.tableName + ".select";
        param += "&" + hmisForms.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFormQuestionOptions.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisFormQuestionOptions.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swForms').css('height', "auto");
    },
    tabSizeForm: function () {
        $('#swCommettes').css('height', 'auto');
    }
};