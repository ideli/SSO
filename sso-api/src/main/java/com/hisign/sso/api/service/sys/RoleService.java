package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;


import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.query.QueryFilter;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午4:40:18
 */
public interface RoleService {
	
	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public Role getById(String id);

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public Map<String, String> delete(String id) throws Exception;

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> update(Role t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> add(Role t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public Map<String, String> addBatch(List<Role> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<Role> query(Map<String, Object> map);

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map);
	
	/**
	 * 分页查询
	 * @param map  查询条件
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter);
	
	
	/**
	 * 根据账号获取角色列表
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByAccount(String account) throws Exception;
	
	/**
	 * 根据userId获取角色列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByUserId(String userId) throws Exception;
}
