package com.ibm.fireapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.ibm.fireapp.BlueListApplication;
import com.ibm.fireapp.EmpDetails;
import com.ibm.fireapp.R;

public class AppDetailsNew extends Activity {

	TextView appnameTV;
	// sbamtv, bamtv, dmtv, pmtv;
	public static final String CLASS_NAME = "AppDetailsNew";
	BlueListApplication blApplication;
	List<EmpDetails> empdetails;
	ArrayAdapter<EmpDetails> empArrayAdapter;
	List<String> empnamesL;
	ArrayAdapter<String> empnameArrayAdapter;
	ListView empLV;
	private EmpDetailsAdapterNew emp_adapter;
	String appname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appdetails);

		blApplication = (BlueListApplication) getApplication();
		empdetails = blApplication.getEmpDetails();
		empnamesL = blApplication.getEmpNames();
		//createItem();
		Intent intent = getIntent();
		appname = intent.getStringExtra("appname");
		appnameTV = (TextView) findViewById(R.id.appnamedetal);
		appnameTV.setText("Business Area :" + appname);
		/*
		 * sbamtv = (TextView) findViewById(R.id.sbam);
		 * sbamtv.setText("Senior BAM :" + sbam); bamtv = (TextView)
		 * findViewById(R.id.bam); bamtv.setText("BAM :" + bam); dmtv =
		 * (TextView) findViewById(R.id.dm); dmtv.setText("Delivery Manager :" +
		 * dm); pmtv = (TextView) findViewById(R.id.pm);
		 * pmtv.setText("Project Manager:" + pm);
		 */
		empnamesL.clear();
		empdetails.clear();
		empLV = (ListView) findViewById(R.id.emplist);
		emp_adapter = new EmpDetailsAdapterNew(empdetails,this);
		empLV.setAdapter(emp_adapter);
		listItems();

	}

	public void listItems() {
		try {
			Log.d(CLASS_NAME, "Inside List New");
			IBMQuery<EmpDetails> query = IBMQuery
					.queryForClass(EmpDetails.class);
			query.whereKeyEqualsTo("app_name", appname);
			// Query all the Item objects from the server.
			query.find().continueWith(
					new Continuation<List<EmpDetails>, Void>() {

						@Override
						public Void then(Task<List<EmpDetails>> task)
								throws Exception {
							
							final List<EmpDetails> objects = task.getResult();
							Log.d(CLASS_NAME, objects.toString());
							Log.d(CLASS_NAME, appname);

							// Log if the find was cancelled.
							if (task.isCancelled()) {
								Log.e(CLASS_NAME, "Exception : Task "
										+ " was cancelled.");
								Log.d(CLASS_NAME, "Exception : Task "
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
								Log.d(CLASS_NAME, "Inside else");
								empdetails.clear();
								empnamesL.clear();
								for (IBMDataObject app : objects) {
									EmpDetails axyz = new EmpDetails();
									axyz = (EmpDetails) app;
									String appname1 = axyz.getAppname();
									String empname1 = axyz.getName();
								//	String role1 = axyz.getRole();
									String skill1 = axyz.getSkill();
									String phone = axyz.getPhone();
									Log.d(CLASS_NAME, "Application Name "+ appname1 + empname1 + skill1);
									empdetails.add((EmpDetails) app);
								//	empnamesL.add(empname1 + " - " + role1 + " - " + phone);
								}
								Log.d(CLASS_NAME, "data changed");
								// appArrayAdapter.notifyDataSetChanged();
								//empArrayAdapter.notifyDataSetChanged();
								//empnameArrayAdapter.notifyDataSetChanged();
								sortItems(empdetails);
								emp_adapter.notifyDataSetChanged();
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
		emp1.setAppName("EPIC");
		emp1.setName("Sree7");
	//	emp1.setRole("PM");
		emp1.setSkill("Mainframes");
		emp1.setPhone("+1-415-246-9753");
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

	private void sortItems(List<EmpDetails> theList) {
		// Sort collection by case insensitive alphabetical order.
		Collections.sort(theList, new Comparator<EmpDetails>() {
			public int compare(EmpDetails lhs,
					EmpDetails rhs) {
				String lhsName = lhs.getName();
				String rhsName = rhs.getName();
				return lhsName.compareToIgnoreCase(rhsName);
			}
		});
	}
}
