package com.github.snambi.googleimagesearcher;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoogleImageAdapter extends ArrayAdapter<Image>{

	public GoogleImageAdapter(Context context, List<Image> images) {
		super(context, android.R.layout.simple_list_item_1, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if( convertView == null ){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_photo, parent, false);			
		}
		
		ViewHolder holder = null;
		if( convertView.getTag() == null ){
			holder = new ViewHolder();
			
			holder.imgGridItem = (ImageView) convertView.findViewById(R.id.imgGridItem);
			holder.tvGridItem = (TextView) convertView.findViewById(R.id.tvGridItem);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.imgGridItem.setImageResource(R.drawable.ic_launcher);
		holder.tvGridItem.setText("hello");

		return convertView;
	}
	
	public static class ViewHolder{
		ImageView imgGridItem;
		TextView tvGridItem;
	}
}
