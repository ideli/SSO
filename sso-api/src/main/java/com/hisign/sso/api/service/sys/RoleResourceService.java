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
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public RoleResource getById(String id) throws Exception;
   
	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;

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
	
}
