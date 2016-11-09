package com.hisign.sso.service.impl.sys;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.RoleResource;
import com.hisign.sso.api.service.sys.RoleResourceService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.RoleResourceMapper;
import com.hisign.sso.service.impl.BaseServiceImpl;
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
	
	private static final Logger logger = LoggerFactory.getLogger(RoleResourceServiceImpl.class);
	
	@Autowired
	private RoleResourceMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	@Override
	@GET
	@Path("{id}")
	public RoleResource getById(@PathParam("id") String id) throws Exception{
		return mapper.getById(id);
	}

	@Override
	@GET
	@Path("{id}/delete")
	public void delete(@PathParam("id") String id) throws Exception {
		mapper.delete(id);
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
		long id = t.getId();
		if(id <= 0){
			id = SysIDGenerator.getInstance().genRoleResourceId();
			t.setId(id);
		}
		mapper.add(t);
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<RoleResource> list) throws Exception {
		for(int i = 0; i < list.size(); i++){
			RoleResource rr = list.get(i);
			long id = rr.getId();
			if(id <= 0){
				id = SysIDGenerator.getInstance().genRoleResourceId(i+1);
				rr.setId(id);
			}
		}
		batchCommitHelper.addBatch(RoleResourceMapper.class, list);
	}

}
