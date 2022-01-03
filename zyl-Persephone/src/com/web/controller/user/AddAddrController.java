package com.web.controller.user;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.biz.AddressBiz;
import com.web.biz.impl.AddressBizImpl;
import com.web.entity.User;
import com.web.entity.UserAddr;



/**
 * 添加地址的控制层
 * @author ASUS-PC
 *
 */
@WebServlet(urlPatterns="/addAddress")
public class AddAddrController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//调用业务逻辑层的注册方法
		
		//实例化业务逻辑层
		AddressBiz addressBiz = new AddressBizImpl();
		
		
		
		//实例化用户对象
		UserAddr userAddr = new UserAddr();
		User user = (User)req.getSession().getAttribute("user");
		int UserID = user.getUserID();

		String City = req.getParameter("City");
		String County = req.getParameter("County");
		String Street = req.getParameter("Street");
		String HouseNum = req.getParameter("HouseNum");
				
		
		userAddr.setUserID(UserID);
		userAddr.setCity(City);
		userAddr.setCounty(County);
		userAddr.setStreet(Street);
		userAddr.setHouseNum(HouseNum);
	

		
/*	    try {
	    	//把表单中的参数赋值给用户对象
			BeanUtils.populate(userAddr, req.getParameterMap());
			
			//调用业务逻辑层的添加地址的方法
			boolean flag = addressBiz.addAddress(userAddr);
			
			if(flag){//如果添加地址成功
				
				//req.getContextPath():获取绝对路径
				resp.sendRedirect(req.getContextPath()+"/client/addAddrSuccess.jsp");
			}else{
				//req.getContextPath():获取绝对路径
				resp.sendRedirect(req.getContextPath()+"/client/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	    
	    boolean flag = addressBiz.addAddress(userAddr);

		// 转发跳转页面
	    resp.sendRedirect(req.getContextPath()+"/showMe");
	
	
	}
}
