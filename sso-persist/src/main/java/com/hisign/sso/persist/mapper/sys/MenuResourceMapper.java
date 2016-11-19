/**
 * 
 */
package com.hisign.sso.persist.mapper.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.MenuResource;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * 菜单资源表DAO
 * @author chailiangzhi
 * @date 2016-7-5
 * 
 */
public interface MenuResourceMapper extends BaseMapper<MenuResource> {
	
	public void delete(MenuResource t);
	
	public MenuResource getById(Map<String,Object> map);
	
	/**
	 * 获取某菜单下的子菜单
	 * @param map
	 * @return
	 */
	public List<MenuResource> getChildMenuResources(Map<String, Object> map);
	
	/**
	 * 根据systemId获取某个系统下的所有菜单项
	 * @param systemId
	 * @return
	 */
	public List<MenuResource> getAllBySystemId(String systemId);
	
	
	/**
	 * 根据账号获取其拥有权限的某个系统的所有菜单
	 * @param map
	 * @return
	 */
	public List<MenuResource> getAuthedMenusByAccount(Map<String, Object> map);
	
	/**
	 * 按条件进行删除
	 * @param map
	 */
	public void deleteByCondition(Map<String,Object> map);

	/**
	 * 查询所有子节点元素
	 * @author yinxiaoyong
	 * @mailto yinxiaoyong@hisign.com.cn
	 * 2016年11月18日
	 */
	public List<MenuResource> getChildrenById(MenuResource menuResource);
	
}
