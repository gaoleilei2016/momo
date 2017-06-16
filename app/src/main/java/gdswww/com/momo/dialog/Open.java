package gdswww.com.momo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tauth.Tencent;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.activity.me.BaseUiListener;
import gdswww.com.momo.activity.me.LoginActivity;
import gdswww.com.momo.utils.Contents;

import static com.tencent.open.SocialConstants.PARAM_APPNAME;
import static com.tencent.open.SocialConstants.PARAM_APP_SOURCE;
import static com.tencent.open.SocialConstants.PARAM_IMAGE_URL;
import static com.tencent.open.SocialConstants.PARAM_SUMMARY;
import static com.tencent.open.SocialConstants.PARAM_TARGET_URL;
import static com.tencent.open.SocialConstants.PARAM_TITLE;

/**
 *
 */
public class Open extends Dialog implements View.OnClickListener, CallBackJSON.CallBack {
    private Activity context;
    private CallBackJSON.DiologCallBack callBackString;
    private ImageView closeDialog;
    private Button openZhibo;
    private EditText zhibo_name;
    private ImageView shareQQ, shareWX, shareSINA;

    public Open(Activity context, CallBackJSON.DiologCallBack callBackString) {
        super(context, R.style.AppThemes);
        this.context = context;
        this.callBackString = callBackString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openpopzhibo);
        closeDialog = (ImageView) findViewById(R.id.closeDialog);
        openZhibo = (Button) findViewById(R.id.openZhibo);
        zhibo_name = (EditText) findViewById(R.id.zhibo_name);
        shareQQ = (ImageView) findViewById(R.id.shareQQ);
        shareWX = (ImageView) findViewById(R.id.shareWX);
        shareSINA = (ImageView) findViewById(R.id.shareSINA);
        shareQQ.setOnClickListener(this);
        shareWX.setOnClickListener(this);
        shareSINA.setOnClickListener(this);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        openZhibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackString.getString(zhibo_name.getText().toString());
            }
        });
    }

    public void share() {
        Bundle bundle = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        bundle.putString(PARAM_TARGET_URL, "http://connect.qq.com/");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(PARAM_TITLE, "我在测试");
        //分享的图片URL
        bundle.putString(PARAM_IMAGE_URL,
                "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        //分享的消息摘要，最长50个字
        bundle.putString(PARAM_SUMMARY, "测试");
        //手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        bundle.putString(PARAM_APPNAME, "??我在测试");
        //标识该消息的来源应用，值为应用名称+AppId。
        bundle.putString(PARAM_APP_SOURCE, "星期几");
        Tencent.createInstance(Contents.APP_ID, context).shareToQQ(context, bundle, new BaseUiListener(Contents.SHAREQQ, this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shareQQ:
                share();
                break;
            case R.id.shareWX:

                break;
            case R.id.shareSINA:

                break;
        }
    }

    @Override
    public void callback(String str) {
        callBackString.getString(str);
//        Toast.makeText(context, "分享回调", Toast.LENGTH_SHORT).show();
    }
}
