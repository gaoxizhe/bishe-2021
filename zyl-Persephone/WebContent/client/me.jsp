<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${user}" == "")
			window.location.href="${pageContext.request.contextPath}/client/login.jsp";
	});
</script>

<title>Me</title>
</head>
<!-- jequery plugins -->
    <script src="${pageContext.request.contextPath}/client/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/bootstrap-v4.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/jquery.simpleLoadMore.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/slick.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/appear.js"></script>
    <script>
    	$(function () {
    		$('#mytab li:eq(0) a').tab('show');
    	});
    	
    	function deleteAddress (AddrID) {
    		$.ajax({
    			type: "POST",
    			url: "${pageContext.request.contextPath}/delAddress",
    			data: {"id": AddrID},
    			statusCode: {
    				404: function() {  
    					alert('提交地址url未发现'); 
    				}  
    			}
    		});
    	}
    	
    	function checkNameForm(){
    		return checkName() && checkSameName();
    	}
    	
    	function checkName(){
    		//获得表单的id
    		var f=document.getElementById("form1");
    									 			  
    		if(f.UserName.value == ""){
    			document.getElementById("UserNameMsg").innerHTML = "用户名不能为空！";
    			document.getElementById("UserNameMsg").style.color = "red";
    			return false;
    		}						 			  
    		else if(!/^.{4,8}$/.test(f.UserName.value)){
    			document.getElementById("UserNameMsg").innerHTML = "用户名必须为4-8位！";
    			document.getElementById("UserNameMsg").style.color = "red";
    			return false;
    		}
    		else{
    			document.getElementById("UserNameMsg").innerHTML = "&nbsp;";
    			return true;
    		}
    	}
    	
    	function checkSameName(){
    		var name = document.getElementById("UserName").value;
    		var flag = false;
    		$.ajax({
    			type: "GET",
    			url: "${pageContext.request.contextPath}/checkName",
    			data: {"UserName": name},
    			async: false,
    			dataType: "text",
    			statusCode: {
    				404: function() {  
    					alert('提交地址url未发现'); 
    				}  
    			},
    			success: function (result) {
    				if(result == "same") {
    					if(name != "${user.userName}") {
    						document.getElementById("UserNameMsg").innerHTML = "用户名重复!";
    						document.getElementById("UserNameMsg").style.color = "red";
    						flag = false;
    					}
    					else {
    						document.getElementById("UserNameMsg").innerHTML = "&nbsp;";
    						document.getElementById("UserNameMsg").style.color = "black";
    						flag = true;
    					}
    				}
    				else {
    					document.getElementById("UserNameMsg").innerHTML = "&nbsp;";
    					flag = checkName();
    				}
    			}
    		});
    		return flag;
    	}
    	
    	function checkPwdFrom(){
    		return checkOldPwd() && checkPwd() && checkRePwd();
    	}
    	
    	function checkOldPwd(){
    		//获得表单的id
    		var f=document.getElementById("form");
    		
    		if(f.OldUserPwd.value == ""){
    			document.getElementById("OldUserPwdMsg").innerHTML = "不能为空!";
				document.getElementById("OldUserPwdMsg").parentNode.style.color = "red";
				return false;
    		}
    		else if(f.OldUserPwd.value != '${user.userPwd}') {
    			document.getElementById("OldUserPwdMsg").innerHTML = "密码错误!";
				document.getElementById("OldUserPwdMsg").parentNode.style.color = "red";
				return false;
    		}
    		else {
				document.getElementById("OldUserPwdMsg").innerHTML = "&nbsp;";
    			return true;
    		}
    	}
    	
    	function checkPwd(){
    		//获得表单的id
    		var f=document.getElementById("form");
    		
    		if(f.UserPwd.value == ""){
    			document.getElementById("UserPwdMsg").innerHTML = "不能为空!";
				document.getElementById("UserPwdMsg").parentNode.style.color = "red";
				return false;
    		}
    		else if(! /^.{4,8}$/.test(f.UserPwd.value)){
				document.getElementById("UserPwdMsg").innerHTML = "密码必须是4-8位!";
				document.getElementById("UserPwdMsg").parentNode.style.color = "red";
    			return false;
   			}
    		else {
				document.getElementById("UserPwdMsg").innerHTML = "&nbsp;";
    			return true;
    		}
    	}
    	
    	function checkRePwd() {
    		//获得表单的id
    		var f=document.getElementById("form");
    		
    		if(f.ReUserPwd.value == ""){
    			document.getElementById("ReUserPwdMsg").innerHTML = "不能为空!";
				document.getElementById("ReUserPwdMsg").parentNode.style.color = "red";
				return false;
    		}	 			  
    		else if(f.UserPwd.value!=f.ReUserPwd.value){
				document.getElementById("ReUserPwdMsg").innerHTML = "两次输入的密码不一致!";
				document.getElementById("ReUserPwdMsg").parentNode.style.color = "red";
    			return false;
    		}
    		else {
				document.getElementById("ReUserPwdMsg").innerHTML = "&nbsp;";
    			return true;
    		}
    	}
    	
    	function checkPhone(){
    		//获得表单的id
    		var f=document.getElementById("form2");
    		
    		if(f.UserPhone.value=="") {
    			document.getElementById("UserPhoneMsg").innerHTML = "不能为空!";
				document.getElementById("UserPhoneMsg").parentNode.style.color = "red";
    			return false;
    		}
    		else if(!/^1[3-9]\d{9}$/.test(f.UserPhone.value)) {
    			document.getElementById("UserPhoneMsg").innerHTML = "手机号码格式不正确!";
				document.getElementById("UserPhoneMsg").parentNode.style.color = "red";
    		    return false;
    		}
    		else {
				document.getElementById("UserPhoneMsg").innerHTML = "&nbsp;";
    			return true;
    		}
    	}
	</script>
<body>
<div class="page-wrapper">
<!-- Preloader -->
<div class="preloader"></div>
<jsp:include page="head.jsp"></jsp:include>

        <!--==================================================================== 
            Start Banner Section
        =====================================================================-->
        <section class="banner-section" style="background-image:url(${pageContext.request.contextPath}/client/img/banner.png);">
            <div class="container">
                <div class="banner-inner text-center">
                    <h2 class="page-title">我的账户</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ShowIndex">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Me</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
        =====================================================================-->
        
       <!--==================================================================== 
           Start Contact Info + Map Section
       =====================================================================-->
        <section class="my-address mt-110 rmt-80 mb-120 rmb-80">
            <div class="container">
                <div class="row col-gap-100">
                    <div class="col-lg-4">
                      <div class="shop-sidebar">
                            <div class="shop-widget b1">
                                <div class="shop-widget-title">
                                    <h5><i class="flaticon-list"></i>我的账户</h5>
                                </div>
                                <ul id = "mytab" class="nav nav-tabs" style ="display:inline;">
                                    <li class="active"><a href="#my-address" data-toggle="tab"><i class="fa fa-map-marker fa-fw"></i>我的地址</a></li>
		                            <li><a href="#my-order" data-toggle="tab"><i class="fa fa-credit-card fa-fw"></i>我的订单</a></li>
		                            <li><a href="#change" data-toggle="tab"><i class="fa fa-pencil-square-o fa-fw"></i>修改信息</a></li>
                                </ul>
                            </div>
                       </div>
                     </div>
                      
                    <div class="col-lg-8 tab-content" id="myTabContent">
                        <div class="my-address-inner tab-pane fade in active" id = "my-address">
                            <div class="my-address-info">
                                <div class="section-title mb-20">
                                    <h3>我的地址</h3>
                                    <!--<i class="fa fa-plus-square" style = "color:#FF0033;"></i> 添加地址  -->
                                    
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/client/addaddress.jsp">
  										<i class="fa fa-plus-square fa-lg"></i> 添加地址</a>

                                </div>

                                <div class="mb-20">
                                    <div class="row ">
                                    
                                    	<c:forEach items="${addrList}" var="addr" varStatus="vs">
                                        	<div class="col-lg-12 col-md-4 col-sm-6 m-10 p-15 b1 bg-white alert">
                                            	<div class="my-address-info-box">
                                                	<i class="fa fa-map-marker"></i>
                                                	<span class = "ml-10 mr-10" style = "color:#292633;font-size:20px;">${addr.city} ${addr.county} ${addr.street} ${addr.houseNum}</span>
                                               		<!-- <i class="fa fa-minus-square" style = "margin-left:auto; color:#FF0033;"></i> --> 
                                                	<button type="button" class="close mt-5" data-dismiss="alert" onclick="deleteAddress('${addr.addrID}');">
                                                		<i class="fa fa-minus-square" style = "margin-left:auto; color:#FF0033;"></i>
                                                	</button>
                                            	</div>
                                        	</div>
                                        </c:forEach>
                                        
                                    </div>
                                </div>

                            </div>
                            
                        </div>
                        
                        
                          <div class ="my-order tab-pane fade in active" id = "my-order"> 
	                      	<div class="section-title mb-20">
                                    <h3>我的订单</h3>
                            </div>
                            
	                        <c:forEach items="${orderList}" var="order" varStatus="vs">
	                        	<div class="order-cart-total-product mb-20 b1 br-5 p-15 bg-white">
	                        	
	                        		<c:if test="${order.payState eq 0}">
	                        			<div class="order-cart-title d-none d-md-flex" style = "border-bottom: 1px solid #F3F3F3;">
	                        				<h6>订单状态：<h6 style="color:#aa2116;">未支付</h6></h6>
	                        			</div>
	                        		</c:if>
	                        		
	                        		<c:if test="${order.payState eq 1}">
	                        			<div class="order-cart-title d-none d-md-flex" style = "border-bottom: 1px solid #F3F3F3;">
	                        				<h6>订单状态：<h6 style="color:#1d953f;">已支付</h6></h6>
	                        			</div>
	                        		</c:if>
	                        		
	                        		<c:forEach items="${order.orderItems}" var="item" varStatus="vs">
	                            		<div class="order-cart-items pb-15">
	                                		<div class="order-cart-single-item">
	                                    		<div class="order-product-img">
	                                        		<img src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}" alt="Product Image">
	                                    		</div>
	                                    		<h6 class="order-product-name">${item.drink.drinkName}  ${item.drinkSweet}·${item.drinkTemp}·${item.drinkSpec}</h6>
	                                    		<div class="order-number-input">
	                                        	<h6>${item.number}</h6>
	                                    		</div>
	                                    		
	                                    		<c:if test="${item.drinkSpec eq '超级杯'}">
	                                    			<h6 class="order-product-price">￥${item.drink.drinkPrice_Super}</h6>
	                                    			<h6 class="order-product-total-price">￥${item.number * item.drink.drinkPrice_Super}</h6>
                                    			</c:if>
                                    			<c:if test="${item.drinkSpec eq '大杯'}">
	                                    			<h6 class="order-product-price">￥${item.drink.drinkPrice_Big}</h6>
	                                    			<h6 class="order-product-total-price">￥${item.number * item.drink.drinkPrice_Big}</h6>
                                    			</c:if>
                                    			<c:if test="${item.drinkSpec eq '中杯'}">
	                                    			<h6 class="order-product-price">￥${item.drink.drinkPrice_Medium}</h6>
	                                    			<h6 class="order-product-total-price">￥${item.number * item.drink.drinkPrice_Medium}</h6>
                                    			</c:if>
	                                		</div>
	                            		</div>
	                            	</c:forEach>
	
	                            	<div class="row text-center text-lg-left">
	                                	<div class="col-lg-4">
	                                   	<a href="${pageContext.request.contextPath}/findOrderById?OrderID=${order.orderID}"><button type="button" class="btn btn-link">>>订单详情</button></a>
	                                	</div>
	                            	</div>
	                        	</div>
	                        </c:forEach>
	                        
	                    </div>
	                    
	                    <div class="change-inner tab-pane fade in active" id = "change">
                            <div class="change-info">
                                <div class="section-title mb-20">
                                    <h3>修改信息</h3>
                                <div class="mb-20">
                                	<ul class="list-group">
                                	<li class="list-group-item">
                                		<div class="row">
                                			<div class="col-lg-2" >
									    	 	<h5 style="margin:0px;">用户名：</h5>
									        </div>
									        <div class="col-lg-8" >
									    		<h5 style="margin:0px;">${user.userName}</h5>
									    	</div>
									    	<button class="btn btn-danger" style = "margin-left:auto;" data-toggle="collapse" 
									    	data-target="#changeusername" aria-expanded="false" aria-controls="changeusername">
									 			<i class="fa fa-pencil-square-o fa-fw"></i>修改
									 		</button>
									 		<div class="collapse col-lg-12" id="changeusername">
										 		<form  action="${pageContext.request.contextPath}/changeUserName" method="post" id="form1"  onsubmit="return checkNameForm()">
										 		  <div class="row">
												  <div class="login-form ml-10 mt-5 col-lg-8" >
												    <input type="text" class="email-field" id="UserName" name="UserName" placeholder="请输入新用户名" onkeyup="checkName()" onblur="checkSameName()">
												  </div>
												  <span id="UserNameMsg" class="mt-20">&nbsp;</span>
												  
												  <button class="btn btn-success mt-20" style = "margin-left:auto;height:40px;">
									 			   <i class="fa fa-check-square-o fa-fw"></i>确定
									 		      </button>
									 		      </div>
												</form>
												
									 		</div>
									 	</div>
									 </li>
									 
									 <li class="list-group-item">
                                		<div class="row">
                                			<div class="col-lg-2" >
									    	 	<h5 style="margin:0px;">密码：</h5>
									        </div>
									        <div class="col-lg-8" >
									    		<h5 style="margin:0px;">*******</h5>
									    	</div>
									    	<button class="btn btn-danger" style = "margin-left:auto;"data-toggle="collapse" 
									    	data-target="#changepwd" aria-expanded="false" aria-controls="changepwd">
									 			<i class="fa fa-pencil-square-o fa-fw"></i>修改
									 		</button>
									 		<div class="collapse col-lg-12" id="changepwd">
										 		<form action="${pageContext.request.contextPath}/changeUserPwd"  method="post" id="form"  onsubmit="return checkPwdFrom()">
										 		  <div class="row">
												  <div class="login-form ml-10 mt-5 col-lg-12" >
												  	<div class="row">
												  		<div class="col-lg-8">
												    		<input type="password" class="email-field" id="OldUserPwd" name="OldUserPwd" placeholder="请输入旧密码"
																	onblur="checkOldPwd()" onkeyup="document.getElementById('OldUserPwdMsg').innerHTML = '&nbsp;';">
												  		</div>
												  		<div class="mt-15">
												  			<span id="OldUserPwdMsg" class="mt-20">&nbsp;</span>
												  		</div>
												  	</div>
												  	<div class="row">
												  		<div class="col-lg-8">
														    <input type="password" class="email-field mt-5" id="UserPwd" name="UserPwd" placeholder="请输入新密码" onkeyup="checkPwd()">
												  		</div>
												  		<div class="mt-15">
												  			<span id="UserPwdMsg" class="mt-20">&nbsp;</span>
												  		</div>
												  	</div>
												  	<div class="row">
												  		<div class="col-lg-8">
												  			<input type="password" class="email-field mt-5" id="ReUserPwd" name="ReUserPwd" placeholder="请再一次输入新密码" onkeyup="checkRePwd()">
												  		</div>
												  		<div class="mt-15">
												  			<span id="ReUserPwdMsg" class="mt-20">&nbsp;</span>
												  		</div>
												  	</div>
												  </div>
												  <button class="btn btn-success" style = "margin-left:auto;height:40px;">
									 			   <i class="fa fa-check-square-o fa-fw"></i>确定
									 		      </button>
									 		      </div>
												</form>
												
									 		</div>
									 	</div>
									 </li>
									 
									 <li class="list-group-item">
                                		<div class="row">
                                			<div class="col-lg-2" >
									    	 	<h5 style="margin:0px;">联系电话：</h5>
									        </div>
									        <div class="col-lg-8" >
									    		<h5 style="margin:0px;">${user.userPhone}</h5>
									    	</div>
									    	<button class="btn btn-danger" style = "margin-left:auto;" data-toggle="collapse" 
									    	data-target="#changephone" aria-expanded="false" aria-controls="changephone">
									 			<i class="fa fa-pencil-square-o fa-fw"></i>修改
									 		</button>
									 		<div class="collapse col-lg-12" id="changephone">
										 		<form action="${pageContext.request.contextPath}/changeUserPhone" method="post" id="form2"  onsubmit="return checkPhone()">
										 		  <div class="row">
												  <div class="login-form ml-10 mt-5 col-lg-8" >
												    <input type="text" class="email-field" id="UserPhone" name="UserPhone" placeholder="请输入新联系方式" onkeyup="checkPhone()">
												  </div>
												  <span id="UserPhoneMsg" class="mt-20">&nbsp;</span>
												  
												  <button class="btn btn-success mt-20" style = "margin-left:auto;height:40px;">
									 			   <i class="fa fa-check-square-o fa-fw"></i>确定
									 		      </button>
									 		      </div>
												</form>
												
									 		</div>
									 	</div>
									 </li>
                                        
                                	
									</ul>
                                    
                                </div>

                            </div>
                            
                          </div>
                        </div>      <!--  -->
	                     
                    </div>
                </div>
            </div>
        </section>
        <!--==================================================================== 
           End Contact Info + Map Section
       =====================================================================-->

<jsp:include page="foot.jsp"></jsp:include>

</div>

<!-- Scroll Top Button -->
    <button class="scroll-top scroll-to-target" data-target="html"><span class="fa fa-angle-up"></span></button>


    <!-- jequery plugins -->
    <script src="${pageContext.request.contextPath}/client/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/bootstrap-v4.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/jquery.simpleLoadMore.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/slick.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/appear.js"></script>

    <!-- Custom script -->
    <script src="${pageContext.request.contextPath}/client/js/script.js"></script>

</body>
</html>