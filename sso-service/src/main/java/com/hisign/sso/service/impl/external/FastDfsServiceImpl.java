/**
 * 
 */
package com.hisign.sso.service.impl.external;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.api.entity.external.FdfsFileInfo;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.external.FileService;

/**
 * 文件服务器接口
 * 封装fastdfs上传文件的API提供HTTP接口，
 * 下载文件由nginx提供HTTP接口
 * @author chailiangzhi
 * @date 2016-11-21
 * 
 */
@Path("file")
@Produces({ MediaType.APPLICATION_JSON })
public class FastDfsServiceImpl implements FileService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.external.FileService#uploadFile(org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput)
	 */
	@Override
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Map<String, Object> uploadFile(MultipartFormDataInput input) {
		Map<String, List<InputPart>> formParts = input.getFormDataMap();
		List<InputPart> inPart = formParts.get("file");
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (inPart == null) {
			logger.error("未获取到文件");
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "未获取到文件");
		}
		for (InputPart inputPart : inPart) {
			InputStream istream = null;
			try {
				// Retrieve headers, read the Content-Disposition header to obtain the original name of the file
				MultivaluedMap<String, String> headers = inputPart.getHeaders();
				String fileName = parseFileName(headers);
				if (StringUtils.isEmpty(fileName)) {
					logger.error("文件名为空");
					throw new RestBusinessException(Status.NOT_ACCEPTABLE, "文件名为空");
				}
				// Handle the body of that part with an InputStream
				istream = inputPart.getBody(InputStream.class, null);
				if (saveFile(istream, fileName)) {
					retMap.put("message", "成功");
					return retMap;
				} else {
					logger.error("文件保存失败");
					throw new RestBusinessException(Status.NOT_ACCEPTABLE, "文件保存失败");
				}

			} catch (IOException e) {
				logger.error("文件保存异常", e);
				throw new RestBusinessException(Status.NOT_ACCEPTABLE, "文件保存异常");
			} finally {
				if (istream != null) {
					try {
						istream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return retMap;
	}

	/**
	 * Parse Content-Disposition header to get the original file name
	 * @param headers
	 * @return
	 */
	private String parseFileName(MultivaluedMap<String, String> headers) {
		String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
		for (String name : contentDispositionHeader) {
			if (name.trim().startsWith("filename")) {
				try {
					name = URLDecoder.decode(name, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("编码不支持", e);
				}
				String[] tmp = name.split("=");
				String fileName = tmp[1].trim().replaceAll("\"", "");
				return fileName;
			}
		}
		return "";
	}

	/**
	 * save uploaded file to a defined location on the server
	 * @param uploadedInputStream
	 * @param fileName
	 */
	private boolean saveFile(InputStream uploadedInputStream, String fileName) throws IOException {
		byte[] buff = new byte[1024];
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int len = uploadedInputStream.read(buff);
		while (len != -1) {
			bout.write(buff, 0, len);
			len = uploadedInputStream.read(buff);
		}
		byte[] file_buff = bout.toByteArray();//获取内存缓冲中的数据
		try {
			ClientGlobal.init("fdfs_client.conf");

			logger.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
			logger.info("charset=" + ClientGlobal.g_charset);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient client = new StorageClient(trackerServer, storageServer);
			String[] results = client.upload_file(file_buff, null, null);
			String group_name = results[0];
			String remote_filename = results[1];

			logger.info("group_name: " + group_name + ", remote_filename: " + remote_filename);

			FdfsFileInfo fInfo = new FdfsFileInfo();
			fInfo.setFileNameLocal(fileName);
			fInfo.setFileNameRemote(remote_filename);
			return true;
		} catch (Exception e) {
			logger.error("保存文件失败", e);
			return false;
		}
	}
}
