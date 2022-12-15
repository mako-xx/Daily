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
    $(".department-edit").show();
    $(".department-show").hide();
  });

  $("#save-apply").click(function () {
    $("#edit-apply").show();
    $("#save-apply").hide();
    $(".department-edit").hide();
    $(".department-show").show();
  });

});
