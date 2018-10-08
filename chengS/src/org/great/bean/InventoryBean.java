/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：InventoryBean.java
 * 
 * 创建时间：2018年9月17日 下午3:55:03
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

import org.springframework.stereotype.Component;

/**
 * @ClassName: InventoryBean
 * @Description: 库存表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午3:55:03
 */
@Component
public class InventoryBean {
	private int inventoryId;// 库存ID
	private int drugId;// 药品编码
	private int inventoryNum;// 库存量
	private long minimum;// 低限值
	private long maximum;//上限值
	private StoDrugBean stoDrugBean;//药品信息
	public InventoryBean() {
		super();
	}

	

	public StoDrugBean getStoDrugBean() {
		return stoDrugBean;
	}

	public void setStoDrugBean(StoDrugBean stoDrugBean) {
		this.stoDrugBean = stoDrugBean;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public int getDrugId() {
		return drugId;
	}

	public int getInventoryNum() {
		return inventoryNum;
	}

	
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public void setInventoryNum(int inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	

	public long getMinimum() {
		return minimum;
	}



	public void setMinimum(long minimum) {
		this.minimum = minimum;
	}



	public long getMaximum() {
		return maximum;
	}



	public void setMaximum(long maximum) {
		this.maximum = maximum;
	}



	@Override
	public String toString() {
		return "InventoryBean [inventoryId=" + inventoryId + ", drugId=" + drugId + ", inventoryNum=" + inventoryNum
				+ ", minimum=" + minimum + "]";
	}

}
