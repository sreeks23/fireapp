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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EmpSearch extends Activity implements OnClickListener {

	EditText empnametosearchET;
	String srchemp;
	public static final String CLASS_NAME = "EmpSearch";
	BlueListApplication blApplication;
	List<EmpDetails> empdetails;
	ArrayAdapter<EmpDetails> empArrayAdapter;
	List<String> empnamesL;
	ArrayAdapter<String> empnameArrayAdapter;
	ListView empLV;
	private EmpDetailsAdapterNew emp_adapter;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	//	requestWindowFeature(Window.FEATURE_PROGRESS);
	//	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.activity_emp_serch);
	//	setProgressBarIndeterminate(true);
	//	setProgressBarVisibility(true);

		blApplication = (BlueListApplication) getApplication();
		empdetails = blApplication.getEmpDetails();
		empnamesL = blApplication.getEmpNames();
		empnametosearchET = (EditText) findViewById(R.id.empsrchtxt1);
		srchemp = empnametosearchET.getText().toString();

		Button b1 = (Button) findViewById(R.id.bempsrch1);
		b1.setOnClickListener(this);

		empnamesL.clear();
		empdetails.clear();
		empLV = (ListView) findViewById(R.id.empsrchlist);
		emp_adapter = new EmpDetailsAdapterNew(empdetails, this);
		empLV.setAdapter(emp_adapter);
		// listItems();

	}

	public void listItems() {
		try {
			Log.d(CLASS_NAME, "Inside List New");
			IBMQuery<EmpDetails> query = IBMQuery
					.queryForClass(EmpDetails.class);
			query.find().continueWith(
					new Continuation<List<EmpDetails>, Void>() {

						@Override
						public Void then(Task<List<EmpDetails>> task)
								throws Exception {

							final List<EmpDetails> objects = task.getResult();
							// Log.d(CLASS_NAME, objects.toString());

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
								Log.d(CLASS_NAME, "Inside else" + srchemp);
								empdetails.clear();
								empnamesL.clear();
								if (!srchemp.contentEquals("")) {
									for (IBMDataObject app : objects) {
										EmpDetails axyz = new EmpDetails();
										axyz = (EmpDetails) app;
										String appname1 = axyz.getAppname();
										String empname1 = axyz.getName();
										String skill1 = axyz.getSkill();
										String phone = axyz.getPhone();
										if (contains(empname1, srchemp)) {
											empdetails.add((EmpDetails) app);
										}
									}

								}
								Log.d(CLASS_NAME, "data changed");
								sortItems(empdetails);
								emp_adapter.notifyDataSetChanged();
							//	setProgressBarVisibility(false);
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

	private void sortItems(List<EmpDetails> theList) {
		// Sort collection by case insensitive alphabetical order.
		Collections.sort(theList, new Comparator<EmpDetails>() {
			public int compare(EmpDetails lhs, EmpDetails rhs) {
				String lhsName = lhs.getName();
				String rhsName = rhs.getName();
				return lhsName.compareToIgnoreCase(rhsName);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		srchemp = empnametosearchET.getText().toString();
		if (!srchemp.contentEquals("")) {
		//	setProgressBarIndeterminate(true);
		//	setProgressBarVisibility(true);
			progressDialog = ProgressDialog.show(EmpSearch.this,
		              "Searching", "Please Wait!");
			listItems();

		}

	}

	public boolean contains(String str1, String str2) {
		return str1.toLowerCase().contains(str2.toLowerCase());
	}
}
