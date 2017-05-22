package com.gdswww.library.http;
import java.util.Map;

import org.json.JSONObject;

import android.app.ProgressDialog;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class AQRequest {
	public interface RequestCallback {
		public void onSuccess(JSONObject response);
		public void onFailure(int code, String message);
	}

	public static void doRequest(AQuery aq,String httpUrl, final ProgressDialog pd,
			final RequestCallback callback) {
		aq.progress(pd).ajax(httpUrl, JSONObject.class, new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject response,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				super.callback(url, response, status);
				callback.onSuccess(response);
			}
			@Override
			public void failure(int code, String message) {
				// TODO Auto-generated method stub
				super.failure(code, message);
				callback.onFailure(code, message);
			}
		});
	}
	public static void doRequest(AQuery aq,String httpUrl,
			final RequestCallback callback) {
		aq.ajax(httpUrl, JSONObject.class, new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject response,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				super.callback(url, response, status);
				callback.onSuccess(response);
			}
			@Override
			public void failure(int code, String message) {
				// TODO Auto-generated method stub
				super.failure(code, message);
				callback.onFailure(code, message);
			}
		});
	}
	
	public static void doRequest(AQuery aq,String httpUrl,Map<String, Object> params, final ProgressDialog pd,
			final RequestCallback callback) {
		aq.progress(pd).ajax(httpUrl, params,JSONObject.class,new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject response,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				super.callback(url, response, status);
				callback.onSuccess(response);
			}
			@Override
			public void failure(int code, String message) {
				// TODO Auto-generated method stub
				super.failure(code, message);
				callback.onFailure(code, message);
			}
		});
	}

}
