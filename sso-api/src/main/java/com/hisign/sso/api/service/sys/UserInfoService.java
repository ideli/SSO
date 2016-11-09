/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.query.QueryFilter;

/**
 * 用户管理
 * 实现用户信息管理。
 * 
 * 
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public interface UserInfoService {

	/**
	 * 根据账号查询
	 * @param t
	 * @return
	 */
	public UserInfo getByAccount(String account);

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public UserInfo getById(String id);

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
	public Map<String, String> update(UserInfo t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> add(UserInfo t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public Map<String, String> addBatch(List<UserInfo> list) throws Exception;

	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<UserInfo> query(Map<String, Object> map);

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map);

	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter);
	
}
