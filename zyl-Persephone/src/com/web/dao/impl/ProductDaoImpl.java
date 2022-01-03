package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.ProductDao;
import com.web.entity.Drink;
import com.web.entity.User;
import com.web.util.JDBCUtil;

public class ProductDaoImpl implements ProductDao {
	/**
	 * 获取商品的总条数
	 */
	@Override
	public Integer findAllCount(String DrinkType) {
		// 定义总条数
		Integer totalCount = 0;
				
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT COUNT(*) FROM drink";
			
			if(!"All".equals(DrinkType)){
				sql+=" WHERE DrinkType='"+DrinkType+"'";
			}
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				// 取结果集中的第一个值赋值给totalCount
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	/**
	 * 根据类别分页查询数据
	 */
	@Override
	public List<Drink> findByPage(Integer currentPage, int currentCount, String DrinkType) {
		// 实例化集合对象
		List<Drink> list = new ArrayList<Drink>();
		
		try {
			// 获取数据库的连接
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
//			String sql = null;
//				
//			// 如果是根据类别查询商品的数据信息
//			if(!"全部商品".equals(DrinkType)){
//				sql = "select * from Drinks where DrinkType=? limit ?,?";
//			}else{
//				sql = "select * from Drinks limit ?,?";
//			}
			
			StringBuffer sb = new StringBuffer("SELECT * FROM drink");
			
			// 如果是根据类别查询商品的数据信息
			if(!"All".equals(DrinkType)){
				sb.append(" WHERE DrinkType='"+DrinkType+"'");
			}
			
			// 添加分页查询的参数
			sb.append(" limit ?,?");
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			
			// 设置参数
			ps.setInt(1, (currentPage-1)*currentCount);  // 设置分页查询排除的笔数
			ps.setInt(2, currentCount);  // 设置分页查询每页显示的条数
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环结果集对象
			while(rs.next()){
				Drink d = new Drink();
				d.setDrinkID(rs.getInt("DrinkID"));
				d.setDrinkName(rs.getString("DrinkName"));
				d.setDrinkPrice_Super(rs.getFloat("DrinkPrice_Super"));
				d.setDrinkPrice_Big(rs.getFloat("DrinkPrice_Big"));
				d.setDrinkPrice_Medium(rs.getFloat("DrinkPrice_Medium"));
				d.setDrinkType(rs.getString("DrinkType"));
				d.setDrinkDesc(rs.getString("DrinkDesc"));
				d.setPicAddres(rs.getString("PicAddres"));
				
				// 把对象添加到集合中取
				list.add(d);
			}
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据字段搜索总条数
	 */
	@Override
	public Integer findDrinkByNameAllCount(String searchfield,String DrinkType) {
		// 定义总条数
		Integer totalCount = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT COUNT(*) FROM drink WHERE DrinkName LIKE '%"
					+ searchfield + "%'";
			if(!"All".equals(DrinkType)){
				sql+=" AND DrinkType='"+DrinkType+"'";
			}
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环结果集
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			
			// 关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	/**
	 * 根据字段搜索数据
	 */
	@Override
	public List<Drink> findDrinkByName(int currentPage, int currentCount, String searchfield, String DrinkType) {
		List<Drink> list = new  ArrayList<Drink>();
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT * FROM drink WHERE DrinkName LIKE '%"
					+ searchfield + "%'";
			if(!"All".equals(DrinkType)){
				sql+=" AND DrinkType='"+DrinkType+"'";
			}
			sql+=" LIMIT ?,?";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
//			ps.setString(1, "'%" + searchfield + "%'");
			ps.setInt(1, (currentPage - 1) * currentCount);
			ps.setInt(2, currentCount);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();

			// 循环结果集
			while (rs.next()) {
				Drink d = new Drink();
				d.setDrinkID(rs.getInt("DrinkID"));
				d.setDrinkName(rs.getString("DrinkName"));
				d.setDrinkPrice_Super(rs.getFloat("DrinkPrice_Super"));
				d.setDrinkPrice_Big(rs.getFloat("DrinkPrice_Big"));
				d.setDrinkPrice_Medium(rs.getFloat("DrinkPrice_Medium"));
				d.setDrinkType(rs.getString("DrinkType"));
				d.setDrinkDesc(rs.getString("DrinkDesc"));
				d.setPicAddres(rs.getString("PicAddres"));
				
				//把对象添加到集合中取
				list.add(d);
			}
						
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据id查询商品详情
	 */
	@Override
	public Drink findDrinkById(int id) {
		// 实例化集合
		List<Drink> list = new ArrayList<Drink>();
				
		try {
			// 获取数据库的连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT * FROM drink WHERE DrinkID=?";
			
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 设置参数
			ps.setInt(1, id);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				Drink d = new Drink();
				d.setDrinkID(rs.getInt("DrinkID"));
				d.setDrinkName(rs.getString("DrinkName"));
				d.setDrinkPrice_Super(rs.getFloat("DrinkPrice_Super"));
				d.setDrinkPrice_Big(rs.getFloat("DrinkPrice_Big"));
				d.setDrinkPrice_Medium(rs.getFloat("DrinkPrice_Medium"));
				d.setDrinkType(rs.getString("DrinkType"));
				d.setDrinkDesc(rs.getString("DrinkDesc"));
				d.setPicAddres(rs.getString("PicAddres"));
				
				// 把商品对象添加到集合中
				list.add(d);
			}
			
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size()>0 ? list.get(0) : null;
	}
	
	/**
	 * 获取本周热销商品
	 */
	@Override
	public List<Object[]> getWeekHotDrink() {
		// 实例化集合
		List<Object[]> list = new ArrayList<>();
		
		try {
			// 获取数据库的连接对象
			Connection conn = JDBCUtil.getConnectinon();
						
			// 编写sql
			String sql = "SELECT drink.DrinkID,drink.DrinkName,drink.PicAddres,"
					+ "drink.DrinkPrice_Super,drink.DrinkPrice_Medium,SUM(orderitem.Number) totalsalnum "
					+ "FROM orderitem,drinkorder,drink "
					+ "WHERE orderitem.OrderID = drinkorder.OrderID "
					+ "AND drink.DrinkID = orderitem.DrinkID AND drinkorder.PayState = 1 "
					+ "AND drinkorder.OrderTime > DATE_SUB(NOW(), INTERVAL 7 DAY) "
					+ "GROUP BY drink.DrinkID,drink.DrinkName,drink.PicAddres "
					+ "ORDER BY totalsalnum DESC "
					+ "LIMIT 0,4";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				list.add(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
			}

			// 关闭数据库
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 后台显示饮品信息
	 */
	@Override
	public List<Drink> showAllDrink() {
		// 实例化集合
		List<Drink> list = new ArrayList<Drink>();
		
		try {
			// 获取数据库的连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			// 编写sql
			String sql = "SELECT * FROM drink";
						
			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = ps.executeQuery();
			
			// 循环
			while(rs.next()){
				Drink d = new Drink();
				d.setDrinkID(rs.getInt("DrinkID"));
				d.setDrinkName(rs.getString("DrinkName"));
				d.setDrinkPrice_Super(rs.getFloat("DrinkPrice_Super"));
				d.setDrinkPrice_Big(rs.getFloat("DrinkPrice_Big"));
				d.setDrinkPrice_Medium(rs.getFloat("DrinkPrice_Medium"));
				d.setDrinkType(rs.getString("DrinkType"));
				d.setDrinkDesc(rs.getString("DrinkDesc"));
				d.setPicAddres(rs.getString("PicAddres"));
				
				// 把商品对象添加到集合中
				list.add(d);
			}
			
			// 关闭
			JDBCUtil.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 后台添加新饮品
	 */
	@Override
	public boolean addDrink(Drink drink) {
		// 影响行数的变量
		int count = 0;
				
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
								
			// 编写sql
			String sql = "INSERT INTO drink(DrinkName,DrinkPrice_Super,DrinkPrice_Big,DrinkPrice_Medium,"
					+ "DrinkType,DrinkDesc,PicAddres) VALUES (?,?,?,?,?,?,?);";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setString(1, drink.getDrinkName());
			ps.setFloat(2, drink.getDrinkPrice_Super());
			ps.setFloat(3, drink.getDrinkPrice_Big());
			ps.setFloat(4, drink.getDrinkPrice_Medium());
			ps.setString(5, drink.getDrinkType());
			ps.setString(6, drink.getDrinkDesc());
			ps.setString(7, drink.getPicAddres());
						
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
	 * 后台修改饮品信息
	 */
	@Override
	public boolean changeDrink(Drink drink) {
		// 影响行数的变量
		int count = 0;
						
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
								
			// 编写sql
			String sql = "UPDATE drink SET DrinkName = ?, DrinkPrice_Super = ?, DrinkPrice_Big = ?, "
					+ "DrinkPrice_Medium = ?, DrinkType = ?, DrinkDesc = ?, PicAddres = ? WHERE DrinkID = ?";

			// 编译sql
			PreparedStatement ps = conn.prepareStatement(sql);

			// 设置参数
			ps.setString(1, drink.getDrinkName());
			ps.setFloat(2, drink.getDrinkPrice_Super());
			ps.setFloat(3, drink.getDrinkPrice_Big());
			ps.setFloat(4, drink.getDrinkPrice_Medium());
			ps.setString(5, drink.getDrinkType());
			ps.setString(6, drink.getDrinkDesc());
			ps.setString(7, drink.getPicAddres());
			ps.setInt(8, drink.getDrinkID());
								
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
	 * 后台删除饮品
	 */
	@Override
	public boolean deleteDrink(int DrinkID) {
		// 影响行数的变量
		int count = 0;
		
		try {
			// 获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
								
			// 编写sql
			String sql = "DELETE FROM drink WHERE DrinkID = " + DrinkID;

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
}
