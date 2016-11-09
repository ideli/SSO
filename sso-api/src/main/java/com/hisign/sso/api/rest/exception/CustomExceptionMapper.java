package com.hisign.sso.api.rest.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.jboss.resteasy.spi.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将普通异常包装为REST异常
 * @author chailiangzhi
 * @date 2016-8-12
 * 
 */
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		if (exception instanceof Failure) {
			Failure failure = (Failure) exception;
			return failure.getResponse();
		}
		logger.error("捕获异常", exception);
		String message = exception.getMessage();
		String[] messages = message.split(":", 2);
		String statusCode = "500";
		String messageText = message;
		if (messages.length == 2 && messages[0].length() == 3) {
			statusCode = messages[0];
			messageText = messages[1];
		}
		//		Integer statusCodeInt = Integer.valueOf(statusCode);
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("message", messageText);
		return Response.status(Status.OK).header("Status", statusCode).entity(entityMap)
				.type(MediaType.APPLICATION_JSON).build();
		//		return null;
	}

}
