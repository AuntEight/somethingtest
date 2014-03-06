package com.example.cmpt371project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cmpt371project.R;

public class researcherEdit extends Activity{

	private Button save;
	private Button edit;
	private EditText firstName;
	private EditText lastName;
	private EditText phone;
	private EditText userName;
	private EditText passWord;
	private Button remove;
	private LocalDB resDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		resDB = new LocalDB(this);
		setContentView(R.layout.activity_researchers_edit);
		
		save = (Button)findViewById(R.id.edRe_save_but);
		edit = (Button)findViewById(R.id.edRe_Edit_but);
		remove = (Button)findViewById(R.id.edRe_Remo_but);
		firstName = (EditText)findViewById(R.id.edRe_firstname_txt);
		lastName = (EditText)findViewById(R.id.edRe_lastname_txt);
		phone = (EditText)findViewById(R.id.edRe_phon_txt);
		userName = (EditText)findViewById(R.id.edRe_userName_txt);
		passWord = (EditText)findViewById(R.id.edRe_password_txt);
		
		//get intent 
		//if intent = res_add, open add function
		//if intent = res_edit,open edit function
		Bundle mBundle = getIntent().getExtras();
		String intentClass = (String) mBundle.get("from");
		System.out.println(intentClass);
		
//		cancel.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//		       	 Intent cancelIntent = new Intent();
//		       	cancelIntent.setClass(researcherEdit.this, researcherList.class);
//		       	 researcherEdit.this.startActivity(cancelIntent);
//				
//			}
//		});
		
		//add function
		if(intentClass.compareTo("res_add")==0){
			//do not show remove in add function
			remove.setVisibility(View.INVISIBLE);
			edit.setVisibility(View.INVISIBLE);
			
			//save button for add function
			save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//to do 
					String inFirstName = firstName.getText().toString();
					String inLastName = lastName.getText().toString();
					String inPhone = phone.getText().toString();
					String inUserName = userName.getText().toString();
					String inPassword = passWord.getText().toString();
					
					//if user do not input all the fields
					if(inFirstName.compareTo("")==0
						||inLastName.compareTo("")==0
						||inPhone.compareTo("")==0
						||inUserName.compareTo("")==0
						||inPassword.compareTo("")==0){
						Context context = getApplicationContext();
						CharSequence text = getResources().getString(R.string.inputError);
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					} else{
						//if user input all the fields
						resDB.addNewUser(inUserName, inPassword, inFirstName, inLastName, inPhone, "researcher");
						
						firstName.setText("");
						lastName.setText("");
						phone.setText("");
						userName.setText("");
						passWord.setText("");
					}
					
				}
			});	
		}
		
		//edit function
		if(intentClass.compareTo("res_view")==0){

//	       	viewIntent.putExtra("userID", itemID);
//	       	viewIntent.putExtra("userPassword", password);
//	       	viewIntent.putExtra("userFirstName", firstName);
//	       	viewIntent.putExtra("userLastName", lastName);
//	       	viewIntent.putExtra("userPhoneNum", phoneN);
			
			String userID = (String) mBundle.get("userID");
			String userPassword = (String) mBundle.get("userPassword");
			String userFirstName = (String) mBundle.get("userFirstName");
			String userLastName = (String) mBundle.get("userLastName");
			String userPhoneNum = (String) mBundle.get("userPhoneNum");
			
//			String firstName = name.
			System.out.println(mBundle.get("name"));
			firstName.setText(userFirstName);
			lastName.setText(userLastName);
			phone.setText(userPhoneNum);
			userName.setText(userID);
			passWord.setText(userPassword);
			
			
			//all data can not be changed
			save.setVisibility(View.INVISIBLE);
			remove.setVisibility(View.INVISIBLE);
			firstName.setFocusable(false);
			lastName.setFocusable(false);
			phone.setFocusable(false);
			userName.setFocusable(false);
			passWord.setFocusable(false);

			firstName.setFocusableInTouchMode(false);
			lastName.setFocusableInTouchMode(false);
			phone.setFocusableInTouchMode(false);
			userName.setFocusableInTouchMode(false);
			passWord.setFocusableInTouchMode(false);
			
			
			//click edit, set all fields editable, and set save button visible
			edit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//set fields editable
					firstName.setFocusable(true);
					lastName.setFocusable(true);
					phone.setFocusable(true);
					passWord.setFocusable(true);
					
					firstName.setFocusableInTouchMode(true);
					lastName.setFocusableInTouchMode(true);
					phone.setFocusableInTouchMode(true);
					passWord.setFocusableInTouchMode(true);
					
					firstName.requestFocus();
					lastName.requestFocus();
					phone.requestFocus();
					passWord.requestFocus();
					
					
					save.setVisibility(View.VISIBLE);
					edit.setVisibility(View.INVISIBLE);
				}
			});
			
			//save button
			save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String inFirstName = firstName.getText().toString();
					String inLastName = lastName.getText().toString();
					String inPhone = phone.getText().toString();
					String inUserName = userName.getText().toString();
					String inPassword = passWord.getText().toString();
					
					if(inFirstName.compareTo("")==0
							||inLastName.compareTo("")==0
							||inPhone.compareTo("")==0
							||inUserName.compareTo("")==0
							||inPassword.compareTo("")==0){
							Context context = getApplicationContext();
							CharSequence text = getResources().getString(R.string.inputError);
							int duration = Toast.LENGTH_SHORT;

							Toast toast = Toast.makeText(context, text, duration);
							toast.show();
					}else{
						resDB.updateUsers(inUserName, inPassword, inFirstName, inLastName, inPhone);
						
						//change button status
						save.setVisibility(View.INVISIBLE);
						edit.setVisibility(View.VISIBLE);
						
						
						//all data can not be changed
						firstName.setFocusable(false);
						lastName.setFocusable(false);
						phone.setFocusable(false);
						userName.setFocusable(false);
						passWord.setFocusable(false);

						firstName.setFocusableInTouchMode(false);
						lastName.setFocusableInTouchMode(false);
						phone.setFocusableInTouchMode(false);
						userName.setFocusableInTouchMode(false);
						passWord.setFocusableInTouchMode(false);
					}
					
				}
			});

		}
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent backIntent = new Intent();
   	 	backIntent.setClass(researcherEdit.this, researcherList.class);
   	 	researcherEdit.this.startActivity(backIntent);
	}

	
}
