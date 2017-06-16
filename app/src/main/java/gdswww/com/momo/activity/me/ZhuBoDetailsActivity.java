package gdswww.com.momo.activity.me;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import gdswww.com.momo.R;
import gdswww.com.momo.base.MyBaseActivity;

/**
 * 主播详情
 * Created by Administrator on 2017/5/22 0022.
 */
public class ZhuBoDetailsActivity extends MyBaseActivity {
    @Override
    public void updateUI(Message msg) {

    }

    ImageView zhubo_img;

    @Override
    public void initUI() {
        toastShort("主播ID:" + getIntent().getIntExtra("zhubo_id", 0));
        zhubo_img = viewId(R.id.zhubo_img);
        loadImg((ImageView) viewId(R.id.zhubo_img), R.mipmap.zhubo);
    }


    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.zhuboinfo;
    }

    public void closeActivity(View view) {
        finish();
    }
}
