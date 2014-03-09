package com.example.cmpt371project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;



import android.content.ContentValues;
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

	//Variables needed for users table
	private final static String USERS_TABLE = "users";  
	private final static String USER_ID = "user_ID";  
	private final static String USER_PASSWORD = "user_Password"; 
	private final static String USER_NAME="username";
	private final static String USER_PRIVILEGE="privilege";
	private final static String USER_FIRSTNAME="firstname";
	private final static String USER_LASTNAME="lastname";
	private final static String USER_PHONENUM="phonenum";
	ArrayList<HashMap<String, Object>> userlist;

	//Variables needed for ChildInfo table
	private final static String CHILDREN_TABLE = "childrenInfo";
	private final static String CHILD_ID = "child_id";
	private final static String CHILD_FName = "child_firstname";
	private final static String CHILD_LName = "child_lastname";
	private final static String CHILDREN_GENDER = "child_gender";
	private final static String CHILDREN_BIRTH = "child_birthdate";
	private final static String CHILDREN_PHONE = "child_phonenum";
	private final static String CHILDREN_POSTAL = "child_postalcode";
	private static final String CHILD_ADDRESS = "address";
	private static final String TAG_CHILDREN="children";
	ArrayList<HashMap<String, Object>> childrenList;
	
	//Variables needed for Institutions table
	private String INSTI_TABLE="institutions";
	private String INSTI_ID="institution_id";
	private String INSTI_name="institution_name";
	private String INSTI_address="institution_address";
	private String INSTI_Descipt="institution_description";
	private String TAG_INSTITUTIONS="institutions";
	ArrayList<HashMap<String,Object>> institutionList;
	
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_username = "username";
	private static final String TAG_password = "password";
	private static final String TAG_users="users";
	private SQLiteDatabase thisDB;

	// users JSONArray
	JSONArray users = null;
	JSONArray children=null;
	JSONArray institutions=null;

	public LocalDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);	

	}

	@Override
	
	public void onCreate(SQLiteDatabase db){
		//Creating user's table
		String userTable = "CREATE TABLE "+USERS_TABLE+" ("
				+USER_ID +" TEXT NOT NULL, "
				+USER_PASSWORD+ " TEXT NOT NULL,"
				+USER_FIRSTNAME+ " TEXT,"
				+USER_LASTNAME+" TEXT,"
				+USER_PHONENUM+" TEXT,"
				+USER_PRIVILEGE+" TEXT NOT NULL,"
				+"UNIQUE("+USER_ID+")ON CONFLICT REPLACE);";
		db.execSQL(userTable);

		//Creating children table
		String childTable = "CREATE TABLE "+CHILDREN_TABLE+" ("
				+CHILD_ID+" TEXT, "
				+CHILD_FName+" TEXT,"
				+CHILD_LName+" TEXT,"
				+CHILDREN_BIRTH+ " DATE,"
				+CHILDREN_GENDER+ " VARCHAR(12),"
				+CHILD_ADDRESS+" TEXT,"
				+CHILDREN_POSTAL+ " VARCHAR(7),"
				+CHILDREN_PHONE+ " TEXT,"
				+"UNIQUE("+CHILD_ID+")ON CONFLICT ABORT);";
		
		Log.d("CHILD TABLE", childTable.toString());
		db.execSQL(childTable);
		
		//Creating institution table
		String institutionTable="CREATE TABLE "+INSTI_TABLE+" ("
				+INSTI_ID+" TEXT,"
				+INSTI_name+" TEXT,"
				+INSTI_address+" TEXT,"
				+INSTI_Descipt+" TEXT,"
				+"UNIQUE("+INSTI_ID+")ON CONFLICT ABORT);";
		db.execSQL(institutionTable);

		SQLiteStatement statement = db.compileStatement("INSERT into "+USERS_TABLE+"(user_ID,user_Password,privilege,firstname,lastname) VALUES(?,?,?,?,?);");
		statement = db.compileStatement("INSERT into "+USERS_TABLE+"(user_ID,user_Password,Privilege,firstname,lastname) VALUES(?,?,?,?,?);");
		statement.bindString(1, "res");
		statement.bindString(2, "res");
		statement.bindString(3,"researcher");
		statement.bindString(4,"res");
		statement.bindString(5,"eacher");
		statement.executeInsert();
		statement.close();		
		
		statement = db.compileStatement("INSERT into "+USERS_TABLE+"(user_ID,user_Password,Privilege,firstname,lastname) VALUES(?,?,?,?,?);");
		statement.bindString(1, "admin");
		statement.bindString(2, "admin");
		statement.bindString(3,"administrator");
		statement.bindString(4, "admin");
		statement.bindString(5,"istrator");
		statement.executeInsert();
		statement.close();	
		
		statement = db.compileStatement("INSERT into "+CHILDREN_TABLE +"("
				+CHILD_ID +","
				+CHILD_FName+","
				+CHILD_LName+","
				+CHILDREN_BIRTH+","
				+CHILDREN_GENDER+","
				+CHILD_ADDRESS+","
				+CHILDREN_POSTAL+ ","
				+CHILDREN_PHONE+ ")VALUES(?,?,?,?,?,?,?,?);");
				
		statement.bindString(1,"9999"); //TEMP ID
		statement.bindString(2,"david");
		statement.bindString(3,"thai");
		statement.bindString(4,"1992-02-29");
		statement.bindString(5,"male");
		statement.bindString(6,"SK");
		statement.bindString(7,"SK");
		statement.bindString(8,"555-555-5555");
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

	/**
	 * Gets the password of user
	 * @param username
	 * @return Returns password for comparison for login
	 */
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
	/**
	 * Gets the privilege of the user
	 * @param username The username of user
	 * @return returns the level of privilege of the user
	 */
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


	/**
	 * Writes user table in the internal database to
	 * user table in the remote database
	 */
	public void exportUserTable(){

		thisDB=this.getWritableDatabase();
		pushUsersToRemoteDB task = new pushUsersToRemoteDB();
		task.execute();
	}

	/**
	 * pushUsersToRemoteDB private class that calls to a php webservice and inserts into the remote database
	 * which is done in a background thread.
	 */
	private class pushUsersToRemoteDB extends AsyncTask<String, String, String> {

		private static final String url_create_user = "http://192.168.0.10/Nutristep/create_user.php";
		
		/*
		 * Creating users
		 */
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
	
	/**
	 * Adding a new user to the database or modify already existing user. Passes the parameters to 
	 * insertUserToLocalDB to insert into the table in the local database to be performed in a background thread
	 * @param username The username
	 * @param password Password for the user
	 * @param privilege Privilege level for the user
	 */
	public void addNewUser(String username, String password, String firstname, String lastname, String phonenum, String privilege){
		thisDB=this.getWritableDatabase();
//      need for debug
//		insertUserToLocalDB task = new insertUserToLocalDB();
//		task.execute(username, password,firstname,lastname,phonenum,privilege);
	    ContentValues userData = new ContentValues();
	    
	    userData.put(USER_ID, username);
	    userData.put(USER_PASSWORD, password);  
	    userData.put(USER_FIRSTNAME, firstname);  
	    userData.put(USER_LASTNAME, lastname);
	    userData.put(USER_PHONENUM, phonenum);
	    userData.put(USER_PRIVILEGE, privilege);
	    thisDB.insert(USERS_TABLE, null, userData);  
	
	}
	
	/**
	 * @author Xingze
	 * @return an arrayList of all users data
	 * get all users in DB, return an arrayList
	 */
	public ArrayList<HashMap<String,Object>> getAllUsers(){
	
		ArrayList<HashMap<String,Object>> allUsers = new ArrayList<HashMap<String,Object>>();
		Cursor DBcursor = this.getWritableDatabase().rawQuery("Select " + USER_ID +","
																	   + USER_PASSWORD +","
																	   + USER_FIRSTNAME +","
																	   + USER_LASTNAME +","
																	   + USER_PHONENUM +","
																	   + USER_PRIVILEGE 
																	   + " from " + USERS_TABLE,null);
    	while(DBcursor.moveToNext()){	
    		String userID = DBcursor.getString(0);	
    		String userPassword = DBcursor.getString(1);
    		String userFirstName = DBcursor.getString(2);
    		String userLastName = DBcursor.getString(3);
    		String userPhoneNum = DBcursor.getString(4);
    		String userPrivilege = DBcursor.getString(5);
    		
 
    		HashMap<String,Object> oneUser = new HashMap<String,Object>();
    		
    		//set data in hash map
    		oneUser.put("userID", userID);
    		oneUser.put("userPassword", userPassword);
    		oneUser.put("userFirstName", userFirstName);
    		oneUser.put("userLastName", userLastName);
    		oneUser.put("userPhoneNum", userPhoneNum);
    		oneUser.put("userPrivilege", userPrivilege);    			
    		allUsers.add(oneUser);
    		
    	}
    	DBcursor.close(); 
    	this.getWritableDatabase().close();		
		return allUsers; 
		
	}
	/**
	 * @author Xingze
	 * update users in DB
	 * @param username  the key for search in DB
	 */
	public void updateUsers(String username, String password, String firstname, String lastname, String phonenum){
    	SQLiteDatabase db = this.getWritableDatabase();
//    	db.execSQL("UPDATE " + USERS_TABLE +" SET "+ USER_PASSWORD +"='" + password+ "', " 
//    				+ USER_LASTNAME + "='" + lastname + "', " 
//    				+ USER_FIRSTNAME + "='" + firstname 
//    				+" WHERE " + USER_ID+ "='" + username  + "'");
    	
//    	db.execSQL("UPDATE list_table SET LIST_CONTENT='" + newName + "', LIST_TIME='" + newTime + "' WHERE LIST_CONTENT='" + target  + "'");
//    	db.execSQL("UPDATE " + USERS_TABLE +" SET "+ USER_LASTNAME +"='" + lastname +" WHERE " + USER_ID+ "='" + username  + "'"); 
    	db.execSQL("UPDATE "+ USERS_TABLE +" SET "+ USER_FIRSTNAME +"='" + firstname + "' WHERE "+ USER_ID+ "='" + username  + "'");
    	db.execSQL("UPDATE "+ USERS_TABLE +" SET "+ USER_LASTNAME +"='" + lastname + "' WHERE "+ USER_ID+ "='" + username  + "'");
    	db.execSQL("UPDATE "+ USERS_TABLE +" SET "+ USER_PASSWORD +"='" + password + "' WHERE "+ USER_ID+ "='" + username  + "'");
    	db.execSQL("UPDATE "+ USERS_TABLE +" SET "+ USER_PHONENUM +"='" + phonenum + "' WHERE "+ USER_ID+ "='" + username  + "'");
    	
    	
    	db.close();
	}
	/**
	 * @author Xingze
	 * @param the element deleted from database
	 */
    public void deleteUsers(String[] name){
        String where = USER_ID+" = ?";  
        String[] whereValues = name;   
        this.getWritableDatabase().delete(USERS_TABLE, where, whereValues);  
        this.getWritableDatabase().close();  
        
    }
	
	/**
	 * Inserts into the table in the local database with parameters passed in arg[0],...,arg[n]
	 * The insert is done in a background thread
	 */
	private class insertUserToLocalDB extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg) {
			SQLiteStatement statement = thisDB.compileStatement("INSERT into "+CHILDREN_TABLE +"("
					+USER_ID +","
					+USER_NAME+","
					+USER_PASSWORD+ ""
					+USER_FIRSTNAME+ ","
					+USER_LASTNAME+","
					+USER_PHONENUM+","
					+USER_PRIVILEGE+")VALUES(?,?,?,?,?,?,?);");		
					
			statement.bindString(1,"9999"); //TEMP USER ID
			statement.bindString(2,arg[0]);
			statement.bindString(3,arg[1]);
			statement.bindString(4,arg[2]);
			statement.bindString(5,arg[3]);
			statement.bindString(6,arg[4]);
			statement.bindString(7,arg[5]);
			statement.executeInsert();
			statement.close();	
			return null;
		}
	}

	/**
	 * getUserTableFromRemoteDB 
	 * when user table in the local database needs to be updated from remote database
	 * this method will be called. This method calls getChildrenTableFromRemoteDB
	 */
	public void getUserTableFromRemoteDB(){
		userlist = new ArrayList<HashMap<String, Object>>();
		thisDB=this.getWritableDatabase();
		pullUserFromRemoteDB task = new pullUserFromRemoteDB();
		task.execute();
	}

	/**
	 * pullUserFromRemoteDB 
	 * when children table in the local database needs to be updated from remote database
	 * this method will be called. This method calls getChildrenTableFromRemoteDB
	 */
	private class pullUserFromRemoteDB extends AsyncTask<String, String, String>{

		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_users = "http://192.168.0.10/Nutristep/get_all_users.php";

		@Override
		/*
		 * getting all users from url
		 */
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
	
	
	
	//=====================CHILDREN TABLE RELATED=====================
	
	/**
	 * Returns a list of all children in the child table from the local database 
	 * @return childrenList ArrayList<HashMap<String,Object>>
	 */
	public ArrayList<HashMap<String, Object>> getListofChildren(){
		ArrayList<HashMap<String, Object>> childrenList= new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM "+CHILDREN_TABLE;
		HashMap<String,Object> map = new HashMap<String,Object>();
		Cursor cursor = db.rawQuery(query, null);
		   if (cursor.moveToFirst()) {
		        do {
		        	map.put("location", cursor.getString(1)+" "+cursor.getString(2));
		        	childrenList.add(map);
		        	Log.d("Map",cursor.getString(1));
		        } while (cursor.moveToNext());
		    }
		   
		return childrenList;
		
	}
	
	/**
	 * getChildrenTableFromRemoteDB 
	 * when children table in the local database needs to be updated from remote database
	 * this method will be called. This method calls getChildrenTableFromRemoteDB
	 */
	public void getChildrenTableFromRemoteDB(){
		childrenList= new ArrayList<HashMap<String, Object>>();
		thisDB=this.getWritableDatabase();
		pullChildrenFromRemoteDB task = new pullChildrenFromRemoteDB();
		task.execute();
	}
	
	/**
	 * pullChildrenFromRemoteDB class that calls to a php webservice
	 * which is done in the background. The php script queries the remote database and
	 * returns the result in a JSON string which will be parsed to retrieve each of the table elements.
	 */
	private class pullChildrenFromRemoteDB extends AsyncTask<String, String, String>{

		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_children = "http://192.168.0.10/Nutristep/get_all_children.php";
		
		/*
		 * getting all children from url
		 */
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
					children = json.getJSONArray(TAG_CHILDREN);

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
						String child_address=c.getString(CHILD_ADDRESS);
						
						// Inserting the values into the table
						SQLiteStatement statement = thisDB.compileStatement("INSERT into "+CHILDREN_TABLE +"("
								+CHILD_ID +","
								+CHILD_FName+","
								+CHILD_LName+","
								+CHILDREN_BIRTH+","
								+CHILDREN_GENDER+","
								+CHILD_ADDRESS+","
								+CHILDREN_POSTAL+ ","
								+CHILDREN_PHONE+ ")VALUES(?,?,?,?,?,?,?,?);");
								
						statement.bindString(1,child_id);
						statement.bindString(2,child_firstName);
						statement.bindString(3,child_lastName);
						statement.bindString(4,child_gender);
						statement.bindString(5,child_birthdate);
						statement.bindString(6,child_address);
						statement.bindString(7,child_postalcode);
						statement.bindString(8,child_phonenum);
						
						statement.executeInsert();
						statement.close();				

						// creating new HashMap
						HashMap<String, Object> map = new HashMap<String, Object>();

						// adding each child node to HashMap key => value
						map.put(CHILD_ID,child_id);
						map.put(CHILD_FName,child_firstName);
						map.put(CHILD_LName,child_lastName);
						map.put(CHILDREN_BIRTH,child_birthdate);
						map.put(CHILDREN_GENDER,child_gender);
						map.put(CHILD_ADDRESS, child_address);
						map.put(CHILDREN_POSTAL,child_postalcode);
						map.put(CHILDREN_PHONE,child_phonenum);

						
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
	
	/**
	 * Adding a new child to the database or modify already existing child. Passes the parameters to 
	 * insertChildToLocalDB to insert into the table in the local database to be performed in a background thread
	 * @param firstName Child's first name
	 * @param lastName Child's last name
	 * @param gender Child's gender
	 * @param birthdate Child's Date of birth
	 * @param address Child's current home address
	 * @param postal Child's postal code
	 * @param phoneNum Child's phone number
	 */
	public void addNewChild(String firstName, String lastName, String gender, String birthdate, String address, String postal, String phoneNum){
		//Initializes thisDB field to write to 
		thisDB=this.getWritableDatabase(); 
		insertChildToLocalDB task = new insertChildToLocalDB();
		task.execute(firstName, lastName, gender, birthdate, address, postal, phoneNum);
	
	}
	/**
	 * Inserts into the table in the local database with parameters passed in arg[0],...,arg[n]
	 * The insert is done in a background thread
	 */
	private class insertChildToLocalDB extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg) {
			SQLiteStatement statement = thisDB.compileStatement("INSERT into "+CHILDREN_TABLE +"("
					+CHILD_ID +","
					+CHILD_FName+","
					+CHILD_LName+","
					+CHILDREN_BIRTH+","
					+CHILDREN_GENDER+","
					+CHILD_ADDRESS+","
					+CHILDREN_POSTAL+ ","
					+CHILDREN_PHONE+ ")VALUES(?,?,?,?,?,?,?,?);");
					
			statement.bindString(1,"9999"); //TEMP ID
			statement.bindString(2,arg[0]);
			statement.bindString(3,arg[1]);
			statement.bindString(4,arg[2]);
			statement.bindString(5,arg[3]);
			statement.bindString(6,arg[4]);
			statement.bindString(7,arg[5]);
			statement.bindString(8,arg[6]);
			statement.executeInsert();
			statement.close();	
			return null;
		}
	}
	
	
	//=====================INSTITUTION TABLE RELATED=====================
	/**
	 * Adding a new institution to the database or modify already existing institution. Passes the parameters
	 * to insertInstitutionToLocalDB which will insert into the table in the local database in a background thread
	 * @param institutionName - The institution's name
	 * @param address - The address of the institution
	 * @param description - Description of the institution
	 */
	public void addNewInstitution(String institutionName, String address, String description){
		thisDB=this.getWritableDatabase();
		insertInstitutionToLocalDB task = new insertInstitutionToLocalDB();
		task.execute(institutionName, address, description);
	}
	
	/**
	 * Inserts into the table in the local database with parameters passed in arg[0],...,arg[n]
	 * The insert is done in a background thread
	 */
	private class insertInstitutionToLocalDB extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg) {
			SQLiteStatement statement = thisDB.compileStatement("INSERT into "+INSTI_TABLE +"("
					+INSTI_ID+","
					+INSTI_name+","
					+INSTI_address+","
					+INSTI_Descipt+") VALUES(?,?,?,?);");
			
			statement.bindString(1, "9999"); //TEMP ID
			statement.bindString(2, arg[0]);
			statement.bindString(3, arg[1]);
			statement.bindString(4, arg[2]);
			statement.executeInsert();
			statement.close();	
					
	
			return null;
		}
	}
	
	/**
	 * getInsitutionTableFromRemoteDB 
	 * when institution table in the local database needs to be updated from remote database
	 * this method will be called. This method calls pullInstitutionFromRemoteDB
	 */
	public void getInsitutionTableFromRemoteDB(){
		institutionList= new ArrayList<HashMap<String, Object>>();
		thisDB=this.getWritableDatabase();
		pullInstitutionFromRemoteDB task = new pullInstitutionFromRemoteDB();
		task.execute();
	}
	
	/**
	 * pullInstitutionFromRemoteDB class that calls to a php webservice
	 * which is done in the background. The php script queries the remote database and
	 * returns the result in a JSON string which will be parsed to retrieve each of the table elements.
	 */
	private class pullInstitutionFromRemoteDB extends AsyncTask<String, String, String>{

		JSONParser jParser = new JSONParser();
		// url to get all user list
		private static final String url_all_institution = "http://192.168.0.10/Nutristep/get_all_institutions.php";
		
		/*
		 * getting all institutions from url
		 */
		@Override
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_institution, "GET", params);
			assert(!json.equals(null));
			// Check your log cat for JSON reponse
			Log.d("All institutions: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) { // institutions found
				
					// Getting Array of institutions
					institutions = json.getJSONArray(TAG_INSTITUTIONS);

					//looping through All institutions
					for (int i = 0; i < institutions.length(); i++) {
						JSONObject c = institutions.getJSONObject(i);

						// Storing each json item in variable
						String institutionId = c.getString(INSTI_ID);
						String institutionName = c.getString(INSTI_name);
						String institutionAddress=c.getString(INSTI_address);
						String institutionDescription=c.getString(INSTI_Descipt);

						// Inserting the values into the table
						SQLiteStatement statement = thisDB.compileStatement("INSERT into "+INSTI_TABLE +"("
								+INSTI_ID+","
								+INSTI_name+","
								+INSTI_address+","
								+INSTI_Descipt+") VALUES(?,?,?,?);");
						
						statement.bindString(1, institutionId);
						statement.bindString(2, institutionName);
						statement.bindString(3,institutionAddress);
						statement.bindString(4,institutionDescription);
						statement.executeInsert();
						statement.close();				

						// creating new HashMap
						HashMap<String, Object> map = new HashMap<String, Object>();

						// adding each institutions node to HashMap key => value
						map.put(INSTI_ID, institutionId);
						map.put(INSTI_name, institutionName);
						map.put(INSTI_address, institutionAddress);
						map.put(INSTI_Descipt, institutionDescription);
						
						// adding HashList to ArrayList
						institutionList.add(map);
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

