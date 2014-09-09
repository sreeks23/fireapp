package com.ibm.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Home extends Activity implements OnClickListener {

	String des1 = "Fireman’s Fund was founded in San Francisco in 1863 by retired sea"
			+ " captain William Holdredge. He started an insurance company to protect the "
			+ "homes and businesses of San Francisco, a town that was booming due to the Gold Rush.\n"
			+ "          Our name comes from Holdredge’s pledge to donate 10% of company profits to the"
			+ " widows and children of fallen San Francisco firefighters.\n"
			+ "          Caring. Courageous. Dependable. Inspired. These are our company values, modeled on the"
			+ " honorable attributes of firefighters. They are principles we look to demonstrate every"
			+ " day, in every moment. In moments when we help to rebuild after a disaster, moments "
			+ "we’re there to answer a policyholder’s question, moments when we partner with agents"
			+ " and brokers to design an innovative solution.\n"
			+ "We celebrated our sesquicentennial in 2013. Looking back at our milestones inspires us"
			+ " to keep innovating into the future.\n"
			+ "We continue to honor our philanthropic roots by funding equipment and training for fire"
			+ " service organizations nationwide.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		TextView view = (TextView) findViewById(R.id.para1);
		Button b1 = (Button) findViewById(R.id.benter);
		b1.setOnClickListener(this);
		// view.setMovementMethod(new ScrollingMovementMethod());
		view.setText(des1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent appintent = new Intent("com.ibm.fireapp.DECISIONACTIVITY");
		startActivity(appintent);

	}

}
