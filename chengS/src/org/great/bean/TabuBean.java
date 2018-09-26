package org.great.bean;

//配伍禁忌表
public class TabuBean {
	private int tabuId;// 配伍禁忌id
	private String drugId1;// 药品1
	private String drugId2;// 药品2
	private String sideeffect;// 产生作用

	public TabuBean() {
		super();
	}

	public int getTabuId() {
		return tabuId;
	}

	public void setTabuId(int tabuId) {
		this.tabuId = tabuId;
	}

	public String getDrugId1() {
		return drugId1;
	}

	public void setDrugId1(String drugId1) {
		this.drugId1 = drugId1;
	}

	public String getDrugId2() {
		return drugId2;
	}

	public void setDrugId2(String drugId2) {
		this.drugId2 = drugId2;
	}

	public String getSideeffect() {
		return sideeffect;
	}

	public void setSideeffect(String sideeffect) {
		this.sideeffect = sideeffect;
	}

}
