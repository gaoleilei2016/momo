package com.gdswww.library.activity;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BaseHandler extends Handler {
	private Activity mActivity;
	private String methodName;
	BaseHandler(Activity activity,String methodName) {
		this.mActivity = activity;
		this.methodName = methodName;
	}
	@Override
	public void handleMessage(Message msg) {
		if(methodName!=null && ! methodName.trim().equals("")){
			try {
				mActivity.getClass().getDeclaredMethod(methodName, Message.class).
				invoke(mActivity, msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("BaseHandler", "handle exception:"+e.getMessage());
			}
		}
	}
}