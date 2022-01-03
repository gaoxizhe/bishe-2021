<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${user}" != "")
			window.location.href="${pageContext.request.contextPath}/showMe";
	});
</script>

<title>Login</title>  
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
                    <h2 class="page-title">Login</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ShowIndex">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Login</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--==================================================================== 
            End Banner Section
        =====================================================================-->
        
        <section class="login-area my-120 rmy-80">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 pr-0 rpr-15 rmb-80">
                        <div class="login-information bg-white br-5">
                            <div class="login-info-inner">
                                <h2>Welcome Back</h2>
                                <form action="${pageContext.request.contextPath}/login" class="login-form" method="post">
                                    <div class="email-field">
                                        <label for="email">用户名*</label>
                                        <input type="text" id="UserName" name="UserName" placeholder="请输入用户名">
                                    </div>
                                    <div class="password-field">
                                        <label for="pass">密码*</label>
                                        <input type="password" id="UserPwd" name="UserPwd" placeholder="请输入密码">
                                    </div>
                                    <div class="alternative-login">
                                        <span><a href="${pageContext.request.contextPath}/client/#">Forget Password</a></span>
                                        <span>Don't Have Account ?<a class="signup-link" href="${pageContext.request.contextPath}/client/register.jsp">Sign Up</a></span>
                                    </div> 
                                    <div class="signin-button-wrap">
                                        <button type="submit" class="btn-bg2">Login</button>
                                    </div>

                                </form>
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-7 px-0 rpl-15">
                        <div class="login_img bg-white">
                            <img src="${pageContext.request.contextPath}/client/img/login.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </section>
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