package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.User;

@WebServlet(urlPatterns="/addUser")
public class AddUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		UserBiz userBiz = new UserBizImpl();
				
		// 获取从前台页面传过来的参数
		String UserName = req.getParameter("add-name");
		String UserPwd = req.getParameter("add-pwd");
		String UserPhone = req.getParameter("add-pho");
				
		// 把参数赋值给用户对象
		User user = new User();
		user.setUserName(UserName);
		user.setUserPwd(UserPwd);
		user.setUserPhone(UserPhone);
				
		// 调用业务逻辑层的更改方法
		boolean flag = userBiz.register(user);
				
		if (flag) {
			resp.getWriter().print("<script>alert('添加成功!');window.location.href='"
					+ req.getContextPath() + "/showUser';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('添加失败!');window.location.href='"
					+ req.getContextPath() + "/showUser';</script>");
		}
	}
}
