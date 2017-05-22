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
import android.widget.TextView;

import com.gdswww.library.view.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.activity.home.SearchAnchorActivity;
import gdswww.com.momo.adapter.SearchAnchorAdapter;
import gdswww.com.momo.base.MyBaseFragment;

/**
 * 首页关注
 * Created by Administrator on 2017/5/17 0017.
 */
@SuppressLint("ValidFragment")
public class GuanzhuFragment extends MyBaseFragment {
    private PullToRefreshListView lv_search_anchor;
    private SearchAnchorAdapter searchAnchorAdapter;
    private ArrayList<HashMap<String, String>> list;
    public static GuanzhuFragment getInstance() {
        GuanzhuFragment instance = new GuanzhuFragment();
        return instance;
    }
    public GuanzhuFragment(){
        super(null);
    }


    public GuanzhuFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public int setContentView() {
        return R.layout.activity_search_anchor;
    }

    @Override
    public void undateUI(Message msg) {

    }

    @Override
    public void initUI() {
        lv_search_anchor = (PullToRefreshListView) findViewById(R.id.lv_search_anchor);
        lv_search_anchor.setMode(PullToRefreshBase.Mode.BOTH);
        hideView(findViewById(R.id.search_bar));
        list = new ArrayList<>();
        searchAnchorAdapter = new SearchAnchorAdapter(getContext(), list);
        lv_search_anchor.setAdapter(searchAnchorAdapter);
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    protected void lazyLoad() {

    }
}
