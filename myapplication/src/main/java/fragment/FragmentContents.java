package fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import momo.com.gdswww.myapplication.R;
/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class FragmentContents extends MyBaseFragment {
    private int index;
    private AnimationDrawable frameAnim;
    private AnimationDrawable frameAnim1;
    private AnimationDrawable frameAnim2;

    public FragmentContents(FragmentManager fragmentManager, int index) {
        super(fragmentManager);
        this.index = index;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }

    @Override
    public int setContentView() {
        if (index == 0)
            return R.layout.activity_luancher_pager_item1;
        if (index == 1)
            return R.layout.activity_luancher_pager_item2;
        if (index == 2)
            return R.layout.activity_luancher_pager_item3;
        if (index == 3)
            return R.layout.activity_luancher_pager_item4;
        if (index == 4)
            return R.layout.activity_luancher_pager_item5;
        if (index == 5)
            return R.layout.activity_luancher_pager_item6;
        return R.layout.activity_luancher_pager_item1;
    }

    @Override
    public void undateUI(Message msg) {

    }

    @Override
    public void initUI() {
        if (index == 0) {
            ImageView first = (ImageView) findViewById(R.id.first);
            first.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first));
            ImageView secend = (ImageView) findViewById(R.id.secend);
            secend.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first1));
            ImageView three = (ImageView) findViewById(R.id.three);
            three.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first2));
        }
        if (index == 1) {
            ImageView imageView = (ImageView) findViewById(R.id.cutImg);
            frameAnim = (AnimationDrawable) context.getResources().getDrawable(R.drawable.cutimg);
            imageView.setBackgroundDrawable(frameAnim);
            frameAnim.start();
        }
        if (index == 2) {
            ImageView first1 = (ImageView) findViewById(R.id.first);
            first1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first2));
            ImageView secend1 = (ImageView) findViewById(R.id.secend);
            secend1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first1));
        }
        if (index == 3) {
            ImageView pukeimg = (ImageView) findViewById(R.id.pukeimg);
            frameAnim1 = (AnimationDrawable) context.getResources().getDrawable(R.drawable.cutimg1);
            pukeimg.setBackgroundDrawable(frameAnim1);
            frameAnim1.start();
        }
        if (index == 4) {
            ImageView tanzhutai = (ImageView) findViewById(R.id.tanzhutai);
            frameAnim2 = (AnimationDrawable) context.getResources().getDrawable(R.drawable.cutimg2);
            tanzhutai.setBackgroundDrawable(frameAnim2);
            frameAnim2.start();
        }
        if (index == 5) {
            ImageView jinpaichuangguan = (ImageView) findViewById(R.id.jinpaichuangguan);
            jinpaichuangguan.setAnimation(AnimationUtils.loadAnimation(context, R.anim.first));
        }
    }

    @Override
    public void regUIEvent() {

    }
}
