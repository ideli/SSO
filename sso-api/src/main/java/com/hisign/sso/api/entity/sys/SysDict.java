package com.hisign.sso.api.entity.sys;

import java.util.Date;

/**
 * 系统字典表
 * @author chailiangzhi
 * @date 2016-7-25
 * 
 */
public class SysDict implements java.io.Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private String id;

	/**
	*
	*/
	private String sysCode;

	/**
	*
	*/
	private String dictLevel;

	/**
	*
	*/
	private String dictKey;

	/**
	*
	*/
	private String parentKey;

	/**
	*
	*/
	private String rootKey;

	/**
	*
	*/
	private String dictValue1;

	/**
	*
	*/
	private String dictValue2;

	/**
	*
	*/
	private String dictValue3;

	/**
	*
	*/
	private String leafFlag;

	/**
	*
	*/
	private String downloadFlag;

	/**
	*
	*/
	private String readonlyFlag;

	/**
	*
	*/
	private String dictSort;

	/**
	*
	*/
	private String dictPy;

	/**
	*
	*/
	private String openFlag;

	/**
	*
	*/
	private String remark;

	/**
	*
	*/
	private String createUser;

	/**
	*
	*/
	private Date createDatetime;

	/**
	*
	*/
	private String updateUser;

	/**
	*
	*/
	private Date updateDatetime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getDictLevel() {
		return this.dictLevel;
	}

	public void setDictLevel(String dictLevel) {
		this.dictLevel = dictLevel;
	}

	public String getDictKey() {
		return this.dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getParentKey() {
		return this.parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getRootKey() {
		return this.rootKey;
	}

	public void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}

	public String getDictValue1() {
		return this.dictValue1;
	}

	public void setDictValue1(String dictValue1) {
		this.dictValue1 = dictValue1;
	}

	public String getDictValue2() {
		return this.dictValue2;
	}

	public void setDictValue2(String dictValue2) {
		this.dictValue2 = dictValue2;
	}

	public String getDictValue3() {
		return this.dictValue3;
	}

	public void setDictValue3(String dictValue3) {
		this.dictValue3 = dictValue3;
	}

	public String getLeafFlag() {
		return this.leafFlag;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	public String getDownloadFlag() {
		return this.downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getReadonlyFlag() {
		return this.readonlyFlag;
	}

	public void setReadonlyFlag(String readonlyFlag) {
		this.readonlyFlag = readonlyFlag;
	}

	public String getDictSort() {
		return this.dictSort;
	}

	public void setDictSort(String dictSort) {
		this.dictSort = dictSort;
	}

	public String getDictPy() {
		return this.dictPy;
	}

	public void setDictPy(String dictPy) {
		this.dictPy = dictPy;
	}

	public String getOpenFlag() {
		return this.openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}