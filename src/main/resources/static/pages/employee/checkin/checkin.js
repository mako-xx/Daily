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
