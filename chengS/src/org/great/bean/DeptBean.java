package org.great.bean;

import org.springframework.stereotype.Component;

@Component
public class DeptBean {
	private int deptId;
	private String dept;//部门名称
	public DeptBean() {
		// TODO Auto-generated constructor stub
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	

}
