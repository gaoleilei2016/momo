package gdswww.com.momo.activity.me;
import android.os.Bundle;
import android.os.Message;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;
import gdswww.com.momo.R;
import gdswww.com.momo.adapter.ListAdapter;
import gdswww.com.momo.base.MyBaseActivity;

/**
 * 我看过的
 * listview滑动删除
 */
public class ListViewDeleteItemActivity extends MyBaseActivity {
    private ListAdapter adapter;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("我看过的");
        setupList();
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null) {
            adapter.saveStates(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (adapter != null) {
            adapter.restoreStates(savedInstanceState);
        }
    }

    private void setupList() {
        PullToRefreshListView listView = viewId(R.id.list_view);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.getLoadingLayoutProxy().setLoadingDrawable(null);
        listView.getLoadingLayoutProxy().setRefreshingLabel("");
        listView.getLoadingLayoutProxy().setPullLabel("");
        listView.getLoadingLayoutProxy().setLastUpdatedLabel("");
        listView.getLoadingLayoutProxy().setReleaseLabel("");
        adapter = new ListAdapter(ListViewDeleteItemActivity.this, createList(20));
        listView.setAdapter(adapter);

    }

    private List<String> createList(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }
        return list;
    }
}
