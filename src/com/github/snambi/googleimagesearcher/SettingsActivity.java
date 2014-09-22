package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SettingsActivity extends Activity {
	
	public static final String COLOR="COLOR";
	public static final String TYPE="TYPE";
	public static final String SIZE="SIZE";
	public static final String SITE="SITE";

	private EditText etSite=null;
	private Spinner spnColorFilter=null;
	private Spinner spnImageFilter=null;
	private Spinner spnImageSize=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		spnColorFilter = (Spinner) findViewById(R.id.spnColorFilter);
		spnImageFilter = (Spinner) findViewById(R.id.spnImageFilter);
		spnImageSize = (Spinner) findViewById(R.id.spnImageSize);
		etSite = (EditText) findViewById(R.id.etSiteFilter);
		
		String[] colors = getResources().getStringArray(R.array.color_values);
		
		// get the inputs from "Search" screen
		String color = getIntent().getStringExtra(COLOR);
		String type = getIntent().getStringExtra(TYPE);
		String size = getIntent().getStringExtra(SIZE);
		String site = getIntent().getStringExtra(SITE);
		
		setSelectedItem(color, spnColorFilter);
		setSelectedItem(type, spnImageFilter);
		setSelectedItem(size, spnImageSize);
		
		if( site != null && !site.trim().equals("")){
			etSite.setText(site);
		}
	}
	
	private void setSelectedItem( String value, Spinner spinner){
		if( value != null && !value.trim().equals("") ){
			for( int i=0; i<spinner.getCount() ; i++ ){
				String item = (String) spinner.getItemAtPosition(i);
				if( item.equals(value)){
					spinner.setSelection(i);
					break;
				}
			}
		}
	}
	
	public void onClick( View view){
		
		String colorFilter = (String) spnColorFilter.getSelectedItem();
		String imageFilter = (String) spnImageFilter.getSelectedItem();
		String imageSize = (String) spnImageSize.getSelectedItem();
		String site = etSite.getText().toString();
		
		Intent intent = new Intent();
		
		intent.putExtra( COLOR, colorFilter);
		intent.putExtra( TYPE, imageFilter);
		intent.putExtra( SIZE, imageSize);
		intent.putExtra( SITE, site);
		
		setResult(RESULT_OK, intent);
		finish();
	}
}
