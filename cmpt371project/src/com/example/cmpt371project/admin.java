package com.example.cmpt371project;

import com.example.cmpt371project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin extends Activity{
	
	private Button userButton;
	private Button childrenButton;
	private Button statsButton;
	private Button addLanguagerenButton;
	private Button chooseLanguageButton;
	private Button locationButton;
	private Button updateButton;
	private Button logOutButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		
		userButton= (Button) findViewById(R.id.admin_user_but);
		childrenButton= (Button) findViewById(R.id.admin_child_but);
		statsButton= (Button) findViewById(R.id.admin_stats_but);
		addLanguagerenButton= (Button) findViewById(R.id.admin_adLa_but);
		chooseLanguageButton= (Button) findViewById(R.id.admin_chLa_but);
		locationButton= (Button) findViewById(R.id.admin_loca_but);
		updateButton= (Button) findViewById(R.id.admin_update_but);
		logOutButton= (Button) findViewById(R.id.admin_logO_but);
		
		userButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
           	 Intent addIntent = new Intent();
           	 addIntent.setClass(admin.this, researcherList.class);
           	 admin.this.startActivity(addIntent);
				
			}});
		
		childrenButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(admin.this, childrenList.class);
	           	 admin.this.startActivity(addIntent);
				
			}});
		
		addLanguagerenButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
		
		chooseLanguageButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
		
		locationButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(admin.this, locationList.class);
	           	 admin.this.startActivity(addIntent);
				
			}});
		
		updateButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
		
		logOutButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(admin.this, Login.class);
	           	 admin.this.startActivity(addIntent);
				
			}});
		
	}

}
