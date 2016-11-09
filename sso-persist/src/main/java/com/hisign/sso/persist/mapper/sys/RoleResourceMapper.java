package com.hisign.sso.persist.mapper.sys;

import com.hisign.sso.api.entity.sys.RoleResource;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *   角色权限对应关系
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午4:11:39
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
	
	/**
	 * 更新权限值
	 * @param t
	 */
	public void updatePrivilegeValue(RoleResource t);

}
