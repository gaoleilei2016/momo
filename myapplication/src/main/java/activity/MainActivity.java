package activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Interface.OnDrawerPageChangeListener;
import adapter.LauncherPagerAdapter;
import adapter.MyFragmentPagerAdapter;
import fragment.FragmentContents;
import momo.com.gdswww.myapplication.R;

public class MainActivity extends BaseAcy implements OnDrawerPageChangeListener {
    private DrawerLayout mDrawerLayout;
    private RelativeLayout rlHome, rlGift, rlShare;
    private int currentSelectItem = R.id.rl_home;//默认首页
    private ViewPager viewpagerLauncher, content_frame;
    private ImageView[] tips;
    private LinearLayout top;
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        findViewById(R.id.imageView).setOnClickListener(clickListener);
        initRightMenu();//初始化左边菜单
        initViewpager();
        DrawerViewPger();
        ImageView zhuqiu = (ImageView) findViewById(R.id.zhuqiu);
        zhuqiu.setAnimation(AnimationUtils.loadAnimation(this, R.anim.zuqiu));
        initFragViewPager();
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void initFragViewPager() {
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 0));
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 1));
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 2));
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 3));
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 4));
        fragmentList.add(new FragmentContents(getSupportFragmentManager(), 5));
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        content_frame = (ViewPager) findViewById(R.id.content_frame);
        content_frame.setAdapter(mFragmentPagerAdapter);
    }

    private void DrawerViewPger() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        // 点击除开侧边栏的区域会收起侧边栏。
        mDrawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }

//    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//        }
//
//        @Override
//        public void onPageSelected(int index) {
//            setImageBackground(index);// 改变点点点的切换效果
//            TextView tvStartHeadlines = (TextView) adapter.getViews().get(index).findViewById(R.id.tv_start_headlines);
//            if (index == tips.length - 1) {// 最后一个
//                tvStartHeadlines.setVisibility(View.VISIBLE);
//            } else {
//                tvStartHeadlines.setVisibility(View.INVISIBLE);
//            }
//        }
//    };

    /**
     * 改变点点点的切换效果
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }

    private void initViewpager() {
        LauncherPagerAdapter mlauncher=new LauncherPagerAdapter(this);
        viewpagerLauncher = (ViewPager) findViewById(R.id.viewpager_launcher);
        viewpagerLauncher.setOffscreenPageLimit(2);
        viewpagerLauncher.setCurrentItem(0);
//        viewpagerLauncher.setOnPageChangeListener(mlauncher);
        viewpagerLauncher.setAdapter(mlauncher);
//        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);// 初始化底部显示控件
//        tips = new ImageView[4];
//        for (int i = 0; i < tips.length; i++) {
//            ImageView imageView = new ImageView(this);
//            if (i == 0) {
//                imageView.setBackgroundResource(R.mipmap.page_indicator_focused);
//            } else {
//                imageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
//            }
//            tips[i] = imageView;
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            layoutParams.leftMargin = 10;// 设置点点点view的左边距
//            layoutParams.rightMargin = 10;// 设置点点点view的右边距
//            group.addView(imageView, layoutParams);
//        }
        final OnDrawerPageChangeListener drawerPageChangeListener = (OnDrawerPageChangeListener) this;
        viewpagerLauncher.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    drawerPageChangeListener.onPageSelected(true);
                } else {
                    drawerPageChangeListener.onPageSelected(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initRightMenu() {
        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlGift = (RelativeLayout) findViewById(R.id.rl_gift);
        top = (LinearLayout) findViewById(R.id.top);
        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlGift = (RelativeLayout) findViewById(R.id.rl_gift);
        rlShare = (RelativeLayout) findViewById(R.id.rl_share);

        rlHome.setOnClickListener(onLeftMenuClickListener);
        rlGift.setOnClickListener(onLeftMenuClickListener);
        rlShare.setOnClickListener(onLeftMenuClickListener);
        top.setOnClickListener(onLeftMenuClickListener);
//        rlGift.setOnClickListener(onLeftMenuClickListener);
//        rlShare.setOnClickListener(onLeftMenuClickListener);
//        rlHome.setSelected(true);
    }

    private OnClickListener onLeftMenuClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            if (currentSelectItem != v.getId()) {//防止重复点击
                currentSelectItem = v.getId();
                noItemSelect();
                switch (v.getId()) {
                    case R.id.rl_home:
                        rlHome.setSelected(true);
                        break;
                    case R.id.rl_gift:
                        rlGift.setSelected(true);
                        break;
                    case R.id.rl_share:
                        rlShare.setSelected(true);
                        break;
                }
//                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        }
    };

    private void noItemSelect() {
        rlHome.setSelected(false);
        rlGift.setSelected(false);
        rlShare.setSelected(false);
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView:// 打开左边抽屉
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                    break;
            }
        }
    };


    @Override
    public void onPageSelected(boolean isLast) {
        if (isLast) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else if (mDrawerLayout.getDrawerLockMode(GravityCompat.START) == DrawerLayout.LOCK_MODE_UNLOCKED) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
    }
}
