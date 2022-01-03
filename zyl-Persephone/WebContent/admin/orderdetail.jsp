<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ordertable</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">  -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/style.css">
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/drinktable.css">
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
                          <li class="active">订单表</li>
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
                                <strong class="card-title mr-20">订单表</strong>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped table-bordered" id="orderdetail">
                                <!--<thead>
                                        <tr>
                                            <th>用户id</th>
                                            <th>用户名</th>
                                            <th>密码</th>
                                            <th>联系方式</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>  -->  
                                    <tbody>
                                     <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
                                      
                                     
                                     
                                        <tr>                                 
                                        	<td>
                                        		<img style="width:60px;height:60px;border-radius:5px;" alt="img" src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}">
                                        	</td>
                                            
                                        	
                                        	<td>${item.drink.drinkName}</td>
                                        	<td>${item.drinkSpec}</td>
                                        	<td>${item.drinkTemp}</td>
                                        	<td>${item.drinkSweet}</td>
                                        	<td>${item.number}</td>          
                                                                      
                                  
                                           <c:if test="${item.drinkSpec eq '超级杯'}">
                            					<td>
                            						￥${item.drink.drinkPrice_Super}
                            					</td>
                            					<td>
                            						￥${item.number * item.drink.drinkPrice_Super}
                            					</td>
                            					
                            					   <c:set var="v" value="${item.number * item.drink.drinkPrice_Super}" scope="request"/> 
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '大杯'}">
                            					<td>
                            						￥${item.drink.drinkPrice_Big}
                            					</td>
                            					<td>
                            						￥${item.number * item.drink.drinkPrice_Big}
                            					</td>
                            					   <c:set var="v" value="${item.number * item.drink.drinkPrice_Super}" scope="request"/> 
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '中杯'}">
                            					<td>
                            						￥${item.drink.drinkPrice_Medium}
                            					</td>
                            					<td>
                            						￥${item.number * item.drink.drinkPrice_Medium}
                            					</td>
                            				   <c:set var="v" value="${item.number * item.drink.drinkPrice_Super}" scope="request"/>  
                                    		</c:if>
                                         
                                        </tr>
                                          </c:forEach>
                                        </tbody>
                                </table>
                            </div>
                            <div class="card-header">
                            	<div class="row">
                            		<div class="col-lg-8">
                            			<strong class="card-title mr-20">总价：￥${order.totalPrice}</strong>
                            		</div>
                            		<div style="margin-left:auto;">
                            			<a href="${pageContext.request.contextPath}/showOrder" class="btn btn-primary active" role="button">返回</a>
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
    <script src="${pageContext.request.contextPath}/admin/assets/js/init/datatables-init.js"></script>


    <script type="text/javascript">
        $(document).ready(function() {
          $('#bootstrap-data-table-export').DataTable();
      } );
  </script>
</body>
</html>