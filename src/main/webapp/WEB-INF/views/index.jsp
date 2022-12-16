<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="cu" %>
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
                <form class="form-horizontal validate-form login-form m-t-20 row" action="/api/login" method="post">
                    <div class="login-border col-md-12 col-sm-12 col-xs-12">
                        <div class="form-group wrap-input">

                            <input class="form-control input" type="text" required=""
                                   onkeyup="value=value.replace(/[^a-zA-Z\d-]/g,'')" placeholder="User ID"
                                   name="username" style="margin-left: 30px;">
                            <span class="focus-input" style="margin-left: 30px;"></span>
                            <span class="symbol-input" style="margin-left: 30px;"><i class="fa fa-user"
                                                                                     aria-hidden="true"></i></span>

                        </div>
                        <div class="form-group wrap-input">

                            <input class="form-control input" type="password" required="" placeholder="Password"
                                   name="password" style="margin-left: 30px;">
                            <span class="focus-input" style="margin-left: 30px;"></span>
                            <span class="symbol-input" style="margin-left: 30px;"><i class="fa fa-user"
                                                                                     aria-hidden="true"></i></span>

                        </div>

                        <div class="form-group wrap-input" style="margin-left: 20px;">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember" value="1">
                                        <span class="cr"></span>
                                        <span class="text">自动登陆</span>
                                    </label>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="form-group text-center m-t-20 container-login-form-btn">
                            <button class="btn btn-common btn-block" type="submit" id="login-form-btn"
                                    style="margin-left: 30px;">登陆
                            </button>
                        </div>
                    </div>

                    <c:if test="${!(msg eq null)}">
                        <label for="username" style="color: #e22a6f">${msg}</label>
                    </c:if>
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
