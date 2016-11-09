package com.hisign.sso.api.entity.sys;

import java.util.*;

public class LogRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;

	private String systemId;

	private String account;

	private String userName;

	private String terminalId;

	private String moduleId;

	private String operateId;

	private long operateTime;

	private int operateResult;

	private String note;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSystemId() {
		return systemId;
	}
	
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	public int getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(int operateResult) {
		this.operateResult = operateResult;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogRecord [id=");
		builder.append(id);
		builder.append(", systemId=");
		builder.append(systemId);
		builder.append(", account=");
		builder.append(account);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", terminalId=");
		builder.append(terminalId);
		builder.append(", moduleId=");
		builder.append(moduleId);
		builder.append(", operateId=");
		builder.append(operateId);
		builder.append(", operateTime=");
		builder.append(operateTime);
		builder.append(", operateResult=");
		builder.append(operateResult);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}