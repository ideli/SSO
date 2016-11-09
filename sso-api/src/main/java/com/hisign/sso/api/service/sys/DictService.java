/**
 * 
 */
package com.hisign.sso.api.service.sys;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.rest.entity.MsgReq;
import com.hisign.sso.api.rest.entity.MsgRsp;

/**
 * 字典服务，
 * REST字典接口
 * @author chailiangzhi
 * @date 2016-6-22
 * 
 */
@Path("sys")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface DictService {

	/**
	 * REST字典接口
	 * @param msgReq
	 * @return
	 */
	@POST
	@Path("dict")
	public MsgRsp dict(MsgReq msgReq);

}
