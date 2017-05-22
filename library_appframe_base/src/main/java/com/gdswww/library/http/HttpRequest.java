package com.gdswww.library.http;

import java.io.File;
import java.io.FileOutputStream;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpRequest {
	private static final int MAX_CONNECTION_COUNT = 10;
	private static final int TIMEOUT = 30 * 1000;
	private static AsyncHttpClient client = new AsyncHttpClient();
	static {
		client.setMaxConnections(MAX_CONNECTION_COUNT);
		client.setTimeout(TIMEOUT);
	}
	
	public interface RequestCallback {
		public void onSuccess(JSONObject response);
		public void onFailure(Throwable e, JSONObject errorResponse);
	}

	/**
	 * post 请求 
	 * @param urlString http URL
	 * @param res  请求结果回调
	 */
	public static void post(String urlString, AsyncHttpResponseHandler res) {
		client.post(urlString, res);
	}

	public static void post(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.post(urlString, params, res);
	}

	public static void post(String urlString, JsonHttpResponseHandler res) {
		client.post(urlString, res);
	}

	public static void post(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {
		client.post(urlString, params, res);
	}

	public static void post(String uString, BinaryHttpResponseHandler bHandler) {
		client.post(uString, bHandler);
	}

	public static void get(String urlString, AsyncHttpResponseHandler res) {
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.get(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) {
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {
		client.get(urlString, params, res);
	}

	public static void get(String uString, BinaryHttpResponseHandler bHandler) {
		client.get(uString, bHandler);
	}

	/**
	  * @Title: doRequest 
	  * @Description: 获取jsonObject
	  * @param httpUrl 请求url
	  * @param pd ProgressDialog对象
	  * @param RequestCallback 请求结果回调 
	 */
	public static void doRequest(String httpUrl, final ProgressDialog pd,
			final RequestCallback callback) {
		get(httpUrl, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				callback.onFailure(e, errorResponse);
			}

			@Override
			public void onSuccess(JSONObject response) {
				callback.onSuccess(response);
			}

			@Override
			public void onFinish() {
				pd.dismiss();
			}

			@Override
			public void onStart() {
				super.onStart();
				pd.show();
			}
		});
	}

	public static void doRequest(String httpUrl, RequestParams params,
			final ProgressDialog pd, final RequestCallback callback) {
		get(httpUrl, params, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				callback.onFailure(e, errorResponse);
			}

			@Override
			public void onSuccess(JSONObject response) {
				callback.onSuccess(response);
			}

			@Override
			public void onFinish() {
				pd.dismiss();
			}

			@Override
			public void onStart() {
				super.onStart();
				pd.show();
			}
		});
	}

	public static void doRequest(String httpUrl, final RequestCallback callback) {
		get(httpUrl, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				callback.onFailure(e, errorResponse);
			}

			@Override
			public void onSuccess(JSONObject response) {
				callback.onSuccess(response);
			}
		});
	}

	/**
	 * @Title: doRequest
	 * @Description: 获取json对象
	 * @param httpUrl 请求url
	 * @param params 请求参数列表   
	 * @param RequestCallback 请求结果回调         
	 */
	public static void doRequest(String httpUrl, RequestParams params,
			final RequestCallback callback) {
		get(httpUrl, params, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				callback.onFailure(e, errorResponse);
			}

			@Override
			public void onSuccess(JSONObject response) {
				callback.onSuccess(response);
			}
		});
	}

	/**
	  * @Title: downloadFile 
	  * @Description: 下载文件
	  * @param @param httpUrl 
	  * @param @param dirName 目录
	  * @param @param fileName 文件名
	 */
	public static void downloadFile(String httpUrl,final String dirName,final String fileName) {
		get(httpUrl, new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] arg0) {
				File file = Environment.getExternalStorageDirectory();
				File file2 = new File(file, dirName);
				file2.mkdir();
				file2 = new File(file2, fileName);
				try {
					FileOutputStream oStream = new FileOutputStream(file2);
					oStream.write(arg0);
					oStream.flush();
					oStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
