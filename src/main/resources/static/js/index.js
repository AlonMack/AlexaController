$(document).ready(function () {
    $("#run").click(function () {
        var first = $("#first").val();
        if (first > 100000) {
            $("#first").css("borderColor", "red");
            return;
        } else {
            $("#first").css("borderColor", "");
        }
        var second = $("#second").val();
        if (second > 100000) {
            $("#second").css("borderColor", "red");
            return;
        } else {
            $("#second").css("borderColor", "");
        }
        var action = $("#action option:selected").val();
        $.get("calculate/" + action + "/?first=" + first + "&second=" + second, function (data) {
            $("#result").html(data);
        });
    });
});