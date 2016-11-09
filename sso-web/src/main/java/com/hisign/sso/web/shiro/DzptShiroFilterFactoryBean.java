/**
 * 
 */
package com.hisign.sso.web.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Shiro过滤器工厂类
 * 为了从数据库动态加载URL和权限的关系配置
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class DzptShiroFilterFactoryBean extends ShiroFilterFactoryBean {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(DzptShiroFilterFactoryBean.class);

	/* (non-Javadoc)
	 * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean#createFilterChainManager()
	 */
	@Override
	protected FilterChainManager createFilterChainManager() {
		FilterChainManager manager = super.createFilterChainManager();
		// 从数据库查找资源或权限代码与资源URL的关联关系
		// 循环数据库菜单资源表的url,逐个添加到过滤器链中。
		// "perms"是过滤器代码，表示访问URL时
		// 通过org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter决定是否有权限
		//String url = "/user/view.html";
		// resKey是URL对应的资源编码,也是权限编码
		//String resKey = "RES_user";
		//manager.addToChain(url, "perms", resKey);
		manager.addToChain("/**", "user", null);
		logger.debug("createFilterChainManager()");
		return manager;
	}

}
