package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.SysBaseEntity;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.OrganiseService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.OrganiseMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *   组织机构服务实现
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午11:23:31
 */

@Path("organises")
@Service("organiseService")
public class OrganiseServiceImpl implements OrganiseService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrganiseMapper mapper;  //组织机构dao
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	/**
	 * 获取组织机构树,该方法仅给实验室用
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/alltree")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getAllOrganiseTree() throws Exception{
		return this.getOrganiseTree("-1");
	}
	
	
	/**
	 * 获取组织机构树 该方法仅给实验室用
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("{superId}/tree")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getOrganiseTree(@PathParam("superId") String superId) throws Exception{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put(UAOPConstant.KEY_SYSTEMID,"ALIMS");
		paraMap.put("type", UAOPConstant.OrganiseType.SUBMISSION); //默认委托用户
		List<Organise> allList = this.mapper.query(paraMap);
		if(allList == null || allList.size() <= 0){
			return new ArrayList<Organise>();
		}
		
		if(StringUtils.isEmpty(superId)){
			superId = "-1";
		}
		
		Map<String,List<Organise>> superMap = new HashMap<String,List<Organise>>();
		for(Organise org : allList){
			String superOrgId = org.getSuperId();
			if(StringUtils.isEmpty(superOrgId)){
				superOrgId = "-1";
			}
			List<Organise> children = null;
		    if(superMap.containsKey(superOrgId)){
		    	children = superMap.get(superOrgId);
		    }else{
		    	children = new ArrayList<Organise>();
		    	superMap.put(superOrgId, children);
		    }
		    children.add(org);
		}
		
		List<Organise> list = superMap.get(superId); //第一层
		
		this.assembleOrganiseTree(list, superMap);
		
		if(list == null || list.size() <= 0){
			return new ArrayList<Organise>();
		}
		
		return list;
	}
	
	/**
	 * 组织组织机构树
	 * @param list
	 * @param superMap
	 */
	public void assembleOrganiseTree(List<Organise> list,Map<String,List<Organise>> superMap){
		if(list == null || list.size() <= 0){
			return;
		}
		
		for(Organise o : list){
			String orgId = o.getOrgId();
			List<Organise> children = superMap.get(orgId);
			o.setChildren(children);
			this.assembleOrganiseTree(children, superMap);
		}
	}

	@GET
	@Path("{superId}/subs")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getOrganiseListBySuperId(@PathParam("superId") String superId) throws Exception {
		return mapper.getOrganiseListBySuperId(superId);
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Organise getById(@PathParam("id") String id) {
		return mapper.getById(id);
	}

	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		try {
			Organise t =new Organise();
			t.setOrgId(id);
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
	public Map<String, String> update(Organise t) throws Exception {
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
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> add(Organise t) throws Exception {
		String orgId = t.getOrgId();
		if(StringUtils.isEmpty(orgId)){
			orgId = SysIDGenerator.getInstance().genOrgId();
			t.setOrgId(orgId);
		}
		t.initBaseParameter();
		try {
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("orgId", orgId);
		retMap.put("message", "成功");
		return retMap;
	}

	@POST
	@Path("batchadd")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> addBatch(List<Organise> list) throws Exception {
		for(Organise org : list){
			String orgId = org.getOrgId();
			if(StringUtils.isEmpty(orgId)){
				orgId = SysIDGenerator.getInstance().genOrgId();
				org.setOrgId(orgId);
			}
			
			org.initBaseParameter();
		}

		try {
			batchCommitHelper.addBatch(OrganiseMapper.class, list);
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
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> query(Map<String, Object> map) {
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
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,Object> queryByPagination(QueryFilter queryFilter){
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
			int type = condition.getType();
			map.put("type", type);
			String orgName = condition.getOrgName();
			map.put("name", orgName);
			String orgCode = condition.getOrgCode();
			map.put("orgCode", orgCode);
			String superId = condition.getSuperId();
			map.put("superId", superId);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<Organise> page = (Page<Organise>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	
	/**
	 * 根据组织机构代码查询上级组织机构列表
	 * @param paraMap
	 * @return
	 */
	@POST
	@Path("superlist")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getSuperListByOrgCode(Map<String,String> paraMap) throws Exception{
		String systemId = paraMap.get(UAOPConstant.KEY_SYSTEMID);
		String orgCode = paraMap.get("orgCode"); //机构编码
		String withSelf = paraMap.get("withSelf"); //是否包含本机组织机构
		int orgType = Integer.parseInt(paraMap.get("orgType"));
		
		List<Organise> allList = this.mapper.getAllOrganisesBySystemId(systemId);
		if(allList == null || allList.size() <= 0){
			return null;
		}
		
		if(StringUtils.isEmpty(orgCode)){ //外部委托用户
			return allList;
		}
		
		Organise currentOrg = null;
		Map<String,Organise> map = new ConcurrentHashMap<String,Organise>();
		for(Organise org : allList){
			map.put(org.getOrgId(), org);
			String code = org.getOrgCode();
			int type = org.getType();
			if(!StringUtils.isEmpty(code) && code.equals(orgCode) && (type == orgType)){ //找到orgCode对应的组织机构
				currentOrg = org;
			}
		}
		
		if(currentOrg == null){
			return new ArrayList();
		}
		
		List<Organise> result = new ArrayList<Organise>();
		this.fetchSuperOrganise(currentOrg, map, result);
		if(!StringUtils.isEmpty(withSelf) && withSelf.equalsIgnoreCase("true")){
			result.add(currentOrg);
		}
		
		return result;
	}
	
	
	/**
	 * 递归获取父级组织机构
	 * @param currentOrg
	 * @param map
	 */
	private void fetchSuperOrganise(Organise currentOrg,Map<String,Organise> map,List<Organise> result){
		String superId = currentOrg.getSuperId();
		if(StringUtils.isEmpty(superId)){
			return;
		}
		
		Organise superOrg = map.get(superId);
		if(superOrg == null){
			return;
		}
		
		result.add(superOrg);
		
		this.fetchSuperOrganise(superOrg, map, result);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.OrganiseService#deleteByIds(java.util.List)
	 */
	@Override
	public Map<String, String> deleteByIds(List<String> ids) {
		logger.debug("Map<String, String> deleteByIds(List<String> ids)");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.size(); i++) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append("'").append(ids.get(i)).append("'");

		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		SysBaseEntity baseEntity = new SysBaseEntity();
		baseEntity.initUpdParameter();
		paraMap.put("baseEntity", baseEntity);
		paraMap.put("ids", sb.toString());
		mapper.deleteByIds(paraMap);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.OrganiseService#queryChildren(java.util.List)
	 */
	@Override
	public List<Organise> queryChildren(List<String> orgIdList){
		logger.debug("List<Organise> queryChildren(List<String> orgIdList)");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < orgIdList.size(); i++) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append("'").append(orgIdList.get(i)).append("'");

		}
		return mapper.qryChildren(sb.toString());
	}
	
	
	/**
	 * 根据userId获取组织机构对象
	 * @param userId
	 * @return
	 */
	@Override
	@GET
	@Path("{userId}/infobyuserid")
	@Produces({MediaType.APPLICATION_JSON})
	public Organise getOrganiseByUserId(@PathParam("userId") String userId) throws Exception{
		Organise organise = this.mapper.getOrganiseByUserId(userId);
		if(organise == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "找不到对应记录");
		}
		
		return organise;
	}
	
	
	/**
	 * 按照系统编号获取组织机构树
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{systemId}/alltreebysystem")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getAllOrganiseTreeBySystem(@PathParam("systemId") String systemId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(UAOPConstant.KEY_SYSTEMID, systemId);
		map.put("superId", "-1");
		return this.getOrganiseTreeBySystem(map);
	}
	
	
	/**
	 * 按照系统编号获取组织机构树
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("treebysystem")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Organise> getOrganiseTreeBySystem(Map<String,Object> map) throws Exception{
		String superId = (String)map.get("superId");
		String systemId = (String)map.get(UAOPConstant.KEY_SYSTEMID);
		Integer type = (Integer)map.get("type");
		if(type == null){
			type = UAOPConstant.OrganiseType.SUBMISSION; //默认委托用户
		}
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put(UAOPConstant.KEY_SYSTEMID, systemId);
		paraMap.put("type", type);
		List<Organise> allList = this.mapper.query(paraMap);
		
		if(allList == null || allList.size() <= 0){
			return new ArrayList<Organise>();
		}
		
		if(StringUtils.isEmpty(superId)){
			superId = "-1";
		}
		
		Map<String,List<Organise>> superMap = new HashMap<String,List<Organise>>();
		for(Organise org : allList){
			String superOrgId = org.getSuperId();
			if(StringUtils.isEmpty(superOrgId)){
				superOrgId = "-1";
			}
			List<Organise> children = null;
		    if(superMap.containsKey(superOrgId)){
		    	children = superMap.get(superOrgId);
		    }else{
		    	children = new ArrayList<Organise>();
		    	superMap.put(superOrgId, children);
		    }
		    children.add(org);
		}
		
		List<Organise> list = superMap.get(superId); //第一层
		
		this.assembleOrganiseTree(list, superMap);
		
		if(list == null || list.size() <= 0){
			return new ArrayList<Organise>();
		}
		
		return list;
	}

}
