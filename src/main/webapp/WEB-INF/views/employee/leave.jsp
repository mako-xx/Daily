<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>leave</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/employee/leave/leave.css">
</head>

<body>
    <div class="container_fluid">
        <div class="row">

            <!-- 左侧 -->
            <div class="col-md-3 col-sm-12 col-xs-12 text-center sidebar"
                style="height:596px; display:flex; flex-direction: column;  margin-top: 50px;">
                <div class="card-left row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <i class="fa fa-dot-circle-o fa-2x icon" aria-hidden="true"></i>
                        <p>Welcome</p>
                        <p>员工</p>
                    </div>
                    <div class="col-md-12 col-sm-12 col-xs-12 row">
                        <img src="/image/head.jpg" class="head col-md-12 col-sm-12 col-xs-12">
                        <p class="col-md-12 col-sm-12 col-xs-12 name">Mako</p>
                    </div>
                    <div class="col-md-12 col-sm-12 col-xs-12 list-group row">
                        <ul class="nav sidebar-nav" style="margin:0 auto;">
                            <li>
                                <a class="list-group-item" href="/view/test2"><i
                                        class="fa fa-fw fa-id-card-o my-list-group-icon"></i>考 勤</a>
                            </li>
                            <li>
                                <a class="list-group-item" href="/view/test3"><i
                                        class="fa fa-fw fa-book my-list-group-icon"></i>请 假</a>
                            </li>
                        </ul>
                    </div>
                    <div class="setting" data-toggle="modal" data-target="#myModal">
                        <a href="#" data-toggle="tooltip" data-placement="right" title="个人信息">
                            <i class="fa fa-cog fa-lg dropdown-icon"></i>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <!-- 用于显示请假办理提交成功 -->
            <div class="modal fade" id="submitSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document" style="width: 500px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-dismiss="modal">返回</button>
                            <h5 class="modal-title" id="myModalLabelSuccess">请假办理成功</h5>
                            <div type="button" class="btn-save"></div>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <p style="color: #e0b756; font-size: 20px;"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <!-- 用于显示请假办理提交失败 -->
            <div class="modal fade" id="submitFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document" style="width: 500px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-dismiss="modal">返回</button>
                            <h5 class="modal-title" id="myModalLabelFail">请假办理失败</h5>
                            <div type="button" class="btn-save"></div>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <p style="color: #b55043; font-size: 20px;"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <!-- 用于修改个人资料 -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document" style="width: 500px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-dismiss="modal">取消</button>
                            <h5 class="modal-title" id="myModalLabel">编辑个人资料</h5>
                            <button type="button" class="btn-save">保存</button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-5 col-sm-5 col-xs-5">
                                    <img src="/image/head.jpg" class="head">
                                </div>
                                <div class="col-md-7 col-sm-7 col-xs-7">
                                    <div class="name">Mako</div>
                                    <div class="position">员工</div>
                                </div>
                            </div>
                            <form>
                                <div class="form-group">
                                    <label for="phoneInput">电话</label>
                                    <div class="input-group">
                                        <select class="custom-select clickable" id="countryCodeSelect">
                                            <option selected>中国 +86</option>
                                            <option value="1">美国 +1</option>
                                            <option value="44">英国 +44</option>
                                            <option value="33">法国 +33</option>
                                        </select>
                                        <input type="tel" class="form-control" id="phoneInput" placeholder="请输入电话">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>选择部门</label>
                                    <div class="card-lists row">
                                        <jsp:useBean id="map" class="java.util.HashMap" scope="request" />
                                        <c:set target="${map}" property="/image/department1.png" value="部门1" />
                                        <c:set target="${map}" property="/image/department2.png" value="部门2" />
                                        <c:forEach var="item" items="${map}">
                                            <div class="card col-md-3 clickable">
                                                <img src="${item.key}" class="card-img img-circle" alt="${item.value}">
                                                <p>${item.value}</p>
                                            </div>
                                        </c:forEach>
<%--                                        <div class="card col-md-3 clickable">--%>
<%--                                            <img src="/image/department1.png" class="card-img img-circle" alt="部门1">--%>
<%--                                            <p>部门1</p>--%>
<%--                                        </div>--%>
<%--                                        <div class="card col-md-3 clickable">--%>
<%--                                            <img src="/image/department2.png" class="card-img img-circle" alt="部门2">--%>
<%--                                            <p>部门2</p>--%>
<%--                                        </div>--%>
<%--                                        <div class="card col-md-3 clickable">--%>
<%--                                            <img src="/image/department3.png" class="card-img img-circle" alt="部门3">--%>
<%--                                            <p>部门3</p>--%>
<%--                                        </div>--%>
<%--                                        <div class="card col-md-3 clickable">--%>
<%--                                            <img src="/image/department4.png" class="card-img img-circle" alt="部门4">--%>
<%--                                            <p>部门4</p>--%>
<%--                                        </div>--%>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 右侧 -->
            <div class="col-md-9 col-sm-12 col-xs-12 text-center content"
                style="height:596px; display:flex;flex-direction: column; margin-top: 50px;">
                <div class=" card-right">
                <div class="clickable">
                    <i class="fa fa-ellipsis-h fa-2x" id="toggle-sidebar"></i>
                </div>
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="card">
                            <div class="card-header">
                                <h4>办理请假</h4>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="form-group row">
                                        <label for="startDate" class="col-form-label">起始日</label>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <input type="date" class="form-control" id="startDate">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="endDate" class="col-form-label">结束日</label>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <input type="date" class="form-control" id="endDate">
                                        </div>
                                    </div>
                                    <!-- 请假类型 -->
                                    <div class="form-group row">
                                        <label for="leaveTypeSelect" class="col-form-label">请假类型</label>
                                        <select class="custom-select col-md-12 col-sm-12 col-xs-12"
                                            id="leaveTypeSelect">
                                            <option selected>事假</option>
                                            <option value="1">病假</option>
                                            <option value="2">婚假</option>
                                            <option value="3">产假</option>
                                            <option value="4">丧假</option>
                                            <option value="5">年假</option>
                                            <option value="6">调休</option>
                                            <option value="7">其他</option>
                                        </select>
                                    </div>
                                    <!-- 请假理由 -->
                                    <div class="form-group row">
                                        <label for="leaveReason" class="col-form-label">请假理由</label>
                                        <textarea class="form-control" id="leaveReason" rows="3"></textarea>
                                    </div>
                                    <div id="leave-apply">
                                        <a href="#" data-toggle="tooltip" data-placement="right" title="提交申请">
                                            <div class="fa fa-check" aria-hidden="true"></div>
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="scrollable">
                            <div class="card-history">
                                <div class="row">
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="date-tag">起始日:</div>
                                                <div class="date-content">2022年10月1日</div>
                                            </div>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="date-tag">结束日:</div>
                                                <div class="date-content">2022年10月1日</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-3 col-xs-3">
                                        <div class="state">正常</div>
                                    </div>
                                    <hr width="100%">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="id-tag">编号:</div>
                                        <div class="id-content">#1</div>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="type-tag">请假类型:</div>
                                        <div class="type-content">#1</div>
                                    </div>
                                    <!-- <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="employee-id-tag">员工id:</div>
                                            <div class="employee-id-content">#1</div>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="employee-name-tag">员工姓名:</div>
                                            <div class="employee-name-content">#1</div>
                                        </div> -->
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <a href="#">
                                            <div class="employee-tag">员工信息</div>
                                            <i class="fa fa-angle-right" aria-hidden="true"></i>
                                        </a>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <a href="#">
                                            <div class="reason-tag">请假理由</div>
                                            <i class="fa fa-angle-right" aria-hidden="true"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <!-- Footer -->
        <footer class="footer">
            <div class="container-fluid">
                <div class="social-area pull-right">

                    <div class="btn-social  btn-pinterest btn-simple">
                        <a href="https://github.com/mako-xx/Daily" data-toggle="tooltip" data-placement="left" title="项目地址">
                            <div class="fa fa-github"></div>
                        </a>
                    </div>
                    <div class="btn-social  btn-pinterest btn-simple">
                        <a href="https://faustpromaxpx.github.io" data-toggle="tooltip" data-placement="left" title="开发者个人网站">
                            <div class="fa fa-user-circle"></div>
                        </a>
                    </div>
                </div>
            </div>
        </footer>

    </div>
    <!-- jquery -->
    <script src="/jquery/jquery-3.2.1.min.js"></script>
    <script src="/pages/employee/leave/leave.js"></script>
    <!-- Bootstrap -->
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bootstrap/js/popper.js"></script>
</body>

</html>