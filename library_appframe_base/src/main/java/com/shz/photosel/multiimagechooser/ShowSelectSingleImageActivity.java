package com.shz.photosel.multiimagechooser;
import java.io.File;

import com.squareup.picasso.Picasso;
import com.gdswww.library.R;
import com.photoview.library.PhotoView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

public class ShowSelectSingleImageActivity extends Activity {
	private PhotoView image;

	public void back(View v) {
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_single_image_with_delete);
		image = (PhotoView) findViewById(R.id.image);
		String path = getIntent().getStringExtra("path");
		if(path.contains("file:///android_asset")){
			Picasso.with(getApplicationContext()).load(path).resize(480, 960)
			.centerInside().into(image);
		}else{
			Picasso.with(getApplicationContext()).load(new File(path)).resize(480, 960)
			.centerInside().into(image);
		}
		
		
	}
	public void delete(View v){
		new AlertDialog.Builder(this).setTitle("提示").setMessage("是否移除该图片？")
		.setPositiveButton("是", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK, getIntent().putExtra("del", true).putExtra("path",
						getIntent().getStringExtra("path"))
						);
				finish();
			}
		}).setNegativeButton("取消", null).show();
	}

}
