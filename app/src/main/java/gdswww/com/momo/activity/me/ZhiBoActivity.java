package gdswww.com.momo.activity.me;

import android.os.Build;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import gdswww.com.momo.R;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.view.ZhuBoDialog;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ZhiBoActivity extends MyBaseActivity {
    private ImageView imageView;//查看主播
    @Override
    public void updateUI(Message msg) {

    }
    @Override
    public void initUI() {
        toastShort("主播：ID" + getIntent().getIntExtra("zhubo_id", 0));
        loadImg((ImageView) viewId(R.id.zhubo_img), R.mipmap.zhubo);
        imageView = (ImageView) findViewById(R.id.zhibo_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    @Override
    public void regUIEvent() {

    }
    @Override
    public int getLayout() {
        //透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        return R.layout.activity_zhibojian;
    }

    /**
     * 弹出对话框。
     */
    public void showDialog() {
        final ZhuBoDialog.Builder dialog = new ZhuBoDialog.Builder(ZhiBoActivity.this)
                .setCity("深圳")
                .setFensi("4544")
                .setGuanZhu("1235")
                .setHuozeng("4565")
                .setName("小仙女(ID:25641)")
                .setSex("女")
                .setSongchu("4145")
                .setAiTeOnCliCKListener(new View.OnClickListener() {//艾特
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ZhiBoActivity.this, "你点击了艾特。", Toast.LENGTH_SHORT).show();
                    }
                }).setGuanzhuOnCliCKListener(new View.OnClickListener() {//关注
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ZhiBoActivity.this, "你点击了关注。", Toast.LENGTH_SHORT).show();

                    }
                }).setQuanXianOnCliCKListener(new View.OnClickListener() {//权限
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ZhiBoActivity.this, "你点击权限。", Toast.LENGTH_SHORT).show();

                    }
                }).setQuxiaoOnCliCKListener(new View.OnClickListener() {//取消
                    @Override
                    public void onClick(View view) {
                        dimissProgressDialog();


                    }
                }).setSongLiOnCliCKListener(new View.OnClickListener() {//送礼
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ZhiBoActivity.this, "你点击了送礼。", Toast.LENGTH_SHORT).show();

                    }
                });
        dialog.create().show();
    }
}
