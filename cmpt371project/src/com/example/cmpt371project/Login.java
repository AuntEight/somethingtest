package com.example.cmpt371project;


import com.example.cmpt371project.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
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
		
		userID = (EditText) findViewById(R.id.log_user_txt);
		password = (EditText) findViewById(R.id.log_pass_txt);
		logInButton= (Button) findViewById(R.id.log_logI_but);
		upDateButton= (Button) findViewById(R.id.log_upda_but);
	
		
		testDB = new LocalDB(this);
		
		logInButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String inputId = userID.getText().toString();
				String inputPassword = password.getText().toString();
				System.out.println("input user id is "+ inputId);
				System.out.println("input password id is "+ inputPassword);
				String password = testDB.readPassword(inputId);
				if(password.compareTo(inputPassword)==0 && !inputPassword.equals("")){
	            	 Intent addIntent = new Intent();
	            	 addIntent.setClass(Login.this, admin.class);
	            	 Login.this.startActivity(addIntent);
				}
				else{
	            	 Intent addIntent = new Intent();
	            	 addIntent.setClass(Login.this, researcher.class);
	            	 Login.this.startActivity(addIntent);
				}
					
			}
			
		});

		upDateButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// Launching All users Activity
				testDB.updateUserTable();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		
		return true;
	}

}
