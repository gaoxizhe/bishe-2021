package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.CartDao;
import com.web.dao.ProductDao;
import com.web.entity.CartItem;
import com.web.entity.Drink;
import com.web.util.JDBCUtil;

public class CartDaoImpl implements CartDao {
	// 实例化商品的数据访问层
	ProductDao productDao = new ProductDaoImpl();
	
	/**
	 * 添加购物车
	 */
	@Override
	public boolean addCart(CartItem cartItem) {
		// 影响行数的变量
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "INSERT INTO cartitem(UserID,DrinkID,DrinkSweet,DrinkTemp,DrinkSpec,Number)"
					+ "VALUES (?,?,?,?,?,?);";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setInt(1, cartItem.getUserID());
			ps.setInt(2, cartItem.getDrinkID());
			ps.setString(3, cartItem.getDrinkSweet());
			ps.setString(4, cartItem.getDrinkTemp());
			ps.setString(5, cartItem.getDrinkSpec());
			ps.setInt(6, cartItem.getNumber());
			
			// 执行修改
			count = ps.executeUpdate();

			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count > 0 ? true : false;
	}

	/**
	 * 查询购物车中的饮品
	 */
	@Override
	public List<CartItem> showCart(int UserID) {
		List<CartItem> list = new ArrayList<CartItem>();
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT * FROM cartitem WHERE UserID = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setInt(1, UserID);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				CartItem c = new CartItem();
				c.setUserID(rs.getInt("UserID"));
				c.setDrinkID(rs.getInt("DrinkID"));
				c.setDrinkSweet(rs.getString("DrinkSweet"));
				c.setDrinkTemp(rs.getString("DrinkTemp"));
				c.setDrinkSpec(rs.getString("DrinkSpec"));
				c.setNumber(rs.getInt("Number"));
				
				// 根据饮品id查询饮品信息
				int DrinkID = c.getDrinkID();
				Drink drink = productDao.findDrinkById(DrinkID);
				c.setDrink(drink);
				
				// 把购物车对象添加到集合中
				list.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 删除购物车中的饮品
	 */
	@Override
	public List<CartItem> deleteCart(CartItem cartItem) {
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "DELETE FROM cartitem WHERE UserID = ? AND DrinkID = ? "
					+ "AND DrinkSweet = ? AND DrinkTemp = ? AND DrinkSpec = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setInt(1, cartItem.getUserID());
			ps.setInt(2, cartItem.getDrinkID());
			ps.setString(3, cartItem.getDrinkSweet());
			ps.setString(4, cartItem.getDrinkTemp());
			ps.setString(5, cartItem.getDrinkSpec());
			
			// 执行修改
			ps.executeUpdate();
			
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showCart(cartItem.getUserID());
	}
	
	/**
	 * 改变购物车数量
	 */
	@Override
	public List<CartItem> changeCart(CartItem cartItem) {
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "UPDATE cartitem SET Number = ? WHERE UserID = ? AND DrinkID = ? "
					+ "AND DrinkSweet = ? AND DrinkTemp = ? AND DrinkSpec = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setInt(1, cartItem.getNumber());
			ps.setInt(2, cartItem.getUserID());
			ps.setInt(3, cartItem.getDrinkID());
			ps.setString(4, cartItem.getDrinkSweet());
			ps.setString(5, cartItem.getDrinkTemp());
			ps.setString(6, cartItem.getDrinkSpec());
			
			// 执行修改
			ps.executeUpdate();
			
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showCart(cartItem.getUserID());
	}

	/**
	 * 清空购物车
	 */
	@Override
	public boolean clearCart(int UserID) {
		// 定义影响的行数
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "DELETE FROM cartitem WHERE UserID = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setInt(1, UserID);
			
			// 执行修改
			count = ps.executeUpdate();

			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count > 0 ? true : false;
	}
}
