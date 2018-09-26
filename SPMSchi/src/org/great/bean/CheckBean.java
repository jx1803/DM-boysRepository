package org.great.bean;

//盘点记录表
public class CheckBean {
	private int checkId;// 盘点记录id
	private int phaDrugId;// 药房药品表id
	private int profitId;// 盘盈/盘亏状态
	private double checkMoney;// 金额
	private String checkDate;// 日期

	public CheckBean() {
		super();
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int getPhaDrugId() {
		return phaDrugId;
	}

	public void setPhaDrugId(int phaDrugId) {
		this.phaDrugId = phaDrugId;
	}

	public int getProfitId() {
		return profitId;
	}

	public void setProfitId(int profitId) {
		this.profitId = profitId;
	}

	public double getCheckMoney() {
		return checkMoney;
	}

	public void setCheckMoney(double checkMoney) {
		this.checkMoney = checkMoney;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

}
