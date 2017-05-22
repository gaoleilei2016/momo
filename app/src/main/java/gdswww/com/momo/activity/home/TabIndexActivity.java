package gdswww.com.momo.activity.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.gdswww.library.widget.swipebacklayout.lib.SwipeBackLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gdswww.com.momo.R;
import gdswww.com.momo.activity.me.TopActivity;
import gdswww.com.momo.adapter.ExamplePagerAdapter;
import gdswww.com.momo.adapter.MyFragmentPagerAdapter;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.fragment.GuanzhuFragment;
import gdswww.com.momo.fragment.RemenFragment;
import gdswww.com.momo.fragment.ZuixinFragment;

public class TabIndexActivity extends MyBaseActivity {
    private static final String[] CHANNELS = new String[]{"关注", "热门", "最新"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void updateUI(Message msg) {
    }


    @Override
    public void initUI() {
        SwipeBackLayout mS = new SwipeBackLayout(this);
        mS.setEnableGesture(false);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initMagicIndicator4();
        Fragment fragment1 = GuanzhuFragment.getInstance();
        Fragment fragment2 = RemenFragment.getInstance();
        Fragment fragment3 = ZuixinFragment.getInstance();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        clickView(R.id.leftSearchIcon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(SearchAnchorActivity.class);
            }
        });
        clickView(R.id.topIcon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(TopActivity.class);
            }
        });
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_badge_tab_example_layout;
    }

    private void initMagicIndicator4() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator4);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.WHITE);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setColors(Color.WHITE);
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return UIUtil.dip2px(TabIndexActivity.this, 15);
            }
        });
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    public void onCreateBefore(Bundle savedInstanceState) {
        super.onCreateBefore(savedInstanceState);
        getSwipeBackLayout().setEdgeTrackingEnabled(100);
    }
}
