/**
 * 
 */
package com.hisign.sso.web.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 信任用户持有的token
 * 用于单点登录或者通过cookie保存登录信息
 * @author chailiangzhi
 * @date 2015-10-15
 * 
 */
public class TrustUserToken implements AuthenticationToken {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 构造函数设置用户名
	 * @param username
	 */
	public TrustUserToken(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return username;
	}

	/**
	 * 单点登录或者通过cookie保存登录信息不需要密码
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

}
