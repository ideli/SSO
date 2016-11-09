/**
 * 
 */
package com.hisign.sso.api.rest.exception;

import javax.ws.rs.core.Response;

/**
 * REST业务异常类
 * @author chailiangzhi
 * @date 2016-8-12
 * 
 */
public class RestBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * HTTP状态码
	 */
	private Response.Status statusCode;

	/**
	 * @param message
	 * @param cause
	 */
	public RestBusinessException(Response.Status statusCode, String message, Throwable cause) {
		super(message, cause);

		this.statusCode = statusCode;
	}

	/**
	 * @param message
	 */
	public RestBusinessException(Response.Status statusCode, String message) {
		super(message);

		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public Response.Status getStatusCode() {
		return statusCode;
	}

}
