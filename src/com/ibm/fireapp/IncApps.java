package com.ibm.fireapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import com.ibm.fireapp.IncAppsList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class IncApps extends Activity implements OnItemClickListener {

	List<IncAppsList> applist;
	List<String> appnameslist;
	ListView appsLV;
	BlueListApplication blApplication;
	ArrayAdapter<IncAppsList> appArrayAdapter;
	ArrayAdapter<String> appnameadapter;
	int listItemPosition;
	public static final String CLASS_NAME = "IncApps";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inc_apps);

		blApplication = (BlueListApplication) getApplication();
		applist = blApplication.getIncAppsList();
		appnameslist = blApplication.getIncAppNamesList();
		
		appnameslist.clear();
		appsLV = (ListView) findViewById(R.id.incappsList);
		appnameadapter = new ArrayAdapter<String>(this, R.layout.list_item_1,
				appnameslist);
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
			IBMQuery<IncAppsList> query = IBMQuery.queryForClass(IncAppsList.class);
			// Query all the Item objects from the server.
			query.find().continueWith(new Continuation<List<IncAppsList>, Void>() {

				@Override
				public Void then(Task<List<IncAppsList>> task) throws Exception {
					final List<IncAppsList> objects = task.getResult();
					Log.d(CLASS_NAME, "objects" + objects.toString());
					// Log if the find was cancelled.
					if (task.isCancelled()) {
						Log.e(CLASS_NAME, "Exception : Task " + task.toString()
								+ " was cancelled.");
						Log.d(CLASS_NAME, "Exception : Task " + task.toString()
								+ " was cancelled.");
					}
					// Log error message, if the find task fails.
					else if (task.isFaulted()) {
						Log.e(CLASS_NAME, "Exception : "
								+ task.getError().getMessage());
						Log.d(CLASS_NAME, "Exception : "
								+ task.getError().getMessage());
					}

					// If the result succeeds, load the list.
					else {
						// Clear local itemList.
						// We'll be reordering and repopulating from
						// DataService.
						Log.d(CLASS_NAME, "Inside else");
						applist.clear();
						appnameslist.clear();
						for (IBMDataObject app : objects) {
							IncAppsList xyz = new IncAppsList();
							xyz = (IncAppsList) app;
							String appname1 = xyz.getAppname();
							applist.add((IncAppsList) app);
							appnameslist.add((String) appname1);

						}

						sortItems(appnameslist);
						appnameadapter.notifyDataSetChanged();
					}
					return null;
				}
			}, Task.UI_THREAD_EXECUTOR);

		} catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			Log.d(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}


	private void sortItems(List<String> theList) {
		// Sort collection by case insensitive alphabetical order.
		Collections.sort(theList, new Comparator<String>() {
			public int compare(String lhs, String rhs) {
				String lhsName = lhs;
				String rhsName = rhs;
				return lhsName.compareToIgnoreCase(rhsName);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int apppos = position;
		String appselected = (String) appsLV.getItemAtPosition(apppos);
		// Intent appdetailintent = new Intent("com.ibm.fireapp.APPDETAILSNEW");
		Log.d(CLASS_NAME, appselected);
		Intent appdetailintent = new Intent("com.ibm.fireapp.INCIDENTREPORT");
		appdetailintent.putExtra("appname", appselected);
		startActivity(appdetailintent);

	}
}
