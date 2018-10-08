package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.biz.IUserBiz;
import org.great.tools.EncryMd5;
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
		session.removeValue("User");
		return new ModelAndView("login");
	}
	
	// 进入index页面
		@RequestMapping(value = "/toIndex.action")
		public ModelAndView toIndex(HttpServletRequest req, AdminBean user,String loginy) {
			
			System.out.println("获得验证码"+loginy);
			//验证码
			HttpSession session = req.getSession();
			System.out.println("图片验证码"+session.getAttribute("keyCode"));
			
			if(loginy.equalsIgnoreCase(session.getAttribute("keyCode").toString())) {
				System.out.println("验证码正确");
				//md5加密
				EncryMd5 md =new EncryMd5();
				String md5=md.encoderByMd5(user.getPassword());
				user.setPassword(md5);
				flag = iUserBiz.userlogin(user, session);
				ulist=iUserBiz.selectAllMenu(user);
				
			}else if(loginy.equalsIgnoreCase("")||loginy==null||loginy!=session.getAttribute("keyCode").toString()) {
				System.out.println("验证码不正确");
				flag="login";
				
			}
			
			// 这里进入biz层
				
				ModelAndView mav = new ModelAndView(flag);
				mav.addObject("ulist", ulist);
			return mav;
		}
		
		//修改密码
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
				flag="验证码正确";
			}else {
				flag="验证码错误";
			}
			
			return flag;
		}
}
