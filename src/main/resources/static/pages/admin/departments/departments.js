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
    $(".department-edit").show();
    $(".department-show").hide();
  });

  $("#save-apply").click(function () {
    $("#edit-apply").show();
    $("#save-apply").hide();
    $("#add-save-apply").hide();
    $(".department-edit").hide();
    $(".department-show").show();
  });

    $("#save-apply").click(() => {
        let id = document.getElementById("department-id").value;
        let name = $("#department-name-edit").val();
        let superiorId = $("#department-father-edit").val();
        fetch(`/api/admin/department/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                    name: name.toString(),
                    superiorId: superiorId.toString(),
            },
            ),
        }).then((response) => {
            console.log(response);
            if (response.status === 200) {
                console.log(response.msg);
                alert("修改成功");
                window.location.reload();
            }
        });
    });

    $("#delete-apply").click(() => {
        let id = document.getElementById("department-id").value;
        console.log(id);
        fetch(`/api/admin/department/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        }).then((response) => {
            console.log(response);
            if (response.status === 200) {
                console.log(response.msg);
                alert("删除成功");
                window.location.reload();
            }
        });
    });

    $("#add-apply").click(() => {
        $("#edit-apply").hide();
        $("#save-apply").hide();
        $("#add-save-apply").show();
        $(".department-edit").show();
        $(".department-show").hide();
    });

    $("#add-save-apply").click(() => {
        let name = $("#department-name-edit").val();
        let superiorId = $("#department-father-edit").val();
        console.log(name, superiorId);
        fetch(`/api/admin/department`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                    name: name.toString(),
                    superiorId: superiorId.toString(),
                },
            ),
        }).then((response) => {
            console.log(response);
            if (response.status === 200) {
                console.log(response.msg);
                alert("添加成功");
                window.location.reload();
            }
        });
    });
});
