package com.web.dao;

import java.util.List;

import com.web.entity.CartItem;

/**
 * 购物车的数据访问层接口
 * @author 王佳祺
 *
 */
public interface CartDao {
	/**
	 * 添加购物车
	 * @param UserID
	 * @param DrinkID
	 * @param sweet
	 * @param temp
	 * @param spec
	 * @param num
	 * @return
	 */
	 public boolean addCart(CartItem cartItem);
	 
	 /**
	  * 查询购物车中的饮品
	  * @param UserID
	  * @return
	  */
	 public List<CartItem> showCart(int UserID);
	 
	 /**
	  * 删除购物车中的饮品
	  * @param UserID
	  * @param DrinkID
	  * @return
	  */
	 public List<CartItem> deleteCart(CartItem cartItem);
	 
	 /**
	  * 改变购物车数量
	  * @param UserID
	  * @param DrinkID
	  * @param Number
	  * @return
	  */
	 public List<CartItem> changeCart(CartItem cartItem);
	 
	 /**
	  * 清空购物车
	  * @param UserID
	  * @return
	  */
	 public boolean clearCart(int UserID);
}
