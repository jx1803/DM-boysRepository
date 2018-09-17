/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：OutAndInBean.java
 * 
 * 创建时间：2018年9月17日 下午4:03:34
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

/**
 * @ClassName: OutAndInBean
 * @Description: 药品出入库表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午4:03:34
 */
public class OutAndInBean {
	private int outAndInId;// 出入库记录ID
	private int drugId;// 药品编码
	private String proPlace;// 药品产地
	private String drugManu;// 药品厂家
	private String proDate;// 生产日期
	private int handleNum;// 数量
	private double purPrice;// 进价
	private double sellPrice;// 售价
	private double totalMoney;// 价格合计
	private String putBatch;// 入库批次
	private String inDate;// 有效日期
	private int placeId;// 药房/药库
	private int outInId;// 出库/入库
	private String destination;// 目的地
	private String handleDate;// 操作时间
	private int adminId;// 操作人员ID
	private String manuBatch;// 厂家批次

	public OutAndInBean() {
		super();
	}

	public int getOutAndInId() {
		return outAndInId;
	}

	public int getDrugId() {
		return drugId;
	}

	public String getProPlace() {
		return proPlace;
	}

	public String getDrugManu() {
		return drugManu;
	}

	public String getProDate() {
		return proDate;
	}

	public int getHandleNum() {
		return handleNum;
	}

	public double getPurPrice() {
		return purPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public String getPutBatch() {
		return putBatch;
	}

	public String getInDate() {
		return inDate;
	}

	public int getPlaceId() {
		return placeId;
	}

	public int getOutInId() {
		return outInId;
	}

	public String getDestination() {
		return destination;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public int getAdminId() {
		return adminId;
	}

	public String getManuBatch() {
		return manuBatch;
	}

	public void setOutAndInId(int outAndInId) {
		this.outAndInId = outAndInId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public void setProPlace(String proPlace) {
		this.proPlace = proPlace;
	}

	public void setDrugManu(String drugManu) {
		this.drugManu = drugManu;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}

	public void setPurPrice(double purPrice) {
		this.purPrice = purPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setPutBatch(String putBatch) {
		this.putBatch = putBatch;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public void setOutInId(int outInId) {
		this.outInId = outInId;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setManuBatch(String manuBatch) {
		this.manuBatch = manuBatch;
	}

	@Override
	public String toString() {
		return "OutAndInBean [outAndInId=" + outAndInId + ", drugId=" + drugId + ", proPlace=" + proPlace
				+ ", drugManu=" + drugManu + ", proDate=" + proDate + ", handleNum=" + handleNum + ", purPrice="
				+ purPrice + ", sellPrice=" + sellPrice + ", totalMoney=" + totalMoney + ", putBatch=" + putBatch
				+ ", inDate=" + inDate + ", placeId=" + placeId + ", outInId=" + outInId + ", destination="
				+ destination + ", handleDate=" + handleDate + ", adminId=" + adminId + ", manuBatch=" + manuBatch
				+ "]";
	}

}
