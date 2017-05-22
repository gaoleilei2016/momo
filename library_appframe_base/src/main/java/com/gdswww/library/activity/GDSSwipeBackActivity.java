package com.gdswww.library.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gdswww.library.widget.swipebacklayout.SwipeBackActivityBase;
import com.gdswww.library.widget.swipebacklayout.SwipeBackActivityHelper;
import com.gdswww.library.widget.swipebacklayout.lib.SwipeBackLayout;
import com.gdswww.library.widget.swipebacklayout.lib.Utils;

/**
 * 滑动关闭Activity
 * @ClassName: SwipeBackActivity
 * @Description: TODO
 * @date 2015年7月31日
 */
public abstract class GDSSwipeBackActivity extends GDSBaseActivity implements
		SwipeBackActivityBase {
	public SwipeBackActivityHelper mHelper;
	public enum SwipeDir {
		LEFT, RIGHT, BOTTOM, LEFT_RIGHT_BOTTOM
	};

	@Override
	public void onCreateBefore(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateBefore(savedInstanceState);
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivityCreate();
		setSwipeDirection(SwipeDir.LEFT);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v == null && mHelper != null)
			return mHelper.findViewById(id);
		return v;
	}

	@Override
	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	@Override
	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	@Override
	public void scrollToFinishActivity() {
		Utils.convertActivityToTranslucent(this);
		getSwipeBackLayout().scrollToFinishActivity();
	}

	/**
	 * 隐藏键盘
	 */
	public void hideKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
	}
	/**
	  * 设置方向
	  * @Title: setSwipeDirection 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param dir 参数描述 
	 */
	public void setSwipeDirection(SwipeDir dir) {
		int edgeFlag = 0;
		switch (dir) {
		case LEFT:
			edgeFlag = SwipeBackLayout.EDGE_LEFT;
			break;
		case RIGHT:
			edgeFlag = SwipeBackLayout.EDGE_RIGHT;
			break;
		case BOTTOM:
			edgeFlag = SwipeBackLayout.EDGE_BOTTOM;
			break;
		case LEFT_RIGHT_BOTTOM:
			edgeFlag = SwipeBackLayout.EDGE_ALL;
			break;
		}
		getSwipeBackLayout().setEdgeTrackingEnabled(edgeFlag);
	}

}
