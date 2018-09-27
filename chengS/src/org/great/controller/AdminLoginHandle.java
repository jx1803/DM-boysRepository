package org.great.controller;

import java.security.acl.Permission;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;
import org.great.biz.IUserBiz;
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

	@RequestMapping("/toLogin.action")
	public ModelAndView toLogin() {
		return new ModelAndView("login");
	}

	// 进入index页面
	@RequestMapping(value = "/toIndex.action")
	public ModelAndView toIndex(HttpServletRequest req, AdminBean user) {
		
		HttpSession session = req.getSession();
		// 这里进入biz层
		flag = iUserBiz.userlogin(user, session);
		return new ModelAndView("pharmacy/index");
	}

	// 获取用户管理的显示数据,需要分页和模糊查找
	@RequestMapping(value = "/memberList.action")
	public ModelAndView userList(HttpServletRequest request,CondiBean cond) {
		//进入Biz
		//带入搜索条件
		ulist=iUserBiz.UserList(cond);
		System.out.println("时间函数+"+cond.getEntrytime());
		int pagenum=iUserBiz.ResultComm(cond);
		int pageTotol=PageUtil.pageTotal(pagenum);
		//总页数带模糊查找的总页数
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist",ulist);//相当于req.sett
		mav.addObject("blurred",cond);//模糊查找的对象
		mav.addObject("pageTotol",pageTotol);//返回总页数
		mav.addObject("page",cond.getPage());
		mav.setViewName("user/memberList");
		return mav;
	}
	// 用户删除
	@RequestMapping(value ="/delUser.action")
	public String DelUser(HttpServletRequest request,AdminBean adbean) {
		System.out.println("进入删除状态用户id"+adbean.getAdminId());
		//进入biz层，更改用户状态
		iUserBiz.DelState(adbean.getAdminId());
		return "redirect:/user/memberList.action";
	}
	
	//ajax启用禁用状态改变
	@RequestMapping(value ="/ChangeStateUser.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public AdminBean stateUser(AdminBean adbean) {
		iUserBiz.changeState(adbean);
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
	//用户增加插入到数据库
	@RequestMapping(value="/memberAddSave.action")
	public String member_add_save(HttpServletRequest request,AdminBean abean) {
		System.out.println("账户名称"+abean.getAdminName());
		System.out.println("账户id"+abean.getDeptId());
		iUserBiz.MemberAdd(abean);
		return "redirect:/user/memberList.action";
	}
	
	//进入用户修改界面
	@RequestMapping(value="/memberListUpdate.action")
	public ModelAndView member_list_update(HttpServletRequest request,AdminBean abean) {
		return new ModelAndView();
	}
	//用户修改提交
//	@RequestMapping(value = "/member_update_save.action")
//	public 
	
	
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
		mav.addObject("page",cond.getPage());
		mav.setViewName("user/roleList");
		return mav;
	}
	
	//角色增加（部门增加）
	@RequestMapping(value ="/roleListAdd.action")
	public ModelAndView role_list_add(HttpServletRequest request) {
		return new ModelAndView();
	}
	
	//部门添加保存,一个插入语句
	@RequestMapping(value ="/roleAddSave.action")
	public String role_add_save(HttpServletRequest request,RoleBean rolebean) {
		System.out.println("角色名称"+rolebean.getRoleName());
		System.out.println("角色简介"+rolebean.getRolebewrite());
		//将语句插入,进入biz层
		iUserBiz.RoleInsert(rolebean);
		return "redirect:/user/roleList.action";
	}
	
	//进入角色信息修改页面
	@RequestMapping(value="/roleListUpdate.action")
	public ModelAndView roleListUpdate(HttpServletRequest request) {
		return new ModelAndView();
	}
	
	//角色信息修改保存
	@RequestMapping(value="/roleListSave.action")
	public String roleListSave() {
		return "redirect:/user/roleList.action";
	}
	
/******************部门管理***************/
	@RequestMapping(value = "/deptList.action")
	public ModelAndView deptList(CondiBean cond) {
		ulist=iUserBiz.memberDept(cond);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dlist", ulist);
		mav.setViewName("user/deptList");
		return mav;
	}
	//部门增加
	@RequestMapping(value = "/dept_list_add.action")
	public ModelAndView dept_list_add(HttpServletRequest request) {
		return new ModelAndView();
	}
	//部门增加保存
	@RequestMapping(value = "/dept_add_save.action")
	public String dept_add_save() {
		return "redirect:/user/dept_list.action";
	}
	
	//部门删除
	@RequestMapping(value = "/dept_list_del.action")
	public String delDept() {
		return "redirect:/user/dept_list.action";
	}
	

	
/******************一级菜单管理*************/
	
	@RequestMapping(value="/Menu_Level1_list.action")
	public ModelAndView Menu_level1(HttpServletRequest request,CondiBean cond) {
		//取出所有一级菜单，进入Biz层
		System.out.println("跳转进来刷新");
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
	@RequestMapping(value="/Menu_Level1_add.action")
	public ModelAndView menu_Level1_add(HttpServletRequest request) {
		return new ModelAndView();
	}
//一级菜单提交至数据库
	@RequestMapping(value="/Menu_Level1_save.action")
	public String Menu_Level1_save(HttpServletRequest request,PermissionBean pbean) {
		System.out.println("pbean+++"+pbean.getPermission());
		iUserBiz.insertPMenu(pbean);
		return "redirect:/user/Menu_Level1_list.action";
	}
	//一级菜单删除
	
	@RequestMapping(value="/Menu_Level1_del.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public PermissionBean Menu_Level1_del(HttpServletRequest request,PermissionBean pbean) {
		System.out.println("ajax进入"+pbean.getPermissionId());
		iUserBiz.MenuTwoDel(pbean.getPermissionId());
		return pbean;
	}
	
	//一级菜单修改
	@RequestMapping(value="/user/Menu_Level1_update.action")
	public String Menu_Level1_update(HttpServletRequest request,PermissionBean pbean) {
		return null;
	}
	
	
/*****************二级菜单管理************/
	@RequestMapping(value="/Menu_Level2_list.action")
	public ModelAndView Menu_level2(HttpServletRequest request,CondiBean cond) {
		ulist = iUserBiz.leveltwoMenu(cond);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		//还要有分页
		int pageTotal= PageUtil.pageTotal(iUserBiz.ResulttwoMenu(cond));//一级菜单总页数
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("blurred", cond);//模糊查找的值，返回页面
		return mav;
	}
	
	//进入增加二级菜单界面
	@RequestMapping(value="/Menu_Level2_add.action")
	public ModelAndView Menu_Level2_add(HttpServletRequest request,CondiBean cond) {
		//把一级菜单，传给二级菜单添加页
		ulist=iUserBiz.PMenu();
		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		return mav;
	}
	//将添加的二级菜单提交到数据库中
	@RequestMapping(value="/Menu_Level2_save.action")
	public String Menu_Level2_save(HttpServletRequest request,PermissionBean pbean) {
		int i = iUserBiz.insertSMenu(pbean);
		return "redirect:Menu_Level2_list.action";
	}
	//删除二级菜单（ajax）
	@RequestMapping(value="/Menu_Level2_del.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public PermissionBean Menu_Level2_del(HttpServletRequest request,PermissionBean pbean) {
		System.out.println("ajax进入"+pbean.getPermissionId());
		iUserBiz.MenuTwoDel(pbean.getPermissionId());
		return pbean;
	}
	
	//二级菜单修改
	@RequestMapping(value="/Menu_Level2_update.action")
	public String Menu_Level2_update(HttpServletRequest request,PermissionBean pbean) {
		return null;
	}
	
	
/*************权限管理*************/
	@RequestMapping(value = "/pConfiguration.action")
	public ModelAndView pConfiguration(HttpServletRequest request,String roleid) {
		//找到所有的角色返回给页面，上面是有分页
		ModelAndView mav = new ModelAndView();
		System.out.println("输出角色id"+roleid);
		if(null!=roleid) {
			int i = Integer.parseInt(roleid);
			//调用Biz找到角色id对应的权限
			rlist=iUserBiz.roleConfiguration(i);//角色的权限
			System.out.println("取得权限"+rlist);
			//角色没有的权限
			olist=iUserBiz.roleConfigOther(i);//角色没有的权限
		}
		ulist = iUserBiz.roleAllList();
		System.out.println("");
		mav.addObject("roleid", roleid);//把过来的id再返回去给页面
		mav.addObject("rlist", rlist);//角色的已分配权限
		mav.addObject("ulist", ulist);//角色
		mav.addObject("roleother", olist);
		return mav;
	}
	
	//各角色的权限
	@RequestMapping(value = "/Configuration.action")
	public ModelAndView Configuration(HttpServletRequest request,String roleid) {
		
		return new ModelAndView();
	}
	
	//角色权限变化---删除权限
	@RequestMapping(value="/ConfigurationDel.action")
	public String ConfigurationDel(HttpServletRequest request, 
			@RequestParam(value = "time") String test,@RequestParam(value = "roleId") int roleId) {
		//进入biz层去，删除权限
		iUserBiz.DelConfig(test,roleId);
		return "redirect:pConfiguration.action";
	}
	
	//角色权限变化---增加权限
	@RequestMapping(value="/ConfigurationAdd.action")
	public String ConfigurationAdd(HttpServletRequest request,
			@RequestParam(value = "time") String test,@RequestParam(value = "roleId") int roleId) {
		System.out.println("进入要增加");
		iUserBiz.AddConfig(test,roleId);
			
		return "redirect:pConfiguration.action";
	}
}
