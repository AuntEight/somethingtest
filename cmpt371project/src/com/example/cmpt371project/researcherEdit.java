package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.cmpt371project.R;

public class researcherEdit extends Activity{

	private Button save;
	private Button cancel;
	private EditText firstName;
	private EditText lastName;
	private EditText phone;
	private EditText userName;
	private EditText passWord;
	private Button remove;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_researchers_edit);
		
		save = (Button)findViewById(R.id.edRe_save_but);
		cancel = (Button)findViewById(R.id.edRe_canc_but);
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
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		       	 Intent cancelIntent = new Intent();
		       	cancelIntent.setClass(researcherEdit.this, researcherList.class);
		       	 researcherEdit.this.startActivity(cancelIntent);
				
			}
		});
		
		//add function
		if(intentClass.compareTo("res_add")==0){
			//do not show remove in add function
			remove.setVisibility(View.INVISIBLE);
			
			//save button for add function
			save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//to do 
					//put new researcher to DB
					
				}
			});	
		}
		
		//edit function
		if(intentClass.compareTo("res_view")==0){
//			mBundle.get("name");
			String name = (String) mBundle.get("name");
//			String firstName = name.
			System.out.println(mBundle.get("name"));
			firstName.setText(name);
			
			//all data can not be changed
			firstName.setFocusable(false);
			save.setVisibility(View.INVISIBLE);
			cancel.setVisibility(View.INVISIBLE);
			remove.setVisibility(View.INVISIBLE);
			firstName.setFocusable(false);
			lastName.setFocusable(false);
			phone.setFocusable(false);
			userName.setFocusable(false);
			passWord.setFocusable(false);

		}
		
		if(intentClass.compareTo("res_edit")==0){
			String name = (String) mBundle.get("name");
			System.out.println(mBundle.get("name"));
			firstName.setText(name);
			
			//save button for add function
			save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//to do 
					//put new researcher to DB
					
				}
			});	
			
		}
	}

}
