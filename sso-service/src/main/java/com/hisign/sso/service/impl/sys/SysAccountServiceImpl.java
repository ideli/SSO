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

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.SysAccountService;
import com.hisign.sso.common.util.Md5Util;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *  系统账号服务
 * @description:
 * 
 * @author lnj 
 */

@Path("sysaccounts")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 系统用户DAO接口
	 */
	@Autowired
	private SysUserMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	/**
	 * 根据账号查询用户的资源代码集合
	 * @param paraMap
	 * @return
	 */
	@POST
	@Path("resources")
	@Consumes({MediaType.APPLICATION_JSON})
	public List<String> findResByAccount(Map<String, String> paraMap){
		return mapper.findResByAccount(paraMap);
	}
	
	/**
	 * 修改密码
	 * @param map
	 * @throws Exception
	 */
	@POST
	@Path("passmodify")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> modifyPass(Map<String, Object> map){
		
		try {
			SysUser t =new SysUser();
			t.initUpdParameter();
			map.put("lastModifyAccount", t.getLastModifyAccount());
			map.put("lastModifyTime", t.getLastModifyTime());
			map.put("lastTerminal", t.getLastTerminal());
			map.put("lastSys", t.getLastSys());
			
			mapper.modifyPass(map);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "修改失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@GET
	@Path("{account}/delete")
	public Map<String, String> delete(@PathParam("account") String account) throws Exception {
		try {
			SysUser t =new SysUser();
			t.setAccount(account);
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

	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> update(SysUser t) throws Exception {
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
	
	@POST
	@Path("sysuser/add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> add(SysUser t) throws Exception {
		t.initBaseParameter();
		try {
			t.setPass(Md5Util.genMd5Hex(t.getPass()).toUpperCase());
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}
	
	@POST
	@Path("batchadd")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> addBatch(List<SysUser> list) throws Exception {
		for(SysUser sysUser : list){
			sysUser.initBaseParameter();
		}
		
		try {
			batchCommitHelper.addBatch(SysUserMapper.class, list);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	
	@POST
	@Path("query")
	@Consumes({MediaType.APPLICATION_JSON})
	public List<SysUser> query(Map<String, Object> map) {
		return mapper.query(map);
	}

	@POST
	@Path("count")
	@Consumes({MediaType.APPLICATION_JSON})
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
	@POST
	@Path("pagequery")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String,Object> queryByPagination(QueryFilter queryFilter){
		int pageNum = queryFilter.getPageNum();
		int pageSize =  queryFilter.getPageSize();
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String userName = condition.getUserName();
			map.put("userName", userName);
			String userId = condition.getUserId();
			map.put("userId", userId);
			int type = condition.getType();
			map.put("userType", type);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<SysUser> page = (Page<SysUser>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}


}
