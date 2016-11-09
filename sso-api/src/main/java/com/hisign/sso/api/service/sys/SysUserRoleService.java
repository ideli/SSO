package com.hisign.sso.api.service.sys;

import java.util.List;

import com.hisign.sso.api.entity.sys.SysUserRole;

/**
 * @Title:
 *  账户角色服务接口
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午9:13:41
 */
public interface SysUserRoleService {
	
	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(SysUserRole t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<SysUserRole> list) throws Exception;
	
	
	/**
	 * 删除用户角色列表
	 * @param userRole
	 */
	public void delete(SysUserRole userRole) throws Exception;
	
	/**
	 * 获取某账户下的角色Id列表
	 * @param account
	 * @return
	 */
	public List<String> getRoleIdsByAccount(String account) throws Exception;

}
