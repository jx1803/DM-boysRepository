package org.great.bean;

public class BatchDetailBean {
	private int batchDetailId;//批次详情id
	private String putBatch;//进货批次
	private String drugmanu;//药品厂家
	private String manuBatch;//厂家批号
	private int drugId;//药品编号
	private String proPlace;//药品产地
	private String proDate;//生产日期
	private int handleNum;//数量
	private double purPrice;//进价
	private double sellPrice;//销售价
	private String inDate;//有效日期
	private double totalMoney;//总价
	
	public BatchDetailBean() {
		// TODO Auto-generated constructor stub
	}

	public int getBatchDetailId() {
		return batchDetailId;
	}

	public void setBatchDetailId(int batchDetailId) {
		this.batchDetailId = batchDetailId;
	}

	public String getPutBatch() {
		return putBatch;
	}

	public void setPutBatch(String putBatch) {
		this.putBatch = putBatch;
	}

	public String getDrugmanu() {
		return drugmanu;
	}

	public void setDrugmanu(String drugmanu) {
		this.drugmanu = drugmanu;
	}

	public String getManuBatch() {
		return manuBatch;
	}

	public void setManuBatch(String manuBatch) {
		this.manuBatch = manuBatch;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getProPlace() {
		return proPlace;
	}

	public void setProPlace(String proPlace) {
		this.proPlace = proPlace;
	}

	public String getProDate() {
		return proDate;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	public int getHandleNum() {
		return handleNum;
	}

	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}

	public double getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(double purPrice) {
		this.purPrice = purPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	

}
