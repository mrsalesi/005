var app = {
    sendTo: function (data, addr, port) {
        alert("sendTo");
        chrome.sockets.udp.create(function (createInfo) {
            chrome.sockets.udp.bind(createInfo.socketId, '0.0.0.0', 0, function (result) {
                chrome.sockets.udp.send(createInfo.socketId, data, addr, port, function (result) {
                    if (result < 0) {
                        alert('send fail: ' + result);
                        chrome.sockets.udp.close(createInfo.socketId);
                    } else {
                        alert('sendTo: success ' + port);
                        chrome.sockets.udp.close(createInfo.socketId);
                    }
                });
            });
        });
    },
    receiveErrorListener: function (info) {
        alert('RecvError on socket: ' + info.socketId);
        chrome.sockets.udp.close(info.socketId);
    },
    receiveListener: function (info) {
        var listItem = document.createElement('li');
        var html = '' + info + '';
        listItem.innerHTML = html;
        deviceList.appendChild(listItem);
        chrome.sockets.udp.close(info.socketId);
    },
    addReceiveListeners: function () {
        try {
            alert("Add Listeners");
            alert(typeof (chrome) == "undefined");
            chrome.sockets.udp.onReceiveError.addListener(this.receiveErrorListener);
            alert("add listeners 2");
            chrome.sockets.udp.onReceive.addListener(this.receiveListener);
        } catch (err) {
            alert(err);
        }
    },
    initialize: function () {
        this.bindEvents();
        detailPage.hidden = true;
    },
    bindEvents: function () {
        alert("bind");
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    onDeviceReady: function () {
        alert(typeof (chrome) == "undefined");
        alert("ready");
        try {
            app.addReceiveListeners();
            app.sendTo("Discover,0,", "255.255.255.255", 12345);
            alert("Send Discover");
        } catch (err) {
            alert(err);
        }
    },
    onError: function (reason) {
        alert(reason);
    }
};