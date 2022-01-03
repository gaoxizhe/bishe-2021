<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function showModal (DrinkID) {
		$.ajax({
			type: "GET",
			url: "${pageContext.request.contextPath}/findProductById",
			data: {"id": DrinkID},
			dataType: "json",
			statusCode: {
				404: function() {  
					alert('提交地址url未发现'); 
				}  
			}, 
			success: function (drink) {
				$('#DrinkName').html(drink.DrinkName);
				$('#DrinkDesc').html(drink.DrinkDesc);
				$('#DrinkPrice_Super').html("￥"+drink.DrinkPrice_Super);
				$('#DrinkPrice_Big').html("￥"+drink.DrinkPrice_Big);
				$('#DrinkPrice_Medium').html("￥"+drink.DrinkPrice_Medium);
				$('#PicAddres').attr('src',"${pageContext.request.contextPath}/client/img/"+drink.PicAddres);
			}
		});
	}
</script>

<title>Search</title>
<!-- Fav Icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/favicon.png" type="image/x-icon">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/client/css/responsive.css">
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
                    <h2 class="page-title">Shop</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Shop</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
        =====================================================================-->
         <!--==================================================================== 
           Start Shop Page
       =====================================================================-->
        <section class="shop-page mt-120 rmt-80 mb-90 rmb-50">
            <div class="container">
                <div class="row">

                    <div class="col-xl-3 col-lg-4">
                        <div class="shop-sidebar">
                            <div class="shop-widget b1">
                                <div class="shop-widget-title">
                                    <h5><i class="flaticon-list"></i>Categories</h5>
                                </div>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=All"><i class="flaticon-apple"></i> 全部饮品</a></li>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=MilkTea"><i class="flaticon-apple"></i> 醇香奶茶</a></li>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Coffee"><i class="flaticon-chicken-hand-drawn-outline"></i> 咖啡时光</a></li>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Milk"><i class="flaticon-pressure-washer"></i> 牧场牛奶</a></li>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=FruitTea"><i class="flaticon-pest"></i> 清新夏日</a></li>
                                    <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Tea"><i class="flaticon-stationery"></i> 原沏茗作</a></li>
                                </ul>
                            </div>
                            <div class="shop-widget b1">
                                <div class="shop-widget-title">
                                    <h5>Best Sale Today</h5>
                                </div>
                                <div class="product list-product d-flex align-items-center bg-white br-5 mb-30">
                                    <div class="product-img-wrap">
                                        <img src="${pageContext.request.contextPath}/client/img/shop/cart-1.png" alt="img">
                                    </div>
                                    <div class="product-content-wrap">
                                        <div class="product-content">
                                            <p><a href="shop-details.html">Cauliflower <br>(1kg)</a>
                                            </p>
                                        </div>
                                        <div class="product-action">
                                            <a href="#" class="add-to-btn small-btn">
                                                <i class="flaticon-shopping-cart"></i>
                                                <span>Add to Cart</span>
                                                <h5 class="product-price">$120.00</h5>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product list-product d-flex align-items-center bg-white br-5 mb-30">
                                    <div class="product-img-wrap">
                                        <img src="${pageContext.request.contextPath}/client/img/shop/cart-2.png" alt="img">
                                    </div>
                                    <div class="product-content-wrap">
                                        <div class="product-content">
                                            <p><a href="shop-details.html">Organic Yellow Papaya (1ps)</a></p>
                                        </div>
                                        <div class="product-action">
                                            <a href="#" class="add-to-btn small-btn">
                                                <i class="flaticon-shopping-cart"></i>
                                                <span>Add to Cart</span>
                                                <h5 class="product-price">$120.00</h5>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product list-product d-flex align-items-center bg-white br-5 mb-30">
                                    <div class="product-img-wrap">
                                        <img src="${pageContext.request.contextPath}/client/img/shop/cart-3.png" alt="img">
                                    </div>
                                    <div class="product-content-wrap">
                                        <div class="product-content">
                                            <p><a href="shop-details.html">Granny Smith Apple (4ps)</a></p>
                                        </div>
                                        <div class="product-action">
                                            <a href="#" class="add-to-btn small-btn">
                                                <i class="flaticon-shopping-cart"></i>
                                                <span>Add to Cart</span>
                                                <h5 class="product-price">$120.00</h5>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-xl-9 col-lg-8">
                        <div class="shop-items">
                           
                            <div class="search-result-header">
                                <h5>Showing ${bean.totalCount} Drinks</h5>
                                <div class="sort-by">
                                    <h5>Sort:</h5>
                                    <select name="#">
                                        <option value="value-1">ALL</option>
                                        <option value="value-2">By Name</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="row">
                                
                                <c:forEach items="${bean.productList}" var="drink" varStatus="vs">
                                	<!--single Product -->
                                	<div class="col-xl-4 col-lg-6 col-sm-6">
                                	    <div class="product">
                                        	<div class="product-img-wrap">
                                            	<img src="${pageContext.request.contextPath}/client/img/${drink.picAddres}" alt="img">
                                            	<!-- Button trigger modal -->
                                            	<button class="quick-view" type="button" id="${drink.drinkID}"
													data-toggle="modal" data-target="#quick-view" onclick="showModal(this.id)">Quick View</button>
                                        	</div>

                                        	<div class="product-content-wrap">
                                            	<div class="product-content">
                                                	<p><a href="shop-details.html">${drink.drinkName}</a></p>
                                            	</div>
                                            	<div class="product-action">
                                                	<a data-toggle="modal" data-target="#quick-view" class="add-to-btn small-btn"
														id="${drink.drinkID}" onclick="showModal(this.id)">
                                                    	<i class="flaticon-shopping-cart"></i>
                                                    	<span>Quick View</span>
                                                    	<h5 class="product-price">￥${drink.drinkPrice_Medium}~￥${drink.drinkPrice_Super}</h5>
                                                	</a>
                                                
                                            	</div>
                                        	</div>
                                    	</div>
                                	</div>
                                </c:forEach>
                                
                                <!-- 分页 -->
                                <div>
									<ul>
										<c:if test="${bean.currentPage!=1}">
											<a href="${pageContext.request.contextPath}/search?currentPage=${bean.currentPage-1}&searchfield=${bean.searchfield}">上一页</a>
										</c:if>
										<c:if test="${bean.currentPage==1}">
											上一页
										</c:if>
										<li>${ bean.currentPage }</li>
										<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
											<a href="${pageContext.request.contextPath}/search?currentPage=${bean.currentPage+1}&searchfield=${bean.searchfield}">下一页</a>
										</c:if>
										<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
											下一页
										</c:if>
									</ul>
								</div>
								<!-- 分页 -->
                                
<jsp:include page="quick_view.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--==================================================================== 
           End Shop Page
       =====================================================================-->                                
        

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