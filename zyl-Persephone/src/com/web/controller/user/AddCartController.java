package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.CartBiz;
import com.web.biz.impl.CartBizImpl;
import com.web.entity.CartItem;
import com.web.entity.User;

@WebServlet(urlPatterns="/addCart")
public class AddCartController extends HttpServlet {
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
		
		// 获取详情页表单的参数
		String id_str = req.getParameter("DrinkID");
		int DrinkID = Integer.valueOf(id_str).intValue();
		String DrinkSweet = req.getParameter("DrinkSweet");
		String DrinkTemp = req.getParameter("DrinkTemp");
		String DrinkSpec = req.getParameter("DrinkSpec");
		String num_str = req.getParameter("Number");
		int Number = Integer.valueOf(num_str).intValue();
		
		// 把参数赋值给购物车对象
		CartItem cartItem = new CartItem();
		cartItem.setUserID(UserID);
		cartItem.setDrinkID(DrinkID);
		cartItem.setDrinkSweet(DrinkSweet);
		cartItem.setDrinkTemp(DrinkTemp);
		cartItem.setDrinkSpec(DrinkSpec);
		cartItem.setNumber(Number);
		
		// 调用业务逻辑层的添加购物车方法
		boolean flag = cartBiz.addCart(cartItem);
		
		if (flag) {
			resp.getWriter().append("success");
		}
		else {
			resp.getWriter().append("fail");
		}
	}
}
