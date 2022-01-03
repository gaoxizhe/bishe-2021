package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AddressBiz;
import com.web.biz.OrderBiz;
import com.web.biz.impl.AddressBizImpl;
import com.web.biz.impl.OrderBizImpl;
import com.web.entity.DrinkOrder;
import com.web.entity.User;
import com.web.entity.UserAddr;

@WebServlet(urlPatterns="/checkout")
public class ShowCheckoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 读取session中的用户信息
		User user = (User)req.getSession().getAttribute("user");
		if(user == null){
			// 未登陆
			resp.getWriter().print("<script>alert('未登陆！');window.location.href='"
				+ req.getContextPath() + "/client/login.jsp';</script>");
			return;
		}
		int UserID = user.getUserID();
				
		// 获取OrderID
		String OrderID_str = req.getParameter("OrderID");
		int OrderID = Integer.valueOf(OrderID_str).intValue();
		
		// 调用业务逻辑层的查询方法
		OrderBiz orderBiz = new OrderBizImpl();
		DrinkOrder drinkOrder = orderBiz.findOrderById(OrderID);
		AddressBiz addressBiz = new AddressBizImpl();
		List<UserAddr> addrList = addressBiz.showAddress(UserID);
		
		// 把数据传到界面中
		req.setAttribute("order", drinkOrder);
		req.setAttribute("addrList", addrList);
		
		// 转发
		req.getRequestDispatcher("/client/checkout.jsp").forward(req, resp);
	}
}
