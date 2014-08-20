package com.ibm.fireapp;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.fireapp.AppList;
import com.ibm.fireapp.BlueListApplication;
import com.ibm.fireapp.R;
import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Apps extends Activity implements OnItemClickListener {

	List<AppList> applist;
	List<String> appnameslist;
	ListView appsLV;
	BlueListApplication blApplication;
	ArrayAdapter<AppList> appArrayAdapter;
	ArrayAdapter<String> appnameadapter;
	ActionMode mActionMode = null;
	int listItemPosition;
	public static final String CLASS_NAME="Apps";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apps);
		
		blApplication = (BlueListApplication) getApplication();
		applist = blApplication.getAppList();
		appnameslist = blApplication.getAppNamesList();
		
		//createItem();
		appsLV = (ListView)findViewById(R.id.itemsList);
		appnameadapter = new ArrayAdapter<String>(this, R.layout.list_item_1, appnameslist);
		appsLV.setAdapter(appnameadapter);
		listItems();
		appsLV.setOnItemClickListener(this);
	}


	/**
	 * Refreshes itemList from data service.
	 * 
	 * An IBMQuery is used to find all the list items.
	 */
	public void listItems() {
		try {
			Log.d(CLASS_NAME, "Inside List");
			IBMQuery<AppList> query = IBMQuery.queryForClass(AppList.class);
			// Query all the Item objects from the server.
			query.find().continueWith(new Continuation<List<AppList>, Void>() {

				@Override
				public Void then(Task<List<AppList>> task) throws Exception {
                    final List<AppList> objects = task.getResult();
                  
                     // Log if the find was cancelled.
                    if (task.isCancelled()){
                        Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
                        Log.d(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
                    }
					 // Log error message, if the find task fails.
					else if (task.isFaulted()) {
						Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
						Log.d(CLASS_NAME, "Exception : " + task.getError().getMessage());
					}

					
					 // If the result succeeds, load the list.
					else {
                        // Clear local itemList.
                        // We'll be reordering and repopulating from DataService.
                        applist.clear();
                        appnameslist.clear();
                        for(IBMDataObject app:objects) {
                        	AppList xyz = new AppList();
                        	xyz = (AppList) app;
                        	String appname1 = xyz.getAppname();
                        	Log.d(CLASS_NAME, "Application Name " + appname1 + objects.toString());
                            applist.add((AppList) app);
                            appnameslist.add((String) appname1);
                          
                        }
                        
                       // sortItems(applist);
                        Log.d(CLASS_NAME, "data changed");
                    //    appArrayAdapter.notifyDataSetChanged();
                        appnameadapter.notifyDataSetChanged();
					}
					return null;
				}
			},Task.UI_THREAD_EXECUTOR);
			
		}  catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			Log.d(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}
	
	public void createItem() {
		Log.d(CLASS_NAME, "Inside Create");
		AppList app1 = new AppList();
			app1.setAppID("11");
			app1.setAppName("CIS");					
			// Use the IBMDataObject to create and persist the Item object.
			app1.save().continueWith(new Continuation<IBMDataObject, Void>() {

				@Override
				public Void then(Task<IBMDataObject> task) throws Exception {
                    // Log if the save was cancelled.
                    if (task.isCancelled()){
                        Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
                    }
					 // Log error message, if the save task fails.
					else if (task.isFaulted()) {
						Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
					}

					 // If the result succeeds, load the list.
					else {
						listItems();
					}
					return null;
				}

			});
			
			// Set text field back to empty after item is added.
		}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int apppos = position;
		String cisact = (String) appsLV.getItemAtPosition(apppos);
		if (cisact.equals("CIS")) {
			Intent cisratingintent = new Intent("com.ibm.fireapp.CIS");
			startActivity(cisratingintent);
		}
		
	}
	
}
