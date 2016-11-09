package com.hisign.sso.api.rest.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 将普通异常包装为REST异常
 * @author chailiangzhi
 * @date 2016-8-12
 * 
 */
public class RestBusinessExceptionMapper implements ExceptionMapper<RestBusinessException> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(RestBusinessException exception) {
		// TODO Auto-generated method stub

		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("message", exception.getMessage());
		return Response.status(Status.OK).header("Status", exception.getStatusCode().getStatusCode()).entity(entityMap)
				.type(MediaType.APPLICATION_JSON).build();
		//		return null;
	}

}
