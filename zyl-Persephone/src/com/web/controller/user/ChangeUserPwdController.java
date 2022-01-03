package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.User;

@WebServlet(urlPatterns="/changeUserPwd")
public class ChangeUserPwdController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserBiz userBiz = new UserBizImpl();
		
		User user = (User)req.getSession().getAttribute("user");
		
		int UserID = user.getUserID();
			

		 
		String UserPwd = req.getParameter("UserPwd");
		String OldUserPwd = req.getParameter("OldUserPwd");
		
		boolean flag1 = OldUserPwd.equals(user.getUserPwd());
		boolean flag2 = OldUserPwd.equals(UserPwd);
		
		if(flag1) {
			if(flag2) {
				resp.getWriter().print("<script>alert('原密码与新密码相同，修改密码失败!');</script>");
			}
			else {
			user.setUserPwd(UserPwd);
		    
			userBiz.changePwd(UserID,user);
			
			resp.sendRedirect(req.getContextPath()+"/showMe");
			}
		}
		else {
			resp.getWriter().print("<script>alert('原密码输入不正确，修改密码失败!');</script>");
		}
}}
