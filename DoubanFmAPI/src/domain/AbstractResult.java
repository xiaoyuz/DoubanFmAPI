package domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import data.AbstractData;

public abstract class AbstractResult {
	
	public String toString() {
		String json = JSON.toJSONString(this);
		return json;
	}

}
