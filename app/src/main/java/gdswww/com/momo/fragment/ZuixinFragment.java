package gdswww.com.momo.fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.activity.home.SearchAnchorActivity;
import gdswww.com.momo.adapter.FragmentGuanzhuGridAdapter;
import gdswww.com.momo.adapter.SearchAnchorAdapter;
import gdswww.com.momo.base.MyBaseFragment;
import zrc.widget.ZrcListView;

/**
 * 首页最新
 * Created by Administrator on 2017/5/17 0017.
 */
@SuppressLint("ValidFragment")
public class ZuixinFragment extends MyBaseFragment {
    private ArrayList<HashMap<String, String>> list;
    PullToRefreshGridView zuixinGridView;
    FragmentGuanzhuGridAdapter fragmentGuanzhuGridAdapter;
    public ZuixinFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    public static ZuixinFragment getInstance() {
        ZuixinFragment instance = new ZuixinFragment();
        return instance;
    }
    public ZuixinFragment(){
        super(null);
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_zuixin;
    }

    @Override
    public void undateUI(Message msg) {

    }

    @Override
    public void initUI() {
        zuixinGridView= (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
        list=new ArrayList<>();
        fragmentGuanzhuGridAdapter=new FragmentGuanzhuGridAdapter(getContext(),list);
        zuixinGridView.setAdapter(fragmentGuanzhuGridAdapter);
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    protected void lazyLoad() {

    }
}
