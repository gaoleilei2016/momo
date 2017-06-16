package adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import momo.com.gdswww.myapplication.R;

/**
 * ViewPager适配器
 */
public class LauncherPagerAdapter extends PagerAdapter {

    private List<View> views;
    //每页显示的图片
    private int[] images = new int[]{R.mipmap.home_background3, R.mipmap.home_background3, R.mipmap.home_background3, R.mipmap.home_background3};

    public LauncherPagerAdapter(Context context) {
        views = new ArrayList<View>();
        //初始化每页显示的View
        for (int i = 0; i < images.length; i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.activity_luancher_pager_item, null);
            ImageView imageview = (ImageView) item.findViewById(R.id.first);
            imageview.setImageResource(images[i]);
            views.add(item);
        }
    }

    public List<View> getViews() {
        return views;
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);
        return views.get(position);
    }
}
