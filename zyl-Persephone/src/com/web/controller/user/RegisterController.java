package com.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.web.biz.UserBiz;
import com.web.biz.impl.UserBizImpl;
import com.web.entity.User;

/**
 * 注册的控制层
 * @author 王佳祺
 *
 */
@WebServlet(urlPatterns="/register")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String UserCheck = req.getParameter("UserCheck");
		String check = req.getSession().getAttribute("checkcode_session").toString();
		
		if (!UserCheck.equals(check)) {
			// 弹出警告框，点击后再跳转回注册页面
			resp.getWriter().print("<script>alert('验证码错误!');window.location.href='"
					+ req.getContextPath() + "/client/register.jsp';</script>");
		}
		else {
			// 调用业务逻辑层的注册方法
			
			// 实例化业务逻辑层
			UserBiz userBiz = new UserBizImpl();
			
			// 实例化用户对象
			User user = new User();
			
		    try {
		    	// 把表单中的参数赋值给用户对象
//		    	BeanUtils.populate(user, req.getParameterMap());
		    	String UserName = req.getParameter("UserName");
		    	String UserPwd = req.getParameter("UserPwd");
		    	String UserPhone = req.getParameter("UserPhone");
		    	user.setUserName(UserName);
		    	user.setUserPwd(UserPwd);
		    	user.setUserPhone(UserPhone);
				
				// 调用业务逻辑层的注册方法
				boolean flag = userBiz.register(user);
				
				if (flag) {  // 如果注册成功
					resp.sendRedirect(req.getContextPath()+"/client/register_success.jsp");
//					resp.sendRedirect(req.getContextPath()+"/client/registerSuccess.jsp");
				}
				else
					resp.getWriter().print("<script>alert('注册失败!');window.location.href='"
							+ req.getContextPath() + "/client/register.jsp';</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
