var UserNameObj;
var UserPwdObj;
var ReUserPwdObj;
var UserPhoneObj;

var UserNameMsg;
var UserPwdMsg;
var ReUserPwdMsg;
var UserPhoneMsg;

window.onload = function() {	// 页面加载之后, 获取页面中的对象
	UserNameObj = document.getElementById("UserName");
	UserPwdObj = document.getElementById("UserPwd");
	ReUserPwdObj = document.getElementById("ReUserPwd");
	UserPhoneObj = document.getElementById("UserPhone");

	UserNameMsg = document.getElementById("UserNameMsg");
	UserPwdMsg = document.getElementById("UserPwdMsg");
	ReUserPwdMsg = document.getElementById("ReUserPwdMsg");
	UserPhoneMsg = document.getElementById("UserPhoneMsg");
};

function checkForm () {
	var bUserName = checkUserName();
	var bUserPwd = checkUserPwd();
	var bReUserPwd = checkReUserPwd();
	var bUserPhone = checkUserPhone();
	return bUserName && bUserPwd && bReUserPwd && bUserPhone && checkSameName();
}

function checkUserName () {
	var regex = /^.{4,8}$/;  // 任意字符, 4到8位
	var value = UserNameObj.value;
	var msg = "&nbsp;";
	if (!value)
		msg = "必须填写用户名！";
	else if (!regex.test(value))
		msg = "用户名不合法！";
	UserNameMsg.innerHTML = msg;  // 将提示消息放入SPAN
	UserNameObj.parentNode.style.color = msg == "&nbsp;" ? "black" : "red";
	return msg == "&nbsp;";
}

function checkUserPwd () {
	var regex = /^.{4,8}$/;  // 任意字符, 4到8位
	var value = UserPwdObj.value;
	var msg = "&nbsp;";
	if (!value)
		msg = "必须填写密码！";
	else if (!regex.test(value))
		msg = "密码不合法！";
	UserPwdMsg.innerHTML = msg;
	UserPwdObj.parentNode.style.color = msg == "&nbsp;" ? "black" : "red";
	return msg == "&nbsp;";
}

function checkReUserPwd() {		// 验证确认密码
	var passwordValue = UserPwdObj.value;
	var confirmValue = ReUserPwdObj.value;
	var msg = "&nbsp;";

    if(!confirmValue){
	    msg = "必须填写确认密码！";
	}
	else	if (passwordValue != confirmValue){
		msg = "密码必须保持一致！";
    }
	ReUserPwdMsg.innerHTML = msg;
	ReUserPwdObj.parentNode.style.color = msg == "&nbsp;" ? "black" : "red";
	return msg == "&nbsp;";
}

function checkUserPhone () {
	var regex = /^[0-9]{11}$/;  // 11位数字
	var value = UserPhoneObj.value;
	var msg = "&nbsp;";
	if (!value)
		msg = "必须填写联系电话！";
	else if (!regex.test(value))
		msg = "联系电话不合法！";
	UserPhoneMsg.innerHTML = msg;
	UserPhoneObj.parentNode.style.color = msg == "&nbsp;" ? "black" : "red";
	return msg == "&nbsp;";
}

function checkSameName(){
	var name = UserNameObj.value;
	var flag = false;
	$.ajax({
		type: "GET",
		url: "/Persephone/checkName",
		data: {"UserName": name},
		async: false,
		dataType: "text",
		statusCode: {
			404: function() {  
				alert('提交地址url未发现'); 
			}  
		},
		success: function (result) {
			if(result == "same") {
				UserNameMsg.innerHTML = "用户名重复!";
				UserNameObj.parentNode.style.color = "red";
				flag = false;
			}
			else {
				UserNameMsg.innerHTML = "&nbsp;";
				flag = checkUserName();
			}
		}
	});
	return flag;
}