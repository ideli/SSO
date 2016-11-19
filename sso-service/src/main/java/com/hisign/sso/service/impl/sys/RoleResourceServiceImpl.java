package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.RoleResource;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.RoleResourceService;
import com.hisign.sso.persist.mapper.sys.RoleResourceMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *  角色权限关系(即授权)服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午6:28:29
 */

@Path("roleresources")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService {
	
	private static final Logger log = LoggerFactory.getLogger(RoleResourceServiceImpl.class);
	
	@Autowired
	private RoleResourceMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(RoleResource roleResource) throws Exception {
		mapper.delete(roleResource);
	}

	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(RoleResource t) throws Exception {
		mapper.update(t);
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(RoleResource t) throws Exception {
		mapper.add(t);
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<RoleResource> list) throws Exception {
		for(int i = 0; i < list.size(); i++){
			RoleResource rr = list.get(i);
		}
		batchCommitHelper.addBatch(RoleResourceMapper.class, list);
	}
	
	
	/**
	 * 按条件进行查询
	 */
	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<RoleResource> query(Map<String, Object> map)  throws Exception{
		return mapper.query(map);
	}

	/**
	 * 按照roleId删除角色权限关系
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> deleteByRoleId(String roleId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		try{
			this.mapper.deleteByCondition(map);
		}catch(Exception ex){
			log.error("deleteByRoleId catch an exception",ex);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}
}
