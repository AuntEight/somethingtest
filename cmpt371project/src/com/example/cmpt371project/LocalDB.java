package com.example.cmpt371project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.content.PeriodicSync;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

@SuppressWarnings("unused")
public class LocalDB extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "localDB";  
	private final static int DATABASE_VERSION = 1;  
	private final static String TABLE_NAME = "userData";  
	public final static String USER_ID = "user_ID";  
	public final static String USER_PASSWORD = "user_Password";  
	public String fileContents="";
	private static Context contextLocalDB;


	public LocalDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);	
		System.out.println("oncreate");
		contextLocalDB=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		System.out.println("DB start");
		System.out.println(" in oncreate");
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+USER_ID +" TEXT, "
				+USER_PASSWORD+ " TEXT);";
		db.execSQL(sql);
		System.out.println("DB created");
		
		
//		ContentValues childIn = new ContentValues();
		SQLiteStatement statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
		//Testing where the program crashed
//		statement.bindString(1, "Before");
//		statement.bindString(2, "Try block");
//		statement.executeInsert();
//		statement.close();


		try {
			
			String line = "";
			String tableName ="userData";
			String columns = "user_ID, user_Password";
			String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
			String str2 = ");";
			//Testing where the program crashed
//			statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
//			statement.bindString(1, "Before");
//			statement.bindString(2, "Loop");
//			statement.executeInsert();
//			statement.close();

			// To be able to read the file, the file must be in the assets directory
			AssetManager am= contextLocalDB.getAssets(); 
			InputStream is = am.open("test.csv"); // opens the file in the assets file
			//files in the assets directory are included in the apk
			BufferedReader r = new BufferedReader(new InputStreamReader(is)); //Reads in the file

			String line2="";
			while ((line = r.readLine()) != null) {
				String[] str = line.split(",");	
				statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
				statement.bindString(1, str[0].toString());
				statement.bindString(2, str[1].toString());
				statement.executeInsert();
				statement.close();
			}
			r.close();
			Log.i("MyMessage", "This is my message to Logcat");
			statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
			statement.bindString(1, "After");
			statement.bindString(2, "Loop");
			statement.executeInsert();
			statement.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			Log.d("FILE_NOT_FOUND", e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.toString());

			e.printStackTrace();
		}
		//Testing where the program crashed
//		statement = db.compileStatement("INSERT into userData(user_ID,user_Password) VALUES(?,?);");
//		statement.bindString(1, "After");
//		statement.bindString(2, "Try block");
//		statement.executeInsert();
//		statement.close();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("DB upgrade");
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME; 
		db.execSQL(sql);
		onCreate(db);

	}

	public String readPassword(String username){
		//to do 
		//the password need to be encrypt
		System.out.println("enter readpassword");
		String password = new String();
		Cursor DBcursor = this.getWritableDatabase().rawQuery("Select user_Password from userData WHERE user_ID='" + username + "'",null);

		while(DBcursor.moveToNext()){	
			password = DBcursor.getString(0);	
		}
		DBcursor.close(); 
		this.getWritableDatabase().close();
		System.out.println("finish readpassword");
		return password;
	}
}
