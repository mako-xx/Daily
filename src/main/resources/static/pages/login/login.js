// 防止在HTML DOM还未加载完成就执行了JS语句
const Login = async (data)=>{
    fetch("/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }
    )
    // cache: 'no-cache',
    // credentials: 'same-origin',ponse => response.json())
    .then((res) => {
        debugger;
        console.log(res);
    }).catch((err) => {
            debugger;
            console.error(err);
        }
    )
}
$(document).ready(function () {

    $(".carousel").carousel({
        interval: 2000,
        pause: "hover",
        wrap: true,
    });

    $("#login-form-btn").click(function () {
        console.log("login-form-btn");
        let username = $("#username").val();
        let password = $("#password").val();
        console.log(username);
        console.log(password);
        // debugger;
        Login({
            username: "1234",
            password: "1234",
            remember: true,
        });
        // 从session里拿用户信息
        console.log(sessionStorage.getItem("user_session"));
    })
});
