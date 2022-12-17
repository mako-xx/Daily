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

  $(".btn-save").click(()=>{
    let name = 'staff';
    let phone = $("#phoneInput").val();
    let url = "http://localhost:8080/api/employee/update"

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
        $("#myModal").modal("hide");
        window.location.reload();
      }else{
        alert("修改失败");
        window.location.reload();
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
    if (res.code == 200) {
      alert("请假申请成功");
      window.location.reload();
    }else{
      alert("请假申请失败");
      window.location.reload();
    }
  })
})
