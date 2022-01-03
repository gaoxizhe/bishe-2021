package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.dao.OrderItemDao;
import com.web.dao.ProductDao;
import com.web.entity.Drink;
import com.web.entity.DrinkOrder;
import com.web.entity.OrderItem;
import com.web.util.JDBCUtil;

public class OrderItemDaoImpl implements OrderItemDao {
	// 实例化商品的数据访问层
	ProductDao productDao = new ProductDaoImpl();
		
	@Override
	public boolean addOrderItem(OrderItem orderItem) {
		// 定义影响的行数
		int count = 0;
		
		try {
			// 获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "INSERT INTO orderitem(OrderID,DrinkID,DrinkSweet,"
					+ "DrinkTemp,DrinkSpec,Number) VALUES (?,?,?,?,?,?)";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setInt(1, orderItem.getOrderID());
			ps.setInt(2, orderItem.getDrinkID());
			ps.setString(3, orderItem.getDrinkSweet());
			ps.setString(4, orderItem.getDrinkTemp());
			ps.setString(5, orderItem.getDrinkSpec());
			ps.setInt(6, orderItem.getNumber());
			
			// 执行添加
			count = ps.executeUpdate();

			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count > 0 ? true : false;
	}

	/**
	 * 根据订单查询订单项
	 */
	@Override
	public List<OrderItem> findOrderItemByOrder(DrinkOrder drinkOrder) {
		// 实例化集合对象
		List<OrderItem> list = new ArrayList<OrderItem>();
		try {
			// 获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "SELECT * FROM orderitem WHERE OrderID = '"
					+ drinkOrder.getOrderID() + "'";

			//编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//执行查询
			ResultSet rs = ps.executeQuery();

			// 循环
			while(rs.next()){
				// 实例化订单项对象
				OrderItem orderItem = new OrderItem();
				
				orderItem.setOrderID(rs.getInt("OrderID"));
				orderItem.setDrinkID(rs.getInt("DrinkID"));
				orderItem.setDrinkSweet(rs.getString("DrinkSweet"));
				orderItem.setDrinkTemp(rs.getString("DrinkTemp"));
				orderItem.setDrinkSpec(rs.getString("DrinkSpec"));
				orderItem.setNumber(rs.getInt("Number"));
				
				// 根据商品的id查询商品信息
				int DrinkID = orderItem.getDrinkID();
				Drink drink = productDao.findDrinkById(DrinkID);
				
				orderItem.setDrink(drink);
				
				// 把对象添加到集合中
				list.add(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 取消订单中的订单项
	 */
	@Override
	public boolean cancelOrderItem(int OrderID) {
		// 定义影响的行数
		int count = 0;
						
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "DELETE FROM orderitem WHERE OrderID = " + OrderID;

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
							
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
	 * 后台显示总销量
	 */
	@Override
	public int showTotalCount() {
		int TotalCount = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "SELECT SUM(Number) FROM orderitem";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
								
			// 执行查询
			ResultSet rs = ps.executeQuery();
						
			// 循环
			while(rs.next()){
				// 取结果集中的第一个值赋值给totalCount
				TotalCount = rs.getInt(1);
			}
								
			// 关闭
			JDBCUtil.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TotalCount;
	}

	/**
	 * 后台显示每天每种类别的总销量
	 */
	@Override
	public List<HashMap<String, String>> showTotalCountByDay() {
		// 实例化集合
		List<HashMap<String, String>> list = new ArrayList<>();
		
		try {
			// 获取数据库的连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "SELECT SUM(orderitem.Number),drinkorder.OrderTime,drink.DrinkType "
					+ "FROM drinkorder,orderitem,drink "
					+ "WHERE orderitem.OrderID = drinkorder.OrderID "
					+ "AND orderitem.DrinkID = drink.DrinkID "
					+ "AND drinkorder.PayState = 1 "
					+ "AND drinkorder.OrderTime > DATE_SUB(NOW(), INTERVAL 8 DAY) "
					+ "AND drinkorder.OrderTime < DATE_SUB(NOW(), INTERVAL 1 DAY) "
					+ "GROUP BY drink.DrinkType,drinkorder.OrderTime,drinkorder.OrderTime "
					+ "ORDER BY drinkorder.OrderTime";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("num", rs.getString(1));
				map.put("date", rs.getString(2));
				map.put("type", rs.getString(3));
				
				list.add(map);
			}

			// 关闭数据库
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
