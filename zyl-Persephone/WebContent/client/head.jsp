<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="${pageContext.request.contextPath}/client/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/cartChange.js"></script>
<script>
	$(document).ready(function(){
		if("${user}" != ""){
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/showCart",
				data: {"flag": "ajax"},
				dataType: 'json',
				statusCode: {
					404: function() {  
						alert('提交地址url未发现'); 
					}  
				},
				success: function (data) {
					changeCartInnerList(data);
				}
			});
		}
	});
</script>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <title>Home</title>
	
    <!-- Fav Icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/favicon.png" type="image/x-icon">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/responsive.css">

        <header class="main-header">
            <div class="container">
                <div class="header-inner">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}/ShowIndex"><img style = "width:125px;height:50px;" src="${pageContext.request.contextPath}/client/img/logo1.1.jpg" alt="Main Logo"></a>
                    </div>
                    
                    <div class="categories">
                        <button ><i class="flaticon-list"></i>
                        <span>Categories</span></button>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=All"><i class="flaticon-apple"></i> 全部饮品</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=MilkTea"><i class="flaticon-apple"></i> 醇香奶茶</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Coffee"><i class="flaticon-chicken-hand-drawn-outline"></i> 咖啡时光</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Milk"><i class="flaticon-pressure-washer"></i> 牧场牛奶</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=FruitTea"><i class="flaticon-pest"></i> 清新夏日</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?DrinkType=Tea"><i class="flaticon-stationery"></i> 原沏茗作</a></li>
                        </ul>
                    </div>
                    
                    <form action="${pageContext.request.contextPath}/search" class="menu-search" id="searchform">
                        <select id="DrinkType" name="DrinkType">
                            <option value="All">全部饮品</option>
                            <option value="MilkTea">醇香奶茶</option>
                            <option value="Coffee">咖啡时光</option>
                            <option value="Milk">牧场牛奶</option>
                            <option value="FruitTea">清新夏日</option>
                            <option value="Tea">原沏茗作</option>
                        </select>
                        <input type="search" placeholder="Search" name="searchfield" onmouseover="this.focus();" required>
                        <button type="submit" >Search</button>
                    </form>
                    
                    <div class="menu-collections">
                        <div class="collection-item cart" id="cartIcon" data-after="0">
                        
                        	<c:if test="${user.userName!=null}">
                            	<i class="flaticon-shopping-cart"></i>
                            	<div class="collection-inner" id="CartItemInnerList">
                            	
                            		<c:forEach items="${CartItem}" var="item" varStatus="vs">
                                		<div class="alert single-collection">
                                    		<div class="collection-image">
                                        		<img src="${pageContext.request.contextPath}/client/img/${item.drink.picAddres}" alt="">
                                    		</div>
                                    		<div class="collection-content">
                                        		<p>${item.drink.drinkName} × ${item.number}</p>
                                        		<h6>${item.drinkSweet}·${item.drinkTemp}·${item.drinkSpec}</h6>
                                        		
                                        		<c:if test="${item.drinkSpec eq '超级杯'}">
                                        		<h6>￥${item.number * item.drink.drinkPrice_Super}</h6>
                                    			</c:if>
                                    			<c:if test="${item.drinkSpec eq '大杯'}">
                                        		<h6>￥${item.number * item.drink.drinkPrice_Big}</h6>
                                    			</c:if>
                                    			<c:if test="${item.drinkSpec eq '中杯'}">
                                        		<h6>￥${item.number * item.drink.drinkPrice_Medium}</h6>
                                    			</c:if>
                                    			
                                    		</div>
                                		</div>
                                	</c:forEach>
                                	
                                	<div class="collection-btn">
                                    	<a href="${pageContext.request.contextPath}/showCart" class="theme-btn bg-blue no-shadow mr-10">View Cart</a>
                                    	<a href="${pageContext.request.contextPath}/createOrder" class="theme-btn ml-auto no-shadow">Checkout</a>
                                	</div>
                            	</div>
                            </c:if>
                            <c:if test="${user.userName==null}">
                            	<i class="flaticon-shopping-cart"></i>
                            	<div class="collection-inner">
                            		<div class="collection-content">
                                        	<p>请先注册或登陆！</p>
                                    	</div>
                            		<div class="collection-btn">
                                    	<a href="${pageContext.request.contextPath}/client/register.jsp" class="theme-btn bg-blue no-shadow mr-10">注册</a>
                                    	<a href="${pageContext.request.contextPath}/client/login.jsp" class="theme-btn ml-auto no-shadow">登陆</a>
                                	</div>
                            	</div>
                            </c:if>
                        </div>
                        <div class="collection-item profile">
                            <i class="flaticon-user-1"></i>
                            <div class="collection-inner">
                                <ul>
                                	<c:if test="${user.userName!=null}">
                                    	<li><a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a></li>
                                    	<li><a href="${pageContext.request.contextPath}/showMe">我的账户</a></li>
                                    	<li><a href="${pageContext.request.contextPath}/logout">退出登陆</a></li>
                                    </c:if>
                                    <c:if test="${user.userName==null}">
                                    	<li><a href="${pageContext.request.contextPath}/client/login.jsp">登录</a></li>
                                    	<li><a href="${pageContext.request.contextPath}/client/register.jsp">注册</a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="collection-close"></div>
        </header>