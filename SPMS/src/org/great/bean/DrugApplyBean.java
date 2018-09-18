package org.great.bean;

//申请表
public class DrugApplyBean {
	private int drugApplyId;// 申请记录id
	private int adminId;// 申请人id
	private int drugId;// 药品编码
	private int applyNum;// 申请数量
	private String applyDate;// 申请时间
	private int checkId;// 审核状态id
	private int applyTypeId;// 请领/退库/报损id
	private String applyReason;// 申请理由

	public DrugApplyBean() {
		super();
	}

	public int getDrugApplyId() {
		return drugApplyId;
	}

	public void setDrugApplyId(int drugApplyId) {
		this.drugApplyId = drugApplyId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	

	public int getApplyTypeId() {
		return applyTypeId;
	}

	public void setApplyTypeId(int applyTypeId) {
		this.applyTypeId = applyTypeId;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

}
