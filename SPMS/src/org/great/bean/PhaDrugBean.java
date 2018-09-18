package org.great.bean;

//药房药品表
public class PhaDrugBean {
	private int phaDrugId;// 药房药品id
	private int drugId;// 药品编号
	private int drugNum;// 数量
	private int useableId;// 停用启用状态
	private int minimum;// 底限
	private int maximum;//上限值

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

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getUseableId() {
		return useableId;
	}

	public void setUseableId(int useableId) {
		this.useableId = useableId;
	}

	

	

}
