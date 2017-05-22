package gdswww.com.momo.base;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.gdswww.library.activity.GDSSwipeBackActivity;

import gdswww.com.momo.R;
import gdswww.com.momo.utils.PreferenceUtil;

public abstract class MyBaseActivity extends GDSSwipeBackActivity {
    private static long lastClickTime = 0;

    public void setSaveData(String key, String value) {
        PreferenceUtil.setStringValue(context, key, value);
    }

    public String getSaveData(String key) {
        return PreferenceUtil.getStringValue(context, key);
    }

    public void setMyTitle(String title) {
        aq.id(R.id.tv_activity_title).text(title);
    }

//    public void setMyImgTitle(String title) {
//        aq.id(R.id.include_img_title).text(title);
//    }

    public void showRight(String rightStr, OnClickListener listener) {
        aq.id(R.id.tv_activity_title_right).visibility(View.VISIBLE).text(rightStr).clicked(listener);
    }

//    public void showSelectCity(String rightStr, OnClickListener listener) {
//        aq.id(R.id.tv_select_city).visibility(View.VISIBLE).text(rightStr).clicked(listener);
//    }

    public void showImgRight(String rightStr, OnClickListener listener) {
        aq.id(R.id.iv_activity_title_right).visibility(View.VISIBLE).text(rightStr).clicked(listener);
    }

    public void showRightText(String rightStr) {
        aq.id(R.id.tv_activity_title_right).visibility(View.VISIBLE).text(rightStr);
    }

    public void showLeftText(String rightStr) {
        aq.id(R.id.tv_activity_title_left).visibility(View.VISIBLE).text(rightStr);
    }

    public void hideLeftText() {
        aq.id(R.id.tv_activity_title_left).visibility(View.GONE);
    }

    /**
     * 防止多次点击生成多个页面
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 500) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

//    public void showRightImgText(String rightStr) {
//        aq.id(R.id.include_img_right_tv).visibility(View.VISIBLE).text(rightStr);
//    }

//    public void setGeneralTitle(String title) {
//        aq.id(R.id.tv_general_header_title).text(title);
//    }

//    public void hideGeneralTitle() {
//        aq.id(R.id.tv_general_header_title).visibility(View.GONE);
//    }

//    public void showGeneralRight(String rightStr, OnClickListener listener) {
//        aq.id(R.id.tv_general_header_right).visibility(View.VISIBLE).text(rightStr).clicked(listener);
//    }

//    public void hideGeneralRight() {
//        aq.id(R.id.tv_general_header_right).visibility(View.GONE);
//    }

    public String getTextString(TextView tv) {
        String str = "";
        str = (null == tv ? "" : tv.getText().toString().trim());
        return str;
    }

    public void titleBack(View v) {
        finish();
    }

    @Override
    public void onCreateBefore(Bundle savedInstanceState) {
        super.onCreateBefore(savedInstanceState);
    }
}
