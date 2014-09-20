package com.github.snambi.googleimagesearcher;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class AndroidUtils {

	public static void hideSoftKeyboard( Activity activity ){
		InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
}
