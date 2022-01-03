package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;

@WebServlet(urlPatterns="/deleteUser")
public class DeleteUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		UserBiz userBiz = new UserBizImpl();
				
		// 获取从前台页面传过来的参数
		String UserID_str = req.getParameter("UserID");
		int UserID = Integer.valueOf(UserID_str).intValue();
		
		// 调用业务逻辑层的更改方法
		boolean flag = userBiz.deleteUser(UserID);
				
		if (flag) {
			resp.getWriter().print("<script>alert('删除成功!');window.location.href='"
					+ req.getContextPath() + "/showUser';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('删除失败!');window.location.href='"
					+ req.getContextPath() + "/showUser';</script>");
		}
		
		// 转发
//		resp.sendRedirect(req.getContextPath()+"/showUser");
	}
}
