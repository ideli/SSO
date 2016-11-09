/**
 * 
 */
package com.hisign.sso.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 界面
	 * @return
	 */
	@RequestMapping("view")
	public String view(Model model) {
		logger.debug("public String view()");
		return "view";
	}

}
