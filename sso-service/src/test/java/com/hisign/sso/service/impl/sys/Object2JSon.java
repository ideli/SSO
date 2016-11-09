package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.LogRecord;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.entity.sys.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月7日  上午10:56:20
 */
public class Object2JSon {
	
	public static void organise2JSon(){
		Organise org = new Organise();
		org.setOrgId("1");
		org.setName("组织结构测试");
		org.setSuperId("-1");
		org.setType(0);
		org.setStatus(0);
		
		JSONObject jsonObject = JSONObject.fromObject(org);
		System.out.println(jsonObject.toString());
	}
	
	public static void organises2JSon(){
		Organise org1 = new Organise();
		org1.setOrgId("11");
		org1.setName("组织结构11");
		org1.setSuperId("-1");
		org1.setType(0);
		org1.setStatus(0);
		
		Organise org2 = new Organise();
		org2.setOrgId("12");
		org2.setName("组织结构12");
		org2.setSuperId("-1");
		org2.setType(0);
		org2.setStatus(0);
		
		List<Organise> orgList = new ArrayList<Organise>();
		orgList.add(org1);
		orgList.add(org2);
		
		JSONArray jsonArray = JSONArray.fromObject(orgList);
		System.out.println(jsonArray.toString());
	}
	
	
	public static void map2JSon(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgName", "2");
		map.put("pageNum", 1);
		map.put("pageSize", 10);
		map.put("orderBy", "ORG_NAME desc");
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
	}
	
	public static void userInfo2Json(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("测试用户1");
		userInfo.setSex(0);
        userInfo.setCid("110111201607160001");
        userInfo.setIsPolice(1);
        userInfo.setPoliceId("030601");
		JSONObject jsonObject = JSONObject.fromObject(userInfo);
		
		System.out.println(jsonObject.toString());
	}
	
	public static void role2Json(){
		Role role = new Role();
		role.setRoleName("测试角色");
		role.setStatus(0);
		JSONObject jsonObject = JSONObject.fromObject(role);
		
		System.out.println(jsonObject.toString());
	}
	
	public static void logrecord2Json(){
		LogRecord logRecord = new LogRecord();
		logRecord.setSystemId("UAOP");
		logRecord.setAccount("admin");
		logRecord.setUserName("系统管理员");
		logRecord.setTerminalId("192.168.40.24");
		logRecord.setModuleId("01");
		logRecord.setOperateId("0001");
		logRecord.setOperateTime(System.currentTimeMillis());
		logRecord.setOperateResult(1);
		JSONObject jsonObject = JSONObject.fromObject(logRecord);
		System.out.println(jsonObject.toString());
	}
	
	public static void main(String[] args) {
//		organise2JSon();
//		organises2JSon();
//		map2JSon();
//		userInfo2Json();
//		logrecord2Json();
		role2Json();
	}

}
