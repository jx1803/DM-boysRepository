/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：StoDrugBean.java
 * 
 * 创建时间：2018年9月17日 下午3:41:42
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @ClassName: StoDrugBean
 * @Description: 药库药品表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午3:41:42
 */
@Component
public class StoDrugBean {
	private int drugId;// 药品编码
	private String drugName;// 药品名称
	private String generalName;// 通用名称
	private String specific;// 规格
	private String unit;// 单位
	private double retailPrice;// 零售价
	private double purPrice;//进价
	private int dosageId;// 剂量ID
	private String usage;// 使用方法
	private String invoiceTitle;// 发票抬头
	private String pinyinCode;// 拼音码
	private String antibiotic;// 抗生素
	private String dosage;// 次剂量
	private int dailyNum;// 日次数
	private int typeId;// 药品类别ID
	private String drugmanu;//药品厂家
	private String proPlace;//药品产地
	private DfBean dfBean;//剂型实体
	private DrugTypeBean dtBean;//药品类型bean
	private BatchDetailBean batchDetailBean;//药品入库批次详情对象
	private PhaDrugBean phaDrugBean;//药房药品信息对象
	private SellBean sellBean;//销售记录对象
	private List<StoDrugBean> drugList;//卖药的信息列表
	private DrugTypeBean drugTypeBean;//药品类别bean
	
	/**
	 * 查找药品(lp)
	 */
	
	private InventoryBean inventoryBean;// 库存实体
	private OutAndInBean outAndInBean;

	public StoDrugBean() {
		super();
	}

	public double getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(double purPrice) {
		this.purPrice = purPrice;
	}

	public String getDrugmanu() {
		return drugmanu;
	}

	public void setDrugmanu(String drugmanu) {
		this.drugmanu = drugmanu;
	}

	public String getProPlace() {
		return proPlace;
	}

	public void setProPlace(String proPlace) {
		this.proPlace = proPlace;
	}

	public int getDrugId() {
		return drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public String getGeneralName() {
		return generalName;
	}

	public String getSpecific() {
		return specific;
	}

	public String getUnit() {
		return unit;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public int getDosageId() {
		return dosageId;
	}

	public String getUsage() {
		return usage;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public String getAntibiotic() {
		return antibiotic;
	}

	public String getDosage() {
		return dosage;
	}

	public int getDailyNum() {
		return dailyNum;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setDosageId(int dosageId) {
		this.dosageId = dosageId;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public void setAntibiotic(String antibiotic) {
		this.antibiotic = antibiotic;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}





	public BatchDetailBean getBatchDetailBean() {
		return batchDetailBean;
	}

	public void setBatchDetailBean(BatchDetailBean batchDetailBean) {
		this.batchDetailBean = batchDetailBean;
	}

	public PhaDrugBean getPhaDrugBean() {
		return phaDrugBean;
	}

	public void setPhaDrugBean(PhaDrugBean phaDrugBean) {
		this.phaDrugBean = phaDrugBean;
	}

	public SellBean getSellBean() {
		return sellBean;
	}

	public void setSellBean(SellBean sellBean) {
		this.sellBean = sellBean;
	}

	public List<StoDrugBean> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<StoDrugBean> drugList) {
		this.drugList = drugList;
	}



	public DfBean getDfBean() {
		return dfBean;
	}

	public void setDfBean(DfBean dfBean) {
		this.dfBean = dfBean;
	}

	public DrugTypeBean getDtBean() {
		return dtBean;
	}

	public void setDtBean(DrugTypeBean dtBean) {
		this.dtBean = dtBean;
	}

	@Override
	public String toString() {
		return "StoDrugBean [drugId=" + drugId + ", drugName=" + drugName + ", generalName=" + generalName
				+ ", specific=" + specific + ", unit=" + unit + ", retailPrice=" + retailPrice + ", dosageId="
				+ dosageId + ", usage=" + usage + ", invoiceTitle=" + invoiceTitle + ", pinyinCode=" + pinyinCode
				+ ", antibiotic=" + antibiotic + ", dosage=" + dosage + ", dailyNum=" + dailyNum + ", typeId=" + typeId
				+ ", drugmanu=" + drugmanu + ", proPlace=" + proPlace + "]";
	}

	public InventoryBean getInventoryBean() {
		return inventoryBean;
	}

	public void setInventoryBean(InventoryBean inventoryBean) {
		this.inventoryBean = inventoryBean;
	}

	public OutAndInBean getOutAndInBean() {
		return outAndInBean;
	}

	public void setOutAndInBean(OutAndInBean outAndInBean) {
		this.outAndInBean = outAndInBean;
	}

	public DrugTypeBean getDrugTypeBean() {
		return drugTypeBean;
	}

	public void setDrugTypeBean(DrugTypeBean drugTypeBean) {
		this.drugTypeBean = drugTypeBean;
	}

	public double getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(double purPrice) {
		this.purPrice = purPrice;
	}

	



	

	
}
