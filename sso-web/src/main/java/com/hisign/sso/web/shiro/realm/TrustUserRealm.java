/**
 * 
 */
package com.hisign.sso.web.shiro.realm;

import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.web.shiro.token.TrustUserToken;

/**
 * 信任用户领域
 * @author chailiangzhi
 * @date 2015-10-15
 * 
 */
public class TrustUserRealm extends UserRealm {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(TrustUserRealm.class);

	/**
	 * 构造函数
	 */
	public TrustUserRealm() {
		logger.debug("TrustUserRealm()");
		// 设置无需凭证，因为从cookie得知用户已登录
		setCredentialsMatcher(new AllowAllCredentialsMatcher());
		// 设置token为我们自定义的  
		// 只支持持有信任Token的用户进行无密码登录
		setAuthenticationTokenClass(TrustUserToken.class);
	}

}
