// 修改cart页面中的显示
function changeCartList (data) {
	$('#CartItemList').empty();
	var priceTotal = 0;
	for (var i = 0; i < data.length; i++) {
		var a = '<div class="cart-single-item">\n' +
				'	<button type="button" class="close" onclick="deleteCartItem(\'' + data[i].DrinkID + '\',\'' + data[i].DrinkSweet + '\',\'' + data[i].DrinkTemp + '\',\'' + data[i].DrinkSpec + '\')"><i class="flaticon-cross"></i></button>\n' +
				'	<div class="product-img">' +
				'		<img src="/Persephone/client/img/' + data[i].drink.PicAddres + '" alt="Product Image">\n' +
				'	</div>\n' +
				'	<h6 class="product-name">' + data[i].drink.DrinkName + ' (' + data[i].DrinkSweet + '·' + data[i].DrinkTemp + '·' + data[i].DrinkSpec + ')</h6>\n' +
				'	<div class="number-input">\n' +
				'		<button class="minus" onclick="changeCart(\'' + data[i].DrinkID + '\',\'' + data[i].DrinkSweet + '\',\'' + data[i].DrinkTemp + '\',\'' + data[i].DrinkSpec + '\',\'' + data[i].Number + '\',\'minus\')"></button>\n' +
				'		<input class="quantity" min="1" max="99" name="quantity" value="' + data[i].Number + '" type="number">\n' +
				'		<button class="plus" onclick="changeCart(\'' + data[i].DrinkID + '\',\'' + data[i].DrinkSweet + '\',\'' + data[i].DrinkTemp + '\',\'' + data[i].DrinkSpec + '\',\'' + data[i].Number + '\',\'plus\')"></button>\n' +
				'	</div>\n';

		if(data[i].DrinkSpec == '超级杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Super;
			a = a + '	<h6 class="product-price">￥' + parseFloat(data[i].drink.DrinkPrice_Super).toFixed(1) + '</h6>\n' +
					'	<h6 class="product-total-price">￥' + parseFloat(price).toFixed(1) + '</h6>\n';
			priceTotal = priceTotal + parseFloat(price);
		}
		else if(data[i].DrinkSpec == '大杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Big;
			a = a + '	<h6 class="product-price">￥' + parseFloat(data[i].drink.DrinkPrice_Big).toFixed(1) + '</h6>\n' +
					'	<h6 class="product-total-price">￥' + parseFloat(price).toFixed(1) + '</h6>\n';
			priceTotal = priceTotal + parseFloat(price);
		}
		else if(data[i].DrinkSpec == '中杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Medium;
			a = a +'	<h6 class="product-price">￥' + parseFloat(data[i].drink.DrinkPrice_Medium).toFixed(1) + '</h6>\n' +
					'	<h6 class="product-total-price">￥' + parseFloat(price).toFixed(1) + '</h6>\n';
			priceTotal = priceTotal + parseFloat(price);
		}
		a = a +'</div>\n';
		$('#CartItemList').append(a);
	}
	$('#totalPrice').html('￥' + parseFloat(priceTotal).toFixed(1));
}
	
// 修改head中购物车的显示
function changeCartInnerList (data) {
	$('#CartItemInnerList').empty();
	var num = 0;
	for (var i = 0; i < data.length; i++) {
		var b = '<div class="alert single-collection">\n' +
				'	<div class="collection-image">\n' +
				'		<img src="/Persephone/client/img/' + data[i].drink.PicAddres + '" alt="">\n' +
				'	</div>\n' +
				'	<div class="collection-content">\n' +
				'		<p>' + data[i].drink.DrinkName + ' × ' + data[i].Number + '</p>\n' +
				'		<h6>'+ data[i].DrinkSweet + '·' + data[i].DrinkTemp + '·' + data[i].DrinkSpec + '</h6>\n';
		if(data[i].DrinkSpec == '超级杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Super;
			b = b + '		<h6>￥' + parseFloat(price).toFixed(1) + '</h6>\n';
		}
		else if(data[i].DrinkSpec == '大杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Big;
			b = b + '		<h6>￥' + parseFloat(price).toFixed(1) + '</h6>\n';
		}
		else if(data[i].DrinkSpec == '中杯'){
			var price = data[i].Number * data[i].drink.DrinkPrice_Medium;
			b = b + '		<h6>￥' + parseFloat(price).toFixed(1) + '</h6>\n';
		}
		b = b + '	</div>\n' +
				'</div>\n';
		$('#CartItemInnerList').append(b);
		num = num + parseInt(data[i].Number);
	}
	$('#CartItemInnerList').append('<div class="collection-btn">\n' +
								   '	<a href="/Persephone/showCart" class="theme-btn bg-blue no-shadow mr-10">View Cart</a>\n' +
								   '	<a href="/Persephone/createOrder" class="theme-btn ml-auto no-shadow">Checkout</a>\n' +
								   '</div>\n');
	// 更改购物车图标中的数字
	var Style = document.createElement('style');
	Style.innerText = '.menu-collections .cart > i:after,\n' +
					  '.menu-collections .watch > i:after {\n' +
					  '	content: \'' + num + '\';\n' +
					  '	position: absolute;\n' +
					  '	height: 17px;\n' +
					  '	width: 17px;\n' +
					  '	color: #FFFFFF;\n' +
					  '	background: #5F66E0;\n' +
					  '	border-radius: 50%;\n' +
					  '	right: -10px;\n' +
					  '	top: -10px;\n' +
					  '	font-size: 12px;\n' +
					  '	line-height: 16px;\n' +
					  '	text-align: center;\n' +
					  '}\n';
	document.body.appendChild(Style);
}