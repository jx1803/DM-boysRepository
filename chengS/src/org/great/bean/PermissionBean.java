package org.great.bean;

import org.springframework.stereotype.Component;

@Component
public class PermissionBean {
	private int permissionId;
	private String permission;//权限
	private int pid;//父id
	private String url;//地址
	
	private String sname;//另外一个权限的名称
	private int fid;//父级id
	private RoleBean role;//2018.09.27 角色bean
	public PermissionBean() {
		// TODO Auto-generated constructor stub
	}
	
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
