package com.web.biz;

import java.util.List;

import com.web.entity.Drink;
import com.web.util.PageBean;

public interface ProductBiz {
	/**
	 * 根据类别分页查询数据信息
	 * @param currentPage  当前页码
	 * @param currentCount  每页显示的条数
	 * @param category   类别
	 * @return
	 */
	public PageBean findDrinkByPage(int currentPage,int currentCount,String category);
	
	/**
	 * 根据名称模糊查询
	 * @param currentPage
	 * @param currentCount
	 * @param searchfield
	 * @return
	 */
	public PageBean findDrinkByName(int currentPage,int currentCount,String searchfield,String category);
	
	/**
	 * 根据id查询商品详情
	 * @param id
	 * @return
	 */
	public Drink findDrinkById(int id);
	

	/**
	 * 获取本周热销商品
	 * @return
	 */
	public List<Object[]> getWeekHotDrink();
	
	/**
	 * 后台显示饮品信息
	 * @return
	 */
	public List<Drink> showAllDrink();
	
	/**
	 * 后台添加新饮品
	 * @param drink
	 * @return
	 */
	public boolean addDrink(Drink drink);
	
	/**
	 * 后台修改饮品信息
	 * @param drink
	 * @return
	 */
	public boolean changeDrink(Drink drink);
	
	/**
	 * 后台删除饮品
	 * @param DrinkID
	 * @return
	 */
	public boolean deleteDrink(int DrinkID);
}
