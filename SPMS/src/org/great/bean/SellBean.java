/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：SellBean.java
 * 
 * 创建时间：2018年9月17日 下午3:48:29
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

/**
 * @ClassName: SellBean
 * @Description: 销售记录表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午3:48:29
 */
public class SellBean {
	private int sellId;// 销售记录ID
	private int drugId;// 药品编号
	private int adminId;// 销售人员ID
	private int sellNum;// 销售数量
	private double sales;// 销售额
	private String sellDate;// 销售时间

	public SellBean() {
		super();
	}

	public int getSellId() {
		return sellId;
	}

	public int getDrugId() {
		return drugId;
	}

	public int getAdminId() {
		return adminId;
	}

	public int getSellNum() {
		return sellNum;
	}

	

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	@Override
	public String toString() {
		return "SellBean [sellId=" + sellId + ", drugId=" + drugId + ", adminId=" + adminId + ", sellNum=" + sellNum
				+ ", sales=" + sales + ", sellDate=" + sellDate + "]";
	}

}
