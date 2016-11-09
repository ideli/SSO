/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.sso.api.entity.sys.MenuResource;
import com.hisign.sso.api.service.sys.ShiroFilterChainService;
import com.hisign.sso.persist.mapper.sys.MenuResourceMapper;

/**
 * Shiro过滤器链定义信息获取服务的实现
 * @author chailiangzhi
 * @date 2016-7-5
 * 
 */
@Service("shiroFilterChainService")
public class ShiroFilterChainServiceImpl implements ShiroFilterChainService {

	/**
	 * 
	 */
	@Autowired
	private MenuResourceMapper uaopMenuResourceMapper;

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.ShiroFilterChainService#findUrlPermission(java.lang.String)
	 */
	@Override
	public Map<String, String> findUrlPermission(String sysCode) {
		MenuResource para = new MenuResource();
		para.setSystemId(sysCode);
		List<MenuResource> urlPermissionList = uaopMenuResourceMapper.find(para);
		Map<String, String> urlPermissionMap = new HashMap<String, String>();
		for (MenuResource uaopMenuResource : urlPermissionList) {
			urlPermissionMap.put(uaopMenuResource.getUrl(), uaopMenuResource.getResourceId());
		}
		return urlPermissionMap;
	}

}
