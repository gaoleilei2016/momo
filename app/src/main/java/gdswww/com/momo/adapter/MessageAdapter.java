package gdswww.com.momo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gdswww.library.view.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class MessageAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<HashMap<String, String>> list;

    public MessageAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        return list == null ? 0 : list.size();
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_message,
                    null);
            holder.tv_message_time = (TextView) convertView.findViewById(R.id.tv_message_time);
            holder.tv_message_title = (TextView) convertView.findViewById(R.id.tv_message_title);
            holder.tv_message_content = (TextView) convertView.findViewById(R.id.tv_message_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_message_time, tv_message_title, tv_message_content;
    }
}
