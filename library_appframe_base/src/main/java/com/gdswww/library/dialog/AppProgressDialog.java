package com.gdswww.library.dialog;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.gdswww.library.R;


public class AppProgressDialog extends ProgressDialog{
	private TextView define_progress_msg;
	private String message="loading";
	public AppProgressDialog(Context context) {
		super(context);
	}
	public void setMessage(String message){
		this.message = message;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_progress_dialog);
		define_progress_msg = (TextView) findViewById(R.id.define_progress_msg);
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		define_progress_msg.setText(this.message);
	}
	
}
