package org.great.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter {

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request,  ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletrequest = (HttpServletRequest) request;
		HttpServletResponse servletresponse = (HttpServletResponse) response;
		HttpSession session = servletrequest.getSession();
		
		//绝对路径
		String path = servletrequest.getScheme() + "://" + servletrequest.getServerName() + ":" + request.getServerPort()
		+ servletrequest.getContextPath() + "/";
		// 获得用户请求的URI
		 String currentURL = servletrequest.getRequestURI();//截取当前文件名用于比较
		
		// 从session里取登录信息，
		 Object user =session.getAttribute("User");
		 //文本路径，这里应该是到/Web-inf
		 String contextPath = servletrequest.getContextPath();

		 String path1=contextPath+"/admin/toIndex.action";
		 String pathMain=contextPath+"/admin/toMain.action";
		 String toLogin=contextPath+"/admin/toLogin.action";//登录路径
		 String toReturnLogin=contextPath+"/admin/toReturnLogin.action";
		 String toCode=contextPath+"/admin/codeTest.action";//验证码路径

		System.out.println(111+currentURL+222+toLogin);
	
		 //如果登录路径等于当前路径直接放过
		 if(toLogin.equals(currentURL)) {
			 chain.doFilter(request, response);
	        	return;
		 }
		 //如果是登录界面，也是放行
		 if(path1.equals(currentURL)) {
			 chain.doFilter(request, response);
	        	return;
		 }
		 
		 if(toCode.equals(currentURL)) {
			 chain.doFilter(request, response);
	        	return;
		 }
		 //如果登录成功等于当前路径直接放过
//		 if(pathMain.equals(currentURL)) {
//			 chain.doFilter(request, response);
//	        	return;
//		 }
		 
		 if(toReturnLogin.equals(currentURL)) {
			 chain.doFilter(request, response);
	        	return;
		 }
		 
		 
		 //如果登录超时后，返回登录界面
		 if(null==user) {
			 servletresponse.sendRedirect(path+"admin/toReturnLogin.action");
	        return;
		 }else{
	        	chain.doFilter(request, response);
	        	return;
	        }
	}

	
	
}
