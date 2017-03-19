package cn.fayostyle.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.commons.beanutils.BeanUtils;

import cn.fayostyle.entity.Admin;
import cn.fayostyle.exception.UserExistsException;
import cn.fayostyle.service.IAdminService;
import cn.fayostyle.service.impl.AdminService;
import cn.fayostyle.utils.WebUtils;

/**
 * 控制层设计
 * @author fayosytle
 * @time 2017年2月7日下午 5:34:59
 */
public class AdminServlet extends HttpServlet {

	private IAdminService adminService = new AdminService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		//1.获取请求参数
		String method = request.getParameter("method");
		if("register".equals(method)) {
			register(request,response);
		}
	}
	
	/**
	 * 注册处理方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//获取请求参数
//		String userName = request.getParameter("userName");
//		String pwdString = request.getParameter("pwd");
		
		//封装
		Admin admin = WebUtils.copyToBean(request, Admin.class);

		
		
//		admin.setUserName(userName);
//		admin.setPwd(pwdString);
		
		//调用service处理注册的业务逻辑
		try {
			adminService.register(admin);
//			//注册成功，跳转到首页
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (UserExistsException e) {
			//用户名存在 注册失败 跳转到注册页面 
			request.setAttribute("message", "用户名已经存在！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch(Exception e) {
//		e.printStackTrace(); //测试的时候打印出异常信息
			//其他异常 跳转到错误页面
			response.sendRedirect(request.getContextPath() + "/error/error.jsp");
		} 
	}



}
