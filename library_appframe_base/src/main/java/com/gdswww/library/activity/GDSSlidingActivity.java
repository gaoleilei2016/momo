package com.gdswww.library.activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.gdswww.library.R;
import com.gdswww.library.widget.slidingmenu.SlidingActivityBase;
import com.gdswww.library.widget.slidingmenu.SlidingActivityHelper;
import com.gdswww.library.widget.slidingmenu.lib.SlidingMenu;

/**
 * 具有侧滑的功能的Activity
 * @ClassName: GDSSlidingActivity
 * @Description: TODO
 * @author shihuanzhang 2335946896@qq_com
 * @date 2015年8月25日
 */
public abstract class GDSSlidingActivity extends GDSBaseActivity implements
		SlidingActivityBase {

	private SlidingActivityHelper mHelper;
	protected SlidingMenu slidingMenu;

	@Override
	public void onCreateBefore(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateBefore(savedInstanceState);
		mHelper = new SlidingActivityHelper(this);
		mHelper.onCreate(savedInstanceState);
		slidingMenu = getSlidingMenu();
		
	}
	
	
	// 忽略SlidingMenu该视图的左右滑动处理
	protected void addIgnoredView(View v) {
		getSlidingMenu().addIgnoredView(v);
	}
	
	protected void setSlidingMenu(int dir, int slidingOffset,
			int contentViewId, boolean isShowShadow) {
		setSlidingMenu(dir,slidingOffset,
				inflater.inflate(contentViewId, null),isShowShadow);
	}

	/**
	 * setSlidingMenu 设置侧滑菜单
	 * @param dir 方向SlidingMenu.LEFT or SlidingMenu.RIGHT
	 * @param slidingOffset 侧滑偏移量
	 * @param contentView 内容视图
	 */
	protected void setSlidingMenu(int dir, int slidingOffset,
			View contentView, boolean isShowShadow) {
		slidingMenu.setMode(dir);
		if (isShowShadow) {
			switch (dir) {
			case SlidingMenu.LEFT:
				slidingMenu.setShadowDrawable(R.drawable.shadow_left);
				break;
			case SlidingMenu.RIGHT:
				slidingMenu.setShadowDrawable(R.drawable.shadow_right);
				break;
			}
			slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		}
		slidingMenu.setMode(dir);;
		slidingMenu.setBehindOffset(slidingOffset);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setBehindContentView(contentView);
		slidingMenu.setFadeEnabled(true);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, 0);
	}

	

	/**
	 * 设置左右侧滑SlidingMenu菜单
	 * @param leftContentView 左边的视图
	 * @param rightContentView 右边的的视图
	 * @param isShowShadow 是否显示侧边阴影
	 */
	protected void setTwoSlidingMenu(int slidingOffset, int leftContentViewId,
			int rightContentViewId,boolean isShowShadow) {
		setTwoSlidingMenu(slidingOffset,inflater.inflate(leftContentViewId, null)
				,inflater.inflate(rightContentViewId, null),isShowShadow);
	}

	/**
	 * 设置左右侧滑SlidingMenu菜单
	 * @param slidingOffset 设置划出时主页面显示的剩余宽度
	 * @param leftContentView 左边的视图
	 * @param rightContentView 右边的的视图
	 * @param isShowShadow 是否显示侧边阴影
	 */
	protected void setTwoSlidingMenu(int slidingOffset, View leftContentView,
			View rightContentView,boolean isShowShadow) {
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		if (isShowShadow) {
			slidingMenu.setShadowDrawable(R.drawable.shadow_left);
			slidingMenu.setSecondaryShadowDrawable(R.drawable.shadow_right);
			slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		}
		slidingMenu.setBehindOffset(slidingOffset);//设置划出时主页面显示的剩余宽度
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 触摸范围
		setBehindContentView(leftContentView);
		slidingMenu.setSecondaryMenu(rightContentView);
		slidingMenu.setFadeEnabled(true);// 滑动时渐变
		slidingMenu.setFadeDegree(0.35f);// 滑动时的渐变程度
		slidingMenu.attachToActivity(this, 0);
	}
	
	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mHelper.findViewById(id);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mHelper.onSaveInstanceState(outState);
	}

	@Override
	public void setContentView(int id) {
		setContentView(getLayoutInflater().inflate(id, null));
	}

	@Override
	public void setContentView(View v) {
		setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	@Override
	public void setContentView(View v, LayoutParams params) {
		super.setContentView(v, params);
		mHelper.registerAboveContentView(v, params);
	}

	public void setBehindContentView(int id) {
		setBehindContentView(getLayoutInflater().inflate(id, null));
	}

	public void setBehindContentView(View v) {
		setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	public void setBehindContentView(View v, LayoutParams params) {
		mHelper.setBehindContentView(v, params);
	}

	public SlidingMenu getSlidingMenu() {
		return mHelper.getSlidingMenu();
	}

	public void toggle() {
		if(slidingMenu.isMenuShowing()){
			showContent();
		}else{
			showMenu();
		}
	}

	public void showContent() {
		slidingMenu.showContent(true);
	}

	public void showMenu() {
		// mHelper.showMenu();
		slidingMenu.showMenu(true);
	}

	public void showSecondaryMenu() {
		slidingMenu.showSecondaryMenu();
	}

	public void setSlidingActionBarEnabled(boolean b) {
		mHelper.setSlidingActionBarEnabled(b);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean b = mHelper.onKeyUp(keyCode, event);
		if (b)
			return b;
		return super.onKeyUp(keyCode, event);
	}

}
