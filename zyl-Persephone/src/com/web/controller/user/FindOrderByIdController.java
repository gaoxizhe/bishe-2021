package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderBiz;
import com.web.biz.impl.OrderBizImpl;
import com.web.entity.DrinkOrder;

@WebServlet(urlPatterns="/findOrderById")
public class FindOrderByIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取OrderID
		String OrderID_str = req.getParameter("OrderID");
		int OrderID = Integer.valueOf(OrderID_str).intValue();
		
		// 调用业务逻辑层的查询方法
		OrderBiz orderBiz = new OrderBizImpl();
		DrinkOrder drinkOrder = orderBiz.findOrderById(OrderID);
		
		// 把数据传到界面中
		req.setAttribute("order", drinkOrder);
		
		// 转发
		req.getRequestDispatcher("/client/order_detail.jsp").forward(req, resp);
	}
}
