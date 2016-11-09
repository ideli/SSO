package com.hisign.sso.api.entity.sys;

import java.util.Date;

import com.hisign.sso.api.rest.entity.sys.User;

/**
 * TOKEN记录表
 * @author chailiangzhi
 * @date 2016-6-29
 * 
 */
public class LogToken implements java.io.Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private String token;

	//
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	*
	*/
	private String account;

	/**
	*
	*/
	private String sysCode;

	/**
	*
	*/
	private String tokenTime;

	/**
	*
	*/
	private String randomVal;

	/**
	*
	*/
	private String effectiveTime;

	/**
	*
	*/
	private String invalidTime;

	/**
	*
	*/
	private Date createDate;
	
	/**
	 * 用户对象
	 */
	private User user;

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getTokenTime() {
		return this.tokenTime;
	}

	public void setTokenTime(String tokenTime) {
		this.tokenTime = tokenTime;
	}

	public String getRandomVal() {
		return this.randomVal;
	}

	public void setRandomVal(String randomVal) {
		this.randomVal = randomVal;
	}

	public String getEffectiveTime() {
		return this.effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getInvalidTime() {
		return this.invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}