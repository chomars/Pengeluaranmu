package apps.chomars.pengeluaranmu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 5;
	
	private static final String DATABASE_NAME = "management";
	private static final String TABLE_SPENDING = "spending_money";
	private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MONEY = "money";
    private static final String KEY_DESC = "description";
    private static final String KEY_DATE = "input_date";
    private static final String KEY_TRX_DATE = "trx_date";
    private static final String KEY_TYPE = "type";
    
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_SPENDING_TABLE = "CREATE TABLE " + TABLE_SPENDING + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_MONEY + " INT," 
                + KEY_DESC + " TEXT ," 
                + KEY_DATE + " DATETIME ,"                  
                + KEY_TYPE + " TEXT ,"
        		+ KEY_TRX_DATE + " DATETIME )";
        db.execSQL(CREATE_SPENDING_TABLE);
       
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPENDING);
		 
	        // Create tables again
	        onCreate(db);
		
	}
	
	void addSpending(Spending spending) {
		   
        SQLiteDatabase db = this.getWritableDatabase();
       
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, spending.getName()); 
        values.put(KEY_MONEY, spending.getMoney());
        values.put(KEY_DESC, spending.getDescription());
        values.put(KEY_DATE, spending.getDate());
        values.put(KEY_TYPE, spending.getType());
        values.put(KEY_TRX_DATE, spending.getTrxDate());
 
        // Inserting Row
        db.insert(TABLE_SPENDING, null, values);
        db.close(); // Closing database connection
    }
	// Getting All Contacts
    public List<Spending> getAllSpending(String Query) {
        List<Spending> contactList = new ArrayList<Spending>();
        // Select All Query
        String selectQuery="";
        
        if(Query.equals("")){
         selectQuery = "SELECT  id,name, money,description,input_date,type,strftime('%d %m %Y', input_date) dateformatted,trx_date FROM " + TABLE_SPENDING + " order by trx_date desc ";
        }else{
         selectQuery = Query;
        }
        Log.d(Query, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Spending contact = new Spending();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setMoney(Integer.parseInt(cursor.getString(2)));
                contact.setDescription(cursor.getString(3));
                contact.setDate(cursor.getString(4));
                contact.setType(cursor.getString(5));
                contact.setTrxDate(cursor.getString(6));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    public List<Spending> getRekapSpending(String Query) {
        List<Spending> contactList = new ArrayList<Spending>();
        // Select All Query
        String selectQuery="";
        
       
         selectQuery = Query;
        
        Log.d(Query, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Spending contact = new Spending();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setMoney(Integer.parseInt(cursor.getString(2)));
                contact.setDescription(cursor.getString(3));
                contact.setDate(cursor.getString(4));
                contact.setType(cursor.getString(5));
                contact.setTrxDate(cursor.getString(6));
                contact.setIncome(Integer.parseInt(cursor.getString(7)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    public void deleteSpending(Spending spending) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPENDING, KEY_ID + " = ?",
                new String[] { String.valueOf(spending.getID()) });
        
        db.close();
    }
    public int updateSpending(Spending spending) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, spending.getName());
        values.put(KEY_MONEY, spending.getMoney());
        values.put(KEY_DESC, spending.getDescription());
        values.put(KEY_DATE, spending.getDate());
        values.put(KEY_TYPE, spending.getType());
        // updating row
        return db.update(TABLE_SPENDING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(spending.getID()) });
    }
    public int sumSpendingAll(){
    	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(
		    "SELECT SUM("+KEY_MONEY+") FROM "+TABLE_SPENDING + " where "+KEY_TYPE+" = '2'", null);
		if(cursor.moveToFirst()) {
		    return cursor.getInt(0);
		}
		return 0;
	}
    public int sumIncomeAll(){
    	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(
		    "SELECT SUM("+KEY_MONEY+") FROM "+TABLE_SPENDING + " where "+KEY_TYPE+" = '1'", null);
		if(cursor.moveToFirst()) {
		    return cursor.getInt(0);
		}
		return 0;
	}
}
