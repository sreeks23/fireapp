package com.ibm.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DecisionActivity extends Activity implements OnClickListener {

	Intent appintent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decision);
		Button b1 = (Button) findViewById(R.id.bempdecision);
		Button b2 = (Button) findViewById(R.id.bincdecision);
		Button b3 = (Button) findViewById(R.id.bincmstrreport);
		Button b4 = (Button) findViewById(R.id.bempsrch);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);



	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bempdecision:
			appintent = new Intent("com.ibm.fireapp.APPS");
			startActivity(appintent);
			break;
		case R.id.bincdecision:
			appintent = new Intent("com.ibm.fireapp.INCAPPS");
			startActivity(appintent);
			break;
		case R.id.bincmstrreport:
			appintent = new Intent("com.ibm.fireapp.INCIDENTMASTERREPORT");
			startActivity(appintent);
			break;
		case R.id.bempsrch:
			appintent = new Intent("com.ibm.fireapp.EMPSEARCH");
			startActivity(appintent);
			break;
		}

	}

}
