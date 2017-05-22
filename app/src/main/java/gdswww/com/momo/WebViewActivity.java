package gdswww.com.momo;

import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import gdswww.com.momo.base.MyBaseActivity;


/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class WebViewActivity extends MyBaseActivity {
    private WebView wv_util;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle(getIntent().getStringExtra("title"));
        findid();
        wv_util.getSettings().setJavaScriptEnabled(true);
        wv_util.setHorizontalScrollBarEnabled(false);// 水平不显示
        wv_util.setVerticalScrollBarEnabled(false); // 垂直不显示
        WebSettings webSettings = wv_util.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv_util.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_web_view;
    }


    private void findid() {
        wv_util = (WebView) findViewById(R.id.wv_util);
    }
}
