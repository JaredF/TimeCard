package com.networksinmocean.timecard;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Add extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_entry);
			
			// This hides the top status bar since it is not necessary if the app is running.
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	}
	
}