package com.ibm.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AppCIS extends Activity implements OnItemClickListener {
	ListView cislv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cis);
		cislv = (ListView) findViewById(R.id.cislv1);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Rating", "Issue", "Errors" };

		ArrayAdapter<String> cisadapter = new ArrayAdapter<String>(this,
				R.layout.simplerow, values);

		cislv.setAdapter(cisadapter);
		cislv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int cisactpos = position;
		String cisact = (String) cislv.getItemAtPosition(cisactpos);
		if (cisact.equals("Rating")) {
			Intent cisratingintent = new Intent("com.ibm.fireapp.CISRATING");
			startActivity(cisratingintent);
		}
		
		if (cisact.equals("Issue")) {
			Intent cisratingintent = new Intent("com.ibm.fireapp.CISISSUE");
			startActivity(cisratingintent);
		}
		if (cisact.equals("Errors")) {
			Intent cisratingintent = new Intent("com.ibm.fireapp.CISERRORS");
			startActivity(cisratingintent);
		}
	}

}
