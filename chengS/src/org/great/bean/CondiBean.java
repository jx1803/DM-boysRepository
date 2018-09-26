package org.great.bean;
/**
 * 
 * @ClassName:  条件实体类  
 * @Description:接收页面过来的搜索条件 
 * @author: 程帅 
 * @date:  2018年9月19日 下午1:51:09   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
public class CondiBean {
	
	private String dosageForm;	//药品剂型名称
	private String drugType;	//药品类型名称
	
	private String drugName;	//药品名称
	private String pinyinCode;	//拼音码
	private int drugId;			//药品编码
	private int typeId;			//药品分类id
	private String drugId1;		//药品名
	
	private int page=1;//分页初始值
	private int pageup;//最后一条
	private int pagedown;//第一条
	public CondiBean() {
		// TODO Auto-generated constructor stub
	}
	

	public String getDrugId1() {
		return drugId1;
	}


	public void setDrugId1(String drugId1) {
		this.drugId1 = drugId1;
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public String getPinyinCode() {
		return pinyinCode;
	}


	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}


	public int getDrugId() {
		return drugId;
	}


	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public String getDrugType() {
		return drugType;
	}


	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}


	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageup() {
		return pageup;
	}

	public void setPageup(int pageup) {
		this.pageup = pageup;
	}

	public int getPagedown() {
		return pagedown;
	}

	public void setPagedown(int pagedown) {
		this.pagedown = pagedown;
	}
	
	
}
