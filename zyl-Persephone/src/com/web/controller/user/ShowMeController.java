package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.biz.AddressBiz;
import com.web.biz.OrderBiz;
import com.web.biz.impl.AddressBizImpl;
import com.web.biz.impl.OrderBizImpl;
import com.web.entity.DrinkOrder;
import com.web.entity.User;
import com.web.entity.UserAddr;

@WebServlet(urlPatterns="/showMe")
public class ShowMeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取用户的信息
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			// 未登陆
			resp.getWriter().print("<script>alert('未登陆！');window.location.href='"
				+ req.getContextPath() + "/client/login.jsp';</script>");
			return;
		}
		
		// 根据用户的信息查询订单信息
		OrderBiz orderBiz = new OrderBizImpl();
		List<DrinkOrder> orderList = orderBiz.findOrderByUser(user);
		
		// 根据用户的信息查询地址信息
		AddressBiz addressBiz = new AddressBizImpl();
		List<UserAddr> addrList = addressBiz.showAddress(user.getUserID());
		
		// 把数据信息传递到前台
		req.setAttribute("orderList", orderList);
		req.setAttribute("addrList", addrList);
		
		// 转发跳转页面
		req.getRequestDispatcher("/client/me.jsp").forward(req, resp);
	}
}
