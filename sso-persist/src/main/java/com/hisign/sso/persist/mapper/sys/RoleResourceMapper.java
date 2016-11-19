package com.hisign.sso.persist.mapper.sys;

import java.util.Map;

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
	 * 删除角色授权对象
	 * @param userRole
	 */
	public void delete(RoleResource userRole);
	
	/**
	 * 更新权限值
	 * @param t
	 */
	public void updatePrivilegeValue(RoleResource t);
	
	/**
	 * 按条件进行删除
	 * @param map
	 */
	public void deleteByCondition(Map<String,Object> map);

}
