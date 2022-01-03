<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/cartChange.js"></script>
<script>
	function submitForm () {
		$.ajax({
			type: 'POST',
			url: $("#addCartForm").attr("action"),
			data: $('#addCartForm').serialize(),
			success: function (result) {
				$('#quick-view').modal('hide');
				if (result == "success") {
					alert("添加成功！");
				}
				else if (result == "fail") {
					alert("已添加过该饮品！");
				}
				$.ajax({
					type: 'GET',
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
	}
	
	function selectSepc () {
		var spec = $("input[name='DrinkSpec']:checked").val();
		if (spec == "超级杯") {
			$("#Super").show();
			$("#Big").hide();
			$("#Medium").hide();
		}
		else if (spec == "大杯") {
			$("#Super").hide();
			$("#Big").show();
			$("#Medium").hide();
		}
		else if (spec == "中杯") {
			$("#Super").hide();
			$("#Big").hide();
			$("#Medium").show();
		}
	}
</script>

<title>Insert title here</title>
</head>
<body>

	<!--==================================== Start product-quick-view //product-modal  ================-->
	<div class="modal product-modal fade" id="quick-view" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body p-35 pt-0">

					<div class="product-quick-view">
						<div class="container">
							<div class="row">
								<div class="col-lg-6">
	 								<div class="product-preview-wrap">
										<div class="tab-content bg-white p-50 b1 br-5">
	                                                                        
											<img src="" alt="Product Preview Image" id="PicAddres">
	                                                                        
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="product-details text-left bg-white ml-25 px-50 py-45 b1 br-5">
										<h3 class="mb-25 rmt-25" id="DrinkName"></h3>
										<!-- 
										<div class="rating mb-25">
											<div class="star mr-15">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
											</div>
											<div class="text">(13 Review)</div>
										</div>
										 -->
										<p id="DrinkDesc"></p>
	                                                                    
	
										<!-- <h6>Availability: <span>In Stock</span></h6> -->
										<div id="Super" style="display:block;">
											<h4 class="price" id="DrinkPrice_Super"></h4>
										</div>
										<div id="Big" style="display:none;">
											<h4 class="price" id="DrinkPrice_Big"></h4>
										</div>
										<div id="Medium" style="display:none;">
											<h4 class="price" id="DrinkPrice_Medium"></h4>
										</div>

										<form action="${pageContext.request.contextPath}/addCart" method = "post" id="addCartForm">
											<input type="hidden" id="DrinkID" name="DrinkID" value="">
											<div class="product-spinner mt-20">
												<div class="number-input b1">
													<button class="minus" type="button"></button>
													<input min="1" max="99" id="Number" name="Number" value="1" type="number">
													<button class="plus" type="button"></button>
												</div>
												<c:if test="${user.userName!=null}">
													<a href="javascript:void(0);" class="theme-btn br-30 ml-20" onclick="submitForm();">加入购物车</a>
												</c:if>
												<c:if test="${user.userName==null}">
													<a href="${pageContext.request.contextPath}/client/login.jsp" class="theme-btn br-30 ml-20">请登陆后再购买！</a>
												</c:if>
											</div>
										
											<div class = "chose-sweet">
												<h7 style = "color : #071C35;"> 请选择甜度</h7>
												<li class="custom-control custom-radio" style = "display:inline;">
													<input type="radio" class="custom-control-input" id="DrinkSweet1" name="DrinkSweet" value="全糖" checked>
													<label class="custom-control-label" for="DrinkSweet1" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">全糖</label>
												</li>   
												<li class="custom-control custom-radio " style = "display:inline;">
													<input type="radio" class="custom-control-input" id="DrinkSweet2" name="DrinkSweet" value="半糖">
													<label class="custom-control-label" for="DrinkSweet2" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">半糖</label>
												</li> 
												<li class="custom-control custom-radio" style = "display:inline;">   
													<input type="radio" class="custom-control-input" id="DrinkSweet3" name="DrinkSweet" value="少糖">
													<label class="custom-control-label" for="DrinkSweet3" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">少糖</label>
												</li> 
											</div>
                                                                    
											<div class = "chose-temp mt-20">
												<h7 style = "color : #071C35;"> 请选择温度</h7>
												<li class="custom-control custom-radio" style = "display:inline;" >
													<input type="radio" class="custom-control-input" id="DrinkTemp1" name="DrinkTemp" value="热" checked>
													<label class="custom-control-label" for="DrinkTemp1" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">热</label>
												</li>   
												<li class="custom-control custom-radio " style = "display:inline;">
													<input type="radio" class="custom-control-input" id="DrinkTemp2" name="DrinkTemp" value="常温">
													<label class="custom-control-label" for="DrinkTemp2" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">常温</label>
												</li> 
												<li class="custom-control custom-radio" style = "display:inline;">   
													<input type="radio" class="custom-control-input" id="DrinkTemp3" name="DrinkTemp" value="加冰">
													<label class="custom-control-label" for="DrinkTemp3" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">加冰</label>
												</li> 
											</div>
                                                                    
											<div class = "chose-sepc mt-20" onclick="selectSepc()">
												<h7 style = "color : #071C35;"> 请选择杯型</h7>
												<li class="custom-control custom-radio" style = "display:inline;">
													<input type="radio" class="custom-control-input" id="DrinkSpec1" name="DrinkSpec" value="超级杯" checked>
													<label class="custom-control-label" for="DrinkSpec1" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">超级杯</label>
												</li>   
												<li class="custom-control custom-radio " style = "display:inline;">
													<input type="radio" class="custom-control-input" id="DrinkSpec2" name="DrinkSpec" value="大杯">
													<label class="custom-control-label" for="DrinkSpec2" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">大杯</label>
												</li> 
												<li class="custom-control custom-radio" style = "display:inline;">   
													<input type="radio" class="custom-control-input" id="DrinkSpec3" name="DrinkSpec" value="中杯">
													<label class="custom-control-label" for="DrinkSpec3" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">中杯</label>
												</li> 
											</div>
										</form>
										
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!--==================================== end product-quick-view //product-modal  ================-->

</body>
</html>