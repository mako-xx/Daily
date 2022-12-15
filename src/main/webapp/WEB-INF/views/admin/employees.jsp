<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>employees</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/pages/admin/employees/employees.css">
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
                                <a class="list-group-item" href="/view/test4"><i
                                        class="fa fa-fw fa-address-book my-list-group-icon"></i>员 工</a>
                            </li>
                            <li>
                                <a class="list-group-item" href="/view/test5"><i
                                        class="fa fa-fw fa-handshake-o my-list-group-icon"></i>部 门</a>
                            </li>
                            <li>
                                <a class="list-group-item" href="/view/test7"><i
                                        class="fa fa-fw fa-id-card-o my-list-group-icon"></i>考 勤</a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle list-group-item" data-toggle="dropdown"><i
                                        class="fa fa-fw fa-book my-list-group-icon"></i>请 假
                                    <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="/view/test6"><i class="fa fa-fw fa-sitemap my-list-group-icon"></i>请假类型</a></li>
                                    <li><a href="/view/test8"><i class="fa fa-fw fa-check my-list-group-icon"></i>请假审批</a></li>
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
                                    <h4 class="card-title">通讯录</h4>
                                </div>
                                <div class="card-body">
                                    <div class="scrollable" style="height: 280px;">
                                        <div class="list1-group row">
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>
                                            <a href="#"
                                                class="col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action"><i
                                                    class="fa fa-user" aria-hidden="true"
                                                    style="float:left; margin-top:10px;"></i>员工1</a>

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
                                    <h4 class="card-title col-md-4 col-sm-4 col-xs-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4">员工信息</h4>
                                    <div class="col-md-2 col-sm-2 col-xs-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-22">
                                        <div class="delete">
                                            <a href="#" data-toggle="tooltip" data-placement="right" title="删除此员工">
                                                <i class="fa fa-trash fa-lg delete-icon"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="scrollable" style="height: 280px;">
                                    <!-- 姓名 -->
                                    <div class="form-group row">
                                        <!-- 修改员工信息 -->
                                        <div class="employee-edit" style="display:none;">
                                            <label for="name"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">姓名</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <input type="text" class="form-control" id="name" placeholder="姓名">
                                            </div>
                                            <!-- 电话 -->
                                            <label for="phone"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">电话</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <input type="text" class="form-control" id="phone" placeholder="电话">
                                            </div>
                                            <!-- 部门id -->
                                            <!-- 使用选择框 -->
                                            <label for="department"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">部门</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <select class="form-control" id="department">
                                                    <option>部门1</option>
                                                    <option>部门2</option>
                                                    <option>部门3</option>
                                                    <option>部门4</option>
                                                    <option>部门5</option>
                                                    <option>部门6</option>
                                                    <option>部门7</option>
                                                    <option>部门8</option>
                                                    <option>部门9</option>
                                                    <option>部门10</option>
                                                </select>
                                            </div>
                                            <!-- 职务，员工和管理员两种 -->
                                            <label for="job"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">职务</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <select class="form-control" id="job">
                                                    <option>员工</option>
                                                    <option>管理员</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- 查看员工信息 -->
                                        <div class="employee-show">
                                            <label for="name"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">姓名</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="name" placeholder="姓名"></div>
                                            </div>
                                            <!-- 电话 -->
                                            <label for="phone"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">电话</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="phone" placeholder="电话"></div>
                                            </div>
                                            <!-- 部门id -->
                                            <!-- 使用选择框 -->
                                            <label for="department"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">部门</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="department">
                                                    <div>部门1</div>
                                                </div>
                                            </div>
                                            <!-- 职务，员工和管理员两种 -->
                                            <label for="job"
                                                class="col-md-2 col-sm-2 col-xs-2 col-form-label">职务</label>
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-control" id="job">
                                                    <div>员工</div>
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
    <script src="/pages/admin/employees/employees.js"></script>
    <!-- Bootstrap -->
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bootstrap/js/popper.js"></script>
</body>

</html>