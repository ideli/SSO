package com.hisign.sso.api.rest.filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.common.util.CommonIOUtils;

/**
 * @Title:
 *  URL地址白名单
 * @description:
 * 
 * @author lnj 
 */
public class URLWhiteList {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String CONFIG_FILE = "urlwhitelist.conf";
	
	private static URLWhiteList instance = new URLWhiteList();
	
	//白名单url列表
	private Set<String> urlSet = null;
	
	public synchronized static URLWhiteList getInstance(){
		if(instance == null){
			instance =  new URLWhiteList();
		}
		return instance;
	}

	private URLWhiteList(){
		init();
		log.info("URLWhiteList="+urlSet.toString());
	}
	
	private void init(){
		Set<String> set = this.loadWhiteListFromConfigFile();
		if(set != null && set.size() > 0){
			this.urlSet = set;
		}else{
			this.urlSet = this.getDefaultWhiteList();
		}
	}
	
	/**
	 * 从配置文件中加载白名单
	 * @return
	 */
	private Set<String> loadWhiteListFromConfigFile(){
		InputStream is = null;
		BufferedReader br = null;
		try{
			URL url = this.getClass().getClassLoader().getResource(CONFIG_FILE);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			Set<String> set = new HashSet<String>();
			while((line = br.readLine()) != null){
				if(line.trim().equals("") || line.trim().startsWith("#")){
					continue;
				}
				
				set.add(line.trim());
			}
			
			if(set.size() > 0){
				log.info("load url white list from "+CONFIG_FILE+" successfully!");
			}
			return set;
		}catch(Exception ex){
        	log.warn("can't load url white list from "+CONFIG_FILE);
        }finally{
            CommonIOUtils.close(br, is);
        }
		
		return null;
	}
	
	/**
	 * 获取默认白名单
	 * @return
	 */
	private Set<String> getDefaultWhiteList(){
		Set<String> set = new HashSet<String>();
		set.add("sys/login");
		set.add("users/add");
		set.add("sys/users/account");
		set.add("sys/users/cid");
		set.add("sys/users/policeId");
		set.add("sys/organises/alltree");
		return set;
	}

	public Set<String> getUrlSet() {
		return urlSet;
	}
}
