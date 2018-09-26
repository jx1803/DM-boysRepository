/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：DfBean.java
 * 
 * 创建时间：2018年9月17日 下午3:59:35
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

import org.springframework.stereotype.Component;

/** 
 * @ClassName: DfBean 
 * @Description: 剂型实体类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月17日 下午3:59:35  
 */
@Component
public class DfBean {
	private int dosageId;//剂型ID
	private String dosageForm;//剂型名称
	public DfBean() {
		super();
	}
	public int getDosageId() {
		return dosageId;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageId(int dosageId) {
		this.dosageId = dosageId;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	@Override
	public String toString() {
		return "DfBean [dosageId=" + dosageId + ", dosageForm=" + dosageForm + "]";
	}
	
	

}
