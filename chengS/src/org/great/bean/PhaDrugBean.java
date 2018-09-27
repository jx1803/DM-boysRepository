package org.great.bean;

import org.springframework.stereotype.Component;

//药房药品表
@Component
public class PhaDrugBean {
	private int phaDrugId;// 药房药品id
	private int drugId;// 药品编号
	private int drugNum;// 数量
	private int useableId;// 停用启用状态
	private double minimum;// 底限
	private double maximum;//上限值
	private StoDrugBean stoDrugBean;//药品信息

	public PhaDrugBean() {
		super();
	}

	public int getPhaDrugId() {
		return phaDrugId;
	}

	public void setPhaDrugId(int phaDrugId) {
		this.phaDrugId = phaDrugId;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}



	public int getDrugNum() {
		return drugNum;
	}

	public void setDrugNum(int drugNum) {
		this.drugNum = drugNum;
	}



	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public int getUseableId() {
		return useableId;
	}

	public void setUseableId(int useableId) {
		this.useableId = useableId;
	}

	public StoDrugBean getStoDrugBean() {
		return stoDrugBean;
	}

	public void setStoDrugBean(StoDrugBean stoDrugBean) {
		this.stoDrugBean = stoDrugBean;
	}

	

	

}
