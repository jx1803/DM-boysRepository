package org.great.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TakeStockBean {
	private long stockId;//盘点记录id
	private int drugId;//药品编号
	private int paramId;//盈亏参数id
	private int outStorage;//计算机统计药品出库数量
	private int putStorage;//计算机统计药品入库数量
	private int drugNum;//计算机统计药品库存
	private int realNum;//实际统计药品的数量
	private int adminId;//操作人员
	private String checkDate;//盘点录入时间
	private StoDrugBean stoDrugBean;//药品信息对象
	private AdminBean adminBean;//管理员对象
	private ParamBean paramBean;//参数对象
	private List<TakeStockBean> stockList;
	public TakeStockBean() {
		// TODO Auto-generated constructor stub
	}
	public long getStockId() {
		return stockId;
	}
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public int getParamId() {
		return paramId;
	}
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	public int getOutStorage() {
		return outStorage;
	}
	public void setOutStorage(int outStorage) {
		this.outStorage = outStorage;
	}
	public int getPutStorage() {
		return putStorage;
	}
	public void setPutStorage(int putStorage) {
		this.putStorage = putStorage;
	}
	public int getDrugNum() {
		return drugNum;
	}
	public void setDrugNum(int drugNum) {
		this.drugNum = drugNum;
	}
	public int getRealNum() {
		return realNum;
	}
	public void setRealNum(int realNum) {
		this.realNum = realNum;
	}
	public StoDrugBean getStoDrugBean() {
		return stoDrugBean;
	}
	public void setStoDrugBean(StoDrugBean stoDrugBean) {
		this.stoDrugBean = stoDrugBean;
	}
	public AdminBean getAdminBean() {
		return adminBean;
	}
	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public ParamBean getParamBean() {
		return paramBean;
	}
	public void setParamBean(ParamBean paramBean) {
		this.paramBean = paramBean;
	}
	public List<TakeStockBean> getStockList() {
		return stockList;
	}
	public void setStockList(List<TakeStockBean> stockList) {
		this.stockList = stockList;
	}
	
}
