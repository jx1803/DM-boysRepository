package org.great.biz;

import java.security.acl.Permission;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.LogBean;
import org.great.bean.ParamBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;



public interface IUserBiz {
/*****************用户管理*****************/
	public String userlogin(AdminBean user,HttpSession session);
	public String userChangePsword(AdminBean user,String pasword);
	public List<AdminBean> UserList(CondiBean cond);
	public int ResultComm(CondiBean cond);//计算总页数
	public int changeState(AdminBean user);//状态
	public int DelState(int adminid);//删除
	public int MemberAdd(AdminBean adbean);
	public int selectRoleAndAdmin(int adminid);//通过用户id找到角色id
	public int updateResetPsw(AdminBean user);
	public List<AdminBean> userDelList(CondiBean cond);//已删除用户查看
	public int userDelComm(CondiBean cond);
/*********用户增加部分************/
	//找出所有的角色信息
	public List<RoleBean> memberRole();
	//找出分页的部门信息
	public List<DeptBean> memberDept(CondiBean cond);
	//通过用户名找到用户信息
	public AdminBean selectnewUser(CondiBean cond);
	
	//用户信息
	public AdminBean selectOtherUser(AdminBean adbean);
	public AdminBean memberUpdateSave(AdminBean ad,CondiBean rolebean);
	
	public AdminBean reappearUser(String account);//新增用户时查看是否重复账户
	
	/************角色管理*************/
	//角色部分
	public List<RoleBean> RoleList(CondiBean cond);
	public int RoleComm(CondiBean cond);//计算总页数
	public int RoleInsert(RoleBean rolebean);
	public List<RoleBean> roleAllList();//找到所有的角色，不分页
	public RoleBean selectRole(int roleId);//通过角色id找到角色
	public int updateRole(RoleBean role);//更改角色信息
	public int delRole(int roleId);//删除角色表
	public int delRoleAndUser(int roleId);//删除角色与用户关系表
	public int delRoleAndPer(int roleId);//删除角色时候，还需要删除角色与权限关系表
/*******************部门管理************/
	//找到所有部门
	public int inserDept(DeptBean dept);//增加部门
	public int delDept(int deptid);//删除部门
	public List<DeptBean> deptAll();//找出所有部门不分页
	public int deptSave(DeptBean dept); //修改部门后保存
	public int deptComm(CondiBean cond);//部门条数统计
/*****************菜单管理**********/
	//一级菜单
	public List<PermissionBean> leveloneMenu(CondiBean cond);
	public List<PermissionBean> PMenu();//找出所有的父级菜单，不分页
	public int ResultoneMenu(CondiBean cond);//一级菜单总个数
	public int insertPMenu(PermissionBean pbean);//插入父级菜单至数据库
	public int MenuOneDel(int permissionId);//一级菜单删除
	//二级菜单
	public List<PermissionBean> leveltwoMenu(CondiBean cond);
	public int ResulttwoMenu(CondiBean cond);//二级菜单总个数
	public int insertSMenu(PermissionBean pbean);//插入二级菜单至数据库
	public int MenuTwoDel(int permissionId);//二级菜单删除
	public int menuSave(PermissionBean pbean);//修改一二级菜单保存到数据库中

	//找到所有的菜单，不分页
	public List<PermissionBean> selectAllMenu(AdminBean admin);

/****************权限分配*******************/
 //根据角色得到权限
	public List<PermissionBean> roleConfiguration(int roleid);
	public List<PermissionBean> roleConfigOther(int roleid);//角色没有的权限
//权限分配删除
	public int DelConfig(String test, int roleid);
	public int AddConfig(String test,int roleid);//权限增加
	
/************参数配置************/
	public List<ParamBean> paramList(CondiBean cond);//参数列表
	public int ParamComm(CondiBean cond);//参数配置总条数
	public int inserLevel1Parame(CondiBean cond);
	
	//找到一级参数配置
	public List<ParamBean> Level1Param();//一级参数
	//二级参数配置插入
	public int inserLevel2Parame(CondiBean cond);//二级参数配置
	public int delOneParam(int paid);//删除一级参数配置
	public int delTwoParam(int paramId);//删除二级参数配置
	public int updatParam(ParamBean bean);
	
/***************日志查看************/
	public List<LogBean> selectLog(CondiBean cond);
	public int logComm(CondiBean cond);

}
	
