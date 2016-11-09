package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.ServiceResource;
import com.hisign.sso.api.query.QueryFilter;

/**
 * @Title:
 *  服务权限点服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午4:51:02
 */
public interface ServiceResourceService{
	
	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public ServiceResource getById(Map<String, Object> map) throws Exception;
	

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void delete(ServiceResource t) throws Exception;

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public void update(ServiceResource t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(ServiceResource t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<ServiceResource> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<ServiceResource> query(Map<String, Object> map) throws Exception;
	
	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map) throws Exception;
	
	/**
	 * 分页查询
	 * @param map  查询条件
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception;
}
