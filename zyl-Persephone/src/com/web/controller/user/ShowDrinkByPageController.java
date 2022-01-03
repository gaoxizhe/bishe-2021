package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.ProductBiz;
import com.web.biz.impl.ProductBizImpl;
import com.web.util.PageBean;

/**
 * 商品展示的控制层
 * @author 王佳祺
 *
 */
@WebServlet(urlPatterns="/showProductByPage")
public class ShowDrinkByPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 实例化商品的业务逻辑层
		ProductBiz DrinkBiz = new ProductBizImpl();
		
		// 定义当前页码，默认为1
		Integer currentPage = 1;
		String _currentPage = req.getParameter("currentPage");
		
		// 判断传过来的页码数是否为空
		if(_currentPage != null){
			// 把字符串转换为整形
			currentPage = Integer.parseInt(_currentPage);
		}
		
		// 定义每页显示的条数默认为8
		Integer currentCount = 8;
		String _currentCount = req.getParameter("currentCount");
		
		// 判断传过来的每页显示的条数是否为空
		if(_currentCount != null){
			// 把字符串转换为整形
			currentCount = Integer.parseInt(_currentCount);
		}
		
		// 定义查找的分类默认为全部商品
		String DrinkType = "All";
		String _DrinkType = req.getParameter("DrinkType");
		
		// 判断是否根据分类查询数据
		if(_DrinkType != null){
			DrinkType = _DrinkType;
		}
		
		// 调用业务逻辑层的分类查询方法
		PageBean bean = DrinkBiz.findDrinkByPage(currentPage, currentCount, DrinkType);
	
	    // 把数据传递给jsp页面
		req.setAttribute("bean", bean);
		
		// 查询本周的热销商品
		List<Object[]> drinkList = DrinkBiz.getWeekHotDrink();
		req.setAttribute("drinkList", drinkList);
		
		// 转发跳转页面
		req.getRequestDispatcher("/client/shop.jsp").forward(req, resp);
		
	}
	
	
	
	
	
	
	
	
	
	

}
