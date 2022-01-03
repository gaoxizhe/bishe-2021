<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

<title>AddAddress</title>
</head>
<body>

<div id="divcontent">

<div class="page-wrapper">
<!-- Preloader -->
<div class="preloader"></div>
<jsp:include page="head.jsp"></jsp:include>
<div class = "checkout-page mt-120 rmt-80 mb-120 rmb-80">
	<div class = container>
		<div class = "row col-gap-60">
			<div class = "col-xl-12 col-lg-12">
				<div class = "checkout-form-wrap rmb-50">
					<div class="cart-title">
                       <h4>填写地址</h4>
                    </div>
		<!-- Form Area Start -->
                            <form action="${pageContext.request.contextPath}/addAddress" method="post">
                                <div class="row col-gap-50 clearfix">
                                    <div class="col-md-6">        
                                        <div class="form-group">
                                            <label for="City">省/市*</label>
                                       <input type="text" class="form-control" id="City" name="City" placeholder="请填写省/市名" required>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">        
                                        <div class="form-group">
                                            <label for="County">区/县*</label>
                                            <input type="text" class="form-control" id="County" name="County" placeholder="请填写区/县名" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">        
                                        <div class="form-group">
                                            <label for="Street">街道*</label>
                                            <input type="text" class="form-control" id="Street" name="Street" placeholder="请填写街道名" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">        
                                        <div class="form-group">
                                            <label for="HouseNum">门牌号*</label>
                                            <input type="text" class="form-control" id="HouseNum" name="HouseNum" placeholder="请填写门牌号" required>
                                        </div>
                                    </div>    
                                </div>
                                <div class="checkout-btn text-center">
                                	<button class="theme-btn br-5 w-50" type="submit">确认添加</button>
                                	
                            	</div>
                            	<div class="checkout-btn text-center mt-10">
                                	<a href="${pageContext.request.contextPath}/client/me.jsp" class="theme-btn w-50" style = "background:#808080;border-radius:10px;" type = "submit">取消添加</a>
                            	</div>
                            </form>
                            <!-- Form Area End -->
		</div>
	</div>
</div>
	</div>
</div>
</form>

















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
</body>
</html>