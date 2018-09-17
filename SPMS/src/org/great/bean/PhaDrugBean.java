package org.great.bean;

//药房药品表
public class PhaDrugBean {
	private int phaDrugId;// 药房药品id
	private int drugId;// 药品编号
	private double drugNum;// 数量
	private int useableId;// 停用启用状态
	private double minimum;// 底限

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

	public double getDrugNum() {
		return drugNum;
	}

	public void setDrugNum(double drugNum) {
		this.drugNum = drugNum;
	}

	public int getUseableId() {
		return useableId;
	}

	public void setUseableId(int useableId) {
		this.useableId = useableId;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

}
