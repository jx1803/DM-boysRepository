/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.bean
 * 文件名称：DrugTypeBean.java
 * 
 * 创建时间：2018年9月17日 下午3:44:09
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.bean;

import org.springframework.stereotype.Component;

/**
 * @ClassName: DrugTypeBean
 * @Description: 药品分类表实体类
 * @author: 周志展(lenovo)
 * @date: 2018年9月17日 下午3:44:09
 */
@Component
public class DrugTypeBean {
	private int typeId;// 类别ID
	private int pid;// pid
	private String drugType;// 药品分类名
	private String pidName;	//pid名
	public DrugTypeBean() {
		super();
	}

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public int getTypeId() {
		return typeId;
	}

	public int getPid() {
		return pid;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	@Override
	public String toString() {
		return "DrugTypeBean [typeId=" + typeId + ", pid=" + pid + ", drugType=" + drugType + "]";
	}

}
