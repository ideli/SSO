/**
 * 
 */
package com.hisign.sso.api.rest.entity;

/**
 * REST响应头
 * @author chailiangzhi
 * @date 2015-12-23
 * 
 */
public class RspHead {

	/**
	 * 接收方应答时原值回填发起方的请求流水号
	 */
	private String reqSn;

	/**
	 * 响应时间(yyyymmddhh24miss)
	 */
	private String rspTime;

	/**
	 * 响应信息节点
	 */
	private Response response;

	/**
	* 秘钥
	*/
	private String secretKey;

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
	 * @return the rspTime
	 */
	public String getRspTime() {
		return rspTime;
	}

	/**
	 * @param rspTime the rspTime to set
	 */
	public void setRspTime(String rspTime) {
		this.rspTime = rspTime;
	}

	/**
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response) {
		this.response = response;
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
