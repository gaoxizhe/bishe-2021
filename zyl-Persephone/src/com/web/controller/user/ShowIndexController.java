package com.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.NoticeBiz;
import com.web.biz.ProductBiz;
import com.web.biz.impl.NoticeBizImpl;
import com.web.biz.impl.ProductBizImpl;
import com.web.entity.Notice;

/**
 * 显示首页的控制层
 * @author ASUS
 *
 */
@WebServlet(urlPatterns="/ShowIndex")
public class ShowIndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 查询公告版的信息
		// 实例化公告版的业务逻辑层
		NoticeBiz noticeBiz = new NoticeBizImpl();
				
		// 调用查询最新公告信息的方法
		List<Notice> noticeList = noticeBiz.getRecentNotice();
				
		// 把公告信息传递到前台页面
		req.setAttribute("noticeList", noticeList);
				
		// 查询本周热销的商品信息
		ProductBiz productBiz = new ProductBizImpl();
				
		// 查询本周的热销商品
		List<Object[]> drinkList = productBiz.getWeekHotDrink();
				
		// 把本周热销的商品信息传递到前台
		req.setAttribute("drinkList", drinkList);
				
		// 转发跳转页面
		req.getRequestDispatcher("/client/index.jsp").include(req, resp);
		
	}	
}
