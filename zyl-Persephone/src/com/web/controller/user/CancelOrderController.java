package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderBiz;
import com.web.biz.OrderItemBiz;
import com.web.biz.impl.OrderBizImpl;
import com.web.biz.impl.OrderItemBizImpl;

@WebServlet(urlPatterns="/cancelOrder")
public class CancelOrderController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		OrderBiz orderBiz = new OrderBizImpl();
		OrderItemBiz orderItemBiz = new OrderItemBizImpl();
		
		// 获取从前台页面传过来的参数
		String id_str = req.getParameter("OrderID");
		int OrderID = Integer.valueOf(id_str).intValue();
		
		// 调用业务逻辑层的取消方法
		orderBiz.cancelOrder(OrderID);
		orderItemBiz.cancelOrderItem(OrderID);

		// 转发跳转页面
		resp.sendRedirect(req.getContextPath()+"/showMe");
	}
}
