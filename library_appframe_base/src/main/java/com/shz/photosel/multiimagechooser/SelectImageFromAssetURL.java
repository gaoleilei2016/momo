package com.shz.photosel.multiimagechooser;
import com.squareup.picasso.Picasso;
import com.gdswww.library.R;
import com.photoview.library.PhotoView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
public class SelectImageFromAssetURL extends Activity {
	private PhotoView image;

	public void back(View v) {
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_asset_image);
		image = (PhotoView) findViewById(R.id.image);
		Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("path")).resize(480, 960)
		.centerInside().into(image);
	}
	public void ok(View v){
		setResult(RESULT_OK,getIntent());
		finish();
	}

}
