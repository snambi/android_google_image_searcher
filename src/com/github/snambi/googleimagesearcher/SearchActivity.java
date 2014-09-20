package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	EditText etSearch = null;
	Button btnSearch = null;
	GridView gvImages = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        // Assign the references of UI components during startup
        etSearch = (EditText) findViewById(R.id.etImgSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        gvImages = (GridView) findViewById(R.id.gvImages);
    }
    
    public void onClick( View view){
    	
    	// remove focus on the EditText
    	if( etSearch.hasFocus() ){
    		AndroidUtils.hideSoftKeyboard(this);
    	}
    	
    	Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}
