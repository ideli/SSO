/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.Map;

import com.hisign.sso.api.entity.sys.LogToken;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.rest.entity.MsgReq;
import com.hisign.sso.api.rest.entity.MsgRsp;

/**
 * 登录服务，
 * REST登录接口
 * @author chailiangzhi
 * @date 2016-6-22
 * 
 */

public interface LoginService {

	/**
	 * REST登录接口(实验室前端用)
	 * @param msgReq
	 * @return
	 */
	public LogToken login(String systemId, SysUser sysUser);
	
	/**
	 * REST登录接口(移动警务平台)
	 * @param systemId
	 * @param sysUser
	 * @return
	 */
	public LogToken imlogin(String systemId, SysUser sysUser);

	/**
	 * REST注销接口(实验室前端用)
	 * @param token
	 * @return
	 */
	public Map<String, Object> logout(String token);

	/**
	 * REST登录接口(外部接口，含报文头)
	 * @param msgReq
	 * @return
	 */
	public MsgRsp login1(MsgReq msgReq);

}
