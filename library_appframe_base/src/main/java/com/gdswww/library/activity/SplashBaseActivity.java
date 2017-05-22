package com.gdswww.library.activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.gdswww.library.R;
/**
  * @ClassName: SplashBaseActivity 
  * @Description: app 启动页基类
  * @author shihuanzhang 2335946896@qq_com
  * @date 2015年5月18日
 */
public abstract class SplashBaseActivity extends FragmentActivity{
	
	public ImageView ivSplash;
	
	//直接通过imageView对象加载图片
	public abstract void setupImageView(ImageView imageView);
	
	//加载完成要执行的操作
	public abstract void loadingFinish();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		ivSplash = (ImageView) findViewById(R.id.iv_splash);
		setupImageView(ivSplash);
	}

	
	public void startTask(long time){
		if(time < 1000){
			time = 1000;
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				loadingFinish();
			}
		}, time);
	}
	

}
