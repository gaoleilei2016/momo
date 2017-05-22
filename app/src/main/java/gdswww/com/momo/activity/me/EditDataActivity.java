package gdswww.com.momo.activity.me;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.gdswww.library.dialog.AppProgressDialog;
import com.gdswww.library.view.CircleImageView;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.shz.photosel.multiimagechooser.GetImageDialog;
import org.json.JSONObject;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.dialog.*;

/**
 * 编辑界面
 * Created by Administrator on 2017/5/10 0010.
 */
public class EditDataActivity extends MyBaseActivity {
    DialogSelectSex dialog;
    private DebugInterface http;
    ModifynNicknameDialog modifynNicknameDialog;
    TextView tv_nickname, tv_tv_sex, tv_address;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    CircleImageView head_img;

    @Override
    public void updateUI(Message msg) {

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
     * 剪切图片
     */
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
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    GetImageDialog getImageDialog;

    @Override
    public void initUI() {
        setMyTitle("编辑资料");
        tv_nickname = viewId(R.id.tv_nick_name);
        tv_tv_sex = viewId(R.id.tv_tv_sex);
        tv_address = viewId(R.id.tv_address);
        head_img = viewId(R.id.head_img);
        showRight("保存", new View.OnClickListener() {//修改资料上传数据
            @Override
            public void onClick(View v) {
                requestHttp();
            }
        });
        clickView(R.id.headview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acp.getInstance(EditDataActivity.this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).build(), new AcpListener() {
                    @Override
                    public void onGranted() {
                        getImageDialog = new GetImageDialog(EditDataActivity.this, new GetImageDialog.GetImageCallback() {
                            @Override
                            public void onTakePhoto() {
                                camera();
                            }

                            @Override
                            public void onGetPhoto() {
                                gallery();
                            }
                        });
                        getImageDialog.show();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        toastShort("权限拒绝");
                    }
                });
            }
        });
        clickView(R.id.nick_name, new View.OnClickListener() {//昵称
            @Override
            public void onClick(View v) {
                modifynNicknameDialog = new ModifynNicknameDialog(EditDataActivity.this, new CallBackJSON.DiologCallBack() {
                    @Override
                    public void getString(String str) {
                        tv_nickname.setText(str);
                        toastShort(str);
                    }
                });
                modifynNicknameDialog.show();
            }
        });
        clickView(R.id.sex, new View.OnClickListener() {//性别选择
            @Override
            public void onClick(View v) {
                dialog = new DialogSelectSex(EditDataActivity.this, new CallBackJSON.DiologCallBack() {
                    @Override
                    public void getString(String str) {
                        tv_tv_sex.setText(str);
                        toastShort(str);
                    }
                });
                dialog.show();
            }
        });
        clickView(R.id.address, new View.OnClickListener() {//选择地址
            @Override
            public void onClick(View v) {
//                tv_address.setText(str);
            }
        });

    }

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
                Toast.makeText(EditDataActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.head_img.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                if(tempFile!=null)
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void requestHttp() {
        http = new DebugInterface(aq, this);
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("","");
        parames.put("","");
        parames.put("","");
        parames.put("","");
        http.getBackstageData(parames, (AppProgressDialog) getProgessDialog("正在保存...", true), "", new CallBackJSON() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                toastShort("成功");
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                toastShort("失败");
            }
        });

    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_edit_data;
    }
}
