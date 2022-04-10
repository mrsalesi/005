/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


////جایگاه ها

var hmisPositions = {
    tableName: "Positions",
    f_id: "id",
    loadForm: function () {
        if ($("#swPositionsForm").html() == '') {
//        if (true) {
            $("#swPositionsForm").load("formHMIS/positions.html", null, function () {
//                new jj('#documentary_LoadingPeriod').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_Positions").button().click(function (e) {

                    hmisPositions.m_clean();
                    hmisPositions.m_show_tbl();

                });
                hmisPositions.getoptionPositions("positions_parent");
                $("#positions_parent").select2({
                    width: '100%'
                });
                hmisPositions.m_getUserPosition("positions_user_id1");
                $("#positions_user_id1").select2({
                    width: '100%'
                });
                hmisPositions.m_getUserPosition("positions_user_id2");
                $("#positions_user_id2").select2({
                    width: '100%'
                });
                hmisPositions.m_getUserPosition("positions_user_id3");
                $("#positions_user_id3").select2({
                    width: '100%'
                });
                hmisPositions.m_getUserPosition("positions_user_id4");
                $("#positions_user_id4").select2({
                    width: '100%'
                });

//            new jj('#sendFiles').jjAjaxFileUploadTitleUploadFiles('#attachFileDocumentary', '#documentary_attachFileDocumentary','documentary_titleFile1','#documentary_attachFileTitle1');


            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisPositions.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swPositionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisPositions.tabSizeTbl();
    },
    m_add_new: function () {
        new jj("do=" + hmisPositions.tableName + ".add_new").jjAjax2(false);
        hmisPositions.m_show_form();
        $("#ListKarbaranPositions").show();
        $("#ListKarbaranDarSelectPositions").hide();



        hmisPositions.m_clean();


    },
    m_getUserPosition: function (selector) {
        var param = "";
        param += "panel=" + selector;
        param += "&do=" + hmisPositions.tableName + ".getUserPosition";
        new jj(param).jjAjax2(false);
    },
    sendMesseageAlarm: function (userId, MessageAlarm) {
        var param = "";
        param += "&UserId=" + userId;
        param += "&MessageAlarm=" + MessageAlarm;
        param += "&do=" + hmisPositions.tableName + ".sendMesseageAlarm";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    selectPositions: function () {
        var param = "";
//        param += "id=" + id;

        param += "&do=" + hmisPositions.tableName + ".selectPositions";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
        hmisPositions.getoptionPositions("positions_parent");
        hmisPositions.m_show_form();

        hmisPositions.m_clean();


//        hmisSignDocumentary.m_clean();
    },
//    changeSelectToInputPositions: function () {
//        if ($('#positions').css('display') == 'none') {
//            $('#positions').show();
//            $('#positions_subcategory').attr('name', 'positions_subcategory');
//            $('#positions_subcategory1').hide();
//            $('#positions_subcategory1').attr('name', 'positions_subcategory1');
//        } else {
//            $('#positions').hide();
//            $('#positions_subcategory').attr('name', 'positions_subcategory1');
//            $('#positions_subcategory1').show();
//            $('#positions_subcategory1').attr('name', 'positions_subcategory');
//        }
//
//
//
////
//    },
    getoptionPositions: function (panel) {
        var param = "";
        param += "panel=" + panel;
        param += "&do=" + hmisPositions.tableName + ".getoptionPositions";

        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swPositionsTbl').hide();
        $('#swPositionsForm').show();
        new jj("#swPositionsForm").jjFormClean();

        hmisPositions.tabSizeForm();
    },
    m_show_formCopy: function () {
        $('#swPositionsTbl').hide();
        $('#swPositionsForm').show();


        hmisPositions.tabSizeForm();
    },
    m_clean: function () {
        $("#positions_user_id1").val(0);
        $("#positions_user_id1").select2();
        $("#positions_user_id2").val(0);
        $("#positions_user_id2").select2();
        $("#positions_user_id3").val(0);
        $("#positions_user_id3").select2();
        $("#positions_user_id4").val(0);
        $("#positions_user_id4").select2();
        new jj("#swPositionsForm").jjFormClean();

    },
    m_show_tbl: function () {
        $('#swPositionsTbl').show();
        $('#swPositionsForm').hide();
        if ($('#swPositionsTbl').html() == "") {
            hmisPositions.m_refresh();
        }
        hmisPositions.m_refresh();
        hmisPositions.tabSizeTbl();

    },
    m_insert: function () {
        var userId = "";
        userId += $("#positions_user_id1").val();
        userId += "," + $("#positions_user_id2").val();
        userId += "," + $("#positions_user_id3").val();
        userId += "," + $("#positions_user_id4").val();
        var param = "";
        param += "do=" + hmisPositions.tableName + ".insert";
        param += "&positions_user_id=" + userId;
        param += "&" + new jj("#swPositionsForm").jjSerial();
        new jj(param).jjAjax2(false);
        hmisPositions.getoptionPositions("positions_parent");
        hmisPositions.m_show_tbl();
        hmisPositions.m_clean();
    },
    m_edit: function () {
        var userId = "";
        userId += $("#positions_user_id1").val();
        userId += "," + $("#positions_user_id2").val();
        userId += "," + $("#positions_user_id3").val();
        userId += "," + $("#positions_user_id4").val();
        var param = "";
        param += "do=" + hmisPositions.tableName + ".edit";
        param += "&positions_user_id=" + userId;
        param += "&" + new jj("#swPositionsForm").jjSerial();
        new jj(param).jjAjax2(false);
        hmisPositions.getoptionPositions("positions_parent");
        hmisPositions.m_show_tbl();
        hmisPositions.m_clean();

    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('hmisPositions.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisPositions.tableName + ".delete";
        param += "&" + hmisPositions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPositions.m_show_tbl();
        hmisPositions.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisPositions.tableName + ".select";
        param += "&" + hmisPositions.f_id + "=" + (id == null ? "" : id);
        $("#ListKarbaranPositions").hide();
        $("#ListKarbaranDarSelectPositions").show();
        new jj(param).jjAjax2(false);



        hmisPositions.m_show_form();
        hmisPositions.m_clean();

    },
    m_selectKarbar: function (id) {

        var param = "";
        param += "do=" + hmisPositions.tableName + ".selectKarbar";
        param += "&" + hmisPositions.f_id + "=" + (id == null ? "" : id);
//        hmisPositions.m_show_form();
//       $("#role_email").val('');
        new jj(param).jjAjax2(false);
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisPositions.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swPositions').css('height', 'auto');
        hmisPositions.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swPositions').css('height', 'auto');
        hmisPositions.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swPositions').css('height', hmisPositions.heightTab);
    },
}







