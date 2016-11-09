package com.hisign.sso.common.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * GSON序列化和反序列化String类型的自定义转换类
 * @author chailiangzhi
 * @date 2015-9-10
 * 
 */
public class CustStringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
	/** 
	 * 序列化时把""当做null处理
	 */
	public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
		if (src == null || src.equals("")) {
			return null;
		} else {
			return new JsonPrimitive(src.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return json.getAsJsonPrimitive().getAsString();
	}
}