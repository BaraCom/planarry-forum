$(document).ready(function(){
    isEmptyRegInputs();
    isEmptyLogInputsModal();
    isEmptyLogInputs();
    isEmptyThemeInputs();
    isEmptyEditThemeModal();
    getReplyForm();

    getThemeUpdateModal();
    createPathForThemeDeleteActionAttr();

    getCommentUpdateModal();

    getUserDescription();
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

function isEmptyThemeInputs() {
    $('#add-new-theme').on('click', function () {
        var title = $("#inputTitle").val();
        var description = $("#themeDescription").val();

        if (title.trim() === '') {
            $('.modal-content-p').text('Pls, enter the title!');
        } else if (description.trim() === '') {
            $('.modal-content-p').text('Pls, enter the description!');
        } else {
            $('#add-new-theme').attr('data-toggle', '');
            $('#add-new-theme').attr('data-target', '');
            $('#add-new-theme').attr('type', 'submit');
        }
    });
}

function isEmptyEditThemeModal() {
    $('#save-modal-edit-theme').on('click', function () {
        var description = $("#modal-edit-theme").val();

        if (description.trim() === '') {
            $('#isEmpty').text('Pls, writing the theme!');
        } else {
            $('#save-modal-edit-theme').attr('type', 'submit');
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

function getThemeUpdateModal() {
    $('.update-btn').on('click', function () {
        var themeTitle = $(this).attr('id');
        var themeDescription = $('#p-' + themeTitle).text();

        $('#theme-update-input').attr('value', themeTitle);
        $('#update-modal-title').text(themeTitle + ' (theme edit)');
        $('#modal-edit-theme').val(themeDescription);
    });
}

function createPathForThemeDeleteActionAttr() {
    $('.delete-btn').on('click', function () {
        var title = $(this).attr('id');

        $('#delete-theme-form').attr('action', '/theme/delete/' + title);
    });
}

function getCommentUpdateModal() {
    $('.comment-update-btn').on('click', function () {
        var commentId = $(this).attr('id');
        var commentValue = $('#update-comment-input-' + commentId).attr('value');
        var themeTitle = $('#title-id-' + commentId).text();

        $('#update-comment-modal-title').text(' (comment edit) ');
        $('#modal-update-comment').val(commentValue);
        $('#comment-update-id').attr('value', commentId);
        $('#comment-update-theme-title').attr('value', themeTitle);
    });
}

function getUserDescription() {
    $('.update-user-btn').on('click', function () {
        var userName = $(this).attr('id');
        var password = $('#p-' + userName).text();
        var email = $('#email-' + userName).text();
        var oldName = $('#oldName' + userName + password).attr('value');
        var oldPassword = $('#oldPassword' + userName + password).attr('value');


        $('#update-user-login').attr('value', userName);
        $('#update-user-modal-title').text(userName + ' (theme edit)');
        $('#update-user-password').attr('value', password);
        $('#update-user-email').attr('value', email);
        $("#update-user-old-login").attr('value', oldName);
        $("#update-user-old-password").attr('value', oldPassword);
    });
}
