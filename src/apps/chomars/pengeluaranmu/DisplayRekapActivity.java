package apps.chomars.pengeluaranmu;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayRekapActivity extends Fragment {
	int i;
	private String[] spendingArr;
	private String[] inputDateArr;
	private String[] typeArr;
	private String[] incomeArr;
	private int[] idArr;
	private String[] balanceArr;
	private Spinner dimension;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_display_rekap, container,
				false);
		final ListView monthsListView = (ListView) v
				.findViewById(R.id.months_list_rekap);
		final DatabaseHandler db = new DatabaseHandler(getActivity());

		String strFormat = "#,###";
		final DecimalFormat df = new DecimalFormat(strFormat,
				new DecimalFormatSymbols(Locale.GERMAN));
		String Query = "";
		Query = "SELECT id,name,"
				+ "SUM(CASE WHEN (type = '2') THEN money ELSE 0 END)  as spend_monthly,description,input_date,"
				+ "type,strftime('%Y-%m', trx_date) dateformatted , "
				+ "SUM(CASE WHEN (type = '1') THEN money ELSE 0 END)  as income_monthly "
				+ "FROM spending_money "
				+ "group by strftime('%Y',trx_date) ,strftime('%m',trx_date)"
				+ " order by trx_date desc";

		List<Spending> spending = db.getRekapSpending(Query);

		spendingArr = new String[spending.size()];
		inputDateArr = new String[spending.size()];
		typeArr = new String[spending.size()];
		idArr = new int[spending.size()];
		incomeArr = new String[spending.size()];
		balanceArr = new String[spending.size()];
		for (Spending cn : spending) {
			Double money = Double.valueOf(cn.getMoney());
			Double income = Double.valueOf(cn.getIncome());
			Double balance = Double.valueOf(cn.getIncome() - cn.getMoney());
			spendingArr[i] = "" + df.format(money);
			inputDateArr[i] = DateFormat.DateFormat("3", cn.getTrxDate());
			typeArr[i] = cn.getType();
			idArr[i] = cn.getID();
			incomeArr[i] = "" + df.format(income);
			balanceArr[i] = "" + df.format(balance);
			i++;
		}

		CustomListRekap adapter = new CustomListRekap(getActivity(),
				spendingArr, inputDateArr, typeArr, incomeArr, balanceArr);

		adapter.notifyDataSetChanged();
		monthsListView.setAdapter(adapter);

		dimension = (Spinner) v.findViewById(R.id.dimension);
		List<String> ListDimension = new ArrayList<String>();
		ListDimension.add("Bulanan");
		ListDimension.add("Harian");

		ArrayAdapter<String> DimensionAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				ListDimension);
		DimensionAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dimension.setAdapter(DimensionAdapter);
		dimension.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String DimensionText = String.valueOf(dimension
						.getSelectedItem());
				// TODO Auto-generated method stub
				if (DimensionText.equals("Harian")) {
					String Query2 = "SELECT id,name,"
							+ "SUM(CASE WHEN (type = '2') THEN money ELSE 0 END)  as spend_monthly,description,input_date,"
							+ "type,strftime('%Y-%m-%d', trx_date) dateformatted , "
							+ "SUM(CASE WHEN (type = '1') THEN money ELSE 0 END)  as income_monthly "
							+ "FROM spending_money "
							+ "group by strftime('%Y',trx_date) ,strftime('%m',trx_date),strftime('%d',trx_date)"
							+ " order by trx_date desc";

					List<Spending> spending2 = db.getRekapSpending(Query2);

					String[] spendingArr2 = new String[spending2.size()];
					String[] inputDateArr2 = new String[spending2.size()];
					String[] typeArr2 = new String[spending2.size()];
					int[] idArr2 = new int[spending2.size()];
					String[] incomeArr2 = new String[spending2.size()];
					String[] balanceArr2 = new String[spending2.size()];
					int i2 = 0;
					for (Spending cn : spending2) {
						Double money = Double.valueOf(cn.getMoney());
						Double income = Double.valueOf(cn.getIncome());
						Double balance = Double.valueOf(cn.getIncome()
								- cn.getMoney());
						spendingArr2[i2] = "" + df.format(money);
						inputDateArr2[i2] = DateFormat.DateFormat("4",
								cn.getTrxDate());
						typeArr2[i2] = cn.getType();
						idArr2[i2] = cn.getID();
						incomeArr2[i2] = "" + df.format(income);
						balanceArr2[i2] = "" + df.format(balance);
						i2++;
					}

					CustomListRekap adapter2 = new CustomListRekap(
							getActivity(), spendingArr2, inputDateArr2,
							typeArr2, incomeArr2, balanceArr2);

					adapter2.notifyDataSetChanged();
					monthsListView.setAdapter(adapter2);
				} else if (DimensionText.equals("Bulanan")) {
					String Query3 = "SELECT id,name,"
							+ "SUM(CASE WHEN (type = '2') THEN money ELSE 0 END)  as spend_monthly,description,input_date,"
							+ "type,strftime('%Y-%m', trx_date) dateformatted , "
							+ "SUM(CASE WHEN (type = '1') THEN money ELSE 0 END)  as income_monthly "
							+ "FROM spending_money "
							+ "group by strftime('%Y',trx_date) ,strftime('%m',trx_date)"
							+ " order by trx_date desc";

					List<Spending> spending3 = db.getRekapSpending(Query3);

					String[] spendingArr3 = new String[spending3.size()];
					String[] inputDateArr3 = new String[spending3.size()];
					String[] typeArr3 = new String[spending3.size()];
					int[] idArr3 = new int[spending3.size()];
					String[] incomeArr3 = new String[spending3.size()];
					String[] balanceArr3 = new String[spending3.size()];
					int i3 = 0;
					for (Spending cn3 : spending3) {
						Double money = Double.valueOf(cn3.getMoney());
						Double income = Double.valueOf(cn3.getIncome());
						Double balance = Double.valueOf(cn3.getIncome()
								- cn3.getMoney());
						spendingArr3[i3] = "" + df.format(money);
						inputDateArr3[i3] = DateFormat.DateFormat("3",
								cn3.getTrxDate());
						typeArr3[i3] = cn3.getType();
						idArr3[i3] = cn3.getID();
						incomeArr3[i3] = "" + df.format(income);
						balanceArr3[i3] = "" + df.format(balance);
						i3++;
					}

					CustomListRekap adapter3 = new CustomListRekap(
							getActivity(), spendingArr3, inputDateArr3,
							typeArr3, incomeArr3, balanceArr3);

					adapter3.notifyDataSetChanged();
					monthsListView.setAdapter(adapter3);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		return v;

	}

}
