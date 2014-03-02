package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cmpt371project.R;

public class researcher extends Activity{
	private Button childrenButton;
	private Button locationButton;
	private Button updateButton;
	private Button statsButton;
	private Button changeLanguageButton;
	private Button logOutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_researcher);
		childrenButton=(Button)findViewById(R.id.stRe_child_but);
		locationButton=(Button)findViewById(R.id.stRe_loca_but);
		updateButton=(Button)findViewById(R.id.stRe_update_but);
		statsButton=(Button)findViewById(R.id.stRe_stats_but);
		changeLanguageButton=(Button)findViewById(R.id.stRe_chLa_but);
		logOutButton=(Button)findViewById(R.id.stRe_logO_but);

		childrenButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent addIntent = new Intent();
				addIntent.setClass(researcher.this, childrenList.class);
				researcher.this.startActivity(addIntent);

			}});

		locationButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent addIntent = new Intent();
				addIntent.setClass(researcher.this, locationList.class);
				researcher.this.startActivity(addIntent);

			}});

		logOutButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent addIntent = new Intent();
				addIntent.setClass(researcher.this, Login.class);
				researcher.this.startActivity(addIntent);
				finish(); 
			}});
	}


}
