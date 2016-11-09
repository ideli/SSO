package com.hisign.sso.web.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.sdk.api.entity.CacheKey;
import com.hisign.sdk.api.enums.CacheKeyType;
import com.hisign.sdk.api.service.CacheService;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.service.sys.UserService;
import com.hisign.sso.common.Constant;
import com.hisign.sso.common.util.LogUtil;

/**
 * 实现初始化认证信息和获取角色权限信息
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class UserRealm extends AuthorizingRealm {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

	/**
	 * 访问后台数据库的用户信息
	 */
	@Autowired
	private UserService userService;

	/**
	 * 访问缓存服务
	 */
	@Autowired
	private CacheService cacheService;

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		logger.debug("doGetAuthorizationInfo,username:{}", username);

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		CacheKey key = new CacheKey();
		key.setKey(username);
		key.setType(CacheKeyType.PERMISSION);
		Set<String> permissions = cacheService.get(key, Set.class);

		authorizationInfo.setStringPermissions(permissions);

		return authorizationInfo;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

		SysUser userFound = null;
		try {
			userFound = null;//userService.getByAccount(username);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

		if (userFound == null) {
			logger.debug("用户不存在:{}", username);
			// 没找到帐号
			throw new UnknownAccountException("用户不存在");
		}

		if (!Constant.USER_OPEN.equals(userFound.getStatus())) {
			// 帐号锁定
			throw new LockedAccountException();
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, //用户名
				userFound.getPass(), //密码
				getName() //realm name
		);
		return authenticationInfo;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#clearCachedAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#clearCachedAuthenticationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.CachingRealm#clearCache(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	/**
	 * 清除所有授权信息的缓存
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 清除所有认证信息的缓存
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	/**
	 * 清除所有认证和授权信息缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
