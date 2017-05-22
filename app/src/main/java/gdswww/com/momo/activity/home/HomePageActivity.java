package gdswww.com.momo.activity.home;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.gdswww.library.view.MyListView;
import com.gdswww.library.view.ViewPagerLoading;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.adapter.MessageAdapter;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.base.MyUrl;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class HomePageActivity extends MyBaseActivity {
    private DebugInterface di;
    private ViewPagerLoading vpl_homepage;
    private ArrayList<View> views = new ArrayList<>();
    private PullToRefreshScrollView psv;
    private MyListView mlv_homepage_list;
    ArrayList<HashMap<String, String>> list=new ArrayList<>();
    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        di = new DebugInterface(aq, this);
        vpl_homepage = viewId(R.id.vpl_homepage);
        for(int i=0;i<10;i++){
            HashMap<String, String> hashMap=new HashMap<>();
            hashMap.put(i+"","Test"+i);
            list.add(hashMap);
        }
        mlv_homepage_list.setAdapter(new MessageAdapter(this,list));
        advertising();
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
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

                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {

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
}