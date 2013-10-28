package com.networksinmocean.timecard;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Preferences extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// This hides the top status bar since it is not necessary if the app is running.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      	WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content,
		new PrefsFragment()).commit();
	 
	}

}