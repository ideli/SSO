/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.Set;

/**
 * 登录服务，
 * 供业务系统基于shiro的单点登录使用，
 * 单点登录原理基于CAS
 * @author chailiangzhi
 * @date 2016-6-30
 * 
 */
public interface ShiroLoginService {

	/**
	 * 生成Ticket,并存储Ticket与username的对应关系
	 * @param username
	 * @return
	 */
	public String genTicket(String username);

	/**
	 * 根据一次性Ticket获取用户名
	 * 返回之前需要把Ticket从存储中删除
	 * 在后台用Ticket换用户名的设计避免用户名在浏览器地址栏泄露
	 * @param ticket
	 * @return
	 */
	public String getUsernameByTicket(String ticket);

	/**
	 * 根据用户名查询用户的权限点(资源ID)集合
	 * shiro框架获取认证信息和授权信息分为两步
	 * 所以后台获取用户名和获取权限点分成两个方法
	 * @param sysCode
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String sysCode, String username);
}
