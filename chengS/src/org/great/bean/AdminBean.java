package org.great.bean;

import org.springframework.stereotype.Component;

//用户实体
@Component
public class AdminBean {
	private int adminId;
	private String adminName;
	private String adminAccount;//用户账户
	private String sex;//性别
	private int roleId;//角色id
	private int deptId;//部门id
	private String officePhone;//办公室电话
	private String mobilePhone;//移动电话
	private String email;//邮箱
	private String education;//学历
	private String idcard;//身份证号
	private String entryDate;//入职日期
	private String birthDate;//出生日期
	private String quitDate;//离职日期
	private String address;//住址
	private String password;//密码
	private int paramId;//停用状态
	private RoleBean rolebean;//角色bean
	private DeptBean deptbean;//部门bean
	private ParamBean parambean;//状态bean

	private String name;//新增  角色用户名拼接字段
	
	public AdminBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public RoleBean getRolebean() {
		return rolebean;
	}
	public void setRolebean(RoleBean rolebean) {
		this.rolebean = rolebean;
	}
	public DeptBean getDeptbean() {
		return deptbean;
	}
	public void setDeptbean(DeptBean deptbean) {
		this.deptbean = deptbean;
	}
	public ParamBean getParambean() {
		return parambean;
	}
	public void setParambean(ParamBean parambean) {
		this.parambean = parambean;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getParamId() {
		return paramId;
	}
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	

}
