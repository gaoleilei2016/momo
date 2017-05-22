package com.shz.photosel.adapter;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.gdswww.library.R;
import com.shz.photosel.multiimagechooser.ShowSingleImageActivity;
import com.squareup.picasso.Picasso;

/**
 * 相册里面的图片适配器
 * 
 * @author Administrator
 * 
 */
public class AlbumGridViewAdapter extends BaseAdapter implements
		OnClickListener {

	private Context mContext;
	private ArrayList<String> dataList;
	private ArrayList<String> selectedDataList;
	private DisplayMetrics dm;

	public AlbumGridViewAdapter(Context c, ArrayList<String> dataList,
			ArrayList<String> selectedDataList) {

		mContext = c;
		this.dataList = dataList;
		this.selectedDataList = selectedDataList;
		dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	/**
	 * 存放列表项控件句柄
	 */
	private class ViewHolder {
		public ImageView imageView;
		public ToggleButton toggleButton;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.select_imageview, parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image_view);
			viewHolder.toggleButton = (ToggleButton) convertView
					.findViewById(R.id.toggle_button);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final String path = dataList.get(position);
		Picasso.with(mContext).load(new File(path))
		.resize(200, 200).centerCrop().placeholder(R.drawable.loading_img).
		error(R.drawable.loading_img).into(viewHolder.imageView);

		viewHolder.toggleButton
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						mContext.startActivity(new Intent(mContext,
								ShowSingleImageActivity.class).putExtra("path",
								path).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
						return true;
					}
				});
		viewHolder.toggleButton.setTag(position);
		viewHolder.toggleButton.setOnClickListener(this);
		if (isInSelectedDataList(path)) {
			viewHolder.toggleButton.setChecked(true);
		} else {
			viewHolder.toggleButton.setChecked(false);
		}

		return convertView;
	}

	private boolean isInSelectedDataList(String selectedString) {
		if(selectedDataList==null){
			return false;
		}
		for (int i = 0; i < selectedDataList.size(); i++) {
			if (selectedDataList.get(i).equals(selectedString)) {
				return true;
			}
		}
		return false;
	}

	public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}

	@Override
	public void onClick(View view) {
		if (view instanceof ToggleButton) {
			ToggleButton toggleButton = (ToggleButton) view;
			int position = (Integer) toggleButton.getTag();
			if (dataList != null && mOnItemClickListener != null
					&& position < dataList.size()) {
				mOnItemClickListener.onItemClick(toggleButton, position,
						dataList.get(position), toggleButton.isChecked());
			}
		}
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	public interface OnItemClickListener {
		public void onItemClick(ToggleButton view, int position, String path,
				boolean isChecked);
	}

}
