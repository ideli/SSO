/**
 * 
 */
package com.hisign.sso.api.rest.entity.sys;

import java.io.Serializable;

import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.entity.sys.UserOrganization;

/**
 * REST接口用户对象
 * @author chailiangzhi
 * @date 2016-8-16
 * 
 */
public class User extends UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String account;

	/**
	 * 
	 */
	private String pass;
	
	
	/**
	 * 用户类型
	 */
	private int userType;

	/**
	 * 
	 */
	private String orgId;
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 组织机构编码
	 */
	private String orgCode;
	
	/**
	 * 组织机构类型
	 */
	private int orgType;

	/**
	 * 
	 */
	private String roleName;
    

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getOrgType() {
		return orgType;
	}

	public void setOrgType(int orgType) {
		this.orgType = orgType;
	}
	
	
	/**
	 * 获取系统用户
	 * @return
	 */
	public SysUser fetchSysUser(){
		SysUser sysUser = new SysUser();
		sysUser.setAccount(this.account);
		sysUser.setUserName(this.getUserName());
		sysUser.setUserId(this.getUserId());
		sysUser.setUserType(this.userType);
		
		return sysUser;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UserInfo fetchUserInfo(){
		return this;
	}

	/**
	 * 获取用户组织机构关系
	 * @return
	 */
    public UserOrganization fetchUserOrganization(){
    	UserOrganization userOrg = new UserOrganization();
    	userOrg.setUserId(this.getUserId());
    	userOrg.setOrgId(this.getOrgId());
        return userOrg;
    }

}
