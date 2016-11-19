package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.entity.sys.LogRecord;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.service.sys.LogRecordService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.LogRecordMapper;

/**
 * @Title:
 *   操作日志记录服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月22日  下午4:15:43
 */

@Path("logrecords")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("logRecordService")
public class LogRecordServiceImpl implements LogRecordService{
	
	@Autowired
	private LogRecordMapper mapper;  //角色DA

	@Override
	@GET
	@Path("{id}")
	public LogRecord getById(@PathParam("id")String id)  throws Exception{
		return mapper.getById(id);
	}

	@Override
	@GET
	@Path("{id}/delete")
	public void delete(@PathParam("id")String id) throws Exception {
		mapper.delete(id);
	}

	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(LogRecord t) throws Exception {
		mapper.update(t);
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(LogRecord t) throws Exception {
		long id = t.getId();
		if(id <= 0){
			id = SysIDGenerator.getInstance().genLogRecordId();
			t.setId(id);
		}
		mapper.add(t);
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<LogRecord> list) throws Exception {
		for(int i = 0; i < list.size(); i++){
			LogRecord lr = list.get(i);
			long id = lr.getId();
			if(id <= 0){
				id = SysIDGenerator.getInstance().genLogRecordId(i+1);
				lr.setId(id);
			}
		}
		mapper.addBatch(list);
	}

	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<LogRecord> query(Map<String, Object> map)  throws Exception{
		return mapper.query(map);
	}

	@Override
	@POST
	@Path("count")
	@Consumes({ MediaType.APPLICATION_JSON })
	public int count(Map<String, Object> map)  throws Exception{
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
	public Map<String,Object> queryByPagination(QueryFilter queryFilter)  throws Exception {
		int pageNum = queryFilter.getPageNum();
		int pageSize =  queryFilter.getPageSize();
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String account = condition.getAccount();
			map.put("account", account);
			String systemId = condition.getSystemId();
			map.put("systemId", systemId);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<LogRecord> page = (Page<LogRecord>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
}
