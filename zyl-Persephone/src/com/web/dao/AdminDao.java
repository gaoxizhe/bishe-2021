package com.web.dao;

import java.util.List;

import com.web.entity.Admin;

public interface AdminDao {
	/**
	 * 管理员登陆
	 * @param AdminName
	 * @param AdminPwd
	 * @return
	 */
	public Admin login(String AdminName, String AdminPwd);

	/**
	 * 后台显示管理员信息
	 * @return
	 */
	public List<Admin> showAdmin();
	
	/**
	 * 后台删除管理员
	 * @param AdminID
	 * @return
	 */
	public boolean deleteAdmin(int AdminID);
	
	/**
	 * 后台添加管理员
	 * @param admin
	 * @return
	 */
	public boolean addAdmin(Admin admin);
	
	/**
	 * 后台更改管理员信息
	 * @param admin
	 * @return
	 */
	public boolean changeAdmin(Admin admin);
}
