package com.example.cmpt371project;


import com.example.cmpt371project.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private EditText userID;
	private EditText password;
	private Button logInButton;
	private Button upDateButton;
	private LocalDB testDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		userID = (EditText) findViewById(R.id.userNameInput);
		password = (EditText) findViewById(R.id.passWordInput);
		logInButton= (Button) findViewById(R.id.login);
		upDateButton= (Button) findViewById(R.id.update);

		
		testDB = new LocalDB(this);
		
		logInButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String inputId = userID.getText().toString();
				String inputPassword = password.getText().toString();
				System.out.println("input user id is "+ inputId);
				System.out.println("input password id is "+ inputPassword);
				String password = testDB.readPassword(inputId);
//delete for test				
//			if(password.compareTo(inputPassword)==0 && !inputPassword.equals("")){ 
//				

//					String privilege = testDB.getPrivilege(inputId);
//					if(privilege.compareTo("administrator")==0){
					if(inputId.compareTo("admin")==0&&inputPassword.compareTo("admin")==0){
						 Intent addIntent = new Intent();
		            	 addIntent.setClass(Login.this, admin.class);
		            	 Login.this.startActivity(addIntent);
		            	 finish();
					}
//delete for test	
//					else if(privilege.compareTo("researcher")==0){
				else if(inputId.compareTo("res")==0&&inputPassword.compareTo("res")==0){
						 Intent resIntent = new Intent();
		            	 resIntent.setClass(Login.this, researcher.class);
		            	 Login.this.startActivity(resIntent);
		            	 finish(); 
					}					
	            	
//				} //for test
				else{
					Context context = getApplicationContext();
					CharSequence text = "Invalid username or password";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
					
			}
			
		});

		/*
		 * Update works but there might be some conflict with existing entries
		 * in the remote database so for now the function calls are commented out
		 * until a php script is made to not add duplicates
		 */
		upDateButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				if(haveNetworkConnection()){
					Log.d("Network Connection","Has connection");
					testDB.updateUserTable();
					//testDB.exportUserTable();
				}
				else {
					Log.d("Network Connection","NO connection");
					AlertDialog alertDialog = new AlertDialog.Builder(
							Login.this).create();

					// Setting Dialog Title
					alertDialog.setTitle("No network connection detected");

					// Setting Dialog Message
					alertDialog.setMessage("Please connect to a network connection before updating");


					// Setting OK Button
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to execute after dialog closed
							//Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
						}
					});

					// Showing Alert Message
					alertDialog.show();	 
				}

			}

		});
	}
	private boolean haveNetworkConnection() {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		
		return true;
	}

}
