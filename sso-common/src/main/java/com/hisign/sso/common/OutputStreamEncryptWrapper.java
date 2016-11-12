/**
 * 
 */
package com.hisign.sso.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hisign.sdk.common.util.EncryptUtil;

/**
 * 输出流包装
 * @author chailiangzhi
 * @date 2015-12-26
 * 
 */
public class OutputStreamEncryptWrapper extends OutputStream {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 输出缓存
	 */
	private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	/**
	 * 持有输出流
	 */
	private final OutputStream output;

	/**
	 * 对称加密密钥
	 */
	private String secretKey;

	/**
	 * 构造函数
	 * @param output
	 */
	public OutputStreamEncryptWrapper(OutputStream output, String secretKey) {
		this.output = output;
		this.secretKey = secretKey;
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int i) throws IOException {
		logger.warn("call write(int i),无法加密");
		buffer.write(i);
		output.write(i);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		logger.warn("call write(byte[] b),加密");
		buffer.write(b);
		writeEncrypt();
		//		output.write(b);
	}

	/**
	 * 输出加密报文到客户端
	 * @throws IOException
	 */
	private void writeEncrypt() throws IOException {
		String charSetName = System.getProperty("charSetName");
		if(StringUtils.isEmpty(charSetName)){
			charSetName = "utf-8";
		}
		String rspMsgPlain = new String(buffer.toByteArray(), charSetName);
		JSONObject reqJson = JSONObject.fromObject(rspMsgPlain);
		String rspMsg = EncryptUtil.encryptMsg(reqJson, secretKey);
		byte[] bytes = rspMsg.getBytes(charSetName);
		output.write(bytes, 0, bytes.length);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		logger.warn("call write(byte[] b, int off, int len),加密:");
		buffer.write(b, off, len);
		writeEncrypt();
		//		output.write(b, off, len);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() throws IOException {
		output.flush();
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
		output.close();
	}

	/**
	 * 获得持有的输出数据
	 * @return
	 */
	public byte[] getBytes() {
		return buffer.toByteArray();
	}

}
