/**
 * 
 */
package com.hisign.sso.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.common.Constant;

/**
 * 在spring容器初始化之前从数据库加载参数
 * @author chailiangzhi
 * @date 2016-6-27
 * 
 */
public class LoadConfigListener implements ServletContextListener {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		SysConfigLoader.getInstance().loadSysConfig(Constant.SYSTEM_ID);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
