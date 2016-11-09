/**
 * 
 */
package com.hisign;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.sso.api.service.sys.TokenService;
import com.hisign.sso.common.util.LogUtil;

/**
 * TokenService 测试的单元用例
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */
public class TokenServiceTest extends BaseUnit4Test {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceTest.class);

	/**
	 * 
	 */
	@Autowired
	private TokenService tokenService;

	@Test
	public void testCall() {
		try {
			String res=tokenService.checkToken("678cd04df3bd2a53ae4a64579054eebe", "sysCode", "serviceCode");
			logger.info(res);

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			Assert.fail();
		}

	}
}
