/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hisign.sso.common.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

/**
 * This type adapter supports three subclasses of date: Date, Timestamp, and
 * java.sql.Date.
 * 
 * @author Inderjeet Singh
 * @author Joel Leitch
 */
public class CustDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

	// TODO: migrate to streaming adapter
	/**
	 * 
	 */
	public static final String YMDHMS_SHORT = "yyyyMMddHHmmss";

	/**
	 * 
	 */
	private final DateFormat custFormat = new SimpleDateFormat(YMDHMS_SHORT);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		String dateFormatAsString = custFormat.format(src);
		return new JsonPrimitive(dateFormatAsString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (!(json instanceof JsonPrimitive)) {
			throw new JsonParseException("The date should be a string value");
		}
		Date date = deserializeToDate(json);
		if (typeOfT == Date.class) {
			return date;
		} else if (typeOfT == Timestamp.class) {
			return new Timestamp(date.getTime());
		} else if (typeOfT == java.sql.Date.class) {
			return new java.sql.Date(date.getTime());
		} else {
			throw new IllegalArgumentException(getClass() + " cannot deserialize to " + typeOfT);
		}
	}

	/**
	 * @param json
	 * @return
	 */
	private Date deserializeToDate(JsonElement json) {
		try {
			String dateStr = json.getAsString();
			if (dateStr.trim().equals("")) {
				return null;
			} else {
				return custFormat.parse(dateStr);
			}
		} catch (ParseException e) {
			throw new JsonSyntaxException(json.getAsString(), e);
		}
	}

}
