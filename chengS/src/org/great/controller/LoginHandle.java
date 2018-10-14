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
	@ResponseBody
	public String toIndex(HttpServletRequest req, HttpServletResponse resp, AdminBean user, String loginy)
			throws IOException {

		// 验证码
		HttpSession session = req.getSession();

		// md5加密
		EncryMd5 md = new EncryMd5();
		String md5 = md.encoderByMd5(user.getPassword());
		user.setPassword(md5);
		flag = iUserBiz.userlogin(user, session);
		if (flag == "login") {// 1表示登录失败

			return "1";
		} else {

			return "2";
		}

	}

	// 进入主页面方法
	@RequestMapping(value = "/toMain.action")
	public ModelAndView toMain(HttpServletRequest req, AdminBean user) {
		HttpSession session = req.getSession();
		user = (AdminBean) session.getAttribute("User");
		int roleid = iUserBiz.selectRoleAndAdmin(user.getAdminId());
		RoleBean role = iUserBiz.selectRole(roleid);
		ulist = iUserBiz.selectAllMenu(user);
		ModelAndView mav = new ModelAndView();
		mav.addObject("role", role);
		mav.addObject("ulist", ulist);
		mav.setViewName("pharmacy/index");
		return mav;
	}

	//修改密码
		@Log(operationType = "", operationName = "修改密码")
			@RequestMapping(value="forgetPassword.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
			@ResponseBody
			public String changePassword(HttpServletRequest req,AdminBean admin,String newPassword) {
				EncryMd5 md =new EncryMd5();
				String md3 = md.encoderByMd5(newPassword);
				String md5=md.encoderByMd5(admin.getPassword());
				admin.setPassword(md5);
				flag = iUserBiz.userChangePsword(admin,md3);
			
				if(flag=="1") {//1表示登录失败
				
					return "1";
				}else {
					
					return "2";
				}
			
			}

	// 验证码
	@RequestMapping(value = "codeTest.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String codeTest(HttpServletRequest req, String codename) {
		System.out.println("判断验证码" + codename);
		HttpSession session = req.getSession();
		if (codename.equalsIgnoreCase(session.getAttribute("keyCode").toString())) {
			flag = "1";
		} else {
			flag = "2";

		}

		return flag;
	}

	// 跳去重新登录界面
	@RequestMapping(value = "/toReturnLogin.action")
	public ModelAndView returnLogin(HttpServletRequest req) {
		return new ModelAndView("sessionTimeout");
	}
}
