package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
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
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.UserInfoService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.UserInfoMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *   用户信息服务
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:35:27
 */
@Path("userinfos")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private UserInfoMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserInfoService#getByAccount(java.lang.String)
	 */
	@Override
	@GET
	@Path("get/{account}")
	public UserInfo getByAccount(@PathParam("account") String account) {
		return mapper.getByAccount(account);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#getById(java.lang.String)
	 */
	@Override
	@GET
	@Path("{id}")
	public UserInfo getById(@PathParam("id") String id) {
		return mapper.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#delete(java.lang.String)
	 */
	@Override
	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		try {
			UserInfo t =new UserInfo();
			t.setUserId(id);
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

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#update(java.lang.Object)
	 */
	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> update(UserInfo t) throws Exception {
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

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#add(java.lang.Object)
	 */
	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> add(UserInfo t) throws Exception {
		String userId = t.getUserId();
		if(StringUtils.isEmpty(userId)){ //如果没有设置userId,则使用系统时间作为UserId
			userId = SysIDGenerator.getInstance().genUserId();
			t.setUserId(userId);
		}
		
		t.initBaseParameter();
		try {
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("userId", userId);
		retMap.put("message", "成功");
		return retMap;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#addBatch(java.util.List)
	 */
	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> addBatch(List<UserInfo> list) throws Exception {
		for(int i = 0; i < list.size(); i++){
			UserInfo user = list.get(i);
			String userId = user.getUserId();
			if(StringUtils.isEmpty(userId)){ //如果没有设置userId,则使用系统时间作为UserId
				userId = SysIDGenerator.getInstance().genUserId(i+1);
				user.setUserId(userId);
			}
			
			user.initBaseParameter();
		}

		try {
			batchCommitHelper.addBatch(UserInfoMapper.class, list);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#query(java.util.Map)
	 */
	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<UserInfo> query(Map<String, Object> map) {
		return mapper.query(map);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.service.impl.BaseServiceImpl#count(java.util.Map)
	 */
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
			String userName = condition.getUserName();
			map.put("userName", userName);
			String cid = condition.getCid();
			map.put("cid", cid);
			String policeId = condition.getPoliceId();
			map.put("policeId", policeId);
			String contact = condition.getContact();
			map.put("contact", contact);
		}
		
		PageHelper.startPage(pageNum, pageSize, orderBy);
        Page<UserInfo> page = (Page<UserInfo>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
}
