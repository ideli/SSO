/**
 * 
 */
package com.hisign.sso.persist.mapper.sys;

import java.util.List;
import java.util.Map;


import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * 新增，删除，更新用户等操作
 * @author chailiangzhi
 * @date 2016-6-28
 * 
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 根据账户名获取账户对象
	 * @param account
	 * @return
	 */
	public SysUser getByAccount(String account);
	
	/**
	 * 根据userId获取账户对象
	 * @param userId
	 * @return
	 */
	public SysUser getByUserId(String userId);

	/**
	 * 根据账号查询用户的资源代码集合
	 * @param paraMap
	 * @return
	 */
	public List<String> findResByAccount(Map<String, String> paraMap);
	
	/**
	 * 修改密码
	 * @param map
	 * @throws Exception
	 */
	public void modifyPass(Map<String, Object> map);
	

	/**
	 * 判断账号是否存在
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int isAccountExist(String account);
	
	/**
	 * 输入用户名ID集合和角色类型，输出具有指定类型角色的用户ID集合
	 * @param map
	 * @return
	 */
	public List<String> findSuperUsers(Map<String, Object> map);
}
