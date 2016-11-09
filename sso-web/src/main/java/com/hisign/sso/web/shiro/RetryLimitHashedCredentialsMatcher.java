package com.hisign.sso.web.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 带限制连续输错密码次数功能的密码比较类
 * 系统提供的HashedCredentialsMatcher会把前台传过来的明文哈希后再和后台数据库提供的进行比较
 * 无需开发者进行自定义的明文密码哈希
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	/**
	 * 连续密码错误重试次数记录缓存
	 */
	private Cache<String, AtomicInteger> passwordRetryCache;

	/**
	 * @param cacheManager
	 */
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.credential.HashedCredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		String cacehKey = username + "_passwordRetry";
		//retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(cacehKey);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(cacehKey, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			//if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			//clear retry count
			passwordRetryCache.remove(cacehKey);
		}
		return matches;
	}
}
