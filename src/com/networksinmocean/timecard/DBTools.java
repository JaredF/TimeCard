package com.networksinmocean.timecard;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {
	
	public DBTools(Context applicationContext) {
		
		super(applicationContext, "punchcard.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		String query1 = "CREATE TABLE timepunches (punchId INTEGER PRIMARY KEY, dateTimeIn TEXT, dateTimeOut TEXT, activity TEXT)";
		String query2 = "CREATE TABLE statuses (statusId INTEGER PRIMARY KEY, punchId INTEGER, status TEXT, timestamp TEXT)";
		String query3 = "CREATE TABLE changelog (changeId INTEGER PRIMARY KEY, punchId INTEGER, newTimeIn TEXT, newTimeOut TEXT, timestamp TEXT)";
		
		database.execSQL(query1);
		database.execSQL(query2);
		database.execSQL(query3);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		String query1 = "DROP TABLE IF EXISTS timepunches";
		String query2 = "DROP TABLE IF EXISTS statuses";
		String query3 = "DROP TABLE IF EXISTS changelog";
		
		database.execSQL(query1);
		database.execSQL(query2);
		database.execSQL(query3);
		
		onCreate(database);
		
	}
	
	public void insertPunch(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("dateTimeIn", queryValues.get("dateTimeIn"));
		values.put("dateTimeOut", queryValues.get("dateTimeOut"));
		values.put("activity", queryValues.get("activity"));
		
		database.insert("timepunches", null, values);
		
		database.close();
		
	}
	
	public void insertStatus(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("punchId", queryValues.get("punchId"));
		values.put("newDateTimeIn", queryValues.get("newDateTimeIn"));
		values.put("newDateTimeOut", queryValues.get("newDateTimeOut"));
		values.put("timestamp", queryValues.get("timestamp"));
		
		database.insert("statuses", null, values);
		
		database.close();
		
	}
	
	public void insertChange(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("punchId", queryValues.get("punchId"));
		values.put("status", queryValues.get("status"));
		values.put("timestamp", queryValues.get("timestamp"));
		
		database.insert("changelog", null, values);
		
		database.close();
		
	}
	
	public ArrayList<HashMap<String, String>> getPunches(){
		
		ArrayList<HashMap<String, String>> punchArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM timepunches ORDER BY punchId";
		
		SQLiteDatabase database = this.getWritableDatabase();
				
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				HashMap<String, String> punchMap = new HashMap<String, String>();
				
				punchMap.put("punchId", cursor.getString(0));
				punchMap.put("dateTimeIn", cursor.getString(1));
				punchMap.put("dateTimeOut", cursor.getString(2));
				punchMap.put("activity", cursor.getString(3));
				
				punchArrayList.add(punchMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return punchArrayList;
		
	}
	
}