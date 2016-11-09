package com.hisign.sso.persist.mapper.sys;

import java.util.List;

import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *   用户组织机构关系DAO
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月11日  下午5:26:05
 */
public interface UserOrganizationMapper extends BaseMapper<UserOrganization> {
	/**
	 * 删除用户组织机构对象
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
	public void deleteByUserIds(List<String> userIds);
}
