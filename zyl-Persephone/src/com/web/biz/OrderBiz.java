package com.web.biz;

import java.util.List;

import com.web.entity.DrinkOrder;
import com.web.entity.User;

public interface OrderBiz {
	/**
	 * 添加订单
	 * @param drinkOrder
	 * @return
	 */
	public int addOrder(DrinkOrder drinkOrder);
	
	/**
	 * 根据id查询订单信息
	 * @param OrderID
	 * @return
	 */
	public DrinkOrder findOrderById(int OrderID);
	
	/**
	 * 根据用户信息查询订单信息
	 * @param user
	 * @return
	 */
	public List<DrinkOrder> findOrderByUser(User user);
	
	/**
	 * 取消订单
	 * @param OrderID
	 * @return
	 */
	public boolean cancelOrder(int OrderID);
	
	/**
	 * 支付订单，改变订单支付状态
	 * @param OrderID
	 * @param AddrID
	 * @return
	 */
	public boolean payOrder(int OrderID, int AddrID);

	/**
	 * 后台查看订单
	 * @return
	 */
	public List<DrinkOrder> showOrder();
	
	/**
	 * 后台删除订单
	 * @param OrderID
	 * @return
	 */
	public boolean deleteOrder(int OrderID);
	
	/**
	 * 后台显示总营业额
	 * @return
	 */
	public float showTotalPrice();
	
	/**
	 * 后台显示订单总数
	 * @return
	 */
	public int showOrderCount();
}
