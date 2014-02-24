package com.example.cmpt371project;

import com.example.cmpt371project.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
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
//				String password = testDB.readPassword(inputId);
				String password = inputId;
				if(password.compareTo(inputPassword)==0 && !inputPassword.equals("")){
//				Toast.makeText(getApplicationContext(), "1",
//						     Toast.LENGTH_SHORT).show();
	            	 Intent addIntent = new Intent();
	            	 addIntent.setClass(Login.this, admin.class);
	            	 Login.this.startActivity(addIntent);
				}
				else if(password.compareTo("res")==0 && !inputPassword.equals("") && password.compareTo(inputPassword)==0){
	            	 Intent addIntent = new Intent();
	            	 addIntent.setClass(Login.this, researcher.class);
	            	 Login.this.startActivity(addIntent);
				}
				else{
					Toast.makeText(getApplicationContext(), "Unvalid userID or password",
						     Toast.LENGTH_SHORT).show();
				}
					
					
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
