package com.shz.photosel;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Environment;

public class MyApplication{
	
	public static String CACHE_DIR = Environment.getExternalStorageDirectory()
			.getPath() + "/MultiImageCache/";
	public  static ArrayList<HashMap<String, String>> dirLit = new ArrayList<HashMap<String,String>>();
	public static MyApplication instatnce;
	
	public static MyApplication getInstance(){
		if(instatnce==null){
			instatnce = new MyApplication();
		}
		return instatnce;
	}
	
	
	
	public  boolean  setPutDir(HashMap<String, String> data){
		if(data!=null && dirLit!=null && ! dirLit.contains(data) ){
			dirLit.add(data);
			return true;
		}
		return false;
	}
	public  ArrayList<HashMap<String, String>> getDirLit(){
		return dirLit;
	}

}
