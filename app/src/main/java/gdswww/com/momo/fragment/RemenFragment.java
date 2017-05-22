package gdswww.com.momo.fragment;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.gdswww.library.view.MyListView;
import com.gdswww.library.view.ViewPagerLoading;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.adapter.MessageAdapter;
import gdswww.com.momo.adapter.RemenAdapter;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseFragment;
import gdswww.com.momo.base.MyUrl;
/**
 * 首页热门
 * Created by Administrator on 2017/5/17 0017.
 */
@SuppressLint("ValidFragment")
public class RemenFragment extends MyBaseFragment implements AdapterView.OnItemClickListener {
    private DebugInterface di;
    private ViewPagerLoading vpl_homepage;
    private ArrayList<View> views = new ArrayList<>();
    private PullToRefreshListView psv;
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    RemenAdapter mRemenAdapter;
    public static RemenFragment getInstance() {
        RemenFragment instance = new RemenFragment();
        return instance;
    }
    public RemenFragment(){
        super(null);
    }
    public RemenFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public int setContentView() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void undateUI(Message msg) {

    }

    @Override
    public void initUI() {
        di = new DebugInterface(aq, getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.headerview, null);
        vpl_homepage = (ViewPagerLoading) view.findViewById(R.id.vpl_homepage);
        psv = (PullToRefreshListView) findViewById(R.id.lv_search_anchor);
        psv.setMode(PullToRefreshBase.Mode.BOTH);
        psv.getRefreshableView().addHeaderView(view);
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(i + "", "Test" + i);
            list.add(hashMap);
        }
        mRemenAdapter = new RemenAdapter(getContext(), list);
        psv.setAdapter(mRemenAdapter);
        advertising();
        psv.setOnItemClickListener(this);
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    protected void lazyLoad() {
    }

    /**
     * 轮播图接口
     */
    private void advertising() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("type", "1");
        di.getBackstageData(params, MyUrl.advertising(), new CallBackJSON() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                JSONArray data = jsonObject.optJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject json = data.optJSONObject(i);
                    ImageView iv = new ImageView(context);
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.with(context).load(json.optString("img")).into(iv);
                    views.add(iv);
                }
                vpl_homepage.setViews(views, new ViewPagerLoading.Callback() {
                    @Override
                    public void onPageSelected(int arg0) {

                    }

                    @Override
                    public void onClick(int arg0) {
                        showToatWithShort("onClick" + arg0);
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        showToatWithShort("arg0" + arg0);
                    }

                    @Override
                    public void onPageScrollStateChanged(int status) {

                    }
                });

            }

            @Override
            public void onFailure(JSONObject jsonObject) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showToatWithShort("pos" + position + "id" + id);
    }
}
