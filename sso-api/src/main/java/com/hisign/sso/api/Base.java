/**
 * 
 */
package com.hisign.sso.api;

import java.util.List;
import java.util.Map;

/**
 * 业务层和持久层公共接口基类
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public interface Base<T> {

	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<T> find(T t);

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public T getById(String id);

	/**
	 * 根据复合主键查询
	 * @param t
	 * @return
	 */
	public T getByCompositeId(T t);

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
	public void update(T t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(T t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<T> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<T> query(Map<String, Object> map);

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map);

}
