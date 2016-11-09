/**
 * 
 */
package com.hisign.sso.api.rest.entity;

/**
 * 响应信息节点
 * @author chailiangzhi
 * @date 2015-12-23
 * 
 */
public class Response {

	/**
	 * 应答类型,成功0,失败1
	 */
	private String rspType;

	/**
	 * 应答代码,
	 * 0000,成功
	 * 1001,系统错误,rspDesc提供具体错误描述
	 * 1002,token认证失败
	 * 1003,token过期
	 * 1004,业务受理失败,rspDesc提供具体原因
	 */
	private String rspCode;

	/**
	 * 应答描述
	 */
	private String rspDesc;

	/**
	 * @return the rspType
	 */
	public String getRspType() {
		return rspType;
	}

	/**
	 * @param rspType the rspType to set
	 */
	public void setRspType(String rspType) {
		this.rspType = rspType;
	}

	/**
	 * @return the rspCode
	 */
	public String getRspCode() {
		return rspCode;
	}

	/**
	 * @param rspCode the rspCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	/**
	 * @return the rspDesc
	 */
	public String getRspDesc() {
		return rspDesc;
	}

	/**
	 * @param rspDesc the rspDesc to set
	 */
	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}

}
