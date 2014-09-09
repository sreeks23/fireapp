package com.ibm.fireapp;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EmpDetailsAdapterNew extends BaseAdapter implements
		OnClickListener {

	public static final String CLASS_NAME = "EmpDetailsNew";
	private Context context;
	private List<EmpDetails> empdetailslist;
	LayoutInflater inflater;

	public EmpDetailsAdapterNew(List<EmpDetails> empdet, Context c) {
		context = c;
		empdetailslist = empdet;
		inflater = (LayoutInflater) c
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return empdetailslist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return empdetailslist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		rowView = inflater.inflate(R.layout.emp_list_1, parent, false);
		EmpDetails empdetails = empdetailslist.get(position);
		TextView empname = (TextView) rowView.findViewById(R.id.emp1);
		TextView empskill = (TextView) rowView.findViewById(R.id.role1);
		TextView empphone = (TextView) rowView.findViewById(R.id.phone1);

		empname.setText(empdetails.getName());
		empskill.setText(empdetails.getSkill());
		empphone.setText(empdetails.getPhone());
		empphone.setHighlightColor(Color.BLUE);
		empname.setContentDescription(empname.getText().toString());
		empskill.setContentDescription(empskill.getText().toString());
		empphone.setContentDescription(empphone.getText().toString());
		empname.setOnClickListener(this);
		empskill.setOnClickListener(this);
		empphone.setOnClickListener(this);

		return rowView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.emp1:
			String des1 = v.getContentDescription().toString();
			Toast.makeText(context, des1, 5000).show();
			break;
		case R.id.role1:
			String des2 = v.getContentDescription().toString();
			Toast.makeText(context, des2, 5000).show();
			break;
		case R.id.phone1:
			String des3 = v.getContentDescription().toString();
		//	Toast.makeText(context, des3, 5000).show();
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:" + des3));
			context.startActivity(intent);
			break;
		default:
			break;
		}

	}

}
