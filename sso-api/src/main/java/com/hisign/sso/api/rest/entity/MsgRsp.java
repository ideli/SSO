/**
 * 
 */
package com.hisign.sso.api.rest.entity;

/**
 * @author chailiangzhi
 * @date 2016-5-6
 * 通用响应实体
 */
public class MsgRsp {

	/**
	 * 响应头
	 */
	private RspHead rspHead;

	/**
	 * 响应体(加密后的字符串)
	 */
	private String rspBody;

	/**
	 * @return the rspHead
	 */
	public RspHead getRspHead() {
		return rspHead;
	}

	/**
	 * @param rspHead the rspHead to set
	 */
	public void setRspHead(RspHead rspHead) {
		this.rspHead = rspHead;
	}

	/**
	 * @return the rspBody
	 */
	public String getRspBody() {
		return rspBody;
	}

	/**
	 * @param rspBody the rspBody to set
	 */
	public void setRspBody(String rspBody) {
		this.rspBody = rspBody;
	}

}
