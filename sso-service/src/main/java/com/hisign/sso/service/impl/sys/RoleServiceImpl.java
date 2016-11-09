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
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.RoleService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.RoleMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *   角色服务类
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  下午4:41:49
 */

@Path("roles")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleMapper mapper;  //角色DAO
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	@Override
	@GET
	@Path("{id}")
	public Role getById(@PathParam("id") String id) {
		return mapper.getById(id);
	}

	@Override
	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		try {
			Role t =new Role();
			t.setRoleId(id);
			t.initUpdParameter();
			t.setStatus(UAOPConstant.STATUS.DELETED);
			
			mapper.updateStatusById(t);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> update(Role t) throws Exception {
		t.initUpdParameter();
		try {
			mapper.update(t);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "修改失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> add(Role t) throws Exception {
		String roleId = t.getRoleId();
		if(StringUtils.isEmpty(roleId)){
			roleId = SysIDGenerator.getInstance().genRoleId();
			t.setRoleId(roleId);
		}
		t.initBaseParameter();
		try {
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("roleId", roleId);
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> addBatch(List<Role> list) throws Exception {
		for(Role role : list){
			String roleId = role.getRoleId();
			if(StringUtils.isEmpty(roleId)){
				roleId = SysIDGenerator.getInstance().genRoleId();
				role.setRoleId(roleId);
			}
			
			role.initBaseParameter();
		}
		
		
		try {
			batchCommitHelper.addBatch(RoleMapper.class, list);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<Role> query(Map<String, Object> map) {
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
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) {
		int pageNum = queryFilter.getPageNum();
		int pageSize =  queryFilter.getPageSize();
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String roleName = condition.getRoleName();
			map.put("roleName", roleName);
			String superId = condition.getSuperId();
			map.put("superId", superId);
			String note = condition.getNote();
			map.put("note", note);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<Role> page = (Page<Role>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	
	
	/**
	 * 根据账号获取角色列表
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{account}/list")
	public List<Role> getRoleListByAccount(@PathParam("account") String account) throws Exception{
		return this.mapper.getRoleListByAccount(account);
	}
	
	/**
	 * 根据账号获取角色列表
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{userId}/listbyuserid")
	public List<Role> getRoleListByUserId(@PathParam("userId") String userId) throws Exception{
		return this.mapper.getRoleListByUserId(userId);
	}
	
}
