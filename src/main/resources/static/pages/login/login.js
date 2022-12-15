// 防止在HTML DOM还未加载完成就执行了JS语句
$(document).ready(function () {
  // your js starts here

  // bootstrap轮播图
  $(".carousel").carousel({
    interval: 2000,
    pause: "hover",
    wrap: true,
  });
  
});
