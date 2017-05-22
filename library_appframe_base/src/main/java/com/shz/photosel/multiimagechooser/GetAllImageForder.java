package com.shz.photosel.multiimagechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gdswww.library.R;
import com.shz.photosel.ImageBean;
import com.shz.photosel.adapter.ListForderAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 获取所有图片文件夹
 * 
 * @author Administrator
 * 
 */
public class GetAllImageForder extends Activity {
	List<ImageBean> dirs = new ArrayList<ImageBean>();
	ArrayList<String> sel;
	ListView lvDatas;
	ListForderAdapter adapter;
	ArrayList<String> ignoreDirsName;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_forder);
		sel = getIntent()
				.getStringArrayListExtra(SelectPhotoActivity.DATA_LIST);
		ignoreDirsName = getIntent().getStringArrayListExtra("ignore");
		if (ignoreDirsName == null) {
			ignoreDirsName = new ArrayList<String>();
		}
		lvDatas = (ListView) findViewById(R.id.lv_datas);
		pd = new ProgressDialog(GetAllImageForder.this);
		pd.setCanceledOnTouchOutside(false);
		pd.setMessage("正在扫描图片……");
		pd.setCancelable(false);
		
		
		getImages();

		lvDatas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ImageBean data = (ImageBean) lvDatas
						.getItemAtPosition(position);
				String path = new File(data.getTopImagePath()).getParent();
				if (path != null && !path.trim().equals("")) {
					startActivityForResult(
							new Intent(getApplicationContext(),
									AlbumActivity.class)
									.putExtra("root", path)
									.putExtra(
											"maxSel",
											getIntent()
													.getIntExtra("maxSel", 1))
									.putStringArrayListExtra("dataList", sel),
							0);
				}
			}

		});
	}

	public void back(View v) {
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				setResult(RESULT_OK, data);
				finish();
			}
		}

	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SCAN_OK:
				pd.dismiss();
				adapter = new ListForderAdapter(getApplicationContext(),
						dirs = subGroupOfImage(mGruopMap));
				lvDatas.setAdapter(adapter);
				break;
			}

		}

	};

	Message msg = handler.obtainMessage();

	private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
	private final static int SCAN_OK = 1;

	/**
	 * 读取图片
	 */
	private void getImages() {
		pd.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = GetAllImageForder.this
						.getContentResolver();
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				if (mCursor == null) {
					return;
				}

				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					// 获取该图片的父路径名
					String parentName = new File(path).getParentFile()
							.getName();

					// 根据父路径名将图片放入到mGruopMap中
					if (!mGruopMap.containsKey(parentName)) {
						List<String> chileList = new ArrayList<String>();
						chileList.add(path);
						mGruopMap.put(parentName, chileList);
					} else {
						mGruopMap.get(parentName).add(path);
					}
				}

				// 通知Handler扫描图片完成
				handler.sendEmptyMessage(SCAN_OK);
				mCursor.close();
			}
		}).start();

	}

	/**
	 * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中 所以需要遍历HashMap将数据组装成List
	 * 
	 * @param mGruopMap
	 * @return
	 */
	private List<ImageBean> subGroupOfImage(
			HashMap<String, List<String>> mGruopMap) {
		if (mGruopMap.size() == 0) {
			return null;
		}
		List<ImageBean> list = new ArrayList<ImageBean>();

		Iterator<Map.Entry<String, List<String>>> it = mGruopMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<String>> entry = it.next();
			ImageBean mImageBean = new ImageBean();
			String key = entry.getKey();
			List<String> value = entry.getValue();

			mImageBean.setFolderName(key);
			mImageBean.setImageCounts(value.size());
			mImageBean.setTopImagePath(value.get(0));// 获取该组的第一张图片
			list.add(mImageBean);
		}
		return list;

	}

}
