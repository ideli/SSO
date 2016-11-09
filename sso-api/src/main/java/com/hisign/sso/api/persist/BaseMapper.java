/**
 * 
 */
package com.hisign.sso.api.persist;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.Base;

/**
 * 持久层接口基类
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public interface BaseMapper<T> extends Base<T> {

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
	
	
	/**
	 * 根据Id更新状态
	 * @param t
	 */
	public void updateStatusById(T t);
	
	/**
	 * 根据userId列表批量更新状态
	 * @param map
	 */
	public void updateStatusByIdList(Map<String, Object> map);

}
