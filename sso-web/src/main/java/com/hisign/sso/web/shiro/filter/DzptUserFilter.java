/**
 * 
 */
package com.hisign.sso.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义登录用户过滤器
 * 继承默认的UserFilter只是为了添加一些日志
 * @author chailiangzhi
 * @date 2015-10-14
 * 
 */
public class DzptUserFilter extends UserFilter {

	/**
	 * 日志实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(DzptUserFilter.class);

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.UserFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		boolean isAllow = super.isAccessAllowed(request, response, mappedValue);
		if (isAllow) {
			return true;
		} else {
			logger.debug("url={},not AccessAllowed", url);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.UserFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		logger.debug("url={},RedirectToLogin", url);
		return super.onAccessDenied(request, response);
	}

}
