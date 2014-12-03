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
	private final String[] inputDateArr;
	private final String[] descriptionArr;	
	private final String[] spendingArr;
	private final String[] typeArr;

	// private ArrayList<MyData> spendingList;
	public CustomList(Activity context, String[] inputDateArr,
			String[] descriptionArr, String[] spendingArr, String[] typeArr) {
		super(context, R.layout.detail_list, spendingArr);

		this.context = context;
		this.inputDateArr = inputDateArr;

		this.descriptionArr = descriptionArr;
		this.spendingArr = spendingArr;
		this.typeArr = typeArr;
	}

	// public CustomList(Activity context,ArrayList<MyData> spendingList) {
	// MyData temp = new MyData();
	//
	// this.context = context;
	// this.spendingArr = spendingList.spending;
	// this.descriptionArr = spendingList.description;
	// this.inputDateArr = spendingList.inputDate;
	// this.typeArr = spendingList.dataType;
	// }
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.detail_list, null, true);

		TextView txtinputdate = (TextView) rowView.findViewById(R.id.inputDate);
		TextView txtdescription = (TextView) rowView
				.findViewById(R.id.description);
		TextView txtspending = (TextView) rowView.findViewById(R.id.spending);
		// TextView txttype= (TextView) rowView.findViewById(R.id.type);
		txtinputdate.setText(inputDateArr[position]);
		txtdescription.setText(descriptionArr[position]);
		txtspending.setText(spendingArr[position]);

		return rowView;
	}

}
