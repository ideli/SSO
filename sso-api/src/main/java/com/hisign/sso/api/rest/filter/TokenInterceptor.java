package com.hisign.sso.api.rest.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.core.interception.PreMatchContainerRequestContext;
import org.jboss.resteasy.plugins.server.servlet.HttpServletInputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.hisign.sdk.common.Constant;
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.api.service.sys.TokenService;

/**
 * TOKEN检查的拦截器
 * @author chailiangzhi
 * @date 2015-12-26
 * 
 */
@Priority(Priorities.USER)
public class TokenInterceptor implements ContainerRequestFilter, ContainerResponseFilter, ReaderInterceptor, WriterInterceptor {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * TOKEN服务
	 */
	private volatile TokenService tokenService;

	/**
	 * 构造函数
	 */
	public TokenInterceptor() {
		//		tokenService = SpringContainer.getContext().getBean(TokenService.class);
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest;
		try {
			httpServletRequest = parseHttpServletRequest(requestContext);
		} catch (IllegalAccessException e) {
			logger.error("获取HttpServletRequest异常", e);
			throw new IOException("获取HttpServletRequest异常", e);
		}
		RequestContext.setRequestIp(httpServletRequest.getRemoteAddr());

		String method = requestContext.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			logger.info("POST method, dont filter");
			return;
		}
		checkHead(requestContext.getHeaders(), requestContext.getUriInfo());
	}
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.container.ContainerRequestContext, javax.ws.rs.container.ContainerResponseContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Expose-Headers", "Status");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,systemId,token");
		headers.add("Access-Control-Allow-Methods", "GET,POST");
		headers.add("Access-Control-Max-Age", 3600 * 24);
		
	}

	private HttpServletRequest parseHttpServletRequest(ContainerRequestContext requestContext)
			throws IllegalAccessException {
		PreMatchContainerRequestContext preMatchContainerRequestContext = (PreMatchContainerRequestContext) requestContext;
		HttpServletRequest httpServletRequest = (HttpServletRequest) FieldUtils.readDeclaredField(
				preMatchContainerRequestContext.getHttpRequest(), "request", true);
		return httpServletRequest;
	}

	/**
	 * 检查HTTP请求头
	 * @param headerMap
	 * @return
	 */
	private boolean checkHead(MultivaluedMap<String, String> headerMap, UriInfo uriInfo) {
		String systemId = null;
		String token = null;
		String uriPath = null;

		if (headerMap.containsKey("systemId")) {
			systemId = headerMap.get("systemId").get(0);
		}
		if (headerMap.containsKey("token")) {
			token = headerMap.get("token").get(0);
		}
		
		if(uriInfo != null){
			uriPath = uriInfo.getPath();
		}
		logger.info("systemId={},token={},url={}", systemId, token,uriPath);

		FilterErrorCode errorCode = this.validateRequest(systemId, token, uriInfo);
		if (errorCode.getErrCode().equals("0000")) {
			return true;
		} else {
			logger.info("systemId={},token={},url={} token check failed", systemId, token,uriPath);
			Map<String, Object> retEntity = new HashMap<String, Object>();
			//			retEntity.put("rspCode", errorCode.getErrCode());
			//			retEntity.put("rspDesc", errorCode.getErrDesc());
			retEntity.put("message", errorCode.getErrDesc());
			ServerResponse serverResponse = new ServerResponse(retEntity,
					javax.ws.rs.core.Response.Status.UNAUTHORIZED.getStatusCode(), new Headers<Object>());
			throw new NotAuthorizedException("token检查未通过", serverResponse);
		}
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ReaderInterceptor#aroundReadFrom(javax.ws.rs.ext.ReaderInterceptorContext)
	 */
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext) throws IOException,
			WebApplicationException {
		byte[] buffer = IOUtils.toByteArray(readerInterceptorContext.getInputStream());
		String reqMsg = new String(buffer, Constant.CHARSET_NAME);
		logger.info("request body is: \n" + reqMsg + "\n");
		readerInterceptorContext.setInputStream(new ByteArrayInputStream(buffer));

		checkHead(readerInterceptorContext.getHeaders(), parseUri(readerInterceptorContext));
		return readerInterceptorContext.proceed();
	}

	/**
	 * 解析资源URI
	 * @param readerInterceptorContext
	 * @return
	 */
	UriInfo parseUri(ReaderInterceptorContext readerInterceptorContext) {
		Field field = null;
		try {
			field = readerInterceptorContext.getClass().getDeclaredField("request");
		} catch (Exception e) {
			logger.error("获取request字段失败", e);
			return null;
		}
		field.setAccessible(true);
		try {
			Object req = field.get(readerInterceptorContext);
			HttpServletInputMessage request = (HttpServletInputMessage) req;
			return request.getUri();
		} catch (Exception e) {
			logger.error("获取URI失败", e);
			return null;
		}

	}

	/**
	 * 验证请求
	 * @param systemId
	 * @param token
	 * @return int 
	 */
	private synchronized FilterErrorCode validateRequest(String systemId, String token, UriInfo uriInfo) {
		// 登录和用户注册不检查TOKEN
		Set<String> whiteURLSet = URLWhiteList.getInstance().getUrlSet();
		for (String url : whiteURLSet) {
			if (uriInfo.getPath().indexOf(url) != -1) {
				logger.info("login service, not check token");
				return FilterErrorCode.SUCCESS;
			}
		}
		//		if (uriInfo.getPath().endsWith("sys/login") || uriInfo.getPath().endsWith("users/add")) {
		//			logger.info("login service, not check token");
		//			return FilterErrorCode.SUCCESS;
		//		}
		if (StringUtils.isEmpty(systemId)) {
			return FilterErrorCode.SYSCODE_FAIL;
		}
		if (StringUtils.isEmpty(token)) {
			return FilterErrorCode.TOKEN_FAIL;
		}
		//TODO  进行权限验证
		if (tokenService == null) {
			synchronized (this) {
				if (tokenService == null) {
					tokenService = initTokenService();
				}
			}
			//			tokenService = SpringContainer.getContext().getBean(TokenService.class);
		}
		logger.info("--- to checkToken systemId={},token={}",systemId,token);
		String retJsonStr = tokenService.checkToken(token, systemId, null);
		logger.info("retJsonStr={},", retJsonStr);
		JSONObject retJsonObj = JSONObject.fromObject(retJsonStr);
		String rspCode = retJsonObj.get(TokenService.RSP_CODE).toString();
		logger.info("rspCode={},", rspCode);
		if (TokenService.TOKEN_VALID.equals(rspCode)) {
			RequestContext.setLoginUserId(retJsonObj.optString(TokenService.USER_ID));
			RequestContext.setLoginAccount(retJsonObj.optString(TokenService.ACCOUNT));
			RequestContext.setSystemId(systemId);
			return FilterErrorCode.SUCCESS;
		} else {
			return FilterErrorCode.TOKEN_FAIL;
		}
	}

	private TokenService initTokenService() {
		ApplicationConfig application = SpringContainer.getContext().getBean(ApplicationConfig.class);
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();

		String zkconnect = System.getProperty("zkconnect");
		if (zkconnect == null) {
			SysConfigLoader.getInstance().loadSysConfig("UAOP");
			zkconnect = System.getProperty("zkconnect");
		}
		registry.setAddress("zookeeper://" + zkconnect);
		//		registry.setUsername("aaa");
		//		registry.setPassword("bbb");

		// 引用远程服务
		ReferenceConfig<TokenService> reference = new ReferenceConfig<TokenService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(TokenService.class);
		//		reference.setVersion("1.0.0");

		// 和本地bean一样使用xxxService
		TokenService tokenService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

		return tokenService;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.WriterInterceptor#aroundWriteTo(javax.ws.rs.ext.WriterInterceptorContext)
	 */
	public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException,
			WebApplicationException {
		OutputStreamWrapper wrapper = new OutputStreamWrapper(writerInterceptorContext.getOutputStream());
		// OutputStreamEncryptWrapper
		
		writerInterceptorContext.setOutputStream(wrapper);
		writerInterceptorContext.proceed();
		String rspMsg = new String(wrapper.getBytes(), "UTF-8");
		logger.info("rspMsg={}", rspMsg);
	}

}
