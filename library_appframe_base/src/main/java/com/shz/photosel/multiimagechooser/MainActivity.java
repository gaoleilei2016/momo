package com.shz.photosel.multiimagechooser;

import java.util.ArrayList;

import com.gdswww.library.R;
import com.shz.photosel.adapter.GridImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends Activity {

	private GridView gridView;
	private ArrayList<String> dataList = new ArrayList<String>();
	private GridImageAdapter gridImageAdapter;
	private static final int GET_PHOTO_CODE = 100;
	private int MAX = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initListener();

	}

	private void init() {
		gridView = (GridView) findViewById(R.id.myGrid);
		dataList.add("camera_default");
		gridImageAdapter = new GridImageAdapter(this, dataList);
		gridView.setAdapter(gridImageAdapter);
	}

	private void getPhotoIntent() {
		Intent intent = new Intent(getApplicationContext(),
				SelectPhotoActivity.class);
		intent.putExtra(SelectPhotoActivity.DATA_LIST, dataList);
		intent.putExtra(SelectPhotoActivity.MAX_SEL, MAX);
		startActivityForResult(intent, GET_PHOTO_CODE);
	}

	private void initListener() {

		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (dataList.get(position).equals(
						SelectPhotoActivity.CAMERA_DEFAULT)) {
					getPhotoIntent();
				} else {
					startActivityForResult((new Intent(getApplicationContext(),
							ShowSelectSingleImageActivity.class)).putExtra(
							"path", dataList.get(position)), 300);
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == GET_PHOTO_CODE && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			@SuppressWarnings("unchecked")
			ArrayList<String> tDataList = (ArrayList<String>) bundle
					.getSerializable(SelectPhotoActivity.DATA_LIST);
			if (tDataList != null) {
				dataList.clear();
				dataList.addAll(tDataList);
				gridImageAdapter.notifyDataSetChanged();
			}
		} else if (requestCode == 300 && resultCode == RESULT_OK) {
			if (data.getBooleanExtra("del", false)) {
				dataList.remove(data.getStringExtra("path"));
				if (dataList.contains(SelectPhotoActivity.CAMERA_DEFAULT)) {
					dataList.remove(SelectPhotoActivity.CAMERA_DEFAULT);
				}
				if (dataList.size() < MAX) {
					dataList.add(SelectPhotoActivity.CAMERA_DEFAULT);
				}
				gridImageAdapter.notifyDataSetChanged();
			}

		}
	}
}
