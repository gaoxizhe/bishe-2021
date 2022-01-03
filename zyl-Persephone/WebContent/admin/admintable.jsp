<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function deleteAdmin (AdminID) {
		window.location.href="${pageContext.request.contextPath}/deleteAdmin?AdminID=" + AdminID;
	}
</script>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admintable</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/assets/css/lib/datatable/dataTables.bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="leftbar.jsp"></jsp:include>
<div id="right-panel" class="right-panel">

<jsp:include page="head.jsp"></jsp:include>

<!-- top bar -->
<div class="breadcrumbs">
	<div class="breadcrumbs-inner">
    	<div class="row m-0">
        	<div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>后台管理系统</h1>
                    </div>
                </div>
            </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                 <div class="page-title">
                      <ol class="breadcrumb text-right">
                          <li><a href="${pageContext.request.contextPath}/showAdminIndex">首页</a></li>
                          <li><a>用户列表</a></li>
                          <li class="active">管理员表</li>
                       </ol>
                  </div>
             </div>
         </div>
      </div>
  </div>
</div>
<div class="content">
            <div class="animated fadeIn">
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title">管理员表</strong>
                                <a class="btn btn-danger btn-sm" href="#" data-toggle="modal" data-target="#addadmin">
  								<i class="fa fa-plus"></i> 添加</a>
  								
  						
  								
                            </div>
                            <div class="card-body">
                                <table class="table table-striped table-bordered" id="admintable">
                                    <thead>
                                        <tr>
                                            <th>管理员id</th>
                                            <th>管理员姓名</th>
                                            <th>密码</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${adminList}" var="admin" varStatus="vs">
                                        <tr>
                                        	<td>${admin.adminID}</td>
                                        	<td>${admin.adminName}</td>
                                        	<td>${admin.adminPwd}</td>
                                           
                                            <td>
                                            <div class="btn-group btn-group-justified" role="group">
											  <div class="btn-group" role="group" style="margin-left:10px;margin-right:10px;">
		
											     <button type="button" class="btn btn-default btn-danger" onclick="deleteAdmin('${admin.adminID}');">删除</button>
											  </div>
											 <div class="btn-group" role="group">
											  <button type="button" class="btn btn-default btn-primary" data-toggle="modal" data-target="#updateadmin"
											    data-id="${admin.adminID}"
											    data-name="${admin.adminName}"
											    data-pwd="${admin.adminPwd}"
											
											    >修改</button>
											  </div>
											</div>
											</td>
                                        </tr>
                                       </c:forEach>
                                        </tbody>
                                        
                                </table>
                                
                              
                              <!--修改模态框-->
                                <form id="myUpdateForm" method="post" action="${pageContext.request.contextPath}/changeAdmin">
                                	<input type="hidden" id="update-adid" name="update-adid" value="">
                                	<div class="modal fade" id="updateadmin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title" id="exampleModalLabel">修改管理员信息</h4>
									      </div>
									      <div class="modal-body">
                                			  <input type="hidden" id="update-adid" name="update-adid">
									          <div class="form-group">
									            <label for="recipient-name" class="control-label">修改管理员姓名</label>
									            <input type="text" class="form-control" id="update-adname" name="update-adname" value="">
									          </div>
									          <div class="form-group">
									            <label for="recipient-name" class="control-label">修改管理员密码</label>
									            <input type="text" class="form-control" id="update-adpwd" name="update-adpwd" value="">
									          </div>
									          
									      </div>
									      <div class="modal-footer">
									        <button type="submit" class="btn btn-primary">确定</button>
									      </div>
									    </div>
									  </div>
									</div>
                                </form>
                                
                                
                                 <!-- 添加模态框 -->
                                <form id="myaddForm" method="post" action="${pageContext.request.contextPath}/addAdmin">
                                	<div class="modal fade" id="addadmin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title" id="exampleModalLabel">添加管理员</h4>
									      </div>
									      <div class="modal-body">
									          <div class="form-group">
									            <label for="recipient-name" class="control-label">管理员姓名</label>
									            <input type="text" class="form-control" id="add-adname" name="add-adname">
									          </div>
									          <div class="form-group">
									            <label for="recipient-name" class="control-label">管理员密码</label>
									            <input type="text" class="form-control" id="add-adpwd" name="add-adpwd">
									          </div>
									          
									      </div>
									      <div class="modal-footer">
									        <button type="submit" class="btn btn-primary">确定</button>
									      </div>
									    </div>
									  </div>
									</div>
                                </form>
                                
                                
                                
                            </div>
                        </div>
                    </div>


                </div>
            </div><!-- .animated -->
        </div><!-- .content -->


        <div class="clearfix"></div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/main.js"></script>

 <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/jszip.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/vfs_fonts.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/buttons.html5.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/buttons.print.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/data-table/buttons.colVis.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/init/datatables-init.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $('#bootstrap-data-table-export').DataTable();
});

$("#updateadmin").on('show.bs.modal', function (event) {
    var btnThis = $(event.relatedTarget); //触发事件的按钮
    var modal = $(this); //当前模态框
    var modalid = btnThis.data('id'); //解析出data-id的内容
    var modalname = btnThis.data('name'); //解析出data-name的内容
    var modalpwd = btnThis.data('pwd'); //解析出data-pwd的内容
    
    // 将从"按钮"解析出来的data值赋给"模态框"的input标签中对于的value里面
    $('#update-adid').attr('value', modalid);
    $('#update-adname').attr('value', modalname);
    $('#update-adpwd').attr('value', modalpwd);
});

</script>
   
</body>
</html>