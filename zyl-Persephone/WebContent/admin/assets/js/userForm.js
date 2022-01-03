var UpdateNameObj;
var UpdatePwdObj;
var UpdatePhoneObj;
var UpdateNameMsg;
var UpdatePwdMsg;
var UpdatePhoneMsg;

var AddNameObj;
var AddPwdObj;
var AddPhoneObj;
var AddNameMsg;
var AddPwdMsg;
var AddPhoneMsg;

window.onload = function() {
	UpdateNameObj = document.getElementById("update-name")
	UpdatePwdObj = document.getElementById("update-pwd")
	UpdatePhoneObj = document.getElementById("update-pho")
	UpdateNameMsg = document.getElementById("update-nameMsg")
	UpdatePwdMsg = document.getElementById("update-pwdMsg")
	UpdatePhoneMsg = document.getElementById("update-phoMsg")
	
	AddNameObj = document.getElementById("add-name")
	AddPwdObj = document.getElementById("add-pwd")
	AddPhoneObj = document.getElementById("add-pho")
	AddNameMsg = document.getElementById("add-nameMsg")
	AddPwdMsg = document.getElementById("add-pwdMsg")
	AddPhoneMsg = document.getElementById("add-phoMsg")
}

// 修改模态框
function checkUpdateFrom() {
	return checkUpdateName() && checkUpdateSameName() && checkUpdatePwd() && checkUpdatePhone();
}

function checkUpdateName() {
	var name = UpdateNameObj.value;
	if (name == "") {
		UpdateNameMsg.innerHTML = "用户名不能为空！";
		UpdateNameMsg.parentNode.style.color = "red";
		return false;
	}
	else if (!/^.{4,8}$/.test(name)) {
		UpdateNameMsg.innerHTML = "用户名必须为4-8位！";
		UpdateNameMsg.parentNode.style.color = "red";
		return false;
	}
	else {
		UpdateNameMsg.innerHTML = "&nbsp;";
		UpdateNameMsg.parentNode.style.color = "black";
		return true;
	}
}

function checkUpdateSameName(){
	var name = UpdateNameObj.value;
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
				var oldName = document.getElementById("oldName").value;
				if(name != oldName) {
					UpdateNameMsg.innerHTML = "用户名重复!";
					UpdateNameMsg.parentNode.style.color = "red";
					flag = false;
				}
				else {
					UpdateNameMsg.innerHTML = "&nbsp;";
					UpdateNameMsg.parentNode.style.color = "black";
					flag = true;
				}
			}
			else {
				UpdateNameMsg.innerHTML = "&nbsp;";
				UpdateNameMsg.parentNode.style.color = "black";
				flag = checkUpdateName();
			}
		}
	});
	return flag;
}

function checkUpdatePwd() {
	var pwd = UpdatePwdObj.value;
	if(pwd == ""){
		UpdatePwdMsg.innerHTML = "不能为空!";
		UpdatePwdMsg.parentNode.style.color = "red";
		return false;
	}
	else if(!/^.{4,8}$/.test(pwd)){
		UpdatePwdMsg.innerHTML = "密码必须是4-8位!";
		UpdatePwdMsg.parentNode.style.color = "red";
		return false;
		}
	else {
		UpdatePwdMsg.innerHTML = "&nbsp;";
		UpdatePwdMsg.parentNode.style.color = "black";
		return true;
	}
}

function checkUpdatePhone() {
	var pho = UpdatePhoneObj.value;
	if(pho == "") {
		UpdatePhoneMsg.innerHTML = "不能为空!";
		UpdatePhoneMsg.parentNode.style.color = "red";
		return false;
	}
	else if(!/^1[3-9]\d{9}$/.test(pho)) {
		UpdatePhoneMsg.innerHTML = "手机号码格式不正确!";
		UpdatePhoneMsg.parentNode.style.color = "red";
	    return false;
	}
	else {
		UpdatePhoneMsg.innerHTML = "&nbsp;";
		UpdatePhoneMsg.parentNode.style.color = "black";
		return true;
	}
}

// 添加模态框
function checkAddFrom() {
	return checkAddName() && checkAddSameName() && checkAddPwd() && checkAddPhone();
}

function checkAddName() {
	var name = AddNameObj.value;
	if (name == "") {
		AddNameMsg.innerHTML = "用户名不能为空！";
		AddNameMsg.parentNode.style.color = "red";
		return false;
	}
	else if (!/^.{4,8}$/.test(name)) {
		AddNameMsg.innerHTML = "用户名必须为4-8位！";
		AddNameMsg.parentNode.style.color = "red";
		return false;
	}
	else {
		AddNameMsg.innerHTML = "&nbsp;";
		AddNameMsg.parentNode.style.color = "black";
		return true;
	}
}

function checkAddSameName(){
	var name = AddNameObj.value;
	var flag = false;
	$.ajax({
		type: "GET",
		url: "/Persephone/checkName",
		data: {"UserName": name},
		dataType: "text",
		async: false,
		statusCode: {
			404: function() {  
				alert('提交地址url未发现'); 
			}  
		},
		success: function (result) {
			if(result == "same") {
				AddNameMsg.innerHTML = "用户名重复!";
				AddNameMsg.parentNode.style.color = "red";
				flag = false;
			}
			else {
				AddNameMsg.innerHTML = "&nbsp;";
				AddNameMsg.parentNode.style.color = "black";
				flag = checkAddName();
			}
		}
	});
	return flag;
}

function checkAddPwd() {
	var pwd = AddPwdObj.value;
	if(pwd == ""){
		AddPwdMsg.innerHTML = "不能为空!";
		AddPwdMsg.parentNode.style.color = "red";
		return false;
	}
	else if(!/^.{4,8}$/.test(pwd)){
		AddPwdMsg.innerHTML = "密码必须是4-8位!";
		AddPwdMsg.parentNode.style.color = "red";
		return false;
		}
	else {
		AddPwdMsg.innerHTML = "&nbsp;";
		AddPwdMsg.parentNode.style.color = "black";
		return true;
	}
}

function checkAddPhone() {
	var pho = AddPhoneObj.value;
	if(pho == "") {
		AddPhoneMsg.innerHTML = "不能为空!";
		AddPhoneMsg.parentNode.style.color = "red";
		return false;
	}
	else if(!/^1[3458]\d{9}$/.test(pho)) {
		AddPhoneMsg.innerHTML = "手机号码格式不正确!";
		AddPhoneMsg.parentNode.style.color = "red";
	    return false;
	}
	else {
		AddPhoneMsg.innerHTML = "&nbsp;";
		AddPhoneMsg.parentNode.style.color = "black";
		return true;
	}
}