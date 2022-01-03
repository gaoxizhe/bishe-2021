package com.web.biz.impl;

import java.util.List;

import com.web.biz.NoticeBiz;
import com.web.dao.NoticeDao;
import com.web.dao.impl.NoticeDaoImpl;
import com.web.entity.Notice;

/**
 * 公告的业务逻辑层实现类
 * @author ASUS
 *
 */
public class NoticeBizImpl implements NoticeBiz {
	
	//实例化数据访问层
	NoticeDao noticeDao = new NoticeDaoImpl();

	/**
	 * 前台系统，查询最新添加或修改的一条公告信息
	 * @return
	 */
	@Override
	public List<Notice> getRecentNotice() {
		
		return noticeDao.getRecentNotice();
	}

}
