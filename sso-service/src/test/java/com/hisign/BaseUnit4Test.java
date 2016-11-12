/**
 * 
 */
package com.hisign;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.api.constant.UAOPConstant;

/**
 * JUNIT测试基类
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置
@ContextConfiguration(locations = { "classpath:spring-service.xml",
		"classpath:spring-test.xml" })
public class BaseUnit4Test {

	public static MockHttpSession session;
	public static MockHttpServletRequest request;
	public static MockHttpServletResponse response;
	
	
	public BaseUnit4Test(){
		SysConfigLoader.getInstance().loadSysConfig(UAOPConstant.SYSTEMID);
	}

	@BeforeClass
	public static void startSession() {
		session = new MockHttpSession();
	}

	@AfterClass
	public static void endSession() {
		session.clearAttributes();
		session = null;
	}

	@BeforeClass
	public static void startRequest() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setSession(session);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@AfterClass
	public static void endRequest() {
		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
		RequestContextHolder.resetRequestAttributes();
		request = null;
	}

	@Test
	public void testApp() {
		Assert.assertTrue(true);
	}
}
