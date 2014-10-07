package apps.chomars.pengeluaranmu;


import java.util.ArrayList;


import apps.chomars.pengeluaranmu.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListRekap extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] spendingArr;
	private final String[] inputDateArr;
	private final String[] typeArr;
	private final String[] incomeArr;
	private final String[] balanceArr;
//	private ArrayList<MyData> spendingList;
	public CustomListRekap(Activity context,
			String[] spendingArr, String[] inputDateArr,String[] typeArr, String[] incomeArr,String[] balanceArr) {
			super(context, R.layout.detail_rekap_list, spendingArr);
		
		this.context = context;
		this.spendingArr = spendingArr;
		this.inputDateArr = inputDateArr;
		this.typeArr = typeArr;
		this.incomeArr = incomeArr;
		this.balanceArr = balanceArr;
	}
	 
//	public CustomList(Activity context,ArrayList<MyData> spendingList) {
//		MyData temp = new MyData();
//		 
//		 	this.context = context;
//			this.spendingArr = spendingList.spending;
//			this.descriptionArr = spendingList.description;
//			this.inputDateArr = spendingList.inputDate;
//			this.typeArr = spendingList.dataType;
//	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.detail_rekap_list, null, true);
	TextView txtspending = (TextView) rowView.findViewById(R.id.spendingRekap);
	TextView txtincome = (TextView) rowView.findViewById(R.id.incomeRekap);
	TextView txtinputdate = (TextView) rowView.findViewById(R.id.inputDateRekap);
	TextView txtbalance = (TextView) rowView.findViewById(R.id.balanceRekap);
	//TextView txttype= (TextView) rowView.findViewById(R.id.type);
	txtspending.setText(spendingArr[position]);
	txtincome.setText(incomeArr[position]);
	txtinputdate.setText(inputDateArr[position]);
	txtbalance.setText(balanceArr[position]);
	
	return rowView;
	}
	
}
