package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AdminBiz;
import com.web.biz.impl.AdminBizImpl;
import com.web.entity.Admin;

@WebServlet(urlPatterns="/addAdmin")
public class AddAdminController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 调用业务逻辑层的方法
		AdminBiz adminBiz = new AdminBizImpl();
				
		// 获取从前台页面传过来的参数
		String AdminName = req.getParameter("add-adname");
		String AdminPwd = req.getParameter("add-adpwd");
		
		// 把参数赋值给用户对象
		Admin admin = new Admin();
		admin.setAdminName(AdminName);
		admin.setAdminPwd(AdminPwd);
				
		// 调用业务逻辑层的方法
		boolean flag = adminBiz.addAdmin(admin);
				
		if (flag) {
			resp.getWriter().print("<script>alert('添加成功');window.location.href='"
					+ req.getContextPath() + "/showAdmin';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('添加失败');window.location.href='"
					+ req.getContextPath() + "/showAdmin';</script>");
		}
	}
}
