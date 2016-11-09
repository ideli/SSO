package com.hisign.sso.persist.mapper.sys;


import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:21:02
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
	
	
	/**
	 * 根据账号查询
	 * @param t
	 * @return
	 */
	public UserInfo getByAccount(String account);
	

}
