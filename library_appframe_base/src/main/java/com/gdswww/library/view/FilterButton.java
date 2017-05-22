package com.gdswww.library.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class FilterButton extends Button {
	public FilterButton(Context context) {
		super(context);
	}

	public FilterButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FilterButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			// 在按下事件中设置滤镜
			setFilter();
			break;
		case MotionEvent.ACTION_UP:
			// 由于捕获了Touch事件，需要手动触发Click事件
			performClick();
		case MotionEvent.ACTION_CANCEL:
			// 在CANCEL和UP事件中清除滤镜
			removeFilter();
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 设置滤镜
	 */
	private void setFilter() {
		//获取背景图
		Drawable drawable  = getBackground();
		if (drawable != null) {
			// 设置滤镜
			drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
		}
	}

	/**
	 * 清除滤镜
	 */
	private void removeFilter() {
		// 获取背景图片
		Drawable drawable = getBackground();
		if (drawable != null) {
			//清除滤镜
			drawable.clearColorFilter();
		}
	}

}
