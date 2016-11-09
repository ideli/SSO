/**
 * 
 */
package com.hisign.sso.api.rest.entity;

/**
 * @author chailiangzhi
 * @date 2016-5-6
 * 通用请求实体
 */
public class MsgReq {

	/**
	 * 请求头
	 */
	private ReqHead reqHead;

	/**
	 * 请求体(加密后的字符串)
	 */
	private String reqBody;

	/**
	 * @return the reqHead
	 */
	public ReqHead getReqHead() {
		return reqHead;
	}

	/**
	 * @param reqHead the reqHead to set
	 */
	public void setReqHead(ReqHead reqHead) {
		this.reqHead = reqHead;
	}

	/**
	 * @return the reqBody
	 */
	public String getReqBody() {
		return reqBody;
	}

	/**
	 * @param reqBody the reqBody to set
	 */
	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}

}
