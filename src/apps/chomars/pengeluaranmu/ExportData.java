package apps.chomars.pengeluaranmu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;





import android.R.string;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class ExportData extends Fragment {

	protected static final File DATABASE_DIRECTORY = new File(
			Environment.getExternalStorageDirectory(), "ImportExport");
	private Button txtExport;
	private Button txtImportData;
	protected static final File IMPORT_ITEM_FILE = new File(DATABASE_DIRECTORY,
			"ExportExcelPENGELUARANMU1.csv");
	String exportFileName="ExportExcelPENGELUARANMU1.csv";
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_export_data,
					container, false);
			
			txtExport =(Button) rootView.findViewById(R.id.export); 
			txtImportData =(Button) rootView.findViewById(R.id.importdata);
			txtExport.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						exportDataToCSV(exportFileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(getActivity(), "exported", Toast.LENGTH_LONG).show();
				}
			});
			txtImportData.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					importData(IMPORT_ITEM_FILE);
					
				}
			});
			
			
			return rootView;
		}

	public void importData(File IMPORT_ITEM_FILE){
		int i = 0;

		boolean flag_is_header = false;

		File file = new File(IMPORT_ITEM_FILE.getPath());
		if (file.exists()) {
		}
		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;

		try {
			while ((line = bufRdr.readLine()) != null) {

				String[] insertValues = line.split(",");
				if (flag_is_header) {
					String trxDate = insertValues[0] ;
					String Type = insertValues[1];
					String Description = insertValues[2];
					int Amount = Integer.parseInt(insertValues[3]);
					String insertDate = insertValues[4];
					DatabaseHandler db = new DatabaseHandler(getActivity());
					db.addSpending(new Spending("Chomars", Amount,
							Description, insertDate,
							Type, trxDate));
				
				//	Log.e("no. of rows inserted", ""+row);

				} else {
					flag_is_header = true;
				}
			}
			
			bufRdr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void exportDataToCSV(String importItemFile) throws IOException {

		String Query = "SELECT * FROM spending_money  order by trx_date desc ,input_date desc";
		String csvHeader ="";
		Boolean returnCode = false;
			
		try {
			
			if (!DATABASE_DIRECTORY.exists()) {
				DATABASE_DIRECTORY.mkdirs();
			}
		
			File outFile = new File(DATABASE_DIRECTORY, importItemFile);
			FileWriter fileWriter = new FileWriter(outFile);
			//Log.e("after FileWriter :file name", outFile.toString());
			BufferedWriter out = new BufferedWriter(fileWriter);
			

			// Log.e("excel", "cursor col count" + cursor.getCount());
			

		
		
		DatabaseHandler db = new DatabaseHandler(getActivity());
		List<Spending> spending = db.getAllSpending(Query);	
		//HEASER
		csvHeader += " Date ,";
		csvHeader += " Type ,";
		csvHeader += " Description ,";
		csvHeader += " Amount ,";
		csvHeader += "\n";

			for (Spending cn : spending) {
				csvHeader += "\"" +DateFormat.DateFormat("5", cn.getTrxDate())+ "\",";
				csvHeader += "\"" +cn.getType()+ "\",";
				csvHeader += "\"" + cn.getDescription() + "\",";
				csvHeader += "\"" + Double.valueOf(cn.getMoney()) + "\",";			
				csvHeader += "\n";

				
				
				
			}
			
			out.write(csvHeader);
			out.close();
			fileWriter.close();
			
			
			returnCode = true;
		} catch (Exception e) {
			returnCode = false;
		//	Log.e("Exception", e.getMessage());
		}
	
	}
}
