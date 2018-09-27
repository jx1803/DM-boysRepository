package org.great.bean;

import org.springframework.stereotype.Component;

//参数表
@Component
public class ParamBean {
	private int paramId;//参数id
	private String param;//参数名
	private int pid;//父id

	public ParamBean() {
		super();
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
