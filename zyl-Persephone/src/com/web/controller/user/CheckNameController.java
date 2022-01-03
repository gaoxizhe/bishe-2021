package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;

@WebServlet(urlPatterns="/checkName")
public class CheckNameController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取从前台页面传过来的参数
		String UserName = req.getParameter("UserName");
		
		// 调用业务逻辑层的方法
		UserBiz userBiz = new UserBizImpl();
		boolean flag = userBiz.checkName(UserName);
		
		if (flag)
			resp.getWriter().append("same");
		else
			resp.getWriter().append("notSame");
	}
}
