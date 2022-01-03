<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/ShowIndex">

<title>LoginSuccess</title>  
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
                    <h4 class="page-title">登录成功!3秒后为您跳转至首页</h4>
                    <h5 class="page-title">如果没有跳转,
                    	<a href="${pageContext.request.contextPath}/ShowIndex" class="btn btn-link">点击此处跳转</a>
                    </h5>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
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
    <script src="${pageContext.request.contextPath}/client//js/script.js"></script>
</body>
</html>