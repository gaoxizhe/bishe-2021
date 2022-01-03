<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/client/img/logo-mini.png" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
<script type="text/javascript">
	function changeImage(){
		// 改变验证码图片中的文字
		document.getElementById("imgCode").src = "${pageContext.request.contextPath}/imageCode?time="+new Date().getTime();
	}
	
	$(document).ready(function(){
		if("${user}" != "")
			window.location.href="${pageContext.request.contextPath}/showMe";
	});
</script>

<title>Register</title>  
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
                    <h2 class="page-title">Sign Up</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ShowIndex">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Sign Up</li>
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
                        <div class="login-information bg-white br-5 py-125">
                            <div class="login-info-inner">
                                <h2>Sign Up</h2>
                                <form action="${pageContext.request.contextPath}/register" class="login-form" method="post" onsubmit="return checkForm();">
                                    <div class="text-field">
                                        <label for="name">用户名*</label>
                                        <input type="text" id="UserName" name="UserName" placeholder="请设置4~8位" onkeyup="checkUserName()" onblur="checkSameName()">
                                        <span id="UserNameMsg">&nbsp;</span>
                                    </div>
                                    <div class="text-field">
                                        <label for="pwd1">密码*</label>
                                        <input type="password" id="UserPwd" name="UserPwd" placeholder="请设置4~8位" onkeyup="checkUserPwd()">
                                        <span id="UserPwdMsg">&nbsp;</span>
                                    </div>
                                    <div class="text-field">
                                        <label for="pwd2">重复密码*</label>
                                        <input type="password" id="ReUserPwd" name="ReUserPwd" placeholder="请再输入一次密码" onkeyup="checkReUserPwd()">
                                        <span id="ReUserPwdMsg">&nbsp;</span>
                                    </div>
                                    <div class="text-field">
                                        <label for="phone">联系电话*</label>
                                        <input type="text" id="UserPhone" name="UserPhone" placeholder="请输入电话号码" onkeyup="checkUserPhone()">
                                        <span id="UserPhoneMsg">&nbsp;</span>
                                    </div>
                                    <div class="text-field">
                                        <label for="phone">验证码*</label>
                                        <input type="text" id="UserCheck" name="UserCheck" placeholder="请输入验证码">
                                        <img src="${pageContext.request.contextPath}/imageCode" width="180"
											height="30" class="textinput" style="height: 30px;" id="imgCode"/>&nbsp;&nbsp;
										<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
                                    </div>
                                    <div class="alternative-login">
                                        Do You Have Account ?<a class="signup-link" href="${pageContext.request.contextPath}/client/login.jsp">Login</a>
                                    </div> 
                                    <div class="signin-button-wrap">
                                        <button type="submit" class="btn-bg2">Sign Up</button>
                                    </div>

                                </form>
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-7 px-0 rpl-15 login-image-wrap">
                        <div class="login_img">
                            <img src="${pageContext.request.contextPath}/client/img/login.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </section>
<jsp:include page="foot.jsp"></jsp:include>
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
	
</div>
</body>
</html>