package gdswww.com.momo.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.shz.photosel.multiimagechooser.GetAllImageForder;
import com.shz.photosel.multiimagechooser.SelectImageFromAssetURL;
import com.shz.photosel.util.PictureUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import gdswww.com.momo.R;

public class ActivitySelectPhoto extends Activity {

	private ArrayList<String> dataList;
	public static final String DATA_LIST = "dataList";
	public static final String MAX_SEL = "maxSel";
	public static final String CAMERA_DEFAULT = "camera_default";
	private int maxSel;
	private String targetPath = Environment.getExternalStorageDirectory()
			.getPath() + "/TakePhotoCaches/";

	// private String dirName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_photo);
		dataList = getIntent().getStringArrayListExtra(DATA_LIST);
		maxSel = getIntent().getIntExtra(MAX_SEL, 1);
		if (dataList == null) {
			dataList = new ArrayList<String>();
		}
		Window window = this.getWindow();
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		this.onWindowAttributesChanged(wl);
	}

	public void takePhoto(View v) {
		if (dataList.size() < maxSel) {
			takePhoto();
		} else {
			Toast.makeText(this, "最多只能选择" + maxSel + "张图片！", Toast.LENGTH_SHORT)
					.show();
		}

	}

	public void selectPhoto(View v) {
		Intent intent = new Intent(ActivitySelectPhoto.this,
				GetAllImageForder.class);
		Bundle bundle = new Bundle();
		// intent.putArrayListExtra("dataList", dataList);
		bundle.putStringArrayList(DATA_LIST, getIntentArrayList(dataList));
		bundle.putInt(MAX_SEL, maxSel);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}

	public void cancel(View v) {
		setResult(RESULT_CANCELED);
		finish();
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
						File targerFile = new File(Environment
								.getExternalStorageDirectory().getPath()
								+ "/TakePhotoCaches/");
						if (!targerFile.exists()) {
							targerFile.mkdirs();
						}
						String path = PictureUtil.compressImage(
								getApplicationContext(), cameraFile,
								targetPath, 60, true);
						dataList.add(path);

						if (dataList.contains(CAMERA_DEFAULT)) {
							dataList.remove(CAMERA_DEFAULT);
						}

						if (dataList.size() < maxSel) {
							dataList.add(CAMERA_DEFAULT);
						}
					} catch (FileNotFoundException e) {
					}

				}
			}
		} else if (requestCode == 250) {
			if (resultCode == RESULT_OK
					&& !dataList.contains(data.getStringExtra("path"))) {
				dataList.add(data.getStringExtra("path"));
				if (dataList.contains(CAMERA_DEFAULT)) {
					dataList.remove(CAMERA_DEFAULT);
				}

				if (dataList.size() < maxSel) {
					dataList.add(CAMERA_DEFAULT);
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

	public void systemImage(View v) {
		if (getIntent().getStringExtra("asset") == null) {
			return;
		}
		for (String name : dataList) {
			if (name.contains("file:///android_asset")) {
				return;
			}
		}
		startActivityForResult(new Intent(getApplicationContext(),
				SelectImageFromAssetURL.class).putExtra("path", getIntent()
				.getStringExtra("asset")), 250);
	}

	private void takePhoto() {
		if (!isExitsSdcard()) {
			return;
		}
		cameraFile = new File(Environment.getExternalStorageDirectory()
				.getPath()
				+ "/TakePhotoCache/"
				+ System.currentTimeMillis()
				+ ".jpg");
		cameraFile.getParentFile().mkdirs();
		startActivityForResult(
				new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
						MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)), 1);
	}

	public static boolean isExitsSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

}
