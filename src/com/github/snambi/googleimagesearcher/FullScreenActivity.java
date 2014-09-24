package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_full_screen);
		
		Bundle data = getIntent().getExtras();
		Image image = (Image) data.getParcelable("IMAGE");
		
		// load the image and set as resource to the Image view
		
		ImageView imgView = (ImageView) findViewById(R.id.imgFullScreen);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		
		Picasso.with( this)
				.load( image.getUrl() )
				//.resize(size.x, size.y)
				.fit()
				.centerInside()
				.into(imgView);
	}
}
