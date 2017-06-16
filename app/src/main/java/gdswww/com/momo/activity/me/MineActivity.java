package gdswww.com.momo.activity.me;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gdswww.library.view.CircleImageView;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.shz.photosel.multiimagechooser.GetImageDialog;
import com.shz.photosel.multiimagechooser.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gdswww.com.momo.R;
import gdswww.com.momo.WebViewActivity;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.utils.ActivitySelectPhoto;

/**
 * 个人中心
 * Created by Administrator on 2017/5/10 0010.
 */

public class MineActivity extends MyBaseActivity implements View.OnClickListener {
    private RelativeLayout re_about_us, rl_set_up, rl_mine_message,kanduo;
    private TextView tv_mine_recharge, ed_open;
    private CircleImageView img_search_anchor_avatar;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    @Override
    public void updateUI(Message msg) {

    }
    TextView openGuanzhu,openGuanzhu1;
    @Override
    public void initUI() {
        findid();
        initDataFromBD();
    }

    private void initDataFromBD() {
        ((TextView)viewId(R.id.name)).setText(getSaveData("nick")+" ");
        if(getSaveData("sex").equals("1"))
        ((ImageView)viewId(R.id.sex)).setImageResource(R.mipmap.nan);
        ((ImageView)viewId(R.id.sex)).setImageResource(R.mipmap.nv);
        Picasso.with(getApplicationContext())
                .load(getSaveData("avatar"))
                .into(img_search_anchor_avatar);
        Log.e("MsgMain+图片：",getSaveData("avatar"));
    }

    private void findid() {
        re_about_us = viewId(R.id.re_about_us);
        rl_set_up = viewId(R.id.rl_set_up);
        ed_open = viewId(R.id.ed_open);
        rl_mine_message = viewId(R.id.rl_mine_message);
        tv_mine_recharge = viewId(R.id.tv_mine_recharge);
        img_search_anchor_avatar = viewId(R.id.img_search_anchor_avatar);
        kanduo=viewId(R.id.kanduo);
        openGuanzhu=viewId(R.id.openGuanzhu);
        openGuanzhu1=viewId(R.id.openGuanzhu1);
    }

    /*
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    @Override
    public void regUIEvent() {
        re_about_us.setOnClickListener(this);
        ed_open.setOnClickListener(this);
        rl_set_up.setOnClickListener(this);
        rl_mine_message.setOnClickListener(this);
        tv_mine_recharge.setOnClickListener(this);
        img_search_anchor_avatar.setOnClickListener(this);
        kanduo.setOnClickListener(this);
        openGuanzhu.setOnClickListener(this);
        openGuanzhu1.setOnClickListener(this);
    }

    /*
     * 从相机获取
     */
    public void camera() {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
65   * 剪切图片
66   */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
87   * 判断sdcard是否被挂载
88   */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    public void openGuanzhu(View view){
        goActivity(GuanZhuActivity.class);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_mine;
    }

    GetImageDialog getImageDialog;

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.re_about_us:
                intent = new Intent(MineActivity.this, WebViewActivity.class).putExtra("title", "关于我们").putExtra("url", "http://mf.gdswww.com/index.php/Home/About/about_us.html");
                startActivity(intent);
                break;
            case R.id.ed_open:
                intent = new Intent(MineActivity.this, EditDataActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_up:
                intent = new Intent(MineActivity.this, SetUpActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_message:
                intent = new Intent(MineActivity.this, MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mine_recharge:
                intent = new Intent(MineActivity.this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.img_search_anchor_avatar://打开本地图库
                startActivity(new Intent(this,LoginActivity.class));
//                Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).build(), new AcpListener() {
//                    @Override
//                    public void onGranted() {
//                        getImageDialog = new GetImageDialog(MineActivity.this, new GetImageDialog.GetImageCallback() {
//                            @Override
//                            public void onTakePhoto() {
//                                camera();
//                            }
//
//                            @Override
//                            public void onGetPhoto() {
//                                gallery();
//                            }
//                        });
//                        getImageDialog.show();
//                    }
//
//                    @Override
//                    public void onDenied(List<String> permissions) {
//                        toastShort("权限拒绝");
//                    }
//                });
                break;
            case R.id.kanduo:
                goActivity(ListViewDeleteItemActivity.class);
                break;
            case R.id.openGuanzhu:
            case R.id.openGuanzhu1:
                goActivity(GuanZhuActivity.class);
                break;

        }
    }

    private ArrayList<String> headList = new ArrayList<>();


    String img = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MineActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.img_search_anchor_avatar.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateBefore(Bundle savedInstanceState) {
        super.onCreateBefore(savedInstanceState);
        getSwipeBackLayout().setEdgeTrackingEnabled(100);
    }
}
