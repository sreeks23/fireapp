package com.ibm.fireapp;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ibm.fireapp.BlueListApplication;

public class AppDetails extends Activity {

	TextView appnameTV,sbamtv;
	public static final String CLASS_NAME = "AppDetails";
	BlueListApplication blApplication;
	List<EmpDetails> empdetails;
	ArrayAdapter<EmpDetails> empArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appdetails);

		blApplication = (BlueListApplication) getApplication();
		empdetails = blApplication.getEmpDetails();
		createItem();
		Intent intent = getIntent();
		String appname = intent.getStringExtra("appname");
		appnameTV = (TextView) findViewById(R.id.appnamedetal);
		appnameTV.setText(appname);
		listItems();

	}

	public void listItems() {
		try {
			Log.d(CLASS_NAME, "Inside List");
			IBMQuery<EmpDetails> query = IBMQuery
					.queryForClass(EmpDetails.class);
			query.whereKeyEqualsTo("app_name", "CIS");
			// Query all the Item objects from the server.
			query.find().continueWith(
					new Continuation<List<EmpDetails>, Void>() {

					
						@Override
						public Void then(Task<List<EmpDetails>> task)
								throws Exception {
							Log.d(CLASS_NAME, "Inside Then");
							final List<EmpDetails> objects = task.getResult();
							Log.d(CLASS_NAME, "objects Returned" + objects.toString());

							// Log if the find was cancelled.
							if (task.isCancelled()) {
								Log.e(CLASS_NAME,
										"Exception : Task " + " was cancelled.");
								Log.d(CLASS_NAME,
										"Exception : Task " +  " was cancelled.");
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
								Log.d(CLASS_NAME, "Before for1");
								empdetails.clear();
								Log.d(CLASS_NAME, "Before for");
								for (IBMDataObject app:objects) {
									Log.d(CLASS_NAME, "Inside For");
									EmpDetails xyz = new EmpDetails();
									xyz = (EmpDetails) app;
									String appname1 = xyz.getAppname();
									String empname1 = xyz.getName();
									String role1 = xyz.getRole();
									String skill1 = xyz.getSkill();
									Log.d(CLASS_NAME, "Application Name "
											+ appname1 + empname1 + role1
											+ skill1);
									empdetails.add((EmpDetails) app);

								}

								// sortItems(applist);
								Log.d(CLASS_NAME, "data changed");
								// appArrayAdapter.notifyDataSetChanged();
								empArrayAdapter.notifyDataSetChanged();
							}
							return null;
						}
					}, Task.UI_THREAD_EXECUTOR);

		} catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			Log.d(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}

	public void createItem() {
		Log.d(CLASS_NAME, "Inside Create");
		EmpDetails emp1 = new EmpDetails();
		emp1.setAppName("CIS");
		emp1.setName("Sree2");
		emp1.setRole("BAM");
		emp1.setSkill("Mainframes");
		// Use the IBMDataObject to create and persist the Item object.
		emp1.save().continueWith(new Continuation<IBMDataObject, Void>() {

			@Override
			public Void then(Task<IBMDataObject> task) throws Exception {
				// Log if the save was cancelled.
				if (task.isCancelled()) {
					Log.e(CLASS_NAME, "Exception : Task " + task.toString()
							+ " was cancelled.");
				}
				// Log error message, if the save task fails.
				else if (task.isFaulted()) {
					Log.e(CLASS_NAME, "Exception : "
							+ task.getError().getMessage());
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
}
