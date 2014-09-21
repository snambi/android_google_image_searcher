package com.github.snambi.googleimagesearcher;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class GoogleImageClient {
	
	public static final String REST_API ="https://ajax.googleapis.com/ajax/services/search/images?v=1.0";
	
	// Image Adapter
	private GoogleImageAdapter imageAdapter = null;

	// result of the query
	private List<Image> images = null;
	
	// mandatory field
	private String queryString ="tigers";
	
	// optional fields
	private String color=null;
	private String type=null;
	private String size=null;
	private String site=null;
	
	
	// store pagination details
	private int currentPage = 0;
	
	public GoogleImageClient( GoogleImageAdapter imageAdapter, List<Image> images ){
		this.imageAdapter = imageAdapter;
		this.images = images;
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
	public GoogleImageAdapter getImageAdapter(){
		return imageAdapter;
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
		
		String restUrl = REST_API + "&q=" + queryString + "&start=" + currentPage;
		 
		httpClient.get( restUrl , new JsonHttpResponseHandler(){

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				
				// parse the results
				if( response != null && response.has("responseData") ){
					
					try {
						
						JSONObject responseDataJson = response.getJSONObject("responseData");
					
						if( responseDataJson.has("results")){
							JSONArray resultsJSON = responseDataJson.getJSONArray("results");
							
							// parse and create image object
							for( int i=0; i<resultsJSON.length() ; i++ ){
								JSONObject resultJson = resultsJSON.getJSONObject(i);
								Image image = ImageUtil.convertJsonToImage(resultJson);
								getImages().add(image);
							}
							
							// increment the page 
							currentPage = currentPage + 4;
							
							// notify the Adapter
							getImageAdapter().notifyDataSetChanged();
							
							// if less images are available, load again.
							if( getImages().size() <= 15 ){
								loadImages();
							}
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
						Log.e("ERROR", "Json Error while parsing goodle output", e);
					}
				}
			}
			
		});
	}

	// clear any pre-loaded images and state information
	public void clear() {
		images.clear();
		currentPage = 0;
	}
}
