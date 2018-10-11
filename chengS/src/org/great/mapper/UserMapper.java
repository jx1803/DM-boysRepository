package org.great.mapper;

import java.util.List;

import org.great.bean.AdminBean;
import org.great.bean.CondiBean;
import org.great.bean.DeptBean;
import org.great.bean.LogBean;
import org.great.bean.ParamBean;
import org.great.bean.PermissionBean;
import org.great.bean.RoleBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
/*****************用户管理*******************/
	public AdminBean userlogin(AdminBean user);//登录验证
	public int changepsword(AdminBean user);//密码修改
	public List<AdminBean> userList(CondiBean cd);//总用户并分页
	public List<AdminBean> userdellist(CondiBean cd);//已删除用户查看
	public int userdelcomm(CondiBean cd);//删除用户总数
	
	public int resultPage(CondiBean cd);//用户总页数
	public int changestate(AdminBean user);//状态
	public int delstate(int adminid);//删除
	//public AdminBean selectOneUser(AdminBean user);//
	public List<RoleBean> memberrole();//找到所有的角色
	public List<DeptBean> memberdept(CondiBean cond);//找到所有的部门信息
	public int memberadd(AdminBean abean);//用户数据插入
	
	public AdminBean selectuserone(String adminName);//找到新添加的用户的信息
	public int insertroleanduser(CondiBean cond);//将用户id和角色id插入到角色和用户的关系表中
	public CondiBean roleandadmin(int adminId);//通过用户id找到角色id
	public int updateresetpsw(AdminBean abean);//一键重置密码
	public int updateuser(AdminBean abean);//修改用户信息
	public int updateroleanduser(CondiBean cond);//修改用户和角色关系表
	
	public AdminBean reappearuser(String account);//注册时看是否重复账号
	
/*********************部门管理*****************/
	public int inserdept(DeptBean dept);//新增部门名称
	public int deldept(int deptid);//部门删除
	public List<DeptBean> deptall();//所有部门
	public int deptsave(DeptBean dept);
	public int deptcomm(CondiBean cond);//部门总数
/******************角色管理***************/
	//角色功能持久层
	public List<RoleBean> rolelist(CondiBean cd);//角色列表，没有查询条件
	public int rolecomm(CondiBean cd);//角色总数
	public int roleinsert(RoleBean rolebean);
	public List<RoleBean> rolealllist();//所有角色不分页
	public RoleBean selectrole(int roleId);//通过角色id找到角色名称以及简介
	public int updaterole(RoleBean role);//修改角色名称
	public int delrole(int roleid);//删除角色表
	public int delroleanduser(int roleid);//删除角色与用户关系表
	public int delroleandper(int roleid);//删除角色与权限关系表
/******************菜单管理**************/
	//一级菜单管理
	public List<PermissionBean> levelonemenu(CondiBean cd);
	public List<PermissionBean> pmnu();//找出所有父级菜单，不分页
	public int resultonemenu(CondiBean cd);//一级父级菜单个数
	public int insertpmenu(PermissionBean pbean);//添加一级菜单至数据库
	//二级菜单管理
	public List<PermissionBean> leveltwomenu(CondiBean cd);//二级菜单列表，取得是所有的
	public int insertsmenu(PermissionBean pbean);//添加二级菜单至数据库
	public int delMenuAndRole(int fid);//删除菜单与角色的关系表
	public int menutwodel(int permissionId);//二级菜单删除
	public int menuonedel(int pid);//一级菜单删除
	//所有的菜单
	public List<PermissionBean> selectallmenu(AdminBean bean);
	public int resulttwomenu(CondiBean cbean);//二级菜单总个数
	public int updatemenu1(PermissionBean pbean);//修改一级菜单
	public int updatetwomenu(PermissionBean pbean);//修改二级菜单
	
/*************************权限管理***********/
	//角色得到权限
	public List<PermissionBean> roleconfig(int roleid);
	public List<PermissionBean> roleconfigother(int roleid);
	public int delconfig(CondiBean cbean);//删除角色权限
	public PermissionBean pidmenu(int perid);//子id找到父id
	public CondiBean roleandmenu(CondiBean cbean);//父级id和角色找到关系表值
	public int insertsconfig(CondiBean cbean);//插入子级菜单id和角色进入关系表中
	
/************************参数配置*****************/
	//参数列表
	public List<ParamBean> paramlist(CondiBean cbean);
	public int paramcomm(CondiBean cbean);
	public int inserlevel1parame(CondiBean cbean);
	public List<ParamBean> level1param();//一级参数的名称
	public int inserlevel2parame(CondiBean cbean);//二级参数的名称
	public int deltwoparam(int paramid);//删除二级参数配置
	public int delonparam(int paid);//删除父级id下的二级id
	public int updatepid(ParamBean bean);//改父级参数的名称
	public int updateparam(ParamBean bean);//改子级参数的名称

	/*************日志查看*************/
	public List<LogBean> selectlog(CondiBean cbean);
	public int logcomm(CondiBean cbean);
}
