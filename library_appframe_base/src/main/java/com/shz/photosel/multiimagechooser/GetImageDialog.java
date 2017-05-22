package com.shz.photosel.multiimagechooser;

import com.gdswww.library.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class GetImageDialog extends Dialog {
	private Button btnTakePhoto, btnGetPhoto, btnCancel;
	private GetImageCallback callback;

	public GetImageDialog(Context context,GetImageCallback callback) {
		this(context, R.layout.alert_pic_sel_dialog,R.style.my_dialog_style,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.callback = callback;
	}

	private GetImageDialog(final Context context, int layout, int style,
			int width, int height) {
		super(context, style);
		setContentView(layout);
		setCanceledOnTouchOutside(true);
		// 设置属性
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = width;
		lp.height = height;
		getWindow().setAttributes(lp);
		btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
		btnGetPhoto = (Button) findViewById(R.id.btn_pick_photo);
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		btnTakePhoto
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(callback != null){
							callback.onTakePhoto();
						}
						dismiss();
					}
				});
		btnGetPhoto
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(callback != null){
							callback.onGetPhoto();
						}
						dismiss();
					}
				});
		btnCancel
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dismiss();
					}
				});
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		getWindow().setGravity(Gravity.BOTTOM);
	}

	public interface GetImageCallback {
		abstract void onTakePhoto();

		abstract void onGetPhoto();
	}

}
