package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.User;

/**
 * 登陆控制层
 * @author 王佳祺
 *
 */
@WebServlet(urlPatterns="/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 获取登录页面表单的参数值
		String UserName = req.getParameter("UserName");
		String UserPwd = req.getParameter("UserPwd");
		
		// 调用业务逻辑层的登录方法
		UserBiz userBiz = new UserBizImpl();
		User user = userBiz.login(UserName, UserPwd);
		
		if (user != null) {
			// 保存用户的会话信息到session中
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			// 登录成功
//			resp.getWriter().print("<script>alert('登陆成功!');window.location.href='"
//					+ req.getContextPath() + "/ShowIndex';</script>");
			resp.sendRedirect(req.getContextPath()+"/client/login_success.jsp");
		}
		else {
			// 登录失败
			resp.getWriter().print("<script>alert('用户名或密码错误!');window.location.href='"
					+ req.getContextPath() + "/client/login.jsp';</script>");
//			resp.sendRedirect(req.getContextPath()+"/client/login.jsp");
		}
	}
}
