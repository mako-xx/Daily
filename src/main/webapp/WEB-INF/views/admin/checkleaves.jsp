<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="cu"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>checkleaves</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/admin/checkleaves/checkleaves.css">
</head>

<body>
<div class="container_fluid">
    <div class="row">

        <!-- 左侧 -->
        <div class="col-md-3 col-sm-12 col-xs-12 text-center sidebar"
             style="height:548px; display:flex; flex-direction: column;  margin-top: 50px;">
            <div class="card-left row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <i class="fa fa-dot-circle-o fa-2x icon" aria-hidden="true"></i>
                    <p>Welcome</p>
                    <p>管理员</p>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12 row">
                    <img src="/image/head.jpg" class="head col-md-12 col-sm-12 col-xs-12">
                    <p class="col-md-12 col-sm-12 col-xs-12 name"><c:out value="${sessionScope.user_session.username}"/></p>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12 list-group row">
                    <ul class="nav sidebar-nav" style="margin:0 auto;">
                        <li>
                            <a class="list-group-item" href="/admin/employees"><i
                                    class="fa fa-fw fa-address-book my-list-group-icon"></i>员 工</a>
                        </li>
                        <li>
                            <a class="list-group-item" href="/admin/departments"><i
                                    class="fa fa-fw fa-handshake-o my-list-group-icon"></i>部 门</a>
                        </li>
                        <li>
                            <a class="list-group-item" href="/admin/checkins"><i
                                    class="fa fa-fw fa-id-card-o my-list-group-icon"></i>考 勤</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle list-group-item" data-toggle="dropdown"><i
                                    class="fa fa-fw fa-book my-list-group-icon"></i>请 假
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/admin/leavetypes"><i class="fa fa-fw fa-sitemap my-list-group-icon"></i>请假类型</a></li>
                                <li><a href="/admin/checkleaves"><i class="fa fa-fw fa-check my-list-group-icon"></i>请假审批</a></li>
                            </ul>
                        </li>
                    </ul>
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
                        <h5 class="modal-title" id="myModalLabel">请假办理成功</h5>
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
                        <h5 class="modal-title" id="myModalLabel">请假办理失败</h5>
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

        <!-- 右侧 -->
        <div class="col-md-9 col-sm-12 col-xs-12 text-center content"
             style="height:548px; display:flex;flex-direction: column; margin-top: 50px;">
            <div class=" card-right">
                <div class="clickable">
                    <i class="fa fa-ellipsis-h fa-2x" id="toggle-sidebar"></i>
                </div>
                <div class="row">
                    <!-- 左栏 -->
                    <div class="col-md-5 col-sm-5 col-xs-5">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">请假申请</h4>
                            </div>
                            <div class="card-body">
                                <div class="scrollable" style="height: 330px;">
                                    <div class="list1-group row">
                                        <!-- jstl -->
                                        <cu:page list="${list}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 右栏 -->
                    <!-- 展示员工信息 -->
                    <div class="col-md-7 col-sm-7 col-xs-7">
                        <div class="card">
                            <div class="card-header row">
                                <h4 class="card-title col-md-12 col-sm-12 col-xs-12">请假信息</h4>
                            </div>
                            <div class="scrollable" style="height: 280px;">
                                <!-- 姓名 -->
                                <div class="form-group row">
                                    <!-- 查看请假信息 -->
                                    <div class="leave-show row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <label for="name"
                                                   class="col-md-4 col-sm-4 col-xs-4 col-form-label">编号</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="name">
                                                    <input id="leave-id" type="text" value="${leave.id}" hidden="hidden"/>
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.id}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <label for="phone"
                                                   class="col-md-4 col-sm-4 col-xs-4 col-form-label">姓名</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="phone">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.name}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <label for="department"
                                                   class="col-md-4 col-sm-4 col-xs-4 col-form-label">理由</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.reason}"/>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <label for="name"
                                                   class="col-md-5 col-sm-5 col-xs-5 col-form-label">开始时间</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="name">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.startDate}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <label for="phone"
                                                   class="col-md-5 col-sm-5 col-xs-5 col-form-label">结束时间</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="phone">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.endDate}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <label for="department"
                                                   class="col-md-4 col-sm-4 col-xs-4 col-form-label">类型</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.type}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <label for="department"
                                                   class="col-md-4 col-sm-4 col-xs-4 col-form-label">状态</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department">
                                                    <c:if test="${leave != null}">
                                                        <c:out value="${leave.status}"/>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3 col-sm-3 col-xs-3"></div>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <div id="leave-approve">
                                        <a href="#" data-toggle="tooltip" data-placement="right" title="批准">
                                            <div class="fa fa fa-check" aria-hidden="true"></div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <div id="leave-refuse">
                                        <a href="#" data-toggle="tooltip" data-placement="right" title="不批准">
                                            <div class="fa fa fa-times" aria-hidden="true"></div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <cu:footer/>

</div>
<!-- jquery -->
<script src="/jquery/jquery-3.2.1.min.js"></script>
<script src="/pages/admin/checkleaves/checkleaves.js"></script>
<!-- Bootstrap -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/popper.js"></script>
</body>

</html>