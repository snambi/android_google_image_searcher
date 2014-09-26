package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.snambi.googleimagesearcher.SettingsDialog.SearchDialogFragmentListener;

public class SettingsFragmentActivity extends FragmentActivity implements SearchDialogFragmentListener{

	private EditText etSite=null;
	private Spinner spnColor=null;
	private Spinner spnType=null;
	private Spinner spnSize=null;
	private Button btnSave=null;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_search);
		
		// get a reference to the view objects
		etSite= (EditText)	findViewById(R.id.etDiagSiteFilter);
		spnColor = (Spinner) findViewById(R.id.spnDiagColorFilter);
		spnType = (Spinner) findViewById(R.id.spnDiagImageFilter);
		spnSize = (Spinner) findViewById(R.id.spnDiagImageSize);
		btnSave = (Button) findViewById(R.id.btnDiagSettingsSave);

		showSettingsDialog( this );		
	}

	private void showSettingsDialog( Context context){
		FragmentManager fm = getSupportFragmentManager();
		String color = getIntent().getStringExtra(SettingsActivity.COLOR);
		String type = getIntent().getStringExtra(SettingsActivity.TYPE);
		String size = getIntent().getStringExtra(SettingsActivity.SIZE);
		String site = getIntent().getStringExtra(SettingsActivity.SITE);
		
		SettingsDialog settings = SettingsDialog.newInstance(color, type, size, site );
		settings.show(fm, "fragment");
	}

	@Override
	public void onFinishDialog(String color, String type, String size,String site) {
		Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent();
		
		intent.putExtra( SettingsActivity.COLOR, color);
		intent.putExtra( SettingsActivity.TYPE, type);
		intent.putExtra( SettingsActivity.SIZE, size);
		intent.putExtra( SettingsActivity.SITE, site);
		
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
	
}

