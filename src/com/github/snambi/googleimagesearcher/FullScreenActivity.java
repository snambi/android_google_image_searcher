package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class FullScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_full_screen);
	}
}
