var hmisDashbord = {
    tableName: "Dashbord",
    f_id: "id",
    showDashbord: function () {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".showDashbord";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    getIndicatorGauges: function (gaugeID,containerId) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".getIndicatorGauges";
        param += "&id=" + (gaugeID == null ? "0" : gaugeID);
        param += "&panel=" + (containerId == null ? "swIndicatorsGauges" : containerId);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    insertDashbord: function () {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".insertDashbord";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_alert: function (id) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".alert";
        param += "&id=" + id;
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    dashbordMyMessage: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".dashbordMyMessage";
        param += "&panel=" + (containerId == null ? "swShowMyMessages" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=auto";
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_add_new: function () {
        new jj("do=" + hmisDashbord.tableName + ".add_new").jjAjax2(false);
        $('#messenger_receiver').val("null").trigger('change');
        $('#status').hide();
        $('#logStatus').hide();
        $(".inputAfterSelectManager").html("");
        $(".inputSelectorDiv").html("");
        hmisDashbord.m_show_form();
        hmisDashbord.m_clean();
    },
    m_show_form: function () {
        $('#swUnreadMessagesTbl').hide();
        $('#swUnreadMessagesForm').show();
        if ($('#swUnreadMessagesTbl').html() == "") {
            hmisDashbord.m_refresh();
        }
        hmisDashbord.tabSizeForm();
    },
    m_clean: function () {
        new jj("#swUnreadMessagesForm").jjFormClean();
        $("#messenger_receiver").val('').trigger('change');

    },
    m_show_tbl: function () {
        $('#swUnreadMessagesTbl').show();
        $('#swUnreadMessagesForm').hide();
        if ($('#swUnreadMessagesTbl').html() == "") {
            hmisDashbord.m_refresh();
        }
        hmisDashbord.m_refresh();
        hmisDashbord.tabSizeTbl();
    },
    m_insert: function () {

        var param = "";
        param += "do=" + hmisDashbord.tableName + ".insert";
        param += "&" + new jj("#swUnreadMessagesForm").jjSerial();
        param += "&jj=1";
        param += "&messenger_receiver=" + $("#unreadMessages_receiver option:selected").val();
        new jj(param).jjAjax2(false);

        hmisDashbord.m_show_tbl();
        hmisDashbord.m_clean();
    },
    m_edit: function () {
        var param = "";
        param += "&do=" + hmisDashbord.tableName + ".edit";
        param += "&" + new jj("#swUnreadMessagesForm").jjSerial();
        param += "&jj=1";
        param += "&messenger_receiver=" + $("#unreadMessages_receiver option:selected").val();
        new jj(param).jjAjax2(false);
        $(".inputSelectorDiv").html('');
        $(".inputAfterSelectManager").html("");
        hmisDashbord.m_show_tbl();
        hmisDashbord.m_clean();
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisDashbord.m_delete_after_question(" + id + ");");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".deleteDashbordMyMessage";
        param += "&" + hmisDashbord.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
    },
    m_remove: function (idUpload, id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjModal_Yes_No("پیام هشدار قبل از حذف", "hmisDashbord.removeFile(" + idUpload + "," + id + ");");
    },
    removeFile: function (idUpload, id) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".removeFile";
        param += "&upload_id=" + idUpload;
        param += "&messenger_id=" + id;
        new jj(param).jjAjax2(false);
        hmisDashbord.m_show_tbl();
        hmisDashbord.m_clean();
    },
    m_select: function (id) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".selectUnreadMessages";
        param += "&" + hmisDashbord.f_id + "=" + (id == null ? "" : id);
        $('#status').show();
        $('#logStatus').show();
        $(".inputSelectorDiv").html("");
        $(".inputAfterSelectManager").html("");
        new jj(param).jjAjax2(false);
        hmisDashbord.m_show_form();
    },
    getIndicatorLineChart: function (id) {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".getIndicatorLineChart";
        param += "&" + hmisDashbord.f_id + "=" + (id == null ? "" : id);
        param += "&chartType=line";
        new jj(param).jjAjax2(false);
    },
    getMyApproved: function () {
        var param = "";
        param += "do=" + hmisApproved.tableName + ".refreshMyApprovedInDashbord";
        param += "&panel=swMyApprov";
        new jj(param).jjAjax2(false);
    },
    m_getMenu: function () {
        var param = "";
        param += "do=" + hmisDashbord.tableName + ".getMenu";
        new jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swUnreadMessages').css('height', 'auto');
        hmisDashbord.heightTab = 'auto';
    },
    tabSizeForm: function () {
        $('#swUnreadMessages').css('height', 'auto');
        hmisDashbord.heightTab = 'auto';
    },
    heightTab: 'auto',
    mainTabSetSize: function () {
        $('#swUnreadMessages').css('height', hmisDashbord.heightTab);
    },
    /**
     * 
     * @param {type} goodFloor عدد اول قسمت سبز
     * @param {type} goodTop  عدد دوم قسمت سبز
     * @param {type} warnningFloor  عدد اول قسمت زرد
     * @param {type} warnningTop  عدد دوم قسمت زرد
     * @param {type} dangerFloor  عدد اول قسمت قرمز
     * @param {type} dangerTop  عدد دوم قسمت قرمز
     * @param {type} numberAll  عدد میانگین
     * @param {type} gaugePanel  پنل نشان دادن گیج
     * @param {type} panelNumber پنل نشان دادن عدد میانگین
     * @example  hmisDashbord.drowGauge1(0,20,20,60,60,0,15,'getIndecatorGauge1','gaugeNumber1');
     * @returns {undefined}
     */
    drowGauge: function (goodFloor, goodTop, warnningFloor,warnningTop,dangerFloor,dangerTop, numberAll,gaugePanel,panelNumber) {
        var min1,min2,min3,max1,max2,max3,color1,color2,color3;
        if (dangerTop > goodFloor) {
            color1 = "#30B32D";//سبز 
            color2 = "#FFDD00";//زرد
            color3 = "#F03E3E";//قرمز
            min1=goodFloor;
            min2=warnningFloor;
            min3=dangerFloor;
            max1=goodTop;
            max2=warnningTop;
            max3=dangerTop;
        }
        if (dangerTop < goodFloor) {
            color1 = "#F03E3E";//قرمز 
            color2 = "#FFDD00";//زرد
            color3 ="#30B32D";//سبز 
            min1=dangerTop;
            min2=warnningTop;
            min3=goodTop;
            max1=dangerFloor;
            max2=warnningFloor;
            max3=goodFloor;
        }
        var opts = {
            angle: 0, // The span of the gauge arc
            lineWidth: 0.2, // The line thickness
            radiusScale: 1, // Relative radius
            pointer: {
                length: 0.6, // Relative to gauge radius
                strokeWidth: 0.035, // The thickness
                color: '#0866C6' // Fill color
            },
            staticZones: [
                {strokeStyle: color1, min: min1, max: max1, height: 1.5},
                {strokeStyle: color2, min: min2, max: max2, height: 1.5},
                {strokeStyle: color3, min: min3, max: max3, height: 1.5}
            ],
            renderTicks: {
                divisions: 5,
                divWidth: 1.1,
                divLength: 0.8,
                subDivisions: 4,
                subLength: 0.5,
                subWidth: 0.8
            },
            staticLabels: {
                font: "10px sans-serif", // Specifies font
                labels: [min1, min2, min3, max3], // Print labels at these values
                color: "#000000", // Optional: Label text color
                fractionDigits: 0  // Optional: Numerical precision. 0=round off.
            },
            limitMax: true, // If false, max value increases automatically if value > maxValue
            limitMin: true, // If true, the min value of the gauge will be fixed
            highDpiSupport: true // High resolution support

        };
        var target = document.getElementById(gaugePanel); // your canvas element
        var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
        gauge.maxValue = max3; // set max gauge value
        gauge.setMinValue(min1);  // Prefer setter over gauge.minValue = 0
        gauge.animationSpeed = 40; // set animation speed (32 is default value)
        gauge.set(numberAll); // set actual value
        document.getElementById(panelNumber).innerHTML = Math.round(100*numberAll)/100;
    }
};




