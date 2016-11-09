/**
 * 
 */
package com.hisign.sso.api.rest.filter;

/**
 * 请求上下文
 * 与请求绑定的变量
 * @author chailiangzhi
 * @date 2016-8-24
 * 
 */
public class RequestContext {
	//
	/**
	 * 创建线程局部变量requestIp，用来保存HTTP请求的源IP
	 */
	private static ThreadLocal<String> requestIpLocal = new ThreadLocal<String>();

	/**
	 * 创建线程局部变量loginUserIdLocal，用来保存登录用户ID
	 */
	private static ThreadLocal<String> loginUserIdLocal = new ThreadLocal<String>();
	
	/**
	 * 创建线程局部变量loginAccountLocal，用来保存登录用户Account
	 */
	private static ThreadLocal<String> loginAccountLocal = new ThreadLocal<String>();
	
	/**
	 * 创建线程局部变量loginAccountLocal，用来保存登录用户SystemId
	 */
	private static ThreadLocal<String> loginSystemIdLocal = new ThreadLocal<String>();

	/**
	 * @return the requestIp
	 */
	public static String getRequestIp() {
		return requestIpLocal.get();
	}

	/**
	 * @param requestIp the requestIp to set
	 */
	protected static void setRequestIp(String requestIp) {
		RequestContext.requestIpLocal.set(requestIp);
	}

	/**
	 * @return the loginUserId
	 */
	public static String getLoginUserId() {
		return loginUserIdLocal.get();
	}

	/**
	 * @param loginUserId the loginUserId to set
	 */
	protected static void setLoginUserId(String loginUserId) {
		loginUserIdLocal.set(loginUserId);
	}
	
	
	/**
	 * @return the loginAccount
	 */
	public static String getLoginAccount() {
		return loginAccountLocal.get();
	}

	/**
	 * @param loginAccount the loginAccount to set
	 */
	protected static void setLoginAccount(String loginAccount) {
		loginAccountLocal.set(loginAccount);
	}
	
	/**
	 * @return the systemId
	 */
	public static String getSystemId() {
		return loginSystemIdLocal.get();
	}

	/**
	 * @param systemId the systemId to set
	 */
	protected static void setSystemId(String systemId) {
		loginSystemIdLocal.set(systemId);
	}

}
