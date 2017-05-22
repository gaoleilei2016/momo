package gdswww.com.momo.activity.me;

import android.os.Message;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;
import gdswww.com.momo.adapter.MessageAdapter;
import gdswww.com.momo.base.MyBaseActivity;
import zrc.widget.ZrcListView;


/**
 * 消息
 * Created by Administrator on 2017/5/11 0011.
 */

public class MessageActivity extends MyBaseActivity {
    private ZrcListView lv_message;
    private ArrayList<HashMap<String, String>> list;
    private MessageAdapter messageAdapter;

    @Override
    public void updateUI(Message msg) {

    }

    @Override
    public void initUI() {
        setMyTitle("消息");
        lv_message = viewId(R.id.lv_message);
        list = new ArrayList<>();
        messageAdapter = new MessageAdapter(MessageActivity.this, list);
        lv_message.setAdapter(messageAdapter);
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_message;
    }
}
