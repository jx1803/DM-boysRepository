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
	private int page=1;//分页初始值
	private int pageup;//最后一条
	private int pagedown;//第一条
	public CondiBean() {
		// TODO Auto-generated constructor stub
	}

	public CondiBean(String dosageForm) {
		super();
		this.dosageForm = dosageForm;
	}

	public CondiBean(String dosageForm, int page, int pageup, int pagedown) {
		super();
		this.dosageForm = dosageForm;
		this.page = page;
		this.pageup = pageup;
		this.pagedown = pagedown;
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
