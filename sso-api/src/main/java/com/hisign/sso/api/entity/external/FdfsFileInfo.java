/**
 * 
 */
package com.hisign.sso.api.entity.external;

/**
 * 文件信息实体
 * @author chailiangzhi
 * @date 2016-11-21
 * 
 */
public class FdfsFileInfo implements java.io.Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private int id;

	/**
	*
	*/
	private String fileNameLocal;

	/**
	* UrlEncode后的文件名
	*/
	private String fileNameLocalEnc;

	/**
	*
	*/
	private String fileNameRemote;

	/**
	* 防盗链
	*/
	private String fileToken;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileNameLocal() {
		return this.fileNameLocal;
	}

	public void setFileNameLocal(String fileNameLocal) {
		this.fileNameLocal = fileNameLocal;
	}

	/**
	 * @return the fileNameLocalEnc
	 */
	public String getFileNameLocalEnc() {
		return fileNameLocalEnc;
	}

	/**
	 * @param fileNameLocalEnc the fileNameLocalEnc to set
	 */
	public void setFileNameLocalEnc(String fileNameLocalEnc) {
		this.fileNameLocalEnc = fileNameLocalEnc;
	}

	public String getFileNameRemote() {
		return this.fileNameRemote;
	}

	public void setFileNameRemote(String fileNameRemote) {
		this.fileNameRemote = fileNameRemote;
	}

	/**
	 * @return the fileToken
	 */
	public String getFileToken() {
		return fileToken;
	}

	/**
	 * @param fileToken the fileToken to set
	 */
	public void setFileToken(String fileToken) {
		this.fileToken = fileToken;
	}

}
