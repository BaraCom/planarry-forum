$(document).ready(function(){
    getLoginForm();
    isEmptyRegInputs();
    isEmptyLogInputs();
});

function getLoginForm() {
    $('#login').on('click', function () {
        $('#login').attr('data-toggle', 'modal');
    });
}

function isEmptyRegInputs() {
    $('#regBtn').on('click', function () {
        var login = $("[name = 'loginReg']").val();
        var email = $("[name = 'emailReg']").val();
        var pass = $("[name = 'passwordReg']").val();
        var pass2 = $("[name = 'password2Reg']").val();

        if (login.trim() === '') {
            $('.modal-content-p').text('Pls, enter the login!');
        } else if (email.trim() === '') {
            $('.modal-content-p').text('Pls, enter the email!');
        } else if (pass.trim() === '' || pass2.trim() === '') {
            $('.modal-content-p').text('Pls, enter the password!');
        } else {
            if (pass === pass2) {
                $('#regBtn').attr('data-toggle', '');
                $('#regBtn').attr('data-target', '');
                $('#regBtn').attr('type', 'submit');
            } else {
                $('.modal-content-p').text('Not matched passwords');
            }
        }
    })
}

function isEmptyLogInputs() {
    $('#logBtn').on('click', function () {
        var login = $("[name = 'login']").val();
        var pass = $("[name = 'password']").val();

        $('.forLoginMessage').text('');
        $('.forPasswordMessage').text('');

        if (login.trim() === '') {
            $('.forLoginMessage').text('You must enter the login');
        } else if (pass.trim() === '') {
            $('.forPasswordMessage').text('You must enter the password');
        } else {
            $('#logBtn').attr('type', 'submit');
        }
    });
}
