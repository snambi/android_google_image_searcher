package com.github.snambi.googleimagesearcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class GoogleImageClient {
	
	public static final String REST_API ="https://ajax.googleapis.com/ajax/services/search/images?v=1.0";
	
	// Image Adapter
	private GoogleImageAdapter imageAdapter = null;
	
	// mandatory field
	private String queryString ="tigers";
	
	// optional fields
	private String color=null;
	private String type=null;
	private String size=null;
	private String site=null;
	
	// result of the query
	private List<Image> images = new ArrayList<Image>();
	
	// store pagination details
	
	public GoogleImageClient( GoogleImageAdapter imageAdapter ){
		this.imageAdapter = imageAdapter;
	}
	
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public List<Image> getImages(){
		return images;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	// make an API call and load images from the rest API
	public void loadImages() {
		
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		String restUrl = REST_API + "&q=" + queryString;
		 
		httpClient.get( restUrl , new JsonHttpResponseHandler(){

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// parse the results
				
				// create image object
				
				// notify the Adapter
				imageAdapter.notifyDataSetChanged();
			}
			
		});
	}

	// clear any pre-loaded images and state information
	public void clear() {
		queryString = null;
		images.clear();
	}
}
