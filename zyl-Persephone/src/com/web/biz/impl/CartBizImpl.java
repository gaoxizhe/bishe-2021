package com.web.biz.impl;

import java.util.List;

import com.web.biz.CartBiz;
import com.web.dao.CartDao;
import com.web.dao.impl.CartDaoImpl;
import com.web.entity.CartItem;

/**
 * 购物车的业务逻辑层
 */
public class CartBizImpl implements CartBiz {
	// 实例化数据访问层
	CartDao cartDao = new CartDaoImpl();
	
	/**
	 * 添加购物车
	 */
	@Override
	public boolean addCart(CartItem cartItem) {
		// 调用数据访问层的添加购物车方法
		return cartDao.addCart(cartItem);
	}
	
	/**
	 * 查询购物车中的饮品
	 */
	@Override
	public List<CartItem> showCart(int UserID) {
		return cartDao.showCart(UserID);
	}

	/**
	 * 删除购物车中的饮品
	 */
	@Override
	public List<CartItem> deleteCart(CartItem cartItem) {
		return cartDao.deleteCart(cartItem);
	}

	/**
	 * 更改购物车中的饮品
	 */
	@Override
	public List<CartItem> changeCart(CartItem cartItem) {
		return cartDao.changeCart(cartItem);
	}

	/**
	 * 清空购物车
	 */
	@Override
	public boolean clearCart(int UserID) {
		return cartDao.clearCart(UserID);
	}
}
