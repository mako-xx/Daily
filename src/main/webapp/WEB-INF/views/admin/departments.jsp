<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="cu"%>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>departments</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/admin/departments/departments.css">
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
                        <!-- 展示通讯录，点击可以查看员工信息 -->
                        <!-- 通讯录列表采用scrollable -->
                        <div class="col-md-5 col-sm-5 col-xs-5">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">部门列表</h4>
                                </div>
                                <div class="card-body">
                                    <div class="scrollable" style="height: 280px;">
                                        <div class="list1-group row">
                                            <!-- jstl -->
                                            <jsp:useBean id="map" class="java.util.HashMap" scope="request" />
                                            <c:set target="${map}" property="#" value="部门1" />
                                            <c:forEach var="item" items="${departments}">
                                                <a href="${'/admin/departments?id='.concat(item.id)}"
                                                   class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                        class="fa fa-users" aria-hidden="true"
                                                        style="float:left; margin-top:10px;"></i>${item.name}</a>
                                            </c:forEach>
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
                        <!-- 展示员工信息 -->
                        <div class="col-md-7 col-sm-7 col-xs-7">
                            <div class="card">
                                <div class="card-header row">
                                    <h4 class="card-title col-md-4 col-sm-4 col-xs-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4">部门信息</h4>
                                    <div class="col-md-2 col-sm-2 col-xs-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
                                        <div id="delete-apply">
                                            <a href="#" data-toggle="tooltip" data-placement="right" title="删除此员工">
                                                <i class="fa fa-trash fa-lg delete-icon"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="scrollable" style="height: 280px;">
                                    <!-- 姓名 -->
                                    <div class="form-group row">
                                        <!-- 修改部门信息 -->
                                        <div class="department-edit" style="display:none;">
                                            <c:if test="${department != null}">
                                                <input hidden="hidden" value="${department.id}" id="department-id">
                                            </c:if>
                                            <label for="department-name-edit"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">名称</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <c:if test="${department != null}">
                                                    <input type="text" class="form-control" id="department-name-edit" placeholder="部门名称" value="${department.name}">
                                                </c:if>
                                                <c:if test="${department == null}">
                                                    <input type="text" class="form-control" id="department-name-edit" placeholder="部门名称">
                                                </c:if>
                                            </div>

                                            <!-- 部门id -->
                                            <!-- 使用选择框 -->
                                            <label for="department-father-edit"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">父级</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <select class="form-control" id="department-father-edit">
                                                    <c:forEach var="item" items="${departments}">
                                                        <c:if test="${item.id != department.id}">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                        </div>

                                        <!-- 查看部门信息 -->
                                        <div class="department-show">
                                            <label for="department-name-show"
                                                class="col-md-3 col-sm-3 col-xs-3 col-form-label">名称</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department-name-show" placeholder="部门名称">
                                                    <c:if test="${department != null}">
                                                        <c:out value="${department.name}"/>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <!-- 部门id -->
                                            <!-- 使用选择框 -->
                                            <label for="department-father-show"
                                                class="col-md-3 col-sm-3 col-xs-3 col-form-label">父级</label>

                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department-father-show">
                                                    <div><c:out value="${department.superior}"/></div>
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

                                <div id="add-save-apply" style="display:none;">
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
    <script src="/pages/admin/departments/departments.js"></script>
    <!-- Bootstrap -->
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bootstrap/js/popper.js"></script>
</body>

</html>