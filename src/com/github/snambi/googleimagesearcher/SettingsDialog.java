package com.github.snambi.googleimagesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SettingsDialog extends DialogFragment implements OnEditorActionListener{
	
	private EditText etSite=null;
	private Spinner spnColor=null;
	private Spinner spnType=null;
	private Spinner spnSize=null;
	private Button btnSave=null;
	
	public SettingsDialog(){
	}

	public static SettingsDialog newInstance( String color, String type, String size, String site){
		SettingsDialog frag = new SettingsDialog();
		Bundle args = new Bundle();
		
		args.putString( SettingsActivity.COLOR, color);
		args.putString( SettingsActivity.TYPE, type);
		args.putString( SettingsActivity.SIZE, size);
		args.putString( SettingsActivity.SITE, site);
		
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
								@Nullable ViewGroup container, 
								@Nullable Bundle bundle) {
		
		View view = inflater.inflate(R.layout.fragment_settings, container);
		
		// get a reference to the view objects
		etSite= (EditText)	view.findViewById(R.id.etDiagSiteFilter);
		spnColor = (Spinner) view.findViewById(R.id.spnDiagColorFilter);
		spnType = (Spinner) view.findViewById(R.id.spnDiagImageFilter);
		spnSize = (Spinner) view.findViewById(R.id.spnDiagImageSize);
		btnSave = (Button) view.findViewById(R.id.btnDiagSettingsSave);
		
		// get the inputs from "Search" screen
		String color =getArguments().getString( SettingsActivity.COLOR);
		String type =getArguments().getString( SettingsActivity.TYPE);
		String size =getArguments().getString( SettingsActivity.SIZE);
		String site =getArguments().getString( SettingsActivity.SITE);
		
		//create Adapter
		ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource( view.getContext(), R.array.color_values, R.layout.spinner_text_view);
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnColor.setAdapter(colorAdapter);
		
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource( view.getContext(), R.array.image_types, R.layout.spinner_text_view);
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnType.setAdapter(typeAdapter);
		
		ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource( view.getContext(), R.array.image_sizes, R.layout.spinner_text_view);
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnSize.setAdapter(sizeAdapter);
		
		// select the items using the static method
		SettingsActivity.setSelectedItem(color, spnColor);
		SettingsActivity.setSelectedItem(type, spnType);
		SettingsActivity.setSelectedItem(size, spnSize);
		
		if( site != null && !site.trim().equals("")){
			etSite.setText(site);
		}
		
		//btnSave.setOnClickListener( new SettingOnClickListener(this));
		
		btnSave.setOnEditorActionListener(this);
		
		getDialog().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		return view;
	}
	
	public void finishActivity( View view){
		String colorFilter = (String) spnColor.getSelectedItem();
		String imageFilter = (String) spnType.getSelectedItem();
		String imageSize = (String) spnSize.getSelectedItem();
		String site = etSite.getText().toString();
		
		Intent intent = new Intent();
		
		intent.putExtra( SettingsActivity.COLOR, colorFilter);
		intent.putExtra( SettingsActivity.TYPE, imageFilter);
		intent.putExtra( SettingsActivity.SIZE, imageSize);
		intent.putExtra( SettingsActivity.SITE, site);
		
//		setResult(Activity.RESULT_OK, intent);
//		finish();

	}
	
	public interface SearchDialogFragmentListener{
		void onFinishDialog( String color, String type, String size, String site);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if( EditorInfo.IME_ACTION_DONE == actionId ){
			SearchDialogFragmentListener listener = (SearchDialogFragmentListener) getActivity();
			
			String color = (String) spnColor.getSelectedItem();
			String type = (String) spnType.getSelectedItem();
			String size = (String) spnSize.getSelectedItem();
			String site = etSite.getText().toString();

			listener.onFinishDialog(color, type, size, site);
		}
		return false;
	}
}
