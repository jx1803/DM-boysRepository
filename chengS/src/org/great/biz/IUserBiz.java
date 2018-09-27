package org.great.biz;

import java.security.acl.Permission;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;



public interface IUserBiz {
/*****************用户管理*****************/
	public String userlogin(AdminBean user,HttpSession session);
	public List<AdminBean> UserList(CondiBean cond);
	public int ResultComm(CondiBean cond);//计算总页数
	public int changeState(AdminBean user);//状态
	public int DelState(int adminid);//删除
	public int MemberAdd(AdminBean adbean);
/*********用户增加部分************/
	//找出所有的角色信息
	public List<RoleBean> memberRole();
	//找出所有的部分信息
	public List<DeptBean> memberDept(CondiBean cond);

/************角色管理*************/
	//角色部分
	public List<RoleBean> RoleList(CondiBean cond);
	public int RoleComm(CondiBean cond);//计算总页数
	public int RoleInsert(RoleBean rolebean);
	public List<RoleBean> roleAllList();//找到所有的角色，不分页
	
	
/*****************菜单管理**********/
	//一级菜单
	public List<PermissionBean> leveloneMenu(CondiBean cond);
	public List<PermissionBean> PMenu();//找出所有的父级菜单，不分页
	public int ResultoneMenu(CondiBean cond);//一级菜单总个数
	public int insertPMenu(PermissionBean pbean);//插入父级菜单至数据库
	//二级菜单
	public List<PermissionBean> leveltwoMenu(CondiBean cond);
	public int ResulttwoMenu(CondiBean cond);//二级菜单总个数
	public int insertSMenu(PermissionBean pbean);//插入二级菜单至数据库
	public int MenuTwoDel(int permissionId);//二级菜单删除
	
/****************权限分配*******************/
 //根据角色得到权限
	public List<PermissionBean> roleConfiguration(int roleid);
	public List<PermissionBean> roleConfigOther(int roleid);//角色没有的权限
//权限分配删除
	public int DelConfig(String test, int roleid);
	public int AddConfig(String test,int roleid);//权限增加
}
	
