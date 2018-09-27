package org.great.bean;

public class RoleBean {
	private int roleId;//角色id
	private String roleName;//角色名
	
	//增加角色描述字段
	private String rolebewrite;//角色相关描述
	public RoleBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getRolebewrite() {
		return rolebewrite;
	}

	public void setRolebewrite(String rolebewrite) {
		this.rolebewrite = rolebewrite;
	}

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
