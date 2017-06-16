package activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import momo.com.gdswww.myapplication.R;

public class WebViewActivity extends BaseAcy {
    private WebView wv_util;
    FrameLayout view;

    @Override
    public void updateUI(Message msg) {
        setMyTitle("Hello");
    }

    @Override
    public void initUI() {
        wv_util = new WebView(this);
        view = (FrameLayout) findViewById(R.id.webView);
        view.addView(wv_util);
        wv_util.getSettings().setJavaScriptEnabled(true);
        wv_util.setHorizontalScrollBarEnabled(false);// 水平不显示
        wv_util.setVerticalScrollBarEnabled(false); // 垂直不显示
        WebSettings webSettings = wv_util.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv_util.loadUrl("https://www.baidu.com");
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.webview;
    }
}
