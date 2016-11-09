package com.hisign.sso.persist.mapper.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *  组织结构数据库访问
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午11:36:26
 */
public interface OrganiseMapper extends BaseMapper<Organise> {
	
	/**
	 * 获取某组织机构下的组织机构列表
	 * @param superId
	 * @return
	 * @throws Exception
	 */
	public List<Organise> getOrganiseListBySuperId(String superId);
	
	/**
	 * 获取所有的组织机构
	 * @return
	 */
	public List<Organise> getAllOrganises();

	/**
	 * 根据Id列表删除
	 * @param paraMap
	 * @return
	 */
	public int deleteByIds(Map<String, Object> paraMap);

	/**
	 * 根据Id列表查询所有子节点
	 * @param ids
	 * @return
	 */
	public List<Organise> qryChildren(String ids);
	
	/**
	 * 根据userId获取组织机构对象
	 * @param userId
	 * @return
	 */
	public Organise getOrganiseByUserId(String userId);
	
	/**
	 * 根据SystemId获取某项目对应的组织机构列表
	 * @param systemId
	 * @return
	 */
	public List<Organise> getAllOrganisesBySystemId(String systemId);
	
}
