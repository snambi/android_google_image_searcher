package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

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
	}
	
	public void onClick( View view){
		
		String colorFilter = (String) spnColorFilter.getSelectedItem();
		String imageFilter = (String) spnImageFilter.getSelectedItem();
		String imageSize = (String) spnImageSize.getSelectedItem();
		String site = etSite.getText().toString();
		
		Intent intent = new Intent();
		
		intent.putExtra("COLOR_FILTER", colorFilter);
		intent.putExtra("IMAGE_FILTER", imageFilter);
		intent.putExtra("IMAGE_SIZE", imageSize);
		intent.putExtra("SITE", site);
		
		setResult(RESULT_OK, intent);
		finish();
	}
}
