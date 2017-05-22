package gdswww.com.momo.fragment;
import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v4.app.FragmentManager;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.adapter.TopAdapter;
import gdswww.com.momo.base.MyBaseFragment;
import gdswww.com.momo.modle.Index;
/**
 * Created by Administrator on 2017/5/19 0019.
 */
@SuppressLint("ValidFragment")
public class TopFragment extends MyBaseFragment {
    private PullToRefreshListView lv_search_anchor;
    private TopAdapter topAdapter;
    private ArrayList<Index> list;

    public TopFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static TopFragment getInstance() {
        TopFragment instance = new TopFragment();
        return instance;
    }

    public TopFragment() {
        super(null);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int setContentView() {
        return R.layout.activity_i_have_seen;
    }

    @Override
    public void undateUI(Message msg) {

    }

    @Override
    public void initUI() {
        lv_search_anchor = (PullToRefreshListView) findViewById(R.id.lv_search_anchor);
        lv_search_anchor.setMode(PullToRefreshBase.Mode.BOTH);
        hideView(findViewById(R.id.search_bar));
        list = new ArrayList<Index>();
        for (int i = 0; i <10; i++) {
            Index mIndex = new Index(i);
            list.add(mIndex);
        }
        topAdapter = new TopAdapter(getContext(), list);
        lv_search_anchor.setAdapter(topAdapter);
    }

    @Override
    public void regUIEvent() {

    }
}
