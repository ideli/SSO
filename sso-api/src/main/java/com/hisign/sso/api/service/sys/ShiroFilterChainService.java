/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.Map;

/**
 * Shiro过滤器链定义信息获取服务
 * @author chailiangzhi
 * @date 2016-6-23
 * 
 */
public interface ShiroFilterChainService {

	/**
	 * 资源URL和权限代码(资源ID)关联关系获取
	 * @param sysCode
	 * @return
	 */
	public Map<String, String> findUrlPermission(String sysCode);

}
