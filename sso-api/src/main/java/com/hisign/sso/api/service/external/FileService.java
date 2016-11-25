/**
 * 
 */
package com.hisign.sso.api.service.external;

import java.util.Map;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * 文件服务器接口
 * 封装fastdfs上传文件的API提供HTTP接口，
 * 下载文件由nginx提供HTTP接口
 * @author chailiangzhi
 * @date 2016-11-21
 * 
 */
public interface FileService {

	/**
	 * 封装fastdfs上传文件的API提供HTTP接口，
	 * @param input
	 * @return
	 */
	public Map<String, Object> uploadFile(MultipartFormDataInput input);
}
