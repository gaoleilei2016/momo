package gdswww.com.momo.activity.me;

import android.util.Log;

import com.gdswww.library.dialog.AppProgressDialog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.utils.Contents;
/**
 * Created by Administrator on 2017/5/23 0023.
 */
public class BaseUiListener implements IUiListener {
    private String openid;
    private String access_token;
    private String expires_in;
    private String head_url;
    private String nickName;


    CallBackJSON.CallBack mCallBack;
    String str;
    AppProgressDialog pd;
    public BaseUiListener() {
    }
    public BaseUiListener(String str, CallBackJSON.CallBack mCallBack) {
        this.str = str;
        this.mCallBack = mCallBack;
    }
    public BaseUiListener(String str, AppProgressDialog pd) {
        this.str = str;
        this.pd = pd;
    }
    public BaseUiListener(String str, AppProgressDialog pd, CallBackJSON.CallBack mCallBack) {
        this.str = str;
        this.pd = pd;
        this.mCallBack = mCallBack;
    }

    @Override
    public void onComplete(Object response) {
        Log.d("response", "" + response.toString());
        if (Contents.LOGIN.equals(str)) {
            Log.d("response", "" + response.toString());
            JSONObject jsonObject = ((JSONObject) response);
            try {
                openid = jsonObject.getString("openid");
                access_token = jsonObject.getString("access_token");
                expires_in = jsonObject.getString("expires_in");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (null != openid && openid != "") {
                Contents.mTencent.setOpenId(openid);
                Contents.mTencent.setAccessToken(access_token, expires_in);
                mCallBack.callback(str);
            }
        }
        if (Contents.GET_USERINFO.equals(str)) {
            Log.d("response", "" + response.toString());
            JSONObject jsonObject = ((JSONObject) response);
            try {
                Log.d("figureurl_qq_1", jsonObject.getString("figureurl_qq_1"));
                Log.d("figureurl_qq_2", jsonObject.getString("figureurl_qq_2"));
                head_url=jsonObject.getString("figureurl_qq_2");
                nickName = jsonObject.getString("nickname");
                Log.d("nickname", jsonObject.getString("nickname"));
                Log.d("city", jsonObject.getString("city"));
                Log.d("figureurl_1", jsonObject.getString("figureurl_1"));
                Log.d("figureurl_2", jsonObject.getString("figureurl_2"));
                Log.d("province", jsonObject.getString("province"));
                Log.d("gender", jsonObject.getString("gender"));
                Log.d("figureurl", jsonObject.getString("figureurl"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mCallBack.callback(str);
        }
        if (Contents.SHAREQQ.equals(str)) {
            mCallBack.callback(str);
        }
    }

    @Override
    public void onError(UiError uiError) {
    Log.d("uiError",uiError.toString());
    }

    @Override
    public void onCancel() {
        Log.d("onCancel","onCancel");
    }
}
