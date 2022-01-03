package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.CartBiz;
import com.web.biz.impl.CartBizImpl;
import com.web.entity.User;

@WebServlet(urlPatterns="/clearCart")
public class ClearCartController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		CartBiz cartBiz = new CartBizImpl();
				
		// 读取session中的用户信息
		User user = (User)req.getSession().getAttribute("user");
		int UserID = user.getUserID();
		
		// 调用业务逻辑层的清空方法
		cartBiz.clearCart(UserID);
		
		// 跳转至全部商品页
		resp.sendRedirect(req.getContextPath()+"/showProductByPage?DrinkType=All");
	}
}
