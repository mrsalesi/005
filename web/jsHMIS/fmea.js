var hmisFmea = {
    tableName: "Fmea",
    f_id: "id",
    loadForm: function () {
        if ($("#swFmeaForm").html() == '') {
            $("#swFmeaForm").load("formHMIS/09FMEA.html", null, function () {
                $("#cancel_Fmea").click(function (e) {
                    hmisFmea.m_clean();
                    hmisFmea.m_show_tbl();
                });
                hmisRole.getSelectOptionRequierd("#swFmeaForm #fmea_member");
                $("#fmea_member").select2({
                    width: '100%'
                });
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swFmeaTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        hmisFmea.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swFmeaTbl').hide();
        $('#swFmeaForm').show();
        hmisFmea.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swFmeaForm").jjFormClean();
        $('#FMEATableOne input:text').attr("value", "");
//        $('#FMEATableOne input:number').val("");
        $('#FMEATableOne textarea').attr('value', '');
        $('#FMEATableTwo input:text').attr("value", "");
//        $('#FMEATableTwo input:number').val("");
        $('#FMEATableTwo textarea').html("");
    },
    m_show_tbl: function () {
        $('#swFmeaTbl').show();
        $('#swFmeaForm').hide();
        $('#refreshFmea').dataTable({//برای اینکه بعضی مواقع جدول در حالت رسپانسیو نمایش داده میشود
            destroy: true
        });
//        hmisFmea.m_refresh();
//        hmisFmea.tabSizeTbl();
    },
    m_add_new: function () {
        new jj("&do=" + hmisFmea.tableName + ".add_new").jjAjax2(false);
        hmisFmea.m_show_form();
        hmisFmea.m_clean();
    },
    m_insert: function () {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".insert";
        param += "&" + new jj("#swFmeaForm").jjSerial();
        param += "&fmea_beforeCorrectiveAction=" + encodeURIComponent($("#beforeCorrectiveActionDiv").html());
        param += "&fmea_afterCorrectiveAction=" + encodeURIComponent($("#afterCorrectiveActionDiv").html());
        param += "&fmea_guideTable=" + encodeURIComponent($("#tableGuideDiv").html());
        new jj(param).jjAjax2(false);
        hmisFmea.m_show_tbl();
        hmisFmea.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".edit";
        param += "&" + new jj("#swFmeaForm").jjSerial();
        param += "&fmea_beforeCorrectiveAction=" + encodeURIComponent($("#beforeCorrectiveActionDiv").html());
        param += "&fmea_afterCorrectiveAction=" + encodeURIComponent($("#afterCorrectiveActionDiv").html());
        param += "&fmea_guideTable=" + encodeURIComponent($("#tableGuideDiv").html());
        new jj(param).jjAjax2(false);
        hmisFmea.m_show_tbl();
        hmisFmea.m_clean();
    },
    m_delete: function (id) {
        if (confirm("از حذف رکورد اطمینان دارید؟")) {
            hmisFmea.m_delete_after_question(id);
        } else {
        }
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".delete";
        param += "&" + hmisFmea.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFmea.m_show_tbl();
        hmisFmea.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".select";
        param += "&" + hmisFmea.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisFmea.m_show_form();
    },
    addRow: function (tableName) {

        var length = 0;
        length = $("#" + tableName + "").length + 1;
        var tr = ("<tr class='tableFMEA1_1'>\n\
                    <td><textarea  rows='2' cols='70' class='form-control is-valid textarea' ></textarea></td>\n\
                    <td><textarea rows='2' cols='70' class='form-control is-valid textarea2' ></textarea></td>\n\
                    <td><input class='form-control is-valid onkeyup' type='text'  />\n\
 </td><td><input class='form-control is-valid onkeyup'  type='text'/></td>\n\
<td><input class='form-control is-valid onkeyup'  type='text'/>\n\
</td><td><input class='form-control is-valid onkeyup'  type='text' />\n\
</td><td><input class='form-control is-valid onkeyup'  type='text' /></td>\n\
<td><input class='form-control is-valid onkeyup'  type='text' /></td>\n\
                </tr>");
        var trRPN = "";
        var textarea = $("#" + tableName + " textarea.textarea");
        for (var i = 0; i < textarea.length + 1; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
<td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
<td><input type='text' class='form-control is-valid '/></td>\n\
</tr>";
        }
        $("#" + tableName + " tr." + tableName + ":last").after(tr);
        var bodyTable = $("#" + tableName + " tbody").html();
        $("#FMEATableOne table tbody").html(bodyTable);
        $("#tableFMEA1_4 tbody").html(trRPN);
        $('#FMEATableOne #tableFMEA1_2 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_2 textarea.textarea2').html("");
        $('#FMEATableOne #tableFMEA1_3 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_3 textarea.textarea2').html("");
        $('#FMEATableOne table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableOne tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        $('#FMEATableOne input.form-control.is-valid').on('keyup', function (ev) {
            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA1_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA1_1 tbody tr:nth-child(" + i + ") td:last-child input").val() * $("#tableFMEA1_2 tbody tr:nth-child(" + i + ")  td:last-child input").val() * $("#tableFMEA1_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");

                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
        });

    },
    addRowTwo: function (tableName) {

        var length = 0;
        length = $("#" + tableName + "").length + 1;
        var tr = ("<tr class='tableFMEA2_1'>\n\
                    <td><textarea  rows='2' cols='70' class='form-control is-valid textarea' ></textarea></td>\n\
                    <td><textarea rows='2' cols='70' class='form-control is-valid textarea2' ></textarea></td>\n\
                    <td><input class='form-control is-valid onkeyup' type='text'  />\n\</td>\n\
                    <td><input class='form-control is-valid onkeyup'  type='text'/></td>\n\
                    <td><input class='form-control is-valid onkeyup'  type='text'/>\n\</td>\n\
                    <td><input class='form-control is-valid onkeyup'  type='text' />\n\</td> \n\
                    <td><input class='form-control is-valid onkeyup'  type='text' /></td>\n\
                    <td><input class='form-control is-valid onkeyup'  type='text' /></td>\n\
                </tr>");
        var trRPN = "";
        var textarea = $("#" + tableName + " textarea.textarea");
        for (var i = 0; i < textarea.length + 1; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
                      \n\
                         <td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
                           <td><input type='text' class='form-control is-valid '/></td>\n\
                                            </tr>";
        }
        $("#" + tableName + " tr." + tableName + ":last").after(tr);
        var bodyTable = $("#" + tableName + " tbody").html();
        $("#FMEATableTwo table tbody").html(bodyTable);
        $("#tableFMEA2_4 tbody").html(trRPN);
        $('#FMEATableTwo #tableFMEA2_2 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_2 textarea.textarea2').html("");
        $('#FMEATableTwo #tableFMEA2_3 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_3 textarea.textarea2').html("");
        $('#FMEATableTwo table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableTwo tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        $('#FMEATableTwo input.form-control.is-valid').on('keyup', function (ev) {
            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA2_1 tbody tr:nth-child(" + i + ") td:last-child input").val()
                        * $("#tableFMEA2_2 tbody tr:nth-child(" + i + ")  td:last-child input").val() *
                        $("#tableFMEA2_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFromTwo").val() && RPNresult <= $("#lowToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFromTwo").val() && RPNresult <= $("#middleToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");

                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFromTwo").val() && RPNresult <= $("#highToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
            $("#finalResult").html("");
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var div = $("#finalResult").append("<div class='col-lg-6'> RPN2-RPN1\n\
                            <input id='finalResult" + i + "' class='form-control is-valid onkeyup'  type='text' />ّ\n\
                        </div>");
                var rpn1 = $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                var rpn2 = $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                $("#finalResult" + i).attr('value', rpn2 - rpn1);
                var subtraction = rpn2 - rpn1;
                if (subtraction >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#finalResult" + i).addClass("alert-success");
                } else {
                    $("#finalResult" + i).removeClass("alert-success");
                }
                if (subtraction >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#finalResult" + i).addClass("alert-warning");
                } else {
                    $("#finalResult" + i).removeClass("alert-warning");
                }
                if (subtraction >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#finalResult" + i).addClass("alert-danger");
                } else {
                    $("#finalResult" + i).removeClass("alert-danger");
                }

            }
        });

    },
    removeRow: function (tableName) {
        var trRPN = "";
        var textarea = $("#" + tableName + " textarea.textarea");
        for (var i = 0; i < textarea.length + 1; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
<td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
<td><input type='text' class='form-control is-valid '/></td>\n\
</tr>";
        }
        if ($("#" + tableName + " tr." + tableName + "").length > 1) {

            $("#" + tableName + " tr." + tableName + " ").last().remove();
        } else {
        }
        var bodyTable = $("#" + tableName + " tbody").html();
        $("#FMEATableOne table tbody").html(bodyTable);
         $("#tableFMEA1_4 tbody").html(trRPN);
        $('#FMEATableOne #tableFMEA1_2 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_2 textarea.textarea2').html("");
        $('#FMEATableOne #tableFMEA1_3 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_3 textarea.textarea2').html("");
        $('#FMEATableOne table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableOne tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        $('#FMEATableOne input.form-control.is-valid').on('keyup', function (ev) {

            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA1_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA1_1 tbody tr:nth-child(" + i + ") td:last-child input").val() * $("#tableFMEA1_2 tbody tr:nth-child(" + i + ")  td:last-child input").val() * $("#tableFMEA1_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");

                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
        });
    },
    removeRowTwo: function (tableName) {
        var trRPN = "";
        var textarea = $("#" + tableName + " textarea.textarea");
        for (var i = 0; i < textarea.length + 1; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
                       \n\
                       <td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
                       <td><input type='text' class='form-control is-valid '/></td>\n\
                      </tr>";
        }
        if ($("#" + tableName + " tr." + tableName + "").length > 1) {

            $("#" + tableName + " tr." + tableName + " ").last().remove();
        } else {
        }
        var bodyTable = $("#" + tableName + " tbody").html();
        $("#FMEATableTwo table tbody").html(bodyTable);
        $("#tableFMEA2_4 tbody").html(trRPN);
         $('#FMEATableTwo #tableFMEA2_2 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_2 textarea.textarea2').html("");
        $('#FMEATableTwo #tableFMEA2_3 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_3 textarea.textarea2').html("");
        $('#FMEATableTwo table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableTwo tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        $('#FMEATableTwo input.form-control.is-valid').on('keyup', function (ev) {

            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA2_1 tbody tr:nth-child(" + i + ") td:last-child input").val()
                        * $("#tableFMEA2_2 tbody tr:nth-child(" + i + ")  td:last-child input").val()
                        * $("#tableFMEA2_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFromTwo").val() && RPNresult <= $("#lowToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFromTwo").val() && RPNresult <= $("#middleToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");

                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFromTwo").val() && RPNresult <= $("#highToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
            $("#finalResult").html("");
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var div = $("#finalResult").append("<div class='col-lg-12'> RPN2-RPN1\n\
                            <input id='finalResult" + i + "' class='form-control is-valid onkeyup'  type='text' />ّ\n\
                        </div>");
                var rpn1 = $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                var rpn2 = $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                $("#finalResult" + i).attr('value', rpn2 - rpn1);
                var subtraction = rpn2 - rpn1;
                if (subtraction >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#finalResult" + i).addClass("alert-success");
                } else {
                    $("#finalResult" + i).removeClass("alert-success");
                }
                if (subtraction >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#finalResult" + i).addClass("alert-warning");
                } else {
                    $("#finalResult" + i).removeClass("alert-warning");
                }
                if (subtraction >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#finalResult" + i).addClass("alert-danger");
                } else {
                    $("#finalResult" + i).removeClass("alert-danger");
                }

            }
        });
    },
    updateTablesOne: function () {

        var bodyTable = $("#tableFMEA1_1 tbody").html();
        $("#FMEATableOne table tbody").html(bodyTable);
        $('#FMEATableOne table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableOne tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        var trRPN = "";
        var textarea = $("#tableFMEA1_1 textarea.textarea");
        for (var i = 0; i < textarea.length; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
<td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
<td><input type='text' class='form-control is-valid '/>\n\
</td></tr>";

        }
        $("#FMEATableOne #tableFMEA1_4 tbody").html(trRPN);
        $('#FMEATableOne #tableFMEA1_2 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_2 textarea.textarea2').html("");
        $('#FMEATableOne #tableFMEA1_3 input.onkeyup').val("");
        $('#FMEATableOne #tableFMEA1_3 textarea.textarea2').html("");
        $('#FMEATableOne table input.onkeyup').keyup(function () {
            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA1_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA1_1 tbody tr:nth-child(" + i + ") td:last-child input").val() * $("#tableFMEA1_2 tbody tr:nth-child(" + i + ")  td:last-child input").val() * $("#tableFMEA1_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");

                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
        });
    },
    updateTablesTwo: function () {

        var bodyTable = $("#tableFMEA2_1 tbody").html();
        $("#FMEATableTwo table tbody").html(bodyTable);
        $('#FMEATableTwo table input').keyup(function () {
            $(this).attr('value', $(this).val());
        });
        $('#FMEATableTwo tr td textarea').keyup(function () {
            $(this).text($(this).val());
        });
        var trRPN = "";
        var textarea = $("#tableFMEA2_1 textarea.textarea");
        for (var i = 0; i < textarea.length; i++) {
            trRPN += "<tr class='rowTableFMEA'>\n\
<td><textarea rows='2' cols='70' class='form-control is-valid textarea'>" + $(textarea[i]).html() + "</textarea></td>\n\
<td><input type='text' class='form-control is-valid '/>\n\
</td></tr>";

        }
        $("#FMEATableTwo #tableFMEA2_4 tbody").html(trRPN);
        $('#FMEATableTwo #tableFMEA2_2 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_2 textarea.textarea2').html("");
        $('#FMEATableTwo #tableFMEA2_3 input.onkeyup').val("");
        $('#FMEATableTwo #tableFMEA2_3 textarea.textarea2').html("");
        $('#FMEATableTwo table input.onkeyup').keyup(function () {
            var sum = 0;
            var j = 0;
            for (var i = 0; i < 5; i++) {
                var nextInput = $(this).parent().parent().find("input")[i];
                if ($(nextInput).val() != "" && $(nextInput).val() != "0") {
                    sum += parseFloat($(nextInput).val());
                    j++;
                }
            }
            var avgInput = $(this).parent().parent().find("input")[i]; // خانه آخر 
            $(avgInput).attr('value', Math.round(sum / j));
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var RPNresult = $("#tableFMEA2_1 tbody tr:nth-child(" + i + ") td:last-child input").val()
                        * $("#tableFMEA2_2 tbody tr:nth-child(" + i + ")  td:last-child input").val()
                        * $("#tableFMEA2_3 tbody tr:nth-child(" + i + ")  td:last-child input").val();
                $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").attr('value', RPNresult);
                if (RPNresult >= $("#lowFromTwo").val() && RPNresult <= $("#lowToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-success");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-success");
                }
                if (RPNresult >= $("#middleFromTwo").val() && RPNresult <= $("#middleToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-warning");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-warning");
                }
                if (RPNresult >= $("#highFromTwo").val() && RPNresult <= $("#highToTwo").val()) {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").addClass("alert-danger");
                } else {
                    $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").removeClass("alert-danger");
                }
            }
            $("#finalResult").html("");
            for (var i = 1; i <= $("#tableFMEA2_4 tbody tr").length; i++) {// به تعداد سطر های جدول
                var div = $("#finalResult").append("<div class='col-lg-12'> RPN2-RPN1\n\
                            <input id='finalResult" + i + "' class='form-control is-valid onkeyup'  type='text' />ّ\n\
                        </div>");
                var rpn1 = $("#tableFMEA1_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                var rpn2 = $("#tableFMEA2_4 tbody tr:nth-child(" + i + ") input:last-child").val();
                $("#finalResult" + i).attr('value', rpn2 - rpn1);
                var subtraction = rpn2 - rpn1;
                if (subtraction >= $("#lowFrom").val() && RPNresult <= $("#lowTo").val()) {
                    $("#finalResult" + i).addClass("alert-success");
                } else {
                    $("#finalResult" + i).removeClass("alert-success");
                }
                if (subtraction >= $("#middleFrom").val() && RPNresult <= $("#middleTo").val()) {
                    $("#finalResult" + i).addClass("alert-warning");
                } else {
                    $("#finalResult" + i).removeClass("alert-warning");
                }
                if (subtraction >= $("#highFrom").val() && RPNresult <= $("#highTo").val()) {
                    $("#finalResult" + i).addClass("alert-danger");
                } else {
                    $("#finalResult" + i).removeClass("alert-danger");
                }
            }
        });
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisFmea.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
//        $('#swFmea').css('height','auto');
//        hmisFmea.heightTab='auto';
    },
    tabSizeForm: function () {
//        $('#swFmea').css('height','auto');
//        hmisFmea.heightTab='auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swFmea').css('height', hmisFmea.heightTab);
    }
}
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
var hmisFmeaAccidentReporting = {
    tableName: "Fmea",
    f_id: "id",
    loadForm: function () {
        if ($("#swFmeaAccidentReportingForm").html() == '') {
            $("#swFmeaAccidentReportingForm").load("formHMIS/09FMEA.html", null, function () {
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisFmeaAccidentReporting.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swFmeaAccidentReportingFormTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        new jj(param).jjAjax2(false);
        hmisFmeaAccidentReporting.tabSizeTbl();
    },
    refreshMyAnswersInAccidentReportingForm: function (formId, containerId) {
//        if ($("#swMyFormsForm").html() == '') {// برای اینکه اگر فرم قبلش لود نشده باشد نمی شود جدول را نشان بدهیم و برای جلوگیری از تکرار اضافی رفرش انسر های من
//            hmisFormAnswerSets.loadForm(formId);
//            return ;
//        }
        hmisFmeaAccidentReporting.m_show_form();

        var param = "";
        param += "do=" + hmisFormAnswerSets.tableName + ".refreshMyAnswersInAccidentReportingForm";
        param += "&formAnswers_formId=" + formId;
        param += "&panel=" + (containerId == null ? "swFmeaAccidentReportingForm" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
//        hmisFormAnswerSets.m_show_form();
    },
    m_show_form: function () {
        $('#swFmeaAccidentReportingTbl').hide();
        $('#swFmeaAccidentReportingForm').show();
        hmisFmea.tabSizeForm();
    },

    m_show_tbl: function () {
        $('#swFmeaAccidentReportingTbl').show();
        $('#swFmeaAccidentReportingForm').hide();

    },

    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisFmeaAccidentReporting.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
//        $('#swFmea').css('height','auto');
//        hmisFmea.heightTab='auto';
    },
    tabSizeForm: function () {
//        $('#swFmea').css('height','auto');
//        hmisFmea.heightTab='auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swFmeaAccidentReporting').css('height', hmisFmeaAccidentReporting.heightTab);
    }
}

