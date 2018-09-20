package org.great.bean;
//调价记录
public class AdjustPriceBean {
	private int adjustPriceId;//
	private String adjustDate;//调价时间
	private int drugId;//药品编码
	private double beforeAdjust;//调价前的价格
	private double afterAdjust;//调价后的价格
	private int adminId;//调价人
	public AdjustPriceBean() {
		// TODO Auto-generated constructor stub
	}
	public int getAdjustPriceId() {
		return adjustPriceId;
	}
	public void setAdjustPriceId(int adjustPriceId) {
		this.adjustPriceId = adjustPriceId;
	}
	public String getAdjustDate() {
		return adjustDate;
	}
	public void setAdjustDate(String adjustDate) {
		this.adjustDate = adjustDate;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public double getBeforeAdjust() {
		return beforeAdjust;
	}
	public void setBeforeAdjust(double beforeAdjust) {
		this.beforeAdjust = beforeAdjust;
	}
	public double getAfterAdjust() {
		return afterAdjust;
	}
	public void setAfterAdjust(double afterAdjust) {
		this.afterAdjust = afterAdjust;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
}
