package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.CartBiz;
import com.web.biz.OrderBiz;
import com.web.biz.impl.CartBizImpl;
import com.web.biz.impl.OrderBizImpl;
import com.web.entity.CartItem;
import com.web.entity.DrinkOrder;
import com.web.entity.OrderItem;
import com.web.entity.User;

@WebServlet(urlPatterns="/createOrder")
public class CreateOrderController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 读取session中的用户信息
		User user = (User)req.getSession().getAttribute("user");
		int UserID = user.getUserID();
		
		// 获取购物车信息
		CartBiz cartBiz = new CartBizImpl();
		List<CartItem> cart = cartBiz.showCart(UserID);
		if(cart.size() == 0) {
			// 购物车中无商品
			resp.getWriter().print("<script>alert('购物车中无商品！请添加商品');window.location.href='"
					+ req.getContextPath() + "/showProductByPage?DrinkType=All';</script>");
			return;
		}
		
		// 设置订单的信息
		DrinkOrder drinkOrder = new DrinkOrder();
		drinkOrder.setUserID(UserID);
		drinkOrder.setUser(user);
		float TotalPrice = 0;
		
		// 设置订单条目信息
		for(CartItem cartItem : cart){
			// 实例化订单条目
			OrderItem orderItem = new OrderItem();
			
			// 设置属性
			orderItem.setDrinkID(cartItem.getDrinkID());
			orderItem.setDrink(cartItem.getDrink());
			orderItem.setDrinkSweet(cartItem.getDrinkSweet());
			orderItem.setDrinkTemp(cartItem.getDrinkTemp());
			orderItem.setDrinkSpec(cartItem.getDrinkSpec());
			orderItem.setNumber(cartItem.getNumber());
			
			// 计算总价
			if(orderItem.getDrinkSpec().equals("超级杯")){
				TotalPrice = TotalPrice + orderItem.getDrink().getDrinkPrice_Super() * orderItem.getNumber();
			}
			else if(orderItem.getDrinkSpec().equals("大杯")){
				TotalPrice = TotalPrice + orderItem.getDrink().getDrinkPrice_Big() * orderItem.getNumber();
			}
			else if(orderItem.getDrinkSpec().equals("中杯")){
				TotalPrice = TotalPrice + orderItem.getDrink().getDrinkPrice_Medium() * orderItem.getNumber();
			}
			
			// 向订单中添加订单条目信息
			drinkOrder.getOrderItems().add(orderItem);	
		}
		drinkOrder.setTotalPrice(TotalPrice);
		
		// 实例化订单的业务逻辑层
		OrderBiz orderBiz = new OrderBizImpl();
		int OrderID = orderBiz.addOrder(drinkOrder);
		
		resp.sendRedirect(req.getContextPath()+"/checkout?OrderID=" + OrderID);
	}
}
