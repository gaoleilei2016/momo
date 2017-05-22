package gdswww.com.momo.activity.me;

import android.os.Message;

import com.gdswww.library.view.MyGridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import gdswww.com.momo.R;
import gdswww.com.momo.adapter.RechargeAdapter;
import gdswww.com.momo.base.MyBaseActivity;


/**
 * 充值
 * Created by Administrator on 2017/5/10 0010.
 */
public class RechargeActivity extends MyBaseActivity {
    private MyGridView gv_recharge_moneys;
    private RechargeAdapter rechargeAdapter;
    private List<String> list;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("充值");
        list = Arrays.asList("300", "580", "1880", "3000", "5800", "10800");
        gv_recharge_moneys = viewId(R.id.gv_recharge_moneys);
        rechargeAdapter = new RechargeAdapter(RechargeActivity.this, list);
        gv_recharge_moneys.setAdapter(rechargeAdapter);
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_recharge;
    }

}
