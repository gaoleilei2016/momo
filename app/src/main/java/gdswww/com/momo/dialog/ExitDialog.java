package gdswww.com.momo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gdswww.com.momo.R;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class ExitDialog extends Dialog implements
        View.OnClickListener {
    private TextView tv_agree, tv_cancel;
    private Activity context;

    public ExitDialog(Activity context) {
        super(context, R.style.my_dialog_style);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);
        findid();
    }

    private void findid() {
        // TODO Auto-generated method stub
        tv_agree = (TextView) findViewById(R.id.tv_agree);
        tv_agree.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.tv_agree:
                dismiss();
                context.finish();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

}

