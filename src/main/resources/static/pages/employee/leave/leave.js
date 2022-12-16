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

  $(".card").click(function () {
    // 只能有一个card choosen
    $(".card").removeClass("choosen");
    $(this).addClass("choosen");
  });

  $("#leave-apply").click(function () {
    let start = $("#startDate").val();
    let end = $("#endDate").val();
    let type = $("#leaveTypeSelect").val();
    let reason = $("#leaveReason").val();
    console.log(start, end, type, reason);

    if (start == "") {
      $("#submitFail").modal("show");
      $("#submitFail .modal-body p").text("请输入起始日期");
      return;
    }
    else if (end == "") {
      $("#submitFail").modal("show");
      $("#submitFail .modal-body p").text("请输入结束日期");
      return;
    }
    else if (type == "") {
      $("#submitFail").modal("show");
      $("#submitFail .modal-body p").text("请选择请假类型");
      return;
    }
    else if (reason == "") {
      $("#submitFail").modal("show");
      $("#submitFail .modal-body p").text("请输入请假原因");
      return;
    }
    // 开始日期不能大于结束日期
    else if (start > end) {
      $("#submitFail").modal("show");
      $("#submitFail .modal-body p").text("开始日期不能大于结束日期");
      return;
    }
    else {
      $("#submitSuccess").modal("show");
      $("#submitSuccess .modal-body p").text("请假办理成功，等待审核");
      // 清空input
      $("#startDate").val("");
      $("#endDate").val("");
      $("#leaveTypeSelect").val("");
      $("#leaveReason").val("");
      // 隐藏请假申请表单
      $("#leave-apply-form").hide();
    }

  });

  $(".btn-save").click(()=>{
    let name = 'Mako';
    let phone = $("#phoneInput").val();
    let department = "1";
    let url = "http://localhost:8080/api/employee/update"
    console.log(phone)
    fetch(url).then(response=>response.json()).then(data=>console.log(data))
    fetch(`/api/employee/update`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
            name: name,
            telephone: phone,
            departmentId: department,
          },
      ),
    }).then(response => response.json()).then((res) => {
      console.log(res);
      if(res.code == 200){
        alert("修改成功");
      }else{
        alert("修改失败");
      }
    })
  })
  
});
$("#leave-apply").click(()=>{
  let url = "http://localhost:8080/api/employee/leave"
  console.log("aaa")
  let date1 = $("#startDate").val()
  let date2 = $("#endDate").val();
  let select = Number($("#leaveTypeSelect").val());
  let reason = $("#leaveReason").val();
  console.log(date1)
  console.log(date2)
  console.log(select)
  console.log(reason)
  fetch(url).then(response=>response.json()).then(data=>console.log(data))
  fetch(`/api/employee/leave`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
          startDate:date1,
          endDate:date2,
          type:select,
          reason:reason
        },
    ),
  }).then(response => response.json()).then((res) => {
    console.log(res);
    if (res.status == 200) {
      alert("请假申请成功");
    }else{
      alert("请假申请失败");
    }
  })
})
