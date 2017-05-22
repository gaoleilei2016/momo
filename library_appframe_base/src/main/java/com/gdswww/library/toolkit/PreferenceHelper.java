package com.gdswww.library.toolkit;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * 偏好设置工具
 * 
 * @author shi
 * 
 */
public class PreferenceHelper {
	protected static SharedPreferences setting;
	protected PreferenceHelper(){};
	/**
	 * 获取一个实例
	 * @param context 上下文对象
	 * @param configName 配置文件名字
	 * @return
	 */
	public static PreferenceHelper getInstance(Context context,String configName){
		if(configName==null || configName.trim().equals("")){
			throw new NullPointerException("请提供配置文件名");
		}
		setting = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
		return new PreferenceHelper();
	}
	
	public void setStringValue(String key, String value) {
		setting.edit().putString(key, value).commit();
	}
	public void clearAllConfig(){
		if(setting.edit()!=null){
			setting.edit().clear().commit();;
		}
	}

	public  String getStringValue(String key) {
		return setting.getString(key, "");
	}
	
	public void setIntValue(String key, int value) {
		setting.edit().putInt(key, value).commit();
	}

	
	public  int getIntValue(String key) {
		return setting.getInt(key, 0);
	}

	
	public void setBooleanValue(String key,boolean value) {
		setting.edit().putBoolean(key, value).commit();
	}

	
	public  boolean getBooleanValue(String key) {
		return setting.getBoolean(key, false);
	}
	
}
