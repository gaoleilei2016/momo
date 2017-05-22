package gdswww.com.momo.activity.me;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import gdswww.com.momo.R;
import gdswww.com.momo.WebViewActivity;
import gdswww.com.momo.base.MyBaseActivity;
import gdswww.com.momo.dialog.ExitDialog;


/**
 * 设置
 * Created by Administrator on 2017/5/10 0010.
 */
public class SetUpActivity extends MyBaseActivity implements View.OnClickListener {
    private TextView tv_exit_login;
    private ExitDialog exitDialog;
    private CheckBox cb_mine;
    private RelativeLayout rl_mine_user_agreement, rl_mine_feedback;

    @Override
    public void updateUI(Message msg) {

    }


    @Override
    public void initUI() {
        setMyTitle("设置");
        tv_exit_login = (TextView) findViewById(R.id.tv_exit_login);
        cb_mine = (CheckBox) findViewById(R.id.cb_mine);
        rl_mine_user_agreement = (RelativeLayout) findViewById(R.id.rl_mine_user_agreement);
        rl_mine_feedback = (RelativeLayout) findViewById(R.id.rl_mine_feedback);
        exitDialog = new ExitDialog(SetUpActivity.this);
    }

    @Override
    public void regUIEvent() {
        cb_mine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SetUpActivity.this, "选中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SetUpActivity.this, "没选中", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_exit_login.setOnClickListener(this);
        rl_mine_user_agreement.setOnClickListener(this);
        rl_mine_feedback.setOnClickListener(this);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_set_up;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit_login:
                exitDialog.show();
                break;
            case R.id.rl_mine_user_agreement:
                Intent intent = new Intent(SetUpActivity.this, WebViewActivity.class).putExtra("title", "用户协议").putExtra("url", "http://mf.gdswww.com/index.php/Home/About/about_us.html");
                startActivity(intent);
                break;
            case R.id.rl_mine_feedback:
                toastShort("反馈");
                break;
        }
    }
}
