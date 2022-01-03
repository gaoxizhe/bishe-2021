package com.web.dao;

import java.util.List;

import com.web.entity.Notice;

/**
 * 公告的数据访问层接口
 * @author ASUS
 *
 */
public interface NoticeDao {
	
	/**
	 * 前台系统，查询最新添加或修改的一条公告信息
	 * @return
	 */
	public List<Notice> getRecentNotice();
	
}
