package gdswww.com.momo.Interface;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/7/15.
 */
public interface CallBackJSON {
    void onSuccess(JSONObject jsonObject);

    void onFailure(JSONObject jsonObject);

    interface DiologCallBack {
        public void getString(String str);
    }

    interface CallBack {
        public void callback(String str);
    }
}
