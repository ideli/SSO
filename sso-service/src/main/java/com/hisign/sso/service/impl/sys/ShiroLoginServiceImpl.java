/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.sso.api.service.sys.ShiroLoginService;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;

/**
 * 登录服务，
 * 供业务系统基于shiro的单点登录使用，
 * 单点登录原理基于CAS
 * @author chailiangzhi
 * @date 2016-6-30
 * 
 */
@Service("shiroLoginService")
public class ShiroLoginServiceImpl implements ShiroLoginService {

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
	 * 
	 */
	private ConcurrentMap<String, String> ticketMap = new ConcurrentHashMap<String, String>();

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.ShiroLoginService#genTicket(java.lang.String)
	 */
	@Override
	public String genTicket(String username) {
		logger.info("生成TICKET");
		String ticket = String.valueOf(System.currentTimeMillis());
		ticketMap.put(ticket, username);
		return ticket;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.ShiroLoginService#getUsernameByTicket(java.lang.String)
	 */
	@Override
	public String getUsernameByTicket(String ticket) {
		String username = ticketMap.get(ticket);
		ticketMap.remove(ticket);
		return username;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.ShiroLoginService#findPermissions(java.lang.String, java.lang.String)
	 */
	@Override
	public Set<String> findPermissions(String sysCode, String username) {
		Set<String> permissionsSet = new HashSet<String>();
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("sysCode", sysCode);
		paraMap.put("account", username);
		List<String> permissionsList = userMapper.findResByAccount(paraMap);
		for (String perm : permissionsList) {
			permissionsSet.add(perm);
		}
		return permissionsSet;
	}

}
