if (localStorage.getItem('hide-hint-bar') != $('body').attr('data-hint-version')) {

    $('.hint-bar').not('.stay').slideToggle();
}

if (localStorage.getItem("log-device") === null) {
    $.get('https://www.cloudflare.com/cdn-cgi/trace', function (data) {
        $.ajax({
            type: "POST",
            data: data,
            url: '/log-device',
            dataType: 'JSON'
        });
    });
    localStorage.setItem("log-device", 1)
}
$(document).on('click', '.close-hint', function (e) {
    e.preventDefault();
    localStorage.setItem('hide-hint-bar', $('body').attr('data-hint-version'));
    $(this).closest('.hint-bar').slideToggle();
});



if (localStorage.getItem("checkBrowser") === null) {
    var data = {};
    if (window.ApplePaySession) {
        var promise = ApplePaySession.canMakePaymentsWithActiveCard(merchantIdentifier);
        promise.then(function (canMakePayments) {
            if (canMakePayments) {
                data = {is_ios: 1, can_pay: 1};
            } else {
                data = {is_ios: is_ios(), can_pay: 0};
            }
            $.ajax({
                type: "POST",
                data: data,
                url: '/apple-device',
                dataType: 'JSON'
            });
        });
    } else {
        data = {is_ios: is_ios(), can_pay: 0};
        $.ajax({
            type: "POST",
            data: data,
            url: '/apple-device',
            dataType: 'JSON'
        });
    }
    localStorage.setItem("checkBrowser", 1);
}


