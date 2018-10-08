package org.great.bean;

import org.springframework.stereotype.Component;

//参数表
@Component
public class ParamBean {
	private int paramId;//参数id
	private String param;//参数名
	private int pid;//父id

	private String fname;//父类参数名
	public ParamBean() {
		super();
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
