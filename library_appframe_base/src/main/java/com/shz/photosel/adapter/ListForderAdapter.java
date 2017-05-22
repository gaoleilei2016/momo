package com.shz.photosel.adapter;
import java.io.File;
import java.util.List;

import com.gdswww.library.R;
import com.shz.photosel.ImageBean;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class ListForderAdapter extends BaseAdapter {
	public Context context;
	private List<ImageBean> dir;
	private LayoutInflater inflater;

	public ListForderAdapter(Context context,List<ImageBean> dir) {
		// TODO Auto-generated constructor stub
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.dir = dir;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dir == null ? 0 : dir.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dir == null?null:dir.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		ImageBean mImageBean = dir.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item_forder, null);
			viewHolder.fName = (TextView) convertView
					.findViewById(R.id.tv_fname);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Picasso.with(context).load(new File(mImageBean.getTopImagePath()))
		.resize(200, 200).centerCrop().placeholder(R.drawable.loading_img).
		error(R.drawable.loading_img).into(viewHolder.imageView);
		StringBuffer sb = new StringBuffer();
		sb.append(mImageBean.getFolderName()).append("(").append(Integer.toString(mImageBean.getImageCounts()))
		.append(")");
		viewHolder.fName.setText(sb.toString());
		return convertView;
	}

	class ViewHolder {
		private TextView fName;
		private ImageView imageView;
	}
}
