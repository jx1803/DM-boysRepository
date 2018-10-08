package org.great.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.LogBean;
import org.great.bean.ParamBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;
import org.great.bean.WarnBean;
import org.great.mapper.DailyWorkMapper;
import org.great.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: UserBizImpl
 * @Description: 专门用于，用户管理，登录，显示，增删改查
 * @author 王伟国 传一科技总经理
 * @date 2018年9月18日 下午11:53:22
 */
@Service
public class UserBizImpl implements IUserBiz {
	private List ulist;
	private String flag;
	@Resource
	private UserMapper userMapper;

	@Resource
	private DailyWorkMapper dailyWorkMapper;// 映射器（日常工作）
	@Override
	public String userlogin(AdminBean user, HttpSession session) {
		// TODO Auto-generated method stub
		//先判断状态，是否为已启用

		user = userMapper.userlogin(user);

		
		if(null==user) {
			flag="login";
		
		}else {
		
			if(user.getParamId()==2) {
				session.setAttribute("User", user);
			
				flag="pharmacy/index";
			}else {
				flag="login";
			}

		session.setAttribute("User", user);
		
		session.setAttribute("belongId", 21);// 登录通过角色判断属于药房21或药库22
		List<WarnBean> warnList = dailyWorkMapper.selWarn(21);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		if (null != user) {
			flag = "success";

		}
		
		return flag;
	}

//修改密码
	public String userChangePsword(AdminBean user,String psword) {
		user = userMapper.userlogin(user);
		if(null==user) {
			//这里还要个弹框//账户密码错误
			flag="login";
			
		}else {
			//修改密码
			user.setName(psword);
			userMapper.changepsword(user);
			flag="pharmacy/index";
		}
		return flag;
	}
	
	//用户管理数据页面显示，
	@Override
	public List<AdminBean> UserList(CondiBean cd) {
		// TODO Auto-generated method stub
		cd.setPageup(cd.getPage()*5);
		cd.setPagedown((cd.getPage()*5-5)+1);
		ulist=userMapper.userList(cd);
		return ulist;
	}
//已删除用户显示
	@Override
	public List<AdminBean> userDelList(CondiBean cd){
		cd.setPageup(cd.getPage()*5);
		cd.setPagedown((cd.getPage()*5-5)+1);
		ulist=userMapper.userdellist(cd);
		return ulist;
	}
//已删除用户总数
	public int userDelComm(CondiBean cd) {
		return userMapper.userdelcomm(cd);
	}

	//获取用户总页数+模糊条件
	@Override
	public int ResultComm(CondiBean cod) {
		// TODO Auto-generated method stub
		int pageTotal= userMapper.resultPage(cod);
		return pageTotal;
	}


	
	//改变启用禁用状态
	@Override
	public int changeState(AdminBean user) {
		// TODO Auto-generated method stub
		//用id得到当前的用户状态
		//AdminBean user=userMapper.selectOneUser(user);
		if(user.getParamId()==3) {
			user.setParamId(2);
			int i =userMapper.changestate(user);
		}
		else if(user.getParamId()==2) {
			user.setParamId(3);
			int i = userMapper.changestate(user);
		}
		return 0;
	}


	//改变删除状态
	@Override
	public int DelState(int adminid) {
		// TODO Auto-generated method stub
		return userMapper.delstate(adminid);
	}

	//用户增加用到
	//找到所有角色信息
@Override
public List<RoleBean> memberRole() {
	// TODO Auto-generated method stub
	return userMapper.memberrole();
}

//用户增加用到
//找到所有部门信息
@Override
public List<DeptBean> memberDept(CondiBean cond) {
	// TODO Auto-generated method stub
	cond.setPageup(cond.getPage()*5);
	cond.setPagedown((cond.getPage()*5-5)+1);
	ulist=userMapper.memberdept(cond);
	return ulist;
}
//部门条数
public int deptComm(CondiBean cond) {
	
	return userMapper.deptcomm(cond);
}
//部门增加用到
public int inserDept(DeptBean dept) {
	userMapper.inserdept(dept);
	return 1;
}
//部门删除
public int delDept(int deptid) {
	userMapper.deldept(deptid);
	return 1;
}

//所有部门不分页
public List<DeptBean> deptAll(){
	ulist=userMapper.deptall();
	return ulist;
}
//修改部门后保存
public int deptSave(DeptBean dept) {
	userMapper.deptsave(dept);
	return 1;
}
//添加新用户后，根据用户名，找到用户id，插入到用户与角色关系表中
@Override
public AdminBean selectnewUser(CondiBean cond) {
	// TODO Auto-generated method stub
	//执行两个方法
	AdminBean adminbean= userMapper.selectuserone(cond.getAdminName());
	cond.setPermissionId(adminbean.getAdminId());
	cond.setRoleid(cond.getRoleid());
	System.out.println("角色id"+cond.getRoleid());
	System.out.println("用户id"+adminbean.getAdminId());
	//插入到角色与用户关系表中
	userMapper.insertroleanduser(cond);
	return null;
}
//用户名找到用户信息
@Override
public AdminBean selectOtherUser(AdminBean adbean) {
	// TODO Auto-generated method stub
	AdminBean adminbean= userMapper.selectuserone(adbean.getAdminName());
	return adminbean;
}

//通过用户id找到角色id
@Override
public int selectRoleAndAdmin(int adminid) {
	// TODO Auto-generated method stub
	
	CondiBean cond = userMapper.roleandadmin(adminid);
	return cond.getRoleid();
}

//重置密码
@Override
public int updateResetPsw(AdminBean admin) {
	// TODO Auto-generated method stub
	return userMapper.updateresetpsw(admin);
}

//修改用户信息
@Override
public AdminBean memberUpdateSave(AdminBean ad, CondiBean cond) {
	// TODO Auto-generated method stub
	//先修改用户本身的信息
	userMapper.updateuser(ad);
	//修改用户关系表
	userMapper.updateroleanduser(cond);
	return null;
}

//重复账户
@Override
public AdminBean reappearUser(String account) {
	// TODO Auto-generated method stub
	AdminBean admin = userMapper.reappearuser(account);
	if(null==admin) {
		
	}
	return admin;
}





/**************以下为角色的功能业务************************/
	
	//角色列表
	@Override
	public List<RoleBean> RoleList(CondiBean cond) {
		// TODO Auto-generated method stub
		cond.setPageup(cond.getPage()*5);
		cond.setPagedown((cond.getPage()*5-5)+1);
		ulist=userMapper.rolelist(cond);
		return ulist;
	}
	
//通过角色id找到角色以及角色简介
	@Override
	public RoleBean selectRole(int roleId) {
		// TODO Auto-generated method stub
		return userMapper.selectrole(roleId);
	}
//删除角色与用户关系表
@Override
public int delRoleAndUser(int roleId) {
	
	return userMapper.delroleanduser(roleId);
}

//删除权限与角色关系表
public int delRoleAndPer(int roleId) {
	return userMapper.delroleandper(roleId);
}
	
//删除角色
@Override
public int delRole(int roleId) {
	// TODO Auto-generated method stub
	return userMapper.delrole(roleId);
}


	//角色总页数
@Override
public int RoleComm(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.rolecomm(cond);
}

//角色插入执行业务
@Override
public int RoleInsert(RoleBean rolebean) {
	// TODO Auto-generated method stub
	int i =userMapper.roleinsert(rolebean);
	return 0;
}


@Override
public int MemberAdd(AdminBean adbean) {
	// TODO Auto-generated method stub
	//用户数据插入
	return userMapper.memberadd(adbean);
}

//所有角色不分页
@Override
public List<RoleBean> roleAllList() {
	// TODO Auto-generated method stub
	return userMapper.rolealllist();
}
//更改角色信息
@Override
public int updateRole(RoleBean role) {
	// TODO Auto-generated method stub
	return userMapper.updaterole(role);
}


/**************菜单***************/
//找出所有的菜单
public List<PermissionBean> selectAllMenu(AdminBean bean){
	return userMapper.selectallmenu(bean);
}
//一级菜单
@Override
public List<PermissionBean> leveloneMenu(CondiBean cond) {
	// TODO Auto-generated method stub
	cond.setPageup(cond.getPage()*5);
	cond.setPagedown((cond.getPage()*5-5)+1);
	return userMapper.levelonemenu(cond);
}

//一级菜单个数
@Override
public int ResultoneMenu(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.resultonemenu(cond);
}
//增加一级父级菜单
@Override
public int insertPMenu(PermissionBean pbean) {
	// TODO Auto-generated method stub
	return userMapper.insertpmenu(pbean);
}


//二级菜单列表
@Override
public List<PermissionBean> leveltwoMenu(CondiBean cond) {
	// TODO Auto-generated method stub
	cond.setPageup(cond.getPage()*5);
	cond.setPagedown((cond.getPage()*5-5)+1);
	
	 ulist = userMapper.leveltwomenu(cond);
	 System.out.println("搜索后的ulist"+ulist);
	return ulist;
}

//二级菜单个数
@Override
public int ResulttwoMenu(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.resulttwomenu(cond);
}


//找出所有的父级菜单不分页
@Override
public List<PermissionBean> PMenu() {
	// TODO Auto-generated method stub
	return userMapper.pmnu();
}


//插入二级菜单
@Override
public int insertSMenu(PermissionBean pbean) {
	// TODO Auto-generated method stub
	return userMapper.insertsmenu(pbean);
}

//二级菜单删除

//一级菜单删除
@Override
public int MenuOneDel(int pid) {
	userMapper.menutwodel(pid);
	int i = userMapper.menuonedel(pid);
	return i;
}

//删除二级菜单
@Override
public int MenuTwoDel(int fid) {
	// TODO Auto-generated method stub
	return userMapper.menuonedel(fid);
}



//修改一二级菜单保存至数据库
@Override
public int menuSave(PermissionBean pbean) {
	int j= userMapper.updatemenu1(pbean);
	int i = userMapper.updatetwomenu(pbean);
	return i;
}


/***************权限管理*************/
//角色有的权限
@Override
public List<PermissionBean> roleConfiguration(int roleid) {
	// TODO Auto-generated method stub
	return userMapper.roleconfig(roleid);
}


//角色没有的权限
@Override
public List<PermissionBean> roleConfigOther(int roleid) {
	// TODO Auto-generated method stub
	return userMapper.roleconfigother(roleid);
}

//角色删除权限
@Override
public int DelConfig(String test,int roleid) {
	// TODO Auto-generated method stub
	System.out.println("进来要删除"+test);
	System.out.println("得到的角色id值"+roleid);
	String[] str=test.split(",");
	
	for(int i=0;i<str.length;i++) {
		System.out.println("str=="+str[i]);
		
		String str1 = str[i];
		//先根据子id找到父id是否插入到关系表中
		int j = Integer.parseInt(str1);
	
		CondiBean cbean = new CondiBean();
		cbean.setPermissionId(j);//菜单id
		cbean.setRoleid(roleid);
		System.out.println("jjjjjjj"+j);
		int k = userMapper.delconfig(cbean);
		System.out.println("是否执行==="+k);
	}
	
	return 0;
}

//角色权限增加

@Override
public int AddConfig(String test, int roleid) {
	// TODO Auto-generated method stub
	String[] str=test.split(",");
	for(int i=0;i<str.length;i++) {
		System.out.println("str=="+str[i]);
		int j = Integer.parseInt(str[i]);
		//得到子菜单id后，查父菜单，然后，看父菜单有没有插入到关系不中，如果有则不必再插入，
	//如果咩有，则需要插入父菜单
		//1、找到父级菜单Id
		PermissionBean pbean = userMapper.pidmenu(j);
		System.out.println("找到父级id"+pbean.getPid());
		//2.通过角色和父级id查询关系表中是否已存在
		CondiBean cbean = new CondiBean();
		cbean.setPermissionId(pbean.getPid());//菜单id
		cbean.setRoleid(roleid);
		CondiBean cdbean	=userMapper.roleandmenu(cbean);
		if(cdbean!=null) {
			//已有父级菜单，只插入子级就好
			
			cbean.setPermissionId(j);//菜单id
			cbean.setRoleid(roleid);
			userMapper.insertsconfig(cbean);
		}else {
			//没有父级菜单，那就先插入父级菜单，
			cbean.setPermissionId(pbean.getPid());//菜单id
			cbean.setRoleid(roleid);
			userMapper.insertsconfig(cbean);
			
			//插入父级菜单后，再插入子级菜单
			cbean.setPermissionId(j);//菜单id
			cbean.setRoleid(roleid);
			userMapper.insertsconfig(cbean);
		}
		
	}	
	return 0;
}


/*******************参数配置***************/
@Override
public List<ParamBean> paramList(CondiBean cond) {
	// TODO Auto-generated method stub
	cond.setPageup(cond.getPage()*5);
	cond.setPagedown((cond.getPage()*5-5)+1);
	return userMapper.paramlist(cond);
}

//参数配置的总条数
@Override
public int ParamComm(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.paramcomm(cond);
}

//插入一级参数
@Override
public int inserLevel1Parame(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.inserlevel1parame(cond);
}

//取得一级参数配置
@Override
public List<ParamBean> Level1Param() {
	// TODO Auto-generated method stub
	return userMapper.level1param();
}

//插入二级参数配置
@Override
public int inserLevel2Parame(CondiBean cond) {
	// TODO Auto-generated method stub
	return userMapper.inserlevel2parame(cond);
}

//删除一级参数配置
@Override
public int delOneParam(int paid) {
	// TODO Auto-generated method stub
	//删除一级参数配置，以及一级参数配置下的二级
	userMapper.delonparam(paid);//
	int i = userMapper.deltwoparam(paid);
	return i;
}
//删除二级参数配置
@Override
public int delTwoParam(int paramid) {
	// TODO Auto-generated method stub
	return userMapper.deltwoparam(paramid);
}

//修改参数
@Override
public int updatParam(ParamBean bean) {
	//改两句
	userMapper.updatepid(bean);
	int i = userMapper.updateparam(bean);
	return i;
}

//日志查看
public List<LogBean> selectLog(CondiBean cond){
	cond.setPageup(cond.getPage()*5);
	cond.setPagedown((cond.getPage()*5-5)+1);
	ulist=userMapper.selectlog(cond);
	return ulist;
}
//日志总条数
public int logComm(CondiBean cond) {
	return userMapper.logcomm(cond);
}
}
