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

@WebServlet(urlPatterns="/changeUserPhone")
public class ChangeUserPhoneController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserBiz userBiz = new UserBizImpl();
		
		User user = (User)req.getSession().getAttribute("user");
		
		int UserID = user.getUserID();
			

		 
		String UserPhone = req.getParameter("UserPhone");
			
	    user.setUserPhone(UserPhone);
		    
		userBiz.changePhone(UserID,user);
			
		resp.sendRedirect(req.getContextPath()+"/showMe");
}}

