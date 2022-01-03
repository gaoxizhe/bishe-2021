package com.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderBiz;
import com.web.biz.impl.OrderBizImpl;
import com.web.entity.DrinkOrder;

@WebServlet(urlPatterns="/showOrder")
public class ShowOrderController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 调用业务逻辑层的方法
		OrderBiz orderBiz = new OrderBizImpl();
		List<DrinkOrder> list = orderBiz.showOrder();
		
		// 把数据传到界面中
		req.setAttribute("orderList", list);

		// 转发
		req.getRequestDispatcher("/admin/ordertable.jsp").forward(req, resp);
	}
}
