package gdswww.com.momo.activity.me;

import android.os.Message;
import android.view.View;
import android.widget.EditText;
import com.gdswww.library.dialog.AppProgressDialog;
import org.json.JSONObject;
import java.util.HashMap;
import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.utils.Contents;
/**
 * Created by Administrator on 2017/5/22 0022.
 */
public class LoginActivity extends MyBaseActivity implements CallBackJSON {
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
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("", "");
        params.put("", "");
        http.getBackstageData(params, (AppProgressDialog) getProgessDialog("正在登录...", true), Contents.login(), this);
    }

    /**
     * QQ登录
     *
     * @param view
     */
    public void qqLogin(View view) {

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
//        goActivity(RegisterAvtivity.class);
    }

    /**
     * 忘记密码
     *
     * @param view
     */
    public void goForgotPass(View view) {
//        goActivity(ForgotPassActivity.class);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {

    }

    @Override
    public void onFailure(JSONObject jsonObject) {

    }
}
