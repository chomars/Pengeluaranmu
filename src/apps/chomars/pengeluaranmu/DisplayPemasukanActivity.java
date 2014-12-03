package apps.chomars.pengeluaranmu;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import apps.chomars.pengeluaranmu.R;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPemasukanActivity extends Fragment {

	private String[] spendingArr;
	private String[] descriptionArr;
	private String[] inputDateArr;
	private String[] typeArr;
	private int[] idArr;

	private int i = 0;
	private ListView monthsListView;
	String Query = "";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_display_pemasukan, container,
				false);
		final DatabaseHandler db = new DatabaseHandler(getActivity());
		String strFormat = "#,###";
		DecimalFormat df = new DecimalFormat(strFormat,
				new DecimalFormatSymbols(Locale.GERMAN));	
		Query = "SELECT * FROM spending_money where type = '1' order by  trx_date desc ,input_date desc";
		List<Spending> spending = db.getAllSpending(Query);
		spendingArr = new String[spending.size()];
		descriptionArr = new String[spending.size()];
		inputDateArr = new String[spending.size()];
		typeArr = new String[spending.size()];
		idArr = new int[spending.size()];

		for (Spending cn : spending) {

			Double money = Double.valueOf(cn.getMoney());
			spendingArr[i] =  df.format(money);
			descriptionArr[i] = cn.getDescription();
			inputDateArr[i] = DateFormat.DateFormat("5", cn.getTrxDate());
			//inputDateArr[i] = cn.getTrxDate();
			typeArr[i] = cn.getType();
			idArr[i] = cn.getID();
			i++;
		}

		CustomList adapter = new CustomList(getActivity(), inputDateArr, 
				descriptionArr,spendingArr, typeArr);
//		TextView SpendingAll = (TextView) v.findViewById(R.id.spendingAll);
		Double AllSpending = Double.valueOf(db.sumIncomeAll());
//		SpendingAll.setText("Total Pengeluaran Mu Rp. "
//				+ df.format(AllSpending));
		monthsListView = (ListView) v.findViewById(R.id.months_list);
		adapter.notifyDataSetChanged();
		monthsListView.setAdapter(adapter);
		monthsListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, final int position, long rowId) {
						// delete selected item list view
						AlertDialog.Builder adb = new AlertDialog.Builder(
								getActivity());
						adb.setTitle("");
						adb.setMessage("Yakin akan menghapus item ini?");
						// adb.setPositiveButton("Ok", null);
						adb.setPositiveButton("ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										db.deleteSpending(new Spending(
												idArr[position]));

									}
								});
						adb.setNegativeButton("cancel",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
						adb.show();

						return false;
					}

				});
		return v;
	}

	
	
	
}
