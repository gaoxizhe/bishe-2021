<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/cartChange.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${user}" == "")
			window.location.href="${pageContext.request.contextPath}/client/login.jsp";
	});

	function deleteCartItem (DrinkID, DrinkSweet, DrinkTemp, DrinkSpec) {
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/deleteCart',
			data: {"DrinkID": DrinkID, "DrinkSweet": DrinkSweet, "DrinkTemp": DrinkTemp, "DrinkSpec": DrinkSpec},
			dataType: 'json',
			statusCode: {
				404: function() {  
					alert('提交地址url未发现'); 
				}  
			},
			success: function (data) {
				changeCartList(data);
				changeCartInnerList(data);
			}
		});
	}

	function changeCart (DrinkID, DrinkSweet, DrinkTemp, DrinkSpec, Number, flag) {
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/changeCart',
			data: {"DrinkID": DrinkID, "DrinkSweet": DrinkSweet, "DrinkTemp": DrinkTemp, "DrinkSpec": DrinkSpec, "Number": Number, "flag": flag},
			dataType: 'json',
			statusCode: {
				404: function() {  
					alert('提交地址url未发现'); 
				}  
			},
			success: function (data) {
				changeCartList(data);
				changeCartInnerList(data);
			}
		});
	}
</script>

<title>Cart</title>
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
                    <h2 class="page-title">Your Cart</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ShowIndex">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Cart</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
        =====================================================================-->
        
        <!--==================================================================== 
           Start Cart Page
       =====================================================================-->
        <section class="cart-page mt-120 rmt-80 mb-120 rmb-80">
            <div class="container">
                <div class="row col-gap-60">

                    <div class="col-xl-8">
                        <div class="cart-total-product rmb-80 b1 br-5 p-50">
                            <h4 class="cart-heading">购物车</h4>
                            <div class="cart-title d-none d-md-flex">
                                <h5 class="product-title">商品</h5>
                                <h5 class="quantity-title">数量</h5>
                                <h5 class="price-title">单价</h5>
                                <h5 class="total-title">总价</h5>
                            </div>
                            <div class="cart-items pb-15" id="CartItemList">
                            	
                            	<c:set var="totalPrice" value="${0}"/>
                            	<c:forEach items="${CartItem}" var="item" varStatus="vs">
                            		
                            		<!-- 计算所有商品总价 -->
                            		<c:if test="${item.drinkSpec eq '超级杯'}">
                            			<c:set var="totalPrice" value="${totalPrice+item.number*item.drink.drinkPrice_Super}"/>
                                    </c:if>
                                    <c:if test="${item.drinkSpec eq '大杯'}">
                            			<c:set var="totalPrice" value="${totalPrice+item.number*item.drink.drinkPrice_Big}"/>
                                    </c:if>
                                    <c:if test="${item.drinkSpec eq '中杯'}">
                            			<c:set var="totalPrice" value="${totalPrice+item.number*item.drink.drinkPrice_Medium}"/>
                                    </c:if>
                            		
                                	<div class="cart-single-item">
                                    	<button type="button" class="close" onclick="deleteCartItem('${item.drinkID}','${item.drinkSweet}','${item.drinkTemp}','${item.drinkSpec}')"><i class="flaticon-cross"></i></button>
                                    	<div class="product-img">
                                        	<img src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}" alt="Product Image">
                                    	</div>
                                    	<h6 class="product-name">${item.drink.drinkName} (${item.drinkSweet}·${item.drinkTemp}·${item.drinkSpec})</h6>
                                    	<div class="number-input">
                                      		<button class="minus" onclick="changeCart('${item.drinkID}','${item.drinkSweet}','${item.drinkTemp}','${item.drinkSpec}','${item.number}','minus')"></button>
                                      		<input class="quantity" min="1" max="99" name="quantity" value="${item.number}" type="number">
                                      		<button class="plus" onclick="changeCart('${item.drinkID}','${item.drinkSweet}','${item.drinkTemp}','${item.drinkSpec}','${item.number}','plus')"></button>
                                    	</div>
                                    	
                                    	<!-- 显示单价 -->
                                    	<c:if test="${item.drinkSpec eq '超级杯'}">
                                    		<h6 class="product-price">￥${item.drink.drinkPrice_Super}</h6>
                                    	</c:if>
                                    	<c:if test="${item.drinkSpec eq '大杯'}">
                                    		<h6 class="product-price">￥${item.drink.drinkPrice_Big}</h6>
                                    	</c:if>
                                    	<c:if test="${item.drinkSpec eq '中杯'}">
                                    		<h6 class="product-price">￥${item.drink.drinkPrice_Medium}</h6>
                                    	</c:if>
                                    	
                                    	<!-- 显示每件商品的总价 -->
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
                                </c:forEach>
                                
                            </div>

                            <div class="row text-center text-lg-left">
                                <div class="col-lg-5">
                                    <div class="continue-shopping">
                                        <a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=All" class="theme-btn no-shadow bg-color5 br-10 rmt-30" type="submit">继续购买</a>
                                    </div>
                                </div>
                                <div class="col-lg-7">
                                    <div class="update-shopping text-lg-right">
                                        <a href="${pageContext.request.contextPath}/clearCart" class="theme-btn no-shadow style-two br-10 rmt-30">清空购物车</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-4">
                        <div class="cart-total-price p-50">
                            <h4 class="cart-heading">订单</h4>
                            <div class="total-item-wrap">
                                <div class="total-item sub-total">
                                    <span class="title">商品总价</span>
                                    <span class="price" id="totalPrice">￥${totalPrice}</span>
                                </div> 
                            </div>
                            <div class="proceed-btn mt-30">
                                <a href="${pageContext.request.contextPath}/createOrder" class="theme-btn w-100 text-center br-10">支付</a>
                            </div>
                        </div>
                    </div>
                </div>

                
                
            </div>
        </section>
        <!--==================================================================== 
           End Cart Page
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