package gdswww.com.momo.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gdswww.library.dialog.AppProgressDialog;
import com.gdswww.library.toolkit.TextUtil;
import com.tencent.connect.UserInfo;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.Tencent;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.base.AppContext;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.utils.Contents;

import static com.tencent.open.SocialConstants.PARAM_APPNAME;
import static com.tencent.open.SocialConstants.PARAM_APP_SOURCE;
import static com.tencent.open.SocialConstants.PARAM_IMAGE_URL;
import static com.tencent.open.SocialConstants.PARAM_SUMMARY;
import static com.tencent.open.SocialConstants.PARAM_TARGET_URL;
import static com.tencent.open.SocialConstants.PARAM_TITLE;


/**
 * 登录
 * Created by Administrator on 2017/5/22 0022.
 */
public class LoginActivity extends MyBaseActivity implements CallBackJSON, CallBackJSON.CallBack {
    EditText ed_user, ed_pass;
    private DebugInterface http;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("登录");
        http = new DebugInterface(aq, this);
        ed_user = viewId(R.id.ed_user);
        ed_pass = viewId(R.id.ed_pass);
        ed_user.setText("18046725673");
        ed_pass.setText("123456");
        clickView(R.id.qqlogin, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqLogin(view);
            }
        });
        clickView(R.id.wxlogin, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toastShort("暂未开放");
//                share();
            }

        });
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        chaeckinput();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", ed_user.getText().toString());
        params.put("password", ed_pass.getText().toString());
        http.getBackstageData(params, (AppProgressDialog) getProgessDialog("正在登录...", true), Contents.login(), this);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        JSONObject json = jsonObject.optJSONObject("data");
        if (json.length() != 0) {
            setSaveData("token", json.optString("token"));
            setSaveData("phone", json.optString("phone"));
            setSaveData("pass", ed_pass.getText().toString());
            setSaveData("nick", json.optString("nick"));
            if(json.optString("sex").equals("1"))
            setSaveData("sex", "男");
            setSaveData("sex", "女");
            setSaveData("address", json.optString("address"));
            setSaveData("avatar", json.optString("avatar"));
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("phone", json.optString("phone"));
            hashMap.put("avatar", json.optString("avatar"));
            hashMap.put("nick", json.optString("nick"));
            hashMap.put("sex", json.optString("sex"));
            hashMap.put("address", json.optString("address"));
            hashMap.put("token", json.optString("token"));
            toastShort("登录成功");
            finish();
        }
    }

    @Override
    public void onFailure(JSONObject jsonObject) {

    }

    private void chaeckinput() {
        if (!TextUtil.checkIsInput(ed_user)) {
            toastShort("请输入手机号码!");
            ed_user.requestFocus();
            return;
        }
        if (!TextUtil.isMobileNumber(getEditTextString(ed_user))) {
            toastShort("请输入正确的手机号码!");
            ed_user.requestFocus();
            return;
        }
        if (!TextUtil.checkIsInput(ed_pass)) {
            toastShort("请输入密码!");
            ed_pass.requestFocus();
            return;
        }
    }

    /**
     * QQ登录
     *
     * @param view
     */
    public void qqLogin(View view) {
        Contents.mTencent = Tencent.createInstance(Contents.APP_ID, this.getApplicationContext());
        if (!Contents.mTencent.isSessionValid()) {
            pd = (AppProgressDialog) getProgessDialog("正在登录...", true);
            pd.show();
            Contents.mTencent.login(this, "all", new BaseUiListener(Contents.LOGIN, pd, LoginActivity.this));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != Contents.mTencent)
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
    }

    private UserInfo mInfo;

    public void getUserInfo() {
        mInfo = new UserInfo(this, Contents.mTencent.getQQToken());
        mInfo.getUserInfo(new BaseUiListener(Contents.GET_USERINFO, pd, this));
    }

    /**
     * QQ注销
     */
    public void logout() {
        if (Contents.mTencent != null) {
            Contents.mTencent.logout(this.getApplicationContext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logout();
    }

    @Override
    public void callback(String str) {
        if (str.equals(Contents.LOGIN)) getUserInfo();
        if (str.equals(Contents.GET_USERINFO)) finish();
        if (str.equals(Contents.SHAREQQ)) toastShort("回调");
    }

    /**
     * 微信登录
     *
     * @param view
     */
    public void wxLogin(View view) {

    }

    /**
     * 注册
     *
     * @param view
     */
    public void goRegister(View view) {
        goActivity(RegisterAvtivity.class);
    }

    /**
     * 忘记密码
     *
     * @param view
     */
    public void goForgotPass(View view) {
//        goActivity(ForgotPassActivity.class);
    }

}
