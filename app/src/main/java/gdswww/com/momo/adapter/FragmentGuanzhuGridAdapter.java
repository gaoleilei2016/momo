package gdswww.com.momo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import gdswww.com.momo.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class FragmentGuanzhuGridAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<HashMap<String, String>> list;

    public FragmentGuanzhuGridAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        return list == null ? 0 : list.size();
        return 20;
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
            convertView = inflater.inflate(R.layout.zuixintem,null);
            holder.zhubo_img= (ImageView) convertView.findViewById(R.id.zhubo_img);
            holder.zhubo_name = (TextView) convertView.findViewById(R.id.zhubo_name);
            holder.zhubo_bofangcount = (TextView) convertView.findViewById(R.id.zhubo_bofangcount);
            holder.zhubo_theme = (TextView) convertView.findViewById(R.id.zhubo_theme);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //.resize(100,100)
        Picasso.with(context).load("http://life.gdswww.com/Application/Upload/advertising/14606925842380.jpg").into(holder.zhubo_img);
        return convertView;
    }

    class ViewHolder {
        ImageView zhubo_img;
        TextView zhubo_name, zhubo_bofangcount, zhubo_theme;
    }
}
