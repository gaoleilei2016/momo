package gdswww.com.momo.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mlist;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mlist=list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);
    }
}
