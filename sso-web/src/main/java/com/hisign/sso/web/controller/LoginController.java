/**
 * 
 */
package com.hisign.sso.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hisign.sso.api.service.sys.ShiroLoginService;
import com.hisign.sso.common.util.LogUtil;

/**
 * 用户登录
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
@Controller
@RequestMapping("/")
public class LoginController {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 登录服务，由SSO服务提供
	 * dubbo服务，RMI协议
	 */
	@Autowired
	private ShiroLoginService loginService;

	/**
	 * 登录界面
	 * @return
	 */
	@RequestMapping("login")
	public String login(String serviceUrl, Model model) {
		logger.debug("public String login()");
		model.addAttribute("serviceUrl", serviceUrl);
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		// 如果用户已登录且是单点登录请求：
		if (principal != null && !StringUtils.isEmpty(serviceUrl)) {
			String username = principal.toString();
			String ticket = loginService.genTicket(username);
			return "redirect:" + serviceUrl + "?ticket=" + ticket;
		} else {
			return "login";
		}
	}

	/**
	 * 提交登录的用户名和密码
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "postLogin", method = RequestMethod.POST)
	public String postLogin(String serviceUrl, String username, String password) {
		logger.debug("public String postLogin()");
		// 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			if (!StringUtils.isEmpty(serviceUrl)) {
				String ticket = loginService.genTicket(username);
				return "redirect:" + serviceUrl + "?ticket=" + ticket;
			} else {
				return "index";
			}
		} catch (AuthenticationException e) {
			if (e instanceof LockedAccountException) {
				logger.debug("账号被锁定:{}", username);
			}
			LogUtil.errStack2Log4j(e);
			return "loginfail";
		}
	}
}
