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
  
  $("#leave-approve").click(function () {
    let id = $("#leave-id").val();
    let url = "/api/admin/leave/check/" + id
    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        status:"APPROVE"
      })
        // $("#employee-name-edit").val("123" + res.statusText);
      }).then(response => response.json()).then((res) => {
      console.log(res);
      if (res.code === 200) {
        alert("审核完成")
        window.location.reload();
      } else {
        alert(res.msg)
      }
    })
  })

  $("#leave-refuse").click(function () {
    let id = $("#leave-id").val();
    let url = "/api/admin/leave/check/" + id
    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        status:"REFUSE"
      })
      // $("#employee-name-edit").val("123" + res.statusText);
    }).then(response => response.json()).then((res) => {
      console.log(res);
      if (res.code === 200) {
        alert("审核完成")
        window.location.reload();
      } else {
        alert(res.msg)
      }
    })
  })
});
