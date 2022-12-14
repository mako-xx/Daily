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
  
});
