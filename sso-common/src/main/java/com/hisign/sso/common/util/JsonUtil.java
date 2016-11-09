/**
 * 
 */
package com.hisign.sso.common.util;

import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hisign.sso.common.gson.CustDateTypeAdapter;
import com.hisign.sso.common.gson.CustStringConverter;

/**
 * JSON和对象转换工具类
 * @author chailiangzhi
 * @date 2015-10-10
 * 
 */
public class JsonUtil {
	/**
	 * 处理JSON字符串与Java对象转换
	 */
	public static Gson gson = initGson();

	/**
	 * 初始化gson
	 * @return
	 */
	private static Gson initGson() {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new CustDateTypeAdapter())
				.registerTypeAdapter(String.class, new CustStringConverter()).setPrettyPrinting();
		gb.setDateFormat(CustDateTypeAdapter.YMDHMS_SHORT);
		Gson gson = gb.create();
		return gson;
	}

	/**
	 * 递归遍历，检查JSON报文的每个字段内容
	 * @param json
	 */
	public static void checkJSONObject(JSONObject json) {
		Iterator<Map.Entry<String, Object>> entrys = json.entrySet().iterator();
		while (entrys.hasNext()) {
			Map.Entry<String, Object> entry = entrys.next();
			Object value = entry.getValue();
			if (value instanceof JSONObject) {
				JSONObject jsonObj = (JSONObject) value;
				checkJSONObject(jsonObj);
			} else if (value instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) value;
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = (JSONObject) jsonArray.get(i);
					checkJSONObject(jsonObj);
				}
			} else {
				changeBigValue(entry, value);
			}
		}
	}

	/**
	 * 把太长的字段忽略掉内容,比如BASE64图片
	 * @param entry
	 * @param value
	 */
	private static void changeBigValue(Map.Entry<String, Object> entry, Object value) {
		if (value != null) {
			int len = value.toString().length();
			if (len > 300) {
				entry.setValue("len_" + len);
			}
		}
	}
}
