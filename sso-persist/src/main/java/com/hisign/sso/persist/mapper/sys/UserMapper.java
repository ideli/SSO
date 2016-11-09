package com.hisign.sso.persist.mapper.sys;


import java.util.List;
import java.util.Map;

import com.hisign.sso.api.persist.BaseMapper;
import com.hisign.sso.api.rest.entity.sys.User;

/**
 * @Title:
 *  用户信息(包含sysuser和userinfo)Mapper
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:21:02
 */
public interface UserMapper extends BaseMapper<User> {
	
	
	/**
	 * 根据用户Id获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);
	
	/**
	 * 判断身份证号是否存在
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public int isCidExist(String cid);
	
	/**
	 * 判断警员编号是否存在
	 * @param policeId
	 * @return 
	 * @throws Exception
	 */
	public int isPoliceIdExist(String policeId);
	
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<User> getUsersUnderOrganise(Map<String,Object> map);

}
