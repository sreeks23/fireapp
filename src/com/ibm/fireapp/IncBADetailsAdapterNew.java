package com.ibm.fireapp;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.fireapp.R;

;

public class IncBADetailsAdapterNew extends BaseAdapter {

	public static final String CLASS_NAME = "IncBADetailsAdapterNew";
	private Context context;
	private List<IncidentBAReport> incbalist;
	LayoutInflater inflater;

	public IncBADetailsAdapterNew(List<IncidentBAReport> incdet, Context c) {
		context = c;
		incbalist = incdet;
		inflater = (LayoutInflater) c
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return incbalist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return incbalist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		rowView = inflater
				.inflate(R.layout.incident_details_row, parent, false);
		IncidentBAReport incbadetails = incbalist.get(position);
		TextView incno = (TextView) rowView.findViewById(R.id.incno);
		TextView incdesc = (TextView) rowView.findViewById(R.id.incdesc);
		TextView inccs = (TextView) rowView.findViewById(R.id.inccs);

		if (!incbadetails.getIncno().contentEquals("End of the Report")) {
			incno.setText("   " + incbadetails.getIncno() + " / P"
					+ incbadetails.getIncPri() + " / "
					+ incbadetails.getIncAsg());
			incdesc.setText("   Reported on: " + incbadetails.getIncRDate()
					+ " / " + incbadetails.getIncAge() + " Days Old");
			inccs.setText("   " + incbadetails.getIncCS());

		} else {
			incno.setText("   " + incbadetails.getIncno());
			incdesc.setText("");
			inccs.setText("");
			incno.setBackgroundColor(0xff00ff00);
		}

		Log.d(CLASS_NAME, incbadetails.getIncPri());
		if (incbadetails.getIncPri().contentEquals("2")
				|| incbadetails.getIncPri().contentEquals("1")) {
			Log.d(CLASS_NAME, "inside color");
			incno.setBackgroundColor(0xffff0000);
			incdesc.setBackgroundColor(0xffff0000);
			inccs.setBackgroundColor(0xffff0000);

		}

		return rowView;
	}

}
