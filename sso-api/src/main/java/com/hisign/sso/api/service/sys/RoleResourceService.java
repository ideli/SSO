package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.RoleResource;


/**
 * @Title:
 *  角色权限关系表
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午6:27:21
 */
public interface RoleResourceService {

	/**
	 * 删除角色授权
	 * @param id
	 * @throws Exception
	 */
	public void delete(RoleResource roleResource) throws Exception;

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public void update(RoleResource t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(RoleResource t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<RoleResource> list) throws Exception;
	
	/**
	 * 按条件进行查询
	 */
	public List<RoleResource> query(Map<String, Object> map)  throws Exception;
	
	
	/**
	 * 按照roleId删除角色权限关系
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> deleteByRoleId(String roleId) throws Exception;
	
}
