package gdswww.com.momo.activity.me;

import android.os.Message;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.adapter.CommonAdapter;
import gdswww.com.momo.adapter.ViewHolder;
import gdswww.com.momo.base.MyBaseActivity;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class GuanZhuActivity extends MyBaseActivity implements PullToRefreshBase.OnRefreshListener2 {
    private PullToRefreshListView list_view;
    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();


    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("关注");
        list_view = viewId(R.id.list_view);
        for (int i = 0; i < 9; i++) {
            list.add(new HashMap<String, Object>());
        }
        list_view.setMode(PullToRefreshBase.Mode.BOTH);
//        list_view.setOnRefreshListener(GuanZhuActivity.this);
        list_view.setAdapter(getAdapter());
    }

    private CommonAdapter getAdapter() {
        CommonAdapter commonAdapter = new CommonAdapter(this, list, R.layout.item_follow) {


            @Override
            public void convert(ViewHolder helper, Object item) {

            }
        };
        return commonAdapter;
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Log.d("下拉", "刷新。。。");
        list_view.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Log.d("上拉", "加载。。。");
        list_view.onRefreshComplete();
    }
}
