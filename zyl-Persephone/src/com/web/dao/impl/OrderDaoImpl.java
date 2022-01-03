package com.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.OrderDao;
import com.web.entity.DrinkOrder;
import com.web.entity.User;
import com.web.util.JDBCUtil;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加订单
	 */
	@Override
	public boolean addOrder(DrinkOrder drinkOrder) {
		// 定义影响的行数
		int count = 0;
		
		try {
			// 获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "INSERT INTO drinkorder(UserID,TotalPrice,"
					+ "OrderTime) VALUES (?,?,?)";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setInt(1, drinkOrder.getUserID());
			ps.setFloat(2, drinkOrder.getTotalPrice());
			ps.setDate(3, new Date(new java.util.Date().getTime()));
			
			// 执行添加
			count = ps.executeUpdate();

			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count > 0 ? true : false;
	}

	@Override
	public int getOrderId() {
		int OrderID = 0;
		
		try {
			// 获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "SELECT MAX(OrderID) FROM drinkorder";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环结果集
			while(rs.next()){
				OrderID = rs.getInt("MAX(OrderID)");
			}
			
			// 关闭
			JDBCUtil.close();
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
		// 实例化集合对象
		List<DrinkOrder> list = new ArrayList<DrinkOrder>();
		
		try {
			// 获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "SELECT * FROM drinkorder WHERE OrderID = '" + OrderID + "'";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环结果集
			while(rs.next()){
				DrinkOrder drinkOrder = new DrinkOrder();
				drinkOrder.setOrderID(rs.getInt("OrderID"));
				drinkOrder.setUserID(rs.getInt("UserID"));
				drinkOrder.setAddrID(rs.getInt("AddrID"));
				drinkOrder.setTotalPrice(rs.getFloat("TotalPrice"));
				drinkOrder.setOrderTime(rs.getDate("OrderTime"));
				drinkOrder.setPayState(rs.getInt("payState"));
				
				// 把对象一个一个的添加到集合中
				list.add(drinkOrder);
			}
			
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size()> 0 ? list.get(0) : null;
	}

	/**
	 * 根据用户信息查询订单信息
	 */
	@Override
	public List<DrinkOrder> findOrderByUser(User user) {
		// 实例化集合对象
		List<DrinkOrder> list = new ArrayList<DrinkOrder>();
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT * FROM drinkorder WHERE UserID = " + user.getUserID();

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				// 实例化订单对象
				DrinkOrder drinkOrder = new DrinkOrder();
				drinkOrder.setOrderID(rs.getInt("OrderID"));
				drinkOrder.setUserID(rs.getInt("UserID"));
				drinkOrder.setUser(user);
				drinkOrder.setAddrID(rs.getInt("AddrID"));
				drinkOrder.setTotalPrice(rs.getFloat("TotalPrice"));
				drinkOrder.setOrderTime(rs.getDate("OrderTime"));
				drinkOrder.setPayState(rs.getInt("PayState"));
				
				// 把对象添加到集合中
				list.add(0, drinkOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 取消订单
	 */
	@Override
	public boolean cancelOrder(int OrderID) {
		// 定义影响的行数
		int count = 0;
				
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "DELETE FROM drinkorder WHERE OrderID = " + OrderID;

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
	 * 支付订单，改变订单支付状态
	 */
	@Override
	public boolean payOrder(int OrderID, int AddrID) {
		// 定义影响的行数
		int count = 0;

		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "UPDATE drinkorder SET AddrID = ?, PayState = 1 WHERE OrderID = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setInt(1, AddrID);
			ps.setInt(2, OrderID);
					
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
	 * 后台查看订单
	 */
	@Override
	public List<DrinkOrder> showOrder() {
		List<DrinkOrder> list = new ArrayList<DrinkOrder>();
		
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
									
			// 编写sql
			String sql ="SELECT * FROM `drinkorder`";
									
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
									
			// 执行查询
			ResultSet rs = ps.executeQuery();
					
			// 循环结果集
			while(rs.next()){
				// 实例化对象
				DrinkOrder drinkOrder = new DrinkOrder();
					
				drinkOrder.setOrderID(rs.getInt("OrderID"));
				drinkOrder.setUserID(rs.getInt("UserID"));
				drinkOrder.setAddrID(rs.getInt("AddrID"));
				drinkOrder.setTotalPrice(rs.getFloat("TotalPrice"));
				drinkOrder.setOrderTime(rs.getDate("OrderTime"));
				drinkOrder.setPayState(rs.getInt("PayState"));
						
				// 把对象添加到集合中去
				list.add(drinkOrder);
			}
			
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 后台删除订单
	 */
	@Override
	public boolean deleteOrder(int OrderID) {
		
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "DELETE FROM `drinkorder` WHERE OrderID = " + OrderID;

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
	 * 后台显示总营业额
	 */
	@Override
	public float showTotalPrice() {
		float TotalPrice = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "SELECT SUM(TotalPrice) FROM drinkorder WHERE PayState = 1";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
								
			// 执行查询
			ResultSet rs = ps.executeQuery();
						
			// 循环
			while(rs.next()){
				// 取结果集中的第一个值赋值给totalCount
				TotalPrice = rs.getFloat(1);
			}
								
			// 关闭
			JDBCUtil.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TotalPrice;
	}

	/**
	 * 后台显示订单总数
	 */
	@Override
	public int showOrderCount() {
		int OrderCount = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "SELECT COUNT(*) FROM drinkorder";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
								
			// 执行查询
			ResultSet rs = ps.executeQuery();
						
			// 循环
			while(rs.next()){
				// 取结果集中的第一个值赋值给totalCount
				OrderCount = rs.getInt(1);
			}
								
			// 关闭
			JDBCUtil.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OrderCount;
	}
}
