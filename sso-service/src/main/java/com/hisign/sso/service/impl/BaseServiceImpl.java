/**
 * 
 */
package com.hisign.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.api.Base;
import com.hisign.sso.api.rest.entity.ReqHead;
import com.hisign.sso.api.rest.entity.RspHead;
import com.hisign.sso.common.util.DateUtils;
import com.hisign.sso.common.util.DateUtils.DateFormat;

/**
 * 抽象服务类,提供空实现
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public class BaseServiceImpl<T> implements Base<T> {
	
	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 生成响应头
	 * @param reqHead
	 * @return
	 */
	public RspHead genRspHead(ReqHead reqHead) {
		log.debug("genRspHead");
		RspHead rspHead = new RspHead();
		rspHead.setReqSn(reqHead.getReqSn());
		rspHead.setRspTime(DateUtils.parse2String(new Date(), DateFormat.YMDHMS_SHORT));
		rspHead.setSecretKey(reqHead.getSecretKey());
		return rspHead;
	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#find(java.lang.T)
	 */
	@Override
	public List<T> find(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#getById(java.lang.String)
	 */
	@Override
	public T getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#getByCompositeId(java.lang.Object)
	 */
	@Override
	public T getByCompositeId(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#update(java.lang.T)
	 */
	@Override
	public void update(T t) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#add(java.lang.T)
	 */
	@Override
	public void add(T t) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.hisign.wise.api.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<T> list) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回分页后的数据
	 * @param List
	 * @param t
	 * @return
	 */
	public List<T> query(Map<String, Object> map) {
		return null;
	}

	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int count(Map<String, Object> map) {
		return -1;
	}
}
