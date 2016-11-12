package com.hisign.sso.service.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.api.constant.UAOPConstant;

/**
 * @Title:
 *  操作日志记录服务启动
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月22日  下午5:56:02
 */
public class LogServiceProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(LogServiceProvider.class);

	
	private static void initPara() {
		SysConfigLoader.getInstance().loadSysConfig(UAOPConstant.SYSTEMID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initPara();
		com.alibaba.dubbo.container.Main.main(null);
	}

}
