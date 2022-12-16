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
        $("#add-save-apply").hide();
        $(".type-edit").show();
        $(".type-show").hide();
    });

    $("#save-apply").click(function () {
        $("#edit-apply").show();
        $("#save-apply").hide();
        $("#add-save-apply").hide();
        $(".type-edit").hide();
        $(".type-show").show();
    });

    $("#save-apply").click(function () {
        let id = document.getElementById("leavetype-id").value;
        let name = $("#leavetype-name-edit").val();
        console.log(id, name);
        fetch(`/api/admin/leave-status/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                    name: name.toString(),
                },
            ),
        }).then((response) => {
            if (response.ok) {
                alert("修改成功");
                window.location.reload();
            } else {
                alert("修改失败");
            }
        });
    });

    $("#delete-apply").click(() => {
        let id = document.getElementById("leavetype-id").value;
        console.log(id)
        fetch("/api/admin/leave-status/" + id, {
            method: "DELETE",
        }).then(response => response.json()).then((res) => {
            console.log(res);
            if (res.code === 200) {
                window.location.reload();
            } else {
                alert(res.msg)
            }
            // $("#employee-name-edit").val("123" + res.statusText);
        })
    });

    $("#add-apply").click(() => {
        $("#edit-apply").hide();
        $("#save-apply").hide();
        $("#add-save-apply").show();
        $(".type-edit").show();
        $(".type-show").hide();
        $("#type-name-edit").val("");
    });

    $("#add-save-apply").click(() => {
        let name = $("#leavetype-name-edit").val();
        fetch("/api/admin/leave-status", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: name.toString(),
            },),
        }).then((response) => {
            if (response.ok) {
                alert("添加成功");
                window.location.reload();
            } else {
                alert("添加失败");
            }
        });
    });

});