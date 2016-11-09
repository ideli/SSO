/**
 * 
 */
package com.hisign.sso.web.shiro.authc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义认证结果监听器
 * 为了在日志输出认证结果
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class DzptAuthenticationListener implements AuthenticationListener {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(DzptAuthenticationListener.class);

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.AuthenticationListener#onSuccess(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		logger.debug("onSuccess,token.Principal:{},token.Credentials:{},info.Principals:{},info.Credentials:{}",
				token.getPrincipal(), token.getCredentials(), info.getPrincipals(), info.getCredentials());

	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.AuthenticationListener#onFailure(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationException)
	 */
	@Override
	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
		logger.debug("onFailure,token.Principal:{},token.Credentials:{},ae:{}", token.getPrincipal(),
				token.getCredentials(), ae.toString());

	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.AuthenticationListener#onLogout(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public void onLogout(PrincipalCollection principals) {
		logger.debug("onLogout,principals:{}", principals);

	}

}
