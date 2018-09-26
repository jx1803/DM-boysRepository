package org.great.bean;

public class CondiBean {
	/**
	 * 申请记录搜索条件
	 * 陈文炽
	 */
	private int pageNum=1;//当前页数
	private int pageLimit=5;//每页显示记录数
	private String adminName;//用户名
	private String afterDate;//之后的日期
	private String beforeDate;//之前的日期
	private int checkId;//审核情况id
	private int applyTypeId;// 请领/退库/报损/采购id
	private String pinyinCode;//拼音码
	private String drugName;//药品名
	private String manuBatch;//厂家批号
	private int useableId;//药房药品可用状态2：启用，3：禁用
	private int miniId;//药品数量是否低于低限值，0：否，1：低于低限
	private int drugId;//药品编号
	/**
	 * 
	 * 用户搜索条件
	 * 王伟国
	 */
	private String entrytime;// 入职时间
	private String quitDate;// 离职时间
	private int page=1;
	private int pageup;//上一页
	private int pagedown;//下一页
	
	public CondiBean() {
		// TODO Auto-generated constructor stub
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getManuBatch() {
		return manuBatch;
	}

	public void setManuBatch(String manuBatch) {
		this.manuBatch = manuBatch;
	}

	public String getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int getApplyTypeId() {
		return applyTypeId;
	}

	public void setApplyTypeId(int applyTypeId) {
		this.applyTypeId = applyTypeId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
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

	public int getUseableId() {
		return useableId;
	}

	public void setUseableId(int useableId) {
		this.useableId = useableId;
	}

	public int getMiniId() {
		return miniId;
	}

	public void setMiniId(int miniId) {
		this.miniId = miniId;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	
	

}
