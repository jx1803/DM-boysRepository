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

import org.springframework.stereotype.Component;

/**
 * @ClassName: OutAndInBean
 * @Description: 药品出入库表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午4:03:34
 */
@Component
public class OutAndInBean {
	private int outAndInId;// 出入库记录ID
	private double totalMoney;// 价格合计
	private String putBatch;// 入库批次
	private int placeId;// 21药房/22药库 
	private int outInId;// 17出库/18入库
	private String destination;// 目的地
	private String handleDate;// 操作时间
	private int adminId;// 操作人员ID
	private String drugSource;//入库来源
	private int drugId;//药品编号
	public OutAndInBean() {
		super();
	}

	public int getOutAndInId() {
		return outAndInId;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getPutBatch() {
		return putBatch;
	}

	public void setPutBatch(String putBatch) {
		this.putBatch = putBatch;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getOutInId() {
		return outInId;
	}

	public void setOutInId(int outInId) {
		this.outInId = outInId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setOutAndInId(int outAndInId) {
		this.outAndInId = outAndInId;
	}

	public String getDrugSource() {
		return drugSource;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public void setDrugSource(String drugSource) {
		this.drugSource = drugSource;
	}

}