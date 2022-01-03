<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		if("${user}" == "")
			window.location.href="${pageContext.request.contextPath}/client/login.jsp";
	});

	function orderFormSubmit() {
		document.getElementById("orderFrom").submit();
	}
</script>

<title>Checkout</title>
</head>
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
                    <h2 class="page-title">Checkout</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ShowIndex">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Checkout</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
        =====================================================================-->
        
         <!--==================================================================== 
           Start Checkout Page
       =====================================================================-->
        <section class="checkout-page mt-120 rmt-80 mb-120 rmb-80">
            <div class="container">
                <div class="row col-gap-60">
                    <div class="col-xl-6 col-lg-6">
                        <div class="checkout-form-wrap rmb-50">
                           
                           <div class="cart-title">
                                <h4>选择地址</h4>
                            </div>
                            
                            <form action="${pageContext.request.contextPath}/payOrder" method="post" id="orderFrom">
                            	<input type="hidden" id="OrderID" name="OrderID" value="${order.orderID}">
                            	<ul class="list-group">
                            	
                            		<c:forEach items="${addrList}" var="addr" varStatus="vs">
										<li class="list-group-item custom-control custom-radio ">
											<input type="radio" class="custom-control-input" id="${addr.addrID}" name="AddrID" value="${addr.addrID}" checked>
											<label class="custom-control-label" for="${addr.addrID}" >${addr.city} ${addr.county} ${addr.street} ${addr.houseNum}</label>
										</li>
									</c:forEach>
									
								</ul>
                            </form>
                            
                        </div>
                        <div class="bg-white p-50 b1 br-5">
                        <div class="cart-title">
                                <h4 class="mb-25 mt-10">支付</h4>
                        </div>
                        <ul id="accordionExample" class="mb-40">
                                
                                
                                <!-- Default unchecked -->
                                <li class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="methodone" name="defaultExampleRadios" checked>
                                    <label class="custom-control-label" for="methodone" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">支付宝/微信</label>

                                    <div id="collapseOne" class="collapse show" data-parent="#accordionExample" style="">
                                        <ul>
                                            <li><a href="#"><img class = "mt-10" style = "width : 70px;height : 36px;" src="${pageContext.request.contextPath}/client/img/pay-method/alipay.jpg" alt=""></a></li>
                                            <li><a href="#"><img style = "width :80px;height : 50px;" src="${pageContext.request.contextPath}/client/img/pay-method/wepay.jpg" alt=""></a></li>
                                            
                                        </ul>
                                    </div>
                                </li>

                                <!-- Default unchecked -->
                                <li class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="methodtwo" name="defaultExampleRadios">
                                    <label class="custom-control-label collapsed" for="methodtwo" data-toggle="collapse" data-target="#collapseTwo" aria-controls="collapseTwo">信用卡</label>

                                    <div id="collapseTwo" class="collapse" data-parent="#accordionExample" style="">
                                        <ul>
                                            <li><a href="#"><img src="${pageContext.request.contextPath}/client/img/pay-method/visa.png" alt=""></a></li>
                                            <li><a href="#"><img src="${pageContext.request.contextPath}/client/img/pay-method/mastercard.png" alt=""></a></li>
                                            <li><a href="#"><img src="${pageContext.request.contextPath}/client/img/pay-method/discover.png" alt=""></a></li>
                                            <li><a href="#"><img src="${pageContext.request.contextPath}/client/img/pay-method/americanexpress.png" alt=""></a></li>
                                        </ul>
                                    </div>
                                </li>

                                <!-- Default unchecked -->
                                <li class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="methodthree" name="defaultExampleRadios">
                                    <label class="custom-control-label collapsed" for="methodthree" data-toggle="collapse" data-target="#collapsethree" aria-controls="collapsethree">到店自取</label>
                                </li>
                            </ul>
                            <div class="checkout-btn text-center">
                                <button class="theme-btn br-5 w-100" onclick="orderFormSubmit()">支付</button>
                            </div>
                            <div class="checkout-btn text-center mt-15  br-5">
                                <!--<button class="theme-btn br-5 w-100">取消支付</button>  -->
                                <a href="${pageContext.request.contextPath}/showMe" class="bg-color5 theme-btn w-100" type="submit">取消支付</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="checkout-cart-total clearfix">
                           <div class="cart-title">
                                <h4>订单</h4>
                            </div>
                            
                            <div class="order-cart-total-product mb-20 b1 br-5 p-15 bg-white">
	                        	<div class="order-cart-title d-none d-md-flex" style = "border-bottom: 1px solid #F3F3F3;">
	                        	<h6 style="color:#aa2116;">待支付...</h6>
	                        	</div>
	                        	
	                            <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
	                            	<div class="order-cart-items pb-15">
	                                	<div class="order-cart-single-item">
	                                    	<div class="order-product-img">
	                                        	<img src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}" alt="Product Image">
	                                    	</div>
	                                    	<h6 class="order-product-name">${item.drink.drinkName} × ${item.number}  ${item.drinkSweet}·${item.drinkTemp}·${item.drinkSpec}</h6>
	                                    	
                                    		<c:if test="${item.drinkSpec eq '超级杯'}">
                                    			<h6 class="product-total-price">￥${item.number * item.drink.drinkPrice_Super}</h6>
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '大杯'}">
                                    			<h6 class="product-total-price">￥${item.number * item.drink.drinkPrice_Big}</h6>
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '中杯'}">
                                    			<h6 class="product-total-price">￥${item.number * item.drink.drinkPrice_Medium}</h6>
                                    		</c:if>
	                                	</div>
	                            	</div>
	                            </c:forEach>
	                            
	                            <div class="mt-10 d-none d-md-flex" style = "border-top: 1px solid #F3F3F3;">
	                            	<div class="mt-10" style="margin-left:300px;">
	                            	 <h5 style="color:#aa2116;">总价：￥${order.totalPrice}</h5>
	                            	</div>
	                        	</div>
	                           </div>
                            
                            
                            
                            
                            
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--==================================================================== 
           End Checkout Page
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