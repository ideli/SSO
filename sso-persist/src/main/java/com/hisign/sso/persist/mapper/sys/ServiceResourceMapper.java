package com.hisign.sso.persist.mapper.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.entity.sys.ServiceResource;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *  服务资源Mapper
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午3:27:33
 */
public interface ServiceResourceMapper extends BaseMapper<ServiceResource> {
	
	/**
	 * 获取服务资源
	 * @param map
	 * @throws Exception
	 */
	public ServiceResource getById(Map map);
	
	/**
	 * 删除服务资源
	 * @param map
	 * @throws Exception
	 */
	public void delete(ServiceResource t);

	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	public List<Role> queryByPagination(Map<String, Object> map);
}
