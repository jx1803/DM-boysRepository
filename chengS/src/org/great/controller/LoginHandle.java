package org.great.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.bean.RoleBean;
import org.great.biz.IUserBiz;
import org.great.tools.EncryMd5;
import org.great.tools.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin")
public class LoginHandle {
	private List ulist;
	private String flag;
	@Resource
	private IUserBiz iUserBiz;
	@RequestMapping("/toLogin.action")
	public ModelAndView toLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("User");
	
		return new ModelAndView("login");
	}
	
	// 进入index页面
	@Log(operationType = "登录", operationName = "登录")
		@RequestMapping(value = "/toIndex.action")
		public String toIndex(HttpServletRequest req, HttpServletResponse resp, AdminBean user,String loginy)throws IOException  {
			PrintWriter out = resp.getWriter();
		
			//验证码
			HttpSession session = req.getSession();
			System.out.println("图片验证码"+session.getAttribute("keyCode"));
			
		//	if(loginy.equalsIgnoreCase(session.getAttribute("keyCode").toString())) {
				System.out.println("验证码正确");
				//md5加密
				EncryMd5 md =new EncryMd5();
				String md5=md.encoderByMd5(user.getPassword());
				user.setPassword(md5);
				flag = iUserBiz.userlogin(user, session);
				if(flag=="login") {
					
					/*System.out.println("账户密码有误");
					out.print("<script>alert('账户密码有误')</script>");
					out.flush();	ModelAndView mav = new ModelAndView(flag);
					out.close();*/
					flag="login";
					session.setAttribute("error","false");
					return "redirect:toLogin.action";
				}else{
					
					session.removeAttribute("error");
						
//					req.setAttribute("ulist", ulist);
					return "redirect:toMain.action";
					//return "forward:pharmacy/index.action";
					
				}
				
				
				
			//}
			// 这里进入biz层
				
				
			
		}
	//进入主页面方法
	@RequestMapping(value="/toMain.action")
	public ModelAndView toMain(HttpServletRequest req,AdminBean user) {
		HttpSession session = req.getSession();
		user=(AdminBean) session.getAttribute("User");
		int roleid = iUserBiz.selectRoleAndAdmin(user.getAdminId());
		RoleBean role = iUserBiz.selectRole(roleid);
		ulist=iUserBiz.selectAllMenu(user);
		ModelAndView mav = new ModelAndView();
		mav.addObject("role",role);
		mav.addObject("ulist", ulist);
		mav.setViewName("pharmacy/index");
		return mav;
	}
		
		//修改密码
	@Log(operationType = "", operationName = "修改密码")
		@RequestMapping(value="/forgetPassword.action")
		public ModelAndView changePassword(HttpServletRequest req,AdminBean admin,String newpassword) {
			EncryMd5 md =new EncryMd5();
			String md3 = md.encoderByMd5(newpassword);
			String md5=md.encoderByMd5(admin.getPassword());
			admin.setPassword(md5);
			flag = iUserBiz.userChangePsword(admin,md3);
			ModelAndView mav = new ModelAndView(flag);
			return mav;
		}
		//验证码
		@RequestMapping(value="codeTest.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
		@ResponseBody
		public String codeTest(HttpServletRequest req,String codename) {
			System.out.println("判断验证码"+codename);
			HttpSession session = req.getSession();
			if(codename.equalsIgnoreCase(session.getAttribute("keyCode").toString())) {
				flag="1";
			}else {
				flag="2";
	
			}
			
			return flag;
		}
		
		//跳去重新登录界面
		@RequestMapping(value="/toReturnLogin.action")
		public ModelAndView returnLogin(HttpServletRequest req) {
			return new ModelAndView("sessionTimeout");
		}
}
