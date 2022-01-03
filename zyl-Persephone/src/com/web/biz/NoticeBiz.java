package com.web.biz;

import java.util.List;

import com.web.entity.Notice;

/**
 * 公告的业务逻辑层接口
 * @author ASUS
 *
 */
public interface NoticeBiz {

	/**
	 * 前台系统，查询最新添加或修改的一条公告信息
	 * @return
	 */
	public List<Notice> getRecentNotice();
}
