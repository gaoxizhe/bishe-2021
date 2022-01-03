package com.web.dao;

import java.util.List;

import com.web.entity.Drink;

public interface ProductDao {
	/**
	 * 查询商品的总条数
	 * @param DrinkType
	 * @return
	 */
	public Integer findAllCount(String DrinkType);
	
	/**
	 * 根据类别分页查询数据
	 * @param currentPage  当前页码
 	 * @param currentCount   每页显示的条数
	 * @param DrinkType  类别
	 * @return
	 */
	public List<Drink> findByPage(Integer currentPage,int currentCount,String DrinkType);
	
	/**
	 * 根据字段搜索总条数
	 * @param searchfield
	 * @return
	 */
	public Integer findDrinkByNameAllCount(String searchfield,String DrinkType);
	
	/**
	 * 根据字段搜索数据
	 * @param currentPage  当前页
	 * @param currentCount  每页的条数
	 * @param searchfield  搜索的字段
	 * @return
	 */
	public List<Drink> findDrinkByName(int currentPage,int currentCount,String searchfield,String DrinkType);
	
	/**
	 * 根据id查询商品的详情信息
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
