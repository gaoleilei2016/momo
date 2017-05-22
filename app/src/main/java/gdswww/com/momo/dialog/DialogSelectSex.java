package gdswww.com.momo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;
/**
 *
 */
public class DialogSelectSex extends Dialog {
    private Context context;
    private TextView tv_dialog_man, tv_dialog_woman;
    private CallBackJSON.DiologCallBack callBackString;

    public DialogSelectSex(Context context, CallBackJSON.DiologCallBack callBackString) {
        super(context, R.style.my_dialog_style);
        this.context = context;
        this.callBackString = callBackString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectsex);
        tv_dialog_man = (TextView) findViewById(R.id.tv_dialog_man);
        tv_dialog_woman = (TextView) findViewById(R.id.tv_dialog_woman);
        tv_dialog_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackString.getString("男");
                dismiss();
            }
        });
        tv_dialog_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackString.getString("女");
                dismiss();
            }
        });
    }
}
