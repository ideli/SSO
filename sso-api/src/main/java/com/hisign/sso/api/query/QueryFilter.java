package com.hisign.sso.api.query;

import java.io.Serializable;

import com.hisign.sdk.common.util.StringUtils;

/**
 * @Title:
 *   查询条件 带分页
 * @description:
 * 
 * @author lnj 
 */
public class QueryFilter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private QueryCondition queryCondition;
	
	//当前登录人
	private String userId;
	
	//页码
	private int pageNum;
	
	//页行数
	private int pageSize;
	
	//排序字段
	private String orderBy;
	
	//排序方式
	private String sort;

	public QueryCondition getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		if(!StringUtils.isEmpty(orderBy)){
			String newOrderBy = StringUtils.underscoreName(orderBy);
			return newOrderBy;
		}
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
