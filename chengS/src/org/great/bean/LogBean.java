package org.great.bean;

import org.springframework.stereotype.Component;

//日志表
@Component
public class LogBean {
	private int logId;// 日志id
	private int adminId;// 操作人员
	private String operateDate;// 操作时间
	private String operateItem;// 操作事项
	
	private AdminBean adminbean;
	public LogBean() {
		super();
	}

	public AdminBean getAdminbean() {
		return adminbean;
	}

	public void setAdminbean(AdminBean adminbean) {
		this.adminbean = adminbean;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateItem() {
		return operateItem;
	}

	public void setOperateItem(String operateItem) {
		this.operateItem = operateItem;
	}

}
