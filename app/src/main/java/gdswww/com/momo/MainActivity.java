package gdswww.com.momo;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdswww.library.tab.BaseTabActivity;

import gdswww.com.momo.Interface.CallBackJSON;
import gdswww.com.momo.activity.home.TabIndexActivity;
import gdswww.com.momo.activity.me.ListViewDeleteItemActivity;
import gdswww.com.momo.activity.me.MineActivity;
import gdswww.com.momo.dialog.Open;


public class MainActivity extends BaseTabActivity {
    private ImageView tab_live_broadcast;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        setTabById(v.getId());
    }


    @Override
    public void initView() {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_main);
        tab_live_broadcast = viewId(R.id.tab_live_broadcast);
        addTabButtonById(R.id.tab_home, TabIndexActivity.class);
//        addTabButtonById(R.id.tab_live_broadcast, ListViewDeleteItemActivity.class);
        addTabButtonById(R.id.tab_mine, MineActivity.class);
        onClick(findViewById(R.id.tab_home));
        tab_live_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open mOpen=new Open(MainActivity.this, new CallBackJSON.DiologCallBack() {
                    @Override
                    public void getString(String str) {
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                });
                mOpen.show();
            }
        });
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
        }
        return super.dispatchKeyEvent(event);
    }


}
