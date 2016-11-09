/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.rest.entity.MsgReq;
import com.hisign.sso.api.rest.entity.MsgRsp;
import com.hisign.sso.api.rest.entity.ReqHead;
import com.hisign.sso.api.rest.entity.Response;
import com.hisign.sso.api.rest.entity.RspHead;
import com.hisign.sso.api.service.sys.DictService;
import com.hisign.sso.common.util.JsonUtil;
import com.hisign.sso.persist.mapper.sys.SysDictMapper;
import com.hisign.sso.service.impl.BaseServiceImpl;

/**
 * 字典服务实现
 * @author chailiangzhi
 * @date 2016-7-25
 * 
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<SysDict> implements DictService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 系统字典表DAO
	 */
	@Autowired
	private SysDictMapper sysDictMapper;

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.DictService#dict(com.hisign.sso.api.rest.entity.MsgReq)
	 */
	@Override
	public MsgRsp dict(MsgReq msgReq) {

		ReqHead reqHead = msgReq.getReqHead();
		logger.debug(reqHead.getApiCode());
		logger.debug(reqHead.getReqTime());
		logger.debug("MsgRsp dict(MsgReq msgReq)");

		MsgRsp rsp = new MsgRsp();
		RspHead rspHead = this.genRspHead(reqHead);
		Response response = new Response();
		rspHead.setResponse(response);
		rsp.setRspHead(rspHead);

		SysDict sysDictPara = JsonUtil.gson.fromJson(msgReq.getReqBody(), SysDict.class);
		List<SysDict> dictList = sysDictMapper.find(sysDictPara);

		// 响应信息
		if (dictList != null && !dictList.isEmpty()) {
			rsp.setRspBody(JsonUtil.gson.toJson(dictList));
			response.setRspType("0");
			response.setRspCode("0000");
			response.setRspDesc("获取成功");
		} else {
			response.setRspType("1");
			response.setRspCode("1004");
			response.setRspDesc("未查到数据");
		}

		return rsp;
	}

}
