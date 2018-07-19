$(document).ready(function(){
    isEmptyRegInputs();
    isEmptyLogInputsModal();
    isEmptyLogInputs();
    getReplyForm();

    getThemeDescription();
    createPathForThemeDeleteActionAttr();

    getCommentUpdateModal();
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

/*function createPathForThemeUpdateActionAttr() {
    $('.update-btn').on('click', function () {
        var title = $(this).attr('id');

        // $('#update-theme-form').attr('action', '/theme/update/' + title);
    });
}*/

function getThemeDescription() {
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