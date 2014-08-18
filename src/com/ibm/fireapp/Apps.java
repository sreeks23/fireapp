package com.ibm.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Apps extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apps);
		Button cis = (Button) findViewById(R.id.bcis);
		Button cpi = (Button) findViewById(R.id.bcpi);
		Button claims = (Button) findViewById(R.id.bclaims);
		Button cws = (Button) findViewById(R.id.bcws);
		Button epas = (Button) findViewById(R.id.bepas);
		Button epic = (Button) findViewById(R.id.bepic);
		Button hoact = (Button) findViewById(R.id.bhoact);
		Button piauto = (Button) findViewById(R.id.bpiauto);
		
		cis.setOnClickListener(this);
		cpi.setOnClickListener(this);
		cws.setOnClickListener(this);
		claims.setOnClickListener(this);
		epas.setOnClickListener(this);
		epic.setOnClickListener(this);
		hoact.setOnClickListener(this);
		piauto.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent appintent;
		appintent = new Intent("com.ibm.fireapp.CIS");
		switch (v.getId()) {
		case R.id.bcis :
			appintent = new Intent("com.ibm.fireapp.CIS");
			break;
		case R.id.bclaims :
			appintent = new Intent("com.ibm.fireapp.CLAIMS");
			break;
		case R.id.bcpi :
			appintent = new Intent("com.ibm.fireapp.CPI");
			break;
		case R.id.bcws :
			appintent = new Intent("com.ibm.fireapp.CWS");
			break;
		case R.id.bepas :
			appintent = new Intent("com.ibm.fireapp.EPAS");
			break;
		case R.id.bepic :
			appintent = new Intent("com.ibm.fireapp.EPIC");
			break;
		case R.id.bhoact :
			appintent = new Intent("com.ibm.fireapp.HOACT");
			break;
		case R.id.bpiauto :
			appintent = new Intent("com.ibm.fireapp.PIAUTO");
			break;			
		};
		startActivity(appintent);
	}	

}
