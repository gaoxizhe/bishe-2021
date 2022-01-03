package com.web.biz.impl;

import java.util.List;

import com.web.biz.AdminBiz;
import com.web.dao.AdminDao;
import com.web.dao.impl.AdminDaoImpl;
import com.web.entity.Admin;

public class AdminBizImpl implements AdminBiz {
	
	// 实例化数据访问层
	AdminDao adminDao = new AdminDaoImpl();

	/**
	 * 管理员登陆
	 */
	@Override
	public Admin login(String AdminName, String AdminPwd) {
		return adminDao.login(AdminName, AdminPwd);
	}
	
	/**
	 * 后台显示管理员信息
	 */
	@Override
	public List<Admin> showAdmin(){
		return adminDao.showAdmin();
	}
	
	/**
	 * 后台删除管理员
	 */
	@Override
	public boolean deleteAdmin(int AdminID) {
		return adminDao.deleteAdmin(AdminID);
	}
	
	/**
	 * 后台添加管理员
	 */
	@Override
	public boolean addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}
	
	/**
	 * 后台更改管理员信息
	 */
	@Override
	public boolean changeAdmin(Admin admin) {
		return adminDao.changeAdmin(admin);
	}
}
