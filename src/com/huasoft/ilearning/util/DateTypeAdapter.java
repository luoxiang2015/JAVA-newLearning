package com.huasoft.ilearning.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateTypeAdapter implements JsonSerializer<Date>{
	private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public JsonElement serialize(Date src, Type arg1,
			JsonSerializationContext arg2) {
		String dateFormatAsString = format.format(src);  
		System.out.println(dateFormatAsString+"----");
        return new JsonPrimitive(dateFormatAsString);  
	}

	/*@Override
	public Date deserialize(JsonElement json, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		if (!(json instanceof JsonPrimitive)) {  
            throw new JsonParseException("The date should be a string value");  
        }  
  
        try {  
            Date date = format.parse(json.getAsString());  
            return date;  
        } catch (ParseException e) {  
            throw new JsonParseException(e);  
        }  
	}*/

}
