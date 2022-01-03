package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.biz.ProductBiz;
import com.web.biz.impl.ProductBizImpl;
import com.web.entity.Drink;

/**
 * 根据id查询商品详情控制层
 * @author 王佳祺
 *
 */
@WebServlet(urlPatterns="/findProductById")
public class FindProductByIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		ProductBiz productBiz = new ProductBizImpl();
		
		// 获取从前台页面传过来的参数
		String id_str = req.getParameter("id");
		int id = Integer.valueOf(id_str).intValue();
		
		// 调用业务逻辑层根据id查询商品详情信息
		Drink drink = productBiz.findDrinkById(id);
		
		// 把数据传递到前台页面
//		req.setAttribute("drinkByID", drink);
		Gson gson = new Gson();
		String json = gson.toJson(drink);
		resp.getWriter().append(json);
		
//		req.getRequestDispatcher("/client/info.jsp").forward(req, resp);
	}
}
