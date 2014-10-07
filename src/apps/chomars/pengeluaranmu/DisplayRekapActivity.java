package apps.chomars.pengeluaranmu;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
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
import android.widget.ListView;
import android.widget.TextView;

public class DisplayRekapActivity extends Fragment {
	int i;
	private String[] spendingArr;
	private String[] inputDateArr;
	private String[] typeArr;
	private String[] incomeArr;
	private int[] idArr;
	private String[] balanceArr;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_display_rekap, container,
				false);
		final DatabaseHandler db = new DatabaseHandler(getActivity());

		
		String strFormat = "#,###";
		DecimalFormat df = new DecimalFormat(strFormat,
				new DecimalFormatSymbols(Locale.GERMAN));
		String Query = "";
		Query = "SELECT id,name,"
				+ "SUM(CASE WHEN (type = '2') THEN money ELSE 0 END)  as spend_monthly,description,input_date,"
				+ "type,strftime('%Y-%m', trx_date) dateformatted , "
				+ "SUM(CASE WHEN (type = '1') THEN money ELSE 0 END)  as income_monthly "
				+ "FROM spending_money " + "group by strftime('%Y',trx_date) ,strftime('%m',trx_date)"
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
			spendingArr[i] = "-  Rp. " + df.format(money);
			inputDateArr[i] = DateFormat.DateFormat("2", cn.getTrxDate());
			typeArr[i] = cn.getType();
			idArr[i] = cn.getID();
			incomeArr[i] = "+ Rp. " + df.format(income);
			balanceArr[i] =  "~ Rp. " + df.format(balance); 
			i++;
		}

		final CustomListRekap adapter = new CustomListRekap(getActivity(),
				spendingArr, inputDateArr, typeArr,incomeArr,balanceArr);

		ListView monthsListView = (ListView) v
				.findViewById(R.id.months_list_rekap);
		adapter.notifyDataSetChanged();
		monthsListView.setAdapter(adapter);
		return v;

	}
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);final DatabaseHandler db = new
	// DatabaseHandler(this);
	//
	// String strFormat = "#,###";
	// DecimalFormat df = new DecimalFormat(strFormat,
	// new DecimalFormatSymbols(Locale.GERMAN));
	// String Query = "";
	// Query =
	// "SELECT id,name,sum(money) as spend_monthly,description,input_date,type,strftime('%Y-%m', input_date) dateformatted FROM spending_money group by date(dateformatted)";
	// List<Spending> spending = db.getAllSpending(Query);
	// spendingArr = new String[spending.size()];
	// inputDateArr = new String[spending.size()];
	// typeArr = new String[spending.size()];
	// idArr = new int[spending.size()];
	//
	//
	// for (Spending cn : spending) {
	// Double money = Double.valueOf(cn.getMoney());
	// spendingArr[i] = "Rp. " + df.format(money);
	// inputDateArr[i] =DateFormat.DateFormat("2",cn.getDate());
	// typeArr[i] = cn.getType();
	// idArr[i] = cn.getID();
	// i++;
	// }
	//
	//
	// setContentView(R.layout.activity_display_rekap);
	//
	// final CustomListRekap adapter = new
	// CustomListRekap(DisplayRekapActivity.this,
	// spendingArr, inputDateArr, typeArr);
	//
	// ListView monthsListView = (ListView)
	// findViewById(R.id.months_list_rekap);
	// adapter.notifyDataSetChanged();
	// monthsListView.setAdapter(adapter);
	// }

}
