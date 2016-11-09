/**
 * 
 */
package com.hisign.sso.api.rest.entity;

/**
 * REST请求头
 * @author chailiangzhi
 * @date 2015-12-23
 * 
 */
public class ReqHead {

	/**
	 * 请求流水(接收方应答时原值回填(发起方的请求流水号))
	 */
	private String reqSn;

	/**
	 * 请求时间(yyyymmddhh24miss)
	 */
	private String reqTime;

	/**
	 * 服务编码
	 */
	private String apiCode;

	/**
	 * 系统编码
	 */
	private String sysCode;

	/**
	* 秘钥
	*/
	private String secretKey;
	/**
	 * token
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the reqSn
	 */
	public String getReqSn() {
		return reqSn;
	}

	/**
	 * @param reqSn the reqSn to set
	 */
	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
	}

	/**
	 * @return the reqTime
	 */
	public String getReqTime() {
		return reqTime;
	}

	/**
	 * @param reqTime the reqTime to set
	 */
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * @return the apiCode
	 */
	public String getApiCode() {
		return apiCode;
	}

	/**
	 * @param apiCode the apiCode to set
	 */
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
