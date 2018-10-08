package org.great.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
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
		user = userMapper.userlogin(user);
		session.setAttribute("User", user);
		
		session.setAttribute("belongId", 21);// 登录通过角色判断属于药房21或药库22
		List<WarnBean> warnList = dailyWorkMapper.selWarn(21);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		if (null != user) {
			flag = "success";
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
		userMapper.delstate(adminid);
		return 0;
	}

	//用户增加用到
	//找到所有角色信息
@Override
public List<RoleBean> memberRole() {
	// TODO Auto-generated method stub
	ulist=userMapper.memberrole();
	return ulist;
}

//用户增加用到
//找到所有部门信息
@Override
public List<DeptBean> memberDept(CondiBean cond) {
	// TODO Auto-generated method stub
	ulist=userMapper.memberdept(cond);
	return ulist;
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


/**************菜单***************/
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
	return userMapper.leveltwomenu(cond);
}

//二级菜单个数
@Override
public int ResulttwoMenu(CondiBean cond) {
	// TODO Auto-generated method stub
	return 0;
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
@Override
public int MenuTwoDel(int permissionId) {
	// TODO Auto-generated method stub
	return userMapper.menutwodel(permissionId);
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








}
