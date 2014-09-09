package com.ibm.fireapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import com.ibm.fireapp.IncApps;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ibm.fireapp.IncidentList;
import com.ibm.fireapp.IncBADetailsAdapterNew;
import com.ibm.fireapp.IncidentBAReport;

public class IncidentReport extends Activity {

	TextView appnameTV;
	public static final String CLASS_NAME = "IncidentReport";
	BlueListApplication blApplication;
	List<IncidentList> inclist;
	List<IncidentBAReport> incbalist;
	ListView incbaLV;

	private IncBADetailsAdapterNew incba_adapter;
	String appname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ba_incidents);

		blApplication = (BlueListApplication) getApplication();
		inclist = blApplication.getIncidentList();
		incbalist = blApplication.getIncidentBAList();
		Intent intent = getIntent();
		appname = intent.getStringExtra("appname");
		appnameTV = (TextView) findViewById(R.id.baname);
		appnameTV.setText("Business Area :" + appname);

		inclist.clear();
		incbalist.clear();
		incbaLV = (ListView) findViewById(R.id.incbalist);
		incba_adapter = new IncBADetailsAdapterNew(incbalist, this);
		incbaLV.setAdapter(incba_adapter);
		listItems();

	}

	public void listItems() {
		try {
			Log.d(CLASS_NAME, "Inside List New1");
			Log.d(CLASS_NAME, appname);
			IBMQuery<IncidentList> query = IBMQuery
					.queryForClass(IncidentList.class);
			query.whereKeyEqualsTo("Business_area", appname);
			// query.whereKeyEqualsTo("Incident_No", "INC0012253");
			query.find().continueWith(
					new Continuation<List<IncidentList>, Void>() {

						@Override
						public Void then(Task<List<IncidentList>> task)
								throws Exception {

							final List<IncidentList> objects = task.getResult();
							// Log.d(CLASS_NAME, objects.toString());
							Log.d(CLASS_NAME, appname);
							if (task.isCancelled()) {
								Log.e(CLASS_NAME, "Exception : Task "
										+ " was cancelled.");
								Log.d(CLASS_NAME, "Exception : Task "
										+ " was cancelled.");
							} else if (task.isFaulted()) {
								Log.e(CLASS_NAME, "Exception : "
										+ task.getError().getMessage());
								Log.d(CLASS_NAME, "Exception : "
										+ task.getError().getMessage());
							} else {
								Log.d(CLASS_NAME, "Inside else");
								inclist.clear();
								incbalist.clear();
								for (IBMDataObject app : objects) {
									IncidentList axyz = new IncidentList();
									axyz = (IncidentList) app;
									IncidentBAReport incbareport = new IncidentBAReport();
									incbareport.setIncno(axyz.getIncidentno());
									incbareport.setIncDesc(axyz
											.getDescription());
									incbareport.setIncCS(axyz.getStatus());
									incbareport.setIncPri(axyz.getPriority());
									incbareport.setIncAsg(axyz.getAssigneegroup());
									incbareport.setIncAge(axyz.getAge());
									incbareport.setIncRDate(axyz.getReporteddate());
									Log.d(CLASS_NAME,
											axyz.getIncidentno()
													+ axyz.getStatus()
													+ axyz.getCurrentstatus());

									if (axyz.getCurrentstatus() != null) {
										if (((axyz.getStatus()
												.contentEquals("Closed")) && (!axyz
												.getCurrentstatus()
												.contentEquals(
														"Transferred to FFIC")))
												|| (axyz.getCurrentstatus()
														.contentEquals("Transferred to FFIC"))) {
											continue;
										}
									} else {
										if (axyz.getStatus().contentEquals(
												"Closed")) {
											continue;
										}
									}

									// Log.d(CLASS_NAME, "Null Check");
									inclist.add((IncidentList) app);
									incbalist.add(incbareport);

								}
								Log.d(CLASS_NAME, "data changed");
								sortItems(incbalist);
								IncidentBAReport incbareport = new IncidentBAReport();
								incbareport.setIncno("End of the Report");
								incbareport.setIncDesc("");
								incbareport.setIncCS("");
								incbareport.setIncPri("");
								incbareport.setIncAsg("");
								incbareport.setIncAge("");
								incbareport.setIncRDate("");
								incbalist.add(incbareport);
								incba_adapter.notifyDataSetChanged();
							}
							return null;
						}
					}, Task.UI_THREAD_EXECUTOR);

		} catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			Log.d(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}

	private void sortItems(List<IncidentBAReport> theList) {
		// Sort collection by case insensitive alphabetical order.
		Collections.sort(theList, new Comparator<IncidentBAReport>() {
			public int compare(IncidentBAReport lhs, IncidentBAReport rhs) {
				String lhsName = lhs.getIncAsg();
				String rhsName = rhs.getIncAsg();
				return lhsName.compareToIgnoreCase(rhsName);
			}
		});
	}

}
