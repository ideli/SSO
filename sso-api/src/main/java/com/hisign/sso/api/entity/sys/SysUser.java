package com.hisign.sso.api.entity.sys;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * 单点登录系统账号表
 * @author chailiangzhi
 * @date 2016-6-28
 * 
 */
public class SysUser extends SysBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	//账号
	private String account;

	//用户名
	private String userName;

	//密码
	private String pass;

	//用户ID
	private String userId;

	//用户状态
	private int status;

	//用户类型
	private int userType;
	
	//用户启用状态  1:启用 0:禁止
	private int activeStatus;

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

}