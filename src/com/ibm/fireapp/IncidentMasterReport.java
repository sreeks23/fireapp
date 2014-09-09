package com.ibm.fireapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class IncidentMasterReport extends Activity {

	public static final String CLASS_NAME = "IncidentMasterReport";
	TextView hdr2;
	BlueListApplication blApplication;
	List<IncidentList> inclist;
	List<IncidentReportDetails> increpdetlist;
	ListView increpdetLV;
	int l5, b5to30, b30to60, b60to90, g90, inf;
	ProgressDialog progressDialog;

	private IncReportDetailsAdapterNew increpdet_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inc_master_report);

		blApplication = (BlueListApplication) getApplication();
		inclist = blApplication.getIncidentList();
		increpdetlist = blApplication.getIncidentReportDetailsList();
		hdr2 = (TextView) findViewById(R.id.mstrhdg2);
		hdr2.setText("                                      AGE (In Days)                   \n" +
				     "Business Area  <=5   >5<=30    >30<=60   >60<=90    >90   Inflow");

		inclist.clear();
		increpdetlist.clear();
		increpdetLV = (ListView) findViewById(R.id.incmstrlist);
		increpdet_adapter = new IncReportDetailsAdapterNew(increpdetlist, this);
		increpdetLV.setAdapter(increpdet_adapter);
		listItems();

	}

	public void listItems() {

		progressDialog = ProgressDialog.show(IncidentMasterReport.this,
				"Processing", "Please Wait!");
		try {
			Log.d(CLASS_NAME, "Inside List New1");
			IBMQuery<IncidentList> query = IBMQuery
					.queryForClass(IncidentList.class);
			// query.whereKeyEqualsTo("Business_area", "MI");
			query.find().continueWith(
					new Continuation<List<IncidentList>, Void>() {

						@Override
						public Void then(Task<List<IncidentList>> task)
								throws Exception {

							final List<IncidentList> objects = task.getResult();
							// Log.d(CLASS_NAME, objects.toString());
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
								increpdetlist.clear();
								/*
								 * IncidentReportDetails newincrepdet = new
								 * IncidentReportDetails();
								 * newincrepdet.setBusinessArea(" BA/AGE ");
								 * newincrepdet.setBetween30To60(">30<=60");
								 * newincrepdet.setBetween5To30(">5<=30");
								 * newincrepdet.setBetween60To90(">60<=90");
								 * newincrepdet.setGreater90(">90");
								 * newincrepdet.setInflow("New");
								 * newincrepdet.setLessThan5("<=5");
								 * increpdetlist.add(newincrepdet);
								 */
								for (IBMDataObject app : objects) {
									IncidentList axyz = new IncidentList();
									axyz = (IncidentList) app;
									addData(axyz);
									inclist.add((IncidentList) app);
								}
								Log.d(CLASS_NAME, "data changed");
								IncidentReportDetails newincrepdet = new IncidentReportDetails();
								newincrepdet.setBusinessArea("TOTAL   ");
								newincrepdet.setBetween30To60(String
										.valueOf(b30to60));
								newincrepdet.setBetween5To30(String
										.valueOf(b5to30));
								newincrepdet.setBetween60To90(String
										.valueOf(b60to90));
								newincrepdet.setGreater90(String.valueOf(g90));
								newincrepdet.setInflow(String.valueOf(inf));
								newincrepdet.setLessThan5(String.valueOf(l5));
								increpdetlist.add(newincrepdet);
								sortItems(increpdetlist);
								increpdet_adapter.notifyDataSetChanged();
								progressDialog.dismiss();

							}
							return null;
						}
					}, Task.UI_THREAD_EXECUTOR);

		} catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			Log.d(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}

	private void sortItems(List<IncidentReportDetails> theList) {
		// Sort collection by case insensitive alphabetical order.
		Collections.sort(theList, new Comparator<IncidentReportDetails>() {
			public int compare(IncidentReportDetails lhs,
					IncidentReportDetails rhs) {
				String lhsName = lhs.getBusinessArea();
				String rhsName = rhs.getBusinessArea();
				return lhsName.compareToIgnoreCase(rhsName);
			}
		});
	}

	private void addData(IncidentList incdet) {

		int i, count;
		String repba = incdet.getBusinessarea();
		Log.d(CLASS_NAME, "Inside add");

		boolean entryFound = false;
		Log.d(CLASS_NAME, String.valueOf(increpdetlist.size()));
		for (i = 0; i < increpdetlist.size(); i++) {
			IncidentReportDetails newincrepdet = increpdetlist.get(i);
			if (newincrepdet.getBusinessArea().contentEquals(repba)) {
				if (incdet.getLessthan5() != null) {
					if (!incdet.getLessthan5().contentEquals("0")) {
						count = Integer.parseInt(newincrepdet.getLessThan5());
						count++;
						l5++;
						newincrepdet.setLessThan5((String.valueOf(count)));
					}
				}
				if (incdet.getBetween5to30() != null) {
					if (!incdet.getBetween5to30().contentEquals("0")) {
						count = Integer
								.parseInt(newincrepdet.getBetween5To30());
						count++;
						b5to30++;
						newincrepdet.setBetween5To30((String.valueOf(count)));
					}
				}
				if (incdet.getBetween30to60() != null) {
					if (!incdet.getBetween30to60().contentEquals("0")) {
						count = Integer.parseInt(newincrepdet
								.getBetween30To60());
						count++;
						b30to60++;
						newincrepdet.setBetween30To60((String.valueOf(count)));
					}
				}
				if (incdet.getBetween60to90() != null) {
					if (!incdet.getBetween60to90().contentEquals("0")) {
						count = Integer.parseInt(newincrepdet
								.getBetween60To90());
						count++;
						b60to90++;
						newincrepdet.setBetween60To90((String.valueOf(count)));
					}
				}
				if (incdet.getGreater90() != null) {
					if (!incdet.getGreater90().contentEquals("0")) {
						count = Integer.parseInt(newincrepdet.getGreater90());
						count++;
						g90++;
						newincrepdet.setGreater90((String.valueOf(count)));
					}
				}
				if (incdet.getCurrentstatus() != null) {
					if (incdet.getCurrentstatus().contentEquals("New Ticket")) {
						count = Integer.parseInt(newincrepdet.getInflow());
						count++;
						inf++;
						newincrepdet.setInflow((String.valueOf(count)));
					}

				}

				entryFound = true;
			}

		}

		if (entryFound == false) {
			Log.d(CLASS_NAME, "Inside new");
			IncidentReportDetails newincrepdet = new IncidentReportDetails();
			newincrepdet.setBusinessArea(repba);
			newincrepdet.setBetween30To60("0");
			newincrepdet.setBetween5To30("0");
			newincrepdet.setBetween60To90("0");
			newincrepdet.setGreater90("0");
			newincrepdet.setInflow("0");
			newincrepdet.setLessThan5("0");
			if (incdet.getLessthan5() != null) {
				if (!incdet.getLessthan5().contentEquals("0")) {
					count = Integer.parseInt(newincrepdet.getLessThan5());
					count++;
					l5++;
					newincrepdet.setLessThan5((String.valueOf(count)));
				}
			}
			if (incdet.getBetween5to30() != null) {
				if (!incdet.getBetween5to30().contentEquals("0")) {
					count = Integer.parseInt(newincrepdet.getBetween5To30());
					count++;
					b5to30++;
					newincrepdet.setBetween5To30((String.valueOf(count)));
				}
			}
			if (incdet.getBetween30to60() != null) {
				if (!incdet.getBetween30to60().contentEquals("0")) {
					count = Integer.parseInt(newincrepdet.getBetween30To60());
					count++;
					b30to60++;
					newincrepdet.setBetween30To60((String.valueOf(count)));
				}
			}
			if (incdet.getBetween60to90() != null) {
				if (!incdet.getBetween60to90().contentEquals("0")) {
					count = Integer.parseInt(newincrepdet.getBetween60To90());
					count++;
					b60to90++;
					newincrepdet.setBetween60To90((String.valueOf(count)));
				}
			}
			if (incdet.getGreater90() != null) {
				if (!incdet.getGreater90().contentEquals("0")) {
					count = Integer.parseInt(newincrepdet.getGreater90());
					count++;
					g90++;
					newincrepdet.setGreater90((String.valueOf(count)));
				}
			}
			if (incdet.getCurrentstatus() != null) {
				if (incdet.getCurrentstatus().contentEquals("New Ticket")) {
					count = Integer.parseInt(newincrepdet.getInflow());
					count++;
					inf++;
					newincrepdet.setInflow((String.valueOf(count)));
				}

			}

			increpdetlist.add(newincrepdet);
			Log.d(CLASS_NAME, "Inside new1");
		}

	}
}
