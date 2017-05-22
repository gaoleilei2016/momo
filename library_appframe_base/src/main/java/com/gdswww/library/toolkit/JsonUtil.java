package com.gdswww.library.toolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	private JsonUtil() {
	};

	@SuppressWarnings("unchecked")
	/**
	 * @Title: jsonArray2HashMapList 
	 * @Description: 将jsonArray转换为ArrayList<HashMap<String, String>>
	 */
	public static ArrayList<HashMap<String, String>> jsonArray2HashMapList(
			JSONArray arrays) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
		if (arrays != null && arrays.length() > 0) {
			JSONObject json = null;
			HashMap<String, String> map = null;
			for (int i = 0; i < arrays.length(); i++) {
				try {
					json = arrays.getJSONObject(i);
					map = new HashMap<String, String>();
					Iterator<String> it = json.keys();
					while (it.hasNext()) {
						String key = it.next();
						map.put(key, json.getString(key));
					}
					maps.add(map);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return maps;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> jsonObject2HashMap(JSONObject json) {
		HashMap<String, String> map = null;
		if (json != null) {
			try {
				map = new HashMap<String, String>();
				Iterator<String> it = json.keys();
				while (it.hasNext()) {
					String key = it.next();
					map.put(key, json.getString(key));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		return map;
	}
}
