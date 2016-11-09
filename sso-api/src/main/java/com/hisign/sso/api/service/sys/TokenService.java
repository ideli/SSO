/**
 * 
 */
package com.hisign.sso.api.service.sys;

/**
 * 3.2	TOKEN服务
 * @author chailiangzhi
 * @date 2016-6-23
 * 
 */
public interface TokenService {

	/**
	 * 返回代码
	 */
	public static final String RSP_CODE = "rspCode";

	/**
	 * 返回用户ID
	 */
	public static final String USER_ID = "userId";
	
	/**
	 * 返回用户ID
	 */
	public static final String ACCOUNT = "account";

	/**
	 * 返回登录地址
	 */
	public static final String LOGIN_REST_URL = "loginRestUrl";

	/**
	 * TOKEN有效
	 */
	public static final String TOKEN_VALID = "0000";

	/**
	 * TOKEN不存在
	 */
	public static final String TOKEN_NOT_EXIST = "1001";

	/**
	 * TOKEN过期
	 */
	public static final String TOKEN_EXPIRE = "1002";

	/**
	 * 其他错误
	 */
	public static final String OTHER_ERROR = "2000";

	/**
	 * TOKEN校验
	 * 检查TOKEN是否有效，是否过期，
	 * 是否有权限访问对应服务
	 * @param token
	 * @param serviceCode
	 * @return 返回JSON字符串
	 * 
	 */
	public String checkToken(String token, String sysCode, String serviceCode);
}
