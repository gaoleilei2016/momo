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
import gdswww.com.momo.modle.Index;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class TopAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<Index> list;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    public TopAdapter(Context context, ArrayList<Index> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Index getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
//        System.out.println("p1------+++>>>" + position);
        int p = position;
        if (p < 3) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = null;
        ViewHolder1 holder1 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    holder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.activity_top,parent, false);
//                    holder.img_search_anchor_avatar = (CircleImageView) convertView.findViewById(R.id.img_search_anchor_avatar);
//                    holder.tv_search_anchor_name = (TextView) convertView.findViewById(R.id.tv_search_anchor_name);
//                    holder.tv_search_anchor_autograph = (TextView) convertView.findViewById(R.id.tv_search_anchor_autograph);
//                    holder.tv_search_anchor_whether_concern = (TextView) convertView.findViewById(R.id.tv_search_anchor_whether_concern);
                    convertView.setTag(holder);
                    break;
                case TYPE_2:
                    holder1 = new ViewHolder1();
                    convertView = inflater.inflate(R.layout.activity_top1,parent, false);
                    convertView.setTag(holder1);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_2:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
            }
        }
        return convertView;
    }

    class ViewHolder {
        CircleImageView img_search_anchor_avatar;
        TextView tv_search_anchor_name, tv_search_anchor_autograph, tv_search_anchor_whether_concern;
    }
    class ViewHolder1 {
        CircleImageView img_search_anchor_avatar;
        TextView tv_search_anchor_name, tv_search_anchor_autograph, tv_search_anchor_whether_concern;
    }
}
