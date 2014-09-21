package com.github.snambi.googleimagesearcher;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GoogleImageAdapter extends ArrayAdapter<Image>{

	public GoogleImageAdapter(Context context, List<Image> images) {
		super(context, android.R.layout.simple_list_item_1, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if( convertView == null ){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_photo, parent, false);			
		}
		
		GoogleViewHolder holder = null;
		if( convertView.getTag() == null ){
			holder = new GoogleViewHolder();
			holder.imgGridItem = (ImageView) convertView.findViewById(R.id.imgGridItem);
			holder.tvGridItem = (TextView) convertView.findViewById(R.id.tvGridItem);
		}else{
			holder = (GoogleViewHolder) convertView.getTag();
		}
		
		holder.imgGridItem.getLayoutParams().width = parent.getWidth();
		
		holder.imgGridItem.setImageResource(0);
				
		// get the appropriate image
		Image image = getItem(position);
		
		Log.i("INFO", "Loading Image : " + image.getUrl() );
		
		//Picasso.with( getContext() ).load( photo.getImageUrl() ).fit().centerInside().into(holder.imgPhoto);
		Picasso.with(getContext())
				.load( image.getThumbUrl())
//				.centerInside()
//				.fit()
//				.noFade()
				.into(holder.imgGridItem);
		
		//holder.imgGridItem.setImageResource(R.drawable.ic_launcher);
		holder.tvGridItem.setText( Html.fromHtml(image.getTitle() ) );

		return convertView;
	}
	
	public static class GoogleViewHolder{
		ImageView imgGridItem;
		TextView tvGridItem;
	}
}
