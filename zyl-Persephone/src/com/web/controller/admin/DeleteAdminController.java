package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AdminBiz;
import com.web.biz.impl.AdminBizImpl;

@WebServlet(urlPatterns="/deleteAdmin")
public class DeleteAdminController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		AdminBiz adminBiz = new AdminBizImpl();
				
		// 获取从前台页面传过来的参数
		String AdminID_str = req.getParameter("AdminID");
		int AdminID = Integer.valueOf(AdminID_str).intValue();
		
		// 调用业务逻辑层的更改方法
		boolean flag = adminBiz.deleteAdmin(AdminID);
				
		if (flag) {
			resp.getWriter().print("<script>alert('删除成功');window.location.href='"
					+ req.getContextPath() + "/showAdmin';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('删除失败');window.location.href='"
					+ req.getContextPath() + "/showAdmin';</script>");
		}
	}
}
