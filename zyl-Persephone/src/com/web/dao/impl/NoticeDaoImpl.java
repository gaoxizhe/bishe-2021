package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.NoticeDao;
import com.web.entity.Notice;
import com.web.util.JDBCUtil;

/**
 * 公告的数据访问层实现类
 * @author ASUS
 *
 */
public class NoticeDaoImpl implements NoticeDao {
	/**
	 * 前台系统，查询最新添加或修改的一条公告信息
	 * @return
	 */
	@Override
	public List<Notice> getRecentNotice() {
		
		//实例化集合对象
		List<Notice> list = new ArrayList<Notice>();
		
		try {
			//获取数据库连接对象
			Connection conn = JDBCUtil.getConnectinon();
			
			//编写sql
			String sql = "select * from notice order by n_time desc limit 0,5";
			
			//执行编译
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//执行查询
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				//实例化集合对象
				Notice n = new Notice();
				
				//为对象复制属性的值
				n.setDetails(rs.getString("details"));
				n.setN_id(rs.getInt("n_id"));
				n.setTitle(rs.getString("title"));
				n.setN_time(rs.getString("n_time"));
				
				//把对象添加到集合中
				list.add(n);
			}
			
			//关闭
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
