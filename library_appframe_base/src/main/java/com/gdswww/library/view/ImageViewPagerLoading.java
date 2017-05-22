package com.gdswww.library.view;

import java.util.ArrayList;
import java.util.List;

import com.gdswww.library.R;
import com.gdswww.library.view.viewpagerindicator.CirclePageIndicator;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * 图片滚动加载
 * 
 * @author Administrator
 * 
 */
public class ImageViewPagerLoading extends RelativeLayout {
	private ViewPager viewContainer;
	private LinearLayout llDot;
	private ArrayList<View> views;
	private CirclePageIndicator indicator;
	private Context context;
	int pagerIndex = 0;
	boolean isAutoScroll;
	int shudu, defaultShudu = 5000;
	int dir = 1;
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			pagerIndex++;
			if (pagerIndex == views.size()) {
				pagerIndex = 0;
			}
			viewContainer.setCurrentItem(pagerIndex, true);
			handler.sendEmptyMessageDelayed(0x100, shudu);
		}

	};

	public ImageViewPagerLoading(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ImageViewPagerLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.MyViewPager);
		isAutoScroll = array.getBoolean(R.styleable.MyViewPager_isAutoScroll,
				false);
		shudu = array.getInteger(R.styleable.MyViewPager_shudu, defaultShudu);
		dir = array.getInteger(R.styleable.MyViewPager_dir, dir);
		array.recycle();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_image_loading, this);
		viewContainer = (ViewPager) findViewById(R.id.picContainer);
		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		llDot = (LinearLayout) findViewById(R.id.ll_dot);
		if (dir == -1) {
			llDot.setGravity(Gravity.LEFT);
		} else if (dir == 0) {
			llDot.setGravity(Gravity.CENTER);
		} else if (dir == 1) {
			llDot.setGravity(Gravity.RIGHT);
		}

	}

	public ImageViewPagerLoading(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 通过资源id数组加载图片
	 * 
	 * @param ids
	 * @param callback
	 */
	public void setImagesByResID(int[] ids, ImageView.ScaleType imageScaletype) {
		if (ids != null && ids.length > 0) {
			views.clear();
			ImageView[] advs = new ImageView[ids.length];
			for (int i = 0; i < ids.length; i++) {
				advs[i] = new ImageView(context);
				advs[i].setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				advs[i].setScaleType(imageScaletype);

				advs[i].setImageResource(ids[i]);
				views.add(advs[i]);
			}
			viewContainer.setAdapter(new ViewPagerAdapter(context, views));
			indicator.setViewPager(viewContainer);
		}

	}

	/**
	 * 通过指定的URL数组加载图片
	 * 
	 * @param urls
	 *            数组
	 * @param callback
	 */
	public void setImagesByURLList(String[] urls,
			ImageView.ScaleType imageScaletype) {
		if (urls != null && urls.length > 0) {
			views.clear();
			ImageView[] advs = new ImageView[urls.length];
			for (int i = 0; i < urls.length; i++) {
				advs[i] = new ImageView(context);
				advs[i].setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				advs[i].setScaleType(imageScaletype);
				Picasso.with(context).load(urls[i]).into(advs[i]);
				views.add(advs[i]);
			}
			viewContainer.setAdapter(new ViewPagerAdapter(context, views));
			indicator.setViewPager(viewContainer);
		}
	}

	/**
	 * 通过指定的View集合加载图片
	 * 
	 * @param view
	 *            View图片
	 * 
	 * @param callback
	 */
	public void setImagesByViews(ArrayList<View> view,
			ImageView.ScaleType imageScaletype) {
		if (view != null && view.size() > 0) {
			viewContainer.setAdapter(new ViewPagerAdapter(context, view));
			indicator.setViewPager(viewContainer);
		}
	}

	class ViewPagerAdapter extends PagerAdapter {

		private List<View> views;
		Context context;
		int mCount;

		public ViewPagerAdapter(Context context, List<View> views) {
			this.views = views;
			this.context = context;
			mCount = views.size();
		}

		@Override
		public void destroyItem(View collection, int position, Object arg2) {
			if (position >= views.size() - 1) {
				int newPosition = position % views.size();
				position = newPosition;
				((ViewPager) collection).removeView(views.get(position));
			}
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			return mCount;
		}

		@Override
		public Object instantiateItem(View collection, int position) {

			if (position >= views.size()) {
				int newPosition = position % views.size();

				position = newPosition;
				mCount++;
			}

			try {
				((ViewPager) collection).addView(views.get(position), 0);
			} catch (Exception e) {
			}
			return views.get(position);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == (object);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}
	}
}
