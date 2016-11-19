package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;


import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.query.QueryFilter;

/**
 * @Title:
 *  组织机构管理服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午11:07:21
 */
public interface OrganiseService{
	
	/**
	 * 获取组织机构树
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getAllOrganiseTree() throws Exception;
	
	/**
	 * 获取某组织机构下的组织机构列表
	 * @param superId
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getOrganiseListBySuperId(String superId) throws Exception;

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public Organise getById(String id);

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public Map<String, String> delete(String id) throws Exception;

	/**
	 * 根据id列表删除多条记录
	 * @param ids
	 * @throws Exception
	 */
	public Map<String, String> deleteByIds(List<String> ids);

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> update(Organise t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> add(Organise t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public Map<String, String> addBatch(List<Organise> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<Organise> query(Map<String, Object> map) throws Exception;

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
	
	/**
	 * 根据组织机构代码查询上级组织机构列表
	 * @param paraMap
	 * @return
	 */
	public List<Organise> getSuperListByOrgCode(Map<String,String> paraMap) throws Exception;

	/**
	 * 查询组织机构及所有下级机构列表
	 * @param orgIdList
	 * @return
	 */
	public List<Organise> queryChildren(List<String> orgIdList);
	
	/**
	 * 根据用户Id获取组织机构对象
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Organise getOrganiseByUserId(String userId) throws Exception;
	
	/**
	 * 获取组织机构树
	 * @param superId
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getOrganiseTree(String superId) throws Exception;
	
	/**
	 * 按照系统编号获取组织机构树
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getAllOrganiseTreeBySystem(String systemId) throws Exception;
	
	/**
	 * 按照systemId和superId获取组织机构树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getOrganiseTreeBySystem(Map<String,Object> map) throws Exception;
}
