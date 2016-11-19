package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.UserOrganization;


/**
 * @Title:
 *  用户组织机构关系服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:05:05
 */
public interface UserOrganizationService {
	
	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(UserOrganization t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<UserOrganization> list) throws Exception;
	
	
	/**
	 * 删除用户组织结构关系
	 * @param userOrg
	 */
	public void delete(UserOrganization userOrg);
	
	/**
	 * 查询部门下所有的用户Id列表
	 * @param orgId 组织机构Id
	 * @return
	 */
	public List<String> getUserIdsInOrganization(String orgId);
	
	
	/**
	 * 根据userId列表真实删除
	 * @param userIds
	 */
	public Map<String,Object> deleteByUserIds(List<String> userIds) throws Exception;
	

}
