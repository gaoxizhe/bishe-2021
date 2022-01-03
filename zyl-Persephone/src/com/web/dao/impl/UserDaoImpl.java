package com.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.UserDao;
import com.web.entity.User;
import com.web.util.JDBCUtil;

/**
 * 用户的数据访问层实现类
 * @author 王佳祺
 *
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * 注册，或后台添加新用户
	 */
	@Override
	public boolean add(User user) {
		
		// 影响行数的变量
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "INSERT INTO `user`(UserName,UserPwd,UserPhone) "
					+ "VALUES (?,?,?);";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserPhone());
			
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
	 * 登陆
	 */
	@Override
	public User login(String UserName, String UserPwd) {
		// 实例化集合对象
		List<User> list = new ArrayList<User>();
		
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql ="SELECT * FROM `user` "
					+ "WHERE UserName=? and UserPwd=?";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setString(1, UserName);
			ps.setString(2, UserPwd);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环结果集
			while(rs.next()){
				// 实例化对象
				User user = new User();
				
				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPwd(rs.getString("UserPwd"));
				user.setUserPhone(rs.getString("UserPhone"));
				
				// 把对象添加到集合中去
				list.add(user);
			}
			
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size()>0 ? list.get(0) : null;
	}

	/**
	 * 修改名称
	 */
	@Override
	public boolean changeName(int UserID, User user) {
		// 影响行数的变量
		int count = 0;
						
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
								
			// 编写sql
			String sql = "UPDATE user SET UserName = ? "
					+ "WHERE UserID=" + UserID;

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setString(1, user.getUserName());
										
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
	 * 修改密码
	 */
	@Override
	public boolean changePwd(int UserID, User user) {
		// 影响行数的变量
		int count = 0;
								
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
										
			// 编写sql
			String sql = "UPDATE user SET UserPwd = ? "
					+ "WHERE UserID=" + UserID;

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
					
			// 设置参数
			ps.setString(1, user.getUserPwd());
												
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
	 * 修改电话
	 */
	@Override
	public boolean changePhone(int UserID, User user) {
		// 影响行数的变量
		int count = 0;
										
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
											
			// 编写sql
			String sql = "UPDATE user SET UserPhone = ? "
							+ "WHERE UserID=" + UserID;

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
							
			// 设置参数
			ps.setString(1, user.getUserPhone());
														
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
	 * 检查名字是否重复
	 */
	@Override
	public boolean checkName(String UserName) {
		// 实例化集合对象
		List<User> list = new ArrayList<User>();
												
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
													
			// 编写sql
			String sql = "SELECT * FROM `user` WHERE UserName = '" + UserName + "'";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
																
			// 执行查询
			ResultSet rs = ps.executeQuery();
						
			// 循环结果集
			while(rs.next()){
				// 实例化对象
				User user = new User();
				
				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPwd(rs.getString("UserPwd"));
				user.setUserPhone(rs.getString("UserPhone"));
						
				// 把对象添加到集合中去
				list.add(user);
			}
																
			// 关闭
			JDBCUtil.close();
												
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size() > 0 ? true : false;
	}

	/**
	 * 后台显示用户信息
	 */
	@Override
	public List<User> showUser() {
		List<User> list = new ArrayList<User>();
		
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
									
			// 编写sql
			String sql ="SELECT * FROM `user`";
									
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
									
			// 执行查询
			ResultSet rs = ps.executeQuery();
					
			// 循环结果集
			while(rs.next()){
				// 实例化对象
				User user = new User();
					
				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPwd(rs.getString("UserPwd"));
				user.setUserPhone(rs.getString("UserPhone"));
						
				// 把对象添加到集合中去
				list.add(user);
			}
			
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 后台更改用户信息
	 */
	@Override
	public boolean changeUser(User user) {
		// 影响行数的变量
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "UPDATE `user` SET UserName = ?,UserPwd = ?,"
					+ "UserPhone = ? WHERE UserID = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserPhone());
			ps.setInt(4, user.getUserID());
						
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
	 * 后台删除用户
	 */
	@Override
	public boolean deleteUser(int UserID) {
		// 影响行数的变量
		int count = 0;
				
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "DELETE FROM `user` WHERE UserID = " + UserID;

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
	 * 后台显示用户总数
	 */
	@Override
	public int showUserCount() {
		int UserCount = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
							
			// 编写sql
			String sql = "SELECT COUNT(*) FROM `user`";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
								
			// 执行查询
			ResultSet rs = ps.executeQuery();
						
			// 循环
			while(rs.next()){
				// 取结果集中的第一个值赋值给totalCount
				UserCount = rs.getInt(1);
			}
								
			// 关闭
			JDBCUtil.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserCount;
	}
}
