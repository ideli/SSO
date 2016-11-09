/**
 * 
 */
package com.hisign.sso.common.util;

import org.slf4j.LoggerFactory;

/**
 * 日志工具
 * @author chailiangzhi
 * @date 2015-7-13
 * 
 */
public class LogUtil {
	/**
	 * 在Log4j中输出异常堆栈
	 * @param e
	 */
	public static void errStack2Log4j(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.toString());
		sb.append("\r\n");
		sb.append(Thread.currentThread().getStackTrace()[2]);
		sb.append("\r\n");
		StackTraceElement[] stackTraces = e.getStackTrace();
		for (int i = 0; i < stackTraces.length; i++) {
			sb.append(stackTraces[i]);
			sb.append("\r\n");
		}
		LoggerFactory.getLogger(LogUtil.class).error(sb.toString());
	}

	/**
	 * 自定义日志
	 * @param fileName
	 * @param msg
	 */
	public static void customLog(String fileName, String msg) {
		String customLogOn = PropertiesUtils.getValue("customLogOn");
		if ("false".equals(customLogOn)) {
			return;
		}
		String userHome = System.getProperty("user.home");
		FileUtil.writeFile(userHome + "/.customLog/" + fileName, msg, true);
	}
}
