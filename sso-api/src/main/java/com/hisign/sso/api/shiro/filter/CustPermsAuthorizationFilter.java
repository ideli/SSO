package com.hisign.sso.api.shiro.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.sdk.config.service.SysParamService;
import com.hisign.sso.api.service.sys.ShiroLoginService;
import com.hisign.sso.api.shiro.token.TrustUserToken;

/**
 * 子系统shiro权限过滤器。
 * 如果用户登录且有对应URL的权限：通过；
 * 如果用户未登录：
 *   检查request中是否有ticket参数：
 *     如果有ticket：
 *       根据ticket去Redis缓存获取用户名（获取后移除缓存中的ticket）并登录（登录失败抛运行时异常，正常情况不会出现ticket登录失败），
 *       检查权限（无权限显示无权限提示界面），
 *       登录成功且有权限：通过；
 *     无ticket：跳转到单点登录界面。
 * @author chailiangzhi
 * @date 2016-6-8
 * 
 */
public class CustPermsAuthorizationFilter extends PermissionsAuthorizationFilter {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 登录服务，由SSO服务提供
	 * dubbo服务，RMI协议
	 */
	@Autowired
	private ShiroLoginService loginService;

	/**
	 * 
	 */
	SysParamService sysParamService;

	/**
	 * @param sysParamService the sysParamService to set
	 */
	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		// TODO Auto-generated method stub
		// 
		Subject subject = getSubject(request, response);
		// 如果用户未登录：
		Object principal = subject.getPrincipal();
		if (principal == null) {
			String ticket = WebUtils.getCleanParam(request, "ticket");
			// 如果有ticket：
			if (ticket != null) {
				String username = loginService.getUsernameByTicket(ticket);
				TrustUserToken trustToken = new TrustUserToken(username);
				subject.login(trustToken);
			}
		}
		principal = subject.getPrincipal();
		return principal != null && super.isAccessAllowed(request, response, mappedValue);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authz.AuthorizationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//		return super.onAccessDenied(request, response);
		Subject subject = getSubject(request, response);
		// If the subject isn't identified, redirect to login URL
		if (subject.getPrincipal() == null) {
			Map<String, String> keyValues = sysParamService.getProperties("UAOP", "SSO");
			String loginUrl = keyValues.get("LOGIN_URL");
			logger.info("loginUrl={}", loginUrl);
			//http://192.168.40.30:9090/sso/login.html
			WebUtils.issueRedirect(request, response, loginUrl + "?serviceUrl="
					+ WebUtils.toHttp(request).getRequestURL().toString());
		} else {
			// If subject is known but not authorized, redirect to the unauthorized URL if there is one
			// If no unauthorized URL is specified, just return an unauthorized HTTP status code
			String unauthorizedUrl = getUnauthorizedUrl();
			//SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
			if (StringUtils.hasText(unauthorizedUrl)) {
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}

}
