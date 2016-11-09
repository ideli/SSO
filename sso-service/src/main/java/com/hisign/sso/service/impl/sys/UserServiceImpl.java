/**
 * 
 */
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.rest.entity.sys.User;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.UserService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.common.util.Md5Util;
import com.hisign.sso.persist.mapper.sys.RoleMapper;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;
import com.hisign.sso.persist.mapper.sys.SysUserRoleMapper;
import com.hisign.sso.persist.mapper.sys.UserInfoMapper;
import com.hisign.sso.persist.mapper.sys.UserMapper;
import com.hisign.sso.persist.mapper.sys.UserOrganizationMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * 4.7	用户管理
 * 实现用户信息管理。
 * 新增，删除，更新用户等操作
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
@Path("users")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 系统用户DAO接口
	 */
	@Autowired
	private SysUserMapper mapper;
	
	/**
	 * 用户信息DAO接口
	 */
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * 用户信息DAO接口
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户与组织机构
	 */
	@Autowired
	private UserOrganizationMapper userOrganizationMapper;  //
	
	/**
	 * 角色DAO接口
	 */
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 用户角色关系DAO接口
	 */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	
	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserService#register(com.hisign.sso.api.entity.sys.SysUser)
	 */
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public User add(User user) throws Exception {
		SysUser sysUser = new SysUser();
		String userId=SysIDGenerator.getInstance().genUserId();
		sysUser.setUserId(userId);
		sysUser.setAccount(user.getAccount());
		sysUser.setPass(Md5Util.genMd5Hex(user.getPass()).toUpperCase());
		sysUser.setUserName(user.getUserName());
		int userType = user.getUserType();
		if(userType <= 0){
			sysUser.setUserType(UAOPConstant.UserType.NORMAL);
		}else{
			sysUser.setUserType(userType);
		}
		
		sysUser.initBaseParameter();

		UserInfo userInfo = user;
		userInfo.setUserId(userId);
		userInfo.initBaseParameter();

		UserOrganization userOrganization = new UserOrganization();
		userOrganization.setUserId(userId);
		userOrganization.setOrgId(user.getOrgId());
		userOrganization.setType(0);
		userOrganization.setAdmin(0);

		mapper.add(sysUser);
		userInfoMapper.add(userInfo);
		userOrganizationMapper.add(userOrganization);
		
		user.setUserId(userId);
		return user;
	}

	
	/**
	 * 判断用户账号是否存在
	 * @param account
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("account/{account}")
	public Map<String,String> isAccountExist(@PathParam("account") String account) throws Exception{
		int count = this.mapper.isAccountExist(account);
		if(count > 0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("account", account);
			return map;
		}
		
		throw new RestBusinessException(Response.Status.NOT_FOUND,"用户名不存在");
	}
	
	
	/**
	 * 判断身份证号是否存在
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("cid/{cid}")
	public Map<String,String> isCidExist(@PathParam("cid") String cid) throws Exception{
		int count = this.userMapper.isCidExist(cid);
		if(count > 0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("cid", cid);
			return map;
		}
		
		throw new RestBusinessException(Response.Status.NOT_FOUND,"身份证号不存在");
	}
	
	/**
	 * 判断警员编号是否存在
	 * @param policeId
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("policeId/{policeId}")
	public Map<String,String> isPoliceIdExist(@PathParam("policeId") String policeId) throws Exception{
		int count = this.userMapper.isPoliceIdExist(policeId);
		if(count > 0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("policeId", policeId);
			return map;
		}
		
		throw new RestBusinessException(Response.Status.NOT_FOUND,"警员编号不存在");
	}
	
	
	/**
	 * 根据用户Id获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	@GET
	@Path("{userId}")
	public User getUserByUserId(@PathParam("userId") String userId) throws Exception{
		User user = this.userMapper.getUserById(userId);
		if(user == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "不存在该用户");
		}
	
		String roleName = "";
		List<Role> roleList = roleMapper.getRoleListByAccount(user.getAccount());
		if(roleList != null && roleList.size() > 0){
			Role defaultRole = roleList.get(0);
			roleName = defaultRole.getRoleName();
		}
		user.setRoleName(roleName);
		user.setAvatar("");
		
		return user;
	}
	
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public void update(User user) throws Exception {
		SysUser sysUser = user.fetchSysUser();
		sysUser.initUpdParameter();
		this.mapper.update(sysUser);
		
		UserInfo userInfo = user.fetchUserInfo();
		userInfo.initUpdParameter();
		this.userInfoMapper.update(userInfo);
		
		UserOrganization userOrg = user.fetchUserOrganization();
		userOrg.setType(0);
		this.userOrganizationMapper.delete(userOrg);
		this.userOrganizationMapper.add(userOrg);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserService#findSuperUsers(java.util.List, java.lang.String)
	 */
	@Override
	public List<String> findSuperUsers(List<String> userIds, String status) {
		logger.debug("findSuperUsers(List<String> userIds, String status)");
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < userIds.size(); i++) {
			String userId = userIds.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append("'").append(userId).append("'");
		}
		sb.append(")");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userIds", sb.toString());
		paraMap.put("status", status);
		return mapper.findSuperUsers(paraMap);
	}
	
	
	/**
	 * 定义一个空方法，主要用于测试过滤器检查token
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("token/check")
	public Map<String,String> checkToken() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "true");
		return map;
	}
	
	
	/**
	 * 根据userId列表删除用户
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("delete/byuserids")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String,String> deleteByUserIds(List<String> userIdList) throws Exception{
		try {
			SysUser t =new SysUser();
			t.initUpdParameter();
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("lastModifyAccount", t.getLastModifyAccount());
			map.put("lastModifyTime", t.getLastModifyTime());
			map.put("lastTerminal", t.getLastTerminal());
			map.put("lastSys", t.getLastSys());
			map.put("status", UAOPConstant.STATUS.DELETED); //删除
			map.put("ids", userIdList);
			
			mapper.updateStatusByIdList(map);
			userInfoMapper.updateStatusByIdList(map);
			userOrganizationMapper.deleteByUserIds(userIdList); //用户与组织机构对应关系删除
			sysUserRoleMapper.deleteByUserIds(userIdList); //用户与角色关系删除
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}
	
	
	/**
	 * 获取某组织机构下的所有用户
	 * @param map
	 * @return
	 */
	@Override
	@POST
	@Path("listbyorganise")
	@Consumes({MediaType.APPLICATION_JSON})
	public List<User> getUsersUnderOrganise(Map<String,Object> map) throws Exception{
		List<User> list = this.userMapper.getUsersUnderOrganise(map);
		if(list == null || list.size() <= 0){
			return new ArrayList<User>();
		}
		return list;
	}

}
