package com.web.dao;

import java.util.List;

import com.web.entity.User;

/**
 * 用户的数据访问层接口
 * @author 王佳祺
 *
 */
public interface UserDao {
	/**
	 * 注册，或后台添加新用户
	 * @param user
	 * @return
	 */
	public boolean add(User user);
	
	/**
	 * 登陆
	 * @param UserName
	 * @param UserPwd
	 * @return
	 */
	public User login(String UserName, String UserPwd);
	
	/**
	 * 修改名称
	 * @param UserID
	 * @param user
	 * @return
	 */
	public boolean changeName(int UserID, User user);
	
	/**
	 * 修改密码
	 * @param UserID
	 * @param user
	 * @return
	 */
	public boolean changePwd(int UserID, User user);
	
	/**
	 * 修改电话
	 * @param UserID
	 * @param user
	 * @return
	 */
	public boolean changePhone(int UserID, User user);
	
	/**
	 * 检查名字是否重复
	 * @param UserName
	 * @return
	 */
	public boolean checkName(String UserName);
	
	/**
	 * 后台显示用户信息
	 * @return
	 */
	public List<User> showUser();
	
	/**
	 * 后台更改用户信息
	 * @param user
	 * @return
	 */
	public boolean changeUser(User user);
	
	/**
	 * 后台删除用户
	 * @param UserID
	 * @return
	 */
	public boolean deleteUser(int UserID);
	
	/**
	 * 后台显示用户总数
	 * @return
	 */
	public int showUserCount();
}
