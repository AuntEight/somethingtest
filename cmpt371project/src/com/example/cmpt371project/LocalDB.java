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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;


public class LocalDB extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "localDB";  
	private final static int DATABASE_VERSION = 1;  

	private final static String USERS_TABLE = "users";  
	private final static String USER_ID = "user_ID";  
	private final static String USER_PASSWORD = "user_Password"; 

	private final static String CHILDREN_TABLE = "childrenInfo";
	private final static String CHILD_ID = "child_id";
	private final static String CHILD_FName = "child_firstname";
	private final static String CHILD_LName = "child_lastname";
	private final static String CHILDREN_GENDER = "child_gender";
	private final static String CHILDREN_BIRTH = "child_birthdate";
	private final static String CHILDREN_PHONE = "child_phonenum";
	private final static String CHILDREN_POSTAL = "child_postalcode";


	ArrayList<HashMap<String, Object>> userlist;
	ArrayList<HashMap<String, Object>> childrenList;


	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_username = "username";
	private static final String TAG_password = "password";
	private static final String TAG_users="users";
	private SQLiteDatabase thisDB;

	// users JSONArray
	JSONArray users = null;
	
	JSONArray children=null;

	public LocalDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);	

	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String userTable = "CREATE TABLE "+USERS_TABLE+" ("
				+USER_ID +" TEXT NOT NULL, "
				+USER_PASSWORD+ " TEXT NOT NULL,"
				+"privilege TEXT NOT NULL,"
				+"UNIQUE("+USER_ID+")ON CONFLICT REPLACE);";
		db.execSQL(userTable);

		String childTable = "CREATE TABLE "+CHILDREN_TABLE+" ("
				+CHILD_ID+" TEXT, "
				+CHILD_FName+" TEXT,"
				+CHILD_LName+" TEXT,"
				+CHILDREN_GENDER+ " VARCHAR(12),"
				+CHILDREN_BIRTH+ " DATE,"
				+CHILDREN_PHONE+ " TEXT,"
				+CHILDREN_POSTAL+ " VARCHAR(7),"
				+"UNIQUE("+CHILD_ID+")ON CONFLICT ABORT);";
		Log.d("CHILD TABLE", childTable.toString());
		db.execSQL(childTable);

		SQLiteStatement statement = db.compileStatement("INSERT into "+USERS_TABLE+"(user_ID,user_Password,privilege) VALUES(?,?,?);");
		statement = db.compileStatement("INSERT into "+USERS_TABLE+"(user_ID,user_Password,Privilege) VALUES(?,?,?);");
		statement.bindString(1, "res");
		statement.bindString(2, "res");
		statement.bindString(3,"researcher");
		statement.executeInsert();
		statement.close();			
	} 

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + USERS_TABLE; 
		db.execSQL(sql);
		thisDB=db;
		onCreate(db);

	}


	public String readPassword(String username){
		//to do 
		//the password need to be encrypt
		String password = new String();
		Cursor DBcursor = this.getWritableDatabase().rawQuery("Select user_Password from "+USERS_TABLE +" WHERE user_ID='" + username + "'",null);

		while(DBcursor.moveToNext()){	
			password = DBcursor.getString(0);	
		}
		DBcursor.close(); 
		this.getWritableDatabase().close();
		return password;
	}
	public String getPrivilege(String username){
		String privilege = new String();
		Cursor DBcursor = this.getWritableDatabase().rawQuery("Select privilege from "+USERS_TABLE +" WHERE user_ID='" + username + "'",null);

		while(DBcursor.moveToNext()){	
			privilege = DBcursor.getString(0);	
		}
		DBcursor.close(); 
		this.getWritableDatabase().close();
		return privilege;
	}

	public void exportUserTable(){

		thisDB=this.getWritableDatabase();
		pushUsersToRemoteDB task = new pushUsersToRemoteDB();
		task.execute();
	}

	/**
	 * Background Async Task to Create new product
	 * */
	private class pushUsersToRemoteDB extends AsyncTask<String, String, String> {

		private static final String url_create_user = "http://192.168.0.10/Nutristep/create_user.php";
		/**
		 * Creating product
		 * */
		JSONParser jsonParser = new JSONParser();
		protected String doInBackground(String... args) {
			String query = "SELECT * FROM "+USERS_TABLE;
			Cursor cursor = thisDB.rawQuery(query, null);
			String[] result = new String[3];
			while(cursor.moveToNext()){

				result[0]=cursor.getString(0);
				result[1]=cursor.getString(1);
				result[2]=cursor.getString(2);

				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", result[0].toString()));
				params.add(new BasicNameValuePair("password", result[1].toString()));
				params.add(new BasicNameValuePair("privilege", result[2].toString()));

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
					Log.d("JSON Exception", e.toString());
					e.printStackTrace();
				}
			}

			return null;
		}

	}


	public void updateUserTable(){
		userlist = new ArrayList<HashMap<String, Object>>();
		thisDB=this.getWritableDatabase();
		pullUserFromDB task = new pullUserFromDB();
		task.execute();
	}

	private class pullUserFromDB extends AsyncTask<String, String, String>{

		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_users = "http://192.168.0.10/Nutristep/get_all_users.php";

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

					//looping through All Users
					for (int i = 0; i < users.length(); i++) {
						JSONObject c = users.getJSONObject(i);

						// Storing each json item in variable
						String name = c.getString(TAG_username);
						String password = c.getString(TAG_password);
						String privilege=c.getString("privilege");

						// Inserting the values into the table
						SQLiteStatement statement = thisDB.compileStatement("INSERT into "+USERS_TABLE +"(user_ID,user_Password, privilege) VALUES(?,?,?);");

						statement.bindString(1, name);
						statement.bindString(2, password);
						statement.bindString(3,privilege);
						statement.executeInsert();
						statement.close();				

						// creating new HashMap
						HashMap<String, Object> map = new HashMap<String, Object>();

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
	public ArrayList<HashMap<String, Object>> displayChildren(){
		ArrayList<HashMap<String, Object>> list= new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM "+CHILDREN_TABLE;
		HashMap<String,Object> map = new HashMap<String,Object>();
		Cursor cursor = db.rawQuery(query, null);
		   if (cursor.moveToFirst()) {
		        do {
		        	map.put("location", cursor.getString(1)+" "+cursor.getString(2));
		        	list.add(map);
		        	Log.d("Map",cursor.getString(1));
		        } while (cursor.moveToNext());
		    }
		   
		return list;
		
	}
	public void getChildren(){
		childrenList= new ArrayList<HashMap<String, Object>>();
		thisDB=this.getWritableDatabase();
		pullChildrenFromDB task = new pullChildrenFromDB();
		task.execute();
	}
	private class pullChildrenFromDB extends AsyncTask<String, String, String>{

		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_children = "http://192.168.0.10/Nutristep/get_all_children.php";
		@Override
		protected String doInBackground(String... args) {

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_children, "GET", params);
			assert(!json.equals(null));
			// Check your log cat for JSON reponse
			Log.d("All children: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// users found
					// Getting Array of Users
					children = json.getJSONArray("children");

					//looping through All Users
					for (int i = 0; i < children.length(); i++) {
						JSONObject c = children.getJSONObject(i);

						// Storing each json item in variable
						String child_id = c.getString(CHILD_ID);
						String child_firstName = c.getString(CHILD_FName);
						String child_lastName = c.getString(CHILD_LName);
						String child_gender = c.getString(CHILDREN_GENDER);
						String child_birthdate = c.getString(CHILDREN_BIRTH);
						String child_phonenum = c.getString(CHILDREN_PHONE);
						String child_postalcode = c.getString(CHILDREN_POSTAL);
						
						// Inserting the values into the table
						SQLiteStatement statement = thisDB.compileStatement("INSERT into "+CHILDREN_TABLE +"("
						+CHILD_ID +","
						+CHILD_FName+","
						+CHILD_LName+","
						+CHILDREN_GENDER+","
						+CHILDREN_BIRTH+","
						+CHILDREN_PHONE+","
						+CHILDREN_POSTAL+")VALUES(?,?,?,?,?,?,?);");
								
						statement.bindString(1,child_id);
						statement.bindString(2,child_firstName);
						statement.bindString(3,child_lastName);
						statement.bindString(4,child_gender);
						statement.bindString(5,child_birthdate);
						statement.bindString(6,child_phonenum);
						statement.bindString(7,child_postalcode);
						statement.executeInsert();
						statement.close();				

						// creating new HashMap
						HashMap<String, Object> map = new HashMap<String, Object>();

						// adding each child node to HashMap key => value
						map.put(CHILD_ID,child_id);
						map.put(CHILD_FName,child_firstName);
						map.put(CHILD_LName,child_lastName);
						map.put(CHILDREN_GENDER,child_gender);
						map.put(CHILDREN_BIRTH,child_birthdate);
						map.put(CHILDREN_PHONE,child_phonenum);
						map.put(CHILDREN_POSTAL,child_postalcode);
						
						// adding HashList to ArrayList
						childrenList.add(map);
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

