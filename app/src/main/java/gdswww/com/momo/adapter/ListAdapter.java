package gdswww.com.momo.adapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gdswww.com.momo.R;
import gdswww.com.momo.view.SwipeRevealLayout;
import gdswww.com.momo.view.ViewBinderHelper;
/**
 * Created by Chau Thai on 4/12/16.
 */
public class ListAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper;
    private List<String> list;
    Activity mContext;
    public ListAdapter(Activity context, List<String> objects) {
        mInflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
        this.list = objects;
        this.mContext=mContext;
        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public int getCount() {
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
    ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_list, parent, false);
            holder = new ViewHolder();
            holder.deleteView = convertView.findViewById(R.id.delete_layout);
            holder.list_item = convertView.findViewById(R.id.list_item);
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String item = getItem(position);
        if (item != null) {
            binderHelper.bind(holder.swipeLayout, item);
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(item);
                    notifyDataSetChanged();
                }
            });
            holder.list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(item+"",item+"");
                }
            });
        }
        return convertView;
    }

    public ViewHolder getHolder() {
        return holder;
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder {
        public View deleteView;
        public SwipeRevealLayout swipeLayout;
        public View list_item;
    }
}
