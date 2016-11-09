package com.hisign.sso.api.rest.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 输出流包装
 * @author chailiangzhi
 * @date 2015-12-26
 * 
 */
public class OutputStreamWrapper extends OutputStream {

	/**
	 * 输出缓存
	 */
	private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
	/**
	 * 持有输出流
	 */
	private final OutputStream output;

	/**
	 * 构造函数
	 * @param output
	 */
	public OutputStreamWrapper(OutputStream output) {
		this.output = output;
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int i) throws IOException {
		buffer.write(i);
		output.write(i);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		buffer.write(b);
		output.write(b);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.write(b, off, len);
		output.write(b, off, len);
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
