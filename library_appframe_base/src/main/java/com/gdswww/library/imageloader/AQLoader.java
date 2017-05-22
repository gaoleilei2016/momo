package com.gdswww.library.imageloader;
import java.io.File;

import com.androidquery.AQuery;
import com.androidquery.util.AQUtility;

import android.content.Context;
import android.widget.ImageView;

public class AQLoader {
	public static AQLoader instance;
	public static Context context;
	private static AQuery aq;
	private static boolean memCache,fileCache;
	private AQLoader(){}
	/**
	 * 
	  * @Title: getInstance 
	  * @param c 上下文对象
	  * @param cacheDir 缓存目录
	  * @param memCache 内存缓存
	  * @param fileCache 文件缓存
	 */
	public static AQLoader getInstance(Context c,String cacheDir,boolean memCache,
			boolean fileCache){
		if(instance==null){
			context = c;
			instance = new AQLoader(); 
			AQUtility.setCacheDir(new File(cacheDir));
			aq = new AQuery(context);
			AQLoader.memCache = memCache;
			AQLoader.fileCache = fileCache;
		}
		return instance;
	}
	public void loadImageByURL(String url,ImageView imageView,int errorImagId,int width){
		aq.id(imageView).image(url, memCache, fileCache, width, errorImagId);
	}
	
	public void loadImageByURL(String url,ImageView imageView,int width){
		aq.id(imageView).image(url, memCache, fileCache, width, 0);
	}
	
	public void loadImageByURL(String url,ImageView imageView){
		aq.id(imageView).image(url,memCache, fileCache);
	}
	
	public void loadImageByFile(String path,ImageView imageView,int width){
		aq.id(imageView).image(new File(path), width);
	}
	
	
	public void loadImageByDrawableId(int drawableId,ImageView imageView){
		aq.id(imageView).image(drawableId);
	}
	
}
