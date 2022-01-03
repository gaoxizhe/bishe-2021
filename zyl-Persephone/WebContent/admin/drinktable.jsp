<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Drinktable</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/drinktable.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/lib/fileinput.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/lib/theme.min.css">
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
                          <li><a>商品订单</a></li>
                          <li class="active">商品表</li>
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
                                <strong class="card-title mr-20">商品表</strong>
                                <a class="btn btn-danger btn-sm" href="#" data-toggle="modal" data-target="#adddrink">
  								<i class="fa fa-plus"></i> 添加</a>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped table-bordered" id="drinktable">
                                
                                    <tbody>
                                    <c:forEach items="${drinkList}" var="drink" varStatus="vs">
                                        <tr>
                                        	<td>
                                        		<img style="width:60px;height:60px;border-radius:5px;" alt="img" src="${pageContext.request.contextPath}/client/img/${drink.picAddres}" class="pic">
                                        	</td>
                                        	<td>${drink.drinkID}</td>
                                            <td class="name">${drink.drinkName}</td>
                                            <td>${drink.drinkPrice_Super}</td>
                                            <td>${drink.drinkPrice_Big}</td>
                                            <td>${drink.drinkPrice_Medium}</td>
                                            <td>${drink.drinkType}</td>
                                            <td class="spec">${drink.drinkDesc}</td> 
                                        	<td class="addr">${drink.picAddres}</td>
                                            <td>
                                            <div class="btn-group btn-group-justified" role="group">
											  <div class="btn-group" role="group" style="margin-left:10px;margin-right:10px;">
											    <button type="button" class="btn btn-default btn-danger" onclick="deleteDrink('${drink.drinkID}')">删除</button>
											  </div>
											  <div class="btn-group" role="group">
											    <button type="button" class="btn btn-default btn-primary" data-toggle="modal" data-target="#updatedrink"
											    data-pic="${drink.picAddres}"
											    data-id="${drink.drinkID}"
											    data-name="${drink.drinkName}"
											    data-super="${drink.drinkPrice_Super}"
											    data-big="${drink.drinkPrice_Big}"
											    data-mediu="${drink.drinkPrice_Medium}"
											    data-type="${drink.drinkType}"
											    data-spec="${drink.drinkDesc}"
											    >修改</button>
											  </div>
											</div>
											</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                
                                <!--添加模态框-->
                                	<div class="modal fade" id="adddrink" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                                	
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title" id="exampleModalLabel">添加饮品</h4>
									      </div>
									      <div class="modal-body">
									      <div id="kv-avatar-errors-2" class="center-block" style="width:800px;display:none"></div>
										<form class="form form-vertical" action="${pageContext.request.contextPath}/addDrink" method="post" id="addForm" name="addForm">
										    
										    <input type="hidden" id="add-PicAddres" name="add-PicAddres" value="">
										    
										        <div class="col-sm-12">
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="email">饮品名称</label>
										                        <input type="text" class="form-control" id="add-DrinkName" name="add-DrinkName">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="pwd">超级杯</label>
										                        <input type="text" class="form-control" id="add-DrinkPrice_Super" name="add-DrinkPrice_Super">
										                    </div>
										                </div>
										            </div>
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="fname">大杯</label>
										                        <input type="text" class="form-control" id="add-DrinkPrice_Big" name="add-DrinkPrice_Big">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="lname">中杯</label>
										                        <input type="text" class="form-control" id="add-DrinkPrice_Medium" name="add-DrinkPrice_Medium">
										                    </div>
										                </div>
										            </div>
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="fname">饮品类型</label>
										                        <input type="text" class="form-control" id="add-DrinkType" name="add-DrinkType">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="lname">饮品描述</label>
										                        <input type="text" class="form-control" id="add-DrinkDesc" name="add-DrinkDesc">
										                    </div>
										                </div>
										            </div>
										        	<div class="col-sm-12 text-center">
										            	<div class="kv-avatar">
										                	<div class="file-loading">
										                    	<input id="addpic" name="addpic" type="file">
										                	</div>
										            	</div>
										        	</div>
										        </div>
										    
										</form>
									      </div>
									      <!-- <div class="modal-footer">
									        <button type="button" class="btn btn-primary">确定</button>
									      </div> -->
									    </div>
									  </div>
									</div>
									
									
									<!--修改模态框-->
                                	<div class="modal fade" id="updatedrink" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title" id="exampleModalLabel">修改饮品</h4>
									      </div>
									      <div class="modal-body">
									      <div id="kv-avatar-errors-2" class="center-block" style="width:800px;display:none"></div>
											<form class="form form-vertical" action="${pageContext.request.contextPath}/changeDrink" method="post" id="updateForm" name="updateForm">
										    
										      <input type="hidden" id="update-DrinkID" name="update-DrinkID" value="">
										      <input type="hidden" id="update-PicAddres" name="update-PicAddres" value="">
										    
										        <div class="col-sm-12">
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="email">饮品名称</label>
										                        <input type="text" class="form-control" id="update-DrinkName" name="update-DrinkName">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="pwd">超级杯</label>
										                        <input type="text" class="form-control" id="update-DrinkPrice_Super" name="update-DrinkPrice_Super">
										                    </div>
										                </div>
										            </div>
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="fname">大杯</label>
										                        <input type="text" class="form-control" id="update-DrinkPrice_Big" name="update-DrinkPrice_Big">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="lname">中杯</label>
										                        <input type="text" class="form-control" id="update-DrinkPrice_Medium" name="update-DrinkPrice_Medium">
										                    </div>
										                </div>
										            </div>
										            <div class="row">
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="fname">饮品类型</label>
										                        <input type="text" class="form-control" id="update-DrinkType" name="update-DrinkType">
										                    </div>
										                </div>
										                <div class="col-sm-6">
										                    <div class="form-group">
										                        <label for="lname">饮品描述</label>
										                        <input type="text" class="form-control" id="update-DrinkDesc" name="update-DrinkDesc">
										                    </div>
										                </div>
										            </div>
										        </div>
										        <div class="col-sm-12 text-center">
										            <div class="kv-avatar">
										                <div class="file-loading">
										                    <input name="avatar-2" type="file" id="update-pic">
										                </div>
										            </div>
										        </div>
										    
											</form>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-primary" onclick="$('#updateForm').submit();">确定</button>
									      </div>
									    </div>
									  </div>
									</div>
                                
                                
                                
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
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/fileinput.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/lib/zh.js"></script>
    <script src="${pageContext.request.contextPath}/admin/assets/js/init/datatables-init.js"></script>
<script>
var btnCust = '<button type="button" style="background:#fff;border:none;"> ' + '</button>'; 
$("#addpic").fileinput({
	uploadUrl: "${pageContext.request.contextPath}/uploadPic",
    overwriteInitial: true,
    maxFileSize: 1500,
    showUpload: true,
    showClose: false,
    showCaption: false,
    showBrowse: false,
    browseOnZoneClick: true,
    removeClass: "btn btn-danger",
    removeIcon: '<i class="fa fa-remove"></i>',
    removeTitle: '取消',
    uploadClass:"btn btn-primary",
    uploadIcon:'<i class="fa fa-upload" aria-hidden="true"></i>',
    uploadTitle: '上传',
    elErrorContainer: '#kv-avatar-errors-2',
    msgErrorClass: 'alert alert-block alert-danger',
    defaultPreviewContent: '<img src="${pageContext.request.contextPath}/admin/images/drinkdft.png" alt="Your Avatar"><h6 class="text-muted">Click to select</h6>',
    layoutTemplates: {actionDelete:'', //去除上传预览的缩略图中的删除图标
        actionUpload:'',//去除上传预览缩略图中的上传图片；
        actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
    allowedFileExtensions: ["jpg", "png", "gif"]
}).on("fileuploaded", function(event, data, previewId, index) {
	var result = data.response;
	if(result.state == "success") {
		// 图片路径放入表单中
		$('#add-PicAddres').attr('value',result.PicAddres);
	}
	
	// 提交表单
	$('#addForm').submit();
});
</script>
<script>
$("#updatedrink").on('show.bs.modal', function (event) {
	var btnThis = $(event.relatedTarget); //触发事件的按钮
    var modal = $(this); //当前模态框
    var modalid = btnThis.data('id'); 
    var modalname = btnThis.data('name');
    var modalsuper = btnThis.data('super'); 
    var modalbig = btnThis.data('big');
    var modalmediu = btnThis.data('mediu');
    var modaltype = btnThis.data('type');
    var modalspec = btnThis.data('spec');
    var modalpic = btnThis.data('pic');
    
    // 将从"按钮"解析出来的data值赋给"模态框"的input标签中对于的value里面
    $('#update-PicAddres').attr('value', modalpic);
    $('#update-DrinkID').attr('value', modalid);
    $('#update-DrinkName').attr('value', modalname);
    $('#update-DrinkPrice_Super').attr('value', modalsuper);
    $('#update-DrinkPrice_Big').attr('value', modalbig);
    $('#update-DrinkPrice_Medium').attr('value', modalmediu);
    $('#update-DrinkType').attr('value', modaltype);
    $('#update-DrinkDesc').attr('value', modalspec);

    $("#update-pic").fileinput({
    	uploadUrl: "${pageContext.request.contextPath}/uploadPic",
        overwriteInitial: true,
        maxFileSize: 1500,
        showUpload: true,
        showClose: false,
        showCaption: false,
        showBrowse: false,
        browseOnZoneClick: true,
        removeClass: "btn btn-danger",
        removeIcon: '<i class="fa fa-remove"></i>',
        removeTitle: '取消',
        uploadClass:"btn btn-primary",
        uploadIcon:'<i class="fa fa-upload" aria-hidden="true"></i>',
        uploadTitle: '上传',
        elErrorContainer: '#kv-avatar-errors-2',
        msgErrorClass: 'alert alert-block alert-danger',
        defaultPreviewContent: '<img id="defaultImg" src="${pageContext.request.contextPath}/admin/images/drinkdft.png" alt="Your Avatar"><h6 class="text-muted">Click to select</h6>',
        
        // overwriteInitial: false, //不覆盖已存在的图片
        /*//下面几个就是初始化预览图片的配置
        initialPreviewAsData: true,
        initialPreviewFileType: 'image',
        initialPreview: modalpic,
        initialPreviewConfig: [
            {caption: modalpic, size: 576237, width: "120px", url: modalpic, key: 1},
        ],*/
        
        layoutTemplates: {actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload:'',//去除上传预览缩略图中的上传图片；
            actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
            },
        allowedFileExtensions: ["jpg", "png", "gif"]
    });

    $('#defaultImg').attr('src', "${pageContext.request.contextPath}/client/img/" + modalpic);
});

$("#update-pic").on("fileuploaded", function(event, data, previewId, index) {
	var result = data.response;
	if(result.state == "success") {
		// 图片路径放入表单中
		$('#update-PicAddres').attr('value',result.PicAddres);
	}
	
	// 提交表单
	$('#updateForm').submit();
});

function deleteDrink (DrinkID) {
	window.location.href = "${pageContext.request.contextPath}/deleteDrink?DrinkID=" + DrinkID;
}
</script>
</body>
</html>