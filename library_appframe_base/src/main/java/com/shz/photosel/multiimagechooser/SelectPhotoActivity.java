package com.shz.photosel.multiimagechooser;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gdswww.library.R;
import com.shz.photosel.util.PictureUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SelectPhotoActivity extends Activity {

    private ArrayList<String> dataList;
    public static final String DATA_LIST = "dataList";
    public static final String MAX_SEL = "maxSel";
    public static final String CAMERA_DEFAULT = "camera_default";
    private int maxSel;
    private String targetPath = Environment.getExternalStorageDirectory().getPath() + "/TakePhotoCaches/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = width;
        window.setAttributes(params);
        dataList = getIntent().getStringArrayListExtra(DATA_LIST);
        maxSel = getIntent().getIntExtra(MAX_SEL, 1);
        if (dataList == null) {
            dataList = new ArrayList<String>();
        }

    }

    public void takePhoto(View v) {
        if (ContextCompat.checkSelfPermission(SelectPhotoActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SelectPhotoActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            takePhoto();
        }
    }

    public void selectPhoto(View v) {
        Intent intent = new Intent(SelectPhotoActivity.this,
                GetAllImageForder.class);
        Bundle bundle = new Bundle();
        // intent.putArrayListExtra("dataList", dataList);
        bundle.putStringArrayList(DATA_LIST,
                getIntentArrayList(dataList));
        bundle.putInt(MAX_SEL, 1);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }

    public void cancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {
                // Permission Denied
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                @SuppressWarnings("unchecked")
                ArrayList<String> tDataList = (ArrayList<String>) bundle
                        .getSerializable("dataList");
                if (tDataList != null) {
                    dataList.clear();
                    dataList.addAll(tDataList);

                    if (dataList.size() < maxSel) {
                        dataList.add(CAMERA_DEFAULT);
                    }
                }
            }
        } else if (requestCode == 1) {
            if (cameraFile != null && cameraFile.exists()) {
                if (resultCode != RESULT_OK) {
                    cameraFile.delete();
                } else {
                    try {
                        File targerFile = new File(Environment.getExternalStorageDirectory().getPath() + "/TakePhotoCaches/");
                        if (!targerFile.exists()) {
                            targerFile.mkdirs();
                        }
                        String path = PictureUtil.compressImage(getApplicationContext(), cameraFile, targetPath, 60, true);
                        dataList.add(path);

                        if (dataList.contains(CAMERA_DEFAULT)) {
                            dataList.remove(CAMERA_DEFAULT);
                        }

                        if (dataList.size() < maxSel) {
                            dataList.add(CAMERA_DEFAULT);
                        }
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getApplicationContext(), "文件不存在，处理失败", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
        setResult(RESULT_OK, getIntent().putExtra(DATA_LIST, dataList));
        finish();

    }

    private ArrayList<String> getIntentArrayList(ArrayList<String> dataList) {

        ArrayList<String> tDataList = new ArrayList<String>();

        for (String s : dataList) {
            if (!s.contains("default")) {
                tDataList.add(s);
            }
        }

        return tDataList;

    }

    private File cameraFile;

    private void takePhoto() {
        if (!isExitsSdcard()) {
            Toast.makeText(getApplicationContext(), "未找到外部存储设备，不能拍照", Toast.LENGTH_SHORT).show();
            return;
        }
        cameraFile = new File(Environment.getExternalStorageDirectory().getPath() + "/TakePhotoCache/"
                + System.currentTimeMillis() + ".jpg");
        cameraFile.getParentFile().mkdirs();
        startActivityForResult(
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
                        MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
                1);
    }

    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
}
