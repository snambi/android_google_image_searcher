package com.github.snambi.googleimagesearcher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	EditText etSearch = null;
	Button btnSearch = null;
	GridView gvImages = null;
	List<Image> images = new ArrayList<Image>();
	GoogleImageAdapter imageAdapter = null;
	GoogleImageClient imageClient = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        // Assign the references of UI components during startup
        etSearch = (EditText) findViewById(R.id.etImgSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        gvImages = (GridView) findViewById(R.id.gvImages);
        
        // create an array adaptor for retrieving images from google and attach it to the "grid view"
        imageAdapter = new GoogleImageAdapter(this, images);
        imageClient = new GoogleImageClient( imageAdapter, images);
        
        gvImages.setAdapter(imageAdapter);
        gvImages.setOnScrollListener( new EndlessScrollListener(){

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				Log.i("INFO", "load the next page. page=" + page + ", totalItems=" + totalItemsCount);	
				imageClient.loadImages();
			}
        	
        } );
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.settings_menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    
    	boolean result = false;
    	
    	switch( item.getItemId() ){
    	case R.id.item1 :
    		Intent intent = new Intent( this, SettingsActivity.class);
    		startActivityForResult(intent, 100);
    		result =true;
    		break;
    	default:
    		break;
    	}
    	
    	return result;
    }
    
    public void onClick( View view){
    	
    	// remove focus on the EditText
    	if( etSearch.hasFocus() ){
    		AndroidUtils.hideSoftKeyboard(this);
    	}
    	
    	Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    	
    	imageClient.clear();
    	
    	// get the search term
    	String search = etSearch.getText().toString();
    	if( search != null && !search.trim().equals("") ){
    		imageClient.setQueryString(search.trim());
    	}else{
    		imageClient.setQueryString("tigers");
    	}

    	// makes call to google, creates the list, notifies the adapter
    	imageClient.loadImages();
    }
}
