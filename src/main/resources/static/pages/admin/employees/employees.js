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
        $(".employee-edit").show();
        $(".employee-show").hide();
        $("#employee-password-edit").hide();
        $("#employee-username-show").hide();
        $("#employee-username-edit").hide();
        $("#employee-password-edit-label").hide();
        $("#employee-username-show-label").hide();
        $("#employee-username-edit-label").hide();
    });

    $("#save-apply").click(function () {
        $("#edit-apply").show();
        $("#save-apply").hide();
        $("#add-save-apply").hide();
        $(".employee-edit").hide();
        $(".employee-show").show();
        $("#employee-password-edit").hide();
        $("#employee-username-show").show();
        $("#employee-username-edit").hide();
        $("#employee-password-edit-label").hide();
        $("#employee-username-show-label").show();
        $("#employee-username-edit-label").hide();
    });

    $("#save-apply").click(() => {
        let id = document.getElementById("employee-id").value;
        let name = $("#employee-name-edit").val();
        let phone = $("#employee-phone-edit").val();
        let department = $("#employee-department-edit").val();
        let job = $("#employee-job-edit").val();
        console.log(id, name, phone, department, job);
        fetch(`/api/admin/employee/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                    name: name.toString(),
                    telephone: phone.toString(),
                    departmentId: department.toString(),
                    role: job.toString()
                },
            ),
        }).then(response => response.json()).then((res) => {
            console.log(res);
            alert(res.msg);
            window.location.reload();
        });
    });

    $("#delete-apply").click(() => {
        let id = document.getElementById("employee-id").value;
        console.log(id);
        fetch(`/api/admin/employee/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            }
        }).then(response => response.json()).then((res) => {
            console.log(res);
            alert(res.msg);
            window.location.reload();
        });
    });

    $("#add-apply").click(() => {
        $("#edit-apply").hide();
        $("#save-apply").hide();
        $("#add-save-apply").show();
        $(".employee-edit").show();
        $(".employee-show").hide();
        $("#employee-password-edit").show();
        $("#employee-username-show").hide();
        $("#employee-username-edit").show();
        $("#employee-password-edit-label").show();
        $("#employee-username-show-label").hide();
        $("#employee-username-edit-label").show();
        // 清除输入框内容
        $("#employee-id").val("");
        $("#employee-name-edit").val("");
        $("#employee-phone-edit").val("");
        $("#employee-department-edit").val("");
        $("#employee-job-edit").val("");
        $("#employee-password-edit").val("");
        $("#employee-username-edit").val("");
    });

    $("#add-save-apply").click(() => {
        let username = $("#employee-username-edit").val();
        let password = $("#employee-password-edit").val();
        let name = $("#employee-name-edit").val();
        let phone = $("#employee-phone-edit").val();
        let department = $("#employee-department-edit").val();
        let job = $("#employee-job-edit").val();
        console.log(name, phone, department, job);
        fetch(`/api/admin/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                    username: username.toString(),
                    password: password.toString(),
                    name: name.toString(),
                    telephone: phone.toString(),
                    departmentId: department.toString(),
                    role: job.toString()
                },
            ),
        }).then(response => response.json()).then((res) => {
            console.log(res);
            alert(res.msg);
            window.location.reload();
        });
    });
});
