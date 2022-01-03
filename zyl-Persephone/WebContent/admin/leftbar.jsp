<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- Left Panel -->

    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">

            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                	<li>
                        <a href="${pageContext.request.contextPath}/showAdminIndex"><i class="menu-icon fa fa-laptop"></i>首页 </a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-user"></i>用户列表</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-user-circle"></i><a href="${pageContext.request.contextPath}/showUser">用户表</a></li>
                            <li><i class="fa fa-user-secret"></i><a href="${pageContext.request.contextPath}/showAdmin">管理员表</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>商品订单</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-product-hunt"></i><a href="${pageContext.request.contextPath}/showAllDrink">商品表</a></li>
                            <li><i class="fa fa-credit-card"></i><a href="${pageContext.request.contextPath}/showOrder">订单表</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="showUserAddress"><i class="menu-icon fa fa-address-card-o"></i>用户地址表 </a>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside><!-- /#left-panel -->

    <!-- Left Panel -->