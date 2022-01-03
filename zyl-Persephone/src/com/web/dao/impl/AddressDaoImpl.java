package com.web.dao.impl;

/**
 * 地址的数据访问层实现
 */

import com.web.dao.AddressDao;
import com.web.entity.UserAddr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.UserAddr;
import com.web.entity.User;
import com.web.util.JDBCUtil;

public class AddressDaoImpl implements AddressDao {

	/**
	 * 添加订单的方法
	 */
	
	@Override
	public boolean addAddress(UserAddr userAddr) {
		
		//定义影响行数的变量
		int count = 0;
				
		try {
					
			//获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
			
			//编写sql语句
			String sql = "insert into useraddr (UserId,City,County,Street,HouseNum)"
					+ "values (?,?,?,?,?)";
			
			//编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//设置参数
			ps.setInt(1, userAddr.getUserID());
			ps.setString(2, userAddr.getCity());
			ps.setString(3, userAddr.getCounty());
			ps.setString(4, userAddr.getStreet());
			ps.setString(5, userAddr.getHouseNum());
			
			//执行修改
			count = ps.executeUpdate();
			
			//关闭
			JDBCUtil.close();
			
		}catch(Exception e) {
			e.printStackTrace();//如果有错误，则在控制台抛出错误信息
		}
		
		//如果count>0,则返回true，否则返回false
		return count > 0 ? true : false;
		
	}

	/**
	 * 显示地址
	 */
	@Override
	public List<UserAddr> showAddress(int UserID) {
		List<UserAddr> list = new ArrayList<UserAddr>();
		try {
			//获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
			
			//编写sql语句
			String sql = "SELECT * FROM useraddr WHERE UserID = " + UserID;

			//编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				UserAddr c = new UserAddr();
				c.setAddrID(rs.getInt("AddrID"));
				c.setUserID(rs.getInt("UserID"));
				c.setCity(rs.getString("City"));
				c.setCounty(rs.getString("County"));
				c.setStreet(rs.getString("Street"));
				c.setHouseNum(rs.getString("HouseNum"));
				
				list.add(c);
			}	
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 删除地址
	 */
	@Override
	public boolean delAddress(int AddrID) {
		//定义影响行数的变量
		int count = 0;
						
		try {
							
			//获取数据库连接
			Connection conn = JDBCUtil.getConnectinon();
					
			//编写sql语句
			String sql = "DELETE FROM useraddr where AddrID = " + AddrID;
					
			//编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
					
			//执行修改
			count = ps.executeUpdate();
					
			//关闭
			JDBCUtil.close();
					
		}catch(Exception e) {
			e.printStackTrace();//如果有错误，则在控制台抛出错误信息
		}
				
		//如果count>0,则返回true，否则返回false
		return count > 0 ? true : false;
	}
	
	/**
	 * 显示所有地址
	 */
	@Override
	public List<UserAddr> showAllAddress() {
		List<UserAddr> list = new ArrayList<UserAddr>();
		
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
									
			// 编写sql
			String sql ="SELECT * FROM useraddr";
									
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
									
			// 执行查询
			ResultSet rs = ps.executeQuery();
					
			// 循环结果集
			while(rs.next()){
				// 实例化对象
				UserAddr userAddr = new UserAddr();
					
				userAddr.setUserID(rs.getInt("UserID"));
				userAddr.setAddrID(rs.getInt("AddrID"));
				userAddr.setCity(rs.getString("City")); 
				userAddr.setCounty(rs.getString("County"));
				userAddr.setStreet(rs.getString("Street"));
				userAddr.setHouseNum(rs.getString("HouseNum"));
									
				// 把对象添加到集合中去
				list.add(userAddr);
			}
			
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
