// 防止在HTML DOM还未加载完成就执行了JS语句
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $("#toggle-sidebar").click(function () {
        $(".sidebar").toggleClass("hidden");
        $("#toggle-sidebar").toggleClass("fa-angle-double-right");
        $("#toggle-sidebar").toggleClass("fa-ellipsis-h");
        $(".content").toggleClass("col-md-9");
        $(".content").toggleClass("col-md-12");
    });

    $("#edit-apply").click(function () {
        $("#edit-apply").hide();
        $("#save-apply").show();
        $(".employee-edit").show();
        $(".employee-show").hide();
    });

    $("#save-apply").click(function () {
        $("#edit-apply").show();
        $("#save-apply").hide();
        $(".employee-edit").hide();
        $(".employee-show").show();
    });

    $("#save-apply").click(() => {
        let test = $("#employee-name-edit").val();
        console.log(document.getElementById("employee-name-edit"));
        console.log("save-apply", test);
        fetch("/api/admin/leave-status", {
            method: "POST",
            body: JSON.stringify({
                name: "yxx",
            })
        }).then(response => response.json()).then((res) => {
            console.log(res);
            $("#employee-name-edit").val("123" + res.statusText);
        })
    })
});
