package com.web.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.biz.CartBiz;
import com.web.biz.OrderBiz;
import com.web.biz.OrderItemBiz;
import com.web.dao.OrderDao;
import com.web.dao.impl.OrderDaoImpl;
import com.web.entity.DrinkOrder;
import com.web.entity.OrderItem;
import com.web.entity.User;

public class OrderBizImpl implements OrderBiz {
	// 实例化订单的数据访问层
	OrderDao orderDao = new OrderDaoImpl();

	// 实例化订单条目的业务逻辑层
	OrderItemBiz orderItemBiz = new OrderItemBizImpl();
	
	// 实例化购物车的业务逻辑层
	CartBiz cartBiz = new CartBizImpl();
	
	/**
	 * 添加订单
	 */
	@Override
	public int addOrder(DrinkOrder drinkOrder) {
		
		int OrderID = 0;
		
		try {
			// 添加订单
			orderDao.addOrder(drinkOrder);
			OrderID = orderDao.getOrderId();
			
			// 清空购物车
			cartBiz.clearCart(drinkOrder.getUserID());
			 
			// 获取订单的条目信息
			List<OrderItem> list = drinkOrder.getOrderItems();
			
			// 循环
			for (OrderItem orderItem : list) {
				 // 添加订单条目
				orderItem.setOrderID(OrderID);
				orderItemBiz.addOrderItem(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OrderID;
	}

	/**
	 * 根据id查询订单信息
	 */
	@Override
	public DrinkOrder findOrderById(int OrderID) {
		// 查询订单的信息
		DrinkOrder drinkOrder = orderDao.findOrderById(OrderID);
				
		// 查询订单项的信息
		List<OrderItem> orderItemList = orderItemBiz.findOrderItemByOrder(drinkOrder);
		
		// 设置订单的订单项
		drinkOrder.setOrderItems(orderItemList);
		return drinkOrder;
	}

	/**
	 * 根据用户信息查询订单信息
	 */
	@Override
	public List<DrinkOrder> findOrderByUser(User user) {
		// 查询订单的信息
		List<DrinkOrder> list = orderDao.findOrderByUser(user);
		
		// 循环查询每一个订单的订单项的信息
		for(DrinkOrder drinkOrder : list) {
			List<OrderItem> orderItemList = orderItemBiz.findOrderItemByOrder(drinkOrder);
			drinkOrder.setOrderItems(orderItemList);
		}
		return list;
	}

	/**
	 * 取消订单
	 */
	@Override
	public boolean cancelOrder(int OrderID) {
		return orderDao.cancelOrder(OrderID);
	}

	/**
	 * 支付订单，改变订单支付状态
	 */
	@Override
	public boolean payOrder(int OrderID, int AddrID) {
		return orderDao.payOrder(OrderID, AddrID);
	}
	
	/**
	 * 后台查看订单
	 */
	@Override
	public List<DrinkOrder> showOrder(){
		return orderDao.showOrder();
	}
	
	/**
	 * 后台删除订单
	 */
	@Override
	public boolean deleteOrder(int OrderID) {
		return orderDao.deleteOrder(OrderID);
	}

	/**
	 * 后台显示总营业额
	 */
	@Override
	public float showTotalPrice() {
		return orderDao.showTotalPrice();
	}

	/**
	 * 后台显示订单总数
	 */
	@Override
	public int showOrderCount() {
		return orderDao.showOrderCount();
	}
}
