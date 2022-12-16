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

  $("#checkin-apply").click(function () {
      $("#checkinSuccess").modal("show");
      $("#checkinSuccess .modal-body p").text("打卡成功");
  });

  let run = () => {
    let date = new Date();
    let h = date.getHours();
    let m = date.getMinutes();
    let s = date.getSeconds();
    console.log(h, m, s);
    if (h > 12) {
      $("#hour").css(
        "transform",
        "translateY(-100%) rotate(" + 30 * (h % 12) + "deg)"
      );
    } else {
      $("#hour").css(
        "transform",
        "translateY(-100%) rotate(" + 30 * h + "deg)"
      );
    }
    $("#minute").css("transform", "translateY(-100%) rotate(" + 6 * m + "deg)");
    $("#second").css("transform", "translateY(-100%) rotate(" + 6 * s + "deg)");
  };
  setInterval(run, 1000);
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
    // $("#employee-name-edit").val("123" + res.statusText);
  })
})



$("#checkin-apply").click(()=>{
  let url = "http://localhost:8080/api/employee/attend"
  console.log("aaa")
  fetch(url).then(response=>response.json()).then(data=>console.log(data))
  fetch(`/api/employee/attend`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({

        },
    ),
  }).then(response => response.json()).then((res) => {
    console.log(res);
    // $("#employee-name-edit").val("123" + res.statusText);
  })
})






