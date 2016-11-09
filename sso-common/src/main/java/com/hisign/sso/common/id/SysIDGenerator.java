package com.hisign.sso.common.id;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title:
 *   系统Id生成器
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月14日  上午10:56:52
 */
public class SysIDGenerator {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static SysIDGenerator instance = new SysIDGenerator();
	
	public static SysIDGenerator getInstance(){
		return instance;
	}
	
	private SysIDGenerator(){
	}
	
	/**
	 * 生成用户ID
	 * @return
	 */
	public String genUserId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 生成用户ID
	 * @param incrNum 增长系数，用于批量产生UserID
	 * @return
	 */
	public String genUserId(int incrNum){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 生成组织机构 Id
	 * @return
	 */
	public String genOrgId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 生成角色 Id
	 * @return
	 */
	public String genRoleId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 获取角色权限点关系Id
	 * @return
	 */
	public long genRoleResourceId(int incrNum){
		return System.currentTimeMillis()+incrNum;
	}
	
	/**
	 * 获取角色权限点关系Id
	 * @return
	 */
	public long genRoleResourceId(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取日志记录Id
	 * @return
	 */
	public long genLogRecordId(int incrNum){
		return System.currentTimeMillis()+incrNum;
	}
	
	/**
	 * 获取日志记录Id
	 * @return
	 */
	public long genLogRecordId(){
		return System.currentTimeMillis();
	}
	
	
	/**
	 * 生成科室Id
	 * @return
	 */
	public String genDeptId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			String id = SysIDGenerator.getInstance().genRoleId();
			System.out.println("id="+id+",lenth="+id.length());
		}
	}

}
