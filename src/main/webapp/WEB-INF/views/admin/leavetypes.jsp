<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="cu"%>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>leavetypes</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/admin/leavetypes/leavetypes.css">
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
                        <p class="col-md-12 col-sm-12 col-xs-12 name">Mako</p>
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
                                    <h4 class="card-title">请假类型</h4>
                                </div>
                                <div class="card-body">
                                    <div class="scrollable" style="height: 280px;">
                                        <div class="list1-group row">
                                            <!-- jstl -->
                                            <c:forEach var="item" items="${leaveTypes}">
                                                <a href="${'/admin/leavetypes?id='.concat(item.id)}"
                                                   class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                        class="fa fa-bars" aria-hidden="true"
                                                        style="float:left; margin-top:10px;"></i>${item.name}</a>
                                            </c:forEach>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>
<%--                                            <a href="#"--%>
<%--                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i--%>
<%--                                                    class="fa fa-bars" aria-hidden="true"--%>
<%--                                                    style="float:left; margin-top:10px;"></i>部门1</a>--%>

                                        </div>
                                    </div>
                                </div>
                                <div id="add-apply">
                                    <a href="#" data-toggle="tooltip" data-placement="right" title="添加员工">
                                        <div class="fa fa-plus" aria-hidden="true"></div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- 右栏 -->
                        <div class="col-md-7 col-sm-7 col-xs-7">
                            <div class="card">
                                <div class="card-header row">
                                    <h4 class="card-title col-md-4 col-sm-4 col-xs-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4">类型信息</h4>
                                    <div class="col-md-2 col-sm-2 col-xs-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
                                        <div id="delete-apply" class="delete">
                                            <a href="#" data-toggle="tooltip" data-placement="right" title="删除此类型">
                                                <i class="fa fa-trash fa-lg delete-icon"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="scrollable" style="height: 280px;">
                                    <div class="form-group row">
                                        <!-- 修改 -->
                                        <div class="department-edit" style="display:none;">
                                            <label for="leavetype-name-edit"
                                                class="col-md-3 col-sm-3 col-xs-3 col-form-label">名称</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <input type="text" class="form-control" id="leavetype-name-edit" placeholder="请输入请假类型">
                                            </div>
                                        </div>

                                        <!-- 查看 -->
                                        <div class="department-show">
                                            <label for="leavetype-name-show"
                                                class="col-md-6 col-sm-6 col-xs-6 col-form-label">名称</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="leavetype-name-show">
                                                    <c:if test="${leavetype != null}">
                                                        <input hidden="hidden" type="text" value="${leavetype.id}" id="leavetype-id"/>
                                                        <c:out value="${leavetype.name}"/>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div id="save-apply" style="display:none;">
                                    <a href="#" data-toggle="tooltip" data-placement="right" title="保存修改">
                                        <div class="fa fa fa-floppy-o" aria-hidden="true"></div>
                                    </a>
                                </div>
                                <div id="edit-apply">
                                    <a href="#" data-toggle="tooltip" data-placement="right" title="修改信息">
                                        <div class="fa fa fa-pencil" aria-hidden="true"></div>
                                    </a>
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
    <script src="/pages/admin/leavetypes/leavetypes.js"></script>
    <!-- Bootstrap -->
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bootstrap/js/popper.js"></script>
</body>

</html>