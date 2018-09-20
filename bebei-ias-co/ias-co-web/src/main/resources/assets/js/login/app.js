$(function () {

    $('.tpl-login-btn').on('click', function () {
        login();
    })

});

function login() {

    var userName = $('#user-name').val();
    var password = $('#password').val();

    $.ajax(
        {
            url: "/login",
            type: 'POST',
            dataType: 'json',
            data: {
                userName: userName,
                password: password
            },
            success: function (result) {
                if (result.success) {
                    window.location = '/';
                } else {
                    alert("登录失败")
                }
            },
            error: function (error) {
                console.log("error", error)
            }
        })

}