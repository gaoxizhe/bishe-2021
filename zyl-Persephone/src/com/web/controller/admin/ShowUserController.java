package com.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.User;

@WebServlet(urlPatterns="/showUser")
public class ShowUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 调用业务逻辑层的方法
		UserBiz userBiz = new UserBizImpl();
		List<User> list = userBiz.showUser();
		
		// 把数据传到界面中
		req.setAttribute("userList", list);

		// 转发
		req.getRequestDispatcher("/admin/usertable.jsp").forward(req, resp);
	}
}
