/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.entity.sys.User;

/**
 * 账号管理
 * 实现账号信息管理。
 * 
 * 
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public interface UserService {
	
	/**
	 * 用户注册
	 * @param t
	 * @throws Exception
	 */
	public User add(User t) throws Exception;

	
	/**
	 * 判断用户账号是否存在
	 * @param account
	 * @return 
	 * @throws Exception
	 */
	public Map<String,String> isAccountExist(String account) throws Exception;
	
	/**
	 * 判断身份证号是否存在
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> isCidExist(String cid) throws Exception;
	
	/**
	 * 判断警员号是否存在
	 * @param policeId
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> isPoliceIdExist(String policeId) throws Exception;
	
	
	/**
	 * 根据UserID获取用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User getUserByUserId(String userId) throws Exception;
	
	/**
	 * 更新用户
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception;
	
	/**
	 * 输入用户名ID集合和角色类型，输出具有指定类型角色的用户ID集合
	 * @param map
	 * @return
	 */
	public List<String> findSuperUsers(List<String> userIds, String status);
	
	/**
	 * 定义一个空方法，主要用于测试过滤器检查token
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> checkToken() throws Exception;
	
	/**
	 * 根据userId列表删除用户
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> deleteByUserIds(List<String> userIdList) throws Exception;
	
	/**
	 * 获取某组织机构下的所有用户
	 * @param map
	 * @return
	 */
	public List<User> getUsersUnderOrganise(Map<String,Object> map) throws Exception;
	
	/**
	 * 按照条件进行查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<User> query(Map<String, Object> map) throws Exception;
	
	/**
	 * 分页查询
	 * @param queryFilter
	 * @return
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception;
	
	/**
	 * 密码修改
	 * @param map
	 * @return
	 */
	public Map<String, String> modifyPass(Map<String, Object> map) throws Exception;
}
