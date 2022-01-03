package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.ProductBiz;
import com.web.biz.impl.ProductBizImpl;

@WebServlet(urlPatterns="/deleteDrink")
public class DeleteDrinkController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		ProductBiz productBiz = new ProductBizImpl();
				
		// 获取从前台页面传过来的参数
		String DrinkID_str = req.getParameter("DrinkID");
		int DrinkID = Integer.valueOf(DrinkID_str).intValue();
		
		// 调用业务逻辑层的方法
		boolean flag = productBiz.deleteDrink(DrinkID);
				
		if (flag) {
			resp.getWriter().print("<script>alert('删除成功!');window.location.href='"
					+ req.getContextPath() + "/showAllDrink';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('删除失败!');window.location.href='"
					+ req.getContextPath() + "/showAllDrink';</script>");
		}
	}
}
