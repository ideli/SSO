package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.entity.sys.ServiceResource;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.service.sys.ServiceResourceService;
import com.hisign.sso.persist.mapper.sys.ServiceResourceMapper;
import com.hisign.sso.service.impl.BaseServiceImpl;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *  服务资源点服务实现
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午4:53:22
 */

@Path("serviceresources")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("serviceResourceService")
public class ServiceResourceServiceImpl implements ServiceResourceService {

	private static final Logger logger = LoggerFactory.getLogger(ServiceResourceServiceImpl.class);
			
	@Autowired
	private ServiceResourceMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	@POST
	@Path("get")
	@Consumes({ MediaType.APPLICATION_JSON })
	public ServiceResource getById(Map<String, Object> map) throws Exception{
		return mapper.getById(map);
	}
	

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(ServiceResource t) throws Exception{
		mapper.delete(t);
	}

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(ServiceResource t) throws Exception{
		mapper.update(t);
	}

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(ServiceResource t) throws Exception{
		mapper.add(t);
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<ServiceResource> list) throws Exception{
		batchCommitHelper.addBatch(ServiceResourceMapper.class, list);
	}
	
	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<ServiceResource> query(Map<String, Object> map) {
		return mapper.query(map);
	}

	@Override
	@POST
	@Path("count")
	@Consumes({ MediaType.APPLICATION_JSON })
	public int count(Map<String, Object> map) {
		return mapper.count(map);
	}
	
	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	@Override
	@POST
	@Path("pagequery")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception{
		int pageNum = queryFilter.getPageNum();
		int pageSize =  queryFilter.getPageSize();
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String systemId = condition.getSystemId();
			map.put("systemId", systemId);
			String serviceName = condition.getServiceName();
			map.put("serviceName", serviceName);
			String interfaceName = condition.getInterfaceName();
			map.put("interfaceName", interfaceName);
			String superId = condition.getSuperId();
			map.put("superId", superId);
			String note = condition.getNote();
			map.put("note", note);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<ServiceResource> page = (Page<ServiceResource>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	
	
}
