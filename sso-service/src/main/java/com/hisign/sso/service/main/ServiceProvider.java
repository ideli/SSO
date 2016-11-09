/**
 * 服务层启动主类
 */
package com.hisign.sso.service.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.common.Constant;

/**
 * 服务层启动主类
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public class ServiceProvider {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

	/**
	 * 
	 */
	private static void initPara() {
		SysConfigLoader.getInstance().loadSysConfig(Constant.SYSTEM_ID);
		// System.setProperty("dubbo.registry.address", "zookeeper://172.16.0.114:52181");
		// System.setProperty("rest_port", "8811");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initPara();
		com.alibaba.dubbo.container.Main.main(null);

	}
}
