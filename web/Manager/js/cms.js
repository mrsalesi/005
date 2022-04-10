
function login() {
    $("#errorLogin").html("");
    if ($("#user_email").val() === '' && $("#user_pass").val() === '') {
        $("#user_email").css("border", "red 1px solid");
        $("#user_pass").css("border", "red  1px solid");
        $("#errorLogin").html("ایمیل و پسورد را وارد کنید");
    } else if ($("#user_email").val() === '' || $("#user_pass").val() === '') {
        if ($("#user_email").val() === '') {
            $("#user_pass").css("border", "#eeeeee 1px solid");
            $("#user_email").css("border", "red 1px solid");
            $("#errorLogin").html("ایمیل  را وارد کنید");
        } else if ($("#user_pass").val() === '') {
            $("#user_email").css("border", "#eeeeee 1px solid");
            $("#user_pass").css("border", "red 1px solid");
            $("#errorLogin").html("پسورد را وراد کنید");
        }
    } else {
        $("#user_email").css("border", "none");
        $("#user_pass").css("border", "none");
//        new jj("do=Access_User.loginUserInSite&jj=1&user_email=" + $("#user_email").val() + "&user_pass=" + $("#user_pass").val()).jjAjax2(false);
        new jj("do=Access_User.login&jj=1&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
    }
}

function signOut() {
    USER_NAME = "";
    USER_FAMILY = "";
    USER_EMAIL = "";
    USER_PASS = "";
    new jj("do=Access_User.signOut").jjAjax2(true);
//    $("#loginForm").show();
//    $("#jjLoginExitPanel").html('ورود / ثبت نام');
//    window.location = 'index_MySite.html';
    $("countPayamhaDidenashode").html('');
//reportStatus.();
//payamhayeman
//window.location = 'Server2?do=Category_Product.getSelectOptions&panel=swSelectOptions&id=0&jj=1';
//    showSabtenam();
//     $("#loginForm").show();
}




//new jj("do=Access_User.signOut").jjAjax2(true);
//
//new jj("do=Access_User.loginUserInSite&jj=1&" + new jj("#loginForm").jjSerial()).jjAjax2(false);


