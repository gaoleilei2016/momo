package gdswww.com.momo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gdswww.library.view.CircleImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gdswww.com.momo.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class RechargeAdapter extends BaseAdapter {
    private Context context;

    private List<String> list;

    public RechargeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        return list == null ? 0 : list.size();
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    int mPosition;
    HashMap<Integer, ViewHolder> hashMap = new HashMap<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            hashMap.put(position, holder);
            convertView = inflater.inflate(R.layout.item_recharge, null);
            holder.tv_item_recharge_integral = (TextView) convertView.findViewById(R.id.tv_item_recharge_integral);
            holder.tv_item_recharge_moneys = (TextView) convertView.findViewById(R.id.tv_item_recharge_moneys);
            holder.chooceLayout = (LinearLayout) convertView.findViewById(R.id.chooceLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_item_recharge_integral.setText(getItem(position));
        holder.tv_item_recharge_moneys.setText(getItem(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv_item_recharge_integral, tv_item_recharge_moneys;
        LinearLayout chooceLayout,chooce_bg;
    }
}

