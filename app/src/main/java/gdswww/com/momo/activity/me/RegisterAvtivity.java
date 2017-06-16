package gdswww.com.momo.activity.me;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.gdswww.library.dialog.AppProgressDialog;
import com.gdswww.library.toolkit.TextUtil;

import org.json.JSONObject;

import java.util.HashMap;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
import gdswww.com.momo.base.CleanableEditText;
import gdswww.com.momo.base.DebugInterface;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.utils.Contents;
import gdswww.com.momo.utils.CountDownUtils;

/**
 * 注册
 * Created by Administrator on 2017/5/22 0022.
 */
public class RegisterAvtivity extends MyBaseActivity {
    DebugInterface di;

    @Override
    public void updateUI(Message msg) {

    }

    HashMap<String, Object> params;
    CleanableEditText mcleanableEd, ed_tv_reg_getcode, ed_repass, ed_pass;
    TextView tv_reg_getcode;

    /**
     * 发送验证码接口
     *
     * @return
     */
    private void code() {
        if (params != null)
            params.clear();
        params = new HashMap<String, Object>();
        params.put("phone", getEditTextString(mcleanableEd));
        params.put("token", getSaveData("token"));
        params.put("type", "1");
        di.getBackstageData(params, (AppProgressDialog) getProgessDialog("正在加载...", true), Contents.getSendCode(), new CallBackJSON() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                toastShort("发送成功!");
                new CountDownUtils(60000, 500, tv_reg_getcode, null, null).start();
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                toastShort(jsonObject.optString("msg"));
            }
        });
    }

    @Override
    public void initUI() {
        setMyTitle("注册");
        di = new DebugInterface(aq, this);
        mcleanableEd = viewId(R.id.ed_user);
        ed_tv_reg_getcode = viewId(R.id.ed_tv_reg_getcode);
        ed_pass = viewId(R.id.ed_pass);
        ed_repass = viewId(R.id.ed_repass);
        tv_reg_getcode = viewId(R.id.tv_reg_getcode);
        mcleanableEd.setText("17710385736");
        tv_reg_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code();
            }
        });
        //确定注册
        clickView(R.id.register, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtil.checkIsInput(mcleanableEd)) {
                    toastShort("请输入手机号码!");
                    mcleanableEd.requestFocus();
                    return;
                }
                if (!TextUtil.isMobileNumber(getEditTextString(mcleanableEd))) {
                    toastShort("请输入正确的手机号码!");
                    mcleanableEd.requestFocus();
                    return;
                }
                if (!TextUtil.checkIsInput(ed_tv_reg_getcode)) {
                    toastShort("请输入验证码!");
                    ed_tv_reg_getcode.requestFocus();
                    return;
                }
                if (!TextUtil.checkIsInput(ed_pass)) {
                    toastShort("请输入密码!");
                    ed_pass.requestFocus();
                    return;
                }
                if (!TextUtil.checkIsInput(ed_repass)) {
                    toastShort("请再次输入密码!");
                    ed_repass.requestFocus();
                    return;
                }
                if (!getEditTextString(ed_pass).equals(getEditTextString(ed_repass))) {
                    toastShort("两次输入的密码不一致!");
                    ed_repass.requestFocus();
                    return;
                }
                register();
            }
        });
    }

    /**
     * 注册接口
     *
     * @return
     */
    private void register() {
        DebugInterface di = new DebugInterface(aq, this);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", getEditTextString(mcleanableEd));
        params.put("password", getEditTextString(ed_pass));
        params.put("code", getEditTextString(ed_tv_reg_getcode));
        params.put("token", getSaveData("token"));
        di.getBackstageData(params, (AppProgressDialog) getProgessDialog("正在加载...", true), Contents.register(), new CallBackJSON() {
            @Override
            public void onSuccess(JSONObject jsonObject)
            {
                toastShort("注册成功!");
                finish();
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                toastShort(jsonObject.optString("msg"));
            }

        });
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }
}
