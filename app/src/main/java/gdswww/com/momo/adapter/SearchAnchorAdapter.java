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

public class SearchAnchorAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<HashMap<String, String>> list;

    public SearchAnchorAdapter(Context context, ArrayList<HashMap<String, String>> list) {
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
            convertView = inflater.inflate(R.layout.item_search_anchor,
                    null);
//            holder.img_search_anchor_avatar = (CircleImageView) convertView.findViewById(R.id.img_search_anchor_avatar);
//            holder.tv_search_anchor_name = (TextView) convertView.findViewById(R.id.tv_search_anchor_name);
//            holder.tv_search_anchor_autograph = (TextView) convertView.findViewById(R.id.tv_search_anchor_autograph);
//            holder.tv_search_anchor_whether_concern = (TextView) convertView.findViewById(R.id.tv_search_anchor_whether_concern);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        CircleImageView img_search_anchor_avatar;
        TextView tv_search_anchor_name, tv_search_anchor_autograph, tv_search_anchor_whether_concern;
    }
}
