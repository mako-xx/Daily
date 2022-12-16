<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="cu"%>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/login/login.css">
</head>

<body>
<div class="container">
    <!-- 左侧-->
    <div class="row">
        <div class="col-md-6 col-sm-12 col-xs-12 text-center" style="margin-top: 8%;">
            <div class="card-left row">
                <!-- bootstrap轮播图 -->
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators dot">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item bg1 active"></div>
                        <div class="item bg2"></div>
                        <div class="item bg3"></div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev"></a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next"></a>
                </div>
            </div>
        </div>

        <!--右侧-->
        <div class="col-md-6 col-sm-12 col-xs-12 text-center" style="margin-top: 8%;">
            <div class="card-right row">
                <img src="/image/head.jpg" class="head"/>
                <h2>Hello Again!</h2>
                <form class="login-form validate-form">
                    <div class="login-border">
                        <div class="wrap-input">
                            <input class="input" type="text" name="email" placeholder="用户名 / Name">
                            <span class="focus-input"></span>
                            <span class="symbol-input">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </span>
                        </div>

                        <div class="wrap-input validate-input">
                            <input class="input" type="password" name="pass" placeholder="密码 / Password">
                            <span class="focus-input"></span>
                            <span class="symbol-input">
                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                </span>
                        </div>
                    </div>

                    <div class="container-login-form-btn">
                        <button id="auto-login-form-btn">
                            自动登录
                        </button>
                    </div>

                    <div class="container-login-form-btn">
                        <button id="login-form-btn">
                            登录 / sign in
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <cu:footer/>
</div>
<!-- jquery -->
<script src="/jquery/jquery-3.2.1.min.js"></script>
<script src="/pages/login/login.js"></script>
<!-- Bootstrap -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/popper.js"></script>
</body>

</html>
