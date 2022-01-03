package com.web.dao;

import java.util.List;

import com.web.entity.CartItem;
import com.web.entity.User;
import com.web.entity.UserAddr;

/**
 * 用户地址数据访问层接口
 * @author ASUS-PC
 *
 */

public interface AddressDao {
	/**
	 * 添加地址的方法
	 * @param userAddr
	 * @return
	 */
	public boolean addAddress(UserAddr userAddr );
	
	/**
	 * 显示地址
	 * @param UserID
	 * @return
	 */
	public List<UserAddr> showAddress(int UserID);
	
	/**
	 * 删除地址
	 * @param AddrID
	 * @return
	 */
	public boolean delAddress(int AddrID);
	
	/**
	 * 显示所有地址
	 * @return
	 */
	public List<UserAddr> showAllAddress();
}
