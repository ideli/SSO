/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

/**
 * 地址服务
 * @author chailiangzhi
 * @date 2016-6-23
 * 
 */
@Path("sys/address")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface AddressService {

	/**
	 * REST方式的对外接口，供PC桌面客户端或移动设备APP调用，
	 * 根据请求中的系统类型和服务代码列表查询统一地址配置表，
	 * 把相关的URL地址返回给客户端。
	 * @param sysCode
	 * @param serviceCodes
	 * @return
	 */
	@POST
	@Path("findaddr")
	public List<Map<String, String>> findServiceAddr(String sysCode, List<String> serviceCodes);
}
