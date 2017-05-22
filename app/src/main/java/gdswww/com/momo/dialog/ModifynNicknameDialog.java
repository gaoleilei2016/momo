package gdswww.com.momo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.Interface.CallBackJSON.DiologCallBack;
import gdswww.com.momo.R;


/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class ModifynNicknameDialog extends Dialog {

    CallBackJSON.DiologCallBack mDialogCallBack;

    public ModifynNicknameDialog(Activity context, CallBackJSON.DiologCallBack callBackString) {
        super(context, R.style.my_dialog_style);
        this.mDialogCallBack = callBackString;
    }

    TextView tv_save;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modifyn_nickname);
        tv_save = (TextView) findViewById(R.id.tv_save);
        editText = (EditText) findViewById(R.id.ed_dialog_content);
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogCallBack.getString(editText.getText().toString());
                dismiss();
            }
        });
    }
}
