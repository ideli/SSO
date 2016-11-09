package com.hisign.sso.api.entity.sys;

import java.io.Serializable;
import java.util.List;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * @Title:
 *   组织机构
 * @description:
 * 
 * @Company: hisign.com.cn
 * @author lnj 
 * @create time：2016年7月6日  上午10:21:23
 * @version 1.0
 */
public class Organise extends SysBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//组织机构ID
	private String orgId;
	
	//机构代码 
	private String orgCode;
	
	//组织机构名称
	private String name;
	
	//上级组织机构ID
	private String superId;
	
	//系统唯一标识
	private String systemId;
	
	//组织机构类型:部门/组
	private int type;
	
	//状态 : 0:正常 1:不可用
	private int status;
	
	//组织机构描述
	private String note;
	
	//组织机构拼音全称
	private String pinyin;
	
	//组织机构拼音首字母
	private String firstLetter;
	
	private List<Organise> children;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<Organise> getChildren() {
		return children;
	}

	public void setChildren(List<Organise> children) {
		this.children = children;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}
