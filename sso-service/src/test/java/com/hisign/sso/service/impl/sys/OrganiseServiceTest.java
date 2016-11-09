package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.BaseUnit4Test;
import com.hisign.TokenServiceTest;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.service.sys.OrganiseService;
import com.hisign.sso.api.service.sys.TokenService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.common.util.LogUtil;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月8日  下午5:09:13
 */
public class OrganiseServiceTest extends BaseUnit4Test {
	/**
	 * 
	 */
	private static final Logger log = LoggerFactory.getLogger(OrganiseServiceTest.class);

	/**
	 * 
	 */
	@Autowired
	private OrganiseService organiseService;

	@Test
	public void testCall() {
		try {
		    Map<String,Object> map = new HashMap<String,Object>();
		    map.put("pageNum", 1);
		    map.put("pageSize", 10);
		    map.put("orderBy", "ORG_NAME DESC");
		    
		    map.put("orgName", "TEST2");

//			List<Organise> list= organiseService.queryByPagination(map);
//			log.info(list.toString());
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testAddBatch(){
		try {
			Organise org1 = new Organise();
			org1.setOrgId("31");
			org1.setName("组织结构11");
			org1.setSuperId("-1");
			org1.setType(0);
			org1.setStatus(0);
			
			Organise org2 = new Organise();
			org2.setOrgId("32");
			org2.setName("组织结构12");
			org2.setSuperId("-1");
			org2.setType(0);
			org2.setStatus(0);
			
			List<Organise> orgList = new ArrayList<Organise>();
			orgList.add(org1);
			orgList.add(org2);
			
			organiseService.addBatch(orgList);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testCreateOrganiseData(){
		List<Organise> list = new ArrayList<Organise>();
		String prefix = "模拟组织机构";
		for(int i = 0; i < 100; i++){
			Organise org1 = this.testCreateOrganise("-1",prefix+"1层", (i+1));
			list.add(org1);
			String superId1 = org1.getOrgId();
			for(int j =0; j < 25; j++){
				Organise org2 = this.testCreateOrganise(superId1,prefix+"2层", (j+1));
				list.add(org2);
				String superId2 = org2.getOrgId();
				for(int k =0; k < 4; k++){
					Organise org3 = this.testCreateOrganise(superId2,prefix+"3层", (k+1));
					list.add(org3);
				}
			}
		}
		
		try {
			System.out.println("begin add organise list="+list.size()+" ...");
			organiseService.addBatch(list);
			System.out.println("add organise list="+list.size()+" sucessfully!");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			Assert.fail();
		}
		
		System.out.println("end");
	}
	
	public Organise testCreateOrganise(String superId,String namePrefix,int index){
		Organise org1 = new Organise();
		String orgId = SysIDGenerator.getInstance().genOrgId();
		org1.setOrgId(orgId);
		String orgCode = orgId.substring(0,12);
		org1.setOrgCode(orgCode);
		org1.setName(namePrefix+index);
		org1.setSuperId(superId);
		org1.setType(0);
		org1.setStatus(0);
		
		return org1;
	}
	
}
