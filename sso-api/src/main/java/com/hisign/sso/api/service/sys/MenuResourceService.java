package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import com.hisign.sso.api.entity.sys.MenuResource;

/**
 * @Title:
 *    菜单服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月15日  上午9:44:24
 */
public interface MenuResourceService {

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public MenuResource getById(Map<String, Object> map) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<MenuResource> getChildMenuResources(Map<String, Object> map) throws Exception;

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void delete(MenuResource t) throws Exception;

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public void update(MenuResource t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(MenuResource t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<MenuResource> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<MenuResource> query(Map<String, Object> map)  throws Exception;
	
	/**
	 * 根据用户ID获取菜单树的JSON字符串
	 * @param uesrId
	 * @return
	 */
	public String findMenuTreeByUserId(Map<String, Object> map)  throws Exception;
	
	/**
	 * 根据用户Id和SystemId获取菜单树
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public List<MenuResource> getMenuTreeByUserId(Map<String,Object> map) throws Exception;
    
    /**
     * 根据用户account和SystemId获取菜单树
     * @param map
     * @return
     * @throws Exception
     */
    public List<MenuResource> getMenuTreeByAccount(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据系统唯一标识获取菜单树
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public List<MenuResource> getAllMenuResourceTree(String systemId) throws Exception;
	
	/**
	 * 根据systemId获取某个系统下的所有菜单项
	 * @param systemId
	 * @return
	 */
	public List<MenuResource> getAllMenusBySystemId(String systemId) throws Exception;
	
	
	/**
	 * 根据账号获取其拥有权限的某个系统的所有菜单
	 * @param map
	 * @return
	 */
	public List<MenuResource> getAuthedMenusByAccount(Map<String, Object> map)  throws Exception;
	
	/**
	 * 根据systemId获取某个系统下的所有仅菜单项，不包含按钮
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public List<MenuResource> getAllOnlyMenusBySystemId(String systemId) throws Exception;
	
	/**
	 * 根据systemId获取某个系统下的所有仅菜单项，不包含按钮
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public List<MenuResource> getAllOnlyButtonsBySystemId(String systemId) throws Exception;
	
	/**
	 * 删除所有的子菜单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> deleteChildrenMenuResources(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询所有子节点元素
	 * @author yinxiaoyong
	 * @mailto yinxiaoyong@hisign.com.cn
	 * 2016年11月18日
	 */
	public List<MenuResource> getChildrenById(MenuResource menuResource) throws Exception;
	
}
