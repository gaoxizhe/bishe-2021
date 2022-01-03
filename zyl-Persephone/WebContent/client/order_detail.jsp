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

<title>OrderDetail</title>
</head>
    
<body>
<div class="page-wrapper">
<!-- Preloader -->
<div class="preloader"></div>
<jsp:include page="head.jsp"></jsp:include>

<!--==================================================================== 
           Start Cart Page
       =====================================================================-->
        <section class="cart-page mt-130 rmt-80 mb-120 rmb-80">
            <div class="container">
                <div class="row col-gap-60">

                    <div class="col-xl-8">
                        <div class="cart-total-product rmb-80 b1 br-5 p-50">
                            <h4 class="cart-heading">订单详情</h4>
                            <table class="table">
                            	<thead>
                            		<tr>
                            			<th><h5>商品</h5></th>
                            			<th><h5>名称</h5></th>
		                                <th><h5 >数量</h5></th>
		                                <th><h5 >单价</h5></th>
		                                <th><h5 >总价</h5></th>
                            		</tr>
                            	</thead>
                            	<tbody>
                            	
	                            	<c:forEach items="${order.orderItems}" var="item" varStatus="vs">
                            			<tr>
                            				<td>
                            					<div class="product-img2">
                                        			<img src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}" alt="Product Image">
                                    			</div>
                                    		
                            				</td>
                            				<td>
                            					<h6 class="mt-40 mb-40">${item.drink.drinkName}  ${item.drinkSweet}·${item.drinkTemp}·${item.drinkSpec}</h6>
                            				</td>
                            				<td>
                            					<div class="mt-40 mb-40">
                                      				<h6>${item.number}</h6>
                                    			</div>
                            				</td>
                            				
                            				<c:if test="${item.drinkSpec eq '超级杯'}">
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.drink.drinkPrice_Super}</h6>
                            					</td>
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.number * item.drink.drinkPrice_Super}</h6>
                            					</td>
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '大杯'}">
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.drink.drinkPrice_Big}</h6>
                            					</td>
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.number * item.drink.drinkPrice_Big}</h6>
                            					</td>
                                    		</c:if>
                                    		<c:if test="${item.drinkSpec eq '中杯'}">
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.drink.drinkPrice_Medium}</h6>
                            					</td>
                            					<td>
                            						<h6 class="mt-40 mb-40">￥${item.number * item.drink.drinkPrice_Medium}</h6>
                            					</td>
                                    		</c:if>
                            				
                            			</tr>
                            		</c:forEach>
                            		
                            	</tbody>
                        </table>
                        <div class="row">
                        	<div class="col-lg-8"></div>
                        	<div style="margin-left:auto;">
                            	<a href="${pageContext.request.contextPath}/showMe" class="btn btn-primary active" role="button">返回</a>
                        	</div>
                        </div>
                    </div>
                    </div>

                    <div class="col-xl-4">
                        <div class="cart-total-price p-50">
                            <h4 class="cart-heading">订单</h4>
                            <div class="total-item-wrap">
                                <div class="total-item sub-total" style="display:inline;">
	                                <div>
	                                	<span class="title mr-50">订单编号</span>
	                                    <span class="price">${order.orderID}</span>
	                                </div>
                                	<div>
	                                	<span class="title mr-50">创建时间</span>
	                                    <span class="price">${order.orderTime}</span>
                                	</div>
                                    <div>
	                                    <span class="title mr-50">商品总价</span>
	                                    <span class="price" style = "color:#aa2116;">${order.totalPrice}</span>
                                    </div>
                                    
                                </div> 
                            </div>
                            <c:if test="${order.payState eq 0}">
                            	<div class="proceed-btn mt-30">
                                	<a href="${pageContext.request.contextPath}/checkout?OrderID=${order.orderID}" class="theme-btn w-100 text-center br-10">支付</a>
                            	</div>
                            	<div class="proceed-btn mt-30">
                                	<a href="${pageContext.request.contextPath}/cancelOrder?OrderID=${order.orderID}" class="theme-btn w-100 text-center br-10" style="background:#74787c;">取消订单</a>
                            	</div>
                            </c:if>
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