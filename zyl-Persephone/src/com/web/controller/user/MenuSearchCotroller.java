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
 * 搜索功能控制层
 * @author 王佳祺
 *
 */
@WebServlet(urlPatterns="/search")
public class MenuSearchCotroller extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		ProductBiz productBiz = new ProductBizImpl();
		
		// 定义当前页码,默认为1
		Integer currentPage = 1;
		String _currentPage = req.getParameter("currentPage");
		
		//判断当前页码是否为空
		if (_currentPage != null) {
			// 将字符串转换为整形
			currentPage = Integer.parseInt(_currentPage);
		}
		
		// 定义每页的条数，默认为8
		Integer currentCount = 8;
		String _currentCount = req.getParameter("currentCount");
		
		if(_currentCount != null){
			currentCount = Integer.parseInt(_currentCount);
		}
		
		// 获取前台jsp页面传过来的搜索字段信息
		String searchfield = req.getParameter("searchfield");
		String DrinkType = req.getParameter("DrinkType");
		
		if ("".equals(searchfield)) {
			// 转发到显示商品信息页面的后台控制层
			req.getRequestDispatcher("/client/search.jsp").forward(req, resp);
		}
		else {
			// 调用业务逻辑层根据搜索字段分页查询数据方法
			PageBean bean = productBiz.findDrinkByName(currentPage, currentCount, searchfield, DrinkType);
			
			// 把数据传到前台jsp页面
			req.setAttribute("bean", bean);
			
			// 查询本周的热销商品
			List<Object[]> drinkList = productBiz.getWeekHotDrink();
			req.setAttribute("drinkList", drinkList);
			
			// 转发跳转页面
			req.getRequestDispatcher("/client/shop.jsp").forward(req, resp);
		}
	}
}
