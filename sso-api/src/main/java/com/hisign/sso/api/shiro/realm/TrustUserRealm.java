package com.hisign.sso.api.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.sso.api.service.sys.ShiroLoginService;
import com.hisign.sso.api.shiro.token.TrustUserToken;

/**
 * 信任用户领域
 * @author chailiangzhi
 * @date 2015-10-15
 * 
 */
public class TrustUserRealm extends AuthorizingRealm {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(TrustUserRealm.class);

	/**
	 * 登录服务，由SSO服务提供
	 * dubbo服务，RMI协议
	 */
	@Autowired
	private ShiroLoginService loginService;

	/**
	 * 业务系统唯一编号，如：XCKY
	 */
	private String sysCode;

	/**
	 * 权限代码前缀
	 */
	private String permCodePrefix = "";

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @param permCodePrefix the permCodePrefix to set
	 */
	public void setPermCodePrefix(String permCodePrefix) {
		this.permCodePrefix = permCodePrefix;
	}

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

	/* (non-Javadoc)
	 * @see com.hisign.wf.web.shiro.realm.UserRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> permissionsOld = loginService.findPermissions(sysCode, username);
		Set<String> permissions = new HashSet<String>();
		// 
		StringBuilder sb = new StringBuilder();
		for (String permission : permissionsOld) {
			sb.append(permission);
			permissions.add(permCodePrefix + permission);
		}
		logger.debug("permissions={}", sb.toString());

		authorizationInfo.setStringPermissions(permissions);

		return authorizationInfo;
	}

	/* (non-Javadoc)
	 * @see com.hisign.wf.web.shiro.realm.UserRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, //用户名
				"NOT NEED", //密码
				getName() //realm name
		);
		return authenticationInfo;
	}

}
