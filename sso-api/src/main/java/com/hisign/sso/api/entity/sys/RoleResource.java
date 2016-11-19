package com.hisign.sso.api.entity.sys;

import java.io.Serializable;

/**
 * @Title:
 *  角色与权限点对应关系(即授权)
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午10:44:54
 */
public class RoleResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//系统唯一标识
	private String systemId;

	//权限资源编号
	private String resourceId;
	
	//权限资源类型 0:菜单权限 1:服务权限
	private int resourceType;

	// 所属角色
	private String roleId = "";
	
	//权限值
	private long privilegeValue = 0;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public long getPrivilegeValue() {
		return privilegeValue;
	}

	public void setPrivilegeValue(long privilegeValue) {
		this.privilegeValue = privilegeValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleResource [systemId=");
		builder.append(systemId);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", resourceType=");
		builder.append(resourceType);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", privilegeValue=");
		builder.append(privilegeValue);
		builder.append("]");
		return builder.toString();
	}
}
