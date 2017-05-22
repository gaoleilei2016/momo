package gdswww.com.momo.activity.home;
import android.os.Bundle;
import android.os.Message;
import gdswww.com.momo.R;
import gdswww.com.momo.base.MyBaseActivity;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class RankingActivity extends MyBaseActivity {
    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {

    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.openpopzhibo;
    }

    @Override
    public void onCreateBefore(Bundle savedInstanceState) {
        super.onCreateBefore(savedInstanceState);
        getSwipeBackLayout().setEdgeTrackingEnabled(100);
    }
}
