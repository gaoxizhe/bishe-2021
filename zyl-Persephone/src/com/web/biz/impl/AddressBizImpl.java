package com.web.biz.impl;

import java.util.List;

import com.web.biz.AddressBiz;
import com.web.dao.AddressDao;
import com.web.dao.impl.AddressDaoImpl;
import com.web.entity.UserAddr;

/**
 * 地址的业务逻辑层实现类
 * @author ASUS
 *
 */
public class AddressBizImpl implements AddressBiz {
	
	//实例化数据访问层
	AddressDao ad = new AddressDaoImpl();

	/**
	 * 添加地址
	 */
	@Override
	public boolean addAddress(UserAddr useraddr) {
		
		//调用数据访问层的添加地址方法
		return ad.addAddress(useraddr);
		
	}
	
	/**
	 * 显示地址
	 */
	@Override
	public List<UserAddr> showAddress(int UserID) {
		return ad.showAddress(UserID);
	}

	/**
	 * 删除地址
	 */
	@Override
	public boolean delAddress(int AddrID) {
		return ad.delAddress(AddrID);
	}
	
	/**
	 * 显示所有地址
	 */
	@Override
	public List<UserAddr> showAllAddress(){
		return ad.showAllAddress();
	}
}
	
	
