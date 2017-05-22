package com.gdswww.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gdswww.library.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerLoading extends RelativeLayout {
    private ViewPager viewContainer;
    private LinearLayout llDot;
    private ArrayList<View> views;
    private ImageView[] dots;
    private Context context;
    int pagerIndex = 0;
    int dot_normal, dot_selected;
    boolean isAutoScroll;
    int shudu, dolIndex, defaultShudu = 5000;
    int dir = 1;
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

    public ViewPagerLoading(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public ViewPagerLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // TODO Auto-generated constructor stub
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.MyViewPager);
        dot_normal = array.getResourceId(R.styleable.MyViewPager_dot_normal,
                R.drawable.dot_normal);
        dot_selected = array.getResourceId(
                R.styleable.MyViewPager_dot_selected, R.drawable.dot_selected);
        isAutoScroll = array.getBoolean(R.styleable.MyViewPager_isAutoScroll,
                false);
        shudu = array.getInteger(R.styleable.MyViewPager_shudu, defaultShudu);
        dir = array.getInteger(R.styleable.MyViewPager_dir, dir);
        array.recycle();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pager_adapter, this);
        viewContainer = (ViewPager) findViewById(R.id.picContainer);
        llDot = (LinearLayout) findViewById(R.id.ll_dot);
        if (dir == -1) {
            llDot.setGravity(Gravity.CENTER);
        } else if (dir == 0) {
            llDot.setGravity(Gravity.CENTER);
        } else if (dir == 1) {
            llDot.setGravity(Gravity.CENTER);
        } else if (dir == 2) {
            llDot.setVisibility(View.GONE);
        }

    }

    public void sudu(int sudu) {
        shudu = sudu;
    }

    public ViewPagerLoading(Context context) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    public interface Callback {
        public void onPageSelected(int arg0);

        public void onClick(int arg0);

        public void onPageScrolled(int arg0, float arg1, int arg2);

        public void onPageScrollStateChanged(int status);
    }

    public void setViews(ArrayList<View> views, final Callback callback) {
        this.views = views;
        if (this.views != null && this.views.size() > 0) {
            viewContainer.setAdapter(new ViewPagerAdapter(context, views));
            dots = new ImageView[views.size()];
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new ImageView(context);
                dots[i].setLayoutParams(new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                dots[i].setScaleType(ScaleType.FIT_CENTER);
                dots[i].setPadding(0, 0, 5, 0);
                dots[i].setImageResource(dot_normal);
                llDot.addView(dots[i]);
            }

            dots[dolIndex].setImageResource(dot_selected);
            viewContainer.setOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageSelected(int arg0) {
                    pagerIndex = arg0;
                    dots[dolIndex].setImageResource(dot_normal);
                    dolIndex = arg0;
                    dots[dolIndex].setImageResource(dot_selected);
                    if (callback != null) {
                        callback.onPageSelected(arg0);
                    }
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                    if (callback != null) {
                        callback.onPageScrolled(arg0, arg1, arg2);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int status) {
                    callback.onPageScrollStateChanged(status);
                }
            });

            if (isAutoScroll) {
                handler.sendEmptyMessageDelayed(0x100, shudu);
            }
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
