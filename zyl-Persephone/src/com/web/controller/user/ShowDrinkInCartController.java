package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.biz.CartBiz;
import com.web.biz.impl.CartBizImpl;
import com.web.entity.CartItem;
import com.web.entity.User;

@WebServlet(urlPatterns="/showCart")
public class ShowDrinkInCartController extends HttpServlet {
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
		if(user == null){
			// 未登陆
			resp.getWriter().print("<script>alert('未登陆！');window.location.href='"
				+ req.getContextPath() + "/client/login.jsp';</script>");
			return;
		}
		int UserID = user.getUserID();
		
		// 调用业务逻辑层的查询方法
		List<CartItem> list = cartBiz.showCart(UserID);
		
		// 判断是否是异步请求
		String flag = req.getParameter("flag");
		if(flag == null){
			// 把数据传递给jsp页面
			req.setAttribute("CartItem", list);
			
			// 转发跳转页面
			req.getRequestDispatcher("/client/cart.jsp").forward(req, resp);
		}
		else {
			// 把数据传递到前台页面
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().append(json);
			resp.getWriter().flush();
			resp.getWriter().close();
		}
	}
}
