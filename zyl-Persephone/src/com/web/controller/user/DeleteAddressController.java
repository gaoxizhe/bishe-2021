package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AddressBiz;
import com.web.biz.impl.AddressBizImpl;

@WebServlet(urlPatterns="/delAddress")
public class DeleteAddressController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AddressBiz addressBiz = new AddressBizImpl();

		int AddrID=Integer.parseInt(req.getParameter("id"));

		addressBiz.delAddress(AddrID);

//		resp.sendRedirect(req.getContextPath()+"/showMe");
		
	}
}
