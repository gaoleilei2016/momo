package gdswww.com.momo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;


public class PreferenceUtil {
	private static SharedPreferences setting;
	private static SharedPreferences.Editor pen;
	/**
	 * 根据键获取值JSON
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getString_Json_Value(Context context, String key,String key2) {
		// 只被本应用使用
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		try {
			JSONObject json =  new JSONObject(setting.getString(key, ""));
			return json.getString(key2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public static void setStringValue(Context context, String key, String value) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putString(key, value);
		pen.commit();
	}
	
	public static void clearAllPrefer(){
		if(pen!=null){
			pen.clear();
		}
	}

	public static String getStringValue(Context context, String key) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		return setting.getString(key, "");
	}
	
	public static void setIntValue(Context context, String key, int value) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putInt(key, value);
		pen.commit();
	}
	public static int getIntValue(Context context, String key) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		return setting.getInt(key, 0);
	}

	public static void setBooleanValue(Context context, String key,
			boolean value) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.putBoolean(key, value);
		pen.commit();
	}
	public static void clearJsonValue(Context context, String key,
			boolean value) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		pen = setting.edit();
		pen.clear();
		pen.commit();
	}

	public static boolean getBooleanValue(Context context, String key) {
		setting = context.getSharedPreferences("gdswww_user", Context.MODE_PRIVATE);
		return setting.getBoolean(key, false);
	}
	
	public static String getPrefersetbyPreferFileName(String fileName, String key,
			Context context) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return  preferences.getString(key, "gdswww_no");
	}
	
	public static void setPrefersetbyPreferFileName(String fileName, String key,String value,
			Context context) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		preferences.edit().putString(key, value).commit();
	}
}
