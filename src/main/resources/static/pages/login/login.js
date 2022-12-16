// 防止在HTML DOM还未加载完成就执行了JS语句
$(document).ready(function () {

  $(".carousel").carousel({
    interval: 2000,
    pause: "hover",
    wrap: true,
  });

  $(".container-login-form-btn").click(function (){
    console.log("login-form-btn");
    let username = $("#username").val();
    let password = $("#password").val();
    console.log(username);
    console.log(password);
    fetch("/api/login",{
        method:"POST",
        body:JSON.stringify({
            username: username,
            password: password,
            remember: false,
        })
        }).then((res)=>{
        console.log(res);
        // if(res.status==200){
        //     window.location.href="/admin/employees";
        // }
        })
    })
});
