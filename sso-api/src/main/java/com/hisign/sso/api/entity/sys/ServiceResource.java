package com.hisign.sso.api.entity.sys;

import java.io.Serializable;

/**
 * @Title:
 *   服务信息资源
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午10:29:30
 */
public class ServiceResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//系统唯一标识
	private String systemId;

	//权限资源ID
	private String resourceId;

	//服务名称
	private String serviceName;
	
	//父服务资源ID
	private String superId;
	
	//服务地址
	private String url;
	
	//图标
	private String icon;
	
	//接口名称
	private String interfaceName;
	
	//方法名称
	private String methodName;
	
	//服务是否可用 0:可用 1:不可用
	private int status;
	
	//可视状态
	private int visibleState;
	
	//排序字段
	private int orderNum;
	
	//服务描述
	private String note;

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
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
	
	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
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
	
}
