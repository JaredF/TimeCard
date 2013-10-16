package com.networksinmocean.timecard;

import java.util.ArrayList;
import java.util.HashMap;
import android.view.View;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Entries extends ListActivity{
	
	Intent intent;
	TextView punchId;
	
	DBTools dbTools = new DBTools(this);
	
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entries);
		ArrayList<HashMap<String, String>> punchList = dbTools.getPunches();
		
		if(punchList.size() != 0){
			
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int arg2, long arg3) {
					
					punchId = (TextView) view.findViewById(R.id.punchId);
					
/*					String punchIdValue = punchId.getText().toString();
					
					Intent theIntent = new Intent(getApplication(), EditEntry.class);
					
					theIntent.putExtra("punchId", punchIdValue);
					
					startActivity(theIntent); */
					
				}
				
		});
			
			ListAdapter adapter = new SimpleAdapter(
					Entries.this, punchList, R.layout.list_entry,
					new String[] {"punchId", "timeIn", "timeOut", "project"},
					new int[] {R.id.punchId, R.id.timeIn, R.id.timeOut, R.id.project});
		
			setListAdapter(adapter);
			
		}
		
	}
	
}