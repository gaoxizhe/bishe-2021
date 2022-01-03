package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.biz.AdminBiz;
import com.web.biz.impl.AdminBizImpl;
import com.web.entity.Admin;

@WebServlet(urlPatterns="/adminLogin")
public class AdminLoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取登录页面表单的参数值
		String AdminName = req.getParameter("AdminName");
		String AdminPwd = req.getParameter("AdminPwd");
		
		// 调用业务逻辑层的登录方法
		AdminBiz adminBiz = new AdminBizImpl();
		Admin admin = adminBiz.login(AdminName, AdminPwd);
		
		if (admin != null) {
			// 保存管理员的会话信息到session中
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			
			// 登陆成功
			resp.getWriter().print("<script>alert('登陆成功!');");
			
			// 转发
			resp.sendRedirect(req.getContextPath()+"/showAdminIndex");
		}
		else {
			resp.getWriter().print("<script>alert('用户名或密码错误!');window.location.href='"
					+ req.getContextPath() + "/admin/login.jsp';</script>");
		}
	}
}
