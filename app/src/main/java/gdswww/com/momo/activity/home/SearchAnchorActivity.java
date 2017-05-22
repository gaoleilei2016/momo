package gdswww.com.momo.activity.home;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.adapter.SearchAnchorAdapter;
import gdswww.com.momo.base.MyBaseActivity;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class SearchAnchorActivity extends MyBaseActivity implements TextWatcher {
    private PullToRefreshListView lv_search_anchor;
    private SearchAnchorAdapter searchAnchorAdapter;
    private ArrayList<HashMap<String, String>> list;
    private TextView no_data, tv_remeng;
    EditText search_zhubo;

    @Override
    public void updateUI(Message msg) {

    }

    public void closeActivity(View view) {
        finish();
    }

    @Override
    public void initUI() {
        no_data = viewId(R.id.no_data);
        tv_remeng = viewId(R.id.tv_remeng);
        tv_remeng.setVisibility(View.GONE);
        lv_search_anchor = viewId(R.id.lv_search_anchor);
        lv_search_anchor.setMode(PullToRefreshBase.Mode.BOTH);
        search_zhubo = viewId(R.id.search_zhubo);
        lv_search_anchor.setVisibility(View.GONE);
        list = new ArrayList<>();
        searchAnchorAdapter = new SearchAnchorAdapter(SearchAnchorActivity.this, list);
        lv_search_anchor.setAdapter(searchAnchorAdapter);

//        // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
//        SimpleHeader header = new SimpleHeader(this);
//        header.setTextColor(0xffE23B69);
//        header.setCircleColor(0xffE23B69);
//        lv_search_anchor.setHeadable(header);
//
//        // 设置加载更多的样式（可选）
//        SimpleFooter footer = new SimpleFooter(this);
//        footer.setCircleColor(0xffE23B69);
//        lv_search_anchor.setFootable(footer);
    }

    @Override
    public void regUIEvent() {
        search_zhubo.addTextChangedListener(this);
//        lv_search_anchor.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
//            @Override
//            public void onStart() {
//                //上啦事件
//                refresh();
//                searchAnchorAdapter.notifyDataSetChanged();
//            }
//        });
//
//        // 加载更多事件回调（可选）
//        lv_search_anchor.setOnLoadMoreStartListener(new ZrcListView.OnStartListener() {
//            @Override
//            public void onStart() {
//                loadMore();
//                searchAnchorAdapter.notifyDataSetChanged();
//            }
//        });
    }

//    private void refresh(){
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lv_search_anchor.setRefreshSuccess("加载成功"); // 通知加载成功
//                lv_search_anchor.startLoadMore(); // 开启LoadingMore功能
//            }
//        }, 2 * 1000);
//    }
//
//    private void loadMore(){
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lv_search_anchor.setLoadMoreSuccess();
//                lv_search_anchor.stopLoadMore();
//            }
//        }, 2 * 1000);
//    }

    @Override
    public int getLayout() {
        return R.layout.serachzhubo;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        toastShort(charSequence.toString());

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        toastShort(charSequence.toString());
        no_data.setVisibility(View.GONE);
        tv_remeng.setVisibility(View.VISIBLE);
        lv_search_anchor.setVisibility(View.VISIBLE);
        //TODO


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
