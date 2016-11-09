/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.sdk.config.service.SysParamService;
import com.hisign.sso.api.entity.sys.LogToken;
import com.hisign.sso.api.service.sys.TokenService;
import com.hisign.sso.common.util.JsonUtil;
import com.hisign.sso.persist.mapper.sys.LogTokenMapper;

/**
 * TOKEN服务的实现
 * @author chailiangzhi
 * @date 2016-6-29
 * 
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Token记录表DAO
	 */
	@Autowired
	LogTokenMapper logTokenMapper;

	/**
	 * 
	 */
	@Autowired
	SysParamService sysParamService;

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.TokenService#checkToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String checkToken(String token, String sysCode, String serviceCode) {
		logger.debug("1..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
		Map<String, String> resMap = new HashMap<String, String>();
		LogToken logToken = logTokenMapper.getById(token);
		if (logToken == null) {
			logger.debug("2..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
			resMap.put(RSP_CODE, TOKEN_NOT_EXIST);
			Map<String, String> keyValues = sysParamService.getProperties("UAOP", "SSO");
			String loginRestUrl = keyValues.get("LOGIN_REST_URL");
			logger.info("loginRestUrl={}", loginRestUrl);
			resMap.put(LOGIN_REST_URL, loginRestUrl);
		} else {
			logger.debug("3..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
			logger.info("account={}", logToken.getAccount());
			resMap.put(RSP_CODE, TOKEN_VALID);
			resMap.put(USER_ID, logToken.getUserId());
			resMap.put(ACCOUNT, logToken.getAccount());
		}
		String retJson = JsonUtil.gson.toJson(resMap);
		return retJson;
	}

}
