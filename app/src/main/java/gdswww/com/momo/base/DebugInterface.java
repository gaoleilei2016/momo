package gdswww.com.momo.base;


import android.app.Activity;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.gdswww.library.dialog.AppProgressDialog;
import com.gdswww.library.toolkit.NetUtil;
import org.json.JSONObject;
import java.util.HashMap;

import gdswww.com.momo.Interface.CallBackJSON;

public class DebugInterface {
    private AQuery aq;
    private Activity activity;

    public DebugInterface(AQuery aq, Activity activity) {
        this.aq = aq;
        this.activity = activity;
    }

    public void getBackstageData(HashMap<String, Object> params, AppProgressDialog pd, String url, final CallBackJSON callback) {
        aq.progress(pd).ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (!NetUtil.CheckNetworkAvailable(activity)) {
                    Toast.makeText(activity, "网络异常!请检查网络!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AppContext.LogInfo("url----->" + url, object + "");
                if (object != null) {
                    if ("1".equals(object.optString("code"))) {
                        callback.onSuccess(object);

                    } else {
                        callback.onFailure(object);
//                        Toast.makeText(activity, object.optString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity, "服务器异常!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getBackstageData(HashMap<String, Object> params, String url, final CallBackJSON callback) {
        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (!NetUtil.CheckNetworkAvailable(activity)) {
                    Toast.makeText(activity, "网络异常!请检查网络!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AppContext.LogInfo("url----->" + url, object + "");
                if (object != null) {
                    if ("1".equals(object.optString("code"))) {
                        callback.onSuccess(object);

                    } else {
                        callback.onFailure(object);
//                        Toast.makeText(activity, object.optString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity, "服务器异常!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getWelcome(HashMap<String, Object> params, String url, final CallBackJSON callback) {
        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if ("1".equals(object.optString("code"))) {
                    callback.onSuccess(object);
                } else {
                    activity.finish();
                }
            }
        });
    }

}
