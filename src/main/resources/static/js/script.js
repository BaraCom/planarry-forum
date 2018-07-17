$(document).ready(function(){
    isEmptyRegInputs();
    isEmptyLogInputsModal();
    isEmptyLogInputs();
    getReplyForm();
});

function isEmptyRegInputs() {
    $('#regBtn').on('click', function () {
        var username = $("[name = 'loginReg']").val();
        var email = $("[name = 'emailReg']").val();
        var pass = $("[name = 'passwordReg']").val();
        var pass2 = $("[name = 'password2Reg']").val();

        if (username.trim() === '') {
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

function isEmptyLogInputsModal() {
    $('#logBtnModal').on('click', function () {
        var username = $(".login-input-modal").val();
        var pass = $(".password-input-modal").val();

        $('.forLoginMessage').text('');
        $('.forPasswordMessage').text('');

        if (username.trim() === '') {
            $('.forLoginMessage').text('You must enter the login');
        } else if (pass.trim() === '') {
            $('.forPasswordMessage').text('You must enter the password');
        } else {
            $('#logBtnModal').attr('type', 'submit');
        }
    });
}

function isEmptyLogInputs() {
    $('#logBtn').on('click', function () {
        var username = $(".login-input").val();
        var pass = $(".password-input").val();

        if (username.trim() === '') {
            $('.modal-content-p').text('Pls, enter the login!');
        } else if (pass.trim() === '') {
            $('.modal-content-p').text('Pls, enter the password!');
        } else {
            $('#logBtn').attr('data-toggle', '');
            $('#logBtn').attr('data-target', '');
            $('#logBtn').attr('type', 'submit');
        }
    });
}

function getReplyForm() {
    $('.reply-btn').on('click', function () {
        $('.reply-form').attr('style', 'display:none');

        var idValue = $(this).attr('id');

        $("[name = '" + idValue +"']").attr('style', 'display:visible');
    });
}
