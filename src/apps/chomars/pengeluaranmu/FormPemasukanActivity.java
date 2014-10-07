package apps.chomars.pengeluaranmu;

import java.text.SimpleDateFormat;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;

import java.util.Calendar;

import apps.chomars.pengeluaranmu.R;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.widget.DatePicker;

public class FormPemasukanActivity extends Fragment {

	

	static final int TIME_DIALOG_ID = 0;
	private EditText txtTanggal;
	private Button btnSave;
	static final int DATE_DIALOG_ID = 1;
	private String[] arrMonth = { "01","02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12",};

	private View v;
	private String inputdate;
	@Override
//	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.activity_pemasukan, container, false);
        txtTanggal = (EditText) v.findViewById(R.id.tanggal);    
        btnSave = (Button) v.findViewById(R.id.button1);
       
        
        		txtTanggal.setOnClickListener(new OnClickListener() {

        			   @Override
        			   public void onClick(View v) {
        			    showDatePicker();
        			   }
    			  });
        
       btnSave.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 inputdate = df.format(Calendar.getInstance().getTime());
			DatabaseHandler db = new DatabaseHandler(getActivity());
			EditText editText = (EditText) getActivity().findViewById(R.id.editText1);
			EditText description = (EditText) getActivity().findViewById(R.id.description);
			int spending = Integer.parseInt(editText.getText().toString());
			String descriptionText = description.getText().toString();
			String type = "1";
			String trxdate = txtTanggal.getText().toString();
			db.addSpending(new Spending("Chomars", spending, descriptionText,inputdate ,
					type,trxdate));
			Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
			clear();
			}
       });
        
        return v;
       

    }


	public void clear() {
		EditText editText = (EditText) getActivity().findViewById(R.id.editText1);
		EditText description = (EditText) getActivity().findViewById(R.id.description);
		editText.setText("");
		description.setText("");
		txtTanggal.setText("");
	}
//
//	@Override
	private void showDatePicker() {
		  DatePickerFragment date = new DatePickerFragment();
		  /**
		   * Set Up Current Date Into dialog
		   */
		  Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calender.get(Calendar.YEAR));
		  args.putInt("month", calender.get(Calendar.MONTH));
		  args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(ondate);
		  date.show(getFragmentManager(), "Date Picker");
		 }

		 OnDateSetListener ondate = new OnDateSetListener() {
		
		  public void onDateSet(DatePicker view, int mYear, int mMonth,
		    int mDay) {
			  String Day;
			  String Month;
			  if(mDay < 10){
				  Day = "0"+mDay;
			  }else{
				  Day =  String.valueOf(mDay);
			  }
			  mMonth = mMonth + 1;
			  if(mMonth <10){
				  Month = "0"+mMonth;
			  }else{
				  Month = String.valueOf(mMonth);
			  }
			  
			  txtTanggal.setText( String.valueOf(mYear) + "-" +Month
		       + "-" + Day );
		 
		  }
		 };


}
