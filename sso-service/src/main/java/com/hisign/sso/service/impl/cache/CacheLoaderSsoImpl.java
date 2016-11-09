/**
 * 
 */
package com.hisign.sso.service.impl.cache;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hisign.sdk.api.entity.CacheKey;
import com.hisign.sdk.api.service.CacheLoader;

/**
 * 从数据库加载需要缓存的数据
 * @author chailiangzhi
 * @date 2016-6-14
 * 
 */
@Service("cacheLoader")
public class CacheLoaderSsoImpl implements CacheLoader {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 应用使用的缓存键
	 */
	CacheKey key;

	/**
	 * 实际在缓存中使用的缓存键
	 */
	String realKey;

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Object call() throws Exception {
		logger.debug("loader call()");
		switch (key.getType()) {
		case PERMISSION:
			String username = key.getKey();
			// 正式使用时从数据库查找用户的权限
			Set<String> permissions = new HashSet<String>();
			permissions.add("RES_1");
			permissions.add("RES_2");
			permissions.add("RES_fileList");
			return permissions;
		default:
			return null;
		}
	}

	/**
	 * 初始化属性的函数
	 * @param key
	 * @param realKey
	 */
	@Override
	public void init(CacheKey key, String realKey) {
		this.key = key;
		this.realKey = realKey;
	}

}
