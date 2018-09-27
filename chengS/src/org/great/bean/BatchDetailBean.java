package org.great.bean;

import org.springframework.stereotype.Component;

@Component
public class BatchDetailBean {
	private int batchDetailId;//批次详情id
	private String putBatch;//进货批次
	private String manuBatch;//厂家批号
	private int drugId;//药品编号
	private String proDate;//生产日期
	private int handleNum;//数量
	private double purPrice;//进价
	private double sellPrice;//销售价
	private String inDate;//有效日期
	private double totalMoney;//总价
	
	/**志展添加*/
    private StoDrugBean stoDrugBean;//药品实体bean
	private OutAndInBean outAndInBean;//出入库记录表，新增用于药库采购药品用
	private InventoryBean inventoryBean;//库存实体bean，用于采购药品入库时更新库存
	private DrugApplyBean drugApplyBean;//药品申请实体bean，用于更改采购药品购买入库状态
	
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

	
	public StoDrugBean getStoDrugBean() {
		return stoDrugBean;
	}

	public OutAndInBean getOutAndInBean() {
		return outAndInBean;
	}

	public InventoryBean getInventoryBean() {
		return inventoryBean;
	}

	public DrugApplyBean getDrugApplyBean() {
		return drugApplyBean;
	}

	public void setStoDrugBean(StoDrugBean stoDrugBean) {
		this.stoDrugBean = stoDrugBean;
	}

	public void setOutAndInBean(OutAndInBean outAndInBean) {
		this.outAndInBean = outAndInBean;
	}

	public void setInventoryBean(InventoryBean inventoryBean) {
		this.inventoryBean = inventoryBean;
	}

	public void setDrugApplyBean(DrugApplyBean drugApplyBean) {
		this.drugApplyBean = drugApplyBean;
	}

	@Override
	public String toString() {
		return "BatchDetailBean [batchDetailId=" + batchDetailId + ", putBatch=" + putBatch + ", manuBatch=" + manuBatch
				+ ", drugId=" + drugId + ", proDate=" + proDate + ", handleNum=" + handleNum + ", purPrice=" + purPrice
				+ ", sellPrice=" + sellPrice + ", inDate=" + inDate + ", totalMoney=" + totalMoney + "]";
	}
	
	

}
