package com.shz.photosel.adapter;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.gdswww.library.R;
import com.squareup.picasso.Picasso;

public class GridImageAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<String> dataList;
	private DisplayMetrics dm;

	public GridImageAdapter(Context c, ArrayList<String> dataList) {

		mContext = c;
		this.dataList = dataList;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.show_imageview, parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image_view);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String path;
		if (dataList != null && position<dataList.size() )
			path = dataList.get(position);
		else
			path = "camera_default";
		Log.i("path", "path:"+path+"::position"+position);
		if (path.contains("default")){
			viewHolder.imageView.setScaleType(ScaleType.CENTER_CROP);
			Picasso.with(mContext).load(R.drawable.album_add)
			.resize(200, 200).placeholder(R.drawable.loading_img).
			error(R.drawable.loading_img).into(viewHolder.imageView);
			
		}else{
			if(path.contains("file:///android_asset")){
				Picasso.with(mContext).load(path)
				.resize(200, 200).placeholder(R.drawable.loading_img).centerCrop().
				error(R.drawable.loading_img).into(viewHolder.imageView);
			}else{
				Picasso.with(mContext).load(new File(path))
				.resize(200, 200).placeholder(R.drawable.loading_img).centerCrop().
				error(R.drawable.loading_img).into(viewHolder.imageView);
			}
			
		}
		return convertView;
	}
	
	public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}
	/**
	 * 存放列表项控件句柄
	 */
	private class ViewHolder {
		public ImageView imageView;
	}


}
