package domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import pack.JsonUtil;

public abstract class AbstractRequest {
	
	public List<NameValuePair> toNVPair() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				if (!(fields[i].get(this) == null || fields[i].get(this).toString().equals(""))) {
					list.add(new BasicNameValuePair(fields[i].getName(), fields[i].get(this).toString()));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public Map<String, String> toQueryMap() {
		Map<String, String> queryMap = new HashMap<String, String>();
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			try {
				if (!(fields[i].get(this) == null || fields[i].get(this).toString().equals(""))) {
					queryMap.put(fields[i].getName(), fields[i].get(this).toString());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return queryMap;
	}

}
