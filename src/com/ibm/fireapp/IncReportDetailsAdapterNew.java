package com.ibm.fireapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IncReportDetailsAdapterNew extends BaseAdapter {

	public static final String CLASS_NAME = "IncBADetailsAdapterNew";
	private Context context;
	private List<IncidentReportDetails> increpdetlist;
	LayoutInflater inflater;

	public IncReportDetailsAdapterNew(List<IncidentReportDetails> incdet, Context c) {
		context = c;
		increpdetlist = incdet;
		inflater = (LayoutInflater) c
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return increpdetlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return increpdetlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		rowView = inflater
				.inflate(R.layout.incident_report_row, parent, false);
		IncidentReportDetails increpdetdetails = increpdetlist.get(position);
		TextView ba = (TextView) rowView.findViewById(R.id.reportba);
		TextView l5 = (TextView) rowView.findViewById(R.id.reportl5);
		TextView g5l30 = (TextView) rowView.findViewById(R.id.reportb5to30);
		TextView g30l60 = (TextView) rowView.findViewById(R.id.reportb30to60);
		TextView g60l90 = (TextView) rowView.findViewById(R.id.reportb60to90);
		TextView g90 = (TextView) rowView.findViewById(R.id.reportg90);
		TextView inf = (TextView) rowView.findViewById(R.id.reportinf);
		

		ba.setText(increpdetdetails.getBusinessArea());
		l5.setText(increpdetdetails.getLessThan5());
		g5l30.setText(increpdetdetails.getBetween5To30());
		g30l60.setText(increpdetdetails.getBetween30To60());
		g60l90.setText(increpdetdetails.getBetween60To90());
		g90.setText(increpdetdetails.getGreater90());
		inf.setText(increpdetdetails.getInflow());
		
		return rowView;
	}

}
