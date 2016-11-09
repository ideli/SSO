package com.hisign.sso.api.entity.sys;


public class SysUserRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String roleId;

	private String account;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysUserRole [roleId=");
		builder.append(roleId);
		builder.append(", account=");
		builder.append(account);
		builder.append("]");
		return builder.toString();
	}
}