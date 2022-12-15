<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>checkins</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/select2/select2.min.css" rel="stylesheet" type="text/css">
    <link href="/css-hamburgers/hamburgers.min.css" rel="stylesheet" type="text/css">
    <link href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </select>
    <link rel="stylesheet" href="/pages/admin/checkins/checkins.css">
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
                    <p>员工</p>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12 row">
                    <img src="./image/head.jpg" class="head col-md-12 col-sm-12 col-xs-12">
                    <p class="col-md-12 col-sm-12 col-xs-12 name">Mako</p>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12 list-group row">
                    <ul class="nav sidebar-nav" style="margin:0 auto;">
                        <li>
                            <a class="list-group-item" href="#"><i
                                    class="fa fa-fw fa-address-book my-list-group-icon"></i>员 工</a>
                        </li>
                        <li>
                            <a class="list-group-item" href="#"><i
                                    class="fa fa-fw fa-handshake-o my-list-group-icon"></i>部 门</a>
                        </li>
                        <li>
                            <a class="list-group-item" href="#"><i
                                    class="fa fa-fw fa-id-card-o my-list-group-icon"></i>考 勤</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle list-group-item" data-toggle="dropdown"><i
                                    class="fa fa-fw fa-book my-list-group-icon"></i>请 假
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#"><i class="fa fa-fw fa-sitemap my-list-group-icon"></i>请假审批</a></li>
                                <li><a href="#"><i class="fa fa-fw fa-check my-list-group-icon"></i>请假类型</a></li>
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
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">查询指定日期范围的考勤表</h4>
                            </div>
                            <div class="card-body">
                                <div style="height: 280px;">
                                    <div class="row" style="margin:0 auto;">
                                        <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top:30px;">
                                            <div class="form-group row">
                                                <label for="startDate" class="col-form-label">起始日</label>
                                                <div class="col-md-12 col-sm-12 col-xs-12">
                                                    <input type="date" class="form-control" id="startDate">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top:30px;">
                                            <div class="form-group row">
                                                <label for="endDate" class="col-form-label">结束日</label>
                                                <div class="col-md-12 col-sm-12 col-xs-12">
                                                    <input type="date" class="form-control" id="endDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="search-apply">
                                <a href="#" data-toggle="tooltip" data-placement="right" title="添加员工">
                                    <div class="fa fa-search" aria-hidden="true"></div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- 右栏 -->
                    <!-- 展示员工信息 -->
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">考勤表</h4>
                            </div>
                            <div class="scrollable" style="height: 330px;">
                                <div class="card-history">
                                    <div class="row">
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <div class="name-tag">员工名称:</div>
                                            <div class="name-content">Mako</div>
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
                                            <div class="id-tag">考勤时间:</div>
                                            <div class="id-content">#1</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
<!-- jquery -->
<script src="/jquery/jquery-3.2.1.min.js"></script>
<script src="/pages/admin/checkins/checkins.js"></script>
<!-- Bootstrap -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/popper.js"></script>
</body>

</html>