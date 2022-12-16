<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </select>
    <link rel="stylesheet" href="/pages/welcome/welcome.css">
</head>

<body >
<div class="container">
    <div class="row text-center">
        <div class="col-md-12 col-sm-12 col-xs-12"><img src="/image/logo.png" width=138/></div>
        <h2 class="col-md-12 col-sm-12 col-xs-12">Welcome</h2>
        <h4 class="col-md-12 col-sm-12 col-xs-12">欢迎使用网上考勤系统~</h4>
    </div>
</div>
<!-- jquery -->
<script src="/jquery/jquery-3.2.1.min.js"></script>
<script src="/pages/welcome/welcome.js"></script>
<!-- Bootstrap -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/popper.js"></script>
<script>
    window.onload=function () {
        setTimeout(getRoleAndMove, 3000)
    }
    function getRoleAndMove() {

        var role = "<%=session.getAttribute("user_role")%>"
        let target;
        if (role === 'ADMIN') {
            window.location.href='/admin/employees'
        } else {
            window.location.href='/employee/check'
        }
    }

</script>
</body>

</html>