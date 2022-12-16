// 防止在HTML DOM还未加载完成就执行了JS语句
$(document).ready(function () {

    $(".carousel").carousel({
        interval: 2000,
        pause: "hover",
        wrap: true,
    });

    $(".container-login-form-btn").click(function () {
        console.log("login-form-btn");
        let username = $("#username").val();
        let password = $("#password").val();
        console.log(username);
        console.log(password);
        const axios = require("axios");
        axios.post("/api/login", {
            // method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                username:"",
                password:"",
                remember: true,
            }),
            // cache: 'no-cache',
            // credentials: 'same-origin',
        }).then(response => response.json()).then((res) => {
            console.log(res);
        }).catch((err) => {
            console.error(err);
        }
        );
        // 从session里拿用户信息
        console.log(sessionStorage.getItem("user_session"));
    })


});
