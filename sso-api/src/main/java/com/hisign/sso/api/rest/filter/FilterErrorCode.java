/**
 * 
 */
package com.hisign.sso.api.rest.filter;

/**
 * 过滤器返回的错误代码和描述定义
 * @author chailiangzhi
 * @date 2016-7-18
 * 
 */
public enum FilterErrorCode {

	// 利用构造函数传参
	/**
	 * 成功
	 */
	SUCCESS("0000", "成功"), //

	/**
	 * token检查失败
	 */
	TOKEN_FAIL("1001", "token检查失败"), //

	/**
	 * 系统编码不正确
	 */
	SYSCODE_FAIL("1002", "系统编码不正确"); //

	/**
	 * 构造函数，枚举类型只能为私有
	 * @param name
	 * @param ordinal
	 */
	private FilterErrorCode(String errCode, String errDesc) {
		this.errCode = errCode;
		this.errDesc = errDesc;
	}

	// 
	/**
	 * 错误代码
	 */
	private String errCode;

	//
	/**
	 * 错误描述
	 */
	private String errDesc;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	// 
}
