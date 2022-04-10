/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisPdca = {
    tableName: "FocusSPdca",
    f_id: "id",
    loadForm: function () {
        if ($("#swPdcaForm").html() == '') {
            $("#swPdcaForm").load("11FOCUS_PDCA.html", null, function () {

                $("#cancel_Pdca").click(function (e) {

                    $("#FMEATableOne1").html("");
                    hmisPdca.m_show_tbl();
                });
                new jj('#sendfile1').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile1', '', 'pdca_titleFile1', "#showfiles1div");
                new jj('#sendfile2').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile2', '', 'pdca_titleFile2', "#showfiles2div");
                new jj('#sendfile3').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile3', '', 'pdca_titleFile3', "#showfiles3div");
                new jj('#sendfile4').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile4', '', 'pdca_titleFile4', "#showfiles4div");
                hmisPdca.m_refresh();
            });

        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {

        var param = "";
        param += "do=" + hmisPdca.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swPdcaTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 'auto' : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swPdcaTbl').slideUp();
        $('#swPdcaForm').slideDown();
        hmisPdca.tabSizeForm();
    },
    m_clean: function () {
        new jj('#swPdcaForm').jjFormClean();
        $('input').removeClass('red');
        $('input').removeClass('green');

    },
    m_load: function () {
        $("#FMEATableOne1").load("formHMIS/mainPdca.html", null, function () {
            new jj('#sendfile1').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile1', '', 'pdca_titleFile1', "#showfiles1div");
            new jj('#sendfile2').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile2', '', 'pdca_titleFile2', "#showfiles2div");
            new jj('#sendfile3').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile3', '', 'pdca_titleFile3', "#showfiles3div");
            new jj('#sendfile4').jjAjaxFileUploadByTitleAndMultiFile('#pdca_sendfile4', '', 'pdca_titleFile4', "#showfiles4div");
            $('.form-control').keyup(function () {
                $(this).attr('value', $(this).val());
            });
            $('.onkeyup').keyup(function () {
                $(this).text($(this).val());
            });
            $('.isok').on('keyup', function (ev) {
                var sum = 0;
                var j = 0;
                for (var i = 0; i <= 5; i++) {
                    var nextInput = $(this).parent().parent().find("input")[i];
                    if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                        sum += parseFloat($(nextInput).val());
                        j++;
                    }
                }
                var avgInput = $(this).parent().parent().find("input")[i];// خانه آخر 
                $(avgInput).attr('value', sum);
            });
            $('.isoksort').on('keyup', function (ev) {
                var sum = 0;
                var j = 0;
                for (var i = 1; i <= 4; i++) {
                    var nextInput = $(this).parent().parent().find("input")[i];
                    if ($(nextInput).val() != "") {
                        sum += parseFloat($(nextInput).val());
                        j++;
                    }
                }
                var avgInput = $(this).parent().parent().find(".sum");// خانه آخر 
                $(avgInput).html(sum);
            });
        });

    },
    m_sort: function () {
        $("#tableFOCUSPDCA2").dataTable({
            searching: false,
            paging: false,
            destroy: true,
            "aaSorting": [[5, 'desc']],
            "bInfo": false
        });
    },
    m_add_new: function () {
        hmisPdca.m_load();
        var param = "";
        param += "&do=" + hmisPdca.tableName + ".add_new&jj=1";
        new jj(param).jjAjax2(false);
        hmisPdca.m_clean();
        $('#swPdcaForm').show();
        $('#swPdcaTbl').hide();
        $("#pdca_insert_btn").show();
        $("#pdca_edit_btn").hide();
        $("#pdca_delete_btn").hide();
        $('div#pointer').css({'transform': 'translate(0,0)'});
    },
    m_show_tbl: function () {
        $('#swPdcaTbl').show();
        $('#swPdcaForm').hide();
        if ($('#swPdcaTbl').html() == "") {
            hmisPdca.m_refresh();
        }
        hmisPdca.tabSizeTbl();
        $("#refreshPdca").dataTable({
            destroy: true,
        });
    },
    m_insert: function () {
        var param = "";
        param += "&do=" + hmisPdca.tableName + ".insert";
        param += "&html=" + encodeURIComponent($("#FMEATableOne1").html());
        param += "&subject=" + $("#subject").val();
        new jj(param).jjAjax2(false);
        hmisPdca.m_show_tbl();
        $("#FMEATableOne1").html("");
    },
    m_edit: function () {
        var param = "";
        param += "&do=" + hmisPdca.tableName + ".edit";
        param += "&htmledit=" + encodeURIComponent($("#FMEATableOne1").html());
        param += "&subject=" + $("#subject").val();
        param += "&id=" + $("#pdca_id").val();
        new jj(param).jjAjax2(false);
        hmisPdca.m_show_tbl();
        $("#FMEATableOne1").html("");
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No('پیام هشدار', "hmisPdca.confirmationFinalStrategic_after_question(" + id + ");");
    },
    confirmationFinalStrategic_after_question: function (id) {
        var param = "";
        param += "do=" + hmisPdca.tableName + ".delete";
        param += "&" + hmisPdca.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPdca.m_show_tbl();
        $("#FMEATableOne1").html("");
    },
    tabSizeTbl: function () {
        $('#swAccessAll').css('height', 'auto');
        hmisPdca.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swAccessAll').css('height', 'auto');
        hmisPdca.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swAccessAll').css('height', hmisPdca.heightTab);
    },
    m_select: function (id) {

        var param = "";
        param += "do=" + hmisPdca.tableName + ".select";
        param += "&" + hmisPdca.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisPdca.m_show_form();
        $("#pdca_insert_btn").hide();
        $("#pdca_delete_btn").show();
        $("#pdca_edit_btn").show();
    }
}
;