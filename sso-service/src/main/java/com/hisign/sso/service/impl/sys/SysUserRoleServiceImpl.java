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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.SysUserRole;
import com.hisign.sso.api.service.sys.SysUserRoleService;
import com.hisign.sso.persist.mapper.sys.SysUserRoleMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午9:15:52
 */
@Path("sysuserroles")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	private static final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
	
	@Autowired
	private SysUserRoleMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(SysUserRole t) throws Exception {
		mapper.add(t);
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<SysUserRole> list) throws Exception {
		batchCommitHelper.addBatch(SysUserRoleMapper.class, list);
	}

	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(SysUserRole userRole) throws Exception {
		mapper.delete(userRole);
	}

	@Override
	@GET
	@Path("{account}/roleids")
	public List<String> getRoleIdsByAccount(@PathParam("account") String account) throws Exception {
		return mapper.getRoleIdsByAccount(account);
	}

	
	/**
	 * 根据userId列表真实删除
	 * @param userIds
	 */
	@Override
	@POST
	@Path("deletebyuserids")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String,Object> deleteByUserIds(List<String> userIds) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			mapper.deleteByUserIds(userIds);
		}catch(Exception ex){
			resultMap.put("message", "删除失败");
			return resultMap;
		}
		
		resultMap.put("message", "删除成功");
		return resultMap;
	}
}
