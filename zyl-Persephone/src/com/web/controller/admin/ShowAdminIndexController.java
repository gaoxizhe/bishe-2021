package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderBiz;
import com.web.biz.OrderItemBiz;
import com.web.biz.UserBiz;
import com.web.biz.impl.OrderBizImpl;
import com.web.biz.impl.OrderItemBizImpl;
import com.web.biz.impl.UserBizImpl;

@WebServlet(urlPatterns="/showAdminIndex")
public class ShowAdminIndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 调用业务逻辑层的方法
		OrderBiz orderBiz = new OrderBizImpl();
		float toltalMoney = orderBiz.showTotalPrice();
		int orderCount = orderBiz.showOrderCount();
		
		OrderItemBiz orderItemBiz = new OrderItemBizImpl();
		int totalCount = orderItemBiz.showTotalCount();
		
		UserBiz userBiz = new UserBizImpl();
		int userCount = userBiz.showUserCount();
		
		// 把数据传到界面中
		req.setAttribute("toltalMoney", toltalMoney);
		req.setAttribute("orderCount", orderCount);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("userCount", userCount);
		
		// 转发跳转页面
		req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
	}
}
