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

public class CustomList extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] spendingArr;
	private final String[] descriptionArr;
	private final String[] inputDateArr;
	private final String[] typeArr;
//	private ArrayList<MyData> spendingList;
	public CustomList(Activity context,
			String[] spendingArr, String[] descriptionArr,String[] inputDateArr,String[] typeArr) {
			super(context, R.layout.detail_list, spendingArr);
		
		this.context = context;
		this.spendingArr = spendingArr;
		this.descriptionArr = descriptionArr;
		this.inputDateArr = inputDateArr;
		this.typeArr = typeArr;
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
	View rowView= inflater.inflate(R.layout.detail_list, null, true);
	TextView txtspending = (TextView) rowView.findViewById(R.id.spending);
	TextView txtdescription = (TextView) rowView.findViewById(R.id.description);
	TextView txtinputdate = (TextView) rowView.findViewById(R.id.inputDate);
	//TextView txttype= (TextView) rowView.findViewById(R.id.type);
	txtspending.setText(spendingArr[position]);
	txtdescription.setText(descriptionArr[position]);
	txtinputdate.setText(inputDateArr[position]);
	
	return rowView;
	}
	
}
