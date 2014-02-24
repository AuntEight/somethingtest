package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmpt371project.R;

public class surveyOption extends Activity{
	private Button beginButton;
	private TextView childInfor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey_option);
		
		beginButton=(Button)findViewById(R.id.opSu_begi_but);
		childInfor=(TextView)findViewById(R.id.opSu_head_lab);
		
//		Intent intent = getIntent();
//	    String message = intent.getExtras();
//		childInfo
		
		beginButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(surveyOption.this, survey.class);
	           	surveyOption.this.startActivity(addIntent);
				
			}});
	}
	
	
}
