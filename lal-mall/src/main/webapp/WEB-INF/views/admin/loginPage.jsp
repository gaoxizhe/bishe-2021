<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/snowflakeCursor.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/admin/admin_login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/admin_login.css"/>
    <title>盆栽销售 - 管理登录</title>
</head>
<body>
<div id="div_background">
    <div id="div_main">
        <div id="div_head"><p>盆栽销售 <span>管理后台</span></p></div>
        <div id="div_content">
            <img id="img_profile_picture"
                 src="${pageContext.request.contextPath}/static/images/admin/loginPage/avatar.jpg"
                 alt="头像" title="头像"
                 onerror="this.src='${pageContext.request.contextPath}/static/images/admin/loginPage/avatar.jpg'"/>
            <form id="form_login">
                <input type="text" class="form-control form_control" placeholder="用户名" id="input_username" title="请输入用户名"/>
                <input type="password" class="form-control form_control" placeholder="密码" id="input_password" title="请输入密码" autocomplete="on">
                <span id="txt_error_msg"></span>
                <button id="btn_login" class="btn btn-primary">登 入</button>
            </form>
        </div>
    </div>
</div>
</body>