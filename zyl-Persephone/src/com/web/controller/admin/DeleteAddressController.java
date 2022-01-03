package com.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AddressBiz;
import com.web.biz.impl.AddressBizImpl;

@WebServlet(urlPatterns="/deleteAddress")
public class DeleteAddressController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实例化业务逻辑层
		AddressBiz addressBiz = new AddressBizImpl();
				
		// 获取从前台页面传过来的参数
		String AddrID_str = req.getParameter("AddrID");
		int AddrID = Integer.valueOf(AddrID_str).intValue();
		
		// 调用业务逻辑层的更改方法
		boolean flag = addressBiz.delAddress(AddrID);
				
		if (flag) {
			resp.getWriter().print("<script>alert('删除成功');window.location.href='"
					+ req.getContextPath() + "/showUserAddress';</script>");
		}
		else {
			resp.getWriter().print("<script>alert('删除失败');window.location.href='"
					+ req.getContextPath() + "/showUserAddress';</script>");
		}
	}
}
