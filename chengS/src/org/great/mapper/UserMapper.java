package org.great.mapper;

import java.util.List;

import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
/*****************用户管理*******************/
	public AdminBean userlogin(AdminBean user);//登录验证
	public List<AdminBean> userList(CondiBean cd);//总用户并分页
	public int resultPage(CondiBean cd);//用户总页数
	public int changestate(AdminBean user);//状态
	public int delstate(int adminid);//删除
	//public AdminBean selectOneUser(AdminBean user);//
	public List<RoleBean> memberrole();//找到所有的角色
	public List<DeptBean> memberdept(CondiBean cond);//找到所有的部门信息
	public int memberadd(AdminBean abean);//用户数据插入
	
/******************角色管理***************/
	//角色功能持久层
	public List<RoleBean> rolelist(CondiBean cd);//角色列表，没有查询条件
	public int rolecomm(CondiBean cd);//角色总数
	public int roleinsert(RoleBean rolebean);
	public List<RoleBean> rolealllist();//所有角色不分页
	
/******************菜单管理**************/
	//一级菜单管理
	public List<PermissionBean> levelonemenu(CondiBean cd);
	public List<PermissionBean> pmnu();//找出所有父级菜单，不分页
	public int resultonemenu(CondiBean cd);//一级父级菜单个数
	public int insertpmenu(PermissionBean pbean);//添加一级菜单至数据库
	//二级菜单管理
	public List<PermissionBean> leveltwomenu(CondiBean cd);//二级菜单列表，取得是所有的
	public int insertsmenu(PermissionBean pbean);//添加二级菜单至数据库
	public int menutwodel(int permissionId);//二级菜单删除
	
/*************************权限管理***********/
	//角色得到权限
	public List<PermissionBean> roleconfig(int roleid);
	public List<PermissionBean> roleconfigother(int roleid);
	public int delconfig(CondiBean cbean);//删除角色权限
	public PermissionBean pidmenu(int perid);//子id找到父id
	public CondiBean roleandmenu(CondiBean cbean);//父级id和角色找到关系表值
	public int insertsconfig(CondiBean cbean);//插入子级菜单id和角色进入关系表中
}
