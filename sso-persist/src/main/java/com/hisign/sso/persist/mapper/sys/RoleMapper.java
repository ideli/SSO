package com.hisign.sso.persist.mapper.sys;

import java.util.List;


import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午3:15:20
 */
public interface RoleMapper extends BaseMapper<Role> {
	
	/**
	 * 根据账号获取角色列表
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByAccount(String account);
	
	/**
	 * 根据userId获取角色列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByUserId(String userId);

}
