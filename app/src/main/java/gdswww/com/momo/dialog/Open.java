package gdswww.com.momo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.R;

/**
 *
 */
public class Open extends Dialog implements View.OnClickListener {
    private Context context;
    private CallBackJSON.DiologCallBack callBackString;
    private ImageView closeDialog;
    private Button openZhibo;
    private EditText zhibo_name;
    private ImageView shareQQ, shareWX, shareSINA;

    public Open(Context context, CallBackJSON.DiologCallBack callBackString) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareQQ:
                break;
            case R.id.shareWX:
                break;
            case R.id.shareSINA:
                break;
        }
    }
}
