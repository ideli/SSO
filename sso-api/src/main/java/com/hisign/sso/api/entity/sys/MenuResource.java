package com.hisign.sso.api.entity.sys;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 菜单资源表
 * @author chailiangzhi
 * @date 2016-7-5
 * 
 */
public class MenuResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	//业务系统唯一标识
	@JsonIgnore
	private String systemId;

	//权限资源ID
	private String resourceId;

	/**
	 * 权限资源名称
	 */
	@JsonIgnore
	private String resourceName;
	
	/**
	 * 权限资源英文名称
	 */
	private String resourceEnName;

	/**
	 * 上级权限资源ID
	 */
	@JsonIgnore
	private String superId;

	/**
	 * 名称(纯为了展示使用)
	 */
	private String name;
	
	/**
	 * 权限点对应url
	 */
	@JsonIgnore
	private String url;
	
	/**
	 * 权限点对应url 纯粹是为展示
	 */
	private String state;
	
	/**
	 * 标签
	 */
	private String icon;
	
	/**
	 * 可视状态
	 */
	@JsonIgnore
	private int visibleState;
	
	/**
	 * 排序字段
	 */
	@JsonIgnore
	private int orderNum;

	/**
	 * 说明
	 */
	@JsonIgnore
	private String note;
	
	/**
	 * 菜单类型  0:菜单 1:按钮
	 */
	private int menuType;
	
	//子菜单权限
	private List<MenuResource> children;

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
		this.name = resourceName;
	}

	public String getSuperId() {
		return this.superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
		this.state = url;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getVisibleState() {
		return visibleState;
	}

	public void setVisibleState(int visibleState) {
		this.visibleState = visibleState;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public List<MenuResource> getChildren() {
		return children;
	}

	public void setChildren(List<MenuResource> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public String getResourceEnName() {
		return resourceEnName;
	}

	public void setResourceEnName(String resourceEnName) {
		this.resourceEnName = resourceEnName;
	}
	
}