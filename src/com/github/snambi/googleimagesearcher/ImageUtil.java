package com.github.snambi.googleimagesearcher;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageUtil {
	
	public static Image convertJsonToImage( JSONObject json ) throws JSONException{
		Image image = new Image();
		
		if( json != null ){
			if( !json.isNull("content")){
				image.setContent(json.getString("content"));
			}
			if( !json.isNull("contentNoFormatting")){
				image.setContentNoFormatting(json.getString("contentNoFormatting"));
			}
			if( !json.isNull("url")){
				image.setUrl( json.getString("url"));
			}
			if( !json.isNull("height")){
				image.setHeight(json.getInt("height"));
			}
			if( !json.isNull("imageId")){
				image.setId(json.getString("imageId"));
			}
			if( !json.isNull("tbHeight")){
				image.setThumbHeight(json.getInt("tbHeight"));
			}
			if( !json.isNull("tbUrl")){
				image.setThumbUrl(json.getString("tbUrl"));
			}
			if( !json.isNull("tbWidth")){
				image.setThumbWidth(json.getInt("tbWidth"));
			}
			if( !json.isNull("title")){
				image.setTitle( json.getString("title"));
			}
			if( !json.isNull("titleNoFormatting")){
				image.setTitleNoFormatting(json.getString("titleNoFormatting"));
			}
			if( !json.isNull("unescapedUrl")){
				image.setUnescapedUrl(json.getString("unescapedUrl"));
			}
			if( !json.isNull("visibleUrl")){
				image.setVisibleUrl(json.getString("visibleUrl"));
			}
			if( !json.isNull("width")){
				image.setWidth(json.getInt("width"));
			}
		}
		return image;
	}

}
