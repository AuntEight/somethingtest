package com.example.cmpt371project;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.util.Log;


public class LocalDB extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "localDB";  
	private final static int DATABASE_VERSION = 1;  
	private final static String TABLE_NAME = "userData";  
	public final static String USER_ID = "user_ID";  
	public final static String USER_PASSWORD = "user_Password";  
	public String fileContents="";

	ArrayList<HashMap<String, String>> userlist;


	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_username = "username";
	private static final String TAG_password = "password";
	private static final String TAG_users="users";
	private SQLiteDatabase thisDB;
	
	// users JSONArray
	JSONArray users = null;

	public LocalDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);	

	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+USER_ID +" TEXT, "
				+USER_PASSWORD+ " TEXT,"
				+"UNIQUE("+USER_ID+")ON CONFLICT REPLACE);";
		db.execSQL(sql);
		SQLiteStatement statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
		statement.bindString(1, "admin");
		statement.bindString(2, "admin");
		statement.executeInsert();
		statement.close();		
	} 

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME; 
		db.execSQL(sql);
		thisDB=db;
		onCreate(db);

	}

    
	public String readPassword(String username){
		//to do 
		//the password need to be encrypt
		String password = new String();
		Cursor DBcursor = this.getWritableDatabase().rawQuery("Select user_Password from userData WHERE user_ID='" + username + "'",null);

		while(DBcursor.moveToNext()){	
			password = DBcursor.getString(0);	
		}
		DBcursor.close(); 
		this.getWritableDatabase().close();
		return password;
	}
	
	public void exportUserTable(){
		
		thisDB=this.getWritableDatabase();
		pushToRemoteDB task = new pushToRemoteDB();
		task.execute();
	}
	
	/**
	 * Background Async Task to Create new product
	 * */
	class pushToRemoteDB extends AsyncTask<String, String, String> {

		private static final String url_create_user = "http://10.80.2.29/create_user.php";
		/**
		 * Creating product
		 * */
		JSONParser jsonParser = new JSONParser();
		protected String doInBackground(String... args) {
			String query = "SELECT * FROM "+TABLE_NAME;
			Cursor cursor = thisDB.rawQuery(query, null);
			String[] result = new String[2];
			while(cursor.moveToNext()){
				result[0]=cursor.getString(0);
				result[1]=cursor.getString(1);

				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", result[0].toString()));
				params.add(new BasicNameValuePair("password", result[1].toString()));

				// getting JSON Object
				// Note that create user url accepts POST method
				JSONObject json = jsonParser.makeHttpRequest(url_create_user,
						"POST", params);

				// check log cat fro response
				Log.d("Create Response", json.toString());

				// check for success tag
				try {
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						// successfully created product

					} else {
						// failed to create product
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			return null;
		}

	}

	
	public void updateUserTable(){
		userlist = new ArrayList<HashMap<String, String>>();
		thisDB=this.getWritableDatabase();
		RequestTask task = new RequestTask();
		task.execute();
	}

	public class RequestTask extends AsyncTask<String, String, String>{
		
		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_users = "http://10.80.2.29/index.php";
		
	    @Override
		/**
		 * getting All users from url
		 * */
	    
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_users, "GET", params);
			assert(!json.equals(null));
			// Check your log cat for JSON reponse
			Log.d("All users: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// users found
					// Getting Array of Users
					users = json.getJSONArray(TAG_users);
				
					Log.d("Breakpoint before loop", "Reached");
					//looping through All Users
					for (int i = 0; i < users.length(); i++) {
						JSONObject c = users.getJSONObject(i);

						// Storing each json item in variable
						String name = c.getString(TAG_username);
						String password = c.getString(TAG_password);
						
						// Inserting the values into the table
						SQLiteStatement statement = thisDB.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");

						statement.bindString(1, name);
						statement.bindString(2, password);
						statement.executeInsert();
						statement.close();				

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_username, name);
						map.put(TAG_password, password);
						// adding HashList to ArrayList
						userlist.add(map);
					}
				} else {
					throw new Exception();
				}
			} catch (JSONException e) {
				Log.d("JSON EXCEPTION",e.toString());
				e.printStackTrace();
			} catch (IOException e) {
				Log.d("IO EXCEPTION",e.toString());
				e.printStackTrace();
			} catch (Exception e) {
				Log.d("Exception Tag", e.toString());
				e.printStackTrace();
			} 
			return null;
		}
	}
}
