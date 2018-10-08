package org.great.bean;

public class WarnBean {
	private int warnId;// 提醒id
	private String warnDate;// 提醒日期
	private String warnEvent;// 提醒事件
	private int eventId;// 属于什么提醒/低限/过期/滞销
	private String eventName;// 事件名称
	private int disposeId;// 处理情况/已处理/未处理
	private String disposeName;// 处理情况名
	private int belongId;//处于药房或药库的消息

	public WarnBean() {
		super();
	}

	public int getWarnId() {
		return warnId;
	}

	public void setWarnId(int warnId) {
		this.warnId = warnId;
	}

	public String getWarnDate() {
		return warnDate;
	}

	public void setWarnDate(String warnDate) {
		this.warnDate = warnDate;
	}

	public String getWarnEvent() {
		return warnEvent;
	}

	public void setWarnEvent(String warnEvent) {
		this.warnEvent = warnEvent;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getDisposeId() {
		return disposeId;
	}

	public void setDisposeId(int disposeId) {
		this.disposeId = disposeId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDisposeName() {
		return disposeName;
	}

	public void setDisposeName(String disposeName) {
		this.disposeName = disposeName;
	}

	public int getBelongId() {
		return belongId;
	}

	public void setBelongId(int belongId) {
		this.belongId = belongId;
	}

}
