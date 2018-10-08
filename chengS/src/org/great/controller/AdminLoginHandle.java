package org.great.controller;

import java.io.IOException;
import java.security.acl.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.ParamBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;
import org.great.biz.IUserBiz;
import org.great.tools.EncryMd5;
import org.great.tools.Log;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.Mod;



/**
 * 
 * @ClassName: AdminLoginHandle
 * @Description: 路径到（http://localhost:8080/SPMS/user/toLogin.action）进入登录页面
 * 				用户列表显示，用户模糊查找，分页，状态改变以及删除
 * 				角色管理：添加角色，角色显示，角色删除
 * 				部门管理：部门添加，部门删除
 * 				权限管理：动态菜单，权限分配
 * @author 王伟国 传一科技总经理
 * @date 2018年9月18日 下午3:08:25
 */

@Controller
@RequestMapping("/user")
public class AdminLoginHandle {
	private List ulist;
	private List rlist;
	private List olist;
	private String flag;
	@Resource
	private IUserBiz iUserBiz;

	// 获取用户管理的显示数据,需要分页和模糊查找
	
	@RequestMapping(value = "/memberList.action")
	public ModelAndView userList(HttpServletRequest request,CondiBean cond) {
		//带入搜索条件
		ulist=iUserBiz.UserList(cond);
		
		int pagenum=iUserBiz.ResultComm(cond);
		int pageTotol=PageUtil.pageTotal(pagenum);
		//总页数带模糊查找的总页数
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist",ulist);//相当于req.sett
		mav.addObject("blurred",cond);//模糊查找的对象
		mav.addObject("pagenum",pagenum);
		mav.addObject("pageTotol",pageTotol);//返回总页数
//		mav.addObject("page",cond.getPage());
		mav.setViewName("user/memberList");
		return mav;
	}
	
	//删除用户显示
	
	@RequestMapping(value="/memberDelList.action")
	public ModelAndView memberDelList(HttpServletRequest request,CondiBean cond) {
		//带入搜索条件
		ulist=iUserBiz.userDelList(cond);
		int pageTotol=PageUtil.pageTotal(iUserBiz.userDelComm(cond));
		ModelAndView mav = new ModelAndView();
		mav.addObject("blurred",cond);//模糊查找的对象
		mav.addObject("ulist", ulist);
		mav.addObject("pageTotol", pageTotol);
		mav.addObject("pageNum", iUserBiz.userDelComm(cond));
		return mav;
	}
	// 用户删除
	@Log(operationType = "", operationName = "用户删除")
	@RequestMapping(value ="/delUser.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public ModelAndView DelUser(HttpServletRequest request,AdminBean adbean) {
		//进入biz层，更改用户状态
		iUserBiz.DelState(adbean.getAdminId());
		CondiBean cond = new CondiBean();
		return userList(request,cond);
	}
	
	//ajax，注册新用户的时候，是否账号已存在
	@RequestMapping(value="/testAccount.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String testAccount(HttpServletRequest request,AdminBean adbean,String adminAccount) {
		
		AdminBean admin = iUserBiz.reappearUser(adminAccount);
		if(null==admin) {
		flag="false";
		}else {
			flag="true";
		}
		
		return flag;
		
	}
	//ajax启用禁用状态改变
	@Log(operationType = "", operationName = "启用禁用")
	@RequestMapping(value ="/ChangeStateUser.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public AdminBean stateUser(AdminBean adbean) {
		iUserBiz.changeState(adbean);
		return adbean;
	}
	
	//密码一键重置，用ajax
	@Log(operationType = "", operationName = "密码重置")
	@RequestMapping(value="/ResetPassword.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public AdminBean resetpassword(AdminBean adbean) {
		String str = "123456";
		EncryMd5 md=new EncryMd5();
		String md5=md.encoderByMd5(str);
		adbean.setPassword(md5);
		iUserBiz.updateResetPsw(adbean);
		return adbean;
	}
	
	//用户增加
	
	@RequestMapping(value ="/memberListAdd.action")
	public ModelAndView member_Add(HttpServletRequest request,CondiBean cond) {
		//取出所有的部门信息返回给jsp页面啊
		//进入biz层，找出角色职位和部门
		rlist=iUserBiz.memberRole();
		ulist=iUserBiz.memberDept(cond);
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist",rlist);
		mav.addObject("dlist", ulist);
		mav.setViewName("user/memberListAdd");
		return mav;
	}
	@Log(operationType = "", operationName = "增加新用户")
	//用户增加插入到数据库
	@RequestMapping(value="/memberAddSave.action")
	public String member_add_save(HttpServletRequest request,AdminBean abean,
			String roleId) {
		int roleid = Integer.parseInt(roleId);
		//还得插入角色与用户关系表中
		EncryMd5 md =new EncryMd5();
		String md5=md.encoderByMd5(abean.getPassword());
		abean.setPassword(md5);
		iUserBiz.MemberAdd(abean);
		//找到新添加的用户id，然后插入到角色与用户关系表中
		CondiBean cond = new CondiBean();
		cond.setAdminName(abean.getAdminName());
		cond.setRoleid(roleid);
		iUserBiz.selectnewUser(cond);
		return "redirect:memberList.action";
	}
	
	//进入用户修改界面
	@RequestMapping(value="/memberListUpdate.action")
	public ModelAndView member_list_update(HttpServletRequest request,AdminBean abean,CondiBean cond) {
		rlist=iUserBiz.memberRole();
		//得到角色id后，
		int roleid=iUserBiz.selectRoleAndAdmin(abean.getAdminId());
		//把部门循环出来,不分页
		olist = iUserBiz.deptAll();
		AdminBean adbean=iUserBiz.selectOtherUser(abean);
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleid",roleid);
		mav.addObject("rlist",rlist);
		mav.addObject("olist",olist);
		mav.addObject("userBean", adbean);
		return mav;
	}
	//用户修改提交
	@Log(operationType = "", operationName = "修改用户信息")
	@RequestMapping(value = "/memberUpdateSave.action")
	public String memberUpdateSave(HttpServletRequest request,AdminBean abean,CondiBean cond) {
		iUserBiz.memberUpdateSave(abean,cond);
		return "redirect:memberList.action";
	}
	
	
/************角色管理****************************/	
	//角色管理、然后直接显示角色的数据在页面
	@RequestMapping(value ="/roleList.action")
	public ModelAndView roleList(CondiBean cond) {
		//进入Biz层
		ulist=iUserBiz.RoleList(cond);
		//找到角色总页数
		int pageTotal = PageUtil.pageTotal(iUserBiz.RoleComm(cond));
		ModelAndView mav = new ModelAndView();
		mav.addObject("blurred", cond);
		mav.addObject("rolelist", ulist);
		mav.addObject("pageTotal",pageTotal);//返回总页数
		mav.addObject("pageNum",iUserBiz.RoleComm(cond));
		mav.setViewName("user/roleList");
		return mav;
	}
	
	//角色增加（部门增加）
	@RequestMapping(value ="/roleListAdd.action")
	public ModelAndView role_list_add(HttpServletRequest request) {
		return new ModelAndView();
	}
	
	
	//角色添加保存,一个插入语句
	@Log(operationType = "", operationName = "角色增加")
	@RequestMapping(value ="/roleAddSave.action")
	public String role_add_save(HttpServletRequest request,RoleBean rolebean) {
		//将语句插入,进入biz层
		iUserBiz.RoleInsert(rolebean);
		if(ulist.size()>0) {
			ulist.clear();
		}
		
		return "redirect:roleList.action";
	}
	
	//进入角色信息修改页面
	@RequestMapping(value="/roleListUpdate.action")
	public ModelAndView roleListUpdate(HttpServletRequest request,int roleId) {
		System.out.println("roleid"+roleId);
		//由id取得角色名称以及角色简介
		RoleBean rbean=iUserBiz.selectRole(roleId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bean", rbean);
		return mav;
	}
	
	//角色信息修改保存
	@Log(operationType = "", operationName = "角色修改")
	@RequestMapping(value="/roleListSave.action")
	public String roleListSave(HttpServletRequest request,RoleBean rolebean) {
		//进入页面修改
		iUserBiz.updateRole(rolebean);
		return "redirect:roleList.action";
	}
	//角色删除ajax
	@Log(operationType = "", operationName = "角色删除")
	@RequestMapping(value="/roleDel.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String roleDel(HttpServletRequest request,int roleid ) {
		//删除角色表之前，还需要删除用户与角色关系表
		System.out.println("取得角色id"+roleid);
		iUserBiz.delRoleAndUser(roleid);
		iUserBiz.delRoleAndPer(roleid);
		//删除角色表
		iUserBiz.delRole(roleid);
		 return "redirect:roleList.action";
	}
	
	
/******************部门管理***************/
	@RequestMapping(value = "/deptList.action")
	public ModelAndView deptList(CondiBean cond) {
		ulist=iUserBiz.memberDept(cond);
		int pageTotal = PageUtil.pageTotal(iUserBiz.deptComm(cond));//总页数
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("pageNum", iUserBiz.deptComm(cond));
		mav.addObject("dlist", ulist);
		mav.addObject("blurred", cond);
		mav.setViewName("user/deptList");
		return mav;
	}
	//部门增加
	@Log(operationType = "", operationName = "增加部门")
	@RequestMapping(value = "/deptListAdd.action")
	public String deptListAdd(HttpServletRequest request,DeptBean dept){
		iUserBiz.inserDept(dept);
		return "redirect:deptList.action";
	}

	//部门删除
	@Log(operationType = "", operationName = "删除部门")
	@RequestMapping(value = "/deptlistDel.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody String delDept(HttpServletRequest request,int deptid) {
		iUserBiz.delDept(deptid);
		return "redirect:deptList.action";
	}
	
	//部门编辑
	@RequestMapping(value="/deptUpdate.action")
	public ModelAndView deptUpdate(HttpServletRequest request,DeptBean dept) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptbean",dept);
		return mav;
	}
	//部门编辑保存
	@Log(operationType = "", operationName = "部门信息修改")
	@RequestMapping(value="/deptSave.action")
	public String deptSave(HttpServletRequest request,DeptBean dept) {
		iUserBiz.deptSave(dept);
		return "redirect:deptList.action";
	}


	
/******************一级菜单管理*************/
	
	@RequestMapping(value="/menuLevel1List.action")
	public ModelAndView Menu_level1(HttpServletRequest request,CondiBean cond) {
		//取出所有一级菜单，进入Biz层
		ulist=iUserBiz.leveloneMenu(cond);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		//还要有分页
		int pageTotal= PageUtil.pageTotal(iUserBiz.ResultoneMenu(cond));//一级菜单总页数
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("blurred", cond);//模糊查找的值，返回页面
		return mav;
	}
	
//一级菜单进入增加页面
	@RequestMapping(value="/menuLevel1Add.action")
	public ModelAndView menu_Level1_add(HttpServletRequest request) {
		return new ModelAndView();
	}
//一级菜单提交至数据库
	@Log(operationType = "", operationName = "一级菜单增加")
	@RequestMapping(value="/menuLevel1Save.action")
	public String Menu_Level1_save(HttpServletRequest request,PermissionBean pbean) {
		iUserBiz.insertPMenu(pbean);
		return "redirect:menuLevel2List.action";
	}
	//一级菜单删除
	@Log(operationType = "", operationName = "一级菜单删除")
	@RequestMapping(value="/menuLevel1Del.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String MenuLevel1del(HttpServletRequest request,int permissionId) {
		iUserBiz.MenuOneDel(permissionId);
		return "redirect:menuLevel2List.action";
	}
	//二级菜单删除
	@Log(operationType = "", operationName = "二级菜单删除")
	@RequestMapping(value="/menuLevelTwoDel.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String menuLevelTwoDel(HttpServletRequest request,int fid) {
		iUserBiz.MenuTwoDel(fid);
		return "redirect:menuLevel2List.action";
	}
	
	//一级菜单修改
	@RequestMapping(value="/menuLevel1Update.action")
	public ModelAndView Menu_Level1_update(HttpServletRequest request,PermissionBean pbean) {
		return new ModelAndView();
	}
	
	
/*****************二级菜单管理************/
	@RequestMapping(value="/menuLevel2List.action")
	public ModelAndView Menu_level2(HttpServletRequest request,CondiBean cond) {
		ulist = iUserBiz.leveltwoMenu(cond);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		//还要有分页
		int pageTotal= PageUtil.pageTotal(iUserBiz.ResulttwoMenu(cond));//一级菜单总页数
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("blurred", cond);//模糊查找的值，返回页面
		mav.addObject("pageNum",iUserBiz.ResulttwoMenu(cond));
		return mav;
	}
	
	//进入增加二级菜单界面
	@RequestMapping(value="/menuLevel2Add.action")
	public ModelAndView Menu_Level2_add(HttpServletRequest request,CondiBean cond) {
		//把一级菜单，传给二级菜单添加页
		ulist=iUserBiz.PMenu();
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		return mav;
	}
	//将添加的二级菜单提交到数据库中
	@Log(operationType = "", operationName = "二级菜单添加")
	@RequestMapping(value="/menuLevel2Save.action")
	public String Menu_Level2_save(HttpServletRequest request,PermissionBean pbean) {
		int i = iUserBiz.insertSMenu(pbean);
		return "redirect:menuLevel2List.action";
	}

	
	//二级菜单修改
	
	@RequestMapping(value="/menuLevel2Update.action")
	public ModelAndView MenuLevel2update(HttpServletRequest request,PermissionBean pbean) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pbean",pbean);
		return mav;
	}
	//修改后的二级菜单添加后给数据库
	@Log(operationType = "", operationName = "一二级菜单修改")
	@RequestMapping(value="/updatemenuLevel.action")
	public String menuLevel2Save(HttpServletRequest request,PermissionBean pbean) {
		iUserBiz.menuSave(pbean);
		return "redirect:menuLevel2List.action";
	}
	
	
	
	
/*************权限管理*************/
	@RequestMapping(value = "/pConfiguration.action")
	public ModelAndView pConfiguration(HttpServletRequest request,String roleid) {
		//找到所有的角色返回给页面，上面是有分页
		ModelAndView mav = new ModelAndView();
		if(null!=roleid) {
			int i = Integer.parseInt(roleid);
			//调用Biz找到角色id对应的权限
			rlist=iUserBiz.roleConfiguration(i);//角色的权限
			//角色没有的权限
			olist=iUserBiz.roleConfigOther(i);//角色没有的权限
			mav.addObject("rlist", rlist);//角色的已分配权限
			mav.addObject("roleother", olist);
		}
		ulist = iUserBiz.roleAllList();
		mav.addObject("roleid",roleid);//把过来的id再返回去给页面
		mav.addObject("ulist", ulist);//角色
		return mav;
	}
	
	//各角色的权限
	@RequestMapping(value = "/Configuration.action")
	public ModelAndView Configuration(HttpServletRequest request,String roleid) {
		return new ModelAndView();
	}
	
	//角色权限变化---删除权限
	@Log(operationType = "", operationName = "删除权限")
	@RequestMapping(value="/ConfigurationDel.action")
	public String ConfigurationDel(HttpServletRequest request, 
			@RequestParam(value = "time") String test,@RequestParam(value = "roleId") int roleId) {
		//进入biz层去，删除权限
		
		
		iUserBiz.DelConfig(test,roleId);
		if(ulist.size()>0) {
			ulist.clear();
		}
		if(rlist.size()>0) {
			rlist.clear();
		}
		if(olist.size()>0) {
			olist.clear();
		}
		return "redirect:pConfiguration.action?roleid="+roleId;
	}
	
	//角色权限变化---增加权限
	@Log(operationType = "", operationName = "增加权限")
	@RequestMapping(value="/ConfigurationAdd.action")
	public String ConfigurationAdd(HttpServletRequest request,
			@RequestParam(value = "time1") String test,@RequestParam(value = "roleId") int roleId) {
		iUserBiz.AddConfig(test,roleId);
		if(ulist.size()>0) {
			ulist.clear();
		}
		if(rlist.size()>0) {
			rlist.clear();
		}
		if(olist.size()>0) {
			olist.clear();
		}
		return "redirect:pConfiguration.action?roleid="+roleId;
	}
	
/************************参数配置*******************/
	//参数配置
	@RequestMapping(value="/parameterList.action")
	public ModelAndView parameterList(HttpServletRequest request,CondiBean cond) {
		//进入
		ulist = iUserBiz.paramList(cond);
		//参数页数
		int pageTotal = PageUtil.pageTotal(iUserBiz.ParamComm(cond));
		//取得一级参数给页面
		rlist=iUserBiz.Level1Param();
		//取得当前
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("blurred", cond);//模糊查找的值，返回页面
		mav.addObject("ulist", ulist);
		mav.addObject("rlist", rlist);
		mav.addObject("pageNum", iUserBiz.ParamComm(cond));
		return mav;
	}
	
	//添加一级参数
	@Log(operationType = "", operationName = "一级参数增加")
	@RequestMapping(value="/addLevel1Parame.action")
	public String addLevel1Parame(HttpServletRequest request,CondiBean cond) {
		iUserBiz.inserLevel1Parame(cond);
		return "redirect:parameterList.action";
	}
	
	//添加二级参数
	@Log(operationType = "", operationName = "二级参数增加")
	@RequestMapping(value="/addLevel2Parame.action")
	public String addLevel2Parame(HttpServletRequest request,CondiBean cond) {
		iUserBiz.inserLevel2Parame(cond);
		return "redirect:parameterList.action";
	}
	
	//删除一级参数
	@Log(operationType = "", operationName = "一级参数删除")
	@RequestMapping(value="/oneparaDel.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody String delParam(HttpServletRequest request,int  paid){
		iUserBiz.delOneParam(paid);
		return "redirect:parameterList.action";
	}
	
	//删除二级参数
	@Log(operationType = "", operationName = "二级参数删除")
	@RequestMapping(value="/twoparaDel.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody String delTwoParam(HttpServletRequest request,int  paramId) {
		iUserBiz.delTwoParam(paramId);
		return "redirect:parameterList.action";
	}
	
	//参数配置修改
	
	@RequestMapping(value="/paramUpdate.action")
	public ModelAndView paramUpdate(HttpServletRequest request,ParamBean paBean) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pbean",paBean);
		mav.setViewName("user/parameterUpdate");
		return mav;
	}
	//参数配置修改后保存
	@Log(operationType = "", operationName = "一二级参数修改")
	@RequestMapping(value="/paramSave.action")
	public String paramSave(HttpServletRequest request,ParamBean paBean) {
		iUserBiz.updatParam(paBean);
		return "redirect:parameterList.action";
	}
	
	//日志查看
	@RequestMapping(value="/logSelect.action")
	public ModelAndView logSelect(HttpServletRequest request,CondiBean cond) {
		
		ModelAndView mav = new ModelAndView();
		ulist=iUserBiz.selectLog(cond);
		int pageTotol=PageUtil.pageTotal(iUserBiz.logComm(cond));
		mav.addObject("pageTotol", pageTotol);
		mav.addObject("pageNum", iUserBiz.logComm(cond));
		mav.addObject("blurred",cond);//模糊查找的对象
		mav.addObject("log", ulist);
		mav.setViewName("user/logselect");
		return mav;
	}
}
