/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.LogToken;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.rest.entity.MsgReq;
import com.hisign.sso.api.rest.entity.MsgRsp;
import com.hisign.sso.api.rest.entity.ReqHead;
import com.hisign.sso.api.rest.entity.Response;
import com.hisign.sso.api.rest.entity.RspHead;
import com.hisign.sso.api.rest.entity.sys.User;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.LoginService;
import com.hisign.sso.api.service.sys.UserService;
import com.hisign.sso.common.util.DateUtils;
import com.hisign.sso.common.util.DateUtils.DateFormat;
import com.hisign.sso.common.util.JsonUtil;
import com.hisign.sso.common.util.Md5Util;
import com.hisign.sso.persist.mapper.sys.LogTokenMapper;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;

/**
 * 登录服务实现
 * @author chailiangzhi
 * @date 2016-6-29
 * 
 */
@Transactional
@Service("loginService")
@Path("/")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class LoginServiceImpl implements LoginService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 系统用户DAO接口
	 */
	@Autowired
	private SysUserMapper userMapper;

	/**
	 * Token记录表DAO
	 */
	@Autowired
	LogTokenMapper logTokenMapper;
	
	@Autowired
	private UserService userService;

	/**
	 * 生成响应头
	 * @param reqHead
	 * @return
	 */
	public RspHead genRspHead(ReqHead reqHead) {
		logger.debug("genRspHead");
		RspHead rspHead = new RspHead();
		rspHead.setReqSn(reqHead.getReqSn());
		rspHead.setRspTime(DateUtils.parse2String(new Date(), DateFormat.YMDHMS_SHORT));
		rspHead.setSecretKey(reqHead.getSecretKey());
		return rspHead;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.LoginService#login(com.hisign.sso.api.entity.sys.SysUser)
	 */
	@Override
	@POST
	@Path("login")
	public LogToken login(@HeaderParam(value = "systemId") String systemId, SysUser sysUser) {
		String account = sysUser.getAccount();
		String password = sysUser.getPass();
		String passwordMd5 = Md5Util.genMd5Hex(password).toUpperCase();
		SysUser uaopSysuserFind = userMapper.getByAccount(account);
		boolean isSuccess = false;
		if (uaopSysuserFind != null) {
			String passInDb = uaopSysuserFind.getPass();
			if (passInDb != null && passInDb.equals(passwordMd5)) {
				isSuccess = true;
			}
		}
		if (isSuccess) {
			LogToken logToken;
			try {
				logToken = genTokenAndRecord(systemId, uaopSysuserFind);
				LogToken logTokenNew = new LogToken();
				logTokenNew.setToken(logToken.getToken());
				String userId = uaopSysuserFind.getUserId();
				User user;
				if (!StringUtils.isEmpty(userId)) {
					logTokenNew.setUserId(userId);
					user = userService.getUserByUserId(userId);
				} else {
					user = new User();
					user.setAccount(uaopSysuserFind.getAccount());
					user.setUserName(uaopSysuserFind.getUserName());
				}
				logTokenNew.setUser(user);
				return logTokenNew;
			} catch (Exception e) {
				logger.error("生成TOKEN异常", e);
				throw new RestBusinessException(Status.EXPECTATION_FAILED, "生成TOKEN异常");
			}
		} else {
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "用户名或密码错误");
		}
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.LoginService#logout(java.lang.String)
	 */
	@Override
	@GET
	@Path("logout")
	public Map<String, Object> logout(@HeaderParam("token") String token) {
		try {
			logTokenMapper.delete(token);
		} catch (Exception e) {
			logger.error("删除Token失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除Token失败");
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("message", "成功");
		return retMap;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.LoginService#login(com.hisign.sso.api.rest.entity.MsgReq)
	 */
	@Override
	@POST
	@Path("login1")
	public MsgRsp login1(MsgReq msgReq) {
		logger.info("login");
		ReqHead reqHead = msgReq.getReqHead();
		Response response = new Response();
		RspHead rspHead = genRspHead(reqHead);
		rspHead.setResponse(response);
		MsgRsp rsp = new MsgRsp();
		rsp.setRspHead(rspHead);

		String reqBody = msgReq.getReqBody();
		SysUser uaopSysuser = JsonUtil.gson.fromJson(reqBody, SysUser.class);
		String account = uaopSysuser.getAccount();
		String password = uaopSysuser.getPass();
		String passwordMd5 = Md5Util.genMd5Hex(password).toUpperCase();
		SysUser uaopSysuserFind = userMapper.getByAccount(account);
		boolean isSuccess = false;
		if (uaopSysuserFind != null) {
			String passInDb = uaopSysuserFind.getPass();
			if (passInDb != null && passInDb.equals(passwordMd5)) {
				isSuccess = true;
			}
		}
		response.setRspType("1");
		if (isSuccess) {
			try {
				LogToken logToken = genTokenAndRecord(reqHead.getSysCode(), uaopSysuser);
				rsp.setRspBody(JsonUtil.gson.toJson(logToken));
				response.setRspType("0");
				response.setRspCode("0000");
				response.setRspDesc("登录成功");
			} catch (Exception e) {
				response.setRspCode("1002");
				response.setRspDesc(e.toString());
			}
		} else {
			response.setRspCode("1001");
			response.setRspDesc("登录失败");
		}

		return rsp;
	}

	/**
	 * 生成并记录Token
	 * @param reqHead
	 * @param account
	 * @return
	 * @throws Exception
	 */
	private LogToken genTokenAndRecord(String sysCode, SysUser uaopSysuser) throws Exception {
		LogToken logToken = new LogToken();
		String curTime = DateUtils.parse2String(new Date(), DateFormat.YMDHMS_SHORT);
		String randomNum = genRandomStr();
		//		String sysCode = reqHead.getSysCode();
		String tokenStr = Md5Util.genMd5Hex(sysCode + curTime + randomNum);
		logToken.setToken(tokenStr);
		long now = System.currentTimeMillis();
		long invalidTime = now + 3600 * 1000;
		logToken.setInvalidTime(String.valueOf(invalidTime));

		// 记录TOKEN到数据库
		logToken.setAccount(uaopSysuser.getAccount());
		logToken.setCreateDate(new Date());
		logToken.setEffectiveTime(String.valueOf(now));
		logToken.setRandomVal(randomNum);
		logToken.setSysCode(sysCode);
		logToken.setTokenTime(curTime);
		logToken.setUserId(uaopSysuser.getUserId());
		logTokenMapper.add(logToken);
		return logToken;
	}

	/**
	 * 生成随机数
	 * @return
	 */
	private String genRandomStr() {
		Random ra = new Random();
		int raInt = ra.nextInt(999999);
		// 得到一个NumberFormat的实例  
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组  
		nf.setGroupingUsed(false);
		// 设置最大整数位数  
		nf.setMaximumIntegerDigits(6);
		// 设置最小整数位数  
		nf.setMinimumIntegerDigits(6);
		String raStr = nf.format(raInt);
		return raStr;
	}

}
