/**
 * 
 */
package com.hisign.sso.web.shiro.authc;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;

/**
 * 自定义认证管理器
 * 为了设置认证策略和设置认证结果监听器
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class DzptModularRealmAuthenticator extends ModularRealmAuthenticator {

	/**
	 * 构造函数
	 */
	public DzptModularRealmAuthenticator() {
		// 不调用父构造器,因为父构造器默认使用AtLeastOneSuccessfulStrategy,
		// 这里设置为FirstSuccessfulStrategy,
		// 返回第一个认证成功的realm
		super.setAuthenticationStrategy(new FirstSuccessfulStrategy());
	}

	/**
	 * 设置认证结果监听器
	 * @param listeners
	 */
	public void setListeners(Collection<AuthenticationListener> listeners) {
		// TODO Auto-generated method stub
		super.setAuthenticationListeners(listeners);
	}

}
