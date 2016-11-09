package com.hisign.sso.api.entity.sys;


public class UserOrganization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String orgId;

	private int type;

	private int admin;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOrganization [userId=");
		builder.append(userId);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", admin=");
		builder.append(admin);
		builder.append("]");
		return builder.toString();
	}
	
}