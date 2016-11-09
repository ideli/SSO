package com.hisign.sso.persist.mapper.sys;

import java.util.List;

import com.hisign.sso.api.entity.sys.SysUserRole;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *  账户角色关系DAO
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月11日  下午5:24:50
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	
	/**
	 * 删除用户角色对象
	 * @param userRole
	 */
	public void delete(SysUserRole userRole);
	
	/**
	 * 获取某账户下的角色Id列表
	 * @param account
	 * @return
	 */
	public List<String> getRoleIdsByAccount(String account);
	
	/**
	 * 根据userId列表真实删除
	 * @param userIds
	 */
	public void deleteByUserIds(List<String> userIds);

}
