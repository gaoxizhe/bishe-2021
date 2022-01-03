package com.web.biz.impl;

import java.util.List;

import com.web.biz.ProductBiz;
import com.web.dao.ProductDao;
import com.web.dao.impl.ProductDaoImpl;
import com.web.entity.Drink;
import com.web.util.PageBean;

public class ProductBizImpl implements ProductBiz {
	// 实例化数据访问层
	ProductDao productDao = new ProductDaoImpl();
	
	/**
	 * 根据类别分页查询商品数据信息
	 */
	@Override
	public PageBean findDrinkByPage(int currentPage, int currentCount, String category) {
		
		//获取分页查询的总条数
		Integer totalCount = productDao.findAllCount(category);
		
		//获取分页查询的数据信息
		List<Drink> list = productDao.findByPage(currentPage, currentCount, category);
		
		PageBean bean = new PageBean();
		bean.setCategory(category);
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);
		bean.setProductList(list);
		bean.setSearchfield(null);
		bean.setTotalCount(totalCount);
		
		return bean;
	}
	
	/**
	 * 根据名称模糊查询
	 */
	@Override
	public PageBean findDrinkByName(int currentPage, int currentCount, String searchfield,String category) {
		PageBean bean = new PageBean();

		// 设置每页显示的条数
		bean.setCurrentCount(currentCount);
				
		// 设置当前页码
		bean.setCurrentPage(currentPage);
				
		// 设置数据信息
		List<Drink> list = productDao.findDrinkByName(currentPage, currentCount, searchfield, category);
		bean.setProductList(list);
				
		// 设置搜索的字段
		bean.setSearchfield(searchfield);
				
		// 设置总条数
		Integer totalCount = productDao.findDrinkByNameAllCount(searchfield, category);
		bean.setTotalCount(totalCount);
				
		return bean;
	}

	/**
	 * 根据id查询商品详情
	 */
	@Override
	public Drink findDrinkById(int id) {
		return productDao.findDrinkById(id);
	}

	/**
	 * 获取本周热销商品
	 */
	@Override
	public List<Object[]> getWeekHotDrink() {
		return productDao.getWeekHotDrink();
	}

	/**
	 * 后台显示饮品信息
	 */
	@Override
	public List<Drink> showAllDrink() {
		return productDao.showAllDrink();
	}

	/**
	 * 后台添加新饮品
	 */
	@Override
	public boolean addDrink(Drink drink) {
		return productDao.addDrink(drink);
	}

	/**
	 * 后台修改饮品信息
	 */
	@Override
	public boolean changeDrink(Drink drink) {
		return productDao.changeDrink(drink);
	}

	/**
	 * 后台删除饮品
	 */
	@Override
	public boolean deleteDrink(int DrinkID) {
		return productDao.deleteDrink(DrinkID);
	}
}
