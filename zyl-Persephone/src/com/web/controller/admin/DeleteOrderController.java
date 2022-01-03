package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.OrderBiz;
import com.web.biz.impl.OrderBizImpl;

@WebServlet(urlPatterns="/deleteOrder")
public class DeleteOrderController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OrderBiz orderBiz = new OrderBizImpl();
				
		// 获取从前台页面传过来的参数
		String OrderID_str = req.getParameter("OrderID");
		int OrderID = Integer.valueOf(OrderID_str).intValue();
		
		// 调用业务逻辑层的方法
		boolean flag = orderBiz.deleteOrder(OrderID);
				
		if (flag) {
			resp.getWriter().print("<script>alert('删除成功');window.location.href='"
					+ req.getContextPath() + "/showOrder';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('删除失败');window.location.href='"
					+ req.getContextPath() + "/showOrder';</script>");
		}
	}
}
