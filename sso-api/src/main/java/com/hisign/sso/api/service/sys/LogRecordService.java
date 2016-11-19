package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.LogRecord;
import com.hisign.sso.api.query.QueryFilter;


/**
 * @Title:
 *  日志记录服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月22日  下午4:17:04
 */
public interface LogRecordService {
	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public LogRecord getById(String id) throws Exception;

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
	public void update(LogRecord t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(LogRecord t) throws Exception;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<LogRecord> list) throws Exception;
	
	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<LogRecord> query(Map<String, Object> map) throws Exception;

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map) throws Exception;
	
	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception;
}
