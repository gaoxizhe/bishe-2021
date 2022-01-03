package com.web.util;

import java.util.List;

import com.web.entity.Drink;

/**
 * 分页工具类
 * @author 王佳祺
 *
 */
public class PageBean {
	private Integer currentPage;  // 当前页码
	private Integer totalCount;  // 总条数   需要从数据库中查询
	private Integer currentCount;  // 每页的条数
	private Integer totalPage;  // 总页数  = 总条数 / 每页显示的条数 
	private List<Drink> productList;  // 显示的数据数据
	
	private String category;  // 类别
	private String searchfield;  // 模糊搜索的字段
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	
	/**
	 * 获取总条数
	 * @return
	 */
	public Integer getTotalPage() {
		
		totalPage = (int)Math.ceil((totalCount * 1.0) / currentCount);
		
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Drink> getProductList() {
		return productList;
	}
	public void setProductList(List<Drink> productList) {
		this.productList = productList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSearchfield() {
		return searchfield;
	}
	public void setSearchfield(String searchfield) {
		this.searchfield = searchfield;
	}
	
	
	public static void main(String[] args) {
		System.out.println((int)Math.ceil((9*1.0)/4));
	}
}
