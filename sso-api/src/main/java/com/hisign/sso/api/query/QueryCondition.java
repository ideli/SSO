package com.hisign.sso.api.query;

import java.io.Serializable;

/**
 * @Title:
 *   查询条件
 * @description:
 * 
 * @author lnj 
 */
public class QueryCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//系统唯一标识
	private String systemId;
	
	//用户Id
	private String userId; 
	
	//用户名
	private String userName;
	
	//用户账号
	private String account;
	
	//用户类型
	private int userType=-1;
	
    //身份证号
	private String cid;
	
    //警员ID号
	private String policeId;
	
	//状态
	private int status;
	
	//角色Id
	private String roleId;
	
	//不包含角色的角色Id
	private String noRoleId;
	
	//角色名称
	private String roleName;
	
	//组织机构ID
	private String orgId;
	
	//机构代码 
	private String orgCode;
	
	//组织机构名称
	private String orgName;
	
	//权限资源ID
	private String resourceId;

	//权限资源名称
	private String resourceName;
	
	//说明
	private String note;
	
	//类型
	private int type;
	
	//上级Id
	private String superId;
	
	//服务名
	private String serviceName;
	
	//接口名
	private String interfaceName;
	
	//联系方式
	private String contact; 

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getNoRoleId() {
		return noRoleId;
	}

	public void setNoRoleId(String noRoleId) {
		this.noRoleId = noRoleId;
	}
}
