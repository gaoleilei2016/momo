package gdswww.com.momo.activity.me;

import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gdswww.library.view.MyGridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import gdswww.com.momo.R;
import gdswww.com.momo.base.MyBaseActivity;


/**
 * 充值
 * Created by Administrator on 2017/5/10 0010.
 */
public class RechargeActivity extends MyBaseActivity {
    private MyGridView gv_recharge_moneys;
    private List<String> list;
    private SimpleAdapter adater1;
    private ArrayList<HashMap<String, Object>> mList1;
    private static final String listContent1[] = {"300", "580", "1880", "3000", "5800", "10800"};
    private static final String listContent2[] = {"￥：30", "￥：58", "￥：188", "￥：300", "￥：580", "￥：1080"};
    private int typeLastItem = 0;

    private void initGridViewOne() {
        gv_recharge_moneys = (MyGridView) findViewById(R.id.gv_recharge_moneys);
        mList1 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < listContent1.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("radioIcon", R.drawable.border);
            map.put("radioText", listContent1[i]);
            map.put("radioText1", listContent2[i]);
            mList1.add(map);
        }
        adater1 = new SimpleAdapter(getApplicationContext(), mList1,
                R.layout.activity_grid_item, new String[]{"radioIcon",
                "radioText", "radioText1"}, new int[]{R.id.item_RadioImg,
                R.id.item_RadioText, R.id.item_RadioText1});
        if (gv_recharge_moneys != null) {
            gv_recharge_moneys.setAdapter(adater1);
            gv_recharge_moneys.requestFocus();
            gv_recharge_moneys.setOnItemClickListener(new MyTypeOnItemClick());
        }
    }

    private void changeItemImg(SimpleAdapter sa, int selectedItem, boolean isOn) {
        HashMap<String, Object> map = (HashMap<String, Object>) sa
                .getItem(selectedItem);
        if (isOn) {
            map.put("radioIcon", R.drawable.selectedborder);
        } else {
            map.put("radioIcon", R.drawable.border);
        }
        sa.notifyDataSetChanged();
    }

    private class MyTypeOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            System.out.println("search_type");
            if (typeLastItem != position) {
                if (typeLastItem >= 0) {
                    changeItemImg(adater1, typeLastItem, false);
                }
            }
            typeLastItem = position;
            changeItemImg(adater1, position, true);
            HashMap<String, Object> map = (HashMap<String, Object>) adater1
                    .getItem(position);
            String typeStr = (String) map.get("radioText1");
            Toast.makeText(getApplicationContext(), typeStr, Toast.LENGTH_SHORT).show();
            System.out.println("Type string:" + typeStr);
        }
    }

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("充值");
        initGridViewOne();
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_recharge;
    }

}
