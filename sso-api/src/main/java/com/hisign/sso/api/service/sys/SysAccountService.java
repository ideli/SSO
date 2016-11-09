package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.query.QueryFilter;

/**
 * @Title:
 *  系统账号服务
 * @description:
 * 
 * @author lnj 
 */
public interface SysAccountService {

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
	public Map<String, String> modifyPass(Map<String, Object> map);
	

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
	public Map<String, String> update(SysUser t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> add(SysUser t) throws Exception;
	
	
	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public Map<String, String> addBatch(List<SysUser> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<SysUser> query(Map<String, Object> map);

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter);
	
}
